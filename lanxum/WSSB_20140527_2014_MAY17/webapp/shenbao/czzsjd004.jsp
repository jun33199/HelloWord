<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<html>
<head>
<title>�����в������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰�����걨</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
</head>
<script language=javascript>
<%/*����Ƿ������Ϊ��*/%>
function checkEmpty(){
	if(document.forms[0].lrze.value == null || document.forms[0].lrze.value == ""){
		alert("�����ܶ��Ϊ�գ�");
		return true;
	}
	var size = jdTable.rows.length-2;
	if(size == 1){
		if(document.forms[0].qcwjsdse.value == null || document.forms[0].qcwjsdse.value == ""
		|| document.forms[0].yjnsdse.value == null || document.forms[0].yjnsdse.value == ""
                || document.forms[0].sjyjse.value == null || document.forms[0].sjyjse.value == ""){
			alert("�ڳ�δ��˰��ѽ���˰���ʵ��Ӧ��˰���Ϊ�գ�");
			return true;
		}
	}else{
		for(var i = 0; i < size; i++){
			if(document.forms[0].qcwjsdse[i].value == null || document.forms[0].qcwjsdse[i].value == ""
			|| document.forms[0].yjnsdse[i].value == null || document.forms[0].yjnsdse[i].value == ""
                	|| document.forms[0].sjyjse[i].value == null || document.forms[0].sjyjse[i].value == ""){
				alert("�ڳ�δ��˰��ѽ���˰���ʵ��Ӧ��˰���Ϊ�գ�");
				return true;
			}
		}
	}
	return false;
}

function formatDIV(obj)
{
	var tmpValue = trim(obj.innerText);
	var pointIndex = tmpValue.indexOf(".");
	if(pointIndex < 0)
	{
		if(tmpValue == null || tmpValue == "" || tmpValue == ".")
		{
			tmpValue = "0.00";
		}
		else
		{
			tmpValue += ".00";
		}
	}
	else
	{
		afterpoint = (tmpValue.length-1) - pointIndex;
		if(afterpoint == 0)
		{
			tmpValue += "00";
		}
		else if(afterpoint == 1)
		{
			tmpValue += "0";
		}
	}
	obj.innerText = tmpValue;
}
<%/*�Զ�����*/%>
function lineChange(i){
<%/*ʵ��Ӧ��˰��=Ӧ��˰��ڳ�δ��˰��ѽ���˰��*/%>
<%/*�ڳ�δ��˰����ѽ���˰��¼�루���ڵ���0��*/%>
<%/*ʵ��Ӧ��˰��ȱʡ��Ϊ0�������޸ģ����ڵ���0��*/%>
	if(document.forms[0].qcwjsdse.length == null){
		var ynse = parseFloat(ynsdseDiv.innerText);
		var qcwjse = parseFloat(document.forms[0].qcwjsdse.value);
		var yjnse = parseFloat(document.forms[0].yjnsdse.value);
		if(qcwjse < 0){
			alert("�ڳ�δ��˰�������ڵ���0��");
			document.forms[0].qcwjsdse.focus();
			document.forms[0].qcwjsdse.select();
			return;
		}
		var sjyjse = Math.round((ynse + qcwjse - yjnse)*100)/100;
		if(sjyjse < 0){
			sjyjse = 0;
		}
		document.forms[0].sjyjse.value = sjyjse;
		formatCurrency(document.forms[0].sjyjse,0);
		document.forms[0].hjDiv.value = document.forms[0].sjyjse.value;
		formatCurrency(document.forms[0].hjDiv,0);
	}else{
		var ynse = parseFloat(ynsdseDiv[i].innerText);
		var qcwjse = parseFloat(document.forms[0].qcwjsdse[i].value);
		var yjnse = parseFloat(document.forms[0].yjnsdse[i].value);
		if(qcwjse < 0){
			alert("�ڳ�δ��˰�������ڵ���0��");
			document.forms[0].qcwjsdse[i].focus();
			document.forms[0].qcwjsdse[i].select();
			return;
		}
		var sjyjse = Math.round((ynse + qcwjse - yjnse)*100)/100;
		if(sjyjse < 0){
			sjyjse = 0;
		}
		document.forms[0].sjyjse[i].value = sjyjse;
		formatCurrency(document.forms[0].sjyjse[i],0);
		var hj = 0;
		for(var k = 0; k < document.forms[0].qcwjsdse.length; k++){
			hj = Math.round((hj + parseFloat(document.forms[0].sjyjse[k].value))*100)/100;
			document.forms[0].hjDiv.value = hj;
		}
		formatCurrency(document.forms[0].hjDiv,0);
	}
}
function lrzeChange(){
<logic:equal name="czzsjdForm" property="done" value='true'>

	 var jd = parseFloat(document.forms[0].jd.value);
	 if(document.forms[0].qcwjsdse.length == null){
		document.forms[0].ynssde.value = Math.round(document.forms[0].lrze.value * document.forms[0].fpbl.value)/100;
		ynssdeDiv.innerText = document.forms[0].ynssde.value;
		if(document.forms[0].lrze.value < 0){
                	document.forms[0].ynsdse.value = 0;
            		ynsdseDiv.innerText = 0;
        	}else{
           		for(var j = 0; j < document.forms[0].sl.length; j++){
       				var var1 = Math.round(parseFloat(document.forms[0].ynssde.value) / jd * 400)/100;
           			var var2 = parseFloat(document.forms[0].ynsqss[j].value);
           			var var3 = parseFloat(document.forms[0].ynszzs[j].value);
           			if(var1 > var2 && (var1 <= var3 || var3 == 0)){
           				document.forms[0].ynsdse.value = Math.round((document.forms[0].ynssde.value * document.forms[0].sl[j].value - document.forms[0].sskcsvo[j].value)*100)/100;
               				ynsdseDiv.innerText = document.forms[0].ynsdse.value;
               				break;
               			}else{
					document.forms[0].ynsdse.value = "0.00";
					ynsdseDiv.innerText = document.forms[0].ynsdse.value;
           			}
       			}
		}
		formatDIV(ynssdeDiv);
		formatDIV(ynsdseDiv);
		lineChange(0);
	 }else{
		for(var i = 0; i < document.forms[0].ynssde.length; i++){
                	document.forms[0].ynssde[i].value = Math.round(document.forms[0].lrze.value * document.forms[0].fpbl[i].value)/100;
                	ynssdeDiv[i].innerText = document.forms[0].ynssde[i].value;

                	if(document.forms[0].lrze.value < 0){
                		document.forms[0].ynsdse[i].value = 0;
            			ynsdseDiv[i].innerText = 0;
        		}else{
                		for(var j = 0; j < document.forms[0].sl.length; j++){
            				var var1 = Math.round(parseFloat(document.forms[0].ynssde[i].value) / jd * 400)/100;
                			var var2 = parseFloat(document.forms[0].ynsqss[j].value);
                			var var3 = parseFloat(document.forms[0].ynszzs[j].value);
                			if(var1 > var2 && (var1 <= var3 || var3 == 0)){
                    				document.forms[0].ynsdse[i].value = Math.round((document.forms[0].ynssde[i].value * document.forms[0].sl[j].value - document.forms[0].sskcsvo[j].value)*100)/100;
                    				ynsdseDiv[i].innerText = document.forms[0].ynsdse[i].value;
                    				break;
                			}else{
						document.forms[0].ynsdse[i].value = "0.00";
						ynsdseDiv[i].innerText = document.forms[0].ynsdse[i].value;
                			}
            			}
			}
			lineChange(i);
			formatDIV(ynssdeDiv[i]);
			formatDIV(ynsdseDiv[i]);
		}
	}
</logic:equal>

}
<%/*����*/%>
function doSave(){
	if(!checkEmpty() && confirm(confirmSave)){
    		document.forms[0].actionType.value = "Save";
		document.forms[0].submit();
    		return true;
    	}else{
        	return false;
    	}
}
<%/*���*/%>
function doClear(){
	document.forms[0].lrze.value = 0;
	var conSize = jdTable.rows.length - 2;
	if(conSize > 1)
	{
		for(var i=0;i<conSize;i++)
        	{
			document.forms[0].qcwjsdse[i].value = 0.00;
			document.forms[0].yjnsdse[i].value = 0.00;
			document.forms[0].sjyjse[i].value = 0.00;
		}
	}
	else if(conSize == 1)
	{
        	document.forms[0].qcwjsdse.value = 0.00;
                document.forms[0].yjnsdse.value = 0.00;
                document.forms[0].sjyjse.value = 0.00;
        }
	lrzeChange();
    	return false;
}
<%/*����*/%>
function doReturn(){
	if(confirm(confirmReturn)){
        	document.forms[0].actionType.value = "Return";
		document.forms[0].submit();
        	return true;
    	}else{
        	return false;
    	}
}
<%/*ɾ��*/%>
function doDelete(){
	if(confirm(confirmDelete)){
        	document.forms[0].actionType.value = "Delete";
		document.forms[0].submit();
        	return true;
    	}else{
        	return false;
    	}
}

</script>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="javascript: lrzeChange();">

<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>

    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="�������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰�����걨" />
		<jsp:param name="help_url" value="help/wssb/sbzl/czzsjd/czzsjd-000.htm"/>
    	</jsp:include>

    	<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td valign="top">
	     <br>
	      <html:errors/>

	     <table  align="center" cellspacing="0" class="table-99">
		<!-- write your code here-->
	        <tr>
	          <td class="1-td1">�����в������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰�����걨��</td>
	        </tr>
<html:form action="czzsjd.do" method="post">
	<html:hidden property="actionType"/>

<logic:equal name="czzsjdForm" property="done" value='true'>

<logic:iterate id="slbsj" indexId="indexSlb" name="czzsjdForm" property="slbsjList">
	<html:hidden name="slbsj" property="sl"/>
	<html:hidden name="slbsj" property="szsmdm"/>
	<html:hidden name="slbsj" property="szsmmc"/>
	<html:hidden name="slbsj" property="ynsqss"/>
	<html:hidden name="slbsj" property="ynszzs"/>
	<input name="sskcsvo" type="hidden" value="<bean:write name='slbsj' property='sskcs'/>">
</logic:iterate>
<input name="nd" type="hidden" value="<bean:write name='czzsjdForm' property='qysbsj.nd'/>"><%//!-- ���--%>
<input name="jd" type="hidden" value="<bean:write name='czzsjdForm' property='qysbsj.jd'/>"><%//!-- ����--%>
<input name="cjsj" type="hidden" value="<shenbao:write name='czzsjdForm' property='qysbsj.cjrq'/>"><%!//-- ����ʱ��--%>
<input name="swdjzh" type="hidden" value="<bean:write name='czzsjdForm' property='qysbsj.swdjzh'/>"><%!//--˰��Ǽ�֤�� --%>
<input name="swjgzzjgdm" type="hidden" value="<bean:write name='czzsjdForm' property='qysbsj.swjgzzjgdm'/>"><%!//--˰�������֯�������� --%>
<input name="lrr" type="hidden" value="<bean:write name='czzsjdForm' property='qysbsj.lrr'/>"><%!//-- ¼���˴���--%>
<input name="lrrq" type="hidden" value="<shenbao:write name='czzsjdForm' property='qysbsj.lrrq'/>"><%!//-- ¼������--%>

<tr>
	<td class="1-td2"><br>
<TABLE cellSpacing=0 class=table-99>
	<TBODY>
        	<TR class=black9>
                	<TD align=middle noWrap>
                        	<DIV align=left>�걨���ڣ�
<input name="sbrq" type="hidden" value="<shenbao:write name='czzsjdForm' property='qysbsj.sbrq'/>">
<shenbao:write name='czzsjdForm' property='qysbsj.sbrq'/>
                                </DIV>
			</TD>
                      	<TD align=left noWrap>�걨�������� ��
<input name="skssksrq" type="hidden" value="<shenbao:write name='czzsjdForm' property='qysbsj.skssksrq'/>">
<shenbao:write name='czzsjdForm' property='qysbsj.skssksrq'/>
��
<input name="skssjsrq" type="hidden" value="<shenbao:write name='czzsjdForm' property='qysbsj.skssjsrq'/>">
<shenbao:write name='czzsjdForm' property='qysbsj.skssjsrq'/>
                        </TD>
                      	<TD align=middle noWrap>
                        	<DIV align=right>��λ�������Ԫ</DIV>
			</TD>
		</TR>
	</TBODY>
</TABLE><br>
<TABLE border=0 cellPadding=0 cellSpacing=0 width="100%">
	<TBODY>
        	<TR class=black9>
                	<TD class=2-td2-t-left noWrap>
                        	<DIV align=right>˰����������&nbsp;</DIV>
			</TD>
                      	<TD class=2-td2-t-left noWrap>
                        	<DIV align=left>&nbsp;
<input name="jsjdm" type="hidden" value="<bean:write name='czzsjdForm' property='qysbsj.jsjdm'/>">
<bean:write name='czzsjdForm' property='qysbsj.jsjdm'/>
				</DIV>
			</TD>
                      	<TD class=2-td2-t-left noWrap>
                        	<DIV align=right>��λ����&nbsp;</DIV>
			</TD>
                      	<TD class=2-td2-t-left noWrap>
                        	<DIV align=left>&nbsp;
<input name="nsrmc" type="hidden" value="<bean:write name='czzsjdForm' property='qysbsj.nsrmc'/>">
<bean:write name='czzsjdForm' property='qysbsj.nsrmc'/>
                                </DIV>
			</TD>
                      	<TD class=2-td2-t-left noWrap>
                        	<DIV align=right>�����ܶ�(1)&nbsp;</DIV>
			</TD>
                      	<TD class=2-td2-t-center>
                        	<DIV align=center>&nbsp;
<input maxlength="16" name="lrze" value="<shenbao:write name='czzsjdForm' property='qysbsj.lrze'/>" onchange="return(checkvalue(this,1)&&formatCurrency(this,0)&&lrzeChange());">
                                </DIV>
			</TD>
		</TR>
	</TBODY>
</TABLE><BR>
<TABLE border=0 cellPadding=0 cellSpacing=0 width="100%" id="jdTable">
	<TBODY>
        <TR>
        	<TD class=2-td1-left noWrap>Ͷ��������</TD>
                <TD class=2-td1-left noWrap>Ͷ�������֤������</TD>
                <TD class=2-td1-left noWrap>����������<br>(2)</TD>
                <TD class=2-td1-left noWrap>Ӧ��˰���ö�<br>(3)=(1)��(2)</TD>
                <TD class=2-td1-left noWrap>Ӧ��˰��<br>(4)</TD>
                <TD class=2-td1-left noWrap>�ڳ�δ��˰��<br>(5)</TD>
                <TD class=2-td1-left noWrap>�ѽ���˰��<br>(6)</TD>
                <TD class=2-td1-center noWrap>ʵ��Ӧ��˰��<br>(7)=(4)+(5)-(6)</TD></TR>
<logic:iterate id="data" indexId="index" name="czzsjdForm" property="tzzsbsjList"
	type="com.ttsoft.bjtax.shenbao.model.domain.Czzsjbtzzsj">
        <TR>
        	<TD class=2-td2-left noWrap><bean:write name='data' property='tzzxm'/></TD>
<input name="tzzxm" type="hidden" value="<bean:write name='data' property='tzzxm'/>">
                <TD class=2-td2-left noWrap><bean:write name='data' property='zjhm'/></TD>
<input name="zjhm" type="hidden" value="<bean:write name='data' property='zjhm'/>">
                <TD class=2-td2-left noWrap><bean:write name='data' property='fpbl'/>%</TD>
<input name="fpbl" type="hidden" value="<bean:write name='data' property='fpbl'/>">
                <TD class=2-td2-left noWrap><div id="ynssdeDiv" align="right"><shenbao:write name='data' property='ynssde'/></div></TD>
<input name="ynssde" type="hidden" value="<bean:write name='data' property='ynssde'/>">
                <TD class=2-td2-left noWrap><div id="ynsdseDiv" align="right"><shenbao:write name='data' property='ynsdse'/></div></TD>
<input name="ynsdse" type="hidden" value="<bean:write name='data' property='ynsdse'/>">
		<TD class=2-td2-left noWrap><input maxlength="16" name="qcwjsdse" size="15" value="<shenbao:write name='data' property='qcwjsdse'/>" onchange="return(checkvalue(this,0)&&formatCurrency(this,0)&&lineChange(<bean:write name='index'/>));"></TD>
                <TD class=2-td2-left noWrap><input maxlength="16" name="yjnsdse" size="15" value="<shenbao:write name='data' property='yjnsdse'/>" onchange="return(checkvalue(this,0)&&formatCurrency(this,0)&&lineChange(<bean:write name='index'/>));"></TD>
                <TD class=2-td2-center noWrap><input maxlength="16" name="sjyjse" size="15" class="inputnoborder" readOnly tabIndex="-1" value="<shenbao:write name='data' property='sjyjse'/>" onchange="return(checkvalue(this,0)&&formatCurrency(this,0)&&lineChange(<bean:write name='index'/>));"></TD>
                <html:hidden name="data" property="sysl"/><%!//--����˰��--%>
		<html:hidden name="data" property="sskcs"/><!--����۳���-->
                <html:hidden name="data" property="zjlxdm"/><%//֤�����ʹ���%>
		<html:hidden name="data" property="jmse"/><%!//����˰��%>
	</TR>
</logic:iterate>
        <TR>
        	<TD class=2-td2-left colSpan=7 noWrap><div align="right">�ϼƣ�&nbsp;</div></TD>
                <TD class=2-td2-center noWrap>
			<input maxlength="16" size="15"  name="hjDiv" class="inputnoborder" readOnly tabIndex="-1" value="0.00">
                </TD>
	</TR>
	</TBODY>
</TABLE><BR><BR>
<TABLE border=0 cellPadding=0 cellSpacing=0 width="100%">
        <TBODY>
        	<TR>
			<TD height="40" colspan="8"> <DIV align="center">
				<img src="<%=static_contextpath%>images/baocun1.jpg" onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="����" onclick="doSave();" style="cursor:hand">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<img src="<%=static_contextpath%>images/shanchu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/shanchu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/shanchu1.jpg'" alt="ɾ��" onclick="doDelete();" style="cursor:hand">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="doReturn();" style="cursor:hand">
			<BR><BR></DIV>
			</TD>
		</TR>
	</TBODY>
</TABLE><BR>
	</td>
    </tr>
</logic:equal>
<logic:equal name="czzsjdForm" property="done" value='false'>
	<tr>
		<td class="1-td1">
			<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="doReturn();" style="cursor:hand">
		</td>
	</tr>
</logic:equal>
</html:form>
	     </table>
	    </td>
	  </tr>
    	</table>
    </td>
  </tr>
  <tr>
    <td valign="bottom" align="center">
	<%@ include file="../include/bottom.jsp" %>
    </td>
  </tr>
 </table>
</body>
</html>
