<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jskffmxb.web.JskffmxbForm"%>
<%@ page import="java.util.HashMap"%>
<html>
<head>

<title>���������ѼӼƿ۳�����ϸ��</title>
<link href="../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript"
	src="../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/function.js"></script>
</head>

<script language="JavaScript">
	
	<%/*��ʼ��*/%>
	function doInit()
	{
	initNumText("nd1",8);
	initNumText("nd2",8);
	initNumText("nd3",8);
	initNumText("nd4",8);
	initNumText("nd5",8);
	initNumText("nd6",8);
	<%
	JskffmxbForm jskffmxbForm=(JskffmxbForm)request.getAttribute("jskffmxbForm");
	if (jskffmxbForm!=null && jskffmxbForm.getDataList().size()>0){
		for(int i=0;i<jskffmxbForm.getDataList().size();i++){
			HashMap map=(HashMap)jskffmxbForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String nd1=(String)map.get("nd1");
			String nd2=(String)map.get("nd2");
			String nd3=(String)map.get("nd3");
			String nd4=(String)map.get("nd4");
			String nd5=(String)map.get("nd5");
			String nd6=(String)map.get("nd6");
			
			%>
				obj = eval("document.getElementById('<%=hc%>_1')");
			obj.value = '<%=nd1%>';
				obj = eval("document.getElementById('<%=hc%>_2')");
			obj.value = '<%=nd2%>';
				obj = eval("document.getElementById('<%=hc%>_3')");
			obj.value = '<%=nd3%>';
				obj = eval("document.getElementById('<%=hc%>_4')");
			obj.value = '<%=nd4%>';
				obj = eval("document.getElementById('<%=hc%>_5')");
			obj.value = '<%=nd5%>';
				obj = eval("document.getElementById('<%=hc%>_6')");
			obj.value = '<%=nd6%>';
			
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
		   	for(var i=1; i < 10; i++){
					obj1 = eval("document.getElementById('" + i + "_1')");
				if(obj1.readOnly!=true)
					obj1.value = "";
					obj1 = eval("document.getElementById('" + i + "_2')");
				if(obj1.readOnly!=true)
					obj1.value = "";
					obj1 = eval("document.getElementById('" + i + "_3')");
				if(obj1.readOnly!=true)
					obj1.value = "";
					obj1 = eval("document.getElementById('" + i + "_4')");
				if(obj1.readOnly!=true)
					obj1.value = "";
					obj1 = eval("document.getElementById('" + i + "_5')");
				if(obj1.readOnly!=true)
					obj1.value = "";
					obj1 = eval("document.getElementById('" + i + "_6')");
				if(obj1.readOnly!=true)
					obj1.value = "";
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
	
	<%/*�ж���ȵķ���*/%>	
	function checknd(obj,i){
		if(isNum(obj,0,9999,null,4,0)){
			if(obj.value.length!=4){
				alert("���볤�Ȳ�Ϊ��λ!!!");
				return false;		
			}else{	
				var nd=parseInt(document.forms[0].sbrq.value.substring(0,4))-1;
				var ndx=parseInt(nd)-i;
				if(obj.value!=ndx){
					alert("������Ȳ��ԣ�����");
				    window.event.returnValue=false;
				    obj.focus();
				    obj.select();
				    return false;
				}
			}
		}
		else{
			return false;		
		}
	}

</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" style="margin: 0" onLoad="doInit()">

<%@ include file="../include/header.jsp"%>
<html:form action="/webapp/smsb/qysds/jskffmxbAction.do">
	<html:hidden property="actionType" />
	<html:hidden property="sbrq" />

	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">���������ѼӼƿ۳�����ϸ��</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="jskffmxbForm" property="sbrq" scope="request" filter="true" />
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
				name="jskffmxbForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="jskffmxbForm" property="nsrmc" scope="request" filter="true" />&nbsp;
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
					<div id="Layer2" style="position:static; ">
					<table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
						<tr>
							<!--<td class="2-td1-left" nowrap>�д�</td>
				   <td class="2-td1-left" nowrap>��Ŀ</td><!-->
							<td rowspan="2" class="2-td1-left" nowrap>�д�</td>
							<td rowspan="2" class="2-td1-left" nowrap>��Ŀ</td>

							<td class="2-td1-left" nowrap><input type='hidden' name='hc'
								id='hc_1' value='1'> <input type='text' name='nd1' id='1_1'
								value='' size='8' style="text-align:center" tabindex='2'
								onChange='checknd(this,4)'>���</td>
							<td class="2-td1-left" nowrap><input type='text' name='nd2'
								id='1_2' value='' size='8' style="text-align:center"
								tabindex='2'>���</td>
							<td class="2-td1-left" nowrap><input type='text' name='nd3'
								id='1_3' value='' size='8' style="text-align:center"
								tabindex='2'>���</td>
							<td class="2-td1-left" nowrap><input type='text' name='nd4'
								id='1_4' value='' size='8' style="text-align:center"
								tabindex='2'>���</td>
							<td class="2-td1-left" nowrap><input type='hidden' name='nd5'
								id='1_5' value=''>����</td>
							<td class="2-td1-center" nowrap><input type='hidden' name='nd6'
								id='1_6' value=''>�ϼ�</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>1</td>
							<td class="2-td2-left" nowrap>2</td>
							<td class="2-td2-left" nowrap>3</td>
							<td class="2-td2-left" nowrap>4</td>
							<td class="2-td2-left" nowrap>5</td>
							<td class="2-td2-center" nowrap>6</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>1</td>
							<td class="2-td2-left" nowrap>���꼼�������ѷ�����</td>
							<input type='hidden' name='hc' id='hc_2' value='2'>
							<td class="2-td2-left" nowrap><input type='text' name='nd1'
								id='2_1' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd2'
								id='2_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd3'
								id='2_3' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd4'
								id='2_4' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd5'
								id='2_5' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-center" nowrap><input type='text' name='nd6'
								id='2_6' value='*' size='1' readonly='true'
								class='text-noborder'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>2</td>
							<td class="2-td2-left" nowrap>���꼼�������ѷ�����</td>
							<input type='hidden' name='hc' id='hc_3' value='3'>
							<td class="2-td2-left" nowrap><input type='text' name='nd1'
								id='3_1' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd2'
								id='3_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd3'
								id='3_3' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd4'
								id='3_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd5'
								id='3_5' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center" nowrap><input type='text' name='nd6'
								id='3_6' value='*' size='1' readonly='true'
								class='text-noborder'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>3</td>
							<td class="2-td2-left" nowrap>��������</td>
							<input type='hidden' name='hc' id='hc_4' value='4'>
							<td class="2-td2-left" nowrap><input type='text' name='nd1'
								id='4_1' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd2'
								id='4_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd3'
								id='4_3' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd4'
								id='4_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd5'
								id='4_5' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center" nowrap><input type='text' name='nd6'
								id='4_6' value='*' size='1' readonly='true'
								class='text-noborder'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>4</td>
							<td class="2-td2-left" nowrap>����Ӧ�Ӽƿ۳���</td>
							<input type='hidden' name='hc' id='hc_5' value='5'>
							<td class="2-td2-left" nowrap><input type='text' name='nd1'
								id='5_1' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd2'
								id='5_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd3'
								id='5_3' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd4'
								id='5_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd5'
								id='5_5' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center" nowrap><input type='text' name='nd6'
								id='5_6' value='*' size='1' readonly='true'
								class='text-noborder'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>5</td>
							<td class="2-td2-left" nowrap>��ǰ��Ƚ�תδ�ֿ۶�</td>
							<input type='hidden' name='hc' id='hc_6' value='6'>
							<td class="2-td2-left" nowrap><input type='text' name='nd1'
								id='6_1' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd2'
								id='6_2' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd3'
								id='6_3' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd4'
								id='6_4' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd5'
								id='6_5' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-center" nowrap><input type='text' name='nd6'
								id='6_6' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>6</td>
							<td class="2-td2-left" nowrap>�ֿ�ǰӦ��˰���ö�</td>
							<input type='hidden' name='hc' id='hc_7' value='7'>
							<td class="2-td2-left" nowrap><input type='text' name='nd1'
								id='7_1' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd2'
								id='7_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd3'
								id='7_3' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd4'
								id='7_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd5'
								id='7_5' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center" nowrap><input type='text' name='nd6'
								id='7_6' value='*' size='1' readonly='true'
								class='text-noborder'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>7</td>
							<td class="2-td2-left" nowrap>����ɵֿ۵ļ��������ѼӼƿ۳���</td>
							<input type='hidden' name='hc' id='hc_8' value='8'>
							<td class="2-td2-left" nowrap><input type='text' name='nd1'
								id='8_1' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd2'
								id='8_2' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd3'
								id='8_3' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd4'
								id='8_4' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd5'
								id='8_5' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center" nowrap><input type='text' name='nd6'
								id='8_6' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>8</td>
							<td class="2-td2-left" nowrap>��ת�Ժ���ȵ�δ�ֿ۶�</td>
							<input type='hidden' name='hc' id='hc_9' value='9'>
							<td class="2-td2-left" nowrap><input type='text' name='nd1'
								id='9_1' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd2'
								id='9_2' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd3'
								id='9_3' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd4'
								id='9_4' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='nd5'
								id='9_5' value='' size='13' tabindex='2'></td>
							<td class="2-td2-center" nowrap><input type='text' name='nd6'
								id='9_6' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center"><input type="image" style="cursor:hand"
						tabIndex="-1" onClick="doReset();return false;" accesskey="u"
						value="���" alt="���ҳ���������Ϣ"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" 
						id="qingchu" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1" onClick="doSave();return false;"
						accesskey="s" value="����" alt="����"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" 
						id="baocun" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doCheck();return false;" accesskey="d"
						value="����У��" alt="���ڹ�ϵУ��"
						onMouseOver="MM_swapImage('jiaoyan','','../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../images/b-bdjyd1.jpg"
						 id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doDelete();return false;" accesskey="x"
						value="ȫ��ɾ��" alt="ɾ�������������ݣ��Ҳ��ɻָ�"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" 
						id="shanchu" /> &nbsp;&nbsp; <input type="image" value="����" alt="���ص���ҵ����˰�걨��ҳ��"
						style="cursor:hand" tabIndex="-1" onClick="doExit();return false;"
						onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/fanhui1.jpg" 
						id="fanhui" /></div>
					</TD>
				</TR>
			</TABLE>
			</td>
		</tr>
	</table>


	<%@ include file="../include/footernew.jsp"%>
</html:form>

</body>
</html>


