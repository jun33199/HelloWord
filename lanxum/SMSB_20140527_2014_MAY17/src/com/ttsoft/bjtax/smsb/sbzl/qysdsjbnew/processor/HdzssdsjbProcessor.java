/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsjbnew.processor;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsjbnew.web.HdzssdsjbForm;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:核定征收企业所得税季报
 * </p>
 * 
 * @author li wenhua
 * @version 1.1
 */

public class HdzssdsjbProcessor implements Processor {

	// 企业所得税税率
	private static final String QYSDS_SL = "0.33";

	/**
	 * 实现Processor接口
	 * 
	 * @param vo
	 *            业务参数
	 * @return Object VOPackage型数据
	 * @throws BaseException
	 *             业务异常 1 当传过来的操作类型不对时抛出 2 当调用的业务方法抛出业务异常时向上传递抛出
	 *             其他异常抛出由EJB的process方法处理。
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
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		}

		return result;
	}

	/**
	 * doShow初始化对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doShow(VOPackage vo) throws BaseException {
		// 得到Action传递过来HdzssdsjbForm对象
		HdzssdsjbForm form = (HdzssdsjbForm) vo.getData();
		// 得到当前时间的所属月
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		Map getsbjd = this.quarterSkssrq(curTime);
		Timestamp skssksrq = (Timestamp) getsbjd.get(Skssrq.SKSSKSRQ);
		Timestamp skssjsrq = (Timestamp) getsbjd.get(Skssrq.SKSSJSRQ);
		// 税款所属开始日期
		form.setSkssksrq(SfTimeUtil.getDateFromDateTime(skssksrq));
		// 税款所属结束日期
		form.setSkssjsrq(SfTimeUtil.getDateFromDateTime(skssjsrq));
		// 税款申报日期
		form.setSbrq(SfDateUtil.getDate());
		return form;
	}

	/**
	 * doQuery 用于返回页面索要查询的详尽信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 * 
	 */

	private Object doQuery(VOPackage vo) throws BaseException {

		// 得到Action传递过来HdzssdsjbForm对象
		HdzssdsjbForm hdzssdsjbForm = (HdzssdsjbForm) vo.getData();
		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 获取税款所属日期
			// Map getsbnd = this.quarterSkssrq(SfDateUtil.getDate(hdzssdsjbForm
			// .getSbrq()));
			// 获取税款所属季度
			String jd = QysdsNewUtil.preQuarter(SfDateUtil
					.getDate(hdzssdsjbForm.getSkssjsrq()));

			System.out.println(hdzssdsjbForm.getJsjdm()
					+ "hdzssdsjbForm.getSbrq()：" + hdzssdsjbForm.getSbrq());
			System.out.println(hdzssdsjbForm.getJsjdm()
					+ "hdzssdsjbForm.getSkssjsrq()："
					+ hdzssdsjbForm.getSkssjsrq());
			System.out.println(hdzssdsjbForm.getJsjdm() + "的jd：" + jd);

			// 获取税款所属年度
			// String sknd = this.getNd(jd, getsbnd, hdzssdsjbForm.getSbrq());
			// String nd = (String)getsbnd.get(Skssrq.SKSSRQ_ND);
			// 从页面中取得税款所属期和年度
			String nd = hdzssdsjbForm.getSkssksrq().substring(0, 4);
			// 设置季度
			hdzssdsjbForm.setQh(jd);
			// 设置年度
			hdzssdsjbForm.setSknd(nd);

			// 设置form中其它所需属性
			hdzssdsjbForm = (HdzssdsjbForm) QysdsNewUtil
					.queryDjxxByInterfaceDJ(conn, hdzssdsjbForm, vo
							.getUserData());

			// 税费核定信息
			this.getHdxx(hdzssdsjbForm);

			/* 征收方式 */
			String zsfs = hdzssdsjbForm.getZsfs();

			System.out.println(hdzssdsjbForm.getJsjdm() + "的征收方式代码：" + zsfs);

			if (zsfs == null || (zsfs != null && zsfs.equals(""))) {
				throw new ApplicationException("没有查询到该企业的征收方式认定信息，请认定后再进行申报录入！");
			}
			if (CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
				throw new ApplicationException(
						"该企业已认定为查帐征收户，不能在此录入，请录入查帐征收季度申报表！");
			}

			// 创建QysdsReportsDeclare对象
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			// 将form中的基本信息转入QysdsReportsDeclare report 中
			QysdsNewUtil.setQysdsReport(report, hdzssdsjbForm);
			// 设置QysdsReportsTableDeclare的基本信息
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_HDZSSDSJB);
			table.setTableName(CodeConstant.TABLE_NAME_HDZSSDSJB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			// 将QysdsReportsTableDeclare的基本信息置入QysdsReportsDeclare
			report.getTableContentList().put(table.getTableId(), table);
			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 调用查询方法进行查询
			iApp.querySingleTable(report);
			// 获取查询到的具体数据
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_HDZSSDSJB);

			if (table.getCellContentList().size() > 0) {
				hdzssdsjbForm.setSbrq(TinyTools.Date2String(report.getSbrq(),
						"yyyyMMdd"));
				hdzssdsjbForm.setSkssksrq(TinyTools.Date2String(report
						.getSkssksrq(), "yyyyMMdd"));
				hdzssdsjbForm.setSkssjsrq(TinyTools.Date2String(report
						.getSkssjsrq(), "yyyyMMdd"));
			}

			// 将数据库中的数据翻译成页面所需数据格式
			int[] arrs = { 1, 14 };
			hdzssdsjbForm.setQysdsjbList(this.translate2Page(putSpace(table,
					arrs)));

			// 测试用，完成后要删除
			System.out.println("计算机代码-" + hdzssdsjbForm.getJsjdm());
			System.out.println("申报日期-" + hdzssdsjbForm.getSbrq());
			System.out.println("纳税人名称-" + hdzssdsjbForm.getNsrmc());
			System.out.println("税款年度-" + hdzssdsjbForm.getSknd());
			System.out.println("期号-" + hdzssdsjbForm.getQh());
			System.out.println("报表期类型-" + hdzssdsjbForm.getBbqlx());
			System.out.println("税款所属开始日期-" + hdzssdsjbForm.getSkssksrq());
			System.out.println("税款所属结束日期-" + hdzssdsjbForm.getSkssjsrq());
			System.out.println("税务机关组织机构代码-" + hdzssdsjbForm.getSwjgzzjgdm());
			System.out.println("区县代码-" + hdzssdsjbForm.getQxdm());
			System.out.println("录入人-" + hdzssdsjbForm.getLrr());
			System.out.println("税务所计算机代码-" + hdzssdsjbForm.getSwjsjdm());

		} catch (Exception e) {
			// 抛出异常
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 查询成功返回hdzssdsjbForm
		return hdzssdsjbForm;
	}

	/**
	 * doSave 用于存储页面提交的详尽处理信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doSave(VOPackage vo) throws BaseException {
		// 得到Action传递过来HdzssdsjbForm对象

		HdzssdsjbForm hdzssdsjbForm = (HdzssdsjbForm) vo.getData();
		Connection conn = null;

		// Map getsbnd = this.quarterSkssrq(SfDateUtil.getDate(hdzssdsjbForm
		// .getSbrq()));
		// 获取税款所属季度
		String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(hdzssdsjbForm
				.getSkssjsrq()));
		// 获取税款所属年度
		// String sknd = this.getNd(jd, getsbnd, hdzssdsjbForm.getSbrq());
		// String nd = (String)getsbnd.get(Skssrq.SKSSRQ_ND);
		// 从页面中取得税款所属期和年度
		String nd = hdzssdsjbForm.getSkssksrq().substring(0, 4);
		// 设置季度
		hdzssdsjbForm.setQh(jd);
		// 设置年度
		hdzssdsjbForm.setSknd(nd);

		try {

			// /* 征收方式 */
			// String zsfs = QysdsNewUtil.getZsfsdm(hdzssdsjbForm);
			//			
			// System.out.println(hdzssdsjbForm.getJsjdm()+"的征收方式代码："+zsfs);
			//			
			//			
			// if(zsfs==null ||(zsfs!=null && zsfs.equals(""))){
			// throw new ApplicationException(
			// "没有查询到该企业的征收方式认定信息，请认定后再进行申报录入！");
			// }
			// if (CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
			// throw new ApplicationException(
			// "该企业已认定为查帐征收户，不能在此录入，请录入查帐征收季度申报表！");
			// }

			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 将hdzssdsjbForm中的数据结构转换，置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this
					.translate2Interface(hdzssdsjbForm);
			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 调用saveSingleTable方法进行数据保存
			iApp.saveSingleTable(report);

			// 获取一个具有空值的QysdsReportsTableDeclare对象
			QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report
					.getTableContentList().get(CodeConstant.TABLE_ID_HDZSSDSJB);
			table.getCellContentList().clear();

			// 将数据库中的数据翻译成页面所需数据格式
			int[] arrs = { 1, 14 };
			hdzssdsjbForm.setQysdsjbList(this.translate2Page(putSpace(table,
					arrs)));

			hdzssdsjbForm = (HdzssdsjbForm) this.doShow(vo);

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 保存成功返回hdzssdsjbForm
		return hdzssdsjbForm;
	}

	/**
	 * doCheck 用于存储页面提交的详尽处理信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */
	// private Object doCheck(VOPackage vo) throws BaseException {
	//
	// // 得到Action传递过来HdzssdsjbForm对象
	// HdzssdsjbForm hdzssdsjbForm = (HdzssdsjbForm) vo.getData();
	// Connection conn = null;
	//
	// try {
	// // 创建数据库连接
	// conn = SfDBResource.getConnection();
	// // 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
	// QysdsReportsDeclare report = this
	// .translate2Interface(hdzssdsjbForm);
	// // 获取校验接口
	// Checker checker = CheckerFactory.getAInstance(conn,
	// CheckerFactory.ACCESS_MODEL_APP_QYSDS);
	// // 进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
	// List listSingle = checker.checkSingeTable(report);
	// hdzssdsjbForm.setCheckList(listSingle);
	// } catch (Exception ex) {
	// // 抛出异常
	// ex.printStackTrace();
	// throw ExceptionUtil.getBaseException(ex);
	// } finally {
	// // 释放数据库连接
	// SfDBResource.freeConnection(conn);
	// }
	// // 检验成功返回hdzssdsjbForm
	// return hdzssdsjbForm;
	// }
	/**
	 * doDelete 用于删除页面提交的详尽处理信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doDelete(VOPackage vo) throws BaseException {

		// 得到Action传递过来HdzssdsjbForm对象
		HdzssdsjbForm hdzssdsjbForm = (HdzssdsjbForm) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();

			// 将ActionForm中的基本信息转入QysdsReportsDeclare对象中
			QysdsReportsDeclare report = this
					.translate2Interface(hdzssdsjbForm);

			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 调用deleteSingleTable方法进行数据删除
			iApp.deleteSingleTable(report);

			// 获取一个具有空值的QysdsReportsTableDeclare对象
			QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report
					.getTableContentList().get(CodeConstant.TABLE_ID_HDZSSDSJB);
			table.getCellContentList().clear();

			// 将数据库中的数据翻译成页面所需数据格式
			int[] arrs = { 1, 14 };
			hdzssdsjbForm.setQysdsjbList(this.translate2Page(putSpace(table,
					arrs)));

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 删除成功返回hdzssdsjbForm
		return hdzssdsjbForm;
	}

	// /**
	// * 根据季度求得所属年度的方法
	// *
	// * @param jd
	// * 季度
	// * @param getsbnd
	// * 申报年度
	// * @param sbrq
	// * 申报日期
	// * @return 年度
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

	// /**
	// * 根据申报日期取得当前前年0101-1231
	// *
	// * @param curSbrq
	// * 申报日期
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
	 * 计算季报类型的税款所属日期
	 * 
	 * @param curDate
	 *            日期
	 * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp 使用Key ＝
	 *         Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp 使用Key ＝ Skssrq.SKSSRQ_ND 得到
	 *         税款所属日期所在的年度 String
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
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构 页面数据结构-->接口数据结构
	 * 
	 * @param form
	 * @return 企业所得税报表申明对象
	 */
	private QysdsReportsDeclare translate2Interface(HdzssdsjbForm form) {

		// 创建QysdsReportsDeclare对象
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		// 将form中的基本信息转入QysdsReportsDeclare对象中
		QysdsNewUtil.setQysdsReport(report, form);

		// 创建企业所得税报表内单表申明对象，并置入基本信息
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_HDZSSDSJB);
		table.setTableName(CodeConstant.TABLE_NAME_HDZSSDSJB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);

		// 把页面数据翻译成数据库接口所需的数据格式
		List list = form.getQysdsjbList();

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
	 * 将接口数据结构中的数据转换，置入页面要求的数据结构 接口数据结构-->页面数据结构
	 * 
	 * @param QysdsReportsTableDeclare
	 * @return 页面表单数据的List对象
	 */
	private List translate2Page(QysdsReportsTableDeclare table) {

		// 创建每个行所对应的map
		HashMap map1 = new HashMap();
		HashMap map2 = new HashMap();
		HashMap map3 = new HashMap();
		HashMap map4 = new HashMap();
		HashMap map5 = new HashMap();
		HashMap map6 = new HashMap();
		HashMap map7 = new HashMap();
		HashMap map8 = new HashMap();
		// 创建List对象，用来存放页面表单数据
		ArrayList pagelist = new ArrayList();
		// 转入表单各行的数据
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
		// 返回List对象
		return pagelist;
	}

	/**
	 * 取从一率和企业征税类型,用于页面校验
	 * 
	 * @param form
	 * @throws BaseException
	 */
	private void getHdxx(HdzssdsjbForm form) throws BaseException {

		String qyzssllx = "3"; // 缺省为正常申报

		// 企业征税的税率 相对于企业征税的税率类型
		String qyzssl = QYSDS_SL;

		// 应纳所得税额
		String ynsdse = "0.00";
		// 定额征收税额
		String dezsse = "0.00";

		// 当前时间
		// Timestamp sbrq = new Timestamp(System.currentTimeMillis());
		// 从申报页面取得申报日期和税款所属日期
		Timestamp sbrq = QysdsNewUtil.getNxetTimestamp(form.getSkssjsrq());

		// Map getsbjd = this.quarterSkssrq(sbrq);
		Timestamp skssksrq = QysdsNewUtil.getTimestamp(form.getSkssksrq());
		Timestamp skssjsrq = QysdsNewUtil.getTimestamp(form.getSkssjsrq());

		System.out.println(form.getJsjdm() + "sbrq：" + sbrq);
		System.out.println(form.getJsjdm() + "skssksrq：" + skssksrq);
		System.out.println(form.getJsjdm() + "skssjsrq：" + skssjsrq);

		ServiceProxy proxy = new ServiceProxy();

		String bblx = form.getBbqlx();
		String jsjdm = form.getJsjdm();

		// 查询税费接口
		QysdsSet qysdsSet = null;

		// 数据库连接对象
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		// 减免资格标识
		boolean jm_type = false;

		try {
			if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_YEAR)) {
				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						CodeConstant.SFGL_QYSDS_BBFS_NB);
			} else if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR)) {
				/* 如果为第四季度，或取企业所得税认定信息时按年报来获取 */

				if (form.getQh() == null
						|| (form.getQh() != null && form.getQh().trim().equals(
								""))) {
					/* 期号不能为空，如果为空抛出异常 */
					throw new ApplicationException("系统发生异常，期号为空，请与系统管理员联系！");
				}
				if ("4".equals(form.getQh())) {
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

		// 1、查询企业征收方式
		Zsfs zsfs = qysdsSet.getZsfs();
		if (zsfs != null) {
			form.setZsfs(zsfs.getZsfsdm() == null ? CodeConstant.ZSFSDM_CZZS
					: zsfs.getZsfsdm());
		} else {
			// form.setZsfs("");
			// 20070208征收方式如果取出为空则认为是查账征收企业的。
			form.setZsfs(CodeConstant.ZSFSDM_CZZS);
		}

		/* 高新技术企业认定日期 */
		Date gxqyrdrq = qysdsSet.getGxjsqy();

		// 初值
		form.setCyl("0");
		form.setXzqy("0");
		form.setDezsse("0.00");
		form.setYbjmsl("0.00");

		if (zsfs != null) {
			String zsfsdm = zsfs.getZsfsdm();
			if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) {
				if (gxqyrdrq == null) {
					// 纯益率征收
					qyzssllx = "2";
				} else {
					// 高新技术和纯益率企业
					qyzssllx = "5";
					qyzssl = "0.15";
					form.setJmzg("1"); // 有减免资格
				}
				form.setCyl(zsfs.getCyl());
			} else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) {
				// 定额征收
				qyzssllx = "4";
				// 此时本字段代表企业核定税额
				// ynsdse = zsfs.getDe();
				dezsse = zsfs.getDe();
				form.setDezsse(dezsse);
			}
		}

		// 2、查询是否是高新技术企业
		if (gxqyrdrq != null) {
			if (zsfs != null
					&& zsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
				// 高新技术和纯益率企业
				qyzssllx = "5";
			} else {
				// 类型为高新技术企业
				qyzssllx = "1";
			}
			qyzssl = "0.15";
			form.setJmzg("1"); // 有减免资格
		} else if (form.getSsjjlx().equals(CodeConstant.JITIQIYE_CODE)) {
			// 判断是否乡镇企业，“1”为乡镇企业，“0”为不是乡镇企业
			if (qysdsSet.isXzqy()) {
				form.setXzqy("1");
				form.setJmzg("1"); // 有减免资格
			}
		}

		if (!(form.getXzqy() != null && form.getXzqy().equals("1"))
				&& qysdsSet.getYbjmsl() != null) {
			// 非乡镇企业的减免情况
			form.setJmzg("1"); // 有减免资格
			DecimalFormat ft = new DecimalFormat("0.00");
			form.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
		}
		form.setQyzslx(qyzssllx);
		form.setSysl(qyzssl);

		

		/* 核定信息输出 */
		System.out.println("-------------核定信息--------------");
		System.out.println("企业征收税率类型-" + qyzssllx);
		System.out.println("减免资格-" + form.getJmzg());
		System.out.println("一般减免税率-" + form.getYbjmsl());
		System.out.println("征收方式-" + form.getZsfs());
		System.out.println("纯益率-" + form.getCyl());
		System.out.println("定额-" + form.getDezsse());
		System.out.println("适用税率-" + form.getSysl());
		System.out.println("-------------核定信息--------------");
	}

	/**
	 * 把存放数据时过滤掉的空格复原
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

		System.out.println("**显示qysdsNewUtil中的putSpace()**");

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
