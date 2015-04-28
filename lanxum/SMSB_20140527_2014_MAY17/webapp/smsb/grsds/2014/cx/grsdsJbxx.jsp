<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html>
<head>
<title>¼���������˰������Ϣ����Ϣ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/swapImages.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/smsb_common.js"></script>
<script language=JavaScript>
var endSave = false;
function setLoadInf()
{
	//��ѡ�� 
	var grxx_nsrlx = document.getElementById("grxx_nsrlx");				//��˰������
	var grxx_nsrlx_cb = document.getElementsByName("grxx_nsrlx_cb");
	splitCheckBox(grxx_nsrlx,grxx_nsrlx_cb);
	
	var grxx_sfyjjnqk = document.getElementById("grxx_sfyjjnqk");				//����һ��������
	var grxx_sfyjjnqk_cb = document.getElementsByName("grxx_sfyjjnqk_cb");
	splitCheckBox(grxx_sfyjjnqk,grxx_sfyjjnqk_cb);
	
	var grxx_sfcjrlsgl = document.getElementById("grxx_sfcjrlsgl");				//�Ƿ�м�����������
	var grxx_sfcjrlsgl_cb = document.getElementsByName("grxx_sfcjrlsgl_cb");
	splitCheckBox(grxx_sfcjrlsgl,grxx_sfcjrlsgl_cb);
	
	var grxx_tzzlx = document.getElementById("grxx_tzzlx");					//Ͷ��������
	var grxx_tzzlx_cb = document.getElementsByName("grxx_tzzlx_cb");
	splitCheckBox(grxx_tzzlx,grxx_tzzlx_cb);
	
	var msg="<bean:write name='grsdsjbxxbForm2014cx' property='msg'/>";
	if(msg){
		alert(msg);
		if(msg=="����ɹ�"){
			endSave=true;
		}
	}
}
function doReturn()
{
	document.forms[0].action = "quit.do";
	document.forms[0].submit();
}

function joinCheckBox(obj1,obj2){
	var length = obj2.length;
	var result="";
	for(var i=0;i<length;i++){
		if(obj2[i].checked){
			result+=obj2[i].value;
			result+="|";
		}
	}
	obj1.value=result;
}

function splitCheckBox(obj1,obj2){
	var values = obj1.value.split("|");
	for(var i=0;i<values.length;i++){
		for(var j=0;j<obj2.length;j++){
			if(values[i]==obj2[j].value){
				obj2[j].checked=true;
			}
		}
	}
}

//���滹���ش�ҳ��
function doSave()
{
	if(!check()) {
		return false;
	}
	beforeSave();
	var actionType = document.getElementById("actionType");
	actionType.value="Save";
	document.forms[0].submit();
}
function doNext(){
	
	//���
	var nd = document.all.nd.value;
	if(!checkNd(nd)){
		alert("��ȸ�ʽ����ȷ")
		return false;
	}
	
		var actionType = document.getElementById("actionType");
		actionType.value="Next";
		document.forms[0].submit();
}

function beforeSave(){
	var grxx_nsrlx = document.getElementById("grxx_nsrlx");				//��˰������
	var grxx_nsrlx_cb = document.getElementsByName("grxx_nsrlx_cb");
	joinCheckBox(grxx_nsrlx,grxx_nsrlx_cb);
	
	var grxx_sfyjjnqk = document.getElementById("grxx_sfyjjnqk");				//����һ��������
	var grxx_sfyjjnqk_cb = document.getElementsByName("grxx_sfyjjnqk_cb");
	joinCheckBox(grxx_sfyjjnqk,grxx_sfyjjnqk_cb);
	
	var grxx_sfcjrlsgl = document.getElementById("grxx_sfcjrlsgl");				//�Ƿ�м�����������
	var grxx_sfcjrlsgl_cb = document.getElementsByName("grxx_sfcjrlsgl_cb");
	joinCheckBox(grxx_sfcjrlsgl,grxx_sfcjrlsgl_cb);
	
	var grxx_tzzlx = document.getElementById("grxx_tzzlx");					//Ͷ��������
	var grxx_tzzlx_cb = document.getElementsByName("grxx_tzzlx_cb");
	joinCheckBox(grxx_tzzlx,grxx_tzzlx_cb);
}

function doBack(){
	var actionType = document.getElementById("actionType");
	actionType.value="Back";
	document.forms[0].submit();
}

function doChangeNd(){
	
	//���
	var nd = document.all.nd.value;
	if(!checkNd(nd)){
		alert("��ȸ�ʽ����ȷ")
		return false;
	}
	
	var actionType = document.getElementById("actionType");
	actionType.value="ChangeNd";
	document.forms[0].submit();
}

/**
 * ����У��
 */
function check() {
	// ����
	var grxx_xm = document.all.grxx_xm.value;
	if(!grxx_xm) {
		alert("��������Ϊ�գ�")
		return false;
	}
	
	// ����ʱ��
	var grxx_wzsnsr_lhsj = document.all.grxx_wzsnsr_lhsj.value;
	if(grxx_wzsnsr_lhsj &&!checkDate(grxx_wzsnsr_lhsj)) {
		alert("����ʱ���ʽ����ȷ��");
		return false;
	}
	
	// Ԥ���뾳ʱ��
	var grxx_wzsnsr_yjljsj = document.all.grxx_wzsnsr_yjljsj.value;
	if(grxx_wzsnsr_yjljsj && !checkDate(grxx_wzsnsr_yjljsj)) {
		alert("Ԥ���뾳ʱ���ʽ����ȷ��");
		return false;
	}
	
	//��������
	var grxx_email = document.all.grxx_email.value;
	if(grxx_email && !checkEmail(grxx_email)) {
		alert("���������ʽ����ȷ��");
		return false;
	}
	
	//��������
	var grxx_yzbm = document.all.grxx_yzbm.value;
	var grxx_yjwsd_yzbm = document.all.grxx_yjwsd_yzbm.value;
	var grxx_yjwsd_yzbm = document.all.grxx_yjwsd_yzbm.value;
	var grxx_wzsnsr_jnrzsgdw_yzbm = document.all.grxx_wzsnsr_jnrzsgdw_yzbm.value;
	var grxx_wzsnsr_jnspqydw_yzbm = document.all.grxx_wzsnsr_jnspqydw_yzbm.value;
	if((grxx_yzbm && !checkZip(grxx_yzbm))||(grxx_yjwsd_yzbm && !checkZip(grxx_yjwsd_yzbm))||(grxx_wzsnsr_jnrzsgdw_yzbm && !checkZip(grxx_wzsnsr_jnrzsgdw_yzbm))||(grxx_wzsnsr_jnspqydw_yzbm && !checkZip(grxx_wzsnsr_jnspqydw_yzbm))) 
	{
		alert("���������ʽ����ȷ��");
		return false;
	}
	return true;
}

function checkNd(nd) {
	var reg = /^(1|2)\d{3}$/;
	if(reg.test(nd) == false) {
		return false;
	}
	return true;
}

/**
 * ��֤Email
 */
function checkEmail(strEmail) {
	if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1) {
		return true;
	} else {
		return false;
	}
}

/**
 * ��֤��������
 */
function checkZip(zip) {
	var reg = /^[1-9]\d{5}(?!\d)$/;
	if(reg.test(zip) == false) {
		return false;
	}
	return true;
}


/**
 * ���ʱ���ʽ
 */
function checkDate(dateVal) {
	if(!dateVal) {
		return false;
	}
	if (/^\d{4}1(0|1|2)(0|1|2)\d{1}$/.test(dateVal) 
			|| /^\d{4}0(1|2|3|4|5|6|7|8|9)(0|1|2)\d{1}$/.test(dateVal)
			|| /^\d{4}1(0|1|2)3(0|1)$/.test(dateVal)
			|| /^\d{4}0(1|2|3|4|5|6|7|8|9)3(0|1)$/.test(dateVal)) {
		return true;
	}
	return false;
}

function clickTbsm(){
	var txsm = document.getElementById("tbsm");
	var display = txsm.style.display;
	if(display=="none"){
		txsm.style.display="";
	}else{
		txsm.style.display="none";
	}
}


</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="setLoadInf()">
		<jsp:include page="../../../include/header.jsp" flush="true" >
    		<jsp:param name="name" value="��������Ӫ����˰��˰�����б�" />
			<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>
<html:form action="/webapp/smsb/grsds/grsdsJbxxAction2014cx.do">
<table width="80%" height="50%" border="0" align="center" cellpadding="0" cellspacing="0" >
	
	<bean:define id="sfzjlxListID" name="grsdsjbxxbForm2014cx" property="sfzjlxList"/>		<!-- ���֤�����ʹ���� -->
	<bean:define id="gjListID" name="grsdsjbxxbForm2014cx" property="gjList"/>				<!-- ���� -->
	<bean:define id="swjgzzjgListID" name="grsdsjbxxbForm2014cx" property="swjgzzjgList"/>	<!-- ˰�������֯���� -->
	<bean:define id="hyListID" name="grsdsjbxxbForm2014cx" property="hyList"/>				<!-- ��ҵ -->
	<bean:define id="djzclxListID" name="grsdsjbxxbForm2014cx" property="djzclxList"/>				<!-- ��ҵ -->
	<input type="hidden" name="actionType" id="actionType" value="Show" />
	
			<!--¼����Ϣ-->
			<tr>
				<td class="1-td1" >¼���������˰������Ϣ����Ϣ</td>
			</tr>

			<tr>
				<td class="1-td2" >
					<br/>
					<div align="left">
						<FONT color="#000000" size="1">
						<STRONG>&nbsp;&nbsp;��ȣ�</STRONG>
						&nbsp;&nbsp;<html:text styleId="nd" maxlength="4" name="grsdsjbxxbForm2014cx" onchange="doChangeNd();" property="nd"/>
						</FONT>
					</div>
				
					<table  cellSpacing="0" cellPadding="0" width="97%" border="0" align = "center">
						<tr>
							<td class="2-td2-t-left" width="10%" ><!--����  �����޸�-->
								����
							</td>
							<td class="2-td2-t-left" width="10%">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_xm"/>&nbsp;
							</td>
							<td class="2-td2-t-left" width="10%"><!--���֤������ �����޸�-->
								���֤������
							</td>
							<td class="2-td2-t-left" width="10%">
								<html:hidden name="grsdsjbxxbForm2014cx" property="grxx_sfzjlx"></html:hidden>
							    <html:select disabled="true" name="grsdsjbxxbForm2014cx" property="grxx_sfzjlx" >
                          				<html:options collection="sfzjlxListID" property="value" labelProperty="text" />
                          		</html:select>
							</td>
							<td class="2-td2-t-left"  width="10%"><!--���֤������ �����޸�-->
								���֤������
							</td>
							<td class="2-td2-t-center"  width="50%" colspan="5">
								<html:hidden name="grsdsjbxxbForm2014cx" property="grxx_sfzjhm"></html:hidden>
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_sfzjhm"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left"  width="10%"><!--��˰������-->
								��˰������
							</td>
							<td class="2-td2-center"  width="90%" colspan="9">
								<html:hidden property="grxx_nsrlx" name="grsdsjbxxbForm2014cx" />
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx0" value="0"  disabled="disabled">����ְ�ܹ͵�λ</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx1" value="1"  disabled="disabled">Ͷ����</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx2" value="2"  disabled="disabled">����ְ�ܹ͵�λ�������ɶ�Ͷ���ߣ�</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='grxx_nsrlx_cb' id="grxx_nsrlx3" value="3"  disabled="disabled">��ס������</input>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left"  width="10%"><!--��ְ�ܹ͵�λ����-->
								��ְ�ܹ͵�λ����
							</td>
							<td class="2-td2-left"  width="60%" colspan="5">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_rzsgdwmc"/>&nbsp;
							</td>
							<td class="2-td2-left"  width="10%"><!--��ְ�ܹ͵�λ��˰��ʶ���-->
								��ְ�ܹ͵�λ��˰��ʶ���
							</td>
							<td class="2-td2-center"  width="20%" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_rzsgdwnsrsbh"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--����һ��������-->
								������һ�𡱽������
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<html:hidden property="grxx_sfyjjnqk" name="grsdsjbxxbForm2014cx" />
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_0" value="0" disabled="disabled">�������ϱ��շ�</input>
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_1" value="1" disabled="disabled">����ҽ�Ʊ��շ�</input>
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_2" value="2" disabled="disabled">ʧҵ���շ�</input>
								<input type='checkbox' name='grxx_sfyjjnqk_cb' id="grxx_sfyjjnqk_3" value="3" disabled="disabled">ס��������</input>
							</td>
							<td class="2-td2-left"  width="10%"><!--��������-->
								��������
							</td>
							<td class="2-td2-center"  width="20%" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_email"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--������ϵ��ַ-->
								������ϵ��ַ
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_jnlxdz"/>&nbsp;
							</td>
							<td class="2-td2-left"  width="10%"><!--��������-->
								��������
							</td>
							<td class="2-td2-center"  width="20%" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_yzbm"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--��ϵ�绰-->
								��ϵ�绰
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_lxdh"/>&nbsp;
							</td>
							<td class="2-td2-left"  width="10%"><!--��������-->
								ְҵ
							</td>
							<td class="2-td2-center"  width="20%" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_zy"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--��ϵ�绰-->
								ְ��
							</td>
							<td class="2-td2-left" width="60%" colspan="5">
								<html:select  name="grsdsjbxxbForm2014cx" property="grxx_zw" styleId="grxx_zw" disabled="true">
								    <html:option value=""></html:option>
									<html:option value="1">�߲�</html:option>
									<html:option value="2">�в�</html:option>
									<html:option value="3">��ͨ</html:option>
								</html:select>
							</td>
							<td class="2-td2-left"  width="10%"><!--��������-->
								ѧ��
							</td>
							<td class="2-td2-center"  width="20%" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_xl"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left"><!--�Ƿ�м���/����/����-->
								�Ƿ�м���/����/����
							</td>
							<td class="2-td2-left" colspan="5">
								<html:hidden property="grxx_sfcjrlsgl" name="grsdsjbxxbForm2014cx" />
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_0" value="0" disabled="disabled">�м�</input>
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_1" value="1" disabled="disabled">����</input>
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_2" value="2" disabled="disabled">����</input>
								<input type='checkbox' name='grxx_sfcjrlsgl_cb' id="grxx_sfcjrlsg_3" value="3" disabled="disabled">��</input>
							</td>
							<td class="2-td2-left"><!--�м��ȼ����-->
								�м��ȼ����
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_cjdjqk"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left"><!-- ���������о���������˰����д -->
								���������о���������˰����д
							</td>
							<td class="2-td2-left" colspan="2">
								<html:select name="grsdsjbxxbForm2014cx" property="grxx_yjwsd_dzlx" styleId="grxx_yjwsd_dzlx" disabled="true">
									<html:option value=""></html:option>
									<html:option value="0">�������ڵ�</html:option>
									<html:option value="1">������ס��</html:option>
								</html:select>
							</td>
							<td class="2-td2-left">
								��������
							</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_yjwsd_yzbm"/>&nbsp;
							</td>
							<td class="2-td2-left">
								��ַ
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_yjwsd_dz"/>&nbsp;
							</td>
						</tr>
						
						<tr>
							<td class="2-td2-left" rowspan="5"><!--��������Ͷ������˰����д-->
								��������Ͷ������˰����д
							</td>
							<td class="2-td2-left" colspan="2"><!--��������Ͷ������˰����д-->
								Ͷ��������
							</td>
							<td class="2-td2-center" colspan="7">
								<html:hidden property="grxx_tzzlx" name="grsdsjbxxbForm2014cx" />
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_0" value="0" disabled="disabled">���幤�̻�</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_1" value="1" disabled="disabled">���˶�����ҵͶ����</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_2" value="2" disabled="disabled">�ϻ���ҵ�ϻ���</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_3" value="3" disabled="disabled">�а������⾭Ӫ��</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_4" value="4" disabled="disabled">�ɶ�</input>
								<input type='checkbox' name='grxx_tzzlx_cb' id="grxx_tzzlx_5" value="5" disabled="disabled">����Ͷ����</input>
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" rowspan="4"><!--��Ͷ�ʵ�λ��Ϣ-->
								��Ͷ�ʵ�λ��Ϣ
							</td>
							<td class="2-td2-left" ><!--����-->
								����
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="qyxx_btzzxm"/>&nbsp;
								<html:hidden property="qyxx_jsjdm" name="grsdsjbxxbForm2014cx" />
							</td>
							<td class="2-td2-left" ><!--�۽������˱���-->
								�۽������˱���
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="qyxx_kjywrbh"/>&nbsp;
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" ><!--����-->
								��ַ
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="qyxx_dz"/>&nbsp;
							</td>
							<td class="2-td2-left" ><!--�۽������˱���-->
								��������
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="qyxx_yzbm"/>&nbsp;
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" ><!--����-->
								�Ǽ�ע������
							</td>
							<td class="2-td2-left" colspan="3">
								<html:select styleId="qyxx_djzclx" name="grsdsjbxxbForm2014cx" property="qyxx_djzclx" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="djzclxListID" property="value" labelProperty="text" />
								</html:select>
							</td>
							<td class="2-td2-left" ><!--�۽������˱���-->
								��ҵ
							</td>
							<td class="2-td2-center" colspan="3">
								
								<html:select styleId="qyxx_hy" name="grsdsjbxxbForm2014cx" property="qyxx_hy" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="hyListID" property="value" labelProperty="text" />
								</html:select>
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" ><!--����-->
								����˰���շ�ʽ
							</td>
							<td class="2-td2-left" colspan="3">
								<html:select styleId="qyxx_sdszsfs" name="grsdsjbxxbForm2014cx" property="qyxx_sdszsfs" disabled="true">
									<html:option value=""></html:option>
									<html:option value="0">��������</html:option>
									<html:option value="1">�˶�����</html:option>
								</html:select>
							</td>
							<td class="2-td2-left" ><!--�۽������˱���-->
								����˰�����
							</td>
							<td class="2-td2-center" colspan="3">
								
								<html:select styleId="qyxx_zgswjg" name="grsdsjbxxbForm2014cx" property="qyxx_zgswjg" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="swjgzzjgListID" property="value" labelProperty="text" />
								</html:select>
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" rowspan="13"><!--����-->
								����������ס����˰����д
							</td>
							<td class="2-td2-left" colspan="2"><!---->
								��˰��ʶ���
							</td>
							<td class="2-td2-center" colspan="7">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_nsrsbh"/>&nbsp;
							</td>
						</tr>

						<tr>
							<td class="2-td2-left" colspan="2" ><!--����-->
								����
							</td>
							<td class="2-td2-left" colspan="3">
								
								<html:select styleId="grxx_wzsnsr_gj" name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_gj" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="gjListID" property="value" labelProperty="text" />
								</html:select>
							</td>
							<td class="2-td2-left"><!--������-->
								������
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_csd"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--�Ա�-->
								�Ա�
							</td>
							<td class="2-td2-left" colspan="3">
								<html:select styleId="grxx_wzsnsr_xb" name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_xb" disabled="true">
									<html:option value=""></html:option>
									<html:option value="0">��</html:option>
									<html:option value="1">Ů</html:option>
								</html:select>
							</td>
							<td class="2-td2-left"><!--��������-->
								��������
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_csrq"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--�Ͷ���ҵ֤����-->
								�Ͷ���ҵ֤����
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_ldjyzhm"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--�Ƿ�˰��Э����Լ���Է�����-->
								�Ƿ�˰��Э����Լ���Է�����
							</td>
							<td class="2-td2-center" colspan="3">
						
								<html:select styleId="grxx_wzsnsr_sfssxddygdfjm" name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_sfssxddygdfjm" disabled="true">
									<html:option value=""></html:option>
									<html:option value="0">��</html:option>
									<html:option value="1">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--����ְ��-->
								����ְ��
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnzw"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--����ְ��-->
								����ְ��
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jwzw"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--����ʱ��-->
								����ʱ��
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_lhsj"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--��ְ����-->
								��ְ����
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_rzqx"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--Ԥ���뾳ʱ��-->
								Ԥ���뾳ʱ��
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_yjljsj"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--Ԥ���뾳�ص�-->
								Ԥ���뾳�ص�
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_yjljdd"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" rowspan="2" ><!--������ְ�ܹ͵�λ-->
								������ְ�ܹ͵�λ
							</td>
							<td class="2-td2-left"><!--����-->
								����
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnrzsgdw_mc"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--�۽������˱���-->
								�۽������˱���
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnrzsgdw_kjywrbm"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--��ַ-->
								��ַ
							</td>
							<td class="2-td2-left" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnrzsgdw_dz"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--��������-->
								��������
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnrzsgdw_yzbm"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" rowspan="2" ><!--������ƸǩԼ��λ-->
								������ƸǩԼ��λ
							</td>
							<td class="2-td2-left" ><!--����-->
								����
							</td>
							<td class="2-td2-left"  colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnspqydw_mc"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--�۽������˱���-->
								�۽������˱���
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnspqydw_kjywrbm"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--��ַ-->
								��ַ
							</td>
							<td class="2-td2-left"  colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnspqydw_dz"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--��������-->
								��������
							</td>
							<td class="2-td2-center" colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jnspqydw_yzbm"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" ><!--������ǲ��λ-->
								������ǲ��λ
							</td>
							<td class="2-td2-left" ><!--����-->
								����
							</td>
							<td class="2-td2-left"  colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jwpqdw_mc"/>&nbsp;
							</td>
							<td class="2-td2-left"><!--��ַ-->
								��ַ
							</td>
							<td class="2-td2-center"  colspan="3">
								<bean:write name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jwpqdw_dz"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2"><!--֧����-->
								֧����
							</td>
							<td class="2-td2-left" colspan="3">
								<html:select styleId="grxx_wzsnsr_zfd" name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_zfd" disabled="true">
									<html:option value=""></html:option>
									<html:option value="0">����֧��</html:option>
									<html:option value="1">����֧��</html:option>
									<html:option value="2">���ڡ���ͬʱ֧��</html:option>
								</html:select>
							</td>
							<td class="2-td2-left"><!--����֧��������-->
								����֧��������
							</td>
							<td class="2-td2-center" colspan="3">
								<html:select styleId="grxx_wzsnsr_jwzfggb" name="grsdsjbxxbForm2014cx" property="grxx_wzsnsr_jwzfggb" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="gjListID" property="value" labelProperty="text" />
								</html:select>
							</td>
						</tr>
						

					</table>
					<br/>

				</td>
			</tr>
		
		
</table>

<div  align="center">
	<span>
		<!-- <a style="cursor:hand"  tabIndex="-1" onClick="doSave();return false;"><img name="baocun" value="����" alt="����" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>/images/baocun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/baocun1.jpg" width="79" height="22" id="baocun"></a>&nbsp;&nbsp;-->
		<a style="cursor:hand"  tabIndex="-1" onClick="doNext();return false;"><img name="xiayiye" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>/images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/xaiyiye1.jpg" width="79" height="22" id="xiayiye"></a>&nbsp;&nbsp;
		<a style="cursor:hand"  tabIndex="-1" onClick="doBack();return false;"><img name="fanhui" value="����" alt="����" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/fanhui1.jpg" width="79" height="22" id="fanhui"></a>&nbsp;&nbsp;
		<a style="cursor:hand"  tabIndex="-1" onClick="tuichu();return false;"><img  name="return" value="�˳�" alt="�˳�" onMouseOver="MM_swapImage('return','','<%=static_contextpath%>/images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/tuichu1.jpg"  width="79" height="22" id="return"></a> 
	</span>			
</div>
<br/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
				 <tr>
				    <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				    <td width="20%" align="center" class="black9">	<a style="cursor:hand;text-decoration:underline;"  tabIndex="-1" onClick="javascript:clickTbsm();"><strong><font color="#0000FF">�����鿴���˵��</font></strong></a></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47" id="tbsm" style="display:none;">
						һ��	���÷�Χ<br/>
&nbsp;&nbsp;������������Ȼ����˰�˻�����Ϣ�����<br/>
&nbsp;&nbsp;����˰����ؿɸ��ݱ���ʵ�ʣ�����Ȼ����˰�˳�����˰����ذ��������˰����ʱ����������걨���Ժ��������Ϣ�����仯ʱ���<br/>
����	���������д���£�<br/>
&nbsp;(һ)	��ͷ��<br/>
&nbsp;&nbsp;1.	��������д��˰���������й�������ס�����ˣ�������Ӧ���ֱ����С�������������д��<br/>
&nbsp;&nbsp;2.	���֤�����ͣ���д��˰����Ч���֤�����գ����ơ��й�������д���֤������֤��ʿ��֤��֤�����ƣ��й�������ס�����ˣ���д���ա��۰ľ��������ڵ�ͨ��֤��̨�����������½ͨ��֤��֤�����ơ�<br/>
&nbsp;&nbsp;3.	���֤�����룺��д���֤���ϵĺ��롣<br/>
&nbsp;&nbsp;4.	��˰�����ͣ���˰�˸�����������ڶ�Ӧ���ڴ򡰡̡����ɶ�ѡ��<br/>
&nbsp;&nbsp;&nbsp;��1��	����ְ�ܹ͵�λ����ָ��˰���й̶���ְ�ܹ͵�λ��<br/>
&nbsp;&nbsp;&nbsp;��2��	����ְ�ܹ͵�λ�������ɶ�Ͷ���ߣ�����ָ��˰��Ϊ����ְҵ�ߣ�û�����κε�λǩ����ְ�ܹͺ�ͬ��������ҵ�ɶ������幤�̻������˶�����ҵͶ���ߡ��ϻ���ҵ�ϻ��ˡ��а����⾭Ӫ�ߡ�<br/>
&nbsp;&nbsp;&nbsp;��3��	Ͷ���ߣ���ָ�ж���Ͷ�ʵ���˰�ˡ�<br/>
&nbsp;&nbsp;&nbsp;��4��	��ס�����ˣ���ָ���й�������ס������˰�ˡ�����ס�����������ס�����ԣ����й�������ס���ĸ��ˣ���ָ�򻧼�����ͥ�����������ϵ�����й�����ϰ���Ծ�ס�ĸ��ˡ�<br/>
&nbsp;&nbsp;5.	��ְ�ܹ͵�λ���Ƽ���˰��ʶ��ţ���д��˰��ǩ����ְ�ܹͺ�ͬ�ĵ�λ����ȫ�Ƽ�����˰����ذ���Ǽ�ʱ����˰��ʶ��š�ǰ�������ƣ���������˰��ʶ��š�<br/>
&nbsp;&nbsp;���ҵ�λǩ����ͬ�ģ��������ʾ��û�����<br/>
&nbsp;&nbsp;6.	������һ�𡱽����������˰�˸����Լ�������ᱣ�շ�����ڡ��������ϱ��շѡ���������ҽ�Ʊ��շѡ�����ʧҵ���շѡ�����ס�������𡱶�Ӧ���ڴ򡰡̡��������û�н��ɵģ��ڡ��ޡ����򡰡̡���<br/>
&nbsp;&nbsp;7.	�������䣺��д˰�����������˰����ϵ�ĵ��������ַ��<br/>
&nbsp;&nbsp;8.	������ϵ��ַ���������룺��д˰�����������˰����ϵ����Ч�й�������ϵ��ַ���������롣<br/>
&nbsp;&nbsp;9.	��ϵ�绰����д˰�����������˰����ϵ�ĵ绰��<br/>
&nbsp;&nbsp;10.	ְҵ����д��˰�������µ�ְҵ��ְҵ���ఴ�Ͷ�����ᱣ�ϲ��ŵĹ�����д��<br/>
&nbsp;&nbsp;11.	ְ����д��˰������ְ�ܹ͵�λ�����ε�ְ���ڡ��߲㡱�����в㡱������ͨ������ǰѡ����һ�򡰡̡���<br/>
&nbsp;&nbsp;12.	ѧ������д��˰��ȡ�õ�����ѧ����<br/>
&nbsp;&nbsp;13.	�Ƿ�м���/����/���ϣ����ϱ�������ģ��ڶ�Ӧ��ǰ�򡰡̡��������ڡ������򡰡̡���<br/>
&nbsp;(��)	�о������õ���˰����д������˰�˴��й�����ȡ�����õ���д������û�����<br/>
&nbsp;��˰����ѡ�����ʱ��Ӧ���ݡ�����˰���ֹܾ���ӡ������������˰������˰�걨�취�����У�����֪ͨ����ʮһ���ڶ�����й�����ȡ�����õģ����й����ڻ������ڵ�����˰������걨�����й������л��������������ڵ����й����ھ�����ס�ز�һ�µģ�ѡ�񲢹̶�������һ������˰������걨�����й�����û�л����ģ����й����ھ�����ס������˰������걨���Ĺ涨ѡ����д��<br/>
&nbsp;ѡ�����˰���ڡ��������ڵء��򡰾�����ס�ء���Ӧ���ڴ򡰡̡�����д�����ַ��<br/>
&nbsp;(��)	Ͷ������˰����д��������Ȼ�˹ɶ���Ͷ������д�����û�ж���Ͷ�ʵģ����<br/>
&nbsp;&nbsp;1.	Ͷ�������ͣ���˰�˸�����������ڶ�Ӧ���ڴ򡰡̡����ɶ�ѡ��<br/>
&nbsp;&nbsp;2.	��Ͷ�ʵ�λ��Ϣ����д��˰�˶���Ͷ�ʵ�λ���й���Ϣ��<br/>
&nbsp;&nbsp;&nbsp;��1��	��˰�����ƣ���д˰����غ˷��ı�Ͷ�ʵ�λ˰��Ǽ�֤��������˰������ȫ�ơ�Ͷ�ʶ�ҵ�λ�ģ���ֱ���ʾ��<br/>
&nbsp;&nbsp;&nbsp;��2��	�۽������˱��룺��д˰����غ˷���˰��Ǽ�֤���롣<br/>
&nbsp;&nbsp;&nbsp;��3��	��ַ���������룺��дͶ����Ͷ�ʵ�λ�ĵ�ַ���������롣<br/>
&nbsp;&nbsp;&nbsp;��4��	�Ǽ�ע�����ͣ���д��Ͷ�ʵ�λ�ڹ�������������صǼ�ע������͡�<br/>
&nbsp;&nbsp;&nbsp;��������ҵ��������ҵ��������ҵ���ɷݺ�����ҵ����Ӫ��ҵ���������ι�˾���ɷ����޹�˾��˽Ӫ��ҵ��������ҵ�����۰�̨��Ͷ����ҵ������Ͷ����ҵ�����ࡣ[ע��������ҵ�����������ڵ���ҵ���͡�]<br/>
&nbsp;&nbsp;&nbsp;��5��	��ҵ�����չ��񾭼���ҵ������ұ�׼��д�����ࡣ<br/>
&nbsp;&nbsp;&nbsp;��6��	����˰�������ͣ���д��Ͷ�ʵ�λ����˰�����շ�ʽ��<br/>
&nbsp;&nbsp;&nbsp;��7��	����˰����أ���д��Ͷ�ʵ�λ������˰��������ơ�<br/>
&nbsp;&nbsp;&nbsp;��8��	�ɶ�������Ͷ������д��������Ȼ�˹ɶ�������Ͷ������д�����幤�̻��������˶�����ҵͶ���ߡ��ϻ���ҵ�ϻ��ˡ��а����⾭Ӫ�߲���д������<br/>
&nbsp;&nbsp;&nbsp;&nbsp;�ٹ�˾�ɱ���Ͷ�ʣ��ܶ��д��Ͷ�ʵ�λ�Ĺ�˾�ɱ���Ͷ�ʣ��ܶ<br/>
&nbsp;&nbsp&nbsp;&nbsp;;�ڸ��˹ɱ���Ͷ�ʣ��ܶ��д��Ȼ�˹ɶ���Ͷ�����ڱ�Ͷ�ʵ�λ����Ͷ�ʵĹɱ���Ͷ�ʣ��<br/>
&nbsp;(��)	��ס����˰����д���������й�������ס����˰����д���������<br/>
&nbsp;&nbsp;��1��	��˰��ʶ��ţ���д����˰����ظ����18λ��˰��ʶ��š�����˰��ʶ�����Ϊ������ס�����˵�Ψһ���ʶ���룬����˰�˵�����˰����ذ��������˰������߿۽������˰������˰�˳��ο۽��걨ʱ��������˰��������衣<br/>
&nbsp;&nbsp;��2��	����������������д��˰�˵Ĺ������ߵ�����<br/>
&nbsp;&nbsp;��3��	�����أ���д��˰�˳����صĹ�����������<br/>
&nbsp;&nbsp;��4��	�Ͷ���ҵ֤���룺��д��˰�����й������Ͷ���ҵ֤�ϵĺ��롣<br/>
&nbsp;&nbsp;��5��	����ְ����д����˰���ھ��ڹ�˾���ε�ְ��<br/>
&nbsp;&nbsp;��6��	����ְ����д����˰���ھ��⹫˾���ε�ְ��<br/>
&nbsp;&nbsp;��7��	�Ƿ�˰��Э����Լ���Է�������˰�����������й�ǩ������˫����˰Э���Ĺ��һ�����ģ��ڡ��ǡ�����Ӧ���ڴ򡰡̡��������ڡ������򡰡̡���<br/>
&nbsp;&nbsp;��8��	����ʱ�䣺��д��˰�˵����й����ڵ������ա�<br/>
&nbsp;&nbsp;��9��	��ְ���ޣ���д��˰������ְ�ܹ͵�λ����ְ���ޡ�<br/>
&nbsp;&nbsp;��10��	Ԥ���뾳ʱ�䣺��д��˰��Ԥ���뾳�������ա�<br/>
&nbsp;&nbsp;��11��	Ԥ���뾳�ص㣺��д��˰��Ԥ���뾳�ĵص㡣<br/>
&nbsp;&nbsp;��12��	������ְ�ܹ͵�λ����д��˰��ǩ����ְ�ܹͺ�ͬ�ĵ�λ�������Ϣ�������д������������ƸǩԼ��λ��������д��<br/>
&nbsp;&nbsp;&nbsp;�����ƣ���д��˰����ְ�ܹ͵�λ������ȫ�ơ�<br/>
&nbsp;&nbsp;&nbsp;�ڿ۽������˱��룺��д˰�����ȷ������ְ�ܹ͵�λ��˰�������롣<br/>
&nbsp;&nbsp;&nbsp;�۵�ַ���������룺��д��ְ�ܹ͵�λ�ĵ�ַ���������롣<br/>
&nbsp;&nbsp;��13��	������ƸǩԼ��λ����д��˰����Ƹ��ǩԼ��λ�������Ϣ�������д������������������ְ�ܹ͵�λ��������д��<br/>
&nbsp;&nbsp;&nbsp;�����ƣ���д��˰����ƸǩԼ��λ������ȫ�ơ�<br/>
&nbsp;&nbsp;&nbsp;�ڿ۽������˱��룺��д˰�����ȷ������ƸǩԼ��λ��˰�������롣<br/>
&nbsp;&nbsp;&nbsp;�۵�ַ���������룺��д��ƸǩԼ��λ�ĵ�ַ���������롣<br/>
&nbsp;&nbsp;��14��	������ǲ��λ�������˰���о�����ǲ��λ�ģ���д������������д��<br/>
&nbsp;&nbsp;&nbsp;�����ƣ���д��˰�˾�����ǲ��λ������ȫ�ƣ����С�������������д��<br/>
&nbsp;&nbsp;&nbsp;�ڵ�ַ����д������ǲ��λ�ĵ�ַ��<br/>
&nbsp;&nbsp;��15��	֧���أ���д��˰��ȡ�õ����õ�֧���أ��ڡ�����֧������������֧�����͡���������ͬʱ֧��������������ѡ��һ����д��<br/>
&nbsp;&nbsp;��16��	����֧���ع��𣨵������������˰��ȡ�õ�����֧����Ϊ����ģ���д����֧���صĹ����������ơ�<br/>
				</td>
		   </tr>
		</table>
		<br/>
	  <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
				 <tr>
				    <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				    <td width="20%" align="center" class="black9"><strong><font color="#0000FF">ע������</font></strong></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47"  >
				        <p style ="color:red">
				            &nbsp;&nbsp;&nbsp;&nbsp;���������˱��Ǹ��ݡ��л����񹲺͹���������˰��������ʵʩ�����͹�����ط��ɷ���涨��д�ģ�����ʵ�ġ������ġ��ɿ��ġ�<br>
				         </p>
				       </td>
				    </tr>
	</table>
<br/><br/>
</html:form>
<%@ include file="../../../include/footernew.jsp"%>
</body>
</html>