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
<title>��ҵ����˰��(��)��Ԥ����˰�걨��(A��)</title>
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
									<html:text tabindex="4" property="jsjdm" maxlength="8" size="15" onChange="doQuery()" style="text-align:left"/>
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
									 <bean:write name="czzssdsjb2014Form" property="nsrsbh" scope="request" filter="true" />
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
									   onclick="compute_Row_1()" onchange="compute_Row_1()" disabled="true">������˰��
							</td>
							<td width="10%" nowrap class="2-td2-left" style="border-top: 1px solid #9BB4CA;">
								<div align="left">
								<input type="hidden" name='zfjg' id='zfjg' value="">
									<input type='radio' name='lje_zfjg' style='text-align:right' 
										   value='<%=CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_ZJG%>' size='13' 
										   onclick="compute_Row_2()" onchange="compute_Row_2()" disabled="true">�ܻ���
								</div>
							</td>
							<td width="10%" rowspan="2" height="78px" nowrap class="2-td2-left" style="border-top: 1px solid #9BB4CA; border-right: 1px solid #9BB4CA;">&nbsp;
								<input type='radio' name='lje_nsfs' style='text-align:right' 
									   value='<%=CodeConstant.HZNSFF_QYSDSJB2008_CZZSSDS_DLNS%>' size='13'
									   onclick="compute_Row_1()" onchange="compute_Row_1()"  disabled="true" checked>������˰
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

              </table>
              <table id="mssrxmAutoTable" border="1" cellspacing="0"
						class="table-99" align="center"> 
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
							onblur="formate(this)" onchange="compute_Row_8()">
							<img onclick="doMssrxmAddRow('','')" src="<%=static_contextpath%>images/zbotton-jia2.gif" alt="����" name="add" id="add"  style="cursor:hand"></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;����������Ӧ��˰���ö�</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="10" type='text' name='lje' 
							id='9_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="compute_Row_9()">
							<img onclick="doJzmzxmAddRow('','')" src="<%=static_contextpath%>images/zbotton-jia2.gif" alt="����" name="add" id="add"  style="cursor:hand"></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;�ֲ���ǰ��ȿ���</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">ʵ������4��+5��-6��-7��-8��-9�У�</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">˰�ʣ�25%��</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">Ӧ������˰��</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">������������˰��</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center">
						<input tabindex="15" type='text' name='lje' 
							id='14_1' style='text-align:right' 
							value='0.00' size='13'
							onblur="formate(this)" onchange="">
						<img onclick="doJmxmAddRow('','')" src="<%=static_contextpath%>images/zbotton-jia2.gif" alt="����" name="add" id="add"  style="cursor:hand">
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У�����������С��΢����ҵ��������˰��</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">����ʵ����Ԥ������˰��</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">�����ض�ҵ��Ԥ�ɣ���������˰��</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">Ӧ�����ˣ�����˰�12��-13��-15��-16�У�</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">������ǰ��ȶ���ڱ��ڵֽ�����˰��</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">���£�����ʵ��Ӧ�����ˣ�����˰��</div></td>
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
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>����������һ��˰���Ӧ��˰���ö�ƽ����Ԥ��</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">21
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">��һ��˰���Ӧ��˰���ö�</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">22
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">���£�����Ӧ��˰���ö21�С�1/4��1/12��</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">23
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">˰��(25%)</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">24
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">���£�����Ӧ������˰�22�С�23�У�</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                   <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">25
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">��������������С��΢����ҵ��������˰��</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                   <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">26
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">���£�����ʵ��Ӧ������˰�24��-25�У�</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">27
						</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>��������˰�����ȷ������������Ԥ��</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">28
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">���£�����˰�����ȷ����Ԥ������˰��</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">29
						</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="center"><B>�ֻܷ�����˰��</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">30
							<input type="hidden" name="hc" value="30" id="hc_30">
						</div></td>
                    <td rowspan="4" width="20%" class="2-td2-left"><div align="center">�ܻ���</div></td>
                    <td nowrap class="2-td2-left"><div align="left">�ܻ�����̯����˰�19�л�26�л�28�С��ܻ�����̯Ԥ�ɱ�����</div></td>
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
                    <td nowrap class="2-td2-left"><div align="left">�������з�������˰��</div></td>
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
                    <td nowrap class="2-td2-left"><div align="left">��֧������̯����˰��(19�л�26�л�28�С���֧������̯������</div></td>
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
                    <td nowrap class="2-td2-left"><div align="left">���У��ܻ�������������Ӫ����Ӧ��̯����˰��</div></td>
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
                    <td nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ܻ����ѳ�����֧����Ӧ��̯����˰��</div></td>
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
                    <td rowspan="2" nowrap class="2-td2-left"><div align="center">��֧����</div></td>
                    <td nowrap class="2-td2-left"><div align="left">�������</div></td>
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
                    <td nowrap class="2-td2-left"><div align="left">��������˰��</div></td>
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
<%@ include file="../../include/footer.jsp"%>
<!--  
<table width="100%" height="33" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td background="<%=static_contextpath%>images/q-bottom-bg-01.jpg" height="9px" colspan="2" nowrap></td>
  </tr>
  <tr>
    <td nowrap><font size="2">&nbsp;&nbsp;&nbsp;* ����ʹ�� IE 5.5 ���ϣ��ֱ��� 800*600 �������վ</font></td>
    <td height="24" align="right"><img src="<%=static_contextpath%>images/q-bottom-tu-01.jpg" alt="�а쵥λ���廪ͬ������ɷ����޹�˾2007_OCT24" width="184" height="24"></td>
  </tr>
</table>
-->
</html:form>
<script language="JavaScript">

	<%/*��ʼ��*/%>	
	function doInit()	
	{
		//alert("��ʼ��...");
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
					//ǿ�Ƹ�ֵ25
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
		
		
		
					
		//��ȡ�ӱ���˰������Ŀ������
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
		
				//��ȡ�ӱ������������Ŀ������
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
		
				//��ȡ�ӱ������������Ŀ������
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
		System.out.println("�洢���="+jbForm.getSAVE_FLAG());
		if("1".equals(jbForm.getSAVE_FLAG())){
		%>
			//if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(document.forms[0].lje_nsfs[0].checked==1){
			
				if(document.getElementById('isXky').value=="Y"){
					//modified by zhangj,2014.12.08	
					if(confirm("��ȷ�ϸ���ҵ�������Ƿ������̶ܹ��ʲ������۾ɣ��۳������ߣ�")){
						document.forms[0].actionType.value="JumpGdzc";
						document.forms[0].submit();
						return false;
					}	
					alert("�걨�ѳɹ���");	
				}else{
					//����ɹ�����ʾת�ֻܷ����걨��
					//if(confirm("����ɹ����Ƿ�ת�롶��ҵ����˰������˰��֧�����������")){
						alert("����ɹ����������д����ҵ����˰������˰��֧�����������");
						jumpToZFJG();
					//}
				}
			}else if(document.forms[0].lje_nsfs[1].checked==1){
				//alert("�걨�ѳɹ���");
				//modified by zhangj,2014.12.08	
				if(confirm("��ȷ�ϸ���ҵ�������Ƿ������̶ܹ��ʲ������۾ɣ��۳������ߣ�")){
					document.forms[0].actionType.value="JumpGdzc";
					document.forms[0].submit();
					return false;
				}	
				alert("�걨�ѳɹ���");				
			}
		<%
		}	
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
		
		if(!isNumJbCheck(document.getElementById(row+'_1'), zero, 9999999999999, null, 16, 2)){
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
		//		alert("����ҵ����Ҫ�걨��ҵ����˰��(��)��Ԥ����˰�걨��(A��)��!");
		//		tuichu();
		//	}
		//}
		
		//��ȡ��������
		var jdlx = document.forms[0].jdlx.value;
		if(jdlx!=""){
		//������˰��---||---�ֻܷ������ڱ��е��ܻ�����˰��
		if(jdlx=='<%=CheckJdlx.QYSDSZGFWJDLX_DLNSR%>' || jdlx=='<%=CheckJdlx.QYSDSZGFWJDLX_ZFJGJZBSZJGNSR%>')
		{
			document.forms[0].lje_nsfs[1].checked=1;
		}else if(jdlx=='<%=CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR%>')
		{//��ʡ�з�֧������˰��
			document.forms[0].lje_nsfs[0].checked = true;
			document.forms[0].lje_zfjg[1].checked = true;
		}else if(jdlx=='<%=CheckJdlx.QYSDSZGFWJDLX_KSSZJGNSR%>')
		{//��ʡ���ܻ�����˰��
		    document.forms[0].lje_nsfs[0].checked = true;
			document.forms[0].lje_zfjg[0].checked = true;
		}else{
			alert("����ҵ����Ҫ�걨��ҵ����˰��(��)��Ԥ����˰�걨��(A��)��!");
			tuichu();
		}
		}
		if(document.forms[0].lje_nsfs[1].checked == 1)
		{
			//ѡ��Ϊ������˰�ģ����������˰��ʽ����ѡ��ͬʱ����Щ������λ���ɱ༭
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
		//��ͷ��˰����ѡ���Ϊ������˰ʱ��ϵͳ�����ű����2-19�У���18��35������
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
		//����ͷ��˰����ѡ���Ϊ������˰-�ܻ���ʱ���ű����2-17��32-35�У������д�����
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
			
		//����ͷ��˰����ѡ���Ϊ������˰-��֧����ʱ���ű����28��34��35�У������д�����
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

			//���������д�ֵ���			
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
		//��ͷ��˰����ѡ���Ϊ������˰ʱ��ϵͳ�����ű����2-19�У���18��35������
		if(document.forms[0].lje_nsfs[1].checked==1){
		//alert("readOnlyFilter()--1");
			
			//�������Զ����㲿������ֻ��
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
		//����ͷ��˰����ѡ���Ϊ������˰-�ܻ���ʱ���ű����2-19��32-35�У������д�����
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
		//alert("readOnlyFilter()--2");
			
			//�������Զ����㲿������ֻ��
			
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

		//����ͷ��˰����ѡ���Ϊ������˰-��֧����ʱ���ű����28��34��35�У������д�����
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){	
		//alert("readOnlyFilter()--3");
			
			//�������Զ����㲿������ֻ��
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
			
			//�������Զ����㲿������ֻ��
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
				alert("��ѡ���ֻܷ������ͣ�");
				return false;
			}
		}
		
		//��ͷ��˰����ѡ���Ϊ������˰ʱ��ϵͳ�����ű����2-19�У���18��35������
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
		      <%/*�жϵ�9������*/%>
		      var obj_4_5_6_7_8_jisuanjieguo = (value_5+value_6-value_7-value_8-value_9).toFixed(2);
		      if((1*obj_4_5_6_7_8_jisuanjieguo>0)&&(value_10>obj_4_5_6_7_8_jisuanjieguo)){
		          alert("�ֲ���ǰ��ȿ���9�У�ӦС�ڵ��ڵ�4+5-6-7-8,��Ӧ���ڵ���0��");
		          document.getElementById('10_1').select();
		          document.getElementById('10_1').focus();
		          return false;
		      }
		      if((1*obj_4_5_6_7_8_jisuanjieguo>0)&&(value_10<0)){
		          alert("�ֲ���ǰ��ȿ���9�У�Ӧ���ڵ���0!");
		          document.getElementById('10_1').select();
		          document.getElementById('10_1').focus();
		          return false;
		      }  
		      if((1*obj_4_5_6_7_8_jisuanjieguo<=0)&&(value_10!=0)){
		  	      alert("��4+5-6-7-8��С�ڵ���0���ֲ���ǰ��ȿ���9�У�Ӧ����0!");
		  	      document.getElementById('10_1').select();
	              document.getElementById('10_1').focus();
				  return false;
		      }      
		      <%/*�жϵ�12������*/%>
		      if(value_14>value_13){
		          alert("������������˰�13�У�ӦС�ڵ���Ӧ������˰�12�У�,��Ӧ���ڵ���0��");
		          document.getElementById('14_1').select();
		          document.getElementById('14_1').focus();
				  return false;
			  }
			  if(value_14<0){
			      alert("������������˰�13�У�Ӧ���ڵ���0!");
			      document.getElementById('14_1').select();
		          document.getElementById('14_1').focus();
				  return false;
			  }
			  
			  //У���14������
			  if(!checkedXwqysdse()){
			  	  document.getElementById('15_1').select();
		          document.getElementById('15_1').focus();
				  return false;
			  }
			  
			  if(value_15>value_14){
			  	  alert("���У�����������С��΢����ҵ��������˰�14�У�ӦС�ڵ��ڼ�����������˰�13�У�����˶ԣ�");
		          document.getElementById('14_1').select();
		          document.getElementById('14_1').focus();
		          return false;
			  }
			  <%/*�жϵ�18������*/%>
		      if(value_19>value_18){
		          alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�18�У�ӦС�ڵ���Ӧ�����ˣ�����˰�17�У�����˶ԣ�");
		          document.getElementById('19_1').select();
		          document.getElementById('19_1').focus();
		        return false;
		      }
		      if(value_19<0){
		          alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�18�У�Ӧ���ڵ���0!");
		          document.getElementById('19_1').select();
		          document.getElementById('19_1').focus();
		        return false;
		      }
		      
		      

		//����ͷ��˰����ѡ���Ϊ������˰-�ܻ���ʱ���ű����2-17��32-35�У������д�����
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
		      <%/*�жϵ�9������*/%>
		      var obj_4_5_6_7_8_jisuanjieguo = (value_5+value_6-value_7-value_8-value_9).toFixed(2);
		      if((1*obj_4_5_6_7_8_jisuanjieguo>0)&&(value_10>obj_4_5_6_7_8_jisuanjieguo)){
		          alert("�ֲ���ǰ��ȿ���9�У�ӦС�ڵ��ڵ�4+5-6-7-8,��Ӧ���ڵ���0��");
		          document.getElementById('10_1').select();
		          document.getElementById('10_1').focus();
		          return false;
		      }
		      if((1*obj_4_5_6_7_8_jisuanjieguo>0)&&(value_10<0)){
		          alert("�ֲ���ǰ��ȿ���9�У�Ӧ���ڵ���0!");
		          document.getElementById('10_1').select();
		          document.getElementById('10_1').focus();
		          return false;
		      }  
		      if((1*obj_4_5_6_7_8_jisuanjieguo<=0)&&(value_10!=0)){
		  	      alert("��4+5-6-7-8��С�ڵ���0���ֲ���ǰ��ȿ���9�У�Ӧ����0!");
		  	      document.getElementById('10_1').select();
	              document.getElementById('10_1').focus();
				  return false;
		      }    
		      <%/*�жϵ�13������*/%>
		      if(value_14>value_13){
		          alert("������������˰�13�У�ӦС�ڵ���Ӧ������˰�12�У�,��Ӧ���ڵ���0��");
		          document.getElementById('14_1').select();
		          document.getElementById('14_1').focus();
				  return false;
			  }
			  if(value_14<0){
			      alert("������������˰�13�У�Ӧ���ڵ���0!");
			      document.getElementById('14_1').select();
		          document.getElementById('14_1').focus();
				  return false;
			  }
			  
			  //У���14������
			  if(!checkedXwqysdse()){
			  	  document.getElementById('15_1').select();
		          document.getElementById('15_1').focus();
				  return false;
			  }
			  if(value_15>value_14){
			  	  alert("���У�����������С��΢����ҵ��������˰�14�У�ӦС�ڵ��ڼ�����������˰�13�У�����˶ԣ�");
		          document.getElementById('14_1').select();
		          document.getElementById('14_1').focus();
		          return false;
			  }
			  <%/*�жϵ�18������*/%>
		      if(value_19>value_18){
		          alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�18�У�ӦС�ڵ���Ӧ�����ˣ�����˰�17�У�����˶ԣ�");
		          document.getElementById('19_1').select();
		          document.getElementById('19_1').focus();
		        return false;
		      }
		      if(value_19<0){
		          alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�18�У�Ӧ���ڵ���0!");
		          document.getElementById('19_1').select();
		          document.getElementById('19_1').focus();
		        return false;
		      }
		      <%/*�жϵ�33������*/%>
		      if(value_33<0){
		          alert("�ܻ�������������Ӫ����Ӧ��̯����˰�33�У�Ӧ���ڵ���0!");
		          document.getElementById('33_1').select();
		          document.getElementById('33_1').focus();
		          return false;
		        }
//		      <%/*��3530������*/%>
//		      if(value_28<0){
//		          alert("�ܻ����ѳ�����֧����Ӧ��̯����˰�30�У�Ӧ���ڵ���0!");
//		          document.getElementById('28_1').select();
//		          document.getElementById('28_1').focus();
//		          return false;
//		        }
		      <%/*�жϵ�33�Ƿ���ڵ�32������*/%>
		      var obj_33_jsjg = (value_33).toFixed(2); 
		      if(1*obj_33_jsjg>value_32){
		          alert("�ܻ�������������Ӫ����Ӧ��̯����˰�33�У���ӦС�ڵ��ڷ�֧����Ӧ��̯����˰�32�У�!");
		          return false;
		        }
			
		//����ͷ��˰����ѡ���Ϊ������˰-��֧����ʱ���ű����28��34��35�У������д�����
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
//			<%/*�жϵ�32������*/%>
//	        if(value_32<0){
//	          alert("��֧����Ӧ��̯����˰�32�У�Ӧ���ڵ���0!");
//	          document.getElementById('32_1').select();
//	          document.getElementById('32_1').focus();
//	          return false;
//	        }
	        <%/*�жϵ�34������*/%>
	        if((value_34<0)||(value_34>100)){
			  alert("���������34�У�Ӧ����0-100�����֣���˶ԣ�");
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
	//�Թ���jsisNumJbCheck���а�װ����2014�꼾����Ҫ����������ֽ���У�飬��ֻ���롰-����������Զ���������NaN,������Ҫ�ȶԴ��������ֵ����У�飬Ȼ��ת�����ܽ��м���
	function isNumJbCheck(obj,minValue,maxValue,empty,totalLength, decimalLength){
		formate(obj);
		return isNum(obj,minValue,maxValue,empty,totalLength, decimalLength);
		
	}
	function checkedXwqysdse(){
		//����С΢��ҵ����˰��
		compute_xwqysdse();
		var value_15=document.getElementById('15_1').value;
//		if(value_15*1==0){
//			if(xwblts=="15"){
//				//alert("�����걨����ҵ��������С��΢����ҵ����������ҵ����˰�Ż����ߣ����ü���50������Ӧ��˰���ö��20%��˰�ʽ�����ҵ����˰��");
//				if(confirm("�����걨����ҵ��������С��΢����ҵ����������ҵ����˰�Ż����ߣ����ü���50������Ӧ��˰���ö��20%��˰�ʽ�����ҵ����˰���Ƿ����ܴ��Ż����ߣ����������걨���ݣ�")){
//					return true;
//				}else{
//					return false;
//				}
//			}else if(xwblts=="5"){
//				//alert("�����걨����ҵ��������С��΢����ҵ����˰�Ż����ߣ���20%��˰�ʽ�����ҵ����˰��");
//				if(confirm("�����걨����ҵ��������С��΢����ҵ����˰�Ż����ߣ���20%��˰�ʽ�����ҵ����˰���Ƿ����ܴ��Ż����ߣ����������걨���ݣ�")){
//					return true;
//				}else{
//					return false;
//				}
//			}
//		}
		if(inputValueTemp!=value_15){
			if(inputValueTemp=="0.00"){
				alert("����ҵ������С��΢����ҵ��������14�С����У�����������С��΢����ҵ��������˰�Ӧ��д0��");
				return false;
			}else //if(value_15!=="0.00")
			{
				alert("����ҵ����С��΢����ҵ��������14�С����У�����������С��΢����ҵ��������˰�Ӧ���յ�10�С�ʵ��������"+xwblts+"%("+inputValueTemp+")������д");
				return false;
			}
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
			alert("��������������!");
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
	<%/*�����2�У�Ӫҵ���룩����*/%>
	function compute_Row_3(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('3_1').value="0.00";
		}          

        //�ж�����������Ƿ����Ҫ��
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
	<%/*�����5������*/%>
	function compute_Row_6(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('6_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
		//compute_Row_10();
		compute_Row_11();
	}
	<%/*�����6������*/%>
	function compute_Row_7(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('7_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
		//compute_Row_10();
		compute_Row_11();
	}
	<%/*�����7������*/%>
	function compute_Row_8(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('8_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
		//compute_Row_10();
		compute_Row_11();
	}
	
		<%/*�����8������*/%>
	function compute_Row_9(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('9_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
        	
        	alert("error");
			return false;                         
		}
		var obj_input=document.getElementById('9_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
			alert("����������Ӧ��˰���ö8�У�Ӧ���ڵ���0!");
			document.getElementById('9_1').select();
			document.getElementById('9_1').focus();
		}
		//compute_Row_10();
		compute_Row_11();
	}
	<%/*�����9������*/%>
	function compute_Row_10(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('10_1').value="0.00";
		}   
        //�ж�����������Ƿ����Ҫ��
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
			alert("�ֲ���ǰ��ȿ���9�У�ӦС�ڵ��ڵ�4+5-6-7-8,��Ӧ���ڵ���0��");
			document.getElementById('10_1').select();
			document.getElementById('10_1').focus();
			//return false;
		}
		if((1*obj_4_5_6_7_8_jisuanjieguo>0)&&(1*obj_input.value<0)){
			alert("�ֲ���ǰ��ȿ���9�У�Ӧ���ڵ���0!");
			document.getElementById('10_1').select();
			document.getElementById('10_1').focus();
			//return false;
		}
		if((1*obj_4_5_6_7_8_jisuanjieguo<=0)&&(1*obj_input.value!=0)){
	  	    alert("��4+5-6-7-8��С�ڵ���0���ֲ���ǰ��ȿ���9�У�Ӧ����0!");
	  	    //obj_input.value="0.00";
	  	    document.getElementById('10_1').select();
			document.getElementById('10_1').focus();
	        //return false;
	    }
		compute_Row_11();
	}
	<%/*�����10������*/%>
	function compute_Row_11(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('11_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
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
	
	
	<%/*�����11������*/%>
	function compute_Row_12(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('12_1').value="25.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('12_1');
		compute_Row_13();
	}
	<%/*�����12������*/%>
	function compute_Row_13(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('13_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
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
	<%/*�����13������*/%>
	function compute_Row_14(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('14_1').value="0.00";
		}   
        //�ж�����������Ƿ����Ҫ��
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('14_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if((1*obj_input.value)>(1*document.getElementById('13_1').value)){
			alert("������������˰�13�У�ӦС�ڵ���Ӧ������˰�12�У�,��Ӧ���ڵ���0��");
			document.getElementById('14_1').select();
			document.getElementById('14_1').focus();
			//return false;
		}
		if(1*obj_input.value<0){
			alert("������������˰�13�У�Ӧ���ڵ���0!");
			document.getElementById('14_1').select();
			document.getElementById('14_1').focus();
			//return false;
		}
		compute_Row_18();
	}
	<%/*�����14������*/%>
	
	var inputValueTemp="0.00";
	var xwblts="0";
	function compute_Row_15(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
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
		//10��¼������
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
				 if(syndZsfsdm==<%=CodeConstant.ZSFSDM_CZZS%>){//��������
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
	
	//����С΢��ҵ����˰˰��
	function compute_xwqysdse(){
		var sfxkh=document.forms[0].sfxkh.value;
		var syndZsfsdm=document.forms[0].syndZsfsdm.value;
		var syndFb5jyjg=document.forms[0].syndFb5jyjg.value;
		//10��¼������
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
				 if(syndZsfsdm==<%=CodeConstant.ZSFSDM_CZZS%>){//��������
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
			//alert("�����걨����ҵ��������С��΢����ҵ����������ҵ����˰�Ż����ߣ����ü���50������Ӧ��˰���ö��20%��˰�ʽ�����ҵ����˰��");
			xwblts="15";
		}
		
		if(strBs=="5"){
			//alert("�����걨����ҵ��������С��΢����ҵ����˰�Ż����ߣ���20%��˰�ʽ�����ҵ����˰��");
			xwblts="5";
		}
	}
	<%/*�����15������*/%>
	function compute_Row_16(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('16_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('16_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
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
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('17_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		compute_Row_18();
	}
	<%/*�����17������*/%>
	function compute_Row_18(){
		//alert("compute_Row_16");
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('18_1').value="0.00";
		}		   

        //�ж�����������Ƿ����Ҫ��
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
		//�����д�11����ֵ
		document.getElementById('18_1').value=(1*document.getElementById('13_1').value-1*document.getElementById('14_1').value-1*document.getElementById('16_1').value-1*document.getElementById('17_1').value).toFixed(2);
		if((1*document.getElementById('18_1').value)<=0){
		//alert("��17��С�ڵ���0");
			document.getElementById('18_1').value="0.00";
		}
//		if((document.forms[0].lje_nsfs[0].checked==1)&&(document.forms[0].lje_zfjg[0].checked==1)){
//			compute_Row_30();
//			compute_Row_31();
//			compute_Row_32();
//		}
		compute_Row_20();
	}
	<%/*�����18������*/%>
	function compute_Row_19(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('19_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('19_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value>1*document.getElementById('18_1').value){
			alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�18�У�ӦС�ڵ���Ӧ�����ˣ�����˰�17�У�����˶ԣ�");
			document.getElementById('19_1').select();
			document.getElementById('19_1').focus();
		}
		if(1*obj_input.value<0){
	        alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�18�У�Ӧ���ڵ���0!");
	        document.getElementById('19_1').select();
			document.getElementById('19_1').focus();
	        //return false;
	    }
		compute_Row_20();
	}
	<%/*�����19������*/%>
	function compute_Row_20(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('20_1').value="0.00";
		}		   

        //�ж�����������Ƿ����Ҫ��
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
		//�����д�19����ֵ
		document.getElementById('20_1').value=(1*document.getElementById('18_1').value-1*document.getElementById('19_1').value).toFixed(2);
		if((1*document.getElementById('20_1').value)<=0){
		//alert("��19��С�ڵ���0");
			document.getElementById('20_1').value="0.00";
		}
		
		if((document.forms[0].lje_nsfs[0].checked==1)&&(document.forms[0].lje_zfjg[0].checked==1)){
			compute_Row_30();
			compute_Row_31();
			compute_Row_32();
		}
	}
	<%/*�����26������*/%>
	function compute_Row_30(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('30_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
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
	<%/*�����31������*/%>
	function compute_Row_31(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('31_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
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
	<%/*�����28������*/%>
	function compute_Row_32(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('32_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
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
//	        alert("��֧����Ӧ��̯����˰�32�У�Ӧ���ڵ���0!");
//	        document.getElementById('32_1').select();
//			document.getElementById('32_1').focus();
//	        //return false;
//	    }
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
			compute_Row_35();
		}
		//compute_Row_35();
	}
	<%/*�����33������*/%>
	function compute_Row_33(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('33_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('33_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
	        alert("�ܻ�������������Ӫ����Ӧ��̯����˰�33�У�Ӧ���ڵ���0!");
	        document.getElementById('33_1').select();
			document.getElementById('33_1').focus();
	      //return false;
	    }
		var obj_33_jsjg = (1*document.getElementById('33_1').value).toFixed(2); 

		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(1*obj_33_jsjg>(1*document.getElementById('32_1').value)){
		        alert("�ܻ�������������Ӫ����Ӧ��̯����˰�33�У�ӦС�ڵ��ڷ�֧����Ӧ��̯����˰�32�У�!");
		        document.getElementById('33_1').select();
		        document.getElementById('33_1').focus();
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
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
		var obj_33_30_jsjg = (1*document.getElementById('33_1').value+1*document.getElementById('28_1').value).toFixed(2); 

		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(1*obj_33_30_jsjg>(1*document.getElementById('32_1').value)){
		        alert("�ܻ�������������Ӫ����Ӧ��̯����˰�33�У����ܻ����ѳ�����֧����Ӧ��̯����˰�30�У�֮��ӦС�ڵ��ڷ�֧����Ӧ��̯����˰�28�У�!");
		        document.getElementById('28_1').select();
		        document.getElementById('28_1').focus();
		        //return false;
		    }
		}
	}
	<%/*�����34������*/%>
	function compute_Row_34(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('34_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.getElementById('34_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if((1*obj_input.value<0)||(1*obj_input.value>100)){
			alert("���������34�У�Ӧ����0-100�����֣���˶ԣ�");
			document.getElementById('34_1').select();
	        document.getElementById('34_1').focus();
		}
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
			compute_Row_35();
		}
	}
	<%/*�����35������*/%>
	function compute_Row_35(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('35_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
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
		//�ж�����������Ƿ����Ҫ��
		if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
	//if(!isNumJbCheckber(obj.value)){
	//	alert("��ʹ�����ָ�ʽ���룡");
	//	obj.focus();
	//	return false;
	//}
	if(obj.value==""||obj.value==null){
		return false;	
	}else{
		//if(obj.value=="-"){
		//	alert("��������ȷ������!");
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
		document.forms[0].isQuery.value="0";
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
		if(parseInt(document.forms[0].skssjsrq.value)<=20140331){
			alert("������ʹ�õ���ҵ����˰�걨��汾�뵱ǰ��˰�������ڼ䲻������ѡ����ȷ����ҵ����˰�걨��汾��");
			return false;	
		}
		return true;
	}
	<%/*����*/%>
	function doSave()
	{
		if(document.forms[0].nsrmc.value==""){
			alert("������Ϣ����ȷ,����ʧ��,����������!");
			document.forms[0].jsjdm.focus();
			return false;
		}
		if(document.forms[0].isQuery.value!="1"){
			alert("���޸��˼��������,������ִ�в�ѯ����!");
			document.forms[0].jsjdm.focus();
			return false;
		}
		if(!checkBb()){
			return false;
		}	
		if(!saveCheckFilter())
		{
			//alert("���ݲ���ȷ���޷����棡");
			return false;
		}
		//�ӱ���У��
		saveCbCheck();
		
		doSubmitForm('Save');	
		
	}
	//�ӱ���У��
	function saveCbCheck(){
		//�������ӱ��е�id��value
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
	//			alert("����ҵ��˰Դ��ʶ��Ϊ�ܡ��ء��֣�����Ҫ��д��ҵ����˰��(��)��Ԥ����˰�걨��(A��)��!");
	//			return false;
	//		}
	//	}else{
	//		alert("û�л�ȡ������ҵ��˰Դ��ʶ��������ִ�в�ѯ������");
		//	return false;
	//	}
	//	return true;
	//}
	<%/*�鿴�����*/%>
	function doView()
	{
		if(document.forms[0].nsrmc.value==""){
			alert("������Ϣ����ȷ,����ʧ��,����������!");
			document.forms[0].jsjdm.focus();
			return false;
		}
		if(!checkBb()){
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
		if(document.getElementById('isXky').value=="Y"){
	      alert("�¿�������鿴�����");
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
	
	
		<%/*������*/%>
	function doJmxmAddRow(cbJmxmYzhc,cbJmxmYz)
	{
		//��ȡ���з�֧������ϸ��Ϣ����
		var mxnum = document.forms[0].cbJmxmYz.length;
		//alert("mxnum = " + mxnum);

		//��ȡ��Ҫ����еı��
		var table = document.getElementById("jmxmAutoTable");

		//Ȼ�󴴽���(TR����)
		var NewTr = document.createElement("tr");
		var NewTd1 = document.createElement("<td width=\"52\" nowrap class=\"2-td2-left\">");
			NewTd1.innerHTML="<div align=\"center\">&nbsp;&nbsp;<input type=\"hidden\" name=\"cbJmxmYzhc\" value=\""+cbJmxmYzhc+"\" id=\"\"></div>";
		var NewTd2 = document.createElement("<td colspan=\"2\" nowrap class=\"2-td2-left\">");
			NewTd2.innerHTML="<div align=\"left\">������������˰��</div>";
		var NewTd3 = document.createElement("<td width=\"180\" nowrap class=\"2-td2-left\">");
			//NewTd3.innerHTML='<div align=\"center\"></div>';			
			NewTd3.innerHTML=document.getElementById("jmxm_div").innerHTML;
		var NewTd4 = document.createElement("<td width=\"180\" nowrap class=\"2-td2-center\">");
			NewTd4.innerHTML="<input type='text' name='cbJmxmYz' id='' value='"+cbJmxmYz+"' size='13' maxlength='13' onblur='' onchange=\"cbsjHj('Jmxm')\"><img onclick=\"doDeleteCbRow(this,'Jmxm')\" src=\"<%=static_contextpath%>images/zbotton-jian2.gif\" alt=\"�Ƴ�\" name=\"remove\" id=\"remove\"  style=\"cursor:hand\">";
			//������������䵥Ԫ��
			NewTr.appendChild(NewTd1);
			NewTr.appendChild(NewTd2);
			NewTr.appendChild(NewTd3);
			NewTr.appendChild(NewTd4);

		//�����
		var LastTr = table.rows[table.rows.length - 1];

		LastTr.parentNode.appendChild(NewTr);
		
		
		renameId("Jmxm");
	}
				<%/*������*/%>
	function doMssrxmAddRow(cbMssrxmYzhc,cbMssrxmYz)
	{
		//��ȡ���з�֧������ϸ��Ϣ����
		var mxnum = document.forms[0].cbMssrxmYz.length;
		//alert("mxnum = " + mxnum);

		//��ȡ��Ҫ����еı��
		var table = document.getElementById("mssrxmAutoTable");

		//Ȼ�󴴽���(TR����)
		var NewTr = document.createElement("tr");
		var NewTd1 = document.createElement("<td width=\"52\" nowrap class=\"2-td2-left\">");
			NewTd1.innerHTML="<div align=\"center\">&nbsp;&nbsp;<input type=\"hidden\" name=\"cbMssrxmYzhc\" value=\""+cbMssrxmYzhc+"\" id=\"\"></div>";
		var NewTd2 = document.createElement("<td colspan=\"2\" nowrap class=\"2-td2-left\">");
			NewTd2.innerHTML="<div align=\"left\">&nbsp;&nbsp;&nbsp;&nbsp;</div>";
		var NewTd3 = document.createElement("<td width=\"180\" nowrap class=\"2-td2-left\">");
			//NewTd3.innerHTML='<div align=\"center\"></div>';			
			NewTd3.innerHTML=document.getElementById("mssrxm_div").innerHTML;
		var NewTd4 = document.createElement("<td width=\"180\" nowrap class=\"2-td2-center\">");
			NewTd4.innerHTML="<input type='text' name='cbMssrxmYz' id='' value='"+cbMssrxmYz+"' size='13' maxlength='13' onblur='' onchange=\"cbsjHj('Mssrxm')\"><img onclick=\"doDeleteCbRow(this,'Mssrxm')\" src=\"<%=static_contextpath%>images/zbotton-jian2.gif\" alt=\"�Ƴ�\" name=\"remove\" id=\"remove\"  style=\"cursor:hand\">";
			//������������䵥Ԫ��
			NewTr.appendChild(NewTd1);
			NewTr.appendChild(NewTd2);
			NewTr.appendChild(NewTd3);
			NewTr.appendChild(NewTd4);
			//alert(NewTr.innerHTML);

		//�����
		var LastTr = table.rows[table.rows.length - 1];

		LastTr.parentNode.appendChild(NewTr);
		
		
		renameId("Mssrxm");
	}
	
					<%/*������*/%>
	function doJzmzxmAddRow(cbJzmzxmYzhc,cbJzmzxmYz)
	{
		//��ȡ���з�֧������ϸ��Ϣ����
		var mxnum = document.forms[0].cbJzmzxmYz.length;
		//alert("mxnum = " + mxnum);

		//��ȡ��Ҫ����еı��
		var table = document.getElementById("jzmzxmAutoTable");

		//Ȼ�󴴽���(TR����)
		var NewTr = document.createElement("tr");
		var NewTd1 = document.createElement("<td width=\"52\" nowrap class=\"2-td2-left\">");
			NewTd1.innerHTML="<div align=\"center\">&nbsp;&nbsp;<input type=\"hidden\" name=\"cbJzmzxmYzhc\" value=\""+cbJzmzxmYzhc+"\" id=\"\"></div>";
		var NewTd2 = document.createElement("<td colspan=\"2\" nowrap class=\"2-td2-left\">");
			NewTd2.innerHTML="<div align=\"left\">&nbsp;&nbsp;&nbsp;&nbsp;����������Ӧ��˰���ö�</div>";
		var NewTd3 = document.createElement("<td width=\"180\" nowrap class=\"2-td2-left\">");
			//NewTd3.innerHTML='<div align=\"center\"></div>';			
			NewTd3.innerHTML=document.getElementById("jzmzxm_div").innerHTML;
		var NewTd4 = document.createElement("<td width=\"180\" nowrap class=\"2-td2-center\">");
			NewTd4.innerHTML="<input type='text' name='cbJzmzxmYz' id='' value='"+cbJzmzxmYz+"' size='13' maxlength='13' onblur='' onchange=\"cbsjHj('Jzmzxm')\"><img onclick=\"doDeleteCbRow(this,'Jzmzxm')\" src=\"<%=static_contextpath%>images/zbotton-jian2.gif\" alt=\"�Ƴ�\" name=\"remove\" id=\"remove\"  style=\"cursor:hand\">";
			//������������䵥Ԫ��
			NewTr.appendChild(NewTd1);
			NewTr.appendChild(NewTd2);
			NewTr.appendChild(NewTd3);
			NewTr.appendChild(NewTd4);
			//alert(NewTr.innerHTML);

		//�����
		var LastTr = table.rows[table.rows.length - 1];

		LastTr.parentNode.appendChild(NewTr);
		
		
		renameId("Jzmzxm");
	}
	//�������ʹӱ����Ŀ���Ͳ�����ɾ�������ڵ���
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
	//����ӱ����Ŀ���Ͳ���������Ӧ�Ĵӱ�����������͸�ֵ
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
	//����ӱ����Ŀ���Ͳ�����������Ӧ�ĺϼ�
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
