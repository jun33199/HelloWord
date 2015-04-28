/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信科技有限公司，版权所有. </p>
 * <p>Company: 四一安信科技有限公司</p>
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
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:企业所得税年报
 * </p>
 * 
 * @author Cao Gang
 * @version 1.1
 */

public class JskffmxbProcessor implements Processor {

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

		JskffmxbForm jskffmxbForm = (JskffmxbForm) vo.getData();

		Connection conn = null;

		try {
			// 获取数据库连接
			conn = SfDBResource.getConnection();

			QysdsReportsDeclare report = new QysdsReportsDeclare();
			// 将form中的基本信息转入QysdsReportsDeclare report 中
			QysdsNewUtil.setQysdsReport(report, jskffmxbForm);
			// 设置QysdsReportsTableDeclare的基本信息
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_JSKFFMXB);
			table.setTableName(CodeConstant.TABLE_NAME_JSKFFMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			// 将QysdsReportsTableDeclare的基本信息置入QysdsReportsDeclare
			report.getTableContentList().put(table.getTableId(), table);

			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 调用查询方法进行查询
			iApp.querySingleTable(report);
			// 获取查询到的具体数据
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_JSKFFMXB);
			// 将数据库中的数据翻译成页面所需数据格式
			int[] array = { 1, 25 };
			jskffmxbForm.setDataList(this.translate2Page(QysdsNewUtil.putSpace(
					table, array)));
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 查询成功返回jskffmxbForm
		return jskffmxbForm;
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

		JskffmxbForm jskffmxbForm = (JskffmxbForm) vo.getData();

		Connection conn = null;

		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();

			// 将ActionForm中的数据结构转换,置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(jskffmxbForm);
			// 获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 调用saveSingleTable方法进行数据保存
			iApp.saveSingleTable(report);
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 保存成功返回jskffmxbForm
		return jskffmxbForm;
	}

	/**
	 * doCheck 用于校验表内关系
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */
	private Object doCheck(VOPackage vo) throws BaseException {

		JskffmxbForm jskffmxbForm = (JskffmxbForm) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(jskffmxbForm);
			// 获取校验接口
			Checker checker = CheckerFactory.getAInstance(conn,
					CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			// 进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List listSingle = checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			jskffmxbForm.setCheckList(listSingle);
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 检验成功返回jskffmxbForm
		return jskffmxbForm;
	}

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

		JskffmxbForm jskffmxbForm = (JskffmxbForm) vo.getData();
		Connection conn = null;
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();

			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(jskffmxbForm);

			// 获取数据库接口，调用delete方法进行数据删除
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
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return jskffmxbForm;
	}

	/**
	 * 将ActionForm中的数据结构转换，置入数据库接口参数要求的数据结构 页面数据结构-->接口数据结构
	 * 
	 * @param jskffmxbForm
	 * @return 企业所得税报表申明对象
	 */
	private QysdsReportsDeclare translate2Interface(JskffmxbForm jskffmxbForm) {

		// 企业所得税报表申明对象
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		// 将form中的基本信息转入QysdsReportsDeclare对象中
		QysdsNewUtil.setQysdsReport(report, jskffmxbForm);

		// 创建企业所得税报表内单表申明对象，并置入基本信息
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_JSKFFMXB);
		table.setTableName(CodeConstant.TABLE_NAME_JSKFFMXB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// 把页面数据翻译成数据库接口的数据格式
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
	 * 将接口数据结构中的数据转换，置入页面要求的数据结构 接口数据结构-->页面数据结构
	 * 
	 * @param QysdsReportsTableDeclare
	 * @return 页面表单数据的List对象
	 */
	private List translate2Page(QysdsReportsTableDeclare table) {
		// 创建List对象，用来存放页面表单数据
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
