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
%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<title>¼��ӡ��˰�������</title>
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/function.js"></script>
<style type="text/css">
<!--
@import url(../css/text.css);
-->
</style>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()" >
<%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/yhsgmlrAction.do" method="POST">
<input type=hidden value="Show" name="actionType">
<html:hidden property="dsjsjdm" />
<html:hidden property="lrrq" />
<html:hidden property="dsdwmc" />
<html:hidden property="sjly" />
<html:hidden property="swjgzzjgdm" />
<html:hidden property="fsdm" />
<html:hidden property="lrr" />


<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">¼��ӡ��˰�������</td>
        </tr>
        <tr>
          <td class="1-td2"><br>
            <table cellspacing=0 class="table-99">
							<tr class="black9">
								<td>
                  <div align="left">&nbsp;&nbsp;����ڣ�
                    <html:text property="cjsj" size="12"/>
                  </div>
                </td>
								<td><div align="right">��λ�������Ԫ&nbsp;&nbsp;</div></td>
							</tr>
						</table>
						<br>
						<table cellspacing=0 class="table-99" onkeydown="if(window.event.keyCode==13) window.event.keyCode=9">
							<tr>
								<td width="20%" class="3-td1-left"><div align="right">������λ(��)����&nbsp;&nbsp;</div></td>
								<td colspan="3" class="3-td1-centen" colspan="3">
									<div align="left">&nbsp;&nbsp;
										<input type="radio" name="ghxz" checked onClick="change()">��λ
										<input type="radio" name="ghxz" onClick="change()">����
									</div>
								</td>
							</tr>

							<tr id="dw" style="display:block">
								<td class="2-td2-left"><div align="right">������λ���������&nbsp;&nbsp;</div></td>
								<td class="2-td2-left"><div align="left"> &nbsp;&nbsp;
										<html:text property="jsjdm" size="8" onchange="doGetQymcSubmit();return false;" onkeydown='if(window.event.keyCode==13) { event.keyCode=9;}' /></div></td>
								<td class="2-td2-left"><div align="right">������λ����&nbsp;&nbsp;</div></td>
								<td class="2-td2-center"><div align="left">&nbsp;&nbsp;
										<html:text property="ghdwmc" size="30" /></div></td>
							</tr>

							<tr id="gr1" style="display:block">
								<td class="2-td2-left"><div align="right">����&nbsp;&nbsp;</div></td>
								<td class="2-td2-left"><div align="left"> &nbsp;&nbsp;
                    <ttsoft:select property="gjdqdm" codekey="GJDQ"/><br>
                    <ttsoft:define id="code1" codekey="GJDQ"/></div></td>
								<td class="2-td2-left"><div align="right">����������&nbsp;&nbsp;</div></td>
								<td class="2-td2-center"><div align="left">&nbsp;&nbsp;
										<html:text property="ghrxm" size="30"/></div></td>
							</tr>
							<tr id="gr2" style="display:block">
								<td class="2-td2-left"><div align="right">֤������&nbsp;&nbsp;</div></td>
								<td class="2-td2-left"><div align="left"> &nbsp;&nbsp;
                    <ttsoft:select property="zjlxdm" codekey="ZJLX"/><br>
                    <ttsoft:define id="code2" codekey="ZJLX"/></div></td>
								<td class="2-td2-left"><div align="right">֤������&nbsp;&nbsp;</div></td>
								<td class="2-td2-center"><div align="left">&nbsp;&nbsp;
										<html:text property="zjhm" size="30"/></div></td>
							</tr>
						</table>
						<br>
						<table cellspacing=0 class="table-99" onkeydown="if(window.event.keyCode==13) window.event.keyCode=9">
							<tr>
								<td class="2-td1-left" align="center" >
									<span  style='font-family:����;"Times New Roman"'>���</span></td>
								<td valign=top class="2-td1-left">
									<span style='font-family:����;"Times New Roman"'>˰Ʊ��ֵ</span></td>
								<td valign=top class="2-td1-left">
								<span style='font-family:����;"Times New Roman"'>��Ʊ����</span></td>
								<td valign=top class="2-td1-center">
								<span style='font-family:����;"Times New Roman"'>���</span></td>
							</tr>
              <bean:define id="yhslist" name="yhsgmlrForm" property="dataList"/>
              <% int yhsIndex = 1;%>
              <logic:iterate indexId="index" name="yhslist" id="itemMap">
              <bean:define id="item" name="itemMap"/>
              <tr id="yhsRow">
                 <input type="hidden" name="spmzdm" value='<ttsoft:write name="item" property="pzzldm"/>'>
                 <input type="hidden" name="spmzje" value='<ttsoft:write name="item" property="mz"/>'>
                 <td nowrap class="2-td2-left"><%=yhsIndex%></td>
                 <td nowrap class="2-td2-left"><ttsoft:write name="item" property="pzzlmc"/></td>
                 <td nowrap class="2-td2-left"><input type="text" name="gpsl" size="8" maxlength="7" class="inputalignright" onchange="countJe('<%=yhsIndex++%>')"></td>
                 <td nowrap class="2-td2-center"><input type="text" name="je" size="12" class="inputnoborder" style="color:#3C5564" value="0.00" readonly tabIndex="-1"></td>
              </tr>
              </logic:iterate>

              <tr>
                <td class="2-td2-left"> �ϼ�</td>
                <td class="2-td2-left" colspan="2">&nbsp; </td>
                <td class="2-td2-center"><html:text property="hjje" value="0.00" size="12" styleClass="inputnoborder" style="color:#3C5564" readonly="true" tabindex="-1" /></td>
              </tr>
            </table>&nbsp;

            <table width="150" border="0" cellpadding="0" cellspacing="0" class="table-99">
              <tr>
                <div align="center">
									<input type="image" accesskey="s" src="<%=static_contextpath%>images/bc-s1.jpg" alt="����" id="baocun" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s1.jpg',1)" onclick="doSave();return false;" style="cursor:hand">
									&nbsp;&nbsp;&nbsp;&nbsp;
								<%--	<img src="<%=static_contextpath%>images/dayin1.jpg" alt="��ӡ" id="dayin" onMouseOver="MM_swapImage('dayin','','<%=static_contextpath%>images/dayin2.jpg',1)" onMouseOut="MM_swapImage('dayin','','<%=static_contextpath%>images/dayin1.jpg',1)" onclick="" style="cursor:hand">
									&nbsp;&nbsp;&nbsp;&nbsp;--%>
									<input type="image" accesskey="u" src="<%=static_contextpath%>images/qc-u1.jpg" alt="���" id="qingchu" onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)" onMouseOut="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u1.jpg',1)" onClick="ConfirmDelete();return false;" style="cursor:hand">
									&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="image" accesskey="c" onClick="tuichu();return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand">

                </div>
              </tr>
            </table>
            <br>
          </td>
        </tr>
      </table>
      <br><!-- </td>
	</tr>
</table> -->
</html:form>

<%@ include file="./include/footer.jsp"%>
</td>
	</tr>
</table>

</body>
<script language="JavaScript">
function change()
{
	var dw = document.all("dw");
	var gr1 = document.all("gr1");
	var gr2 = document.all("gr2");
	if(document.all.ghxz[1].checked==true){
		dw.style.display="none";
		document.all.jsjdm.disabled=true;
    document.all.ghdwmc.disabled=true;
		gr1.style.display="block";
		document.all.gjdqdm.disabled=false;
    document.all.ghrxm.disabled=false;
		gr2.style.display="block";
		document.all.zjlxdm.disabled=false;
    document.all.zjhm.disabled=false;
	}
	else {
		dw.style.display="block";
		document.all.jsjdm.disabled=false;
    document.all.ghdwmc.disabled=false;
		gr1.style.display="none";
		document.all.gjdqdm.disabled=true;
    document.all.ghrxm.disabled=true;
		gr2.style.display="none";
		document.all.zjlxdm.disabled=true;
    document.all.zjhm.disabled=true;
	}
}
change();

//�õ���ҵ����
function doGetQymcSubmit(){

  strJsjdm = document.forms[0].jsjdm.value+ "";

  if(strJsjdm!="")
    doSubmitForm('Reader');
  else
    alert("��������Ҫ��ѯ�� *���۵�λ���������* ��");

//  strJsjdm = document.forms[0].jsjdm.value+ "";
//  if( trim(strJsjdm).length!=8 && trim(strJsjdm).length!=7 && trim(strJsjdm).length!=0 ) {
//    document.forms[0].ghdwmc.value="";
//    alert("����������ʽΪ8λ����")
//    return false;
//  }
//  if(trim(strJsjdm).length==0){
//    document.forms[0].ghdwmc.value="";
//    return false;
//  }
//  if(doSubmitForm('Reader')==false){
//    return false;
//  }

  //return false;
}

function ConfirmDelete() {
  if(confirm("ȷ���Ƿ������ǰ���ݣ�")){
    var yhsrow=document.all("yhsRow");
    if(yhsrow){//����ӡ��˰Ʊ������
      if(yhsrow.length) {//ӡ��˰Ʊ��ֻһ��
        var lengths = yhsrow.length;
        for(var i=0;i<lengths;i++) {
          yhsrow[i].all("gpsl").value = "";
          yhsrow[i].all("je").value = formatNumber(0);
        }
      }
      else{//ӡ��˰Ʊֻ��һ��
        yhsrow.all("gpsl").value = "";
        yhsrow.all("je").value = formatNumber(0);
      }
      document.all("hjje").value = formatNumber(0);
    }
  }
}
function countJe(lineNo) {
  var yhsrow=document.all("yhsRow");
  if(yhsrow.length) {
    yhsrow = yhsrow[lineNo-1];
  }
  if(!trim(yhsrow.all("gpsl").value+"")=="" && !fnIsIntNum(yhsrow.all("gpsl").value)) {
    alert("*��Ʊ����* ��������������");
    yhsrow.all("gpsl").value = "";
    yhsrow.all("je").value = formatNumber(0);
  }
  else {
    yhsrow.all("je").value = formatNumber(parseFloat(yhsrow.all("spmzje").value)*parseInt(yhsrow.all("gpsl").value));
  }
  countHjje();
}

function countHjje() {
  var yhsrow=document.all("yhsRow");
  var temp = 0;
  if(yhsrow.length) {//ӡ��˰Ʊ��ֻһ��
    var lengths = yhsrow.length;
    for(var i=0;i<lengths;i++) {
      temp = parseFloat(temp) + parseFloat(document.all("yhsRow")[i].all("je").value);
    }
  }else {//ӡ��˰Ʊֻ��һ��
    temp = parseFloat(document.all("yhsRow").all("je").value);
  }
  document.all("hjje").value = formatNumber(temp);
}

var actionType = '<bean:write name="yhsgmlrForm" property="actionType"/>';
if(actionType=="Reader" || actionType=="Save"){
	if(document.all.jsjdm.value!="") {
    document.all.ghdwmc.readOnly=true;
		document.all.ghdwmc.className="txtInput";
  }else {
    document.all.ghdwmc.readOnly=false;
		document.all.ghdwmc.className="";
  }
}
if(actionType=="Save") {
  if(document.all.ghrxm.value!="") {
    document.all.ghxz[1].checked=true;
    dw.style.display="none";
		document.all.jsjdm.disabled=true;
    document.all.ghdwmc.disabled=true;
		gr1.style.display="block";
		document.all.gjdqdm.disabled=false;
    document.all.ghrxm.disabled=false;
		gr2.style.display="block";
		document.all.zjlxdm.disabled=false;
    document.all.zjhm.disabled=false;
  }
}

function doSave() {

  //�����Check
  strCjsj =document.all.cjsj.value;
  if(isFullDate(strCjsj,0,"NotNull",false)==false){
    alert("����ڸ�ʽ����ȷ��");
    return false;
  }

  if(document.all.ghxz[0].checked==true) {
    if(document.all.ghdwmc.value=="") {
      alert("*������λ����* ����Ϊ�գ�");
      return false;
    }
  }
  else {
    if(document.all.ghrxm.value=="") {
      alert("*����������* ����Ϊ�գ�");
      return false;
    }
    //���Ӷ�֤�����ͺ�֤�������У�飡��������������������������������������������������������������
    strGjdqdm = document.all.gjdqdm.value;//����
    strZjlxdm = document.all.zjlxdm.value;//֤������
    if(strGjdqdm=="CHN" && strZjlxdm=="02"){ //�й����֤
      if(checkIdentityCard(document.all.zjhm.value,null)==false){
        return false;
      }
    }
  }
  doSubmitForm('Save');
}
function fnOnLoad()
{
  document.forms[0].jsjdm.focus();
}


</script>
</html:html>
