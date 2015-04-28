<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsjb2012.web.CzzssdsjbForm"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<%@ page import="com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.CheckJdlx"%>

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
<html:form method="POST" action="/webapp/smsb/qysds/czzssdsjb2012Action.do">
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
					<div align="left">&nbsp;&nbsp;申报日期： <html:text tabindex="1" property="sbrq" size="11"
						maxlength="8" readonly='true' style='text-align:left'
						onchange="checkZQ(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,3)" /></div>
					</td>
					<td wigth="90%" align="center" nowrap>税款所属期间： <html:text tabindex="2" property="skssksrq"
						size="15" maxlength="8"
						onchange="isDate(this,false);compareDate(this)"  style="text-align:left" /> 至 <html:text tabindex="3"
						property="skssjsrq" size="15" maxlength="8"
						onchange="isDate(this,false);compareDate(this)" style="text-align:left" /></td>
					<td align="right" nowrap>
						<!-- <div align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div> -->
						<div align="right">金额单位：人民币元（列至角分）</div>
					</td>
				</tr>
			</table>
			
			
			
			<table class="table-99" border="0" cellpadding="0" cellspacing="0" width="80%">
				<td>
					<table class="table-99" border="0" cellpadding="0" cellspacing="0" width="80%">
						<tr>
							<td width="10%" nowrap >
								<div style="font-size: 9pt; line-height: 26px; color: #3E6071; background-color: #F3F5F8; text-align: left;">&nbsp;&nbsp;纳税人地税计算机代码：
								</div>
							</td>
							<td colspan="3" nowrap align=left>
								<div align="left">
									<html:text tabindex="4" property="jsjdm" maxlength="8" size="15" onchange="doQuery()" style="text-align:left"/>
								</div>
							</td>
						</tr>
						<TR>
							<td width="10%" nowrap >
								<div style="font-size: 9pt; line-height: 26px; color: #3E6071; background-color: #F3F5F8; text-align: left;">&nbsp;&nbsp;纳&nbsp;税&nbsp;人&nbsp;识&nbsp;别&nbsp;号&nbsp;&nbsp;&nbsp;：
								</div>
							</td>
							<td colspan="3" width="70%" nowrap align="left">
								<div style="font-size: 9pt; line-height: 26px; color: #3E6071; background-color: #F3F5F8; text-align: left;">
									 <bean:write name="czzssdsjb2012Form" property="nsrsbh" scope="request" filter="true" />
								</div>
							</td>
						</tr>
						<TR>
							<td width="10%" nowrap >
								<div style="font-size: 9pt; line-height: 26px; color: #3E6071; background-color: #F3F5F8; text-align: left;">&nbsp;&nbsp;纳&nbsp;&nbsp;税&nbsp;&nbsp;人&nbsp;&nbsp;名&nbsp;&nbsp;称&nbsp;&nbsp;：
								</div>
							</td>
							<td colspan="3" nowrap align="left">
								<div style="font-size: 9pt; line-height: 26px; color: #3E6071; background-color: #F3F5F8; text-align: left;">
									<bean:write name="czzssdsjb2012Form" property="nsrmc" scope="request" filter="true" />
								</div>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<table class="table-99" border="0" cellpadding="0" cellspacing="0" width="80%">
						<tr> 
							<td width="10%" rowspan="2" height="78px" nowrap class="2-td2-left" style="border-top: 1px solid #9BB4CA;">&nbsp;
								<input type='radio' name='lje_nsfs' style='text-align:right' 
									   value='<%=CodeConstant.HZNSFF_QYSDSJB2008_CZZSSDS_HZNS%>' size='13' 
									   onclick="compute_Row_1()" onchange="compute_Row_1()" disabled="true">汇总纳税：
							</td>
							<td width="10%" nowrap class="2-td2-left" style="border-top: 1px solid #9BB4CA;">
								<div align="left">
									<input type='radio' name='lje_zfjg' style='text-align:right' 
										   value='<%=CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_ZJG%>' size='13' 
										   onclick="compute_Row_2()" onchange="compute_Row_2()" disabled="true">总机构
								</div>
							</td>
							<td width="10%" rowspan="2" height="78px" nowrap class="2-td2-left" style="border-top: 1px solid #9BB4CA; border-right: 1px solid #9BB4CA;">&nbsp;
								<input type='radio' name='lje_nsfs' style='text-align:right' 
									   value='<%=CodeConstant.HZNSFF_QYSDSJB2008_CZZSSDS_DLNS%>' size='13'
									   onclick="compute_Row_1()" onchange="compute_Row_1()" disabled="true">独立纳税
							</td>
						  </tr>
						  <tr> 
							<td width="10%" nowrap class="2-td2-left">
								<div align="left">
									<input type='radio' name='lje_zfjg' style='text-align:right' 
										   value='<%=CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_FZJG%>' size='13'
										   onclick="compute_Row_2()" onchange="compute_Row_2()" disabled="true">分支机构
								</div>
							</td>
						  </tr>
					</table>
				</td>
			</table>
			<!-- 	
			<table cellspacing="0" class="table-99">
				<tr class="black9">					
					<td align="right" nowrap>
						<div align="right">金额单位：人民币元（列至角分）</div>
					</td>
				</tr>
			</table>	
			-->	
				
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
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>一、按照实际利润额预缴</B></div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">利润总额</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">加：特定业务计算的应纳税所得额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="8" type='text' name='lje' 
							id='6_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_6()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">6
							<input type="hidden" name="hc" value="7" id="hc_7">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">减：不征税收入</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;免税收入</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;弥补以前年度亏损</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">实际利润额（4行+5行-6行-7行-8行）</div></td>
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
							<input type="hidden" name="hc" value="11" id="hc_11">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">税率（25%）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						&nbsp;<input readonly tabindex="13" type='text' name='lje' 
							id='11_1' style='text-align:right' 
							value='25.00' size='13'
							onblur="formate(this)" onchange="compute_Row_11()">%</td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">11
							<input type="hidden" name="hc" value="12" id="hc_12">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">应纳所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="14" type='text' name='lje' 
							id='12_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_12()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">12
							<input type="hidden" name="hc" value="13" id="hc_13">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">减：减免所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="15" type='text' name='lje' 
							id='13_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_13()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">13
							<input type="hidden" name="hc" value="14" id="hc_14">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">减：实际已预缴所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="16" type='text' name='lje' 
							id='14_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_14()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">14
							<input type="hidden" name="hc" value="15" id="hc_15">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">减：特定业务预缴（征）所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="17" type='text' name='lje' 
							id='15_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_15()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">15
							<input type="hidden" name="hc" value="16" id="hc_16">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">应补（退）所得税额（11行-12行-13行-14行）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="18" type='text' name='lje' 
							id='16_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_16()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">16
							<input type="hidden" name="hc" value="17" id="hc_17">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">减：以前年度多缴在本期抵缴所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="19" type='text' name='lje' 
							id='17_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_17()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">17
							<input type="hidden" name="hc" value="18" id="hc_18">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">本期实际应补（退）所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="20" type='text' name='lje' 
							id='18_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_18()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">18
						</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>二、按照上一纳税年度应纳税所得额平均额预缴</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">19
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">上一纳税年度应纳税所得额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">20
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">本月（季）应纳税所得额（19行×1/4或1/12）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">21
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">税率(25%)</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">22
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">本月（季）应纳所得税额（20行×21行）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">23
						</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>三、按照税务机关确定的其他方法预缴</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">24
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">本月（季）确定预缴的所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">25
						</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="center"><B>总分机构纳税人</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">26
							<input type="hidden" name="hc" value="24" id="hc_24">
						</div></td>
                    <td rowspan="5" width="20%" class="2-td2-left"><div align="center">总机构</div></td>
                    <td nowrap class="2-td2-left"><div align="left">总机构应分摊所得税额（15行或22行或24行×总机构应分摊预缴比例）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="21" type='text' name='lje' 
							id='24_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_24()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">27
							<input type="hidden" name="hc" value="25" id="hc_25">
						</div></td>
                    <td nowrap class="2-td2-left"><div align="left">财政集中分配所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="22" type='text' name='lje' 
							id='25_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_25()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">28
							<input type="hidden" name="hc" value="26" id="hc_26">
						</div></td>
                    <td nowrap class="2-td2-left"><div align="left">分支机构应分摊所得税额（15行或22行或24行×分支机构应分摊比例）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="23" type='text' name='lje' 
							id='26_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_26()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">29
							<input type="hidden" name="hc" value="27" id="hc_27">
						</div></td>
                    <td nowrap class="2-td2-left"><div align="left">其中：总机构独立生产经营部门应分摊所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="24" type='text' name='lje' 
							id='27_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_27()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">30
							<input type="hidden" name="hc" value="28" id="hc_28">
						</div></td>
                    <td nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总机构已撤销分支机构应分摊所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="25" type='text' name='lje' 
							id='28_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_28()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">31
							<input type="hidden" name="hc" value="29" id="hc_29">
						</div></td>
                    <td rowspan="2" nowrap class="2-td2-left"><div align="center">分支机构</div></td>
                    <td nowrap class="2-td2-left"><div align="left">分配比例</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						&nbsp;<input tabindex="26" type='text' name='lje' 
							id='29_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_29()">%</td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">32
							<input type="hidden" name="hc" value="30" id="hc_30">
						</div></td>
                    <td nowrap class="2-td2-left"><div align="left">分配所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="27" type='text' name='lje' 
							id='30_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_30()"></td>
                  </tr>
                </table>
					</div>
					</TD>
				</TR>

				<TR class="black9">
					<TD>
					<div align="center"><input type="image" accesskey="q"
						onClick="doQuery();return false;"
						onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)"
						onMouseOut="MM_swapImgRestore()" value="查询"
						src="<%=static_contextpath%>images/cx-q1.jpg " width="79"
						height="22" id="chaxun" style="cursor:hand">
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="s"
						style="cursor:hand" onClick="doSave();return false;"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" name="Image13"
						width="79" height="22" border="0" id='baocun'>
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="v"
						style="cursor:hand" onClick="doView();return false;"
						onMouseOver="MM_swapImage('chakan','','<%=static_contextpath%>images/chakfpb2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/chakfpb1.jpg" name="Image13"
						width="79" height="22" border="0" id='chakan'>
					&nbsp;&nbsp;&nbsp;&nbsp;<input type="image" accesskey="x"
						style="cursor:hand" onClick="doDelete();return false;"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" name="Image13"
						width="79" height="22" border="0" id='shanchu'>
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="u"
						style="cursor:hand" onClick="doReset();return false;"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" name="Image13"
						width="79" height="22" border="0" id='qingchu'>
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
		CzzssdsjbForm jbForm=(CzzssdsjbForm)request.getAttribute("czzssdsjb2012Form");
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
				}else if(hc==11){
					%>		
					//强制赋值25
					document.getElementById('11_1').value="25.00";
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
			//if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(document.forms[0].lje_nsfs[0].checked==1){
				//保存成功，提示转总分机构申报表
				//if(confirm("保存成功，是否转入《企业所得税汇总纳税分支机构分配表》？")){
					alert("保存成功，请继续填写《企业所得税汇总纳税分支机构分配表》！");
					jumpToZFJG();
				//}
			}else if(document.forms[0].lje_nsfs[1].checked==1){
				alert("申报已成功！");
			}
		<%
		}
			
		//添加鉴定类型
		String jdlx = jbForm.getJdlx();
		//jdlx = "03";
		if(jdlx.equals(CheckJdlx.QYSDSZGFWJDLX_DLNSR) || jdlx.equals(CheckJdlx.QYSDSZGFWJDLX_ZFJGJZBSZJGNSR))
		{%>
			document.forms[0].lje_nsfs[1].checked=1;
			document.forms[0].lje_nsfs[1].disabled = false;
		<%}
		
		if(jdlx.equals(CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR))
		{%>
			document.forms[0].lje_nsfs[0].checked=1;
			document.forms[0].lje_nsfs[0].disabled = false;
			
			document.forms[0].lje_zfjg[1].checked=1;
			document.forms[0].lje_zfjg[1].disabled = false;
		<%}
		
		if(jdlx.equals(CheckJdlx.QYSDSZGFWJDLX_KSSZJGNSR))
		{%>
			document.forms[0].lje_nsfs[0].checked=1;
			document.forms[0].lje_nsfs[0].disabled = false;
			
			document.forms[0].lje_zfjg[0].checked=1;
			document.forms[0].lje_zfjg[0].disabled = false;
		<%}
		
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
	
	//如果申报月份不属于企业所得税季度征期1、4、7、10则提示操作人员
	function checkZQ(sbrq,ksrq,jzrq,lx){
		if(!isDate(sbrq,"v")) return;	
		getStartEndDate1(sbrq,ksrq,jzrq,lx);
		var inputDate = sbrq.value;
		mon = inputDate.substring(4,6);
		//alert("mon=="+mon);
		if(mon!='01' && mon!='04' && mon!='07' && mon!='10'){
			alert('注意：'+inputDate+'不属于征期。');
		}
	}
	
	/**
	* Notes: 获取当前日期的上一年/月/季的起止日期。
	* Version: 1.0
	* Author: gaoyh
	* Parames: flag 1,Last Year;2,Last Month;默认：输出季度
	
	function getStartEndDate1(oInput1,oInput2,oInput3,flag){
		var date_start,date_end;
		var year,mon,days;
		var strMon;
	
		var inputDate = oInput1.value;
	
		//是否合法日期
		if(isDate(oInput1,"v")){
			year = inputDate.substring(0,4);
			mon = inputDate.substring(4,6);
			days = inputDate.substring(6,8);
	
			if(flag == 1){//Last Year
				date_start = (year-1)+"0101";
				date_end = (year-1)+"1231";
			}else if(flag == 2){//Last Month
				var date2 = new Date(year,mon-1,-1);
				days = date2.getDate()+1;
				year = date2.getYear();
				mon = date2.getMonth()+1;
	
				date_start = year+""+formatMon(mon)+"01";
				date_end = year+""+formatMon(mon)+days;
				//date_start = year+""+formatMon(mon-1)+"01";
				//date_end = year+""+formatMon(mon-1)+days;
			}else{
				//mon = parseInt(mon);
				switch(mon){
					case "01":
					case "02":
					case "03":
						date_start = (year-1)+"0101";
						date_end = (year-1)+"1231";
						break;
					case "04":
					case "05":
					case "06":
						date_start = year+"0101";
						date_end = year+"0331";
						break;
					case "07":
					case "08":
					case "09":
						date_start = year+"0101";
						date_end = year+"0630";
						break;
					case "10":
					case "11":
					case "12":
						date_start = year+"0101";
						date_end = year+"0930";
						break;
				}
			}
	
			oInput2.value = date_start;
			oInput3.value = date_end;
		}
	}	
	*/

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
	
	<%/*判断输入值是否为空*/%>
	function saveCheckNull(id){
		obj=document.getElementById(id);
		if(obj.value==""){
			obj.focus();
			alert("该选项不能为空!");
			return flase;
		}
		return true;
	}
	
	<%/*对数据进行校验*/%>
	function saveCheck(row,zero){
		
		if(!isNum(document.getElementById(row+'_1'), zero, 9999999999999, null, 16, 2)){
			return false;			
		}
		return true;	
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
		//alert("changeSelect...");
		//alert("document.forms[0].lje_nsfs.length==="+document.forms[0].lje_nsfs.length);
		//alert("document.forms[0].lje_nsfs[0].checked==="+document.forms[0].lje_nsfs[0].checked);
		//alert("document.forms[0].lje_nsfs[1].checked==="+document.forms[0].lje_nsfs[1].checked);
		//alert("document.forms[0].lje_nsfs[0]==="+document.forms[0].lje_nsfs[0].value);
		//alert("document.forms[0].lje_nsfs[1]==="+document.forms[0].lje_nsfs[1].value);
		//alert("document.forms[0].lje_zfjg[0]==="+document.forms[0].lje_zfjg[0].value);
		//alert("document.forms[0].lje_zfjg[1]==="+document.forms[0].lje_zfjg[1].value);
		if(document.forms[0].lje_nsfs[1].checked == 1)
		{
			//选择为独立纳税的，清除汇总纳税方式子项选择，同时将这些子项置位不可编辑
			document.forms[0].lje_zfjg[0].checked = false;
			document.forms[0].lje_zfjg[0].disabled = true;
			document.forms[0].lje_zfjg[1].checked = false;
			document.forms[0].lje_zfjg[1].disabled = true;
		}
		else if(document.forms[0].lje_nsfs[0].checked == 1)
		{
			if(document.forms[0].lje_zfjg[0].checked==0&&document.forms[0].lje_zfjg[1].checked==0){
				document.forms[0].lje_zfjg[0].checked = true;
				document.forms[0].lje_zfjg[1].checked = false;
			}
			//选择为总机构时，开发其子项选择权限
			//document.forms[0].lje_zfjg[0].disabled = false;
			//document.forms[0].lje_zfjg[1].disabled = false;
		}
		//readOnlyFilter();
	}
	
	function checkFilter(){
		//表头纳税方法选项定义为独立纳税时，系统仅开放本表第2-17行，第18至32行屏蔽
		if(document.forms[0].lje_nsfs[1].checked==1){
			document.getElementById('11_1').value="25.00";
			document.getElementById('11_1').readOnly=true;

			
			//alert("checkFilter....");
			document.getElementById('3_1').disabled=false;
			document.getElementById('4_1').disabled=false;
			document.getElementById('5_1').disabled=false;
			document.getElementById('6_1').disabled=false;
			document.getElementById('7_1').disabled=false;
			document.getElementById('8_1').disabled=false;
			document.getElementById('9_1').disabled=false;
			document.getElementById('10_1').disabled=false;
			document.getElementById('11_1').disabled=false;
			document.getElementById('12_1').disabled=false;
			document.getElementById('13_1').disabled=false;
			document.getElementById('14_1').disabled=false;
			document.getElementById('15_1').disabled=false;
			document.getElementById('16_1').disabled=false;
			document.getElementById('17_1').disabled=false;
			document.getElementById('18_1').disabled=false;
			document.getElementById('24_1').disabled=true;
			document.getElementById('25_1').disabled=true;
			document.getElementById('26_1').disabled=true;
			document.getElementById('27_1').disabled=true;
			document.getElementById('28_1').disabled=true;
			document.getElementById('29_1').disabled=true;
			document.getElementById('30_1').disabled=true;

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
			if(document.getElementById('11_1').value == null || document.getElementById('11_1').value == "")
			{
				document.getElementById('11_1').value="0.00";
			}
			if(document.getElementById('12_1').value == null || document.getElementById('12_1').value == "")
			{
				document.getElementById('12_1').value="0.00";
			}
			if(document.getElementById('13_1').value == null || document.getElementById('13_1').value == "")
			{
				document.getElementById('13_1').value="0.00";
			}
			if(document.getElementById('14_1').value == null || document.getElementById('14_1').value == "")
			{
				document.getElementById('14_1').value="0.00";
			}
			if(document.getElementById('15_1').value == null || document.getElementById('15_1').value == "")
			{
				document.getElementById('15_1').value="0.00";
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
			document.getElementById('24_1').value="";
			document.getElementById('25_1').value="";
			document.getElementById('26_1').value="";
			document.getElementById('27_1').value="";
			document.getElementById('28_1').value="";
			document.getElementById('29_1').value="";
			document.getElementById('30_1').value="";

			document.getElementById('3_1').style.backgroundColor="";
			document.getElementById('4_1').style.backgroundColor="";
			document.getElementById('5_1').style.backgroundColor="";
			document.getElementById('6_1').style.backgroundColor="";
			document.getElementById('7_1').style.backgroundColor="";
			document.getElementById('8_1').style.backgroundColor="";
			document.getElementById('9_1').style.backgroundColor="";
			document.getElementById('10_1').style.backgroundColor="";
			document.getElementById('11_1').style.backgroundColor="";
			document.getElementById('12_1').style.backgroundColor="";
			document.getElementById('13_1').style.backgroundColor="";
			document.getElementById('14_1').style.backgroundColor="";
			document.getElementById('15_1').style.backgroundColor="";
			document.getElementById('16_1').style.backgroundColor="";
			document.getElementById('17_1').style.backgroundColor="";
			document.getElementById('18_1').style.backgroundColor="";
			document.getElementById('24_1').style.backgroundColor="#aaaaaa";
			document.getElementById('25_1').style.backgroundColor="#aaaaaa";
			document.getElementById('26_1').style.backgroundColor="#aaaaaa";
			document.getElementById('27_1').style.backgroundColor="#aaaaaa";
			document.getElementById('28_1').style.backgroundColor="#aaaaaa";
			document.getElementById('29_1').style.backgroundColor="#aaaaaa";
			document.getElementById('30_1').style.backgroundColor="#aaaaaa";

			document.getElementById('hc_3').disabled=false;
			document.getElementById('hc_4').disabled=false;
			document.getElementById('hc_5').disabled=false;
			document.getElementById('hc_6').disabled=false;
			document.getElementById('hc_7').disabled=false;
			document.getElementById('hc_8').disabled=false;
			document.getElementById('hc_9').disabled=false;
			document.getElementById('hc_10').disabled=false;
			document.getElementById('hc_11').disabled=false;
			document.getElementById('hc_12').disabled=false;
			document.getElementById('hc_13').disabled=false;
			document.getElementById('hc_14').disabled=false;
			document.getElementById('hc_15').disabled=false;
			document.getElementById('hc_16').disabled=false;
			document.getElementById('hc_17').disabled=false;
			document.getElementById('hc_18').disabled=false;
			document.getElementById('hc_24').disabled=true;
			document.getElementById('hc_25').disabled=true;
			document.getElementById('hc_26').disabled=true;
			document.getElementById('hc_27').disabled=true;
			document.getElementById('hc_28').disabled=true;
			document.getElementById('hc_29').disabled=true;
			document.getElementById('hc_30').disabled=true;
		//当表头纳税方法选项定义为汇总纳税-总机构时开放本表第2-17、26-30行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			document.getElementById('11_1').value="25.00";
			document.getElementById('11_1').readOnly=true;

			document.getElementById('3_1').disabled=false;
			document.getElementById('4_1').disabled=false;
			document.getElementById('5_1').disabled=false;
			document.getElementById('6_1').disabled=false;
			document.getElementById('7_1').disabled=false;
			document.getElementById('8_1').disabled=false;
			document.getElementById('9_1').disabled=false;
			document.getElementById('10_1').disabled=false;
			document.getElementById('11_1').disabled=false;
			document.getElementById('12_1').disabled=false;
			document.getElementById('13_1').disabled=false;
			document.getElementById('14_1').disabled=false;
			document.getElementById('15_1').disabled=false;
			document.getElementById('16_1').disabled=false;
			document.getElementById('17_1').disabled=false;
			document.getElementById('18_1').disabled=false;
			document.getElementById('24_1').disabled=false;
			document.getElementById('25_1').disabled=false;
			document.getElementById('26_1').disabled=false;
			document.getElementById('27_1').disabled=false;
			document.getElementById('28_1').disabled=false;
			document.getElementById('29_1').disabled=true;
			document.getElementById('30_1').disabled=true;

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
			if(document.getElementById('11_1').value == null || document.getElementById('11_1').value == "")
			{
				document.getElementById('11_1').value="0.00";
			}
			if(document.getElementById('12_1').value == null || document.getElementById('12_1').value == "")
			{
				document.getElementById('12_1').value="0.00";
			}
			if(document.getElementById('13_1').value == null || document.getElementById('13_1').value == "")
			{
				document.getElementById('13_1').value="0.00";
			}
			if(document.getElementById('14_1').value == null || document.getElementById('14_1').value == "")
			{
				document.getElementById('14_1').value="0.00";
			}
			if(document.getElementById('15_1').value == null || document.getElementById('15_1').value == "")
			{
				document.getElementById('15_1').value="0.00";
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
			if(document.getElementById('24_1').value == null || document.getElementById('24_1').value == "")
			{
				document.getElementById('24_1').value="0.00";
			}
			if(document.getElementById('25_1').value == null || document.getElementById('25_1').value == "")
			{
				document.getElementById('25_1').value="0.00";
			}
			if(document.getElementById('26_1').value == null || document.getElementById('26_1').value == "")
			{
				document.getElementById('26_1').value="0.00";
			}
			if(document.getElementById('27_1').value == null || document.getElementById('27_1').value == "")
			{
				document.getElementById('27_1').value="0.00";
			}
			if(document.getElementById('28_1').value == null || document.getElementById('28_1').value == "")
			{
				document.getElementById('28_1').value="0.00";
			}
			document.getElementById('29_1').value="";
			document.getElementById('30_1').value="";

			document.getElementById('3_1').style.backgroundColor="";
			document.getElementById('4_1').style.backgroundColor="";
			document.getElementById('5_1').style.backgroundColor="";
			document.getElementById('6_1').style.backgroundColor="";
			document.getElementById('7_1').style.backgroundColor="";
			document.getElementById('8_1').style.backgroundColor="";
			document.getElementById('9_1').style.backgroundColor="";
			document.getElementById('10_1').style.backgroundColor="";
			document.getElementById('11_1').style.backgroundColor="";
			document.getElementById('12_1').style.backgroundColor="";
			document.getElementById('13_1').style.backgroundColor="";
			document.getElementById('14_1').style.backgroundColor="";
			document.getElementById('15_1').style.backgroundColor="";
			document.getElementById('16_1').style.backgroundColor="";
			document.getElementById('17_1').style.backgroundColor="";
			document.getElementById('18_1').style.backgroundColor="";
			document.getElementById('24_1').style.backgroundColor="";
			document.getElementById('25_1').style.backgroundColor="";
			document.getElementById('26_1').style.backgroundColor="";
			document.getElementById('27_1').style.backgroundColor="";
			document.getElementById('28_1').style.backgroundColor="";
			document.getElementById('29_1').style.backgroundColor="#aaaaaa";
			document.getElementById('30_1').style.backgroundColor="#aaaaaa";

			document.getElementById('hc_3').disabled=false;
			document.getElementById('hc_4').disabled=false;
			document.getElementById('hc_5').disabled=false;
			document.getElementById('hc_6').disabled=false;
			document.getElementById('hc_7').disabled=false;
			document.getElementById('hc_8').disabled=false;
			document.getElementById('hc_9').disabled=false;
			document.getElementById('hc_10').disabled=false;
			document.getElementById('hc_11').disabled=false;
			document.getElementById('hc_12').disabled=false;
			document.getElementById('hc_13').disabled=false;
			document.getElementById('hc_14').disabled=false;
			document.getElementById('hc_15').disabled=false;
			document.getElementById('hc_16').disabled=false;
			document.getElementById('hc_17').disabled=false;
			document.getElementById('hc_18').disabled=false;
			document.getElementById('hc_24').disabled=false;
			document.getElementById('hc_25').disabled=false;
			document.getElementById('hc_26').disabled=false;
			document.getElementById('hc_27').disabled=false;
			document.getElementById('hc_28').disabled=false;
			document.getElementById('hc_29').disabled=true;
			document.getElementById('hc_30').disabled=true;
			
		//当表头纳税方法选项定义为汇总纳税-分支机构时开放本表第28、31、32行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){	
			document.getElementById('11_1').value="25.00";
			document.getElementById('11_1').readOnly=true;

			document.getElementById('3_1').disabled=true;
			document.getElementById('4_1').disabled=true;
			document.getElementById('5_1').disabled=true;
			document.getElementById('6_1').disabled=true;
			document.getElementById('7_1').disabled=true;
			document.getElementById('8_1').disabled=true;
			document.getElementById('9_1').disabled=true;
			document.getElementById('10_1').disabled=true;
			document.getElementById('11_1').disabled=true;
			document.getElementById('12_1').disabled=true;
			document.getElementById('13_1').disabled=true;
			document.getElementById('14_1').disabled=true;
			document.getElementById('15_1').disabled=true;
			document.getElementById('16_1').disabled=true;
			document.getElementById('17_1').disabled=true;
			document.getElementById('18_1').disabled=true;
			document.getElementById('24_1').disabled=true;
			document.getElementById('25_1').disabled=true;
			document.getElementById('26_1').disabled=false;
			document.getElementById('27_1').disabled=true;
			document.getElementById('28_1').disabled=true;
			document.getElementById('29_1').disabled=false;
			document.getElementById('30_1').disabled=false;

			//将被屏蔽行次值清空			
			document.getElementById('3_1').value="";
			document.getElementById('4_1').value="";
			document.getElementById('5_1').value="";
			document.getElementById('6_1').value="";
			document.getElementById('7_1').value="";
			document.getElementById('8_1').value="";
			document.getElementById('9_1').value="";
			document.getElementById('10_1').value="";
			//document.getElementById('11_1').value="";
			document.getElementById('12_1').value="";
			document.getElementById('13_1').value="";
			document.getElementById('14_1').value="";
			document.getElementById('15_1').value="";
			document.getElementById('16_1').value="";
			document.getElementById('17_1').value="";
			document.getElementById('18_1').value="";
			document.getElementById('24_1').value="";
			document.getElementById('25_1').value="";
			document.getElementById('27_1').value="";
			document.getElementById('28_1').value="";
			
			if(document.getElementById('26_1').value == null || document.getElementById('26_1').value == "")
			{
				document.getElementById('26_1').value="0.00";
			}
			if(document.getElementById('29_1').value == null || document.getElementById('29_1').value == "")
			{
				document.getElementById('29_1').value="0.00";
			}
			if(document.getElementById('30_1').value == null || document.getElementById('30_1').value == "")
			{
				document.getElementById('30_1').value="0.00";
			}

			document.getElementById('3_1').style.backgroundColor="#aaaaaa";
			document.getElementById('4_1').style.backgroundColor="#aaaaaa";
			document.getElementById('5_1').style.backgroundColor="#aaaaaa";
			document.getElementById('6_1').style.backgroundColor="#aaaaaa";
			document.getElementById('7_1').style.backgroundColor="#aaaaaa";
			document.getElementById('8_1').style.backgroundColor="#aaaaaa";
			document.getElementById('9_1').style.backgroundColor="#aaaaaa";
			document.getElementById('10_1').style.backgroundColor="#aaaaaa";
			document.getElementById('11_1').style.backgroundColor="#aaaaaa";
			document.getElementById('12_1').style.backgroundColor="#aaaaaa";
			document.getElementById('13_1').style.backgroundColor="#aaaaaa";
			document.getElementById('14_1').style.backgroundColor="#aaaaaa";
			document.getElementById('15_1').style.backgroundColor="#aaaaaa";
			document.getElementById('16_1').style.backgroundColor="#aaaaaa";
			document.getElementById('17_1').style.backgroundColor="#aaaaaa";
			document.getElementById('18_1').style.backgroundColor="#aaaaaa";
			document.getElementById('24_1').style.backgroundColor="#aaaaaa";
			document.getElementById('25_1').style.backgroundColor="#aaaaaa";			
			document.getElementById('26_1').style.backgroundColor="";
			document.getElementById('27_1').style.backgroundColor="#aaaaaa";
			document.getElementById('28_1').style.backgroundColor="#aaaaaa";
			document.getElementById('29_1').style.backgroundColor="";
			document.getElementById('30_1').style.backgroundColor="";
			

			document.getElementById('hc_3').disabled=true;
			document.getElementById('hc_4').disabled=true;
			document.getElementById('hc_5').disabled=true;
			document.getElementById('hc_6').disabled=true;
			document.getElementById('hc_7').disabled=true;
			document.getElementById('hc_8').disabled=true;
			document.getElementById('hc_9').disabled=true;
			document.getElementById('hc_10').disabled=true;			
			document.getElementById('hc_11').disabled=true;
			document.getElementById('hc_12').disabled=true;
			document.getElementById('hc_13').disabled=true;
			document.getElementById('hc_14').disabled=true;
			document.getElementById('hc_15').disabled=true;
			document.getElementById('hc_16').disabled=true;
			document.getElementById('hc_17').disabled=true;
			document.getElementById('hc_18').disabled=true;
			document.getElementById('hc_24').disabled=true;
			document.getElementById('hc_25').disabled=true;
			document.getElementById('hc_26').disabled=false;
			document.getElementById('hc_27').disabled=true;			
			document.getElementById('hc_28').disabled=true;						
			document.getElementById('hc_29').disabled=false;
			document.getElementById('hc_30').disabled=false;
		}else{		
			document.getElementById('11_1').value="25";
			document.getElementById('11_1').readOnly=true;

			

			document.getElementById('3_1').disabled=false;
			document.getElementById('4_1').disabled=false;
			document.getElementById('5_1').disabled=false;
			document.getElementById('6_1').disabled=false;
			document.getElementById('7_1').disabled=false;
			document.getElementById('8_1').disabled=false;
			document.getElementById('9_1').disabled=false;
			document.getElementById('10_1').disabled=false;
			document.getElementById('11_1').disabled=false;
			document.getElementById('12_1').disabled=false;
			document.getElementById('13_1').disabled=false;
			document.getElementById('14_1').disabled=false;
			document.getElementById('15_1').disabled=false;
			document.getElementById('16_1').disabled=false;
			document.getElementById('17_1').disabled=false;
			document.getElementById('18_1').disabled=false;
			document.getElementById('24_1').disabled=false;
			document.getElementById('25_1').disabled=false;
			document.getElementById('26_1').disabled=false;
			document.getElementById('27_1').disabled=false;
			document.getElementById('28_1').disabled=false;
			document.getElementById('29_1').disabled=false;
			document.getElementById('30_1').disabled=false;

			document.getElementById('3_1').style.backgroundColor="";
			document.getElementById('4_1').style.backgroundColor="";
			document.getElementById('5_1').style.backgroundColor="";
			document.getElementById('6_1').style.backgroundColor="";
			document.getElementById('7_1').style.backgroundColor="";
			document.getElementById('8_1').style.backgroundColor="";
			document.getElementById('9_1').style.backgroundColor="";
			document.getElementById('10_1').style.backgroundColor="";
			document.getElementById('11_1').style.backgroundColor="";
			document.getElementById('12_1').style.backgroundColor="";
			document.getElementById('13_1').style.backgroundColor="";
			document.getElementById('14_1').style.backgroundColor="";
			document.getElementById('15_1').style.backgroundColor="";
			document.getElementById('16_1').style.backgroundColor="";
			document.getElementById('17_1').style.backgroundColor="";
			document.getElementById('18_1').style.backgroundColor="";
			document.getElementById('24_1').style.backgroundColor="#aaaaaa";
			document.getElementById('25_1').style.backgroundColor="#aaaaaa";
			document.getElementById('26_1').style.backgroundColor="#aaaaaa";
			document.getElementById('27_1').style.backgroundColor="#aaaaaa";
			document.getElementById('28_1').style.backgroundColor="#aaaaaa";
			document.getElementById('29_1').style.backgroundColor="#aaaaaa";
			document.getElementById('30_1').style.backgroundColor="#aaaaaa";

			document.getElementById('hc_3').disabled=false;
			document.getElementById('hc_4').disabled=false;
			document.getElementById('hc_5').disabled=false;
			document.getElementById('hc_6').disabled=false;
			document.getElementById('hc_7').disabled=false;
			document.getElementById('hc_8').disabled=false;
			document.getElementById('hc_9').disabled=false;
			document.getElementById('hc_10').disabled=false;
			document.getElementById('hc_11').disabled=false;
			document.getElementById('hc_12').disabled=false;
			document.getElementById('hc_13').disabled=false;
			document.getElementById('hc_14').disabled=false;
			document.getElementById('hc_15').disabled=false;
			document.getElementById('hc_16').disabled=false;
			document.getElementById('hc_17').disabled=false;
			document.getElementById('hc_18').disabled=false;
			document.getElementById('hc_24').disabled=false;
			document.getElementById('hc_25').disabled=false;
			document.getElementById('hc_26').disabled=false;
			document.getElementById('hc_27').disabled=false;
			document.getElementById('hc_28').disabled=false;
			document.getElementById('hc_29').disabled=false;
			document.getElementById('hc_30').disabled=false;
		}
	}

	function readOnlyFilter(){
		//alert("readOnlyFilter()");
		//表头纳税方法选项定义为独立纳税时，系统仅开放本表第2-17行，第18至32行屏蔽
		if(document.forms[0].lje_nsfs[1].checked==1){
		//alert("readOnlyFilter()--1");
			
			//以下是自动计算部分屏蔽只读
			document.getElementById('10_1').readOnly=true;
			document.getElementById('11_1').readOnly=true;
			document.getElementById('12_1').readOnly=true;
			document.getElementById('16_1').readOnly=true;
			document.getElementById('18_1').readOnly=true;
			//document.getElementById('24_1').readOnly=true;
			//document.getElementById('26_1').readOnly=true;
			
			document.getElementById('10_1').style.backgroundColor="#FAEBD7";
			document.getElementById('11_1').style.backgroundColor="#FAEBD7";
			document.getElementById('12_1').style.backgroundColor="#FAEBD7";
			document.getElementById('16_1').style.backgroundColor="#FAEBD7";
			document.getElementById('18_1').style.backgroundColor="#FAEBD7";
		//当表头纳税方法选项定义为汇总纳税-总机构时开放本表第2-17、26-30行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
		//alert("readOnlyFilter()--2");
			
			//以下是自动计算部分屏蔽只读
			
			document.getElementById('10_1').readOnly=true;
			document.getElementById('11_1').readOnly=true;
			document.getElementById('12_1').readOnly=true;
			document.getElementById('16_1').readOnly=true;
			document.getElementById('18_1').readOnly=true;
			document.getElementById('24_1').readOnly=true;
			document.getElementById('25_1').readOnly=true;
			document.getElementById('26_1').readOnly=true;
			document.getElementById('27_1').readOnly=false;
			document.getElementById('28_1').readOnly=false;
			
			document.getElementById('10_1').style.backgroundColor="#FAEBD7";
			document.getElementById('11_1').style.backgroundColor="#FAEBD7";
			document.getElementById('12_1').style.backgroundColor="#FAEBD7";
			document.getElementById('16_1').style.backgroundColor="#FAEBD7";
			document.getElementById('18_1').style.backgroundColor="#FAEBD7";
			document.getElementById('24_1').style.backgroundColor="#FAEBD7";
			document.getElementById('25_1').style.backgroundColor="#FAEBD7";
			document.getElementById('26_1').style.backgroundColor="#FAEBD7";

		//当表头纳税方法选项定义为汇总纳税-分支机构时开放本表第28、31、32行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){	
		//alert("readOnlyFilter()--3");
			
			//以下是自动计算部分屏蔽只读
			document.getElementById('10_1').readOnly=true;
			document.getElementById('11_1').readOnly=true;
			document.getElementById('12_1').readOnly=true;
			document.getElementById('16_1').readOnly=true;
			document.getElementById('18_1').readOnly=true;
			//document.getElementById('24_1').readOnly=true;
			document.getElementById('26_1').readOnly=false;
			document.getElementById('30_1').readOnly=true;
			
			document.getElementById('10_1').style.backgroundColor="#FAEBD7";
			//document.getElementById('11_1').style.backgroundColor="#FAEBD7";
			document.getElementById('12_1').style.backgroundColor="#FAEBD7";
			document.getElementById('16_1').style.backgroundColor="#FAEBD7";
			document.getElementById('18_1').style.backgroundColor="#FAEBD7";
			document.getElementById('30_1').style.backgroundColor="#FAEBD7";
		}else{		
			//alert("readOnlyFilter()--4");
			
			//以下是自动计算部分屏蔽只读
			document.getElementById('10_1').readOnly=true;
			document.getElementById('11_1').readOnly=true;
			document.getElementById('12_1').readOnly=true;
			document.getElementById('16_1').readOnly=true;
			//document.getElementById('24_1').readOnly=true;
			//document.getElementById('26_1').readOnly=true;
			
			document.getElementById('10_1').style.backgroundColor="#FAEBD7";
			document.getElementById('11_1').style.backgroundColor="#FAEBD7";
			document.getElementById('12_1').style.backgroundColor="#FAEBD7";
			document.getElementById('16_1').style.backgroundColor="#FAEBD7";
			//document.getElementById('24_1').style.backgroundColor="#FAEBD7";
			//document.getElementById('26_1').style.backgroundColor="#FAEBD7";
		}
	}
	function saveCheckFilter(){
		//alert("saveCheckFilter...");
		if(document.forms[0].lje_nsfs[0].checked==1){
			if((document.forms[0].lje_zfjg[0].checked==0)&&(document.forms[0].lje_zfjg[1].checked==0)){
				alert("请选择总分机构类型！");
				return false;
			}
		}
		
		//表头纳税方法选项定义为独立纳税时，系统仅开放本表第2-17行，第18至32行屏蔽
		if(document.forms[0].lje_nsfs[1].checked==1){
		//alert("document.forms[0].lje_nsfs[1].value==="+document.forms[0].lje_nsfs[1].value);
			var value_3=(document.getElementById('3_1').value)*1;
			var value_4=(document.getElementById('4_1').value)*1;
			var value_5=(document.getElementById('5_1').value)*1;
			var value_6=(document.getElementById('6_1').value)*1;
			var value_7=(document.getElementById('7_1').value)*1;
			var value_8=(document.getElementById('8_1').value)*1;
			var value_9=(document.getElementById('9_1').value)*1;
			var value_10=(document.getElementById('10_1').value)*1;
			var value_11=((document.getElementById('11_1').value)*1)/100;
			var value_12=(document.getElementById('12_1').value)*1;
			var value_13=(document.getElementById('13_1').value)*1;
			var value_14=(document.getElementById('14_1').value)*1;
			var value_15=(document.getElementById('15_1').value)*1;
			var value_16=(document.getElementById('16_1').value)*1;
			var value_17=(document.getElementById('17_1').value)*1;
			var value_18=(document.getElementById('18_1').value)*1;
			
			if(!isNum(document.getElementById('3_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('4_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('5_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('6_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('7_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('8_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('9_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('12_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('13_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('14_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('15_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('17_1'), null, null, null, null, 2)){
				return false;			
			}
			
			  <%/*判断第5行数据*/%>
		      if(value_6<0){
		          alert("加：特定业务计算的应纳税所得额（5行）应大于等于0!");
		          document.getElementById('6_1').select();
		          document.getElementById('6_1').focus();
		          return false;
		      }
		      <%/*判断第6行数据*/%>
		      if(value_7<0){
		          alert("减：不征税收入（6行）应大于等于0!");
		          document.getElementById('7_1').select();
		          document.getElementById('7_1').focus();
		          return false;
		      }
		      <%/*判断第7行数据*/%>
		      if(value_8<0){
		          alert("免税收入（7行）应大于等于0!");
		          document.getElementById('8_1').select();
		          document.getElementById('8_1').focus();
		          return false;
		      }
		      <%/*判断第8行数据*/%>
		      var obj_4_5_6_7_jisuanjieguo = (value_5+value_6-value_7-value_8).toFixed(2);
		      if((1*obj_4_5_6_7_jisuanjieguo>0)&&(value_9>obj_4_5_6_7_jisuanjieguo)){
		          alert("弥补以前年度亏损（8行）应小于等于第4+5-6-7,且应大于等于0！");
		          document.getElementById('9_1').select();
		          document.getElementById('9_1').focus();
		          return false;
		      }
		      if((1*obj_4_5_6_7_jisuanjieguo>0)&&(value_9<0)){
		          alert("弥补以前年度亏损（8行）应大于等于0!");
		          document.getElementById('9_1').select();
		          document.getElementById('9_1').focus();
		          return false;
		      }  
		      if((1*obj_4_5_6_7_jisuanjieguo<=0)&&(value_9!=0)){
		  	      alert("第4+5-6-7行小于等于0，弥补以前年度亏损（8行）应等于0!");
		  	      document.getElementById('9_1').select();
	              document.getElementById('9_1').focus();
				  return false;
		      }      
		      <%/*判断第12行数据*/%>
		      if(value_13>value_12){
		          alert("减：减免所得税额（12行）应小于等于应纳所得税额（11行）,且应大于等于0！");
		          document.getElementById('13_1').select();
		          document.getElementById('13_1').focus();
				  return false;
			  }
			  if(value_13<0){
			      alert("减：减免所得税额（12行）应大于等于0!");
			      document.getElementById('13_1').select();
		          document.getElementById('13_1').focus();
				  return false;
			  }
			  <%/*判断第16行数据*/%>
		      if(value_17>value_16){
		          alert("减：以前年度多缴在本期抵缴所得税额（16行）应小于等于应补（退）所得税额（15行），请核对！");
		          document.getElementById('17_1').select();
		          document.getElementById('17_1').focus();
		        return false;
		      }
		      if(value_17<0){
		          alert("减：以前年度多缴在本期抵缴所得税额（16行）应大于等于0!");
		          document.getElementById('17_1').select();
		          document.getElementById('17_1').focus();
		        return false;
		      }

		//当表头纳税方法选项定义为汇总纳税-总机构时开放本表第2-17、26-30行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			
			var value_3=(document.getElementById('3_1').value)*1;
			var value_4=(document.getElementById('4_1').value)*1;
			var value_5=(document.getElementById('5_1').value)*1;
			var value_6=(document.getElementById('6_1').value)*1;
			var value_7=(document.getElementById('7_1').value)*1;
			var value_8=(document.getElementById('8_1').value)*1;
			var value_9=(document.getElementById('9_1').value)*1;
			var value_10=(document.getElementById('10_1').value)*1;
			var value_11=((document.getElementById('11_1').value)*1)/100;
			var value_12=(document.getElementById('12_1').value)*1;
			var value_13=(document.getElementById('13_1').value)*1;
			var value_14=(document.getElementById('14_1').value)*1;
			var value_15=(document.getElementById('15_1').value)*1;
			var value_16=(document.getElementById('16_1').value)*1;
			var value_17=(document.getElementById('17_1').value)*1;
			var value_18=(document.getElementById('18_1').value)*1;
			var value_24=(document.getElementById('24_1').value)*1;
			var value_25=(document.getElementById('25_1').value)*1;
			var value_26=(document.getElementById('26_1').value)*1;
			var value_27=(document.getElementById('27_1').value)*1;
			var value_28=(document.getElementById('28_1').value)*1;
			
			if(!isNum(document.getElementById('3_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('4_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('5_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('6_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('7_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('8_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('9_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('12_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('13_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('14_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('15_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('17_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('27_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('28_1'), null, null, null, null, 2)){
				return false;			
			}
			
			<%/*判断第5行数据*/%>
		      if(value_6<0){
		          alert("加：特定业务计算的应纳税所得额（5行）应大于等于0!");
		          document.getElementById('6_1').select();
		          document.getElementById('6_1').focus();
		          return false;
		      }
		      <%/*判断第6行数据*/%>
		      if(value_7<0){
		          alert("减：不征税收入（6行）应大于等于0!");
		          document.getElementById('7_1').select();
		          document.getElementById('7_1').focus();
		          return false;
		      }
		      <%/*判断第7行数据*/%>
		      if(value_8<0){
		          alert("免税收入（7行）应大于等于0!");
		          document.getElementById('8_1').select();
		          document.getElementById('8_1').focus();
		          return false;
		      }
		      <%/*判断第8行数据*/%>
		      var obj_4_5_6_7_jisuanjieguo = (value_5+value_6-value_7-value_8).toFixed(2);
		      if((1*obj_4_5_6_7_jisuanjieguo>0)&&(value_9>obj_4_5_6_7_jisuanjieguo)){
		          alert("弥补以前年度亏损（8行）应小于等于第4+5-6-7,且应大于等于0！");
		          document.getElementById('9_1').select();
		          document.getElementById('9_1').focus();
		          return false;
		      }
		      if((1*obj_4_5_6_7_jisuanjieguo>0)&&(value_9<0)){
		          alert("弥补以前年度亏损（8行）应大于等于0!");
		          document.getElementById('9_1').select();
		          document.getElementById('9_1').focus();
		          return false;
		      }  
		      if((1*obj_4_5_6_7_jisuanjieguo<=0)&&(value_9!=0)){
		  	      alert("第4+5-6-7行小于等于0，弥补以前年度亏损（8行）应等于0!");
		  	      document.getElementById('9_1').select();
	              document.getElementById('9_1').focus();
				  return false;
		      }    
		      <%/*判断第12行数据*/%>
		      if(value_13>value_12){
		          alert("减：减免所得税额（12行）应小于等于应纳所得税额（11行）,且应大于等于0！");
		          document.getElementById('13_1').select();
		          document.getElementById('13_1').focus();
				  return false;
			  }
			  if(value_13<0){
			      alert("减：减免所得税额（12行）应大于等于0!");
			      document.getElementById('13_1').select();
		          document.getElementById('13_1').focus();
				  return false;
			  }
			  <%/*判断第16行数据*/%>
		      if(value_17>value_16){
		          alert("减：以前年度多缴在本期抵缴所得税额（16行）应小于等于应补（退）所得税额（15行），请核对！");
		          document.getElementById('17_1').select();
		          document.getElementById('17_1').focus();
		        return false;
		      }
		      if(value_17<0){
		          alert("减：以前年度多缴在本期抵缴所得税额（16行）应大于等于0!");
		          document.getElementById('17_1').select();
		          document.getElementById('17_1').focus();
		        return false;
		      }
		      <%/*判断第29行数据*/%>
		      if(value_27<0){
		          alert("总机构独立生产经营部门应分摊所得税额（29行）应大于等于0!");
		          document.getElementById('27_1').select();
		          document.getElementById('27_1').focus();
		          return false;
		        }
		      <%/*判断第30行数据*/%>
		      if(value_28<0){
		          alert("总机构已撤销分支机构应分摊所得税额（30行）应大于等于0!");
		          document.getElementById('28_1').select();
		          document.getElementById('28_1').focus();
		          return false;
		        }
		      <%/*判断第29+30是否大于第28行数据*/%>
		      var obj_29_30_jsjg = (value_27+value_28).toFixed(2); 
		      if(1*obj_29_30_jsjg>value_26){
		          alert("总机构独立生产经营部门应分摊所得税额（29行）与总机构已撤销分支机构应分摊所得税额（30行）之和应小于等于分支机构应分摊所得税额（28行）!");
		          return false;
		        }
			
		//当表头纳税方法选项定义为汇总纳税-分支机构时开放本表第28、31、32行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){			
			var value_26=(document.getElementById('26_1').value)*1;
			var value_29=(document.getElementById('29_1').value)*1;
			var value_30=(document.getElementById('30_1').value)*1;
			
			if(!isNum(document.getElementById('26_1') , null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('29_1') , null, null, null, null, 2)){
				return false;			
			}
			if(!isNum(document.getElementById('30_1') , null, null, null, null, 2)){
				return false;			
			}
			<%/*判断第28行数据*/%>
	        if(value_26<0){
	          alert("分支机构应分摊所得税额（28行）应大于等于0!");
	          document.getElementById('26_1').select();
	          document.getElementById('26_1').focus();
	          return false;
	        }
	        <%/*判断第31行数据*/%>
	        if((value_29<0)||(value_29>100)){
			  alert("分配比例（31行）应填入0-100的数字，请核对！");
			  document.getElementById('29_1').select();
	          document.getElementById('29_1').focus();
	          return false;
		    }
			
		}else{		
			
			//alert("else_end");
		}
		return true;
	}
	
	function compute_Row_1(){
		//alert("compute_Row_1...");
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
		}
		changeSelect();
		checkFilter();
		readOnlyFilter();

		if(document.forms[0].lje_nsfs[0].checked==true&&document.forms[0].lje_zfjg[0].checked==true){
	      //alert("document.forms[0].lje_nsfs[0].checked==="+document.forms[0].lje_nsfs[0].checked);
	      //alert("document.forms[0].ybtsdse.value===="+document.forms[0].ybtsdse.value);
		  document.getElementById('24_1').value=((1*document.getElementById('16_1').value/100)*25).toFixed(2);
		  document.getElementById('25_1').value=((1*document.getElementById('16_1').value/100)*25).toFixed(2);
		  document.getElementById('26_1').value=((1*document.getElementById('16_1').value/100)*50).toFixed(2);
	    }
}
	
	function compute_Row_2(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
		}
		changeSelect();
		checkFilter();
		readOnlyFilter();

		if(document.forms[0].lje_nsfs[0].checked==true){
			document.getElementById('26_1').value="0.00";
	    }
	    if(document.forms[0].lje_nsfs[1].checked==true){
	    	document.getElementById('26_1').value="";
	    }
	}
	<%/*计算第2行（营业收入）数据*/%>
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
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
	}
	<%/*计算第3行（营业成本）数据*/%>
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
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
	}
	<%/*计算第4行（利润总额）数据*/%>
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
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//compute_Row_9();
		compute_Row_10();
	}
	<%/*计算第5行数据*/%>
	function compute_Row_6(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('6_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('6_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
			alert("加：特定业务计算的应纳税所得额（5行）应大于等于0!");
			document.getElementById('6_1').select();
			document.getElementById('6_1').focus();
		}
		//compute_Row_9();
		compute_Row_10();
	}
	<%/*计算第6行数据*/%>
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
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
			alert("减：不征税收入（6行）应大于等于0!");
			document.getElementById('7_1').select();
			document.getElementById('7_1').focus();
		}
		//compute_Row_9();
		compute_Row_10();
	}
	<%/*计算第7行数据*/%>
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
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
			alert("免税收入（7行）应大于等于0!");
			document.getElementById('8_1').select();
			document.getElementById('8_1').focus();
		}
		//compute_Row_9();
		compute_Row_10();
	}
	<%/*计算第8行数据*/%>
	function compute_Row_9(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('9_1').value="0.00";
		}   
        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('9_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}

		var obj_4_5_6_7_jisuanjieguo = (1*document.getElementById('5_1').value+1*document.getElementById('6_1').value-1*document.getElementById('7_1').value-1*document.getElementById('8_1').value).toFixed(2);
		//alert(obj_4_5_6_7_jisuanjieguo);
		if((1*obj_4_5_6_7_jisuanjieguo>0)&&((1*obj_input.value)>(1*obj_4_5_6_7_jisuanjieguo))){
			alert("弥补以前年度亏损（8行）应小于等于第4+5-6-7,且应大于等于0！");
			document.getElementById('9_1').select();
			document.getElementById('9_1').focus();
			//return false;
		}
		if((1*obj_4_5_6_7_jisuanjieguo>0)&&(1*obj_input.value<0)){
			alert("弥补以前年度亏损（8行）应大于等于0!");
			document.getElementById('9_1').select();
			document.getElementById('9_1').focus();
			//return false;
		}
		if((1*obj_4_5_6_7_jisuanjieguo<=0)&&(1*obj_input.value!=0)){
	  	    alert("第4+5-6-7行小于等于0，弥补以前年度亏损（8行）应等于0!");
	  	    //obj_input.value="0.00";
	  	    document.getElementById('9_1').select();
			document.getElementById('9_1').focus();
	        //return false;
	    }
		compute_Row_10();
	}
	<%/*计算第9行数据*/%>
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
		if(obj_input.value==""){
			obj_input.value="0.00";
		}

		document.getElementById('10_1').value=(1*document.getElementById('5_1').value+1*document.getElementById('6_1').value-1*document.getElementById('7_1').value-1*document.getElementById('8_1').value-1*document.getElementById('9_1').value).toFixed(2);
		if((1*document.getElementById('10_1').value)==""){
			document.getElementById('10_1').value="0.00";
		}
		compute_Row_12();
	}
	
	
	<%/*计算第10行数据*/%>
	function compute_Row_11(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('11_1').value="25.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('11_1');
		compute_Row_12();
	}
	<%/*计算第11行数据*/%>
	function compute_Row_12(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('12_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('12_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		
		document.getElementById('12_1').value=((1*document.getElementById('10_1').value/100)*(1*document.getElementById('11_1').value)).toFixed(2);
		
		if(((1*document.getElementById('10_1').value/100)*(1*document.getElementById('11_1').value).toFixed(2))<0){
			obj_input.value="0.00";
		}
		
		if(1*obj_input.value<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		
		compute_Row_16();
	}
	<%/*计算第12行数据*/%>
	function compute_Row_13(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('13_1').value="0.00";
		}   
        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('13_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if((1*obj_input.value)>(1*document.getElementById('12_1').value)){
			alert("减：减免所得税额（12行）应小于等于应纳所得税额（11行）,且应大于等于0！");
			document.getElementById('13_1').select();
			document.getElementById('13_1').focus();
			//return false;
		}
		if(1*obj_input.value<0){
			alert("减：减免所得税额（12行）应大于等于0!");
			document.getElementById('13_1').select();
			document.getElementById('13_1').focus();
			//return false;
		}
		compute_Row_16();
	}
	<%/*计算第13行数据*/%>
	function compute_Row_14(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('14_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('14_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		compute_Row_16();
	}
	<%/*计算第14行数据*/%>
	function compute_Row_15(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('15_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('15_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		compute_Row_16();
	}
	<%/*计算第15行数据*/%>
	function compute_Row_16(){
		//alert("compute_Row_16");
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
		//计算行次10的域值
		document.getElementById('16_1').value=(1*document.getElementById('12_1').value-1*document.getElementById('13_1').value-1*document.getElementById('14_1').value-1*document.getElementById('15_1').value).toFixed(2);
		if((1*document.getElementById('16_1').value)<=0){
		//alert("第15行小于等于0");
			document.getElementById('16_1').value="0.00";
		}
		if((document.forms[0].lje_nsfs[0].checked==1)&&(document.forms[0].lje_zfjg[0].checked==1)){
			compute_Row_24();
			compute_Row_25();
			compute_Row_26();
		}
		compute_Row_18();
	}
	<%/*计算第16行数据*/%>
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
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value>1*document.getElementById('16_1').value){
			alert("减：以前年度多缴在本期抵缴所得税额（16行）应小于等于应补（退）所得税额（15行），请核对！");
			document.getElementById('17_1').select();
			document.getElementById('17_1').focus();
		}
		if(1*obj_input.value<0){
	        alert("减：以前年度多缴在本期抵缴所得税额（16行）应大于等于0!");
	        document.getElementById('17_1').select();
			document.getElementById('17_1').focus();
	        //return false;
	    }
		compute_Row_18();
	}
	<%/*计算第17行数据*/%>
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
		//计算行次17的域值
		document.getElementById('18_1').value=(1*document.getElementById('16_1').value-1*document.getElementById('17_1').value).toFixed(2);
		if((1*document.getElementById('18_1').value)<=0){
		//alert("第17行小于等于0");
			document.getElementById('18_1').value="0.00";
		}
	}
	<%/*计算第26行数据*/%>
	function compute_Row_24(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('24_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('24_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		obj_input.value=((1*document.getElementById('16_1').value/100)*25).toFixed(2);
	}
	<%/*计算第27行数据*/%>
	function compute_Row_25(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('25_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('25_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		obj_input.value=((1*document.getElementById('16_1').value/100)*25).toFixed(2);
	}
	<%/*计算第28行数据*/%>
	function compute_Row_26(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('26_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('26_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			obj_input.value=((1*document.getElementById('16_1').value/100)*50).toFixed(2);
		}
		if(1*obj_input.value<0){
	        alert("分支机构应分摊所得税额（28行）应大于等于0!");
	        document.getElementById('26_1').select();
			document.getElementById('26_1').focus();
	        //return false;
	    }
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
			compute_Row_30();
		}
		//compute_Row_30();
	}
	<%/*计算第29行数据*/%>
	function compute_Row_27(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('27_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('27_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
	        alert("总机构独立生产经营部门应分摊所得税额（29行）应大于等于0!");
	        document.getElementById('27_1').select();
			document.getElementById('27_1').focus();
	      //return false;
	    }
		var obj_29_30_jsjg = (1*document.getElementById('27_1').value+1*document.getElementById('28_1').value).toFixed(2); 

		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(1*obj_29_30_jsjg>(1*document.getElementById('26_1').value)){
		        alert("总机构独立生产经营部门应分摊所得税额（29行）与总机构已撤销分支机构应分摊所得税额（30行）之和应小于等于分支机构应分摊所得税额（28行）!");
		        document.getElementById('27_1').select();
		        document.getElementById('27_1').focus();
		      //return false;
		    }
		}
	}
	<%/*计算第30行数据*/%>
	function compute_Row_28(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('28_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('28_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
	        alert("总机构已撤销分支机构应分摊所得税额（30行）应大于等于0!");
	        document.getElementById('28_1').select();
			document.getElementById('28_1').focus();
	      //return false;
	    }
		var obj_29_30_jsjg = (1*document.getElementById('27_1').value+1*document.getElementById('28_1').value).toFixed(2); 

		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(1*obj_29_30_jsjg>(1*document.getElementById('26_1').value)){
		        alert("总机构独立生产经营部门应分摊所得税额（29行）与总机构已撤销分支机构应分摊所得税额（30行）之和应小于等于分支机构应分摊所得税额（28行）!");
		        document.getElementById('28_1').select();
		        document.getElementById('28_1').focus();
		        //return false;
		    }
		}
	}
	<%/*计算第31行数据*/%>
	function compute_Row_29(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('29_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('29_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if((1*obj_input.value<0)||(1*obj_input.value>100)){
			alert("分配比例（31行）应填入0-100的数字，请核对！");
			document.getElementById('29_1').select();
	        document.getElementById('29_1').focus();
		}
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
			compute_Row_30();
		}
	}
	<%/*计算第32行数据*/%>
	function compute_Row_30(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('30_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('30_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		
		document.getElementById('30_1').value=((1*document.getElementById('26_1').value/100)*(1*document.getElementById('29_1').value)).toFixed(2);
		if(1*obj_input.value<0){
			obj_input.value="0.00";
		}
		//if(obj_input.value==""){
		//	obj_input.value="0.00";
		//}
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

    <%/*查询*/%>
	function doQuery(){
		if(!checkBb()){
			return false;
		}
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

//检查版本
	function checkBb(){
		if(parseInt(document.forms[0].skssjsrq.value)>20140331){
			alert("您正在使用的企业所得税申报表版本与当前的税款所属期间不符，请选择正确的企业所得税申报表版本！！");
			return false;	
		}
		return true;
	}
	<%/*保存*/%>
	function doSave()
	{
		if(!checkBb()){
			return false;
		}
		if(document.forms[0].nsrmc.value==""){
			alert("基本信息不正确,保存失败,请重新输入!");
			document.forms[0].jsjdm.focus();
			return false;
		}	
		if(!saveCheckFilter())
		{
			//alert("数据不正确，无法保存！");
			return false;
		}
		doSubmitForm('Save');	
		
	}
	<%/*查看分配表*/%>
	function doView()
	{
		if(document.forms[0].nsrmc.value==""){
			alert("基本信息不正确,保存失败,请重新输入!");
			document.forms[0].jsjdm.focus();
			return false;
		}	
		if(!saveCheckFilter())
		{
			//alert("数据不正确，无法保存！");
			return false;
		}
		if(document.forms[0].lje_nsfs[1].checked==1){
	      alert("独立纳税无需查看分配表！");
	      return false;
	    }
	    if(confirm("查看分配表将重新保存页面数据，请确认！"))
	    {
	    	doSubmitForm('Save');
	    }
	}
	<%/*清除*/%>
	function doReset()
	{
		if(confirm("确认是否清除当前数据？"))
		{	   	
				if(document.forms[0].lje_nsfs[1].checked==1){
					for(var i=3;i<19;i++){
		   				obj = document.getElementById(i+"_1");
						if(obj!=null){
							obj.value = "0.00";
						}
		   			}
				}
				if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
					for(var i=3;i<29;i++){
		   				obj = document.getElementById(i+"_1");
						if(obj!=null){
							obj.value = "0.00";
						}
		   			}
				}
				if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
					for(var i=26;i<31;i++){
		   				obj = document.getElementById(i+"_1");

						if(obj!=null){
							obj.value = "0.00";
						}
		   			}
					document.getElementById("27_1").value="";
					document.getElementById("28_1").value="";
				}
	   			
			document.getElementById("11_1").value="25.00";
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
	<%/*转入企业所得税汇总纳税分支机构分配表*/%>
	function jumpToZFJG(){
		//document.forms[0].ActionType="Jump";
		//alert("此处需要填写转入企业所得税汇总纳税分支机构分配表》功能的链接");
		doSubmitForm('Jump');	
	}
</script>
</body>
</html:html>
