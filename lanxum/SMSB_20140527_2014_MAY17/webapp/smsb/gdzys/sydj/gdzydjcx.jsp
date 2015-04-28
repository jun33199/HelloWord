<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.web.GdzysSydjcxForm"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.model.Gdzydjcxmodel"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.model.Gdzydj_sbmx"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
	<title>耕地占用税登记查询</title>
	<link href="../css/text.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="../js/treatImage.js"></script>
	<script language="JavaScript" src="../js/DTable.js"></script>
	<script language="JavaScript" src="../js/InputSelect.js"></script>
	<script language="JavaScript" src="../js/function.js"></script>
	<script language="javascript" src="../js/gmit_selectcontrol.js"></script>
	<script language="JavaScript" src="../js/smsb_common.js"></script>
	
<script language="JavaScript">

	//新建
	function doNewCreate()
	{
		document.forms[0].action="/smsb/webapp/smsb/sydjxxlrAction.do";
		document.forms[0].actionType.value = "Init";
		document.forms[0].submit();
	}
	

	//查询的js,验证4个条件
	function doQuery()
	{
		if(document.forms[0].nsrmc.value.length <=0 && document.forms[0].jsjdm.value.length <=0 && document.forms[0].sbxlh.value.length <=0 && document.forms[0].zdpwh.value.length <=0)
		{
			alert("4个条件最少输入一个");	
			return false;
		}
		else
		{
		document.forms[0].action="/smsb/webapp/smsb/gdzydjcxAction.do";
		document.forms[0].actionType.value = "Query";
		document.forms[0].submit();
		}
	}
	
	//多条列表明细查询
	function doMxQuery(obj)
	{
		document.forms[0].nsrmc.value = "";
		document.forms[0].jsjdm.value = "";
		document.forms[0].zdpwh.value = "";
		document.forms[0].action="/smsb/webapp/smsb/gdzydjcxAction.do";
		document.forms[0].sbxlh.value =  document.getElementById(obj.id).innerHTML;
		document.forms[0].actionType.value = "QueryDetail";
		document.forms[0].submit();
		//document.forms[0].sbxlh.value="";
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
	<td class="1-td1" >税源登记查询</td>
</tr>	
<!---------------------theme------------------------------------------------------------------------>

<!-- ---------------------------------------------------context----------------------------------------------------------------------------------------------------------------- -->
<!-------------------------------------------------------先查询------------------------------------------------------>
<tr><td class = "1-td2">		
	<html:form action="/webapp/smsb/gdzydjcxAction.do" >
	<html:hidden property="actionType"/>


			 <table width="99%" border="0" cellpadding="0" cellspacing="0">
              <tr>
              	<tr><td>&nbsp;</td></tr>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
                <td width="100" align="center" class="black9"><strong>查询条件</strong></td>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
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
			    <td  class="2-td2-t-right" width="30%"><input name="jsjdm" type="text"  style="width:250px" onkeydown="if(event.keyCode==13) event.keyCode=9"/></td>
			  </tr>
			  <tr>
			    <td class="2-td2-left">批准占地文号</td>
			    <td class="2-td2-center"><input name="zdpwh" type="text" style="width:250px" onkeydown="if(event.keyCode==13) event.keyCode=9;"/></td>
			    <td class="2-td2-right">申报序列号</td>
			    <td class="2-td2-right"><input name="sbxlh" type="text" style="width:250px" onkeydown="if(event.keyCode==13) event.keyCode=9;"/></td>
			  </tr>   
			</table>

			<table width="99%" height="60" border="0">
			  <tr>
			  	<td> 
			  		<div align="center">
			  				<!-- 新建按钮 -->
			  				<img src="<%=static_contextpath%>images/gdzys/gdzys_xjsydj1.jpg" 
								 onmouseover="this.src='<%=static_contextpath%>images/gdzys/gdzys_xjsydj2.jpg'" 
							     onmouseout="this.src='<%=static_contextpath%>images/gdzys/gdzys_xjsydj1.jpg'" 
							     alt="新建税源登记" onclick="doNewCreate();" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
			  		
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
	GdzysSydjcxForm sydjcx_Form = (GdzysSydjcxForm)request.getAttribute("gdzysSydjcxForm");
	if(sydjcx_Form.getNum()>1)
	{
	%>
		<table width="99%" border="0" cellpadding="0" cellspacing="0">
			  <tr><td>&nbsp;</td></tr>
              <tr>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
                <td width="100" align="center" class="black9"><strong>概要信息</strong></td>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
              </tr>
              <tr><td>&nbsp;</td></tr>
         </table>
         
  		<!--*查询结果概要*-->
  		<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
  			<tr>
  				<td class="2-td1-left"   width="10%" noWrap>申报序列号</td>
  				<td class="2-td1-center" width="20%" noWrap>纳税人名称</td>
  				<td class="2-td1-right"  width="10%" noWrap>计算机代码</td>
				<td class="2-td1-right"  width="10%" noWrap>批准占地文号</td>
				<td class="2-td1-right"  width="10%" noWrap>计税面积</td>
				<td class="2-td1-right"  width="10%" noWrap>减免面积</td>
				<td class="2-td1-right"  width="10%" noWrap>应纳税额</td>
				<td class="2-td1-right"  width="10%" noWrap>申报状态</td>
				<td class="2-td1-right"  width="10%" noWrap>申报时间</td>
  			</tr>
  			
  				<%
  				int k=0;
  				%>
				<logic:iterate id="gdinf" name="gdzysSydjcxForm" property="infList">
				<%
				Gdzydjcxmodel gdzydjcxmodeltemp = (Gdzydjcxmodel)(sydjcx_Form.getInfList().get(k));
				k++; %>
  			<tr>
  				<td class="2-td2-left" noWrap><a id=sbxlhResult<%=k %>  style="cursor:hand ;text-decoration:underline;" onclick = "doMxQuery(this)"><bean:write name="gdinf" property="sbxlh"/></a></td>
  				<td class="2-td2-center" noWrap><bean:write name="gdinf" property="nsrmc"/>&nbsp;</td>
  				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="jsjdm"/>&nbsp;</td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="zdpwh"/>&nbsp;</td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="jsmj"/>&nbsp;</td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="jmmj"/>&nbsp;</td>
				<td class="2-td2-right" noWrap> <bean:write name="gdinf" property="ynse"/>&nbsp;</td>
				
				<td class="2-td2-right"> 

  					<%if(gdzydjcxmodeltemp.getSbzt().equals("40")) {%>
  						 市局已确认			
					<%}if(gdzydjcxmodeltemp.getSbzt().equals("30")) {%>
  						税务所已确认			
					<%}if(gdzydjcxmodeltemp.getSbzt().equals("10")){ %>	
						 已初存 
					<% }%>	
		  	 
				&nbsp;
				</td>
				<td class="2-td2-right"> <bean:write name="gdinf" property="cjsj"/>&nbsp;</td>
  			</tr>
				</logic:iterate>
  		</table>
<% }%>
<!-----------------------------------------第二种情况------如果查到1条就显示详细信息------------------------------------>
<% 
if(sydjcx_Form.getNum()==1)	
{
	Gdzydjcxmodel gdzydjcxmodel = (Gdzydjcxmodel)(sydjcx_Form.getInfList().get(0));
	
%>

		<logic:iterate id="gdinf" name="gdzysSydjcxForm" property="infList">
																
		<!--*查询结果详情*-->
		<table width="99%" border="0" cellpadding="0" cellspacing="0">
			  <tr><td>&nbsp;</td></tr>
              <tr>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
                <td width="100" align="center" class="black9"><strong>详细信息</strong></td>
                <td width="700"> <hr width="100%" size="1" class="hr1" >
                </td>
              </tr>
              <tr><td>&nbsp;</td></tr>
         </table>

			<!--*基本信息*-->
		<table width="99%"><tr><td align="left"><FONT color=#000000 size=2><strong>&nbsp;基本信息:</strong></FONT></td></tr></table>
  		<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
  		
  			<tr>
  				<td class="2-td2-t-left"   width="20%" noWrap>申报序列号</td>
  				<td class="2-td2-t-center" width="30%" noWrap>
  					<bean:write name="gdinf" property="sbxlh"/>&nbsp;
  				</td>
  				<td class="2-td2-t-right"  width="20%" noWrap>纳税人类型</td>
				<td class="2-td2-t-right"  width="30%" noWrap>
							<%if(gdzydjcxmodel.getNsrlx().equals("0")) {%>
			  					单位
			  					<% }%>
			  				<%if(gdzydjcxmodel.getNsrlx().equals("1")) {%>
			  					个人
			  					<% }%>&nbsp;
				</td>		
  			</tr>
  			
  			<tr>
  				<td class="2-td2-left"  noWrap>税源类型</td>
				<td class="2-td2-center"  noWrap>
					<bean:write name="gdinf" property="sylx"/>&nbsp;
				</td>
				<td class="2-td2-right"  noWrap>计算机代码</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="gdinf" property="jsjdm"/>&nbsp;
				</td>
  			</tr>
  			
  			
  			<tr>
  				<td class="2-td2-left"   noWrap>纳税人名称</td>
  				<td class="2-td2-center" noWrap>
  					<bean:write name="gdinf" property="nsrmc"/>&nbsp;
  				</td>
  				<td class="2-td2-right" id="nsrsbhname" noWrap>纳税人识别号</td>
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
  				<td class="2-td2-left"  noWrap>纳税人所属行业</td>
				<td class="2-td2-center"  noWrap>
					<bean:write name="gdinf" property="nsrsshy"/>&nbsp;
				</td>
				<td class="2-td2-right" noWrap >企业登记注册类型</td>
				<td class="2-td2-right" noWrap >
					<bean:write name="gdinf" property="qydjzclx"/>&nbsp;
				</td>
  			</tr>
  			
  			<tr>
  				<td class="2-td2-left"   noWrap>开户银行</td>
  				<td class="2-td2-center" noWrap>
  					<bean:write name="gdinf" property="khyh"/>&nbsp;
  				</td>
  				<td class="2-td2-right"  noWrap>银行账号</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="gdinf" property="yhzh"/>&nbsp;
				</td>		
  			</tr>
  			<% }%>
  			
  			<tr>
  				<td class="2-td2-left"   noWrap>联系人姓名</td>
  				<td class="2-td2-center" noWrap>
  					<bean:write name="gdinf" property="lxrxm"/>&nbsp;
  				</td>
  				<td class="2-td2-right"  noWrap>联系电话</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="gdinf" property="lxdh"/>&nbsp;
				</td>		
  			</tr>
  			
  			<%if(gdzydjcxmodel.getNsrlx().equals("1")) {%>
  			<tr>
  				<td class="2-td2-left"   noWrap>身份证照号码</td>
  				<td class="2-td2-center" noWrap>
  					<bean:write name="gdinf" property="sfzzhm"/>&nbsp;
  				</td>
  				<td class="2-td2-right"  noWrap>身份证照类型</td>
				<td class="2-td2-right"  noWrap>
					<%
					int sfzzlx_dm = Integer.parseInt(gdzydjcxmodel.getSfzzlx());
					switch(sfzzlx_dm)
					{
						case 02:	%>身份证 		<%; break;						
					 	case 03:  %>军人证件		<%; break;
						case 04:	%>护照        		<%; break;
				    	case 05:	%>港澳同胞回乡证	<%; break; 	
				    	case 90:  %>其他			<%; break;
				    	default:
					}
					
%>	
				</td>		
  			</tr>
  			<%} %>
  			<tr>
  				<td class="2-td2-left"   noWrap>纳税人详细地址</td>
  				<td class="2-td2-center" colspan=3 noWrap>
  					<bean:write name="gdinf" property="nsrxxdz"/>&nbsp;
  				</td>
  			<tr>
						
  		</table>
			
		<!--*土地信息*-->
		<table><tr><td>&nbsp;</td></tr></table>
		<table width="99%"><tr><td align="left"><FONT color=#000000 size=2><strong>&nbsp;土地信息:</strong></FONT></td></tr></table>
		<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
		
			<tr>
				<td class="2-td2-t-left"   width="20%" noWrap>批准占地文号</td>
				<td class="2-td2-t-center" width="30%" noWrap>
					<bean:write name="gdinf" property="zdpwh"/>&nbsp;
				</td>
  				<td class="2-td2-t-right"    width="20%" noWrap>批准占地面积（批文农转用面积：平方米）</td>
  				<td class="2-td2-t-right"    width="30%" noWrap>
  					<bean:write name="gdinf" property="pzzdmj"/>&nbsp;
  				</td>		
  			</tr>
  			
			<tr>
  				<td class="2-td2-left"   noWrap>实际占地面积（与计税面积一致：平方米）</td>
  				<td class="2-td2-center" noWrap>
  					<bean:write name="gdinf" property="sjzdmj"/>&nbsp;
  				</td>
  				<td class="2-td2-right"  noWrap>批准时间/实际占地时间</td>
				<td class="2-td2-right"  noWrap>
					<bean:write name="gdinf" property="zdsj"/>&nbsp;
				</td>		
  			</tr>
  			
  			<tr>
  				<td class="2-td2-left"   colspan=1 noWrap>土地坐落地址</td>
  				<td class="2-td2-center" colspan=3 noWrap>
  					<bean:write name="gdinf" property="tdzldz"/>&nbsp;
  				</td>
  			</tr>
  			<tr>
  				<td class="2-td2-left"   colspan=1 noWrap>建设项目名称</td>
				<td class="2-td2-center" colspan=3 noWrap>
					<bean:write name="gdinf" property="jsxmmc"/>&nbsp;
				</td>		
  			</tr>
			
			</table>

			<!--*土地详情*-->
			<table> <tr><td heigth="15px"/></tr><table>
			<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
			<tr>
  				<td class="2-td1-left"   width="4%" noWrap>序号</td>
  				<td class="2-td1-center" width="12%" noWrap>占地用途</td>
  				<td class="2-td1-right"  width="12%" noWrap>适用税额（元/平方米）</td>
				<td class="2-td1-right"  width="12%" noWrap>计税面积（平方米）</td>
				<td class="2-td1-right"  width="12%" noWrap>计征税额（元）</td>
				<td class="2-td1-right"  width="12%" noWrap>减免面积（平方米）</td>
				<td class="2-td1-right"  width="12%" noWrap>减免税额（元）</td>
				<td class="2-td1-right"  width="12%" noWrap>应税面积（平方米）</td>
				<td class="2-td1-right"  width="12%" noWrap>应纳税额（元）</td>
  			</tr>
  			
  				<%int i=0; %>																			<!-- 序号 -->
  																										<!-- 合计 -->
				<logic:iterate id="tdxq" name="gdinf" property="sbmx">									<!-- 循环显示多条信息 -->
			<tr>
				<%i++;%>
  				<td class="2-td2-left"   noWrap><%=i%></td>
  				<td class="2-td2-center" noWrap>
  					<bean:write name="tdxq" property="zdyt"/>&nbsp;
  				</td>
  				<td class="2-td2-right"  noWrap>
  					<bean:write name="tdxq" property="syse"/>&nbsp;
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
  				<td class="2-td2-left" >合计</td>
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

			<!--*减免详情*-->
			<% if(gdzydjcxmodel.getJmxx()!=null )
			{
			%>
			<table><tr><td>&nbsp;</td></tr></table>
			<table width="99%"><tr><td align="left"><FONT color=#000000 size=2><strong>&nbsp;减免信息:</strong></FONT></td></tr></table>
			<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
			<tr>
				<td class="2-td1-left"   width="25%" noWrap>序号</td>
				<td class="2-td1-center" width="25%" noWrap>减免类别</td>
				<td class="2-td1-right"  width="25%" noWrap>减免面积（平方米）</td>
				<td class="2-td1-right"  width="25%" noWrap>分类减免税额（元）</td>
			</tr>
			
				<%int j=0; %>
				<logic:iterate id="jmxq" name="gdinf" property="jmxx">
				
			<tr>
				<%j++;%>
  				<td class="2-td2-left" noWrap><%=j %></td>
  				<td class="2-td2-center" noWrap>
  					<bean:write name="jmxq" property="jmslb"/>&nbsp;
  				</td>
  				<td class="2-td2-right" noWrap>
  					<bean:write name="jmxq" property="jmmj"/>&nbsp;
  				</td>
				<td class="2-td2-right" noWrap>
					<bean:write name="jmxq" property="jmse"/>&nbsp;
				</td>
  			</tr>
  			
				</logic:iterate>
			</table>
			
			<!--*减免理由*-->
			<table><tr><td>&nbsp;</td></tr></table>
			<table width="99%"><tr><td align="left"><FONT color=#000000 size=2><strong>&nbsp;申报减免理由:</strong></FONT></td></tr></table>
			<table align="center" width="99%" height = 90 border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
			<tr>
				<td class =2-td2-t-center width="100%" >
            		<textarea  style="width:99%;text-align:left " left="true"   rows="5" disabled><bean:write name="gdinf" property="jmly"/>&nbsp;
            		</textarea>
				</tr>
			</tr>
			</table>
			<%} %>
		
			<!--*备注*-->
			<table><tr><td>&nbsp;</td></tr></table>
			<table width="99%"><tr><td align="left"><FONT color=#000000 size=2><strong>&nbsp;备注:</strong></FONT></td></tr></table>
			<table align="center" width="99%" height = 90 border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
			<tr>
				<td class =2-td2-t-center width="100%">
            		<textarea   style="width:99%;text-align:left " rows="5" disabled><bean:write name="gdinf" property="bz"/>&nbsp;
            		</textarea>
				</tr>
			</tr>
			</table>
				
				
			<!--*确认信息*-->
			<table><tr><td>&nbsp;</td></tr></table>
			<table width="99%"><tr><td align="left"><FONT color=#000000 size=2><strong>&nbsp;确认信息:</strong></FONT></td></tr></table>
			
								<!-- *是否市局审核* -->
			<table align="center" width="99%" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
			<tr>
				<td class =2-td2-t-left noWrap colspan=2>
            		是否市局审核
				</td>
				<td class =2-td2-t-center noWrap colspan=2>
						<%if(gdzydjcxmodel.getSfsjsp().equals("0")) {%>
		  					否
		  				<% }%>
		  				<%if(gdzydjcxmodel.getSfsjsp().equals("1")) {%>
		  					是
		  				<% }%>
				</td>
			</tr>
			<tr>
				<td class=2-td2-left  noWrap colspan=2>审核状态
				</td>
	            <td class=2-td2-center noWrap colspan=2>
	                  <%
	                if(gdzydjcxmodel.getSbzt().equals("10")){
	                	%>
	                	未审核
	                	<%
	                	}else if(gdzydjcxmodel.getSbzt().equals("30")){
	                	%>
	                	区县已审核
	                	<%
	                	}else{
	                		%>
	                		市局已审核
	                		<%
	                	}
	                %>     
	              </td>
			</tr>
			<tr>
                <TD class=2-td2-left noWrap width="25%">创建人</TD>
                <TD class=2-td2-left noWrap width="25%"> <bean:write name="gdinf" property="cjr"/>&nbsp; </TD>
                <TD class=2-td2-left noWrap width="25%">创建时间</TD>
                <TD class=2-td2-center noWrap width="25%"> <bean:write name="gdinf" property="cjsj"/>&nbsp;</TD>
             </tr>
             <tr>
                <TD class=2-td2-left noWrap width="25%">区县审核人</TD>
                <TD class=2-td2-left noWrap width="25%"> <bean:write name="gdinf" property="qrr"/>&nbsp; </TD>
                <TD class=2-td2-left noWrap width="25%">审核时间</TD>
                <TD class=2-td2-center noWrap width="25%"> <bean:write name="gdinf" property="qrsj"/>&nbsp;</TD>
             </tr>
                <%
                	if(gdzydjcxmodel.getSfsjsp().equals("1"))
                	{
                %>
             <tr class=black9>
                <TD class=2-td2-left noWrap>市局审核人</TD>
                <td class=2-td2-left noWrap>	<bean:write name="gdinf" property="sjqrr"/>&nbsp; </td>
                <TD class=2-td2-left noWrap >市局审核时间</TD>
                <TD class=2-td2-center noWrap > <bean:write name="gdinf" property="sjqrsj"/>&nbsp; </TD>
             </tr>
                <%
                	}
                %>
			
			</table>
		
  		</logic:iterate>
<%} %>

	<!------------------------------------------------------第3种情况-----如果未查到结果显示并新建---------------------------------------------->
  		
  		<% if(sydjcx_Form.getNum()==0)
  		{
  		%>
  		<table  align="center" width="99%" height = "80" border="1" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF">
		<tr>
			<td class = "2-td2-t-center" >
			<font size="3px" color = "red"> 没有对应的税源信息，请进行税源登记 &nbsp;&nbsp;&nbsp;&nbsp;
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