package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.processor;

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
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.Constant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.bo.HdzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.HdzssdsjbVO;
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
 * @version 1.0 2008�˶�������ҵ����˰����processor��
 */
public class HdzssdsjbProcessor implements Processor {
//	String[] HCItem = { "1", "2", "3", "11", "12", "13", "14","15" };	
//	String[] HCName = { "syze", "yssdl", "ynssde",
//			"sl", "ynsdse", "jmsdse", "yyjsdse",
//			"ybsdse"};
	//2012��
	String[] HCItem = { "1", "2", "3", "4", "5", "6", 
			"10", "11", "12",
			"13" };	
	String[] HCName = { "syze", "bzssr", "mssr", "yssre", "yssdl", "ynssde",
			"sl", "ynsdse", "yyjsdse",
			"ybsdse"};	
	
	public HdzssdsjbProcessor() {
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
		System.out.println("======doQuery====="+1);
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
			String bblx = (String) pData
					.get(QysdsksMapConstant.STRING_KEY_BBLX);
			// ��������
			String jdlx = (String) pData
					.get(QysdsksMapConstant.STRING_KEY_JDLX);

			// ȡ��˰����������Map
			Map skssrq = new HashMap();
			
			skssrq = Skssrq.otherSkssrq(curDate);
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
			//qrd.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS);
			qrd.setVersion(Constant.QYSDSJB_VERSION_2012);
			qrd.setNsrjsjdm(jsjdm);

			qrd.setSknd(nd);
			// ����Ǽ��������������������ں�Ϊ1��2��3��4
			qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			qrd.setQh(jdlx);

			

			QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();
			qrtd.setTableId(bblx);
			//qrtd.setTbblx(QysdsReportsConstants.CREPORTS_TABLE_REPORTTYPE_NORMAL_ONE_DIMENSIONALITY);
			
			/**
			 * ������ͣ��ͱ���������һ��
			 * 
			 */

			qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);			
			
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
						// System.out.println("ItemID:" + qrtid.getItemID()
						// + " ItemValue:" + qrtid.getItemValue());
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
			throw ExceptionUtil.getBaseException(ex, "��ѯ��ҵ����˰��������ʧ��");
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
		HdzssdsjbVO qyjb = (HdzssdsjbVO) data
				.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) data
				.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);

		HdzssdsBO qysdsjdbo = (HdzssdsBO) data
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
					qysdsjdbo, bblx, jdlx, djjbsj);
			// ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
		} catch (Exception ex) {
		    DBResource.destroyConnection(conn);
			throw ExceptionUtil.getBaseException(ex);
		}
//		System.out.println("�����ʸ�"+qysdsjdbo.getJmzg());
//		insertJm(qysdsjdbo,djjbsj,conn);
		

		if (ud.getCaflag()) {

			System.out.println("===========ǩ����ʼ==========");
			try {
				String ywid = qyjb.getNsrxx().getJsjdm()
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
		
		PreparedStatement pstmt = null;

		// ����ɾ������
		StringBuffer sqlBuffer = new StringBuffer();
		String sql = "";
		
		HdzssdsjbVO qyjb = (HdzssdsjbVO) data
				.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) data
				.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		
		HdzssdsBO qysdsjbbo = (HdzssdsBO) data
		.get(QysdsksMapConstant.VO_KEY_KSSBSJ);

		// ��������
		String bblx = (String) data.get(QysdsksMapConstant.STRING_KEY_BBLX);
		// ��������
		String jdlx = (String) data.get(QysdsksMapConstant.STRING_KEY_JDLX);
		
//		 ˰��Ǽǻ�������ֵ����
		SWDJJBSJ djjbsj = (SWDJJBSJ) data
				.get(QysdsksMapConstant.STRING_KEY_JBSJ);

		try {
			// ������ݿ�����
			conn = DBResource.getConnection();
			QysdsReportsDeclare qrd = new QysdsReportsDeclare();
			qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
			//qrd.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS);
			qrd.setVersion(Constant.QYSDSJB_VERSION_2012);
			qrd.setNsrjsjdm(qyjb.getNsrxx().getJsjdm());

			qrd.setSknd(qyjb.getSbxx().getNd());
			// ����Ǽ��������������������ں�Ϊ1��2��3��4

			qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			qrd.setQh(jdlx);
			
			QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();
			qrtd.setTableId(bblx);
			//qrtd.setTbblx(QysdsReportsConstants.CREPORTS_TABLE_REPORTTYPE_NORMAL_ONE_DIMENSIONALITY);
			
			/**
			 * ������ͣ��ͱ���������һ��
			 * 
			 */
			qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);			
			
			Map tmp = new HashMap();
			tmp.put(bblx, qrtd);
			qrd.setTableContentList(tmp);

			// ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(qrd);
			
			//����һ�����Ϊ0�ļ�¼
//			qysdsjbbo.setJmsdse("0");
//			insertJm(qysdsjbbo,djjbsj,conn);

			
		} catch (Exception ex) {
		    DBResource.destroyConnection(conn);
			throw ExceptionUtil.getBaseException(ex);
		}

		if (ud.getCaflag()) {

			System.out.println("===========ǩ����ʼ==========");
			try {
				String ywid = qyjb.getNsrxx().getJsjdm()
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
		//report.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS);
		report.setVersion(Constant.QYSDSJB_VERSION_2012);
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
		// ����Ǽ��������������������ں�Ϊ1��2��3��4

		report.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		/**
		 * �ں�
		 */
		report.setQh(jdlx);

		

		// ȡ��˰����������Map
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		Map skssrq = new HashMap();

		skssrq = Skssrq.otherSkssrq(curDate);

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
		//table.setTbblx("0");
		
		/**
		 * ������ͣ��ͱ���������һ��
		 * 
		 */

		table.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);			
		
		
		if(qysdsbo.getQyzslx().equals("2")||qysdsbo.getQyzslx().equals("5")){
			Map sbsjMap = ConvertDlSbsjBoToMap(qysdsbo);
			for (int i = 0; i < HCItem.length; i++) {
				QysdsReportsItemDeclare item_1_1 = new QysdsReportsItemDeclare();
				item_1_1.setItemID(HCItem[i]);
				item_1_1.setItemValue(sbsjMap.get(HCItem[i]).toString());
				item_1_1.setItemType("0");
				table.getCellContentList().put(item_1_1.getItemID(), item_1_1);
			}
		}
		else{
			Map sbsjMap = ConvertDeSbsjBoToMap(qysdsbo);
			for (int i = 7; i < HCItem.length; i++) {
				QysdsReportsItemDeclare item_1_1 = new QysdsReportsItemDeclare();
				item_1_1.setItemID(HCItem[i]);
				item_1_1.setItemValue(sbsjMap.get(HCItem[i]).toString());
				item_1_1.setItemType("0");
				table.getCellContentList().put(item_1_1.getItemID(), item_1_1);
			}
			
		}

		report.getTableContentList().put(table.getTableId(), table);

		/*
		 * // ceshi Iterator it =
		 * table.getCellContentList().keySet().iterator(); while (it.hasNext()) {
		 * QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table
		 * .getCellContentList().get((String) it.next());
		 * System.out.println(item.getItemID() + " " + item.getItemValue()); }
		 */

		return report;

	}

	/**
	 * ���ʸ�ʽ������˰BO����
	 * 
	 * @param qysdsnbbo
	 * @return
	 */
	private Map ConvertDlSbsjBoToMap(HdzssdsBO qysdsjbbo) {
		Map map = new HashMap();
		map.put("1", qysdsjbbo.getSyze());
		map.put("2", qysdsjbbo.getBzssr());
		map.put("3", qysdsjbbo.getMssr());
		map.put("4", qysdsjbbo.getYssre());
		map.put("5", qysdsjbbo.getYssdl());
		map.put("6", qysdsjbbo.getYnssde());
		map.put("10",qysdsjbbo.getSl());
		map.put("11", qysdsjbbo.getYnsdse());
		map.put("12", qysdsjbbo.getYyjsdse());
		map.put("13",qysdsjbbo.getYbsdse());
		return map;
	}
	
	/**
	 * �����ʽ������˰BO����
	 * 
	 * @param qysdsnbbo
	 * @return
	 */
	private Map ConvertDeSbsjBoToMap(HdzssdsBO qysdsjbbo) {
		Map map = new HashMap();
		map.put("11", qysdsjbbo.getYnsdse());
		map.put("12", qysdsjbbo.getYyjsdse());
		map.put("13",qysdsjbbo.getYbsdse());
		return map;
	}
	
	private void insertJm_delete(HdzssdsBO qysdsjdbo,SWDJJBSJ djjbsj,Connection conn)throws BaseException{
		ORManager orManager = null;
		String sql=null;
		CallableStatement st = null;
//		if ((qysdsjdbo.getJmsdse() == null)
//				|| ("0.00".equals(qysdsjdbo.getJmsdse()))
//				|| ("".equals(qysdsjdbo.getJmsdse()))) {
//			qysdsjdbo.setJmsdse("0");
//		}

			Timestamp t1, t2;

			t1 = new Timestamp(System.currentTimeMillis());

			try {

				// ��� ORManager
				orManager = DBResource.getORManager();

				Timestamp now = new Timestamp(System.currentTimeMillis());
				Jm jm = new Jm();
				jm.setJsjdm(djjbsj.getJsjdm());
				jm.setJmlx(CodeConstant.JMLX_SP); // ��������
				jm.setSzsmdm(SzsmdmConstant.QYSDS_SM);
				jm.setSbrq(TinyTools.second2Day(now));
				jm.setLrrq(now);
//				jm.setJsje(new BigDecimal(qysdsjdbo.getJmsdse()));
				jm.setKssl(null);
				jm.setJmse(jm.getJsje());
				jm.setSwjgzzjgdm(djjbsj.getSwjgzzjgdm());
				jm.setLrr(djjbsj.getJsjdm());
				jm.setFsdm(CodeConstant.FSDM_WSSB);
				jm.setDjzclxdm(djjbsj.getDjzclxdm());
				jm.setGjbzhydm(djjbsj.getGjbzhydm());
				jm.setYskmdm(null); // ��processor��ֵ
				jm.setYsjcdm(null); // ��processor��ֵ
				Timestamp skssksrq = qysdsjdbo.getSkssksrq();
				Timestamp skssjsrq = qysdsjdbo.getSkssjsrq();
				jm.setSkssksrq(skssksrq); // ˰��������ʼʱ��
				jm.setSkssjsrq(skssjsrq); // ˰����������ʱ��
				jm.setNd(qysdsjdbo.getNd());
				jm.setCjrq(now);
				jm.setQxdm(djjbsj.getSwjgzzjgdm().substring(0, 2));
				String jmxmdm = (new ServiceProxy()).getJmsbs(
						djjbsj.getJsjdm(), SzsmdmConstant.QYSDS, skssksrq,
						skssjsrq);

				// ������Ŀ����Ϊ�գ��ӱ���ȡ����ҵ�����������жϼ��ⷽʽ
				if (jmxmdm == null) {

					System.out
							.println("**************   ȡ�õļ�����Ŀ����Ϊ�գ������걨�ļ�������putJm�е���ҵ��������qysdsjbbo.getQyzslx()Ϊ:"
									+ qysdsjdbo.getQyzslx());

					//if (qysdsjdbo.getQyzslx() != null) {

						// ��ҵ��������Ϊ���¼�����ҵ ���������Ϊ���¼�����ҵ����
						//if ("1".equals(qysdsjdbo.getQyzslx())
								//|| "5".equals(qysdsjdbo.getQyzslx())) {

							//jmxmdm = CodeConstant.JMLX9010;

						//}
					//}
					jmxmdm=CodeConstant.JMLXOTHER;
					jm.setJmlx(CodeConstant.JMLX_FD); // ��������

				}

				if (jmxmdm != null && !("".equals(jmxmdm))) {

					jm.setJmxmdm(jmxmdm); // ������Ŀ����

					String ysjcdm = null;
					String yskmdm = null;
					ysjcdm = FriendHelper.getYsjc(jm.getJsjdm(),
							jm.getSzsmdm(), jm.getSkssjsrq()).getYsjcdm();
					yskmdm = JKAdapter.getInstance().getYskm(jm.getSzsmdm(),
							jm.getDjzclxdm(), jm.getGjbzhydm(), ysjcdm)
							.getYskmdm();

					jm.setYskmdm(yskmdm);
					jm.setYsjcdm(ysjcdm);

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
						// Timestamp skssjsrq = jm.getSkssjsrq();
						// Timestamp skssksrq = jm.getSkssksrq();
						String swjgzzjgdm = jm.getSwjgzzjgdm();
						String qxdm = jm.getQxdm();
						String djzclxdm = jm.getDjzclxdm();
						String gjbzhydm = jm.getGjbzhydm();
						String nd = jm.getNd();
						// String ysjcdm = jm.getYsjcdm();
						// String yskmdm = jm.getYskmdm();
						// String jmxmdm = jm.getJmxmdm();
						BigDecimal jmse = jm.getJmse();

						sql = "BEGIN sbdb.sb_pkg_sbjmtools.insertjm_jb(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); END;";

						st = conn.prepareCall(sql);

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

			} catch (Exception e) {
				throw ExceptionUtil.getBaseException(e);
			}
			finally {
			    // �ر����ݿ�����
			    DBResource.destroyConnection(conn);
		    }

			t2 = new Timestamp(System.currentTimeMillis());

			System.out.println("��������걨�����ݺ�ʱ��" + (t2.getTime() - t1.getTime()));
	}
}
