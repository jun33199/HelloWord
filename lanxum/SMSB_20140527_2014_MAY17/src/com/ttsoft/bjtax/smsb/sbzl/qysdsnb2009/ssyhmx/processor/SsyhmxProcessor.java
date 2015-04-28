/**
 *
 */
package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.ssyhmx.processor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.ssyhmx.web.SsyhmxForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
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
 * Description:2009查帐征收企业所得税季报
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
			QysdsUtil2009.setQysdsReport(report,nbForm);
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2009_5);
			table.setTableName(CodeConstant.TABLE_NAME_2009_5);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(),table);
			// 获取数据库接口，调用query方法进行数据查找
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2009_5);
			int []arra={1,59};
			nbForm.setDataList(this.translate2Page(QysdsUtil2009.putSpace(table,arra)));
			//根据减免税备案事项填报情况，获取本表不允许填写的输入项列表 
            nbForm.setReadOnlyMap(this.getReadOnlyMap(nbForm));
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

	    return nbForm;
	  }
	  
		private Map  getReadOnlyMap(SsyhmxForm form) throws BaseException {  		
	  		
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			//构造全部输入项限制
			Map map=new HashMap();
			map.put("7", "true");
  			map.put("10", "true");
  			map.put("11", "true");
  			map.put("16", "true");
  			map.put("17", "true");
  			map.put("18", "true");
  			map.put("19", "true");
  			map.put("20", "true");
  			map.put("21", "true");
  			map.put("22", "true");
  			map.put("23", "true");
  			map.put("26", "true");
  			map.put("27", "true");
  			map.put("29", "true");
  			map.put("30", "true");
  			map.put("31", "true");
  			map.put("35", "true");
  			map.put("39", "true");
  			map.put("41", "true");
  			map.put("42", "true");
  			map.put("43", "true");
  			map.put("49", "true");		
  			map.put("52", "true");
  			map.put("53", "true");
  			map.put("54", "true");
  			map.put("55", "true");
  			map.put("56", "true");
  			map.put("57", "true");
  			map.put("58", "true");

			try {
				
				conn = SfDBResource.getConnection();
				String sql=" select distinct "+
				" case "+
				" when a.jmbasxdm='0010' then '7,' "+
				" when a.jmbasxdm='0020' then '10,' "+
				" when a.jmbasxdm='0030' then '11,' "+
				" when a.jmbasxdm='0040' then '29,' "+
				" when a.jmbasxdm='0060' then '30,' "+
				" when a.jmbasxdm='0070' then '31,' "+
				" when a.jmbasxdm='0080' then '35,' "+
				" when a.jmbasxdm='0090' then '52,' "+
				" when a.jmbasxdm='0100' then '53,' "+
				" when a.jmbasxdm='0110' then '54,' "+
				" when a.jmbasxdm='0120' then '55,' "+
				" when a.jmbasxdm='013B' then '39,' "+
				" when a.jmbasxdm='0170' then '49,' "+
				" when a.jmbasxdm='0180' then '56,' "+
				" when a.jmbasxdm='0190' then '58,' "+
				" when a.jmbasxdm='0200' then '57,' "+
				" else '9999,' end hc "+
				" from sfdb.sf_jl_qysdsjmsbajl a  "+
				" where a.jsjdm = '"+form.getJsjdm()+"' "+
				" and a.band='"+form.getSknd()+"' "+
				" and a.sqzt = '4' "+
				" union "+
				" select distinct "+
				" case "+
				" when b.nlmyjmxmdm='01' then '16,' "+
				" when b.nlmyjmxmdm='02' then '17,' "+
				" when b.nlmyjmxmdm='03' then '18,' "+
				" when b.nlmyjmxmdm='04' then '19,' "+
				" when b.nlmyjmxmdm='05' then '20,' "+
				" when b.nlmyjmxmdm='06' then '21,' "+
				" when b.nlmyjmxmdm='07' then '22,' "+
				" when b.nlmyjmxmdm='08' then '23,' "+
				" when b.nlmyjmxmdm='09' then '26,' "+
				" when b.nlmyjmxmdm='10' then '27,' "+
				" else '9999,' end hc "+
				" from sfdb.sf_jl_qysdsjmsba_05 b,sfdb.sf_jl_qysdsjmsbajl a "+
				" where b.basqwsh=a.basqwsh "+
				" and a.jsjdm = '"+form.getJsjdm()+"' "+
				" and a.band='"+form.getSknd()+"' "+
				" and a.sqzt = '4' "+
				" union "+
				" select distinct "+
				" case "+
				" when c.fjddm='01' then '41,' "+
				" when c.fjddm='07' then '42,' "+
				" when c.fjddm='10' then '43,' "+
				" else '9999,' end hc "+
				" from sfdb.sf_jl_qysdsjmsba_14b b, "+
				" sfdb.sf_jl_qysdsjmsbajl a , "+
				" dmdb.sf_dm_zysblx c "+
				" where b.basqwsh=a.basqwsh "+
				" and b.zysblxdm=c.zysblxdm "+
				" and a.jsjdm = '"+form.getJsjdm()+"' "+
				" and a.band='"+form.getSknd()+"' "+
				" and a.sqzt = '4' ";

				
				System.out.println(sql);
				System.out.println("into getReadOnlyMap");
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				StringBuffer sb=new StringBuffer();
				while(rs.next()){
					sb.append(rs.getString("HC"));					
				}
				String[] allowedArray=sb.toString().split(",");
				for(int i=0;i<allowedArray.length;i++){
					map.put(allowedArray[i], "false");
				}
			
				if (rs != null) {rs.close();}
				if (ps != null) {ps.close();}

			} catch (Exception ex) {
				
				// 抛出异常
				ex.printStackTrace();
				throw ExceptionUtil.getBaseException(ex);
			} finally {			
				SfDBResource.freeConnection(conn);
			}			
			return map;
	  		
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
			form.setReadOnlyMap(this.getReadOnlyMap(form));
			return form;
		}
    //对小型微利企业的认定
	private void wlyd(SsyhmxForm form, Connection conn) throws SQLException {
		String sql = "select yz from sbdb.sb_jl_qysdssbb_zb_nd t where t.nsrjsjdm = '" + form.getJsjdm() + "' and t.sbdm = '" + CodeConstant.TABLE_ID_2009_ZB + "' and t.hc = '25'";
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
			SsyhmxForm.setReadOnlyMap(this.getReadOnlyMap(SsyhmxForm));
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
				table.setTableId(CodeConstant.TABLE_ID_2009_5);
				table.setTableName(CodeConstant.TABLE_NAME_2009_5);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
				int []arra={1,59};
				table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2009_5);
				SsyhmxForm.setDataList(this.translate2Page(QysdsUtil2009.putSpace(table,arra)));
				
			}catch (Exception ex) { 
				//抛出异常
				ex.printStackTrace();
				throw ExceptionUtil.getBaseException(ex);
			}
			finally {
				SfDBResource.freeConnection(conn);
			}
			SsyhmxForm.setReadOnlyMap(this.getReadOnlyMap(SsyhmxForm));
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
			QysdsUtil2009.setQysdsReport(report,form);
			System.out.println("---sbrq: " + report.getSbrq());
			System.out.println("---getSkssksrq: " + report.getSkssksrq());
			System.out.println("---getSkssjsrq: " + report.getSkssjsrq());
			//企业所得税报表内单表申明对象
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2009_5);
			table.setTableName(CodeConstant.TABLE_NAME_2009_5);
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
			report.getTableContentList().put(table.getTableId(), QysdsUtil2009.cleanSpace(table));
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
