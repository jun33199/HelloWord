/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.dzzbjtmxb.processor;
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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.dzzbjtmxb.web.DzzbjtmxbForm;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class DzzbjtmxbProcessor implements Processor 
{
	/**
	 * 实现Processor接口
	 * @param vo 业务参数
	 * @return Object VOPackage型数据
	 * @throws BaseException 业务异常
	 * 		1 当传过来的操作类型不对时抛出
	 * 		2 当调用的业务方法抛出业务异常时向上传递抛出
	 * 	其他异常抛出由EJB的process方法处理。
	 */
	
	public Object process(VOPackage vo) throws BaseException
	{
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
	
	private Object doShow(VOPackage vo) throws BaseException 
	{
		DzzbjtmxbForm dzzbjtmxbForm = (DzzbjtmxbForm) vo.getData();
		Connection conn = null;
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();
			
			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsNewUtil.setQysdsReport(report,dzzbjtmxbForm);
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_DZZBJTMXB);
			table.setTableName(CodeConstant.TABLE_NAME_DZZBJTMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(),table);
			// 获取数据库接口，调用query方法进行数据查找
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			table=(QysdsReportsTableDeclare)report.getTableContentList().get(table.getTableId());
			int []arra={1,31};
			dzzbjtmxbForm.setDataList(this.translate2Page(QysdsNewUtil.putSpace(table,arra)));
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		
		return dzzbjtmxbForm;
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
	
	private Object doSave(VOPackage vo) throws BaseException
	{
		DzzbjtmxbForm form = (DzzbjtmxbForm) vo.getData();
		Connection conn = null;
		try {
			//获取数据库连接
			conn = SfDBResource.getConnection();
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(form);
			//获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
			//更新审核状态为“保存成功”
			iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);
		}catch (Exception ex) { 
			//抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			SfDBResource.freeConnection(conn);
		}
		
		return form;
		
	}
	
	/**
	 * doCheck   用于存储页面提交的详尽处理信息
	 * @param   vo 业务参数
	 * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	private Object doCheck(VOPackage vo) throws BaseException 
	{
		DzzbjtmxbForm dzzbjtmxbForm = (DzzbjtmxbForm) vo.getData();
		Connection conn = null;
		try {
			//创建数据库连接
			conn = SfDBResource.getConnection();
			
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(dzzbjtmxbForm);
			
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
			dzzbjtmxbForm.setCheckList(listSingle);
		}catch (Exception ex) { 
			//抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			//释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		return dzzbjtmxbForm;
	}
	/**
	 * doDelete  用于删除页面提交的详尽处理信息
	 * @param    vo 业务参数
	 * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	
	private Object doDelete(VOPackage vo) throws BaseException
	{
		DzzbjtmxbForm dzzbjtmxbForm = (DzzbjtmxbForm) vo.getData();
		 Connection conn = null;
		try {
			conn = SfDBResource.getConnection();
			
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(dzzbjtmxbForm);
			//获取数据库接口，调用delete方法进行数据删除,iApp返回一个report对象
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report);
			iApp.updateCheckStatus(report,"");
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_DZZBJTMXB);
			table.setTableName(CodeConstant.TABLE_NAME_DZZBJTMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			int []arra={1,31};
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_DZZBJTMXB);
			dzzbjtmxbForm.setDataList(this.translate2Page(QysdsNewUtil.putSpace(table,arra)));
		}catch (Exception ex) { 
			//抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			SfDBResource.freeConnection(conn);
		}
		return dzzbjtmxbForm;
	}
	
	/**
	 * doUpdate  用于存储页面提交的详尽处理信息
	 * @param    vo 业务参数
	 * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	
	private Object doUpdate(VOPackage vo) throws BaseException {
		
		QysdsnbForm nbForm = (QysdsnbForm) vo.getData();
		
		return nbForm;
	}
	
	
	/**
	 * 初始化
	 * @param nbForm 主表数据
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	
	private void initForm(QysdsnbForm nbForm) 
	throws BaseException
	{
		
	}
	/**
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
	 * 页面数据结构-->接口数据结构
	 * @param DzzbjtmxbForm 
	 * @return 企业所得税报表申明对象
	 */
	private QysdsReportsDeclare translate2Interface(DzzbjtmxbForm form){
		//企业所得税报表申明对象
		QysdsReportsDeclare report =new QysdsReportsDeclare();
		QysdsNewUtil.setQysdsReport(report,form);
		//企业所得税报表内单表申明对象
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_DZZBJTMXB);
		table.setTableName(CodeConstant.TABLE_NAME_DZZBJTMXB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		List list=form.getDataList();
		for(int i=0;i<list.size();i++){
			HashMap map=(HashMap)list.get(i);
			String hc=(String)map.get("hc");
			if("1".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("1");
				item_1_1.setItemValue((String)map.get("je1"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
			}	
			if("2".equals(hc)){
				QysdsReportsItemDeclare item_2_1=new QysdsReportsItemDeclare();
				item_2_1.setItemID("2");
				item_2_1.setItemValue((String)map.get("je1"));
				item_2_1.setItemType("11");
				table.getCellContentList().put(item_2_1.getItemID(),item_2_1);
			}
			if("3".equals(hc)){
				QysdsReportsItemDeclare item_3_1=new QysdsReportsItemDeclare();
				item_3_1.setItemID("3");
				item_3_1.setItemValue((String)map.get("je1"));
				item_3_1.setItemType("11");
				table.getCellContentList().put(item_3_1.getItemID(),item_3_1);
			}
			if("4".equals(hc)){
				QysdsReportsItemDeclare item_4_1=new QysdsReportsItemDeclare();
				item_4_1.setItemID("4");
				item_4_1.setItemValue((String)map.get("je1"));
				item_4_1.setItemType("11");
				table.getCellContentList().put(item_4_1.getItemID(),item_4_1);
			}
			if("5".equals(hc)){
				QysdsReportsItemDeclare item_5_1=new QysdsReportsItemDeclare();
				item_5_1.setItemID("5");
				item_5_1.setItemValue((String)map.get("je1"));
				item_5_1.setItemType("11");
				table.getCellContentList().put(item_5_1.getItemID(),item_5_1);
			}
			if("6".equals(hc)){
				QysdsReportsItemDeclare item_6_1=new QysdsReportsItemDeclare();
				item_6_1.setItemID("6");
				item_6_1.setItemValue((String)map.get("je1"));
				item_6_1.setItemType("11");
				table.getCellContentList().put(item_6_1.getItemID(),item_6_1);
			}
			if("7".equals(hc)){
				QysdsReportsItemDeclare item_7_1=new QysdsReportsItemDeclare();
				item_7_1.setItemID("7");
				item_7_1.setItemValue((String)map.get("je1"));
				item_7_1.setItemType("11");
				table.getCellContentList().put(item_7_1.getItemID(),item_7_1);
			}
			if("8".equals(hc)){
				QysdsReportsItemDeclare item_8_1=new QysdsReportsItemDeclare();
				item_8_1.setItemID("8");
				item_8_1.setItemValue((String)map.get("je1"));
				item_8_1.setItemType("11");
				table.getCellContentList().put(item_8_1.getItemID(),item_8_1);
			}
			if("9".equals(hc)){
				QysdsReportsItemDeclare item_9_1=new QysdsReportsItemDeclare();
				item_9_1.setItemID("9");
				item_9_1.setItemValue((String)map.get("je1"));
				item_9_1.setItemType("11");
				table.getCellContentList().put(item_9_1.getItemID(),item_9_1);
			}
			if("10".equals(hc)){
				QysdsReportsItemDeclare item_10_1=new QysdsReportsItemDeclare();
				item_10_1.setItemID("10");
				item_10_1.setItemValue((String)map.get("je1"));
				item_10_1.setItemType("11");
				table.getCellContentList().put(item_10_1.getItemID(),item_10_1);
			}
			if("11".equals(hc)){
				QysdsReportsItemDeclare item_11_1=new QysdsReportsItemDeclare();
				item_11_1.setItemID("11");
				item_11_1.setItemValue((String)map.get("je1"));
				item_11_1.setItemType("11");
				table.getCellContentList().put(item_11_1.getItemID(),item_11_1);
			}
			if("12".equals(hc)){
				QysdsReportsItemDeclare item_12_1=new QysdsReportsItemDeclare();
				item_12_1.setItemID("12");
				item_12_1.setItemValue((String)map.get("je1"));
				item_12_1.setItemType("11");
				table.getCellContentList().put(item_12_1.getItemID(),item_12_1);
				
				QysdsReportsItemDeclare item_12_2=new QysdsReportsItemDeclare();
				item_12_2.setItemID("13");
				item_12_2.setItemValue((String)map.get("je2"));
				item_12_2.setItemType("11");
				table.getCellContentList().put(item_12_2.getItemID(),item_12_2);
			}
			if("13".equals(hc)){
				QysdsReportsItemDeclare item_13_1=new QysdsReportsItemDeclare();
				item_13_1.setItemID("14");
				item_13_1.setItemValue((String)map.get("je1"));
				item_13_1.setItemType("11");
				table.getCellContentList().put(item_13_1.getItemID(),item_13_1);
				
				QysdsReportsItemDeclare item_13_2=new QysdsReportsItemDeclare();
				item_13_2.setItemID("15");
				item_13_2.setItemValue((String)map.get("je2"));
				item_13_2.setItemType("11");
				table.getCellContentList().put(item_13_2.getItemID(),item_13_2);
			}
			if("14".equals(hc)){
				QysdsReportsItemDeclare item_14_1=new QysdsReportsItemDeclare();
				item_14_1.setItemID("16");
				item_14_1.setItemValue((String)map.get("je1"));
				item_14_1.setItemType("11");
				table.getCellContentList().put(item_14_1.getItemID(),item_14_1);
				
				QysdsReportsItemDeclare item_14_2=new QysdsReportsItemDeclare();
				item_14_2.setItemID("17");
				item_14_2.setItemValue((String)map.get("je2"));
				item_14_2.setItemType("11");
				table.getCellContentList().put(item_14_2.getItemID(),item_14_2);
			}
			if("15".equals(hc)){
				QysdsReportsItemDeclare item_15_1=new QysdsReportsItemDeclare();
				item_15_1.setItemID("18");
				item_15_1.setItemValue((String)map.get("je1"));
				item_15_1.setItemType("11");
				table.getCellContentList().put(item_15_1.getItemID(),item_15_1);
				
				QysdsReportsItemDeclare item_15_2=new QysdsReportsItemDeclare();
				item_15_2.setItemID("19");
				item_15_2.setItemValue((String)map.get("je2"));
				item_15_2.setItemType("11");
				table.getCellContentList().put(item_15_2.getItemID(),item_15_2);
			}
			if("16".equals(hc)){
				QysdsReportsItemDeclare item_16_1=new QysdsReportsItemDeclare();
				item_16_1.setItemID("20");
				item_16_1.setItemValue((String)map.get("je1"));
				item_16_1.setItemType("11");
				table.getCellContentList().put(item_16_1.getItemID(),item_16_1);
				
				QysdsReportsItemDeclare item_16_2=new QysdsReportsItemDeclare();
				item_16_2.setItemID("21");
				item_16_2.setItemValue((String)map.get("je2"));
				item_16_2.setItemType("11");
				table.getCellContentList().put(item_16_2.getItemID(),item_16_2);
			}
			if("17".equals(hc)){
				QysdsReportsItemDeclare item_17_1=new QysdsReportsItemDeclare();
				item_17_1.setItemID("22");
				item_17_1.setItemValue((String)map.get("je1"));
				item_17_1.setItemType("11");
				table.getCellContentList().put(item_17_1.getItemID(),item_17_1);
				
				QysdsReportsItemDeclare item_17_2=new QysdsReportsItemDeclare();
				item_17_2.setItemID("23");
				item_17_2.setItemValue((String)map.get("je2"));
				item_17_2.setItemType("11");
				table.getCellContentList().put(item_17_2.getItemID(),item_17_2);
			}
			if("18".equals(hc)){
				QysdsReportsItemDeclare item_18_1=new QysdsReportsItemDeclare();
				item_18_1.setItemID("24");
				item_18_1.setItemValue((String)map.get("je1"));
				item_18_1.setItemType("11");
				table.getCellContentList().put(item_18_1.getItemID(),item_18_1);
				
				QysdsReportsItemDeclare item_18_2=new QysdsReportsItemDeclare();
				item_18_2.setItemID("25");
				item_18_2.setItemValue((String)map.get("je2"));
				item_18_2.setItemType("11");
				table.getCellContentList().put(item_18_2.getItemID(),item_18_2);
			}
			if("19".equals(hc)){
				QysdsReportsItemDeclare item_19_1=new QysdsReportsItemDeclare();
				item_19_1.setItemID("26");
				item_19_1.setItemValue((String)map.get("je1"));
				item_19_1.setItemType("11");
				table.getCellContentList().put(item_19_1.getItemID(),item_19_1);
				
				QysdsReportsItemDeclare item_19_2=new QysdsReportsItemDeclare();
				item_19_2.setItemID("27");
				item_19_2.setItemValue((String)map.get("je2"));
				item_19_2.setItemType("11");
				table.getCellContentList().put(item_19_2.getItemID(),item_19_2);
			}
			if("20".equals(hc)){
				QysdsReportsItemDeclare item_20_1=new QysdsReportsItemDeclare();
				item_20_1.setItemID("28");
				item_20_1.setItemValue((String)map.get("je1"));
				item_20_1.setItemType("11");
				table.getCellContentList().put(item_20_1.getItemID(),item_20_1);
				
				QysdsReportsItemDeclare item_20_2=new QysdsReportsItemDeclare();
				item_20_2.setItemID("29");
				item_20_2.setItemValue((String)map.get("je2"));
				item_20_2.setItemType("11");
				table.getCellContentList().put(item_20_2.getItemID(),item_20_2);
			}
			if("21".equals(hc)){
				QysdsReportsItemDeclare item_21_1=new QysdsReportsItemDeclare();
				item_21_1.setItemID("30");
				item_21_1.setItemValue((String)map.get("je1"));
				item_21_1.setItemType("11");
				table.getCellContentList().put(item_21_1.getItemID(),item_21_1);
				
				QysdsReportsItemDeclare item_21_2=new QysdsReportsItemDeclare();
				item_21_2.setItemID("31");
				item_21_2.setItemValue((String)map.get("je2"));
				item_21_2.setItemType("11");
				table.getCellContentList().put(item_21_2.getItemID(),item_21_2);
			}
		}
		report.getTableContentList().put(table.getTableId(),QysdsNewUtil.cleanSpace(table));
		return report;
	}
	/**
	 * 将接口数据结构中的数据转换置入页面要求的数据结构
	 * 接口数据结构-->页面数据结构
	 * @param QysdsReportsTableDeclare
	 * @return 企业所得税报表申明对象
	 */
	private List translate2Page(QysdsReportsTableDeclare table){
		List list=new ArrayList();
		Map map=table.getCellContentList();
		Map map2=new HashMap();
		Map map3=new HashMap();
		Map map4=new HashMap();
		Map map5=new HashMap();
		Map map6=new HashMap();
		Map map7=new HashMap();
		Map map8=new HashMap();
		Map map9=new HashMap();
		Map map10=new HashMap();
		Map map11=new HashMap();
		for(int i=1;i<12;i++)
		{
			Map map1=new HashMap();
			map1.put("hc",i+"");
			QysdsReportsItemDeclare je=(QysdsReportsItemDeclare)map.get(i+"");
			System.out.println(je.getItemValue());
			String jeje=je.getItemValue();
			map1.put("je1",jeje);
			map1.put("je2"," ");
			list.add(map1);
		}
		map2.put("hc","12");
		QysdsReportsItemDeclare je1_12=(QysdsReportsItemDeclare)map.get("12");
		String je1_12_1=je1_12.getItemValue();
		map2.put("je1",je1_12_1);
		QysdsReportsItemDeclare je2_12=(QysdsReportsItemDeclare)map.get("13");
		String je2_12_2=je2_12.getItemValue();
		map2.put("je2",je2_12_2);
		list.add(map2);
		
		map3.put("hc","13");
		QysdsReportsItemDeclare je1_13=(QysdsReportsItemDeclare)map.get("14");
		String je1_13_1=je1_13.getItemValue();
		map3.put("je1",je1_13_1);
		QysdsReportsItemDeclare je2_13=(QysdsReportsItemDeclare)map.get("15");
		String je2_13_2=je2_13.getItemValue();
		map3.put("je2",je2_13_2);
		list.add(map3);
		
		map4.put("hc","14");
		QysdsReportsItemDeclare je1_14=(QysdsReportsItemDeclare)map.get("16");
		String je1_14_1=je1_14.getItemValue();
		map4.put("je1",je1_14_1);
		QysdsReportsItemDeclare je2_14=(QysdsReportsItemDeclare)map.get("17");
		String je2_14_2=je2_14.getItemValue();
		map4.put("je2",je2_14_2);
		list.add(map4);
		
		map5.put("hc","15");
		QysdsReportsItemDeclare je1_15=(QysdsReportsItemDeclare)map.get("18");
		String je1_15_1=je1_15.getItemValue();
		map5.put("je1",je1_15_1);
		QysdsReportsItemDeclare je2_15=(QysdsReportsItemDeclare)map.get("19");
		String je2_15_2=je2_15.getItemValue();
		map5.put("je2",je2_15_2);
		list.add(map5);
		
		map6.put("hc","16");
		QysdsReportsItemDeclare je1_16=(QysdsReportsItemDeclare)map.get("20");
		String je1_16_1=je1_16.getItemValue();
		map6.put("je1",je1_16_1);
		QysdsReportsItemDeclare je2_16=(QysdsReportsItemDeclare)map.get("21");
		String je2_16_2=je2_16.getItemValue();
		map6.put("je2",je2_16_2);
		list.add(map6);
		
		map7.put("hc","17");
		QysdsReportsItemDeclare je1_17=(QysdsReportsItemDeclare)map.get("22");
		String je1_17_1=je1_17.getItemValue();
		map7.put("je1",je1_17_1);
		QysdsReportsItemDeclare je2_17=(QysdsReportsItemDeclare)map.get("23");
		String je2_17_2=je2_17.getItemValue();
		map7.put("je2",je2_17_2);
		list.add(map7);
		
		map8.put("hc","18");
		QysdsReportsItemDeclare je1_18=(QysdsReportsItemDeclare)map.get("24");
		String je1_18_1=je1_18.getItemValue();
		map8.put("je1",je1_18_1);
		QysdsReportsItemDeclare je2_18=(QysdsReportsItemDeclare)map.get("25");
		String je2_18_2=je2_18.getItemValue();
		map8.put("je2",je2_18_2);
		list.add(map8);
		
		map9.put("hc","19");
		QysdsReportsItemDeclare je1_19=(QysdsReportsItemDeclare)map.get("26");
		String je1_19_1=je1_19.getItemValue();
		map9.put("je1",je1_19_1);
		QysdsReportsItemDeclare je2_19=(QysdsReportsItemDeclare)map.get("27");
		String je2_19_2=je2_19.getItemValue();
		map9.put("je2",je2_19_2);
		list.add(map9);
		
		map10.put("hc","20");
		QysdsReportsItemDeclare je1_20=(QysdsReportsItemDeclare)map.get("28");
		String je1_20_1=je1_20.getItemValue();
		map10.put("je1",je1_20_1);
		QysdsReportsItemDeclare je2_20=(QysdsReportsItemDeclare)map.get("29");
		String je2_20_2=je2_20.getItemValue();
		map10.put("je2",je2_20_2);
		list.add(map10);
		
		map11.put("hc","21");
		QysdsReportsItemDeclare je1_21=(QysdsReportsItemDeclare)map.get("30");
		String je1_21_1=je1_21.getItemValue();
		map11.put("je1",je1_21_1);
		QysdsReportsItemDeclare je2_21=(QysdsReportsItemDeclare)map.get("31");
		String je2_21_2=je2_21.getItemValue();
		map11.put("je2",je2_21_2);
		list.add(map11);

		for(int h=0;h<list.size();h++)
		{
			HashMap mapcs=(HashMap)list.get(h);
			String hc=(String)mapcs.get("hc");
			String je11=(String)mapcs.get("je1");
			String je21=(String)mapcs.get("je2");
			System.out.println("hc-"+hc+"je11-"+je11 +"   je2-"+je21);
		}
	return list;
		
	}
	
}
