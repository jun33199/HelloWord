package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2012.processor;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.ekernel.db.or.ORManager;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.creports.bo.qysds.Jbxx;
import com.syax.creports.bo.qysds.QysdsReportsConstants;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.frame.util.DateTimeUtil;
import com.ttsoft.bjtax.dj.CAConstants;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.util.DjStringUtil;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2012.Constant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2012.bo.HdzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2012.xmlvo.HdzssdsnbVO;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * @version 1.0 2012�˶�������ҵ����˰�걨processor��
 */
public class HdzssdsnbProcessor implements Processor {
//	String[] HCItem = { "1", "2", "3", "11", "12", "13", "14","15" };	
//	String[] HCName = { "syze", "yssdl", "ynssde",
//			"sl", "ynsdse", "jmsdse", "yyjsdse",
//			"ybsdse"};
	//2012��
	String[] HCItem = { "1", "2", "3", "4", "5", "6", "10", "11", "12", "13" , "14", "15"};	
	String[] HCName = { "syze", "bzssr", "mssr", "yssre", "yssdl", "ynssde",
			            "sl", "ynsdse", "yyjsdse", "ybsdse", "zczb", "zcze"};	
	
	public HdzssdsnbProcessor() {
	}

	/**
	 * ��ѯ�Ǽ���Ϣ���걨����
	 * 
	 * @param pData
	 *            Map
	 * @return Map
	 * @throws BaseException
	 */
	private Object doQuery(Map pData) throws BaseException {
		// ���ݿ����Ӷ���
		Connection conn = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
		try {
			// ������ݿ�����
			conn = DBResource.getConnection();
			HdzssdsBO hdsdsbo = new HdzssdsBO();
			// ���������
			String jsjdm = null;
			// ��ǰ����
			Timestamp curDate = null;
			// ˰��Ǽǻ�������ֵ����
			SWDJJBSJ djjbsj = (SWDJJBSJ) pData
					.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
			hdsdsbo.setJsjdm(djjbsj.getJsjdm());
			hdsdsbo.setNsrmc(djjbsj.getNsrmc());
			hdsdsbo.setNsrsbh(djjbsj.getSwdjzh());
			// hdsdsbo.setZczb(djjbsj.getZczb()+"");
			// ȡ�ü��������
			jsjdm = (String) pData.get(QysdsksMapConstant.STRING_KEY_JSJDM);
			// ȡ�����ڲ���
			curDate = (Timestamp) pData.get(QysdsksMapConstant.STRING_KEY_DATE);
			hdsdsbo.setSbrq(curDate);
			hdsdsbo.setSbrqshow(sdf.format(curDate));
			// ȡ�����ڵļ���
			String jd = Skssrq.preQuarter(curDate);
			hdsdsbo.setJd(jd);
			// ��������
			String bblx = (String) pData.get(QysdsksMapConstant.STRING_KEY_BBLX);
			// ��������
			String jdlx = (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX);

			// ȡ��˰����������Map
			Map skssrq = new HashMap();
			if (bblx.equals(Constant.WENNB)) {
				skssrq = Skssrq.yearSkssrq(curDate);
			} else {
				skssrq = Skssrq.otherSkssrq(curDate);
			}
			// ȡ��˰��������ʼ�ͽ�������
			Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
			Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);
			hdsdsbo.setSkssksrq(skssksrq);
			hdsdsbo.setSkssjsrq(skssjsrq);

			// ȡ�����
			String nd = (String) skssrq.get(Skssrq.SKSSRQ_ND);
			hdsdsbo.setNd(nd);

			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			QysdsReportsDeclare qrd = new QysdsReportsDeclare();
			qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
			qrd.setVersion(Constant.CREPORTS_VERSION_HDQYSDSNB_2012);
			qrd.setNsrjsjdm(jsjdm);

			qrd.setSknd(nd);
			// ������걨������̶������ں�Ϊ1������Ǽ��������������������ں�Ϊ1��2��3��4
			if (bblx.equals(Constant.WENNB)) {
				qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
				qrd.setQh("1");
			} else {
				qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
				qrd.setQh(jdlx);
			}

			QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();
			qrtd.setTableId(bblx);
			// qrtd.setTbblx(QysdsReportsConstants.CREPORTS_TABLE_REPORTTYPE_NORMAL_ONE_DIMENSIONALITY);

			/**
			 * ������ͣ��ͱ���������һ��
			 * 
			 */
			if (bblx.equals(Constant.WENNB)) {
				qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
			} else {
				qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			}

			Map tmp = new HashMap();
			tmp.put(bblx, qrtd);
			qrd.setTableContentList(tmp);

			qrd = (QysdsReportsDeclare) iApp.querySingleTable(qrd);

			qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList().get(
					bblx);
			if (qrtd == null) {
				System.out.println("======no value======");
			} else {
				HashMap map = new HashMap();
				for (int i = 0; i < HCItem.length; i++) {
					if (qrtd.getCellContentList().get(HCItem[i]) != null) {
						QysdsReportsItemDeclare qrtid = (QysdsReportsItemDeclare) qrtd
								.getCellContentList().get(HCItem[i]);
	System.out.println("ItemID:" + qrtid.getItemID() + " ItemValue:" + qrtid.getItemValue());
						if (qrtid.getItemValue() == null) {
							map.put(HCName[i], "0.00");
						} else {
							map.put(HCName[i], qrtid.getItemValue());
						}
					} else {
						map.put(HCName[i], "0.00");
					}
				}
				hdsdsbo.setSbsj(map);
			}
			return hdsdsbo;
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "��ѯ��ҵ����˰�걨����ʧ��");
		} finally {
			// �ر����ݿ�����
			DBResource.destroyConnection(conn);
		}
	}

	/**
	 * ������ҵ����˰��������
	 * 
	 * @param data
	 *            Map
	 * @throws BaseException
	 */
	private Map doSave(VOPackage vop) throws BaseException {
		DzyjHelper dh = new DzyjHelper();
		Map retData = new HashMap();
		Map data = (Map) vop.getData();
		UserData ud = vop.getUserData();
		Connection conn = null;
		HdzssdsnbVO qynb = (HdzssdsnbVO) data
				.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) data
				.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);

		HdzssdsBO qysdsndbo = (HdzssdsBO) data
				.get(QysdsksMapConstant.VO_KEY_KSSBSJ);
		// ��������
		String bblx = (String) data.get(QysdsksMapConstant.STRING_KEY_BBLX);
		// ��������
		String jdlx = (String) data.get(QysdsksMapConstant.STRING_KEY_JDLX);
		// ˰��Ǽǻ�������ֵ����
		SWDJJBSJ djjbsj = (SWDJJBSJ) data
				.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
		try {
			// ������ݿ�����
			conn = DBResource.getConnection();
			QysdsReportsDeclare report = this.ConvertBoToReportsDeclare(
					qysdsndbo, bblx, jdlx, djjbsj);
			// ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
		} catch (Exception ex) {
			DBResource.destroyConnection(conn);
			throw ExceptionUtil.getBaseException(ex);
		}
		// System.out.println("�����ʸ�"+qysdsjdbo.getJmzg());
		// insertJm(qysdsjdbo,djjbsj,conn);

		if (ud.getCaflag()) {

			System.out.println("===========ǩ����ʼ==========");
			try {
				String ywid = qynb.getNsrxx().getJsjdm() + "+" + DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
				System.out.println("======ywid:" + ywid);
				dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
				System.out.println("===========ǩ������==========");
			} catch (Exception ex) {
				System.out.println("===========ǩ��ʧ��==========");
				throw ExceptionUtil.getBaseException(ex);

			}

			retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		}
		return retData;
	}

	/**
	 * ����ҵ���������ֵ����ҵ�����
	 * 
	 * @param vo
	 *            VOPackage
	 * @return java.lang.Object
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
	public Object process(VOPackage vo) throws BaseException {
		// ����ҵ���������ֵ����ҵ�����
		try {
			switch (vo.getAction()) {
			// ��ѯ
			case QysdsksActionConstant.INT_ACTION_QUERY: {
				return doQuery((Map) vo.getData());
			}

				// ����
			case QysdsksActionConstant.INT_ACTION_SAVE: {
				return doSave(vo);
			}
				// ɾ��
			case QysdsksActionConstant.INT_ACTION_DELETE: {
				doDelete(vo);
				return null;
			}

			default:
				throw new SystemException("no such method");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 * ɾ�������걨����
	 * 
	 * @param data
	 *            Map
	 * @throws BaseException
	 */
	private void doDelete(VOPackage vop) throws BaseException {
		DzyjHelper dh = new DzyjHelper();
		Map retData = new HashMap();
		Map data = (Map) vop.getData();
		UserData ud = vop.getUserData();
		Connection conn = null;
		HdzssdsnbVO qynb = (HdzssdsnbVO) data
				.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) data
				.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);

		// ��������
		String bblx = (String) data.get(QysdsksMapConstant.STRING_KEY_BBLX);
		// ��������
		String jdlx = (String) data.get(QysdsksMapConstant.STRING_KEY_JDLX);

		try {
			// ������ݿ�����
			conn = DBResource.getConnection();
			QysdsReportsDeclare qrd = new QysdsReportsDeclare();
			qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
			qrd.setVersion(Constant.CREPORTS_VERSION_HDQYSDSNB_2012);
			qrd.setNsrjsjdm(qynb.getNsrxx().getJsjdm());

			qrd.setSknd(qynb.getSbxx().getNd());
			// ������걨������̶������ں�Ϊ1������Ǽ��������������������ں�Ϊ1��2��3��4
			if (bblx.equals(Constant.WENNB)) {
				qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
				qrd.setQh("1");
			} else {
				qrd
						.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
				qrd.setQh(jdlx);
			}

			QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();
			QysdsReportsTableDeclare qrtdFb = new QysdsReportsTableDeclare();
			qrtd.setTableId(bblx);
			// qrtd.setTbblx(QysdsReportsConstants.CREPORTS_TABLE_REPORTTYPE_NORMAL_ONE_DIMENSIONALITY);

			/**
			 * ������ͣ��ͱ���������һ��
			 * 
			 */
			if (bblx.equals(Constant.WENNB)) {
				qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
				qrtdFb.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
			} else {
				qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			}

			Map tmp = new HashMap();
			Map tmpFb = new HashMap();
			tmp.put(bblx, qrtd);
			qrd.setTableContentList(tmp);

			// ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(qrd);
			DBResource.destroyConnection(conn);
			
			// ����һ�����Ϊ0�ļ�¼
			// qysdsjbbo.setJmsdse("0");
			// insertJm(qysdsjbbo,djjbsj,conn);
		} catch (Exception ex) {
			DBResource.destroyConnection(conn);
			throw ExceptionUtil.getBaseException(ex);
		}

		if (ud.getCaflag()) {

			System.out.println("===========ǩ����ʼ==========");
			try {
				String ywid = qynb.getNsrxx().getJsjdm()
						+ "+"
						+ DjStringUtil
								.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
				System.out.println("======ywid:" + ywid);
				dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
				System.out.println("===========ǩ������==========");
			} catch (Exception ex) {
				System.out.println("===========ǩ��ʧ��==========");
				throw ExceptionUtil.getBaseException(ex);

			}

			retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		}
	}

	/**
	 * ���ݷ���ӿ�
	 * 
	 * @param qysdsbo
	 *            ��ҵ����˰BO����
	 * @param bblx
	 *            ��������
	 * @param jdlx
	 *            ��������
	 * @param djjbsj
	 *            �Ǽ����ݶ���
	 * @return
	 */
	private QysdsReportsDeclare ConvertBoToReportsDeclare(HdzssdsBO qysdsbo,
			String bblx, String jdlx, SWDJJBSJ djjbsj) {

		QysdsReportsDeclare report = new QysdsReportsDeclare();

		// ������Ϣ
		Jbxx jbxx = new Jbxx();
		// ������Ϣ(JBXX)-���������
		jbxx.setJsjdm(qysdsbo.getJsjdm());
		// ������Ϣ(JBXX)-��˰������
		jbxx.setNsrmc(qysdsbo.getNsrmc());
		// ������Ϣ(JBXX)-��ϵ�绰
		jbxx.setLxdh(djjbsj.getJydzlxdm());
		// ������Ϣ(JBXX)-������ҵ
		jbxx.setSshy(djjbsj.getGjbzhydm());
		// ������Ϣ(JBXX)-���շ�ʽ
		jbxx.setZsfs(qysdsbo.getQyzslx());

		/*
		 * //������Ϣ(JBXX)-������������ jbxx.setSsjjlx(form.getSsjjlx());
		 * //������Ϣ(JBXX)-�������� jbxx.setJmlx(form.getJmlx()); //������Ϣ(JBXX)-�ƻ��ƶ�
		 * jbxx.setCkzd(form.getCkzd()); // ������Ϣ(JBXX)-���ʹ�����ʽ //
		 * jbxx.setGzglxs(form.getGzglxs());
		 */

		// �򱨱��������˰�˻�����Ϣ
		report.setJbxx(jbxx);

		/**
		 * Ӧ�ñ��
		 */
		report.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
		/**
		 * �汾��
		 */
		report.setVersion(Constant.CREPORTS_VERSION_HDQYSDSNB_2012);
		/**
		 * ��˰�˼��������
		 */
		report.setNsrjsjdm(qysdsbo.getJsjdm());
		/**
		 * ��˰������
		 */
		report.setNsrmc(qysdsbo.getNsrmc());
		/**
		 * ����������
		 */
		// ������걨������̶������ں�Ϊ1������Ǽ��������������������ں�Ϊ1��2��3��4
		if (bblx.equals(Constant.WENNB)) {
			report.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
			/**
			 * �걨�ں�Ĭ��Ϊ1
			 */
			report.setQh("1");
		} else {
			report.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			/**
			 * �ں�
			 */
			report.setQh(jdlx);
		}

		// ȡ��˰����������Map
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		Map skssrq = new HashMap();
		if (bblx.equals(Constant.WENNB)) {
			skssrq = Skssrq.yearSkssrq(curDate);
		} else {
			skssrq = Skssrq.otherSkssrq(curDate);
		}

		String sbrq = "";

		try {
			/**
			 * ˰��������ʼ����
			 */
			String skssksrq = DateTimeUtil.timestampToString((Timestamp) skssrq
					.get(Skssrq.SKSSKSRQ));
			report.setSkssksrq(new Date(TinyTools.stringToDate(skssksrq,
					"yyyy-MM-dd").getTime()));

			/**
			 * ˰��������������
			 */
			String skssjsrq = DateTimeUtil.timestampToString((Timestamp) skssrq
					.get(Skssrq.SKSSJSRQ));
			report.setSkssjsrq(new Date(TinyTools.stringToDate(skssjsrq,
					"yyyy-MM-dd").getTime()));
			/**
			 * �걨����
			 */
			sbrq = DateTimeUtil.timestampToString(curDate);
			report.setSbrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd")
					.getTime()));
		} catch (Exception e) {
			System.out.println("ת���걨��ʱ����");
		}
		/**
		 * ˰�����
		 */
		report.setSknd((String) skssrq.get(Skssrq.SKSSRQ_ND));
		/**
		 * ˰�������֯��������
		 */
		report.setSwjgzzjgdm(qysdsbo.getSwjgzzjgdm());
		/**
		 * ���ش���
		 */
		report.setQxdm(djjbsj.getQxdm());
		/**
		 * ¼����
		 */
		report.setLrr(qysdsbo.getJsjdm());
		/**
		 * ¼������
		 */
		report.setLrrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd")
				.getTime()));
		/**
		 * ������
		 */
		report.setCjr(qysdsbo.getJsjdm());
		/**
		 * ��������
		 */
		report.setCjrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd")
				.getTime()));

		/**
		 * ���ش���
		 */
		report.setQxdm(qysdsbo.getSwjgzzjgdm().substring(0, 2));

		// ��ҵ����˰�����ڵ�����������
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(bblx);
		table.setTableName(Constant.WENNB_TABLENAME);
		// table.setTbblx("0");

		/**
		 * ������ͣ��ͱ���������һ��
		 * 
		 */
		if (bblx.equals(Constant.WENNB)) {
			table.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
		} else {
			table.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		}

		if (qysdsbo.getQyzslx().equals("2") || qysdsbo.getQyzslx().equals("5")) {
			Map sbsjMap = ConvertDlSbsjBoToMap(qysdsbo);
			for (int i = 0; i < HCItem.length; i++) {
				QysdsReportsItemDeclare item_1_1 = new

				QysdsReportsItemDeclare();
				item_1_1.setItemID(HCItem[i]);
				item_1_1.setItemValue(sbsjMap.get(HCItem[i]).toString());
				item_1_1.setItemType("0");
				table.getCellContentList().put(item_1_1.getItemID(), item_1_1);
			}
		} else {
			Map sbsjMap = ConvertDeSbsjBoToMap(qysdsbo);
			//Map sbsjMap = ConvertDlSbsjBoToMap(qysdsbo);
			for (int i = 7; i < HCItem.length; i++) {
				QysdsReportsItemDeclare item_1_1 = new

				QysdsReportsItemDeclare();
				item_1_1.setItemID(HCItem[i]);
				item_1_1.setItemValue(sbsjMap.get(HCItem[i]).toString());
				item_1_1.setItemType("0");
				table.getCellContentList().put(item_1_1.getItemID(), item_1_1);
			}

		}
		
//		Map sbsjMap = ConvertDlSbsjBoToMap(qysdsbo);
//
//		for (int i = 0; i < HCItem.length; i++) {
//			QysdsReportsItemDeclare item_1_1 = new QysdsReportsItemDeclare();
//			item_1_1.setItemID(HCItem[i]);
//			item_1_1.setItemValue(sbsjMap.get(HCItem[i]).toString());
//			item_1_1.setItemType("0");
//			table.getCellContentList().put(item_1_1.getItemID(), item_1_1);
//		}
		
		report.getTableContentList().put(table.getTableId(), table);

		return report;

	}

	/**
	 * ���ʸ�ʽ������˰BO����
	 * 
	 * @param qysdsnbbo
	 * @return
	 */
	private Map ConvertDlSbsjBoToMap(HdzssdsBO qysdsnbbo) {
		Map map = new HashMap();
		map.put("1", qysdsnbbo.getSyze());
		map.put("2", qysdsnbbo.getBzssr());
		map.put("3", qysdsnbbo.getMssr());
		map.put("4", qysdsnbbo.getYssre());
		map.put("5", qysdsnbbo.getYssdl());
		map.put("6", qysdsnbbo.getYnssde());
		map.put("10",qysdsnbbo.getSl());
		map.put("11", qysdsnbbo.getYnsdse());
		map.put("12", qysdsnbbo.getYyjsdse());
		map.put("13",qysdsnbbo.getYbsdse());
		
		map.put("14", qysdsnbbo.getZczb());
		map.put("15",qysdsnbbo.getZcze());
		return map;
	}
	
	/**
	 * �����ʽ������˰BO����
	 * 
	 * @param qysdsnbbo
	 * @return
	 */
	private Map ConvertDeSbsjBoToMap(HdzssdsBO qysdsnbbo) {
		Map map = new HashMap();
		map.put("11", qysdsnbbo.getYnsdse());
		map.put("12", qysdsnbbo.getYyjsdse());
		map.put("13",qysdsnbbo.getYbsdse());
		
		map.put("14", qysdsnbbo.getZczb());
		map.put("15",qysdsnbbo.getZcze());
		return map;
	}

}
