<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="com.ttsoft.common.model.UserData"%>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.FriendHelper"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="java.util.Map"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<html>
<head>
<title>打印申报成功回执</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="sbcghzPrint001Action.do" method="POST">
<html:hidden property="actionType"/>
<html:hidden property="h_jsjdm"/>
<html:hidden property="h_sbbh"/>
<br>
<html:errors />
<div align="center">
  <h1><font color="#000000"><strong>缴款申请单</strong></font></h1>
</div>
<div align="center" >
	<table>
		<tr>
			<td>
				□ 已申报   申报序号：<u><bean:write name='sbcghzPrint001ActionForm' property='sbbh'/></u><br>
	 			□ 未申报
			</td>
		</tr>
	</table>
</div>
<br>
<div align="center" >
<table width="600">
		<tr>
			<td align="left">
				申请日期：<bean:write name='sbcghzPrint001ActionForm' property='sbrq'/>
			</td>
			<td align="right">
				金额单位：元（角分）
			</td>
		</tr>
	</table>
</div>
<div align="center" >
  <table width="630" border="1" align="center" cellpadding="1" cellspacing="0" bordercolor="#000000" id="main">
    <tr>
      <th align="center" colspan="2" nowrap> 地税计算机代码 </th>
      <td align="center" colspan="3" nowrap><bean:write name='sbcghzPrint001ActionForm' property='jsjdm'/>&nbsp; </td>
      <th align="center" colspan="2" nowrap> 缴款单位（人） </th>
      <td align="center" colspan="3" nowrap><bean:write name='sbcghzPrint001ActionForm' property='nsrmc'/>&nbsp; </td>
    </tr>
    <tr>
      <th align="center" colspan="2" nowrap> 缴款银行帐号 </th>
      <td align="center" colspan="3" nowrap><bean:write name='sbcghzPrint001ActionForm' property='zh'/>&nbsp; </td>
      <th align="center" colspan="2" nowrap> 缴款银行名称 </th>
      <td align="center" colspan="3" nowrap><bean:write name='sbcghzPrint001ActionForm' property='yhmc'/>&nbsp; </td>
    </tr>
    <tr>
      <th align="center" nowrap><font size="2">序号</font></th>
      <th colspan="3" align="center" nowrap><font size="2">纳税项目代码</font></th>
      <th colspan="1" align="center" nowrap><font size="2">课税数量 </font> </th>
      <th width="155" colspan="3" align="center" nowrap><font size="2">计税金额</font></th>
      <th width="155" colspan="2" align="center" nowrap><font size="2">实缴金额</font></th>
    </tr>
	<bean:define id="itemjksprint" name="sbcghzPrint001ActionForm" property="mxs"/>
	<%
		int xh=1;
	%>
	<logic:iterate id="itemprint" name="itemjksprint" indexId="index">
		<tr>
		  <td align="center"><font size="2"><%=xh%></font></td>
		  <td colspan="3" align="center" nowrap><font size="2"><%=((Map)itemprint).get("szsmmc")%>&nbsp;</font></td>
		  <td colspan="1" align="center" nowrap ><font size="2"><%=((Map)itemprint).get("kssl")%>&nbsp; </font></td>
		  <td colspan="3" align="center" nowrap ><font size="2"><%=((Map)itemprint).get("jsje")%>&nbsp;</font></td>
		  <td colspan="2" align="center" nowrap><font size="2"><%=((Map)itemprint).get("sjje")%>&nbsp;</font></td>
		</tr>
	<%
		xh++;
	%>
	</logic:iterate>    
    <tr>
      <th align="center" colspan="1">备注</th>
      <td align="right" colspan="9"><bean:write name='sbcghzPrint001ActionForm' property='bz'/>&nbsp;&nbsp;&nbsp;合计金额：<bean:write name='sbcghzPrint001ActionForm' property='hjjexx'/>&nbsp;&nbsp;&nbsp;</td>
    </tr>
  </table>
</div>
<div align="center">
	<table  width="580" border="0">
		<tr>
			<td align="right">缴款单位（人）签章</td>
		</tr>
	</table>
</div>
<br><br>
<div align="center">
	<table  width="580" border="0">
		<tr>
			<td align="left">注：1、此表用于在银行柜台缴款，不作为申报资料</td>
		</tr>
	</table>
</div>
<br><br>
<div id="dayin" align="center">
<img style="cursor:hand" onclick="printTab()" src="<%=static_contextpath%>/images/dayin1.jpg" width="79" height="22" id="dayin1"/>&nbsp;&nbsp;<img style="cursor:hand" onclick="window.close()" src="<%=static_contextpath%>/images/guanbi1.jpg" width="79" height="22" id="guanbi"/>
</div>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function printTab(){
			 document.all.dayin.style.display = "none";
			 window.print();
			 document.all.dayin.style.display = "";
	}
//-->
</SCRIPT>
</html:form>
</body>
</html>
