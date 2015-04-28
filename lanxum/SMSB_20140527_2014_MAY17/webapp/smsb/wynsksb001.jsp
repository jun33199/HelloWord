<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html>
<head>
<title>��Ӧ��˰���ѣ����걨 </title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad();">
 <%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/wynsksbAction.do" method="POST" >

<html:hidden property="actionType" />
<html:hidden property="swjgzzjgdm"/>
<html:hidden property="sbbh"/>
<html:hidden property="wssbyynr"/><!-- add by tangchangfu 2014-04-08 ��˰����˰��Ŀ -->


      <table width="96%" align="center" cellspacing="0" class="table-99">
                <tr>
                  <td class="1-td1"  colspan="7">��Ӧ��˰���ѣ����걨 </td>
                </tr>

                <tr>
                  <td class="1-td2"  colspan="7">
				  <br>
				  <table cellspacing="0" class="table-99">
					<tr class="black9">
						<td align="left" nowrap>
							<div align="left">�걨���ڣ�
							<html:text size="8" maxLength="8" property="sbrq" tabIndex="1" onchange="setSkssrq(this)" onKeyDown="if(event.keyCode==13)	event.keyCode = 9;"/>
							</div>
						</td>
						<td align="center" nowrap>
							<div align="left">˰���������ڣ�
											 <html:text property="skssksrq" size="8" maxlength="8" tabIndex="2" onchange="isDate(this,false);" onKeyDown="if(event.keyCode==13)	event.keyCode = 9;"/>&nbsp;��&nbsp;
											 <html:text property="skssjsrq" size="8" maxlength="8" tabIndex="3" onchange="isDate(this,false);" onKeyDown="if(event.keyCode==13)	event.keyCode = 9;"/>
							</div>
						</td>
					</tr>
					</table>
				  <br>
				  <table  cellspacing="0" class="table-99">
					<tr> 
					  <td class="2-td2-t-left">���������</td>
					  <td class="2-td2-t-left"> <div align="left"><html:text property="jsjdm"   size="10" maxlength="10" onchange="if(this.value!='') doSubmitForm('Query');" onKeyDown="return jsjdmQuery();"/></div></td>
					  <td class="2-td2-t-left">��˰������</td>
					  <td class="2-td2-t-center"> <html:text property="nsrmc" tabIndex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/></td>
					</tr>
					
					<tr> 
					  <td class="2-td2-left">����˰�����</td>
					  <td class="2-td2-left"><html:text property="swjgzzjgmc" tabIndex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/></td>
					  <td class="2-td2-left">��ϵ�绰</td>
					  <td class="2-td2-center"><html:text property="zcdzlxdh" tabIndex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/></td>
					</tr>
					
					<tr>
					 	<td class="2-td2-left">��˰�걨ԭ��<font color="red">*</font></td>
						<td class="2-td2-center" colspan="3">
							<div align="left">
								<ttsoft:select property="wssbyydm" codekey="WSSB_YY" onchange="docheckSelected()" onkeydown="if(event.keyCode==13) event.keyCode=9;" /><br>
							</div>
						</td>

					</tr>
					<tr id="id_bz" style="display:none">
						<td class="2-td2-left">��ע</td>
						<td class="2-td2-center" colspan="3">
							<div align="left">
								<html:textarea property="bz" cols="80"  rows="6"></html:textarea>
							</div>
						</td>
					</tr>
				  </table>
                    <br>
                    
					<table width="100%" border="0" cellspacing="0" class="table-99" align="center" >
						<tr>
						  <td class="2-td1-left">�걨����</td>
						  <td class="2-td1-left">˰��������ʼ����</td>
						  <td class="2-td1-left">˰��������ֹ����</td>
						  <td class="2-td1-left">��˰�걨ԭ��</td>
						  <td class="2-td1-left">¼����</td>
						  <td class="2-td1-center">&nbsp;</td>
						</tr>
					<logic:iterate id="item" name="wynsksbActionForm" property="dataList" >
					<%
						com.ttsoft.bjtax.shenbao.model.domain.Wynsksb wynsksb = (com.ttsoft.bjtax.shenbao.model.domain.Wynsksb)item;
						String lrr = wynsksb.getLrrmc();
					if(lrr == null || "".equals(lrr)){
						lrr = wynsksb.getLrr();
						
					}
					 %>
						<tr>
						  <td class="2-td2-left">&nbsp;<%=com.ttsoft.bjtax.sfgl.common.util.SfDateUtil.getDate(wynsksb.getSbrq())%></td>
						  <td class="2-td2-left">&nbsp;<%=com.ttsoft.bjtax.sfgl.common.util.SfDateUtil.getDate(wynsksb.getSkssksrq())%></td>
						  <td class="2-td2-left">&nbsp;<%=com.ttsoft.bjtax.sfgl.common.util.SfDateUtil.getDate(wynsksb.getSkssjsrq())%></td>
						  <td class="2-td2-left">&nbsp;<%=wynsksb.getWssbyynr()%></td>
						  <td class="2-td2-left">&nbsp;<%=lrr%></td>
						  <td class="2-td2-center">&nbsp;<input type="image"  onClick="doDelete('<%=wynsksb.getSbbh()%>'); return false;"  onMouseOver="MM_swapImage('<%=wynsksb.getSbbh()%>','','<%=static_contextpath%>images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="ɾ��" id="<%=wynsksb.getSbbh()%>" src="<%=static_contextpath%>images/shanchu1.jpg" width="79" height="22"></td>
						</tr>
					</logic:iterate>
					  </table>
					<br>
                    <table width="94%" border="0" cellpadding="0" cellspacing="4">
                      <tr valign="bottom">
                        <td width="30%"> </td>
                        <td width="40%" align="center">
						<input type="image" accesskey="s"  onClick=" doSubmitCheck('Save'); return false;"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="����" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="21"> 
						&nbsp;&nbsp;&nbsp;
						
						<input type="image" accesskey="c"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="21">
					  </td>
                        <td width="30%"></td>
                      </tr>
                    </table>
					<table width="94%" border="0" cellpadding="0" cellspacing="0">
                <tr valign="top"></tr>
                <td colspan="7"> <ul>
                    <li id="foldheader" style="list-style-image:url('<%=static_contextpath%>images/zbotton-jia2.gif')"> 
                      �걨����¼��</li>
                    <ul id="foldinglist" style="display:none">
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">1 
                        <a href="javascript:toSbzl('qysdsjbAction.do?actionType=Query','o')">��ҵ����˰������˰�걨��</a></li>
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">2 
                        <a href="javascript:toSbzl('qysdsnbAction.do?actionType=Query','y')">��ҵ����˰����걨��</a></li>
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">3 
                        <a href="javascript:toSbzl('qyjbcwzbAction.do?actionType=Query','m')">��ҵ��������ָ�������</a></li>
                      <li id="foldheader" style="list-style-image:url('<%=static_contextpath%>images/zbotton-jia2.gif')">4 
                        ����Ӫҵ˰�걨��</li>
                      <ul id="foldinglist" style="display:none">
                        <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')"><a href="javascript:toSbzl('wqyysassbAction.do?actionType=Query','q')"> 
                          ��ʵ�걨</a></li>
                        <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')"><a href="javascript:toSbzl('wqyyshdzsAction.do?actionType=Query','q')"> 
                          �˶�����</a></li>
                        <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')"><a href="javascript:toSbzl('wqyysjfhsAction.do?actionType=Query','q')"> 
                          ���ѻ���</a></li>
                      </ul>
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">5 
                        <a href="javascript:toSbzl('yhsAction.do?actionType=Query','y')">ӡ��˰����걨��</a></li>
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">6 
                        <a href="javascript:toSbzl('dsdzdkAction.do?actionType=Query','m')">���۴��������걨</a></li>
                      <li id="foldheader" style="list-style-image:url('<%=static_contextpath%>images/zbotton-jia2.gif')">7 
                        ���˶��ʸ��˺ϻ��������˰�걨��</li>
                      <ul id="foldinglist" style="display:none">
                        <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')"><a href="javascript:toSbzl('czzsjdAction.do?actionType=Query','o')">�������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰�����걨��</a></li>
                       <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">
                        <a href="javascript:toSbzl('czzsnbAction.do?actionType=Query','y')">�������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰�걨</a></li>
                      
                      </ul>
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">8 
                        <a href="javascript:toSbzl('jmssbAction.do?actionType=Query')">����˰�걨����</a></li>
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">9 
                        <a href="javascript:toSbzl('gtgshsdsAction.do?actionType=Query','y')">�������ո��幤�̻�����˰�����(�·�)�걨��</a></li>
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">10 
                        <a href="javascript:toSbzl('gtgshyysrAction.do?actionType=Query','m')">���幤�̻�Ӫҵ�걨��</a></li>
						<li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">11 
                        <a href="javascript:toSbzl('sydwshttsrsbAction.do?actionType=Query','y')">��ҵ����˰_��ҵ��λ��������������걨��</a></li>
					
						
                    </ul>
                  </ul></td>
              </table>
	     </td>
		</tr>
	  </table>


<%@ include file="./include/footer.jsp"%>
    </td>
  </tr>
</table>
</html:form>
<script language="JavaScript">
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

document.onclick=change;
function doSubmitCheck(ope){
	if (document.forms[0].nsrmc.value!=""){
		//add by tangchanhgfu 2014-04-08 ��˰����˰��Ŀ ����Ǳ��棬��У��������걨ԭ��ѡ��Ϊ������������Ҫ��ע��������д����
		if(ope == "Save"){
			if(!docheckSelected()){
				return false;
			}
		}
		doSubmitForm(ope);
	}
}

//��Ӧ���������Ļس���ѯ
function jsjdmQuery(){
	
//	if(event.keyCode==13) {		event.keyCode = 9;	}
	if(event.keyCode==13) {
		doSubmitForm('Query');
		return false;
	}
	return true;
}

//����ҳ��ʱ��������Ϊ���������¼��
// ҳ����뽹��ȷ��
function fnOnLoad(){
    document.forms[0].jsjdm.focus();
    docheckSelected();
    return false;
}

/****�������˰��Ϊ��������������ʾ��ʾ��Ϣ****/
/****20040216 Shi Yanfeng****/
var nsrzt = "<ttsoft:write name="wynsksbActionForm" property="nsrzt" filter="false"/>";

checkNsrzt();

function doDelete(sbbh){
	document.all.sbbh.value=sbbh;
	doSubmitForm("Delete");
}

//ת�Ƶ��걨����ʹ�õ�˰����������
<ttsoft:write name="wynsksbActionForm" property="skssrqArr" filter="false"/>

//����ת���걨����֮ǰ��������ʾ�Ƿ񱣴��Ѿ�¼������
function toSbzl(returnStr,type)
{
	if((document.forms[0].jsjdm.value!='' && document.forms[0].nsrmc.value!='' && document.forms[0].sbrq.value!='')){
		//var truthBeTold = window.confirm("�ò������޷�������¼������,��ȷ��");
		if( true){
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

		returnStr+="&jsjdm="+document.forms[0].jsjdm.value+"&sbrq="+document.forms[0].sbrq.value+"&lrrq="+document.forms[0].sbrq.value+ssrq;	
			window.location=returnStr;    
		}
	}else{
		alert('��ȷ���Ѿ�¼���걨���ں�˰����������');
	}
}
//
function setSkssrq(sbrq){
	//getStartEndDate(sbrq,document.forms[0].skssksrq,document.forms[0].skssjsrq,2);
	getSkssrqMonth(sbrq,document.forms[0].skssksrq,document.forms[0].skssjsrq);
}
function getSkssrqMonth(oInput1,oInput2,oInput3){
	if(isDate(oInput1,"v")){
		var date_start,date_end;
		var year,mon,days;

		var inputDate = oInput1.value;

		year = inputDate.substring(0,4);
		mon = inputDate.substring(4,6);
		days = inputDate.substring(6,8);

		var date2 = new Date(year,mon-1,-1);
		days = date2.getDate()+1;
		year = date2.getFullYear();
		mon = date2.getMonth()+1;

		date_start = year+""+formatMon(mon)+"01";
		date_end = year+""+formatMon(mon)+days;

		oInput2.value = date_start;
		oInput3.value = date_end;
	}
}

/**
 * add by tangchangfu 2014-04-08 ��˰����˰��Ŀ
 */
function docheckSelected(){
	var trObj_bz = document.all.id_bz;
	var bzobj = document.forms[0].bz;//��ע��Ϣ
	var objVal = document.all.wssbyydm.value;//��˰�걨����
	
	
	//���ѡ�����˰�걨ԭ��Ϊ������������Ҫ�����¼�뱸ע��Ϣ
	if(objVal != "" && objVal =="99"){
		//��ʾ��ע��
		trObj_bz.style.display='';
		if(replaceBlank(bzobj.value)==""){
			alert("��ѡ�����˰�걨ԭ��Ϊ������������д��ע˵����ע��������100�֣���");
			//���㶨λ����ע��
			bzobj.focus();
			return false;
		}else{
			if(replaceBlank(bzobj.value).length>100){
				alert("��ע��Ϣ�����ܳ���100���֣�");
				bzobj.focus();
				return false;
			}
			bzobj.value = replaceBlank(bzobj.value);
		}
	}else{
		//ѡ���ǡ��������������ر�ע��
		trObj_bz.style.display='none';
		bzobj.value="";
	}
	return true;
}

/**
 * ��������ʽȥ���ո�
 *add by tangchangfu 2014-04-08 ��˰����˰��Ŀ
 */
function replaceBlank(Val)
	{
	    // ��������ʽ��ǰ��ո�
	    // �ÿ��ַ��������
	    return Val.replace(/(^\s*)|(\s*$)/g, "");
	}
</script>


</body>
</html:html>