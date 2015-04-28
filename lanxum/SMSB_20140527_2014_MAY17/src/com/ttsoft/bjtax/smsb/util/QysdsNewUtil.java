package com.ttsoft.bjtax.smsb.util;

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
import com.syax.creports.bo.qysds.Jbxx;
import com.syax.creports.bo.qysds.QysdsReportsConstants;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.exception.BaseException;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.main.web.QysdsTabelInfo;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;

public class QysdsNewUtil {
	
	public static void main(String[] args){
//		StringBuffer buffer=new StringBuffer();
//		//SELECT
//		buffer.append(" SELECT ");
//		//读取字段
//		buffer.append(" NSRJSJDM,NSRSBH,NSRMC,ND,SSJJLX,");
//		buffer.append(" LXDH,JYDZ,SSHY,ZSFS,CKZD,GZGLXS,");
//		buffer.append(" JMLX,SWJGZZJGDM,");
//		//子查询-根据税务机关组织代码查询税务机关组织机构名称
//		buffer.append(" (SELECT T2.SWJGZZJGMC FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJGZZJGMC,");
//		buffer.append(" (SELECT T2.JSJDM FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJSJDM");
//		//FROM
//		buffer.append(" FROM SBDB.SB_JL_QYSDS_NSRJBXXB T1 WHERE T1.NSRJSJDM=? AND T1.ND=?");
//		
//		System.out.println("企业所得税-基本信息查询SQL");
//		System.out.println(buffer.toString());
		
//		System.out.println(getTimestamp("2006-10-10"));
		
		System.out.println(preQuarter(SfDateUtil.getDate("20060428")));
		
	}
	/**
	 * 设置报表对象基本信息
	 * @param report
	 * @param form
	 */
	public static void setQysdsReport(QysdsReportsDeclare report,QysdsNewForm form){
		/**
		 * 基本信息
		 */
		Jbxx jbxx=new Jbxx();
		
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
		/**
		 * 基本信息(JBXX)-征收方式
		 */
		jbxx.setZsfs(form.getZsfs());
		/**
		 * 基本信息(JBXX)-财会制度
		 */
		jbxx.setCkzd(form.getCkzd());
		/**
		 * 基本信息(JBXX)-工资管理形式
		 */
		jbxx.setGzglxs(form.getGzglxs());
		/**
		 * 基本信息(JBXX)-减免类型
		 */
		jbxx.setJmlx(form.getJmlx());
		
		report.setJbxx(jbxx);
		
		/**
		 * 应用编号
		 */
		report.setAppid(Constants.CREPORTS_APPID_QYSDS);
		/**
		 * 版本号
		 */
		report.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS);
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
		report.setBbqlx(form.getBbqlx());
		/**
		 * 期号
		 */
		report.setQh(form.getQh());
		/**
		 * 税款所属开始日期
		 */
		if(form.getSkssksrq()!=null && !form.getSkssksrq().equals("")){
			report.setSkssksrq(new Date(TinyTools.stringToDate(form.getSkssksrq(),"yyyyMMdd").getTime()) );
		}
		/**
		 * 税款所属结束日期
		 */
		if(form.getSkssjsrq()!=null && !form.getSkssjsrq().equals("")){
			report.setSkssjsrq(new Date(TinyTools.stringToDate(form.getSkssjsrq(),"yyyyMMdd").getTime()) );
		}
		/**
		 * 申报日期
		 */
		if(form.getSbrq()!=null && !form.getSbrq().equals("")){
			report.setSbrq(new Date(TinyTools.stringToDate(form.getSbrq(),"yyyyMMdd").getTime()) );
		}
		/**
		 * 税款年度
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
	public static Object queryQysdsJbxx(Connection conn,QysdsNewForm form) throws Exception{
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		StringBuffer buffer=new StringBuffer();
		//SELECT
		buffer.append(" SELECT ");
		//读取字段
		buffer.append(" NSRJSJDM,NSRSBH,NSRMC,ND,SSJJLX,");
		buffer.append(" LXDH,JYDZ,SSHY,ZSFS,CKZD,GZGLXS,");
		buffer.append(" JMLX,SWJGZZJGDM,SUBSTR(SWJGZZJGDM,1,2) QXDM,");
		buffer.append(" TO_CHAR(SKSSSQQ,'YYYYMMDD') SKSSKSRQ,TO_CHAR(SKSSSQZ,'YYYYMMDD') SKSSJSRQ,");
		//子查询-根据税务机关组织代码查询税务机关组织机构名称
		buffer.append(" (SELECT T2.SWJGZZJGMC FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJGZZJGMC,");
		buffer.append(" (SELECT T2.JSJDM FROM DMDB.GY_DM_SWJGZZJG T2 WHERE T2.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJSJDM");
		//FROM
		buffer.append(" FROM SBDB.SB_JL_QYSDS_NSRJBXXB T1 WHERE T1.NSRJSJDM=? AND T1.ND=?");
		
		System.out.println("企业所得税-基本信息查询SQL");
		System.out.println(buffer.toString());
		
		ps=conn.prepareStatement(buffer.toString());
		System.out.println("1-"+form.getJsjdm());
		System.out.println("2-"+form.getSknd());
		ps.setString(1,form.getJsjdm());
		ps.setString(2,form.getSknd());
		
		rs= ps.executeQuery();
		if(rs.next()){
			//纳税人计算机代码
			form.setJsjdm(rs.getString("NSRJSJDM"));
			//纳税人识别号－税务登记证号
			form.setNsrsbh(rs.getString("NSRSBH"));
			//纳税人名称
			form.setNsrmc(rs.getString("NSRMC"));
			//年度
			form.setSknd(rs.getString("ND"));
			//期号
			form.setQh("1");
			//报表期类型
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			/*税款所属日期不从基本信息表取得*/
//			//税款所属开始日期
//			form.setSkssksrq(rs.getString("SKSSKSRQ"));
//			//税款所属结束日期
//			form.setSkssjsrq(rs.getString("SKSSJSRQ"));
			//所属经济类型
			form.setSsjjlx(rs.getString("SSJJLX"));
			//联系电话
			form.setLxdh(rs.getString("LXDH"));
			//经营地址
			form.setJydz(rs.getString("JYDZ"));
			//所属行业
			form.setSshy(rs.getString("SSHY"));
			//企业所得税征收方式
			form.setZsfs(rs.getString("ZSFS"));
			//财会制度 00:一类01:二类02:三类
			form.setCkzd(rs.getString("CKZD"));
			/**
			 * 工资管理形式
			 * 非工效：计税工资、全额扣除
			 * 工  效：工效挂钩
			 */
			String gzglxs=rs.getString("GZGLXS")==null?"":rs.getString("GZGLXS");
			form.setGzglxs(rs.getString("GZGLXS"));
			//11月30号新需求，非工效挂钩的不再分为计税工资和全额扣除两项 合为一项
			if(CodeConstant.GZGLXS01.equals(gzglxs) || CodeConstant.GZGLXS02.equals(gzglxs)){
//				if(CodeConstant.GZGLXS01.equals(gzglxs)){
				form.setGzlx(CodeConstant.SMSB_GZGLLX_FGX);
			}else if(CodeConstant.GZGLXS03.equals(gzglxs)){
				form.setGzlx(CodeConstant.SMSB_GZGLLX_GX);
			}
			//减免类型
			form.setJmlx(rs.getString("JMLX"));
			//税务机关组织机构代码
			form.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
			//税务机关组织机构名称
			form.setZgswjgzzjgmc(rs.getString("SWJGZZJGMC"));
			//税务计算机代码
			form.setSwjsjdm(rs.getString("SWJSJDM"));
			//区县代码
			form.setQxdm(rs.getString("QXDM"));
			//定制纳税人所需填写的相关纳税申报表
			form.setTableList(getTableList(form.getCkzd()));
			//HTML表链接
			form.setLinkUrlHTML(getLinkUrlHtml(form.getTableList(),form));
			//构造表链接map容器
			form.setLinkMap(generateLinkMap(form.getTableList()));
			
		}else{
			throw new ApplicationException("没有该纳税人的登记信息或者没有权限查看该纳税人信息！");
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
	public static Object queryNsrdjxx(Connection conn,QysdsNewForm form) throws Exception{
		
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
	
	public static Object queryDjxxByInterfaceDJ(Connection conn,QysdsNewForm form,UserData ud) throws Exception{
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
			//form.setQxdm(djsj.getQxdm());
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
	 * 根据财会制度定制纳税人所需填写的相关纳税申报表
	 * @param ckzd 财会制度
	 * @return 
	 */
	private static List getTableList(String ckzd){
		
		List list=new ArrayList();
		
		QysdsTabelInfo zb =new QysdsTabelInfo();
		//主表
		zb.setTableID(CodeConstant.TABLE_ID_ZB);
		zb.setTableName(CodeConstant.TABLE_NAME_ZB);
		zb.setURL("zbAction.do?actionType=Show");
		
		list.add(zb);
		
		//测试使用，财会制度为“TEST”，显示所有表
		if(ckzd!=null && "99".equals(ckzd) ){
			//附表一(1):销售(营业)收入及其他收入明细表
			QysdsTabelInfo table_1_1 =new QysdsTabelInfo();
			table_1_1.setTableID(CodeConstant.TABLE_ID_SRBYBQY);
			table_1_1.setTableName(CodeConstant.TABLE_NAME_SRBYBQY);
			table_1_1.setURL("srbybqyAction.do?actionType=Show");
			list.add(table_1_1);
			
			//附表一(2):金融保险企业主营业务收入明细表 
			QysdsTabelInfo table_1_2 =new QysdsTabelInfo();
			table_1_2.setTableID(CodeConstant.TABLE_ID_SRBJRQY);
			table_1_2.setTableName(CodeConstant.TABLE_NAME_SRBJRQY);
			table_1_2.setURL("srbjrqyAction.do?actionType=Show");
			list.add(table_1_2);
			
			//附表一(3):事业单位、社会团体、民办非企业单位收入明细表
			QysdsTabelInfo table_1_3 =new QysdsTabelInfo();
			table_1_3.setTableID(CodeConstant.TABLE_ID_SRBSYDW);
			table_1_3.setTableName(CodeConstant.TABLE_NAME_SRBSYDW);
			table_1_3.setURL("srbsydwAction.do?actionType=Show");
			list.add(table_1_3);
			
			//附表二(1):成本费用明细表
			QysdsTabelInfo table_2_1 =new QysdsTabelInfo();
			table_2_1.setTableID(CodeConstant.TABLE_ID_CBMXBYBQY);
			table_2_1.setTableName(CodeConstant.TABLE_NAME_CBMXBYBQY);
			table_2_1.setURL("cbmxbybqyAction.do?actionType=Show");
			list.add(table_2_1);
			
			//附表二(2):金融保险企业营业成本明细表
			QysdsTabelInfo table_2_2 =new QysdsTabelInfo();
			table_2_2.setTableID(CodeConstant.TABLE_ID_CBMXBJRQY);
			table_2_2.setTableName(CodeConstant.TABLE_NAME_CBMXBJRQY);
			table_2_2.setURL("cbmxbjrqyAction.do?actionType=Show");
			list.add(table_2_2);
			
			//附表二(3):事业单位、社会团体、民办非企业单位支出项目明细表
			QysdsTabelInfo table_2_3 =new QysdsTabelInfo();
			table_2_3.setTableID(CodeConstant.TABLE_ID_CBMXBSYDW);
			table_2_3.setTableName(CodeConstant.TABLE_NAME_CBMXBSYDW);
			table_2_3.setURL("cbmxbsydwAction.do?actionType=Show");
			list.add(table_2_3);
			
			//附表三:投资所得(损失)明细表
			QysdsTabelInfo table_2 =new QysdsTabelInfo();
			table_2.setTableID(CodeConstant.TABLE_ID_TZMXB);
			table_2.setTableName(CodeConstant.TABLE_NAME_TZMXB);
			table_2.setURL("tzmxbAction.do?actionType=Show");
			list.add(table_2);
			
			//附表四:纳税调整增加项目明细表
			QysdsTabelInfo table_3 =new QysdsTabelInfo();
			table_3.setTableID(CodeConstant.TABLE_ID_NSTZMXZJB);
			table_3.setTableName(CodeConstant.TABLE_NAME_NSTZMXZJB);
			table_3.setURL("nstzmxzjbAction.do?actionType=Show");
			list.add(table_3);
			
			//附表五:纳税调整减少项目明细表
			QysdsTabelInfo table_4 =new QysdsTabelInfo();
			table_4.setTableID(CodeConstant.TABLE_ID_NSTZMXJSB);
			table_4.setTableName(CodeConstant.TABLE_NAME_NSTZMXJSB);
			table_4.setURL("nstzmxjsbAction.do?actionType=Show");
			list.add(table_4);
			
			//附表六:税前弥补亏损明细表
			QysdsTabelInfo table_5 =new QysdsTabelInfo();
			table_5.setTableID(CodeConstant.TABLE_ID_MBKSMXB);
			table_5.setTableName(CodeConstant.TABLE_NAME_MBKSMXB);
			table_5.setURL("mbksmxbAction.do?actionType=Show");
			list.add(table_5);
			
			//附表七:免税所得明细表
			QysdsTabelInfo table_6 =new QysdsTabelInfo();
			table_6.setTableID(CodeConstant.TABLE_ID_MSSDMXB);
			table_6.setTableName(CodeConstant.TABLE_NAME_MSSDMXB);
			table_6.setURL("mssdmxbAction.do?actionType=Show");
			list.add(table_6);
			
			//附表八:捐赠支出明细表
			QysdsTabelInfo table_7 =new QysdsTabelInfo();
			table_7.setTableID(CodeConstant.TABLE_ID_JZZCMXB);
			table_7.setTableName(CodeConstant.TABLE_NAME_JZZCMXB);
			table_7.setURL("jzzcmxbAction.do?actionType=Show");
			list.add(table_7);
			
			//附表九:技术开发费加计扣除额明细表
//			QysdsTabelInfo table_8 =new QysdsTabelInfo();
//			table_8.setTableID(CodeConstant.TABLE_ID_JSKFFMXB);
//			table_8.setTableName(CodeConstant.TABLE_NAME_JSKFFMXB);
//			table_8.setURL("jskffmxbAction.do?actionType=Show");
//			list.add(table_8);
			
			//附表十:境外所得计算明细表
			QysdsTabelInfo table_9 =new QysdsTabelInfo();
			table_9.setTableID(CodeConstant.TABLE_ID_JWSDMXB);
			table_9.setTableName(CodeConstant.TABLE_NAME_JWSDMXB);
			table_9.setURL("jwsdmxbAction.do?actionType=Show");
			list.add(table_9);
			
			//附表十一:广告费支出明细表
			QysdsTabelInfo table_10 =new QysdsTabelInfo();
			table_10.setTableID(CodeConstant.TABLE_ID_GKFZCMXB);
			table_10.setTableName(CodeConstant.TABLE_NAME_GKFZCMXB);
			table_10.setURL("gkfzcmxbAction.do?actionType=Show");
			list.add(table_10);
			
			//附表十二:工资薪金和三项费用明细表
			QysdsTabelInfo table_11 =new QysdsTabelInfo();
			table_11.setTableID(CodeConstant.TABLE_ID_GZXJMXB_FGX);
			table_11.setTableName("附表十二工资薪金和工会经费等三项经费明细表");
			table_11.setURL("gzxjmxbAction.do?actionType=Show");
			list.add(table_11);
			
			//附表十三:资产折旧、摊销明细表
			QysdsTabelInfo table_12 =new QysdsTabelInfo();
			table_12.setTableID(CodeConstant.TABLE_ID_ZCMXB);
			table_12.setTableName(CodeConstant.TABLE_NAME_ZCMXB);
			table_12.setURL("zcmxbAction.do?actionType=Show");
			list.add(table_12);
			
			//附表十四(1):坏帐损失明细表
			QysdsTabelInfo table_13 =new QysdsTabelInfo();
			table_13.setTableID(CodeConstant.TABLE_ID_HZSSMXB);
			table_13.setTableName(CodeConstant.TABLE_NAME_HZSSMXB);
			table_13.setURL("hzssmxbAction.do?actionType=Show");
			list.add(table_13);
			
			//附表十四(2):呆帐准备计提明细表
			QysdsTabelInfo table_14_2 =new QysdsTabelInfo();
			table_14_2.setTableID(CodeConstant.TABLE_ID_DZZBJTMXB);
			table_14_2.setTableName(CodeConstant.TABLE_NAME_DZZBJTMXB);
			table_14_2.setURL("dzzbjtmxbAction.do?actionType=Show");
			list.add(table_14_2);
			
			//附表十四(3):保险准备金提转差纳税调整表
			QysdsTabelInfo table_14 =new QysdsTabelInfo();
			table_14.setTableID(CodeConstant.TABLE_ID_BXZBJ);
			table_14.setTableName(CodeConstant.TABLE_NAME_BXZBJ);
			table_14.setURL("bxzbjAction.do?actionType=Show");
			list.add(table_14);
			
		}
		
		//当纳税人选择企业（小企业）会计制度（或没有选择）时，系统自动定制该纳税人所需填写的相关纳税申报表式为
		if(ckzd==null || 
				(ckzd!=null && "".equals(ckzd) )
				||(ckzd!=null && CodeConstant.CWKJZD01.equals(ckzd) ) ){
			
			//附表一(1):销售(营业)收入及其他收入明细表
			QysdsTabelInfo table_0 =new QysdsTabelInfo();
			table_0.setTableID(CodeConstant.TABLE_ID_SRBYBQY);
			table_0.setTableName(CodeConstant.TABLE_NAME_SRBYBQY);
			table_0.setURL("srbybqyAction.do?actionType=Show");
			list.add(table_0);
			
			//附表二(1):成本费用明细表
			QysdsTabelInfo table_1 =new QysdsTabelInfo();
			table_1.setTableID(CodeConstant.TABLE_ID_CBMXBYBQY);
			table_1.setTableName(CodeConstant.TABLE_NAME_CBMXBYBQY);
			table_1.setURL("cbmxbybqyAction.do?actionType=Show");
			list.add(table_1);
			
			//附表三:投资所得(损失)明细表
			QysdsTabelInfo table_2 =new QysdsTabelInfo();
			table_2.setTableID(CodeConstant.TABLE_ID_TZMXB);
			table_2.setTableName(CodeConstant.TABLE_NAME_TZMXB);
			table_2.setURL("tzmxbAction.do?actionType=Show");
			list.add(table_2);
			
			//附表四:纳税调整增加项目明细表
			QysdsTabelInfo table_3 =new QysdsTabelInfo();
			table_3.setTableID(CodeConstant.TABLE_ID_NSTZMXZJB);
			table_3.setTableName(CodeConstant.TABLE_NAME_NSTZMXZJB);
			table_3.setURL("nstzmxzjbAction.do?actionType=Show");
			list.add(table_3);
			
			//附表五:纳税调整减少项目明细表
			QysdsTabelInfo table_4 =new QysdsTabelInfo();
			table_4.setTableID(CodeConstant.TABLE_ID_NSTZMXJSB);
			table_4.setTableName(CodeConstant.TABLE_NAME_NSTZMXJSB);
			table_4.setURL("nstzmxjsbAction.do?actionType=Show");
			list.add(table_4);
			
			//附表六:税前弥补亏损明细表
			QysdsTabelInfo table_5 =new QysdsTabelInfo();
			table_5.setTableID(CodeConstant.TABLE_ID_MBKSMXB);
			table_5.setTableName(CodeConstant.TABLE_NAME_MBKSMXB);
			table_5.setURL("mbksmxbAction.do?actionType=Show");
			list.add(table_5);
			
			//附表七:免税所得明细表
			QysdsTabelInfo table_6 =new QysdsTabelInfo();
			table_6.setTableID(CodeConstant.TABLE_ID_MSSDMXB);
			table_6.setTableName(CodeConstant.TABLE_NAME_MSSDMXB);
			table_6.setURL("mssdmxbAction.do?actionType=Show");
			list.add(table_6);
			
			//附表八:捐赠支出明细表
			QysdsTabelInfo table_7 =new QysdsTabelInfo();
			table_7.setTableID(CodeConstant.TABLE_ID_JZZCMXB);
			table_7.setTableName(CodeConstant.TABLE_NAME_JZZCMXB);
			table_7.setURL("jzzcmxbAction.do?actionType=Show");
			list.add(table_7);
			
			//附表九:技术开发费加计扣除额明细表
//			QysdsTabelInfo table_8 =new QysdsTabelInfo();
//			table_8.setTableID(CodeConstant.TABLE_ID_JSKFFMXB);
//			table_8.setTableName(CodeConstant.TABLE_NAME_JSKFFMXB);
//			table_8.setURL("jskffmxbAction.do?actionType=Show");
//			list.add(table_8);
			
			//附表十:境外所得计算明细表
			QysdsTabelInfo table_9 =new QysdsTabelInfo();
			table_9.setTableID(CodeConstant.TABLE_ID_JWSDMXB);
			table_9.setTableName(CodeConstant.TABLE_NAME_JWSDMXB);
			table_9.setURL("jwsdmxbAction.do?actionType=Show");
			list.add(table_9);
			
			//附表十一:广告费支出明细表
			QysdsTabelInfo table_10 =new QysdsTabelInfo();
			table_10.setTableID(CodeConstant.TABLE_ID_GKFZCMXB);
			table_10.setTableName(CodeConstant.TABLE_NAME_GKFZCMXB);
			table_10.setURL("gkfzcmxbAction.do?actionType=Show");
			list.add(table_10);
			
			//附表十二:工资薪金和三项费用明细表
			QysdsTabelInfo table_11 =new QysdsTabelInfo();
			table_11.setTableID(CodeConstant.TABLE_ID_GZXJMXB_FGX);
			table_11.setTableName("附表十二工资薪金和工会经费等三项经费明细表");
			table_11.setURL("gzxjmxbAction.do?actionType=Show");
			list.add(table_11);
			
			//附表十三:资产折旧、摊销明细表
			QysdsTabelInfo table_12 =new QysdsTabelInfo();
			table_12.setTableID(CodeConstant.TABLE_ID_ZCMXB);
			table_12.setTableName(CodeConstant.TABLE_NAME_ZCMXB);
			table_12.setURL("zcmxbAction.do?actionType=Show");
			list.add(table_12);
			
			//附表十四(1):坏帐损失明细表
			QysdsTabelInfo table_13 =new QysdsTabelInfo();
			table_13.setTableID(CodeConstant.TABLE_ID_HZSSMXB);
			table_13.setTableName(CodeConstant.TABLE_NAME_HZSSMXB);
			table_13.setURL("hzssmxbAction.do?actionType=Show");
			list.add(table_13);
		}
		
		//当纳税人选择金融企业会计制度时，系统自动定制该纳税人所需填写的相关纳税申报表式为
		if(ckzd!=null && CodeConstant.CWKJZD02.equals(ckzd) ){
			
			//附表一(2):金融保险企业主营业务收入明细表 
			QysdsTabelInfo table_0 =new QysdsTabelInfo();
			table_0.setTableID(CodeConstant.TABLE_ID_SRBJRQY);
			table_0.setTableName(CodeConstant.TABLE_NAME_SRBJRQY);
			table_0.setURL("srbjrqyAction.do?actionType=Show");
			list.add(table_0);
			
			//附表二(2):金融保险企业营业成本明细表
			QysdsTabelInfo table_1 =new QysdsTabelInfo();
			table_1.setTableID(CodeConstant.TABLE_ID_CBMXBJRQY);
			table_1.setTableName(CodeConstant.TABLE_NAME_CBMXBJRQY);
			table_1.setURL("cbmxbjrqyAction.do?actionType=Show");
			list.add(table_1);
			
			//附表三:投资所得(损失)明细表
			QysdsTabelInfo table_2 =new QysdsTabelInfo();
			table_2.setTableID(CodeConstant.TABLE_ID_TZMXB);
			table_2.setTableName(CodeConstant.TABLE_NAME_TZMXB);
			table_2.setURL("tzmxbAction.do?actionType=Show");
			list.add(table_2);
			
			//附表四:纳税调整增加项目明细表
			QysdsTabelInfo table_3 =new QysdsTabelInfo();
			table_3.setTableID(CodeConstant.TABLE_ID_NSTZMXZJB);
			table_3.setTableName(CodeConstant.TABLE_NAME_NSTZMXZJB);
			table_3.setURL("nstzmxzjbAction.do?actionType=Show");
			list.add(table_3);
			
			//附表五:纳税调整减少项目明细表
			QysdsTabelInfo table_4 =new QysdsTabelInfo();
			table_4.setTableID(CodeConstant.TABLE_ID_NSTZMXJSB);
			table_4.setTableName(CodeConstant.TABLE_NAME_NSTZMXJSB);
			table_4.setURL("nstzmxjsbAction.do?actionType=Show");
			list.add(table_4);
			
			//附表六:税前弥补亏损明细表
			QysdsTabelInfo table_5 =new QysdsTabelInfo();
			table_5.setTableID(CodeConstant.TABLE_ID_MBKSMXB);
			table_5.setTableName(CodeConstant.TABLE_NAME_MBKSMXB);
			table_5.setURL("mbksmxbAction.do?actionType=Show");
			list.add(table_5);
			
			//附表七:免税所得明细表
			QysdsTabelInfo table_6 =new QysdsTabelInfo();
			table_6.setTableID(CodeConstant.TABLE_ID_MSSDMXB);
			table_6.setTableName(CodeConstant.TABLE_NAME_MSSDMXB);
			table_6.setURL("mssdmxbAction.do?actionType=Show");
			list.add(table_6);
			
			//附表八:捐赠支出明细表
			QysdsTabelInfo table_7 =new QysdsTabelInfo();
			table_7.setTableID(CodeConstant.TABLE_ID_JZZCMXB);
			table_7.setTableName(CodeConstant.TABLE_NAME_JZZCMXB);
			table_7.setURL("jzzcmxbAction.do?actionType=Show");
			list.add(table_7);
			
			//附表九:技术开发费加计扣除额明细表
//			QysdsTabelInfo table_8 =new QysdsTabelInfo();
//			table_8.setTableID(CodeConstant.TABLE_ID_JSKFFMXB);
//			table_8.setTableName(CodeConstant.TABLE_NAME_JSKFFMXB);
//			table_8.setURL("jskffmxbAction.do?actionType=Show");
//			list.add(table_8);
			
			//附表十:境外所得计算明细表
			QysdsTabelInfo table_9 =new QysdsTabelInfo();
			table_9.setTableID(CodeConstant.TABLE_ID_JWSDMXB);
			table_9.setTableName(CodeConstant.TABLE_NAME_JWSDMXB);
			table_9.setURL("jwsdmxbAction.do?actionType=Show");
			list.add(table_9);
			
			//附表十一:广告费支出明细表
			QysdsTabelInfo table_10 =new QysdsTabelInfo();
			table_10.setTableID(CodeConstant.TABLE_ID_GKFZCMXB);
			table_10.setTableName(CodeConstant.TABLE_NAME_GKFZCMXB);
			table_10.setURL("gkfzcmxbAction.do?actionType=Show");
			list.add(table_10);
			
			//附表十二:工资薪金和三项费用明细表
			QysdsTabelInfo table_11 =new QysdsTabelInfo();
			table_11.setTableID(CodeConstant.TABLE_ID_GZXJMXB_FGX);
			table_11.setTableName("附表十二工资薪金和工会经费等三项经费明细表");
			table_11.setURL("gzxjmxbAction.do?actionType=Show");
			list.add(table_11);
			
			//附表十三:资产折旧、摊销明细表
			QysdsTabelInfo table_12 =new QysdsTabelInfo();
			table_12.setTableID(CodeConstant.TABLE_ID_ZCMXB);
			table_12.setTableName(CodeConstant.TABLE_NAME_ZCMXB);
			table_12.setURL("zcmxbAction.do?actionType=Show");
			list.add(table_12);
			
			//附表十四(2):呆帐准备计提明细表
			QysdsTabelInfo table_13 =new QysdsTabelInfo();
			table_13.setTableID(CodeConstant.TABLE_ID_DZZBJTMXB);
			table_13.setTableName(CodeConstant.TABLE_NAME_DZZBJTMXB);
			table_13.setURL("dzzbjtmxbAction.do?actionType=Show");
			list.add(table_13);
			
			//附表十四(3):保险准备金提转差纳税调整表
			QysdsTabelInfo table_14 =new QysdsTabelInfo();
			table_14.setTableID(CodeConstant.TABLE_ID_BXZBJ);
			table_14.setTableName(CodeConstant.TABLE_NAME_BXZBJ);
			table_14.setURL("bxzbjAction.do?actionType=Show");
			list.add(table_14);
			
		}
		
		//当纳税人选择事业单位、社会团体、民办非企业单位会计制度时，系统自动定制该纳税人所需填写的相关纳税申报表式为
		if(ckzd!=null && CodeConstant.CWKJZD03.equals(ckzd) ){
			
			//附表一(3):事业单位、社会团体、民办非企业单位收入明细表
			QysdsTabelInfo table_0 =new QysdsTabelInfo();
			table_0.setTableID(CodeConstant.TABLE_ID_SRBSYDW);
			table_0.setTableName(CodeConstant.TABLE_NAME_SRBSYDW);
			table_0.setURL("srbsydwAction.do?actionType=Show");
			list.add(table_0);
			
			//附表二(3):事业单位、社会团体、民办非企业单位支出项目明细表
			QysdsTabelInfo table_1 =new QysdsTabelInfo();
			table_1.setTableID(CodeConstant.TABLE_ID_CBMXBSYDW);
			table_1.setTableName(CodeConstant.TABLE_NAME_CBMXBSYDW);
			table_1.setURL("cbmxbsydwAction.do?actionType=Show");
			list.add(table_1);
			
			//附表三:投资所得(损失)明细表
			QysdsTabelInfo table_2 =new QysdsTabelInfo();
			table_2.setTableID(CodeConstant.TABLE_ID_TZMXB);
			table_2.setTableName(CodeConstant.TABLE_NAME_TZMXB);
			table_2.setURL("tzmxbAction.do?actionType=Show");
			list.add(table_2);
			
			//附表四:纳税调整增加项目明细表
			QysdsTabelInfo table_3 =new QysdsTabelInfo();
			table_3.setTableID(CodeConstant.TABLE_ID_NSTZMXZJB);
			table_3.setTableName(CodeConstant.TABLE_NAME_NSTZMXZJB);
			table_3.setURL("nstzmxzjbAction.do?actionType=Show");
			list.add(table_3);
			
			//附表五:纳税调整减少项目明细表
			QysdsTabelInfo table_4 =new QysdsTabelInfo();
			table_4.setTableID(CodeConstant.TABLE_ID_NSTZMXJSB);
			table_4.setTableName(CodeConstant.TABLE_NAME_NSTZMXJSB);
			table_4.setURL("nstzmxjsbAction.do?actionType=Show");
			list.add(table_4);
			
			//附表六:税前弥补亏损明细表
			QysdsTabelInfo table_5 =new QysdsTabelInfo();
			table_5.setTableID(CodeConstant.TABLE_ID_MBKSMXB);
			table_5.setTableName(CodeConstant.TABLE_NAME_MBKSMXB);
			table_5.setURL("mbksmxbAction.do?actionType=Show");
			list.add(table_5);
			
			//附表七:免税所得明细表
			QysdsTabelInfo table_6 =new QysdsTabelInfo();
			table_6.setTableID(CodeConstant.TABLE_ID_MSSDMXB);
			table_6.setTableName(CodeConstant.TABLE_NAME_MSSDMXB);
			table_6.setURL("mssdmxbAction.do?actionType=Show");
			list.add(table_6);
			
			//附表八:捐赠支出明细表
			QysdsTabelInfo table_7 =new QysdsTabelInfo();
			table_7.setTableID(CodeConstant.TABLE_ID_JZZCMXB);
			table_7.setTableName(CodeConstant.TABLE_NAME_JZZCMXB);
			table_7.setURL("jzzcmxbAction.do?actionType=Show");
			list.add(table_7);
			
			//附表九:技术开发费加计扣除额明细表
//			QysdsTabelInfo table_8 =new QysdsTabelInfo();
//			table_8.setTableID(CodeConstant.TABLE_ID_JSKFFMXB);
//			table_8.setTableName(CodeConstant.TABLE_NAME_JSKFFMXB);
//			table_8.setURL("jskffmxbAction.do?actionType=Show");
//			list.add(table_8);
			
			//附表十:境外所得计算明细表
			QysdsTabelInfo table_9 =new QysdsTabelInfo();
			table_9.setTableID(CodeConstant.TABLE_ID_JWSDMXB);
			table_9.setTableName(CodeConstant.TABLE_NAME_JWSDMXB);
			table_9.setURL("jwsdmxbAction.do?actionType=Show");
			list.add(table_9);
			
			//附表十一:广告费支出明细表
			QysdsTabelInfo table_10 =new QysdsTabelInfo();
			table_10.setTableID(CodeConstant.TABLE_ID_GKFZCMXB);
			table_10.setTableName(CodeConstant.TABLE_NAME_GKFZCMXB);
			table_10.setURL("gkfzcmxbAction.do?actionType=Show");
			list.add(table_10);
			
			//附表十二:工资薪金和三项费用明细表
			QysdsTabelInfo table_11 =new QysdsTabelInfo();
			table_11.setTableID(CodeConstant.TABLE_ID_GZXJMXB_FGX);
			table_11.setTableName("附表十二工资薪金和工会经费等三项经费明细表");
			table_11.setURL("gzxjmxbAction.do?actionType=Show");
			list.add(table_11);
			
			//附表十三:资产折旧、摊销明细表
			QysdsTabelInfo table_12 =new QysdsTabelInfo();
			table_12.setTableID(CodeConstant.TABLE_ID_ZCMXB);
			table_12.setTableName(CodeConstant.TABLE_NAME_ZCMXB);
			table_12.setURL("zcmxbAction.do?actionType=Show");
			list.add(table_12);
			
			//附表十四(1):坏帐损失明细表
			QysdsTabelInfo table_13 =new QysdsTabelInfo();
			table_13.setTableID(CodeConstant.TABLE_ID_HZSSMXB);
			table_13.setTableName(CodeConstant.TABLE_NAME_HZSSMXB);
			table_13.setURL("hzssmxbAction.do?actionType=Show");
			list.add(table_13);
		}
		
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
			QysdsTabelInfo table=(QysdsTabelInfo)list.get(i-1);
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
	public static String getLinkUrlHtml(List list,QysdsNewForm form) throws Exception {
		StringBuffer buffer=new StringBuffer();
		QysdsReportsDeclare report=new QysdsReportsDeclare();
		setQysdsReport(report,form);
		Connection conn = null; 
		try {
			conn = SfDBResource.getConnection();
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			
			//分3列展现表链接,每列最多展示row个链接 
			int row=6;
			StringBuffer buffer1=new StringBuffer();
			StringBuffer buffer2=new StringBuffer();
			StringBuffer buffer3=new StringBuffer();
			
			buffer1.append("<TD>");
			buffer2.append("<TD>");
			buffer3.append("<TD>");
			
			for(int i=1;i<=list.size();i++){
				QysdsTabelInfo table=(QysdsTabelInfo)list.get(i-1);
				QysdsReportsTableDeclare iTable=new QysdsReportsTableDeclare();
				iTable.setTableId(table.getTableID());
				report.getTableContentList().clear();
				report.getTableContentList().put(table.getTableID(),iTable);
				String shbz=iApp.queryCheckStatus(report);
				if(i<=row){
					buffer1.append("<DIV>");
					if(shbz.equals(Constants.QYSDS_SHZT_SINGLE_PASS) || shbz.equals(Constants.QYSDS_SHZT_ALL_PASS)){
						buffer1.append("<INPUT TYPE='CHECKBOX' CHECKED DISABLED/>");
					}else{
						buffer1.append("<INPUT TYPE='CHECKBOX' DISABLED/>");
					}
					buffer1.append("<A ");
					buffer1.append("id='TableID_"+table.getTableID()+"' ");
					buffer1.append(" onClick='link2Table("+table.getTableID()+")'");
					buffer1.append(" HREF='"+table.getURL()+"'>");
					buffer1.append(table.getTableName());
					buffer1.append("</A>");
					buffer1.append("</DIV>");
				}
				if(i<=2*row && i>row){
					buffer2.append("<DIV>");
					if(shbz.equals(Constants.QYSDS_SHZT_SINGLE_PASS) || shbz.equals(Constants.QYSDS_SHZT_ALL_PASS)){
						buffer2.append("<INPUT TYPE='CHECKBOX' CHECKED DISABLED/>");
					}else{
						buffer2.append("<INPUT TYPE='CHECKBOX' DISABLED/>");
					}
					buffer2.append("<A ");
					buffer2.append("id='TableID_"+table.getTableID()+"' ");
					buffer2.append(" onClick='link2Table("+table.getTableID()+")'");
					buffer2.append(" HREF='"+table.getURL()+"'>");
					buffer2.append(table.getTableName());
					buffer2.append("</A>");
					buffer2.append("</DIV>");
				}
				if( i>2*row){
					buffer3.append("<DIV>");
					if(shbz.equals(Constants.QYSDS_SHZT_SINGLE_PASS) || shbz.equals(Constants.QYSDS_SHZT_ALL_PASS)){
						buffer3.append("<INPUT TYPE='CHECKBOX' CHECKED DISABLED/>");
					}else{
						buffer3.append("<INPUT TYPE='CHECKBOX' DISABLED/>");
					}
					buffer3.append("<A ");
					buffer3.append("id='TableID_"+table.getTableID()+"' ");
					buffer3.append(" onClick='link2Table("+table.getTableID()+")'");
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
	public static QysdsReportsTableDeclare cleanSpace(QysdsReportsTableDeclare table){
		Iterator it = table.getCellContentList().keySet().iterator();
		Map map=new HashMap();
		while(it.hasNext()){
			String key = (String)it.next();
			System.out.println("key--"+key);
			QysdsReportsItemDeclare item =(QysdsReportsItemDeclare)table.getCellContentList().get(key);
			if(!"".equals(item.getItemValue().trim())){
				map.put(key, item);
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
	public static QysdsReportsTableDeclare putSpace(QysdsReportsTableDeclare table,int arrs[]) {
		System.out.println("**显示qysdsNewUtil中的putSpace()**");
		for(int j=1;j<=arrs.length;j=j+2){			
			System.out.println("j___  "+j+"***"+arrs.length);
			for(int i=arrs[j-1];i<=arrs[j];i++){
				QysdsReportsItemDeclare item =(QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(i));
				if(item==null ){
					QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
					item1.setItemID(String.valueOf(i));
					item1.setItemValue("");
					item1.setItemType("11");
					table.getCellContentList().put(String.valueOf(i), item1);
				}else if(item!=null && item.getItemValue()!=null && "".equals(item.getItemValue().trim())){
					QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
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
		
		buffer.append("<html><link href='../../css/text.css' rel='stylesheet' type='text/css'><title>"+"校验结果</title>");
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
	public static String getTableURL(QysdsNewForm form,String key){
		return (String)form.getLinkMap().get(key);
	}
	
	/**
	 * 判断当前表是否为最后一张表
	 * @param form
	 * @param key
	 * @return
	 */
	public static String isLastTable(QysdsNewForm form,String key){
		QysdsTabelInfo table=(QysdsTabelInfo)form.getTableList().get(form.getTableList().size()-1);
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
//	public static String getZsfsdm(QysdsNewForm form) throws BaseException {
//	String re = "";
//	
//	// 当前时间
////	Timestamp sbrq = new Timestamp(System.currentTimeMillis());
//	//从申报页面取得申报日期和税款所属日期
//	Timestamp sbrq = QysdsNewUtil.getNxetTimestamp(form.getSkssjsrq());
//	
////	Map getsbjd = this.quarterSkssrq(sbrq);
//	Timestamp skssksrq = QysdsNewUtil.getTimestamp(form.getSkssksrq());
//	Timestamp skssjsrq = QysdsNewUtil.getTimestamp(form.getSkssjsrq());
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
//	QysdsSet qysdsSet = null;
//	
//	
//	
//	try {
//	if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_YEAR)) {
//	qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
//	CodeConstant.SFGL_QYSDS_BBFS_NB);
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
//	qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
//	CodeConstant.SFGL_QYSDS_BBFS_NB);
//	}else{
//	qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
//	CodeConstant.SFGL_QYSDS_BBFS_JB);
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
//	Zsfs zsfs = qysdsSet.getZsfs();
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
			QysdsTabelInfo table=(QysdsTabelInfo)list.get(i);
			if( i!=list.size()-1 ){
				//当前表的下一张表
				QysdsTabelInfo next_table=(QysdsTabelInfo)list.get(i+1);
				//为当前表设置下一张表链接
				linkMap.put("N_"+table.getTableID(),next_table.getURL());
				//为下一张表设置上一张表链接
				linkMap.put("P_"+next_table.getTableID(),table.getURL());
			}else{
				//最后一张表
				QysdsTabelInfo pre_table=(QysdsTabelInfo)list.get(i-1);
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
		//System.out.println(month);
		int jd = month / 3;
		jd++;
		return new Integer(jd).toString();
	}
	
	
	
	
}
