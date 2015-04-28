<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml" %>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeTable"%>
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
			var xslPath='/XSLTWEB/model/030021/XSLT/basx21viewdetail.xsl';
		
			if (strXml != "")
		    {
		        if (! parseXml(strXml,xslPath,"result"))
		            return false;
		    }
		     return true;
		}
		
		//返回按钮
		function doReturn()
		{
			document.forms[0].action = "/shenbao/jmbaz.dc";
			document.forms[0].actionType.value="Show";
			document.forms[0].submit();
		}
		
		//查看备案表
		function doViewZb()
		{
			document.forms[0].action = "/shenbao/jmbaz.dc";
			document.forms[0].actionType.value="ViewZb";
			document.forms[0].submit();
		}
</script>

<title>
      生产和装配伤残人员专门用品企业所得税备案事项
</title>
	</head>
	
	<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"onload="parseXmlOnLoad()">
	
	<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr>
    	<td align="center" colspan=2 valign="top">
    	
    		<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    			<jsp:param name="name" value="录入纳税人企业所得税减免税备案申请" />
				<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    		</jsp:include>
    		
        	<html:errors/>
        	
        	<form name="Form1" method="post" action="/shenbao/basx21.dc">
 			<input name="actionType" type="hidden" id="actionType"></input>
      		<table width="770" border="0" cellpadding="0" cellspacing="0" align="center" >
        	<tr>
          		<td valign="top" class="title">
            	<br>
            	
            	<table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
              	
              	<tr>
                	<td class="1-td1">
                 		 录入生产和装配伤残人员专门用品企业所得税备案事项
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
                        	<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturn()" style="cursor:hand">
                      	</td>
                    </tr>
                  </table>
                  <br/>
                  
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