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
<title>���ֽɿ���¼�� </title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="fnOnLoad();whenReload('GTGSHWSZMX')">
<%@ include file="./include/header.jsp"%>


<html:form action="/webapp/smsb/gtgshWszlrAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="zhdm" />
<html:hidden property="pzzldm" />
<html:hidden property="lrr" />
<html:hidden property="jsjdm" />
<html:hidden property="swjgzzjgdm2" />
<html:hidden property="gjbzhydm" />
<html:hidden property="djzclxdm" />
<html:hidden property="djzclxmc" />
<html:hidden property="dz" />
<html:hidden property="wszh"/>

<html:hidden property="zjlxdm"/>
<html:hidden property="zjhm"/>
<html:hidden property="zjlxmc"/>


      <TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9' height="300" >
 <tr>
    <td valign="top" height="300"> <br>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1"  colspan="2">���ֽɿ���¼��</td>
        </tr>
        <tr>
          <td class="1-td2"  colspan="2"> <br>
            <table cellspacing="0" class="table-99" width="99%" height="27">
              <!--<tr class="black9">
                     <td colspan="2" align="center" nowrap  height="12">
                	��˰֤�ţ�<bean:write name="gtgshWszlrForm" property="wszh"/></td>
              </tr>-->
              <tr class="black9">
                <td align="center" nowrap  height="11">
        	        <div align="center">����ڣ�<bean:write name="gtgshWszlrForm" property="lrrq"/> <html:hidden property="lrrq"/>&nbsp;&nbsp;
        	        </div>
                </td>
                <td align="center" nowrap  height="11">
                	<div align="right">&nbsp;���ջ������ƣ�<bean:write name="gtgshWszlrForm" property="swjgzzjgmc"/><html:hidden property="swjgzzjgmc"/> <html:hidden property="swjgzzjgdm"/></div>
                </td>
              </tr>
            </table><br>

             <table class="table-99" border="0" cellpadding="0" cellspacing="0">
              <tr class="black9">
               <td nowrap class="2-td2-t-left">���������</td>
               <td nowrap class="2-td2-t-left">
               <html:text property="nsrjsjdm" onKeyDown="jsjdmQuery()" onchange="doSubmitForm('Query')"/></td>
                      <td nowrap class="2-td2-t-left">����˰������</td>
                      <td nowrap class="2-td2-t-center"> <html:text property="nsrmc" styleClass="inputnoborder"  onkeydown="if(event.keyCode==13) event.keyCode=9;"  size="30" readonly="true"/>
                        ��</td>
              </tr>

             </table>
             <br>
			<table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0" id="GTGSHWSZMX"  onkeydown=dokeydown(this.id,'szsmdm')  onmouseup='return dokeyUp(this.id)'>
			<ttsoft:smsbtable form="gtgshWszlrForm" property="dataList"tableId="GTGSHWSZMX" hasTitle="true"/>
			<DIV id=divSfTemp></DIV>
              <tr>
                <td nowrap class="2-td2-left">�ϼ�</td>
                <td nowrap class="2-td2-left" colspan="6">&nbsp;</td>
                <td nowrap class="2-td2-center"><html:text property="hjsjje" readonly="true" onkeydown="if(event.keyCode==13) event.keyCode=9;" size="15" styleClass="inbright" /></td>
              </tr>
             </table>
               <table border="0" width="100%">
              <tr>
                <td width="27%">&nbsp;</td>
                <td width="7%"><input type="image" accesskey="a" tabIndex="-1" onclick="befAdd();return false;" style="cursor:hand" onMouseOver="MM_swapImage('zj1','','<%=static_contextpath%>images/zj-a2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="zj1" src="<%=static_contextpath%>images/zj-a1.jpg" width="79" border="0" height="22"></td>
                <td width="8%"><input type="image" accesskey="d" tabIndex="-1" onclick="befDel();return false;" style="cursor:hand"  onMouseOver="MM_swapImage('sc1','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="ɾ��" id="sc1" src="<%=static_contextpath%>images/sc-d1.jpg" width="79" border="0" height="22"></td>
                <td width="8%"><input type="image" accesskey="s" tabIndex="-1" onclick="befSave('Save');return false;" style="cursor:hand" onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" border="0" height="22"></td>
                <td width="8%"><input type="image" accesskey="x" tabIndex="-1" onclick="doSubmitForm('Dismiss');return false;" style="cursor:hand" onMouseOver="MM_swapImage('ds1','','<%=static_contextpath%>images/cx-x2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="ds1" src="<%=static_contextpath%>images/cx-x1.jpg" width="79" border="0" height="22"></td>
                <td width="9%"><input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22"></td>
                <td width="33%">&nbsp;</td>
              </tr>
            </table>
			<br>
          </td>
        </tr>
      </table>

      <br>
  <!--   </td>
 </tr>
</table> -->
<div id=szsmdm_epodDateLayer style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" ><select id=sel onclick=selectClick("szsmdm","GTGSHWSZMX") onkeyup=selectMove("szsmdm","GTGSHWSZMX")  onfocusout=selectClick("szsmdm","GTGSHWSZMX") onkeydown=if(window.event.keyCode==13)selectClick("szsmdm","GTGSHWSZMX")></select></div>

<INPUT TYPE="hidden" NAME="szsmdm_focus">
</html:form>
<%@ include file="./include/footer.jsp"%>
   </td>
  </tr>
</table> 
</body>
<script language=JavaScript >
var GTGSHWSZMX_list=new DTable(GTGSHWSZMX,jsArr_GTGSHWSZMX);
GTGSHWSZMX_list.tableTail=1;
function jsjdmQuery(){
	//if(window.event.keyCode==13){
		//doSubmitForm('Query')
	//}
	if(event.keyCode==13) event.keyCode = 9;
}

function befAdd(){
	if (document.gtgshWszlrForm.nsrmc.value!="" || document.gtgshWszlrForm.swjgzzjgdm2.value!=""){
		tableid="GTGSHWSZMX";
    	var rows = eval(tableid).rows;
    	//alert(rows.length+"<>hhhh");
    	if (rows.length<9){
			addRow('GTGSHWSZMX',null,'szsmdm');
		}
	}
}
function befDel(){
	tableid="GTGSHWSZMX";
	var rows = eval(tableid).rows;
	//alert(rows.length+"<>hhhh");
	if (rows.length>2){
		deleteRow('GTGSHWSZMX',null,'szsmdm')
	}
}
//modified by zhangyj 20131224 ������˰֤���¼������4��7
function befSave(actionFlag){
	if (document.gtgshWszlrForm.nsrmc.value!="" || document.gtgshWszlrForm.swjgzzjgdm2.value!=""){
		tableid="GTGSHWSZMX";
    	var rows = eval(tableid).rows;
    	//alert(rows.length+"<>hhhh");
		if (rows.length>9) {
			alert("һ��¼�벻�ܶ���7����Ϣ��");
		}
		if (rows.length>2 && rows.length< 10){
			//doSubmitForm(actionFlag);
			doSubmitCheck(actionFlag);
		}
	}
}

function befPrint(actionFlag){
	document.gtgshWszlrForm.wszh.value="0007265";
	//if (document.gtgshWszlrForm.wszh.value!=""){
		//doSubmitForm(actionFlag);
	//}
}

//TSNY�ж�   ChangeBy Tujunbing 2012-08-30
//��鲢�Ƚ���ʾ���ڡ�ִ�����ڣ���Ҫ˰��˰Ŀ���롢˰��������������
function doCheck_szsm_tsny(szsmdm,skssjsrq){
	//alert("szsmdm=="+szsmdm+"==skssjsrq=="+skssjsrq);
	//���˰��˰Ŀ�����Ӧ��ʾ��Ϣ
	var tsnyArr = new Array();
	tsnyArr = getTsny(szsmdm_ts_value,szsmdm);
	//�Ƿ��ҵ�
	if(tsnyArr.length != 0){
		//alert(tsnyArr);
		var tsny = tsnyArr[0][1];//��ʾ����
		var nowdate1 = tsnyArr[0][2];//ϵͳ����						
		var tsksrq = tsnyArr[0][3];//��ʾ��ʼ����
		var tsjsrq = tsnyArr[0][4];//��ʾ��������	
		var zxksrq = tsnyArr[0][5];//ִ�п�ʼ����
		var zxjsrq = tsnyArr[0][6];//ִ�н�������
		
		//�ж�ϵͳ��ǰ�����Ƿ�����ʾ��ʼ���ں���ʾ�������ڷ�Χ�ڣ�������ʾtsny�е���Ϣ
		var inRangeOfTsrq = false;
		//ϵͳ���� >= ��ʾ��ʼ����
		if(tsksrq != ""){
			//���ϵͳ���� >= ��ʾ��ʼ����
			//alert("parseFloat(nowdate1)::"+parseFloat(nowdate1));
			//alert("parseFloat(tsksrq)::"+parseFloat(tsksrq));
			//alert("ϵͳ���� >= ��ʾ��ʼ����::"+(parseFloat(nowdate1) >= parseFloat(tsksrq)));
			if(parseFloat(nowdate1) >= parseFloat(tsksrq)){
				inRangeOfTsrq = true;
			}			
		}
		//ϵͳ���� <= ��ʾ��������
		if(tsjsrq != ""){
			//���ϵͳ���� <= ��ʾ��������
			//alert("ϵͳ���� <= ��ʾ��������::"+(parseFloat(nowdate1) <= parseFloat(tsjsrq)));
			if(tsksrq != ""){
				if(parseFloat(nowdate1) >= parseFloat(tsksrq)){
					if(parseFloat(nowdate1) <= parseFloat(tsjsrq)){
						inRangeOfTsrq = true;
					}else{
						inRangeOfTsrq = false;
					}
				} 
			}
			if(tsksrq == ""){
				if(parseFloat(nowdate1) <= parseFloat(tsjsrq)){
					inRangeOfTsrq = true;
				}else{
					inRangeOfTsrq = false;
				}
			}				
		}
		
		//�ж�˰���������������Ƿ���ִ�п�ʼ���ں�ִ�н������ڷ�Χ�⣬������ʾ"�����˰���������ڲ����������ڷ�Χ�ڣ�"
		var outRangeOfZxrq = false;
		//˰�������������� < ִ�п�ʼ����
		if(zxksrq != ""){
			//˰�������������� < ִ�п�ʼ����
			//alert("parseFloat(skssjsrq)::"+parseFloat(skssjsrq));
			//alert("parseFloat(zxksrq)::"+parseFloat(zxksrq));
			//alert("˰�������������� < ִ�п�ʼ����::"+(parseFloat(skssjsrq) < parseFloat(zxksrq)));
			if(parseFloat(skssjsrq) < parseFloat(zxksrq)){
				outRangeOfZxrq = true;
			}		
		}
		//˰�������������� > ִ�п�ʼ����
		if(zxjsrq != ""){
			//˰��������������>ִ�н�������
			//alert("(parseFloat(skssjsrq) ::"+(parseFloat(skssjsrq)));
			//alert("parseFloat(zxjsrq)::"+(parseFloat(zxjsrq)));
			//alert("˰��������������>ִ�н�������::"+(parseFloat(skssjsrq) > parseFloat(zxjsrq)));
			if(parseFloat(skssjsrq) > parseFloat(zxjsrq)){
				outRangeOfZxrq = true;
			}						
		}				
		//�����ʾ����Ϊ�գ�����ʾ��ʾ����;���ж�ִ�������Ƿ��ڹ涨ʱ�䷶Χ�ڡ�
		if(tsksrq == "" && tsjsrq == ""){
			inRangeOfTsrq = true;
		}			
		//�����ʾ���ں�ִ�����ڶ�Ϊ�գ���ֻ��ʾ��ʾ����
		if(tsksrq == "" && tsjsrq == "" && zxksrq == "" && zxjsrq == ""){
			inRangeOfTsrq = true;
			outRangeOfZxrq = false;
		}			
		//��ʾ��Ϣ���£�		
		//�����ǰ��������ʾ���ڷ�Χ�ڣ�����ʾ
		if(inRangeOfTsrq == true){
		  //alert("---------"+tsny);
			if(tsny != '' && tsny != null && tsny !='null'){
				if(!window.confirm(tsny+'\n\n'+"�Ƿ������  �����ȷ�������������걨�������ȡ������ɾ����˰��˰Ŀ������걨��" )){
					return false;//��ͬ�⣬����
				}
			}
		}
	}
}

//���������ж�  ChangeBy Tujunbing 2012-08-30
//��鲢�Ƚ���ʾ���ڡ�ִ�����ڣ���Ҫ˰��˰Ŀ���롢˰��������������  
function doCheck_szsm_zxrq(szsmdm,skssjsrq){
	//alert("szsmdm=="+szsmdm+"==skssjsrq=="+skssjsrq);
	//���˰��˰Ŀ�����Ӧ��ʾ��Ϣ
	var tsnyArr = new Array();
	tsnyArr = getTsny(szsmdm_ts_value,szsmdm);
	//�Ƿ��ҵ�
	if(tsnyArr.length != 0){
		//alert(tsnyArr);
		var tsny = tsnyArr[0][1];//��ʾ����
		var nowdate = tsnyArr[0][2];//ϵͳ����						
		var tsksrq = tsnyArr[0][3];//��ʾ��ʼ����
		var tsjsrq = tsnyArr[0][4];//��ʾ��������	
		var zxksrq = tsnyArr[0][5];//ִ�п�ʼ����
		var zxjsrq = tsnyArr[0][6];//ִ�н�������
		
		//�ж�ϵͳ��ǰ�����Ƿ�����ʾ��ʼ���ں���ʾ�������ڷ�Χ�ڣ�������ʾtsny�е���Ϣ
		var inRangeOfTsrq = false;
		//ϵͳ���� >= ��ʾ��ʼ����
		if(tsksrq != ""){
			//���ϵͳ���� >= ��ʾ��ʼ����
			//alert("parseFloat(nowdate)::"+parseFloat(nowdate));
			//alert("parseFloat(tsksrq)::"+parseFloat(tsksrq));
			//alert("ϵͳ���� >= ��ʾ��ʼ����::"+(parseFloat(nowdate) >= parseFloat(tsksrq)));
			if(parseFloat(nowdate) >= parseFloat(tsksrq)){
				inRangeOfTsrq = true;
			}			
		}
		//ϵͳ���� <= ��ʾ��������
		if(tsjsrq != ""){
			//���ϵͳ���� <= ��ʾ��������
			//alert("ϵͳ���� <= ��ʾ��������::"+(parseFloat(nowdate) <= parseFloat(tsjsrq)));
			if(tsksrq != ""){
				if(parseFloat(nowdate) >= parseFloat(tsksrq)){
					if(parseFloat(nowdate) <= parseFloat(tsjsrq)){
						inRangeOfTsrq = true;
					}else{
						inRangeOfTsrq = false;
					}
				} 
			}
			if(tsksrq == ""){
				if(parseFloat(nowdate) <= parseFloat(tsjsrq)){
						inRangeOfTsrq = true;
					}else{
						inRangeOfTsrq = false;
				}
			}				
		}
		
		//�ж�˰���������������Ƿ���ִ�п�ʼ���ں�ִ�н������ڷ�Χ�⣬������ʾ"�����˰���������ڲ����������ڷ�Χ�ڣ�"
		var outRangeOfZxrq = false;
		//˰�������������� < ִ�п�ʼ����
		if(zxksrq != ""){
			//˰�������������� < ִ�п�ʼ����
			//alert("˰�������������� < ִ�п�ʼ����::"+(parseFloat(skssjsrq) < parseFloat(zxksrq)));
			if(parseFloat(skssjsrq) < parseFloat(zxksrq)){
				outRangeOfZxrq = true;
			}		
		}
		//˰�������������� > ִ�п�ʼ����
		if(zxjsrq != ""){
			//˰��������������>ִ�н�������
			//alert("˰��������������>ִ�н�������::"+(parseFloat(skssjsrq) > parseFloat(zxjsrq)));
			if(parseFloat(skssjsrq) > parseFloat(zxjsrq)){
				outRangeOfZxrq = true;
			}						
		}					
		//�����ʾ����Ϊ�գ�����ʾ��ʾ����;���ж�ִ�������Ƿ��ڹ涨ʱ�䷶Χ�ڡ�
		if(tsksrq == "" && tsjsrq == ""){
			inRangeOfTsrq = true;
		}				
		//�����ʾ���ں�ִ�����ڶ�Ϊ�գ���ֻ��ʾ��ʾ����
		if(zxksrq == "" && zxjsrq == ""){
			inRangeOfTsrq = true;
			outRangeOfZxrq = false;
		}			
		//��ʾ��Ϣ���£�						
		//���˰������������ִ�����ڷ�Χ�⣬����ʾ
		if(outRangeOfZxrq == true){
			alert("˰��˰Ŀ����"+szsmdm+": �����˰���������ڲ�����Ч���ڷ�Χ��!");
			return false;//��ͬ�⣬����
		}
	}
}

//���ݹؼ�ֵ������ʾ���� ChangeBy Tujunbing 2012-08-30
function getTsny(arrayName,key){
  var obj_array = get_obj(arrayName);
  var tempArr = new Array();
  for(var ii=0;ii<obj_array.length;ii++){
		if(obj_array[ii][0]==key) tempArr.push(obj_array[ii]);
  }
  return tempArr;
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

/****�������˰��Ϊ��������������ʾ��ʾ��Ϣ****/
/****20040216 Shi Yanfeng****/
var nsrzt = <ttsoft:write name="gtgshWszlrForm" property="nsrzt" filter="false"/>;
function checkNsrzt(){
	if(nsrzt=='30'){
		alert('����˰��״̬Ϊ��������');
	}
	if(nsrzt=='20'){
		alert('����˰��״̬Ϊͣҵ��');
	}
	if(nsrzt=='40'){
		alert('����˰��״̬Ϊע����');
	}
	if(nsrzt=='41'){
		alert('����˰��״̬Ϊ������');
	}
	if(nsrzt=='50'){
		alert('����˰��״̬Ϊ��;��');
	}
}
checkNsrzt();
//��鲢�ύ����Ҫ����걨���ڡ�˰����������
//��˰��Ŀ���롢˰���������ڡ���˰��������˰��ʵ�ʽ�˰��
function doSubmitCheck(ope){
	//ChangeBy Tujunbing 2012-08-30
	//����¼����������
	if(document.forms[0].nsrmc.value=='') {
		alert("��˰�����Ʋ���Ϊ�գ�");
		return false;
	}
	//���ÿһ�е�ֵ�Ƿ�Ϸ�
	for(var ii=1;ii<GTGSHWSZMX.rows.length-1;ii++){	
		var checkSzsmdm = "";
		var checkSkssjsrq = "";	
		if(GTGSHWSZMX.rows[ii].all("szsmdm")) {	
			var szsmdmVal = GTGSHWSZMX.rows[ii].all("szsmdm").value;
			if(szsmdmVal==""){
				alert("˰��˰Ŀ���벻��Ϊ��");	
				GTGSHWSZMX.rows[ii].all("szsmdm").focus();
				return false;
			}
			else{
				checkSzsmdm = szsmdmVal;			
			}	
		}					
		if(GTGSHWSZMX.rows[ii].all("skssksrq") && !isDate(GTGSHWSZMX.rows[ii].all("skssksrq"),false)) {
			alert("˰��������ʼ���ڲ��Ϸ���");		
			return false;
		}
		if(GTGSHWSZMX.rows[ii].all("skssjsrq") && !isDate(GTGSHWSZMX.rows[ii].all("skssjsrq"),false)) {
			alert("˰��������ֹ���ڲ��Ϸ���");		
			return false;
		}
				//��ʼ����ҪС�ڽ�ֹ����
		if(compare(GTGSHWSZMX.rows[ii].all("skssksrq"),GTGSHWSZMX.rows[ii].all("skssjsrq"))<0) {
			alert("��ʼ���ڱ���С�ڻ���ڽ�ֹ���ڣ�");
			return false;
		}//���˰��������ֹ���� ChangeBy Tujunbing 2012-08-30 
		else{
			if(GTGSHWSZMX.rows[ii].all("skssjsrq")){
				checkSkssjsrq = GTGSHWSZMX.rows[ii].all("skssjsrq").value;
			}
		}

		//2007���������ʹ��˰�¾�˰���ν�
		//������2007-1-1�����������ʹ��˰˰�������2007-1-1�պ������˰�֮ǰ���þ�˰��
		if(GTGSHWSZMX.rows[ii].all("szsmdm")&&GTGSHWSZMX.rows[ii].all("szsmdm").value.substr(0,2)=="15"){
			if(GTGSHWSZMX.rows[ii].all("skssksrq")&&GTGSHWSZMX.rows[ii].all("skssjsrq")){
				var ksnd=GTGSHWSZMX.rows[ii].all("skssksrq").value.substr(0,4);
				var jsnd=GTGSHWSZMX.rows[ii].all("skssjsrq").value.substr(0,4);
				if(ksnd<=2006&&jsnd>2006){
					alert("2007��1��1�����������ʹ��˰˰�����Ϊԭ�����������뽫˰�������ڲ��Ϊ��\n"+
					GTGSHWSZMX.rows[ii].all("skssksrq").value+"-20061231\n"+
					"20070101-"+GTGSHWSZMX.rows[ii].all("skssjsrq").value+"\n"+
					"���������ֽɿ������¼�룡");
					GTGSHWSZMX.rows[ii].all("skssjsrq").value="20061231";
					GTGSHWSZMX.rows[ii].all("skssjsrq").focus();
					return false;
				}
			}
		}
		//��˰������Ϊ��ʱ����Ϊ�Ϸ�������
		if(GTGSHWSZMX.rows[ii].all("kssl") &&GTGSHWSZMX.rows[ii].all("kssl").value!=null&& !isNum(GTGSHWSZMX.rows[ii].all("kssl"),0)) {
			alert("��˰������Ϊ��ʱ����Ϊ�Ϸ���������");		
			return false;
		}
		var obj_name=GTGSHWSZMX.rows[ii].all("szsmdm");
		var obj_value = obj_name.value;
		if (obj_value.indexOf("11")!=0 && obj_value.indexOf("88")!=0) {
			if(GTGSHWSZMX.rows[ii].all("jsje") && !isNum(GTGSHWSZMX.rows[ii].all("jsje"),0,null,true)) {
				alert("��˰���Ϸ���");		
				return false;
			}
			if(GTGSHWSZMX.rows[ii].all("sjse") && !isNum(GTGSHWSZMX.rows[ii].all("sjse"),0,null,true)) {
				alert("ʵ�ʽ�˰��Ϸ���");		
				return false;
			}
		}
		//ѡ���˳��������˰��������¼��
		if (obj_value.indexOf("11")==0 || obj_value.indexOf("88")==0) {
			if(GTGSHWSZMX.rows[ii].all("kssl") && !isNum(GTGSHWSZMX.rows[ii].all("kssl"),0,null,true)) {
				alert("��˰�������Ϸ���");		
				return false;
			}
			//��˰��ʵ�ʽ�˰�Ϊ��ʱ������Ϊ�Ϸ�����
			if(GTGSHWSZMX.rows[ii].all("jsje") && GTGSHWSZMX.rows[ii].all("jsje").value!=null && !isNum(GTGSHWSZMX.rows[ii].all("jsje"),0)) {
				alert("��˰��Ϊ��ʱ����Ϊ�Ϸ������֣�");		
				return false;
			}
			if(GTGSHWSZMX.rows[ii].all("sjse") && GTGSHWSZMX.rows[ii].all("sjse").value!=null && !isNum(GTGSHWSZMX.rows[ii].all("sjse"),0)) {
				alert("ʵ�ʽ�˰�Ϊ��ʱ����Ϊ�Ϸ������֣�");		
				return false;
			}
		}
		//��֤ ChangeBy Tujunbing 2012-08-30
		if(checkSzsmdm != "" && checkSkssjsrq != ""){
			var checkResult_tsny ="";
			var checkResult_zxrq = "";
			//��ȡTSNY:doCheck_szsm_tsny function
			checkResult_tsny = doCheck_szsm_tsny(checkSzsmdm,checkSkssjsrq);
			//��ȡ˰��������:doCheck_szsm_zxrq function
			checkResult_zxrq = doCheck_szsm_zxrq(checkSzsmdm,checkSkssjsrq);
			if(checkResult_tsny == false){
				return checkResult_tsny;
			}
			if(checkResult_zxrq == false){
				return checkResult_zxrq;
			}       
		}
	}
	doSubmitForm(ope);
}


//�����б�ʹ�õ�js����
<ttsoft:write name="gtgshWszlrForm" property="scriptStr" filter="false"/>
//�Ƿ���ʵ����˰��־
isadditions = true;
//���¼��㸽��˰�ļ�˰����Ӧ�ɽ��
function resetFjs(){	
	tableid="GTGSHWSZMX";
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
	computeSameSum("sjse","hjsjje","GTGSHWSZMX");

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
	
	var oRow = getObjRow(obj);	
	//������޸���Ϊ����˰�����¼���
	if(oRow.all("sffjs").value=='2'){ 
		//���¼���ϵͳ�ϼ�
		computeSameSum("sjse","hjsjje","GTGSHWSZMX");
		hj();
		return false;
	}
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
hjArray[0]=new Array("sjse","hjsjje","GTGSHWSZMX");
//���ϼ�������
function initHj(){	
	document.forms[0].hjsjje.value='0';
}
initHj();

//�����Ļ���ҵ����ѵļ�˰��ʵ��
//�Ļ���ҵ����ѵļ�˰���Ϊ��˰�ļ�˰���
function resetWhjssy(){
	tableid="GTGSHWSZMX";
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
	computeSameSum("sjse","hjsjje","GTGSHWSZMX");

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
	if(jsje=='')jsje=0;
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
		    //����˰��������ʼ���ھ�������˰˰�� added by wen qing yu 2007-5-29
			if(oRow.all("szsmdm").value.substr(0,2)==15){
		    if(oRow.all("skssksrq").value.substr(0,4)<2007){
					 if(oRow.all("szsmdm").value==150010)
					   { sl=10; }
					 if(oRow.all("szsmdm").value==150020)
					   { sl=8;  }
           if(oRow.all("szsmdm").value==150030)
             { sl=6;  }
				   if(oRow.all("szsmdm").value==150040)
				     { sl=4;  }
					 if(oRow.all("szsmdm").value==150050)
					   { sl=1;  }	
				   if(oRow.all("szsmdm").value==150060)
				     { sl=0.5;}
				}else{
					if(oRow.all("szsmdm").value==150010)
					   { sl=30; }
					if(oRow.all("szsmdm").value==150020)
					   { sl=24;  }
					if(oRow.all("szsmdm").value==150030)
             { sl=18;  }
					if(oRow.all("szsmdm").value==150040)
				     { sl=12;  }
					if(oRow.all("szsmdm").value==150050)
					   { sl=3;  }	
					if(oRow.all("szsmdm").value==150060)
				     { sl=1.5;}
				}
				oRow.all("sl").value=sl;
			}
		    
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