<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html>
<head>
<title>录入个人所得税基础信息表信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/swapImages.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/smsb_common.js"></script>
<script language=JavaScript>
var endSave = false;
function setLoadInf()
{
	//多选框 
	var grxx_nsrlx = document.getElementById("grxx_nsrlx");				//纳税人类型
	var grxx_nsrlx_cb = document.getElementsByName("grxx_nsrlx_cb");
	splitCheckBox(grxx_nsrlx,grxx_nsrlx_cb);
	
	var grxx_sfyjjnqk = document.getElementById("grxx_sfyjjnqk");				//三费一金缴纳情况
	var grxx_sfyjjnqk_cb = document.getElementsByName("grxx_sfyjjnqk_cb");
	splitCheckBox(grxx_sfyjjnqk,grxx_sfyjjnqk_cb);
	
	var grxx_sfcjrlsgl = document.getElementById("grxx_sfcjrlsgl");				//是否残疾人烈属孤老
	var grxx_sfcjrlsgl_cb = document.getElementsByName("grxx_sfcjrlsgl_cb");
	splitCheckBox(grxx_sfcjrlsgl,grxx_sfcjrlsgl_cb);
	
	var grxx_tzzlx = document.getElementById("grxx_tzzlx");					//投资者类型
	var grxx_tzzlx_cb = document.getElementsByName("grxx_tzzlx_cb");
	splitCheckBox(grxx_tzzlx,grxx_tzzlx_cb);
	
	var msg="<bean:write name='grsdsjbxxbForm2014cx' property='msg'/>";
	if(msg){
		alert(msg);
		if(msg=="保存成功"){
			endSave=true;
		}
	}
}
function doReturn()
{
	document.forms[0].action = "quit.do";
	document.forms[0].submit();
}

function joinCheckBox(obj1,obj2){
	var length = obj2.length;
	var result="";
	for(var i=0;i<length;i++){
		if(obj2[i].checked){
			result+=obj2[i].value;
			result+="|";
		}
	}
	obj1.value=result;
}

function splitCheckBox(obj1,obj2){
	var values = obj1.value.split("|");
	for(var i=0;i<values.length;i++){
		for(var j=0;j<obj2.length;j++){
			if(values[i]==obj2[j].value){
				obj2[j].checked=true;
			}
		}
	}
}

//保存还返回此页面
function doSave()
{
	if(!check()) {
		return false;
	}
	beforeSave();
	var actionType = document.getElementById("actionType");
	actionType.value="Save";
	document.forms[0].submit();
}
function doNext(){
	
	//年度
	var nd = document.all.nd.value;
	if(!checkNd(nd)){
		alert("年度格式不正确")
		return false;
	}
	
		var actionType = document.getElementById("actionType");
		actionType.value="Next";
		document.forms[0].submit();
}

function beforeSave(){
	var grxx_nsrlx = document.getElementById("grxx_nsrlx");				//纳税人类型
	var grxx_nsrlx_cb = document.getElementsByName("grxx_nsrlx_cb");
	joinCheckBox(grxx_nsrlx,grxx_nsrlx_cb);
	
	var grxx_sfyjjnqk = document.getElementById("grxx_sfyjjnqk");				//三费一金缴纳情况
	var grxx_sfyjjnqk_cb = document.getElementsByName("grxx_sfyjjnqk_cb");
	joinCheckBox(grxx_sfyjjnqk,grxx_sfyjjnqk_cb);
	
	var grxx_sfcjrlsgl = document.getElementById("grxx_sfcjrlsgl");				//是否残疾人烈属孤老
	var grxx_sfcjrlsgl_cb = document.getElementsByName("grxx_sfcjrlsgl_cb");
	joinCheckBox(grxx_sfcjrlsgl,grxx_sfcjrlsgl_cb);
	
	var grxx_tzzlx = document.getElementById("grxx_tzzlx");					//投资者类型
	var grxx_tzzlx_cb = document.getElementsByName("grxx_tzzlx_cb");
	joinCheckBox(grxx_tzzlx,grxx_tzzlx_cb);
}

function doBack(){
	var actionType = document.getElementById("actionType");
	actionType.value="Back";
	document.forms[0].submit();
}

function doChangeNd(){
	
	//年度
	var nd = document.all.nd.value;
	if(!checkNd(nd)){
		alert("年度格式不正确")
		return false;
	}
	
	var actionType = document.getElementById("actionType");
	actionType.value="ChangeNd";
	document.forms[0].submit();
}

/**
 * 数据校验
 */
function check() {
	// 姓名
	var grxx_xm = document.all.grxx_xm.value;
	if(!grxx_xm) {
		alert("姓名不能为空！")
		return false;
	}
	
	// 来华时间
	var grxx_wzsnsr_lhsj = document.all.grxx_wzsnsr_lhsj.value;
	if(grxx_wzsnsr_lhsj &&!checkDate(grxx_wzsnsr_lhsj)) {
		alert("来华时间格式不正确！");
		return false;
	}
	
	// 预计离境时间
	var grxx_wzsnsr_yjljsj = document.all.grxx_wzsnsr_yjljsj.value;
	if(grxx_wzsnsr_yjljsj && !checkDate(grxx_wzsnsr_yjljsj)) {
		alert("预计离境时间格式不正确！");
		return false;
	}
	
	//电子邮箱
	var grxx_email = document.all.grxx_email.value;
	if(grxx_email && !checkEmail(grxx_email)) {
		alert("电子邮箱格式不正确！");
		return false;
	}
	
	//邮政编码
	var grxx_yzbm = document.all.grxx_yzbm.value;
	var grxx_yjwsd_yzbm = document.all.grxx_yjwsd_yzbm.value;
	var grxx_yjwsd_yzbm = document.all.grxx_yjwsd_yzbm.value;
	var grxx_wzsnsr_jnrzsgdw_yzbm = document.all.grxx_wzsnsr_jnrzsgdw_yzbm.value;
	var grxx_wzsnsr_jnspqydw_yzbm = document.all.grxx_wzsnsr_jnspqydw_yzbm.value;
	if((grxx_yzbm && !checkZip(grxx_yzbm))||(grxx_yjwsd_yzbm && !checkZip(grxx_yjwsd_yzbm))||(grxx_wzsnsr_jnrzsgdw_yzbm && !checkZip(grxx_wzsnsr_jnrzsgdw_yzbm))||(grxx_wzsnsr_jnspqydw_yzbm && !checkZip(grxx_wzsnsr_jnspqydw_yzbm))) 
	{
		alert("邮政编码格式不正确！");
		return false;
	}
	return true;
}

function checkNd(nd) {
	var reg = /^(1|2)\d{3}$/;
	if(reg.test(nd) == false) {
		return false;
	}
	return true;
}

/**
 * 验证Email
 */
function checkEmail(strEmail) {
	if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1) {
		return true;
	} else {
		return false;
	}
}

/**
 * 验证邮政编码
 */
function checkZip(zip) {
	var reg = /^[1-9]\d{5}(?!\d)$/;
	if(reg.test(zip) == false) {
		return false;
	}
	return true;
}


/**
 * 检查时间格式
 */
function checkDate(dateVal) {
	if(!dateVal) {
		return false;
	}
	if (/^\d{4}1(0|1|2)(0|1|2)\d{1}$/.test(dateVal) 
			|| /^\d{4}0(1|2|3|4|5|6|7|8|9)(0|1|2)\d{1}$/.test(dateVal)
			|| /^\d{4}1(0|1|2)3(0|1)$/.test(dateVal)
			|| /^\d{4}0(1|2|3|4|5|6|7|8|9)3(0|1)$/.test(dateVal)) {
		return true;
	}
	return false;
}

function clickTbsm(){
	var txsm = document.getElementById("tbsm");
	var display = txsm.style.display;
	if(display=="none"){
		txsm.style.display="";
	}else{
		txsm.style.display="none";
	}
}


</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="setLoadInf()">
		<jsp:include page="../../../include/header.jsp" flush="true" >
    		<jsp:param name="name" value="生产、经营所得税纳税个人列表" />
			<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>
<html:form action="/webapp/smsb/grsds/grsdsJbxxAction2014cx.do">
<table width="80%" height="50%" border="0" align="center" cellpadding="0" cellspacing="0" >
	
	<bean:define id="sfzjlxListID" name="grsdsjbxxbForm2014cx" property="sfzjlxList"/>		<!-- 身份证件类型代码表 -->
	<bean:define id="gjListID" name="grsdsjbxxbForm2014cx" property="gjList"/>				<!-- 国籍 -->
	<bean:define id="swjgzzjgListID" name="grsdsjbxxbForm2014cx" property="swjgzzjgList"/>	<!-- 税务机关组织机构 -->
	<bean:define id="hyListID" name="grsdsjbxxbForm2014cx" property="hyList"/>				<!-- 行业 -->
	<bean:define id="djzclxListID" name="grsdsjbxxbForm2014cx" property="djzclxList"/>				<!-- 行业 -->
	<input type="hidden" name="actionType" id="actionType" value="Show" />
	
			<!--录入信息-->
			<tr>
				<td class="1-td1" >录入个人所得税基础信息表信息</td>
			</tr>

			<tr>
				<td class="1-td2" >
					<br/>
					<div align="left">
						<FONT color="#000000" size="1">
						<STRONG>&nbsp;&nbsp;年度：</STRONG>
						&nbsp;&nbsp;<html:text styleId="nd" maxlength="4" name="grsdsjbxxbForm2014cx" onchange="doChangeNd();" property="nd"/>
						</FONT>
					</div>
				
					<table  cellSpacing="0" cellPadding="0" width="97%" border="0" align = "center">
						<tr>
							<td class="2-td2-t-left" width="10%" ><!--姓名  不可修改-->
								姓名
							</td>
							<td class="2-td2-t-left" width="10%">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_xm"/>&nbsp;
							</td>
							<td class="2-td2-t-left" width="10%"><!--身份证件类型 不可修改-->
								身份证件类型
							</td>
							<td class="2-td2-t-left" width="10%">
								<html:hidden name="grsdsjbxxbForm2014cx" property="grxx_sfzjlx"></html:hidden>
							    <html:select disabled="true" name="grsdsjbxxbForm2014cx" property="grxx_sfzjlx" >
                          				<html:options collection="sfzjlxListID" property="value" labelProperty="text" />
                          		</html:select>
							</td>
							<td class="2-td2-t-left"  width="10%"><!--身份证件号码 不可修改-->
								身份证件号码
							</td>
							<td class="2-td2-t-center"  width="50%" colspan="5">
								<html:hidden name="grsdsjbxxbForm2014cx" property="grxx_sfzjhm"></html:hidden>
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_sfzjhm"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left"  width="10%"><!--纳税人类型-->
								纳税人类型
							</td>
							<td class="2-td2-center"  width="90%" colspan="9">
								<html:hidden property="grxx_nsrlx" name="grsdsjbxxbForm2014cx" />
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx0" value="0"  disabled="disabled">有任职受雇单位</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx1" value="1"  disabled="disabled">投资者</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx2" value="2"  disabled="disabled">无任职受雇单位（不含股东投资者）</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx3" value="3"  disabled="disabled">无住所个人</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left"  width="10%"><!--任职受雇单位名称-->
								任职受雇单位名称
							</td>
							<td class="2-td2-left"  width="60%" colspan="5">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_rzsgdwmc"/>&nbsp;
							</td>
							<td class="2-td2-left"  width="10%"><!--任职受雇单位纳税人识别号-->
								任职受雇单位纳税人识别号
							</td>
							<td class="2-td2-center"  width="20%" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_rzsgdwnsrsbh"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--三费一斤缴纳情况-->
								“三费一金”缴纳情况
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<html:hidden property="grxx_sfyjjnqk" name="grsdsjbxxbForm2014cx" />
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_0" value="0" disabled="disabled">基本养老保险费</input>
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_1" value="1" disabled="disabled">基本医疗保险费</input>
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_2" value="2" disabled="disabled">失业保险费</input>
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_3" value="3" disabled="disabled">住房公积金</input>
							</td>
							<td class="2-td2-left"  width="10%"><!--电子邮箱-->
								电子邮箱
							</td>
							<td class="2-td2-center"  width="20%" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_email"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--境内联系地址-->
								境内联系地址
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_jnlxdz"/>&nbsp;
							</td>
							<td class="2-td2-left"  width="10%"><!--邮政编码-->
								邮政编码
							</td>
							<td class="2-td2-center"  width="20%" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_yzbm"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--联系电话-->
								联系电话
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_lxdh"/>&nbsp;
							</td>
							<td class="2-td2-left"  width="10%"><!--邮政编码-->
								职业
							</td>
							<td class="2-td2-center"  width="20%" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_zy"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--联系电话-->
								职务
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<html:select  name="grsdsjbxxbForm2014cx" property="grxx_zw" styleId="grxx_zw" disabled="true">
								    <html:option value=""></html:option>
									<html:option value="1">高层</html:option>
									<html:option value="2">中层</html:option>
									<html:option value="3">普通</html:option>
								</html:select>
							</td>
							<td class="2-td2-left"  width="10%"><!--邮政编码-->
								学历
							</td>
							<td class="2-td2-center"  width="20%" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_xl"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left"><!--是否残疾人/烈属/孤老-->
								是否残疾人/烈属/孤老
							</td>
							<td class="2-td2-left" colspan="5">
								<html:hidden property="grxx_sfcjrlsgl" name="grsdsjbxxbForm2014cx" />
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_0" value="0" disabled="disabled">残疾</input>
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_1" value="1" disabled="disabled">烈属</input>
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_2" value="2" disabled="disabled">孤老</input>
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_3" value="3" disabled="disabled">否</input>
							</td>
							<td class="2-td2-left"><!--残疾等级情况-->
								残疾等级情况
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_cjdjqk"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left"><!-- 该栏仅由有境外所得纳税人填写 -->
								该栏仅由有境外所得纳税人填写
							</td>
							<td class="2-td2-left" colspan="2">
								<html:select name="grsdsjbxxbForm2014cx" property="grxx_yjwsd_dzlx" styleId="grxx_yjwsd_dzlx" disabled="true">
									<html:option value=""></html:option>
									<html:option value="0">户籍所在地</html:option>
									<html:option value="1">经常居住地</html:option>
								</html:select>
							</td>
							<td class="2-td2-left">
								邮政编码
							</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_yjwsd_yzbm"/>&nbsp;
							</td>
							<td class="2-td2-left">
								地址
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_yjwsd_dz"/>&nbsp;
							</td>
						</tr>
						
						<tr>
							<td class="2-td2-left" rowspan="5"><!--该栏仅由投资者纳税人填写-->
								该栏仅由投资者纳税人填写
							</td>
							<td class="2-td2-left" colspan="2"><!--该栏仅由投资者纳税人填写-->
								投资者类型
							</td>
							<td class="2-td2-center" colspan="7">
								<html:hidden property="grxx_tzzlx" name="grsdsjbxxbForm2014cx" />
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_0" value="0" disabled="disabled">个体工商户</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_1" value="1" disabled="disabled">个人独资企业投资者</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_2" value="2" disabled="disabled">合伙企业合伙人</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_3" value="3" disabled="disabled">承包、承租经营者</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_4" value="4" disabled="disabled">股东</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_5" value="5" disabled="disabled">其他投资者</input>
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" rowspan="4"><!--被投资单位信息-->
								被投资单位信息
							</td>
							<td class="2-td2-left" ><!--名称-->
								名称
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="qyxx_btzzxm"/>&nbsp;
								<html:hidden property="qyxx_jsjdm" name="grsdsjbxxbForm2014cx" />
							</td>
							<td class="2-td2-left" ><!--扣缴义务人编码-->
								扣缴义务人编码
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="qyxx_kjywrbh"/>&nbsp;
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" ><!--名称-->
								地址
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="qyxx_dz"/>&nbsp;
							</td>
							<td class="2-td2-left" ><!--扣缴义务人编码-->
								邮政编码
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="qyxx_yzbm"/>&nbsp;
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" ><!--名称-->
								登记注册类型
							</td>
							<td class="2-td2-left" colspan="3">
								<html:select styleId="qyxx_djzclx" name="grsdsjbxxbForm2014cx" property="qyxx_djzclx" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="djzclxListID" property="value" labelProperty="text" />
								</html:select>
							</td>
							<td class="2-td2-left" ><!--扣缴义务人编码-->
								行业
							</td>
							<td class="2-td2-center" colspan="3">
								
								<html:select styleId="qyxx_hy" name="grsdsjbxxbForm2014cx" property="qyxx_hy" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="hyListID" property="value" labelProperty="text" />
								</html:select>
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" ><!--名称-->
								所得税征收方式
							</td>
							<td class="2-td2-left" colspan="3">
								<html:select styleId="qyxx_sdszsfs" name="grsdsjbxxbForm2014cx" property="qyxx_sdszsfs" disabled="true">
									<html:option value=""></html:option>
									<html:option value="0">查账征收</html:option>
									<html:option value="1">核定征收</html:option>
								</html:select>
							</td>
							<td class="2-td2-left" ><!--扣缴义务人编码-->
								主管税务机关
							</td>
							<td class="2-td2-center" colspan="3">
								
								<html:select styleId="qyxx_zgswjg" name="grsdsjbxxbForm2014cx" property="qyxx_zgswjg" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="swjgzzjgListID" property="value" labelProperty="text" />
								</html:select>
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" rowspan="13"><!--名称-->
								该栏仅由无住所纳税人填写
							</td>
							<td class="2-td2-left" colspan="2"><!---->
								纳税人识别号
							</td>
							<td class="2-td2-center" colspan="7">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_nsrsbh"/>&nbsp;
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" colspan="2" ><!--国籍-->
								国籍
							</td>
							<td class="2-td2-left" colspan="3">
								
								<html:select styleId="grxx_wzsnsr_gj" name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_gj" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="gjListID" property="value" labelProperty="text" />
								</html:select>
							</td>
							<td class="2-td2-left"><!--出生地-->
								出生地
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_csd"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--性别-->
								性别
							</td>
							<td class="2-td2-left" colspan="3">
								<html:select styleId="grxx_wzsnsr_xb" name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_xb" disabled="true">
									<html:option value=""></html:option>
									<html:option value="0">男</html:option>
									<html:option value="1">女</html:option>
								</html:select>
							</td>
							<td class="2-td2-left"><!--出生日期-->
								出生日期
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_csrq"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--劳动就业证号码-->
								劳动就业证号码
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_ldjyzhm"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--是否税收协定缔约国对方居民-->
								是否税收协定缔约国对方居民
							</td>
							<td class="2-td2-center" colspan="3">
						
								<html:select styleId="grxx_wzsnsr_sfssxddygdfjm" name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_sfssxddygdfjm" disabled="true">
									<html:option value=""></html:option>
									<html:option value="0">是</html:option>
									<html:option value="1">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--境内职务-->
								境内职务
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnzw"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--境外职务-->
								境外职务
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jwzw"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--来华时间-->
								来华时间
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_lhsj"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--任职期限-->
								任职期限
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_rzqx"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--预计离境时间-->
								预计离境时间
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_yjljsj"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--预计离境地点-->
								预计离境地点
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_yjljdd"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" rowspan="2" ><!--境内任职受雇单位-->
								境内任职受雇单位
							</td>
							<td class="2-td2-left"><!--名称-->
								名称
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnrzsgdw_mc"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--扣缴义务人编码-->
								扣缴义务人编码
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnrzsgdw_kjywrbm"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--地址-->
								地址
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnrzsgdw_dz"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--邮政编码-->
								邮政编码
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnrzsgdw_yzbm"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" rowspan="2" ><!--境内受聘签约单位-->
								境内受聘签约单位
							</td>
							<td class="2-td2-left" ><!--名称-->
								名称
							</td>
							<td class="2-td2-left"  colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnspqydw_mc"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--扣缴义务人编码-->
								扣缴义务人编码
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnspqydw_kjywrbm"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--地址-->
								地址
							</td>
							<td class="2-td2-left"  colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnspqydw_dz"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--邮政编码-->
								邮政编码
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnspqydw_yzbm"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--境外派遣单位-->
								境外派遣单位
							</td>
							<td class="2-td2-left" ><!--名称-->
								名称
							</td>
							<td class="2-td2-left"  colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jwpqdw_mc"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--地址-->
								地址
							</td>
							<td class="2-td2-center"  colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jwpqdw_dz"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--支付地-->
								支付地
							</td>
							<td class="2-td2-left" colspan="3">
								<html:select styleId="grxx_wzsnsr_zfd" name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_zfd" disabled="true">
									<html:option value=""></html:option>
									<html:option value="0">境内支付</html:option>
									<html:option value="1">境外支付</html:option>
									<html:option value="2">境内、外同时支付</html:option>
								</html:select>
							</td>
							<td class="2-td2-left"><!--境外支付国国别-->
								境外支付国国别
							</td>
							<td class="2-td2-center" colspan="3">
								<html:select styleId="grxx_wzsnsr_jwzfggb" name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jwzfggb" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="gjListID" property="value" labelProperty="text" />
								</html:select>
							</td>
						</tr>
						

					</table>
					<br/>

				</td>
			</tr>
		
		
</table>

<div  align="center">
	<span>
		<!-- <a style="cursor:hand"  tabIndex="-1" onClick="doSave();return false;"><img name="baocun" value="保存" alt="保存" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>/images/baocun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/baocun1.jpg" width="79" height="22" id="baocun"></a>&nbsp;&nbsp;-->
		<a style="cursor:hand"  tabIndex="-1" onClick="doNext();return false;"><img name="xiayiye" value="下一页" alt="下一页" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>/images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/xaiyiye1.jpg" width="79" height="22" id="xiayiye"></a>&nbsp;&nbsp;
		<a style="cursor:hand"  tabIndex="-1" onClick="doBack();return false;"><img name="fanhui" value="返回" alt="返回" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/fanhui1.jpg" width="79" height="22" id="fanhui"></a>&nbsp;&nbsp;
		<a style="cursor:hand"  tabIndex="-1" onClick="tuichu();return false;"><img  name="return" value="退出" alt="退出" onMouseOver="MM_swapImage('return','','<%=static_contextpath%>/images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/tuichu1.jpg"  width="79" height="22" id="return"></a> 
	</span>			
</div>
<br/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
				 <tr>
				    <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				    <td width="20%" align="center" class="black9">	<a style="cursor:hand;text-decoration:underline;"  tabIndex="-1" onClick="javascript:clickTbsm();"><strong><font color="#0000FF">请点击查看填表说明</font></strong></a></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47" id="tbsm" style="display:none;">
						一、	适用范围<br/>
&nbsp;&nbsp;本表适用于自然人纳税人基础信息的填报。<br/>
&nbsp;&nbsp;各地税务机关可根据本地实际，由自然人纳税人初次向税务机关办理相关涉税事宜时填报本表；初次申报后，以后仅需在信息发生变化时填报。<br/>
二、	本表各栏填写如下：<br/>
&nbsp;(一)	表头栏<br/>
&nbsp;&nbsp;1.	姓名：填写纳税人姓名。中国境内无住所个人，其姓名应当分别用中、外两种文字填写。<br/>
&nbsp;&nbsp;2.	身份证件类型：填写纳税人有效身份证件（照）名称。中国居民，填写身份证、军官证、士兵证等证件名称；中国境内无住所个人，填写护照、港澳居民来往内地通行证、台湾居民来往大陆通行证等证照名称。<br/>
&nbsp;&nbsp;3.	身份证件号码：填写身份证件上的号码。<br/>
&nbsp;&nbsp;4.	纳税人类型：纳税人根据自身情况在对应框内打“√”，可多选。<br/>
&nbsp;&nbsp;&nbsp;（1）	有任职受雇单位：是指纳税人有固定任职受雇单位。<br/>
&nbsp;&nbsp;&nbsp;（2）	无任职受雇单位（不含股东投资者）：是指纳税人为自由职业者，没有与任何单位签订任职受雇合同；不含企业股东、个体工商户、个人独资企业投资者、合伙企业合伙人、承包承租经营者。<br/>
&nbsp;&nbsp;&nbsp;（3）	投资者：是指有对外投资的纳税人。<br/>
&nbsp;&nbsp;&nbsp;（4）	无住所个人：是指在中国境内无住所的纳税人。“无住所”是相对有住所而言；在中国境内有住所的个人，是指因户籍、家庭、经济利益关系而在中国境内习惯性居住的个人。<br/>
&nbsp;&nbsp;5.	任职受雇单位名称及纳税人识别号：填写纳税人签订任职受雇合同的单位名称全称及其在税务机关办理登记时的纳税人识别号。前列填名称，后列填纳税人识别号。<br/>
&nbsp;&nbsp;与多家单位签订合同的，须分行列示。没有则不填。<br/>
&nbsp;&nbsp;6.	“三费一金”缴纳情况：纳税人根据自己缴纳社会保险费情况在“基本养老保险费”、“基本医疗保险费”、“失业保险费”、“住房公积金”对应框内打“√”；如果都没有缴纳的，在“无”栏打“√”。<br/>
&nbsp;&nbsp;7.	电子邮箱：填写税务机关能与纳税人联系的电子邮箱地址。<br/>
&nbsp;&nbsp;8.	境内联系地址、邮政编码：填写税务机关能与纳税人联系的有效中国境内联系地址和邮政编码。<br/>
&nbsp;&nbsp;9.	联系电话：填写税务机关能与纳税人联系的电话。<br/>
&nbsp;&nbsp;10.	职业：填写纳税人所从事的职业。职业分类按劳动和社会保障部门的国标填写。<br/>
&nbsp;&nbsp;11.	职务：填写纳税人在任职受雇单位所担任的职务，在“高层”、“中层”、“普通”三项前选择其一打“√”。<br/>
&nbsp;&nbsp;12.	学历：填写纳税人取得的最终学历。<br/>
&nbsp;&nbsp;13.	是否残疾人/烈属/孤老：符合本栏情况的，在对应框前打“√”；否则，在“否”栏打“√”。<br/>
&nbsp;(二)	有境外所得的纳税人填写栏：纳税人从中国境外取得所得的填写本栏；没有则不填。<br/>
&nbsp;纳税人在选填此栏时，应根据《国家税务总局关于印发〈个人所得税自行纳税申报办法（试行）〉的通知》第十一条第二款“从中国境外取得所得的，向中国境内户籍所在地主管税务机关申报。在中国境内有户籍，但户籍所在地与中国境内经常居住地不一致的，选择并固定向其中一地主管税务机关申报。在中国境内没有户籍的，向中国境内经常居住地主管税务机关申报”的规定选择填写。<br/>
&nbsp;选择后，纳税人在“户籍所在地”或“经常居住地”对应框内打“√”并填写具体地址。<br/>
&nbsp;(三)	投资者纳税人填写栏：由自然人股东、投资者填写。如果没有对外投资的，则不填。<br/>
&nbsp;&nbsp;1.	投资者类型：纳税人根据自身情况在对应框内打“√”，可多选。<br/>
&nbsp;&nbsp;2.	被投资单位信息：填写纳税人对外投资单位的有关信息。<br/>
&nbsp;&nbsp;&nbsp;（1）	纳税人名称：填写税务机关核发的被投资单位税务登记证载明的纳税人名称全称。投资多家单位的，需分别列示。<br/>
&nbsp;&nbsp;&nbsp;（2）	扣缴义务人编码：填写税务机关核发的税务登记证号码。<br/>
&nbsp;&nbsp;&nbsp;（3）	地址、邮政编码：填写投资者投资单位的地址和邮政编码。<br/>
&nbsp;&nbsp;&nbsp;（4）	登记注册类型：填写被投资单位在工商行政管理机关登记注册的类型。<br/>
&nbsp;&nbsp;&nbsp;分内资企业（国有企业、集体企业、股份合作企业、联营企业、有限责任公司、股份有限公司、私营企业和其他企业）、港澳台商投资企业和外商投资企业三大类。[注：内资企业需填至括号内的企业类型。]<br/>
&nbsp;&nbsp;&nbsp;（5）	行业：按照国民经济行业分类国家标准填写至大类。<br/>
&nbsp;&nbsp;&nbsp;（6）	所得税征收类型：填写被投资单位所得税的征收方式。<br/>
&nbsp;&nbsp;&nbsp;（7）	主管税务机关：填写被投资单位的主管税务机关名称。<br/>
&nbsp;&nbsp;&nbsp;（8）	股东及其他投资者填写栏：由自然人股东和其他投资者填写。个体工商户主、个人独资企业投资者、合伙企业合伙人、承包承租经营者不填写此栏。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;①公司股本（投资）总额：填写被投资单位的公司股本（投资）总额。<br/>
&nbsp;&nbsp&nbsp;&nbsp;;②个人股本（投资）总额：填写自然人股东、投资者在被投资单位个人投资的股本（投资）额。<br/>
&nbsp;(四)	无住所纳税人填写栏：由在中国境内无住所纳税人填写。其他则不填。<br/>
&nbsp;&nbsp;（1）	纳税人识别号：填写主管税务机关赋予的18位纳税人识别号。该纳税人识别号作为境内无住所个人的唯一身份识别码，由纳税人到主管税务机关办理初次涉税事项，或者扣缴义务人办理该纳税人初次扣缴申报时，由主管税务机关授予。<br/>
&nbsp;&nbsp;（2）	国籍（地区）：填写纳税人的国籍或者地区。<br/>
&nbsp;&nbsp;（3）	出生地：填写纳税人出生地的国籍及地区。<br/>
&nbsp;&nbsp;（4）	劳动就业证号码：填写纳税人在中国境内劳动就业证上的号码。<br/>
&nbsp;&nbsp;（5）	境内职务：填写该纳税人在境内公司担任的职务。<br/>
&nbsp;&nbsp;（6）	境外职务：填写该纳税人在境外公司担任的职务。<br/>
&nbsp;&nbsp;（7）	是否税收协定缔约国对方居民：纳税人来自于与中国签订避免双重征税协定的国家或地区的，在“是”栏对应框内打“√”；否则，在“否”栏打“√”。<br/>
&nbsp;&nbsp;（8）	来华时间：填写纳税人到达中国境内的年月日。<br/>
&nbsp;&nbsp;（9）	任职期限：填写纳税人在任职受雇单位的任职期限。<br/>
&nbsp;&nbsp;（10）	预计离境时间：填写纳税人预计离境的年月日。<br/>
&nbsp;&nbsp;（11）	预计离境地点：填写纳税人预计离境的地点。<br/>
&nbsp;&nbsp;（12）	境内任职受雇单位：填写纳税人签订任职受雇合同的单位的相关信息。如果填写本栏，则境内受聘签约单位栏不用填写。<br/>
&nbsp;&nbsp;&nbsp;①名称：填写纳税人任职受雇单位的名称全称。<br/>
&nbsp;&nbsp;&nbsp;②扣缴义务人编码：填写税务机关确定的任职受雇单位的税务编码号码。<br/>
&nbsp;&nbsp;&nbsp;③地址、邮政编码：填写任职受雇单位的地址和邮政编码。<br/>
&nbsp;&nbsp;（13）	境内受聘签约单位：填写纳税人受聘或签约单位的相关信息。如果填写本栏，则上栏境内任职受雇单位栏则不用填写。<br/>
&nbsp;&nbsp;&nbsp;①名称：填写纳税人受聘签约单位的名称全称。<br/>
&nbsp;&nbsp;&nbsp;②扣缴义务人编码：填写税务机关确定的受聘签约单位的税务编码号码。<br/>
&nbsp;&nbsp;&nbsp;③地址、邮政编码：填写受聘签约单位的地址和邮政编码。<br/>
&nbsp;&nbsp;（14）	境外派遣单位：如果纳税人有境外派遣单位的，填写本栏。否则不填写。<br/>
&nbsp;&nbsp;&nbsp;①名称：填写纳税人境外派遣单位的名称全称，用中、外两种文字填写。<br/>
&nbsp;&nbsp;&nbsp;②地址：填写境外派遣单位的地址。<br/>
&nbsp;&nbsp;（15）	支付地：填写纳税人取得的所得的支付地，在“境内支付”、“境外支付”和“境、内外同时支付”三种类型中选择一种填写。<br/>
&nbsp;&nbsp;（16）	境外支付地国别（地区）：如果纳税人取得的所得支付地为国外的，填写境外支付地的国别或地区名称。<br/>
				</td>
		   </tr>
		</table>
		<br/>
	  <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
				 <tr>
				    <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				    <td width="20%" align="center" class="black9"><strong><font color="#0000FF">注意事项</font></strong></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47"  >
				        <p style ="color:red">
				            &nbsp;&nbsp;&nbsp;&nbsp;谨声明：此表是根据《中华人民共和国个人所得税法》及其实施条例和国家相关法律法规规定填写的，是真实的、完整的、可靠的。<br>
				         </p>
				       </td>
				    </tr>
	</table>
<br/><br/>
</html:form>
<%@ include file="../../../include/footernew.jsp"%>
</body>
</html>