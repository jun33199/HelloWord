<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.mssdmxb.web.MssdmxbForm"%>
<%@ page import="java.util.HashMap"%>

<html>
<head>
<title>��˰���ü�����˰��ϸ��</title>
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
	<%/*����ҳ������*/%>
	initNumText("je",64);
	<%
	MssdmxbForm mssdmxbForm=(MssdmxbForm)request.getAttribute("mssdmxbForm");
	if (mssdmxbForm!=null && mssdmxbForm.getDataList().size()>0){
		for(int i=0;i<mssdmxbForm.getDataList().size();i++){
			HashMap map=(HashMap)mssdmxbForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String je=(String)map.get("je");
			%>
			obj = eval("document.getElementById('<%=hc%>_1')");
			obj.value = '<%=je%>';	
			<% 
		}
	}
	String jmlx=mssdmxbForm.getJmlx();
	%>
	pb('<%=jmlx%>');
	InitTabindex(document.forms[0]);
	}
		
	<%/*�����ı���*/%>
	function pb(jmlx){
			
			<%/*û���Ż�*/%>
			if(jmlx=='<%=CodeConstant.JMLXNO%>'||jmlx=='null'){
				for(var j=10;j<62;j++){
					obj = eval("document.getElementById('"+j+"_1')");
					if(obj.readOnly!=true){
						obj.readOnly=true;
						obj.className='text-gray';			
					}
				}
	 		}
	 		
			<%/*�����ҵ�����ɵ�·*/%>
			if(jmlx=='<%=CodeConstant.JMLX9020%>'||jmlx=='<%=CodeConstant.JMLX9030%>'){
				for(var j=10;j<62;j++){
					obj = eval("document.getElementById('"+j+"_1')");
					if(obj.readOnly!=true){
						obj.readOnly=true;
						obj.className='text-gray';			
					}
				}
			
				obj = eval("document.getElementById('10_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('11_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('13_1')");
				obj.readOnly=false;
				obj.className="";
	 		}
	 		
	 		<%/*������ҵ*/%>
			if(jmlx=='<%=CodeConstant.JMLX9090%>'){			
				for(var j=10;j<62;j++){
					obj = eval("document.getElementById('"+j+"_1')");
					if(obj.readOnly!=true){
						obj.readOnly=true;	
						obj.className='text-gray';				
					}
				}
			
				obj = eval("document.getElementById('10_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('42_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('43_1')");
				obj.readOnly=false;
				obj.className="";
	 		}
	 		
	 		<%/*�ͷ���ҵ*/%>	 		
			if(jmlx=='<%=CodeConstant.JMLX9070%>'){
				for(var j=10;j<62;j++){
					obj = eval("document.getElementById('"+j+"_1')");
					if(obj.readOnly!=true){								
						obj.readOnly=true;
						obj.className='text-gray';			
					}		
				}
		 		
				obj = eval("document.getElementById('10_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('42_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('44_1')");
				obj.readOnly=false;
				obj.className="";
	 		}
	 		
			<%/*���¼�����ҵ*/%>
			if(jmlx=='<%=CodeConstant.JMLX9010%>'){
				for(var j=10;j<62;j++){
					obj = eval("document.getElementById('"+j+"_1')");
					if(obj.readOnly!=true){
						obj.readOnly=true;
						obj.className='text-gray';	
					}
				}
			
				obj = eval("document.getElementById('10_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('11_1')");
				obj.readOnly=false;
				obj.className="";
				
				obj = eval("document.getElementById('12_1')");
				obj.readOnly=false;
				obj.className="";
	 		}
	 		
	 		<%/*�޼���*/%>
			if(jmlx=='<%=CodeConstant.JMLXNO%>'){
				for(var j=10;j<62;j++){
				obj = eval("document.getElementById('"+j+"_1')");
					if(obj.readOnly!=true){
						obj.readOnly=true;
						obj.className='text-gray';
					}
				}
			}
			
			<%/*������������*/%>
			if(jmlx=='<%=CodeConstant.JMLXOTHER%>'){
				obj = eval("document.getElementById('12_1')");
				obj.readOnly=true;
				obj.className='text-gray';
				obj = eval("document.getElementById('13_1')");
				obj.readOnly=true;
				obj.className='text-gray';
				obj = eval("document.getElementById('43_1')");
				obj.readOnly=true;
				obj.className='text-gray';
				obj = eval("document.getElementById('44_1')");
				obj.readOnly=true;
				obj.className='text-gray';
	 		}
	 }
	 	
	<%/*����*/%>
	function doSave()
	{
		doSubmitForm('Save');
	}
	
	function doCheck()
	{
		doSubmitForm('Check');
	}
	
	<%/*���*/%>
	function doReset()
	{
	    if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
	    {
		   	for(var i=1; i < 65; i++){
				obj1 = eval("document.getElementById('" + i + "_1')");
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
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" style="margin: 0" onLoad="doInit()">
<%@ include file="../include/header.jsp"%>

<html:form action="/webapp/smsb/qysds/mssdmxbAction.do">
	<html:hidden property="actionType" />
	<html:hidden property="jmlx" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
		
	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">��˰���ü�����˰��ϸ��</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="mssdmxbForm" property="sbrq" scope="request" filter="true" />
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
				name="mssdmxbForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="mssdmxbForm" property="nsrmc" scope="request" filter="true" />&nbsp;
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
							<td class="2-td1-left" nowrap>�д�</td>
							<td class="2-td1-left" nowrap>��Ŀ</td>
							<td class="2-td1-center" nowrap>���</td>
							<td class="2-td1-left" nowrap>�д�</td>
							<td class="2-td1-left" nowrap>��Ŀ</td>
							<td class="2-td1-center" nowrap>���</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>1</td>
							<td class="2-td2-left-qysds1" nowrap>һ����˰���ã�2+3+��9��</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_1' value='1'> <input type='text' name='je' id='1_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>34</td>
							<td class="2-td2-left-qysds2" nowrap>(��)�Ľ��������⣨35+36+��41��</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_34' value='34'> <input type='text' name='je' id='34_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>2</td>
							<td class="2-td2-left-qysds2" nowrap>��ծ��Ϣ����</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_2' value='2'> <input type='text' name='je' id='2_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>35</td>
							<td class="2-td2-left-qysds2" nowrap>���У�1������������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_35' value='35'> <input type='text' name='je' id='35_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>3</td>
							<td class="2-td2-left-qysds2" nowrap>��˰�Ĳ�������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_3' value='3'> <input type='text' name='je' id='3_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>36</td>
							<td class="2-td2-left-qysds3" nowrap>2����ӯ��ҽ�ƻ���</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_36' value='36'> <input type='text' name='je' id='36_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>4</td>
							<td class="2-td2-left-qysds2" nowrap>
							��˰������Ԥ�����Ļ����շѻ򸽼�</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_4' value='4'> <input type='text' name='je' id='4_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>37</td>
							<td class="2-td2-left-qysds3" nowrap>3���������̨</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_37' value='37'> <input type='text' name='je' id='37_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>5</td>
							<td class="2-td2-left-qysds2" nowrap>���ڲ�˰��Ͷ������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_5' value='5'> <input type='text' name='je' id='5_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>38</td>
							<td class="2-td2-left-qysds3" nowrap>4��ѧУ��ѵ������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_38' value='38'> <input type='text' name='je' id='38_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>6</td>
							<td class="2-td2-left-qysds2" nowrap>��˰�ļ���ת������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_6' value='6'> <input type='text' name='je' id='6_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>39</td>
							<td class="2-td2-left-qysds3" nowrap>5����У���ڼ���</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_39' value='39'> <input type='text' name='je' id='39_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>7</td>
							<td class="2-td2-left-qysds2" nowrap>��˰������"����"����</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_7' value='7'> <input type='text' name='je' id='7_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>40</td>
							<td class="2-td2-left-qysds3" nowrap>6��29����˻���˰
							</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_40' value='40'> <input type='text' name='je' id='40_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>8</td>
							<td class="2-td2-left-qysds2" nowrap>
							��ֲҵ����ֳҵ��ũ�ֲ�Ʒ���ӹ�����</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_8' value='8'> <input type='text' name='je' id='8_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>41</td>
							<td class="2-td2-left-qysds3" nowrap>7������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_41' value='41'> <input type='text' name='je' id='41_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>9</td>
							<td class="2-td2-left-qysds2" nowrap>������˰����</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_9' value='9'> <input type='text' name='je' id='9_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>42</td>
							<td class="2-td2-left-qysds2" nowrap>(��)�ٽ���ҵ���⣨43+44+��50��</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_42' value='42'> <input type='text' name='je' id='42_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>10</td>
							<td class="2-td2-left-qysds1" nowrap>
							��������˰��11+17+18+26+31+34+42+51+52+53+54+60+61��</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_10' value='10'> <input type='text' name='je' id='10_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>43</td>
							<td class="2-td2-left-qysds2" nowrap>���У�1������������ҵ</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_43' value='43'> <input type='text' name='je' id='43_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>11</td>
							<td class="2-td2-left-qysds2" nowrap>(һ)���¼�����ҵ������������12+13+��16��</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_11' value='11'> <input type='text' name='je' id='11_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>44</td>
							<td class="2-td2-left-qysds3" nowrap>2���ͷ���ҵ</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_44' value='44'> <input type='text' name='je' id='44_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>12</td>
							<td class="2-td2-left-qysds2" nowrap>���У�1�����¼���������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_12' value='12'> <input type='text' name='je' id='12_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>45</td>
							<td class="2-td2-left-qysds3" nowrap>
							3���¸�ʧҵ��Ա�پ�ҵ</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_45' value='45'> <input type='text' name='je' id='45_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>13</td>
							<td class="2-td2-left-qysds3" nowrap>2����������ɵ�·</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_13' value='13'> <input type='text' name='je' id='13_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>46</td>
							<td class="2-td2-left-qysds3" nowrap>4�����������ҵ</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_46' value='46'> <input type='text' name='je' id='46_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>14</td>
							<td class="2-td2-left-qysds3" nowrap>3����ӯ�����л���</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_14' value='14'> <input type='text' name='je' id='14_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>47</td>
							<td class="2-td2-left-qysds3" nowrap>5����ת�ɲ���ҵ</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_47' value='47'> <input type='text' name='je' id='47_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>15</td>
							<td class="2-td2-left-qysds3" nowrap>
							4�����л���ת��Ϊ��ҵ5����˰</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_15' value='15'> <input type='text' name='je' id='15_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>48</td>
							<td class="2-td2-left-qysds3" nowrap>
							6��������ҵ����������ҵ</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_48' value='48'> <input type='text' name='je' id='48_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>16</td>
							<td class="2-td2-left-qysds3" nowrap>5������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_16' value='16'> <input type='text' name='je' id='16_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>49</td>
							<td class="2-td2-left-qysds3" nowrap>7������������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_49' value='49'> <input type='text' name='je' id='49_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>17</td>
							<td class="2-td2-left-qysds2" nowrap>(��)������ʩ�������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_17' value='17'> <input type='text' name='je' id='17_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>50</td>
							<td class="2-td2-left-qysds3" nowrap>8 ����������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_50' value='50'> <input type='text' name='je' id='50_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>18</td>
							<td class="2-td2-left-qysds2" nowrap>(��)���������Żݼ��⣨19+20+��25��</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_18' value='18'> <input type='text' name='je' id='18_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>51</td>
							<td class="2-td2-left-qysds2" nowrap>(��)��Դ�ۺ����ü���</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_51' value='51'> <input type='text' name='je' id='51_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>19</td>
							<td class="2-td2-left-qysds2" nowrap>���У�1�������󿪷�����</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_19' value='19'> <input type='text' name='je' id='19_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>52</td>
							<td class="2-td2-left-qysds2" nowrap>(��)�͸��ͽ̼���</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_52' value='52'> <input type='text' name='je' id='52_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>20</td>
							<td class="2-td2-left-qysds3" nowrap>2����������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_20' value='20'> <input type='text' name='je' id='20_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>53</td>
							<td class="2-td2-left-qysds2" nowrap>(ʮ)������ҵ����</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_53' value='53'> <input type='text' name='je' id='53_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>21</td>
							<td class="2-td2-left-qysds3" nowrap>
							3�������Ϲ�ҵ���ؼ���</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_21' value='21'> <input type='text' name='je' id='21_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>54</td>
							<td class="2-td2-left-qysds2" nowrap>(ʮһ)��������⣨55+56+��59��</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_54' value='54'> <input type='text' name='je' id='54_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>22</td>
							<td class="2-td2-left-qysds3" nowrap>4�����������������
							</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_22' value='22'> <input type='text' name='je' id='22_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>55</td>
							<td class="2-td2-left-qysds2" nowrap>���У�1��ũ����</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_55' value='55'> <input type='text' name='je' id='55_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>23</td>
							<td class="2-td2-left-qysds3" nowrap>5�����ٱ������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_23' value='23'> <input type='text' name='je' id='23_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>56</td>
							<td class="2-td2-left-qysds3" nowrap>2�����ж�������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_56' value='56'> <input type='text' name='je' id='56_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>24</td>
							<td class="2-td2-left-qysds3" nowrap>
							6�������������ĸ��¼�����ҵ</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_24' value='24'> <input type='text' name='je' id='24_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>57</td>
							<td class="2-td2-left-qysds3" nowrap>3������ʽ����</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_57' value='57'> <input type='text' name='je' id='57_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>25</td>
							<td class="2-td2-left-qysds3" nowrap>7������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_25' value='25'> <input type='text' name='je' id='25_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>58</td>
							<td class="2-td2-left-qysds3" nowrap>4�����ʽ����</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_58' value='58'> <input type='text' name='je' id='58_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>26</td>
							<td class="2-td2-left-qysds2" nowrap>(��)ũҵ���⣨27+28+29+30��</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_26' value='26'> <input type='text' name='je' id='26_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>59</td>
							<td class="2-td2-left-qysds3" nowrap>5������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_59' value='59'> <input type='text' name='je' id='59_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>27</td>
							<td class="2-td2-left-qysds3" nowrap>
							1��ũҵ��ҵ����ͷ��ҵ��˰</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_27' value='27'> <input type='text' name='je' id='27_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>60</td>
							<td class="2-td2-left-qysds2" nowrap>(ʮ��)��Ȼ�ֺ�����</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_60' value='60'> <input type='text' name='je' id='60_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>28</td>
							<td class="2-td2-left-qysds3" nowrap>
							2��ũҵ��ǰ�����С��������ҵ</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_28' value='28'> <input type='text' name='je' id='28_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>61</td>
							<td class="2-td2-left-qysds2" nowrap>(ʮ��)��������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_61' value='61'> <input type='text' name='je' id='61_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>29</td>
							<td class="2-td2-left-qysds3" nowrap>3��Զ����ҵ��˰</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_29' value='29'> <input type='text' name='je' id='29_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap>62</td>
							<td class="2-td2-left-qysds1" nowrap>������������˰�63+64��</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_62' value='62'> <input type='text' name='je' id='62_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>30</td>
							<td class="2-td2-left-qysds3" nowrap>4������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_30' value='30'> <input type='text' name='je' id='30_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>63</td>
							<td class="2-td2-left-qysds2" nowrap>��������豸Ͷ�ʵ�������˰</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_63' value='63'> <input type='text' name='je' id='63_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>31</td>
							<td class="2-td2-left-qysds2" nowrap>(��)������ҵ���⣨32+33��</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_31' value='31'> <input type='text' name='je' id='31_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>64</td>
							<td class="2-td2-left-qysds2" nowrap>����</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_64' value='64'> <input type='text' name='je' id='64_1'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>32</td>
							<td class="2-td2-left-qysds2" nowrap>���У�1���°�ķ�������ҵ</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_32' value='32'> <input type='text' name='je' id='32_1'
								value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>65</td>
							<td class="2-td2-left-qysds1" nowrap>�ġ������������Ϻ��ֶ�������˰��</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_65' value='65'> <input type='text' name='je' id='65_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'tabindex='2'></td>
							
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>33</td>
							<td class="2-td2-left-qysds3" nowrap>2����������</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='hc'
								id='hc_33' value='33'> <input type='text' name='je' id='33_1'
								value='' size='13' tabindex='1'></td>
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
					
					<input type="image" style="cursor:hand"
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
						onClick="doCheck();return false;" accesskey="d" value="����У��"
						alt="���ڹ�ϵУ��"
						onMouseOver="MM_swapImage('jiaoyan','','../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../images/b-bdjyd1.jpg"
						 id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doDelete();return false;" accesskey="x" value="ȫ��ɾ��"
						alt="ɾ�������������ݣ��Ҳ��ɻָ�"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" 
						id="shanchu" /> &nbsp;&nbsp; <input type="image" value="����"
						alt="���ص���ҵ����˰�걨��ҳ��" style="cursor:hand" tabIndex="-1"
						onClick="doExit();return false;"
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


