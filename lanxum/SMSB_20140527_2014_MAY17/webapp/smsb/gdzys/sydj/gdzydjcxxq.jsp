<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.model.Gdzydjcxmodel"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.model.Gdzydj_sbmx"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.web.GdzysSydjcxForm"%>

<html:html>
<head>
	<title>����ռ��˰�Ǽǲ�ѯ</title>
	<link href="../css/text.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="../js/treatImage.js"></script>
	<script language="JavaScript" src="../js/DTable.js"></script>
	<script language="JavaScript" src="../js/InputSelect.js"></script>
	<script language="JavaScript" src="../js/function.js"></script>
	<script language="javascript" src="../js/gmit_selectcontrol.js"></script>
	<script language="JavaScript" src="../js/smsb_common.js"></script>
	<script language="JavaScript">

		//����
		//function doBack()
		//{
			//document.forms[0].action = "/webapp/smsb/gdzydjcxAction.do";
			//document.forms[0].actionType.value = "Show";
			//document.forms[0].submit();
		//}
	</script>
</head>

 <body bgcolor="#FFFFFF" marginwidth="0" marginheight="0" style="margin: 0">

 <!-----------------------------jsp�ﵥ����jspHeader----logo------------------------------------------------------------------------------------------------------------>
<%@include file="../../include/header.jsp"%>
<!-----------------------------------jspHeader����-------------------------------------------------------------------------------------------------------------------------->


 <table  align ="center" width="100%" height="61%" border="0" cellpadding="0" cellspacing="0" class = "black9">
<tr>
	<td valign="top"><br>
	 	<table align="center" cellspacing="0" class="table-99">
<!-- -----------------theme------------------------------------------------------------------------------------------------------------------------------------------------- -->
<tr>
	<td class="1-td1" >˰Դ�Ǽ���ϸ��Ϣ</td>
</tr>	
<!---------------------theme---------------------------------------------------------------------------------------------------------------------------------------------------->

<!-- ---------------------------------------------------context----------------------------------------------------------------------------------------------------------------- -->
<tr><td class = "1-td2">		
		
	<html:form action="/webapp/smsb/gdzydjcxAction.do">
	<html:hidden property="actionType"/>
	<%
	GdzysSydjcxForm sydjcx_Form = (GdzysSydjcxForm)request.getAttribute("gdzysSydjcxForm");
	Gdzydjcxmodel gdzydjcxmodel = (Gdzydjcxmodel)(sydjcx_Form.getInfList().get(0));
	%>
	
	<logic:iterate id="gdinf" name="gdzysSydjcxForm" property="infList">
																
		<!--*��ѯ�������*-->
		<table width="99%" border="0" cellpadding="0" cellspacing="0">
			  <tr><td>&nbsp;</td></tr>
              <tr>
                <td width="800"> <hr width="100%" size="1" class="hr1" >
                </td>
                <td width="100" align="center" class="black9"><strong>��ϸ��Ϣ</strong></td>
                <td width="800"> <hr width="100%" size="1" class="hr1" >
                </td>
              </tr>
              <tr><td>&nbsp;</td></tr>
         </table>

			<!--*������Ϣ*-->
		<table width="99%"><tr><td align="left"><FONT color=#000000 size=2><strong>&nbsp;������Ϣ:</strong></FONT></td></tr></table>
  		<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
  		
  			<tr>
  				<td class="2-td2-t-left"   width="20%" noWrap>�걨���к�</td>
  				<td class="2-td2-t-center" width="30%" noWrap>
  					<bean:write name="gdinf" property="sbxlh"/>&nbsp;
  				</td>
  				<td class="2-td2-t-right"  width="20%" noWrap>��˰������</td>
				<td class="2-td2-t-right"  width="30%" noWrap>
							<%if(gdzydjcxmodel.getNsrlx().equals("0")) {%>
			  					��λ
			  					<% }%>
			  				<%if(gdzydjcxmodel.getNsrlx().equals("1")) {%>
			  					����
			  					<% }%>&nbsp;
				</td>		
  			</tr>
  			
  			<tr>
  				<td class="2-td2-left"    noWrap>˰Դ����</td>
				<td class="2-td2-center"  noWrap>
					<bean:write name="gdinf" property="sylx"/>&nbsp;
				</td>
				<td class="2-td2-right"  noWrap>���������</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="gdinf" property="jsjdm"/>&nbsp;
				</td>
  			</tr>
  			
  			
  			<tr>
  				<td class="2-td2-left"   noWrap>��˰������</td>
  				<td class="2-td2-center" noWrap>
  					<bean:write name="gdinf" property="nsrmc"/>&nbsp;
  				</td>
  				<td class="2-td2-right" id="nsrsbhname" noWrap>��˰��ʶ���</td>
  				<td class="2-td2-right" id="nsrsbhcontext" noWrap>
  					<%if(gdzydjcxmodel.getNsrlx().equals("0")) {%>
			  			<bean:write name="gdinf" property="nsrsbh"/>&nbsp;
			  		<% }else{%>
			  			&nbsp;
  					<%} %>					
  				</td>		
  			</tr>
  			
  			<%if(gdzydjcxmodel.getNsrlx().equals("0")) {%>
  			<tr>
  				<td class="2-td2-left"  noWrap>��˰��������ҵ</td>
				<td class="2-td2-center"  noWrap>
					<bean:write name="gdinf" property="nsrsshy"/>&nbsp;
				</td>
				<td class="2-td2-right"  noWrap>��ҵ�Ǽ�ע������</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="gdinf" property="qydjzclx"/>&nbsp;
				</td>
  			</tr>
  			
  			<tr>
  				<td class="2-td2-left"   noWrap>��������</td>
  				<td class="2-td2-center" noWrap>
  					<bean:write name="gdinf" property="khyh"/>&nbsp;
  				</td>
  				<td class="2-td2-right"  noWrap>�����˺�</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="gdinf" property="yhzh"/>&nbsp;
				</td>		
  			</tr>
  			<% }%>
  			
  			<tr>
  				<td class="2-td2-left"   noWrap>��ϵ������</td>
  				<td class="2-td2-center" noWrap>
  					<bean:write name="gdinf" property="lxrxm"/>&nbsp;
  				</td>
  				<td class="2-td2-right"  noWrap>��ϵ�绰</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="gdinf" property="lxdh"/>&nbsp;
				</td>		
  			</tr>
  			
  			<%if(gdzydjcxmodel.getNsrlx().equals("1")) {%>
  			<tr>
  				<td class="2-td2-left"   noWrap>���֤�պ���</td>
  				<td class="2-td2-center" noWrap>
  					<bean:write name="gdinf" property="sfzzhm"/>&nbsp;
  				</td>
  				<td class="2-td2-right"  noWrap>���֤������</td>
				<td class="2-td2-right"  noWrap>
<%
					int sfzzlx_dm = Integer.parseInt(gdzydjcxmodel.getSfzzlx());
					switch(sfzzlx_dm)
					{
						case 02:	%>���֤ 		<%; break;						
					 	case 03:  %>����֤��		<%; break;
						case 04:	%>����        		<%; break;
				    	case 05:	%>�۰�ͬ������֤	<%; break; 	
				    	case 90:  %>����			<%; break;
				    	default:
					}
					
%>				
					
				</td>		
  			</tr>
  			<%} %>
  			
  			<tr>
  				<td class="2-td2-left"   noWrap>��˰����ϸ��ַ</td>
  				<td class="2-td2-center" colspan=3 noWrap>
  					<bean:write name="gdinf" property="nsrxxdz"/>&nbsp;
  				</td>
  			<tr>
						
  		</table>
			
		<!--*������Ϣ*-->
		<table><tr><td>&nbsp;</td></tr></table>
		<table width="99%"><tr><td align="left"><FONT color=#000000 size=2><strong>&nbsp;������Ϣ:</strong></FONT></td></tr></table>
		<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
		
			<tr>
				<td class="2-td2-t-left"   width="20%" noWrap>��׼ռ���ĺ�</td>
				<td class="2-td2-t-center" width="30%" noWrap> 
					<bean:write name="gdinf" property="zdpwh"/>&nbsp;
				</td>
  				<td class="2-td2-t-right"    width="20%" noWrap>��׼ռ�����������ũת�������ƽ���ף�</td>
  				<td class="2-td2-t-right"    width="30%" noWrap>
  					<bean:write name="gdinf" property="pzzdmj"/>&nbsp;
  				</td>		
  			</tr>
  			
			<tr>
  				<td class="2-td2-left"   noWrap>ʵ��ռ����������˰���һ�£�ƽ���ף�</td>
  				<td class="2-td2-center" noWrap>
  					<bean:write name="gdinf" property="sjzdmj"/>&nbsp;
  				</td>
  				<td class="2-td2-right"  noWrap>��׼ʱ��/ʵ��ռ��ʱ��</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="gdinf" property="zdsj"/>&nbsp;
				</td>		
  			</tr>
  			
  			<tr>
  				<td class="2-td2-left"   colspan=1 noWrap>���������ַ</td>
  				<td class="2-td2-center" colspan=3 noWrap>
  					<bean:write name="gdinf" property="tdzldz"/>&nbsp;
  				</td>
  			</tr>
  			<tr>
  				<td class="2-td2-left"   colspan=1 noWrap>������Ŀ����</td>
				<td class="2-td2-center" colspan=3 noWrap>
					<bean:write name="gdinf" property="jsxmmc"/>&nbsp;
				</td>		
  			</tr>
			
			</table>

			<!--*��������*-->
			<table> <tr><td heigth="15px"/></tr><table>
			<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
			<tr>
  				<td class="2-td1-left"   width="4%" noWrap>���</td>
  				<td class="2-td1-center" width="12%" noWrap>ռ����;</td>
  				<td class="2-td1-right"  width="12%" noWrap>����˰�Ԫ/ƽ���ף�</td>
				<td class="2-td1-right"  width="12%" noWrap>��˰�����ƽ���ף�</td>
				<td class="2-td1-right"  width="12%" noWrap>����˰�Ԫ��</td>
				<td class="2-td1-right"  width="12%" noWrap>���������ƽ���ף�</td>
				<td class="2-td1-right"  width="12%" noWrap>����˰�Ԫ��</td>
				<td class="2-td1-right"  width="12%" noWrap>Ӧ˰�����ƽ���ף�</td>
				<td class="2-td1-right"  width="12%" noWrap>Ӧ��˰�Ԫ��</td>
  			</tr>
  			
  				<%int i=0; %>																			<!-- ��� -->
  					
  																										<!-- �ϼ� -->
				<logic:iterate id="tdxq" name="gdinf" property="sbmx">									<!-- ѭ����ʾ������Ϣ -->
			<tr>
				<%i++;%>
  				<td class="2-td2-left"   noWrap><%=i%></td>
  				<td class="2-td2-center" noWrap>
  					<bean:write name="tdxq" property="zdyt"/>&nbsp;
  				</td>
  				<td class="2-td2-right"  noWrap>
  					<bean:write name="tdxq" property="syse"/>
  				</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="tdxq" property="jsmj"/>&nbsp;
				</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="tdxq" property="jzse"/>&nbsp;
				</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="tdxq" property="jmmj"/>&nbsp;
				</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="tdxq" property="jmse"/>&nbsp;
				</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="tdxq" property="ysmj"/>&nbsp;
				</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="tdxq" property="ynse"/>&nbsp;
				</td>
  			</tr>
				</logic:iterate>
			<tr>
  				<td class="2-td2-left" >�ϼ�</td>
  				<td class="2-td2-center">&nbsp;</td>
  				<td class="2-td2-right"> &nbsp;</td>
				<td class="2-td2-right"><bean:write name="gdinf" property="jsmj"/>&nbsp;</td>
				<td class="2-td2-right"><bean:write name="gdinf" property="jzse"/>&nbsp;</td>
				<td class="2-td2-right"><bean:write name="gdinf" property="jmmj"/>&nbsp;</td>
				<td class="2-td2-right"><bean:write name="gdinf" property="jmse"/>&nbsp;</td>
				<td class="2-td2-right"><bean:write name="gdinf" property="ysmj"/>&nbsp;</td>
				<td class="2-td2-right"><bean:write name="gdinf" property="ynse"/>&nbsp;</td>
  			</tr>
  			</table>

			<!--*��������*-->
			<% if(gdzydjcxmodel.getJmxx()!=null )
			{
			%>
			<table><tr><td>&nbsp;</td></tr></table>
			<table width="99%"><tr><td align="left"><FONT color=#000000 size=2><strong>&nbsp;������Ϣ:</strong></FONT></td></tr></table>
			<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
			<tr>
				<td class="2-td1-left"   width="25%"  noWrap>���</td>
				<td class="2-td1-center" width="25%"  noWrap>�������</td>
				<td class="2-td1-right"  width="25%"  noWrap>���������ƽ���ף�</td>
				<td class="2-td1-right"  width="25%"  noWrap>�������˰�Ԫ��</td>
			</tr>
			
				<%int j=0; %>
				<logic:iterate id="jmxq" name="gdinf" property="jmxx">
				
			<tr>
				<%j++;%>
  				<td class="2-td2-left"   noWrap><%=j %></td>
  				<td class="2-td2-center" noWrap>
  					<bean:write name="jmxq" property="jmslb"/>&nbsp;
  				</td>
  				<td class="2-td2-right"  noWrap>
  					<bean:write name="jmxq" property="jmmj"/>&nbsp;
  				</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="jmxq" property="jmse"/>&nbsp;
				</td>
  			</tr>
  			
				</logic:iterate>
			</table>
			
			<!--*��������*-->
			<table><tr><td>&nbsp;</td></tr></table>
			<table width="99%"><tr><td align="left"><FONT color=#000000 size=2><strong>&nbsp;�걨��������:</strong></FONT></td></tr></table>
			<table align="center" width="99%" height = 90 border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
			<tr>
				<td class =2-td2-t-center width="100%">
            		<textarea   style="width:99%;text-align:left " rows="5" disabled><bean:write name="gdinf" property="jmly"/>&nbsp;
            		</textarea>
				</tr>
			</tr>
			</table>
			<%} %>
			
			<!--*��ע*-->
			<table><tr><td>&nbsp;</td></tr></table>
			<table width="99%"><tr><td align="left"><FONT color=#000000 size=2><strong>&nbsp;��ע:</strong></FONT></td></tr></table>
			<table align="center" width="99%" height = 90 border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
			<tr>
				<td class =2-td2-t-center width="100%">
            		<textarea   style="width:99%;text-align:left " rows="5" disabled><bean:write name="gdinf" property="bz"/>&nbsp;
            		</textarea>
				</tr>
			</tr>
			</table>
			
				
			<!--*ȷ����Ϣ*-->
			<table><tr><td>&nbsp;</td></tr></table>
			<table width="99%"><tr><td align="left"><FONT color=#000000 size=2><strong>&nbsp;ȷ����Ϣ:</strong></FONT></td></tr></table>
			
								<!-- *�Ƿ��о����* -->
			<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
			<tr>
				<td class =2-td2-t-left noWrap colspan=2>
            		�Ƿ��о����
				</td>
				<td class =2-td2-t-center noWrap colspan=2>
						<%if(gdzydjcxmodel.getSfsjsp().equals("0")) {%>
		  					��
		  				<% }%>
		  				<%if(gdzydjcxmodel.getSfsjsp().equals("1")) {%>
		  					��
		  				<% }%>
				</td>
			</tr>
			<tr>
				<td class=2-td2-left  noWrap colspan=2>���״̬
				</td>
	            <td class=2-td2-center noWrap colspan=2>
	                  <%
	                if(gdzydjcxmodel.getSbzt().equals("10")){
	                	%>
	                	δ���
	                	<%
	                	}else if(gdzydjcxmodel.getSbzt().equals("30")){
	                	%>
	                	���������
	                	<%
	                	}else{
	                		%>
	                		�о������
	                		<%
	                	}
	                %>     
	              </td>
			</tr>
			<tr>
                <TD class=2-td2-left noWrap width="25%">������</TD>
                <TD class=2-td2-left noWrap width="25%"> <bean:write name="gdinf" property="cjr"/>&nbsp; </TD>
                <TD class=2-td2-left noWrap width="25%">����ʱ��</TD>
                <TD class=2-td2-center noWrap width="25%"> <bean:write name="gdinf" property="cjsj"/>&nbsp;</TD>
             </tr>
             <tr>
                <TD class=2-td2-left noWrap width="25%">���������</TD>
                <TD class=2-td2-left noWrap width="25%"> <bean:write name="gdinf" property="qrr"/>&nbsp; </TD>
                <TD class=2-td2-left noWrap width="25%">���ʱ��</TD>
                <TD class=2-td2-center noWrap width="25%"> <bean:write name="gdinf" property="qrsj"/>&nbsp;</TD>
             </tr>
                <%
                	if(gdzydjcxmodel.getSfsjsp().equals("1"))
                	{
                %>
             <tr class=black9>
                <TD class=2-td2-left noWrap>�о������</TD>
                <td class=2-td2-left noWrap>	<bean:write name="gdinf" property="sjqrr"/>&nbsp; </td>
                <TD class=2-td2-left noWrap >�о����ʱ��</TD>
                <TD class=2-td2-center noWrap > <bean:write name="gdinf" property="sjqrsj"/>&nbsp; </TD>
             </tr>
                <%
                	}
                %>
			</table>
			
			

		<!--*��ť*-->
  		<table width="99%" height="60" border="0">
			  <tr>
			  	<td> 
			  		<div align="center">
			  								
							<img src="<%=static_contextpath%>images/fanhui1.jpg" 
							onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" 
							onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'"
							alt="����" onclick="javascript:history.back(-1);" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
							
							<img src="<%=static_contextpath%>images/tuichu1.jpg" 
							onmouseover="this.src='<%=static_contextpath%>images/tuichu2.jpg'" 
							onmouseout="this.src='<%=static_contextpath%>images/tuichu1.jpg'"
							alt="�˳�" onclick="tuichu();" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
			  	</td>
			  </tr>
  		</table>
  		
  		</logic:iterate>
	
	</html:form>
</td></tr>
  <!-- ---------------------------------------------------context------------------------------------------------ -->
  		</table>
  	</td>
</tr>

<!-------------------------------------------------------------bottom-jsp:include------------------------------------------------------------------>
<tr>
	<td>
		<%@ include file="../../include/footernew.jsp"%>
	</td>
</tr>
<!-------------------------------------------------------------------bottom------------------------------------------------------------->

</table> 

 </body>
 </html:html>