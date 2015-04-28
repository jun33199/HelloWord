/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jwsdmxb.processor;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web.QysdsnbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.jwsdmxb.web.JwsdmxbForm2009;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.smsb.util.Debug;
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
 * @author Shi Yanfeng
 * @version 1.1
 */

public class JwsdmxbProcessor2009 implements Processor {

	private final String[] columns = { "L1", "L2", "L3", "L4", "L5", "L6",
			"L7", "L8", "L9", "L10", "L11", "L12", "L13" };
	
	public static void main(String[] args) {
		
		System.out.println("7.1".substring(0,"7.1".indexOf(".")));
		int max=0;
		int num1=7;
		int num2=9;
		max=num1>num2?num1:num2;
		System.out.println(max);
		
		
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		JwsdmxbForm2009 form=new JwsdmxbForm2009();
		JwsdmxbProcessor2009 p=new JwsdmxbProcessor2009();
		QysdsReportsItemDeclare item=new QysdsReportsItemDeclare();
		item.setItemID("7.1");
		item.setItemValue("12412341");
		
		QysdsReportsItemDeclare item2=new QysdsReportsItemDeclare();
		item2.setItemID("7.1");
		item2.setItemValue("12412341");
		
		table.getCellContentList().put(item.getItemID(),item);
		table.getCellContentList().put(item2.getItemID(),item2);
		p.translate2Page(form,table);
	}
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
		case CodeConstant.SMSB_UPDATEACTION:
			result = doUpdate(vo);
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

		JwsdmxbForm2009 jwsdmxbForm = (JwsdmxbForm2009) vo.getData();

		Connection conn = null;

		try {
			// 获取数据库连接
			conn = SfDBResource.getConnection();

			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsUtil2009.setQysdsReport(report, jwsdmxbForm);

			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2009_6);
			table.setTableName(CodeConstant.TABLE_NAME_2009_6);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			
			report.getTableContentList().put(table.getTableId(), table);
			// 获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_2009_6);
			
			jwsdmxbForm=this.translate2Page(jwsdmxbForm,table);
			
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return jwsdmxbForm;
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

		JwsdmxbForm2009 jwsdmxbForm = (JwsdmxbForm2009) vo.getData();

		Connection conn = null;
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();

			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(jwsdmxbForm);
			// 获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
			//更新审核状态为“保存成功”
			iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return jwsdmxbForm;
	}

	/**
	 * doCheck   用于存储页面提交的详尽处理信息
	 * @param   vo 业务参数
	 * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	private Object doCheck(VOPackage vo) throws BaseException 
	{
		JwsdmxbForm2009 jwsdmxbForm = (JwsdmxbForm2009) vo.getData();
		Connection conn = null;
		try {
			//创建数据库连接
			conn = SfDBResource.getConnection();
			
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(jwsdmxbForm);
			
			//获取校验接口
			Checker checker=CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			//进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			
			/*如果校验通过，调用接口保存数据*/
			if(listSingle==null ||( listSingle!=null && listSingle.size()==0)){
				iApp.saveSingleTable(report);
				//更新审核状态为“单表审核通过”
				iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SINGLE_PASS);
			}else if(listSingle.size()>0){
				//单表审核未通过
				iApp.updateCheckStatus(report,"");
			}
			
			jwsdmxbForm.setCheckList(listSingle);
		}catch (Exception ex) { 
			//抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			//释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		return jwsdmxbForm;
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
		JwsdmxbForm2009 jwsdmxbForm = (JwsdmxbForm2009) vo.getData();
		Connection conn = null;
		
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();
			
			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(jwsdmxbForm);

			// 获取数据库接口，调用delete方法进行数据删除
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report);
			
			iApp.updateCheckStatus(report,"");
			
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2009_6);
			table.setTableId(CodeConstant.TABLE_NAME_2009_6);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			table=(QysdsReportsTableDeclare)report.getTableContentList().get(CodeConstant.TABLE_ID_2009_6);
			int []array ={15,26};
			jwsdmxbForm=this.translate2Page(jwsdmxbForm,QysdsUtil2009.putSpace(table,array));
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return jwsdmxbForm;
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

	private QysdsReportsDeclare translate2Interface(JwsdmxbForm2009 form) {

		// 企业所得税报表申明对象
		QysdsReportsDeclare report = new QysdsReportsDeclare();

		QysdsUtil2009.setQysdsReport(report, form);

		// 企业所得税报表内单表申明对象
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_2009_6);
		table.setTableName(CodeConstant.TABLE_NAME_2009_6);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		
		//境外所得已纳税款抵扣方法
		QysdsReportsItemDeclare itemDK = new QysdsReportsItemDeclare();
		itemDK.setItemID("1");
		itemDK.setItemValue(form.getJwsddk());
		itemDK.setItemType("11");
		table.getCellContentList().put(itemDK.getItemID(),itemDK);
		
		/**
		 * 三部分数据需要翻译
		 * 1、直接抵免数据
		 * 2、间接抵免数据
		 * 3、合计行数据
		 */
		
		/**
		 * 直接抵免数据翻译
		 */
		List zjdmList=form.getZjdmList();
		for(int i=0;i<zjdmList.size();i++){
			Map map=(Map)zjdmList.get(i);
			
			/*当前行次*/
//			String hc=(String)map.get("zjhc");
			String hc=Integer.toString(i+1);
			((Map)form.getZjdmList().get(i)).put("zjhc",hc);
			Iterator it = map.keySet().iterator();
			
			while (it.hasNext()) {
				/*关键字*/
				String key = (String) it.next();
			
				/*ITEM ID*/
				String itemId="";
				
				if(key.startsWith("ZJDM_L")){
					QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
					String col=key.substring(6);
					/*表右上角抵扣方法ITEM ID 规定为1，直接抵免数据ID从2开始，故列号+1=ITEM ID*/
					itemId=Integer.toString(Integer.parseInt(col)+1);
					
					item.setItemID(itemId+"."+hc);
					item.setItemValue((String)map.get(key));
					item.setItemType("11");
					table.getCellContentList().put(item.getItemID(), item);
					
				}
			}
			
		}
		
		/**
		 * 间接抵免数据翻译
		 */
		List jjdmList=form.getJjdmList();
		for(int i=0;i<jjdmList.size();i++){
			Map map=(Map)jjdmList.get(i);
			
			/*当前行次*/
//			String hc=(String)map.get("jjhc");
			String hc=Integer.toString(i+1);
			((Map)form.getJjdmList().get(i)).put("jjhc",hc);

			Iterator it = map.keySet().iterator();
			
			while (it.hasNext()) {
				/*关键字*/
				String key = (String) it.next();
				
				/*ITEM ID*/
				String itemId="";
				
				if(key.startsWith("JJDM_L")){
					QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
					/*当前单元格数据所在列*/
					String col=key.substring(6);
					/*间接抵免数据ID从19开始，故列号+18=ITEM ID*/
					itemId=Integer.toString(Integer.parseInt(col)+18);
					
					item.setItemID(itemId+"."+hc);
					item.setItemValue((String)map.get(key));
					item.setItemType("11");
					table.getCellContentList().put(item.getItemID(), item);
				}
			}
		}
		
		/**
		 * 翻译合计行数据
		 */
		List list=form.getHjList();
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map)list.get(i);
			
			QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
			item.setItemID((String)map.get("hjhc"));
			item.setItemValue((String)map.get("hjje"));
			item.setItemType("11");
			table.getCellContentList().put(item.getItemID(), item);
		}
		
		report.getTableContentList().put(table.getTableId(),
				QysdsUtil2009.cleanSpace(table));
		return report;
	}
	

	/**
	 * 将接口数据结构中的数据转换置入页面要求的数据结构 接口数据结构-->页面数据结构
	 * 翻译所有form内容
	 * @param form
	 * @param table
	 * @return
	 */
	private JwsdmxbForm2009 translate2Page(JwsdmxbForm2009 form,QysdsReportsTableDeclare table){
		
		//境外所得已纳税款抵扣方法
		if(table.getCellContentList().get("1")!=null){
			QysdsReportsItemDeclare item=(QysdsReportsItemDeclare)table.getCellContentList().get("1");
			form.setJwsddk(item.getItemValue());
		}
		
		List zjList=new ArrayList();
		List jjList=new ArrayList();
		List hjList=new ArrayList();
		Map zjMap=new HashMap();
		Map jjMap=new HashMap();
		
		
		/*求最大行时使用*/
		int curRow=0;
		int zjMaxRow=0;
		int jjMaxRow=0;
		
		Iterator it = table.getCellContentList().keySet().iterator();
		
		/**
		 * 本次循环完成以下工作
		 * 1、把直接抵免、间接抵免、合计行数据分别摘出来,合计行数据摘开即完成翻译
		 * 2、计算出直接抵免、间接抵免的最大行数
		 */
		while (it.hasNext()) {
			
			String key = (String) it.next();
			String mainItemID="";
			
			QysdsReportsItemDeclare item=(QysdsReportsItemDeclare)table.getCellContentList().get(key);
			
			if(key.indexOf(".")>0){
				mainItemID=key.substring(0,key.indexOf("."));
				curRow=Integer.parseInt(key.substring(key.indexOf(".")+1));
			}else{
				mainItemID=key;
			}
			
			/**
			 * 直接减免 ITEM ID 2--->18
			 */
			if(Integer.parseInt(mainItemID)>=2 && Integer.parseInt(mainItemID)<=18 ){
				zjMap.put(key,item.getItemValue());
				zjMaxRow=curRow>zjMaxRow?curRow:zjMaxRow;
			}
			
			/**
			 * 间接减免 ITEM ID 19--->30
			 */
			if(Integer.parseInt(mainItemID)>=19 && Integer.parseInt(mainItemID)<=30 ){
				jjMap.put(key,item.getItemValue());
				jjMaxRow=curRow>jjMaxRow?curRow:jjMaxRow;
			}
			
			/**
			 * 合计行 ITEM ID 31--->45
			 */
			if(Integer.parseInt(mainItemID)>=31 && Integer.parseInt(mainItemID)<=45 ){
				Map hjMap=new HashMap();
				hjMap.put(key,item.getItemValue());
				hjMap.put("hjhc",item.getItemID());
				hjMap.put("hjje",item.getItemValue());
				hjList.add(hjMap);
			}
			
		}
		
		/**
		 * 翻译直接抵免数据
		 */
		for (int i = 1; i <=zjMaxRow; i++) {
			Map map=new HashMap();
			map.put("zjhc",Integer.toString(i));
			Iterator iterator = zjMap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				int row=Integer.parseInt(key.substring(key.indexOf(".")+1));
				int id=Integer.parseInt(key.substring(0,key.indexOf(".")));
				if(i==row){
					map.put("ZJDM_L"+(id-1),zjMap.get(key));
				}
			}
			zjList.add(map);
		}
		
		/**
		 * 翻译间接抵免数据
		 */
		for (int i = 1; i <= jjMaxRow; i++) {
			Map map=new HashMap();
			map.put("jjhc",Integer.toString(i));
			Iterator iterator = jjMap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				int row=Integer.parseInt(key.substring(key.indexOf(".")+1));
				int id=Integer.parseInt(key.substring(0,key.indexOf(".")));
				if(i==row){
					map.put("JJDM_L"+(id-18),jjMap.get(key));
				}
			}
			jjList.add(map);
		}
		
		form.setZjdmList(zjList);
		form.setJjdmList(jjList);
		form.setHjList(hjList);
		
		return form;
	}


}
