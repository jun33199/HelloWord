/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsjbnew.processor;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjbnew.web.CzqysdsjbForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
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
 * Description:������ҵ����˰����
 * </p>
 * 
 * @author Li Wenhua
 * @version 1.1
 */

public class CzqysdsjbProcessor implements Processor {

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

		// �õ�Action���ݹ���CzqysdsjbForm����
		CzqysdsjbForm form = (CzqysdsjbForm) vo.getData();
		// �õ���ǰʱ���������
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		Map getsbjd = this.quarterSkssrq(curTime);
		Timestamp skssksrq = (Timestamp) getsbjd.get(Skssrq.SKSSKSRQ);
		Timestamp skssjsrq = (Timestamp) getsbjd.get(Skssrq.SKSSJSRQ);
		// ˰��������ʼ����
		form.setSkssksrq(SfTimeUtil.getDateFromDateTime(skssksrq));
		// ˰��������������
		form.setSkssjsrq(SfTimeUtil.getDateFromDateTime(skssjsrq));
		// ˰���걨����
		form.setSbrq(SfDateUtil.getDate());
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

		// �õ�Action���ݹ���CzqysdsjbForm����
		CzqysdsjbForm czqysdsjbForm = (CzqysdsjbForm) vo.getData();
		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��ȡ˰���������ڡ����
			// Map getsbnd = this.quarterSkssrq(SfDateUtil.getDate(czqysdsjbForm
			// .getSbrq()));

			// ��ȡ˰����������
			String jd = QysdsNewUtil.preQuarter(SfDateUtil
					.getDate(czqysdsjbForm.getSkssjsrq()));

			System.out.println(czqysdsjbForm.getJsjdm()
					+ "��czqysdsjbForm.getSbrq()��" + czqysdsjbForm.getSbrq());
			System.out.println(czqysdsjbForm.getJsjdm() + "��jd��" + jd);

			// ��ȡ˰���������
			// String nd = this.getNd(jd, getsbnd, czqysdsjbForm.getSbrq());
			// String nd = (String)getsbnd.get(Skssrq.SKSSRQ_ND);
			// ��ҳ����ȡ��˰�������ں����
			String nd = czqysdsjbForm.getSkssksrq().substring(0, 4);

			// ���ü���
			czqysdsjbForm.setQh(jd);

			// �������
			czqysdsjbForm.setSknd(nd);

			System.out.println(czqysdsjbForm.getJsjdm()
					+ "��czqysdsjbForm.setSknd��" + czqysdsjbForm.getSknd());

			// ����form��������������
			czqysdsjbForm = (CzqysdsjbForm) QysdsNewUtil
					.queryDjxxByInterfaceDJ(conn, czqysdsjbForm, vo
							.getUserData());

			// ˰�Ѻ˶���Ϣ
			this.getHdxx(czqysdsjbForm);

			/* ���շ�ʽ */
			String zsfs = czqysdsjbForm.getZsfs();

			System.out.println(czqysdsjbForm.getJsjdm() + "�����շ�ʽ���룺" + zsfs);

			if (zsfs == null || (zsfs != null && zsfs.equals(""))) {
				throw new ApplicationException("û�в�ѯ������ҵ�����շ�ʽ�϶���Ϣ�����϶����ٽ����걨¼�룡");
			}
			if (!CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
				throw new ApplicationException(
						"����ҵ���϶�Ϊ�˶����ջ��������ڴ�¼�룬��¼��˶����ռ����걨��");
			}

			// ����QysdsReportsDeclare����
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			// ��form�еĻ�����Ϣת��QysdsReportsDeclare report ��
			QysdsNewUtil.setQysdsReport(report, czqysdsjbForm);

			System.out.println("--------------" + report.getSbrq());
			System.out.println("--------------" + report.getSkssksrq());
			System.out.println("--------------" + report.getSkssjsrq());

			// ����QysdsReportsTableDeclare�Ļ�����Ϣ
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_CZQYSDSJB);
			table.setTableName(CodeConstant.TABLE_NAME_CZQYSDSJB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			// ��QysdsReportsTableDeclare�Ļ�����Ϣ����QysdsReportsDeclare
			report.getTableContentList().put(table.getTableId(), table);

			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ���ò�ѯ�������в�ѯ
			iApp.querySingleTable(report);
			// ��ȡ��ѯ���ľ�������
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_CZQYSDSJB);

			if (table.getCellContentList().size() > 0) {
				czqysdsjbForm.setSbrq(TinyTools.Date2String(report.getSbrq(),
						"yyyyMMdd"));
				czqysdsjbForm.setSkssksrq(TinyTools.Date2String(report
						.getSkssksrq(), "yyyyMMdd"));
				czqysdsjbForm.setSkssjsrq(TinyTools.Date2String(report
						.getSkssjsrq(), "yyyyMMdd"));
			}

			// �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
			int[] arrs = { 1, 11 };
			czqysdsjbForm.setQysdsjbList(this.translate2Page(putSpace(table,
					arrs)));

			// �����ã���ɺ�Ҫɾ��
			System.out.println(czqysdsjbForm.getJsjdm());
			System.out.println(czqysdsjbForm.getSbrq());
			System.out.println(czqysdsjbForm.getNsrmc());
			System.out.println(czqysdsjbForm.getSknd());
			System.out.println(czqysdsjbForm.getQh());
			System.out.println(czqysdsjbForm.getBbqlx());
			System.out.println(czqysdsjbForm.getSkssksrq());
			System.out.println(czqysdsjbForm.getSkssjsrq());
			System.out.println(czqysdsjbForm.getSwjgzzjgdm());
			System.out.println(czqysdsjbForm.getQxdm());
			System.out.println(czqysdsjbForm.getLrr());
			System.out.println(czqysdsjbForm.getSwjsjdm());

		} catch (Exception e) {
			// �׳��쳣
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ��ѯ�ɹ�����czqysdsjbForm
		return czqysdsjbForm;
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
		// �õ�Action���ݹ���CzqysdsjbForm����
		CzqysdsjbForm czqysdsjbForm = (CzqysdsjbForm) vo.getData();

		Connection conn = null;

		// ��ȡ˰����������
		// Map getsbnd = this.quarterSkssrq(SfDateUtil.getDate(czqysdsjbForm
		// .getSbrq()));

		// ��ȡ˰����������
		String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(czqysdsjbForm
				.getSkssjsrq()));

		// ��ȡ˰���������
		// String nd = this.getNd(jd, getsbnd, czqysdsjbForm.getSbrq());
		// String nd = (String)getsbnd.get(Skssrq.SKSSRQ_ND);
		// ��ҳ����ȡ��˰�������ں����
		String nd = czqysdsjbForm.getSkssksrq().substring(0, 4);
		// ���ü���
		czqysdsjbForm.setQh(jd);
		// �������
		czqysdsjbForm.setSknd(nd);
		System.out.println(czqysdsjbForm.getJsjdm() + "��czqysdsjbForm.setSknd��"
				+ czqysdsjbForm.getSknd());
		try {

			// /* ���շ�ʽ */
			// String zsfs = QysdsNewUtil.getZsfsdm(czqysdsjbForm);
			//			
			// System.out.println(czqysdsjbForm.getJsjdm()+"�����շ�ʽ���룺"+zsfs);
			//			
			//			
			// if(zsfs==null ||(zsfs!=null && zsfs.equals(""))){
			// throw new ApplicationException(
			// "û�в�ѯ������ҵ�����շ�ʽ�϶���Ϣ�����϶����ٽ����걨¼�룡");
			// }
			// if (!CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
			// throw new ApplicationException(
			// "����ҵ���϶�Ϊ�˶����ջ��������ڴ�¼�룬��¼��˶����ռ����걨��");
			// }

			// �������ݿ�����
			conn = SfDBResource.getConnection();

			// ��czqysdsjbForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this
					.translate2Interface(czqysdsjbForm);
			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ����saveSingleTable�����������ݱ���
			iApp.saveSingleTable(report);

			/**
			 * �������˰
			 */
			List list = czqysdsjbForm.getQysdsjbList();

			for (int i = 0; i < (list.size() - 3); i++) {

				if (i == 7) {
					// �����еļ���˰��
					HashMap map = (HashMap) list.get(7);
					System.out.println(czqysdsjbForm.getJsjdm() + "�ļ����д�:"
							+ (String) map.get("hc"));
					System.out.println(czqysdsjbForm.getJsjdm() + "�ļ����д�:"
							+ (String) map.get("ljje"));

					// ʹ��OrMap�������ݿ�ķ�װ��
					SfDBAccess da = new SfDBAccess(conn);
					SWDJJBSJ djxx = null;
					UserData ud = (UserData) vo.getUserData();
					try {
						/* start added by huxiaofeng 2005.8.16 */
						// djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
						djxx = InterfaceDj.getJBSJ_New(
								czqysdsjbForm.getJsjdm(), ud);
						/* end added by huxiaofeng 2005.8.16 */

					} catch (Exception ex1) {
						throw ExceptionUtil.getBaseException(ex1);
					}

					Timestamp skssksrq = QysdsNewUtil
							.getTimestamp(czqysdsjbForm.getSkssksrq());
					Timestamp skssjsrq = QysdsNewUtil
							.getTimestamp(czqysdsjbForm.getSkssjsrq());
					Timestamp sbrq = QysdsNewUtil.getTimestamp(czqysdsjbForm
							.getSbrq());

					Timestamp now = new Timestamp(System.currentTimeMillis());

					// ��һ������������걨����
					// this.putJm((String) map.get("ljje"), czqysdsjbForm, da,
					// ud,
					// djxx, now, now, sbrq, skssjsrq, skssksrq, nd);
					this.insertJmJb((String) map.get("ljje"), czqysdsjbForm,
							da, ud, djxx, now, now, sbrq, skssjsrq, skssksrq,
							nd);

				}
			}

			// ��ȡһ�����п�ֵ��QysdsReportsTableDeclare����
			QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report
					.getTableContentList().get(CodeConstant.TABLE_ID_CZQYSDSJB);
			table.getCellContentList().clear();

			// �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
			int[] arrs = { 1, 11 };
			czqysdsjbForm.setQysdsjbList(this.translate2Page(putSpace(table,
					arrs)));

			czqysdsjbForm = (CzqysdsjbForm) this.doShow(vo);

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ����ɹ�����czqysdsjbForm
		return czqysdsjbForm;
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
	// private Object doCheck(VOPackage vo) throws BaseException {
	//
	// // �õ�Action���ݹ���CzqysdsjbForm����
	// CzqysdsjbForm czqysdsjbForm = (CzqysdsjbForm) vo.getData();
	//
	// Connection conn = null;
	//
	// try {
	// // �������ݿ�����
	// conn = SfDBResource.getConnection();
	// // ��ActionForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
	// QysdsReportsDeclare report = this
	// .translate2Interface(czqysdsjbForm);
	// // ��ȡУ��ӿ�
	// Checker checker = CheckerFactory.getAInstance(conn,
	// CheckerFactory.ACCESS_MODEL_APP_QYSDS);
	// // ���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
	// List listSingle = checker.checkSingeTable(report);
	// czqysdsjbForm.setCheckList(listSingle);
	// } catch (Exception ex) {
	// // �׳��쳣
	// ex.printStackTrace();
	// throw ExceptionUtil.getBaseException(ex);
	// } finally {
	// // �ͷ����ݿ�����
	// SfDBResource.freeConnection(conn);
	// }
	// // ����ɹ�����czqysdsjbForm
	// return czqysdsjbForm;
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

		CzqysdsjbForm czqysdsjbForm = (CzqysdsjbForm) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��czqysdsjbForm�е����ݽṹת��,�������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this
					.translate2Interface(czqysdsjbForm);

			// ��ȡ���ݿ�Ӧ�ýӿ�,����deleteSingleTable����ɾ������
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report);

			// ��ȡһ�����п�ֵ��QysdsReportsTableDeclare����
			QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report
					.getTableContentList().get(CodeConstant.TABLE_ID_CZQYSDSJB);
			table.getCellContentList().clear();

			// �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
			int[] arrs = { 1, 11 };
			czqysdsjbForm.setQysdsjbList(this.translate2Page(putSpace(table,
					arrs)));

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ɾ���ɹ�����hdzssdsnbForm
		return czqysdsjbForm;
	}

	// /**
	// * ���ݼ������������ȵķ���
	// *
	// * @param jd
	// * ����
	// * @param getsbnd
	// * �걨���
	// * @param sbrq
	// * �걨����
	// * @return ���
	// */
	// private String getNd(String jd, Map getsbnd, String sbrq) {
	// String nd;
	// if (jd.equals("4")) {
	// nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
	// } else {
	// nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
	// }
	// return nd;
	// }
	//
	/**
	 * �����걨����ȡ�õ�ǰǰ��0101-1231
	 * 
	 * @param curSbrq
	 *            �걨����
	 * @return dateMap
	 */
	private Map getSbrl(String curSbrq) {
		Timestamp sbrq = SfTimeUtil.getTimestamp(curSbrq);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(sbrq);
		calendar.add(calendar.MONTH, -1);
		int month = calendar.get(calendar.MONTH);
		int year = calendar.get(calendar.YEAR);
		String nd = new Integer(year).toString();
		int maxDate = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
		Timestamp ksrq = new Timestamp(new GregorianCalendar(year, 0, 1)
				.getTime().getTime());
		Timestamp jsrq = new Timestamp(new GregorianCalendar(year, month,
				maxDate).getTime().getTime());
		Map dateMap = new HashMap();
		dateMap.put("ksrq", ksrq);
		dateMap.put("jsrq", jsrq);
		return dateMap;
	}

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
	private QysdsReportsDeclare translate2Interface(CzqysdsjbForm form) {

		// ����QysdsReportsDeclare����
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		// ��form�еĻ�����Ϣת��QysdsReportsDeclare������
		QysdsNewUtil.setQysdsReport(report, form);

		// ������ҵ����˰�����ڵ����������󣬲����������Ϣ
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_CZQYSDSJB);
		table.setTableName(CodeConstant.TABLE_NAME_CZQYSDSJB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);

		// ��ҳ�����ݷ�������ݿ�ӿڵ����ݸ�ʽ
		List list = form.getQysdsjbList();

		for (int i = 0; i < (list.size() - 3); i++) {
			HashMap map = (HashMap) list.get(i);
			QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
			item.setItemID((String) map.get("hc"));
			item.setItemValue((String) map.get("ljje"));
			item.setItemType("11");
			table.getCellContentList().put(item.getItemID(), item);
		}
		report.getTableContentList().put(table.getTableId(),
				QysdsNewUtil.cleanSpace(table));

		return report;
	}

	/**
	 * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ �ӿ����ݽṹ-->ҳ�����ݽṹ
	 * 
	 * @param QysdsReportsTableDeclare
	 * @return ҳ������ݵ�List����
	 */
	private List translate2Page(QysdsReportsTableDeclare table) {

		// ����List�����������ҳ�������
		ArrayList pagelist = new ArrayList();
		// �Բ���*�ŵ��е����ݽ��з���
		int i = 1;
		Iterator it = table.getCellContentList().keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table
					.getCellContentList().get(key);
			HashMap map = new HashMap();
			map.put("hc", item.getItemID());
			map.put("ljje", item.getItemValue());
			System.out.println(i++);
			pagelist.add(map);
		}
		// �Ѵ�*�ŵ��м���
		for (int j = 12; j < 15; j++) {
			HashMap map = new HashMap();
			map.put("hc", String.valueOf(j));
			map.put("ljje", "*");
			pagelist.add(map);
			System.out.println(j);
		}
		// ����List����
		return pagelist;
	}

	/**
	 * ȡ��һ�ʺ���ҵ��˰����,����ҳ��У��
	 * 
	 * @param form
	 * @throws BaseException
	 */
	private void getHdxx(CzqysdsjbForm form) throws BaseException {

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
		form.setJmzg("0"); // �м����ʸ�

		try {
			if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_YEAR)) {
				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						CodeConstant.SFGL_QYSDS_BBFS_NB);
			} else if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR)) {

				if (form.getQh() == null
						|| (form.getQh() != null && form.getQh().trim().equals(
								""))) {
					/* �ںŲ���Ϊ�գ����Ϊ���׳��쳣 */
					throw new ApplicationException("ϵͳ�����쳣���ں�Ϊ�գ�����ϵͳ����Ա��ϵ��");
				}

				System.out.println("form.getQh()::" + form.getQh());

				if ("4".equals(form.getQh())) {
					/* ���Ϊ���ļ��ȣ���ȡ��ҵ����˰�϶���Ϣʱ���걨����ȡ */
					qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
							skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_NB);
				} else {
					qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
							skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_JB);
				}
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

		} else if (form.getSsjjlx().equals(CodeConstant.JITIQIYE_CODE)) {
			// �ж��Ƿ�������ҵ����1��Ϊ������ҵ����0��Ϊ����������ҵ
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

	/**
	 * �������
	 * 
	 * @param jmje
	 *            ������
	 * @param form
	 *            �걨��Ϣ
	 * @param dbAgent
	 *            ���ݿ�����
	 * @param ud
	 *            ����Ա��Ϣ
	 * @param djsj
	 *            �Ǽ���Ϣ
	 * @param ts_cjrq
	 *            ��������
	 * @param ts_lrrq
	 *            ¼������
	 * @param ts_sbrq
	 *            �걨����
	 * @param ts_Skssjsrq
	 *            ˰��������������
	 * @param ts_Skssksrq
	 *            ˰��������ʼ����
	 * @param nd
	 *            ���
	 */
	private void putJm(String jmje, CzqysdsjbForm form, SfDBAccess dbAgent,
			UserData ud, SWDJJBSJ djsj, Timestamp ts_cjrq, Timestamp ts_lrrq,
			Timestamp ts_sbrq, Timestamp ts_Skssjsrq, Timestamp ts_Skssksrq,
			String nd) {

		Timestamp t1, t2;

		t1 = new Timestamp(System.currentTimeMillis());

		try {
			// ����ҵ����˰�������걨�ļ����ֶ���ֵ����˰���м����ʸ��ʱ��Ҫ�������걨��������ݣ�
			// �����޸ģ�
			// ����������Ѿ�����������ˣ������ݼ��˱�ʶΪδ���ˣ���Ҫ�޸ļ����걨��ļ������ֶΣ�
			// ����������Ѿ�����������ˣ������ݼ��˱�ʶΪ�Ѽ��ˣ������ٲ������ݣ�
			// ���������û�в�������ݣ������һ�����ݣ�
			//
			// �����ϼ���˰�걨��ʱ����Ʋ���¼����ҵ����˰�ļ������ݣ������걨��˰��˰Ŀ�¹��˵���ҵ����˰��˰��˰Ŀ��
			// ��ҳ�����ݵļ������Ϊ�ջ�0���Ҿ߱�һ������ʸ�Ľ���
			// if (!"0".equals(jmje) && !"".equals(jmje) && form.getYbjmsl() !=
			// null &&
			// !form.getYbjmsl().equals(""))
			// {
			if ((jmje != null) && (!"0".equals(jmje)) && (!"".equals(jmje))
					&& ("1".equals(form.getJmzg()))) {

				Jm jm = new Jm();
				// ����ֵ�������
				jm.setCjrq(ts_cjrq);
				jm.setJsjdm(form.getJsjdm());
				jm.setJmlx(CodeConstant.JMLX_SP);
				jm.setSzsmdm(CodeConstant.SZSM_QYSDSCODE);
				jm.setSbrq(ts_sbrq);
				jm.setLrrq(ts_lrrq);
				jm.setFsdm(CodeConstant.FSDM_SMSB);
				jm.setJzbz(CodeConstant.SMSB_JZBZ);
				jm.setJmse(new BigDecimal(jmje));
				jm.setJsje(new BigDecimal(jmje));
				jm.setLrr(ud.getYhid());
				jm.setSkssjsrq(ts_Skssjsrq);
				jm.setSkssksrq(ts_Skssksrq);
				jm.setSwjgzzjgdm(djsj.getSwjgzzjgdm());
				// jm.setQxdm(InterfaceDj.getQxdm(ud));
				jm.setQxdm(djsj.getSwjgzzjgdm().substring(0, 2));
				jm.setDjzclxdm(djsj.getDjzclxdm());
				jm.setGjbzhydm(djsj.getGjbzhydm());
				jm.setNd(nd);

				Date date = TinyTools.stringToDate(form.getSbrq(), "yyyyMMdd");
				// ����������
				ServiceProxy proxy = new ServiceProxy();
				String jmxmdm = proxy.getJmsbs(form.getJsjdm(),
						CodeConstant.SZSM_QYSDS, ts_Skssksrq, ts_Skssjsrq);
				Debug.out("���������� =" + jmxmdm);

				// ������Ŀ����Ϊ�գ��ӱ���ȡ����ҵ�����������жϼ��ⷽʽ
				if (jmxmdm == null) {

					System.out
							.println("**************   ȡ�õļ�����Ŀ����Ϊ�գ������걨�ļ�������putJm�е���ҵ��������form.getQyzslx()Ϊ:"
									+ form.getQyzslx());

					if (form.getQyzslx() != null) {

						// ��ҵ��������Ϊ���¼�����ҵ ���������Ϊ���¼�����ҵ����
						if ("1".equals(form.getQyzslx())
								|| "5".equals(form.getQyzslx())) {

							jmxmdm = CodeConstant.JMLX9010;

						}
					}

				}

				if (jmxmdm != null && !("".equals(jmxmdm))) {

					jm.setJmxmdm(jmxmdm);

					// ����ʱ�����
					Map dateMap = getSbrl(form.getSbrq());
					Vector vZb = new Vector();
					// ��ѯ����
					vZb.add(" qxdm='" + jm.getQxdm() + "'");
					vZb.add(" nd = '" + jm.getNd() + "'");
					vZb.add(" SKSSKSRQ = to_date('"
							+ String.valueOf(dateMap.get("ksrq")).substring(0,
									10) + "','yyyy-MM-dd')");
					vZb.add(" SKSSJSRQ = to_date('"
							+ String.valueOf(dateMap.get("jsrq")).substring(0,
									10) + "','yyyy-MM-dd')");
					// ����jzbz�����жϣ�ֻ��û�м������ݵ�ʱ��Ų����µ����ݣ�����м��������ڸ���jzbz���д���
					// vZb.add("jzbz like '" + CodeConstant.SMSB_JZBZ_UNEDIT +
					// "%"
					// + "'");
					vZb.add(" (FSDM='" + CodeConstant.FSDM_WSSB + "' OR FSDM='"
							+ CodeConstant.FSDM_SMSB + "') ");
					vZb.add(" szsmdm ='" + jm.getSzsmdm() + "' ");
					vZb.add(" jsjdm='" + jm.getJsjdm() + "' ");
					vZb.add(" jmxmdm='" + jm.getJmxmdm() + "' ");
					vZb.add(" jmlx='" + jm.getJmlx() + "' ");
					List zbData = dbAgent.query(Jm.class, vZb);
					if (zbData.size() <= 0) {

						try {
							// ɾ����������

							dbAgent
									.delete(
											" qxdm='"
													+ InterfaceDj.getQxdm(ud)
													+ "' and SKSSKSRQ = to_date('"
													+ String
															.valueOf(
																	dateMap
																			.get("ksrq"))
															.substring(0, 10)
													+ "','yyyy-MM-dd') and SKSSJSRQ = to_date('"
													+ String
															.valueOf(
																	dateMap
																			.get("jsrq"))
															.substring(0, 10)
													+ "','yyyy-MM-dd') "
													+ "and jzbz like '"
													+ CodeConstant.SMSB_JZBZ_EDIT
													+ "%" + "'"
													+ "and ( FSDM='"
													+ CodeConstant.FSDM_WSSB
													+ "' OR FSDM='"
													+ CodeConstant.FSDM_SMSB
													+ "') " + "and jsjdm='"
													+ form.getJsjdm() + "'",
											new Jm());

							Debug.out("�������ݿ�ɾ��ԭ������");
						} catch (BaseException ex1) {
							throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
						}

						try {

							// ȡ��Ԥ�㼶�δ���
							Ysjc ysjc = null;
							try {
								ysjc = JksUtil.getYsjc(form.getJsjdm(),
										CodeConstant.SZSM_QYSDS, SfDateUtil
												.getDate(form.getSbrq()));
							} catch (Exception e) {
								throw new ApplicationException(
										"�ü���������Ԥ�㼶�δ���û�м�¼��");
							}
							if (ysjc != null) {
								Debug.out("���� =" + ysjc.getYsjcdm());
							} else {
								throw new ApplicationException(
										"�ü���������Ԥ�㼶�δ���û�м�¼��");
							}
							// ȡ��Ԥ���Ŀ����
							com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm yskm = null;
							try {
								yskm = JKAdapter.getInstance().getYskm(
										CodeConstant.SZSM_QYSDSCODE,
										djsj.getDjzclxdm(), djsj.getGjbzhydm(),
										ysjc.getYsjcdm());
							} catch (Exception e) {
								throw new ApplicationException(
										"�ü���������Ԥ���Ŀ����û�м�¼��");
							}
							if (yskm != null) {
								Debug.out("Ԥ���Ŀ =" + yskm.getYskmdm());
							} else {
								throw new ApplicationException(
										"�ü���������Ԥ���Ŀ����û�м�¼��");
							}

							jm.setYsjcdm(ysjc.getYsjcdm());
							jm.setYskmdm(yskm.getYskmdm());

							// ������������
							dbAgent.insert(jm);
						} catch (BaseException ex4) {
							throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
						}

					} else {
						// ����������Ѿ�����������ˣ������ݼ��˱�ʶΪδ���ˣ���Ҫ�޸ļ����걨��ļ������ֶΣ�
						Jm jmTemp = (Jm) zbData.get(0);
						if (jmTemp.getJzbz().substring(0, 1).equals(
								CodeConstant.SMSB_JZBZ_EDIT)) {
							// δ���ˣ������jmse
							jmTemp.setJmse(new BigDecimal(jmje));
							dbAgent.update(jmTemp);
						}

					}
				}

			}

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
		}

		t2 = new Timestamp(System.currentTimeMillis());

		System.out.println("��������걨�����ݺ�ʱ��" + (t2.getTime() - t1.getTime()));

	}

	/**
	 * �������
	 * 
	 * @param jmje
	 *            ������
	 * @param form
	 *            �걨��Ϣ
	 * @param dbAgent
	 *            ���ݿ�����
	 * @param ud
	 *            ����Ա��Ϣ
	 * @param djsj
	 *            �Ǽ���Ϣ
	 * @param ts_cjrq
	 *            ��������
	 * @param ts_lrrq
	 *            ¼������
	 * @param ts_sbrq
	 *            �걨����
	 * @param ts_Skssjsrq
	 *            ˰��������������
	 * @param ts_Skssksrq
	 *            ˰��������ʼ����
	 * @param nd
	 *            ���
	 */
	private void insertJmJb(String jmje, CzqysdsjbForm form,
			SfDBAccess dbAgent, UserData ud, SWDJJBSJ djsj, Timestamp ts_cjrq,
			Timestamp ts_lrrq, Timestamp ts_sbrq, Timestamp ts_Skssjsrq,
			Timestamp ts_Skssksrq, String nd) {

		Connection con = null;
		CallableStatement st = null;
		String sql = "";

		Timestamp t1, t2;

		t1 = new Timestamp(System.currentTimeMillis());

		try {
			// ����ҵ����˰�������걨�ļ����ֶ���ֵ����˰���м����ʸ��ʱ��Ҫ�������걨��������ݣ�
			// �����޸ģ�
			// ����������Ѿ�����������ˣ������ݼ��˱�ʶΪδ���ˣ���Ҫ�޸ļ����걨��ļ������ֶΣ�
			// ����������Ѿ�����������ˣ������ݼ��˱�ʶΪ�Ѽ��ˣ������ٲ������ݣ�
			// ���������û�в�������ݣ������һ�����ݣ�
			//
			// �����ϼ���˰�걨��ʱ����Ʋ���¼����ҵ����˰�ļ������ݣ������걨��˰��˰Ŀ�¹��˵���ҵ����˰��˰��˰Ŀ��
			// ��ҳ�����ݵļ������Ϊ�ջ�0���Ҿ߱�һ������ʸ�Ľ���
			// if (!"0".equals(jmje) && !"".equals(jmje) && form.getYbjmsl() !=
			// null &&
			// !form.getYbjmsl().equals(""))
			// {
			if ((jmje != null) && (!"0".equals(jmje)) && (!"".equals(jmje))
					&& ("1".equals(form.getJmzg()))) {

				Jm jm = new Jm();
				// ����ֵ�������
				jm.setCjrq(ts_cjrq);
				jm.setJsjdm(form.getJsjdm());
				jm.setJmlx(CodeConstant.JMLX_SP);
				jm.setSzsmdm(CodeConstant.SZSM_QYSDSCODE);
				jm.setSbrq(ts_sbrq);
				jm.setLrrq(ts_lrrq);
				jm.setFsdm(CodeConstant.FSDM_SMSB);
				jm.setJzbz(CodeConstant.SMSB_JZBZ);
				jm.setJmse(new BigDecimal(jmje));
				jm.setJsje(new BigDecimal(jmje));
				jm.setLrr(ud.getYhid());
				jm.setSkssjsrq(ts_Skssjsrq);
				jm.setSkssksrq(ts_Skssksrq);
				jm.setSwjgzzjgdm(djsj.getSwjgzzjgdm());
				// jm.setQxdm(InterfaceDj.getQxdm(ud));
				jm.setQxdm(djsj.getSwjgzzjgdm().substring(0, 2));
				jm.setDjzclxdm(djsj.getDjzclxdm());
				jm.setGjbzhydm(djsj.getGjbzhydm());
				jm.setNd(nd);

				Date date = TinyTools.stringToDate(form.getSbrq(), "yyyyMMdd");
				// ����������
				ServiceProxy proxy = new ServiceProxy();
				String jmxmdm = proxy.getJmsbs(form.getJsjdm(),
						CodeConstant.SZSM_QYSDS, ts_Skssksrq, ts_Skssjsrq);
				Debug.out("���������� =" + jmxmdm);

				// ������Ŀ����Ϊ�գ��ӱ���ȡ����ҵ�����������жϼ��ⷽʽ
				if (jmxmdm == null) {

					System.out
							.println("**************   ȡ�õļ�����Ŀ����Ϊ�գ������걨�ļ�������putJm�е���ҵ��������form.getQyzslx()Ϊ:"
									+ form.getQyzslx());

					if (form.getQyzslx() != null) {

						// ��ҵ��������Ϊ���¼�����ҵ ���������Ϊ���¼�����ҵ����
						if ("1".equals(form.getQyzslx())
								|| "5".equals(form.getQyzslx())) {

							jmxmdm = CodeConstant.JMLX9010;

						}
					}

				}

				if (jmxmdm != null && !("".equals(jmxmdm))) {

					jm.setJmxmdm(jmxmdm);
					// ȡ��Ԥ�㼶�δ���
					Ysjc ysjc = null;
					try {
						ysjc = JksUtil.getYsjc(form.getJsjdm(),
								CodeConstant.SZSM_QYSDS, SfDateUtil
										.getDate(form.getSbrq()));
					} catch (Exception e) {
						throw new ApplicationException("�ü���������Ԥ�㼶�δ���û�м�¼��");
					}
					if (ysjc != null) {
						Debug.out("���� =" + ysjc.getYsjcdm());
					} else {
						throw new ApplicationException("�ü���������Ԥ�㼶�δ���û�м�¼��");
					}
					// ȡ��Ԥ���Ŀ����
					com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm yskm = null;
					try {
						yskm = JKAdapter.getInstance().getYskm(
								CodeConstant.SZSM_QYSDSCODE,
								djsj.getDjzclxdm(), djsj.getGjbzhydm(),
								ysjc.getYsjcdm());
					} catch (Exception e) {
						throw new ApplicationException("�ü���������Ԥ���Ŀ����û�м�¼��");
					}
					if (yskm != null) {
						Debug.out("Ԥ���Ŀ =" + yskm.getYskmdm());
					} else {
						throw new ApplicationException("�ü���������Ԥ���Ŀ����û�м�¼��");
					}

					jm.setYsjcdm(ysjc.getYsjcdm());
					jm.setYskmdm(yskm.getYskmdm());

					try {

						String jsjdm = jm.getJsjdm();
						String jmlx = jm.getJmlx();
						String szsmdm = jm.getSzsmdm();
						// String sbrq = df.format(now);
						Timestamp sbrq = jm.getSbrq();
						String fsdm = jm.getFsdm();
						String jzbz = jm.getJzbz();
						String lrr = jm.getLrr();
						// String skssjsrq = df.format(declare.getSkssjsrq());
						// String skssksrq = df.format(declare.getSkssksrq());
						Timestamp skssjsrq = jm.getSkssjsrq();
						Timestamp skssksrq = jm.getSkssksrq();
						String swjgzzjgdm = jm.getSwjgzzjgdm();
						String qxdm = jm.getQxdm();
						String djzclxdm = jm.getDjzclxdm();
						String gjbzhydm = jm.getGjbzhydm();
						// String nd = jm.getNd();
						String ysjcdm = jm.getYsjcdm();
						String yskmdm = jm.getYskmdm();
						// String jmxmdm = jm.getJmxmdm();
						BigDecimal jmse = jm.getJmse();

						// �������ݿ�����
						con = SfDBResource.getConnection();
						sql = "BEGIN sbdb.sb_pkg_sbjmtools.insertjm_jb(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); END;";

						st = con.prepareCall(sql);

						System.out
								.println("-------------���ò�������걨�洢���̲���----------------");
						System.out.println("1-jsjdm--" + jsjdm);
						System.out.println("2-jmlx--" + jmlx);
						System.out.println("3-szsmdm--" + szsmdm);
						System.out.println("4-sbrq--" + sbrq);
						System.out.println("5-fsdm--" + fsdm);
						System.out.println("6-jzbz--" + jzbz);
						System.out.println("7-lrr--" + lrr);
						System.out.println("8-skssjsrq--" + skssjsrq);
						System.out.println("9-skssksrq--" + skssksrq);
						System.out.println("10-swjgzzjgdm--" + swjgzzjgdm);
						System.out.println("11-qxdm--" + qxdm);
						System.out.println("12-djzclxdm--" + djzclxdm);
						System.out.println("13-gjbzhydm--" + gjbzhydm);
						System.out.println("14-nd--" + nd);
						System.out.println("15-ysjcdm--" + ysjcdm);
						System.out.println("16-yskmdm--" + yskmdm);
						System.out.println("17-jmxmdm--" + jmxmdm);
						System.out.println("18-jmse--" + jmse);

						st.setString(1, jsjdm);
						st.setString(2, jmlx);
						st.setString(3, szsmdm);
						st.setTimestamp(4, sbrq);
						st.setString(5, fsdm);

						st.setString(6, jzbz);
						st.setString(7, lrr);
						st.setTimestamp(8, skssjsrq);
						st.setTimestamp(9, skssksrq);
						st.setString(10, swjgzzjgdm);

						st.setString(11, qxdm);
						st.setString(12, djzclxdm);
						st.setString(13, gjbzhydm);
						st.setString(14, nd);
						st.setString(15, ysjcdm);

						st.setString(16, yskmdm);
						st.setString(17, jmxmdm);
						st.setBigDecimal(18, jmse);

						st.execute();

					} catch (Exception ex4) {
						throw new ApplicationException(
								"��������걨��ʧ�ܣ����ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
					}

				}

			}

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
		}

		t2 = new Timestamp(System.currentTimeMillis());

		System.out.println("��������걨�����ݺ�ʱ��" + (t2.getTime() - t1.getTime()));

	}
}
