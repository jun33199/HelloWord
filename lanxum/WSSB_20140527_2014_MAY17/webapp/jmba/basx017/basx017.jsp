<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml"%>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeTable"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.YFFYLY"%>

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
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
<script language="JavaScript" src="js/calculate.js">
    </script>

<link href="css/jmba.css" rel="stylesheet" type="text/css">

<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
<script language="JavaScript">

<%
        String containerName = "";

        com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData)session.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
        if (userdata.getCaflag())
        {
	        containerName = userdata.getCert().getContainerName();
        }
        else
        {
	        containerName = "";
        }
%>
g_objSI.Container = "<%=containerName%>";
var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';


function parseXmlOnLoad()
{
	var xslPath='/XSLTWEB/model/030017/XSLT/basx017.xsl';

	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }

    return true;
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
    var min = 0000;

    var n = new Number(year);
    if ((n.valueOf() > max) || (n.valueOf() < min)) {
        return false;
    }
    return true;

}
	<%/*效验页面元素*/%>
	function checkYm(){
		var zrsrje = document.all("zrsrje").value;
		if(zrsrje == "" ){
			alert("温室气体减排量转让收入金额不能为空!");
			document.forms[0].zrsrje.focus();
			return false;
		}
		var sjje1 = document.all("sjje1").value;
		if(sjje1 == "" ){
			alert("上缴给国家的HFC和PFC类CDM项目的金额不能为空!");
			document.forms[0].sjje1.focus();
			return false;
		}
		var sjje2 = document.all("sjje2").value;
		if(sjje2 == "" ){
			alert("上缴给国家的N2O类CDM项目的金额不能为空!");
			document.forms[0].sjje2.focus();
			return false;
		}
		var hlnd = document.all("hlnd").value;
		if(hlnd == "" ){
			alert("取得第一笔减排量转让收入所属纳税年度不能为空!");
			document.forms[0].hlnd.focus();
			return false;
		}
		if(!validateYear(hlnd))
		{
			alert("取得第一笔减排量转让收入所属纳税年度格式不正确!");
			document.forms[0].hlnd.focus();
			return false;
		}

		var band = document.getElementById("band").value;
		if(hlnd>band){
			alert("取得第一笔减排量转让收入所属纳税年度不能大于备案年度！");
			document.forms[0].hlnd.focus();
			return false;
		}

if(Trim(document.getElementById("mzqsnd").value).length>0||Trim(document.getElementById("mzzznd").value).length>0||Trim(document.getElementById("jzqsnd").value).length>0||Trim(document.getElementById("jzzznd").value).length>0){


    var mzqsnd = document.all("mzqsnd").value;
    if(mzqsnd == "" ){
        alert("免征年度起始时间不能为空!");
        document.forms[0].mzqsnd.focus();
        return false;
    }
    if(mzqsnd<hlnd){
        alert("免征年度起始时间不能小于取得第一笔减排量转让收入所属纳税年度!");
        document.forms[0].mzqsnd.focus();
        return false;
    }
    if(!validateYear(mzqsnd))
    {
        alert("免征年度起始时间格式不正确!");
        document.forms[0].mzqsnd.focus();
        return false;
    }

    var mzzznd = document.all("mzzznd").value;
    if(mzzznd == "" ){
        alert("免征年度截止时间不能为空!");
        document.forms[0].mzzznd.focus();
        return false;
    }
    if(mzzznd<mzqsnd)
    {
        alert("免征年度截止时间不能小于免征年度起始时间!");
        document.forms[0].mzzznd.focus();
        return false;
    }

    if(!validateYear(mzzznd))
    {
        alert("免征年度截止时间格式不正确!");
        document.forms[0].mzzznd.focus();
        return false;
    }

    var jzqsnd = document.all("jzqsnd").value;
    if(jzqsnd == "" ){
        alert("减半征收年度起始时间不能为空!");
        document.forms[0].jzqsnd.focus();
        return false;
    }
    if(jzqsnd<mzzznd)
    {
        alert("减半征收年度起始时间不能小于免征年度截止时间!");
        document.forms[0].jzqsnd.focus();
        return false;
    }
    if(!validateYear(jzqsnd))
    {
        alert("减半征收年度起始时间格式不正确!");
        document.forms[0].jzqsnd.focus();
        return false;
    }

    var jzzznd = document.all("jzzznd").value;
    if(jzzznd == "" ){
        alert("减半征收年度截止时间不能为空!");
        document.forms[0].jzzznd.focus();
        return false;
    }
    if(jzzznd<jzqsnd)
    {
        alert("减半征收年度截止时间不能小于减半征收年度起始时间!");
        document.forms[0].jzzznd.focus();
        return false;
    }
    if(!validateYear(jzzznd))
    {
        alert("减半征收年度截止时间格式不正确!");
        document.forms[0].jzzznd.focus();
        return false;
    }
}

		return true;
	}

    function Trim(str) {
return str.replace(/(^\s*)|(\s*$)/g, "");
}

function getRadioChildren(temp,strTag)
{
    var obj=document.getElementsByName(strTag);
	var objTemp=temp;
	var node=g_Doc.XMLDoc.createElement(strTag);
	objTemp.appendChild(node);
	var strValue=formString(getRadioValue(obj));
	var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
	node.appendChild(objCDATA);

	var mcobj=document.all(strTag+"mc");
	if (strValue=="0"){
	  mcobj.value="是";
	}else{
    	mcobj.value="否";
	}

	return strValue;
}

//生成申报数据
function getSbsj(objForm)
{
    var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
	var objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
	objNode.appendChild(objTemp);
	getChildren(objTemp,"xh");
	getChildren(objTemp,"zrsrje");
	getChildren(objTemp,"sjje1");
	getChildren(objTemp,"sjje2");
	getChildren(objTemp,"hlnd");
	getChildren(objTemp,"mzqsnd");
	getChildren(objTemp,"mzzznd");
	getChildren(objTemp,"jzqsnd");
	getChildren(objTemp,"jzzznd");
}
// 将字符串格式化为货币格式
// par为0，自动填充0.00
// par为1，不自动填充
function formatKsslJsje(obj)
{
	//alert("kssljsje");
	return (checkvalue(obj,0)&&formatCurrency(obj,0));
}
function doSave(){
	if(!checkYm() ){
			return false;
   		}
	if(!confirm("是否保存录入数据?"))
	{
		return false;
	}
	var old  = document.forms[0].ywczlx.value;

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
}

function doEditZb(){

	if(!checkYm() ){
		return false;
   }
	if(!confirm("是否生成备案表?"))
	{
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

//减免年度联动
function doChengNd(){
    var pageHlnd=document.getElementById("hlnd").value;
    if(isNaN(pageHlnd)){
    alert("获利年度应该为四位数字年！");
    window.setTimeout("document.getElementById('hlnd').focus();", 50)  ;
    return;
    }else{
    if(pageHlnd>2009||pageHlnd<(2009-5)){
    alert("获利年度大于范围！");
    window.setTimeout("document.getElementById('hlnd').focus();", 50)  ;
    return;
    }
    }
    var pageHlnd=document.getElementById("hlnd").value;
    if(pageHlnd.length != 4){
        alert("请输入4位年度数字!");
        document.getElementById("HLND").focus();
        return;
    }else{
        document.getElementById("mzqsnd").value=parseInt(pageHlnd);
        document.getElementById("mzzznd").value=parseInt(pageHlnd)+3;
        document.getElementById("jzqsnd").value=parseInt(pageHlnd)+4;
        document.getElementById("jzzznd").value=parseInt(pageHlnd)+6;
    }

}

function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
}
function doReset(){
document.forms[0].jjkcje.value="";
document.forms[0].zfgz.value="";

}

 function getRadioValue(obj)
{
		var objLength = obj.length;
		var k=0;
		for (i=0;i<objLength;i++)
		{
			if(obj[i].checked == true)
			{
				k=i;//寻找radio数组中被选中的值的位置
				return obj[k].value;//给对象赋值
			}
		}
		return "";

}

</script>


<title>录入清洁发展机制项目所得减免备案事项</title>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="parseXmlOnLoad()">

<table width="100%" height="100%" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" colspan=2 valign="top"><jsp:include
			page="../../include/SbHeader.jsp" flush="true">
			<jsp:param name="name" value="录入纳税人企业所得税减免税备案申请" />
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
		</jsp:include> <html:errors />


		<form name="Form1" method="post" action="/shenbao/basx017.dc">
		<input name="actionType" type="hidden" id="actionType"></input>
		<table width="770" border="0" cellpadding="0" cellspacing="0"
			align="center">
			<input name="tbblx" type="hidden" id="tbblx">
			<tr>
				<td valign="top" class="title"><br>
				<table width="75%" cellspacing=0 border=0 class="table-99"
					style="TABLE-LAYOUT:fixed">
					<tr>
						<td class="1-td1">录入清洁发展机制项目所得减免备案事项</td>
					</tr>
					<tr>
						<td class="1-td2"><br>
						<div id="result"></div>
						<br>
						<table width="100%" border="0" align="center">
							<tr align="center">


								<td><img src="<%=static_contextpath%>images/baocun1.jpg"
									onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'"
									onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'"
									alt="保存" onclick="return doSave()" style="cursor:hand"></td>
								<td><img src="<%=static_contextpath%>images/b_scbab1.jpg"
									onmouseover="this.src='<%=static_contextpath%>images/b_scbab2.jpg'"
									onmouseout="this.src='<%=static_contextpath%>images/b_scbab1.jpg'"
									alt="生成备案表" onclick="doEditZb()" style="cursor:hand" width="95"
									height="22"></td>


								<td><img src="<%=static_contextpath%>images/fanhui1.jpg"
									onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'"
									onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'"
									alt="返回" onclick="doReturn()" style="cursor:hand"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>

				</td>
			</tr>
		</table>
		</form>
	<tr>
		<td valign="bottom" align="center"><br>
		<jsp:include page="../../include/bottom.jsp" flush="true">
		</jsp:include></td>
	</tr>
</body>

</html>
