<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="java.util.*"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.GXJSLY"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
    List gxjslyList = (List)session.getAttribute("GXJSLYLIST");
    String ymlx =(String)request.getSession().getAttribute("XSLLX08");
%>
<html>
<head>
<title>经认定的高新技术企业</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>

<script language="JavaScript" type="text/JavaScript" src="js1/WdatePicker.js"></script>

<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">

<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
<script language="JavaScript">
var gxjs= new Array(<%=gxjslyList.size()%>);
<%
for(int i=0;i<gxjslyList.size();i++)
{
	GXJSLY tmpgxjs = (GXJSLY)gxjslyList.get(i);
	out.println("gxjs["+i+"] = [\""+tmpgxjs.getGXJSLYDM()+"\",\""+tmpgxjs.getGXJSLYMC()+"\"];");
}
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
var tempgxjslx = '<%=request.getSession().getAttribute("GXJSLX08")==null?"":request.getSession().getAttribute("GXJSLX08")%>';
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX08")==null?"":request.getSession().getAttribute("XSLLX08")%>';

function parseXmlOnLoad()
{
	
	var xslPath="";
  if(tempxsllx=='VIEW'){
	 xslPath='/XSLTWEB/model/030008/XSLT/gjyqzdfcgxjsqyview08.xsl';
	 }else{
	 xslPath='/XSLTWEB/model/030008/XSLT/gjyqzdfcgxjsqy08.xsl';
	 }

	
	if (strXml != "")
    {    //alert(strXml);
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    insertGxjs();
    return true;
}
function insertGxjs()
{
var obj=document.getElementById("gxjslydmdiv");
//alert(obj);
var select1 = document.createElement("select");
        var ooption = new Array();
        for(var i=0;i<gxjs.length;i++)
        {
               select1.options[i] = new Option(gxjs[i][1], gxjs[i][0]);
                if(tempgxjslx!=""&&gxjs[i][0]==tempgxjslx){
                //alert(tempgxjslx);
               select1.options[i].selected=true;
               }
        }
        select1.id="gxjslydm";
        obj.appendChild(select1);
         if(tempxsllx!=""&&tempxsllx=='VIEW')
               select1.disabled=true;
}
function doViewZb()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="ViewZb";
	document.forms[0].submit();
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
    applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc","fhwjmc","qsrq","ztdm","ztmc","jzrq","szdm","szmc","bsfsdm","bsfsmc"]);
	var objNodeJm = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
	var objTempJm = g_Doc.XMLDoc.createElement("jmsbajl");
	objNodeJm.appendChild(objTempJm);
	
	//申报数据
	getSbsj(objForm);	
	
	//去掉末尾自动添加的回车
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	return retstr;
}


//生成申报数据
function getSbsj(objForm)
{
	var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
	var objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
	objNode.appendChild(objTemp);
		getChildren(objTemp,"xh");
	getChildren(objTemp,"basqwsh");
	getChildren(objTemp,"jsjdm");
	getChildren(objTemp,"band");
	getChildren(objTemp,"swjgzzjgdm");
	getChildren(objTemp,"gxjslydm");
	getChildren(objTemp,"zsbh");
	getChildren(objTemp,"zsyxqsrq");
	getChildren(objTemp,"zsyxzzrq");
	getChildren(objTemp,"zkysbl");
	getChildren(objTemp,"yfrybl");
	getChildren(objTemp,"yffybl");
	getChildren(objTemp,"gxcpsrbl");
	getChildren(objTemp,"lrr");
	getChildren(objTemp,"lrrq");
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
	{    if(strTag=="sfysygdfw" || strTag=="sfyzjjzbg" ||strTag=="sfyfyjgmxb"){

           if(document.getElementsByName(strTag)[0].checked){
               strValue=formString("1");
           }else{
               strValue=formString("0");
           }
           //alert(strTag +" "+strValue);
	  }else{
		strValue=formString(element.value);		
		}
//		 alert(element+strValue);	
        //alert(strValue);
		var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
		node.appendChild(objCDATA);
	}	
}
function Trim(str) {
return str.replace(/(^\s*)|(\s*$)/g, "");
}
function checkblx(){
if(document.getElementById("zsyxzzrq").value.length==0||document.getElementById("zsyxqsrq").value.length==0){
alert("证书有效期不能为空!");
return false;
}
var a=/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})/;
if (!a.test(document.getElementById("zsyxzzrq").value)||!a.test(document.getElementById("zsyxqsrq").value)){
alert("证书有效期格式错误！");
  return false
 } 
if(document.getElementById("zsbh").value.length>50||Trim(document.getElementById("zsbh").value).length<=0){
alert("证书编号不能为空最大长度最长为50位！");
return false;
}


if(document.getElementById("zsyxzzrq").value<=document.getElementById("zsyxqsrq").value){
alert("证书有效期止不能小于等于证书有效期起!");
return false;
}
var blx=document.getElementById("zkysbl").value;
//alert(parseInt(zkry.substr(0,2))+"rrrrrrr");
if(blx==null||blx==""){
alert("企业具有大学专科以上学历的科技人员占企业当年职工总数的比例为必填项!");
return false;
}else if(blx<30){
alert("企业具有大学专科以上学历的科技人员占企业当年职工总数的比例数值不能大于100或小于30，请重新输入！");
return false;
}
blx=document.getElementById("yfrybl").value;
if(blx==null||blx==""){
alert("研发人员占企业当年职工总数的比例为必填项！");
return false;
}else if(blx<10){
alert("研发人员占企业当年职工总数的比例数值不能大于100或小于10，请重新输入！");
return false;
}
blx=document.getElementById("gxcpsrbl").value;
if(blx==null||blx==""){
alert("企业当年高新技术产品（服务）收入占企业总收入的比例为必填项！");
return false;
}else if(blx<60){
alert("企业当年高新技术产品（服务）收入占企业总收入的比例数值不能大于100或小于60，请重新输入！");
return false;
}
blx=document.getElementById("yffybl").value;
if(blx==null||blx==""){
alert("企业近三个会计年度的研究开发费用总额占销售收入总额的比例为必填项！");
return false;
}else if(blx<0){
alert("企业近三个会计年度的研究开发费用总额占销售收入总额的比例数值不能大于100或小于0，请重新输入！");
return false;
}
/*if(document.forms[0].sfysygdfw[1].checked==true){
alert("产品（服务）是否属于《国家重点支持的高新技术领域》规定的范围不能为否！");
return false;
}
if(document.forms[0].sfyzjjzbg[1].checked==true){
alert("中介机构鉴证报告不能为否！");
return false;
}
if(document.forms[0].sfyfyjgmxb[1].checked==true){
alert("企业年度研究开发费用结构明细表不能为否！");
return false;
}*/
return true;
}
function yzcs(str1,str2){
if(str2=="zkysbl"){
if(isNaN(str1)){
alert("请在企业具有大学专科以上学历的科技人员占企业当年职工总数的比例项数值范围为大于等于30小于100内的数字！");
document.getElementById('zkysbl').focus(); 
return;
}else{
if(str1>100||str1<0){
alert("企业具有大学专科以上学历的科技人员占企业当年职工总数的比例数值不能大于100或小于30，请重新输入！");
document.getElementById('zkysbl').focus();
return;
}
if(str1.length>5){
alert("企业具有大学专科以上学历的科技人员占企业当年职工总数的比例数值最多保留2位小数，请重新输入！");
document.getElementById('zkysbl').focus(); 
return;
}
}
return;
}
if(str2=="yfrybl"){
if(isNaN(str1)){
alert("请在研发人员占企业当年职工总数的比例项数值范围为大于等于10小于100内的数字！");
document.getElementById('yfrybl').focus(); 
return;
}else{
if(str1>100||str1<0){
alert("研发人员占企业当年职工总数的比例数值不能大于100或小于10，请重新输入！");
document.getElementById('yfrybl').focus();
return;
}
if(str1.length>5){
alert("研发人员占企业当年职工总数的比例数值最多保留2位小数，请重新输入！");
document.getElementById('yfrybl').focus();
return;
}
}
return;
}
if(str2=="gxcpsrbl"){
if(isNaN(str1)){
alert("请在企业当年高新技术产品（服务）收入占企业总收入的比例项数值范围为大于等于60小于100内的数字！");
document.getElementById('gxcpsrbl').focus(); 
return;
}else{
if(str1>100||str1<0){
alert("企业当年高新技术产品（服务）收入占企业总收入的比例数值不能大于100或小于60，请重新输入！");
document.getElementById('gxcpsrbl').focus(); 
return;
}
if(str1.length>5){
alert("企业当年高新技术产品（服务）收入占企业总收入的比例数值最多保留2位小数，请重新输入！");
document.getElementById('gxcpsrbl').focus(); 
return;
}
}
return;
}
if(str2=="yffybl"){
if(isNaN(str1)){
alert("请在企业近三个会计年度的研究开发费用总额占销售收入总额的比例项数值范围为大于等于0小于100内的数字！");
document.getElementById('yffybl').focus(); 
return;
}else{
if(str1>100||str1<0){
alert("企业近三个会计年度的研究开发费用总额占销售收入总额的比例数值不能大于100或小于0，请重新输入！");
document.getElementById('yffybl').focus(); 
return;
}
if(str1.length>5){
alert("企业近三个会计年度的研究开发费用总额占销售收入总额的比例数值最多保留2位小数，请重新输入！");
document.getElementById('yffybl').focus(); 
return;
}
}
return;
}
}
function doSave(){
    if(checkblx()==true){
	var old  = document.forms[0].ywczlx.value;
	//alert("业务操作类型：1---"+old);
	if (confirm(confirmSave))
	{
		 if (document.forms[0].ywczlx.value == 0)
		{
			document.forms[0].ywczlx.value = 1;
		}
		else if (document.forms[0].ywczlx.value == 1)
		{
			document.forms[0].ywczlx.value = 2;
		}
		//alert("业务操作类型：2---"+document.forms[0].ywczlx.value);
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
}

function doCommit(){
if(checkblx()==true){
	var old  = document.forms[0].ywczlx.value;
		//alert("业务操作类型：1---"+old);
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
			//alert("业务操作类型：2---"+document.forms[0].ywczlx.value);
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
}
function doEditZb(){
	
		if(checkblx()==true){

	
	var old  = document.forms[0].ywczlx.value;
		if (confirm("是否生成备案表?"))
	{
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
		}else
	{
		return false;
	}
}
function doReturn()
{  
/* alert("ddd");
     var xmlhttp_request;
      if(window.ActiveXObject){
	       xmlhttp_request=new ActiveXObject("Microsoft.XMLHTTP");
	    }else if(window.XMLHttpRequest){
	       xmlhttp_request=new XMLHttpRequest();
	     } else {
	       return;
	   }
	   alert(xmlhttp_request);
	     xmlhttp_request.open("GET", "/shenbao/gjyqzdfcgxjsqy.dc?actionType=Test", true);
     	 xmlhttp_request.send(null);
     	 //alert("1");
	     xmlhttp_request.onreadystatechange =function zdyprocessAjaxResponse(){
	     var xmlDOM;
	       //alert("3");
	     if (xmlhttp_request.readyState == 4) {
        if (xmlhttp_request.status == 200) {
        alert("2");
         xmlDOM = xmlhttp_request.responseXML;
         alert(xmlDOM);
         alert(xmlDOM.getElementsByTagName("KK")[0].firstChild.data);
         return false;
        }
        }
        }
        */
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
}

</script>


</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="/include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="经认定的高新技术企业" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/gjyqzdfcgxjsqy.dc">
	<input name="actionType" type="hidden" id="actionType">
    
<table  width="770"  border="0" cellpadding="0" cellspacing="0" align="center" >
	<tr>
<td valign="top" class="title"> <br>

      <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
        <tr>
          <td class="1-td1" >经认定的高新技术企业</td>
        </tr>
        <tr>
          <td class="1-td2">
		      
        <br>


            <div id="result"></div>
                 <table width="100%" border="0" align="center">
                     <tr align="center">
<%if (!"VIEW".equals(ymlx)){%>
                    	<td >
                      	<img onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/baocun2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/baocun1.jpg" name="shanchu11" width="95" height="22" border="0" id="baocun"  alt="保存" 

onclick="javascript:return doSave();">
                      </td>


                      <td >
                      <img onMouseOver="MM_swapImage('scbab','','<%=static_contextpath%>images/b_scbab2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/b_scbab1.jpg" name="shanchu11" width="95" height="22" border="0" id="scbab"  alt="生成备案表" 

onclick="doEditZb()">
                      
                      	<!--<img src="<%=static_contextpath%>images/qc-u2.jpg" name="shanchu11" width="79" height="22" border="0" id="shanchu11" alt="提交" 

onclick="javascript:return doClear();">-->
                      </td>
<%}else{%>
 <td >
 <img onMouseOver="MM_swapImage('ckbab','','<%=static_contextpath%>images/b_ckbab2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/b_ckbab1.jpg" name="shanchu11" width="95" height="22" border="0" id="ckbab"  alt="查看备案表" 

onclick="doViewZb()">
                       
                      	<!--<img src="<%=static_contextpath%>images/qc-u2.jpg" name="shanchu11" width="95" height="22" border="0" id="shanchu11" alt="提交" 

onclick="javascript:return doClear();">-->
                      </td>
                  <%}%>    
                      <td >
                        <img onclick="doReturn()" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/fanhui2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/fanhui1.jpg"
                        width="95" height="22">
                      </td>
                    </tr>
                  </table>
            <br>
            
			
				<input type="hidden" name="rdnd" value="2009">
				<div id=hiddenArea></div>
			</td>
	</tr>
</table>
</form>
 </td>
</tr>
<tr><td valign="bottom" align="center">
<br>
<%@ include file="/include/bottom.jsp" %>
 </td>
</tr>
</table>
</body>
</html>