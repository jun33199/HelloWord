<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.sybg.model.GdzysSybgmodel"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.sybg.web.GdzysSybgForm"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
	<title>税源变更</title>
	<link href="../css/text.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="../js/treatImage.js"></script>
	<script language="JavaScript" src="../js/DTable.js"></script>
	<script language="JavaScript" src="../js/InputSelect.js"></script>
	<script language="JavaScript" src="../js/function.js"></script>
	<script language="javascript" src="../js/gmit_selectcontrol.js"></script>
	<script language="JavaScript" src="../js/smsb_common.js"></script>
	
<script language="JavaScript">

//查询的js,验证4个条件
function doQuery()
{
	if(document.forms[0].nsrmc.value.length <=0 && document.forms[0].jsjdm.value.length <=0 && document.forms[0].sbbxlh.value.length <=0 && document.forms[0].zdpwh.value.length <=0)
	{
		alert("4个条件最少输入一个");	
		return false;
	}
	else
	{
	document.forms[0].actionType.value = "Query";
	document.forms[0].submit();
	}
}
//选择一条变更
function doBianGeng(a)
{
	// var bg = document.getElementsByName("bg");                                  
    // var a;
    // var p = false;
    // for(a=1; a<=bg.length; a++)
    // {
     //    if(bg[a-1].checked)
     //   {
       // 	 p = true;
        	 if(document.getElementById("rk"+a).value == "1")
       		 {
        		 	alert("税款已入库，请走退税流程");
        		 	return false;
        	 }
        	 if(document.getElementById("jk"+a).value == "1")
        		 {
        		 	alert("请先撤销缴款书，再进行税源变更");
        		 	return false;
        		 }
         	if(document.getElementById("jm"+a).value == "1")
         		{
       			alert("请先撤销减免税证明，再进行税源变更");
         			return false;
         		}
         	 document.forms[0].nsrmc.value = "";
    		 document.forms[0].jsjdm.value = "";
    		 document.forms[0].zdpwh.value = "";
        	 document.forms[0].sbbxlh.value =  document.getElementById("sbbxlhResult"+a).innerHTML;
        	 document.forms[0].action="/smsb/webapp/smsb/sydjxxlrAction.do";
     		 document.forms[0].actionType.value = "Bgcx";
    		 document.forms[0].submit();
        	 
        // } 
    // }

     //if(p==false)
    	// {
    	// 	alert("请先选择一条再进行变更");
    	// }
}


//======================kanght

//选择一条变更
function doBG(val)
{
  	 document.forms[0].sbbxlh.value = val;
  	 document.forms[0].bg_flag.value="bg_flag";
  	 document.forms[0].action="/smsb/webapp/smsb/sydjxxlrAction.do";
	 document.forms[0].actionType.value = "Query";
	 document.forms[0].submit();
}









</script>
</head>


 <body bgcolor="#FFFFFF" marginwidth="0" marginheight="0" style="margin: 0">
 <!-----------------------------jsp里单独的jspHeader----logo------------------------------------------------------------------------------------------------------------>
<%@include file="../../include/header.jsp"%>
<!-----------------------------------jspHeader结束-------------------------------------------------------------------------------------------------------------------------->


 <table  align ="center" width="100%" height="75%" border="0" cellpadding="0" cellspacing="0" class = "black9">
<tr>
	<td valign="top"><br>
	 	<table align="center" cellspacing="0" class="table-99">
	 	
<!-- -----------------theme-------------------------------------------------------------- -->
<tr>
	<td class="1-td1" >耕地占用税税源变更</td>
</tr>	
<!---------------------theme------------------------------------------------------------------------>

<!-- ---------------------------------------------------context----------------------------------------------------------------------------------------------------------------- -->
<!-------------------------------------------------------先查询------------------------------------------------------>
<tr><td class = "1-td2">		
	<html:form action="/webapp/smsb/gdzySybgAction.do" >
	<html:hidden property="actionType"/>
	<input type="hidden" name='bg_flag' />

			 <table width="99%" border="0" cellpadding="0" cellspacing="0">
              <tr>
              	<tr><td>&nbsp;</td></tr>
                <td width="650"> <hr width="100%" size="1" class="hr1" >
                </td>
                <td width="200" align="center" class="black9"><strong>税源变更查询条件</strong></td>
                <td width="650"> <hr width="100%" size="1" class="hr1" >
                </td>
                <tr><td>&nbsp;</td></tr>
              </tr>
            </table>
			
		<!-- *查询条件  -- 录入*-->
			<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF"  >
			  <tr>
			    <td  class="2-td2-t-left" width="20%">纳税人名称</td>
			    <td  class="2-td2-t-center" width="30%"><input name="nsrmc" type="text" style="width:250px" onkeydown="if(event.keyCode==13) event.keyCode=9" /></td>
			    <td  class="2-td2-t-right" width="20%">计算机代码</td>
			    <td  class="2-td2-t-right" width="30%"><input name="jsjdm" type="text"  style="width:250px" onkeydown="if(event.keyCode==13) event.keyCode=9" /></td>
			  </tr>
			  <tr>
			    <td class="2-td2-left">批准占地文号</td>
			    <td class="2-td2-center"><input name="zdpwh" type="text" style="width:250px" onkeydown="if(event.keyCode==13) event.keyCode=9;" /></td>
			    <td class="2-td2-right">申报序列号</td>
			    <td class="2-td2-right"><input name="sbbxlh" type="text" style="width:250px" onkeydown="if(event.keyCode==13) event.keyCode=9;" /></td>
			  </tr>   
			</table>

			<table width="99%" height="60" border="0">
			  <tr>
			  	<td> 
			  		<div align="center">
							<!--*查询按钮*-->
			  				<img src="<%=static_contextpath%>images/chaxun1.jpg" 
								 onmouseover="this.src='<%=static_contextpath%>images/chaxun2.jpg'" 
							     onmouseout="this.src='<%=static_contextpath%>images/chaxun1.jpg'" 
							     alt="查询" onclick="doQuery();" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
								 
								 <!--*退出按钮*-->
			  				<img src="<%=static_contextpath%>images/tuichu1.jpg" 
								 onmouseover="this.src='<%=static_contextpath%>images/tuichu2.jpg'" 
								 onmouseout="this.src='<%=static_contextpath%>images/tuichu1.jpg'" 
								 alt="退出" onclick="tuichu();" style="cursor:hand">
					</div>
			  	</td>
			  </tr>
  			</table>
				

<!--------------------------------------------第一种情况------如果查到多条结果就显示列表------------------------------------------->
	<%
	GdzysSybgForm gdzysSybgForm = (GdzysSybgForm)request.getAttribute("gdzysSybgForm");
	if(gdzysSybgForm.getNum()>=1)
	{
	%>
		<table width="99%" border="0" cellpadding="0" cellspacing="0">
			  <tr><td>&nbsp;</td></tr>
              <tr>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
                <td width="100" align="center" class="black9"><strong>概要信息</strong>
                </td>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
              </tr>
              <tr><td>&nbsp;</td></tr>
         </table>
         
  		<!--*查询结果概要*-->
  		<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
  			<tr>
  				<td class="2-td1-left"   width ="10%" noWrap>申报序列号</td>
  				<td class="2-td1-center" width ="20%" noWrap>纳税人名称</td>
  				<td class="2-td1-right"  width ="10%" noWrap>计算机代码</td>
				<td class="2-td1-right"  width ="10%" noWrap>批准占地文号</td>
				<td class="2-td1-right"  width ="10%" noWrap>计税面积</td>
				<td class="2-td1-right"  width ="10%" noWrap>减免面积</td>
				<td class="2-td1-right"  width ="10%" noWrap>应纳税额</td>
				<td class="2-td1-right"  width ="10%" noWrap>申报状态</td>
				<td class="2-td1-right"  width ="10%" noWrap>申报时间</td>
  			</tr>
  			
  				<%
  				int k=0;
  				%>
				<logic:iterate id="gdinf" name="gdzysSybgForm" property="infList">
				<%
				GdzysSybgmodel gdzysSybgmodeltemp = (GdzysSybgmodel)(gdzysSybgForm.getInfList().get(k));
				k++; %>
  			<tr>

<td class="2-td2-left" noWrap><a style="cursor:hand;text-decoration:underline;" onclick=doBG('<bean:write name="gdinf" property="sbbxlh"/>');><bean:write name="gdinf" property="sbbxlh"/></a>&nbsp;

  				</td>
  				<td class="2-td2-center" noWrap><bean:write name="gdinf" property="nsrmc"/>&nbsp;</td>
  				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="jsjdm"/>&nbsp;</td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="zdpwh"/>&nbsp; </td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="jsmj"/>&nbsp; </td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="jmmj"/>&nbsp; </td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="ynse"/>&nbsp; </td>		
   				<td class="2-td2-right" noWrap>
					<%if(gdzysSybgmodeltemp.getSbzt().equals("40")) {%>
  						 市局已确认			
					<%}if(gdzysSybgmodeltemp.getSbzt().equals("30")) {%>
  						税务所已确认			
					<%}if(gdzysSybgmodeltemp.getSbzt().equals("10")){ %>	
						 已初存 
					<% }%>	
				</td>								
				<td class="2-td2-right"> <bean:write name="gdinf" property="cjsj"/>&nbsp;</td>
  			</tr>
				</logic:iterate>		
  		</table>

<% }%>

	<!------------------------------------------------------第2种情况-----如果未查到结果提示---------------------------------------------->
  		
  		<% if(gdzysSybgForm.getNum()==0)
  		{
  		%>
  		<table  align="center" width="99%" height = "80" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
		<tr>
			<td class = "2-td2-t-center" >
			<font size="3px" color = "red"> 没有查到税源信息，请重新确认查询条件 
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