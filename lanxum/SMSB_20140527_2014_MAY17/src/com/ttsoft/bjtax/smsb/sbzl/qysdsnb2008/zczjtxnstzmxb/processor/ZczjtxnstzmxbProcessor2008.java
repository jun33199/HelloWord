/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.zczjtxnstzmxb.processor;
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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.zczjtxnstzmxb.web.ZczjtxnstzmxbForm2008;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.util.QysdsUtil2008;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报</p>
 * @author zhangyj
 * @version 1.1
 */

public class ZczjtxnstzmxbProcessor2008 implements Processor
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
	
	private Object doShow(VOPackage vo) throws BaseException
	{
		ZczjtxnstzmxbForm2008 zcmxbForm = (ZczjtxnstzmxbForm2008) vo.getData();
		Connection conn = null;
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();
			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsUtil2008.setQysdsReport(report,zcmxbForm);
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2008_9);
			table.setTableName(CodeConstant.TABLE_NAME_2008_9);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(),table);
			// 获取数据库接口，调用query方法进行数据查找
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			int[] arrs={1,118};
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2008_9);
			zcmxbForm.setDataList(this.translate2Page(QysdsUtil2008.putSpace(table,arrs)));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return zcmxbForm;
	}
	/**
	 * 初始化
	 * @para
	 * @throws BseException
	 */
	private void initForm(ZczjtxnstzmxbForm2008 zcmxbForm) throws BaseException {
		
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
		ZczjtxnstzmxbForm2008 zcmxbForm = (ZczjtxnstzmxbForm2008) vo.getData();
		Connection conn = null;
		try {
			//获取数据库连接
			conn = SfDBResource.getConnection();
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(zcmxbForm);
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
		return zcmxbForm;
	}
	
	/**
	 * doCheck   用于存储页面提交的详尽处理信息
	 * @param   vo 业务参数
	 * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	private Object doCheck(VOPackage vo) throws BaseException 
	{
		ZczjtxnstzmxbForm2008 zcmxbForm = (ZczjtxnstzmxbForm2008) vo.getData();
		Connection conn = null;
		try {
			//创建数据库连接
			conn = SfDBResource.getConnection();
			
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(zcmxbForm);
			
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
			zcmxbForm.setCheckList(listSingle);
		}catch (Exception ex) { 
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			SfDBResource.freeConnection(conn);
		}
		return zcmxbForm;
	}
	
	/**
	 * doDelete  用于删除页面提交的详尽处理信息
	 * @param    vo 业务参数
	 * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException 当其他情况都会抛出异常信息
	 */
	private Object doDelete(VOPackage vo) throws BaseException
	{
		ZczjtxnstzmxbForm2008 zcmxbForm = (ZczjtxnstzmxbForm2008) vo.getData();
		Connection conn = null;
		try {
			//获取数据库连接
			conn = SfDBResource.getConnection();
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(zcmxbForm);
			//获取数据库接口，调用delete方法进行数据删除,iApp返回一个report对象
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report);
			iApp.updateCheckStatus(report,"");
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2008_9);
			table.setTableName(CodeConstant.TABLE_NAME_2008_9);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			int[] arrs={1,118};
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2008_9);
			zcmxbForm.setDataList(this.translate2Page(QysdsUtil2008.putSpace(table,arrs)));
		}catch (Exception ex) { 
			//抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		finally {
			SfDBResource.freeConnection(conn);
		}
		return zcmxbForm;
	}
	
	
	/**
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
	 * 页面数据结构-->接口数据结构
	 * @param ZczjtxnstzmxbForm2008 
	 * @return 企业所得税报表申明对象
	 */
	private QysdsReportsDeclare translate2Interface(ZczjtxnstzmxbForm2008 form){
		
		//企业所得税报表申明对象
		QysdsReportsDeclare report =new QysdsReportsDeclare();
		QysdsUtil2008.setQysdsReport(report,form);
		
		//企业所得税报表内单表申明对象
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_2008_9);
		table.setTableName(CodeConstant.TABLE_NAME_2008_9);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		List list=form.getDataList();

		for(int i=0;i<list.size();i++){
			System.out.println("=====" + list.get(i));
			
			HashMap map=(HashMap)list.get(i);
			
			String hc=(String)map.get("hc");
			if("1".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("1");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("2");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("3");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("4");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("5");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}	
			if("2".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("6");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("7");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("8");
				item_1_3.setItemValue((String)map.get("zjtxnxkj"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("9");
				item_1_4.setItemValue((String)map.get("zjtxnxss"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("10");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("11");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("12");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}	
			if("3".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("13");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("14");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("15");
				item_1_3.setItemValue((String)map.get("zjtxnxkj"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("16");
				item_1_4.setItemValue((String)map.get("zjtxnxss"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("17");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("18");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("19");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}	
			if("4".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("20");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("21");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("22");
				item_1_3.setItemValue((String)map.get("zjtxnxkj"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("23");
				item_1_4.setItemValue((String)map.get("zjtxnxss"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("24");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("25");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("26");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}	
			if("5".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("27");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("28");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("29");
				item_1_3.setItemValue((String)map.get("zjtxnxkj"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("30");
				item_1_4.setItemValue((String)map.get("zjtxnxss"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("31");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("32");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("33");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}	
			if("6".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("34");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("35");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("36");
				item_1_3.setItemValue((String)map.get("zjtxnxkj"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("37");
				item_1_4.setItemValue((String)map.get("zjtxnxss"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("38");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("39");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("40");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}	
			if("7".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("41");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("42");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);								
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("43");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("44");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("45");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}
			if("8".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("46");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("47");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("48");
				item_1_3.setItemValue((String)map.get("zjtxnxkj"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("49");
				item_1_4.setItemValue((String)map.get("zjtxnxss"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("50");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("51");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("52");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}	
			if("9".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("53");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("54");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("55");
				item_1_3.setItemValue((String)map.get("zjtxnxkj"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("56");
				item_1_4.setItemValue((String)map.get("zjtxnxss"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("57");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("58");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("59");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}
			if("10".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("60");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("61");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
								
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("62");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("63");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("64");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}	
			if("11".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("65");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("66");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("67");
				item_1_3.setItemValue((String)map.get("zjtxnxkj"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("68");
				item_1_4.setItemValue((String)map.get("zjtxnxss"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("69");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("70");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("71");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}
			if("12".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("72");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("73");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("74");
				item_1_3.setItemValue((String)map.get("zjtxnxkj"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("75");
				item_1_4.setItemValue((String)map.get("zjtxnxss"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("76");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("77");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("78");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}	
			if("13".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("79");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("80");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("81");
				item_1_3.setItemValue((String)map.get("zjtxnxkj"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("82");
				item_1_4.setItemValue((String)map.get("zjtxnxss"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("83");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("84");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("85");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}			
			if("14".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("86");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("87");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("88");
				item_1_3.setItemValue((String)map.get("zjtxnxkj"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("89");
				item_1_4.setItemValue((String)map.get("zjtxnxss"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("90");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("91");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("92");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}	
			if("15".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("93");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("94");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("95");
				item_1_3.setItemValue((String)map.get("zjtxnxkj"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("96");
				item_1_4.setItemValue((String)map.get("zjtxnxss"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("97");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("98");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("99");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}
			if("16".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("100");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("101");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("102");
				item_1_3.setItemValue((String)map.get("zjtxnxkj"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("103");
				item_1_4.setItemValue((String)map.get("zjtxnxss"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("104");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("105");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("106");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}	
			if("17".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("107");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("108");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("109");
				item_1_3.setItemValue((String)map.get("zjtxnxkj"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("110");
				item_1_4.setItemValue((String)map.get("zjtxnxss"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("111");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("112");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("113");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}	
			if("18".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("114");
				item_1_1.setItemValue((String)map.get("zcyzzzje"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("115");
				item_1_2.setItemValue((String)map.get("zcyzjsjc"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
								
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("116");
				item_1_5.setItemValue((String)map.get("bqzjtxekj"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("117");
				item_1_6.setItemValue((String)map.get("bqzjtxess"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("118");
				item_1_7.setItemValue((String)map.get("nstze"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);				
			}
			
		}
		report.getTableContentList().put(table.getTableId(), QysdsUtil2008.cleanSpace(table));
		//ceshi
		Iterator it=table.getCellContentList().keySet().iterator();
		while(it.hasNext()){
			QysdsReportsItemDeclare item=(QysdsReportsItemDeclare)table.getCellContentList().get((String)it.next());
			System.out.println(item.getItemID()+"   "+item.getItemValue());
		}
		return report;
	}
	
	/**
	 * 将接口数据结构中的数据转换置入页面要求的数据结构
	 * 接口数据结构-->页面数据结构
	 * @param QysdsReportsTableDeclare
	 * @return 企业所得税报表申明对象
	 */
	private List translate2Page(QysdsReportsTableDeclare table)
	{	
		List list = new ArrayList();
		for(int i=1;i<2;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("1")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("2")).getItemValue());
			map.put("zjtxnxkj",  "*");
			map.put("zjtxnxss",  "*");
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("3")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("4")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("5")).getItemValue());
			list.add(map);
		}
		for(int i=2;i<3;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("6")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("7")).getItemValue());
			map.put("zjtxnxkj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("8")).getItemValue());
			map.put("zjtxnxss",  ((QysdsReportsItemDeclare)table.getCellContentList().get("9")).getItemValue());
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("10")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("11")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("12")).getItemValue());
			list.add(map);
		}
		for(int i=3;i<4;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("13")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("14")).getItemValue());
			map.put("zjtxnxkj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("15")).getItemValue());
			map.put("zjtxnxss",  ((QysdsReportsItemDeclare)table.getCellContentList().get("16")).getItemValue());
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("17")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("18")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("19")).getItemValue());
			list.add(map);
		}
		for(int i=4;i<5;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("20")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("21")).getItemValue());
			map.put("zjtxnxkj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("22")).getItemValue());
			map.put("zjtxnxss",  ((QysdsReportsItemDeclare)table.getCellContentList().get("23")).getItemValue());
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("24")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("25")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("26")).getItemValue());
			list.add(map);
		}
		for(int i=5;i<6;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("27")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("28")).getItemValue());
			map.put("zjtxnxkj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("29")).getItemValue());
			map.put("zjtxnxss",  ((QysdsReportsItemDeclare)table.getCellContentList().get("30")).getItemValue());
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("31")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("32")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("33")).getItemValue());
			list.add(map);
		}
		for(int i=6;i<7;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("34")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("35")).getItemValue());
			map.put("zjtxnxkj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("36")).getItemValue());
			map.put("zjtxnxss",  ((QysdsReportsItemDeclare)table.getCellContentList().get("37")).getItemValue());
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("38")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("39")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("40")).getItemValue());
			list.add(map);
		}
		for(int i=7;i<8;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("41")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("42")).getItemValue());
			map.put("zjtxnxkj",  "*");
			map.put("zjtxnxss",  "*");
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("43")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("44")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("45")).getItemValue());
			list.add(map);
		}
		for(int i=8;i<9;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("46")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("47")).getItemValue());
			map.put("zjtxnxkj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("48")).getItemValue());
			map.put("zjtxnxss",  ((QysdsReportsItemDeclare)table.getCellContentList().get("49")).getItemValue());
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("50")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("51")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("52")).getItemValue());
			list.add(map);
		}
		for(int i=9;i<10;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("53")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("54")).getItemValue());
			map.put("zjtxnxkj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("55")).getItemValue());
			map.put("zjtxnxss",  ((QysdsReportsItemDeclare)table.getCellContentList().get("56")).getItemValue());
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("57")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("58")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("59")).getItemValue());
			list.add(map);
		}
		for(int i=10;i<11;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("60")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("61")).getItemValue());
			map.put("zjtxnxkj",  "*");
			map.put("zjtxnxss",  "*");
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("62")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("63")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("64")).getItemValue());
			list.add(map);
		}
		for(int i=11;i<12;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("65")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("66")).getItemValue());
			map.put("zjtxnxkj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("67")).getItemValue());
			map.put("zjtxnxss",  ((QysdsReportsItemDeclare)table.getCellContentList().get("68")).getItemValue());
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("69")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("70")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("71")).getItemValue());
			list.add(map);
		}
		for(int i=12;i<13;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("72")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("73")).getItemValue());
			map.put("zjtxnxkj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("74")).getItemValue());
			map.put("zjtxnxss",  ((QysdsReportsItemDeclare)table.getCellContentList().get("75")).getItemValue());
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("76")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("77")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("78")).getItemValue());
			list.add(map);
		}
		for(int i=13;i<14;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("79")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("80")).getItemValue());
			map.put("zjtxnxkj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("81")).getItemValue());
			map.put("zjtxnxss",  ((QysdsReportsItemDeclare)table.getCellContentList().get("82")).getItemValue());
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("83")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("84")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("85")).getItemValue());
			list.add(map);
		}
		for(int i=14;i<15;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("86")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("87")).getItemValue());
			map.put("zjtxnxkj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("88")).getItemValue());
			map.put("zjtxnxss",  ((QysdsReportsItemDeclare)table.getCellContentList().get("89")).getItemValue());
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("90")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("91")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("92")).getItemValue());
			list.add(map);
		}
		for(int i=15;i<16;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("93")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("94")).getItemValue());
			map.put("zjtxnxkj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("95")).getItemValue());
			map.put("zjtxnxss",  ((QysdsReportsItemDeclare)table.getCellContentList().get("96")).getItemValue());
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("97")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("98")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("99")).getItemValue());
			list.add(map);
		}
		for(int i=16;i<17;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("100")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("101")).getItemValue());
			map.put("zjtxnxkj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("102")).getItemValue());
			map.put("zjtxnxss",  ((QysdsReportsItemDeclare)table.getCellContentList().get("103")).getItemValue());
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("104")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("105")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("106")).getItemValue());
			list.add(map);
		}
		for(int i=17;i<18;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("107")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("108")).getItemValue());
			map.put("zjtxnxkj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("109")).getItemValue());
			map.put("zjtxnxss",  ((QysdsReportsItemDeclare)table.getCellContentList().get("110")).getItemValue());
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("111")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("112")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("113")).getItemValue());
			list.add(map);
		}
		for(int i=18;i<19;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("zcyzzzje", ((QysdsReportsItemDeclare)table.getCellContentList().get("114")).getItemValue());
			map.put("zcyzjsjc", ((QysdsReportsItemDeclare)table.getCellContentList().get("115")).getItemValue());
			map.put("zjtxnxkj",  "*");
			map.put("zjtxnxss",  "*");
			map.put("bqzjtxekj", ((QysdsReportsItemDeclare)table.getCellContentList().get("116")).getItemValue());
			map.put("bqzjtxess",  ((QysdsReportsItemDeclare)table.getCellContentList().get("117")).getItemValue());
			map.put("nstze",  ((QysdsReportsItemDeclare)table.getCellContentList().get("118")).getItemValue());
			list.add(map);
		}			
		return list;
	}	
}
