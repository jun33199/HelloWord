package com.creationstar.bjtax.qsgl.BizLogic.processor.fpgl;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpdcBO;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
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
 * <p>Description: ��Ʊ����ģ��ķ�Ʊ��������Processor��</p>
 * @author tutu
 * @version 1.1
 */
public class FpdcProcessor implements Processor{

	/**
    *
    * @param vo VOPackage
    * @return Object
    * @throws BaseException
    */
   public Object process(VOPackage vo) throws BaseException {
       Object result = null;

       Debug.out("--Debug-- Info : Here is FpdcProcessor.process(vo)");

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
		FpdcBO bo = (FpdcBO)vo.getData();
		UserData ud = vo.getUserData();
		
		String htbh = SecurityUtil.dealwithStringPara(bo.getHtbh());  //��ͬ���
		String swjgzzjgdm = bo.getSwjgzzjgdm();  //˰�������֯��������
		String fpkfdm = bo.getFpkfdm();  //��Ʊ�ⷿ����
		String fpzldm = bo.getFpzldm();  //��Ʊ�������
		String qshm = SecurityUtil.dealwithStringPara(bo.getQshm());  //��Ʊ�������
		String qsrq = bo.getQsrq();  // ��ʼʱ��
        String jzrq = bo.getJzrq();  // ��ֹʱ��
        String dczt = bo.getDczt();  // ����״̬
        
		Connection conn = null;
		try 
		{	
			// ��ȡ��������
			conn = QSDBUtil.getConnection();
			
			//1.�жϺ�ͬ����Ƿ����
			if((htbh != null) && !htbh.equals(""))
			{
				String fwxxfilter = ZKAdapter.getInstance().getDataFilterString(ud, "QSDB", "QS_JL_FWXXB" );
				String fwxxconditions = " where htbh = '" + htbh+ "' and"+fwxxfilter;
				
				ArrayList fwxxList = DAOFactory.getInstance().getFwxxDAO().queryFwList(conn, fwxxconditions);
				
				if(fwxxList == null || fwxxList.size() == 0)
				{
					bo.setMessagefwx("�޲�ѯ������޶�Ӧ���ݲɼ���Ϣ������û��Ȩ�޲鿴�òɼ���Ϣ!");
					return bo;
				}
			}
			
			//2.��ѯ��Ʊ��Ϣ
            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
                    "FPDB", "FP_JL_FPKPZB" );
            Debug.out("datafilter: " + datafilter);
			
			String conditions = " and b.fpkfdm ='"+fpkfdm+"' and exists (select 1 from fpdb.fp_jl_fpkpzb t " +
								"where t.fpzldm=b.fpzldm and t.fphm = b.fphm and "+datafilter+" ) ";
			
			//��ͬ���
            if (htbh != null && !htbh.equals(""))
            {
            	conditions = conditions + " and a.htbh = '" + htbh + "' and a.pzfldm ='"+Constants.FP_PZFLDM_FP+"' ";
            }

            //��Ʊ����
            if (qshm != null && !qshm.equals(""))
            {
            	conditions = conditions + " and b.fpzldm ='"+fpzldm+"' and b.fphm = '" + qshm + "' ";
            }
            
            if ((qsrq != null) && !qsrq.equals(""))
            {
        		conditions = conditions + " and b.kprq>=to_date('" + qsrq +" 00:00:00','yyyy-mm-dd hh24:mi:ss')";
            }

            if ((jzrq != null) && !jzrq.equals(""))
            {
            	conditions = conditions + " and b.kprq<=to_date('" + jzrq +" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
            }
            
            if ((dczt != null && !dczt.equals("")) && !dczt.equals("-1"))
            {
            	conditions = conditions + " and b.dcbz = '" + dczt + "'  ";
            }
            
            if((htbh == null || htbh.equals("")) && (qshm == null || qshm.equals("")) && (qsrq == null || qsrq.equals("")) && (jzrq == null || jzrq.equals("")) && (dczt != null && !dczt.equals("") && dczt.equals("0")))
            {
            	conditions = conditions + " and b.kprq >= trunc(trunc(sysdate, 'month') - 1, 'month') and b.kprq < trunc(sysdate, 'month') " ;
            }
            
            try
			{
				
				bo.setCxList(DAOFactory.getInstance().getFpczzDAO().queryFpdc(conditions, conn));
				
				if(bo.getCxList() == null || bo.getCxList().size() == 0)
				{
					bo.setMessagefwx("�޲�ѯ������޶�Ӧ��Ʊ��Ϣ������û��Ȩ�޲鿴�÷�Ʊ��Ϣ!");
				}
				
				Debug.out("��Ʊ����������ѯ��Ʊ������Ϣ�ɹ�....");
			}
			catch (Exception ex) 
			{
				Debug.out(ex);
				throw new ApplicationException("��Ʊ����������ѯ��Ʊ������Ϣ����");
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
	 * doSave
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doSave(VOPackage vo) throws BaseException
	{
		// ��VOPackage�л������
		FpdcBO bo = (FpdcBO)vo.getData();
		UserData ud = vo.getUserData();
		
		Connection conn = null;
		try 
		{
			conn = QSDBUtil.getConnection();
			
			//�õ���ǰʱ��
			Timestamp now = CommonUtil.getDBtime(conn);
			
			String fpkfdm = bo.getFpkfdm();  //��Ʊ�ⷿ����
			String swjgzzjgdm = bo.getSwjgzzjgdm();
			String yhr = bo.getYhid() + bo.getYhmc(); //ʹ����Ա
			
			ArrayList cxlist = bo.getCxList();
			
			//����ԭ��Ʊ������Ϣ�����õ�����ʶ��
			for(int i =0; i <cxlist.size(); i++)
			{
				Fpczz cxItem = (Fpczz) cxlist.get(i);
				String fpzldm = cxItem.getFpzldm();  //��Ʊ�������
				String fphm = cxItem.getFphm();  //��Ʊ����
				
				//System.out.println(">>>fpkfdm:"+fpkfdm+" fpzldm:"+fpzldm+" fphm:"+fphm+" yhr:"+yhr);
				
				if(fphm != null && !fphm.equals(""))
				{
					Fpczz fpczzLs = new Fpczz();
					
					fpczzLs.setFpkfdm(fpkfdm);
					fpczzLs.setFpzldm(fpzldm);
					fpczzLs.setFphm(fphm);
					
					fpczzLs.setDcbz(Constants.FP_DCBZ_YTP);
					fpczzLs.setLrr(yhr);
					fpczzLs.setLrrq(now);
					
					try 
					{
						DAOFactory.getInstance().getFpczzDAO().updateDc(fpczzLs, conn);
						Debug.out("��Ʊ���������Ѿ���������־���µ�ԭ��Ʊ��Ʊ������....");
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("���µ�ԭ��Ʊ��Ʊ�������");
					}
					
					//��5����ȡ���ݵ���ʹ��
					try 
					{
						// ��������Ȩ�޿���
			            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
			                    "FPDB", "FP_JL_FPKPZB" );
			            Debug.out("datafilter: " + datafilter);
			            
						//ƴ��SQL
						String conditions = " and b.fpkfdm = '"+fpkfdm+"' and b.fpzldm = '"+fpzldm+"'  and b.fphm ='"+fphm+"'  and b.dcbz='"+Constants.FP_DCBZ_YTP+"' " +
											"and exists (select 1 from fpdb.fp_jl_fpkpzb t where t.fpzldm=b.fpzldm and t.fphm = b.fphm and "+datafilter+" ) ";
						
						ArrayList fpczAllList = DAOFactory.getInstance().getFpczzDAO().queryFpdcData(conditions, conn);
						bo.getFpkpList().addAll(fpczAllList);
						
						bo.setFpkfdm(fpkfdm);
						bo.setSwjgzzjgdm(swjgzzjgdm);
					}
					catch (Exception ex) 
					{
						Debug.out(ex);
						throw new ApplicationException("��ѯ��Ʊ������Ϣ����");
					}
				}
				else
				{
					throw new ApplicationException("��Ʊ������Ϣ����ʧ�ܣ�");
				}
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

}
