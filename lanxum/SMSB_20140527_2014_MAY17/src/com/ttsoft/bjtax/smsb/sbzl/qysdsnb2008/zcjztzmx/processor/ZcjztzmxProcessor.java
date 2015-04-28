/**
 *
 */
package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.zcjztzmx.processor;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web.QysdsnbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.util.QysdsUtil2008;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.zcjztzmx.web.ZcjztzmxForm;
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
 * Description:2008查帐征收企业所得税季报
 * </p>
 * 
 * @author Ha Zhengze
 * @version 1.1
 */
public class ZcjztzmxProcessor implements Processor {
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
		ZcjztzmxForm ZcjztzmxForm = (ZcjztzmxForm) vo.getData();
		Connection conn = null;
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();

			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = new QysdsReportsDeclare();

			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			QysdsUtil2008.setQysdsReport(report, ZcjztzmxForm);
			table.setTableId(CodeConstant.TABLE_ID_2008_10);
			table.setTableName(CodeConstant.TABLE_NAME_2008_10);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(), table);
			// 获取数据库接口，调用query方法进行数据查找
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_2008_10);

			int[] arra1 = { 1, 84 };
			ZcjztzmxForm.setDataList(this.FGXtranslate2Page(QysdsUtil2008
					.putSpace(table, arra1)));
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return ZcjztzmxForm;
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

		return null;
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
		ZcjztzmxForm form = (ZcjztzmxForm) vo.getData();
		Connection conn = null;
		try {
			// 获取数据库连接
			conn = SfDBResource.getConnection();

			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(form);

			// 获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
			// 更新审核状态为“保存成功”
			iApp.updateCheckStatus(report, Constants.QYSDS_SHZT_SAVE);
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return form;

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
	private Object doCheck(VOPackage vo) throws BaseException {
		ZcjztzmxForm ZcjztzmxForm = (ZcjztzmxForm) vo.getData();
		Connection conn = null;
		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();

			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(ZcjztzmxForm);

			// 获取校验接口
			Checker checker = CheckerFactory.getAInstance(conn,
					CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);

			// 进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List listSingle = checker.checkSingeTable(report,
					Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			/* 如果校验通过，调用接口保存数据 */
			if (listSingle == null
					|| (listSingle != null && listSingle.size() == 0)) {
				iApp.saveSingleTable(report);
				// 更新审核状态为“单表审核通过”
				iApp
						.updateCheckStatus(report,
								Constants.QYSDS_SHZT_SINGLE_PASS);
			} else if (listSingle.size() > 0) {
				// 单表审核未通过
				iApp.updateCheckStatus(report, "");
			}
			ZcjztzmxForm.setCheckList(listSingle);
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		return ZcjztzmxForm;
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
		ZcjztzmxForm ZcjztzmxForm = (ZcjztzmxForm) vo.getData();
		Connection conn = null;
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();

			QysdsReportsDeclare report1 = this
					.translate2Interface(ZcjztzmxForm);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report1);
			iApp.updateCheckStatus(report1, "");
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2008_10);
			table.setTableName(CodeConstant.TABLE_NAME_2008_10);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			int[] arra1 = { 1, 84 };
			table = (QysdsReportsTableDeclare) report1.getTableContentList()
					.get(CodeConstant.TABLE_ID_2008_10);
			ZcjztzmxForm.setDataList(this.FGXtranslate2Page(QysdsUtil2008
					.putSpace(table, arra1)));

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return ZcjztzmxForm;
	}

	/**
	 * doUpdate 用于存储页面提交的详尽处理信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doUpdate(VOPackage vo) throws BaseException {

		QysdsnbForm nbForm = (QysdsnbForm) vo.getData();

		return nbForm;
	}

	/**
	 * 初始化
	 * 
	 * @param nbForm
	 *            主表数据
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private void initForm(QysdsnbForm nbForm) throws BaseException {

	}

	/**
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构 页面数据结构-->接口数据结构
	 * 
	 * @param ZcjztzmxForm
	 * @return 企业所得税报表申明对象
	 */
	private QysdsReportsDeclare translate2Interface(ZcjztzmxForm form) {
		// 企业所得税报表申明对象
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		QysdsUtil2008.setQysdsReport(report, form);
		// 企业所得税报表内单表申明对象
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		List list = form.getDataList();
		table.setTableId(CodeConstant.TABLE_ID_2008_10);
		table.setTableName(CodeConstant.TABLE_NAME_2008_10);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		int js = 1; // 计数器
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			String hc1 = (String) map.get("hc1");
			if (Integer.parseInt(hc1)<5) {
				QysdsReportsItemDeclare item_7_1 = new QysdsReportsItemDeclare();
				item_7_1.setItemID("" + js);
				item_7_1.setItemValue((String) map.get("gzxj"));
				item_7_1.setItemType("11");
				table.getCellContentList().put(item_7_1.getItemID(), item_7_1);
				js++;

				QysdsReportsItemDeclare item_7_2 = new QysdsReportsItemDeclare();
				item_7_2.setItemID("" + js);
				item_7_2.setItemValue((String) map.get("ghjf"));
				item_7_2.setItemType("11");
				table.getCellContentList().put(item_7_2.getItemID(), item_7_2);
				js++;

				QysdsReportsItemDeclare item_7_3 = new QysdsReportsItemDeclare();
				item_7_3.setItemID("" + js);
				item_7_3.setItemValue((String) map.get("zgflf"));
				item_7_3.setItemType("11");
				table.getCellContentList().put(item_7_3.getItemID(), item_7_3);
				js++;

				QysdsReportsItemDeclare item_7_4 = new QysdsReportsItemDeclare();
				item_7_4.setItemID("" + js);
				item_7_4.setItemValue((String) map.get("zgjyjf"));
				item_7_4.setItemType("11");
				table.getCellContentList().put(item_7_4.getItemID(), item_7_4);
				js++;

				QysdsReportsItemDeclare item_7_5 = new QysdsReportsItemDeclare();
				item_7_5.setItemID("" + js);
				item_7_5.setItemValue((String) map.get("xj"));
				item_7_5.setItemType("11");
				table.getCellContentList().put(item_7_5.getItemID(), item_7_5);
				js++;
			}

			else if ("5".equals(hc1)) {
				QysdsReportsItemDeclare item_7_1 = new QysdsReportsItemDeclare();
				item_7_1.setItemID("21");
				item_7_1.setItemValue((String) map.get("gzxj"));
				item_7_1.setItemType("11");
				table.getCellContentList().put(item_7_1.getItemID(), item_7_1);

				QysdsReportsItemDeclare item_7_3 = new QysdsReportsItemDeclare();
				item_7_3.setItemID("22");
				item_7_3.setItemValue((String) map.get("zgflf"));
				item_7_3.setItemType("11");
				table.getCellContentList().put(item_7_3.getItemID(), item_7_3);

				QysdsReportsItemDeclare item_7_4 = new QysdsReportsItemDeclare();
				item_7_4.setItemID("23");
				item_7_4.setItemValue((String) map.get("zgjyjf"));
				item_7_4.setItemType("11");
				table.getCellContentList().put(item_7_4.getItemID(), item_7_4);

				QysdsReportsItemDeclare item_7_5 = new QysdsReportsItemDeclare();
				item_7_5.setItemID("24");
				item_7_5.setItemValue((String) map.get("xj"));
				item_7_5.setItemType("11");
				table.getCellContentList().put(item_7_5.getItemID(), item_7_5);
				js = 25;
			}
			else {
				QysdsReportsItemDeclare item_7_1 = new QysdsReportsItemDeclare();
				item_7_1.setItemID("" + js);
				item_7_1.setItemValue((String) map.get("gzxj"));
				item_7_1.setItemType("11");
				table.getCellContentList().put(item_7_1.getItemID(), item_7_1);
				js++;

				QysdsReportsItemDeclare item_7_2 = new QysdsReportsItemDeclare();
				item_7_2.setItemID("" + js);
				item_7_2.setItemValue((String) map.get("ghjf"));
				item_7_2.setItemType("11");
				table.getCellContentList().put(item_7_2.getItemID(), item_7_2);
				js++;

				QysdsReportsItemDeclare item_7_3 = new QysdsReportsItemDeclare();
				item_7_3.setItemID("" + js);
				item_7_3.setItemValue((String) map.get("zgflf"));
				item_7_3.setItemType("11");
				table.getCellContentList().put(item_7_3.getItemID(), item_7_3);
				js++;

				QysdsReportsItemDeclare item_7_4 = new QysdsReportsItemDeclare();
				item_7_4.setItemID("" + js);
				item_7_4.setItemValue((String) map.get("zgjyjf"));
				item_7_4.setItemType("11");
				table.getCellContentList().put(item_7_4.getItemID(), item_7_4);
				js++;

				QysdsReportsItemDeclare item_7_5 = new QysdsReportsItemDeclare();
				item_7_5.setItemID("" + js);
				item_7_5.setItemValue((String) map.get("xj"));
				item_7_5.setItemType("11");
				table.getCellContentList().put(item_7_5.getItemID(), item_7_5);
				js++;
			}

		}
		report.getTableContentList().put(table.getTableId(),
				QysdsUtil2008.cleanSpace(table));
		return report;
	}

	/**
	 * 将接口数据结构中的数据转换置入页面要求的数据结构 接口数据结构-->页面数据结构
	 * 
	 * @param QysdsReportsTableDeclare
	 */
	private List FGXtranslate2Page(QysdsReportsTableDeclare table) {
		List list = new ArrayList();
		Map map = table.getCellContentList();
        int js = 1; //计数器
		for(int i=1;i<18;i++){
			if(i<5){
				Map map1 = new HashMap();
				map1.put("hc1", "" + i);
				
				QysdsReportsItemDeclare item1_1 = (QysdsReportsItemDeclare) map.get("" + js);
				String gzxj1 = item1_1.getItemValue();
				map1.put("gzxj", gzxj1);
				js++;
				
				QysdsReportsItemDeclare item1_2 = (QysdsReportsItemDeclare) map.get("" + js);
				String ghjf1 = item1_2.getItemValue();
				map1.put("ghjf", ghjf1);
				js++;
				
				QysdsReportsItemDeclare item1_3 = (QysdsReportsItemDeclare) map.get("" + js);
				String zgflf1 = item1_3.getItemValue();
				map1.put("zgflf", zgflf1);
				js++;
				
				QysdsReportsItemDeclare item1_4 = (QysdsReportsItemDeclare) map.get("" + js);
				String zgjyjf1 = item1_4.getItemValue();
				map1.put("zgjyjf", zgjyjf1);
				js++;
				
				QysdsReportsItemDeclare item1_5 = (QysdsReportsItemDeclare) map.get("" + js);
				String xj1 = item1_5.getItemValue();
				map1.put("xj", xj1);
				js++;
				list.add(map1);
			}
			else if(i==5){
				Map map5 = new HashMap();
				map5.put("hc1", "5");
				QysdsReportsItemDeclare item5_1 = (QysdsReportsItemDeclare) map.get("21");
				String gzxj5 = item5_1.getItemValue();
				map5.put("gzxj", gzxj5);
				map5.put("ghjf", "——");
				QysdsReportsItemDeclare item5_3 = (QysdsReportsItemDeclare) map.get("22");
				String zgflf5 = item5_3.getItemValue();
				map5.put("zgflf", zgflf5);
				QysdsReportsItemDeclare item5_4 = (QysdsReportsItemDeclare) map.get("23");
				String zgjyjf5 = item5_4.getItemValue();
				map5.put("zgjyjf", zgjyjf5);
				QysdsReportsItemDeclare item5_5 = (QysdsReportsItemDeclare) map.get("24");
				String xj5 = item5_5.getItemValue();
				map5.put("xj", xj5);
				js = 25;
				list.add(map5);
			}
			else{
				Map map1 = new HashMap();
				map1.put("hc1", "" + i);
				
				QysdsReportsItemDeclare item1_1 = (QysdsReportsItemDeclare) map.get("" + js);
				String gzxj1 = item1_1.getItemValue();
				map1.put("gzxj", gzxj1);
				js++;
				
				QysdsReportsItemDeclare item1_2 = (QysdsReportsItemDeclare) map.get("" + js);
				String ghjf1 = item1_2.getItemValue();
				map1.put("ghjf", ghjf1);
				js++;
				
				QysdsReportsItemDeclare item1_3 = (QysdsReportsItemDeclare) map.get("" + js);
				String zgflf1 = item1_3.getItemValue();
				map1.put("zgflf", zgflf1);
				js++;
				
				QysdsReportsItemDeclare item1_4 = (QysdsReportsItemDeclare) map.get("" + js);
				String zgjyjf1 = item1_4.getItemValue();
				map1.put("zgjyjf", zgjyjf1);
				js++;
				
				QysdsReportsItemDeclare item1_5 = (QysdsReportsItemDeclare) map.get("" + js);
				String xj1 = item1_5.getItemValue();
				map1.put("xj", xj1);
				js++;
				list.add(map1);
			}
		}

		// 测试反向输出
		for (int h = 0; h < list.size(); h++) {
			HashMap mapcs = (HashMap) list.get(h);

			String hc = (String) mapcs.get("hc1");
			String gzxj = (String) mapcs.get("gzxj");
			String ghjf = (String) mapcs.get("ghjf");
			String zgflf = (String) mapcs.get("zgflf");
			String zgjyjf = (String) mapcs.get("zgjyjf");
			String xj = (String) mapcs.get("xj");

			System.out.println("hc-" + hc + "   gzxj-" + gzxj + "   ghjf-"
					+ ghjf + "     zgflf-" + zgflf + "       zgjyjf-" + zgjyjf
					+ "   xj-" + xj);

		}
		return list;

	}

}
