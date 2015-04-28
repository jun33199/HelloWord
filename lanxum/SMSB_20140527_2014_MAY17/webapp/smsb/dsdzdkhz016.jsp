<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>

<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>撤消代售代征代扣汇总</title>

<script language="JavaScript" type="text/JavaScript" src="../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/smsb_common.js"></script>
<style type="text/css">
<!--
	@import url(../css/text.css);
-->
</style>
<script language=JavaScript>

function doReturn(){
document.forms[0].target='_self';
    doSubmitForm("Query");
}

function doHzsbjkd()
{        document.forms[0].target='_self';
	doSubmitForm("Hzsbjkd");

}

function doDelete(){
	if(trim(document.forms[0].sbhzdh.value) == "" ){
		alert("没有汇总单可供撤销！");
		return false;
	}
        document.forms[0].target='_self';
	doSubmitForm("Delete");
}
function printFunc(pzh,dm){
	if (pzh!="" && dm!=""){
		document.dsdzdkForm.headJkpzh.value=pzh;
		document.dsdzdkForm.headJsjdm.value=dm;
		document.dsdzdkForm.target="blank";
		//alert(document.gtgshCxjksForm.headJkpzh.value);
		//alert(document.gtgshCxjksForm.headJsjdm.value);
        //document.forms[0].target='_self';
		doSubmitForm('Print');
	}
}
</script>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
<td align="center" colspan=2>

<%@ include file="include/header.jsp" %>
<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0" onkeydown="jsjdmQuery()">
<tr>

<html:form method="POST" action="/webapp/smsb/dsdzdkAction.do">
<html:hidden property="actionType" />
<html:hidden property="jsjdm" />
<html:hidden property="sbhzdh" />
<html:hidden property="sbrq" />
<html:hidden property="skssksrq" />
<html:hidden property="skssjsrq" />
<html:hidden property="cjrq"/>
<html:hidden property="qxdm"/>
<html:hidden property="lrrq"/>
<html:hidden property="lrr"/>
<html:hidden property="fsdm"/>
<html:hidden property="swjgzzjgdm"/>
<html:hidden property="nsrmc" />
<html:hidden property="qylxdh" />
<html:hidden property="headJkpzh" />
<html:hidden property="headJsjdm" />
	<td valign="top"><br>

	<table align="center" cellspacing="0" class="table-99" border="0">
	<tr class="black9">
		<td class="1-td1">代售代征代扣汇总</td>
	</tr>
	<tr>
		<td valign="top" class="1-td2"><br>
		<table width="100%" cellspacing=0 class="table-99">
		<tr class="black9">
			<td align="right" nowrap>金额单位：人民币元</td>
		</tr>
		</table>

		<table width="100%" cellspacing=0 class="table-99">
		<tr>
			<td width="15%" nowrap class="2-td2-t-left">计算机代码</td>
			<td width="10%" nowrap class="2-td2-t-left">&nbsp;
				<ttsoft:write name="dsdzdkForm" property="jsjdm" />
			</td>
			<td width="15%" nowrap class="2-td2-t-left">单位名称(盖章)</td>
			<td width="30%" nowrap class="2-td2-t-left">&nbsp;
				<ttsoft:write name="dsdzdkForm" property="nsrmc"/>
			</td>
			<td width="15%" nowrap class="2-td2-t-left">联系电话</td>
			<td width="15%" nowrap class="2-td2-t-center">&nbsp;
				<ttsoft:write name="dsdzdkForm" property="qylxdh"/>
			</td>
		</tr>
		</table><br>

		<table width="100%" cellspacing=0 class="table-99">
		<tr>
			<td valign=top nowrap class="2-td1-left">汇总日期</td>
			<td valign=top nowrap class="2-td1-left">汇总单号</td>
			<td valign=top nowrap class="2-td1-left">缴款凭证张数</td>
			<td valign=top nowrap class="2-td1-left">实缴金额合计</td>
		</tr>
		<logic:iterate name="dsdzdkForm" id="item" property="hzdDataList" scope="request">
		<tr>
			<td nowrap class="2-td2-left">&nbsp;
				<ttsoft:write name="item" property="hzrq"/>
			</td>
			<td nowrap class="2-td2-left">&nbsp;
				<ttsoft:write name="item" property="sbhzdh"/>
			</td>
			<td nowrap class="2-td2-left">&nbsp;
				<ttsoft:write name="item" property="jkpzs"/>
			</td>
			<td nowrap class="2-td2-left">&nbsp;
				<ttsoft:write name="item" property="sjse"/>
			</td>
	   	</tr>
		</logic:iterate>
		</table><br>

		<table width="100%" cellspacing=0 class="table-99">
		<tr>
			<td valign=top nowrap class="2-td1-left">序号</td>
			<td valign=top nowrap class="2-td1-left">汇总单号</td>
			<td valign=top nowrap class="2-td1-left">缴款凭证号</td>
			<td valign=top nowrap class="2-td1-center">实缴金额</td>
		</tr>
		<% int i=1; %>
		<logic:iterate id="item" name="dsdzdkForm" property="jkpzDataList" scope="request">
	  	<tr>
	    	<td nowrap class="2-td2-left"> <%= i++ %></td>
	    	<td nowrap class="2-td2-left">
	    		<ttsoft:write name="item" property="sbhzdh"/>
	    	</td>
	    	<td nowrap class="2-td2-left">
	    	    <a href="javascript:printFunc('<ttsoft:write name="item" property="jkpzh"/>','<ttsoft:write name="dsdzdkForm" property="jsjdm" />')"><ttsoft:write name="item" property="jkpzh"/>
                    </a>
	    	</td>
	    	<td nowrap class="2-td2-center">
	    		<ttsoft:write name="item" property="sjse"/>
	    	</td>
	  	</tr>
		</logic:iterate>
		</table><br><br>

		<div align="center">
			<input type="image" accesskey="g" tabIndex="-1" style="cursor:hand"
				onClick="doHzsbjkd(); return false;"
				onMouseOver="MM_swapImage('b','','<%=static_contextpath%>images/cxhz-g2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/cxhz-g1.jpg " alt="撤销汇总" name="Image13" width="79" height="22" border="0" id='b'>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="image" accesskey="f" tabIndex="-1"   onClick="doReturn(); return false;" style="cursor:hand"
			onMouseOver="MM_swapImage('a','','<%=static_contextpath%>images/fh-f2.jpg',1)"
			onMouseOut="MM_swapImgRestore()"
			 src="<%=static_contextpath%>/images/fh-f1.jpg " name="Image13" width="79" height="22" border="0" id='a'>

		</div><br><br>
		</td>
	</tr>
	</table>

	</td>
</html:form>
</tr>
</table><br>

<%@ include file="./include/footer.jsp"%>

</td>
</tr>
</table>
</body>
</html:html>