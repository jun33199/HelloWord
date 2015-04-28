<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.web.PKTaskForm"%>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.model.PKTaskModel"%>
<html>
 <head>
  <title>打印页面</title>
  <meta name="Generator" content="EditPlus">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
 </head>

 <body>
 <%
    com.ttsoft.bjtax.smsb.lwpk.web.PKTaskForm pkTask = (com.ttsoft.bjtax.smsb.lwpk.web.PKTaskForm)request.getAttribute("pkTaskForm");
%>
	<h3 align="center">批扣定时计划表</h3>
	<table align="center" width="80%" border="1">
	<tr>
	<th width="30%">任务日期</th>
	<th width="20%">任务时间</th>
	<th width="30%">任务类型</th>
	</tr>
	
	<logic:iterate id="item" name="pktaskForm" property="pkTaskList" type="com.ttsoft.bjtax.smsb.lwpk.model.PKTaskModel" >
	<tr>
		<td align="center"><bean:write name="item" property="zxrq"/></td>
		<td align="center"><bean:write name="item" property="sj"/></td>
		<td align="center"><bean:write name="item" property="rwlxmc"/></td>
	</tr>
	 </logic:iterate>
	</table>
	<table align="center" width="80%">
	<tr>
	<td align="right"><input type="button" value="打印" onclick="javascript:window.print();"/></td>
	<td width="5%"></td>
	<td align="left"><input type="button" value="取消" onclick="to_back()"/></td>
	</tr>
	</table>
	<script type="text/javascript">
	
	function to_back(){
		window.history.go(-1);
	}
	
	
	</script>
	
	
 </body>
</html>
