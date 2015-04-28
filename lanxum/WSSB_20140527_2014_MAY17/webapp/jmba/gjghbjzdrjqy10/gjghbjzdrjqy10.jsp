<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
String ymlx =(String)request.getSession().getAttribute("XSLLX10");
%>
<html>
<head>
<title>国家规划布局内的重点软件生产企业减免税备案事项</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>

<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">

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
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX10")==null?"":request.getSession().getAttribute("XSLLX10")%>';

function parseXmlOnLoad()
{
 var xslPath=''
  if(tempxsllx=='VIEW'){
	 xslPath='/XSLTWEB/model/030010/XSLT/gjghbjzdrjqyview10.xsl';
	 }else{
	 xslPath='/XSLTWEB/model/030010/XSLT/gjghbjzdrjqy10.xsl';
	 }
	if (strXml != "")
    {    //alert(strXml);
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
    applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc"	,"fhwjmc","qsrq","ztdm","ztmc","jzrq","szdm","szmc","bsfsdm","bsfsmc"]);

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
	getChildren(objTemp,"lrr");
	getChildren(objTemp,"lrrq");
	getChildren(objTemp,"yjjse");
	
	

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
	{    if(strTag=="sfghbjnrjqy"){

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
function checkYm(){
//alert("dd");
/*if(document.getElementById("qtzl").value.length>1000){
alert("其他资料最大长度为1000个汉字或字符！");
return false;
}*/
if(isNaN(document.getElementById("yjjse").value)){
alert("本年减按10%税率征收的预计减税额必须位数字！");
return false;
}else{
if(document.getElementById("yjjse").value.length>17||document.getElementById("yjjse").value.length<=0){
alert("本年减按10%税率征收的预计减税额不能为空最大长度最长为17位！");
return false;
}
}
/*
if(document.forms[0].sfghbjnrjqy[1].checked==true){
alert("是否属于国家发展和改革委员会、工业信息化部、商务部、国家税务总局发布的国家规划布局内重点软件企业不能为否！");
return false;
}*/
return true;
}
function doSave(){
if(!checkYm())
  return false;
	var old  = document.forms[0].ywczlx.value;
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

function doCommit(){
if(!checkYm())
  return false;
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
function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	
	document.forms[0].actionType.value="Show";
	
	document.forms[0].submit();
}

function doEditZb(){
	
		if(checkYm()==true){

	
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
function doViewZb()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="ViewZb";
	document.forms[0].submit();
}
</script>


</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="/include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="国家规划布局内的重点软件生产企业减免税备案事项" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/gjghbjzdrjqy.dc">
	<input name="actionType" type="hidden" id="actionType">
    
<table  width="770"  border="0" cellpadding="0" cellspacing="0" align="center" >
	<tr>
<td valign="top" class="title"> <br>

      <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
        <tr>
          <td class="1-td1" >国家规划布局内的重点软件生产企业减免税备案事项</td>
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