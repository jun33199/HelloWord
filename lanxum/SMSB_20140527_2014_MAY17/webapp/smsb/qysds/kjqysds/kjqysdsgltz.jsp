<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>

<html>
<head>
<title>扣缴企业所得税管理台帐</title>
	<link href="../../../css/text.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" type="text/JavaScript" src="../../../js/smsb_common.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="../../../js/treatImage.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="../../../js/gmit_selectcontrol.js"></script>  
</head>

<script language=JavaScript>
	
	
	// 回车查询纳税人信息
	function doJsjdmQuery()
	{
		if(event.keyCode==13) doQuery();
	}
	
	
	function doQuery()
	{
		// 扣缴人计算机代码
		if(document.forms[0].jsjdm.value == null || document.forms[0].jsjdm.value == "")
		{
			alert("请输入扣缴义务人计算机代码！");
			document.forms[0].jsjdm.focus();
			return ;
		}else{
			document.forms[0].actionType.value = "QueryTZXX";
		    document.forms[0].submit();
		 }   
	}
</script>


<%@ include file="../../include/header.jsp"%>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<html:form action="/webapp/smsb/qysds/kjqysds/dztzAction.do" method="post">
<html:hidden property="actionType" />

	<table width="1000" align="center" cellspacing="0">
	    <tr>
	        <td class="1-td1">扣缴企业所得税管理台帐 </td>
	    </tr>    
	    <tr>
	        <td class="1-td2" align="left">
	        	<div align="left">
	        		&nbsp;扣缴义务人计算机代码：<html:text property="jsjdm" size="20" maxlength="8" onkeydown="doJsjdmQuery()" onclick="this.select()"/>&nbsp;&nbsp;
	        		<input type="button" value="查询" onclick="doQuery()" />&nbsp;&nbsp;
	        		<input type="button" value="返回" onclick="tuichu();return false;" />
	        		
	        	</div>			
			</td>
	    </tr> 
	    <tr>
	        <td class="1-td2">  	        
	        	<div id="gdjl" style="position:static; overflow: auto; height: 380px; width : 1000px">	
					<%=request.getAttribute("QUERY_HTML")%>
				</div>   
	        </td>
	    </tr>

	</table>
    <br/>
<%@ include file="../../include/footernew.jsp"%>
</html:form>

</body>
</html>
