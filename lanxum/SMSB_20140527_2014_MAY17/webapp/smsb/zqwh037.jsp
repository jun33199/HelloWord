<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

<title>征期日历维护</title>

<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/list.js">
</script>

<link href="../css/text.css" rel="stylesheet" type="text/css">

</head>
<%@ include file="./include/header.jsp"%>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0"  >


<table width="100%" height="50%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"> <br>
      <table align="center" cellspacing="0" class="table-99" border="0">
        <tr class="black9">
          <td class="1-td1">征期日历维护</td>
        </tr>
        <tr>
          <td valign="top" class="1-td2"> <p><br>
              <br>
            </p>
            <table width="50%" border="0">
              <tr>
                <td width="39%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td width="61%"><a href="jjrwhAction.do">节假日维护</a></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><a href="zqlxwhAction.do?actionType=Query">征期类型维护</a></td>
              </tr><!--
              <tr>
                <td>&nbsp;</td>
                <td><a href="zqrlcxAction.do?actionType=Query">征期日历查询</a></td>
              </tr>-->
              <tr>
                <td>&nbsp;</td>
                <td><a href="szzqdzAction.do?actionType=Show">税种与征期类型对照关系维护</a></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><a href="zqrlmxcxAction.do?actionType=Show">征期日历详细信息维护</a></td>
              </tr>
            </table>
            <div align="center"> </div>
            <br>
            <div align="center">&nbsp;&nbsp; <input type="image" accesskey="c" onClick="tuichu();return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22">
            </div>
            <br> <br>
             </td>
        </tr>
      </table>
      <br></td>
    </tr>
</table>
<script language="javascript">
</script>
</body>
<%@ include file="./include/footer.jsp"%>

</html:html>
