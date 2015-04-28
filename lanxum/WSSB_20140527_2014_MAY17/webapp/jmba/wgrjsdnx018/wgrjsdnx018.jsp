<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
%>
<html>
<head>
<title>外购软件缩短折旧或摊销年限备案事项</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript"
	src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/XmlBuild.js"></script>

<link href="<%=static_contextpath%>css/text.css" rel="stylesheet"
	type="text/css">

<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
<script language="JavaScript">

<%

		String containerName = "";
			com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData) session
		.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
		if (userdata.getCaflag()) {
			containerName = userdata.getCert().getContainerName();
		}else {
		containerName = "";
		}
%>
		g_objSI.Container = "<%=containerName%>";


var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var kkczjeHje = '<%=request.getSession().getAttribute("kkczjeHje")==null?"0":request.getSession().getAttribute("kkczjeHje")%>';
var zcyzHje = '<%=request.getSession().getAttribute("zcyzHje")==null?"0":request.getSession().getAttribute("zcyzHje")%>';
var yjtzjeHje = '<%=request.getSession().getAttribute("yjtzjeHje")==null?"0":request.getSession().getAttribute("yjtzjeHje")%>';
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX18")==null?"":request.getSession().getAttribute("XSLLX18")%>';

function parseXmlOnLoad()
{
	if(tempxsllx=="VIEW")
	{
		var xslPath='/shenbao/jmba/wgrjsdnx018/wgrjsdnx018View.xsl';
		document.getElementById("an1").style.display="none";
		document.getElementById("an2").style.display="block";
	}else
	{
		var xslPath='/shenbao/jmba/wgrjsdnx018/wgrjsdnx018.xsl';
		document.getElementById("an1").style.display="block";
		document.getElementById("an2").style.display="none";
	}
	

	if (strXml != "")
    {   
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    jshje();
    return true;
}

function jshje(){
	document.getElementById("kkczjeHje").innerHTML=kkczjeHje;
	document.getElementById("zcyzHje").innerHTML=zcyzHje;
	document.getElementById("yjtzjeHje").innerHTML=yjtzjeHje;
}

function doAdd(){
    document.forms[0].actionType.value="Add";
    document.forms[0].submit();
}

function doCommit(){
	if(confirm("是否提交全部备案事项?")){
		document.forms[0].actionType.value="Commit";
    	document.forms[0].submit();
	}
}

function doDel(selIndex){
	if(confirm("是否将此记录删除?")){
		document.forms[0].selIndex.value=selIndex;
    	document.forms[0].actionType.value="Del";
    	document.forms[0].submit();
	}else {
		return false;
	} 
}

function doEditor(selIndex){
    document.forms[0].selIndex.value=selIndex;
    document.forms[0].actionType.value="Editor";
    document.forms[0].submit();
}

function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Edit";
	document.forms[0].submit();
} 

function doReturnView()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="View";
	document.forms[0].submit();
}


</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" colspan=2 valign="top"><jsp:include
			page="../../../include/SbHeader.jsp" flush="true">
			<jsp:param name="name" value="外购软件缩短折旧或摊销年限备案事项" />
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
		</jsp:include> <html:errors />

		<form name="form1" method="POST" action="/shenbao/wgrjsdnx18.dc">
		<input name="actionType" type="hidden" id="actionType"> <input
			name="selIndex" type="hidden" id="selIndex">

		<TABLE width="770" border='0' cellpadding='0' cellspacing='0'
			align='center' class='black9'>
			<tr>
				<td valign="top">
				<table align="center" cellspacing="0" class="table-99">
					<tr>
						<td class="1-td1">外购软件缩短折旧或摊销年限备案事项</td>
					</tr>
					<tr>
						<td class="1-td2"><br>
						<div id="result"></div>
						</td>
					</tr>
					<tr>

						<table width="100%" border="0" align="center" id="an1">
							<tr align="center">
								<td><img style="cursor:hand"
									onclick="javascript:return doAdd();"
									onMouseOver="MM_swapImage('zj1','','<%=static_contextpath%>images/tianjia2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="增加" id="zj1"
									src="<%=static_contextpath%>images/tianjia1.jpg" width="79"
									height="22" alt="增加"></td>

								<td><img style="cursor:hand"
									onclick="javascript:return doCommit();"
									onMouseOver="MM_swapImage('tj1','','<%=static_contextpath%>images/tijiao2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="提交" id="tj1"
									src="<%=static_contextpath%>images/tijiao1.jpg" width="79"
									height="22" alt="提交"></td>

								<td><img style="cursor:hand" onclick="doReturn()"
									onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fanhui2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="返回" id="fh1"
									src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
									height="22" alt="返回"></td>
							</tr>
						</table>
						<table width="100%" border="0" align="center" id="an2">
							<tr align="center">
								<td><img style="cursor:hand" onclick="doReturnView()"
									onMouseOver="MM_swapImage('fh2','','<%=static_contextpath%>images/fanhui2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="返回" id="fh2"
									src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
									height="22" alt="返回"></td>
							</tr>
						</table>
						<br>
						<table width="99%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
								<hr width="100%" size="1" style="color='#003399'">
								</td>
								<td width="99" align="center" class="black9"><strong><font
									color="#0000FF">注 意 事 项</font></strong></td>
								<td>
								<hr width="100%" size="1" style="color='#003399'">
								</td>
							</tr>
						</table>
						<table width="99%" border="1" align="center" cellpadding="1"
							cellspacing="1" bordercolor="#FFFFFF" class="black9">
							<tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
								<td height="47"><br>
								&nbsp;&nbsp;1企业购进软件的凭证名称：（必填项）填写固定资产名称 <br>
								&nbsp;&nbsp;2是否符合固定资产或无形资产确认条件：（必填项），填是或否。 <br>
								&nbsp;&nbsp;3是否有外购软件缩短折旧或摊销的年限的理由：（必填项），填是或否。 <br>
								&nbsp;&nbsp;4是否有外购软件缩短折旧或摊销的年限的证明资料：（必填项），填是或否。 <br>
								&nbsp;&nbsp;5是否有相关情况的说明：（必填项），填是或否。 <br>
								&nbsp;&nbsp;6外购软件缩短折旧或摊销的年限（必填项）：填写该软件按照税收规定缩短折旧或摊销的年限，单位为年。 <br>
								&nbsp;&nbsp;7计提折旧起止年度（必填项） <br>
								&nbsp;&nbsp;8每年可扣除的折旧额:填写数值,保留两位小数,单位为元。（必填项） <br>
								&nbsp;&nbsp;9固定资产（无形资产）原值:（必填项）填写按照国家统一会计制度计算提取折旧、摊销的资产原值（或历史成本）的金额。
								<br>
								&nbsp;&nbsp;10已计提折旧的年限:（必填项） <br>
								&nbsp;&nbsp;11已计提的折旧额：按税法规定已计提的折旧额。 <br>
								<br>
								&nbsp;&nbsp;3．1表中选择是否的条件中，有任何一条选择否，则不允许纳税人向税务机关提交。 <br>
								&nbsp;&nbsp;3．2此减免税备案表与年度申报表无勾稽关系。</td>
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
