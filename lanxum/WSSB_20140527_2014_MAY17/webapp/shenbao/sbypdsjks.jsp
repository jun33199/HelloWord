<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%@page import="com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant"%>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.client.DeclareInfor"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<html>
<head>
<title>北京市地方税务局申报缴款单</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript">
function doReturn()
{
	document.forms[0].actionType.value = "ListJks";
	document.forms[0].submit();
}
</script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table  width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  <td colspan=2 valign="top">
    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="查看缴款书" />
		<jsp:param name="help_url" value="help/wssb/qyzhsb/jksmx/jksmxypds-000.htm"/>
    	</jsp:include>

<html:errors />

<%
        String jksh = request.getParameter("jkshIndex");
        String sbbh = request.getParameter("sbbhIndex");

        HashMap jksMap = (HashMap)request.getSession().getAttribute(SessionKey.JKS);
        HashMap jks = (HashMap)((HashMap)jksMap.get(sbbh)).get(jksh);

        // 一票多税
        List aJks = (List)jks.get(ZhsbMapConstant.SBSJ);

		for (int i=0; i<aJks.size(); i++)
		{
			JspUtil.completeZbInfo(request, ((DeclareInfor)aJks.get(i)).getSbjkzb());
		}

		Sbjkzb aZb = ((DeclareInfor)aJks.get(0)).getSbjkzb();

		String jsjdm = aZb.getJsjdm();
		String sbrq = JspUtil.format(aZb.getSbrq());
		String zsswjgzzjgmc = aZb.getZsswjgzzjgmc();
		String nsrmc = aZb.getNsrmc();
		String yhmc = aZb.getYhmc();
%>
<br>
      <table width="96%" align="center" cellspacing="0" class="table-99">
                <tr>
            <td class="1-td1" colspan="7">中 华 人 民 共 和 国 税 收 电 子 转 帐 专 用 完 税 凭 证 <%=jksh%> 号</td>
                </tr>
                <tr>
                  <td class="1-td2"  colspan="7" valign="top"> <br>
					<table cellpadding="2" border="0" cellspacing="2" width="100%">
                      <tr>
					  	<td width="3%">&nbsp;</td>
						<td width="77%"><div align="left" class="black9">填发日期：<%=sbrq%></div></td>
						<td width="20%">&nbsp;</td>
					  </tr>
					</table>

              <table class="table-99" cellspacing="0">
                <tr>
                  <td width="15%" class="2-td2-t-left"><div align="right"><strong>税务登记代码&nbsp;&nbsp;</strong></div></td>
                  <td width="35%" class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<%=jsjdm%></div></td>
                  <td width="15%" class="2-td2-t-left"><div align="right"><strong>征收机关&nbsp;&nbsp;</strong></div></td>
                  <td width="35%" class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<%=zsswjgzzjgmc%></div></td>
                </tr>
                <tr>
                  <td class="2-td2-left"><div align="right"><strong>纳税人全称&nbsp;&nbsp;</strong></div></td>
                  <td class="2-td2-left"><div align="left">&nbsp;&nbsp;<%=nsrmc%></div></td>
                  <td class="2-td2-left"><div align="right"><strong>收款银行(邮局)&nbsp;&nbsp;</strong></div></td>
                  <td class="2-td2-center"><div align="left">&nbsp;&nbsp;<%=yhmc%></div></td>
                </tr>
              </table>
					<br>
              <br>
<%
for (int i=0; i<aJks.size(); i++)
{
	DeclareInfor declare = (DeclareInfor)aJks.get(i);
	Sbjkzb zb = declare.getSbjkzb();
%>
            <table class="table_99" cellspacing="0" width="98%">
                <tr>
					<td class="2-td1-center" colspan="5"><div align="left">
						税种名称：<%=zb.getSzmc()%>
&nbsp;&nbsp;&nbsp;&nbsp;税款所属起始日期：<%=JspUtil.format(zb.getSkssksrq())%>至<%=JspUtil.format(zb.getSkssjsrq())%>
&nbsp;&nbsp;&nbsp;&nbsp;实际缴税额：<%=JspUtil.format(zb.getSjje())%></div>
					</td>
                </tr>
						<tr>
							<td width="15%" height="28" nowrap class="2-td1-left">税目代码</td>
							<td width="30%" height="28" nowrap class="2-td1-left">税目名称</td>
							<td width="14%" height="28" nowrap class="2-td1-left">课税数量</td>
							<td width="21%" height="28" nowrap class="2-td1-left">计税金额</td>
							<td width="20%" height="28" nowrap class="2-td1-center">实缴税额</td>
						</tr>
<%
	List mxList = declare.getSbjkmxInfo();
	for (int j=0; j<mxList.size(); j++)
	{
		Sbjkmx mx = (Sbjkmx)mxList.get(j);
		JspUtil.completeMxInfo(request, mx);
%>
						<tr>
						  <td nowrap class="2-td2-left" height="30"><%=mx.getSzsmdm()%></td>
		                  <td nowrap class="2-td2-left" height="30"><%=mx.getSzsmmc()%></td>
		                  <td nowrap class="2-td2-left" height="30">&nbsp;<%=JspUtil.format(mx.getKssl())%></td>
		                  <td nowrap class="2-td2-left" height="30"><%=JspUtil.format(mx.getJsje())%></b></td>
		                  <td nowrap class="2-td2-center" height="30"><%=JspUtil.format(mx.getSjse())%></td>
						</tr>
<%
	}
%>
			</table>
<br>
<%
}
		java.math.BigDecimal jehj = (java.math.BigDecimal)jks.get(ZhsbMapConstant.JEHJ);
%>

			  <table class="table_99" border="0" width="98%">
                <tr>
                  <td class="2-td2-t-center" width="80%"><div align="right">合计（大写）：<%=com.ttsoft.framework.util.Currency.convert(jehj)%></div></td>
                  <td class="2-td2-t-center" width="20%"><div align="center">合计：<%=JspUtil.format(jehj)%></div></td>
                </tr>
              </table>

<html:form method="POST" action="/listJks.do">
                <html:hidden name="listJksForm" property="actionType"/>

			  <table width="94%" border="0" cellpadding="0" cellspacing="4">
                <tr>
                  <td><div align="center">
					<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturn()" style="cursor:hand">
				  </div></td>
                </tr>
              </table>
</html:form>

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