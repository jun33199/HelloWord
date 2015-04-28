package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.syax.creports.Constants;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsItemDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsTableDeclare;
import com.syax.creports.check.Checker;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.web.QyqssdsZbForm2014;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.DateUtilToChinese;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsSaveAndCheck;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;


/**
 * 
 * ��Ŀ���ƣ�smsb   
 * �����ƣ�QyqssdsZbProcessor2014   
 * ��������    �л����񹲺͹���ҵ��������˰�걨��
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-2-17 ����12:26:15   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-2-17 ����12:26:15   
 * �޸ı�ע��   
 * @version  1.0
 */
public class QyqssdsZbProcessor2014 implements Processor {

	/**
	 * ʵ��Processor�ӿ�
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return Object VOPackage������
	 * @throws BaseException
	 *             ҵ���쳣 1 ���������Ĳ������Ͳ���ʱ�׳� 2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
	 *             �����쳣�׳���EJB��process��������
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
			throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
		}

		return result;
	}

	/**
	 * doShow��ʼ������ҳ����ϢҪ��
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doShow(VOPackage vo) throws BaseException {
		// ��ȡAction���ݹ���ZbForm����
		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();

			/*----add by huohb,add skssq-----*/
			String ksrq =zbForm.getQssbksrq();
			String jsrq =zbForm.getQssbjsrq();
			String skssq=DateUtilToChinese.convertDate(ksrq, jsrq);
			zbForm.setSkssq(skssq);
			
			// ����QyqssdsReportsDeclare����
			QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();
			// ��form�еĻ�����Ϣת��QyqssdsReportsDeclare report ��
			QyqssdsUtil2014.setQyqssdsReport(report, zbForm);
			// ����QyqssdsReportsTableDeclare�Ļ�����Ϣ
			QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
			table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_ZB);
			table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_ZB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			// ��QyqssdsReportsTableDeclare�Ļ�����Ϣ����QyqssdsReportsDeclare
			report.getTableContentList().put(table.getTableId(), table);

			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// ���ò�ѯ�������в�ѯ
			iApp.querySingleTable(report);
			// ��ȡ��ѯ���ľ�������
			table = (QyqssdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_ZB);
			// �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
			int[] arrs = { 1, 18 };
			zbForm.setDataList(this.translate2Page(QyqssdsUtil2014.putSpace(table,arrs)));
		
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ��ѯ�ɹ�����QyqssdsZbForm2014
		return zbForm;
	}

	/**
	 * doSave ���ڴ洢ҳ���ύ���꾡������Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doSave(VOPackage vo) throws BaseException {
		// �õ�Action���ݹ���QyqssdsZbForm2014����
		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��ActionForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
			QyqssdsReportsDeclare report = this.translate2Interface(zbForm);
			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// ����saveSingleTable�����������ݱ���
			iApp.saveSingleTable(report);
			// �������״̬Ϊ������ɹ���
			iApp.updateCheckStatus(report, Constants.QYQSSDS_SHZT_SAVE);

			//�����걨���е��걨״̬��ʶΪ�����ύδ��ˡ�
			QyqssdsSaveAndCheck.updateFlag(conn,zbForm);
			//���������걨��ʼ���ںͽ�������
			QyqssdsUtil2014.updateAllDate(conn, zbForm);
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ����ɹ�����zbForm
		return zbForm;
	}

	/**
	 * doCheck ����У����ڹ�ϵ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */
	private Object doCheck(VOPackage vo) throws BaseException {
		// �õ�Action���ݹ���QyqssdsZbForm2014����
		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��ActionForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
			QyqssdsReportsDeclare report = this.translate2Interface(zbForm);
			// ��ȡУ��ӿ�
			Checker checker = CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYQSSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// ���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List listSingle = checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			zbForm.setCheckList(listSingle);
			/* ���У��ͨ�������ýӿڱ������� */
			if (listSingle == null || (listSingle != null && listSingle.size() == 0)) {
				//�����걨�ĺ��������ͨ���ĵ���У�鲻��������
				if(zbForm.getSqlx()=="0"||zbForm.getSbShztbs()=="2"){
					
				}else{
					iApp.saveSingleTable(report);
				}
				// �������״̬Ϊ���������ͨ����
				iApp.updateCheckStatus(report,Constants.QYQSSDS_SHZT_SINGLE_PASS);
				// ���ͨ��֮�󱣴��������
				// this.saveJM(vo);
			} else if (listSingle.size() > 0) {
				// �������δͨ��
				iApp.updateCheckStatus(report, "");
			}
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ����ɹ�����cbmxbybqyForm
		return zbForm;
	}

	/**
	 * doDelete ����ɾ��ҳ���ύ���꾡������Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */
	private Object doDelete(VOPackage vo) throws BaseException {

		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��ActionForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
			QyqssdsReportsDeclare report = this.translate2Interface(zbForm);
			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// ����saveSingleTable������������ɾ��
			iApp.deleteSingleTable(report);

			iApp.updateCheckStatus(report, "");

			QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
			table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_ZB);
			table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_ZB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			table = (QyqssdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_ZB);
			// ȡlist
			int[] arrs = { 1, 18 };
			zbForm.setDataList(this.translate2Page(QyqssdsUtil2014.putSpace(table,arrs)));

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}

		return zbForm;
	}

	/**
	 * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ ҳ�����ݽṹ-->�ӿ����ݽṹ
	 * 
	 * @param QyqssdsFb3Form2014
	 * @return ��ҵ����˰���㱨����������
	 */
	private QyqssdsReportsDeclare translate2Interface(QyqssdsZbForm2014 form) {

		// ��ҵ����˰������������
		QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();
		// ��form�еĻ�����Ϣת��QyqssdsReportsDeclare������
		QyqssdsUtil2014.setQyqssdsReport(report, form);
		// ������ҵ����˰�����ڵ����������󣬲����������Ϣ
		QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
		table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_ZB);
		table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_ZB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);

		// ��ҳ�����ݷ�������ݿ�ӿڵ����ݸ�ʽ
		List list = form.getDataList();
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			QyqssdsReportsItemDeclare item = new QyqssdsReportsItemDeclare();
			item.setItemID((String) map.get("hc"));
			item.setItemValue((String) map.get("ljje"));
			item.setItemType("11");
			table.getCellContentList().put(item.getItemID(), item);
		}
		report.getTableContentList().put(table.getTableId(),QyqssdsUtil2014.cleanSpace(table));
		return report;
	}

	/**
	 * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ �ӿ����ݽṹ-->ҳ�����ݽṹ
	 * 
	 * @param QyqssdsReportsTableDeclare
	 * @return ҳ������ݵ�List����
	 */
	private List translate2Page(QyqssdsReportsTableDeclare table) {
		// ����List�����������ҳ�������
		ArrayList pagelist = new ArrayList();
		int i = 0;

		Iterator it = table.getCellContentList().keySet().iterator();
		Debug.out("----start---2014�� ��ҵ����˰���� ����걨����----translate2Page");
		while (it.hasNext()) {
			QyqssdsReportsItemDeclare item = new QyqssdsReportsItemDeclare();
			String key = (String) it.next();
			item = (QyqssdsReportsItemDeclare) table.getCellContentList()
					.get(key);
			HashMap map = new HashMap();
			if(item.getItemID().equals("12")){
				map.put("hc", item.getItemID());
				map.put("ljje", "25");
			}else{
				map.put("hc", item.getItemID());
				map.put("ljje", item.getItemValue());
			}
			pagelist.add(map);
			Debug.out("�дΣ�"+item.getItemID()+"����ֵ��"+item.getItemValue());
		}
		Debug.out("----over---2014�� ��ҵ����˰���� ����걨����----translate2Page");
		return pagelist;
	}
}
