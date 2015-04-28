<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%
String msg = (String)request.getAttribute(Constants.MESSAGE_KEY);
if ((msg == null) || (msg.equals("")))
{
    msg = (String)session.getAttribute(Constants.MESSAGE_KEY);
    session.removeAttribute(Constants.MESSAGE_KEY);
}
if ((msg == null) || (msg.equals("")))
{
    msg = request.getParameter(Constants.MESSAGE_KEY);
}
if (msg != null)
{
%>
<table>
<tr>
<td align="left">
<FONT color="red"><%=msg%></FONT>
</td>
</tr>
</table>
<%
}
%>
