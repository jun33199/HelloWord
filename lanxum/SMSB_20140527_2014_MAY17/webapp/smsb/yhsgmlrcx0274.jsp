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
<title>¼��ӡ��˰�����������</title>
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<style type="text/css">
<!--
@import url(../css/text.css);
-->
</style>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
<%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/yhsgmlrcxAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="viewId" />
<html:hidden property="pgNum" />
<html:hidden property="length" />
<html:hidden property="pgSum" />

<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
  <tr> <!-- InstanceBeginEditable name="02" -->
    <td> <br>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">¼��ӡ��˰�����������</td>
        </tr>
        <tr>
          <td class="1-td2">&nbsp;&nbsp; <br>

		<table cellspacing=0 class="table-99" onkeydown="if(window.event.keyCode==13) window.event.keyCode=9">
			<tr>
				<td width="20%" class="3-td1-left"><div align="right">¼������&nbsp;&nbsp;</div></td>
				<td class="3-td1-centen" colspan="3"><div align="left"> &nbsp;&nbsp;
					<html:text property="lrqsrq" size="10" maxlength="8" />
					��
					<html:text property="lrjzrq" size="10" maxlength="8" />
					</div>
				</td>
			</tr>
			<tr>
				<td width="20%" class="2-td2-left"><div align="right">¼����&nbsp;&nbsp;</div></td>
				<td colspan="3" class="2-td2-center" ><div align="left">&nbsp;&nbsp;
					<html:text property="lrr" size="10" maxlength="10" readonly="true" />
					</div>
				</td>
			</tr>
			<tr id="gr2" style="display:block">
				<td class="2-td2-left"><div align="right">����ƾ֤��&nbsp;&nbsp;</div></td>
				<td class="2-td2-left"><div align="left">&nbsp;&nbsp;
						<html:text property="xspzh" size="30" /></div>
				</td><br>
				<td class="2-td2-left"><div align="right">���ʺϼƽ��&nbsp;&nbsp;</div></td>
				<td class="2-td2-center"><div align="left">&nbsp;&nbsp;
						<html:text property="dbjehj" size="30" /></div></td>
			</tr>
			<tr>
				<td width="20%" class="2-td2-left"><div align="right">������λ(��)����&nbsp;&nbsp;</div></td>
				<td colspan="3" class="2-td2-center" >
					<div align="left">&nbsp;&nbsp;
						<html:radio property="ghxz" value="1" onclick="change()" />��λ
						<html:radio property="ghxz" value="2" onclick="change()" />����
					</div>
				</td>
			</tr>
			<tr id="dw" style="display:block">
				<td class="2-td2-left"><div align="right">������λ���������&nbsp;&nbsp;</div></td>
				<td class="2-td2-left"><div align="left"> &nbsp;&nbsp;
						<html:text property="ghdwjsjdm" size="30" /></div></td>
				<td class="2-td2-left"><div align="right">������λ����&nbsp;&nbsp;</div></td>
				<td class="2-td2-center"><div align="left">&nbsp;&nbsp;
						<html:text property="ghdwmc" size="30" /></div></td>
			<tr id="gr" style="display:block">
				<td class="2-td2-left"><div align="right">֤������&nbsp;&nbsp;</div></td>
				<td class="2-td2-left"><div align="left">&nbsp;&nbsp;
						<html:text property="zjhm" size="30" /></div></td>
				<td class="2-td2-left"><div align="right">����������&nbsp;&nbsp;</div></td>
				<td class="2-td2-center"><div align="left">&nbsp;&nbsp;
						<html:text property="ghrmc" size="30" /></div></td>

			</tr>
	                <tr>
	                	<td colspan="4" class="2-td2-center"><div align="right"><input name="I2" type="image" value="��ѯ"  onclick='doSelectSubmit();return false;' onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/chaxun1.jpg" width="79" height="22" id="chaxun">&nbsp;&nbsp&nbsp;&nbsp</div>
	                	</td>
	                </tr>
	                <tr>
		            </table>
					<br>
					 <table border="1" cellpadding="0" cellspacing="0" class="table-99" height="1" width="99%">
		              <tr>
		                <td width="5%" class="2-td1-left">ɾ��</td>
		                <td width="13%" class="2-td1-left" height="1">����ƾ֤��</td>
		                <td width="12%" class="2-td1-left" height="1">��˰��λ(��)����</td>
		                <td width="16%" class="2-td1-left" height="1">��˰��λ(��)����</td>
		                <td width="13%" class="2-td1-left" height="1">�ϼƽ��</td>
		                <td width="12%" class="2-td1-left" height="1">¼����</td>
		                <td width="9%" class="2-td1-left" height="1">¼������</td>
		                <td width="12%" class="2-td1-center" height="1">&nbsp; </td>
		              </tr>
                              <bean:define id="ylist" name="yhsgmlrcxForm" property="dataList"/>
                              <%
                                 com.ttsoft.bjtax.smsb.yhsgl.web.YhsgmlrcxForm form = (com.ttsoft.bjtax.smsb.yhsgl.web.YhsgmlrcxForm)request.getAttribute("yhsgmlrcxForm");
                                 int pgLength = form.getLength();
                                 int pgSum = form.getPgSum();
                                 int pgNum = form.getPgNum();
                                 int start = pgLength*(pgNum-1);
                                 int Length = String.valueOf(pgSum).length();
                              %>
                              <logic:iterate id="items" name="ylist" indexId="index" >
                              <bean:define id="item" name="items"/>
		                <tr>
		                  <td width="5%" class="2-td2-left"><input type="checkbox" name="deleteCheckbox" value="<ttsoft:write name="item" property="checkboxId"/>"></td>
		                  <td width="13%" class="2-td2-left" height="23"><ttsoft:write name="item" property="xspzh"/>&nbsp</td>
		                  <td width="12%" class="2-td2-left" height="23"><ttsoft:write name="item" property="dwdm"/>&nbsp</td>
		                  <td width="16%" class="2-td2-left" height="23"><ttsoft:write name="item" property="dwmc"/>&nbsp</td>
		                  <td width="13%" class="2-td2-left" height="23"><ttsoft:write name="item" property="hjje"/>&nbsp</td>
		                  <td width="12%" class="2-td2-left" height="23"><ttsoft:write name="item" property="lrr"/>&nbsp</td>
		                  <td width="9%" class="2-td2-left" height="23"><ttsoft:write name="item" property="cjrq1"/>&nbsp</td>
		                  <td width="12%" class="2-td2-center" height="23"><input name="xiugai" type="image" value="<ttsoft:write name="item" property="viewId"/>"  onClick="doViewSubmit(this.value);return false;" onMouseOver="MM_swapImage('xiugai<ttsoft:write name="item" property="checkboxId"/>','','<%=static_contextpath%>images/xjxx-b2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�꾡��Ϣ" src="<%=static_contextpath%>images/xjxx-b1.jpg" width="79" height="22" id="xiugai<ttsoft:write name="item" property="checkboxId"/>"></td>
		                </tr>
                               </logic:iterate>

		            </table>
						<br>
		            <table  class="table-99">
		              <tr class="black9">
		                <td align="right">��(<input type="text" name="gotoPG" value="<%=pgNum%>" size="<%=Length%>" onchange="doGotoPG()">/<%=pgSum%>)ҳ
		                  <%if(pgNum > 1 ) {%>
		                    <img src="<%=static_contextpath%>images/diyiye1.jpg" alt="��һҳ" id="first" onMouseOver="MM_swapImage('first','','<%=static_contextpath%>images/diyiye2.jpg',1)" onMouseOut="MM_swapImage('first','','<%=static_contextpath%>images/diyiye1.jpg',1)" onclick="goToPage('1');return false;" style="cursor:hand">
		                    <img src="<%=static_contextpath%>images/shangyiye1.jpg" alt="��һҳ" id="backward" onMouseOver="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye1.jpg',1)" onclick="goToPage('<%=pgNum-1%>');return false;" style="cursor:hand">
		                  <%}%>
		                  <%if(pgNum < pgSum) {%>
		                    <img src="<%=static_contextpath%>images/xaiyiye1.jpg" alt="��һҳ" id="forward" onMouseOver="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye1.jpg',1)" onclick="goToPage('<%=pgNum+1%>');return false;" style="cursor:hand">
		                    <img src="<%=static_contextpath%>images/zuimoye1.jpg" alt="��ĩҳ" id="last" onMouseOver="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye1.jpg',1)" onclick="goToPage('<%=pgSum%>');return false;" style="cursor:hand">
		                  <%}%>
		                </td>
		              </tr>
		            </table>
                            <br>
		            <table border="0" width="100%">
		              <tr>
		                <td width="22%">&nbsp; </td>
                                <td width="20%"><input name="shanchu1" type="image" accesskey="d" value="ɾ��" alt="ɾ��"  onClick="doDeleteSubmit();return false;" onMouseOver="MM_swapImage('shanchu1','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()"  src="<%=static_contextpath%>images/sc-d1.jpg" width="79" height="22" id="shachu1"></td>
                                <td width="21%"><input name="shanchu" type="image" accesskey="x" value="ȫ��ɾ��" alt="ȫ��ɾ��" onClick="doDeleteAllSubmit();return false;" onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)" onMouseOut="MM_swapImgRestore()"  src="<%=static_contextpath%>images/qbsc-x1.jpg" width="79" height="22" id="shachu"></td>
                                <td width="8%"><input name="tuichu" type="image" accesskey="c" value="�˳�" alt="�˳�" onClick="tuichu();return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22">
		                </td>
		                <td width="29%">&nbsp;</td>
		              </tr>
		            </table>
	                </tr>
		</table>



            <br>
            <br>
          </td>
        </tr>
      </table>
      <br>
    </html:form>
    <%@ include file="./include/footer.jsp"%>
    </td>
    <!-- InstanceEndEditable --></tr>
</table>
</body>
<!-- InstanceEnd --></html:html>
<script language="JavaScript">
function change()
{
	var dw = document.all("dw");
	var gr = document.all("gr");
	if(document.all.ghxz[1].checked==true){
		dw.style.display="none";
		document.all.ghdwjsjdm.disabled=true;
                document.all.ghdwmc.disabled=true;
		document.all.ghdwjsjdm.value="";
                document.all.ghdwmc.value="";
		gr.style.display="block";
                document.all.zjhm.disabled=false;
                document.all.ghrmc.disabled=false;
	}
	else {
		dw.style.display="block";
		document.all.ghdwjsjdm.disabled=false;
                document.all.ghdwmc.disabled=false;
		gr.style.display="none";
                document.all.zjhm.disabled=true;
                document.all.ghrmc.disabled=true;
                document.all.zjhm.value="";
                document.all.ghrmc.value="";
	}
}
change();
//�����ύ
function doSelectSubmit()
{
  //¼����ʼ����Check
  strLrqsrq =document.all.lrqsrq.value;
  if(isFullDate(strLrqsrq,0,null,false)==false){
    alert("¼����ʼ���ڸ�ʽ����ȷ��");
    return false;
  }
  //¼���������Check
  strLrjzrq =document.all.lrjzrq.value;
  if(isFullDate(strLrjzrq,0,null,false)==false){
    alert("¼���ֹ���ڸ�ʽ����ȷ��");
    return false;
  }
  //¼����ʼ���ں�¼��������ڹ�ϵCheck
  if( compare(strLrjzrq,strLrqsrq)<0 ){
    alert("¼����ʼ����ӦС�����ڽ������ڡ�");
    return false;
  }
  document.all.pgNum.value = 1;
  if(doSubmitForm("Query")==false){
    return false;
  }
}
function compare(value1,value2){
  if(parseInt(value1)<parseInt(value2)){
    return -1;
  }else if(parseInt(value1)==parseInt(value2)){
    return 0;
  }else{
    return 1;
  }
}
function goToPage(pgNum) {
  document.all.pgNum.value = pgNum;
  doSubmitForm('Query');
}

function doGotoPG() {
  var gotoPG = document.all.gotoPG;
  var thisPG = document.all.pgNum;
  if(!fnIsIntNum(gotoPG.value)){
    gotoPG.value=thisPG.value;
    return;
  }
  if(parseInt(gotoPG.value)>parseInt(document.all.pgSum.value)){
    gotoPG.value=thisPG.value;
    return;
   }
  if(gotoPG.value!=thisPG.value) {
    thisPG.value = gotoPG.value;
    doSubmitForm('Query');
  }
}

function doDeleteAllSubmit(){
  var checkbox =document.all("deleteCheckBox");
  if(checkbox){
  }else{
    alert("û�п�ɾ��������");
    return false;
  }
  if(window.confirm("�ò������޸����ݿ��е�����,�Ҳ����Զ��ָ�,��ȷ��")){
    doSubmitForm("DeleteAll");
  }
}

//checkboxes check
function checkBoxesCheck(checkboxesName)
{
  var checkbox =document.all(checkboxesName);
  if(checkbox){
    if(checkbox.length){
      for(i=0;i<checkbox.length;i++){
        if(checkbox[i].checked == true){
          return true;
        }
      }
      alert("������ѡ��һ��");
      return false;
    }else{
      if(checkbox.checked == true){
        return true;
      }else{
        alert("������ѡ��һ��");
        return false;
      }
    }
  }
  alert("û�п�ɾ��������");
  return false;
}

//ɾ���ύ
function doDeleteSubmit()
{
  if(checkBoxesCheck("deleteCheckBox")==false){
    return false;
  }
  doSubmitForm("Delete");
}

//�鿴�ύ
function doViewSubmit(thisIndex)
{
  document.forms[0].viewId.value = thisIndex;
  doSubmitForm("View");
}
</script>