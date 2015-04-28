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
一个简单的Struts的例子
</font>
<form method="post" name="1">
<br>姓名:  <input name="name" value="<%=inputname%>">
<br>年龄:  <input name="age">
<br><br>
<input type="submit" name="Submit" value="Submit">
<input type="reset" value="Reset">
<br>
<font size="5" color="#372Da2" face="MS Sans Serif">
<jsp:getProperty name="yhsBeanId" property="name" />的年龄的两倍是:<jsp:getProperty name="yhsBeanId" property="old" />
</font>
</form>

</body>
</html>
