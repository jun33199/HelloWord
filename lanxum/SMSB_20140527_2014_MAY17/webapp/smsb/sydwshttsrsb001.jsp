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
<title>企业所得税_事业单位、社会团体收入申报表</title>

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
<%/*保存*/%>
function doSave()
{
	if(!check_jsjdm()) return false;

	if(!isDate(document.forms[0].sbrq, "true")){
		return;
	}
	if(!isDate(document.forms[0].skssksrq, "true")){
		return;
	}
	if(!isDate(document.forms[0].skssjsrq, "true")){
		return;
	}

	doSubmitForm('Save');
}

<%/*删除*/%>
function doDelete()
{
	if(!check_jsjdm()) return false;

	doSubmitForm('Delete');
}

<% /*查找*/ %>


function doQuery()
{
	if(trim(document.forms[0].jsjdm.value) == ""){
		alert("请输入纳税企业计算机代码！");
		return false;
	}
	if(!isDate(document.forms[0].sbrq, "true")){
		return false;
	}
	document.forms[0].nsrmc.value = "";
	document.forms[0].zcdzlxdh.value = "";
	doSubmitForm('Query');
}

//响应计算机代码的回车查询
function jsjdmQuery(){
    if(event.keyCode==13) event.keyCode = 9;
}

<%/*清除*/%>
function doReset()
{
    if(confirm("确认是否清除当前数据？"))
    {
	   	for(var i=1; i<21; i++){
			obj = eval("document.forms[0].bqljs_" + i);
			obj.value = "";
	   	}
    }
}

function doReturn(strUrl)
{
	if(!check_jsjdm()) return false;

	var param = "actionType=Query"
		param = param + "&jsjdm=" + document.forms[0].jsjdm.value;
		param = param + "&sbrq=" + document.forms[0].sbrq.value;
		param = param + "&skssksrq=" + document.forms[0].skssksrq.value;
		param = param + "&skssjsrq=" + document.forms[0].skssjsrq.value;
	window.location = strUrl + "?" + param;
}

// 检测用户是否输入计算机代码
function check_jsjdm(){
	if(trim(document.forms[0].jsjdm.value) == "" || document.forms[0].nsrmc.value == ""){
		alert("请输入纳税企业计算机代码！");
		return false;
	}
	return true;
}

// 录入数据域的校验方法
function check_sydwshttsrsb(cell_obj, index){

	// 校验文号数据是否为空值
	if(index > 17){
		cell_val = trim(cell_obj.value);
		if(cell_val == ""){
			return true;
		}
	  	var index = -1;
	  	for(var i=0;i<cell_val.length;i++){
	     	if(cell_val.charAt(i) == '.'){
	          	index = i;
	     	}
	  	}
	  	if(index > 0){
	  		alert("批准文号不能输入小数点");
			window.event.returnValue=false;
			cell_obj.focus();
			cell_obj.select();
	  		return false;
	  	}
        if(cell_val.length > 40){
	  		alert("批准文号长度不能大于40个字符");
			window.event.returnValue=false;
			cell_obj.focus();
			cell_obj.select();
	  		return false;
        }
	}else{
		if(!isNum(cell_obj, null, null, null, 16, 2))
			return false;
		mathArray = mathString_SYDWSHTTSRMX;
		computeFunction(cell_obj, "bqljs_" + index);
	}

	return true;
}
function show(){
document.forms[0].jsjdm.focus();
}
</script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0"  onLoad="show()">
<table width="100%" align="center" cellspacing="0" class="table-99" >
<tr>
<td><%@ include file="include/header.jsp" %></td>
</tr>

<tr>

<html:form method="post" action="/webapp/smsb/sydwshttsrsbAction.do">
<html:hidden property="actionType" />
<html:hidden property="nsrmc" />
<html:hidden property="zcdzlxdh" />
<html:hidden property="swjgzzjgdm" />
<html:hidden property="qxdm" />
<html:hidden property="fsdm" />
<html:hidden property="djzclxdm" />
<html:hidden property="lrrq" />
<html:hidden property="cjrq" />
<html:hidden property="iszhsb"/>

<td valign="top"> <br>

	<table  width="100%" align="center" cellspacing="0" class="table-99" onkeydown="jsjdmQuery()">
	<tr>
		<td class="1-td1">事业单位、社会团体收入申报表</td>
	</tr>
	<tr>
		<td valign="top" class="1-td2"><br><br>
		<table  border="0"  width="100%" cellspacing="0" class="table-99">
		<tr>
			<td  colspan="2" align="center"> </td>
		</tr>
		<tr class="black9">
			<td align="left" nowrap>
				<div align="left">申报日期：
				<html:text size="8" maxLength="8" property="sbrq" tabIndex="1" onchange="getStartEndDate(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,1)" />
				</div>
			</td>
			<td align="center" nowrap>
				<div align="left">税款所属日期：
				<!--<html:text size="8" maxLength="8" property="skssksrq" tabIndex="2" onblur="javascript:isDate(this,'true');" />&nbsp;至
				<html:text size="8" maxLength="8" property="skssjsrq" tabIndex="3" onblur="javascript:isDate(this,'true');" />-->
                                <html:text property="skssksrq" size="11" maxlength="8" tabIndex="2" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,0,0)"/>至
                                <html:text property="skssjsrq" size="11" maxlength="8" tabIndex="3" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,0,1)"/>
				</div>
			</td>
			<td align="right" nowrap>金额单位：元</td>
		</tr>
		</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
		<tr>
			<td nowrap width="10%" class="2-td2-t-left"><div align="right">计算机代码&nbsp;&nbsp;</div></td>
			<td nowrap width="15%" class="2-td2-t-left">
				<div align="left">&nbsp;&nbsp;
				<html:text size="8" maxLength="8" property="jsjdm" tabIndex="3" onchange="doQuery();"  onKeyDown="jsjdmQuery()" />&nbsp;
				</div>
			</td>
			<td width="10%" nowrap class="2-td2-t-left">
				<div align="right">单位名称（公章）&nbsp;&nbsp;</div>
			</td>
			<td width="20%" nowrap class="2-td2-t-left">
				<div align="left">&nbsp;
				<bean:write name="sydwshttsrsbForm" property="nsrmc" scope="request" filter="true" />
				</div>
			</td>
			<td width="10%" nowrap class="2-td2-t-left">
				<div align="right">联系电话&nbsp;&nbsp;</div>
			</td>
			<td width="15%" nowrap class="2-td2-t-center">
				<div align="left">&nbsp;&nbsp;
				<bean:write name="sydwshttsrsbForm" property="zcdzlxdh" scope="request" filter="true" />
				</div>
			</td>
		</tr>
		<tr>
			<td nowrap class="2-td2-left">
			<div align="right">单位类型&nbsp;&nbsp;</div></td>
			<td nowrap class="2-td2-left">
				<div align="left">&nbsp;&nbsp;
				<logic:notEqual name="sydwshttsrsbForm" property="djzclxdm" value="">
				<ttsoft:dmmc property="djzclxdm" codekey="SYDW_DWLX"/>
				</logic:notEqual>
				</div>
			</td>
			<td colspan="2" nowrap class="2-td2-left">&nbsp;</td>
			<td nowrap class="2-td2-left"><div align="right">应税收入选项&nbsp;&nbsp;</div></td>
			<td nowrap class="2-td2-center">
				<div align="left">&nbsp;
				<html:radio property="sfyyssr" value="1" />有
				<html:radio property="sfyyssr" value="0" />无
				</div>
			</td>
		</tr>
		</table><br>

		<table width="100%" border="0" cellpadding="0" cellspacing="0" id="SYDWSHTTSRMX">
		<ttsoft:smsbtable form="sydwshttsrsbForm" property="dataList" tableId="SYDWSHTTSRMX" hasTitle="true" tabIndexStart="10"/>
		</table><BR>

		<div align="center">
               	&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="u" tabIndex="-1"  onClick="doReset();return false;" onMouseOver="MM_swapImage('qingchun','','<%=static_contextpath%>images/qc-u2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/qc-u1.jpg" value="保存"  width="79" height="22" id="qingchun" style="cursor:hand" >
		  		&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="s" tabIndex="-1"  onClick="doSave();return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/bc-s1.jpg" value="保存"  width="79" height="22" id="baocun" style="cursor:hand" >
               	&nbsp;&nbsp;&nbsp;&nbsp;<input type="image" accesskey="x" tabIndex="-1" onClick="doDelete();return false;" onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="清除" src="<%=static_contextpath%>images/qbsc-x1.jpg" width="79" height="22" id="shanchu" style="cursor:hand">
               	&nbsp;&nbsp;&nbsp;&nbsp;
               	<% if(request.getParameter("from") != null) { %>
                     <input type="hidden" name="from" value="<%=request.getParameter("from") %>" />
                  	 <input type="image" accesskey="f" tabIndex="-1"  onClick="doReturn('<%=request.getContextPath() %>/webapp/smsb/qysdsnbAction.do'); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" style="cursor:hand">
               	<% }else{ %>
               		<input type="image" accesskey="c" tabIndex="-1" onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand">
               	<% } %>
                  <br>
		</div><BR>
        </td>
	</tr>
	</table><br>
</td>
</html:form>
</tr>
<tr>
	<td><%@ include file="include/footer.jsp" %></td>
</tr>
</table>
<script language="javascript"> 
/****如果该纳税人为非正常户，则显示提示信息****/
/****20050817 Huxiaofeng****/
var nsrzt = <ttsoft:write name="sydwshttsrsbForm" property="nsrzt" filter="false"/>;
checkNsrzt();
/**************end *******/
</script>
</body>
</html:html>


