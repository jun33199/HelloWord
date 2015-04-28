<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@page import="com.creationstar.bjtax.qsgl.VisionLogic.form.JmsbForm"%>
<HTML>
<HEAD>
<TITLE>契税减免申报个人信息显示表</TITLE>
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
<!----本页面的头文件和本页帮助的路径----->
<SCRIPT language=javascript src="<%=static_file%>js/Header.js"></SCRIPT>

<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
	type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
<META content="MSHTML 5.00.2919.6307" name=GENERATOR>
</HEAD>
<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0
	marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
	<jsp:param name="subtitle" value="契税减免申报录入>契税减免申报个人信息显示表" />
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
						<TD class=1-td1>北京市地方税务局契税减免申报表-个人</TD>
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
										<TD class=2-td1-left width="20%">契税减免申报表号</TD>
										<html:hidden name="jmsbForm" property="sbbh" />
										<TD class=2-td1-center width="43%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="sbbh" /></DIV>
										</TD>

									</TR>
									<TR>

										<TD class=2-td2-left width="20%">房屋土地管理部门受理号</TD>
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
										<DIV align=left>个人填写部分</DIV>
										</TD>
										<TD class=2-td2-center width="30%" colspan="2">&nbsp;</TD>

									</TR>

									<TR>
										<TD colspan="6" valign="top" width="100%">

										<table border="0" cellpadding="0" cellspacing="0" width="100%">
											<tr>
												<td width="20%" class="2-td2-left">纳税人名称</td>
												<td width="20%" class="2-td2-left">联系电话</td>
												<td width="20%" class="2-td2-left">身份证件类型</td>
												<td width="20%" class="2-td2-left">身份证件号码</td>
												<td width="10%" class="2-td2-left">国籍</td>
												<td width="10%" class="2-td2-center">产权人类型</td>
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
    共有产权人
</logic:equal> <logic:equal name="nsrdata" property="cqrlx" value="0">
    主产权人
</logic:equal></td>
												</tr>
											</logic:iterate>

										</table>
										</TD>
									</TR>


									<tr>
										<TD class=2-td2-left width="8%" rowspan="7">
										<DIV align=right>土地房屋</DIV>
										<DIV align=right>权属转移</DIV>
										</TD>
										<TD class=2-td2-left width="15%">
										<DIV align=right>房地产项目名称&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center width="15%"
											style="word-break:break-all">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="fdcxmmc" /></DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>合约签订时间&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="18%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="hyqdsj" /></DIV>
										</TD>
										<TD class=2-td2-left width="19%">
										<DIV align=right>购房原因&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="33%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="flmc" /></DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left width="25%";>
										<DIV align=right>土地、房屋座落地址&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center width="15%">
										<DIV align=left style="word-break:break-all">&nbsp;<bean:write
											name="jmsbForm" property="tdfwzldz" /></DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>土地、房屋权属转移类型&nbsp;</DIV>
										</TD>
										<!-- TD class=2-td2-left width="18%"-->
										<TD colspan="4" class=2-td2-center width="19%">
										<DIV align=left style="word-break:break-all">&nbsp;<bean:write
											name="jmsbForm" property="tdfwqszylxmc" /></DIV>
										</TD>

										<!-- TD class=2-td2-left width="19%">
										<DIV align=right>房屋类别&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="33%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="fwlbmc" /></DIV>
										</TD-->
									</TR>
									<TR>
										<TD class=2-td2-left width="25%";>
										<DIV align=right>土地、房屋权属转移面积&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="18%">
										<DIV align=left>&nbsp;土地：<bean:write name="jmsbForm"
											property="tdfwqszymj" /> &nbsp;㎡</DIV>
										</TD>
										<TD class=2-td2-left width="17%">
										<DIV align=right>房屋建筑面积&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="32%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="fwjzmj" /> &nbsp;㎡</DIV>
										</TD>
									</TR>


									<%-- %> TR>
										<TD class=2-td2-left width="25%";>
										<DIV align=right>容积率&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="18%">
										<DIV align=left>
										<%if(rjl.equals("00"))
	{
	%> 1.0以下 <%
	}
	if(rjl.equals("01"))
	{
	%> 1.0以上含1.0 <%
	}
	%>&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left width="17%">
										<DIV align=right>土地级次&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="32%">
										<DIV align=left>
										<%if(tdjc.equals("01"))
	{
	%> 一级&nbsp; <%
	}
	if(tdjc.equals("02"))
	{
	%> 二级&nbsp; <%
	}
	%> <%if(tdjc.equals("03"))
	{
	%> 三级&nbsp; <%
	}
	if(tdjc.equals("04"))
	{
	%> 四级&nbsp; <%
	}
	%> <%if(tdjc.equals("05"))
	{
	%> 五级&nbsp; <%
	}
	if(tdjc.equals("06"))
	{
	%> 六级&nbsp; <%
	}
	%> <%if(tdjc.equals("07"))
	{
	%> 七级&nbsp; <%
	}
	if(tdjc.equals("08"))
	{
	%> 八级&nbsp; <%
	}
	%> <%if(tdjc.equals("09"))
	{
	%> 九级&nbsp; <%
	}
	if(tdjc.equals("10"))
	{
	%> 十级&nbsp; <%
	}
	%> <%if(tdjc.equals("11"))
	{
	%> 六至十级&nbsp; <%
	}
	%> &nbsp;</DIV>
										</TD>
									</TR><% --%>


									<TR>
										<TD class=2-td2-left width="8%" rowspan="2">
										<DIV align=right>成交价格</DIV>
										<TD class=2-td2-left width="5%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="cjjgyrmb" /> &nbsp; <br>
										&nbsp;元(人民币)</DIV>
										</TD>
										<TD class=2-td2-left width="8%">
										<DIV align=right>税务机关核定价格&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center width="32%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="pgjg" /> &nbsp; <br>
										&nbsp;元(人民币)</DIV>
										</TD>
									</TR>

									<TR>
										<TD class=2-td2-left width="15%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="cjjgywb" /> &nbsp; <br>
										&nbsp;元(外币)</DIV>
										</TD>
										<TD class=2-td2-left width="15%">
										<DIV align=left>币种： &nbsp;<bean:write name="jmsbForm"
											property="bzmc" /> &nbsp;<br>

										<DIV align=left>&nbsp;汇率: &nbsp;<bean:write
											name="jmsbForm" property="hn" />&nbsp;</DIV>
										</TD>

										<TD class=2-td2-center colspan="2" width="25%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="zhyrmb" /> &nbsp; <br>
										&nbsp;折合元(人民币)</DIV>
										</TD>

									</tr>


									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>申请减税、免税理由&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center width="85%">
										<DIV align=left><DIV align=left><%--%><bean:write name="jmsbForm"
											property="qsjmlbmc" /><% --%><%=qsjmlbmc %>&nbsp;</DIV></DIV>
										</TD>
									</TR>

									<% if(qtjmlybeizhu != null && !("".equals(qtjmlybeizhu))){ %>
									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>其它减免理由&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center width="85%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="qtjmlybeizhu" /></DIV>
										</TD>
									</TR>
									<% }%>

									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>备注&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center width="84%">
										<DIV align=left>&nbsp;<bean:write name="jmsbForm"
											property="beizhu" /> &nbsp;</DIV>
										</TD>
									</TR>

								</TBODY>
							</TABLE>
							<BR>

							<DIV align=center><IMG alt=打印 height=22 id=baocun
								name=Submit63 onclick="checkSubmit('Print')"
								onmouseout=MM_swapImgRestore()
								onmouseover="MM_swapImage('dayin','','<%=static_file%>images/dayin2.jpg',1)"
								src="<%=static_file%>images/dayin1.jpg" style="CURSOR: hand"
								width=79> <IMG alt=退出 height=22 id=tuichu name=tuichu
								onclick="checkSubmit('Return');" onmouseout=MM_swapImgRestore()
								onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
								src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand"
								width=79></DIV>
							<BR>

						</html:form> <%@ include file="../include/Bottom.jsp"%>
</BODY>
</HTML>
