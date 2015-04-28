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
var temptbblx = '<%=request.getSession().getAttribute("TBBLX")==null?"":request.getSession().getAttribute("TBBLX")%>';
//alert(temptbblx);
function parseXmlOnLoad()
{
	var xslPath='/XSLTWEB/model/030015/XSLT/basx015view.xsl';

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
		document.getElementById("TABLE_LIST1").style.display="block";
		document.getElementById("TABLE_LIST2").style.display="none";
	
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
	<%/*效验页面元素*/%>
	function checkYmSd(){
		//固定资产验证
		var gdzcmc_sd = document.all("gdzcmc_sd").value;
		if(gdzcmc_sd == "" ){
			alert("固定资产名称不能为空!");
			document.forms[0].gdzcmc_sd.focus();
			return false;
		}
		
		var gdzcyz_sd = document.all("gdzcyz_sd").value;
		if(gdzcyz_sd == "" ){
			alert("固定资产原值不能为空!");
			document.forms[0].gdzcyz_sd.focus();
			return false;
		}
		
		var gdzcjsjc_sd = document.all("gdzcjsjc_sd").value;
		if(gdzcjsjc_sd == "" ){
			alert("固定资产计税基础不能为空!");
			document.forms[0].gdzcjsjc_sd.focus();
			return false;
		}
		
		var sfgdzdnx_sd = document.all("sfgdzdnx_sd").value;
		if(sfgdzdnx_sd == "" ){
			alert("税法规定的最低年限不能为空!");
			document.forms[0].sfgdzdnx_sd.focus();
			return false;
		}
		if(isNaN(sfgdzdnx_sd))
		{
			alert("税法规定的最低年限只能为数字!");
			document.forms[0].sfgdzdnx_sd.focus();
			return false;
		}
		
		var jszjzdnx_sd = document.all("jszjzdnx_sd").value;
		if(jszjzdnx_sd == "" ){
			alert("加速折旧的最低年限不能为空!");
			document.forms[0].jszjzdnx_sd.focus();
			return false;
		}
		if(isNaN(jszjzdnx_sd))
		{
			alert("加速折旧的最低年限只能为数字!");
			document.forms[0].jszjzdnx_sd.focus();
			return false;
		}
		
		var zjqsnd_sd = document.all("zjqsnd_sd").value;
		if(zjqsnd_sd == "" ){
			alert("计提折旧年度起不能为空!");
			document.forms[0].zjqsnd_sd.focus();
			return false;
		}
		if(!validateYear(zjqsnd_sd))
		{
			alert("计提折旧年度起格式不正确!需填写在1925至2009之间!");
			document.forms[0].zjqsnd_sd.focus();
			return false;
		}
		
		var zjzznd_sd = document.all("zjzznd_sd").value;
		if(zjzznd_sd == "" ){
			alert("计提折旧年度止不能为空!");
			document.forms[0].zjzznd_sd.focus();
			return false;
		}
		if(!validateYear(zjzznd_sd))
		{
			alert("计提折旧年度止格式不正确!需填写在1925至2009之间!");
			document.forms[0].zjzznd_sd.focus();
			return false;
		}
		
		if(zjqsnd_sd>zjzznd_sd)
		{
			alert("计提折旧年度止时间不能小于计提折旧年度起时间!");
			document.forms[0].zjzznd_sd.focus();
			return false;
		}
		
		var zje_sd = document.all("zje_sd").value;
		if(zje_sd == "" ){
			alert("每年可扣除的折旧额不能为空!");
			document.forms[0].zje_sd.focus();
			return false;
		}
		
		var yjtzjnx_sd = document.all("yjtzjnx_sd").value;
		if(yjtzjnx_sd == "" ){
			alert("已计提折旧的年限不能为空!");
			document.forms[0].yjtzjnx_sd.focus();
			return false;
		}
		if(isNaN(yjtzjnx_sd))
		{
			alert("已计提折旧的年限只能为数字!");
			document.forms[0].yjtzjnx_sd.focus();
			return false;
		}
		
		var yjtzje_sd = document.all("yjtzje_sd").value;
		if(yjtzje_sd == "" ){
			alert("已计提的折旧额不能为空!");
			document.forms[0].yjtzje_sd.focus();
			return false;
		}
		return true;
		
	}
	
//生成申报数据
function getSbsj(objForm)
{   
    var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
	var objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
	objNode.appendChild(objTemp);
	getChildren(objTemp,"xh");
	getChildren(objTemp,"tbblx");
	getChildren(objTemp,"gdzcmc_sd");
	getChildren(objTemp,"gdzcyz_sd");
	getChildren(objTemp,"gdzcjsjc_sd");
	getChildren(objTemp,"sfgdzdnx_sd");
	getChildren(objTemp,"jszjzdnx_sd");
	getChildren(objTemp,"zjqsnd_sd");
	getChildren(objTemp,"zjzznd_sd");
	getChildren(objTemp,"zje_sd");
	getChildren(objTemp,"yjtzjnx_sd");
	getChildren(objTemp,"yjtzje_sd");
	getChildren(objTemp,"gdzcmc_js");
	getChildren(objTemp,"gdzcyz_js");
	getChildren(objTemp,"gdzcjsjc_js");
	getChildren(objTemp,"jszjffdm_js");
	getChildren(objTemp,"zje_js");	
}
// 将字符串格式化为货币格式
// par为0，自动填充0.00
// par为1，不自动填充
function formatKsslJsje(obj)
{
	//alert("kssljsje");
	return (checkvalue(obj,0)&&formatCurrency(obj,0));
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
    var max = date.getFullYear()-1;
    var min = 1925;

    var n = new Number(year);
    if ((n.valueOf() > max) || (n.valueOf() < min)) {
        return false;
    }
    return true;

}
function doSave(){
	if(document.getElementsByName("bm")[0].checked){
		document.getElementById("tbblx").value = 0;
	}
	
	if(document.getElementsByName("bm")[1].checked){
		document.getElementById("tbblx").value = 1;
	}
	
	if(document.getElementById("tbblx").value==0)
	{	
		if(!checkYmSd() ){
			return false;
   		}
	}
	if(document.getElementById("tbblx").value==1)
	{
		//if(!checkYmJs() ){
		//	return false;
   		//}
   		//加速验证
		var gdzcmc_js = document.all("gdzcmc_js").value;
		if(gdzcmc_js == "" ){
			alert("固定资产名称不能为空!");
			document.forms[0].gdzcmc_js.focus();
			return false;
		}
		var gdzcyz_js = document.all("gdzcyz_js").value;
		if(gdzcyz_js == "" ){
			alert("固定资产原值不能为空!");
			document.forms[0].gdzcyz_js.focus();
			return false;
		}
		
		var gdzcjsjc_js = document.all("gdzcjsjc_js").value;
		if(gdzcjsjc_js == "" ){
			alert("固定资产计税基础不能为空!");
			document.forms[0].gdzcjsjc_js.focus();
			return false;
		}
		var zje_js = document.all("zje_js").value;
		if(zje_js == "" ){
			alert("年折旧额不能为空!");
			document.forms[0].zje_js.focus();
			return false;
		}
		return true;
		
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
	
	if(document.getElementsByName("bm")[0].checked){
		document.getElementById("tbblx").value = 0;
	}
	
	if(document.getElementsByName("bm")[1].checked){
		document.getElementById("tbblx").value = 1;
	}
	
	if(document.getElementById("tbblx").value==0)
	{	
		if(!checkYmSd() ){
			return false;
   		}
	}
	if(document.getElementById("tbblx").value==1)
	{
		//if(!checkYmJs() ){
		//	return false;
   		//}
   		//加速验证
		var gdzcmc_js = document.all("gdzcmc_js").value;
		if(gdzcmc_js == "" ){
			alert("固定资产名称不能为空!");
			document.forms[0].gdzcmc_js.focus();
			return false;
		}
		var gdzcyz_js = document.all("gdzcyz_js").value;
		if(gdzcyz_js == "" ){
			alert("固定资产原值不能为空!");
			document.forms[0].gdzcyz_js.focus();
			return false;
		}
		
		var gdzcjsjc_js = document.all("gdzcjsjc_js").value;
		if(gdzcjsjc_js == "" ){
			alert("固定资产计税基础不能为空!");
			document.forms[0].gdzcjsjc_js.focus();
			return false;
		}
		var zje_js = document.all("zje_js").value;
		if(zje_js == "" ){
			alert("年折旧额不能为空!");
			document.forms[0].zje_js.focus();
			return false;
		}
		return true;
		
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


function xzb(){
	
	if(document.getElementsByName("bm")[0].checked){
		//document.getElementById("tbblx").value = 0;
		//alert(document.getElementsByName("bm")[0].value);
		document.getElementById("TABLE_LIST1").style.display="block";
		document.getElementById("TABLE_LIST2").style.display="none";
		//document.getElementsByName("bm")[1].disabled="true"
	}
	
	if(document.getElementsByName("bm")[1].checked){
		//document.getElementById("tbblx").value = 1;
		document.getElementById("TABLE_LIST1").style.display="none";
		document.getElementById("TABLE_LIST2").style.display="block";
		//document.getElementsByName("bm")[0].disabled="true"
	}
}

function doViewZb()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="ViewZb";
	document.forms[0].submit();
}
</script>


<title>固定资产缩短折旧年限或加速折旧备案事项</title>

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


		<form name="Form1" method="post" action="/shenbao/basx015.dc">
		<input name="actionType" type="hidden" id="actionType"></input>
		<table width="770" border="0" cellpadding="0" cellspacing="0"
			align="center">
			<input name="tbblx" type="hidden" id="tbblx">
			<tr>
				<td valign="top" class="title"><br>
				<table width="75%" cellspacing=0 border=0 class="table-99"
					style="TABLE-LAYOUT:fixed">
					<tr>
						<td class="1-td1">固定资产缩短折旧年限或加速折旧备案事项</td>
					</tr>
					<tr>
						<td class="2-td2-t-center">缩短折旧年限表:<input type="radio"
							name="bm" id="bm1" value="1" onclick="xzb()" checked="true">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						加速折旧表（采取双倍余额递减法或年数总额法:<input type="radio" name="bm" id="bm2"
							value="2" onclick="xzb()"></td>
					</tr>
					<tr>
						<td class="1-td2"><br>
						<div id="result"></div>
						<br>
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
