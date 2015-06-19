<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
	String ymlx = (String) request.getSession().getAttribute("XSLLX11");
%>
<html>
<head>
<title>�����߿�С��0.8΢�ף��������ɵ�·��Ʒ��������ҵ����˰��������</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript"
	src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" src="js/calculate.js">
    </script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet"
	type="text/css">

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
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX11")==null?"":request.getSession().getAttribute("XSLLX11")%>';
var tempczlx11 = '<%=request.getSession().getAttribute("CZLX11")==null?"":request.getSession().getAttribute("CZLX11")%>';

function parseXmlOnLoad()
{
var xslPath=''
  if(tempxsllx=='VIEW'){
	 xslPath='/XSLTWEB/model/030011/XSLT/scxkxyjcdlcpscqyview11.xsl';
	 }else{
	 if(tempczlx11=='1'){
	 xslPath='/XSLTWEB/model/030011/XSLT/scxkxyjcdlcpscqyview11.xsl';
	 }else{
	  xslPath='/XSLTWEB/model/030011/XSLT/scxkxyjcdlcpscqy11.xsl';
	 }
	 }
	if (strXml != "")
    {    //alert(strXml);
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    /* if(tempxsllx!='VIEW'&&tempxsllx!=""&&tempczlx11=='2'){
    document.getElementById("sfsyjcdlqy").disabled=true;
    document.getElementById("hlnd").disabled=true;
    document.getElementById("qtzl").disabled=true;
    }*/
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
    applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc"	,"fhwjmc","qsrq","ztdm","ztmc","jzrq","szdm","szmc","bsfsdm","bsfsmc"]);

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
	getChildren(objTemp,"lrr");
	getChildren(objTemp,"lrrq");

	getChildren(objTemp,"hlnd");
		getChildren(objTemp,"jzqsnd");
	getChildren(objTemp,"mzzznd");
	getChildren(objTemp,"jzzznd");
	getChildren(objTemp,"yjjmse");
	getChildren(objTemp,"mzqsnd");



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
	{    if(strTag=="sfsyjcdlqy"){

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
    	if(pageHlnd>document.getElementById('band').value){
    	alert("������Ȳ��ܴ��ڵ�ǰ������ȣ�");
    	window.setTimeout("document.getElementById('hlnd').focus();", 50)  ;
   		return false;
    }else{

        return true;
    }
    }
    }
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
	    	   alert("������4λ��ȣ�");	    	   
	    	   obj.focus();
	    	   obj.select();
	    	   return false;
	    	}
	    if(t1.length > 0 && isInt(t1)) {
	    	return true;
	    } else {
	      alert("������4λ��ȣ�");
	    	   obj.focus();
	    	   obj.select();
	    	   return false;
	    }	

}
function checkYm(){
//alert("dd");
/*if(document.getElementById("qtzl").value.length>1000){
alert("����������󳤶�Ϊ1000�����ֻ��ַ���");
return false;
}*/
if(doChengNd()){
if(isNaN(document.getElementById("yjjmse").value)){
alert("����Ԥ�Ƶļ���˰�����λ���֣�");
return false;
}else{
if(document.getElementById("yjjmse").value.length>17||document.getElementById("yjjmse").value.length<=0||document.getElementById("yjjmse").value<=0){
alert("����Ԥ�Ƶļ���˰���Ϊ���Ҵ���0��󳤶��Ϊ17λ��");
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
          alert("������ֹ��ȱ������������ʼ��ȣ�");
          return false;
        }
        }else{
        if((trim(document.all("mzqsnd").value)!="" && trim(document.all("mzzznd").value)=="")||(trim(document.all("mzqsnd").value)=="" && trim(document.all("mzzznd").value)!="")){
         alert("������Ȳ���Ϊ�գ�");
         return false;
        }
        }
        if (trim(document.all("jzqsnd").value)!="" && trim(document.all("jzzznd").value)!="")
        {
         qsnd=parseInt(document.all("jzqsnd").value);
         jznd=parseInt(document.all("jzzznd").value);
        if (jznd<=qsnd){
          alert("������ֹ��ȱ�����ڼ�����ʼ��ȣ�");
          return false;
        }
        }else{
        if((trim(document.all("jzqsnd").value)!="" && trim(document.all("jzzznd").value)=="")||(trim(document.all("jzqsnd").value)=="" && trim(document.all("jzzznd").value)!="")){
         alert("������Ȳ���Ϊ�գ�");
         return false;
        }
        }

 
/*
if(Trim(document.getElementById("mzqsnd").value).length>0||Trim(document.getElementById("mzzznd").value).length>0||Trim(document.getElementById("jzqsnd").value).length>0||Trim(document.getElementById("jzzznd").value).length>0){
//alert("������������������Ȳ����п�ֵ��");
////��֤�����
//return false;
//}else{
if(isNaN(document.getElementById("mzqsnd").value)||isNaN(document.getElementById("mzzznd").value)||isNaN(document.getElementById("jzqsnd").value)||isNaN(document.getElementById("jzzznd").value)){
alert("������������������ȱ���ȫ��Ϊ��λ�꣡");
//��֤�����
return false;
}else{
if(document.getElementById("mzqsnd").value.length!=4||document.getElementById("mzzznd").value.length!=4||document.getElementById("jzqsnd").value.length!=4||document.getElementById("jzzznd").value.length!=4){
alert("������������������ȱ���ȫ��Ϊ��λ�꣡");
//��֤�����
return false;
}else{
if(parseInt(document.getElementById("mzqsnd").value)>=parseInt(document.getElementById("mzzznd").value)){
alert("���������С������ֹ��ȣ�");
//��֤�����
return false;
}
if(parseInt(document.getElementById("mzzznd").value)>=parseInt(document.getElementById("jzqsnd").value)){
alert("����ֹ���С�ڼ�����������ȣ�");
//��֤�����
return false;
}
if(parseInt(document.getElementById("jzqsnd").value)>=parseInt(document.getElementById("jzzznd").value)){
alert("�������������С�ڼ�����ֹ��ȣ�");
//��֤�����
return false;
}
}
}
*/
return true;
}

}
function Trim(str) {
return str.replace(/(^\s*)|(\s*$)/g, "");
}
function doSave(){
	if(tempczlx11=='1'){
		alert("�Ѿ��������Ż����ߣ������ٴα�����");
		return false;
	}
	if(!checkYm()){
  		return false;
  	}
  	if(!confirm("�Ƿ񱣴�¼������?"))
	{
		return false;
	}
  	
		var old  = document.forms[0].ywczlx.value;
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
}

function doCommit(){
		if(tempczlx11=='1'){
			alert("�Ѿ��������Ż����ߣ������ٴα�����");
			return false;
		}
		if(!checkYm()){
  			return false;
  		}
  		
		var old  = document.forms[0].ywczlx.value;
	
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
}

function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";

	document.forms[0].actionType.value="Show";

	document.forms[0].submit();
}
function doEditZb(){

	if(!checkYm()){
  			return false;
  		}
		
	if(!confirm("�Ƿ����ɱ�����?"))
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
<table width="98%" height="100%" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" colspan=2 valign="top"><jsp:include
			page="/include/SbHeader.jsp" flush="true">
			<jsp:param name="name" value="�����߿�С��0.8΢�ף��������ɵ�·��Ʒ��������ҵ����˰��������" />
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
		</jsp:include> <html:errors />

		<form name="form1" method="POST" action="/shenbao/scxkxyjcdlcpscqy.dc">
		<input name="actionType" type="hidden" id="actionType">

		<table width="770" border="0" cellpadding="0" cellspacing="0"
			align="center">
			<tr>
				<td valign="top" class="title"><br>

				<table width="75%" cellspacing=0 border=0 class="table-99"
					style="TABLE-LAYOUT:fixed">
					<tr>
						<td class="1-td1">�����߿�С��0.8΢�ף��������ɵ�·��Ʒ��������ҵ����˰��������</td>
					</tr>
					<tr>
						<td class="1-td2"><br>


						<div id="result"></div>
						<table width="100%" border="0" align="center">
							<tr align="center">
								<%
								if (!"VIEW".equals(ymlx)) {
								%>
								<td><img
									onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/baocun2.jpg',1)"
									onMouseOut="MM_swapImgRestore()"
									src="<%=static_contextpath%>images/baocun1.jpg"
									name="shanchu11" width="95" height="22" border="0" id="baocun"
									alt="����" onclick="javascript:return doSave();"></td>


								<td><img
									onMouseOver="MM_swapImage('scbab','','<%=static_contextpath%>images/b_scbab2.jpg',1)"
									onMouseOut="MM_swapImgRestore()"
									src="<%=static_contextpath%>images/b_scbab1.jpg"
									name="shanchu11" width="95" height="22" border="0" id="scbab"
									alt="���ɱ�����" onclick="doEditZb()"> <!--<img src="<%=static_contextpath%>images/qc-u2.jpg" name="shanchu11" width="79" height="22" border="0" id="shanchu11" alt="�ύ"

onclick="javascript:return doClear();">--></td>
								<%
								} else {
								%>
								<td><img
									onMouseOver="MM_swapImage('ckbab','','<%=static_contextpath%>images/b_ckbab2.jpg',1)"
									onMouseOut="MM_swapImgRestore()"
									src="<%=static_contextpath%>images/b_ckbab1.jpg"
									name="shanchu11" width="95" height="22" border="0" id="ckbab"
									alt="�鿴������" onclick="doViewZb()"> <!--<img src="<%=static_contextpath%>images/qc-u2.jpg" name="shanchu11" width="95" height="22" border="0" id="shanchu11" alt="�ύ"

onclick="javascript:return doClear();">--></td>
								<%
								}
								%>
								<td><img onclick="doReturn()"
									onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/fanhui2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1"
									src="<%=static_contextpath%>images/fanhui1.jpg" width="95"
									height="22"></td>
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
			<tr>
				<td valign="bottom" align="center"><br>
				<%@ include file="/include/bottom.jsp"%></td>
			</tr>
		</table>
</body>
</html>
