/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.cbmxbybqy.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.cbmxbybqy.web.CbmxbybqyForm2008;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.util.QysdsUtil2008;
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
 * Description:附表二（１）成本费用明细表
 * </p>
 * 
 * @author liwenhua
 * @version 1.1
 */

public class CbmxbybqyProcessor2008 implements Processor {

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
		// 得到Action传递过来CzqysdsjbForm对象
		CbmxbybqyForm2008 cbmxbybqyForm = (CbmxbybqyForm2008) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();

			// 创建QysdsReportsDeclare对象
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			// 将form中的基本信息转入QysdsReportsDeclare report 中
			QysdsUtil2008.setQysdsReport(report, cbmxbybqyForm);
			// 设置QysdsReportsTableDeclare的基本信息
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2008_2_1);
			table.setTableName(CodeConstant.TABLE_NAME_2008_2_1);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			// 将QysdsReportsTableDeclare的基本信息置入QysdsReportsDeclare
			report.getTableContentList().put(table.getTableId(), table);
			report.setVersion("20080101");

			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 调用查询方法进行查询
			iApp.querySingleTable(report);
			// 获取查询到的具体数据
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_2008_2_1);
			// 将数据库中的数据翻译成页面所需数据格式
			int[] arrs = { 1, 28 };
			cbmxbybqyForm.setDataList(this.translate2Page(QysdsUtil2008
					.putSpace(table, arrs)));

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 查询成功返回czqysdsjbForm
		return cbmxbybqyForm;
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
		// 得到Action传递过来CzqysdsjbForm对象
		CbmxbybqyForm2008 cbmxbybqyForm = (CbmxbybqyForm2008) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 将ActionForm中的数据结构转换，置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this
					.translate2Interface(cbmxbybqyForm);
			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 调用saveSingleTable方法进行数据保存
			iApp.saveSingleTable(report);
			//更新审核状态为“保存成功”
			iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 保存成功返回cbmxbybqyForm
		return cbmxbybqyForm;
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
		// 得到Action传递过来CzqysdsjbForm对象
		CbmxbybqyForm2008 cbmxbybqyForm = (CbmxbybqyForm2008) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();

			// 将ActionForm中的数据结构转换,置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this
					.translate2Interface(cbmxbybqyForm);
			// 获取校验接口
			Checker checker = CheckerFactory.getAInstance(conn,
					CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List listSingle = checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			cbmxbybqyForm.setCheckList(listSingle);
			/*如果校验通过，调用接口保存数据*/
			if(listSingle==null ||( listSingle!=null && listSingle.size()==0))
			{
				iApp.saveSingleTable(report);
				//更新审核状态为“单表审核通过”
				iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SINGLE_PASS);
			}else if(listSingle.size()>0){
				//单表审核未通过
				iApp.updateCheckStatus(report,"");
			}

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		return cbmxbybqyForm;
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

		CbmxbybqyForm2008 cbmxbybqyForm = (CbmxbybqyForm2008) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 将ActionForm中的数据结构转换，置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this
					.translate2Interface(cbmxbybqyForm);
			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 调用saveSingleTable方法进行数据删除
			iApp.deleteSingleTable(report);
			iApp.updateCheckStatus(report,"");

			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_CBMXBYBQY);
			table.setTableName(CodeConstant.TABLE_NAME_CBMXBYBQY);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_CBMXBYBQY);
			// 取list
			int[] arrs = { 1, 28 };
			cbmxbybqyForm.setDataList(this.translate2Page(QysdsUtil2008
					.putSpace(table, arrs)));

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}

		return cbmxbybqyForm;
	}

	/**
	 * 将ActionForm中的数据结构转换，置入数据库接口参数要求的数据结构 页面数据结构-->接口数据结构
	 * 
	 * @param cbmxbybqyForm
	 * @return 企业所得税报表申明对象
	 */
	private QysdsReportsDeclare translate2Interface(CbmxbybqyForm2008 form) {

		// 企业所得税报表申明对象
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		// 将form中的基本信息转入QysdsReportsDeclare对象中
		QysdsUtil2008.setQysdsReport(report, form);
		report.setVersion("20080101");
		// 创建企业所得税报表内单表申明对象，并置入基本信息
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_2008_2_1);
		table.setTableName(CodeConstant.TABLE_NAME_2008_2_1);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);

		// 把页面数据翻译成数据库接口的数据格式
		List list = form.getDataList();
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
			item.setItemID((String) map.get("hc"));
			item.setItemValue((String) map.get("je"));
			item.setItemType("11");
			table.getCellContentList().put(item.getItemID(), item);
		}

		report.getTableContentList().put(table.getTableId(),
				QysdsUtil2008.cleanSpace(table));

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
		ArrayList pagelist = new ArrayList();
		int i = 0;

		Iterator it = table.getCellContentList().keySet().iterator();
		while (it.hasNext()) {
			QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
			String key = (String) it.next();
			item = (QysdsReportsItemDeclare) table.getCellContentList()
					.get(key);
			HashMap map = new HashMap();
			map.put("hc", item.getItemID());
			map.put("je", item.getItemValue());
			pagelist.add(map);
			System.out.println(i++);
		}

		return pagelist;
	}

}
