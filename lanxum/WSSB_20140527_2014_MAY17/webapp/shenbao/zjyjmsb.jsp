<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="java.util.*"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.ttsoft.bjtax.shenbao.zjyjmsb.ZjyjmsbForm"%>
<%
String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
ZjyjmsbForm zjyjmsbForm = (ZjyjmsbForm)request.getAttribute("zjyjmsbForm");
int yf =zjyjmsbForm.getYf();
int djcelx =zjyjmsbForm.getDjcelx();
 boolean sjpd =zjyjmsbForm.isSjpd();
%>


<html>
<head>
<title>再就业减免税申报录入</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/inputchecker.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>

    <link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
    <style>
      input {
      font-size: 9pt;
      }
    </style>
    <link href="text.css" rel="stylesheet" type="text/css">
    <script language="JavaScript">
    	
    	//数据判断
    	var sjpd = <%=sjpd%>;
	function chciceTable1(){
			var qycelx = "<%=djcelx%>";
			if(qycelx==2){
			alert("您是个体经营者，请填写申报表(二)或(和)申报表(三)");
  		}
  		else {
  		table1.style.display = "";
  		table2.style.display = "none";
			table3.style.display = "none";
			document.getElementById('biao1').className = 'black9c';
      document.getElementById('biao2').className = 'default';
      document.getElementById('biao3').className = 'default';
  		}
	}
	function chciceTable2(){
		
			table1.style.display = "none";
  		table2.style.display = "";
			table3.style.display = "none";
			document.getElementById('biao1').className = 'default';
      document.getElementById('biao2').className = 'black9c';
      document.getElementById('biao3').className = 'default';
  		
	}
	function chciceTable3(){
			var qycelx = "<%=djcelx%>";
			if(qycelx==1){
			alert("您是企业用户，请填写申报表(一)或申报表(二)");
  		}
  		else {
  		table1.style.display = "none";
  		table2.style.display = "none";
			table3.style.display = "";
			document.getElementById('biao1').className = 'default';
      document.getElementById('biao2').className = 'default';
      document.getElementById('biao3').className = 'black9c';
  		}
	}
	function doSave(){ 
	  if(sjpd) {
	     alert("此申报数据已出表，不允许修改");
	     return false;
	   }	
    if(confirm(confirmSave))
	   {
	    document.forms[0].actionType.value = "Save";
			document.forms[0].submit();
       			return true;
	   }
	    else
	    {
       		return false;
    	}
	}
 function doDelete(){
 	   if(sjpd) {
	      alert("此申报数据已出表，不允许删除");
	      return false;
	   }
	   if(confirm(confirmDelete))
	   {
	    document.forms[0].actionType.value = "Delete";
			document.forms[0].submit();
       			return true;
	   }
	    else
	    {
       		return false;
    	}
    }
	function doReturn()
    {
	   if(confirm(confirmReturn))
	   {
       document.forms[0].actionType.value = "Rtn";
       document.forms[0].submit();
    	}
	    else
	    {
       		return false;
    	}
    	return true;
	}

	function qylxisSelect()
	{ 
	var v =document.forms[0].qylx.value;
	
	if(v==1){
		 document.forms[0].j1.disabled=false;
		 document.forms[0].j2.disabled=false;
		 document.forms[0].j3.disabled=false;
		 document.forms[0].j4.disabled=false;
		 document.forms[0].j5.disabled=false;
		 document.forms[0].j6.disabled=false;
		 document.forms[0].j7.disabled=false;
		 document.forms[0].j8.disabled=false;
		 document.forms[0].j9.disabled=false;
		 document.forms[0].j10.disabled=false;
		 document.forms[0].j14.disabled=true;
		}
		if(v==2){
		 document.forms[0].j1.disabled=false;
		 document.forms[0].j2.disabled=true;
		 document.forms[0].j3.disabled=true;
		 document.forms[0].j4.disabled=true;
		 document.forms[0].j5.disabled=false;
		 document.forms[0].j6.disabled=false;
		 document.forms[0].j7.disabled=false;
		 document.forms[0].j8.disabled=false;
		 document.forms[0].j9.disabled=false;
		 document.forms[0].j10.disabled=false;
		 document.forms[0].j14.disabled=true;
		}
		
		if(v==3){
		 document.forms[0].j1.disabled=false;
		 document.forms[0].j2.disabled=true;
		 document.forms[0].j3.disabled=true;
		 document.forms[0].j4.disabled=true;
		 document.forms[0].j5.disabled=true;
		 document.forms[0].j6.disabled=true;
		 document.forms[0].j7.disabled=true;
		 document.forms[0].j8.disabled=true;
		 document.forms[0].j9.disabled=true;
		 document.forms[0].j10.disabled=true;
		 document.forms[0].j14.disabled=false;
		 }
		if(v==4){
		 document.forms[0].j1.disabled=false;
		 document.forms[0].j2.disabled=true;
		 document.forms[0].j3.disabled=true;
		 document.forms[0].j4.disabled=true;
		 document.forms[0].j5.disabled=true;
		 document.forms[0].j6.disabled=true;
		 document.forms[0].j7.disabled=true;
		 document.forms[0].j8.disabled=true;
		 document.forms[0].j9.disabled=true;
		 document.forms[0].j10.disabled=true;
		 document.forms[0].j14.disabled=false;
		 }
		if(v==5){
		 document.forms[0].j1.disabled=false;
		 document.forms[0].j2.disabled=true;
		 document.forms[0].j3.disabled=true;
		 document.forms[0].j4.disabled=true;
		 document.forms[0].j5.disabled=true;
		 document.forms[0].j6.disabled=true;
		 document.forms[0].j7.disabled=true;
		 document.forms[0].j8.disabled=true;
		 document.forms[0].j9.disabled=true;
		 document.forms[0].j10.disabled=true;
		 document.forms[0].j14.disabled=false;
		 }
	}
	function qylx1isSelect()
	{ 
	var lx1 =document.forms[0].qylx1.value;
	
	if(lx1==1){
		 document.forms[0].j21.disabled=false;
		 document.forms[0].j22.disabled=false;
		 document.forms[0].j23.disabled=false;
		 document.forms[0].j24.disabled=false;
		 document.forms[0].j25.disabled=false;
		 document.forms[0].j26.disabled=false;
		 document.forms[0].j27.disabled=false;
		 document.forms[0].j28.disabled=false;
		 document.forms[0].j29.disabled=false;
		 document.forms[0].j30.disabled=false;
		 document.forms[0].j31.disabled=true;
		 document.forms[0].j32.disabled=true;
		 document.forms[0].j33.disabled=true;
		 document.forms[0].j34.disabled=false;
		}

		
		if(lx1==2){
		 document.forms[0].j21.disabled=false;
		 document.forms[0].j22.disabled=true;
		 document.forms[0].j23.disabled=true;
		 document.forms[0].j24.disabled=true;
		 document.forms[0].j25.disabled=true;
		 document.forms[0].j26.disabled=true;
		 document.forms[0].j27.disabled=true;
		 document.forms[0].j28.disabled=true;
		 document.forms[0].j29.disabled=true;
		 document.forms[0].j30.disabled=true;
		 document.forms[0].j31.disabled=true;
		 document.forms[0].j32.disabled=true;
		 document.forms[0].j33.disabled=true;
		 document.forms[0].j34.disabled=false;
		 }
		if(lx1==3){
		 document.forms[0].j21.disabled=false;
		 document.forms[0].j22.disabled=false;
		 document.forms[0].j23.disabled=false;
		 document.forms[0].j24.disabled=false;
		 document.forms[0].j25.disabled=false;
		 document.forms[0].j26.disabled=false;
		 document.forms[0].j27.disabled=false;
		 document.forms[0].j28.disabled=false;
		 document.forms[0].j29.disabled=false;
		 document.forms[0].j30.disabled=false;
		 document.forms[0].j31.disabled=false;
		 document.forms[0].j32.disabled=false;
		 document.forms[0].j33.disabled=false;
		 document.forms[0].j34.disabled=true;
		}
	}
	 </script>
    <style type="text/css">
      input {
      font-size: 9pt;
      }
      .black9c {
	   font-size: 16pt;
	   color: #000000;
	   white-space: pre;
	   font-weight: bold;
     }
    </style>
</head>
 <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" ">
  
  <table width="104%" height="100%" border='0' cellpadding='0' cellspacing='0' align='center' class='black9'>
   
   <tr>
      <td align="center" valign="top">
       <jsp:include page="../include/SbHeader.jsp" flush="true" >
        <jsp:param name="name" value="再就业减免税申报录入" />
        	<jsp:param name="help_url" value="help/wssb/sbzl/zrrsb/zjyjmsb-000.htm"/>
        </jsp:include>
         <html:errors/>
         	
      </td>
   </tr>
  <tr>
  
<td width="104%" valign="middle" align="center">
    <br>
    <html:form  method="POST" action="/zjyjmsb.do">
	<html:hidden property="actionType"/>
	<html:hidden property="jsjdm"/>
	<html:hidden property="nd"/>
	<html:hidden property="jd"/>
	<table width="104%" align="center" cellspacing="0" class="table-99">
	<tr>
   <td valign="top" class='1-td1'>
    <a id="biao1" href="#" onclick="javascript:chciceTable1()">再就业申报表(一)</font></a>&nbsp;&nbsp;&nbsp;
    <a id="biao2" href="#" onclick="javascript:chciceTable2()">再就业申报表(二)</font></a>&nbsp;&nbsp;&nbsp;
    <a id="biao3" href="#" onclick="javascript:chciceTable3()">再就业申报表(三)</font></a>&nbsp;&nbsp;&nbsp;
   </td></tr>
	<span id = "table1"  style= "display:none">
    <table width="104%" align="center" cellspacing="0" class="table-99">
    	
        <tr>
          <td class="1-td1">再就业减免税申报表(之一)</td>
        </tr>
        <tr>
          <td width="100%" class="1-td2" align="center"> <br>
		 
              <table width="104%" cellspacing="0" class="table-99">
                  <tr class="black9">
				              <td width="12%" nowrap class="2-td2-t-left"><div align="right">纳税户名称&nbsp;&nbsp;</div></td>
                      <td width="15%" nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="nsrmc"/></div></td>
                      <td width="12%" nowrap class="2-td2-t-left"><div align="right">计算机代码&nbsp;&nbsp;</div></td>
                      <td width="15%" nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="jsjdm"/></div></td>
                      <td width="10%" nowrap class="2-td2-t-left"><div align="right">联系电话&nbsp;&nbsp;</div></td>
                      <td width="10%" nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="dh"/></div></td>
					            <td width="7%" nowrap class="2-td2-t-center"><div align="right">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="nd"/>年</div></td>
					            <td width="7%" nowrap class="2-td2-t-center"><div align="right">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="jd"/>季度</div>
					            </td>
					          <td width="10%" nowrap class="2-td2-t-left"><div align="right">计算单位：人、元&nbsp;&nbsp;</div></td>		
                 </tr>
             </table>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99" id="mxtable">
  <tr>
    <td width="12%" rowspan="3" class="2-td1-left">类别</td>
    <td width="6%" rowspan="3" class="2-td1-left">吸纳下岗失业人员数(人)本季度</td>
    <td colspan="13" class="2-td1-left">减免税额</td>
  </tr>
  <tr>
    <td colspan="3" class="2-td1-left">营业税</td>
    <td colspan="3" class="2-td1-left">城市维护建设税</td>
    <td colspan="3" class="2-td1-left">教育费附加</td>
    <td colspan="3" class="2-td1-left">个人所得税</td>
    <td width="10%" class="2-td1-left">企业所得税</td>
  </tr>
  <tr>
    <td width="6%" class="2-td1-left" name="2"><%=yf%>月</td>
    <td width="6%" class="2-td1-left" name="3"><%=yf+1%>月</td>
    <td width="6%" class="2-td1-left" name="4"><%=yf+2%>月</td>
    <td width="6%" class="2-td1-left" name="5"><%=yf%>月</td>
    <td width="6%" class="2-td1-left" name="6"><%=yf+1%>月</td>
    <td width="6%" class="2-td1-left" name="7"><%=yf+2%>月</td>
    <td width="6%" class="2-td1-left" name="8"><%=yf%>月</td>
    <td width="6%" class="2-td1-left" name="9"><%=yf+1%>月</td>
    <td width="6%" class="2-td1-left" name="10"><%=yf+2%>月</td>
    <td width="6%" class="2-td1-left" name="11"><%=yf%>月</td>
    <td width="6%" class="2-td1-left" name="12"><%=yf+1%>月</td>
    <td width="6%" class="2-td1-left" name="13"><%=yf+2%>月</td>
    <td width="10%" class="2-td1-left" >本季度</td>
  </tr>
  <tr>
    <td class="2-td1-left">序号</td>
    <td class="2-td1-left">1</td>
    <td class="2-td1-left">2</td>
    <td class="2-td1-left">3</td>
    <td class="2-td1-left">4</td>
    <td class="2-td1-left">5</td>
    <td class="2-td1-left">6</td>
    <td class="2-td1-left">7</td>
    <td class="2-td1-left">8</td>
    <td class="2-td1-left">9</td>
    <td class="2-td1-left">10</td>
    <td class="2-td1-left">11</td>
    <td class="2-td1-left">12</td>
    <td class="2-td1-left">13</td>
    <td class="2-td1-left">14</td>
  </tr>
  <tr>
    <td height="33" class="2-td2-left">
	    <select name="qylx"  onChange="qylxisSelect()">
	    <option value="1" <%if(zjyjmsbForm.getQylx().equals("1")){out.println("selected");}%>>新办企业:服务型30%以上</option>
	    <option value="2" <%if(zjyjmsbForm.getQylx().equals("2")){out.println("selected");}%>>新办企业:商业纯零售30%以上</option>
	    <option value="3" <%if(zjyjmsbForm.getQylx().equals("3")){out.println("selected");}%>>现有企业:服务型30%以上</option>
	    <option value="4" <%if(zjyjmsbForm.getQylx().equals("4")){out.println("selected");}%>>现有企业:商业纯零售30%以上</option>
	    <option value="5" <%if(zjyjmsbForm.getQylx().equals("5")){out.println("selected");}%>>主辅体分离经济实体</option>
	    </select>
	  </td>                              
    <td class="2-td2-left">
	   <input name="j1" size="7" maxlength="16" <% if(zjyjmsbForm.getJ1()!=null){%> value="<%=zjyjmsbForm.getJ1()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j2" size="7" maxlength="16" <% if(zjyjmsbForm.getJ2()!=null){%> value="<%=zjyjmsbForm.getJ2()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j3" size="7" maxlength="16" <% if(zjyjmsbForm.getJ3()!=null){%> value="<%=zjyjmsbForm.getJ3()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j4" size="7" maxlength="16" <% if(zjyjmsbForm.getJ4()!=null){%> value="<%=zjyjmsbForm.getJ4()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j5" size="7" maxlength="16" <% if(zjyjmsbForm.getJ5()!=null){%> value="<%=zjyjmsbForm.getJ5()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j6" size="7" maxlength="16" <% if(zjyjmsbForm.getJ6()!=null){%> value="<%=zjyjmsbForm.getJ6()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j7" size="7" maxlength="16" <% if(zjyjmsbForm.getJ7()!=null){%> value="<%=zjyjmsbForm.getJ7()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j8" size="7" maxlength="16" <% if(zjyjmsbForm.getJ8()!=null){%> value="<%=zjyjmsbForm.getJ8()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j9" size="7" maxlength="16" <% if(zjyjmsbForm.getJ9()!=null){%> value="<%=zjyjmsbForm.getJ9()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j10" size="7" maxlength="16" <% if(zjyjmsbForm.getJ10()!=null){%> value="<%=zjyjmsbForm.getJ10()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j11" size="7" maxlength="16" disabled></td>
    <td class="2-td2-left">
	   <input name="j12" size="7" maxlength="16" disabled></td>
    <td class="2-td2-left">
	   <input name="j13" size="7" maxlength="16" disabled></td>
    <td class="2-td2-left">
	   <input name="j14" size="7" maxlength="16" <% if(zjyjmsbForm.getJ14()!=null){%> value="<%=zjyjmsbForm.getJ14()%>" 
	    <%}else{%> value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" disabled></td>
  </tr>
</table>
</td>
</table>
       <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
           <tr>
             <td width="40%"> <hr width="100%" size="1" class="hr1" >
             </td>
             <td width="20%" align="center" class="black9">
             	<strong><font color="#0000FF">注意事项</font></strong>
             </td>
             <td width="40%"> <hr width="100%" size="1" class="hr1" >
             </td>
           </tr>
        </table>
        <table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
           <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
             <td height="47" >
             	<p>
             		&nbsp;&nbsp;&nbsp;&nbsp;1.本表由2005年12月31日前核准的享受再就业减免税政策的企业填报，在每季度结束后10天内填写并向主管税务所报送本表（网上申报的纳税人在每季度结束后10天内网上填写报送本表）。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;2.本表只填写本季度内能享受再就业减免税各月份的数据，超出减免税限额的应交税款按照有关规定进行纳税申报。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;3.本表可在主管税务所领取或从HTTP://WWW.TAX861.GOV.CN下载。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;4.“吸纳下岗失业人数（人）”：只填报本季度新增人数，减少人数不扣除。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;5.本表第14列“减免企业所得税本季度数”应小于或等于《企业所得税季度纳税申报表》第8列数据。<br>
              </p>
             </td>
           </tr>
        </table>
</span>
<span id = "table2" style="display:">
    <table width="104%" align="center" cellspacing="0" class="table-99">
    	
        <tr>
          <td class="1-td1">再就业减免税申报表(之二)</td>
        </tr>
        <tr>
          <td width="100%" class="1-td2" align="center"> <br>
              <table width="104%" cellspacing="0" class="table-99">
                  <tr class="black9">
				              <td width="12%" nowrap class="2-td2-t-left"><div align="right">纳税户名称&nbsp;&nbsp;</div></td>
                      <td width="15%" nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="nsrmc"/></div></td>
                      <td width="12%" nowrap class="2-td2-t-left"><div align="right">计算机代码&nbsp;&nbsp;</div></td>
                      <td width="15%" nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="jsjdm"/></div></td>
                      <td width="10%" nowrap class="2-td2-t-left"><div align="right">联系电话&nbsp;&nbsp;</div></td>
                      <td width="10%" nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="dh"/></div></td>
					            <td width="7%" nowrap class="2-td2-t-center"><div align="right">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="nd"/>年</div></td>
					            <td width="7%" nowrap class="2-td2-t-center"><div align="right">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="jd"/>季度</div></td>
					            <td width="10%" nowrap class="2-td2-t-left"><div align="right">计算单位：人、元&nbsp;&nbsp;</div></td>		
                 </tr>
             </table>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99" id="mxtable">
  <tr>
    <td width="12%" rowspan="3" class="2-td1-left">类别</td>
    <td width="6%" rowspan="3" class="2-td1-left">吸纳下岗失业人员数(人)本季度</td>
    <td colspan="13" class="2-td1-left">减免税额</td>
  </tr>
  <tr>
    <td colspan="3" class="2-td1-left">营业税</td>
    <td colspan="3" class="2-td1-left">城市维护建设税</td>
    <td colspan="3" class="2-td1-left">教育费附加</td>
    <td colspan="3" class="2-td1-left">个人所得税</td>
    <td width="10%" class="2-td1-left">企业所得税</td>
  </tr>
  <tr>
    <td width="6%" class="2-td1-left" name="2"><%=yf%>月</td>
    <td width="6%" class="2-td1-left" name="3"><%=yf+1%>月</td>
    <td width="6%" class="2-td1-left" name="4"><%=yf+2%>月</td>
    <td width="6%" class="2-td1-left" name="5"><%=yf%>月</td>
    <td width="6%" class="2-td1-left" name="6"><%=yf+1%>月</td>
    <td width="6%" class="2-td1-left" name="7"><%=yf+2%>月</td>
    <td width="6%" class="2-td1-left" name="8"><%=yf%>月</td>
    <td width="6%" class="2-td1-left" name="9"><%=yf+1%>月</td>
    <td width="6%" class="2-td1-left" name="10"><%=yf+2%>月</td>
    <td width="6%" class="2-td1-left" name="11"><%=yf%>月</td>
    <td width="6%" class="2-td1-left" name="12"><%=yf+1%>月</td>
    <td width="6%" class="2-td1-left" name="13"><%=yf+2%>月</td>
    <td width="10%" class="2-td1-left" >本季度</td>
  </tr>
  <tr>
    <td class="2-td1-left">序号</td>
    <td class="2-td1-left">1</td>
    <td class="2-td1-left">2</td>
    <td class="2-td1-left">3</td>
    <td class="2-td1-left">4</td>
    <td class="2-td1-left">5</td>
    <td class="2-td1-left">6</td>
    <td class="2-td1-left">7</td>
    <td class="2-td1-left">8</td>
    <td class="2-td1-left">9</td>
    <td class="2-td1-left">10</td>
    <td class="2-td1-left">11</td>
    <td class="2-td1-left">12</td>
    <td class="2-td1-left">13</td>
    <td class="2-td1-left">14</td>
  </tr>
  <tr>
    <td class="2-td2-left">
	    <select name="qylx1" onChange="qylx1isSelect()">
	    <option value="1" <%if(zjyjmsbForm.getQylx1().equals("1")){out.println("selected");}%>>企业吸收下岗失业人员</option>
	    <option value="2" <%if(zjyjmsbForm.getQylx1().equals("2")){out.println("selected");}%>>主辅分离经济实体</option>
	    <option value="3" <%if(zjyjmsbForm.getQylx1().equals("3")){out.println("selected");}%>>个体经营</option>
	    </select>
	  </td>
    <td class="2-td2-left">
	   <input name="j21" size="7" maxlength="16" <% if(zjyjmsbForm.getJ21()!=null){%> value="<%=zjyjmsbForm.getJ21()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j22" size="7" maxlength="16" <% if(zjyjmsbForm.getJ22()!=null){%> value="<%=zjyjmsbForm.getJ22()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j23" size="7" maxlength="16" <% if(zjyjmsbForm.getJ23()!=null){%> value="<%=zjyjmsbForm.getJ23()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j24" size="7" maxlength="16" <% if(zjyjmsbForm.getJ24()!=null){%> value="<%=zjyjmsbForm.getJ24()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j25" size="7" maxlength="16" <% if(zjyjmsbForm.getJ25()!=null){%> value="<%=zjyjmsbForm.getJ25()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j26" size="7" maxlength="16" <% if(zjyjmsbForm.getJ26()!=null){%> value="<%=zjyjmsbForm.getJ26()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j27" size="7" maxlength="16" <% if(zjyjmsbForm.getJ27()!=null){%> value="<%=zjyjmsbForm.getJ27()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j28" size="7" maxlength="16" <% if(zjyjmsbForm.getJ28()!=null){%> value="<%=zjyjmsbForm.getJ28()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j29" size="7" maxlength="16" <% if(zjyjmsbForm.getJ29()!=null){%> value="<%=zjyjmsbForm.getJ29()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j30" size="7" maxlength="16" <% if(zjyjmsbForm.getJ30()!=null){%> value="<%=zjyjmsbForm.getJ30()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j31" size="7" maxlength="16" <% if(zjyjmsbForm.getJ31()!=null){%> value="<%=zjyjmsbForm.getJ31()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j32" size="7" maxlength="16" <% if(zjyjmsbForm.getJ32()!=null){%> value="<%=zjyjmsbForm.getJ32()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j33" size="7" maxlength="16" <% if(zjyjmsbForm.getJ33()!=null){%> value="<%=zjyjmsbForm.getJ33()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
    <td class="2-td2-left">
	   <input name="j34" size="7" maxlength="16" <% if(zjyjmsbForm.getJ34()!=null){%> value="<%=zjyjmsbForm.getJ34()%>" 
	    <%}else{%>value="" <%}%> onChange="return (checkvalue(this,0)&&formatCurrency(this,0))" ></td>
  </tr>
</table>
</td>
</table>
         <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
           <tr>
             <td width="40%"> <hr width="100%" size="1" class="hr1" >
             </td>
             <td width="20%" align="center" class="black9">
             	<strong><font color="#0000FF">注意事项</font></strong>
             </td>
             <td width="40%"> <hr width="100%" size="1" class="hr1" >
             </td>
           </tr>
        </table>
        <table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
           <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
             <td height="47" >
             	<p>
             		&nbsp;&nbsp;&nbsp;&nbsp;1.本表由2006年1月1日后新核准享受再就业减免税政策的企业和所有持《再就业优惠证》人员从事个体经营者填报。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;2.（1）企业和按季申报的个体户在每季度结束后10天内填写并向主管税务所报送本表（网上申报的纳税人在每季度结束后10天内网上填写报送本表）。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 需填写所申报季度内能享受再就业减免税各月份的数据，超出减免税限额内的当月只填写可减免的税额。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; （2）按月申报的个体户在每月申报时填写本表，只填写所申报月份的数据，申报数据为所申报月份内可享受的减免税额（限额内）。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; （3）按次申报的个体户在每次申报时填写本表，月份为申报日所属月份，申报数据为上次申报至本次申报时间段内可享受的减免税额（限额内）。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;3.本表只填写本季度内能享受再就业减免税各月份的数据，超出减免税限额的应交税款按照有关规定进行纳税申报。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;4.本表可在主管税务所领取或从HTTP://WWW.TAX861.GOV.CN下载。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;5.“吸纳下岗失业人员数（人）”：只填报本季度新增人数，减少人数不扣除。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;6.“个体经营”：此项目中的“吸纳下岗失业人员数”不包含从事个体经营的下岗失业人员本人。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;7.本表第14列“企业所得税（本年度）”有数据的纳税人，在企业所得税年度汇算清缴结束后10天内填写本列全年数据和第1-13列第四季度数据，并上报主管税务机关（或网上申报）。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;8.对“个体经营”：此项目中的“吸纳下岗失业人员数”不包含从事个体经营的下岗失业人员本人。<br>
              </p>
             </td>
           </tr>
        </table>
</span>
<span id = "table3" style= "display:none">
    <table width="96%" align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">再就业减免税申报表(之三)</td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
              <table width="100%" cellspacing="0" class="table-99">
                  <tr class="black9">
                     <td width="15%" nowrap class="2-td2-t-left"><div align="right">税务计算机代码&nbsp;&nbsp;</div></td>
                      <td width="10%" nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="jsjdm"/></div></td>
                      <td width="10%" nowrap class="2-td2-t-left"><div align="right">纳税户名称&nbsp;&nbsp;</div></td>
                      <td width="20%" nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="nsrmc"/></div></td>
                      <td width="10%" nowrap class="2-td2-t-left"><div align="right">联系电话&nbsp;&nbsp;</div></td>
                      <td width="10%" nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="dh"/></div></td>
					            <td width="10%"  nowrap class="2-td2-t-center"><div align="right">&nbsp;&nbsp;<bean:write name="zjyjmsbForm" property="nd"/>年</div></td>
					            <td width="15%" nowrap class="2-td2-t-left">  <div align="right">计算单位:元&nbsp;&nbsp;</div></td>		
                 </tr>
             </table>          
          </td>   
        </tr>
		<table align="center" cellspacing="0" class="table-99">
            <tr class="black9">
			  <td width="20%" class="2-td2-left">
		    <div align="right">全年营业收入&nbsp;&nbsp;</div></td>
		      <td width="30%" class="2-td2-left">
		      <div align="left">&nbsp;&nbsp;<html:text name="zjyjmsbForm" property="qnyysr" onchange="return (checkvalue(this,0)&&formatCurrency(this,0))" value="<%=zjyjmsbForm.getQnyysr()%>"/></div></td>
		    <td width="20%" class="2-td2-left">
		    <div align="right">全年减免税额&nbsp;&nbsp;</div></td>
		      <td width="30%" class="2-td2-center">
		      <div align="left">&nbsp;&nbsp;<html:text name="zjyjmsbForm" property="qnjmse" onchange="return (checkvalue(this,0)&&formatCurrency(this,0))" value="<%=zjyjmsbForm.getQnjmse()%>"/></div></td>
            </tr>
         </table>
    </table>
 </table>
     <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
           <tr>
             <td width="40%"> <hr width="100%" size="1" class="hr1" >
             </td>
             <td width="20%" align="center" class="black9">
             	<strong><font color="#0000FF">注意事项</font></strong>
             </td>
             <td width="40%"> <hr width="100%" size="1" class="hr1" >
             </td>
           </tr>
        </table>
        <table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
           <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
             <td height="47" >
             	<p>
             		&nbsp;&nbsp;&nbsp;&nbsp;1.本表由下岗失业人员从事个体经营且营业额未达到营业税起征点的个体工商户填报，在次年4月10日前填写并向主管税务所报送本表（网上申报的纳税人在次年4月10日前网上填写报送本表）。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;2.本表可在主管税务所领取或从HTTP://WWW.TAX861.GOV.CN下载。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;3.本表填报政策执行年度的全年总数。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;4."全年营业收入"栏次应填报全年营业税应税收入。<br>
             		&nbsp;&nbsp;&nbsp;&nbsp;5."全年减免税额"栏次应填报全年减免营业税税额。<br>
              </p>
             </td>
           </tr>
        </table>
 </span>
</html:form>
    <tr>
          <td colspan="13">
            <br>
            <div align="center">
		  	    <shenbao:imgButton src="images/baocun1.jpg"  alt="保存" overSrc="images/baocun2.jpg"  onclick="return doSave()"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
			      <shenbao:imgButton src="images/shanchu1.jpg" alt="删除" overSrc="images/shanchu1.jpg" onclick="return doDelete()"/>
		                &nbsp;&nbsp;&nbsp;&nbsp;
			      <shenbao:imgButton src="images/fanhui1.jpg"  alt="返回" overSrc="images/fanhui2.jpg"  onclick="doReturn()"/>
          </div>
          </td>
    </tr>
	
  </td>
  </tr>
   <tr>
     <td valign="bottom" align="center">
        <%@ include file="../include/bottom.jsp" %>
     </td>
   </tr>
</table> 
</body>
</html>
