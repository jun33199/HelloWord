<%@page pageEncoding="GBK"%>
<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<style>
* {
    font-family: "����";
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
document.title = "��������";
</script>
<html>
<body>
	<div id="zheng_title">
		�������� <span style="cursor: pointer;" onclick="javascript: window.close();">[Close]</span>
	</div>
	
	<div id="zheng_zw">
		�������ƣ�[<bean:write name="fangtuZhengceForm" property="jmzc.jmzcmc"/>]
		<br/>
		<br/>
		<bean:write name="fangtuZhengceForm" property="jmzc.jmzczw"/>
	</div>
</body>
</html>