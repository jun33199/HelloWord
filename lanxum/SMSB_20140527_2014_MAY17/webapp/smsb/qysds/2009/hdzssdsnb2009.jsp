<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.hdzssdsnb.web.HdzssdsnbForm"%>
<%@ page import="java.util.HashMap"%>

<html:html>
<head>
<title>�л����񹲺͹� ��ҵ����˰�����˰�걨��B�ࣩ</title>
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
<html:form method="POST" action="/webapp/smsb/qysds/2009/hdzssdsnbAction2009">
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
		<%
		String notify = (String)request.getAttribute("SYX_NOTIFY");	
		if(notify!=null && !notify.trim().equals("")){
			%>
				<font color=red face="����"><strong>�𾴵���˰�ˣ�</strong></font><br>
				<font color=red face="����"><strong>&nbsp;&nbsp;&nbsp;&nbsp;<%=notify%></strong></font>
			<%
		}
	%>
	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="jsjdmQuery()">
		<tr>
			<td class="1-td1" colspan="7">
				�л����񹲺͹�<br>��ҵ����˰�����˰�걨��B�ࣩ
			</td>
		</tr>

		<tr>
			<td class="1-td2" colspan="7"><br>
			<table cellspacing="0" class="table-99">

				<tr class="black9">
					<td align="center" nowrap>
					<div align="left">�걨���� <html:text property="sbrq" size="11"
						maxlength="8" readonly='true' style='text-align:right'
						onchange="checkZQ(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,3)" /></div>
					</td>
					<td wigth="90%" align="center" nowrap>˰���������ڣ� <html:text property="skssksrq"
						size="15" maxlength="8"
						onchange="isDate(this,false);compareDate(this)"  style="text-align:left" /> �� <html:text
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
					<td colspan="3" nowrap class="2-td2-t-center" align=left>
					<div align="left">
					&nbsp;&nbsp;<html:text tabindex="1" property="jsjdm" maxlength="8" size="15" onchange="doQuery()" style="text-align:left"/>
					</div>
					</td>
				</tr>
				<TR>
					<td width="16%" nowrap class="2-td2-left"><div align="center">��˰��ʶ���&nbsp;</div></td>
					<td width="30%" nowrap class="2-td2-left" align=left>&nbsp;<bean:write name="hdzssdsnbForm2009"
						property="nsrsbh" scope="request" filter="true" /></td>
					<td width="16%" nowrap class="2-td2-left"><div align="center">��˰������&nbsp;</div></td>
					<td width="30%" nowrap class="2-td2-center" align=left>&nbsp; <bean:write
						name="hdzssdsnbForm2009" property="nsrmc" scope="request"
						filter="true" /> </td>
				</tr>
				<TR>
					<td width="16%" nowrap class="2-td2-left"><div align="center">ע���ʱ�����λ����Ԫ��&nbsp;<input type="hidden" name="hc"
								value="9" id="hc_9"></div></td>
					<td width="30%" nowrap class="2-td2-left" align=left>
						<input tabindex='8' type='text' name='je' id='9_1'  value='' size='13' onblur='checkZczb()'>
					</td>
					<td width="16%" nowrap class="2-td2-left"><div align="center">�ʲ��ܶ��λ����Ԫ��&nbsp;<input type="hidden" name="hc"
								value="10" id="hc_10"></div></td>
					<td width="30%" nowrap class="2-td2-center" align=left>
						<input tabindex='9' type='text' name='je' id='10_1'  value='' size='13' onblur='checkZcze()'>
					</td>
				</tr>
			</table>
			<br>

			<TABLE class="table-99" align="center">
				<TR>
					
            <TD width="895" height="334"> 
              <div id="Layer2" style="position:static;">
					
                <table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
                  <tr> 
                    <td colspan="3" nowrap class="2-td1-left"><div align="center">��Ŀ</div></td>
                    <td width="52" nowrap class="2-td1-left"><div align="center">�д�</div></td>
                    <td width="180" nowrap class="2-td1-center">�ۼƽ��</td>
                  </tr>
                  <tr> 
                    <td width="144" rowspan="10" nowrap class="2-td2-left">Ӧ��˰���ö�ļ���</td>
                    <td width="208" rowspan="3" nowrap class="2-td2-left">�������ܶ�˶�Ӧ��˰���ö�</td>
                    <td width="384" nowrap class="2-td2-left">�����ܶ�</td>
                    <td class="2-td2-left" nowrap><div align="center">1<input type="hidden" name="hc"
								value="1" id="hc_1"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='2' type='text' name='je'
								id='1_1' style='text-align:right' value='' size='13'
								 onblur="checkNumInput(this)" onchange="compute_Row_3(this)" class="text-gray" readonly='true'></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>˰����غ˶���Ӧ˰�����ʣ� %��</td>
                    <td class="2-td2-left" nowrap><div align="center">2<input type="hidden" name="hc"
								value="2" id="hc_2"></div></td>
                    <td class="2-td2-center" nowrap>
                    	&nbsp;<input tabindex='3' type='text' name='je' onblur="checkNumInput(this)" id='2_1' style='text-align:right' readonly='true' onchange="compute_Row_3(this)" class="text-gray" value='' size='13'>%</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>Ӧ��˰���ö1�С�2�У�</td>
                    <td class="2-td2-left" nowrap><div align="center">3<input type="hidden" name="hc" value="3" id="hc_3"></div></td>
                    <td class="2-td2-center" nowrap>
                    	<input tabindex='4' type='text' name='je' class="text-gray" 
                    	id='3_1' style='text-align:right'  readonly='true' value='' size='13' onchange="compute_Row_5(this)"></td>
                  </tr>
                  <tr> 
                    <td rowspan="3" nowrap class="2-td2-left">���ɱ����ú˶�Ӧ��˰���ö�</td>
                    <td class="2-td2-left" nowrap>�ɱ������ܶ�</td>
                    <td class="2-td2-left" nowrap><div align="center">4</div></td>
                    <td class="2-td2-center" nowrap>����</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>˰����غ˶���Ӧ˰�����ʣ� %��</td>
                    <td class="2-td2-left" nowrap><div align="center">5</div></td>
                    <td class="2-td2-center" nowrap>����</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>Ӧ��˰���ö�[4�С£�1��5�У���5��]</td>
                    <td class="2-td2-left" nowrap><div align="center">6</div></td>
                    <td class="2-td2-center" nowrap>����</td>
                  </tr>
                  <tr> 
                    <td rowspan="4" nowrap class="2-td2-left">������֧������Ӧ��˰���ö�</td>
                    <td class="2-td2-left" nowrap>����֧���ܶ�</td>
                    <td class="2-td2-left" nowrap><div align="center">7</div></td>
                    <td class="2-td2-center" nowrap>����</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>˰����غ˶���Ӧ˰�����ʣ� %��</td>
                    <td class="2-td2-left" nowrap><div align="center">8</div></td>
                    <td class="2-td2-center" nowrap>����</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>����������[7�С£�1��8�У�]</td>
                    <td class="2-td2-left" nowrap><div align="center">9</div></td>
                    <td class="2-td2-center" nowrap>����</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>Ӧ��˰���ö8�С�9�У�</td>
                    <td class="2-td2-left" nowrap><div align="center">10</div></td>
                    <td class="2-td2-center" nowrap>����</td>
                  </tr>
                  <tr> 
                    <td colspan="2" rowspan="3" nowrap class="2-td2-left">Ӧ������˰��ļ���</td>
                    <td class="2-td2-left" nowrap>˰�ʣ�25%��</td>
                    <td class="2-td2-left" nowrap><div align="center">11<input type="hidden" name="hc"
								value="4" id="hc_4"></div></td>
                    <td class="2-td2-center" nowrap>
                    	&nbsp;<input tabindex='5' type='text' name='je'
								id='4_1' style='text-align:right' value='' size='13' onchange="compute_Row_5(this)"
								readonly='true' class="text-gray">%</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>Ӧ������˰�3�С�11�У�</td>
                    <td class="2-td2-left" nowrap><div align="center">12<input type="hidden" name="hc"
								value="5" id="hc_5"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='6' type='text' name='je'
								id='5_1' class="text-gray" style='text-align:right' readonly='true' value='' size='13'
								 onchange="compute_Row_7(this)" onblur="checkNumInput(this)"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>��������˰��</td>
                    <td class="2-td2-left" nowrap><div align="center">13<input type="hidden" name="hc"
								value="6" id="hc_6"></div></td>
                    <td class="2-td2-center" nowrap>
                    	<input tabindex='7' type='text' name='je'
								id='6_1' style='text-align:right' onblur="checkNumInput(this)" value='' size='13' onchange="compute_Row_7(this)"
								 class="text-gray" readonly='true'>
								</td>
                  </tr>
                  <tr> 
                    <td colspan="2" rowspan="2" nowrap class="2-td2-left">Ӧ�����ˣ�����˰��ļ���</td>
                    <td class="2-td2-left" nowrap>��Ԥ������˰��</td>
                    <td class="2-td2-left" nowrap><div align="center">14<input type="hidden" name="hc"
								value="7" id="hc_7"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='8' type='text' name='je'
								id='7_1' style='text-align:right' value='' size='13'
								 onchange="compute_Row_7(this)" onblur="checkNumInput(this)" class="text-gray" readonly='true'></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>Ӧ�����ˣ�����˰�12�У�13�У�14�У�</td>
                    <td class="2-td2-left" nowrap><div align="center">15<input type="hidden" name="hc"
								value="8" id="hc_8"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='8' type='text' name='je'
								id='8_1' style='text-align:right' value='' size='13'
								 class="text-gray" readonly='true'>
								</td>
                  </tr>
                </table>
					</div>
					</TD>
				</TR>
				<BR>
				<TR class="black9">
					<TD>
					<div align="center"><input tabindex='-1' type="image" accesskey="q"
						onClick="doQuery();return false;"
						onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)"
						onMouseOut="MM_swapImgRestore()" value="��ѯ"
						src="<%=static_contextpath%>images/cx-q1.jpg " width="79"
						height="22" id="chaxun" style="cursor:hand">
					&nbsp;&nbsp;&nbsp;&nbsp; <input tabindex='-1'type="image" accesskey="u"
						style="cursor:hand" onClick="doReset();return false;"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" name="Image13"
						width="79" height="22" border="0" id='qingchu'>
					&nbsp;&nbsp;&nbsp;&nbsp; <input tabindex='-1' type="image" accesskey="s"
						style="cursor:hand" onClick="doSave();return false;"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" name="Image13"
						width="79" height="22" border="0" id='baocun'>
					&nbsp;&nbsp;&nbsp;&nbsp;<input tabindex='-1' type="image" accesskey="x"
						style="cursor:hand" onClick="doDelete();return false;"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" name="Image13"
						width="79" height="22" border="0" id='shanchu'>
					&nbsp;&nbsp;&nbsp;&nbsp;<input tabindex='-1' type="image" accesskey="c"
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
	<%
	  HdzssdsnbForm jbForm=(HdzssdsnbForm)request.getAttribute("hdzssdsnbForm2009");
	%>
	function doInit()	
	{
	<%
	if (jbForm!=null && jbForm.getDataList()!=null && jbForm.getDataList().size()>0){
		for(int i=0;i<jbForm.getDataList().size();i++){
			HashMap map=(HashMap)jbForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String je=(String)map.get("je");
			%>			
			obj = eval("document.getElementById('<%=hc%>_1')");
			obj.value = '<%=je%>';
			<% 
		}
	}
	%>
		document.forms[0].jsjdm.focus();
		<%/*��ҵ��������*/%>
		var qyzslx=document.forms[0].qyzslx.value;
			
		if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){	
			<%/*Ӧ˰������(�༴�������ʡ�)*/%>
			<%/*���õ�2��*/%>
			var obj= document.getElementById('1_1');
			obj.readOnly=false;
			obj.className='text-border';
			obj.focus();
			var obj= document.getElementById('2_1');
			var tmp = Math.round(parseFloat(getValues("cyl"))*100*100)/100;
			obj.value=tmp;
			var obj= document.getElementById('4_1');
			obj.value='25';
			var obj= document.getElementById('6_1');
			obj.readOnly=false;
			obj.className='text-border';
			var obj= document.getElementById('7_1');
			obj.readOnly=false;
			obj.className='text-border';
		}
		if(qyzslx ==4){
			<%/*��������*/%>
			var obj= document.getElementById('5_1');
			obj.readOnly=false;
			obj.className='text-border';
			obj.focus();
			var obj= document.getElementById('6_1');
			obj.readOnly=false;
			obj.className='text-border';
			var obj= document.getElementById('7_1');
			obj.readOnly=false;
			obj.className='text-border';
		}

	}
		<%/*��Ӧ���������Ļس���ѯ*/%>
	function jsjdmQuery(){
		if(event.keyCode==13) event.keyCode = 9;
	}
	
	function ssshmx(){
		for(var i=1; i < 10; i++){
		  obj = eval("document.getElementById('" + i+ "_1')");
		  if(!numberyz(obj))
		    return;
	  }
		doSubmitForm('ToFb');
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
		if(!doCheckmethod()){
			return;
		}	
		var zczbje=document.getElementById('9_1');	
		var zcze=document.getElementById('10_1');	
		if(zcze.value.length<=0){
			alert("�ʲ��ܶ�Ϊ������������ʲ��ܶ");
			zcze.focus();
			zcze.select();
			return false;
		}		
		
		 if(zczbje.value.length<=0){
			alert("ע���ʱ����Ϊ�����������ע���ʱ���");
			zczbje.focus();
			zczbje.select();
			return false;
		}		
		
		doSubmitForm('Save');	
	}
	
	 function checkZczb(){
		 var zczbje=document.getElementById('9_1');		
		
		if(!checkNumber(zczbje.value,15,2,true)){
			alert("ע���ʱ�������Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
			zczbje.focus();
			zczbje.select();
			return false;
		}	
		return true;
	 }
	 
	 function checkZcze(){
		var zcze=document.getElementById('10_1');
		
		if(!checkNumber(zcze.value,15,2,true)){
			alert("�ʲ��ܶ����Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
			zcze.focus();
			zcze.select();
			return false;
		}	
		return true;
	 }
	 
	
	

	//function  : ��������ַ����Ƿ����������	//parameters: DigitString: String,������ַ�����ֵ	//			  totalLength: int, ���ֵĳ������ƣ�������С���㣩	//			  decimalLength: int��С���ĳ�������	//            totalLength-decimalLength �������ֵ�����	//            isPositive �Ƿ�Ϊ�Ǹ��� true��ʾһ��Ҫ�ǷǸ�����false��ʾһ��Ҫ�Ǹ�����null��ʾ���� add by zhangp 2003-09-25	//return    : false: �ַ����а�������������ַ�	//			  true : else	function checkNumber(DigitString, totalLength, decimalLength,isPositive)	{	    var re = "";	   			if(isPositive == "true" || isPositive == true)		{//����ǷǸ���		  if(isNaN(DigitString*1) || DigitString*1<0)				return false;		}		if(isPositive == "false" || isPositive == false)		{//����Ǹ���			if(isNaN(DigitString*1) || DigitString*1>=0)				return false;		}		    if (isNaN(DigitString))	    {	      return false;	    }	    if (decimalLength!=null && decimalLength != 0)	    {   //С����Ϊ��	        re = re+"\\.[\\d]{1,"+ decimalLength +"}"	    }		    if (totalLength == null)	    {   //δ�趨�ܳ���	        re= "\\d*"+re;	    }	    else	    {   //�趨�ܳ���	        //��С������Ϊ�յ�����£������ж����ֵĳ���	        var intergerLength = totalLength;	        if (decimalLength!=null)	        {   //��С�����ֲ�Ϊ���жϣ�������λ����С����λ���Ƿ���ȷ	            intergerLength = totalLength-decimalLength;	        }	        re="([\\d]{0,"+ intergerLength +"}"+re+"|[\\d]{0,"+intergerLength+"})";	    }	    re = new RegExp("^[-|+]?"+re+"$","g");		    //���ַ���Ϊ�ա�λ�����ԡ�����ƥ�������ʱ	    if(re.exec(DigitString) == null)	    {	        return false;	    }	    return true;	}

	
	function doCheck()
		{
			if(doCheckmethod()){
        alert("���ͨ����");
			}
		}
	<%/*���ڹ�ϵУ��*/%>
		function doCheckmethod()
		{
			if(document.forms[0].nsrmc.value==""){
			alert("������Ϣ����ȷ,����ʧ��,����������!");
			document.forms[0].jsjdm.focus();
			return false;
		}
		
		var info = "";
		
		//�����13�Сܵ�12��
		if(getValues('6_1')>getValues('5_1'))
		{
			info =  "�����Ϲ�ʽ�������13��С�ڵ��ڵ�12�У����������룡\n";
			alert(info);
			return false;
		}
		
		  //�ж�15��
		  var ybsds=Math.round((getValues('5_1')-getValues('6_1')-getValues('7_1'))*100)/100;
			if(document.getElementById('8_'+1).value!=parseFloat(ybsds)){
				info = "�����Ϲ�ʽ��15��=12��-13��-14�У����������룡\n";
				alert(info);
				formate(document.getElementById('7_1'));
				return false;
			}
			
			if(info!=""){
				return false;
			}
			else{
				return true;
			}
		}
		
		function compute_Row_3(obj){
			if(!numberyz(obj))
			  return;
			var ybsds=Math.round((getValues('1_1')*getValues('2_1')*0.01)*100)/100;
			document.getElementById('3_'+1).value=parseFloat(ybsds);
			formate(document.getElementById('3_1'));
			compute_Row_5(obj);
		}
		
		function compute_Row_5(obj){
			if(!numberyz(obj))
			  return;
			var ybsds=Math.round((getValues('3_1')*getValues('4_1')*0.01)*100)/100;
			document.getElementById('5_'+1).value=parseFloat(ybsds);
			formate(document.getElementById('5_1'));
			compute_Row_7(obj);
		}
		
		function compute_Row_7(obj){
			if(!numberyz(obj))
			  return;
			var ybsds=Math.round((getValues('5_1')-getValues('6_1')-getValues('7_1'))*100)/100;
			document.getElementById('8_'+1).value=parseFloat(ybsds);
			formate(document.getElementById('8_1'));
		}
	
	  //��ֵ��֤
		function numberyz(obj){
			var str=obj.value.replace(/\s+/gm,'');
			if(str.length!=obj.value.length){
				return false;
			}
			if(!isNumber(obj.value)){
	    	return false;
	    }
	    var temp = obj.value;
	    if(temp.indexOf(".")!=-1){
	      var len=temp.indexOf(".");
	      var cz = temp.length-len;
        if(cz<2||cz>3){
        	return false;
        }
      }
	    return true;
	  }
	
		<%/*����ʱ�Ե�1��14�е����ݽ���У��*/%>
	function saveCheck(row,zero){
		
		if(!isNum(document.getElementById(row+'_1'), zero, null, null, null, 2)){
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

	function getValues(str){
		var strv = document.getElementById(str).value;
		if(strv=="")
		   return 0;
		else
			 return parseFloat(strv);  
	}
		<%/*���*/%>
	function doReset()
	{
		
		if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
		{
	   		//��ҵ��˰����
			var qyzslx=document.forms[0].qyzslx.value;
			
	   		if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){
	   			for(var i=1; i < 9; i++){
					  obj = eval("document.getElementById('" + i+ "_1')");
					    if (i!=2&&i!=4&&i!=6)
						    obj.value = "0.00";
	   			}
	   		}
	   		else if(qyzslx == 4){
	   			for(var i=5; i < 9; i++){
	   				if(i!=6){
					    obj = eval("document.getElementById('" + i+ "_1')");
					    obj.value = "0.00";
					  }
	   			}
	   		}
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
	
		function checkNumInput(obj)

{

                   //�ж�����������Ƿ����Ҫ��

                   if(!isNum(getCellObject() , null, null, null, null, 2)){

                            return false;                         

                   }

                   //��ʽ������

                   formate(obj);

}

 

//��֤���ݵĺϷ���

function formate(obj){

 

         if(obj.value==""||obj.value==null){

                   obj.value="0.00";       

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