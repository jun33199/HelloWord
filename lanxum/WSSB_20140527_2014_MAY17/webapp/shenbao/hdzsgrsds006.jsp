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
<title>�����к˶����ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰�꣨�������걨</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js">
</script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
</head>
<script language="JavaScript">
<%/*����Ƿ������Ϊ��*/%>
function checkEmpty(){
	if(document.forms[0].qysrze.value == null || document.forms[0].qysrze.value == ""
        || document.forms[0].yssdl.value == null || document.forms[0].yssdl.value == ""){
		alert("�����ܶ��Ӧ˰�����ʲ���Ϊ�գ�");
		return true;
	}
	var size = hdzstable.rows.length-2;
	if(size == 1){
		if(document.forms[0].jmse.value == null || document.forms[0].jmse.value == ""
		|| document.forms[0].qcwjsk.value == null || document.forms[0].qcwjsk.value == ""
		|| document.forms[0].yjnse.value == null || document.forms[0].yjnse.value == ""
                || document.forms[0].sjyjse.value == null || document.forms[0].sjyjse.value == ""){
			alert("����˰��ڳ�δ��˰��ѽ���˰���ʵ��Ӧ��˰���Ϊ�գ�");
			return true;
		}
	}else{
		for(var i = 0; i < size; i++){
			if(document.forms[0].jmse[i].value == null || document.forms[0].jmse[i].value == ""
			|| document.forms[0].qcwjsk[i].value == null || document.forms[0].qcwjsk[i].value == ""
			|| document.forms[0].yjnse[i].value == null || document.forms[0].yjnse[i].value == ""
                	|| document.forms[0].sjyjse[i].value == null || document.forms[0].sjyjse[i].value == ""){
				alert("����˰��ڳ�δ��˰��ѽ���˰���ʵ��Ӧ��˰���Ϊ�գ�");
				return true;
			}
		}
	}
	return false;
}
<%/*�Զ�����*/%>
function srzeChange(){
	var srze = document.forms[0].qysrze.value;
	var yssdl = document.forms[0].yssdl.value;
	document.forms[0].lrze.value = Math.round(srze * yssdl * 100) / 100;
	lrzeDiv.innerText = document.forms[0].lrze.value;
	itemsize = hdzstable.rows.length - 2;
	if(itemsize > 1)
	{
		for(var i=0;i<itemsize;i++){
			document.forms[0].ynssde[i].value = Math.round(document.forms[0].lrze.value * document.forms[0].lrfpbl[i].value )/ 100;
			ynssdeDiv[i].innerText = document.forms[0].ynssde[i].value;
			for(var j = 0; j < document.forms[0].sl.length; j++){
            			var var1 = parseFloat(document.forms[0].ynssde[i].value);
                		var var2 = parseFloat(document.forms[0].ynsqss[j].value);
                		var var3 = parseFloat(document.forms[0].ynszzs[j].value);
                		if(var1 > var2 && (var1 <= var3 || var3 == 0)){
					document.forms[0].sysl[i].value = document.forms[0].sl[j].value;
					syslDiv[i].innerText = document.forms[0].sysl[i].value;
					document.forms[0].sskcs[i].value = document.forms[0].sskcsvo[j].value;
					sskcsDiv[i].innerText = document.forms[0].sskcs[i].value;
                    			break;
                		}else{
					document.forms[0].sysl[i].value = "0";
					syslDiv[i].innerText = document.forms[0].sysl[i].value;
					document.forms[0].sskcs[i].value = "0.00";
					sskcsDiv[i].innerText = document.forms[0].sskcs[i].value;
                		}
            		}
			document.forms[0].ynse[i].value = Math.round((document.forms[0].ynssde[i].value * document.forms[0].sysl[i].value - document.forms[0].sskcs[i].value)*100)/100;
                        ynseDiv[i].innerText = document.forms[0].ynse[i].value;
			lineChange(i);
		}
	}
	else if(itemsize == 1)
	{
		document.forms[0].ynssde.value = Math.round(document.forms[0].lrze.value * document.forms[0].lrfpbl.value )/ 100;
		ynssdeDiv.innerText = document.forms[0].ynssde.value;
		for(var j = 0; j < document.forms[0].sl.length; j++){
            		var var1 = parseFloat(document.forms[0].ynssde.value);
                	var var2 = parseFloat(document.forms[0].ynsqss[j].value);
                	var var3 = parseFloat(document.forms[0].ynszzs[j].value);
                	if(var1 > var2 && (var1 <= var3 || var3 == 0)){
				document.forms[0].sysl.value = document.forms[0].sl[j].value;
				syslDiv.innerText = document.forms[0].sysl.value;
				document.forms[0].sskcs.value = document.forms[0].sskcsvo[j].value;
				sskcsDiv.innerText = document.forms[0].sskcs.value;
                    		break;
                	}else{
				document.forms[0].sysl.value = "0";
				syslDiv[i].innerText = document.forms[0].sysl.value;
				document.forms[0].sskcs.value = "0.00";
				sskcsDiv.innerText = document.forms[0].sskcs.value;
                        }
                }
		document.forms[0].ynse.value = Math.round((document.forms[0].ynssde.value * document.forms[0].sysl.value - document.forms[0].sskcs.value)*100)/100;
		ynseDiv.innerText = document.forms[0].ynse.value;
		lineChange(0);
	}
	computerHj();
}
function computerHj()
{
<logic:equal name="hdzsgrsdsForm" property="done" value='true'>
	itemsize = hdzstable.rows.length - 2;
	if(itemsize > 1)
	{
		var hj = 0;
		for(var i=0;i<itemsize;i++){
			hj = Math.round((parseFloat(hj) + parseFloat(document.forms[0].sjyjse[i].value))*100)/100;
			document.forms[0].hjDiv.value = hj;
		}
	} else if(itemsize == 1){
		document.forms[0].hjDiv.value = document.forms[0].sjyjse.value;
	}
	formatCurrency(document.forms[0].hjDiv,0);
</logic:equal>
}

function lineChange(i){
<%/*�ڳ�δ��˰����ѽ���˰��¼�루���ڵ���0��*/%>
<%/*ʵ��Ӧ��˰��ȱʡ��Ϊ0�������޸ģ����ڵ���0��*/%>
<%/*ʵ��Ӧ��˰��=Ӧ��˰�����˰��ڳ�δ��˰��ѽ���˰��*/%>
	itemsize = hdzstable.rows.length - 2;
	if(itemsize == 1){
		var ynse = parseFloat(document.forms[0].ynse.value);
		var jmse = parseFloat(document.forms[0].jmse.value);
        	var qcwjse = parseFloat(document.forms[0].qcwjsk.value);
        	var yjnse = parseFloat(document.forms[0].yjnse.value);
        	if(qcwjse < 0){
        		alert("�ڳ�δ��˰�������ڵ���0��");
                	document.forms[0].qcwjsdse[i].focus();
                	document.forms[0].qcwjsdse[i].select();
                	return;
        	}
        	var sjyjse = Math.round((ynse - jmse + qcwjse - yjnse)*100)/100;
        	if(sjyjse < 0){
        		sjyjse = 0;
        	}
        	document.forms[0].sjyjse.value = sjyjse;
	}else{
		var ynse = parseFloat(document.forms[0].ynse[i].value);
		var jmse = parseFloat(document.forms[0].jmse[i].value);
        	var qcwjse = parseFloat(document.forms[0].qcwjsk[i].value);
        	var yjnse = parseFloat(document.forms[0].yjnse[i].value);
        	if(qcwjse < 0){
        		alert("�ڳ�δ��˰�������ڵ���0��");
                	document.forms[0].qcwjsdse[i].focus();
                	document.forms[0].qcwjsdse[i].select();
                	return;
        	}
        	var sjyjse = Math.round((ynse - jmse + qcwjse - yjnse)*100)/100;
        	if(sjyjse < 0){
        		sjyjse = 0;
        	}
        	document.forms[0].sjyjse[i].value = sjyjse;
        }
	computerHj();
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
	document.forms[0].qysrze.value = "0.00";
	itemsize = hdzstable.rows.length - 2;
	if(itemsize > 1)
	{
		for(var i=0;i<itemsize;i++){
			document.forms[0].jmse[i].value = "0.00";
			document.forms[0].qcwjsk[i].value = "0.00";
			document.forms[0].yjnse[i].value = "0.00";
			document.forms[0].sjyjse[i].value = "0.00";
		}
	} else if(itemsize == 1){
		document.forms[0].jmse.value = "0.00";
		document.forms[0].qcwjsk.value = "0.00";
		document.forms[0].yjnse.value = "0.00";
		document.forms[0].sjyjse.value = "0.00";
	}
	computerHj();
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
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="javascript: computerHj()">

<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr>
    		<td align="center" colspan=2>

    			<jsp:include page="../include/SbHeader.jsp" flush="true" >
    				<jsp:param name="name" value="�˶����ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰��(��)���걨��" />
    			</jsp:include>
    			<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
	  			<tr>
	    				<td valign="top">
                                        	<br>
	     					<%@//include file="../include/ShowErrMsg.jsp"%>
	      					<html:errors/>
	     					<table  align="center" cellspacing="0" class="table-99">
	        					<tr>
	          						<td class="1-td1">�˶����ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰��(��)���걨��</td>
	        					</tr>

						<html:form action="/hdzsgrsds.do" method="post">
							<html:hidden name="hdzsgrsdsForm" property="actionType"/>
<logic:equal name="hdzsgrsdsForm" property="done" value='true'>

							<!-- ҳ����û�����ֳ�����������-->
							<logic:iterate id="slbsj" indexId="indexSlb" name="hdzsgrsdsForm" property="slbsjList"
								type="com.ttsoft.bjtax.shenbao.model.domain.Szsm">
								<html:hidden name="slbsj" property="sl"/>
								<html:hidden name="slbsj" property="szsmdm"/>
								<html:hidden name="slbsj" property="szsmmc"/>
								<html:hidden name="slbsj" property="ynsqss"/>
								<html:hidden name="slbsj" property="ynszzs"/>
								<input name="sskcsvo" type="hidden" value="<bean:write name='slbsj' property='sskcs'/>">
							</logic:iterate>
							<input name="cjsj" type="hidden" value="<bean:write name='hdzsgrsdsForm' property='qysbsj.cjsj'/>"><!-- ����ʱ��-->
							<input name="lrrq" type="hidden" value="<bean:write name='hdzsgrsdsForm' property='qysbsj.lrrq'/>"><!-- ¼������-->
							<input name="swdjzh" type="hidden" value="<bean:write name='hdzsgrsdsForm' property='qysbsj.swdjzh'/>"><!--˰��Ǽ�֤�� -->
							<input name="swjgzzjgdm" type="hidden" value="<bean:write name='hdzsgrsdsForm' property='qysbsj.swjgzzjgdm'/>"><!--˰�������֯�������� -->
							<input name="lrr" type="hidden" value="<bean:write name='hdzsgrsdsForm' property='qysbsj.lrr'/>"><!-- ¼���˴���-->
							<!--  END-->
	        					<tr>
	          						<td class="1-td2"><br>
	            							<table cellspacing="0" class="table-99">
                      								<tr class="black9">
                         								<td align="center" nowrap>
												<div align=left>�걨���ڣ�
													<input name="sbrq" type="hidden" value="<shenbao:write name='hdzsgrsdsForm' property='qysbsj.sbrq'/>">
													<shenbao:write name='hdzsgrsdsForm' property='qysbsj.sbrq'/>
                                								</div>
                                                                                        </td>
											<td align=left nowrap>�걨�������� ��
                                                                                        	<input name="skssksrq" type="hidden" value="<shenbao:write name='hdzsgrsdsForm' property='qysbsj.skssksrq'/>">
												<shenbao:write name='hdzsgrsdsForm' property='qysbsj.skssksrq'/>
                                                                                                ��
												<input name="skssjsrq" type="hidden" value="<shenbao:write name='hdzsgrsdsForm' property='qysbsj.skssjsrq'/>">
                                                                                                <shenbao:write name='hdzsgrsdsForm' property='qysbsj.skssjsrq'/>
								                        </td>
                        								<td align="center" nowrap><div align="right">��λ�������Ԫ</div></td>
                      								</tr>
                    							</table>
	            							<br>
	            							<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr class="black9">
                        <td nowrap class="2-td2-t-left"><div align="right">˰����������&nbsp;&nbsp;</div></td>
                      	<td nowrap class="2-td2-t-left"><div align="left">
				<input name="jsjdm" type="hidden" value="<bean:write name='hdzsgrsdsForm' property='qysbsj.jsjdm'/>">
				<bean:write name='hdzsgrsdsForm' property='qysbsj.jsjdm'/>
			</div></td>

                      	<td nowrap class="2-td2-t-left"><div align="right">��λ����&nbsp;&nbsp;</div></td>
                      	<td nowrap class="2-td2-t-left"><div align="left"><bean:write name='hdzsgrsdsForm' property='qysbsj.nsrmc'/></div></td>
			<input name="nsrmc" type="hidden" value="<bean:write name='hdzsgrsdsForm' property='qysbsj.nsrmc'/>">
                      	<td nowrap class="2-td2-t-center" colspan="2">&nbsp;</td>
								                </tr>
							                      	<tr class="black9">
                      	<td nowrap class="2-td2-left"><div align="right">�����ܶ�(1)</div></td>
                      	<td nowrap class="2-td2-left"><input maxlength="16" name="qysrze" value="<shenbao:write name='hdzsgrsdsForm' property='qysbsj.qysrze'/>" onchange="return(checkvalue(this,0)&&formatCurrency(this,0)&&srzeChange());"></td>
                      	<td nowrap class="2-td2-left"><div align="right">Ӧ˰������(2)</div></td>
                      	<td nowrap class="2-td2-left"><div align="left"><input name="yssdl" value="<bean:write name='hdzsgrsdsForm' property='qysbsj.yssdl'/>" onchange="return(checkvalue(this, 3)&&srzeChange());"></div></td>

                      	<td nowrap class="2-td2-left"><div align="right">�����ܶ�(3)=(1)��(2)</div></td>
                      	<td nowrap class="2-td2-center"><div align="left" id="lrzeDiv"><shenbao:write name='hdzsgrsdsForm' property='qysbsj.lrze'/></div></td>
				<input name="lrze" type="hidden" value="<bean:write name='hdzsgrsdsForm' property='qysbsj.lrze'/>">
                								</tr>
						                    	</table>
	            							<br>
	            							<table id="hdzstable" width="100%" border="0" cellpadding="0" cellspacing="0">
							                	<tr>
								                      	<td nowrap class="2-td1-left">Ͷ��������</td>
								                      	<td nowrap class="2-td1-left">Ͷ�������֤������</td>
                      									<td nowrap class="2-td1-left">����������<br>(4)</td>
                      									<td nowrap class="2-td1-left">Ӧ��˰���ö�<br>(5)=(3)��(4)</td>
                      									<td nowrap class="2-td1-left">����˰��<br>(6)</td>
                      									<td nowrap class="2-td1-left">����۳���<br>(7)</td>
                      									<td nowrap class="2-td1-left">Ӧ��˰��<br>(8)=<br>(5)��(6)-(7)</td>
                      									<td nowrap class="2-td1-left">����˰��<br>(9)</td>
                      									<td nowrap class="2-td1-left">�ڳ�δ��˰��<br>(10)</td>
                      									<td nowrap class="2-td1-left">�ѽ���˰��<br>(11)</td>
                      									<td nowrap class="2-td1-center">ʵ��Ӧ��˰��<br>(12)=<br>(8)-(9)+(10)-(11)</td>
                    								</tr>
                    								<logic:iterate id="data" indexId="index" name="hdzsgrsdsForm" property="tzzsbsjList" type="com.ttsoft.bjtax.shenbao.model.domain.Hdzstzzsb">
                <tr>
                        <td nowrap class="2-td2-left"><bean:write name='data' property='tzzxm'/></td>
			<input name="tzzxm" type="hidden" value="<bean:write name='data' property='tzzxm'/>">
                        <td nowrap class="2-td2-left"><bean:write name='data' property='zjhm'/></td>
			<input name="zjhm" type="hidden" value="<bean:write name='data' property='zjhm'/>">
                        <td nowrap class="2-td2-left"><bean:write name='data' property='lrfpbl'/>%</td>
			<input name="lrfpbl" type="hidden" value="<bean:write name='data' property='lrfpbl'/>">
                        <td nowrap class="2-td2-left"><div id="ynssdeDiv"><shenbao:write name='data' property='ynssde'/></div></td>
			<input name="ynssde" type="hidden" value="<bean:write name='data' property='ynssde'/>">
                        <td nowrap class="2-td2-left"><div id="syslDiv"><bean:write name='data' property='sysl'/></div></td>
			<input name="sysl" type="hidden" value="<bean:write name='data' property='sysl'/>">
                        <td nowrap class="2-td2-left"><div id="sskcsDiv"><shenbao:write name='data' property='sskcs'/></div></td>
			<input name="sskcs" type="hidden" value="<bean:write name='data' property='sskcs'/>">
                        <td nowrap class="2-td2-left"><div id="ynseDiv"><shenbao:write name='data' property='ynse'/></div></td>
			<input name="ynse" type="hidden" value="<bean:write name='data' property='ynse'/>">
                        <td nowrap class="2-td2-left"><input maxlength="16" name="jmse" size="10" value="<shenbao:write name='data' property='jmse'/>" onchange="return(checkvalue(this,0)&&formatCurrency(this,0)&&lineChange(<bean:write name='index'/>));"></td>
                        <td nowrap class="2-td2-left"><input maxlength="16" name="qcwjsk" size="10" value="<shenbao:write name='data' property='qcwjsk'/>" onchange="return(checkvalue(this,0)&&formatCurrency(this,0)&&lineChange(<bean:write name='index'/>));"></td>
                        <td nowrap class="2-td2-left"><input maxlength="16" name="yjnse" size="10" value="<shenbao:write name='data' property='yjnse'/>" onchange="return(checkvalue(this,0)&&formatCurrency(this,0)&&lineChange(<bean:write name='index'/>));"></td>
                        <td nowrap class="2-td2-center"><input maxlength="16" name="sjyjse" size="10" class="inputnoborder" readOnly tabIndex="-1" value="<shenbao:write name='data' property='sjyjse'/>" onchange="return(checkvalue(this,0)&&formatCurrency(this,0)&&computerHj());"></td>
                	<html:hidden name="data" property="zjlxdm"/><!--֤�����ʹ���-->
		</tr>
                								</logic:iterate>
                    								<tr>
                      									<td nowrap class="2-td2-left" colspan="10">˰��ϼ�</td>
								                      	<td nowrap class="2-td2-center" colspan="1">
																	<input name="hjDiv" class="inputnoborder" readOnly tabIndex="-1" value="0.00">
														</td>
                    								</tr>
                                                                        </table>
									<br><br>
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
<logic:equal name="hdzsgrsdsForm" property="done" value='false'>
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