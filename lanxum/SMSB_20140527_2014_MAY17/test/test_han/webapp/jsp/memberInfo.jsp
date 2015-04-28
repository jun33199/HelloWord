<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
<head>
	<title>会员信息</title>
	<html:base/>
</head>

<body bgcolor="white">

<html:errors/>

<html:form action="/savemember.do" method="POST" focus="name">
<table border="0" width="100%">
<tr>
	<th align="right">会员名称</th>
	<td align="left">
  		<html:text property="name" size="16" maxlength="16"/>
	</td>
</tr>
<tr>
	<th align="right">会员年龄</th>
	<td align="left">
  		<html:text property="age" size="16" maxlength="16"/>
	</td>
</tr>

<tr>
	<td align="right">
  		<html:submit property="submit" value="Submit"/>
	</td>
	<td align="left">
  		<html:reset/>
	</td>
</tr>
</table>
</html:form>

</body>
</html:html>
