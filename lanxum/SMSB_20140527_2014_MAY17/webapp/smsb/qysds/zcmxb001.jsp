<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.zcmxb.web.ZcmxbForm"%>
<%@ page import="java.util.HashMap"%>


<html>
<head>
<title>资产折旧、摊销明细表</title>
</head>
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
	src="../../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/smsb_common.js"></script>
<script language="JavaScript" src="../../js/DTable.js"></script>
<script language=JavaScript src="../../js/reader.js"></script>
<script language=JavaScript src="../../js/compute.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/qysdsnew.js"></script>

<script language="JavaScript">
	
	<%/*初始化*/%>
	function doInit()
	{
	 //初始化文本框onChange事件处理
		initNumText("zyjz",17);
		initNumText("xj",17);
		initNumText("zzfy",17);
		initNumText("glfy",17);
		initNumText("yyfy",17);
		initNumText("zjgc",17);
		
		initNumText("jrqt",17);
		
		initNumText("zcpjjz",17);
		initNumText("zcjscb",17);
		initNumText("zjtxe",17);
		initNumText("nstze",17);
		initNumText("sjxcy",17);
		initNumText("ndkczjtx",17);
		initNumText("ndjze",17);
		initNumText("sqkce",17);
		initNumText("ljjzhndkc",17);
		
	<%
	ZcmxbForm zcmxbForm=(ZcmxbForm)request.getAttribute("zcmxbForm");
	if (zcmxbForm!=null && zcmxbForm.getDataList().size()>0){
		for(int i=0;i<zcmxbForm.getDataList().size();i++){
			HashMap map=(HashMap)zcmxbForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String zyjz=(String)map.get("zyjz");
			String xj=(String)map.get("xj");
			String zzfy=(String)map.get("zzfy");
			String glfy=(String)map.get("glfy");
			String yyfy=(String)map.get("yyfy");
			String zjgc=(String)map.get("zjgc");
			
			String jrqt=(String)map.get("jrqt");
			
			String zcpjjz=(String)map.get("zcpjjz");
			String zcjscb=(String)map.get("zcjscb");
			String zjtxe=(String)map.get("zjtxe");
			String nstze=(String)map.get("nstze");
			String sjxcy=(String)map.get("sjxcy");
			String ndkczjtx=(String)map.get("ndkczjtx");
			String ndjze=(String)map.get("ndjze");
			String sqkce=(String)map.get("sqkce");
			String ljjzhndkc=(String)map.get("ljjzhndkc");
			
			%>
			obj=eval("document.getElementById('<%=hc%>_1')");
			obj.value='<%=zyjz%>';
			
			obj=eval("document.getElementById('<%=hc%>_2')");
			obj.value='<%=xj%>';
			
			obj=eval("document.getElementById('<%=hc%>_3')");
			obj.value='<%=zzfy%>';
			
			obj=eval("document.getElementById('<%=hc%>_4')");
			obj.value='<%=glfy%>';
			
			obj=eval("document.getElementById('<%=hc%>_5')");
			obj.value='<%=yyfy%>';
			
			obj=eval("document.getElementById('<%=hc%>_6')");
			obj.value='<%=zjgc%>';
			
			obj=eval("document.getElementById('<%=hc%>_7')");
			obj.value='<%=jrqt%>';
			
			obj=eval("document.getElementById('<%=hc%>_8')");
			obj.value='<%=zcpjjz%>';
			
			obj=eval("document.getElementById('<%=hc%>_9')");
			obj.value='<%=zcjscb%>';
			
			obj=eval("document.getElementById('<%=hc%>_10')");
			obj.value='<%=zjtxe%>';
			
			obj=eval("document.getElementById('<%=hc%>_11')");
			obj.value='<%=nstze%>';
			
			obj=eval("document.getElementById('<%=hc%>_12')");
			obj.value='<%=sjxcy%>';
			
			obj=eval("document.getElementById('<%=hc%>_13')");
			obj.value='<%=ndkczjtx%>';
			
			obj=eval("document.getElementById('<%=hc%>_14')");
			obj.value='<%=ndjze%>';
			
			obj=eval("document.getElementById('<%=hc%>_15')");
			obj.value='<%=sqkce%>';
			
			obj=eval("document.getElementById('<%=hc%>_16')");
			obj.value='<%=ljjzhndkc%>';
			
			
			<% 
		}
	}
	%>
	}
	
	<%/*保存*/%>
	function doSave()
	{
		doSubmitForm('Save');
	}
	
	<%/*审核*/%>
	function doCheck()
	{
		doSubmitForm('Check');
	}
		
	<%/*清除*/%>
	function doReset()
	{
	    if(confirm("确认是否清除当前数据？"))
	    {
		   	for(var i=1; i<=17; i++){
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
					 
				obj = eval("document.getElementById('"+i+"_8')");
				     if (obj.readOnly!=true)
					 obj.value = "";
				obj = eval("document.getElementById('"+i+"_9')");
				     if (obj.readOnly!=true)
					 obj.value = "";
				obj = eval("document.getElementById('"+i+"_10')");
				     if (obj.readOnly!=true)
					 obj.value = "";
				obj = eval("document.getElementById('"+i+"_11')");
				     if (obj.readOnly!=true)
					 obj.value = "";
				obj = eval("document.getElementById('"+i+"_12')");
				     if (obj.readOnly!=true)
					 obj.value = "";
			    obj = eval("document.getElementById('"+i+"_13')");
				     if (obj.readOnly!=true)
					 obj.value = "";
			    obj = eval("document.getElementById('"+i+"_14')");
				     if (obj.readOnly!=true)
					 obj.value = "";
				obj = eval("document.getElementById('"+i+"_15')");
				     if (obj.readOnly!=true)
					 obj.value = "";
				obj = eval("document.getElementById('"+i+"_16')");
				     if (obj.readOnly!=true)
					 obj.value = "";
		   	}
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

<html:form action="/webapp/smsb/qysds/zcmxbAction">
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">资产折旧、摊销明细表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="zcmxbForm" property="sbrq" scope="request" filter="true" />
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
				name="zcmxbForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="zcmxbForm" property="nsrmc" scope="request" filter="true" />&nbsp;
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
							<td rowspan="2" class="2-td1-left">行&nbsp;&nbsp;次</td>
							<td rowspan="2" class="2-td1-left">资产类别</td>
							<td rowspan="2" class="2-td1-left">本期计提折旧摊销的资产平均原值或折余价值</td>
							<td colspan="6" class="2-td1-left">本期资产折旧或摊销额</td>
							<td rowspan="2" class="2-td1-left">应予调整的资产平均价值</td>
							<td rowspan="2" class="2-td1-left">本期资产计税成本</td>
							<td rowspan="2" class="2-td1-left">允许税前扣除的折旧或摊销额</td>
							<td rowspan="2" class="2-td1-left">本期纳税调整增加额或减少额</td>
							<td rowspan="2" class="2-td1-left">本期转回以前年度确认的时间性差异</td>
							<td colspan="4" class="2-td1-center">可抵减时间性差异的计算</td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">小计</td>
							<td class="2-td2-left" align="center">计入制造费用</td>
							<td class="2-td2-left" align="center">计入管理费用</td>
							<td class="2-td2-left" align="center">计入营业费用</td>
							<td class="2-td2-left" align="center">计入在建工程</td>
							<td class="2-td2-left" align="center">计入其它</td>
							<td class="2-td2-left" align="center">本年结转以后年度扣除的折旧或摊销</td>
							<td class="2-td2-left" align="center">以前年度结转额</td>
							<td class="2-td2-left" align="center">本年税前扣除额</td>
							<td class="2-td2-center" align="center">累计结转以后年度扣除或摊销</td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td class="2-td2-left" align="center">&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td class="2-td2-left" align="center">1</td>
							<td class="2-td2-left" align="center">2</td>
							<td class="2-td2-left" align="center">3</td>
							<td class="2-td2-left" align="center">4</td>
							<td class="2-td2-left" align="center">5</td>
							<td class="2-td2-left" align="center">6</td>
							<td class="2-td2-left" align="center">7</td>
							<td class="2-td2-left" align="center">8</td>
							<td class="2-td2-left" align="center">9</td>
							<td class="2-td2-left" align="center">10</td>
							<td class="2-td2-left" align="center">11</td>
							<td class="2-td2-left" align="center">12</td>
							<td class="2-td2-left" align="center">13</td>
							<td class="2-td2-left" align="center">14</td>
							<td class="2-td2-center" align="center">15</td>
							<td class="2-td2-center" align="center">16</td>

						</tr>
						<tr>
							<td class="2-td2-left" align="center">1<input type='hidden'
								name='hc' id='hc_1' value='1'></td>
							<td class="2-td2-left-qysds1" align="center" nowrap>固定资产小计</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='1_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='1_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='1_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='1_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='1_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='1_6' value='' size='5' tabindex='2'></td>



							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='1_7' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='1_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='1_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='1_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='1_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='1_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='1_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='1_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='1_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='1_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">2<input type='hidden'
								name='hc' id='hc_2' value='2'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>房屋建筑物</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='2_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='2_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='2_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='2_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='2_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='2_6' value='' size='5' tabindex='2'></td>



							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='2_7' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='2_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='2_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='2_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='2_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='2_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='2_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='2_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='2_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='2_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">3<input type='hidden'
								name='hc' id='hc_3' value='3'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>机器设备</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='3_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='3_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='3_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='3_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='3_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='3_6' value='' size='5' tabindex='2'></td>



							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='3_7' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='3_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='3_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='3_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='3_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='3_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='3_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='3_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='3_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='3_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">4<input type='hidden'
								name='hc' id='hc_4' value='4'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>电子设备运输工具</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='4_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='4_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='4_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='4_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='4_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='4_6' value='' size='5' tabindex='2'></td>


							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='4_7' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='4_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='4_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='4_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='4_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='4_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='4_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='4_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='4_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='4_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">5<input type='hidden'
								name='hc' id='hc_5' value='5'></td>
							<td class="2-td2-left-qysds1" align="center" nowrap>无形资产小计</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='5_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='5_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name=zzfy ' id='5_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='5_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='5_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='5_6' value='' size='5' tabindex='2'></td>


							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='5_7' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='5_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='5_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='5_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='5_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='5_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='5_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='5_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='5_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='5_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">6<input type='hidden'
								name='hc' id='hc_6' value='6'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>专利权</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='6_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='6_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='6_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='6_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='6_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='6_6' value='' size='5' tabindex='2'></td>



							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='6_7' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='6_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='6_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='6_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='6_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='6_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='6_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='6_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='6_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='6_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">7<input type='hidden'
								name='hc' id='hc_7' value='7'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>非专利技术</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='7_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='7_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='7_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='7_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='7_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='7_6' value='' size='5' tabindex='2'></td>


							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='7_7' value='' size='5' tabindex='2'></td>




							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='7_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='7_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='7_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='7_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='7_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='7_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='7_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='7_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='7_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">8<input type='hidden'
								name='hc' id='hc_8' value='8'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>商标权</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='8_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='8_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='8_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='8_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='8_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='8_6' value='' size='5' tabindex='2'></td>




							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='8_7' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='8_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='8_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='8_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='8_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='8_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='8_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='8_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='8_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='8_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">9<input type='hidden'
								name='hc' id='hc_9' value='9'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>著作权</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='9_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='9_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='9_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='9_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='9_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='9_6' value='' size='5' tabindex='2'></td>


							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='9_7' value='' size='5' tabindex='2'></td>


							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='9_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='9_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='9_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='9_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='9_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='9_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='9_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='9_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='9_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">10<input type='hidden'
								name='hc' id='hc_10' value='10'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>土地使用权</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='10_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='10_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='10_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='10_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='10_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='10_6' value='' size='5' tabindex='2'></td>


							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='10_7' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='10_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='10_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='10_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='10_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='10_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='10_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='10_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='10_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='10_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">11<input type='hidden'
								name='hc' id='hc_11' value='11'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>商誉</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='11_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='11_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='11_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='11_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='11_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='11_6' value='' size='5' tabindex='2'></td>


							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='11_7' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='11_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='11_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='11_10' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='11_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='11_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='11_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='11_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='11_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='11_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">12<input type='hidden'
								name='hc' id='hc_12' value='12'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>其他</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='12_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='12_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='12_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='12_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='12_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='12_6' value='' size='5' tabindex='2'></td>




							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='12_7' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='12_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='12_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='12_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='12_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='12_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='12_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='12_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='12_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='12_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">13<input type='hidden'
								name='hc' id='hc_13' value='13'></td>
							<td class="2-td2-left-qysds1" align="center" nowrap>其他资产小计</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='13_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='13_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='13_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='13_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='13_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='13_6' value='' size='5' tabindex='2'></td>


							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='13_7' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='13_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='13_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='13_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='13_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='13_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='13_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='13_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='13_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='13_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">14<input type='hidden'
								name='hc' id='hc_14' value='14'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>开办费</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='14_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='14_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='14_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='14_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='14_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='14_6' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='14_7' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='14_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='14_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='14_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='14_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='14_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='14_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='14_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='14_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='14_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">15<input type='hidden'
								name='hc' id='hc_15' value='15'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>长期待摊费</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='15_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='15_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='15_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='15_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='15_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='15_6' value='' size='5' tabindex='2'></td>



							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='15_7' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='15_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='15_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='15_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='15_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='15_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='15_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='15_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='15_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='15_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">16<input type='hidden'
								name='hc' id='hc_16' value='16'></td>
							<td class="2-td2-left-qysds2" align="center" nowrap>其他</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='16_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='16_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='16_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='16_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='16_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='16_6' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='16_7' value='' size='5' tabindex='2'></td>


							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='16_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='16_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='16_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='16_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='16_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='16_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='16_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='16_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='16_16' value='' size='5' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" align="center">17<input type='hidden'
								name='hc' id='hc_17' value='17'></td>
							<td class="2-td2-left-qysds1" align="center" nowrap>合计（1行+5行+13行）</td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zyjz' id='17_1' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='xj' id='17_2' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zzfy' id='17_3' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='glfy' id='17_4' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='yyfy' id='17_5' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjgc' id='17_6' value='' size='5' tabindex='2'></td>


							<td class="2-td2-left" align="center"><input type='text'
								name='jrqt' id='17_7' value='' size='5' tabindex='2'></td>

							<td class="2-td2-left" align="center"><input type='text'
								name='zcpjjz' id='17_8' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zcjscb' id='17_9' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='zjtxe' id='17_10' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='nstze' id='17_11' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sjxcy' id='17_12' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndkczjtx' id='17_13' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='ndjze' id='17_14' value='' size='5' tabindex='2'></td>
							<td class="2-td2-left" align="center"><input type='text'
								name='sqkce' id='17_15' value='' size='5' tabindex='2'></td>
							<td class="2-td2-center" align="center"><input type='text'
								name='ljjzhndkc' id='17_16' value='' size='5' tabindex='2'></td>
						</tr>

					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center"><a style="cursor:hand" id="previous"
				onclick="gotoPreviousTable()"><img name="spage" value="上一张表"
				alt="填写上一张表"
				onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				src="<%=static_contextpath%>images/shangyiye1.jpg" id="previoustable">
			</a>&nbsp;&nbsp;
			
			 <a style="cursor:hand" id="next"
				onclick="gotoNextTable()"><img name="xpage" value="下一张表"
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
						onMouseOver="MM_swapImage('jiaoyan','','../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../images/b-bdjyd1.jpg"
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
	<%@ include file="../include/footernew.jsp"%>
</html:form>
</body>
</html>
