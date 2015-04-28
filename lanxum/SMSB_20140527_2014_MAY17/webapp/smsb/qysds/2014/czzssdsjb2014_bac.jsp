<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web.CzzssdsjbForm"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<%@ page import="com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.CheckJdlx"%>
<%@ page import="java.util.Iterator"%>
<html:html>
<head>
<title>企业所得税月(季)度预缴纳税申报表(A类)</title>
<!-- <link href="../../css/text.css" rel="stylesheet" type="text/css">
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
	src="../../js/qysdsnew.js"></script> -->
<%--modify   20140828 --%>
<html:base/>
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
<script language=JavaScript type="text/JavaScript"
	src="../../../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/qysdsnew.js"></script>
	
	
	<link href="../../../css/text.css" rel="stylesheet" type="text/css">
	
	<script language="JavaScript" type="text/javascript"
		src="../../../js/My97DatePicker/WdatePicker.js"></script>
    <script language=JavaScript src="../../../js/compute.js">
    </script>
    <script language=JavaScript src="../../../js/reader.js">
    </script>
    <script language=JavaScript src="../../../js/gmit_selectcontrol.js">
    </script>
    <script language="JavaScript" src="../../../js/calculate.js">
    </script>
  </head>
<%--end      20140828--%>
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
<%@include file="../../include/header.jsp"%>
<html:form method="POST" action="/webapp/smsb/qysds/czzssdsjb2014Action.do">
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
	<html:hidden property="sfxkh"/>
	<html:hidden property="syndZsfsdm"/>
	<html:hidden property="syndZbh6"/>
	<html:hidden property="syndZbh25"/>
	<html:hidden property="syndFb5jyjg"/>
	<html:hidden property="sybs"/>
	<html:hidden property="isQuery"/>
	<html:hidden property="isXky"/>
	<html:hidden property="jdlx" />
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
									<html:text tabindex="4" property="jsjdm" maxlength="8" size="15" onChange="doQuery()" style="text-align:left"/>
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
									 <bean:write name="czzssdsjb2014Form" property="nsrsbh" scope="request" filter="true" />
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
									<bean:write name="czzssdsjb2014Form" property="nsrmc" scope="request" filter="true" />
								</div>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<table class="table-99" border="0" cellpadding="0" cellspacing="0" width="80%">
						<tr> 
							<td width="10%" rowspan="2" height="78px" nowrap class="2-td2-left" style="border-top: 1px solid #9BB4CA;">&nbsp;
								<input type="hidden" name='nsfs' id='nsfs' value="">
								<input type='radio' name='lje_nsfs' style='text-align:right' 
									   value='<%=CodeConstant.HZNSFF_QYSDSJB2008_CZZSSDS_HZNS%>' size='13' 
									   onclick="compute_Row_1()" onchange="compute_Row_1()" disabled="true">汇总纳税：
							</td>
							<td width="10%" nowrap class="2-td2-left" style="border-top: 1px solid #9BB4CA;">
								<div align="left">
								<input type="hidden" name='zfjg' id='zfjg' value="">
									<input type='radio' name='lje_zfjg' style='text-align:right' 
										   value='<%=CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_ZJG%>' size='13' 
										   onclick="compute_Row_2()" onchange="compute_Row_2()" disabled="true">总机构
								</div>
							</td>
							<td width="10%" rowspan="2" height="78px" nowrap class="2-td2-left" style="border-top: 1px solid #9BB4CA; border-right: 1px solid #9BB4CA;">&nbsp;
								<input type='radio' name='lje_nsfs' style='text-align:right' 
									   value='<%=CodeConstant.HZNSFF_QYSDSJB2008_CZZSSDS_DLNS%>' size='13'
									   onclick="compute_Row_1()" onchange="compute_Row_1()"  disabled="true" checked>独立纳税
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

              </table>
              <table id="mssrxmAutoTable" border="1" cellspacing="0"
						class="table-99" align="center"> 
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
							onblur="formate(this)" onchange="compute_Row_8()">
							<img onclick="doMssrxmAddRow('','')" src="<%=static_contextpath%>images/zbotton-jia2.gif" alt="增加" name="add" id="add"  style="cursor:hand"></td>
                  </tr>
                  <tr>
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">&nbsp;&nbsp;
							<input type="hidden" name="cbMssrxmYzhc" value="1" id="cbMssrxmYzhc_14">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center" id='mssrxm_div'>
                    	<input type="hidden" name="cbMssrxmDmhc" value="1" id="cbMssrxmDmhc_14">
                    	<input type="hidden" name="cbMssrxmDm" value="1" id="cbMssrxmDm_14">
                    	<html:select name="czzssdsjb2014Form" property="mssrxmdm" onchange="renameId('Mssrxm')">
  						<logic:iterate id="data" indexId="index" length="length" name="czzssdsjb2014Form"
  							property="mssrxmList" type="com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.vo.DmVo">
    					<logic:equal name="data" property="level" value="1">
      						<optgroup label="<bean:write name="data" property="mc"/>">
      						</optgroup>
   		 				</logic:equal>
    					<logic:equal name="data" property="level" value="2">
      						<option value="<bean:write name="data" property="dm"/>">&nbsp;&nbsp;
        						<bean:write name="data" property="mc" />
     						 </option>
    					</logic:equal>
  						</logic:iterate>
					</html:select></div></td>
                    <td width="180" nowrap class="2-td2-center"><input type='text' name='cbMssrxmYz' id='1' size='13' maxlength='13' value="" onblur='' onchange="cbsjHj('Mssrxm')"></td>
                  </tr>			
			  </table>
              <table id="jzmzxmAutoTable" border="1" cellspacing="0"
						class="table-99" align="center"> 
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">8
							<input type="hidden" name="hc" value="9" id="hc_9">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;减征、免征应纳税所得额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="10" type='text' name='lje' 
							id='9_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_9()">
							<img onclick="doJzmzxmAddRow('','')" src="<%=static_contextpath%>images/zbotton-jia2.gif" alt="增加" name="add" id="add"  style="cursor:hand"></td>
                  </tr>
                  <tr>
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">&nbsp;&nbsp;
							<input type="hidden" name="cbJzmzxmYzhc" value="1" id="cbJzmzxmYzhc_14">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center" id='jzmzxm_div'>
                    	<input type="hidden" name="cbJzmzxmDmhc" value="1" id="cbJzmzxmDmhc_14">
                    	<input type="hidden" name="cbJzmzxmDm" value="1" id="cbJzmzxmDm_14">
                    	<html:select name="czzssdsjb2014Form" property="jzmzxmdm" onchange="renameId('Jzmzxm')">
  						<logic:iterate id="data" indexId="index" length="length" name="czzssdsjb2014Form"
  							property="jzmzxmList" type="com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.vo.DmVo">
    					<logic:equal name="data" property="level" value="1">
      						<optgroup label="<bean:write name="data" property="mc"/>">
      						</optgroup>
   		 				</logic:equal>
    					<logic:equal name="data" property="level" value="2">
      						<option value="<bean:write name="data" property="dm"/>">&nbsp;&nbsp;
        						<bean:write name="data" property="mc" />
     						 </option>
    					</logic:equal>
  						</logic:iterate>
					</html:select></div></td>
                    <td width="180" nowrap class="2-td2-center"><input type='text' name='cbJzmzxmYz' id='1' size='13' maxlength='13' value="" onblur='' onchange="cbsjHj('Jzmzxm')"></td>
                  </tr>			
			  </table>
              <table id="wrklistTable3" border="1" cellspacing="0"
						class="table-99" align="center">                   
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">9
							<input type="hidden" name="hc" value="10" id="hc_10">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;弥补以前年度亏损</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="11" type='text' name='lje' 
							id='10_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_10()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">10
							<input type="hidden" name="hc" value="11" id="hc_11">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">实际利润额（4行+5行-6行-7行-8行-9行）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="12" type='text' name='lje' 
							id='11_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_11()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">11
							<input type="hidden" name="hc" value="12" id="hc_12">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">税率（25%）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						&nbsp;<input readonly tabindex="13" type='text' name='lje' 
							id='12_1' style='text-align:right' 
							value='25.00' size='13'
							onblur="formate(this)" onchange="compute_Row_12()">%</td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">12
							<input type="hidden" name="hc" value="13" id="hc_13">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">应纳所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="14" type='text' name='lje' 
							id='13_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_13()"></td>
                  </tr>
                </table>
              <table id="jmxmAutoTable" border="1" cellspacing="0"
						class="table-99" align="center"> 
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">13
							<input type="hidden" name="hc" value="14" id="hc_14">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">减：减免所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="15" type='text' name='lje' 
							id='14_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="">
						<img onclick="doJmxmAddRow('','')" src="<%=static_contextpath%>images/zbotton-jia2.gif" alt="增加" name="add" id="add"  style="cursor:hand">
					</td>
                  </tr>	
                  <tr>
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">&nbsp;&nbsp;
							<input type="hidden" name="cbJmxmYzhc" value="1" id="cbJmxmYzhc_14">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">&nbsp;</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center" id='jmxm_div'>
                    	<input type="hidden" name="cbJmxmDmhc" value="1" id="cbJmxmDmhc_14">
                    	<input type="hidden" name="cbJmxmDm" value="1" id="cbJmxmDm_14">
                    	<html:select name="czzssdsjb2014Form" property="jmxmdm" onchange="renameId('Jmxm')">
  						<logic:iterate id="data" indexId="index" length="length" name="czzssdsjb2014Form"
  							property="jmxmList" type="com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.vo.DmVo">
    					<logic:equal name="data" property="level" value="1">
      						<optgroup label="<bean:write name="data" property="mc"/>">
      						</optgroup>
   		 				</logic:equal>
    					<logic:equal name="data" property="level" value="2">
      						<option value="<bean:write name="data" property="dm"/>">&nbsp;&nbsp;
        						<bean:write name="data" property="mc" />
     						 </option>
    					</logic:equal>
  						</logic:iterate>
					</html:select></div></td>
                    <td width="180" nowrap class="2-td2-center"><input type='text' name='cbJmxmYz' id='1' size='13' maxlength='13' value="" onblur='' onchange="cbsjHj('Jmxm')"></td>
                  </tr>			
			  </table>
              <table id="wrklistTable4" border="1" cellspacing="0"
						class="table-99" align="center"> 
                   <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">14
							<input type="hidden" name="hc" value="15" id="hc_15">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;其中：符合条件的小型微利企业减免所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="15" type='text' name='lje' 
							id='15_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_15()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">15
							<input type="hidden" name="hc" value="16" id="hc_16">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">减：实际已预缴所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="16" type='text' name='lje' 
							id='16_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_16()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">16
							<input type="hidden" name="hc" value="17" id="hc_17">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">减：特定业务预缴（征）所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="17" type='text' name='lje' 
							id='17_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_17()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">17
							<input type="hidden" name="hc" value="18" id="hc_18">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">应补（退）所得税额（12行-13行-15行-16行）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="18" type='text' name='lje' 
							id='18_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_18()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">18
							<input type="hidden" name="hc" value="19" id="hc_19">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">减：以前年度多缴在本期抵缴所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="19" type='text' name='lje' 
							id='19_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_19()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">19
							<input type="hidden" name="hc" value="20" id="hc_20">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">本月（季）实际应补（退）所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="20" type='text' name='lje' 
							id='20_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_20()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">20
						</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>二、按照上一纳税年度应纳税所得额平均额预缴</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">21
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">上一纳税年度应纳税所得额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">22
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">本月（季）应纳税所得额（21行×1/4或1/12）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">23
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">税率(25%)</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">24
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">本月（季）应纳所得税额（22行×23行）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                   <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">25
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">减：符合条件的小型微利企业减免所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                   <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">26
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">本月（季）实际应纳所得税额（24行-25行）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">27
						</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>三、按照税务机关确定的其他方法预缴</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">28
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">本月（季）税务机关确定的预缴所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">29
						</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="center"><B>总分机构纳税人</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">30
							<input type="hidden" name="hc" value="30" id="hc_30">
						</div></td>
                    <td rowspan="4" width="20%" class="2-td2-left"><div align="center">总机构</div></td>
                    <td nowrap class="2-td2-left"><div align="left">总机构分摊所得税额（19行或26行或28行×总机构分摊预缴比例）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="30" type='text' name='lje' 
							id='30_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_30()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">31
							<input type="hidden" name="hc" value="31" id="hc_31">
						</div></td>
                    <td nowrap class="2-td2-left"><div align="left">财政集中分配所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="31" type='text' name='lje' 
							id='31_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_31()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">32
							<input type="hidden" name="hc" value="32" id="hc_32">
						</div></td>
                    <td nowrap class="2-td2-left"><div align="left">分支机构分摊所得税额(19行或26行或28行×分支机构分摊比例）</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="32" type='text' name='lje' 
							id='32_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_32()"></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">33
							<input type="hidden" name="hc" value="33" id="hc_33">
						</div></td>
                    <td nowrap class="2-td2-left"><div align="left">其中：总机构独立生产经营部门应分摊所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="33" type='text' name='lje' 
							id='33_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_33()"></td>
                  </tr>
                  <!--  
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
                  -->
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">34
							<input type="hidden" name="hc" value="34" id="hc_34">
						</div></td>
                    <td rowspan="2" nowrap class="2-td2-left"><div align="center">分支机构</div></td>
                    <td nowrap class="2-td2-left"><div align="left">分配比例</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						&nbsp;<input tabindex="34" type='text' name='lje' 
							id='34_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_34()">%</td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">35
							<input type="hidden" name="hc" value="35" id="hc_35">
						</div></td>
                    <td nowrap class="2-td2-left"><div align="left">分配所得税额</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="33" type='text' name='lje' 
							id='35_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_35()"></td>
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
<%@ include file="../../include/footer.jsp"%>
<!--  
<table width="100%" height="33" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td background="<%=static_contextpath%>images/q-bottom-bg-01.jpg" height="9px" colspan="2" nowrap></td>
  </tr>
  <tr>
    <td nowrap><font size="2">&nbsp;&nbsp;&nbsp;* 必须使用 IE 5.5 以上，分辨率 800*600 浏览本网站</font></td>
    <td height="24" align="right"><img src="<%=static_contextpath%>images/q-bottom-tu-01.jpg" alt="承办单位：清华同方软件股份有限公司2007_OCT24" width="184" height="24"></td>
  </tr>
</table>
-->
</html:form>
<script language="JavaScript">

	<%/*初始化*/%>	
	function doInit()	
	{
		//alert("初始化...");
		<%
		CzzssdsjbForm jbForm=(CzzssdsjbForm)request.getAttribute("czzssdsjb2014Form");
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
				}else if(hc==12){
					%>		
					//强制赋值25
					document.getElementById('12_1').value="25.00";
					<% 
				}else{
					%>				
					obj = eval("document.getElementById('<%=hc%>_1')");
					obj.value = '<%=lje%>';
					<% 
				}
			}			
		}
		
		
		
					
		//获取从表免税收入项目中数据
		if(jbForm!=null && jbForm.getCbsbMssrxmList()!=null && jbForm.getCbsbMssrxmList().size()>0){	
			HashMap cbmap=(HashMap)jbForm.getCbsbMssrxmList().get(0);
			Iterator it =cbmap.keySet().iterator();
			
			for(int i=0;i<cbmap.size();i++){
				String dmKey="777."+(i+1);
				i++;				
				String yzKey="777."+(i+1);	
				if(null==cbmap.get(yzKey)||"null"==cbmap.get(yzKey)||"".equals(cbmap.get(yzKey))){
					continue;
				}		
				if(i==1){%>
					//alert(<%=cbmap.get(yzKey)%>);
					document.getElementsByName("cbMssrxmYzhc")[0].value=<%=yzKey%>;
					document.getElementsByName("cbMssrxmYz")[0].value=<%=cbmap.get(yzKey)%>;	
					document.getElementsByName("cbMssrxmDmhc")[0].value=<%=dmKey%>;
					document.getElementsByName("cbMssrxmDm")[0].value=<%=cbmap.get(dmKey)%>;					
					document.getElementsByName("mssrxmdm")[0].value="<%=cbmap.get(dmKey)%>";		
					//alert(<%=cbmap.get(dmKey)%>);			
				<%}else{%>
					doMssrxmAddRow(<%=yzKey%>,<%=cbmap.get(yzKey)%>);
					var max=document.getElementsByName("cbMssrxmDmhc").length;
					document.getElementsByName("cbMssrxmDmhc")[max-1].value=<%=dmKey%>;
					document.getElementsByName("cbMssrxmDm")[max-1].value=<%=cbmap.get(dmKey)%>;	
					document.getElementsByName("mssrxmdm")[max-1].value="<%=cbmap.get(dmKey)%>";	

				<%}
						
			}
		}
		
				//获取从表减征、免征项目中数据
		if(jbForm!=null && jbForm.getCbsbJzmzxmList()!=null && jbForm.getCbsbJzmzxmList().size()>0){	
			HashMap cbmap=(HashMap)jbForm.getCbsbJzmzxmList().get(0);
			Iterator it =cbmap.keySet().iterator();
			
			for(int i=0;i<cbmap.size();i++){
				String dmKey="888."+(i+1);
				i++;
				String yzKey="888."+(i+1);
				if(null==cbmap.get(yzKey)||"null"==cbmap.get(yzKey)||"".equals(cbmap.get(yzKey))){
					continue;
				}							
				if(i==1){%>
					//alert(<%=cbmap.get(yzKey)%>);
					document.getElementsByName("cbJzmzxmYzhc")[0].value=<%=yzKey%>;
					document.getElementsByName("cbJzmzxmYz")[0].value=<%=cbmap.get(yzKey)%>;	
					document.getElementsByName("cbJzmzxmDmhc")[0].value=<%=dmKey%>;
					document.getElementsByName("cbJzmzxmDm")[0].value=<%=cbmap.get(dmKey)%>;					
					document.getElementsByName("jzmzxmdm")[0].value="<%=cbmap.get(dmKey)%>";		
					//alert(<%=cbmap.get(dmKey)%>);			
				<%}else{%>
					doJzmzxmAddRow(<%=yzKey%>,<%=cbmap.get(yzKey)%>);
					var max=document.getElementsByName("cbJzmzxmDmhc").length;
					document.getElementsByName("cbJzmzxmDmhc")[max-1].value=<%=dmKey%>;
					document.getElementsByName("cbJzmzxmDm")[max-1].value=<%=cbmap.get(dmKey)%>;	
					document.getElementsByName("jzmzxmdm")[max-1].value="<%=cbmap.get(dmKey)%>";	

				<%}
			
			}
		}
		
				//获取从表减征、免征项目中数据
		if(jbForm!=null && jbForm.getCbsb_qysdsjbList()!=null && jbForm.getCbsb_qysdsjbList().size()>0){	
			HashMap cbmap=(HashMap)jbForm.getCbsb_qysdsjbList().get(0);
			Iterator it =cbmap.keySet().iterator();
			
			for(int i=0;i<cbmap.size();i++){
				String dmKey="999."+(i+1);
				i++;	
				String yzKey="999."+(i+1);
				if(null==cbmap.get(yzKey)||"null"==cbmap.get(yzKey)||"".equals(cbmap.get(yzKey))){
					continue;
				}						
				if(i==1){%>
					//alert(<%=cbmap.get(yzKey)%>);
					document.getElementsByName("cbJmxmYzhc")[0].value=<%=yzKey%>;
					document.getElementsByName("cbJmxmYz")[0].value=<%=cbmap.get(yzKey)%>;	
					document.getElementsByName("cbJmxmDmhc")[0].value=<%=dmKey%>;
					document.getElementsByName("cbJmxmDm")[0].value=<%=cbmap.get(dmKey)%>;					
					document.getElementsByName("jmxmdm")[0].value="<%=cbmap.get(dmKey)%>";		
					//alert(<%=cbmap.get(dmKey)%>);			
				<%}else{%>
					doJmxmAddRow(<%=yzKey%>,<%=cbmap.get(yzKey)%>);
					var max=document.getElementsByName("cbJmxmDmhc").length;
					document.getElementsByName("cbJmxmDmhc")[max-1].value=<%=dmKey%>;
					document.getElementsByName("cbJmxmDm")[max-1].value=<%=cbmap.get(dmKey)%>;	
					document.getElementsByName("jmxmdm")[max-1].value="<%=cbmap.get(dmKey)%>";	

				<%}
			
			}
		}
		%>
		<%
		System.out.println("存储标记="+jbForm.getSAVE_FLAG());
		if("1".equals(jbForm.getSAVE_FLAG())){
		%>
			//if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(document.forms[0].lje_nsfs[0].checked==1){
			
				if(document.getElementById('isXky').value=="Y"){
					//modified by zhangj,2014.12.08	
					if(confirm("请确认该企业本季度是否已享受固定资产加速折旧（扣除）政策？")){
						document.forms[0].actionType.value="JumpGdzc";
						document.forms[0].submit();
						return false;
					}	
					alert("申报已成功！");	
				}else{
					//保存成功，提示转总分机构申报表
					//if(confirm("保存成功，是否转入《企业所得税汇总纳税分支机构分配表》？")){
						alert("保存成功，请继续填写《企业所得税汇总纳税分支机构分配表》！");
						jumpToZFJG();
					//}
				}
			}else if(document.forms[0].lje_nsfs[1].checked==1){
				//alert("申报已成功！");
				//modified by zhangj,2014.12.08	
				if(confirm("请确认该企业本季度是否已享受固定资产加速折旧（扣除）政策？")){
					document.forms[0].actionType.value="JumpGdzc";
					document.forms[0].submit();
					return false;
				}	
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
		
		if(!isNumJbCheck(document.getElementById(row+'_1'), zero, 9999999999999, null, 16, 2)){
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
		
		//if(document.forms[0].sybs.value!=""){
		//	if(document.forms[0].sybs.value==<%=CodeConstant.CODE_QYSDS_SYBS_D%>){
		//		document.forms[0].lje_nsfs[1].checked=1;
		//	}else if(document.forms[0].sybs.value==<%=CodeConstant.CODE_QYSDS_SYBS_Z%>){
		//		document.forms[0].lje_nsfs[0].checked=1;
		//		document.forms[0].lje_zfjg[0].checked=1;
		//	}else if(document.forms[0].sybs.value==<%=CodeConstant.CODE_QYSDS_SYBS_F%>){
		//		document.forms[0].lje_nsfs[0].checked=1;
		//		document.forms[0].lje_zfjg[1].checked=1;
		//	}else{
		//		alert("该企业不需要申报企业所得税月(季)度预缴纳税申报表(A类)表!");
		//		tuichu();
		//	}
		//}
		
		//获取鉴定类型
		var jdlx = document.forms[0].jdlx.value;
		if(jdlx!=""){
		//独立纳税人---||---总分机构均在本市的总机构纳税人
		if(jdlx=='<%=CheckJdlx.QYSDSZGFWJDLX_DLNSR%>' || jdlx=='<%=CheckJdlx.QYSDSZGFWJDLX_ZFJGJZBSZJGNSR%>')
		{
			document.forms[0].lje_nsfs[1].checked=1;
		}else if(jdlx=='<%=CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR%>')
		{//跨省市分支机构纳税人
			document.forms[0].lje_nsfs[0].checked = true;
			document.forms[0].lje_zfjg[1].checked = true;
		}else if(jdlx=='<%=CheckJdlx.QYSDSZGFWJDLX_KSSZJGNSR%>')
		{//跨省市总机构纳税人
		    document.forms[0].lje_nsfs[0].checked = true;
			document.forms[0].lje_zfjg[0].checked = true;
		}else{
			alert("该企业不需要申报企业所得税月(季)度预缴纳税申报表(A类)表!");
			tuichu();
		}
		}
		if(document.forms[0].lje_nsfs[1].checked == 1)
		{
			//选择为独立纳税的，清除汇总纳税方式子项选择，同时将这些子项置位不可编辑
			document.forms[0].lje_zfjg[0].checked = false;
			document.forms[0].lje_zfjg[1].checked = false;
			document.forms[0].nsfs.value="2";
			document.forms[0].zfjg.value="";
		}
		else if(document.forms[0].lje_nsfs[0].checked == 1)
		{
			if(document.forms[0].lje_zfjg[0].checked==1){
				document.forms[0].lje_zfjg[0].checked = true;
				document.forms[0].lje_zfjg[1].checked = false;
				document.forms[0].nsfs.value="1";
				document.forms[0].zfjg.value="1";
			}
			
			if(document.forms[0].lje_zfjg[1].checked==1){
				document.forms[0].lje_zfjg[0].checked = false;
				document.forms[0].lje_zfjg[1].checked = true;
				document.forms[0].nsfs.value="1";
				document.forms[0].zfjg.value="2";
			}
		}
		//readOnlyFilter();
	}
	
	function checkFilter(){
		//表头纳税方法选项定义为独立纳税时，系统仅开放本表第2-19行，第18至35行屏蔽
		if(document.forms[0].lje_nsfs[1].checked==1){
			document.getElementById('12_1').value="25.00";
			document.getElementById('12_1').readOnly=true;

			
//			alert("checkFilter....");
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
			//document.getElementById('15_1').disabled=false;
			document.getElementById('16_1').disabled=false;
			document.getElementById('17_1').disabled=false;
			document.getElementById('18_1').disabled=false;
			document.getElementById('19_1').disabled=false;
			document.getElementById('20_1').disabled=false;
			document.getElementById('30_1').disabled=true;
			document.getElementById('31_1').disabled=true;
			document.getElementById('32_1').disabled=true;
			document.getElementById('33_1').disabled=true;
//			document.getElementById('28_1').disabled=true;
			document.getElementById('34_1').disabled=true;
			document.getElementById('35_1').disabled=true;

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
			if(document.getElementById('19_1').value == null || document.getElementById('19_1').value == "")
			{
				document.getElementById('19_1').value="0.00";
			}
			if(document.getElementById('20_1').value == null || document.getElementById('20_1').value == "")
			{
				document.getElementById('20_1').value="0.00";
			}
			document.getElementById('30_1').value="";
			document.getElementById('31_1').value="";
			document.getElementById('32_1').value="";
			document.getElementById('33_1').value="";
//			document.getElementById('28_1').value="";
			document.getElementById('34_1').value="";
			document.getElementById('35_1').value="";

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
			document.getElementById('19_1').style.backgroundColor="";
			document.getElementById('20_1').style.backgroundColor="";
			document.getElementById('30_1').style.backgroundColor="#aaaaaa";
			document.getElementById('31_1').style.backgroundColor="#aaaaaa";
			document.getElementById('32_1').style.backgroundColor="#aaaaaa";
			document.getElementById('33_1').style.backgroundColor="#aaaaaa";
//			document.getElementById('28_1').style.backgroundColor="#aaaaaa";
			document.getElementById('34_1').style.backgroundColor="#aaaaaa";
			document.getElementById('35_1').style.backgroundColor="#aaaaaa";

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
//			document.getElementById('hc_15').disabled=false;
			document.getElementById('hc_16').disabled=false;
			document.getElementById('hc_17').disabled=false;
			document.getElementById('hc_18').disabled=false;
			document.getElementById('hc_19').disabled=false;
			document.getElementById('hc_20').disabled=false;
			document.getElementById('hc_30').disabled=true;
			document.getElementById('hc_31').disabled=true;
			document.getElementById('hc_32').disabled=true;
			document.getElementById('hc_33').disabled=true;
//			document.getElementById('hc_28').disabled=true;
			document.getElementById('hc_34').disabled=true;
			document.getElementById('hc_35').disabled=true;
		//当表头纳税方法选项定义为汇总纳税-总机构时开放本表第2-17、32-35行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			document.getElementById('12_1').value="25.00";
			document.getElementById('12_1').readOnly=true;

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
//			document.getElementById('15_1').disabled=false;
			document.getElementById('16_1').disabled=false;
			document.getElementById('17_1').disabled=false;
			document.getElementById('18_1').disabled=false;
			document.getElementById('19_1').disabled=false;
			document.getElementById('20_1').disabled=false;
			document.getElementById('30_1').disabled=false;
			document.getElementById('31_1').disabled=false;
			document.getElementById('32_1').disabled=false;
			document.getElementById('33_1').disabled=false;
//			document.getElementById('28_1').disabled=false;
			document.getElementById('34_1').disabled=true;
			document.getElementById('35_1').disabled=true;

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
			if(document.getElementById('19_1').value == null || document.getElementById('19_1').value == "")
			{
				document.getElementById('19_1').value="0.00";
			}
			if(document.getElementById('20_1').value == null || document.getElementById('20_1').value == "")
			{
				document.getElementById('20_1').value="0.00";
			}
			if(document.getElementById('30_1').value == null || document.getElementById('30_1').value == "")
			{
				document.getElementById('30_1').value="0.00";
			}
			if(document.getElementById('31_1').value == null || document.getElementById('31_1').value == "")
			{
				document.getElementById('31_1').value="0.00";
			}
			if(document.getElementById('32_1').value == null || document.getElementById('32_1').value == "")
			{
				document.getElementById('32_1').value="0.00";
			}
			if(document.getElementById('33_1').value == null || document.getElementById('33_1').value == "")
			{
				document.getElementById('33_1').value="0.00";
			}
//			if(document.getElementById('28_1').value == null || document.getElementById('28_1').value == "")
//			{
//				document.getElementById('28_1').value="0.00";
//			}
			document.getElementById('34_1').value="";
			document.getElementById('35_1').value="";

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
//			document.getElementById('15_1').style.backgroundColor="";
			document.getElementById('16_1').style.backgroundColor="";
			document.getElementById('17_1').style.backgroundColor="";
			document.getElementById('18_1').style.backgroundColor="";
			document.getElementById('19_1').style.backgroundColor="";
			document.getElementById('20_1').style.backgroundColor="";
			document.getElementById('30_1').style.backgroundColor="";
			document.getElementById('31_1').style.backgroundColor="";
			document.getElementById('32_1').style.backgroundColor="";
			document.getElementById('33_1').style.backgroundColor="";
//			document.getElementById('28_1').style.backgroundColor="";
			document.getElementById('34_1').style.backgroundColor="#aaaaaa";
			document.getElementById('35_1').style.backgroundColor="#aaaaaa";

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
//			document.getElementById('hc_15').disabled=false;
			document.getElementById('hc_16').disabled=false;
			document.getElementById('hc_17').disabled=false;
			document.getElementById('hc_18').disabled=false;
			document.getElementById('hc_19').disabled=false;
			document.getElementById('hc_20').disabled=false;
			document.getElementById('hc_30').disabled=false;
			document.getElementById('hc_31').disabled=false;
			document.getElementById('hc_32').disabled=false;
			document.getElementById('hc_33').disabled=false;
//			document.getElementById('hc_28').disabled=false;
			document.getElementById('hc_34').disabled=true;
			document.getElementById('hc_35').disabled=true;
			
		//当表头纳税方法选项定义为汇总纳税-分支机构时开放本表第28、34、35行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){	
			document.getElementById('12_1').value="25.00";
			document.getElementById('12_1').readOnly=true;

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
			document.getElementById('19_1').disabled=true;
			document.getElementById('20_1').disabled=true;
			document.getElementById('30_1').disabled=true;
			document.getElementById('31_1').disabled=true;
			document.getElementById('32_1').disabled=false;
			document.getElementById('33_1').disabled=true;
//			document.getElementById('28_1').disabled=true;
			document.getElementById('34_1').disabled=false;
			document.getElementById('35_1').disabled=false;

			//将被屏蔽行次值清空			
			document.getElementById('3_1').value="";
			document.getElementById('4_1').value="";
			document.getElementById('5_1').value="";
			document.getElementById('6_1').value="";
			document.getElementById('7_1').value="";
			document.getElementById('8_1').value="";
			document.getElementById('9_1').value="";
			document.getElementById('10_1').value="";
			document.getElementById('11_1').value="";
			//document.getElementById('12_1').value="";
			document.getElementById('13_1').value="";
			document.getElementById('14_1').value="";
			document.getElementById('15_1').value="";
			document.getElementById('16_1').value="";
			document.getElementById('17_1').value="";
			document.getElementById('18_1').value="";
			document.getElementById('19_1').value="";
			document.getElementById('20_1').value="";
			document.getElementById('30_1').value="";
			document.getElementById('31_1').value="";
			document.getElementById('33_1').value="";
//			document.getElementById('28_1').value="";
			
			if(document.getElementById('32_1').value == null || document.getElementById('32_1').value == "")
			{
				document.getElementById('32_1').value="0.00";
			}
			if(document.getElementById('34_1').value == null || document.getElementById('34_1').value == "")
			{
				document.getElementById('34_1').value="0.00";
			}
			if(document.getElementById('35_1').value == null || document.getElementById('35_1').value == "")
			{
				document.getElementById('35_1').value="0.00";
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
			document.getElementById('19_1').style.backgroundColor="#aaaaaa";
			document.getElementById('20_1').style.backgroundColor="#aaaaaa";
			document.getElementById('30_1').style.backgroundColor="#aaaaaa";
			document.getElementById('31_1').style.backgroundColor="#aaaaaa";			
			document.getElementById('32_1').style.backgroundColor="";
			document.getElementById('33_1').style.backgroundColor="#aaaaaa";
//			document.getElementById('28_1').style.backgroundColor="#aaaaaa";
			document.getElementById('34_1').style.backgroundColor="";
			document.getElementById('35_1').style.backgroundColor="";
			

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
			document.getElementById('hc_19').disabled=true;
			document.getElementById('hc_20').disabled=true;
			document.getElementById('hc_30').disabled=true;
			document.getElementById('hc_31').disabled=true;
			document.getElementById('hc_32').disabled=false;
			document.getElementById('hc_33').disabled=true;			
//			document.getElementById('hc_28').disabled=true;						
			document.getElementById('hc_34').disabled=false;
			document.getElementById('hc_35').disabled=false;
		}else{		
			document.getElementById('12_1').value="25";
			document.getElementById('12_1').readOnly=true;

			

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
//			document.getElementById('15_1').disabled=false;
			document.getElementById('16_1').disabled=false;
			document.getElementById('17_1').disabled=false;
			document.getElementById('18_1').disabled=false;
			document.getElementById('19_1').disabled=false;
			document.getElementById('20_1').disabled=false;
			document.getElementById('30_1').disabled=false;
			document.getElementById('31_1').disabled=false;
			document.getElementById('32_1').disabled=false;
			document.getElementById('33_1').disabled=false;
//			document.getElementById('28_1').disabled=false;
			document.getElementById('34_1').disabled=false;
			document.getElementById('35_1').disabled=false;

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
			document.getElementById('19_1').style.backgroundColor="";
			document.getElementById('20_1').style.backgroundColor="";
			document.getElementById('30_1').style.backgroundColor="#aaaaaa";
			document.getElementById('31_1').style.backgroundColor="#aaaaaa";
			document.getElementById('32_1').style.backgroundColor="#aaaaaa";
			document.getElementById('33_1').style.backgroundColor="#aaaaaa";
//			document.getElementById('28_1').style.backgroundColor="#aaaaaa";
			document.getElementById('34_1').style.backgroundColor="#aaaaaa";
			document.getElementById('35_1').style.backgroundColor="#aaaaaa";

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
//			document.getElementById('hc_15').disabled=false;
			document.getElementById('hc_16').disabled=false;
			document.getElementById('hc_17').disabled=false;
			document.getElementById('hc_18').disabled=false;
			document.getElementById('hc_19').disabled=false;
			document.getElementById('hc_20').disabled=false;
			document.getElementById('hc_30').disabled=false;
			document.getElementById('hc_31').disabled=false;
			document.getElementById('hc_32').disabled=false;
			document.getElementById('hc_33').disabled=false;
//			document.getElementById('hc_28').disabled=false;
			document.getElementById('hc_34').disabled=false;
			document.getElementById('hc_35').disabled=false;
		}
	}

	function readOnlyFilter(){
		//alert("readOnlyFilter()");
		//表头纳税方法选项定义为独立纳税时，系统仅开放本表第2-19行，第18至35行屏蔽
		if(document.forms[0].lje_nsfs[1].checked==1){
		//alert("readOnlyFilter()--1");
			
			//以下是自动计算部分屏蔽只读
			document.getElementById('11_1').readOnly=true;
			document.getElementById('12_1').readOnly=true;
			document.getElementById('13_1').readOnly=true;
//			document.getElementById('15_1').readOnly=true;
			document.getElementById('18_1').readOnly=true;
			document.getElementById('20_1').readOnly=true;
			//document.getElementById('30_1').readOnly=true;
			//document.getElementById('32_1').readOnly=true;
			
			document.getElementById('11_1').style.backgroundColor="#FAEBD7";
			document.getElementById('12_1').style.backgroundColor="#FAEBD7";
			document.getElementById('13_1').style.backgroundColor="#FAEBD7";
//			document.getElementById('15_1').style.backgroundColor="#FAEBD7";
			document.getElementById('18_1').style.backgroundColor="#FAEBD7";
			document.getElementById('20_1').style.backgroundColor="#FAEBD7";
		//当表头纳税方法选项定义为汇总纳税-总机构时开放本表第2-19、32-35行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
		//alert("readOnlyFilter()--2");
			
			//以下是自动计算部分屏蔽只读
			
			document.getElementById('11_1').readOnly=true;
			document.getElementById('12_1').readOnly=true;
			document.getElementById('13_1').readOnly=true;
//			document.getElementById('15_1').readOnly=true;
			document.getElementById('18_1').readOnly=true;
			document.getElementById('20_1').readOnly=true;
			document.getElementById('30_1').readOnly=true;
			document.getElementById('31_1').readOnly=true;
			document.getElementById('32_1').readOnly=true;
			document.getElementById('33_1').readOnly=false;
//			document.getElementById('28_1').readOnly=false;
			
			document.getElementById('11_1').style.backgroundColor="#FAEBD7";
			document.getElementById('12_1').style.backgroundColor="#FAEBD7";
			document.getElementById('13_1').style.backgroundColor="#FAEBD7";
//			document.getElementById('15_1').style.backgroundColor="#FAEBD7";
			document.getElementById('18_1').style.backgroundColor="#FAEBD7";
			document.getElementById('20_1').style.backgroundColor="#FAEBD7";
			document.getElementById('30_1').style.backgroundColor="#FAEBD7";
			document.getElementById('31_1').style.backgroundColor="#FAEBD7";
			document.getElementById('32_1').style.backgroundColor="#FAEBD7";

		//当表头纳税方法选项定义为汇总纳税-分支机构时开放本表第28、34、35行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){	
		//alert("readOnlyFilter()--3");
			
			//以下是自动计算部分屏蔽只读
			document.getElementById('11_1').readOnly=true;
			document.getElementById('12_1').readOnly=true;
			document.getElementById('13_1').readOnly=true;
			document.getElementById('18_1').readOnly=true;
			document.getElementById('20_1').readOnly=true;
			//document.getElementById('30_1').readOnly=true;
			document.getElementById('32_1').readOnly=false;
			document.getElementById('35_1').readOnly=true;
			
			document.getElementById('11_1').style.backgroundColor="#FAEBD7";
			//document.getElementById('12_1').style.backgroundColor="#FAEBD7";
			document.getElementById('13_1').style.backgroundColor="#FAEBD7";
			document.getElementById('18_1').style.backgroundColor="#FAEBD7";
			document.getElementById('20_1').style.backgroundColor="#FAEBD7";
			document.getElementById('35_1').style.backgroundColor="#FAEBD7";
		}else{		
			//alert("readOnlyFilter()--4");
			
			//以下是自动计算部分屏蔽只读
			document.getElementById('11_1').readOnly=true;
			document.getElementById('12_1').readOnly=true;
			document.getElementById('13_1').readOnly=true;
//			document.getElementById('15_1').readOnly=true;
			document.getElementById('18_1').readOnly=true;
			//document.getElementById('30_1').readOnly=true;
			//document.getElementById('32_1').readOnly=true;
			
			document.getElementById('11_1').style.backgroundColor="#FAEBD7";
			document.getElementById('12_1').style.backgroundColor="#FAEBD7";
			document.getElementById('13_1').style.backgroundColor="#FAEBD7";
//			document.getElementById('15_1').style.backgroundColor="#FAEBD7";
			document.getElementById('18_1').style.backgroundColor="#FAEBD7";
			//document.getElementById('30_1').style.backgroundColor="#FAEBD7";
			//document.getElementById('32_1').style.backgroundColor="#FAEBD7";
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
		
		//表头纳税方法选项定义为独立纳税时，系统仅开放本表第2-19行，第18至35行屏蔽
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
			var value_11=(document.getElementById('11_1').value)*1;
			var value_12=((document.getElementById('12_1').value)*1)/100;
			var value_13=(document.getElementById('13_1').value)*1;
			var value_14=(document.getElementById('14_1').value)*1;
			var value_15=(document.getElementById('15_1').value)*1;
			var value_16=(document.getElementById('16_1').value)*1;
			var value_17=(document.getElementById('17_1').value)*1;
			var value_18=(document.getElementById('18_1').value)*1;
			var value_19=(document.getElementById('19_1').value)*1;
			var value_20=(document.getElementById('20_1').value)*1;
			
			if(!isNumJbCheck(document.getElementById('3_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('4_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('5_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('6_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('7_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('8_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('10_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('13_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('14_1'), null, null, null, null, 2)){
				return false;			
			}
			
			if(!isNumJbCheck(document.getElementById('15_1'), null, null, null, null, 2)){
				return false;			
			}
			
			if(!isNumJbCheck(document.getElementById('16_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('17_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('19_1'), null, null, null, null, 2)){
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
		      <%/*判断第9行数据*/%>
		      var obj_4_5_6_7_8_jisuanjieguo = (value_5+value_6-value_7-value_8-value_9).toFixed(2);
		      if((1*obj_4_5_6_7_8_jisuanjieguo>0)&&(value_10>obj_4_5_6_7_8_jisuanjieguo)){
		          alert("弥补以前年度亏损（9行）应小于等于第4+5-6-7-8,且应大于等于0！");
		          document.getElementById('10_1').select();
		          document.getElementById('10_1').focus();
		          return false;
		      }
		      if((1*obj_4_5_6_7_8_jisuanjieguo>0)&&(value_10<0)){
		          alert("弥补以前年度亏损（9行）应大于等于0!");
		          document.getElementById('10_1').select();
		          document.getElementById('10_1').focus();
		          return false;
		      }  
		      if((1*obj_4_5_6_7_8_jisuanjieguo<=0)&&(value_10!=0)){
		  	      alert("第4+5-6-7-8行小于等于0，弥补以前年度亏损（9行）应等于0!");
		  	      document.getElementById('10_1').select();
	              document.getElementById('10_1').focus();
				  return false;
		      }      
		      <%/*判断第12行数据*/%>
		      if(value_14>value_13){
		          alert("减：减免所得税额（13行）应小于等于应纳所得税额（12行）,且应大于等于0！");
		          document.getElementById('14_1').select();
		          document.getElementById('14_1').focus();
				  return false;
			  }
			  if(value_14<0){
			      alert("减：减免所得税额（13行）应大于等于0!");
			      document.getElementById('14_1').select();
		          document.getElementById('14_1').focus();
				  return false;
			  }
			  
			  //校验第14行数据
			  if(!checkedXwqysdse()){
			  	  document.getElementById('15_1').select();
		          document.getElementById('15_1').focus();
				  return false;
			  }
			  
			  if(value_15>value_14){
			  	  alert("其中：符合条件的小型微利企业减免所得税额（14行）应小于等于减：减免所得税额（13行），请核对！");
		          document.getElementById('14_1').select();
		          document.getElementById('14_1').focus();
		          return false;
			  }
			  <%/*判断第18行数据*/%>
		      if(value_19>value_18){
		          alert("减：以前年度多缴在本期抵缴所得税额（18行）应小于等于应补（退）所得税额（17行），请核对！");
		          document.getElementById('19_1').select();
		          document.getElementById('19_1').focus();
		        return false;
		      }
		      if(value_19<0){
		          alert("减：以前年度多缴在本期抵缴所得税额（18行）应大于等于0!");
		          document.getElementById('19_1').select();
		          document.getElementById('19_1').focus();
		        return false;
		      }
		      
		      

		//当表头纳税方法选项定义为汇总纳税-总机构时开放本表第2-17、32-35行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			
			var value_3=(document.getElementById('3_1').value)*1;
			var value_4=(document.getElementById('4_1').value)*1;
			var value_5=(document.getElementById('5_1').value)*1;
			var value_6=(document.getElementById('6_1').value)*1;
			var value_7=(document.getElementById('7_1').value)*1;
			var value_8=(document.getElementById('8_1').value)*1;
			var value_9=(document.getElementById('9_1').value)*1;
			var value_10=(document.getElementById('10_1').value)*1;
			var value_11=(document.getElementById('11_1').value)*1;
			var value_12=((document.getElementById('12_1').value)*1)/100;
			var value_13=(document.getElementById('13_1').value)*1;
			var value_14=(document.getElementById('14_1').value)*1;
			var value_15=(document.getElementById('15_1').value)*1;
			var value_16=(document.getElementById('16_1').value)*1;
			var value_17=(document.getElementById('17_1').value)*1;
			var value_18=(document.getElementById('18_1').value)*1;
			var value_19=(document.getElementById('19_1').value)*1;
			var value_20=(document.getElementById('20_1').value)*1;
			var value_30=(document.getElementById('30_1').value)*1;
			var value_31=(document.getElementById('31_1').value)*1;
			var value_32=(document.getElementById('32_1').value)*1;
			var value_33=(document.getElementById('33_1').value)*1;
//			var value_28=(document.getElementById('28_1').value)*1;
			
			if(!isNumJbCheck(document.getElementById('3_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('4_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('5_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('6_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('7_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('8_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('10_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('13_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('14_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('16_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('17_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('19_1'), null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('33_1'), null, null, null, null, 2)){
				return false;			
			}
//			if(!isNumJbCheck(document.getElementById('28_1'), null, null, null, null, 2)){
//				return false;			
//			}
			
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
		      <%/*判断第9行数据*/%>
		      var obj_4_5_6_7_8_jisuanjieguo = (value_5+value_6-value_7-value_8-value_9).toFixed(2);
		      if((1*obj_4_5_6_7_8_jisuanjieguo>0)&&(value_10>obj_4_5_6_7_8_jisuanjieguo)){
		          alert("弥补以前年度亏损（9行）应小于等于第4+5-6-7-8,且应大于等于0！");
		          document.getElementById('10_1').select();
		          document.getElementById('10_1').focus();
		          return false;
		      }
		      if((1*obj_4_5_6_7_8_jisuanjieguo>0)&&(value_10<0)){
		          alert("弥补以前年度亏损（9行）应大于等于0!");
		          document.getElementById('10_1').select();
		          document.getElementById('10_1').focus();
		          return false;
		      }  
		      if((1*obj_4_5_6_7_8_jisuanjieguo<=0)&&(value_10!=0)){
		  	      alert("第4+5-6-7-8行小于等于0，弥补以前年度亏损（9行）应等于0!");
		  	      document.getElementById('10_1').select();
	              document.getElementById('10_1').focus();
				  return false;
		      }    
		      <%/*判断第13行数据*/%>
		      if(value_14>value_13){
		          alert("减：减免所得税额（13行）应小于等于应纳所得税额（12行）,且应大于等于0！");
		          document.getElementById('14_1').select();
		          document.getElementById('14_1').focus();
				  return false;
			  }
			  if(value_14<0){
			      alert("减：减免所得税额（13行）应大于等于0!");
			      document.getElementById('14_1').select();
		          document.getElementById('14_1').focus();
				  return false;
			  }
			  
			  //校验第14行数据
			  if(!checkedXwqysdse()){
			  	  document.getElementById('15_1').select();
		          document.getElementById('15_1').focus();
				  return false;
			  }
			  if(value_15>value_14){
			  	  alert("其中：符合条件的小型微利企业减免所得税额（14行）应小于等于减：减免所得税额（13行），请核对！");
		          document.getElementById('14_1').select();
		          document.getElementById('14_1').focus();
		          return false;
			  }
			  <%/*判断第18行数据*/%>
		      if(value_19>value_18){
		          alert("减：以前年度多缴在本期抵缴所得税额（18行）应小于等于应补（退）所得税额（17行），请核对！");
		          document.getElementById('19_1').select();
		          document.getElementById('19_1').focus();
		        return false;
		      }
		      if(value_19<0){
		          alert("减：以前年度多缴在本期抵缴所得税额（18行）应大于等于0!");
		          document.getElementById('19_1').select();
		          document.getElementById('19_1').focus();
		        return false;
		      }
		      <%/*判断第33行数据*/%>
		      if(value_33<0){
		          alert("总机构独立生产经营部门应分摊所得税额（33行）应大于等于0!");
		          document.getElementById('33_1').select();
		          document.getElementById('33_1').focus();
		          return false;
		        }
//		      <%/*判3530行数据*/%>
//		      if(value_28<0){
//		          alert("总机构已撤销分支机构应分摊所得税额（30行）应大于等于0!");
//		          document.getElementById('28_1').select();
//		          document.getElementById('28_1').focus();
//		          return false;
//		        }
		      <%/*判断第33是否大于第32行数据*/%>
		      var obj_33_jsjg = (value_33).toFixed(2); 
		      if(1*obj_33_jsjg>value_32){
		          alert("总机构独立生产经营部门应分摊所得税额（33行）与应小于等于分支机构应分摊所得税额（32行）!");
		          return false;
		        }
			
		//当表头纳税方法选项定义为汇总纳税-分支机构时开放本表第28、34、35行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){			
			var value_32=(document.getElementById('32_1').value)*1;
			var value_34=(document.getElementById('34_1').value)*1;
			var value_35=(document.getElementById('35_1').value)*1;
			
			if(!isNumJbCheck(document.getElementById('32_1') , null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('34_1') , null, null, null, null, 2)){
				return false;			
			}
			if(!isNumJbCheck(document.getElementById('35_1') , null, null, null, null, 2)){
				return false;			
			}
//			<%/*判断第32行数据*/%>
//	        if(value_32<0){
//	          alert("分支机构应分摊所得税额（32行）应大于等于0!");
//	          document.getElementById('32_1').select();
//	          document.getElementById('32_1').focus();
//	          return false;
//	        }
	        <%/*判断第34行数据*/%>
	        if((value_34<0)||(value_34>100)){
			  alert("分配比例（34行）应填入0-100的数字，请核对！");
			  document.getElementById('34_1').select();
	          document.getElementById('34_1').focus();
	          return false;
		    }
			
		}else{		
			
			//alert("else_end");
		}
		return true;
	}
	//modified by huohb,2014-07-31
	//对公用jsisNumJbCheck进行包装，因2014年季报需要对输入的数字进行校验，如只输入“-”，下面的自动计算会出现NaN,所以需要先对传输进来的值进行校验，然后转换才能进行计算
	function isNumJbCheck(obj,minValue,maxValue,empty,totalLength, decimalLength){
		formate(obj);
		return isNum(obj,minValue,maxValue,empty,totalLength, decimalLength);
		
	}
	function checkedXwqysdse(){
		//计算小微企业所得税额
		compute_xwqysdse();
		var value_15=document.getElementById('15_1').value;
//		if(value_15*1==0){
//			if(xwblts=="15"){
//				//alert("本期申报您企业可以享受小型微利企业减半征收企业所得税优惠政策，所得减按50％计入应纳税所得额，按20%的税率缴纳企业所得税。");
//				if(confirm("本期申报该企业可以享受小型微利企业减半征收企业所得税优惠政策，所得减按50％计入应纳税所得额，按20%的税率缴纳企业所得税。是否不享受此优惠政策，继续保存申报数据？")){
//					return true;
//				}else{
//					return false;
//				}
//			}else if(xwblts=="5"){
//				//alert("本期申报您企业可以享受小型微利企业所得税优惠政策，按20%的税率缴纳企业所得税。");
//				if(confirm("本期申报该企业可以享受小型微利企业所得税优惠政策，按20%的税率缴纳企业所得税。是否不享受此优惠政策，继续保存申报数据？")){
//					return true;
//				}else{
//					return false;
//				}
//			}
//		}
		if(inputValueTemp!=value_15){
			if(inputValueTemp=="0.00"){
				alert("该企业不符合小型微利企业条件，第14行“其中：符合条件的小型微利企业减免所得税额”应填写0！");
				return false;
			}else //if(value_15!=="0.00")
			{
				alert("该企业符合小型微利企业条件，第14行“其中：符合条件的小型微利企业减免所得税额”应按照第10行“实际利润额”的"+xwblts+"%("+inputValueTemp+")进行填写");
				return false;
			}
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
//		  document.getElementById('30_1').value=((1*document.getElementById('18_1').value/100)*25).toFixed(2);
//		  document.getElementById('31_1').value=((1*document.getElementById('18_1').value/100)*25).toFixed(2);
//		  document.getElementById('32_1').value=((1*document.getElementById('18_1').value/100)*50).toFixed(2);
		  
		  
		  document.getElementById('30_1').value=((1*document.getElementById('20_1').value/100)*25).toFixed(2);
		  document.getElementById('31_1').value=((1*document.getElementById('20_1').value/100)*25).toFixed(2);
		  document.getElementById('32_1').value=((1*document.getElementById('20_1').value/100)*50).toFixed(2);
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
			document.getElementById('32_1').value="0.00";
	    }
	    if(document.forms[0].lje_nsfs[1].checked==true){
	    	document.getElementById('32_1').value="";
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
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('5_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//compute_Row_10();
		compute_Row_11();
	}
	<%/*计算第5行数据*/%>
	function compute_Row_6(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('6_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
		//compute_Row_10();
		compute_Row_11();
	}
	<%/*计算第6行数据*/%>
	function compute_Row_7(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('7_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
		//compute_Row_10();
		compute_Row_11();
	}
	<%/*计算第7行数据*/%>
	function compute_Row_8(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('8_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
		//compute_Row_10();
		compute_Row_11();
	}
	
		<%/*计算第8行数据*/%>
	function compute_Row_9(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('9_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
        	
        	alert("error");
			return false;                         
		}
		var obj_input=document.getElementById('9_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
			alert("减征、免征应纳税所得额（8行）应大于等于0!");
			document.getElementById('9_1').select();
			document.getElementById('9_1').focus();
		}
		//compute_Row_10();
		compute_Row_11();
	}
	<%/*计算第9行数据*/%>
	function compute_Row_10(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('10_1').value="0.00";
		}   
        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('10_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}

		var obj_4_5_6_7_8_jisuanjieguo = (1*document.getElementById('5_1').value+1*document.getElementById('6_1').value-1*document.getElementById('7_1').value-1*document.getElementById('8_1').value-1*document.getElementById('9_1').value).toFixed(2);
		//alert(obj_4_5_6_7_jisuanjieguo);
		if((1*obj_4_5_6_7_8_jisuanjieguo>0)&&((1*obj_input.value)>(1*obj_4_5_6_7_8_jisuanjieguo))){
			alert("弥补以前年度亏损（9行）应小于等于第4+5-6-7-8,且应大于等于0！");
			document.getElementById('10_1').select();
			document.getElementById('10_1').focus();
			//return false;
		}
		if((1*obj_4_5_6_7_8_jisuanjieguo>0)&&(1*obj_input.value<0)){
			alert("弥补以前年度亏损（9行）应大于等于0!");
			document.getElementById('10_1').select();
			document.getElementById('10_1').focus();
			//return false;
		}
		if((1*obj_4_5_6_7_8_jisuanjieguo<=0)&&(1*obj_input.value!=0)){
	  	    alert("第4+5-6-7-8行小于等于0，弥补以前年度亏损（9行）应等于0!");
	  	    //obj_input.value="0.00";
	  	    document.getElementById('10_1').select();
			document.getElementById('10_1').focus();
	        //return false;
	    }
		compute_Row_11();
	}
	<%/*计算第10行数据*/%>
	function compute_Row_11(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('11_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('11_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}

		document.getElementById('11_1').value=(1*document.getElementById('5_1').value+1*document.getElementById('6_1').value-1*document.getElementById('7_1').value-1*document.getElementById('8_1').value-1*document.getElementById('9_1').value-1*document.getElementById('10_1').value).toFixed(2);
		if((1*document.getElementById('11_1').value)==""){
			document.getElementById('11_1').value="0.00";
		}
		compute_Row_13();
		//compute_Row_15();
	}
	
	
	<%/*计算第11行数据*/%>
	function compute_Row_12(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('12_1').value="25.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('12_1');
		compute_Row_13();
	}
	<%/*计算第12行数据*/%>
	function compute_Row_13(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('13_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('13_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		
		document.getElementById('13_1').value=((1*document.getElementById('11_1').value/100)*(1*document.getElementById('12_1').value)).toFixed(2);
		
		if(((1*document.getElementById('11_1').value/100)*(1*document.getElementById('12_1').value).toFixed(2))<0){
			obj_input.value="0.00";
		}
		
		if(1*obj_input.value<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		
		compute_Row_18();
	}
	<%/*计算第13行数据*/%>
	function compute_Row_14(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('14_1').value="0.00";
		}   
        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('14_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if((1*obj_input.value)>(1*document.getElementById('13_1').value)){
			alert("减：减免所得税额（13行）应小于等于应纳所得税额（12行）,且应大于等于0！");
			document.getElementById('14_1').select();
			document.getElementById('14_1').focus();
			//return false;
		}
		if(1*obj_input.value<0){
			alert("减：减免所得税额（13行）应大于等于0!");
			document.getElementById('14_1').select();
			document.getElementById('14_1').focus();
			//return false;
		}
		compute_Row_18();
	}
	<%/*计算第14行数据*/%>
	
	var inputValueTemp="0.00";
	var xwblts="0";
	function compute_Row_15(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('15_1').value="0.00";
		}   

		var obj_input=document.getElementById('15_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		var sfxkh=document.forms[0].sfxkh.value;
		var syndZsfsdm=document.forms[0].syndZsfsdm.value;
		var syndFb5jyjg=document.forms[0].syndFb5jyjg.value;
		//10行录入数据
		var sjlreStr=document.getElementById('11_1').value;
		var syndZbh6Str=document.forms[0].syndZbh6.value;
		var syndZbh25Str=document.forms[0].syndZbh25.value;
		var sjlre=0.00;
		var syndZbh6=0.00;
		var syndZbh25=0.00;
		if(sjlreStr!=null && sjlreStr!=""){
			sjlre=1*sjlreStr;
		}
		if(syndZbh6Str!=null && syndZbh6Str!=""){
			syndZbh6=1*syndZbh6Str;
		}
		if(syndZbh25Str!=null && syndZbh25Str!=""){
			syndZbh25=1*syndZbh25Str;
		}
		
		//alert("sfxkh: "+sfxkh);
		//alert("syndZsfsdm: "+syndZsfsdm);
		//alert("syndFb5jyjg: "+syndFb5jyjg);
		//alert("sjlre: "+sjlre);
		//alert("syndZbh6: "+syndZbh6);
		//alert("syndZbh25: "+syndZbh25);
		xwblts="0";
		if(sjlre>30*10000 || sjlre<=0){
			//obj_input.value="0.00";
			inputValueTemp="0.00";
		}else{
			if(sfxkh=="Y"){
				if(sjlre<=10*10000){
					//obj_input.value=(sjlre*0.15).toFixed(2);
					inputValueTemp=(sjlre*0.15).toFixed(2);
//					gyts("15");
					xwblts="15";
				}
				
				if(10*10000<sjlre && sjlre<=30*10000){
					//obj_input.value=(sjlre*0.05).toFixed(2);
					inputValueTemp=(sjlre*0.05).toFixed(2);
//					gyts("5");
					xwblts="5";
				}
				
				if(sjlre>30*10000){
					//obj_input.value="0.00";
					inputValueTemp="0.00";
				}
			}else{
				 if(syndZsfsdm==<%=CodeConstant.ZSFSDM_CZZS%>){//查账征收
					if(1*syndZbh25>30*10000){
						//obj_input.value="0.00";
						inputValueTemp="0.00";
					}else{
						if(syndFb5jyjg=="Y"){
							if(sjlre<=10*10000 && syndZbh25<=10*10000){
								//obj_input.value=(sjlre*0.15).toFixed(2);
								inputValueTemp=(sjlre*0.15).toFixed(2);
								//gyts("15");
								xwblts="15";
							}else{
								//obj_input.value=(sjlre*0.05).toFixed(2);
								inputValueTemp=(sjlre*0.05).toFixed(2);
								//gyts("5");
								xwblts="5";
							}
						}else{
							//obj_input.value="0.00";
							inputValueTemp="0.00";
						}
					}
				}else{
					if(1*syndZbh6>30*10000){
						//obj_input.value="0.00";
						inputValueTemp="0.00";
					}else{
						if(sjlre<=10*10000 && syndZbh6<=10*10000){
							//obj_input.value=(sjlre*0.15).toFixed(2);
							inputValueTemp=(sjlre*0.15).toFixed(2);
							//gyts("15");
							xwblts="15";
						}else{
							//obj_input.value=(sjlre*0.05).toFixed(2);
							inputValueTemp=(sjlre*0.05).toFixed(2);
							//gyts("5");
							xwblts="5";
						}
					}
				}
			}
		}
	}
	
	//计算小微企业所得税税额
	function compute_xwqysdse(){
		var sfxkh=document.forms[0].sfxkh.value;
		var syndZsfsdm=document.forms[0].syndZsfsdm.value;
		var syndFb5jyjg=document.forms[0].syndFb5jyjg.value;
		//10行录入数据
		var sjlreStr=document.getElementById('11_1').value;
		var syndZbh6Str=document.forms[0].syndZbh6.value;
		var syndZbh25Str=document.forms[0].syndZbh25.value;
		var sjlre=0.00;
		var syndZbh6=0.00;
		var syndZbh25=0.00;
		if(sjlreStr!=null && sjlreStr!=""){
			sjlre=1*sjlreStr;
		}
		if(syndZbh6Str!=null && syndZbh6Str!=""){
			syndZbh6=1*syndZbh6Str;
		}
		if(syndZbh25Str!=null && syndZbh25Str!=""){
			syndZbh25=1*syndZbh25Str;
		}
		xwblts="0";
		if(sjlre>30*10000 || sjlre<=0){
			//obj_input.value="0.00";
			inputValueTemp="0.00";
		}else{
			if(sfxkh=="Y"){
				if(sjlre<=10*10000){
					//obj_input.value=(sjlre*0.15).toFixed(2);
					inputValueTemp=(sjlre*0.15).toFixed(2);
					gyts("15");
					xwblts="15";
				}
				
				if(10*10000<sjlre && sjlre<=30*10000){
					//obj_input.value=(sjlre*0.05).toFixed(2);
					inputValueTemp=(sjlre*0.05).toFixed(2);
					gyts("5");
					xwblts="5";
				}
				
				if(sjlre>30*10000){
					//obj_input.value="0.00";
					inputValueTemp="0.00";
				}
			}else{
				 if(syndZsfsdm==<%=CodeConstant.ZSFSDM_CZZS%>){//查账征收
					if(1*syndZbh25>30*10000){
						//obj_input.value="0.00";
						inputValueTemp="0.00";
					}else{
						if(syndFb5jyjg=="Y"){
							if(sjlre<=10*10000 && syndZbh25<=10*10000){
								//obj_input.value=(sjlre*0.15).toFixed(2);
								inputValueTemp=(sjlre*0.15).toFixed(2);
								gyts("15");
								xwblts="15";
							}else{
								//obj_input.value=(sjlre*0.05).toFixed(2);
								inputValueTemp=(sjlre*0.05).toFixed(2);
								gyts("5");
								xwblts="5";
							}
						}else{
							//obj_input.value="0.00";
							inputValueTemp="0.00";
						}
					}
				}else{
					if(1*syndZbh6>30*10000){
						//obj_input.value="0.00";
						inputValueTemp="0.00";
					}else{
						if(sjlre<=10*10000 && syndZbh6<=10*10000){
							//obj_input.value=(sjlre*0.15).toFixed(2);
							inputValueTemp=(sjlre*0.15).toFixed(2);
							gyts("15");
							xwblts="15";
						}else{
							//obj_input.value=(sjlre*0.05).toFixed(2);
							inputValueTemp=(sjlre*0.05).toFixed(2);
							gyts("5");
							xwblts="5";
						}
					}
				}
			}
		}
	}
	
	 function gyts(strBs){
		if(strBs=="15"){
			//alert("本期申报该企业可以享受小型微利企业减半征收企业所得税优惠政策，所得减按50％计入应纳税所得额，按20%的税率缴纳企业所得税。");
			xwblts="15";
		}
		
		if(strBs=="5"){
			//alert("本期申报该企业可以享受小型微利企业所得税优惠政策，按20%的税率缴纳企业所得税。");
			xwblts="5";
		}
	}
	<%/*计算第15行数据*/%>
	function compute_Row_16(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('16_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('16_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
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
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('17_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		compute_Row_18();
	}
	<%/*计算第17行数据*/%>
	function compute_Row_18(){
		//alert("compute_Row_16");
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('18_1').value="0.00";
		}		   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('18_1');
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//计算行次11的域值
		document.getElementById('18_1').value=(1*document.getElementById('13_1').value-1*document.getElementById('14_1').value-1*document.getElementById('16_1').value-1*document.getElementById('17_1').value).toFixed(2);
		if((1*document.getElementById('18_1').value)<=0){
		//alert("第17行小于等于0");
			document.getElementById('18_1').value="0.00";
		}
//		if((document.forms[0].lje_nsfs[0].checked==1)&&(document.forms[0].lje_zfjg[0].checked==1)){
//			compute_Row_30();
//			compute_Row_31();
//			compute_Row_32();
//		}
		compute_Row_20();
	}
	<%/*计算第18行数据*/%>
	function compute_Row_19(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('19_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('19_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value>1*document.getElementById('18_1').value){
			alert("减：以前年度多缴在本期抵缴所得税额（18行）应小于等于应补（退）所得税额（17行），请核对！");
			document.getElementById('19_1').select();
			document.getElementById('19_1').focus();
		}
		if(1*obj_input.value<0){
	        alert("减：以前年度多缴在本期抵缴所得税额（18行）应大于等于0!");
	        document.getElementById('19_1').select();
			document.getElementById('19_1').focus();
	        //return false;
	    }
		compute_Row_20();
	}
	<%/*计算第19行数据*/%>
	function compute_Row_20(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('20_1').value="0.00";
		}		   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('20_1');
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//计算行次19的域值
		document.getElementById('20_1').value=(1*document.getElementById('18_1').value-1*document.getElementById('19_1').value).toFixed(2);
		if((1*document.getElementById('20_1').value)<=0){
		//alert("第19行小于等于0");
			document.getElementById('20_1').value="0.00";
		}
		
		if((document.forms[0].lje_nsfs[0].checked==1)&&(document.forms[0].lje_zfjg[0].checked==1)){
			compute_Row_30();
			compute_Row_31();
			compute_Row_32();
		}
	}
	<%/*计算第26行数据*/%>
	function compute_Row_30(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('30_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('30_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
//		obj_input.value=((1*document.getElementById('18_1').value/100)*25).toFixed(2);
		obj_input.value=((1*document.getElementById('20_1').value/100)*25).toFixed(2);
	}
	<%/*计算第31行数据*/%>
	function compute_Row_31(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('31_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('31_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
//		obj_input.value=((1*document.getElementById('18_1').value/100)*25).toFixed(2);
		obj_input.value=((1*document.getElementById('20_1').value/100)*25).toFixed(2);
	}
	<%/*计算第28行数据*/%>
	function compute_Row_32(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('32_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('32_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
//			obj_input.value=((1*document.getElementById('18_1').value/100)*50).toFixed(2);
			obj_input.value=((1*document.getElementById('20_1').value/100)*50).toFixed(2);
		}
//		if(1*obj_input.value<0){
//	        alert("分支机构应分摊所得税额（32行）应大于等于0!");
//	        document.getElementById('32_1').select();
//			document.getElementById('32_1').focus();
//	        //return false;
//	    }
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
			compute_Row_35();
		}
		//compute_Row_35();
	}
	<%/*计算第33行数据*/%>
	function compute_Row_33(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('33_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('33_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
	        alert("总机构独立生产经营部门应分摊所得税额（33行）应大于等于0!");
	        document.getElementById('33_1').select();
			document.getElementById('33_1').focus();
	      //return false;
	    }
		var obj_33_jsjg = (1*document.getElementById('33_1').value).toFixed(2); 

		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(1*obj_33_jsjg>(1*document.getElementById('32_1').value)){
		        alert("总机构独立生产经营部门应分摊所得税额（33行）应小于等于分支机构应分摊所得税额（32行）!");
		        document.getElementById('33_1').select();
		        document.getElementById('33_1').focus();
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
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
		var obj_33_30_jsjg = (1*document.getElementById('33_1').value+1*document.getElementById('28_1').value).toFixed(2); 

		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(1*obj_33_30_jsjg>(1*document.getElementById('32_1').value)){
		        alert("总机构独立生产经营部门应分摊所得税额（33行）与总机构已撤销分支机构应分摊所得税额（30行）之和应小于等于分支机构应分摊所得税额（28行）!");
		        document.getElementById('28_1').select();
		        document.getElementById('28_1').focus();
		        //return false;
		    }
		}
	}
	<%/*计算第34行数据*/%>
	function compute_Row_34(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('34_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('34_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if((1*obj_input.value<0)||(1*obj_input.value>100)){
			alert("分配比例（34行）应填入0-100的数字，请核对！");
			document.getElementById('34_1').select();
	        document.getElementById('34_1').focus();
		}
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
			compute_Row_35();
		}
	}
	<%/*计算第35行数据*/%>
	function compute_Row_35(){
		if(document.forms[0].jsjdm.value==""){
			alert("请输入计算机代码!");
			document.forms[0].jsjdm.focus();
			document.getElementById('35_1').value="0.00";
		}   

        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('35_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		
		document.getElementById('35_1').value=((1*document.getElementById('32_1').value/100)*(1*document.getElementById('34_1').value)).toFixed(2);
//		if(1*obj_input.value<0){
//			obj_input.value="0.00";
//		}
		//if(obj_input.value==""){
		//	obj_input.value="0.00";
		//}
	}
	
function checkNumInput(obj)
{
		//判断输入的数据是否符合要求
		if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
	//if(!isNumJbCheckber(obj.value)){
	//	alert("请使用数字格式输入！");
	//	obj.focus();
	//	return false;
	//}
	if(obj.value==""||obj.value==null){
		return false;	
	}else{
		//if(obj.value=="-"){
		//	alert("请输入正确的数字!");
		//	return false;
		//}else{
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
			if(temp=="-.00"){
				temp="-0.00";
			}
			obj.value=temp;	
			formateNum(obj);
		//}
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
		document.forms[0].isQuery.value="0";
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
		if(parseInt(document.forms[0].skssjsrq.value)<=20140331){
			alert("您正在使用的企业所得税申报表版本与当前的税款所属期间不符，请选择正确的企业所得税申报表版本！");
			return false;	
		}
		return true;
	}
	<%/*保存*/%>
	function doSave()
	{
		if(document.forms[0].nsrmc.value==""){
			alert("基本信息不正确,保存失败,请重新输入!");
			document.forms[0].jsjdm.focus();
			return false;
		}
		if(document.forms[0].isQuery.value!="1"){
			alert("您修改了计算机代码,请重新执行查询动作!");
			document.forms[0].jsjdm.focus();
			return false;
		}
		if(!checkBb()){
			return false;
		}	
		if(!saveCheckFilter())
		{
			//alert("数据不正确，无法保存！");
			return false;
		}
		//从表保存校验
		saveCbCheck();
		
		doSubmitForm('Save');	
		
	}
	//从表保存校验
	function saveCbCheck(){
		//重命名从表中的id和value
		renameId("Mssrxm");
		renameId("Jzmzxm");
		renameId("Jmxm");
		cbsjHj("Mssrxm");
		cbsjHj("Jzmzxm");
		cbsjHj("Jmxm");
	}
	//function checkSybs(){
	//	if(document.forms[0].sybs.value!=""){
	//		if(document.forms[0].sybs.value==<%=CodeConstant.CODE_QYSDS_SYBS_OTHER%>){
	//			alert("该企业的税源标识不为总、地、分，不需要添写企业所得税月(季)度预缴纳税申报表(A类)表!");
	//			return false;
	//		}
	//	}else{
	//		alert("没有获取到该企业的税源标识，请重新执行查询动作！");
		//	return false;
	//	}
	//	return true;
	//}
	<%/*查看分配表*/%>
	function doView()
	{
		if(document.forms[0].nsrmc.value==""){
			alert("基本信息不正确,保存失败,请重新输入!");
			document.forms[0].jsjdm.focus();
			return false;
		}
		if(!checkBb()){
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
		if(document.getElementById('isXky').value=="Y"){
	      alert("新开户无需查看分配表！");
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
					for(var i=3;i<21;i++){
		   				obj = document.getElementById(i+"_1");
						if(obj!=null){
							obj.value = "0.00";
						}
		   			}
				}
				if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
					for(var i=3;i<34;i++){
		   				obj = document.getElementById(i+"_1");
						if(obj!=null){
							obj.value = "0.00";
						}
		   			}
				}
				if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
					for(var i=32;i<36;i++){
		   				obj = document.getElementById(i+"_1");

						if(obj!=null){
							obj.value = "0.00";
						}
		   			}
					document.getElementById("33_1").value="";
//					document.getElementById("28_1").value="";
				}
	   			
			document.getElementById("12_1").value="25.00";
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
	
	
		<%/*增加行*/%>
	function doJmxmAddRow(cbJmxmYzhc,cbJmxmYz)
	{
		//获取已有分支机构明细信息数量
		var mxnum = document.forms[0].cbJmxmYz.length;
		//alert("mxnum = " + mxnum);

		//获取需要添加行的表格
		var table = document.getElementById("jmxmAutoTable");

		//然后创建行(TR对象)
		var NewTr = document.createElement("tr");
		var NewTd1 = document.createElement("<td width=\"52\" nowrap class=\"2-td2-left\">");
			NewTd1.innerHTML="<div align=\"center\">&nbsp;&nbsp;<input type=\"hidden\" name=\"cbJmxmYzhc\" value=\""+cbJmxmYzhc+"\" id=\"\"></div>";
		var NewTd2 = document.createElement("<td colspan=\"2\" nowrap class=\"2-td2-left\">");
			NewTd2.innerHTML="<div align=\"left\">减：减免所得税额</div>";
		var NewTd3 = document.createElement("<td width=\"180\" nowrap class=\"2-td2-left\">");
			//NewTd3.innerHTML='<div align=\"center\"></div>';			
			NewTd3.innerHTML=document.getElementById("jmxm_div").innerHTML;
		var NewTd4 = document.createElement("<td width=\"180\" nowrap class=\"2-td2-center\">");
			NewTd4.innerHTML="<input type='text' name='cbJmxmYz' id='' value='"+cbJmxmYz+"' size='13' maxlength='13' onblur='' onchange=\"cbsjHj('Jmxm')\"><img onclick=\"doDeleteCbRow(this,'Jmxm')\" src=\"<%=static_contextpath%>images/zbotton-jian2.gif\" alt=\"移除\" name=\"remove\" id=\"remove\"  style=\"cursor:hand\">";
			//往新行里面填充单元格
			NewTr.appendChild(NewTd1);
			NewTr.appendChild(NewTd2);
			NewTr.appendChild(NewTd3);
			NewTr.appendChild(NewTd4);

		//添加行
		var LastTr = table.rows[table.rows.length - 1];

		LastTr.parentNode.appendChild(NewTr);
		
		
		renameId("Jmxm");
	}
				<%/*增加行*/%>
	function doMssrxmAddRow(cbMssrxmYzhc,cbMssrxmYz)
	{
		//获取已有分支机构明细信息数量
		var mxnum = document.forms[0].cbMssrxmYz.length;
		//alert("mxnum = " + mxnum);

		//获取需要添加行的表格
		var table = document.getElementById("mssrxmAutoTable");

		//然后创建行(TR对象)
		var NewTr = document.createElement("tr");
		var NewTd1 = document.createElement("<td width=\"52\" nowrap class=\"2-td2-left\">");
			NewTd1.innerHTML="<div align=\"center\">&nbsp;&nbsp;<input type=\"hidden\" name=\"cbMssrxmYzhc\" value=\""+cbMssrxmYzhc+"\" id=\"\"></div>";
		var NewTd2 = document.createElement("<td colspan=\"2\" nowrap class=\"2-td2-left\">");
			NewTd2.innerHTML="<div align=\"left\">&nbsp;&nbsp;&nbsp;&nbsp;</div>";
		var NewTd3 = document.createElement("<td width=\"180\" nowrap class=\"2-td2-left\">");
			//NewTd3.innerHTML='<div align=\"center\"></div>';			
			NewTd3.innerHTML=document.getElementById("mssrxm_div").innerHTML;
		var NewTd4 = document.createElement("<td width=\"180\" nowrap class=\"2-td2-center\">");
			NewTd4.innerHTML="<input type='text' name='cbMssrxmYz' id='' value='"+cbMssrxmYz+"' size='13' maxlength='13' onblur='' onchange=\"cbsjHj('Mssrxm')\"><img onclick=\"doDeleteCbRow(this,'Mssrxm')\" src=\"<%=static_contextpath%>images/zbotton-jian2.gif\" alt=\"移除\" name=\"remove\" id=\"remove\"  style=\"cursor:hand\">";
			//往新行里面填充单元格
			NewTr.appendChild(NewTd1);
			NewTr.appendChild(NewTd2);
			NewTr.appendChild(NewTd3);
			NewTr.appendChild(NewTd4);
			//alert(NewTr.innerHTML);

		//添加行
		var LastTr = table.rows[table.rows.length - 1];

		LastTr.parentNode.appendChild(NewTr);
		
		
		renameId("Mssrxm");
	}
	
					<%/*增加行*/%>
	function doJzmzxmAddRow(cbJzmzxmYzhc,cbJzmzxmYz)
	{
		//获取已有分支机构明细信息数量
		var mxnum = document.forms[0].cbJzmzxmYz.length;
		//alert("mxnum = " + mxnum);

		//获取需要添加行的表格
		var table = document.getElementById("jzmzxmAutoTable");

		//然后创建行(TR对象)
		var NewTr = document.createElement("tr");
		var NewTd1 = document.createElement("<td width=\"52\" nowrap class=\"2-td2-left\">");
			NewTd1.innerHTML="<div align=\"center\">&nbsp;&nbsp;<input type=\"hidden\" name=\"cbJzmzxmYzhc\" value=\""+cbJzmzxmYzhc+"\" id=\"\"></div>";
		var NewTd2 = document.createElement("<td colspan=\"2\" nowrap class=\"2-td2-left\">");
			NewTd2.innerHTML="<div align=\"left\">&nbsp;&nbsp;&nbsp;&nbsp;减征、免征应纳税所得额</div>";
		var NewTd3 = document.createElement("<td width=\"180\" nowrap class=\"2-td2-left\">");
			//NewTd3.innerHTML='<div align=\"center\"></div>';			
			NewTd3.innerHTML=document.getElementById("jzmzxm_div").innerHTML;
		var NewTd4 = document.createElement("<td width=\"180\" nowrap class=\"2-td2-center\">");
			NewTd4.innerHTML="<input type='text' name='cbJzmzxmYz' id='' value='"+cbJzmzxmYz+"' size='13' maxlength='13' onblur='' onchange=\"cbsjHj('Jzmzxm')\"><img onclick=\"doDeleteCbRow(this,'Jzmzxm')\" src=\"<%=static_contextpath%>images/zbotton-jian2.gif\" alt=\"移除\" name=\"remove\" id=\"remove\"  style=\"cursor:hand\">";
			//往新行里面填充单元格
			NewTr.appendChild(NewTd1);
			NewTr.appendChild(NewTd2);
			NewTr.appendChild(NewTd3);
			NewTr.appendChild(NewTd4);
			//alert(NewTr.innerHTML);

		//添加行
		var LastTr = table.rows[table.rows.length - 1];

		LastTr.parentNode.appendChild(NewTr);
		
		
		renameId("Jzmzxm");
	}
	//传入对象和从表的项目类型参数，删除其所在的行
	function doDeleteCbRow(obj,cbxmlx) {  
		var tableId;
		if("Mssrxm"==cbxmlx){
			tableId="mssrxmAutoTable";
		}else if("Jzmzxm"==cbxmlx){
			tableId="jzmzxmAutoTable";
		}else if("Jmxm"==cbxmlx){
			tableId="jmxmAutoTable";
		}	
		var tr = obj.parentNode.parentNode;
		//alert(tr.innerHTML);
	    var cbTable=document.getElementById(tableId);  

		cbTable.rows[0].parentNode.removeChild(tr);
		renameId(cbxmlx);
		cbsjHj(cbxmlx);
	} 
	//传入从表的项目类型参数，对相应的从表进行重命名和赋值
	function renameId(cbxmlx){
		var mxnum;var cbYz;var cbYzhc;var cbDm;var cbDmhc;var xmdm;var xmPreId;
		if("Mssrxm"==cbxmlx){
			mxnum = document.forms[0].cbMssrxmYz.length;
			cbYz=document.forms[0].cbMssrxmYz;
			cbYzhc=document.forms[0].cbMssrxmYzhc;
			cbDm=document.forms[0].cbMssrxmDm;
			cbDmhc=document.forms[0].cbMssrxmDmhc;
			xmdm=document.forms[0].mssrxmdm;
			xmPreId="777.";
		}else if("Jzmzxm"==cbxmlx){
			mxnum = document.forms[0].cbJzmzxmYz.length;
			cbYz=document.forms[0].cbJzmzxmYz;
			cbYzhc=document.forms[0].cbJzmzxmYzhc;
			cbDm=document.forms[0].cbJzmzxmDm;
			cbDmhc=document.forms[0].cbJzmzxmDmhc;
			xmdm=document.forms[0].jzmzxmdm;
			xmPreId="888.";
		}else if("Jmxm"==cbxmlx){
			mxnum = document.forms[0].cbJmxmYz.length;
			cbYz=document.forms[0].cbJmxmYz;
			cbYzhc=document.forms[0].cbJmxmYzhc;
			cbDm=document.forms[0].cbJmxmDm;
			cbDmhc=document.forms[0].cbJmxmDmhc;
			xmdm=document.forms[0].jmxmdm;
			xmPreId="999.";
		}		
		for(var i=0;i<mxnum*2;i++){
			var j=i/2;
			cbDm[j].setAttribute("id",(i+1)+"");
			cbDm[j].setAttribute("value",xmdm[j].value);
			cbDmhc[j].setAttribute("value",xmPreId+(i+1));
			cbDmhc[j].setAttribute("id","cb"+cbxmlx+"Dmhc_"+(i+1));
			cbYz[j].setAttribute("id",(i+2)+"");
			cbYzhc[j].setAttribute("value",xmPreId+(i+2));
			cbYzhc[j].setAttribute("id","cb"+cbxmlx+"Yzhc_"+(i+2));

			i++;
		}
	}
	//传入从表的项目类型参数，计算相应的合计
	function cbsjHj(cbxmlx){
		var cbYzs;var hj;
		if("Mssrxm"==cbxmlx){
			//cbYzs=document.forms[0].cbMssrxmYz;
			cbYzs=document.getElementsByName("cbMssrxmYz");
			hj=document.getElementById("8_1");
		}else if("Jzmzxm"==cbxmlx){
			cbYzs=document.getElementsByName("cbJzmzxmYz");
			hj=document.getElementById("9_1");
		}else if("Jmxm"==cbxmlx){
			cbYzs=document.getElementsByName("cbJmxmYz");
			hj=document.getElementById("14_1");
		}	
		var cbsjHj=0;
		for(var i=0;i<cbYzs.length;i++){			
			var cbYz=cbYzs[i].value;								
			if(null==cbYz||""==cbYz||"null"==cbYz){
				cbYz="0.00";
			}
			cbsjHj+=parseFloat(cbYz);
		}
		hj.value=parseFloat(cbsjHj).toFixed(2);
	}
</script>
</body>
</html:html>
