<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@page import="com.creationstar.bjtax.qsgl.VisionLogic.form.JmsbForm"%>
<HTML>
<HEAD>
<TITLE>��˰�����걨������Ϣ��ʾ��</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>


<SCRIPT language=JavaScript>
function checkSubmit(operationType)
{

	document.forms[0].operationType.value=operationType;
	if(operationType=="Print" )
	{

			sbbh = document.forms[0].sbbh.value;
			window.open("/qsgl/jmsb/printJmsbxx.do?sbbh="+sbbh,"print","toolbar=no,location=yes,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=640,height=480");
  }
  if(operationType=="Return")
  {

  	document.forms[0].operationType.value="ReturnPri"

  	document.forms[0].submit();
  }

}


function doPrintHdtzs()
{
    window.open("/qsgl/qssb/printHdtzs.do?sbbh="+document.forms[0].sbbh.value,"print","toolbar=no,location=yes,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=640,height=480");
}


</SCRIPT>
<!----��ҳ���ͷ�ļ��ͱ�ҳ������·��----->
<SCRIPT language=javascript src="<%=static_file%>js/Header.js"></SCRIPT>

<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
	type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
<META content="MSHTML 5.00.2919.6307" name=GENERATOR>
</HEAD>
<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0
	marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
	<jsp:param name="subtitle" value="��˰�����걨¼��>��˰�����걨������Ϣ��ʾ��" />
	<jsp:param name="helpURL" value="" />
</jsp:include>

									<%
	JmsbForm bForm = (JmsbForm)session.getAttribute("jmsbForm");
	String rjl = bForm.getRjl();
    String tdjc = bForm.getTdjc();
    String qtjmlybeizhu = bForm.getQtjmlybeizhu();
    String qsjmlbmc = bForm.getQsjmlbmc();
 %>
<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%"
	width=98%>
	<TBODY>
		<TR>
			<TD vAlign=top>

			<TABLE align=center cellSpacing=0 class=table-99>
				<TBODY>
					<TR>
						<TD class=1-td1>�����еط�˰�����˰�����걨��-����</TD>
					</TR>
					<TR>
						<TD class=1-td2 vAlign=top><html:form
							action="/jmsb/addJmsbGr.do">
							<html:hidden property="operationType" />
							<html:hidden property="ztbs" />
							<html:hidden property="yhbs" />

							<TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
								<TBODY>
									<BR>
									<TR>
										<TD class=2-td1-left width="20%">��˰�����걨���</TD>
										<html:hidden name="jmsbForm" property="sbbh" />
										<TD class=2-td1-center width="43%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="sbbh" /></DIV>
										</TD>

									</TR>
									<TR>

										<TD class=2-td2-left width="20%">�������ع����������</TD>
										<TD class=2-td2-center width="23%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="fwtdbmslh" /></DIV>
										</TD>
									</TR>
								</TBODY>
							</TABLE>



							<TABLE border=0 cellSpacing=0 class=tabled-99 width="99%"
								cellpadding="0">
								<TBODY>
									<TR>
										<TD class=2-td2-left width="70%" colspan="4">
										<DIV align=left>������д����</DIV>
										</TD>
										<TD class=2-td2-center width="30%" colspan="2">&nbsp;</TD>

									</TR>

									<TR>
										<TD colspan="6" valign="top" width="100%">

										<table border="0" cellpadding="0" cellspacing="0" width="100%">
											<tr>
												<td width="20%" class="2-td2-left">��˰������</td>
												<td width="20%" class="2-td2-left">��ϵ�绰</td>
												<td width="20%" class="2-td2-left">���֤������</td>
												<td width="20%" class="2-td2-left">���֤������</td>
												<td width="10%" class="2-td2-left">����</td>
												<td width="10%" class="2-td2-center">��Ȩ������</td>
											</tr>

											<logic:iterate id="nsrdata" indexId="index" length="length"
												name="jmsbForm" property="nsrList">
												<tr>
													<td width="20%" class="2-td2-left"><bean:write
														name="nsrdata" property="nsrmc" /></td>
													<td width="20%" class="2-td2-left"><bean:write
														name="nsrdata" property="lxdh" />&nbsp;</td>
													<td width="20%" class="2-td2-left"><bean:write
														name="nsrdata" property="sfzjlxmc" /></td>
													<td width="20%" class="2-td2-left"><bean:write
														name="nsrdata" property="sfzjhm" /></td>
													<td width="10%" class="2-td2-left"><bean:write
														name="nsrdata" property="gjmc" /></td>
													<td width="10%" class="2-td2-center"><logic:equal
														name="nsrdata" property="cqrlx" value="1">
    ���в�Ȩ��
</logic:equal> <logic:equal name="nsrdata" property="cqrlx" value="0">
    ����Ȩ��
</logic:equal></td>
												</tr>
											</logic:iterate>

										</table>
										</TD>
									</TR>


									<tr>
										<TD class=2-td2-left width="8%" rowspan="7">
										<DIV align=right>���ط���</DIV>
										<DIV align=right>Ȩ��ת��</DIV>
										</TD>
										<TD class=2-td2-left width="15%">
										<DIV align=right>���ز���Ŀ����&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center width="15%"
											style="word-break:break-all">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="fdcxmmc" /></DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>��Լǩ��ʱ��&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="18%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="hyqdsj" /></DIV>
										</TD>
										<TD class=2-td2-left width="19%">
										<DIV align=right>����ԭ��&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="33%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="flmc" /></DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left width="25%";>
										<DIV align=right>���ء����������ַ&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center width="15%">
										<DIV align=left style="word-break:break-all">&nbsp;<bean:write
											name="jmsbForm" property="tdfwzldz" /></DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>���ء�����Ȩ��ת������&nbsp;</DIV>
										</TD>
										<!-- TD class=2-td2-left width="18%"-->
										<TD colspan="4" class=2-td2-center width="19%">
										<DIV align=left style="word-break:break-all">&nbsp;<bean:write
											name="jmsbForm" property="tdfwqszylxmc" /></DIV>
										</TD>

										<!-- TD class=2-td2-left width="19%">
										<DIV align=right>�������&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="33%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="fwlbmc" /></DIV>
										</TD-->
									</TR>
									<TR>
										<TD class=2-td2-left width="25%";>
										<DIV align=right>���ء�����Ȩ��ת�����&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="18%">
										<DIV align=left>&nbsp;���أ�<bean:write name="jmsbForm"
											property="tdfwqszymj" /> &nbsp;�O</DIV>
										</TD>
										<TD class=2-td2-left width="17%">
										<DIV align=right>���ݽ������&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="32%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="fwjzmj" /> &nbsp;�O</DIV>
										</TD>
									</TR>


									<%-- %> TR>
										<TD class=2-td2-left width="25%";>
										<DIV align=right>�ݻ���&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="18%">
										<DIV align=left>
										<%if(rjl.equals("00"))
	{
	%> 1.0���� <%
	}
	if(rjl.equals("01"))
	{
	%> 1.0���Ϻ�1.0 <%
	}
	%>&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="17%">
										<DIV align=right>���ؼ���&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="32%">
										<DIV align=left>
										<%if(tdjc.equals("01"))
	{
	%> һ��&nbsp; <%
	}
	if(tdjc.equals("02"))
	{
	%> ����&nbsp; <%
	}
	%> <%if(tdjc.equals("03"))
	{
	%> ����&nbsp; <%
	}
	if(tdjc.equals("04"))
	{
	%> �ļ�&nbsp; <%
	}
	%> <%if(tdjc.equals("05"))
	{
	%> �弶&nbsp; <%
	}
	if(tdjc.equals("06"))
	{
	%> ����&nbsp; <%
	}
	%> <%if(tdjc.equals("07"))
	{
	%> �߼�&nbsp; <%
	}
	if(tdjc.equals("08"))
	{
	%> �˼�&nbsp; <%
	}
	%> <%if(tdjc.equals("09"))
	{
	%> �ż�&nbsp; <%
	}
	if(tdjc.equals("10"))
	{
	%> ʮ��&nbsp; <%
	}
	%> <%if(tdjc.equals("11"))
	{
	%> ����ʮ��&nbsp; <%
	}
	%> &nbsp;</DIV>
										</TD>
									</TR><% --%>


									<TR>
										<TD class=2-td2-left width="8%" rowspan="2">
										<DIV align=right>�ɽ��۸�</DIV>
										<TD class=2-td2-left width="5%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="cjjgyrmb" /> &nbsp; <br>
										&nbsp;Ԫ(�����)</DIV>
										</TD>
										<TD class=2-td2-left width="8%">
										<DIV align=right>˰����غ˶��۸�&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="32%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="pgjg" /> &nbsp; <br>
										&nbsp;Ԫ(�����)</DIV>
										</TD>
									</TR>

									<TR>
										<TD class=2-td2-left width="15%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="cjjgywb" /> &nbsp; <br>
										&nbsp;Ԫ(���)</DIV>
										</TD>
										<TD class=2-td2-left width="15%">
										<DIV align=left>���֣� &nbsp;<bean:write name="jmsbForm"
											property="bzmc" /> &nbsp;<br>

										<DIV align=left>&nbsp;����: &nbsp;<bean:write
											name="jmsbForm" property="hn" />&nbsp;</DIV>
										</TD>

										<TD class=2-td2-center colspan="2" width="25%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="zhyrmb" /> &nbsp; <br>
										&nbsp;�ۺ�Ԫ(�����)</DIV>
										</TD>

									</tr>


									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>�����˰����˰����&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center width="85%">
										<DIV align=left><DIV align=left><%--%><bean:write name="jmsbForm"
											property="qsjmlbmc" /><% --%><%=qsjmlbmc %>&nbsp;</DIV></DIV>
										</TD>
									</TR>

									<% if(qtjmlybeizhu != null && !("".equals(qtjmlybeizhu))){ %>
									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>������������&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center width="85%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="qtjmlybeizhu" /></DIV>
										</TD>
									</TR>
									<% }%>

									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>��ע&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center width="84%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="beizhu" /> &nbsp;</DIV>
										</TD>
									</TR>

								</TBODY>
							</TABLE>
							<BR>

							<DIV align=center><IMG alt=��ӡ height=22 id=baocun
								name=Submit63 onclick="checkSubmit('Print')"
								onmouseout=MM_swapImgRestore()
								onmouseover="MM_swapImage('dayin','','<%=static_file%>images/dayin2.jpg',1)"
								src="<%=static_file%>images/dayin1.jpg" style="CURSOR: hand"
								width=79> <IMG alt=�˳� height=22 id=tuichu name=tuichu
								onclick="checkSubmit('Return');" onmouseout=MM_swapImgRestore()
								onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
								src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand"
								width=79></DIV>
							<BR>

						</html:form> <%@ include file="../include/Bottom.jsp"%>
</BODY>
</HTML>
