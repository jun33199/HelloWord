<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
	String ymlx = (String) request.getSession().getAttribute("XSLLX12");

	String containerName = "";
	com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData) session
			.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
	if (userdata.getCaflag()) {
		containerName = userdata.getCert().getContainerName();
	} else {
		containerName = "";
	}
%>

<!--
g_objSI.Container = "<%=containerName%>";
-->
<html>
<head>
<title>投资额超过80亿元人民币或集成电路线宽小于0.25μm的集成电路生产企业减免税备案事项</title>
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

var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';

var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX12")==null?"":request.getSession().getAttribute("XSLLX12")%>';
var tempczlx12 = '<%=request.getSession().getAttribute("CZLX12")==null?"":request.getSession().getAttribute("CZLX12")%>';
function parseXmlOnLoad()
{
	 xslPath='/XSLTWEB/model/030012/XSLT/jcdlscqy12view.xsl';
	if (strXml != "")
    {
        //alert(strXml);
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    return true;
}

//减免年度联动
function doChengNd(){
    var pageHlnd=document.getElementById("hlnd").value;
    if(pageHlnd.length<=0)
    {
    	alert("获利年度不能为空!");
    	return false;
    }else{
    if(isNaN(pageHlnd)){
    	alert("获利年度应该为四位数字年！");
    	window.setTimeout("document.getElementById('hlnd').focus();", 50)  ;
   		return false;
    }else{
    	if(pageHlnd>2009||pageHlnd<(2009-5)){
    	alert("获利年度大于或小于范围！");
    	window.setTimeout("document.getElementById('hlnd').focus();", 50)  ;
   		return false;
    }else{
     document.getElementById("mzqsnd").value=parseInt(pageHlnd);
        document.getElementById("mzzznd").value=parseInt(pageHlnd)+5;
        document.getElementById("jzqsnd").value=parseInt(pageHlnd)+6;
        document.getElementById("jzzznd").value=parseInt(pageHlnd)+10;
        return true;
    }
    }
    }
}


function getPostXml(objForm)
{
	var retstr;
	//基本数据
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//纳税人信息
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);

	applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc","ztdm","ztmc","szdm","szmc","lrrq"]);
	//申报数据
	getSbsj(objForm);

	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	return retstr;
}

function getChildren(temp,strTag)
{
	//alert("strTag = " + strTag);
	var element=document.getElementById(strTag);
	var objTemp=temp;
	var node=g_Doc.XMLDoc.createElement(strTag);
	objTemp.appendChild(node);
	var strValue="";
	if(element!=null)
	{

		strValue=formString(element.value);

		var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
		node.appendChild(objCDATA);
	}
}

//生成申报数据
function getSbsj(objForm)
{
	var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
	var objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
	objNode.appendChild(objTemp);
	getChildren(objTemp,"XH");
	getChildren(objTemp,"BASQWSH");
	getChildren(objTemp,"JSJDM");
	getChildren(objTemp,"BAND");
	getChildren(objTemp,"SWJGZZJGDM");
	getChildren(objTemp,"SFSYJCDLQY");
	getChildren(objTemp,"HLND");
	getChildren(objTemp,"QTZL");
	getChildren(objTemp,"MZQSND");
	getChildren(objTemp,"MZZZND");
	getChildren(objTemp,"JZQSND");
	getChildren(objTemp,"JZZZND");
	getChildren(objTemp,"YJJMSE");
	getChildren(objTemp,"CJR");
	getChildren(objTemp,"CJRQ");
	getChildren(objTemp,"LRR");
    getChildren(objTemp,"LRRQ");



}

function formatKsslJsje(obj)
{
	//alert("kssljsje");
	return (checkvalue(obj,0)&&formatCurrency(obj,0));
}

function dofanhui(){
    document.forms[0].actionType.value="Return";
	document.forms[0].submit();
}

function doSave(){

	if(!checkYm())
	{
		return false;
	}
	if(tempczlx12=='3'||tempczlx12=='4'){
	alert("已经享受完优惠政策，不能再次备案！");
	return false;
	}

	var old  = document.forms[0].ywczlx.value;
	//alert(old);
	if (confirm(confirmSave))
	{   //alert("1");
		 if (document.forms[0].ywczlx.value == 0)
		{  //alert("2");
			document.forms[0].ywczlx.value = 1;
		}
		else if (document.forms[0].ywczlx.value == 1)
		{  //alert("3");
			document.forms[0].ywczlx.value = 2;
		}
		document.forms[0].actionType.value="Save";
			if (g_objSI.Container != '')
			{  //alert("4");
				if (!doSubmitXML(document.forms[0],"Save",true,"",true))
				{  //alert("5");
					 document.forms[0].ywczlx.value = old;
					return false;
				}
			}
			else
			{  //alert("6");
			   if(!doSubmitXML(document.forms[0],"Save",false,"",true))
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

function doCommit(){
	if(!checkYm())
	{
		return false;
	}
	if(tempczlx12=='3'||tempczlx12=='4'){
	alert("已经享受完优惠政策，不能再次备案！");
	return false;
	}
	var old  = document.forms[0].ywczlx.value;
	if (confirm(confirmSave))
	{
		 if (document.forms[0].ywczlx.value == 0)
		{
			document.forms[0].ywczlx.value = 3;
		}
		else if (document.forms[0].ywczlx.value == 1)
		{
			document.forms[0].ywczlx.value = 2;
		}
		document.forms[0].actionType.value="Save";
			if (g_objSI.Container != '')
			{
				if (!doSubmitXML(document.forms[0],"Save",true,"",true))
				{
					 document.forms[0].ywczlx.value = old;
					return false;
				}
			}
			else
			{
			   if(!doSubmitXML(document.forms[0],"Save",false,"",true))
				{
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


//验证年份
function validateYear(year) {
    if (!year) {
        return false;
    }
    if (year.length != 4) {
        return false;
    }
    if (!(/^[0-9]+$/.test(year))) {
        return false;
    }
 var date = new Date();
    var max = 9999;
    var min = 1900;

    var n = new Number(year);
    if ((n.valueOf() > max) || (n.valueOf() < min)) {
        return false;
    }
    return true;

}
function checkYm()
{
	
   	var hlnd = document.all("HLND").value;
		if(hlnd == "" ){
			alert("获利年度不能为空!");
			document.forms[0].HLND.focus();
			return false;
		}
		if(!validateYear(hlnd))
		{
			alert("获利年度格式不正确!");
			document.forms[0].hlnd.focus();
			return false;
		}
		
		var band = document.getElementById("BAND").value;
		if(hlnd>band){
			alert("获利年度不能大于备案年度！");
			document.forms[0].hlnd.focus();
			return false;
		}
		
		var mzqsnd = document.all("MZQSND").value;
		if(mzqsnd == "" ){
			alert("免征年度起始时间不能为空!");
			document.forms[0].MZQSND.focus();
			return false;
		}
		
		if(!validateYear(mzqsnd))
		{
			alert("免征年度起始时间格式不正确!");
			document.forms[0].MZQSND.focus();
			return false;
		}
		
		var mzzznd = document.all("MZZZND").value;
		if(mzzznd == "" ){
			alert("免征年度截止时间不能为空!");
			document.forms[0].MZZZND.focus();
			return false;
		}
		if(mzzznd<mzqsnd)
		{
			alert("免征年度截止时间不能小于免征年度起始时间!");
			document.forms[0].MZZZND.focus();
			return false;
		}
		
		if(!validateYear(mzzznd))
		{
			alert("免征年度截止时间格式不正确!");
			document.forms[0].MZZZND.focus();
			return false;
		}
		
		var jzqsnd = document.all("JZQSND").value;
		if(jzqsnd == "" ){
			alert("减半征收年度起始时间不能为空!");
			document.forms[0].JZQSND.focus();
			return false;
		}
		if(jzqsnd<mzzznd)
		{
			alert("减半征收年度起始时间不能小于免征年度截止时间!");
			document.forms[0].JZQSND.focus();
			return false;
		}
		if(!validateYear(jzqsnd))
		{
			alert("减半征收年度起始时间格式不正确!");
			document.forms[0].JZQSND.focus();
			return false;
		}
		
		var jzzznd = document.all("JZZZND").value;
		if(jzzznd == "" ){
			alert("减半征收年度截止时间不能为空!");
			document.forms[0].JZZZND.focus();
			return false;
		}
		if(jzzznd<jzqsnd)
		{
			alert("减半征收年度截止时间不能小于减半征收年度起始时间!");
			document.forms[0].JZZZND.focus();
			return false;
		}
		if(!validateYear(jzzznd))
		{
			alert("减半征收年度截止时间格式不正确!");
			document.forms[0].JZZZND.focus();
			return false;
		}
    
	    var yjjmse = document.all("YJJMSE").value;
		if(yjjmse == "" ){
			alert("本年预计的减免税额不能为空!");
			document.forms[0].YJJMSE.focus();
			return false;
		}
		if(yjjmse<=0)
		{
			alert("本年预计的减免税额必须大于0!");
			document.forms[0].YJJMSE.focus();
			return false;
		}
    return true;
}

function doEditZb(){
		if(!checkYm() ){
			return false;
   		}

	var old  = document.forms[0].ywczlx.value;

		document.forms[0].actionType.value="EditZb";

		if (g_objSI.Container != '')
		{
			if (!doSubmitXML(document.forms[0],"EditZb",true,"",true))
			{
					document.forms[0].ywczlx.value = old;
				return false;
			}
		}
		else
		{
			if(!doSubmitXML(document.forms[0],"EditZb",false,"",true))
			{
				document.forms[0].ywczlx.value = old;
				return false;
			}
		}
		return true;
}


function doReset(){
document.forms[0].jjkcje.value="";
document.forms[0].zfgz.value="";

}


//function doReturn()
//{
//	document.forms[0].action = "/shenbao/jmbaz.dc";
//if(tempxsllx=='VIEW'){
//	document.forms[0].actionType.value="View";
//	}else{
//	document.forms[0].actionType.value="Edit";
//	}
//	document.forms[0].submit();
//}

function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
}
function doViewZb()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="ViewZb";
	document.forms[0].submit();
}
</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="100%" height="100%" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" colspan=2 valign="top"><jsp:include
			page="../../include/SbHeader.jsp" flush="true">
			<jsp:param name="name" value="录入纳税人企业所得税减免税备案申请" />
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
		</jsp:include> <html:errors />

		<form name="form1" method="POST" action="/shenbao/jcdlscqy12.dc">
		<input name="actionType" type="hidden" id="actionType">

		<table width="90%" border="0" cellpadding="0" cellspacing="0"
			align="center">
			<tr>
				<td valign="top">
				<table align="center" cellspacing="0" class="table-99">
					<tr>
						<td class="1-td1">投资额超过80亿元人民币或集成电路线宽小于0.25μm的集成电路生产企业减免税备案事项</td>
					</tr>
					<tr>
						<td class="1-td2">
						<div id="result"></div>
						<br/>
						<table width="100%" border="0" align="center">
							<tr align="center">
								<td><img src="<%=static_contextpath%>images/b_ckbab1.jpg"
									onmouseover="this.src='<%=static_contextpath%>images/b_ckbab2.jpg'"
									onmouseout="this.src='<%=static_contextpath%>images/b_ckbab1.jpg'"
									alt="查看备案表" onclick="doViewZb()" style="cursor:hand" width="95"
									height="22"></td>
								<td></td>
								<td><img src="<%=static_contextpath%>images/fanhui1.jpg"
									onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'"
									onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'"
									alt="返回" onclick="doReturn()" style="cursor:hand"></td>
							</tr>
						</table>
						</td>

				</td>
			</tr>
		</table>
</table>
<tr>
	<td valign="bottom" align="center"><br>
	<jsp:include page="../../include/bottom.jsp" flush="true">
	</jsp:include></td>
</tr>
</body>
</html>
