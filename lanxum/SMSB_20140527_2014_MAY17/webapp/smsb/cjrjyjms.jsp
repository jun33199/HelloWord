<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>



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
	<title>安置残疾人就业企业营业税减免税申报表</title>
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
</head>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="fnOnLoad()">
	<%@ include file="include/header.jsp"%>
	<html:form action="/webapp/smsb/cjrjyjmsbAction.do" method="POST">
		<html:hidden property="actionType" />
		<html:hidden property="lrr" />
		<html:hidden property="sqspbh" />
		<html:hidden property="sign" />
		<table width="70%" align="center" cellspacing="0" class="table-99">
			<tr>
				<td valign="top">
					<br>
					<table cellspacing=0 border=0 class="table-99" align="center">
						<tr>
							<td class="1-td1">
								安置残疾人就业企业营业税减免税申报表
								<br>
							</td>
						</tr>
						<tr>
							<td valign="top" class="1-td2">
							   <div  id="div1">
							    <table class="table-99" cellspacing="0">
									<tr class="black9">
										<td class="2-td2-t-left" width="35%" height="20" nowrap>
											<div align="right">
												请确认计算机代码
											</div>
										</td>
										<td class="2-td2-t-left" colspan="3" width="30%">
											<div align="left">
												 <html:text property="jsjdm" size="20" maxlength="8"/><font color="#FF0000">&nbsp*</font>
											</div>
										</td>
										
										<td class="2-td2-t-center" width="35%">
										<div align="left">
											&nbsp;&nbsp;<input type="image" accesskey="q" tabIndex="-1" onClick="doQuery(); return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/queren2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="确认" src="<%=static_contextpath%>images/queren1.jpg" width="79" height="22" id="chaxun" style="cursor:hand">
											&nbsp;&nbsp;<input type="image" accesskey="c" tabIndex="-1" onClick="tuichu(); return false;" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"	onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor: hand">
										</div>
										</td>
										

									</tr>
								</table>
								</div>
								<div  id="div2" style="display: none">
								<table class="table-99" cellspacing="0">
									<tr class="black9">
										<td align="left">
											<div>
												税款所属时间：
												<html:text property="skssrq" maxlength="6" size="12" />&nbsp;&nbsp;<input type="image" accesskey="q" tabIndex="-1" onClick="doQuery(); return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="确认" src="<%=static_contextpath%>images/chaxun1.jpg" width="79" height="22" id="chaxun" style="cursor:hand">
											</div>
										</td>
										<td align="center">
											<div align="right">
												金额单位:元至角分
											</div>
										</td>
								</table>
								<table class="table-99" cellspacing="0">

									<tr class="black9">
										<td class="2-td2-t-left" width="20%" height="20" nowrap>
											<div align="right">
												单位名称(公章)
											</div>
										</td>
										<td class="2-td2-t-left" colspan="3" width="35%">
											<div align="left">
												<!--<html:text property="nsrmc"/>-->
													&nbsp;<ttsoft:write name="cjrjyjmsForm" property="nsrmc" />
											</div>
										</td>
										<td class="2-td2-t-left" width="20%" nowrap>
											<div align="right">
												纳税人识别号(税务登记证号)
											</div>
										</td>
										<td class="2-td2-t-center" width="140" width="25%">
											<div align="left">
												<html:text property="swdjzh" styleClass="txtInput" readonly="true"/>
											</div>
										</td>
									</tr>
									<tr>
										<td class="2-td2-left">
											<div align="right">
												注册地址
											</div>
										</td>
										<td class="2-td2-left" colspan="3">
											<div align="left">
												&nbsp;<!--<html:text property="zcdz"/>-->
												<ttsoft:write name="cjrjyjmsForm" property="zcdz" />
											</div>
										</td>
										<td class="2-td2-left">
											<div align="right">
												单位性质
											</div>
										</td>
										<td class="2-td2-center">
											<div align="left">
												<!--<html:text property="dwxz"  size="20"/>-->
												<html:select property="dwxz">
													<html:options property="dwxzList" />
												</html:select>
											</div>
										</td>
									</tr>

									<tr>
										<td class="2-td2-left" align="right">
											<div align="right">
												职工总人数
											</div>
										</td>
										<td class="2-td2-left">
											<div align="left">
												<html:text property="zgzrs" maxlength="6" size="23" />
											</div>
										</td>
										<td class="2-td2-left" width="100" height="20">
											<div align="center">
												安置残疾职工人数
											</div>
										</td>
										<td class="2-td2-left">
											<div align="left">
												<html:text property="cjrzgrs" maxlength="6" size="15" />
											</div>
										</td>

										<td class="2-td2-left" width="100" height="20">
											<div align="right">
												残疾人职工占职工总人数的比例
											</div>
										</td>
										<td class="2-td2-center">
											<div align="left">
												<html:text property="cjrybl" maxlength="6" size="15" />
											</div>
										</td>
									</tr>

									<tr>
										<td class="2-td2-left" height="70">
											<div align="right">
												经营范围
											</div>
										</td>
										<td class="2-td2-center" colspan="5">
											<div align="left">
												&nbsp;<!--<html:textarea property="jyfw"  style="height:60px;width:567px"  tabIndex="-1"/>-->
												<ttsoft:write name="cjrjyjmsForm" property="jyfw" />
											</div>
										</td>
									</tr>

								</table>

								<table class="table-99" cellspacing="0">
									<tr>
										<td width="10%" class="2-td2-left" colspan="2">
											项目
										</td>
										<td width="20%" class="2-td2-left">
											栏次
										</td>
										<td width="20%" class="2-td2-center">
											金额
										</td>
									</tr>
									<tr>
										<td width="20%" class="2-td2-left" colspan="2">
											本月营业税应税收入
										</td>
										<td width="20%" class="2-td2-left">
											1
										</td>
										<td width="20%" class="2-td2-center">
											<div align="center">
												<html:text property="ynyyssr" maxlength="15" size="25" />
											</div>
										</td>
									</tr>
									<tr>
										<td width="20%" class="2-td2-left" colspan="2">
											本月应交营业税税额
										</td>
										<td width="20%" class="2-td2-left">
											2
										</td>
										<td width="20%" class="2-td2-center">
											<div align="center">
												<html:text property="yjyysse" maxlength="15" size="25" />
											</div>
										</td>
									</tr>
									<tr>
										<td width="20%" class="2-td2-left" colspan="2">
											其中：可享受本优惠政策的税额
										</td>
										<td width="20%" class="2-td2-left">
											3
										</td>
										<td width="20%" class="2-td2-center">
											<div align="center">
												<html:text property="xsyhzzse" maxlength="15" size="25" />
											</div>
										</td>
									</tr>
									<tr>
										<td width="20%" class="2-td2-left" colspan="2">
											本月应减征营业税限额
										</td>
										<td width="20%" class="2-td2-left">
											4=35000/12*安置残疾职工人数
										</td>
										<td width="20%" class="2-td2-center">
											<div align="center">
												<html:text property="byyjzyysxe" size="25" maxlength="15" tabIndex="-1" />
											</div>
										</td>
									</tr>
									<tr>
										<td width="20%" class="2-td2-left" colspan="2">
											上月未减征营业税限额
										</td>
										<td width="20%" class="2-td2-left">
											5=上月9
										</td>
										<td width="20%" class="2-td2-center">
											<div align="center">
												<html:text property="syyjzyysxe" maxlength="15" size="25" />
											</div>
										</td>
									</tr>
									<tr>
										<td width="20%" class="2-td2-left" colspan="2">
											本月可减征营业税限额
										</td>
										<td width="20%" class="2-td2-left">
											6=4+5
										</td>
										<td width="20%" class="2-td2-center">
											<div align="center">
												<html:text property="bykjzyysxe" maxlength="15" size="25" />
											</div>
										</td>
									</tr>
									<tr>
										<td width="20%" class="2-td2-left" colspan="2">
											本月实际减征营业税税额
										</td>
										<td width="20%" class="2-td2-left">
											7(若3>=6则为6，否则为3)
										</td>
										<td width="20%" class="2-td2-center">
											<div align="center">
												<html:text property="bysjjzyysye" maxlength="15" size="25" />
											</div>
										</td>
									</tr>
									<tr>
										<td width="20%" class="2-td2-left" colspan="2">
											本月减征营业税后应交营业税税额
										</td>
										<td width="20%" class="2-td2-left">
											8=2-7
										</td>
										<td width="20%" class="2-td2-center">
											<div align="center">
												<html:text property="bysjyesse" maxlength="15" size="25" />
											</div>
										</td>
									</tr>
									<tr>
										<td width="20%" class="2-td2-left" colspan="2">
											本月未减征营业税限额
										</td>
										<td width="20%" class="2-td2-left">
											9 = 6-7
										</td>
										<td width="20%" class="2-td2-center">
											<div align="center">
												<html:text property="bymjzyysxe" maxlength="15" size="25" />
											</div>
										</td>
									</tr>

								</table>
								<br>
								<table class="table-99" cellspacing="0">
									<tr>
										<td width="7%" class="2-td2-t-left">
											<div align="right">
												录入日期
											</div>
										</td>
										<td width="30%" class="2-td2-t-center" colspan="2">
											<div align="left">
												<html:text property="lrrq" maxlength="8" size="20" styleClass="txtInput"
													readonly="true" />
											</div>
										</td>
									</tr>
								</table>
								<br>
								<div align="center">
									<input type="image" accesskey="s" tabIndex="-1"
										onClick="doSave();return false"
										onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
										onMouseOut="MM_swapImgRestore()"
										src="<%=static_contextpath%>images/bc-s1.jpg" alt="保存"
										width="79" height="22" id="baocun" style="cursor: hand">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="image" accesskey="u" tabIndex="-1"
										onClick="ql();return false; "
										onMouseOver="MM_swapImage('qingchu','',' <%=static_contextpath%>images/qc-u2.jpg ',1)"
										onMouseOut="MM_swapImgRestore()"
										src=" <%=static_contextpath%>images/qc-u1.jpg " alt="清除"
										width="79" height="22" id="qingchu" style="cursor: hand">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="image" accesskey="c" tabIndex="-1"
										onClick="tuichu(); return false;"
										onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1"
										src="<%=static_contextpath%>images/tc-c1.jpg" width="79"
										height="22" style="cursor: hand">
								</div>
								<br>
								<table width="99%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<hr width="100%" size="1">
										</td>
										<td width="99" align="center" class="black9">
											<strong><font color="#0000FF">注 意 事 项</font> </strong>
										</td>
										<td>
											<hr width="100%" size="1">
										</td>
									</tr>
								</table>
								<table width="99%" border="1" align="center" cellpadding="1"
									cellspacing="1" bordercolor="#FFFFFF" class="black9">
									<tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
										<td height="47">
											&nbsp;&nbsp;1．凡享受财税[2007]92号文件第一条规定按月征减营业税的纳税人，应于每月终了后10日内向主管地税机关报送本表；
											<br>
											&nbsp;&nbsp;2．本表"单位性质"栏，填写福利企业，盲人按摩机构，工疗机构或其他单位；
											<br>
											&nbsp;&nbsp;3．本表"安置残疾职工人数栏"，填写纳税人实际安置的符合财税[2007]
											92号文件规定的残疾职工人数。
										</td>
									</tr>
								</table>
								<p>
									&nbsp;
								</p>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<%@ include file="include/footer.jsp"%>

<script language="JavaScript">

window.onload   =   new   function() 
{ 
	  var sign= document.forms[0].sign.value;
	  if(!(sign==null || sign=="")){
	     show();
	     hidden();
	  }
}
function show(){
document.getElementById("div2").style.display="";
//alert(document.getElementById("div").style.display)
}
function hidden(){
document.getElementById("div1").style.display="none";
//alert(document.getElementById("div").style.display)
}


function fnOnLoad()

{

    document.forms[0].jsjdm.focus();

}

function doSubmit(method){
	document.all.actionType.value=method;
	document.all.cjrjyjmsForm.submit();
	return false;
}

function jsjdmQuery(){
      if(event.keyCode==13) event.keyCode = 9;
}

function chkjsjdm()
{
	var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	if(jsjdm==null || jsjdm==""){
        alert("计算机代码不允许是空！");
        return false;
        }
        return true;
        
}

<%--
function doSave()
{
	if(!checkInput()) {
alert("doSave + warnMessage");
		alert(warnMessage);
	} else {
alert("doSave + null to save");
		doSubmitForm("Save");
		return false;
	}      
}
--%>

function doQuery()
{
        var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	if(jsjdm==null || jsjdm==""){
        alert("计算机代码不允许是空！");
        //document.forms[0].jsjdm.focus();
        return true;
        }
	doSubmitForm("Query");
   	return false;
}

function ql(){
	document.forms[0].bysjyesse.value = "";
	document.forms[0].bymjzyysxe.value = "";
    document.forms[0].skssrq.value = "";
    document.forms[0].zgzrs.value = "";
    document.forms[0].cjrzgrs.value = "";
    document.forms[0].cjrybl.value = "" ;
    document.forms[0].ynyyssr.value = "" ;
    document.forms[0].yjyysse.value = "";
    document.forms[0].xsyhzzse.value = "";
    document.forms[0].byyjzyysxe.value = "";
    document.forms[0].syyjzyysxe.value = "";
    document.forms[0].bykjzyysxe.value="";
	document.forms[0].bysjjzyysye.value = "";
	//document.forms[0].bkhlrze.value = "";
  }
function printFunc(){
  document.forms[0].target="blank";
  doSubmitForm('Print');
}

// 提示信息
var warnMessage = "";
// 追加的详细提示信息
var alertStr="";

// 检查输入信息格式
function checkInput() {

	if(trim(document.forms[0].jsjdm.value) == "") {
		warnMessage += "*计算机代码不能为空\n";
	} else if(!/^[A-Za-z0-9]+$/.test(trim(document.forms[0].jsjdm.value))) {
		warnMessage += "*计算机代码只能包含字母和数据\n";
	}
	if(trim(document.forms[0].skssrq.value) == "") {
		warnMessage += "\n*税款所属时间不能为空\n";
	} else if(!/^\d{6}$/.test(trim(document.forms[0].skssrq.value))) {
		warnMessage += "\n*税款所属时间格式为YYYYMM\n";
	} else if(!/^[0-9]+$/.test(trim(document.forms[0].skssrq.value))){
	    warnMessage += "\n*税款所属时间格式为YYYYMM\n";
	}
	
	if(trim(document.forms[0].zgzrs.value) == "") {
		warnMessage += "\n*职工总人数不能为空\n";
	} else if(!isDigit(trim(document.forms[0].zgzrs.value))) {
		warnMessage += "\n*职工总人数为整数\n"
	}
	
	if(trim(document.forms[0].cjrzgrs.value) == "") {
		warnMessage += "\n*安置残疾职工人数不能为空\n";
	} else if(!isDigit(trim(document.forms[0].cjrzgrs.value))) {
		warnMessage += "\n*安置残疾职工人数为整数\n"
	}
	
	if(trim(document.forms[0].cjrybl.value) == "") {
		warnMessage += "\n*残疾人职工占职工总人数的比例不能为空\n";
	} else if(!isEqualNum(document.forms[0].cjrybl, 0, 100, "", 6, 3)) {
		warnMessage += "\n*残疾人职工占职工总人数" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].ynyyssr.value) == "") {
		warnMessage += "\n*本月营业税应税收入不能为空\n";
	} else if(!isEqualNum(document.forms[0].ynyyssr, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*本月营业税应税收入" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].yjyysse.value) == "") {
		warnMessage += "\n*本月应交营业税税额不能为空\n";
	} else if(!isEqualNum(document.forms[0].yjyysse, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*本月应交营业税税额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].xsyhzzse.value) == "") {
		warnMessage += "\n*其中：可享受本优惠政策的税额不能为空\n";
	} else if(!isEqualNum(document.forms[0].xsyhzzse, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*其中：可享受本优惠政策的税额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].byyjzyysxe.value) == "") {
		warnMessage += "\n*本月应减征营业税限额不能为空\n";
	} else if(!isEqualNum(document.forms[0].byyjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*本月应减征营业税限额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].syyjzyysxe.value) == "") {
		warnMessage += "\n*上月未减征营业税限额不能为空\n";
	} else if(!isEqualNum(document.forms[0].syyjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*上月未减征营业税限额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bykjzyysxe.value) == "") {
		warnMessage += "\n*本月可减征营业税限额不能为空\n";
	} else if(!isEqualNum(document.forms[0].bykjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*本月可减征营业税限额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bysjjzyysye.value) == "") {
		warnMessage += "\n*本月实际减征营业税税额不能为空\n";
	} else if(!isEqualNum(document.forms[0].bysjjzyysye, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*本月实际减征营业税税额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bysjyesse.value) == "") {
		warnMessage += "\n*本月减征营业税后应交营业税税额不能为空\n";
	} else if(!isEqualNum(document.forms[0].bysjyesse, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*本月减征营业税后应交营业税税额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bymjzyysxe.value) == "") {
		warnMessage += "\n*本月未减征营业税限额不能为空\n";
	} else if(!isEqualNum(document.forms[0].bymjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*本月未减征营业税限额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].lrrq.value) == "") {
		warnMessage += "\n*录入日期不能为空";
	} else if(!isDate(document.forms[0].lrrq,"")) {
		warnMessage += "\n*录入日期格式为YYYYMMDD";
	}
}

// 检查是否满足等式要求
function checkEquation() {

	if(parseFloat(trim(document.forms[0].byyjzyysxe.value)) != (35000/12*(parseFloat(trim(document.forms[0].cjrzgrs.value)))).toFixed(2)) {
		warnMessage += "\n*本月应减征营业税限额 = 35000/12*安置残疾职工人数 \n";
	}
	
	<%--
	if(parseFloat(trim(document.forms[0].syyjzyysxe.value)) != ) {
		warnMessage += "\n*上月未减征营业税限额 = 本月未减征营业税限额(上月)\n";
	}
	--%>
	
	if(parseFloat(trim(document.forms[0].bykjzyysxe.value)) != (parseFloat(trim(document.forms[0].byyjzyysxe.value)) + parseFloat(trim(document.forms[0].syyjzyysxe.value))).toFixed(2)) {
		warnMessage += "\n*本月可减征营业税限额 = 本月应减征营业税限额 + 上月未减征营业税限额\n";
	}
	
	if(parseFloat(trim(document.forms[0].xsyhzzse.value)) >= parseFloat(trim(document.forms[0].bykjzyysxe.value))) {
		if(parseFloat(trim(document.forms[0].bysjjzyysye.value)) != parseFloat(trim(document.forms[0].bykjzyysxe.value))) {
			warnMessage += "\n*本月实际减征营业税税额 = 本月可减征营业税限额\n";
		}
	} else {
		if(parseFloat(trim(document.forms[0].bysjjzyysye.value)) != parseFloat(trim(document.forms[0].xsyhzzse.value))) {
			warnMessage += "\n*本月实际减征营业税税额 = 其中：可享受本优惠政策的税额\n";
		}
	}
	
	if(parseFloat(trim(document.forms[0].bysjyesse.value)) != (parseFloat(trim(document.forms[0].yjyysse.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2)) {
		warnMessage += "\n*本月减征营业税后应交营业税税额 = 本月应交营业税税额 - 本月实际减征营业税税额\n";
	}
	
	if(parseFloat(trim(document.forms[0].bymjzyysxe.value)) != (parseFloat(trim(document.forms[0].bykjzyysxe.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2)) {
		warnMessage += "\n*本月未减征营业税限额 = 本月可减征营业税限额 - 本月实际减征营业税税额\n";
	}
	
	//alert("-----------------------------------------");
	//alert("35000/12*安置残疾职工人数 = " + (35000/12*(parseFloat(trim(document.forms[0].cjrzgrs.value)))).toFixed(2));
	//alert("本月应减征营业税限额 + 上月未减征营业税限额 = " + (parseFloat(trim(document.forms[0].byyjzyysxe.value)) + parseFloat(trim(document.forms[0].syyjzyysxe.value))).toFixed(2));
	//alert("本月应交营业税税额 - 本月实际减征营业税税额 = " + (parseFloat(trim(document.forms[0].yjyysse.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2));
	//alert("本月可减征营业税限额 - 本月实际减征营业税税额 = " + (parseFloat(trim(document.forms[0].bykjzyysxe.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2));
	//alert("-----------------------------------------");
}

// 保存
function doSave() {
	// 检查输入格式
	checkInput();
	if(warnMessage == "") {
		// 检查等式校验
		checkEquation();
		if(warnMessage == "") {
			doSubmitForm("Save");
		} else {
			alert(warnMessage);
			warnMessage = "";
		}
	} else {
		alert(warnMessage);
		warnMessage = "";
	}
}

//function  : 检查输入字符串是否由数字组成
//parameters: DigitString: String,待检查字符串的值
//			  totalLength: int, 数字的长度限制（不包括小数点）
//			  decimalLength: int，小数的长度限制
//            totalLength-decimalLength 整数部分的限制
//return    : false: 字符串中包含除数字外的字符
//			  true : else
function isEqualNum(obj,minValue,maxValue,empty,totalLength, decimalLength)
{
	  if(window.event.keyCode!=13 && window.event.keyCode!=0) return true;
		var DigitString=trim(obj.value);
		var succeed=true;
		var havedecimalLength=true;
		var or_decimalLength=decimalLength;
		if(DigitString.length==0){
		  if(empty!=null){
				succeed=false;
		  }else{
		    return true;
		  }
		}
		if(decimalLength==null){
			decimalLength=2;
			or_decimalLength=2;
		}
		var re = "";
		//当小数部分长度为0时 将小数部分设为null
		if(decimalLength=="0"){
			decimalLength=null;
			or_decimalLength=0;
			}
		//当小数部分长度不为null 如果没有小数点在小数点后加00
		if(decimalLength!=null){if(DigitString.indexOf(".")<0) DigitString+=".00";}
    if (decimalLength!=null)
    {   //小数不为空
        re = re+"\\.[\\d]{1,"+ decimalLength +"}"
    }

    if (totalLength == null)
    {   //未设定总长度
        re= "\\d*"+re;
    }
    else
    {   //设定总长度
        //当小数部分为空的情况下，就是判断数字的长度
        var intergerLength = totalLength;
        if (decimalLength!=null)
        {   //当小数部分不为空判断，整数的位数和小数的位数是否正确
            intergerLength = totalLength-decimalLength;
        }
        //re="[\\d]{0,"+ intergerLength +"}"+re;
		re="[\\d]{1,"+ intergerLength +"}"+re;//不允许从小数点开始
    }
    re = new RegExp("^(-){0,1}"+re+"$","g");//郭现华 modify 2003/11/11 可以判断负数。

    //当字符串为空、位数不对、不能匹配成数字时
		//var meetRequest=true;
    if(re.exec(DigitString) == null)
    {
			  alertStr+="的值必须为数字 "
				if(totalLength!=null) alertStr+="总长度为"+totalLength+"位 ";
				if(havedecimalLength) {
					alertStr+="小数点后的长度为"+or_decimalLength+"位 ";
				}
        succeed=false;
    }
		//当指定字符串范围时 字符串不在指定范围内
		if(maxValue!=null){
			if(parseFloat(DigitString)>parseFloat(maxValue)){
				alertStr+="数字不大于"+maxValue+" ";
				succeed= false;
			}
		}
		if(minValue!=null){
			if(parseFloat(DigitString)<parseFloat(minValue)){
				alertStr+="数字不小于"+minValue+" ";
				succeed= false;
			}
		}
    return succeed;
}

</script>
</body>


</html:html>
