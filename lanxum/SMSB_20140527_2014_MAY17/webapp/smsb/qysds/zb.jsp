<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.zb.web.ZbForm"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ttsoft.bjtax.smsb.util.QysdsNewUtil"%>

<html>
<head>
<title>查帐征收企业所得税年度纳税申报表</title>
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
		
		//初始化文本框onChange事件处理
		initNumText("ljje",35);
		<%
		ZbForm nbForm=(ZbForm)request.getAttribute("zbForm");
			if (nbForm!=null && nbForm.getDataList().size()>0){
				for(int i=0;i<nbForm.getDataList().size();i++){
					HashMap map=(HashMap)nbForm.getDataList().get(i);
					int hc=Integer.parseInt((String)map.get("hc"));
					String ljje=(String)map.get("ljje");
					%>
					obj = eval("document.getElementById('<%=hc%>_1')");
					obj.value = '<%=ljje%>';
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
		   		for(var i=1; i < 36; i++){
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

<html:form action="/webapp/smsb/qysds/zbAction">
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />

	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">查帐征收企业所得税年度纳税申报表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="zbForm" property="sbrq" scope="request" filter="true" />
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
				name="zbForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="zbForm" property="nsrmc" scope="request" filter="true" />&nbsp;
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
							<td class="2-td1-center" nowrap>累计金额</td>
							<td class="2-td1-left" nowrap>行次</td>
							<td class="2-td1-left" nowrap>项目</td>
							<td class="2-td1-center" nowrap>累计金额</td>
						</tr>
						<tr>
							<td colspan="3" class="2-td2-center" nowrap>收入总额</td>
							<td class="2-td2-left" nowrap>19 <input type="hidden" name="hc"
								value="19" id="hc_19"></td>
							<td class="2-td2-left-qysds1" nowrap>加：应补税投资收益已缴所得税额</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='19_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>1 <input type="hidden" name="hc"
								value="1" id="hc_1"></td>
							<td class="2-td2-left-qysds1" nowrap>销售(营业)收入(请填附表一)</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='1_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>20 <input type="hidden" name="hc"
								value="20" id="hc_20"></td>
							<td class="2-td2-left-qysds1" nowrap>减：允许扣除的公益救济性捐赠额（请填附表八）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='20_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>2 <input type="hidden" name="hc"
								value="2" id="hc_2"></td>
							<td class="2-td2-left-qysds1" nowrap>投资收益（请填附表三）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='2_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>21 <input type="hidden" name="hc"
								value="21" id="hc_21"></td>
							<td class="2-td2-left-qysds1" nowrap>减：加计扣除额(21≤16-17-18+19-20)</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='21_1' value='' size='13' tabindex='2'></td>
						</tr>

						<tr>
							<td class="2-td2-left" nowrap>3 <input type="hidden" name="hc"
								value="3" id="hc_3"></td>
							<td class="2-td2-left-qysds1" nowrap>投资转让净收入（请填附表三）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='3_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>22 <input type="hidden" name="hc"
								value="22" id="hc_22"></td>
							<td class="2-td2-left-qysds1" nowrap>应纳税所得额（16－17－18+19－20－21）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='22_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>4 <input type="hidden" name="hc"
								value="4" id="hc_4"></td>
							<td class="2-td2-left-qysds1" nowrap>补贴收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='4_1' value='' size='13' tabindex='1'></td>
							<td colspan="3" class="2-td2-center" nowrap>应纳所得税额的计算</td>

						</tr>
						<tr>
							<td class="2-td2-left" nowrap>5 <input type="hidden" name="hc"
								value="5" id="hc_5"></td>
							<td class="2-td2-left-qysds1" nowrap>其他收入(请填附表一)</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='5_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>23 <input type="hidden" name="hc"
								value="23" id="hc_23"></td>
							<td class="2-td2-left-qysds1" nowrap>适用税率</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='23_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>6 <input type="hidden" name="hc"
								value="6" id="hc_6"></td>
							<td class="2-td2-left-qysds1" nowrap>收入总额合计(1+2+3+4+5)</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='6_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>24 <input type="hidden" name="hc"
								value="24" id="hc_24"></td>
							<td class="2-td2-left-qysds1" nowrap>境内所得应纳所得税额（22×23）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='24_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td colspan="3" class="2-td2-center" nowrap>扣除项目</td>
							<td class="2-td2-left" nowrap>25 <input type="hidden" name="hc"
								value="25" id="hc_25"></td>
							<td class="2-td2-left-qysds1" nowrap>减：境内投资所得抵免税额</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='25_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>7 <input type="hidden" name="hc"
								value="7" id="hc_7"></td>
							<td class="2-td2-left-qysds1" nowrap>销售(营业)成本(请填附表二)</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='7_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>26 <input type="hidden" name="hc"
								value="26" id="hc_26"></td>
							<td class="2-td2-left-qysds1" nowrap>加：境外所得应纳所得税额(请填附表十)</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='26_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>8 <input type="hidden" name="hc"
								value="8" id="hc_8"></td>
							<td class="2-td2-left-qysds1" nowrap>主营业务税金及附加</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='8_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>27 <input type="hidden" name="hc"
								value="27" id="hc_27"></td>
							<td class="2-td2-left-qysds1" nowrap>减：境外所得抵免税额(请填附表十)</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='27_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>9 <input type="hidden" name="hc"
								value="9" id="hc_9"></td>
							<td class="2-td2-left-qysds1" nowrap>期间费用(请填附表二)</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='9_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>28 <input type="hidden" name="hc"
								value="28" id="hc_28"></td>
							<td class="2-td2-left-qysds1" nowrap>境内、外所得应纳所得税额（24－25＋26－27）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='28_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>10 <input type="hidden" name="hc"
								value="10" id="hc_10"></td>
							<td class="2-td2-left-qysds1" nowrap>投资转让成本（请填附表三）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='10_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>29 <input type="hidden" name="hc"
								value="29" id="hc_29"></td>
							<td class="2-td2-left-qysds1" nowrap>减：减免所得税额（请填附表七）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='29_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>11 <input type="hidden" name="hc"
								value="11" id="hc_11"></td>
							<td class="2-td2-left-qysds1" nowrap>其他扣除项目（请填附表二）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='11_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>30 <input type="hidden" name="hc"
								value="30" id="hc_30"></td>
							<td class="2-td2-left-qysds1" nowrap>实际应纳所得税额（28－29）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='30_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>12 <input type="hidden" name="hc"
								value="12" id="hc_12"></td>
							<td class="2-td2-left-qysds1" nowrap>扣除项目合计（7+8+9+10+11）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='12_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>31 <input type="hidden" name="hc"
								value="31" id="hc_31"></td>
							<td class="2-td2-left-qysds1" nowrap>汇总纳税成员企业就地预缴比例</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='31_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td colspan="3" class="2-td2-center" nowrap>应纳税所得额的计算</td>
							<td class="2-td2-left" nowrap>32 <input type="hidden" name="hc"
								value="32" id="hc_32"></td>
							<td class="2-td2-left-qysds1" nowrap>汇总纳税成员企业就地应预缴的所得税额(30×31)</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='32_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>13 <input type="hidden" name="hc"
								value="13" id="hc_13"></td>
							<td class="2-td2-left-qysds1" nowrap>纳税调整前所得（6－12）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='13_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>33 <input type="hidden" name="hc"
								value="33" id="hc_33"></td>
							<td class="2-td2-left-qysds1" nowrap>减：本期累计实际已预缴的所得税额</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='33_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>14 <input type="hidden" name="hc"
								value="14" id="hc_14"></td>
							<td class="2-td2-left-qysds1" nowrap>加：纳税调整增加额（请填附表四）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='14_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>34 <input type="hidden" name="hc"
								value="34" id="hc_34"></td>
							<td class="2-td2-left-qysds1" nowrap>本期应补(退)的所得税额</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='34_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>15 <input type="hidden" name="hc"
								value="15" id="hc_15"></td>
							<td class="2-td2-left-qysds1" nowrap>减：纳税调整减少额（请填附表五）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='15_1' value='' size='13' tabindex='1'></td>
							<td class="2-td2-left" nowrap>35 <input type="hidden" name="hc"
								value="35" id="hc_35"></td>
							<td class="2-td2-left-qysds1" nowrap>附：上年应缴未缴本年入库所得税额</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='35_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>16 <input type="hidden" name="hc"
								value="16" id="hc_16"></td>
							<td class="2-td2-left-qysds1" nowrap>纳税调整后所得（13+14－15)</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='16_1' value='' size='13' tabindex='1'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>17 <input type="hidden" name="hc"
								value="17" id="hc_17"></td>
							<td class="2-td2-left-qysds1" nowrap>减：弥补以前年度亏损（填附表六）（17≤16）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='17_1' value='' size='13' tabindex='1'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>18 <input type="hidden" name="hc"
								value="18" id="hc_18"></td>
							<td class="2-td2-left-qysds1" nowrap>减：免税所得（请填附表七）（18≤16－17）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='18_1' value='' size='13' tabindex='1'></td>
						</tr>
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center"><a style="cursor:hand" id="next"
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
