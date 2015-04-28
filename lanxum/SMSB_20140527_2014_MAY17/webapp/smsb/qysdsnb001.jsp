<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>

<jsp:useBean type="com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web.QysdsnbForm" scope="request" id="qysdsnbForm"/>

<html:html>
<head>
<title>企业所得税年度纳税申报表</title>

<script language="JavaScript" type="text/JavaScript" src="../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language=JavaScript src="../js/reader.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<style type="text/css">
<!--
	@import url(../css/text.css);
-->
</style>
<script language=JavaScript>
	//判断迁移画面是否可以正常换行的标示符
	var addRowFlg = true;

function setFoucs(){
	document.forms[0].jsjdm.focus();
}

// 检测用户是否输入计算机代码
function check_jsjdm(){
	if(trim(document.forms[0].jsjdm.value) == "" || document.forms[0].nsrmc.value == ""){
		alert("请输入纳税企业计算机代码！");
		return false;
	}
	return true;
}

<%/*保存*/%>
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

	//if(!isNum(document.forms[0].bqljs_96, 0, null, 1, 13, 0)) return false;

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
	   	for(var i=1; i < 105; i++){
			obj1 = eval("document.forms[0].bqljs_" + i);
			obj1.value = "";
	   	}
    }
}

<%/*删除*/%>
function doDelete()
{
	if(!check_jsjdm()) return false;

	if(!isDate(document.forms[0].sbrq, "true")){
		return false;
	}

	doSubmitForm('Delete');
}

//响应计算机代码的回车查询
function jsjdmQuery(){
	if(event.keyCode==13) event.keyCode = 9;
}

function doReturn(strUrl){
	if(!check_jsjdm()) return false;

	var param = "actionType=Query"
		param = param + "&jsjdm=" + document.forms[0].jsjdm.value;
		param = param + "&sbrq=" + document.forms[0].sbrq.value;
		param = param + "&skssksrq=" + document.forms[0].skssksrq.value;
		param = param + "&skssjsrq=" + document.forms[0].skssjsrq.value;
		param = param + "&from=nb";
	window.location = strUrl + "?" + param;
}

</script>

<script language="javascript">

</script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onLoad="setFoucs()">
<%@ include file="include/header.jsp" %>
<html:form action="/webapp/smsb/qysdsnbAction.do" method="POST">
<html:hidden property="actionType" value="Show" />
<html:hidden property="nsrmc" />
<html:hidden property="zgswjgzzjgdm" />
<html:hidden property="swjgzzjgdm" />
<html:hidden property="qxdm" />
<html:hidden property="fsdm" />
<html:hidden property="zcdzlxdh" />
<html:hidden property="djzclxdm" />
<html:hidden property="lrrq" />
<html:hidden property="cjrq" />
<html:hidden property="iszhsb"/>
<html:hidden property="xzqy"/>
<html:hidden property="zssl" />
<html:hidden property="zssl2" />
<html:hidden property="zsfsdm" />
<html:hidden property="zsfsdm2" />
<html:hidden property="gxjsqy" />
<html:hidden property="xzqyjm"/>
<html:hidden property="cyl"/>
<td align="center" valign="top"> <br>

<div id="smsb_qysdsnb" >
	<table align="center" cellspacing="0" class="table-99" onkeydown="jsjdmQuery()">
	<tr>
		<td class="1-td1">企业所得税年度纳税申报表</td>
	</tr>
	<tr>
	<td valign="top" class="1-td2">

		<table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=static_contextpath%>images/biaopqian-bg.jpg">
		<tr>

			<td background="<%=static_contextpath%>images/biaopqian-bg.jpg" align="left">
			<a href="#" onclick="doReturn('<%=request.getContextPath() %>/webapp/smsb/qyjbcwzbAction.do')" >企业基本财务指标情况表</a>  &nbsp;&nbsp;
			<%
				if(	CodeConstant.DJZCLXDM_SYDW.equals(qysdsnbForm.getDjzclxdm()) ||
					CodeConstant.DJZCLXDM_SHTT.equals(qysdsnbForm.getDjzclxdm())){
			%>
			<a href="#" onclick="doReturn('<%=request.getContextPath() %>/webapp/smsb/sydwshttsrsbAction.do')" >事业单位、社会团体收入申报表</a>
			<% } %>
		</tr>
		</table><BR><BR>

		<table cellspacing="0" class="table-99">
		<tr class="black9">
			<td align="left" nowrap>
				<div align="left">申报日期：
				<html:text size="8" maxLength="8" property="sbrq" tabIndex="1"  onchange="getStartEndDate(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,1)" />
				</div>
			</td>
			<td align="center" nowrap>
				<div align="left">税款所属日期：
				<!--<html:text size="8" maxLength="8" property="skssksrq" tabIndex="2" />&nbsp;至
				<html:text size="8" maxLength="8" property="skssjsrq" tabIndex="3" />-->
                                 <html:text property="skssksrq" size="11" maxlength="8" tabIndex="2" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,0,0)"/>至
                                 <html:text property="skssjsrq" size="11" maxlength="8" tabIndex="3" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,0,1)"/>
				</div>
			</td>
			<td align="right" nowrap>金额单位：元</td>
		</tr>
		</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="15%" nowrap class="2-td2-t-left"><div align="right">计算机代码&nbsp;&nbsp;</div></td>
			<td width="15%" nowrap class="2-td2-t-left" align=left >
				<html:text property="jsjdm" size="8" maxlength="8" tabindex="4" onchange="doQuery()"  onKeyDown="jsjdmQuery()" />
			</td>
			<td width="15%" nowrap class="2-td2-t-left"><div align="right">申报单位名称（公章）&nbsp;&nbsp;</div></td>
			<td width="30%" nowrap class="2-td2-t-left"  align=left >&nbsp;
				<bean:write name="qysdsnbForm" property="nsrmc" scope="request" filter="true" />
			</td>
			<td width="10%" nowrap class="2-td2-t-left"><div align="right">联系电话&nbsp;&nbsp;</div></td>

			<td width="15%" nowrap class="2-td2-t-left">&nbsp;
				<bean:write name="qysdsnbForm" property="zcdzlxdh" scope="request" filter="true" />
			</td>
		</tr>
		</table><br>

		<!-- 在这里放置企业所得税年度纳税申报表数据 -->

		<table width="100%" border="0" cellpadding="0" cellspacing="0" id="QYSDSNB">
		<ttsoft:smsbtable form="qysdsnbForm" property="dataList" tableId="QYSDSNB" hasTitle="true" tabIndexStart="10"/>
		<input type="hidden" name="bqljs_105"/>
                <input type="hidden" name="bqljs_106"/>
                </table>

		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr class="black9">
			<td align="center" nowrap>
			<div align="left"><br>&nbsp;说明1：实行核定纯益率征收方式缴纳企业所得税的纳税人，在进行年度申报时需填写《企业所得税年度纳税申报表》第1行、16至22行、57行、58行、62至76行、<br>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;78行、80行、83行至104行的有关项目；如有需要补税投资收益的还需填报《联营、股份、中外合资、投资企业收入纳税计算表》。 </div>
														
			</td>
		</tr>
		<tr class="black9">
			<td align="center" nowrap>
				<div align="left" width="600">
					&nbsp;说明2：若您发生弥补以前年度亏损,研究新产品、新技术、新工艺费用抵扣额事项，向主管税务机关报送报备资料并办理备案事宜。
				</div>
			</td>
		</tr>
		</table><br>

		<div align="center">
			<input type="image" accesskey="u" tabIndex="-1" style="cursor:hand"
				onClick="doReset();return false;"
				onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/qc-u1.jpg" name="Image13" width="79" height="22" border="0" id='qingchu'>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

			<input type="image" accesskey="s" tabIndex="-1" style="cursor:hand"
				onClick="doSave();return false;"
				onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/bc-s1.jpg" name="Image13" width="79" height="22" border="0" id='baocun'>

			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

			<input type="image" accesskey="x" tabIndex="-1"  style="cursor:hand"
				onClick="doDelete();return false;"
				onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/qbsc-x1.jpg" name="Image13" width="79" height="22" border="0" id='shanchu'>

                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand">

		</div><BR>
	</td>
	</tr>
	</table><br>

</div><!-- End of smsb_qysdsnb -->

<script language="javascript">
document.forms[0].bqljs_105.value = <%=qysdsnbForm.getYhbl()%>;
//2003年12月25号（需求变更）乡镇企业减免函数
function xzqyjme()
{ <%
  if(qysdsnbForm.isXzqy()){
                %>
  			document.forms[0].bqljs_81.value=round(document.forms[0].bqljs_80.value*document.forms[0].xzqyjm.value);
                <%
  }
%>
}
//document.forms[0].xzqyjm.value = <%=qysdsnbForm.getXzqyjm()%>;
// 获得当前对象
function getCellObject(){
   	var id = trim(window.event.srcElement.id + "");
  	var hc = getHcByID(id)
  	var obj = eval("document.forms[0].bqljs_" + hc);
  	return obj;
}


// 计算适用税率
function compute_sysl(){
<%
if (qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) { // 纯益征收
%>
	// 收入
	var sr = getNumber(document.forms[0].bqljs_1);
	// 纯益率
	var cyl = getNumber(document.forms[0].zssl);
	// 应纳税所得额
	var ynssde = sr*cyl;
	// 研究新产品、新技术、新工艺费用抵扣额
	var dke = getNumber(document.forms[0].bqljs_77);
	// 抵扣后的应纳税所得额
	var dkhynssde = ynssde - dke;
	if(dkhynssde < 0) dkhynssde = 0;

	// 计算适用税率
	var sysl;
	if(dkhynssde <= 30000.00000000){
    	sysl = 0.18;
	}else if(dkhynssde <= 100000.00000000){
    	sysl = 0.27
	}else{
    	sysl = 0.33;
	}
	//modified by hazhengze 20051221 start
	var value_62=trim(document.forms[0].bqljs_62.value);
	if(parseFloat(value_62>=0)){
		setValue('bqljs_76',ynssde);
		setValue('bqljs_78',dkhynssde);
	}else{
		setValue('bqljs_76',"0");
		setValue('bqljs_78',"0");
	}
	//modified by hazhengze 20051221 end
	setValue('bqljs_79',sysl);

<%
} else if(qysdsnbForm.isGxjsqy()) { // 高新技术企业
%>
	setValue('bqljs_79', document.forms[0].zssl.value);
<%
} else {	//普通企业
%>
	// 研究新产品、新技术、新工艺费用抵扣额
	var dkhynssde = getNumber(document.forms[0].bqljs_78);

	//计算适用税率
	var sysl;
	if(dkhynssde < 0){
	    sysl = 0.00;
	}else if(dkhynssde <= 30000.00000000){
	    sysl = 0.18;
	}else if(dkhynssde <= 100000.00000000){
	    sysl = 0.27
	}else{
	    sysl = 0.33;
	}
	setValue('bqljs_79', sysl);
<%
}
%>

}


//lufeng 2004-04-04 计算适用税率
function compute_sysl4(){
<%
if (qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) { // 纯益征收
%>
	// 收入
	var sr = getNumber(document.forms[0].bqljs_1);
	// 纯益率
	var cyl = getNumber(document.forms[0].zssl);
	// 应纳税所得额
	var ynssde = sr*cyl;
	// 研究新产品、新技术、新工艺费用抵扣额
	var dke = getNumber(document.forms[0].bqljs_77);
	// 抵扣后的应纳税所得额
	var dkhynssde = ynssde - dke;
	if(dkhynssde < 0) dkhynssde = 0;

	// 计算适用税率
	var sysl;
	if(dkhynssde <= 30000.00000000){
    	sysl = 0.18;
	}else if(dkhynssde <= 100000.00000000){
    	sysl = 0.27
	}else{
    	sysl = 0.33;
	}
	
	//lufeng 2004-04-04
	if (document.forms[0].bqljs_79.value!=""){
		sysl = document.forms[0].bqljs_79.value;
	}
	
	
	//modified by hazhengze 20051221 start
	var value_62=trim(document.forms[0].bqljs_62.value);
	if(parseFloat(value_62>=0)){
		setValue('bqljs_76',ynssde);
		setValue('bqljs_78',dkhynssde);
	}else{
		setValue('bqljs_76',"0");
		setValue('bqljs_78',"0");
	}
	//modified by hazhengze 20051221 end
	setValue('bqljs_79',sysl);

<%
} else if(qysdsnbForm.isGxjsqy()) { // 高新技术企业
%>
	setValue('bqljs_79', document.forms[0].bqljs_79.value);
	
<%
} else {	//普通企业
%>
	// 研究新产品、新技术、新工艺费用抵扣额
	var dkhynssde = getNumber(document.forms[0].bqljs_78);

	//计算适用税率
	var sysl;
	if(dkhynssde < 0){
	    sysl = 0.00;
	}else if(dkhynssde <= 30000.00000000){
	    sysl = 0.18;
	}else if(dkhynssde <= 100000.00000000){
	    sysl = 0.27
	}else{
	    sysl = 0.33;
	}
	
	//lufeng 2004-04-04
	if (document.forms[0].bqljs_79.value!=""){
		sysl = document.forms[0].bqljs_79.value;
	}
	
	setValue('bqljs_79', sysl);
<%
}
%>

}
//重新设置域表中设置的只读属性
//Shi Yanfeng 20040113
function setReadonly(){
	//设置79项
		document.forms[0].bqljs_79.readOnly=false;
  		document.forms[0].bqljs_79.className='inputalignright';
	//设置92项
		document.forms[0].bqljs_92.readOnly=false;
  		document.forms[0].bqljs_92.className='inputalignright';
	//设置94项
		document.forms[0].bqljs_94.readOnly=false;
  		document.forms[0].bqljs_94.className='inputalignright';
}
//设置只读属性为普通
setReadonly();
//Insert  Start  Zhou kejing 20040106
//企业所度税年度申报表，第。76（6）项，78（7）项，80（8）行，
//88（9）行，92（10）行，94（11）行。不能为负，为负设为0。
function action_on_Negative(){
  	var value_76 = trim(document.forms[0].bqljs_76.value);
  	if( value_76 != "" ){
     		if(parseFloat(value_76) < 0.00000)
     			document.forms[0].bqljs_76.value = value_76 = 0;
  	}
  	var value_78 = trim(document.forms[0].bqljs_78.value);
  	if( value_78 != "" ){
     		if(parseFloat(value_78) < 0.00000)
     			document.forms[0].bqljs_78.value = value_78 = 0;
  	}
  	var value_80 = trim(document.forms[0].bqljs_80.value);
  	if( value_80 != "" ){
     		if(parseFloat(value_80) < 0.00000)
     			document.forms[0].bqljs_80.value = value_80 = 0;
  	}
  	var value_88 = trim(document.forms[0].bqljs_88.value);
  	if( value_88 != "" ){
     		if(parseFloat(value_88) < 0.00000)
     			document.forms[0].bqljs_88.value = value_88 = 0;
  	}
	/*******Shi Yanfeng 20040112 92,94可以为负值**********/
  	//var value_92 = trim(document.forms[0].bqljs_92.value);
  	//if( value_92 != "" ){
    // 		if(parseFloat(value_92) < 0.00000)
    // 			document.forms[0].bqljs_92.value = value_92 = 0;
  	//}
  	//var value_94 = trim(document.forms[0].bqljs_94.value);
  	//if( value_94 != "" ){
    // 		if(parseFloat(value_94) < 0.00000)
   //  			document.forms[0].bqljs_94.value = value_94 = 0;
  	//}  	  	  	  	  	  	
}
//Insert  End    Zhou kejing 20040106

// 计算行次78
function compute_cell78(){

	//确定企业的适用税率
  	compute_sysl();

   	//取得年报第78行次的状态
   	var new_state = 1;
  	var value_78 = trim(document.forms[0].bqljs_78.value);
  	if( value_78 != "" ){
     	if(parseFloat(value_78) <= 0.00000)
     		new_state = -1;
  	}

 	// 如果计算结果为负数，则不做检查，仍按照负数进行计算；如果78行（抵扣后的应纳税所得额）
    // 为0或者为负数，从第78行到第95行默认为0，可以录入，但不再根据计算公式计算；第95行不能
    // 为负数如果计算结果为负数，则不做检查，仍按照负数进行计算；如果78行（抵扣后的应纳税所得额）
    // 为0或者为负数，从第78行到第95行默认为0，可以录入，但不再根据计算公式计算；第95行不能为负数
 	if(new_state < 0){
		// 设置行次79-95
		for(var i=79; i<=95; i++){
	   		eval("document.forms[0].bqljs_"+i).onchange = function(){
	   			return isNum(getCellObject() , null, 9999999999999, null, 16, 2);
	   		};
            eval("document.forms[0].bqljs_"+i).onclick = null;
	   		eval("document.forms[0].bqljs_"+i).readOnly = false;
	   		eval("document.forms[0].bqljs_"+i).className = 'inputalignright';
//Delete  Start  Zhou kejing 20040113
			//if( i==89 || i==90 || i==91 || i==93)
	   		//	eval("document.forms[0].bqljs_"+i).value = 0;
//Delete  End    Zhou kejing 20040113	   		
		}
//Insert  Start  Zhou kejing 20040106
		//除在此处增加，有关78行的处理还有"78行次附加处理",见function FN_return(qylx,nbhc)
		for(var i=79; i<=82; i++){
	   		eval("document.forms[0].bqljs_"+i).value = 0;		
		}
		for(var i=87; i<=95; i++){
	   		eval("document.forms[0].bqljs_"+i).value = 0;		
		}
		// 设置行次80的值不能为负数
   		document.forms[0].bqljs_80.onchange = function(){
   			return isNum(getCellObject() , 0, 9999999999999, null, 16, 2);
   		};
		// 设置行次88的值不能为负数
   		document.forms[0].bqljs_88.onchange = function(){
   			return isNum(getCellObject() , 0, 9999999999999, null, 16, 2);
   		};
		/*******Shi Yanfeng 20040112 92,94可以为负值**********/
		// 设置行次92的值不能为负数
   		//document.forms[0].bqljs_92.onchange = function(){
   		//	return isNum(getCellObject() , 0, 9999999999999, null, 16, 2);
   		//};
		// 设置行次94的值不能为负数
   		//document.forms[0].bqljs_94.onchange = function(){
   		//	return isNum(getCellObject() , 0, 9999999999999, null, 16, 2);
   		//};
	    	document.forms[0].bqljs_83.onclick= onclick83;
	    	document.forms[0].bqljs_83.className='txtinput-link';
	    	document.forms[0].bqljs_83.readOnly = true;

	    	document.forms[0].bqljs_84.onclick= onclick84;
	    	document.forms[0].bqljs_84.className='txtinput-link';
	    	document.forms[0].bqljs_84.readOnly = true;

	    	document.forms[0].bqljs_85.onclick= onclick85;
	    	document.forms[0].bqljs_85.className='txtinput-link';
	    	document.forms[0].bqljs_85.readOnly = true;

	    	document.forms[0].bqljs_86.onclick= onclick86;
	    	document.forms[0].bqljs_86.className='txtinput-link';
	    	document.forms[0].bqljs_86.readOnly = true;
	    		   		   		   		
//Insert  End    Zhou kejing 20040106		
		// 设置行次95的值不能为负数
   		document.forms[0].bqljs_95.onchange = function(){
   			return isNum(getCellObject() , 0, 9999999999999, null, 16, 2);
   		};
		document.forms[0].bqljs_78.value = 0.00;
 	}else{
		// 设置行次80-95
    	for(var i=80;i<=95;i++){
       		//if(i>82 && i< 87) continue;
       		eval("document.forms[0].bqljs_"+i).onchange = compute_nb;
    	}
    	document.forms[0].bqljs_80.readOnly=true;
    	document.forms[0].bqljs_80.className='inbright';

    	document.forms[0].bqljs_88.readOnly=true;
    	document.forms[0].bqljs_88.className='inbright';

    	//document.forms[0].bqljs_79.readOnly=true;
    	//document.forms[0].bqljs_79.className='inbright';
    	
		/** Shi Yanfeng 20040112 92,94行任何值都可以编辑**/

//    	document.forms[0].bqljs_92.readOnly=true;
//    	document.forms[0].bqljs_92.className='inbright';

//    	document.forms[0].bqljs_94.readOnly=true;
//    	document.forms[0].bqljs_94.className='inbright';

    	document.forms[0].bqljs_95.readOnly=true;
    	document.forms[0].bqljs_95.className='inbright';
    	

		/**lufeng 2004-04-04, 79行的值可编辑**/
//    	document.forms[0].bqljs_79.readOnly=true;
//    	document.forms[0].bqljs_79.className='inbright';

		//79行次可以为负
		document.forms[0].bqljs_79.onchange=function(){
			//alert("11<><>"+document.forms[0].bqljs_79.value);
			//modified by hazhengze 200651226 Start
			<% if (!qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) { // 纯益率征收 %>
				compute_cell784();
			<%}%>
			//compute_cell784();
			//modified by hazhengze 200651226 End	
			return isNum(getCellObject(), null, null, null, 16, 2);
		}
		
//Insert  Start  Zhou kejing 20040113
	//92和94行次可以为负
	document.forms[0].bqljs_92.onchange=function(){
		return isNum(getCellObject(), null, null, null, 16, 2);
	}
	document.forms[0].bqljs_94.onchange=function(){
		return isNum(getCellObject(), null, null, null, 16, 2);
	}
//Insert  End    Zhou kejing 20040113

	    document.forms[0].bqljs_83.onclick= onclick83;
	    document.forms[0].bqljs_83.className='txtinput-link';
	    document.forms[0].bqljs_83.readOnly = true;

	    document.forms[0].bqljs_84.onclick= onclick84;
	    document.forms[0].bqljs_84.className='txtinput-link';
	    document.forms[0].bqljs_84.readOnly = true;

	    document.forms[0].bqljs_85.onclick= onclick85;
	    document.forms[0].bqljs_85.className='txtinput-link';
	    document.forms[0].bqljs_85.readOnly = true;

	    document.forms[0].bqljs_86.onclick= onclick86;
	    document.forms[0].bqljs_86.className='txtinput-link';
	    document.forms[0].bqljs_86.readOnly = true;

	    // 重新计算
	    compute_cell("bqljs_78");
            //乡镇企业减免计算
            xzqyjme();
  	} 	
}


//lufeng 2004-04-04 计算行次78
function compute_cell784(){

	//确定企业的适用税率
  	compute_sysl4();

   	//取得年报第78行次的状态
   	var new_state = 1;
  	var value_78 = trim(document.forms[0].bqljs_78.value);
  	if( value_78 != "" ){
     	if(parseFloat(value_78) <= 0.00000)
     		new_state = -1;
  	}

 	// 如果计算结果为负数，则不做检查，仍按照负数进行计算；如果78行（抵扣后的应纳税所得额）
    // 为0或者为负数，从第78行到第95行默认为0，可以录入，但不再根据计算公式计算；第95行不能
    // 为负数如果计算结果为负数，则不做检查，仍按照负数进行计算；如果78行（抵扣后的应纳税所得额）
    // 为0或者为负数，从第78行到第95行默认为0，可以录入，但不再根据计算公式计算；第95行不能为负数
 	if(new_state < 0){
		// 设置行次79-95
		for(var i=79; i<=95; i++){
	   		eval("document.forms[0].bqljs_"+i).onchange = function(){
	   			return isNum(getCellObject() , null, 9999999999999, null, 16, 2);
	   		};
            eval("document.forms[0].bqljs_"+i).onclick = null;
	   		eval("document.forms[0].bqljs_"+i).readOnly = false;
	   		eval("document.forms[0].bqljs_"+i).className = 'inputalignright';
//Delete  Start  Zhou kejing 20040113
			//if( i==89 || i==90 || i==91 || i==93)
	   		//	eval("document.forms[0].bqljs_"+i).value = 0;
//Delete  End    Zhou kejing 20040113	   		
		}
//Insert  Start  Zhou kejing 20040106
		//除在此处增加，有关78行的处理还有"78行次附加处理",见function FN_return(qylx,nbhc)
		for(var i=79; i<=82; i++){
	   		eval("document.forms[0].bqljs_"+i).value = 0;		
		}
		for(var i=87; i<=95; i++){
	   		eval("document.forms[0].bqljs_"+i).value = 0;		
		}
		// 设置行次80的值不能为负数
   		document.forms[0].bqljs_80.onchange = function(){
   			return isNum(getCellObject() , 0, 9999999999999, null, 16, 2);
   		};
		// 设置行次88的值不能为负数
   		document.forms[0].bqljs_88.onchange = function(){
   			return isNum(getCellObject() , 0, 9999999999999, null, 16, 2);
   		};
		/*******Shi Yanfeng 20040112 92,94可以为负值**********/
		// 设置行次92的值不能为负数
   		//document.forms[0].bqljs_92.onchange = function(){
   		//	return isNum(getCellObject() , 0, 9999999999999, null, 16, 2);
   		//};
		// 设置行次94的值不能为负数
   		//document.forms[0].bqljs_94.onchange = function(){
   		//	return isNum(getCellObject() , 0, 9999999999999, null, 16, 2);
   		//};
	    	document.forms[0].bqljs_83.onclick= onclick83;
	    	document.forms[0].bqljs_83.className='txtinput-link';
	    	document.forms[0].bqljs_83.readOnly = true;

	    	document.forms[0].bqljs_84.onclick= onclick84;
	    	document.forms[0].bqljs_84.className='txtinput-link';
	    	document.forms[0].bqljs_84.readOnly = true;

	    	document.forms[0].bqljs_85.onclick= onclick85;
	    	document.forms[0].bqljs_85.className='txtinput-link';
	    	document.forms[0].bqljs_85.readOnly = true;

	    	document.forms[0].bqljs_86.onclick= onclick86;
	    	document.forms[0].bqljs_86.className='txtinput-link';
	    	document.forms[0].bqljs_86.readOnly = true;
	    		   		   		   		
//Insert  End    Zhou kejing 20040106		
		// 设置行次95的值不能为负数
   		document.forms[0].bqljs_95.onchange = function(){
   			return isNum(getCellObject() , 0, 9999999999999, null, 16, 2);
   		};
		document.forms[0].bqljs_78.value = 0.00;
 	}else{
		// 设置行次80-95
    	for(var i=80;i<=95;i++){
       		//if(i>82 && i< 87) continue;
       		eval("document.forms[0].bqljs_"+i).onchange = compute_nb;
    	}
    	document.forms[0].bqljs_80.readOnly=true;
    	document.forms[0].bqljs_80.className='inbright';

    	document.forms[0].bqljs_88.readOnly=true;
    	document.forms[0].bqljs_88.className='inbright';

	    /**lufeng 20040112 79行任何值都可以编辑**/
    	//document.forms[0].bqljs_79.readOnly=true;
    	//document.forms[0].bqljs_79.className='inbright';
    	
		
		/** Shi Yanfeng 20040112 92,94行任何值都可以编辑**/

//    	document.forms[0].bqljs_92.readOnly=true;
//    	document.forms[0].bqljs_92.className='inbright';

//    	document.forms[0].bqljs_94.readOnly=true;
//    	document.forms[0].bqljs_94.className='inbright';

    	document.forms[0].bqljs_95.readOnly=true;
    	document.forms[0].bqljs_95.className='inbright';
    		
//Insert  Start  Zhou kejing 20040113
	//92和94行次可以为负
	document.forms[0].bqljs_92.onchange=function(){
		return isNum(getCellObject(), null, null, null, 16, 2);
	}
	document.forms[0].bqljs_94.onchange=function(){
		return isNum(getCellObject(), null, null, null, 16, 2);
	}
//Insert  End    Zhou kejing 20040113

	    document.forms[0].bqljs_83.onclick= onclick83;
	    document.forms[0].bqljs_83.className='txtinput-link';
	    document.forms[0].bqljs_83.readOnly = true;

	    document.forms[0].bqljs_84.onclick= onclick84;
	    document.forms[0].bqljs_84.className='txtinput-link';
	    document.forms[0].bqljs_84.readOnly = true;

	    document.forms[0].bqljs_85.onclick= onclick85;
	    document.forms[0].bqljs_85.className='txtinput-link';
	    document.forms[0].bqljs_85.readOnly = true;

	    document.forms[0].bqljs_86.onclick= onclick86;
	    document.forms[0].bqljs_86.className='txtinput-link';
	    document.forms[0].bqljs_86.readOnly = true;

	    // 重新计算
	    compute_cell("bqljs_78");
            //乡镇企业减免计算
            xzqyjme();
  	} 	
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

//added by hazhengze 20051226 Start
function onChange96(){
	///4.增加第96行“期末（平均）职工人数”，此行数据大于6000人时，系统提示，重新输入数据，系统保存重新输入的数据。
		var value_96 = trim(document.forms[0].bqljs_96.value);
		if(isNum(getCellObject(), 0, null, 1, 13, 0)){
			if( parseFloat(value_96) > 6000){
      	if (!confirm("您现在录入的职工人数大于6000，请确认！"))
        	return false;
    	}
  	}
		return isNum(getCellObject(), 0, null, 1, 13, 0);
	}
	
<% if (qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) { // 纯益率征收 %>
	function doNothing(){
		//return isNum(getCellObject() , null, 9999999999999, null, 16, 2);
	}
<%}%>
//added by hazhengze 20051226 End


function compute_nb()
{
	
   	var id = trim(window.event.srcElement.id + "");   	
   	
		//alert("行次"+getHcByID(id)+"被触发！");


	if(getHcByID(id) == 103 || getHcByID(id) == 104){
		return isNum(getCellObject(), 0, null, null, 13, 0);
	}
//Update  Start  Zhou kejing 20040106
/****Shi Yanfeng 20040112 第8项可以为负**/
	//if(getHcByID(id) == 13){
	if(getHcByID(id) == 13 ||getHcByID(id) == 14||getHcByID(id) == 25 || getHcByID(id) == 8 || getHcByID(id) == 16  || getHcByID(id) == 89 ){
//Update  End    Zhou kejing 20040106	
		if(!isNum(getCellObject(), null, null, null, 16, 2)) return false;
	}else{
		if(!isNum(getCellObject(), 0, null, null, 16, 2)) return false;
	}

   	compute_cell(id);
<%
	if(	!qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_DEZS)){ // 非定额征收
%>
//modified by hazhengze 200651226 Start
<% if (!qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) { // 纯益率征收 %>
	compute_cell78();
   	compute_cell784();
<%}%>
//compute_cell78();
  // 	compute_cell784();
//modified by hazhengze 200651226 End
   	

	//2003 12.5 计算81行，乡镇企业减免
	xzqyjme();
<%	}	%>
        //2003 12.5 计算81行，乡镇企业减免
	xzqyjme();

//Insert  Start  Zhou kejing 20040106
	//负数处理
	action_on_Negative();
	//高新企业+纯益率
<%
	if (qysdsnbForm.getZsfsdm2().equals(CodeConstant.ZSFSDM_CYLZS)  // 纯益征收
	    &&qysdsnbForm.isGxjsqy()) { // 高新技术企业
%>
	
		action_on_GXandCYL();
<%	}	%>

<% 	if (qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_DEZS)) { // 定额征收 %>
		document.forms[0].bqljs_95.value="";
<%	}	%>
//Insert  End    Zhou kejing 20040106	

//modified by hazhengze 20051220 18:20 start
	
	
	<% if (qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) { // 纯益率征收 %>
	///5.第62行“纳税调整后所得”，由系统按照计算公式即62行=1行×行业纯益率+16+21+22-57 （行业纯益率，按照目前系统规则取自税费管理模块“企业所得税征收方式核定表”）
	if(getHcByID(id) == 1||getHcByID(id) == 16||getHcByID(id) == 21||getHcByID(id) == 22||getHcByID(id) == 57){
		compute_62();
	}
	///6.第76行=62-63-64。
	///7.第78行“抵扣后应纳税所得额”：与76行数据一致。
	if(getHcByID(id) == 62 ||getHcByID(id) == 63 ||getHcByID(id) == 64 ){
		compute_76_78();
	}		
	///8.64=65+...+75;
	<%for(int ii=65;ii<76;ii++){%>
		if(getHcByID(id) == <%=ii%>){
			compute_64();
			//2.计算76和78
			compute_76_78();
		}
	<% }%>
	///9.57＝58+59+60+61
	if(getHcByID(id) == 58||getHcByID(id) == 59||getHcByID(id) == 60||getHcByID(id) == 61){
			var value_58 = getCylValue(trim(document.forms[0].bqljs_58.value)); 
			var value_59 = getCylValue(trim(document.forms[0].bqljs_59.value)); 
			var value_60 = getCylValue(trim(document.forms[0].bqljs_60.value));
			var value_61 = getCylValue(trim(document.forms[0].bqljs_61.value));
			var value_57=parseFloat(value_58)+parseFloat(value_59)+parseFloat(value_60)+parseFloat(value_61);
			document.forms[0].bqljs_57.value=value_57;
			//
			compute_62();
			onclick58();
	}	
	///10.88=80-81-82+83+...+87???
	if(getHcByID(id) == 81||getHcByID(id) == 82||getHcByID(id) == 83||getHcByID(id) == 84||getHcByID(id) == 85||getHcByID(id) == 86||getHcByID(id) == 87){
			//alert("111");
		compute_88();
	}
	///11.92=88+89+90-91???
	if(getHcByID(id) == 89||getHcByID(id) == 90||getHcByID(id) == 91){
		compute_92();
	}
	///12.94=91-92???
	if(getHcByID(id) == 91||getHcByID(id) == 92){
		compute_94();
	}
	///13.95=77+81+82+(78-80)???
	if(getHcByID(id) == 77||getHcByID(id) == 81||getHcByID(id) == 82||getHcByID(id) == 78||getHcByID(id) == 80){
		compute_95();
	}
<% }%>
//modified by hazhengze 20051220 18:20 end
	
}
		function getCylValue(inValue){
			if(inValue==""||inValue==null){
				return "0";
			}
			return inValue;
		}
<% if (qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) { // 纯益率征收 %>
		//modified by hazhengze 20051220 18:20 start
		
		function compute_95(){
			var value_77=parseFloat(getCylValue(document.forms[0].bqljs_77.value));
			var value_81=parseFloat(getCylValue(document.forms[0].bqljs_81.value));
			var value_82=parseFloat(getCylValue(document.forms[0].bqljs_82.value));
			var value_78=parseFloat(getCylValue(document.forms[0].bqljs_78.value));
			var value_80=parseFloat(getCylValue(document.forms[0].bqljs_80.value));
			var value_95=value_77+value_81+value_82+value_78-value_80;			
			document.forms[0].bqljs_95.value=value_95.toFixed(2);	
		}
		
		function compute_94(){
			var value_92=parseFloat(getCylValue(document.forms[0].bqljs_92.value));
			var value_93=parseFloat(getCylValue(document.forms[0].bqljs_93.value));
			var value_94=value_92-value_93;			
			document.forms[0].bqljs_94.value=value_94.toFixed(2);	
		}
		
		function compute_92(){
			var value_88=parseFloat(getCylValue(document.forms[0].bqljs_88.value));
			var value_89=parseFloat(getCylValue(document.forms[0].bqljs_89.value));
			var value_90=parseFloat(getCylValue(document.forms[0].bqljs_90.value));
			var value_91=parseFloat(getCylValue(document.forms[0].bqljs_91.value));
			var value_92=value_88+value_89+value_90-value_91;			
			document.forms[0].bqljs_92.value=value_92.toFixed(2);	
			compute_94();
		}
		
		function compute_88(){
			var value_80=parseFloat(getCylValue(document.forms[0].bqljs_80.value));
			var value_81=parseFloat(getCylValue(document.forms[0].bqljs_81.value));
			var value_82=parseFloat(getCylValue(document.forms[0].bqljs_82.value));
			var value_83=parseFloat(getCylValue(document.forms[0].bqljs_83.value));
			var value_84=parseFloat(getCylValue(document.forms[0].bqljs_84.value));
			var value_85=parseFloat(getCylValue(document.forms[0].bqljs_85.value));
			var value_86=parseFloat(getCylValue(document.forms[0].bqljs_86.value));
			var value_87=parseFloat(getCylValue(document.forms[0].bqljs_87.value));
			var value_88=value_80-value_81-value_82+value_83+value_84+value_85+value_86+value_87;
			
			document.forms[0].bqljs_88.value=value_88.toFixed(2);	
			compute_92();
		}
		
		function compute_80(){
			var value_78=parseFloat(getCylValue(document.forms[0].bqljs_78.value));
			var value_79=parseFloat(getCylValue(document.forms[0].bqljs_79.value));
			var value_80=value_78*value_79;
			
			document.forms[0].bqljs_80.value=value_80.toFixed(2);	
			xzqyjme();
			compute_88();
			compute_95();
		}
		
		function compute_79(){
			var value_78=parseFloat(getCylValue(document.forms[0].bqljs_78.value));
			var value_79=0;
			if(value_78<=30000){
				value_79="0.18";
			}else if(value_78>30000&&value_78<=100000){
				value_79="0.27";
			}else{
				value_79="0.33";
			}
			document.forms[0].bqljs_79.value=value_79;
			compute_80();	
			<% if (qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) { // 纯益率征收 %>
			///1.纯益率企业只读
			document.forms[0].bqljs_79.readOnly=true;
			document.forms[0].bqljs_79.className='inputalignright'; 
			<% } %>
		}
		
		function compute_76_78(){
			var value_62 = getCylValue(trim(document.forms[0].bqljs_62.value));
			var value_63 = getCylValue(trim(document.forms[0].bqljs_63.value));
			var value_64 = getCylValue(trim(document.forms[0].bqljs_64.value));
			var value_76=0;
			if(parseFloat(value_62)>=0){
				value_76=parseFloat(value_62)-parseFloat(value_63)-parseFloat(value_64);
			}else{
				value_76=0;
			}
			if(parseFloat(value_76)<0){
				value_76=0;
			}		
			document.forms[0].bqljs_76.value=value_76.toFixed(2);		//3.
			document.forms[0].bqljs_78.value=value_76.toFixed(2);
			compute_79();
		}
		
		function compute_76_78_NO(){
			document.forms[0].bqljs_76.value=0;		//3.
			document.forms[0].bqljs_78.value=0;
			compute_79();
		}
		
		function compute_64(){
			var value_65 = getCylValue(trim(document.forms[0].bqljs_65.value));
			var value_66 = getCylValue(trim(document.forms[0].bqljs_66.value));
			var value_67 = getCylValue(trim(document.forms[0].bqljs_67.value));
			var value_68 = getCylValue(trim(document.forms[0].bqljs_68.value));
			var value_69 = getCylValue(trim(document.forms[0].bqljs_69.value));
			var value_70 = getCylValue(trim(document.forms[0].bqljs_70.value));
			var value_71 = getCylValue(trim(document.forms[0].bqljs_71.value));
			var value_72 = getCylValue(trim(document.forms[0].bqljs_72.value));
			var value_73 = getCylValue(trim(document.forms[0].bqljs_73.value));
			var value_74 = getCylValue(trim(document.forms[0].bqljs_74.value));
			var value_75 = getCylValue(trim(document.forms[0].bqljs_75.value));
			var value_64=parseFloat(value_65)+parseFloat(value_66)+parseFloat(value_67)+parseFloat(value_68)+parseFloat(value_69)+parseFloat(value_70)+parseFloat(value_71)+parseFloat(value_72)+parseFloat(value_73)+parseFloat(value_74)+parseFloat(value_75);
			document.forms[0].bqljs_64.value=value_64.toFixed(2);
		}
		
		function compute_62(){
			var value_1 = getCylValue(trim(document.forms[0].bqljs_1.value));
			var value_16 = getCylValue(trim(document.forms[0].bqljs_16.value));
			var value_21 = getCylValue(trim(document.forms[0].bqljs_21.value));
			var value_22 = getCylValue(trim(document.forms[0].bqljs_22.value));
			var value_57 = getCylValue(trim(document.forms[0].bqljs_57.value));
			var hycyl=getCylValue(trim(document.forms[0].cyl.value));
			//alert("hycyl="+hycyl);
			var value_62=parseFloat(value_1)*parseFloat(hycyl)+parseFloat(value_16)+parseFloat(value_21)+parseFloat(value_22)-parseFloat(value_57);		
			document.forms[0].bqljs_62.value=value_62.toFixed(2);
			if(value_62>=0){
				compute_76_78();
			}else{
				compute_76_78_NO();
			}
		}
<%}%>
		//modified by hazhengze 20051220 18:20 end

function compute_cell(myid)
{
   	mathArray = mathString_QYSDSNB;
//Insert  Start  Zhou kejing 20040113
<%
	if (qysdsnbForm.getZsfsdm2().equals(CodeConstant.ZSFSDM_CYLZS)  // 纯益征收
	    &&qysdsnbForm.isGxjsqy()) { // 高新技术企业
%>
		mathArray[2]=new MathString("bqljs_26=bqljs_1*zssl2");		
<%	}	%>
//Insert  End    Zhou kejing 20040113
  	var hc = getHcByID(myid)
  	var obj = eval("document.forms[0].bqljs_" + hc);
        xzqyjme();
   	computeFunction(obj, myid, null);
}

function onclick58(){
		if(!check_jsjdm()) return false;
		hidden_all();
		smsb_lygf_title.style.display = "";
		smsb_lygf_<%=CodeConstant.LYGFQYLXDM_LY %>.style.display = "";
}


function onclick83(){
		if(!check_jsjdm()) return false;
		hidden_all();
		smsb_lygf_title.style.display = "";
		smsb_lygf_<%=CodeConstant.LYGFQYLXDM_LY %>.style.display = "";
}

function onclick84(){
		if(!check_jsjdm()) return false;
		hidden_all();
		smsb_lygf_title.style.display = "";
		smsb_lygf_<%=CodeConstant.LYGFQYLXDM_GF %>.style.display = "";
}

function onclick85(){
		if(!check_jsjdm()) return false;
		hidden_all();
		smsb_lygf_title.style.display = "";
		smsb_lygf_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>.style.display = "";
}

function onclick86(){
		if(!check_jsjdm()) return false;
		hidden_all();
		smsb_lygf_title.style.display = "";
		smsb_lygf_<%=CodeConstant.LYGFQYLXDM_TZ %>.style.display = "";
}

// 设置年报的onchange事件
for(var i=1; i<=document.forms[0].hc.length; i++){	
  	if (eval("document.forms[0].bqljs_"+i).readOnly){		
    	continue;
		}
  	eval("document.forms[0].bqljs_"+i).onchange = compute_nb;
}

<%
if( 1 == 0){ // qysdsnbForm.isGxjsqy()企业是否为高新技术企业
%>
  	document.forms[0].bqljs_24.readOnly=true;
  	document.forms[0].bqljs_24.className='inbright';

  	document.forms[0].bqljs_63.readOnly=true;
  	document.forms[0].bqljs_63.className='inbright';

  	document.forms[0].bqljs_69.readOnly=true;
  	document.forms[0].bqljs_69.className='inbright';

  	document.forms[0].bqljs_70.readOnly=true;
  	document.forms[0].bqljs_70.className='inbright';

  	document.forms[0].bqljs_77.readOnly=true;
  	document.forms[0].bqljs_77.className='inbright';

  	document.forms[0].bqljs_81.readOnly=true;
  	document.forms[0].bqljs_81.className='inbright';

  	document.forms[0].bqljs_82.readOnly=true;
  	document.forms[0].bqljs_82.className='inbright';

  	document.forms[0].bqljs_92.readOnly=true;
  	document.forms[0].bqljs_92.className='inbright';
<%
}else { //非高新技术企业

	//本期是否申报过 true-本期第一次申报 false-本期已经申报过
	boolean has_sb_data = (qysdsnbForm.getDataList().size() == 0);

	//财产损失扣除额
	java.math.BigDecimal sp24 = qysdsnbForm.getSpzg24();
	if(sp24 != null){
%>
  		document.forms[0].bqljs_24.readOnly=false;
  		document.forms[0].bqljs_24.className='inputalignright';
<%
		if(has_sb_data){
%>
  			document.forms[0].bqljs_24.value=<%=sp24 %>;
<%
		}
	}

	//弥补以前年度亏损 63
	java.math.BigDecimal sp63 = qysdsnbForm.getSpzg63();
	if(sp63 != null){
%>
  		document.forms[0].bqljs_63.readOnly=false;
  		document.forms[0].bqljs_63.className='inputalignright';
<%
		if(has_sb_data){
%>
  			document.forms[0].bqljs_63.value=<%=sp63 %>;
<%
		}
	}

	// 免税的技术转让收益 69
	java.math.BigDecimal sp69 = qysdsnbForm.getSpzg69();
	if(sp69 != null){
%>
  		document.forms[0].bqljs_69.readOnly=false;
  		document.forms[0].bqljs_69.className='inputalignright';
<%
		if(has_sb_data){
%>
  			document.forms[0].bqljs_69.value=<%=sp69 %>;
<%
		}
	}

	// 免税的治理“三废”收益 70
	java.math.BigDecimal sp70 = qysdsnbForm.getSpzg70();
	if(sp70 != null){
%>
  		document.forms[0].bqljs_70.readOnly=false;
  		document.forms[0].bqljs_70.className='inputalignright';
<%
		if(has_sb_data){
%>
  			document.forms[0].bqljs_70.value=<%=sp70 %>;
<%
		}
	}

	//研究新产品、新技术、新工艺费用抵扣额 77
	java.math.BigDecimal sp77 = qysdsnbForm.getSpzg77();
	if(sp77 != null){
%>
  		document.forms[0].bqljs_77.readOnly=false;
  		document.forms[0].bqljs_77.className='inputalignright';
<%
		if(has_sb_data){
%>
  			document.forms[0].bqljs_77.value=<%=sp77 %>;
<%
		}
	}
	//减、免所得税额   81
	java.math.BigDecimal sp81 = qysdsnbForm.getSpzg81();
	if(sp81 != null){
%>
  		document.forms[0].bqljs_81.readOnly=false;
  		document.forms[0].bqljs_81.className='inputalignright';
<%
		if(has_sb_data){
                  if(qysdsnbForm.isXzqy()){
                %>      // alert(document.forms[0].bqljs_80.value);
                        // alert(document.forms[0].xzqyjm.value);
  			document.forms[0].bqljs_81.value=round(document.forms[0].bqljs_80.value*document.forms[0].xzqyjm.value);
                <%
                }else{
%>			
				//modified by hazhengze 2005-12-27 17:48 Start
  			//document.forms[0].bqljs_81.value=<%=sp81 %>;
  			//modified by hazhengze 2005-12-27 17:48 end
<%                }
		}

	}
	//技术改造国产设备投资抵免税额 82
	java.math.BigDecimal sp82 = qysdsnbForm.getSpzg82();
	if(sp82 != null){
%>
  		document.forms[0].bqljs_82.readOnly=false;
  		document.forms[0].bqljs_82.className = 'inputalignright';
<%
		if(has_sb_data){
%>
  			document.forms[0].bqljs_82.value=<%=sp82 %>;
<%
		}
	}

}
%>

<% if(	!(qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_DEZS) ||
	  qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS))){
	// 非定额纯益征收
%>

	// 设置83~86行的属性，其数据由企业投资的联营、股份计算得到
	document.forms[0].bqljs_83.onclick= onclick83;
	document.forms[0].bqljs_83.className='txtinput-link';
	document.forms[0].bqljs_83.readOnly = true;

	document.forms[0].bqljs_84.onclick= onclick84;
	document.forms[0].bqljs_84.className='txtinput-link';
	document.forms[0].bqljs_84.readOnly = true;

	document.forms[0].bqljs_85.onclick= onclick85;
	document.forms[0].bqljs_85.className='txtinput-link';
	document.forms[0].bqljs_85.readOnly = true;

	document.forms[0].bqljs_86.onclick= onclick86;
	document.forms[0].bqljs_86.className='txtinput-link';
	document.forms[0].bqljs_86.readOnly = true;
<% } %>


<% if (qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) { // 纯益率征收 %>
   	// 初始化
   	var obj;
   	//modified by hazhengze 2006-1-22 13:51 START
   	for(var i=2; i<76; i++){
   	if((i>=16&&i<=22)||i==57||i==58||(i>=62&&i<=76)||i==78||i==80||(i>=83&&i<=104)){continue;}
		obj = eval("document.forms[0].bqljs_" + i);
		obj.readOnly = true;
		obj.className = 'inbright';
                obj.value='';
   	}
  //modified by hazhengze 2006-1-22 13:51 END

   	document.forms[0].bqljs_1.readOnly=false;
    document.forms[0].bqljs_1.className = 'inputalignright';
    document.forms[0].bqljs_1.onchange=function(){
    	if(isNum(document.forms[0].bqljs_1, 0, 9999999999999, null, 16, 2)){
			
			//modified by hazhengze 200651226 Start
<% if (!qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) { // 纯益率征收 %>
	compute_cell78();
<%}%>
//compute_cell78();
//modified by hazhengze 200651226 End
                        //计算乡镇企业减免额的
                        xzqyjme();
		}
		return true;
    }
<% } %>


<% if (qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_DEZS)) { // 定额征收 %>
//Insert  Start  Zhou kejing 20040113
	var value_95_bak = document.forms[0].bqljs_95.value;
//Insert  End    Zhou kejing 20040113
   	//初始化所有
   	var obj;
//Delete  Start  Zhou kejing 20040113
//   	for(var i=1; i<80; i++){
//		obj = eval("document.forms[0].bqljs_"+i);
//		obj.readOnly = true;
//		obj.className = 'inbright';
//                obj.value='';
//   	}

//   	for(var i=83; i < 88; i++){
//		obj = eval("document.forms[0].bqljs_"+i);
//		obj.readOnly = false;
//		obj.className = 'inputalignright';
//                obj.value='';
//   	}
/*
    document.forms[0].bqljs_80.readOnly=false;
    document.forms[0].bqljs_80.className = 'inputalignright';
    document.forms[0].bqljs_80.onchange=function(){
		if(!isNum(getCellObject(), 0, null, null, 16, 2)) return false;

		compute_cell("bqljs_80");
		return true;
    }

    document.forms[0].bqljs_95.readOnly=false;
    document.forms[0].bqljs_95.className = 'inputalignright';
    document.forms[0].bqljs_95.onchange=function(){
		if(!isNum(getCellObject(), 0, null, null, 16, 2)) return false;

		compute_cell("bqljs_95");

		return true;
   	}
*/   	
//Delete  End    Zhou kejing 20040113

	document.forms[0].bqljs_80.value = round(<%=qysdsnbForm.getDe() %>*document.forms[0].bqljs_105.value);
   	compute_cell("bqljs_80");
   	
//Insert  Start  Zhou kejing 20040113
   	for(var i=1; i<88; i++){
		obj = eval("document.forms[0].bqljs_"+i);
		obj.readOnly = true;
		obj.className = 'inbright';
                obj.value='';
   	}
	document.forms[0].bqljs_88.readOnly=false;
	document.forms[0].bqljs_88.className = 'inputalignright';
	document.forms[0].bqljs_88.onchange=function(){
		if(!isNum(getCellObject(), null, null, null, 16, 2)) return false;
	
		compute_cell("bqljs_88");
	
		return true;
	}
	document.forms[0].bqljs_92.readOnly=false;
	document.forms[0].bqljs_92.className = 'inputalignright';
	document.forms[0].bqljs_92.onchange=function(){
		return isNum(getCellObject(), null, null, null, 16, 2);
	}
	document.forms[0].bqljs_94.readOnly=false;
	document.forms[0].bqljs_94.className = 'inputalignright';
	document.forms[0].bqljs_94.onchange=function(){
		return isNum(getCellObject(), null, null, null, 16, 2);
	}
	document.forms[0].bqljs_95.readOnly=false;
	document.forms[0].bqljs_95.className = 'inputalignright';
	document.forms[0].bqljs_95.onchange=function(){
		return isNum(getCellObject(), null, null, null, 16, 2);
	}
	document.forms[0].bqljs_95.value=value_95_bak;
//Insert  End    Zhou kejing 20040113

<% } %>

<% if(qysdsnbForm.isGxjsqy()){ // 企业是否为高新技术企业 %>
	document.forms[0].bqljs_79.value=<%=qysdsnbForm.getZssl() %>;
<% } %>

//Insert  Start  Zhou kejing 20040113
function action_on_GXandCYL(){
		for(var i=2; i<76; i++){
   	//for(var i=2; i<77; i++){//modified by hazhengze 2005-12-22 13:09
		obj = eval("document.forms[0].bqljs_" + i);
		obj.readOnly = true;
		obj.className = 'inbright';
   	}
	document.forms[0].bqljs_80.readOnly=false;
	document.forms[0].bqljs_80.className = 'inputalignright';
	document.forms[0].bqljs_80.onchange=function(){
		return isNum(getCellObject(), null, null, null, 16, 2);
	}
	document.forms[0].bqljs_88.readOnly=false;
	document.forms[0].bqljs_88.className = 'inputalignright';
	document.forms[0].bqljs_88.onchange=function(){
		return isNum(getCellObject(), null, null, null, 16, 2);
	}
	document.forms[0].bqljs_92.readOnly=false;
	document.forms[0].bqljs_92.className = 'inputalignright';
	document.forms[0].bqljs_92.onchange=function(){
		return isNum(getCellObject(), null, null, null, 16, 2);
	}
	document.forms[0].bqljs_94.readOnly=false;
	document.forms[0].bqljs_94.className = 'inputalignright';
	document.forms[0].bqljs_94.onchange=function(){
		return isNum(getCellObject(), null, null, null, 16, 2);
	}
	document.forms[0].bqljs_95.readOnly=false;
	document.forms[0].bqljs_95.className = 'inputalignright';
	document.forms[0].bqljs_95.onchange=function(){
		return isNum(getCellObject(), null, null, null, 16, 2);
	}
   	
}
function action_on_83to86(){
	// 设置83~86行的属性，其数据由企业投资的联营、股份计算得到
	document.forms[0].bqljs_83.onclick= onclick83;
	document.forms[0].bqljs_83.className='txtinput-link';
	document.forms[0].bqljs_83.readOnly = true;

	document.forms[0].bqljs_84.onclick= onclick84;
	document.forms[0].bqljs_84.className='txtinput-link';
	document.forms[0].bqljs_84.readOnly = true;

	document.forms[0].bqljs_85.onclick= onclick85;
	document.forms[0].bqljs_85.className='txtinput-link';
	document.forms[0].bqljs_85.readOnly = true;

	document.forms[0].bqljs_86.onclick= onclick86;
	document.forms[0].bqljs_86.className='txtinput-link';
	document.forms[0].bqljs_86.readOnly = true;	
}

	//高新企业+纯益率
<%
	if (qysdsnbForm.getZsfsdm2().equals(CodeConstant.ZSFSDM_CYLZS)  // 纯益征收
	    &&qysdsnbForm.isGxjsqy()) { // 高新技术企业
%>
	
		action_on_GXandCYL();
<%	}	%>
	//83-86行次的联接在非定额征收的情况下都有效
	<% if (!qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_DEZS)) { // 非定额征收 %>
		action_on_83to86();
<%	}	%>
//Insert  Start  Zhou kejing 20040113
	document.forms[0].bqljs_92.onchange=function(){
		return isNum(getCellObject(), null, null, null, 16, 2);
	}
	document.forms[0].bqljs_94.onchange=function(){
		return isNum(getCellObject(), null, null, null, 16, 2);
	}
//Insert  End    Zhou kejing 20040113
//Insert  End    Zhou kejing 20040113

</script>

<script language="javascript">
var mathString_SYDWSHTTSRSB = new Array();
mathString_SYDWSHTTSRSB[0] = new MathString("ynssde_<%=CodeConstant.LYGFQYLXDM_LY %>=lr_<%=CodeConstant.LYGFQYLXDM_LY %>/(1-sl_<%=CodeConstant.LYGFQYLXDM_LY %>)");
mathString_SYDWSHTTSRSB[1] = new MathString("ynsdse_<%=CodeConstant.LYGFQYLXDM_LY %>=qysl_<%=CodeConstant.LYGFQYLXDM_LY %>*ynssde_<%=CodeConstant.LYGFQYLXDM_LY %>");
mathString_SYDWSHTTSRSB[2] = new MathString("sskce_<%=CodeConstant.LYGFQYLXDM_LY %>=sl_<%=CodeConstant.LYGFQYLXDM_LY %>*ynssde_<%=CodeConstant.LYGFQYLXDM_LY %>");
mathString_SYDWSHTTSRSB[3] = new MathString("ybsdse_<%=CodeConstant.LYGFQYLXDM_LY %>=ynsdse_<%=CodeConstant.LYGFQYLXDM_LY %>-sskce_<%=CodeConstant.LYGFQYLXDM_LY %>");

mathString_SYDWSHTTSRSB[4] = new MathString("ynssde_<%=CodeConstant.LYGFQYLXDM_GF %>=lr_<%=CodeConstant.LYGFQYLXDM_GF %>/(1-sl_<%=CodeConstant.LYGFQYLXDM_GF %>)");
mathString_SYDWSHTTSRSB[5] = new MathString("ynsdse_<%=CodeConstant.LYGFQYLXDM_GF %>=qysl_<%=CodeConstant.LYGFQYLXDM_GF %>*ynssde_<%=CodeConstant.LYGFQYLXDM_GF %>");
mathString_SYDWSHTTSRSB[6] = new MathString("sskce_<%=CodeConstant.LYGFQYLXDM_GF %>=sl_<%=CodeConstant.LYGFQYLXDM_GF %>*ynssde_<%=CodeConstant.LYGFQYLXDM_GF %>");
mathString_SYDWSHTTSRSB[7] = new MathString("ybsdse_<%=CodeConstant.LYGFQYLXDM_GF %>=ynsdse_<%=CodeConstant.LYGFQYLXDM_GF %>-sskce_<%=CodeConstant.LYGFQYLXDM_GF %>");

mathString_SYDWSHTTSRSB[8] = new MathString("ynssde_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>=lr_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>/(1-sl_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>)");
mathString_SYDWSHTTSRSB[9] = new MathString("ynsdse_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>=qysl_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>*ynssde_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>");
mathString_SYDWSHTTSRSB[10] = new MathString("sskce_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>=sl_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>*ynssde_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>");
mathString_SYDWSHTTSRSB[11] = new MathString("ybsdse_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>=ynsdse_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>-sskce_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>");

mathString_SYDWSHTTSRSB[12] = new MathString("ynssde_<%=CodeConstant.LYGFQYLXDM_TZ %>=lr_<%=CodeConstant.LYGFQYLXDM_TZ %>/(1-sl_<%=CodeConstant.LYGFQYLXDM_TZ %>)");
mathString_SYDWSHTTSRSB[13] = new MathString("ynsdse_<%=CodeConstant.LYGFQYLXDM_TZ %>=qysl_<%=CodeConstant.LYGFQYLXDM_TZ %>*ynssde_<%=CodeConstant.LYGFQYLXDM_TZ %>");
mathString_SYDWSHTTSRSB[14] = new MathString("sskce_<%=CodeConstant.LYGFQYLXDM_TZ %>=sl_<%=CodeConstant.LYGFQYLXDM_TZ %>*ynssde_<%=CodeConstant.LYGFQYLXDM_TZ %>");
mathString_SYDWSHTTSRSB[15] = new MathString("ybsdse_<%=CodeConstant.LYGFQYLXDM_TZ %>=ynsdse_<%=CodeConstant.LYGFQYLXDM_TZ %>-sskce_<%=CodeConstant.LYGFQYLXDM_TZ %>");

function hidden_all(){
	smsb_qysdsnb.style.display='none';
	smsb_lygf_title.style.display='none';
	smsb_lygf_<%=CodeConstant.LYGFQYLXDM_LY %>.style.display='none';
	smsb_lygf_<%=CodeConstant.LYGFQYLXDM_GF %>.style.display='none';
	smsb_lygf_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>.style.display='none';
	smsb_lygf_<%=CodeConstant.LYGFQYLXDM_TZ %>.style.display='none';
}

function FN_deleteRows(qylx)
{
    deleteRow(qylx);
	compute_lygf_sum(qylx);

    return false;
}

function publicMethod(){
	compute_lygf_sum("<%=CodeConstant.LYGFQYLXDM_LY %>");
	compute_lygf_sum('<%=CodeConstant.LYGFQYLXDM_GF %>');
	compute_lygf_sum('<%=CodeConstant.LYGFQYLXDM_ZWHZ %>');
	compute_lygf_sum('<%=CodeConstant.LYGFQYLXDM_TZ %>');
}

// 返回联营、股份企业的申报合计数据到企业所得税相应的行次
function FN_return(qylx,nbhc){

  	var qysdsnb_cell_id = "bqljs_" + nbhc;
  	var qysdsnb_cell = eval("document.forms[0]." + qysdsnb_cell_id);
  	var length = getLength(eval("document.forms[0].fl_" + qylx));
  	if(length != null){
	  	var mcobj = eval("document.forms[0].dwmc_" + qylx);
	   	if(length == -1){
	   		if(trim(mcobj.value) == ""){
	   	    	alert("企业名称不能为空！");

	   	    	mcobj.focus();
	   	    	return false;
	   		}
	   	}else {
	        // 有多行
	        for (var i=mcobj.length-1; i>=0; i--) {
		   		if(trim(mcobj[i].value) == ""){
		   	    	alert("企业名称不能为空！");
		   	    	mcobj[i].focus();
		   	    	return false;
		   		}
	   		}
		}
  	}

	qysdsnb_cell.value = eval("document.forms[0].ybsdse_" + qylx + "_sum").value;

//Insert  Start  Zhou kejing 20040113
	//78行次附加处理
  	var value_78 = trim(document.forms[0].bqljs_78.value);
	if(parseFloat(value_78) > 0.00000)
	  	compute_cell(qysdsnb_cell_id);
//Insert  End    Zhou kejing 20040113

  	hidden_all();
  	smsb_qysdsnb.style.display='';
  	qysdsnb_cell.focus();

}

// 检查联营、股份企业名称
function check_lygf_name(obj, qylx){
	if(trim(obj.value) == ""){
		alert("联营、股份企业名称不能为空！");
		window.event.returnValue=false;
		obj.focus();
		obj.select();
		return false;
	}
	return true;
}

// 计算联营、股份企业的申报数据
function compute_lygf_item(obj, qylx){	
	mathArray = mathString_SYDWSHTTSRSB;

	computeFunction(obj, "lr_" + qylx , 1);

	computeFunction(obj, "sl_" + qylx , 1);

	computeFunction(obj, "qysl_" + qylx , 1);

	computeFunction(obj, "sskce_" + qylx , 1);



	//如果应补所得税额为负数，归0
	var oRow = getObjRow(obj);
	var oYbsdse = eval("oRow.all('ybsdse_"+qylx+"')");
	var val = oYbsdse.value;
	if(val<0){
		oYbsdse.value = 0.00;
	}

	compute_lygf_sum(qylx);
}

// 计算联营、股份企业的申报数据合计
function compute_lygf_sum(qylx ){

    computeSameSum("lr_" + qylx, "lr_" + qylx + "_sum");
    computeSameSum("ynssde_" + qylx, "ynssde_" + qylx + "_sum");
    computeSameSum("ynsdse_" + qylx, "ynsdse_" + qylx + "_sum");
    computeSameSum("sskce_" + qylx, "sskce_" + qylx + "_sum");
    computeSameSum("ybsdse_" + qylx, "ybsdse_" + qylx + "_sum");

}

</script>

<div id="smsb_lygf_title" style="display:none">
	<table cellspacing="0" class="table-99">
 	<tr class="black9">
    	<td colspan="2">
    		<div align="left"> 税款所属日期：
    			<bean:write name="qysdsnbForm" property="skssksrq" scope="request" filter="true" />
    		至  <bean:write name="qysdsnbForm" property="skssjsrq" scope="request" filter="true" />
    		</div>
    	</td>
    	<td colspan="2">
    		<div align="left"> 申报日期：
    			<bean:write name="qysdsnbForm" property="sbrq" scope="request" filter="true" />
    		</div>
    	</td>
    	<td colspan="2"><div align="right"> 金额单位：人民币元</div></td>
 	</tr>
 	<tr>
    	<td width="15%" nowrap class="2-td2-t-left">
    		<div align="right"> 计算机代码&nbsp;&nbsp;</div>
    	</td>
    	<td width="15%" nowrap class="2-td2-t-left">
    		<div align="left">&nbsp;&nbsp;
    		<bean:write name="qysdsnbForm" property="jsjdm" scope="request" filter="true" />
    		</div>
    	</td>
    	<td width="15%" nowrap class="2-td2-t-left">
    		<div align="right">申报单位名称（公章）&nbsp;&nbsp;</div></td>
    	<td width="30%" nowrap class="2-td2-t-left">
    		<div align="left">&nbsp;&nbsp;
    		<bean:write name="qysdsnbForm" property="nsrmc" scope="request" filter="true" />
    		</div>
    	</td>
    	<td width="10%" nowrap class="2-td2-t-left"><div align="right"> 联系电话&nbsp;&nbsp;</div></td>
    	<td width="15%" nowrap class="2-td2-t-center">
    		<div align="left">&nbsp;&nbsp;
    		<bean:write name="qysdsnbForm" property="zcdzlxdh" scope="request" filter="true" />
    		</div>
    	</td>
	</tr>
	</table><br>
</div><!-- End of smsb_lygf_title -->

<div id="smsb_lygf_<%=CodeConstant.LYGFQYLXDM_LY %>" style="display:none">
	<table cellspacing="0" class="table-99">
	<tr>
		<td class="1-td1">联营企业收入纳税计算表</td>
	</tr>
	<tr>
		<td valign="top" class="1-td2"><br>

		<table width="94%" border="0" cellpadding="0" cellspacing="0" height="114" id="LYGFNB" onkeydown="if(addRowFlg) dokeydown(this.id,'szsmdm');"  onmouseup='return dokeyUp(this.id)'>
		<ttsoft:smsbtable form="qysdsnbForm" property="lyqyDataList"  tableId="LYGFNB" hasTitle="true"/><DIV id=divSfTemp></DIV>
		<tr>
 			<td class=2-td2-left> 合 计 </td>
 			<td  class=2-td2-left>&nbsp;</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='lr_<%=CodeConstant.LYGFQYLXDM_LY %>_sum' value='0.00' size='10' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='ynssde_<%=CodeConstant.LYGFQYLXDM_LY %>_sum' value='0.00' size='16' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='ynsdse_<%=CodeConstant.LYGFQYLXDM_LY %>_sum' value='0.00' size='16' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='sskce_<%=CodeConstant.LYGFQYLXDM_LY %>_sum' value='0.00' size='16' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-center>
 				<input type='text' name='ybsdse_<%=CodeConstant.LYGFQYLXDM_LY %>_sum' value='0.00'  size='16'  tabIndex=-1 readonly class=inputnoborder>
 			</td>
		</tr>
		</table><br>
		<div align="center">
			<input type="image" accesskey="a" tabIndex="-1" style="cursor:hand"
				onclick="addRow('<%=CodeConstant.LYGFQYLXDM_LY %>');return false;"
				onMouseOver="MM_swapImage('xinzeng1','','<%=static_contextpath%>images/zj-a2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/zj-a1.jpg" alt="新增一行" name="Image13" width="79" height="22" border="0" id='xinzeng1'>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="image" accesskey="d" tabIndex="-1" style="cursor:hand"
				onclick="FN_deleteRows('<%=CodeConstant.LYGFQYLXDM_LY %>');return false;"
				onMouseOver="MM_swapImage('shanchu1','','<%=static_contextpath%>images/sc-d2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/sc-d1.jpg" alt="删除" name="Image13" width="79" height="22" border="0" id='shanchu1'>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="image" accesskey="f" tabIndex="-1" style="cursor:hand"
				onclick="FN_return('<%=CodeConstant.LYGFQYLXDM_LY %>','83');return false;"
				onMouseOver="MM_swapImage('fanhui1','','<%=static_contextpath%>images/fh-f2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/fh-f1.jpg" alt="返回" name="Image13" width="79" height="22" border="0" id='fanhui1'>

		</div><br>
		<script language="JavaScript">
		<!--
		var LYGFNB_list=new DTable(LYGFNB,jsArr_LYGFNB);
		LYGFNB_list.tableTail=1;
		//-->
		</script>
		</td>
	</tr>
	</table>
</div><!-- End of smsb_lygf_1 -->

<div id="smsb_lygf_<%=CodeConstant.LYGFQYLXDM_GF %>" style="display:none">
	<table cellspacing="0" class="table-99">
	<tr>
		<td class="1-td1">股份制企业收入纳税计算表</td>
	</tr>
	<tr>
		<td valign="top" class="1-td2"><br>
	  	<table width="94%" border="0" cellpadding="0" cellspacing="0" height="114" id="GFZQYNB" onkeydown="if(addRowFlg) dokeydown(this.id,'szsmdm');"  onmouseup='return dokeyUp(this.id)'>
		<ttsoft:smsbtable form="qysdsnbForm" property="gfqyDataList"  tableId="GFZQYNB" hasTitle="true"/><DIV id=divSfTemp></DIV>
     	<tr>
 			<td class=2-td2-left> 合 计 </td>
 			<td  class=2-td2-left>&nbsp;</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='lr_<%=CodeConstant.LYGFQYLXDM_GF %>_sum' value='0.00' size='10' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='ynssde_<%=CodeConstant.LYGFQYLXDM_GF %>_sum' value='0.00' size='16' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='ynsdse_<%=CodeConstant.LYGFQYLXDM_GF %>_sum' value='0.00' size='16' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='sskce_<%=CodeConstant.LYGFQYLXDM_GF %>_sum' value='0.00' size='16' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-center>
 				<input type='text' name='ybsdse_<%=CodeConstant.LYGFQYLXDM_GF %>_sum' value='0.00'  size='16'  tabIndex=-1 readonly class=inputnoborder>
 			</td>
		</tr>
		</table><br>
		<div align="center">
			<input type="image" accesskey="a" tabIndex="-1" style="cursor:hand"
				onclick="addRow('<%=CodeConstant.LYGFQYLXDM_GF %>');return false;"
				onMouseOver="MM_swapImage('xinzeng2','','<%=static_contextpath%>images/zj-a2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/zj-a1.jpg" alt="新增一行" name="Image13" width="79" height="22" border="0" id='xinzeng2'>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="image" accesskey="d" tabIndex="-1" style="cursor:hand"
				onclick="FN_deleteRows('<%=CodeConstant.LYGFQYLXDM_GF %>');return false;"
				onMouseOver="MM_swapImage('shanchu2','','<%=static_contextpath%>images/sc-d2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/sc-d1.jpg" alt="删除" name="Image13" width="79" height="22" border="0" id='shanchu2'>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="image" accesskey="f" tabIndex="-1"  style="cursor:hand"
				onclick="FN_return('<%=CodeConstant.LYGFQYLXDM_GF %>','84');return false;"
				onMouseOver="MM_swapImage('fanhui2','','<%=static_contextpath%>images/fh-f2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/fh-f1.jpg" alt="返回" name="Image13" width="79" height="22" border="0" id='fanhui2'>

		</div><br>
		<script language="JavaScript">
		<!--
		var GFZQYNB_list=new DTable(GFZQYNB,jsArr_GFZQYNB);
		GFZQYNB_list.tableTail=1;
		//-->
		</script>
		</td>
	</tr>
	</table>
</div><!-- End of smsb_lygf_2 -->

<div id="smsb_lygf_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>" style="display:none">
	<table cellspacing="0" class="table-99">
	<tr>
		<td class="1-td1">中外合资企业收入纳税计算表</td>
	</tr>
	<tr>
		<td valign="top" class="1-td2"><br>
		<table width="94%" border="0" cellpadding="0" cellspacing="0" height="114" id="ZWHZQYNB" onkeydown="if(addRowFlg) dokeydown(this.id,'szsmdm');"  onmouseup='return dokeyUp(this.id)'>
		<ttsoft:smsbtable form="qysdsnbForm" property="hzqyDataList"  tableId="ZWHZQYNB" hasTitle="true"/><DIV id=divSfTemp></DIV>
     	<tr>
 			<td class=2-td2-left> 合 计 </td>
 			<td  class=2-td2-left>&nbsp;</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='lr_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>_sum' value='0.00' size='10' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='ynssde_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>_sum' value='0.00' size='16' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='ynsdse_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>_sum' value='0.00' size='16' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='sskce_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>_sum' value='0.00' size='16' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-center>
 				<input type='text' name='ybsdse_<%=CodeConstant.LYGFQYLXDM_ZWHZ %>_sum' value='0.00'  size='16' tabIndex=-1  readonly class=inputnoborder>
 			</td>
		</tr>
		</table><br>
		<div align="center">
			<input type="image" accesskey="a" tabIndex="-1" style="cursor:hand"
				onclick="addRow('<%=CodeConstant.LYGFQYLXDM_ZWHZ %>');return false;"
				onMouseOver="MM_swapImage('xinzeng3','','<%=static_contextpath%>images/zj-a2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/zj-a1.jpg" alt="新增一行" name="Image13" width="79" height="22" border="0" id='xinzeng3'>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="image" accesskey="d" tabIndex="-1" style="cursor:hand"
				onclick="FN_deleteRows('<%=CodeConstant.LYGFQYLXDM_ZWHZ %>');return false;"
				onMouseOver="MM_swapImage('shanchu3','','<%=static_contextpath%>images/sc-d2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/sc-d1.jpg" alt="删除" name="Image13" width="79" height="22" border="0" id='shanchu3'>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="image" accesskey="f" tabIndex="-1"  style="cursor:hand"
				onclick="FN_return('<%=CodeConstant.LYGFQYLXDM_ZWHZ %>','85');return false;"
				onMouseOver="MM_swapImage('fanhui3','','<%=static_contextpath%>images/fh-f2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/fh-f1.jpg" alt="返回" name="Image13" width="79" height="22" border="0" id='fanhui3'>

		</div><br>
		<script language="JavaScript">
		<!--
		var ZWHZQYNB_list=new DTable(ZWHZQYNB,jsArr_ZWHZQYNB);
		ZWHZQYNB_list.tableTail=1;
		//-->
		</script>
		</td>
	</tr>
	</table>
</div><!-- End of smsb_lygf_3 -->

<div id="smsb_lygf_<%=CodeConstant.LYGFQYLXDM_TZ %>" style="display:none">
	<table cellspacing="0" class="table-99">
	<tr>
		<td class="1-td1">投资企业企业收入纳税计算表</td>
	</tr>
	<tr>
		<td valign="top" class="1-td2"><br>
		<table width="94%" border="0" cellpadding="0" cellspacing="0" height="114" id="TZQYNB" onkeydown="if(addRowFlg) dokeydown(this.id,'szsmdm');"  onmouseup='return dokeyUp(this.id)'>
		<ttsoft:smsbtable form="qysdsnbForm" property="tzqyDataList"  tableId="TZQYNB" hasTitle="true"/><DIV id=divSfTemp></DIV>
     	<tr>
 			<td class=2-td2-left> 合 计 </td>
 			<td  class=2-td2-left>&nbsp;</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='lr_<%=CodeConstant.LYGFQYLXDM_TZ %>_sum' value='0.00' size='10' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='ynssde_<%=CodeConstant.LYGFQYLXDM_TZ %>_sum' value='0.00' size='16' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='ynsdse_<%=CodeConstant.LYGFQYLXDM_TZ %>_sum' value='0.00' size='16' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-left>
 				<input type='text'  name='sskce_<%=CodeConstant.LYGFQYLXDM_TZ %>_sum' value='0.00' size='16' tabIndex=-1 readonly class=inputnoborder>
 			</td>
 			<td  class=2-td2-center>
 				<input type='text' name='ybsdse_<%=CodeConstant.LYGFQYLXDM_TZ %>_sum' value='0.00'  size='16' tabIndex=-1  readonly class=inputnoborder>
 			</td>
		</tr>
		</table><br>
		<div align="center">
			<input type="image" accesskey="a" tabIndex="-1" style="cursor:hand"
				onclick="addRow('<%=CodeConstant.LYGFQYLXDM_TZ %>');return false;"
				onMouseOver="MM_swapImage('xinzeng4','','<%=static_contextpath%>images/zj-a2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/zj-a1.jpg"  name="1" width="79" height="22" border="0" id='xinzeng4'>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="image" accesskey="d" tabIndex="-1" style="cursor:hand"
				onclick="FN_deleteRows('<%=CodeConstant.LYGFQYLXDM_TZ %>');return false;"
				onMouseOver="MM_swapImage('shanchu4','','<%=static_contextpath%>images/sc-d2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/sc-d1.jpg" alt="删除" name="2" width="79" height="22" border="0" id='shanchu4'>

                       &nbsp;&nbsp;&nbsp;&nbsp;
			<input type="image" accesskey="f" tabIndex="-1" style="cursor:hand"
				onclick="FN_return('<%=CodeConstant.LYGFQYLXDM_TZ %>','86');return false;"
				onMouseOver="MM_swapImage('fanhui4','','<%=static_contextpath%>images/fh-f2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/fh-f1.jpg" alt="返回" name="3" width="79" height="22" border="0" id='fanhui4'>

		</div><br>
		<script language="JavaScript">
		<!--
		var TZQYNB_list=new DTable(TZQYNB,jsArr_TZQYNB);
		TZQYNB_list.tableTail=1;
		//-->
		</script>
		</td>
	</tr>
	</table>
</div><!-- End of smsb_lygf_4 -->

<script language="javascript">

	// 计算联营、股份企业已有历史数据和
	compute_lygf_sum("<%=CodeConstant.LYGFQYLXDM_LY %>");
	compute_lygf_sum('<%=CodeConstant.LYGFQYLXDM_GF %>');
	compute_lygf_sum('<%=CodeConstant.LYGFQYLXDM_ZWHZ %>');
	compute_lygf_sum('<%=CodeConstant.LYGFQYLXDM_TZ %>');
	

	document.forms[0].bqljs_79.onchange=function(){
		//alert("555<><>"+document.forms[0].bqljs_79.value);
		
		//modified by hazhengze 200651226 Start
<% if (!qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) { // 纯益率征收 %>
	compute_cell784();
<%}%>
//compute_cell784();
//modified by hazhengze 200651226 End
		return isNum(getCellObject(), null, null, null, 16, 2);
	}
</script>

</td>
</html:form>

</tr>
</table><br>

<%@ include file="include/footer.jsp" %>
<SCRIPT LANGUAGE="JavaScript">
<!--
/****如果该纳税人为非正常户，则显示提示信息****/
/****20050817 HU xiaofeng****/
var nsrzt = <ttsoft:write name="qysdsnbForm" property="nsrzt" filter="false"/>;
checkNsrzt();
/*************end*****************/
//Modified by hazhengze 2005-12-22 13:18 START
<% if (qysdsnbForm.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) { // 纯益率征收 %>
	///1.纯益率企业只读
	document.forms[0].bqljs_79.readOnly=true;
	document.forms[0].bqljs_79.className='inputalignright'; 
	//2.设置6和15不显示
	document.forms[0].bqljs_6.style.display="none";
	document.forms[0].bqljs_6.className='inputalignright'; 
	document.forms[0].bqljs_15.style.display="none";
	document.forms[0].bqljs_15.className='inputalignright'; 
	document.forms[0].bqljs_26.style.display="none";
	document.forms[0].bqljs_26.className='inputalignright'; 
	document.forms[0].bqljs_77.style.display="none";
	document.forms[0].bqljs_77.className='inputalignright'; 
	//document.forms[0].bqljs_81.style.display="none";
	//document.forms[0].bqljs_81.className='inputalignright'; 
	document.forms[0].bqljs_82.style.display="none";
	document.forms[0].bqljs_82.className='inputalignright'; 
	//3.
	//eval(document.forms[0].bqljs_58).onblur= onclick58;
	eval(document.forms[0].bqljs_58).onchange= onclick58;
	//4.
	eval(document.forms[0].bqljs_1).onchange = compute_nb;
	eval(document.forms[0].bqljs_16).onchange = compute_nb;
	eval(document.forms[0].bqljs_21).onchange = compute_nb;
	eval(document.forms[0].bqljs_22).onchange = compute_nb;
	eval(document.forms[0].bqljs_57).onchange = compute_nb;
	eval(document.forms[0].bqljs_58).onchange = compute_nb;
	eval(document.forms[0].bqljs_59).onchange = compute_nb;
	eval(document.forms[0].bqljs_60).onchange = compute_nb;
	eval(document.forms[0].bqljs_61).onchange = compute_nb;
	eval(document.forms[0].bqljs_62).onchange = compute_nb;
	eval(document.forms[0].bqljs_63).onchange = compute_nb;
	eval(document.forms[0].bqljs_64).onchange = compute_nb;
	eval(document.forms[0].bqljs_65).onchange = compute_nb;
	eval(document.forms[0].bqljs_66).onchange = compute_nb;
	eval(document.forms[0].bqljs_67).onchange = compute_nb;
	eval(document.forms[0].bqljs_68).onchange = compute_nb;
	eval(document.forms[0].bqljs_69).onchange = compute_nb;
	eval(document.forms[0].bqljs_70).onchange = compute_nb;
	eval(document.forms[0].bqljs_71).onchange = compute_nb;
	eval(document.forms[0].bqljs_72).onchange = compute_nb;
	eval(document.forms[0].bqljs_73).onchange = compute_nb;
	eval(document.forms[0].bqljs_74).onchange = compute_nb;
	eval(document.forms[0].bqljs_75).onchange = compute_nb;
	eval(document.forms[0].bqljs_76).onchange = compute_nb;
	eval(document.forms[0].bqljs_78).onchange = compute_nb;
	eval(document.forms[0].bqljs_80).onchange = compute_nb;
	eval(document.forms[0].bqljs_81).onchange = compute_nb;
	eval(document.forms[0].bqljs_82).onchange = compute_nb;
	eval(document.forms[0].bqljs_83).onchange = compute_nb;
	eval(document.forms[0].bqljs_84).onchange = compute_nb;
	eval(document.forms[0].bqljs_85).onchange = compute_nb;
	eval(document.forms[0].bqljs_86).onchange = compute_nb;
	eval(document.forms[0].bqljs_87).onchange = compute_nb;
	//eval(document.forms[0].bqljs_87).onblur = compute_nb;
	eval(document.forms[0].bqljs_88).onchange = compute_nb;
	eval(document.forms[0].bqljs_89).onchange = compute_nb;
	eval(document.forms[0].bqljs_90).onchange = compute_nb;
	eval(document.forms[0].bqljs_91).onchange = compute_nb;
	eval(document.forms[0].bqljs_92).onchange = compute_nb;
	eval(document.forms[0].bqljs_93).onchange = compute_nb;
	eval(document.forms[0].bqljs_94).onchange = compute_nb;
	//5.	
	eval(document.forms[0].bqljs_97).onchange = doNothing;
	eval(document.forms[0].bqljs_98).onchange = doNothing;
	eval(document.forms[0].bqljs_99).onchange = doNothing;
	eval(document.forms[0].bqljs_100).onchange = doNothing;
	eval(document.forms[0].bqljs_101).onchange = doNothing;
	eval(document.forms[0].bqljs_102).onchange = doNothing;
	eval(document.forms[0].bqljs_103).onchange = doNothing;
	eval(document.forms[0].bqljs_104).onchange = doNothing;
<%}%>		
	eval(document.forms[0].bqljs_96).onchange = onChange96;
//Modified by hazhengze 2005-12-22 13:18 END
	//var debug=;
</SCRIPT>

</body>
</html:html>


