<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@page import="com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.MfskzsForm"%>

<HTML><HEAD><TITLE>����˰������</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
</HEAD>


<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0" onload="checkFwyz('');init()">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="��˰�걨&gt;���������׹���&gt;����˰������"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<SCRIPT language=javascript>

</SCRIPT>
<br>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>����˰������</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/clfgl/mfskzs.do" type="POST">
            <html:hidden property="operationType"/>
            <html:hidden property="nsrmc" name ='mfskzsForm'/>
            <html:hidden name="mfskzsForm" property="hasHdxx"/><!-- �Ƿ��к˶���Ϣ -->
            <html:hidden name="mfskzsForm" property="fwhdlxdm"/> <!--���ݺ˶����ʹ��� -->
            <html:hidden  property="scriptStr" />
            <input type="hidden" name="tdzzssl" value='<bean:write name="mfskzsForm" property="tdzzssl"/>'> <!-- ������ֵ˰˰�� -->
            <input type="hidden" name="tfrq" value='<bean:write name="mfskzsForm" property="tfrq"/>'>
            <input type="hidden" name="zsjg" value='<bean:write name="mfskzsForm" property="zsjg"/>'>
            <input type="hidden" name="sbbh" value='<bean:write name="mfskzsForm" property="sbbh"/>'>
			<input type="hidden" name="sbbh_his" value='<bean:write name="mfskzsForm" property="sbbh_his"/>'>
			<input type="hidden" name="htbh1" value='<bean:write name="mfskzsForm" property="htbh1"/>'>	
			<input type="hidden" name="htwsqyrq" value='<bean:write name="mfskzsForm" property="htwsqyrq"/>'>
			<input type="hidden" name="rqcs" value='<bean:write name="mfskzsForm" property="rqcs"/>'>
             <TABLE border="0" cellSpacing=0 class=table-99>
              <TBODY>
							 <tr>
                <td class="2-td2-t-left" nowrap="nowrap" width="25%">�����</td>
                <td class="2-td2-t-left" width="25%">
                    <div align=left>
						<bean:write name="mfskzsForm" property="tfrq" />
					</div>
                </td>
                <td class="2-td2-t-left" nowrap="nowrap" width="25%">���ջ���</td>
                <td class="2-td2-t-center" nowrap="nowrap" width="25%">
                    <div align=left>
						<bean:write name="mfskzsForm" property="zsjg" />
					</div>
                </td>
							 </tr>
               <tr>
                <td class="2-td2-left" nowrap="nowrap">��ͬ���</td>
                <td class="2-td2-left" nowrap="nowrap">
                    <div align=left>
						<html:text property="htbh" size="20" maxlength="20"/>
					</div>
                </td>
                <td class="2-td2-center" nowrap="nowrap" colspan="2">
					<div align=left>
      <DIV align=center>
      <IMG name="query"
          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
          onclick = "javascript:if(document.forms[0].htbh.value==''){ return false;} else {doSubmitForm('QueryGr');return false;}"
          width="79" height="22" id="chaxun1" alt="��ѯ" style="cursor:hand">
      </DIV>						
					</div>
				       </td>
							 </tr>				       
						<tr>
                <td class="2-td2-left" nowrap="nowrap">��˰������</td>
                <td class="2-td2-left">
                    <div align=left>
						   &nbsp;<bean:write name="mfskzsForm" property="nsrmc"/>
					</div>
                </td>
                <td class="2-td2-left" nowrap="nowrap">֤������</td>
                <td class="2-td2-center">
                    <div align=left>
						  &nbsp;<bean:write name="mfskzsForm" property="zjhm"/>
					</div>
                </td>				       
              </tr>
              							 </tr>				       
						<tr>
				<td class="2-td2-left" nowrap="nowrap">���ݺ˶�����</td>
                <td colspan="3" class="2-td2-center" nowrap="nowrap">
					<div align="center">���˶�Ϊס��&nbsp;<input type="radio" name="fwhdlx" value="<%=Constants.FWHDLX_HOUSING%>" disabled="disabled" onclick=""/>
                	&nbsp;���˶�Ϊ��ס��&nbsp;<input type="radio" name="fwhdlx" value="<%=Constants.FWHDLX_NONHOUSING%>" disabled="disabled" onclick=""/>
                	</div>
                </td>			       
              </tr>
     </TABLE>
       <br>
				<div id="Layer2" style="position:static; overflow: auto;  width: 860px; height: 120px">
					<TABLE class=table-99 cellSpacing=0 align=center id="sbxxHis">
              <TBODY>
              <TR>
                <TD colspan="11">--------------�����ϴν���˰����Ϣ--------------</TD></TR>
       <TR>
       	<TD class="2-td1-left" height="28" noWrap>��˰������</TD>
        <TD class="2-td1-left" noWrap>֤������</TD>
        <TD class="2-td1-left" noWrap>���ز�λ��</TD>
        <TD class="2-td1-left" noWrap>��˰���</TD>
        <TD class="2-td1-left" noWrap>���ز�Ȩ��ת�����</TD>
        <TD class="2-td1-left" noWrap>����Ȩ��ת������</TD>
        <TD class="2-td1-left" noWrap>��ͬǩ������</TD>       
        <TD class="2-td1-left" noWrap>��������Ȩ֤��</TD>        
        <TD class="2-td1-left" noWrap>֤������</TD>        
        <TD class="2-td1-left" noWrap>�걨����</TD>
        <TD class="2-td1-left" noWrap>˰��</TD>
        <TD class="2-td1-center" noWrap>Ӧ�ɽ��</TD>
      </TR>
      <logic:iterate id="dataList" name="mfskzsForm" property="sbxxHisList" indexId="index">         
      <TR>
       	<TD class="2-td1-left" height="28" noWrap><bean:write name="dataList" property="nsrmc_his"/></TD>
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="sfzjhm_his"/></TD>
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="tdfwzldz_his"/></TD>
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="jsje_his"/></TD>
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="fwjzmj_his"/></TD>        	
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="fwqszylx_his"/></TD>        	
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="htqdsj_his"/></TD>        	
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="fwsyqzh_his"  filter="false"/></TD>
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="sfzjlxmc_his"/></TD>        	
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="sbrq_his"/></TD>
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="sl_his"/></TD>
        <TD class="2-td1-center" noWrap><bean:write name="dataList" property="ynse_his"/></TD>
      </TR> 
      </logic:iterate>           
							</TBODY>
						</TABLE>
					</div>
          <br>
          <table>
          	<tr>
          		<td colspan="8">-------------------������Ϣ¼��------------------</td>
          	</tr>
          </table>
          	
		  <table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0" id="CLFJKMX"  onkeydown=dokeydown(this.id,'szsmdm')  onmouseup='return dokeyUp(this.id)'>
			<ttsoft:smsbtable form="mfskzsForm" property="dataList" tableId="CLFJKMX" hasTitle="true"/>
			<DIV id=divSfTemp></DIV>
              <tr>
                <td nowrap class="2-td2-left">�ϼ�</td>
                <td nowrap class="2-td2-left" colspan="5">&nbsp;</td>
                <td nowrap class="2-td2-left"><html:text property="hjjsje" readonly="true" onkeydown="if(event.keyCode==13) event.keyCode=9;" size="12" styleClass="inputnoborder" /></td>
                <td nowrap class="2-td2-left"><html:text property="hjsjse" readonly="true" onkeydown="if(event.keyCode==13) event.keyCode=9;" size="12" styleClass="inputnoborder" /></td>
                <td nowrap class="2-td2-left"><html:text property="hjjmje" readonly="true" onkeydown="if(event.keyCode==13) event.keyCode=9;" size="12" styleClass="inputnoborder" /></td>
                <td nowrap class="2-td2-center"><html:text property="hjyjje" readonly="true" onkeydown="if(event.keyCode==13) event.keyCode=9;" size="12" styleClass="inputnoborder" /></td>
              </tr>
           </table>
           <table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0">
          	<tr id="jwyzId" >
              	<td nowrap class="2-td2-left" width="17%">&nbsp;��ί��ѯ����ԭֵ</td>
                <td nowrap class="2-td2-center" ><div align="left"> &nbsp;<html:text property="fwyz" size="20" maxlength="20" onchange="publicMethod()"/></div></td>
             </tr>
          </table>
							
										<BR>
										<BR>
										<BR>
      		<DIV align=center>
      			<img onClick="javascript:if(document.forms[0].htbh.value!=document.forms[0].htbh1.value || document.forms[0].htbh1.value==''){ return false;} else {befAdd('Add');return false;}" alt="����" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('zengjia','','<%=static_file%>images/tianjia2.jpg',1)"  src="<%=static_file%>images/tianjia1.jpg" name="zengjia" width="79" height="22" border="0" id="zengjia" >&nbsp;
      			<img onClick="befSave('Save');return false;" alt="����" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"  src="<%=static_file%>images/baocun1.jpg" name="baocun" width="79" height="22" border="0" id="baocun" >&nbsp;
       			<img onClick="befDel();return false;" alt="ɾ��" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('shanchu','','<%=static_file%>images/shanchu2.jpg',1)"  src="<%=static_file%>images/shanchu1.jpg" name="shanchu" width="79" height="22" border="0" id="shanchu" >&nbsp;
       			<img onclick="doSubmitForm('toClfswjghdxxlr');return false;" alt="����"  onMouseOver="MM_swapImage('fanhui1','','<%=static_file%>images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/fanhui1.jpg" name="back" width="79" height="22" id="fanhui1"  style="cursor:hand">
       			<img onclick="doSubmitForm('Return');return false;" alt="�˳�"  onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">       			
      			<BR>
      			<img onClick="doSubmitForm('toWhsbxx');return false;" alt="ά���걨��Ϣ" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('whsbxx','','<%=static_file%>images/whsbxx2.jpg',1)"  src="<%=static_file%>images/whsbxx1.jpg" name="whsbxx" width="79" height="22" border="0" id="whsbxx" >&nbsp;
       			<img onClick="doSubmitForm('toFpdk');return false;" alt="ת��Ʊ����" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('zfpdk','','<%=static_file%>images/fpdk2.jpg',1)"  src="<%=static_file%>images/fpdk1.jpg" name="zfpdk" width="100" height="22" border="0" id="zfpdk" >&nbsp;
       			<img onClick="doSubmitForm('toAddSbGr');return false;" alt="ת��˰�걨" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('zqssb','','<%=static_file%>images/qssb2.jpg',1)"  src="<%=static_file%>images/qssb1.jpg" name="zqssb" width="100" height="22" border="0" id="zqssb" >&nbsp;
      			<img onClick="doSubmitForm('toClfxxcj');return false;" alt="ת��������Ϣ�ɼ�" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('zclfxxcj','','<%=static_file%>images/clfxxcj2.jpg',1)"  src="<%=static_file%>images/clfxxcj1.jpg" name="zclfxxcj" width="130" height="22" border="0" id="zclfxxcj" >&nbsp;
      			
      		</DIV>

<div id=szsmdm_epodDateLayer style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" ><select id=sel onclick=selectClick("szsmdm","CLFJKMX") onkeyup=selectMove("szsmdm","CLFJKMX")  onfocusout=selectClick("szsmdm","CLFJKMX") onkeydown=if(window.event.keyCode==13)selectClick("szsmdm","CLFJKMX")></select></div>

<INPUT TYPE="hidden" NAME="szsmdm_focus">
      		
</html:form>
<%@ include file="../include/BottomMF.jsp" %>

<script language=JavaScript type='text/JavaScript'>

var szsmdm_value ="";
var szsmdm_hd_value ="";
//alert(szsmdm_value.length +" �� "+szsmdm_hd_value.length);
function doSubmitForm(operationType)
{
	if(operationType=="Save")
	{
		if (!confirm("���Ĳ����ǣ������桱���Ƿ������"))
    	return false;
	}
	
	var htbhValue = document.forms[0].htbh.value;
	if(htbhValue != "")
	{
		if(operationType=="toWhsbxx")
		{
			document.forms[0].action="/qsgl/clfgl/whsbxx.do?operationType=QuerySbxx&htbh ='"+htbhValue+"'&htbh1 ='"+htbhValue+"'";
			document.forms[0].submit();
			return false;
		}
		if(operationType=="toFpdk")
		{
			document.forms[0].action="/qsgl/fpgl/fpdk.do?operationType=Query&htbh ='"+htbhValue+"'";
			document.forms[0].submit();
			return false;
		}
	}
	else
	{
		if(operationType=="toWhsbxx")
		{
			alert("��������Ӧ�Ĳ�ѯ������");
			document.forms[0].htbh.focus();
			return false;
		}
	}
	
	
    document.all.operationType.value = operationType;
    document.forms[0].submit();
}
</script>
<script language=JavaScript >
var CLFJKMX_list=new DTable(CLFJKMX,jsArr_CLFJKMX);
CLFJKMX_list.tableTail=1;

//The conditions before action.
function befAdd(type){

	if(document.all.fwhdlxdm.value==""||document.all.fwhdlxdm.value==null){
		alert("���ݺ˶�����Ϊ�գ���ִ�в�ѯ������");
		return false;
	}
	//��ý�ί��ѯ����ԭֵ
	if (!checkFwyz(type))
	{
		return false;
	}
	tableid="CLFJKMX";
	var rows = eval(tableid).rows;
	//alert(rows.length+"<>hhhh");
	if (rows.length<16){
		addRow('CLFJKMX',null,'szsmdm');
	}
}

function befDel(){
	tableid="CLFJKMX";
	var rows = eval(tableid).rows;
	//alert(rows.length+"<>hhhh");
	if (rows.length>2){
		deleteRow('CLFJKMX',null,'szsmdm')
	}
}

//������˰֤¼������4-->5 Add By TuJunbing @@2012-7-13
function befSave(actionFlag){
	//�жϸ�������˰���շ�ʽ�Ƿ���ȷ
	if (!checkgrsdszsfs())
	{
		return false;
	}
	tableid="CLFJKMX";
	var rows = eval(tableid).rows;
	//alert(rows.length+"<>hhhh");
	if (rows.length>16) {
		alert("һ��¼�벻�ܶ���14����Ϣ��");
	}
	if (rows.length>2 && rows.length< 17){
		//doSubmitForm(actionFlag);
		doSubmitCheck(actionFlag);
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
			var obj_value = def_obj.value;
			//alert(obj_value);
			//if(obj_value!="1" && obj_value!="2"){
			/*if(obj_value.indexOf("11")!=0 && obj_value.indexOf("88")!=0){
				obj.readOnly = true;
				obj.className = "inputnoborder";
				cnt_obj.focus();
			}
			//if (obj_value=="1" || obj_value=="2"){
			else{
				obj.className = "inputalignright";
			}*/
		}	
	}
}



function fnOnLoad(){
    //document.forms[0].nsrmc.focus();
    if(szsmdm_value.length !=0)
    {
    	var szsmdm=szsmdm_value.length;
    	//alert(szsmdm);
    	//alert(szsmdm_value);
    	//befAdd();;
    	//alert("CLFJKMX.rows.length:"+CLFJKMX.rows.length);
    	//ϵͳ�ж�����¼��Ӫҵ˰
    	if(CLFJKMX.rows.length > 2 )
    	{
    		var szsmTemp = CLFJKMX.rows[1].all("szsmdm").value
    		szsmTemp = szsmTemp.substr(0,2);
    		if( szsmTemp !="02")
    		{
    			alert("Ϊȷ��˰�������ȷ�ԣ�������¼�롰Ӫҵ˰��˰��˰Ŀ��");
    			deleteRow1('CLFJKMX',null,'szsmdm');
    		}
    	}
    }
    
}

//��鲢�ύ����Ҫ����걨���ڡ�˰����������
//��˰��Ŀ���롢˰���������ڡ���˰��������˰��ʵ�ʽ�˰��
function doSubmitCheck(ope){
//	tableid="sbxxHis";
//	var sbxxnum = eval(tableid).rows;
		
	//���ÿһ�е�ֵ�Ƿ�Ϸ�
	for(var ii=1;ii<CLFJKMX.rows.length-1;ii++){
		var checkSzsmdm = "";
		var checkSkssjsrq = "";	
		if(CLFJKMX.rows[ii].all("szsmdm")) {	
			var szsmdmVal = CLFJKMX.rows[ii].all("szsmdm").value;
			if(szsmdmVal==""){
				alert("˰��˰Ŀ���벻��Ϊ��");	
				CLFJKMX.rows[ii].all("szsmdm").focus();
				return false;
			}
			else{
				checkSzsmdm = szsmdmVal;			
			}	
		}			
		if(CLFJKMX.rows[ii].all("skssksrq") && !isDate(CLFJKMX.rows[ii].all("skssksrq"),false)) {
			alert("˰��������ʼ���ڲ��Ϸ���");		
			return false;
		}
		if(CLFJKMX.rows[ii].all("skssjsrq") && !isDate(CLFJKMX.rows[ii].all("skssjsrq"),false)) {
			alert("˰��������ֹ���ڲ��Ϸ���");		
			return false;
		}
		//��ʼ����ҪС�ڽ�ֹ����
		if(compare(CLFJKMX.rows[ii].all("skssksrq"),CLFJKMX.rows[ii].all("skssjsrq"))<0) {
			alert("��ʼ���ڱ���С�ڻ���ڽ�ֹ���ڣ�");
			return false;
		}
		
		//2007���������ʹ��˰�¾�˰���ν�
		//������2007-1-1�����������ʹ��˰˰�������2007-1-1�պ������˰�֮ǰ���þ�˰��
		if(CLFJKMX.rows[ii].all("szsmdm")&&CLFJKMX.rows[ii].all("szsmdm").value.substr(0,2)=="15"){
			if(CLFJKMX.rows[ii].all("skssksrq")&&CLFJKMX.rows[ii].all("skssjsrq")){
				var ksnd=CLFJKMX.rows[ii].all("skssksrq").value.substr(0,4);
				var jsnd=CLFJKMX.rows[ii].all("skssjsrq").value.substr(0,4);
				if(ksnd<=2006&&jsnd>2006){
					alert("2007��1��1�����������ʹ��˰˰�����Ϊԭ�����������뽫˰�������ڲ��Ϊ��\n"+
					CLFJKMX.rows[ii].all("skssksrq").value+"-20061231\n"+
					"20070101-"+CLFJKMX.rows[ii].all("skssjsrq").value+"\n"+
					"��������˰֤����¼�룡");
					CLFJKMX.rows[ii].all("skssjsrq").value="20061231";
					CLFJKMX.rows[ii].all("skssjsrq").focus();
					return false;
				}
			}
		}
		//��˰������Ϊ��ʱ����Ϊ�Ϸ�������
		if(CLFJKMX.rows[ii].all("kssl") &&CLFJKMX.rows[ii].all("kssl").value!=null&& !isNum(CLFJKMX.rows[ii].all("kssl"),0)) {
			alert("��˰������Ϊ��ʱ����Ϊ�Ϸ���������");		
			return false;
		}
		var obj_name=CLFJKMX.rows[ii].all("szsmdm");
		var obj_value = obj_name.value;
		if(CLFJKMX.rows[ii].all("jsje") && !isNum(CLFJKMX.rows[ii].all("jsje"),0,null,true)) 
		{
			alert("��˰���Ϸ���");		
			return false;
		}
		if(CLFJKMX.rows[ii].all("sjse") && !isNum(CLFJKMX.rows[ii].all("sjse"),0,null,true)) 
		{
			alert("Ӧ�ɽ��Ϸ���");		
			return false;
		}
		if(CLFJKMX.rows[ii].all("jmje") && !isNum(CLFJKMX.rows[ii].all("jmje"),0,null,true)) 
		{
			alert("������Ϸ���");		
			return false;
		}
		if(CLFJKMX.rows[ii].all("yjje") && !isNum(CLFJKMX.rows[ii].all("yjje"),0,null,true)) 
		{
			alert("ʵ�ɽ��Ϸ���");		
			return false;
		}

		//����в�ѯ�������ϴν���˰����Ϣ,��������������˰,ֻ��ѡ��:050801 ����ת������(��20%˰��)
//		if(sbxxnum.length>2 && CLFJKMX.rows[ii].all("szsmdm").value.substr(0,2)=="05")
//		{
//			 if(CLFJKMX.rows[ii].all("szsmdm").value!="050801")
//			 {
//			 	alert("�������ϴν��׼۸񣬸�������˰ֻ�ܰ��շ���ת������(��20%˰��)���գ�");	
//			 	return false;
//			 }
//		}
	}
	//����Ƿ���δ¼���˰Ŀ��added by zhangj
	if(!checkNsxmdm()){
		return false;
	}
	doSubmitForm(ope);
}


//�����б�ʹ�õ�js����
<ttsoft:write name="mfskzsForm" property="scriptStr" filter="false"/>

//�Ƿ���ʵ����˰��־
isadditions = true;

function publicMethod(){
	//alert("publicmethod");
	//resetFjs();
	//���㸽��˰Ϊ�Ļ���ҵ�����
	//resetWhjssy();
	//�����趨�����༭��
	//changePrope();

	computePrope();
	getYYSsYjjehj();
	computeYhs();
	recomputeTdzzs();
	getOutGrsdsYjjehj();
	recomputeGrsds();
}


//�����������
function checkSjseInput(obj){	
	var obj1 = eval(obj);
	
	var oRow = getObjRow(obj);	
	//������޸���Ϊ����˰�����¼���
	if(oRow.all("sffjs").value=='2'){ 
		//���¼���ϵͳ�ϼ�
		computeSameSum("jsje","hjjsje","CLFJKMX");
		computeSameSum("sjse","hjsjse","CLFJKMX");
		computeSameSum("jmje","hjjmje","CLFJKMX");
		computeSameSum("yjje","hjyjje","CLFJKMX");
		hj();
		return false;
	}

	return true;
}


hjArray[0]=new Array("jsje","hjjsje","CLFJKMX");
hjArray[1]=new Array("sjse","hjsjse","CLFJKMX");
hjArray[2]=new Array("jmje","hjjmje","CLFJKMX");
hjArray[3]=new Array("yjje","hjyjje","CLFJKMX");
//���ϼ�������
function initHj(){
	document.forms[0].hjjsje.value='0.00';
	document.forms[0].hjsjse.value='0.00';
	document.forms[0].hjjmje.value='0.00';	
	document.forms[0].hjyjje.value='0.00';
}
initHj();


//��˰���仯ʱ������صĸ���˰��ʵ��
function computeRow1(obj){	
	if(checkSjseInput(obj)){		
		//����ʵ��
		//computeSjje(obj);
		//���㸽��˰���Ļ���ҵ�����
	    //resetFjs();
		//�����Ļ�������ҵ��
		//resetWhjssy();
		//�����趨�����༭��
		//changePrope();

		computePrope();
		getYYSsYjjehj();
		computeYhs();
		recomputeTdzzs();
		getOutGrsdsYjjehj();
		recomputeGrsds();
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

//���������༭��
function changePrope()
{
	//tableid="CLFJKMX";
	//var rows = eval(tableid).rows;
	for(var ii=1;ii<CLFJKMX.rows.length-1;ii++)
	{
		//var obj = rows[ii].all("sffjs");
		//if(!obj) continue;
		var szsmStr = CLFJKMX.rows[ii].all("szsmdm").value;
		var jsje = CLFJKMX.rows[ii].all("jsje");//��˰���
		var sjse = CLFJKMX.rows[ii].all("sjse");//Ӧ�ɽ��
		var jmje = CLFJKMX.rows[ii].all("jmje");//������
		var yjje = CLFJKMX.rows[ii].all("yjje");//ʵ�ɽ��
		//alert(szsmStr+" ��˰��"+jsje.readOnly+" Ӧ�ɽ�"+sjse.readOnly+" �����"+jmje.readOnly+" ʵ�ɽ�"+yjje.readOnly);
		//�������'080040'���򲻿ɱ༭
		if(szsmStr == '080040')
		{
			jsje.removeAttribute('readOnly');
			jsje.className = "inputalignright";
			
			sjse.removeAttribute('readOnly');
			sjse.className = "inputalignright";
			
			jmje.removeAttribute('readOnly');
			jmje.className = "inputalignright";
			
			yjje.removeAttribute('readOnly');
			yjje.className = "inputalignright";
		}
	}
	//���¼���ϵͳ�ϼ�
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
}

//����Ӫҵ˰������˰����������˰��������ֵ˰˰��
function computePrope()
{
	//tableid="CLFJKMX";
	//var rows = eval(tableid).rows;
	var sbbh = document.forms[0].sbbh.value;
	
	var hdjsje = changeJE(0.00);
	var ygffpje = changeJE(0.00);
	var cjssl = changeJE(0.00);
	
	//��ú˶���Ϣ
	var hdxxArr = new Array();
    if(szsmdm_hd_value.length !=0)
    {
		hdxxArr = getHdxx(szsmdm_hd_value,sbbh);

		//�Ƿ��ҵ�
		if(hdxxArr.length != 0)
		{
			//alert(tsnyArr);
			var sbbh = hdxxArr[0][0];
			var htbh = hdxxArr[0][1];					
			var yyszsfs = hdxxArr[0][2];
			hdjsje = hdxxArr[0][5];
			ygffpje = hdxxArr[0][9];
			cjssl = hdxxArr[0][15];
		}
	}
	for(var ii=1;ii<CLFJKMX.rows.length-1;ii++)
	{
		//var obj = rows[ii].all("sffjs");
		//if(!obj) continue;
		var szsmdm = CLFJKMX.rows[ii].all("szsmdm").value;
		var jsje = CLFJKMX.rows[ii].all("jsje");//��˰���
		var sjse = CLFJKMX.rows[ii].all("sjse");//Ӧ�ɽ��
		var jmje = CLFJKMX.rows[ii].all("jmje");//������
		var yjje = CLFJKMX.rows[ii].all("yjje");//ʵ�ɽ��
		var sl = CLFJKMX.rows[ii].all("sl").value; //˰��
		var kssl = CLFJKMX.rows[ii].all("kssl");//��˰����
				
		//alert("sbbh:"+sbbh+" htbh��"+htbh+" yyszsfs��"+yyszsfs+" grsdszsfs��"+grsdszsfs+" tdzzszsfs��"+tdzzszsfs+" hdjsje��"+hdjsje+" qdfcqsje��"+qdfcqsje+" qdfcyhsje��"+qdfcyhsje+" hlfy��"+hlfy+" ygffpje��"+ygffpje);
		//alert(sbbh);
		
		var szsmStr;
		szsmStr = szsmdm.substr(0,2);
		//Ӫҵ˰
		if(szsmStr=='02')
		{
			getYysInfo(yyszsfs,hdjsje,ygffpje,jsje,sjse,jmje,yjje,sl);
			//document.all("htbh").focus();
		}
		//Ӫҵ˰����˰
		var fjsbz = CLFJKMX.rows[ii].all("sffjs").value;
		if(fjsbz=="2" && szsmStr!='53')
		{
			countFjs(fjsbz,szsmStr,yyszsfs,CLFJKMX.rows,ii,cjssl);
		}
	}

	//���¼���ϵͳ�ϼ�
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
}

//�����������˰
function recomputeGrsds()
{
	//tableid="CLFJKMX";
	//var rows = eval(tableid).rows;
	var hdjsje = changeJE(0.00);
	var qdfcqsje = changeJE(0.00);
	var qdfcyhsje = changeJE(0.00);
	var hlfy = changeJE(0.00);
	var ygffpje = changeJE(0.00);
	var htzj = changeJE(0.00);
	var fwhdlxdm="";
	var sbbh = document.forms[0].sbbh.value;

	//��ú˶���Ϣ
	var hdxxArr = new Array();
    if(szsmdm_hd_value.length !=0)
    {
		hdxxArr = getHdxx(szsmdm_hd_value,sbbh);

		//�Ƿ��ҵ�
		if(hdxxArr.length != 0)
		{
			//alert(tsnyArr);
			var sbbh = hdxxArr[0][0];
			var htbh = hdxxArr[0][1];					
			var grsdszsfs = hdxxArr[0][3];	
			hdjsje = hdxxArr[0][5];
			qdfcqsje = hdxxArr[0][6];
			qdfcyhsje = hdxxArr[0][7];
			hlfy = hdxxArr[0][8];
			ygffpje = hdxxArr[0][9];
			htzj = hdxxArr[0][16];
			fwhdlxdm=hdxxArr[0][17];
		}
	}
	for(var ii=1;ii<CLFJKMX.rows.length-1;ii++)
	{
		//var obj = rows[ii].all("sffjs");
		//if(!obj) continue;
		var szsmdm = CLFJKMX.rows[ii].all("szsmdm").value;
		var jsje = CLFJKMX.rows[ii].all("jsje");//��˰���
		var sjse = CLFJKMX.rows[ii].all("sjse");//Ӧ�ɽ��
		var jmje = CLFJKMX.rows[ii].all("jmje");//������
		var yjje = CLFJKMX.rows[ii].all("yjje");//ʵ�ɽ��
		var sl = CLFJKMX.rows[ii].all("sl").value; //˰��
		var kssl = CLFJKMX.rows[ii].all("kssl");//��˰����
		
		var szsmStr;
		szsmStr = szsmdm.substr(0,2);
		if(szsmStr == '05')
		{
			countGrsds(szsmStr,grsdszsfs,ygffpje,hdjsje,qdfcqsje,qdfcyhsje,hlfy,jsje,yjje,sl,sjse,jmje,yjje,kssl,htzj,fwhdlxdm);
		}
	}

	//���¼���ϵͳ�ϼ�
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
	//alert(ygffpje);
	return ygffpje;
}

//����������ֵ˰
function recomputeTdzzs()
{
	//tableid="CLFJKMX";
	//var rows = eval(tableid).rows;
	var hdjsje = changeJE(0.00);
	var qdfcqsje = changeJE(0.00);
	var hlfy = changeJE(0.00);
	var ygffpje = changeJE(0.00);
	var qdtdsyqzfje = changeJE(0.00);
	var jfpgjg = changeJE(0.00);
	var jgpgfy = changeJE(0.00);
	var anjjse = changeJE(0.00);
	var fwhdlxdm = "";
	
	var sbbh = document.forms[0].sbbh.value;
	var htwsqyrq = document.forms[0].htwsqyrq.value;
	var rqcs = document.forms[0].rqcs.value;
	
	//��ú˶���Ϣ
	var hdxxArr = new Array();
    if(szsmdm_hd_value.length !=0)
    {
		hdxxArr = getHdxx(szsmdm_hd_value,sbbh);

		//�Ƿ��ҵ�
		if(hdxxArr.length != 0)
		{
			//alert(tsnyArr);
			var sbbh = hdxxArr[0][0];
			var htbh = hdxxArr[0][1];					
			var tdzzszsfs = hdxxArr[0][4];
			hdjsje = hdxxArr[0][5];
			qdfcqsje = hdxxArr[0][6];
			hlfy = hdxxArr[0][8];
			ygffpje = hdxxArr[0][9];
			var tdzzssbfs = hdxxArr[0][10];
			qdtdsyqzfje = hdxxArr[0][11];
			jfpgjg = hdxxArr[0][12];
			jgpgfy = hdxxArr[0][13];
			anjjse = hdxxArr[0][14];
			fwhdlxdm=hdxxArr[0][17];
		}
	}
	for(var ii=1;ii<CLFJKMX.rows.length-1;ii++)
	{
		//var obj = rows[ii].all("sffjs");
		//if(!obj) continue;
		var szsmdm = CLFJKMX.rows[ii].all("szsmdm").value;
		var jsje = CLFJKMX.rows[ii].all("jsje");//��˰���
		var sjse = CLFJKMX.rows[ii].all("sjse");//Ӧ�ɽ��
		var jmje = CLFJKMX.rows[ii].all("jmje");//������
		var yjje = CLFJKMX.rows[ii].all("yjje");//ʵ�ɽ��
		var sl = CLFJKMX.rows[ii].all("sl").value; //˰��
		var kssl = CLFJKMX.rows[ii].all("kssl");//��˰����
		
		var szsmStr;
		szsmStr = szsmdm.substr(0,2);
		if(szsmStr == '08')
		{
			countTdzzs(tdzzszsfs,tdzzssbfs,ygffpje,hdjsje,qdfcqsje,qdtdsyqzfje,jfpgjg,jgpgfy,hlfy,jsje,yjje,sjse,jmje,yjje,kssl,htwsqyrq,anjjse,rqcs,fwhdlxdm);
		}
	}

	//���¼���ϵͳ�ϼ�
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
}


//Ӫҵ˰��˰
/*BR2.2.3.4 ����˰������֮Ӫҵ˰������˰�������
1�����Ӫҵ˰���շ�ʽ�ǡ�����Ӫҵ˰�����ߡ�ȫ������Ӫҵ˰�������˰����Ϊ������˰����ȷ�Ϸ�ʽ������ʽȷ�ϵĽ�
2. ���Ӫҵ˰���շ�ʽ�ǡ������˰Ӫҵ˰�������˰����Ϊ������˰����ȷ�Ϸ�ʽ������ʽȷ�ϵĽ�����ԭ������Ʊ����
3��˰�ʾ�Ϊ��5%����
4�����Ӫҵ˰���շ�ʽ�ǡ�����Ӫҵ˰������Ӧ�ɽ��Ϊ��˰������˰�ʣ�������ΪӦ��˰�ʵ��˰��Ϊ0��
5�����Ӫҵ˰���շ�ʽ�ǡ�ȫ������Ӫҵ˰���򡾲����˰Ӫҵ˰������Ӧ��˰��Ϊ��˰������˰�ʣ�������Ϊ0��ʵ��˰��ΪӦ��˰�
6��Ӫҵ˰�ĸ���˰�ѳǽ�˰�������Ѹ��ӡ��ط��������ӵļ�˰����ΪӪҵ˰��Ӧ��˰���Ӧ��˰˰��Ϊ��˰������˰�ʣ�����˰������Ӫҵ˰���շ�ʽ��
   ���Ӫҵ˰���շ�ʽ�ǡ�����Ӫҵ˰���������˰��ΪӦ��˰�ʵ��˰��Ϊ0��
   ���Ӫҵ˰���շ�ʽΪ��ȫ������Ӫҵ˰���򡾲����˰Ӫҵ˰���������˰��Ϊ0��ʵ��˰��ΪӦ��˰�
*/
function getYysInfo(yyszsfs,hdjsje,ygffpje,jsje,sjse,jmje,yjje,sl)
{
	//alert(hdjsje +" : "+jsje.value);
	
	if(hdjsje =='NaN' || hdjsje =="" || parseFloat(hdjsje) < 0)
		hdjsje = changeJE(0.00);
	if(ygffpje =='NaN' || ygffpje =="" || parseFloat(ygffpje) < 0)
		ygffpje = changeJE(0.00);
	if(sl =='NaN' || sl =="" || parseFloat(sl) < 0)
		sl = changeJE(0.00);
				
	//Ӫҵ˰��˰���
	if( jsje.value == "" || jsje.value == 'NaN')
	{
		if(yyszsfs == '<%=Constants.YSSZSFS_MINUS%>')
		{
			var strValue = Math.round((parseFloat(hdjsje)-parseFloat(ygffpje))*100)/100;
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
			
			jsje.value = changeJE(strValue);
		}
		else
		{
			jsje.value = changeJE(Math.round(parseFloat(hdjsje)*100)/100);
		}
	}
	//Ӫҵ˰Ӧ�ɽ������ʵ��˰��
	if( (sjse.value == "" || sjse.value == 'NaN'))
	{
		if(yyszsfs == '<%=Constants.YSSZSFS_NOT%>')
		{
			if(jsje.value =='NaN' || jsje.value =="" || parseFloat(jsje.value) < 0)
				jsje.value = changeJE(0.00);
			
			var strValue = changeJE(Math.round(parseFloat(jsje.value)*parseFloat(sl)*100)/100);
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
			
			sjse.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
			jmje.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
			yjje.value = changeJE(0.00);
		}
		else
		{
			var strValue = Math.round(parseFloat(jsje.value)*parseFloat(sl)*100)/100;
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
			
			sjse.value = changeJE(strValue);
			jmje.value = changeJE(0.00);
			yjje.value = changeJE(strValue);
		}
	}
	//���¼���ϵͳ�ϼ�
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
}

//�������е��У����㸽��˰
//rowIndex��ʾ����˰����
function countFjs(fjsbz,szsmStr,yyszsfs,obj,rowIndex,cjssl){
	//alert("����˰��="+rowIndex);
	//�õ������ж���
	var rows = eval(obj);
	//�õ��ø���˰��sjse
	var sjsehj = getFjsSj(obj,rowIndex);
	if(sjsehj =='NaN' || sjsehj =="" )
		sjsehj = changeJE(0.00);
	//alert("sjsehj:"+sjsehj);
	//�õ��ø���˰��jmje
	var jmje = rows[rowIndex].all("jmje").value;
	//����˰˰��
	var sl = rows[rowIndex].all("sl").value;
	if(sl =='NaN' || sl =="" )
		sl = changeJE(0.00);
	if(fjsbz=="2" && szsmStr == "10")
	{
		sl = cjssl;
	}
	//alert(sl);
	//�踽��˰�ļ�˰���Ϊ��˰Ӧ��˰��ĺϼ�
	rows[rowIndex].all("jsje").value = changeJE(Math.round(parseFloat(sjsehj)*100)/100);
	//�踽��˰��Ӧ��˰��Ϊ��˰���*˰��
	rows[rowIndex].all("sjse").value = changeJE(Math.round(parseFloat(sjsehj)*parseFloat(sl)*100)/100);
	
	//�õ�����˰��sjse
	var sjseValue = rows[rowIndex].all("sjse").value;
	//�踽��˰������
	if(sjseValue =='NaN' || sjseValue =="" || parseFloat(sjseValue) < 0)
		sjseValue = changeJE(0.00);
	if(yyszsfs == '<%=Constants.YSSZSFS_NOT%>')
	{
			
		rows[rowIndex].all("jmje").value = changeJE(sjseValue);
		rows[rowIndex].all("yjje").value = changeJE(0.00);
	}
	else
	{
		//alert("1");
		rows[rowIndex].all("jmje").value = changeJE(0.00);
		rows[rowIndex].all("yjje").value = changeJE(sjseValue);
	}
	//���¼���ϵͳ�ϼ�
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
}

//��˰��������˰
/*
BR2.2.3.6 ����˰������֮��������˰��˰����
1�������ԭ������Ʊ���������㣬����˰���ѯѡ����˰��¼�����ߡ�ԭ������Ʊ�����Ϊ�㲢������˰���ѯδѡ������δ��ѯ����˰��¼�����ҡ���ί��ѯ����ԭֵ��������ʱ�����������˰���ա���ʵ���ա���ʽ����˰�
   �����ԭ������Ʊ��Ϊ�㣬����������˰˰��û�в�ѯ�������ҡ���ί��ѯ����ԭֵ��¼��ֵΪ��ʱ���򰴡��˶����ա���ʽ����˰�
2������ʵ���ա���ʽ��˰������������£�
  A������˰����ȷ�Ϸ�ʽ������ʽȷ�ϵĽ���ȥ������ԭֵ����
     ������ԭֵ���������Ϊ�������ԭ������Ʊ�����ߺ���˰�ļ�˰����һ����Ϊ�㣬�����ԭ��
     ������߽�Ϊ�㣬��Ϊ����ί��ѯ����ԭֵ����
  B����A�Ļ����ϼ�ȥ��ȡ�÷��ز�ʱ�����ɵ���˰����
  C����B�Ļ����ϼ�ȥ��ȡ�÷��ز�ʱ�����ɵ�ӡ��˰����
  D����C�Ļ����ϼ�ȥ��������á���
  E����D�Ļ����ϼ�ȥ�����걨�г���������˰����������˰�ѵ�ʵ��˰���Ľ��Ϊ��˰��
3������ʵ���ա���ʽ�걨��˰��Ϊ20%��
4�����˶����ա���ʽ��˰���Ϊ����˰����ȷ�Ϸ�ʽ������ʽȷ�ϵĽ�
5�����˶����ա���ʽ�걨��˰��Ϊ1%��
6����������˰Ӧ��˰��Ϊ��˰������˰�ʣ�
7�������������˰���շ�ʽΪ��������������˰�����򲻼����������˰Ӧ��˰�
8�������������˰���շ�ʽΪ�����ո�������˰�����������Ϊ�㣬ʵ�ɽ��ΪӦ��˰�
*/
/*
BR2.2.3.6.2����ס���ࡱ����˰������֮��������˰�������
1�������������˰���շ�ʽΪ��������������˰�����򲻼����������˰Ӧ��˰�
2�������������˰���շ�ʽΪ�����ո�������˰�����������¼����������������˰��
A����˰�۸�Ϊ������˰����ȷ�Ϸ�ʽ����
B����A�Ļ����ϼ�ȥ��ԭ������Ʊ���� 
C����C�Ļ����ϼ�ȥ�����걨�г���������˰����������˰�ѵ�ʵ��˰���Ľ��Ϊ��˰��
3��˰��Ϊ20%��
4����������˰Ӧ��˰��Ϊ��˰������˰�ʣ�
5�������������˰���շ�ʽΪ�����ո�������˰�����������Ϊ�㣬ʵ�ɽ��ΪӦ��˰�

*/
function countGrsds(szsmStr,grsdszsfs,ygffpje,hdjsje,qdfcqsje,qdfcyhsje,hlfy,jsje,yjje,sl,sjse,jmje,yjje,kssl,htzj,fwhdlxdm)
{
	var sbbh_his = document.all("sbbh_his").value; //����걨���
	var jwyzValue = document.forms[0].fwyz.value; //��÷���ԭֵ
	var jsje_his = getMfscjnInfo(); //�����˰��ѯ���
	var otherJe = getOutGrsdsYjjehj(); //��ó���������˰Ӧ�ɽ�����ʵ�ɽ��ϼ���

	if(jwyzValue =='NaN' || jwyzValue =="" || parseFloat(jwyzValue) < 0)
		jwyzValue = changeJE(0.00);
	if(hdjsje =='NaN' || hdjsje =="" || parseFloat(hdjsje) < 0)
		hdjsje = changeJE(0.00);
	if(ygffpje =='NaN' || ygffpje =="" || parseFloat(ygffpje) < 0)
		ygffpje = changeJE(0.00);
	if(qdfcqsje =='NaN' || qdfcqsje =="" || parseFloat(qdfcqsje) < 0)
		qdfcqsje = changeJE(0.00);
	if(qdfcyhsje =='NaN' || qdfcyhsje =="" || parseFloat(qdfcyhsje) < 0)
		qdfcyhsje = changeJE(0.00);
	if(hlfy =='NaN' || hlfy =="" || parseFloat(hlfy) < 0)
		hlfy = changeJE(0.00);	
	if(sl =='NaN' || sl =="" || parseFloat(sl) < 0)
		sl = changeJE(0.00);	
	if(yjje.value =='NaN' || yjje.value =="" || parseFloat(yjje.value) < 0)
		yjje.value = changeJE(0.00);
	if(jsje_his =='NaN' || jsje_his =="" || parseFloat(jsje_his) < 0)
		jsje_his = changeJE(0.00);
	//������ݺ˶�����Ϊס�������к˶����ա���ʵ�������ַ�ʽ�����Ϊ��ס����ֻ�о�ʵ���շ�ʽ������ʵ���յĹ�����ס�����в�ͬ��added by zhangj
	if(fwhdlxdm=="0"){		
	//alert(hdjsje + " : "+ygffpje + " : "+ sbbh_his + " : "+ jsje_his + " : "+ otherJe + " �� " +jwyzValue + " : "+sl);
	//alert("ygffpje:"+ygffpje+" sbbh_his��"+sbbh_his+" jwyzValue:"+jwyzValue);
	//�����˶����ա���ʽ����˰��	��050802��
	if((changeJE(ygffpje) == "0.00") && (changeJE(jsje_his) == "0.00") && (changeJE(jwyzValue) == "0.00"))
	{	
		jsje.value = changeJE(Math.round(parseFloat(hdjsje)*100)/100);
		
		var strValue = changeJE(Math.round(parseFloat(jsje.value)*parseFloat(sl)*100)/100);
		if(parseFloat(strValue) < 0)
		{
			strValue = changeJE(0.00);	
		}
		sjse.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
		jmje.value = changeJE(0.00);
		yjje.value = changeJE(strValue);
		//alert("hdjsje:"+hdjsje+" jsje.value:"+jsje.value+" sl:"+sl+" sjse.value:"+sjse.value+" jmje.value:"+jmje.value+" yjje.value:"+yjje.value+" strValue:"+strValue);
	}
	//������ʵ���ա���ʽ����˰�� ��050801��
	else
	{
		var tempJe = changeJE(0.00);
		if((changeJE(ygffpje) == "0.00") && (changeJE(jsje_his) == "0.00"))
		{
			tempJe = changeJE(parseFloat(jwyzValue));
		}
		else
		{
			if(parseFloat(ygffpje)>parseFloat(jsje_his))
			{
				tempJe = changeJE(parseFloat(ygffpje));
			}
			else
			{
				tempJe = changeJE(parseFloat(jsje_his));
			}
		}
		var strValue = changeJE(Math.round((parseFloat(hdjsje)-parseFloat(tempJe)-parseFloat(qdfcqsje)-parseFloat(qdfcyhsje)-parseFloat(hlfy)-parseFloat(otherJe))*100)/100);
		if(parseFloat(strValue) < 0)
		{
			strValue = changeJE(0.00);	
		}
		
		jsje.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
		//alert(" hdjsje:"+hdjsje + " ygffpje:"+ygffpje + " qdfcqsje:"+ qdfcqsje + " jsje_his:"+jsje_his+" qdfcyhsje:"+ qdfcyhsje + " hlfy:"+ hlfy + " otherJe:"+ otherJe+ " strValue:"+ strValue+" tempJe:"+tempJe);
		var tmpValue = changeJE(Math.round(parseFloat(jsje.value)*parseFloat(sl)*100)/100);
		if(parseFloat(tmpValue) < 0)
		{
			tmpValue = changeJE(0.00);	
		}
		
		sjse.value = changeJE(Math.round(parseFloat(tmpValue)*100)/100);
		jmje.value = changeJE(0.00);
		yjje.value = changeJE(tmpValue);
	}
	}else if(fwhdlxdm=="1"){
		//��ס����ʵ���շ�ʽ��added by zhangj��
		//htzjΪ��ͬ�ܼۣ���Ϊ��ǩ�۸�hdjsjeΪ�˶��۸����ǩ�۸�ϸ���	
		var strValue = changeJE(Math.round((parseFloat(hdjsje)-parseFloat(qdfcqsje)-parseFloat(ygffpje)-parseFloat(qdfcyhsje)-parseFloat(hlfy)-parseFloat(otherJe))*100)/100);
		if(parseFloat(strValue) < 0)
		{
			strValue = changeJE(0.00);	
		}
		
		jsje.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
		//alert(" hdjsje:"+hdjsje + " ygffpje:"+ygffpje + " qdfcqsje:"+ qdfcqsje + " jsje_his:"+jsje_his+" qdfcyhsje:"+ qdfcyhsje + " hlfy:"+ hlfy + " otherJe:"+ otherJe+ " strValue:"+ strValue+" tempJe:"+tempJe);
		var tmpValue = changeJE(Math.round(parseFloat(jsje.value)*parseFloat(sl)*100)/100);
		if(parseFloat(tmpValue) < 0)
		{
			tmpValue = changeJE(0.00);	
		}
		
		sjse.value = changeJE(Math.round(parseFloat(tmpValue)*100)/100);
		jmje.value = changeJE(0.00);
		yjje.value = changeJE(tmpValue);
	}else{
		alert("���ݺ˶����Ͳ���ȷ");
	}
	
	//���¼���ϵͳ�ϼ�
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
	
}

////��˰������ֵ˰
/*
1�������������ֵ˰���շ�ʽ��ѡ���ṩ������Ʊ����������ֵ˰���������������£�
��1����˰������˰����ȷ�Ϸ�ʽ������ʽȷ�ϵĽ�������ǩ�۸��롰�˶���˰�۸񡱽ϸ��ߣ�����ͬ�࣬������������
��2���۳���=��ԭ������Ʊ���ӡ�����Ӽ������+��ȡ�÷��ز�ʱ�����ɵ���˰��+�������걨��Ӫҵ˰���ǽ�˰�������Ѹ��ӡ��ط��������Ӻ�ӡ��˰��ʵ��˰���
��3��������ֵ��=����˰�������۳����
��4������˰����ͨ����������ֵ�/���۳���ı�����ã�
���չ�ϵ���£�
A����������ֵ�/���۳��֮��С�ڵ���0.5����˰��30%��
B����������ֵ�/���۳��֮�ȴ���0.5����С�ڵ���1����˰��40%��
C����������ֵ�/���۳��֮�ȴ���1����С�ڵ���2����˰��50%��
D����������ֵ�/���۳��֮�ȴ���2����˰��60%��
��5������۳�ϵ����ͨ����������ֵ��ķ�Χ��ã����չ�ϵ����:
A����������ֵ�/���۳��֮��С�ڵ���0.5��������۳���0%��
B�� ��������ֵ�/���۳��֮�ȴ���0.5����С�ڵ���1��������۳���5%��
C����������ֵ�/���۳��֮�ȴ���1����С�ڵ���2��������۳���15%��
D����������ֵ�/���۳��֮�ȴ���2��������۳���35%��
��6��������ֵ˰Ӧ��˰��=��������ֵ����ԡ�����˰�ʡ������۳�����ԡ�����۳�ϵ������

2�������������ֵ˰���շ�ʽ��ѡ���ṩ������������������ֵ˰���������������£�
��1����˰������˰����ȷ�Ϸ�ʽ������ʽȷ�ϵĽ�
��2���۳���=��ȡ������ʹ��Ȩ��֧���Ľ��ӡ��ɷ��������������۸񡿼ӡ��۸��������á��ӡ������걨��Ӫҵ˰���ǽ�˰�������Ѹ��ӡ��ط��������Ӻ�ӡ��˰��ʵ��˰���
��3��������ֵ��=����˰�������۳����
��4������˰����ͨ����������ֵ�/���۳���ı�����ã�
���չ�ϵ���£�
A����������ֵ�/���۳��֮��С�ڵ���0.5����˰��30%��
B����������ֵ�/���۳��֮�ȴ���0.5����С�ڵ���1����˰��40%��
C����������ֵ�/���۳��֮�ȴ���1����С�ڵ���2����˰��50%��
D����������ֵ�/���۳��֮�ȴ���2����˰��60%��
��5������۳�ϵ����ͨ����������ֵ��ķ�Χ��ã����չ�ϵ����:
A����������ֵ�/���۳��֮��С�ڵ���0.5��������۳���0%��
B����������ֵ�/���۳��֮�ȴ���0.5����С�ڵ���1��������۳���5%��
C����������ֵ�/���۳��֮�ȴ���1����С�ڵ���2��������۳���15%��
D����������ֵ�/���۳��֮�ȴ���2��������۳���35%��
��6��������ֵ˰Ӧ��˰��=��������ֵ����ԡ�����˰�ʡ������۳�����ԡ�����۳�ϵ������
3�������������ֵ˰���շ�ʽ��Ϊ���˶�����������ֵ˰�������˰���*˰�ʣ�˰��Ϊ��5%����
4�������������ֵ˰���շ�ʽ��ѡ�񡾲�����������ֵ˰��������Ҫ����������ֵ˰��
5�������������ֵ˰���շ�ʽ��ѡ������������ֵ˰����������������ֵ˰��Ҳ�����м����ƺ��㡣

*/
function countTdzzs(tdzzszsfs,tdzzssbfs,ygffpje,hdjsje,qdfcqsje,qdtdsyqzfje,jfpgjg,jgpgfy,hlfy,jsje,yjje,sjse,jmje,yjje,kssl,htwsqyrq,anjjse,rqcs,fwhdlxdm)
{
	//����걨���
	var sbbh_his = document.all("sbbh_his").value;

	if(hdjsje =='NaN' || hdjsje =="" || parseFloat(hdjsje) < 0)
		hdjsje = changeJE(0.00);
	if(ygffpje =='NaN' || ygffpje =="" || parseFloat(ygffpje) < 0)
		ygffpje = changeJE(0.00);
	if(qdfcqsje =='NaN' || qdfcqsje =="" || parseFloat(qdfcqsje) < 0)
		qdfcqsje = changeJE(0.00);
	if(qdtdsyqzfje =='NaN' || qdtdsyqzfje =="" || parseFloat(qdtdsyqzfje) < 0)
		qdtdsyqzfje = changeJE(0.00);
	if(jfpgjg =='NaN' || jfpgjg =="" || parseFloat(jfpgjg) < 0)
		jfpgjg = changeJE(0.00);
	if(jgpgfy =='NaN' || jgpgfy =="" || parseFloat(jgpgfy) < 0)
		jgpgfy = changeJE(0.00);
	if(anjjse =='NaN' || anjjse =="" || parseFloat(anjjse) < 0)
		anjjse = changeJE(0.00);
	
	//���Ӫҵ˰������˰ʵ�ɽ��ϼ���
	var yyssjJe = getYYSsYjjehj();
	var yhssjJe = getYhssjje();
	//alert(hdjsje + " : "+ygffpje + " : "+ sbbh_his + " : "+ htwsqyrq + " : "+ qdtdsyqzfje + " : "+ jfpgjg+ " : "+ jgpgfy + " : "+ htwsqyrq + " : " +yyssjJe);
	//alert(" szsmStr:"+szsmStr+" tdzzszsfs:"+tdzzszsfs+" tdzzssbfs:"+tdzzssbfs+" hdjsje:"+hdjsje+" htwsqyrq:"+htwsqyrq+" anjjse:"+anjjse)
	//alert("rqcs:"+parseFloat(rqcs)+" htwsqyrq:"+parseFloat(htwsqyrq));
	
	//ס���ͷ�ס����������ֳ˰���㷽ʽ��ͬ��modified by zhangj��
		//��ס����������ֵ˰���շ�ʽ(added by zhangj)
		var strValue = changeJE(0.00);
		jsje.value = changeJE(Math.round(parseFloat(hdjsje)*100)/100);
		if(tdzzszsfs=='<%=Constants.TDZZSZSFS_GFFPZS%>'){
		//1.�ṩ������Ʊ����������ֵ˰
			var kce = changeJE(Math.round((parseFloat(ygffpje)+parseFloat(anjjse)+parseFloat(qdfcqsje)+parseFloat(yyssjJe)+parseFloat(yhssjJe))*100)/100); //�۳���
			var tdzze = changeJE(Math.round((parseFloat(jsje.value)-parseFloat(kce))*100)/100); //������ֵ��
			if(parseFloat(tdzze) < 0)
			{
				tdzze = changeJE(0.00);	
			}
			
			strValue = getTdzzsSlKcs(tdzze,kce);
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
			
			sjse.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
		}else if(tdzzszsfs=='<%=Constants.TDZZSZSFS_PGBGZS%>'){
		//2.�ṩ������������������ֵ˰
			var kce = changeJE(Math.round((parseFloat(qdtdsyqzfje)+parseFloat(jfpgjg)+parseFloat(jgpgfy)+parseFloat(yyssjJe)+parseFloat(yhssjJe))*100)/100); //�۳���
			var tdzze = changeJE(Math.round((parseFloat(jsje.value)-parseFloat(kce))*100)/100); //������ֵ��
			if(parseFloat(tdzze) < 0)
			{
				tdzze = changeJE(0.00);	
			}
			
			strValue = getTdzzsSlKcs(tdzze,kce);
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
			sjse.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
		
		}else if(tdzzszsfs=='<%=Constants.TDZZSZSFS_HDZS%>'){
		//3.�˶�����������ֵ˰
			var sl = changeJE(0.01);
			if(parseFloat(htwsqyrq) >= parseFloat(rqcs))
			{
				sl = changeJE(0.05);
			}
			//alert("parseFloat(htwsqyrq)��"+parseFloat(htwsqyrq)+" parseFloat(rqcs)��"+parseFloat(rqcs)+" sl��"+sl);
			strValue = changeJE(Math.round(parseFloat(jsje.value)*parseFloat(sl)*100)/100);
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
		
			sjse.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
			document.all.tdzzssl.value = sl;
		
		}else if(tdzzszsfs=='<%=Constants.TDZZSZSFS_FREE%>'){
		//5.����������ֵ˰
			var sl = changeJE(0.01);
			if(parseFloat(htwsqyrq) >= parseFloat(rqcs))
			{
				sl = changeJE(0.05);
			}
			//alert("parseFloat(htwsqyrq)��"+parseFloat(htwsqyrq)+" parseFloat(rqcs)��"+parseFloat(rqcs)+" sl��"+sl);
			strValue = changeJE(Math.round(parseFloat(jsje.value)*parseFloat(sl)*100)/100);
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
		
			sjse.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
			document.all.tdzzssl.value = sl;
		}else{
		//4.������������ֵ˰
		}		
		//������
		var jmjeTemp = getTdzssJmje(tdzzszsfs,strValue);
		if(parseFloat(jmjeTemp) < 0)
		{
			jmjeTemp = changeJE(0.00);	
		}
		
		jmje.value = changeJE(Math.round(parseFloat(jmjeTemp)*100)/100);

		//ʵ��˰��
		var yjjeTemp = getTdzssYjje(tdzzszsfs,strValue);
		if(parseFloat(yjjeTemp) < 0)
		{
			yjjeTemp = changeJE(0.00);	
		}
		
		yjje.value = changeJE(Math.round(parseFloat(yjjeTemp)*100)/100);		
	

	//���¼���ϵͳ�ϼ�
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
}


//������ֵ˰������
function getTdzssJmje(tdzzszsfs,strValue)
{
	var jmjeValue = changeJE(0.00);
	if(tdzzszsfs == '<%=Constants.TDZZSZSFS_FREE%>')
	{
		jmjeValue = changeJE(strValue);
	}
	if((tdzzszsfs == '<%=Constants.TDZZSZSFS_ZS%>') || (tdzzszsfs == '<%=Constants.TDZZSZSFS_GFFPZS%>')|| (tdzzszsfs == '<%=Constants.TDZZSZSFS_HDZS%>')|| (tdzzszsfs == '<%=Constants.TDZZSZSFS_PGBGZS%>') )
	{
		jmjeValue = changeJE(0.00);
	}

	return jmjeValue;
}

//������ֵ˰ʵ�ɽ��
function getTdzssYjje(tdzzszsfs,strValue)
{
	var yjjeValue = changeJE(0.00);
	if(tdzzszsfs == '<%=Constants.TDZZSZSFS_FREE%>' )
	{
		yjjeValue = changeJE(0.00);
	}
	if((tdzzszsfs == '<%=Constants.TDZZSZSFS_ZS%>') || (tdzzszsfs == '<%=Constants.TDZZSZSFS_GFFPZS%>')|| (tdzzszsfs == '<%=Constants.TDZZSZSFS_HDZS%>')|| (tdzzszsfs == '<%=Constants.TDZZSZSFS_PGBGZS%>'))
	{
		yjjeValue = changeJE(strValue);
	}
	return yjjeValue;
}


//�������������˰ʵ�ɽ���������˰ʵ�ɽ��ϼ���
function getOutGrsdsYjjehj()
{
	//ʵ��˰��ϼ�
	var yjjehj =changeJE(0.00);
	for(var ii=1;ii <CLFJKMX.rows.length-1;ii++)
	{
		var szsmStr = CLFJKMX.rows[ii].all("szsmdm").value;
		var yjje = CLFJKMX.rows[ii].all("yjje").value;//ʵ�ɽ��
		if(yjje =='NaN' || yjje =="" )
			yjje = changeJE(0.00);
		szsmStr = szsmStr.substr(0,2);
		if( szsmStr != '05')
		{
			yjjehj = changeJE(parseFloat(yjjehj) + parseFloat(yjje));
		}
	}
	return yjjehj;
}

//����Ӫҵ˰������˰ʵ�ɽ��ϼ���
function getYYSsYjjehj()
{
	//ʵ��˰��ϼ�
	var yysyjjehj =changeJE(0.00);
	for(var ii=1;ii <CLFJKMX.rows.length-1;ii++)
	{
		var szsmStr = CLFJKMX.rows[ii].all("szsmdm").value;
		var fjsbz = CLFJKMX.rows[ii].all("sffjs").value;
		var yjje = CLFJKMX.rows[ii].all("yjje").value;//ʵ�ɽ��
		if(yjje =='NaN' || yjje =="" )
			yjje = changeJE(0.00);
		szsmStr = szsmStr.substr(0,2);
		if( (szsmStr == '02') || (fjsbz=="2" && szsmStr!='53'))
		{
			yysyjjehj = changeJE(parseFloat(yysyjjehj) + parseFloat(yjje));
		}
	}
	return yysyjjehj;
}
//ӡ��˰ʵ�ɽ��ϼ��� ,added by zhangj
function getYhssjje(){
	//ʵ��˰��ϼ�
	var yhssjje =changeJE(0.00);
	for(var ii=1;ii <CLFJKMX.rows.length-1;ii++)
	{
		var szsmStr = CLFJKMX.rows[ii].all("szsmdm").value;
		var fjsbz = CLFJKMX.rows[ii].all("sffjs").value;
		var yjje = CLFJKMX.rows[ii].all("yjje").value;//ʵ�ɽ��
		if(yjje =='NaN' || yjje =="" )
			yjje = changeJE(0.00);
		szsmStr = szsmStr.substr(0,2);
		if(szsmStr=='16')
		{
			yhssjje = changeJE(parseFloat(yhssjje) + parseFloat(yjje));
		}
	}
	return yhssjje;
}


//���㸽��˰����˰��Ӧ�ɽ��
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


function getHdxx(arrayName,key){
  var obj = get_obj(arrayName);
  //alert(obj.length);
  var tempArr = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==key) tempArr.push(obj[ii]);
  }
  return tempArr;
}

//����ʽ
function  changeJE(je)
{
	var strJe = je.toString();
    var rs = strJe.indexOf('.');  
     
    if (rs < 0) {  
         rs = strJe.length;  
         strJe += '.';  
    }  
    while (strJe.length <= rs + 2) {  
         strJe += '0';  
    }  
    return strJe;  
}

//��ί����ԭֵ�������
////1.�ϴν�����ϢΪ��  || ���ɼ�˰���Ϊ 0��
////2.ԭ������Ʊ���Ϊ 0�� 
function checkFwyz(type)
{		
	var jwyzId = document.all("jwyzId");
		//added by zhangj
	setFwhdlxdm();
	if(document.all.fwhdlxdm.value!="0"){
		jwyzId.style.display="none";
		document.all.fwyz.disabled=true;
		return true;
	}else{
	
	//ԭ�����ж��߼�
		
	//�ж��Ƿ��з��ݺ˶���Ϣ
	var hasHdxxVal = document.all.hasHdxx.value;
	
	//alert(type+" : "+hasHdxxVal);
	if(type == "Add" && hasHdxxVal == "N")
	{
		alert("���ʵ�Ƿ�����˰����ض�����к˶���");
		return false;
	}
	
	var sbbh = document.forms[0].sbbh.value;
	var fwyz = document.forms[0].fwyz.value;
	var scjnxxTemp = getMfscjnInfo(); //�ϴν�����Ϣ
	//��ú˶���Ϣ��������˰���շ�ʽ
	var hdxxArray = new Array();
	var ygffpjeTemp ="";
	var grsdszsfs ="";
	if(szsmdm_hd_value.length !=0)
    {
		hdxxArray = getHdxx(szsmdm_hd_value,sbbh);
		if(hdxxArray.length != 0)
		{
			grsdszsfs = hdxxArray[0][3];
			ygffpjeTemp = hdxxArray[0][9];	
		}
	}
	if(document.forms[0].htbh1.value!='')
	{
		//alert(ygffpjeTemp +" : "+scjnxxTemp + " : " +grsdszsfs);
		if((ygffpjeTemp��!=��"" && changeJE(ygffpjeTemp) == "0.00") && (changeJE(scjnxxTemp) == "0.00") && (grsdszsfs��!=��""��&&��grsdszsfs == '<%=Constants.GRSDSZSFS_ZS%>'))
		{
			if(fwyz == "")
			{
				alert("��ί��ѯ����ԭֵ�������룡");
				document.forms[0].fwyz.focus();
				return false;
			}
			else
			{
				if (!FN_QSCheckNumberDigit(fwyz,2,"��ί��ѯ����ԭֵ"))
				{
					document.forms[0].fwyz.focus();
					return false;
				}
			}
		}
		else
		{
			jwyzId.style.display="none";
			document.all.fwyz.disabled=true;
		}
	}
	else
	{
		jwyzId.style.display="none";
		document.all.fwyz.disabled=true;
	}
	return true;
	
	}
	
}


//����������˰���շ�ʽ
function checkgrsdszsfs()
{	var fwhdlxdm="";
	var sbbh_his = document.all("sbbh_his").value; //����걨���
	var sbbh = document.forms[0].sbbh.value;
	var ygffpjeTemp = changeJE(0.00);
	var jwyzValue = document.forms[0].fwyz.value; //��÷���ԭֵ
	if(jwyzValue =='NaN' || jwyzValue =="")
		jwyzValue = changeJE(0.00);
		
	//��ú˶���Ϣ��������˰���շ�ʽ
	var hdxxArray = new Array();
	if(szsmdm_hd_value.length !=0)
    {
		hdxxArray = getHdxx(szsmdm_hd_value,sbbh);
		if(hdxxArray.length != 0)
		{
			ygffpjeTemp = hdxxArray[0][9];
			fwhdlxdm = hdxxArray[0][17];
		}
	}
	for(var ii=1;ii<CLFJKMX.rows.length-1;ii++)
	{	
		// modified by zhangj
		var szsmdm = CLFJKMX.rows[ii].all("szsmdm").value;
		if(fwhdlxdm==<%=Constants.FWHDLX_NONHOUSING%> && szsmdm=='050802'){
			alert("���ݺ˶�����Ϊ��ס������������˰ӦΪ����ʵ���ա�(˰Ŀ��050801)��ʽ����ɾ��������¼����ȷ˰Ŀ��");
			return false;
		}else if(fwhdlxdm==<%=Constants.FWHDLX_HOUSING%>){

			//alert("ygffpjeTemp:"+changeJE(ygffpjeTemp)+" sbbh_his:"+sbbh_his+" jwyzValue:"+changeJE(jwyzValue)+" szsmdm:"+szsmdm);
			if((changeJE(ygffpjeTemp) == "0.00") && (sbbh_his == "" || sbbh_his == "NaN") && (changeJE(jwyzValue) == "0.00") && (szsmdm == '050801'))
			{
				alert("���ݡ�ԭ������Ʊ�����������ϴν���˰����Ϣ��������ί��ѯ����ԭֵ�����ֵ�жϣ���������˰ӦΪ���˶����ա�(˰Ŀ��050802)��ʽ����ɾ��������¼����ȷ˰Ŀ��");
				return false;
			}
			if(((changeJE(ygffpjeTemp) != "0.00") || (sbbh_his != "" && sbbh_his != "NaN") || (changeJE(jwyzValue) != "0.00")) && (szsmdm == '050802'))
			{
				alert("���ݡ�ԭ������Ʊ�����������ϴν���˰����Ϣ��������ί��ѯ����ԭֵ�����ֵ�жϣ���������˰ӦΪ����ʵ���ա�(˰Ŀ��050801)��ʽ����ɾ��������¼����ȷ˰Ŀ��");
				return false;
			}
		}
	}
	return true;
}


//�����˰�ϴν�����Ϣ
function getMfscjnInfo()
{
	var jsje_his = changeJE(0.00);
	<logic:iterate id="dataList" name="mfskzsForm" property="sbxxHisList" indexId="index">
	<%if (0 == index.intValue()) { %>         
      jsje_his = changeJE('<bean:write name="dataList" property="jsje_his"/>'); 
    <% }%>	
	</logic:iterate>
	return jsje_his;
}

//function  : ��������ַ����Ƿ����������
//parameters: DigitString: String,������ַ�����ֵ
//			  totalLength: int, ���ֵĳ������ƣ�������С���㣩
//			  decimalLength: int��С���ĳ�������
//            totalLength-decimalLength �������ֵ�����
//            isPositive �Ƿ�Ϊ�Ǹ��� true��ʾһ��Ҫ�ǷǸ�����false��ʾһ��Ҫ�Ǹ�����null��ʾ���� add by zhangp 2003-09-25
//return    : false: �ַ����а�������������ַ�
//			  true : else
function checkNumber1(DigitString, totalLength, decimalLength,isPositive)
{
    var re = "";
    
    if(isPositive == "true" || isPositive == true)
    {//����ǷǸ�����ֵ����0��
        if(isNaN(DigitString*1) || !(DigitString*1 > 0))
            return false;
    }
    if(isPositive == "false" || isPositive == false)
    {//����Ǹ���
        if(isNaN(DigitString*1) || DigitString*1>=0)
            return false;
    }

    
    if (decimalLength!=null && decimalLength != 0)
    {   //С����Ϊ��
        re = re+"\\.[\\d]{1,"+ decimalLength +"}"
    }

    if (totalLength == null)
    {   //δ�趨�ܳ���
        re= "\\d*"+re;
    }
    else
    {   //�趨�ܳ���
        //��С������Ϊ�յ�����£������ж����ֵĳ���
        var intergerLength = totalLength;
        if (decimalLength!=null)
        {   //��С�����ֲ�Ϊ���жϣ�������λ����С����λ���Ƿ���ȷ
            intergerLength = totalLength-decimalLength;
        }
        re="([\\d]{0,"+ intergerLength +"}"+re+"|[\\d]{0,"+intergerLength+"})";
    }
    re = new RegExp("^"+re+"$","g");

    //���ַ���Ϊ�ա�λ�����ԡ�����ƥ�������ʱ
    if(re.exec(DigitString) == null)
    {
        return false;
    }
    return true;
}

function deleteRow1(tableid,cellIndex,nameHead)
{
	eval("var tempDTable="+tableid+"_list");
	//var truthBeTold = window.confirm("�ò�����ɾ��ҳ����һ�е�����,�Ҳ����Զ��ָ�,��ȷ��");
	//hj();//������function���� ����ϼ� 
	//if(!truthBeTold)	return false;
	var row=tempDTable.doGetRow(tempDTable.CurrentRow);

	var obj = row.all("szsmdm_old");
	var father="";
	if(obj) father = obj.value;

	tempDTable.deleteRow(tempDTable.CurrentRow)
	if(cellIndex!=null)	tempDTable.focusAt(tempDTable.CurrentRow,cellIndex);
	if(nameHead!=null) delSonRow(nameHead,tableid,father);
	publicMethod();
	hj();//������function���� ����ϼ� 
}

//���������ֵ˰����˰�ʺ�����۳���
function getTdzzsSlKcs(tdzze,kce)
{
	var sl = changeJE(0.00);
	var slkcs = changeJE(0.00);
	var slkcxs = changeJE(0.00);

	//��ֵ��δ�����۳���Ŀ���50%�Ĳ��� ˰��:30 ����۳���:0;
	if(parseFloat(tdzze) <= parseFloat(kce)*0.50)
	{
		sl= changeJE(0.30);
		slkcs = changeJE(0.00);
		slkcxs = changeJE(0.00);
	}

	//��ֵ����۳���Ŀ���50%��δ����100%�Ĳ��� ˰��:40 ����۳���:�۳���Ŀ����5%;
	if((parseFloat(tdzze) > parseFloat(kce)*0.50) && (parseFloat(tdzze) <= parseFloat(kce)*1.00))
	{
		sl= changeJE(0.40);
		slkcs = changeJE(Math.round(parseFloat(kce)*0.050*100)/100);
		slkcxs = changeJE(0.05);
	}

	//��ֵ����۳���Ŀ���100%��δ����200%�Ĳ��� ˰��:50 ����۳���:�۳���Ŀ����15%;
	if((parseFloat(tdzze) > parseFloat(kce)*1.00) && (parseFloat(tdzze) <= parseFloat(kce)*2.00))
	{
		sl= changeJE(0.50);
		slkcs = changeJE(Math.round(parseFloat(kce)*0.15*100)/100);
		slkcxs = changeJE(0.15);
	}

	//��ֵ����۳���Ŀ���200%�Ĳ��� ˰��:60 ����۳���:�۳���Ŀ����35%;
	if(parseFloat(tdzze) > parseFloat(kce)*2.00)
	{
		sl= changeJE(0.60);
		slkcs = changeJE(Math.round(parseFloat(kce)*0.35*100)/100);
		slkcxs = changeJE(0.35);
	}
	
	document.all.tdzzssl.value = sl;
	
	//alert("sl:"+sl+" slkcxs:"+slkcxs);
	return changeJE(Math.round(((parseFloat(tdzze)*parseFloat(sl))-(parseFloat(kce)*parseFloat(slkcxs)))*100)/100);
}

//added by zhangj start
//����ӡ��˰
function computeYhs(){
	var htzj = changeJE(0.00);
	
	var sbbh = document.forms[0].sbbh.value;
	var fwhdlxdm="";
	var yhszsfs="";
	
	//��ú˶���Ϣ
	var hdxxArr = new Array();
    if(szsmdm_hd_value.length !=0)
    {
		hdxxArr = getHdxx(szsmdm_hd_value,sbbh);

		//�Ƿ��ҵ�
		if(hdxxArr.length != 0)
		{
			htzj = hdxxArr[0][16];
			fwhdlxdm=hdxxArr[0][17];
			yhszsfs=hdxxArr[0][18];
		}
	}
	for(var ii=1;ii<CLFJKMX.rows.length-1;ii++)
	{
		//var obj = rows[ii].all("sffjs");
		//if(!obj) continue;
		var szsmdm = CLFJKMX.rows[ii].all("szsmdm").value;
		var jsje = CLFJKMX.rows[ii].all("jsje");//��˰���
		var sjse = CLFJKMX.rows[ii].all("sjse");//Ӧ�ɽ��
		var jmje = CLFJKMX.rows[ii].all("jmje");//������
		var yjje = CLFJKMX.rows[ii].all("yjje");//ʵ�ɽ��
		var sl = CLFJKMX.rows[ii].all("sl").value; //˰��
		var kssl = CLFJKMX.rows[ii].all("kssl");//��˰����
		
		var szsmStr;
		szsmStr = szsmdm.substr(0,2);
		if(szsmStr == '16')
		{
			jsje.value=changeJE(Math.round(parseFloat(htzj)*100)/100);
			var strValue = changeJE(Math.round(parseFloat(htzj)*parseFloat(sl)*100)/100);
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
			if(yhszsfs==<%=Constants.YHSZSFS_ZS%>){
				sjse.value = strValue;
				jmje.value=changeJE(0.00);	
				yjje.value=strValue;
			}else{
				sjse.value = strValue;
				jmje.value=	strValue;
				yjje.value=changeJE(0.00);				
			}

		}
	}

	//���¼���ϵͳ�ϼ�
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
}

//�����ݺ˶����ʹ��븳ֵ
	function setFwhdlxdm(){
		var fwhdlxdm="";
		var sbbh = document.forms[0].sbbh.value;
	//��ú˶���Ϣ
		var hdxxArr = new Array();
    	if(szsmdm_hd_value.length !=0)
    	{
			hdxxArr = getHdxx(szsmdm_hd_value,sbbh);

		//�Ƿ��ҵ�
			if(hdxxArr.length != 0)
			{
				fwhdlxdm=hdxxArr[0][17];
				if(document.all.fwhdlxdm.value==""||document.all.fwhdlxdm.value==null){
					document.all.fwhdlxdm.value=fwhdlxdm;
				}				
			}
		}
		if(document.all.fwhdlxdm.value=="0"){
			document.all.fwhdlx[0].checked=true;
		}else if(document.all.fwhdlxdm.value==<%=Constants.FWHDLX_NONHOUSING%>){
			document.all.fwhdlx[1].checked=true;
		}else{
			document.all.fwhdlx[0].checked=false;
			document.all.fwhdlx[1].checked=false;
		}		
	}
//����Ƿ���δ��ӵ���˰��Ŀ����
	function checkNsxmdm(){
		var htzj=0;
		var fwhdlxdm="";
		var sbbh = document.forms[0].sbbh.value;
		setFwhdlxdm();		
    	if(szsmlist.length !=0)
    	{
    		var sbbh_his = document.all("sbbh_his").value; //����걨���
    		var ygffpjeTemp = changeJE(0.00);
			var jwyzValue = document.forms[0].fwyz.value; //��÷���ԭֵ
			if(jwyzValue =='NaN' || jwyzValue =="")
				jwyzValue = changeJE(0.00);
		
				//��ú˶���Ϣ��������˰���շ�ʽ
			var hdxxArray = new Array();
			if(szsmdm_hd_value.length !=0)
   			{
				hdxxArray = getHdxx(szsmdm_hd_value,sbbh);
				if(hdxxArray.length != 0)
				{
					ygffpjeTemp = hdxxArray[0][9];
					htzj=hdxxArray[0][16];
					fwhdlxdm=hdxxArray[0][17];
				}	
			}
			for(var i=0;i<szsmlist.length;i++){
				
				//if(szsmlist[i][0].substr(0,2)=="16"&&changeJE(Math.round(parseFloat(htzj)*parseFloat(szsmlist[i][12])*100)/100)<500){
				if(szsmlist[i][0].substr(0,2)=="16"){
					continue;
				}							
				if(fwhdlxdm==<%=Constants.FWHDLX_NONHOUSING%> && szsmlist[i][0]=="050802"){
					continue;
				}else if(fwhdlxdm==<%=Constants.FWHDLX_HOUSING%>){
					if((changeJE(ygffpjeTemp) == "0.00") && (sbbh_his == "" || sbbh_his == "NaN") && (changeJE(jwyzValue) == "0.00") && (szsmlist[i][0] == '050801'))
					{
						continue;
					}
					if(((changeJE(ygffpjeTemp) != "0.00") || (sbbh_his != "" && sbbh_his != "NaN") || (changeJE(jwyzValue) != "0.00")) && (szsmlist[i][0] == '050802'))
					{
						continue;
					}
				}
				
				if(hasExited(szsmlist[i][0])){
					continue;
				}else{
					alert("��˰��Ŀ����["+szsmlist[i][0]+"]δ��ӣ�����Ӻ��ٱ��棡");
					return false;				
				}
			}
			return true;
		}
		return false;

	}
//�����˰��Ŀ�����Ƿ��Ѵ���	
	function hasExited(szsmdm){
		for(var j=1;j<CLFJKMX.rows.length-1;j++){
			if(szsmdm==CLFJKMX.rows[j].all("szsmdm").value){		
				return true;			
			}
		}
		return false;
	}
function init(){
	
if(document.all.fwhdlxdm.value=="1" && (document.all.scriptStr.value=="" ||document.all.scriptStr.value==null) ){
		doSubmitForm('QueryFzfGr');

	}
}
//added by zhangj end
</script>
</BODY></HTML>
