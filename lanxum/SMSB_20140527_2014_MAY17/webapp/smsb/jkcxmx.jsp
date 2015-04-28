<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.jkcx.web.JkcxForm"%>
<%@ page import="com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb" %>
<%@ page import="com.ttsoft.bjtax.smsb.util.JspUtil" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator"%>

<html>
<%
    response.setHeader("pragma", "no-cache");
    response.setHeader("Cache-control", "no-cache, no-store");
    response.setHeader("Expires", "0");
    String static_contextpath = SfResourceLocator.getContextPath(request);
    JkcxForm form = (JkcxForm)request.getAttribute("jkcxForm");
    Sbjkzb zb = form.getZb();
%>
<head>
<title>缴款查询明细</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
<script language="JavaScript">
function doPrint()
{
    document.forms[0].actionType.value = "Print";
    document.forms[0].target="_self";
    document.forms[0].submit();
}
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<html:form action="/webapp/smsb/jkcxAction.do" method="POST" >
<html:hidden property="actionType" />
<html:hidden property="jsjdm"/>
<html:hidden property="jkpzh"/>
<input type="hidden" name="sjly" value="<%=zb.getSjly()%>">
<input type="hidden" name="sbbh" value="<%=zb.getSbbh()%>">
<table align="center" width='750' border='1' bordercolor='black'>
<tr>
<td align="center" colspan='4'>
北京市地方税务局综合申报表
</td>
</tr>
<tr>
<td colspan='4'>
<div align='center'>申报日期：<%=JspUtil.format(zb.getSbrq())%></div><div align='right'>表号： <%=zb.getSbbh()%></div>
</td>
</tr>
<tr>
<td colspan='4'>
<%
  String sklxdm = zb.getSklxdm();
%>
税款类型：
<%if(sklxdm.startsWith("1")){out.print("√");}else{out.print("□");}%>正常税款
<%if(sklxdm.startsWith("2")){out.print("√");}else{out.print("□");}%>四代解缴
<%if(sklxdm.startsWith("7")){out.print("√");}else{out.print("□");}%>补缴欠税
<%if(sklxdm.startsWith("4")){out.print("√");}else{out.print("□");}%>自查补税
</td>
</tr>
<tr>
<td align='right' colspan='4'>
金额单位：元（列至角分）
</td>
</tr>
<tr>
<td width='20%' align='right'>
税务计算机代码
</td>
<td>
<%=form.getJsjdm()%>
</td>
<td width='20%' align='right'>
纳税人名称（公章）
</td>
<td>
<%=form.getNarmc()%>
</td>
</tr>
<tr>
<td width='20%' align='right'>
缴款银行帐号
</td>
<td>
<%if(zb.getZh()!=null){out.print(zb.getZh());}%>
</td>
<td width='20%' align='right'>
缴款银行名称
</td>
<td>
<%if(zb.getYhmc()!=null){out.print(zb.getYhmc());}%>
</td>
</tr>
<table>
<TABLE align="center" width='750' border='1' bordercolor='black'>
<TR>
  <TD align="center">纳税项目代码</TD>
  <TD align="center">税款所属时期</TD>
  <TD align="center">课税数量</TD>
  <TD align="center">计税金额</TD>
  <TD align="center">实缴税额</TD>
</TR>
<bean:define id="dataList" name="jkcxForm" property="mxList"/>
<logic:iterate name="dataList" indexId="index" id="item" type="com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx">
<tr>
  <td align="center">&nbsp;<%=item.getSzsmmc()%></td>
  <td align="center">&nbsp;<%=JspUtil.format(item.getSkssksrq())%>至<%=JspUtil.format(item.getSkssjsrq())%></td>
  <td align="center">&nbsp;<%=JspUtil.format(item.getKssl())%></td>
  <td align="center">&nbsp;<%=JspUtil.format(item.getJsje())%></td>
  <td align="center">&nbsp;<%=JspUtil.format(item.getSjse())%></td>
</tr>
</logic:iterate>
<TR>
  <TD width='10%' align="center">合计</TD>
  <TD colspan="3">人民币大写：<%=form.getSjsehjdx()%></TD>
  <td>￥<%=JspUtil.format(form.getHj())%></td>
</TR>
<TR>
  <TD valign='middle' align="center">备注</TD>
  <TD height='150' colspan="4">&nbsp;&nbsp;<%=JspUtil.format(zb.getBz())%></TD>
</TR>
<tr>
  <td colspan="5" align="center">
    <a style="cursor:hand"  tabIndex="-1" onClick="window.print(); return false;"><img name="beginpage" value="打印" alt="打印" onMouseOver="MM_swapImage('dayin','','<%=static_contextpath%>images/b-dysqb2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/b-dysqb1.jpg" width="79" height="22" id="dayin"></a>
  </td>
</tr>
</TABLE>
</html:form>
</BODY>
</HTML>
