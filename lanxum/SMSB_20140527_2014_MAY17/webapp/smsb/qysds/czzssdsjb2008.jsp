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
				�л����񹲺͹�<br>��ҵ����˰��(��)��Ԥ����˰�걨��(A��)
			</td>
		</tr>

		<tr>
			<td class="1-td2" colspan="7"><br>
			<table cellspacing="0" class="table-99">

				<tr class="black9">
					<td align="center" nowrap>
					<div align="left">�걨���� <html:text tabindex="1" property="sbrq" size="11"
						maxlength="8" readonly='true' style='text-align:right'
						onchange="checkZQ(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,3)" /></div>
					</td>
					<td wigth="90%" align="center" nowrap>˰���������ڣ� <html:text tabindex="2" property="skssksrq"
						size="15" maxlength="8"
						onchange="isDate(this,false);compareDate(this)"  style="text-align:left" /> �� <html:text tabindex="3"
						property="skssjsrq" size="15" maxlength="8"
						onchange="isDate(this,false);compareDate(this)" style="text-align:left" /></td>
					<td align="right" nowrap>
					<div align="right">��λ��Ԫ�������Ƿ֣�</div>
					</td>
				</tr>
			</table>
			<table class="table-99" border="0" cellpadding="0" cellspacing="0" width="80%">
				<tr>
					<td width="16%" nowrap class="2-td2-t-left"><div align="center">��˰�˵�˰���������&nbsp;</div></td>
					<td colspan="3" nowrap class="2-td2-t-center" align=left><div align="left">&nbsp;&nbsp;<html:text tabindex="4" property="jsjdm" maxlength="8" size="15" onchange="doQuery()" style="text-align:left"/></div></td>
				</tr>
				<TR>
					<td width="16%" nowrap class="2-td2-left"><div align="center">��˰��ʶ���&nbsp;</div></td>
					<td colspan="3" width="30%" nowrap class="2-td2-center" align="left"><div align="left">&nbsp;<bean:write name="czzssdsjb2008Form"
						property="nsrsbh" scope="request" filter="true" /></div></td>
				</tr>
				<TR>
					<td  width="16%" nowrap class="2-td2-left"><div align="center">��˰������&nbsp;</div></td>
					<td colspan="3" nowrap class="2-td2-center" align="left"><div align="left">&nbsp; <bean:write
						name="czzssdsjb2008Form" property="nsrmc" scope="request"
						filter="true" /></div></td>
				</tr>
			</table>
			<br>
			<table class="table-99" border="0" cellpadding="0" cellspacing="0" width="80%">
				<tr>
					<td nowrap class="2-td2-t-left">��˰������</td>
                    <td nowrap class="2-td2-t-left">&nbsp;<input type='radio' name='lje_nsfs'
								style='text-align:right' value='<%=CodeConstant.HZNSFF_QYSDSJB2008_CZZSSDS_DLNS%>' size='13'
								onclick="compute_Row_1()" onchange="compute_Row_1()" checked>������˰&nbsp;<input type='radio' name='lje_nsfs'
								style='text-align:right' value='<%=CodeConstant.HZNSFF_QYSDSJB2008_CZZSSDS_HZNS%>' size='13'
								onclick="compute_Row_1()" onchange="compute_Row_1()">������˰
					</td>
                    <td class="2-td2-t-center" nowrap><div align="left">&nbsp;<input type='radio' name='lje_zfjg'
								style='text-align:right' value='<%=CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_ZJG%>' size='13'
								onclick="compute_Row_2()" onchange="compute_Row_2()" disabled="true">�ܻ���<br>
								<input type='radio' name='lje_zfjg'
								style='text-align:right' value='<%=CodeConstant.HZNSFS_QYSDSJB2008_CZZSSDS_FZJG%>' size='13'
								onclick="compute_Row_2()" onchange="compute_Row_2()" disabled="true">��֧����</div></td>
				</tr>
			</table>
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
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>һ����ʵԤ��</B></div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">ʵ�������</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">˰�ʣ�25%��</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">Ӧ������˰�4�С�5�У�</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">��������˰��</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">ʵ���ѽ�����˰��</div></td>
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
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">Ӧ�����ˣ�������˰�6��-7��-8�У�</div></td>
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
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>����������һ��˰���Ӧ��˰���ö��ƽ����Ԥ��</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">11
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">��һ��˰���Ӧ��˰���ö�</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">12
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">���£�����Ӧ��˰���ö11�С�12��11�С�4��</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">13
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">˰��(25%)</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">14
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">���£�����Ӧ������˰�12�С�13�У�</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">15
						</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="left"><B>��������˰�����ȷ������������Ԥ��</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">16
						</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">���£�����Ӧ������˰�12�С�13�У�</div></td>
                    <td width="180" nowrap class="2-td2-left"><div align="center">---</div></td>
                    <td width="180" nowrap class="2-td2-center"><div align="center">---</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">17
						</div></td>
                    <td colspan="4" nowrap class="2-td2-center"><div align="center"><B>�ֻܷ�����˰��</B></div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left">
						<div align="center">18
							<input type="hidden" name="hc" value="16" id="hc_16">
						</div></td>
                    <td rowspan="4" width="20%" class="2-td2-left"><div align="center">�ܻ���<br>��ע�����ֻܷ���˰�ʲ�һ��ʱ��18-20�й�ʽ����������</div></td>
                    <td nowrap class="2-td2-left"><div align="left">�ܻ���Ӧ��̯������˰�9�л�14�л�16�С�25%��</div></td>
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
                    <td nowrap class="2-td2-left"><div align="left">����������з��������˰�9�л�14�л�16�С�25%��</div></td>
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
                    <td nowrap class="2-td2-left"><div align="left">��֧������̯������˰�9�л�14�л�16�С�50%��</div></td>
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
                    <td nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;���У��ܻ������������������Ӫ���ŷ�̯������˰��</div></td>
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
                    <td rowspan="2" nowrap class="2-td2-left"><div align="center">��֧����</div></td>
                    <td nowrap class="2-td2-left"><div align="left">�������</div></td>
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
                    <td nowrap class="2-td2-left"><div align="left">���������˰�20�С�21�У�</div></td>
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
						onMouseOut="MM_swapImgRestore()" value="��ѯ"
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
					//ǿ�Ƹ�ֵ25
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
		System.out.println("�洢���="+jbForm.getSAVE_FLAG());
		if("1".equals(jbForm.getSAVE_FLAG())){
		%>
			if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
				//����ɹ�����ʾת�ֻܷ����걨��
				//if(confirm("����ɹ����Ƿ�ת�롶��ҵ����˰������˰��֧�����������")){
					alert("����ɹ����������д����ҵ����˰������˰��֧�����������");
					jumpToZFJG();
				//}
			}else if(document.forms[0].lje_nsfs[0].checked==1){
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
		<%/*��ѯ*/%>
	function doQuery(){
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
	
	
	<%/*����*/%>
	function doSave()
	{
		if(document.forms[0].nsrmc.value==""){
			alert("������Ϣ����ȷ,����ʧ��,����������!");
			document.forms[0].jsjdm.focus();
			return false;
		}
		//if(!saveCheckNull("1_1")){return false;}		
		//if(!saveCheckNull("2_1")){return false;}	
			
		if(!saveCheckFilter()){//alert("���ݲ���ȷ���޷����棡");
		return false;}
		doSubmitForm('Save');	
		
	}

	function saveCheckNull(id){
		obj=document.getElementById(id);
		if(obj.value==""){
			obj.focus();
			alert("��ѡ���Ϊ��!");
			return flase;
		}
		return true;
	}
	
	<%/*����ʱ�Ե�1��14�е����ݽ���У��*/%>
	function saveCheck(row,zero){
		
		if(!isNum(document.getElementById(row+'_1'), zero, 9999999999999, null, 16, 2)){
			return false;			
		}
		return true;	
	}
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
		if(document.forms[0].lje_nsfs[0].checked == 1)
		{
			//ѡ��Ϊ������˰�ģ����������˰��ʽ����ѡ��ͬʱ����Щ������λ���ɱ༭
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
			//ѡ��Ϊ�ܻ���ʱ������������ѡ��Ȩ��
			document.forms[0].lje_zfjg[0].disabled = false;
			document.forms[0].lje_zfjg[1].disabled = false;
		}
		//readOnlyFilter();
	}

	<%/*�����1������*/%>
	function compute_Row_1(){
		//alert("document.forms[0].jsjdm.value="+document.forms[0].jsjdm.value+"|");
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
		}
		//document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1
		changeSelect();
		checkFilter();
		readOnlyFilter();
	}
	<%/*�����2������*/%>
	function compute_Row_2(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
		}
		changeSelect();
		checkFilter();
		readOnlyFilter();
	}
	<%/*�����3������*/%>
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
		//if(!(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1)){
		//	if((obj_input.value)*1<0){
		//		obj_input.value="0.00";
		//	}
		//	if(obj_input.value==""){
		//		obj_input.value="0.00";
		//	}
		//}
	}
	<%/*�����4������*/%>
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
		//if(!(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1)){
		//	if((obj_input.value)*1<0){
		//		obj_input.value="0.00";
		//	}
		//	if(obj_input.value==""){
		//		obj_input.value="0.00";
		//	}
		//}
	}
	<%/*�����5������*/%>
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
	<%/*�����6������*/%>
	function compute_Row_6(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('6_1').value="25";
		}   

        //�ж�����������Ƿ����Ҫ��
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
	<%/*�����7������*/%>
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
				alert("����˰�7�У�ӦС�ڵ���Ӧ������˰�6�У�,��Ӧ���ڵ���0��");
				getCellObject().focus();
			}
			if(1*document.getElementById('8_1').value<0){
				alert("����˰�7�У�Ӧ���ڵ���0");
				getCellObject().focus();
			}
		//}
		//
		compute_Row_10();
	}
	<%/*�����8������*/%>
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
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//
		//if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if((1*obj_input.value)>(1*document.getElementById('7_1').value)){
				alert("����˰�7�У�ӦС�ڵ���Ӧ������˰�6�У�,��Ӧ���ڵ���0��");
				document.getElementById('8_1').select();
				document.getElementById('8_1').focus();
				return false;
			}
			if(1*obj_input.value<0){
				alert("����˰�7�У�Ӧ���ڵ���0!");
				document.getElementById('8_1').select();
				document.getElementById('8_1').focus();
				return false;
			}
		//}
		//
		compute_Row_10();
	}
	<%/*�����9������*/%>
	function compute_Row_9(){
		//alert("compute_Row_9.."+document.getElementById('16_1').disabled);
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('9_1').value="0.00";
		}   
		//alert("1.."+document.getElementById('16_1').disabled);

        //�ж�����������Ƿ����Ҫ��
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
			alert("��8�У�ʵ���ѽ�����˰�Ӧ��С��0!");
			document.getElementById('9_1').select();
			document.getElementById('9_1').focus();
			return false;
		}
		//alert("4.."+document.getElementById('16_1').disabled);
		//
		compute_Row_10();
		//alert("5.."+document.getElementById('16_1').disabled);
	}
	<%/*�����10������*/%>
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
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//�����д�10����ֵ
		document.getElementById('10_1').value=(1*document.getElementById('7_1').value-1*document.getElementById('8_1').value-document.getElementById('9_1').value).toFixed(2);
		if((1*document.getElementById('10_1').value)<0){
			document.getElementById('10_1').value="0.00";
		}
		//��ͷ��˰����ѡ���Ϊ������˰ʱ��ϵͳ�����ű����2-9�У���10��22������
		if(document.forms[0].lje_nsfs[0].checked==1){
			//do nothing
		
		}
		else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1)
		{
			/**
			 * �ܾ�������ȡ��18-20���Զ�����
			 * modify by tum at 2008-9-23
			 */
			//����ͷ��˰����ѡ���Ϊ������˰-�ܻ���ʱ���ű����2-9��18-20�У������д�����
			//document.getElementById('16_1').value=((document.getElementById('10_1').value)*0.25).toFixed(2)+"";
			//document.getElementById('17_1').value=((document.getElementById('10_1').value)*0.25).toFixed(2)+"";
			//document.getElementById('18_1').value=((document.getElementById('10_1').value)*0.50).toFixed(2)+"";
		//����ͷ��˰����ѡ���Ϊ������˰-��֧����ʱ���ű����21��22�У������д�����
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
		//�����д�10����ֵ
		document.getElementById('10_1').value=(1*document.getElementById('7_1').value-1*document.getElementById('8_1').value-document.getElementById('9_1').value).toFixed(2);
		if((1*document.getElementById('10_1').value)<0){
			document.getElementById('10_1').value="0.00";
		}
		//��ͷ��˰����ѡ���Ϊ������˰ʱ��ϵͳ�����ű����2-9�У���10��22������
		if(document.forms[0].lje_nsfs[0].checked==1){
			//do nothing
		
		}
		//����ͷ��˰����ѡ���Ϊ������˰-�ܻ���ʱ���ű����2-9��18-20�У������д�����
		else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1)
		{
			/*
			 * �ܾ�������18-20��������д��
			 * modify by tum at 2008-9-23
			 */
			//document.getElementById('16_1').value=((document.getElementById('10_1').value)*0.25).toFixed(2)+"";
			//document.getElementById('17_1').value=((document.getElementById('10_1').value)*0.25).toFixed(2)+"";
			//document.getElementById('18_1').value=((document.getElementById('10_1').value)*0.50).toFixed(2)+"";
		//����ͷ��˰����ѡ���Ϊ������˰-��֧����ʱ���ű����21��22�У������д�����
		}else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[1].checked==1){			
			//do nothing
		}else{		
			//do nothing
		}
	}

	<%/*�����16������*/%>
	function compute_Row_16(){
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
	}
	<%/*�����17������*/%>
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
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
	}
	<%/*�����18������*/%>
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
	}
	<%/*�����19������*/%>
	function compute_Row_19(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('19_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
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
	<%/*�����20������*/%>
	function compute_Row_20(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('20_1').value="0.00";
		}   

        //�ж�����������Ƿ����Ҫ��
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
		<%/*�����20.1������*/%>
	function compute_Row_21(){
		if(document.forms[0].jsjdm.value==""){
			alert("��������������!");
			document.forms[0].jsjdm.focus();
			document.getElementById('21_1').value="0.00";
		}   
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
        //�ж�����������Ƿ����Ҫ��
		var obj_input=document.getElementById('21_1');
		if((obj_input.value)*1<0){
			alert("�ܻ������������������Ӫ���ŷ�̯������˰�20.1�У�Ӧ��С��0����С�ڵ��ڷ�֧������̯������˰�20�У�");
			window.event.returnValue=false;
			document.getElementById('21_1').focus();
			document.getElementById('21_1').select();
			return false;
		}
		if(obj_input.value*1>document.getElementById('18_1').value){
			alert("�ܻ������������������Ӫ���ŷ�̯������˰�20.1�У�Ӧ��С��0����С�ڵ��ڷ�֧������̯������˰�20�У�");
			window.event.returnValue=false;
			document.getElementById('21_1').focus();
			document.getElementById('21_1').select();
			return false;
		}
	}

		<%/*���*/%>
	function doReset()
	{
		
		if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
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
		<%/*ɾ��*/%>
	function doDelete()
	{
		if(document.forms[0].jsjdm.value==""){
			alert("������Ϣ����ȷ,ɾ��ʧ��,����������!");
			return false;
		}
		doSubmitForm('Delete');
	}
	
		//����걨�·ݲ�������ҵ����˰��������4��7��10����ʾ������Ա
	function checkZQ(sbrq,ksrq,jzrq,lx){
		if(!isDate(sbrq,"v")) return;
		getStartEndDate1(sbrq,ksrq,jzrq,lx);
		var inputDate = sbrq.value;
		mon = inputDate.substring(4,6);
		if(mon!='01' && mon!='04' && mon!='07' && mon!='10'){
			alert('ע�⣺'+inputDate+'���������ڡ�');
		}
	}

	

	function saveCheckFilter(){
		//
		if(document.forms[0].lje_nsfs[1].checked==1){
			if((document.forms[0].lje_zfjg[0].checked==0)&&(document.forms[0].lje_zfjg[1].checked==0)){
				alert("��ѡ���ֻܷ������ͣ�");
				return false;
			}
		}
		//alert("saveCheckFilter...");
		//��ͷ��˰����ѡ���Ϊ������˰ʱ��ϵͳ�����ű����2-9�У���10��22������
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
				alert("��6��\"Ӧ������˰��\"����4�С���5�У��ҵ�6�С�0");
				return false;
				}
			}
			//if(!((value_7==(value_5*value_6))&&(value_7>=0))){
			//	alert("��6��\"Ӧ������˰��\"����4�С���5�У��ҵ�6�С�0");
			//	return false;
			//}
			//alert("2");
			if(!((((value_5*value_6).toFixed(2)<0)&&(value_7==0))||((value_5*value_6).toFixed(2)>=0))){				
				alert("����4�С���5�Щ�0�����6��=0��");
				return false;
			}
			//alert("3");
			if(!((value_8<=value_7)&&(value_8>=0))){
				alert("��7��\"��������˰��\"�ܵ�6�У��ҵ�7�С�0��");
				document.getElementById('8_1').select();
				document.getElementById('8_1').focus();
				return false;
			}			
			//alert("3");
			if(value_9<0){
				alert("��8��\"ʵ���ѽ�����˰������˰��\"Ӧ��0��");
				document.getElementById('9_1').select();
				document.getElementById('9_1').focus();
				return false;
			}
			//alert("4");
			if((value_7*1-value_8*1-value_9*1).toFixed(2)>=0){
				if(!((value_10*1)==(value_7*1-value_8*1-value_9*1).toFixed(2))){
					alert("��9��\"Ӧ�����ˣ�����˰��\" ����6��-��7��-��8�С�");
					return false;
				}
			}
			//alert("5");

		//����ͷ��˰����ѡ���Ϊ������˰-�ܻ���ʱ���ű����2-9��18-20�У������д�����
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
				alert("��6��\"Ӧ������˰��\"����4�С���5�У��ҵ�6�С�0");
				return false;
				}
			}
			//if(!((value_7==(value_5*value_6))&&(value_7>=0))){
			//	alert("��6��\"Ӧ������˰��\"����4�С���5�У��ҵ�6�С�0");
			//	return false;
			//}
			//alert("2");
			if(!((((value_5*value_6).toFixed(2)<0)&&(value_7==0))||((value_5*value_6).toFixed(2)>=0))){				
				alert("����4�С���5�Щ�0�����6��=0��");
				return false;
			}
			//alert("3");
			if(!((value_8<=value_7)&&(value_8>=0))){
				alert("��7��\"��������˰��\"�ܵ�6�У��ҵ�7�С�0��");
				document.getElementById('8_1').select();
				document.getElementById('8_1').focus();
				return false;
			}
			//alert("3");
			if(value_9<0){
				alert("��8��\"ʵ���ѽ�����˰������˰��\"Ӧ��0��");
				document.getElementById('9_1').select();
				document.getElementById('9_1').focus();
				return false;
			}
			//alert("3");
			if(value_8<0){
				alert("��7��\"��������˰��\"Ӧ��0��");
				document.getElementById('8_1').select();
				document.getElementById('8_1').focus();
				return false;
			}
			//alert("4");
			if((value_7*1-value_8*1-value_9*1).toFixed(2)>=0){
				if(!((value_10*1)==(value_7*1-value_8*1-value_9*1).toFixed(2))){
					alert("��9��\"Ӧ�����ˣ�����˰��\" ����6��-��7��-��8�С�");
					return false;
				}
			}
			//�жϵ�20.1������������Ƿ����Ҫ��
			var obj_input=document.getElementById('21_1');
			if(!isNum(obj_input , null, null, null, null, 2)){
				return false;                         
			}
		
			if((obj_input.value)*1<0){
				alert("�ܻ������������������Ӫ���ŷ�̯������˰�20.1�У�Ӧ��С0����С�ڵ��ڷ�֧������̯������˰�20�У�");
				window.event.returnValue=false;
				document.getElementById('21_1').focus();
				document.getElementById('21_1').select();
				return false;
			}
			if(obj_input.value*1>document.getElementById('18_1').value){
				alert("�ܻ������������������Ӫ���ŷ�̯������˰�20.1�У�Ӧ��С0����С�ڵ��ڷ�֧������̯������˰�20�У�");
				window.event.returnValue=false;
				document.getElementById('21_1').focus();
				document.getElementById('21_1').select();
				return false;
			}
			//alert("5");
		//����ͷ��˰����ѡ���Ϊ������˰-��֧����ʱ���ű����21��22�У������д�����
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
		//��ͷ��˰����ѡ���Ϊ������˰ʱ��ϵͳ�����ű����2-9�У���10��22������
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
		//����ͷ��˰����ѡ���Ϊ������˰-�ܻ���ʱ���ű����2-9��18-20�У������д�����
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
			
		//����ͷ��˰����ѡ���Ϊ������˰-��֧����ʱ���ű����21��22�У������д�����
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

			//���������д�ֵ���			
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
		//��ͷ��˰����ѡ���Ϊ������˰ʱ��ϵͳ�����ű����2-9�У���10��22������
		if(document.forms[0].lje_nsfs[0].checked==1){
		//alert("readOnlyFilter()--1");
			
			//�������Զ����㲿������ֻ��
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
		//����ͷ��˰����ѡ���Ϊ������˰-�ܻ���ʱ���ű����2-9��18-20�У������д�����
		}else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
		//alert("readOnlyFilter()--2");
			
			//�������Զ����㲿������ֻ��
			
			document.getElementById('7_1').readOnly=true;
			document.getElementById('10_1').readOnly=true;
			/*���ܾ������󣬻�����˰-�ܻ����û���18-20��Ϊ������д����������*/
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


		//����ͷ��˰����ѡ���Ϊ������˰-��֧����ʱ���ű����21��22�У������д�����
		}else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[1].checked==1){	
		//alert("readOnlyFilter()--3");
			
			//�������Զ����㲿������ֻ��
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
			
			//�������Զ����㲿������ֻ��
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
		//alert("�˴���Ҫ��дת����ҵ����˰������˰��֧������������ܵ�����");
		doSubmitForm('Jump');	
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



</script>
</body>
</html:html>
