package com.creationstar.bjtax.qsgl.BizLogic.processor.fpgl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwhdxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Htypzdzgxb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxBuyer;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxSeller;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpkc;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpzl;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpdkBO;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.DateUtils;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.SecurityUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.util.Currency;

/**
 * <p>Title: 北京地税核心征管系统－－发票管理</p>
 * <p>Description: 发票管理模块的发票代开功能Processor类</p>
 * @author tutu
 * @version 1.1
 */
public class FpdkProcessor implements Processor {

	/**
    *
    * @param vo VOPackage
    * @return Object
    * @throws BaseException
    */
   public Object process(VOPackage vo) throws BaseException {
       Object result = null;

       Debug.out("--Debug-- Info : Here is FpdkProcessor.process(vo)");

       if (vo == null) {
           throw new NullPointerException();
       }
       switch (vo.getAction()) 
       {
       		case ActionType.GET:
       			result = doShow(vo);
       			break;
       		
       		case ActionType.QUERY:
       		   result = doQuery(vo);
       		   break;
   		   
       	   case ActionType.INSERT:
       		   result = doSave(vo);
       		   break;
       		   
       	   default:
           throw new ApplicationException(
                   "ActionType有错误，processor中找不到相应的方法.");
       }

       return result;
   }
   
   /**
    * 显示初始页面
    * @param vo
    * @return
    * @throws BaseException
    */
	private Object doShow(VOPackage vo) throws BaseException 
	{
		// 从VOPackage中获得数据
		FpdkBO bo = (FpdkBO)vo.getData();
		
		Connection conn = null;
		try 
		{
			conn = QSDBUtil.getConnection();
			
			String fpkfdm = bo.getFpkfdm(); //发票库房代码
			
			ArrayList fpzlList = bo.getFpzlList(); //库存中有发票数量的发票种类List
			
			ArrayList KcList = new ArrayList();  // 库存结存list
			
			Timestamp newTime = null;
			
			//获得fpzlList中第一条发票种类代码
			if ((fpzlList != null) && (fpzlList.size() > 0))
			{
				Fpzl fpzl = new Fpzl();
				Fpzl fpzlItem = (Fpzl)fpzlList.get(0);
				fpzl.setFpzldm(fpzlItem.getFpzldm());
				
				//得到最新的结存时间
				try 
				{
					//拼接SQL
					String conditions = " where fpkfdm = '" + fpkfdm+ "'  and fpzldm = '" + fpzl.getFpzldm() + "' ";
					
					newTime = DAOFactory.getInstance().getFpkcDAO().queryMaxTime(conn, conditions);
					
					Debug.out("发票代开管理：查询最大结存时间成功...."+newTime);
				}
				catch (Exception ex) 
				{
					Debug.out(ex);
					throw new ApplicationException("查询最大结存时间出错！");
				}
				
				// 根据发票库房代码fpkfdm、发票种类代码fpzldm、最大结存时间newTimeStr 获取最新时间对应的起始号码
				if(newTime != null)
				{
					String newTimeStr = newTime.toString();
					try
					{
						String conditions = "where fpkfdm = '" + fpkfdm + "'  and fpzldm = '" + fpzl.getFpzldm() + "' " +
								"and jcsj = to_date('"+newTimeStr.substring(0,19)+"','yyyy-mm-dd hh24:mi:ss') " +
								"and sl <>'"+Constants.FP_SL_ZERO+"' order by fpkfdm, fpzldm, qshm, jcsj  ";
						
						ArrayList oldKcList = DAOFactory.getInstance().getFpkcDAO().queryMaxList(conn, conditions);
						
						for (int j = 0; j < oldKcList.size(); j++)
						{
							Fpkc fpkc= new Fpkc();
		            		Fpkc qshmItem = (Fpkc) oldKcList.get(j);
		            				
		            		fpkc.setFpkfdm(qshmItem.getFpkfdm());
		            		fpkc.setFpzldm(qshmItem.getFpzldm());
		            		fpkc.setQshm(qshmItem.getQshm());
		            		fpkc.setJzhm(qshmItem.getJzhm());
		            		fpkc.setSl(qshmItem.getSl());
		            		fpkc.setSwjgzzjgdm(qshmItem.getSwjgzzjgdm());
		            		
		            		KcList.add(fpkc);
		            	}
							Debug.out("发票代开管理：查询最大结存记录结束....");
						}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("查询最大结存记录出错！");
					}
				}
			}
			bo.setFpkcList(KcList);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally {
            QSDBUtil.freeConnection(conn);
        }
		return bo;
	}
	
	/**
	 * 查询
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doQuery(VOPackage vo) throws BaseException 
	{
		// 从VOPackage中获得数据
		FpdkBO bo = (FpdkBO)vo.getData();
		UserData ud = vo.getUserData();
		
		// 定义wszh
		String wszh = "";
		// 定义返回信息
		String message = "";
		
		Connection conn = null;
		CallableStatement proc = null;
		try 
		{	
			// 获取数据连接
			conn = QSDBUtil.getConnection();
			
			String htbh = SecurityUtil.dealwithStringPara(bo.getHtbh());  //合同编号
			String fpkfdm = bo.getFpkfdm();  //发票库房代码
			String fpzldm = bo.getFpzldm();  //发票种类代码
			String swjgzzjgdm = bo.getSwjgzzjgdm();  //税务机关组织机构代码
			ArrayList fpzlList = bo.getFpzlList(); //库存中有发票数量的发票种类List
			Timestamp newTime = null;
			ArrayList KcList = new ArrayList();  // 库存结存list
			
			//获得存量房信息
			if (htbh != null && !htbh.equals(""))
			{
				//（1）获得fpzlList中第一条发票种类代码
				if (fpzlList != null && fpzlList.size() > 0)
				{
					Fpzl fpzl = new Fpzl();
					Fpzl fpzlItem = (Fpzl)fpzlList.get(0);
					fpzl.setFpzldm(fpzlItem.getFpzldm());
					
					//得到最新的结存时间
					try 
					{
						//拼接SQL
						String conditions = " where fpkfdm = '" + fpkfdm+ "'  and fpzldm = '" + fpzl.getFpzldm() + "' ";
						
						newTime = DAOFactory.getInstance().getFpkcDAO().queryMaxTime(conn, conditions);
						
						Debug.out("发票代开管理：查询最大结存时间成功...."+newTime);
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("查询最大结存时间出错！");
					}
					
					// 根据发票库房代码fpkfdm、发票种类代码fpzldm、最大结存时间newTimeStr 获取最新时间对应的起始号码
					if(newTime != null)
					{
						String newTimeStr = newTime.toString();
						try
						{
							String conditions = "where fpkfdm = '" + fpkfdm + "'  and fpzldm = '" + fpzl.getFpzldm() + "' " +
									"and jcsj = to_date('"+newTimeStr.substring(0,19)+"','yyyy-mm-dd hh24:mi:ss') " +
									"and sl <>'"+Constants.FP_SL_ZERO+"' order by fpkfdm, fpzldm, qshm, jcsj  ";
							
							ArrayList oldKcList = DAOFactory.getInstance().getFpkcDAO().queryMaxList(conn, conditions);
							
							for (int j = 0; j < oldKcList.size(); j++)
							{
								Fpkc fpkc= new Fpkc();
			            		Fpkc qshmItem = (Fpkc) oldKcList.get(j);
			            				
			            		fpkc.setFpkfdm(qshmItem.getFpkfdm());
			            		fpkc.setFpzldm(qshmItem.getFpzldm());
			            		fpkc.setQshm(qshmItem.getQshm());
			            		fpkc.setJzhm(qshmItem.getJzhm());
			            		fpkc.setSl(qshmItem.getSl());
			            		fpkc.setSwjgzzjgdm(qshmItem.getSwjgzzjgdm());
			            		
			            		KcList.add(fpkc);
			            	}
								Debug.out("发票代开管理：查询最大结存记录结束....");
							}
						catch (Exception ex) 
						{
							Debug.out(ex);
							throw new ApplicationException("查询最大结存记录出错！");
						}
					}
					bo.setFpkcList(KcList);
				}
				
				
				//（2）获得完税凭证信息
				proc = conn.prepareCall("{ call qsdb.qs_pkg_clfjy.returnwspzh(?,?,?,?) }");
				int k = 1;
				// 输入参数
				proc.setString(k++,htbh);
				proc.setString(k++,"");
				
				// 出口参数
				proc.registerOutParameter(k++, Types.VARCHAR);
				proc.registerOutParameter(k++, Types.VARCHAR);
				// 调用存储过程
				proc.execute();
				
				// 获取wsz
				wszh = proc.getString(3);
				message = proc.getString(4);
				
				if(message != null && !message.equals("") && message.substring(0, 1).equals("0"))
				{
					bo.setMessage(message);
				}
				
				bo.setWszh(wszh);
				Debug.out("发票代开管理：查询完税凭证信息成功....");
				if(proc != null) 
				{
					try 
					{
						proc.close();
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
				
				//（3）查询房屋产权证号、房屋坐落地址、单价、金额、大小写合计
				try 
				{
					Fwxx fwxx =null;
					
					// 增加数据权限控制
		            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
		                    "QSDB", "QS_JL_FWXXB" );
		            Debug.out("datafilter: " + datafilter);
		            
					//拼接SQL
					String conditions = " where htbh = '" + htbh+ "' and"+datafilter;
					
					ArrayList fwxxList = DAOFactory.getInstance().getFwxxDAO().queryFwList(conn, conditions);
					if(fwxxList == null || fwxxList.size() == 0)
					{
						bo.setMessagefwx("无查询结果，无对应房屋采集信息，或者没有权限查看该采集信息!");
						return bo;
					}
					
	  				DecimalFormat deFormat = new DecimalFormat("#0.00");// 合计金额格式
					if((fwxxList != null) && (fwxxList.size() != 0))
					{
						for (int j = 0; j < fwxxList.size(); j++)
						{
							fwxx = new Fwxx();
							Fwxx fwxxItem = (Fwxx) fwxxList.get(j);
							
							fwxx.setSbbh(fwxxItem.getSbbh());
							fwxx.setFwcqzh(fwxxItem.getFwcqzh());
							fwxx.setFwzldz(fwxxItem.getFwzldz());
							fwxx.setHtzj(fwxxItem.getHtzj());
						}
						
						bo.setSbbh(fwxx.getSbbh()); //申报表号
						bo.setFwcqzh(fwxx.getFwcqzh()); //房屋产权证号
						bo.setFwzldz(fwxx.getFwzldz()); //房屋坐落地址
						bo.setHtzj(String.valueOf(deFormat.format(fwxx.getHtzj())));  //合同金额
						bo.setXxhj(String.valueOf(deFormat.format(fwxx.getHtzj())));  // 小写金额合计
						bo.setDxhj(Currency.convert((fwxx.getHtzj().doubleValue())).substring(1));  //大写金额合计
						
						Debug.out("发票代开管理：查询房屋信息成功....");
					}
				}
				catch (Exception ex) 
				{
					Debug.out(ex);
					throw new ApplicationException("查询房屋信息出错！");
				}
				
				//（4）查询核定价格
				try 
				{
					Fwhdxx fwhdxx  = null;
					
					//拼接SQL
					String conditions = " where htbh = '" + htbh+ "'";
					
					ArrayList fwhdxxList = DAOFactory.getInstance().getFwhdxxbDAO().query(conn, conditions);
					
					DecimalFormat deFormat = new DecimalFormat("#0.00");// 合计金额格式
					if((fwhdxxList != null) && (fwhdxxList.size() != 0))
					{
						for (int j = 0; j < fwhdxxList.size(); j++)
						{
							fwhdxx = new Fwhdxx();
							Fwhdxx fwhdxxItem = (Fwhdxx) fwhdxxList.get(j);
        				
							fwhdxx.setHdjg(fwhdxxItem.getHdjg());

						}
						bo.setHdjg(DataConvert.BigDecimal2String(fwhdxx.getHdjg(),2,false)); //核定价格
						
						//System.out.println("BO核定价格:"+bo.getHdjg());
						
						Debug.out("发票代开管理：查询税务机关核定价格信息成功....");
					}
				}
				catch (Exception ex) 
				{
					Debug.out(ex);
					throw new ApplicationException("查询税务机关核定价格信息出错！");
				}
				
				
				//（5）查询收款单位-卖方-信息(如果有多个，则使用空格隔开)
				try 
				{
					String mfgrxxSellers = "";
					MfgrxxSeller mfgrxxSeller  = null;
					
					//拼接SQL
					String conditions = " where htbh = '" + htbh + "' " ;
					
					ArrayList mfgrxxSellerList = DAOFactory.getInstance().getMfgrxxSellerDAO().queryMfgrxxSellerList(conn, conditions);
					
					if((mfgrxxSellerList != null) && (mfgrxxSellerList.size() != 0))
					{
						for (int j = 0; j < mfgrxxSellerList.size(); j++)
						{
							mfgrxxSeller = new MfgrxxSeller();
							MfgrxxSeller mfgrxxSellerItem = (MfgrxxSeller) mfgrxxSellerList.get(j);
        				
							mfgrxxSeller.setNsrmc(mfgrxxSellerItem.getNsrmc());
							
							if(j <  mfgrxxSellerList.size()-1)
							{
								mfgrxxSellers = mfgrxxSellers + mfgrxxSeller.getNsrmc() + " ";
							}
							else
							{
								mfgrxxSellers = mfgrxxSellers + mfgrxxSeller.getNsrmc();
							}
						}
						bo.setNsrmc_seller(mfgrxxSellers); //收款单位
						
						Debug.out("发票代开管理：查询卖方个人信息成功....");
					}
				}
				catch (Exception ex) 
				{
					Debug.out(ex);
					throw new ApplicationException("查询卖方个人信息出错！");
				}
				
				
				//（6）查询收款单位-买方-信息(如果有多个，则使用空格隔开)
				try 
				{
					String mfgrxxBuyers = "";
					MfgrxxBuyer mfgrxxBuyer  = null;
					
					//拼接SQL
					String conditions = " where htbh = '" + htbh + "'";
					
					ArrayList mfgrxxBuyerList = DAOFactory.getInstance().getMfgrxxBuyerDAO().queryMfgrxxBuyerList(conn, conditions);
					
					if((mfgrxxBuyerList != null) && (mfgrxxBuyerList.size() != 0))
					{
						for (int j = 0; j < mfgrxxBuyerList.size(); j++)
						{
							mfgrxxBuyer = new MfgrxxBuyer();
							MfgrxxBuyer mfgrxxBuyerItem = (MfgrxxBuyer) mfgrxxBuyerList.get(j);
        				
							mfgrxxBuyer.setNsrmc(mfgrxxBuyerItem.getNsrmc());
							
							if(j <  mfgrxxBuyerList.size()-1)
							{
								mfgrxxBuyers = mfgrxxBuyers + mfgrxxBuyer.getNsrmc() + " ";
							}
							else
							{
								mfgrxxBuyers = mfgrxxBuyers + mfgrxxBuyer.getNsrmc();
							}
						}
						
						bo.setNsrmc_buyer(mfgrxxBuyers); //收款单位
						
						Debug.out("发票代开管理：查询买方个人信息成功....");
					}
				}
				catch (Exception ex) 
				{
					Debug.out(ex);
					throw new ApplicationException("查询买方个人信息出错！");
				}

			}else{
				throw new ApplicationException("发票代开信息查询失败！");
			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally {
            QSDBUtil.freeConnection(conn);
        }	
		return bo;
	}
	
	
	/**
	 * 保存
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doSave(VOPackage vo) throws BaseException  
	{
		// 从VOPackage中获得数据
		FpdkBO bo = (FpdkBO)vo.getData();
		
		UserData ud = vo.getUserData();
		
		bo.setFpkpList(new ArrayList());
		Connection conn = null;
		try 
		{
			conn = QSDBUtil.getConnection();
			
			String qshm = bo.getQshm();  //起始号码
			String htbh = bo.getHtbh();  //合同编号
			String fpkfdm = bo.getFpkfdm();  //发票库房代码
			String fpzldm = bo.getFpzldm();  //发票种类代码
			
			double je = Double.valueOf(bo.getJe()).doubleValue();//总金额
			int pageNum = bo.getPageNum();//打印张数
			
			//如果打印张数为空或者0则，报错
			if(pageNum ==0)
			{
				throw new ApplicationException("打印张数为：0");
			}
			
			//获得存量房信息
			if ( (htbh != null && !htbh.equals("")) && (qshm != null && !qshm.equals("")))
			{
				//判断是否有库存
				ArrayList fpkcList  = ActionUtil.queryMaxFpkc2(fpkfdm,fpzldm,qshm);
				
				//如果一张  直接打
				if(pageNum == 1)
				{
					saveFPXX(bo,ud, conn,qshm,je+"");
					return bo;
				}
				
				//判断多张
				if(pageNum > 1)
				{
					if(fpkcList != null && fpkcList.size() !=0)
					{
						Fpkc fpkc = new Fpkc();
						fpkc = (Fpkc)fpkcList.get(0);
						
						BigDecimal index_0_kcsl = fpkc.getSl();
						
						if(index_0_kcsl != null)
						{
							int qshm_next = Integer.valueOf(qshm).intValue();
							
							//获取kcList第一条记录中前台录入的起始号码后的所有数量（正整数，即防止输入的号码在中间）
							
							int index_0_jzhm_INT = Integer.valueOf(fpkc.getJzhm()).intValue();
							int index_0_kcsl_INT = (index_0_jzhm_INT - qshm_next)+1;
							
							//1）判断kcList的第一条记录的张数是否不小于pageNum  要打印的张数
							
							//1.1）如果不小于（够打印），则直接加一打印下一张
							if(index_0_kcsl_INT >= pageNum)
							{
								for(int index =0; index < pageNum; index ++)
								{
									double current_je =0.00;
									if(je > Constants.FP_MAX_JE)
									{
										current_je = Constants.FP_MAX_JE;
										je -= Constants.FP_MAX_JE;
									}else{
										current_je = je;
									}
									//System.out.println("第一列>>> current_je:"+current_je+"  qshm_next:"+calculateHm(qshm,index));
									saveFPXX(bo,ud, conn,calculateHm(qshm,index),current_je+"");
								}
								return bo;
							}
							else{//1.2）如果不够打印 ，先把一条记录中的票按照 1.1的规则把当前号码以后的票使用完，然后跳到kcList下一条记录，一次类推则可
								//把第一条记录从输入的起始号码开始之后的所有票用完
								for(int index =0; index < index_0_kcsl_INT; index ++)
								{
									double current_je =0.00;
									if(je > Constants.FP_MAX_JE)
									{
										current_je = Constants.FP_MAX_JE;
										je -= Constants.FP_MAX_JE;
									}
									else{
										current_je = je;
									}
									//System.out.println("下一列>>> current_je:"+current_je+"  qshm_next:"+calculateHm(qshm,index));
									saveFPXX(bo,ud, conn,calculateHm(qshm,index),current_je+"");
								}
								
								pageNum = pageNum - index_0_kcsl_INT;
								//System.out.println("下一列后>>> pageNum:"+pageNum);
								
								//用下一条记录
								for(int index =1; index < fpkcList.size();index ++)
								{
									fpkc = (Fpkc)fpkcList.get(index);
									int kcsl_next_index = Integer.valueOf(fpkc.getSl()+"").intValue();
									for(int innerInd =0; innerInd < kcsl_next_index;innerInd++)
									{
										double current_je =0.00;
										if(je > Constants.FP_MAX_JE)
										{
											current_je = Constants.FP_MAX_JE;
											je -= Constants.FP_MAX_JE;
										}
										else{
											current_je = je;
										}
										
										//System.out.println("再下一列>>> current_je:"+current_je+"  qshm_next_index:"+calculateHm(fpkc.getQshm(),innerInd));
										saveFPXX(bo,ud, conn,calculateHm(fpkc.getQshm(),innerInd),current_je+"");	
										
										pageNum -= 1;
										if(pageNum == 0)
										{
											return bo;
										}
									}
								}
							}
						}
						else{
							throw new ApplicationException("库存数查询失败！");
						}
					}
					else{
					throw new ApplicationException("打印张数不大于1张！");
				}
			}
		}
		}catch (Exception ex) 
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally {
            QSDBUtil.freeConnection(conn);
        }
		return bo;
	}

	private void saveFPXX(FpdkBO bo,UserData ud, Connection conn,String qshm,String je) throws BaseException {
		try 
		{
			
			//得到当前时间
			Timestamp now = CommonUtil.getDBtime(conn);
			
			//fpdkForm 中的 发票相关信息
			String swjgzzjgdm = bo.getSwjgzzjgdm(); //税务机关组织机构代码
			String swjgzzjgmc = bo.getSwjgzzjgmc(); //税务机关组织机构代码
			String fpkfdm = bo.getFpkfdm();  //发票库房代码
			String fpzldm = bo.getFpzldm();  //发票种类代码
			String kpr = bo.getKpr();  //开票人
			String yhr = bo.getYhid() + bo.getYhmc(); //使用人员
			
			//fpdkForm 中的 存量房相关信息
			String sbbh = bo.getSbbh();  //申报表号 
			String htbh = bo.getHtbh();  //合同编号
			String hyfl = bo.getHyfl();  //行业分类
			String nsrmc_buyer = bo.getNsrmc_buyer(); //付款单位
			String nsrmc_seller = bo.getNsrmc_seller(); //收款单位
			String pm = bo.getPm();  //品目 
			String dj= bo.getDj();  //单价
			String sl= bo.getSl();  //数量
			String fwcqzh = bo.getFwcqzh();  //房屋产权证号
			String fwzldz= bo.getFwzldz();  //房屋坐落地址
			String bz = bo.getBz();  //备注
			String wszh = bo.getWszh();  //完税证号码
			
			Fpkc fpkcHm =null;
			Calendar cal = Calendar.getInstance();
			
			//获得存量房信息
			if ( (htbh != null && !htbh.equals("")) && (qshm != null && !qshm.equals("")))
			{
				//判断是否有库存
				ArrayList LastkcList  = ActionUtil.queryMaxFpkc(fpkfdm,fpzldm);
				
				ArrayList fpkcNewList = new ArrayList();
				if(LastkcList != null && LastkcList.size() != 0)
				{
					//（1）插入发票开票主表
					Fpczz fpczz = new Fpczz();
					
					fpczz.setFpzldm(fpzldm);
					fpczz.setFphm(qshm);
					fpczz.setFpkfdm(fpkfdm);
					fpczz.setKprq(now);
					fpczz.setFkdw(nsrmc_buyer);
					fpczz.setSkdw(nsrmc_seller);
					fpczz.setDkdwmc(swjgzzjgmc);
					fpczz.setKplxdm(Constants.FP_KPLX_KP);
					fpczz.setSphm(wszh);
					fpczz.setKpr(kpr);
					fpczz.setSfyjbltp(Constants.FP_TPBZ_WTP);
					fpczz.setCjr(yhr);
					fpczz.setCjrq(now);
					fpczz.setLrr(yhr);
					fpczz.setLrrq(now);
					fpczz.setSwjgzzjgdm(swjgzzjgdm);
					fpczz.setBz(bz);
					fpczz.setDcbz(Constants.FP_DCBZ_WTP);
					fpczz.setHyfl(hyfl);
					fpczz.setFwcqzh(fwcqzh);
					fpczz.setFwzldz(fwzldz);
					
					try
					{
						DAOFactory.getInstance().getFpczzDAO().insert(fpczz, conn);
						Debug.out("发票代开管理：已经将fpkpzb的数据插入到数据库中....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("插入发票开票主表出错！");
					}
					
					//（2）插入发票开票明细
					Fpczmx fpczmx = new Fpczmx();
					
					fpczmx.setFpkfdm(fpczz.getFpkfdm());
					fpczmx.setFpzldm(fpczz.getFpzldm());
					fpczmx.setFphm(qshm);
					fpczmx.setKprq(fpczz.getKprq());
					fpczmx.setPm(pm);
					fpczmx.setDj(new BigDecimal(je));
					fpczmx.setSl(new BigDecimal(sl));
					fpczmx.setJe(new BigDecimal(je));
					fpczmx.setCjr(fpczz.getCjr());
					fpczmx.setCjrq(fpczz.getCjrq());
					fpczmx.setLrr(fpczz.getLrr());
					fpczmx.setLrrq(fpczz.getLrrq());
					fpczmx.setSwjgzzjgdm(swjgzzjgdm);
					fpczz.setBz(bz);
					
					try
					{
						DAOFactory.getInstance().getFpczmxDAO().insert(fpczmx, conn);
						Debug.out("发票代开管理：已经将fpkpmx的数据插入到数据库中....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("插入发票开票明细表出错！");
					}
					
					//（3）插入对照关系表
					Htypzdzgxb htypzdzgxb = new Htypzdzgxb();
					
					htypzdzgxb.setHtbh(htbh);
					htypzdzgxb.setMmfbz(Constants.FP_MMFBZ_SF);
					htypzdzgxb.setPzfldm(Constants.FP_PZFLDM_FP);
					htypzdzgxb.setPizzldm(fpzldm);
					htypzdzgxb.setPzhm(qshm);
					htypzdzgxb.setCjr(yhr);
					htypzdzgxb.setCjrq(now);
					htypzdzgxb.setLrr(yhr);
					htypzdzgxb.setLrrq(now);
					htypzdzgxb.setSbbh(sbbh);
					htypzdzgxb.setSwjgzzjgdm(swjgzzjgdm);
					
					try
					{
						DAOFactory.getInstance().getHtypzdzgxbDAO().insert(htypzdzgxb, conn);
						Debug.out("发票代开管理：已经将htypzdzgxb的数据插入到数据库中....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("插入合同与凭证对照关系表出错！");
					}
					
					//（4）变更库存表
					for (int i = 0; i < LastkcList.size(); i++)
					{
						fpkcHm = new Fpkc();
						Fpkc fpkcItem = (Fpkc) LastkcList.get(i);
						fpkcHm.setQshm(fpkcItem.getQshm());
						fpkcHm.setJzhm(fpkcItem.getJzhm());
						fpkcHm.setJcsj(fpkcItem.getJcsj());
						
						String kcqshm = fpkcHm.getQshm();
						String kcjzhm = fpkcHm.getJzhm();
						
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
						
						int timeInt = df.format(fpkcHm.getJcsj()).compareTo(df.format(now));
						
						//判断时间（若当前系统时间 <= 最新结存时间，插入时间为：最新结存时间+1秒）
						if(!(timeInt <0))
						{
							now = DateUtils.getTimestamp(addSecond(fpkcHm.getJcsj(),1));
						}
						
						//未使用List（库存起始号码>起始号码 或者 库存截止号码<起始号码）
						//if((Long.parseLong(kcqshm) - Long.parseLong(qshm) > 0) || (Long.parseLong(kcjzhm) - Long.parseLong(qshm) < 0))
						if(compareHm(kcqshm,qshm) || compareHm(qshm,kcjzhm))	
						{
							Fpkc fpkcWY =new Fpkc();
							
							fpkcWY.setFpkfdm(fpkcItem.getFpkfdm());
							fpkcWY.setFpzldm(fpkcItem.getFpzldm());
							fpkcWY.setJcsj(now);
							fpkcWY.setQshm(fpkcItem.getQshm());
							fpkcWY.setJzhm(fpkcItem.getJzhm());
							fpkcWY.setSl(fpkcItem.getSl());
							fpkcWY.setSwjgzzjgdm(fpkcItem.getSwjgzzjgdm());
							
							fpkcNewList.add(fpkcWY);
						}
						else
                        {
							// 库存起始号码 < 起始号码
                            if (compareHm(qshm, kcqshm))
                            {
                            	Fpkc fpkcYY =new Fpkc();
                            	
                            	fpkcYY.setFpkfdm(fpkcItem.getFpkfdm());
                            	fpkcYY.setFpzldm(fpkcItem.getFpzldm());
                            	fpkcYY.setJcsj(now);
                            	fpkcYY.setQshm(fpkcItem.getQshm());
                            	fpkcYY.setJzhm(this.calculateHm(qshm, -1));
                            	fpkcYY.setSl(new BigDecimal(this.calculateSl(qshm, fpkcItem.getQshm())));
    							
    							fpkcNewList.add(fpkcYY);
                            }
                            
                            // 库存截止号码 > 起始号码
                            if (compareHm(kcjzhm, qshm))
                            {
                            	Fpkc fpkcYYJ =new Fpkc();
                            	
                            	fpkcYYJ.setFpkfdm(fpkcItem.getFpkfdm());
                            	fpkcYYJ.setFpzldm(fpkcItem.getFpzldm());
                            	fpkcYYJ.setJcsj(now);
                            	fpkcYYJ.setQshm(this.calculateHm(qshm,1));
    							fpkcYYJ.setJzhm(fpkcItem.getJzhm());
    							fpkcYYJ.setSl(new BigDecimal(this.calculateSl(fpkcItem.getJzhm(), qshm)));
    				        	
    							fpkcNewList.add(fpkcYYJ);
                            }
                            
                            // 起始号码 == 库存起始、截止号码
                            if ( equalHm(qshm, kcqshm) && equalHm(qshm, kcjzhm) )
                            {
                            	Fpkc fpkcYW =new Fpkc();
                            	
                            	fpkcYW.setFpkfdm(fpkcItem.getFpkfdm());
                            	fpkcYW.setFpzldm(fpkcItem.getFpzldm());
    							fpkcYW.setJcsj(now);
    							fpkcYW.setQshm("00000000");
    							fpkcYW.setJzhm("00000000");
    							fpkcYW.setSl(new BigDecimal("0"));
    				        	
    							fpkcNewList.add(fpkcYW);
                            }
                        }
							
					}
					
					//通过插入库存表List 与现有库存中List 的集合 将数据写入到库存表。
					for(int index =0; index < fpkcNewList.size() ; index ++)
					{
						Fpkc fpkcNew = new Fpkc();
						Fpkc fpkcNewItem = (Fpkc) fpkcNewList.get(index);
						
						fpkcNew.setFpkfdm(fpkcNewItem.getFpkfdm());
						fpkcNew.setFpzldm(fpkcNewItem.getFpzldm());
						fpkcNew.setJcsj(fpkcNewItem.getJcsj());
						fpkcNew.setQshm(fpkcNewItem.getQshm());
						fpkcNew.setJzhm(fpkcNewItem.getJzhm());
						fpkcNew.setSl(fpkcNewItem.getSl());
						fpkcNew.setSwjgzzjgdm(swjgzzjgdm);
						fpkcNew.setCjr(yhr);
						fpkcNew.setCjrq(now);
						fpkcNew.setLrr(yhr);
						fpkcNew.setLrrq(now);
						fpkcNew.setBz("");
						fpkcNew.setRkbs(Constants.FP_RKBZ_FRK);
						
						//System.out.println("Fpkfdm:"+fpkcNew.getFpkfdm()+" Fpzldm:"+fpkcNew.getFpzldm()+" Jcsj:"+fpkcNew.getJcsj()+" Qshm:"+fpkcNew.getQshm());
						try 
						{
							DAOFactory.getInstance().getFpkcDAO().insert(fpkcNew, conn);
							//Debug.out("发票代开管理：已经将fpkc的数据插入到数据库中....");
						}
						catch (Exception ex) 
						{
							Debug.out(ex);
							throw new ApplicationException("插入发票库存表出错！");
						}
					}
					
					//（5）获取数据打印使用
					try 
					{
						// 增加数据权限控制
			            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
			                    "FPDB", "FP_JL_FPKPZB" );
			            Debug.out("datafilter: " + datafilter);
			            
						//拼接SQL
						String conditions = " and a.fpkfdm = '"+fpkfdm+"' and a.fpzldm = '"+fpzldm+"'  and a.fphm ='"+qshm+"' " +
											"and exists (select 1 from fpdb.fp_jl_fpkpzb t where t.fpzldm=a.fpzldm and t.fphm = a.fphm and "+datafilter+" )";
						ArrayList fpczAllList = DAOFactory.getInstance().getFpczzDAO().queryFpdkPrint(conditions, conn);
						
						bo.getFpkpList().addAll(fpczAllList);
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("查询发票信息出错！");
					}
				}
				else{
					throw new ApplicationException("发票代开信息保存失败！");
				}
			}else{
				throw new ApplicationException("发票代开信息保存失败！");
			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	
	
    /**
     * 计算库起始库存号码
     *
     * @param hm 票证号码
     * @param sl  数量
     * @return  String 起始库存号码
     */
    private String calculateHm(String hm, long sl)
    {
        int size = hm.length();
        long qs = Long.parseLong(hm);
        qs = qs + sl;

        String newQshm = (String.valueOf(qs));
        int newsize = (String.valueOf(qs)).length();
        int zeroCount = size - newsize;

        for (int i = 0; i < zeroCount; i++)
        {
            newQshm = "0" + newQshm;
        }

        return newQshm;
    }

    /**
     * 计算两个号码的差
     *
     * @param hm1 票证号码1
     * @param hm2 票证号码2
     * @return long 两个号码的差
     */
    private long calculateSl(String hm1, String hm2)
    {
        return (Long.parseLong(hm1) - Long.parseLong(hm2));
    }

    /**
     * 比较号码大小
     *
     * @param hm1 票证号码1
     * @param hm2 票证号码2
     * @return boolean  比较号码大小的结果
     */
    private boolean compareHm(String hm1, String hm2)
    {
        return (Long.parseLong(hm1) > Long.parseLong(hm2));
    }
    
    /**
     * 比较号码大小（相等）
     *
     * @param hm1 票证号码1
     * @param hm2 票证号码2
     * @return boolean  比较号码大小的结果
     */
    private boolean equalHm(String hm1, String hm2)
    {
        return (Long.parseLong(hm1) == Long.parseLong(hm2));
    }
    
    
    /**
	 * 加秒
	 * 
	 * @param Timestamp 时间
	 * @param int 加的秒数
	 * @return DATE  返回结果
	 */
	public java.util.Date addSecond(Timestamp timestamp, int seconds) 
	{ 
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(timestamp); 
		calendar.add(Calendar.SECOND, seconds); 
		return calendar.getTime(); 
	} 
	
	
	public static void main(String[] args) 
	{
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Timestamp kcjcsj = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒

		String strNow = df.format(now);
		String strKcjcsj = df.format(kcjcsj);
		
		System.out.println("kcjcsj:"+kcjcsj);
		System.out.println("now:"+now);
		System.out.println("strKcjcsj:"+strNow);
		System.out.println("strNow:"+strKcjcsj);
		
		int timeInt = strKcjcsj.compareTo(strNow);
		if(!(timeInt <0))
		{
			System.out.println("now:"+now); 
			
			//now = DateUtils.getTimestamp(addSecond(kcjcsj,1));
			System.out.println("now:"+now); 
		}
		
	}
	
}
