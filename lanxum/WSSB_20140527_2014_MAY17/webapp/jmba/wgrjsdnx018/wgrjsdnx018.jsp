<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
%>
<html>
<head>
<title>�⹺��������۾ɻ�̯�����ޱ�������</title>
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
			com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData) session
		.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
		if (userdata.getCaflag()) {
			containerName = userdata.getCert().getContainerName();
		}else {
		containerName = "";
		}
%>
		g_objSI.Container = "<%=containerName%>";


var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var kkczjeHje = '<%=request.getSession().getAttribute("kkczjeHje")==null?"0":request.getSession().getAttribute("kkczjeHje")%>';
var zcyzHje = '<%=request.getSession().getAttribute("zcyzHje")==null?"0":request.getSession().getAttribute("zcyzHje")%>';
var yjtzjeHje = '<%=request.getSession().getAttribute("yjtzjeHje")==null?"0":request.getSession().getAttribute("yjtzjeHje")%>';
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX18")==null?"":request.getSession().getAttribute("XSLLX18")%>';

function parseXmlOnLoad()
{
	if(tempxsllx=="VIEW")
	{
		var xslPath='/shenbao/jmba/wgrjsdnx018/wgrjsdnx018View.xsl';
		document.getElementById("an1").style.display="none";
		document.getElementById("an2").style.display="block";
	}else
	{
		var xslPath='/shenbao/jmba/wgrjsdnx018/wgrjsdnx018.xsl';
		document.getElementById("an1").style.display="block";
		document.getElementById("an2").style.display="none";
	}
	

	if (strXml != "")
    {   
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    jshje();
    return true;
}

function jshje(){
	document.getElementById("kkczjeHje").innerHTML=kkczjeHje;
	document.getElementById("zcyzHje").innerHTML=zcyzHje;
	document.getElementById("yjtzjeHje").innerHTML=yjtzjeHje;
}

function doAdd(){
    document.forms[0].actionType.value="Add";
    document.forms[0].submit();
}

function doCommit(){
	if(confirm("�Ƿ��ύȫ����������?")){
		document.forms[0].actionType.value="Commit";
    	document.forms[0].submit();
	}
}

function doDel(selIndex){
	if(confirm("�Ƿ񽫴˼�¼ɾ��?")){
		document.forms[0].selIndex.value=selIndex;
    	document.forms[0].actionType.value="Del";
    	document.forms[0].submit();
	}else {
		return false;
	} 
}

function doEditor(selIndex){
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
		<td align="center" colspan=2 valign="top"><jsp:include
			page="../../../include/SbHeader.jsp" flush="true">
			<jsp:param name="name" value="�⹺��������۾ɻ�̯�����ޱ�������" />
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
		</jsp:include> <html:errors />

		<form name="form1" method="POST" action="/shenbao/wgrjsdnx18.dc">
		<input name="actionType" type="hidden" id="actionType"> <input
			name="selIndex" type="hidden" id="selIndex">

		<TABLE width="770" border='0' cellpadding='0' cellspacing='0'
			align='center' class='black9'>
			<tr>
				<td valign="top">
				<table align="center" cellspacing="0" class="table-99">
					<tr>
						<td class="1-td1">�⹺��������۾ɻ�̯�����ޱ�������</td>
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
									onclick="javascript:return doAdd();"
									onMouseOver="MM_swapImage('zj1','','<%=static_contextpath%>images/tianjia2.jpg',1)"
									onMouseOut="MM_swapImgRestore()" value="����" id="zj1"
									src="<%=static_contextpath%>images/tianjia1.jpg" width="79"
									height="22" alt="����"></td>

								<td><img style="cursor:hand"
									onclick="javascript:return doCommit();"
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
								&nbsp;&nbsp;1��ҵ���������ƾ֤���ƣ����������д�̶��ʲ����� <br>
								&nbsp;&nbsp;2�Ƿ���Ϲ̶��ʲ��������ʲ�ȷ��������������������ǻ�� <br>
								&nbsp;&nbsp;3�Ƿ����⹺��������۾ɻ�̯�������޵����ɣ�������������ǻ�� <br>
								&nbsp;&nbsp;4�Ƿ����⹺��������۾ɻ�̯�������޵�֤�����ϣ�������������ǻ�� <br>
								&nbsp;&nbsp;5�Ƿ�����������˵����������������ǻ�� <br>
								&nbsp;&nbsp;6�⹺��������۾ɻ�̯�������ޣ����������д���������˰�չ涨�����۾ɻ�̯�������ޣ���λΪ�ꡣ <br>
								&nbsp;&nbsp;7�����۾���ֹ��ȣ������ <br>
								&nbsp;&nbsp;8ÿ��ɿ۳����۾ɶ�:��д��ֵ,������λС��,��λΪԪ��������� <br>
								&nbsp;&nbsp;9�̶��ʲ��������ʲ���ԭֵ:���������д���չ���ͳһ����ƶȼ�����ȡ�۾ɡ�̯�����ʲ�ԭֵ������ʷ�ɱ����Ľ�
								<br>
								&nbsp;&nbsp;10�Ѽ����۾ɵ�����:������� <br>
								&nbsp;&nbsp;11�Ѽ�����۾ɶ��˰���涨�Ѽ�����۾ɶ <br>
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
