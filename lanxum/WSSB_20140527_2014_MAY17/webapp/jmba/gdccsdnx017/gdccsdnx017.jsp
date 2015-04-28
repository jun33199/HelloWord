<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);

	String containerName = "";
	com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData) session
			.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
	if (userdata.getCaflag()) {
		containerName = userdata.getCert().getContainerName();
	} else {
		containerName = "";
	}
%>
<html>
<head>
<title>固定资产缩短折旧年限或加速折旧备案事项</title>
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

<link href="<%=static_contextpath%>css/text.css" rel="stylesheet"
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
var temptbblx = '<%=request.getSession().getAttribute("TBBLX")==null?"":request.getSession().getAttribute("TBBLX")%>';
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX17")==null?"":request.getSession().getAttribute("XSLLX17")%>';

//alert("temptbblx"+tempxsllx);
function parseXmlOnLoad()
{	
	if(tempxsllx=="VIEW")
	{
		var xslPath='/shenbao/jmba/gdccsdnx017/gdccsdnx017View.xsl';
		document.getElementById("an1").style.display="none";
		document.getElementById("an2").style.display="block";
	}else
	{
		var xslPath='/shenbao/jmba/gdccsdnx017/gdccsdnx017.xsl';
		document.getElementById("an1").style.display="block";
		document.getElementById("an2").style.display="none";
	}
	
	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    xzb1();
    return true;
}


function xzb1(){

	if(temptbblx=="0")
	{
		document.getElementById("TABLE_LIST1").style.display="block";
		document.getElementById("TABLE_LIST2").style.display="none";
		document.getElementsByName("bm")[0].checked="true";
		document.getElementById("bm1").disabled="false";
		document.getElementById("bm2").disabled="true";
	}else if(temptbblx=="1")
	{
		document.getElementById("TABLE_LIST1").style.display="none";
		document.getElementById("TABLE_LIST2").style.display="block";
		document.getElementsByName("bm")[1].checked="true";
		document.getElementById("bm1").disabled="true";
		document.getElementById("bm2").disabled="false";
	}else
	{
		document.getElementById("TABLE_LIST1").style.display="none";
		document.getElementById("TABLE_LIST2").style.display="block";
		document.getElementsByName("bm")[1].checked="true";
	}
}

function addShow()
{        
	if(document.getElementsByName("bm")[0].checked){
		document.forms[0].tbblx.value = 0;
		//alert(document.forms[0].tbblx.value);
		document.forms[0].actionType.value = "Add_Sd_Show";
		document.forms[0].submit();
	}
	if(document.getElementsByName("bm")[1].checked){
		document.forms[0].tbblx.value = 1;
		//alert(document.forms[0].tbblx.value);
		document.forms[0].actionType.value = "Add_Js_Show";
		document.forms[0].submit();
	}
	
}

function xzb(){
	if(document.getElementsByName("bm")[0].checked){
		//alert(document.getElementsByName("bm")[0].value);
		document.getElementById("TABLE_LIST1").style.display="block";
		document.getElementById("TABLE_LIST2").style.display="none";
		//document.getElementsByName("bm")[1].disabled="true"
	}
	if(document.getElementsByName("bm")[1].checked){
		document.getElementById("TABLE_LIST1").style.display="none";
		document.getElementById("TABLE_LIST2").style.display="block";
		//document.getElementsByName("bm")[0].disabled="true"
	}
}

function doCommit(){
	if(confirm("是否提交全部备案事项?")){
		document.forms[0].actionType.value="Commit";
	    document.forms[0].submit();
    }
}

function doDel(selIndex){
	if(confirm("确定删除此条数据?")){
		document.forms[0].selIndex.value=selIndex;
    	document.forms[0].actionType.value="Del";
    	document.forms[0].submit();
	}
    
}

function doEditor(selIndex){
	//alert(selIndex);
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
		<td align="center" colspan="2" valign="top"><jsp:include
			page="../../../include/SbHeader.jsp" flush="true">
			<jsp:param name="name" value="固定资产缩短折旧年限或加速折旧备案事项" />
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
		</jsp:include> <html:errors />

		<form name="form1" method="POST" action="/shenbao/gdccsdnx17.dc">
		<input name="actionType" type="hidden" id="actionType"> <input
			name="selIndex" type="hidden" id="selIndex"> <input
			name="tbblx" type="hidden" id="tbblx">
		<TABLE width="770" border='0' cellpadding='0' cellspacing='0'
			align='center' class='black9'>
			<tr>
				<td valign="top">
				<table align="center" cellspacing="0" class="table-99">
					<tr>
						<td class="1-td1">固定资产缩短折旧年限或加速折旧备案事项</td>
					</tr>
					<tr>
						<td class="1-td2">缩短折旧年限表:<input type="radio" name="bm"
							id="bm1" value="1" onclick="xzb()">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						加速折旧表（采取双倍余额递减法或年数总额法:<input type="radio" name="bm" id="bm2"
							value="2" onclick="xzb()"></td>
					</tr>
					<tr>
						<td class="1-td2"><br>
						<div id="result"></div>
						</td>
					</tr>
					<tr>

						<table width="100%" border="0" align="center" id="an1">
							<tr align="center">
								<td><img style="cursor:hand" onclick="addShow()"
									onMouseOver="MM_swapImage('zj1','','<%=static_contextpath%>images/tianjia2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="增加" id="zj1"
									src="<%=static_contextpath%>images/tianjia1.jpg" width="79"
									height="22" alt="增加"></td>

								<td><img style="cursor:hand" onclick="doCommit()"
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
								&nbsp;&nbsp;1、固定资产名称：（必填项）填写固定资产名称 <br>
								&nbsp;&nbsp;2、是否提交固定资产的功能、预计使用年限短于《实施条例》规定计算折旧的最低年限的理由、证明资料及有关情况的明：必填项，填是或否。
								<br>
								&nbsp;&nbsp;3、是否提交被替代的旧固定资产的功能、使用及处置等情况的说明：必填项，填是或否。 <br>
								&nbsp;&nbsp;4、固定资产原值：（必填项）填写按照国家统一会计制度计算提取折旧、摊销的资产原值（或历史成本）的金额。
								<br>
								&nbsp;&nbsp;5、固定资产计税基础：（必填项）填写按照税收规定计算税前扣除折旧、摊销的金额。 <br>
								&nbsp;&nbsp;6、税法规定的最低年限：（必填项）填写该项固定资产按照税收规定的最低使用年限，单位为年： <br>
								&nbsp;&nbsp;7、加速折旧的最低年限：（必填项）填写该项固定资产按照税收规定的加速折旧的最低年限，单位为年： <br>
								&nbsp;&nbsp;8、计提折旧起止年度：（必填项） <br>
								&nbsp;&nbsp;9、每年可扣除的折旧额：（必填项）填写该项固定资产按照税收规定计算的每年可扣除的折旧额 <br>
								&nbsp;&nbsp;10、已计提折旧的年限：（必填项）据实填写 <br>
								&nbsp;&nbsp;11、已计提的折旧额：（必填项）税法允许扣除的已计提的折旧额 <br>
								&nbsp;&nbsp;2.1.2加速折旧表（采取双倍余额递减法或年数总额法） <br>
								&nbsp;&nbsp;2.1.2.1固定资产名称：（必填项）填写固定资产名称 <br>
								&nbsp;&nbsp;2.1.2.2是否提供固定资产加速折旧拟采用的方法和折旧额的说明：必填项，填是或否。 <br>
								&nbsp;&nbsp;2.1.2.3固定资产原值：（必填项）填写按照国家统一会计制度计算提取折旧、摊销的资产原值（或历史成本）的金额。
								<br>
								&nbsp;&nbsp;2.1.2.4固定资产计税基础：（必填项）填写按照税收规定计算税前扣除折旧、摊销的金额。 <br>
								&nbsp;&nbsp;2.1.2.5计算折旧的方法：（必填项）填写按照税法规定采用的方法（双倍余额递减法或年数总额法） <br>
								&nbsp;&nbsp;2.2.2.6年折旧额：（必填项）填写按照税法规定计算的年折旧额。 <br>
								<br>
								&nbsp;&nbsp;3.1表中选择是否的条件中，有任何一条选择否，则不允许纳税人向税务机关提交。 <br>
								&nbsp;&nbsp;3.2此减免税备案表与年度申报表无勾稽关系。</td>
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
<td valign="bottom" align="center"><br>
<jsp:include page="../../../include/bottom.jsp" flush="true">
</jsp:include></td>
</body>
</html>
