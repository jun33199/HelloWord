<%@ page contentType="text/html; charset=gb2312" language="java"  errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.common.model.UserData"%>
<%@ page import="com.ttsoft.framework.util.DateTimeUtil"%>
<%@ page import="com.ttsoft.bjtax.dj.model.YHZH"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ttsoft.bjtax.dj.util.CodeTableUtil"%>
<%@ page import="com.ttsoft.bjtax.dj.util.CodeTableKey"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.web.GdzysSydjxxlrForm" %>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model.SBMXModel" %>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model.JMXXModel" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator"%>
<style type="text/css">
<!--
@import url(../css/text.css);
-->
</style>


<html:html>
<%
	response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Expires", "0");
%>

<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<title>����˰�걨��</title>
	<script language=JavaScript src="../js/treatImage.js"></script>
	<script language=JavaScript src="../js/compute.js"></script>
	<script language=JavaScript src="../js/smsb_common.js"></script>
	<script language=JavaScript src="../js/DTable.js"></script>
	<script language=JavaScript src="../js/zhsb.js"></script>
	<script language=JavaScript src="../js/treatImage.js"></script>
	<script language=JavaScript src="../js/compute.js"></script>
	<script language=JavaScript src="../js/function.js"></script>
	<script language=JavaScript src="../js/smsb_common.js"></script>
	<script language=JavaScript src="../js/Bolan.js"></script>
	<script language=JavaScript src="../js/MathString.js"></script>
	<script language=JavaScript src="../js/Stack.js"></script>
	<script language=JavaScript src="../js/InputSelect.js"></script>
	
	<style>
<!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
.font019664
	{color:windowtext;
	font-size:8.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font519664
	{color:windowtext;
	font-size:8.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font619664
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font719664
	{color:windowtext;
	font-size:18.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.xl1519664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl6519664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl6619664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl6719664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl6819664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl6919664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl7019664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl7119664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7219664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7319664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:top;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl7419664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:12.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7519664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:top;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl7619664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;
	mso-text-control:shrinktofit;}
.xl7719664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl7819664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl7919664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl8019664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:none;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl8119664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl8219664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl8319664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:top;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl8419664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl8519664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:top;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl8619664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:top;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl8719664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:top;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl8819664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl8919664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:none;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl9019664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl9119664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl9219664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl9319664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:right;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl9419664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:right;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl9519664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:none;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl9619664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:none;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl9719664
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
ruby
	{ruby-align:left;}
rt
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-char-type:none;}
-->
</style>
<style>
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
.font55668
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.xl155668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl655668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;
	}
.xl665668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl675668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl685668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl695668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
	
.xl705668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl715668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:none;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl725668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
text-align:left;
	vertical-align:top;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl735668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
text-align:left;
	vertical-align:top;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl745668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:top;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl755668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:right;
	vertical-align:bottom;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl765668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:right;
	vertical-align:bottom;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl775668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:bottom;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl785668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl795668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl805668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl815668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl825668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl835668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl845668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl855668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl865668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl875668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;
	mso-text-control:shrinktofit;}
.xl885668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;
	mso-text-control:shrinktofit;}
.xl895668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:right;
	vertical-align:bottom;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl905668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl915668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:12.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl925668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl935668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl945668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:none;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl955668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:none;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl965668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:none;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl975668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl985668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl995668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl1005668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:8.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:right;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl1015668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:right;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl1025668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:top;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl1035668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:none;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl1045668
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
ruby
	{ruby-align:left;}
rt
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:����;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-char-type:none;}
--></style>


</head>
<style>
@media print {
	INPUT {
		display: none
	}
}
</style>

<style>
@media Print {
	.Noprn {
		DISPLAY: none
	}
}
</style>
  <object ID='wb' WIDTH=0 HEIGHT=0 CLASSID='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2'></object> 
<script>
function printsetup(){ 
	//��ӡҳ������ 
	wb.execwb(8,1); 
} 

function printpreview(){ 
	//��ӡҳ��Ԥ�� 
	PageSetup_Null(); 
	wb.execwb(7,1); 
} 

function printit() 
{ 
if (confirm('ȷ����ӡ��')) { 
	for(var i=0;i<2;i++){
		PageSetup_Null(); 
		wb.execwb(6,6);	
	}
  
} 
}

var HKEY_Root,HKEY_Path,HKEY_Key; 
HKEY_Root="HKEY_CURRENT_USER"; 
HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\"; 
//������ҳ��ӡ��ҳüҳ��Ϊ�� 
function PageSetup_Null() 
	{ 
	try 
		{ 
		var Wsh=new ActiveXObject("WScript.Shell"); 
		HKEY_Key="header"; 
		Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); 
		HKEY_Key="footer"; 
		Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); 
		
		} 
	catch(e){
		
	} 
}




function doprint(){
	PageSetup_Null();
	var dy = document.getElementById("dayin");
	var fh = document.getElementById("goback");
	dy.style.display="none";
	fh.style.display="none";
	for(var i=0;i<2;i++){
		PageSetup_Null();
	      window.print();	
	      //PageSetup_Null(); 
	      //wb.execwb(6,6);
	}
	dy.style.display="inline";
	fh.style.display="inline";
}


function goback(){
	document.forms[0].actionType.value = "goBack";
	document.forms[0].submit();
}

</script>
<%
 	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm)request.getAttribute("sydjxxlrForm");
 %>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0">
	<html:form action="/webapp/smsb/sydjxxlrAction.do" method="post">
	<html:hidden property="actionType"/>
	</html:form>
	<div id="" align=center x:publishsource="Excel">
	
	<% 
	if("0".equals(form.getSylxdm())){
		%>
<table border=0 cellpadding=0 cellspacing=0 style='border-collapse:collapse;table-layout:fixed;width:98%'>
 
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=8  class=xl7419664 style='height:20pt;
  width:98%'>�����еط�˰��ָ���ռ��˰��˰�걨��</td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=8  class=xl9319664 style='height:20pt;
  width:85%'><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span><font class="font519664">��λ�� Ԫ�������ǡ��֣�<br>
    </font><font class="font019664">�걨ʱ�䣺<span style='mso-spacerun:yes'>&nbsp;&nbsp;
  <%=form.getNian() %></span>��<span style='mso-spacerun:yes'>&nbsp;&nbsp;<%=form.getYue() %></span>��<span
  style='mso-spacerun:yes'>&nbsp;&nbsp;<%=form.getTian() %></span>��<span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></font><font class="font519664">ƽ���ף�������С�������λ��</font></td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl6819664 style='height:20pt;border-bottom:none;'>��˰������</td>
  <td colspan=6 class=xl6819664 width=660 style="word-wrap:break-word;word-break:break-all;border-left:none;border-bottom:none;"><bean:write name="sydjxxlrForm" property="nsrmc"/>��</td>
 </tr>
  <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl6819664 style='height:20pt'>��˰����ϸ��ַ</td>
  <td colspan=6 class=xl6819664 style='border-left:none' width=660 style="word-wrap:break-word;word-break:break-all;"><bean:write name="sydjxxlrForm" property="nsrxxdz"/>��</td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl9019664 style='border-right:.5pt solid black;
  height:20pt;border-bottom:none;'>���֤������</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="sfzzlxmc"/>��</td>
  <td colspan=2 class=xl6819664 style='border-top:none;border-left:none'>���֤�պ���</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="sfzzhm"/></td>
 </tr>
 <tr  style='mso-height-source:userset;height:20pt'>
  <td colspan=2  class=xl6819664 style='height:20pt;border-bottom:none;'>��˰��������ҵ</td>
  <td colspan=2 class=xl6819664 style="word-wrap:break-word;word-break:break-all;border-left:none;border-bottom:none;" align=center ><bean:write name="sydjxxlrForm" property="nsrsshymc"/>��</td>
  <td colspan=2 class=xl6919664 width=161 style='border-top:none;border-left:none;
  width:121pt'>��ҵ�Ǽ�ע������</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="qydjzclxmc"/></td>
 </tr>
 <tr  style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl9019664 style='border-right:.5pt solid black;border-bottom:none;
  height:20pt'>˰����������</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="jsjdm"/>��</td>
  <td colspan=2 class=xl6919664 width=161 style='border-top:none;border-left:none;
  width:121pt'>��˰��ʶ���</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="nsrsbh"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 height=42 class=xl9019664 style='border-right:.5pt solid black;
  height:20pt'>��������</td>
  <td colspan=2 class=xl6819664 style="word-wrap:break-word;word-break:break-all;border-left:none" align=center><bean:write name="sydjxxlrForm" property="khyh"/>��</td>
  <td colspan=2 class=xl6919664 width=161 style='border-top:none;border-left:none;
  width:121pt'>�����˺�</td>
  <td colspan=2 class=xl6819664 style='border-left:none'><bean:write name="sydjxxlrForm" property="yhzh"/>��</td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl9019664 style='border-right:.5pt solid black;
  height:20pt;border-bottom:none;'>��ϵ������</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="lxrxm"/></td>
  <td colspan=2 class=xl6919664 width=161 style='border-top:none;border-left:none;
  width:121pt'>��ϵ�绰</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="lxdh"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl9019664 style='border-right:.5pt solid black;border-bottom:none;
  height:20pt'>��׼ռ���ĺ�</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="pzjdwh"/>��</td>
  <td colspan=2 class=xl6919664 width=161 style='border-top:none;border-left:none;
  width:121pt'>ռ��ʱ��</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="zdsj"/>��</td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl6819664 style='height:20pt'>��׼ռ�����</td>
  <td colspan=2 class=xl6819664 style='border-left:none'><bean:write name="sydjxxlrForm" property="pzjdmj"/>��</td>
  <td colspan=2 class=xl6919664 width=161 style='border-top:none;border-left:none;
  width:121pt'>ʵ��ռ�����</td>
  <td colspan=2 class=xl6819664 style='border-left:none'>��<bean:write name="sydjxxlrForm" property="sjzdmj"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 height=20 class=xl9019664 style='border-right:.5pt solid black;border-bottom:none;
  height:20pt'>���������ַ</td>
  <td colspan=6 class=xl9019664 style='border-right:.5pt solid black;
  border-left:none' style="word-wrap:break-word;word-break:break-all;border-bottom:none;"><bean:write name="sydjxxlrForm" property="tdzldz"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl6819664 style='height:20pt'>������Ŀ����</td>
  <td colspan=6 class=xl6819664 style='border-left:none' style="word-wrap:break-word;word-break:break-all;"><bean:write name="sydjxxlrForm" property="jsxmmc"/></td>
 </tr>
 
 
 
 
 
 
 
 
 
 <tr>
<td colspan=8>
<table  border=0 cellpadding=1 cellspacing=0 style='border-collapse:collapse;table-layout:fixed;'>
 <tr style='mso-height-source:userset;height:20pt'>
  <td class=xl7019664 style='height:20pt;width:13%;border-top:none' nowrap=true>ռ����;</td>
  <td class=xl7619664 width=13% style='border-top:none;border-left:none' >����˰��<br>��Ԫ/ƽ���ף�</td>
  <td class=xl7019664 width=13% style='border-top:none;border-left:none' nowrap=true >��˰���</td>
  <td class=xl7119664 style='border-top:none;border-left:none' nowrap=true width=16%>����˰��</td>
  <td class=xl7019664 width=13% style='border-top:none;border-left:none;' nowrap=true >�������<span style='mso-spacerun:yes'>&nbsp;&nbsp;</span></td>
  <td class=xl7119664 style='border-top:none;border-left:none' nowrap=true width=16%>����˰��</td>
  <td class=xl7119664 style='border-top:none;border-left:none' nowrap=true width=16%>Ӧ��˰��</td>
 </tr>
   <%
 Map map = form.getSbmx_map();
 List jt = (List)map.get("00");
 for(int i=0;i<jt.size();i++){
	 SBMXModel model = (SBMXModel)jt.get(i);
	 %>
	 <tr style='mso-height-source:userset;height:20pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:20pt'>��ͨ������ʩ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=center  ><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right ><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right ><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right ><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right ><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right ><%=model.getYnse() %></td>
	 </tr> 
	<%	 
 	}
 	%>
	 
	 <%
 	List gy = (List)map.get("01");
 	for(int i=0;i<gy.size();i++){
	 SBMXModel model = (SBMXModel)gy.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:20pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:20pt' nowrap=true>��ҵ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
	 
  <%
 	List sy = (List)map.get("02");
 	for(int i=0;i<sy.size();i++){
	 SBMXModel model = (SBMXModel)sy.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:20pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:20pt' nowrap=true>��ҵ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
	
	<%
 	List zz = (List)map.get("03");
 	for(int i=0;i<zz.size();i++){
	 SBMXModel model = (SBMXModel)zz.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:20pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:20pt' nowrap=true>סլ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
	
	<%
 	List jm = (List)map.get("04");
 	for(int i=0;i<jm.size();i++){
	 SBMXModel model = (SBMXModel)jm.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:20pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:20pt' nowrap=true>ũ�����סլ</td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
 <%
 	List qt = (List)map.get("05");
 	for(int i=0;i<qt.size();i++){
	 SBMXModel model = (SBMXModel)qt.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:20pt'>
	  <td colspan=1 class=xl8819664  style='border-right:.5pt solid black;
	  height:20pt' nowrap=true>����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
 
 
 
 
 
 <tr style='mso-height-source:userset;height:20.0pt'>
  <td colspan=1  class=xl8819664 style='border-right:.5pt solid black;border-bottom:.5pt solid black;
  height:20.0pt;border-bottom:none' nowrap=true>�ϼ�</td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:none' nowrap=true align=center>&nbsp;</td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:none' nowrap=true align=right>&nbsp;<%=form.getHj_jsmj() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:none' nowrap=true align=right>&nbsp;<%=form.getHj_jzse() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:none' nowrap=true align=right>&nbsp;<%=form.getHj_jmmj() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:none' nowrap=true align=right>&nbsp;<%=form.getHj_jmse() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:none' nowrap=true align=right>&nbsp;<%=form.getHj_ynse() %></td>
 </tr>
 </table>
 </td>
</tr>

 
 <tr style='mso-height-source:userset;height:30.0pt'>
  <td colspan=8  class=xl7819664 width=919 style='border-right:.5pt solid black;
  height:30.0pt'>����������������˰�걨���Ǹ��ݡ��л����񹲺͹�����ռ��˰�����������Ĺ涨��ģ���ȷ��������ʵ�ġ��ɿ��ġ������ġ�������������������������������������������������������������������������������������������������������������</td>
 </tr>
 <tr class=xl6519664 style='mso-height-source:userset;height:30pt'>
  <td colspan=8 class=xl9519664 style='border-right:.5pt solid black;
  height:30pt'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>��˰��ǩ�֣�<span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span>��˰�˸��£�</td>
 </tr>
 <tr  style='mso-height-source:userset;height:20pt'>
  <td colspan=8 class=xl8119664 style='height:20pt'>������˰�������д</td>
 </tr>
 <tr style='mso-height-source:userset;height:60pt'>
  <td colspan=2  class=xl8319664 width=145 style='border-right:.5pt solid black;border-top:none;
  height:60pt'><span style='mso-spacerun:yes'>&nbsp;</span>˰�����������ǩ��</td>
  <td colspan=2 class=xl7319664 style='border-top:none;border-left:none;border-right:none'>˰�������������</td>
  <td class=xl7519664  style='border-top:none;border-left:none'><span style='mso-spacerun:yes'>&nbsp;</span></td>
  <td colspan=3 class=xl8519664 style='border-right:.5pt solid black;border-top:none;
  border-left:none'>˰�����ͬ�������걨<br>
    <br>
    <br>
    <br>
    <span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span>�����£�</td>
 </tr>
</table>
		<%
	}else if("1".equals(form.getSylxdm())){
		%>
		<%	

if("".equals(form.getJmlbdm_jmmx()) || form.getJmlbdm_jmmx()==null || form.getJmlbdm_jmmx().length<1){
	}else{
	%>
	<table border=0 cellpadding=0 cellspacing=0 style='border-collapse:collapse;table-layout:fixed;width:98%'>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=10 class=xl915668 style='height:20pt; width:98%'>�����еط�˰��ָ���ռ��˰�����걨��</td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=10 class=xl1005668 style='height:20pt;
  width:85%pt'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span>��λ�� Ԫ�������ǡ��֣�<br>
    �걨ʱ�䣺<span style='mso-spacerun:yes'>&nbsp;&nbsp;
  <%=form.getNian() %></span>��<span style='mso-spacerun:yes'>&nbsp;&nbsp;<%=form.getYue() %></span>��<span
  style='mso-spacerun:yes'>&nbsp;&nbsp;<%=form.getTian() %></span>��<span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span>ƽ���ף�������С�������λ��</td>
 </tr>
 <tr  style='mso-height-source:userset;height:18pt'>
  <td colspan=2 class=xl985668 style='height:18pt'>��˰������</td>
  <td colspan=8 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'><bean:write name="sydjxxlrForm" property="nsrmc"/>��</td>
 </tr>
 <tr  style='mso-height-source:userset;height:18pt'>
  <td colspan=2  class=xl985668 style='height:18pt;border-bottom:none'>��˰����ϸ��ַ</td>
  <td colspan=8 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'><bean:write name="sydjxxlrForm" property="nsrxxdz"/>��</td>
 </tr>
 <tr style='mso-height-source:userset;height:18.0pt;border-bottom:none'>
  <td colspan=2 class=xl985668 style='height:18pt;border-bottom:none'>���֤������</td>
  <td colspan=3 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'>��<bean:write name="sydjxxlrForm" property="sfzzlxmc"/>��</td>
  <td colspan=2 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'>���֤�պ���</td>
  <td colspan=3 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'>��<bean:write name="sydjxxlrForm" property="sfzzhm"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt'>
  <td colspan=2 class=xl805668 style='border-right:.5pt solid black;
  height:18pt'>��˰��������ҵ</td>
  <td colspan=3 class=xl805668 style="word-wrap:break-word;word-break:break-all;border-right:.5pt solid black;border-left:none" align=center><bean:write name="sydjxxlrForm" property="nsrsshymc"/></td>
  <td colspan=2 class=xl835668 style='border-right:.5pt solid black;border-bottom:none;border-left:none;' align=center>��ҵ�Ǽ�ע������</td>
  <td colspan=3 class=xl835668 style='border-right:.5pt solid black;border-bottom:none;border-left:none;' align=center><bean:write name="sydjxxlrForm" property="qydjzclxmc"/>��</td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt'>
  <td colspan=2 class=xl985668 style='height:18pt;border-bottom:none'>˰����������</td>
  <td colspan=3 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'>��<bean:write name="sydjxxlrForm" property="jsjdm"/></td>
  <td colspan=2 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none' align=center>��˰��ʶ���</td>
  <td colspan=3 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none' align=center><bean:write name="sydjxxlrForm" property="nsrsbh"/>��</td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt;border-bottom:none'>
  <td colspan=2 class=xl985668 style='height:18pt;border-bottom:none'>��������</td>
  <td colspan=3 class=xl805668 style="word-wrap:break-word;word-break:break-all;border-right:.5pt solid black;border-left:none" align=center><bean:write name="sydjxxlrForm" property="khyh"/></td>
  <td colspan=2 class=xl835668 style='border-right:.5pt solid black;border-bottom:none;
  border-left:none;' align=center>�����˺�</td>
  <td colspan=3 class=xl835668 style='word-wrap:break-word;word-break:break-all;border-right:.5pt solid black;border-left:none;border-bottom:none' align=center><bean:write name="sydjxxlrForm" property="yhzh"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt'>
  <td colspan=2  class=xl985668 style='height:18pt;border-bottom:none'>��ϵ������</td>
  <td colspan=3 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'>��<bean:write name="sydjxxlrForm" property="lxrxm"/></td>
  <td colspan=2 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none' align=center>��ϵ�绰</td>
  <td colspan=3 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none' align=center>��<bean:write name="sydjxxlrForm" property="lxdh"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt'>
  <td colspan=2 class=xl985668 style='height:18pt;border-bottom:none' align=center>��׼ռ���ĺ�</td>
  <td colspan=3 class=xl805668 style='border-right:.5pt solid black;border-left:
  none;border-bottom:none'>��<bean:write name="sydjxxlrForm" property="pzjdwh"/></td>
  <td colspan=2 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none;' align=center>ռ��ʱ��</td>
  <td colspan=3 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none;' align=center><bean:write name="sydjxxlrForm" property="zdsj"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt;border-bottom:none'>
  <td colspan=2 class=xl985668 style='height:18pt;border-bottom:none'>��׼ռ�����</td>
  <td colspan=3 class=xl805668 style='border-right:.5pt solid black;border-bottom:none;border-left:
  none'>��<bean:write name="sydjxxlrForm" property="pzjdmj"/></td>
  <td colspan=2 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none' align=center>ʵ��ռ�����</td>
  <td colspan=3 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none' align=center><bean:write name="sydjxxlrForm" property="sjzdmj"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt'>
  <td colspan=2 class=xl985668 style='height:18pt;border-bottom:none'>���������ַ</td>
  <td colspan=8 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'>��<bean:write name="sydjxxlrForm" property="tdzldz"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt'>
  <td colspan=2 class=xl985668 style='height:18pt'>������Ŀ����</td>
  <td colspan=8 class=xl805668 style='border-right:.5pt solid black;border-left:
  none;border-bottom:.5pt solid black'>��<bean:write name="sydjxxlrForm" property="jsxmmc"/></td>
 </tr>
 
 
 
 
 
<tr>
<td colspan=10>
<table  border=0 cellpadding=1 cellspacing=0 style='border-collapse:collapse;table-layout:fixed;'>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=1 class=xl7019664 style='height:20pt;width:16%;border-top:none;border-bottom:none;font-size:9pt' nowrap=true>ռ����;</td>
  <td class=xl7619664 width=14% style='border-top:none;border-left:none;font-size:9pt' nowrap=true>����˰��<br>
    ��Ԫ/ƽ���ף�</td>
  <td class=xl7019664 width=13% style='border-top:none;border-left:none;font-size:9pt' nowrap=true >��˰���</td>
  <td class=xl7119664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true width=15%>����˰��</td>
  <td class=xl7019664 width=13% style='border-top:none;border-left:none;font-size:9pt' nowrap=true >�������<span style='mso-spacerun:yes'>&nbsp;&nbsp;</span></td>
  <td class=xl7119664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true width=14%>����˰��</td>
  <td class=xl7119664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true width=15%>Ӧ��˰��</td>
 </tr>
   <%
 Map map2 = form.getSbmx_map();
 List jt2 = (List)map2.get("00");
 for(int i=0;i<jt2.size();i++){
	 SBMXModel model = (SBMXModel)jt2.get(i);
	 %>
	 <tr style='mso-height-source:userset;height:18pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:18pt;font-size:9pt' nowrap=true>��ͨ������ʩ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=center  ><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right ><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right ><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right ><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right ><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right ><%=model.getYnse() %></td>
	 </tr> 
	<%	 
 	}
 	%>
	 
	 <%
 	List gy2 = (List)map2.get("01");
 	for(int i=0;i<gy2.size();i++){
	 SBMXModel model = (SBMXModel)gy2.get(i);
	 %>
	 <tr style='mso-height-source:userset;height:18pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:18pt;font-size:9pt' nowrap=true>��ҵ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
	 
  <%
 	List sy2 = (List)map2.get("02");
 	for(int i=0;i<sy2.size();i++){
	 SBMXModel model = (SBMXModel)sy2.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:18pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:18pt;font-size:9pt' nowrap=true>��ҵ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
	
	<%
 	List zz2 = (List)map2.get("03");
 	for(int i=0;i<zz2.size();i++){
	 SBMXModel model = (SBMXModel)zz2.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:18pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:18pt;font-size:9pt' nowrap=true>סլ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
	
	<%
 	List jm2 = (List)map2.get("04");
 	for(int i=0;i<jm2.size();i++){
	 SBMXModel model = (SBMXModel)jm2.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:18pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:18pt;font-size:9pt' nowrap=true>ũ�����סլ</td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
 <%
 	List qt2 = (List)map2.get("05");
 	for(int i=0;i<qt2.size();i++){
	 SBMXModel model = (SBMXModel)qt2.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:18pt'>
	  <td colspan=1 class=xl8819664  style='border-right:.5pt solid black;
	  height:18pt;font-size:9pt' nowrap=true>����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
 
 
 
 
 
 <tr style='mso-height-source:userset;height:18.0pt'>
  <td colspan=1  class=xl8819664 style='border-right:.5pt solid black;border-bottom:.5pt solid black;
  height:18.0pt;border-bottom:.5pt solid black;font-size:9pt' nowrap=true>�ϼ�</td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:.5pt solid black;font-size:9pt' nowrap=true align=center>&nbsp;</td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:.5pt solid black;font-size:9pt' nowrap=true align=right>&nbsp;<%=form.getHj_jsmj() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:.5pt solid black;font-size:9pt' nowrap=true align=right>&nbsp;<%=form.getHj_jzse() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:.5pt solid black;font-size:9pt' nowrap=true align=right>&nbsp;<%=form.getHj_jmmj() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:.5pt solid black;font-size:9pt' nowrap=true align=right>&nbsp;<%=form.getHj_jmse() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:.5pt solid black;font-size:9pt' nowrap=true align=right>&nbsp;<%=form.getHj_ynse() %></td>
 </tr>
 </table>
 </td>
</tr>
 
 
 
 <tr>
 <td colspan=10>
<table  border=0 cellpadding=1 cellspacing=0 style='border-collapse:collapse;table-layout:fixed;width="100%"'>
 <tr>
  <td class=xl655668 style='height:20pt;border-top:none;' >����˰���</td>
  <td class=xl655668 style='border-top:none;border-left:none' align=center>������ʩռ��</td>
  <td class=xl655668 style='border-top:none;border-left:none' width="8%" align=center>ũ�����ס��</td>
  <td class=xl695668 style='border-top:none;border-left:none' width="8%" align=center>ũ�����Ѽ���</td>
  <td class=xl665668 style='border-top:none'>����Ժ</td>
  <td class=xl685668 style='border-top:none;'>ҽԺ</td>
  <td class=xl685668 style='border-top:none'>�׶�԰</td>
  <td class=xl655668 style='border-top:none;border-left:none' align=center>ѧУ</td>
  <td class=xl655668 style='border-top:none;border-left:none' align=center>����</td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td class=xl655668 style='height:20pt;border-bottom:none'>��������˰���</td>
  <%
  Map map3 = (Map)form.getJmmx_map();
  JMXXModel jsmodel = (JMXXModel)map3.get("00");
  JMXXModel ncmodel = (JMXXModel)map3.get("01");
  JMXXModel knmodel = (JMXXModel)map3.get("02");
  JMXXModel ylymodel = (JMXXModel)map3.get("03");
  JMXXModel yymodel = (JMXXModel)map3.get("04");
  JMXXModel yeymodel = (JMXXModel)map3.get("05");
  JMXXModel xxmodel = (JMXXModel)map3.get("06");
  JMXXModel qtmodel = (JMXXModel)map3.get("07");
  
  if("".equals(jsmodel.getFljmse()) || jsmodel.getFljmse() == null){
	  jsmodel.setFljmse("0");
  }
  if("".equals(ncmodel.getFljmse()) || ncmodel.getFljmse() == null){
	  ncmodel.setFljmse("0");
  }
  if("".equals(knmodel.getFljmse()) || knmodel.getFljmse() == null){
	  knmodel.setFljmse("0");
  }
  if("".equals(ylymodel.getFljmse()) || ylymodel.getFljmse() == null){
	  ylymodel.setFljmse("0");
  }
  if("".equals(yymodel.getFljmse()) || yymodel.getFljmse() == null){
	  yymodel.setFljmse("0");
  }
  if("".equals(yeymodel.getFljmse()) || yeymodel.getFljmse() == null){
	  yeymodel.setFljmse("0");
  }
  if("".equals(xxmodel.getFljmse()) || xxmodel.getFljmse() == null){
	  xxmodel.setFljmse("0");
  }
  if("".equals(qtmodel.getFljmse()) || qtmodel.getFljmse() == null){
	  qtmodel.setFljmse("0");
  }
  
  
  if("".equals(jsmodel.getFljmmj()) || jsmodel.getFljmmj() == null){
	  jsmodel.setFljmmj("0");
  }
  if("".equals(ncmodel.getFljmmj()) || ncmodel.getFljmmj() == null){
	  ncmodel.setFljmmj("0");
  }
  if("".equals(knmodel.getFljmmj()) || knmodel.getFljmmj() == null){
	  knmodel.setFljmmj("0");
  }
  if("".equals(ylymodel.getFljmmj()) || ylymodel.getFljmmj() == null){
	  ylymodel.setFljmmj("0");
  }
  if("".equals(yymodel.getFljmmj()) || yymodel.getFljmmj() == null){
	  yymodel.setFljmmj("0");
  }
  if("".equals(yeymodel.getFljmmj()) || yeymodel.getFljmmj() == null){
	  yeymodel.setFljmmj("0");
  }
  if("".equals(xxmodel.getFljmmj()) || xxmodel.getFljmmj() == null){
	  xxmodel.setFljmmj("0");
  }
  if("".equals(qtmodel.getFljmmj()) || qtmodel.getFljmmj() == null){
	  qtmodel.setFljmmj("0");
  }
 

  %>
  
  
  <td class=xl675668 style='border-top:none;border-left:none;border-right:.5pt solid black;WORD-WRAP: break-word' align=right><%=jsmodel.getFljmmj() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;border-right:.5pt solid black;WORD-WRAP: break-word' align=right><%=ncmodel.getFljmmj() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;border-right:none;WORD-WRAP: break-word' align=right><%=knmodel.getFljmmj() %></td>
  <td class=xl675668 style='border-top:none;WORD-WRAP: break-word' align=right><%=ylymodel.getFljmmj() %></td>
  <td class=xl705668 style='border-top:none;WORD-WRAP: break-word' align=right><%=yymodel.getFljmmj() %></td>
  <td class=xl675668 style='border-top:none;border-right:.5pt solid black;WORD-WRAP: break-word' align=right><%=yeymodel.getFljmmj() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;border-right:.5pt solid black;WORD-WRAP: break-word' align=right><%=xxmodel.getFljmmj() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;WORD-WRAP: break-word' align=right><%=qtmodel.getFljmmj() %></td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td class=xl675668 style='height:20pt;border-right:.5pt solid black;' align=center>��������˰˰��</td>
  <td class=xl675668 style='border-top:none;border-left:none;border-right:.5pt solid black;WORD-WRAP: break-word'  align=right><%=jsmodel.getFljmse() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;border-right:.5pt solid black;WORD-WRAP: break-word' align=right><%=ncmodel.getFljmse() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;border-right:none;WORD-WRAP: break-word' align=right><%=knmodel.getFljmse() %></td>
  <td class=xl675668 style='border-top:none;border-right:none;WORD-WRAP: break-word' align=right><%=ylymodel.getFljmse() %></td>
  <td class=xl675668 style='border-top:none;WORD-WRAP: break-word' align=right><%=yymodel.getFljmse() %></td>
  <td class=xl675668 style='border-top:none;border-right:.5pt solid black;WORD-WRAP: break-word' align=right><%=yeymodel.getFljmse() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;border-right:.5pt solid black;WORD-WRAP: break-word'  align=right><%=xxmodel.getFljmse() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;WORD-WRAP: break-word'  align=right><%=qtmodel.getFljmse() %></td>
 </tr>
 </table>
 </td>
 </tr>
 <tr style='mso-height-source:userset;height:40pt;border-bottom:none'>
  <td colspan=2 class=xl1035668 style='height:40pt;border-bottom:.5pt solid black;border-top:none;border-bottom:none' align=center>�걨����˰����</td>
  <td colspan=8 class=xl785668 style='border-right:.5pt solid black;border-left:none;border-top:none;border-bottom:none;font-size:9pt'><bean:write name="sydjxxlrForm" property="jmsyj"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=10 class=xl925668 style='border-right:.5pt solid black;
  height:20pt;width:75%'>�������������˼����걨���Ǹ��ݡ��л����񹲺͹�����ռ��˰�����������Ĺ涨��ģ���ȷ��������ʵ�ġ��ɿ��ġ������ġ�������������������������������������������������������������������������������������������������������������</td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=10 class=xl955668 style='border-right:.5pt solid black;border-bottom:none;
  height:20pt;width:75%'>��˰��ǩ�֣�<span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span>��˰�˸��£�</td>
 </tr>
 <tr style='mso-height-source:userset;height:15pt'>
  <td colspan=10 class=xl905668 style='height:15pt;border-bottom:none;
  width:75%'>����������˰�������д</td>
 </tr>
 <tr style='mso-height-source:userset;height:40pt'>
  <td colspan=4 class=xl725668 style='border-right:.5pt solid black;border-bottom:none;
  height:40pt;width:284pt'>˰�����������ǩ��ͬ�⣺</td>
  <td colspan=3 class=xl725668  style='border-right:.5pt solid black;border-bottom:none;
  border-left:none;width:245pt'>˰����������ǩ��ͬ�⣺ ��������������</td>
  <td colspan=3 class=xl735668 style='border-right:.5pt solid black;border-bottom:none;
  width:222pt'>˰�����ͬ���������˰�걨�����£���</td>
 </tr>
 <tr style='mso-height-source:userset;height:15pt;border-bottom:none'>
  <td colspan=4 class=xl895668 width=378 style='height:15pt;
  width:284pt'>�ꡡ���¡�����</td>
  <td colspan=3 class=xl755668 width=327 style='border-right:.5pt solid black;
  border-left:none;width:245pt;height:15pt'>�ꡡ���¡�����</td>
  <td colspan=3 class=xl765668 width=294 style='border-right:.5pt solid black;
  width:222pt;height:15pt'>���ꡡ���¡�����</td>
 </tr>
 <tr style='mso-height-source:userset;height:15pt'>
  <td colspan=10 class=xl905668 style='height:20pt;border-top:none;border-bottom:none;
  width:75%'>�������о�˰�������д</td>
 </tr>
 <tr style='mso-height-source:userset;height:40pt'>
  <td colspan=4 class=xl1025668 width=378 style='height:40pt;
  width:28%'>���������</td>
  <td colspan=3 class=xl725668 style='border-right:.5pt solid black;
  border-left:none;width:25%'>���������</td>
  <td colspan=3 class=xl735668 style='border-right:.5pt solid black;
  width:222pt'>��������</td>
 </tr>
 <tr style='mso-height-source:userset;height:15pt'>
  <td colspan=4 class=xl895668 style='height:15pt;border-top:none;
  width:28%'>�ꡡ���¡�����</td>
  <td colspan=3 class=xl755668 style='border-right:.5pt solid black;border-top:none;
  border-left:none;width:25%;height:15pt'>�ꡡ���¡�����</td>
  <td colspan=3 class=xl765668  style='border-right:.5pt solid black;border-top:none;
  width:22%;height:15pt'>�ꡡ���¡�����</td>
 </tr>
</table>

<%
	}
%>
		<%
	}else{
		%>
<table border=0 cellpadding=0 cellspacing=0 style='border-collapse:collapse;table-layout:fixed;width:98%'>
 
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=8  class=xl7419664 style='height:20pt;
  width:98%'>�����еط�˰��ָ���ռ��˰��˰�걨��</td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=8  class=xl9319664 style='height:20pt;
  width:85%'><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span><font class="font519664">��λ�� Ԫ�������ǡ��֣�<br>
    </font><font class="font019664">�걨ʱ�䣺<span style='mso-spacerun:yes'>&nbsp;&nbsp;
  <%=form.getNian() %></span>��<span style='mso-spacerun:yes'>&nbsp;&nbsp;<%=form.getYue() %></span>��<span
  style='mso-spacerun:yes'>&nbsp;&nbsp;<%=form.getTian() %></span>��<span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></font><font class="font519664">ƽ���ף�������С�������λ��</font></td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl6819664 style='height:20pt;border-bottom:none;'>��˰������</td>
  <td colspan=6 class=xl6819664 width=660 style="word-wrap:break-word;word-break:break-all;border-left:none;border-bottom:none;"><bean:write name="sydjxxlrForm" property="nsrmc"/>��</td>
 </tr>
  <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl6819664 style='height:20pt'>��˰����ϸ��ַ</td>
  <td colspan=6 class=xl6819664 style='border-left:none' width=660 style="word-wrap:break-word;word-break:break-all;"><bean:write name="sydjxxlrForm" property="nsrxxdz"/>��</td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl9019664 style='border-right:.5pt solid black;
  height:20pt;border-bottom:none;'>���֤������</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="sfzzlxmc"/>��</td>
  <td colspan=2 class=xl6819664 style='border-top:none;border-left:none'>���֤�պ���</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="sfzzhm"/></td>
 </tr>
 <tr  style='mso-height-source:userset;height:20pt'>
  <td colspan=2  class=xl6819664 style='height:20pt;border-bottom:none;'>��˰��������ҵ</td>
  <td colspan=2 class=xl6819664 style="word-wrap:break-word;word-break:break-all;border-left:none;border-bottom:none;" align=center ><bean:write name="sydjxxlrForm" property="nsrsshymc"/>��</td>
  <td colspan=2 class=xl6919664 width=161 style='border-top:none;border-left:none;
  width:121pt'>��ҵ�Ǽ�ע������</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="qydjzclxmc"/></td>
 </tr>
 <tr  style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl9019664 style='border-right:.5pt solid black;border-bottom:none;
  height:20pt'>˰����������</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="jsjdm"/>��</td>
  <td colspan=2 class=xl6919664 width=161 style='border-top:none;border-left:none;
  width:121pt'>��˰��ʶ���</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="nsrsbh"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 height=42 class=xl9019664 style='border-right:.5pt solid black;
  height:20pt'>��������</td>
  <td colspan=2 class=xl6819664 style="word-wrap:break-word;word-break:break-all;border-left:none" align=center><bean:write name="sydjxxlrForm" property="khyh"/>��</td>
  <td colspan=2 class=xl6919664 width=161 style='border-top:none;border-left:none;
  width:121pt'>�����˺�</td>
  <td colspan=2 class=xl6819664 style='border-left:none'><bean:write name="sydjxxlrForm" property="yhzh"/>��</td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl9019664 style='border-right:.5pt solid black;
  height:20pt;border-bottom:none;'>��ϵ������</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="lxrxm"/></td>
  <td colspan=2 class=xl6919664 width=161 style='border-top:none;border-left:none;
  width:121pt'>��ϵ�绰</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="lxdh"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl9019664 style='border-right:.5pt solid black;border-bottom:none;
  height:20pt'>��׼ռ���ĺ�</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="pzjdwh"/>��</td>
  <td colspan=2 class=xl6919664 width=161 style='border-top:none;border-left:none;
  width:121pt'>ռ��ʱ��</td>
  <td colspan=2 class=xl6819664 style='border-left:none;border-bottom:none;'><bean:write name="sydjxxlrForm" property="zdsj"/>��</td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl6819664 style='height:20pt'>��׼ռ�����</td>
  <td colspan=2 class=xl6819664 style='border-left:none'><bean:write name="sydjxxlrForm" property="pzjdmj"/>��</td>
  <td colspan=2 class=xl6919664 width=161 style='border-top:none;border-left:none;
  width:121pt'>ʵ��ռ�����</td>
  <td colspan=2 class=xl6819664 style='border-left:none'>��<bean:write name="sydjxxlrForm" property="sjzdmj"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 height=20 class=xl9019664 style='border-right:.5pt solid black;border-bottom:none;
  height:20pt'>���������ַ</td>
  <td colspan=6 class=xl9019664 style='border-right:.5pt solid black;
  border-left:none' style="word-wrap:break-word;word-break:break-all;border-bottom:none;"><bean:write name="sydjxxlrForm" property="tdzldz"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=2 class=xl6819664 style='height:20pt'>������Ŀ����</td>
  <td colspan=6 class=xl6819664 style='border-left:none' style="word-wrap:break-word;word-break:break-all;"><bean:write name="sydjxxlrForm" property="jsxmmc"/></td>
 </tr>
 
 
 
 
 
 
 
 
 
 <tr>
<td colspan=8>
<table  border=0 cellpadding=1 cellspacing=0 style='border-collapse:collapse;table-layout:fixed;'>
 <tr style='mso-height-source:userset;height:20pt'>
  <td class=xl7019664 style='height:20pt;width:13%;border-top:none' nowrap=true>ռ����;</td>
  <td class=xl7619664 width=13% style='border-top:none;border-left:none' >����˰��<br>��Ԫ/ƽ���ף�</td>
  <td class=xl7019664 width=13% style='border-top:none;border-left:none' nowrap=true >��˰���</td>
  <td class=xl7119664 style='border-top:none;border-left:none' nowrap=true width=16%>����˰��</td>
  <td class=xl7019664 width=13% style='border-top:none;border-left:none;' nowrap=true >�������<span style='mso-spacerun:yes'>&nbsp;&nbsp;</span></td>
  <td class=xl7119664 style='border-top:none;border-left:none' nowrap=true width=16%>����˰��</td>
  <td class=xl7119664 style='border-top:none;border-left:none' nowrap=true width=16%>Ӧ��˰��</td>
 </tr>
   <%
 Map map = form.getSbmx_map();
 List jt = (List)map.get("00");
 for(int i=0;i<jt.size();i++){
	 SBMXModel model = (SBMXModel)jt.get(i);
	 %>
	 <tr style='mso-height-source:userset;height:20pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:20pt'>��ͨ������ʩ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=center  ><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right ><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right ><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right ><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right ><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right ><%=model.getYnse() %></td>
	 </tr> 
	<%	 
 	}
 	%>
	 
	 <%
 	List gy = (List)map.get("01");
 	for(int i=0;i<gy.size();i++){
	 SBMXModel model = (SBMXModel)gy.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:20pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:20pt' nowrap=true>��ҵ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
	 
  <%
 	List sy = (List)map.get("02");
 	for(int i=0;i<sy.size();i++){
	 SBMXModel model = (SBMXModel)sy.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:20pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:20pt' nowrap=true>��ҵ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
	
	<%
 	List zz = (List)map.get("03");
 	for(int i=0;i<zz.size();i++){
	 SBMXModel model = (SBMXModel)zz.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:20pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:20pt' nowrap=true>סլ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
	
	<%
 	List jm = (List)map.get("04");
 	for(int i=0;i<jm.size();i++){
	 SBMXModel model = (SBMXModel)jm.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:20pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:20pt' nowrap=true>ũ�����סլ</td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
 <%
 	List qt = (List)map.get("05");
 	for(int i=0;i<qt.size();i++){
	 SBMXModel model = (SBMXModel)qt.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:20pt'>
	  <td colspan=1 class=xl8819664  style='border-right:.5pt solid black;
	  height:20pt' nowrap=true>����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
 
 
 
 
 
 <tr style='mso-height-source:userset;height:20.0pt'>
  <td colspan=1  class=xl8819664 style='border-right:.5pt solid black;border-bottom:.5pt solid black;
  height:20.0pt;border-bottom:none' nowrap=true>�ϼ�</td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:none' nowrap=true align=center>&nbsp;</td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:none' nowrap=true align=right>&nbsp;<%=form.getHj_jsmj() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:none' nowrap=true align=right>&nbsp;<%=form.getHj_jzse() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:none' nowrap=true align=right>&nbsp;<%=form.getHj_jmmj() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:none' nowrap=true align=right>&nbsp;<%=form.getHj_jmse() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:none' nowrap=true align=right>&nbsp;<%=form.getHj_ynse() %></td>
 </tr>
 </table>
 </td>
</tr>

 
 <tr style='mso-height-source:userset;height:30.0pt'>
  <td colspan=8  class=xl7819664 width=919 style='border-right:.5pt solid black;
  height:30.0pt'>����������������˰�걨���Ǹ��ݡ��л����񹲺͹�����ռ��˰�����������Ĺ涨��ģ���ȷ��������ʵ�ġ��ɿ��ġ������ġ�������������������������������������������������������������������������������������������������������������</td>
 </tr>
 <tr class=xl6519664 style='mso-height-source:userset;height:30pt'>
  <td colspan=8 class=xl9519664 style='border-right:.5pt solid black;
  height:30pt'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>��˰��ǩ�֣�<span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span>��˰�˸��£�</td>
 </tr>
 <tr  style='mso-height-source:userset;height:20pt'>
  <td colspan=8 class=xl8119664 style='height:20pt'>������˰�������д</td>
 </tr>
 <tr style='mso-height-source:userset;height:60pt'>
  <td colspan=2  class=xl8319664 width=145 style='border-right:.5pt solid black;border-top:none;
  height:60pt'><span style='mso-spacerun:yes'>&nbsp;</span>˰�����������ǩ��</td>
  <td colspan=2 class=xl7319664 style='border-top:none;border-left:none;border-right:none'>˰�������������</td>
  <td class=xl7519664  style='border-top:none;border-left:none'><span style='mso-spacerun:yes'>&nbsp;</span></td>
  <td colspan=3 class=xl8519664 style='border-right:.5pt solid black;border-top:none;
  border-left:none'>˰�����ͬ�������걨<br>
    <br>
    <br>
    <br>
    <span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span>�����£�</td>
 </tr>
</table>
<div style="page-break-before:always;"><br/></div>
		<%	
if("".equals(form.getJmlbdm_jmmx()) || form.getJmlbdm_jmmx()==null || form.getJmlbdm_jmmx().length<1){
	}else{
	%>
	
	<table border=0 cellpadding=0 cellspacing=0 style='border-collapse:collapse;table-layout:fixed;width:98%'>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=10 class=xl915668 style='height:20pt; width:98%'>�����еط�˰��ָ���ռ��˰�����걨��</td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=10 class=xl1005668 style='height:20pt;
  width:85%pt'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span>��λ�� Ԫ�������ǡ��֣�<br>
    �걨ʱ�䣺<span style='mso-spacerun:yes'>&nbsp;&nbsp;
  <%=form.getNian() %></span>��<span style='mso-spacerun:yes'>&nbsp;&nbsp;<%=form.getYue() %></span>��<span
  style='mso-spacerun:yes'>&nbsp;&nbsp;<%=form.getTian() %></span>��<span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span>ƽ���ף�������С�������λ��</td>
 </tr>
 <tr  style='mso-height-source:userset;height:18pt'>
  <td colspan=2 class=xl985668 style='height:18pt'>��˰������</td>
  <td colspan=8 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'><bean:write name="sydjxxlrForm" property="nsrmc"/>��</td>
 </tr>
 <tr  style='mso-height-source:userset;height:18pt'>
  <td colspan=2  class=xl985668 style='height:18pt;border-bottom:none'>��˰����ϸ��ַ</td>
  <td colspan=8 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'><bean:write name="sydjxxlrForm" property="nsrxxdz"/>��</td>
 </tr>
 <tr style='mso-height-source:userset;height:18.0pt;border-bottom:none'>
  <td colspan=2 class=xl985668 style='height:18pt;border-bottom:none'>���֤������</td>
  <td colspan=3 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'>��<bean:write name="sydjxxlrForm" property="sfzzlxmc"/>��</td>
  <td colspan=2 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'>���֤�պ���</td>
  <td colspan=3 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'>��<bean:write name="sydjxxlrForm" property="sfzzhm"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt'>
  <td colspan=2 class=xl805668 style='border-right:.5pt solid black;
  height:18pt'>��˰��������ҵ</td>
  <td colspan=3 class=xl805668 style="word-wrap:break-word;word-break:break-all;border-right:.5pt solid black;border-left:none" align=center><bean:write name="sydjxxlrForm" property="nsrsshymc"/></td>
  <td colspan=2 class=xl835668 style='border-right:.5pt solid black;border-bottom:none;border-left:none;' align=center>��ҵ�Ǽ�ע������</td>
  <td colspan=3 class=xl835668 style='border-right:.5pt solid black;border-bottom:none;border-left:none;' align=center><bean:write name="sydjxxlrForm" property="qydjzclxmc"/>��</td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt'>
  <td colspan=2 class=xl985668 style='height:18pt;border-bottom:none'>˰����������</td>
  <td colspan=3 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'>��<bean:write name="sydjxxlrForm" property="jsjdm"/></td>
  <td colspan=2 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none' align=center>��˰��ʶ���</td>
  <td colspan=3 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none' align=center><bean:write name="sydjxxlrForm" property="nsrsbh"/>��</td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt;border-bottom:none'>
  <td colspan=2 class=xl985668 style='height:18pt;border-bottom:none'>��������</td>
  <td colspan=3 class=xl805668 style="word-wrap:break-word;word-break:break-all;border-right:.5pt solid black;border-left:none" align=center><bean:write name="sydjxxlrForm" property="khyh"/></td>
  <td colspan=2 class=xl835668 style='border-right:.5pt solid black;border-bottom:none;
  border-left:none;' align=center>�����˺�</td>
  <td colspan=3 class=xl835668 style='word-wrap:break-word;word-break:break-all;border-right:.5pt solid black;border-left:none;border-bottom:none' align=center><bean:write name="sydjxxlrForm" property="yhzh"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt'>
  <td colspan=2  class=xl985668 style='height:18pt;border-bottom:none'>��ϵ������</td>
  <td colspan=3 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'>��<bean:write name="sydjxxlrForm" property="lxrxm"/></td>
  <td colspan=2 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none' align=center>��ϵ�绰</td>
  <td colspan=3 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none' align=center>��<bean:write name="sydjxxlrForm" property="lxdh"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt'>
  <td colspan=2 class=xl985668 style='height:18pt;border-bottom:none' align=center>��׼ռ���ĺ�</td>
  <td colspan=3 class=xl805668 style='border-right:.5pt solid black;border-left:
  none;border-bottom:none'>��<bean:write name="sydjxxlrForm" property="pzjdwh"/></td>
  <td colspan=2 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none;' align=center>ռ��ʱ��</td>
  <td colspan=3 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none;' align=center><bean:write name="sydjxxlrForm" property="zdsj"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt;border-bottom:none'>
  <td colspan=2 class=xl985668 style='height:18pt;border-bottom:none'>��׼ռ�����</td>
  <td colspan=3 class=xl805668 style='border-right:.5pt solid black;border-bottom:none;border-left:
  none'>��<bean:write name="sydjxxlrForm" property="pzjdmj"/></td>
  <td colspan=2 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none' align=center>ʵ��ռ�����</td>
  <td colspan=3 class=xl835668 style='border-right:.5pt solid black;
  border-left:none;border-bottom:none' align=center><bean:write name="sydjxxlrForm" property="sjzdmj"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt'>
  <td colspan=2 class=xl985668 style='height:18pt;border-bottom:none'>���������ַ</td>
  <td colspan=8 class=xl805668 style='border-right:.5pt solid black;border-left:
  none'>��<bean:write name="sydjxxlrForm" property="tdzldz"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:18pt'>
  <td colspan=2 class=xl985668 style='height:18pt'>������Ŀ����</td>
  <td colspan=8 class=xl805668 style='border-right:.5pt solid black;border-left:
  none;border-bottom:.5pt solid black'>��<bean:write name="sydjxxlrForm" property="jsxmmc"/></td>
 </tr>
 
 
 
 
 
<tr>
<td colspan=10>
<table  border=0 cellpadding=1 cellspacing=0 style='border-collapse:collapse;table-layout:fixed;'>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=1 class=xl7019664 style='height:20pt;width:16%;border-top:none;border-bottom:none;font-size:9pt' nowrap=true>ռ����;</td>
  <td class=xl7619664 width=14% style='border-top:none;border-left:none;font-size:9pt' nowrap=true>����˰��<br>
    ��Ԫ/ƽ���ף�</td>
  <td class=xl7019664 width=13% style='border-top:none;border-left:none;font-size:9pt' nowrap=true >��˰���</td>
  <td class=xl7119664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true width=15%>����˰��</td>
  <td class=xl7019664 width=13% style='border-top:none;border-left:none;font-size:9pt' nowrap=true >�������<span style='mso-spacerun:yes'>&nbsp;&nbsp;</span></td>
  <td class=xl7119664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true width=14%>����˰��</td>
  <td class=xl7119664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true width=15%>Ӧ��˰��</td>
 </tr>
   <%
 Map map2 = form.getSbmx_map();
 List jt2 = (List)map2.get("00");
 for(int i=0;i<jt2.size();i++){
	 SBMXModel model = (SBMXModel)jt2.get(i);
	 %>
	 <tr style='mso-height-source:userset;height:18pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:18pt;font-size:9pt' nowrap=true>��ͨ������ʩ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=center  ><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right ><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right ><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right ><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right ><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right ><%=model.getYnse() %></td>
	 </tr> 
	<%	 
 	}
 	%>
	 
	 <%
 	List gy2 = (List)map2.get("01");
 	for(int i=0;i<gy2.size();i++){
	 SBMXModel model = (SBMXModel)gy2.get(i);
	 %>
	 <tr style='mso-height-source:userset;height:18pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:18pt;font-size:9pt' nowrap=true>��ҵ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
	 
  <%
 	List sy2 = (List)map2.get("02");
 	for(int i=0;i<sy2.size();i++){
	 SBMXModel model = (SBMXModel)sy2.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:18pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:18pt;font-size:9pt' nowrap=true>��ҵ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
	
	<%
 	List zz2 = (List)map2.get("03");
 	for(int i=0;i<zz2.size();i++){
	 SBMXModel model = (SBMXModel)zz2.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:18pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:18pt;font-size:9pt' nowrap=true>סլ����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
	
	<%
 	List jm2 = (List)map2.get("04");
 	for(int i=0;i<jm2.size();i++){
	 SBMXModel model = (SBMXModel)jm2.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:18pt'>
	  <td colspan=1 class=xl8819664 style='border-right:.5pt solid black;
	  height:18pt;font-size:9pt' nowrap=true>ũ�����סլ</td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
 <%
 	List qt2 = (List)map2.get("05");
 	for(int i=0;i<qt2.size();i++){
	 SBMXModel model = (SBMXModel)qt2.get(i);
	 %>
	 <trstyle='mso-height-source:userset;height:18pt'>
	  <td colspan=1 class=xl8819664  style='border-right:.5pt solid black;
	  height:18pt;font-size:9pt' nowrap=true>����</td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=center><%=model.getSyse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJsmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJzse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmmj() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getJmse() %></td>
	  <td class=xl7219664 style='border-top:none;border-left:none;font-size:9pt' nowrap=true align=right><%=model.getYnse() %></td>
	 </tr> 
	 <%
 	}
	%>
 
 
 
 
 
 <tr style='mso-height-source:userset;height:18.0pt'>
  <td colspan=1  class=xl8819664 style='border-right:.5pt solid black;border-bottom:.5pt solid black;
  height:18.0pt;border-bottom:.5pt solid black;font-size:9pt' nowrap=true>�ϼ�</td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:.5pt solid black;font-size:9pt' nowrap=true align=center>&nbsp;</td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:.5pt solid black;font-size:9pt' nowrap=true align=right>&nbsp;<%=form.getHj_jsmj() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:.5pt solid black;font-size:9pt' nowrap=true align=right>&nbsp;<%=form.getHj_jzse() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:.5pt solid black;font-size:9pt' nowrap=true align=right>&nbsp;<%=form.getHj_jmmj() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:.5pt solid black;font-size:9pt' nowrap=true align=right>&nbsp;<%=form.getHj_jmse() %></td>
  <td class=xl7219664 style='border-top:none;border-left:none;border-bottom:.5pt solid black;font-size:9pt' nowrap=true align=right>&nbsp;<%=form.getHj_ynse() %></td>
 </tr>
 </table>
 </td>
</tr>
 
 
 
 <tr>
 <td colspan=10>
<table  border=0 cellpadding=1 cellspacing=0 style='border-collapse:collapse;table-layout:fixed;width="100%"'>
 <tr>
  <td class=xl655668 style='height:20pt;border-top:none;' >����˰���</td>
  <td class=xl655668 style='border-top:none;border-left:none' align=center>������ʩռ��</td>
  <td class=xl655668 style='border-top:none;border-left:none' width="8%" align=center>ũ�����ס��</td>
  <td class=xl695668 style='border-top:none;border-left:none' width="8%" align=center>ũ�����Ѽ���</td>
  <td class=xl665668 style='border-top:none'>����Ժ</td>
  <td class=xl685668 style='border-top:none;'>ҽԺ</td>
  <td class=xl685668 style='border-top:none'>�׶�԰</td>
  <td class=xl655668 style='border-top:none;border-left:none' align=center>ѧУ</td>
  <td class=xl655668 style='border-top:none;border-left:none' align=center>����</td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td class=xl655668 style='height:20pt;border-bottom:none'>��������˰���</td>
  <%
  Map map3 = (Map)form.getJmmx_map();
  JMXXModel jsmodel = (JMXXModel)map3.get("00");
  JMXXModel ncmodel = (JMXXModel)map3.get("01");
  JMXXModel knmodel = (JMXXModel)map3.get("02");
  JMXXModel ylymodel = (JMXXModel)map3.get("03");
  JMXXModel yymodel = (JMXXModel)map3.get("04");
  JMXXModel yeymodel = (JMXXModel)map3.get("05");
  JMXXModel xxmodel = (JMXXModel)map3.get("06");
  JMXXModel qtmodel = (JMXXModel)map3.get("07");
  
  if("".equals(jsmodel.getFljmse()) || jsmodel.getFljmse() == null){
	  jsmodel.setFljmse("0");
  }
  if("".equals(ncmodel.getFljmse()) || ncmodel.getFljmse() == null){
	  ncmodel.setFljmse("0");
  }
  if("".equals(knmodel.getFljmse()) || knmodel.getFljmse() == null){
	  knmodel.setFljmse("0");
  }
  if("".equals(ylymodel.getFljmse()) || ylymodel.getFljmse() == null){
	  ylymodel.setFljmse("0");
  }
  if("".equals(yymodel.getFljmse()) || yymodel.getFljmse() == null){
	  yymodel.setFljmse("0");
  }
  if("".equals(yeymodel.getFljmse()) || yeymodel.getFljmse() == null){
	  yeymodel.setFljmse("0");
  }
  if("".equals(xxmodel.getFljmse()) || xxmodel.getFljmse() == null){
	  xxmodel.setFljmse("0");
  }
  if("".equals(qtmodel.getFljmse()) || qtmodel.getFljmse() == null){
	  qtmodel.setFljmse("0");
  }
  
  
  if("".equals(jsmodel.getFljmmj()) || jsmodel.getFljmmj() == null){
	  jsmodel.setFljmmj("0");
  }
  if("".equals(ncmodel.getFljmmj()) || ncmodel.getFljmmj() == null){
	  ncmodel.setFljmmj("0");
  }
  if("".equals(knmodel.getFljmmj()) || knmodel.getFljmmj() == null){
	  knmodel.setFljmmj("0");
  }
  if("".equals(ylymodel.getFljmmj()) || ylymodel.getFljmmj() == null){
	  ylymodel.setFljmmj("0");
  }
  if("".equals(yymodel.getFljmmj()) || yymodel.getFljmmj() == null){
	  yymodel.setFljmmj("0");
  }
  if("".equals(yeymodel.getFljmmj()) || yeymodel.getFljmmj() == null){
	  yeymodel.setFljmmj("0");
  }
  if("".equals(xxmodel.getFljmmj()) || xxmodel.getFljmmj() == null){
	  xxmodel.setFljmmj("0");
  }
  if("".equals(qtmodel.getFljmmj()) || qtmodel.getFljmmj() == null){
	  qtmodel.setFljmmj("0");
  }
 

  %>
  
  
  <td class=xl675668 style='border-top:none;border-left:none;border-right:.5pt solid black;WORD-WRAP: break-word' align=right><%=jsmodel.getFljmmj() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;border-right:.5pt solid black;WORD-WRAP: break-word' align=right><%=ncmodel.getFljmmj() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;border-right:none;WORD-WRAP: break-word' align=right><%=knmodel.getFljmmj() %></td>
  <td class=xl675668 style='border-top:none;WORD-WRAP: break-word' align=right><%=ylymodel.getFljmmj() %></td>
  <td class=xl705668 style='border-top:none;WORD-WRAP: break-word' align=right><%=yymodel.getFljmmj() %></td>
  <td class=xl675668 style='border-top:none;border-right:.5pt solid black;WORD-WRAP: break-word' align=right><%=yeymodel.getFljmmj() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;border-right:.5pt solid black;WORD-WRAP: break-word' align=right><%=xxmodel.getFljmmj() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;WORD-WRAP: break-word' align=right><%=qtmodel.getFljmmj() %></td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td class=xl675668 style='height:20pt;border-right:.5pt solid black;' align=center>��������˰˰��</td>
  <td class=xl675668 style='border-top:none;border-left:none;border-right:.5pt solid black;WORD-WRAP: break-word'  align=right><%=jsmodel.getFljmse() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;border-right:.5pt solid black;WORD-WRAP: break-word' align=right><%=ncmodel.getFljmse() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;border-right:none;WORD-WRAP: break-word' align=right><%=knmodel.getFljmse() %></td>
  <td class=xl675668 style='border-top:none;border-right:none;WORD-WRAP: break-word' align=right><%=ylymodel.getFljmse() %></td>
  <td class=xl675668 style='border-top:none;WORD-WRAP: break-word' align=right><%=yymodel.getFljmse() %></td>
  <td class=xl675668 style='border-top:none;border-right:.5pt solid black;WORD-WRAP: break-word' align=right><%=yeymodel.getFljmse() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;border-right:.5pt solid black;WORD-WRAP: break-word'  align=right><%=xxmodel.getFljmse() %></td>
  <td class=xl675668 style='border-top:none;border-left:none;WORD-WRAP: break-word'  align=right><%=qtmodel.getFljmse() %></td>
 </tr>
 </table>
 </td>
 </tr>
 <tr style='mso-height-source:userset;height:40pt;border-bottom:none'>
  <td colspan=2 class=xl1035668 style='height:40pt;border-bottom:.5pt solid black;border-top:none;border-bottom:none' align=center>�걨����˰����</td>
  <td colspan=8 class=xl785668 style='border-right:.5pt solid black;border-left:none;border-top:none;border-bottom:none;font-size:9pt'><bean:write name="sydjxxlrForm" property="jmsyj"/></td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=10 class=xl925668 style='border-right:.5pt solid black;
  height:20pt;width:75%'>�������������˼����걨���Ǹ��ݡ��л����񹲺͹�����ռ��˰�����������Ĺ涨��ģ���ȷ��������ʵ�ġ��ɿ��ġ������ġ�������������������������������������������������������������������������������������������������������������</td>
 </tr>
 <tr style='mso-height-source:userset;height:20pt'>
  <td colspan=10 class=xl955668 style='border-right:.5pt solid black;border-bottom:none;
  height:20pt;width:75%'>��˰��ǩ�֣�<span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span>��˰�˸��£�</td>
 </tr>
 <tr style='mso-height-source:userset;height:15pt'>
  <td colspan=10 class=xl905668 style='height:15pt;border-bottom:none;
  width:75%'>����������˰�������д</td>
 </tr>
 <tr style='mso-height-source:userset;height:40pt'>
  <td colspan=4 class=xl725668 style='border-right:.5pt solid black;border-bottom:none;
  height:40pt;width:284pt'>˰�����������ǩ��ͬ�⣺</td>
  <td colspan=3 class=xl725668  style='border-right:.5pt solid black;border-bottom:none;
  border-left:none;width:245pt'>˰����������ǩ��ͬ�⣺ ��������������</td>
  <td colspan=3 class=xl735668 style='border-right:.5pt solid black;border-bottom:none;
  width:222pt'>˰�����ͬ���������˰�걨�����£���</td>
 </tr>
 <tr style='mso-height-source:userset;height:15pt;border-bottom:none'>
  <td colspan=4 class=xl895668 width=378 style='height:15pt;
  width:284pt'>�ꡡ���¡�����</td>
  <td colspan=3 class=xl755668 width=327 style='border-right:.5pt solid black;
  border-left:none;width:245pt;height:15pt'>�ꡡ���¡�����</td>
  <td colspan=3 class=xl765668 width=294 style='border-right:.5pt solid black;
  width:222pt;height:15pt'>���ꡡ���¡�����</td>
 </tr>
 <tr style='mso-height-source:userset;height:15pt'>
  <td colspan=10 class=xl905668 style='height:20pt;border-top:none;border-bottom:none;
  width:75%'>�������о�˰�������д</td>
 </tr>
 <tr style='mso-height-source:userset;height:40pt'>
  <td colspan=4 class=xl1025668 width=378 style='height:40pt;
  width:28%'>���������</td>
  <td colspan=3 class=xl725668 style='border-right:.5pt solid black;
  border-left:none;width:25%'>���������</td>
  <td colspan=3 class=xl735668 style='border-right:.5pt solid black;
  width:222pt'>��������</td>
 </tr>
 <tr style='mso-height-source:userset;height:15pt'>
  <td colspan=4 class=xl895668 style='height:15pt;border-top:none;
  width:28%'>�ꡡ���¡�����</td>
  <td colspan=3 class=xl755668 style='border-right:.5pt solid black;border-top:none;
  border-left:none;width:25%;height:15pt'>�ꡡ���¡�����</td>
  <td colspan=3 class=xl765668  style='border-right:.5pt solid black;border-top:none;
  width:22%;height:15pt'>�ꡡ���¡�����</td>
 </tr>
</table>

<%
	}

}
%>





</div>
<%
String static_contextpath = SfResourceLocator.getContextPath(request);
%>
<br>
 
              <div align=center>
              	<input type="image" accesskey="z" tabIndex="-1" onclick="printit();return false;" style="cursor:hand;" onMouseOver="MM_swapImage('dayin','','<%=static_contextpath%>images/dayin2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ӡ" id="dayin" src="<%=static_contextpath%>images/dayin1.jpg">
          
      
             	<input type="image" accesskey="z" tabIndex="-1" onclick="goback();return false;" style="cursor:hand;" onMouseOver="MM_swapImage('goback','','<%=static_contextpath%>images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="goback" src="<%=static_contextpath%>images/fanhui1.jpg">
     		</div>
</body>


</html:html>
