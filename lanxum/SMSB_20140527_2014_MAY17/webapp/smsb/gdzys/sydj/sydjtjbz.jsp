<%@ page contentType="text/html; charset=gb2312" language="java"  errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.common.model.UserData"%>
<%@ page import="com.ttsoft.framework.util.DateTimeUtil"%>
<%@ page import="com.ttsoft.bjtax.dj.model.YHZH"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ttsoft.bjtax.dj.util.CodeTableUtil"%>
<%@ page import="com.ttsoft.bjtax.dj.util.CodeTableKey"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.web.GdzysSydjxxlrForm" %>

<head>  
	<link href="../css/text.css" rel="stylesheet" type="text/css">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
</head>
<html:html>
<STYLE>.inputalignright {
	FONT-SIZE: 9pt; TEXT-ALIGN: right
}
.inbright {
	FONT-SIZE: 9pt; BORDER-TOP: 0px; BORDER-RIGHT: 0px; BACKGROUND: #f3f5f8; BORDER-BOTTOM: 0px; TEXT-ALIGN: left; BORDER-LEFT: 0px
}
</STYLE>

<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>
 <%
     String currentDate = DateTimeUtil.getCurrentDate();
%>
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language=JavaScript src="../js/MathString.js"></script>
<script language=JavaScript src="../js/Stack.js"></script>
<script language=JavaScript src="../js/Bolan.js"></script>
<script language=JavaScript src="../js/reader.js"></script>
<script language=JavaScript src="../js/InputSelect.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/DTable.js"></script>
<SCRIPT language=JavaScript>




//获取减免税依据
 /* function getBZLX(){
	   var xmlhttp_request;
	   var bzdm = document.getElementsByName("bzdm");
	  
	   if(window.ActiveXObject){
	  	       xmlhttp_request=new ActiveXObject("Microsoft.XMLHTTP");
	  	    }else if(window.XMLHttpRequest){
	  	       xmlhttp_request=new XMLHttpRequest();
	  	     } else {
	  	       return;
	  	   }
	  	     xmlhttp_request.open("GET", "sydjxxlrAction.do?actionType=getBZLX&bzdm="+bzdm, true);
	       	 xmlhttp_request.send(null);
	  	     xmlhttp_request.onreadystatechange = function jmsyjprocessAjaxResponse(){
	          if (xmlhttp_request.readyState == 4) {
	          if (xmlhttp_request.status == 200) {
	        	var xmlDOM = xmlhttp_request.responseXML;
	        	var obj = document.getElementById("bznr");
	        		obj.value = xmlDOM.getElementsByTagName("bznr")[0].firstChild.data;
	        
	     		}
	       }
	  	 };
}
 */
 function getBZLX(){
	 var dm = document.getElementById("bzlxdm");
	 var nr = document.getElementById("bzms");
	 if(dm.value == "0"){
		 nr.innerHTML="时间：";
	 }else if(dm.value == "1"){
		 nr.innerHTML="文号（可多个）：";
	 }else if(dm.value == "2"){
		 nr.innerHTML="时间：税款：元 滞纳金：元";
	 }else if(dm.value == "3"){
		 nr.innerHTML="";
	 }
 }
function clearnr(){
	 var nr = document.getElementById("bzms");
	 nr.innerHTML="";
}
//打印申报表 
function savebz(){
	document.forms[0].actionType.value = "Remark";
	document.forms[0].submit();  
}

</SCRIPT>

<html:form action="/webapp/smsb/sydjxxlrAction.do" method="post">
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
 <%@ include file="../../include/header.jsp"%>
<html:hidden property="actionType"/>
<input type="hidden" name="sbbxlh" value='<bean:write name="sydjxxlrForm" property="sbbxlh"/>' />
<TABLE class=black9 height=300 cellSpacing=0 cellPadding=0 width="90%" 
align=center border=0>
  <TBODY>
  <TR>
    <TD height=300 vAlign=top><BR>
      <TABLE class=table-99 cellSpacing=0 align=center>
        <TBODY>
        <TR>
          <TD class=1-td1 colSpan=2>备注信息录入</TD></TR>
        <TR>
          <TD class=1-td2 colSpan=2><BR>
            <TABLE class=table-99 cellSpacing=0 cellPadding=0 width="80%" border=0 align = center>
              <TR class=black9>
                <TD class=2-td2-t-left width="40%" noWrap>备注类型</TD>
                <TD class=2-td2-t-center noWrap>
                <SELECT onchange="getBZLX()" id=bzlxdm style="WIDTH: 145px" name=bzlxdm>
                   <OPTION selected value=0>全额退税</OPTION> 
                   <OPTION  value=1>实际占地征税与征地批复一致</OPTION>
                    <OPTION value=2>补缴欠税</OPTION>
                    <OPTION value=3>其他</OPTION>
                    
                    </SELECT>  
              </TD></TR>
        	<tr  class=black9>
        	 <TD class=2-td2-left noWrap>备注内容</TD>
                <TD class=2-td2-center width="40%" noWrap>
                <textarea rows="5" style="width:100%" name=bzms id=bzms>时间：</textarea>
                </TD>
        	
        	</tr>
              </TABLE><BR>
            
            <TABLE width="100%" border=0>
              <TBODY>
              <TR>
              	<td width="42%">&nbsp;</td>
                <TD width="8%"><INPUT onclick="savebz();return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=bc1 style="CURSOR: hand" 
                  accessKey=s height=22 alt=保存 src="<%=static_contextpath%>images/bc-s1.jpg" 
                  type=image width=79 border=0></TD>
                <TD width="8%"><INPUT onclick="tuichu();return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=tc1 style="CURSOR: hand" 
                  accessKey=c height=22 alt=退出 src="<%=static_contextpath%>images/tc-c1.jpg" 
                  type=image width=79 border=0></TD>
                <TD 
            width="42%">&nbsp;</TD></TR></TBODY></TABLE><BR></DIV></DIV></DIV></DIV></TD></TR></TBODY></TABLE><BR></FORM>
      <TABLE height=33 cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD height=9 background=<%=static_contextpath%>images/q-bottom-bg-01.jpg colSpan=2 
          noWrap></TD></TR>
        <TR>
          <TD noWrap><FONT size=2>&nbsp;&nbsp;&nbsp;* 必须使用 IE 5.5 以上，分辨率 
            800*600 浏览本网站</FONT></TD>
          <TD height=24 align=right><IMG alt=承办单位：清华同方软件股份有限公司2008_APR11_01 
            src="<%=static_contextpath%>images/q-bottom-tu-01.jpg" width=184 
        height=24></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
</body>
</html:form>
</html:html>