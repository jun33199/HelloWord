<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.cbmxbjrqy.web.CbmxbjrqyForm"%>
<%@ page import="java.util.HashMap"%>

<html>
<head>
<title>���ڱ�����ҵӪҵ�ɱ���ϸ��</title>
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
<%/*����*/%>
	function doInit()
	{
	      //��ʼ���ı���onChange�¼�����
		initNumText("je",42);
	      
	<%
	CbmxbjrqyForm cbmxbjrqyForm=(CbmxbjrqyForm)request.getAttribute("cbmxbjrqyForm");
	if (cbmxbjrqyForm!=null && cbmxbjrqyForm.getDataList().size()>0){
		for(int i=0;i<cbmxbjrqyForm.getDataList().size();i++){
			HashMap map=(HashMap)cbmxbjrqyForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String je=(String)map.get("je");
			%>
			obj = eval("document.getElementById('<%=hc%>_1')");
			obj.value = '<%=je%>';
			
			<% 
		}
	}
	%>
	}
	function doSave()
	{
		doSubmitForm('Save');
	}
	function doChecked()
	{
	   doSubmitForm('Check');
	  
	}
	
	<%/*���*/%>
	function doReset()
	{
		if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
	    {
	      <%
		   	for(int i=1; i<=42; i++){
		   	%>
				obj = eval("document.getElementById('<%=i%>_1')");
				obj.value = "";
				<%
		   	}
		   	%>
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
<%@ include file="../include/header.jsp"%>
<br>
<html:form action="/webapp/smsb/qysds/cbmxbjrqyAction.do" method="post">

	<html:hidden property="actionType" />
    <html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">���ڱ�����ҵӪҵ�ɱ���ϸ��</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="cbmxbjrqyForm" property="sbrq" scope="request" filter="true" />
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
				name="cbmxbjrqyForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="cbmxbjrqyForm" property="nsrmc" scope="request" filter="true" />&nbsp;
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
							<td class="2-td1-center" nowrap>���</td>
							<td class="2-td1-left" nowrap>�д�</td>
							<td class="2-td1-left" nowrap>��Ŀ</td>
							<td class="2-td1-center" nowrap>���</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>1</td>
							<td class="2-td2-left-qysds1" nowrap>һ������ҵ��ɱ��ϼƣ�2+3+��+24-25��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='1_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="1" /></td>
							<td class="2-td2-left" nowrap>22</td>
							<td class="2-td2-left-qysds2" nowrap>��ȡ���ձ��ϻ���</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='22_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="22" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>2</td>
							<td class="2-td2-left-qysds2" nowrap>��Ϣ֧��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='2_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="2" /></td>
							<td class="2-td2-left" nowrap>23</td>
							<td class="2-td2-left-qysds2" nowrap>�����ʧ</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='23_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="23" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>3</td>
							<td class="2-td2-left-qysds2" nowrap>������ҵ����֧��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='3_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="3" /></td>
							<td class="2-td2-left" nowrap>24</td>
							<td class="2-td2-left-qysds2" nowrap>����ҵ��֧��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='24_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="24" /></td>
						</tr>

						<tr>
							<td class="2-td2-left" nowrap>4</td>
							<td class="2-td2-left-qysds2" nowrap>�м�ҵ��֧��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='4_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="4" /></td>
							<td class="2-td2-left" nowrap>25</td>
							<td class="2-td2-left-qysds2" nowrap>����̯�طֱ����</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='25_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="25" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>5</td>
							<td class="2-td2-left-qysds2" nowrap>������֧��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='5_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="5" /></td>
							<td class="2-td2-left" nowrap>26</td>
							<td class="2-td2-left-qysds1" nowrap>��������֧����27+28+35+38��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='26_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="26" /></td>

						</tr>
						<tr>
							<td class="2-td2-left" nowrap>6</td>
							<td class="2-td2-left-qysds2" nowrap>�����ع�֤ȯ֧��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='6_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="6" /></td>
							<td class="2-td2-left" nowrap>27</td>
							<td class="2-td2-left-qysds2" nowrap>1����ͬ���۳ɱ�</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='27_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="27" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>7</td>
							<td class="2-td2-left-qysds2" nowrap>����ҽ�Ƹ���</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='7_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="7" /></td>
							<td class="2-td2-left" nowrap>28</td>
							<td class="2-td2-left-qysds2" nowrap>2��Ӫҵ��֧����29+30+��+34)</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='28_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="28" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>8</td>
							<td class="2-td2-left-qysds2" nowrap>���ڸ���</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='8_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="8" /></td>
							<td class="2-td2-left" nowrap>29</td>
							<td class="2-td2-left-qysds3" nowrap>�̶��ʲ��̿�</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='29_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="29" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>9</td>
							<td class="2-td2-left-qysds2" nowrap>������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='9_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="9" /></td>
							<td class="2-td2-left" nowrap>30</td>
							<td class="2-td2-left-qysds3" nowrap>���ù̶��ʲ�����ʧ</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='30_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="30" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>10</td>
							<td class="2-td2-left-qysds2" nowrap>�˱���</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='10_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="10" /></td>
							<td class="2-td2-left" nowrap>31</td>
							<td class="2-td2-left-qysds3" nowrap>���������ʲ���ʧ</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='31_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="31" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>11</td>
							<td class="2-td2-left-qysds2" nowrap>���֧��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='11_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="11" /></td>
							<td class="2-td2-left" nowrap>32</td>
							<td class="2-td2-left-qysds3" nowrap>����֧��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='32_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="32" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>12</td>
							<td class="2-td2-left-qysds2" nowrap>�ֳ�����</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='12_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="12" /></td>
							<td class="2-td2-left" nowrap>33</td>
							<td class="2-td2-left-qysds3" nowrap>�ǳ���ʧ</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='33_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="33" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>13</td>
							<td class="2-td2-left-qysds2" nowrap>�ֱ����֧��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='13_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="13" /></td>
							<td class="2-td2-left" nowrap>34</td>
							<td class="2-td2-left-qysds3" nowrap>����</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='34_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="34" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>14</td>
							<td class="2-td2-left-qysds2" nowrap>�ֱ�����֧��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='14_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="14" /></td>
							<td class="2-td2-left" nowrap>35</td>
							<td class="2-td2-left-qysds2" nowrap>3��˰����Ӧȷ�ϵ������ɱ�����(36+37)</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='35_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="35" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>15</td>
							<td class="2-td2-left-qysds2" nowrap>Ӷ��֧��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='15_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="15" /></td>
							<td class="2-td2-left" nowrap>36</td>
							<td class="2-td2-left-qysds3" nowrap>˰����ȷ�ϵ��ʲ�������ֵ</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='36_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="36" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>16</td>
							<td class="2-td2-left-qysds2" nowrap>��������֧��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='16_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="16" /></td>
							<td class="2-td2-left" nowrap>37</td>
							<td class="2-td2-left-qysds3" nowrap>����</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='37_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="37" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>17</td>
							<td class="2-td2-left-qysds2" nowrap>δ��������׼������ת��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='17_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="17" /></td>
							<td class="2-td2-left" nowrap>38</td>
							<td class="2-td2-left-qysds2" nowrap>4������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='38_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="38" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>18</td>
							<td class="2-td2-left-qysds2" nowrap>��������׼������ת��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='18_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="18" /></td>
							<td class="2-td2-left" nowrap>39</td>
							<td class="2-td2-left-qysds1" nowrap>�����ڼ���úϼƣ�40+41+42��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='39_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="39" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>19</td>
							<td class="2-td2-left-qysds2" nowrap>δ�����׼������ת��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='19_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="19" /></td>
							<td class="2-td2-left" nowrap>40</td>
							<td class="2-td2-left-qysds2" nowrap>Ӫҵ����</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='40_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="40" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>20</td>
							<td class="2-td2-left-qysds2" nowrap>��������׼������ת��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='20_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="20" /></td>
							<td class="2-td2-left" nowrap>41</td>
							<td class="2-td2-left-qysds2" nowrap>�������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='41_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="41" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>21</td>
							<td class="2-td2-left-qysds2" nowrap>���ڽ���������׼������ת��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='21_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="21" /></td>
							<td class="2-td2-left" nowrap>42</td>
							<td class="2-td2-left-qysds2" nowrap>�������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='42_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="42" /></td>
						</tr>




					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center">
					    <a style="cursor:hand" id="previous"
						onclick="gotoPreviousTable()"><img name="xpage" value="��һ�ű�"
						alt="��д��һ�ű�"
						onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/shangyiye1.jpg" id="previoustable"> </a>&nbsp;&nbsp;
						<a style="cursor:hand" id="next"
						onclick="gotoNextTable()"><img name="spage" value="��һ�ű�"
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
						onClick="doChecked();return false;" accesskey="d"
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
	<br>
	<br>
	<br>

	<%@ include file="../include/footernew.jsp"%>

</html:form>
</body>
</html>
