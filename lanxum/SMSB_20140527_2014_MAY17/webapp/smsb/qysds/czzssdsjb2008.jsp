<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsjb2008.web.CzzssdsjbForm"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>

<html:html>
<head>
<title>企业所得税月(季)度预缴纳税申报表(A类)</title>
<!-- modify  20140829 -->
<html:base/>
<!-- end 20140829 -->
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
<script language=JavaScript type="text/JavaScript"
	src="../../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/qysdsnew.js"></script>
<style>
input {
    font-size: 9pt;
    text-align: right;
}
.inputalignright{
    font-size: 9pt;
    text-align: right;
}
.inbright {
    font-size: 9pt;
    background :#F3F5F8;
	text-align: right;
    background-color: #F3F5F8;
    border: 0px;
}
</style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInit()">
<%@include file="../include/header.jsp"%>
<html:form method="POST" action="/webapp/smsb/qysds/czzssdsjb2008Action.do">
	<html:hidden property="actionType" />
	<html:hidden property="nsrsbh" />
	<html:hidden property="zgswjgzzjgmc" />
	<html:hidden property="swjgzzjgdm" />
	<html:hidden property="nsrmc" />
	<html:hidden property="lxdh" />
	<html:hidden property="jydz" />
	<html:hidden property="sknd" />
	<html:hidden property="qh" />
	<html:hidden property="bbqlx" />
	<html:hidden property="qxdm" />
	<html:hidden property="swjsjdm" />
	<html:hidden property="qyzslx" />
	<html:hidden property="cyl" />
	<html:hidden property="dezsse" />
	<html:hidden property="jmzg" />
	<html:hidden property="ybjmsl" />
	<html:hidden property="sysl" />
	<html:hidden property="zsfs"/>
	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="jsjdmQuery()">
		<tr>
			<td class="1-td1" colspan="7">
				中华人民共和国<br>企业所得税月(季)度预缴纳税申报表(A类)
			</td>
		</tr>

		<tr>
			<td class="1-td2" colspan="7"><br>
			<table cellspacing="0" class="table-99">

				<tr class="black9">
					<td align="center" nowrap>
					<div align="left">申报日期 <html:text tabindex="1" property="sbrq" size="11"
						maxlength="8" readonly='true' style='text-align:right'
						onchange="checkZQ(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,3)" /></div>
					</td>
					<td wigth="90%" align="center" nowrap>税款所属日期： <html:text tabindex="2" property="skssksrq"
						size="15" maxlength="8"
						onchange="isDate(this,false);compareDate(this)"  style="text-align:left" /> 至 <html:text tabindex="3"
						property="skssjsrq" size="15" maxlength="8"
						onchange="isDate(this,false);compareDate(this)" style="text-align:left" /></td>
					<td align="right" nowrap>
					<div align="right">金额单位：元（列至角分）</div>
					</td>
				</tr>
			</table>
			<table class="table-99" border="0" cellpadding="0" cellspacing="0" width="80%">
				<tr>
					<td width="16%" nowrap class="2-td2-t-left"><div align="center">纳税人地税计算机代码&nbsp;</div></td>
					<td colspan="3" nowrap class="2-td2-t-center" align=left><div align="left">&nbsp;&nbsp;<html:text tabindex="4" property="jsjdm" maxlength="8" size="15" onchange="doQuery()" style="text-align:left"/></div></td>
				</tr>
				<TR>
					<td width="16%" nowrap class="2-td2-left"><div align="center">纳税人识别号&nbsp;</div></td>
					<td colspan="3" width="30%" nowrap class="2-td2-center" align="left"><div align="left">&nbsp;<bean:write name="czzssdsjb2008Form"
						property="nsrsbh" scope="request" filter="true" /></div></td>
				</tr>
				<TR>
					<td  width="16%" nowrap class="2-td2-left"><div align="center">纳税人名称&nbsp;</div></td>
					<td colspan="3" nowrap class="2-td2-center" align="left"><div align="left">&nbsp; <bean:write
						name="czzssdsjb2008Form" property="nsrmc" scope="request"
						filter="true" /></div></td>
				</tr>
			</table>
			<br>
			<table class="table-99" border="0" cellpadding="0" cellspacing="0" width="80%">
				<tr>
					<td nowrap class="2-td2-t-left">纳税方法：</td>
                    <td nowrap class="2-td2-t-left">&nbsp;<input type='radio' name='lje_nsfs'
								style='text-align:right' value='<%=CodeConstant.HZNSFF_QYSDSJB2008_CZZSSDS_DLNS%>' size='13'
								onclick="compute_Row_1()" onchange="compute_Row_1()" checked>独立纳税&nbsp;<input type='radio' name='lje_nsfs'
								style='text-align:right' value='<%=CodeConstant.HZNSFF_QYSDSJB2008_CZZSSDS_HZNS%>' size='13'
								onclick="compute_Row_1()" onchange="compute_Row_1()">汇总纳税
					</td>
                    <td class="2-td2-t-center" nowrap><div align="left">&nbsp;<input type='radio' name='lje_zfjg'
								style='text-align:right' value='<%=CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_ZJG%>' size='13'
								onclick="compute_Row_2()" onchange="compute_Row_2()" disabled="true">总机构<br>
								<input type='radio' name='lje_zfjg'
								style='text-align:right' value='<%=CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_FZJG%>' size='13'
								onclick="compute_Row_2()" onchange="compute_Row_2()" disabled="true">分支机构</div></td>
				</tr>
			</table>
			<TABLE class="table-99" align="center">
			<TR>					
            <TD> 
              <div id="Layer2" style="position:static;">
					
                <table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
                  <tr> 
                    <td width="52" nowrap class="2-td1-left"><div align="center">行次</div></td>
                    <td colspan="2" nowrap class="2-td1-left"><div align="center">项目</div></td>
                    <td width="180" nowrap class="2-td1-left">本期金额</td>
                    <td width="180" nowrap class="2-td1-center">累计金额</td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left"><div align="center">1</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>一、据实预缴</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">2
							<input type="hidden" name="hc" value="3" id="hc_3">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">营业收入</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="5" type='text' name='lje' 
							id='3_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_3()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">3
							<input type="hidden" name="hc" value="4" id="hc_4">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">营业成本</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="6" type='text' name='lje' 
							id='4_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_4()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">4
							<input type="hidden" name="hc" value="5" id="hc_5">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">实际利润额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="7" type='text' name='lje' 
							id='5_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_5()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">5
							<input type="hidden" name="hc" value="6" id="hc_6">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">税率（25%）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input readonly tabindex="8" type='text' name='lje' 
							id='6_1' style='text-align:right' 
							value='25' size='13'
							onblur="formate(this)" onchange="compute_Row_6()">%</td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">6
							<input type="hidden" name="hc" value="7" id="hc_7">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">应纳所得税额（4行×5行）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="9" type='text' name='lje' 
							id='7_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_7()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">7
							<input type="hidden" name="hc" value="8" id="hc_8">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">减免所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="10" type='text' name='lje' 
							id='8_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_8()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">8
							<input type="hidden" name="hc" value="9" id="hc_9">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">实际已缴所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="11" type='text' name='lje' 
							id='9_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_9()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">9
							<input type="hidden" name="hc" value="10" id="hc_10">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">应补（退）的所得税额（6行-7行-8行）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="12" type='text' name='lje' 
							id='10_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_10()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">10
						</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>二、按照上一纳税年度应纳税所得额的平均额预缴</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">11
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">上一纳税年度应纳税所得额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">12
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">本月（季）应纳税所得额（11行÷12或11行÷4）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">13
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">税率(25%)</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">14
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">本月（季）应纳所得税额（12行×13行）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">15
						</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>三、按照税务机关确定的其他方法预缴</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">16
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">本月（季）应纳所得税额（12行×13行）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">17
						</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="center"><B>总分机构纳税人</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">18
							<input type="hidden" name="hc" value="16" id="hc_16">
						</div></td>
                    <td rowspan="4" width="20%" class="2-td2-left"><div align="center">总机构<br>（注：当总分机构税率不一致时，18-20行公式不成立。）</div></td>
                    <td nowrap class="2-td2-left"><div align="left">总机构应分摊的所得税额（9行或14行或16行×25%）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="13" type='text' name='lje' 
							id='16_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_16()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">19
							<input type="hidden" name="hc" value="17" id="hc_17">
						</div></td>
                    <td nowrap class="2-td2-left"><div align="left">中央财政集中分配的所得税额（9行或14行或16行×25%）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="14" type='text' name='lje' 
							id='17_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_17()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">20
							<input type="hidden" name="hc" value="18" id="hc_18">
						</div></td>
                    <td nowrap class="2-td2-left"><div align="left">分支机构分摊的所得税额（9行或14行或16行×50%）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="15" type='text' name='lje' 
							id='18_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_18()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">20.1
							<input type="hidden" name="hc" value="21" id="hc_21">
						</div></td>
                    <td nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：总机构缴纳其独立生产经营部门分摊的所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="16" type='text' name='lje' 
							id='21_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_21()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">21
							<input type="hidden" name="hc" value="19" id="hc_19">
						</div></td>
                    <td rowspan="2" nowrap class="2-td2-left"><div align="center">分支机构</div></td>
                    <td nowrap class="2-td2-left"><div align="left">分配比例</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="17" type='text' name='lje' 
							id='19_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_19()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">22
							<input type="hidden" name="hc" value="20" id="hc_20">
						</div></td>
                    <td nowrap class="2-td2-left"><div align="left">分配的所得税额（20行×21行）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="18" type='text' name='lje' 
							id='20_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_20()"></td>
                  </tr>
                </table>
					</div>
					</TD>
				</TR>
				<BR>
				<BR>
				<BR>
				<BR>
				<TR class="black9">
					<TD>
					<div align="center"><input type="image" accesskey="q"
						onClick="doQuery();return false;"
						onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)"
						onMouseOut="MM_swapImgRestore()" value="查询"
						src="<%=static_contextpath%>images/cx-q1.jpg " width="79"
						height="22" id="chaxun" style="cursor:hand">
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="u"
						style="cursor:hand" onClick="doReset();return false;"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" name="Image13"
						width="79" height="22" border="0" id='qingchu'>
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="s"
						style="cursor:hand" onClick="doSave();return false;"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" name="Image13"
						width="79" height="22" border="0" id='baocun'>

					&nbsp;&nbsp;&nbsp;&nbsp;<input type="image" accesskey="x"
						style="cursor:hand" onClick="doDelete();return false;"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" name="Image13"
						width="79" height="22" border="0" id='shanchu'>
					&nbsp;&nbsp;&nbsp;&nbsp;<input type="image" accesskey="c"
						onClick="tuichu();return false;"
						onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" value="退出" id="tc1"
						src="<%=static_contextpath%>images/tc-c1.jpg" width="79"
						height="22" style="cursor:hand"></div>
					<BR>
					</TD>
				</TR>
			</TABLE>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>

	
<table width="100%" height="33" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td background="<%=static_contextpath%>images/q-bottom-bg-01.jpg" height="9px" colspan="2" nowrap></td>
  </tr>
  <tr>
    <td nowrap><font size="2">&nbsp;&nbsp;&nbsp;* 必须使用 IE 5.5 以上，分辨率 800*600 浏览本网站</font></td>
    <td height="24" align="right"><img src="<%=static_contextpath%>images/q-bottom-tu-01.jpg" alt="承办单位：清华同方软件股份有限公司2007_OCT24" width="184" height="24"></td>
  </tr>
</table>

</html:form>
<script language="JavaScript">
	<%/*初始化*/%>	
	function doInit()	
	{
		//alert("初始化...");
		<%
		CzzssdsjbForm jbForm=(CzzssdsjbForm)request.getAttribute("czzssdsjb2008Form");
		if (jbForm!=null && jbForm.getQysdsjbList()!=null && jbForm.getQysdsjbList().size()>0){
			for(int i=0;i<jbForm.getQysdsjbList().size();i++){
				HashMap map=(HashMap)jbForm.getQysdsjbList().get(i);
				int hc=Integer.parseInt((String)map.get("hc"));
				String lje=(String)map.get("lje");
				if("null".equals(lje)){
					lje="0.00";
				}
				if(lje==null){
					lje="0.00";
				}
				if(hc==1){
					%>			
					for(ii=0;ii<document.forms[0].lje_nsfs.length;ii++){
						if(document.forms[0].lje_nsfs[ii].value==<%=lje%>){
							document.forms[0].lje_nsfs[ii].checked=1;
						}
					}
					<% 
				}else if(hc==2){
					%>		
					for(ii=0;ii<document.forms[0].lje_zfjg.length;ii++){
						if(document.forms[0].lje_zfjg[ii].value==<%=lje%>){
							document.forms[0].lje_zfjg[ii].checked=1;
						}
					}
					<% 
				}else if(hc==6){
					%>		
					//强制赋值25
					document.getElementById('6_1').value="25";
					<% 
				}else{
					%>				
					obj = eval("document.getElementById('<%=hc%>_1')");
					obj.value = '<%=lje%>';
					<% 
				}
			}
		}
		%>
		<%
		System.out.println("存储标记="+jbForm.getSAVE_FLAG());
		if("1".equals(jbForm.getSAVE_FLAG())){
		%>
			if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
				//保存成功，提示转总分机构申报表
				//if(confirm("保存成功，是否转入《企业所得税汇总纳税分支机构分配表》？")){
					alert("保存成功，请继续填写《企业所得税汇总纳税分支机构分配表》！");
					jumpToZFJG();
				//}
			}else if(document.forms[0].lje_nsfs[0].checked==1){
				alert("申报已成功！");
			}
		<%
		}
		%>
		changeSelect();
		checkFilter();
		readOnlyFilter();
		document.forms[0].jsjdm.focus();

	}
		<%/*响应计算机代码的回车查询*/%>
	function jsjdmQuery(){
		if(event.keyCode==13) event.keyCode = 9;
	}
	
	//设置输入计算机代码时的焦点变化
	function setFoucs(num){
		if(document.forms[0].nsrmc.value==""){
			document.forms[0].jsjdm.focus();
		}else{
			document.getElementById(num+'_1').focus();
		}	
	}
		<%/*查询*/%>
	function doQuery(){
		var jsjdm;
		jsjdm = document.forms[0].jsjdm.value;
		if(jsjdm==""){
		    alert("计算机代码不允许是空！");
		    return false;
	  	}else{
		    doSubmitForm("Query");
	    	return false;
	  	}
	}
	
	
	<%/*保存*/%>
	function doSave()
	{
		if(document.forms[0].nsrmc.value==""){
			alert("基本信息不正确,保存失败,请重新输入!");
			document.forms[0].jsjdm.focus();
			return false;
		}
		//if(!saveCheckNull("1_1")){return false;}		
		//if(!saveCheckNull("2_1")){return false;}	
			
		if(!saveCheckFilter()){//alert("数据不正确，无法保存！");
		return false;}
		doSubmitForm('Save');	
		
	}

	function saveCheckNull(id){
		obj=document.getElementById(id);
		if(obj.value==""){
			obj.focus();
			alert("该选项不能为空!");
			return flase;
		}
		return true;
	}
	
	<%/*保存时对第1、14行的数据进行校验*/%>
	function saveCheck(row,zero){
		
		if(!isNum(document.getElementById(row+'_1'), zero, 9999999999999, null, 16, 2)){
			return false;			
		}
		return true;	
	}
			//判断比较税款所属日期
	function compareDate(obj){
		
		var strDate1 = document.forms[0].skssksrq.value;		
		var strYear1 = strDate1.substr(0,4);		
		var strMonth1 = strDate1.substr(4,2);
 	 	var strDay1 = strDate1.substr(6,2);
 	 	
 	 	var strDate2 = document.forms[0].skssjsrq.value;
 	 	var strYear2 = strDate2.substr(0,4);		
		var strMonth2 = strDate2.substr(4,2);
 	 	var strDay2 = strDate2.substr(6,2);
 	 	
 	 	var sksbrq = document.forms[0].sbrq.value;
 	 	var strYear3 = sksbrq.substr(0,4);		
		var strMonth3 = sksbrq.substr(4,2);
 	 	var strDay3 = sksbrq.substr(6,2); 
 	 	
  	var objDate1 = new Date(strMonth1+'-'+strDay1+'-'+strYear1);
  	var objDate2 = new Date(strMonth2+'-'+strDay2+'-'+strYear2);
  	var objDate3 = new Date(strMonth3+'-'+strDay3+'-'+strYear3);
  	
  	if(objDate2>objDate3){
  		alert("税款结束时间不能大于申报日期");
  		window.event.returnValue=false;
			document.forms[0].skssjsrq.focus();
			document.forms[0].skssjsrq.select();
			return false;
  	}
  	  	
  		if(objDate1>=objDate2){
			if(obj == document.forms[0].skssjsrq){
				alert("税款结束时间不能小于或等于税款开始时间");
			}else{
				alert("税款开始时间不能大于或等于税款结束时间");	
			}
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;
		}
		
		if(strYear1!=strYear2){
			alert("税款开始时间与税款结束时间的年份不同，税款不能跨年！");
			return false;				
		}
		
	}
	
		<%/*判断比较税款所属日期*/%>
	function compareDate2Save(obj){
	
		var strDate1 = document.forms[0].skssksrq.value;		
		var strYear1 = strDate1.substr(0,4);		
		var strMonth1 = strDate1.substr(4,2);
 	 	var strDay1 = strDate1.substr(6,2);
 	 	
 	 	var strDate2 = document.forms[0].skssjsrq.value;
 	 	var strYear2 = strDate2.substr(0,4);		
		var strMonth2 = strDate2.substr(4,2);
 	 	var strDay2 = strDate2.substr(6,2);
 	 	
 	 	var sksbrq = document.forms[0].sbrq.value;
 	 	var strYear3 = sksbrq.substr(0,4);		
		var strMonth3 = sksbrq.substr(4,2);
 	 	var strDay3 = sksbrq.substr(6,2); 
 	 	
  	var objDate1 = new Date(strMonth1+'-'+strDay1+'-'+strYear1);
  	var objDate2 = new Date(strMonth2+'-'+strDay2+'-'+strYear2);
  	var objDate3 = new Date(strMonth3+'-'+strDay3+'-'+strYear3);
  	
  	if(objDate2>objDate3){
  		alert("税款结束时间不能大于当前日期");
  		window.event.returnValue=false;
			document.forms[0].skssjsrq.focus();
			document.forms[0].skssjsrq.select();
			return false;
  	}
  		if(objDate1>=objDate2){
			if(obj == document.forms[0].skssjsrq){
				alert("税款结束时间不能小于或等于税款开始时间");
			}else{
				alert("税款开始时间不能大于或等于税款结束时间");	
			}
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;
		}
		
		if(strYear1!=strYear2){
			alert("税款开始时间与税款结束时间的年份不同，税款不能跨年！");
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;				
		}
		
		return true;
			
	}

	<%/*根据选择设置单选框选择情况*/%>
	function changeSelect()
	{
		if(document.forms[0].lje_nsfs[0].checked == 1)
		{
			//选择为独立纳税的，清除汇总纳税方式子项选择，同时将这些子项置位不可编辑
			document.forms[0].lje_zfjg[0].checked = false;
			document.forms[0].lje_zfjg[0].disabled = true;
			document.forms[0].lje_zfjg[1].checked = false;
			document.forms[0].lje_zfjg[1].disabled = true;
		}
		else if(document.forms[0].lje_nsfs[1].checked == 1)
		{
			if(document.forms[0].lje_zfjg[0].checked==0&&document.forms[0].lje_zfjg[1].checked==0){
				document.forms[0].lje_zfjg[0].checked = true;
				document.forms[0].lje_zfjg[1].checked = false;
			}
			//选择为总机构时，开发其子项选择权限
			document.forms[0].lje_zfjg[0].disabled = false;
			document.forms[0].lje_zfjg[1].disabled = false;
		}
		//readOnlyFilter();
	}

	<%/*计算第1行数据*/%>
	function compute_Row_1(){
		//alert("document.forms[0].jsjdm.value="+document.forms[0].jsjdm.value+"|");
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
		}
		//document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1
		changeSelect();
		checkFilter();
		readOnlyFilter();
	}
	<%/*计算第2行数据*/%>
	function compute_Row_2(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
		}
		changeSelect();
		checkFilter();
		readOnlyFilter();
	}
	<%/*计算第3行数据*/%>
	function compute_Row_3(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('3_1').value="0.00";
		}          

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}

		var obj_input=document.getElementById('3_1');
		//if(!(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1)){
		//	if((obj_input.value)*1<0){
		//		obj_input.value="0.00";
		//	}
		//	if(obj_input.value==""){
		//		obj_input.value="0.00";
		//	}
		//}
	}
	<%/*计算第4行数据*/%>
	function compute_Row_4(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('4_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('4_1');
		//if(!(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1)){
		//	if((obj_input.value)*1<0){
		//		obj_input.value="0.00";
		//	}
		//	if(obj_input.value==""){
		//		obj_input.value="0.00";
		//	}
		//}
	}
	<%/*计算第5行数据*/%>
	function compute_Row_5(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('5_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('5_1');
		//if(!(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1)){
		//	if((obj_input.value)*1<0){
		//		obj_input.value="0.00";
		//	}
		//	if(obj_input.value==""){
		//		obj_input.value="0.00";
		//	}
		//}
		//
		compute_Row_7();
	}
	<%/*计算第6行数据*/%>
	function compute_Row_6(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('6_1').value="25";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('6_1');
		//if(!(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1)){
			if((obj_input.value)*1<0){
				obj_input.value="0.00";
			}
			if(obj_input.value==""){
				obj_input.value="0.00";
			}
		//}
		//
		compute_Row_7();
	}
	<%/*计算第7行数据*/%>
	function compute_Row_7(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('7_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('7_1');
		//if(!(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1)){
			if((obj_input.value)*1<0){
				obj_input.value="0.00";
			}
			if(obj_input.value==""){
				obj_input.value="0.00";
			}
		//}
		//
		document.getElementById('7_1').value=((1*document.getElementById('6_1').value/100)*document.getElementById('5_1').value).toFixed(2);
			if((obj_input.value)*1<0){
				obj_input.value="0.00";
			}
			if(obj_input.value==""){
				obj_input.value="0.00";
			}
		//
		//if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if((1*obj_input.value)<(1*document.getElementById('8_1').value)){
				alert("减免税额（7行）应小于等于应纳所得税额（6行）,且应大于等于0！");
				getCellObject().focus();
			}
			if(1*document.getElementById('8_1').value<0){
				alert("减免税额（7行）应大于等于0");
				getCellObject().focus();
			}
		//}
		//
		compute_Row_10();
	}
	<%/*计算第8行数据*/%>
	function compute_Row_8(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('8_1').value="0.00";
		}   
        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('8_1');
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//
		//if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if((1*obj_input.value)>(1*document.getElementById('7_1').value)){
				alert("减免税额（7行）应小于等于应纳所得税额（6行）,且应大于等于0！");
				document.getElementById('8_1').select();
				document.getElementById('8_1').focus();
				return false;
			}
			if(1*obj_input.value<0){
				alert("减免税额（7行）应大于等于0!");
				document.getElementById('8_1').select();
				document.getElementById('8_1').focus();
				return false;
			}
		//}
		//
		compute_Row_10();
	}
	<%/*计算第9行数据*/%>
	function compute_Row_9(){
		//alert("compute_Row_9.."+document.getElementById('16_1').disabled);
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('9_1').value="0.00";
		}   
		//alert("1.."+document.getElementById('16_1').disabled);

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		//alert("2.."+document.getElementById('16_1').disabled);
		var obj_input=document.getElementById('9_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//alert("3.."+document.getElementById('16_1').disabled);
		if((obj_input.value)*1<0){
			//obj_input.value="0.00";
			alert("第8行（实际已缴所得税额）应不小于0!");
			document.getElementById('9_1').select();
			document.getElementById('9_1').focus();
			return false;
		}
		//alert("4.."+document.getElementById('16_1').disabled);
		//
		compute_Row_10();
		//alert("5.."+document.getElementById('16_1').disabled);
	}
	<%/*计算第10行数据*/%>
	function compute_Row_10(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('10_1').value="0.00";
		}		   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('10_1');
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//计算行次10的域值
		document.getElementById('10_1').value=(1*document.getElementById('7_1').value-1*document.getElementById('8_1').value-document.getElementById('9_1').value).toFixed(2);
		if((1*document.getElementById('10_1').value)<0){
			document.getElementById('10_1').value="0.00";
		}
		//表头纳税方法选项定义为独立纳税时，系统仅开放本表第2-9行，第10至22行屏蔽
		if(document.forms[0].lje_nsfs[0].checked==1){
			//do nothing
		
		}
		else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1)
		{
			/**
			 * 总局新需求，取消18-20行自动计算
			 * modify by tum at 2008-9-23
			 */
			//当表头纳税方法选项定义为汇总纳税-总机构时开放本表第2-9、18-20行，其余行次屏蔽
			//document.getElementById('16_1').value=((document.getElementById('10_1').value)*0.25).toFixed(2)+"";
			//document.getElementById('17_1').value=((document.getElementById('10_1').value)*0.25).toFixed(2)+"";
			//document.getElementById('18_1').value=((document.getElementById('10_1').value)*0.50).toFixed(2)+"";
		//当表头纳税方法选项定义为汇总纳税-分支机构时开放本表第21、22行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[1].checked==1){			
			//do nothing
		}else{		
			//do nothing
		}
	}

	function compute_Row_10_1(){
		var obj_input=document.getElementById('10_1');
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//计算行次10的域值
		document.getElementById('10_1').value=(1*document.getElementById('7_1').value-1*document.getElementById('8_1').value-document.getElementById('9_1').value).toFixed(2);
		if((1*document.getElementById('10_1').value)<0){
			document.getElementById('10_1').value="0.00";
		}
		//表头纳税方法选项定义为独立纳税时，系统仅开放本表第2-9行，第10至22行屏蔽
		if(document.forms[0].lje_nsfs[0].checked==1){
			//do nothing
		
		}
		//当表头纳税方法选项定义为汇总纳税-总机构时开放本表第2-9、18-20行，其余行次屏蔽
		else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1)
		{
			/*
			 * 总局新需求，18-20行自行填写。
			 * modify by tum at 2008-9-23
			 */
			//document.getElementById('16_1').value=((document.getElementById('10_1').value)*0.25).toFixed(2)+"";
			//document.getElementById('17_1').value=((document.getElementById('10_1').value)*0.25).toFixed(2)+"";
			//document.getElementById('18_1').value=((document.getElementById('10_1').value)*0.50).toFixed(2)+"";
		//当表头纳税方法选项定义为汇总纳税-分支机构时开放本表第21、22行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[1].checked==1){			
			//do nothing
		}else{		
			//do nothing
		}
	}

	<%/*计算第16行数据*/%>
	function compute_Row_16(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('16_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('16_1');
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
	}
	<%/*计算第17行数据*/%>
	function compute_Row_17(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('17_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('17_1');
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
	}
	<%/*计算第18行数据*/%>
	function compute_Row_18(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('18_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('18_1');
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
	}
	<%/*计算第19行数据*/%>
	function compute_Row_19(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('19_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('19_1');
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
	}
	<%/*计算第20行数据*/%>
	function compute_Row_20(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('20_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('20_1');
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
	}
		<%/*计算第20.1行数据*/%>
	function compute_Row_21(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('21_1').value="0.00";
		}   
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
        //判断输入的数据是否符合要求
		var obj_input=document.getElementById('21_1');
		if((obj_input.value)*1<0){
			alert("总机构缴纳其独立生产经营部门分摊的所得税额（20.1行）应不小于0，且小于等于分支机构分摊的所得税额（20行）");
			window.event.returnValue=false;
			document.getElementById('21_1').focus();
			document.getElementById('21_1').select();
			return false;
		}
		if(obj_input.value*1>document.getElementById('18_1').value){
			alert("总机构缴纳其独立生产经营部门分摊的所得税额（20.1行）应不小于0，且小于等于分支机构分摊的所得税额（20行）");
			window.event.returnValue=false;
			document.getElementById('21_1').focus();
			document.getElementById('21_1').select();
			return false;
		}
	}

		<%/*清除*/%>
	function doReset()
	{
		
		if(confirm("确认是否清除当前数据？"))
		{	   		
	   			for(var i=3;i<22;i++){
	   				obj = document.getElementById(i+"_1");
					if(obj!=null){
						obj.value = "0.00";
					}
	   			}
			document.getElementById("6_1").value="25";
	   		document.forms[0].jsjdm.focus();
			document.forms[0].jsjdm.select();
		}
	}
		<%/*删除*/%>
	function doDelete()
	{
		if(document.forms[0].jsjdm.value==""){
			alert("基本信息不正确,删除失败,请重新输入!");
			return false;
		}
		doSubmitForm('Delete');
	}
	
		//如果申报月份不属于企业所得税季度征期4、7、10则提示操作人员
	function checkZQ(sbrq,ksrq,jzrq,lx){
		if(!isDate(sbrq,"v")) return;
		getStartEndDate1(sbrq,ksrq,jzrq,lx);
		var inputDate = sbrq.value;
		mon = inputDate.substring(4,6);
		if(mon!='01' && mon!='04' && mon!='07' && mon!='10'){
			alert('注意：'+inputDate+'不属于征期。');
		}
	}

	

	function saveCheckFilter(){
		//
		if(document.forms[0].lje_nsfs[1].checked==1){
			if((document.forms[0].lje_zfjg[0].checked==0)&&(document.forms[0].lje_zfjg[1].checked==0)){
				alert("请选择总分机构类型！");
				return false;
			}
		}
		//alert("saveCheckFilter...");
		//表头纳税方法选项定义为独立纳税时，系统仅开放本表第2-9行，第10至22行屏蔽
		if(document.forms[0].lje_nsfs[0].checked==1){
			var value_3=(document.getElementById('3_1').value)*1;
			var value_4=(document.getElementById('4_1').value)*1;
			var value_5=(document.getElementById('5_1').value)*1;
			var value_6=((document.getElementById('6_1').value)*1)/100;
			var value_7=(document.getElementById('7_1').value)*1;
			var value_8=(document.getElementById('8_1').value)*1;
			var value_9=(document.getElementById('9_1').value)*1;
			var value_10=(document.getElementById('10_1').value)*1;
			
			if(!isNum(document.getElementById('3_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('4_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('5_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('8_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('9_1'), null, null, null, null, 2)){
				return false;			
			}
			
			//alert("1");
			if((value_5*value_6).toFixed(2)>=0){
				if(!((value_7==(value_5*value_6).toFixed(2))&&(value_7>=0))){
				alert("第6行\"应纳所得税额\"＝第4行×第5行，且第6行≥0");
				return false;
				}
			}
			//if(!((value_7==(value_5*value_6))&&(value_7>=0))){
			//	alert("第6行\"应纳所得税额\"＝第4行×第5行，且第6行≥0");
			//	return false;
			//}
			//alert("2");
			if(!((((value_5*value_6).toFixed(2)<0)&&(value_7==0))||((value_5*value_6).toFixed(2)>=0))){				
				alert("若第4行×第5行﹤0，则第6行=0。");
				return false;
			}
			//alert("3");
			if(!((value_8<=value_7)&&(value_8>=0))){
				alert("第7行\"减免所得税额\"≤第6行，且第7行≥0。");
				document.getElementById('8_1').select();
				document.getElementById('8_1').focus();
				return false;
			}			
			//alert("3");
			if(value_9<0){
				alert("第8行\"实际已缴所得税额所得税额\"应≥0。");
				document.getElementById('9_1').select();
				document.getElementById('9_1').focus();
				return false;
			}
			//alert("4");
			if((value_7*1-value_8*1-value_9*1).toFixed(2)>=0){
				if(!((value_10*1)==(value_7*1-value_8*1-value_9*1).toFixed(2))){
					alert("第9行\"应补（退）所得税额\" ＝第6行-第7行-第8行。");
					return false;
				}
			}
			//alert("5");

		//当表头纳税方法选项定义为汇总纳税-总机构时开放本表第2-9、18-20行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			//alert("6");
			var value_3=(document.getElementById('3_1').value)*1;
			var value_4=(document.getElementById('4_1').value)*1;
			var value_5=(document.getElementById('5_1').value)*1;
			var value_6=((document.getElementById('6_1').value)*1)/100;
			var value_7=(document.getElementById('7_1').value)*1;
			var value_8=(document.getElementById('8_1').value)*1;
			var value_9=(document.getElementById('9_1').value)*1;
			var value_10=(document.getElementById('10_1').value)*1;
			
			if(!isNum(document.getElementById('3_1') , null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('4_1') , null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('5_1') , null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('8_1') , null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('9_1') , null, null, null, null, 2)){
				return false;			
			}
			
			//alert("1");
			if((value_5*value_6).toFixed(2)>=0){
				if(!((value_7==(value_5*value_6).toFixed(2))&&(value_7>=0))){
				alert("第6行\"应纳所得税额\"＝第4行×第5行，且第6行≥0");
				return false;
				}
			}
			//if(!((value_7==(value_5*value_6))&&(value_7>=0))){
			//	alert("第6行\"应纳所得税额\"＝第4行×第5行，且第6行≥0");
			//	return false;
			//}
			//alert("2");
			if(!((((value_5*value_6).toFixed(2)<0)&&(value_7==0))||((value_5*value_6).toFixed(2)>=0))){				
				alert("若第4行×第5行﹤0，则第6行=0。");
				return false;
			}
			//alert("3");
			if(!((value_8<=value_7)&&(value_8>=0))){
				alert("第7行\"减免所得税额\"≤第6行，且第7行≥0。");
				document.getElementById('8_1').select();
				document.getElementById('8_1').focus();
				return false;
			}
			//alert("3");
			if(value_9<0){
				alert("第8行\"实际已缴所得税额所得税额\"应≥0。");
				document.getElementById('9_1').select();
				document.getElementById('9_1').focus();
				return false;
			}
			//alert("3");
			if(value_8<0){
				alert("第7行\"减免所得税额\"应≥0。");
				document.getElementById('8_1').select();
				document.getElementById('8_1').focus();
				return false;
			}
			//alert("4");
			if((value_7*1-value_8*1-value_9*1).toFixed(2)>=0){
				if(!((value_10*1)==(value_7*1-value_8*1-value_9*1).toFixed(2))){
					alert("第9行\"应补（退）所得税额\" ＝第6行-第7行-第8行。");
					return false;
				}
			}
			//判断第20.1行输入的数据是否符合要求
			var obj_input=document.getElementById('21_1');
			if(!isNum(obj_input , null, null, null, null, 2)){
				return false;                         
			}
		
			if((obj_input.value)*1<0){
				alert("总机构缴纳其独立生产经营部门分摊的所得税额（20.1行）应不小0，且小于等于分支机构分摊的所得税额（20行）");
				window.event.returnValue=false;
				document.getElementById('21_1').focus();
				document.getElementById('21_1').select();
				return false;
			}
			if(obj_input.value*1>document.getElementById('18_1').value){
				alert("总机构缴纳其独立生产经营部门分摊的所得税额（20.1行）应不小0，且小于等于分支机构分摊的所得税额（20行）");
				window.event.returnValue=false;
				document.getElementById('21_1').focus();
				document.getElementById('21_1').select();
				return false;
			}
			//alert("5");
		//当表头纳税方法选项定义为汇总纳税-分支机构时开放本表第21、22行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[1].checked==1){			
			//alert("7");
			if(!isNum(document.getElementById('19_1') , null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('20_1') , null, null, null, null, 2)){
				return false;			
			}
			
		}else{		
			
			//alert("8");
		}
		return true;
	}

	function checkFilter(){
		//表头纳税方法选项定义为独立纳税时，系统仅开放本表第2-9行，第10至22行屏蔽
		if(document.forms[0].lje_nsfs[0].checked==1){
			document.getElementById('6_1').value="25";
			document.getElementById('6_1').readOnly=true;

			

			document.getElementById('3_1').disabled=false;
			document.getElementById('4_1').disabled=false;
			document.getElementById('5_1').disabled=false;
			document.getElementById('6_1').disabled=false;
			document.getElementById('7_1').disabled=false;
			document.getElementById('8_1').disabled=false;
			document.getElementById('9_1').disabled=false;
			document.getElementById('10_1').disabled=false;
			document.getElementById('16_1').disabled=true;
			document.getElementById('17_1').disabled=true;
			document.getElementById('18_1').disabled=true;
			document.getElementById('19_1').disabled=true;
			document.getElementById('20_1').disabled=true;
			document.getElementById('21_1').disabled=true;

			//将被屏蔽行次值清空
			if(document.getElementById('3_1').value == null || document.getElementById('3_1').value == "")
			{
				document.getElementById('3_1').value="0.00";
			}
			if(document.getElementById('4_1').value == null || document.getElementById('4_1').value == "")
			{
				document.getElementById('4_1').value="0.00";
			}
			if(document.getElementById('5_1').value == null || document.getElementById('5_1').value == "")
			{
				document.getElementById('5_1').value="0.00";
			}
			if(document.getElementById('6_1').value == null || document.getElementById('6_1').value == "")
			{
				document.getElementById('6_1').value="0.00";
			}
			if(document.getElementById('7_1').value == null || document.getElementById('7_1').value == "")
			{
				document.getElementById('7_1').value="0.00";
			}
			if(document.getElementById('8_1').value == null || document.getElementById('8_1').value == "")
			{
				document.getElementById('8_1').value="0.00";
			}
			if(document.getElementById('9_1').value == null || document.getElementById('9_1').value == "")
			{
				document.getElementById('9_1').value="0.00";
			}
			if(document.getElementById('10_1').value == null || document.getElementById('10_1').value == "")
			{
				document.getElementById('10_1').value="0.00";
			}
			document.getElementById('16_1').value="";
			document.getElementById('17_1').value="";
			document.getElementById('18_1').value="";
			document.getElementById('19_1').value="";
			document.getElementById('20_1').value="";
			document.getElementById('21_1').value="";

			document.getElementById('3_1').style.backgroundColor="";
			document.getElementById('4_1').style.backgroundColor="";
			document.getElementById('5_1').style.backgroundColor="";
			document.getElementById('6_1').style.backgroundColor="";
			document.getElementById('7_1').style.backgroundColor="";
			document.getElementById('8_1').style.backgroundColor="";
			document.getElementById('9_1').style.backgroundColor="";
			document.getElementById('10_1').style.backgroundColor="";
			document.getElementById('16_1').style.backgroundColor="#aaaaaa";
			document.getElementById('17_1').style.backgroundColor="#aaaaaa";
			document.getElementById('18_1').style.backgroundColor="#aaaaaa";
			document.getElementById('19_1').style.backgroundColor="#aaaaaa";
			document.getElementById('20_1').style.backgroundColor="#aaaaaa";
			document.getElementById('21_1').style.backgroundColor="#aaaaaa";

			document.getElementById('hc_3').disabled=false;
			document.getElementById('hc_4').disabled=false;
			document.getElementById('hc_5').disabled=false;
			document.getElementById('hc_6').disabled=false;
			document.getElementById('hc_7').disabled=false;
			document.getElementById('hc_8').disabled=false;
			document.getElementById('hc_9').disabled=false;
			document.getElementById('hc_10').disabled=false;
			document.getElementById('hc_16').disabled=true;
			document.getElementById('hc_17').disabled=true;
			document.getElementById('hc_18').disabled=true;
			document.getElementById('hc_19').disabled=true;
			document.getElementById('hc_20').disabled=true;
			document.getElementById('hc_21').disabled=true;
		//当表头纳税方法选项定义为汇总纳税-总机构时开放本表第2-9、18-20行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			document.getElementById('6_1').value="25";
			document.getElementById('6_1').readOnly=true;

			document.getElementById('3_1').disabled=false;
			document.getElementById('4_1').disabled=false;
			document.getElementById('5_1').disabled=false;
			document.getElementById('6_1').disabled=false;
			document.getElementById('7_1').disabled=false;
			document.getElementById('8_1').disabled=false;
			document.getElementById('9_1').disabled=false;
			document.getElementById('10_1').disabled=false;
			document.getElementById('16_1').disabled=false;
			document.getElementById('17_1').disabled=false;
			document.getElementById('18_1').disabled=false;
			document.getElementById('19_1').disabled=true;
			document.getElementById('20_1').disabled=true;
			document.getElementById('21_1').disabled=false;

			//将被屏蔽行次值清空
			if(document.getElementById('3_1').value == null || document.getElementById('3_1').value == "")
			{
				document.getElementById('3_1').value="0.00";
			}
			if(document.getElementById('4_1').value == null || document.getElementById('4_1').value == "")
			{
				document.getElementById('4_1').value="0.00";
			}
			if(document.getElementById('5_1').value == null || document.getElementById('5_1').value == "")
			{
				document.getElementById('5_1').value="0.00";
			}
			if(document.getElementById('6_1').value == null || document.getElementById('6_1').value == "")
			{
				document.getElementById('6_1').value="0.00";
			}
			if(document.getElementById('7_1').value == null || document.getElementById('7_1').value == "")
			{
				document.getElementById('7_1').value="0.00";
			}
			if(document.getElementById('8_1').value == null || document.getElementById('8_1').value == "")
			{
				document.getElementById('8_1').value="0.00";
			}
			if(document.getElementById('9_1').value == null || document.getElementById('9_1').value == "")
			{
				document.getElementById('9_1').value="0.00";
			}
			if(document.getElementById('10_1').value == null || document.getElementById('10_1').value == "")
			{
				document.getElementById('10_1').value="0.00";
			}
			if(document.getElementById('16_1').value == null || document.getElementById('16_1').value == "")
			{
				document.getElementById('16_1').value="0.00";
			}
			if(document.getElementById('17_1').value == null || document.getElementById('17_1').value == "")
			{
				document.getElementById('17_1').value="0.00";
			}
			if(document.getElementById('18_1').value == null || document.getElementById('18_1').value == "")
			{
				document.getElementById('18_1').value="0.00";
			}
			if(document.getElementById('21_1').value == null || document.getElementById('21_1').value == "")
			{
				document.getElementById('21_1').value="0.00";
			}
			document.getElementById('19_1').value="";
			document.getElementById('20_1').value="";

			document.getElementById('3_1').style.backgroundColor="";
			document.getElementById('4_1').style.backgroundColor="";
			document.getElementById('5_1').style.backgroundColor="";
			document.getElementById('6_1').style.backgroundColor="";
			document.getElementById('7_1').style.backgroundColor="";
			document.getElementById('8_1').style.backgroundColor="";
			document.getElementById('9_1').style.backgroundColor="";
			document.getElementById('10_1').style.backgroundColor="";
			document.getElementById('16_1').style.backgroundColor="";
			document.getElementById('17_1').style.backgroundColor="";
			document.getElementById('18_1').style.backgroundColor="";
			document.getElementById('19_1').style.backgroundColor="#aaaaaa";
			document.getElementById('20_1').style.backgroundColor="#aaaaaa";
			document.getElementById('21_1').style.backgroundColor="";
			

			document.getElementById('hc_3').disabled=false;
			document.getElementById('hc_4').disabled=false;
			document.getElementById('hc_5').disabled=false;
			document.getElementById('hc_6').disabled=false;
			document.getElementById('hc_7').disabled=false;
			document.getElementById('hc_8').disabled=false;
			document.getElementById('hc_9').disabled=false;
			document.getElementById('hc_10').disabled=false;
			document.getElementById('hc_16').disabled=false;
			document.getElementById('hc_17').disabled=false;
			document.getElementById('hc_18').disabled=false;
			document.getElementById('hc_19').disabled=true;
			document.getElementById('hc_20').disabled=true;
			document.getElementById('hc_21').disabled=false;
			
		//当表头纳税方法选项定义为汇总纳税-分支机构时开放本表第21、22行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[1].checked==1){	
			document.getElementById('6_1').value="25";
			document.getElementById('6_1').readOnly=true;

			document.getElementById('3_1').disabled=true;
			document.getElementById('4_1').disabled=true;
			document.getElementById('5_1').disabled=true;
			document.getElementById('6_1').disabled=true;
			document.getElementById('7_1').disabled=true;
			document.getElementById('8_1').disabled=true;
			document.getElementById('9_1').disabled=true;
			document.getElementById('10_1').disabled=true;
			document.getElementById('16_1').disabled=true;
			document.getElementById('17_1').disabled=true;
			document.getElementById('18_1').disabled=true;
			document.getElementById('19_1').disabled=false;
			document.getElementById('20_1').disabled=false;
			document.getElementById('21_1').disabled=true;

			//将被屏蔽行次值清空			
			document.getElementById('3_1').value="";
			document.getElementById('4_1').value="";
			document.getElementById('5_1').value="";
			//document.getElementById('6_1').value="";
			document.getElementById('7_1').value="";
			document.getElementById('8_1').value="";
			document.getElementById('9_1').value="";
			document.getElementById('10_1').value="";
			document.getElementById('16_1').value="";
			document.getElementById('17_1').value="";
			document.getElementById('18_1').value="";
			document.getElementById('21_1').value="";
			if(document.getElementById('19_1').value == null || document.getElementById('19_1').value == "")
			{
				document.getElementById('19_1').value="0.00";
			}
			if(document.getElementById('20_1').value == null || document.getElementById('20_1').value == "")
			{
				document.getElementById('20_1').value="0.00";
			}

			document.getElementById('3_1').style.backgroundColor="#aaaaaa";
			document.getElementById('4_1').style.backgroundColor="#aaaaaa";
			document.getElementById('5_1').style.backgroundColor="#aaaaaa";
			document.getElementById('6_1').style.backgroundColor="#aaaaaa";
			document.getElementById('7_1').style.backgroundColor="#aaaaaa";
			document.getElementById('8_1').style.backgroundColor="#aaaaaa";
			document.getElementById('9_1').style.backgroundColor="#aaaaaa";
			document.getElementById('10_1').style.backgroundColor="#aaaaaa";
			document.getElementById('16_1').style.backgroundColor="#aaaaaa";
			document.getElementById('17_1').style.backgroundColor="#aaaaaa";
			document.getElementById('18_1').style.backgroundColor="#aaaaaa";
			document.getElementById('19_1').style.backgroundColor="";
			document.getElementById('20_1').style.backgroundColor="";
			document.getElementById('21_1').style.backgroundColor="#aaaaaa";

			document.getElementById('hc_3').disabled=true;
			document.getElementById('hc_4').disabled=true;
			document.getElementById('hc_5').disabled=true;
			document.getElementById('hc_6').disabled=true;
			document.getElementById('hc_7').disabled=true;
			document.getElementById('hc_8').disabled=true;
			document.getElementById('hc_9').disabled=true;
			document.getElementById('hc_10').disabled=true;			
			document.getElementById('hc_16').disabled=true;
			document.getElementById('hc_17').disabled=true;
			document.getElementById('hc_18').disabled=true;
			document.getElementById('hc_19').disabled=false;
			document.getElementById('hc_20').disabled=false;
			document.getElementById('hc_21').disabled=true;
		}else{		
			document.getElementById('6_1').value="25";
			document.getElementById('6_1').readOnly=true;

			

			document.getElementById('3_1').disabled=false;
			document.getElementById('4_1').disabled=false;
			document.getElementById('5_1').disabled=false;
			document.getElementById('6_1').disabled=false;
			document.getElementById('7_1').disabled=false;
			document.getElementById('8_1').disabled=false;
			document.getElementById('9_1').disabled=false;
			document.getElementById('10_1').disabled=false;
			document.getElementById('16_1').disabled=false;
			document.getElementById('17_1').disabled=false;
			document.getElementById('18_1').disabled=false;
			document.getElementById('19_1').disabled=false;
			document.getElementById('20_1').disabled=false;
			document.getElementById('21_1').disabled=false;

			document.getElementById('3_1').style.backgroundColor="";
			document.getElementById('4_1').style.backgroundColor="";
			document.getElementById('5_1').style.backgroundColor="";
			document.getElementById('6_1').style.backgroundColor="";
			document.getElementById('7_1').style.backgroundColor="";
			document.getElementById('8_1').style.backgroundColor="";
			document.getElementById('9_1').style.backgroundColor="";
			document.getElementById('10_1').style.backgroundColor="";
			document.getElementById('16_1').style.backgroundColor="#aaaaaa";
			document.getElementById('17_1').style.backgroundColor="#aaaaaa";
			document.getElementById('18_1').style.backgroundColor="#aaaaaa";
			document.getElementById('19_1').style.backgroundColor="#aaaaaa";
			document.getElementById('20_1').style.backgroundColor="#aaaaaa";
			document.getElementById('21_1').style.backgroundColor="#aaaaaa";

			document.getElementById('hc_3').disabled=false;
			document.getElementById('hc_4').disabled=false;
			document.getElementById('hc_5').disabled=false;
			document.getElementById('hc_6').disabled=false;
			document.getElementById('hc_7').disabled=false;
			document.getElementById('hc_8').disabled=false;
			document.getElementById('hc_9').disabled=false;
			document.getElementById('hc_10').disabled=false;
			document.getElementById('hc_16').disabled=false;
			document.getElementById('hc_17').disabled=false;
			document.getElementById('hc_18').disabled=false;
			document.getElementById('hc_19').disabled=false;
			document.getElementById('hc_20').disabled=false;
			document.getElementById('hc_21').disabled=false;
		}
	}

	function readOnlyFilter(){
		//alert("readOnlyFilter()");
		//表头纳税方法选项定义为独立纳税时，系统仅开放本表第2-9行，第10至22行屏蔽
		if(document.forms[0].lje_nsfs[0].checked==1){
		//alert("readOnlyFilter()--1");
			
			//以下是自动计算部分屏蔽只读
			document.getElementById('7_1').readOnly=true;
			document.getElementById('10_1').readOnly=true;
			document.getElementById('16_1').readOnly=true;
			document.getElementById('17_1').readOnly=true;
			document.getElementById('18_1').readOnly=true;
			
			document.getElementById('6_1').style.backgroundColor="#FAEBD7";
			document.getElementById('7_1').style.backgroundColor="#FAEBD7";
			document.getElementById('10_1').style.backgroundColor="#FAEBD7";
			//document.getElementById('16_1').style.backgroundColor="#FAEBD7";
			//document.getElementById('17_1').style.backgroundColor="#FAEBD7";
			//document.getElementById('18_1').style.backgroundColor="#FAEBD7";
		//当表头纳税方法选项定义为汇总纳税-总机构时开放本表第2-9、18-20行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
		//alert("readOnlyFilter()--2");
			
			//以下是自动计算部分屏蔽只读
			
			document.getElementById('7_1').readOnly=true;
			document.getElementById('10_1').readOnly=true;
			/*按总局新需求，汇总纳税-总机构用户，18-20行为自行填写，不作计算*/
			document.getElementById('16_1').readOnly=false;
			document.getElementById('17_1').readOnly=false;
			document.getElementById('18_1').readOnly=false;
			
			document.getElementById('6_1').style.backgroundColor="#FAEBD7";
			document.getElementById('7_1').style.backgroundColor="#FAEBD7";
			document.getElementById('10_1').style.backgroundColor="#FAEBD7";
			//document.getElementById('16_1').style.backgroundColor="#FAEBD7";
			//document.getElementById('17_1').style.backgroundColor="#FAEBD7";
			//document.getElementById('18_1').style.backgroundColor="#FAEBD7";
			//
			//compute_Row_10_1();


		//当表头纳税方法选项定义为汇总纳税-分支机构时开放本表第21、22行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[1].checked==1){	
		//alert("readOnlyFilter()--3");
			
			//以下是自动计算部分屏蔽只读
			document.getElementById('7_1').readOnly=true;
			document.getElementById('10_1').readOnly=true;
			document.getElementById('16_1').readOnly=true;
			document.getElementById('17_1').readOnly=true;
			document.getElementById('18_1').readOnly=true;
			
			document.getElementById('7_1').style.backgroundColor="#FAEBD7";
			document.getElementById('10_1').style.backgroundColor="#FAEBD7";
			//document.getElementById('16_1').style.backgroundColor="#FAEBD7";
			//document.getElementById('17_1').style.backgroundColor="#FAEBD7";
			//document.getElementById('18_1').style.backgroundColor="#FAEBD7";
		}else{		
		alert("readOnlyFilter()--4");
			
			//以下是自动计算部分屏蔽只读
			document.getElementById('7_1').readOnly=true;
			document.getElementById('10_1').readOnly=true;
			document.getElementById('16_1').readOnly=true;
			document.getElementById('17_1').readOnly=true;
			document.getElementById('18_1').readOnly=true;
			
			document.getElementById('6_1').style.backgroundColor="#FAEBD7";
			document.getElementById('7_1').style.backgroundColor="#FAEBD7";
			document.getElementById('10_1').style.backgroundColor="#FAEBD7";
			document.getElementById('16_1').style.backgroundColor="#FAEBD7";
			document.getElementById('17_1').style.backgroundColor="#FAEBD7";
			document.getElementById('18_1').style.backgroundColor="#FAEBD7";
		}
	}


	function jumpToZFJG(){
		//document.forms[0].ActionType="Jump";
		//alert("此处需要填写转入企业所得税汇总纳税分支机构分配表》功能的链接");
		doSubmitForm('Jump');	
	}
	
	function checkNumInput(obj)
{
		//判断输入的数据是否符合要求
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;			
		}
		//格式化数据
		formate(obj);
}

/**
 * 格式化输入数据为小数点后两位
 * 
 * @param 文本框
 */
function formate(obj){
	//if(!isNumber(obj.value)){
	//	alert("请使用数字格式输入！");
	//	obj.focus();
	//	return false;
	//}
	if(obj.value==""||obj.value==null){
		return false;	
	}else{
		var temp = trim(obj.value+"");
		if(temp.indexOf(".")!=-1){
			var len=temp.indexOf(".")+1;
			if(temp.length-len==1)
			temp = temp+"0";
			var zs=trim(temp.substring(0,temp.indexOf(".")));
			if(zs==""){
				temp="0"+temp;
			}
		}else{
			temp=temp+".00";
		}
		obj.value=temp;	
		formateNum(obj);
	}		
}
/**
 * 格式化输入数据以0开头
 *
 * @param 文本框
 */
 function formateNum(obj){
 	var tempNum=obj.value;
 	var num=trim(tempNum.substring(0,tempNum.indexOf(".")));
 	if(num.length>1){
 		num=num.substring(0,num.length-1);
 		var i;
 		for(i=0;i<num.length;i++){
 			var itemp=num.substring(i,i+1);
 			if(itemp!="0"){
 				break;
 			}
 		}
 		tempNum=tempNum.substring(i,tempNum.length);
 		obj.value=tempNum;
 	}
 
 }	



</script>
</body>
</html:html>
