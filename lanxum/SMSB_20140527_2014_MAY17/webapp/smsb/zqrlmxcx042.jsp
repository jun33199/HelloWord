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
<script language="JavaScript">
//�������������õ��ִ�
<%
    com.ttsoft.bjtax.smsb.zqwh.ZqwhHelper zHelper = new com.ttsoft.bjtax.smsb.zqwh.ZqwhHelper();
    com.ttsoft.bjtax.smsb.zqwh.ZqwhHelper zHelper2 = new com.ttsoft.bjtax.smsb.zqwh.ZqwhHelper();
    java.util.List szList = zHelper.getSzList();
    java.util.List djzclxList = zHelper2.getDjzclxList();
    java.util.List monthList = zHelper.getMonthList();
    
    pageContext.setAttribute("szList",szList);
    pageContext.setAttribute("djzclxList",djzclxList);
    pageContext.setAttribute("monthList",monthList);
%>


</script>

<link href="../css/text.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="./include/header.jsp"%>

<html:form action="/webapp/smsb/zqrlmxcxAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="tempNd" />
<html:hidden property="tempDjzclx" />
<html:hidden property="tempSz" />
<html:hidden property="tempZqlx" />
<html:hidden property="tempZqqsrq" />
<html:hidden property="tempMonth" />

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
               <td nowrap class="2-td2-t-left" width="15%">����ά�����</td>
               <td nowrap class="2-td2-t-left" width="25%"><div align="left"><html:text property="headNd" size="10" onkeydown="if(window.event.keyCode==13) event.keyCode=9;" /></div></td>
               <td nowrap class="2-td2-t-left" width="15%">��������</td>
               <td nowrap class="2-td2-t-center" width="45%"><div align="left">				
					<ttsoft:define id="zqlxItems" codekey="ZQWH_ZQLX"/>
					<html:select property="headZqlx" onkeydown="if(window.event.keyCode==13) event.keyCode=9;" >
	                	<html:option value="*">ȫ��</html:option>
	                	<html:options collection="zqlxItems" property="value" labelProperty="label"/>
	              	</html:select></div>
					</td>
              </tr>
              <tr >
               <td nowrap class="2-td2-t-left" width="15%">˰��</td>
               <td nowrap class="2-td2-t-left" width="25%"><div align="left"><html:select property="headSz" onkeydown="if(window.event.keyCode==13) event.keyCode=9;">
               		<html:option value="*">ȫ��</html:option>
                    <html:options collection="szList" property="value" labelProperty="label"/>
                    </html:select></div></td>
               <td nowrap class="2-td2-t-left" width="15%">�Ǽ�ע������</td>
               <td nowrap class="2-td2-t-center" width="45%"><div align="left">
					<ttsoft:define id="djzclxItems" codekey="ZQWH_DJZCLX"/>
					<html:select property="headDjzclx" onkeydown="if(window.event.keyCode==13) event.keyCode=9;" >
	                	<html:option value="*">ȫ��</html:option>
	                	<html:options collection="djzclxItems" property="value" labelProperty="label"/>
	              	</html:select></div>
					</td>
              </tr>
              <tr >
               <td nowrap class="2-td2-t-left" width="15%">�·�</td>
               <td nowrap class="2-td2-t-left" width="25%"><div align="left"><html:select property="headMonth" onkeydown="if(window.event.keyCode==13) event.keyCode=9;">
                    <html:options collection="monthList" property="value" labelProperty="label"/>
                    </html:select></div></td>
               <td nowrap class="2-td2-t-left" width="15%">&nbsp;</td>
               <td nowrap class="2-td2-t-center" width="45%">&nbsp;</td>
              </tr>
              <tr>
               <td colspan="4" class="2-td2-t-center" ><div align="right"><input type="image" accesskey="q" tabIndex="-1" onClick="befQuery('Query');return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" style="cursor:hand">&nbsp;&nbsp;</div></td>
              </tr>
             </table>
             
            <br>    
              <table id="SzzqwhTable" width="89%"  class="table-99" cellspacing="0">
                <tr>
                  <td width="10%"  class="2-td1-left">˰��</td>
                  <td width="8%"  class="2-td1-left">˰�ִ���</td>
                  <td width="18%" class="2-td1-left">������������</td>
                  <td width="16%"  class="2-td1-left">�Ǽ�ע������</td>
                  <td width="10%" class="2-td1-left">���ڿ�ʼʱ��</td>
                  <td width="10%" class="2-td1-left">���ڽ���ʱ��</td>
                  <td width="10%" class="2-td1-left">������ʼʱ��</td>
                  <td width="10%" class="2-td1-left">��������ʱ��</td>
                  <td width="5%" class="2-td1-left">&nbsp;</td>
                  <td width="5%" class="2-td1-center">&nbsp;</td>
                </tr>
            <bean:define id="items" name="zqrlmxcxForm" property="dataList"/>
            <logic:iterate id="item" name="items" indexId="index">
              <tr> 
                  <td width="10%"  class="2-td2-left"><ttsoft:write name="item" property="szsmmc"/></td>
                  <td width="8%"  class="2-td2-left"><ttsoft:write name="item" property="szsmdm"/></td>
                  <td width="18%" class="2-td2-left"><ttsoft:write name="item" property="zqlxmc"/></td>
                  <td width="16%"  class="2-td2-left"><ttsoft:write name="item" property="djzclxmc"/></td>
                  <td width="10%" class="2-td2-left"><ttsoft:write name="item" property="zqqsrq"/></td>
                  <td width="10%" class="2-td2-left"><ttsoft:write name="item" property="zqzzrq"/></td>
                  <td width="10%" class="2-td2-left"><ttsoft:write name="item" property="zqssrqq"/></td>
                  <td width="10%" class="2-td2-left"><ttsoft:write name="item" property="zqssrqz"/></td>
                <td width="5%" class="2-td2-left"><div align="center" ><a href="javascript:updateFunc('<ttsoft:write name="item" property="nd"/>','<ttsoft:write name="item" property="szsmdm"/>','<ttsoft:write name="item" property="zqlxdm"/>','<ttsoft:write name="item" property="djzclxdm"/>','<ttsoft:write name="item" property="zqqsrq"/>')">�޸�</a></div></td>
                <td width="5%" class="2-td2-center"><div align="center" ><a href="javascript:del('<ttsoft:write name="item" property="nd"/>','<ttsoft:write name="item" property="szsmdm"/>','<ttsoft:write name="item" property="zqlxdm"/>','<ttsoft:write name="item" property="djzclxdm"/>','<ttsoft:write name="item" property="zqqsrq"/>')">ɾ��</a></div></td>
              </tr>
              
             </logic:iterate>
              </table>
            </div>
            <br>
            <div align="center">
              &nbsp;&nbsp; <input type="image" accesskey="f" src="<%=static_contextpath%>images/fh-f1.jpg" alt="����" name="Image2" onclick='doSubmitForm("Return");return false' border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/fh-f2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/fh-f1.jpg';">
              <!--&nbsp;&nbsp; <input type="image" accesskey="c" src="<%=static_contextpath%>images/tc-c1.jpg" alt="�˳�" name="Impage3" onClick="tuichu();return false;" border="0" id="Image3"  style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" id="tc1"></div>-->
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

function befQuery(actionType){
	if(!isFullDate1(document.forms[0].headNd.value+"0101",0,"1","")) {
		alert("��Ȳ��Ϸ���");	
		document.forms[0].headNd.select();
		return false;
	}
	else {
		doSubmitForm(actionType);
		return false;
	}
}

//����
function updateFunc(nd,sz,zqlx,dj,qsrq){

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

//ɾ���ύ
function del(nd,sz,zqlx,dj,qsrq){
	document.forms[0].tempNd.value=nd;
	document.forms[0].tempSz.value=sz;
	document.forms[0].tempDjzclx.value=dj;
	document.forms[0].tempZqlx.value=zqlx;
	document.forms[0].tempZqqsrq.value=qsrq;
	doSubmitForm("Delete");
}


function fnOnLoad(){
   document.forms[0].headNd.focus();
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
