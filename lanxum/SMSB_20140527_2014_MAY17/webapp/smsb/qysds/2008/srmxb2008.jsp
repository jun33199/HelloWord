<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.HashMap"%>
<%@ page import=" com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.srmxb.web.SrmxbForm2008" %>


<html>
<head>
<title>������ϸ��</title>
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
<%/*��ʼ��*/%>
function doInit()
{
     //��ʼ���ı���onChange�¼�����
		initNumText("je",26);
   <%
     SrmxbForm2008 srmxbForm = (SrmxbForm2008)request.getAttribute("srmxbForm2008");
     if(srmxbForm!=null && srmxbForm.getSrmxb().size()>0)
     {
       for(int i=0;i<srmxbForm.getSrmxb().size();i++)
       {
         HashMap map = new HashMap();
         map = (HashMap)srmxbForm.getSrmxb().get(i);
         String hc = (String)map.get("hc");
         String je =(String)map.get("je");
         %>
          var obj = document.getElementById('<%=hc%>_1');
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
		   	for(int i=1; i<=26; i++){
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
	<%@include file="../../include/header.jsp"%>
 <br>
    <html:form  action="/webapp/smsb/qysds/2008/srmxbAction2008.do" method="post">	
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1"  colspan="7">������ϸ��</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="srmxbForm2008" property="sbrq" scope="request" filter="true" />
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
				name="srmxbForm2008" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="srmxbForm2008" property="nsrmc" scope="request" filter="true" />&nbsp;
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
				<div id="Layer2" style="position:static;">
				<table id="srmxb_list" border="1" cellspacing="0" class="table-99" align="center">
                  <tr> 
                    <td class="2-td1-left" nowrap>�д�</td>
                    <td class="2-td1-left" nowrap>��Ŀ</td>
                    <td class="2-td1-center" nowrap>���</td>       
					<td class="2-td1-left" nowrap>�д�</td>
                    <td class="2-td1-left" nowrap>��Ŀ</td>
                    <td class="2-td1-center" nowrap>���</td>
                  </tr>
				  <tr> 
                   <td class="2-td2-left" nowrap>1</td>
					<td class="2-td2-left-qysds1" nowrap>һ�����ۣ�Ӫҵ������ϼƣ�2��13��</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='1_1' value='' size='13' tabindex='1'>
                   <input type="hidden" name="hc" value="1"/>
                   </td>
					<td class="2-td2-left" nowrap>13</td>
					<td class="2-td2-left-qysds2" nowrap>��������ͬ�������루14��15��16��</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='13_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="13"/>
                   </td>
                  </tr>  
				  <tr> 
                    <td class="2-td2-left" nowrap>2</td>
					<td class="2-td2-left-qysds2" nowrap>��һ��Ӫҵ����ϼƣ�3��8��</td>
                    <td class="2-td2-center" nowrap>
                    <input type='text'  name='je' id='2_1' value='' size='13' tabindex='1'>
                    <input type="hidden" name="hc" value="2"/>
                    </td>					
				   <td class="2-td2-left" nowrap>14</td>
					<td class="2-td2-left-qysds3" nowrap>��1���ǻ����Խ�����ͬ��������</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='14_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="14"/>
                   </td>
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap>3</td>
					<td class="2-td2-left-qysds2" nowrap>1.��Ӫҵ�����루4��5��6��7��</td>
                    <td class="2-td2-center" nowrap>
                    <input type='text'  name='je' id='3_1' value='' size='13' tabindex='1'>
                    <input type="hidden" name="hc" value="3"/>
                    </td>
					<td class="2-td2-left" nowrap>15</td>
					<td class="2-td2-left-qysds3" nowrap>��2������Ʋ���������ͬ��������</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='15_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="15"/>
                   </td>
                  </tr>                  
                   
                  <tr> 
                    <td class="2-td2-left" nowrap>4</td>
					<td class="2-td2-left-qysds3" nowrap>��1�����ۻ���</td>
                    <td class="2-td2-center" nowrap>
                    <input type='text'  name='je' id='4_1' value='' size='13' tabindex='1'>
                    <input type="hidden" name="hc" value="4"/>
                    </td>
					 <td class="2-td2-left" nowrap>16</td>
					<td class="2-td2-left-qysds3" nowrap>��3��������ͬ��������</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='16_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="16"/>
                   </td>
                  </tr>    
                   <tr> 
				   <td class="2-td2-left" nowrap>5</td>
					<td class="2-td2-left-qysds3" nowrap>��2���ṩ����</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='5_1' value='' size='13' tabindex='1'>
                   <input type="hidden" name="hc" value="5"/>
                   </td>
                    <td class="2-td2-left" nowrap>17</td>
					<td class="2-td2-left-qysds1" nowrap>����Ӫҵ������ ��18��19��20��21��22��23��24��25��26��</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='17_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="17"/>
                   </td>
				  
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap>6</td>
					<td class="2-td2-left-qysds3" nowrap>��3���ö��ʲ�ʹ��Ȩ</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='6_1' value='' size='13' tabindex='1'>
                   <input type="hidden" name="hc" value="6"/>
                   </td>
				   <td class="2-td2-left" nowrap>18</td>
					<td class="2-td2-left-qysds2" nowrap>1.�̶��ʲ���ӯ</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='18_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="18"/>
                   </td>
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap>7</td>
					<td class="2-td2-left-qysds3" nowrap>��4�������ͬ</td>
                    <td class="2-td2-center" nowrap>
                    <input type='text'  name='je' id='7_1' value='' size='13' tabindex='1'>
                    <input type="hidden" name="hc" value="7"/>
                    </td>
				   <td class="2-td2-left" nowrap>19</td>
					<td class="2-td2-left-qysds2" nowrap>2.���ù̶��ʲ�������</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='19_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="19"/>
                   </td>
                  </tr>    
				  <tr> 
                    <td class="2-td2-left" nowrap>8</td>
					<td class="2-td2-left-qysds2" nowrap>2.����ҵ�����루9��10��11��12��</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='8_1' value='' size='13' tabindex='1'>
                   <input type="hidden" name="hc" value="8"/>
                   </td>
					 <td class="2-td2-left" nowrap>20</td>
					<td class="2-td2-left-qysds2" nowrap>3.�ǻ������ʲ���������</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='20_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="20"/>
                   </td>
                  </tr>  
                  <tr> 
                    <td class="2-td2-left" nowrap>9</td>
					<td class="2-td2-left-qysds3" nowrap>��1��������������</td>
                    <td class="2-td2-center" nowrap>
                    <input type='text'  name='je' id='9_1' value='' size='13' tabindex='1'>
                    <input type="hidden" name="hc" value="9"/>
                    </td>
					<td class="2-td2-left" nowrap>21</td>
					<td class="2-td2-left-qysds2" nowrap>4.���������ʲ�����</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='21_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="21"/>
                   </td>
                  </tr>    
                  <tr> 
                    <td class="2-td2-left" nowrap>10</td>
					<td class="2-td2-left-qysds3" nowrap>��2��������������������</td>
                    <td class="2-td2-center" nowrap>
                    <input type='text'  name='je' id='10_1' value='' size='13' tabindex='1'>
                    <input type="hidden" name="hc" value="10"/>
                    </td>
					<td class="2-td2-left" nowrap>22</td>
					<td class="2-td2-left-qysds2" nowrap>5.�������</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='22_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="22"/>
                   </td>
                  </tr>    
				   <tr> 
                    <td class="2-td2-left" nowrap>11</td>
					<td class="2-td2-left-qysds3" nowrap>��3����װ���������</td>
                    <td class="2-td2-center" nowrap>
                    <input type='text'  name='je' id='11_1' value='' size='13' tabindex='1'>
                    <input type="hidden" name="hc" value="11"/>
                    </td>
                    <td class="2-td2-left" nowrap>23</td>
					<td class="2-td2-left-qysds2" nowrap>6.ծ����������</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='23_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="23"/>
                   </td>
                  </tr>  
				   <tr> 
                    <td class="2-td2-left" nowrap>12</td>
					<td class="2-td2-left-qysds3" nowrap>��4������</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='12_1' value='' size='13' tabindex='1'>
                   <input type="hidden" name="hc" value="12"/>
                   </td>
                    <td class="2-td2-left" nowrap>24</td>
					<td class="2-td2-left-qysds2" nowrap>7.������������</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='24_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="24"/>
                   </td>
                  </tr>  
				   <tr> 
                    <td class="2-td2-left" nowrap>&nbsp;</td>
					<td class="2-td2-left-qysds3" nowrap>&nbsp;</td>
                   <td class="2-td2-center" nowrap>
                   &nbsp;
                   </td>
                    <td class="2-td2-left" nowrap>25</td>
					<td class="2-td2-left-qysds2" nowrap>8.��������</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='25_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="25"/>
                   </td>
                  </tr>  
				   <tr> 
                    <td class="2-td2-left" nowrap>&nbsp;</td>
					<td class="2-td2-left-qysds3" nowrap>&nbsp;</td>
                    <td class="2-td2-center" nowrap>
                    &nbsp;
                    </td>
                    <td class="2-td2-left" nowrap>26</td>
					<td class="2-td2-left-qysds2" nowrap>9.����</td>
                   <td class="2-td2-center" nowrap>
                   <input type='text'  name='je' id='26_1' value='' size='13' tabindex='2'>
                   <input type="hidden" name="hc" value="26"/>
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