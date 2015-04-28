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
g_objSI.Container = "<%=containerName%>";
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
		var xslPath='/shenbao/jmba/gdccsdnx017/sub_gdccsdnx017.xsl';
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
{	alert("返回");
	return false;
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

function scbab() {
        alert("生成备案表");

      }
      
 function doSave(){
 
 	alert("保存成功");
	return false;
	if(!checkYm() ){
		return false;
   	}
	var old  = document.forms[0].ywczlx.value;
	//alert(old);
	if (confirm(confirmSave))
	{  // alert("1");
		 if (document.forms[0].ywczlx.value == 0)
		{  //alert("2");
			document.forms[0].ywczlx.value = 1;
		}
		else if (document.forms[0].ywczlx.value == 1)
		{  //alert("3");
			document.forms[0].ywczlx.value = 2;
		}
		var commitType = document.forms[0].buss.value;
		document.forms[0].actionType.value=commitType;
			if (g_objSI.Container != '')
			{  //alert("4");
				if (!doSubmitXML(document.forms[0],commitType,true,"",true))
				{  //alert("5");
					 document.forms[0].ywczlx.value = old;
					return false;
				}
			}
			else
			{  //alert("6");
			   if(!doSubmitXML(document.forms[0],commitType,false,"",true))
				{  //alert("7");
					 document.forms[0].ywczlx.value = old;
					return false;
			   }			   
			}
		return true;
	}else
	{
		return false;
	}
}
</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="100%" height="100%" border="0" align="center"
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
		<TABLE width="95%" border='0' cellpadding='0' cellspacing='0'
			align='center' class='black9'>
			<tr>
				<td valign="top">
				<table align="center" cellspacing="0" class="table-99">
					<tr>
						<td class="1-td1">固定资产缩短折旧年限或加速折旧备案事项</td>
					</tr>

					<tr>
						<td class="2-td2-t-center">缩短折旧年限表:<input type="radio"
							name="bm" id="bm1" value="1" onclick="xzb()">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						加速折旧表（采取双倍余额递减法或年数总额法:<input type="radio" name="bm" id="bm2"
							value="2" onclick="xzb()"></td>
					</tr>
					<tr>
						<td class="1-td2"><br>
						<div id="result"></div>
						<table width="100%" border="0" align="center" id="an1">
							<tr align="center">
								<td><img style="cursor:hand"
									onclick="javascript:return doSave();"
									onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/baocun2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="保存" id="bc1"
									src="<%=static_contextpath%>images/baocun1.jpg" width="79"
									height="22" alt="保存"></td>
								<td>
								<button onclick="scbab()">生成备案表</button>

								</td>
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
						</td>
					</tr>
				</table>
			</tr>
		</table>
		</td>
	<tr>
		<td valign="bottom" align="center"><br>
		<jsp:include page="../../../include/bottom.jsp" flush="true">
		</jsp:include></td>
	</tr>
</table>

</body>
</html>
