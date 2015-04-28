package com.creationstar.bjtax.qsgl.BizLogic.processor.fpgl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpkc;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpzfBO;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ������Ʊ����</p>
 * <p>Description: ��Ʊ����ģ��ķ�Ʊ���Ϲ���Processor��</p>
 * @author tutu
 * @version 1.1
 */
public class FpzfProcessor implements Processor{

	/**
    *
    * @param vo VOPackage
    * @return Object
    * @throws BaseException
    */
   public Object process(VOPackage vo) throws BaseException {
       Object result = null;

       Debug.out("--Debug-- Info : Here is FpzfProcessor.process(vo)");

       if (vo == null) {
           throw new NullPointerException();
       }
       switch (vo.getAction()) 
       {
       
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
	 * ����
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doSave(VOPackage vo) throws BaseException  
	{
		// ��VOPackage�л������
		FpzfBO bo = (FpzfBO)vo.getData();
		
		Connection conn = null;
		try 
		{
			conn = QSDBUtil.getConnection();
			
			//�õ���ǰʱ��
			Timestamp now = CommonUtil.getDBtime(conn);
			
			//fpdkForm �е� ��Ʊ�����Ϣ
			String swjgzzjgdm = bo.getSwjgzzjgdm(); //˰�������֯��������
			String swjgzzjgmc = bo.getSwjgzzjgmc(); //˰�������֯��������
			String fpkfdm = bo.getFpkfdm();  //��Ʊ�ⷿ����
			String fpzldm = bo.getFpzldm();  //��Ʊ�������
			String qshm = bo.getQshm();  //��ʼ����
			String kpr = bo.getKpr();  //��Ʊ��
			String yhr = bo.getYhid() + bo.getYhmc(); //ʹ����Ա
			
			
			Fpkc fpkc =null;
			
			//��ô�������Ϣ
			if ( qshm != null && !qshm.equals(""))
			{
				//�ж��Ƿ��п��
				ArrayList kcList  = ActionUtil.queryMaxFpkc(fpkfdm,fpzldm);
				
				ArrayList fpkcNewList = new ArrayList();
				if(kcList != null && kcList.size() != 0)
				{
					//��1�����뷢Ʊ��Ʊ����
					Fpczz fpczz = new Fpczz();
					
					fpczz.setFpzldm(fpzldm);
					fpczz.setFphm(qshm);
					fpczz.setFpkfdm(fpkfdm);
					fpczz.setKprq(now);
					fpczz.setDkdwmc(swjgzzjgmc);
					fpczz.setKplxdm(Constants.FP_KPLX_FP);
					fpczz.setKpr(yhr);
					fpczz.setCjr(yhr);
					fpczz.setCjrq(now);
					fpczz.setLrr(yhr);
					fpczz.setLrrq(now);
					fpczz.setSwjgzzjgdm(swjgzzjgdm);
					fpczz.setBz("��Ʊ");
					fpczz.setDcbz(Constants.FP_DCBZ_WTP);
					
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
					
					//��2���������
					for (int i = 0; i < kcList.size(); i++)
					{
						fpkc = new Fpkc();
						Fpkc fpkcItem = (Fpkc) kcList.get(i);
						fpkc.setQshm(fpkcItem.getQshm());
						fpkc.setJzhm(fpkcItem.getJzhm());
						
						String kcqshm = fpkc.getQshm();
						String kcjzhm = fpkc.getJzhm();
						
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
							fpkcWY.setCjr(yhr);
							fpkcWY.setCjrq(now);
							fpkcWY.setLrr(yhr);
							fpkcWY.setLrrq(now);
							fpkcWY.setBz("");
							fpkcWY.setRkbs(Constants.FP_RKBZ_FRK);
							
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
                           	fpkcYY.setSwjgzzjgdm(fpkcItem.getSwjgzzjgdm());
                           	fpkcYY.setCjr(yhr);
                           	fpkcYY.setCjrq(now);
                           	fpkcYY.setLrr(yhr);
                           	fpkcYY.setLrrq(now);
                           	fpkcYY.setBz("");
   							fpkcYY.setRkbs(Constants.FP_RKBZ_FRK);
   							
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
   							fpkcYYJ.setSwjgzzjgdm(fpkcItem.getSwjgzzjgdm());
   							fpkcYYJ.setCjr(yhr);
   							fpkcYYJ.setCjrq(now);
   							fpkcYYJ.setLrr(yhr);
   							fpkcYYJ.setLrrq(now);
   							fpkcYYJ.setBz("");
   							fpkcYYJ.setRkbs(Constants.FP_RKBZ_FRK);
   				        	
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
   							fpkcYW.setSwjgzzjgdm(fpkcItem.getSwjgzzjgdm());
   							fpkcYW.setCjr(yhr);
   							fpkcYW.setCjrq(now);
   							fpkcYW.setLrr(yhr);
   							fpkcYW.setLrrq(now);
   							fpkcYW.setBz("");
   							fpkcYW.setRkbs(Constants.FP_RKBZ_FRK);
   				        	
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
						fpkcNew.setSwjgzzjgdm(fpkcNewItem.getSwjgzzjgdm());
						fpkcNew.setCjr(fpkcNewItem.getCjr());
						fpkcNew.setCjrq(fpkcNewItem.getCjrq());
						fpkcNew.setLrr(fpkcNewItem.getLrr());
						fpkcNew.setLrrq(fpkcNewItem.getLrrq());
						fpkcNew.setBz(fpkcNewItem.getBz());
						fpkcNew.setRkbs(fpkcNewItem.getRkbs());
						
						try 
						{
							DAOFactory.getInstance().getFpkcDAO().insert(fpkcNew, conn);
							Debug.out("��Ʊ���������Ѿ���fpkc�����ݲ��뵽���ݿ���....");
						}
						catch (Exception ex) 
						{
							Debug.out(ex);
							throw new ApplicationException("���뷢Ʊ�������");
						}
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
		}finally {
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
}
