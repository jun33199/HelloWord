<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="com.ttsoft.common.util.SessionKey"%>
<html>
<%
    java.util.Vector alertMessages = (java.util.Vector)request.getAttribute(com.ttsoft.framework.util.JspUtil.REQUEST_KEY_ALERT_MESSAGE);
if (alertMessages!=null && alertMessages.size()>0)
{
    String alertMsg = "";
    for (int i=0;i<alertMessages.size();i++)
    {
        alertMsg += (String)alertMessages.elementAt(i)+"\n";
    }
%>
<script language="javascript">
    alert("<%=com.ttsoft.framework.util.JspUtil.displayValue(alertMsg,com.ttsoft.framework.util.JspUtil.ESCAPE_FOR_JAVASCRIPT)%>");
</script>
<%
}
    //debug，实际是从应用安全的包中获取
    String return_url = (String)session.getAttribute(SessionKey.PARAM_MENU);
    session.invalidate();
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
