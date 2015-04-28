/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.tzmxb.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.tzmxb.web.TzmxbForm;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报</p>
 * @author 付江霞
 * @version 1.1
 */

public class TzmxbProcessor
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
		
		TzmxbForm nbForm = (TzmxbForm) vo.getData();
		
		Connection conn = null;
		try
		{
//			获取数据库连接
			conn = SfDBResource.getConnection();
//			将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsNewUtil.setQysdsReport(report, nbForm);
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_TZMXB);
			table.setTableName(CodeConstant.TABLE_NAME_TZMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(), table);
			
//			获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			int[] arrs={1,5,6,10,17,22,30,36,37,43,55,61,73,77,78,79,80,81};
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_TZMXB);
			
			List listgd = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("pagelist");
			List dqzq = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("dqzq");
			List cqzq = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("cqzq");
			List dqgq = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("dqgq");
			List cqgq = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("cqgq");
			
			String value78 = (String)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("value78");
			String value79 = (String)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("value79");
			String value80 = (String)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("value80");
			String value81 = (String)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("value81");
			
			nbForm.setDataList(listgd);
			nbForm.setDqzqtzList(dqzq);
			nbForm.setCqzqtzList(cqzq);
			nbForm.setDqgqtzList(dqgq);
			nbForm.setCqgqtzList(cqgq);
			nbForm.setGqtzzrss(value78);
			nbForm.setGqsszrsqkcqe(value79);
			nbForm.setNstze(value80);
			nbForm.setJzhkce(value81);
		}catch(Exception ex)
		{
//			抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally
		{
			SfDBResource.freeConnection(conn);
		}
		
		return nbForm;
	}
	
	/**
	 * doSave   用于存储页面提交的详尽处理信息
	 * @param   vo 业务参数
	 * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	
	private Object doSave(VOPackage vo) throws BaseException {
		TzmxbForm form = (TzmxbForm) vo.getData();
		
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
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			SfDBResource.freeConnection(conn);
		}
		return form;
	}
	
	/**
	 * doDelete  用于删除页面提交的详尽处理信息
	 * @param    vo 业务参数
	 * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	
	private Object doDelete(VOPackage vo) throws BaseException 
	{
		TzmxbForm form = (TzmxbForm) vo.getData();
		
		Connection conn = null;
		try
		{
//			获取数据库连接
			
			conn = SfDBResource.getConnection();
//			将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(form);
//			获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			try
			{
				iApp.deleteSingleTable(report);	
				iApp.updateCheckStatus(report,"");
				QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
				table.setTableId(CodeConstant.TABLE_ID_TZMXB);
				table.setTableName(CodeConstant.TABLE_NAME_TZMXB);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
				table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_TZMXB);
				int[] arrs={1,5,6,10,17,22,30,36,37,43,55,61,73,77,78,79,80,81};
				List listgd = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("pagelist");
				List dqzq = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("dqzq");
				List cqzq = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("cqzq");
				List dqgq = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("dqgq");
				List cqgq = (List)this.translate2Page(QysdsNewUtil.putSpace(table,arrs)).get("cqgq");
				
				form.setDataList(listgd);
				form.setDqzqtzList(dqzq);
				form.setCqzqtzList(cqzq);
				form.setDqgqtzList(dqgq);
				form.setCqgqtzList(cqgq);
				form.setGqtzzrss("");
				form.setGqsszrsqkcqe("");
				form.setNstze("");
				form.setJzhkce("");
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally
		{
			SfDBResource.freeConnection(conn);
		}
		return form;
	}
	
	/**
	 * doSave   用于存储页面提交的详尽处理信息
	 * @param   vo 业务参数
	 * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	private Object doCheck(VOPackage vo) throws BaseException {
		
		TzmxbForm tzmxbForm = (TzmxbForm) vo.getData();
		
		Connection conn = null;
		
		try {
			//创建数据库连接
			conn = SfDBResource.getConnection();
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(tzmxbForm);
			//获取校验接口
			Checker checker=CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			//进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			tzmxbForm.setCheckList(listSingle);
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
		return tzmxbForm;
	}
	
	
	/**
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
	 * 页面数据结构-->接口数据结构
	 * @param ZcmxbForm 
	 * @return 企业所得税报表申明对象
	 */
	private QysdsReportsDeclare translate2Interface(TzmxbForm form){
		
		//---------企业所得税报表申明对象-------------
		QysdsReportsDeclare report =new QysdsReportsDeclare();
		QysdsNewUtil.setQysdsReport(report, form); //对report 进行一系列的设置
		
		//企业所得税报表内单表申明对象
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_TZMXB);
		table.setTableName(CodeConstant.TABLE_NAME_TZMXB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		
		List list=form.getDataList();  //存放固定行数据
		
		for(int i=0;i<list.size();i++){
			HashMap map=(HashMap)list.get(i);
			
			String hc=(String)map.get("hc");
			if("1".equals(hc)){
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("1");
				item_1.setItemValue((String)map.get("tzsy"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("2");
				item_2.setItemValue((String)map.get("tzzrjsr"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("3");
				item_3.setItemValue((String)map.get("cstzcb"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("4");
				item_4.setItemValue((String)map.get("tzzrcb"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
				QysdsReportsItemDeclare item_5=new QysdsReportsItemDeclare();
				item_5.setItemID("5");
				item_5.setItemValue((String)map.get("tzzrsd"));
				item_5.setItemType("11");
				table.getCellContentList().put(item_5.getItemID(),item_5);
			}
			if("2".equals(hc)){
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("6");
				item_1.setItemValue((String)map.get("tzsy"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("7");
				item_2.setItemValue((String)map.get("tzzrjsr"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("8");
				item_3.setItemValue((String)map.get("cstzcb"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("9");
				item_4.setItemValue((String)map.get("tzzrcb"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
				QysdsReportsItemDeclare item_5=new QysdsReportsItemDeclare();
				item_5.setItemID("10");
				item_5.setItemValue((String)map.get("tzzrsd"));
				item_5.setItemType("11");
				table.getCellContentList().put(item_5.getItemID(),item_5);
			}
			if("3".equals(hc)){
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("17");
				item_1.setItemValue((String)map.get("tzsy"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("18");
				item_2.setItemValue((String)map.get("tzzrjsr"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("19");
				item_3.setItemValue((String)map.get("cstzcb"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("20");
				item_4.setItemValue((String)map.get("jscbtz"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
				QysdsReportsItemDeclare item_5=new QysdsReportsItemDeclare();
				item_5.setItemID("21");
				item_5.setItemValue((String)map.get("tzzrcb"));
				item_5.setItemType("11");
				table.getCellContentList().put(item_5.getItemID(),item_5);	
				
				QysdsReportsItemDeclare item_6=new QysdsReportsItemDeclare();
				item_6.setItemID("22");
				item_6.setItemValue((String)map.get("tzzrsd"));
				item_6.setItemType("11");
				table.getCellContentList().put(item_6.getItemID(),item_6);	
			}
			if("4".equals(hc)){
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("30");
				item_1.setItemValue((String)map.get("tzsy"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("31");
				item_2.setItemValue((String)map.get("ynqysds"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("32");
				item_3.setItemValue((String)map.get("tzzrjsr"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("33");
				item_4.setItemValue((String)map.get("cstzcb"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
				QysdsReportsItemDeclare item_5=new QysdsReportsItemDeclare();
				item_5.setItemID("34");
				item_5.setItemValue((String)map.get("jscbtz"));
				item_5.setItemType("11");
				table.getCellContentList().put(item_5.getItemID(),item_5);	
				
				QysdsReportsItemDeclare item_6=new QysdsReportsItemDeclare();
				item_6.setItemID("35");
				item_6.setItemValue((String)map.get("tzzrcb"));
				item_6.setItemType("11");
				table.getCellContentList().put(item_6.getItemID(),item_6);	
				
				QysdsReportsItemDeclare item_7=new QysdsReportsItemDeclare();
				item_7.setItemID("36");
				item_7.setItemValue((String)map.get("tzzrsd"));
				item_7.setItemType("11");
				table.getCellContentList().put(item_7.getItemID(),item_7);
			}
			if("5".equals(hc)){
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("37");
				item_1.setItemValue((String)map.get("tzsy"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("38");
				item_2.setItemValue((String)map.get("ynqysds"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("39");
				item_3.setItemValue((String)map.get("tzzrjsr"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("40");
				item_4.setItemValue((String)map.get("cstzcb"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
				QysdsReportsItemDeclare item_5=new QysdsReportsItemDeclare();
				item_5.setItemID("41");
				item_5.setItemValue((String)map.get("jscbtz"));
				item_5.setItemType("11");
				table.getCellContentList().put(item_5.getItemID(),item_5);	
				
				QysdsReportsItemDeclare item_6=new QysdsReportsItemDeclare();
				item_6.setItemID("42");
				item_6.setItemValue((String)map.get("tzzrcb"));
				item_6.setItemType("11");
				table.getCellContentList().put(item_6.getItemID(),item_6);	
				
				QysdsReportsItemDeclare item_7=new QysdsReportsItemDeclare();
				item_7.setItemID("43");
				item_7.setItemValue((String)map.get("tzzrsd"));
				item_7.setItemType("11");
				table.getCellContentList().put(item_7.getItemID(),item_7);
			}
			if("6".equals(hc)){
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("55");
				item_1.setItemValue((String)map.get("tzsy"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("56");
				item_2.setItemValue((String)map.get("ynqysds"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("57");
				item_3.setItemValue((String)map.get("tzzrjsr"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("58");
				item_4.setItemValue((String)map.get("cstzcb"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
				QysdsReportsItemDeclare item_5=new QysdsReportsItemDeclare();
				item_5.setItemID("59");
				item_5.setItemValue((String)map.get("jscbtz"));
				item_5.setItemType("11");
				table.getCellContentList().put(item_5.getItemID(),item_5);	
				
				QysdsReportsItemDeclare item_6=new QysdsReportsItemDeclare();
				item_6.setItemID("60");
				item_6.setItemValue((String)map.get("tzzrcb"));
				item_6.setItemType("11");
				table.getCellContentList().put(item_6.getItemID(),item_6);	
				
				QysdsReportsItemDeclare item_7=new QysdsReportsItemDeclare();
				item_7.setItemID("61");
				item_7.setItemValue((String)map.get("tzzrsd"));
				item_7.setItemType("11");
				table.getCellContentList().put(item_7.getItemID(),item_7);
			}
			if("7".equals(hc)){
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("73");
				item_1.setItemValue((String)map.get("tzsy"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("74");
				item_2.setItemValue((String)map.get("ynqysds"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("75");
				item_3.setItemValue((String)map.get("tzzrjsr"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("76");
				item_4.setItemValue((String)map.get("cstzcb"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
				QysdsReportsItemDeclare item_5=new QysdsReportsItemDeclare();
				item_5.setItemID("77");
				item_5.setItemValue((String)map.get("tzzrcb"));
				item_5.setItemType("11");
				table.getCellContentList().put(item_5.getItemID(),item_5);	
				
			}
		}
		/**
		 * 翻译补充资料中的数据
		 */
		QysdsReportsItemDeclare item_111=new QysdsReportsItemDeclare();
		item_111.setItemID("78");
		item_111.setItemValue(form.getGqtzzrss());
		item_111.setItemType("11");
		table.getCellContentList().put(item_111.getItemID(),item_111);
		
		QysdsReportsItemDeclare item_12=new QysdsReportsItemDeclare();
		item_12.setItemID("79");
		item_12.setItemValue(form.getGqsszrsqkcqe());
		item_12.setItemType("11");
		table.getCellContentList().put(item_12.getItemID(),item_12);
		
		QysdsReportsItemDeclare item_13=new QysdsReportsItemDeclare();
		item_13.setItemID("80");
		item_13.setItemValue(form.getNstze());
		item_13.setItemType("11");
		table.getCellContentList().put(item_13.getItemID(),item_13);
		
		QysdsReportsItemDeclare item_14=new QysdsReportsItemDeclare();
		item_14.setItemID("81");
		item_14.setItemValue(form.getJzhkce());
		item_14.setItemType("11");
		table.getCellContentList().put(item_14.getItemID(),item_14);
		/**
		 * 翻译短期债权投资中的数据
		 */
		
		List dqzqtz=this.filterList1(form.getDqzqtzList(),"dqzqtz_tzzczl", 
				"dqzqtz_tzsy","dqzqtz_tzzrjsr","dqzqtz_cstzcb",
				"dqzqtz_tzzrcb","dqzqtz_tzzrsd"); //存放短期债权数据LIST
		for(int i=0;i<dqzqtz.size();i++){
			HashMap map=(HashMap)dqzqtz.get(i);
			if(dqzqtz.size()==1){
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("11");
				item_1.setItemValue((String)map.get("dqzqtz_tzzczl"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("12");
				item_2.setItemValue((String)map.get("dqzqtz_tzsy"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("13");
				item_3.setItemValue((String)map.get("dqzqtz_tzzrjsr"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);	
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("14");
				item_4.setItemValue((String)map.get("dqzqtz_cstzcb"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
				QysdsReportsItemDeclare item_5=new QysdsReportsItemDeclare();
				item_5.setItemID("15");
				item_5.setItemValue((String)map.get("dqzqtz_tzzrcb"));
				item_5.setItemType("11");
				table.getCellContentList().put(item_5.getItemID(),item_5);
				
				QysdsReportsItemDeclare item_6=new QysdsReportsItemDeclare();
				item_6.setItemID("16");
				item_6.setItemValue((String)map.get("dqzqtz_tzzrsd"));
				item_6.setItemType("11");
				table.getCellContentList().put(item_6.getItemID(),item_6);	
			}else{
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("11"+"."+String.valueOf(i+1));
				item_1.setItemValue((String)map.get("dqzqtz_tzzczl"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("12"+"."+String.valueOf(i+1));
				item_2.setItemValue((String)map.get("dqzqtz_tzsy"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("13"+"."+String.valueOf(i+1));
				item_3.setItemValue((String)map.get("dqzqtz_tzzrjsr"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);	
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("14"+"."+String.valueOf(i+1));
				item_4.setItemValue((String)map.get("dqzqtz_cstzcb"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
				QysdsReportsItemDeclare item_5=new QysdsReportsItemDeclare();
				item_5.setItemID("15"+"."+String.valueOf(i+1));
				item_5.setItemValue((String)map.get("dqzqtz_tzzrcb"));
				item_5.setItemType("11");
				table.getCellContentList().put(item_5.getItemID(),item_5);
				
				QysdsReportsItemDeclare item_6=new QysdsReportsItemDeclare();
				item_6.setItemID("16"+"."+String.valueOf(i+1));
				item_6.setItemValue((String)map.get("dqzqtz_tzzrsd"));
				item_6.setItemType("11");
				table.getCellContentList().put(item_6.getItemID(),item_6);
			}
		}
		/**
		 * 翻译长期债权投资中的数据
		 */
		
		List cqzqtz=this.filterList2(form.getCqzqtzList(),"cqzqtz_tzzczl",
				"cqzqtz_tzsy","cqzqtz_tzzrjsr","cqzqtz_cstzcb","cqzqtz_jscbtz",
				"cqzqtz_tzzrcb","cqzqtz_tzzrsd"); //存放长期债权投资数据LIST
		for(int i=0;i<cqzqtz.size();i++){
			HashMap map=(HashMap)cqzqtz.get(i);
			if(cqzqtz.size()==1){
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("23");
				item_1.setItemValue((String)map.get("cqzqtz_tzzczl"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("24");
				item_2.setItemValue((String)map.get("cqzqtz_tzsy"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("25");
				item_3.setItemValue((String)map.get("cqzqtz_tzzrjsr"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);	
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("26");
				item_4.setItemValue((String)map.get("cqzqtz_cstzcb"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
				QysdsReportsItemDeclare item_5=new QysdsReportsItemDeclare();
				item_5.setItemID("27");
				item_5.setItemValue((String)map.get("cqzqtz_jscbtz"));
				item_5.setItemType("11");
				table.getCellContentList().put(item_5.getItemID(),item_5);
				
				QysdsReportsItemDeclare item_6=new QysdsReportsItemDeclare();
				item_6.setItemID("28");
				item_6.setItemValue((String)map.get("cqzqtz_tzzrcb"));
				item_6.setItemType("11");
				table.getCellContentList().put(item_6.getItemID(),item_6);	
				
				QysdsReportsItemDeclare item_7=new QysdsReportsItemDeclare();
				item_7.setItemID("29");
				item_7.setItemValue((String)map.get("cqzqtz_tzzrsd"));
				item_7.setItemType("11");
				table.getCellContentList().put(item_7.getItemID(),item_7);
			}else{
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("23"+"."+String.valueOf(i+1));
				item_1.setItemValue((String)map.get("cqzqtz_tzzczl"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("24"+"."+String.valueOf(i+1));
				item_2.setItemValue((String)map.get("cqzqtz_tzsy"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("25"+"."+String.valueOf(i+1));
				item_3.setItemValue((String)map.get("cqzqtz_tzzrjsr"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);	
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("26"+"."+String.valueOf(i+1));
				item_4.setItemValue((String)map.get("cqzqtz_cstzcb"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
				QysdsReportsItemDeclare item_5=new QysdsReportsItemDeclare();
				item_5.setItemID("27"+"."+String.valueOf(i+1));
				item_5.setItemValue((String)map.get("cqzqtz_jscbtz"));
				item_5.setItemType("11");
				table.getCellContentList().put(item_5.getItemID(),item_5);
				
				QysdsReportsItemDeclare item_6=new QysdsReportsItemDeclare();
				item_6.setItemID("28"+"."+String.valueOf(i+1));
				item_6.setItemValue((String)map.get("cqzqtz_tzzrcb"));
				item_6.setItemType("11");
				table.getCellContentList().put(item_6.getItemID(),item_6);	
				
				QysdsReportsItemDeclare item_7=new QysdsReportsItemDeclare();
				item_7.setItemID("29"+"."+String.valueOf(i+1));
				item_7.setItemValue((String)map.get("cqzqtz_tzzrsd"));
				item_7.setItemType("11");
				table.getCellContentList().put(item_7.getItemID(),item_7);
			}
			
		}
		/**
		 * 翻译短期股权投资中的数据
		 */
		
		List dqgqtz=this.filterList(form.getDqgqtzList(),"dqgqtz_tzzczl", "dqgqtz_btzqyszd", "dqgqtz_qybl",
				"dqgqtz_sl","dqgqtz_tzsy","dqgqtz_ynqysds","dqgqtz_tzzrjsr","dqgqtz_cstzcb","dqgqtz_jscbtz",
				"dqgqtz_tzzrcb","dqgqtz_tzzrsd"); //存放短期股权投资数据LIST
		for(int i=0;i<dqgqtz.size();i++){
			HashMap map=(HashMap)dqgqtz.get(i);
			if(dqgqtz.size()==1){
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("44");
				item_1.setItemValue((String)map.get("dqgqtz_tzzczl"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("45");
				item_2.setItemValue((String)map.get("dqgqtz_btzqyszd"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("46");
				item_3.setItemValue((String)map.get("dqgqtz_qybl"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);	
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("47");
				item_4.setItemValue((String)map.get("dqgqtz_sl"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
				QysdsReportsItemDeclare item_5=new QysdsReportsItemDeclare();
				item_5.setItemID("48");
				item_5.setItemValue((String)map.get("dqgqtz_tzsy"));
				item_5.setItemType("11");
				table.getCellContentList().put(item_5.getItemID(),item_5);
				
				QysdsReportsItemDeclare item_6=new QysdsReportsItemDeclare();
				item_6.setItemID("49");
				item_6.setItemValue((String)map.get("dqgqtz_ynqysds"));
				item_6.setItemType("11");
				table.getCellContentList().put(item_6.getItemID(),item_6);	
				
				QysdsReportsItemDeclare item_7=new QysdsReportsItemDeclare();
				item_7.setItemID("50");
				item_7.setItemValue((String)map.get("dqgqtz_tzzrjsr"));
				item_7.setItemType("11");
				table.getCellContentList().put(item_7.getItemID(),item_7);
				
				QysdsReportsItemDeclare item_8=new QysdsReportsItemDeclare();
				item_8.setItemID("51");
				item_8.setItemValue((String)map.get("dqgqtz_cstzcb"));
				item_8.setItemType("11");
				table.getCellContentList().put(item_8.getItemID(),item_8);	
				
				QysdsReportsItemDeclare item_9=new QysdsReportsItemDeclare();
				item_9.setItemID("52");
				item_9.setItemValue((String)map.get("dqgqtz_jscbtz"));
				item_9.setItemType("11");
				table.getCellContentList().put(item_9.getItemID(),item_9);	
				
				QysdsReportsItemDeclare item_10=new QysdsReportsItemDeclare();
				item_10.setItemID("53");
				item_10.setItemValue((String)map.get("dqgqtz_tzzrcb"));
				item_10.setItemType("11");
				table.getCellContentList().put(item_10.getItemID(),item_10);
				
				QysdsReportsItemDeclare item_11=new QysdsReportsItemDeclare();
				item_11.setItemID("54");
				item_11.setItemValue((String)map.get("dqgqtz_tzzrsd"));
				item_11.setItemType("11");
				table.getCellContentList().put(item_11.getItemID(),item_11);
				
			}else{
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("44"+"."+String.valueOf(i+1));
				item_1.setItemValue((String)map.get("dqgqtz_tzzczl"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("45"+"."+String.valueOf(i+1));
				item_2.setItemValue((String)map.get("dqgqtz_btzqyszd"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("46"+"."+String.valueOf(i+1));
				item_3.setItemValue((String)map.get("dqgqtz_qybl"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);	
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("47"+"."+String.valueOf(i+1));
				item_4.setItemValue((String)map.get("dqgqtz_sl"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
				QysdsReportsItemDeclare item_5=new QysdsReportsItemDeclare();
				item_5.setItemID("48"+"."+String.valueOf(i+1));
				item_5.setItemValue((String)map.get("dqgqtz_tzsy"));
				item_5.setItemType("11");
				table.getCellContentList().put(item_5.getItemID(),item_5);
				
				QysdsReportsItemDeclare item_6=new QysdsReportsItemDeclare();
				item_6.setItemID("49"+"."+String.valueOf(i+1));
				item_6.setItemValue((String)map.get("dqgqtz_ynqysds"));
				item_6.setItemType("11");
				table.getCellContentList().put(item_6.getItemID(),item_6);	
				
				QysdsReportsItemDeclare item_7=new QysdsReportsItemDeclare();
				item_7.setItemID("50"+"."+String.valueOf(i+1));
				item_7.setItemValue((String)map.get("dqgqtz_tzzrjsr"));
				item_7.setItemType("11");
				table.getCellContentList().put(item_7.getItemID(),item_7);
				
				QysdsReportsItemDeclare item_8=new QysdsReportsItemDeclare();
				item_8.setItemID("51"+"."+String.valueOf(i+1));
				item_8.setItemValue((String)map.get("dqgqtz_cstzcb"));
				item_8.setItemType("11");
				table.getCellContentList().put(item_8.getItemID(),item_8);	
				
				QysdsReportsItemDeclare item_9=new QysdsReportsItemDeclare();
				item_9.setItemID("52"+"."+String.valueOf(i+1));
				item_9.setItemValue((String)map.get("dqgqtz_jscbtz"));
				item_9.setItemType("11");
				table.getCellContentList().put(item_9.getItemID(),item_9);	
				
				QysdsReportsItemDeclare item_10=new QysdsReportsItemDeclare();
				item_10.setItemID("53"+"."+String.valueOf(i+1));
				item_10.setItemValue((String)map.get("dqgqtz_tzzrcb"));
				item_10.setItemType("11");
				table.getCellContentList().put(item_10.getItemID(),item_10);
				
				QysdsReportsItemDeclare item_11=new QysdsReportsItemDeclare();
				item_11.setItemID("54"+"."+String.valueOf(i+1));
				item_11.setItemValue((String)map.get("dqgqtz_tzzrsd"));
				item_11.setItemType("11");
				table.getCellContentList().put(item_11.getItemID(),item_11);
			}
			
		}
		/**
		 * 翻译长期股权投资中的数据
		 */
		List cqgqtz=this.filterList(form.getCqgqtzList(),"cqgqtz_tzzczl", "cqgqtz_btzqyszd", "cqgqtz_qybl",
				"cqgqtz_sl","cqgqtz_tzsy","cqgqtz_ynqysds","cqgqtz_tzzrjsr","cqgqtz_cstzcb","cqgqtz_jscbtz",
				"cqgqtz_tzzrcb","cqgqtz_tzzrsd"); //存放长期股权投资数据LIST
		for(int i=0;i<cqgqtz.size();i++){
			HashMap map=(HashMap)cqgqtz.get(i);
			if(cqgqtz.size()==1){
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("62");
				item_1.setItemValue((String)map.get("cqgqtz_tzzczl"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("63");
				item_2.setItemValue((String)map.get("cqgqtz_btzqyszd"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("64");
				item_3.setItemValue((String)map.get("cqgqtz_qybl"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);	
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("65");
				item_4.setItemValue((String)map.get("cqgqtz_sl"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
				QysdsReportsItemDeclare item_5=new QysdsReportsItemDeclare();
				item_5.setItemID("66");
				item_5.setItemValue((String)map.get("cqgqtz_tzsy"));
				item_5.setItemType("11");
				table.getCellContentList().put(item_5.getItemID(),item_5);
				
				QysdsReportsItemDeclare item_6=new QysdsReportsItemDeclare();
				item_6.setItemID("67");
				item_6.setItemValue((String)map.get("cqgqtz_ynqysds"));
				item_6.setItemType("11");
				table.getCellContentList().put(item_6.getItemID(),item_6);	
				
				QysdsReportsItemDeclare item_7=new QysdsReportsItemDeclare();
				item_7.setItemID("68");
				item_7.setItemValue((String)map.get("cqgqtz_tzzrjsr"));
				item_7.setItemType("11");
				table.getCellContentList().put(item_7.getItemID(),item_7);
				
				QysdsReportsItemDeclare item_8=new QysdsReportsItemDeclare();
				item_8.setItemID("69");
				item_8.setItemValue((String)map.get("cqgqtz_cstzcb"));
				item_8.setItemType("11");
				table.getCellContentList().put(item_8.getItemID(),item_8);	
				
				QysdsReportsItemDeclare item_9=new QysdsReportsItemDeclare();
				item_9.setItemID("70");
				item_9.setItemValue((String)map.get("cqgqtz_jscbtz"));
				item_9.setItemType("11");
				table.getCellContentList().put(item_9.getItemID(),item_9);	
				
				QysdsReportsItemDeclare item_10=new QysdsReportsItemDeclare();
				item_10.setItemID("71");
				item_10.setItemValue((String)map.get("cqgqtz_tzzrcb"));
				item_10.setItemType("11");
				table.getCellContentList().put(item_10.getItemID(),item_10);
				
				QysdsReportsItemDeclare item_11=new QysdsReportsItemDeclare();
				item_11.setItemID("72");
				item_11.setItemValue((String)map.get("cqgqtz_tzzrsd"));
				item_11.setItemType("11");
				table.getCellContentList().put(item_11.getItemID(),item_11);
				
			}else{
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("62"+"."+String.valueOf(i+1));
				item_1.setItemValue((String)map.get("cqgqtz_tzzczl"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("63"+"."+String.valueOf(i+1));
				item_2.setItemValue((String)map.get("cqgqtz_btzqyszd"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("64"+"."+String.valueOf(i+1));
				item_3.setItemValue((String)map.get("cqgqtz_qybl"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);	
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("65"+"."+String.valueOf(i+1));
				item_4.setItemValue((String)map.get("cqgqtz_sl"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
				QysdsReportsItemDeclare item_5=new QysdsReportsItemDeclare();
				item_5.setItemID("66"+"."+String.valueOf(i+1));
				item_5.setItemValue((String)map.get("cqgqtz_tzsy"));
				item_5.setItemType("11");
				table.getCellContentList().put(item_5.getItemID(),item_5);
				
				QysdsReportsItemDeclare item_6=new QysdsReportsItemDeclare();
				item_6.setItemID("67"+"."+String.valueOf(i+1));
				item_6.setItemValue((String)map.get("cqgqtz_ynqysds"));
				item_6.setItemType("11");
				table.getCellContentList().put(item_6.getItemID(),item_6);	
				
				QysdsReportsItemDeclare item_7=new QysdsReportsItemDeclare();
				item_7.setItemID("68"+"."+String.valueOf(i+1));
				item_7.setItemValue((String)map.get("cqgqtz_tzzrjsr"));
				item_7.setItemType("11");
				table.getCellContentList().put(item_7.getItemID(),item_7);
				
				QysdsReportsItemDeclare item_8=new QysdsReportsItemDeclare();
				item_8.setItemID("69"+"."+String.valueOf(i+1));
				item_8.setItemValue((String)map.get("cqgqtz_cstzcb"));
				item_8.setItemType("11");
				table.getCellContentList().put(item_8.getItemID(),item_8);	
				
				QysdsReportsItemDeclare item_9=new QysdsReportsItemDeclare();
				item_9.setItemID("70"+"."+String.valueOf(i+1));
				item_9.setItemValue((String)map.get("cqgqtz_jscbtz"));
				item_9.setItemType("11");
				table.getCellContentList().put(item_9.getItemID(),item_9);	
				
				QysdsReportsItemDeclare item_10=new QysdsReportsItemDeclare();
				item_10.setItemID("71"+"."+String.valueOf(i+1));
				item_10.setItemValue((String)map.get("cqgqtz_tzzrcb"));
				item_10.setItemType("11");
				table.getCellContentList().put(item_10.getItemID(),item_10);
				
				QysdsReportsItemDeclare item_11=new QysdsReportsItemDeclare();
				item_11.setItemID("72"+"."+String.valueOf(i+1));
				item_11.setItemValue((String)map.get("cqgqtz_tzzrsd"));
				item_11.setItemType("11");
				table.getCellContentList().put(item_11.getItemID(),item_11);	
			}
			
		}
		
		report.getTableContentList().put(table.getTableId(),QysdsNewUtil.cleanSpace(table));
		
		
		//ceshi
		Iterator it= table.getCellContentList().keySet().iterator();
		while(it.hasNext())
		{
			QysdsReportsItemDeclare item=(QysdsReportsItemDeclare)table.getCellContentList().get((String)it.next());
			System.out.println(item.getItemID()+"  ------------- "+item.getItemValue());
		}
		return report;
	}
	
	/**
	 * 将接口数据结构中的数据转换置入页面要求的数据结构
	 * 接口数据结构-->页面数据结构
	 * @param QysdsReportsTableDeclare
	 * @return 企业所得税报表申明对象
	 */
	private HashMap translate2Page(QysdsReportsTableDeclare table) {
		
		HashMap map1 = new HashMap();
		HashMap map2 = new HashMap();
		HashMap map3 = new HashMap();
		HashMap map4 = new HashMap();
		HashMap map5 = new HashMap();
		HashMap map6 = new HashMap();
		HashMap map7 = new HashMap();
		
		ArrayList pagelist = new ArrayList(); //存放固定行数据LIST
		
		map1.put("hc", "1");
		map1.put("btzqyszd", "*");
		map1.put("qybl", "*");
		map1.put("sl", "*");
		map1.put("tzsy", ((QysdsReportsItemDeclare)table.getCellContentList().get("1")).getItemValue());
		map1.put("ynqysds", "*");
		map1.put("tzzrjsr", ((QysdsReportsItemDeclare)table.getCellContentList().get("2")).getItemValue());
		map1.put("cstzcb", ((QysdsReportsItemDeclare)table.getCellContentList().get("3")).getItemValue());
		map1.put("jscbtz", "*");
		map1.put("tzzrcb", ((QysdsReportsItemDeclare)table.getCellContentList().get("4")).getItemValue());
		map1.put("tzzrsd", ((QysdsReportsItemDeclare)table.getCellContentList().get("5")).getItemValue());
		pagelist.add(map1);
		
		map2.put("hc", "2");
		map2.put("btzqyszd", "*");
		map2.put("qybl", "*");
		map2.put("sl", "*");
		map2.put("tzsy", ((QysdsReportsItemDeclare)table.getCellContentList().get("6")).getItemValue());
		map2.put("ynqysds", "");
		map2.put("tzzrjsr", ((QysdsReportsItemDeclare)table.getCellContentList().get("7")).getItemValue());
		map2.put("cstzcb", ((QysdsReportsItemDeclare)table.getCellContentList().get("8")).getItemValue());
		map2.put("jscbtz", "*");
		map2.put("tzzrcb", ((QysdsReportsItemDeclare)table.getCellContentList().get("9")).getItemValue());
		map2.put("tzzrsd", ((QysdsReportsItemDeclare)table.getCellContentList().get("10")).getItemValue());
		pagelist.add(map2);
		
		map3.put("hc", "3");
		map3.put("btzqyszd", "*");
		map3.put("qybl", "*");
		map3.put("sl", "*");
		map3.put("tzsy", ((QysdsReportsItemDeclare)table.getCellContentList().get("17")).getItemValue());
		map3.put("ynqysds", "*");
		map3.put("tzzrjsr", ((QysdsReportsItemDeclare)table.getCellContentList().get("18")).getItemValue());
		map3.put("cstzcb", ((QysdsReportsItemDeclare)table.getCellContentList().get("19")).getItemValue());
		map3.put("jscbtz", ((QysdsReportsItemDeclare)table.getCellContentList().get("20")).getItemValue());
		map3.put("tzzrcb", ((QysdsReportsItemDeclare)table.getCellContentList().get("21")).getItemValue());
		map3.put("tzzrsd", ((QysdsReportsItemDeclare)table.getCellContentList().get("22")).getItemValue());
		pagelist.add(map3);
		
		map4.put("hc", "4");
		map4.put("btzqyszd", "*");
		map4.put("qybl", "*");
		map4.put("sl", "*");
		map4.put("tzsy", ((QysdsReportsItemDeclare)table.getCellContentList().get("30")).getItemValue());
		map4.put("ynqysds", ((QysdsReportsItemDeclare)table.getCellContentList().get("31")).getItemValue());
		map4.put("tzzrjsr", ((QysdsReportsItemDeclare)table.getCellContentList().get("32")).getItemValue());
		map4.put("cstzcb", ((QysdsReportsItemDeclare)table.getCellContentList().get("33")).getItemValue());
		map4.put("jscbtz", ((QysdsReportsItemDeclare)table.getCellContentList().get("34")).getItemValue());
		map4.put("tzzrcb", ((QysdsReportsItemDeclare)table.getCellContentList().get("35")).getItemValue());
		map4.put("tzzrsd", ((QysdsReportsItemDeclare)table.getCellContentList().get("36")).getItemValue());
		pagelist.add(map4);
		
		map5.put("hc", "5");
		map5.put("btzqyszd", "*");
		map5.put("qybl", "*");
		map5.put("sl", "*");
		map5.put("tzsy", ((QysdsReportsItemDeclare)table.getCellContentList().get("37")).getItemValue());
		map5.put("ynqysds", ((QysdsReportsItemDeclare)table.getCellContentList().get("38")).getItemValue());
		map5.put("tzzrjsr", ((QysdsReportsItemDeclare)table.getCellContentList().get("39")).getItemValue());
		map5.put("cstzcb", ((QysdsReportsItemDeclare)table.getCellContentList().get("40")).getItemValue());
		map5.put("jscbtz", ((QysdsReportsItemDeclare)table.getCellContentList().get("41")).getItemValue());
		map5.put("tzzrcb", ((QysdsReportsItemDeclare)table.getCellContentList().get("42")).getItemValue());
		map5.put("tzzrsd", ((QysdsReportsItemDeclare)table.getCellContentList().get("43")).getItemValue());
		pagelist.add(map5);
		
		map6.put("hc", "6");
		map6.put("btzqyszd", "*");
		map6.put("qybl", "*");
		map6.put("sl", "*");
		map6.put("tzsy", ((QysdsReportsItemDeclare)table.getCellContentList().get("55")).getItemValue());
		map6.put("ynqysds", ((QysdsReportsItemDeclare)table.getCellContentList().get("56")).getItemValue());
		map6.put("tzzrjsr", ((QysdsReportsItemDeclare)table.getCellContentList().get("57")).getItemValue());
		map6.put("cstzcb", ((QysdsReportsItemDeclare)table.getCellContentList().get("58")).getItemValue());
		map6.put("jscbtz", ((QysdsReportsItemDeclare)table.getCellContentList().get("59")).getItemValue());
		map6.put("tzzrcb", ((QysdsReportsItemDeclare)table.getCellContentList().get("60")).getItemValue());
		map6.put("tzzrsd", ((QysdsReportsItemDeclare)table.getCellContentList().get("61")).getItemValue());
		pagelist.add(map6);
		
		map7.put("hc", "7");
		map7.put("btzqyszd", "*");
		map7.put("qybl", "*");
		map7.put("sl", "*");
		map7.put("tzsy", ((QysdsReportsItemDeclare)table.getCellContentList().get("73")).getItemValue());
		map7.put("ynqysds", ((QysdsReportsItemDeclare)table.getCellContentList().get("74")).getItemValue());
		map7.put("tzzrjsr", ((QysdsReportsItemDeclare)table.getCellContentList().get("75")).getItemValue());
		map7.put("cstzcb", ((QysdsReportsItemDeclare)table.getCellContentList().get("76")).getItemValue());
		map7.put("jscbtz", "*");
		map7.put("tzzrcb", ((QysdsReportsItemDeclare)table.getCellContentList().get("77")).getItemValue());
		map7.put("tzzrsd", "*");
		pagelist.add(map7);
		
		/**
		 * 翻译补充资料数据
		 */
		
		String value78= ((QysdsReportsItemDeclare)table.getCellContentList().get("78")).getItemValue();
		String value79= ((QysdsReportsItemDeclare)table.getCellContentList().get("79")).getItemValue();
		String value80= ((QysdsReportsItemDeclare)table.getCellContentList().get("80")).getItemValue();
		String value81= ((QysdsReportsItemDeclare)table.getCellContentList().get("81")).getItemValue();
		//拆分table.getCellContentList()
		Map dqzq_1 =this.getCellMap(table, "11");
		Map dqzq_2 =this.getCellMap(table, "12");
		Map dqzq_3 =this.getCellMap(table, "13");
		Map dqzq_4 =this.getCellMap(table, "14");
		Map dqzq_5 =this.getCellMap(table, "15");
		Map dqzq_6 =this.getCellMap(table, "16");
		
		
		
		Map cqzq_1 =this.getCellMap(table, "23");
		Map cqzq_2 =this.getCellMap(table, "24");
		Map cqzq_3 =this.getCellMap(table, "25");
		Map cqzq_4 =this.getCellMap(table, "26");
		Map cqzq_5 =this.getCellMap(table, "27");
		Map cqzq_6 =this.getCellMap(table, "28");
		Map cqzq_7 =this.getCellMap(table, "29");
		
		
		
		Map dqgq_1 =this.getCellMap(table, "44");
		Map dqgq_2 =this.getCellMap(table, "45");
		Map dqgq_3 =this.getCellMap(table, "46");
		Map dqgq_4 =this.getCellMap(table, "47");
		Map dqgq_5 =this.getCellMap(table, "48");
		Map dqgq_6 =this.getCellMap(table, "49");
		Map dqgq_7 =this.getCellMap(table, "50");
		Map dqgq_8 =this.getCellMap(table, "51");
		Map dqgq_9 =this.getCellMap(table, "52");
		Map dqgq_10 =this.getCellMap(table, "53");
		Map dqgq_11 =this.getCellMap(table, "54");
		
		
		Map cqgq_1 =this.getCellMap(table, "62");
		Map cqgq_2 =this.getCellMap(table, "63");
		Map cqgq_3 =this.getCellMap(table, "64");
		Map cqgq_4 =this.getCellMap(table, "65");
		Map cqgq_5 =this.getCellMap(table, "66");
		Map cqgq_6 =this.getCellMap(table, "67");
		Map cqgq_7 =this.getCellMap(table, "68");
		Map cqgq_8 =this.getCellMap(table, "69");
		Map cqgq_9 =this.getCellMap(table, "70");
		Map cqgq_10 =this.getCellMap(table, "71");
		Map cqgq_11 =this.getCellMap(table, "72");
		
		
		
		//处理为等长度Map
		List dqzq=this.processMap1(dqzq_1, dqzq_2, dqzq_3,dqzq_4,dqzq_5,dqzq_6,
				"11","12","13","14","15","16",
				"dqzqtz_tzzczl","dqzqtz_btzqyszd","dqzqtz_qybl","dqzqtz_sl",
				"dqzqtz_tzsy","dqzqtz_ynqysds","dqzqtz_tzzrjsr",
				"dqzqtz_cstzcb","dqzqtz_jscbtz","dqzqtz_tzzrcb","dqzqtz_tzzrsd");
		List cqzq=this.processMap2(cqzq_1, cqzq_2, cqzq_3,cqzq_4,cqzq_5,cqzq_6,cqzq_7,
				"23","24","25","26","27","28","29",
				"cqzqtz_tzzczl","cqzqtz_btzqyszd","cqzqtz_qybl","cqzqtz_sl",
				"cqzqtz_tzsy","cqzqtz_ynqysds","cqzqtz_tzzrjsr",
				"cqzqtz_cstzcb","cqzqtz_jscbtz","cqzqtz_tzzrcb","cqzqtz_tzzrsd");
		List dqgq=this.processMap(dqgq_1, dqgq_2, dqgq_3,dqgq_4,dqgq_5,dqgq_6,
				dqgq_7, dqgq_8, dqgq_9,dqgq_10,dqgq_11,
				"44","45","46","47","48","49","50","51","52","53","54",
				"dqgqtz_tzzczl","dqgqtz_btzqyszd","dqgqtz_qybl","dqgqtz_sl",
				"dqgqtz_tzsy","dqgqtz_ynqysds","dqgqtz_tzzrjsr",
				"dqgqtz_cstzcb","dqgqtz_jscbtz","dqgqtz_tzzrcb","dqgqtz_tzzrsd");
		List cqgq=this.processMap(cqgq_1, cqgq_2, cqgq_3,cqgq_4,cqgq_5,cqgq_6,
				cqgq_7, cqgq_8, cqgq_9,cqgq_10,cqgq_11,
				"62","63","64","65","66","67","68","69","70","71","72",
				"cqgqtz_tzzczl","cqgqtz_btzqyszd","cqgqtz_qybl","cqgqtz_sl",
				"cqgqtz_tzsy","cqgqtz_ynqysds","cqgqtz_tzzrjsr",
				"cqgqtz_cstzcb","cqgqtz_jscbtz","cqgqtz_tzzrcb","cqgqtz_tzzrsd" );
		
		
		HashMap totalmap = new HashMap();
		totalmap.put("pagelist", pagelist);
		totalmap.put("dqzq", dqzq);
		totalmap.put("cqzq", cqzq);
		totalmap.put("dqgq", dqgq);
		totalmap.put("cqgq", cqgq);
		totalmap.put("value78", value78);
		totalmap.put("value79", value79);
		totalmap.put("value80", value80);
		totalmap.put("value81", value81);
		
		return totalmap;
	}
	
	
	//按子行单元格进行分类,返回子行单元格对应的map
	private HashMap getCellMap(QysdsReportsTableDeclare table,String flag){
		HashMap map = new HashMap();		
		Iterator it = table.getCellContentList().keySet().iterator();
		
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				if(flag.equals(key)){
					map.put(key, table.getCellContentList().get(key));
				}
			}else if(flag.equals(key.substring(0, key.indexOf(".")))){
				String est=key.substring(0, key.indexOf("."));
				map.put(key, table.getCellContentList().get(key));			
			}						
			
		}
		return map;
		
	}
	
	//此方法返回子行对应的List
	private List processMap(Map map1,Map map2,Map map3,Map map4,Map map5,Map map6,Map map7,Map map8,
			Map map9,Map map10,Map map11,
			String keyFlag1,String keyFlag2,String keyFlag3,String keyFlag4,String keyFlag5,String keyFlag6,
			String keyFlag7,String keyFlag8,String keyFlag9,
			String keyFlag10,String keyFlag11,
			String L1,String L2,String L3,
			String L4,String L5,String L6,String L7,String L8,String L9,
			String L10,String L11){
		
		boolean flagMuti=false;
		List list=new ArrayList();
		
		Iterator it = map1.keySet().iterator();
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map2.keySet().iterator();
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map3.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map4.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map5.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map6.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map7.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map8.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map9.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map10.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map11.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		//如果下列判断条件为真,则子行里内容全部为空
		if(flagMuti==false && map1.size()==0 && map2.size()==0 && map3.size()==0 && 
				map4.size()==0 && map5.size()==0 && map6.size()==0 &&
				map7.size()==0 && map8.size()==0 && map9.size()==0 &&
				map10.size()==0 && map11.size()==0){
			/**
			 * @todo
			 * 往2个map里放入
			 */
			for (int i=0;i<2;i++){
				Map rowMap=new HashMap();
				rowMap.put(L1, "");
				rowMap.put(L2, "");
				rowMap.put(L3, "");
				rowMap.put(L4, "");
				rowMap.put(L5, "");
				rowMap.put(L6, "");
				rowMap.put(L7, "");
				rowMap.put(L8, "");
				rowMap.put(L9, "");
				rowMap.put(L10, "");
				rowMap.put(L11, "");
				list.add(rowMap);
			}		
			return list;			
			
			//如果满足此判断条件,则子行只有一行
		}else if(flagMuti==false){
			//放一条数据和1条空行
			Map rowMap=new HashMap();
			rowMap.put(L1, map1.get(keyFlag1)==null?"":((QysdsReportsItemDeclare)map1.get(keyFlag1)).getItemValue());
			rowMap.put(L2, map2.get(keyFlag2)==null?"":((QysdsReportsItemDeclare)map2.get(keyFlag2)).getItemValue());
			rowMap.put(L3, map3.get(keyFlag3)==null?"":((QysdsReportsItemDeclare)map3.get(keyFlag3)).getItemValue());
			rowMap.put(L4,map4.get(keyFlag4)==null?"":((QysdsReportsItemDeclare)map4.get(keyFlag4)).getItemValue() );
			rowMap.put(L5, map5.get(keyFlag5)==null?"":((QysdsReportsItemDeclare)map5.get(keyFlag5)).getItemValue());
			rowMap.put(L6,map6.get(keyFlag6)==null?"":((QysdsReportsItemDeclare)map6.get(keyFlag6)).getItemValue() );
			rowMap.put(L7, map7.get(keyFlag7)==null?"":((QysdsReportsItemDeclare)map7.get(keyFlag7)).getItemValue());
			rowMap.put(L8, map8.get(keyFlag8)==null?"":((QysdsReportsItemDeclare)map8.get(keyFlag8)).getItemValue());
			rowMap.put(L9,map9.get(keyFlag9)==null?"":((QysdsReportsItemDeclare)map9.get(keyFlag9)).getItemValue() );
			rowMap.put(L10, map10.get(keyFlag10)==null?"":((QysdsReportsItemDeclare)map10.get(keyFlag10)).getItemValue());
			rowMap.put(L11,map11.get(keyFlag11)==null?"":((QysdsReportsItemDeclare)map11.get(keyFlag11)).getItemValue() );
			
			list.add(rowMap);
			
			Map rowMap1=new HashMap();
			rowMap1.put(L1, "");
			rowMap1.put(L2, "");
			rowMap1.put(L3, "");
			rowMap1.put(L4, "");
			rowMap1.put(L5, "");
			rowMap1.put(L6, "");
			rowMap1.put(L7, "");
			rowMap1.put(L8, "");
			rowMap1.put(L9, "");
			rowMap1.put(L10, "");
			rowMap1.put(L11, "");
			list.add(rowMap1);
			
			return list;
		}
		
		//如果满足下列判断条件,则子行为多行 
		if(flagMuti==true){
			int max=0;
			String[] arr;
			String temp=new String("0");
			//取map1中下标的最大值,即子行的数目
			if(map1.size()!=0){
				it = map1.keySet().iterator();
				while (it.hasNext()) {
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				max=getMax(arr);
			}
			//取map2中下标的最大值,即子行的数目
			if(map2.size()!=0){
				it = map2.keySet().iterator();
				while (it.hasNext()) {
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max2=this.getMax(arr);
				if (max<=max2){
					max=max2;
				}
			}
			
			if(map3.size()!=0)
			{
				it = map3.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max3=this.getMax(arr);
				if (max<=max3)
				{
					max=max3;
				}
			}
			if(map4.size()!=0)
			{
				it = map4.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max4=this.getMax(arr);
				if (max<=max4)
				{
					max=max4;
				}
			}
			if(map5.size()!=0)
			{
				it = map5.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max5=this.getMax(arr);
				if (max<=max5)
				{
					max=max5;
				}
			}
			if(map6.size()!=0)
			{
				it = map6.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max6=this.getMax(arr);
				if (max<=max6)
				{
					max=max6;
				}
			}
			if(map7.size()!=0)
			{
				it = map7.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max7=this.getMax(arr);
				if (max<=max7)
				{
					max=max7;
				}
			}
			if(map8.size()!=0)
			{
				it = map8.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max8=this.getMax(arr);
				if (max<=max8)
				{
					max=max8;
				}
			}
			if(map9.size()!=0)
			{
				it = map9.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max9=this.getMax(arr);
				if (max<=max9)
				{
					max=max9;
				}
			}
			if(map10.size()!=0)
			{
				it = map10.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max10=this.getMax(arr);
				if (max<=max10)
				{
					max=max10;
				}
			}
			if(map11.size()!=0)
			{
				it = map11.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max11=this.getMax(arr);
				if (max<=max11)
				{
					max=max11;
				}
			}
			//插入子行的数据
			for(int i=0;i<max;i++){
				Map rowMap=new HashMap();
				String key1=keyFlag1+"."+String.valueOf(i+1);
				String key2=keyFlag2+"."+String.valueOf(i+1);
				String key3=keyFlag3+"."+String.valueOf(i+1);
				String key4=keyFlag4+"."+String.valueOf(i+1);
				String key5=keyFlag5+"."+String.valueOf(i+1);
				String key6=keyFlag6+"."+String.valueOf(i+1);
				String key7=keyFlag7+"."+String.valueOf(i+1);
				String key8=keyFlag8+"."+String.valueOf(i+1);
				String key9=keyFlag9+"."+String.valueOf(i+1);
				String key10=keyFlag10+"."+String.valueOf(i+1);
				String key11=keyFlag11+"."+String.valueOf(i+1);
				
				rowMap.put(L1, map1.get(key1)==null?"":((QysdsReportsItemDeclare)map1.get(key1)).getItemValue());
				rowMap.put(L2, map2.get(key2)==null?"":((QysdsReportsItemDeclare)map2.get(key2)).getItemValue());
				rowMap.put(L3, map3.get(key3)==null?"":((QysdsReportsItemDeclare)map3.get(key3)).getItemValue());
				rowMap.put(L4, map4.get(key4)==null?"":((QysdsReportsItemDeclare)map4.get(key4)).getItemValue());
				rowMap.put(L5, map5.get(key5)==null?"":((QysdsReportsItemDeclare)map5.get(key5)).getItemValue());
				rowMap.put(L6, map6.get(key6)==null?"":((QysdsReportsItemDeclare)map6.get(key6)).getItemValue());
				rowMap.put(L7, map7.get(key7)==null?"":((QysdsReportsItemDeclare)map7.get(key7)).getItemValue());
				rowMap.put(L8, map8.get(key8)==null?"":((QysdsReportsItemDeclare)map8.get(key8)).getItemValue());
				rowMap.put(L9, map9.get(key9)==null?"":((QysdsReportsItemDeclare)map9.get(key9)).getItemValue());
				rowMap.put(L10, map10.get(key10)==null?"":((QysdsReportsItemDeclare)map10.get(key10)).getItemValue());
				
				rowMap.put(L11, map11.get(key11)==null?"":((QysdsReportsItemDeclare)map11.get(key11)).getItemValue());
				
				
				list.add(rowMap);
				
			}			
		}
		
		return list;
	}
	
	//取得子行三个单元格map下标的最大值,以此值为准计算子行的行数
	private  int getMax(String[] arr){
		int i=arr.length;
		int temp=0;
		for (int j=0;j<i;j++){
			if(temp<Integer.parseInt(arr[j])){
				temp=Integer.parseInt(arr[j]);
			}			
		}
		return temp;
	}
	
	/**
	 * 过滤页面List中的空值
	 * @param list
	 */
	private List filterList1(List list,String L1,String L2,String L3,String L4,String L5,String L6)
	{
		List rtnList=new ArrayList();
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			String strL1=(String)map.get(L1);
			String strL2=(String)map.get(L2);
			String strL3=(String)map.get(L3);	
			
			String strL4=(String)map.get(L4);
			String strL5=(String)map.get(L5);
			String strL6=(String)map.get(L6);
			
			if(!strL1.equals("")||!strL2.equals("")||!strL3.equals("") ||!strL4.equals("")||!strL5.equals("")||!strL6.equals(""))
			{
				rtnList.add(map);
			}
		}		
		return rtnList;
	}
	
	/**
	 * 过滤页面List中的空值
	 * @param list
	 */
	private List filterList2(List list,String L1,String L2,String L3,String L4,String L5,String L6,
			String L7){
		List rtnList=new ArrayList();
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			String strL1=(String)map.get(L1);
			String strL2=(String)map.get(L2);
			String strL3=(String)map.get(L3);	
			
			String strL4=(String)map.get(L4);
			String strL5=(String)map.get(L5);
			String strL6=(String)map.get(L6);
			
			String strL7=(String)map.get(L7);
			
			if(!strL1.equals("")||!strL2.equals("")||!strL3.equals("") ||!strL4.equals("")||!strL5.equals("")||!strL6.equals("")
					|| !strL7.equals("")){
				rtnList.add(map);
			}
		}		
		return rtnList;
	}
	
	
	
	/**
	 * 过滤页面List中的空值
	 * @param list
	 */
	private List filterList(List list,String L1,String L2,String L3,String L4,String L5,String L6,
			String L7,String L8,String L9,String L10,String L11){
		List rtnList=new ArrayList();
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			String strL1=(String)map.get(L1);
			String strL2=(String)map.get(L2);
			String strL3=(String)map.get(L3);	
			
			String strL4=(String)map.get(L4);
			String strL5=(String)map.get(L5);
			String strL6=(String)map.get(L6);
			
			String strL7=(String)map.get(L7);
			String strL8=(String)map.get(L8);
			String strL9=(String)map.get(L9);
			
			String strL10=(String)map.get(L10);
			String strL11=(String)map.get(L11);
			
			if(!strL1.equals("")||!strL2.equals("")||!strL3.equals("") ||!strL4.equals("")||!strL5.equals("")||!strL6.equals("")
					|| !strL7.equals("")||!strL8.equals("")||!strL9.equals("")
					|| !strL10.equals("")||!strL11.equals("")){
				rtnList.add(map);
			}
		}		
		return rtnList;
	}
	
//	此方法返回子行对应的List
	private List processMap1(Map map1,Map map2,Map map3,Map map4,Map map5,Map map6,
			String keyFlag1,String keyFlag2,String keyFlag3,String keyFlag4,String keyFlag5,String keyFlag6,
			String L1,String L2,String L3,
			String L4,String L5,String L6,String L7,String L8,String L9,
			String L10,String L11){
		
		boolean flagMuti=false;
		List list=new ArrayList();
		
		Iterator it = map1.keySet().iterator();
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map2.keySet().iterator();
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map3.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map4.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map5.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map6.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		//如果下列判断条件为真,则子行里内容全部为空
		if(flagMuti==false && map1.size()==0 && map2.size()==0 && map3.size()==0 && 
				map4.size()==0 && map5.size()==0 && map6.size()==0)
		{
			/**
			 * @todo
			 * 往三个map里放入
			 */
			for (int i=0;i<3;i++){
				Map rowMap=new HashMap();
				rowMap.put(L1, "");
				rowMap.put(L2, "*");
				rowMap.put(L3, "*");
				rowMap.put(L4, "*");
				rowMap.put(L5, "");
				rowMap.put(L6, "*");
				rowMap.put(L7, "");
				rowMap.put(L8, "");
				rowMap.put(L9, "*");
				rowMap.put(L10, "");
				rowMap.put(L11, "");
				list.add(rowMap);
			}		
			return list;			
			
			//如果满足此判断条件,则子行只有一行
		}else if(flagMuti==false){
			//放一条数据和二条空行
			Map rowMap=new HashMap();
			rowMap.put(L1, map1.get(keyFlag1)==null?"":((QysdsReportsItemDeclare)map1.get(keyFlag1)).getItemValue());
			rowMap.put(L2,"*" );
			rowMap.put(L3,"*");
			rowMap.put(L4, "*");
			rowMap.put(L5, map2.get(keyFlag2)==null?"":((QysdsReportsItemDeclare)map2.get(keyFlag2)).getItemValue());
			rowMap.put(L6,"*");
			rowMap.put(L7,  map3.get(keyFlag3)==null?"":((QysdsReportsItemDeclare)map3.get(keyFlag3)).getItemValue());
			rowMap.put(L8, map4.get(keyFlag4)==null?"":((QysdsReportsItemDeclare)map4.get(keyFlag4)).getItemValue());
			rowMap.put(L9, "*");
			rowMap.put(L10, map5.get(keyFlag5)==null?"":((QysdsReportsItemDeclare)map5.get(keyFlag5)).getItemValue());
			rowMap.put(L11,map6.get(keyFlag6)==null?"":((QysdsReportsItemDeclare)map6.get(keyFlag6)).getItemValue() );
			list.add(rowMap);
			
			Map rowMap1=new HashMap();
			rowMap1.put(L1, "");
			rowMap1.put(L2, "*");
			rowMap1.put(L3, "*");
			rowMap1.put(L4, "*");
			rowMap1.put(L5, "");
			rowMap1.put(L6, "*");
			rowMap1.put(L7, "");
			rowMap1.put(L8, "");
			rowMap1.put(L9, "*");
			rowMap1.put(L10, "");
			rowMap1.put(L11, "");
			list.add(rowMap1);
			
			Map rowMap2=new HashMap();
			rowMap2.put(L1, "");
			rowMap2.put(L2, "*");
			rowMap2.put(L3, "*");
			rowMap2.put(L4, "*");
			rowMap2.put(L5, "");
			rowMap2.put(L6, "*");
			rowMap2.put(L7, "");
			rowMap2.put(L8, "");
			rowMap2.put(L9, "*");
			rowMap2.put(L10, "");
			rowMap2.put(L11, "");
			list.add(rowMap2);
			
			return list;
		}
		
		//如果满足下列判断条件,则子行为多行 
		if(flagMuti==true){
			int max=0;
			String[] arr;
			String temp=new String("0");
			//取map1中下标的最大值,即子行的数目
			if(map1.size()!=0){
				it = map1.keySet().iterator();
				while (it.hasNext()) {
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				max=getMax(arr);
			}
			//取map2中下标的最大值,即子行的数目
			if(map2.size()!=0){
				it = map2.keySet().iterator();
				while (it.hasNext()) {
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max2=this.getMax(arr);
				if (max<=max2){
					max=max2;
				}
			}
			
			if(map3.size()!=0)
			{
				it = map3.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max3=this.getMax(arr);
				if (max<=max3)
				{
					max=max3;
				}
			}
			if(map4.size()!=0)
			{
				it = map4.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max4=this.getMax(arr);
				if (max<=max4)
				{
					max=max4;
				}
			}
			if(map5.size()!=0)
			{
				it = map5.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max5=this.getMax(arr);
				if (max<=max5)
				{
					max=max5;
				}
			}
			if(map6.size()!=0)
			{
				it = map6.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max6=this.getMax(arr);
				if (max<=max6)
				{
					max=max6;
				}
			}
			
			
			
			//插入子行的数据
			for(int i=0;i<max;i++)
			{
				Map rowMap=new HashMap();
				String key1=keyFlag1+"."+String.valueOf(i+1);
				String key2=keyFlag2+"."+String.valueOf(i+1);
				String key3=keyFlag3+"."+String.valueOf(i+1);
				String key4=keyFlag4+"."+String.valueOf(i+1);
				String key5=keyFlag5+"."+String.valueOf(i+1);
				String key6=keyFlag6+"."+String.valueOf(i+1);
				
				
				rowMap.put(L1, map1.get(key1)==null?"":((QysdsReportsItemDeclare)map1.get(key1)).getItemValue());
				rowMap.put(L2, "*");
				rowMap.put(L3, "*");
				rowMap.put(L4,"*");
				rowMap.put(L5, map2.get(key2)==null?"":((QysdsReportsItemDeclare)map2.get(key2)).getItemValue());
				rowMap.put(L6, "*");
				rowMap.put(L7, map3.get(key3)==null?"":((QysdsReportsItemDeclare)map3.get(key3)).getItemValue());
				rowMap.put(L8,  map4.get(key4)==null?"":((QysdsReportsItemDeclare)map4.get(key4)).getItemValue());
				rowMap.put(L9, "*");
				rowMap.put(L10, map5.get(key5)==null?"":((QysdsReportsItemDeclare)map5.get(key5)).getItemValue());
				rowMap.put(L11, map6.get(key6)==null?"":((QysdsReportsItemDeclare)map6.get(key6)).getItemValue());
				
				list.add(rowMap);
				
			}
			if(max==2)
			{	
				Map rowMap1=new HashMap();
				rowMap1.put(L1, "");
				rowMap1.put(L2, "*");
				rowMap1.put(L3, "*");
				rowMap1.put(L4, "*");
				rowMap1.put(L5, "");
				rowMap1.put(L6, "*");
				rowMap1.put(L7, "");
				rowMap1.put(L8, "");
				rowMap1.put(L9, "*");
				rowMap1.put(L10, "");
				rowMap1.put(L11, "");
				list.add(rowMap1);
			}
		}
		
		return list;
	}
	
//	此方法返回子行对应的List
	private List processMap2(Map map1,Map map2,Map map3,Map map4,Map map5,Map map6,Map map7,		
			String keyFlag1,String keyFlag2,String keyFlag3,String keyFlag4,String keyFlag5,String keyFlag6,
			String keyFlag7,
			String L1,String L2,String L3,
			String L4,String L5,String L6,String L7,String L8,String L9,
			String L10,String L11){
		
		boolean flagMuti=false;
		List list=new ArrayList();
		
		Iterator it = map1.keySet().iterator();
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map2.keySet().iterator();
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map3.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map4.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map5.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map6.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map7.keySet().iterator();
		while (it.hasNext()) 
		{
			String key=(String)it.next();
			if(key.indexOf(".")==-1)
			{
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		
		//如果下列判断条件为真,则子行里内容全部为空
		if(flagMuti==false && map1.size()==0 && map2.size()==0 && map3.size()==0 && 
				map4.size()==0 && map5.size()==0 && map6.size()==0 &&
				map7.size()==0 ){
			/**
			 * @todo
			 * 往三个map里放入
			 */
			for (int i=0;i<3;i++){
				Map rowMap=new HashMap();
				rowMap.put(L1, "");
				rowMap.put(L2, "*");
				rowMap.put(L3, "*");
				rowMap.put(L4, "*");
				rowMap.put(L5, "");
				rowMap.put(L6, "*");
				rowMap.put(L7, "");
				rowMap.put(L8, "");
				rowMap.put(L9, "");
				rowMap.put(L10, "");
				rowMap.put(L11, "");
				list.add(rowMap);
			}		
			return list;			
			
			//如果满足此判断条件,则子行只有一行
		}else if(flagMuti==false){
			//放一条数据和二条空行
			Map rowMap=new HashMap();
			rowMap.put(L1, map1.get(keyFlag1)==null?"":((QysdsReportsItemDeclare)map1.get(keyFlag1)).getItemValue());
			rowMap.put(L2, "*");
			rowMap.put(L3, "*");
			rowMap.put(L4, "*");
			rowMap.put(L5, map2.get(keyFlag2)==null?"":((QysdsReportsItemDeclare)map2.get(keyFlag2)).getItemValue());
			rowMap.put(L6, "*");
			rowMap.put(L7, map3.get(keyFlag3)==null?"":((QysdsReportsItemDeclare)map3.get(keyFlag3)).getItemValue());
			rowMap.put(L8,map4.get(keyFlag4)==null?"":((QysdsReportsItemDeclare)map4.get(keyFlag4)).getItemValue() );
			rowMap.put(L9, map5.get(keyFlag5)==null?"":((QysdsReportsItemDeclare)map5.get(keyFlag5)).getItemValue());
			rowMap.put(L10,map6.get(keyFlag6)==null?"":((QysdsReportsItemDeclare)map6.get(keyFlag6)).getItemValue() );
			rowMap.put(L11, map7.get(keyFlag7)==null?"":((QysdsReportsItemDeclare)map7.get(keyFlag7)).getItemValue());
			
			list.add(rowMap);
			
			Map rowMap1=new HashMap();
			rowMap1.put(L1, "");
			rowMap1.put(L2, "*");
			rowMap1.put(L3, "*");
			rowMap1.put(L4, "*");
			rowMap1.put(L5, "");
			rowMap1.put(L6, "*");
			rowMap1.put(L7, "");
			rowMap1.put(L8, "");
			rowMap1.put(L9, "");
			rowMap1.put(L10, "");
			rowMap1.put(L11, "");
			list.add(rowMap1);
			
			Map rowMap2=new HashMap();
			rowMap2.put(L1, "");
			rowMap2.put(L2, "*");
			rowMap2.put(L3, "*");
			rowMap2.put(L4, "*");
			rowMap2.put(L5, "");
			rowMap2.put(L6, "*");
			rowMap2.put(L7, "");
			rowMap2.put(L8, "");
			rowMap2.put(L9, "");
			rowMap2.put(L10, "");
			rowMap2.put(L11, "");
			list.add(rowMap2);
			
			return list;
		}
		
		//如果满足下列判断条件,则子行为多行 
		if(flagMuti==true){
			int max=0;
			String[] arr;
			String temp=new String("0");
			//取map1中下标的最大值,即子行的数目
			if(map1.size()!=0){
				it = map1.keySet().iterator();
				while (it.hasNext()) {
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				max=getMax(arr);
			}
			//取map2中下标的最大值,即子行的数目
			if(map2.size()!=0){
				it = map2.keySet().iterator();
				while (it.hasNext()) {
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max2=this.getMax(arr);
				if (max<=max2){
					max=max2;
				}
			}
			
			if(map3.size()!=0)
			{
				it = map3.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max3=this.getMax(arr);
				if (max<=max3)
				{
					max=max3;
				}
			}
			if(map4.size()!=0)
			{
				it = map4.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max4=this.getMax(arr);
				if (max<=max4)
				{
					max=max4;
				}
			}
			if(map5.size()!=0)
			{
				it = map5.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max5=this.getMax(arr);
				if (max<=max5)
				{
					max=max5;
				}
			}
			if(map6.size()!=0)
			{
				it = map6.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max6=this.getMax(arr);
				if (max<=max6)
				{
					max=max6;
				}
			}
			if(map7.size()!=0)
			{
				it = map7.keySet().iterator();
				while (it.hasNext()) 
				{
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max7=this.getMax(arr);
				if (max<=max7)
				{
					max=max7;
				}
			}
			
			
			
			//插入子行的数据
			for(int i=0;i<max;i++){
				Map rowMap=new HashMap();
				String key1=keyFlag1+"."+String.valueOf(i+1);
				String key2=keyFlag2+"."+String.valueOf(i+1);
				String key3=keyFlag3+"."+String.valueOf(i+1);
				String key4=keyFlag4+"."+String.valueOf(i+1);
				String key5=keyFlag5+"."+String.valueOf(i+1);
				String key6=keyFlag6+"."+String.valueOf(i+1);
				String key7=keyFlag7+"."+String.valueOf(i+1);
				
				rowMap.put(L1, map1.get(key1)==null?"":((QysdsReportsItemDeclare)map1.get(key1)).getItemValue());
				rowMap.put(L2, "*");
				rowMap.put(L3, "*");
				rowMap.put(L4, "*");
				rowMap.put(L5, map2.get(key2)==null?"":((QysdsReportsItemDeclare)map2.get(key2)).getItemValue());
				rowMap.put(L6, "*");
				rowMap.put(L7, map3.get(key3)==null?"":((QysdsReportsItemDeclare)map3.get(key3)).getItemValue());
				rowMap.put(L8,map4.get(key4)==null?"":((QysdsReportsItemDeclare)map4.get(key4)).getItemValue() );
				rowMap.put(L9, map5.get(key5)==null?"":((QysdsReportsItemDeclare)map5.get(key5)).getItemValue());
				rowMap.put(L10,map6.get(key6)==null?"":((QysdsReportsItemDeclare)map6.get(key6)).getItemValue() );
				rowMap.put(L11, map7.get(key7)==null?"":((QysdsReportsItemDeclare)map7.get(key7)).getItemValue());
				
				list.add(rowMap);
				
			}
			if(max==2)
			{
				
				Map rowMap1=new HashMap();
				rowMap1.put(L1, "");
				rowMap1.put(L2, "*");
				rowMap1.put(L3, "*");
				rowMap1.put(L4, "*");
				rowMap1.put(L5, "");
				rowMap1.put(L6, "*");
				rowMap1.put(L7, "");
				rowMap1.put(L8, "");
				rowMap1.put(L9, "");
				rowMap1.put(L10, "");
				rowMap1.put(L11, "");
				list.add(rowMap1);
			}
		}
		
		return list;
	}
	
}
