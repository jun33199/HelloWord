<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
%>
<html>
<head>
<title>¼����෢չ������Ŀ�����ñ�������</title>
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


//�����������
function doChengNd(){
    var pageHlnd=document.getElementById("hlnd").value;
    if(isNaN(pageHlnd)){
    alert("�������Ӧ��Ϊ��λ�����꣡");
    window.setTimeout("document.getElementById('hlnd').focus();", 50)  ; 
    return;
    }else{
    if(pageHlnd>2009||pageHlnd<(2009-5)){
    alert("������ȴ��ڷ�Χ��");
    window.setTimeout("document.getElementById('hlnd').focus();", 50)  ; 
    return;
    }
    }
    var pageHlnd=document.getElementById("hlnd").value;
    if(pageHlnd.length != 4){
        alert("������4λ�������!");
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
		alert("�Ѿ��������Ż����ߣ������ٴα�����");
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
	if (confirm("�Ƿ��ύ�걨����!"))
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

//��֤ѡ���Ƿ�������У����κ�һ��ѡ�����������˰����˰������ύ								
function checkCommit()
{	
	if(document.getElementsByName("sfyzrzmcl")[1].checked ||
	 document.getElementsByName("sfysjzmcl")[1].checked || 
	 document.getElementsByName("sfysrzmcl")[1].checked || 
	 document.getElementsByName("sfyhsqksm")[1].checked
	)
	{	
		alert("ѡ�����������У����κ�һ��ѡ���ޣ���������˰����˰������ύ!");
		return false;
	}
	return true;
}

<%/*Ч��ҳ��Ԫ��*/%>
	function checkYm(){
		var zrsrje = document.all("zrsrje").value;
		if(zrsrje == "" ){
			alert("�������������ת���������Ϊ��!");
			document.forms[0].zrsrje.focus();
			return false;
		}
		var sjje1 = document.all("sjje1").value;
		if(sjje1 == "" ){
			alert("�Ͻɸ����ҵ�HFC��PFC��CDM��Ŀ�Ľ���Ϊ��!");
			document.forms[0].sjje1.focus();
			return false;
		}
		var sjje2 = document.all("sjje2").value;
		if(sjje2 == "" ){
			alert("�Ͻɸ����ҵ�N2O��CDM��Ŀ�Ľ���Ϊ��!");
			document.forms[0].sjje2.focus();
			return false;
		}
		var hlnd = document.all("hlnd").value;
		if(hlnd == "" ){
			alert("ȡ�õ�һ�ʼ�����ת������������˰��Ȳ���Ϊ��!");
			document.forms[0].hlnd.focus();
			return false;
		}
		var qtzl=document.getElementById("QTZL").value;
	    if(qtzl.length>=1000)
	    {
	    	alert("�������ϳ��Ȳ��ܳ���1000��!");
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
			<jsp:param name="name" value="¼����෢չ������Ŀ�����ñ�������" />
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
						<td class="1-td1">¼����෢չ������Ŀ�����ñ�������</td>
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
									onMouseOut="MM_swapImgRestore()" value="����" id="bc1"
									src="<%=static_contextpath%>images/baocun1.jpg" width="79"
									height="22" alt="����"></td>
								<td><img style="cursor:hand"
									onclick="javascript:return doCommit();"
									onMouseOver="MM_swapImage('tj1','','<%=static_contextpath%>images/tijiao2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="�ύ" id="tj1"
									src="<%=static_contextpath%>images/tijiao1.jpg" width="79"
									height="22" alt="�ύ"></td>
								<td><img style="cursor:hand" onclick="doReturn()"
									onMouseOver="MM_swapImage('fh2','','<%=static_contextpath%>images/fanhui2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="����" id="fh2"
									src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
									height="22" alt="����"></td>
							</tr>
						</table>
						<table width="100%" border="0" align="center" id="an2">
							<tr align="center">
								<td><img style="cursor:hand" onclick="doReturnView()"
									onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fanhui2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="����" id="fh1"
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
								&nbsp;&nbsp;1�������������ת��������ֹ�¼��� <br>
								&nbsp;&nbsp;2�Ͻɸ����ҵ�HFC��PFC��CDM��Ŀ�Ľ��ֹ�¼��� <br>
								&nbsp;&nbsp;3�������������ת��������ֹ�¼��� <br>
								&nbsp;&nbsp;4�Ͻɸ����ҵ�N2O��CDM��Ŀ�Ľ��ֹ�¼��� <br>
								&nbsp;&nbsp;5��ҵ���������������ת�õ�HFC��PFC��CDM��Ŀ���Լ����������������ת�õ�N2O��CDM��Ŀ��֤�����ϣ�ѡ���С����ޡ���Ŀ��
								<br>
								&nbsp;&nbsp;6���������������ת�������Ͻɸ����ҵ�֤����ѡ���С����ޡ���Ŀ���������ݣ����ֹ�¼�룩�� <br>
								&nbsp;&nbsp;7ȡ�õ�һ����������֤�����ϣ�ѡ���С����ޡ���Ŀ���������ݣ����ֹ�¼�룩�� <br>
								&nbsp;&nbsp;8��Ŀ���ú������������ѡ���С����ޡ���Ŀ�� <br>
								&nbsp;&nbsp;9����˰�����Ҫ���͵��������ϣ��ֹ�¼��� <br>
								<br>
								<br>
								&nbsp;&nbsp;3.1 �������ĿΪ�������δ��д������˰���ύ���롣 <br>
								&nbsp;&nbsp;3.2 ����ѡ���Ƿ�������У����κ�һ��ѡ���������˰���ύ���롣 <br>
								&nbsp;&nbsp;3.3 �������˰�걨�������������������ɺ����ύ�������</td>
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
