<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.web.PKTaskForm"%>

<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>
<head>
<title>批扣测试页面</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/function.js"></script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<%@ include file="./include/header.jsp"%>

<html:form action="/webapp/smsb/pktaskAction.do" method="POST" >
<html:hidden property="actionType" />

<table width="96%" align="center" cellspacing="0" class="table-99">
  <tr>
    <td class="1-td1">批扣测试页面</td>
  </tr>
  <tr>
    <td class="1-td2">
      <br>
      <table width="98%" border="0" cellpadding="5" cellspacing="0">
        <tr class="black9">
          
          <td nowrap class="2-td2-t-left"><div align="right">执行时间(<FONT SIZE="" COLOR="#FF0000">*</FONT>)&nbsp;&nbsp;</div></td>
          <td nowrap class="2-td2-t-center">
            <div align="left">&nbsp;&nbsp;
              <html:text property="zxsj" size="12" maxlength="12"/>(YYYYmm)
            </div>
          </td>
        </tr>
      </table>
      <br>
      <table width="94%" border="0" cellpadding="0" cellspacing="4">
        <tr  class="black9">
          <td width="20%">&nbsp;</td>
          <td width="20%" align="center"><input type="image" accesskey="c" tabIndex="-1" onClick="doQuery(); return false;"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="查询" id="bc1" src="<%=static_contextpath%>images/chaxun1.jpg" width="79" height="22"></td>
		  <td width="20%" align="center"><input type="button" accesskey="c" tabIndex="-1" onClick="doGenDKXX(); return false;"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="生成代扣" id="bc1" src="<%=static_contextpath%>images/chaxun1.jpg" width="79" height="22"></td>
          <td width="20%" align="center"><input type="image" accesskey="t" tabIndex="-1" onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc','','<%=static_contextpath%>images/tc-c1.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22"></td>
          <td width="20%">&nbsp;</td>
        </tr>
      </table>
      <br>
    </td>
  </tr>
</table>
</html:form>

<%@ include file="./include/footer.jsp"%>
</body>
<script language="JavaScript">

//查询提交
function doQuery()
{
    if (document.forms[0].zxsj.value.length <=0)
    {
      alert("执行时间不能为空！");
      return false;
    }
	alert(document.forms[0].zxsj.value);
    document.forms[0].actionType.value = "Test1";
    //document.forms[0].target="_self";
    document.forms[0].submit();
}
//生成代扣信息
function doGenDKXX(){
	alert(document.forms[0].zxsj.value);
	document.forms[0].actionType.value = "GenDKXX";
    //document.forms[0].target="_self";
    document.forms[0].submit();
}

function doQuXiao()
{
    document.forms[0].actionType.value = "Show";
    document.forms[0].target="_self";
    document.forms[0].submit();
}
</script>
</html:html>
