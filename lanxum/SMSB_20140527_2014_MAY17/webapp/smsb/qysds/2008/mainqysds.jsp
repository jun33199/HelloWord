<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm"%>
<jsp:useBean
	type="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm"
	scope="request" id="qysdsNewForm" />

<html>
<head>
<title>��ҵ����˰�����˰�걨</title>
<!--modify 20141011  -->
<html:base/>
<!-- end -->
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/function.js"></script>
</head>
<script language="JavaScript">
	
	// ����û��Ƿ�������������
	function check_jsjdm(){
		if(trim(document.forms[0].jsjdm.value) == "" || document.forms[0].nsrmc.value == ""){
			alert("��������˰��ҵ��������룡");
			document.forms[0].jsjdm.focus();
			document.forms[0].jsjdm.select();
			return false;
		}
		return true;
	}
	
	// ���˰�����������ڲ�ѯ֮���Ƿ��޸Ĺ�
	function checkChangeOfSkssrq(){
		ksrq=document.forms[0].skssksrq.value;
		jsrq=document.forms[0].skssjsrq.value;
		h_ksrq=document.forms[0].hid_skssksrq.value;
		h_jsrq=document.forms[0].hid_skssjsrq.value;
		
		if(h_ksrq==""||h_jsrq==""){
			return true;
		}
		if(h_ksrq!=ksrq||h_jsrq!=jsrq){
			if(confirm("���޸���˰�������ڣ��밴���µ�˰��������ִ�в�ѯ����������˰��������Ϊ���һ�γɹ���ѯʱ��˰�������ڣ�Ҫ������")){
					document.forms[0].skssksrq.value=h_ksrq;
					document.forms[0].skssjsrq.value=h_jsrq;
			}
			return false;
		}
		
		return true;
		
	}
	//�ûس�ʱ�ƶ����㣨����������������⣩
	function moveFocus(){
		if(event.keyCode==13) event.keyCode = 9;
	}
	
	//��Ӧ���������Ļس���ѯ
	function jsjdmQuery(){
		if(event.keyCode==13) doQuery();
	}
	
	//������������ѯ
	function doQuery()
	{
		if(trim(document.forms[0].jsjdm.value) == ""){
			alert("��������˰��ҵ��������룡");
			document.forms[0].jsjdm.focus();
			return false;
		}
	
		if(!isDate(document.forms[0].sbrq, "true")){
			document.forms[0].sbrq.focus();
			return false;
		}
		
		if(!compareDate(document.forms[0].skssjsrq)){
			return false;
		}
		
		doSubmitForm('Query');
	}
	
	//����ȫ��У��
	function doCheck(){
		if (!check_jsjdm()){
			return false;
		}
		
		if(!checkChangeOfSkssrq()){
			return false;
		}
		doSubmitForm('Check');
	}
	
	<%/*�жϱȽ�˰����������*/%>
	function compareDate(obj){
		
		if(!isDate(obj, "true")){
			return false;
		}
		
		var strDate1 = document.forms[0].skssksrq.value;		
		var strYear1 = strDate1.substr(0,4);		
		var strMonth1 = strDate1.substr(4,2);
 	 	var strDay1 = strDate1.substr(6,2);
 	 	
 	 	var strDate2 = document.forms[0].skssjsrq.value;
 	 	var strYear2 = strDate2.substr(0,4);		
		var strMonth2 = strDate2.substr(4,2);
 	 	var strDay2 = strDate2.substr(6,2);
 	 	
 	 	var sksbrq = document.forms[0].sbrq.value;
 	 	var strYear3 = sksbrq.substr(0,4);		
		var strMonth3 = sksbrq.substr(4,2);
 	 	var strDay3 = sksbrq.substr(6,2); 
 	 	
	  	var objDate1 = new Date(strMonth1+'-'+strDay1+'-'+strYear1);
	  	var objDate2 = new Date(strMonth2+'-'+strDay2+'-'+strYear2);
	  	var objDate3 = new Date(strMonth3+'-'+strDay3+'-'+strYear3);
	  	
	  	if(objDate2>objDate3){
	  		alert("˰�����ʱ�䲻�ܴ����걨����");
	  		window.event.returnValue=false;
			document.forms[0].skssjsrq.focus();
			document.forms[0].skssjsrq.select();
			return false;
	  	}
  	  	
  		if(objDate1>=objDate2){
			if(obj == document.forms[0].skssjsrq){
				alert("˰�����ʱ�䲻��С�ڻ����˰�ʼʱ��");
			}else{
				alert("˰�ʼʱ�䲻�ܴ��ڻ����˰�����ʱ��");	
			}
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;
		}
		
		if(strYear1!=strYear2){
			alert("˰�ʼʱ����˰�����ʱ�����ݲ�ͬ��˰��ܿ��꣡");
			return false;				
		}
		
		if(parseInt(strYear1)<2008){
			alert("������ʹ�õ���ҵ����˰�걨����¼��ϵͳΪ2008�棬�ð汾֧�ֵ���С˰�����Ϊ2008��\n2007����2007����ǰ��˰�������ʹ�þɰ汾ϵͳ��");
			return false;	
		}
		
		return true;
	}
	
	//�жϱȽ�˰����������
	function compareDate2Save(obj){
		
		var strDate1 = document.forms[0].skssksrq.value;		
		var strYear1 = strDate1.substr(0,4);		
		var strMonth1 = strDate1.substr(4,2);
 	 	var strDay1 = strDate1.substr(6,2);
 	 	
 	 	var strDate2 = document.forms[0].skssjsrq.value;
 	 	var strYear2 = strDate2.substr(0,4);		
		var strMonth2 = strDate2.substr(4,2);
 	 	var strDay2 = strDate2.substr(6,2);
 	 	
 	 	var sksbrq = document.forms[0].sbrq.value;
 	 	var strYear3 = sksbrq.substr(0,4);		
		var strMonth3 = sksbrq.substr(4,2);
 	 	var strDay3 = sksbrq.substr(6,2); 
 	 	
	  	var objDate1 = new Date(strMonth1+'-'+strDay1+'-'+strYear1);
	  	var objDate2 = new Date(strMonth2+'-'+strDay2+'-'+strYear2);
	  	var objDate3 = new Date(strMonth3+'-'+strDay3+'-'+strYear3);
	  	
	  	if(objDate2>objDate3){
	  		alert("˰�����ʱ�䲻�ܴ����걨����");
	  		window.event.returnValue=false;
				document.forms[0].skssjsrq.focus();
				document.forms[0].skssjsrq.select();
				return false;
	  	}
  	  	
  		if(objDate1>=objDate2){
			if(obj == document.forms[0].skssjsrq){
				alert("˰�����ʱ�䲻��С�ڻ����˰�ʼʱ��");
			}else{
				alert("˰�ʼʱ�䲻�ܴ��ڻ����˰�����ʱ��");	
			}
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;
		}
		
		if(strYear1!=strYear2){
			alert("˰�ʼʱ����˰�����ʱ�����ݲ�ͬ��˰��ܿ��꣡");
			return false;				
		}
		
		return true;
			
	}
	
	function link2Table(tableId){
		
		if(!checkChangeOfSkssrq()){
			document.getElementById("TableID_"+tableId).href="#";
			return false;
		}
		document.getElementById("TableID_"+tableId).href=document.getElementById("TableID_"+tableId).href+"&skksrq="+document.forms[0].skssksrq.value+"&skjsrq="+document.forms[0].skssjsrq.value;
	}
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">
<%@include file="../../include/header.jsp"%>
<html:form action="/webapp/smsb/qysds/2008/qysdsMainAction2008.do" method="POST">
	<html:hidden property="actionType" value="Show" />
	<html:hidden property="nsrmc" />
	<html:hidden property="sknd" />
	<html:hidden property="swjgzzjgdm" />
	<html:hidden property="ssjjlx" />
	<html:hidden property="sshy" />
	<html:hidden property="ckzd" />
	<html:hidden property="gzglxs" />
	<html:hidden property="jmlx" />
	<html:hidden property="zgswjgzzjgmc" />
	<html:hidden property="linkUrlHTML" />
	<html:hidden property="jydz" />
	<html:hidden property="lxdh" />
	<html:hidden property="hid_skssksrq" />
	<html:hidden property="hid_skssjsrq" />


	<div id="smsb_qysdsnb">
	<table align="center" cellspacing="0" class="table-99"
		onkeydown="moveFocus()">
		<tr>
			<td class="1-td1">��ҵ����˰�����˰�걨</td>
		</tr>
		<tr>
			<td valign="top" class="1-td2"><br>
			<table cellspacing="0" class="table-99">
				<tr class="black9">
					<td align="left" nowrap>
					<div align="left">�걨���ڣ� <html:text size="8" maxLength="8"
						property="sbrq" tabIndex="1"
						onchange="getStartEndDate(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,1)" />
					</div>
					</td>
					<td align="center" nowrap>
					<div align="left">˰���������ڣ� <html:text property="skssksrq" size="11"
						maxlength="8" tabIndex="2"
						onchange="compareDate(this)" />��
					<html:text property="skssjsrq" size="11" maxlength="8" tabIndex="3"
						onchange="compareDate(this)" />
					</div>
					</td>
				</tr>
				<tr class="black9">
					<td align="left" nowrap>
					<div align="left">��˰��ʶ��ţ�&nbsp; <bean:write name="qysdsNewForm"
						property="nsrsbh" scope="request" filter="true" /></div>
					</td>

					<td align="right" nowrap>
					<div align="right">��λ��Ԫ�������Ƿ֣�</div>
					</td>
				</tr>
			</table>

			<table class="table-99" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="15%" nowrap class="2-td2-t-left">���������&nbsp;</td>
					<td width="15%" nowrap class="2-td2-t-left" align=left><html:text
						property="jsjdm" size="8" maxlength="8" tabindex="4" onKeyDown="jsjdmQuery()" /></td>
					<td width="15%" nowrap class="2-td2-t-left">����˰�����&nbsp;</td>
					<td width="40%" nowrap class="2-td2-t-center" align=left>&nbsp; <bean:write
						name="qysdsNewForm" property="zgswjgzzjgmc" scope="request"
						filter="true" /></td>
				</tr>
				<TR>
					<td width="15%" nowrap class="2-td2-left">��˰������&nbsp;</td>
					<td width="30%" nowrap class="2-td2-left" align=left>&nbsp; <bean:write
						name="qysdsNewForm" property="nsrmc" scope="request" filter="true" /></td>
					<td width="10%" nowrap class="2-td2-left">��ϵ�绰&nbsp;</td>

					<td width="15%" nowrap class="2-td2-center">&nbsp;<bean:write
						name="qysdsNewForm" property="lxdh" scope="request" filter="true" /></td>
				</tr>
				<tr>
					<td nowrap class="2-td2-left">������Ӫ��ַ&nbsp;</td>
					<td colspan=3 nowrap class="2-td2-align-left" align=left>&nbsp;<bean:write
						name="qysdsNewForm" property="jydz" scope="request" filter="true" />
					</td>
				</tr>
			</table>

			<br>

			<table id="tableQysds">
				<tr>

					<bean:write name="qysdsNewForm" property="linkUrlHTML"
						scope="request" filter="false" />

				</tr>

			</table>
			<br>
			<br>
			<br>
			</td>
		</tr>
		<TR class="black9">
			<TD>
			<div align="center"><input type="image" accesskey="q"
				onClick="doQuery();return false;"
				onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)"
				onMouseOut="MM_swapImgRestore()" value="��ѯ"
				src="<%=static_contextpath%>images/cx-q1.jpg " id="chaxun"
				style="cursor:hand"> &nbsp;&nbsp; <input type="image"
				accesskey="q" tabIndex="-1" onClick="doCheck();return false;"
				onMouseOver="MM_swapImage('qbjy','','../../../images/b-qbjy2.jpg',1)"
				onMouseOut="MM_swapImgRestore()" value="ȫ��У��" alt="����ϵУ��" id="qbjy"
				src="../../../images/b-qbjy1.jpg" style="cursor:hand">&nbsp;&nbsp; <input
				type="image" accesskey="c" tabIndex="-1"
				onClick="tuichu();return false;"
				onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
				onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1"
				src="<%=static_contextpath%>images/tc-c1.jpg" style="cursor:hand"></div>
			</TD>
		</TR>
	</table>
	</div>
	<br>
	<%@ include file="../../include/footernew.jsp"%>
</html:form>
</body>
</html>
