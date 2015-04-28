<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);


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
<html>
<head>
<title>企业购置用于环境保护、节能节水、安全生产等专用设备投资额抵免应纳税额资格备案事项</title>
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

function parseXmlOnLoad()
{
	var xslPath='jmba/qygza/qygza.xsl';

	if (strXml != "")
    {
        //alert(strXml);
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    return true;
}


function doAdd(){
//    alert("add");
    document.forms[0].actionType.value="Add";
    document.forms[0].submit();
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
    		<jsp:param name="name" value="企业购置用于环境保护、节能节水、安全生产等专用设备投资额抵免应纳税额资格备案事项" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/qygza15.dc">
	<input name="actionType" type="hidden" id="actionType">
    <input name="selIndex" type="hidden" id="selIndex">
<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9'>
 <tr>
    <td valign="top">
      <table align="center" cellspacing="0" class="table-99">
<tr>
                <td class="1-td1">
                企业购置用于环境保护、节能节水、安全生产等专用设备投资额抵免应纳税额资格备案事项
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

                    	<td >
                      	<img src="<%=static_contextpath%>images/xinzeng1.jpg"  name="shanchu11" width="79" height="22" border="0" id="shanchu11"  alt="新增"

onclick="javascript:return doAdd();">
                      </td>


                      <td >
                      	<img src="<%=static_contextpath%>images/tijiao2.jpg" name="shanchu11" width="79" height="22" border="0" id="shanchu11" alt="提交"

onclick="javascript:return doCommit();">
                      </td>

                      <td >
                        <img onclick="javascript:return dofanhui();" alt="退出" name="tc1" width="79" height="22" border="0" id="tc1" src="<%=static_contextpath%>images/fanhui2.jpg">
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
            <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1"
                  bordercolor="#FFFFFF" class="black9">
                    <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                      <td height="47">
                        <br>
                        &nbsp;&nbsp;1、“企业购置并实际使用的专用设备类型”：为必选项。通过下拉菜单选择一项。可选项共三项分别为“环境保护专用设备”，“节能节水专用设备”，“安全生产专用设备”
                        <br>
                        &nbsp;&nbsp;2、“企业购置并实际使用的专用设备名称”：为必填项。根据《环境保护专用设备企业所得税优惠目录》、《节能节水专用设备企业所得税优惠目录》，《安全生产专用设备企业所得税优惠目录》填报具体设备名称。
                        <br>
                        &nbsp;&nbsp;3、“购置年度”：为必填项。购置年度时间除2008年外由（服务器年度-1）自动带出且系统锁死（例：服务器年度为2010年，系统带出为2009年度），以后年度系统也自动带出。
                        <br>
                        &nbsp;&nbsp;4、是否有购买专用设备发票及清单
                        <br>
                        &nbsp;&nbsp;5、是否设置专用设备固定资产使用卡片或专用设备固定资产明细帐
                        <br>
                        &nbsp;&nbsp;6、专用设备属于目录规定的环境保护、节能节水、安全生产等专用设备的声明
                        <br>
                        &nbsp;&nbsp;7、是否有专用设备使用情况声明
                        <br>
                        &nbsp;&nbsp;8、购置专用设备的投资额：为必填项。单位元。
                        <br>
                        &nbsp;&nbsp;9、“购置专用设备投资额可抵免的应纳税额”：为自动生成项。由“专用设备投资额”*10%自动生成。
                        <br>
                        &nbsp;&nbsp;10、主管税务机关要求报送的其他资料，由操作人员手工录入系统。
                        <br>
                        <br>
                        &nbsp;&nbsp;3.1 本表各栏目中必填项如未填写则不能向税务提交申请。
                        <br>
                        &nbsp;&nbsp;3.2 填报人在对“企业购置用于环境保护、节能节水、安全生产等专用设备投资额抵免税额资格备案表”填报完全后， “企业购置用于环境保护、节能节水、安全生产等专用设备投资额抵免税额备案表”在填写权限将放开，允许填报。
                        <br>
                        &nbsp;&nbsp;3.3 表中选择是否的条件中，有任何一条选择否，则不能向税务提交申请。
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
