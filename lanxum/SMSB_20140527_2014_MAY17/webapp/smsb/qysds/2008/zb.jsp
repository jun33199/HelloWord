<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.zb.web.ZbForm2008"%>
<%@ page import="java.util.HashMap"%>

<html>
<head>
<title>�л����񹲺͹���ҵ����˰�����˰�걨����A�ࣩ</title>
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
	src="../../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/function.js"></script>
</head>

<script language="JavaScript">
		<%/*��ʼ��*/%>	
		function doInit()
		{
		
		//��ʼ���ı���onChange�¼�����
		initNumText("ljje",35);
		<%
		ZbForm2008 nbForm=(ZbForm2008)request.getAttribute("zbForm2008");
			if (nbForm!=null && nbForm.getDataList().size()>0){
				for(int i=0;i<nbForm.getDataList().size();i++){
					HashMap map=(HashMap)nbForm.getDataList().get(i);
					int hc=Integer.parseInt((String)map.get("hc"));
					String ljje=(String)map.get("ljje");
					%>
					obj = eval("document.getElementById('<%=hc%>_1')");
					obj.value = '<%=ljje%>';
					<% 
				}
			}
			%>
		}		
		
		<%/*����*/%>
		function doSave()
		{
			doSubmitForm('Save');
		
		}
		<%/*���ڹ�ϵУ��*/%>
		function doCheck()
		{
			doSubmitForm('Check');
		}
		<%/*���*/%>
		function doReset()
		{
			if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
			{
		   		for(var i=1; i < 43; i++){
					obj = eval("document.getElementById('" + i+"_1')");
					if(obj.readOnly!=true)
					obj.value = "";
		   		}
			}
		}
	
		<%/*ɾ��*/%>
		function doDelete()
		{
			doSubmitForm('Delete');
		}
		
		<%/*����*/%>
		function doExit()
		{
			doSubmitForm('Exit');
		}
		
	</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInit()">
<%@include file="../../include/header.jsp"%>
<br>

<html:form action="/webapp/smsb/qysds/2008/zbAction2008">
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />

	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">�л����񹲺͹���ҵ����˰�����˰�걨����A�ࣩ</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="zbForm2008" property="sbrq" scope="request" filter="true" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��λ��Ԫ�������Ƿ֣�
			</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;���������:<bean:write
				name="zbForm2008" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="zbForm2008" property="nsrmc" scope="request" filter="true" />&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">
			<TABLE class="table-99" align="center">
				<TR>
					<TD>
					<div id="Layer2" style="position:static;">
					<table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
						<tr>
							<!--<td class="2-td1-left" nowrap>�д�</td>
									   	<td class="2-td1-left" nowrap>��Ŀ</td><!-->
							<td class="2-td1-left" nowrap>�д�</td>
							<td class="2-td1-left" nowrap>��Ŀ</td>
							<td class="2-td1-center" nowrap>�ۼƽ��</td>
							<td class="2-td1-left" nowrap>�д�</td>
							<td class="2-td1-left" nowrap>��Ŀ</td>
							<td class="2-td1-center" nowrap>�ۼƽ��</td>
						</tr>
						<tr>
							<td colspan="3" class="2-td2-center" nowrap>�����ܶ����</td>
							<td class="2-td2-left" nowrap>22 <input type="hidden" name="hc"
								value="22" id="hc_22"></td>
							<td class="2-td2-left-qysds1" nowrap>�ӣ�����Ӧ˰�����ֲ����ڿ���</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='22_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>1 <input type="hidden" name="hc"
								value="1" id="hc_1"></td>
							<td class="2-td2-left-qysds1" nowrap>һ��Ӫҵ���루���һ��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='1_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>23 <input type="hidden" name="hc"
								value="23" id="hc_23"></td>
							<td class="2-td2-left-qysds1" nowrap>��˰���������ã�13��14��15��22��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='23_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>2 <input type="hidden" name="hc"
								value="2" id="hc_2"></td>
							<td class="2-td2-left-qysds1" nowrap>����Ӫҵ�ɱ����������</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='2_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>24 <input type="hidden" name="hc"
								value="24" id="hc_24"></td>
							<td class="2-td2-left-qysds1" nowrap>�����ֲ���ǰ��ȿ�������ģ�</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='24_1' value='' size='13' tabindex='2'></td>
						</tr>

						<tr>
							<td class="2-td2-left" nowrap>3 <input type="hidden" name="hc"
								value="3" id="hc_3"></td>
							<td class="2-td2-left-qysds1" nowrap> ����Ӫҵ˰�𼰸���</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='3_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>25 <input type="hidden" name="hc"
								value="25" id="hc_25"></td>
							<td class="2-td2-left-qysds1" nowrap>Ӧ��˰���ö23��24��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='25_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>4 <input type="hidden" name="hc"
								value="4" id="hc_4"></td>
							<td class="2-td2-left-qysds1" nowrap>�������۷��ã��������</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='4_1' value='' size='13' tabindex='1'></td>
							<td colspan="3" class="2-td2-center" nowrap>Ӧ��˰�����</td>

						</tr>
						<tr>
							<td class="2-td2-left" nowrap>5 <input type="hidden" name="hc"
								value="5" id="hc_5"></td>
							<td class="2-td2-left-qysds1" nowrap>�����������ã��������</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='5_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>26 
							  <input type="hidden" name="hc"
								value="26" id="hc_26"></td>
							<td class="2-td2-left-qysds1" nowrap>˰�ʣ�25%��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='26_1' value='25' readOnly='true' class='text-noborder' style='text-align:center' size='1' tabindex='-1'>%</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>6 <input type="hidden" name="hc"
								value="6" id="hc_6"></td>
							<td class="2-td2-left-qysds1" nowrap>����������ã��������</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='6_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>27 
							  <input type="hidden" name="hc"
								value="27" id="hc_27"></td>
							<td class="2-td2-left-qysds1" nowrap>Ӧ������˰�25��26��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='27_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>7 <input type="hidden" name="hc"
								value="7" id="hc_7"></td>
							<td class="2-td2-left-qysds1" nowrap>�����ʲ���ֵ��ʧ</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='7_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>28 
							  <input type="hidden" name="hc"
								value="28" id="hc_28"></td>
							<td class="2-td2-left-qysds1" nowrap>������������˰�����壩</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='28_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>8 <input type="hidden" name="hc"
								value="8" id="hc_8"></td>
							<td class="2-td2-left-qysds1" nowrap>�ӣ����ʼ�ֵ�䶯����</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='8_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>29 
							  <input type="hidden" name="hc"
								value="29" id="hc_29"></td>
							<td class="2-td2-left-qysds1" nowrap>������������˰�����壩</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='29_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>9 <input type="hidden" name="hc"
								value="9" id="hc_9"></td>
							<td class="2-td2-left-qysds1" nowrap>����Ͷ������</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='9_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>30 <input type="hidden" name="hc"
								value="30" id="hc_30"></td>
							<td class="2-td2-left-qysds1" nowrap>Ӧ��˰�27��28��29��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='30_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>10 <input type="hidden" name="hc"
								value="10" id="hc_10"></td>
							<td class="2-td2-left-qysds1" nowrap>����Ӫҵ����</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='10_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>31 <input type="hidden" name="hc"
								value="31" id="hc_31"></td>
							<td class="2-td2-left-qysds1" nowrap>�ӣ���������Ӧ������˰��������</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='31_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>11 <input type="hidden" name="hc"
								value="11" id="hc_11"></td>
							<td class="2-td2-left-qysds1" nowrap>�ӣ�Ӫҵ�����루���һ��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='11_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>32 <input type="hidden" name="hc"
								value="32" id="hc_32"></td>
							<td class="2-td2-left-qysds1" nowrap>�����������õ�������˰��������</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='32_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>12 <input type="hidden" name="hc"
								value="12" id="hc_12"></td>
							<td class="2-td2-left-qysds1" nowrap>����Ӫҵ��֧�����������</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='12_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>33 
							  <input type="hidden" name="hc"
								value="33" id="hc_33"></td>
							<td class="2-td2-left-qysds1" nowrap>ʵ��Ӧ������˰�30��31��32��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='33_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>13 <input type="hidden" name="hc"
								value="13" id="hc_13"></td>
							<td class="2-td2-left-qysds1" nowrap>���������ܶ10��11��12��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='13_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>34 
							  <input type="hidden" name="hc"
								value="34" id="hc_34"></td>
							<td class="2-td2-left-qysds1" nowrap>��:�����ۼ�ʵ����Ԥ�ɵ�����˰��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='34_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td colspan="3" class="2-td2-center" nowrap>Ӧ��˰���ö�ļ���</td>
							<td class="2-td2-left" nowrap>35 
							  <input type="hidden" name="hc"
								value="35" id="hc_35"></td>
							<td class="2-td2-left-qysds1" nowrap>���У�������˰���ܻ�����̯Ԥ�ɵ�˰��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='35_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>14 <input type="hidden" name="hc"
								value="14" id="hc_14"></td>
							<td class="2-td2-left-qysds1" nowrap>�ӣ���˰�������Ӷ�������</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='14_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>36 
							  <input type="hidden" name="hc"
								value="36" id="hc_36"></td>
							<td class="2-td2-left-qysds1" nowrap>������������˰���ܻ�����������Ԥ�ɵ�˰��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='36_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>15 <input type="hidden" name="hc"
								value="15" id="hc_15"></td>
							<td class="2-td2-left-qysds1" nowrap>������˰�������ٶ�������</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='15_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>37 
							  <input type="hidden" name="hc"
								value="37" id="hc_37"></td>
							<td class="2-td2-left-qysds1" nowrap>������������˰���ܻ���������֧������̯��Ԥ��˰��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='37_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>16 <input type="hidden" name="hc"
								value="16" id="hc_16"></td>
							<td class="2-td2-left-qysds1" nowrap>���У�����˰����</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='16_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>38 
							  <input type="hidden" name="hc"
								value="38" id="hc_38"></td>
							<td class="2-td2-left-qysds1" nowrap>�������ϲ���˰��ĸ�����ƣ���Ա��ҵ�͵�Ԥ�ɱ���</td>
							<td class="2-td2-center" nowrap>&nbsp<input type='text' name='ljje'
								id='38_1' value='' size='13' tabindex='2'>%</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>17 <input type="hidden" name="hc"
								value="17" id="hc_17"></td>
							<td class="2-td2-left-qysds1" nowrap>��������˰����</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='17_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>39 
							  <input type="hidden" name="hc"
								value="39" id="hc_39"></td>
							<td class="2-td2-left-qysds1" nowrap>�������ϲ���˰��ҵ�͵�Ԥ�ɵ�����˰��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='39_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>18 <input type="hidden" name="hc"
								value="18" id="hc_18"></td>
							<td class="2-td2-left-qysds1" nowrap>��������������</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='18_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>40 
							  <input type="hidden" name="hc"
								value="40" id="hc_40"></td>
							<td class="2-td2-left-qysds1" nowrap>����Ӧ�����ˣ�������˰�33��34��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='40_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>19 <input type="hidden" name="hc"
								value="19" id="hc_19"></td>
							<td class="2-td2-left-qysds1" nowrap>������������˰��Ŀ����</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='19_1' value='' size='13' tabindex='1'></td>
							<td colspan="3" class="2-td2-center" nowrap>��������</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>20 <input type="hidden" name="hc"
								value="20" id="hc_21"></td>
							<td class="2-td2-left-qysds1" nowrap>�������Ӽƿ۳�</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='20_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>41 
							  <input type="hidden" name="hc"
								value="41" id="hc_41"></td>
							<td class="2-td2-left-qysds1" nowrap>��ǰ��ȶ�ɵ�����˰���ڱ���ּ���</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='41_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>21 <input type="hidden" name="hc"
								value="21" id="hc_21"></td>
							<td class="2-td2-left-qysds1" nowrap>�������ֿ�Ӧ��˰���ö���</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='21_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>42 <input type="hidden" name="hc"
								value="42" id="hc_42"></td>
							<td class="2-td2-left-qysds1" nowrap>��ǰ���Ӧ��δ���ڱ����������˰��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='42_1' value='' size='13' tabindex='2'></td>
						</tr>
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center"><a style="cursor:hand" id="next"
						onclick="gotoNextTable()"><img name="spage" value="��һ�ű�"
						alt="��д��һ�ű�"
						onMouseOver="MM_swapImage('nexttable','','<%=static_contextpath%>images/xaiyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/xaiyiye1.jpg" id="nexttable"> </a>&nbsp;&nbsp;
					<input type="image" style="cursor:hand" tabIndex="-1"
						onClick="doReset();return false;" accesskey="u" value="���"
						alt="���ҳ���������Ϣ"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" id="qingchu" />
					&nbsp;&nbsp; <input type="image" style="cursor:hand" tabIndex="-1"
						onClick="doSave();return false;" accesskey="s" value="����" alt="����"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" id="baocun" />
					&nbsp;&nbsp; <input type="image" style="cursor:hand" tabIndex="-1"
						onClick="doCheck();return false;" accesskey="d" value="����У��"
						alt="���ڹ�ϵУ��"
						onMouseOver="MM_swapImage('jiaoyan','','../../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../../images/b-bdjyd1.jpg"
						id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doDelete();return false;" accesskey="x" value="ȫ��ɾ��"
						alt="ɾ�������������ݣ��Ҳ��ɻָ�"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" id="shanchu" />
					&nbsp;&nbsp; <input type="image" value="����" alt="���ص���ҵ����˰�걨��ҳ��"
						style="cursor:hand" tabIndex="-1" onClick="doExit();return false;"
						onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/fanhui1.jpg" id="fanhui" /></div>
					</TD>
				</TR>
			</TABLE>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
	<%@ include file="../../include/footernew.jsp"%>
</html:form>
</body>
</html>