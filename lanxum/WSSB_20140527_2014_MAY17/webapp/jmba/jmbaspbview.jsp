<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml"%>
<%@page import="java.util.List"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.JMBASXDM"%>

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
%>

<html>
<head>
<title>减免税备案表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript"
	src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/date.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js1/WdatePicker.js"></script>

<link href="css/jmba.css" rel="stylesheet"
	type="text/css">

<style>
input {
    font-size: 9pt;
    text-align: right;
}



</style>
<script language="JavaScript">



var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';


function parseXmlOnLoad()
{
	var xslPath='/XSLTWEB/model/030000/XSLT/jmbaspbview.xsl';

	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    
    return true;
}

function doExit()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
} 
 
 
function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="ReturnView";
	document.forms[0].submit();
}
function doViewDetail(value){
	
	
		document.forms[0].actionType.value="ViewDetail"+value;
		//document.forms[0].basqwsh.value=basqwshPara;
//	document.forms[0].basxdm.value=basxdmPara;
	document.forms[0].submit();
		
} 
</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" colspan="2" valign="top"><jsp:include
			page="../../../include/SbHeader.jsp" flush="true">
			<jsp:param name="name" value="减免税备案表" />
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
		</jsp:include> <html:errors />

		<form name="form1" method="POST" action="/shenbao/jmbaz.dc">
		<input	name="actionType" type="hidden" id="actionType"> 


		<TABLE width="900" border='0' cellpadding='0' cellspacing='0'
			align='center' class='black9'>
			<tr>
				<td valign="top">
				<table align="center" cellspacing="0" class="table-99">
					<tr>
						<td class="1-td1">减免税备案表</td>
					</tr>
					<tr>
						<td class="1-td2"><br>
						<div id="result"></div>
						</td>
					</tr>
					<tr>
						<br>
						<table width="100%" border="0" align="center">
							<tr align="center">

							
								<td width="9%">
								
								<img src="<%=static_contextpath%>images/dayin1.jpg" onmouseover="this.src='<%=static_contextpath%>images/dayin2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/dayin1.jpg'" alt="打印" onclick="doViewDetail('1')" style="cursor:hand">
								</td>
								<td width="9%"><img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturn()" style="cursor:hand">
</td>
								<td width="9%"><img src="<%=static_contextpath%>images/tuichu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tuichu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tuichu1.jpg'" alt="退出" onclick="doExit();" style="cursor:hand">
</td>
							</tr>
						</table>
						<br>
					</tr>
				</table>
			</tr>
		</table>
		</td>
	</tr>
</table>
<tr>
	<td valign="bottom" align="center"><br>
	<jsp:include page="../../../include/bottom.jsp" flush="true">
	</jsp:include></td>
</tr>
</body>
</html>
