<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.srbjrqy.web.SrbjrqyForm"%>
<%@ page import="java.util.HashMap"%>


<html>
<head>
<title>金融保险企业主营业务收入明细表</title>
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
	 initNumText("je",28);
	<%
	SrbjrqyForm srbjrqyForm=(SrbjrqyForm)request.getAttribute("srbjrqyForm");
	if (srbjrqyForm!=null && srbjrqyForm.getDataList().size()>0){
		for(int i=0;i<srbjrqyForm.getDataList().size();i++){
			HashMap map=(HashMap)srbjrqyForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String je=(String)map.get("je");
			System.out.println(je);
						%>
						
			obj=eval("document.getElementById(<%=hc%>+'_1')");
			obj.value='<%=je%>';
			
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
		   	for(var i=1; i<=28; i++){
				obj = eval("document.getElementById('"+i+"_1')");
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

<html:form action="/webapp/smsb/qysds/srbjrqyAction">
	<html:hidden property="actionType" />
    <html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />   
	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">金融保险企业主营业务收入明细表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="srbjrqyForm" property="sbrq" scope="request" filter="true" />
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
				name="srbjrqyForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="srbjrqyForm" property="nsrmc" scope="request" filter="true" />&nbsp;
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
							<td class="2-td2-left" nowrap>1<input type='hidden' name='hc'
								id='hc_1' value='1'></td>
							<td class="2-td2-left-qysds1" nowrap>一、基本业务收入（2+3+…+15）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='1_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>15<input type='hidden' name='hc'
								id='hc_15' value='15'></td>
							<td class="2-td2-left-qysds2" nowrap>其他业务收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='15_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>2<input type='hidden' name='hc'
								id='hc_2' value='2'></td>
							<td class="2-td2-left-qysds2" nowrap>利息收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='2_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>16<input type='hidden' name='hc'
								id='hc_16' value='16'></td>
							<td class="2-td2-left-qysds1" nowrap>二、其他收入合计（17+18+24+28）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='16_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>3<input type='hidden' name='hc'
								id='hc_3' value='3'></td>
							<td class="2-td2-left-qysds2" nowrap>金融企业往来收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='3_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>17<input type='hidden' name='hc'
								id='hc_17' value='17'></td>
							<td class="2-td2-left-qysds2" nowrap>1、视同销售收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='17_1' value='' size='13' tabindex='2'></td>
						</tr>

						<tr>
							<td class="2-td2-left" nowrap>4<input type='hidden' name='hc'
								id='hc_4' value='4'></td>
							<td class="2-td2-left-qysds2" nowrap>中间业务收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='4_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>18<input type='hidden' name='hc'
								id='hc_18' value='18'></td>
							<td class="2-td2-left-qysds2" nowrap>2、营业外收入（19+20+21+22+23）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='18_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>5<input type='hidden' name='hc'
								id='hc_5' value='5'></td>
							<td class="2-td2-left-qysds2" nowrap>手续费收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='5_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>19<input type='hidden' name='hc'
								id='hc_19' value='19'></td>
							<td class="2-td2-left-qysds3" nowrap>固定资产盘盈和转让净收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='19_1' value='' size='13' tabindex='2'></td>

						</tr>
						<tr>
							<td class="2-td2-left" nowrap>6<input type='hidden' name='hc'
								id='hc_6' value='6'></td>
							<td class="2-td2-left-qysds2" nowrap>证券承销收入（亏损以"-"填列）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='6_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>20<input type='hidden' name='hc'
								id='hc_20' value='20'></td>
							<td class="2-td2-left-qysds3" nowrap>非货币性交易收益</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='20_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>7<input type='hidden' name='hc'
								id='hc_7' value='7'></td>
							<td class="2-td2-left-qysds2" nowrap>自营证券差价收入（亏损以"-"填列）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='7_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>21<input type='hidden' name='hc'
								id='hc_21' value='21'></td>
							<td class="2-td2-left-qysds3" nowrap>出售无形资产收益</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='21_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>8<input type='hidden' name='hc'
								id='hc_8' value='8'></td>
							<td class="2-td2-left-qysds2" nowrap>买入返售证券收入（亏损以"-"填列）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='8_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>22<input type='hidden' name='hc'
								id='hc_22' value='22'></td>
							<td class="2-td2-left-qysds3" nowrap>取得的各种价外基金、收费和附加</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='22_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>9<input type='hidden' name='hc'
								id='hc_9' value='9'></td>
							<td class="2-td2-left-qysds2" nowrap>受托投资管理收益（亏损以"-"填列）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='9_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>23<input type='hidden' name='hc'
								id='hc_23' value='23'></td>
							<td class="2-td2-left-qysds3" nowrap>罚款收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='23_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>10<input type='hidden' name='hc'
								id='hc_10' value='10'></td>
							<td class="2-td2-left-qysds2" nowrap>保费收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='10_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>24<input type='hidden' name='hc'
								id='hc_24' value='24'></td>
							<td class="2-td2-left-qysds2" nowrap>3、税收上应确认的收入（25+26+27）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='24_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>11<input type='hidden' name='hc'
								id='hc_11' value='11'></td>
							<td class="2-td2-left-qysds2" nowrap>分保费收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='11_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>25<input type='hidden' name='hc'
								id='hc_25' value='25'></td>
							<td class="2-td2-left-qysds3" nowrap>因债权人原因确实无法支付的应付款</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='25_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>12<input type='hidden' name='hc'
								id='hc_12' value='12'></td>
							<td class="2-td2-left-qysds2" nowrap>追偿款收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='12_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>26<input type='hidden' name='hc'
								id='hc_26' value='26'></td>
							<td class="2-td2-left-qysds3" nowrap>接受捐赠的资产</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='26_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>13<input type='hidden' name='hc'
								id='hc_13' value='13'></td>
							<td class="2-td2-left-qysds2" nowrap>基金收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='13_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>27<input type='hidden' name='hc'
								id='hc_27' value='27'></td>
							<td class="2-td2-left-qysds3" nowrap>在税收上确认的资产评估增值</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='27_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>14<input type='hidden' name='hc'
								id='hc_14' value='14'></td>
							<td class="2-td2-left-qysds2" nowrap>汇兑收益</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='14_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>28<input type='hidden' name='hc'
								id='hc_28' value='28'></td>
							<td class="2-td2-left-qysds2" nowrap>4、其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='28_1' value='' size='13' tabindex='2'></td>
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
