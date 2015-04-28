<%@ page contentType="text/html; charset=gb2312" language="java"  errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.common.model.UserData"%>
<%@ page import="com.ttsoft.framework.util.DateTimeUtil"%>
<%@ page import="com.ttsoft.bjtax.dj.model.YHZH"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ttsoft.bjtax.dj.util.CodeTableUtil"%>
<%@ page import="com.ttsoft.bjtax.dj.util.CodeTableKey"%>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.web.GdzysSydjxxlrForm" %>

<head>  
	<link href="../css/text.css" rel="stylesheet" type="text/css">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
</head>
<html:html>
<STYLE>.inputalignright {
	FONT-SIZE: 9pt; TEXT-ALIGN: right
}
.inbright {
	FONT-SIZE: 9pt; BORDER-TOP: 0px; BORDER-RIGHT: 0px; BACKGROUND: #f3f5f8; BORDER-BOTTOM: 0px; TEXT-ALIGN: right; BORDER-LEFT: 0px
}
</STYLE>

<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>
 <%
     String currentDate = DateTimeUtil.getCurrentDate();
%>
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language=JavaScript src="../js/MathString.js"></script>
<script language=JavaScript src="../js/Stack.js"></script>
<script language=JavaScript src="../js/Bolan.js"></script>
<script language=JavaScript src="../js/reader.js"></script>
<script language=JavaScript src="../js/InputSelect.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/DTable.js"></script>
<SCRIPT language=JavaScript>


<%
//GdzysSydjxxlrForm sydjxxlrForm = (GdzysSydjxxlrForm)request.getAttribute("sydjxxlrForm");
//sydjxxlrForm.getZdyt_list();

%>

//根据纳税人类型显示不同输入项
function is_nsrlx_display(){
	var val = document.getElementById("nsrlx").value;
	var qy = document.getElementById("qy");
	if(val == 0){
		qy.style.display="inline";
	}else{
		qy.style.display="none";
	}



}





//计算
function jisuan(){
	//计征税额
	jzse();
	//减免税额
	jmse();
	//应税面积
	ysmj();
	//应纳税额
	ynse();
	//合计
	hj();
	
}

//计算计征税额
function jzse(){
	var syse = document.getElementsByName("syse");
	var jsmj = document.getElementsByName("jsmj");
	var jzse = document.getElementsByName("jzse");
	for(var i=0;i<jzse.length;i++){
		jzse[i].value =  parseFloat("0.00");
		jzse[i].value = parseFloat(syse[i].value.substring(3)) * parseFloat(jsmj[i].value);
	}

}

//计算减免税额
function jmse(){
	var syse = document.getElementsByName("syse");
	var jmmj = document.getElementsByName("jmmj");
	var jmse = document.getElementsByName("jmse");
	for(var i=0;i<jmse.length;i++){
		jmse[i].value =  parseFloat("0.00");
		jmse[i].value = parseFloat(syse[i].value.substring(3)) * parseFloat(jmmj[i].value);
	}

}
//计算应税面积
function ysmj(){
	var jsmj = document.getElementsByName("jsmj");
	var jmmj = document.getElementsByName("jmmj");
	var ysmj = document.getElementsByName("ysmj");
	for(var i=0;i<jsmj.length;i++){
		ysmj[i].value =  parseFloat("0.00");
		ysmj[i].value = parseFloat(jsmj[i].value) - parseFloat(jmmj[i].value);
		
	}
	
}
//计算应纳税额
function ynse(){
	var jzse = document.getElementsByName("jzse");
	var jmse = document.getElementsByName("jmse");
	var ynse = document.getElementsByName("ynse");
	for(var i=0;i<ynse.length;i++){
		ynse[i].value =  parseFloat("0.00");
		ynse[i].value = parseFloat(jzse[i].value) - parseFloat(jmse[i].value);
	}
}

//计算合计
function hj(){
	var jsmj_sum = 0;
	var jzse_sum = 0;
	var jmmj_sum = 0;
	var jmse_sum = 0;
	var ysmj_sum = 0;
	var ynse_sum = 0;
	var jsmj_val = document.getElementsByName("jsmj");
	var jzse_val = document.getElementsByName("jzse");
	var jmmj_val = document.getElementsByName("jmmj");
	var jmse_val = document.getElementsByName("jmse");
	var ysmj_val = document.getElementsByName("ysmj");
	var ynse_val = document.getElementsByName("ynse");
	for(var j = 0;j<jsmj_val.length;j++){
		jsmj_sum += parseFloat(jsmj_val[j].value);
		jzse_sum +=parseFloat(jzse_val[j].value);
		jmmj_sum +=parseFloat(jmmj_val[j].value);
		jmse_sum +=parseFloat(jmse_val[j].value);
		ysmj_sum +=parseFloat(ysmj_val[j].value);
		ynse_sum +=parseFloat(ynse_val[j].value);
	}

	document.getElementById("hj_jsmj").value="";
	document.getElementById("hj_jsmj").value=jsmj_sum;
	document.getElementById("hj_jzse").value="";
	document.getElementById("hj_jzse").value=jzse_sum;
	document.getElementById("hj_jmmj").value="";
	document.getElementById("hj_jmmj").value=jmmj_sum;
	document.getElementById("hj_jmse").value="";
	document.getElementById("hj_jmse").value=jmse_sum;
	document.getElementById("hj_ysmj").value="";
	document.getElementById("hj_ysmj").value=ysmj_sum;
	document.getElementById("hj_ynse").value="";
	document.getElementById("hj_ynse").value=ynse_sum;
}

//======================================================================================================================================================

//减免类别
//输入计算机代码获取登记信息
 function getDJXX(){
	   var xmlhttp_request;
	   var jsjdm = document.getElementById("jsjdm");
	   var nsrlxdm = document.getElementById("jsjdm");
	   if(window.ActiveXObject){
	  	       xmlhttp_request=new ActiveXObject("Microsoft.XMLHTTP");
	  	    }else if(window.XMLHttpRequest){
	  	       xmlhttp_request=new XMLHttpRequest();
	  	     } else {
	  	       return;
	  	   }
	  	     xmlhttp_request.open("GET", "sydjxxlrAction.do?actionType=getDJXX&jsjdm="+jsjdm.value+"&nsrlxdm="+nsrlxdm.value, true);
	       	 xmlhttp_request.send(null);
	  	     xmlhttp_request.onreadystatechange =function zdyprocessAjaxResponse(){
	  	      var xmlDOM;
	          if (xmlhttp_request.readyState == 4) {
	          if (xmlhttp_request.status == 200) {
	         xmlDOM = xmlhttp_request.responseXML;
	         var obj = document.getElementById("nsrmc");
	         obj.value = xmlDOM.getElementsByTagName("nsrmc")[0].firstChild.data;
	       	 obj.text = xmlDOM.getElementsByTagName("nsrmc")[0].firstChild.data;
	       	 
	       	 var obj = document.getElementById("nsrsshy");
	         obj.value = xmlDOM.getElementsByTagName("nsrsshy")[0].firstChild.data;
	       	 
	       	 var obj = document.getElementById("nsrsshymc");
	         obj.value = xmlDOM.getElementsByTagName("nsrsshymc")[0].firstChild.data;
	       	 
	       	 var obj = document.getElementById("qydjzclx");
	         obj.value = xmlDOM.getElementsByTagName("qydjzclxdm")[0].firstChild.data;
	       	 var obj = document.getElementById("qydjzclxmc");
	       	 obj.value = xmlDOM.getElementsByTagName("qydjzclxmc")[0].firstChild.data;
	       	 
	         var obj = document.getElementById("nsrxxdz");
	         obj.value = xmlDOM.getElementsByTagName("nsrxxdz")[0].firstChild.data;
	       	 obj.text = xmlDOM.getElementsByTagName("nsrxxdz")[0].firstChild.data;
	       	 var obj = document.getElementById("nsrsbh");
	       	 obj.value = xmlDOM.getElementsByTagName("nsrsbh")[0].firstChild.data;
	       	 obj.text = xmlDOM.getElementsByTagName("nsrsbh")[0].firstChild.data;
	         
	       }
	     }
	  	 };
}


//获取适用税额

 function getSyse(){
	   var xmlhttp_request;
	   var syse_nd = document.getElementById("syse_nd");
	   if(window.ActiveXObject){
	  	       xmlhttp_request=new ActiveXObject("Microsoft.XMLHTTP");
	  	    }else if(window.XMLHttpRequest){
	  	       xmlhttp_request=new XMLHttpRequest();
	  	     } else {
	  	       return;
	  	   }
	  	     xmlhttp_request.open("GET", "sydjxxlrAction.do?actionType=getSYSE&sysend="+syse_nd.value, true);
	       	 xmlhttp_request.send(null);
	  	     xmlhttp_request.onreadystatechange = function syseprocessAjaxResponse(){
	          if (xmlhttp_request.readyState == 4) {
	          if (xmlhttp_request.status == 200) {
	        	var xmlDOM = xmlhttp_request.responseXML;
	        	var objs = document.getElementsByName("syse");
	        	for(var k = 0;k<objs.length;k++){
	        		var obj = objs[k]; 
	        		obj.style.width='80%';
		        	var len = obj.options.length;
		        	for(var i = 0;i<len;i++){
		        		obj.options.remove(i);
		        		i--;
		        		len--;
		        	}
		        	var len2 = xmlDOM.getElementsByTagName("sysedm").length;
		     		for(var j=0;j<len2;j++){
		     			oOption = document.createElement("option");
		  	           	oOption.text= xmlDOM.getElementsByTagName("sysedm")[j].firstChild.data;
		  	          	oOption.value= xmlDOM.getElementsByTagName("syseval")[j].firstChild.data;
		  	          	obj.add(oOption);
		     		}
	        	}
	        	
	       }
	     }
	  	 };
}
//获取减免税依据
 function getJMSYJ(){
	   var xmlhttp_request;
	   var jmslb_list = new Array();
	   var jmslb = document.getElementsByName("jmlbdm_jmmx");
	   for(var i=0;i<jmslb.length;i++){
		   jmslb_list[i] = jmslb[i].value;
	   }
	   if(window.ActiveXObject){
	  	       xmlhttp_request=new ActiveXObject("Microsoft.XMLHTTP");
	  	    }else if(window.XMLHttpRequest){
	  	       xmlhttp_request=new XMLHttpRequest();
	  	     } else {
	  	       return;
	  	   }
	  	     xmlhttp_request.open("GET", "sydjxxlrAction.do?actionType=getJMSYJ&jmlbdm_jmmx="+jmslb_list, true);
	       	 xmlhttp_request.send(null);
	  	     xmlhttp_request.onreadystatechange = function jmsyjprocessAjaxResponse(){
	          if (xmlhttp_request.readyState == 4) {
	          if (xmlhttp_request.status == 200) {
	        	var xmlDOM = xmlhttp_request.responseXML;
	        	var obj = document.getElementById("jmsyj");
	        	var yj = xmlDOM.getElementsByTagName("jmsyjmc")[0].firstChild.data;
	        	obj.value="";
	        	obj.value = yj;
	     		}
	       }
	  	 };
}



//保存 
function befSave(flag){
	document.forms[0].actionType.value = "Save";
	alert(flag);
	document.forms[0].submit();  
}
//打印申报表 
function printsbb(){
	document.forms[0].actionType.value = "PrintSBB";
	document.forms[0].submit();  
}
//减免申报表
function printjmsbb(){
	document.forms[0].actionType.value = "PrintJMSBB";
	document.forms[0].submit();  
}
//确认时间

function qrsj(){
	 var sj=prompt("请输入确认时：","");
	 
	 
}


</SCRIPT>
<%
GdzysSydjxxlrForm form = (GdzysSydjxxlrForm)request.getAttribute("sydjxxlrForm");

%>
<html:form action="/webapp/smsb/sydjxxlrAction.do" method="post">
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
 <%@ include file="../../include/header.jsp"%>
<html:hidden property="actionType"/>
<html:hidden property="sjqrsj"/>
<input type="hidden" name="sbbxlh" value='<bean:write name="sydjxxlrForm" property="sbbxlh"/>'/>
<input type="hidden" name="sysend" value='<bean:write name="sydjxxlrForm" property="sysend"/>'/>

<input type="hidden" name="jms_flag" value='<bean:write name="sydjxxlrForm" property="jms_flag"/>'/>
<input type="hidden" name="jks_flag" value='<bean:write name="sydjxxlrForm" property="jks_flag"/>'/>
<input type="hidden" name="zwbs_flag" value='<bean:write name="sydjxxlrForm" property="zwbs_flag"/>'/>

<TABLE class=black9 height=300 cellSpacing=0 cellPadding=0 width="90%"  
align=center border=0 >
  <TBODY>
  <TR>
    <TD height=300 vAlign=top><BR>
      <TABLE class=table-99 cellSpacing=0 align=center>
        <TBODY>
        <TR>
          <TD class=1-td1 colSpan=2>税源登记详细信息</TD></TR>
        <TR>
          <TD class=1-td2 colSpan=2><BR>
          <DIV align=left><FONT color=#000000 
            size=1><STRONG>基本信息：</STRONG></FONT> 
            <TABLE class=table-99 cellSpacing=0 cellPadding=0 width="80%" border=0 align = center>
              <TBODY>
              <TR class=black9>
                <TD class=2-td2-t-left width="20%" noWrap>申报序列号</TD>
                <TD class=2-td2-t-left noWrap><bean:write name="sydjxxlrForm" property="sbbxlh"/></TD>
                 <TD class=2-td2-t-left width="20%" noWrap>纳税人类型</TD>
                 <%
                 if(form.getNsrlx().equals("0")){
                	%>
                	<TD class=2-td2-t-center noWrap>单位</td>
                	<%	 
                 } else{
                 %>
                	 <TD class=2-td2-t-center noWrap>个人</td>
                <%
                 }
                 %>
                </TR>
             	<%
             	 if(form.getNsrlx().equals("0")){
             	
             	%>
               <TR class=black9>
                <TD class=2-td2-left noWrap>计算机代码</TD>
                <TD class=2-td2-left noWrap><bean:write name="sydjxxlrForm" property="jsjdm"/></TD>
                <TD class=2-td2-left  noWrap>&nbsp;</TD>
                <TD class=2-td2-center noWrap>&nbsp;</TD>
                </TR> 
                <%
                }
             	%>
               <TR>
                <TD class=2-td2-left noWrap>税源类型</TD>
                <%
                if(form.getSylxdm().equals("2")){
                	%>
                	<TD class=2-td2-left noWrap>有征有免</TD>
                	<% 
                }else if(form.getSylxdm().equals("0")){
                	%>
                	<TD class=2-td2-left noWrap>全征</TD>
                	<%
                }else{
                	%>
                	<TD class=2-td2-left noWrap>全免</TD>
                	<%
                }
                %>
                 <TD class=2-td2-left width="20%" noWrap>税额类型</TD>
                 <% 
                 if(form.getSysend().equals("2007")){
                	 %>
                	 <TD class=2-td2-center noWrap>旧税额</TD>
                	 <%
                 } else{
                	 %>
                	 <TD class=2-td2-center noWrap>新税额</TD>
                	 <%
                 }
                 %>
                
                </TR> </TBODY>
               <TR>
               <TD class=2-td2-left width="20%" noWrap>纳税人名称</TD>
                <TD class=2-td2-center noWrap colspan=3>&nbsp;<bean:write name="sydjxxlrForm" property="nsrmc"/></TD>
              </TR>
              <TR>
                <TD class=2-td2-left width="20%" noWrap>纳税人详细地址</TD>
                <TD class=2-td2-center noWrap colspan=3>&nbsp;<bean:write name="sydjxxlrForm" property="nsrxxdz"/></TD>
              </TR>
               <%
               
                if(form.getNsrlx().equals("0")){
                %>
             <TBODY id=qy>
              <TR class=black9>
                <TD class=2-td2-left width="20%" noWrap>纳税人所属行业</TD>
                <TD class=2-td2-left noWrap width="30%">&nbsp;<bean:write name="sydjxxlrForm" property="nsrsshymc"/></TD>
                <TD class=2-td2-left width="20%" noWrap>企业登记注册类型</TD>
                <TD class=2-td2-center noWrap width="30%">&nbsp;<bean:write name="sydjxxlrForm" property="qydjzclxmc"/></TD></TR>
           
              <TR>
                <TD class=2-td2-left width="20%" noWrap>纳税人识别号</TD>
                <TD class=2-td2-left noWrap>&nbsp;<bean:write name="sydjxxlrForm" property="nsrsbh"/> </TD>
                <TD class=2-td2-left noWrap>&nbsp;</TD>
                <TD class=2-td2-center noWrap>&nbsp;</TD></TR>
              <TR class=black9>
              	<TD class=2-td2-left width="20%" noWrap>开户银行</TD>
                <TD class=2-td2-left noWrap> &nbsp;<bean:write name="sydjxxlrForm" property="khyh"/></TD>
                <TD class=2-td2-left width="20%" noWrap>银行账号</TD>
                <TD class=2-td2-center noWrap>&nbsp;<bean:write name="sydjxxlrForm" property="yhzh"/></TD>
               
                </TR>
                
                <%
                }
                %>
                
             <TBODY>
              <TR>
              <TD class=2-td2-left width="20%" noWrap>联系人姓名</TD>
                <TD class=2-td2-left noWrap> <bean:write name="sydjxxlrForm" property="lxrxm"/> </TD>
                <TD class=2-td2-left width="20%" noWrap>联系电话</TD>
                <TD class=2-td2-left noWrap><bean:write name="sydjxxlrForm" property="lxdh"/> </TD>
                
                </TR>
                <%
                if(form.getNsrlx().equals("1")){
                %>
                
              <TR class=black9>
                <TD class=2-td2-left width="20%" noWrap>身份证照类型</TD>
                <TD class=2-td2-left noWrap>
                 <%
                
                 String sfzzlxdm = form.getSfzzlxdm();
                 
                 	if(sfzzlxdm.equals("02")){
                 		%>身份证<% 
                 	}else if(sfzzlxdm.equals("03")){
                 		%>军人证件<%
                 	}else if(sfzzlxdm.equals("04")){
                 		%>护照<%
                 	}else if(sfzzlxdm.equals("05")){
                 		%>港澳同胞回乡证<%
                 	}else {
                 		%>
                 	其他
                 		<%
                 	}
                 %>
                 
                 
                </TD> 
                <TD class=2-td2-left width="20%" noWrap>身份证照号码</TD>
                <TD class=2-td2-center noWrap><bean:write name="sydjxxlrForm" property="sfzzhm"/>
                </TD></TR>
                <%
                }
                
                %>
                
                </TBODY>
              
              </TABLE><BR> 
            <DIV align=left><FONT color=#000000 
            size=1><STRONG>土地信息：</STRONG></FONT> 
            <DIV><!--土地信息 -->
         <TABLE id=tdxx class=table-99 cellSpacing=0 cellPadding=0 
            width="80%" align=center border=0>
              <TBODY>
                <TR class=black9>
                <TD class=2-td2-t-left width="20%" noWrap>土地坐落地址</TD>
                <TD class=2-td2-t-center noWrap colspan= 3><bean:write name="sydjxxlrForm" property="tdzldz"/></TD>
                </TR>
               <TR>
                <TD class=2-td2-left width="20%" noWrap>建设项目名称</TD>
                <TD class=2-td2-center noWrap colspan = 3>&nbsp;<bean:write name="sydjxxlrForm" property="jsxmmc"/></TD>
                </TR> 
              <TR >
                <TD class=2-td2-left width="20%" noWrap>批准占地文号</TD>
                <TD class=2-td2-left noWrap width="30%">&nbsp;<bean:write name="sydjxxlrForm" property="pzjdwh"/></TD> 
                <TD class=2-td2-left width="20%" noWrap>批准占地面积（批文农转用面积：平方米）</TD>
                <TD class=2-td2-center noWrap width="30%">&nbsp;<bean:write name="sydjxxlrForm" property="pzjdmj"/></TD>
                </TR>
               <TR >
                <TD class=2-td2-left width="20%" noWrap>实际占地面积（与计税面积一致：平方米）</TD>
                <TD class=2-td2-left noWrap width="30%">&nbsp;<bean:write name="sydjxxlrForm" property="sjzdmj"/></TD>
                <TD class=2-td2-left width="20%" noWrap>占地时间/实际占地时间（格式：YYYYMMDD）</TD>
                <TD class=2-td2-center noWrap width="30%">&nbsp;<bean:write name="sydjxxlrForm" property="zdsj"/></TD></TR> 
              </TBODY></TABLE> <BR>
              
              
                 <TABLE id=ZDYT_LIST class=table-99 cellSpacing=0 cellPadding=0 
            width="80%" align=center border=0>
              <TBODY>
              <TR class=black9>
                <TD class=2-td2-t-left width="5%" noWrap>序号</TD>
                <TD class=2-td2-t-left width="10%" noWrap>占地用途</TD>
                <TD class=2-td2-t-left width="10%" noWrap>适用税额（元/平方米）</TD>
                <TD class=2-td2-t-left width="10%" noWrap>计税面积（平方米）</TD>
                <TD class=2-td2-t-left width="10%" noWrap>计征税额（元）</TD>
                <TD class=2-td2-t-left width="10%" noWrap>减免面积（平方米）</TD>
                <TD class=2-td2-t-left width="10%" noWrap>减免税额（元）</TD>
                <TD class=2-td2-t-left width="10%" noWrap>应税面积（平方米）</TD>
                <TD class=2-td2-t-left width="10%" noWrap>应纳税额（元）</TD>
                <TD class=2-td2-t-center width="15%" noWrap>操作</TD></TR>
              <TBODY id=ZDYT_ROWS>
              <TR class=black9>
              <% 
              //GdzysSydjxxlrForm form = (GdzysSydjxxlrForm)request.getAttribute("sydjxxlrForm");
              String[] zdyt = form.getZdyt();
              String[] syse = form.getSyse();
              String[] jsmj = form.getJsmj();
              String[] jzse = form.getJzse();
              String[] jmmj = form.getJmmj();
              String[] jmse = form.getJmse();
              String[] ysmj = form.getYsmj();
              String[] ynse = form.getYnse();
              List zdytmc = form.getZdytmc();
              List zdytdm = form.getZdytdm();
              for(int i = 0;i<form.getJsmj().length;i++){
            	  int j = syse[i].indexOf("-")+1;
            	  syse[i] = syse[i].substring(j);
            	  %>
                <TD class=2-td2-left noWrap><%= i+1%></TD>
                
                <TD class=2-td2-left noWrap>
                <%
                	for(int k = 0;k<zdytdm.size();k++){
                		if(zdytdm.get(k).toString().equals(zdyt[i])){
                %>
                			<%=zdytmc.get(k)%>
                <%
                	}
                }
                %>
                	
                </TD>
                <TD class=2-td2-left noWrap>
				<%=syse[i] %>
				
				</TD>
                <TD class=2-td2-left noWrap><%= jsmj[i]%></TD>
                  
                  
                <TD class=2-td2-left noWrap><%=jzse[i]%> </TD>
                <TD class=2-td2-left noWrap> <%=jmmj[i] %></TD>
                <TD class=2-td2-left noWrap><%=jmse[i] %></TD>
                <TD class=2-td2-left noWrap><%=ysmj[i] %> </TD>
                <TD class=2-td2-left noWrap><%=ynse[i] %></TD>
                <TD class=2-td2-center noWrap>&nbsp;<SPAN><U>删除</U></SPAN> </TD></TR></TBODY>
                <% 
              }
                %>
                <TBODY>
              <TR class=black9>
                <TD class=2-td2-left noWrap>合计 </TD>
                <TD class=2-td2-left noWrap>&nbsp; </TD>
                <TD class=2-td2-left noWrap>&nbsp; </TD>
                <TD class=2-td2-left noWrap><bean:write name="sydjxxlrForm" property="hj_jsmj"/></TD>
                <TD class=2-td2-left noWrap><bean:write name="sydjxxlrForm" property="hj_jzse"/></TD>
                <TD class=2-td2-left noWrap><bean:write name="sydjxxlrForm" property="hj_jmmj"/></TD>
                <TD class=2-td2-left noWrap><bean:write name="sydjxxlrForm" property="hj_jmse"/></TD>
                          
                <TD class=2-td2-left noWrap><bean:write name="sydjxxlrForm" property="hj_ysmj"/></TD>
                <TD class=2-td2-left noWrap><bean:write name="sydjxxlrForm" property="hj_ynse"/></TD>
                <TD class=2-td2-center noWrap>&nbsp; </TD></TR>
                </TBODY></TABLE><BR>
 			<%
 			
 			String[] jmlbdm_jmmx = form.getJmlbdm_jmmx();
            String[] fljmmj = form.getFljmmj();
            String[] fljmse = form.getFljmse();
            List jmlbdm = form.getJmlbdm();
            List jmlbmc = form.getJmlbmc();
            if( "".equals(jmlbdm_jmmx) || jmlbdm_jmmx==null || jmlbdm_jmmx.length<1 ){
            	
            }else{
            	
            	%>
            	<DIV align=left>
 			<FONT color=#000000 size=1><STRONG>减免税信息：</STRONG></FONT></div>
            		<TABLE id="JMLB_LIST" class=table-99 cellSpacing=0 cellPadding=0 width="80%" align=center border=0>
              <TBODY>
              <TR class=black9>
                <TD class=2-td2-t-left width="5%" noWrap>序号</TD>
                <TD class=2-td2-t-left width="30%" noWrap>减免类别</TD>
                <TD class=2-td2-t-left width="20%" noWrap>分类减免面积（平方米）</TD>
                <TD class=2-td2-t-left width="20%" noWrap>分类减免税额（元）</TD>
                <TD class=2-td2-t-center width="25%" noWrap>操作</TD></TR>
              <TBODY id="JMLB_ROWS">
              <%
              
              for(int i=0;i<fljmmj.length;i++){
            	  %>
              <TR class=black9>
                <TD class=2-td2-left noWrap><%=i+1 %></TD>
                <TD class=2-td2-left noWrap>
				<%
					for(int k =0;k<jmlbdm.size();k++){
					if(jmlbdm_jmmx[i].equals(jmlbdm.get(k))){
					%>
							<%=jmlbmc.get(k) %>
					<%
						}
					}
				
				%>
                </TD>
                <TD class=2-td2-left noWrap> &nbsp;<%=fljmmj[i] %> </TD>
                <TD class=2-td2-left noWrap>&nbsp;<%=fljmse[i] %></TD>
               
                <TD class=2-td2-center noWrap><SPAN><U>删除</U></SPAN> </TD></TR>
                  <%
	              }
	              
	              %>
                  </TBODY>
                  
                  
                  <tr>
                  <td colspan=5><span><FONT color=#000000 size=1><STRONG>申报减免理由：</STRONG></FONT> </span></td>
                 
                  </tr><tr>
                     <td colspan =5>
                     <textarea id =jmsyj  name ="jmsyj"  style="width:98%" rows = 5 readonly ><bean:write name="sydjxxlrForm" property="jmsyj"/></textarea>
                  </td>
                  
                  </tr>
                  
                  
              </TABLE><BR>
            	
            	
            	<%
            }
 			
 			%>
 			
		 	
              <TABLE id="" class=table-99 cellSpacing=0 cellPadding=0 width="80%" align=center border=0>
              
              <tr>
                  <td colspan=5><span><FONT color=#000000 size=1><STRONG>备注：</STRONG></FONT></span></td>
                 
                  </tr><tr>
                     <td colspan =4>
            		<textarea id =bzms  name ="bzms"  style="width:98%" rows = 5 readonly ><bean:write name="sydjxxlrForm" property="bzms"/></textarea>
                  </td>
                  
                  </tr>       
              
              <TR class=black9>
                <TD class=2-td2-t-left  noWrap colspan=2>是否市局审核</TD>
                
                <TD class=2-td2-t-center noWrap colspan=2>
                 <logic:equal value="0" name ="sydjxxlrForm" property="sfsjsp">
              	 否
                 </logic:equal>
                 <logic:equal value="1" name ="sydjxxlrForm" property="sfsjsp">
              	是
                 </logic:equal>
                </TD>
                </TR>
                
                 <TR class=black9>
                <TD class=2-td2-left  noWrap colspan=2>审核状态</TD>
                
                <TD class=2-td2-center noWrap colspan=2>
                  <%
                if(form.getShzt().equals("10")){
                	%>
                	未审核
                	<%
                	}else if(form.getShzt().equals("30")){
                	%>
                	区县已审核
                	<%
                	}else{
                		%>
                		市局已审核
                		<%
                	}
                %>     
                </TD>
                </TR>
                <tr>
                <TD class=2-td2-left noWrap width="25%">区县审核人</TD>
                <TD class=2-td2-left noWrap width="25%">&nbsp;<bean:write name="sydjxxlrForm" property="qrr"/></TD>
                <TD class=2-td2-left noWrap width="25%">区县审核时间</TD>
                <TD class=2-td2-center noWrap width="25%">&nbsp;<bean:write name="sydjxxlrForm" property="qrrq"/></TD>
                </TR>
                <%
                	if(form.getSfsjsp().equals("1")){
                %>
                <TR class=black9>
                <TD class=2-td2-left noWrap>市局审核人</TD>
                <td class=2-td2-left noWrap>&nbsp;<bean:write name="sydjxxlrForm" property="sjqrr"/>
                </td>
                <TD class=2-td2-left noWrap width="25%">市局审核时间</TD>
                <TD class=2-td2-center noWrap width="25%">&nbsp;<bean:write name="sydjxxlrForm" property="sjqrrq"/></TD>
                </TR>
                		<%
                	}
                %>
                <TR class=black9>
                <TD class=2-td2-left noWrap>创建人</TD>
                <td class=2-td2-left noWrap>&nbsp;<bean:write name="sydjxxlrForm" property="cjr"/>
                </td>
                <TD class=2-td2-left noWrap width="25%">创建时间</TD>
                <TD class=2-td2-center noWrap width="25%">&nbsp;<bean:write name="sydjxxlrForm" property="cjsj"/></TD>
                </TR>
           </TABLE><BR> 
           
 		
           
           <script type="text/javascript">
           function qxsh(){
        	   document.forms[0].actionType.value = "Qxsh";
        		document.forms[0].submit();  
           }
           
           
           
           function sjsh(){
        	   var sjqrsj = prompt("请输入市局确认时间(yyyymmdd)：","");
        	   var a = /^([\d]{4}(((0[13578]|1[02])((0[1-9])|([12][0-9])|(3[01])))|(((0[469])|11)((0[1-9])|([12][1-9])|30))|(02((0[1-9])|(1[0-9])|(2[1-8])))))|((((([02468][048])|([13579][26]))00)|([0-9]{2}(([02468][048])|([13579][26]))))(((0[13578]|1[02])((0[1-9])|([12][0-9])|(3[01])))|(((0[469])|11)((0[1-9])|([12][1-9])|30))|(02((0[1-9])|(1[0-9])|(2[1-9])))))$/;
        	   if(sjqrsj == null || sjqrsj==""){
        		   return false;
        	   }
        	   if(sjqrsj.length>8){
        		   alert("输入时间长度不正确");
        		   return false;
        	   }
      		 if(!a.test(sjqrsj)){
      			 alert("时间格式不正确");
      			 return false;
      		 }
        	   	document.forms[0].sjqrsj.value =sjqrsj;
        	   	document.forms[0].actionType.value = "Sjsh";
        		document.forms[0].submit();  
           }
           function addbz(){
        		document.forms[0].actionType.value = "Addbz";
        		document.forms[0].submit();  
           }
           
           function bianji(){
        	   
        	   var jms_flag = document.getElementById("jms_flag").value;
        	   var jks_flag = document.getElementById("jks_flag").value;
        	   var zwbs_flag = document.getElementById("zwbs_flag").value;
        	   if("jms_flag" == jms_flag){
        		   alert("请先撤销减免税证明再进行变更！");
        		   return false;
        	   }
        	   if("jks_flag" == jks_flag){
        		   alert("请先撤销缴款书再进行变更！");
        		   return false;
        	   }
        	   if("zwbs_flag" == zwbs_flag){
        		   alert("请先进行退税然后重新申报！");
        		   return false;
        	   }
        	   document.forms[0].actionType.value = "Bgcx";
       		   document.forms[0].submit();  
           }
           
           
           
           
           <%
           if("qxsh_success".equals(form.getFlag())){
           %>
           alert("区县审核成功");
           <%
           } else if("qxsh_false".equals(form.getFlag())){
        	   %>
        	   alert("区县审核失败");
        	   <%
           }else if("sjsh_success".equals(form.getFlag())){
        	   %>
        	   
        	   alert("市局审核成功");
        	   <%
           }else if("sjsh_false".equals(form.getFlag())){
 				%>
        	   
 				 alert("市局审核失败");
        	   <%
           }else{
        	   
           }
           %>
           
           </script>
           
            <TABLE width="100%" border=0>
              <TBODY>
              <%
              String shzt = form.getShzt();
              String sfsjsp = form.getSfsjsp();
              String bg_flag = form.getBg_flag();
              String sh_flag = form.getSh_flag();
              if("bg_flag".equals(bg_flag)){
            	  %> 
            	  <TR>
                  <TD width="34%">&nbsp;</TD>
                    <TD width="8%"><INPUT 
                      onclick="printsbb();return false;" tabIndex=-1 
                      onmouseover="MM_swapImage('dysbb','','<%=static_contextpath%>images/gdzys/gdzys_dysbb2.jpg',1)" 
                      onmouseout=MM_swapImgRestore() id=dysbb style="CURSOR: hand" 
                      accessKey=x height=22 alt=打印申报表 src="<%=static_contextpath%>images/gdzys/gdzys_dysbb1.jpg" 
                      type=image width=79 border=0></TD>
                      
                      <TD width="8%"><INPUT 
                      onclick="addbz();return false;" tabIndex=-1 
                      onmouseover="MM_swapImage('tjbz','','<%=static_contextpath%>images/gdzys/gdzys_tjbz2.jpg',1)" 
                      onmouseout=MM_swapImgRestore() id=tjbz style="CURSOR: hand" 
                      accessKey=x height=22 alt=添加备注 src="<%=static_contextpath%>images/gdzys/gdzys_tjbz1.jpg" 
                      type=image width=79 border=0></TD>
                      
                    <TD width="8%"><INPUT 
                      onclick="bianji();return false;" tabIndex=-1 
                      onmouseover="MM_swapImage('ds1','','<%=static_contextpath%>images/gdzys/gdzys_sybg2.jpg',1)" 
                      onmouseout=MM_swapImgRestore() id=ds1 style="CURSOR: hand" 
                      accessKey=x height=22 alt=税源变更 src="<%=static_contextpath%>images/gdzys/gdzys_sybg1.jpg" 
                      type=image width=79 border=0></TD>
                    <TD width="8%"><INPUT onclick="tuichu();return false;" 
                      tabIndex=-1 
                      onmouseover="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" 
                      onmouseout=MM_swapImgRestore() id=tc1 style="CURSOR: hand" 
                      accessKey=c height=22 alt=退出 src="<%=static_contextpath%>images/tc-c1.jpg" 
                      type=image width=79 border=0></TD>
                    <TD width="34%">&nbsp;</TD></TR>
                <%	  
                	  
              }else if("sh_flag".equals(sh_flag)){
         
              if(shzt.equals("10")){
            	  %>
            	  <TR>
              <TD width="30%">&nbsp;</TD>
                <TD width="8%"><INPUT 
                  onclick="printsbb();return false;" tabIndex=-1 
                  onmouseover="MM_swapImage('dysbb','','<%=static_contextpath%>images/gdzys/gdzys_dysbb2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=dysbb style="CURSOR: hand" 
                  accessKey=x height=22 alt=打印申报表 src="<%=static_contextpath%>images/gdzys/gdzys_dysbb1.jpg" 
                  type=image width=79 border=0></TD>
                  
                  <TD width="8%"><INPUT 
                  onclick="addbz();return false;" tabIndex=-1 
                  onmouseover="MM_swapImage('tjbz','','<%=static_contextpath%>images/gdzys/gdzys_tjbz2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=tjbz style="CURSOR: hand" 
                  accessKey=x height=22 alt=添加备注 src="<%=static_contextpath%>images/gdzys/gdzys_tjbz1.jpg" 
                  type=image width=79 border=0></TD>
                <!-- <td width="8%"><input type="button" value = "添加备注" onclick = "addbz()" /></td> -->
                 <TD width="8%"><INPUT onclick="qxsh();return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('qxsh1','','<%=static_contextpath%>images/gdzys/gdzys_qxsh2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=qxsh1 style="CURSOR: hand" 
                  accessKey=s height=22 alt=区县审核 src="<%=static_contextpath%>images/gdzys/gdzys_qxsh1.jpg" 
                  type=image width=79 border=0></TD>
              
                <TD width="8%"><INPUT onclick="tuichu();return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=tc1 style="CURSOR: hand" 
                  accessKey=c height=22 alt=退出 src="<%=static_contextpath%>images/tc-c1.jpg" 
                  type=image width=79 border=0></TD>
                <TD width="30%">&nbsp;</TD></TR>
            	  
            	  
            	  <%
              }else if(shzt.equals("30") && sfsjsp.equals("1")){
            	  %>
            	  <TR>
              <TD width="34%">&nbsp;</TD>
               <TD width="8%"><INPUT 
                  onclick="printsbb();return false;" tabIndex=-1 
                  onmouseover="MM_swapImage('dysbb','','<%=static_contextpath%>images/gdzys/gdzys_dysbb2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=dysbb style="CURSOR: hand" 
                  accessKey=x height=22 alt=打印申报表 src="<%=static_contextpath%>images/gdzys/gdzys_dysbb1.jpg" 
                  type=image width=79 border=0></TD>
               
               
                 <TD width="8%"><INPUT 
                  onclick="addbz();return false;" tabIndex=-1 
                  onmouseover="MM_swapImage('tjbz','','<%=static_contextpath%>images/gdzys/gdzys_tjbz2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=tjbz style="CURSOR: hand" 
                  accessKey=x height=22 alt=添加备注 src="<%=static_contextpath%>images/gdzys/gdzys_tjbz1.jpg" 
                  type=image width=79 border=0></TD>
               
               <TD width="8%"><INPUT onclick="sjsh();return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('bc1','','<%=static_contextpath%>images/gdzys/gdzys_sjsh2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=bc1 style="CURSOR: hand" 
                  accessKey=s height=22 alt=市局审核 src="<%=static_contextpath%>images/gdzys/gdzys_sjsh1.jpg" 
                  type=image width=79 border=0></TD> 
               
                <TD width="8%"><INPUT onclick="tuichu();return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=tc1 style="CURSOR: hand" 
                  accessKey=c height=22 alt=退出 src="<%=static_contextpath%>images/tc-c1.jpg" 
                  type=image width=79 border=0></TD>
                <TD width="34%">&nbsp;</TD></TR>
            	  
            	  <%
              }else{
            	  %>
            	  <TR>
              <TD width="28%">&nbsp;</TD>
                 <TD width="8%"><INPUT 
                  onclick="printsbb();return false;" tabIndex=-1 
                  onmouseover="MM_swapImage('dysbb','','<%=static_contextpath%>images/gdzys/gdzys_dysbb2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=dysbb style="CURSOR: hand" 
                  accessKey=x height=22 alt=打印申报表 src="<%=static_contextpath%>images/gdzys/gdzys_dysbb1.jpg" 
                  type=image width=79 border=0></TD>
                
                <TD width="8%"><INPUT 
                  onclick="addbz();return false;" tabIndex=-1 
                  onmouseover="MM_swapImage('tjbz','','<%=static_contextpath%>images/gdzys/gdzys_tjbz2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=tjbz style="CURSOR: hand" 
                  accessKey=x height=22 alt=添加备注 src="<%=static_contextpath%>images/gdzys/gdzys_tjbz1.jpg" 
                  type=image width=79 border=0></TD>
                <TD width="8%"><INPUT onclick="tuichu();return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=tc1 style="CURSOR: hand" 
                  accessKey=c height=22 alt=退出 src="<%=static_contextpath%>images/tc-c1.jpg" 
                  type=image width=79 border=0></TD>
                <TD width="38%">&nbsp;</TD></TR>
            	  
            	  
             <%
              }
           }else{
        	     if(shzt.equals("10")){
            	  %>
            	  <TR>
              <TD width="30%">&nbsp;</TD>
                <TD width="8%"><INPUT 
                  onclick="printsbb();return false;" tabIndex=-1 
                  onmouseover="MM_swapImage('dysbb','','<%=static_contextpath%>images/gdzys/gdzys_dysbb2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=dysbb style="CURSOR: hand" 
                  accessKey=x height=22 alt=打印申报表 src="<%=static_contextpath%>images/gdzys/gdzys_dysbb1.jpg" 
                  type=image width=79 border=0></TD>
                  
                  <TD width="8%"><INPUT 
                  onclick="addbz();return false;" tabIndex=-1 
                  onmouseover="MM_swapImage('tjbz','','<%=static_contextpath%>images/gdzys/gdzys_tjbz2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=tjbz style="CURSOR: hand" 
                  accessKey=x height=22 alt=添加备注 src="<%=static_contextpath%>images/gdzys/gdzys_tjbz1.jpg" 
                  type=image width=79 border=0></TD>
                <!-- <td width="8%"><input type="button" value = "添加备注" onclick = "addbz()" /></td> -->
                 <TD width="8%"><INPUT onclick="qxsh();return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('qxsh1','','<%=static_contextpath%>images/gdzys/gdzys_qxsh2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=qxsh1 style="CURSOR: hand" 
                  accessKey=s height=22 alt=区县审核 src="<%=static_contextpath%>images/gdzys/gdzys_qxsh1.jpg" 
                  type=image width=79 border=0></TD>
                  
                <TD width="8%"><INPUT 
                  onclick="bianji();return false;" tabIndex=-1 
                  onmouseover="MM_swapImage('ds1','','<%=static_contextpath%>images/gdzys/gdzys_sybg2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=ds1 style="CURSOR: hand" 
                  accessKey=x height=22 alt=税源变更 src="<%=static_contextpath%>images/gdzys/gdzys_sybg1.jpg" 
                  type=image width=79 border=0></TD>
                <TD width="8%"><INPUT onclick="tuichu();return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=tc1 style="CURSOR: hand" 
                  accessKey=c height=22 alt=退出 src="<%=static_contextpath%>images/tc-c1.jpg" 
                  type=image width=79 border=0></TD>
                <TD width="30%">&nbsp;</TD></TR>
            	  
            	  
            	  <%
              }else if(shzt.equals("30") && sfsjsp.equals("1")){
            	  %>
            	  <TR>
              <TD width="34%">&nbsp;</TD>
               <TD width="8%"><INPUT 
                  onclick="printsbb();return false;" tabIndex=-1 
                  onmouseover="MM_swapImage('dysbb','','<%=static_contextpath%>images/gdzys/gdzys_dysbb2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=dysbb style="CURSOR: hand" 
                  accessKey=x height=22 alt=打印申报表 src="<%=static_contextpath%>images/gdzys/gdzys_dysbb1.jpg" 
                  type=image width=79 border=0></TD>
               
               
                 <TD width="8%"><INPUT 
                  onclick="addbz();return false;" tabIndex=-1 
                  onmouseover="MM_swapImage('tjbz','','<%=static_contextpath%>images/gdzys/gdzys_tjbz2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=tjbz style="CURSOR: hand" 
                  accessKey=x height=22 alt=添加备注 src="<%=static_contextpath%>images/gdzys/gdzys_tjbz1.jpg" 
                  type=image width=79 border=0></TD>
               
               <TD width="8%"><INPUT onclick="sjsh();return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('bc1','','<%=static_contextpath%>images/gdzys/gdzys_sjsh2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=bc1 style="CURSOR: hand" 
                  accessKey=s height=22 alt=市局审核 src="<%=static_contextpath%>images/gdzys/gdzys_sjsh1.jpg" 
                  type=image width=79 border=0></TD> 
                <TD width="8%"><INPUT 
                  onclick="bianji();return false;" tabIndex=-1 
                  onmouseover="MM_swapImage('ds1','','<%=static_contextpath%>images/gdzys/gdzys_sybg2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=ds1 style="CURSOR: hand" 
                  accessKey=x height=22 alt=税源变更 src="<%=static_contextpath%>images/gdzys/gdzys_sybg1.jpg" 
                  type=image width=79 border=0></TD>
                <TD width="8%"><INPUT onclick="tuichu();return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=tc1 style="CURSOR: hand" 
                  accessKey=c height=22 alt=退出 src="<%=static_contextpath%>images/tc-c1.jpg" 
                  type=image width=79 border=0></TD>
                <TD width="34%">&nbsp;</TD></TR>
            	  
            	  <%
              }else{
            	  %>
            	  <TR>
              <TD width="28%">&nbsp;</TD>
                 <TD width="8%"><INPUT 
                  onclick="printsbb();return false;" tabIndex=-1 
                  onmouseover="MM_swapImage('dysbb','','<%=static_contextpath%>images/gdzys/gdzys_dysbb2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=dysbb style="CURSOR: hand" 
                  accessKey=x height=22 alt=打印申报表 src="<%=static_contextpath%>images/gdzys/gdzys_dysbb1.jpg" 
                  type=image width=79 border=0></TD>
                
                <TD width="8%"><INPUT 
                  onclick="addbz();return false;" tabIndex=-1 
                  onmouseover="MM_swapImage('tjbz','','<%=static_contextpath%>images/gdzys/gdzys_tjbz2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=tjbz style="CURSOR: hand" 
                  accessKey=x height=22 alt=添加备注 src="<%=static_contextpath%>images/gdzys/gdzys_tjbz1.jpg" 
                  type=image width=79 border=0></TD>
                <TD width="8%"><INPUT 
                  onclick="bianji();return false;" tabIndex=-1 
                  onmouseover="MM_swapImage('ds1','','<%=static_contextpath%>images/gdzys/gdzys_sybg2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=ds1 style="CURSOR: hand" 
                  accessKey=x height=22 alt=税源变更 src="<%=static_contextpath%>images/gdzys/gdzys_sybg1.jpg" 
                  type=image width=79 border=0></TD>
                <TD width="8%"><INPUT onclick="tuichu();return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=tc1 style="CURSOR: hand" 
                  accessKey=c height=22 alt=退出 src="<%=static_contextpath%>images/tc-c1.jpg" 
                  type=image width=79 border=0></TD>
                <TD width="38%">&nbsp;</TD></TR>
            	  
            	  
             <%
              }
        	   
           }
              %>
              </TBODY></TABLE><BR></DIV></DIV></DIV></DIV></TD></TR></TBODY></TABLE><BR></FORM>
      <TABLE height=33 cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD height=9 background=<%=static_contextpath%>images/q-bottom-bg-01.jpg colSpan=2 
          noWrap></TD></TR>
        <TR>
          <TD noWrap><FONT size=2>&nbsp;&nbsp;&nbsp;* 必须使用 IE 5.5 以上，分辨率 
            800*600 浏览本网站</FONT></TD>
          <TD height=24 align=right><IMG alt=承办单位：清华同方软件股份有限公司2008_APR11_01 
            src="<%=static_contextpath%>images/q-bottom-tu-01.jpg" width=184 
        height=24></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
</body>
</html:form>
</html:html>