<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.qrsh.model.GdzyShsbmx"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.qrsh.model.GdzyShsbmodel"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.qrsh.web.GdzysShqrForm"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
	<title>ȷ�����</title>
	<link href="../css/text.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="../js/treatImage.js"></script>
	<script language="JavaScript" src="../js/DTable.js"></script>
	<script language="JavaScript" src="../js/InputSelect.js"></script>
	<script language="JavaScript" src="../js/function.js"></script>
	<script language="javascript" src="../js/gmit_selectcontrol.js"></script>
	<script language="JavaScript" src="../js/smsb_common.js"></script>
	
<script language="JavaScript">

	//��ѯ��js,��֤4������
	function doQuery()
	{
		if(document.forms[0].nsrmc.value.length <=0 && document.forms[0].jsjdm.value.length <=0 && document.forms[0].sbbxlh.value.length <=0 && document.forms[0].zdpwh.value.length <=0)
		{
			alert("4��������������һ��");	
			return false;
		}
		else
		{
		document.forms[0].actionType.value = "Query";
		document.forms[0].submit();
		}
	}
	
	//�����б���ϸ��ѯ
	function doMxQuery(k)
	{
		document.forms[0].nsrmc.value = "";
		document.forms[0].jsjdm.value = "";
		document.forms[0].zdpwh.value = "";
		document.forms[0].sh_flag.value = "sh_flag";
		document.forms[0].sbbxlh.value =  document.getElementById("sbxlhResult"+k).innerHTML;
		document.forms[0].sfsjsp.value =  document.getElementById("sfsjsp"+k).value;
		document.forms[0].action="/smsb/webapp/smsb/sydjxxlrAction.do";
		document.forms[0].actionType.value = "Query";
		document.forms[0].submit();
	}
	
	//�������
	//function doCountyCheck()
	//{
	//	var qrshinf = [];
	//	var qrsh = document.getElementsByName("qrshno");
	//	var i;
	//	var k=0;
	//	for(i=0;i<qrsh.length;i++)
	//	{       
	//		if(qrsh[i].checked)
		//	{
		//		var sbxlh = "sbxlhResult"+i;
		//		var qrzt  = "qrztResult"+i;
		//		if(document.getElementById(qrzt).value!=10)
		//			{
		//				alert("ȷ���б����������ѡ��ȷ���б�");
		//				return false;
		//			}
		//		else{
		//				qrshinf[k] = document.getElementById(sbxlh).innerHtml;
		//				k++;
		//			}
		//	}       
			       
	//	}
	//	document.getElementById("shqrinf").value = qrshinf;
	//}
</script>
</head>


 <body bgcolor="#FFFFFF" marginwidth="0" marginheight="0" style="margin: 0">
 <!-----------------------------jsp�ﵥ����jspHeader----logo------------------------------------------------------------------------------------------------------------>
<%@include file="../../include/header.jsp"%>
<!-----------------------------------jspHeader����-------------------------------------------------------------------------------------------------------------------------->


 <table  align ="center" width="100%" height="75%" border="0" cellpadding="0" cellspacing="0" class = "black9">
<tr>
	<td valign="top"><br>
	 	<table align="center" cellspacing="0" class="table-99">
	 	
<!-- -----------------theme-------------------------------------------------------------- -->
<tr>
	<td class="1-td1" >����ռ��˰ȷ�����</td>
</tr>	
<!---------------------theme------------------------------------------------------------------------>

<!-- ---------------------------------------------------context----------------------------------------------------------------------------------------------------------------- -->
<!-------------------------------------------------------�Ȳ�ѯ------------------------------------------------------>
<tr><td class = "1-td2">		
	<html:form action="/webapp/smsb/gdzyShqrAction.do" >
	<html:hidden property="actionType"/>
	<html:hidden property="sfsjsp"/>
	<input type='hidden' id = 'sh_flag' name="sh_flag" />
			 <table width="99%" border="0" cellpadding="0" cellspacing="0">
              <tr>
              	<tr><td>&nbsp;</td></tr>
                <td width="675"> <hr width="100%" size="1" class="hr1" >
                </td>
                <td width="150" align="center" class="black9"><strong>��˲�ѯ����</strong></td>
                <td width="675"> <hr width="100%" size="1" class="hr1" >
                </td>
                <tr><td>&nbsp;</td></tr>
              </tr>
            </table>
			
		<!-- *��ѯ����  -- ¼��*-->
			<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF"  >
			  <tr>
			    <td  class="2-td2-t-left" width="20%">��˰������</td>
			    <td  class="2-td2-t-center" width="30%"><input name="nsrmc" type="text" style="width:250px" onkeydown="if(event.keyCode==13) event.keyCode=9" /></td>
			    <td  class="2-td2-t-right" width="20%">���������</td>
			    <td  class="2-td2-t-right" width="30%"><input name="jsjdm" type="text"  style="width:250px" onkeydown="if(event.keyCode==13) event.keyCode=9"/></td>
			  </tr>
			  <tr>
			    <td class="2-td2-left">��׼ռ���ĺ�</td>
			    <td class="2-td2-center"><input name="zdpwh" type="text" style="width:250px" onkeydown="if(event.keyCode==13) event.keyCode=9;"/></td>
			    <td class="2-td2-right">�걨���к�</td>
			    <td class="2-td2-right"><input name="sbbxlh" type="text" style="width:250px" onkeydown="if(event.keyCode==13) event.keyCode=9;"/></td>
			  </tr>   
			</table>

			<table width="99%" height="60" border="0">
			  <tr>
			  	<td> 
			  		<div align="center">
							<!--*��ѯ��ť*-->
			  				<img src="<%=static_contextpath%>images/chaxun1.jpg" 
								 onmouseover="this.src='<%=static_contextpath%>images/chaxun2.jpg'" 
							     onmouseout="this.src='<%=static_contextpath%>images/chaxun1.jpg'" 
							     alt="��ѯ" onclick="doQuery();" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
								 
								 <!--*�˳���ť*-->
			  				<img src="<%=static_contextpath%>images/tuichu1.jpg" 
								 onmouseover="this.src='<%=static_contextpath%>images/tuichu2.jpg'" 
								 onmouseout="this.src='<%=static_contextpath%>images/tuichu1.jpg'" 
								 alt="�˳�" onclick="tuichu();" style="cursor:hand">
					</div>
			  	</td>
			  </tr>
  			</table>
				

<!--------------------------------------------��һ�����------����鵽�����������ʾ�б�------------------------------------------->
	<%
	GdzysShqrForm gdzysShqrForm = (GdzysShqrForm)request.getAttribute("gdzysShqrForm");
	if(gdzysShqrForm.getNum()>=1)
	{
	%>
		<table width="99%" border="0" cellpadding="0" cellspacing="0">
			  <tr><td>&nbsp;</td></tr>
              <tr>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
                <td width="100" align="center" class="black9"><strong>��Ҫ��Ϣ</strong>
                </td>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
              </tr>
              <tr><td>&nbsp;</td></tr>
         </table>
         
  		<!--*��ѯ�����Ҫ*-->
  		<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
  			<tr>
  				<td class="2-td1-left"   width="10%" noWrap>�걨���к�</td>
  				<td class="2-td1-center" width="20%" noWrap>��˰������</td>
  				<td class="2-td1-right"  width="10%" noWrap>���������</td>
				<td class="2-td1-right"  width="10%" noWrap>��׼ռ���ĺ�</td>
				<td class="2-td1-right"  width="10%" noWrap>��˰���</td>
				<td class="2-td1-right"  width="10%" noWrap>�������</td>
				<td class="2-td1-right"  width="10%" noWrap>Ӧ��˰��</td>
				<td class="2-td1-right"  width="10%" noWrap>�걨״̬</td>
				<td class="2-td1-right"  width="10%" noWrap>�걨ʱ��</td>
			<!-- <td class="2-td1-right"  width="5%">ȷ�����</td>  -->	
  			</tr>
  			
  				<%
  				int k=0;
  				%>
				<logic:iterate id="gdinf" name="gdzysShqrForm" property="infList">
				<%
				GdzyShsbmodel gdzyShsbmodeltemp = (GdzyShsbmodel)(gdzysShqrForm.getInfList().get(k));
				k++; %>
  			<tr>
  				<td class="2-td2-left" noWrap><a id="sbxlhResult<%=k %>"  style="cursor:hand ;text-decoration:underline;" onclick = "doMxQuery(<%= k%>)"><bean:write name="gdinf" property="sbbxlh"/></a>
  										<input name="sfsjsp<%=k %>" type="hidden" value=<bean:write name="gdinf" property="sfsjsp"/> />
  				</td>
  				<td class="2-td2-center" noWrap><bean:write name="gdinf" property="nsrmc"/>&nbsp;</td>
  				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="jsjdm"/>&nbsp;</td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="zdpwh"/>&nbsp; </td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="jsmj"/>&nbsp; </td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="jmmj"/>&nbsp; </td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="ynse"/>&nbsp; </td>
				<td class="2-td2-right" noWrap> 
				<%if(gdzyShsbmodeltemp.getSbzt().equals("40")) {%>
  						 �о���ȷ��			
					<%}if(gdzyShsbmodeltemp.getSbzt().equals("30")) {%>
  						˰������ȷ��			
					<%}if(gdzyShsbmodeltemp.getSbzt().equals("10")){ %>	
						 �ѳ��� 
					<% }%>	
				
				</td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="cjsj"/>&nbsp;</td>
			<!-- <td class="2-td2-right"> <input type="checkbox" name="qrshno">&nbsp;</td>  -->	
  			</tr>
				</logic:iterate>
				
			
  		</table>
  			
<% }%>

	<!------------------------------------------------------��2�����-----���δ�鵽�����ʾ---------------------------------------------->
  		
  		<% if(gdzysShqrForm.getNum()==0)
  		{
  		%>
  		<table  align="center" width="99%" height = "80" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
		<tr>
			<td class = "2-td2-t-center" >
			<font size="3px" color = "red"> û�в鵽����˵�˰Դ��Ϣ��������ȷ�ϲ�ѯ���� 
			</font>
			</td>
		</tr>
		</table>
		<% }%>
			
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