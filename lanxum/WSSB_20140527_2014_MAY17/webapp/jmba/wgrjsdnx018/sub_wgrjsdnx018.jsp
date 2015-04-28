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
<html>
<head>
<title>¼���⹺��������۾ɻ�̯�����ޱ�������</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript"
	src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>

<link href="<%=static_contextpath%>css/text.css" rel="stylesheet"
	type="text/css">

<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
<script language="JavaScript">

g_objSI.Container = "<%=containerName%>";



var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX18")==null?"":request.getSession().getAttribute("XSLLX18")%>';

function parseXmlOnLoad()
{
	if(tempxsllx=="VIEW")
	{
		var xslPath='/shenbao/jmba/wgrjsdnx018/sub_wgrjsdnx018View.xsl';
		document.getElementById("an1").style.display="none";
		document.getElementById("an2").style.display="block";
	}else
	{
		var xslPath='/shenbao/jmba/wgrjsdnx018/sub_wgrjsdnx018.xsl';
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
	var strValue="";
	if(element!=null)
	{
	   if(strTag=="sffhqrtj" || strTag=="sfysdnxly" ||strTag=="sfysdnxzm" ||strTag=="sfyxgqksm"){

           if(document.getElementsByName(strTag)[0].checked){
               strValue=formString("0");
           }else{
               strValue=formString("1");
           }
           
	  }else{
		strValue=formString(element.value);
      }
//		 alert(element+strValue);
        //alert(strValue);
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
	getChildren(objTemp,"gjrjpzmc");
	getChildren(objTemp,"sffhqrtj");
	getChildren(objTemp,"sfysdnxly");
	getChildren(objTemp,"sfysdnxzm");
	getChildren(objTemp,"sfyxgqksm");
	getChildren(objTemp,"sdtxnx");
	getChildren(objTemp,"jtzjqsnd");
	getChildren(objTemp,"jtzjzznd");
	getChildren(objTemp,"kkczje");
	getChildren(objTemp,"zcyz");
	getChildren(objTemp,"yjtzjnx");
	getChildren(objTemp,"yjtzje");
	getChildren(objTemp,"shbj");
	getChildren(objTemp,"cjr");
	getChildren(objTemp,"cjrq");
	getChildren(objTemp,"lrr");
	getChildren(objTemp,"lrrq");
}

function doSave(){
	if(!checkCommit())
	{
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

function doReturn()
{
	 document.forms[0].action = "/shenbao/wgrjsdnx18.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
}

// ���ַ�����ʽ��Ϊ���Ҹ�ʽ
// parΪ0���Զ����0.00
// parΪ1�����Զ����
function formatKsslJsje(obj)
{
	
	return (checkvalue(obj,0)&&formatCurrency(obj,0));
}



function doReturnView()
{
	document.forms[0].action = "/shenbao/wgrjsdnx18.dc";
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


<%/*�ύУ��*/%>
 
	function checkCommit(){
		
		if(document.getElementsByName("sffhqrtj")[1].checked || 
		document.getElementsByName("sfysdnxly")[1].checked || 
		document.getElementsByName("sfysdnxzm")[1].checked || 
		document.getElementsByName("sfyxgqksm")[1].checked
		)
		{	
			alert("���κ�һ��ѡ�����������˰������ύ!");
			return false;
		}
		return true;
	}
	
	
	function doClear(){
		document.forms[0].gjrjpzmc.value="";
		document.forms[0].sffhqrtj[1].checked=true;
		document.forms[0].sfysdnxly[1].checked=true;
		document.forms[0].sfysdnxzm[1].checked=true;
		document.forms[0].sfyxgqksm[1].checked=true;
		document.forms[0].sdtxnx.value="";
		document.forms[0].jtzjqsnd.value="";
		document.forms[0].jtzjzznd.value="";
		document.forms[0].kkczje.value="";
		document.forms[0].zcyz.value="";
		document.forms[0].yjtzjnx.value="";
		document.forms[0].yjtzje.value="";
		
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
			<jsp:param name="name" value="¼���⹺��������۾ɻ�̯�����ޱ�������"/>
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
		</jsp:include> <html:errors />
		<form name="form1" method="POST" action="/shenbao/wgrjsdnx18.dc">
		<input name="actionType" type="hidden" id="actionType"> <input
			name="buss" type="hidden" id="buss" value="<%=bussStr%>"> <input
			name="selIndex" type="hidden" id="selIndex" value="<%=selIndex%>">

		<TABLE width="770" border='0' cellpadding='0' cellspacing='0'
			align='center' class='black9'>
			<tr>
				<td valign="top">
				<table align="center" cellspacing="0" class="table-99">
					<tr>
						<td class="1-td1">¼���⹺��������۾ɻ�̯�����ޱ�������</td>
					</tr>
					<tr>
						<td class="1-td2">
						<div id="result"></div>
						</td>
					</tr>
					<tr>
							<table width="100%" border="0" align="center" id="an1">
							<br />
							<tr align="center">
								<td><img style="cursor:hand"
									onclick="javascript:return doSave();"
									onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/baocun2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="����" id="bc1"
									src="<%=static_contextpath%>images/baocun1.jpg" width="79"
									height="22" alt="����"></td>
								<td><img style="cursor:hand" onclick="doClear()"
									onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/qc-u2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="���" id="tc1"
									src="<%=static_contextpath%>images/qc-u1.jpg" width="79"
									height="22" alt="���"></td>
								<td><img style="cursor:hand" onclick="doReturn()"
									onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fanhui2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="����" id="fh1"
									src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
									height="22" alt="����"></td>
							</tr>
						</table>
						<table width="100%" border="0" align="center" id="an2">
						<tr  align="center">
							<td colapan="3">
								<img style="cursor:hand" onclick="doReturnView()"
								onMouseOver="MM_swapImage('fh2','','<%=static_contextpath%>images/fanhui2.jpg',1)"
								onMouseOut="MM_swapImgRestore()" value="����" id="fh2"
								src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
								height="22" alt="����">
							</td>
						</tr>
						</table>
						
						</br>
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
								&nbsp;&nbsp;1����ҵ���������ƾ֤���ƣ����������д�̶��ʲ����� <br>
								&nbsp;&nbsp;2���Ƿ���Ϲ̶��ʲ��������ʲ�ȷ��������������������ǻ�� <br>
								&nbsp;&nbsp;3���Ƿ����⹺��������۾ɻ�̯�������޵����ɣ�������������ǻ�� <br>
								&nbsp;&nbsp;4���Ƿ����⹺��������۾ɻ�̯�������޵�֤�����ϣ�������������ǻ�� <br>
								&nbsp;&nbsp;5���Ƿ�����������˵����������������ǻ�� <br>
								&nbsp;&nbsp;6���⹺��������۾ɻ�̯�������ޣ����������д���������˰�չ涨�����۾ɻ�̯�������ޣ���λΪ�ꡣ <br>
								&nbsp;&nbsp;7�������۾���ֹ��ȣ������ <br>
								&nbsp;&nbsp;8��ÿ��ɿ۳����۾ɶ�:��д��ֵ,������λС��,��λΪԪ��������� <br>
								&nbsp;&nbsp;9���̶��ʲ��������ʲ���ԭֵ:���������д���չ���ͳһ����ƶȼ�����ȡ�۾ɡ�̯�����ʲ�ԭֵ������ʷ�ɱ����Ľ�
								<br>
								&nbsp;&nbsp;10���Ѽ����۾ɵ�����:������� <br>
								&nbsp;&nbsp;11���Ѽ�����۾ɶ��˰���涨�Ѽ�����۾ɶ <br>
								<br>
								&nbsp;&nbsp;3��1����ѡ���Ƿ�������У����κ�һ��ѡ�����������˰����˰������ύ�� <br>
								&nbsp;&nbsp;3��2�˼���˰������������걨���޹�����ϵ��</td>
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
