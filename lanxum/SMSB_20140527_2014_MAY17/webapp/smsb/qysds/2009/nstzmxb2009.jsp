<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.nstzmxb.web.NstzmxbForm2009" %>
<%@ page import ="java.util.HashMap"%>
<%@ page import="com.syax.creports.Constants"%>
<html>
<head>
<title>��˰������Ŀ��ϸ��</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="../../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/function.js"></script>
</head>
<script language=JavaScript>

	
/*��ʼ��*/
function doInit()
{
	  initNumText("zzje",55);
	  initNumText("ssje",55);
	  initNumText("tzje",55);
	  initNumText("tjje",55);
	<%
	NstzmxbForm2009 nstzmxzjbForm=(NstzmxbForm2009)request.getAttribute("nstzmxbForm2009");
	if (nstzmxzjbForm!=null && nstzmxzjbForm.getDataList().size()>0){
		for(int i=0;i<nstzmxzjbForm.getDataList().size();i++){
			HashMap map=(HashMap)nstzmxzjbForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			System.out.println("hc ==" + hc);
			String zzje=(String)map.get("zzje");
			String ssje=(String)map.get("ssje");
			String tzje=(String)map.get("tzje");
			String tjje=(String)map.get("tjje");
			%>
			obj = eval("document.getElementById('<%=hc%>_1')");
			obj.value = '<%=zzje%>';
			obj = eval("document.getElementById('<%=hc%>_2')");
			obj.value = '<%=ssje%>';
			obj = eval("document.getElementById('<%=hc%>_3')");
			obj.value = '<%=tzje%>';
			obj = eval("document.getElementById('<%=hc%>_4')");
			obj.value = '<%=tjje%>';
			
			<% 
		}
	}
	
	%>
}

<%/*����*/%>
	function doSave()
	{
      var list="<%=Constants.QYSDS_CONTROL_CHAR_FOR_JS%>";
		if (!Char_Vaildate(document.forms[0],list)){
			return false;	
		}
      doSubmitForm('Save');
		
	}
	
	<%/*���*/%>
	function doReset()
	{
	    if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
	    {
	      <%
		   	for(int i=1; i<=55; i++){
		   	%>
				obj = eval("document.getElementById('<%=i%>_1')");
				if(obj.readOnly!=true)
				obj.value = "";
				
				obj = eval("document.getElementById('<%=i%>_2')");
				if(obj.readOnly!=true)
				obj.value = "";
				
				obj = eval("document.getElementById('<%=i%>_3')");
				if(obj.readOnly!=true)
				obj.value = "";
				
				obj = eval("document.getElementById('<%=i%>_4')");
				if(obj.readOnly!=true)
				obj.value = "";
				
				<%
		   	}
		   	%>
	    }
	}
	
	
	
	<%/*ɾ��*/%>
	function doDelete()
	{
		doSubmitForm('Delete');
	}
	
	<%/*���*/%>
	function doChecked()
	{
	   var list="<%=Constants.QYSDS_CONTROL_CHAR_FOR_JS%>";
		if (!Char_Vaildate(document.forms[0],list)){
			return false;	
		}
	   doSubmitForm('Check');
	}
	
		<%/*����*/%>
		function doExit()
		{
			doSubmitForm('Exit');
		}
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0" onLoad="doInit()">
		<%@ include file="../../include/header.jsp"%>
 <br>
    <html:form 
			action="/webapp/smsb/qysds/2009/nstzmxbAction2009.do" method="post">
			
		<html:hidden property="actionType" />
		<html:hidden property="nextTableURL" />
	    <html:hidden property="previousTableURL" />

	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1"  colspan="7">��˰������Ŀ��ϸ�� </td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="nstzmxbForm2009" property="sbrq" scope="request" filter="true" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��λ��Ԫ�������Ƿ֣�
			</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;���������:<bean:write
				name="nstzmxbForm2009" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="nstzmxbForm2009" property="nsrmc" scope="request" filter="true" />&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>	
		<tr>
			<td class="1-td2"  colspan="7">
				<TABLE class="table-99" align="center">
				<TR>
				<TD>
				<div id="Layer2" style="position:static; ">
				<table id="nstzzjmxb_list" border="1" cellspacing="0" class="table-99" align="center" >
                  <tr> 
                    <td rowspan="2" class="2-td1-left">�д�</td>
                    <td rowspan="2" class="2-td1-left">��Ŀ</td>
                    <td class="2-td1-left">���ؽ��</td>
					<td class="2-td1-left">˰�ս��</td>					
                    <td class="2-td1-left">�������</td>     
                    <td class="2-td1-center">�������</td>     
                  </tr>
				  <tr> 
                    <td class="2-td1-left">1</td>
                    <td class="2-td1-left">2</td>                    
                    <td class="2-td1-center">3</td>       
                    <td class="2-td1-center">4</td>       
                  </tr>
				  
                  <tr> 
                    <td class="2-td2-left">1</td>
                    <td class="2-td2-left-qysds1">һ�������������Ŀ</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='1_1' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='1_2' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='1_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='1_4'  value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="1"/>
					</td>
				 </tr>
				<tr>
					<td class="2-td2-left">2</td>
                    <td class="2-td2-left-qysds2"> 1����ͬ�������루��д����һ��</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='2_1' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='2_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='2_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='2_4' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'>
					<input type="hidden" name="hc" value="2"/>
					</td>					  
				</tr>
				<tr>
				    <td class="2-td2-left">#3</td>
                    <td class="2-td2-left-qysds2">2�����ܾ�������</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='3_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder' ></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='3_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='3_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='3_4' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'>
					<input type="hidden" name="hc" value="3"/>
					</td>	
				</tr>	
					
				<tr>
				   <td class="2-td2-left">4</td>
                    <td class="2-td2-left-qysds2">3��������˰�չ涨�������ۿۺ�����</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='4_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='4_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='4_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='4_4' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'>
					<input type="hidden" name="hc" value="4"/>
					</td>
				</tr>	
				<tr>
				   <td class="2-td2-left">*5</td>
                    <td class="2-td2-left-qysds2">4��δ��Ȩ������ԭ��ȷ�ϵ�����</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='5_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='5_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='5_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='5_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="5"/>
					</td>	
				</tr>	
					
				<tr>
				    <td class="2-td2-left">*6</td>
                    <td class="2-td2-left-qysds2">5����Ȩ�淨���㳤�ڹ�ȨͶ�ʶԳ�ʼͶ�ʳɱ�����ȷ������</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='6_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='6_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='6_3'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='6_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="6"/>
					</td>	
                </tr>
				<tr>
				    <td class="2-td2-left">7</td>
                    <td class="2-td2-left-qysds2">6����Ȩ�淨����ĳ��ڹ�ȨͶ�ʳ����ڼ��Ͷ������</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='7_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='7_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='7_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='7_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="7"/>
					</td>	
				</tr>	
				<tr>
				     <td class="2-td2-left">*8</td>
                    <td class="2-td2-left-qysds2">7����������</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='8_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='8_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='8_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='8_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="8"/>
					</td>	
				</tr>	
				<tr>
				     <td class="2-td2-left">*9</td>
                    <td class="2-td2-left-qysds2">8��һ������</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='9_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='9_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='9_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='9_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="9"/>
					</td>	
				</tr>	
				<tr>
				    <td class="2-td2-left">*10</td>
                    <td class="2-td2-left-qysds2">9�����ʼ�ֵ�䶯�����棨��д�����ߣ�</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='10_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='10_2' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='10_3'  value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='10_4'  value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="10"/>
					</td>	
				</tr>	
				<tr>
				    <td class="2-td2-left">11</td>
                    <td class="2-td2-left-qysds2">10��ȷ��Ϊ�����������������</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='11_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='11_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='11_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='11_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="11"/>
					</td>	
				</tr>	
				<tr> 
				    <td class="2-td2-left">12</td>
                    <td class="2-td2-left-qysds2">11������Ӧ˰���ã���д��������</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='12_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='12_2' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='12_3' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='12_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="12"/>
					</td>
				    	
				</tr>	
				<tr>
				    <td class="2-td2-left">13</td>
                    <td class="2-td2-left-qysds2">12��������۳��ľ���Ͷ����ʧ</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='13_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='13_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='13_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='13_4' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'>
					<input type="hidden" name="hc" value="13"/>
					</td>	
				</tr>	
				<tr>
				    <td class="2-td2-left">14</td>
                    <td class="2-td2-left-qysds2">13������˰���루���һ[3]��</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='14_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='14_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='14_3'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='14_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="14"/>
					</td>	
				</tr>	
				<tr>
				    <td class="2-td2-left">15</td>
                    <td class="2-td2-left-qysds2">14����˰���루����壩</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='15_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='15_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='15_3'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='15_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="15"/>
					</td>
				    	
				</tr>	
				<tr>
				    <td class="2-td2-left">16</td>
                    <td class="2-td2-left-qysds2">15���������루����壩</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='16_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='16_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='16_3'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='16_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="16"/>
					</td>	
				</tr>	
				<tr>
				     <td class="2-td2-left">17</td>
                    <td class="2-td2-left-qysds2">16��������˰��Ŀ���ã�����壩</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='17_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='17_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='17_3'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='17_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="17"/>
					</td>	
				</tr>	
				<tr>
				    <td class="2-td2-left">18</td>
                    <td class="2-td2-left-qysds2">17���ֿ�Ӧ��˰���ö����壩</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='18_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='18_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='18_3'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='18_4' value='' size='13' tabindex='2'>
						<input type="hidden" name="hc" value="18"/>
					</td>	
				</tr>	
				<tr>
				    <td class="2-td2-left">19</td>
                    <td class="2-td2-left-qysds2">18������</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='19_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='19_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='19_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='19_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="19"/>
					</td>	
				</tr>	
				<tr>
				     <td class="2-td2-left">20</td>
                    <td class="2-td2-left-qysds1">�����۳��������Ŀ</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='20_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='20_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='20_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='20_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="20"/>
					</td>	
				</tr>	
				<tr>
				   <td class="2-td2-left">21</td>
                    <td class="2-td2-left-qysds2">1����ͬ���۳ɱ�����д�������</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='21_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='21_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='21_3'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='21_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="21"/>
					</td>	
				</tr>	
					<tr>
					<td class="2-td2-left">22</td>
                    <td class="2-td2-left-qysds2">2������н��֧��</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='22_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='22_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='22_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='22_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="22"/>
					</td>
                  </tr> 	
				 <tr>                     
					<td class="2-td2-left">23</td>
                    <td class="2-td2-left-qysds2">3��ְ��������֧��</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='23_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='23_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='23_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='23_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="23"/>
					</td>
                  </tr> 
				  <tr> 
					<td class="2-td2-left">24</td>
                    <td class="2-td2-left-qysds2">4��ְ����������֧��</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='24_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='24_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='24_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='24_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="24"/>
					</td>
                  </tr> 
				  <tr> 
					<td class="2-td2-left">25</td>
                    <td class="2-td2-left-qysds2">5�����ᾭ��֧��</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='25_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='25_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='25_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='25_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="25"/>
					</td>
                  </tr> 
				  <tr> 
					<td class="2-td2-left">26</td>
                    <td class="2-td2-left-qysds2">6��ҵ���д���֧��</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='26_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='26_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='26_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='26_4'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'>
					<input type="hidden" name="hc" value="26"/>
					</td>
                  </tr> 
				  <tr> 
                    <td class="2-td2-left">27</td>
                    <td class="2-td2-left-qysds2">7�����Ѻ�ҵ��������֧������д����ˣ�</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='27_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='27_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='27_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='27_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="27"/>
					</td>
                  </tr> 
				  <tr> 
					<td class="2-td2-left">28</td>
                    <td class="2-td2-left-qysds2">8������֧��</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='28_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='28_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='28_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='28_4'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'>
					<input type="hidden" name="hc" value="28"/>
					</td>
                  </tr> 
				  <tr> 
					<td class="2-td2-left">29</td>
                    <td class="2-td2-left-qysds2">9����Ϣ֧��</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='29_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='29_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='29_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='29_4' value='' readOnly='true' style='background-color:#C1CDCD' size='13' tabindex='-1'>
					<input type="hidden" name="hc" value="29"/>
					</td>
                  </tr> 
				  <tr> 
					<td class="2-td2-left">30</td>
                    <td class="2-td2-left-qysds2">10��ס��������</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='30_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='30_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='30_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='30_4'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'>
					<input type="hidden" name="hc" value="30"/>
					</td>
                  </tr> 
				  <tr>                    
					<td class="2-td2-left">31</td>
                    <td class="2-td2-left-qysds2">11�����𡢷���ͱ�û�ղ������ʧ</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='31_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='31_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='31_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='31_4'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'>
					<input type="hidden" name="hc" value="31"/>
					</td>
                  </tr> 
				  <tr>                     
					<td class="2-td2-left">32</td>
                    <td class="2-td2-left-qysds2">12��˰�����ɽ�</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='32_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='32_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='32_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='32_4'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'>
					<input type="hidden" name="hc" value="32"/>
					</td>
                  </tr> 
				  <tr>                     
					<td class="2-td2-left">33</td>
                    <td class="2-td2-left-qysds2">13������֧��</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='33_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='33_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='33_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='33_4'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'>
					<input type="hidden" name="hc" value="33"/>
					</td>
                  </tr> 
				  <tr>                    
					<td class="2-td2-left">34</td>
                    <td class="2-td2-left-qysds2">14�����������ᱣ���Խɿ�</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='34_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='34_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='34_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='34_4' value='' readOnly='true' style='background-color:#C1CDCD' size='13' tabindex='-1'>
					<input type="hidden" name="hc" value="34"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">35</td>
                    <td class="2-td2-left-qysds2">15���������ϱ��ա�����ҽ�Ʊ���</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='35_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='35_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='35_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='35_4' value='' readOnly='true' style='background-color:#C1CDCD' size='13' tabindex='-1'>
					<input type="hidden" name="hc" value="35"/>
					</td>
                  </tr> 
				  <tr>                    
					<td class="2-td2-left">36</td>
                    <td class="2-td2-left-qysds2">16����δʵ��������������ڵ���ȷ�ϵĲ������</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='36_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='36_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='36_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='36_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="36"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">37</td>
                    <td class="2-td2-left-qysds2">17����ȡ�������޹ص�֧��</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='37_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='37_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='37_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='37_4'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'>
					<input type="hidden" name="hc" value="37"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">38</td>
                    <td class="2-td2-left-qysds2">18������˰��������֧�����γɵķ���</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='38_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='38_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='38_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='38_4'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'>
					<input type="hidden" name="hc" value="38"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">39</td>
                    <td class="2-td2-left-qysds2">19���Ӽƿ۳�������壩</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='39_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='39_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='39_3'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='39_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="39"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">40</td>
                    <td class="2-td2-left-qysds2">20������</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='40_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='40_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='40_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='40_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="40"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">41</td>
                    <td class="2-td2-left-qysds1">�����ʲ��������Ŀ</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='41_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='41_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='41_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='41_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="41"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">42</td>
                    <td class="2-td2-left-qysds2">1���Ʋ���ʧ</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='42_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='42_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='42_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='42_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="42"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">43</td>
                    <td class="2-td2-left-qysds2">2���̶��ʲ��۾ɣ���д����ţ�</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='43_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='43_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='43_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='43_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="43"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">44</td>
                    <td class="2-td2-left-qysds2">3�������������ʲ��۾ɣ���д����ţ�</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='44_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='44_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='44_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='44_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="44"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">45</td>
                    <td class="2-td2-left-qysds2">4�����ڴ�̯���õ�̯������д����ţ�</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='45_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='45_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='45_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='45_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="45"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">46</td>
                    <td class="2-td2-left-qysds2">5�������ʲ�̯������д����ţ�</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='46_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='46_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='46_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='46_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="46"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">47</td>
                    <td class="2-td2-left-qysds2">6��Ͷ��ת�á��������ã���д����ʮһ��</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='47_1'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='47_2'  value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='47_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='47_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="47"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">48</td>
                    <td class="2-td2-left-qysds2">7.������̽Ͷ��(��д�����)</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='48_1' value='' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='48_2' value='' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='48_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='48_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="48"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">49</td>
                    <td class="2-td2-left-qysds2">8.��������Ͷ��(��д�����)</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='49_1'  value='' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='49_2' value='' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='49_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='49_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="49"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">50</td>
                    <td class="2-td2-left-qysds2">9������</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='50_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='50_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='50_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='50_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="50"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">51</td>
                    <td class="2-td2-left-qysds1">�ġ�׼���������Ŀ����д����ʮ��</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='51_1' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='51_2' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='51_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='51_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="51"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">52</td>
                    <td class="2-td2-left-qysds1">�塢���ز���ҵԤ����������Ԥ������</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='52_1' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='52_2' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='52_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='52_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="52"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">53</td>
                    <td class="2-td2-left-qysds1">�����ر���˰����Ӧ˰����</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='53_1' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='53_2' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='53_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='53_4' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'>
					<input type="hidden" name="hc" value="53"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">54</td>
                    <td class="2-td2-left-qysds1">�ߡ�����</td>
                    <td class="2-td2-left"><input type='text'  name='zzje' id='54_1' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='54_2' value='*' size='1' tabindex='-1' readonly='true' class='text-noborder'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='54_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='54_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="54"/>
					</td>
                  </tr> 

				  <tr>                    
					<td class="2-td2-left">55</td>
                    <td class="2-td2-left-qysds1">��     ��</td>
                    <td class="2-td2-left"><input type='text' name='zzje' id='55_1' value='*' tabindex='-1'
														size='1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='ssje' id='55_2' value='*' tabindex='-1' size='1' class='text-noborder' readonly='true'></td> 
                    <td class="2-td2-left"><input type='text'  name='tzje' id='55_3' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-center"><input type='text'  name='tjje' id='55_4' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="55"/>
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
						onclick="gotoPreviousTable()"><img name="xpage" value="��һ�ű�"
						alt="��д��һ�ű�"
						onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/shangyiye1.jpg" id="previoustable"> </a>&nbsp;&nbsp;
						<a style="cursor:hand" id="next"
						onclick="gotoNextTable()"><img name="spage" value="��һ�ű�"
						alt="��д��һ�ű�"
						onMouseOver="MM_swapImage('nexttable','','<%=static_contextpath%>images/xaiyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/xaiyiye1.jpg" id="nexttable"> </a>&nbsp;&nbsp;
				        <input type="image" style="cursor:hand"
						tabIndex="-1" onClick="doReset();return false;" accesskey="u"
						value="���" alt="���ҳ���������Ϣ"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" 
						id="qingchu" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1" onClick="doSave();return false;"
						accesskey="s" value="����" alt="����"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" 
						id="baocun" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doChecked();return false;" accesskey="d"
						value="����У��" alt="���ڹ�ϵУ��"
						onMouseOver="MM_swapImage('jiaoyan','','../../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../../images/b-bdjyd1.jpg"
						 id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doDelete();return false;" accesskey="x"
						value="ȫ��ɾ��" alt="ɾ�������������ݣ��Ҳ��ɻָ�"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" 
						id="shanchu" /> &nbsp;&nbsp; <input type="image" value="����" alt="���ص���ҵ����˰�걨��ҳ��"
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

<%@ include file="../../include/footernew.jsp"%>
</html:form>

</body>
</html>