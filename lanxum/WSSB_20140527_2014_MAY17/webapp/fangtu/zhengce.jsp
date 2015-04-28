<%@page pageEncoding="GBK"%>
<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<style>
* {
    font-family: "宋体";
	font-size: 12px;
}

#zheng_zw {
	padding-top: 20;
}

#zheng_title {
	font-weight: bold;
}
</style>
<script type="text/javascript">
document.title = "政策正文";
</script>
<html>
<body>
	<div id="zheng_title">
		政策正文 <span style="cursor: pointer;" onclick="javascript: window.close();">[Close]</span>
	</div>
	
	<div id="zheng_zw">
		政策名称：[<bean:write name="fangtuZhengceForm" property="jmzc.jmzcmc"/>]
		<br/>
		<br/>
		<bean:write name="fangtuZhengceForm" property="jmzc.jmzczw"/>
	</div>
</body>
</html>