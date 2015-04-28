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
<title>金融保险企业营业成本明细表</title>
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
<%/*保存*/%>
	function doInit()
	{
	      //初始化文本框onChange事件处理
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
	
	<%/*清除*/%>
	function doReset()
	{
		if(confirm("确认是否清除当前数据？"))
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
<%@ include file="../include/header.jsp"%>
<br>
<html:form action="/webapp/smsb/qysds/cbmxbjrqyAction.do" method="post">

	<html:hidden property="actionType" />
    <html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">金融保险企业营业成本明细表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="cbmxbjrqyForm" property="sbrq" scope="request" filter="true" />
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
				name="cbmxbjrqyForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
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
							<td class="2-td2-left-qysds1" nowrap>一、基本业务成本合计（2+3+…+24-25）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='1_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="1" /></td>
							<td class="2-td2-left" nowrap>22</td>
							<td class="2-td2-left-qysds2" nowrap>提取保险保障基金</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='22_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="22" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>2</td>
							<td class="2-td2-left-qysds2" nowrap>利息支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='2_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="2" /></td>
							<td class="2-td2-left" nowrap>23</td>
							<td class="2-td2-left-qysds2" nowrap>汇兑损失</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='23_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="23" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>3</td>
							<td class="2-td2-left-qysds2" nowrap>金融企业往来支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='3_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="3" /></td>
							<td class="2-td2-left" nowrap>24</td>
							<td class="2-td2-left-qysds2" nowrap>其他业务支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='24_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="24" /></td>
						</tr>

						<tr>
							<td class="2-td2-left" nowrap>4</td>
							<td class="2-td2-left-qysds2" nowrap>中间业务支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='4_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="4" /></td>
							<td class="2-td2-left" nowrap>25</td>
							<td class="2-td2-left-qysds2" nowrap>减：摊回分保赔款</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='25_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="25" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>5</td>
							<td class="2-td2-left-qysds2" nowrap>手续费支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='5_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="5" /></td>
							<td class="2-td2-left" nowrap>26</td>
							<td class="2-td2-left-qysds1" nowrap>二、其他支出（27+28+35+38）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='26_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="26" /></td>

						</tr>
						<tr>
							<td class="2-td2-left" nowrap>6</td>
							<td class="2-td2-left-qysds2" nowrap>卖出回购证券支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='6_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="6" /></td>
							<td class="2-td2-left" nowrap>27</td>
							<td class="2-td2-left-qysds2" nowrap>1、视同销售成本</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='27_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="27" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>7</td>
							<td class="2-td2-left-qysds2" nowrap>死伤医疗给付</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='7_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="7" /></td>
							<td class="2-td2-left" nowrap>28</td>
							<td class="2-td2-left-qysds2" nowrap>2、营业外支出（29+30+…+34)</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='28_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="28" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>8</td>
							<td class="2-td2-left-qysds2" nowrap>满期给付</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='8_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="8" /></td>
							<td class="2-td2-left" nowrap>29</td>
							<td class="2-td2-left-qysds3" nowrap>固定资产盘亏</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='29_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="29" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>9</td>
							<td class="2-td2-left-qysds2" nowrap>年金给付</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='9_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="9" /></td>
							<td class="2-td2-left" nowrap>30</td>
							<td class="2-td2-left-qysds3" nowrap>处置固定资产净损失</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='30_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="30" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>10</td>
							<td class="2-td2-left-qysds2" nowrap>退保金</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='10_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="10" /></td>
							<td class="2-td2-left" nowrap>31</td>
							<td class="2-td2-left-qysds3" nowrap>出售无形资产损失</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='31_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="31" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>11</td>
							<td class="2-td2-left-qysds2" nowrap>赔款支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='11_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="11" /></td>
							<td class="2-td2-left" nowrap>32</td>
							<td class="2-td2-left-qysds3" nowrap>罚款支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='32_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="32" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>12</td>
							<td class="2-td2-left-qysds2" nowrap>分出保费</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='12_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="12" /></td>
							<td class="2-td2-left" nowrap>33</td>
							<td class="2-td2-left-qysds3" nowrap>非常损失</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='33_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="33" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>13</td>
							<td class="2-td2-left-qysds2" nowrap>分保赔款支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='13_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="13" /></td>
							<td class="2-td2-left" nowrap>34</td>
							<td class="2-td2-left-qysds3" nowrap>其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='34_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="34" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>14</td>
							<td class="2-td2-left-qysds2" nowrap>分保费用支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='14_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="14" /></td>
							<td class="2-td2-left" nowrap>35</td>
							<td class="2-td2-left-qysds2" nowrap>3、税收上应确认的其他成本费用(36+37)</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='35_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="35" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>15</td>
							<td class="2-td2-left-qysds2" nowrap>佣金支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='15_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="15" /></td>
							<td class="2-td2-left" nowrap>36</td>
							<td class="2-td2-left-qysds3" nowrap>税收上确认的资产评估减值</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='36_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="36" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>16</td>
							<td class="2-td2-left-qysds2" nowrap>保户红利支出</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='16_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="16" /></td>
							<td class="2-td2-left" nowrap>37</td>
							<td class="2-td2-left-qysds3" nowrap>其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='37_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="37" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>17</td>
							<td class="2-td2-left-qysds2" nowrap>未到期责任准备金提转差</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='17_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="17" /></td>
							<td class="2-td2-left" nowrap>38</td>
							<td class="2-td2-left-qysds2" nowrap>4、其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='38_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="38" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>18</td>
							<td class="2-td2-left-qysds2" nowrap>长期责任准备金提转差</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='18_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="18" /></td>
							<td class="2-td2-left" nowrap>39</td>
							<td class="2-td2-left-qysds1" nowrap>三、期间费用合计（40+41+42）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='39_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="39" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>19</td>
							<td class="2-td2-left-qysds2" nowrap>未决赔款准备金提转差</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='19_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="19" /></td>
							<td class="2-td2-left" nowrap>40</td>
							<td class="2-td2-left-qysds2" nowrap>营业费用</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='40_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="40" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>20</td>
							<td class="2-td2-left-qysds2" nowrap>寿险责任准备金提转差</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='20_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="20" /></td>
							<td class="2-td2-left" nowrap>41</td>
							<td class="2-td2-left-qysds2" nowrap>管理费用</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='41_1' value='' size='13' tabindex='2'> <input type="hidden"
								name="hc" value="41" /></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>21</td>
							<td class="2-td2-left-qysds2" nowrap>长期健康险责任准备金提转差</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='21_1' value='' size='13' tabindex='1'> <input type="hidden"
								name="hc" value="21" /></td>
							<td class="2-td2-left" nowrap>42</td>
							<td class="2-td2-left-qysds2" nowrap>财务费用</td>
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
						onMouseOver="MM_swapImage('jiaoyan','','../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../images/b-bdjyd1.jpg"
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

	<%@ include file="../include/footernew.jsp"%>

</html:form>
</body>
</html>
