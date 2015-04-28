<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.srbsydw.web.SrbsydwForm"%>
<%@ page import="java.util.HashMap"%>
<html>
	<head>
		<title>事业单位、社会团体、民办非企业单位收入项目明细表</title>
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
	<%/*初始化*/%>	
	function doInit()	
	{
		initNumText("je",24);
			<%
			SrbsydwForm nbForm=(SrbsydwForm)request.getAttribute("srbsydwForm");
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
		   		for(var i=1; i < 25; i++){
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
		<%@include file="../include/header.jsp"%>
		<br>

		<html:form action="/webapp/smsb/qysds/srbsydwAction">
			<html:hidden property="actionType" />
            <html:hidden property="nextTableURL" />
	        <html:hidden property="previousTableURL" />
			<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
				<tr>
					<td class="1-td1" colspan="7">
						事业单位、社会团体、民办非企业单位收入项目明细表
					</td>
				</tr>
				<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="srbsydwForm" property="sbrq" scope="request" filter="true" />
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
				name="srbsydwForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="srbsydwForm" property="nsrmc" scope="request" filter="true" />&nbsp;
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
													<input type="hidden" name="hc" value="1"  id="hc_1" >
												</td>
												<td class="2-td2-left-qysds1" nowrap>
													一、收入总额(2+3+……+11)
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='1_1' value=''
														size='13' tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													13
													<input type="hidden" name="hc" value="13"  id="hc_13" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													纳入预算及预算外专户管理的基金及附加
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='13_1' value=''
														size='13' tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													2
													<input type="hidden" name="hc" value="2"  id="hc_2" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													财政补助收入
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='2_1' value=''
														size='13' tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													14
													<input type="hidden" name="hc" value="14"  id="hc_14" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													纳入预算及预算外专户管理的行政事业性收费
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='14_1' value=''
														size='13' tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													3
													<input type="hidden" name="hc" value="3"  id="hc_3" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													上级补助收入
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='3_1' value=''
														size='13' tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													15
													<input type="hidden" name="hc" value="15"  id="hc_15" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													财政部核准不上缴专户管理的预算外资金
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='15_1' value=''
														size='13' tabindex='2'>
												</td>
											</tr>

											<tr>
												<td class="2-td2-left" nowrap>
													4
													<input type="hidden" name="hc" value="4"  id="hc_4" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													拨入专款
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='4_1' value=''
														size='13' tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													16
													<input type="hidden" name="hc" value="16"  id="hc_16" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													主管部门、上级单位的专项补助收入
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='16_1' value=''
														size='13' tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													5
													<input type="hidden" name="hc" value="5"  id="hc_5" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													事业收入
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='5_1' value=''
														size='13' tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													17
													<input type="hidden" name="hc" value="17"  id="hc_17" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													所属独立核算单位上交的税后利润
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='17_1' value=''
														size='13' tabindex='2'>
												</td>

											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													6
													<input type="hidden" name="hc" value="6"  id="hc_6" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													经营收入
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='6_1' value=''
														size='13' tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													18
													<input type="hidden" name="hc" value="18"  id="hc_18" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													各级政府资助
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='18_1' value=''
														size='13' tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													7
													<input type="hidden" name="hc" value="7"  id="hc_7" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													附属单位缴款
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='7_1' value=''
														size='13' tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													19
													<input type="hidden" name="hc" value="19"  id="hc_19" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													省级以上民政、财政部门规定收取的会费
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='19_1' value=''
														size='13' tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													8
													<input type="hidden" name="hc" value="8"  id="hc_8" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													投资收益（见附表三）
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='8_1' value=''
														size='13' tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													20
													<input type="hidden" name="hc" value="20"  id="hc_20" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													社会各界的捐赠收入
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='20_1' value=''
														size='13' tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													9
													<input type="hidden" name="hc" value="9"  id="hc_9" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													投资转让净收入（见附表三）
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='9_1' value=''
														size='13' tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													21
													<input type="hidden" name="hc" value="21"  id="hc_21" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													财政拨款
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='21_1' value=''
														size='13' tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													10
													<input type="hidden" name="hc" value="10"  id="hc_10" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													捐赠收入
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='10_1' value=''
														size='13' tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													22
													<input type="hidden" name="hc" value="22"  id="hc_22" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													其他
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='22_1' value=''
														size='13' tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													11
													<input type="hidden" name="hc" value="11"  id="hc_11" >
												</td>
												<td class="2-td2-left-qysds2" nowrap>
													其他收入
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='11_1' value=''
														size='13' tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													23
													<input type="hidden" name="hc" value="23"  id="hc_23" >
												</td>
												<td class="2-td2-left-qysds1" nowrap>
													三、应纳税收入总额(1-12)
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='23_1' value=''
														size='13' tabindex='2'>
												</td>
											</tr>
											<tr>
												<td class="2-td2-left" nowrap>
													12
													<input type="hidden" name="hc" value="12"  id="hc_12" >
												</td>
												<td class="2-td2-left-qysds1" nowrap>
													二、免税收入总额(13+14+……+22)
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='12_1' value=''
														size='13' tabindex='1'>
												</td>
												<td class="2-td2-left" nowrap>
													24
													<input type="hidden" name="hc" value="24"  id="hc_24" >
												</td>
												<td class="2-td2-left-qysds1" nowrap>
													四、应纳税收入总额占全部收入总额比重(23÷1)
												</td>
												<td class="2-td2-center" nowrap>
													<input type='text' name='je' id='24_1' value=''
														size='13' tabindex='2'>
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
