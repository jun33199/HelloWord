<%@ page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);%>

<html>
<head>
<title>无应纳税（费）款告知事项</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<script language="JavaScript" type="text/JavaScript"src="js/function.js"></script>
<script language="JavaScript" type = "text/JavaScript" src="js/gmit_selectcontrol.js"></script>
<script language="JavaScript" type="text/JavaScript"	src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript"	src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/JavaScript">
var strXml = "<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>";
var xmlDoc;
function parseXmlOnLoad()
{
	xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	xmlDoc.async = false;
	xmlDoc.loadXML(strXml);
	var text = xmlDoc.selectSingleNode("/taxdoc/gzsxtext").text;

		document.getElementById("txtgzsx").innerHTML = text;
	
    return true;
}

function doReturn()
{
	document.forms[0].action = "wsksb.dc?actionType=Return";	
	document.forms[0].submit();
}
function doWsk()
{
	document.forms[0].action = "wsksb.dc?actionType=Show";	
	document.forms[0].submit();
}

</script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<style type="text/css">
.gzsxnr{
	font-size:15pt;
}
#txtgzsx {
	line-height:30px;
}
#txtgzsx font{
	font-size:18pt;
	color:red
}
.1-td1{
	font-size:22pt;
	line-height:30px;
}
</style>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onLoad="parseXmlOnLoad();">
	 
<form name="form1" method="POST" action="/shenbao/wsksb.dc">
<input name="actionType" type="hidden" id="actionType">

<table width="97%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr height="90px">
     <td align="left" valign="top" style="font-size:20pt">
    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="告知事项" />
		<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>
	 </td>
    </tr>
    	

    
 	<!-- xsl样式表 -->
	<tr height = "300px"><td valign="top" >
	<br/>
	<table align="center" class="table-99"  width="83%" >
	  		
		<!-- write your code here--->
	        <tr height="40px">
	          <td class="1-td1" valign="center">告知事项</td>
	        </tr>
               
	        <tr height="200px">
	        	<td class="2-td2-center gzsxnr">
	   			 <div id="txtgzsx" align="left"></div>
				</td>
			</tr>
			
			
		</table>
		</td></tr>
		
    
    
    <tr height="40px" >
	  <td align="center" valign="top">
	  		<br/>
		 	<div  align="center">
		  		<table>
		    	<TR >
				<TD>
					<img src="<%=static_contextpath%>images/jixu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/jixu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/jixu1.jpg'" alt="继续" onclick="doWsk()" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
						<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturn()" style="cursor:hand">					
				</TD>
				</TR>
		    	</table>
			</div>
	   </td>
	</tr>
    
  	
	
	<!-- 底 -->
	<tr>
  	<td valign="bottom" align="center">
		
		<%@ include file="../include/bottom.jsp" %>
	  
  	</td>
	</tr>
 </table>
 </form>
</body>
</html>