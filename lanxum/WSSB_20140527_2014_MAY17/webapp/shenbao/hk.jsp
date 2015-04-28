<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
response.setHeader("pragma", "no-cache");
response.setHeader("Cache-control", "no-cache, no-store");
response.setHeader("Expires", "0");
%>
<html>
<head>
<title>操作结果</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="javascript">

<%/*退出*/%>
function doExit()
{
    document.forms[0].action = "/shenbao/quit.dc?actionType=Quit";
	document.forms[0].target = "_self";
    document.forms[0].submit();
}
function doReturn()
{
    document.forms[0].action = "<%=request.getAttribute("REQ_KEY_RETURN_ADDRESS")%>";
	document.forms[0].target = "_self";
    document.forms[0].submit();
}
function doDownload(lsh)
{
	document.forms[0].action = '/redirect/qmyj/dzyjsjmx.dc?actionType=Download&lsh=' + lsh;
	document.forms[0].target = "_self";
    document.forms[0].submit();
}

var p=0,j=0;
var c=new Array("lightskyblue","white")
setInterval('proccess();',100)
function proccess(){
	document.forms.proccess.elements[p].style.background=c[j];
	p+=1;
	if(p==30){p=0;j=1-j;}
}
var CallTimeLen = "1";
var timer1 = null;

function DoCallTimer()
{
  var minute="0";
  var second="30";
  CallTimeLen = parseInt(CallTimeLen)+1;
  minute = parseInt(CallTimeLen/60);
  second = CallTimeLen%60;
  if(minute=="0")
  {
   document.form.thzt.innerText =second+"秒";
  }
  else
  {
   document.form.thzt.innerText =minute+"分"+second+"秒";
  }
  if(CallTimeLen==1800)
  {
    document.form.actionType.value="TimeOut";
    document.form.submit();
  }
  window.timer1 = window.setTimeout("DoCallTimer()",1000);
}

function doHK()
{
    DoCallTimer();
    document.form.actionType.value="HK";
    document.form.submit();
}
function popUp(){
    props=window.open('<%=static_contextpath%>' + '/about/about.htm','poppage','toolbars=0,scrollbars=0,location=0,statusbara=0,menubars=0,resizable=0,width=500,height=267');
}
</script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<style>
.proccess {
    BACKGROUND: #ffffff; BORDER-BOTTOM: 1px solid; BORDER-LEFT: 1px solid; BORDER-RIGHT: 1px solid; BORDER-TOP: 1px solid; HEIGHT: 14px; MARGIN: 3px; WIDTH: 14px
}
.style1 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 9pt;
}
</style>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="doHK()">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>
<table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=static_contextpath%>images/q-top-bg-01.jpg">
  <tr>
    <td><img width="495" height="58" src="<%=static_contextpath%>images/q-top-tu-01.jpg"/></td>
    <td align="right"><img width="284" height="58" src="<%=static_contextpath%>images/q-top-tu-02.jpg"/></td>
  </tr>
</table>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/swapImages.js"></script>
<table width="100%" height="23" border="0" cellpadding="0" cellspacing="0" background="<%=static_contextpath%>images/q-top-bg-02.jpg" class="black9">
  <tr background="<%=static_contextpath%>images/q-top-bg-02.jpg">
    <td width="88%"><font color="#A4B9C6">综合服务管理信息系统</font>&gt;<font color="#7C9AAB">网上服务系统</font>&gt;<font color="#6F8EA2">综合申报</font>&gt;划款</td>
    <td width="7%" valign="bottom" nowrap>
      <img src="<%=static_contextpath%>images/t-help1.jpg" name="Image2" id="Image2" onMouseOver="MM_showHideLayers('Layer1','','show');this.src='<%=static_contextpath%>images/t-help2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/t-help1.jpg';MM_showHideLayers('Layer1','','hide')" style="cursor:hand">
      <div id="Layer1" style="Z-INDEX: 1; WIDTH: 100px; POSITION: absolute; HEIGHT: 10px; right: 1px; top: 77px; visibility: hidden; margin-right: 1% "
            onMouseOut="MM_showHideLayers('Layer1','','hide')">
        <table width="80" cellspacing=0 onMouseOver="MM_showHideLayers('Layer1','','show')" >
          <tr>
              <td class="2-td2-t-center"><a href="<%=static_contextpath%><%=request.getParameter("help_url")%>" target="_blank">帮助</a></td>
            </tr>
            <tr>
              <td class="2-td2-center"><div align="center"><a href="javascript:popUp()">关于</a></div></td>
            </tr>
        </table>
      </div>
    </td>
  </tr>
</table>
      <table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td  class="1-td2" valign="middle">
              <DIV align=center>
                  <table width="100%" height="70%" align="center" cellpadding="8" cellspacing="1">
                      <TBODY>
                      <TR>
                          <TD align=middle>
                              <!--  Displaytext-->
                              <p align="center"><FONT class=fontbig></font></p><br/><br/>
                              <span style="font-family: 宋体; font-size: 20pt; font-weight: bold;font-color:black">划款中, 请稍候...</span><br>
                              <span style="font-family: Tahoma, Arial; font-size: 9pt;font-color:black"></span><br><br>
                              <span style="font-family: 宋体; font-size: 20pt; font-weight: bold;font-color:black">请勿中断操作，请勿关闭浏览器！</span><br>
                              </font>
                              <FONT class=fontbig>
                                  <DIV align=center>
                                  <FORM method=post name=proccess>
                                  <SCRIPT language=javascript>
                                  for(i=0;i<30;i++)document.write("<input class=proccess readonly>")
                                  </SCRIPT>
                                  </FORM>
                                  </DIV>
                              </font>
                          </TD>
                      </TR>
                      <TR>
                          <TD align=middle>
                              <form name="form" action="/shenbao/zhsbWithSfxy.dc">
                                  <input type="hidden" name="actionType">
                                  <input type="hidden" name="REQUEST_TOKEN" value="<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>">
                                  <input type="text" name="thzt" id="thzt" readonly>
                              </form>
                          </TD>
                      </TR>
                      </TBODY>
                  </TABLE>
              </DIV>
          </td>
        </tr>
      </table><br><br><br>
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
                    &nbsp;&nbsp;&nbsp;&nbsp;1.在页面未显示结果前请勿关闭当前页面。<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;2.如果在太长时间内页面无显示划款结果请查询银行账户余额。<br>
                </p>
              </td>
          </tr>
      </table>
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
