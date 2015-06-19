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
	var xslPath='/XSLTWEB/model/030016/XSLT/basx016view.xsl';

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
    var max = date.getFullYear()-1;
    var min = 1925;

    var n = new Number(year);
    if ((n.valueOf() > max) || (n.valueOf() < min)) {
        return false;
    }
    return true;

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
	<%/*Ч��ҳ��Ԫ��*/%>
	function checkYm(){
		var gjrjpzmc = document.all("gjrjpzmc").value;
		if(gjrjpzmc == "" ){
			alert("��ҵ���������ƾ֤���Ʋ���Ϊ��!");
			document.forms[0].gjrjpzmc.focus();
			return false;
		}
		
		var sdtxnx = document.all("sdtxnx").value;
		if(sdtxnx == "" ){
			alert("�⹺��������۾ɻ�̯�������޲���Ϊ��!");
			document.forms[0].sdtxnx.focus();
			return false;
		}
		if(isNaN(sdtxnx))
		{
			alert("�⹺��������۾ɻ�̯��������ֻ��Ϊ����!");
			document.forms[0].sdtxnx.focus();
			return false;
		}
		
		var jtzjqsnd = document.all("jtzjqsnd").value;
		if(jtzjqsnd == "" ){
			alert("�����۾��������Ϊ��!");
			document.forms[0].jtzjqsnd.focus();
			return false;
		}
		if(!validateYear(jtzjqsnd))
		{
			alert("�����۾�������ʽ����ȷ!����д��1925��2009֮��!");
			document.forms[0].jtzjqsnd.focus();
			return false;
		}
		
		var jtzjzznd = document.all("jtzjzznd").value;
		if(jtzjzznd == "" ){
			alert("�����۾����ֹ����Ϊ��!");
			document.forms[0].jtzjzznd.focus();
			return false;
		}
		if(!validateYear(jtzjzznd))
		{
			alert("�����۾����ֹ��ʽ����ȷ!����д��1925��2009֮��!");
			document.forms[0].jtzjqsnd.focus();
			return false;
		}
		if(jtzjqsnd>jtzjzznd)
		{
			alert("�����۾����ֹʱ�䲻��С�ڼ����۾������ʱ��!");
			document.forms[0].jtzjqsnd.focus();
			return false;
		}
		
		var kkczje = document.all("kkczje").value;
		if(kkczje == "" ){
			alert("ÿ��ɿ۳����۾ɶ��Ϊ��!");
			document.forms[0].kkczje.focus();
			return false;
		}
		
		var zcyz = document.all("zcyz").value;
		if(zcyz == "" ){
			alert("�̶��ʲ��������ʲ���ԭֵ����Ϊ��!");
			document.forms[0].zcyz.focus();
			return false;
		}
		
		var yjtzjnx = document.all("yjtzjnx").value;
		if(yjtzjnx == "" ){
			alert("�Ѽ����۾ɵ����޲���Ϊ��!");
			document.forms[0].yjtzjnx.focus();
			return false;
		}
		if(isNaN(yjtzjnx))
		{
			alert("�Ѽ����۾ɵ�����ֻ��Ϊ����!");
			document.forms[0].yjtzjnx.focus();
			return false;
		}
		
		var yjtzje = document.all("yjtzje").value;
		if(yjtzje == "" ){
			alert("�Ѽ�����۾ɶ��Ϊ��!");
			document.forms[0].yjtzje.focus();
			return false;
		}
		 return true;
		
	}
	
   function   checkLength(obj,maxLength1)   
  {
  var evalue=obj.value;
  evalue=evalue.replace(/[^\x00-\xff]/g,'**');
//alert(evalue.length+"  "+obj.maxLength);
  var maxLength=maxLength1;
  if (obj.maxLength!=null){
     maxLength=obj.maxLength;
  }
  if(evalue.length>maxLength){
  alert("�������ݲ��ܴ���"+maxLength+"���ַ� ��");
  return   false;
  }  
return true; 
  }  
	
function canCommit(sfFields){
  for(var i= 0;i<sfFields.length;i++)
  {
	var rValue  = getRadioValue(document.getElementsByName(sfFields[i]));
 		  if (rValue=="1"){
	   alert("�������Ƿ�Ϊ���ǡ���");
	   return false;
 		  }
  }  
  return true;
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
	  mcobj.value="��";
	}else{
    	mcobj.value="��";
	}
	
	return strValue;
}

//�����걨����
function getSbsj(objForm)
{   
    var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
	var objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
	objNode.appendChild(objTemp);
	objNode.appendChild(objTemp);
	getChildren(objTemp,"xh");
	getChildren(objTemp,"gjrjpzmc");
	getChildren(objTemp,"sdtxnx");
	getChildren(objTemp,"jtzjqsnd");
	getChildren(objTemp,"jtzjzznd");
	getChildren(objTemp,"kkczje");
	getChildren(objTemp,"zcyz");
	getChildren(objTemp,"yjtzjnx");
	getChildren(objTemp,"yjtzje");

}
// ���ַ�����ʽ��Ϊ���Ҹ�ʽ
// parΪ0���Զ����0.00
// parΪ1�����Զ����
function formatKsslJsje(obj)
{
	
	return (checkvalue(obj,0)&&formatCurrency(obj,0));
}

function doSave(){

	if(!checkYm() ){
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
function doViewZb()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="ViewZb";
	document.forms[0].submit();
}
 
</script>


<title>¼����ҵ�⹺��������۾ɻ�̯�����ޱ�������</title>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="parseXmlOnLoad()">

<table width="100%" height="100%" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" colspan=2 valign="top"><jsp:include
			page="../../include/SbHeader.jsp" flush="true">
			<jsp:param name="name" value="¼����˰����ҵ����˰����˰��������" />
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
		</jsp:include> <html:errors />


		<form name="Form1" method="post" action="/shenbao/basx016.dc">
		<input name="actionType" type="hidden" id="actionType"></input>
		<table width="770" border="0" cellpadding="0" cellspacing="0"
			align="center">
			<tr>
				<td valign="top" class="title"><br>
				<table width="75%" cellspacing=0 border=0 class="table-99"
					style="TABLE-LAYOUT:fixed">
					<tr>
						<td class="1-td1">¼����ҵ�⹺��������۾ɻ�̯�����ޱ�������</td>
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
									alt="�鿴������" onclick="doViewZb()" style="cursor:hand" width="95"
									height="22"></td>
								<td></td>
								<td><img src="<%=static_contextpath%>images/fanhui1.jpg"
									onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'"
									onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'"
									alt="����" onclick="doReturn()" style="cursor:hand"></td>
							</tr>
						</table>
						<br>
						<br>
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
