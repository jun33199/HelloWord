package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.web.QyqssdsZbForm2014;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.DateUtilToChinese;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsSaveAndCheck;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;


/**
 * 
 * 项目名称：smsb   
 * 类名称：QyqssdsZbProcessor2014   
 * 类描述：    中华人民共和国企业清算所得税申报表
 * 创建人：wangcy 
 * 创建时间：2014-2-17 下午12:26:15   
 * 修改人：wangcy   
 * 修改时间：2014-2-17 下午12:26:15   
 * 修改备注：   
 * @version  1.0
 */
public class QyqssdsZbProcessor2014 implements Processor {

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
		// 获取Action传递过来ZbForm对象
		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();

			/*----add by huohb,add skssq-----*/
			String ksrq =zbForm.getQssbksrq();
			String jsrq =zbForm.getQssbjsrq();
			String skssq=DateUtilToChinese.convertDate(ksrq, jsrq);
			zbForm.setSkssq(skssq);
			
			// 创建QyqssdsReportsDeclare对象
			QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();
			// 将form中的基本信息转入QyqssdsReportsDeclare report 中
			QyqssdsUtil2014.setQyqssdsReport(report, zbForm);
			// 设置QyqssdsReportsTableDeclare的基本信息
			QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
			table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_ZB);
			table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_ZB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			// 将QyqssdsReportsTableDeclare的基本信息置入QyqssdsReportsDeclare
			report.getTableContentList().put(table.getTableId(), table);

			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// 调用查询方法进行查询
			iApp.querySingleTable(report);
			// 获取查询到的具体数据
			table = (QyqssdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_ZB);
			// 将数据库中的数据翻译成页面所需数据格式
			int[] arrs = { 1, 18 };
			zbForm.setDataList(this.translate2Page(QyqssdsUtil2014.putSpace(table,arrs)));
		
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 查询成功返回QyqssdsZbForm2014
		return zbForm;
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
		// 得到Action传递过来QyqssdsZbForm2014对象
		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 将ActionForm中的数据结构转换，置入数据库接口参数要求的数据结构
			QyqssdsReportsDeclare report = this.translate2Interface(zbForm);
			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// 调用saveSingleTable方法进行数据保存
			iApp.saveSingleTable(report);
			// 更新审核状态为“保存成功”
			iApp.updateCheckStatus(report, Constants.QYQSSDS_SHZT_SAVE);

			//更新申报表中的申报状态标识为“已提交未审核”
			QyqssdsSaveAndCheck.updateFlag(conn,zbForm);
			//更新所有申报开始日期和结束日期
			QyqssdsUtil2014.updateAllDate(conn, zbForm);
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 保存成功返回zbForm
		return zbForm;
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
		// 得到Action传递过来QyqssdsZbForm2014对象
		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 将ActionForm中的数据结构转换，置入数据库接口参数要求的数据结构
			QyqssdsReportsDeclare report = this.translate2Interface(zbForm);
			// 获取校验接口
			Checker checker = CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYQSSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// 进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List listSingle = checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			zbForm.setCheckList(listSingle);
			/* 如果校验通过，调用接口保存数据 */
			if (listSingle == null || (listSingle != null && listSingle.size() == 0)) {
				//网上申报的和上门审核通过的单表校验不保存数据
				if(zbForm.getSqlx()=="0"||zbForm.getSbShztbs()=="2"){
					
				}else{
					iApp.saveSingleTable(report);
				}
				// 更新审核状态为“单表审核通过”
				iApp.updateCheckStatus(report,Constants.QYQSSDS_SHZT_SINGLE_PASS);
				// 审核通过之后保存减免数据
				// this.saveJM(vo);
			} else if (listSingle.size() > 0) {
				// 单表审核未通过
				iApp.updateCheckStatus(report, "");
			}
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 检验成功返回cbmxbybqyForm
		return zbForm;
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

		QyqssdsZbForm2014 zbForm = (QyqssdsZbForm2014) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 将ActionForm中的数据结构转换，置入数据库接口参数要求的数据结构
			QyqssdsReportsDeclare report = this.translate2Interface(zbForm);
			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// 调用saveSingleTable方法进行数据删除
			iApp.deleteSingleTable(report);

			iApp.updateCheckStatus(report, "");

			QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
			table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_ZB);
			table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_ZB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			table = (QyqssdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_ZB);
			// 取list
			int[] arrs = { 1, 18 };
			zbForm.setDataList(this.translate2Page(QyqssdsUtil2014.putSpace(table,arrs)));

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}

		return zbForm;
	}

	/**
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构 页面数据结构-->接口数据结构
	 * 
	 * @param QyqssdsFb3Form2014
	 * @return 企业所得税清算报表申明对象
	 */
	private QyqssdsReportsDeclare translate2Interface(QyqssdsZbForm2014 form) {

		// 企业所得税报表申明对象
		QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();
		// 将form中的基本信息转入QyqssdsReportsDeclare对象中
		QyqssdsUtil2014.setQyqssdsReport(report, form);
		// 创建企业所得税报表内单表申明对象，并置入基本信息
		QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
		table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_ZB);
		table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_ZB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);

		// 把页面数据翻译成数据库接口的数据格式
		List list = form.getDataList();
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			QyqssdsReportsItemDeclare item = new QyqssdsReportsItemDeclare();
			item.setItemID((String) map.get("hc"));
			item.setItemValue((String) map.get("ljje"));
			item.setItemType("11");
			table.getCellContentList().put(item.getItemID(), item);
		}
		report.getTableContentList().put(table.getTableId(),QyqssdsUtil2014.cleanSpace(table));
		return report;
	}

	/**
	 * 将接口数据结构中的数据转换置入页面要求的数据结构 接口数据结构-->页面数据结构
	 * 
	 * @param QyqssdsReportsTableDeclare
	 * @return 页面表单数据的List对象
	 */
	private List translate2Page(QyqssdsReportsTableDeclare table) {
		// 创建List对象，用来存放页面表单数据
		ArrayList pagelist = new ArrayList();
		int i = 0;

		Iterator it = table.getCellContentList().keySet().iterator();
		Debug.out("----start---2014版 企业所得税清算 年度申报主表----translate2Page");
		while (it.hasNext()) {
			QyqssdsReportsItemDeclare item = new QyqssdsReportsItemDeclare();
			String key = (String) it.next();
			item = (QyqssdsReportsItemDeclare) table.getCellContentList()
					.get(key);
			HashMap map = new HashMap();
			if(item.getItemID().equals("12")){
				map.put("hc", item.getItemID());
				map.put("ljje", "25");
			}else{
				map.put("hc", item.getItemID());
				map.put("ljje", item.getItemValue());
			}
			pagelist.add(map);
			Debug.out("行次："+item.getItemID()+"，域值："+item.getItemValue());
		}
		Debug.out("----over---2014版 企业所得税清算 年度申报主表----translate2Page");
		return pagelist;
	}
}
