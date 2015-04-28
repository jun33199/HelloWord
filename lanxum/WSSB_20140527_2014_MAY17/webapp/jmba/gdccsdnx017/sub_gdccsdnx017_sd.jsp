<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
%>
<html>
<head>
<title>¼��̶��ʲ������۾����޻�����۾ɱ�������</title>
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

<%
String bussStr = (String) session.getAttribute("buss");
String selIndex = (String) session.getAttribute("selIndex");
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
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX17")==null?"":request.getSession().getAttribute("XSLLX17")%>';

function parseXmlOnLoad()
{	if(tempxsllx=="VIEW")
	{
		var xslPath='/shenbao/jmba/gdccsdnx017/sub_gdccsdnx017_sdView.xsl';
		document.getElementById("an1").style.display="none";
		document.getElementById("an2").style.display="block";
	}else
	{
		var xslPath='/shenbao/jmba/gdccsdnx017/sub_gdccsdnx017_sd.xsl';
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
	//��������
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//��˰����Ϣ
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);

	applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc","bajmsehbl"	,"fhwjmc","qsrq","ztdm","ztmc","jzrq","szdm","szmc","bsfsdm","bsfsmc"]);
	//�걨����
	getSbsj(objForm);	
	
	//ȥ��ĩβ�Զ���ӵĻس�
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
		if(strTag=="sfnxdyzd_sd" || strTag=="sftgczqksm_sd"){

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
	getChildren(objTemp,"gdzcmc_sd");
	getChildren(objTemp,"sfnxdyzd_sd");
	getChildren(objTemp,"sftgczqksm_sd");
	getChildren(objTemp,"gdzcyz_sd");
	getChildren(objTemp,"gdzcjsjc_sd");
	getChildren(objTemp,"sfgdzdnx_sd");
	getChildren(objTemp,"jszjzdnx_sd");
	getChildren(objTemp,"zjqsnd_sd");
	getChildren(objTemp,"zjzznd_sd");
	getChildren(objTemp,"zje_sd");
	getChildren(objTemp,"yjtzjnx_sd");
	getChildren(objTemp,"yjtzje_sd");
	//getChildren(objTemp,"gdzcmc_js");
	//getChildren(objTemp,"sftgffsm_js");
	//getChildren(objTemp,"gdzcyz_js");
	//getChildren(objTemp,"gdzcjsjc_js");
	//getChildren(objTemp,"jszjffdm_js");
	//getChildren(objTemp,"zje_js");
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
	if(!checkYm() ){
		return false;
   	}
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


<%/*Ч��ҳ��Ԫ��*/%>
	function checkYm(){
		var gdzcmc_sd = document.all("gdzcmc_sd").value;
		if(gdzcmc_sd == "" ){
			alert("�̶��ʲ����Ʋ���Ϊ��!");
			document.forms[0].gdzcmc_sd.focus();
			return false;
		}
		
		var gdzcyz_sd = document.all("gdzcyz_sd").value;
		if(gdzcyz_sd == "" ){
			alert("�̶��ʲ�ԭֵ����Ϊ��!");
			document.forms[0].gdzcyz_sd.focus();
			return false;
		}
		
		var gdzcjsjc_sd = document.all("gdzcjsjc_sd").value;
		if(gdzcjsjc_sd == "" ){
			alert("�̶��ʲ���˰��������Ϊ��!");
			document.forms[0].gdzcjsjc_sd.focus();
			return false;
		}
		
		var sfgdzdnx_sd = document.all("sfgdzdnx_sd").value;
		if(sfgdzdnx_sd == "" ){
			alert("˰���涨��������޲���Ϊ��!");
			document.forms[0].sfgdzdnx_sd.focus();
			return false;
		}
		if(isNaN(sfgdzdnx_sd))
		{
			alert("˰���涨���������ֻ��Ϊ����!");
			document.forms[0].sfgdzdnx_sd.focus();
			return false;
		}
		
		var jszjzdnx_sd = document.all("jszjzdnx_sd").value;
		if(jszjzdnx_sd == "" ){
			alert("�����۾ɵ�������޲���Ϊ��!");
			document.forms[0].jszjzdnx_sd.focus();
			return false;
		}
		if(isNaN(jszjzdnx_sd))
		{
			alert("�����۾ɵ��������ֻ��Ϊ����!");
			document.forms[0].jszjzdnx_sd.focus();
			return false;
		}
		
		var zjqsnd_sd = document.all("zjqsnd_sd").value;
		if(zjqsnd_sd == "" ){
			alert("�����۾��������Ϊ��!");
			document.forms[0].zjqsnd_sd.focus();
			return false;
		}
		if(!validateYear(zjqsnd_sd))
		{
			alert("�����۾�������ʽ����ȷ!����д��1925��2009֮��!");
			document.forms[0].zjqsnd_sd.focus();
			return false;
		}
		
		var zjzznd_sd = document.all("zjzznd_sd").value;
		if(zjzznd_sd == "" ){
			alert("�����۾����ֹ����Ϊ��!");
			document.forms[0].zjzznd_sd.focus();
			return false;
		}
		if(!validateYear(zjzznd_sd))
		{
			alert("�����۾����ֹ��ʽ����ȷ!����д��1925��2009֮��!");
			document.forms[0].zjzznd_sd.focus();
			return false;
		}
		
		if(zjqsnd_sd>zjzznd_sd)
		{
			alert("�����۾����ֹʱ�䲻��С�ڼ����۾������ʱ��!");
			document.forms[0].zjzznd_sd.focus();
			return false;
		}
		
		var zje_sd = document.all("zje_sd").value;
		if(zje_sd == "" ){
			alert("ÿ��ɿ۳����۾ɶ��Ϊ��!");
			document.forms[0].zje_sd.focus();
			return false;
		}
		
		var yjtzjnx_sd = document.all("yjtzjnx_sd").value;
		if(yjtzjnx_sd == "" ){
			alert("�Ѽ����۾ɵ����޲���Ϊ��!");
			document.forms[0].yjtzjnx_sd.focus();
			return false;
		}
		if(isNaN(yjtzjnx_sd))
		{
			alert("�Ѽ����۾ɵ�����ֻ��Ϊ����!");
			document.forms[0].yjtzjnx_sd.focus();
			return false;
		}
		
		var yjtzje_sd = document.all("yjtzje_sd").value;
		if(yjtzje_sd == "" ){
			alert("�Ѽ�����۾ɶ��Ϊ��!");
			document.forms[0].yjtzje_sd.focus();
			return false;
		}
		if(document.getElementsByName("sfnxdyzd_sd")[1].checked ||document.getElementsByName("sftgczqksm_sd")[1].checked)
		{	
			alert("ѡ���Ƿ�������У����κ�һ��ѡ�����������˰����˰������ύ!");
			return false;
		}
		 return true;
		
	}
	
	function doClear(){
		document.forms[0].gdzcmc_sd.value="";
		document.forms[0].sfnxdyzd_sd[1].checked=true;
		document.forms[0].sftgczqksm_sd[1].checked=true;
		document.forms[0].gdzcyz_sd.value="";
		document.forms[0].gdzcjsjc_sd.value="";
		document.forms[0].sfgdzdnx_sd.value="";
		document.forms[0].jszjzdnx_sd.value="";
		document.forms[0].zjqsnd_sd.value="";
		document.forms[0].zjzznd_sd.value="";
		document.forms[0].zje_sd.value="";
		document.forms[0].yjtzjnx_sd.value="";
		document.forms[0].yjtzje_sd.value="";
		
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
			<jsp:param name="name" value="¼��̶��ʲ������۾����޻�����۾ɱ�������" />
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
		</jsp:include> <html:errors />

		<form name="form1" method="POST" action="/shenbao/gdccsdnx17.dc"><input
			name="actionType" type="hidden" id="actionType"> <input
			name="buss" type="hidden" id="buss" value="<%=bussStr%>"> <input
			name="selIndex" type="hidden" id="selIndex" value="<%=selIndex%>">

		<TABLE width="770" border='0' cellpadding='0' cellspacing='0'
			align='center' class='black9'>
			<tr>
				<td valign="top">
				<table align="center" cellspacing="0" class="table-99">
					<tr>
						<td class="1-td1">¼��̶��ʲ������۾����޻�����۾ɱ�������</td>
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
									onMouseOut="MM_swapImgRestore()" value="����" id="bc1"
									src="<%=static_contextpath%>images/baocun1.jpg" width="79"
									height="22" alt="����"></td>
								<td><img style="cursor:hand" onclick="doClear()"
									onMouseOver="MM_swapImage('qc','','<%=static_contextpath%>images/qc-u2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="���" id="qc"
									src="<%=static_contextpath%>images//qc-u1.jpg" width="79"
									height="22" alt="���"></td>
								<td><img style="cursor:hand" onclick="doReturn()"
									onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fanhui2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="����" id="fh1"
									src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
									height="22" alt="����"></td>
							</tr>
						</table>
						
						<table width="100%" border="0" align="center" id="an2">
							<tr align="center">
								<td><img style="cursor:hand" onclick="doReturnView()"
									onMouseOver="MM_swapImage('fh2','','<%=static_contextpath%>images/fanhui2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="����" id="fh2"
									src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
									height="22" alt="����"></td>
							</tr>
						</table>
						<br>
						<table width="99%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
								<hr width="100%" size="1" style="color='#003399'">
								</td>
								<td width="99" align="center" class="black9"><strong><font
									color="#0000FF">ע �� �� ��</font></strong></td>
								<td>
								<hr width="100%" size="1" style="color='#003399'">
								</td>
							</tr>
						</table>
						<table width="99%" border="1" align="center" cellpadding="1"
							cellspacing="1" bordercolor="#FFFFFF" class="black9">
							<tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
								<td height="47"><br>
								&nbsp;&nbsp;1���̶��ʲ����ƣ����������д�̶��ʲ�����<br />
								&nbsp;&nbsp;2���Ƿ��ύ�̶��ʲ��Ĺ��ܡ�Ԥ��ʹ�����޶��ڡ�ʵʩ�������涨�����۾ɵ�������޵����ɡ�֤�����ϼ��й������������������ǻ��<br />
								&nbsp;&nbsp;3���Ƿ��ύ������ľɹ̶��ʲ��Ĺ��ܡ�ʹ�ü����õ������˵������������ǻ��<br />
								&nbsp;&nbsp;4���̶��ʲ�ԭֵ�����������д���չ���ͳһ����ƶȼ�����ȡ�۾ɡ�̯�����ʲ�ԭֵ������ʷ�ɱ����Ľ�<br />
								&nbsp;&nbsp;5���̶��ʲ���˰���������������д����˰�չ涨����˰ǰ�۳��۾ɡ�̯���Ľ�<br />
								&nbsp;&nbsp;6��˰���涨��������ޣ����������д����̶��ʲ�����˰�չ涨�����ʹ�����ޣ���λΪ�꣺<br />
								&nbsp;&nbsp;7�������۾ɵ�������ޣ����������д����̶��ʲ�����˰�չ涨�ļ����۾ɵ�������ޣ���λΪ��.<br />
								&nbsp;&nbsp;8�������۾���ֹ��ȣ��������<br />
								&nbsp;&nbsp;9��ÿ��ɿ۳����۾ɶ���������д����̶��ʲ�����˰�չ涨�����ÿ��ɿ۳����۾ɶ�.<br />
								&nbsp;&nbsp;10���Ѽ����۾ɵ����ޣ����������ʵ��д 11���Ѽ�����۾ɶ�������˰������۳����Ѽ�����۾ɶ�<br />
								&nbsp;&nbsp;&nbsp;3.1����ѡ���Ƿ�������У����κ�һ��ѡ�����������˰����˰������ύ�� <br />
								&nbsp;&nbsp;&nbsp;3.2�˼���˰������������걨���޹�����ϵ��
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
