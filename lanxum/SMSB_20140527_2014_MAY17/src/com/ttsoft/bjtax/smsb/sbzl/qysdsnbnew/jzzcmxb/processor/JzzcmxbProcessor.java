/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jzzcmxb.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jzzcmxb.web.JzzcmxbForm;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
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
 * Description:����˾���֧����ϸ��
 * </p>
 * 
 * @author liwenhua
 * @version 1.1
 */

public class JzzcmxbProcessor implements Processor {

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

		JzzcmxbForm jzzcmxbForm = (JzzcmxbForm) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();

			QysdsReportsDeclare report = new QysdsReportsDeclare();
			// ��form�еĻ�����Ϣת��QysdsReportsDeclare report ��
			QysdsNewUtil.setQysdsReport(report, jzzcmxbForm);
			// ����QysdsReportsTableDeclare�Ļ�����Ϣ
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_JZZCMXB);
			table.setTableName(CodeConstant.TABLE_NAME_JZZCMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			// ��QysdsReportsTableDeclare�Ļ�����Ϣ����QysdsReportsDeclare
			report.getTableContentList().put(table.getTableId(), table);

			// ��ȡ���ݿ�Ӧ�ýӿڣ�����uerySingleTable�����������ݲ�ѯ
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			// ��ȡ��ѯ���ľ�������
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_JZZCMXB);
			// �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
			int[] arrs = { 1, 5, 9, 11, 15, 17, 21, 23, 27, 27, 31, 32 };
			HashMap map = translate2Page(QysdsNewUtil.putSpace(table, arrs));
			jzzcmxbForm.setDataList((ArrayList) map.get("pagelist"));
			jzzcmxbForm.setQekclist((ArrayList) map.get("qekclist"));
			jzzcmxbForm.setBfzskclist((ArrayList) map.get("bfzskclist"));
			jzzcmxbForm.setBfzwkclist((ArrayList) map.get("bfzwkclist"));
			jzzcmxbForm.setBfzydwkclist((ArrayList) map.get("bfzydwkclist"));
			jzzcmxbForm.setFgyjjxlist((ArrayList) map.get("fgyjjxlist"));
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ��ѯ�ɹ�����czqysdsjbForm
		return jzzcmxbForm;
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

		JzzcmxbForm form = (JzzcmxbForm) vo.getData();

		Connection conn = null;

		try {
			// ��ȡ���ݿ�����
			conn = SfDBResource.getConnection();

			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this.translate2Interface(form);
			// ��ȡ���ݿ�Ӧ�ýӿڣ�����saveSingleTable�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
			//�������״̬Ϊ������ɹ���
			iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ����ɹ�����cbmxbybqyForm
		return form;
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

		JzzcmxbForm jzzcmxbForm = (JzzcmxbForm) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this.translate2Interface(jzzcmxbForm);
			// ��ȡУ��ӿ�
			Checker checker = CheckerFactory.getAInstance(conn,
					CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List listSingle = checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			for(int i=0;i<listSingle.size();i++){
				System.out.println("listSingle ������Ϊ��    "+listSingle.get(i));				
			}
			jzzcmxbForm.setCheckList(listSingle);
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
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ����ɹ�����cbmxbybqyForm
		return jzzcmxbForm;
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

		JzzcmxbForm jzzcmxbForm = (JzzcmxbForm) vo.getData();

		Connection conn = null;

		try {
			// ��ȡ���ݿ�����

			conn = SfDBResource.getConnection();

			// ��ActionForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this.translate2Interface(jzzcmxbForm);
			// ��ȡ���ݿ�Ӧ�ýӿڣ�����saveSingleTable������������ɾ��
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report);
			iApp.updateCheckStatus(report,"");
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_JZZCMXB);
			table.setTableName(CodeConstant.TABLE_NAME_JZZCMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_JZZCMXB);
			// ȡlist
			int[] arrs = { 1, 5, 9, 11, 15, 17, 21, 23, 27, 27, 31, 32 };
			HashMap map = translate2Page(QysdsNewUtil.putSpace(table, arrs));
			jzzcmxbForm.setDataList((ArrayList) map.get("pagelist"));
			jzzcmxbForm.setQekclist((ArrayList) map.get("qekclist"));
			jzzcmxbForm.setBfzskclist((ArrayList) map.get("bfzskclist"));
			jzzcmxbForm.setBfzwkclist((ArrayList) map.get("bfzwkclist"));
			jzzcmxbForm.setBfzydwkclist((ArrayList) map.get("bfzydwkclist"));
			jzzcmxbForm.setFgyjjxlist((ArrayList) map.get("fgyjjxlist"));

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}

		return jzzcmxbForm;
	}

	/**
	 * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ ҳ�����ݽṹ-->�ӿ����ݽṹ
	 * 
	 * @param ZcmxbForm
	 * @return ��ҵ����˰������������
	 */
	private QysdsReportsDeclare translate2Interface(JzzcmxbForm form) {

		// ��ҵ����˰������������
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		// ��form�еĻ�����Ϣת��QysdsReportsDeclare������
		QysdsNewUtil.setQysdsReport(report, form);

		// ������ҵ����˰�����ڵ����������󣬲����������Ϣ
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_JZZCMXB);
		table.setTableName(CodeConstant.TABLE_NAME_JZZCMXB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);

		// ��ҳ�����ݷ�������ݿ�ӿڵ����ݸ�ʽ
		List list = form.getDataList();
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);

			String hc = (String) map.get("hc");
			if ("1".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("1");
				item_1.setItemValue((String) map.get("L2"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("2");
				item_2.setItemValue((String) map.get("L5"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);
			}
			if ("2".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("3");
				item_1.setItemValue((String) map.get("L2"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("4");
				item_2.setItemValue((String) map.get("L4"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);

				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID("5");
				item_3.setItemValue((String) map.get("L5"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			}
			if ("3".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("9");
				item_1.setItemValue((String) map.get("L2"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("10");
				item_2.setItemValue((String) map.get("L4"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);

				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID("11");
				item_3.setItemValue((String) map.get("L5"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			}
			if ("4".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("15");
				item_1.setItemValue((String) map.get("L2"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("16");
				item_2.setItemValue((String) map.get("L4"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);

				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID("17");
				item_3.setItemValue((String) map.get("L5"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			}
			if ("5".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("21");
				item_1.setItemValue((String) map.get("L2"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("22");
				item_2.setItemValue((String) map.get("L4"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);

				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID("23");
				item_3.setItemValue((String) map.get("L5"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			}
			if ("6".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("27");
				item_1.setItemValue((String) map.get("L2"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
			if ("7".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("31");
				item_1.setItemValue((String) map.get("L2"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("32");
				item_2.setItemValue((String) map.get("L4"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);
			}
		}

		List qekcList = this.filterList(form.getQekclist(), "qekc_L1",
				"qekc_L2", "qekc_L3");
		for (int i = 0; i < qekcList.size(); i++) {
			HashMap map = (HashMap) qekcList.get(i);
			if (qekcList.size() == 1) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("6");
				item_1.setItemValue((String) map.get("qekc_L1"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("7");
				item_2.setItemValue((String) map.get("qekc_L2"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);

				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID("8");
				item_3.setItemValue((String) map.get("qekc_L3"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			} else {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("6" + "." + String.valueOf(i + 1));
				item_1.setItemValue((String) map.get("qekc_L1"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("7" + "." + String.valueOf(i + 1));
				item_2.setItemValue((String) map.get("qekc_L2"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);

				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID("8" + "." + String.valueOf(i + 1));
				item_3.setItemValue((String) map.get("qekc_L3"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			}
		}

		List bfzskcList = this.filterList(form.getBfzskclist(), "bfzskc_L1",
				"bfzskc_L2", "bfzskc_L3");
		for (int i = 0; i < bfzskcList.size(); i++) {
			HashMap map = (HashMap) bfzskcList.get(i);
			if (bfzskcList.size() == 1) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("12");
				item_1.setItemValue((String) map.get("bfzskc_L1"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("13");
				item_2.setItemValue((String) map.get("bfzskc_L2"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);

				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID("14");
				item_3.setItemValue((String) map.get("bfzskc_L3"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			} else {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("12" + "." + String.valueOf(i + 1));
				item_1.setItemValue((String) map.get("bfzskc_L1"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("13" + "." + String.valueOf(i + 1));
				item_2.setItemValue((String) map.get("bfzskc_L2"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);

				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID("14" + "." + String.valueOf(i + 1));
				item_3.setItemValue((String) map.get("bfzskc_L3"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			}

		}

		List bfzwkcList = this.filterList(form.getBfzwkclist(), "bfzwkc_L1",
				"bfzwkc_L2", "bfzwkc_L3");
		for (int i = 0; i < bfzwkcList.size(); i++) {
			HashMap map = (HashMap) bfzwkcList.get(i);
			if (bfzwkcList.size() == 1) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("18");
				item_1.setItemValue((String) map.get("bfzwkc_L1"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("19");
				item_2.setItemValue((String) map.get("bfzwkc_L2"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);

				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID("20");
				item_3.setItemValue((String) map.get("bfzwkc_L3"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			} else {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("18" + "." + String.valueOf(i + 1));
				item_1.setItemValue((String) map.get("bfzwkc_L1"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("19" + "." + String.valueOf(i + 1));
				item_2.setItemValue((String) map.get("bfzwkc_L2"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);

				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID("20" + "." + String.valueOf(i + 1));
				item_3.setItemValue((String) map.get("bfzwkc_L3"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			}

		}

		List bfzydwkcList = this.filterList(form.getBfzydwkclist(),
				"bfzydwkc_L1", "bfzydwkc_L2", "bfzydwkc_L3");
		for (int i = 0; i < bfzydwkcList.size(); i++) {
			HashMap map = (HashMap) bfzydwkcList.get(i);
			if (bfzydwkcList.size() == 1) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("24");
				item_1.setItemValue((String) map.get("bfzydwkc_L1"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("25");
				item_2.setItemValue((String) map.get("bfzydwkc_L2"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);

				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID("26");
				item_3.setItemValue((String) map.get("bfzydwkc_L3"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			} else {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("24" + "." + String.valueOf(i + 1));
				item_1.setItemValue((String) map.get("bfzydwkc_L1"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("25" + "." + String.valueOf(i + 1));
				item_2.setItemValue((String) map.get("bfzydwkc_L2"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);

				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID("26" + "." + String.valueOf(i + 1));
				item_3.setItemValue((String) map.get("bfzydwkc_L3"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			}

		}

		List fgyjjxList = this.filterList(form.getFgyjjxlist(), "fgyjjx_L1",
				"fgyjjx_L2", "fgyjjx_L3");
		for (int i = 0; i < fgyjjxList.size(); i++) {
			HashMap map = (HashMap) fgyjjxList.get(i);
			if (fgyjjxList.size() == 1) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("28");
				item_1.setItemValue((String) map.get("fgyjjx_L1"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("29");
				item_2.setItemValue((String) map.get("fgyjjx_L2"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);

				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID("30");
				item_3.setItemValue((String) map.get("fgyjjx_L3"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			} else {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("28" + "." + String.valueOf(i + 1));
				item_1.setItemValue((String) map.get("fgyjjx_L1"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("29" + "." + String.valueOf(i + 1));
				item_2.setItemValue((String) map.get("fgyjjx_L2"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);

				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID("30" + "." + String.valueOf(i + 1));
				item_3.setItemValue((String) map.get("fgyjjx_L3"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			}

		}

		report.getTableContentList().put(table.getTableId(),
				QysdsNewUtil.cleanSpace(table));

		return report;
	}

	/**
	 * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ �ӿ����ݽṹ-->ҳ�����ݽṹ
	 * 
	 * @param QysdsReportsTableDeclare
	 * @return ҳ������ݵ�map����
	 */
	private HashMap translate2Page(QysdsReportsTableDeclare table) {

		// �����̶��е����ݶ�Ӧ�� map
		HashMap map1 = new HashMap();
		HashMap map2 = new HashMap();
		HashMap map3 = new HashMap();
		HashMap map4 = new HashMap();
		HashMap map5 = new HashMap();
		HashMap map6 = new HashMap();
		HashMap map7 = new HashMap();
		
		// ����List�����������ҳ����̶��е�����
		ArrayList pagelist = new ArrayList();

		map1.put("hc", "1");
		map1.put("L1", "*");
		map1.put("L2", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("1")).getItemValue());
		map1.put("L3", "*");
		map1.put("L4", "*");
		map1.put("L5", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("2")).getItemValue());
		pagelist.add(map1);

		map2.put("hc", "2");
		map2.put("L1", "*");
		map2.put("L2", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("3")).getItemValue());
		map2.put("L3", "100%");
		map2.put("L4", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("4")).getItemValue());
		map2.put("L5", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("5")).getItemValue());
		pagelist.add(map2);

		map3.put("hc", "3");
		map3.put("L1", "*");
		map3.put("L2", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("9")).getItemValue());
		map3.put("L3", "10%");
		map3.put("L4", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("10")).getItemValue());
		map3.put("L5", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("11")).getItemValue());
		pagelist.add(map3);

		map4.put("hc", "4");
		map4.put("L1", "*");
		map4.put("L2", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("15")).getItemValue());
		map4.put("L3", "3%");
		map4.put("L4", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("16")).getItemValue());
		map4.put("L5", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("17")).getItemValue());
		pagelist.add(map4);

		map5.put("hc", "5");
		map5.put("L1", "*");
		map5.put("L2", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("21")).getItemValue());
		map5.put("L3", "1.50%");
		map5.put("L4", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("22")).getItemValue());
		map5.put("L5", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("23")).getItemValue());
		pagelist.add(map5);

		map6.put("hc", "6");
		map6.put("L1", "*");
		map6.put("L2", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("27")).getItemValue());
		map6.put("L3", "*");
		map6.put("L4", "*");
		map6.put("L5", "*");
		pagelist.add(map6);

		map7.put("hc", "7");
		map7.put("L1", "*");
		map7.put("L2", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("31")).getItemValue());
		map7.put("L3", "*");
		map7.put("L4", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("32")).getItemValue());
		map7.put("L5", "*");
		pagelist.add(map7);

		// ���table.getCellContentList()
		Map Qekc_1 = this.getCellMap(table, "6");
		Map Qekc_2 = this.getCellMap(table, "7");
		Map Qekc_3 = this.getCellMap(table, "8");

		Map Bfzskc_1 = this.getCellMap(table, "12");
		Map Bfzskc_2 = this.getCellMap(table, "13");
		Map Bfzskc_3 = this.getCellMap(table, "14");

		Map Bfzwkc_1 = this.getCellMap(table, "18");
		Map Bfzwkc_2 = this.getCellMap(table, "19");
		Map Bfzwkc_3 = this.getCellMap(table, "20");

		Map Bfzydwkc_1 = this.getCellMap(table, "24");
		Map Bfzydwkc_2 = this.getCellMap(table, "25");
		Map Bfzydwkc_3 = this.getCellMap(table, "26");

		Map Fgyjjx_1 = this.getCellMap(table, "28");
		Map Fgyjjx_2 = this.getCellMap(table, "29");
		Map Fgyjjx_3 = this.getCellMap(table, "30");

		// ����Ϊ�ȳ���Map,���õ���Ҫ���Ե�ҳ�����������Ӧ��List
		List qekclist = this.processMap(Qekc_1, Qekc_2, Qekc_3, "6", "7", "8",
				"qekc_L1", "qekc_L2", "qekc_L3", "qekc_L4", "qekc_L5",
				"qekc_L6");
		List bfzskclist = this.processMap(Bfzskc_1, Bfzskc_2, Bfzskc_3, "12",
				"13", "14", "bfzskc_L1", "bfzskc_L2", "bfzskc_L3", "bfzskc_L4",
				"bfzskc_L5", "bfzskc_L6");
		List bfzwkclist = this.processMap(Bfzwkc_1, Bfzwkc_2, Bfzwkc_3, "18",
				"19", "20", "bfzwkc_L1", "bfzwkc_L2", "bfzwkc_L3", "bfzwkc_L4",
				"bfzwkc_L5", "bfzwkc_L6");
		List bfzydwkclist = this.processMap(Bfzydwkc_1, Bfzydwkc_2, Bfzydwkc_3,
				"24", "25", "26", "bfzydwkc_L1", "bfzydwkc_L2", "bfzydwkc_L3",
				"bfzydwkc_L4", "bfzydwkc_L5", "bfzydwkc_L6");
		List fgyjjxlist = this.processMap(Fgyjjx_1, Fgyjjx_2, Fgyjjx_3, "28",
				"29", "30", "fgyjjx_L1", "fgyjjx_L2", "fgyjjx_L3", "fgyjjx_L4",
				"fgyjjx_L5", "fgyjjx_L6");

		// �����л��Ե�ҳ�����������Ӧ��List ����һ��map����
		HashMap totalmap = new HashMap();
		totalmap.put("pagelist", pagelist);
		totalmap.put("qekclist", qekclist);
		totalmap.put("bfzskclist", bfzskclist);
		totalmap.put("bfzwkclist", bfzwkclist);
		totalmap.put("bfzydwkclist", bfzydwkclist);
		totalmap.put("fgyjjxlist", fgyjjxlist);

		return totalmap;
	}

	// �����е�Ԫ����з���,�������е�Ԫ���Ӧ��map
	private HashMap getCellMap(QysdsReportsTableDeclare table, String flag) {
		HashMap map = new HashMap();
		Iterator it = table.getCellContentList().keySet().iterator();

		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				if (flag.equals(key)) {
					map.put(key, table.getCellContentList().get(key));
					System.out.println("flag----" + flag
							+ "          key--------" + key);
				}
			} else if (flag.equals(key.substring(0, key.indexOf(".")))) {
				String est = key.substring(0, key.indexOf("."));
				System.out.println("est ----------" + est);
				map.put(key, table.getCellContentList().get(key));
				System.out.println("flag----" + flag + "          key--------"
						+ key);
			}
		}

		return map;
	}

	// �˷����������ж�Ӧ��List
	private List processMap(Map map1, Map map2, Map map3, String keyFlag1,
			String keyFlag2, String keyFlag3, String L1, String L2, String L3,
			String L4, String L5, String L6) {

		boolean flagMuti = false;
		List list = new ArrayList();

		// ������map����������Ӧ�����������ж�
		Iterator it = map1.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		it = map2.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		it = map3.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		// ��������ж�����Ϊ��,������������ȫ��Ϊ��,���������
		if (flagMuti == false && map1.size() == 0 && map2.size() == 0
				&& map3.size() == 0) {

			for (int i = 0; i < 3; i++) {
				Map rowMap = new HashMap();
				rowMap.put(L1, "");
				rowMap.put(L2, "");
				rowMap.put(L3, "");
				rowMap.put(L4, "*");
				rowMap.put(L5, "*");
				rowMap.put(L6, "*");
				list.add(rowMap);
			}
			return list;

			// ���������ж�����,������ֻ��һ��,����������
		} else if (flagMuti == false) {
			// ��һ�����ݺͶ�������
			Map rowMap = new HashMap();
			rowMap.put(L1, map1.get(keyFlag1) == null ? ""
					: ((QysdsReportsItemDeclare) map1.get(keyFlag1))
							.getItemValue());
			rowMap.put(L2, map2.get(keyFlag2) == null ? ""
					: ((QysdsReportsItemDeclare) map2.get(keyFlag2))
							.getItemValue());
			rowMap.put(L3, map3.get(keyFlag3) == null ? ""
					: ((QysdsReportsItemDeclare) map3.get(keyFlag3))
							.getItemValue());
			rowMap.put(L4, "*");
			rowMap.put(L5, "*");
			rowMap.put(L6, "*");
			list.add(rowMap);

			Map rowMap1 = new HashMap();
			rowMap1.put(L1, "");
			rowMap1.put(L2, "");
			rowMap1.put(L3, "");
			rowMap1.put(L4, "*");
			rowMap1.put(L5, "*");
			rowMap1.put(L6, "*");
			list.add(rowMap1);

			Map rowMap2 = new HashMap();
			rowMap2.put(L1, "");
			rowMap2.put(L2, "");
			rowMap2.put(L3, "");
			rowMap2.put(L4, "*");
			rowMap2.put(L5, "*");
			rowMap2.put(L6, "*");
			list.add(rowMap2);
			return list;
		}

		// ������������ж�����,������Ϊ���� ,���������ݲ���
		if (flagMuti == true) {
			int max = 0;
			String[] arr;
			String temp = new String("0");
			// ȡmap1���±�����ֵ,�����е���Ŀ
			if (map1.size() != 0) {
				it = map1.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				max = getMax(arr);
			}
			// ȡmap2���±����ֵ,����map1�е��±�Ƚ�,ȡ�����еĴ�ֵ
			if (map2.size() != 0) {
				it = map2.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				int max2 = this.getMax(arr);
				if (max <= max2) {
					max = max2;
				}
			}
			// ȡmap3���±����ֵ,����ǰ�߱Ƚ�,ȡ�����еĴ�ֵ
			if (map3.size() != 0) {
				it = map3.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				int max3 = this.getMax(arr);
				if (max <= max3) {
					max = max3;
				}
			}

			// �������е�����

			for (int i = 0; i < max; i++) {
				Map rowMap = new HashMap();
				String key1 = keyFlag1 + "." + String.valueOf(i + 1);
				String key2 = keyFlag2 + "." + String.valueOf(i + 1);
				String key3 = keyFlag3 + "." + String.valueOf(i + 1);
				rowMap.put(L1, map1.get(key1) == null ? ""
						: ((QysdsReportsItemDeclare) map1.get(key1))
								.getItemValue());
				rowMap.put(L2, map2.get(key2) == null ? ""
						: ((QysdsReportsItemDeclare) map2.get(key2))
								.getItemValue());
				rowMap.put(L3, map3.get(key3) == null ? ""
						: ((QysdsReportsItemDeclare) map3.get(key3))
								.getItemValue());
				rowMap.put(L4, "*");
				rowMap.put(L5, "*");
				rowMap.put(L6, "*");
				list.add(rowMap);
			}
			// ������е���ĿΪ2��,�����һ����������
			Map rowMap = new HashMap();
			if (max == 2) {
				rowMap.put(L1, "");
				rowMap.put(L2, "");
				rowMap.put(L3, "");
				rowMap.put(L4, "*");
				rowMap.put(L5, "*");
				rowMap.put(L6, "*");
				list.add(rowMap);
			}
		}

		return list;
	}

	// ȡ������������Ԫ��map�±�����ֵ,�Դ�ֵΪ׼�������е�����
	private int getMax(String[] arr) {
		int i = arr.length;
		int temp = 0;
		for (int j = 0; j < i; j++) {
			if (temp < Integer.parseInt(arr[j])) {
				temp = Integer.parseInt(arr[j]);
			}
		}
		return temp;
	}

	/**
	 * ����ҳ��List�еĿ�ֵ
	 * 
	 * @param list
	 */
	private List filterList(List list, String L1, String L2, String L3) {
		List rtnList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			String strL1 = (String) map.get(L1);
			String strL2 = (String) map.get(L2);
			String strL3 = (String) map.get(L3);
			if (!strL1.equals("") || !strL2.equals("") || !strL3.equals("")) {
				rtnList.add(map);
			}
		}
		return rtnList;
	}

}
