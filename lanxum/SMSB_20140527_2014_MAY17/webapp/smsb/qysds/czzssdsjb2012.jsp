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
<title>��ҵ����˰��(��)��Ԥ����˰�걨��(A��)</title>
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
				�л����񹲺͹�<br>��ҵ����˰��(��)��Ԥ����˰�걨��(A��)
			</td>
		</tr>

		<tr>
			<td class="1-td2" colspan="7"><br>
			<table cellspacing="0" class="table-99">

				<tr class="black9">
					<td align="center" nowrap>
					<div align="left">&nbsp;&nbsp;�걨���ڣ� <html:text tabindex="1" property="sbrq" size="11"
						maxlength="8" readonly='true' style='text-align:left'
						onchange="checkZQ(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,3)" /></div>
					</td>
					<td wigth="90%" align="center" nowrap>˰�������ڼ䣺 <html:text tabindex="2" property="skssksrq"
						size="15" maxlength="8"
						onchange="isDate(this,false);compareDate(this)"  style="text-align:left" /> �� <html:text tabindex="3"
						property="skssjsrq" size="15" maxlength="8"
						onchange="isDate(this,false);compareDate(this)" style="text-align:left" /></td>
					<td align="right" nowrap>
						<!-- <div align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div> -->
						<div align="right">��λ�������Ԫ�������Ƿ֣�</div>
					</td>
				</tr>
			</table>
			
			
			
			<table class="table-99" border="0" cellpadding="0" cellspacing="0" width="80%">
				<td>
					<table class="table-99" border="0" cellpadding="0" cellspacing="0" width="80%">
						<tr>
							<td width="10%" nowrap >
								<div style="font-size: 9pt; line-height: 26px; color: #3E6071; background-color: #F3F5F8; text-align: left;">&nbsp;&nbsp;��˰�˵�˰��������룺
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
								<div style="font-size: 9pt; line-height: 26px; color: #3E6071; background-color: #F3F5F8; text-align: left;">&nbsp;&nbsp;��&nbsp;˰&nbsp;��&nbsp;ʶ&nbsp;��&nbsp;��&nbsp;&nbsp;&nbsp;��
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
								<div style="font-size: 9pt; line-height: 26px; color: #3E6071; background-color: #F3F5F8; text-align: left;">&nbsp;&nbsp;��&nbsp;&nbsp;˰&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��
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
									   onclick="compute_Row_1()" onchange="compute_Row_1()" disabled="true">������˰��
							</td>
							<td width="10%" nowrap class="2-td2-left" style="border-top: 1px solid #9BB4CA;">
								<div align="left">
									<input type='radio' name='lje_zfjg' style='text-align:right' 
										   value='<%=CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_ZJG%>' size='13' 
										   onclick="compute_Row_2()" onchange="compute_Row_2()" disabled="true">�ܻ���
								</div>
							</td>
							<td width="10%" rowspan="2" height="78px" nowrap class="2-td2-left" style="border-top: 1px solid #9BB4CA; border-right: 1px solid #9BB4CA;">&nbsp;
								<input type='radio' name='lje_nsfs' style='text-align:right' 
									   value='<%=CodeConstant.HZNSFF_QYSDSJB2008_CZZSSDS_DLNS%>' size='13'
									   onclick="compute_Row_1()" onchange="compute_Row_1()" disabled="true">������˰
							</td>
						  </tr>
						  <tr> 
							<td width="10%" nowrap class="2-td2-left">
								<div align="left">
									<input type='radio' name='lje_zfjg' style='text-align:right' 
										   value='<%=CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_FZJG%>' size='13'
										   onclick="compute_Row_2()" onchange="compute_Row_2()" disabled="true">��֧����
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
						<div align="right">��λ�������Ԫ�������Ƿ֣�</div>
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
                    <td width="52" nowrap class="2-td1-left"><div align="center">�д�</div></td>
                    <td colspan="2" nowrap class="2-td1-left"><div align="center">��Ŀ</div></td>
                    <td width="180" nowrap class="2-td1-left">���ڽ��</td>
                    <td width="180" nowrap class="2-td1-center">�ۼƽ��</td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left"><div align="center">1</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>һ������ʵ�������Ԥ��</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">2
							<input type="hidden" name="hc" value="3" id="hc_3">
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">Ӫҵ����</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">Ӫҵ�ɱ�</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">�����ܶ�</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">�ӣ��ض�ҵ������Ӧ��˰���ö�</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">��������˰����</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;��˰����</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;�ֲ���ǰ��ȿ���</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">ʵ������4��+5��-6��-7��-8�У�</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">˰�ʣ�25%��</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">Ӧ������˰��</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">������������˰��</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">����ʵ����Ԥ������˰��</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">�����ض�ҵ��Ԥ�ɣ���������˰��</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">Ӧ�����ˣ�����˰�11��-12��-13��-14�У�</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">������ǰ��ȶ���ڱ��ڵֽ�����˰��</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">����ʵ��Ӧ�����ˣ�����˰��</div></td>
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
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>����������һ��˰���Ӧ��˰���ö�ƽ����Ԥ��</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">19
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">��һ��˰���Ӧ��˰���ö�</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">20
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">���£�����Ӧ��˰���ö19�С�1/4��1/12��</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">21
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">˰��(25%)</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">22
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">���£�����Ӧ������˰�20�С�21�У�</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">23
						</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>��������˰�����ȷ������������Ԥ��</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">24
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">���£�����ȷ��Ԥ�ɵ�����˰��</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">25
						</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="center"><B>�ֻܷ�����˰��</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">26
							<input type="hidden" name="hc" value="24" id="hc_24">
						</div></td>
                    <td rowspan="5" width="20%" class="2-td2-left"><div align="center">�ܻ���</div></td>
                    <td nowrap class="2-td2-left"><div align="left">�ܻ���Ӧ��̯����˰�15�л�22�л�24�С��ܻ���Ӧ��̯Ԥ�ɱ�����</div></td>
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
                    <td nowrap class="2-td2-left"><div align="left">�������з�������˰��</div></td>
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
                    <td nowrap class="2-td2-left"><div align="left">��֧����Ӧ��̯����˰�15�л�22�л�24�С���֧����Ӧ��̯������</div></td>
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
                    <td nowrap class="2-td2-left"><div align="left">���У��ܻ�������������Ӫ����Ӧ��̯����˰��</div></td>
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
                    <td nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ܻ����ѳ�����֧����Ӧ��̯����˰��</div></td>
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
                    <td rowspan="2" nowrap class="2-td2-left"><div align="center">��֧����</div></td>
                    <td nowrap class="2-td2-left"><div align="left">�������</div></td>
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
                    <td nowrap class="2-td2-left"><div align="left">��������˰��</div></td>
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
						onMouseOut="MM_swapImgRestore()" value="��ѯ"
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
						onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1"
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
    <td nowrap><font size="2">&nbsp;&nbsp;&nbsp;* ����ʹ�� IE 5.5 ���ϣ��ֱ��� 800*600 �������վ</font></td>
    <td height="24" align="right"><img src="<%=static_contextpath%>images/q-bottom-tu-01.jpg" alt="�а쵥λ���廪ͬ������ɷ����޹�˾2007_OCT24" width="184" height="24"></td>
  </tr>
</table>

</html:form>
<script language="JavaScript">

	<%/*��ʼ��*/%>	
	function doInit()	
	{
		//alert("��ʼ��...");
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
					//ǿ�Ƹ�ֵ25
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
		System.out.println("�洢���="+jbForm.getSAVE_FLAG());
		if("1".equals(jbForm.getSAVE_FLAG())){
		%>
			//if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(document.forms[0].lje_nsfs[0].checked==1){
				//����ɹ�����ʾת�ֻܷ����걨��
				//if(confirm("����ɹ����Ƿ�ת�롶��ҵ����˰������˰��֧�����������")){
					alert("����ɹ����������д����ҵ����˰������˰��֧�����������");
					jumpToZFJG();
				//}
			}else if(document.forms[0].lje_nsfs[1].checked==1){
				alert("�걨�ѳɹ���");
			}
		<%
		}
			
		//��Ӽ�������
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
		<%/*��Ӧ���������Ļس���ѯ*/%>
	function jsjdmQuery(){
		if(event.keyCode==13) event.keyCode = 9;
	}
	
	//����������������ʱ�Ľ���仯
	function setFoucs(num){
		if(document.forms[0].nsrmc.value==""){
			document.forms[0].jsjdm.focus();
		}else{
			document.getElementById(num+'_1').focus();
		}	
	}
	
	//����걨�·ݲ�������ҵ����˰��������1��4��7��10����ʾ������Ա
	function checkZQ(sbrq,ksrq,jzrq,lx){
		if(!isDate(sbrq,"v")) return;	
		getStartEndDate1(sbrq,ksrq,jzrq,lx);
		var inputDate = sbrq.value;
		mon = inputDate.substring(4,6);
		//alert("mon=="+mon);
		if(mon!='01' && mon!='04' && mon!='07' && mon!='10'){
			alert('ע�⣺'+inputDate+'���������ڡ�');
		}
	}
	
	/**
	* Notes: ��ȡ��ǰ���ڵ���һ��/��/������ֹ���ڡ�
	* Version: 1.0
	* Author: gaoyh
	* Parames: flag 1,Last Year;2,Last Month;Ĭ�ϣ��������
	
	function getStartEndDate1(oInput1,oInput2,oInput3,flag){
		var date_start,date_end;
		var year,mon,days;
		var strMon;
	
		var inputDate = oInput1.value;
	
		//�Ƿ�Ϸ�����
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

	//�жϱȽ�˰����������
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
	  		alert("˰�����ʱ�䲻�ܴ����걨����");
	  		window.event.returnValue=false;
				document.forms[0].skssjsrq.focus();
				document.forms[0].skssjsrq.select();
				return false;
	  	}
  	  	
  		if(objDate1>=objDate2){
			if(obj == document.forms[0].skssjsrq){
				alert("˰�����ʱ�䲻��С�ڻ����˰�ʼʱ��");
			}else{
				alert("˰�ʼʱ�䲻�ܴ��ڻ����˰�����ʱ��");	
			}
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;
		}
		
		if(strYear1!=strYear2){
			alert("˰�ʼʱ����˰�����ʱ�����ݲ�ͬ��˰��ܿ��꣡");
			return false;				
		}
		
	}
	
	<%/*�ж�����ֵ�Ƿ�Ϊ��*/%>
	function saveCheckNull(id){
		obj=document.getElementById(id);
		if(obj.value==""){
			obj.focus();
			alert("��ѡ���Ϊ��!");
			return flase;
		}
		return true;
	}
	
	<%/*�����ݽ���У��*/%>
	function saveCheck(row,zero){
		
		if(!isNum(document.getElementById(row+'_1'), zero, 9999999999999, null, 16, 2)){
			return false;			
		}
		return true;	
	}
	
	<%/*�жϱȽ�˰����������*/%>
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
	  		alert("˰�����ʱ�䲻�ܴ��ڵ�ǰ����");
	  		window.event.returnValue=false;
				document.forms[0].skssjsrq.focus();
				document.forms[0].skssjsrq.select();
				return false;
	  	}
	  		if(objDate1>=objDate2){
				if(obj == document.forms[0].skssjsrq){
					alert("˰�����ʱ�䲻��С�ڻ����˰�ʼʱ��");
				}else{
					alert("˰�ʼʱ�䲻�ܴ��ڻ����˰�����ʱ��");	
				}
				window.event.returnValue=false;
				obj.focus();
				obj.select();
				return false;
			}
			
			if(strYear1!=strYear2){
				alert("˰�ʼʱ����˰�����ʱ�����ݲ�ͬ��˰��ܿ��꣡");
				window.event.returnValue=false;
				obj.focus();
				obj.select();
				return false;				
			}
			
			return true;
			
	}
	
	<%/*����ѡ�����õ�ѡ��ѡ�����*/%>
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
			//ѡ��Ϊ������˰�ģ����������˰��ʽ����ѡ��ͬʱ����Щ������λ���ɱ༭
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
			//ѡ��Ϊ�ܻ���ʱ������������ѡ��Ȩ��
			//document.forms[0].lje_zfjg[0].disabled = false;
			//document.forms[0].lje_zfjg[1].disabled = false;
		}
		//readOnlyFilter();
	}
	
	function checkFilter(){
		//��ͷ��˰����ѡ���Ϊ������˰ʱ��ϵͳ�����ű����2-17�У���18��32������
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

			//���������д�ֵ���
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
		//����ͷ��˰����ѡ���Ϊ������˰-�ܻ���ʱ���ű����2-17��26-30�У������д�����
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

			//���������д�ֵ���
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
			
		//����ͷ��˰����ѡ���Ϊ������˰-��֧����ʱ���ű����28��31��32�У������д�����
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

			//���������д�ֵ���			
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
		//��ͷ��˰����ѡ���Ϊ������˰ʱ��ϵͳ�����ű����2-17�У���18��32������
		if(document.forms[0].lje_nsfs[1].checked==1){
		//alert("readOnlyFilter()--1");
			
			//�������Զ����㲿������ֻ��
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
		//����ͷ��˰����ѡ���Ϊ������˰-�ܻ���ʱ���ű����2-17��26-30�У������д�����
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
		//alert("readOnlyFilter()--2");
			
			//�������Զ����㲿������ֻ��
			
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

		//����ͷ��˰����ѡ���Ϊ������˰-��֧����ʱ���ű����28��31��32�У������д�����
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){	
		//alert("readOnlyFilter()--3");
			
			//�������Զ����㲿������ֻ��
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
			
			//�������Զ����㲿������ֻ��
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
				alert("��ѡ���ֻܷ������ͣ�");
				return false;
			}
		}
		
		//��ͷ��˰����ѡ���Ϊ������˰ʱ��ϵͳ�����ű����2-17�У���18��32������
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
			
			  <%/*�жϵ�5������*/%>
		      if(value_6<0){
		          alert("�ӣ��ض�ҵ������Ӧ��˰���ö5�У�Ӧ���ڵ���0!");
		          document.getElementById('6_1').select();
		          document.getElementById('6_1').focus();
		          return false;
		      }
		      <%/*�жϵ�6������*/%>
		      if(value_7<0){
		          alert("��������˰���루6�У�Ӧ���ڵ���0!");
		          document.getElementById('7_1').select();
		          document.getElementById('7_1').focus();
		          return false;
		      }
		      <%/*�жϵ�7������*/%>
		      if(value_8<0){
		          alert("��˰���루7�У�Ӧ���ڵ���0!");
		          document.getElementById('8_1').select();
		          document.getElementById('8_1').focus();
		          return false;
		      }
		      <%/*�жϵ�8������*/%>
		      var obj_4_5_6_7_jisuanjieguo = (value_5+value_6-value_7-value_8).toFixed(2);
		      if((1*obj_4_5_6_7_jisuanjieguo>0)&&(value_9>obj_4_5_6_7_jisuanjieguo)){
		          alert("�ֲ���ǰ��ȿ���8�У�ӦС�ڵ��ڵ�4+5-6-7,��Ӧ���ڵ���0��");
		          document.getElementById('9_1').select();
		          document.getElementById('9_1').focus();
		          return false;
		      }
		      if((1*obj_4_5_6_7_jisuanjieguo>0)&&(value_9<0)){
		          alert("�ֲ���ǰ��ȿ���8�У�Ӧ���ڵ���0!");
		          document.getElementById('9_1').select();
		          document.getElementById('9_1').focus();
		          return false;
		      }  
		      if((1*obj_4_5_6_7_jisuanjieguo<=0)&&(value_9!=0)){
		  	      alert("��4+5-6-7��С�ڵ���0���ֲ���ǰ��ȿ���8�У�Ӧ����0!");
		  	      document.getElementById('9_1').select();
	              document.getElementById('9_1').focus();
				  return false;
		      }      
		      <%/*�жϵ�12������*/%>
		      if(value_13>value_12){
		          alert("������������˰�12�У�ӦС�ڵ���Ӧ������˰�11�У�,��Ӧ���ڵ���0��");
		          document.getElementById('13_1').select();
		          document.getElementById('13_1').focus();
				  return false;
			  }
			  if(value_13<0){
			      alert("������������˰�12�У�Ӧ���ڵ���0!");
			      document.getElementById('13_1').select();
		          document.getElementById('13_1').focus();
				  return false;
			  }
			  <%/*�жϵ�16������*/%>
		      if(value_17>value_16){
		          alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�16�У�ӦС�ڵ���Ӧ�����ˣ�����˰�15�У�����˶ԣ�");
		          document.getElementById('17_1').select();
		          document.getElementById('17_1').focus();
		        return false;
		      }
		      if(value_17<0){
		          alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�16�У�Ӧ���ڵ���0!");
		          document.getElementById('17_1').select();
		          document.getElementById('17_1').focus();
		        return false;
		      }

		//����ͷ��˰����ѡ���Ϊ������˰-�ܻ���ʱ���ű����2-17��26-30�У������д�����
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
			
			<%/*�жϵ�5������*/%>
		      if(value_6<0){
		          alert("�ӣ��ض�ҵ������Ӧ��˰���ö5�У�Ӧ���ڵ���0!");
		          document.getElementById('6_1').select();
		          document.getElementById('6_1').focus();
		          return false;
		      }
		      <%/*�жϵ�6������*/%>
		      if(value_7<0){
		          alert("��������˰���루6�У�Ӧ���ڵ���0!");
		          document.getElementById('7_1').select();
		          document.getElementById('7_1').focus();
		          return false;
		      }
		      <%/*�жϵ�7������*/%>
		      if(value_8<0){
		          alert("��˰���루7�У�Ӧ���ڵ���0!");
		          document.getElementById('8_1').select();
		          document.getElementById('8_1').focus();
		          return false;
		      }
		      <%/*�жϵ�8������*/%>
		      var obj_4_5_6_7_jisuanjieguo = (value_5+value_6-value_7-value_8).toFixed(2);
		      if((1*obj_4_5_6_7_jisuanjieguo>0)&&(value_9>obj_4_5_6_7_jisuanjieguo)){
		          alert("�ֲ���ǰ��ȿ���8�У�ӦС�ڵ��ڵ�4+5-6-7,��Ӧ���ڵ���0��");
		          document.getElementById('9_1').select();
		          document.getElementById('9_1').focus();
		          return false;
		      }
		      if((1*obj_4_5_6_7_jisuanjieguo>0)&&(value_9<0)){
		          alert("�ֲ���ǰ��ȿ���8�У�Ӧ���ڵ���0!");
		          document.getElementById('9_1').select();
		          document.getElementById('9_1').focus();
		          return false;
		      }  
		      if((1*obj_4_5_6_7_jisuanjieguo<=0)&&(value_9!=0)){
		  	      alert("��4+5-6-7��С�ڵ���0���ֲ���ǰ��ȿ���8�У�Ӧ����0!");
		  	      document.getElementById('9_1').select();
	              document.getElementById('9_1').focus();
				  return false;
		      }    
		      <%/*�жϵ�12������*/%>
		      if(value_13>value_12){
		          alert("������������˰�12�У�ӦС�ڵ���Ӧ������˰�11�У�,��Ӧ���ڵ���0��");
		          document.getElementById('13_1').select();
		          document.getElementById('13_1').focus();
				  return false;
			  }
			  if(value_13<0){
			      alert("������������˰�12�У�Ӧ���ڵ���0!");
			      document.getElementById('13_1').select();
		          document.getElementById('13_1').focus();
				  return false;
			  }
			  <%/*�жϵ�16������*/%>
		      if(value_17>value_16){
		          alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�16�У�ӦС�ڵ���Ӧ�����ˣ�����˰�15�У�����˶ԣ�");
		          document.getElementById('17_1').select();
		          document.getElementById('17_1').focus();
		        return false;
		      }
		      if(value_17<0){
		          alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�16�У�Ӧ���ڵ���0!");
		          document.getElementById('17_1').select();
		          document.getElementById('17_1').focus();
		        return false;
		      }
		      <%/*�жϵ�29������*/%>
		      if(value_27<0){
		          alert("�ܻ�������������Ӫ����Ӧ��̯����˰�29�У�Ӧ���ڵ���0!");
		          document.getElementById('27_1').select();
		          document.getElementById('27_1').focus();
		          return false;
		        }
		      <%/*�жϵ�30������*/%>
		      if(value_28<0){
		          alert("�ܻ����ѳ�����֧����Ӧ��̯����˰�30�У�Ӧ���ڵ���0!");
		          document.getElementById('28_1').select();
		          document.getElementById('28_1').focus();
		          return false;
		        }
		      <%/*�жϵ�29+30�Ƿ���ڵ�28������*/%>
		      var obj_29_30_jsjg = (value_27+value_28).toFixed(2); 
		      if(1*obj_29_30_jsjg>value_26){
		          alert("�ܻ�������������Ӫ����Ӧ��̯����˰�29�У����ܻ����ѳ�����֧����Ӧ��̯����˰�30�У�֮��ӦС�ڵ��ڷ�֧����Ӧ��̯����˰�28�У�!");
		          return false;
		        }
			
		//����ͷ��˰����ѡ���Ϊ������˰-��֧����ʱ���ű����28��31��32�У������д�����
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
			<%/*�жϵ�28������*/%>
	        if(value_26<0){
	          alert("��֧����Ӧ��̯����˰�28�У�Ӧ���ڵ���0!");
	          document.getElementById('26_1').select();
	          document.getElementById('26_1').focus();
	          return false;
	        }
	        <%/*�жϵ�31������*/%>
	        if((value_29<0)||(value_29>100)){
			  alert("���������31�У�Ӧ����0-100�����֣���˶ԣ�");
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
			alert("��������������!");
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
			alert("��������������!");
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
	<%/*�����2�У�Ӫҵ���룩����*/%>
	function compute_Row_3(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('3_1').value="0.00";
		}          

        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}

		var obj_input=document.getElementById('3_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
	}
	<%/*�����3�У�Ӫҵ�ɱ�������*/%>
	function compute_Row_4(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('4_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('4_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
	}
	<%/*�����4�У������ܶ����*/%>
	function compute_Row_5(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('5_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
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
	<%/*�����5������*/%>
	function compute_Row_6(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('6_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('6_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
			alert("�ӣ��ض�ҵ������Ӧ��˰���ö5�У�Ӧ���ڵ���0!");
			document.getElementById('6_1').select();
			document.getElementById('6_1').focus();
		}
		//compute_Row_9();
		compute_Row_10();
	}
	<%/*�����6������*/%>
	function compute_Row_7(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('7_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('7_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
			alert("��������˰���루6�У�Ӧ���ڵ���0!");
			document.getElementById('7_1').select();
			document.getElementById('7_1').focus();
		}
		//compute_Row_9();
		compute_Row_10();
	}
	<%/*�����7������*/%>
	function compute_Row_8(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('8_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('8_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
			alert("��˰���루7�У�Ӧ���ڵ���0!");
			document.getElementById('8_1').select();
			document.getElementById('8_1').focus();
		}
		//compute_Row_9();
		compute_Row_10();
	}
	<%/*�����8������*/%>
	function compute_Row_9(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('9_1').value="0.00";
		}   
        //�ж�����������Ƿ����Ҫ��
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
			alert("�ֲ���ǰ��ȿ���8�У�ӦС�ڵ��ڵ�4+5-6-7,��Ӧ���ڵ���0��");
			document.getElementById('9_1').select();
			document.getElementById('9_1').focus();
			//return false;
		}
		if((1*obj_4_5_6_7_jisuanjieguo>0)&&(1*obj_input.value<0)){
			alert("�ֲ���ǰ��ȿ���8�У�Ӧ���ڵ���0!");
			document.getElementById('9_1').select();
			document.getElementById('9_1').focus();
			//return false;
		}
		if((1*obj_4_5_6_7_jisuanjieguo<=0)&&(1*obj_input.value!=0)){
	  	    alert("��4+5-6-7��С�ڵ���0���ֲ���ǰ��ȿ���8�У�Ӧ����0!");
	  	    //obj_input.value="0.00";
	  	    document.getElementById('9_1').select();
			document.getElementById('9_1').focus();
	        //return false;
	    }
		compute_Row_10();
	}
	<%/*�����9������*/%>
	function compute_Row_10(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('10_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
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
	
	
	<%/*�����10������*/%>
	function compute_Row_11(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('11_1').value="25.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('11_1');
		compute_Row_12();
	}
	<%/*�����11������*/%>
	function compute_Row_12(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('12_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
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
	<%/*�����12������*/%>
	function compute_Row_13(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('13_1').value="0.00";
		}   
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('13_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if((1*obj_input.value)>(1*document.getElementById('12_1').value)){
			alert("������������˰�12�У�ӦС�ڵ���Ӧ������˰�11�У�,��Ӧ���ڵ���0��");
			document.getElementById('13_1').select();
			document.getElementById('13_1').focus();
			//return false;
		}
		if(1*obj_input.value<0){
			alert("������������˰�12�У�Ӧ���ڵ���0!");
			document.getElementById('13_1').select();
			document.getElementById('13_1').focus();
			//return false;
		}
		compute_Row_16();
	}
	<%/*�����13������*/%>
	function compute_Row_14(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('14_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('14_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		compute_Row_16();
	}
	<%/*�����14������*/%>
	function compute_Row_15(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('15_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('15_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		compute_Row_16();
	}
	<%/*�����15������*/%>
	function compute_Row_16(){
		//alert("compute_Row_16");
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('16_1').value="0.00";
		}		   

        //�ж�����������Ƿ����Ҫ��
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
		//�����д�10����ֵ
		document.getElementById('16_1').value=(1*document.getElementById('12_1').value-1*document.getElementById('13_1').value-1*document.getElementById('14_1').value-1*document.getElementById('15_1').value).toFixed(2);
		if((1*document.getElementById('16_1').value)<=0){
		//alert("��15��С�ڵ���0");
			document.getElementById('16_1').value="0.00";
		}
		if((document.forms[0].lje_nsfs[0].checked==1)&&(document.forms[0].lje_zfjg[0].checked==1)){
			compute_Row_24();
			compute_Row_25();
			compute_Row_26();
		}
		compute_Row_18();
	}
	<%/*�����16������*/%>
	function compute_Row_17(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('17_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('17_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value>1*document.getElementById('16_1').value){
			alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�16�У�ӦС�ڵ���Ӧ�����ˣ�����˰�15�У�����˶ԣ�");
			document.getElementById('17_1').select();
			document.getElementById('17_1').focus();
		}
		if(1*obj_input.value<0){
	        alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�16�У�Ӧ���ڵ���0!");
	        document.getElementById('17_1').select();
			document.getElementById('17_1').focus();
	        //return false;
	    }
		compute_Row_18();
	}
	<%/*�����17������*/%>
	function compute_Row_18(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('18_1').value="0.00";
		}		   

        //�ж�����������Ƿ����Ҫ��
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
		//�����д�17����ֵ
		document.getElementById('18_1').value=(1*document.getElementById('16_1').value-1*document.getElementById('17_1').value).toFixed(2);
		if((1*document.getElementById('18_1').value)<=0){
		//alert("��17��С�ڵ���0");
			document.getElementById('18_1').value="0.00";
		}
	}
	<%/*�����26������*/%>
	function compute_Row_24(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('24_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('24_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		obj_input.value=((1*document.getElementById('16_1').value/100)*25).toFixed(2);
	}
	<%/*�����27������*/%>
	function compute_Row_25(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('25_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('25_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		obj_input.value=((1*document.getElementById('16_1').value/100)*25).toFixed(2);
	}
	<%/*�����28������*/%>
	function compute_Row_26(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('26_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
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
	        alert("��֧����Ӧ��̯����˰�28�У�Ӧ���ڵ���0!");
	        document.getElementById('26_1').select();
			document.getElementById('26_1').focus();
	        //return false;
	    }
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
			compute_Row_30();
		}
		//compute_Row_30();
	}
	<%/*�����29������*/%>
	function compute_Row_27(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('27_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('27_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
	        alert("�ܻ�������������Ӫ����Ӧ��̯����˰�29�У�Ӧ���ڵ���0!");
	        document.getElementById('27_1').select();
			document.getElementById('27_1').focus();
	      //return false;
	    }
		var obj_29_30_jsjg = (1*document.getElementById('27_1').value+1*document.getElementById('28_1').value).toFixed(2); 

		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(1*obj_29_30_jsjg>(1*document.getElementById('26_1').value)){
		        alert("�ܻ�������������Ӫ����Ӧ��̯����˰�29�У����ܻ����ѳ�����֧����Ӧ��̯����˰�30�У�֮��ӦС�ڵ��ڷ�֧����Ӧ��̯����˰�28�У�!");
		        document.getElementById('27_1').select();
		        document.getElementById('27_1').focus();
		      //return false;
		    }
		}
	}
	<%/*�����30������*/%>
	function compute_Row_28(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('28_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('28_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
	        alert("�ܻ����ѳ�����֧����Ӧ��̯����˰�30�У�Ӧ���ڵ���0!");
	        document.getElementById('28_1').select();
			document.getElementById('28_1').focus();
	      //return false;
	    }
		var obj_29_30_jsjg = (1*document.getElementById('27_1').value+1*document.getElementById('28_1').value).toFixed(2); 

		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(1*obj_29_30_jsjg>(1*document.getElementById('26_1').value)){
		        alert("�ܻ�������������Ӫ����Ӧ��̯����˰�29�У����ܻ����ѳ�����֧����Ӧ��̯����˰�30�У�֮��ӦС�ڵ��ڷ�֧����Ӧ��̯����˰�28�У�!");
		        document.getElementById('28_1').select();
		        document.getElementById('28_1').focus();
		        //return false;
		    }
		}
	}
	<%/*�����31������*/%>
	function compute_Row_29(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('29_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('29_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if((1*obj_input.value<0)||(1*obj_input.value>100)){
			alert("���������31�У�Ӧ����0-100�����֣���˶ԣ�");
			document.getElementById('29_1').select();
	        document.getElementById('29_1').focus();
		}
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
			compute_Row_30();
		}
	}
	<%/*�����32������*/%>
	function compute_Row_30(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('30_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
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
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;			
		}
		//��ʽ������
		formate(obj);
}


/**
 * ��ʽ����������ΪС�������λ
 * 
 * @param �ı���
 */
function formate(obj){
	//if(!isNumber(obj.value)){
	//	alert("��ʹ�����ָ�ʽ���룡");
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
 * ��ʽ������������0��ͷ
 *
 * @param �ı���
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

    <%/*��ѯ*/%>
	function doQuery(){
		if(!checkBb()){
			return false;
		}
		var jsjdm;
		jsjdm = document.forms[0].jsjdm.value;
		if(jsjdm==""){
		    alert("��������벻�����ǿգ�");
		    return false;
	  	}else{
		    doSubmitForm("Query");
	    	return false;
	  	}
	}

//���汾
	function checkBb(){
		if(parseInt(document.forms[0].skssjsrq.value)>20140331){
			alert("������ʹ�õ���ҵ����˰�걨��汾�뵱ǰ��˰�������ڼ䲻������ѡ����ȷ����ҵ����˰�걨��汾����");
			return false;	
		}
		return true;
	}
	<%/*����*/%>
	function doSave()
	{
		if(!checkBb()){
			return false;
		}
		if(document.forms[0].nsrmc.value==""){
			alert("������Ϣ����ȷ,����ʧ��,����������!");
			document.forms[0].jsjdm.focus();
			return false;
		}	
		if(!saveCheckFilter())
		{
			//alert("���ݲ���ȷ���޷����棡");
			return false;
		}
		doSubmitForm('Save');	
		
	}
	<%/*�鿴�����*/%>
	function doView()
	{
		if(document.forms[0].nsrmc.value==""){
			alert("������Ϣ����ȷ,����ʧ��,����������!");
			document.forms[0].jsjdm.focus();
			return false;
		}	
		if(!saveCheckFilter())
		{
			//alert("���ݲ���ȷ���޷����棡");
			return false;
		}
		if(document.forms[0].lje_nsfs[1].checked==1){
	      alert("������˰����鿴�����");
	      return false;
	    }
	    if(confirm("�鿴��������±���ҳ�����ݣ���ȷ�ϣ�"))
	    {
	    	doSubmitForm('Save');
	    }
	}
	<%/*���*/%>
	function doReset()
	{
		if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
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
	<%/*ɾ��*/%>
	function doDelete()
	{
		if(document.forms[0].jsjdm.value==""){
			alert("������Ϣ����ȷ,ɾ��ʧ��,����������!");
			return false;
		}
		doSubmitForm('Delete');
	}
	<%/*ת����ҵ����˰������˰��֧���������*/%>
	function jumpToZFJG(){
		//document.forms[0].ActionType="Jump";
		//alert("�˴���Ҫ��дת����ҵ����˰������˰��֧������������ܵ�����");
		doSubmitForm('Jump');	
	}
</script>
</body>
</html:html>
