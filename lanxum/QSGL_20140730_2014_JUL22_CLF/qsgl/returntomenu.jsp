<%@ page contentType="text/html;charset=GBK"%>
<%@page import="com.ttsoft.common.util.SessionKey"%>
<html>
<%
    //debug��ʵ���Ǵ�Ӧ�ð�ȫ�İ��л�ȡ
    String return_url = (String)session.getAttribute(SessionKey.PARAM_MENU);
//    session.invalidate();
%>
<head>
<META HTTP-EQUIV="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<meta http-equiv="refresh" content="0;url=<%=return_url%>" >
</head>
<body>
</body>
</html>
