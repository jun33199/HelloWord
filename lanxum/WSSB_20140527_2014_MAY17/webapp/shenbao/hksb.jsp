<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import = "java.util.Map"%>
<%@page import = "java.util.Iterator"%>
<%@page import = "java.util.List"%>
<%@page import = "com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import = "com.ttsoft.bjtax.shenbao.zhsb.web.ZhsbForm"%>
<%@page import = "com.ttsoft.bjtax.shenbao.constant.CodeConstant"%>
<%@page import = "com.ttsoft.bjtax.dj.model.YHZH"%>
<%@page import = "com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb"%>
<%@page import = "com.ttsoft.bjtax.shenbao.model.client.SbjkmxData"%>
<%@page import = "com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.syax.common.web.action.ActionErrors"%>
<%
String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<%@ include file="zhsbFunc.jsp" %>

<html>
<head>
<title>已申报数据</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/zhsb.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/JavaScript">
function doExit()
{
    document.forms[0].action = "quit.do";
    document.forms[0].submit();
}
function doReturn()
{
    document.forms[0].actionType.value = "Return";
    document.forms[0].submit();
}
</script>

<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
    <table width="97%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td align="center" valign="top">
                <jsp:include page="../include/SbHeader.jsp" flush="true" >
                <jsp:param name="name" value="划款失败" />
                </jsp:include>
            </td>
        </tr>
        <tr>
            <td>
                <table border="1" align="center" cellpadding="4" cellspacing="1" bordercolor="#496C8B" bgcolor="#FFFFFF" class="black9">
                    <tr bgcolor="#E7EEF1">
                        <td align="center">
                            <font color="red">发生错误</font>
                        </td>
                    </tr>
<%
ActionErrors nowerr = (ActionErrors) request.getAttribute(ActionErrors.REQ_ERRORS_KEY);
for (int i=0;i<nowerr.getErrorSize();i++){
    String err = nowerr.getError(i);
    if(err.indexOf("管理员")>0 || err.indexOf("技术支持")>0){
        err = "系统错误。CWA-01-0101，请5分钟后【查看本期缴款书】核对是否已经形成【电子缴库专用缴款书】，并及时核对银行账户金额；如仍有问题，请与技术支持联系，谢谢！";
    }
%>
                    <tr bgcolor="#E7EEF1">
                        <td align="left">
                            <font color="blue">
                                <li><%=err%></li>
                            </font>
                        </td>
                    </tr>
                </table>
                <br>
<%
}
%>
            </td>
        </tr>
        <tr>
            <td align="center">
            <form name="form" method="POST" action="/shenbao/zhsbWithSfxy.dc">
            <input name="actionType" type="hidden" id="actionType">
            <img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturn()" style="cursor:hand">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <img src="<%=static_contextpath%>images/tuichu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tuichu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tuichu1.jpg'" alt="退出" onclick="doExit();" style="cursor:hand">
            </form>
            </td>
        </tr>
        <tr>
            <td>
                <table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">
                    <tr>
                        <td width="40%"> <hr width="100%" size="1" class="hr1" >
                        </td>
                        <td width="20%" align="center" class="black9"><strong><font color="#0000FF">注意事项</font></strong></td>
                        <td width="40%"> <hr width="100%" size="1" class="hr1" >
                        </td>
                    </tr>
                </table>
                <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
                    <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                        <td height="47"  >
                            <p>
                                &nbsp;&nbsp;&nbsp;&nbsp;1.点击【返回】按钮回到税种税目选择界面继续申报。<br>
                                &nbsp;&nbsp;&nbsp;&nbsp;2.点击【退出】按钮退出申报回到功能清单界面。<br>
                            </p>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td valign="bottom" align="center">
                <%@ include file="../include/bottom.jsp" %>
            </td>
        </tr>
    </table>
</body>
</html>
