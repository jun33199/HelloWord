<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jrqycbfymxb.web.JrqycbfymxbForm2008"%>
<%@ page import="java.util.HashMap"%>

<html>
<head>
<title>金融企业成本费用明细表</title>
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
<%/*保存*/%>
	function doInit()
	{
	      //初始化文本框onChange事件处理
		initNumText("je",50);
	      
	<%
	JrqycbfymxbForm2008 jrqycbfymxbForm=(JrqycbfymxbForm2008)request.getAttribute("jrqycbfymxbForm2008");
	if (jrqycbfymxbForm!=null && jrqycbfymxbForm.getDataList().size()>0){
		for(int i=0;i<jrqycbfymxbForm.getDataList().size();i++){
			HashMap map=(HashMap)jrqycbfymxbForm.getDataList().get(i);
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
	
	<%/*清除*/%>
	function doReset()
	{
		if(confirm("确认是否清除当前数据？"))
	    {
	      <%
		   	for(int i=1; i<=50; i++){
		   	%>
				obj = eval("document.getElementById('<%=i%>_1')");
				obj.value = "";
				<%
		   	}
		   	%>
	    }
	}
	
	<%/*删除*/%>
	function doDelete()
	{
	  
		doSubmitForm('Delete');
	}
		
		<%/*返回*/%>
		function doExit()
		{
			doSubmitForm('Exit');
		}

</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInit()">
<%@ include file="../../include/header.jsp"%>
<br>
<html:form action="/webapp/smsb/qysds/2008/jrqycbfymxbAction2008.do" method="post">

	<html:hidden property="actionType" />
    <html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">金融企业成本费用明细表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="jrqycbfymxbForm2008" property="sbrq" scope="request" filter="true" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额单位：元（列至角分）
			</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;计算机代码:<bean:write
				name="jrqycbfymxbForm2008" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="jrqycbfymxbForm2008" property="nsrmc" scope="request" filter="true" />&nbsp;
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
							<!--<td class="2-td1-left" nowrap>行次</td>
				   <td class="2-td1-left" nowrap>项目</td><!-->
							<td class="2-td1-left" nowrap>行次</td>
							<td class="2-td1-left" nowrap>项目</td>
							<td class="2-td1-center" nowrap>金额</td>
							<td class="2-td1-left" nowrap>行次</td>
							<td class="2-td1-left" nowrap>项目</td>
							<td class="2-td1-center" nowrap>金额</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>1</td>
							<td class="2-td2-left-qysds1" nowrap>一、营业成本（2+17+31+38）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='1_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="1" /></td>
							<td class="2-td2-left" nowrap>26</td>
							<td class="2-td2-left-qysds3" nowrap>（6）手续费及佣金支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='26_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="26" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>2</td>
							<td class="2-td2-left-qysds2" nowrap>（一）银行业务成本（3+11+15+16）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='2_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="2" /></td>
							<td class="2-td2-left" nowrap>27</td>
							<td class="2-td2-left-qysds3" nowrap>（7）业务及管理费</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='27_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="27" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>3</td>
							<td class="2-td2-left-qysds2" nowrap>1.银行利息支出（4+5+…+10）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='3_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="3" /></td>
							<td class="2-td2-left" nowrap>28</td>
							<td class="2-td2-left-qysds2" nowrap>&nbsp;&nbsp;减：摊回分保费用</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='28_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="28" /></td>
						</tr>

						<tr>
							<td class="2-td2-left" nowrap>4</td>
							<td class="2-td2-left-qysds3" nowrap>（1）同业存放</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='4_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="4" /></td>
							<td class="2-td2-left" nowrap>29</td>
							<td class="2-td2-left-qysds3" nowrap>（8）其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='29_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="29" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>5</td>
							<td class="2-td2-left-qysds3" nowrap>（2）向中央银行借款</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='5_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="5" /></td>
							<td class="2-td2-left" nowrap>30</td>
							<td class="2-td2-left-qysds2" nowrap>2.其他业务成本</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='30_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="30" /></td>

						</tr>
						<tr>
							<td class="2-td2-left" nowrap>6</td>
							<td class="2-td2-left-qysds3" nowrap>（3）拆入资金</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='6_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="6" /></td>
							<td class="2-td2-left" nowrap>31</td>
							<td class="2-td2-left-qysds2" nowrap>（三）证券业务支出（32+36+37）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='31_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="31" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>7</td>
							<td class="2-td2-left-qysds3" nowrap>（4）吸收存款</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='7_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="7" /></td>
							<td class="2-td2-left" nowrap>32</td>
							<td class="2-td2-left-qysds2" nowrap>1.证券手续费支出（33+34+35)</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='32_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="32" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>8</td>
							<td class="2-td2-left-qysds3" nowrap>（5）卖出回购金融资产</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='8_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="8" /></td>
							<td class="2-td2-left" nowrap>33</td>
							<td class="2-td2-left-qysds3" nowrap>（1）证券经纪业务支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='33_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="33" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>9</td>
							<td class="2-td2-left-qysds3" nowrap>（6）发行债券</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='9_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="9" /></td>
							<td class="2-td2-left" nowrap>34</td>
							<td class="2-td2-left-qysds3" nowrap>（2）佣金</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='34_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="34" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>10</td>
							<td class="2-td2-left-qysds3" nowrap>（7）其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='10_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="10" /></td>
							<td class="2-td2-left" nowrap>35</td>
							<td class="2-td2-left-qysds3" nowrap>（3）其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='35_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="35" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>11</td>
							<td class="2-td2-left-qysds2" nowrap>2.银行手续费及佣金支出（12+13+14）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='11_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="11" /></td>
							<td class="2-td2-left" nowrap>36</td>
							<td class="2-td2-left-qysds2" nowrap>2.业务及管理费</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='36_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="36" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>12</td>
							<td class="2-td2-left-qysds3" nowrap>（1）手续费支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='12_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="12" /></td>
							<td class="2-td2-left" nowrap>37</td>
							<td class="2-td2-left-qysds2" nowrap>3.其他业务成本</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='37_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="37" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>13</td>
							<td class="2-td2-left-qysds3" nowrap>（2）佣金支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='13_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="13" /></td>
							<td class="2-td2-left" nowrap>38</td>
							<td class="2-td2-left-qysds2" nowrap>（四）其他金融业务支出（39+40）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='38_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="38" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>14</td>
							<td class="2-td2-left-qysds3" nowrap>（3）其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='14_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="14" /></td>
							<td class="2-td2-left" nowrap>39</td>
							<td class="2-td2-left-qysds2" nowrap>1.业务支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='39_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="39" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>15</td>
							<td class="2-td2-left-qysds2" nowrap>3.业务及管理费</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='15_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="15" /></td>
							<td class="2-td2-left" nowrap>40</td>
							<td class="2-td2-left-qysds2" nowrap>2.其他业务成本</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='40_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="40" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>16</td>
							<td class="2-td2-left-qysds2" nowrap>4.其他业务成本</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='16_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="16" /></td>
							<td class="2-td2-left" nowrap>41</td>
							<td class="2-td2-left-qysds1" nowrap>二、视同销售应确认成本（42+43+44）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='41_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="41" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>17</td>
							<td class="2-td2-left-qysds2" nowrap>（二）保险业务支出（18+30）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='17_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="17" /></td>
							<td class="2-td2-left" nowrap>42</td>
							<td class="2-td2-left-qysds2" nowrap>1.非货币性资产交换成本</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='42_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="42" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>18</td>
							<td class="2-td2-left-qysds2" nowrap>1.业务支出（19+20-21+22-23+24+25+26+27-28+29）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='18_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="18" /></td>
							<td class="2-td2-left" nowrap>43</td>
							<td class="2-td2-left-qysds2" nowrap>2.货物、财产、劳务视同销售成本</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='43_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="43" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>19</td>
							<td class="2-td2-left-qysds3" nowrap>（1）退保金</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='19_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="19" /></td>
							<td class="2-td2-left" nowrap>44</td>
							<td class="2-td2-left-qysds2" nowrap>3.其他视同销售成本</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='44_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="44" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>20</td>
							<td class="2-td2-left-qysds3" nowrap>（2）赔付支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='20_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="20" /></td>
							<td class="2-td2-left" nowrap>45</td>
							<td class="2-td2-left-qysds1" nowrap>三、营业外支出（46+47+48+49+50）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='45_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="45" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>21</td>
							<td class="2-td2-left-qysds2" nowrap>&nbsp;&nbsp;减：摊回赔付支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='21_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="21" /></td>
							<td class="2-td2-left" nowrap>46</td>
							<td class="2-td2-left-qysds2" nowrap>1.固定资产盘亏</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='46_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="46" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>22</td>
							<td class="2-td2-left-qysds3" nowrap>（3）提取保险责任准备金</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='22_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="22" /></td>
							<td class="2-td2-left" nowrap>47</td>
							<td class="2-td2-left-qysds2" nowrap>2.处置固定资产净损失</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='47_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="47" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>23</td>
							<td class="2-td2-left-qysds2" nowrap>&nbsp;&nbsp;减：摊回保险责任准备金</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='23_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="23" /></td>
							<td class="2-td2-left" nowrap>48</td>
							<td class="2-td2-left-qysds2" nowrap>3.非货币性资产交易损失</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='48_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="48" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>24</td>
							<td class="2-td2-left-qysds3" nowrap>（4）保单红利支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='24_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="24" /></td>
							<td class="2-td2-left" nowrap>49</td>
							<td class="2-td2-left-qysds2" nowrap>4.出售无形资产损失</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='49_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="49" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>25</td>
							<td class="2-td2-left-qysds3" nowrap>（5）分保费用</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='25_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="25" /></td>
							<td class="2-td2-left" nowrap>50</td>
							<td class="2-td2-left-qysds2" nowrap>5.其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='50_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="50" /></td>
						</tr>
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center">
					    <a style="cursor:hand" id="previous"
						onclick="gotoPreviousTable()"><img name="xpage" value="上一张表"
						alt="填写上一张表"
						onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/shangyiye1.jpg" id="previoustable"> </a>&nbsp;&nbsp;
						<a style="cursor:hand" id="next"
						onclick="gotoNextTable()"><img name="spage" value="下一张表"
						alt="填写下一张表"
						onMouseOver="MM_swapImage('nexttable','','<%=static_contextpath%>images/xaiyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/xaiyiye1.jpg" id="nexttable"> </a>&nbsp;&nbsp;
					    <input type="image" style="cursor:hand"
						tabIndex="-1" onClick="doReset();return false;" accesskey="u"
						value="清除" alt="清除页面输入框信息"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" 
						id="qingchu" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1" onClick="doSave();return false;"
						accesskey="s" value="保存" alt="保存"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" 
						id="baocun" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doChecked();return false;" accesskey="d"
						value="单表校验" alt="表内关系校验"
						onMouseOver="MM_swapImage('jiaoyan','','../../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../../images/b-bdjyd1.jpg"
						 id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doDelete();return false;" accesskey="x"
						value="全部删除" alt="删除本表所有数据，且不可恢复"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" 
						id="shanchu" /> &nbsp;&nbsp; <input type="image" value="返回" alt="返回到企业所得税年报主页面"
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

	<%@ include file="../../include/footernew.jsp"%>

</html:form>
</body>
</html>
