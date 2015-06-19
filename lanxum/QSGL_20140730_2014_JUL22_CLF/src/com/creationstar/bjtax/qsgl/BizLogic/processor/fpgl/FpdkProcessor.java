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
 * <p>Title: ������˰��������ϵͳ������Ʊ����</p>
 * <p>Description: ��Ʊ����ģ��ķ�Ʊ��������Processor��</p>
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
                   "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
       }

       return result;
   }
   
   /**
    * ��ʾ��ʼҳ��
    * @param vo
    * @return
    * @throws BaseException
    */
	private Object doShow(VOPackage vo) throws BaseException 
	{
		// ��VOPackage�л������
		FpdkBO bo = (FpdkBO)vo.getData();
		
		Connection conn = null;
		try 
		{
			conn = QSDBUtil.getConnection();
			
			String fpkfdm = bo.getFpkfdm(); //��Ʊ�ⷿ����
			
			ArrayList fpzlList = bo.getFpzlList(); //������з�Ʊ�����ķ�Ʊ����List
			
			ArrayList KcList = new ArrayList();  // �����list
			
			Timestamp newTime = null;
			
			//���fpzlList�е�һ����Ʊ�������
			if ((fpzlList != null) && (fpzlList.size() > 0))
			{
				Fpzl fpzl = new Fpzl();
				Fpzl fpzlItem = (Fpzl)fpzlList.get(0);
				fpzl.setFpzldm(fpzlItem.getFpzldm());
				
				//�õ����µĽ��ʱ��
				try 
				{
					//ƴ��SQL
					String conditions = " where fpkfdm = '" + fpkfdm+ "'  and fpzldm = '" + fpzl.getFpzldm() + "' ";
					
					newTime = DAOFactory.getInstance().getFpkcDAO().queryMaxTime(conn, conditions);
					
					Debug.out("��Ʊ����������ѯ�����ʱ��ɹ�...."+newTime);
				}
				catch (Exception ex) 
				{
					Debug.out(ex);
					throw new ApplicationException("��ѯ�����ʱ�����");
				}
				
				// ���ݷ�Ʊ�ⷿ����fpkfdm����Ʊ�������fpzldm�������ʱ��newTimeStr ��ȡ����ʱ���Ӧ����ʼ����
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
							Debug.out("��Ʊ����������ѯ������¼����....");
						}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("��ѯ������¼����");
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
	 * ��ѯ
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doQuery(VOPackage vo) throws BaseException 
	{
		// ��VOPackage�л������
		FpdkBO bo = (FpdkBO)vo.getData();
		UserData ud = vo.getUserData();
		
		// ����wszh
		String wszh = "";
		// ���巵����Ϣ
		String message = "";
		
		Connection conn = null;
		CallableStatement proc = null;
		try 
		{	
			// ��ȡ��������
			conn = QSDBUtil.getConnection();
			
			String htbh = SecurityUtil.dealwithStringPara(bo.getHtbh());  //��ͬ���
			String fpkfdm = bo.getFpkfdm();  //��Ʊ�ⷿ����
			String fpzldm = bo.getFpzldm();  //��Ʊ�������
			String swjgzzjgdm = bo.getSwjgzzjgdm();  //˰�������֯��������
			ArrayList fpzlList = bo.getFpzlList(); //������з�Ʊ�����ķ�Ʊ����List
			Timestamp newTime = null;
			ArrayList KcList = new ArrayList();  // �����list
			
			//��ô�������Ϣ
			if (htbh != null && !htbh.equals(""))
			{
				//��1�����fpzlList�е�һ����Ʊ�������
				if (fpzlList != null && fpzlList.size() > 0)
				{
					Fpzl fpzl = new Fpzl();
					Fpzl fpzlItem = (Fpzl)fpzlList.get(0);
					fpzl.setFpzldm(fpzlItem.getFpzldm());
					
					//�õ����µĽ��ʱ��
					try 
					{
						//ƴ��SQL
						String conditions = " where fpkfdm = '" + fpkfdm+ "'  and fpzldm = '" + fpzl.getFpzldm() + "' ";
						
						newTime = DAOFactory.getInstance().getFpkcDAO().queryMaxTime(conn, conditions);
						
						Debug.out("��Ʊ����������ѯ�����ʱ��ɹ�...."+newTime);
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("��ѯ�����ʱ�����");
					}
					
					// ���ݷ�Ʊ�ⷿ����fpkfdm����Ʊ�������fpzldm�������ʱ��newTimeStr ��ȡ����ʱ���Ӧ����ʼ����
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
								Debug.out("��Ʊ����������ѯ������¼����....");
							}
						catch (Exception ex) 
						{
							Debug.out(ex);
							throw new ApplicationException("��ѯ������¼����");
						}
					}
					bo.setFpkcList(KcList);
				}
				
				
				//��2�������˰ƾ֤��Ϣ
				proc = conn.prepareCall("{ call qsdb.qs_pkg_clfjy.returnwspzh(?,?,?,?) }");
				int k = 1;
				// �������
				proc.setString(k++,htbh);
				proc.setString(k++,"");
				
				// ���ڲ���
				proc.registerOutParameter(k++, Types.VARCHAR);
				proc.registerOutParameter(k++, Types.VARCHAR);
				// ���ô洢����
				proc.execute();
				
				// ��ȡwsz
				wszh = proc.getString(3);
				message = proc.getString(4);
				
				if(message != null && !message.equals("") && message.substring(0, 1).equals("0"))
				{
					bo.setMessage(message);
				}
				
				bo.setWszh(wszh);
				Debug.out("��Ʊ����������ѯ��˰ƾ֤��Ϣ�ɹ�....");
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
				
				//��3����ѯ���ݲ�Ȩ֤�š����������ַ�����ۡ�����Сд�ϼ�
				try 
				{
					Fwxx fwxx =null;
					
					// ��������Ȩ�޿���
		            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
		                    "QSDB", "QS_JL_FWXXB" );
		            Debug.out("datafilter: " + datafilter);
		            
					//ƴ��SQL
					String conditions = " where htbh = '" + htbh+ "' and"+datafilter;
					
					ArrayList fwxxList = DAOFactory.getInstance().getFwxxDAO().queryFwList(conn, conditions);
					if(fwxxList == null || fwxxList.size() == 0)
					{
						bo.setMessagefwx("�޲�ѯ������޶�Ӧ���ݲɼ���Ϣ������û��Ȩ�޲鿴�òɼ���Ϣ!");
						return bo;
					}
					
	  				DecimalFormat deFormat = new DecimalFormat("#0.00");// �ϼƽ���ʽ
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
						
						bo.setSbbh(fwxx.getSbbh()); //�걨���
						bo.setFwcqzh(fwxx.getFwcqzh()); //���ݲ�Ȩ֤��
						bo.setFwzldz(fwxx.getFwzldz()); //���������ַ
						bo.setHtzj(String.valueOf(deFormat.format(fwxx.getHtzj())));  //��ͬ���
						bo.setXxhj(String.valueOf(deFormat.format(fwxx.getHtzj())));  // Сд���ϼ�
						bo.setDxhj(Currency.convert((fwxx.getHtzj().doubleValue())).substring(1));  //��д���ϼ�
						
						Debug.out("��Ʊ����������ѯ������Ϣ�ɹ�....");
					}
				}
				catch (Exception ex) 
				{
					Debug.out(ex);
					throw new ApplicationException("��ѯ������Ϣ����");
				}
				
				//��4����ѯ�˶��۸�
				try 
				{
					Fwhdxx fwhdxx  = null;
					
					//ƴ��SQL
					String conditions = " where htbh = '" + htbh+ "'";
					
					ArrayList fwhdxxList = DAOFactory.getInstance().getFwhdxxbDAO().query(conn, conditions);
					
					DecimalFormat deFormat = new DecimalFormat("#0.00");// �ϼƽ���ʽ
					if((fwhdxxList != null) && (fwhdxxList.size() != 0))
					{
						for (int j = 0; j < fwhdxxList.size(); j++)
						{
							fwhdxx = new Fwhdxx();
							Fwhdxx fwhdxxItem = (Fwhdxx) fwhdxxList.get(j);
        				
							fwhdxx.setHdjg(fwhdxxItem.getHdjg());

						}
						bo.setHdjg(DataConvert.BigDecimal2String(fwhdxx.getHdjg(),2,false)); //�˶��۸�
						
						//System.out.println("BO�˶��۸�:"+bo.getHdjg());
						
						Debug.out("��Ʊ����������ѯ˰����غ˶��۸���Ϣ�ɹ�....");
					}
				}
				catch (Exception ex) 
				{
					Debug.out(ex);
					throw new ApplicationException("��ѯ˰����غ˶��۸���Ϣ����");
				}
				
				
				//��5����ѯ�տλ-����-��Ϣ(����ж������ʹ�ÿո����)
				try 
				{
					String mfgrxxSellers = "";
					MfgrxxSeller mfgrxxSeller  = null;
					
					//ƴ��SQL
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
						bo.setNsrmc_seller(mfgrxxSellers); //�տλ
						
						Debug.out("��Ʊ����������ѯ����������Ϣ�ɹ�....");
					}
				}
				catch (Exception ex) 
				{
					Debug.out(ex);
					throw new ApplicationException("��ѯ����������Ϣ����");
				}
				
				
				//��6����ѯ�տλ-��-��Ϣ(����ж������ʹ�ÿո����)
				try 
				{
					String mfgrxxBuyers = "";
					MfgrxxBuyer mfgrxxBuyer  = null;
					
					//ƴ��SQL
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
						
						bo.setNsrmc_buyer(mfgrxxBuyers); //�տλ
						
						Debug.out("��Ʊ����������ѯ�򷽸�����Ϣ�ɹ�....");
					}
				}
				catch (Exception ex) 
				{
					Debug.out(ex);
					throw new ApplicationException("��ѯ�򷽸�����Ϣ����");
				}

			}else{
				throw new ApplicationException("��Ʊ������Ϣ��ѯʧ�ܣ�");
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
	 * ����
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doSave(VOPackage vo) throws BaseException  
	{
		// ��VOPackage�л������
		FpdkBO bo = (FpdkBO)vo.getData();
		
		UserData ud = vo.getUserData();
		
		bo.setFpkpList(new ArrayList());
		Connection conn = null;
		try 
		{
			conn = QSDBUtil.getConnection();
			
			String qshm = bo.getQshm();  //��ʼ����
			String htbh = bo.getHtbh();  //��ͬ���
			String fpkfdm = bo.getFpkfdm();  //��Ʊ�ⷿ����
			String fpzldm = bo.getFpzldm();  //��Ʊ�������
			
			double je = Double.valueOf(bo.getJe()).doubleValue();//�ܽ��
			int pageNum = bo.getPageNum();//��ӡ����
			
			//�����ӡ����Ϊ�ջ���0�򣬱���
			if(pageNum ==0)
			{
				throw new ApplicationException("��ӡ����Ϊ��0");
			}
			
			//��ô�������Ϣ
			if ( (htbh != null && !htbh.equals("")) && (qshm != null && !qshm.equals("")))
			{
				//�ж��Ƿ��п��
				ArrayList fpkcList  = ActionUtil.queryMaxFpkc2(fpkfdm,fpzldm,qshm);
				
				//���һ��  ֱ�Ӵ�
				if(pageNum == 1)
				{
					saveFPXX(bo,ud, conn,qshm,je+"");
					return bo;
				}
				
				//�ж϶���
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
							
							//��ȡkcList��һ����¼��ǰ̨¼�����ʼ����������������������������ֹ����ĺ������м䣩
							
							int index_0_jzhm_INT = Integer.valueOf(fpkc.getJzhm()).intValue();
							int index_0_kcsl_INT = (index_0_jzhm_INT - qshm_next)+1;
							
							//1���ж�kcList�ĵ�һ����¼�������Ƿ�С��pageNum  Ҫ��ӡ������
							
							//1.1�������С�ڣ�����ӡ������ֱ�Ӽ�һ��ӡ��һ��
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
									//System.out.println("��һ��>>> current_je:"+current_je+"  qshm_next:"+calculateHm(qshm,index));
									saveFPXX(bo,ud, conn,calculateHm(qshm,index),current_je+"");
								}
								return bo;
							}
							else{//1.2�����������ӡ ���Ȱ�һ����¼�е�Ʊ���� 1.1�Ĺ���ѵ�ǰ�����Ժ��Ʊʹ���꣬Ȼ������kcList��һ����¼��һ���������
								//�ѵ�һ����¼���������ʼ���뿪ʼ֮�������Ʊ����
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
									//System.out.println("��һ��>>> current_je:"+current_je+"  qshm_next:"+calculateHm(qshm,index));
									saveFPXX(bo,ud, conn,calculateHm(qshm,index),current_je+"");
								}
								
								pageNum = pageNum - index_0_kcsl_INT;
								//System.out.println("��һ�к�>>> pageNum:"+pageNum);
								
								//����һ����¼
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
										
										//System.out.println("����һ��>>> current_je:"+current_je+"  qshm_next_index:"+calculateHm(fpkc.getQshm(),innerInd));
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
							throw new ApplicationException("�������ѯʧ�ܣ�");
						}
					}
					else{
					throw new ApplicationException("��ӡ����������1�ţ�");
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
			
			//�õ���ǰʱ��
			Timestamp now = CommonUtil.getDBtime(conn);
			
			//fpdkForm �е� ��Ʊ�����Ϣ
			String swjgzzjgdm = bo.getSwjgzzjgdm(); //˰�������֯��������
			String swjgzzjgmc = bo.getSwjgzzjgmc(); //˰�������֯��������
			String fpkfdm = bo.getFpkfdm();  //��Ʊ�ⷿ����
			String fpzldm = bo.getFpzldm();  //��Ʊ�������
			String kpr = bo.getKpr();  //��Ʊ��
			String yhr = bo.getYhid() + bo.getYhmc(); //ʹ����Ա
			
			//fpdkForm �е� �����������Ϣ
			String sbbh = bo.getSbbh();  //�걨��� 
			String htbh = bo.getHtbh();  //��ͬ���
			String hyfl = bo.getHyfl();  //��ҵ����
			String nsrmc_buyer = bo.getNsrmc_buyer(); //���λ
			String nsrmc_seller = bo.getNsrmc_seller(); //�տλ
			String pm = bo.getPm();  //ƷĿ 
			String dj= bo.getDj();  //����
			String sl= bo.getSl();  //����
			String fwcqzh = bo.getFwcqzh();  //���ݲ�Ȩ֤��
			String fwzldz= bo.getFwzldz();  //���������ַ
			String bz = bo.getBz();  //��ע
			String wszh = bo.getWszh();  //��˰֤����
			
			Fpkc fpkcHm =null;
			Calendar cal = Calendar.getInstance();
			
			//��ô�������Ϣ
			if ( (htbh != null && !htbh.equals("")) && (qshm != null && !qshm.equals("")))
			{
				//�ж��Ƿ��п��
				ArrayList LastkcList  = ActionUtil.queryMaxFpkc(fpkfdm,fpzldm);
				
				ArrayList fpkcNewList = new ArrayList();
				if(LastkcList != null && LastkcList.size() != 0)
				{
					//��1�����뷢Ʊ��Ʊ����
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
						Debug.out("��Ʊ���������Ѿ���fpkpzb�����ݲ��뵽���ݿ���....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("���뷢Ʊ��Ʊ�������");
					}
					
					//��2�����뷢Ʊ��Ʊ��ϸ
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
						Debug.out("��Ʊ���������Ѿ���fpkpmx�����ݲ��뵽���ݿ���....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("���뷢Ʊ��Ʊ��ϸ�����");
					}
					
					//��3��������չ�ϵ��
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
						Debug.out("��Ʊ���������Ѿ���htypzdzgxb�����ݲ��뵽���ݿ���....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("�����ͬ��ƾ֤���չ�ϵ�����");
					}
					
					//��4���������
					for (int i = 0; i < LastkcList.size(); i++)
					{
						fpkcHm = new Fpkc();
						Fpkc fpkcItem = (Fpkc) LastkcList.get(i);
						fpkcHm.setQshm(fpkcItem.getQshm());
						fpkcHm.setJzhm(fpkcItem.getJzhm());
						fpkcHm.setJcsj(fpkcItem.getJcsj());
						
						String kcqshm = fpkcHm.getQshm();
						String kcjzhm = fpkcHm.getJzhm();
						
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�����ʽ������ʾ����
						
						int timeInt = df.format(fpkcHm.getJcsj()).compareTo(df.format(now));
						
						//�ж�ʱ�䣨����ǰϵͳʱ�� <= ���½��ʱ�䣬����ʱ��Ϊ�����½��ʱ��+1�룩
						if(!(timeInt <0))
						{
							now = DateUtils.getTimestamp(addSecond(fpkcHm.getJcsj(),1));
						}
						
						//δʹ��List�������ʼ����>��ʼ���� ���� ����ֹ����<��ʼ���룩
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
							// �����ʼ���� < ��ʼ����
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
                            
                            // ����ֹ���� > ��ʼ����
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
                            
                            // ��ʼ���� == �����ʼ����ֹ����
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
					
					//ͨ���������List �����п����List �ļ��� ������д�뵽����
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
							//Debug.out("��Ʊ���������Ѿ���fpkc�����ݲ��뵽���ݿ���....");
						}
						catch (Exception ex) 
						{
							Debug.out(ex);
							throw new ApplicationException("���뷢Ʊ�������");
						}
					}
					
					//��5����ȡ���ݴ�ӡʹ��
					try 
					{
						// ��������Ȩ�޿���
			            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
			                    "FPDB", "FP_JL_FPKPZB" );
			            Debug.out("datafilter: " + datafilter);
			            
						//ƴ��SQL
						String conditions = " and a.fpkfdm = '"+fpkfdm+"' and a.fpzldm = '"+fpzldm+"'  and a.fphm ='"+qshm+"' " +
											"and exists (select 1 from fpdb.fp_jl_fpkpzb t where t.fpzldm=a.fpzldm and t.fphm = a.fphm and "+datafilter+" )";
						ArrayList fpczAllList = DAOFactory.getInstance().getFpczzDAO().queryFpdkPrint(conditions, conn);
						
						bo.getFpkpList().addAll(fpczAllList);
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("��ѯ��Ʊ��Ϣ����");
					}
				}
				else{
					throw new ApplicationException("��Ʊ������Ϣ����ʧ�ܣ�");
				}
			}else{
				throw new ApplicationException("��Ʊ������Ϣ����ʧ�ܣ�");
			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	
	
    /**
     * �������ʼ������
     *
     * @param hm Ʊ֤����
     * @param sl  ����
     * @return  String ��ʼ������
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
     * ������������Ĳ�
     *
     * @param hm1 Ʊ֤����1
     * @param hm2 Ʊ֤����2
     * @return long ��������Ĳ�
     */
    private long calculateSl(String hm1, String hm2)
    {
        return (Long.parseLong(hm1) - Long.parseLong(hm2));
    }

    /**
     * �ȽϺ����С
     *
     * @param hm1 Ʊ֤����1
     * @param hm2 Ʊ֤����2
     * @return boolean  �ȽϺ����С�Ľ��
     */
    private boolean compareHm(String hm1, String hm2)
    {
        return (Long.parseLong(hm1) > Long.parseLong(hm2));
    }
    
    /**
     * �ȽϺ����С����ȣ�
     *
     * @param hm1 Ʊ֤����1
     * @param hm2 Ʊ֤����2
     * @return boolean  �ȽϺ����С�Ľ��
     */
    private boolean equalHm(String hm1, String hm2)
    {
        return (Long.parseLong(hm1) == Long.parseLong(hm2));
    }
    
    
    /**
	 * ����
	 * 
	 * @param Timestamp ʱ��
	 * @param int �ӵ�����
	 * @return DATE  ���ؽ��
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�����ʽ������ʾ����

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
