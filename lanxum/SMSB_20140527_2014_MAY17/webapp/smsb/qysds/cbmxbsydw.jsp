<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.cbmxbsydw.web.CbmxbsydwForm" %>
<%@ page import="java.util.HashMap"%>


<html>
<head>
<title>��ҵ��λ��������塢������ҵ��λ֧����Ŀ��ϸ��</title>
<link href="../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/function.js"></script>
</head>
<script language=JavaScript>
/*��ʼ��*/
function doInit()
{
    //��ʼ���ı���onChange�¼�����
		initNumText("je",14);
  <%
	CbmxbsydwForm cbmxbsydwForm=(CbmxbsydwForm)request.getAttribute("cbmxbsydwForm");
	if (cbmxbsydwForm!=null && cbmxbsydwForm.getDataList().size()>0){
		for(int i=0;i<cbmxbsydwForm.getDataList().size();i++){
			HashMap map=(HashMap)cbmxbsydwForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String je=(String)map.get("je");
			%>
			obj = eval("document.getElementById('<%=hc%>_1')");
			obj.value = '<%=je%>';
			
			<% 
		}
	}
	%>
}


<%/*����*/%>
	function doSave()
	{
		doSubmitForm('Save');
		
	}
	
	<%/*���*/%>
	function doReset()
	{
	    if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
	    {
	    <%
		   	for(int i=1; i<=14; i++){
		   	%>
				obj = eval("document.getElementById('<%=i%>_1')");
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
      doSubmitForm('Check');
    }
    	
		<%/*����*/%>
		function doExit()
		{
			doSubmitForm('Exit');
		}
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="doInit()">
 <%@ include file="../include/header.jsp"%>
 <br>
 
<html:form  action="/webapp/smsb/qysds/cbmxbsydwAction.do">
   <html:hidden property="actionType" />
   <html:hidden property="nextTableURL" />
   <html:hidden property="previousTableURL" />
	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1"  colspan="7">��ҵ��λ��������塢������ҵ��λ֧����Ŀ��ϸ��</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="cbmxbsydwForm" property="sbrq" scope="request" filter="true" />
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
				name="cbmxbsydwForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="cbmxbsydwForm" property="nsrmc" scope="request" filter="true" />&nbsp;
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
				<div id="Layer2" style="position:static";>
				<table id="wrklistTable" border="1" cellspacing="0" class="table-99" align="center" >
                  <tr> 
                    <!--<td class="2-td1-left" nowrap>�д�</td>
				   <td class="2-td1-left" nowrap>��Ŀ</td><!-->
                    <td class="2-td1-left" nowrap>�д�</td>
                    <td class="2-td1-left" nowrap>��Ŀ</td>
                    <td class="2-td1-center" nowrap>���</td>       
					<td class="2-td1-left" nowrap>�д�</td>
                    <td class="2-td1-left" nowrap>��Ŀ</td>
                    <td class="2-td1-center" nowrap>���</td>
                  </tr>
				  <tr> 
                   <td class="2-td2-left" nowrap>1</td>
					<td class="2-td2-left-qysds1" nowrap>һ��֧���ܶ�(2+3+����+10)</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='1_1' value='' size='13' tabindex='1'>
				   <input type="hidden" name="hc" value="1"/>
				   </td>
					<td class="2-td2-left" nowrap>9</td>
					<td class="2-td2-left-qysds2" nowrap>  ��ת�Գ����</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='9_1' value='' size='13' tabindex='2'>
				   <input type="hidden" name="hc" value="9"/>
				   </td>
                  </tr>  
				  <tr> 
                    <td class="2-td2-left" nowrap>2</td>
					<td class="2-td2-left-qysds2" nowrap> ��������</td>
                    <td class="2-td2-center" nowrap><input type='text'  name='je' id='2_1' value='' size='13' tabindex='1'>
					<input type="hidden" name="hc" value="2"/>
					</td>					
				   <td class="2-td2-left" nowrap>10</td>
					<td class="2-td2-left-qysds2" nowrap>����֧��</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='10_1' value='' size='13' tabindex='2'>
				   <input type="hidden" name="hc" value="10"/>
				   </td>
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap>3</td>
					<td class="2-td2-left-qysds2" nowrap>�Ͻ��ϼ�֧��</td>
                    <td class="2-td2-center" nowrap><input type='text'  name='je' id='3_1' value='' size='13' tabindex='1'>
					<input type="hidden" name="hc" value="3"/>
					</td>
					<td class="2-td2-left" nowrap>11</td>
					<td class="2-td2-left-qysds1" nowrap>������׼�۳���֧���ܶ�</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='11_1' value='' size='13' tabindex='2'>
				   <input type="hidden" name="hc" value="11"/>
				   </td>
                  </tr>                  
                   
                  <tr> 
                    <td class="2-td2-left" nowrap>4</td>
					<td class="2-td2-left-qysds2" nowrap> ����ר��</td>
                    <td class="2-td2-center" nowrap><input type='text'  name='je' id='4_1' value='' size='13' tabindex='1'>
					<input type="hidden" name="hc" value="4"/>
					</td>
					 <td class="2-td2-left" nowrap>12</td>
					<td class="2-td2-left-qysds2" nowrap>��1��˰�չ涨������۳���֧����Ŀ���</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='12_1' value='' size='13' tabindex='2'>
				   <input type="hidden" name="hc" value="12"/>
				   </td>
                  </tr>    
                   <tr> 
				   <td class="2-td2-left" nowrap>5</td>
					<td class="2-td2-left-qysds2" nowrap>ר��֧��</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='5_1' value='' size='13' tabindex='1'>
				   <input type="hidden" name="hc" value="5"/>
				   </td>
                    <td class="2-td2-left" nowrap>13</td>
					<td class="2-td2-left-qysds2" nowrap> ��2������̯���������֧����Ŀ���</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='13_1' value='' size='13' tabindex='2'>
				   <input type="hidden" name="hc" value="13"/>
				   </td>
				  
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap>6</td>
					<td class="2-td2-left-qysds2" nowrap> ��ҵ֧��</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='6_1' value='' size='13' tabindex='1'>
				   <input type="hidden" name="hc" value="6"/>
				   </td>
				   <td class="2-td2-left" nowrap>14</td>
					<td class="2-td2-left-qysds1" nowrap>����׼��۳���֧���ܶ�</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='14_1' value='' size='13' tabindex='2'>
				   <input type="hidden" name="hc" value="14"/>
				   </td>
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap>7</td>
					<td class="2-td2-left-qysds2" nowrap>��Ӫ֧��</td>
                    <td class="2-td2-center" nowrap><input type='text'  name='je' id='7_1' value='' size='13' tabindex='1'>
					<input type="hidden" name="hc" value="7"/>
					</td>
		
                  </tr>    
				  <tr> 
                    <td class="2-td2-left" nowrap>8</td>
					<td class="2-td2-left-qysds2" nowrap> �Ը�����λ����</td>
                   <td class="2-td2-center" nowrap><input type='text'  name='je' id='8_1' value='' size='13' tabindex='1'>
				   <input type="hidden" name="hc" value="8"/>
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
						onMouseOver="MM_swapImage('jiaoyan','','../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../images/b-bdjyd1.jpg"
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

</table>
<%@ include file="../include/footernew.jsp"%>
</html:form>
</body>
</html>