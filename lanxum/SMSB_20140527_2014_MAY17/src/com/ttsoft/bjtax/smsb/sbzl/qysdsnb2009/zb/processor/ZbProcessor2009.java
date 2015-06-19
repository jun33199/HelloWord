/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.zb.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
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
import com.syax.creports.check.Checker;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.zb.web.ZbForm2009;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.JksUtil;
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
 * Description:��ҵ����˰�����˰�걨��
 * </p>
 * 
 * @author liwenhua
 * @version 1.1
 */

public class ZbProcessor2009 implements Processor {

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
		ZbForm2009 zbForm = (ZbForm2009) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();

			// ����QysdsReportsDeclare����
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			// ��form�еĻ�����Ϣת��QysdsReportsDeclare report ��
			QysdsUtil2009.setQysdsReport(report, zbForm);
			// ����QysdsReportsTableDeclare�Ļ�����Ϣ
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2009_ZB);
			table.setTableName(CodeConstant.TABLE_NAME_2009_ZB);
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
					.get(CodeConstant.TABLE_ID_2009_ZB);
			// �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
			int[] arrs = { 1, 42 };
			zbForm.setDataList(this.translate2Page(QysdsUtil2009.putSpace(table,
					arrs)));
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ��ѯ�ɹ�����czqysdsjbForm
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
		// �õ�Action���ݹ���ZbForm����
		ZbForm2009 zbForm = (ZbForm2009) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��ActionForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this.translate2Interface(zbForm);
			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ����saveSingleTable�����������ݱ���
			iApp.saveSingleTable(report);
			// �������״̬Ϊ������ɹ���
			iApp.updateCheckStatus(report, Constants.QYSDS_SHZT_SAVE);

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
		// �õ�Action���ݹ���ZbForm����
		ZbForm2009 zbForm = (ZbForm2009) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��ActionForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this.translate2Interface(zbForm);
			// ��ȡУ��ӿ�
			Checker checker = CheckerFactory.getAInstance(conn,
					CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List listSingle = checker.checkSingeTable(report,
					Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			zbForm.setCheckList(listSingle);
			/* ���У��ͨ�������ýӿڱ������� */
			if (listSingle == null
					|| (listSingle != null && listSingle.size() == 0)) {
				iApp.saveSingleTable(report);
				// �������״̬Ϊ���������ͨ����
				iApp
						.updateCheckStatus(report,
								Constants.QYSDS_SHZT_SINGLE_PASS);

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

		ZbForm2009 zbForm = (ZbForm2009) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��ActionForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this.translate2Interface(zbForm);
			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ����saveSingleTable������������ɾ��
			iApp.deleteSingleTable(report);

			iApp.updateCheckStatus(report, "");

			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2009_ZB);
			table.setTableName(CodeConstant.TABLE_NAME_ZB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_2009_ZB);
			// ȡlist
			int[] arrs = { 1, 35 };
			zbForm.setDataList(this.translate2Page(QysdsUtil2009.putSpace(table,
					arrs)));

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
	 * @param zbForm
	 * @return ��ҵ����˰������������
	 */
	private QysdsReportsDeclare translate2Interface(ZbForm2009 form) {

		// ��ҵ����˰������������
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		// ��form�еĻ�����Ϣת��QysdsReportsDeclare������
		QysdsUtil2009.setQysdsReport(report, form);
		// ������ҵ����˰�����ڵ����������󣬲����������Ϣ
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_2009_ZB);
		table.setTableName(CodeConstant.TABLE_NAME_2009_ZB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);

		// ��ҳ�����ݷ�������ݿ�ӿڵ����ݸ�ʽ
		List list = form.getDataList();
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
			item.setItemID((String) map.get("hc"));
			item.setItemValue((String) map.get("ljje"));
			item.setItemType("11");
			table.getCellContentList().put(item.getItemID(), item);
		}
		report.getTableContentList().put(table.getTableId(),
				QysdsUtil2009.cleanSpace(table));
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
		int i = 0;

		Iterator it = table.getCellContentList().keySet().iterator();
		Debug.out("----start---2009�� ��ҵ����˰ ����걨����----translate2Page");
		while (it.hasNext()) {
			QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
			String key = (String) it.next();
			item = (QysdsReportsItemDeclare) table.getCellContentList()
					.get(key);
			HashMap map = new HashMap();
			if(item.getItemID().equals("26")){
				map.put("hc", item.getItemID());
				map.put("ljje", "25");
			}else{
				map.put("hc", item.getItemID());
				map.put("ljje", item.getItemValue());
			}
			pagelist.add(map);
			Debug.out("�дΣ�"+item.getItemID()+"����ֵ��"+item.getItemValue());
		}
		Debug.out("----over---2009�� ��ҵ����˰ ����걨����----translate2Page");
		return pagelist;
	}

	/**
	 * ������ǰ�걨�ļ������ݱ��淽��
	 * 
	 * @param vo
	 * @throws BaseException
	 */
	private void saveJM(VOPackage vo) throws BaseException {

		ZbForm2009 form = (ZbForm2009) vo.getData();

		ServiceProxy proxy = new ServiceProxy();
		SfDBAccess dbAgent = null;
		Connection conn = null;

		conn = SfDBResource.getConnection();
		dbAgent = new SfDBAccess(conn);

		// ҳ������List
		List list = form.getDataList();

		/*
		 * ׼������
		 */

		// ������
		String jmje = form.getJmlx();
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			String hc = (String) map.get("hc");
			if (hc.equals("29")) {
				jmje = (String) map.get("ljje");
				break;
			}
		}

		// ˰���϶���Ϣ
		java.util.Date time = SfDateUtil.getDate(form.getSbrq());
		// QysdsSet sdsInfo = proxy.getQysdsInfo(
		// form.getJsjdm(),
		// time,
		// SfDateUtil.getDate(form.getSkssksrq()),
		// SfDateUtil.getDate(form.getSkssjsrq()),
		// CodeConstant.SFGL_QYSDS_BBFS_NB);
		QysdsSet sdsInfo = new QysdsSet();

		// ����Ա��Ϣ
		UserData ud = (UserData) vo.getUserData();

		SWDJJBSJ djsj = new SWDJJBSJ();
		/*
		 * ��ҵ����˰ͨ��������Ϣ�������ҵ������Ϣ����ͨ���Ǽ� �ʴ�form��ȡ���������Ϣ 2007.01.18 by wofei
		 */
		djsj.setGjbzhydm(form.getSshy());
		djsj.setSwjgzzjgdm(form.getSwjgzzjgdm());
		djsj.setDjzclxdm(form.getSsjjlx());

		// try {
		// // �����ҵ�Ǽǻ�����Ϣ
		// djsj = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
		// }
		// catch (Exception ex1) {
		// ex1.printStackTrace();
		// }
		// if (djsj == null) {
		// throw new ApplicationException("�����������ʱ��δ��ȡ���û���ҵ�ĵǼ���Ϣ��");
		// }

		form.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
		if (form.getCjrq() == null || "".equals(form.getCjrq())) {
			form.setCjrq(TinyTools.Date2String(new Date(),
					CodeConstant.DATETYPE));
		}

//		Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(
//				form.getCjrq(), CodeConstant.DATETYPE).getTime());
//		Timestamp ts_lrrq = new Timestamp(TinyTools.stringToDate(
//				form.getLrrq(), CodeConstant.DATETYPE).getTime());
//		Timestamp ts_sbrq = new Timestamp(TinyTools.stringToDate(
//				form.getSbrq(), "yyyyMMdd").getTime());
//		Timestamp ts_Skssjsrq = new Timestamp(TinyTools.stringToDate(
//				form.getSkssjsrq(), "yyyyMMdd").getTime());
//		Timestamp ts_Skssksrq = new Timestamp(TinyTools.stringToDate(
//				form.getSkssksrq(), "yyyyMMdd").getTime());

//		this.putJm(jmje, sdsInfo, form, dbAgent, ud, proxy, djsj, ts_cjrq,
//				ts_lrrq, ts_sbrq, ts_Skssjsrq, ts_Skssksrq, time);

	}

	/**
	 * �����������
	 * 
	 * @param jmje
	 *            ������
	 * @param sdsInfo
	 *            ˰���϶���Ϣ
	 * @param form
	 *            ¼������
	 * @param dbAgent
	 *            ���ݿ�����
	 * @param ud
	 *            ����Ա��Ϣ
	 * @param proxy
	 *            �ӿ�
	 * @param djsj
	 *            �Ǽǻ�����Ϣ
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
	 * @param time
	 *            ��ǰʱ��
	 */
	private void putJm(String jmje, QysdsSet sdsInfo, ZbForm2009 form,
			SfDBAccess dbAgent, UserData ud, ServiceProxy proxy, SWDJJBSJ djsj,
			Timestamp ts_cjrq, Timestamp ts_lrrq, Timestamp ts_sbrq,
			Timestamp ts_Skssjsrq, Timestamp ts_Skssksrq, java.util.Date time) {
		try {
			String jmzg = form.getJmlx();
			if (form.getJmlx() != null && !form.equals("")) {
				jmzg = "yes";
			} else {
				jmzg = "no";
			}
			if (!"".equals(jmje) && jmzg.equals("yes")) {
				// ����ҵ����˰�������걨�ļ����ֶ���ֵ����˰���м����ʸ��ʱ��Ҫ�������걨��������ݣ�
				// �����޸ģ�
				// ����������Ѿ�����������ˣ������ݼ��˱�ʶΪδ���ˣ���Ҫ�޸ļ����걨��ļ������ֶΣ�
				// ����������Ѿ�����������ˣ������ݼ��˱�ʶΪ�Ѽ��ˣ������ٲ������ݣ�
				// ���������û�в�������ݣ������һ�����ݣ�
				//
				// �����ϼ���˰�걨��ʱ����Ʋ���¼����ҵ����˰�ļ������ݣ������걨��˰��˰Ŀ�¹��˵���ҵ����˰��˰��˰Ŀ��

				Debug.out("�������");
				Jm jm = new Jm();
				Map dateMap = getSbrl(form.getSbrq());
				Vector vZb = new Vector();

				// vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
				vZb.add("qxdm='" + djsj.getSwjgzzjgdm().substring(0, 2) + "'");

				vZb.add("SKSSKSRQ = to_date('"
						+ String.valueOf(dateMap.get("ksrq")).substring(0, 10)
						+ "','yyyy-MM-dd')");
				vZb.add("SKSSJSRQ = to_date('"
						+ String.valueOf(dateMap.get("jsrq")).substring(0, 10)
						+ "','yyyy-MM-dd')");
				vZb.add(" (FSDM='" + CodeConstant.FSDM_WSSB + "' OR FSDM='"
						+ CodeConstant.FSDM_SMSB + "') ");

				// ����jzbz�����жϣ�ֻ��û�м������ݵ�ʱ��Ų����µ����ݣ�����м��������ڸ���jzbz���д���
				// vZb.add("jzbz like '" + CodeConstant.SMSB_JZBZ_UNEDIT + "%"
				// + "'");

				vZb.add("szsmdm ='" + CodeConstant.SZSM_QYSDSCODE + "'");
				vZb.add("jsjdm='" + form.getJsjdm() + "' ");
				List zbData = dbAgent.query(Jm.class, vZb);
				Debug.out("�鵽������Ϊ" + zbData.size());
				if (zbData.size() <= 0) {
					// ������û�в�������ݣ�����һ�����ݣ�
					try {
						// ɾ����ϸ����
						// dbAgent.delete(" qxdm='" + InterfaceDj.getQxdm(ud) +
						dbAgent
								.delete(
										" qxdm='"
												+ djsj.getSwjgzzjgdm()
														.substring(0, 2)
												+ "' and SKSSKSRQ = to_date('"
												+ String.valueOf(
														dateMap.get("ksrq"))
														.substring(0, 10)
												+ "','yyyy-MM-dd') and SKSSJSRQ = to_date('"
												+ String.valueOf(
														dateMap.get("jsrq"))
														.substring(0, 10)
												+ "','yyyy-MM-dd') "
												+ "and jzbz like '"
												+ CodeConstant.SMSB_JZBZ_EDIT
												+ "%" + "'" + "and ( FSDM='"
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

					com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm yskm = null;
					try {
						System.out.println("djsj.getDjzclxdm()--"
								+ djsj.getDjzclxdm());
						System.out.println("djsj.getGjbzhydm()--"
								+ djsj.getGjbzhydm());
						System.out.println("ysjc.getYsjcdm()--"
								+ ysjc.getYsjcdm());
						yskm = JKAdapter.getInstance().getYskm(
								CodeConstant.SZSM_QYSDSCODE,
								djsj.getDjzclxdm(), djsj.getGjbzhydm(),
								ysjc.getYsjcdm());

					} catch (Exception e) {
						e.printStackTrace();
						throw new ApplicationException("�ü���������Ԥ���Ŀ����û�м�¼��");
					}
					if (yskm != null) {
						Debug.out("Ԥ���Ŀ =" + yskm.getYskmdm());
					} else {
						throw new ApplicationException("�ü���������Ԥ���Ŀ����û�м�¼��");
					}
					Date date = TinyTools.stringToDate(form.getSbrq(),
							"yyyyMMdd");
					// ����˰����ӿ�
					// Update Start Zhou kejing 20031212
					String jmxmdm = proxy.getJmsbs(form.getJsjdm(),
							CodeConstant.SZSM_QYSDS,
							// time);
							SfDateUtil.getDate(form.getSkssksrq()), SfDateUtil
									.getDate(form.getSkssjsrq()));
					// Update End Zhou kejing 20031212
					Debug.out("���������� =" + jmxmdm);
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
					jm.setJmxmdm(jmxmdm);
					jm.setLrr(ud.getYhid());
					jm.setSkssjsrq(ts_Skssjsrq);
					jm.setSkssksrq(ts_Skssksrq);
					jm.setSwjgzzjgdm(djsj.getSwjgzzjgdm());
					// jm.setQxdm(InterfaceDj.getQxdm(ud));
					jm.setQxdm(djsj.getSwjgzzjgdm().substring(0, 2));
					jm.setDjzclxdm(djsj.getDjzclxdm());
					jm.setGjbzhydm(djsj.getGjbzhydm());
					jm.setNd(form.getSknd());
					jm.setYsjcdm(ysjc.getYsjcdm());
					jm.setYskmdm(yskm.getYskmdm());
					try {
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

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
		}
	}

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
		calendar.add(calendar.YEAR, -1);
		int year = calendar.get(calendar.YEAR);
		String nd = new Integer(year).toString();
		Timestamp ksrq;
		Timestamp jsrq;
		Map retMap = new HashMap();
		ksrq = new Timestamp(new GregorianCalendar(year, 0, 1).getTime()
				.getTime());
		jsrq = new Timestamp(new GregorianCalendar(year, 11, 31).getTime()
				.getTime());

		Map dateMap = new HashMap();
		dateMap.put("ksrq", ksrq);
		dateMap.put("jsrq", jsrq);
		return dateMap;
	}
}
