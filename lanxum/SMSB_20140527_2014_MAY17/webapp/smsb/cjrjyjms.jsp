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
	<title>���òм��˾�ҵ��ҵӪҵ˰����˰�걨��</title>
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
								���òм��˾�ҵ��ҵӪҵ˰����˰�걨��
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
												��ȷ�ϼ��������
											</div>
										</td>
										<td class="2-td2-t-left" colspan="3" width="30%">
											<div align="left">
												 <html:text property="jsjdm" size="20" maxlength="8"/><font color="#FF0000">&nbsp*</font>
											</div>
										</td>
										
										<td class="2-td2-t-center" width="35%">
										<div align="left">
											&nbsp;&nbsp;<input type="image" accesskey="q" tabIndex="-1" onClick="doQuery(); return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/queren2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="ȷ��" src="<%=static_contextpath%>images/queren1.jpg" width="79" height="22" id="chaxun" style="cursor:hand">
											&nbsp;&nbsp;<input type="image" accesskey="c" tabIndex="-1" onClick="tuichu(); return false;" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"	onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor: hand">
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
												˰������ʱ�䣺
												<html:text property="skssrq" maxlength="6" size="12" />&nbsp;&nbsp;<input type="image" accesskey="q" tabIndex="-1" onClick="doQuery(); return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="ȷ��" src="<%=static_contextpath%>images/chaxun1.jpg" width="79" height="22" id="chaxun" style="cursor:hand">
											</div>
										</td>
										<td align="center">
											<div align="right">
												��λ:Ԫ���Ƿ�
											</div>
										</td>
								</table>
								<table class="table-99" cellspacing="0">

									<tr class="black9">
										<td class="2-td2-t-left" width="20%" height="20" nowrap>
											<div align="right">
												��λ����(����)
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
												��˰��ʶ���(˰��Ǽ�֤��)
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
												ע���ַ
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
												��λ����
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
												ְ��������
											</div>
										</td>
										<td class="2-td2-left">
											<div align="left">
												<html:text property="zgzrs" maxlength="6" size="23" />
											</div>
										</td>
										<td class="2-td2-left" width="100" height="20">
											<div align="center">
												���òм�ְ������
											</div>
										</td>
										<td class="2-td2-left">
											<div align="left">
												<html:text property="cjrzgrs" maxlength="6" size="15" />
											</div>
										</td>

										<td class="2-td2-left" width="100" height="20">
											<div align="right">
												�м���ְ��ռְ���������ı���
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
												��Ӫ��Χ
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
											��Ŀ
										</td>
										<td width="20%" class="2-td2-left">
											����
										</td>
										<td width="20%" class="2-td2-center">
											���
										</td>
									</tr>
									<tr>
										<td width="20%" class="2-td2-left" colspan="2">
											����Ӫҵ˰Ӧ˰����
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
											����Ӧ��Ӫҵ˰˰��
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
											���У������ܱ��Ż����ߵ�˰��
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
											����Ӧ����Ӫҵ˰�޶�
										</td>
										<td width="20%" class="2-td2-left">
											4=35000/12*���òм�ְ������
										</td>
										<td width="20%" class="2-td2-center">
											<div align="center">
												<html:text property="byyjzyysxe" size="25" maxlength="15" tabIndex="-1" />
											</div>
										</td>
									</tr>
									<tr>
										<td width="20%" class="2-td2-left" colspan="2">
											����δ����Ӫҵ˰�޶�
										</td>
										<td width="20%" class="2-td2-left">
											5=����9
										</td>
										<td width="20%" class="2-td2-center">
											<div align="center">
												<html:text property="syyjzyysxe" maxlength="15" size="25" />
											</div>
										</td>
									</tr>
									<tr>
										<td width="20%" class="2-td2-left" colspan="2">
											���¿ɼ���Ӫҵ˰�޶�
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
											����ʵ�ʼ���Ӫҵ˰˰��
										</td>
										<td width="20%" class="2-td2-left">
											7(��3>=6��Ϊ6������Ϊ3)
										</td>
										<td width="20%" class="2-td2-center">
											<div align="center">
												<html:text property="bysjjzyysye" maxlength="15" size="25" />
											</div>
										</td>
									</tr>
									<tr>
										<td width="20%" class="2-td2-left" colspan="2">
											���¼���Ӫҵ˰��Ӧ��Ӫҵ˰˰��
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
											����δ����Ӫҵ˰�޶�
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
												¼������
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
										src="<%=static_contextpath%>images/bc-s1.jpg" alt="����"
										width="79" height="22" id="baocun" style="cursor: hand">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="image" accesskey="u" tabIndex="-1"
										onClick="ql();return false; "
										onMouseOver="MM_swapImage('qingchu','',' <%=static_contextpath%>images/qc-u2.jpg ',1)"
										onMouseOut="MM_swapImgRestore()"
										src=" <%=static_contextpath%>images/qc-u1.jpg " alt="���"
										width="79" height="22" id="qingchu" style="cursor: hand">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="image" accesskey="c" tabIndex="-1"
										onClick="tuichu(); return false;"
										onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1"
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
											<strong><font color="#0000FF">ע �� �� ��</font> </strong>
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
											&nbsp;&nbsp;1�������ܲ�˰[2007]92���ļ���һ���涨��������Ӫҵ˰����˰�ˣ�Ӧ��ÿ�����˺�10���������ܵ�˰���ر��ͱ���
											<br>
											&nbsp;&nbsp;2������"��λ����"������д������ҵ��ä�˰�Ħ���������ƻ�����������λ��
											<br>
											&nbsp;&nbsp;3������"���òм�ְ��������"����д��˰��ʵ�ʰ��õķ��ϲ�˰[2007]
											92���ļ��涨�Ĳм�ְ��������
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
        alert("��������벻�����ǿգ�");
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
        alert("��������벻�����ǿգ�");
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

// ��ʾ��Ϣ
var warnMessage = "";
// ׷�ӵ���ϸ��ʾ��Ϣ
var alertStr="";

// ���������Ϣ��ʽ
function checkInput() {

	if(trim(document.forms[0].jsjdm.value) == "") {
		warnMessage += "*��������벻��Ϊ��\n";
	} else if(!/^[A-Za-z0-9]+$/.test(trim(document.forms[0].jsjdm.value))) {
		warnMessage += "*���������ֻ�ܰ�����ĸ������\n";
	}
	if(trim(document.forms[0].skssrq.value) == "") {
		warnMessage += "\n*˰������ʱ�䲻��Ϊ��\n";
	} else if(!/^\d{6}$/.test(trim(document.forms[0].skssrq.value))) {
		warnMessage += "\n*˰������ʱ���ʽΪYYYYMM\n";
	} else if(!/^[0-9]+$/.test(trim(document.forms[0].skssrq.value))){
	    warnMessage += "\n*˰������ʱ���ʽΪYYYYMM\n";
	}
	
	if(trim(document.forms[0].zgzrs.value) == "") {
		warnMessage += "\n*ְ������������Ϊ��\n";
	} else if(!isDigit(trim(document.forms[0].zgzrs.value))) {
		warnMessage += "\n*ְ��������Ϊ����\n"
	}
	
	if(trim(document.forms[0].cjrzgrs.value) == "") {
		warnMessage += "\n*���òм�ְ����������Ϊ��\n";
	} else if(!isDigit(trim(document.forms[0].cjrzgrs.value))) {
		warnMessage += "\n*���òм�ְ������Ϊ����\n"
	}
	
	if(trim(document.forms[0].cjrybl.value) == "") {
		warnMessage += "\n*�м���ְ��ռְ���������ı�������Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].cjrybl, 0, 100, "", 6, 3)) {
		warnMessage += "\n*�м���ְ��ռְ��������" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].ynyyssr.value) == "") {
		warnMessage += "\n*����Ӫҵ˰Ӧ˰���벻��Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].ynyyssr, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*����Ӫҵ˰Ӧ˰����" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].yjyysse.value) == "") {
		warnMessage += "\n*����Ӧ��Ӫҵ˰˰���Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].yjyysse, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*����Ӧ��Ӫҵ˰˰��" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].xsyhzzse.value) == "") {
		warnMessage += "\n*���У������ܱ��Ż����ߵ�˰���Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].xsyhzzse, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*���У������ܱ��Ż����ߵ�˰��" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].byyjzyysxe.value) == "") {
		warnMessage += "\n*����Ӧ����Ӫҵ˰�޶��Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].byyjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*����Ӧ����Ӫҵ˰�޶�" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].syyjzyysxe.value) == "") {
		warnMessage += "\n*����δ����Ӫҵ˰�޶��Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].syyjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*����δ����Ӫҵ˰�޶�" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bykjzyysxe.value) == "") {
		warnMessage += "\n*���¿ɼ���Ӫҵ˰�޶��Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].bykjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*���¿ɼ���Ӫҵ˰�޶�" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bysjjzyysye.value) == "") {
		warnMessage += "\n*����ʵ�ʼ���Ӫҵ˰˰���Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].bysjjzyysye, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*����ʵ�ʼ���Ӫҵ˰˰��" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bysjyesse.value) == "") {
		warnMessage += "\n*���¼���Ӫҵ˰��Ӧ��Ӫҵ˰˰���Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].bysjyesse, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*���¼���Ӫҵ˰��Ӧ��Ӫҵ˰˰��" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bymjzyysxe.value) == "") {
		warnMessage += "\n*����δ����Ӫҵ˰�޶��Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].bymjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*����δ����Ӫҵ˰�޶�" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].lrrq.value) == "") {
		warnMessage += "\n*¼�����ڲ���Ϊ��";
	} else if(!isDate(document.forms[0].lrrq,"")) {
		warnMessage += "\n*¼�����ڸ�ʽΪYYYYMMDD";
	}
}

// ����Ƿ������ʽҪ��
function checkEquation() {

	if(parseFloat(trim(document.forms[0].byyjzyysxe.value)) != (35000/12*(parseFloat(trim(document.forms[0].cjrzgrs.value)))).toFixed(2)) {
		warnMessage += "\n*����Ӧ����Ӫҵ˰�޶� = 35000/12*���òм�ְ������ \n";
	}
	
	<%--
	if(parseFloat(trim(document.forms[0].syyjzyysxe.value)) != ) {
		warnMessage += "\n*����δ����Ӫҵ˰�޶� = ����δ����Ӫҵ˰�޶�(����)\n";
	}
	--%>
	
	if(parseFloat(trim(document.forms[0].bykjzyysxe.value)) != (parseFloat(trim(document.forms[0].byyjzyysxe.value)) + parseFloat(trim(document.forms[0].syyjzyysxe.value))).toFixed(2)) {
		warnMessage += "\n*���¿ɼ���Ӫҵ˰�޶� = ����Ӧ����Ӫҵ˰�޶� + ����δ����Ӫҵ˰�޶�\n";
	}
	
	if(parseFloat(trim(document.forms[0].xsyhzzse.value)) >= parseFloat(trim(document.forms[0].bykjzyysxe.value))) {
		if(parseFloat(trim(document.forms[0].bysjjzyysye.value)) != parseFloat(trim(document.forms[0].bykjzyysxe.value))) {
			warnMessage += "\n*����ʵ�ʼ���Ӫҵ˰˰�� = ���¿ɼ���Ӫҵ˰�޶�\n";
		}
	} else {
		if(parseFloat(trim(document.forms[0].bysjjzyysye.value)) != parseFloat(trim(document.forms[0].xsyhzzse.value))) {
			warnMessage += "\n*����ʵ�ʼ���Ӫҵ˰˰�� = ���У������ܱ��Ż����ߵ�˰��\n";
		}
	}
	
	if(parseFloat(trim(document.forms[0].bysjyesse.value)) != (parseFloat(trim(document.forms[0].yjyysse.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2)) {
		warnMessage += "\n*���¼���Ӫҵ˰��Ӧ��Ӫҵ˰˰�� = ����Ӧ��Ӫҵ˰˰�� - ����ʵ�ʼ���Ӫҵ˰˰��\n";
	}
	
	if(parseFloat(trim(document.forms[0].bymjzyysxe.value)) != (parseFloat(trim(document.forms[0].bykjzyysxe.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2)) {
		warnMessage += "\n*����δ����Ӫҵ˰�޶� = ���¿ɼ���Ӫҵ˰�޶� - ����ʵ�ʼ���Ӫҵ˰˰��\n";
	}
	
	//alert("-----------------------------------------");
	//alert("35000/12*���òм�ְ������ = " + (35000/12*(parseFloat(trim(document.forms[0].cjrzgrs.value)))).toFixed(2));
	//alert("����Ӧ����Ӫҵ˰�޶� + ����δ����Ӫҵ˰�޶� = " + (parseFloat(trim(document.forms[0].byyjzyysxe.value)) + parseFloat(trim(document.forms[0].syyjzyysxe.value))).toFixed(2));
	//alert("����Ӧ��Ӫҵ˰˰�� - ����ʵ�ʼ���Ӫҵ˰˰�� = " + (parseFloat(trim(document.forms[0].yjyysse.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2));
	//alert("���¿ɼ���Ӫҵ˰�޶� - ����ʵ�ʼ���Ӫҵ˰˰�� = " + (parseFloat(trim(document.forms[0].bykjzyysxe.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2));
	//alert("-----------------------------------------");
}

// ����
function doSave() {
	// ��������ʽ
	checkInput();
	if(warnMessage == "") {
		// ����ʽУ��
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

//function  : ��������ַ����Ƿ����������
//parameters: DigitString: String,������ַ�����ֵ
//			  totalLength: int, ���ֵĳ������ƣ�������С���㣩
//			  decimalLength: int��С���ĳ�������
//            totalLength-decimalLength �������ֵ�����
//return    : false: �ַ����а�������������ַ�
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
		//��С�����ֳ���Ϊ0ʱ ��С��������Ϊnull
		if(decimalLength=="0"){
			decimalLength=null;
			or_decimalLength=0;
			}
		//��С�����ֳ��Ȳ�Ϊnull ���û��С������С������00
		if(decimalLength!=null){if(DigitString.indexOf(".")<0) DigitString+=".00";}
    if (decimalLength!=null)
    {   //С����Ϊ��
        re = re+"\\.[\\d]{1,"+ decimalLength +"}"
    }

    if (totalLength == null)
    {   //δ�趨�ܳ���
        re= "\\d*"+re;
    }
    else
    {   //�趨�ܳ���
        //��С������Ϊ�յ�����£������ж����ֵĳ���
        var intergerLength = totalLength;
        if (decimalLength!=null)
        {   //��С�����ֲ�Ϊ���жϣ�������λ����С����λ���Ƿ���ȷ
            intergerLength = totalLength-decimalLength;
        }
        //re="[\\d]{0,"+ intergerLength +"}"+re;
		re="[\\d]{1,"+ intergerLength +"}"+re;//�������С���㿪ʼ
    }
    re = new RegExp("^(-){0,1}"+re+"$","g");//���ֻ� modify 2003/11/11 �����жϸ�����

    //���ַ���Ϊ�ա�λ�����ԡ�����ƥ�������ʱ
		//var meetRequest=true;
    if(re.exec(DigitString) == null)
    {
			  alertStr+="��ֵ����Ϊ���� "
				if(totalLength!=null) alertStr+="�ܳ���Ϊ"+totalLength+"λ ";
				if(havedecimalLength) {
					alertStr+="С�����ĳ���Ϊ"+or_decimalLength+"λ ";
				}
        succeed=false;
    }
		//��ָ���ַ�����Χʱ �ַ�������ָ����Χ��
		if(maxValue!=null){
			if(parseFloat(DigitString)>parseFloat(maxValue)){
				alertStr+="���ֲ�����"+maxValue+" ";
				succeed= false;
			}
		}
		if(minValue!=null){
			if(parseFloat(DigitString)<parseFloat(minValue)){
				alertStr+="���ֲ�С��"+minValue+" ";
				succeed= false;
			}
		}
    return succeed;
}

</script>
</body>


</html:html>
