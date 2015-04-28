<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html>
<head>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
	java.util.List qxjList = (java.util.List)session.getAttribute("GGHSB_YHDK_QXLIST");
	pageContext.setAttribute("qxjList",qxjList);
	java.util.List nyList = (java.util.List)session.getAttribute("GGHSB_YHDK_NYLIST");
	pageContext.setAttribute("nyList",nyList);
%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<title>����ί�д�����Ϣ</title>
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/function.js"></script>
<style type="text/css">
<!--
@import url(../css/text.css);
-->
</style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "" >
<%@ include file="./include/header.jsp"%>
<div id="floater">
<table width="250" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="194" height="20"><img src="<%=static_contextpath%>images/wait.gif" border="0" width="194" height="20"></td>
    <td width="56" height="20" align="center" valign="middle" background="<%=static_contextpath%>images/wait2.gif" class="black9" id="face">&nbsp;</td>
  </tr>
</table>
</div>
<html:form action="/webapp/smsb/yhdkwjscscLwAction.do" method="POST">
<html:hidden property="yscNY"/>
<html:hidden property="qxdmOfBccb"/>
<html:hidden property="qxdmOfNxs"/>
<input type=hidden value="Show" name="actionType">
<table  width="760" height='430' border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td valign="top">
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">����ί�д�����Ϣ</td>
        </tr>
        <tr>
          <td class="1-td2">
						<br>
						<table cellspacing=0 class="table-99">
							<tr>
								<td width="84%" class="2-td2-t-left">
									���ؾ֣�	<html:select property="qxdm" onChange="">
														<html:options collection="qxjList" property="value" labelProperty="label"/>
													</html:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

									���ڣ� <html:select property="ny" onChange="">
														<html:options collection="nyList" property="value" labelProperty="label"/>
													</html:select>&nbsp;&nbsp;
								</td>
								<td width="16%" class="2-td2-t-left">
									<a style="cursor:hand" onClick="doSave();" tabindex="-1">
										<img src="<%=static_contextpath%>images/gghsc01.jpg" value="����ί�д�����Ϣ" name="chaxun1a" width="125" height="22" border="0" id="chaxun1a" alt="����ί�д�����Ϣ" onMouseOver="MM_swapImage('chaxun1a','','<%=static_contextpath%>images/gghsc02.jpg',1)" onMouseOut="MM_swapImgRestore()">
									</a>
								</td>
								<td width="16%" class="2-td2-t-center">
									<a style="cursor:hand" onClick="doSend();" tabindex="-1">
										<img src="<%=static_contextpath%>images/tijiao1.jpg" value="����ί�д�����Ϣ" name="send1a" width="125" height="22" border="0" id="send1a" alt="����ί�д�����Ϣ" onMouseOver="MM_swapImage('send1a','','<%=static_contextpath%>images/tijiao2.jpg',1)" onMouseOut="MM_swapImgRestore()">
									</a>
								</td>
							</tr>
						</table>
						<br>

            <table border="0" cellpadding="0" cellspacing="0" class="table-99">
              <tr>
                <div align="center">
									<a style="cursor:hand" onClick="doQuit();" tabindex="-1">
										<img src="<%=static_contextpath%>images/tuichu1.jpg" value="�˳�" name="tc1" width="79" height="22" border="0" id="tc1" alt="�˳�" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()">
									</a>
                </div>
              </tr>
            </table>
						<br>

						<table cellspacing="0"  class="table-99">
							<tr>
								<td width="20%" class="2-td2-t-left">������Ա</td>
								<td width="25%" class="2-td2-t-left">
									<div align="left"><html:text property="lrr" readonly="true" styleClass="txtInput" size="18"/></div>
								</td>
								<td width="20%" class="2-td2-t-left">��������</td>
								<td width="25%" class="2-td2-t-center">
									<div align="left"><html:text property="lrrq"  readonly="true" styleClass="txtInput" size="18"/></div>
								</td>
							</tr>
						</table>
						<br>

						<table width="99%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td><hr width="100%" size="1" ></td>
								<td width="99" align="center" class="black9">
									<strong><font color="#0000FF">ע �� �� ��</font></strong>
								</td>
								<td><hr width="100%" size="1" ></td>
							</tr>
						</table>

						<table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
							<tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
								<td >
									<br>&nbsp;&nbsp;ѡ������ί�д�����Ϣ�����������ĵȺ������ɣ���Ҫ�˳���ģ����߹ر��������<br><br>
								</td>
							</tr>
						</table>
						<br>

          </td>
        </tr>
      </table>
    <br>
	</html:form>
	</td>
	</tr>
</table>
<%@ include file="./include/footer.jsp"%>
</body>
<script language="JavaScript">
var flag = false;
function doSave(){
	if(flag)
		return false;
	var nyValue = document.all.ny.value;
	var yscNyValue = document.all.yscNY.value;
	if(nyValue=="") {
		alert("���¿ۿ������ѷ��������У������������ɣ�");
		return false;
	}
	else if(yscNyValue.indexOf(nyValue) >= 0 && !window.confirm("���µĿۿ������Ѿ����ɹ�����ȷ���Ƿ��������ɣ�")){
		return false;
	}
	if(window.confirm("�������ɸ������пۿ����ݣ���ȷ��")) {
		document.all.actionType.value='Save';
		document.forms[0].submit();
		jhDisabled();
		flag=true;
	}
}


function doSend(){
	if(window.confirm("�������͸������пۿ����ݣ���ȷ��")) {
		document.all.actionType.value="Send";
		document.forms[0].submit();
		jhDisabled();
		flag=true;
	}
}

function doQuit(){
	if(flag)
		return false;
	tuichu();
}
</script>
</html:html>
