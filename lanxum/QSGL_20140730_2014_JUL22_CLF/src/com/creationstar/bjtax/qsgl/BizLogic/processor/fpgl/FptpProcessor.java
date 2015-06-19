package com.creationstar.bjtax.qsgl.BizLogic.processor.fpgl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.HtypzdzgxbLs;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpkc;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FptpBO;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
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

/**
 * <p>Title: ������˰��������ϵͳ������Ʊ����</p>
 * <p>Description: ��Ʊ����ģ��ķ�Ʊ��Ʊ����Processor��</p>
 * @author tutu
 * @version 1.1
 */
public class FptpProcessor implements Processor {

	/**
    *
    * @param vo VOPackage
    * @return Object
    * @throws BaseException
    */
   public Object process(VOPackage vo) throws BaseException {
       Object result = null;

       Debug.out("--Debug-- Info : Here is FptpProcessor.process(vo)");

       if (vo == null) {
           throw new NullPointerException();
       }
       switch (vo.getAction()) 
       {
       		
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
	 * ��ѯ
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doQuery(VOPackage vo) throws BaseException 
	{
		// ��VOPackage�л������
		FptpBO bo = (FptpBO)vo.getData();
		UserData ud = vo.getUserData();
		
		String htbh = SecurityUtil.dealwithStringPara(bo.getHtbh());  //��ͬ���
		String fpkfdm = bo.getFpkfdm();  //��Ʊ�ⷿ����
		String fpzldm = bo.getFpzldm();  //��Ʊ�������
		String qshm = SecurityUtil.dealwithStringPara(bo.getQshm());  //��Ʊ�������
		
		Connection conn = null;
		try 
		{	
			// ��ȡ��������
			conn = QSDBUtil.getConnection();
			
			//1.�жϺ�ͬ����Ƿ����
			if((htbh != null) && !htbh.equals(""))
			{
				String fwxxfilter = ZKAdapter.getInstance().getDataFilterString(ud, "QSDB", "QS_JL_FWXXB" );
				String fwxxconditions = " where htbh = '" + htbh + "' and"+fwxxfilter;
				
				ArrayList fwxxList = DAOFactory.getInstance().getFwxxDAO().queryFwList(conn, fwxxconditions);
				
				if(fwxxList == null || fwxxList.size() == 0)
				{
					bo.setMessagefwx("�޲�ѯ������޶�Ӧ���ݲɼ���Ϣ������û��Ȩ�޲鿴�òɼ���Ϣ!");
					return bo;
				}
			}
			
			//2.��ѯ��Ʊ��Ϣ
            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud, "FPDB", "FP_JL_FPKPZB" );
            Debug.out("datafilter: " + datafilter);
			
			String conditions = " and b.fpkfdm ='"+fpkfdm+"' and exists (select 1 from fpdb.fp_jl_fpkpzb t " +
								"where t.fpzldm=b.fpzldm and t.fphm = b.fphm and "+datafilter+" ) ";
			
			//��ͬ���
            if ((htbh != null) && !htbh.equals(""))
            {
            	conditions = conditions + " and a.htbh = '" + htbh + "'";
            }

            //��Ʊ����
            if ((qshm != null) && !qshm.equals(""))
            {
            	conditions = conditions + " and b.fpzldm ='"+fpzldm+"' and b.fphm = '" + qshm + "' and b.kplxdm ='"+Constants.FP_KPLX_KP+"' ";
            }
            
            try
			{
				
				bo.setCxList(DAOFactory.getInstance().getFpczzDAO().queryFptp(conditions, conn));
				
				if(bo.getCxList() == null || bo.getCxList().size() == 0)
				{
					bo.setMessagefwx("�޲�ѯ������޶�Ӧ��Ʊ��Ϣ������û��Ȩ�޲鿴�÷�Ʊ��Ϣ!");
				}
				
				Debug.out("��Ʊ��Ʊ������ѯ��Ʊ��Ʊ��Ϣ�ɹ�....");
			}
			catch (Exception ex) 
			{
				Debug.out(ex);
				throw new ApplicationException("��Ʊ��Ʊ������ѯ��Ʊ��Ʊ��Ϣ����");
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

	private Object doSave(VOPackage vo) throws BaseException
	{
		// ��VOPackage�л������
		FptpBO bo = (FptpBO)vo.getData();
		UserData ud = vo.getUserData();
		
		Connection conn = null;
		try 
		{
			conn = QSDBUtil.getConnection();
			
			//�õ���ǰʱ��
			Timestamp now = CommonUtil.getDBtime(conn);
			
			String swjgzzjgdm = bo.getSwjgzzjgdm(); //˰�������֯��������
			String swjgzzjgmc = bo.getSwjgzzjgmc(); //˰�������֯��������
			String fpkfdm = bo.getFpkfdm();  //��Ʊ�ⷿ����
			String cxfpzldm = bo.getCxfpzldm(); //��Ʊ��Ʊ�������
			String cxqshm = bo.getCxqshm();  //��Ʊ��Ʊ����
			String tpfpzldm = bo.getTpfpzldm(); //��Ʊ��Ʊ�������
			String tpqshm = bo.getTpqshm();  //��Ʊ��Ʊ����
			String kpr =bo.getKpr();  //��Ʊ��
			String yhr = bo.getYhid() + bo.getYhmc(); //ʹ����Ա

			Fpczz fpczzb = null;
			Fpczmx fpczmxb = null;
			Fpkc fpkcb =null;
			
			if((cxqshm != null && !cxqshm.equals("")) && (tpqshm != null && !tpqshm.equals("")))
			{
				
				//�ж��Ƿ��п��
				ArrayList kcList  = ActionUtil.queryMaxFpkc(fpkfdm,tpfpzldm);
				
				ArrayList fpkcNewList = new ArrayList();
				if(kcList != null && kcList.size() != 0)
				{

					//(1)���ݷ�Ʊ��������չ�ϵ��ʷ��
					HtypzdzgxbLs htypzdzgxbLs = new HtypzdzgxbLs();
					
					htypzdzgxbLs.setYbxh(bo.getHtypzdzgxb().getXh());
					htypzdzgxbLs.setHtbh(bo.getHtypzdzgxb().getHtbh());
					htypzdzgxbLs.setSbbh(bo.getHtypzdzgxb().getSbbh());
					htypzdzgxbLs.setMmfbz(bo.getHtypzdzgxb().getMmfbz());
					htypzdzgxbLs.setPzfldm(bo.getHtypzdzgxb().getPzfldm());
					htypzdzgxbLs.setPizzldm(bo.getHtypzdzgxb().getPizzldm());
					htypzdzgxbLs.setPzhm(bo.getHtypzdzgxb().getPzhm());
					htypzdzgxbLs.setPzndzb(bo.getHtypzdzgxb().getPzndzb());
					htypzdzgxbLs.setCjr(bo.getHtypzdzgxb().getCjr());
					htypzdzgxbLs.setCjrq(bo.getHtypzdzgxb().getCjrq());
					htypzdzgxbLs.setLrr(bo.getHtypzdzgxb().getLrr());
					htypzdzgxbLs.setLrrq(bo.getHtypzdzgxb().getLrrq());
					htypzdzgxbLs.setCzr(yhr);
					htypzdzgxbLs.setCzrq(now);
					htypzdzgxbLs.setSwjgzzjgdm(swjgzzjgdm);
					htypzdzgxbLs.setBz("|��Ʊ(2)");
					
					try 
					{
						DAOFactory.getInstance().getHtypzdzgxbLsDAO().insert(htypzdzgxbLs, conn);
						Debug.out("��Ʊ��Ʊ�����Ѿ��������չ�ϵ�����ݲ��뵽���չ�ϵ��ʷ����....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("������չ�ϵ��ʷ�����");
					}
					
					//��2�����뷢Ʊ��Ʊ����
					fpczzb = new Fpczz();
					
					fpczzb.setFpzldm(tpfpzldm);
					fpczzb.setFphm(tpqshm);
					fpczzb.setFpkfdm(fpkfdm);
					fpczzb.setKprq(now);
					fpczzb.setFkdw(bo.getFpczz().getFkdw());
					fpczzb.setSkdw(bo.getFpczz().getSkdw());
					fpczzb.setDkdwmc(swjgzzjgmc);
					fpczzb.setKplxdm(Constants.FP_KPLX_TP);
					fpczzb.setSphm(bo.getFpczz().getSphm());
					fpczzb.setKpr(kpr);
					fpczzb.setSfyjbltp(Constants.FP_TPBZ_WTP);
					fpczzb.setCjr(yhr);
					fpczzb.setCjrq(now);
					fpczzb.setLrr(yhr);
					fpczzb.setLrrq(now);
					fpczzb.setSwjgzzjgdm(swjgzzjgdm);
					fpczzb.setBz(bo.getFpczz().getBz());
					fpczzb.setDcbz(Constants.FP_DCBZ_WTP);
					fpczzb.setHyfl(bo.getFpczz().getHyfl());
					fpczzb.setFwcqzh(bo.getFpczz().getFwcqzh());
					fpczzb.setFwzldz(bo.getFpczz().getFwzldz());
					
					try
					{
						DAOFactory.getInstance().getFpczzDAO().insert(fpczzb, conn);
						Debug.out("��Ʊ��Ʊ�����Ѿ���fpkpzb�����ݲ��뵽���ݿ���....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("���뷢Ʊ��Ʊ�������");
					}
					
					//��3�����뷢Ʊ��Ʊ��ϸ
					fpczmxb = new Fpczmx();
					
					fpczmxb.setFpkfdm(fpczzb.getFpkfdm());
					fpczmxb.setFpzldm(fpczzb.getFpzldm());
					fpczmxb.setFphm(fpczzb.getFphm());
					fpczmxb.setKprq(fpczzb.getKprq());
					fpczmxb.setPm(bo.getFpczmx().getPm());
					fpczmxb.setDj(new BigDecimal("-"+bo.getFpczmx().getDj()));
					fpczmxb.setSl(bo.getFpczmx().getSl());
					fpczmxb.setJe(new BigDecimal("-"+bo.getFpczmx().getJe()));
					fpczmxb.setCjr(fpczzb.getCjr());
					fpczmxb.setCjrq(fpczzb.getCjrq());
					fpczmxb.setLrr(fpczzb.getLrr());
					fpczmxb.setLrrq(fpczzb.getLrrq());
					fpczmxb.setSwjgzzjgdm(fpczzb.getSwjgzzjgdm());
					
					try
					{
						DAOFactory.getInstance().getFpczmxDAO().insert(fpczmxb, conn);
						Debug.out("��Ʊ��Ʊ�����Ѿ���fpkpmx�����ݲ��뵽���ݿ���....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("���뷢Ʊ��Ʊ��ϸ�����");
					}
					
					//��4���������
					for (int i = 0; i < kcList.size(); i++)
					{
						fpkcb = new Fpkc();
						Fpkc fpkcItem = (Fpkc) kcList.get(i);
						fpkcb.setQshm(fpkcItem.getQshm());
						fpkcb.setJzhm(fpkcItem.getJzhm());
						fpkcb.setJcsj(fpkcItem.getJcsj());
						
						String kcqshm = fpkcb.getQshm();
						String kcjzhm = fpkcb.getJzhm();
						
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�����ʽ������ʾ����
						
						int timeInt = df.format(fpkcb.getJcsj()).compareTo(df.format(now));
						
						//�ж�ʱ�䣨����ǰϵͳʱ�� <= ���½��ʱ�䣬����ʱ��Ϊ�����½��ʱ��+1�룩
						if(!(timeInt <0))
						{
							now = DateUtils.getTimestamp(addSecond(fpkcb.getJcsj(),1));
						}
						
						//δʹ��List�������ʼ����>��ʼ���� ���� ����ֹ����<��ʼ���룩
						//if((Long.parseLong(kcqshm) - Long.parseLong(cxqshm) > 0) || (Long.parseLong(kcjzhm) - Long.parseLong(cxqshm) < 0))
						if(compareHm(kcqshm,tpqshm) || compareHm(tpqshm,kcjzhm))	
						{
							Fpkc fpkcWY =new Fpkc();
							
							fpkcWY.setFpkfdm(fpkcItem.getFpkfdm());
							fpkcWY.setFpzldm(fpkcItem.getFpzldm());
							fpkcWY.setJcsj(now);
							fpkcWY.setQshm(fpkcItem.getQshm());
							fpkcWY.setJzhm(fpkcItem.getJzhm());
							fpkcWY.setSl(fpkcItem.getSl());
							
							fpkcNewList.add(fpkcWY);
						}
						else
                        {
							// �����ʼ���� < ��ʼ����
                            if (compareHm(tpqshm, kcqshm))
                            {
                            	Fpkc fpkcYY =new Fpkc();
                            	
                            	fpkcYY.setFpkfdm(fpkcItem.getFpkfdm());
                            	fpkcYY.setFpzldm(fpkcItem.getFpzldm());
                            	fpkcYY.setJcsj(now);
                            	fpkcYY.setQshm(fpkcItem.getQshm());
                            	fpkcYY.setJzhm(this.calculateHm(tpqshm, -1));
                            	fpkcYY.setSl(new BigDecimal(this.calculateSl(tpqshm, fpkcItem.getQshm())));
    							
    							fpkcNewList.add(fpkcYY);
                            }
                            
                            // ����ֹ���� > ��ʼ����
                            if (compareHm(kcjzhm, tpqshm))
                            {
                            	Fpkc fpkcYYJ =new Fpkc();
                            	
                            	fpkcYYJ.setFpkfdm(fpkcItem.getFpkfdm());
                            	fpkcYYJ.setFpzldm(fpkcItem.getFpzldm());
                            	fpkcYYJ.setJcsj(now);
                            	fpkcYYJ.setQshm(this.calculateHm(tpqshm,1));
    							fpkcYYJ.setJzhm(fpkcItem.getJzhm());
    							fpkcYYJ.setSl(new BigDecimal(this.calculateSl(fpkcItem.getJzhm(), tpqshm)));
    				        	
    							fpkcNewList.add(fpkcYYJ);
                            }
                            
                            // ��ʼ���� == �����ʼ����ֹ����
                            if ( equalHm(tpqshm, kcqshm) && equalHm(tpqshm, kcjzhm) )
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
							Debug.out("��Ʊ��Ʊ�����Ѿ���fpkc�����ݸ��µ����ݿ���....");
						}
						catch (Exception ex) 
						{
							Debug.out(ex);
							throw new ApplicationException("���·�Ʊ�������");
						}
					}
					
					
					//��5������ԭ��Ʊ������Ϣ����������Ʊ��ʶ��
					
					Fpczz fpczzLs = new Fpczz();
					
					fpczzLs.setFpkfdm(fpkfdm);
					fpczzLs.setFpzldm(bo.getHtypzdzgxb().getPizzldm());
					fpczzLs.setFphm(bo.getHtypzdzgxb().getPzhm());
					
					fpczzLs.setTpfpzldm(tpfpzldm);
					fpczzLs.setTpfphm(tpqshm);
					fpczzLs.setSfyjbltp(Constants.FP_TPBZ_YTP);
					fpczzLs.setLrr(yhr);
					fpczzLs.setLrrq(now);
					
					try 
					{
						DAOFactory.getInstance().getFpczzDAO().updateTp(fpczzLs, conn);
						Debug.out("��Ʊ��Ʊ�����Ѿ�����Ʊ���ݸ��µ�ԭ��Ʊ��Ʊ������....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("���µ�ԭ��Ʊ��Ʊ�������");
					}
					
					//��6��ɾ����ͬ��ƾ֤���չ�ϵ������
					try 
					{
						String condition = " where pzfldm = '"+Constants.FP_PZFLDM_FP+"' and pizzldm = '"+bo.getHtypzdzgxb().getPizzldm()+"' and pzhm ='"+bo.getHtypzdzgxb().getPzhm()+"' ";
						DAOFactory.getInstance().getHtypzdzgxbDAO().delete(condition, conn);
						Debug.out("��Ʊ��Ʊ�����Ѿ�ɾ����ͬ��ƾ֤���չ�ϵ������....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("ɾ����ͬ��ƾ֤���չ�ϵ�����ݳ���");
					}
					
					//��7����ȡ���ݴ�ӡʹ��
					try 
					{
						// ��������Ȩ�޿���
			            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
			                    "FPDB", "FP_JL_FPKPZB" );
			            Debug.out("datafilter: " + datafilter);
						
						//ƴ��SQL
						String conditions = " and b.fpkfdm = '"+fpkfdm+"' and b.fpzldm = '"+tpfpzldm+"'  and b.fphm ='"+tpqshm+"' " +
											"and exists (select 1 from fpdb.fp_jl_fpkpzb t where t.fpzldm=a.fpzldm and t.fphm = a.fphm and "+datafilter+" )";
						ArrayList fpczAllList = DAOFactory.getInstance().getFpczzDAO().queryFptpPrint(conditions, conn);
						bo.getFpkpList().addAll(fpczAllList);
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("��ѯ��Ʊ��Ϣ����");
					}
				}
				else{
					throw new ApplicationException("����ؿ�棡");
				}
				
			}
			else
			{
				throw new ApplicationException("��Ʊ��Ʊ��Ϣ����ʧ�ܣ�");
			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally 
		{
            QSDBUtil.freeConnection(conn);
        }	
		return bo;
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
	
}	