<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm"%>
<jsp:useBean
	type="com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm"
	scope="request" id="qyqssdsBaseForm" />

<html>
<head>
<title>��ҵ����˰�����걨</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="../../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/function.js"></script>
<script language="JavaScript" type="text/javascript" src="../../../js/My97DatePicker/WdatePicker.js"></script>
</head>
<%
com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm form = (com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm) request
				.getAttribute("qyqssdsBaseForm");
	//�������� 0:���ϣ�1������
	String sqlx = form.getSqlx();
	//������˱�ʶ
	String bashbs = form.getBaShztbs();
	//�걨��˱�ʶ
	String sbshbs = form.getSbShztbs();
	%>
<script language="JavaScript">
	
function doShow(){
	 //���水ť
	 var baocun = document.getElementById("baocundiv");
	 //ȫ��У�鰴ť
	 var quanbiaojiaoyan = document.getElementById("quanbiaojiaoyandiv");
	 //�������밴ť
	 var jieshoushenqing = document.getElementById("jieshoushenqingdiv");
	 //�ܾ����밴ť
	 var jujueshenqing = document.getElementById("jujueshenqingdiv");
	 //ɾ����ť
	 var shanchu = document.getElementById("shanchudiv");
	 //������˱�ʶ
	 var shbs="<%=form.getBaShztbs()==null ?"0":form.getBaShztbs()%>";
	 //�걨��˱�ʶ
	 var sbshbs ="<%=form.getSbShztbs()==null?"0":form.getSbShztbs()%>";
	 var sqlx ="<%=form.getSqlx()==null?"2":form.getSqlx()%>";
	 //�걨���״̬��ʶΪ2������£������걨�������ڲ��ɱ༭
	 if(sbshbs=="2"){
		 var qssbjsrqText=document.getElementById("qssbjsrq");
		 qssbjsrqText.readOnly=true;
	 }
	 if(sqlx=="0"){
			 //��������Ĳ���Ҫ�޸�
			
			 if(sbshbs==""){
				 jieshoushenqing.style.display="none";
			 }
			 //���ͨ����ֻ��Ҫ�����Ϻͷ��ذ�ť
			 if(sbshbs=="2" || sbshbs=="3"){
				 baocun.style.display="none";
				 quanbiaojiaoyan.style.display="none";
				 jieshoushenqing.style.display="none";
				 jujueshenqing.style.display="none";
			 }
	 }else if(sqlx=="1"){
		 //�����걨����Ҫ�ܾ����밴ť
		 jujueshenqing.style.display="none";
		 if(sbshbs==""){
			 jieshoushenqing.style.display="none";
		 }
		 //���ͨ����ֻ��Ҫ�����Ϻͷ��صİ�ť
		 if(sbshbs=="2"){
			 //�������밴ť������
			 jieshoushenqing.style.display="none";
			 //��˳ɹ��ı����ɾ����ť������
			 baocun.style.display="none";
			 quanbiaojiaoyan.style.display="none";
			 jujueshenqing.style.display="none";
		 }
	 }
}
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
		doSubmitForm('Query');
	}
	
	//����ȫ��У��
	function doCheck(){
		if (!check_jsjdm()){
			return false;
		}
		
		doSubmitForm('Check');
	}
	
	function checkChangeOfQsrq(){
		//Ч����ʼ��ֹ����
		var qssbksrq = document.forms[0].qssbksrq.value;
		var qssbjsrq = document.forms[0].qssbjsrq.value;
		if(qssbksrq == ""){
			alert("����д���㿪ʼ���ڣ�");
			document.forms[0].qssbksrq.focus();
			return false;
		}
		if(qssbjsrq == ""){
			alert("����д����������ڣ�");
			document.forms[0].qssbjsrq.focus();
			return false;
		}
		//��ʼ���ڲ��ܴ��ڽ�ֹ����
		if(!checkDate(qssbksrq,qssbjsrq)){
			return false;
		}
		return true;
	}
	function link2Table(tableId){
		
		if(!checkChangeOfQsrq()){
			return false;
		}else{
			document.getElementById("TableID_"+tableId).href=document.getElementById("TableID_"+tableId).href+"&qssbksrq="+document.forms[0].qssbksrq.value+"&qssbjsrq="+document.forms[0].qssbjsrq.value
			+"&sbShztbs="+document.forms[0].sbShztbs.value+"&sqlx="+<%=form.getSqlx()==null?"2":form.getSqlx()%>;
			return true;
		}
	}
	//��������
	function doAccept(){
		doSubmitForm("Accept");
	}
	//�ܾ�����
	function doRefuse(){
	var sbshbs ="<%=form.getSbShztbs()==null?"0":form.getSbShztbs()%>";
	if(!("1"==sbshbs)){
		alert("����ҵ����ҵ��������˰�걨�����ύδ���״̬������ִ����˲��أ�");
	}else{
		doSubmitForm("Refuse");
	}		
	}
	//����
	function doSave(){
		doSubmitForm("Save");
	}
	//����
	function doDelete(){
	var sbshbs ="<%=form.getSbShztbs()==null?"0":form.getSbShztbs()%>";
	if(""==sbshbs){
		alert("����ҵ����ҵ��������˰�걨δ�ύ���������ϣ�");
	}else{
		doSubmitForm("Delete");
	}			
	}
	<%/*����*/%>
	function doBack()
	{
		doSubmitForm("Back");
	}
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="doShow();">
	<%@include file="../../include/header.jsp"%>
	<html:form action="/webapp/smsb/qyqssds/2014/qyqssdsMainAction2014.do"
		method="POST">
		<html:hidden property="actionType" value="Show" />
		<html:hidden property="nsrmc" />
		<html:hidden property="swjgzzjgdm" />
		<html:hidden property="ssjjlx" />
		<html:hidden property="sshy" />
		<html:hidden property="zgswjgzzjgmc" />
		<html:hidden property="linkUrlHTML" />
		<html:hidden property="jydz" />
		<html:hidden property="qsllry" />
		<html:hidden property="lxdh" />
		<html:hidden property="hid_qssbksrq" />
		<html:hidden property="hid_qssbjsrq" />
		<html:hidden property="tbrq" />
		<html:hidden property="qssbksrq" />
		<html:hidden property="sbShztbs" />
		<html:hidden property="jsjdm" />
		<div id="smsb_qysdsnb">
			<table align="center" cellspacing="0" class="table-99"
				onkeydown="moveFocus()">
				<tr>
					<td class="1-td1">��ҵ��������˰�걨</td>
				</tr>
				<tr>
					<td valign="top" class="1-td2"><br>
						<table cellspacing="0" class="table-99">
							<tr class="black9">
								<td align="left" nowrap>
									<div align="left">
										����ڣ�
										<bean:write name="qyqssdsBaseForm" property="tbrq"
											scope="request" filter="true" />
									</div></td>
								<td align="center" nowrap>&nbsp;</td>
							</tr>
							<tr class="black9">
								<td align="left" nowrap>
									<div align="left">
										��˰��ʶ��ţ�&nbsp;
										<bean:write name="qyqssdsBaseForm" property="nsrsbh"
											scope="request" filter="true" />
									</div></td>

								<td align="right" nowrap>
									<div align="right">��λ��Ԫ�������Ƿ֣�</div></td>
							</tr>
						</table>

						<table class="table-99" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="15%" nowrap class="2-td2-t-left">���������&nbsp;</td>
								<td width="15%" nowrap class="2-td2-t-left" align=left>							  
								 <!--<html:text								
										property="jsjdm" size="20" maxlength="8" tabindex="4"
										onKeyDown="jsjdmQuery()"  readonly="true"/>-->
									<bean:write name="qyqssdsBaseForm" property="jsjdm"	scope="request" filter="true" />									
								</td>
								<td width="15%" nowrap class="2-td2-t-left">����˰�����&nbsp;</td>
								<td width="40%" nowrap class="2-td2-t-center" align=left>&nbsp;
									<bean:write name="qyqssdsBaseForm" property="zgswjgzzjgmc"
										scope="request" filter="true" />
								</td>
							</tr>
							<TR>
								<td width="15%" nowrap class="2-td2-left">��˰������&nbsp;</td>
								<td width="30%" nowrap class="2-td2-left" align=left>&nbsp;
									<bean:write name="qyqssdsBaseForm" property="nsrmc"
										scope="request" filter="true" />
								</td>
								<td width="10%" nowrap class="2-td2-left">������Ӫ��ַ&nbsp;</td>

								<td width="15%" nowrap class="2-td2-center">&nbsp;<bean:write
										name="qyqssdsBaseForm" property="jydz" scope="request"
										filter="true" />
								</td>
							</tr>
							<tr>
								<td nowrap class="2-td2-left">�����˻�������������Ա&nbsp;</td>
								<td  nowrap class="2-td2-left" align=left>
								&nbsp;<bean:write name="qyqssdsBaseForm" property="qsllry" scope="request"
										filter="true" />
								</td>
								<td nowrap class="2-td2-left">��ϵ�绰&nbsp;</td>
								<td  nowrap class="2-td2-center" align=left>
								&nbsp;<bean:write name="qyqssdsBaseForm" property="lxdh" scope="request"
										filter="true" />
								</td>
							</tr>
							<tr>
								<td class="2-td2-left" nowrap>������ʼ����&nbsp;</td>
								<td  class="2-td2-left" nowrap>
									<bean:write name="qyqssdsBaseForm" property="qssbksrq"/>
								</td>
								<td class="2-td2-left" nowrap>�����������&nbsp;</td>
								<td  class="2-td2-center" nowrap>
									<html:text property="qssbjsrq" onClick="WdatePicker()" ></html:text> 
								</td>
							</tr>
							<tr>
								<td  class="2-td2-left" nowrap="nowrap">���״̬��ʾ&nbsp;</td>	
									<td colspan="3" class="2-td2-center" nowrap="nowrap">
										<div align="left" style="color:red;">
											&nbsp;<bean:write name="qyqssdsBaseForm" property="sbShztMessage" scope="request" />
										</div>
								</td>	
							</tr>
						</table> <br>

						<table id="tableQysds">
							<tr>

								<bean:write name="qyqssdsBaseForm" property="linkUrlHTML"
									scope="request" filter="false" />

							</tr>

						</table> <br> <br> <br></td>
				</tr>
				<TR class="black9">
					<TD>
						<div align="center">
							
								<span id="baocundiv" >
										<img onclick="doSave();return false;"
										onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" value="����" id="baocun"
										src="<%=static_contextpath%>images/bc-s1.jpg" width="79"
										height="22" style="cursor:hand" />&nbsp;&nbsp;&nbsp;&nbsp; 
										</span> 
										<span id="quanbiaojiaoyandiv">
								<input type="image"
								accesskey="q" tabIndex="-1" onClick="doCheck();return false;"
								onMouseOver="MM_swapImage('qbjy','','../../../images/b-qbjy2.jpg',1)"
								onMouseOut="MM_swapImgRestore()" value="ȫ��У��" alt="����ϵУ��"
								id="qbjy" src="../../../images/b-qbjy1.jpg" style="cursor:hand">

							&nbsp;&nbsp;&nbsp;&nbsp; </span>
							<span id="jieshoushenqingdiv" >
										<a style="cursor:hand" onClick="doAccept();return false;" onMouseOut="MM_swapImgRestore()"
				                        onMouseOver="MM_swapImage('jishoushenqing','','../../../images/b_shtg2.jpg',1)">
				                        <img src="../../../images/b_shtg1.jpg" name="jishoushenqing" width="79" height="22" border="0" id="jishoushenqing">
				                        </a>
				                        &nbsp;&nbsp;&nbsp;&nbsp;
				                        </span>
				                        <span id="jujueshenqingdiv" >
				                        <a style="cursor:hand" onClick="doRefuse();return false;" onMouseOut="MM_swapImgRestore()"
				                        onMouseOver="MM_swapImage('jujueshenqing','','../../../images/b_shbh2.jpg',1)">
				                        <img src="../../../images/b_shbh1.jpg" name="jujueshenqing" width="79" height="22" border="0" id="jujueshenqing">
				                        </a>
				                        &nbsp;&nbsp;&nbsp;&nbsp;
										</span>
								<!-- ɾ����ť�����ϰ�ťʹ�� -->
										<span id="shanchudiv" >
										<a style="cursor:hand" onClick="doDelete()" onMouseOut="MM_swapImgRestore()"
				                        onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/b-zf2.jpg',1)">
				                        <img src="<%=static_contextpath%>images/b-zf1.jpg" name="shanchu" width="79" height="22" border="0" id="shanchu">
				                        </a>
							 &nbsp;&nbsp;&nbsp;&nbsp;
								 <span >
										<img onclick="doBack()"
										onMouseOver="MM_swapImage('back','','<%=static_contextpath%>images/fanhui2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" value="����" id="back"
										src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
										height="22" style="cursor:hand" /></span>
						</div></TD>
				</TR>
			</table>
		</div>
		<br>
		<%@ include file="../../include/footernew.jsp"%>
	</html:form>
</body>
</html>
