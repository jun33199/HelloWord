<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
String ymlx =(String)request.getSession().getAttribute("XSLLX09");
%>
<html>
<head>
<title>新办软件生产企业、集成电路设计企业减免税备案事项</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js1/WdatePicker.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>
    <script language="JavaScript" src="js/calculate.js">
    </script>

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
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX09")==null?"":request.getSession().getAttribute("XSLLX09")%>';
var tempzslx09 = '<%=request.getSession().getAttribute("ZSLX09")==null?"":request.getSession().getAttribute("ZSLX09")%>';
var tempczlx09 = '<%=request.getSession().getAttribute("CZLX09")==null?"":request.getSession().getAttribute("CZLX09")%>';
function parseXmlOnLoad()
{
 var xslPath=''
  if(tempxsllx=='VIEW'){
	 xslPath='/XSLTWEB/model/030009/XSLT/xrjscqyjcdlsjqyview09.xsl';
	 }else{
	 if(tempczlx09=='1'){
	  xslPath='/XSLTWEB/model/030009/XSLT/xrjscqyjcdlsjqyview09.xsl';
	 }else{
	  xslPath='/XSLTWEB/model/030009/XSLT/xrjscqyjcdlsjqy09.xsl';
	 }
	 }
	if (strXml != "")
    {    //alert(strXml);
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    //alert(tempzslx09);
    if(tempzslx09!=""&&tempzslx09!=null)
    document.getElementById("zslxdm").selectedIndex=tempzslx09;
   /* if(tempxsllx!='VIEW'&&tempxsllx!=""&&tempczlx09=='2'){
    document.getElementById("zslxdm").disabled=true;
    document.getElementById("zsbh").disabled=true;
    document.getElementById("zsyxqsrq").disabled=true;
    document.getElementById("zsyxzzrq").disabled=true;
    document.getElementById("hlnd").disabled=true;
    document.getElementById("bnsdqksm").disabled=true;
    document.getElementById("qtzl").disabled=true;
    }*/
     if(tempxsllx!=""&&tempxsllx=='VIEW')
               document.getElementById("zslxdm").disabled=true;
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
function Trim(str) {
return str.replace(/(^\s*)|(\s*$)/g, "");
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
    	//||pageHlnd<(2003) 小于2003或
    	if(pageHlnd>document.getElementById('band').value){
    	alert("获利年度不能大于备案年度！");
    	window.setTimeout("document.getElementById('hlnd').focus();", 50)  ;
   		return false;
    }else{

        return true;
    }
    }
    }



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

	getChildren(objTemp,"zslxdm");
	getChildren(objTemp,"zsbh");
	getChildren(objTemp,"zsyxqsrq");
	getChildren(objTemp,"zsyxzzrq");
	getChildren(objTemp,"hlnd");
	getChildren(objTemp,"mzqsnd");
	getChildren(objTemp,"mzzznd");
	getChildren(objTemp,"jzqsnd");
	getChildren(objTemp,"jzzznd");





}

function checkD1(obj){
	var t1 = trim(obj.value);
	if (t1==""){
	   return true;
	}
	while(t1.length>0 && t1.charAt(0)=='0')
	{
		t1 = t1.substring(1);
	}
	    	if (t1.length!=4){
	    	   alert("请输入4位年度！");	    	   
	    	   obj.focus();
	    	   obj.select();
	    	   return false;
	    	}
	    if(t1.length > 0 && isInt(t1)) {
	    	return true;
	    } else {
	      alert("请输入4位年度！");
	    	   obj.focus();
	    	   obj.select();
	    	   return false;
	    }	

}

function checkblx(){

if(document.getElementById("zsyxzzrq").value<=document.getElementById("zsyxqsrq").value){
alert("证书有效期止不能小于等于证书有效期起!");
return false;
}
}
function checkYm(){
if(document.getElementById("zsyxzzrq").value.length==0||document.getElementById("zsyxqsrq").value.length==0){
alert("证书有效期不能为空!");
return false;
}
var a=/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})/;
if (!a.test(document.getElementById("zsyxzzrq").value)||!a.test(document.getElementById("zsyxqsrq").value)){
alert("证书有效期格式错误！");
  return false
 }
 if(document.getElementById("zsyxzzrq").value<=document.getElementById("zsyxqsrq").value){
alert("证书有效期止不能小于等于证书有效期起!");
return false;
}
if(document.getElementById("zsbh").value.length>50||Trim(document.getElementById("zsbh").value).length<=0){
alert("证书编号不能为空最大长度最长为50位！");
return false;
}
if(Trim(document.getElementById("hlnd").value).length<=0){
alert("获利年度不能为空！");
return false;
}else{
 if(!checkD1(document.getElementById("hlnd"))) {
	      return false;
        }
        if(document.getElementById("hlnd").value>document.getElementById("band").value){
        alert("获利年度不能大于备案年度");
         return false;
        }
}

	    if(!checkD1(document.all("mzqsnd"))) {
	      return false;
        }	
	    if(!checkD1(document.all("mzzznd"))) {
	      return false;
        }	
	    if(!checkD1(document.all("jzqsnd"))) {
	      return false;
        }	
	    if(!checkD1(document.all("jzzznd"))) {
	      return false;
        }	

        var qsnd=0;
        var jznd=0;
        if (trim(document.all("mzqsnd").value)!="" && trim(document.all("mzzznd").value)!="")
        {
         qsnd=parseInt(document.all("mzqsnd").value);
         jznd=parseInt(document.all("mzzznd").value);
        if (jznd<=qsnd){
          alert("免征终止年度必须大于免征起始年度！");
          return false;
        }
        }else{
        if((trim(document.all("mzqsnd").value)!="" && trim(document.all("mzzznd").value)=="")||(trim(document.all("mzqsnd").value)=="" && trim(document.all("mzzznd").value)!="")){
         alert("免征年度不能为空！");
         return false;
        }
        }
        if (trim(document.all("jzqsnd").value)!="" && trim(document.all("jzzznd").value)!="")
        {
         qsnd=parseInt(document.all("jzqsnd").value);
         jznd=parseInt(document.all("jzzznd").value);
        if (jznd<=qsnd){
          alert("减征终止年度必须大于减征起始年度！");
          return false;
        }
        }else{
        if((trim(document.all("jzqsnd").value)!="" && trim(document.all("jzzznd").value)=="")||(trim(document.all("jzqsnd").value)=="" && trim(document.all("jzzznd").value)!="")){
         alert("减征年度不能为空！");
         return false;
        }
        }

/*
if(Trim(document.getElementById("mzqsnd").value).length>0||Trim(document.getElementById("mzzznd").value).length>0||Trim(document.getElementById("jzqsnd").value).length>0||Trim(document.getElementById("jzzznd").value).length>0){
//alert("免征年度与减半征收年度不能有空值！");
//验证待完成
//return true;
//}else{
if(isNaN(document.getElementById("mzqsnd").value)||isNaN(document.getElementById("mzzznd").value)||isNaN(document.getElementById("jzqsnd").value)||isNaN(document.getElementById("jzzznd").value)){
alert("免征年度与减半征收年度必须全部为四位年！");
//验证待完成
return false;
}

else{
if(document.getElementById("mzqsnd").value.length!=4||document.getElementById("mzzznd").value.length!=4||document.getElementById("jzqsnd").value.length!=4||document.getElementById("jzzznd").value.length!=4){
alert("免征年度与减半征收年度必须全部为四位年！");
//验证待完成
return false;
}else{
if(parseInt(document.getElementById("mzqsnd").value)>=parseInt(document.getElementById("mzzznd").value)){
alert("免征起年度小于免征止年度！");
//验证待完成
return false;
}


if(parseInt(document.getElementById("mzzznd").value)>=parseInt(document.getElementById("jzqsnd").value)){
alert("免征止年度小于减半征收起年度！");
//验证待完成
return false;
}
if(parseInt(document.getElementById("jzqsnd").value)>=parseInt(document.getElementById("jzzznd").value)){
alert("减半征收起年度小于减半征止年度！");
//验证待完成
return false;
}
}
}
return true;
}
*/
/*
if(document.getElementById("bnsdqksm").value.length>50||document.getElementById("bnsdqksm").value.length<=0){
alert("本年所得情况的说明不能为空最大长度最长为50和汉字！");
return false;
}*/
return true;
}
function doSave(){
if(tempczlx09=='1'){
alert("已经享受完优惠政策，不能再次备案！");
return false;
}
if(!checkYm())
  return false;
	var old  = document.forms[0].ywczlx.value;
	if (confirm("是否保存录入数据?"))
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
if(tempczlx09=='1'){
alert("已经享受完优惠政策，不能再次备案！");
return false;
}
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
    		<jsp:param name="name" value="新办软件生产企业、集成电路设计企业减免税备案事项" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/xrjscqyjcdlsjqy.dc">
	<input name="actionType" type="hidden" id="actionType">

<table  width="770"  border="0" cellpadding="0" cellspacing="0" align="center" >
	<tr>
<td valign="top" class="title"> <br>

      <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
        <tr>
          <td class="1-td1" >新办软件生产企业、集成电路设计企业减免税备案事项</td>
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
