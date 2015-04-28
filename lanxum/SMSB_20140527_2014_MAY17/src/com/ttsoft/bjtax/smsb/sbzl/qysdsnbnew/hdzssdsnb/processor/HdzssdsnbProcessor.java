/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.hdzssdsnb.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.hdzssdsnb.web.HdzssdsnbForm;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:��ҵ����˰�걨
 * </p>
 * 
 * @author li wenhua
 * @version 1.1
 */

public class HdzssdsnbProcessor implements Processor {

	// ��ҵ����˰˰��
	private static final String QYSDS_SL = "0.33";

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
		case CodeConstant.SMSB_QUERYACTION:
			result = doQuery(vo);
			break;
		case CodeConstant.SMSB_SAVEACTION:
			result = doSave(vo);
			break;
		case CodeConstant.SMSB_DELETEACTION:
			result = doDelete(vo);
			break;
		// case CodeConstant.SMSB_CHECKACTION:
		// result = doCheck(vo);
		// break;
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

		HdzssdsnbForm form = (HdzssdsnbForm) vo.getData();

		// ��ʼ��FORM�������걨���ڡ��걨�ڼ�
		initForm(form);

		return form;
	}

	/**
	 * doQuery ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 * 
	 */

	private Object doQuery(VOPackage vo) throws BaseException {

		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) vo.getData();
		Connection conn = null;

		try {
			conn = SfDBResource.getConnection();
			// ����˰�����
			// hdzssdsnbForm.setSknd(this.getSbnd(hdzssdsnbForm.getSbrq()));
			// ��ҳ����ȡ��˰�������ں����
			String nd = hdzssdsnbForm.getSkssksrq().substring(0, 4);
			hdzssdsnbForm.setSknd(nd);

			// �����ں�
			hdzssdsnbForm.setQh("1");
			// ��������������Ϣ
			hdzssdsnbForm = (HdzssdsnbForm) QysdsNewUtil
					.queryDjxxByInterfaceDJ(conn, hdzssdsnbForm, vo
							.getUserData());
			// ˰����Ϣ
			this.getHdxx(hdzssdsnbForm);

			/* ���շ�ʽ */
			String zsfs = hdzssdsnbForm.getZsfs();
			if (zsfs == null || (zsfs != null && zsfs.equals(""))) {
				throw new ApplicationException("û�в�ѯ������ҵ�����շ�ʽ�϶���Ϣ�����϶����ٽ����걨¼�룡");
			}
			if (CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
				throw new ApplicationException(
						"����ҵ���϶�Ϊ�������ջ��������ڴ�¼�룬��¼�������������걨��");
			}

			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			// ��hdzssdsnbForm�еĻ�����Ϣ��ת��QysdsReportsDeclare������
			QysdsNewUtil.setQysdsReport(report, hdzssdsnbForm);

			// ����QysdsReportsTableDeclare����Ļ�����Ϣ
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_HDZSSDSNB);
			table.setTableName(CodeConstant.TABLE_NAME_HDZSSDSNB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			// ��QysdsReportsTableDeclare����Ļ�����Ϣת��QysdsReportsDeclare��
			report.getTableContentList().put(table.getTableId(), table);

			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ���ò�ѯ�������в�ѯ
			iApp.querySingleTable(report);
			// ��ȡ��ѯ���ľ�������
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_HDZSSDSNB);

			if (table.getCellContentList().size() > 0) {
				hdzssdsnbForm.setSbrq(TinyTools.Date2String(report.getSbrq(),
						"yyyyMMdd"));
				hdzssdsnbForm.setSkssksrq(TinyTools.Date2String(report
						.getSkssksrq(), "yyyyMMdd"));
				hdzssdsnbForm.setSkssjsrq(TinyTools.Date2String(report
						.getSkssjsrq(), "yyyyMMdd"));
			}

			// �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
			int[] arrs = { 1, 14 };
			hdzssdsnbForm.setDataList(this
					.translate2Page(putSpace(table, arrs)));

			// ������
			System.out.println(hdzssdsnbForm.getJsjdm());
			System.out.println(hdzssdsnbForm.getSbrq());
			System.out.println(hdzssdsnbForm.getNsrmc());
			System.out.println(hdzssdsnbForm.getSknd());
			System.out.println(hdzssdsnbForm.getQh());
			System.out.println(hdzssdsnbForm.getBbqlx());
			System.out.println(hdzssdsnbForm.getSkssksrq());
			System.out.println(hdzssdsnbForm.getSkssjsrq());
			System.out.println(hdzssdsnbForm.getSwjgzzjgdm());
			System.out.println(hdzssdsnbForm.getQxdm());
			System.out.println(hdzssdsnbForm.getLrr());
			System.out.println(hdzssdsnbForm.getSwjsjdm());

		} catch (Exception e) {
			// �׳��쳣
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ��ѯ�ɹ�����hdzssdsnbForm
		return hdzssdsnbForm;
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

		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) vo.getData();

		Connection conn = null;

		// ����˰�����
		// hdzssdsnbForm.setSknd(this.getSbnd(hdzssdsnbForm.getSbrq()));
		// ��ҳ����ȡ��˰�������ں����
		String nd = hdzssdsnbForm.getSkssksrq().substring(0, 4);
		hdzssdsnbForm.setSknd(nd);

		// �����ں�
		hdzssdsnbForm.setQh("1");

		try {

			// /* ���շ�ʽ */
			// String zsfs = QysdsNewUtil.getZsfsdm(hdzssdsnbForm);
			//			
			// System.out.println(hdzssdsnbForm.getJsjdm()+"�����շ�ʽ���룺"+zsfs);
			//			
			//			
			// if(zsfs==null ||(zsfs!=null && zsfs.equals(""))){
			// throw new ApplicationException(
			// "û�в�ѯ������ҵ�����շ�ʽ�϶���Ϣ�����϶����ٽ����걨¼�룡");
			// }
			// if (CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
			// throw new ApplicationException(
			// "����ҵ���϶�Ϊ�������ջ��������ڴ�¼�룬��¼��������ռ����걨��");
			// }

			// ��ȡ���ݿ�����
			conn = SfDBResource.getConnection();

			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this
					.translate2Interface(hdzssdsnbForm);

			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ����saveSingleTable�����������ݱ���
			iApp.saveSingleTable(report);

			// ��ȡһ�����п�ֵ��QysdsReportsTableDeclare����
			QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report
					.getTableContentList().get(CodeConstant.TABLE_ID_HDZSSDSNB);
			table.getCellContentList().clear();

			// �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
			int[] arrs = { 1, 14 };
			hdzssdsnbForm.setDataList(this
					.translate2Page(putSpace(table, arrs)));

			hdzssdsnbForm = (HdzssdsnbForm) this.doShow(vo);

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ����ɹ�����hdzssdsnbForm
		return hdzssdsnbForm;
	}

	/**
	 * doCheck ���ڴ洢ҳ���ύ���꾡������Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */
	// private Object doCheck(VOPackage vo) throws BaseException {
	//
	// HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) vo.getData();
	//
	// Connection conn = null;
	//
	// try {
	// // �������ݿ�����
	// conn = SfDBResource.getConnection();
	// // ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
	// QysdsReportsDeclare report = this
	// .translate2Interface(hdzssdsnbForm);
	// // ��ȡУ��ӿ�
	// Checker checker = CheckerFactory.getAInstance(conn,
	// CheckerFactory.ACCESS_MODEL_APP_QYSDS);
	// // ���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
	// List listSingle = checker.checkSingeTable(report);
	// hdzssdsnbForm.setCheckList(listSingle);
	// } catch (Exception ex) {
	// // �׳��쳣
	// ex.printStackTrace();
	// throw ExceptionUtil.getBaseException(ex);
	// } finally {
	// // �ͷ����ݿ�����
	// SfDBResource.freeConnection(conn);
	// }
	// return hdzssdsnbForm;
	// }
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

		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��HdzssdsnbForm�е����ݽṹת��,�������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this
					.translate2Interface(hdzssdsnbForm);

			// ��ȡ���ݿ�Ӧ�ýӿ�,����deleteSingleTable����ɾ������
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report);

			// ��ȡһ�����п�ֵ��QysdsReportsTableDeclare����
			QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report
					.getTableContentList().get(CodeConstant.TABLE_ID_HDZSSDSNB);
			table.getCellContentList().clear();

			// �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
			int[] arrs = { 1, 14 };
			hdzssdsnbForm.setDataList(this
					.translate2Page(putSpace(table, arrs)));

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ɾ���ɹ�����hdzssdsnbForm
		return hdzssdsnbForm;
	}

	/**
	 * ��ʼ��
	 * 
	 * @param form
	 *            ��������
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private void initForm(HdzssdsnbForm form) throws BaseException {

		// �����걨����
		form.setSbrq(SfDateUtil.getDate());
		// ����¼������
		form.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
		// ���ô�������
		form.setCjrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
		// �걨�ڼ�
		Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(form.getSbrq()));
		try {
			// ��ȡ��ʼ����
			String ksrq = DateTimeUtil.timestampToString((Timestamp) qj
					.get(Skssrq.SKSSKSRQ), DateTimeUtil.JAVA_DATEFORMAT);
			// ��ȡ��������
			String jsrq = DateTimeUtil.timestampToString((Timestamp) qj
					.get(Skssrq.SKSSJSRQ), DateTimeUtil.JAVA_DATEFORMAT);
			// ����˰�����
			form.setSknd((String) qj.get(Skssrq.SKSSRQ_ND));
			// ����˰�ʼ����
			form.setSkssksrq(ksrq);
			// ����˰���������
			form.setSkssjsrq(jsrq);
		} catch (Exception ex) {
			// �׳��쳣
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	// /**
	// * �����걨���
	// *
	// * @param sbrq
	// * �걨����
	// * @return String ���
	// */
	//
	// private String getSbnd(String sbrq) {
	//
	// Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sbrq));
	// System.out.println("------------------------getSbnd---"
	// + (String) qj.get(Skssrq.SKSSRQ_ND));
	// return (String) qj.get(Skssrq.SKSSRQ_ND);
	// }

	// /**
	// * �����걨����ȡ�õ�ǰǰ��0101-1231
	// *
	// * @param curSbrq
	// * �걨����
	// * @return dateMap
	// */
	// private Map getSbrl(String curSbrq) {
	// Timestamp sbrq = SfTimeUtil.getTimestamp(curSbrq);
	// GregorianCalendar calendar = new GregorianCalendar();
	// calendar.setTime(sbrq);
	// calendar.add(calendar.YEAR, -1);
	// int year = calendar.get(calendar.YEAR);
	// String nd = new Integer(year).toString();
	// Timestamp ksrq;
	// Timestamp jsrq;
	// Map retMap = new HashMap();
	// ksrq = new Timestamp(new GregorianCalendar(year, 0, 1).getTime()
	// .getTime());
	// jsrq = new Timestamp(new GregorianCalendar(year, 11, 31).getTime()
	// .getTime());
	//
	// Map dateMap = new HashMap();
	// dateMap.put("ksrq", ksrq);
	// dateMap.put("jsrq", jsrq);
	// return dateMap;
	// }

	/**
	 * ���㼾�����͵�˰����������
	 * 
	 * @param curDate
	 *            ����
	 * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp ʹ��Key ��
	 *         Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp ʹ��Key �� Skssrq.SKSSRQ_ND �õ�
	 *         ˰�������������ڵ���� String
	 */
	public Map quarterSkssrq(Date curDate) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(curDate);
		int month = calendar.get(calendar.MONTH);
		int year = calendar.get(calendar.YEAR);

		int jd = month / 3;
		if (jd == 0) {
			year--;
			jd = 4;
		}
		String nd = new Integer(year).toString();
		Timestamp skssksrqDate = new Timestamp(
				new GregorianCalendar(year, 0, 1).getTime().getTime());
		Timestamp skssjsrqDate = new Timestamp(new GregorianCalendar(year,
				(jd - 1) * 3 + 2, new GregorianCalendar(year, (jd - 1) * 3 + 2,
						1).getActualMaximum(calendar.DAY_OF_MONTH)).getTime()
				.getTime());
		Map retMap = new HashMap();
		retMap.put(Skssrq.SKSSKSRQ, skssksrqDate);
		retMap.put(Skssrq.SKSSJSRQ, skssjsrqDate);
		retMap.put(Skssrq.SKSSRQ_ND, nd);
		return retMap;
	}

	/**
	 * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ ҳ�����ݽṹ-->�ӿ����ݽṹ
	 * 
	 * @param form
	 * @return ��ҵ����˰������������
	 */
	private QysdsReportsDeclare translate2Interface(HdzssdsnbForm form) {

		// ��ҵ����˰������������
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		// ��form�еĻ�����Ϣת��QysdsReportsDeclare������
		QysdsNewUtil.setQysdsReport(report, form);

		// ������ҵ����˰�����ڵ����������󣬲����������Ϣ
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_HDZSSDSNB);
		table.setTableName(CodeConstant.TABLE_NAME_HDZSSDSNB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);

		// ��ҳ�����ݷ�������ݿ�ӿڵ����ݸ�ʽ
		List list = form.getDataList();

		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			String hc = (String) map.get("hc");
			if ("1".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("1");
				item_1.setItemValue((String) map.get("bqs"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("2");
				item_2.setItemValue((String) map.get("ljs"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);
			}
			if ("3".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("3");
				item_1.setItemValue((String) map.get("bqs"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("4");
				item_2.setItemValue((String) map.get("ljs"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);
			}
			if ("4".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("5");
				item_1.setItemValue((String) map.get("bqs"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("6");
				item_2.setItemValue((String) map.get("ljs"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);
			}
			if ("5".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("7");
				item_1.setItemValue((String) map.get("bqs"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("8");
				item_2.setItemValue((String) map.get("ljs"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);
			}
			if ("6".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("9");
				item_1.setItemValue((String) map.get("bqs"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("10");
				item_2.setItemValue((String) map.get("ljs"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);
			}
			if ("7".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("11");
				item_1.setItemValue((String) map.get("bqs"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("12");
				item_2.setItemValue((String) map.get("ljs"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);
			}
			if ("8".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("13");
				item_1.setItemValue((String) map.get("bqs"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);

				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID("14");
				item_2.setItemValue((String) map.get("ljs"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);
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
	 * @return ��ҵ����˰������������
	 */
	private List translate2Page(QysdsReportsTableDeclare table) {

		// ����ÿ��������Ӧ��map
		HashMap map1 = new HashMap();
		HashMap map2 = new HashMap();
		HashMap map3 = new HashMap();
		HashMap map4 = new HashMap();
		HashMap map5 = new HashMap();
		HashMap map6 = new HashMap();
		HashMap map7 = new HashMap();
		HashMap map8 = new HashMap();
		// ����List�����������ҳ�������
		ArrayList pagelist = new ArrayList();
		// ת������е�����
		map1.put("hc", "1");
		map1.put("bqs", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("1")).getItemValue());
		map1.put("ljs", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("2")).getItemValue());
		pagelist.add(map1);

		map2.put("hc", "2");
		map2.put("bqs", "*");
		map2.put("ljs", "*");
		pagelist.add(map2);

		map3.put("hc", "3");
		map3.put("bqs", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("3")).getItemValue());
		map3.put("ljs", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("4")).getItemValue());
		pagelist.add(map3);

		map4.put("hc", "4");
		map4.put("bqs", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("5")).getItemValue());
		map4.put("ljs", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("6")).getItemValue());
		pagelist.add(map4);

		map5.put("hc", "5");
		map5.put("bqs", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("7")).getItemValue());
		map5.put("ljs", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("8")).getItemValue());
		pagelist.add(map5);

		map6.put("hc", "6");
		map6.put("bqs", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("9")).getItemValue());
		map6.put("ljs", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("10")).getItemValue());
		pagelist.add(map6);

		map7.put("hc", "7");
		map7.put("bqs", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("11")).getItemValue());
		map7.put("ljs", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("12")).getItemValue());
		pagelist.add(map7);

		map8.put("hc", "8");
		map8.put("bqs", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("13")).getItemValue());
		map8.put("ljs", ((QysdsReportsItemDeclare) table.getCellContentList()
				.get("14")).getItemValue());
		pagelist.add(map8);

		// ����List����
		return pagelist;
	}

	/**
	 * ȡ��һ�ʺ���ҵ��˰����,����ҳ��У��
	 * 
	 * @param form
	 * @throws BaseException
	 */
	private void getHdxx(HdzssdsnbForm form) throws BaseException {
		String qyzssllx = "3"; // ȱʡΪ�����걨

		// ��ҵ��˰��˰�� �������ҵ��˰��˰������
		String qyzssl = QYSDS_SL;

		// Ӧ������˰��
		String ynsdse = "0.00";
		// ��������˰��
		String dezsse = "0.00";

		// ��ǰʱ��
		// Timestamp sbrq = new Timestamp(System.currentTimeMillis());
		// ���걨ҳ��ȡ���걨���ں�˰����������
		Timestamp sbrq = QysdsNewUtil.getNxetTimestamp(form.getSkssjsrq());

		// Map getsbjd = this.quarterSkssrq(sbrq);
		// Timestamp skssksrq = (Timestamp) getsbjd.get(Skssrq.SKSSKSRQ);
		// Timestamp skssjsrq = (Timestamp) getsbjd.get(Skssrq.SKSSJSRQ);
		Timestamp skssksrq = QysdsNewUtil.getTimestamp(form.getSkssksrq());
		Timestamp skssjsrq = QysdsNewUtil.getTimestamp(form.getSkssjsrq());

		System.out.println(form.getJsjdm() + "sbrq��" + sbrq);
		System.out.println(form.getJsjdm() + "skssksrq��" + skssksrq);
		System.out.println(form.getJsjdm() + "skssjsrq��" + skssjsrq);

		ServiceProxy proxy = new ServiceProxy();

		String bblx = form.getBbqlx();
		String jsjdm = form.getJsjdm();

		// ��ѯ˰�ѽӿ�
		QysdsSet qysdsSet = null;

		// ���ݿ����Ӷ���
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		// �����ʸ��ʶ
		boolean jm_type = false;

		try {
			if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_YEAR)) {
				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						"01");
			} else {
				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						"00");
			}

		} catch (com.ttsoft.framework.exception.BaseException e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

		// 1����ѯ��ҵ���շ�ʽ
		Zsfs zsfs = qysdsSet.getZsfs();
		if (zsfs != null) {
			form.setZsfs(zsfs.getZsfsdm() == null ? CodeConstant.ZSFSDM_CZZS
					: zsfs.getZsfsdm());
		} else {
			// form.setZsfs("");
			// 20070208���շ�ʽ���ȡ��Ϊ������Ϊ�ǲ���������ҵ�ġ�
			form.setZsfs(CodeConstant.ZSFSDM_CZZS);
		}

		/* ���¼�����ҵ�϶����� */
		Date gxqyrdrq = qysdsSet.getGxjsqy();

		// ��ֵ
		form.setCyl("0");
		form.setXzqy("0");
		form.setDezsse("0.00");
		form.setYbjmsl("0.00");

		if (zsfs != null) {
			String zsfsdm = zsfs.getZsfsdm();
			if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) {
				if (gxqyrdrq == null) {
					// ����������
					qyzssllx = "2";
				} else {
					// ���¼����ʹ�������ҵ
					qyzssllx = "5";
					qyzssl = "0.15";
					form.setJmzg("1"); // �м����ʸ�
				}
				form.setCyl(zsfs.getCyl());
			} else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) {
				// ��������
				qyzssllx = "4";
				// ��ʱ���ֶδ�����ҵ�˶�˰��
				// ynsdse = zsfs.getDe();
				dezsse = zsfs.getDe();
				form.setDezsse(dezsse);
			}
		}

		// 2����ѯ�Ƿ��Ǹ��¼�����ҵ
		if (gxqyrdrq != null) {
			if (zsfs != null
					&& zsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
				// ���¼����ʹ�������ҵ
				qyzssllx = "5";
			} else {
				// ����Ϊ���¼�����ҵ
				qyzssllx = "1";
			}
			qyzssl = "0.15";
			form.setJmzg("1"); // �м����ʸ�
		}

		// �ж��Ƿ�������ҵ����1��Ϊ������ҵ����0��Ϊ����������ҵ
		else if (form.getSsjjlx().equals(CodeConstant.JITIQIYE_CODE)) {
			if (qysdsSet.isXzqy()) {
				form.setXzqy("1");
				form.setJmzg("1"); // �м����ʸ�
			}
		}

		if (!(form.getXzqy() != null && form.getXzqy().equals("1"))
				&& qysdsSet.getYbjmsl() != null) {
			// ��������ҵ�ļ������
			form.setJmzg("1"); // �м����ʸ�
			DecimalFormat ft = new DecimalFormat("0.00");
			form.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
		}
		form.setQyzslx(qyzssllx);
		form.setSysl(qyzssl);

		

		/* �˶���Ϣ��� */
		System.out.println("-------------�˶���Ϣ--------------");
		System.out.println("��ҵ����˰������-" + qyzssllx);
		System.out.println("�����ʸ�-" + form.getJmzg());
		System.out.println("һ�����˰��-" + form.getYbjmsl());
		System.out.println("���շ�ʽ-" + form.getZsfs());
		System.out.println("������-" + form.getCyl());
		System.out.println("����-" + form.getDezsse());
		System.out.println("����˰��-" + form.getSysl());
		System.out.println("-------------�˶���Ϣ--------------");
	}

	/**
	 * �Ѵ������ʱ���˵��Ŀո�ԭ
	 * 
	 * @param table
	 * @param a
	 * @return
	 */
	public static QysdsReportsTableDeclare putSpace(
			QysdsReportsTableDeclare table, int arrs[]) {

		String flag = null;

		if (table.getCellContentList().size() == 0) {
			flag = "0.00";
		} else {
			flag = "";
		}

		System.out.println("**��ʾqysdsNewUtil�е�putSpace()**");

		for (int j = 1; j <= arrs.length; j = j + 2) {
			System.out.println("j___  " + j + "***" + arrs.length);
			for (int i = arrs[j - 1]; i <= arrs[j]; i++) {
				QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table
						.getCellContentList().get(String.valueOf(i));
				if (item == null) {
					QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
					item1.setItemID(String.valueOf(i));
					item1.setItemValue(flag);
					item1.setItemType("11");
					table.getCellContentList().put(String.valueOf(i), item1);
				} else if (item != null && item.getItemValue() != null
						&& "".equals(item.getItemValue().trim())) {
					QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
					item1.setItemID(String.valueOf(i));
					item1.setItemValue(flag);
					item1.setItemType("11");
					table.getCellContentList().put(String.valueOf(i), item1);
				}
			}
		}
		return table;
	}
}