package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.hdzssdsnb.processor;

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
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.hdzssdsnb.web.HdzssdsnbForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsConstant2014;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsHdzsNbUtil2014;


/**
 * 2014�˶�������ҵ����˰����
 * ��Ŀ���ƣ���ҵ����˰   
 * �����ƣ�HdzssdsnbProcessor   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-4-10 ����3:37:12   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-4-10 ����3:37:12   
 * �޸ı�ע��   
 * @version  1.0
 */
public class HdzssdsnbProcessor implements Processor {

	// ��ҵ����˰˰��
	private static final String QYSDS_SL = "0.25";

    private QysdsHdzsNbUtil2014 util = new QysdsHdzsNbUtil2014();

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
		// �õ�Action���ݹ���HdzssdsnbForm����
		HdzssdsnbForm form = (HdzssdsnbForm) vo.getData();
		// �õ���ǰʱ���������
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		//Map getsbjd = this.quarterSkssrq(curTime);
		//Timestamp skssksrq = (Timestamp) getsbjd.get(Skssrq.SKSSKSRQ);
		//Timestamp skssjsrq = (Timestamp) getsbjd.get(Skssrq.SKSSJSRQ);
		Map nbqj = Skssrq.yearSkssrq(curTime);
		Timestamp skssksrq = (Timestamp) nbqj.get(Skssrq.SKSSKSRQ);
		Timestamp skssjsrq = (Timestamp) nbqj.get(Skssrq.SKSSJSRQ);
		// ˰��������ʼ����
		form.setSkssksrq(SfTimeUtil.getDateFromDateTime(skssksrq));
		// ˰��������������
		form.setSkssjsrq(SfTimeUtil.getDateFromDateTime(skssjsrq));
		// ˰���걨����
		form.setSbrq(SfDateUtil.getDate());
		
		form.setSknd((String) nbqj.get(Skssrq.SKSSRQ_ND));
		form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
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

		// �õ�Action���ݹ���HdzssdsnbForm����
		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) vo.getData();
		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();

			// ��ȡ˰����������
			String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(hdzssdsnbForm.getSkssjsrq()));

			System.out.println(hdzssdsnbForm.getJsjdm() + "hdzssdsnbForm.getSbrq()��" + hdzssdsnbForm.getSbrq());
			System.out.println(hdzssdsnbForm.getJsjdm() + "hdzssdsnbForm.getSkssjsrq()��" + hdzssdsnbForm.getSkssjsrq());
			System.out.println(hdzssdsnbForm.getJsjdm() + "��jd��" + jd);

			// ��ȡ˰���������
			// String sknd = this.getNd(jd, getsbnd, hdzssdsnbForm.getSbrq());
			// String nd = (String)getsbnd.get(Skssrq.SKSSRQ_ND);
			// ��ҳ����ȡ��˰�������ں����
			String nd = hdzssdsnbForm.getSkssksrq().substring(0, 4);
			// ���ü���
			//hdzssdsnbForm.setQh(jd);
			hdzssdsnbForm.setQh("1");
			// �������
			hdzssdsnbForm.setSknd(nd);

			// ����form��������������
			hdzssdsnbForm = (HdzssdsnbForm) QysdsNewUtil.queryDjxxByInterfaceDJ(conn, hdzssdsnbForm, vo.getUserData());
			SWDJJBSJ djsj = null;
			try {
				// �����ҵ�Ǽǻ�����Ϣ
				djsj = InterfaceDj.getJBSJ_New(hdzssdsnbForm.getJsjdm(), vo.getUserData());
			} catch (Exception ex1) {
				throw new ApplicationException("û�и���˰�˵ĵǼ���Ϣ����û��Ȩ�޲鿴����˰����Ϣ��");
			}
			//��ȡ�Ƿ����¿���  �ǣ�Y ��N 
			hdzssdsnbForm.setSfxkh(util.getSfxkh(nd,djsj));
			//��ȡ˰�����������������һ������շ�ʽ
			hdzssdsnbForm.setSyndZsfsdm(util.getSyndZsfsDm(hdzssdsnbForm.getJsjdm(), nd));
			//��ȡ˰�����������������һ��ȵĻ�������걨��Ϣ ������9��25 ������45��46��47
			Map map=util.getSyndHsqjSbxx(hdzssdsnbForm.getSyndZsfsdm(),hdzssdsnbForm.getJsjdm(), nd);
			hdzssdsnbForm.setSyndZbh6(map.get("syndZbh6").toString());
			hdzssdsnbForm.setSyndZbh25(map.get("syndZbh25").toString());
			hdzssdsnbForm.setSyndFb5jyjg(map.get("syndFb5jyjg").toString());
			System.out.println("��ҵ��������1_" + hdzssdsnbForm.getQyzslx());
			// ˰�Ѻ˶���Ϣ
			this.getHdxx(hdzssdsnbForm);
			System.out.println("��ҵ��������2_" + hdzssdsnbForm.getQyzslx());
			/* ���շ�ʽ */
			String zsfs = hdzssdsnbForm.getZsfs();

			System.out.println(hdzssdsnbForm.getJsjdm() + "�����շ�ʽ���룺" + zsfs);

			if (zsfs == null || (zsfs != null && zsfs.equals(""))) {
				throw new ApplicationException("û�в�ѯ������ҵ�����շ�ʽ�϶���Ϣ�����϶����ٽ����걨¼�룡");
			}
			if (CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
				throw new ApplicationException(
						"����ҵ���϶�Ϊ�������ջ��������ڴ�¼�룬��¼��������ռ����걨��");
			}

			// ����QysdsReportsDeclare����
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			// ��form�еĻ�����Ϣת��QysdsReportsDeclare report ��
			QysdsHdzsNbUtil2014.setQysdsReport(report, hdzssdsnbForm);
			// ����QysdsReportsTableDeclare�Ļ�����Ϣ
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(QysdsConstant2014.TABLE_ID_2014_24);
			table.setTableName(QysdsConstant2014.TABLE_NAME_2014_24);
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
					.get(QysdsConstant2014.TABLE_ID_2014_24);

			if (table.getCellContentList().size() > 0) {
				hdzssdsnbForm.setSbrq(TinyTools.Date2String(report.getSbrq(), "yyyyMMdd"));
				hdzssdsnbForm.setSkssksrq(TinyTools.Date2String(report.getSkssksrq(), "yyyyMMdd"));
				hdzssdsnbForm.setSkssjsrq(TinyTools.Date2String(report.getSkssjsrq(), "yyyyMMdd"));
			}

			// �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
			int[] arrs = { 1, 19 };
			hdzssdsnbForm.setQysdsnbList(this.translate2Page(putSpace(table, arrs), zsfs));

			// �����ã���ɺ�Ҫɾ��
//			System.out.println("���������-" + hdzssdsnbForm.getJsjdm());
//			System.out.println("�걨����-" + hdzssdsnbForm.getSbrq());
//			System.out.println("��˰������-" + hdzssdsnbForm.getNsrmc());
//			System.out.println("˰�����-" + hdzssdsnbForm.getSknd());
//			System.out.println("�ں�-" + hdzssdsnbForm.getQh());
//			System.out.println("����������-" + hdzssdsnbForm.getBbqlx());
//			System.out.println("˰��������ʼ����-" + hdzssdsnbForm.getSkssksrq());
//			System.out.println("˰��������������-" + hdzssdsnbForm.getSkssjsrq());
//			System.out.println("˰�������֯��������-" + hdzssdsnbForm.getSwjgzzjgdm());
//			System.out.println("���ش���-" + hdzssdsnbForm.getQxdm());
//			System.out.println("¼����-" + hdzssdsnbForm.getLrr());
//			System.out.println("˰�������������-" + hdzssdsnbForm.getSwjsjdm());
//			System.out.println("��ҵ��������_" + hdzssdsnbForm.getQyzslx());

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
		// �õ�Action���ݹ���HdzssdsnbForm����

		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) vo.getData();
		Connection conn = null;

		// Map getsbnd = this.quarterSkssrq(SfDateUtil.getDate(hdzssdsnbForm.getSbrq()));
		// ��ȡ˰����������
		String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(hdzssdsnbForm
				.getSkssjsrq()));
		// ��ȡ˰���������
		// String sknd = this.getNd(jd, getsbnd, hdzssdsnbForm.getSbrq());
		// String nd = (String)getsbnd.get(Skssrq.SKSSRQ_ND);
		// ��ҳ����ȡ��˰�������ں����
		String nd = hdzssdsnbForm.getSkssksrq().substring(0, 4);
		// ���ü���
		//hdzssdsnbForm.setQh(jd);
		hdzssdsnbForm.setQh("1");
		// �������
		hdzssdsnbForm.setSknd(nd);
		System.out.println("=====doSave=======");
		try {

			// /* ���շ�ʽ */
			String zsfs = hdzssdsnbForm.getZsfs();
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��hdzssdsnbForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this.translate2Interface(hdzssdsnbForm, zsfs);
			report.setVersion(QysdsConstant2014.QYSDS_VERSION_HDZSQYSDSNB_2014);

			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ����saveSingleTable�����������ݱ���
			iApp.saveSingleTable(report);

			// ��ȡһ�����п�ֵ��QysdsReportsTableDeclare����
			QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report
					.getTableContentList().get(QysdsConstant2014.TABLE_ID_2014_24);
			table.getCellContentList().clear();

			// �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
			int[] arrs = { 1, 17 };

			hdzssdsnbForm.setQysdsnbList(null);

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
	private QysdsReportsDeclare translate2Interface(HdzssdsnbForm form,String zsfs) {

		// ����QysdsReportsDeclare����
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		// ��form�еĻ�����Ϣת��QysdsReportsDeclare������
		QysdsNewUtil.setQysdsReport(report, form);

		// ������ҵ����˰�����ڵ����������󣬲����������Ϣ
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(QysdsConstant2014.TABLE_ID_2014_24);
		table.setTableName(QysdsConstant2014.TABLE_NAME_2014_24);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ��ҳ�����ݷ�������ݿ�ӿ���������ݸ�ʽ
		List list = form.getQysdsnbList();

		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			String hc = (String) map.get("hc");
			if(zsfs.equals(CodeConstant.ZSFSDM_CYLZS)){
				if ("1".equals(hc)) {
					QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
					item_1.setItemID("1");
					item_1.setItemValue((String) map.get("lje"));
					System.out.println("����lje1"+(String) map.get("lje"));
					item_1.setItemType("11");
					table.getCellContentList().put(item_1.getItemID(), item_1);
				}
				if ("2".equals(hc)) {
					QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
					item_1.setItemID("2");
					item_1.setItemValue((String) map.get("lje"));
					System.out.println("����lje2"+(String) map.get("lje"));
					item_1.setItemType("11");
					table.getCellContentList().put(item_1.getItemID(), item_1);
				}
				if ("3".equals(hc)) {
					QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
					item_1.setItemID("3");
					item_1.setItemValue((String) map.get("lje"));
					System.out.println("����lje3"+(String) map.get("lje"));
					item_1.setItemType("11");
					table.getCellContentList().put(item_1.getItemID(), item_1);
				}
				if ("4".equals(hc)) {
					QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
					item_1.setItemID("4");
					item_1.setItemValue((String) map.get("lje"));
					System.out.println("����lje4"+(String) map.get("lje"));
					item_1.setItemType("11");
					table.getCellContentList().put(item_1.getItemID(), item_1);
				}
				if ("5".equals(hc)) {
					QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
					item_1.setItemID("5");
					item_1.setItemValue((String) map.get("lje"));
					System.out.println("����lje5"+(String) map.get("lje"));
					item_1.setItemType("11");
					table.getCellContentList().put(item_1.getItemID(), item_1);
				}
				if ("6".equals(hc)) {
					QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
					item_1.setItemID("6");
					item_1.setItemValue((String) map.get("lje"));
					System.out.println("����lje6"+(String) map.get("lje"));
					item_1.setItemType("11");
					table.getCellContentList().put(item_1.getItemID(), item_1);
				}				
				if ("10".equals(hc)) {
					QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
					item_1.setItemID("10");
					item_1.setItemValue((String) map.get("lje"));
					System.out.println("����lje10"+(String) map.get("lje"));
					item_1.setItemType("11");
					table.getCellContentList().put(item_1.getItemID(), item_1);

				}
			}
			if ("11".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("11");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("����lje11"+(String) map.get("lje"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
			if ("12".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("12");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("����lje12"+(String) map.get("lje"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
			if ("13".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("13");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("����lje13"+(String) map.get("lje"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
			if ("14".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("14");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("����lje14"+(String) map.get("lje"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
			
			if ("16".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("16");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("����lje16"+(String) map.get("lje"));
				item_1.setItemType("16");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
			if ("17".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("17");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("����lje17"+(String) map.get("lje"));
				item_1.setItemType("17");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
			if ("18".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("18");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("����lje18"+(String) map.get("lje"));
				item_1.setItemType("18");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
			if ("19".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("19");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("����lje19"+(String) map.get("lje"));
				item_1.setItemType("19");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
		}

		report.getTableContentList().put(table.getTableId(), QysdsHdzsNbUtil2014.cleanSpace(table));
		return report;
	}

	/**
	 * ���ӿ����ݽṹ�е�����ת��������ҳ��Ҫ������ݽṹ �ӿ����ݽṹ-->ҳ�����ݽṹ
	 *
	 * @param QysdsReportsTableDeclare
	 * @return ҳ������ݵ�List����
	 */
	private List translate2Page(QysdsReportsTableDeclare table,String zsfs) {

		// ����ÿ��������Ӧ��map
		HashMap map1 = new HashMap();
		HashMap map2 = new HashMap();
		HashMap map3 = new HashMap();
		HashMap map4 = new HashMap();
		HashMap map5 = new HashMap();
		HashMap map6 = new HashMap();
		HashMap map7 = new HashMap();
		HashMap map8 = new HashMap();
		HashMap map9 = new HashMap();
		HashMap map10 = new HashMap();
		HashMap map11 = new HashMap();
		HashMap map12 = new HashMap();
		HashMap map13 = new HashMap();
		HashMap map14 = new HashMap();
		
		HashMap map16 = new HashMap();
		HashMap map17 = new HashMap();
		HashMap map18 = new HashMap();
		HashMap map19 = new HashMap();
		// ����List�����������ҳ�������
		ArrayList pagelist = new ArrayList();
		// ת������е�����
		//����������
		if(zsfs.equals(CodeConstant.ZSFSDM_CYLZS)){
			map1.put("hc", "1");
			map1.put("lje", ((QysdsReportsItemDeclare) table.getCellContentList()
					.get("1")).getItemValue());
			pagelist.add(map1);

			map2.put("hc", "2");
			map2.put("lje", ((QysdsReportsItemDeclare) table.getCellContentList()
					.get("2")).getItemValue());
			pagelist.add(map2);

			map3.put("hc", "3");
			map3.put("lje", ((QysdsReportsItemDeclare) table.getCellContentList()
					.get("3")).getItemValue());
			pagelist.add(map3);

			map4.put("hc", "4");
			map4.put("lje", ((QysdsReportsItemDeclare) table.getCellContentList()
					.get("4")).getItemValue());
			pagelist.add(map4);

			map5.put("hc", "5");
			map5.put("lje", ((QysdsReportsItemDeclare) table.getCellContentList()
					.get("5")).getItemValue());
			pagelist.add(map5);

			map6.put("hc", "6");
			map6.put("lje", ((QysdsReportsItemDeclare) table.getCellContentList()
					.get("6")).getItemValue());
			pagelist.add(map6);
			
			map10.put("hc", "10");
			map10.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
					.get("10")).getItemValue());
			pagelist.add(map10);

		}
		//��������
		if(zsfs.equals(CodeConstant.ZSFSDM_DEZS)){
			map1.put("hc", "1");
			map1.put("lje","*");
			pagelist.add(map1);

			map2.put("hc", "2");
			map2.put("lje", "*");
			pagelist.add(map2);

			map3.put("hc", "3");
			map3.put("lje", "*");
			pagelist.add(map3);
			
			map4.put("hc", "4");
			map4.put("lje","*");
			pagelist.add(map4);

			map5.put("hc", "5");
			map5.put("lje", "*");
			pagelist.add(map5);

			map6.put("hc", "6");
			map6.put("lje", "*");
			pagelist.add(map6);
			
			map10.put("hc", "10");
			map10.put("lje","*");
			pagelist.add(map10);
		}

		map7.put("hc", "7");
		map7.put("lje","*");
		pagelist.add(map7);

		map8.put("hc", "8");
		map8.put("lje","*");
		pagelist.add(map8);

		map9.put("hc", "9");
		map9.put("lje","*");
		pagelist.add(map9);



		map11.put("hc","11");
		map11.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("11")).getItemValue());
		pagelist.add(map11);

		map12.put("hc","12");
		map12.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("12")).getItemValue());
		pagelist.add(map12);

		map13.put("hc","13");
		map13.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("13")).getItemValue());
		pagelist.add(map13);
		
		map14.put("hc","14");
		map14.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("14")).getItemValue());
		pagelist.add(map14);
		
		map16.put("hc","16");
		map16.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("16")).getItemValue());
		pagelist.add(map16);
		
		map17.put("hc","17");
		map17.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("17")).getItemValue());
		pagelist.add(map17);
		
		map18.put("hc","18");
		map18.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("18")).getItemValue());
		pagelist.add(map18);
		
		map19.put("hc","19");
		map19.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("19")).getItemValue());
		pagelist.add(map19);
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
		Timestamp skssksrq = QysdsNewUtil.getTimestamp(form.getSkssksrq());
		Timestamp skssjsrq = QysdsNewUtil.getTimestamp(form.getSkssjsrq());

		System.out.println(form.getJsjdm() + "sbrq��" + sbrq);
		System.out.println(form.getJsjdm() + "skssksrq��" + skssksrq);
		System.out.println(form.getJsjdm() + "skssjsrq��" + skssjsrq);
        System.out.println(form.getJsjdm() + "qh��" + form.getQh());

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
						CodeConstant.SFGL_QYSDS_BBFS_NB);
			} else if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR)) {
				/* ���Ϊ���ļ��ȣ���ȡ��ҵ����˰�϶���Ϣʱ���걨����ȡ */

				if (form.getQh() == null
						|| (form.getQh() != null && form.getQh().trim().equals(
								""))) {
					/* �ںŲ���Ϊ�գ����Ϊ���׳��쳣 */
					throw new ApplicationException("ϵͳ�����쳣���ں�Ϊ�գ�����ϵͳ����Ա��ϵ��");
				}
				if ("4".equals(form.getQh())) {
					qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
							skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_NB);
				} else {
					qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
							skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_JB);
                    //����Zsfs
                    //Zsfs zsfs = util.getZsfsInfo(jsjdm, skssjsrq);
                    //qysdsSet.setZsfs(zsfs);
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
//	private void insertJmJb(String jmje, hdzssdsnbForm form,
//			SfDBAccess dbAgent, UserData ud, SWDJJBSJ djsj, Timestamp ts_cjrq,
//			Timestamp ts_lrrq, Timestamp ts_sbrq, Timestamp ts_Skssjsrq,
//			Timestamp ts_Skssksrq, String nd) {
//		Connection con = null;
//		CallableStatement st = null;
//		String sql = "";
//
//		Timestamp t1, t2;
//
//		t1 = new Timestamp(System.currentTimeMillis());
//
//		try {
//			// ����ҵ����˰�������걨�ļ����ֶ���ֵ����˰���м����ʸ��ʱ��Ҫ�������걨��������ݣ�
//			// �����޸ģ�
//			// ����������Ѿ�����������ˣ������ݼ��˱�ʶΪδ���ˣ���Ҫ�޸ļ����걨��ļ������ֶΣ�
//			// ����������Ѿ�����������ˣ������ݼ��˱�ʶΪ�Ѽ��ˣ������ٲ������ݣ�
//			// ���������û�в�������ݣ������һ�����ݣ�
//			//
//			// �����ϼ���˰�걨��ʱ����Ʋ���¼����ҵ����˰�ļ������ݣ������걨��˰��˰Ŀ�¹��˵���ҵ����˰��˰��˰Ŀ��
//			// ��ҳ�����ݵļ������Ϊ�ջ�0���Ҿ߱�һ������ʸ�Ľ���
//			// if (!"0".equals(jmje) && !"".equals(jmje) && form.getYbjmsl() !=
//			// null &&
//			// !form.getYbjmsl().equals(""))
//			// {
//			if ((jmje == null) || ("0.00".equals(jmje)) || ("".equals(jmje))) {
//				jmje="0";
//			}
//				Jm jm = new Jm();
//				// ����ֵ�������
//				jm.setCjrq(ts_cjrq);
//				jm.setJsjdm(form.getJsjdm());
//				jm.setJmlx(CodeConstant.JMLX_SP);
//				jm.setSzsmdm(CodeConstant.SZSM_QYSDSCODE);
//				jm.setSbrq(ts_sbrq);
//				jm.setLrrq(ts_lrrq);
//				jm.setFsdm(CodeConstant.FSDM_SMSB);
//				jm.setJzbz(CodeConstant.SMSB_JZBZ);
//				jm.setJmse(new BigDecimal(jmje));
//				jm.setJsje(new BigDecimal(jmje));
//				jm.setLrr(ud.getYhid());
//				jm.setSkssjsrq(ts_Skssjsrq);
//				jm.setSkssksrq(ts_Skssksrq);
//				jm.setSwjgzzjgdm(djsj.getSwjgzzjgdm());
//				// jm.setQxdm(InterfaceDj.getQxdm(ud));
//				jm.setQxdm(djsj.getSwjgzzjgdm().substring(0, 2));
//				jm.setDjzclxdm(djsj.getDjzclxdm());
//				jm.setGjbzhydm(djsj.getGjbzhydm());
//				jm.setNd(nd);
//
//				Date date = TinyTools.stringToDate(form.getSbrq(), "yyyyMMdd");
//				// ����������
//				ServiceProxy proxy = new ServiceProxy();
//				String jmxmdm = proxy.getJmsbs(form.getJsjdm(),
//						CodeConstant.SZSM_QYSDS, ts_Skssksrq, ts_Skssjsrq);
//				Debug.out("���������� =" + jmxmdm);
//				System.out.println("���������� ="+jmxmdm);
//				// ������Ŀ����Ϊ�գ��ӱ���ȡ����ҵ�����������жϼ��ⷽʽ
//				if (jmxmdm == null) {
//
//					System.out
//							.println("**************   ȡ�õļ�����Ŀ����Ϊ�գ������걨�ļ�������putJm�е���ҵ��������form.getQyzslx()Ϊ:"
//									+ form.getQyzslx());
//
//					jmxmdm = CodeConstant.JMLXOTHER;
//					jm.setJmlx(CodeConstant.JMLX_FD);//�����Լ�������
//
//
//				}
//
//				if (jmxmdm != null && !("".equals(jmxmdm))) {
//
//					jm.setJmxmdm(jmxmdm);
//					// ȡ��Ԥ�㼶�δ���
//					Ysjc ysjc = null;
//					try {
//						ysjc = JksUtil.getYsjc(form.getJsjdm(),
//								CodeConstant.SZSM_QYSDS, SfDateUtil
//										.getDate(form.getSbrq()));
//					} catch (Exception e) {
//						throw new ApplicationException("�ü���������Ԥ�㼶�δ���û�м�¼��");
//					}
//					if (ysjc != null) {
//						Debug.out("���� =" + ysjc.getYsjcdm());
//					} else {
//						throw new ApplicationException("�ü���������Ԥ�㼶�δ���û�м�¼��");
//					}
//					// ȡ��Ԥ���Ŀ����
//					com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm yskm = null;
//					try {
//						yskm = JKAdapter.getInstance().getYskm(
//								CodeConstant.SZSM_QYSDSCODE,
//								djsj.getDjzclxdm(), djsj.getGjbzhydm(),
//								ysjc.getYsjcdm());
//					} catch (Exception e) {
//						throw new ApplicationException("�ü���������Ԥ���Ŀ����û�м�¼��");
//					}
//					if (yskm != null) {
//						Debug.out("Ԥ���Ŀ =" + yskm.getYskmdm());
//					} else {
//						throw new ApplicationException("�ü���������Ԥ���Ŀ����û�м�¼��");
//					}
//
//					jm.setYsjcdm(ysjc.getYsjcdm());
//					jm.setYskmdm(yskm.getYskmdm());
//
//					try {
//
//						String jsjdm = jm.getJsjdm();
//						String jmlx = jm.getJmlx();
//						String szsmdm = jm.getSzsmdm();
//						// String sbrq = df.format(now);
//						Timestamp sbrq = jm.getSbrq();
//						String fsdm = jm.getFsdm();
//						String jzbz = jm.getJzbz();
//						String lrr = jm.getLrr();
//						// String skssjsrq = df.format(declare.getSkssjsrq());
//						// String skssksrq = df.format(declare.getSkssksrq());
//						Timestamp skssjsrq = jm.getSkssjsrq();
//						Timestamp skssksrq = jm.getSkssksrq();
//						String swjgzzjgdm = jm.getSwjgzzjgdm();
//						String qxdm = jm.getQxdm();
//						String djzclxdm = jm.getDjzclxdm();
//						String gjbzhydm = jm.getGjbzhydm();
//						// String nd = jm.getNd();
//						String ysjcdm = jm.getYsjcdm();
//						String yskmdm = jm.getYskmdm();
//						// String jmxmdm = jm.getJmxmdm();
//						BigDecimal jmse = jm.getJmse();
//
//						// �������ݿ�����
//						con = SfDBResource.getConnection();
//						sql = "BEGIN sbdb.sb_pkg_sbjmtools.insertjm_jb(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); END;";
//
//						st = con.prepareCall(sql);
//
//						System.out
//								.println("-------------���ò�������걨�洢���̲���----------------");
//						System.out.println("1-jsjdm--" + jsjdm);
//						System.out.println("2-jmlx--" + jmlx);
//						System.out.println("3-szsmdm--" + szsmdm);
//						System.out.println("4-sbrq--" + sbrq);
//						System.out.println("5-fsdm--" + fsdm);
//						System.out.println("6-jzbz--" + jzbz);
//						System.out.println("7-lrr--" + lrr);
//						System.out.println("8-skssjsrq--" + skssjsrq);
//						System.out.println("9-skssksrq--" + skssksrq);
//						System.out.println("10-swjgzzjgdm--" + swjgzzjgdm);
//						System.out.println("11-qxdm--" + qxdm);
//						System.out.println("12-djzclxdm--" + djzclxdm);
//						System.out.println("13-gjbzhydm--" + gjbzhydm);
//						System.out.println("14-nd--" + nd);
//						System.out.println("15-ysjcdm--" + ysjcdm);
//						System.out.println("16-yskmdm--" + yskmdm);
//						System.out.println("17-jmxmdm--" + jmxmdm);
//						System.out.println("18-jmse--" + jmse);
//
//						st.setString(1, jsjdm);
//						st.setString(2, jmlx);
//						st.setString(3, szsmdm);
//						st.setTimestamp(4, sbrq);
//						st.setString(5, fsdm);
//
//						st.setString(6, jzbz);
//						st.setString(7, lrr);
//						st.setTimestamp(8, skssjsrq);
//						st.setTimestamp(9, skssksrq);
//						st.setString(10, swjgzzjgdm);
//
//						st.setString(11, qxdm);
//						st.setString(12, djzclxdm);
//						st.setString(13, gjbzhydm);
//						st.setString(14, nd);
//						st.setString(15, ysjcdm);
//
//						st.setString(16, yskmdm);
//						st.setString(17, jmxmdm);
//						st.setBigDecimal(18, jmse);
//
//						st.execute();
//
//					} catch (Exception ex4) {
//						throw new ApplicationException(
//								"��������걨��ʧ�ܣ����ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
//					}
//
//				}
//
//
//		} catch (Exception ex) {
//			// �׳��쳣
//			ex.printStackTrace();
//		} finally {
//			SfDBResource.freeConnection(con);
//		}
//
//		t2 = new Timestamp(System.currentTimeMillis());
//
//		System.out.println("��������걨�����ݺ�ʱ��" + (t2.getTime() - t1.getTime()));
//
//	}
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

		// �õ�Action���ݹ���hdzssdsnbForm����
		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			/* ���շ�ʽ */
			String zsfs = hdzssdsnbForm.getZsfs();
			// ��ActionForm�еĻ�����Ϣת��QysdsReportsDeclare������
			QysdsReportsDeclare report = this.translate2Interface(
					hdzssdsnbForm, zsfs);

			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ����deleteSingleTable������������ɾ��
			iApp.deleteSingleTable(report);

			// ��ȡһ�����п�ֵ��QysdsReportsTableDeclare����
			QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report
					.getTableContentList().get(
							QysdsConstant2014.TABLE_ID_2014_24);
			table.getCellContentList().clear();

			// �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
			int[] arrs = { 1, 15 };
			hdzssdsnbForm.setQysdsnbList(null);

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
	
}
