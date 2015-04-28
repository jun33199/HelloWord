package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb2.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.creports.Constants;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsItemDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsTableDeclare;
import com.syax.creports.check.Checker;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb2.web.QyqssdsFb2Form2014;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.DateUtilToChinese;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsSaveAndCheck;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * 项目名称：smsb   
 * 类名称：QyqssdsFb2Processor2014   
 * 类描述：   附表二：负债清偿损益明细表
 * 创建人：wangcy 
 * 创建时间：2014-2-17 下午3:31:00   
 * 修改人：wangcy   
 * 修改时间：2014-2-17 下午3:31:00   
 * 修改备注：   
 * @version  1.0
 */
public class QyqssdsFb2Processor2014 implements Processor {
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
		QyqssdsFb2Form2014 fb2Form = (QyqssdsFb2Form2014) vo.getData();
		Connection conn = null;
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();

			/*----add by huohb,add skssq-----*/
			String ksrq =fb2Form.getQssbksrq();
			String jsrq =fb2Form.getQssbjsrq();
			String skssq=DateUtilToChinese.convertDate(ksrq, jsrq);
			fb2Form.setSkssq(skssq);
			
			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();

			QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
			QyqssdsUtil2014.setQyqssdsReport(report, fb2Form);
			table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_2);
			table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_2);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			report.getTableContentList().put(table.getTableId(), table);
			// 获取数据库接口，调用query方法进行数据查找
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			iApp.querySingleTable(report);
			table = (QyqssdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_2);

			int[] arra1 = { 1, 92 };
			fb2Form.setDataList(this.FGXtranslate2Page(QyqssdsUtil2014.putSpace(table, arra1)));
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return fb2Form;
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
		QyqssdsFb2Form2014 form = (QyqssdsFb2Form2014) vo.getData();
		Connection conn = null;
		try {
			// 获取数据库连接
			conn = SfDBResource.getConnection();

			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QyqssdsReportsDeclare report = this.translate2Interface(form);

			// 获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			iApp.saveSingleTable(report);
			// 更新审核状态为“保存成功”
			iApp.updateCheckStatus(report, Constants.QYQSSDS_SHZT_SAVE);
			
			//更新申报表中的申报状态标识为“已提交未审核”
			QyqssdsSaveAndCheck.updateFlag(conn,form);
			//更新所有申报开始日期和结束日期
			QyqssdsUtil2014.updateAllDate(conn, form);
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
		QyqssdsFb2Form2014 fb2Form = (QyqssdsFb2Form2014) vo.getData();
		Connection conn = null;
		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();

			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QyqssdsReportsDeclare report = this.translate2Interface(fb2Form);

			// 获取校验接口
			Checker checker = CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYQSSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);

			// 进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List listSingle = checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			/* 如果校验通过，调用接口保存数据 */
			if (listSingle == null || (listSingle != null && listSingle.size() == 0)) {
				//网上申报的和上门审核通过的单表校验不保存数据
				if(fb2Form.getSqlx()=="0"||fb2Form.getSbShztbs()=="2"){
					
				}else{
					iApp.saveSingleTable(report);
				}
				// 更新审核状态为“单表审核通过”
				iApp.updateCheckStatus(report, Constants.QYQSSDS_SHZT_SINGLE_PASS);
			} else if (listSingle.size() > 0) {
				// 单表审核未通过
				iApp.updateCheckStatus(report, "");
			}
			fb2Form.setCheckList(listSingle);
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		return fb2Form;
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
		QyqssdsFb2Form2014 fb2Form = (QyqssdsFb2Form2014) vo.getData();
		Connection conn = null;
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();

			QyqssdsReportsDeclare report1 = this.translate2Interface(fb2Form);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			iApp.deleteSingleTable(report1);
			iApp.updateCheckStatus(report1, "");
			QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
			table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_2);
			table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_2);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			int[] arra1 = { 1, 92 };
			table = (QyqssdsReportsTableDeclare) report1.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_2);
			fb2Form.setDataList(this.FGXtranslate2Page(QyqssdsUtil2014.putSpace(table, arra1)));

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return fb2Form;
	}


	/**
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构 页面数据结构-->接口数据结构
	 * 
	 * @param QyqssdsFb2Form2014
	 * @return 企业所得税报表申明对象
	 */
	private QyqssdsReportsDeclare translate2Interface(QyqssdsFb2Form2014 form) {
		// 企业所得税报表申明对象
		QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();
		QyqssdsUtil2014.setQyqssdsReport(report, form);
		// 企业所得税报表内单表申明对象
		QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
		List list = form.getDataList();
		table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_2);
		table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_2);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
		int js = 1; // 计数器
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
//			String hc1 = (String) map.get("hc1");
//			if (Integer.parseInt(hc1) < 5) {
//				QyqssdsReportsItemDeclare item_7_1 = new QyqssdsReportsItemDeclare();
//				item_7_1.setItemID("" + js);
//				item_7_1.setItemValue((String) map.get("zmjz"));
//				item_7_1.setItemType("11");
//				table.getCellContentList().put(item_7_1.getItemID(), item_7_1);
//				js++;
//
//				QyqssdsReportsItemDeclare item_7_2 = new QyqssdsReportsItemDeclare();
//				item_7_2.setItemID("" + js);
//				item_7_2.setItemValue((String) map.get("jsjc"));
//				item_7_2.setItemType("11");
//				table.getCellContentList().put(item_7_2.getItemID(), item_7_2);
//				js++;
//
//				QyqssdsReportsItemDeclare item_7_3 = new QyqssdsReportsItemDeclare();
//				item_7_3.setItemID("" + js);
//				item_7_3.setItemValue((String) map.get("qcje"));
//				item_7_3.setItemType("11");
//				table.getCellContentList().put(item_7_3.getItemID(), item_7_3);
//				js++;
//
//				QyqssdsReportsItemDeclare item_7_4 = new QyqssdsReportsItemDeclare();
//				item_7_4.setItemID("" + js);
//				item_7_4.setItemValue((String) map.get("fzqcxy"));
//				item_7_4.setItemType("11");
//				table.getCellContentList().put(item_7_4.getItemID(), item_7_4);
//				js++;
//			}
//
//			else if ("5".equals(hc1)) {
//				QyqssdsReportsItemDeclare item_7_1 = new QyqssdsReportsItemDeclare();
//				item_7_1.setItemID("21");
//				item_7_1.setItemValue((String) map.get("zmjz"));
//				item_7_1.setItemType("11");
//				table.getCellContentList().put(item_7_1.getItemID(), item_7_1);
//
//				QyqssdsReportsItemDeclare item_7_3 = new QyqssdsReportsItemDeclare();
//				item_7_3.setItemID("22");
//				item_7_3.setItemValue((String) map.get("qcje"));
//				item_7_3.setItemType("11");
//				table.getCellContentList().put(item_7_3.getItemID(), item_7_3);
//
//				QyqssdsReportsItemDeclare item_7_4 = new QyqssdsReportsItemDeclare();
//				item_7_4.setItemID("23");
//				item_7_4.setItemValue((String) map.get("fzqcxy"));
//				item_7_4.setItemType("11");
//				table.getCellContentList().put(item_7_4.getItemID(), item_7_4);
//
//				js = 25;
//			} else {
				QyqssdsReportsItemDeclare item_7_1 = new QyqssdsReportsItemDeclare();
				item_7_1.setItemID("" + js);
				item_7_1.setItemValue((String) map.get("zmjz"));
				item_7_1.setItemType("11");
				table.getCellContentList().put(item_7_1.getItemID(), item_7_1);
				js++;

				QyqssdsReportsItemDeclare item_7_2 = new QyqssdsReportsItemDeclare();
				item_7_2.setItemID("" + js);
				item_7_2.setItemValue((String) map.get("jsjc"));
				item_7_2.setItemType("11");
				table.getCellContentList().put(item_7_2.getItemID(), item_7_2);
				js++;

				QyqssdsReportsItemDeclare item_7_3 = new QyqssdsReportsItemDeclare();
				item_7_3.setItemID("" + js);
				item_7_3.setItemValue((String) map.get("qcje"));
				item_7_3.setItemType("11");
				table.getCellContentList().put(item_7_3.getItemID(), item_7_3);
				js++;

				QyqssdsReportsItemDeclare item_7_4 = new QyqssdsReportsItemDeclare();
				item_7_4.setItemID("" + js);
				item_7_4.setItemValue((String) map.get("fzqcxy"));
				item_7_4.setItemType("11");
				table.getCellContentList().put(item_7_4.getItemID(), item_7_4);
				js++;

//			}

		}
		report.getTableContentList().put(table.getTableId(),QyqssdsUtil2014.cleanSpace(table));
		return report;
	}

	/**
	 * 将接口数据结构中的数据转换置入页面要求的数据结构 接口数据结构-->页面数据结构
	 * 
	 * @param QyqssdsReportsTableDeclare
	 */
	private List FGXtranslate2Page(QyqssdsReportsTableDeclare table) {
		List list = new ArrayList();
		Map map = table.getCellContentList();
		int js = 1; // 计数器
		for (int i = 1; i < 24; i++) {
//			if (i < 4) {
//				Map map1 = new HashMap();
//				map1.put("hc1", "" + i);
//
//				QyqssdsReportsItemDeclare item1_1 = (QyqssdsReportsItemDeclare) map.get("" + js);
//				String zmjz1 = item1_1.getItemValue();
//				map1.put("zmjz", zmjz1);
//				js++;
//
//				QyqssdsReportsItemDeclare item1_2 = (QyqssdsReportsItemDeclare) map.get("" + js);
//				String jsjc1 = item1_2.getItemValue();
//				map1.put("jsjc", jsjc1);
//				js++;
//
//				QyqssdsReportsItemDeclare item1_3 = (QyqssdsReportsItemDeclare) map.get("" + js);
//				String qcje1 = item1_3.getItemValue();
//				map1.put("qcje", qcje1);
//				js++;
//
//				QyqssdsReportsItemDeclare item1_4 = (QyqssdsReportsItemDeclare) map.get("" + js);
//				String fzqcxy1 = item1_4.getItemValue();
//				map1.put("fzqcxy", fzqcxy1);
//				js++;
//
//				list.add(map1);
//			} else if (i == 5) {
//				Map map5 = new HashMap();
//				map5.put("hc1", "5");
//				QyqssdsReportsItemDeclare item5_1 = (QyqssdsReportsItemDeclare) map.get("21");
//				String zmjz5 = item5_1.getItemValue();
//				map5.put("zmjz", zmjz5);
//				map5.put("jsjc", "——");
//				QyqssdsReportsItemDeclare item5_3 = (QyqssdsReportsItemDeclare) map.get("22");
//				String qcje5 = item5_3.getItemValue();
//				map5.put("qcje", qcje5);
//				QyqssdsReportsItemDeclare item5_4 = (QyqssdsReportsItemDeclare) map.get("23");
//				String fzqcxy5 = item5_4.getItemValue();
//				map5.put("fzqcxy", fzqcxy5);
//				QyqssdsReportsItemDeclare item5_5 = (QyqssdsReportsItemDeclare) map.get("24");
//				String xj5 = item5_5.getItemValue();
//				map5.put("xj", xj5);
//				js = 25;
//				list.add(map5);
//			} else {
				Map map1 = new HashMap();
				map1.put("hc1", "" + i);

				QyqssdsReportsItemDeclare item1_1 = (QyqssdsReportsItemDeclare) map.get("" + js);
				String zmjz1 = item1_1.getItemValue();
				map1.put("zmjz", zmjz1);
				js++;

				QyqssdsReportsItemDeclare item1_2 = (QyqssdsReportsItemDeclare) map.get("" + js);
				String jsjc1 = item1_2.getItemValue();
				map1.put("jsjc", jsjc1);
				js++;

				QyqssdsReportsItemDeclare item1_3 = (QyqssdsReportsItemDeclare) map.get("" + js);
				String qcje1 = item1_3.getItemValue();
				map1.put("qcje", qcje1);
				js++;

				QyqssdsReportsItemDeclare item1_4 = (QyqssdsReportsItemDeclare) map.get("" + js);
				String fzqcxy1 = item1_4.getItemValue();
				map1.put("fzqcxy", fzqcxy1);
				js++;
				
				list.add(map1);
//			}
		}

		// 测试反向输出
		for (int h = 0; h < list.size(); h++) {
			HashMap mapcs = (HashMap) list.get(h);

			String hc = (String) mapcs.get("hc1");
			String zmjz = (String) mapcs.get("zmjz");
			String jsjc = (String) mapcs.get("jsjc");
			String qcje = (String) mapcs.get("qcje");
			String fzqcxy = (String) mapcs.get("fzqcxy");


			System.out.println("hc-" + hc + "   zmjz-" + zmjz + "   jsjc-"
					+ jsjc + "     qcje-" + qcje + "       fzqcxy-" + fzqcxy
					);

		}
		return list;

	}

}
