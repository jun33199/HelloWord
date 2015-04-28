/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，是一安信科技有限公司，版权所有. </p>
 * <p>Company: 四一安信科技有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.gkfzcmxb.processor;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.gkfzcmxb.web.GkfzcmxbForm;
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

public class GkfzcmxbProcessor implements Processor 
{
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
		
		GkfzcmxbForm gkfzcmxbForm = (GkfzcmxbForm) vo.getData();

		Connection conn = null;

		try {
			//获取数据库连接
			conn = SfDBResource.getConnection();

			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsNewUtil.setQysdsReport(report, gkfzcmxbForm);

			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_GKFZCMXB);
			table.setTableName(CodeConstant.TABLE_NAME_GKFZCMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(), table);
			
			// 获取数据库接口，调用querySingleTable方法进行数据保存
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_GKFZCMXB);
			int []array = {1,13};
			gkfzcmxbForm.setDataList(this.translate2Page(QysdsNewUtil.putSpace(table,array)));
		} catch (Exception ex) {
			//抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return gkfzcmxbForm;
	}
	

	
	/**
	 * doQuery 用于返回页面索要查询的详尽信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
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
		
		GkfzcmxbForm gkfzcmxbForm = (GkfzcmxbForm) vo.getData();

		Connection conn = null;
		
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();
			
			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(gkfzcmxbForm);
						
			// 获取数据库接口，调用save方法进行数据保存
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
		return gkfzcmxbForm;
	}
	/**
	 * doCheck  用于校验页面数据
	 * @param   vo 业务参数
	 * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	private Object doCheck(VOPackage vo) throws BaseException {
		
		GkfzcmxbForm gkfzcmxbForm = (GkfzcmxbForm) vo.getData();
		
		Connection conn = null;
		
		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			
			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(gkfzcmxbForm);
			
			 //获取校验接口
			Checker checker=CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			
			//进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
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
			gkfzcmxbForm.setCheckList(listSingle);
			
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		return gkfzcmxbForm;
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

		GkfzcmxbForm gkfzcmxbForm = (GkfzcmxbForm) vo.getData();
		Connection conn = null;
		
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();
			
			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(gkfzcmxbForm);

			// 获取数据库接口，调用delete方法进行数据删除
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report);
			
			iApp.updateCheckStatus(report,"");
	
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_GKFZCMXB);
			table.setTableId(CodeConstant.TABLE_NAME_GKFZCMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			table=(QysdsReportsTableDeclare)report.getTableContentList().get(CodeConstant.TABLE_ID_GKFZCMXB);
			
			int []array ={1,13};
			gkfzcmxbForm.setDataList(this.translate2Page(QysdsNewUtil.putSpace(table,array)));
			
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return gkfzcmxbForm;
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
		
		
		
		return null;
	}
	
	/**
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构 页面数据结构-->接口数据结构
	 * 
	 * @param GkfzcmxbForm
	 * @return 企业所得税报表申明对象
	 */
	private QysdsReportsDeclare translate2Interface(GkfzcmxbForm gkfzcmxbForm) {
		
		// 企业所得税报表申明对象
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		
		QysdsNewUtil.setQysdsReport(report,gkfzcmxbForm);
		
		// 企业所得税报表内单表申明对象
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_GKFZCMXB);
	table.setTableName(CodeConstant.TABLE_NAME_GKFZCMXB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		
		List list = gkfzcmxbForm.getDataList();
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
		
		Map map1 = new HashMap();
		Map map2 = new HashMap();
		Map map3 = new HashMap();
		Map map4 = new HashMap();
		Map map5 = new HashMap();
		Map map6 = new HashMap();
		Map map7 = new HashMap();
		Map map8 = new HashMap();
		Map map9 = new HashMap();
		Map map10 = new HashMap();
		Map map11 = new HashMap();
		Map map12 = new HashMap();
		Map map13 = new HashMap();
		QysdsReportsItemDeclare item1 = (QysdsReportsItemDeclare) map.get("1");
		map1.put("hc", item1.getItemID());
		map1.put("je", item1.getItemValue());
		list.add(map1);
		
		QysdsReportsItemDeclare item2 = (QysdsReportsItemDeclare) map.get("2");
		map2.put("hc", item2.getItemID());
		map2.put("je", item2.getItemValue());
		list.add(map2);
		
		QysdsReportsItemDeclare item3 = (QysdsReportsItemDeclare) map.get("3");
		map3.put("hc", item3.getItemID());
		map3.put("je", item3.getItemValue());
		list.add(map3);
		
		QysdsReportsItemDeclare item4 = (QysdsReportsItemDeclare) map.get("4");
		map4.put("hc", item4.getItemID());
		map4.put("je", item4.getItemValue());
		list.add(map4);
		
		QysdsReportsItemDeclare item5 = (QysdsReportsItemDeclare) map.get("5");
		map5.put("hc", item5.getItemID());
		map5.put("je", item5.getItemValue());
		list.add(map5);
		
		QysdsReportsItemDeclare item6 = (QysdsReportsItemDeclare) map.get("6");
		map6.put("hc", item6.getItemID());
		map6.put("je", item6.getItemValue());
		list.add(map6);
		
		QysdsReportsItemDeclare item7 = (QysdsReportsItemDeclare) map.get("7");
		map7.put("hc", item7.getItemID());
		map7.put("je", item7.getItemValue());
		list.add(map7);
		
		QysdsReportsItemDeclare item8 = (QysdsReportsItemDeclare) map.get("8");
		map8.put("hc", item8.getItemID());
		map8.put("je", item8.getItemValue());
		list.add(map8);
		
		QysdsReportsItemDeclare item9 = (QysdsReportsItemDeclare) map.get("9");
		map9.put("hc", item9.getItemID());
		map9.put("je", item9.getItemValue());
		list.add(map9);
		
		QysdsReportsItemDeclare item10 = (QysdsReportsItemDeclare) map
		.get("10");
		map10.put("hc", item10.getItemID());
		map10.put("je", item10.getItemValue());
		list.add(map10);
		
		QysdsReportsItemDeclare item11 = (QysdsReportsItemDeclare) map
		.get("11");
		map11.put("hc", item11.getItemID());
		map11.put("je", item11.getItemValue());
		list.add(map11);
		
		QysdsReportsItemDeclare item12 = (QysdsReportsItemDeclare) map
		.get("12");
		map12.put("hc", item12.getItemID());
		map12.put("je", item12.getItemValue());
		list.add(map12);
		
		QysdsReportsItemDeclare item13 = (QysdsReportsItemDeclare) map
		.get("13");
		map13.put("hc", item13.getItemID());
		map13.put("je", item13.getItemValue());
		list.add(map13);		
		return list;
	}
}
