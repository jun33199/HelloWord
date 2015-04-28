/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.cbmxbjrqy.processor;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.cbmxbjrqy.web.CbmxbjrqyForm;
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

public class CbmxbjrqyProcessor
implements Processor 
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
		
		CbmxbjrqyForm nbForm = (CbmxbjrqyForm) vo.getData(); //获得操作数据
		Connection conn = null;
		try
		{
			//获取数据库连接
			conn = SfDBResource.getConnection();
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsNewUtil.setQysdsReport(report, nbForm);
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_CBMXBJRQY);
			table.setTableName(CodeConstant.TABLE_NAME_CBMXBJRQY);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(), table);
			//获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_CBMXBJRQY);
			int[] arrs = {1,42};
			nbForm.setDataList(this.translate2Page(QysdsNewUtil.putSpace(table,arrs)));
		}catch(Exception ex)
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally
		{
			SfDBResource.freeConnection(conn);
		}
		return nbForm;
	}
	
	/**
	 * doSave    用于保存页面信息
	 * @param     vo 业务参数
	 * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException    当其他情况都会抛出异常信息
	 */
	private Object doSave(VOPackage vo) throws BaseException
	{
		
		CbmxbjrqyForm cbmxbjrqyForm = (CbmxbjrqyForm)vo.getData();
		Connection con = null;
		try
		{
			//获取数据库连接
			con = SfDBResource.getConnection();
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(cbmxbjrqyForm);
			
			//获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp=AppAccessFactory.getAInstance(con,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
			//更新审核状态为“保存成功”
			iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally 
		{
			SfDBResource.freeConnection(con);
		}
		return cbmxbjrqyForm;
		
	}
	/**
	 * doDelete    用于删除页面数据
	 * @param     vo 业务参数
	 * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException    当其他情况都会抛出异常信息
	 */
	private Object doDelete(VOPackage vo) throws BaseException
	{
		CbmxbjrqyForm cbmxbjrqyform = (CbmxbjrqyForm)vo.getData();
		Connection conn = null;
		try
		{
			//获取数据库连接
			conn = SfDBResource.getConnection();
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(cbmxbjrqyform);
			//获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			try
			{
				iApp.deleteSingleTable(report);	
				iApp.updateCheckStatus(report,"");
				QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
				table.setTableId(CodeConstant.TABLE_ID_CBMXBJRQY);
				table.setTableName(CodeConstant.TABLE_NAME_CBMXBJRQY);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
				table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_CBMXBJRQY);
				int[] arrs = {1,42};
				cbmxbjrqyform.setDataList(this.translate2Page(QysdsNewUtil.putSpace(table,arrs)));
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
		return cbmxbjrqyform;
	}
	/**
	 * doCheck    用于校验页面数据
	 * @param     vo 业务参数
	 * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException    当其他情况都会抛出异常信息
	 */
	private Object doCheck(VOPackage vo)throws BaseException
	{
		CbmxbjrqyForm cbmxbjrqyForm = (CbmxbjrqyForm)vo.getData();
		Connection con = null;
		try
		{
			//创建数据库连接
			con = SfDBResource.getConnection();
			//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report=this.translate2Interface(cbmxbjrqyForm);
			/**
			 * 单表校验
			 * 通过后继续执行；
			 * 未通过退出执行，页面提示校验结果信息
			 */
			Checker checker=CheckerFactory.getAInstance(con,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(con,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			//进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			cbmxbjrqyForm.setCheckList(listSingle);
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
		}catch(Exception ex)
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally
		{
			//释放数据库连接
			SfDBResource.freeConnection(con);
		}
		return cbmxbjrqyForm;
	}
	
	/**
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
	 * 页面数据结构-->接口数据结构
	 * @param ZcmxbForm 
	 * @return 企业所得税报表申明对象
	 */
	private QysdsReportsDeclare translate2Interface(CbmxbjrqyForm nbForm)
	{
		//企业所得税报表申明对象
		QysdsReportsDeclare report =new QysdsReportsDeclare();
		QysdsNewUtil.setQysdsReport(report, nbForm); //对report 进行一系列的设置
		//企业所得税报表内单表申明对象
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_CBMXBJRQY);
		table.setTableName(CodeConstant.TABLE_NAME_CBMXBJRQY);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		
		List list=nbForm.getDataList(); //取得存放操作数据的LIST
		
		for(int i=0;i<list.size();i++){
			HashMap map=(HashMap)list.get(i);
			QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
			item.setItemID((String) map.get("hc"));
			item.setItemValue((String) map.get("je"));
			item.setItemType("11");
			table.getCellContentList().put(item.getItemID(), item);			
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
		List list = new ArrayList();
		HashMap map = (HashMap)table.getCellContentList();
		Iterator it=map.keySet().iterator();
		while(it.hasNext()){
			HashMap backMap = new HashMap();
			String key=(String)it.next();
			QysdsReportsItemDeclare item=(QysdsReportsItemDeclare)map.get(key);
			backMap.put("hc", item.getItemID());
			backMap.put("je", item.getItemValue());
			list.add(backMap);
		}
		
		return list;
	}
}