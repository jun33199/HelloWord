<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.zhsb.web.SbbyzclForm"%>
<%@ page import="com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb" %>
<%@ page import="com.ttsoft.bjtax.smsb.util.JspUtil" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator"%>
<%@ page import="java.util.List" %>


<html>
<%
    response.setHeader("pragma", "no-cache");
    response.setHeader("Cache-control", "no-cache, no-store");
    response.setHeader("Expires", "0");
    String static_contextpath = SfResourceLocator.getContextPath(request);
    SbbyzclForm form = (SbbyzclForm)request.getAttribute("sbbyzclForm");
    Sbjkzb zb = form.getZb();
    List list=form.getMxList();
%>
<head>
<title>申报入库不一致处理查询明细</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
<script language="JavaScript">
	function doprint()
{
	document.all.dayin.style.display="none";
	window.print();
}
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<html:form action="/webapp/smsb/sbbyzclAction.do" method="POST" >
<html:hidden property="actionType" />
<input type="hidden" name="sjly" value="<%=zb.getSjly()%>">
<input type="hidden" name="sbbh" value="<%=zb.getSbbh()%>">
<table align="center" width='900'>
<tr>
  <td width="100" colspan='5'>&nbsp;</td>
</tr>	
<tr>
<td align="center" colspan='5'>
	<font size="3"><B>北京市地方税务局申报入库不一致信息核查确认单</B></font>
</td>
</tr>
<tr>
  <td width="100" colspan='5'>&nbsp;</td>
</tr>	
<table align="center" width='900' border='1' bordercolor='black' cellspacing="0">
<tr>
<td width='4%' rowspan='<%=list.size()+6%>' align="center">申报信息</td>
<td colspan='5'>
<div align='left'>申报日期：<%=JspUtil.format(zb.getSbrq())%></div><div align='right'>申报序号： <%=zb.getSbbh()%></div>
</td>
</tr>
<tr>
<td colspan='5'>
<%
  String sklxdm = zb.getSklxdm();
%>
<div align='left'>税款类型：
<%if(sklxdm.startsWith("1")){out.print("√");}else{out.print("□");}%>正常税款
<%if(sklxdm.startsWith("2")){out.print("√");}else{out.print("□");}%>四代解缴
<%if(sklxdm.startsWith("7")){out.print("√");}else{out.print("□");}%>补缴欠税
<%if(sklxdm.startsWith("4")){out.print("√");}else{out.print("□");}%>自查补税
</div><div align='right'>
金额单位：元（列至角分）
</div>
</td>
</tr>
<tr>
<td width='15%' align='middle'>
税务计算机代码
</td>
<td width='24%' align='middle'>
<%=form.getQueryJsjdm()%>
</td>
<td width='13%' align='middle'>
纳税人名称
</td>
<td width='44%' colspan="2" align='middle'>
<%=form.getNsrmc()%>
</td>
</tr>
<tr>
<td align='middle'>
缴款银行帐号
</td>
<td align='middle'>
<%if(zb.getZh()!=null){out.print(zb.getZh());}%>
</td>
<td align='middle'>
缴款银行名称
</td>
<td align='middle' colspan="2">
<%if(zb.getYhmc()!=null){out.print(zb.getYhmc());}%>
</td>
</tr>
<TR>
  <TD width='15%' align="center">纳税项目代码</TD>
  <TD width='24%' align="center">税款所属时期</TD>
  <TD width='13%' align="center">课税数量</TD>
  <TD width='22%' align="center">计税金额</TD>
  <TD width='22%' align="center">实缴税额</TD>
</TR>
<bean:define id="dataList" name="sbbyzclForm" property="mxList"/>
<logic:iterate name="dataList" indexId="index" id="item" type="com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx">
<tr>
  <td align="center">&nbsp;<%=item.getSzsmmc()%></td>
  <td align="center">&nbsp;<%=JspUtil.format(item.getSkssksrq())%>至<%=JspUtil.format(item.getSkssjsrq())%></td>
  <td align="center">&nbsp;<%=JspUtil.format(item.getKssl())%></td>
  <td align="right">&nbsp;<%=JspUtil.format(item.getJsje())%></td>
  <td align="right">&nbsp;<%=JspUtil.format(item.getSjse())%></td>
</tr>
</logic:iterate>
<TR>
  <TD align="center">合计</TD>
  <TD colspan="3">人民币大写：<%=form.getSjsehjdx()%></TD>
  <td align="right">￥<%=JspUtil.format(form.getHj())%></td>
</TR>
<TR>
	<td rowspan='3' align="center">检查确认情况</td>
  <TD valign='middle' align="center">选项□</TD>
  <TD height='80' colspan="3"><div align='left'>我声明，该笔申报为重复申报，现申请删除。</div><br><div align='right'>申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></TD>
  <TD valign='middle' rowspan='3' align="center"><div align="center">（纳税人公章）</div><p><div align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日</div></TD>
</TR>
<TR>
  <TD valign='middle' align="center">选项□</TD>
  <TD height='80' colspan="3"><div align='left'>我声明，该笔申报所涉及的税款尚未入库，确系本单位欠缴税款。</div><br><div align='right'>声明人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></TD>
</TR>
<TR>
  <TD valign='middle' align="center">备注</TD>
  <TD height='60' colspan="3">&nbsp;&nbsp;<%=JspUtil.format(zb.getBz())%></TD>
</TR>
</table>
<table align="center" width='900'>
<tr>
  <td colspan="6" align="center">
    &nbsp;<br><br>
  </td>
</tr>
<tr>
  <td colspan="6" align="center">
			<a style="cursor:hand"  tabIndex="-1" onClick="doprint(); return false;"><img name="beginpage" value="打印" alt="打印" onMouseOver="MM_swapImage('dayin','','<%=static_contextpath%>images/b-dysqb2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/b-dysqb1.jpg" width="79" height="22" id="dayin"></a>
  </td>
</tr>
</table>
</TABLE>
</html:form>
</BODY>
</HTML>
