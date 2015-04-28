/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.bxzbjb.processor;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.bxzbjb.web.BxzbjForm;
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

public class BxzbjbProcessor implements Processor
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
		BxzbjForm bxzbjForm = (BxzbjForm) vo.getData();
		Connection conn = null;
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();
			
			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsNewUtil.setQysdsReport(report,bxzbjForm);
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_BXZBJ);
			table.setTableName(CodeConstant.TABLE_NAME_BXZBJ);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(),table);
			
			// 获取数据库接口，调用query方法进行数据查找
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			table=(QysdsReportsTableDeclare)report.getTableContentList().get(table.getTableId());
			int []arra={1,78,83,100};
			HashMap map=translate2Page(QysdsNewUtil.putSpace(table,arra));
			bxzbjForm.setDataList((ArrayList)map.get("pagelist"));
			bxzbjForm.setQtzbjList((ArrayList)map.get("qtzbjlist"));
			bxzbjForm.setQtList((ArrayList)map.get("qtlist"));
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		
		return bxzbjForm;
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
		BxzbjForm bxzbjForm = (BxzbjForm) vo.getData();
		Connection conn = null;
		try {
			//获取数据库连接
			conn = SfDBResource.getConnection();
			
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(bxzbjForm);
			
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
		return bxzbjForm;
	}
	
	/**
	 * doSave   用于存储页面提交的详尽处理信息
	 * @param   vo 业务参数
	 * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	private Object doCheck(VOPackage vo) throws BaseException 
	{
		BxzbjForm bxzbjForm = (BxzbjForm) vo.getData();
		Connection conn = null;
		try {
			//创建数据库连接
			conn = SfDBResource.getConnection();
			
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(bxzbjForm);
			
			 //获取校验接口
			Checker checker=CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			
			//进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			
			/*如果校验通过，调用接口保存数据*/
			if(listSingle==null ||( listSingle!=null && listSingle.size()==0)){
				iApp.saveSingleTable(report);
				//更新审核状态为“单表审核通过”
				iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SINGLE_PASS);
			}else if(listSingle.size()>0){
				//单表审核未通过
				iApp.updateCheckStatus(report,"");
			}
			bxzbjForm.setCheckList(listSingle);
		}catch (Exception ex) { 
			//抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			//释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		return bxzbjForm;
	}
	
	/**
	 * doDelete  用于删除页面提交的详尽处理信息
	 * @param    vo 业务参数
	 * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	private Object doDelete(VOPackage vo) throws BaseException 
	{
		BxzbjForm bxzbjForm = (BxzbjForm) vo.getData();
		Connection conn = null;
		try {
			//获取数据库连接
			conn = SfDBResource.getConnection();
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(bxzbjForm);
			//获取数据库接口，调用Delete方法进行数据删除
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report);	
			iApp.updateCheckStatus(report,"");
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_BXZBJ);
			table.setTableName(CodeConstant.TABLE_NAME_BXZBJ);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);		
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_BXZBJ);
			//取list
			int []arra={1,78,83,100};
			HashMap map=translate2Page(QysdsNewUtil.putSpace(table,arra));
			bxzbjForm.setDataList((ArrayList)map.get("pagelist"));
			bxzbjForm.setQtzbjList((ArrayList)map.get("qtzbjlist"));
			bxzbjForm.setQtList((ArrayList)map.get("qtlist"));
		} catch (Exception ex) {
			//抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return bxzbjForm;
	}
	
	/**
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
	 * 页面数据结构-->接口数据结构
	 * @param bxzbjForm 
	 * @return 企业所得税报表申明对象
	 */
	private QysdsReportsDeclare translate2Interface(BxzbjForm form){
		
		//企业所得税报表申明对象
		QysdsReportsDeclare report =new QysdsReportsDeclare();
		QysdsNewUtil.setQysdsReport(report,form);
		
		//企业所得税报表内单表申明对象
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_BXZBJ);
		table.setTableName(CodeConstant.TABLE_NAME_BXZBJ);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		List list=form.getDataList();
		for(int i=0;i<list.size();i++){
			HashMap map = (HashMap)list.get(i);
			int hc = Integer.parseInt((String)map.get("hc"));
			if (hc<27){
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID(String.valueOf(hc*3-2));
				item_1.setItemValue((String)map.get("sjfse"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);
				
				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID(String.valueOf(hc*3-1));
				item_2.setItemValue((String)map.get("sfgdkce"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);
				
				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID(String.valueOf(hc*3));
				item_3.setItemValue((String)map.get("tze"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);
			}
			
			if(hc>26&&hc<33){
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID(String.valueOf(hc*3+4-2));
				item_1.setItemValue((String)map.get("sjfse"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);
				
				QysdsReportsItemDeclare item_2 = new QysdsReportsItemDeclare();
				item_2.setItemID(String.valueOf(hc*3+4-1));
				item_2.setItemValue((String)map.get("sfgdkce"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(), item_2);
				
				QysdsReportsItemDeclare item_3 = new QysdsReportsItemDeclare();
				item_3.setItemID(String.valueOf(hc*3+4));
				item_3.setItemValue((String)map.get("tze"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(), item_3);				
			}			
		}
		
		
		
		List qtzbjList=this.filterList(form.getQtzbjList(),"qtzbj_xm", "qtzbj_sjfse", "qtzbj_sfgdkce","qtzbj_tze");		
		for(int i=0;i<qtzbjList.size();i++){
			HashMap map=(HashMap)qtzbjList.get(i);
			if(qtzbjList.size()==1){
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("79");
				item_1.setItemValue((String)map.get("qtzbj_xm"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("80");
				item_2.setItemValue((String)map.get("qtzbj_sjfse"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("81");
				item_3.setItemValue((String)map.get("qtzbj_sfgdkce"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("82");
				item_4.setItemValue((String)map.get("qtzbj_tze"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
			}else{
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("79"+"."+String.valueOf(i+1));
				item_1.setItemValue((String)map.get("qtzbj_xm"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("80"+"."+String.valueOf(i+1));
				item_2.setItemValue((String)map.get("qtzbj_sjfse"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("81"+"."+String.valueOf(i+1));
				item_3.setItemValue((String)map.get("qtzbj_sfgdkce"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);	
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("82"+"."+String.valueOf(i+1));
				item_4.setItemValue((String)map.get("qtzbj_tze"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
			}
		}
		
		List qtList=this.filterList(form.getQtList(),"qt_xm", "qt_sjfse", "qt_sfgdkce","qt_tze");		
		for(int i=0;i<qtList.size();i++){
			HashMap map=(HashMap)qtList.get(i);
			if(qtList.size()==1){
				
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("101");
				item_1.setItemValue((String)map.get("qt_xm"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("102");
				item_2.setItemValue((String)map.get("qt_sjfse"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("103");
				item_3.setItemValue((String)map.get("qt_sfgdkce"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("104");
				item_4.setItemValue((String)map.get("qt_tze"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
				
			}else{
				QysdsReportsItemDeclare item_1=new QysdsReportsItemDeclare();
				item_1.setItemID("101"+"."+String.valueOf(i+1));
				item_1.setItemValue((String)map.get("qt_xm"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(),item_1);
				
				QysdsReportsItemDeclare item_2=new QysdsReportsItemDeclare();
				item_2.setItemID("102"+"."+String.valueOf(i+1));
				item_2.setItemValue((String)map.get("qt_sjfse"));
				item_2.setItemType("11");
				table.getCellContentList().put(item_2.getItemID(),item_2);
				
				QysdsReportsItemDeclare item_3=new QysdsReportsItemDeclare();
				item_3.setItemID("103"+"."+String.valueOf(i+1));
				item_3.setItemValue((String)map.get("qt_sfgdkce"));
				item_3.setItemType("11");
				table.getCellContentList().put(item_3.getItemID(),item_3);	
				
				QysdsReportsItemDeclare item_4=new QysdsReportsItemDeclare();
				item_4.setItemID("104"+"."+String.valueOf(i+1));
				item_4.setItemValue((String)map.get("qt_tze"));
				item_4.setItemType("11");
				table.getCellContentList().put(item_4.getItemID(),item_4);
			}
		}		
		report.getTableContentList().put(table.getTableId(),QysdsNewUtil.cleanSpace(table));
		
		//		测试用
		Iterator it= table.getCellContentList().keySet().iterator();		
		while(it.hasNext()){
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
	private HashMap translate2Page(QysdsReportsTableDeclare table){
		
		//取固定行的数据 list 进行回显 
		List pagelist = new ArrayList();
		for(int i = 1;i<=26;i++){
			HashMap stat_map = new HashMap();
			stat_map.put("hc",String.valueOf(i));
			int a = 3*i;
			int b = 3*i-1;
			int c = 3*i-2;
			stat_map.put("tze",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(a))).getItemValue());
			stat_map.put("sfgdkce",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(b))).getItemValue());
			stat_map.put("sjfse",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(c))).getItemValue());
			pagelist.add(stat_map);		
		}
		
		for(int i = 27;i<=32;i++){
			HashMap stat_map = new HashMap();
			stat_map.put("hc",String.valueOf(i));
			int a = 3*i+4;
			int b = 3*i+4-1;
			int c = 3*i+4-2;
			stat_map.put("tze",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(a))).getItemValue());
			stat_map.put("sfgdkce",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(b))).getItemValue());
			stat_map.put("sjfse",((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(c))).getItemValue());
			pagelist.add(stat_map);		
		}
		
		
		
		//拆分table.getCellContentList()此时map中的数据是以列存取的。
		Map qtzbj_1 = this.getCellMap(table, "79");
		Map qtzbj_2 = this.getCellMap(table, "80");
		Map qtzbj_3 = this.getCellMap(table, "81");
		Map qtzbj_4 = this.getCellMap(table, "82");
		
		Map qt_1 = this.getCellMap(table, "101");
		Map qt_2 = this.getCellMap(table, "102");
		Map qt_3 = this.getCellMap(table, "103");
		Map qt_4 = this.getCellMap(table, "104");
		
		//处理为等长度Map,并得到需要回显到页面的数据所对应的List
		List qtzbjlist=this.processMap(qtzbj_1, qtzbj_2, qtzbj_3,qtzbj_4,
				"79","80","81","82",
				"qtzbj_xm","qtzbj_sjfse","qtzbj_sfgdkce","qtzbj_tze",
		"2");
		List qtlist=this.processMap(qt_1, qt_2, qt_3,qt_4,
				"101","102","103","104",
				"qt_xm","qt_sjfse","qt_sfgdkce","qt_tze",
		"4");
		
		//把所有回显到页面的数据所对应的List 放入一个map返回
		HashMap totalmap = new HashMap();
		totalmap.put("pagelist", pagelist);
		totalmap.put("qtzbjlist", qtzbjlist);
		totalmap.put("qtlist", qtlist);
		
		return totalmap;
	}
	
	
	//按子行单元格进行分类,返回子行单元格对应的map
	private HashMap getCellMap(QysdsReportsTableDeclare table,String flag)
	{
		HashMap map = new HashMap();		
		Iterator it = table.getCellContentList().keySet().iterator();
		
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				if(flag.equals(key)){
					map.put(key, table.getCellContentList().get(key));
					System.out.println("flag----"+flag+"          key--------"+key);
				}
			}else if(flag.equals(key.substring(0, key.indexOf(".")))){
				String est=key.substring(0, key.indexOf("."));
				System.out.println("est ----------"+est);
				map.put(key, table.getCellContentList().get(key));
				System.out.println("flag----"+flag+"          key--------"+key);			
			}			
		}
		
		return map;		
	}
	
	//此方法返回子行对应的List
	private List processMap(Map map1,Map map2,Map map3,Map map4,
			String keyFlag1,String keyFlag2,String keyFlag3,String keyFlag4,
			String L1,String L2,String L3,String L4,String off ){
		
		boolean flagMuti=false;
		List list=new ArrayList();
		
		//对四个map的数据所对应的子行数作判断
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
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		it = map4.keySet().iterator();
		while (it.hasNext()) {
			String key=(String)it.next();
			if(key.indexOf(".")==-1){
				continue;
			}else{
				flagMuti=true;
				break;
			}
		}
		
		//如果下列判断条件为真,则子行里内容全部为空,插入空数据
		if(flagMuti==false && map1.size()==0 && map2.size()==0 && map3.size()==0&&map4.size()==0){
			/**
			 * @todo
			 * 往同一个map里放入4个空值
			 */
			for (int i=0;i<Integer.parseInt(off);i++){
				Map rowMap=new HashMap();
				rowMap.put(L1, "");
				rowMap.put(L2, "");
				rowMap.put(L3, "");
				rowMap.put(L4, "");
				list.add(rowMap);
			}		
			return list;			
			
			//如果满足此判断条件,则子行只有一行,并插入数据
		}else if(flagMuti==false){
			//放一条数据和一条空行
			Map rowMap=new HashMap();
			rowMap.put(L1, map1.get(keyFlag1)==null?"":((QysdsReportsItemDeclare)map1.get(keyFlag1)).getItemValue());
			rowMap.put(L2, map2.get(keyFlag2)==null?"":((QysdsReportsItemDeclare)map2.get(keyFlag2)).getItemValue());
			rowMap.put(L3, map3.get(keyFlag3)==null?"":((QysdsReportsItemDeclare)map3.get(keyFlag3)).getItemValue());
			rowMap.put(L4, map4.get(keyFlag4)==null?"":((QysdsReportsItemDeclare)map4.get(keyFlag4)).getItemValue());
			list.add(rowMap);
			for(int j=1;j<Integer.parseInt(off);j++){
				Map rowMap1=new HashMap();
				rowMap1.put(L1, "");
				rowMap1.put(L2, "");
				rowMap1.put(L3, "");
				rowMap1.put(L4, "");
				list.add(rowMap1);
			}
			return list;
		}
		
		//如果满足下列判断条件,则子行为多行 ,并进行数据插入
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
			//取map2中下标最大值,并与map1中的下标比较,取二者中的大值
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
			//取map3中下标最大值,并与前者比较,取二者中的大值
			if(map3.size()!=0){
				it = map3.keySet().iterator();
				while (it.hasNext()) {
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max3=this.getMax(arr);
				if (max<=max3){
					max=max3;
				}
			}
//			取map4中下标最大值,并与前者比较,取二者中的大值
			if(map4.size()!=0){
				it = map4.keySet().iterator();
				while (it.hasNext()) {
					String key=(String)it.next();
					temp=temp+","+key.substring(key.indexOf(".")+1);
				}
				arr=temp.split(",");
				int max4=this.getMax(arr);
				if (max<=max4){
					max=max4;
				}
			}
			//插入子行的数据
			for(int i=0;i<max;i++){
				Map rowMap=new HashMap();
				String key1=keyFlag1+"."+String.valueOf(i+1);
				String key2=keyFlag2+"."+String.valueOf(i+1);
				String key3=keyFlag3+"."+String.valueOf(i+1);
				String key4=keyFlag4+"."+String.valueOf(i+1);
				rowMap.put(L1, map1.get(key1)==null?"":((QysdsReportsItemDeclare)map1.get(key1)).getItemValue());
				rowMap.put(L2, map2.get(key2)==null?"":((QysdsReportsItemDeclare)map2.get(key2)).getItemValue());
				rowMap.put(L3, map3.get(key3)==null?"":((QysdsReportsItemDeclare)map3.get(key3)).getItemValue());
				rowMap.put(L4, map4.get(key4)==null?"":((QysdsReportsItemDeclare)map4.get(key4)).getItemValue());
				list.add(rowMap);								
			}
			if(off.equals("4")){
				for(int h=0;h<4-max;h++){
					Map rowMap=new HashMap();
					rowMap.put(L1, "");
					rowMap.put(L2, "");
					rowMap.put(L3, "");
					rowMap.put(L4, "");
					list.add(rowMap);
				}
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
	private List filterList(List list,String L1,String L2,String L3,String L4){
		List rtnList=new ArrayList();
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			String strL1=(String)map.get(L1);
			String strL2=(String)map.get(L2);
			String strL3=(String)map.get(L3);	
			String strL4=(String)map.get(L4);
			if(!strL1.equals("")||!strL2.equals("")||!strL3.equals("")||!strL4.equals("")){
				rtnList.add(map);
			}
		}		
		return rtnList;
	}	
}
