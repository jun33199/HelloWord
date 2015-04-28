<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<html:html>
<head>  
<title>告知事项内容</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
</head>
<body  bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
<table width="97%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>
    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="告知事项内容" />
		<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>
    	<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td valign="top">
	     <br>
	      <html:errors/>
	     <table  align="center" cellspacing="0" class="table-99">
		<!-- write your code here--->
	        <tr>
	          <td class="1-td1">告知事项内容详细</td>
	        </tr>            
	        <tr>
	          <td class="1-td2"><br>
<html:form action="gzsxfk.do" method="POST">
<TABLE border=0 cellPadding=0 cellSpacing=0 width="98%">
	<TBODY>
        <TR>
        	<TD  width="100%"><br>
        	<div align="center">
               <%=request.getSession().getAttribute((String)request.getAttribute("gzsx_id"))%>
              <div/>
        	</TD>
            
	</TR>
	</TBODY>
</TABLE><BR>
</html:form>
	          </td>
	         </tr>
	     </table>
	    </td>
	  </tr>
    	</table>
<br>
    </td>
  </tr>
<tr>
  <td valign="bottom" align="center">
	<%@ include file="../include/bottom.jsp" %>
  </td>
</tr>
 </table>
</body>
</html:html>
