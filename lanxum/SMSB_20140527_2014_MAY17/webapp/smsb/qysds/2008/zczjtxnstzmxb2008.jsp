<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.zczjtxnstzmxb.web.ZczjtxnstzmxbForm2008"%>
<%@ page import="java.util.HashMap"%>


<html>
<head>
<title>�ʲ��۾ɡ�̯����˰������ϸ��</title>
</head>
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
	src="../../../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/smsb_common.js"></script>
<script language="JavaScript" src="../../../js/DTable.js"></script>
<script language=JavaScript src="../../../js/reader.js"></script>
<script language=JavaScript src="../../../js/compute.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/qysdsnew.js"></script>

<script language="JavaScript">
	
	<%/*��ʼ��*/%>
	function doInit()
	{
	 //��ʼ���ı���onChange�¼�����
		initNumText("zcyzzzje",18);
		initNumText("zcyzjsjc",18);
		initNumText("zjtxnxkj",18);
		initNumText("zjtxnxss",18);
		initNumText("bqzjtxekj",18);
		initNumText("bqzjtxess",18);		
		initNumText("nstze",18);		

		
	<%
	ZczjtxnstzmxbForm2008 zcmxbForm=(ZczjtxnstzmxbForm2008)request.getAttribute("zczjtxnstzmxbForm2008");
	if (zcmxbForm!=null && zcmxbForm.getDataList().size()>0){
		for(int i=0;i<zcmxbForm.getDataList().size();i++){
			HashMap map=(HashMap)zcmxbForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String zcyzzzje=(String)map.get("zcyzzzje");
			String zcyzjsjc=(String)map.get("zcyzjsjc");
			String zjtxnxkj=(String)map.get("zjtxnxkj");
			String zjtxnxss=(String)map.get("zjtxnxss");
			String bqzjtxekj=(String)map.get("bqzjtxekj");
			String bqzjtxess=(String)map.get("bqzjtxess");			
			String nstze=(String)map.get("nstze");

			
			%>
			obj=eval("document.getElementById('<%=hc%>_1')");
			obj.value='<%=zcyzzzje%>';
			
			obj=eval("document.getElementById('<%=hc%>_2')");
			obj.value='<%=zcyzjsjc%>';
			
			obj=eval("document.getElementById('<%=hc%>_3')");
			obj.value='<%=zjtxnxkj%>';
			
			obj=eval("document.getElementById('<%=hc%>_4')");
			obj.value='<%=zjtxnxss%>';
			
			obj=eval("document.getElementById('<%=hc%>_5')");
			obj.value='<%=bqzjtxekj%>';
			
			obj=eval("document.getElementById('<%=hc%>_6')");
			obj.value='<%=bqzjtxess%>';
			
			obj=eval("document.getElementById('<%=hc%>_7')");
			obj.value='<%=nstze%>';						
			
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
	
	<%/*���*/%>
	function doCheck()
	{
		doSubmitForm('Check');
	}
		
	<%/*���*/%>
	function doReset()
	{
	    if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
	    {
		   	for(var i=1; i<=18; i++){
				obj = eval("document.getElementById('"+i+"_1')");
				     if (obj.readOnly!=true)
					 obj.value = "";
				obj = eval("document.getElementById('"+i+"_2')");
				     if (obj.readOnly!=true)
					 obj.value = "";
			    obj = eval("document.getElementById('"+i+"_3')");
				     if (obj.readOnly!=true)
					 obj.value = "";
				obj = eval("document.getElementById('"+i+"_4')");
				     if (obj.readOnly!=true)
					 obj.value = "";
				obj = eval("document.getElementById('"+i+"_5')");
				     if (obj.readOnly!=true)
					 obj.value = "";
				obj = eval("document.getElementById('"+i+"_6')");
				     if (obj.readOnly!=true)
					 obj.value = "";
				
				obj = eval("document.getElementById('"+i+"_7')");
				     if (obj.readOnly!=true)
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
<%@ include file="../../include/header.jsp"%>
<br>

<html:form action="/webapp/smsb/qysds/2008/zczjtxnstzmxbAction2008">
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">�ʲ��۾ɡ�̯����˰������ϸ��</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="zczjtxnstzmxbForm2008" property="sbrq" scope="request" filter="true" />
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
				name="zczjtxnstzmxbForm2008" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="zczjtxnstzmxbForm2008" property="nsrmc" scope="request" filter="true" />&nbsp;
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
					<div id="Layer2" style="position:static; overflow: auto; ">
					<table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
						<tr>
							<td rowspan="3" class="2-td1-left">��&nbsp;&nbsp;��</td>
							<td rowspan="3" class="2-td1-left">�ʲ����</td>
							<td colspan="2" class="2-td1-left">�ʲ�ԭֵ</td>
							<td colspan="2" class="2-td1-left">�۾ɡ�̯������</td>
							<td colspan="2" class="2-td1-left">�����۾ɡ�̯����</td>
							<td rowspan="2" class="2-td1-center">��˰������</td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">���ؽ��</td>
							<td class="2-td2-left" align="center">��˰����</td>
							<td class="2-td2-left" align="center">���</td>
							<td class="2-td2-left" align="center">˰��</td>
							<td class="2-td2-left" align="center">���</td>
							<td class="2-td2-left" align="center">˰��</td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">1</td>
							<td class="2-td2-left" align="center">2</td>
							<td class="2-td2-left" align="center">3</td>
							<td class="2-td2-left" align="center">4</td>
							<td class="2-td2-left" align="center">5</td>
							<td class="2-td2-left" align="center">6</td>
							<td class="2-td2-center" align="center">7</td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">1<input type='hidden'
								name='hc' id='hc_1' value='1'></td>
							<td class="2-td2-left-qysds1" align="center" nowrap>һ���̶��ʲ�</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='1_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='1_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='1_3' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='1_4' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='1_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='1_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='1_7' value='' size='9' tabindex='2'></td>

						</tr>
						<tr>
							<td class="2-td2-left" align="center">2<input type='hidden'
								name='hc' id='hc_2' value='2'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap> 1.���ݽ�����</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='2_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='2_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='2_3' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='2_4' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='2_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='2_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='2_7' value='' size='9' tabindex='2'></td>

						</tr>
						<tr>
							<td class="2-td2-left" align="center">3<input type='hidden'
								name='hc' id='hc_3' value='3'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>2.�ɻ����𳵡��ִ�����������е�����������豸</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='3_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='3_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='3_3' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='3_4' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='3_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='3_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='3_7' value='' size='9' tabindex='2'></td>

						</tr>
						<tr>
							<td class="2-td2-left" align="center">4<input type='hidden'
								name='hc' id='hc_4' value='4'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>3.��������Ӫ�йص����߹��߼Ҿ�</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='4_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='4_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='4_3' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='4_4' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='4_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='4_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='4_7' value='' size='9' tabindex='2'></td>

						</tr>
						<tr>
							<td class="2-td2-left" align="center">5<input type='hidden'
								name='hc' id='hc_5' value='5'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap> 4.�ɻ����𳵡��ִ���������乤��</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='5_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='5_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name=zjtxnxkj ' id='5_3' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='5_4' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='5_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='5_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='5_7' value='' size='9' tabindex='2'></td>

						</tr>
						<tr>
							<td class="2-td2-left" align="center">6<input type='hidden'
								name='hc' id='hc_6' value='6'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>5.�����豸</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='6_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='6_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='6_3' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='6_4' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='6_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='6_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='6_7' value='' size='9' tabindex='2'></td>

						</tr>
						<tr>
							<td class="2-td2-left" align="center">7<input type='hidden'
								name='hc' id='hc_7' value='7'></td>
							<td class="2-td2-left-qysds1" align="center" nowrap>���������������ʲ�</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='7_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='7_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='7_3' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='7_4' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='7_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='7_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='7_7' value='' size='9' tabindex='2'></td>

						</tr>
						<tr>
							<td class="2-td2-left" align="center">8<input type='hidden'
								name='hc' id='hc_8' value='8'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>1.��ľ��</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='8_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='8_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='8_3' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='8_4' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='8_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='8_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='8_7' value='' size='9' tabindex='2'></td>

						</tr>
						<tr>
							<td class="2-td2-left" align="center">9<input type='hidden'
								name='hc' id='hc_9' value='9'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>2.����</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='9_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='9_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='9_3' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='9_4' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='9_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='9_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='9_7' value='' size='9' tabindex='2'></td>

						</tr>
						<tr>
							<td class="2-td2-left" align="center">10<input type='hidden'
								name='hc' id='hc_10' value='10'></td>
							<td class="2-td2-left-qysds1" align="center" nowrap>�������ڴ�̯����</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='10_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='10_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='10_3' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='10_4' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='10_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='10_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='10_7' value='' size='9' tabindex='2'></td>

						</tr>
						<tr>
							<td class="2-td2-left" align="center">11<input type='hidden'
								name='hc' id='hc_11' value='11'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>1.�������ȡ�۾ɵĹ̶��ʲ��ĸĽ�֧��</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='11_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='11_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='11_3' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='11_4' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='11_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='11_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='11_7' value='' size='9' tabindex='2'></td>

						</tr>
						<tr>
							<td class="2-td2-left" align="center">12<input type='hidden'
								name='hc' id='hc_12' value='12'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>2.����̶��ʲ��ĵĸĽ�֧��</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='12_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='12_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='12_3' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='12_4' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='12_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='12_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='12_7' value='' size='9' tabindex='2'></td>


						</tr>
						<tr>
							<td class="2-td2-left" align="center">13<input type='hidden'
								name='hc' id='hc_13' value='13'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>3.�̶��ʲ�������֧��</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='13_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='13_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='13_3' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='13_4' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='13_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='13_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='13_7' value='' size='9' tabindex='2'></td>


						</tr>
						<tr>
							<td class="2-td2-left" align="center">14<input type='hidden'
								name='hc' id='hc_14' value='14'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>4.�������ڴ�̯����</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='14_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='14_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='14_3' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='14_4' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='14_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='14_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='14_7' value='' size='9' tabindex='2'></td>


						</tr>
						<tr>
							<td class="2-td2-left" align="center">15<input type='hidden'
								name='hc' id='hc_15' value='15'></td>
							<td class="2-td2-left-qysds1" align="center" nowrap>�ġ������ʲ�</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='15_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='15_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='15_3' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='15_4' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='15_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='15_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='15_7' value='' size='9' tabindex='2'></td>


						</tr>
						<tr>
							<td class="2-td2-left" align="center">16<input type='hidden'
								name='hc' id='hc_16' value='16'></td>
							<td class="2-td2-left-qysds1" align="center" nowrap>�塢������̽Ͷ��</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='16_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='16_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='16_3' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='16_4' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='16_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='16_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='16_7' value='' size='9' tabindex='2'></td>


						</tr>
						<tr>
							<td class="2-td2-left" align="center">17<input type='hidden'
								name='hc' id='hc_17' value='17'></td>
							<td class="2-td2-left-qysds1" align="center" nowrap>������������Ͷ��</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='17_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='17_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='17_3' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='17_4' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='17_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='17_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='17_7' value='' size='9' tabindex='2'></td>


						</tr>
						<tr>
							<td class="2-td2-left" align="center">18<input type='hidden'
								name='hc' id='hc_18' value='18'></td>
							<td class="2-td2-left-qysds1" align="center" nowrap>�ϼ�</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzzzje' id='18_1' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcyzjsjc' id='18_2' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxkj' id='18_3' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxnxss' id='18_4' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxekj' id='18_5' value='' size='9' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='bqzjtxess' id='18_6' value='' size='9' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='nstze' id='18_7' value='' size='9' tabindex='2'></td>


						</tr>
						
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center"><a style="cursor:hand" id="previous"
				onclick="gotoPreviousTable()"><img name="spage" value="��һ�ű�"
				alt="��д��һ�ű�"
				onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				src="<%=static_contextpath%>images/shangyiye1.jpg" id="previoustable">
			</a>&nbsp;&nbsp;
			
			 <a style="cursor:hand" id="next"
				onclick="gotoNextTable()"><img name="xpage" value="��һ�ű�"
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
