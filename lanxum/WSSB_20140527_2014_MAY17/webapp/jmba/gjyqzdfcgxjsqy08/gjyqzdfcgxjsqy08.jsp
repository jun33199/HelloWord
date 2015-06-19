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
<title>���϶��ĸ��¼�����ҵ</title>
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
	//��������
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//��˰����Ϣ
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);
    applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc","fhwjmc","qsrq","ztdm","ztmc","jzrq","szdm","szmc","bsfsdm","bsfsmc"]);
	var objNodeJm = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
	var objTempJm = g_Doc.XMLDoc.createElement("jmsbajl");
	objNodeJm.appendChild(objTempJm);
	
	//�걨����
	getSbsj(objForm);	
	
	//ȥ��ĩβ�Զ���ӵĻس�
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	return retstr;
}


//�����걨����
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
alert("֤����Ч�ڲ���Ϊ��!");
return false;
}
var a=/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})/;
if (!a.test(document.getElementById("zsyxzzrq").value)||!a.test(document.getElementById("zsyxqsrq").value)){
alert("֤����Ч�ڸ�ʽ����");
  return false
 } 
if(document.getElementById("zsbh").value.length>50||Trim(document.getElementById("zsbh").value).length<=0){
alert("֤���Ų���Ϊ����󳤶��Ϊ50λ��");
return false;
}


if(document.getElementById("zsyxzzrq").value<=document.getElementById("zsyxqsrq").value){
alert("֤����Ч��ֹ����С�ڵ���֤����Ч����!");
return false;
}
var blx=document.getElementById("zkysbl").value;
//alert(parseInt(zkry.substr(0,2))+"rrrrrrr");
if(blx==null||blx==""){
alert("��ҵ���д�ѧר������ѧ���ĿƼ���Առ��ҵ����ְ�������ı���Ϊ������!");
return false;
}else if(blx<30){
alert("��ҵ���д�ѧר������ѧ���ĿƼ���Առ��ҵ����ְ�������ı�����ֵ���ܴ���100��С��30�����������룡");
return false;
}
blx=document.getElementById("yfrybl").value;
if(blx==null||blx==""){
alert("�з���Առ��ҵ����ְ�������ı���Ϊ�����");
return false;
}else if(blx<10){
alert("�з���Առ��ҵ����ְ�������ı�����ֵ���ܴ���100��С��10�����������룡");
return false;
}
blx=document.getElementById("gxcpsrbl").value;
if(blx==null||blx==""){
alert("��ҵ������¼�����Ʒ����������ռ��ҵ������ı���Ϊ�����");
return false;
}else if(blx<60){
alert("��ҵ������¼�����Ʒ����������ռ��ҵ������ı�����ֵ���ܴ���100��С��60�����������룡");
return false;
}
blx=document.getElementById("yffybl").value;
if(blx==null||blx==""){
alert("��ҵ�����������ȵ��о����������ܶ�ռ���������ܶ�ı���Ϊ�����");
return false;
}else if(blx<0){
alert("��ҵ�����������ȵ��о����������ܶ�ռ���������ܶ�ı�����ֵ���ܴ���100��С��0�����������룡");
return false;
}
/*if(document.forms[0].sfysygdfw[1].checked==true){
alert("��Ʒ�������Ƿ����ڡ������ص�֧�ֵĸ��¼������򡷹涨�ķ�Χ����Ϊ��");
return false;
}
if(document.forms[0].sfyzjjzbg[1].checked==true){
alert("�н������֤���治��Ϊ��");
return false;
}
if(document.forms[0].sfyfyjgmxb[1].checked==true){
alert("��ҵ����о��������ýṹ��ϸ����Ϊ��");
return false;
}*/
return true;
}
function yzcs(str1,str2){
if(str2=="zkysbl"){
if(isNaN(str1)){
alert("������ҵ���д�ѧר������ѧ���ĿƼ���Առ��ҵ����ְ�������ı�������ֵ��ΧΪ���ڵ���30С��100�ڵ����֣�");
document.getElementById('zkysbl').focus(); 
return;
}else{
if(str1>100||str1<0){
alert("��ҵ���д�ѧר������ѧ���ĿƼ���Առ��ҵ����ְ�������ı�����ֵ���ܴ���100��С��30�����������룡");
document.getElementById('zkysbl').focus();
return;
}
if(str1.length>5){
alert("��ҵ���д�ѧר������ѧ���ĿƼ���Առ��ҵ����ְ�������ı�����ֵ��ౣ��2λС�������������룡");
document.getElementById('zkysbl').focus(); 
return;
}
}
return;
}
if(str2=="yfrybl"){
if(isNaN(str1)){
alert("�����з���Առ��ҵ����ְ�������ı�������ֵ��ΧΪ���ڵ���10С��100�ڵ����֣�");
document.getElementById('yfrybl').focus(); 
return;
}else{
if(str1>100||str1<0){
alert("�з���Առ��ҵ����ְ�������ı�����ֵ���ܴ���100��С��10�����������룡");
document.getElementById('yfrybl').focus();
return;
}
if(str1.length>5){
alert("�з���Առ��ҵ����ְ�������ı�����ֵ��ౣ��2λС�������������룡");
document.getElementById('yfrybl').focus();
return;
}
}
return;
}
if(str2=="gxcpsrbl"){
if(isNaN(str1)){
alert("������ҵ������¼�����Ʒ����������ռ��ҵ������ı�������ֵ��ΧΪ���ڵ���60С��100�ڵ����֣�");
document.getElementById('gxcpsrbl').focus(); 
return;
}else{
if(str1>100||str1<0){
alert("��ҵ������¼�����Ʒ����������ռ��ҵ������ı�����ֵ���ܴ���100��С��60�����������룡");
document.getElementById('gxcpsrbl').focus(); 
return;
}
if(str1.length>5){
alert("��ҵ������¼�����Ʒ����������ռ��ҵ������ı�����ֵ��ౣ��2λС�������������룡");
document.getElementById('gxcpsrbl').focus(); 
return;
}
}
return;
}
if(str2=="yffybl"){
if(isNaN(str1)){
alert("������ҵ�����������ȵ��о����������ܶ�ռ���������ܶ�ı�������ֵ��ΧΪ���ڵ���0С��100�ڵ����֣�");
document.getElementById('yffybl').focus(); 
return;
}else{
if(str1>100||str1<0){
alert("��ҵ�����������ȵ��о����������ܶ�ռ���������ܶ�ı�����ֵ���ܴ���100��С��0�����������룡");
document.getElementById('yffybl').focus(); 
return;
}
if(str1.length>5){
alert("��ҵ�����������ȵ��о����������ܶ�ռ���������ܶ�ı�����ֵ��ౣ��2λС�������������룡");
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
	//alert("ҵ��������ͣ�1---"+old);
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
		//alert("ҵ��������ͣ�2---"+document.forms[0].ywczlx.value);
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
		//alert("ҵ��������ͣ�1---"+old);
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
			//alert("ҵ��������ͣ�2---"+document.forms[0].ywczlx.value);
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
		if (confirm("�Ƿ����ɱ�����?"))
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
    		<jsp:param name="name" value="���϶��ĸ��¼�����ҵ" />
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
          <td class="1-td1" >���϶��ĸ��¼�����ҵ</td>
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
                        onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/baocun1.jpg" name="shanchu11" width="95" height="22" border="0" id="baocun"  alt="����" 

onclick="javascript:return doSave();">
                      </td>


                      <td >
                      <img onMouseOver="MM_swapImage('scbab','','<%=static_contextpath%>images/b_scbab2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/b_scbab1.jpg" name="shanchu11" width="95" height="22" border="0" id="scbab"  alt="���ɱ�����" 

onclick="doEditZb()">
                      
                      	<!--<img src="<%=static_contextpath%>images/qc-u2.jpg" name="shanchu11" width="79" height="22" border="0" id="shanchu11" alt="�ύ" 

onclick="javascript:return doClear();">-->
                      </td>
<%}else{%>
 <td >
 <img onMouseOver="MM_swapImage('ckbab','','<%=static_contextpath%>images/b_ckbab2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/b_ckbab1.jpg" name="shanchu11" width="95" height="22" border="0" id="ckbab"  alt="�鿴������" 

onclick="doViewZb()">
                       
                      	<!--<img src="<%=static_contextpath%>images/qc-u2.jpg" name="shanchu11" width="95" height="22" border="0" id="shanchu11" alt="�ύ" 

onclick="javascript:return doClear();">-->
                      </td>
                  <%}%>    
                      <td >
                        <img onclick="doReturn()" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/fanhui2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/fanhui1.jpg"
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