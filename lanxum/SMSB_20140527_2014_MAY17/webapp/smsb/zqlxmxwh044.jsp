<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

<title>����������ϸ��Ϣά��</title>

<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/list.js"></script>
<link href="../css/text.css" rel="stylesheet" type="text/css">

</head>
<body onLoad="setListFunc()">
<%@ include file="./include/header.jsp"%>

<html:form action="/webapp/smsb/zqlxmxwhAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="headZqlxdm" />

<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0"  class='black9'>
  <tr>
    <td valign="top"> <br>
      <table  align="center" cellspacing="0" class="table-99" border="0">
        <tr class="black9">
          <td class="1-td1">����������ϸ��Ϣά��</td>
        </tr>
        <tr>
          <td valign="top" class="1-td2">
            <br>
            <div align="center">
             <table class="table-99" border="0" cellpadding="0" cellspacing="0">
              <tr>
               <td nowrap class="2-td1-left" width="20%">������������</td>
               <td nowrap class="2-td2-t-left" width="30%">&nbsp;<bean:write name="zqlxmxwhForm" property="zqlxmc" /></td>
               <td nowrap class="2-td1-left" width="20%">�������ʹ���</td>
               <td nowrap class="2-td2-t-center" width="30%">&nbsp;<bean:write name="zqlxmxwhForm" property="zqlxdm" /></td>
              </tr>
              <tr>
               <td nowrap class="2-td1-left" width="20%">�������</td>
               <td nowrap class="2-td2-left" width="30%">&nbsp;<select name="jglxdm" >
<option value="0">��</option>
<option value="1">��</option>
<option value="2">����</option>
<option value="4">��</option>
<option value="12">��</option>
</select></td>
               <td nowrap class="2-td1-left" width="20%">˰��������</td>
               <td nowrap class="2-td2-center" width="30%">&nbsp;<select name="skssq" >
               <option value="0">����</option>
               <option value="1">����</option>
               <option value="2">�����ۼ�</option> 
               <option value="3">�����ۼ�</option> 
               </select></td>
              </tr>
              <tr>
               <td nowrap class="2-td1-left" width="20%">���ڿ�ʼ����</td>
               <td nowrap class="2-td2-left" width="30%">&nbsp;<html:text property="zqksrq" size="15" /></td>
               <td nowrap class="2-td1-left" width="20%">��������</td>
               <td nowrap class="2-td2-center" width="30%">&nbsp;<html:text property="zqts" size="15" /></td>
              </tr>
              <tr>
               <td nowrap class="2-td1-left" width="20%">��������</td>
               <td nowrap class="2-td2-left" width="30%">&nbsp;<html:text property="zqzq" size="15" /></td>
               <td nowrap class="2-td1-left" width="20%">¼����</td>
               <td nowrap class="2-td2-center" width="30%">&nbsp;<bean:write name="zqlxmxwhForm" property="lrr" /></td>
              </tr>
             </table>
             
            </div>
            <br>
            <div align="center">
              &nbsp;&nbsp;<input type="image" accesskey="s" tabIndex="-1" onclick="befSave('Save');return false;" style="cursor:hand"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="22" border="0">
              &nbsp;&nbsp;<input type="image" accesskey="f" tabIndex="-1" onclick="doSubmitForm('Back');return false;" style="cursor:hand"  onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="fh1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" border="0">
              &nbsp;&nbsp;<input type="image" accesskey="c" tabIndex="-1" onClick="doSubmitForm('Return');return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" border="0">
              </div>
            <br> <br>
             </td>
        </tr>
      </table>
      <br></td>
    </tr>
</table>
</html:form>
<%@ include file="./include/footer.jsp"%>
</body>
<script language="javascript">

//����ԭ�������б��ֵ
function setListFunc(){
	document.zqlxmxwhForm.jglxdm.value="<bean:write name="zqlxmxwhForm" property="jglxdm" />"
	document.zqlxmxwhForm.skssq.value="<bean:write name="zqlxmxwhForm" property="skssq" />"
}

//����
function updateFunc(nd,sz,zqlx,dj,qsrq){
	if (dm!="" && rq!=""){
		document.forms[0].tempNd.value=nd;
		document.forms[0].tempSz.value=sz;
		document.forms[0].tempDjzclx.value=dj;
		document.forms[0].tempZqlx.value=zqlx;
		document.forms[0].tempZqqsrq.value=qsrq;
		
		//if (confirm("�ò�����ҳ����һ�е�����,�Ҳ����Զ��ָ�,��ȷ��")){
			document.forms[0].target="";
			document.forms[0].actionType.value="Update";
			document.forms[0].submit();
		//}
	}
}

function befSave(actType){
	var strZqzq = document.forms[0].zqzq.value;
	var strZqksrq = document.forms[0].zqksrq.value;
	if(document.forms[0].zqts.value=="" || !chkNum(document.forms[0].zqts.value) || !fnIsIntNum(document.forms[0].zqts.value)) {
		alert("�����������Ϸ���");	
		document.forms[0].zqts.select();
		return false;
	}
	else if((!isNumber(strZqzq)) || (strZqzq.length!=12) ){
          alert("��������Ӧ����12λ�ʵ���0��1�ִ���");
          document.forms[0].zqzq.select();
          return false;
    }
	else if((!fnIsIntNum(strZqksrq)) || (strZqksrq.length>2) || parseInt(strZqksrq)>31){
          alert("���ڿ�ʼ����Ӧ����1-31�����֡�");
          document.forms[0].zqksrq.select();
          return false;
    }
	else {
		doSubmitForm(actType);
		return false;
	}
}

function chkNum(strValue){
	if (strValue.indexOf(".")>=0){
		return false;
	}
	else {
		return true;
	}
}

function fnOnLoad(){
   document.forms[0].zqts.focus();
}

fnOnLoad();

function isFullDate1(strDate,dateKind,empty,isThrow){
	var year,mon,days;
	var err = "";
	if(dateKind==0){//����8λ������
		err = "";

		if(strDate.length==0){
			  if(empty!=null){
					err="���ڱ��벻Ϊ��!\n";
				}
		}else{
			if(strDate.length!=8){
				err="���ڸ�ʽ����ȷ��������8λ����!\n";
			}else{
				var strYear = strDate.substr(0,4);
				var strMonth = strDate.substr(4,2);
				var strDay = strDate.substr(6,2);
				var objDate = new Date(strMonth+'-'+strDay+'-'+strYear);
				if (isNaN(objDate)){
					err="���ڸ�ʽ����ȷ!\n"
				}
				if(strYear*1<1900) {
					err="���ڸ�ʽ����ȷ!\n"
				}
				if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900)) {
					err="���ڸ�ʽ����ȷ!\n"
				}
				if (strMonth*1 != objDate.getMonth()*1+1){
					err="���ڸ�ʽ����ȷ!\n"
				}
				if (strDay*1 != objDate.getDate()*1) {
					err="���ڸ�ʽ����ȷ!\n"
				} //don't call getDay function.
			}
		}
		/*if(err!=""){
			if(isThrow){
				alert(alertStr);
			}
			return false;
		}
		return true;*/
	}else if(dateKind==1){//4λ��
		err = "";
		if((strDate.charAt(0)=="0") || strDate.length!=4 || (!isDigit(strDate))){
			err = "���ǺϷ�����ݣ�";
		}
		//alert(err);
	}

	if(err!=""){
		if(isThrow){
			alert(err);
		}
		return false;
	}
	return true;
}
</script>

</html:html>
