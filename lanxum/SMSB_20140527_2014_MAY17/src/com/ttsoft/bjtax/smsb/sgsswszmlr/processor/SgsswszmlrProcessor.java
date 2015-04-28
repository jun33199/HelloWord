package com.ttsoft.bjtax.smsb.sgsswszmlr.processor;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.dj.model.dm.SZSM;
import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sgsswszmlr.common.Constant;
import com.ttsoft.bjtax.smsb.sgsswszmlr.vo.SgsswszmMXVo;
import com.ttsoft.bjtax.smsb.sgsswszmlr.vo.SgsswszmVo;
import com.ttsoft.bjtax.smsb.sgsswszmlr.web.SgsswszmlrForm;
import com.ttsoft.bjtax.smsb.util.gzsxexcel.DateTimeUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.StringUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.util.Currency;

public class SgsswszmlrProcessor implements Processor {
	public Object process(VOPackage vo) throws BaseException {
		 Object result = null;
		    if (vo == null) {
		      throw new NullPointerException();
		    }

		    switch (vo.getAction()) {
		      case CodeConstant.SMSB_INIT:
		    	  //初始化显示
		        result = doInit(vo);
		        break;
		      case CodeConstant.SMSB_SAVEACTION:
		    	  //保存手工录入信息
		        result = SaveNewAdd(vo);
		        break;
		      case CodeConstant.SMSB_UPDATEACTION: 
		    	  //修改
		    	 result = doUpdate(vo);
		    	  break;
		      case CodeConstant.SMSB_QUERYACTION:
		    	  result = doGetAll(vo);
		    	  break;
		      case Constant.QUERYONE:
		    	  result = doGetOne(vo);
		    	  break;
		      case Constant.CONS_SGLR_PRINT_SUCCESS:
		    	  result = doPrintSuccess(vo);
		    	  break;
		      case Constant.CONS_SGLR_PRINT_NEWPH:
		    	  result = doPrintByNewWSZH(vo);
		    	  break;
		      case Constant.CONS_SGLR_CANCLE:
		    	  result = doCancel(vo);
		    	  break;
		      default:
		        throw new UnsupportedOperationException(
		            "Method process() not yet implemented.");
		    }
		    return result;
	}
	/**
	 * 页面初始化
	 * @param vo
	 * @return
	 */
	private Object doInit(VOPackage vo) throws BaseException{
		  ArrayList res = new ArrayList();
		  Connection conn =null;
		  PreparedStatement ps = null;
		  UserData ud = vo.getUserData();
		  try{
			  conn = SfDBResource.getConnection();
			  String querySql = "select szsmdm,  t.szsmdm||'-'||t.szsmmc  ms from dmdb.sb_dm_szsm t where t.ccbs='2' order by t.szsmdm";
			  ps = conn.prepareStatement(querySql);
			  ResultSet rs = ps.executeQuery();
			  
			  SZSM szsmFirst  = new SZSM();
			  szsmFirst.setSzsmdm("");
			  szsmFirst.setSzsmmc("");
			  res.add(szsmFirst);
			  
			  while(rs.next()){
				SZSM szsm  = new SZSM();
				szsm.setSzsmdm(rs.getString("szsmdm"));
				szsm.setSzsmmc(rs.getString("ms"));
				res.add(szsm);
			  }
			  //判断是否能获取到票号  // deleted by tangchangfu 新票样后续维护 删除初始化时票证账户以及是否有票的判断 2014-03-12
/*			  if(!this.havePH(ud)){
				  throw new ApplicationException("页面初始化失败，无法获取税收完税证明，由于无票证账户代码   或  账户下无该类票据  或  账户中该类票已经用完，请确认！");
			  }*/
		  }catch(Exception e){
			  e.printStackTrace();
				//如果是特定抛出的异常
				if(e instanceof BaseException){
					throw new ApplicationException(e.getMessage());
				}
				
				throw new ApplicationException("页面初始化失败，请退出重试！");
		  }finally{
			  SfDBResource.freeConnection(conn);
		  }
		return res;
	}
	
	/**
	 * 保存新增信息
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private SgsswszmlrForm SaveNewAdd(VOPackage vo) throws BaseException{
		Connection conn = null;
		UserData ud = vo.getUserData();
		//获得前台提交的数据
		SgsswszmlrForm data = (SgsswszmlrForm) vo.getData();
		HashMap wszmap = null;
		
		try{
			// 获取数据连接
			conn = SfDBResource.getConnection();
			String allNewAddInfo = data.getWsxxAll();
			
			if(allNewAddInfo == null || "".equals(allNewAddInfo)){
				throw new ApplicationException("保存手工录入信息出错，无法获得提交数据，请重试，或者和管理员联系！");
			}
			
			//构造提交信息
			ArrayList SaveInfoList = constructMx2SaveOrUpdate(ud, allNewAddInfo);
			//获取完税证
			  //判断是否能获取到票号
			  if(!this.havePH(ud)){
				  throw new ApplicationException("无法获取税收完税证明，由于无票证账户代码   或  账户下无该类票据  或  账户中该类票已经用完，请确认！");
			  }
			wszmap = getNewPH( ud,data.getSjjeHJ());
			//执行保存
			//(1)保存主表
			save2zb(conn, wszmap, ud, data);
			//(2)保存明细
			save2Mx(conn, wszmap, ud, SaveInfoList);
			
			//回显保存信息
/*			String wszmKey = Constant.CONS_SGLR_PZZLDM+"-"+(String)wszmap.get("wszh")+"-"+(String)wszmap.get("ndzb");
			data.setWszmKey(wszmKey);*/
			data.setQuery_nsrmc(data.getNsrmc());
			data.setQuery_nsrsbh(data.getNsrsbh());
			data.setQuery_wspzh((String)wszmap.get("wszh"));
			//执行查询
			vo.setData(data);
			data = (SgsswszmlrForm)doGetAll(vo);
			data.setSaveIsSucc(Constant.CONS_SGLR_SAVEISSUCC_Y);//保存成功
		}catch(Exception e){
			e.printStackTrace();
			//作废票号
			if(phIsZUOFEI(conn,wszmap) && wszmap != null){
				try{
					this.canclePH(ud, wszmap, "0.00");
				}catch(Exception e1){
					e1.printStackTrace();
					this.canclePH(ud, wszmap, "0.00");
				}
			}
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("保存信息失败，退出重试！");
		}finally {
			SfDBResource.freeConnection(conn);
	       }
		return data;
	}
	private ArrayList constructMx2SaveOrUpdate(UserData ud, String allNewAddInfo)throws BaseException {
		ArrayList SaveInfoList = new ArrayList();
		try{
		String regEx ="[\\^]";//定义多组用^分开
		String[] splitAllNewAddInfo = allNewAddInfo.split(regEx);

		for (int index = 0; index < splitAllNewAddInfo.length; index++) {

			String[] oneRowInfoArr = new String[] {};
			if (splitAllNewAddInfo[index] != null) {
				System.out.println("每行手工录入信息################################"+splitAllNewAddInfo[index]);

				oneRowInfoArr = splitAllNewAddInfo[index].split("~");
				if (oneRowInfoArr.length != 0) {
					SgsswszmMXVo temp = new SgsswszmMXVo();
					
					temp.setOld_pzh(oneRowInfoArr[0]);
					temp.setSelect_zssmdm(oneRowInfoArr[1]);
					temp.setSkssksrq(oneRowInfoArr[2]);
					temp.setSkssjsrq(oneRowInfoArr[3]);
					temp.setRk_tkrq(oneRowInfoArr[4]);
					temp.setSjje(oneRowInfoArr[5]);
					temp.setSwjgzzjgdm(ud.getSsdwdm());
					temp.setLrr(ud.getYhid());
					temp.setCjr(ud.getYhid());
					
					SaveInfoList.add(temp);
					//检查是否已经存在
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("获取保存明细信息失败，请重试！");
		}
		return SaveInfoList;
	}
	
	/**
	 * 保存信息到手工主表
	 * @param conn
	 * @param data
	 */
	private boolean save2zb(Connection conn,HashMap wszmap,UserData ud,SgsswszmlrForm data)throws
    BaseException{
		boolean savSucc = false;
		
		try{
			String zbSql = "INSERT INTO SBDB.SB_JL_KJSSWSZM(pzlxdm,pzlydm,sjm,pzzldm,wszh,ndzb,nsrsbh,nsrmc,swjgzzjgdm,kjswjgzzjgdm,tfrq   ,hjje,cjr,   cjrq,lrr,   lrrq,qxdm,ypzh,ypzzldm,ywszh,yndzb,ywhm,dybz,dycs,yxbz,kjlydm,bz,zhdm)" +
					                                 "VALUES(    ?,     ?,  ?,     ?,   ?,   ?,     ?,    ?,      null,           ?,sysdate,   ?,  ?,sysdate,  ?,sysdate,   ?,null,   null, null, null,null,   ?,   ?,   ?,      ?,?,?)";
			PreparedStatement ps = conn.prepareStatement(zbSql);
			int index =1;
			ps.setString(index++, data.getPzlxdm());//录入凭证类型  0 -- 缴税凭证    1 -- 退税凭证
			ps.setString(index++, data.getPzlydm());//凭证来源代码 默认为8--手工完税证明
			ps.setString(index++, StringUtil.randomString(10));//随机码
			ps.setString(index++, Constant.CONS_SGLR_PZZLDM);//票证种类代码
			ps.setString(index++, (String)wszmap.get("wszh"));//完税证明完税证号
			ps.setString(index++, (String)wszmap.get("ndzb"));//年度字别
			ps.setString(index++, data.getNsrsbh());
			ps.setString(index++, data.getNsrmc());
			ps.setString(index++, ud.getSsdwdm());//开具税务机关组织机构代码
			ps.setBigDecimal(index++, string2BigDecimal(data.getSjjeHJ()));//合计金额
			ps.setString(index++, ud.getYhid());//创建人
			ps.setString(index++, ud.getYhid());//录入人
			ps.setString(index++, ud.getSsdwdm().substring(0, 2));//区县代码
			ps.setString(index++, data.getDybz());//打印标志
			ps.setInt(index++, data.getDycs());//打印次数
			ps.setString(index++, data.getYxbz());//有效标志
			ps.setString(index++, data.getKjlydm());//开具来源代码
			ps.setString(index++, data.getBz());//备注
			ps.setString(index++, ud.getXtsbm1());//账户代码
			
			
			savSucc= ps.execute();
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("保存手工录入信息到主表失败！");
		}
		
		return savSucc;
	}
	
	/**
	 * 保存明细信息
	 * @param conn
	 * @param data
	 * @param ud
	 * @param mxList
	 * @return
	 */
	private boolean save2Mx(Connection conn,HashMap wszmap,UserData ud,ArrayList mxList)throws
    BaseException{
		
		boolean savSucc = false;
		try{
			String mxSQL ="insert into SBDB.SB_JL_KJSSWSZMMX(pzzldm,wszh,ndzb,xh,ypzh,szdm,szsmdm,swjgzzjgdm,kjswjgzzjgdm,skssksrq,skssjsrq,rtkrq,sjtje,cjr,   cjrq,lrr,lrrq   ,qxdm)" +
					                                  "values(    ?,   ?,   ?, ?,   ?,   ?,     ?,      NULL,           ?,       ?,       ?,    ?,    ?,  ?,sysdate,?  ,sysdate,?)";
			PreparedStatement ps = conn.prepareStatement(mxSQL);
			
			for(int index =0; index < mxList.size(); index++){
				SgsswszmMXVo mxVo = (SgsswszmMXVo)mxList.get(index);
				int tempIndex =1;
				ps.setString(tempIndex++, Constant.CONS_SGLR_PZZLDM);//票证种类代码
				ps.setString(tempIndex++,(String)wszmap.get("wszh"));//完税证明完税证号
				ps.setString(tempIndex++,(String)wszmap.get("ndzb"));//年度字别
				
				ps.setInt(tempIndex++,index+1);//序号
				ps.setString(tempIndex++,mxVo.getOld_pzh());//手工录入凭证号
				ps.setString(tempIndex++, mxVo.getSelect_szdm());//税种代码
				ps.setString(tempIndex++, mxVo.getSelect_zssmdm());//税种税目代码
				ps.setString(tempIndex++, ud.getSsdwdm());//开具税务机关组织机构代码
				ps.setTimestamp(tempIndex++, DateTimeUtil.stringToTimestamp(mxVo.getSkssksrq(),DateTimeUtil.JAVA_DATEFORMAT));//税款所属开始日期
				ps.setTimestamp(tempIndex++, DateTimeUtil.stringToTimestamp(mxVo.getSkssjsrq(),DateTimeUtil.JAVA_DATEFORMAT));//税款所属结束日期
				ps.setTimestamp(tempIndex++, DateTimeUtil.stringToTimestamp(mxVo.getRk_tkrq(),DateTimeUtil.JAVA_DATEFORMAT));//入(退)库日期
				ps.setBigDecimal(tempIndex++,string2BigDecimal(mxVo.getSjje()));//实缴(退)金额
				ps.setString(tempIndex++, ud.getYhid());//创建人
				ps.setString(tempIndex++, ud.getYhid());//录入人
				ps.setString(tempIndex++, ud.getSsdwdm().substring(0, 2));//区县代码
				
				ps.addBatch();
			}
			
			 ps.executeBatch();
			
			 savSucc = true;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("保存手工录入信息明细失败！");
		}
		return savSucc;
	} 
	
	/**
	 * 对特定某条录入信息进行修改
	 * 实现步骤：（1）、修改主表信息  （2）、先根据主表主键删除明细表信息 然后保存修改之后的明细
	 * 
	 * @return
	 */
	private Object doUpdate(VOPackage vo)throws BaseException{
		Connection conn = null;
		//获得前台提交的数据
		UserData ud = vo.getUserData();
		SgsswszmlrForm data = (SgsswszmlrForm) vo.getData();
		try{
			// 获取数据连接
			conn = SfDBResource.getConnection();
			
			String wszmKey = data.getWszmKey();
			if(wszmKey != null && !"".equals(wszmKey)){
				String[] keyArr = wszmKey.split(Constant.CONS_SGLR_WSZMKEY_SPLIT);
				if(keyArr != null && keyArr.length == 4){
					//获得主键信息
					String pzzldm = keyArr[0];
					String wszh = keyArr[1];
					String ndzb = keyArr[2];
					
					//已经打印不能修改
					if(!this.hasPrinted(conn, ndzb, pzzldm, wszh)){
						//完税证明未出具过完税证明
						if(hasCJWSZMByOthers(conn, ndzb, pzzldm, wszh)  == Constant.CONS_SGLR_CJWSZM_BY_OTHERS_N){
							//更新主表
							StringBuffer updateZbSQL = new StringBuffer();
							updateZbSQL.append("update SBDB.SB_JL_KJSSWSZM t set ");
							updateZbSQL.append("PZLXDM=?,NSRSBH=?,NSRMC=?,HJJE=?,LRR=?,LRRQ=sysdate,QXDM=?,BZ=? ");
							updateZbSQL.append("where t.pzzldm =? ");
							updateZbSQL.append("and t.wszh=? ");
							updateZbSQL.append("and t.ndzb=? ");
							updateZbSQL.append("and t.pzlydm=? ");
							updateZbSQL.append("and t.zhdm=? ");
							
							PreparedStatement ps = conn.prepareStatement(updateZbSQL.toString());
							int index =1;
							ps.setString(index++, data.getPzlxdm());//录入凭证类型  0 -- 缴税凭证    1 -- 退税凭证
							ps.setString(index++, data.getNsrsbh());
							ps.setString(index++, data.getNsrmc());
							ps.setBigDecimal(index++, string2BigDecimal(data.getSjjeHJ()));//合计金额
							ps.setString(index++, ud.getYhid());//录入人
							ps.setString(index++, ud.getSsdwdm().substring(ud.getSsdwdm().length()-2, ud.getSsdwdm().length()));//区县代码
							ps.setString(index++, data.getBz());//备注
							
							ps.setString(index++, pzzldm);
							ps.setString(index++, wszh);
							ps.setString(index++, ndzb);
							ps.setString(index++, Constant.CONS_SGLR_PZLYDM_08);
							ps.setString(index++, ud.getXtsbm1());//账户代码
							
							int count = ps.executeUpdate();
							
							//删除明细表旧信息
							if(count == 1){
								StringBuffer mxSQL = new StringBuffer();
								mxSQL.append("delete from  SBDB.SB_JL_KJSSWSZMMX t where t.pzzldm=? and t.ndzb=? and t.wszh=?");
								
								ps = conn.prepareStatement(mxSQL.toString());
								index =1;
								ps.setString(index++, pzzldm);
								ps.setString(index++, ndzb);
								ps.setString(index++, wszh);
								
								count = ps.executeUpdate();
								//重新插入明细信息
								if(count >0){
									String allNewAddInfo = data.getWsxxAll();
									
									if(allNewAddInfo == null || "".equals(allNewAddInfo)){
										throw new ApplicationException("保存手工录入信息出错，无法获得提交数据，请重试，或者和管理员联系！");
									}
									
									//构造提交信息
									ArrayList SaveInfoList = constructMx2SaveOrUpdate(ud, allNewAddInfo);
									
									HashMap wszmapOld = new HashMap();
									wszmapOld.put("wszh", wszh);
									wszmapOld.put("ndzb", ndzb);
									
									save2Mx(conn, wszmapOld, ud, SaveInfoList);
									
									//为查询设置条件
									data.setQuery_nsrmc(data.getNsrmc());
									data.setQuery_nsrsbh(data.getNsrsbh());
									data.setQuery_wspzh(wszh);
									data.setSaveIsSucc(Constant.CONS_SGLR_SAVEISSUCC_Y);//保存成功
								}else{
									throw new ApplicationException("修改操作失败，无法更新明细信息！");
								}
							}else{
								throw new ApplicationException("修改操作失败，无法更新汇总信息！");
							}
						}else{
							throw new ApplicationException("该证明已经被其他模块使用并出具了有效完税证明，不能进行修改，请确认！");
						}
					}else{
						throw new ApplicationException("修改操作失败，该完税证明已经打印不能修改！");
					}
				}else{
					throw new ApplicationException("修改操作失败，获取更新查询条件失败！");
				}
			}else{
				throw new ApplicationException("修改操作失败，无更新查询条件！");
			}
		}catch(Exception e){
			e.printStackTrace();
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("修改操作失败，请重试！");
		}
		
		//返回结果
		vo.setData(data);
		return this.doGetAll(vo);
	}
	
	
	
	/**
	 * 作废信息（给当前表记录打上作废标记 ；调用票证接口作废票）
	 * @return
	 */
	private Object doCancel(VOPackage vo)throws BaseException{
		
		Connection conn = null;
		//获得前台提交的数据
		UserData ud = vo.getUserData();
		SgsswszmlrForm data = (SgsswszmlrForm) vo.getData();
		try{
			String wszmKey = data.getWszmKey();
			String[] keyArr = wszmKey.split(Constant.CONS_SGLR_WSZMKEY_SPLIT);
			if(keyArr != null && keyArr.length == 4){
				//获得主键信息
				String pzzldm = keyArr[0];
				String wszh = keyArr[1];
				String ndzb = keyArr[2];
				// 获取数据连接
				conn = SfDBResource.getConnection();
				
				//完税证明未出具过完税证明
			if(hasCJWSZMByOthers(conn, ndzb, pzzldm, wszh)  == Constant.CONS_SGLR_CJWSZM_BY_OTHERS_N){
				String cancelSQL = "update SBDB.SB_JL_KJSSWSZM t  set t.yxbz =? where t.pzzldm=? and t.wszh=? and t.ndzb =? and  t.yxbz = ? and t.pzlydm=? and zhdm=?";
				PreparedStatement ps = conn.prepareStatement(cancelSQL);
				int index =1;
				ps.setString(index++, Constant.CONS_SGLR_YXBZ_1);
				ps.setString(index++, pzzldm);
				ps.setString(index++, wszh);
				ps.setString(index++, ndzb);
				ps.setString(index++, Constant.CONS_SGLR_YXBZ_0);
				ps.setString(index++, Constant.CONS_SGLR_PZLYDM_08);
				ps.setString(index++, ud.getXtsbm1());//账户代码
				int count = ps.executeUpdate();
				//作废成功
				if(count == 1){
					//作废票证模块中票据信息
					HashMap wszmap = new HashMap();
					wszmap.put("wszh", wszh);
					wszmap.put("ndzb", ndzb);
					
					this.canclePH(ud, wszmap, "0");//作废金额0
				}else{
					throw new ApplicationException("作废操作失败，未找到对应的作废数据！");
				}
				
			}else{
				throw new ApplicationException("该证明已经被其他模块使用并出具了有效完税证明，不能进行修改，请确认！");
			}
			}else{
				throw new ApplicationException("作废操作失败，无作废查询条件！");
			}
		}catch(Exception e){
			e.printStackTrace();
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("作废操作失败！");
		}finally {
			SfDBResource.freeConnection(conn);
	       }
		//返回查询记录
		data.setQuery_type(Constant.CONS_SGLR_QUERY_TYPE_0);
		vo.setData(data);
		
		return this.doGetAll(vo);
	}
	
	
	/**
	 * 打印成功（需要 1.设置打印标志  2.设置打印次数+1）
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doPrintSuccess(VOPackage vo) throws BaseException{
		UserData ud = vo.getUserData();
		//获得前台提交的数据
		SgsswszmlrForm data = (SgsswszmlrForm) vo.getData();
		
		Connection conn =null;
		try{
			 conn = SfDBResource.getConnection();
			 
			 if(isNotNullOrBlank(conn)){
				 
				 
					String wszmKey = data.getWszmKey();
					if(wszmKey != null && !"".equals(wszmKey)){
						String[] keyArr = wszmKey.split(Constant.CONS_SGLR_WSZMKEY_SPLIT);
						if(keyArr != null && keyArr.length == 4){
							//获得主键信息
							String pzzldm = keyArr[0];
							String wszh = keyArr[1];
							String ndzb = keyArr[2];
							//设置为已打印
							this.updateDYBZ(conn,ud, ndzb, pzzldm, wszh);
							
							//设置打印次数
							this.updateDYCS(conn,ud, ndzb, pzzldm, wszh);
						}else{
							throw new ApplicationException("设置打印标志出错,更新条件获取出错！");
						}
					}else{
						throw new ApplicationException("设置打印标志出错,无法获取更新条件！");
					}
			 }else{
				 throw new ApplicationException("设置打印标志出错,无法获取数据库连接！");
			 }
		}catch(Exception e){
			e.printStackTrace();
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("设置打印标志出错！");
			
		}
 		return null;
	}
	
	
	/**
	 * 取号重打（1.获得一张新票 ；2.作废原票；3.用新票号保存一条记录）
	 * @return
	 */
	private Object doPrintByNewWSZH(VOPackage vo)throws BaseException{
		Connection conn = null;
		//获得前台提交的数据
		UserData ud = vo.getUserData();
		SgsswszmlrForm data = (SgsswszmlrForm) vo.getData();
		//为返回查询设置新的查询条件
		String newWszKey ="";
		HashMap wszmap = null;
		try{
			// 获取数据连接
			conn = SfDBResource.getConnection();
			//获得旧票信息
			String wszmKey = data.getWszmKey();
			String[] keyArr = wszmKey.split(Constant.CONS_SGLR_WSZMKEY_SPLIT);
			
			//作废旧票
			this.doCancel(vo);
			
			//获得新票
			  //判断是否能获取到票号
			  if(!this.havePH(ud)){
				  throw new ApplicationException("无法获取税收完税证明，由于无票证账户代码   或  账户下无该类票据  或  账户中该类票已经用完，请确认！");
			  }
			  if(keyArr != null && keyArr.length == 4){
				  wszmap = this.getNewPH(ud, keyArr[3]);
			  }else{
				  throw new ApplicationException("无罚获取合计金额，获取税收完税证明失败，请退出后重试！");
			  }
			newWszKey = Constant.CONS_SGLR_PZZLDM+"-"+(String)wszmap.get("wszh")+"-"+(String)wszmap.get("ndzb")+"-"+keyArr[3];
			

			if(keyArr != null && keyArr.length == 4){
				//获得主键信息
				String pzzldm = keyArr[0];
				String wszh = keyArr[1];
				String ndzb = keyArr[2];
				
				//复制主表信息
				StringBuffer cpzbSQL = new StringBuffer();
				cpzbSQL.append("insert into SBDB.SB_JL_KJSSWSZM (PZLYDM,SJM,PZZLDM,WSZH,NDZB,NSRSBH,NSRMC,SWJGZZJGDM,KJSWJGZZJGDM,TFRQ,HJJE,CJR,CJRQ,LRR,LRRQ,QXDM,YPZH,YPZZLDM,YWSZH,YNDZB,YWHM,DYBZ,DYCS,YXBZ,KJLYDM,BZ,PZLXDM,zhdm) ");
				cpzbSQL.append("select PZLYDM,?,PZZLDM,?,?,NSRSBH,NSRMC,SWJGZZJGDM,?,TFRQ,HJJE,?,sysdate CJRQ,?,sysdate LRRQ,QXDM,YPZH,YPZZLDM,YWSZH,YNDZB,YWHM,0 DYBZ,0 DYCS,0 YXBZ,KJLYDM,BZ,PZLXDM ,zhdm ");
				cpzbSQL.append("from SBDB.SB_JL_KJSSWSZM t ");
				cpzbSQL.append("where t.pzzldm =? ");
				cpzbSQL.append("and t.wszh=? ");
				cpzbSQL.append("and t.ndzb=? ");
				cpzbSQL.append("and t.pzlydm=? ");
				cpzbSQL.append("and t.zhdm=? ");
				
				PreparedStatement ps = conn.prepareStatement(cpzbSQL.toString());
				int index =1;
				ps.setString(index++, StringUtil.randomString(10));
				ps.setString(index++, (String)wszmap.get("wszh"));
				ps.setString(index++, (String)wszmap.get("ndzb"));
				ps.setString(index++, ud.getSsdwdm());
				ps.setString(index++, ud.getYhid());
				ps.setString(index++, ud.getYhid());
				ps.setString(index++, pzzldm);
				ps.setString(index++, wszh);
				ps.setString(index++, ndzb);
				ps.setString(index++, Constant.CONS_SGLR_PZLYDM_08);
				ps.setString(index++, ud.getXtsbm1());//账户代码
				
				int count = ps.executeUpdate();
				
				//复制明细表信息
				if(count == 1){
					StringBuffer mxSqlBuf = new StringBuffer();
					mxSqlBuf.append("insert into SBDB.SB_JL_KJSSWSZMMX (PZZLDM,WSZH,NDZB,XH,YPZH,SZDM,SZSMDM,SWJGZZJGDM,KJSWJGZZJGDM,SKSSKSRQ,SKSSJSRQ,RTKRQ,SJTJE,CJR,CJRQ,LRR,LRRQ) ");
					mxSqlBuf.append("select PZZLDM,?,?,XH,YPZH,SZDM,SZSMDM,SWJGZZJGDM,?,SKSSKSRQ,SKSSJSRQ,RTKRQ,SJTJE,?, sysdate CJRQ,?,sysdate LRRQ ");
					mxSqlBuf.append("from SBDB.SB_JL_KJSSWSZMMX t ");
					mxSqlBuf.append("where t.pzzldm =? ");
					mxSqlBuf.append("and t.wszh=? ");
					mxSqlBuf.append("and t.ndzb=? ");
					
					ps = conn.prepareStatement(mxSqlBuf.toString());
					index =1;
					ps.setString(index++, (String)wszmap.get("wszh"));
					ps.setString(index++, (String)wszmap.get("ndzb"));
					ps.setString(index++, ud.getSsdwdm());
					ps.setString(index++, ud.getYhid());
					ps.setString(index++, ud.getYhid());
					ps.setString(index++, pzzldm);
					ps.setString(index++, wszh);
					ps.setString(index++, ndzb);
					
					count = ps.executeUpdate();
					
					//作废主表信息、和原票号
					if(count < 0){
						throw new ApplicationException("重打操作失败，保存重打明细信息出错！");
					}
				}else{
					throw new ApplicationException("重打操作失败，保存重打信息出错！");
				}
			}else{
				throw new ApplicationException("重打操作失败，未获得询条件！");
			}
			//为查询做准备
			data.setWszmKey(newWszKey);
		}catch(Exception e){
			e.printStackTrace();
			//作废票号
			if(phIsZUOFEI(conn,wszmap) && wszmap != null){
				try{
					this.canclePH(ud, wszmap, "0.00");
				}catch(Exception e1){
					e1.printStackTrace();
					this.canclePH(ud, wszmap, "0.00");
				}
			}
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			
			throw new ApplicationException("取号操作失败！");
		}
		
		vo.setData(data);
		return this.doGetOne(vo);
	}
	
	
	/**
	 * 查询记录
	 * @param vo
	 * @return
	 */
	private Object doGetAll(VOPackage vo) throws BaseException{
		UserData ud = vo.getUserData();
		//获得前台提交的数据
		SgsswszmlrForm data = (SgsswszmlrForm) vo.getData();
		
		try{
			data.setQuery_type(Constant.CONS_SGLR_QUERY_TYPE_0);
			ArrayList allDataList = this.doQuery(ud, data);
			
			if(allDataList.size() > 0){
				System.out.println("doGetAll size +++++++++++++++++++++++++++++++++++++++++"+allDataList.size());
				// 获取总页数数据
				data.setTotalpage(this.getPageTotalCount(allDataList.size()));
				// 分页当前显示的数据集
				ArrayList showDataList = new ArrayList();
				// 整理查询结果
				showDataList = this.getPageDataList(allDataList, data);
				
				data.setQueryList_onePage(showDataList);
				
				
			}else{
				throw new ApplicationException("无对应查询结果，请确认查询条件后重试！");
			}
			}catch(Exception e){
				e.printStackTrace();
				
				//如果是特定抛出的异常
				if(e instanceof BaseException){
					throw new ApplicationException(e.getMessage());
				}
				throw new ApplicationException("查询失败，请退出后重试！");
			}
		return data;
	}
	
	/**
	 * 获得特定一条手工录入信息
	 * @return
	 */
	private Object doGetOne(VOPackage vo)throws BaseException{
		UserData ud = vo.getUserData();
		ArrayList resList = new ArrayList();
		//获得前台提交的数据
		SgsswszmlrForm data = (SgsswszmlrForm) vo.getData();
		try {
			//分情况
			//1、显示单条手工录入明细  或  进行原号码重新打印
			
			//2、重打（需要作废原号码 、调用票证接口获取新号码）
		
			data.setQuery_type(Constant.CONS_SGLR_QUERY_TYPE_1);
			resList = this.doQuery(ud, data);
			if(resList.size() >0){
				SgsswszmVo resvo = (SgsswszmVo)resList.get(0);
				
				//for 打印预览
				data.setPrintVo(resvo);
				
				//for 修改  查看明细
				data.setWsxxAll(resvo.getReShowMx());
				data.setNsrsbh(resvo.getNSRSBH());
				data.setNsrmc(resvo.getNSRMC());
				data.setPzlxdm(resvo.getPZLXDM());
				data.setSjjeHJ(resvo.getHJJE());
				data.setBz(resvo.getBZ());
				data.setDycs(Integer.valueOf(resvo.getDYCS()).intValue());
				data.setDybz(resvo.getDYBZ());
				data.setCjwszmBYothers(resvo.getCjwszmBYothers());
				data.setYxbz(resvo.getYXBZ());
				
				//
				data.setHasSaved(Constant.CONS_SGLR_HASSAVED_Y);//该记录已经保存
			}else{
					throw new ApplicationException("无对应查询结果，请确认查询条件后重试！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("查询失败，请退出后重试！");
		}
		return data;
	}
	
	
	
	
	/**
	 * 执行查询
	 * @param vo
	 * @return
	 */
	private ArrayList doQuery(UserData ud,SgsswszmlrForm data) throws BaseException{
		  Connection conn =null;
		  PreparedStatement ps = null;
		  ArrayList resList = new ArrayList();
		try{
			 conn = SfDBResource.getConnection();
			//获得查询SQL 
				StringBuffer zbQuerySQL = new StringBuffer();//主表查询SQL
				
				//获得查询条件
				String  queryType = data.getQuery_type();
				//（1）、维护查询
				String  queryNsrmc = data.getQuery_nsrmc();
				String  queryNsrsbh = data.getQuery_nsrsbh();
				String  queryWspzh = data.getQuery_wspzh();
				//（2）、查询单条记录
				String[] keyArr = new String[2];
				String wszmKey = data.getWszmKey();
				
				//判断查询条件是否为空
				if(!isNotNullOrBlank(queryNsrmc)
						&&!isNotNullOrBlank(queryNsrsbh)
						&&!isNotNullOrBlank(queryWspzh)
						&&!isNotNullOrBlank(wszmKey))
				{
					throw new ApplicationException("查询出错，未录入查询条件，请录入后重试！");
				}
				
				//拼接查询SQL
				zbQuerySQL.append("select PZLYDM,SJM,PZZLDM,WSZH,NDZB,NSRSBH,NSRMC,SWJGZZJGDM,KJSWJGZZJGDM,TFRQ,trim(to_char(HJJE, '9999999999990.99')) HJJE,CJR,CJRQ,LRR,LRRQ,QXDM,YPZH,YPZZLDM,YWSZH,YNDZB,YWHM,DYBZ,DYCS,YXBZ,KJLYDM,BZ,PZLXDM,ZHDM from SBDB.SB_JL_KJSSWSZM where 1=1 ");
				//如果维护查询
				if(Constant.CONS_SGLR_QUERY_TYPE_0.equals(queryType))
				{
					if(isNotNullOrBlank(queryNsrmc)){
						zbQuerySQL.append(" and nsrmc=? ");
					}
					if(isNotNullOrBlank(queryNsrsbh)){
						zbQuerySQL.append(" and nsrsbh=? ");
					}
					if(isNotNullOrBlank(queryWspzh)){
						zbQuerySQL.append(" and wszh=? ");
					}
					
				}//如果查询单条信息
				else if(Constant.CONS_SGLR_QUERY_TYPE_1.equals(queryType))
				{
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*******************+++++++++++++++++++++++++++++++++++++++++++++++++"+wszmKey);
					
					keyArr = wszmKey.split(Constant.CONS_SGLR_WSZMKEY_SPLIT);
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*******************+++++++++++++++++++++++++++++++++++++++++++++++++"+keyArr.length);
					if(keyArr != null && keyArr.length == 4){
						zbQuerySQL.append(" and pzzldm=? and wszh=? and ndzb =?");
					}else{
						throw new ApplicationException("查询手工明细信息出错，请录入查询条件后重试！");
					}
				}else{
					throw new ApplicationException("查询出错，请录入查询条件后重试！");
				}
				zbQuerySQL.append(" and lrr =? and kjswjgzzjgdm =? and pzlydm=?  and zhdm =?  and ywszh is null order by lrrq desc");
				System.out.println("主表查询SQL#####################"+zbQuerySQL.toString());
			 
				System.out.println(isNotNullOrBlank(zbQuerySQL)+"（1）主表查询到数据###########################################################################################");
				if(isNotNullOrBlank(zbQuerySQL)){
					System.out.println("（1）主表查询到数据###########################################################################################");

					ps = conn.prepareStatement(zbQuerySQL.toString());
					int index =1;
					if(Constant.CONS_SGLR_QUERY_TYPE_0.equals(queryType)){
						if(isNotNullOrBlank(queryNsrmc)){
								ps.setString(index++, queryNsrmc);
						}
						if(isNotNullOrBlank(queryNsrsbh)){
								ps.setString(index++, queryNsrsbh);
						}
						if(isNotNullOrBlank(queryWspzh)){
								ps.setString(index++, queryWspzh);
						}
					}
					if(Constant.CONS_SGLR_QUERY_TYPE_1.equals(queryType) && isNotNullOrBlank(keyArr)){
							ps.setString(index++, keyArr[0]);
							ps.setString(index++, keyArr[1]);
							ps.setString(index++, keyArr[2]);
							
					}
						
					ps.setString(index++, ud.getYhid());
					ps.setString(index++, ud.getSsdwdm());
					ps.setString(index++, Constant.CONS_SGLR_PZLYDM_08);
					ps.setString(index++, ud.getXtsbm1());//账户代码
						
					ResultSet rs = ps.executeQuery();
					int indx =1;
					while(rs.next()){
						System.out.println("（2）主表查询到数据###########################################################################################");
						SgsswszmVo zbVo = new SgsswszmVo();
						zbVo.setIndexId(indx++);
						zbVo.setPZLYDM(rs.getString("PZLYDM"));//凭证来源代码
						zbVo.setSJM(rs.getString("SJM"));//随机码
						zbVo.setPZZLDM(rs.getString("PZZLDM"));//票证种类代码
						zbVo.setWSZH(rs.getString("WSZH"));//完税证明完税证号
						zbVo.setNDZB(rs.getString("NDZB"));//年度字别
						zbVo.setNSRSBH(rs.getString("NSRSBH"));//纳税人识别号
						zbVo.setNSRMC(rs.getString("NSRMC"));//纳税人名称
						zbVo.setSWJGZZJGDM(rs.getString("SWJGZZJGDM"));//税务机关组织机构代码
						zbVo.setKJSWJGZZJGDM(rs.getString("KJSWJGZZJGDM"));//开具税务机关组织机构代码
						zbVo.setSWJGMC(ud.getSsdwmc());
						zbVo.setTFRQ(DateTimeUtil.timestampToString(rs.getTimestamp("TFRQ"),DateTimeUtil.JAVA_DATEFORMAT));//填发日期
						zbVo.setHJJE(rs.getString("HJJE"));//合计金额
						zbVo.setHJJEDX(Currency.convert(Double.parseDouble(rs.getString("HJJE"))));//合计金额大写
						zbVo.setCJR(rs.getString("CJR"));//创建人
						zbVo.setCJRQ(rs.getString("CJRQ"));//创建日期
						zbVo.setLRR(rs.getString("LRR"));//录入人
						zbVo.setLRRQ(rs.getString("LRRQ"));//录入日期
						zbVo.setQXDM(rs.getString("QXDM"));//区县代码
						zbVo.setYPZH(rs.getString("YPZH"));//原凭证号
						zbVo.setYPZZLDM(rs.getString("YPZZLDM"));//原票证种类代码
						zbVo.setYWSZH(rs.getString("YWSZH"));//原完税证明完税证号
						zbVo.setYNDZB(rs.getString("YNDZB"));//原年度字别
						zbVo.setYWHM(rs.getString("YWHM"));//业务号码(投保确认码||车辆编号 交易流水号)
						zbVo.setDYBZ(rs.getString("DYBZ"));//打印标志
						zbVo.setDYCS(rs.getString("DYCS"));//打印次数
/*						if(zbVo.getDYCS() != null && !"".equals(zbVo.getDYCS())){
							zbVo.setCURRENT_DYCS((Integer.valueOf(zbVo.getDYCS()).intValue() + 1)+"");
						}*/
						zbVo.setCURRENT_DYCS("1");//手工规定，打印次数永远为第一次
						zbVo.setYXBZ(rs.getString("YXBZ"));//有效标志
						zbVo.setKJLYDM(rs.getString("KJLYDM"));//开具来源代码(0税收完税证明管理 1申报换开 2保单换开)
						zbVo.setBZ(rs.getString("BZ"));//备注
						zbVo.setPZLXDM(rs.getString("PZLXDM"));//凭证类型代码(0缴税凭证  1退税凭证)
						zbVo.setCjwszmBYothers(hasCJWSZMByOthers(conn, zbVo.getNDZB(), zbVo.getPZZLDM(), zbVo.getWSZH()));//是否已经被出具过完税证明
						
						System.out.println("（3）主表查询到数据###########################################################################################");

							//获取明细信息
						if(Constant.CONS_SGLR_QUERY_TYPE_1.equals(queryType)){
							System.out.println("（1）查询明细数据###########################################################################################");

								getMxList(conn, zbVo);
							}
						resList.add(zbVo);
						}
					}else{
						throw new ApplicationException("查询失败，请退出后重试！");
					}
		}catch(Exception e){
			e.printStackTrace();
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("查询失败，请退出后重试！");
		}finally{
			  SfDBResource.freeConnection(conn);
		  }
		return resList;
	}

	/*
	 * 获取明细信息
	 */
	private ArrayList getMxList(Connection conn,SgsswszmVo zbVo) throws BaseException {
		ArrayList rsList = new ArrayList();
		try{
			StringBuffer mxQuerySQL = new StringBuffer();
			mxQuerySQL.append("select pzzldm,wszh,ndzb,xh,ypzh,szdm,szsmdm,swjgzzjgdm,kjswjgzzjgdm,to_char(skssksrq,'yyyymmdd') skssksrq,to_char(skssjsrq,'yyyymmdd') skssjsrq,to_char(rtkrq,'yyyymmdd') rtkrq,trim(to_char(sjtje, '9999999999990.99')) sjtje,cjr,  cjrq,lrr,lrrq from SBDB.SB_JL_KJSSWSZMMX where pzzldm=? and wszh=? and ndzb =? and lrr =? and kjswjgzzjgdm =? order by xh");
			System.out.println("明细表查询SQL#####################"+mxQuerySQL.toString());
			System.out.println("select * from SBDB.SB_JL_KJSSWSZMMX where pzzldm="+zbVo.getPZZLDM()+" and wszh="+zbVo.getWSZH()+" and ndzb ="+zbVo.getNDZB()+" and lrr ="+zbVo.getLRR()+" and kjswjgzzjgdm ="+zbVo.getKJSWJGZZJGDM());
			
			PreparedStatement ps = conn.prepareStatement(mxQuerySQL.toString());
			int index =1;
			ps.setString(index++, zbVo.getPZZLDM());
			ps.setString(index++, zbVo.getWSZH());
			ps.setString(index++, zbVo.getNDZB());
			ps.setString(index++, zbVo.getLRR());
			ps.setString(index++, zbVo.getKJSWJGZZJGDM());
			ResultSet mxrs = ps.executeQuery();
			this.getMx2ReShow(zbVo, mxrs);
			mxrs = ps.executeQuery();
			this.getMx2Print(zbVo, mxrs);
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("查询出错，获得完税信息明细出错，请重试！");
		}
		
		return rsList;
	}
	
	/**
	 * 获得打印明细信息
	 * @param zbVo
	 * @param mxrs
	 * @throws BaseException 
	 */
	private void getMx2Print(SgsswszmVo zbVo,ResultSet mxrs) throws BaseException{
		SgsswszmMXVo printMxVo = new SgsswszmMXVo();//单条打印信息
		
		StringBuffer ypzhBF = new StringBuffer();//原凭证号
		StringBuffer szmcBF = new StringBuffer();//税种
		StringBuffer pmmcBF = new StringBuffer();//品目名称
		StringBuffer skssqBF = new StringBuffer();//税款所属时期
		StringBuffer rtkrqBF = new StringBuffer();//入（退）库日期
		StringBuffer sjjeBF = new StringBuffer();//实缴（退）金额
		try{
			while(mxrs.next()){
				ypzhBF.append(mxrs.getString("ypzh")+";;");
				szmcBF.append(CodeUtils.getCodeBeanLabel("DM_SZSM", mxrs.getString("szdm"))+";;");
				pmmcBF.append(CodeUtils.getCodeBeanLabel("DM_SZSM", mxrs.getString("szsmdm"))+";;");
				skssqBF.append(mxrs.getString("skssksrq")+" 至 "+mxrs.getString("skssjsrq")+";;");
				rtkrqBF.append(mxrs.getString("rtkrq")+";;");
				sjjeBF.append(mxrs.getString("sjtje")+";;");
			}
			//赋值给打印明细VO
			printMxVo.setOld_pzh(ypzhBF.toString());
			printMxVo.setSelect_szmc(szmcBF.toString());
			printMxVo.setSelect_zssmmc(pmmcBF.toString());
			printMxVo.setSkssrq(skssqBF.toString());
			printMxVo.setRk_tkrq(rtkrqBF.toString());
			printMxVo.setSjje(sjjeBF.toString());
			
			zbVo.setPrintMx(printMxVo);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("查询出错，获得完税信息明细出错，请重试！");
		}
	}
	
	/**
	 * 获得明细信息（回显  修改 用）
	 * @param zbVo
	 * @param mxrs
	 */
	private void getMx2ReShow(SgsswszmVo zbVo,ResultSet mxrs)throws BaseException{
		StringBuffer reShowBF = new StringBuffer();//回显明细（如保存成功回显 或者修改信息）
		try{
			while(mxrs.next()){
				reShowBF.append(mxrs.getString("ypzh"));//原凭证号
				reShowBF.append("~");
				reShowBF.append(mxrs.getString("szsmdm")+"-"+CodeUtils.getCodeBeanLabel("DM_SZSM", mxrs.getString("szsmdm")));//品目名称
				reShowBF.append("~");
				reShowBF.append(mxrs.getString("skssksrq"));//税款所属时期开始
				reShowBF.append("~");
				reShowBF.append(mxrs.getString("skssjsrq"));//税款所属时期结束
				reShowBF.append("~");
				reShowBF.append(mxrs.getString("rtkrq"));//入（退）库日期
				reShowBF.append("~");
				reShowBF.append(mxrs.getString("sjtje"));//实缴（退）金额
				reShowBF.append("^");
			}
			System.out.println("打印信息(1)###############"+reShowBF.toString());
			if(reShowBF.length() >0){
				reShowBF = reShowBF.replace(reShowBF.length()-1, reShowBF.length(), "");
			}
			System.out.println("打印信息(2)###############"+reShowBF.toString());
			zbVo.setReShowMx(reShowBF.toString());
			
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("查询出错，获得完税信息明细出错，请重试！");
		}
	}
	
	
	/**
	 * 判断当前票号是否已经打印
	 * @param conn
	 * @param ndzb
	 * @param pzzldm
	 * @param wszh
	 * @return
	 */
	private boolean hasPrinted(Connection conn,String ndzb,String pzzldm,String wszh)throws BaseException{
		boolean succ = false;//操作是否成功
		try{
			if( isNotNullOrBlank(conn) && isNotNullOrBlank(ndzb) && isNotNullOrBlank(pzzldm) && isNotNullOrBlank(wszh)){
				String querySQL="select 1 from SBDB.SB_JL_KJSSWSZM where ndzb =? and pzzldm=? and wszh=? and dybz =? and pzlydm=?";
				PreparedStatement ps = conn.prepareStatement(querySQL);
				int index =1;
				ps.setString(index++, ndzb);
				ps.setString(index++, pzzldm);
				ps.setString(index++, wszh);
				ps.setString(index++, Constant.CONS_SGLR_DYBZ_1);
				ps.setString(index++, Constant.CONS_SGLR_PZLYDM_08);
				
				ResultSet rs = ps.executeQuery();
				
			if(rs.next()){
				succ = true;
			}
			}else{
				throw new ApplicationException("获取票号打印标志出错！");
			}
		}catch(Exception e){
			e.printStackTrace();
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("获取票号打印标志出错！");
		}
		return succ;
	}
	
	
	
	/**
	 * 更新打印标志(从未打印更新到已打印)
	 * @param ndzb
	 * @param pzzldm
	 * @param wszh
	 * @return
	 */
	private boolean updateDYBZ(Connection conn,UserData ud ,String ndzb,String pzzldm,String wszh)throws BaseException{
		boolean succ = false;//操作是否成功
		try{
			if( isNotNullOrBlank(conn) && isNotNullOrBlank(ndzb) && isNotNullOrBlank(pzzldm) && isNotNullOrBlank(wszh)){
				String updateZbSQL = "update SBDB.SB_JL_KJSSWSZM  set dybz =?,lrr=?,lrrq=sysdate where ndzb =? and pzzldm=? and wszh=? and dybz=? and pzlydm=? and zhdm=?";
				PreparedStatement ps = conn.prepareStatement(updateZbSQL);
				int index =1;
				ps.setString(index++, Constant.CONS_SGLR_DYBZ_1);
				ps.setString(index++, ud.getYhid());
				ps.setString(index++, ndzb);
				ps.setString(index++, pzzldm);
				ps.setString(index++, wszh);
				ps.setString(index++, Constant.CONS_SGLR_DYBZ_0);
				ps.setString(index++, Constant.CONS_SGLR_PZLYDM_08);
				ps.setString(index++, ud.getXtsbm1());//账户代码
				
				succ = ps.execute();
			}else{
				throw new ApplicationException("更新打印标志出错！");
			}
		}catch(Exception e){
			e.printStackTrace();
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("更新打印标志出错！");
		}
		return succ;
	}
	
	/**
	 * 更新已打印次数 (规定已打印次数永远为1次)
	 * @param conn
	 * @param ndzb
	 * @param pzzldm
	 * @param wszh
	 * @return
	 */
	private boolean updateDYCS(Connection conn,UserData ud,String ndzb,String pzzldm,String wszh)throws BaseException{
		boolean succ = false;//操作是否成功
		
		try{
			if(isNotNullOrBlank(conn) && isNotNullOrBlank(ndzb) && isNotNullOrBlank(pzzldm) && isNotNullOrBlank(wszh)){
				
			String updateZbSQL = "update SBDB.SB_JL_KJSSWSZM  set dycs =1,lrr=?,lrrq=sysdate where ndzb =? and pzzldm=? and wszh=? and dybz=? and pzlydm=? and zhdm=?";
			PreparedStatement ps = conn.prepareStatement(updateZbSQL);
			int index =1;
			ps.setString(index++, ud.getYhid());
			ps.setString(index++, ndzb);
			ps.setString(index++, pzzldm);
			ps.setString(index++, wszh);
			ps.setString(index++, Constant.CONS_SGLR_DYBZ_1);
			ps.setString(index++, Constant.CONS_SGLR_PZLYDM_08);
			ps.setString(index++, ud.getXtsbm1());//账户代码
			
			succ = ps.execute();
			}else{
				throw new ApplicationException("更新打印次数出错！");
			}
		}catch(Exception e){
			e.printStackTrace();
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("更新打印次数出错！");
		}
		return succ;
	}
	
	/**
	 * 获得一个标志，标志一条记录是否已经被其他地方（比如查询出具完税证明）出具过完税证明
	 * @return
	 */
	private String hasCJWSZMByOthers(Connection conn,String ndzb,String pzzldm,String wszh)throws BaseException{
		String hasCJ = Constant.CONS_SGLR_CJWSZM_BY_OTHERS_N;//0--未出具
		try{
			if(isNotNullOrBlank(conn) && isNotNullOrBlank(ndzb) && isNotNullOrBlank(pzzldm) && isNotNullOrBlank(wszh)){
				String querySQL = "SELECT * FROM SBDB.SB_JL_KJSSWSZM t where t.ywszh=? and t.yndzb=? and t.ypzzldm=? and t.yxbz=?";
				PreparedStatement ps = conn.prepareStatement(querySQL);
				int index =1;
				ps.setString(index++, wszh);
				ps.setString(index++, ndzb);
				ps.setString(index++, pzzldm);
				ps.setString(index++, Constant.CONS_SGLR_YXBZ_0);//有效
				//ps.setString(index++, Constant.CONS_SGLR_PZLYDM_08);//（非）手工
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()){
					hasCJ =Constant.CONS_SGLR_CJWSZM_BY_OTHERS_Y;//  1-- 已出具
				}
			}else{
				throw new ApplicationException("获取完税证明出具标志出错！");
			}
		}catch(Exception e){
			e.printStackTrace();
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("获取完税证明出具标志出错，请退出后重试！");
			
		}
		return hasCJ;
	}
	
	/**
	 * 判断票号是否是已作废状态
	 * 1）、如果已经作废  或者票号在表中不存 返回 true  
	 * 2）、其他情况返回false
	 * @return
	 */
	private boolean phIsZUOFEI(Connection conn,HashMap wszmap)throws BaseException{
		boolean iszuofei = false;
		try{
			if(isNotNullOrBlank(wszmap)){
				String querySql="select 1 from  SBDB.SB_JL_KJSSWSZM t where t.pzzldm=? and t.wszh=? and t.ndzb=? and t.yxbz=? and t.pzlydm=?";
				PreparedStatement ps = conn.prepareStatement(querySql);
				int index =1;
				ps.setString(index++, Constant.CONS_SGLR_PZZLDM);
				ps.setString(index++, (String)wszmap.get("wszh"));
				ps.setString(index++, (String)wszmap.get("ndzb"));
				ps.setString(index++, Constant.CONS_SGLR_YXBZ_1);//作废
				ps.setString(index++, Constant.CONS_SGLR_PZLYDM_08);
				
				ResultSet rs = ps.executeQuery();
				
				//如果有结果且作废
				if(rs.next()){
					iszuofei = true;
				}
				
				querySql="select 1 from  SBDB.SB_JL_KJSSWSZM t where t.pzzldm=? and t.wszh=? and t.ndzb=? and t.pzlydm=?";
				ps = conn.prepareStatement(querySql);
				index =1;
				ps.setString(index++, Constant.CONS_SGLR_PZZLDM);
				ps.setString(index++, (String)wszmap.get("wszh"));
				ps.setString(index++, (String)wszmap.get("ndzb"));
				ps.setString(index++, Constant.CONS_SGLR_PZLYDM_08);
				
				rs = ps.executeQuery();
				
				//如果不存在该票
				if(rs.next()== false){
					iszuofei = true;
				}
				
			}else{
				throw new ApplicationException("获取票号状态出错,无法获取查询条件！");
			}
		}catch(Exception e){
			e.printStackTrace();
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("获取票号状态出错，请退出后重试！");
		}
		
		return iszuofei;
	}
	
	
	

	

	
	/**
	 * 调用票证接口，获得一张新票 
	 */
	private  HashMap getNewPH(UserData ud,String hjje)throws BaseException{
		HashMap map = new HashMap();
		String ndzb ="";
		String wszh="";
        //获得完税证号
        try {
            String retResult = ServiceProxy.getNumber(ud, Constant.CONS_SGLR_PZZLDM,
                StringUtil.getDouble(hjje, 0), "1", "0");
            ndzb = retResult.substring(0, 4); //年度字别
            wszh = retResult.substring(4); //完税证号
            
            System.out.println("年度字别+++++++++++++++++"+ndzb);
            System.out.println("完税证号+++++++++++++++++"+wszh);
            
            map.put("ndzb", ndzb);
            map.put("wszh", wszh);
            
        } catch (Exception ex) {
            ex.printStackTrace();
			//如果是特定抛出的异常
			if(ex instanceof BaseException){
				throw new ApplicationException(ex.getMessage());
			}
            throw new ApplicationException("获取完税证失败！");
        }
        
        return map;
	}
	
	/**
	 * 调用票证接口、作废票 
	 */
	private void canclePH(UserData ud,HashMap wszmap,String hjje)throws BaseException{
        //3、调用票证接口，作废当前完税证号，并不再取号
		String ndzb = (String)wszmap.get("ndzb");
		String wszh = (String)wszmap.get("wszh");
		
		System.out.println("作废完税证号+++++++++++++++++"+wszh);
        System.out.println("作废年度字别+++++++++++++++++"+ndzb);
        
        try
        {
			if( ndzb != null && !"".equals(ndzb) 
				&& wszh != null && !"".equals(wszh)){
				
				String result = ServiceProxy.setCancellation(ud,
						Constant.CONS_SGLR_PZZLDM,
						ndzb+wszh,
						StringUtil.getDouble(hjje, 0), "1", "0", "0");
				
				if(result == null || "0".equals(result)){
					throw new ApplicationException("调用票证接口出错，无法作废票号！");
				}
			}
			
        }
        catch (Exception ex1)
        {
        	ex1.printStackTrace();
			//如果是特定抛出的异常
			if(ex1 instanceof BaseException){
				throw new ApplicationException(ex1.getMessage());
			}
            throw new ApplicationException("作废完税证号出错！");
        }
		
		
	}
	
	/**
	 * 判断是否有能打票
	 * @return
	 */
	private boolean havePH(UserData ud){
		boolean yes= false;
		//获取完税证明号
		 try 
		 {
			 HashMap retResult = ServiceProxy.readNumber(ud, CodeConstant.SMSB_SWWSZM_PZZLDM);
			 yes = true;
		 }
		 catch (Exception ex) 
		 {
			 ex.printStackTrace();
		 }
		 
		 return yes;
	}
	
	public BigDecimal string2BigDecimal(String StrJe) {
		if (StrJe == null || "".equals(StrJe)) {
			StrJe = "0.00";
		}
		// DecimalFormat deFormat = new DecimalFormat("#0.00");// 合计金额格式
		BigDecimal zje = new BigDecimal(StrJe);// 获得增加前的合计金额信息
		return zje;
	}
	
	
	private  boolean isNotNullOrBlank(Object obj){
		boolean isNotNull = true;
		
		if(obj instanceof String){
			String tempStr = (String)obj;
			if(tempStr == null || "".equals(tempStr)){
				isNotNull = false;
			}
		}else{
			if(obj == null){
				isNotNull = false;
			}
		}
		return isNotNull;
	}
	
	
	/**
	 * 获取页数
	 * 
	 * @param rsCount
	 *            查询结果集build
	 * @return 页数
	 */
	private String getPageTotalCount(int rsCount) {
		// 句柄申明
		String countTotal = "0";
		// 开始业务
		int pageCount = 0;
		if ((rsCount % CodeConstant.SD_PG_LENGTH) == 0) {
			pageCount = (rsCount / CodeConstant.SD_PG_LENGTH);
		} else {
			pageCount = (rsCount / CodeConstant.SD_PG_LENGTH) + 1;
		}
		countTotal = String.valueOf(pageCount);
		// 返回值
		return countTotal;
	}
	
	/**
	 * 获取当前分页数据集
	 * 
	 * @param tmpList
	 *            完整数据集
	 * @param pf
	 *            页面对象
	 * @return 当前分页数据集
	 */
	private ArrayList getPageDataList(List tmpList, SgsswszmlrForm pf) {
		// 1.申明句柄
		ArrayList dataList = new ArrayList();
		// 2.初始化参数值
		int startIndex = this.getPageStartIndex(pf.getNextPage(), pf
				.getTotalpage());
		int endIndex = this
				.getPageEndIndex(pf.getNextPage(), pf.getTotalpage());
		// 3.开始业务
		for (int i = startIndex; i < endIndex; i++) {
			if (i < tmpList.size()) {
				dataList.add(tmpList.get(i));
			}
		}
		tmpList = null;
		// 99.返回值
		return dataList;
	}
	
	/**
	 * 获取当前页开始index
	 * 
	 * @param nextPage
	 *            下一页
	 * @param countTotal
	 *            总页数
	 * @return 开始index
	 */
	private int getPageStartIndex(String nextPage, String countTotal) {
		// 1.句柄申明
		int iNextPage = Integer.parseInt(nextPage);
		int iCountTotal = Integer.parseInt(countTotal);
		int start = -1;
		// 2.开始业务
		start = (iNextPage - 1) * CodeConstant.SD_PG_LENGTH;
		// 99.返回值
		return start;
	}
	
	/**
	 * 获取当前页结束index
	 * 
	 * @param nextPage
	 *            下一页
	 * @param countTotal
	 *            总页数
	 * @return 结束index
	 */
	private int getPageEndIndex(String nextPage, String countTotal) {
		// 1.句柄申明
		int iNextPage = Integer.parseInt(nextPage);
		int iCountTotal = Integer.parseInt(countTotal);
		int end = -1;
		// 2.开始业务
		end = iNextPage * CodeConstant.SD_PG_LENGTH;
		// 99.返回值
		return end;
	}
}
