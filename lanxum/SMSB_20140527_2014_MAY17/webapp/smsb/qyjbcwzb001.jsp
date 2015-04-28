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
<title>企业所得税</title>
<script language="JavaScript" type="text/JavaScript" src="../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/smsb_common.js"></script>

<script language=JavaScript>
<%/*保存*/%>
function showhj(){
	 //if(document.forms[0].actionType.value=="Show")
document.forms[0].jsjdm.focus();

}
function doSave()
{
	if(!check_jsjdm()) return false;

	if(!isDate(document.forms[0].sbrq, "true")){
		return false;
	}
	if(!isDate(document.forms[0].skssksrq, "true")){
		return false;
	}
	if(!isDate(document.forms[0].skssjsrq, "true")){
		return false;
	}

	doSubmitForm('Save');
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

<%/*清除*/%>
function doReset()
{
    if(confirm("确认是否清除当前数据？"))
    {
	   	for(var i=1; i < 41; i++){
			obj1 = eval("document.forms[0].ncs_" + i);
			obj1.value = "";
			obj2 = eval("document.forms[0].nms_" + i);
			obj2.value = "";
	   	}
    }
}

<%/*删除*/%>
function doDelete()
{
	if(!check_jsjdm()) return false;

	doSubmitForm('Delete');
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

function getHcByID(id){
  	var index = -1;
  	for(var i=0;i<id.length;i++){
     	if(id.charAt(i) == '_'){
          	index = i;
     	}
  	}

  	if(index < 0){
  		alert("ID:"+id+",不正确！")
  		return false;
  	}
  	return parseInt(id.substring(index+1,id.length));
}

// 录入数据域的校验方法
function check_qyjbcwzb(cell_obj){

	if(getHcByID(cell_obj.id) == 6){
		return isNum(cell_obj, null, null, null, 16, 2);
	}else if(getHcByID(cell_obj.id) == 40){
		//return isNum(cell_obj, 0, null, null, 16, 0);//Modified by lufeng 2004-01-03
		return isNum(cell_obj, null, null, null, 16, 0);
	}else{
		//return isNum(cell_obj, 0, null, null, 16, 2);//Modified by lufeng 2004-01-03
		return isNum(cell_obj, null, null, null, 16, 2);
	}
}

//响应计算机代码的回车查询
function jsjdmQuery(){
	if(event.keyCode==13) event.keyCode = 9;
}

</script>

<style type="text/css">
<!--
	@import url(../css/text.css);
-->
</style>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onLoad="showhj()">

<%@ include file="include/header.jsp" %>
<!--
<tr><td>
<html:errors />
</td></tr>
-->

<tr>

<html:form method="post" action="/webapp/smsb/qyjbcwzbAction.do" >
<html:hidden property="actionType" />
<html:hidden property="nsrmc" />
<html:hidden property="zcdzlxdh" />
<html:hidden property="swjgzzjgdm" />
<html:hidden property="qxdm" />
<html:hidden property="fsdm" />
<html:hidden property="lrrq" />
<html:hidden property="cjrq" />
<html:hidden property="iszhsb"/>

<td valign="top"> <br>
<table align="center" cellspacing="0" class="table-99" onkeydown="jsjdmQuery()">
<tr>
	<td class="1-td1">企业基本财务指标情况表</td>
</tr>
<tr>
	<td valign="top" class="1-td2"><br>
	<table cellspacing="0" class="table-99">
	<tr class="black9">
		<td align="left" nowrap>
			<div align="left">申报日期：
			<html:text size="8" maxLength="8" property="sbrq" tabIndex="3"  onchange=" getStartEndDate(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,1)" />
			</div>
		</td>
		<td align="left" nowrap>
			<div align="left">税款所属日期：
				<!--<html:text property="skssksrq" size="8" maxlength="8"  tabIndex="1" onchange="isDate(this,1)"/>&nbsp;至
				<html:text property="skssjsrq" size="8" maxlength="8"  tabIndex="2" onchange="isDate(this,1)"/>-->
                        <html:text property="skssksrq" size="11" maxlength="8" tabIndex="1" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,0,0)"/>至
                        <html:text property="skssjsrq" size="11" maxlength="8" tabIndex="2" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,0,1)"/>
			</div>
		</td>
		<td align="right" nowrap>金额单位：人民币元</td>
	</tr>
	<tr>
		<td  width="100%" colspan="3">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="15%" nowrap class="2-td2-t-left">
				<div align="right">税务计算机代码&nbsp;&nbsp;	</div>
			</td>
			<td width="15%" nowrap class="2-td2-t-left">
				<div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<html:text property="jsjdm" size="8" maxlength="8"  tabIndex="4" onchange="doQuery();"  onKeyDown="jsjdmQuery()" />&nbsp;
				</div>
			</td>
			<td width="15%" nowrap class="2-td2-t-left">
				<div align="right">单位名称(公章)&nbsp;&nbsp;</div>
			</td>
			<td width="30%" nowrap class="2-td2-t-left">
				<div align="left">&nbsp;
				<bean:write name="qyjbcwzbForm" property="nsrmc"  scope="request" filter="true" />
				</div>
			</td>
			<td width="10%" nowrap class="2-td2-t-left">
				<div align="right">联系电话&nbsp;&nbsp;</div>
			</td>
			<td width="15%" nowrap class="2-td2-t-center">
				<div align="left">&nbsp;
				<bean:write name="qyjbcwzbForm" property="zcdzlxdh"  scope="request" filter="true" />
				</div>
			</td>
		</tr>
		</table><br>

		<table width="100%" border="0" cellpadding="0" cellspacing="0" id="QYJBCWZB">
		<ttsoft:smsbtable form="qyjbcwzbForm" property="dataList" tableId="QYJBCWZB" hasTitle="true" tabIndexStart="10"/>
		</table>

		</td>
	</tr>
	</table><br>

	<div align="center">
		&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="u" tabIndex="-1"  onClick="doReset();return false;" onMouseOver="MM_swapImage('qingchun','','<%=static_contextpath%>images/qc-u2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/qc-u1.jpg" value="清除"  width="79" height="22" id="qingchun" style="cursor:hand" >
		&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="s" tabIndex="-1"  onClick="doSave();return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/bc-s1.jpg" value="保存"  width="79" height="22" id="baocun" style="cursor:hand" >
     	&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="x" tabIndex="-1" onClick="doDelete();return false;"onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="删除" src="<%=static_contextpath%>images/qbsc-x1.jpg" width="79" height="22" id="shanchu" style="cursor:hand">
       	&nbsp;&nbsp;&nbsp;&nbsp;
               	<% if(request.getParameter("from") != null) { %>
                    <input type="hidden"  name="from" value="<%=request.getParameter("from") %>" />
                  	<input type="image" accesskey="f" tabIndex="-1" onClick="doReturn('<%=request.getContextPath() %>/webapp/smsb/qysdsnbAction.do');return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" style="cursor:hand">
               	<% }else{ %>
       				<input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand">
               	<% } %>
	</div><BR>

	</td>
</tr>
</table>
<%@ include file="include/footer.jsp" %>
<br>
</td>

</html:form>
</tr>
</table>

<script language="javascript"> 
/****如果该纳税人为非正常户，则显示提示信息****/
/****20050817 Huxiaofeng****/
var nsrzt = <ttsoft:write name="qyjbcwzbForm" property="nsrzt" filter="false"/>;
checkNsrzt();
/**************end *******/
</script>

</body>
</html:html>


