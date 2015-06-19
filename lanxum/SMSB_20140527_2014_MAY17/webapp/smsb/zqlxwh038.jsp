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

<title>��������ά��</title>

<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/list.js">
</script>

<link href="../css/text.css" rel="stylesheet" type="text/css">

</head>
<%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/zqlxwhAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="strNow" />
<html:hidden property="tempZqlxdm" />

<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
  <!--<tr><td class="2-td1-center">����ά�����&nbsp<html:text property="whnf" size="5" onkeydown="if(window.event.keyCode==13) return doShowHandle()" /></td>
  </tr>-->
  <tr>
    <td valign="top"> <br>
      <table align="center" cellspacing="0" class="table-99" border="0">
        <tr class="black9">
          <td class="1-td1">��������ά��</td>
        </tr>
        <tr>
          <td valign="top" class="1-td2">
            <div align="center">
              <table width="100%" id="displayTable" class="table-99" cellspacing="0">
                <tr id="tableTitle" bgcolor="#C5CAD3">
                  <td class="2-td1-left">&nbsp;</td>
                  <td class="2-td1-left">������������</td>
                  <td class="2-td1-left">������<br>�ʹ���</td>
                  <td class="2-td1-left">���<br>����</td>
                  <td class="2-td1-left">˰��������</td>
                  <td class="2-td1-left">���ڿ�ʼ����</td>
                  <td class="2-td1-left">��������</td>
                  <td class="2-td1-left">��������</td>
                  <td class="2-td1-left">¼����</td>
                  <td class="2-td1-left">¼������</td>
                  <td class="2-td1-left">ͣ������</td>
                  <td class="2-td1-center">&nbsp;</td>
                </tr>
               <bean:define id="zqlist" name="zqlxwhForm" property="dataList"/>
               <logic:iterate id="items" name="zqlist" indexId="index">
                <bean:define id="item" name="items"/>
                <tr id='normal<ttsoft:write name="item" property="tyrq"/>'>
                 <% Object tyrq = ((Map)item).get("tyrq"); %>
                 <%if(tyrq==null){%>
                  <td class="2-td2-left"><input type="checkbox" name="tyCheckbox" value="<ttsoft:write name="item" property="zqlxdm"/>"></td>
                 <%}else{%>
                  <td class="2-td2-left">&nbsp</td>
                 <%}%>
                  <td class="2-td2-left"><ttsoft:write name="item" property="zqlxmc"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="zqlxdm"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="jglxdm"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="skssq"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="zqksrq"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="zqts"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="zqzq"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="lrr"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="lrrq"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="tyrq"/>&nbsp</td>
                  <td class="2-td2-center"><div align="center"><a href="javascript:updateFunc('<ttsoft:write name="item" property="zqlxdm"/>')">�޸�</a></div></td>
                </tr>
               </logic:iterate>
              </table>
	      <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <tr>
                  <td width="5%">&nbsp;</td>
                  <td width="8%"  valign="bottom">
                    <div align="right"  class="black9">��ʾ����</div></td>
                  <td width="40%">
                    <div align="left"class="black9">
                      <input type="radio" name="radiobutton" value="all" onClick="doChangeDisplay(this.value)" checked>
                      ȫ����������
                      <input type="radio" name="radiobutton" value="normal" onClick="doChangeDisplay(this.value)">
                      ������������
                      <input type="radio" name="radiobutton" value="stopUsing" onClick="doChangeDisplay(this.value)">
                      ��ͣ����������</div></td>
                  <td width="9%"><input type="image" accesskey="a" src="<%=static_contextpath%>images/zj-a1.jpg" alt="����" name="Image2" onClick="addRow();return false;" border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/zj-a2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/zj-a1.jpg';"></td>
                  <td width="9%"><input type="image" accesskey="d" src="<%=static_contextpath%>images/ty-d1.jpg" alt="ͣ��" name="Image2" onClick='doUpdateSubmit();return false;' border="0" id="ty" onMouseOver="this.src ='<%=static_contextpath%>images/ty-d2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/ty-d1.jpg';"></td>
                  <td width="9%"><input type="image" accesskey="s" name="I2" type="image" value="����"  onClick='doSaveSubmit();return false;' onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="22" id="baocun"></td>
		  <!--<td width="9%"><input type="image" accesskey="k" name="Submit42" value="������������" onClick="doCreateCalendarSubmit();return false;" onMouseOver="MM_swapImage('sczqrl','','<%=static_contextpath%>images/sczqrl-k2.jpg',1)" onMouseOut="MM_swapImgRestore()"  src="<%=static_contextpath%>images/sczqrl-k1.jpg" width="116" height="22"  id="sczqrl" alt="������������" ></td>-->
                  <td width="9%"><input type="image" accesskey="f" src="<%=static_contextpath%>images/fh-f1.jpg" alt="����" name="Image2" onClick='doSubmitForm("Return");return false;' border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/fh-f2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/fh-f1.jpg';"></a></td>
                  <td width="23%">&nbsp;</td>
                </tr>
              </table>

            </div>
            <div align="center">
              <p><font size="2">&nbsp; </font> &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
                &nbsp;&nbsp;</p>
<!--              <table width="99%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td> <hr width="100%" size="1" > </td>
                  <td width="99" align="center" class="black9"><strong><font color="#0000FF">ע
                    �� �� ��</font></strong></td>
                  <td> <hr width="100%" size="1" > </td>
                </tr>
              </table>
              <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
                <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                  <td height="47">&nbsp;&nbsp;&nbsp;1�������������Ʋ���Ϊ�գ��������ʹ����Զ����ɣ����ڿ�ʼ������ָ�ڵ��µĿ�ʼ���ڣ�����������һ�����ڵĳ��ȣ�����������12λ��1��0����ɵ����ִ���1�����������ڣ�0����û�С�<br>
                    &nbsp;&nbsp;&nbsp;2�����Ӳ�����ͣ�ò����ڵ��������������������ť�Ժ󱣴����ݿ⡣ </td>
				  </tr>
              </table>-->
              <p>&nbsp;</p>
            </div>
          </td>
        </tr>
      </table><br>

    </td>
    </tr>
</table>
</html:form>
<%@ include file="./include/footer.jsp"%>
<script language="javascript">

//����
function updateFunc(actionValue){
	document.forms[0].tempZqlxdm.value=actionValue;
	document.forms[0].target="";
	document.forms[0].actionType.value="Edit";
	document.forms[0].submit();
}

function insertCheck()
{
  var zqlxmc =document.all("zqlxmc");
  var zqlxdm =document.all("zqlxdm");
  var zqksrq =document.all("zqksrq");
  var zqts =document.all("zqts");
  var zqzq =document.all("zqzq");
  if(zqksrq){
    if(zqksrq.length){
      for( i=0;i<zqksrq.length;i++){
        strZqlxmc = zqlxmc[i].value + "";
        strZqlxdm = zqlxdm[i].value + "";
        strZqksrq = zqksrq[i].value;
        strZqts = zqts[i].value;
        strZqzq = zqzq[i].value + "";
        if(strZqlxmc.length<1){
          alert("�����ʽ����������" + (i+1) + "�������������Ʊ������롣");
          return false;
        }
        if(strZqlxdm.length<1){
          alert("�����ʽ����������" + (i+1) + "���������ʹ���������롣");
          return false;
        }
        if((!fnIsIntNum(strZqksrq)) || (strZqksrq.length>2) || parseInt(strZqksrq)>31){
          alert("�����ʽ����������" + (i+1) + "�����ڿ�ʼ����Ӧ����1-31�����֡�");
          return false;
        }
        if((!fnIsIntNum(strZqts))){
          alert("�����ʽ����������" + (i+1) + "����������Ӧ�������֡�");
          return false;
        }
        if( strZqzq.length < 1 ){
          //do nothing
        }else if((!isNumber(strZqzq)) || (strZqzq.length!=12) ){
          alert("�����ʽ����������" + (i+1) + "����������Ӧ����12λ�ʵ���0��1�ִ���");
          return false;
        }else{
          for( j=0;j<strZqzq.length;j++) {
            if(strZqzq.charAt(j)!="1"&&strZqzq.charAt(j)!="0"){
              alert("�����ʽ����������" + (i+1) + "����������Ӧ����12λ�ʵ���0��1�ִ���");
              return false;
            }
          }
          //����check
          if(isCircle(strZqzq)==false){
            alert("�����ʽ����������" + (i+1) + "����������Ӧ����12λ�ʵ���0��1�ִ���");
            return false;
          }
        }
      }
    }else{
      strZqlxmc = zqlxmc.value + "";
      strZqlxdm = zqlxdm.value + "";
      strZqksrq = zqksrq.value;
      strZqts = zqts.value;
      strZqzq = zqzq.value+"";
      if(strZqlxmc.length<1){
        alert("�����ʽ����������" + 1 + "�������������Ʊ������롣");
        return false;
      }
      if(strZqlxdm.length<1){
        alert("�����ʽ����������" + 1 + "���������ʹ���������롣");
        return false;
      }
      if((!fnIsIntNum(strZqksrq)) || (strZqksrq.length>2) || parseInt(strZqksrq)>31){
        alert("�����ʽ����������" + 1 + "�����ڿ�ʼ����Ӧ����1-31�����֡�");
        return false;
      }
      if((!fnIsIntNum(strZqts))){
        alert("�����ʽ����������" + 1 + "����������Ӧ�������֡�");
        return false;
      }
      if( strZqzq.length < 1 ){
          //do nothing
      }else if((!isNumber(strZqzq)) || (strZqzq.length!=12) ){
        alert("�����ʽ����������" + 1 + "����������Ӧ����12λ�ʵ���0��1�ִ���");
        return false;
      }else{
        for( j=0;j<strZqzq.length;j++) {
          if(strZqzq.charAt(j)!='1'&& strZqzq.charAt(j)!='0'){
            alert("�����ʽ����������" + 1 + "����������Ӧ����12λ�ʵ���0��1�ִ���");
            return false;
          }
        }
        //����check
        if(isCircle(strZqzq)==false){
          alert("�����ʽ����������" + 1 + "����������Ӧ����12λ�ʵ���0��1�ִ���");
          return false;
        }
      }
    }
  }
  return true;
}

//����check
function isCircle(strZqzq)
{
  //����ĸ�ʽ�������й��ɵ�
  haveOneflag = -1;
  standStep = 0;
  sumStep=0;
  trebleStrZqzq = strZqzq + strZqzq + strZqzq;
  for( j=0;j<trebleStrZqzq.length;j++) {
    if(trebleStrZqzq.charAt(j)=="1"){
      if(haveOneflag==-1){
        haveOneflag = 0;
      }else if(haveOneflag == 0){
        haveOneflag = 1;
      }else{
        haveOneflag = 2;
      }
      if(haveOneflag==1){
        standStep = sumStep;
      }else if(haveOneflag==2){
        if(sumStep!=standStep){
          return false;
        }
      }
      sumStep = 0;
    }else{
      sumStep = sumStep + 1;
    }
  }
  if(haveOneflag == -1){
        return false;
  }
  return true;

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

function doShowHandle(){
  //ά�����Check
  //strDate =document.all.whnf.value;
  //if(isFullDate(strDate,1,"Notempty",true)==false){
    //return false;
  //}
  return false;
}

//�����ύ
function doSaveSubmit()
{
  //ά�����Check
  //strDate =document.all.whnf.value;
  //if(isFullDate(strDate,1,"Notempty",true)==false){
    //return false;
  //}
  if(insertCheck()==false){
    return false;
  }
//  if(doSubmitForm("Save")==false){
//    return false;
//  }
  doSubmitForm("Save");
}
//ͣ���ύ
function doUpdateSubmit()
{
  if(checkBoxesCheck("tyCheckbox")==false){
    return false;
  }
//  if(doSubmitForm("Update")==false){
//    return false;
//  }
  doSubmitForm("Update");
}


//������������
function doCreateCalendarSubmit()
{
  //ά�����Check
  //strDate =document.all.whnf.value;
  //if(isFullDate(strDate,1,"Notempty",true)==false){
    //return false;
  //}

  if(window.confirm("�ò������޸����ݿ��е�����,�Ҳ����Զ��ָ�,��ȷ��")){
    document.forms[0].actionType.value = "CreateCalendar";
    document.forms[0].submit();
  }
}

//��ʾ��Ŀ�仯
function doChangeDisplay(strDisplayType){

  var rows=displayTable.rows;
  if(strDisplayType=="all"){
    for(var i=0;i<rows.length;i++){
       rows[i].style.display='inline';
    }
  }else if(strDisplayType=="normal"){
    for(var i=0;i<rows.length;i++){
      if( rows[i].id!="normal" && rows[i].id!="_rowid" ){
        rows[i].style.display='none';
      }else{
        rows[i].style.display='inline';
      }
    }
  }else if(strDisplayType=="stopUsing"){
    for(var i=0;i<rows.length;i++){
      if(rows[i].id=="normal"){
        rows[i].style.display='none';
      }else{
        rows[i].style.display='inline';
      }
    }
  }
  tableTitle.style.display="inline";
}
//����һ��
function addRow(){
        //�������
        var strJglxdm='<select name="jglxdm" ><option value="0">��</option>'
                     +'<option value="1">��</option><option value="2">����</option>'
                     +'<option value="4">��</option></option><option value="12">��</option></select>';
//	var today = new Date();
//	var strToday = today.getYear()+""+(today.getMonth()+1)+""+today.getDate();
        var strToday = <ttsoft:write name="zqlxwhForm" property="strNow" />;
	var tdClass = ["2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-center"];
	var tdAlign = ["left","left","left","left","left","left","left","left","left","left","left","left"];
	var tdContent = ['<a href="#" id="delIndex" onclick=delRow(this)>ɾ��</a>',
		'<input type="text" name="zqlxmc" size="25" value = "">&nbsp;',
		'<input type="text" name="zqlxdm" size="5" value = "">&nbsp;',
                strJglxdm,
		'<select name="skssq" ><option value="0">����</option><option value="1">����</option><option value="2">�����ۼ�</option><option value="3">�����ۼ�</option> </select>',
		'<div align="center">�� <input type="text" name="zqksrq" size="2" value = ""> ��</div>',
		'�� <input type="text" name="zqts" size="2" value = ""> ��',
		'<input type="text" name="zqzq" size="12" maxlength="12" value = "">&nbsp;',
		'<bean:write name="zqlxwhForm" property="lrr"/>',
		strToday,
		'&nbsp;','&nbsp;'];
	//tdContent[0] = document.forms[0].all.select.outerHTML;
	//var trNode = document.createElement("TR");
	var rowIndex = displayTable.rows.length;
	var trNode = displayTable.insertRow(rowIndex);

	trNode.id ="_rowid";
	for( ii=0 ; ii < tdClass.length ; ii++ ) {
			var tdNode = document.createElement("TD");
			//if(ii==0) tdNode.id = "sfgl_td1"
			tdNode.className =tdClass[ii];
			tdNode.align=tdAlign[ii];
			tdNode.innerHTML=tdContent[ii];//.toString().replace("%rowIndex%",rowIndex);
			trNode.appendChild(tdNode);
	}//end for
        //alert(trNode.outerHTML);
	//document.all.ROW_LIST.insertAdjacentElement("BeforeBegin" , trNode);
	//formattable();
}
function delRow(obj)
{
  if(confirm("ȷ��ɾ���˼�¼��")){
    getObjRow(obj).removeNode(true);
  }
}

function getObjRow(obj){
  obj=obj.parentElement;
  while(obj.tagName!="TR"){
    obj=obj.parentElement;
  }
  return obj;
}

//function fnOnLoad()
//{
   //document.forms[0].whnf.focus();
//}
//fnOnLoad();
</script>
</html:html>
