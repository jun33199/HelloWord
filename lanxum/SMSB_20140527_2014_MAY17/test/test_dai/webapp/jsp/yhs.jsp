<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<title>
yhs
</title>
<%
	String inputname = request.getParameter("name");
	if(inputname==null)
	{
		inputname="";
	}
%>
</head>
<jsp:useBean id="yhsBeanId" scope="session" class="yhs.YhsBean" />
<jsp:setProperty name="yhsBeanId" property="*" />
<body bgcolor="#ffffff">
<font size="6" color="#372Da2" face="MS Sans Serif">
һ���򵥵�Struts������
</font>
<form method="post" name="1">
<br>����:  <input name="name" value="<%=inputname%>">
<br>����:  <input name="age">
<br><br>
<input type="submit" name="Submit" value="Submit">
<input type="reset" value="Reset">
<br>
<font size="5" color="#372Da2" face="MS Sans Serif">
<jsp:getProperty name="yhsBeanId" property="name" />�������������:<jsp:getProperty name="yhsBeanId" property="old" />
</font>
</form>

</body>
</html>
