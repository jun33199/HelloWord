<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=gb2312" %>

<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>�ɿ���¼��</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="fnOnLoad();whenReload('SBJKMX_GTGSH')">
<%@ include file="./include/header.jsp"%>


<html:form action="/webapp/smsb/gtgshJkslrAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="lrr" />
<html:hidden property="jsjdm" />
<html:hidden property="gjbzhydm" />
<html:hidden property="djzclxdm" />
<html:hidden property="djzclxmc" />

<link href="css/beijing.css" rel="stylesheet" type="text/css">
      <TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9' height="300">
 <tr>
    <td valign="top" height="300"> <br>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1"  colspan="2">�ɿ���¼��</td>
        </tr>
        <tr>
          <td class="1-td2"  colspan="2"> <br>
            <table cellspacing="0" class="table-99" width="99%" height="27">
              <tr class="black9">
                <td align="center" nowrap  height="11">
        	        <div align="center">����ڣ�<bean:write name="gtgshJkslrForm" property="sbrq"/> <html:hidden property="sbrq"/>&nbsp;&nbsp;
        	        </div>
                </td>
                <td align="center" nowrap  height="11">
                	<div align="right">&nbsp;���ջ������ƣ�<bean:write name="gtgshJkslrForm" property="swjgzzjgmc"/><html:hidden property="swjgzzjgmc"/> <html:hidden property="swjgzzjgdm"/></div>
                </td>
              </tr>
            </table><br>

             <table class="table-99" border="0" cellpadding="0" cellspacing="0">
              <tr class="black9">
               <td nowrap class="2-td2-t-left">��˰�˼��������</td>
               <td nowrap class="2-td2-t-left"><html:text property="nsrjsjdm" onKeyDown="jsjdmQuery()" onchange="doSubmitForm('Query')" /></td>
               <td nowrap class="2-td2-t-left">��˰������ </td>
               <td nowrap class="2-td2-t-center"><html:text property="nsrmc" styleClass="inputnoborder"  onkeydown="if(event.keyCode==13) event.keyCode=9;" size="30" readonly="true"/></td>
              </tr><!--
              <tr class="black9">
               <td nowrap class="2-td2-t-left">�ɿ����</td>
               <td nowrap class="2-td2-t-left"><html:text property="jkpzh" styleClass="inputnoborder" size="30" readonly="true"/></td>
               <td nowrap class="2-td2-t-left">�ɿ���ϼƽ��</td>
               <td nowrap class="2-td2-t-center"><html:text property="jkshjje" styleClass="inputnoborder" size="30" readonly="true"/></td>
              </tr>-->

             </table>
             <br>
			<table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0" id="SBJKMX_GTGSH"  onkeydown=dokeydown(this.id,'szsmdm')  onmouseup='return dokeyUp(this.id)'>
			<ttsoft:smsbtable form="gtgshJkslrForm" property="dataList" tableId="SBJKMX_GTGSH" hasTitle="true"/>
			<DIV id=divSfTemp></DIV>
              <tr>
                <td nowrap class="2-td2-left">�ϼ�</td>
                <td nowrap class="2-td2-left" colspan="6">&nbsp;</td>
                <td nowrap class="2-td2-center"><html:text property="hjsjje" onkeydown="if(event.keyCode==13) event.keyCode=9;" size="15" styleClass="inbright" /></td>
              </tr>

             </table>
               <table border="0" width="100%">
              <tr>
                <td width="27%">&nbsp;</td>
                <td width="7%"><input type="image" accesskey="a" tabIndex="-1" onclick="befAdd();return false;" style="cursor:hand" onMouseOver="MM_swapImage('zj1','','<%=static_contextpath%>images/zj-a2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="zj1" src="<%=static_contextpath%>images/zj-a1.jpg" width="79" border="0" height="22"></td>
                <td width="8%"><input type="image" accesskey="d" tabIndex="-1" onclick="befDel();return false;" style="cursor:hand"  onMouseOver="MM_swapImage('sc1','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="ɾ��" id="sc1" src="<%=static_contextpath%>images/sc-d1.jpg" width="79" border="0" height="22"></td>
                <td width="8%"><input type="image" accesskey="s" tabIndex="-1" onclick="befSave('Save');return false;" style="cursor:hand" onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" border="0" height="22"></td>
                <td width="8%"><input type="image" accesskey="w" tabIndex="-1" onclick="befDismiss('Dismiss');return false;" style="cursor:hand" onMouseOver="MM_swapImage('ds1','','<%=static_contextpath%>images/whjks-w2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="ds1" src="<%=static_contextpath%>images/whjks-w1.jpg" width="110" border="0" height="22"></td>
                <td width="9%"><input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22"></td>
                <td width="33%">&nbsp;</td>
              </tr>
            </table>
			<br>
          </td>
        </tr>
      </table>

      <br>
    <!-- </td>
 </tr>
</table> -->
<div id=szsmdm_epodDateLayer style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" ><select id=sel onclick=selectClick("szsmdm","SBJKMX_GTGSH") onkeyup=selectMove("szsmdm","SBJKMX_GTGSH")  onfocusout=selectClick("szsmdm","SBJKMX_GTGSH") onkeydown=if(window.event.keyCode==13)selectClick("szsmdm","SBJKMX_GTGSH")></select></div>

<INPUT TYPE="hidden" NAME="szsmdm_focus">
</html:form>



<%@ include file="./include/footer.jsp"%>
</td>
 </tr>
</table>

</body>
<script language=JavaScript >
var SBJKMX_GTGSH_list=new DTable(SBJKMX_GTGSH,jsArr_SBJKMX_GTGSH);
SBJKMX_GTGSH_list.tableTail=1;
function jsjdmQuery(){
	//if(window.event.keyCode==13){
		//doSubmitForm('Query')
	//}
	if(event.keyCode==13) event.keyCode = 9;
	return false;
}

function befAdd(){
	if (document.gtgshJkslrForm.gjbzhydm.value!="" || document.gtgshJkslrForm.nsrmc.value!=""){
		tableid="SBJKMX_GTGSH";
    	var rows = eval(tableid).rows;
    	if (rows.length<6){
			addRow('SBJKMX_GTGSH',null,'szsmdm');
		}
	}
}

function befDel(){
	tableid="SBJKMX_GTGSH";
	var rows = eval(tableid).rows;
	//alert(rows.length+"<>hhhh");
	if (rows.length>2){
		deleteRow('SBJKMX_GTGSH',null,'szsmdm')
	}
}

function befDismiss(actType){
	if (document.gtgshJkslrForm.nsrjsjdm.value!=""){
		doSubmitForm(actType);
	}
}

function befSave(){
	if (document.gtgshJkslrForm.gjbzhydm.value!="" || document.gtgshJkslrForm.nsrmc.value!=""){
		tableid="SBJKMX_GTGSH";
    	var rows = eval(tableid).rows;
    	//alert(rows.length+"<>hhhh");
		if (rows.length>6) {
			alert("һ��¼�벻�ܶ���4����Ϣ��");
		}
		if (rows.length>2 && rows.length< 7){
			doSubmitCheck('Save');
		}
	}
}

//�����治�ɹ������ص�ʱ���������Ƕ�д����
function whenReload(tableid){
	var rows = eval(tableid).rows;
	//alert(rows.length+"<>hhhh");
	if (rows.length>2){
		for(var ii=1;ii<rows.length-1;ii++){
			var obj = rows[ii].all("kssl");
			var def_obj = rows[ii].all("szsmdm");
			var cnt_obj = rows[ii].all("sjse");
			//alert("11111111");
			var obj_value = def_obj.value;
			if(obj_value.indexOf("11")!=0 && obj_value.indexOf("88")!=0){
				obj.readOnly = true;
				obj.className = "inputnoborder";
				cnt_obj.focus();
			}
			if(obj_value.indexOf("11")==0 || obj_value.indexOf("88")==0){
				obj.className = "inputalignright";
			}
		}	
	}
}

function fnOnLoad(){
    document.forms[0].nsrjsjdm.focus();
}

//��鲢�ύ����Ҫ����걨���ڡ�˰����������
//��˰��Ŀ���롢˰���������ڡ���˰��������˰��ʵ�ʽ�˰��
function doSubmitCheck(ope){
	//����¼����������
	if(document.forms[0].nsrjsjdm.value==''|| document.forms[0].nsrmc.value=='') return false;
	//���ÿһ�е�ֵ�Ƿ�Ϸ�
	for(var ii=1;ii<SBJKMX_GTGSH.rows.length-1;ii++){		
		if(SBJKMX_GTGSH.rows[ii].all("skssksrq") && !isDate(SBJKMX_GTGSH.rows[ii].all("skssksrq"),false)) {
			alert("˰��������ʼ���ڲ��Ϸ���");		
			return false;
		}
		if(SBJKMX_GTGSH.rows[ii].all("skssjsrq") && !isDate(SBJKMX_GTGSH.rows[ii].all("skssjsrq"),false)) {
			alert("˰��������ֹ���ڲ��Ϸ���");		
			return false;
		}
		//��ʼ����ҪС�ڽ�ֹ����
		if(compare(SBJKMX_GTGSH.rows[ii].all("skssksrq"),SBJKMX_GTGSH.rows[ii].all("skssjsrq"))<0) {
			alert("��ʼ���ڱ���С�ڻ���ڽ�ֹ���ڣ�");
			return false;
		}
		//��˰������Ϊ��ʱ����Ϊ�Ϸ�������
		if(SBJKMX_GTGSH.rows[ii].all("kssl") &&SBJKMX_GTGSH.rows[ii].all("kssl").value!=null&& !isNum(SBJKMX_GTGSH.rows[ii].all("kssl"),0)) {
			alert("��˰������Ϊ��ʱ����Ϊ�Ϸ���������");		
			return false;
		}
		//if(SBJKMX_GTGSH.rows[ii].all("jsje") && !isNum(SBJKMX_GTGSH.rows[ii].all("jsje"),0,null,true)) {
			//alert("��˰���Ϸ���");		
			//return false;
		//}
		//if(SBJKMX_GTGSH.rows[ii].all("sjse") && !isNum(SBJKMX_GTGSH.rows[ii].all("sjse"),0,null,true)) {
			//alert("ʵ�ʽ�˰��Ϸ���");		
			//return false;
		//}
		var obj_name=SBJKMX_GTGSH.rows[ii].all("szsmdm");
		var obj_value = obj_name.value;
		if (obj_value.indexOf("11")!=0 && obj_value.indexOf("88")!=0) {
			if(SBJKMX_GTGSH.rows[ii].all("jsje") && !isNum(SBJKMX_GTGSH.rows[ii].all("jsje"),0,null,true)) {
				alert("��˰���Ϸ���");		
				return false;
			}
			if(SBJKMX_GTGSH.rows[ii].all("sjse") && !isNum(SBJKMX_GTGSH.rows[ii].all("sjse"),0,null,true)) {
				alert("ʵ�ʽ�˰��Ϸ���");		
				return false;
			}
		}
		//ѡ���˳��������˰��������¼��
		if (obj_value.indexOf("11")==0 || obj_value.indexOf("88")==0) {
			if(SBJKMX_GTGSH.rows[ii].all("kssl") && !isNum(SBJKMX_GTGSH.rows[ii].all("kssl"),0,null,true)) {
				alert("��˰�������Ϸ���");		
				return false;
			}
			//��˰��ʵ�ʽ�˰�Ϊ��ʱ������Ϊ�Ϸ�����
			if(SBJKMX_GTGSH.rows[ii].all("jsje") && SBJKMX_GTGSH.rows[ii].all("jsje").value!=null && !isNum(SBJKMX_GTGSH.rows[ii].all("jsje"),0)) {
				alert("��˰��Ϊ��ʱ����Ϊ�Ϸ������֣�");		
				return false;
			}
			if(SBJKMX_GTGSH.rows[ii].all("sjse") && SBJKMX_GTGSH.rows[ii].all("sjse").value!=null && !isNum(SBJKMX_GTGSH.rows[ii].all("sjse"),0)) {
				alert("ʵ�ʽ�˰�Ϊ��ʱ����Ϊ�Ϸ������֣�");		
				return false;
			}
		}
	}
	
	doSubmitForm(ope);
}

//�����б�ʹ�õ�js����
<ttsoft:write name="gtgshJkslrForm" property="scriptStr" filter="false"/>
//�Ƿ���ʵ����˰��־
isadditions = true;


//���¼��㸽��˰�ļ�˰����Ӧ�ɽ��
function resetFjs(){	
	tableid="SBJKMX_GTGSH";
    var rows = eval(tableid).rows;
	for(var ii=0;ii<rows.length;ii++){
		//�쿴ÿһ�еĸ���˰��־�����Ϊ2�Ǹ���˰�����¼��㸽��˰
		var obj = rows[ii].all("sffjs");
		if(!obj) continue;
		//alert("sffjs="+obj.value);
		//������Ļ���ҵ�������Ҫ���ݼ�˰�������㣬������ͨ����˰��ʵ�ɼ���
		var szsmStr = rows[ii].all("szsmdm").value;
		//ȡǰ��λ�������'53'��Ϊ�Ļ���ҵ�����
		szsmStr = szsmStr.substr(0,2);
		if(obj.value=="2" && szsmStr!='53') countFjs(rows,ii);

	}
	//���¼���ϵͳ�ϼ�
	computeSameSum("sjse","hjsjje","SBJKMX_GTGSH");

	hj();
}
function publicMethod(){
	//alert("publicmethod");
	resetFjs();
	//���㸽��˰Ϊ�Ļ���ҵ�����
	resetWhjssy()
}
//�����������
function checkSjseInput(obj){	
	var obj1 = eval(obj);
	//alert(obj1.value + isNum(obj1.value,0));
	//if(!isNum(obj1.value,0)){
	if(!isNum(obj1,0,null,false)){
		//obj1.value='0';
		return false;
	}
	return true;
}
//�������е��У����㸽��˰
//rowIndex��ʾ����˰����
function countFjs(obj,rowIndex){
	//alert("����˰��="+rowIndex);
	//�õ������ж���
	var rows = eval(obj);
	//�õ��ø���˰��sjse
	var sjsehj = getFjsSj(obj,rowIndex);	
	//����˰˰��
	var sl = rows[rowIndex].all("sl").value;
	//�踽��˰�ļ�˰���Ϊ��˰ʵ��˰��ĺϼ�
	rows[rowIndex].all("jsje").value = Math.round(parseFloat(sjsehj)*100)/100;
	//�踽��˰��ʵ��˰��Ϊ��˰���*��˰ʵ��˰��ϼ�
	//ʹ��Math.round����֤С�����ֻ����λС��
	rows[rowIndex].all("sjse").value = Math.round(parseFloat(sjsehj)*parseFloat(sl)*100)/100;
	
}
//���㸽��˰����˰��ʵ�ɺϼ�
function getFjsSj(obj,rowIndex){	
	//�õ������ж���
	var rows = eval(obj);
	//�õ��ø���˰��������˰
	var fSzsm = getFathers(rows[rowIndex].all("szsmdm").value);
	//ʵ�ʽ�˰��ϼ�
	var sjsehj =0;
	//�������˰�Ѿ���ӵ��б��ϼ�ʵ�ʽ�˰��
	var sss = ","+fSzsm.toString()+",";
	for(var ii=0;ii<rows.length;ii++){
		if(rows[ii].all("szsmdm")){
			//����ϸ������
			if(sss.indexOf(","+rows[ii].all("szsmdm").value+",")>=0 && rows[ii].all("sjse").value!=''){
				//�Ǹ���˰��˰��������ʵ�ɽ��
				//�ۼ�ʵ��˰��				
				sjsehj = parseFloat(sjsehj) + parseFloat(rows[ii].all("sjse").value);
			}
		}
	}	
	return sjsehj;
}

hjArray[0]=new Array("sjse","hjsjje","SBJKMX_GTGSH");
//���ϼ�������
function initHj(){	
	document.forms[0].hjsjje.value='0';
}
initHj();

//�����Ļ���ҵ����ѵļ�˰��ʵ��
//�Ļ���ҵ����ѵļ�˰���Ϊ��˰�ļ�˰���
function resetWhjssy(){
	tableid="SBJKMX_GTGSH";
    var rows = eval(tableid).rows;
	for(var ii=0;ii<rows.length;ii++){
		//�쿴ÿһ�еĸ���˰��־�����Ϊ2�Ǹ���˰�����¼��㸽��˰
		var obj = rows[ii].all("sffjs");
		if(!obj) continue;
		//alert("sffjs="+obj.value);
		//if(obj.value=="2") countFjs(rows,ii);
		//������Ļ���ҵ�������Ҫ���ݼ�˰�������㣬������ͨ����˰��ʵ�ɼ���
		var szsmStr = rows[ii].all("szsmdm").value;
		//ȡǰ��λ�������'53'��Ϊ�Ļ���ҵ�����
		szsmStr = szsmStr.substr(0,2);
		if(obj.value=="2" && szsmStr=='53') countWhjssy(rows,ii);

	}
	//���¼���ϵͳ�ϼ�
	computeSameSum("sjse","hjsjje","SBJKMX_GTGSH");

	hj();
}
//�����Ļ���ҵ����ѵļ�˰��ʵ��
function countWhjssy(obj,rowIndex){
	//�õ������ж���
	var rows = eval(obj);
	//�õ��ø���˰��sjse
	var jsjehj = getFjsJs(obj,rowIndex);	
	//����˰˰��
	var sl = rows[rowIndex].all("sl").value;
	//�踽��˰�ļ�˰���Ϊ��˰ʵ��˰��ĺϼ�
	rows[rowIndex].all("jsje").value = Math.round(parseFloat(jsjehj)*100)/100;
	//�踽��˰��ʵ��˰��Ϊ��˰���*��˰ʵ��˰��ϼ�
	//ʹ��Math.round����֤С�����ֻ����λС��
	rows[rowIndex].all("sjse").value = Math.round(parseFloat(jsjehj)*parseFloat(sl)*100)/100;
}
//���㸽��˰����˰�ļ�˰���ϼ�
function getFjsJs(obj,rowIndex){	
	//�õ������ж���
	var rows = eval(obj);
	//�õ��ø���˰��������˰
	var fSzsm = getFathers(rows[rowIndex].all("szsmdm").value);
	//��˰�ϼ�
	var jsjehj =0;
	//�������˰�Ѿ���ӵ��б��ϼƼ�˰��
	var sss = ","+fSzsm.toString()+",";
	for(var ii=0;ii<rows.length;ii++){
		if(rows[ii].all("szsmdm")){
			//����ϸ������
			if(sss.indexOf(","+rows[ii].all("szsmdm").value+",")>=0 && rows[ii].all("jsje").value!=''){
				//�Ǹ���˰��˰�������м�˰���
				//�ۼӼ�˰˰��				
				jsjehj = parseFloat(jsjehj) + parseFloat(rows[ii].all("jsje").value);
			}
		}
	}	
	return jsjehj;
}


//��˰���仯ʱ������صĸ���˰��ʵ��
function computeRow1(obj){	
	if(checkSjseInput(obj)){		
		//����ʵ��
		computeSjje(obj);
		//���㸽��˰���Ļ���ҵ�����
	    resetFjs();
		//�����Ļ�������ҵ��
		resetWhjssy();
	}

}

//����ʵ�ɽ��
function computeSjje(obj){
	var oRow = getObjRow(obj);
	//�������Ʊ�ʾ
	var asljbs = oRow.all("asljbs").value;
	//��˰���
	var jsje = oRow.all("jsje").value;
	//˰��
	var sl = oRow.all("sl").value;
	//��˰����
	//var jsjs = oRow.all("jsjs").value;
	//��˰����
	var kssl = oRow.all("kssl").value;
	//alert(asljbs);
	//1.��ѡ�е�˰Ŀ����(szsmdm)��Ӧ��˰��˰Ŀ�����(dmdb.sb_dm_szsm)�е�asljbsΪ�յ�,ҳ�治�����㣻
	if(asljbs!=''){
		 //2.ѡ�е�˰Ŀ����(szsmdm)��Ӧ��˰��˰Ŀ�����(dmdb.sb_dm_szsm)�еġ�asljbs������ҳ���ʵ�ʽ�˰�ҳ��ļ�˰����dmdb.sb_dm_szsmdm.sl��
		if(asljbs=='0'){
			oRow.all("sjse").value=Math.round(parseFloat(jsje)*parseFloat(sl)*100)/100;
		}
		//3.ѡ�е�˰Ŀ����(szsmdm)��Ӧ��˰��˰Ŀ�����(dmdb.sb_dm_szsm)�еġ�asljbs�������ߣ�����ҳ���ʵ�ʽ�˰�ҳ��ļ�˰����dmdb.sb_dm_szsmdm.jsjs��
		if(asljbs=='1' || asljbs=='3'){
			//oRow.all("sjse").value=Math.round(parseFloat(jsje)*parseFloat(jsjs)*100)/100;
			oRow.all("sjse").value=Math.round(parseFloat(jsje)*parseFloat(sl)*100)/100;
		}
		//4.ѡ�е�˰Ŀ����(szsmdm)��Ӧ��˰��˰Ŀ�����(dmdb.sb_dm_szsm)�еġ�asljbs������ҳ���ʵ�ʽ�˰�ҳ��Ŀ�˰������dmdb.sb_dm_szsmdm.jsjs��
		if(asljbs=='2'){
			//oRow.all("sjse").value=Math.round(parseFloat(kssl)*parseFloat(jsjs)*100)/100; 
			oRow.all("sjse").value=Math.round(parseFloat(kssl)*parseFloat(sl)*100)/100; 
		}
	
	}
	
}

</script>
</html:html>