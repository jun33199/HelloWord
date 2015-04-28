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
 * @version 1.0 2012核定征收企业所得税年报processor类
 */
public class HdzssdsnbProcessor implements Processor {
//	String[] HCItem = { "1", "2", "3", "11", "12", "13", "14","15" };	
//	String[] HCName = { "syze", "yssdl", "ynssde",
//			"sl", "ynsdse", "jmsdse", "yyjsdse",
//			"ybsdse"};
	//2012版
	String[] HCItem = { "1", "2", "3", "4", "5", "6", "10", "11", "12", "13" , "14", "15"};	
	String[] HCName = { "syze", "bzssr", "mssr", "yssre", "yssdl", "ynssde",
			            "sl", "ynsdse", "yyjsdse", "ybsdse", "zczb", "zcze"};	
	
	public HdzssdsnbProcessor() {
	}

	/**
	 * 查询登记信息和申报数据
	 * 
	 * @param pData
	 *            Map
	 * @return Map
	 * @throws BaseException
	 */
	private Object doQuery(Map pData) throws BaseException {
		// 数据库连接对象
		Connection conn = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		try {
			// 获得数据库连接
			conn = DBResource.getConnection();
			HdzssdsBO hdsdsbo = new HdzssdsBO();
			// 计算机代码
			String jsjdm = null;
			// 当前日期
			Timestamp curDate = null;
			// 税务登记基本数据值对象
			SWDJJBSJ djjbsj = (SWDJJBSJ) pData
					.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
			hdsdsbo.setJsjdm(djjbsj.getJsjdm());
			hdsdsbo.setNsrmc(djjbsj.getNsrmc());
			hdsdsbo.setNsrsbh(djjbsj.getSwdjzh());
			// hdsdsbo.setZczb(djjbsj.getZczb()+"");
			// 取得计算机代码
			jsjdm = (String) pData.get(QysdsksMapConstant.STRING_KEY_JSJDM);
			// 取得日期参数
			curDate = (Timestamp) pData.get(QysdsksMapConstant.STRING_KEY_DATE);
			hdsdsbo.setSbrq(curDate);
			hdsdsbo.setSbrqshow(sdf.format(curDate));
			// 取得所在的季度
			String jd = Skssrq.preQuarter(curDate);
			hdsdsbo.setJd(jd);
			// 报表类型
			String bblx = (String) pData.get(QysdsksMapConstant.STRING_KEY_BBLX);
			// 季度类型
			String jdlx = (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX);

			// 取得税款所属日期Map
			Map skssrq = new HashMap();
			if (bblx.equals(Constant.WENNB)) {
				skssrq = Skssrq.yearSkssrq(curDate);
			} else {
				skssrq = Skssrq.otherSkssrq(curDate);
			}
			// 取得税款所属开始和结束日期
			Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
			Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);
			hdsdsbo.setSkssksrq(skssksrq);
			hdsdsbo.setSkssjsrq(skssjsrq);

			// 取得年度
			String nd = (String) skssrq.get(Skssrq.SKSSRQ_ND);
			hdsdsbo.setNd(nd);

			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			QysdsReportsDeclare qrd = new QysdsReportsDeclare();
			qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
			qrd.setVersion(Constant.CREPORTS_VERSION_HDQYSDSNB_2012);
			qrd.setNsrjsjdm(jsjdm);

			qrd.setSknd(nd);
			// 如果是年报数据则固定设置期号为1；如果是季报数据则根据情况设置期号为1、2、3、4
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
			 * 填报表类型，和报表期类型一样
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
			throw ExceptionUtil.getBaseException(ex, "查询企业所得税年报数据失败");
		} finally {
			// 关闭数据库连接
			DBResource.destroyConnection(conn);
		}
	}

	/**
	 * 保存企业所得税亏损数据
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
		// 报表类型
		String bblx = (String) data.get(QysdsksMapConstant.STRING_KEY_BBLX);
		// 季度类型
		String jdlx = (String) data.get(QysdsksMapConstant.STRING_KEY_JDLX);
		// 税务登记基本数据值对象
		SWDJJBSJ djjbsj = (SWDJJBSJ) data
				.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
		try {
			// 获得数据库连接
			conn = DBResource.getConnection();
			QysdsReportsDeclare report = this.ConvertBoToReportsDeclare(
					qysdsndbo, bblx, jdlx, djjbsj);
			// 获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
		} catch (Exception ex) {
			DBResource.destroyConnection(conn);
			throw ExceptionUtil.getBaseException(ex);
		}
		// System.out.println("减免资格："+qysdsjdbo.getJmzg());
		// insertJm(qysdsjdbo,djjbsj,conn);

		if (ud.getCaflag()) {

			System.out.println("===========签名开始==========");
			try {
				String ywid = qynb.getNsrxx().getJsjdm() + "+" + DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
				System.out.println("======ywid:" + ywid);
				dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
				System.out.println("===========签名结束==========");
			} catch (Exception ex) {
				System.out.println("===========签名失败==========");
				throw ExceptionUtil.getBaseException(ex);

			}

			retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		}
		return retData;
	}

	/**
	 * 根据业务操作类型值来做业务操作
	 * 
	 * @param vo
	 *            VOPackage
	 * @return java.lang.Object
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
	public Object process(VOPackage vo) throws BaseException {
		// 根据业务操作类型值来做业务操作
		try {
			switch (vo.getAction()) {
			// 查询
			case QysdsksActionConstant.INT_ACTION_QUERY: {
				return doQuery((Map) vo.getData());
			}

				// 保存
			case QysdsksActionConstant.INT_ACTION_SAVE: {
				return doSave(vo);
			}
				// 删除
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
	 * 删除亏损申报数据
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

		// 报表类型
		String bblx = (String) data.get(QysdsksMapConstant.STRING_KEY_BBLX);
		// 季度类型
		String jdlx = (String) data.get(QysdsksMapConstant.STRING_KEY_JDLX);

		try {
			// 获得数据库连接
			conn = DBResource.getConnection();
			QysdsReportsDeclare qrd = new QysdsReportsDeclare();
			qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
			qrd.setVersion(Constant.CREPORTS_VERSION_HDQYSDSNB_2012);
			qrd.setNsrjsjdm(qynb.getNsrxx().getJsjdm());

			qrd.setSknd(qynb.getSbxx().getNd());
			// 如果是年报数据则固定设置期号为1；如果是季报数据则根据情况设置期号为1、2、3、4
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
			 * 填报表类型，和报表期类型一样
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

			// 获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(qrd);
			DBResource.destroyConnection(conn);
			
			// 插入一条金额为0的记录
			// qysdsjbbo.setJmsdse("0");
			// insertJm(qysdsjbbo,djjbsj,conn);
		} catch (Exception ex) {
			DBResource.destroyConnection(conn);
			throw ExceptionUtil.getBaseException(ex);
		}

		if (ud.getCaflag()) {

			System.out.println("===========签名开始==========");
			try {
				String ywid = qynb.getNsrxx().getJsjdm()
						+ "+"
						+ DjStringUtil
								.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
				System.out.println("======ywid:" + ywid);
				dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
				System.out.println("===========签名结束==========");
			} catch (Exception ex) {
				System.out.println("===========签名失败==========");
				throw ExceptionUtil.getBaseException(ex);

			}

			retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		}
	}

	/**
	 * 数据翻译接口
	 * 
	 * @param qysdsbo
	 *            企业所得税BO对象
	 * @param bblx
	 *            报表类型
	 * @param jdlx
	 *            季度类型
	 * @param djjbsj
	 *            登记数据对象
	 * @return
	 */
	private QysdsReportsDeclare ConvertBoToReportsDeclare(HdzssdsBO qysdsbo,
			String bblx, String jdlx, SWDJJBSJ djjbsj) {

		QysdsReportsDeclare report = new QysdsReportsDeclare();

		// 基本信息
		Jbxx jbxx = new Jbxx();
		// 基本信息(JBXX)-计算机代码
		jbxx.setJsjdm(qysdsbo.getJsjdm());
		// 基本信息(JBXX)-纳税人名称
		jbxx.setNsrmc(qysdsbo.getNsrmc());
		// 基本信息(JBXX)-联系电话
		jbxx.setLxdh(djjbsj.getJydzlxdm());
		// 基本信息(JBXX)-所属行业
		jbxx.setSshy(djjbsj.getGjbzhydm());
		// 基本信息(JBXX)-征收方式
		jbxx.setZsfs(qysdsbo.getQyzslx());

		/*
		 * //基本信息(JBXX)-所属经济类型 jbxx.setSsjjlx(form.getSsjjlx());
		 * //基本信息(JBXX)-减免类型 jbxx.setJmlx(form.getJmlx()); //基本信息(JBXX)-财会制度
		 * jbxx.setCkzd(form.getCkzd()); // 基本信息(JBXX)-工资管理形式 //
		 * jbxx.setGzglxs(form.getGzglxs());
		 */

		// 向报表中添加纳税人基本信息
		report.setJbxx(jbxx);

		/**
		 * 应用编号
		 */
		report.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
		/**
		 * 版本号
		 */
		report.setVersion(Constant.CREPORTS_VERSION_HDQYSDSNB_2012);
		/**
		 * 纳税人计算机代码
		 */
		report.setNsrjsjdm(qysdsbo.getJsjdm());
		/**
		 * 纳税人名称
		 */
		report.setNsrmc(qysdsbo.getNsrmc());
		/**
		 * 报表期类型
		 */
		// 如果是年报数据则固定设置期号为1；如果是季报数据则根据情况设置期号为1、2、3、4
		if (bblx.equals(Constant.WENNB)) {
			report.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
			/**
			 * 年报期号默认为1
			 */
			report.setQh("1");
		} else {
			report.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			/**
			 * 期号
			 */
			report.setQh(jdlx);
		}

		// 取得税款所属日期Map
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
			 * 税款所属开始日期
			 */
			String skssksrq = DateTimeUtil.timestampToString((Timestamp) skssrq
					.get(Skssrq.SKSSKSRQ));
			report.setSkssksrq(new Date(TinyTools.stringToDate(skssksrq,
					"yyyy-MM-dd").getTime()));

			/**
			 * 税款所属结束日期
			 */
			String skssjsrq = DateTimeUtil.timestampToString((Timestamp) skssrq
					.get(Skssrq.SKSSJSRQ));
			report.setSkssjsrq(new Date(TinyTools.stringToDate(skssjsrq,
					"yyyy-MM-dd").getTime()));
			/**
			 * 申报日期
			 */
			sbrq = DateTimeUtil.timestampToString(curDate);
			report.setSbrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd")
					.getTime()));
		} catch (Exception e) {
			System.out.println("转换申报期时出错！");
		}
		/**
		 * 税款年度
		 */
		report.setSknd((String) skssrq.get(Skssrq.SKSSRQ_ND));
		/**
		 * 税务机关组织机构代码
		 */
		report.setSwjgzzjgdm(qysdsbo.getSwjgzzjgdm());
		/**
		 * 区县代码
		 */
		report.setQxdm(djjbsj.getQxdm());
		/**
		 * 录入人
		 */
		report.setLrr(qysdsbo.getJsjdm());
		/**
		 * 录入日期
		 */
		report.setLrrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd")
				.getTime()));
		/**
		 * 创建人
		 */
		report.setCjr(qysdsbo.getJsjdm());
		/**
		 * 创建日期
		 */
		report.setCjrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd")
				.getTime()));

		/**
		 * 区县代码
		 */
		report.setQxdm(qysdsbo.getSwjgzzjgdm().substring(0, 2));

		// 企业所得税报表内单表申明对象
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(bblx);
		table.setTableName(Constant.WENNB_TABLENAME);
		// table.setTbblx("0");

		/**
		 * 填报表类型，和报表期类型一样
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
	 * 定率格式化所得税BO对象
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
	 * 定额格式化所得税BO对象
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
