/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信科技有限公司，版权所有. </p>
 * <p>Company: 四一安信科技有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.mssdmxb.processor;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.mssdmxb.web.MssdmxbForm;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报</p>
 * @author cao gang
 * @version 1.1
 */
public class MssdmxbProcessor
implements Processor {
	
	/**
	 * 实现Processor接口
	 * @param vo 业务参数
	 * @return Object VOPackage型数据
	 * @throws BaseException 业务异常
	 * 		1 当传过来的操作类型不对时抛出
	 * 		2 当调用的业务方法抛出业务异常时向上传递抛出
	 * 	其他异常抛出由EJB的process方法处理。
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
				throw new ApplicationException(
				"用户执行了系统不支持的方法或功能.");
		}
		
		return result;
	}
	
	/**
	 * doShow初始化对象页面信息要素
	 * @param vo 业务参数
	 * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	private Object doShow(VOPackage vo) throws BaseException {
		
		MssdmxbForm mssdmxbForm = (MssdmxbForm) vo.getData();
		
		
		Connection conn = null;
		
		try {
			//获取数据库连接
			conn = SfDBResource.getConnection();
			
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsNewUtil.setQysdsReport(report, mssdmxbForm);
			
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_MSSDMXB);
			table.setTableName(CodeConstant.TABLE_NAME_MSSDMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(), table);
			
			// 获取数据库接口，调用
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_MSSDMXB);
			int []array={1,55};
			mssdmxbForm.setDataList(this.translate2Page(QysdsNewUtil.putSpace(table,array)));
		} catch (Exception ex) {
			//抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		
		return mssdmxbForm;
	}
	
	/**
	 * doQuery    用于返回页面索要查询的详尽信息
	 * @param     vo 业务参数
	 * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException    当其他情况都会抛出异常信息
	 *
	 */
	private Object doQuery(VOPackage vo) throws BaseException {
		
		return null;
	}
	
	/**
	 * doSave   用于存储页面提交的详尽处理信息
	 * @param   vo 业务参数
	 * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	private Object doSave(VOPackage vo) throws BaseException {
		
		MssdmxbForm mssdmxbForm = (MssdmxbForm) vo.getData();
		
		Connection conn = null;
		
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();
			
			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(mssdmxbForm);
			
			// 获取数据库接口，调用saveSingleTable方法进行数据保存
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
			//更新审核状态为“保存成功”
			iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return mssdmxbForm;
	}
	
	/**
	 * doCheck   用于存储页面提交的详尽处理信息
	 * @param   vo 业务参数
	 * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	private Object doCheck(VOPackage vo) throws BaseException 
	{
		MssdmxbForm mssdmxbForm = (MssdmxbForm) vo.getData();
		Connection conn = null;
		try {
			//创建数据库连接
			conn = SfDBResource.getConnection();
			
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(mssdmxbForm);
			
			//获取校验接口
			Checker checker=CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			//进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			mssdmxbForm.setCheckList(listSingle);
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
		}catch (Exception ex) { 
			//抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			//释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		return mssdmxbForm;
	}
	
	/**
	 * doDelete  用于删除页面提交的详尽处理信息
	 * @param    vo 业务参数
	 * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	private Object doDelete(VOPackage vo) throws BaseException {
		
		MssdmxbForm mssdmxbForm = (MssdmxbForm) vo.getData();
		Connection conn = null;
		
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();
			
			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(mssdmxbForm);
			
			// 获取数据库接口，调用delete方法进行数据删除
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report);
			
			iApp.updateCheckStatus(report,"");
			
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_MSSDMXB);
			table.setTableId(CodeConstant.TABLE_NAME_MSSDMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			table=(QysdsReportsTableDeclare)report.getTableContentList().get(CodeConstant.TABLE_ID_MSSDMXB);
			int []array ={1,55};
			mssdmxbForm.setDataList(this.translate2Page(QysdsNewUtil.putSpace(table,array)));
			
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return mssdmxbForm;
	}
	
	/**
	 * doUpdate  用于存储页面提交的详尽处理信息
	 * @param    vo 业务参数
	 * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	private Object doUpdate(VOPackage vo) throws BaseException {
		
		return null;
	}
	
	
	/**
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构 页面数据结构-->接口数据结构
	 * 
	 * @param MssdmxbForm
	 * @return 企业所得税报表申明对象
	 */
	private QysdsReportsDeclare translate2Interface(MssdmxbForm mssdmxbForm) {
		
		// 企业所得税报表申明对象
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		
		QysdsNewUtil.setQysdsReport(report,mssdmxbForm);
		
		// 企业所得税报表内单表申明对象
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_MSSDMXB);
		table.setTableName(CodeConstant.TABLE_NAME_MSSDMXB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		//翻译
		List list = mssdmxbForm.getDataList();
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			
			String hc = (String) map.get("hc");
			if ("1".equals(hc)) {
				QysdsReportsItemDeclare item_1_1 = new QysdsReportsItemDeclare();
				item_1_1.setItemID("1");
				item_1_1.setItemValue((String) map.get("je"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(), item_1_1);
			}
			if ("2".equals(hc)) {
				QysdsReportsItemDeclare item_2_1 = new QysdsReportsItemDeclare();
				item_2_1.setItemID("2");
				item_2_1.setItemValue((String) map.get("je"));
				item_2_1.setItemType("11");
				table.getCellContentList().put(item_2_1.getItemID(), item_2_1);
			}
			if ("3".equals(hc)) {
				QysdsReportsItemDeclare item_3_1 = new QysdsReportsItemDeclare();
				item_3_1.setItemID("3");
				item_3_1.setItemValue((String) map.get("je"));
				item_3_1.setItemType("11");
				table.getCellContentList().put(item_3_1.getItemID(), item_3_1);
			}
			if ("4".equals(hc)) {
				QysdsReportsItemDeclare item_4_1 = new QysdsReportsItemDeclare();
				item_4_1.setItemID("4");
				item_4_1.setItemValue((String) map.get("je"));
				item_4_1.setItemType("11");
				table.getCellContentList().put(item_4_1.getItemID(), item_4_1);
			}
			if ("5".equals(hc)) {
				QysdsReportsItemDeclare item_5_1 = new QysdsReportsItemDeclare();
				item_5_1.setItemID("5");
				item_5_1.setItemValue((String) map.get("je"));
				item_5_1.setItemType("11");
				table.getCellContentList().put(item_5_1.getItemID(), item_5_1);
			}
			if ("6".equals(hc)) {
				QysdsReportsItemDeclare item_6_1 = new QysdsReportsItemDeclare();
				item_6_1.setItemID("6");
				item_6_1.setItemValue((String) map.get("je"));
				item_6_1.setItemType("11");
				table.getCellContentList().put(item_6_1.getItemID(), item_6_1);
			}
			if ("6".equals(hc)) {
				QysdsReportsItemDeclare item_6_1 = new QysdsReportsItemDeclare();
				item_6_1.setItemID("6");
				item_6_1.setItemValue((String) map.get("je"));
				item_6_1.setItemType("11");
				table.getCellContentList().put(item_6_1.getItemID(), item_6_1);
			}
			if ("7".equals(hc)) {
				QysdsReportsItemDeclare item_7_1 = new QysdsReportsItemDeclare();
				item_7_1.setItemID("7");
				item_7_1.setItemValue((String) map.get("je"));
				item_7_1.setItemType("11");
				table.getCellContentList().put(item_7_1.getItemID(), item_7_1);
			}
			if ("8".equals(hc)) {
				QysdsReportsItemDeclare item_8_1 = new QysdsReportsItemDeclare();
				item_8_1.setItemID("8");
				item_8_1.setItemValue((String) map.get("je"));
				item_8_1.setItemType("11");
				table.getCellContentList().put(item_8_1.getItemID(), item_8_1);
			}
			if ("9".equals(hc)) {
				QysdsReportsItemDeclare item_9_1 = new QysdsReportsItemDeclare();
				item_9_1.setItemID("9");
				item_9_1.setItemValue((String) map.get("je"));
				item_9_1.setItemType("11");
				table.getCellContentList().put(item_9_1.getItemID(), item_9_1);
			}
			if ("10".equals(hc)) {
				QysdsReportsItemDeclare item_10_1 = new QysdsReportsItemDeclare();
				item_10_1.setItemID("10");
				item_10_1.setItemValue((String) map.get("je"));
				item_10_1.setItemType("11");
				table.getCellContentList()
				.put(item_10_1.getItemID(), item_10_1);
			}
			if ("11".equals(hc)) {
				QysdsReportsItemDeclare item_11_1 = new QysdsReportsItemDeclare();
				item_11_1.setItemID("11");
				item_11_1.setItemValue((String) map.get("je"));
				item_11_1.setItemType("11");
				table.getCellContentList()
				.put(item_11_1.getItemID(), item_11_1);
			}
			if ("12".equals(hc)) {
				QysdsReportsItemDeclare item_12_1 = new QysdsReportsItemDeclare();
				item_12_1.setItemID("12");
				item_12_1.setItemValue((String) map.get("je"));
				item_12_1.setItemType("11");
				table.getCellContentList()
				.put(item_12_1.getItemID(), item_12_1);
			}
			if ("13".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("13");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("14".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("14");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("15".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("15");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("16".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("16");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("18".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("17");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("25".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("18");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("26".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("19");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("27".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("20");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("28".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("21");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("30".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("22");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("31".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("23");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("32".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("24");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("33".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("25");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("34".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("26");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("35".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("27");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("36".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("28");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("37".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("29");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("38".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("30");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("39".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("31");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("40".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("32");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("41".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("33");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("42".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("34");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("43".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("35");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("44".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("36");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("45".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("37");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("46".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("38");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("47".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("39");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("48".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("40");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("49".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("41");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("50".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("42");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("52".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("43");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("53".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("44");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("54".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("45");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("55".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("46");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("56".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("47");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("57".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("48");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("58".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("49");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("59".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("50");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("60".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("51");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("61".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("52");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("62".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("53");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("63".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("54");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}
			if ("64".equals(hc)) {
				QysdsReportsItemDeclare item_13_1 = new QysdsReportsItemDeclare();
				item_13_1.setItemID("55");
				item_13_1.setItemValue((String) map.get("je"));
				item_13_1.setItemType("11");
				table.getCellContentList()
				.put(item_13_1.getItemID(), item_13_1);
			}			
		}
		report.getTableContentList().put(table.getTableId(), QysdsNewUtil.cleanSpace(table));
		return report;
	}
	
	/**
	 * 将接口数据结构中的数据转换置入页面要求的数据结构 接口数据结构-->页面数据结构
	 * 
	 * @param QysdsReportsTableDeclare
	 * @return 企业所得税报表申明对象
	 */
	private List translate2Page(QysdsReportsTableDeclare table) {
		List list = new ArrayList();
		Map map = table.getCellContentList();
		for(int i=1;i<17;i++){
			String hc="".valueOf(i);
			QysdsReportsItemDeclare item1=(QysdsReportsItemDeclare)map.get(hc);
			Map map1 = new HashMap();
			map1.put("hc",hc);
			map1.put("je",item1.getItemValue());
			list.add(map1);
		}
		Map map17 = new HashMap();
		map17.put("hc","17");
		map17.put("je","*");
		list.add(map17);
		
		QysdsReportsItemDeclare item18=(QysdsReportsItemDeclare)map.get("17");
		Map map18 = new HashMap();
		map18.put("hc","18");
		map18.put("je",item18.getItemValue());
		list.add(map18);
		
		Map map19 = new HashMap();
		map19.put("hc","19");
		map19.put("je","*");
		list.add(map19);
		
		Map map20 = new HashMap();
		map20.put("hc","20");
		map20.put("je","*");
		list.add(map20);
		
		Map map21 = new HashMap();
		map21.put("hc","21");
		map21.put("je","*");
		list.add(map21);
		
		Map map22 = new HashMap();
		map22.put("hc","22");
		map22.put("je","*");
		list.add(map22);
		
		Map map23 = new HashMap();
		map23.put("hc","23");
		map23.put("je","*");
		list.add(map23);
		
		Map map24 = new HashMap();
		map24.put("hc","24");
		map24.put("je","*");
		list.add(map24);
		
		QysdsReportsItemDeclare item25=(QysdsReportsItemDeclare)map.get("18");
		Map map25 = new HashMap();
		map25.put("hc","25");
		map25.put("je",item25.getItemValue());
		list.add(map25);
		
		QysdsReportsItemDeclare item26=(QysdsReportsItemDeclare)map.get("19");
		Map map26 = new HashMap();
		map26.put("hc","26");
		map26.put("je",item26.getItemValue());
		list.add(map26);
		
		QysdsReportsItemDeclare item27=(QysdsReportsItemDeclare)map.get("20");
		Map map27 = new HashMap();
		map27.put("hc","27");
		map27.put("je",item27.getItemValue());
		list.add(map27);
		
		QysdsReportsItemDeclare item28=(QysdsReportsItemDeclare)map.get("21");
		Map map28 = new HashMap();
		map28.put("hc","28");
		map28.put("je",item28.getItemValue());
		list.add(map28);
		
		Map map29 = new HashMap();
		map29.put("hc","29");
		map29.put("je","*");
		list.add(map29);
		
		QysdsReportsItemDeclare item30=(QysdsReportsItemDeclare)map.get("22");
		Map map30 = new HashMap();
		map30.put("hc","30");
		map30.put("je",item30.getItemValue());
		list.add(map30);
		
		QysdsReportsItemDeclare item31=(QysdsReportsItemDeclare)map.get("23");
		Map map31 = new HashMap();
		map31.put("hc","31");
		map31.put("je",item31.getItemValue());
		list.add(map31);
		
		
		QysdsReportsItemDeclare item32=(QysdsReportsItemDeclare)map.get("24");
		Map map32 = new HashMap();
		map32.put("hc","32");
		map32.put("je",item32.getItemValue());
		list.add(map32);
		
		QysdsReportsItemDeclare item33=(QysdsReportsItemDeclare)map.get("25");
		Map map33 = new HashMap();
		map33.put("hc","33");
		map33.put("je",item33.getItemValue());
		list.add(map33);
		
		QysdsReportsItemDeclare item34=(QysdsReportsItemDeclare)map.get("26");
		Map map34 = new HashMap();
		map34.put("hc","34");
		map34.put("je",item34.getItemValue());
		list.add(map34);
		
		QysdsReportsItemDeclare item35=(QysdsReportsItemDeclare)map.get("27");
		Map map35 = new HashMap();
		map35.put("hc","35");
		map35.put("je",item35.getItemValue());
		list.add(map35);
		
		QysdsReportsItemDeclare item36=(QysdsReportsItemDeclare)map.get("28");
		Map map36 = new HashMap();
		map36.put("hc","36");
		map36.put("je",item36.getItemValue());
		list.add(map36);
		
		QysdsReportsItemDeclare item37=(QysdsReportsItemDeclare)map.get("29");
		Map map37 = new HashMap();
		map37.put("hc","37");
		map37.put("je",item37.getItemValue());
		list.add(map37);
		
		QysdsReportsItemDeclare item38=(QysdsReportsItemDeclare)map.get("30");
		Map map38 = new HashMap();
		map38.put("hc","38");
		map38.put("je",item38.getItemValue());
		list.add(map38);
		
		QysdsReportsItemDeclare item39=(QysdsReportsItemDeclare)map.get("31");
		Map map39 = new HashMap();
		map39.put("hc","39");
		map39.put("je",item39.getItemValue());
		list.add(map39);
		
		QysdsReportsItemDeclare item40=(QysdsReportsItemDeclare)map.get("32");
		Map map40 = new HashMap();
		map40.put("hc","40");
		map40.put("je",item40.getItemValue());
		list.add(map40);
		
		QysdsReportsItemDeclare item41=(QysdsReportsItemDeclare)map.get("33");
		Map map41 = new HashMap();
		map41.put("hc","41");
		map41.put("je",item41.getItemValue());
		list.add(map41);
		
		QysdsReportsItemDeclare item42=(QysdsReportsItemDeclare)map.get("34");
		Map map42 = new HashMap();
		map42.put("hc","42");
		map42.put("je",item42.getItemValue());
		list.add(map42);
		
		QysdsReportsItemDeclare item43=(QysdsReportsItemDeclare)map.get("35");
		Map map43 = new HashMap();
		map43.put("hc","43");
		map43.put("je",item43.getItemValue());
		list.add(map43);
		
		QysdsReportsItemDeclare item44=(QysdsReportsItemDeclare)map.get("36");
		Map map44 = new HashMap();
		map44.put("hc","44");
		map44.put("je",item44.getItemValue());
		list.add(map44);
		
		QysdsReportsItemDeclare item45=(QysdsReportsItemDeclare)map.get("37");
		Map map45 = new HashMap();
		map45.put("hc","45");
		map45.put("je",item45.getItemValue());
		list.add(map45);
		
		QysdsReportsItemDeclare item46=(QysdsReportsItemDeclare)map.get("38");
		Map map46 = new HashMap();
		map46.put("hc","46");
		map46.put("je",item46.getItemValue());
		list.add(map46);
		
		QysdsReportsItemDeclare item47=(QysdsReportsItemDeclare)map.get("39");
		Map map47 = new HashMap();
		map47.put("hc","47");
		map47.put("je",item47.getItemValue());
		list.add(map47);
		
		QysdsReportsItemDeclare item48=(QysdsReportsItemDeclare)map.get("40");
		Map map48 = new HashMap();
		map48.put("hc","48");
		map48.put("je",item48.getItemValue());
		list.add(map48);
		
		QysdsReportsItemDeclare item49=(QysdsReportsItemDeclare)map.get("41");
		Map map49 = new HashMap();
		map49.put("hc","49");
		map49.put("je",item49.getItemValue());
		list.add(map49);
		
		QysdsReportsItemDeclare item50=(QysdsReportsItemDeclare)map.get("42");
		Map map50 = new HashMap();
		map50.put("hc","50");
		map50.put("je",item50.getItemValue());
		list.add(map50);
		
		Map map51 = new HashMap();
		map51.put("hc","51");
		map51.put("je","*");
		list.add(map51);
		
		Map map53 = new HashMap();
		map53.put("hc","65");
		map53.put("je","*");
		list.add(map53);
		
		for(int i=43;i<56;i++){
			String key="".valueOf(i);
			String hc="".valueOf(i+9);
			QysdsReportsItemDeclare item52=(QysdsReportsItemDeclare)map.get(key);
			Map map52 = new HashMap();
			map52.put("hc",hc);
			map52.put("je",item52.getItemValue());
			list.add(map52);
		}
		return list;
	}
	
}
