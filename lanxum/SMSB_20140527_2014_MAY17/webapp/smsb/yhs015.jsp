<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>

<%@ page contentType="text/html; charset=GBK" %>
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>印花税年度申报表</title>

<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">

<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/Bolan.js"></script>
<script language=JavaScript src="../js/MathString.js"></script>
<script language=JavaScript src="../js/Stack.js"></script>
<script language=JavaScript src="../js/reader.js"></script>

<script language=JavaScript>

<%/*保存*/%>
function doQuery()
{
	if(trim(document.forms[0].jsjdm.value) == ""){
		alert("请输入纳税企业的计算代码");
		return false;
	}
	doSubmitForm("Query");
}

function doSave()
{
	if(trim(document.forms[0].jsjdm.value) == ""){
		alert("请输入纳税企业的计算代码");
		return false;
	}
	doSubmitForm("Save");
}

function doDelete()
{
	if(trim(document.forms[0].jsjdm.value) == ""){
		alert("请输入纳税企业的计算代码");
		return false;
	}
	doSubmitForm("Delete");
}

//响应计算机代码的回车查询
function jsjdmQuery(){
      if(event.keyCode==13) event.keyCode = 9;
}


function getSzsmById(id)
{
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
  	return id.substring(index+1,id.length);
}

//计算公式定义
var mathArray = new Array();
mathArray[0] = new MathString("sjse=jsje*sl");
mathArray[1] = new MathString("sjse=fs*sl");

function compute_yhsnb(obj)
{

	if(obj.name == "sl"){
		if(!isNum(obj, null, null, null, 6, 5)) return false;
	}

	if(obj.name == "jsje"){
		if(!isNum(obj, null, null, null, 15, 2)) return false;
	}
	if(obj.name == "fs"){
		if(!isNum(obj, 0, null, null, 15, 0)) return false;
	}

   	var id = trim(window.event.srcElement.id + "");
	var sm = getSzsmById(id);
	if(sm == "161300" || sm == "161220"){
		computeFunction(obj, "fs", 1);
	}else{
		computeFunction(obj, "jsje", 1);
	}

	compute_sum();
}
function compute_yhsnb_noComputer(obj)
{

	if(obj.name == "sjse"){
		if(!isNum(obj, null, null, null, 15, 2)) return false;
	}
	compute_sum();
}
function compute_sum(){
	computeSameSum("fs", "hjfs");
	computeSameSum("jsje", "hjjsje");
	computeSameSum("sjse", "hjynse");
}
function fnOnLoad()
{
   document.forms[0].jsjdm.focus();
}
</script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload = "fnOnLoad()" >
<%@ include file="include/header.jsp" %>
<p>
<html:form action="/webapp/smsb/yhsAction.do" method="POST">
<html:hidden  property="actionType" />
<html:hidden  property="iszhsb" />
<html:hidden  property="cjrq" />

<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>
		<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
	  	<tr>
	   	<td valign="top">
		<table  align="center" cellspacing="0" class="table-99">

    	<tr>
      		<td class="1-td1">印花税年度纳税申报表</td>
    	</tr>
    	<tr>
      		<td class="1-td2"><br>
			  	<table  width="100%" border="0" cellpadding="0" class="table-99" onkeydown="jsjdmQuery()">
			    <tr class="black9">
			      	<td width="23%" align="center" nowrap>申报日期：
			      		<html:text property="sbrq" size="8" maxlength="8"
			      		  onchange="getStartEndDate(this,document.forms[0].skssksrq,document.forms[0].skssjsrq, 1)"
			      		tabIndex="2"/>
			      	</td>
			      	<td width="54%" align="center" nowrap>
			      		<div align="left">税款所属日期：
			          	<!--<html:text property="skssksrq" size="8"  maxlength="8"  onchange="isDate(this,1)" tabIndex="2" /> 至
			          	<html:text property="skssjsrq" size="8" maxlength="8"   onchange="isDate(this,1)" tabIndex="2" />-->
                                         <html:text property="skssksrq" size="11" maxlength="8" tabIndex="2" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,0,0)"/>至
                                         <html:text property="skssjsrq" size="11" maxlength="8" tabIndex="2" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,0,1)"/>
			        	</div>
			        </td>
			      	<td width="23%" align="right" nowrap>金额单位：元(列至角分)</td>
			  	</tr>
				<tr>
			  		<td colspan="3">
			    	<table width="100%" border="0" cellpadding="0" cellspacing="0">
			      	<tr>
			        	<td width="15%" nowrap class="2-td2-t-left">&nbsp;
			        		<strong>税务计算机代码</strong>
			        	</td>
			        	<td width="15%" nowrap class="2-td2-t-left">
			        		&nbsp;
			           		<html:text property="jsjdm" size="8" maxlength="8" onchange="doQuery();return false;"  onkeydown="if(event.keyCode==13) event.keyCode=9;" tabIndex="2"/>
			          	</td>
			        	<td width="15%" nowrap class="2-td2-t-left">&nbsp;
			        		<strong>单位名称(公章)</strong>&nbsp;&nbsp;
			        	</td>
			        	<td width="30%" nowrap class="2-td2-t-left">
			        		&nbsp;
			        		<ttsoft:write name='yhsForm' property='nsrmc'/>

			        	</td>
			        	<td width="10%" nowrap class="2-td2-t-left">
			        		<strong>联系电话</strong>
			        	</td>
			        	<td width="15%" nowrap class="2-td2-t-center">
			        		&nbsp;
			        		<ttsoft:write name='yhsForm' property='qylxdh'/>

			        	</td>
			      	</tr>
			    	</table>
			    	</td>
				</tr>
				</table><BR>

            <table  width="100%" cellspacing="0" cellpadding="0"  class="table-99" border="1" onkeydown="jsjdmQuery()">
              <tr>
                <td colspan="2" class="2-td1-left" > 税 目</td>
                <td valign=top class="2-td1-left"> 份 数</td>
                <td valign=top class="2-td1-left"> 计税金额 </td>
                <td valign=top class="2-td1-left"> 税率(单位税额) </td>
                <td valign=top class="2-td1-left"> 已纳税额 </td>
            	</tr>
            <bean:define id="items" name="yhsForm" property="dataList"/>
            <logic:iterate id="item" name="items" >
			<% String szsmdm=(String)((Map)item).get("szsmdm"); %>
			<%if(szsmdm.equals("161210")){%>
				<tr>
                <td rowspan=2 valign="middle" class="2-td2-left">
                	<p align="center">帐簿
                </td>
                <td class="2-td2-left">
				   <input type="text" name="szsmmc" id="szsmmc_<%=szsmdm%>" value="<ttsoft:write name="item" property="szsmmc"/>" readonly class="inputnoborder" tabindex="-1" />
				   <input type="hidden" name="szsmdm" id="szsmdm_<%=szsmdm%>" value="<ttsoft:write name="item" property="szsmdm"/>" />
				</td>
                <td class="2-td2-left">
				   <input name="fs" tabIndex="2" type="text" class="inputalignright" value="<ttsoft:write name="item" property="fs"/>" id="fs_<%=szsmdm%>" size="8" maxlength="8" onchange="compute_yhsnb(this)" >
			 	</td>
				<td class="2-td2-left">
					<input name="jsje" tabIndex="2" type="text" class="inputalignright" value="<ttsoft:write name="item" property="jsje"/>" id="jsje_<%=szsmdm%>" size="17" maxlength="16" onchange="compute_yhsnb(this)" >
				</td>
				<td class="2-td2-left">
					<input type="text" name="sl" id="sl_<%=szsmdm%>" value="<ttsoft:write name="item" property="sl"/>" size="8" readonly class="inputnoborder" tabindex="-1" />
				</td>
				<td class="2-td2-center">&nbsp;
				    <input name="sjse" type="text" value="<ttsoft:write name="item" property="sjse"/>" id="sjse_<%=szsmdm%>"   size="17" maxlength="16" class="inputalignright" tabindex="2" onchange="compute_yhsnb_noComputer(this)" />
				</td>
              	</tr>
			<%}else if(szsmdm.equals("161220")){%>
			<tr>
                <td class="2-td2-left">
				   <input type="text" name="szsmmc" id="szsmmc_<%=szsmdm%>" value="<ttsoft:write name="item" property="szsmmc"/>" readonly class="inputnoborder" tabindex="-1" />
				   <input type="hidden" name="szsmdm" id="szsmdm_<%=szsmdm%>" value="<ttsoft:write name="item" property="szsmdm"/>" />
				</td>
				<td class="2-td2-left">
                   <input type="text" name="fs" tabIndex="2" id="fs_<%=szsmdm%>" class="inputalignright" value="<ttsoft:write name="item" property="fs"/>" size="8" maxlength="8" onchange="compute_yhsnb(this)" />
              	</td>
				<td class="2-td2-left">
				   <input name="jsje" tabIndex="2" type="text" value="<ttsoft:write name="item" property="jsje"/>" id="jsje_<%=szsmdm%>"  size="17" maxlength="16" readonly class="inputnoborder" tabindex="-1" />
                </td>
				<td class="2-td2-left">
				   <input type="text" name="sl" id="sl_<%=szsmdm%>" value="<ttsoft:write name="item" property="sl"/>" size="8" readonly class="inputnoborder" tabindex="-1" />
				</td>
                <td class="2-td2-center">&nbsp;
				   <input name="sjse" type="text" id="sjse_<%=szsmdm%>" value="<ttsoft:write name="item" property="sjse"/>"   size="17" maxlength="16" class="inputalignright" tabindex="2"  onchange="compute_yhsnb_noComputer(this)"/>
			    </td>
              </tr>
			<%}else if(szsmdm.equals("161300")){%>
              <tr>
                <td colspan="2" class="2-td2-left">
				   <input type="text" name="szsmmc" id="szsmmc_<%=szsmdm%>" value="<ttsoft:write name="item" property="szsmmc"/>" readonly class="inputnoborder" tabindex="-1" />
				   <input type="hidden" name="szsmdm" id="szsmdm_<%=szsmdm%>" value="<ttsoft:write name="item" property="szsmdm"/>" />
				</td>
                <td class="2-td2-left">
                    <input name="fs" type="text" tabIndex="2" class="inputalignright" id="fs_<%=szsmdm%>" value="<ttsoft:write name="item" property="fs"/>" size="8" maxlength="8"  onchange="compute_yhsnb(this)" >
                </td>
                <td class="2-td2-left">
					<input name="jsje" type="text"  id="jsje_<%=szsmdm%>" value="<ttsoft:write name="item" property="jsje"/>"  size="17" maxlength="16" readonly class="inputnoborder" tabindex="-1" />
				</td>
                <td class="2-td2-left">
					<input type="text" name="sl" id="sl_<%=szsmdm%>" value="<ttsoft:write name="item" property="sl"/>" size="8" readonly class="inputnoborder" tabindex="-1" />
				</td>
                <td class="2-td2-center">&nbsp;
					<input name="sjse" type="text" id="sjse_<%=szsmdm%>" value="<ttsoft:write name="item" property="sjse"/>"   size="17" maxlength="16" class="inputalignright" tabindex="2" onchange="compute_yhsnb_noComputer(this)" />
                </td>
              </tr>
			<%}else if(szsmdm.equals("161400")){%>
			  <tr>
                <td colspan="2" class="2-td2-left">
				   <input type="text" name="szsmmc" id="szsmmc_<%=szsmdm%>" value="<ttsoft:write name="item" property="szsmmc"/>" readonly class="inputnoborder" tabindex="-1" />
				   <input type="hidden" name="szsmdm" id="szsmdm_<%=szsmdm%>" value="<ttsoft:write name="item" property="szsmdm"/>" />
				</td>
                <td class="2-td2-left">
					<input name="fs" tabIndex="2" type="text" class="inputalignright" id="fs_<%=szsmdm%>" value="<ttsoft:write name="item" property="fs"/>" size="8" maxlength="8" onchange="compute_yhsnb(this)" >
				</td>
                <td class="2-td2-left">
					<input name="jsje" tabIndex="2" type="text" class="inputalignright" id="jsje_<%=szsmdm%>" value="<ttsoft:write name="item" property="jsje"/>"  size="17" maxlength="16"  onchange="compute_yhsnb(this)" >
				</td>
                <td class="2-td2-left">
					<input type="text" name="sl" id="sl_<%=szsmdm%>" value="<ttsoft:write name="item" property="sl"/>"  size="8"  onchange="compute_yhsnb(this)" >
				</td>
                <td class="2-td2-center">&nbsp;
					<input name="sjse" type="text" id="sjse_<%=szsmdm%>" value="<ttsoft:write name="item" property="sjse"/>"   size="17" maxlength="16" class="inputalignright" tabindex="2" onchange="compute_yhsnb_noComputer(this)" >
				</td>
              </tr>
			<%}else{%>
			  <tr>
                <td colspan="2" class="2-td2-left">
				   <input type="text" name="szsmmc" id="szsmmc_<%=szsmdm%>" value="<ttsoft:write name="item" property="szsmmc"/>" readonly class="inputnoborder" tabindex="-1" />
				   <input type="hidden" name="szsmdm" id="szsmdm_<%=szsmdm%>" value="<ttsoft:write name="item" property="szsmdm"/>" readonly class="inputnoborder" tabindex="-1" />
				</td>
                <td class="2-td2-left">
					<input name="fs" tabIndex="2" type="text" class="inputalignright" id="fs_<%=szsmdm%>" value="<ttsoft:write name="item" property="fs"/>" size="8" maxlength="8" onchange="compute_yhsnb(this)" ></td>
                <td class="2-td2-left">
					<input name="jsje" tabIndex="2" type="text" class="inputalignright" id="jsje_<%=szsmdm%>" value="<ttsoft:write name="item" property="jsje"/>"  size="17" maxlength="16"  onchange="compute_yhsnb(this)" ></td>
                <td class="2-td2-left">
					<input type="text" name="sl" id="sl_<%=szsmdm%>" value="<ttsoft:write name="item" property="sl"/>" size="8" readonly class="inputnoborder" tabindex="-1" ></td>
                <td class="2-td2-center">&nbsp;
					<input name="sjse" type="text" id="sjse_<%=szsmdm%>" value="<ttsoft:write name="item" property="sjse"/>"   size="17" maxlength="16" class="inputalignright" tabindex="2" onchange="compute_yhsnb_noComputer(this)" > </td>
              </tr>
			<%}%>
           </logic:iterate>
            <tr>
			    <td colspan="2" class="2-td2-left">合计</td>
			    <td class="2-td2-left">
			    	<input type="text" name="hjfs" value="" size="15" readonly class="inbright" tabindex="-1" />
			    </td>
			    <td class="2-td2-left">
			    	<input type="text" name="hjjsje" value="" size="17" readonly class="inbright" tabindex="-1" />
			    </td>
				<td class="2-td2-left">&nbsp;</td>
				<td class="2-td2-center">&nbsp;
					<input type="text" name="hjynse" value="" size="15" readonly class="inbright" tabindex="-1" />
				</td>
            </tr>
            </table><BR>

			<script language="javascript" >
			compute_sum();
			</script>

     		<table width="150" border="0" cellpadding="0" cellspacing="0" class="table-99">
              <tr>
                    <br>
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   <input type="image" accesskey="s"  onClick="doSave();return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/bc-s1.jpg" value="保存"  width="79" height="22" id="baocun" style="cursor:hand" >
                   &nbsp;&nbsp;&nbsp;&nbsp;
                   <input type="image" accesskey="d"  onClick="doDelete();return false;" onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="清除" src="<%=static_contextpath%>images/sc-d1.jpg" width="79" height="22" id="shanchu" style="cursor:hand">
                   &nbsp;&nbsp;&nbsp;&nbsp;
                   <input type="image" accesskey="c"  onClick="tuichu();return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand">
                   </br>
              </tr>
            </table>
			<br>
			<br>
    </td>
  </tr>
 </table>
 <%@ include file="include/footer.jsp" %>
</html:form>
<SCRIPT LANGUAGE="JavaScript">
<!--
/****如果该纳税人为非正常户，则显示提示信息****/
/****20050817 HU xiaofeng****/
var nsrzt = <ttsoft:write name="yhsForm" property="nsrzt" filter="false"/>;
checkNsrzt();
/*************end*****************/
</SCRIPT>
</body>
</html:html>
