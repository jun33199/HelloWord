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
function doReturn()
{
	document.forms[0].action = "quit.do";
	document.forms[0].submit();
}

function load(){
	
	var btzzxx_djzclx = document.getElementById("btzzxx_djzclx");				//��˰������
	var btzzxx_djzclx_cb = document.getElementsByName("btzzxx_djzclx_cb");
	splitCheckBox(btzzxx_djzclx,btzzxx_djzclx_cb);
	jiSuan();
	js();
	if(document.all.btzzxx_djzclx_3.checked) {//��˰��Ϊ�ϻ���ҵ�ϻ���
		document.getElementById("hc_43").innerHTML="43=(38-39)*40-41-42";
	} else if(!document.all.btzzxx_djzclx_3.checked) {//��˰��Ϊ�Ǻϻ���ҵ�ϻ���
		document.getElementById("hc_43").innerHTML="43=38-39-41-42";
	}
	var msg="<bean:write name='grsdsndsbbForm2014' property='msg'/>";
	if(msg){
		alert(msg);		
	}
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
function doBack(){
	var actionType = document.getElementById("actionType");
	actionType.value="Back";
	document.forms[0].submit();
}
function doPrevious(){
	var actionType = document.getElementById("actionType");
	actionType.value="Previous";
	document.forms[0].submit();
}
function doSave(){
	
	//��������
	var skssqq = document.all.skssqq;
	var skssqz = document.all.skssqz;
	skssqq.value = document.all.nd.value + document.getElementById("skssrqq").value;
	skssqz.value = document.all.nd.value + document.getElementById("skssrqz").value;
	if(!checkDate(skssqq.value)) {
		alert("˰�������������ʽ����ȷ��");
		return false;
	}
	
	if(!checkDate(skssqz.value)) {
		alert("˰����������ֹ��ʽ����ȷ��");
		return false;
	}
	
	var btzzxx_djzclx = document.getElementById("btzzxx_djzclx");				//��˰������
	var btzzxx_djzclx_cb = document.getElementsByName("btzzxx_djzclx_cb");
	joinCheckBox(btzzxx_djzclx,btzzxx_djzclx_cb);
	
	var actionType = document.getElementById("actionType");
	actionType.value="Save";
	document.forms[0].submit();
}
function jiSuan() {
	var btzzxx_djzclx_3 = document.getElementById("btzzxx_djzclx_3");
	document.all.col_1 .onchange = colChange;
	document.all.col_2 .onchange = colChange;
	document.all.col_3 .onchange = colChange;
	document.all.col_4 .onchange = colChange;
	document.all.col_5 .onchange = colChange;
	document.all.col_6 .onchange = colChange;
	document.all.col_7 .onchange = colChange;
	document.all.col_8 .onchange = colChange;
	document.all.col_9 .onchange = colChange;
	document.all.col_10.onchange = colChange;
	document.all.col_11.onchange = colChange;
	document.all.col_12.onchange = colChange;
	document.all.col_13.onchange = colChange;
	document.all.col_14.onchange = colChange;
	document.all.col_15.onchange = colChange;
	document.all.col_16.onchange = colChange;
	document.all.col_17.onchange = colChange;
	document.all.col_18.onchange = colChange;
	document.all.col_19.onchange = colChange;
	document.all.col_20.onchange = colChange;
	document.all.col_21.onchange = colChange;
	document.all.col_22.onchange = colChange;
	document.all.col_23.onchange = colChange;
	document.all.col_24.onchange = colChange;
	document.all.col_25.onchange = colChange;
	document.all.col_26.onchange = colChange;
	document.all.col_27.onchange = colChange;
	document.all.col_28.onchange = colChange;
	document.all.col_29.onchange = colChange;
	document.all.col_30.onchange = colChange;
	document.all.col_31.onchange = colChange;
	document.all.col_32.onchange = colChange;
	document.all.col_33.onchange = colChange;
	document.all.col_34.onchange = colChange;
	document.all.col_35.onchange = colChange;
	document.all.col_36.onchange = colChange;
	document.all.col_37.onchange = colChange;
	document.all.col_38.onchange = colChange;
	document.all.col_39.onchange = colChange;
	document.all.col_40.onchange = colChange40;
	document.all.col_41.onchange = colChange;
	document.all.col_42.onchange = colChange;
	document.all.col_43.onchange = colChange;
	document.all.col_44.onchange = colChange;
	document.all.col_45.onchange = colChange;
	document.all.col_46.onchange = colChange;
	document.all.col_47.onchange = colChange;
	document.all.col_48.onchange = colChange;
	document.all.col_49.onchange = colChange;
	document.all.col_50.onchange = colChange;
	document.all.col_51.onchange = colChange;
	btzzxx_djzclx_3.onblur=js;
	
	return true;
}

function colChange(){
	//for(var i=0;i<51;){
	//	var obj = eval("document.all.col_"+(++i));
		if(isNaN(this.value)|| this.value=="" ){
			this.value = "0.00";
		}else{
			this.value = parseFloat(this.value).toFixed(2);
		}
	//}
	
	js();
}


function colChange40(){
	//for(var i=0;i<51;){
	//	var obj = eval("document.all.col_"+(++i));
		if(isNaN(this.value)|| this.value=="" ){
			this.value = "0.0000";
		}else{
			this.value = parseFloat(this.value).toFixed(4);
		}
	//}
	
	js();
}

function js() {
	var col_1 = parseFloat(document.all.col_1.value||"0");
	var col_2 = parseFloat(document.all.col_2.value||"0");
	var col_3 = parseFloat(document.all.col_3.value||"0");
	var col_4 = parseFloat(document.all.col_4.value||"0");
	var col_5 = parseFloat(document.all.col_5.value||"0");
	var col_6 = parseFloat(document.all.col_6.value||"0");
	var col_7 = parseFloat(document.all.col_7.value||"0");
	var col_9 = parseFloat(document.all.col_9.value||"0");
	var col_34 = parseFloat(document.all.col_34.value||"0");
	var col_37 = parseFloat(document.all.col_37.value||"0");
	var col_39 = parseFloat(document.all.col_39.value||"0");
	var col_40 = parseFloat(document.all.col_40.value||"0");
	var col_41 = parseFloat(document.all.col_41.value||"0");
	var col_42 = parseFloat(document.all.col_42.value||"0");
	var col_44 = parseFloat(document.all.col_44.value||"0");
	var col_45 = parseFloat(document.all.col_45.value||"0");
	var col_47 = parseFloat(document.all.col_47.value||"0");
	var col_49 = parseFloat(document.all.col_49.value||"0");
	var col_50 = parseFloat(document.all.col_50.value||"0");
	
	
	
	var col_8 = col_1 - col_2 - col_3 - col_4 - col_5 - col_6 - col_7;
	var col_38 = col_8 + col_9 - col_34 - col_37;
	var col_43 = 0;
	if(document.all.btzzxx_djzclx_3.checked) {//��˰��Ϊ�ϻ���ҵ�ϻ���
		col_43= (col_38 - col_39) * col_40/100 - col_41 - col_42;
		document.getElementById("hc_43").innerHTML="43=(38-39)*40-41-42";
	} else if(!document.all.btzzxx_djzclx_3.checked) {//��˰��Ϊ�Ǻϻ���ҵ�ϻ���
		col_43= col_38 - col_39 - col_41 - col_42;
		document.getElementById("hc_43").innerHTML="43=38-39-41-42";
	}
	var col_46 = col_43 * col_44 - col_45;
	var col_48 = col_46 - col_47;
	var col_51 = col_48 + col_49 - col_50;

	document.all.col_8.value = col_8.toFixed(2);
	document.all.col_38.value = col_38.toFixed(2);
	document.all.col_43.value = col_43.toFixed(2);
	document.all.col_46.value = col_46.toFixed(2);
	document.all.col_48.value = col_48.toFixed(2);
	document.all.col_51.value = col_51.toFixed(2);
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
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="load()">
		<jsp:include page="../../include/header.jsp" flush="true" >
    		<jsp:param name="name" value="��������Ӫ���ø�������˰��˰�걨��" />
			<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>
<html:form action="/webapp/smsb/grsds/grsdsNdsbbAction2014.do">
<table width="80%" height="50%" border="0" align="center" cellpadding="0" cellspacing="0" >
	
	<bean:define id="sfzjlxListID" name="grsdsndsbbForm2014" property="sfzjlxList"/>	<!-- ���֤�����ʹ���� -->
	<bean:define id="gjListID" name="grsdsndsbbForm2014" property="gjList"/>			<!-- ���� -->
	<input type="hidden" name="actionType" id="actionType" value="Show" />
			<!--¼����Ϣ-->
			<tr>
				<td class="1-td1" >¼����������Ӫ���ø�������˰��˰�걨��</td>
			</tr>

			<tr>
				<td class="1-td2" >
					<br/>
					<div align="left" style="display:inline">
						<FONT color="#000000" size="1">˰��������(yyyyMMdd)��</FONT>
						<html:hidden name="grsdsndsbbForm2014" property="nd"></html:hidden>
						
						<html:hidden name="grsdsndsbbForm2014" property="skssqq" ></html:hidden>
						<bean:write name="grsdsndsbbForm2014" property="nd"/>
						<html:text styleId="skssrqq" name="grsdsndsbbForm2014"  property="skssrqq" maxlength="4" style="width:40"/>��
						
						
						<html:hidden name="grsdsndsbbForm2014" property="skssqz"></html:hidden>
						<bean:write name="grsdsndsbbForm2014" property="nd"/>
						<html:text styleId="skssrqz" name="grsdsndsbbForm2014"  property="skssrqz" maxlength="4" style="width:40"/>
					</div>
					<div align="right" style="display:inline"><FONT color="#000000" size="1">��λ�������Ԫ�������Ƿ֣�</FONT></div>
					<table  cellSpacing="0" cellPadding="0" width="97%" border="0" align = "center">
						<tr>
							<td class="2-td2-t-left" rowspan="2" width="10%">Ͷ������Ϣ</td>
							<td class="2-td2-t-left" width="10%">����</td>
							<td class="2-td2-t-left" width="10%">
								<html:text styleId="tzzxx_name" name="grsdsndsbbForm2014"  property="tzzxx_name"/>
							</td>
							<td class="2-td2-t-left" width="10%">���֤������</td>
							<td class="2-td2-t-left" width="10%">
								<html:hidden name="grsdsndsbbForm2014" property="tzzxx_sfzjlx"></html:hidden>
								<html:select disabled="true" name="grsdsndsbbForm2014" property="tzzxx_sfzjlx" >
                          				<html:options collection="sfzjlxListID" property="value" labelProperty="text" />
                          		</html:select>
							</td>
							<td class="2-td2-t-left" width="10%">���֤������</td>
							<td class="2-td2-t-center" colspan="4" width="40%">
								<html:text styleId="tzzxx_sfzjhm" name="grsdsndsbbForm2014" style="background-color:#cccccc"  property="tzzxx_sfzjhm" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" width="10%">����</td>
							<td class="2-td2-left" colspan="3" width="30%">
								<html:select  name="grsdsndsbbForm2014" property="tzzxx_gj" >
                          				<html:options collection="gjListID" property="value" labelProperty="text" />
                          		</html:select>
							</td>
							<td class="2-td2-left" width="10%">��˰��ʶ���</td>
							<td class="2-td2-center" colspan="4" width="40%">
								<html:text styleId="tzzxx_nsrsbh" name="grsdsndsbbForm2014"  property="tzzxx_nsrsbh"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" rowspan="2" width="10%" >��Ͷ�ʵ�λ��Ϣ</td>
							<td class="2-td2-left" width="10%">����</td>
							<td class="2-td2-left" colspan="3" width="30%">
								
								<html:hidden property="btzzxx_jsjdm" name="grsdsndsbbForm2014" />
								
								<html:text styleId="btzzxx_name" name="grsdsndsbbForm2014"  property="btzzxx_name"/>
							
							</td>
							<td class="2-td2-left" width="10%">��˰��ʶ���</td>
							<td class="2-td2-center" colspan="4" width="40%">
									
									<html:text styleId="btzzxx_nsrsbh" name="grsdsndsbbForm2014"  property="btzzxx_nsrsbh"/>
								</input>
							</td>
						</tr>
						<tr>
							
							<td class="2-td2-left" width="10%">����</td>
							<td class="2-td2-center" colspan="8" width="80%">
								<html:hidden property="btzzxx_djzclx" name="grsdsndsbbForm2014" />
								<input type='checkbox' name='btzzxx_djzclx_cb' id="btzzxx_djzclx_0" value="0">���幤�̻�</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='btzzxx_djzclx_cb' id="btzzxx_djzclx_1" value="1">�а������⾭Ӫ��</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='btzzxx_djzclx_cb' id="btzzxx_djzclx_2" value="2">���˶�����ҵ</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='btzzxx_djzclx_cb' id="btzzxx_djzclx_3" value="3">�ϻ���ҵ</input>
							</td>
						</tr>
						
						<tr>
							<td class="2-td2-left" style="border-bottom-width:0px" width="10%">��ƽ��ְ������</td>
							<td class="2-td2-left" style="border-bottom-width:0px" colspan="2" width="20%">
								
								<html:text styleId="btzzxx_npjzgrs" name="grsdsndsbbForm2014"  property="btzzxx_npjzgrs"/>
							</td>
							<td class="2-td2-left" style="border-bottom-width:0px" width="10%">�����ܶԪ��</td>
							<td class="2-td2-left" style="border-bottom-width:0px" colspan="2" width="20%">
								
								<html:text styleId="btzzxx_gzze" name="grsdsndsbbForm2014"  property="btzzxx_gzze"/>
							</td>
							<td class="2-td2-left" style="border-bottom-width:0px" width="10%">Ͷ�����������ˣ�</td>
							<td class="2-td2-center" style="border-bottom-width:0px" colspan="3" width="30%">
								
								<html:text styleId="btzzxx_tzzrs" name="grsdsndsbbForm2014"  property="btzzxx_tzzrs"/>
							</td>
						</tr>
						<tr>
							<td class="2-td1-left" style="border-bottom-size:0" colspan="2" width="20%">��Ŀ</td>
							<td class="2-td1-left" style="border-bottom-size:0" width="10%">�д�</td>
							<td class="2-td1-left" style="border-bottom-size:0" colspan="2" width="20%">���</td>
							<td class="2-td1-left" style="border-bottom-size:0" colspan="2" width="20%">��Ŀ</td>
							<td class="2-td1-left" style="border-bottom-size:0" width="10%">�д�</td>
							<td class="2-td1-center" style="border-bottom-size:0" colspan="2" width="20%">���</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">һ�������ܶ�</td>
							<td class="2-td2-left">1</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_1" name="grsdsndsbbForm2014"  property="col_1"/>
							</td>
							<td class="2-td2-left" colspan="2">˰�����ɽ𡢷��𡢷���</td>
							<td class="2-td2-left">27</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_27" name="grsdsndsbbForm2014"  property="col_27"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">�����ɱ�</td>
							<td class="2-td2-left">2</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_2" name="grsdsndsbbForm2014"  property="col_2"/>
							</td>
							<td class="2-td2-left" colspan="2">����֧�����ǽ����͹�����ҵ����</td>
							<td class="2-td2-left">28</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_28" name="grsdsndsbbForm2014"  property="col_28"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">Ӫҵ����</td>
							<td class="2-td2-left">3</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_3" name="grsdsndsbbForm2014"  property="col_3"/>
							</td>
							<td class="2-td2-left" colspan="2">�ֺ��¹���ʧ�⳥</td>
							<td class="2-td2-left">29</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_29" name="grsdsndsbbForm2014"  property="col_29"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">�������</td>
							<td class="2-td2-left">4</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_4" name="grsdsndsbbForm2014"  property="col_4"/>
							</td>
							<td class="2-td2-left" colspan="2">����ĸ���׼����</td>
							<td class="2-td2-left">30</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_30" name="grsdsndsbbForm2014"  property="col_30"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">�������</td>
							<td class="2-td2-left">5</td>
							<td class="2-td2-left" colspan="2">
							
								<html:text styleId="col_5" name="grsdsndsbbForm2014"  property="col_5"/>
							</td>
							<td class="2-td2-left" colspan="2">Ͷ���߹���н��</td>
							<td class="2-td2-left">31</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_31" name="grsdsndsbbForm2014"  property="col_31"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">Ӫҵ˰�𼰸���</td>
							<td class="2-td2-left">6</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_6" name="grsdsndsbbForm2014"  property="col_6"/>
							</td>
							<td class="2-td2-left" colspan="2">�������޹ص�֧��</td>
							<td class="2-td2-left">32</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_32" name="grsdsndsbbForm2014"  property="col_32"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">Ӫҵ��֧��</td>
							<td class="2-td2-left">7</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_7" name="grsdsndsbbForm2014"  property="col_7"/>
							</td>
							<td class="2-td2-left" colspan="2">���У�Ͷ���߼�ͥ����</td>
							<td class="2-td2-left">33</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_33" name="grsdsndsbbForm2014"  property="col_33"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">���������ܶ�</td>
							<td class="2-td2-left">8=1-2-3-4-5-6-7</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_8" name="grsdsndsbbForm2014"  property="col_8"/>
							</td>
							<td class="2-td2-left" colspan="2">�ġ���˰�������ٶ�</td>
							<td class="2-td2-left">34</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_34" name="grsdsndsbbForm2014"  property="col_34"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">������˰�������Ӷ�</td>
							<td class="2-td2-left">9</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_9" name="grsdsndsbbForm2014"  property="col_9"/>
							</td>
							<td class="2-td2-left" colspan="2">1����ծ��Ϣ����</td>
							<td class="2-td2-left">35</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_35" name="grsdsndsbbForm2014"  property="col_35"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">1�������涨��׼�۳�����Ŀ</td>
							<td class="2-td2-left">10</td>
							<td class="2-td2-left" colspan="2">
							
								<html:text styleId="col_10" name="grsdsndsbbForm2014"  property="col_10"/>
							</td>
							<td class="2-td2-left" colspan="2">2������</td>
							<td class="2-td2-left">36</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_36" name="grsdsndsbbForm2014"  property="col_36"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��1��ְ��������</td>
							<td class="2-td2-left">11</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_11" name="grsdsndsbbForm2014"  property="col_11"/>
							</td>
							<td class="2-td2-left" colspan="2">�塢��ǰ����������</td>
							<td class="2-td2-left">37</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_37" name="grsdsndsbbForm2014"  property="col_37"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��2��ְ����������</td>
							<td class="2-td2-left">12</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_12" name="grsdsndsbbForm2014"  property="col_12"/>
							</td>
							<td class="2-td2-left" colspan="2">��������˰�������������Ӫ����</td>
							<td class="2-td2-left">38=8+9-34-37</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_38" name="grsdsndsbbForm2014"  property="col_38"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��3�����ᾭ��</td>
							<td class="2-td2-left">13</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_13" name="grsdsndsbbForm2014"  property="col_13"/>
							</td>
							<td class="2-td2-left" colspan="2">�����ֲ���ǰ��ȿ���</td>
							<td class="2-td2-left">39</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_39" name="grsdsndsbbForm2014"  property="col_39"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��4����Ϣ֧��</td>
							<td class="2-td2-left">14</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_14" name="grsdsndsbbForm2014"  property="col_14"/>
							</td>
							<td class="2-td2-left" colspan="2">�ˣ��������</td>
							<td class="2-td2-left">40</td>
							<td class="2-td2-center" colspan="2">
						
								<html:text styleId="col_40" name="grsdsndsbbForm2014"  property="col_40"/>%
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��5��ҵ���д���</td>
							<td class="2-td2-left">15</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_15" name="grsdsndsbbForm2014"  property="col_15"/>
							</td>
							<td class="2-td2-left" colspan="2">�ߡ�����۳�����������</td>
							<td class="2-td2-left">41</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_41" name="grsdsndsbbForm2014"  property="col_41"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��6�����Ѻ�ҵ��������</td>
							<td class="2-td2-left">16</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_16" name="grsdsndsbbForm2014"  property="col_16"/>
							</td>
							<td class="2-td2-left" colspan="2">�ˡ�Ͷ���߼�������</td>
							<td class="2-td2-left">42</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_42" name="grsdsndsbbForm2014"  property="col_42"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��7�������͹�����ҵ����</td>
							<td class="2-td2-left">17</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_17" name="grsdsndsbbForm2014"  property="col_17"/>
							</td>
							<td class="2-td2-left" colspan="2">�š�Ӧ��˰���ö�</td>
							<td class="2-td2-left" id="hc_43">43</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_43" name="grsdsndsbbForm2014"  property="col_43"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��8��ס��������</td>
							<td class="2-td2-left">18</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_18" name="grsdsndsbbForm2014"  property="col_18"/>
							</td>
							<td class="2-td2-left" colspan="2">ʮ��˰�ʣ�%��</td>
							<td class="2-td2-left">44</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_44" name="grsdsndsbbForm2014"  property="col_44"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��9����ᱣ�շ�</td>
							<td class="2-td2-left">19</td>
							<td class="2-td2-left" colspan="2">
							
								<html:text styleId="col_19" name="grsdsndsbbForm2014"  property="col_19"/>
							</td>
							<td class="2-td2-left" colspan="2">ʮһ������۳���</td>
							<td class="2-td2-left">45</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_45" name="grsdsndsbbForm2014"  property="col_45"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��10���۾ɷ���</td>
							<td class="2-td2-left">20</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_20" name="grsdsndsbbForm2014"  property="col_20"/>
							</td>
							<td class="2-td2-left" colspan="2">ʮ����Ӧ��˰��</td>
							<td class="2-td2-left">46��43*44-45</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_46" name="grsdsndsbbForm2014"  property="col_46"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��11�������ʲ�̯��</td>
							<td class="2-td2-left">21</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_21" name="grsdsndsbbForm2014"  property="col_21"/>
							</td>
							<td class="2-td2-left" colspan="2">��������˰��</td>
							<td class="2-td2-left">47</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_47" name="grsdsndsbbForm2014"  property="col_47"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��12���ʲ���ʧ</td>
							<td class="2-td2-left">22</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_22" name="grsdsndsbbForm2014"  property="col_22"/>
							</td>
							<td class="2-td2-left" colspan="2">ʮ����ȫ��Ӧ��˰��</td>
							<td class="2-td2-left">48=46-47</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_48" name="grsdsndsbbForm2014"  property="col_48"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">��13������</td>
							<td class="2-td2-left">23</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_23" name="grsdsndsbbForm2014"  property="col_23"/>
							</td>
							<td class="2-td2-left" colspan="2">�ӣ��ڳ�δ��˰��</td>
							<td class="2-td2-left">49</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_49" name="grsdsndsbbForm2014"  property="col_49"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">������۳�����Ŀ</td>
							<td class="2-td2-left">24</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_24" name="grsdsndsbbForm2014"  property="col_24"/>
							</td>
							<td class="2-td2-left" colspan="2">����ȫ����Ԥ��˰��</td>
							<td class="2-td2-left">50</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_50" name="grsdsndsbbForm2014"  property="col_50"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">�ʱ���֧��</td>
							<td class="2-td2-left">25</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_25" name="grsdsndsbbForm2014"  property="col_25"/>
							</td>
							<td class="2-td2-left" colspan="2">ʮ�ġ�Ӧ�����ˣ�˰��</td>
							<td class="2-td2-left">51=48+49-50</td>
							<td class="2-td2-center" colspan="2">
								
								<html:text styleId="col_51" name="grsdsndsbbForm2014"  property="col_51"/>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">�����ʲ����á�����֧��</td>
							<td class="2-td2-left">26</td>
							<td class="2-td2-left" colspan="2">
								
								<html:text styleId="col_26" name="grsdsndsbbForm2014"  property="col_26"/>
							</td>
							<td class="2-td2-left" colspan="2">&#160;</td>
							<td class="2-td2-left">&#160;</td>
							<td class="2-td2-center" colspan="2">&#160;</td>
						</tr>
					</table>
					<br/>

				</td>
			</tr>
		
		
</table>

<div  align="center">
	<span>
		<a style="cursor:hand"  tabIndex="-1" onClick="doSave();return false;"><img name="baocun" value="����" alt="����" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>/images/baocun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/baocun1.jpg" width="79" height="22" id="baocun"></a>&nbsp;&nbsp;
		<a style="cursor:hand"  tabIndex="-1" onClick="doPrevious();return false;"><img name="shangyiye" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>/images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>&nbsp;&nbsp;
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
&nbsp;���������ڲ������ա����幤�̻�����������Ӫ���á��͡�������ҵ��λ�ĳа���Ӫ�����⾭Ӫ���á���������˰�ĸ��幤�̻����а����⾭Ӫ�ߡ����˶�����ҵͶ���ߺͺϻ���ҵ�ϻ��˵ĸ�������˰��Ȼ�����ɡ���˰���ڰ����걨ʱ����ͬʱ��������2������������˰������Ϣ��B������<br/>
&nbsp;�ϻ���ҵ������������������Ȼ��Ͷ���ߵģ�Ӧ�ֱ������<br/>
����	�걨����<br/>
&nbsp;���幤�̻������˶�����ҵͶ���ߡ��ϻ���ҵ�ϻ��˵���������Ӫ����Ӧ�ɸ�������˰�������˰�걨��Ӧ��������˺��������ڰ���<br/>
&nbsp;������ҵ��λ�а���Ӫ�����⾭Ӫ��Ӧ�ɸ�������˰�������˰�걨��Ӧ��������˺���ʮ���ڰ�����˰��һ���ڷִ�ȡ�óа������⾭Ӫ���õģ�Ӧ��������˺��������ڰ��������ɡ�<br/>
&nbsp;��˰�˲��ܰ��涨���ް�����˰�걨�ģ�Ӧ�����ա��л����񹲺͹�˰�����չ����������¼��˰�����ܷ�������ʵʩϸ��Ĺ涨���������걨��<br/>
����	���������д���£�<br/>
&nbsp;��һ����ͷ��Ŀ<br/>
&nbsp;˰�������ڣ���ָ��˰��ȡ�����õ�Ӧ�ɸ�������˰��������ڼ䣬Ӧ��д�������ֹ�����ա�<br/>
&nbsp;������������Ϣ��<br/>
&nbsp;&nbsp;1.	Ͷ������Ϣ������д���幤�̻�ҵ�����а���Ӫ�ߡ����⾭Ӫ�ߡ����˶�����ҵͶ���ߡ��ϻ���ҵ�ϻ��˵������Ϣ��<br/>
&nbsp;&nbsp;&nbsp;��1��	��������д��˰���������й�������ס�����ˣ�������Ӧ�����С�����ͬʱ��д��<br/>
&nbsp;&nbsp;&nbsp;��2��	���֤�����ͣ���д��ʶ����˰��Ψһ��ݵ���Ч֤�����ơ�<br/>
&nbsp;&nbsp;&nbsp;&nbsp;��	���й�������ס���ĸ��ˣ���д���֤������֤��ʿ��֤��֤�����ơ�<br/>
&nbsp;&nbsp;&nbsp;&nbsp;��	���й�������ס���ĸ��ˣ���д���ա��۰ľ��������ڵ�ͨ��֤��̨�����������½ͨ��֤��֤�����ơ�<br/>
&nbsp;&nbsp;&nbsp;��3��	���֤�����룺��д��˰�����֤���ϵĺ��롣<br/>
&nbsp;&nbsp;&nbsp;��4��	����������������д��˰�˵Ĺ������ߵ�����<br/>
&nbsp;&nbsp;&nbsp;��5��	��˰��ʶ��ţ����й�������ס���ĸ�����д����ס���ĸ��˲���д��������д˰����ظ����18λ��˰��ʶ��š�˰�����δ����ģ�����д��<br/>
&nbsp;&nbsp;&nbsp;˰����ظ��辳����ס�����˵�18λ��˰��ʶ��ţ���Ϊ��Ψһ���ʶ���룬����˰�˵�����˰����ذ��������˰�����۽������˰������˰�˳��ο۽��걨ʱ��������˰����ظ��衣<br/>
&nbsp;&nbsp;2.	��Ͷ�ʵ�λ��Ϣ����<br/>
&nbsp;&nbsp;&nbsp;��1��	���ƣ���д˰����غ˷���Ͷ�ʵ�λ˰��Ǽ�֤�������ĵ�λȫ�ơ�<br/>
&nbsp;&nbsp;&nbsp;��2��	��˰��ʶ��ţ���д˰����غ˷���˰��Ǽ�֤���롣<br/>
&nbsp;&nbsp;&nbsp;��3��	���ͣ���˰�˸�����������ڶ�Ӧ���ڴ򡰡̡���<br/>
&nbsp;���������ڸ��е���д��<br/>
&nbsp;&nbsp1.	��1�С������ܶ����д��Ͷ�ʵ�λ�ڱ�����ȡ�õ������ܶ<br/>
&nbsp;&nbsp;2.	��2�С��ɱ�������д��Ͷ�ʵ�λ�ڱ�������Ҫ��Ӫҵ���������Ӫҵ�����ĳɱ��ܶ<br/>
&nbsp;&nbsp;3.	��3�С�Ӫҵ���á������Ͷ�ʵ�λ��������Ʒ�Ͳ��ϡ��ṩ����Ĺ����з����ĸ��ַ��á�<br/>
&nbsp;&nbsp;4.	��4�С�������á������Ͷ�ʵ�λΪ��֯�͹�����ҵ������Ӫ�����Ĺ�����á�<br/>
&nbsp;&nbsp;5.	��5�С�������á������Ͷ�ʵ�λΪ�Ｏ������Ӫ�����ʽ�ȷ����ĳ��ʷ��á�<br/>
&nbsp;&nbsp;6.	��6�С�Ӫҵ˰�𼰸��ӡ������Ͷ�ʵ�λ��Ӫ�������Ӫҵ˰������˰������ά������˰����Դ˰��������ֵ˰�ͽ����Ѹ��ӵ����˰�ѡ�<br/>
&nbsp;&nbsp;7.	��8�С������ܶ������������μ��㡣<br/>
&nbsp;&nbsp;��8�У���1�ШD��2�ШD��3�ШD��4�ШD��5�ШD��6�ШD��7��<br/>
&nbsp;&nbsp;8.	��10�С������涨��׼�۳�����Ŀ������ָ��Ͷ�ʵ�λ������������˰������ʵʩ���������˰�շ��ɷ������߹涨�Ŀ۳���׼���۳��ĸ��ֳɱ������ú���ʧ��Ӧ�����Ӧ��˰���ö�Ĳ��֡�<br/>
&nbsp;&nbsp;9.	��24�С�������۳�����Ŀ������ָ�涨������۳�������Ͷ�ʵ�λ�ѽ���۳��ĸ���ɱ������ú���ʧ��Ӧ�����Ӧ��˰���ö�Ĳ��֡�<br/>
&nbsp;&nbsp;10.	��35�С���ծ��Ϣ���롱����ָ��ҵ��������˰�����Ѽ�������������ծ��ȡ�õ���Ϣ��<br/>
&nbsp;&nbsp;11.	��37�С���ǰ����������������ָ��ǰ��ȷ����Ķ�ƻ��ټƵ�Ӧ��˰���ö<br/>
&nbsp;&nbsp;12.	��38�С�����˰�������������Ӫ���á�������������μ��㡣<br/>
&nbsp;&nbsp;��38�У���8�У���9�ШD��34�ШD��37��<br/>
&nbsp;&nbsp;13.	��39�С��ֲ���ǰ��ȿ��𡱣���ָ��ҵ���ݹ涨����ǰ��ȿ���������˰ǰ�ֲ�����Ӧ������Ӧ��˰���ö<br/>
&nbsp;&nbsp;14.	��40�С��������������˰��Ϊ�ϻ���ҵ�ϻ��˵ģ���д����������������պϻ���ҵ���䷽���й涨�ĸúϻ��˵ı�����д��û�У�����ƽ�����䡣<br/>
&nbsp;&nbsp;15.	��41�С�����۳����������á�����ָ���շ��ɷ���涨����˰ǰ�۳����������á�û�еģ�����磺������˰���ֹܾ�����ʦ��������ҵ��Ա�йظ�������˰����Ĺ��桷������˰���ֹܾ���2012���53�ţ��������涨�����<br/>
&nbsp;&nbsp;16.	��42�С�Ͷ���߼������á�����ָ����˰�����йط��ɷ���涨���ڸ��幤�̻�ҵ�������˶�����ҵͶ���ߺͺϻ���ҵ�ϻ��˵�������Ӫ���ü�����������˰ʱ������˰ǰ�۳���Ͷ���߱��˵����Ƽ������á�2011��9��1����ִ��42000Ԫ/���׼���Ժ��׼���������߹涨ִ�С�<br/>
&nbsp;&nbsp;17.	��43�С�Ӧ��˰���ö�����ݲ�ͬ�����������дμ�����д��<br/>
&nbsp;&nbsp;&nbsp;��1��	��˰��Ϊ�Ǻϻ���ҵ�ϻ��˵�<br/>
&nbsp;&nbsp;&nbsp;��43�У���38�У���39�У���41�У���42��<br/>
&nbsp;&nbsp;&nbsp;��2��	��˰��Ϊ�ϻ���ҵ�ϻ��˵�<br/>
&nbsp;&nbsp;&nbsp;��43�У�����38�У���39�У�����40�У���41�У���42��<br/>
&nbsp;&nbsp;18.	��44�С�˰�ʡ�����45�С�����۳�����������˰���������涨��д��<br/>
&nbsp;&nbsp;19.	��46�С�Ӧ��˰�����������μ�����д��<br/>
&nbsp;&nbsp;��46�У���43�С���44�У���45��<br/>
&nbsp;&nbsp;20.	��48�С�ȫ��Ӧ��˰�����������μ�����д��<br/>
&nbsp;&nbsp;��48�У���46�У���47��<br/>
&nbsp;&nbsp;21.	��51�С�Ӧ�����ˣ�˰�����������μ�����д��<br/>
&nbsp;&nbsp;��51�У���48�У���49�У���50��<br/>

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
				<br/>
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
<%@ include file="../../include/footernew.jsp"%>
</body>
</html>