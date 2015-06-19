<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);

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
<title>�̶��ʲ������۾����޻�����۾ɱ�������</title>
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
var temptbblx = '<%=request.getSession().getAttribute("TBBLX")==null?"":request.getSession().getAttribute("TBBLX")%>';
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX17")==null?"":request.getSession().getAttribute("XSLLX17")%>';

//alert("temptbblx"+tempxsllx);
function parseXmlOnLoad()
{	
	if(tempxsllx=="VIEW")
	{
		var xslPath='/shenbao/jmba/gdccsdnx017/gdccsdnx017View.xsl';
		document.getElementById("an1").style.display="none";
		document.getElementById("an2").style.display="block";
	}else
	{
		var xslPath='/shenbao/jmba/gdccsdnx017/gdccsdnx017.xsl';
		document.getElementById("an1").style.display="block";
		document.getElementById("an2").style.display="none";
	}
	
	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    xzb1();
    return true;
}


function xzb1(){

	if(temptbblx=="0")
	{
		document.getElementById("TABLE_LIST1").style.display="block";
		document.getElementById("TABLE_LIST2").style.display="none";
		document.getElementsByName("bm")[0].checked="true";
		document.getElementById("bm1").disabled="false";
		document.getElementById("bm2").disabled="true";
	}else if(temptbblx=="1")
	{
		document.getElementById("TABLE_LIST1").style.display="none";
		document.getElementById("TABLE_LIST2").style.display="block";
		document.getElementsByName("bm")[1].checked="true";
		document.getElementById("bm1").disabled="true";
		document.getElementById("bm2").disabled="false";
	}else
	{
		document.getElementById("TABLE_LIST1").style.display="none";
		document.getElementById("TABLE_LIST2").style.display="block";
		document.getElementsByName("bm")[1].checked="true";
	}
}

function addShow()
{        
	if(document.getElementsByName("bm")[0].checked){
		document.forms[0].tbblx.value = 0;
		//alert(document.forms[0].tbblx.value);
		document.forms[0].actionType.value = "Add_Sd_Show";
		document.forms[0].submit();
	}
	if(document.getElementsByName("bm")[1].checked){
		document.forms[0].tbblx.value = 1;
		//alert(document.forms[0].tbblx.value);
		document.forms[0].actionType.value = "Add_Js_Show";
		document.forms[0].submit();
	}
	
}

function xzb(){
	if(document.getElementsByName("bm")[0].checked){
		//alert(document.getElementsByName("bm")[0].value);
		document.getElementById("TABLE_LIST1").style.display="block";
		document.getElementById("TABLE_LIST2").style.display="none";
		//document.getElementsByName("bm")[1].disabled="true"
	}
	if(document.getElementsByName("bm")[1].checked){
		document.getElementById("TABLE_LIST1").style.display="none";
		document.getElementById("TABLE_LIST2").style.display="block";
		//document.getElementsByName("bm")[0].disabled="true"
	}
}

function doCommit(){
	if(confirm("�Ƿ��ύȫ����������?")){
		document.forms[0].actionType.value="Commit";
	    document.forms[0].submit();
    }
}

function doDel(selIndex){
	if(confirm("ȷ��ɾ����������?")){
		document.forms[0].selIndex.value=selIndex;
    	document.forms[0].actionType.value="Del";
    	document.forms[0].submit();
	}
    
}

function doEditor(selIndex){
	//alert(selIndex);
    document.forms[0].selIndex.value=selIndex;
    document.forms[0].actionType.value="Editor";
    document.forms[0].submit();
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
			<jsp:param name="name" value="�̶��ʲ������۾����޻�����۾ɱ�������" />
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
		</jsp:include> <html:errors />

		<form name="form1" method="POST" action="/shenbao/gdccsdnx17.dc">
		<input name="actionType" type="hidden" id="actionType"> <input
			name="selIndex" type="hidden" id="selIndex"> <input
			name="tbblx" type="hidden" id="tbblx">
		<TABLE width="770" border='0' cellpadding='0' cellspacing='0'
			align='center' class='black9'>
			<tr>
				<td valign="top">
				<table align="center" cellspacing="0" class="table-99">
					<tr>
						<td class="1-td1">�̶��ʲ������۾����޻�����۾ɱ�������</td>
					</tr>
					<tr>
						<td class="1-td2">�����۾����ޱ�:<input type="radio" name="bm"
							id="bm1" value="1" onclick="xzb()">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�����۾ɱ���ȡ˫�����ݼ����������ܶ:<input type="radio" name="bm" id="bm2"
							value="2" onclick="xzb()"></td>
					</tr>
					<tr>
						<td class="1-td2"><br>
						<div id="result"></div>
						</td>
					</tr>
					<tr>

						<table width="100%" border="0" align="center" id="an1">
							<tr align="center">
								<td><img style="cursor:hand" onclick="addShow()"
									onMouseOver="MM_swapImage('zj1','','<%=static_contextpath%>images/tianjia2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="����" id="zj1"
									src="<%=static_contextpath%>images/tianjia1.jpg" width="79"
									height="22" alt="����"></td>

								<td><img style="cursor:hand" onclick="doCommit()"
									onMouseOver="MM_swapImage('tj1','','<%=static_contextpath%>images/tijiao2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="�ύ" id="tj1"
									src="<%=static_contextpath%>images/tijiao1.jpg" width="79"
									height="22" alt="�ύ"></td>

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
								&nbsp;&nbsp;1���̶��ʲ����ƣ����������д�̶��ʲ����� <br>
								&nbsp;&nbsp;2���Ƿ��ύ�̶��ʲ��Ĺ��ܡ�Ԥ��ʹ�����޶��ڡ�ʵʩ�������涨�����۾ɵ�������޵����ɡ�֤�����ϼ��й������������������ǻ��
								<br>
								&nbsp;&nbsp;3���Ƿ��ύ������ľɹ̶��ʲ��Ĺ��ܡ�ʹ�ü����õ������˵������������ǻ�� <br>
								&nbsp;&nbsp;4���̶��ʲ�ԭֵ�����������д���չ���ͳһ����ƶȼ�����ȡ�۾ɡ�̯�����ʲ�ԭֵ������ʷ�ɱ����Ľ�
								<br>
								&nbsp;&nbsp;5���̶��ʲ���˰���������������д����˰�չ涨����˰ǰ�۳��۾ɡ�̯���Ľ� <br>
								&nbsp;&nbsp;6��˰���涨��������ޣ����������д����̶��ʲ�����˰�չ涨�����ʹ�����ޣ���λΪ�꣺ <br>
								&nbsp;&nbsp;7�������۾ɵ�������ޣ����������д����̶��ʲ�����˰�չ涨�ļ����۾ɵ�������ޣ���λΪ�꣺ <br>
								&nbsp;&nbsp;8�������۾���ֹ��ȣ�������� <br>
								&nbsp;&nbsp;9��ÿ��ɿ۳����۾ɶ���������д����̶��ʲ�����˰�չ涨�����ÿ��ɿ۳����۾ɶ� <br>
								&nbsp;&nbsp;10���Ѽ����۾ɵ����ޣ����������ʵ��д <br>
								&nbsp;&nbsp;11���Ѽ�����۾ɶ�������˰������۳����Ѽ�����۾ɶ� <br>
								&nbsp;&nbsp;2.1.2�����۾ɱ���ȡ˫�����ݼ����������ܶ�� <br>
								&nbsp;&nbsp;2.1.2.1�̶��ʲ����ƣ����������д�̶��ʲ����� <br>
								&nbsp;&nbsp;2.1.2.2�Ƿ��ṩ�̶��ʲ������۾�����õķ������۾ɶ��˵������������ǻ�� <br>
								&nbsp;&nbsp;2.1.2.3�̶��ʲ�ԭֵ�����������д���չ���ͳһ����ƶȼ�����ȡ�۾ɡ�̯�����ʲ�ԭֵ������ʷ�ɱ����Ľ�
								<br>
								&nbsp;&nbsp;2.1.2.4�̶��ʲ���˰���������������д����˰�չ涨����˰ǰ�۳��۾ɡ�̯���Ľ� <br>
								&nbsp;&nbsp;2.1.2.5�����۾ɵķ��������������д����˰���涨���õķ�����˫�����ݼ����������ܶ�� <br>
								&nbsp;&nbsp;2.2.2.6���۾ɶ���������д����˰���涨��������۾ɶ <br>
								<br>
								&nbsp;&nbsp;3.1����ѡ���Ƿ�������У����κ�һ��ѡ�����������˰����˰������ύ�� <br>
								&nbsp;&nbsp;3.2�˼���˰������������걨���޹�����ϵ��</td>
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
