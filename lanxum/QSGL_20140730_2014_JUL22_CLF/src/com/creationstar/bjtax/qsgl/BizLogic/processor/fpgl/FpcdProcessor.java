package com.creationstar.bjtax.qsgl.BizLogic.processor.fpgl;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.HtypzdzgxbLs;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpcdBO;
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
 * <p>Description: ��Ʊ����ģ��ķ�Ʊ�ش���Processor��</p>
 * @author tutu
 * @version 1.1
 */
public class FpcdProcessor implements Processor {

	/**
    *
    * @param vo VOPackage
    * @return Object
    * @throws BaseException
    */
   public Object process(VOPackage vo) throws BaseException {
       Object result = null;

       Debug.out("--Debug-- Info : Here is FpcdProcessor.process(vo)");

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
		FpcdBO bo = (FpcdBO)vo.getData();
		UserData ud = vo.getUserData();
		
		String htbh = SecurityUtil.dealwithStringPara(bo.getHtbh());  //��ͬ���
		String swjgzzjgdm = bo.getSwjgzzjgdm();
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
			
			//��1����ȡ��ѯ���
			String conditions = " and b.fpkfdm ='"+fpkfdm+"' and exists (select 1 from fpdb.fp_jl_fpkpzb t " +
								"where t.fpzldm=b.fpzldm and t.fphm = b.fphm and "+datafilter+" ) ";
			
			//��ͬ���
            if ((htbh != null) && !htbh.equals(""))
            {
            	conditions = conditions + " and a.htbh = '" + htbh + "' and a.pzfldm ='"+Constants.FP_PZFLDM_FP+"' ";
            }
            
            //��Ʊ����
            if ((qshm != null) && !qshm.equals(""))
            {
            	conditions = conditions + " and b.fpzldm ='"+fpzldm+"' and b.fphm = '" +qshm+ "' and b.sfyjbltp ='"+Constants.FP_TPBZ_WTP+"' " +
            			"and b.kplxdm <>'"+Constants.FP_KPLX_FP+"' ";
            }
            
            try
			{
				
				bo.setCxList(DAOFactory.getInstance().getFpczzDAO().queryFpcd(conditions, conn));
				
				if(bo.getCxList() == null || bo.getCxList().size() == 0)
				{
					bo.setMessagefwx("�޲�ѯ������޶�Ӧ��Ʊ��Ϣ������û��Ȩ�޲鿴�÷�Ʊ��Ϣ!");
				}
				
				Debug.out("��Ʊ�ش������ѯ��Ʊ�ش���Ϣ�ɹ�....");
			}
			catch (Exception ex) 
			{
				Debug.out(ex);
				throw new ApplicationException("��Ʊ�ش������ѯ��Ʊ�ش���Ϣ����");
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
		FpcdBO bo = (FpcdBO)vo.getData();
		UserData ud = vo.getUserData();
		
		Connection conn = null;
		try 
		{
			conn = QSDBUtil.getConnection();
			
			//�õ���ǰʱ��
			Timestamp now = CommonUtil.getDBtime(conn);
			
			String fpkfdm = bo.getFpkfdm();  //��Ʊ�ⷿ����
			String cxfpzldm = bo.getCxfpzldm(); //�ش�Ʊ�������
			String cxqshm = bo.getCxqshm();  //�ش�Ʊ����
			String yhr = bo.getYhid() + bo.getYhmc(); //ʹ����Ա
			String swjgzzjgdm = bo.getSwjgzzjgdm();  //˰�������֯�ṹ����
			
			if(cxqshm != null && !cxqshm.equals(""))
			{
				//(1)���ݺ�ͬ��Ų�����չ�ϵ��ʷ��
				HtypzdzgxbLs htypzdzgxbLs = new HtypzdzgxbLs();
				
				htypzdzgxbLs.setHtbh(bo.getHtypzdzgxb().getHtbh());
				htypzdzgxbLs.setSbbh(bo.getHtypzdzgxb().getSbbh());
				htypzdzgxbLs.setMmfbz(bo.getHtypzdzgxb().getMmfbz());
				htypzdzgxbLs.setPzfldm(Constants.FP_PZFLDM_FP);
				htypzdzgxbLs.setPizzldm(cxfpzldm);
				htypzdzgxbLs.setPzhm(cxqshm);
				htypzdzgxbLs.setCjr(yhr);
				htypzdzgxbLs.setCjrq(now);
				htypzdzgxbLs.setLrr(yhr);
				htypzdzgxbLs.setLrrq(now);
				htypzdzgxbLs.setCzr(yhr);
				htypzdzgxbLs.setCzrq(now);
				htypzdzgxbLs.setSwjgzzjgdm(swjgzzjgdm);
				htypzdzgxbLs.setBz("|�ش�(90)");
				
				try 
				{
					DAOFactory.getInstance().getHtypzdzgxbLsDAO().insert(htypzdzgxbLs, conn);
					Debug.out("��Ʊ�ش�����Ѿ��������չ�ϵ�����ݲ��뵽���չ�ϵ��ʷ����....");
				}
				catch (Exception ex) 
				{
					Debug.out(ex);
					throw new ApplicationException("������չ�ϵ��ʷ�����");
				}
			}
			else
			{
				throw new ApplicationException("��Ʊ�ش���Ϣ����ʧ�ܣ�");
			}
			
			//��5����ȡ���ݴ�ӡʹ��
			try 
			{
				// ��������Ȩ�޿���
	            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
	                    "FPDB", "FP_JL_FPKPZB" );
	            Debug.out("datafilter: " + datafilter);
	            
				//ƴ��SQL
				String conditions = " and a.fpkfdm = '"+fpkfdm+"' and a.fpzldm = '"+cxfpzldm+"'  and a.fphm ='"+cxqshm+"'  " +
									"and exists (select 1 from fpdb.fp_jl_fpkpzb t where t.fpzldm=a.fpzldm and t.fphm = a.fphm and "+datafilter+" )";
				ArrayList fpczAllList = DAOFactory.getInstance().getFpczzDAO().queryFpcdPrint(conditions, conn);
				bo.getFpkpList().addAll(fpczAllList);
			}
			catch (Exception ex) 
			{
				Debug.out(ex);
				throw new ApplicationException("��ѯ��Ʊ��Ϣ����");
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
