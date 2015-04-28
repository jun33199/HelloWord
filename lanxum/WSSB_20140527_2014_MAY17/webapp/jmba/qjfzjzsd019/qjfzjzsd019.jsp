<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
%>
<html>
<head>
<title>录入清洁发展机制项目的所得备案事项</title>
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
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX19")==null?"":request.getSession().getAttribute("XSLLX19")%>';
var tempczlx19 = '<%=request.getSession().getAttribute("CZLX19")==null?"":request.getSession().getAttribute("CZLX19")%>';

function parseXmlOnLoad()
{
	var xslPath='';
	if(tempxsllx=="VIEW")
	{
		xslPath='/shenbao/jmba/qjfzjzsd019/qjfzjzsd019View.xsl';
		document.getElementById("an1").style.display="none";
		document.getElementById("an2").style.display="block";
	}else{
		if(tempczlx19=='3'||tempczlx19=='4'){
	 
	  		xslPath='/shenbao/jmba/qjfzjzsd019/qjfzjzsd019View.xsl';
	  		document.getElementById("an1").style.display="none";
		document.getElementById("an2").style.display="block";
		 }else{
		  	xslPath='/shenbao/jmba/qjfzjzsd019/qjfzjzsd019.xsl';
		  	document.getElementById("an1").style.display="block";
			document.getElementById("an2").style.display="none";
		 }
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
        document.getElementById("mzzznd").value=parseInt(pageHlnd)+5;
        document.getElementById("jzqsnd").value=parseInt(pageHlnd)+6;
        document.getElementById("jzzznd").value=parseInt(pageHlnd)+10;
    }

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
		if(strTag=="sfyzrzmcl" || strTag=="sfysjzmcl" ||strTag=="sfysrzmcl" ||strTag=="sfyhsqksm"){
			 if(document.getElementsByName(strTag)[0].checked){
               strValue=formString("0");
           }else{
               strValue=formString("1");
           }
		
		//alert(strTag +" "+strValue);
		}else{
		strValue=formString(element.value);		
//		 alert(element+strValue);	
		}
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
	getChildren(objTemp,"zrsrje");
	getChildren(objTemp,"sjje1");
	getChildren(objTemp,"sjje2");
	getChildren(objTemp,"sfyzrzmcl");
	getChildren(objTemp,"sfysjzmcl");
	getChildren(objTemp,"sfysrzmcl");
	getChildren(objTemp,"sfyhsqksm");
	getChildren(objTemp,"hlnd");
	getChildren(objTemp,"qtzl");
	getChildren(objTemp,"mzqsnd");
	getChildren(objTemp,"mzzznd");
	getChildren(objTemp,"jzqsnd");
	getChildren(objTemp,"jzzznd");
	getChildren(objTemp,"cjr");
	getChildren(objTemp,"cjrq");
	getChildren(objTemp,"lrr");
	getChildren(objTemp,"lrrq");
	
	

}

function doSave(){
	if(tempczlx19=='3'||tempczlx19=='4')
	{
		alert("已经享受完优惠政策，不能再次备案！");
		return false;
	}
	if(!checkCommit()){
		return false;
   	}
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
	if(!checkYm() ){
		return false;
   	}
	if(!checkCommit()){
		return false;
   	}
	var old  = document.forms[0].ywczlx.value;
	if (confirm("是否提交申报资料!"))
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


function formatKsslJsje(obj)
{
	//alert("kssljsje");
	return (checkvalue(obj,0)&&formatCurrency(obj,0));
}

//验证选择是否的条件中，有任何一条选择否，则不允许纳税人向税务机关提交								
function checkCommit()
{	
	if(document.getElementsByName("sfyzrzmcl")[1].checked ||
	 document.getElementsByName("sfysjzmcl")[1].checked || 
	 document.getElementsByName("sfysrzmcl")[1].checked || 
	 document.getElementsByName("sfyhsqksm")[1].checked
	)
	{	
		alert("选择有无条件中，有任何一条选择无，则不允许纳税人向税务机关提交!");
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
		var qtzl=document.getElementById("QTZL").value;
	    if(qtzl.length>=1000)
	    {
	    	alert("其它资料长度不能超过1000字!");
	    	return false;
	    }
		return true;
	}
	
function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Edit";
	document.forms[0].submit();
}

function doReturnView()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="View";
	document.forms[0].submit();
} 
</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" colspan="2" valign="top"><jsp:include
			page="../../../include/SbHeader.jsp" flush="true">
			<jsp:param name="name" value="录入清洁发展机制项目的所得备案事项" />
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
		</jsp:include> <html:errors />

		<form name="form1" method="POST" action="/shenbao/qjfzjzsd19.dc">
		<input name="actionType" type="hidden" id="actionType">

		<TABLE width="770" border='0' cellpadding='0' cellspacing='0'
			align='center' class='black9'>
			<tr>
				<td valign="top">
				<table align="center" cellspacing="0" class="table-99">
					<tr>
						<td class="1-td1">录入清洁发展机制项目的所得备案事项</td>
					</tr>
					<tr>
						<td class="1-td2"><br>
						<div id="result"></div>
						</td>
					</tr>
					<tr>
						<table width="100%" border="0" align="center" id="an1">
							<tr align="center">
								<td><img style="cursor:hand"
									onclick="javascript:return doSave();"
									onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/baocun2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="保存" id="bc1"
									src="<%=static_contextpath%>images/baocun1.jpg" width="79"
									height="22" alt="保存"></td>
								<td><img style="cursor:hand"
									onclick="javascript:return doCommit();"
									onMouseOver="MM_swapImage('tj1','','<%=static_contextpath%>images/tijiao2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="提交" id="tj1"
									src="<%=static_contextpath%>images/tijiao1.jpg" width="79"
									height="22" alt="提交"></td>
								<td><img style="cursor:hand" onclick="doReturn()"
									onMouseOver="MM_swapImage('fh2','','<%=static_contextpath%>images/fanhui2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="返回" id="fh2"
									src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
									height="22" alt="返回"></td>
							</tr>
						</table>
						<table width="100%" border="0" align="center" id="an2">
							<tr align="center">
								<td><img style="cursor:hand" onclick="doReturnView()"
									onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fanhui2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="返回" id="fh1"
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
								&nbsp;&nbsp;1温室气体减排量转让收入金额：手工录入项； <br>
								&nbsp;&nbsp;2上缴给国家的HFC和PFC类CDM项目的金额：手工录入项； <br>
								&nbsp;&nbsp;3温室气体减排量转让收入金额：手工录入项； <br>
								&nbsp;&nbsp;4上缴给国家的N2O类CDM项目的金额：手工录入项； <br>
								&nbsp;&nbsp;5企业将温室气体减排量转让的HFC和PFC类CDM项目，以及将温室气体减排量转让的N2O类CDM项目的证明材料：选择“有”或“无”项目；
								<br>
								&nbsp;&nbsp;6将温室气体减排量转让收入上缴给国家的证明：选择“有”或“无”项目（材料内容，可手工录入）； <br>
								&nbsp;&nbsp;7取得第一笔收入的相关证明资料：选择“有”或“无”项目（材料内容，可手工录入）； <br>
								&nbsp;&nbsp;8项目所得核算情况声明：选择“有”或“无”项目； <br>
								&nbsp;&nbsp;9主管税务机关要求报送的其他资料：手工录入项； <br>
								<br>
								<br>
								&nbsp;&nbsp;3.1 本表各栏目为必填项，如未填写则不能向税务提交申请。 <br>
								&nbsp;&nbsp;3.2 表中选择是否的条件中，有任何一条选择否，则不能向税务提交申请。 <br>
								&nbsp;&nbsp;3.3 待年度纳税申报表附表五的三级附表开发完成后再提交相关需求。</td>
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
<tr>
	<td valign="bottom" align="center"><br>
	<jsp:include page="../../../include/bottom.jsp" flush="true">
	</jsp:include></td>
</tr>
</body>
</html>
