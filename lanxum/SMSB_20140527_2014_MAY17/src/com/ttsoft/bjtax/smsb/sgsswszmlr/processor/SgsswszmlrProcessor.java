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
		    	  //��ʼ����ʾ
		        result = doInit(vo);
		        break;
		      case CodeConstant.SMSB_SAVEACTION:
		    	  //�����ֹ�¼����Ϣ
		        result = SaveNewAdd(vo);
		        break;
		      case CodeConstant.SMSB_UPDATEACTION: 
		    	  //�޸�
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
	 * ҳ���ʼ��
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
			  //�ж��Ƿ��ܻ�ȡ��Ʊ��  // deleted by tangchangfu ��Ʊ������ά�� ɾ����ʼ��ʱƱ֤�˻��Լ��Ƿ���Ʊ���ж� 2014-03-12
/*			  if(!this.havePH(ud)){
				  throw new ApplicationException("ҳ���ʼ��ʧ�ܣ��޷���ȡ˰����˰֤����������Ʊ֤�˻�����   ��  �˻����޸���Ʊ��  ��  �˻��и���Ʊ�Ѿ����꣬��ȷ�ϣ�");
			  }*/
		  }catch(Exception e){
			  e.printStackTrace();
				//������ض��׳����쳣
				if(e instanceof BaseException){
					throw new ApplicationException(e.getMessage());
				}
				
				throw new ApplicationException("ҳ���ʼ��ʧ�ܣ����˳����ԣ�");
		  }finally{
			  SfDBResource.freeConnection(conn);
		  }
		return res;
	}
	
	/**
	 * ����������Ϣ
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private SgsswszmlrForm SaveNewAdd(VOPackage vo) throws BaseException{
		Connection conn = null;
		UserData ud = vo.getUserData();
		//���ǰ̨�ύ������
		SgsswszmlrForm data = (SgsswszmlrForm) vo.getData();
		HashMap wszmap = null;
		
		try{
			// ��ȡ��������
			conn = SfDBResource.getConnection();
			String allNewAddInfo = data.getWsxxAll();
			
			if(allNewAddInfo == null || "".equals(allNewAddInfo)){
				throw new ApplicationException("�����ֹ�¼����Ϣ�����޷�����ύ���ݣ������ԣ����ߺ͹���Ա��ϵ��");
			}
			
			//�����ύ��Ϣ
			ArrayList SaveInfoList = constructMx2SaveOrUpdate(ud, allNewAddInfo);
			//��ȡ��˰֤
			  //�ж��Ƿ��ܻ�ȡ��Ʊ��
			  if(!this.havePH(ud)){
				  throw new ApplicationException("�޷���ȡ˰����˰֤����������Ʊ֤�˻�����   ��  �˻����޸���Ʊ��  ��  �˻��и���Ʊ�Ѿ����꣬��ȷ�ϣ�");
			  }
			wszmap = getNewPH( ud,data.getSjjeHJ());
			//ִ�б���
			//(1)��������
			save2zb(conn, wszmap, ud, data);
			//(2)������ϸ
			save2Mx(conn, wszmap, ud, SaveInfoList);
			
			//���Ա�����Ϣ
/*			String wszmKey = Constant.CONS_SGLR_PZZLDM+"-"+(String)wszmap.get("wszh")+"-"+(String)wszmap.get("ndzb");
			data.setWszmKey(wszmKey);*/
			data.setQuery_nsrmc(data.getNsrmc());
			data.setQuery_nsrsbh(data.getNsrsbh());
			data.setQuery_wspzh((String)wszmap.get("wszh"));
			//ִ�в�ѯ
			vo.setData(data);
			data = (SgsswszmlrForm)doGetAll(vo);
			data.setSaveIsSucc(Constant.CONS_SGLR_SAVEISSUCC_Y);//����ɹ�
		}catch(Exception e){
			e.printStackTrace();
			//����Ʊ��
			if(phIsZUOFEI(conn,wszmap) && wszmap != null){
				try{
					this.canclePH(ud, wszmap, "0.00");
				}catch(Exception e1){
					e1.printStackTrace();
					this.canclePH(ud, wszmap, "0.00");
				}
			}
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("������Ϣʧ�ܣ��˳����ԣ�");
		}finally {
			SfDBResource.freeConnection(conn);
	       }
		return data;
	}
	private ArrayList constructMx2SaveOrUpdate(UserData ud, String allNewAddInfo)throws BaseException {
		ArrayList SaveInfoList = new ArrayList();
		try{
		String regEx ="[\\^]";//���������^�ֿ�
		String[] splitAllNewAddInfo = allNewAddInfo.split(regEx);

		for (int index = 0; index < splitAllNewAddInfo.length; index++) {

			String[] oneRowInfoArr = new String[] {};
			if (splitAllNewAddInfo[index] != null) {
				System.out.println("ÿ���ֹ�¼����Ϣ################################"+splitAllNewAddInfo[index]);

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
					//����Ƿ��Ѿ�����
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("��ȡ������ϸ��Ϣʧ�ܣ������ԣ�");
		}
		return SaveInfoList;
	}
	
	/**
	 * ������Ϣ���ֹ�����
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
			ps.setString(index++, data.getPzlxdm());//¼��ƾ֤����  0 -- ��˰ƾ֤    1 -- ��˰ƾ֤
			ps.setString(index++, data.getPzlydm());//ƾ֤��Դ���� Ĭ��Ϊ8--�ֹ���˰֤��
			ps.setString(index++, StringUtil.randomString(10));//�����
			ps.setString(index++, Constant.CONS_SGLR_PZZLDM);//Ʊ֤�������
			ps.setString(index++, (String)wszmap.get("wszh"));//��˰֤����˰֤��
			ps.setString(index++, (String)wszmap.get("ndzb"));//����ֱ�
			ps.setString(index++, data.getNsrsbh());
			ps.setString(index++, data.getNsrmc());
			ps.setString(index++, ud.getSsdwdm());//����˰�������֯��������
			ps.setBigDecimal(index++, string2BigDecimal(data.getSjjeHJ()));//�ϼƽ��
			ps.setString(index++, ud.getYhid());//������
			ps.setString(index++, ud.getYhid());//¼����
			ps.setString(index++, ud.getSsdwdm().substring(0, 2));//���ش���
			ps.setString(index++, data.getDybz());//��ӡ��־
			ps.setInt(index++, data.getDycs());//��ӡ����
			ps.setString(index++, data.getYxbz());//��Ч��־
			ps.setString(index++, data.getKjlydm());//������Դ����
			ps.setString(index++, data.getBz());//��ע
			ps.setString(index++, ud.getXtsbm1());//�˻�����
			
			
			savSucc= ps.execute();
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("�����ֹ�¼����Ϣ������ʧ�ܣ�");
		}
		
		return savSucc;
	}
	
	/**
	 * ������ϸ��Ϣ
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
				ps.setString(tempIndex++, Constant.CONS_SGLR_PZZLDM);//Ʊ֤�������
				ps.setString(tempIndex++,(String)wszmap.get("wszh"));//��˰֤����˰֤��
				ps.setString(tempIndex++,(String)wszmap.get("ndzb"));//����ֱ�
				
				ps.setInt(tempIndex++,index+1);//���
				ps.setString(tempIndex++,mxVo.getOld_pzh());//�ֹ�¼��ƾ֤��
				ps.setString(tempIndex++, mxVo.getSelect_szdm());//˰�ִ���
				ps.setString(tempIndex++, mxVo.getSelect_zssmdm());//˰��˰Ŀ����
				ps.setString(tempIndex++, ud.getSsdwdm());//����˰�������֯��������
				ps.setTimestamp(tempIndex++, DateTimeUtil.stringToTimestamp(mxVo.getSkssksrq(),DateTimeUtil.JAVA_DATEFORMAT));//˰��������ʼ����
				ps.setTimestamp(tempIndex++, DateTimeUtil.stringToTimestamp(mxVo.getSkssjsrq(),DateTimeUtil.JAVA_DATEFORMAT));//˰��������������
				ps.setTimestamp(tempIndex++, DateTimeUtil.stringToTimestamp(mxVo.getRk_tkrq(),DateTimeUtil.JAVA_DATEFORMAT));//��(��)������
				ps.setBigDecimal(tempIndex++,string2BigDecimal(mxVo.getSjje()));//ʵ��(��)���
				ps.setString(tempIndex++, ud.getYhid());//������
				ps.setString(tempIndex++, ud.getYhid());//¼����
				ps.setString(tempIndex++, ud.getSsdwdm().substring(0, 2));//���ش���
				
				ps.addBatch();
			}
			
			 ps.executeBatch();
			
			 savSucc = true;
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("�����ֹ�¼����Ϣ��ϸʧ�ܣ�");
		}
		return savSucc;
	} 
	
	/**
	 * ���ض�ĳ��¼����Ϣ�����޸�
	 * ʵ�ֲ��裺��1�����޸�������Ϣ  ��2�����ȸ�����������ɾ����ϸ����Ϣ Ȼ�󱣴��޸�֮�����ϸ
	 * 
	 * @return
	 */
	private Object doUpdate(VOPackage vo)throws BaseException{
		Connection conn = null;
		//���ǰ̨�ύ������
		UserData ud = vo.getUserData();
		SgsswszmlrForm data = (SgsswszmlrForm) vo.getData();
		try{
			// ��ȡ��������
			conn = SfDBResource.getConnection();
			
			String wszmKey = data.getWszmKey();
			if(wszmKey != null && !"".equals(wszmKey)){
				String[] keyArr = wszmKey.split(Constant.CONS_SGLR_WSZMKEY_SPLIT);
				if(keyArr != null && keyArr.length == 4){
					//���������Ϣ
					String pzzldm = keyArr[0];
					String wszh = keyArr[1];
					String ndzb = keyArr[2];
					
					//�Ѿ���ӡ�����޸�
					if(!this.hasPrinted(conn, ndzb, pzzldm, wszh)){
						//��˰֤��δ���߹���˰֤��
						if(hasCJWSZMByOthers(conn, ndzb, pzzldm, wszh)  == Constant.CONS_SGLR_CJWSZM_BY_OTHERS_N){
							//��������
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
							ps.setString(index++, data.getPzlxdm());//¼��ƾ֤����  0 -- ��˰ƾ֤    1 -- ��˰ƾ֤
							ps.setString(index++, data.getNsrsbh());
							ps.setString(index++, data.getNsrmc());
							ps.setBigDecimal(index++, string2BigDecimal(data.getSjjeHJ()));//�ϼƽ��
							ps.setString(index++, ud.getYhid());//¼����
							ps.setString(index++, ud.getSsdwdm().substring(ud.getSsdwdm().length()-2, ud.getSsdwdm().length()));//���ش���
							ps.setString(index++, data.getBz());//��ע
							
							ps.setString(index++, pzzldm);
							ps.setString(index++, wszh);
							ps.setString(index++, ndzb);
							ps.setString(index++, Constant.CONS_SGLR_PZLYDM_08);
							ps.setString(index++, ud.getXtsbm1());//�˻�����
							
							int count = ps.executeUpdate();
							
							//ɾ����ϸ�����Ϣ
							if(count == 1){
								StringBuffer mxSQL = new StringBuffer();
								mxSQL.append("delete from  SBDB.SB_JL_KJSSWSZMMX t where t.pzzldm=? and t.ndzb=? and t.wszh=?");
								
								ps = conn.prepareStatement(mxSQL.toString());
								index =1;
								ps.setString(index++, pzzldm);
								ps.setString(index++, ndzb);
								ps.setString(index++, wszh);
								
								count = ps.executeUpdate();
								//���²�����ϸ��Ϣ
								if(count >0){
									String allNewAddInfo = data.getWsxxAll();
									
									if(allNewAddInfo == null || "".equals(allNewAddInfo)){
										throw new ApplicationException("�����ֹ�¼����Ϣ�����޷�����ύ���ݣ������ԣ����ߺ͹���Ա��ϵ��");
									}
									
									//�����ύ��Ϣ
									ArrayList SaveInfoList = constructMx2SaveOrUpdate(ud, allNewAddInfo);
									
									HashMap wszmapOld = new HashMap();
									wszmapOld.put("wszh", wszh);
									wszmapOld.put("ndzb", ndzb);
									
									save2Mx(conn, wszmapOld, ud, SaveInfoList);
									
									//Ϊ��ѯ��������
									data.setQuery_nsrmc(data.getNsrmc());
									data.setQuery_nsrsbh(data.getNsrsbh());
									data.setQuery_wspzh(wszh);
									data.setSaveIsSucc(Constant.CONS_SGLR_SAVEISSUCC_Y);//����ɹ�
								}else{
									throw new ApplicationException("�޸Ĳ���ʧ�ܣ��޷�������ϸ��Ϣ��");
								}
							}else{
								throw new ApplicationException("�޸Ĳ���ʧ�ܣ��޷����»�����Ϣ��");
							}
						}else{
							throw new ApplicationException("��֤���Ѿ�������ģ��ʹ�ò���������Ч��˰֤�������ܽ����޸ģ���ȷ�ϣ�");
						}
					}else{
						throw new ApplicationException("�޸Ĳ���ʧ�ܣ�����˰֤���Ѿ���ӡ�����޸ģ�");
					}
				}else{
					throw new ApplicationException("�޸Ĳ���ʧ�ܣ���ȡ���²�ѯ����ʧ�ܣ�");
				}
			}else{
				throw new ApplicationException("�޸Ĳ���ʧ�ܣ��޸��²�ѯ������");
			}
		}catch(Exception e){
			e.printStackTrace();
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("�޸Ĳ���ʧ�ܣ������ԣ�");
		}
		
		//���ؽ��
		vo.setData(data);
		return this.doGetAll(vo);
	}
	
	
	
	/**
	 * ������Ϣ������ǰ���¼�������ϱ�� ������Ʊ֤�ӿ�����Ʊ��
	 * @return
	 */
	private Object doCancel(VOPackage vo)throws BaseException{
		
		Connection conn = null;
		//���ǰ̨�ύ������
		UserData ud = vo.getUserData();
		SgsswszmlrForm data = (SgsswszmlrForm) vo.getData();
		try{
			String wszmKey = data.getWszmKey();
			String[] keyArr = wszmKey.split(Constant.CONS_SGLR_WSZMKEY_SPLIT);
			if(keyArr != null && keyArr.length == 4){
				//���������Ϣ
				String pzzldm = keyArr[0];
				String wszh = keyArr[1];
				String ndzb = keyArr[2];
				// ��ȡ��������
				conn = SfDBResource.getConnection();
				
				//��˰֤��δ���߹���˰֤��
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
				ps.setString(index++, ud.getXtsbm1());//�˻�����
				int count = ps.executeUpdate();
				//���ϳɹ�
				if(count == 1){
					//����Ʊ֤ģ����Ʊ����Ϣ
					HashMap wszmap = new HashMap();
					wszmap.put("wszh", wszh);
					wszmap.put("ndzb", ndzb);
					
					this.canclePH(ud, wszmap, "0");//���Ͻ��0
				}else{
					throw new ApplicationException("���ϲ���ʧ�ܣ�δ�ҵ���Ӧ���������ݣ�");
				}
				
			}else{
				throw new ApplicationException("��֤���Ѿ�������ģ��ʹ�ò���������Ч��˰֤�������ܽ����޸ģ���ȷ�ϣ�");
			}
			}else{
				throw new ApplicationException("���ϲ���ʧ�ܣ������ϲ�ѯ������");
			}
		}catch(Exception e){
			e.printStackTrace();
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("���ϲ���ʧ�ܣ�");
		}finally {
			SfDBResource.freeConnection(conn);
	       }
		//���ز�ѯ��¼
		data.setQuery_type(Constant.CONS_SGLR_QUERY_TYPE_0);
		vo.setData(data);
		
		return this.doGetAll(vo);
	}
	
	
	/**
	 * ��ӡ�ɹ�����Ҫ 1.���ô�ӡ��־  2.���ô�ӡ����+1��
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doPrintSuccess(VOPackage vo) throws BaseException{
		UserData ud = vo.getUserData();
		//���ǰ̨�ύ������
		SgsswszmlrForm data = (SgsswszmlrForm) vo.getData();
		
		Connection conn =null;
		try{
			 conn = SfDBResource.getConnection();
			 
			 if(isNotNullOrBlank(conn)){
				 
				 
					String wszmKey = data.getWszmKey();
					if(wszmKey != null && !"".equals(wszmKey)){
						String[] keyArr = wszmKey.split(Constant.CONS_SGLR_WSZMKEY_SPLIT);
						if(keyArr != null && keyArr.length == 4){
							//���������Ϣ
							String pzzldm = keyArr[0];
							String wszh = keyArr[1];
							String ndzb = keyArr[2];
							//����Ϊ�Ѵ�ӡ
							this.updateDYBZ(conn,ud, ndzb, pzzldm, wszh);
							
							//���ô�ӡ����
							this.updateDYCS(conn,ud, ndzb, pzzldm, wszh);
						}else{
							throw new ApplicationException("���ô�ӡ��־����,����������ȡ����");
						}
					}else{
						throw new ApplicationException("���ô�ӡ��־����,�޷���ȡ����������");
					}
			 }else{
				 throw new ApplicationException("���ô�ӡ��־����,�޷���ȡ���ݿ����ӣ�");
			 }
		}catch(Exception e){
			e.printStackTrace();
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("���ô�ӡ��־����");
			
		}
 		return null;
	}
	
	
	/**
	 * ȡ���ش�1.���һ����Ʊ ��2.����ԭƱ��3.����Ʊ�ű���һ����¼��
	 * @return
	 */
	private Object doPrintByNewWSZH(VOPackage vo)throws BaseException{
		Connection conn = null;
		//���ǰ̨�ύ������
		UserData ud = vo.getUserData();
		SgsswszmlrForm data = (SgsswszmlrForm) vo.getData();
		//Ϊ���ز�ѯ�����µĲ�ѯ����
		String newWszKey ="";
		HashMap wszmap = null;
		try{
			// ��ȡ��������
			conn = SfDBResource.getConnection();
			//��þ�Ʊ��Ϣ
			String wszmKey = data.getWszmKey();
			String[] keyArr = wszmKey.split(Constant.CONS_SGLR_WSZMKEY_SPLIT);
			
			//���Ͼ�Ʊ
			this.doCancel(vo);
			
			//�����Ʊ
			  //�ж��Ƿ��ܻ�ȡ��Ʊ��
			  if(!this.havePH(ud)){
				  throw new ApplicationException("�޷���ȡ˰����˰֤����������Ʊ֤�˻�����   ��  �˻����޸���Ʊ��  ��  �˻��и���Ʊ�Ѿ����꣬��ȷ�ϣ�");
			  }
			  if(keyArr != null && keyArr.length == 4){
				  wszmap = this.getNewPH(ud, keyArr[3]);
			  }else{
				  throw new ApplicationException("�޷���ȡ�ϼƽ���ȡ˰����˰֤��ʧ�ܣ����˳������ԣ�");
			  }
			newWszKey = Constant.CONS_SGLR_PZZLDM+"-"+(String)wszmap.get("wszh")+"-"+(String)wszmap.get("ndzb")+"-"+keyArr[3];
			

			if(keyArr != null && keyArr.length == 4){
				//���������Ϣ
				String pzzldm = keyArr[0];
				String wszh = keyArr[1];
				String ndzb = keyArr[2];
				
				//����������Ϣ
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
				ps.setString(index++, ud.getXtsbm1());//�˻�����
				
				int count = ps.executeUpdate();
				
				//������ϸ����Ϣ
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
					
					//����������Ϣ����ԭƱ��
					if(count < 0){
						throw new ApplicationException("�ش����ʧ�ܣ������ش���ϸ��Ϣ����");
					}
				}else{
					throw new ApplicationException("�ش����ʧ�ܣ������ش���Ϣ����");
				}
			}else{
				throw new ApplicationException("�ش����ʧ�ܣ�δ���ѯ������");
			}
			//Ϊ��ѯ��׼��
			data.setWszmKey(newWszKey);
		}catch(Exception e){
			e.printStackTrace();
			//����Ʊ��
			if(phIsZUOFEI(conn,wszmap) && wszmap != null){
				try{
					this.canclePH(ud, wszmap, "0.00");
				}catch(Exception e1){
					e1.printStackTrace();
					this.canclePH(ud, wszmap, "0.00");
				}
			}
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			
			throw new ApplicationException("ȡ�Ų���ʧ�ܣ�");
		}
		
		vo.setData(data);
		return this.doGetOne(vo);
	}
	
	
	/**
	 * ��ѯ��¼
	 * @param vo
	 * @return
	 */
	private Object doGetAll(VOPackage vo) throws BaseException{
		UserData ud = vo.getUserData();
		//���ǰ̨�ύ������
		SgsswszmlrForm data = (SgsswszmlrForm) vo.getData();
		
		try{
			data.setQuery_type(Constant.CONS_SGLR_QUERY_TYPE_0);
			ArrayList allDataList = this.doQuery(ud, data);
			
			if(allDataList.size() > 0){
				System.out.println("doGetAll size +++++++++++++++++++++++++++++++++++++++++"+allDataList.size());
				// ��ȡ��ҳ������
				data.setTotalpage(this.getPageTotalCount(allDataList.size()));
				// ��ҳ��ǰ��ʾ�����ݼ�
				ArrayList showDataList = new ArrayList();
				// �����ѯ���
				showDataList = this.getPageDataList(allDataList, data);
				
				data.setQueryList_onePage(showDataList);
				
				
			}else{
				throw new ApplicationException("�޶�Ӧ��ѯ�������ȷ�ϲ�ѯ���������ԣ�");
			}
			}catch(Exception e){
				e.printStackTrace();
				
				//������ض��׳����쳣
				if(e instanceof BaseException){
					throw new ApplicationException(e.getMessage());
				}
				throw new ApplicationException("��ѯʧ�ܣ����˳������ԣ�");
			}
		return data;
	}
	
	/**
	 * ����ض�һ���ֹ�¼����Ϣ
	 * @return
	 */
	private Object doGetOne(VOPackage vo)throws BaseException{
		UserData ud = vo.getUserData();
		ArrayList resList = new ArrayList();
		//���ǰ̨�ύ������
		SgsswszmlrForm data = (SgsswszmlrForm) vo.getData();
		try {
			//�����
			//1����ʾ�����ֹ�¼����ϸ  ��  ����ԭ�������´�ӡ
			
			//2���ش���Ҫ����ԭ���� ������Ʊ֤�ӿڻ�ȡ�º��룩
		
			data.setQuery_type(Constant.CONS_SGLR_QUERY_TYPE_1);
			resList = this.doQuery(ud, data);
			if(resList.size() >0){
				SgsswszmVo resvo = (SgsswszmVo)resList.get(0);
				
				//for ��ӡԤ��
				data.setPrintVo(resvo);
				
				//for �޸�  �鿴��ϸ
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
				data.setHasSaved(Constant.CONS_SGLR_HASSAVED_Y);//�ü�¼�Ѿ�����
			}else{
					throw new ApplicationException("�޶�Ӧ��ѯ�������ȷ�ϲ�ѯ���������ԣ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("��ѯʧ�ܣ����˳������ԣ�");
		}
		return data;
	}
	
	
	
	
	/**
	 * ִ�в�ѯ
	 * @param vo
	 * @return
	 */
	private ArrayList doQuery(UserData ud,SgsswszmlrForm data) throws BaseException{
		  Connection conn =null;
		  PreparedStatement ps = null;
		  ArrayList resList = new ArrayList();
		try{
			 conn = SfDBResource.getConnection();
			//��ò�ѯSQL 
				StringBuffer zbQuerySQL = new StringBuffer();//�����ѯSQL
				
				//��ò�ѯ����
				String  queryType = data.getQuery_type();
				//��1����ά����ѯ
				String  queryNsrmc = data.getQuery_nsrmc();
				String  queryNsrsbh = data.getQuery_nsrsbh();
				String  queryWspzh = data.getQuery_wspzh();
				//��2������ѯ������¼
				String[] keyArr = new String[2];
				String wszmKey = data.getWszmKey();
				
				//�жϲ�ѯ�����Ƿ�Ϊ��
				if(!isNotNullOrBlank(queryNsrmc)
						&&!isNotNullOrBlank(queryNsrsbh)
						&&!isNotNullOrBlank(queryWspzh)
						&&!isNotNullOrBlank(wszmKey))
				{
					throw new ApplicationException("��ѯ����δ¼���ѯ��������¼������ԣ�");
				}
				
				//ƴ�Ӳ�ѯSQL
				zbQuerySQL.append("select PZLYDM,SJM,PZZLDM,WSZH,NDZB,NSRSBH,NSRMC,SWJGZZJGDM,KJSWJGZZJGDM,TFRQ,trim(to_char(HJJE, '9999999999990.99')) HJJE,CJR,CJRQ,LRR,LRRQ,QXDM,YPZH,YPZZLDM,YWSZH,YNDZB,YWHM,DYBZ,DYCS,YXBZ,KJLYDM,BZ,PZLXDM,ZHDM from SBDB.SB_JL_KJSSWSZM where 1=1 ");
				//���ά����ѯ
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
					
				}//�����ѯ������Ϣ
				else if(Constant.CONS_SGLR_QUERY_TYPE_1.equals(queryType))
				{
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*******************+++++++++++++++++++++++++++++++++++++++++++++++++"+wszmKey);
					
					keyArr = wszmKey.split(Constant.CONS_SGLR_WSZMKEY_SPLIT);
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*******************+++++++++++++++++++++++++++++++++++++++++++++++++"+keyArr.length);
					if(keyArr != null && keyArr.length == 4){
						zbQuerySQL.append(" and pzzldm=? and wszh=? and ndzb =?");
					}else{
						throw new ApplicationException("��ѯ�ֹ���ϸ��Ϣ������¼���ѯ���������ԣ�");
					}
				}else{
					throw new ApplicationException("��ѯ������¼���ѯ���������ԣ�");
				}
				zbQuerySQL.append(" and lrr =? and kjswjgzzjgdm =? and pzlydm=?  and zhdm =?  and ywszh is null order by lrrq desc");
				System.out.println("�����ѯSQL#####################"+zbQuerySQL.toString());
			 
				System.out.println(isNotNullOrBlank(zbQuerySQL)+"��1�������ѯ������###########################################################################################");
				if(isNotNullOrBlank(zbQuerySQL)){
					System.out.println("��1�������ѯ������###########################################################################################");

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
					ps.setString(index++, ud.getXtsbm1());//�˻�����
						
					ResultSet rs = ps.executeQuery();
					int indx =1;
					while(rs.next()){
						System.out.println("��2�������ѯ������###########################################################################################");
						SgsswszmVo zbVo = new SgsswszmVo();
						zbVo.setIndexId(indx++);
						zbVo.setPZLYDM(rs.getString("PZLYDM"));//ƾ֤��Դ����
						zbVo.setSJM(rs.getString("SJM"));//�����
						zbVo.setPZZLDM(rs.getString("PZZLDM"));//Ʊ֤�������
						zbVo.setWSZH(rs.getString("WSZH"));//��˰֤����˰֤��
						zbVo.setNDZB(rs.getString("NDZB"));//����ֱ�
						zbVo.setNSRSBH(rs.getString("NSRSBH"));//��˰��ʶ���
						zbVo.setNSRMC(rs.getString("NSRMC"));//��˰������
						zbVo.setSWJGZZJGDM(rs.getString("SWJGZZJGDM"));//˰�������֯��������
						zbVo.setKJSWJGZZJGDM(rs.getString("KJSWJGZZJGDM"));//����˰�������֯��������
						zbVo.setSWJGMC(ud.getSsdwmc());
						zbVo.setTFRQ(DateTimeUtil.timestampToString(rs.getTimestamp("TFRQ"),DateTimeUtil.JAVA_DATEFORMAT));//�����
						zbVo.setHJJE(rs.getString("HJJE"));//�ϼƽ��
						zbVo.setHJJEDX(Currency.convert(Double.parseDouble(rs.getString("HJJE"))));//�ϼƽ���д
						zbVo.setCJR(rs.getString("CJR"));//������
						zbVo.setCJRQ(rs.getString("CJRQ"));//��������
						zbVo.setLRR(rs.getString("LRR"));//¼����
						zbVo.setLRRQ(rs.getString("LRRQ"));//¼������
						zbVo.setQXDM(rs.getString("QXDM"));//���ش���
						zbVo.setYPZH(rs.getString("YPZH"));//ԭƾ֤��
						zbVo.setYPZZLDM(rs.getString("YPZZLDM"));//ԭƱ֤�������
						zbVo.setYWSZH(rs.getString("YWSZH"));//ԭ��˰֤����˰֤��
						zbVo.setYNDZB(rs.getString("YNDZB"));//ԭ����ֱ�
						zbVo.setYWHM(rs.getString("YWHM"));//ҵ�����(Ͷ��ȷ����||������� ������ˮ��)
						zbVo.setDYBZ(rs.getString("DYBZ"));//��ӡ��־
						zbVo.setDYCS(rs.getString("DYCS"));//��ӡ����
/*						if(zbVo.getDYCS() != null && !"".equals(zbVo.getDYCS())){
							zbVo.setCURRENT_DYCS((Integer.valueOf(zbVo.getDYCS()).intValue() + 1)+"");
						}*/
						zbVo.setCURRENT_DYCS("1");//�ֹ��涨����ӡ������ԶΪ��һ��
						zbVo.setYXBZ(rs.getString("YXBZ"));//��Ч��־
						zbVo.setKJLYDM(rs.getString("KJLYDM"));//������Դ����(0˰����˰֤������ 1�걨���� 2��������)
						zbVo.setBZ(rs.getString("BZ"));//��ע
						zbVo.setPZLXDM(rs.getString("PZLXDM"));//ƾ֤���ʹ���(0��˰ƾ֤  1��˰ƾ֤)
						zbVo.setCjwszmBYothers(hasCJWSZMByOthers(conn, zbVo.getNDZB(), zbVo.getPZZLDM(), zbVo.getWSZH()));//�Ƿ��Ѿ������߹���˰֤��
						
						System.out.println("��3�������ѯ������###########################################################################################");

							//��ȡ��ϸ��Ϣ
						if(Constant.CONS_SGLR_QUERY_TYPE_1.equals(queryType)){
							System.out.println("��1����ѯ��ϸ����###########################################################################################");

								getMxList(conn, zbVo);
							}
						resList.add(zbVo);
						}
					}else{
						throw new ApplicationException("��ѯʧ�ܣ����˳������ԣ�");
					}
		}catch(Exception e){
			e.printStackTrace();
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("��ѯʧ�ܣ����˳������ԣ�");
		}finally{
			  SfDBResource.freeConnection(conn);
		  }
		return resList;
	}

	/*
	 * ��ȡ��ϸ��Ϣ
	 */
	private ArrayList getMxList(Connection conn,SgsswszmVo zbVo) throws BaseException {
		ArrayList rsList = new ArrayList();
		try{
			StringBuffer mxQuerySQL = new StringBuffer();
			mxQuerySQL.append("select pzzldm,wszh,ndzb,xh,ypzh,szdm,szsmdm,swjgzzjgdm,kjswjgzzjgdm,to_char(skssksrq,'yyyymmdd') skssksrq,to_char(skssjsrq,'yyyymmdd') skssjsrq,to_char(rtkrq,'yyyymmdd') rtkrq,trim(to_char(sjtje, '9999999999990.99')) sjtje,cjr,  cjrq,lrr,lrrq from SBDB.SB_JL_KJSSWSZMMX where pzzldm=? and wszh=? and ndzb =? and lrr =? and kjswjgzzjgdm =? order by xh");
			System.out.println("��ϸ���ѯSQL#####################"+mxQuerySQL.toString());
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
			throw new ApplicationException("��ѯ���������˰��Ϣ��ϸ���������ԣ�");
		}
		
		return rsList;
	}
	
	/**
	 * ��ô�ӡ��ϸ��Ϣ
	 * @param zbVo
	 * @param mxrs
	 * @throws BaseException 
	 */
	private void getMx2Print(SgsswszmVo zbVo,ResultSet mxrs) throws BaseException{
		SgsswszmMXVo printMxVo = new SgsswszmMXVo();//������ӡ��Ϣ
		
		StringBuffer ypzhBF = new StringBuffer();//ԭƾ֤��
		StringBuffer szmcBF = new StringBuffer();//˰��
		StringBuffer pmmcBF = new StringBuffer();//ƷĿ����
		StringBuffer skssqBF = new StringBuffer();//˰������ʱ��
		StringBuffer rtkrqBF = new StringBuffer();//�루�ˣ�������
		StringBuffer sjjeBF = new StringBuffer();//ʵ�ɣ��ˣ����
		try{
			while(mxrs.next()){
				ypzhBF.append(mxrs.getString("ypzh")+";;");
				szmcBF.append(CodeUtils.getCodeBeanLabel("DM_SZSM", mxrs.getString("szdm"))+";;");
				pmmcBF.append(CodeUtils.getCodeBeanLabel("DM_SZSM", mxrs.getString("szsmdm"))+";;");
				skssqBF.append(mxrs.getString("skssksrq")+" �� "+mxrs.getString("skssjsrq")+";;");
				rtkrqBF.append(mxrs.getString("rtkrq")+";;");
				sjjeBF.append(mxrs.getString("sjtje")+";;");
			}
			//��ֵ����ӡ��ϸVO
			printMxVo.setOld_pzh(ypzhBF.toString());
			printMxVo.setSelect_szmc(szmcBF.toString());
			printMxVo.setSelect_zssmmc(pmmcBF.toString());
			printMxVo.setSkssrq(skssqBF.toString());
			printMxVo.setRk_tkrq(rtkrqBF.toString());
			printMxVo.setSjje(sjjeBF.toString());
			
			zbVo.setPrintMx(printMxVo);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("��ѯ���������˰��Ϣ��ϸ���������ԣ�");
		}
	}
	
	/**
	 * �����ϸ��Ϣ������  �޸� �ã�
	 * @param zbVo
	 * @param mxrs
	 */
	private void getMx2ReShow(SgsswszmVo zbVo,ResultSet mxrs)throws BaseException{
		StringBuffer reShowBF = new StringBuffer();//������ϸ���籣��ɹ����� �����޸���Ϣ��
		try{
			while(mxrs.next()){
				reShowBF.append(mxrs.getString("ypzh"));//ԭƾ֤��
				reShowBF.append("~");
				reShowBF.append(mxrs.getString("szsmdm")+"-"+CodeUtils.getCodeBeanLabel("DM_SZSM", mxrs.getString("szsmdm")));//ƷĿ����
				reShowBF.append("~");
				reShowBF.append(mxrs.getString("skssksrq"));//˰������ʱ�ڿ�ʼ
				reShowBF.append("~");
				reShowBF.append(mxrs.getString("skssjsrq"));//˰������ʱ�ڽ���
				reShowBF.append("~");
				reShowBF.append(mxrs.getString("rtkrq"));//�루�ˣ�������
				reShowBF.append("~");
				reShowBF.append(mxrs.getString("sjtje"));//ʵ�ɣ��ˣ����
				reShowBF.append("^");
			}
			System.out.println("��ӡ��Ϣ(1)###############"+reShowBF.toString());
			if(reShowBF.length() >0){
				reShowBF = reShowBF.replace(reShowBF.length()-1, reShowBF.length(), "");
			}
			System.out.println("��ӡ��Ϣ(2)###############"+reShowBF.toString());
			zbVo.setReShowMx(reShowBF.toString());
			
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("��ѯ���������˰��Ϣ��ϸ���������ԣ�");
		}
	}
	
	
	/**
	 * �жϵ�ǰƱ���Ƿ��Ѿ���ӡ
	 * @param conn
	 * @param ndzb
	 * @param pzzldm
	 * @param wszh
	 * @return
	 */
	private boolean hasPrinted(Connection conn,String ndzb,String pzzldm,String wszh)throws BaseException{
		boolean succ = false;//�����Ƿ�ɹ�
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
				throw new ApplicationException("��ȡƱ�Ŵ�ӡ��־����");
			}
		}catch(Exception e){
			e.printStackTrace();
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("��ȡƱ�Ŵ�ӡ��־����");
		}
		return succ;
	}
	
	
	
	/**
	 * ���´�ӡ��־(��δ��ӡ���µ��Ѵ�ӡ)
	 * @param ndzb
	 * @param pzzldm
	 * @param wszh
	 * @return
	 */
	private boolean updateDYBZ(Connection conn,UserData ud ,String ndzb,String pzzldm,String wszh)throws BaseException{
		boolean succ = false;//�����Ƿ�ɹ�
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
				ps.setString(index++, ud.getXtsbm1());//�˻�����
				
				succ = ps.execute();
			}else{
				throw new ApplicationException("���´�ӡ��־����");
			}
		}catch(Exception e){
			e.printStackTrace();
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("���´�ӡ��־����");
		}
		return succ;
	}
	
	/**
	 * �����Ѵ�ӡ���� (�涨�Ѵ�ӡ������ԶΪ1��)
	 * @param conn
	 * @param ndzb
	 * @param pzzldm
	 * @param wszh
	 * @return
	 */
	private boolean updateDYCS(Connection conn,UserData ud,String ndzb,String pzzldm,String wszh)throws BaseException{
		boolean succ = false;//�����Ƿ�ɹ�
		
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
			ps.setString(index++, ud.getXtsbm1());//�˻�����
			
			succ = ps.execute();
			}else{
				throw new ApplicationException("���´�ӡ��������");
			}
		}catch(Exception e){
			e.printStackTrace();
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("���´�ӡ��������");
		}
		return succ;
	}
	
	/**
	 * ���һ����־����־һ����¼�Ƿ��Ѿ��������ط��������ѯ������˰֤�������߹���˰֤��
	 * @return
	 */
	private String hasCJWSZMByOthers(Connection conn,String ndzb,String pzzldm,String wszh)throws BaseException{
		String hasCJ = Constant.CONS_SGLR_CJWSZM_BY_OTHERS_N;//0--δ����
		try{
			if(isNotNullOrBlank(conn) && isNotNullOrBlank(ndzb) && isNotNullOrBlank(pzzldm) && isNotNullOrBlank(wszh)){
				String querySQL = "SELECT * FROM SBDB.SB_JL_KJSSWSZM t where t.ywszh=? and t.yndzb=? and t.ypzzldm=? and t.yxbz=?";
				PreparedStatement ps = conn.prepareStatement(querySQL);
				int index =1;
				ps.setString(index++, wszh);
				ps.setString(index++, ndzb);
				ps.setString(index++, pzzldm);
				ps.setString(index++, Constant.CONS_SGLR_YXBZ_0);//��Ч
				//ps.setString(index++, Constant.CONS_SGLR_PZLYDM_08);//���ǣ��ֹ�
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()){
					hasCJ =Constant.CONS_SGLR_CJWSZM_BY_OTHERS_Y;//  1-- �ѳ���
				}
			}else{
				throw new ApplicationException("��ȡ��˰֤�����߱�־����");
			}
		}catch(Exception e){
			e.printStackTrace();
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("��ȡ��˰֤�����߱�־�������˳������ԣ�");
			
		}
		return hasCJ;
	}
	
	/**
	 * �ж�Ʊ���Ƿ���������״̬
	 * 1��������Ѿ�����  ����Ʊ���ڱ��в��� ���� true  
	 * 2���������������false
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
				ps.setString(index++, Constant.CONS_SGLR_YXBZ_1);//����
				ps.setString(index++, Constant.CONS_SGLR_PZLYDM_08);
				
				ResultSet rs = ps.executeQuery();
				
				//����н��������
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
				
				//��������ڸ�Ʊ
				if(rs.next()== false){
					iszuofei = true;
				}
				
			}else{
				throw new ApplicationException("��ȡƱ��״̬����,�޷���ȡ��ѯ������");
			}
		}catch(Exception e){
			e.printStackTrace();
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("��ȡƱ��״̬�������˳������ԣ�");
		}
		
		return iszuofei;
	}
	
	
	

	

	
	/**
	 * ����Ʊ֤�ӿڣ����һ����Ʊ 
	 */
	private  HashMap getNewPH(UserData ud,String hjje)throws BaseException{
		HashMap map = new HashMap();
		String ndzb ="";
		String wszh="";
        //�����˰֤��
        try {
            String retResult = ServiceProxy.getNumber(ud, Constant.CONS_SGLR_PZZLDM,
                StringUtil.getDouble(hjje, 0), "1", "0");
            ndzb = retResult.substring(0, 4); //����ֱ�
            wszh = retResult.substring(4); //��˰֤��
            
            System.out.println("����ֱ�+++++++++++++++++"+ndzb);
            System.out.println("��˰֤��+++++++++++++++++"+wszh);
            
            map.put("ndzb", ndzb);
            map.put("wszh", wszh);
            
        } catch (Exception ex) {
            ex.printStackTrace();
			//������ض��׳����쳣
			if(ex instanceof BaseException){
				throw new ApplicationException(ex.getMessage());
			}
            throw new ApplicationException("��ȡ��˰֤ʧ�ܣ�");
        }
        
        return map;
	}
	
	/**
	 * ����Ʊ֤�ӿڡ�����Ʊ 
	 */
	private void canclePH(UserData ud,HashMap wszmap,String hjje)throws BaseException{
        //3������Ʊ֤�ӿڣ����ϵ�ǰ��˰֤�ţ�������ȡ��
		String ndzb = (String)wszmap.get("ndzb");
		String wszh = (String)wszmap.get("wszh");
		
		System.out.println("������˰֤��+++++++++++++++++"+wszh);
        System.out.println("��������ֱ�+++++++++++++++++"+ndzb);
        
        try
        {
			if( ndzb != null && !"".equals(ndzb) 
				&& wszh != null && !"".equals(wszh)){
				
				String result = ServiceProxy.setCancellation(ud,
						Constant.CONS_SGLR_PZZLDM,
						ndzb+wszh,
						StringUtil.getDouble(hjje, 0), "1", "0", "0");
				
				if(result == null || "0".equals(result)){
					throw new ApplicationException("����Ʊ֤�ӿڳ����޷�����Ʊ�ţ�");
				}
			}
			
        }
        catch (Exception ex1)
        {
        	ex1.printStackTrace();
			//������ض��׳����쳣
			if(ex1 instanceof BaseException){
				throw new ApplicationException(ex1.getMessage());
			}
            throw new ApplicationException("������˰֤�ų���");
        }
		
		
	}
	
	/**
	 * �ж��Ƿ����ܴ�Ʊ
	 * @return
	 */
	private boolean havePH(UserData ud){
		boolean yes= false;
		//��ȡ��˰֤����
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
		// DecimalFormat deFormat = new DecimalFormat("#0.00");// �ϼƽ���ʽ
		BigDecimal zje = new BigDecimal(StrJe);// �������ǰ�ĺϼƽ����Ϣ
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
	 * ��ȡҳ��
	 * 
	 * @param rsCount
	 *            ��ѯ�����build
	 * @return ҳ��
	 */
	private String getPageTotalCount(int rsCount) {
		// �������
		String countTotal = "0";
		// ��ʼҵ��
		int pageCount = 0;
		if ((rsCount % CodeConstant.SD_PG_LENGTH) == 0) {
			pageCount = (rsCount / CodeConstant.SD_PG_LENGTH);
		} else {
			pageCount = (rsCount / CodeConstant.SD_PG_LENGTH) + 1;
		}
		countTotal = String.valueOf(pageCount);
		// ����ֵ
		return countTotal;
	}
	
	/**
	 * ��ȡ��ǰ��ҳ���ݼ�
	 * 
	 * @param tmpList
	 *            �������ݼ�
	 * @param pf
	 *            ҳ�����
	 * @return ��ǰ��ҳ���ݼ�
	 */
	private ArrayList getPageDataList(List tmpList, SgsswszmlrForm pf) {
		// 1.�������
		ArrayList dataList = new ArrayList();
		// 2.��ʼ������ֵ
		int startIndex = this.getPageStartIndex(pf.getNextPage(), pf
				.getTotalpage());
		int endIndex = this
				.getPageEndIndex(pf.getNextPage(), pf.getTotalpage());
		// 3.��ʼҵ��
		for (int i = startIndex; i < endIndex; i++) {
			if (i < tmpList.size()) {
				dataList.add(tmpList.get(i));
			}
		}
		tmpList = null;
		// 99.����ֵ
		return dataList;
	}
	
	/**
	 * ��ȡ��ǰҳ��ʼindex
	 * 
	 * @param nextPage
	 *            ��һҳ
	 * @param countTotal
	 *            ��ҳ��
	 * @return ��ʼindex
	 */
	private int getPageStartIndex(String nextPage, String countTotal) {
		// 1.�������
		int iNextPage = Integer.parseInt(nextPage);
		int iCountTotal = Integer.parseInt(countTotal);
		int start = -1;
		// 2.��ʼҵ��
		start = (iNextPage - 1) * CodeConstant.SD_PG_LENGTH;
		// 99.����ֵ
		return start;
	}
	
	/**
	 * ��ȡ��ǰҳ����index
	 * 
	 * @param nextPage
	 *            ��һҳ
	 * @param countTotal
	 *            ��ҳ��
	 * @return ����index
	 */
	private int getPageEndIndex(String nextPage, String countTotal) {
		// 1.�������
		int iNextPage = Integer.parseInt(nextPage);
		int iCountTotal = Integer.parseInt(countTotal);
		int end = -1;
		// 2.��ʼҵ��
		end = iNextPage * CodeConstant.SD_PG_LENGTH;
		// 99.����ֵ
		return end;
	}
}
