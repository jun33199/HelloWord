<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>


<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>�����еط�˰����걨�ɿ</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>


</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
<!-- <table  width="100%" height="61%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
   <td colspan=2> -->
<%@ include file="./include/header.jsp"%>

<!-- ������˰ -->
<html:form action="/webapp/smsb/zhsbPgbsAction.do" method="POST" >

<html:hidden property="actionType" value="Show"/>
<html:hidden property="yhdm" />
<html:hidden property="yhmc" />
<html:hidden property="sklxmc" />

      <table width="96%" align="center" cellspacing="0" class="table-99">
                <tr>
                  <td class="1-td1"  colspan="7">��˰����˰��</td>
                </tr>
                <tr>
                  <td class="1-td2"  colspan="7"> <br>
				  	<table width="93%" cellspacing="0" class="table-99">
                      <tr class="black9">
                        <td width="11%" align="center" nowrap> <div align="right">�걨���ڣ�</div></td>
                        <td width="11%" align="center" nowrap><div align="left">
							<html:text property="sbrq"    size="10" onchange="if(isDate(this,false)) changeDateQ();"  onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                          </div></td>
                        <td width="21%" align="center" nowrap> <div align="right">˰�����ͣ�
                            &nbsp; </div></td>
                        <td width="39%" align="center" nowrap><div align="left">
                            <!-- <select size="1" name="sklxdm" onchange="clearTable('SBJKMX')">
                              <option value=1>����˰��</option>
                              <option value=4>����Ƿ˰</option>
                              <option value=8>�������</option>
                            </select> -->

                            <!-- ������˰ -->
                            <select disabled>
                            	<option>��˰����˰��</option>
                            </select>
                            <!--
							<ttsoft:select property="sklxdm" codekey="ZHSB_SKLX" onchange="clearTableSklx('SBJKMX')" onkeydown="if(event.keyCode==13) event.keyCode=9;" /><br>
						<ttsoft:define id="ZHSB_SKLX" codekey="ZHSB_SKLX"/>-->
                          </div></td>

                        <td width="14%" align="center" nowrap> <div align="center">��λ��Ԫ</div></td>
                        <td width="4%" align="center" nowrap>&nbsp;</td>
                      </tr>
                    </table>
                    <br> <table width="94%" border="0" cellpadding="0" cellspacing="0">
                      <tr class="black9">
                        <td width="17%" nowrap class="2-td2-t-left">˰����������</td>
                        <td width="35%" nowrap class="2-td2-t-left">
						<html:text property="jsjdm"   size="20" onchange="if(this.value!='') doSubmitForm('Query')" onKeyDown="jsjdmQuery()"/>
						</td>
                        <td width="24%" nowrap class="2-td2-t-left">��λ����</td>
                        <td width="24%" nowrap class="2-td2-t-center">
						<html:text property="nsrmc" tabIndex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/>
						</td>
                      </tr>
                      <tr>
                        <td nowrap class="2-td2-left">�ɿ���������</td>
                        <td nowrap class="2-td2-left" id="bankName">&nbsp
						<!-- <html:text property="yhmc" styleClass="inputnoborder"   size="30" readonly="true"/> -->
						</td>
                        <td nowrap class="2-td2-left">�ɿ������ʺ�</td>
                        <td nowrap class="2-td2-center"><!-- <select name=zh>
                            <option>�����ʺ�</option>
                            <option>�����ʺ�</option>
                          </select> -->

                          <!-- ������˰ -->
						  <bean:define id="bankList" name="zhsbPgbsActionForm" property="bankList" />
                              <html:select property="zh" onchange="setBankName(this)"  onkeydown="enterAddRow()">
                                 <html:options collection="bankList" property="zh" labelProperty="zh"/>
                              </html:select>
						  </td>
                      </tr>
                    </table>
                    <br>
				<table width="94%" border="0" cellpadding="0" cellspacing="0" height="114" id="SBJKMX" onkeydown="return dokeydown(this.id,'szsmdm')"  onmouseup='return dokeyUp(this.id)'>
				<!-- ������˰ -->
				<ttsoft:smsbtable form="zhsbPgbsActionForm" property="dataList"  tableId="SBJKMX" hasTitle="true"/>
					  <DIV id=divSfTemp></DIV>
                      <tr>
                        <td nowrap class="2-td2-left" height="28">�ϼ�</td>
                        <td nowrap class="2-td2-left" height="28">
						<!-- <html:text property="lrhj"    size="10" onchange="isNum(this,null,null,false,null,2)" styleClass="inputalignright" />
						-->
						&nbsp;
						</td>
                        <td nowrap class="2-td2-left" height="28"> ��</td>
                        <td nowrap class="2-td2-left" height="28"> ��</td>
                        <td nowrap class="2-td2-left" height="28"> ��</td>
                        <td nowrap class="2-td2-left" height="28"> ��</td>
                        <td nowrap class="2-td2-left" height="28"> ��</td>
                        <td nowrap class="2-td2-center" height="28"> &nbsp<!-- <html:hidden property="xthj" /> --><html:text property="xthj" size="10"  styleClass="inputnoborder" /></td>
                      </tr>
                    </table>
			        <table width="94%" border="0" cellpadding="0" cellspacing="4">
                      <tr valign="bottom">
                        <td width="51%"> </td>
                        <td width="9%"><input type="image" accesskey="w" tabIndex="-1" onclick="javascript:doJkswh(); return false;"  onMouseOver="MM_swapImage('whjks1','','<%=static_contextpath%>images/whjks-w2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="ά���ɿ���" id="whjks1" src="<%=static_contextpath%>images/whjks-w1.jpg" width="110" height="22"></td>
                        <td width="9%"> <input type="image" accesskey="a" tabIndex="-1" onclick="javascript:if(document.forms[0].jsjdm.value=='' || document.forms[0].nsrmc.value==''){ return false;} else {addRow('SBJKMX',null,'szsmdm');return false;}"  onMouseOver="MM_swapImage('zj1','','<%=static_contextpath%>images/zj-a2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="����" id="zj1" src="<%=static_contextpath%>images/zj-a1.jpg" width="79" height="22"></td>
                        <td width="9%" ><input type="image" accesskey="d" tabIndex="-1" onClick="javascript:deleteRow('SBJKMX',null,'szsmdm'); return false;"  onMouseOver="MM_swapImage('sc2','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="ɾ��" id="sc2" src="<%=static_contextpath%>images/sc-d1.jpg" width="79" height="22"></td>
                        <td width="9%"><input type="image" accesskey="s" tabIndex="-1" onClick="doSubmitCheck('Save'); return false;"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="����" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="22"></td>
                        <!-- <td width="9%"><input type="image" accesskey="p" tabIndex="-1"  onClick=""  onMouseOver="MM_swapImage('dy1','','<%=static_contextpath%>images/dy-p2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="��ӡ" id="dy1" src="<%=static_contextpath%>images/dy-p1.jpg" width="79" height="22"></td> -->
                        <td width="15%"><input type="image" accesskey="c" tabIndex="-1"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22"></td>
                        <td width="0%">&nbsp;</td>
                      </tr>
                    </table>



      <br>
	     </td>
		</tr>
	  </table>
	  <div id=szsmdm_epodDateLayer style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" ><select id=sel onclick=selectClick("szsmdm","SBJKMX") onkeyup=selectMove("szsmdm","SBJKMX")  onfocusout=selectClick("szsmdm","SBJKMX") onkeydown="if(window.event.keyCode==13) selectClick('szsmdm','SBJKMX')"></select></div>

<INPUT TYPE="hidden" NAME="szsmdm_focus">
</html:form>


<%@ include file="./include/footer.jsp"%>
    <!-- </td>
  </tr>
</table> -->
<script language="javascript">
function change(){
	if(!document.all)
		return
	if (event.srcElement.id=="foldheader") {
		var srcIndex = event.srcElement.sourceIndex
		var nested = document.all[srcIndex+1]
		if (nested.style.display=="none") {
			nested.style.display=''
			event.srcElement.style.listStyleImage="url(<%=static_contextpath%>images/zbotton-jian2.gif)"
		}
		else {
			nested.style.display="none"
			event.srcElement.style.listStyleImage="url(<%=static_contextpath%>images/zbotton-jia2.gif)"
		}
	}
}

document.onclick=change
//��Ӧ���������Ļس���ѯ
function jsjdmQuery(){
	//if(document.all.jsjdm.value!='' && window.event.keyCode==13){
	//	doSubmitForm('Query')
	//}
	if(event.keyCode==13) event.keyCode = 9;
}

//�Ƿ���ʵ����˰��ʾ
isadditions = true;
//isadditions = <ttsoft:write name="zhsbPgbsActionForm" property="isadditons" filter="false"/>;
//������˰,�Ƿ�������ҵ
iswz=<ttsoft:write name="zhsbPgbsActionForm" property="wz" filter="false"/>;
var SBJKMX_list=new DTable(SBJKMX,jsArr_SBJKMX);
//SBJKMX_list.tableHead=1;
SBJKMX_list.tableTail=1;

//������˰
<ttsoft:write name="zhsbPgbsActionForm" property="bankJsArray" filter="false"/>

function setBankName(obj)
{

	for (var i=0; i<bankInfo.length; i++)
	{
		if (bankInfo[i][0] == obj.value)
		{
			document.all.yhmc.value = bankInfo[i][1];
			document.all.yhdm.value = bankInfo[i][2];
			bankName.innerText = " " + bankInfo[i][1];
			break;
		}
	}
	/********������û������������˻���ʱ�򣬽�������Ϣ�ÿ�**********/
	/********20040209 Shi Yanfeng*********************/
	if(bankInfo.length==0){
		document.all.yhmc.value ='';
		document.all.yhdm.value ='';
	}

}
setBankName(document.all.zh);
/****�������˰��Ϊ��������������ʾ��ʾ��Ϣ****/
/****20040216 Shi Yanfeng****/
//������˰
var nsrzt = <ttsoft:write name="zhsbPgbsActionForm" property="nsrzt" filter="false"/>;
checkNsrzt();
//������˰,ת�Ƶ��걨����ʹ�õ�˰����������
<ttsoft:write name="zhsbPgbsActionForm" property="skssrqArr" filter="false"/>

//����ת���걨����֮ǰ��������ʾ�Ƿ񱣴��Ѿ�¼������
function toSbzl(returnStr,type)
{
	if((document.forms[0].jsjdm.value!='' && document.forms[0].nsrmc.value!='' && document.forms[0].sbrq.value!='')){
		var truthBeTold = window.confirm("�ò������޷�������¼������,��ȷ��");
		if( truthBeTold){
			//˰����������

			var ssrq =  "";
			if(type=='q'){
				//����
				ssrq="&skssjsrq="+skssrqArr[3]+"&skssksrq="+skssrqArr[2];
			}
			if(type=='m'){
				//�·�
				ssrq="&skssjsrq="+skssrqArr[1]+"&skssksrq="+skssrqArr[0];
			}
			if(type=='y'){
				//���
				ssrq="&skssjsrq="+skssrqArr[5]+"&skssksrq="+skssrqArr[4]
			}
			if(type=='o'){
				//���⼾����
				ssrq="&skssjsrq="+skssrqArr[7]+"&skssksrq="+skssrqArr[6]
			}

		returnStr+="&iszhsb=1&jsjdm="+document.forms[0].jsjdm.value+"&sbrq="+document.forms[0].sbrq.value+"&lrrq="+document.forms[0].sbrq.value+ssrq;
			window.location=returnStr;
		}
	}else{
		alert('��ȷ���Ѿ�¼���걨���ں�˰����������');
	}
}
//��ת���ɿ���ά������
function doJkswh(){
	//����¼����������
	if(document.forms[0].jsjdm.value=='' || document.forms[0].nsrmc.value=='') return false;
	document.all.actionType.value='Jkswh';
	document.forms[0].submit();
}

//��鲢�ύ����Ҫ����걨���ڡ�˰����������
//��˰��Ŀ���롢˰���������ڡ���˰��������˰��ʵ�ʽ�˰��
function doSubmitCheck(ope){
	//����¼����������
	if(document.forms[0].jsjdm.value==''|| document.forms[0].nsrmc.value=='') return false;
	//�걨����
	if(!isDate(document.forms[0].sbrq,false)) return false;
	//û�в�����ϸ����
	if(SBJKMX.rows.length<=2) return false;

	//���ÿһ�е�ֵ�Ƿ�Ϸ�
	for(var ii=0;ii<SBJKMX.rows.length;ii++){
		if(SBJKMX.rows[ii].all("szsmdm") && SBJKMX.rows[ii].all("szsmdm").value=="") {
			alert("˰��˰Ŀ���벻��Ϊ��");
			SBJKMX.rows[ii].all("szsmdm").focus();
			//SBJKMX.rows[ii].all("szsmdm").select();
			return false;
		}
		if(SBJKMX.rows[ii].all("skssksrq") && !isDate(SBJKMX.rows[ii].all("skssksrq"),false)) {
			//alert("˰��������ʼ���ڲ��Ϸ�");
			return false;
		}
		if(SBJKMX.rows[ii].all("skssjsrq") && !isDate(SBJKMX.rows[ii].all("skssjsrq"),false)) {
			//alert("˰��������ֹ���ڲ��Ϸ�");
			return false;
		}

		if(SBJKMX.rows[ii].all("skssjsrq") &&SBJKMX.rows[ii].all("skssksrq")&& parseFloat(SBJKMX.rows[ii].all("skssksrq").value) > parseFloat(SBJKMX.rows[ii].all("skssjsrq").value)){
			alert("˰��������ʼ���ڴ���˰��������ֹ����");
			SBJKMX.rows[ii].all("skssksrq").focus();
			return false;
		}

		//2007���������ʹ��˰�¾�˰���ν�
		//������2007-1-1�����������ʹ��˰˰�������2007-1-1�պ������˰�֮ǰ���þ�˰��
		if(SBJKMX.rows[ii].all("szsmdm")&&SBJKMX.rows[ii].all("szsmdm").value.substr(0,2)=="15"){
			if(SBJKMX.rows[ii].all("skssksrq")&&SBJKMX.rows[ii].all("skssjsrq")){
				var ksnd=SBJKMX.rows[ii].all("skssksrq").value.substr(0,4);
				var jsnd=SBJKMX.rows[ii].all("skssjsrq").value.substr(0,4);
				if(ksnd<=2006&&jsnd>2006){
					alert("2007��1��1�����������ʹ��˰˰�����Ϊԭ�����������뽫˰�������ڲ��Ϊ��\n"+
					SBJKMX.rows[ii].all("skssksrq").value+"-20061231\n"+
					"20070101-"+SBJKMX.rows[ii].all("skssjsrq").value+"\n"+
					"�����ν����걨¼�룡");
					SBJKMX.rows[ii].all("skssjsrq").value="20061231";
					SBJKMX.rows[ii].all("skssjsrq").focus();
					return false;
				}
			}
		}
		//�����˶Գ���˰������ʹ��˰˰���������ڵ��жϣ�����˰
		//˰�ʼ���ڲ�С��2007��1��1�գ�����ʹ��˰˰�����
		//���ڲ�����2006��12��31��
		//ѭ��ʱ��1��ʼ����Ϊ��һ���Ǳ�����  added  by yuwq  2007-09-11
		if(SBJKMX.rows[ii].all("szsmdm")&&SBJKMX.rows[ii].all("skssksrq")){
			if(SBJKMX.rows[ii].all("szsmdm").value.substr(0,2)=="09")
			 {
			  if(SBJKMX.rows[ii].all("skssksrq").value<20070101)
			  {
				alert("����˰-˰��������ʼ���ڲ�С��2007��1��1�գ�");
				return false;
				}
			 }
			 if(SBJKMX.rows[ii].all("szsmdm").value.substr(0,2)=="11" ||SBJKMX.rows[ii].all("szsmdm").value.substr(0,2)=="88")
			 {
			  if(SBJKMX.rows[ii].all("skssjsrq").value>=20070101)
			  {
				alert("����ʹ��(����)˰-˰�������������ڲ�����2006��12��31�գ�");
				return false;
				}
			 }
		}


		//��˰������Ϊ��ʱ����Ϊ�Ϸ�������
		if(SBJKMX.rows[ii].all("kssl")  && (SBJKMX.rows[ii].all("asljbs").value=="1" || SBJKMX.rows[ii].all("asljbs").value=="2")&& !isNum(SBJKMX.rows[ii].all("kssl"),0,null,true,null,5)) {
			//alert("��˰������Ϊ��ʱ����Ϊ�Ϸ�������");
			return false;
		}
		if(SBJKMX.rows[ii].all("jsje") && !isNum(SBJKMX.rows[ii].all("jsje"),0,null,true)) {
			//alert("��˰���Ϸ�");
			return false;
		}
		if(SBJKMX.rows[ii].all("sjse") && !isNum(SBJKMX.rows[ii].all("sjse"),0,null,true)) {
			//alert("ʵ�ʽ�˰��Ϸ�");
			return false;
		}
		if(SBJKMX.rows[ii].all("sjse") && SBJKMX.rows[ii].all("sjse").value==0) {
			alert("�Ѿ�ȡ�����걨��ʵ�ʽ�˰��Ӧ������");
			SBJKMX.rows[ii].all("sjse").focus();
			return false;
		}

	}
	//����Ƿ������걨
	if(!checkMult()){
		alert("�����ظ��걨����");
		return false;
	}

	//��鳵��˰��˰�������� add by ������ 2006��12��31
	/*if(!checkCar()){
		alert("���ݹ���Ժ�½��䷢�ġ��л����񹲺͹�����˰�����������Ĺ涨��ԭ���л����񹲺͹�����ʹ��˰���������������л����񹲺͹�����ʹ������˰����������ͬʱ���ϣ���ͣ���е�2007��ȵĳ���˰���չ�����������ǰ��ȵĲ���˰���Կɽ��н��ɣ�˰�������ɽ�ļ����ֹ�����趨Ϊ2006��12��31�գ����޸�˰�������ڣ�����");
		return false;
	}*/
	//if(parseFloat(document.forms[0].lrhj.value)!=parseFloat(document.forms[0].xthj.value)) {
	//	alert("¼��ϼƽ����ϵͳ�ϼƽ���\nϵͳ�ϼƽ��="+document.forms[0].xthj.value);
		//document.forms[0].lrhj.focus();
	//	document.forms[0].lrhj.select();
	//	return false;
	//}
	doSubmitForm(ope);
}
//�������ظ��걨��ͬ˰��˰Ŀ��ͬ˰����������
function checkMult(){
	//����ÿ����ͬ˰��˰Ŀ��ͬ˰���������ڵ��걨����
	var count = new Array();
	//���ÿһ�е�ֵ�Ƿ�Ϸ�
	for(var ii=0;ii<SBJKMX.rows.length;ii++){
		if(SBJKMX.rows[ii].all("szsmdm")){
			//ֻ�����ϸ���ݣ���ȥ��ͷ�ͱ�β
			//����µ���Ŀ
			count.push(new Array(SBJKMX.rows[ii].all("szsmdm").value, SBJKMX.rows[ii].all("skssksrq").value, SBJKMX.rows[ii].all("skssjsrq").value));

		}
	}
	//����Ƿ����ظ�����
	count.sort();
	for(var i=0;i<count.length;i++){
		for(var j=i+1; j<count.length; j++){
			if(count[i][0]==count[j][0] && count[i][1]==count[j][1] && count[i][2]==count[j][2]){
				return false;
			}
		}
	}

	return true;
}

//��鳵��˰��˰�������� add by ������ 2006��12��31
function checkCar(){

    //����ÿ����ͬ˰��˰Ŀ��ͬ˰���������ڵ��걨����
	var count = new Array();
	//���ÿһ�е�ֵ�Ƿ�Ϸ�
	for(var ii=0;ii<SBJKMX.rows.length;ii++){
		if(SBJKMX.rows[ii].all("szsmdm")){
			//ֻ�����ϸ���ݣ���ȥ��ͷ�ͱ�β
			//����µ���Ŀ
			count.push(new Array(SBJKMX.rows[ii].all("szsmdm").value, SBJKMX.rows[ii].all("skssksrq").value, SBJKMX.rows[ii].all("skssjsrq").value));

		}
	}
	//����Ƿ����ظ�����
	count.sort();
	for(var i=0;i<count.length;i++){

		if((count[i][0]).substring(0,2) == '11' || (count[i][0]).substring(0,2) == '88') {
			if (count[i][1] > '20061231' || count[i][2] > '20061231') {
				return false;
			}
		}

	}

	return true;

}

//������˰
<ttsoft:write name="zhsbPgbsActionForm" property="scriptStr" filter="false"/>
//���¼��㸽��˰�ļ�˰����Ӧ�ɽ��
function resetFjs(){
	tableid="SBJKMX";
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
		if(obj.value=="2" && szsmStr!='53') countFjs(rows,ii);

	}
	//���¼���ϵͳ�ϼ�
	computeSameSum("sjse","xthj","SBJKMX");

	hj();
}
function publicMethod(){
	//alert("publicmethod");
	//���㸽��˰���Ļ���ҵ�����
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
		computeSameSum("sjse","xthj","SBJKMX");
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
	if(sjsehj==0) return;
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
//�����Ļ���ҵ����ѵļ�˰��ʵ��
//�Ļ���ҵ����ѵļ�˰���Ϊ��˰�ļ�˰���
function resetWhjssy(){
	tableid="SBJKMX";
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
	computeSameSum("sjse","xthj","SBJKMX");

	hj();
}
//�����Ļ���ҵ����ѵļ�˰��ʵ��
function countWhjssy(obj,rowIndex){
	//�õ������ж���
	var rows = eval(obj);
	//�õ��ø���˰��sjse
	var jsjehj = getFjsJs(obj,rowIndex);
	if(jsjehj==0) return;
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


hjArray[0]=new Array("sjse","xthj","SBJKMX");
//���ϼ�������
function initHj(){
	//document.forms[0].lrhj.value='0';
	document.forms[0].xthj.value='0';
}
initHj();

/*//��¼ԭ˰������ֵ
var ysklx = document.forms[0].sklxdm.value;

//ѡ��˰����������б�����
function clearTableSklx(obj){
	//alert(ysklx);

	if(clearTable('SBJKMX')==false){
		document.forms[0].sklxdm.value=ysklx;
		doSubmitForm("Query");
	}else{
		ysklx= document.forms[0].sklxdm.value;
		doSubmitForm("Query");
	}
}*/

//��˰���仯ʱ������صĸ���˰��ʵ��
function computeRow1(obj){
	//if(checkSjseInput(obj)){
		if(isNum(obj,0,null,false)){
		//����ʵ��
		computeSjje(obj);
		//���㸽��˰���Ļ���ҵ�����
		var oRow = getObjRow(obj);
		if(oRow.all("sffjs").value!='2'){
			//����޸���Ϊ����˰�����¼��㸽��˰
			resetFjs();
			//�����Ļ�������ҵ��
			resetWhjssy();
		}else{
			//���¼���ϵͳ�ϼ�
			computeSameSum("sjse","xthj","SBJKMX");
			hj();
		}
	}

}
//������˰�ִ���
var cftszdm = ",12,89,11,88,15,76,";
//����ʵ�ɽ��
function computeSjje(obj){
	var oRow = getObjRow(obj);
	//�������Ʊ�ʾ
	var asljbs = oRow.all("asljbs").value;
	//��˰���
	var jsje = oRow.all("jsje").value;
	if(jsje=='') jsje=0;
	//˰��
	var sl = oRow.all("sl").value;
	if(sl=='') sl=0;
	//��˰����
	var jsjs = oRow.all("jsjs").value;
	//��˰����
	var kssl = oRow.all("kssl").value;
	//˰��˰Ŀ����
	var szsmdm = oRow.all("szsmdm").value;
	//20040408 Shi yanfeng
	//��ע�⣬�����������������˰���˰������ʱ��Ϊ���꣬����������˰��Ӧ��Ϊȫ���һ�롣
	var months=getBMonths(oRow.all("skssksrq").value,oRow.all("skssjsrq").value)+1;

	//if(szsmdm=='300000') alert(asljbs);
	//1.��ѡ�е�˰Ŀ����(szsmdm)��Ӧ��˰��˰Ŀ�����(dmdb.sb_dm_szsm)�е�asljbsΪ�յ�,ҳ�治�����㣻
	if(asljbs!='' && szsmdm!='120010' && szsmdm!='890001'){
		 //2.ѡ�е�˰Ŀ����(szsmdm)��Ӧ��˰��˰Ŀ�����(dmdb.sb_dm_szsm)�еġ�asljbs������ҳ���ʵ�ʽ�˰�ҳ��ļ�˰����dmdb.sb_dm_szsmdm.sl��
		if(asljbs=='0'){
			oRow.all("sjse").value=Math.round(parseFloat(jsje)*parseFloat(sl)*100)/100;
		}
		//3.ѡ�е�˰Ŀ����(szsmdm)��Ӧ��˰��˰Ŀ�����(dmdb.sb_dm_szsm)�еġ�asljbs�������ߣ�����ҳ���ʵ�ʽ�˰�ҳ��ļ�˰����dmdb.sb_dm_szsmdm.jsjs��
		if(asljbs=='1' || asljbs=='3'){
		//�������������ж��Ƿ���Ҫ�ı��˰����
		  if(oRow.all("skssksrq").value.substr(0,4)<2007&&oRow.all("szsmdm").value.substr(0,2)==15)
				 {
					 if(oRow.all("szsmdm").value==150010)
					   { jsjs=10; }
					 if(oRow.all("szsmdm").value==150020)
					   { jsjs=8;  }
           if(oRow.all("szsmdm").value==150030)
             { jsjs=6;  }
				   if(oRow.all("szsmdm").value==150040)
				     { jsjs=4;  }
					 if(oRow.all("szsmdm").value==150050)
					   {jsjs=1;  }
				   if(oRow.all("szsmdm").value==150060)
				     { jsjs=0.5;}
				   //oRow.all("sl").value=jsjs;
				}
			oRow.all("sjse").value=Math.round(parseFloat(jsje)*parseFloat(jsjs)*100)/100;
			//20040408 Shi yanfeng
			//��ע�⣬�����������������˰���˰������ʱ��Ϊ���꣬����������˰��Ӧ��Ϊȫ���һ�롣

			if(months==6 && cftszdm.indexOf(szsmdm.substr(0,2))>0){
				oRow.all("sjse").value=Math.round(oRow.all("sjse").value/2);
			}
		}
		//4.ѡ�е�˰Ŀ����(szsmdm)��Ӧ��˰��˰Ŀ�����(dmdb.sb_dm_szsm)�еġ�asljbs������ҳ���ʵ�ʽ�˰�ҳ��Ŀ�˰������dmdb.sb_dm_szsmdm.jsjs��
		if(asljbs=='2'){
			oRow.all("sjse").value=Math.round(parseFloat(kssl)*parseFloat(jsjs)*100)/100;
		}

	}else if(szsmdm=='120010' || szsmdm=='890001'){
		//���ѡ�����120010˰Ŀ�����ã���˰����70����������ٳ���˰�ʼ����ʵ�ʽ�˰�
		oRow.all("sjse").value=Math.round(parseFloat(jsje)*parseFloat(sl)*100*0.7)/100;
	}

}

//����ҳ��ʱ��������Ϊ���������¼��
// ҳ����뽹��ȷ��
function fnOnLoad()
{
    document.forms[0].jsjdm.focus();
}
//���������˺��б��ʱ�򣬻س�����һ��
function enterAddRow(){
	if(event.keyCode==13) {
		if(document.forms[0].jsjdm.value=='' || document.forms[0].nsrmc.value==''){
			return false;
		} else {
			addRow('SBJKMX',null,'szsmdm');
			return false;
		}
	}
}
//�ı��걨���ڵ�ʱ�����¸��ݼ���������ѯ
function changeDateQ(){
	if(document.forms[0].jsjdm.value!='' && document.forms[0].nsrmc.value!=''){
		doSubmitForm("Query");
	}
}

//������ǰ�м�����˰(����˰���벻�ɸ�,���ᴥ���˷���)
//�����ʷ������Ϊ1��2ʱֻ�е�ѡ��026,0277�µ�˰�֣����Ҹ�˰�ֵ�˰�ʲ�Ϊ5%ʱ�����535070��535610���������������������˰��
function setAdditions(nameHead,tableid,obj){
//	as("setAdditions");
	/*****�⼮����������ʾ���ִ����б�******/
	if(nameHead=="bzdm"){
		var values = get_subobj("bzlist",obj.value);
		var fields = get_obj("bzlist_fields");
		var row = getObjRow(obj);
				var oldCode = row.all("bzdm_old").value;
		//alert(row.rowIndex+" :"+obj.value+" :"+oldCode)
		//ֵδ��ʱ�����κβ���
		if(obj.value==oldCode) return;

	//���Ϸ�������ʹֵ���䣨����գ�values��ȫ������ֵ����return�������������������˰��
		if(!values){
			obj.value=oldCode;
		}
		setTheRow(fields,values,row);
		//����λ�ñ�־
		document.all(nameHead+"_focus").value = "0";
		//�ر�div
	  document.all(nameHead+"_epodDateLayer").style.display = 'none';
		tempSelect.srcObj.select();
		return;
	}
	/*****�⼮����������ʾ���ִ����б�******/
	var values = get_subobj("szsmlist",obj.value);
	var fields = get_obj("szsmlist_fields");
	var row = getObjRow(obj);
	//ԭ˰Ŀ����

	var oldCode = row.all("szsmdm_old").value;
		//alert(row.rowIndex+" :"+obj.value+" :"+oldCode)
		//ֵδ��ʱ�����κβ���
	if(obj.value==oldCode) return;

	//���Ϸ�������ʹֵ���䣨����գ�values��ȫ������ֵ����return�������������������˰��
	if(!values){
		obj.value=oldCode;
		return;
	}

	//���õ�ǰ��
	setTheRow(fields,values,row);
	//ɾ����˰Ŀ����
	delSzsmList(nameHead,obj.value)

	//����λ�ñ�־
	document.all(nameHead+"_focus").value = "0";
	//�ر�div
	document.all(nameHead+"_epodDateLayer").style.display = 'none';
	tempSelect.srcObj.select();


	if(!isadditions){
		delSonRow(nameHead,tableid,oldCode);
		return;
	}
	//��˰˰��
	var sl = row.all("sl").value;
	//��˰˰��˰Ŀ����
	var szsmdm = row.all("szsmdm").value;

	//���Ҹ���˰(szsmlist_add��˰��˰Ŀ����͸���˰�Ķ�Ӧ��ϵ)
	if(!iswz){
		//����������ҵ
		var list = get_subobjs("szsmlist_add",obj.value);//����˰����
		var addCode;
		for(var ii=0;ii<list.length;ii++){
			var addCode = list[ii][1];//����˰����
			if(findRowIndex(tableid,addCode)>=0) continue;
			var row = addSonRow(tableid);//����һ�У��������ж���
			var values = get_subobj("szsmlist",addCode);
			setTheRow(fields,values,row);
			//delSzsmList(addCode)//maybe not needed
		}
		//����˰Ŀ����ı�ʱ,�ж��Ƿ�ɾ��֮ǰ�ĸ���˰
		delSonRow(nameHead,tableid,oldCode);
	}else if((szsmdm.substr(0,3)=='026' || szsmdm.substr(0,4)=='0277') && sl!=0.05){
		//��������ҵ
		//�����ʷ������Ϊ1��2ʱֻ�е�ѡ��026,0277�µ�˰�֣����Ҹ�˰�ֵ�˰�ʲ�Ϊ5%ʱ�����535070��535610���������������������˰��.
		var list = get_subobjs("szsmlist_add",obj.value);//����˰����
		var addCode;
		for(var ii=0;ii<list.length;ii++){
			var addCode = list[ii][1];//����˰����
			//����535070��535610
			//alert(addCode)
			if(addCode!='535070' && addCode!='535610') continue;
			if(findRowIndex(tableid,addCode)>=0) continue;
			var row = addSonRow(tableid);//����һ�У��������ж���
			var values = get_subobj("szsmlist",addCode);
			setTheRow(fields,values,row);
			//delSzsmList(addCode)//maybe not needed
		}
		//����˰Ŀ����ı�ʱ,�ж��Ƿ�ɾ��֮ǰ�ĸ���˰
		delSonRow(nameHead,tableid,oldCode);

	}

}


//��д'../js/smsb_common.js'�е�tuchu()����  zzb  090407
	//�˳�
function tuichu(){
	if(returnStr==null || returnStr==""){
		//������˰
		returnStr="zhsbPgbsAction.do";
	}
	//��������ۺ��걨�����걨����ҳ�棬���˳����ۺ��걨ҳ��
	if(document.all.iszhsb && document.all.iszhsb.value=='1')
		//������˰
		returnStr="zhsbPgbsAction.do?actionType=Show";
	window.location=returnStr;
}
</script>


</body>
</html:html>
