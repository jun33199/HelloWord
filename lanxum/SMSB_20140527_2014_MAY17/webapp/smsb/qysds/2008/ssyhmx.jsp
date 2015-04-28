<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.ssyhmx.web.SsyhmxForm"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ttsoft.bjtax.smsb.util.QysdsNewUtil"%>

<html>
<head>
<title>税收优惠明细表</title>
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
		<%/*初始化*/%>	
		function doInit()
		{
		
		//初始化文本框onChange事件处理
		initNumText("je",47);
		var row_45=document.getElementById("45_1");
		row_45.style.textAlign ="right";
		row_45.onkeyup = function(){formateSign();}
		row_45.onblur = function(){return isNum(getCellObject() , null, 9999999999999, null, 16, 0);}
		
		<%
		SsyhmxForm nbForm=(SsyhmxForm)request.getAttribute("ssyhmxForm");
		String wlrdbs = nbForm.getWlrdbs();
			if (nbForm!=null && nbForm.getDataList().size()>0){
				for(int i=0;i<nbForm.getDataList().size();i++){
					HashMap map=(HashMap)nbForm.getDataList().get(i);
					int hc=Integer.parseInt((String)map.get("hc"));
					String je=(String)map.get("je");
					%>
					obj = eval("document.getElementById('<%=hc%>_1')");
					obj.value = '<%=je%>';
					<% 
				}
			}
			%>

	    if(document.all("47_1").value == "01"){
	    	document.forms[0].hy47_1.checked = true;
	    	document.forms[0].hy47_2.checked = false;
	    }
	    if(document.all("47_1").value == "02"){
	    	document.forms[0].hy47_1.checked = false;
	    	document.forms[0].hy47_2.checked = true;
	    }
	    if(document.all("47_1").value != "01" && document.all("47_1").value != "02"){
	    	document.forms[0].hy47_1.checked = false;
	    	document.forms[0].hy47_2.checked = false;
	    }
		}		
		
		<%/*保存*/%>
		function doSave()
		{
			<%/*
			if(document.all("45_1").value==""){
				alert("行45、46、47均不能为空！");
				return;
			}
			if(document.all("46_1").value==""){
				alert("行45、46、47均不能为空！");
				return;
			}
			if(document.all("47_1").value==""){
				alert("行45、46、47均不能为空！");
				return;
			}
			*/%>
			doSubmitForm('Save');
		
		}
		<%/*表内关系校验*/%>
		function doCheck()
		{
			<%/*
			if(document.all("45_1").value==""){
				alert("行45、46、47均不能为空！");
				return;
			}
			if(document.all("46_1").value==""){
				alert("行45、46、47均不能为空！");
				return;
			}
			if(document.all("47_1").value==""){
				alert("行45、46、47均不能为空！");
				return;
			}
			*/%>
			doSubmitForm('Check');
		}
		<%/*清除*/%>
		function doReset()
		{
			if(confirm("确认是否清除当前数据？"))
			{
		   		for(var i=1; i < 48; i++){
					obj = eval("document.getElementById('" + i+"_1')");
					if(obj.readOnly!=true)
					obj.value = "";
		   		}
		   		document.forms[0].hy47_1.checked = false;
	 	      document.forms[0].hy47_2.checked = false;
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
		
		<%/*根据选择设置单选框选择情况*/%>
		function changeSelect()
	  {
	  	if(document.forms[0].hy47_1.checked == true)
	  	{
	  		//选择为独立纳税的，清除汇总纳税方式子项选择，同时将这些子项置位不可编辑
	  		document.all("47_1").value = "01";
	  	}
	  	else
	  	{
	  		document.all("47_1").value = "02";
	  	}
	  }
		
	</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInit()">
<%@include file="../../include/header.jsp"%>
<br>

<html:form action="/webapp/smsb/qysds/2008/ssyhmxAction2008">
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
		<% if("no".equals(null)){ %>
<font color=red>您尚未符合小型微利企业政策。请确认您所填写的减免税金额。</font>
    <% } %>
	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">税收优惠明细表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="ssyhmxForm" property="sbrq" scope="request" filter="true" />
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
				name="ssyhmxForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="ssyhmxForm" property="nsrmc" scope="request" filter="true" />&nbsp;
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
							<td class="2-td2-left" nowrap>1 <input type="hidden" name="hc"
								value="1" id="hc_1"></td>
							<td class="2-td2-left-qysds1" nowrap>一、免税收入（2＋3＋4＋5）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='1_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>25 <input type="hidden" name="hc"
								value="25" id="hc_25"></td>
							<td class="2-td2-left-qysds1" nowrap>   （二）减税所得（26＋27＋28）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='25_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>2 <input type="hidden" name="hc"
								value="2" id="hc_2"></td>
							<td class="2-td2-left-qysds1" nowrap>    1、国债利息收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='2_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>26 <input type="hidden" name="hc"
								value="26" id="hc_26"></td>
							<td class="2-td2-left-qysds1" nowrap>      1、花卉、茶以及其他饮料作物和香料作物的种植</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='26_1' value='' size='13' tabindex='2'></td>
						</tr>

						<tr>
							<td class="2-td2-left" nowrap>3 <input type="hidden" name="hc"
								value="3" id="hc_3"></td>
							<td class="2-td2-left-qysds1" nowrap>    2、符合条件的居民企业之间的股息、红利等权益性投资收益</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='3_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>27 <input type="hidden" name="hc"
								value="27" id="hc_27"></td>
							<td class="2-td2-left-qysds1" nowrap>      2、海水养殖、内陆养殖</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='27_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>4 <input type="hidden" name="hc"
								value="4" id="hc_4"></td>
							<td class="2-td2-left-qysds1" nowrap>    3、符合条件的非营利组织的收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='4_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>28 <input type="hidden" name="hc"
								value="28" id="hc_28"></td>
							<td class="2-td2-left-qysds1" nowrap>      3、其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='28_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>5 <input type="hidden" name="hc"
								value="5" id="hc_5"></td>
							<td class="2-td2-left-qysds1" nowrap>    4、其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='5_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>29 
							  <input type="hidden" name="hc"
								value="29" id="hc_29"></td>
							<td class="2-td2-left-qysds1" nowrap>   （三）从事国家重点扶持的公共基础设施项目投资经营的所得</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='29_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>6 <input type="hidden" name="hc"
								value="6" id="hc_6"></td>
							<td class="2-td2-left-qysds1" nowrap>二、减计收入（7＋8）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='6_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>30 
							  <input type="hidden" name="hc"
								value="30" id="hc_30"></td>
							<td class="2-td2-left-qysds1" nowrap>   （四）从事符合条件的环境保护、节能节水项目的所得</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='30_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>7 <input type="hidden" name="hc"
								value="7" id="hc_7"></td>
							<td class="2-td2-left-qysds1" nowrap>    1、企业综合利用资源,生产符合国家产业政策规定的产品所取得的收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='7_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>31 
							  <input type="hidden" name="hc"
								value="31" id="hc_31"></td>
							<td class="2-td2-left-qysds1" nowrap>   （五）符合条件的技术转让所得</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='31_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>8 <input type="hidden" name="hc"
								value="8" id="hc_8"></td>
							<td class="2-td2-left-qysds1" nowrap>    2、其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='8_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>32 
							  <input type="hidden" name="hc"
								value="32" id="hc_32"></td>
							<td class="2-td2-left-qysds1" nowrap>   （六）其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='32_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>9 <input type="hidden" name="hc"
								value="9" id="hc_9"></td>
							<td class="2-td2-left-qysds1" nowrap>三、加计扣除额合计（10＋11＋12＋13）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='9_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>33 <input type="hidden" name="hc"
								value="33" id="hc_33"></td>
							<td class="2-td2-left-qysds1" nowrap>五、减免税合计（34＋35＋36＋37＋38）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='33_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>10 <input type="hidden" name="hc"
								value="10" id="hc_10"></td>
							<td class="2-td2-left-qysds1" nowrap>    1、开发新技术、新产品、新工艺发生的研究开发费用</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='10_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>34 <input type="hidden" name="hc"
								value="34" id="hc_34"></td>
							<td class="2-td2-left-qysds1" nowrap>   （一）符合条件的小型微利企业</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='34_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>11 <input type="hidden" name="hc"
								value="11" id="hc_11"></td>
							<td class="2-td2-left-qysds1" nowrap>    2、安置残疾人员所支付的工资</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='11_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>35 <input type="hidden" name="hc"
								value="35" id="hc_35"></td>
							<td class="2-td2-left-qysds1" nowrap>   （二）国家需要重点扶持的高新技术企业</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='35_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>12 <input type="hidden" name="hc"
								value="12" id="hc_12"></td>
							<td class="2-td2-left-qysds1" nowrap>    3、国家鼓励安置的其他就业人员支付的工资</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='12_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>36 
							  <input type="hidden" name="hc"
								value="36" id="hc_36"></td>
							<td class="2-td2-left-qysds1" nowrap>   （三）民族自治地方的企业应缴纳的企业所得税中属于地方分享的部分</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='36_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>13 <input type="hidden" name="hc"
								value="13" id="hc_13"></td>
							<td class="2-td2-left-qysds1" nowrap>    4、其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='13_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>37 
							  <input type="hidden" name="hc"
								value="37" id="hc_37"></td>
							<td class="2-td2-left-qysds1" nowrap>   （四）过渡期税收优惠</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='37_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>14 <input type="hidden" name="hc"
								value="14" id="hc_14"></td>
							<td class="2-td2-left-qysds1" nowrap>四、减免所得额合计（15＋25＋29＋30＋31＋32）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='14_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>38 
							  <input type="hidden" name="hc"
								value="38" id="hc_38"></td>
							<td class="2-td2-left-qysds1" nowrap>   （五）其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='38_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>15 <input type="hidden" name="hc"
								value="15" id="hc_15"></td>
							<td class="2-td2-left-qysds1" nowrap>   （一）免税所得（16＋17＋…＋24）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='15_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>39 
							  <input type="hidden" name="hc"
								value="39" id="hc_39"></td>
							<td class="2-td2-left-qysds1" nowrap>六、创业投资企业抵扣的应纳税所得额</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='39_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>16 <input type="hidden" name="hc"
								value="16" id="hc_16"></td>
							<td class="2-td2-left-qysds1" nowrap>      1、蔬菜、谷物、薯类、油料、豆类、棉花、麻类、糖料、水果、坚果的种植</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='16_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>40 
							  <input type="hidden" name="hc"
								value="40" id="hc_40"></td>
							<td class="2-td2-left-qysds1" nowrap>七、抵免所得税额合计（41＋42＋43＋44）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='40_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>17 <input type="hidden" name="hc"
								value="17" id="hc_17"></td>
							<td class="2-td2-left-qysds1" nowrap>      2、农作物新品种的选育</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='17_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>41 
							  <input type="hidden" name="hc"
								value="41" id="hc_41"></td>
							<td class="2-td2-left-qysds1" nowrap>   （一）企业购置用于环境保护专用设备的投资额抵免的税额</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='41_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>18 <input type="hidden" name="hc"
								value="18" id="hc_18"></td>
							<td class="2-td2-left-qysds1" nowrap>      3、中药材的种植</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='18_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>42 
							  <input type="hidden" name="hc"
								value="42" id="hc_42"></td>
							<td class="2-td2-left-qysds1" nowrap>   （二）企业购置用于节能节水专用设备的投资额抵免的税额</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='42_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>19 <input type="hidden" name="hc"
								value="19" id="hc_19"></td>
							<td class="2-td2-left-qysds1" nowrap>      4、林木的培育和种植</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='19_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>43 
							  <input type="hidden" name="hc"
								value="43" id="hc_43"></td>
							<td class="2-td2-left-qysds1" nowrap>   （三）企业购置用于安全生产专用设备的投资额抵免的税额</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='43_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>20 <input type="hidden" name="hc"
								value="20" id="hc_20"></td>
							<td class="2-td2-left-qysds1" nowrap>      5、牲畜、家禽的饲养</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='20_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>44 <input type="hidden" name="hc"
								value="44" id="hc_44"></td>
							<td class="2-td2-left-qysds1" nowrap>   （四）其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='44_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>21 <input type="hidden" name="hc"
								value="21" id="hc_21"></td>
							<td class="2-td2-left-qysds1" nowrap>      6、林产品的采集</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='21_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>45 <input type="hidden" name="hc"
								value="45" id="hc_45"></td>
							<td class="2-td2-left-qysds1" nowrap>企业从业人数（全年平均人数）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='45_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>22 <input type="hidden" name="hc"
								value="22" id="hc_22"></td>
							<td class="2-td2-left-qysds1" nowrap>      7、灌溉、农产品初加工、兽医、农技推广、农机作业和维修等农、林、牧、渔服务业项目</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='22_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>46 <input type="hidden" name="hc"
								value="46" id="hc_46"></td>
							<td class="2-td2-left-qysds1" nowrap>资产总额（全年平均数）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='46_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>23 <input type="hidden" name="hc"
								value="23" id="hc_23"></td>
							<td class="2-td2-left-qysds1" nowrap>      8、远洋捕捞</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='23_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>47 <input type="hidden" name="hc"
								value="47" id="hc_47"></td>
							<td class="2-td2-left-qysds1" nowrap>所属行业（工业企业    其他企业    ）</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='je'
								id='47_1' value=''>
								<input type="Radio" name="hyje" id="hy47_1" value="01" onclick="changeSelect()" onchange="changeSelect()">工业企业
								<input type="Radio" name="hyje" id="hy47_2" value="02" onclick="changeSelect()" onchange="changeSelect()">其他企业
								</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>24 <input type="hidden" name="hc"
								value="24" id="hc_24"></td>
							<td class="2-td2-left-qysds1" nowrap>      9、其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='24_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>&nbsp;</td>
							<td class="2-td2-left-qysds1" nowrap>&nbsp;</td>
							<td class="2-td2-center" nowrap>&nbsp;</td>
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
					<input type="image" style="cursor:hand" tabIndex="-1"
						onClick="doReset();return false;" accesskey="u" value="清除"
						alt="清除页面输入框信息"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" id="qingchu" />
					&nbsp;&nbsp; <input type="image" style="cursor:hand" tabIndex="-1"
						onClick="doSave();return false;" accesskey="s" value="保存" alt="保存"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" id="baocun" />
					&nbsp;&nbsp; <input type="image" style="cursor:hand" tabIndex="-1"
						onClick="doCheck();return false;" accesskey="d" value="单表校验"
						alt="表内关系校验"
						onMouseOver="MM_swapImage('jiaoyan','','../../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../../images/b-bdjyd1.jpg"
						id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doDelete();return false;" accesskey="x" value="全部删除"
						alt="删除本表所有数据，且不可恢复"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" id="shanchu" />
					&nbsp;&nbsp; <input type="image" value="返回" alt="返回到企业所得税年报主页面"
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