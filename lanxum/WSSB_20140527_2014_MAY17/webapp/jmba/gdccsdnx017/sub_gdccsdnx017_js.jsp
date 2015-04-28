<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
String bussStr = (String) session.getAttribute("buss");
String selIndex = (String) session.getAttribute("selIndex");
String containerName = "";
com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData) session
		.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
if (userdata.getCaflag()) {
	containerName = userdata.getCert().getContainerName();
} else {
	containerName = "";
}
%>
g_objSI.Container = "<%=containerName%>";
<html>
<head>
<title>录入固定资产缩短折旧年限或加速折旧备案事项</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript"
	src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/date.js"></script>

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
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX17")==null?"":request.getSession().getAttribute("XSLLX17")%>';

function parseXmlOnLoad()
{	
	if(tempxsllx=="VIEW")
	{
		var xslPath='/shenbao/jmba/gdccsdnx017/sub_gdccsdnx017_jsView.xsl';
		document.getElementById("an1").style.display="none";
		document.getElementById("an2").style.display="block";
	}else
	{
		var xslPath='/shenbao/jmba/gdccsdnx017/sub_gdccsdnx017_js.xsl';
		document.getElementById("an1").style.display="block";
		document.getElementById("an2").style.display="none";
	}
	
	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    return true;
}



function getPostXml(objForm)
{	//alert("88888");
	var retstr;
	//基本数据
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//纳税人信息
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);

	applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc","bajmsehbl"	,"fhwjmc","qsrq","ztdm","ztmc","jzrq","szdm","szmc","bsfsdm","bsfsmc"]);
	//申报数据
	getSbsj(objForm);	
	
	//去掉末尾自动添加的回车
	//alert(g_Doc.XMLHeader);
	//alert(g_Doc.XMLDoc.xml);
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	//alert(retstr);
	return retstr;
}

function getChildren(temp,strTag)
{
	//alert("strTag = " + strTag);
	var element=document.getElementById(strTag);
	var objTemp=temp;
	var node=g_Doc.XMLDoc.createElement(strTag);
	objTemp.appendChild(node);
	
	if(element!=null)
	{ 
		if(strTag=="sftgffsm_js"){

           if(document.getElementsByName(strTag)[0].checked){
               strValue=formString("0");
           }else{
               strValue=formString("1");
           }
           
	  }else{
		strValue=formString(element.value);
      }	
//		 alert(element+strValue);	
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
	getChildren(objTemp,"xh");
	getChildren(objTemp,"basqwsh");
	getChildren(objTemp,"jsjdm");
	getChildren(objTemp,"band");
	getChildren(objTemp,"swjgzzjgdm");
	//getChildren(objTemp,"gdzcmc_sd");
	//getChildren(objTemp,"sfnxdyzd_sd");
	//getChildren(objTemp,"sftgczqksm_sd");
	//getChildren(objTemp,"gdzcyz_sd");
	//getChildren(objTemp,"gdzcjsjc_sd");
	//getChildren(objTemp,"sfgdzdnx_sd");
	//getChildren(objTemp,"jszjzdnx_sd");
	//getChildren(objTemp,"zjqsnd_sd");
	//getChildren(objTemp,"zjzznd_sd");
	//getChildren(objTemp,"zje_sd");
	//getChildren(objTemp,"yjtzjnx_sd");
	//getChildren(objTemp,"yjtzje_sd");
	getChildren(objTemp,"gdzcmc_js");
	getChildren(objTemp,"sftgffsm_js");
	getChildren(objTemp,"gdzcyz_js");
	getChildren(objTemp,"gdzcjsjc_js");
	getChildren(objTemp,"jszjffdm_js");
	getChildren(objTemp,"zje_js");
	getChildren(objTemp,"shbj");
	getChildren(objTemp,"cjr");
	getChildren(objTemp,"cjrq");
	getChildren(objTemp,"lrr");
	getChildren(objTemp,"lrrq");
	getChildren(objTemp,"tbblx");
}

function doSave(){
	
	if(!checkYm() ){
		return false;
   	}
	var old  = document.forms[0].ywczlx.value;
	//alert(old);
	if (confirm(confirmSave))
	{  // alert("1");
		 if (document.forms[0].ywczlx.value == 0)
		{  //alert("2");
			document.forms[0].ywczlx.value = 1;
		}
		else if (document.forms[0].ywczlx.value == 1)
		{  //alert("3");
			document.forms[0].ywczlx.value = 2;
		}
		var commitType = document.forms[0].buss.value;
		document.forms[0].actionType.value=commitType;
			if (g_objSI.Container != '')
			{  //alert("4");
				if (!doSubmitXML(document.forms[0],commitType,true,"",true))
				{  //alert("5");
					 document.forms[0].ywczlx.value = old;
					return false;
				}
			}
			else
			{  //alert("6");
			   if(!doSubmitXML(document.forms[0],commitType,false,"",true))
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
		var commitType = document.forms[0].buss.value;
		document.forms[0].actionType.value=commitType;
			if (g_objSI.Container != '')
			{
				if (!doSubmitXML(document.forms[0],commitType,true,"",true))
				{
					 document.forms[0].ywczlx.value = old;
					return false;
				}
			}
			else
			{
			   if(!doSubmitXML(document.forms[0],commitType,false,"",true))
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
	document.forms[0].action = "/shenbao/gdccsdnx17.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
}

function formatKsslJsje(obj)
{
	//alert("kssljsje");
	return (checkvalue(obj,0)&&formatCurrency(obj,0));
}

function doReturnView()
{
	document.forms[0].action = "/shenbao/gdccsdnx17.dc";
	document.forms[0].actionType.value="View";
	document.forms[0].submit();
}



<%/*效验页面元素*/%>
	function checkYm(){
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
		if(document.getElementsByName("sftgffsm_js")[1].checked)
		{	
			alert("选择是否的条件中，有任何一条选择否，则不允许纳税人向税务机关提交!");
			return false;
		}
		return true;	
	}
	
	function doClear(){
		document.forms[0].jszjffdm_js.options[0].selected=true;
		document.forms[0].gdzcmc_js.value="";
		document.forms[0].sftgffsm_js[1].checked=true;
		document.forms[0].gdzcyz_js.value="";
		document.forms[0].gdzcjsjc_js.value="";
		document.forms[0].zje_js.value="";
		
		
	}
</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" colspan=2 valign="top"><jsp:include
			page="../../../include/SbHeader.jsp" flush="true">
			<jsp:param name="name" value="录入固定资产缩短折旧年限或加速折旧备案事项" />
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
		</jsp:include> <html:errors />

		<form name="form1" method="POST" action="/shenbao/gdccsdnx17.dc"><input
			name="actionType" type="hidden" id="actionType">
			<input name="buss" type="hidden" id="buss" value="<%=bussStr%>"> 
			<input name="selIndex" type="hidden" id="selIndex" value="<%=selIndex%>">

		<TABLE width="770" border='0' cellpadding='0' cellspacing='0'
			align='center' class='black9'>
			<tr>
				<td valign="top">
				<table align="center" cellspacing="0" class="table-99">
					<tr>
						<td class="1-td1">录入固定资产缩短折旧年限或加速折旧备案事项</td>
					</tr>
					<tr>
						<td class="1-td2">
						<div id="result"></div>
						</td>



					</tr>
					<tr>

						<table width="100%" border="0" align="center" id="an1">
							<tr align="center">
								<td><img style="cursor:hand" onclick="javascript:return doSave();"
									onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/baocun2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="保存" id="bc1"
									src="<%=static_contextpath%>images/baocun1.jpg" width="79"
									height="22" alt="保存"></td>
								<td><img style="cursor:hand" onclick="doClear()"
									onMouseOver="MM_swapImage('qc','','<%=static_contextpath%>images/qc-u2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="清除" id="qc"
									src="<%=static_contextpath%>images//qc-u1.jpg" width="79"
									height="22" alt="清除"></td>
								<td><img style="cursor:hand" onclick="doReturn()"
									onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fanhui2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="返回" id="fh1"
									src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
									height="22" alt="返回"></td>
							</tr>
						</table>
						<table width="100%" border="0" align="center" id="an2">
							<tr align="center" colspan="3">
								<td><img style="cursor:hand" onclick="doReturnView()"
									onMouseOver="MM_swapImage('fh2','','<%=static_contextpath%>images/fanhui2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="返回" id="fh2"
									src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
									height="22" alt="返回"></td>
							</tr>
						</table>
						<br>
						<table width="99%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
								<hr width="100%" size="1" style="color='#003399'">
								</td>
								<td width="99" align="center" class="black9"><strong><font
									color="#0000FF">注 意 事 项</font></strong></td>
								<td>
								<hr width="100%" size="1" style="color='#003399'">
								</td>
							</tr>
						</table>
						<table width="99%" border="1" align="center" cellpadding="1"
							cellspacing="1" bordercolor="#FFFFFF" class="black9">
							<tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
								<td height="47"><br>
								&nbsp;&nbsp;1、固定资产名称：（必填项）填写固定资产名称 <br/>
								&nbsp;&nbsp;2、是否提供固定资产加速折旧拟采用的方法和折旧额的说明：必填项，填是或否。<br/>
								&nbsp;&nbsp;3、固定资产原值：（必填项）填写按照国家统一会计制度计算提取折旧、摊销的资产原值（或历史成本）的金额。<br/>
								&nbsp;&nbsp;4、固定资产计税基础：（必填项）填写按照税收规定计算税前扣除折旧、摊销的金额。<br/>
								&nbsp;&nbsp;5、计算折旧的方法：（必填项）填写按照税法规定采用的方法（双倍余额递减法或年数总额法）<br/>
								&nbsp;&nbsp;6、年折旧额：（必填项）填写按照税法规定计算的年折旧额。<br/>
								<br/>
								&nbsp;&nbsp;3.1表中选择是否的条件中，有任何一条选择否，则不允许纳税人向税务机关提交。 3.2此减免税备案表与年度申报表无勾稽关系。<br/>
							</tr>
						</table>
						<br>
					</tr>
				</table>
			</tr>
		</table>
		</td>
	</tr>
</table>
<td valign="bottom" align="center"><br>
<jsp:include page="../../../include/bottom.jsp" flush="true">
</jsp:include></td>
</body>
</html>
