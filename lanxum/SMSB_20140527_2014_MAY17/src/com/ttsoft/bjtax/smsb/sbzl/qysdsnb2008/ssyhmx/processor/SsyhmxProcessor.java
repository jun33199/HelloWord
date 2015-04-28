/**
 *
 */
package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.ssyhmx.processor;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.ssyhmx.web.SsyhmxForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.util.QysdsUtil2008;
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
 * Description:2008查帐征收企业所得税季报
 * </p>
 *
 * @author Ha Zhengze
 * @version 1.1
 */
public class SsyhmxProcessor implements Processor {
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
	    SsyhmxForm nbForm = (SsyhmxForm) vo.getData();
	    Connection conn = null;
		try {
			// 获取数据库接口
			conn = SfDBResource.getConnection();
			
			// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsUtil2008.setQysdsReport(report,nbForm);
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2008_5);
			table.setTableName(CodeConstant.TABLE_NAME_2008_5);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(),table);
			// 获取数据库接口，调用query方法进行数据查找
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2008_5);
			int []arra={1,47};
			nbForm.setDataList(this.translate2Page(QysdsUtil2008.putSpace(table,arra)));
			
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

	    return nbForm;
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
			SsyhmxForm form = (SsyhmxForm) vo.getData();
			Connection conn = null;
			try {
				//获取数据库连接
				conn = SfDBResource.getConnection();
				
				//将ActionForm中的数据结构6转换置入数据库接口参数要求的数据结构
				QysdsReportsDeclare report=this.translate2Interface(form);
				IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
				iApp.saveSingleTable(report);
//				更新审核状态为“保存成功”
				iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);
//				wlyd(form, conn);
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
    //对小型微利企业的认定
	private void wlyd(SsyhmxForm form, Connection conn) throws SQLException {
		String sql = "select yz from sbdb.sb_jl_qysdssbb_zb_nd t where t.nsrjsjdm = '" + form.getJsjdm() + "' and t.sbdm = '" + CodeConstant.TABLE_ID_2008_ZB + "' and t.hc = '25'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		String yz = null;
		if(rs.next()){
			yz = rs.getString("yz");
		}
		rs.close();
		st.close();
		HashMap map = getHcMap(form);
		double zbh25 = getValue(yz);
		double h34 = getValue((String)map.get("34"));
		double h45 = getValue((String)map.get("45"));
		double h46 = getValue((String)map.get("46"));
		String h47 = (String)map.get("47");
		String wlrdbs = "yes"; //对小型微利企业的认定标识
		if(h34>0){
			if("01".equals(h47)){
				if(!(h46<=30000000&&h45<=100&&zbh25<=300000)){
					wlrdbs = "no";
				}
			}
			else
			{
				if(!(h46<=10000000&&h45<=80&&zbh25<=300000)){
					wlrdbs = "no";
				}
			}
		}
		form.setWlrdbs(wlrdbs);
	}
		private double getValue(String str){
			if(str!=null&&!"".equals(str.trim())){
				return Double.parseDouble(str);
			}
			return 0;
		}
		
		/**
		 * doCheck   用于存储页面提交的详尽处理信息
		 * @param   vo 业务参数
		 * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
		 * @throws BaseException 当其他情况都会抛出异常信息
		 */
		private Object doCheck(VOPackage vo) throws BaseException 
		{
			SsyhmxForm SsyhmxForm = (SsyhmxForm) vo.getData();
			Connection conn = null;
			try
	         {
				//创建数据库连接
				conn = SfDBResource.getConnection();
				
				//将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
				QysdsReportsDeclare report=this.translate2Interface(SsyhmxForm);
				
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
				SsyhmxForm.setCheckList(listSingle);
//				wlyd(SsyhmxForm, conn);
			}
			catch (Exception ex)
			{ 
				//抛出异常
				ex.printStackTrace();
				throw ExceptionUtil.getBaseException(ex);
			}
			finally
			{
				//释放数据库连接
				SfDBResource.freeConnection(conn);
			}
			return SsyhmxForm;
		}

	  /**
	   * doDelete  用于删除页面提交的详尽处理信息
	   * @param    vo 业务参数
	   * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	   * @throws BaseException 当其他情况都会抛出异常信息
	   */

	  private Object doDelete(VOPackage vo) throws BaseException 
	  {
		  SsyhmxForm SsyhmxForm=(SsyhmxForm)vo.getData();
		  Connection conn = null;
			try {
				// 获取数据库接口
				conn = SfDBResource.getConnection();
				
				// 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
	            QysdsReportsDeclare report=this.translate2Interface(SsyhmxForm);
				
				//获取数据库接口，调用delete方法进行数据删除,iApp返回一个report对象
				IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
				iApp.deleteSingleTable(report);
				iApp.updateCheckStatus(report,"");
				QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
				table.setTableId(CodeConstant.TABLE_ID_2008_5);
				table.setTableName(CodeConstant.TABLE_NAME_2008_5);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
				int []arra={1,47};
				table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2008_5);
				SsyhmxForm.setDataList(this.translate2Page(QysdsUtil2008.putSpace(table,arra)));
				
			}catch (Exception ex) { 
				//抛出异常
				ex.printStackTrace();
				throw ExceptionUtil.getBaseException(ex);
			}
			finally {
				SfDBResource.freeConnection(conn);
			}
		  
	      return SsyhmxForm;
	  }

	  /**
	   * doUpdate  用于存储页面提交的详尽处理信息
	   * @param    vo 业务参数
	   * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	   * @throws BaseException 当其他情况都会抛出异常信息
	   */

	  private Object doUpdate(VOPackage vo) throws BaseException {

	    SsyhmxForm nbForm = (SsyhmxForm) vo.getData();

	    return nbForm;
	  }

	  /**
	   * 初始化
	   * @param nbForm 主表数据
	   * @throws BaseException 当其他情况都会抛出异常信息
	   */

	  private void initForm(SsyhmxForm nbForm) throws BaseException 
	  {
	   }
	  /**
		 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
		 * 页面数据结构-->接口数据结构
		 * @param SsyhmxForm 
		 * @return 企业所得税报表申明对象
		 */
	  
	  private QysdsReportsDeclare translate2Interface(SsyhmxForm form){
			//企业所得税报表申明对象
			QysdsReportsDeclare report =new QysdsReportsDeclare();
			QysdsUtil2008.setQysdsReport(report,form);
			System.out.println("---sbrq: " + report.getSbrq());
			System.out.println("---getSkssksrq: " + report.getSkssksrq());
			System.out.println("---getSkssjsrq: " + report.getSkssjsrq());
			//企业所得税报表内单表申明对象
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2008_5);
			table.setTableName(CodeConstant.TABLE_NAME_2008_5);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			
			List list=form.getDataList();
			for(int i=0;i<list.size();i++){
				    HashMap map=(HashMap)list.get(i);
					QysdsReportsItemDeclare item=new QysdsReportsItemDeclare();
					String hc=(String)map.get("hc");
					item.setItemID(hc);
					item.setItemValue((String)map.get("je"));
					item.setItemType("11");
					table.getCellContentList().put(item.getItemID(),item);
				  }	
			report.getTableContentList().put(table.getTableId(), QysdsUtil2008.cleanSpace(table));
			return report;
	  }
	  private HashMap getHcMap(SsyhmxForm form){
			List list=form.getDataList();
			HashMap reMap = new HashMap();
			for(int i=0;i<list.size();i++){
				    HashMap map=(HashMap)list.get(i);
					String hc=(String)map.get("hc");
					reMap.put(hc, (String)map.get("je"));
			}	
			return reMap;
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
			for(int i=0;i<list.size();i++)
			{
//				System.out.println("==2page list content==" + list.get(i));
			}

			return list;
		}
	  }
