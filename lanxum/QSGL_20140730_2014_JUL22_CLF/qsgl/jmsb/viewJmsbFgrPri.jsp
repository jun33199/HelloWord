<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@page import="com.creationstar.bjtax.qsgl.VisionLogic.form.JmsbForm"%>
<HTML>
<HEAD>
<TITLE>��˰�����걨�Ǹ�����Ϣ��ʾ��</TITLE>
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
	<jsp:param name="subtitle" value="��˰�����걨¼��>��˰�����걨�Ǹ�����Ϣ��ʾ��" />
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
	width=770>
	<TBODY>
		<TR>
			<TD vAlign=top>

			<TABLE align=center cellSpacing=0 class=table-99>
				<TBODY>
					<TR>
						<TD class=1-td1>�����еط�˰�����˰�����걨��-�Ǹ���</TD>
					</TR>
					<TR>
						<TD class=1-td2 vAlign=top><html:form
							action="/jmsb/addJmsbFgr.do">
							<html:hidden property="operationType" value="" />
							<html:hidden property="ztbs" />
							<html:hidden property="yhbs" />
							<html:hidden property="sbbh" />


							<TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
								<TBODY>
									<BR>
									<TR>
										<TD class=2-td2-t-left width="20%">��˰�����걨���</TD>
										<TD class=2-td2-t-left width="43%" colspan=3>
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


							<TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
								<TBODY>

									<TR>
										<TD class=2-td2-t-left width="8%" rowspan="5">
										<DIV align=right>�Ǹ�����</DIV>
										<DIV align=right>д����</DIV>
										</TD>




									</tr>

									<tr>

										<TD class=2-td2-t-left width="18%">
										<DIV align=right>��˰���������&nbsp;</DIV>
										</TD>

										<DIV align=right>&nbsp;</DIV>
										<TD class=2-td2-t-left width="18%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="jsjdm" /></DIV>
										</TD>



										<TD class=2-td2-t-left width="19%">
										<DIV align=right>��˰������&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-t-center width="33%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="nsrlxmc" /></DIV>
										</TD>
									</TR>



									<TR>
										<TD class=2-td2-left>
										<DIV align=right>��˰������&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center>
										<DIV align=left style="word-break:break-all">&nbsp;<bean:write
											name="jmsbForm" property="nsrmc" /></DIV>
										</TD>
									</TR>

									<TR>
										<TD class=2-td2-left>
										<DIV align=right>��������&nbsp;</DIV>
										</TD>
										<TD class=2-td2-center colspan="4">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="khyhmc" /></DIV>
										</TD>

									</TR>


									<TR>
										<TD class=2-td2-left>
										<DIV align=right>��ϵ������&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="lxrxm" /></DIV>
										</TD>


										<TD class=2-td2-left>
										<DIV align=right>��ϵ�绰&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="33%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="lxdh" /></DIV>
										</TD>
									</TR>


									<TR>
										<TD class=2-td2-left rowspan="7">
										<DIV align=right>���ط���</DIV>
										<DIV align=right>Ȩ��ת��</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=right style="word-break:break-all">���ز���Ŀ����&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center nowrap>
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="fdcxmmc" />&nbsp;</DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left>
										<DIV align=right>��Լǩ��ʱ��&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="hyqdsj" /> &nbsp;</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=right>����ԭ��&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center>
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="flmc" /> &nbsp;</DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left>
										<DIV align=right>���ء����������ַ&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center>
										<DIV align=left style="word-break:break-all">&nbsp;<bean:write
											name="jmsbForm" property="tdfwzldz" /> &nbsp;</DIV>
										</TD>
									</TR>									
									<TR>
										<TD class=2-td2-left>
										<DIV align=right>���ء�����Ȩ��ת������&nbsp;</DIV>
										</TD>
										<!--TD class=2-td2-left -->
										<TD colspan="4" class=2-td2-center>
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="tdfwqszylxmc" /> &nbsp;</DIV>
										</TD>
										<!--TD class=2-td2-left>
										<DIV align=right>�������&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center>
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="fwlbmc" /> &nbsp;</DIV>
										</TD-->
									</TR>
									<TR>
										<TD class=2-td2-left>
										<DIV align=right>���ء�����Ȩ��ת�����&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=left>&nbsp;���أ�<bean:write name="jmsbForm"
											property="tdfwqszymj" /> &nbsp; �O</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=right>���ݽ������&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center>
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="fwjzmj" /> &nbsp; �O</DIV>
										</TD>
									</TR>


									<%-- %><TR>
										<TD class=2-td2-left>
										<DIV align=right>�ݻ���&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left>
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
										<TD class=2-td2-left>
										<DIV align=right>���ؼ���&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center>
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
										<TD class=2-td2-left rowspan="2">
										<DIV align=right>�ɽ��۸�</DIV>
										<TD class=2-td2-left>
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="cjjgyrmb" /> &nbsp; <br>
										&nbsp;Ԫ(�����)</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=right>˰����غ˶��۸�&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center>
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="pgjg" /> &nbsp; <br>
										&nbsp;Ԫ(�����)</DIV>
										</TD>
									</TR>

									<TR>
										<TD class=2-td2-left>
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="cjjgywb" /> &nbsp; <br>
										&nbsp;Ԫ(���)</DIV>
										</TD>
										<TD class=2-td2-center>
										<DIV align=left>���֣�&nbsp;<bean:write name="jmsbForm"
											property="bzmc" /> &nbsp;<br>

										<DIV align=left>&nbsp;����: &nbsp;<bean:write
											name="jmsbForm" property="hn" />&nbsp;</DIV>
										</TD>

										<TD class=2-td2-center colspan="2">
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
										<TD class=2-td2-left>
										<DIV align=right>��ע&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center>
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
