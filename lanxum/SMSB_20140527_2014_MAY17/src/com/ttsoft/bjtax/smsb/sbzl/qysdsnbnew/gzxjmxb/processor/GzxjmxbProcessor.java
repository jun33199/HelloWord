/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.gzxjmxb.processor;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.gzxjmxb.web.GzxjmxbForm;
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

public class GzxjmxbProcessor implements Processor 
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
		GzxjmxbForm gzxjmxbForm = (GzxjmxbForm) vo.getData();
		Connection conn = null;
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();
			
			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			System.out.println(gzxjmxbForm.getGzlx());
			
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			if(gzxjmxbForm.getGzlx().equals(CodeConstant.SMSB_GZGLLX_FGX))
			{
				QysdsNewUtil.setQysdsReport(report,gzxjmxbForm);
				table.setTableId(CodeConstant.TABLE_ID_GZXJMXB_FGX);
				table.setTableName(CodeConstant.TABLE_NAME_GZXJMXB_FGX);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
				report.getTableContentList().put(table.getTableId(),table);
				// 获取数据库接口，调用query方法进行数据查找
				IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
				iApp.querySingleTable(report);
				table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_GZXJMXB_FGX);
				
				//工资管理形式
				if(table.getCellContentList().get("1")!=null){
					QysdsReportsItemDeclare item=(QysdsReportsItemDeclare)table.getCellContentList().get("1");
					gzxjmxbForm.setGzglxs_gzxj(item.getItemValue());
				}
								
				int []arra1={2,40};
				gzxjmxbForm.setDataList(this.FGXtranslate2Page(QysdsNewUtil.putSpace(table,arra1)));
			}
			else
			{
				QysdsNewUtil.setQysdsReport(report,gzxjmxbForm);
				table.setTableId(CodeConstant.TABLE_ID_TABLE_ID_GZXJMXB_GX);
				table.setTableName(CodeConstant.TABLE_NAME_GZXJMXB_GX);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
				report.getTableContentList().put(table.getTableId(),table);
				IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
				iApp.querySingleTable(report);
				table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_TABLE_ID_GZXJMXB_GX);
				int []arra2={1,47};
				gzxjmxbForm.setDataList(this.GXtranslate2Page(QysdsNewUtil.putSpace(table,arra2)));
			}
			
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return gzxjmxbForm;
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
		GzxjmxbForm form = (GzxjmxbForm) vo.getData();
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
		GzxjmxbForm gzxjmxbForm = (GzxjmxbForm) vo.getData();
		Connection conn = null;
		try {
			//创建数据库连接
			conn = SfDBResource.getConnection();
			
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(gzxjmxbForm);
			
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
			gzxjmxbForm.setCheckList(listSingle);
		}catch (Exception ex) { 
			//抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			//释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		return gzxjmxbForm;
	}
	
	/**
	 * doDelete  用于删除页面提交的详尽处理信息
	 * @param    vo 业务参数
	 * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	
	private Object doDelete(VOPackage vo) throws BaseException 
	{
        GzxjmxbForm gzxjmxbForm = (GzxjmxbForm) vo.getData();
		Connection conn = null;
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();
			
			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			
			if(gzxjmxbForm.getGzlx().equals(CodeConstant.SMSB_GZGLLX_FGX))
			{
				QysdsReportsDeclare report1=this.translate2Interface(gzxjmxbForm);
				IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
				iApp.deleteSingleTable(report1);
				iApp.updateCheckStatus(report1,"");
				QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
				table.setTableId(CodeConstant.TABLE_ID_GZXJMXB_FGX);
				table.setTableName(CodeConstant.TABLE_NAME_GZXJMXB_FGX);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
				int []arra1={2,40};
				table = (QysdsReportsTableDeclare) report1.getTableContentList().get(CodeConstant.TABLE_ID_GZXJMXB_FGX);
				gzxjmxbForm.setGzglxs_gzxj(CodeConstant.GZGLXS01);
				gzxjmxbForm.setDataList(this.FGXtranslate2Page(QysdsNewUtil.putSpace(table,arra1)));
			}
			else
			{
				QysdsReportsDeclare report2=this.translate2Interface(gzxjmxbForm);
				IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
				iApp.deleteSingleTable(report2);
				iApp.updateCheckStatus(report2,"");
				QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
				table.setTableId(CodeConstant.TABLE_ID_TABLE_ID_GZXJMXB_GX);
				table.setTableName(CodeConstant.TABLE_NAME_GZXJMXB_GX);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
				int []arra2={1,47};
				table = (QysdsReportsTableDeclare) report2.getTableContentList().get(CodeConstant.TABLE_ID_TABLE_ID_GZXJMXB_GX);
				gzxjmxbForm.setDataList(this.GXtranslate2Page(QysdsNewUtil.putSpace(table,arra2)));
			}
			
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return gzxjmxbForm;
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
	
	private void initForm(QysdsnbForm nbForm) throws BaseException {
		
		
	}
	/**
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
	 * 页面数据结构-->接口数据结构
	 * @param GzxjmxbForm 
	 * @return 企业所得税报表申明对象
	 */
	private QysdsReportsDeclare translate2Interface(GzxjmxbForm form)
	{
		//企业所得税报表申明对象
		QysdsReportsDeclare report =new QysdsReportsDeclare();
		QysdsNewUtil.setQysdsReport(report,form);
		//企业所得税报表内单表申明对象
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		List list=form.getDataList();
		if(form.getGzlx().equals("FGX"))
		{
			table.setTableId(CodeConstant.TABLE_ID_GZXJMXB_FGX);
			table.setTableName(CodeConstant.TABLE_NAME_GZXJMXB_FGX);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			
			//工资管理形式
			QysdsReportsItemDeclare itemGZGL = new QysdsReportsItemDeclare();
			itemGZGL.setItemID("1");
			itemGZGL.setItemValue(form.getGzglxs_gzxj());
			System.out.println("gzglxs============="+form.getGzglxs_gzxj());
			table.getCellContentList().put(itemGZGL.getItemID(),itemGZGL);
			
			
			for(int i=0;i<list.size();i++){
				HashMap map=(HashMap)list.get(i);
				String hc1=(String)map.get("hc1");
				if("1".equals(hc1)){
					QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
					item_1_1.setItemID("2");
					item_1_1.setItemValue((String)map.get("gzxj"));
					item_1_1.setItemType("11");
					table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
					
					QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
					item_1_2.setItemID("3");
					item_1_2.setItemValue((String)map.get("zgflf"));
					item_1_2.setItemType("11");
					table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
					
					QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
					item_1_3.setItemID("4");
					item_1_3.setItemValue((String)map.get("xj"));
					item_1_3.setItemType("11");
					table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				}
				
				if("2".equals(hc1)){
					QysdsReportsItemDeclare item_2_1=new QysdsReportsItemDeclare();
					item_2_1.setItemID("5");
					item_2_1.setItemValue((String)map.get("gzxj"));
					item_2_1.setItemType("11");
					table.getCellContentList().put(item_2_1.getItemID(),item_2_1);
					
					QysdsReportsItemDeclare item_2_2=new QysdsReportsItemDeclare();
					item_2_2.setItemID("6");
					item_2_2.setItemValue((String)map.get("zgflf"));
					item_2_2.setItemType("11");
					table.getCellContentList().put(item_2_2.getItemID(),item_2_2);
					
					QysdsReportsItemDeclare item_2_3=new QysdsReportsItemDeclare();
					item_2_3.setItemID("7");
					item_2_3.setItemValue((String)map.get("xj"));
					item_2_3.setItemType("11");
					table.getCellContentList().put(item_2_3.getItemID(),item_2_3);
				}
				
				if("3".equals(hc1)){
					QysdsReportsItemDeclare item_3_1=new QysdsReportsItemDeclare();
					item_3_1.setItemID("8");
					item_3_1.setItemValue((String)map.get("gzxj"));
					item_3_1.setItemType("11");
					table.getCellContentList().put(item_3_1.getItemID(),item_3_1);
					
					QysdsReportsItemDeclare item_3_2=new QysdsReportsItemDeclare();
					item_3_2.setItemID("9");
					item_3_2.setItemValue((String)map.get("zgflf"));
					item_3_2.setItemType("11");
					table.getCellContentList().put(item_3_2.getItemID(),item_3_2);
					
					QysdsReportsItemDeclare item_3_3=new QysdsReportsItemDeclare();
					item_3_3.setItemID("10");
					item_3_3.setItemValue((String)map.get("xj"));
					item_3_3.setItemType("11");
					table.getCellContentList().put(item_3_3.getItemID(),item_3_3);
				}
				
				if("4".equals(hc1)){
					QysdsReportsItemDeclare item_4_1=new QysdsReportsItemDeclare();
					item_4_1.setItemID("11");
					item_4_1.setItemValue((String)map.get("gzxj"));
					item_4_1.setItemType("11");
					table.getCellContentList().put(item_4_1.getItemID(),item_4_1);
					
					QysdsReportsItemDeclare item_4_2=new QysdsReportsItemDeclare();
					item_4_2.setItemID("12");
					item_4_2.setItemValue((String)map.get("ghjf"));
					item_4_2.setItemType("11");
					table.getCellContentList().put(item_4_2.getItemID(),item_4_2);
					
					QysdsReportsItemDeclare item_4_3=new QysdsReportsItemDeclare();
					item_4_3.setItemID("13");
					item_4_3.setItemValue((String)map.get("zgflf"));
					item_4_3.setItemType("11");
					table.getCellContentList().put(item_4_3.getItemID(),item_4_3);
					
					QysdsReportsItemDeclare item_4_4=new QysdsReportsItemDeclare();
					item_4_4.setItemID("14");
					item_4_4.setItemValue((String)map.get("zgjyjf"));
					item_4_4.setItemType("11");
					table.getCellContentList().put(item_4_4.getItemID(),item_4_4);
					
					QysdsReportsItemDeclare item_4_5=new QysdsReportsItemDeclare();
					item_4_5.setItemID("15");
					item_4_5.setItemValue((String)map.get("xj"));
					item_4_5.setItemType("11");
					table.getCellContentList().put(item_4_5.getItemID(),item_4_5);
				}
				
				if("5".equals(hc1)){
					QysdsReportsItemDeclare item_5_1=new QysdsReportsItemDeclare();
					item_5_1.setItemID("16");
					item_5_1.setItemValue((String)map.get("gzxj"));
					item_5_1.setItemType("11");
					table.getCellContentList().put(item_5_1.getItemID(),item_5_1);
					
					QysdsReportsItemDeclare item_5_2=new QysdsReportsItemDeclare();
					item_5_2.setItemID("17");
					item_5_2.setItemValue((String)map.get("zgflf"));
					item_5_2.setItemType("11");
					table.getCellContentList().put(item_5_2.getItemID(),item_5_2);
					
					QysdsReportsItemDeclare item_5_3=new QysdsReportsItemDeclare();
					item_5_3.setItemID("18");
					item_5_3.setItemValue((String)map.get("xj"));
					item_5_3.setItemType("11");
					table.getCellContentList().put(item_5_3.getItemID(),item_5_3);
				}
				
				if("6".equals(hc1)){
					QysdsReportsItemDeclare item_6_1=new QysdsReportsItemDeclare();
					item_6_1.setItemID("19");
					item_6_1.setItemValue((String)map.get("gzxj"));
					item_6_1.setItemType("11");
					table.getCellContentList().put(item_6_1.getItemID(),item_6_1);
					
					QysdsReportsItemDeclare item_6_2=new QysdsReportsItemDeclare();
					item_6_2.setItemID("20");
					item_6_2.setItemValue((String)map.get("ghjf"));
					item_6_2.setItemType("11");
					table.getCellContentList().put(item_6_2.getItemID(),item_6_2);
					
					QysdsReportsItemDeclare item_6_3=new QysdsReportsItemDeclare();
					item_6_3.setItemID("21");
					item_6_3.setItemValue((String)map.get("zgflf"));
					item_6_3.setItemType("11");
					table.getCellContentList().put(item_6_3.getItemID(),item_6_3);
					
					QysdsReportsItemDeclare item_6_4=new QysdsReportsItemDeclare();
					item_6_4.setItemID("22");
					item_6_4.setItemValue((String)map.get("zgjyjf"));
					item_6_4.setItemType("11");
					table.getCellContentList().put(item_6_4.getItemID(),item_6_4);
					
					QysdsReportsItemDeclare item_6_5=new QysdsReportsItemDeclare();
					item_6_5.setItemID("23");
					item_6_5.setItemValue((String)map.get("xj"));
					item_6_5.setItemType("11");
					table.getCellContentList().put(item_6_5.getItemID(),item_6_5);
					
					
				}
				
				if("7".equals(hc1)){
					QysdsReportsItemDeclare item_7_1=new QysdsReportsItemDeclare();
					item_7_1.setItemID("24");
					item_7_1.setItemValue((String)map.get("gzxj"));
					item_7_1.setItemType("11");
					table.getCellContentList().put(item_7_1.getItemID(),item_7_1);
					
					QysdsReportsItemDeclare item_7_2=new QysdsReportsItemDeclare();
					item_7_2.setItemID("25");
					item_7_2.setItemValue((String)map.get("ghjf"));
					item_7_2.setItemType("11");
					table.getCellContentList().put(item_7_2.getItemID(),item_7_2);
					
					QysdsReportsItemDeclare item_7_3=new QysdsReportsItemDeclare();
					item_7_3.setItemID("26");
					item_7_3.setItemValue((String)map.get("zgflf"));
					item_7_3.setItemType("11");
					table.getCellContentList().put(item_7_3.getItemID(),item_7_3);
					
					QysdsReportsItemDeclare item_7_4=new QysdsReportsItemDeclare();
					item_7_4.setItemID("27");
					item_7_4.setItemValue((String)map.get("zgjyjf"));
					item_7_4.setItemType("11");
					table.getCellContentList().put(item_7_4.getItemID(),item_7_4);
					
					QysdsReportsItemDeclare item_7_5=new QysdsReportsItemDeclare();
					item_7_5.setItemID("28");
					item_7_5.setItemValue((String)map.get("xj"));
					item_7_5.setItemType("11");
					table.getCellContentList().put(item_7_5.getItemID(),item_7_5);
				}
				
				if("8".equals(hc1)){
					QysdsReportsItemDeclare item_8_1=new QysdsReportsItemDeclare();
					item_8_1.setItemID("29");
					item_8_1.setItemValue((String)map.get("gzxj"));
					item_8_1.setItemType("11");
					table.getCellContentList().put(item_8_1.getItemID(),item_8_1);
				}
				if("9".equals(hc1)){
					QysdsReportsItemDeclare item_9_1=new QysdsReportsItemDeclare();
					item_9_1.setItemID("30");
					item_9_1.setItemValue((String)map.get("gzxj"));
					item_9_1.setItemType("11");
					table.getCellContentList().put(item_9_1.getItemID(),item_9_1);
				}
				if("10".equals(hc1)){
					QysdsReportsItemDeclare item_10_1=new QysdsReportsItemDeclare();
					item_10_1.setItemID("31");
					item_10_1.setItemValue((String)map.get("gzxj"));
					item_10_1.setItemType("11");
					table.getCellContentList().put(item_10_1.getItemID(),item_10_1);
					
					QysdsReportsItemDeclare item_10_2=new QysdsReportsItemDeclare();
					item_10_2.setItemID("32");
					item_10_2.setItemValue((String)map.get("ghjf"));
					item_10_2.setItemType("11");
					table.getCellContentList().put(item_10_2.getItemID(),item_10_2);
					
					QysdsReportsItemDeclare item_10_3=new QysdsReportsItemDeclare();
					item_10_3.setItemID("33");
					item_10_3.setItemValue((String)map.get("zgflf"));
					item_10_3.setItemType("11");
					table.getCellContentList().put(item_10_3.getItemID(),item_10_3);
					
					QysdsReportsItemDeclare item_10_4=new QysdsReportsItemDeclare();
					item_10_4.setItemID("34");
					item_10_4.setItemValue((String)map.get("zgjyjf"));
					item_10_4.setItemType("11");
					table.getCellContentList().put(item_10_4.getItemID(),item_10_4);
					
					QysdsReportsItemDeclare item_10_5=new QysdsReportsItemDeclare();
					item_10_5.setItemID("35");
					item_10_5.setItemValue((String)map.get("xj"));
					item_10_5.setItemType("11");
					table.getCellContentList().put(item_10_5.getItemID(),item_10_5);
				}
				
				if("11".equals(hc1)){
					QysdsReportsItemDeclare item_11_1=new QysdsReportsItemDeclare();
					item_11_1.setItemID("36");
					item_11_1.setItemValue((String)map.get("gzxj"));
					item_11_1.setItemType("11");
					table.getCellContentList().put(item_11_1.getItemID(),item_11_1);
					
					QysdsReportsItemDeclare item_11_2=new QysdsReportsItemDeclare();
					item_11_2.setItemID("37");
					item_11_2.setItemValue((String)map.get("ghjf"));
					item_11_2.setItemType("11");
					table.getCellContentList().put(item_11_2.getItemID(),item_11_2);
					
					QysdsReportsItemDeclare item_11_3=new QysdsReportsItemDeclare();
					item_11_3.setItemID("38");
					item_11_3.setItemValue((String)map.get("zgflf"));
					item_11_3.setItemType("11");
					table.getCellContentList().put(item_11_3.getItemID(),item_11_3);
					
					QysdsReportsItemDeclare item_11_4=new QysdsReportsItemDeclare();
					item_11_4.setItemID("39");
					item_11_4.setItemValue((String)map.get("zgjyjf"));
					item_11_4.setItemType("11");
					table.getCellContentList().put(item_11_4.getItemID(),item_11_4);
					
					QysdsReportsItemDeclare item_11_5=new QysdsReportsItemDeclare();
					item_11_5.setItemID("40");
					item_11_5.setItemValue((String)map.get("xj"));
					item_11_5.setItemType("11");
					table.getCellContentList().put(item_11_5.getItemID(),item_11_5);
				}
			}
		}
		else
		{
			table.setTableId(CodeConstant.TABLE_ID_TABLE_ID_GZXJMXB_GX);
			table.setTableName(CodeConstant.TABLE_NAME_GZXJMXB_GX);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			for(int i=0;i<list.size();i++){
				HashMap map=(HashMap)list.get(i);
				String hc2=(String)map.get("hc2");
				if("1".equals(hc2)){
					QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
					item_1_1.setItemID("1");
					item_1_1.setItemValue((String)map.get("L1"));
					item_1_1.setItemType("11");
					table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
					
					QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
					item_1_2.setItemID("2");
					item_1_2.setItemValue((String)map.get("L3"));
					item_1_2.setItemType("11");
					table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
					
					QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
					item_1_3.setItemID("3");
					item_1_3.setItemValue((String)map.get("L5"));
					item_1_3.setItemType("11");
					table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				}
				
				if("2".equals(hc2)){
					QysdsReportsItemDeclare item_2_1=new QysdsReportsItemDeclare();
					item_2_1.setItemID("4");
					item_2_1.setItemValue((String)map.get("L1"));
					item_2_1.setItemType("11");
					table.getCellContentList().put(item_2_1.getItemID(),item_2_1);
					
					QysdsReportsItemDeclare item_2_2=new QysdsReportsItemDeclare();
					item_2_2.setItemID("5");
					item_2_2.setItemValue((String)map.get("L3"));
					item_2_2.setItemType("11");
					table.getCellContentList().put(item_2_2.getItemID(),item_2_2);
					
					QysdsReportsItemDeclare item_2_3=new QysdsReportsItemDeclare();
					item_2_3.setItemID("6");
					item_2_3.setItemValue((String)map.get("L5"));
					item_2_3.setItemType("11");
					table.getCellContentList().put(item_2_3.getItemID(),item_2_3);
				}
				
				if("3".equals(hc2)){
					QysdsReportsItemDeclare item_3_1=new QysdsReportsItemDeclare();
					item_3_1.setItemID("7");
					item_3_1.setItemValue((String)map.get("L1"));
					item_3_1.setItemType("11");
					table.getCellContentList().put(item_3_1.getItemID(),item_3_1);
					
					QysdsReportsItemDeclare item_3_2=new QysdsReportsItemDeclare();
					item_3_2.setItemID("8");
					item_3_2.setItemValue((String)map.get("L3"));
					item_3_2.setItemType("11");
					table.getCellContentList().put(item_3_2.getItemID(),item_3_2);
					
					QysdsReportsItemDeclare item_3_3=new QysdsReportsItemDeclare();
					item_3_3.setItemID("9");
					item_3_3.setItemValue((String)map.get("L5"));
					item_3_3.setItemType("11");
					table.getCellContentList().put(item_3_3.getItemID(),item_3_3);
				}
				
				if("4".equals(hc2)){
					QysdsReportsItemDeclare item_4_1=new QysdsReportsItemDeclare();
					item_4_1.setItemID("10");
					item_4_1.setItemValue((String)map.get("L1"));
					item_4_1.setItemType("11");
					table.getCellContentList().put(item_4_1.getItemID(),item_4_1);
					
					QysdsReportsItemDeclare item_4_2=new QysdsReportsItemDeclare();
					item_4_2.setItemID("11");
					item_4_2.setItemValue((String)map.get("L2"));
					item_4_2.setItemType("11");
					table.getCellContentList().put(item_4_2.getItemID(),item_4_2);
					
					QysdsReportsItemDeclare item_4_3=new QysdsReportsItemDeclare();
					item_4_3.setItemID("12");
					item_4_3.setItemValue((String)map.get("L3"));
					item_4_3.setItemType("11");
					table.getCellContentList().put(item_4_3.getItemID(),item_4_3);
					
					QysdsReportsItemDeclare item_4_4=new QysdsReportsItemDeclare();
					item_4_4.setItemID("13");
					item_4_4.setItemValue((String)map.get("L4"));
					item_4_4.setItemType("11");
					table.getCellContentList().put(item_4_4.getItemID(),item_4_4);
					
					QysdsReportsItemDeclare item_4_5=new QysdsReportsItemDeclare();
					item_4_5.setItemID("14");
					item_4_5.setItemValue((String)map.get("L5"));
					item_4_5.setItemType("11");
					table.getCellContentList().put(item_4_5.getItemID(),item_4_5);
				}
				
				if("5".equals(hc2)){
					QysdsReportsItemDeclare item_5_1=new QysdsReportsItemDeclare();
					item_5_1.setItemID("15");
					item_5_1.setItemValue((String)map.get("L1"));
					item_5_1.setItemType("11");
					table.getCellContentList().put(item_5_1.getItemID(),item_5_1);
					
					QysdsReportsItemDeclare item_5_2=new QysdsReportsItemDeclare();
					item_5_2.setItemID("16");
					item_5_2.setItemValue((String)map.get("L3"));
					item_5_2.setItemType("11");
					table.getCellContentList().put(item_5_2.getItemID(),item_5_2);
					
					QysdsReportsItemDeclare item_5_3=new QysdsReportsItemDeclare();
					item_5_3.setItemID("17");
					item_5_3.setItemValue((String)map.get("L5"));
					item_5_3.setItemType("11");
					table.getCellContentList().put(item_5_3.getItemID(),item_5_3);
				}
				
				if("6".equals(hc2)){
					QysdsReportsItemDeclare item_6_1=new QysdsReportsItemDeclare();
					item_6_1.setItemID("18");
					item_6_1.setItemValue((String)map.get("L1"));
					item_6_1.setItemType("11");
					table.getCellContentList().put(item_6_1.getItemID(),item_6_1);
					
					QysdsReportsItemDeclare item_6_2=new QysdsReportsItemDeclare();
					item_6_2.setItemID("19");
					item_6_2.setItemValue((String)map.get("L2"));
					item_6_2.setItemType("11");
					table.getCellContentList().put(item_6_2.getItemID(),item_6_2);
					
					QysdsReportsItemDeclare item_6_3=new QysdsReportsItemDeclare();
					item_6_3.setItemID("20");
					item_6_3.setItemValue((String)map.get("L3"));
					item_6_3.setItemType("11");
					table.getCellContentList().put(item_6_3.getItemID(),item_6_3);
					
					QysdsReportsItemDeclare item_6_4=new QysdsReportsItemDeclare();
					item_6_4.setItemID("21");
					item_6_4.setItemValue((String)map.get("L4"));
					item_6_4.setItemType("11");
					table.getCellContentList().put(item_6_4.getItemID(),item_6_4);
					
					QysdsReportsItemDeclare item_6_5=new QysdsReportsItemDeclare();
					item_6_5.setItemID("22");
					item_6_5.setItemValue((String)map.get("L5"));
					item_6_5.setItemType("11");
					table.getCellContentList().put(item_6_5.getItemID(),item_6_5);
					
					
				}
				
				if("7".equals(hc2)){
					QysdsReportsItemDeclare item_7_1=new QysdsReportsItemDeclare();
					item_7_1.setItemID("23");
					item_7_1.setItemValue((String)map.get("L1"));
					item_7_1.setItemType("11");
					table.getCellContentList().put(item_7_1.getItemID(),item_7_1);
					
					QysdsReportsItemDeclare item_7_2=new QysdsReportsItemDeclare();
					item_7_2.setItemID("24");
					item_7_2.setItemValue((String)map.get("L2"));
					item_7_2.setItemType("11");
					table.getCellContentList().put(item_7_2.getItemID(),item_7_2);
					
					QysdsReportsItemDeclare item_7_3=new QysdsReportsItemDeclare();
					item_7_3.setItemID("25");
					item_7_3.setItemValue((String)map.get("L3"));
					item_7_3.setItemType("11");
					table.getCellContentList().put(item_7_3.getItemID(),item_7_3);
					
					QysdsReportsItemDeclare item_7_4=new QysdsReportsItemDeclare();
					item_7_4.setItemID("26");
					item_7_4.setItemValue((String)map.get("L4"));
					item_7_4.setItemType("11");
					table.getCellContentList().put(item_7_4.getItemID(),item_7_4);
					
					QysdsReportsItemDeclare item_7_5=new QysdsReportsItemDeclare();
					item_7_5.setItemID("27");
					item_7_5.setItemValue((String)map.get("L5"));
					item_7_5.setItemType("11");
					table.getCellContentList().put(item_7_5.getItemID(),item_7_5);
				}
				if("8".equals(hc2)){
					QysdsReportsItemDeclare item_8_1=new QysdsReportsItemDeclare();
					item_8_1.setItemID("28");
					item_8_1.setItemValue((String)map.get("L1"));
					item_8_1.setItemType("11");
					table.getCellContentList().put(item_8_1.getItemID(),item_8_1);
					
					QysdsReportsItemDeclare item_8_2=new QysdsReportsItemDeclare();
					item_8_2.setItemID("29");
					item_8_2.setItemValue((String)map.get("L2"));
					item_8_2.setItemType("11");
					table.getCellContentList().put(item_8_2.getItemID(),item_8_2);
					
					QysdsReportsItemDeclare item_8_3=new QysdsReportsItemDeclare();
					item_8_3.setItemID("30");
					item_8_3.setItemValue((String)map.get("L3"));
					item_8_3.setItemType("11");
					table.getCellContentList().put(item_8_3.getItemID(),item_8_3);
					
					QysdsReportsItemDeclare item_8_4=new QysdsReportsItemDeclare();
					item_8_4.setItemID("31");
					item_8_4.setItemValue((String)map.get("L4"));
					item_8_4.setItemType("11");
					table.getCellContentList().put(item_8_4.getItemID(),item_8_4);
					
					QysdsReportsItemDeclare item_8_5=new QysdsReportsItemDeclare();
					item_8_5.setItemID("32");
					item_8_5.setItemValue((String)map.get("L5"));
					item_8_5.setItemType("11");
					table.getCellContentList().put(item_8_5.getItemID(),item_8_5);
				}
				
				if("9".equals(hc2)){
					QysdsReportsItemDeclare item_9_1=new QysdsReportsItemDeclare();
					item_9_1.setItemID("33");
					item_9_1.setItemValue((String)map.get("L1"));
					item_9_1.setItemType("11");
					table.getCellContentList().put(item_9_1.getItemID(),item_9_1);
					
				}
				
				if("10".equals(hc2)){
					QysdsReportsItemDeclare item_10_1=new QysdsReportsItemDeclare();
					item_10_1.setItemID("34");
					item_10_1.setItemValue((String)map.get("L1"));
					item_10_1.setItemType("11");
					table.getCellContentList().put(item_10_1.getItemID(),item_10_1);
					
					QysdsReportsItemDeclare item_10_2=new QysdsReportsItemDeclare();
					item_10_2.setItemID("35");
					item_10_2.setItemValue((String)map.get("L2"));
					item_10_2.setItemType("11");
					table.getCellContentList().put(item_10_2.getItemID(),item_10_2);
					
					QysdsReportsItemDeclare item_10_3=new QysdsReportsItemDeclare();
					item_10_3.setItemID("36");
					item_10_3.setItemValue((String)map.get("L3"));
					item_10_3.setItemType("11");
					table.getCellContentList().put(item_10_3.getItemID(),item_10_3);
					
					QysdsReportsItemDeclare item_10_4=new QysdsReportsItemDeclare();
					item_10_4.setItemID("37");
					item_10_4.setItemValue((String)map.get("L4"));
					item_10_4.setItemType("11");
					table.getCellContentList().put(item_10_4.getItemID(),item_10_4);
					
					QysdsReportsItemDeclare item_10_5=new QysdsReportsItemDeclare();
					item_10_5.setItemID("38");
					item_10_5.setItemValue((String)map.get("L5"));
					item_10_5.setItemType("11");
					table.getCellContentList().put(item_10_5.getItemID(),item_10_5);
					
					
				}
				
				if("11".equals(hc2)){
					QysdsReportsItemDeclare item_11_1=new QysdsReportsItemDeclare();
					item_11_1.setItemID("39");
					item_11_1.setItemValue((String)map.get("L1"));
					item_11_1.setItemType("11");
					table.getCellContentList().put(item_11_1.getItemID(),item_11_1);
					
					QysdsReportsItemDeclare item_11_2=new QysdsReportsItemDeclare();
					item_11_2.setItemID("40");
					item_11_2.setItemValue((String)map.get("L2"));
					item_11_2.setItemType("11");
					table.getCellContentList().put(item_11_2.getItemID(),item_11_2);
					
					QysdsReportsItemDeclare item_11_3=new QysdsReportsItemDeclare();
					item_11_3.setItemID("41");
					item_11_3.setItemValue((String)map.get("L3"));
					item_11_3.setItemType("11");
					table.getCellContentList().put(item_11_3.getItemID(),item_11_3);
					
					QysdsReportsItemDeclare item_11_4=new QysdsReportsItemDeclare();
					item_11_4.setItemID("42");
					item_11_4.setItemValue((String)map.get("L4"));
					item_11_4.setItemType("11");
					table.getCellContentList().put(item_11_4.getItemID(),item_11_4);
					
					QysdsReportsItemDeclare item_11_5=new QysdsReportsItemDeclare();
					item_11_5.setItemID("43");
					item_11_5.setItemValue((String)map.get("L5"));
					item_11_5.setItemType("11");
					table.getCellContentList().put(item_11_5.getItemID(),item_11_5);
				}
				
				if("12".equals(hc2)){
					QysdsReportsItemDeclare item_12_1=new QysdsReportsItemDeclare();
					item_12_1.setItemID("44");
					item_12_1.setItemValue((String)map.get("L1"));
					item_12_1.setItemType("11");
					table.getCellContentList().put(item_12_1.getItemID(),item_12_1);
					
					
				}
				
				if("13".equals(hc2)){
					QysdsReportsItemDeclare item_13_1=new QysdsReportsItemDeclare();
					item_13_1.setItemID("45");
					item_13_1.setItemValue((String)map.get("L1"));
					item_13_1.setItemType("11");
					table.getCellContentList().put(item_13_1.getItemID(),item_13_1);
					
				}
				if("14".equals(hc2)){
					QysdsReportsItemDeclare item_14_1=new QysdsReportsItemDeclare();
					item_14_1.setItemID("46");
					item_14_1.setItemValue((String)map.get("L1"));
					item_14_1.setItemType("11");
					table.getCellContentList().put(item_14_1.getItemID(),item_14_1);
					
				}
				
				if("15".equals(hc2)){
					QysdsReportsItemDeclare item_15_1=new QysdsReportsItemDeclare();
					item_15_1.setItemID("47");
					item_15_1.setItemValue((String)map.get("L1"));
					item_15_1.setItemType("11");
					table.getCellContentList().put(item_15_1.getItemID(),item_15_1);
					
				}
			}	
			
		}
		report.getTableContentList().put(table.getTableId(), QysdsNewUtil.cleanSpace(table));
		return report;
	}
	/**
	 * 将接口数据结构中的数据转换置入页面要求的数据结构
	 * 接口数据结构-->页面数据结构
	 * @param QysdsReportsTableDeclare
	 * @return 非工效企业所得税报表申明对象
	 */
	private List FGXtranslate2Page(QysdsReportsTableDeclare table){
		List list=new ArrayList();
		Map map=table.getCellContentList();
		Map map1=new HashMap();
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
		map1.put("hc1","1");
		QysdsReportsItemDeclare item1_1=(QysdsReportsItemDeclare)map.get("2");
		String gzxj1=item1_1.getItemValue();
		map1.put("gzxj",gzxj1);
		map1.put("ghjf","*");
		QysdsReportsItemDeclare item1_2=(QysdsReportsItemDeclare)map.get("3");
		String zgflf1=item1_2.getItemValue();
		map1.put("zgflf",zgflf1);
		map1.put("zgjyjf","*");
		QysdsReportsItemDeclare item1_3=(QysdsReportsItemDeclare)map.get("4");
		String xj1=item1_3.getItemValue();
		map1.put("xj",xj1);
		
		map2.put("hc1","2");
		QysdsReportsItemDeclare item2_1=(QysdsReportsItemDeclare)map.get("5");
		String gzxj2=item2_1.getItemValue();
		map2.put("gzxj",gzxj2);
		map2.put("ghjf","*");
		QysdsReportsItemDeclare item2_2=(QysdsReportsItemDeclare)map.get("6");
		String zgflf2=item2_2.getItemValue();
		map2.put("zgflf",zgflf2);
		map2.put("zgjyjf","*");
		QysdsReportsItemDeclare item2_3=(QysdsReportsItemDeclare)map.get("7");
		String xj2=item2_3.getItemValue();
		map2.put("xj",xj2);
		
		map3.put("hc1","3");
		QysdsReportsItemDeclare item3_1=(QysdsReportsItemDeclare)map.get("8");
		String gzxj3=item3_1.getItemValue();
		map3.put("gzxj",gzxj3);
		map3.put("ghjf","*");
		QysdsReportsItemDeclare item3_2=(QysdsReportsItemDeclare)map.get("9");
		String zgflf3=item3_2.getItemValue();
		map3.put("zgflf",zgflf3);
		map3.put("zgjyjf","*");
		QysdsReportsItemDeclare item3_3=(QysdsReportsItemDeclare)map.get("10");
		String xj3=item3_3.getItemValue();
		map3.put("xj",xj3);
		
		map4.put("hc1","4");
		QysdsReportsItemDeclare item4_1=(QysdsReportsItemDeclare)map.get("11");
		String gzxj4=item4_1.getItemValue();
		map4.put("gzxj",gzxj4);
		QysdsReportsItemDeclare item4_2=(QysdsReportsItemDeclare)map.get("12");
		String ghjf4=item4_2.getItemValue();
		map4.put("ghjf",ghjf4);
		QysdsReportsItemDeclare item4_3=(QysdsReportsItemDeclare)map.get("13");
		String zgflf4=item4_3.getItemValue();
		map4.put("zgflf",zgflf4);
		QysdsReportsItemDeclare item4_4=(QysdsReportsItemDeclare)map.get("14");
		String zgjyjf4=item4_4.getItemValue();
		map4.put("zgjyjf",zgjyjf4);
		QysdsReportsItemDeclare item4_5=(QysdsReportsItemDeclare)map.get("15");
		String xj4=item4_5.getItemValue();
		map4.put("xj",xj4);
		
		map5.put("hc1","5");
		QysdsReportsItemDeclare item5_1=(QysdsReportsItemDeclare)map.get("16");
		String gzxj5=item5_1.getItemValue();
		map5.put("gzxj",gzxj5);
		map5.put("ghjf","*");
		QysdsReportsItemDeclare item5_2=(QysdsReportsItemDeclare)map.get("17");
		String zgflf5=item5_2.getItemValue();
		map5.put("zgflf",zgflf5);
		map5.put("zgjyjf","*");
		QysdsReportsItemDeclare item5_3=(QysdsReportsItemDeclare)map.get("18");
		String xj5=item5_3.getItemValue();
		map5.put("xj",xj5);
		
		map6.put("hc1","6");
		QysdsReportsItemDeclare item6_1=(QysdsReportsItemDeclare)map.get("19");
		String gzxj6=item6_1.getItemValue();
		map6.put("gzxj",gzxj6);
		QysdsReportsItemDeclare item6_2=(QysdsReportsItemDeclare)map.get("20");
		String ghjf6=item6_2.getItemValue();
		map6.put("ghjf",ghjf6);
		QysdsReportsItemDeclare item6_3=(QysdsReportsItemDeclare)map.get("21");
		String zgflf6=item6_3.getItemValue();
		map6.put("zgflf",zgflf6);
		QysdsReportsItemDeclare item6_4=(QysdsReportsItemDeclare)map.get("22");
		String zgjyjf6=item6_4.getItemValue();
		map6.put("zgjyjf",zgjyjf6);
		QysdsReportsItemDeclare item6_5=(QysdsReportsItemDeclare)map.get("23");
		String xj6=item6_5.getItemValue();
		map6.put("xj",xj6);
		
		map7.put("hc1","7");
		QysdsReportsItemDeclare item7_1=(QysdsReportsItemDeclare)map.get("24");
		String gzxj7=item7_1.getItemValue();
		map7.put("gzxj",gzxj7);
		QysdsReportsItemDeclare item7_2=(QysdsReportsItemDeclare)map.get("25");
		String ghjf7=item7_2.getItemValue();
		map7.put("ghjf",ghjf7);
		QysdsReportsItemDeclare item7_3=(QysdsReportsItemDeclare)map.get("26");
		String zgflf7=item7_3.getItemValue();
		map7.put("zgflf",zgflf7);
		QysdsReportsItemDeclare item7_4=(QysdsReportsItemDeclare)map.get("27");
		String zgjyjf7=item7_4.getItemValue();
		map7.put("zgjyjf",zgjyjf7);
		QysdsReportsItemDeclare item7_5=(QysdsReportsItemDeclare)map.get("28");
		String xj7=item7_5.getItemValue();
		map7.put("xj",xj7);
		
		map8.put("hc1","8");
		QysdsReportsItemDeclare item8_1=(QysdsReportsItemDeclare)map.get("29");
		String gzxj8=item8_1.getItemValue();
		map8.put("gzxj",gzxj8);
		map8.put("ghjf","");
		map8.put("zgflf","");
		map8.put("zgjyjf","");
		map8.put("xj","");
		
		map9.put("hc1","9");
		QysdsReportsItemDeclare item9_1=(QysdsReportsItemDeclare)map.get("30");
		String gzxj9=item9_1.getItemValue();
		map9.put("gzxj",gzxj9);
		map9.put("ghjf","");
		map9.put("zgflf","");
		map9.put("zgjyjf","");
		map9.put("xj","");
		
		map10.put("hc1","10");
		QysdsReportsItemDeclare item10_1=(QysdsReportsItemDeclare)map.get("31");
		String gzxj10=item10_1.getItemValue();
		map10.put("gzxj",gzxj10);
		QysdsReportsItemDeclare item10_2=(QysdsReportsItemDeclare)map.get("32");
		String ghjf10=item10_2.getItemValue();
		map10.put("ghjf",ghjf10);
		QysdsReportsItemDeclare item10_3=(QysdsReportsItemDeclare)map.get("33");
		String zgflf10=item10_3.getItemValue();
		map10.put("zgflf",zgflf10);
		QysdsReportsItemDeclare item10_4=(QysdsReportsItemDeclare)map.get("34");
		String zgjyjf10=item10_4.getItemValue();
		map10.put("zgjyjf",zgjyjf10);
		QysdsReportsItemDeclare item10_5=(QysdsReportsItemDeclare)map.get("35");
		String xj10=item10_5.getItemValue();
		map10.put("xj",xj10);
		
		map11.put("hc1","11");
		QysdsReportsItemDeclare item11_1=(QysdsReportsItemDeclare)map.get("36");
		String gzxj11=item11_1.getItemValue();
		map11.put("gzxj",gzxj11);
		QysdsReportsItemDeclare item11_2=(QysdsReportsItemDeclare)map.get("37");
		String ghjf11=item11_2.getItemValue();
		map11.put("ghjf",ghjf11);
		QysdsReportsItemDeclare item11_3=(QysdsReportsItemDeclare)map.get("38");
		String zgflf11=item11_3.getItemValue();
		map11.put("zgflf",zgflf11);
		QysdsReportsItemDeclare item11_4=(QysdsReportsItemDeclare)map.get("39");
		String zgjyjf11=item11_4.getItemValue();
		map11.put("zgjyjf",zgjyjf11);
		QysdsReportsItemDeclare item11_5=(QysdsReportsItemDeclare)map.get("40");
		String xj11=item11_5.getItemValue();
		map11.put("xj",xj11);
		
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		list.add(map6);
		list.add(map7);
		list.add(map8);
		list.add(map9);
		list.add(map10);
		list.add(map11);
		
		//测试反向输出
		for(int h=0;h<list.size();h++)
		{
			HashMap mapcs=(HashMap)list.get(h);
			
			String hc=(String)mapcs.get("hc1");
			String gzxj=(String)mapcs.get("gzxj");
			String ghjf=(String)mapcs.get("ghjf");
			String zgflf=(String)mapcs.get("zgflf");
			String zgjyjf=(String)mapcs.get("zgjyjf");
			String xj=(String)mapcs.get("xj");
			
			System.out.println("hc-"+hc +"   gzxj-"+gzxj+"   ghjf-"+ghjf+"     zgflf-"+zgflf+"       zgjyjf-"+zgjyjf+"   xj-"+xj);
			
		}
		return list;
		
	}
	/**
	 * 将接口数据结构中的数据转换置入页面要求的数据结构
	 * 接口数据结构-->页面数据结构
	 * @param QysdsReportsTableDeclare
	 * @return 工效企业所得税报表申明对象
	 */
	private List GXtranslate2Page(QysdsReportsTableDeclare table)
	{
		List list=new ArrayList();
		Map map=table.getCellContentList();
		Map map1=new HashMap();
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
		Map map12=new HashMap();
		Map map13=new HashMap();
		Map map14=new HashMap();
		Map map15=new HashMap();
		map1.put("hc2","1");
		QysdsReportsItemDeclare item1_1=(QysdsReportsItemDeclare)map.get("1");
		String L1_1=item1_1.getItemValue();
		map1.put("L1",L1_1);
		map1.put("L2","*");
		QysdsReportsItemDeclare item1_2=(QysdsReportsItemDeclare)map.get("2");
		String L3_1=item1_2.getItemValue();
		map1.put("L3",L3_1);
		map1.put("L4","*");
		QysdsReportsItemDeclare item1_3=(QysdsReportsItemDeclare)map.get("3");
		String L5_1=item1_3.getItemValue();
		map1.put("L5",L5_1);
		
		map2.put("hc2","2");
		QysdsReportsItemDeclare item2_1=(QysdsReportsItemDeclare)map.get("4");
		String L1_2=item2_1.getItemValue();
		map2.put("L1",L1_2);
		map2.put("L2","*");
		QysdsReportsItemDeclare item2_2=(QysdsReportsItemDeclare)map.get("5");
		String L3_2=item2_2.getItemValue();
		map2.put("L3",L3_2);
		map2.put("L4","*");
		QysdsReportsItemDeclare item2_3=(QysdsReportsItemDeclare)map.get("6");
		String L5_2=item2_3.getItemValue();
		map2.put("L5",L5_2);
		
		map3.put("hc2","3");
		QysdsReportsItemDeclare item3_1=(QysdsReportsItemDeclare)map.get("7");
		String L1_3=item3_1.getItemValue();
		map3.put("L1",L1_3);
		map3.put("L2","*");
		QysdsReportsItemDeclare item3_2=(QysdsReportsItemDeclare)map.get("8");
		String L3_3=item3_2.getItemValue();
		map3.put("L3",L3_3);
		map3.put("L4","*");
		QysdsReportsItemDeclare item3_3=(QysdsReportsItemDeclare)map.get("9");
		String L5_3=item3_3.getItemValue();
		map3.put("L5",L5_3);
		
		map4.put("hc2","4");
		QysdsReportsItemDeclare item4_1=(QysdsReportsItemDeclare)map.get("10");
		String L1_4=item4_1.getItemValue();
		map4.put("L1",L1_4);
		QysdsReportsItemDeclare item4_2=(QysdsReportsItemDeclare)map.get("11");
		String L2_4=item4_2.getItemValue();
		map4.put("L2",L2_4);
		QysdsReportsItemDeclare item4_3=(QysdsReportsItemDeclare)map.get("12");
		String L3_4=item4_3.getItemValue();
		map4.put("L3",L3_4);
		QysdsReportsItemDeclare item4_4=(QysdsReportsItemDeclare)map.get("13");
		String L4_4=item4_4.getItemValue();
		map4.put("L4",L4_4);
		QysdsReportsItemDeclare item4_5=(QysdsReportsItemDeclare)map.get("14");
		String L5_4=item4_5.getItemValue();
		map4.put("L5",L5_4);
		
		map5.put("hc2","5");
		QysdsReportsItemDeclare item5_1=(QysdsReportsItemDeclare)map.get("15");
		String L1_5=item5_1.getItemValue();
		map5.put("L1",L1_5);
		map5.put("L2","*");
		QysdsReportsItemDeclare item5_3=(QysdsReportsItemDeclare)map.get("16");
		String L3_5=item5_3.getItemValue();
		map5.put("L3",L3_5);
		map5.put("L4","*");
		QysdsReportsItemDeclare item5_5=(QysdsReportsItemDeclare)map.get("17");
		String L5_5=item5_5.getItemValue();
		map5.put("L5",L5_5);
		
		map6.put("hc2","6");
		QysdsReportsItemDeclare item6_1=(QysdsReportsItemDeclare)map.get("18");
		String L1_6=item6_1.getItemValue();
		map6.put("L1",L1_6);
		QysdsReportsItemDeclare item6_2=(QysdsReportsItemDeclare)map.get("19");
		String L2_6=item6_2.getItemValue();
		map6.put("L2",L2_6);
		QysdsReportsItemDeclare item6_3=(QysdsReportsItemDeclare)map.get("20");
		String L3_6=item6_3.getItemValue();
		map6.put("L3",L3_6);
		QysdsReportsItemDeclare item6_4=(QysdsReportsItemDeclare)map.get("21");
		String L4_6=item6_4.getItemValue();
		map6.put("L4",L4_6);
		QysdsReportsItemDeclare item6_5=(QysdsReportsItemDeclare)map.get("22");
		String L5_6=item6_5.getItemValue();
		map6.put("L5",L5_6);
		
		map7.put("hc2","7");
		QysdsReportsItemDeclare item7_1=(QysdsReportsItemDeclare)map.get("23");
		String L1_7=item7_1.getItemValue();
		map7.put("L1",L1_7);
		QysdsReportsItemDeclare item7_2=(QysdsReportsItemDeclare)map.get("24");
		String L2_7=item7_2.getItemValue();
		map7.put("L2",L2_7);
		QysdsReportsItemDeclare item7_3=(QysdsReportsItemDeclare)map.get("25");
		String L3_7=item7_3.getItemValue();
		map7.put("L3",L3_7);
		QysdsReportsItemDeclare item7_4=(QysdsReportsItemDeclare)map.get("26");
		String L4_7=item7_4.getItemValue();
		map7.put("L4",L4_7);
		QysdsReportsItemDeclare item7_5=(QysdsReportsItemDeclare)map.get("27");
		String L5_7=item7_5.getItemValue();
		map7.put("L5",L5_7);
		
		map8.put("hc2","8");
		QysdsReportsItemDeclare item8_1=(QysdsReportsItemDeclare)map.get("28");
		String L1_8=item8_1.getItemValue();
		map8.put("L1",L1_8);
		QysdsReportsItemDeclare item8_2=(QysdsReportsItemDeclare)map.get("29");
		String L2_8=item8_2.getItemValue();
		map8.put("L2",L2_8);
		QysdsReportsItemDeclare item8_3=(QysdsReportsItemDeclare)map.get("30");
		String L3_8=item8_3.getItemValue();
		map8.put("L3",L3_8);
		QysdsReportsItemDeclare item8_4=(QysdsReportsItemDeclare)map.get("31");
		String L4_8=item8_4.getItemValue();
		map8.put("L4",L4_8);
		QysdsReportsItemDeclare item8_5=(QysdsReportsItemDeclare)map.get("32");
		String L5_8=item8_5.getItemValue();
		map8.put("L5",L5_8);
		
		map9.put("hc2","9");
		QysdsReportsItemDeclare item9_1=(QysdsReportsItemDeclare)map.get("33");
		String L1_9=item9_1.getItemValue();
		map9.put("L1",L1_9);
		map9.put("L2","*");
		map9.put("L3","*");
		map9.put("L4","*");
		map9.put("L5","" );
		
		map10.put("hc2","10");
		QysdsReportsItemDeclare item10_1=(QysdsReportsItemDeclare)map.get("34");
		String L1_10=item10_1.getItemValue();
		map10.put("L1",L1_10);
		QysdsReportsItemDeclare item10_2=(QysdsReportsItemDeclare)map.get("35");
		String L2_10=item10_2.getItemValue();
		map10.put("L2",L2_10);
		QysdsReportsItemDeclare item10_3=(QysdsReportsItemDeclare)map.get("36");
		String L3_10=item10_3.getItemValue();
		map10.put("L3",L3_10);
		QysdsReportsItemDeclare item10_4=(QysdsReportsItemDeclare)map.get("37");
		String L4_10=item10_4.getItemValue();
		map10.put("L4",L4_10);
		QysdsReportsItemDeclare item10_5=(QysdsReportsItemDeclare)map.get("38");
		String L5_10=item10_5.getItemValue();
		map10.put("L5",L5_10);
		
		
		map11.put("hc2","11");
		QysdsReportsItemDeclare item11_1=(QysdsReportsItemDeclare)map.get("39");
		String L1_11=item11_1.getItemValue();
		map11.put("L1",L1_11);
		QysdsReportsItemDeclare item11_2=(QysdsReportsItemDeclare)map.get("40");
		String L2_11=item11_2.getItemValue();
		map11.put("L2",L2_11);
		QysdsReportsItemDeclare item11_3=(QysdsReportsItemDeclare)map.get("41");
		String L3_11=item11_3.getItemValue();
		map11.put("L3",L3_11);
		QysdsReportsItemDeclare item11_4=(QysdsReportsItemDeclare)map.get("42");
		String L4_11=item11_4.getItemValue();
		map11.put("L4",L4_11);
		QysdsReportsItemDeclare item11_5=(QysdsReportsItemDeclare)map.get("43");
		String L5_11=item11_5.getItemValue();
		map11.put("L5",L5_11);
		
		map12.put("hc2","12");
		QysdsReportsItemDeclare item12_1=(QysdsReportsItemDeclare)map.get("44");
		String L1_12=item12_1.getItemValue();
		map12.put("L1",L1_12);
		map12.put("L2","*");
		map12.put("L3","*");
		map12.put("L4","*");
		map12.put("L5","");
		
		map13.put("hc2","13");
		QysdsReportsItemDeclare item13_1=(QysdsReportsItemDeclare)map.get("45");
		String L1_13=item13_1.getItemValue();
		map13.put("L1",L1_13);
		map13.put("L2","*");
		map13.put("L3","*");
		map13.put("L4","*");
		map13.put("L5","");
		
		map14.put("hc2","14");
		QysdsReportsItemDeclare item14_1=(QysdsReportsItemDeclare)map.get("46");
		String L1_14=item14_1.getItemValue();
		map14.put("L1",L1_14);
		map14.put("L2","*");
		map14.put("L3","*");
		map14.put("L4","*");
		map14.put("L5","");
		
		map15.put("hc2","15");
		QysdsReportsItemDeclare item15_1=(QysdsReportsItemDeclare)map.get("47");
		String L1_15=item15_1.getItemValue();
		map15.put("L1",L1_15);
		map15.put("L2","*");
		map15.put("L3","*");
		map15.put("L4","*");
		map15.put("L5","");
		
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		list.add(map6);
		list.add(map7);
		list.add(map8);
		list.add(map9);
		list.add(map10);
		list.add(map11);
		list.add(map12);
		list.add(map13);
		list.add(map14);
		list.add(map15);
		
//		测试反向输出
		for(int h=0;h<list.size();h++)
		{
			HashMap mapcs=(HashMap)list.get(h);
			
			String hc=(String)mapcs.get("hc2");
			String gzxj=(String)mapcs.get("L1");
			String ghjf=(String)mapcs.get("L2");
			String zgflf=(String)mapcs.get("L3");
			String zgjyjf=(String)mapcs.get("L4");
			String xj=(String)mapcs.get("L5");
			
			System.out.println("hc-"+hc +"   gzxj-"+gzxj+"   ghjf-"+ghjf+"     zgflf-"+zgflf+"       zgjyjf-"+zgjyjf+"   xj-"+xj);
			
		}
		
		return list;
	}
	
}
