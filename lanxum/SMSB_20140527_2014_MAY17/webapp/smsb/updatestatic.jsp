<%@ page contentType="text/html; charset=gb2312" language="java" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.table.TableManager" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.code.CodeManager" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.relation.RelationManager" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.reader.ReaderManager" %>
<!doctype html public "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title> 更新静态变量 </title>
<meta name="Generator" content="EditPlus">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
</head>
<%
	String tableid = request.getParameter("tableid");
	String codekey = request.getParameter("codekey");
	String relation = request.getParameter("relation");
	String reader = request.getParameter("reader");
	if(tableid!=null&&!tableid.trim().equals("")){
		TableManager.updateTableMap(tableid);
	}
	if(codekey!=null&&!codekey.trim().equals("")){
		CodeManager.updateCodeMap(codekey);
	}
	if(relation!=null&&!relation.trim().equals("")){
		RelationManager.updateRelation(relation);
	}
	if(reader!=null&&!reader.trim().equals("")){
		ReaderManager.updateJS(reader);
	}
%>

更新静态变量：
<form method=post action="updatestatic.jsp">
域表ID:<input type="text" name="tableid"><br>
代码表ID:<input type="text" name="codekey"><br>
更新联动:<input type="text" name="relation"><br>
更新读入块:<input type="text" name="reader"><br>
<input type="submit" value="更新">
</form>

<body>

</body>
</html>