package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.syax.creports.Constants;
import com.syax.creports.bo.CheckResult;
import com.syax.creports.bo.qyqssds.QyqssdsBaJbxx;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsItemDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsTableDeclare;
import com.syax.creports.exception.BaseException;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.creports.util.DateUtils;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.main.web.QyqssdsTabelInfo2014;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.ExceptionUtil;

public class QyqssdsUtil2014 {
	
	public static void main(String[] args){

		System.out.println(preQuarter(SfDateUtil.getDate("20060428")));
		
		System.out.println(new Date(TinyTools.stringToDate("2014-01-01","yyyy-MM-dd").getTime()));
	}
	/**
	 * 设置报表对象基本信息
	 * @param report
	 * @param form
	 */
	public static void setQyqssdsReport(QyqssdsReportsDeclare report,QyqssdsBaseForm form){
		/**
		 * 基本信息
		 */
		QyqssdsBaJbxx jbxx=new QyqssdsBaJbxx();
		
		/**
		 * 基本信息(JBXX)-计算机代码
		 */
		jbxx.setJsjdm(form.getJsjdm());
		/**
		 * 基本信息(JBXX)-纳税人名称
		 */
		jbxx.setNsrmc(form.getNsrmc());
		/**
		 * 基本信息(JBXX)-所属经济类型
		 */
		jbxx.setSsjjlx(form.getSsjjlx());
		/**
		 * 基本信息(JBXX)-联系电话
		 */
		jbxx.setLxdh(form.getLxdh());
		/**
		 * 基本信息(JBXX)-所属行业
		 */
		jbxx.setSshy(form.getSshy());
		
//		/**
//		 * 基本信息(JBXX)-工资管理形式
//		 */
//		jbxx.setGzglxs("");
//		/**
//		 * 基本信息(JBXX)-减免类型
//		 */
//		jbxx.setJmlx("");
		
		jbxx.setBbmsf(GetJbxxBbmsf(form));
//		jbxx.setBbmsf("0101,0102,0103,0104");
		
		report.setJbxx(jbxx);
		
		/**
		 * 应用编号
		 */
		report.setAppid(Constants.CREPORTS_APPID_QYQSSDS);
		/**
		 * 版本号
		 */
		report.setVersion(CodeConstant.QYQSSDS_VERSION_2014);
		/**
		 * 纳税人计算机代码
		 */
		report.setNsrjsjdm(form.getJsjdm());
		/**
		 * 纳税人名称
		 */
		report.setNsrmc(form.getNsrmc());
		/**
		 * 报表期类型
		 */
		report.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
		/**
		 * 期号
		 */
		report.setQh("1");
		/**
		 * 税款所属开始日期   
		 * 改为 清算申报开始日期
		 */
		System.out.println("************"+form.getQssbksrq());
		if(form.getQssbksrq()!=null && !form.getQssbksrq().equals("")){
			report.setQssbksrq(new Date(TinyTools.stringToDate(form.getQssbksrq(),"yyyy-MM-dd").getTime()) );
			System.out.println(new Date(TinyTools.stringToDate(form.getQssbksrq(),"yyyy-MM-dd").getTime()) );
			System.out.println("************"+report.getQssbksrq());
		}
		/**
		 * 税款所属结束日期
		 * 改为 清算申报结束日期
		 */
		if(form.getQssbjsrq()!=null && !form.getQssbjsrq().equals("")){
			report.setQssbjsrq(new Date(TinyTools.stringToDate(form.getQssbjsrq(),"yyyy-MM-dd").getTime()) );
		}
		/**
		 * 申报日期
		 * 改为清算填报当前日期
		 */
		if(form.getTbrq()!=null && !form.getTbrq().equals("")){
			report.setSbrq(new Date(TinyTools.stringToDate(form.getTbrq(),"yyyyMMdd").getTime()) );
			System.out.println(form.getTbrq());
			
		}
		
		/**
		 * 税款年度  为清算备案完成所在年度
		 */
		report.setSknd(form.getSknd());
		/**
		 * 税务机关组织机构代码
		 */
		report.setSwjgzzjgdm(form.getSwjgzzjgdm());
		/**
		 * 税务计算机代码
		 */
		report.setSwjsjdm(form.getSwjsjdm());
		/**
		 * 区县代码
		 */
		report.setQxdm(form.getQxdm());
		/**
		 * 录入人
		 */
		report.setLrr(form.getLrr());
		/**
		 * 录入日期
		 */
		if(form.getLrrq()!=null && !form.getLrrq().equals("")){
//			report.setLrrq(new Date(TinyTools.stringToDate(form.getLrrq(),"yyyyMMdd").getTime()) );
		}
		report.setLrrq(new java.sql.Date(new java.util.Date().getTime()) );
		/**
		 * 创建人
		 */
		report.setCjr(form.getLrr());
		/**
		 * 创建日期
		 */
		if(form.getCjrq()!=null && !form.getCjrq().equals("")){
//			report.setCjrq(new Date(TinyTools.stringToDate(form.getCjrq(),"yyyyMMdd").getTime()));
		}
		report.setCjrq(new java.sql.Date(new java.util.Date().getTime()));
	}
	
	/**
	 * 查询企业所得税纳税人基本信息
	 * @param conn 数据库连接
	 * @param form 企业所得税Form基类
	 * @return
	 * @throws SQLException
	 */
	public static String GetJbxxBbmsf(QyqssdsBaseForm form) {
		
		String bbmsf="";
		Connection conn = null;
		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			StringBuffer buffer=new StringBuffer();
			//SELECT
			buffer.append(" SELECT ");
			//读取字段
			buffer.append(" NSRJSJDM,NSRSBH,NSRMC,BBMSF");
			//FROM
			buffer.append(" FROM SBDB.SB_JL_QYQSSDSBA_NSRJBXXB T1 WHERE T1.NSRJSJDM=? ");
			
			Debug.out("企业所得税-基本信息查询SQL");
			System.out.println(buffer.toString());
			ps=conn.prepareStatement(buffer.toString());
			System.out.println("1-"+form.getJsjdm());
			
			ps.setString(1,form.getJsjdm());
			
			rs= ps.executeQuery();
			if(rs.next()){
				bbmsf=rs.getString("BBMSF");
			}else{
				throw new ApplicationException("没有该纳税人的基本信息登记表数据！");
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		return bbmsf;
	}
	
	/**
	 * 查询企业所得税纳税人基本信息
	 * @param conn 数据库连接
	 * @param form 企业所得税Form基类
	 * @return
	 * @throws SQLException
	 */
	public static Object queryQyqssdsJbxx(Connection conn,QyqssdsBaseForm form) throws Exception{
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		StringBuffer buffer=new StringBuffer();
		//SELECT
		buffer.append(" SELECT ");
		//读取字段
		buffer.append(" NSRJSJDM,NSRSBH,NSRMC,SSJJLX, ");
		buffer.append(" LXDH,JYDZ,SSHY,QSLLRY, ");
		buffer.append(" SWJGZZJGDM,SUBSTR(SWJGZZJGDM,1,2) QXDM, BASHZTBS, ");
		buffer.append(" TO_CHAR(BASHTGRQ,'YYYYMMDD') BASHTGRQ, ");
		buffer.append(" TO_CHAR(QSSBKSRQ,'YYYY-MM-DD') QSSBKSRQ, ");
		buffer.append(" TO_CHAR(QSSBJSRQ,'YYYY-MM-DD') QSSBJSRQ,SQLXDM,SBSHZTBS,  ");
		//子查询-根据税务机关组织代码查询税务机关组织机构名称
		buffer.append(" (SELECT T2.SWJGZZJGMC FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJGZZJGMC, ");
		buffer.append(" (SELECT T2.JSJDM FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJSJDM ");
		//FROM
		buffer.append(" FROM SBDB.SB_JL_QYQSSDSBA_NSRJBXXB T1 WHERE T1.NSRJSJDM=?");
		
		Debug.out("企业所得税-基本信息查询SQL");
		System.out.println(buffer.toString());
		
		ps=conn.prepareStatement(buffer.toString());
		System.out.println("1-"+form.getJsjdm());
		ps.setString(1,form.getJsjdm());
	
		rs= ps.executeQuery();
		if(rs.next()){
			//纳税人计算机代码
			form.setJsjdm(rs.getString("NSRJSJDM"));
			//纳税人识别号－税务登记证号
			form.setNsrsbh(rs.getString("NSRSBH"));
			//纳税人名称
			form.setNsrmc(rs.getString("NSRMC"));
			
			//期号
			form.setQh("1");
			//报表期类型
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			/*税款所属日期不从基本信息表取得*/
			//税款所属开始日期
			form.setBaShtgrq(rs.getString("BASHTGRQ"));	
			form.setBaShztbs(rs.getString("BASHZTBS"));	
			if(!form.getBaShztbs().equals("2")){
				throw new ApplicationException("该纳税人的企业清算所得税备案数据还没有审核通过不能填报清算数据！");
			}
			if(form.getBaShtgrq()!=null){
				form.setQssbksrq(DateUtils.toHyphenDate(form.getBaShtgrq()));
			}
			
			String qssbksrq=rs.getString("QSSBKSRQ");
			String qssbjsrq=rs.getString("QSSBJSRQ");
			form.setIsBcsbrq("0");
			if(qssbksrq!=null && qssbksrq.length()>0){
				form.setQssbksrq(qssbksrq);
				form.setIsBcsbrq("1");
			}
			if(qssbjsrq!=null && qssbjsrq.length()>0){
				form.setQssbjsrq(qssbjsrq);
			}
			//所属经济类型
			form.setSsjjlx(rs.getString("SSJJLX"));
			//联系电话
			form.setLxdh(rs.getString("LXDH"));
			//经营地址
			form.setJydz(rs.getString("JYDZ"));
			//所属行业
			form.setSshy(rs.getString("SSHY"));
			//清算组或清算联络人员
			form.setQsllry(rs.getString("QSLLRY"));
			//税务机关组织机构代码
			form.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
			//税务机关组织机构名称
			form.setZgswjgzzjgmc(rs.getString("SWJGZZJGMC"));
			//税务计算机代码
			form.setSwjsjdm(rs.getString("SWJSJDM"));
			//区县代码
			form.setQxdm(rs.getString("QXDM"));
			//定制纳税人所需填写的相关纳税申报表
			form.setTableList(getTableList());
			//HTML表链接
			form.setLinkUrlHTML(getLinkUrlHtml(form.getTableList(),form));
			//构造表链接map容器
			form.setLinkMap(generateLinkMap(form.getTableList()));
			//申请类型代码
			form.setSqlx(rs.getString("SQLXDM"));
			//申报审核状态标识
			form.setSbShztbs(rs.getString("SBSHZTBS")==null?"":rs.getString("SBSHZTBS"));
			
		}else{
			throw new ApplicationException("没有该纳税人的企业清算所得税备案数据！");
		}
		
		rs.close();
		ps.close();
		
		return form;
	}
	
	/**
	 * 查询纳税人基本信息(从登记表获取)
	 * @param conn 数据库连接
	 * @param form 企业所得税Form基类
	 * @return
	 * @throws SQLException
	 */
	public static Object queryNsrdjxx(Connection conn,QyqssdsBaseForm form) throws Exception{
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		StringBuffer buffer=new StringBuffer();
		//SELECT
		buffer.append(" SELECT ");
		//读取字段
		buffer.append(" JSJDM,SWDJZH,NSRMC,DJZCLXDM,");
		buffer.append(" JYDZLXDM,JYDZ,GJBZHYDM,");
		buffer.append(" SWJGZZJGDM,SUBSTR(SWJGZZJGDM,1,2) QXDM,");
		//子查询-根据税务机关组织代码查询税务机关组织机构名称
		buffer.append(" (SELECT T2.SWJGZZJGMC FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJGZZJGMC,");
		buffer.append(" (SELECT T2.JSJDM FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJSJDM");
		//FROM
		buffer.append(" FROM DJDB.DJ_JL_JBSJ T1 WHERE T1.JSJDM=? ");
		
		System.out.println("企业所得税-基本信息查询SQL");
		System.out.println(buffer.toString());
		
		ps=conn.prepareStatement(buffer.toString());
		ps.setString(1,form.getJsjdm());
		
		rs= ps.executeQuery();
		if(rs.next()){
			//纳税人计算机代码
			form.setJsjdm(rs.getString("JSJDM"));
			//纳税人识别号－税务登记证号
			form.setNsrsbh(rs.getString("SWDJZH"));
			//纳税人名称
			form.setNsrmc(rs.getString("NSRMC"));
			//所属经济类型
			form.setSsjjlx(rs.getString("DJZCLXDM"));
			//联系电话
			form.setLxdh(rs.getString("JYDZLXDM"));
			//经营地址
			form.setJydz(rs.getString("JYDZ"));
			//所属行业
			form.setSshy(rs.getString("GJBZHYDM"));
			//税务机关组织机构代码
			form.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
			//税务机关组织机构名称
			form.setZgswjgzzjgmc(rs.getString("SWJGZZJGMC"));
			//税务计算机代码
			form.setSwjsjdm(rs.getString("SWJSJDM"));
			//区县代码
			form.setQxdm(rs.getString("QXDM"));
		}else{
			throw new ApplicationException("没有该纳税人的登记信息或者没有权限查看该纳税人信息！");
		}
		
		rs.close();
		ps.close();
		
		return form;
	}
	
	public static Object queryDjxxByInterfaceDJ(Connection conn,QyqssdsBaseForm form,UserData ud) throws Exception{
		SWDJJBSJ djsj = null;
		try {
			// 获得企业登记基本信息
			djsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
			//纳税人识别号
			form.setNsrsbh(djsj.getSwdjzh()); 
			// 纳税人名称
			form.setNsrmc(djsj.getNsrmc()); 
			// 所属经济类型-登记注册类型代码
			form.setSsjjlx(djsj.getDjzclxdm());
			// 注册地址联系电话
			form.setLxdh(djsj.getZcdzlxdh()); 
			// 经营地址
			form.setJydz(djsj.getJydz());
			// 所属行业代码
			form.setSshy(djsj.getGjbzhydm());
			// 税务机关组织机构代码
			form.setSwjgzzjgdm(djsj.getSwjgzzjgdm());
			// 税务机关组织机构名称
			form.setZgswjgzzjgmc(djsj.getSwjgzzjgmc()); 
			// 区县代码
			form.setQxdm(djsj.getQxdm());
			form.setQxdm(djsj.getSwjgzzjgdm().substring(0, 2));
			return form;
		} catch (Exception ex1) {
			throw new ApplicationException("没有该纳税人的登记信息或者没有权限查看该纳税人信息！");
		}
	}
	/**
	 * 过滤页面List中的空值
	 * @param list
	 */
	public static List filterList(List list){
		List rtnList=new ArrayList();
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			Iterator it=map.keySet().iterator();
			while(it.hasNext()){
				String key=(String)it.next();
				if (map.get(key)!=null && !"".equals((String)map.get(key))){
					rtnList.add(map);
					break;
				}
			}
		}
		return rtnList;
	}
	
	/**
	 * 所需填写的相关纳税申报表
	 * @param 
	 * @return 
	 */
	private static List getTableList(){
		
		List list=new ArrayList();
		
		QyqssdsTabelInfo2014 qyqssdsZb =new QyqssdsTabelInfo2014();
		//主表
		qyqssdsZb.setTableID(CodeConstant.QYQSSDS_TABLE_ID_2014_ZB);
		qyqssdsZb.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_ZB);
		qyqssdsZb.setURL("qyqssdsZbAction2014.do?actionType=Show");
		list.add(qyqssdsZb);

		//附表一 资产处置损益明细表
		QyqssdsTabelInfo2014 qyqssds_table_1 =new QyqssdsTabelInfo2014();
		qyqssds_table_1.setTableID(CodeConstant.QYQSSDS_TABLE_ID_2014_1);
		qyqssds_table_1.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_1);
		qyqssds_table_1.setURL("qyqssdsFb1Action2014.do?actionType=Show");
		list.add(qyqssds_table_1);
		
		//附表二 负债清偿损益明细表
		QyqssdsTabelInfo2014 qyqssds_table_2 =new QyqssdsTabelInfo2014();
		qyqssds_table_2.setTableID(CodeConstant.QYQSSDS_TABLE_ID_2014_2);
		qyqssds_table_2.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_2);
		qyqssds_table_2.setURL("qyqssdsFb2Action2014.do?actionType=Show");
		list.add(qyqssds_table_2);
		
		//附表三  剩余财产计算和分配明细表
		QyqssdsTabelInfo2014 qyqssds_table_3 =new QyqssdsTabelInfo2014();
		qyqssds_table_3.setTableID(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
		qyqssds_table_3.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_3);
		qyqssds_table_3.setURL("qyqssdsFb3Action2014.do?actionType=Show");
		list.add(qyqssds_table_3);
		
		return list;
	}
	
	/**
	 * 返回一个HTML片断，内容为表链接
	 * @param list 定制列表
	 * @return
	 * @throws BaseException 
	 */
	private static String getLinkUrlHtml(List list){
		StringBuffer buffer=new StringBuffer();
		
		//分3列展现表链接,每列最多展示row个链接 
		int row=6;
		StringBuffer buffer1=new StringBuffer();
		StringBuffer buffer2=new StringBuffer();
		StringBuffer buffer3=new StringBuffer();
		
		buffer1.append("<TD>");
		buffer2.append("<TD>");
		buffer3.append("<TD>");
		
		for(int i=1;i<=list.size();i++){
			QyqssdsTabelInfo2014 table=(QyqssdsTabelInfo2014)list.get(i-1);
			if(i<=row){
				buffer1.append("<DIV>");
				buffer1.append("<A HREF='");
				buffer1.append(table.getURL()+"'>");
				buffer1.append(table.getTableName());
				buffer1.append("</A>");
				buffer1.append("</DIV>");
			}
			if(i<=2*row && i>row){
				buffer2.append("<DIV>");
				buffer2.append("<A HREF='");
				buffer2.append(table.getURL()+"'>");
				buffer2.append(table.getTableName());
				buffer2.append("</A>");
				buffer2.append("</DIV>");
			}
			if( i>2*row){
				buffer3.append("<DIV>");
				buffer3.append("<A HREF='");
				buffer3.append(table.getURL()+"'>");
				buffer3.append(table.getTableName());
				buffer3.append("</A>");
				buffer3.append("</DIV>");
			}
		}
		
		buffer1.append("</TD>");
		buffer2.append("</TD>");
		buffer3.append("</TD>");
		
		buffer.append(buffer1.toString()+buffer2.toString()+buffer3.toString());
		
		return buffer.toString();
	}
	
	/**
	 * 返回一个HTML片断，内容为表链接
	 * @param list 定制列表
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws BaseException 
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws BaseException 
	 */
	public static String getLinkUrlHtml(List list,QyqssdsBaseForm form) throws Exception {
		StringBuffer buffer=new StringBuffer();
		QyqssdsReportsDeclare report=new QyqssdsReportsDeclare();
		setQyqssdsReport(report,form);
		Connection conn = null; 
		try {
			conn = SfDBResource.getConnection();
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			
			//分3列展现表链接,每列最多展示row个链接 
			int row=2;
			StringBuffer buffer1=new StringBuffer();
			StringBuffer buffer2=new StringBuffer();
			StringBuffer buffer3=new StringBuffer();
			
			buffer1.append("<TD>");
			buffer2.append("<TD>");
			buffer3.append("<TD>");
			
			for(int i=1;i<=list.size();i++){
				QyqssdsTabelInfo2014 table=(QyqssdsTabelInfo2014)list.get(i-1);
				QyqssdsReportsTableDeclare iTable=new QyqssdsReportsTableDeclare();
				iTable.setTableId(table.getTableID());
				report.getTableContentList().clear();
				report.getTableContentList().put(table.getTableID(),iTable);
				String shbz=iApp.queryCheckStatus(report);
				if(i<=row){
					buffer1.append("<DIV>");
					if(shbz.equals(Constants.QYQSSDS_SHZT_SINGLE_PASS) || shbz.equals(Constants.QYQSSDS_SHZT_ALL_PASS)){
						buffer1.append("<INPUT TYPE='CHECKBOX' CHECKED DISABLED/>");
					}else{
						buffer1.append("<INPUT TYPE='CHECKBOX' DISABLED/>");
					}
					buffer1.append("<A ");
					buffer1.append("id='TableID_"+table.getTableID()+"' ");
					buffer1.append(" onClick='return link2Table(\""+table.getTableID()+"\");'");
					buffer1.append(" HREF='"+table.getURL()+"'>");
					buffer1.append(table.getTableName());
					buffer1.append("</A>");
					buffer1.append("</DIV>");
				}
				if(i<=2*row && i>row){
					buffer2.append("<DIV>");
					if(shbz.equals(Constants.QYQSSDS_SHZT_SINGLE_PASS) || shbz.equals(Constants.QYQSSDS_SHZT_ALL_PASS)){
						buffer2.append("<INPUT TYPE='CHECKBOX' CHECKED DISABLED/>");
					}else{
						buffer2.append("<INPUT TYPE='CHECKBOX' DISABLED/>");
					}
					buffer2.append("<A ");
					buffer2.append("id='TableID_"+table.getTableID()+"' ");
					buffer2.append(" onClick='return link2Table(\""+table.getTableID()+"\");'");
					buffer2.append(" HREF='"+table.getURL()+"'>");
					buffer2.append(table.getTableName());
					buffer2.append("</A>");
					buffer2.append("</DIV>");
				}
				if( i>2*row){
					buffer3.append("<DIV>");
					if(shbz.equals(Constants.QYQSSDS_SHZT_SINGLE_PASS) || shbz.equals(Constants.QYQSSDS_SHZT_ALL_PASS)){
						buffer3.append("<INPUT TYPE='CHECKBOX' CHECKED DISABLED/>");
					}else{
						buffer3.append("<INPUT TYPE='CHECKBOX' DISABLED/>");
					}
					buffer3.append("<A ");
					buffer3.append("id='TableID_"+table.getTableID()+"' ");
					buffer3.append(" onClick='return link2Table(\""+table.getTableID()+"\");'");
					buffer3.append(" HREF='"+table.getURL()+"'>");
					buffer3.append(table.getTableName());
					buffer3.append("</A>");
					buffer3.append("</DIV>");
				}
			}
			
			buffer1.append("</TD>");
			buffer2.append("</TD>");
			buffer3.append("</TD>");
			
			buffer.append(buffer1.toString()+buffer2.toString()+buffer3.toString());
			
			return buffer.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}finally{
			SfDBResource.freeConnection(conn);
		}
	}
	
	/**
	 * 过滤放入table数据中的空格
	 * @param table
	 * @return
	 */
	public static QyqssdsReportsTableDeclare cleanSpace(QyqssdsReportsTableDeclare table){
		Iterator it = table.getCellContentList().keySet().iterator();
		Map map=new HashMap();
		while(it.hasNext()){
			String key = (String)it.next();
			
			QyqssdsReportsItemDeclare item =(QyqssdsReportsItemDeclare)table.getCellContentList().get(key);
			if(!"".equals(item.getItemValue().trim())){
				map.put(key, item);
				Debug.out("key--"+key);
			}				
		}
		table.setCellContentList(map);
		return table;
		
	}
	
	/**
	 * 把存放数据时过滤掉的空格复原
	 * @param table
	 * @param a
	 * @return
	 */
	public static QyqssdsReportsTableDeclare putSpace(QyqssdsReportsTableDeclare table,int arrs[]) {
		System.out.println("**显示qyqssdsNewUtil中的putSpace()**");
		for(int j=1;j<=arrs.length;j=j+2){			
			System.out.println("j___  "+j+"***"+arrs.length);
			for(int i=arrs[j-1];i<=arrs[j];i++){
				QyqssdsReportsItemDeclare item =(QyqssdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(i));
				if(item==null ){
					QyqssdsReportsItemDeclare item1 = new QyqssdsReportsItemDeclare();
					item1.setItemID(String.valueOf(i));
					item1.setItemValue("");
					item1.setItemType("11");
					table.getCellContentList().put(String.valueOf(i), item1);
				}else if(item!=null && item.getItemValue()!=null && "".equals(item.getItemValue().trim())){
					QyqssdsReportsItemDeclare item1 = new QyqssdsReportsItemDeclare();
					item1.setItemID(String.valueOf(i));
					item1.setItemValue("");
					item1.setItemType("11");
					table.getCellContentList().put(String.valueOf(i), item1);
				}				
			}
		}	
		return table;
	}
	
	/**
	 * 根据校验接口返回的校验结果List生成页面提示信息（HTML）
	 * @param list
	 * @return
	 */
	public static String getCheckResults(List list){
		
		StringBuffer buffer =new StringBuffer();
		
		//如果校验结果List为null,或其长度为零，说明校验通过，返回空字符串
		if(list==null || ( list!=null && list.size()==0)){
			return "";
		}
		
		buffer.append("<html><link href='../../../css/text.css' rel='stylesheet' type='text/css'><title>"+"校验结果</title>");
		buffer.append("<table border='1' cellspacing='0' class='table-99' align='center'>");
		buffer.append("<tr>");
		buffer.append("<td class='2-td1-center'>");
		buffer.append("校验结果列表");
		buffer.append("</td>");
		buffer.append("</tr>");
		
		for(int i=0;i<list.size();i++){
			CheckResult checkResult=(CheckResult)list.get(i);
			buffer.append("<tr>");
			buffer.append("<td class='2-td2-align-left'>");
			buffer.append(String.valueOf(i+1)+"、  "+checkResult.getResultDescription().replaceAll("\"","&quot;")+"&nbsp;");
			buffer.append("</td>");
			buffer.append("</tr>");
		}
		
		buffer.append("</html>");
		System.out.println("-----------------------------buffer------------");
		System.out.println(buffer.toString());
		return buffer.toString();
	}
	
	/**
	 * 将指定日期转换为 Timestamp
	 * @param date  指定日期格式为 "20030908"
	 * @return  Timestamp
	 */
	public static Timestamp getTimestamp(String dateStr) {
		
		java.util.Date date = null; 
		
		String tmp = SBStringUtils.trim(dateStr);
		tmp = SBStringUtils.killNull(tmp);
		tmp = replaceAll(tmp,"-","");
//		System.out.println(tmp);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			date = sdf.parse(tmp);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			
		}
		
		return second2Day(date);
	}
	
	
//	将java.util.Date转换为的java.sql.Timestamp
	/**将java.util.Date转换为的java.sql.Timestamp
	 * @param java.util.Date
	 * @return java.sql.Timestamp
	 */
	public static java.sql.Timestamp second2Day(java.util.Date date)
	{
		if (date==null)
			return new Timestamp(System.currentTimeMillis());
		java.sql.Timestamp tempStamp = null;
		
		try
		{
			String tempStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
			tempStamp = java.sql.Timestamp.valueOf(tempStr +" 00:00:00.000");
		}
		catch (Exception ex) {}
		
		return tempStamp;
	}
	
	
	
	/**
	 * 替换字符串
	 * @param original 源字符串
	 * @param find 查找字符串
	 * @param replacement 替换字符串
	 * @return 替换后的字符串
	 */
	public final static String replaceAll(String original, String find,
			String replacement) {
		StringBuffer buffer = new StringBuffer(original);
		
		int idx = original.length();
		int offset = find.length();
		
		while ( (idx = original.lastIndexOf(find, idx - 1)) > -1) {
			buffer.replace(idx, idx + offset, replacement);
		}
		
		return buffer.toString();
	}
	
	/**
	 * 将指定日期转换为 Timestamp
	 * @param date  指定日期格式为 "20030908"
	 * @return  Timestamp
	 */
	public static Timestamp getNxetTimestamp(String dateStr) {
		
		java.util.Date date = null; 
		
		String tmp = SBStringUtils.trim(dateStr);
		tmp = SBStringUtils.killNull(tmp);
		tmp = replaceAll(tmp,"-","");
//		System.out.println(tmp);
		
		
		try {			    	
			java.util.Date dateD = SBStringUtils.getDate(tmp, "yyyyMMdd");
			
			date = TinyTools.addDay(1, dateD);    	
		}
		catch (Exception ex) {
			ex.printStackTrace();
			
		}
		
		return second2Day(date);
	}
	
	/**
	 * 返回表链接URL
	 * @param key 表信息
	 * @return
	 */
	public static String getTableURL(QyqssdsBaseForm form,String key){
		return (String)form.getLinkMap().get(key);
	}
	
	/**
	 * 判断当前表是否为最后一张表
	 * @param form
	 * @param key
	 * @return
	 */
	public static String isLastTable(QyqssdsBaseForm form,String key){
		QyqssdsTabelInfo2014 table=(QyqssdsTabelInfo2014)form.getTableList().get(form.getTableList().size()-1);
		if(key.equalsIgnoreCase(table.getTableID())){
			return "yes";
		}else{
			return "no";
		}
	}
	
//	/**
//	* 取从一率和企业征税类型,用于页面校验
//	* 
//	* @param form
//	* @throws BaseException
//	*/
//	public static String getZsfsdm(QyqssdsNewForm form) throws BaseException {
//	String re = "";
//	
//	// 当前时间
////	Timestamp sbrq = new Timestamp(System.currentTimeMillis());
//	//从申报页面取得申报日期和税款所属日期
//	Timestamp sbrq = QyqssdsUtil2009.getNxetTimestamp(form.getSkssjsrq());
//	
////	Map getsbjd = this.quarterSkssrq(sbrq);
//	Timestamp skssksrq = QyqssdsUtil2009.getTimestamp(form.getSkssksrq());
//	Timestamp skssjsrq = QyqssdsUtil2009.getTimestamp(form.getSkssjsrq());
//	
//	
//	System.out.println(form.getJsjdm()+"sbrq："+sbrq);
//	System.out.println(form.getJsjdm()+"skssksrq："+skssksrq);
//	System.out.println(form.getJsjdm()+"skssjsrq："+skssjsrq);
//	
//	ServiceProxy proxy = new ServiceProxy();
//	
//	String bblx = form.getBbqlx();
//	String jsjdm = form.getJsjdm();
//	
//	// 查询税费接口
//	QyqssdsSet qyqssdsSet = null;
//	
//	
//	
//	try {
//	if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS)) {
//	qyqssdsSet = proxy.getQyqssdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
//	CodeConstant.SFGL_QYQSSDS_BBFS_NB);
//	} else if(bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR)){
//	
//	if(form.getQh()==null || (form.getQh()!=null && form.getQh().trim().equals(""))){
//	/*期号不能为空，如果为空抛出异常*/
//	throw new ApplicationException("系统发生异常，期号为空，请与系统管理员联系！");
//	}
//	
//	System.out.println("form.getQh()::"+form.getQh());
//	
//	
//	if("4".equals(form.getQh())){
//	/*如果为第四季度，或取企业所得税认定信息时按年报来获取*/
//	qyqssdsSet = proxy.getQyqssdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
//	CodeConstant.SFGL_QYQSSDS_BBFS_NB);
//	}else{
//	qyqssdsSet = proxy.getQyqssdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
//	CodeConstant.SFGL_QYQSSDS_BBFS_JB);
//	}
//	}			
//	
//	
//	} catch (com.ttsoft.framework.exception.BaseException e) {
//	e.printStackTrace();
//	throw ExceptionUtil.getBaseException(e);
//	}
//	
//	// 1、查询企业征收方式
//	Zsfs zsfs = qyqssdsSet.getZsfs();
//	if (zsfs != null) {
//	
//	re = (zsfs.getZsfsdm()==null?"":zsfs.getZsfsdm());
//	
//	}
//	return re;
//	
//	}
	
	/**
	 * 构造表链接Map（LinkMap）
	 */
	private static Map generateLinkMap(List list){
		
		Map linkMap=new HashMap();
		
		for (int i = 0; i < list.size(); i++) {
			
			//当前表
			QyqssdsTabelInfo2014 table=(QyqssdsTabelInfo2014)list.get(i);
			if( i!=list.size()-1 ){
				
					
				//当前表的下一张表
				QyqssdsTabelInfo2014 next_table=(QyqssdsTabelInfo2014)list.get(i+1);
				//为当前表设置下一张表链接
				linkMap.put("N_"+table.getTableID(),next_table.getURL());				
				//为下一张表设置上一张表链接
				linkMap.put("P_"+next_table.getTableID(),table.getURL());			
			}else{
				//最后一张表
				QyqssdsTabelInfo2014 pre_table=(QyqssdsTabelInfo2014)list.get(i-1);
				linkMap.put("P_"+table.getTableID(),pre_table.getURL());			
			}
		}


		return linkMap;
	}
	
	/**
	 * 取得传入日期所在的季度
	 * @param curDate 日期
	 * @return String 季度
	 */
	public static String preQuarter (java.util.Date curDate)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(curDate);
		int month = calendar.get(calendar.MONTH);
		int year = calendar.get(calendar.YEAR);
		System.out.println(month);
		int jd = month / 3;
		jd++;
		return new Integer(jd).toString();
	}
	
	
	/**
	 * 数据权限过滤
	 * @param qxdm
	 * @param band
	 * @param yhid
	 * @param jmbasxdm
	 * @param conn
	 * @return
	 * @throws BaseException
	 * @throws com.ttsoft.framework.exception.BaseException 
	 */
	public static void getAlertStrWhenAdd(String jsjdm,UserData ud) throws com.ttsoft.framework.exception.BaseException

{
		
		
		SWDJJBSJ djsj = null;
		try {
			// 获得企业登记基本信息
			djsj = InterfaceDj.getJBSJ_New(jsjdm, ud);
		} catch (Exception ex1) {
			throw ExceptionUtil.getBaseException(ex1);
		}		
		String nsrSwjgzzjgdm=djsj.getSwjgzzjgdm();
		String ssdwdm=ud.getSsdwdm();		
		String yhjb=ud.getYhjb();
		
		if(yhjb.equals("40")){
			if(!ssdwdm.substring(0,2).equals(nsrSwjgzzjgdm.substring(0,2))){
				throw new ApplicationException("该纳税人并非所属管户，不能对该纳税人进行备案！");
			}
		}
		if(yhjb.equals("30")){
			if(!ssdwdm.equals(nsrSwjgzzjgdm)){
				throw new ApplicationException("该纳税人并非所属管户，不能对该纳税人进行备案！");
			}
		}
		
		
	}
	
	/**
	 * 更新所有表中的申报开始日期和申报结束日期
	 * @param conn 数据库连接
	 * @param form 企业所得税Form基类
	 * @return
	 * @throws SQLException
	 */
	public static Object updateAllDate(Connection conn,QyqssdsBaseForm form) throws Exception{
		
		PreparedStatement ps=null;
		
		StringBuffer bufferZbNd=new StringBuffer();
		StringBuffer bufferNsrjbxxb=new StringBuffer();
		StringBuffer bufferCzzb=new StringBuffer();
		bufferZbNd.append("update SBDB.SB_JL_QYQSSDSSBB_ZB_ND set qssbksrq=to_date(?,'YYYY-MM-DD'),qssbjsrq=to_date(?,'YYYY-MM-DD') where nsrjsjdm=?");
		bufferNsrjbxxb.append("update SBDB.SB_JL_QYQSSDSBA_NSRJBXXB set qssbksrq=to_date(?,'YYYY-MM-DD'),qssbjsrq=to_date(?,'YYYY-MM-DD') where nsrjsjdm=?");
		bufferCzzb.append("update SBDB.SB_JL_QYQSSDSSBB_CZZB set qssbksrq=to_date(?,'YYYY-MM-DD'),qssbjsrq=to_date(?,'YYYY-MM-DD') where nsrjsjdm=?");
		ps=conn.prepareStatement(bufferZbNd.toString());
		ps.setString(1, form.getQssbksrq());
		ps.setString(2, form.getQssbjsrq());
		ps.setString(3,form.getJsjdm());
		ps.executeUpdate();
		
		ps=conn.prepareStatement(bufferNsrjbxxb.toString());
		ps.setString(1, form.getQssbksrq());
		ps.setString(2, form.getQssbjsrq());
		ps.setString(3,form.getJsjdm());
		ps.executeUpdate();
		
		
		ps=conn.prepareStatement(bufferCzzb.toString());
		ps.setString(1, form.getQssbksrq());
		ps.setString(2, form.getQssbjsrq());
		ps.setString(3,form.getJsjdm());
		ps.executeUpdate();
		
		ps.close();
		
		return form;
	}
		/**
    * 该方法用以检查是否进入清算期，是否是应征户
    * @Description: TODO
    * @param checkBean
    * @return
    */
   public static boolean checkJd(CheckBean checkBean, String subject)
   {   	
	   
	try {
		if(subject.equals("6")){
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject6, checkBean);
		}else{
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject5, checkBean);
		}
		
	} catch (com.ttsoft.framework.exception.BaseException e) {

		return false;
	}			
   	return true;
   }
	
}
