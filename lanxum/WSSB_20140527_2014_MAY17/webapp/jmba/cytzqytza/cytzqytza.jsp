<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
String ymlx =(String)request.getSession().getAttribute("XSLLX13a");


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
<html>
<head>
<title>创业投资企业抵扣应纳税所得额备案事项</title>
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

var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX13a")==null?"":request.getSession().getAttribute("XSLLX13a")%>';
function parseXmlOnLoad()
{
 var xslPath=''
  if(tempxsllx=='VIEW'){
	 xslPath='jmba/cytzqytza/cytzqytzaview.xsl';
	 }else{
	 xslPath='jmba/cytzqytza/cytzqytza.xsl';
	 }
	if (strXml != "")
    {
        //alert(strXml);
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    return true;
}
function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	if(tempxsllx=='VIEW'){
	document.forms[0].actionType.value="View";
	}else{
	document.forms[0].actionType.value="Edit";
	}
	document.forms[0].submit();
}

function doAdd(){
//    alert("add");
    document.forms[0].actionType.value="Add";
    document.forms[0].submit();
}

function doCommit(){
	document.forms[0].actionType.value="Commit";
    document.forms[0].submit();
}

function doDel(selIndex){
    if(confirm("请确认删除")){
        document.forms[0].selIndex.value=selIndex;
        document.forms[0].actionType.value="Del";
        document.forms[0].submit();
    }
}

function doEditor(selIndex){
    document.forms[0].selIndex.value=selIndex;
    document.forms[0].actionType.value="Editor";
    document.forms[0].submit();
}
</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="13A创业投资企业抵扣应纳税所得额备案事项" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/cytzqytza13.dc">
	<input name="actionType" type="hidden" id="actionType">
    <input name="selIndex" type="hidden" id="selIndex">
<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9'>
 <tr>
    <td valign="top">
      <table align="center" cellspacing="0" class="table-99">
<tr>
                <td class="1-td1">
                创业投资企业抵扣应纳税所得额备案事项
                </td>
              </tr>
              <tr>
                <td class="1-td2">

                  <br>
				<div id="result"></div>
            </td>
    </tr>
    <tr>

                  <table width="100%" border="0" align="center">
                    <tr align="center">
<%if (!"VIEW".equals(ymlx)){%>
                    	<td >
                      	<img src="<%=static_contextpath%>images/xinzeng1.jpg"  name="shanchu11" width="79" height="22" border="0" id="shanchu11"  alt="新增"

onclick="javascript:return doAdd();">
                      </td>


                      <td >
                      	<img src="<%=static_contextpath%>images/tijiao2.jpg" name="shanchu11" width="79" height="22" border="0" id="shanchu11" alt="提交"

onclick="javascript:return doCommit();">
                      </td>
 <%}%>
                      <td >
                        <img onclick="doReturn()" alt="退出" name="tc1" width="79" height="22" border="0" id="tc1" src="<%=static_contextpath%>images/fanhui2.jpg">
                      </td>
                    </tr>
                  </table>
            <br>
            <table width="99%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td> <hr width="100%" size="1" style="color='#003399'"> </td>
                <td width="99" align="center" class="black9"><strong><font color="#0000FF">注
                  意 事 项</font></strong></td>
                <td> <hr width="100%" size="1" style="color='#003399'"> </td>
              </tr>
            </table>
            <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
              <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                  <td height="47">
                      <br>&nbsp;&nbsp;1手工录入业投资企业投资的被投资企业计算机代码，纳税人名称
                          <br>&nbsp;&nbsp;2是O   否O：手工选择 “是”或 “否”；
                              <br>&nbsp;&nbsp;3手工录入主管税务机关要求报送的其他资料
                                  <br>&nbsp;&nbsp;4“新增一行”“删除一行”可增加或者删除一行。
                                      <br>
                                          <br>&nbsp;&nbsp;3.1 本表各栏目为必填项，如未填写则不能向税务提交申请。
                                              <br>&nbsp;&nbsp;3.2 表中选择是否的条件中，有任何一条选择否，则不能向税务提交申请。
                                              </td>
			  </tr>
            </table>
            <br> </td>
					</tr>
				</table>
    </tr>
  </table>
    </td>
 </tr>
</table>
</form>
 </td>
</tr>
<tr><td valign="bottom" align="center">
<br>
<jsp:include page="../../include/bottom.jsp" flush="true" >
</jsp:include>
 </td>
</tr>
</table>
</body>
</html>
