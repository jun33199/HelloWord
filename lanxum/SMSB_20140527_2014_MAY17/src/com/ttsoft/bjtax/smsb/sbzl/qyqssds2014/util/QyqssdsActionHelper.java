package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.DmVo;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

public class QyqssdsActionHelper {
	public static final String V_QUERY_HTML_BODY="V_QUERY_HTML_BODY";
	public static final String PAGE_QYQSSDS_SHOW="/webapp/smsb/qyqssds/2014/qyqssdsBaAction2014.do";
	public static final String PAGE_QYQSSDS_QUERY = "/webapp/smsb/qyqssds/2014/qyqssdsBaglAction2014.do?actionType=Query&returnFlag=operateReturn"; 
	
	public static final String PAGE_QYQSSDSSB_SHOW="/webapp/smsb/qyqssds/2014/qyqssdsMainAction2014.do";
	public static final String PAGE_QYQSSDSSB_QUERY = "/webapp/smsb/qyqssds/2014/qyqssdsSbglAction2014.do?actionType=Query&returnFlag=operateReturn"; 
	
	
	public static final String QUERY_HTML_HEAD=
		"<table  width=\"940\" id=\"table1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">   "+		
		"	<tr> "+
		"		<td width=\"80\" class=\"2-td1-left\"  align=\"center\">计算机代码</td> "+
		"		<td class=\"2-td1-left\"  align=\"center\">纳税人名称</td> "+
		"		<td class=\"2-td1-left\"  align=\"center\">所属主管税务机关</td> "+
		"		<td width=\"60\"  class=\"2-td1-left\"  align=\"center\">备案年度</td> "+
		"		<td width=\"70\"  class=\"2-td1-left\"  align=\"center\">申请类型</td> "+
		"		<td width=\"95\" class=\"2-td1-left\"  align=\"center\">备案申请状态</td> "+
		"		<td width=\"80\" class=\"2-td1-center\"  align=\"center\">操作</td> "+
		"	</tr> "+V_QUERY_HTML_BODY+
		"</table> ";
	public static final String QYQSSDSSB_QUERY_HTML_HEAD=
			"<table  width=\"940\" id=\"table1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">   "+		
			"	<tr> "+
			"		<td width=\"80\" class=\"2-td1-left\"  align=\"center\">计算机代码</td> "+
			"		<td class=\"2-td1-left\"  align=\"center\">纳税人名称</td> "+
			"		<td class=\"2-td1-left\"  align=\"center\">所属主管税务机关</td> "+
			"		<td width=\"60\"  class=\"2-td1-left\"  align=\"center\">备案年度</td> "+
			"		<td width=\"70\"  class=\"2-td1-left\"  align=\"center\">申请类型</td> "+
			"		<td width=\"95\" class=\"2-td1-left\"  align=\"center\">申报申请状态</td> "+
			"		<td width=\"80\" class=\"2-td1-center\"  align=\"center\">操作</td> "+
			"	</tr> "+V_QUERY_HTML_BODY+
			"</table> ";
	public static final String QYQSSDSHIS_QUERY_HTML_HEAD=
			"<table  width=\"940\" id=\"table1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">   "+		
			"	<tr> "+
			"		<td width=\"80\" class=\"2-td1-left\"  align=\"center\">计算机代码</td> "+
			"		<td class=\"2-td1-left\"  align=\"center\">纳税人名称</td> "+
			"		<td class=\"2-td1-left\"  align=\"center\">所属主管税务机关</td> "+
			"		<td width=\"60\"  class=\"2-td1-left\"  align=\"center\">备案年度</td> "+
			"		<td width=\"70\"  class=\"2-td1-left\"  align=\"center\">申请类型</td> "+
			"		<td width=\"95\" class=\"2-td1-left\"  align=\"center\">操作类型</td> "+
			"		<td width=\"80\" class=\"2-td1-center\"  align=\"center\">作废/删除人</td> "+
			"	</tr> "+V_QUERY_HTML_BODY+
			"</table> ";
	//added by zhangj
	public static final String PAGE_QYQSSDS_WXBA_QUERY = "/webapp/smsb/qyqssds/2014/qyqssdsWxBaglAction2014.do?actionType=Query&returnFlag=operateReturn"; 
	public static final String PAGE_QYQSSDS_WXBA_SHOW="/webapp/smsb/qyqssds/2014/qyqssdsWxBaAction2014.do";
	public static final String QYQSSDSWXBA_QUERY_HTML_HEAD=
		"<table  width=\"940\" id=\"table1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">   "+		
		"	<tr> "+
		"		<td width=\"80\" class=\"2-td1-left\"  align=\"center\">计算机代码</td> "+
		"		<td class=\"2-td1-left\"  align=\"center\">纳税人名称</td> "+
		"		<td class=\"2-td1-left\"  align=\"center\">所属主管税务机关</td> "+
		"		<td class=\"2-td1-left\"  align=\"center\">是否不需进行清算备案</td> "+
		"		<td width=\"80\" class=\"2-td1-center\"  align=\"center\">操作</td> "+
		"	</tr> "+V_QUERY_HTML_BODY+
		"</table> ";
	 /**
     * 初始化页面下拉信息
     * @param request HttpServletRequest
     * @throws ApplicationException
     */
    public static void initialPageSelectItem(HttpServletRequest request,List list1) throws ApplicationException
    {
        // 获取session
        HttpSession session = request.getSession(false);
        //获取主管税务机关
        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSZGSWJG) == null) {
            String[][] jmszgswjgArray = new String[list1.size()][2];
            for (int i = 0; i < list1.size(); i++) {
            	DmVo vo=(DmVo)list1.get(i);
            	jmszgswjgArray[i][0] = vo.getDm();
            	jmszgswjgArray[i][1] = vo.getMc();
            }
            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSZGSWJG, jmszgswjgArray);
        } 
        
        // 获取申请类型
        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBASQLX) == null) {
            String[][] jmssqlxArray = new String[2][2];
            
            jmssqlxArray[0][0]="0";
            jmssqlxArray[0][1]="网上申请";
            
            jmssqlxArray[1][0]="1";
            jmssqlxArray[1][1]="上门申请";
            
            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBASQLX, jmssqlxArray);
        }           
        
        // 获取申请状态
        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBASQZT) == null) {
            String[][] jmssqztArray = new String[4][2];
          
            jmssqztArray[0][0]="1";
            jmssqztArray[0][1]="提交未审核";
            
            jmssqztArray[1][0]="2";
            jmssqztArray[1][1]="审核已通过";
            
            jmssqztArray[2][0]="2";
            jmssqztArray[2][1]="审核被驳回";
            
            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JMSBASQZT, jmssqztArray);
        }           
    }
    
    
    public static String boToHtml(List list){
    	if(list==null){
    		return QUERY_HTML_HEAD.replaceAll(V_QUERY_HTML_BODY, "");	
    	}else{
    		return QUERY_HTML_HEAD.replaceAll(V_QUERY_HTML_BODY, getBodyByList(list));	
    	}
    		
    }
    public static String hisboToHtml(List list){
    	if(list==null){
    		return QYQSSDSHIS_QUERY_HTML_HEAD.replaceAll(V_QUERY_HTML_BODY, "");	
    	}else{
    		return QYQSSDSHIS_QUERY_HTML_HEAD.replaceAll(V_QUERY_HTML_BODY, getBodyByList(list));	
    	}
    		
    }
    
    public static String sbboToHtml(List list){
    	if(list==null){
    		return QYQSSDSSB_QUERY_HTML_HEAD.replaceAll(V_QUERY_HTML_BODY, "");	
    	}else{
    		return QYQSSDSSB_QUERY_HTML_HEAD.replaceAll(V_QUERY_HTML_BODY, getBodyByList(list));	
    	}
    		
    }
    
    
    public static String getBodyByList(List list)
    {
        String returnHtml = "";
        if (list == null || list.size() == 0) {
            returnHtml = "";
        }
        else
        {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                sb.append("<tr>");
                Map map = (Map) list.get(i);
                for (int j = 1; j <= 7; j++) {
                    String valueKey = "COL_" + String.valueOf(j);
                    if(j==7)sb.append("<td class=\"2-td2-center\">");
                    
                    else sb.append("<td class=\"2-td2-left\">");
                    if(j>=2 && j<=5)sb.append("<div align=\"left\">");
                    sb.append(nullConvertToNbsp((String) map.get(valueKey)));
                    if(j>=2 && j<=5)sb.append("</div>");
                    sb.append("</td>");
                   
                }
                sb.append("</tr>");
            }
            returnHtml = sb.toString();
          
        }
    	return returnHtml;

    }
    public static String nullConvertToNbsp(String value){
    	return (value == null || value.trim().length() == 0)?"&nbsp;":value;
    }
    
    
    public static String getForwardPath(String jsjdm,String czlx){
    	String str = "?actionType=Query&jsjdm="+jsjdm+"&czlx="+czlx;
//    	if(Integer.parseInt(czlx)==CodeConstant.QYSDSJMSBAJL_CZLX_ZFBG){
//    		str="?actionType=Zfbg&basqwsh="+wsh+"&czlx="+czlx+"&basqbh="+basqbh;
//    	}
//    	Map map=new HashMap();
    	
    	return PAGE_QYQSSDS_SHOW+str;
    
    }
    
    /**
     * 申报管理跳转
     * @param jsjdm
     * @param czlx
     * @return
     */
    public static String getSbForwardPath(String jsjdm,String czlx){
    	String str = "?actionType=Query&jsjdm="+jsjdm+"&czlx="+czlx;
    	return PAGE_QYQSSDSSB_SHOW+str;
    }
    
    
	 /**
     * 获取备案审核状态和申报审核状态
     * @author zhangj
     * @param jsjdm
     * @throws BaseException
     */
    
    public static Map getShztbs(String jsjdm) throws BaseException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		HashMap map=new HashMap();
		try {
			conn = SfDBResource.getConnection();
			String showSql="select NSRJSJDM,BASHZTBS,SBSHZTBS,BASHTGRQ from sbdb.sb_jl_qyqssdsba_nsrjbxxb  where nsrjsjdm=?";
			ps = conn.prepareStatement(showSql);
			ps.setString(1, jsjdm);
			rs = ps.executeQuery();
			if(rs.next()){

				map.put(CodeConstant.SMSB_QYQSSDS2014_BASHZTBS,rs.getString("BASHZTBS")==null ?"":rs.getString("BASHZTBS"));
				map.put(CodeConstant.SMSB_QYQSSDS2014_SBSHZTBS,rs.getString("SBSHZTBS")==null ?"":rs.getString("SBSHZTBS"));
				map.put(CodeConstant.SMSB_QYQSSDS2014_BASHTGRQ,rs.getString("BASHTGRQ")==null ?"":rs.getString("BASHTGRQ"));
				//map.put(CodeConstant.SMSB_QYQSSDS2014_SBSHZTBS,"");
			}
		} catch (Exception ex) {
			// 抛出异常
			System.out.println("catch exception  .............."+ex);
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			SfDBResource.freeConnection(conn);
		}
		return map;
    }   
	 /**
     * 获取跳转的url
     * @author zhangj
     * @param String basePath,String jsjdm,String czlx
     * @return path
     */
    public static String getForward(String basePath,String jsjdm){
    	String str = "?actionType=Query&jsjdm="+jsjdm;  	
    	return basePath+str;
    
    }
    public static String getHtmlBodyByList(List list)
    {
        String returnHtml = "";
        if (list == null || list.size() == 0) {
            returnHtml = "";
        }
        else
        {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                sb.append("<tr>");
                Map map = (Map) list.get(i);
                for (int j = 1; j <= 5; j++) {
                    String valueKey = "COL_" + String.valueOf(j);
                    if(j==5)sb.append("<td class=\"2-td2-center\">");
                    
                    else sb.append("<td class=\"2-td2-left\">");
                    if(j>=2 && j<=5)sb.append("<div align=\"left\">");
                    sb.append(nullConvertToNbsp((String) map.get(valueKey)));
                    if(j>=2 && j<=5)sb.append("</div>");
                    sb.append("</td>");
                   
                }
                sb.append("</tr>");
            }
            returnHtml = sb.toString();
          
        }
    	return returnHtml;

    }
    public static String creatHtml(String head,List list){
    	if(list==null){
    		return head.replaceAll(V_QUERY_HTML_BODY, "");	
    	}else{
    		return head.replaceAll(V_QUERY_HTML_BODY, getHtmlBodyByList(list));	
    	}
    		
    }
    
	 /**
    * 获取是否无需备案状态
    * @author zhangj
    * @param jsjdm
    * @throws BaseException
    */
   
   public static Map getSfwxjsba(String jsjdm) throws BaseException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		HashMap map=new HashMap();
		String sfwxjxba="";
		try {
			conn = SfDBResource.getConnection();
			String showSql="select JSJDM,SFWXJXBA from SBDB.SB_JL_QYQSSDS_WXBADJB  where jsjdm=?";
			ps = conn.prepareStatement(showSql);
			ps.setString(1, jsjdm);
			rs = ps.executeQuery();
			if(rs.next()){
				sfwxjxba=rs.getString("SFWXJXBA")==null ?"":rs.getString("SFWXJXBA");
				//map.put(CodeConstant.SMSB_QYQSSDS2014_SBSHZTBS,"");
			}
			map.put(CodeConstant.SMSB_QYQSSDS2014_SFWXJXBA,sfwxjxba);
		} catch (Exception ex) {
			// 抛出异常
			System.out.println("catch exception  .............."+ex);
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			SfDBResource.freeConnection(conn);
		}
		return map;
   }   
}
