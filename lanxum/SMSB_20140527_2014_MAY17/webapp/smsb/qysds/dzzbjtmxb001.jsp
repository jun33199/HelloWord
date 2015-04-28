

<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.dzzbjtmxb.web.DzzbjtmxbForm"%>
<%@ page import="java.util.HashMap"%>



<html>
<head>
<title>呆帐准备计提明细表</title>
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
</head>

<script language="JavaScript">
	
	<%/*初始化*/%>
	function doInit()
	{
	//初始化文本框onChange事件处理
		initNumText("je1",21);
		initNumText("je2",21);
	<%
	DzzbjtmxbForm dzzbjtmxbForm=(DzzbjtmxbForm)request.getAttribute("dzzbjtmxbForm");
	if (dzzbjtmxbForm!=null && dzzbjtmxbForm.getDataList().size()>0){
		for(int i=0;i<dzzbjtmxbForm.getDataList().size();i++){
			HashMap map=(HashMap)dzzbjtmxbForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String je1=(String)map.get("je1");
			String je2=(String)map.get("je2");
	%>
			obj=eval("document.getElementById('<%=hc%>_1')");
			obj.value='<%=je1%>';
			
			obj=eval("document.getElementById('<%=hc%>_2')");
			obj.value='<%=je2%>';
			
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
	
	<%/*校验*/%>
	function doCheck()
	{
		doSubmitForm('Check');
	}
	
	<%/*清除*/%>
	function doReset()
	{
	    if(confirm("确认是否清除当前数据？"))
	    {
		   	for(var i=1; i<=21; i++){
				obj = eval("document.getElementById('"+i+"_1')");
				     if (obj.readOnly!=true)
					obj.value = "";
				obj = eval("document.getElementById('"+i+"_2')");
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
<script>
 //	Document.form[0].je_1_2.style.display='none';
 	//
</script>
<br>

<html:form action="/webapp/smsb/qysds/dzzbjtmxbAction">
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
	
	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">呆帐准备计提明细表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="dzzbjtmxbForm" property="sbrq" scope="request" filter="true" />
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
				name="dzzbjtmxbForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="dzzbjtmxbForm" property="nsrmc" scope="request" filter="true" />&nbsp;
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
							<td class="2-td1-left">行&nbsp;&nbsp;次</td>
							<td class="2-td1-left">项&nbsp;&nbsp;&nbsp;&nbsp;目</td>
							<td colspan="2" class="2-td1-center">金额</td>
						</tr>
						<tr>
							<td class="2-td2-left">1<input type='hidden' name='hc' value='1'
								id='hc_1'></td>
							<td class="2-td2-left-qysds1">期初呆帐准备帐户余额</td>
							<td colspan="2" class="2-td2-center"><input type='text'
								name='je1' id='1_1' value='' size='13' tabindex='2'></td>
							<td colspan="0" class="2-td2-center"><input type='text'
								name='je2' id='1_2' value='' size='0' style='display:none'
								tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">2<input type='hidden' name='hc' value='2'
								id='hc_2'></td>
							<td class="2-td2-left-qysds1">期初风险资产余额</td>
							<td colspan="2" class="2-td2-center"><input type='text'
								name='je1' id='2_1' value='' size='13' tabindex='2'></td>
							<td colspan="0" class="2-td2-center"><input type='text'
								name='je2' id='2_2' value='' size='0' style='display:none'
								tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">3<input type='hidden' name='hc' value='3'
								id='hc_3'></td>
							<td class="2-td2-left-qysds1">本期实际发生呆帐损失</td>
							<td colspan="2" class="2-td2-center"><input type='text'
								name='je1' id='3_1' value='' size='13' tabindex='2'></td>
							<td colspan="0" class="2-td2-center"><input type='text'
								name='je2' id='3_2' value='' size='0' style='display:none'
								tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">4<input type='hidden' name='hc' value='4'
								id='hc_4'></td>
							<td class="2-td2-left-qysds1">本期增（减）提呆帐准备</td>
							<td colspan="2" class="2-td2-center"><input type='text'
								name='je1' id='4_1' value='' size='13' tabindex='2'></td>
							<td colspan="0" class="2-td2-center"><input type='text'
								name='je2' id='4_2' value='' size='0' style='display:none'
								tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">5<input type='hidden' name='hc' value='5'
								id='hc_5'></td>
							<td class="2-td2-left-qysds1">本期收回已核销的呆帐</td>
							<td colspan="2" class="2-td2-center"><input type='text'
								name='je1' id='5_1' value='' size='13' tabindex='2'></td>
							<td colspan="0" class="2-td2-center"><input type='text'
								name='je2' id='5_2' value='' size='0' style='display:none'
								tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">6<input type='hidden' name='hc' value='6'
								id='hc_6'></td>
							<td class="2-td2-left-qysds1">期末呆帐准备帐户余额</td>
							<td colspan="2" class="2-td2-center"><input type='text'
								name='je1' id='6_1' value='' size='13' tabindex='2'></td>
							<td colspan="0" class="2-td2-center"><input type='text'
								name='je2' id='6_2' value='' size='0' style='display:none'
								tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">7<input type='hidden' name='hc' value='7'
								id='hc_7'></td>
							<td class="2-td2-left-qysds1">上年申报的呆帐准备纳税调整额</td>
							<td colspan="2" class="2-td2-center"><input type='text'
								name='je1' id='7_1' value='' size='13' tabindex='2'></td>
							<td colspan="0" class="2-td2-center"><input type='text'
								name='je2' id='7_2' value='' size='0' style='display:none'
								tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">8<input type='hidden' name='hc' value='8'
								id='hc_8'></td>
							<td class="2-td2-left-qysds1">期末风险资产余额</td>
							<td colspan="2" class="2-td2-center"><input type='text'
								name='je1' id=8_1 ' value='' size='13' tabindex='2'></td>
							<td colspan="0" class="2-td2-center"><input type='text'
								name='je2' id='8_2' value='' size='0' style='display:none'
								tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">9<input type='hidden' name='hc' value='9'
								id='hc_9'></td>
							<td class="2-td2-left-qysds1">本期按税收规定增提呆帐准备</td>
							<td colspan="2" class="2-td2-center"><input type='text'
								name='je1' id='9_1' value='' size='13' tabindex='2'></td>
							<td colspan="0" class="2-td2-center"><input type='text'
								name='je2' id='9_2' value='' size='0' style='display:none'
								tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">10<input type='hidden' name='hc'
								value='10' id='hc_10'></td>
							<td class="2-td2-left-qysds1">本期实际呆帐损失税务审批数</td>
							<td colspan="2" class="2-td2-center"><input type='text'
								name='je1' id='10_1' value='' size='13' tabindex='2'></td>
							<td colspan="0" class="2-td2-center"><input type='text'
								name='je2' id='10_2' value='' size='0' style='display:none'
								tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">11<input type='hidden' name='hc'
								value='11' id='hc_11'></td>
							<td class="2-td2-left-qysds1">本期申报的呆帐准备纳税调整额</td>
							<td colspan="2" class="2-td2-center"><input type='text'
								name='je1' id='11_1' value='' size='13' tabindex='2'></td>
							<td colspan="0" class="2-td2-center"><input type='text'
								name='je2' id='11_2' value='' size='0' style='display:none'
								tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td class="2-td2-left">风险资产项目</td>
							<td class="2-td2-left">期初余额</td>
							<td class="2-td2-center">期末余额</td>
						</tr>
						<tr>
							<td class="2-td2-left">12<input type='hidden' name='hc'
								value='12' id='hc_12'></td>
							<td class="2-td2-left-qysds1">贷款（含抵押、质押、担保等贷款）</td>
							<td class="2-td2-left"><input type='text' name='je1' id='12_1'
								value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='je2' id='12_2'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">13<input type='hidden' name='hc'
								value='13' id='hc_13'></td>
							<td class="2-td2-left-qysds1">银行卡透支</td>
							<td class="2-td2-left"><input type='text' name='je1' id='13_1'
								value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='je2' id='13_2'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">14<input type='hidden' name='hc'
								value='14' id='hc_14'></td>
							<td class="2-td2-left-qysds1">抵债资产</td>
							<td class="2-td2-left"><input type='text' name='je1' id='14_1'
								value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='je2' id='14_2'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">15<input type='hidden' name='hc'
								value='15' id='hc_15'></td>
							<td class="2-td2-left-qysds1">贴现</td>
							<td class="2-td2-left"><input type='text' name='je1' id='15_1'
								value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='je2' id='15_2'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">16<input type='hidden' name='hc'
								value='16' id='hc_16'></td>
							<td class="2-td2-left-qysds1">银行承兑汇票垫款</td>
							<td class="2-td2-left"><input type='text' name='je1' id='16_1'
								value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='je2' id='16_2'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">17<input type='hidden' name='hc'
								value='17' id='hc_17'></td>
							<td class="2-td2-left-qysds1">进出口押汇</td>
							<td class="2-td2-left"><input type='text' name='je1' id='17_1'
								value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='je2' id='17_2'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">18<input type='hidden' name='hc'
								value='18' id='hc_18'></td>
							<td class="2-td2-left-qysds1">投资</td>
							<td class="2-td2-left"><input type='text' name='je1' id='18_1'
								value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='je2' id='18_2'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">19<input type='hidden' name='hc'
								value='19' id='hc_19'></td>
							<td class="2-td2-left-qysds1">拆借（拆出）</td>
							<td class="2-td2-left"><input type='text' name='je1' id='19_1'
								value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='je2' id='19_2'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">20<input type='hidden' name='hc'
								value='20' id='hc_20'></td>
							<td class="2-td2-left-qysds1">应收款项</td>
							<td class="2-td2-left"><input type='text' name='je1' id='20_1'
								value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='je2' id='20_2'
								value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">21<input type='hidden' name='hc'
								value='21' id='hc_21'></td>
							<td class="2-td2-left-qysds1">其他债权和股权</td>
							<td class="2-td2-left"><input type='text' name='je1' id='21_1'
								value='' size='13' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='je2' id='21_2'
								value='' size='13' tabindex='2'></td>
						</tr>

					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center">
					<a style="cursor:hand" id="previous"
						onclick="gotoPreviousTable()"><img name="spage" value="上一张表"
						alt="填写上一张表"
						onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/shangyiye1.jpg" id="previoustable">
					</a>&nbsp;&nbsp;
					<logic:equal name="dzzbjtmxbForm" property="isLastTable" value="no">
						 <a style="cursor:hand" id="next"
							onclick="gotoNextTable()"><img name="xpage" value="下一张表"
							alt="填写下一张表"
							onMouseOver="MM_swapImage('nexttable','','<%=static_contextpath%>images/xaiyiye2.jpg',1)"
							onMouseOut="MM_swapImgRestore()"
							src="<%=static_contextpath%>images/xaiyiye1.jpg" id="nexttable"> 
						</a>&nbsp;&nbsp;
					</logic:equal>
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
						onClick="doCheck();return false;" accesskey="d"
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
