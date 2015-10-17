<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>   <!-- 开放el表达式 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>景区网站登录</title>
</head>

<body>
	<c:if test="${!empty error}">
		<font color="red"><c:out value="${error}"/></font>
	</c:if>
	<form action="<c:url value="/admin/loginCheck.html" />" method="post">
		用户名：
		<input type="text" name="userName">
		<br>
		密码：
		<input type="password" name="password">
		<br>
		<input type="submit" value="登录"/>
		<input type="reset" value="重置"/>
	</form>
</body>
</html>
