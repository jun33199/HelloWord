<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.ygyjzjlnstzb.web.YgyjzjlnstzbForm" %>
<%@ page import ="java.util.HashMap"%>
<%@ page import="com.syax.creports.Constants"%>
<html>
<head>
<title>�Թ��ʼ�ֵ�����ʲ���˰������</title>
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
	  initNumText("qczzje",10);
	  initNumText("qcjsjc",10);
	  initNumText("qmzzje",10);
	  initNumText("qmjsjc",10);
	  initNumText("nstze",10);
	<%
	YgyjzjlnstzbForm nstzmxzjbForm=(YgyjzjlnstzbForm)request.getAttribute("ygyjzjlnstzbForm2009");
	if (nstzmxzjbForm!=null && nstzmxzjbForm.getDataList().size()>0){
		for(int i=0;i<nstzmxzjbForm.getDataList().size();i++){
			HashMap map=(HashMap)nstzmxzjbForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			System.out.println("hc ==" + hc);
			String qczzje=(String)map.get("qczzje");
			String qcjsjc=(String)map.get("qcjsjc");
			String qmzzje=(String)map.get("qmzzje");
			String qmjsjc=(String)map.get("qmjsjc");
			String nstze=(String)map.get("nstze");
			%>
			obj = eval("document.getElementById('<%=hc%>_1')");
			obj.value = '<%=qczzje%>';
			obj = eval("document.getElementById('<%=hc%>_2')");
			obj.value = '<%=qcjsjc%>';
			obj = eval("document.getElementById('<%=hc%>_3')");
			obj.value = '<%=qmzzje%>';
			obj = eval("document.getElementById('<%=hc%>_4')");
			obj.value = '<%=qmjsjc%>';
			obj = eval("document.getElementById('<%=hc%>_5')");
			obj.value = '<%=nstze%>';
			
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
		   	for(int i=1; i<=10; i++){
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
				obj = eval("document.getElementById('<%=i%>_5')");
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
			action="/webapp/smsb/qysds/2009/ygyjzjlnstzbAction.do" method="post">
			
		<html:hidden property="actionType" />
		<html:hidden property="nextTableURL" />
	    <html:hidden property="previousTableURL" />

	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1"  colspan="7">�Թ��ʼ�ֵ�����ʲ���˰������ </td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="ygyjzjlnstzbForm2009" property="sbrq" scope="request" filter="true" />
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
				name="ygyjzjlnstzbForm2009" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="ygyjzjlnstzbForm2009" property="nsrmc" scope="request" filter="true" />&nbsp;
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
                    <td rowspan="3" class="2-td1-left">�д�</td>
                    <td rowspan="3" class="2-td1-left">�ʲ�����</td>
                    <td colspan="2" class="2-td1-left">�ڳ����</td>
                    <td colspan="2" class="2-td1-left">��ĩ���</td>
                    <td rowspan="2" class="2-td1-center">��˰�������˰�����ԡ�������ʾ��</td>
                    </tr>
                    <tr>
                    <td class="2-td1-left">���ؽ����ʼ�ֵ��</td>
					<td class="2-td1-left">��˰����</td>					
                    <td class="2-td1-left">���ؽ����ʼ�ֵ��</td>     
                    <td class="2-td1-left">��˰����</td>     
                  </tr>
				  <tr> 
                    <td class="2-td1-left">1</td>
                    <td class="2-td1-left">2</td>                    
                    <td class="2-td1-center">3</td>       
                    <td class="2-td1-center">4</td>       
                    <td class="2-td1-center">5</td>       
                  </tr>
				  
                  <tr> 
                    <td class="2-td2-left">1</td>
                    <td class="2-td2-left-qysds1">һ�����ʼ�ֵ��������䶯���뵱������Ľ����ʲ�</td>
                    <td class="2-td2-left"><input type='text'  name='qczzje' id='1_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='qcjsjc' id='1_2'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmzzje' id='1_3'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmjsjc' id='1_4'  value='' size='13' tabindex='2'>
                    <td class="2-td2-center"><input type='text'  name='nstze' id='1_5'  value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="1"/>
					</td>
				 </tr>
				<tr>
					<td class="2-td2-left">2</td>
                    <td class="2-td2-left-qysds2">1.�����Խ����ʲ�</td>
                    <td class="2-td2-left"><input type='text'  name='qczzje' id='2_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='qcjsjc' id='2_2'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmzzje' id='2_3'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmjsjc' id='2_4'  value='' size='13' tabindex='2'>
                    <td class="2-td2-center"><input type='text'  name='nstze' id='2_5'  value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="2"/>
					</td>					  
				</tr>
				<tr>
				    <td class="2-td2-left">3</td>
                    <td class="2-td2-left-qysds2">2.�������ڹ���</td>
                    <td class="2-td2-left"><input type='text'  name='qczzje' id='3_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='qcjsjc' id='3_2'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmzzje' id='3_3'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmjsjc' id='3_4'  value='' size='13' tabindex='2'>
                    <td class="2-td2-center"><input type='text'  name='nstze' id='3_5'  value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="3"/>
					</td>	
				</tr>	
					
				<tr>
				   <td class="2-td2-left">4</td>
                    <td class="2-td2-left-qysds2">3.�����Թ��ʼ�ֵ�����Ľ����ʲ�</td>
                    <td class="2-td2-left"><input type='text'  name='qczzje' id='4_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='qcjsjc' id='4_2'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmzzje' id='4_3'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmjsjc' id='4_4'  value='' size='13' tabindex='2'>
                    <td class="2-td2-center"><input type='text'  name='nstze' id='4_5'  value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="4"/>
					</td>
				</tr>	
				<tr>
				   <td class="2-td2-left">5</td>
                    <td class="2-td2-left-qysds1">�������ʼ�ֵ��������䶯���뵱������Ľ��ڸ�ծ</td>
                    <td class="2-td2-left"><input type='text'  name='qczzje' id='5_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='qcjsjc' id='5_2'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmzzje' id='5_3'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmjsjc' id='5_4'  value='' size='13' tabindex='2'>
                    <td class="2-td2-center"><input type='text'  name='nstze' id='5_5'  value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="5"/>
					</td>	
				</tr>	
					
				<tr>
				    <td class="2-td2-left">6</td>
                    <td class="2-td2-left-qysds2">1.�����Խ��ڸ�ծ</td>
                    <td class="2-td2-left"><input type='text'  name='qczzje' id='6_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='qcjsjc' id='6_2'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmzzje' id='6_3'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmjsjc' id='6_4'  value='' size='13' tabindex='2'>
                    <td class="2-td2-center"><input type='text'  name='nstze' id='6_5'  value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="6"/>
					</td>	
                </tr>
				<tr>
				    <td class="2-td2-left">7</td>
                    <td class="2-td2-left-qysds2">2.�������ڹ���</td>
                    <td class="2-td2-left"><input type='text'  name='qczzje' id='7_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='qcjsjc' id='7_2'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmzzje' id='7_3'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmjsjc' id='7_4'  value='' size='13' tabindex='2'>
                    <td class="2-td2-center"><input type='text'  name='nstze' id='7_5'  value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="7"/>
					</td>	
				</tr>	
				<tr>
				     <td class="2-td2-left">8</td>
                    <td class="2-td2-left-qysds2">3.�����Թ��ʼ�ֵ�����Ľ��ڸ�ծ</td>
                    <td class="2-td2-left"><input type='text'  name='qczzje' id='8_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='qcjsjc' id='8_2'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmzzje' id='8_3'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmjsjc' id='8_4'  value='' size='13' tabindex='2'>
                    <td class="2-td2-center"><input type='text'  name='nstze' id='8_5'  value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="8"/>
					</td>	
				</tr>	
				<tr>
				     <td class="2-td2-left">9</td>
                    <td class="2-td2-left-qysds1">����Ͷ���Է��ز�</td>
                    <td class="2-td2-left"><input type='text'  name='qczzje' id='9_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='qcjsjc' id='9_2'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmzzje' id='9_3'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmjsjc' id='9_4'  value='' size='13' tabindex='2'>
                    <td class="2-td2-center"><input type='text'  name='nstze' id='9_5'  value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="9"/>
					</td>	
				</tr>	
				<tr>
				    <td class="2-td2-left">10</td>
                    <td class="2-td2-left-qysds1">�ϼ�</td>
                    <td class="2-td2-left"><input type='text'  name='qczzje' id='10_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='qcjsjc' id='10_2'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmzzje' id='10_3'  value='' size='13' tabindex='2'>
                    <td class="2-td2-left"><input type='text'  name='qmjsjc' id='10_4'  value='' size='13' tabindex='2'>
                    <td class="2-td2-center"><input type='text'  name='nstze' id='10_5'  value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="10"/>
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