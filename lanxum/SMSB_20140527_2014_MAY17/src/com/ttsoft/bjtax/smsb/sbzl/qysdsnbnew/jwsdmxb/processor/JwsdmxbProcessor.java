/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jwsdmxb.processor;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jwsdmxb.web.JwsdmxbForm;
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
 * @author Shi Yanfeng
 * @version 1.1
 */

public class JwsdmxbProcessor implements Processor {

	private final String[] columns = { "L1", "L2", "L3", "L4", "L5", "L6",
			"L7", "L8", "L9", "L10", "L11", "L12", "L13" };
	
	public static void main(String[] args) {
		JwsdmxbProcessor processor=new JwsdmxbProcessor();
		JwsdmxbForm form =new JwsdmxbForm();
		
		processor.translate2Interface(form);
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

		JwsdmxbForm jwsdmxbForm = (JwsdmxbForm) vo.getData();

		Connection conn = null;

		try {
			// 获取数据库连接
			conn = SfDBResource.getConnection();

			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsNewUtil.setQysdsReport(report, jwsdmxbForm);

			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_JWSDMXB);
			table.setTableName(CodeConstant.TABLE_NAME_JWSDMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			
			report.getTableContentList().put(table.getTableId(), table);
			// 获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_JWSDMXB);
			int[] array = { 15, 26 };
			jwsdmxbForm=this.translate2Page(jwsdmxbForm,QysdsNewUtil.putSpace(table,array));
			
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

		JwsdmxbForm jwsdmxbForm = (JwsdmxbForm) vo.getData();

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
		JwsdmxbForm jwsdmxbForm = (JwsdmxbForm) vo.getData();
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
		JwsdmxbForm jwsdmxbForm = (JwsdmxbForm) vo.getData();
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
			table.setTableId(CodeConstant.TABLE_ID_JWSDMXB);
			table.setTableId(CodeConstant.TABLE_NAME_JWSDMXB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			table=(QysdsReportsTableDeclare)report.getTableContentList().get(CodeConstant.TABLE_ID_JWSDMXB);
			int []array ={15,26};
			jwsdmxbForm=this.translate2Page(jwsdmxbForm,QysdsNewUtil.putSpace(table,array));
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

	private QysdsReportsDeclare translate2Interface(JwsdmxbForm jwsdmxbForm) {

		// 企业所得税报表申明对象
		QysdsReportsDeclare report = new QysdsReportsDeclare();

		QysdsNewUtil.setQysdsReport(report, jwsdmxbForm);

		// 企业所得税报表内单表申明对象
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_JWSDMXB);
		table.setTableName(CodeConstant.TABLE_NAME_JWSDMXB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		
		//境外所得已纳税款抵扣方法
		QysdsReportsItemDeclare itemDK = new QysdsReportsItemDeclare();
		itemDK.setItemID("1");
		itemDK.setItemValue(jwsdmxbForm.getJwsddk());
		table.getCellContentList().put(itemDK.getItemID(),itemDK);
		
		/**
		 * 说明： 1、假如有且仅有一行数据，则该行仅有可能为合计行 2、假如有且仅有两行数据，则一行为明细行、另一行为合计行
		 */
		List list = QysdsNewUtil.filterList(jwsdmxbForm.getDataList());

		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);

			if (i == list.size() - 1) {
				/**
				 * 说明： 1、循环到最后一行，即“合计行” 2、“合计”单元格不计入ITEM
				 */
				for (int m = 1; m <= 12; m++) {
					QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
					String itemID = String.valueOf(m + 14);
					item.setItemID(itemID);
					item.setItemValue((String) map.get(columns[m]));
					item.setItemType("11");
					table.getCellContentList().put(item.getItemID(), item);
				}
			} else {
				if (i == 0 && list.size() == 2) {
					// 只有一行明细行，一个合计行
					for (int m = 2; m <= 14; m++) {
						QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
						String itemID = String.valueOf(m);
						item.setItemID(itemID);
						item.setItemValue((String) map.get(columns[m - 2]));
						item.setItemType("11");
						table.getCellContentList().put(item.getItemID(), item);
					}
				} else {
					for (int m = 2; m <= 14; m++) {
						QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
						String itemID = String.valueOf(m) + "."
								+ String.valueOf(i + 1);
						item.setItemID(itemID);
						item.setItemValue((String) map.get(columns[m - 2]));
						item.setItemType("11");
						table.getCellContentList().put(item.getItemID(), item);
					}
				}
			}
		}

		report.getTableContentList().put(table.getTableId(),
				QysdsNewUtil.cleanSpace(table));
		return report;
	}
	
	/**
	 * 将接口数据结构中的数据转换置入页面要求的数据结构 接口数据结构-->页面数据结构
	 * 翻译所有form内容
	 * @param form
	 * @param table
	 * @return
	 */
	private JwsdmxbForm translate2Page(JwsdmxbForm form,QysdsReportsTableDeclare table){
		
		//境外所得已纳税款抵扣方法
		if(table.getCellContentList().get("1")!=null){
			QysdsReportsItemDeclare item=(QysdsReportsItemDeclare)table.getCellContentList().get("1");
			form.setJwsddk(item.getItemValue());
		}
		form.setDataList(this.translate2Page(table));
		return form;
	}
	
	/**
	 * 将接口数据结构中的数据转换置入页面要求的数据结构 接口数据结构-->页面数据结构
	 * 翻译明细、合计数据
	 * @param QysdsReportsTableDeclare
	 * @return 企业所得税报表申明对象
	 */
	private List translate2Page(QysdsReportsTableDeclare table) {
		List list = new ArrayList();
		
		//境外所得已纳税款抵扣方法
		
		/**
		 * 翻译子行数据（含静态子行、动态子行）
		 */
		list = this.processMap(table, "2", "3", "4", "5", "6", "7", "8",
				"9", "10", "11", "12", "13","14", "L1", "L2", "L3", "L4", "L5",
				"L6", "L7", "L8", "L9", "L10", "L11", "L12", "L13");
				
		/**
		 * 翻译固定行数据 合计行
		 */

		Map gdMap = new HashMap();
		gdMap.put(columns[0], "合计");
		for (int i = 1; i <= 12; i++) {
			QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table
					.getCellContentList().get(String.valueOf(i + 14));
			String itemValue = item == null ? "" : item.getItemValue();
			gdMap.put(columns[i], itemValue);
		}
		list.add(gdMap);
		
		return list;
	}

	// 按子行单元格进行分类,返回子行单元格对应的map
	private HashMap getCellMap(QysdsReportsTableDeclare table, String flag) {
		HashMap map = new HashMap();
		Iterator it = table.getCellContentList().keySet().iterator();

		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				if (flag.equals(key)) {
					map.put(key, table.getCellContentList().get(key));
				}
			} else if (flag.equals(key.substring(0, key.indexOf(".")))) {
				map.put(key, table.getCellContentList().get(key));
			}
		}

		return map;
	}

	// 此方法返回子行对应的List
	private List processMap(QysdsReportsTableDeclare table, String keyFlag1,
			String keyFlag2, String keyFlag3, String keyFlag4, String keyFlag5,
			String keyFlag6, String keyFlag7, String keyFlag8, String keyFlag9,
			String keyFlag10, String keyFlag11, String keyFlag12,
			String keyFlag13, String L1, String L2, String L3, String L4,
			String L5, String L6, String L7, String L8, String L9, String L10,
			String L11, String L12, String L13) {

		// 拆分table.getCellContentList()
		Map mapL1 = this.getCellMap(table, keyFlag1);
		Map mapL2 = this.getCellMap(table, keyFlag2);
		Map mapL3 = this.getCellMap(table, keyFlag3);
		Map mapL4 = this.getCellMap(table, keyFlag4);
		Map mapL5 = this.getCellMap(table, keyFlag5);
		Map mapL6 = this.getCellMap(table, keyFlag6);
		Map mapL7 = this.getCellMap(table, keyFlag7);
		Map mapL8 = this.getCellMap(table, keyFlag8);
		Map mapL9 = this.getCellMap(table, keyFlag9);
		Map mapL10 = this.getCellMap(table, keyFlag10);
		Map mapL11 = this.getCellMap(table, keyFlag11);
		Map mapL12 = this.getCellMap(table, keyFlag12);
		Map mapL13 = this.getCellMap(table, keyFlag13);

		boolean flagMuti = false;
		List list = new ArrayList();

		// 对三个map的数据所对应的子行数作判断
		Iterator it = mapL1.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		it = mapL2.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		it = mapL3.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		it = mapL4.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		it = mapL5.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		it = mapL6.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		it = mapL7.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		it = mapL8.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		it = mapL9.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		it = mapL10.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		it = mapL11.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		it = mapL12.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		it = mapL13.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.indexOf(".") == -1) {
				continue;
			} else {
				flagMuti = true;
				break;
			}
		}

		// 如果下列判断条件为真,则子行里内容全部为空,插入空数据
		if (flagMuti == false && mapL1.size() == 0 && mapL2.size() == 0
				&& mapL3.size() == 0 && mapL4.size() == 0 && mapL5.size() == 0
				&& mapL6.size() == 0 && mapL7.size() == 0 && mapL8.size() == 0
				&& mapL9.size() == 0 && mapL10.size() == 0
				&& mapL11.size() == 0 && mapL12.size() == 0
				&& mapL13.size() == 0) {
			/**
			 * @todo 往三个map里放入
			 */
			for (int i = 0; i < 7; i++) {
				Map rowMap = new HashMap();
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
				rowMap.put(L12, "");
				rowMap.put(L13, "");

				list.add(rowMap);
			}
			return list;

			// 如果满足此判断条件,则子行只有一行,并插入数据
		} else if (flagMuti == false) {
			// 放一条数据和二条空行
			Map rowMap = new HashMap();
			rowMap.put(L1, mapL1.get(keyFlag1) == null ? ""
					: ((QysdsReportsItemDeclare) mapL1.get(keyFlag1))
							.getItemValue());
			rowMap.put(L2, mapL2.get(keyFlag2) == null ? ""
					: ((QysdsReportsItemDeclare) mapL2.get(keyFlag2))
							.getItemValue());
			rowMap.put(L3, mapL3.get(keyFlag3) == null ? ""
					: ((QysdsReportsItemDeclare) mapL3.get(keyFlag3))
							.getItemValue());
			rowMap.put(L4, mapL4.get(keyFlag4) == null ? ""
					: ((QysdsReportsItemDeclare) mapL4.get(keyFlag4))
							.getItemValue());
			rowMap.put(L5, mapL5.get(keyFlag5) == null ? ""
					: ((QysdsReportsItemDeclare) mapL5.get(keyFlag5))
							.getItemValue());
			rowMap.put(L6, mapL6.get(keyFlag6) == null ? ""
					: ((QysdsReportsItemDeclare) mapL6.get(keyFlag6))
							.getItemValue());
			rowMap.put(L7, mapL7.get(keyFlag7) == null ? ""
					: ((QysdsReportsItemDeclare) mapL7.get(keyFlag7))
							.getItemValue());
			rowMap.put(L8, mapL8.get(keyFlag8) == null ? ""
					: ((QysdsReportsItemDeclare) mapL8.get(keyFlag8))
							.getItemValue());
			rowMap.put(L9, mapL9.get(keyFlag9) == null ? ""
					: ((QysdsReportsItemDeclare) mapL9.get(keyFlag9))
							.getItemValue());
			rowMap.put(L10, mapL10.get(keyFlag10) == null ? ""
					: ((QysdsReportsItemDeclare) mapL10.get(keyFlag10))
							.getItemValue());
			rowMap.put(L11, mapL11.get(keyFlag11) == null ? ""
					: ((QysdsReportsItemDeclare) mapL11.get(keyFlag11))
							.getItemValue());
			rowMap.put(L12, mapL12.get(keyFlag12) == null ? ""
					: ((QysdsReportsItemDeclare) mapL12.get(keyFlag12))
							.getItemValue());
			rowMap.put(L13, mapL13.get(keyFlag13) == null ? ""
					: ((QysdsReportsItemDeclare) mapL13.get(keyFlag13))
							.getItemValue());

			list.add(rowMap);

			for (int i = 0; i < 6; i++) {
				Map tempMap = new HashMap();
				tempMap.put(L1, "");
				tempMap.put(L2, "");
				tempMap.put(L3, "");
				tempMap.put(L4, "");
				tempMap.put(L5, "");
				tempMap.put(L6, "");
				tempMap.put(L7, "");
				tempMap.put(L8, "");
				tempMap.put(L9, "");
				tempMap.put(L10, "");
				tempMap.put(L11, "");
				tempMap.put(L12, "");
				tempMap.put(L13, "");

				list.add(tempMap);
			}

			return list;
		}

		// 如果满足下列判断条件,则子行为多行 ,并进行数据插入
		if (flagMuti == true) {
			int max = 0;
			String[] arr;
			String temp = new String("0");
			// 取map1中下标的最大值,即子行的数目
			if (mapL1.size() != 0) {
				it = mapL1.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				max = getMax(arr);
			}
			// 取map2中下标最大值,并与map1中的下标比较,取二者中的大值
			if (mapL2.size() != 0) {
				it = mapL2.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				int tempMax = this.getMax(arr);
				if (max <= tempMax) {
					max = tempMax;
				}
			}

			if (mapL3.size() != 0) {
				it = mapL3.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				int tempMax = this.getMax(arr);
				if (max <= tempMax) {
					max = tempMax;
				}
			}

			if (mapL4.size() != 0) {
				it = mapL4.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				int tempMax = this.getMax(arr);
				if (max <= tempMax) {
					max = tempMax;
				}
			}

			if (mapL5.size() != 0) {
				it = mapL5.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				int tempMax = this.getMax(arr);
				if (max <= tempMax) {
					max = tempMax;
				}
			}

			if (mapL6.size() != 0) {
				it = mapL6.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				int tempMax = this.getMax(arr);
				if (max <= tempMax) {
					max = tempMax;
				}
			}

			if (mapL7.size() != 0) {
				it = mapL7.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				int tempMax = this.getMax(arr);
				if (max <= tempMax) {
					max = tempMax;
				}
			}

			if (mapL8.size() != 0) {
				it = mapL8.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				int tempMax = this.getMax(arr);
				if (max <= tempMax) {
					max = tempMax;
				}
			}

			if (mapL9.size() != 0) {
				it = mapL9.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				int tempMax = this.getMax(arr);
				if (max <= tempMax) {
					max = tempMax;
				}
			}

			if (mapL10.size() != 0) {
				it = mapL10.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				int tempMax = this.getMax(arr);
				if (max <= tempMax) {
					max = tempMax;
				}
			}

			if (mapL11.size() != 0) {
				it = mapL11.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				int tempMax = this.getMax(arr);
				if (max <= tempMax) {
					max = tempMax;
				}
			}

			if (mapL12.size() != 0) {
				it = mapL12.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				int tempMax = this.getMax(arr);
				if (max <= tempMax) {
					max = tempMax;
				}
			}

			if (mapL13.size() != 0) {
				it = mapL13.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					temp = temp + "," + key.substring(key.indexOf(".") + 1);
				}
				arr = temp.split(",");
				int tempMax = this.getMax(arr);
				if (max <= tempMax) {
					max = tempMax;
				}
			}

			// 插入子行的数据
			for (int i = 0; i < max; i++) {
				Map rowMap = new HashMap();
				String key1 = keyFlag1 + "." + String.valueOf(i + 1);
				String key2 = keyFlag2 + "." + String.valueOf(i + 1);
				String key3 = keyFlag3 + "." + String.valueOf(i + 1);
				String key4 = keyFlag4 + "." + String.valueOf(i + 1);
				String key5 = keyFlag5 + "." + String.valueOf(i + 1);
				String key6 = keyFlag6 + "." + String.valueOf(i + 1);
				String key7 = keyFlag7 + "." + String.valueOf(i + 1);
				String key8 = keyFlag8 + "." + String.valueOf(i + 1);
				String key9 = keyFlag9 + "." + String.valueOf(i + 1);
				String key10 = keyFlag10 + "." + String.valueOf(i + 1);
				String key11 = keyFlag11 + "." + String.valueOf(i + 1);
				String key12 = keyFlag12 + "." + String.valueOf(i + 1);
				String key13 = keyFlag13 + "." + String.valueOf(i + 1);
				rowMap.put(L1, mapL1.get(key1) == null ? ""
						: ((QysdsReportsItemDeclare) mapL1.get(key1))
								.getItemValue());
				rowMap.put(L2, mapL2.get(key2) == null ? ""
						: ((QysdsReportsItemDeclare) mapL2.get(key2))
								.getItemValue());
				rowMap.put(L3, mapL3.get(key3) == null ? ""
						: ((QysdsReportsItemDeclare) mapL3.get(key3))
								.getItemValue());
				rowMap.put(L4, mapL4.get(key4) == null ? ""
						: ((QysdsReportsItemDeclare) mapL4.get(key4))
								.getItemValue());
				rowMap.put(L5, mapL5.get(key5) == null ? ""
						: ((QysdsReportsItemDeclare) mapL5.get(key5))
								.getItemValue());
				rowMap.put(L6, mapL6.get(key6) == null ? ""
						: ((QysdsReportsItemDeclare) mapL6.get(key6))
								.getItemValue());
				rowMap.put(L7, mapL7.get(key7) == null ? ""
						: ((QysdsReportsItemDeclare) mapL7.get(key7))
								.getItemValue());
				rowMap.put(L8, mapL8.get(key8) == null ? ""
						: ((QysdsReportsItemDeclare) mapL8.get(key8))
								.getItemValue());
				rowMap.put(L9, mapL9.get(key9) == null ? ""
						: ((QysdsReportsItemDeclare) mapL9.get(key9))
								.getItemValue());
				rowMap.put(L10, mapL10.get(key10) == null ? ""
						: ((QysdsReportsItemDeclare) mapL10.get(key10))
								.getItemValue());
				rowMap.put(L11, mapL11.get(key11) == null ? ""
						: ((QysdsReportsItemDeclare) mapL11.get(key11))
								.getItemValue());
				rowMap.put(L12, mapL12.get(key12) == null ? ""
						: ((QysdsReportsItemDeclare) mapL12.get(key12))
								.getItemValue());
				rowMap.put(L13, mapL13.get(key13) == null ? ""
						: ((QysdsReportsItemDeclare) mapL13.get(key13))
								.getItemValue());
				list.add(rowMap);

			}

			// 如果子行数目小于7行，则插入（7-max）行
			if (max < 7) {
				for (int j = 0; j < (7 - max); j++) {
					Map tempMap = new HashMap();
					tempMap.put(L1, "");
					tempMap.put(L2, "");
					tempMap.put(L3, "");
					tempMap.put(L4, "");
					tempMap.put(L5, "");
					tempMap.put(L6, "");
					tempMap.put(L7, "");
					tempMap.put(L8, "");
					tempMap.put(L9, "");
					tempMap.put(L10, "");
					tempMap.put(L11, "");
					tempMap.put(L12, "");
					tempMap.put(L13, "");

					list.add(tempMap);
				}
			}
		}

		return list;
	}

	// 取得子行三个单元格map下标的最大值,以此值为准计算子行的行数
	private int getMax(String[] arr) {
		int i = arr.length;
		int temp = 0;
		for (int j = 0; j < i; j++) {
			if (temp < Integer.parseInt(arr[j])) {
				temp = Integer.parseInt(arr[j]);
			}
		}
		return temp;
	}

}
