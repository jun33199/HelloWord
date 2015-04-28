/**
 *
 */
package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.systmbfqyzcmx.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.check.Checker;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.systmbfqyzcmx.web.SystmbfqyzcmxForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.util.QysdsUtil2008;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:2008����������ҵ����˰����
 * </p>
 *
 * @author Ha Zhengze
 * @version 1.1
 */
public class SystmbfqyzcmxProcessor implements Processor {
	  /**
	   * ʵ��Processor�ӿ�
	   * @param vo ҵ�����
	   * @return Object VOPackage������
	   * @throws BaseException ҵ���쳣
	   * 		1 ���������Ĳ������Ͳ���ʱ�׳�
	   * 		2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
	   * 	�����쳣�׳���EJB��process��������
	   */

	  public Object process(VOPackage vo) throws BaseException {

	    Object result = null;
	    if (vo == null) {
	      throw new NullPointerException();
	    }

	    switch (vo.getAction()) {
	      case CodeConstant.SMSB_SHOWACTION:
	        result = doShow(vo);
	        break;
	      case CodeConstant.SMSB_QUERYACTION:
	        result = doQuery(vo);
	        break;
	      case CodeConstant.SMSB_SAVEACTION:
	        result = doSave(vo);
	        break;
	      case CodeConstant.SMSB_DELETEACTION:
	        result = doDelete(vo);
	        break;
	      case CodeConstant.SMSB_CHECKACTION:
	        result = doCheck(vo);
	        break;
	      default:
	        throw new ApplicationException(
	            "�û�ִ����ϵͳ��֧�ֵķ�������.");
	    }

	    return result;
	  }

	  /**
	   * doShow��ʼ������ҳ����ϢҪ��
	   * @param vo ҵ�����
	   * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	   * @throws BaseException ��������������׳��쳣��Ϣ
	   */

	  private Object doShow(VOPackage vo) throws BaseException 
	  {
	    SystmbfqyzcmxForm nbForm = (SystmbfqyzcmxForm) vo.getData();
	    Connection conn = null;
		try {
			// ��ȡ���ݿ�ӿ�
			conn = SfDBResource.getConnection();
			
			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsUtil2008.setQysdsReport(report,nbForm);
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2008_2_3);
			table.setTableName(CodeConstant.TABLE_NAME_2008_2_3);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(),table);
			// ��ȡ���ݿ�ӿڣ�����query�����������ݲ���
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2008_2_3);
			int []arra={1,14};
			nbForm.setDataList(this.translate2Page(QysdsUtil2008.putSpace(table,arra)));
			
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

	    return nbForm;
	  }

	  /**
	   * doQuery    ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
	   * @param     vo ҵ�����
	   * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	   * @throws BaseException    ��������������׳��쳣��Ϣ
	   *
	   */

	  private Object doQuery(VOPackage vo) throws BaseException {

	   return null;
	  }

	  /**
		 * doSave   ���ڴ洢ҳ���ύ���꾡������Ϣ
		 * @param   vo ҵ�����
		 * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
		 * @throws BaseException ��������������׳��쳣��Ϣ
		 */
		
		private Object doSave(VOPackage vo) throws BaseException 
		{
			SystmbfqyzcmxForm form = (SystmbfqyzcmxForm) vo.getData();
			Connection conn = null;
			try {
				//��ȡ���ݿ�����
				conn = SfDBResource.getConnection();
				
				//��ActionForm�е����ݽṹ6ת���������ݿ�ӿڲ���Ҫ������ݽṹ
				QysdsReportsDeclare report=this.translate2Interface(form);
				IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
				iApp.saveSingleTable(report);
//				�������״̬Ϊ������ɹ���
				iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);
			}catch (Exception ex) { 
				//�׳��쳣
				ex.printStackTrace();
				throw ExceptionUtil.getBaseException(ex);
			}
			finally {
				SfDBResource.freeConnection(conn);
			}
			
			return form;
			
		}
		
		/**
		 * doCheck   ���ڴ洢ҳ���ύ���꾡������Ϣ
		 * @param   vo ҵ�����
		 * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
		 * @throws BaseException ��������������׳��쳣��Ϣ
		 */
		private Object doCheck(VOPackage vo) throws BaseException 
		{
			SystmbfqyzcmxForm SystmbfqyzcmxForm = (SystmbfqyzcmxForm) vo.getData();
			Connection conn = null;
			try
	         {
				//�������ݿ�����
				conn = SfDBResource.getConnection();
				
				//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
				QysdsReportsDeclare report=this.translate2Interface(SystmbfqyzcmxForm);
				
				 //��ȡУ��ӿ�
				Checker checker=CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
				IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
				
				//���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
				List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
				/*���У��ͨ�������ýӿڱ�������*/
				if(listSingle==null ||( listSingle!=null && listSingle.size()==0))
				{
					iApp.saveSingleTable(report);
					//�������״̬Ϊ���������ͨ����
					iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SINGLE_PASS);
				}else if(listSingle.size()>0){
					//�������δͨ��
					iApp.updateCheckStatus(report,"");
				}
				SystmbfqyzcmxForm.setCheckList(listSingle);
			}
			catch (Exception ex)
			{ 
				//�׳��쳣
				ex.printStackTrace();
				throw ExceptionUtil.getBaseException(ex);
			}
			finally
			{
				//�ͷ����ݿ�����
				SfDBResource.freeConnection(conn);
			}
			return SystmbfqyzcmxForm;
		}

	  /**
	   * doDelete  ����ɾ��ҳ���ύ���꾡������Ϣ
	   * @param    vo ҵ�����
	   * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	   * @throws BaseException ��������������׳��쳣��Ϣ
	   */

	  private Object doDelete(VOPackage vo) throws BaseException 
	  {
		  SystmbfqyzcmxForm SystmbfqyzcmxForm=(SystmbfqyzcmxForm)vo.getData();
		  Connection conn = null;
			try {
				// ��ȡ���ݿ�ӿ�
				conn = SfDBResource.getConnection();
				
				// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
	            QysdsReportsDeclare report=this.translate2Interface(SystmbfqyzcmxForm);
				
				//��ȡ���ݿ�ӿڣ�����delete������������ɾ��,iApp����һ��report����
				IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
				iApp.deleteSingleTable(report);
				iApp.updateCheckStatus(report,"");
				QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
				table.setTableId(CodeConstant.TABLE_ID_2008_2_3);
				table.setTableName(CodeConstant.TABLE_NAME_2008_2_3);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
				int []arra={1,14};
				table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2008_2_3);
				SystmbfqyzcmxForm.setDataList(this.translate2Page(QysdsUtil2008.putSpace(table,arra)));
				
			}catch (Exception ex) { 
				//�׳��쳣
				ex.printStackTrace();
				throw ExceptionUtil.getBaseException(ex);
			}
			finally {
				SfDBResource.freeConnection(conn);
			}
		  
	      return SystmbfqyzcmxForm;
	  }

	  /**
	   * doUpdate  ���ڴ洢ҳ���ύ���꾡������Ϣ
	   * @param    vo ҵ�����
	   * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	   * @throws BaseException ��������������׳��쳣��Ϣ
	   */

	  private Object doUpdate(VOPackage vo) throws BaseException {

	    SystmbfqyzcmxForm nbForm = (SystmbfqyzcmxForm) vo.getData();

	    return nbForm;
	  }

	  /**
	   * ��ʼ��
	   * @param nbForm ��������
	   * @throws BaseException ��������������׳��쳣��Ϣ
	   */

	  private void initForm(SystmbfqyzcmxForm nbForm) throws BaseException 
	  {
	   }
	  /**
		 * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
		 * ҳ�����ݽṹ-->�ӿ����ݽṹ
		 * @param SystmbfqyzcmxForm 
		 * @return ��ҵ����˰������������
		 */
	  
	  private QysdsReportsDeclare translate2Interface(SystmbfqyzcmxForm form){
			//��ҵ����˰������������
			QysdsReportsDeclare report =new QysdsReportsDeclare();
			QysdsUtil2008.setQysdsReport(report,form);
			//��ҵ����˰�����ڵ�����������
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2008_2_3);
			table.setTableName(CodeConstant.TABLE_NAME_2008_2_3);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			
			List list=form.getDataList();
			for(int i=0;i<list.size();i++){
				    HashMap map=(HashMap)list.get(i);
					QysdsReportsItemDeclare item=new QysdsReportsItemDeclare();
					String hc=(String)map.get("hc");
					item.setItemID(hc);
					item.setItemValue((String)map.get("je"));
					item.setItemType("11");
					table.getCellContentList().put(item.getItemID(),item);
				  }	
			report.getTableContentList().put(table.getTableId(), QysdsUtil2008.cleanSpace(table));
			return report;
			}
	  /**
		 * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ
		 * �ӿ����ݽṹ-->ҳ�����ݽṹ
		 * @param QysdsReportsTableDeclare
		 * @return ��ҵ����˰������������
		 */
		private List translate2Page(QysdsReportsTableDeclare table){
			
			List list = new ArrayList();
			HashMap map = (HashMap)table.getCellContentList();
			Iterator it=map.keySet().iterator();
			while(it.hasNext()){
				HashMap backMap = new HashMap();
				String key=(String)it.next();
				QysdsReportsItemDeclare item=(QysdsReportsItemDeclare)map.get(key);
				backMap.put("hc", item.getItemID());
				backMap.put("je", item.getItemValue());
				list.add(backMap);
			}
			for(int i=0;i<list.size();i++)
			{
				System.out.println("==2page list content==" + list.get(i));
			}

			return list;
		}
	  }
