package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.hdzssdsnb.processor;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.hdzssdsnb.web.HdzssdsnbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.hdzsssyhmx.web.HdzsssyhmxForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
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

public class HdzssdsnbProcessor implements Processor {

	// ��ҵ����˰˰��
	private static final String QYSDS_SL = "0.25";

    private QysdsUtil2009 util = new QysdsUtil2009();

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
		// �õ�Action���ݹ���HdzssdsnbForm����
		HdzssdsnbForm form = (HdzssdsnbForm) vo.getData();
		// �õ���ǰʱ���������
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
//		Map getsbjd = this.quarterSkssrq(curTime);
                Map nbqj = Skssrq.yearSkssrq(curTime);
		Timestamp skssksrq = (Timestamp) nbqj.get(Skssrq.SKSSKSRQ);
		Timestamp skssjsrq = (Timestamp) nbqj.get(Skssrq.SKSSJSRQ);
		// ˰��������ʼ����
		form.setSkssksrq(SfTimeUtil.getDateFromDateTime(skssksrq));
		// ˰��������������
		form.setSkssjsrq(SfTimeUtil.getDateFromDateTime(skssjsrq));
		// ˰���걨����
		form.setSbrq(SfDateUtil.getDate());
//		Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(form.getSbrq()));
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

		HdzssdsnbForm form = (HdzssdsnbForm) vo.getData();

		Connection conn = SfDBResource.getConnection();;

		try {
			Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(form.getSbrq()));
			form.setSknd((String) qj.get(Skssrq.SKSSRQ_ND));
			/* ͨ��˰����������ȡ��˰����� */
			//��������ʱ�����ظ���ѯ������Ϣ
//			if (!form.isFbreturnbs()) {
//				form.setSknd(form.getSkssksrq().substring(0, 4));
//				form = (HdzssdsnbForm) QysdsUtil2009.queryQysdsJbxx(conn, form);
//			}

//			 �����ں�
			form.setQh("1");
			// ��������������Ϣ
			
			SWDJJBSJ djsj = null;
			
				// �����ҵ�Ǽǻ�����Ϣ

			djsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), vo
					.getUserData());
			
			form.setNsrsbh(djsj.getSwdjzh()); 
			// ��˰������
			form.setNsrmc(djsj.getNsrmc()); 
			// ������������-�Ǽ�ע�����ʹ���
			form.setSsjjlx(djsj.getDjzclxdm());
			// ע���ַ��ϵ�绰
			form.setLxdh(djsj.getZcdzlxdh()); 
			// ��Ӫ��ַ
			form.setJydz(djsj.getJydz());
			// ������ҵ����
			form.setSshy(djsj.getGjbzhydm());
			// ˰�������֯��������
			form.setSwjgzzjgdm(djsj.getSwjgzzjgdm());
			// ˰�������֯��������
			form.setZgswjgzzjgmc(djsj.getSwjgzzjgmc()); 
			// ���ش���
			form.setQxdm(djsj.getQxdm());
			form.setQxdm(djsj.getSwjgzzjgdm().substring(0, 2));		
//			 ˰����Ϣ
			this.getHdxx(form);
			/* ���շ�ʽ */
			String zsfs = form.getZsfs();
			if (zsfs == null || (zsfs != null && zsfs.equals(""))) {
				throw new ApplicationException("û�в�ѯ������ҵ�����շ�ʽ�϶���Ϣ�����϶����ٽ����걨¼�룡");
			}
			if (CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
				throw new ApplicationException(
						"����ҵ���϶�Ϊ�������ջ��������ڴ�¼�룬��¼�������������걨��");
			}

			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsUtil2009.setQysdsReport(report, form);
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2009_12);
			table.setTableName(CodeConstant.TABLE_NAME_2009_12);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(), table);
			// ��ȡ���ݿ�ӿڣ�����query�����������ݲ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_2009_12);
			int[] arra = { 1, 10 };
			List list = this.translate2Page(QysdsUtil2009.putSpace(
					table, arra));
			if (form.isFbreturnbs()) {
				HashMap backMap = new HashMap();
				backMap.put("hc", "6");
				backMap.put("je", Double.toString(form.getJmshj()));
				list.add(backMap);
			}
			form.setDataList(list);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return form;
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
		HdzssdsnbForm form = (HdzssdsnbForm) vo.getData();
		Connection conn = null;
		try {
			//��ȡ���ݿ�����
			conn = SfDBResource.getConnection();
			//��ActionForm�е����ݽṹ6ת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(form);
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
//			�������״̬Ϊ������ɹ���
			iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);
//			wlyd(form, conn);
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
		//��ҵ����˰������������
		QysdsReportsDeclare report =new QysdsReportsDeclare();
		QysdsUtil2009.setQysdsReport(report,form);
		//��ҵ����˰�����ڵ�����������
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_2009_12);
		table.setTableName(CodeConstant.TABLE_NAME_2009_12);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		List list=form.getDataList();
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
			String hc = (String) map.get("hc");
			item.setItemID(hc);
			item.setItemValue((String) map.get("je"));
			item.setItemType("11");
			table.getCellContentList().put(item.getItemID(), item);
		}
		report.getTableContentList().put(table.getTableId(), QysdsUtil2009.cleanSpace(table));
		return report;
  }
	private QysdsReportsDeclare translate2Interface_fb(HdzssdsnbForm form) {
		// ��ҵ����˰������������
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		QysdsUtil2009.setQysdsReport(report, form);
		// ��ҵ����˰�����ڵ�����������
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_2009_13);
		table.setTableName(CodeConstant.TABLE_NAME_2009_13);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		for (int i = 1; i < 48; i++) {
			QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
			String hc = "" + i;
			item.setItemID(hc);
			item.setItemValue("");
			item.setItemType("11");
			table.getCellContentList().put(item.getItemID(), item);
		}
		report.getTableContentList().put(table.getTableId(),
				QysdsUtil2009.cleanSpace(table));
		return report;
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
//                    Zsfs zsfs = util.getZsfsInfo(jsjdm, skssjsrq);
//                    qysdsSet.setZsfs(zsfs);
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
	private void insertJmJb(String jmje, HdzssdsnbForm form,
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
			if ((jmje == null) || ("0.00".equals(jmje)) || ("".equals(jmje))) {
				jmje="0";
			}
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
				System.out.println("���������� ="+jmxmdm);
				// ������Ŀ����Ϊ�գ��ӱ���ȡ����ҵ�����������жϼ��ⷽʽ
				if (jmxmdm == null) {

					System.out
							.println("**************   ȡ�õļ�����Ŀ����Ϊ�գ������걨�ļ�������putJm�е���ҵ��������form.getQyzslx()Ϊ:"
									+ form.getQyzslx());

					jmxmdm = CodeConstant.JMLXOTHER;
					jm.setJmlx(CodeConstant.JMLX_FD);//�����Լ�������


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


		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
		}

		t2 = new Timestamp(System.currentTimeMillis());

		System.out.println("��������걨�����ݺ�ʱ��" + (t2.getTime() - t1.getTime()));

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

		HdzssdsnbForm form = (HdzssdsnbForm) vo.getData();
		Connection conn = null;
		try {
			// ��ȡ���ݿ�ӿ�
			conn = SfDBResource.getConnection();

			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			//��ɾ������
			QysdsReportsDeclare report_fb = this
			.translate2Interface_fb(form);
			iApp.deleteSingleTable(report_fb);

			QysdsReportsDeclare report = this
					.translate2Interface(form);

			// ��ȡ���ݿ�ӿڣ�����delete������������ɾ��,iApp����һ��report����

			iApp.deleteSingleTable(report);
			iApp.updateCheckStatus(report, "");
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2009_12);
			table.setTableName(CodeConstant.TABLE_NAME_2009_12);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			int[] arra = { 1, 10 };
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_2009_12);
			form.setDataList(this.translate2Page(QysdsUtil2009
					.putSpace(table, arra)));

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return form;
	}
	/**
	 * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ �ӿ����ݽṹ-->ҳ�����ݽṹ
	 *
	 * @param QysdsReportsTableDeclare
	 * @return ��ҵ����˰������������
	 */
	private List translate2Page(QysdsReportsTableDeclare table) {

		List list = new ArrayList();
		HashMap map = (HashMap) table.getCellContentList();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			HashMap backMap = new HashMap();
			String key = (String) it.next();
			QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) map
					.get(key);
			backMap.put("hc", item.getItemID());
			backMap.put("je", item.getItemValue());
			list.add(backMap);
		}

		for (int i = 0; i < list.size(); i++) {
//			System.out.println("==2page list content==" + list.get(i));
		}

		return list;
	}
}
