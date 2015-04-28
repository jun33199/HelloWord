/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣���һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ���ſƼ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jskffmxb.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jskffmxb.web.JskffmxbForm;
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
 * Description:��ҵ����˰�걨
 * </p>
 * 
 * @author Cao Gang
 * @version 1.1
 */

public class JskffmxbProcessor implements Processor {

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

		JskffmxbForm jskffmxbForm = (JskffmxbForm) vo.getData();

		Connection conn = null;

		try {
			// ��ȡ���ݿ�����
			conn = SfDBResource.getConnection();

			QysdsReportsDeclare report = new QysdsReportsDeclare();
			// ��form�еĻ�����Ϣת��QysdsReportsDeclare report ��
			QysdsNewUtil.setQysdsReport(report, jskffmxbForm);
			// ����QysdsReportsTableDeclare�Ļ�����Ϣ
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_JSKFFMXB);
			table.setTableName(CodeConstant.TABLE_NAME_JSKFFMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			// ��QysdsReportsTableDeclare�Ļ�����Ϣ����QysdsReportsDeclare
			report.getTableContentList().put(table.getTableId(), table);

			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ���ò�ѯ�������в�ѯ
			iApp.querySingleTable(report);
			// ��ȡ��ѯ���ľ�������
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_JSKFFMXB);
			// �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
			int[] array = { 1, 25 };
			jskffmxbForm.setDataList(this.translate2Page(QysdsNewUtil.putSpace(
					table, array)));
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ��ѯ�ɹ�����jskffmxbForm
		return jskffmxbForm;
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

		JskffmxbForm jskffmxbForm = (JskffmxbForm) vo.getData();

		Connection conn = null;

		try {
			// ��ȡ���ݿ�ӿ�
			conn = SfDBResource.getConnection();

			// ��ActionForm�е����ݽṹת��,�������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this.translate2Interface(jskffmxbForm);
			// ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ����saveSingleTable�����������ݱ���
			iApp.saveSingleTable(report);
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ����ɹ�����jskffmxbForm
		return jskffmxbForm;
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

		JskffmxbForm jskffmxbForm = (JskffmxbForm) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this.translate2Interface(jskffmxbForm);
			// ��ȡУ��ӿ�
			Checker checker = CheckerFactory.getAInstance(conn,
					CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			// ���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List listSingle = checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			jskffmxbForm.setCheckList(listSingle);
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ����ɹ�����jskffmxbForm
		return jskffmxbForm;
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

		JskffmxbForm jskffmxbForm = (JskffmxbForm) vo.getData();
		Connection conn = null;
		try {
			// ��ȡ���ݿ�ӿ�
			conn = SfDBResource.getConnection();

			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this.translate2Interface(jskffmxbForm);

			// ��ȡ���ݿ�ӿڣ�����delete������������ɾ��
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report);

			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_JSKFFMXB);
			table.setTableId(CodeConstant.TABLE_NAME_JSKFFMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_JSKFFMXB);

			int[] array = { 1, 25 };
			jskffmxbForm.setDataList(this.translate2Page(QysdsNewUtil.putSpace(
					table, array)));

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return jskffmxbForm;
	}

	/**
	 * ��ActionForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ ҳ�����ݽṹ-->�ӿ����ݽṹ
	 * 
	 * @param jskffmxbForm
	 * @return ��ҵ����˰������������
	 */
	private QysdsReportsDeclare translate2Interface(JskffmxbForm jskffmxbForm) {

		// ��ҵ����˰������������
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		// ��form�еĻ�����Ϣת��QysdsReportsDeclare������
		QysdsNewUtil.setQysdsReport(report, jskffmxbForm);

		// ������ҵ����˰�����ڵ����������󣬲����������Ϣ
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_JSKFFMXB);
		table.setTableName(CodeConstant.TABLE_NAME_JSKFFMXB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ��ҳ�����ݷ�������ݿ�ӿڵ����ݸ�ʽ
		List list = jskffmxbForm.getDataList();
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);

			String hc = (String) map.get("hc");

			if ("1".equals(hc)) {
				QysdsReportsItemDeclare item_1_1 = new QysdsReportsItemDeclare();
				item_1_1.setItemID("1");
				item_1_1.setItemValue((String) map.get("nd1"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(), item_1_1);

				QysdsReportsItemDeclare item_1_2 = new QysdsReportsItemDeclare();
				item_1_2.setItemID("2");
				item_1_2.setItemValue((String) map.get("nd2"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(), item_1_2);

				QysdsReportsItemDeclare item_1_3 = new QysdsReportsItemDeclare();
				item_1_3.setItemID("3");
				item_1_3.setItemValue((String) map.get("nd3"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(), item_1_3);

				QysdsReportsItemDeclare item_1_4 = new QysdsReportsItemDeclare();
				item_1_4.setItemID("4");
				item_1_4.setItemValue((String) map.get("nd4"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(), item_1_4);
			}

			if ("2".equals(hc)) {
				QysdsReportsItemDeclare item_1_1 = new QysdsReportsItemDeclare();
				item_1_1.setItemID("5");
				item_1_1.setItemValue((String) map.get("nd4"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(), item_1_1);
			}
			if ("3".equals(hc)) {

				QysdsReportsItemDeclare item_1_4 = new QysdsReportsItemDeclare();
				item_1_4.setItemID("6");
				item_1_4.setItemValue((String) map.get("nd5"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(), item_1_4);
			}
			if ("4".equals(hc)) {
				QysdsReportsItemDeclare item_1_4 = new QysdsReportsItemDeclare();
				item_1_4.setItemID("7");
				item_1_4.setItemValue((String) map.get("nd5"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(), item_1_4);
			}
			if ("5".equals(hc)) {
				QysdsReportsItemDeclare item_1_4 = new QysdsReportsItemDeclare();
				item_1_4.setItemID("8");
				item_1_4.setItemValue((String) map.get("nd5"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(), item_1_4);
			}
			if ("6".equals(hc)) {
				QysdsReportsItemDeclare item_1_1 = new QysdsReportsItemDeclare();
				item_1_1.setItemID("9");
				item_1_1.setItemValue((String) map.get("nd1"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(), item_1_1);

				QysdsReportsItemDeclare item_1_2 = new QysdsReportsItemDeclare();
				item_1_2.setItemID("10");
				item_1_2.setItemValue((String) map.get("nd2"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(), item_1_2);

				QysdsReportsItemDeclare item_1_3 = new QysdsReportsItemDeclare();
				item_1_3.setItemID("11");
				item_1_3.setItemValue((String) map.get("nd3"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(), item_1_3);

				QysdsReportsItemDeclare item_1_4 = new QysdsReportsItemDeclare();
				item_1_4.setItemID("12");
				item_1_4.setItemValue((String) map.get("nd4"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(), item_1_4);

				QysdsReportsItemDeclare item_1_6 = new QysdsReportsItemDeclare();
				item_1_6.setItemID("13");
				item_1_6.setItemValue((String) map.get("nd6"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(), item_1_6);
			}
			if ("7".equals(hc)) {
				QysdsReportsItemDeclare item_1_4 = new QysdsReportsItemDeclare();
				item_1_4.setItemID("14");
				item_1_4.setItemValue((String) map.get("nd5"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(), item_1_4);
			}
			if ("8".equals(hc)) {
				QysdsReportsItemDeclare item_1_1 = new QysdsReportsItemDeclare();
				item_1_1.setItemID("15");
				item_1_1.setItemValue((String) map.get("nd1"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(), item_1_1);

				QysdsReportsItemDeclare item_1_2 = new QysdsReportsItemDeclare();
				item_1_2.setItemID("16");
				item_1_2.setItemValue((String) map.get("nd2"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(), item_1_2);

				QysdsReportsItemDeclare item_1_3 = new QysdsReportsItemDeclare();
				item_1_3.setItemID("17");
				item_1_3.setItemValue((String) map.get("nd3"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(), item_1_3);

				QysdsReportsItemDeclare item_1_4 = new QysdsReportsItemDeclare();
				item_1_4.setItemID("18");
				item_1_4.setItemValue((String) map.get("nd4"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(), item_1_4);

				QysdsReportsItemDeclare item_1_5 = new QysdsReportsItemDeclare();
				item_1_5.setItemID("19");
				item_1_5.setItemValue((String) map.get("nd5"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(), item_1_5);

				QysdsReportsItemDeclare item_1_6 = new QysdsReportsItemDeclare();
				item_1_6.setItemID("20");
				item_1_6.setItemValue((String) map.get("nd6"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(), item_1_6);
			}
			if ("9".equals(hc)) {
				QysdsReportsItemDeclare item_1_2 = new QysdsReportsItemDeclare();
				item_1_2.setItemID("21");
				item_1_2.setItemValue((String) map.get("nd2"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(), item_1_2);

				QysdsReportsItemDeclare item_1_3 = new QysdsReportsItemDeclare();
				item_1_3.setItemID("22");
				item_1_3.setItemValue((String) map.get("nd3"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(), item_1_3);

				QysdsReportsItemDeclare item_1_4 = new QysdsReportsItemDeclare();
				item_1_4.setItemID("23");
				item_1_4.setItemValue((String) map.get("nd4"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(), item_1_4);

				QysdsReportsItemDeclare item_1_5 = new QysdsReportsItemDeclare();
				item_1_5.setItemID("24");
				item_1_5.setItemValue((String) map.get("nd5"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(), item_1_5);

				QysdsReportsItemDeclare item_1_6 = new QysdsReportsItemDeclare();
				item_1_6.setItemID("25");
				item_1_6.setItemValue((String) map.get("nd6"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(), item_1_6);
			}
		}
		report.getTableContentList().put(table.getTableId(),
				QysdsNewUtil.cleanSpace(table));

		return report;
	}

	/**
	 * ���ӿ����ݽṹ�е�����ת��������ҳ��Ҫ������ݽṹ �ӿ����ݽṹ-->ҳ�����ݽṹ
	 * 
	 * @param QysdsReportsTableDeclare
	 * @return ҳ������ݵ�List����
	 */
	private List translate2Page(QysdsReportsTableDeclare table) {
		// ����List�����������ҳ�������
		List list = new ArrayList();

		Map map = table.getCellContentList();

		Map map1 = new HashMap();
		Map map2 = new HashMap();
		Map map3 = new HashMap();
		Map map4 = new HashMap();
		Map map5 = new HashMap();
		Map map6 = new HashMap();
		Map map7 = new HashMap();
		Map map8 = new HashMap();
		Map map9 = new HashMap();
		map1.put("hc", "1");
		QysdsReportsItemDeclare item1 = (QysdsReportsItemDeclare) map.get("1");
		String nd1_1 = item1.getItemValue();
		map1.put("nd1", nd1_1);
		QysdsReportsItemDeclare item2 = (QysdsReportsItemDeclare) map.get("2");
		String nd1_2 = item2.getItemValue();
		map1.put("nd2", nd1_2);
		QysdsReportsItemDeclare item3 = (QysdsReportsItemDeclare) map.get("3");
		String nd1_3 = item3.getItemValue();
		map1.put("nd3", nd1_3);
		QysdsReportsItemDeclare item4 = (QysdsReportsItemDeclare) map.get("4");
		String nd1_4 = item4.getItemValue();
		map1.put("nd4", nd1_4);
		map1.put("nd5", " ");
		map1.put("nd6", " ");
		list.add(map1);
		System.out.println(map1.get("nd1"));
		System.out.println(map1.get("nd2"));
		System.out.println(map1.get("nd3"));
		System.out.println(map1.get("nd4"));
		System.out.println(map1.get("nd5"));
		System.out.println(map1.get("nd6"));

		map2.put("hc", "2");
		map2.put("nd1", "*");
		map2.put("nd2", "*");
		map2.put("nd3", "*");
		QysdsReportsItemDeclare item5 = (QysdsReportsItemDeclare) map.get("5");
		String nd2_4 = item5.getItemValue();
		map2.put("nd4", nd2_4);
		map2.put("nd5", "*");
		map2.put("nd6", "*");
		list.add(map2);

		map3.put("hc", "3");
		map3.put("nd1", "*");
		map3.put("nd2", "*");
		map3.put("nd3", "*");
		map3.put("nd4", "*");
		QysdsReportsItemDeclare item6 = (QysdsReportsItemDeclare) map.get("6");
		String nd3_5 = item6.getItemValue();
		map3.put("nd5", nd3_5);
		map3.put("nd6", "*");
		list.add(map3);

		map4.put("hc", "4");
		map4.put("nd1", "*");
		map4.put("nd2", "*");
		map4.put("nd3", "*");
		map4.put("nd4", "*");
		QysdsReportsItemDeclare item7 = (QysdsReportsItemDeclare) map.get("7");
		String nd4_5 = item7.getItemValue();
		map4.put("nd5", nd4_5);
		map4.put("nd6", "*");
		list.add(map4);

		map5.put("hc", "5");
		map5.put("nd1", "*");
		map5.put("nd2", "*");
		map5.put("nd3", "*");
		map5.put("nd4", "*");
		QysdsReportsItemDeclare item8 = (QysdsReportsItemDeclare) map.get("8");
		String nd5_5 = item8.getItemValue();
		map5.put("nd5", nd5_5);
		map5.put("nd6", "*");
		list.add(map5);

		map6.put("hc", "6");
		QysdsReportsItemDeclare item9 = (QysdsReportsItemDeclare) map.get("9");
		String nd6_1 = item9.getItemValue();
		map6.put("nd1", nd6_1);
		QysdsReportsItemDeclare item10 = (QysdsReportsItemDeclare) map
				.get("10");
		String nd6_2 = item10.getItemValue();
		map6.put("nd2", nd6_2);
		QysdsReportsItemDeclare item11 = (QysdsReportsItemDeclare) map
				.get("11");
		String nd6_3 = item11.getItemValue();
		map6.put("nd3", nd6_3);
		QysdsReportsItemDeclare item12 = (QysdsReportsItemDeclare) map
				.get("12");
		String nd6_4 = item12.getItemValue();
		map6.put("nd4", nd6_4);
		map6.put("nd5", "*");
		QysdsReportsItemDeclare item13 = (QysdsReportsItemDeclare) map
				.get("13");
		String nd6_6 = item13.getItemValue();
		map6.put("nd6", nd6_6);
		list.add(map6);

		map7.put("hc", "7");
		map7.put("nd1", "*");
		map7.put("nd2", "*");
		map7.put("nd3", "*");
		map7.put("nd4", "*");
		QysdsReportsItemDeclare item14 = (QysdsReportsItemDeclare) map
				.get("14");
		String nd7_5 = item14.getItemValue();
		map7.put("nd5", nd7_5);
		map7.put("nd6", "*");
		list.add(map7);

		map8.put("hc", "8");
		QysdsReportsItemDeclare item15 = (QysdsReportsItemDeclare) map
				.get("15");
		String nd8_1 = item15.getItemValue();
		map8.put("nd1", nd8_1);
		QysdsReportsItemDeclare item16 = (QysdsReportsItemDeclare) map
				.get("16");
		String nd8_2 = item16.getItemValue();
		map8.put("nd2", nd8_2);
		QysdsReportsItemDeclare item17 = (QysdsReportsItemDeclare) map
				.get("17");
		String nd8_3 = item17.getItemValue();
		map8.put("nd3", nd8_3);
		QysdsReportsItemDeclare item18 = (QysdsReportsItemDeclare) map
				.get("18");
		String nd8_4 = item18.getItemValue();
		map8.put("nd4", nd8_4);
		QysdsReportsItemDeclare item19 = (QysdsReportsItemDeclare) map
				.get("19");
		String nd8_5 = item19.getItemValue();
		map8.put("nd5", nd8_5);
		QysdsReportsItemDeclare item20 = (QysdsReportsItemDeclare) map
				.get("20");
		String nd8_6 = item20.getItemValue();
		map8.put("nd6", nd8_6);
		list.add(map8);

		map9.put("hc", "9");
		map9.put("nd1", "*");
		QysdsReportsItemDeclare item21 = (QysdsReportsItemDeclare) map
				.get("21");
		String nd9_2 = item21.getItemValue();
		map9.put("nd2", nd9_2);
		QysdsReportsItemDeclare item22 = (QysdsReportsItemDeclare) map
				.get("22");
		String nd9_3 = item22.getItemValue();
		map9.put("nd3", nd9_3);
		QysdsReportsItemDeclare item23 = (QysdsReportsItemDeclare) map
				.get("23");
		String nd9_4 = item23.getItemValue();
		map9.put("nd4", nd9_4);
		QysdsReportsItemDeclare item24 = (QysdsReportsItemDeclare) map
				.get("24");
		String nd9_5 = item24.getItemValue();
		map9.put("nd5", nd9_5);
		QysdsReportsItemDeclare item25 = (QysdsReportsItemDeclare) map
				.get("25");
		String nd9_6 = item25.getItemValue();
		map9.put("nd6", nd9_6);
		list.add(map9);

		return list;

	}

}
