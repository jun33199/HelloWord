package com.creationstar.bjtax.qsgl.BizLogic.processor.fpgl;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpkc;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FplrBO;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ������Ʊ����</p>
 * <p>Description: ��Ʊ����ģ��ķ�Ʊ�Ŷ�¼��Processor��</p>
 * @author tutu
 * @version 1.1
 */
public class FplrProcessor implements Processor {
	
	/**
    *
    * @param vo VOPackage
    * @return Object
    * @throws BaseException
    */
   public Object process(VOPackage vo) throws BaseException {
       Object result = null;

       Debug.out("--Debug-- Info : Here is FplrProcessor.process(vo)");

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
    * �������������
    * VOPackage�е�Data���ԣ���ǰ̨����������FplrBO�����ݶ���
    * @param vo ���ݼ����󣨰���Qswszzֵ�����UserData����
    * @return ��ǰҳ���Ӧ��Form����
    * @throws BaseException
    */
    private Object doSave(VOPackage vo) throws BaseException 
   {
       FplrBO bo = (FplrBO) vo.getData();
       
       // �����list
       ArrayList KcList = new ArrayList();
       
       Connection conn = null;
       try {
    	   conn = QSDBUtil.getConnection();
    	   
    	   //�õ���ǰʱ��
           Timestamp now = CommonUtil.getDBtime(conn);
           
           String fpkfdm = bo.getFpkfdm();
           String yhr = bo.getYhid()+bo.getYhmc();
           String swjgzzjgdm = bo.getSwjgzzjgdm();
           ArrayList fplrList = bo.getFplrList();
           
           //inerst fpkc
           //����Fpkc��Ҫ����ķ�Ʊ����List
           Timestamp newTime = null;
           Fpkc fpkcNew = null;
           Fpkc fpkcOld = null;
           
           for(int i = 0; i < fplrList.size(); i++)
  			{
        	   fpkcNew = new Fpkc();
        	   Fpkc fpkcNewItem = (Fpkc)fplrList.get(i);
        	   
        	   fpkcNew.setFpkfdm(fpkfdm);
        	   fpkcNew.setFpzldm(fpkcNewItem.getFpzldm());
        	   fpkcNew.setJcsj(now);
        	   fpkcNew.setQshm(fpkcNewItem.getQshm());
        	   fpkcNew.setJzhm(fpkcNewItem.getJzhm());
        	   fpkcNew.setSl(fpkcNewItem.getSl());
        	   fpkcNew.setSwjgzzjgdm(swjgzzjgdm);
        	   fpkcNew.setCjr(yhr);
        	   fpkcNew.setCjrq(now);
        	   fpkcNew.setLrr(yhr);
        	   fpkcNew.setLrrq(now);
        	   fpkcNew.setBz("");
        	   fpkcNew.setRkbs(Constants.FP_RKBZ_RK);
        	   
        	   KcList.add(fpkcNew);
  			}
           
           //��Ʊ�������ȥ��
           ArrayList fpzldmList = new ArrayList();
           
           for(int i = 0; i < KcList.size(); i++)
           {
        	   Fpkc fpzlfpkc = new Fpkc();
        	   
        	   Fpkc fpzlItem = (Fpkc)fplrList.get(i);
        	   
        	   fpzlfpkc.setFpzldm(fpzlItem.getFpzldm());
        	   
        	   //�����Ʊ������벻����fpzldmList�У�����룬�����Թ�
        	   if(!fpzldmList.contains(fpzlfpkc.getFpzldm()))
        	   {
        		   fpzldmList.add(fpzlfpkc.getFpzldm());
        	   }
           }
           
           //��ÿ���Ѿ����ڵĿ�����ݣ�����Ϊ1.���ݿ�����ʱ�䣬��ȡ��Ӧ�����п�����ݣ����ҶԻ�õĿ�����������£����¿��ʱ��Ϊ�����²�����ʱ�� now       
           //ѭ����ȡ�������õķ�Ʊ����Ŀⷿ�����ʱ�䣬����ͨ�����ʱ���ҵ���Ӧ�ļ�¼�����������ʱ��Ϊ��ǰʱ�䣬�����²��뵽����
           for(int i = 0; i < fpzldmList.size(); i++)
           {
        	   String queryFpzldm = (String)fpzldmList.get(i);
        	   
               //�õ����µĽ��ʱ��
               try 
               {	
            	   //ƴ��SQL
            	   String conditions = " where fpkfdm = '" + fpkfdm+ "'  and fpzldm = '" + queryFpzldm + "' ";
            	   newTime = DAOFactory.getInstance().getFpkcDAO().queryMaxTime(conn, conditions);
            	   
            	   Debug.out("��Ʊ�Ŷ�¼�룺��ѯ�����ʱ��ɹ�...."+newTime);
            	} 
               catch (Exception ex) 
               {
            		 Debug.out(ex);
            		 throw new ApplicationException("��ѯ�����ʱ�����");
            	}
            	
            	// ���ݷ�Ʊ�ⷿ����fpkfdm����Ʊ�������fpzldm�������ʱ��newTimeStr ��ȡ����ʱ���Ӧ�Ŀ������
            	if(newTime != null)
            	{
            		String newTimeStr = newTime.toString();
            		
            		try 
            		{
            			String conditions = "where fpkfdm = '" + fpkfdm + "'  and fpzldm = '" + queryFpzldm + "' and jcsj = to_date('"+newTimeStr.substring(0,19)+"','yyyy-mm-dd hh24:mi:ss') and sl <>'"+Constants.FP_SL_ZERO+"'  ";
            			ArrayList oldKcList = DAOFactory.getInstance().getFpkcDAO().queryMaxList(conn, conditions);
            			
            			for (int j = 0; j < oldKcList.size(); j++)
            			{
            				fpkcOld = new Fpkc();
            				Fpkc fpkcOldItem = (Fpkc) oldKcList.get(j);
            				
            				fpkcOld.setFpkfdm(fpkcOldItem.getFpkfdm());
            				fpkcOld.setFpzldm(fpkcOldItem.getFpzldm());
            				fpkcOld.setJcsj(now);
            				fpkcOld.setQshm(fpkcOldItem.getQshm());
            				fpkcOld.setJzhm(fpkcOldItem.getJzhm());
            				fpkcOld.setSl(fpkcOldItem.getSl());
            				fpkcOld.setSwjgzzjgdm(fpkcOldItem.getSwjgzzjgdm());
            				fpkcOld.setCjr(fpkcOldItem.getCjr());
            				fpkcOld.setCjrq(fpkcOldItem.getCjrq());
            				fpkcOld.setLrr(yhr);
            				fpkcOld.setLrrq(newTime);
            				fpkcOld.setBz(fpkcOldItem.getBz());
            				fpkcOld.setRkbs(fpkcOldItem.getRkbs());
            				
            				KcList.add(fpkcOld);
            			}
            				Debug.out("��Ʊ�Ŷ�¼�룺��ѯ������¼����....");
            		} 
            		catch (Exception ex) 
            		{
            			Debug.out(ex);
            			throw new ApplicationException("��ѯ������¼����");
            		}
            	}
           }
           
           //ͨ���������List �����п����List �ļ��� ������д�뵽����
           for(int index =0; index < KcList.size() ; index ++)
           {
        	   Fpkc fpkc = new Fpkc();
        	   Fpkc fpkcItem = (Fpkc) KcList.get(index);
        	   
        	   fpkc.setFpkfdm(fpkcItem.getFpkfdm());
        	   fpkc.setFpzldm(fpkcItem.getFpzldm());
        	   fpkc.setJcsj(fpkcItem.getJcsj());
        	   fpkc.setQshm(fpkcItem.getQshm());
        	   fpkc.setJzhm(fpkcItem.getJzhm());
        	   fpkc.setSl(fpkcItem.getSl());
        	   fpkc.setSwjgzzjgdm(fpkcItem.getSwjgzzjgdm());
        	   fpkc.setCjr(fpkcItem.getCjr());
        	   fpkc.setCjrq(fpkcItem.getCjrq());
        	   fpkc.setLrr(fpkcItem.getLrr());
        	   fpkc.setLrrq(fpkcItem.getLrrq());
        	   fpkc.setBz(fpkcItem.getBz());
        	   fpkc.setRkbs(fpkcItem.getRkbs());
        	   
        	   try 
        	   {
        		   DAOFactory.getInstance().getFpkcDAO().insert(fpkc, conn);
        		   Debug.out("��Ʊ�Ŷ�¼�룺�Ѿ���fpkc�����ݲ��뵽���ݿ���....");
        	   } 
        	   catch (Exception ex) 
        	   {
        		   Debug.out(ex);
        		   throw new ApplicationException("���뷢Ʊ�������");
        	   }
			}
        	
       } catch (Exception ex) {
           // ����ʧ����Ϣ:����̨ �� ��־
           Debug.printException(ex);
           ErrorLog.makeLog(vo.getUserData(), "��˰��Ʊ����FplrProcessor����������",new StackMsg2StringUtil(ex,
                   Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");
           throw ExceptionUtil.getBaseException(ex);
       } finally {
           QSDBUtil.freeConnection(conn);
       }
       return bo;
   }
   
}
