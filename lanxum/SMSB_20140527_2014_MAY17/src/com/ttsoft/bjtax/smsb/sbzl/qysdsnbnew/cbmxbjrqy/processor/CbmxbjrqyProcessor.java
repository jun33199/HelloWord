/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.cbmxbjrqy.processor;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.cbmxbjrqy.web.CbmxbjrqyForm;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨</p>
 * @author ����ϼ
 * @version 1.1
 */

public class CbmxbjrqyProcessor
implements Processor 
{
	
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
	
	private Object doShow(VOPackage vo) throws BaseException {
		
		CbmxbjrqyForm nbForm = (CbmxbjrqyForm) vo.getData(); //��ò�������
		Connection conn = null;
		try
		{
			//��ȡ���ݿ�����
			conn = SfDBResource.getConnection();
			//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsNewUtil.setQysdsReport(report, nbForm);
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_CBMXBJRQY);
			table.setTableName(CodeConstant.TABLE_NAME_CBMXBJRQY);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(), table);
			//��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_CBMXBJRQY);
			int[] arrs = {1,42};
			nbForm.setDataList(this.translate2Page(QysdsNewUtil.putSpace(table,arrs)));
		}catch(Exception ex)
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally
		{
			SfDBResource.freeConnection(conn);
		}
		return nbForm;
	}
	
	/**
	 * doSave    ���ڱ���ҳ����Ϣ
	 * @param     vo ҵ�����
	 * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException    ��������������׳��쳣��Ϣ
	 */
	private Object doSave(VOPackage vo) throws BaseException
	{
		
		CbmxbjrqyForm cbmxbjrqyForm = (CbmxbjrqyForm)vo.getData();
		Connection con = null;
		try
		{
			//��ȡ���ݿ�����
			con = SfDBResource.getConnection();
			//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(cbmxbjrqyForm);
			
			//��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp=AppAccessFactory.getAInstance(con,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
			//�������״̬Ϊ������ɹ���
			iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally 
		{
			SfDBResource.freeConnection(con);
		}
		return cbmxbjrqyForm;
		
	}
	/**
	 * doDelete    ����ɾ��ҳ������
	 * @param     vo ҵ�����
	 * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException    ��������������׳��쳣��Ϣ
	 */
	private Object doDelete(VOPackage vo) throws BaseException
	{
		CbmxbjrqyForm cbmxbjrqyform = (CbmxbjrqyForm)vo.getData();
		Connection conn = null;
		try
		{
			//��ȡ���ݿ�����
			conn = SfDBResource.getConnection();
			//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(cbmxbjrqyform);
			//��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			try
			{
				iApp.deleteSingleTable(report);	
				iApp.updateCheckStatus(report,"");
				QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
				table.setTableId(CodeConstant.TABLE_ID_CBMXBJRQY);
				table.setTableName(CodeConstant.TABLE_NAME_CBMXBJRQY);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
				table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_CBMXBJRQY);
				int[] arrs = {1,42};
				cbmxbjrqyform.setDataList(this.translate2Page(QysdsNewUtil.putSpace(table,arrs)));
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
		}catch(Exception ex)		
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally
		{
			SfDBResource.freeConnection(conn);
		}
		return cbmxbjrqyform;
	}
	/**
	 * doCheck    ����У��ҳ������
	 * @param     vo ҵ�����
	 * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException    ��������������׳��쳣��Ϣ
	 */
	private Object doCheck(VOPackage vo)throws BaseException
	{
		CbmxbjrqyForm cbmxbjrqyForm = (CbmxbjrqyForm)vo.getData();
		Connection con = null;
		try
		{
			//�������ݿ�����
			con = SfDBResource.getConnection();
			//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(cbmxbjrqyForm);
			/**
			 * ����У��
			 * ͨ�������ִ�У�
			 * δͨ���˳�ִ�У�ҳ����ʾУ������Ϣ
			 */
			Checker checker=CheckerFactory.getAInstance(con,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(con,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			//���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			cbmxbjrqyForm.setCheckList(listSingle);
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
		}catch(Exception ex)
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally
		{
			//�ͷ����ݿ�����
			SfDBResource.freeConnection(con);
		}
		return cbmxbjrqyForm;
	}
	
	/**
	 * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
	 * ҳ�����ݽṹ-->�ӿ����ݽṹ
	 * @param ZcmxbForm 
	 * @return ��ҵ����˰������������
	 */
	private QysdsReportsDeclare translate2Interface(CbmxbjrqyForm nbForm)
	{
		//��ҵ����˰������������
		QysdsReportsDeclare report =new QysdsReportsDeclare();
		QysdsNewUtil.setQysdsReport(report, nbForm); //��report ����һϵ�е�����
		//��ҵ����˰�����ڵ�����������
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_CBMXBJRQY);
		table.setTableName(CodeConstant.TABLE_NAME_CBMXBJRQY);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		
		List list=nbForm.getDataList(); //ȡ�ô�Ų������ݵ�LIST
		
		for(int i=0;i<list.size();i++){
			HashMap map=(HashMap)list.get(i);
			QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
			item.setItemID((String) map.get("hc"));
			item.setItemValue((String) map.get("je"));
			item.setItemType("11");
			table.getCellContentList().put(item.getItemID(), item);			
		}
		report.getTableContentList().put(table.getTableId(),QysdsNewUtil.cleanSpace(table));
		
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
		
		return list;
	}
}