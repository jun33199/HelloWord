<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="java.util.*"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.JSZRLX"%>
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
String ymlx =(String)request.getSession().getAttribute("XSLLX07");
%>

<html>
<head>
<title>符合条件的技术装让所得</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/date.js"></script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">

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
var hje = '<%=request.getSession().getAttribute("HJE")==null?"0":request.getSession().getAttribute("HJE")%>';
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX07")==null?"":request.getSession().getAttribute("XSLLX07")%>';


function parseXmlOnLoad()
{   var xslPath=''
  if(tempxsllx=='VIEW'){
	 xslPath='jmba/fhtjjszrsd07/fhtjjszrsdlistview07.xsl';
	 }else{
	 xslPath='jmba/fhtjjszrsd07/fhtjjszrsdlist07.xsl';
	 }

	if (strXml != "")
    {   // alert(strXml);
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    jshje();
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
function jshje(){
//alert(document.getElementById("hje"));
document.getElementById("hje").innerHTML=hje;
}
function doAdd(){
    //alert("add");
    document.form1.actionType.value="Add";
    
   // alert(document.form1.actionType.value);
    document.form1.submit();
}
function doDel(selIndex){
if(confirm("确定删除此项数据！")){
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

function doCommit(){
	document.forms[0].actionType.value="Commit";
    document.forms[0].submit();
}


</script>


</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="/include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="符合条件的技术转让所得减免企业所得税备案事项" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/fhtjjszrsd.dc">
		<input name="actionType" type="hidden" id="actionType">
    <input name="selIndex" type="hidden" id="selIndex">
    
<table  width="100%"  border="0" cellpadding="0" cellspacing="0" align="center" >
	<tr>
<td valign="top" class="title"> <br>

      <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
        <tr>
          <td class="1-td1" >符合条件的技术转让所得减免企业所得税备案事项</td>
        </tr>
        <tr>
          <td class="1-td2">
		      
        <br>


            <div id="result"></div>
                 <table width="100%" border="0" align="center">
                    <tr align="center">
  <%if (!"VIEW".equals(ymlx)){%>
                    	<td >
                      	<img src="<%=static_contextpath%>images/xinzeng2.jpg"  name="xinzeng11" width="79" height="22" border="0" id="shanchu11"  alt="新增" 

onclick="javascript:return doAdd();">
                      </td>


                      <td >
                      	<img src="<%=static_contextpath%>images/tijiao2.jpg" name="xinzeng11" width="79" height="22" border="0" id="shanchu11" alt="提交" 

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
<br>&nbsp;&nbsp;1是否属于符合条件的技术转让所得，表内数据由纳税人填列并提交主管税务机关。
<br>&nbsp;&nbsp;2经科学技术行政部门认定登记技术转让合同为必录入项，选择有或无，须提供纸制资料并提交主管税务机关。
<br>&nbsp;&nbsp;3技术转让合同认定登记证明和认定登记表为必录入项，选择有或无，须提供纸制资料并提交主管税务机关。
<br>&nbsp;&nbsp;4实际发生的技术性收入明细表为必录入项，选择有或无，须提供纸制资料并提交主管税务机关。
<br>&nbsp;&nbsp;5项目所得核算情况声明为必填项，选择有或无，资料提交主管税务机关。
<br>&nbsp;&nbsp;6主管税务机关要求报送的其他资料为可选择录入项，由操作人员手工录入系统。
<br>&nbsp;&nbsp;7取得技术转让所得为必录入项，由操作人员手工录入。
<br><br>
<br>&nbsp;&nbsp;3．1备案表中必录入项不能为空，如未录入则不能向主管税务机关提交申请。
<br>&nbsp;&nbsp;3．2当纳税人申报减免税备案且通过审核时，“中华人民共和国企业所得税年度申报表A类”附表5“税收优惠明细表”第31行开放，允许填写数据，否则，对应

行次系统默认为不允许编辑状态。
</td>
			  </tr>
            </table><br> </td>
					</tr>
				</table>
				
				<input type="hidden" name="rdnd" value="2009">
				<div id=hiddenArea></div>
			</td>
	</tr>
</table>
</form>
 </td>
</tr>
<tr><td valign="bottom" align="center">
<br>
<%@ include file="/include/bottom.jsp" %>
 </td>
</tr>
</table>
</body>
</html>