<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.cbmxbybqy.web.CbmxbybqyForm2009"%>
<%@ page import="java.util.HashMap"%>
<html>
	<head>
		<title>成本费用明细表</title>
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
		initNumText("je",28);
			<%
			CbmxbybqyForm2009 nbForm=(CbmxbybqyForm2009)request.getAttribute("cbmxbybqyForm2009");
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
		}
		<%/*保存*/%>
		function doSave()
		{
			doSubmitForm('Save');
		
		}
		<%/*表内关系校验*/%>
		function doCheck()
		{
			doSubmitForm('Check');
		}
		<%/*清除*/%>
		function doReset()
		{
			if(confirm("确认是否清除当前数据？"))
			{
		   		for(var i=1; i < 29; i++){
					obj = eval("document.getElementById('" + i+"_1')");
					if(obj.readOnly!=true)
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
		marginheight="0" onload="doInit()">
		<%@include file="../../include/header.jsp"%>
		<br>

		<html:form action="/webapp/smsb/qysds/2009/cbmxbybqyAction2009">
			<html:hidden property="actionType" />
            <html:hidden property="nextTableURL" />
	        <html:hidden property="previousTableURL" />
			<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
				<tr>
					<td class="1-td1" colspan="7">
						成本费用明细表
					</td>
				</tr>
				<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="cbmxbybqyForm2009" property="sbrq" scope="request" filter="true" />
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
				name="cbmxbybqyForm2009" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="cbmxbybqyForm2009" property="nsrmc" scope="request" filter="true" />&nbsp;
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
												<td class="2-td1-left" nowrap>
													行次
												</td>
												<td class="2-td1-left" nowrap>
													项目
												</td>
												<td class="2-td1-center" nowrap>
													金额
												</td>
												<td class="2-td1-left" nowrap>
													行次
												</td>
												<td class="2-td1-left" nowrap>
													项目
												</td>
												<td class="2-td1-center" nowrap>
													金额
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													1
													<input type="hidden" name="hc" value="1" id="hc_1">
												</td>
												<td class="2-td2-left-qysds1" nowrap>
													一、销售（营业）成本合计（2＋7＋12）
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='1_1' value='' size='13'
														tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													15
													<input type="hidden" name="hc" value="15" id="hc_15">
												</td>
												<td class="2-td2-left-qysds3" nowrap>
													（3）其他视同销售成本
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='15_1' value='' size='13'
														tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													2
													<input type="hidden" name="hc" value="2" id="hc_2">
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													（一）主营业务成本（3＋4＋5＋6）
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='2_1' value='' size='13'
														tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													16
													<input type="hidden" name="hc" value="16" id="hc_16">
												</td>
												<td class="2-td2-left-qysds1" nowrap>
													二、营业外支出（17＋18＋······＋24）
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='16_1' value='' size='13'
														tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													3
													<input type="hidden" name="hc" value="3" id="hc_3">
												</td>
												<td class="2-td2-left-qysds3" nowrap>
													（1）销售货物成本
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='3_1' value='' size='13'
														tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													17
													<input type="hidden" name="hc" value="17" id="hc_17">
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													1.固定资产盘亏
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='17_1' value='' size='13'
														tabindex='2'>
												</td>
											</tr>

											<tr>
												<td class="2-td2-left" nowrap>
													4
													<input type="hidden" name="hc" value="4" id="hc_4">
												</td>
												<td class="2-td2-left-qysds3" nowrap>
													（2）提供劳务成本
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='4_1' value='' size='13'
														tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													18
													<input type="hidden" name="hc" value="18" id="hc_18">
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													2.处置固定资产净损失
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='18_1' value='' size='13'
														tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													5
													<input type="hidden" name="hc" value="5" id="hc_5">
												</td>
												<td class="2-td2-left-qysds3" nowrap>
													（3）让渡资产使用权成本
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='5_1' value='' size='13'
														tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													19
													<input type="hidden" name="hc" value="19" id="hc_19">
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													3.出售无形资产损失
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='19_1' value='' size='13'
														tabindex='2'>
												</td>

											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													6
													<input type="hidden" name="hc" value="6" id="hc_6">
												</td>
												<td class="2-td2-left-qysds3" nowrap>
													（4）建造合同成本
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='6_1' value='' size='13'
														tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													20
													<input type="hidden" name="hc" value="20" id="hc_20">
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													4.债务重组损失
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='20_1' value='' size='13'
														tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													7
													<input type="hidden" name="hc" value="7" id="hc_7">
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													（二）其他业务成本（8＋9＋10＋11）
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='7_1' value='' size='13'
														tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													21
													<input type="hidden" name="hc" value="21" id="hc_21">
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													5.罚款支出
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='21_1' value='' size='13'
														tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													8
													<input type="hidden" name="hc" value="8" id="hc_8">
												</td>
												<td class="2-td2-left-qysds3" nowrap>
													（1）材料销售成本
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='8_1' value='' size='13'
														tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													22
													<input type="hidden" name="hc" value="22" id="hc_22">
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													6.非常损失
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='22_1' value='' size='13'
														tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													9
													<input type="hidden" name="hc" value="9" id="hc_9">
												</td>
												<td class="2-td2-left-qysds3" nowrap>
													（2）代购代销费用
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='9_1' value='' size='13'
														tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													23
													<input type="hidden" name="hc" value="23" id="hc_23">
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													7.捐赠支出
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='23_1' value='' size='13'
														tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													10
													<input type="hidden" name="hc" value="10" id="hc_10">
												</td>
												<td class="2-td2-left-qysds3" nowrap>
													（3）包装物出租成本
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='10_1' value='' size='13'
														tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													24
													<input type="hidden" name="hc" value="24" id="hc_24">
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													8.其他
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='24_1' value='' size='13'
														tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													11
													<input type="hidden" name="hc" value="11" id="hc_11">
												</td>
												<td class="2-td2-left-qysds3" nowrap>
													（4）其他
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='11_1' value='' size='13'
														tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													25
													<input type="hidden" name="hc" value="25" id="hc_25">
												</td>
												<td class="2-td2-left-qysds1" nowrap>
													三、期间费用（26＋27＋28）
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='25_1' value='' size='13'
														tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													12
													<input type="hidden" name="hc" value="12" id="hc_12">
												</td>
												<td class="2-td2-left-qysds2" nowrap>
												（三）视同销售成本（13＋14＋15）
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='12_1' value='' size='13'
														tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													26
													<input type="hidden" name="hc" value="26" id="hc_26">
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													1.销售（营业）费用
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='26_1' value='' size='13'
														tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													13
													<input type="hidden" name="hc" value="13" id="hc_13">
												</td>
												<td class="2-td2-left-qysds3" nowrap>
													 （1）非货币性交易视同销售成本
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='13_1' value='' size='13'
														tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													27
													<input type="hidden" name="hc" value="27" id="hc_27">
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													2.管理费用
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='27_1' value='' size='13'
														tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													14
													<input type="hidden" name="hc" value="14" id="hc_14">
												</td>
												<td class="2-td2-left-qysds3" nowrap>
													（2）货物、财产、劳务视同销售成本
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='14_1' value='' size='13'
														tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													28
													<input type="hidden" name="hc" value="28" id="hc_28">
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													3.财务费用
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='28_1' value='' size='13'
														tabindex='2'>
												</td>
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
						onClick="doCheck();return false;" accesskey="d"
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
