<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml" %>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeTable"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.ZYZHLYZL"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
  <head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="JavaScript" type="text/JavaScript"
	src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/date.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js1/WdatePicker.js"></script>
    <script language="JavaScript" src="js/calculate.js">
    </script>

<link href="css/jmba.css" rel="stylesheet" type="text/css">

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


function parseXmlOnLoad()
{
	var xslPath='/XSLTWEB/model/030001/XSLT/zyzhly01viewdetail.xsl';

	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    
     return true;
}
 
function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
}

function doViewZb()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="ViewZb";
	document.forms[0].submit();
}

</script>


   <title>
      资源综合利用企业（项目）申请减免企业所得税备案事项
    </title>
 
  </head>
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
 onload="parseXmlOnLoad()">
  
  <table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="录入纳税人企业所得税减免税备案申请" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>
 

    <form name="Form1" method="post" action="/shenbao/zyzhly01.dc">
 	<input name="actionType" type="hidden" id="actionType"></input>
       <table width="770" border="0" cellpadding="0" cellspacing="0" align="center" >
        <tr>
          <td valign="top" class="title">
            <br>
            <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
              <tr>
                <td class="1-td1">
                  录入资源综合利用企业（项目）申请减免企业所得税备案事项
                </td>
              </tr>
              <tr>
                <td class="1-td2">
                  
                  <br>                 
<div id="result"></div>
                  <br>
               
               
                  <table width="100%" border="0" align="center">
                    <tr align="center">  
                  	
                               <td>
                               <img src="<%=static_contextpath%>images/b_ckbab1.jpg" onmouseover="this.src='<%=static_contextpath%>images/b_ckbab2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/b_ckbab1.jpg'" alt="查看备案表" onclick="return doViewZb()" style="cursor:hand">
                      </td>
                      <td>
                      </td>
                      <td>
                        <img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturn()" style="cursor:hand">
                      </td>
                    </tr>
                  </table>
                  <br>
                  <!--
                  <table width="99%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>
                        <hr width="100%" size="1" style="color='#003399'">
                      </td>
                      <td width="99" align="center" class="black9">
                        <strong>
                          <font color="#0000FF">
                            注 意 事 项
                          </font>
                        </strong>
                      </td>
                      <td>
                        <hr width="100%" size="1" style="color='#003399'">
                      </td>
                    </tr>
                  </table>
                  <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1"
                  bordercolor="#FFFFFF" class="black9">
                    <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                      <td height="47">
                        &nbsp;&nbsp;1. 资源综合利用认定证书编号：手工录入证书编号;
                        <br>
                        &nbsp;&nbsp;2. 证书的有效期：通过日历表的形式选择证书的有效期起止日期;
                        <br>
                        &nbsp;&nbsp;3. 北京市发展和改革委员会公布资源综合利用企业认定企业名单的文件：分别录入文件名称及文件号,并按名单上资源综合利用种类的内容分行次填写“资源综合利用种类”栏，行次可无限量增加（税务机关一定要严格按照文件中的项目把关）;
                        <br>
                        &nbsp;&nbsp;4. 是否提交企业实际资源综合利用情况及分别核算资源综合利用收入的声明：选择 “是”或 “否”;
                        <br>
                        &nbsp;&nbsp;5. 综合利用资源生产符合国家产业政策规定的产品所取得的收入（单位：元）：手工录入项;
                        <br>
                        &nbsp;&nbsp;6. 减计收入额（单位：元）：该栏次自动生成，生成数据=“综合利用资源生产符合国家产业政策规定的产品所取得的收入”*10%
                        ;
                        <br>
                        <br>
                        &nbsp;&nbsp;3.1备案表各栏目为必填项，如未填写则不能向税务提交申请。
                        <br>
                        &nbsp;&nbsp;3.2 表中选择是否的条件中，有任何一条选择否，则不能向税务提交申请。
                        <br>
                        &nbsp;&nbsp;3.3 纳税人只有提交备案申请并经税务机关审核成功后方可开放“中华人民共和国企业所得税年度纳税申报表（A类）——附表五税收优惠明细表”第7行，允许在汇算清缴时填写数据，否则，对应行次系统默认为不允许编辑状态。
                        <br>
                        <br>
                        &nbsp;&nbsp;1. 审核结果中至少有一行选择为“是”才可确定“企业综合利用资源，生产符合国家产业政策规定的产品所取得的收入”备案事项审核成功，否则则视为实质性不符合，拒绝该备案项目的申请。；
                        <br>
                      </td>
                    </tr>
                  </table>
                  -->
                  <br>
                  </td>
              </tr>
            </table>
        
            </td>
        </tr>
      </table>
    </form>
  
   	<tr><td valign="bottom" align="center"><br>
	<jsp:include page="../../include/bottom.jsp" flush="true">
	</jsp:include></td>
	</tr>
  </body>

</html>
