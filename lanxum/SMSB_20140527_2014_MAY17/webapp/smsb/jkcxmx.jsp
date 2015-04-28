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
<title>�ɿ��ѯ��ϸ</title>
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
�����еط�˰����ۺ��걨��
</td>
</tr>
<tr>
<td colspan='4'>
<div align='center'>�걨���ڣ�<%=JspUtil.format(zb.getSbrq())%></div><div align='right'>��ţ� <%=zb.getSbbh()%></div>
</td>
</tr>
<tr>
<td colspan='4'>
<%
  String sklxdm = zb.getSklxdm();
%>
˰�����ͣ�
<%if(sklxdm.startsWith("1")){out.print("��");}else{out.print("��");}%>����˰��
<%if(sklxdm.startsWith("2")){out.print("��");}else{out.print("��");}%>�Ĵ����
<%if(sklxdm.startsWith("7")){out.print("��");}else{out.print("��");}%>����Ƿ˰
<%if(sklxdm.startsWith("4")){out.print("��");}else{out.print("��");}%>�Բ鲹˰
</td>
</tr>
<tr>
<td align='right' colspan='4'>
��λ��Ԫ�������Ƿ֣�
</td>
</tr>
<tr>
<td width='20%' align='right'>
˰����������
</td>
<td>
<%=form.getJsjdm()%>
</td>
<td width='20%' align='right'>
��˰�����ƣ����£�
</td>
<td>
<%=form.getNarmc()%>
</td>
</tr>
<tr>
<td width='20%' align='right'>
�ɿ������ʺ�
</td>
<td>
<%if(zb.getZh()!=null){out.print(zb.getZh());}%>
</td>
<td width='20%' align='right'>
�ɿ���������
</td>
<td>
<%if(zb.getYhmc()!=null){out.print(zb.getYhmc());}%>
</td>
</tr>
<table>
<TABLE align="center" width='750' border='1' bordercolor='black'>
<TR>
  <TD align="center">��˰��Ŀ����</TD>
  <TD align="center">˰������ʱ��</TD>
  <TD align="center">��˰����</TD>
  <TD align="center">��˰���</TD>
  <TD align="center">ʵ��˰��</TD>
</TR>
<bean:define id="dataList" name="jkcxForm" property="mxList"/>
<logic:iterate name="dataList" indexId="index" id="item" type="com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx">
<tr>
  <td align="center">&nbsp;<%=item.getSzsmmc()%></td>
  <td align="center">&nbsp;<%=JspUtil.format(item.getSkssksrq())%>��<%=JspUtil.format(item.getSkssjsrq())%></td>
  <td align="center">&nbsp;<%=JspUtil.format(item.getKssl())%></td>
  <td align="center">&nbsp;<%=JspUtil.format(item.getJsje())%></td>
  <td align="center">&nbsp;<%=JspUtil.format(item.getSjse())%></td>
</tr>
</logic:iterate>
<TR>
  <TD width='10%' align="center">�ϼ�</TD>
  <TD colspan="3">����Ҵ�д��<%=form.getSjsehjdx()%></TD>
  <td>��<%=JspUtil.format(form.getHj())%></td>
</TR>
<TR>
  <TD valign='middle' align="center">��ע</TD>
  <TD height='150' colspan="4">&nbsp;&nbsp;<%=JspUtil.format(zb.getBz())%></TD>
</TR>
<tr>
  <td colspan="5" align="center">
    <a style="cursor:hand"  tabIndex="-1" onClick="window.print(); return false;"><img name="beginpage" value="��ӡ" alt="��ӡ" onMouseOver="MM_swapImage('dayin','','<%=static_contextpath%>images/b-dysqb2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/b-dysqb1.jpg" width="79" height="22" id="dayin"></a>
  </td>
</tr>
</TABLE>
</html:form>
</BODY>
</HTML>
