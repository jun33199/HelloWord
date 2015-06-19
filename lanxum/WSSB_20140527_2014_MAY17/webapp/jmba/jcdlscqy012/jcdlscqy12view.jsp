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
<title>Ͷ�ʶ��80��Ԫ����һ򼯳ɵ�·�߿�С��0.25��m�ļ��ɵ�·������ҵ����˰��������</title>
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

//�����������
function doChengNd(){
    var pageHlnd=document.getElementById("hlnd").value;
    if(pageHlnd.length<=0)
    {
    	alert("������Ȳ���Ϊ��!");
    	return false;
    }else{
    if(isNaN(pageHlnd)){
    	alert("�������Ӧ��Ϊ��λ�����꣡");
    	window.setTimeout("document.getElementById('hlnd').focus();", 50)  ;
   		return false;
    }else{
    	if(pageHlnd>2009||pageHlnd<(2009-5)){
    	alert("������ȴ��ڻ�С�ڷ�Χ��");
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
	//��������
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//��˰����Ϣ
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);

	applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc","ztdm","ztmc","szdm","szmc","lrrq"]);
	//�걨����
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

//�����걨����
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
	alert("�Ѿ��������Ż����ߣ������ٴα�����");
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
	alert("�Ѿ��������Ż����ߣ������ٴα�����");
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


//��֤���
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
			alert("������Ȳ���Ϊ��!");
			document.forms[0].HLND.focus();
			return false;
		}
		if(!validateYear(hlnd))
		{
			alert("������ȸ�ʽ����ȷ!");
			document.forms[0].hlnd.focus();
			return false;
		}
		
		var band = document.getElementById("BAND").value;
		if(hlnd>band){
			alert("������Ȳ��ܴ��ڱ�����ȣ�");
			document.forms[0].hlnd.focus();
			return false;
		}
		
		var mzqsnd = document.all("MZQSND").value;
		if(mzqsnd == "" ){
			alert("���������ʼʱ�䲻��Ϊ��!");
			document.forms[0].MZQSND.focus();
			return false;
		}
		
		if(!validateYear(mzqsnd))
		{
			alert("���������ʼʱ���ʽ����ȷ!");
			document.forms[0].MZQSND.focus();
			return false;
		}
		
		var mzzznd = document.all("MZZZND").value;
		if(mzzznd == "" ){
			alert("������Ƚ�ֹʱ�䲻��Ϊ��!");
			document.forms[0].MZZZND.focus();
			return false;
		}
		if(mzzznd<mzqsnd)
		{
			alert("������Ƚ�ֹʱ�䲻��С�����������ʼʱ��!");
			document.forms[0].MZZZND.focus();
			return false;
		}
		
		if(!validateYear(mzzznd))
		{
			alert("������Ƚ�ֹʱ���ʽ����ȷ!");
			document.forms[0].MZZZND.focus();
			return false;
		}
		
		var jzqsnd = document.all("JZQSND").value;
		if(jzqsnd == "" ){
			alert("�������������ʼʱ�䲻��Ϊ��!");
			document.forms[0].JZQSND.focus();
			return false;
		}
		if(jzqsnd<mzzznd)
		{
			alert("�������������ʼʱ�䲻��С��������Ƚ�ֹʱ��!");
			document.forms[0].JZQSND.focus();
			return false;
		}
		if(!validateYear(jzqsnd))
		{
			alert("�������������ʼʱ���ʽ����ȷ!");
			document.forms[0].JZQSND.focus();
			return false;
		}
		
		var jzzznd = document.all("JZZZND").value;
		if(jzzznd == "" ){
			alert("����������Ƚ�ֹʱ�䲻��Ϊ��!");
			document.forms[0].JZZZND.focus();
			return false;
		}
		if(jzzznd<jzqsnd)
		{
			alert("����������Ƚ�ֹʱ�䲻��С�ڼ������������ʼʱ��!");
			document.forms[0].JZZZND.focus();
			return false;
		}
		if(!validateYear(jzzznd))
		{
			alert("����������Ƚ�ֹʱ���ʽ����ȷ!");
			document.forms[0].JZZZND.focus();
			return false;
		}
    
	    var yjjmse = document.all("YJJMSE").value;
		if(yjjmse == "" ){
			alert("����Ԥ�Ƶļ���˰���Ϊ��!");
			document.forms[0].YJJMSE.focus();
			return false;
		}
		if(yjjmse<=0)
		{
			alert("����Ԥ�Ƶļ���˰��������0!");
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
			<jsp:param name="name" value="¼����˰����ҵ����˰����˰��������" />
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
						<td class="1-td1">Ͷ�ʶ��80��Ԫ����һ򼯳ɵ�·�߿�С��0.25��m�ļ��ɵ�·������ҵ����˰��������</td>
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
									alt="�鿴������" onclick="doViewZb()" style="cursor:hand" width="95"
									height="22"></td>
								<td></td>
								<td><img src="<%=static_contextpath%>images/fanhui1.jpg"
									onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'"
									onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'"
									alt="����" onclick="doReturn()" style="cursor:hand"></td>
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
