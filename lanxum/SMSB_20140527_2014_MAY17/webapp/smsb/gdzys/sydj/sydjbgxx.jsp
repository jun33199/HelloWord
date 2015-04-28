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
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model.SBMXModel" %>
<%@ page import="com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model.JMXXModel" %>
<%@ page import= "com.ttsoft.bjtax.sfgl.common.util.LabelValueBean" %>
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
	FONT-SIZE: 9pt; BORDER-TOP: 0px; BORDER-RIGHT: 0px; BACKGROUND: #f3f5f8; BORDER-BOTTOM: 0px; TEXT-ALIGN: left; BORDER-LEFT: 0px
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
<%
GdzysSydjxxlrForm form = (GdzysSydjxxlrForm)request.getAttribute("sydjxxlrForm");
if("".equals(form.getYhdm())){
	form.setYhdm("0");
}
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



function fnonload(){
	is_nsrlx_display();
	display_jmsxx();
	getDJXX();
}

//根据纳税人类型显示不同输入项
function is_nsrlx_display(){
	var val = document.getElementById("nsrlx").value;
	var qy = document.getElementById("qy");
	var gr = document.getElementById("gr");
	var tr_jsjdm = document.getElementById("tr_jsjdm");
	if(val == 0){
		tr_jsjdm.style.display = "inline";
		qy.style.display="inline";
		gr.style.display="none";
	}else{
		tr_jsjdm.style.display = "none";
		qy.style.display="none";
		gr.style.display="inline";
	}



}


//根据纳税人类型显示不同输入项
function is_nsrlx_display(){
	var val = document.getElementById("nsrlx").value;
	var qy = document.getElementById("qy");
	var gr = document.getElementById("gr");
	var tr_jsjdm = document.getElementById("tr_jsjdm");
	if(val == 0){
		tr_jsjdm.style.display = "inline";
		qy.style.display="inline";
		gr.style.display="none";
	}else{
		tr_jsjdm.style.display = "none";
		qy.style.display="none";
		gr.style.display="inline";
	}



}

//添加行--占地用途
	function insertrow_zdyt(){
	var obj = document.getElementById("zdyt_hidden");
	obj.style.display="inline";
	obj.disabled=false;
	var newrow = document.all.zdyt_hidden.rows[0].cloneNode(true);
	document.all("ZDYT_ROWS").appendChild(newrow); //添加刚才克隆的一行
	var len = document.all.ZDYT_ROWS.rows;
	var xh = document.getElementsByName("zdytxh");
	for(var i=1;i<=len.length;i++){
		xh[i].value=i+1;
	}
	obj.style.display="none";
	obj.disabled=true;
	//合计
	hj();
	
	}
//删除行---占地用途
function delRow_zdyt(obj){
	var len = document.getElementById("ZDYT_ROWS").rows.length;
	var row = obj.parentNode.parentNode.rowIndex; //A标签所在行
	if(confirm("你确认要删除吗？")){
		ZDYT_ROWS.deleteRow(Number(row)-1);
		var len = document.all.ZDYT_ROWS.rows;
		var xh = document.getElementsByName("zdytxh");
		for(var i=0;i<=len.length;i++){
			xh[i].value=i+1;
		}
		hj();
		
	}else{
		return false;	
	}

}

//添加行--占地用途
function insertrow_jmlb(){
var obj = document.getElementById("jmlb_hidden");
obj.style.display="inline";
obj.disabled=false;
var newrow = document.all.jmlb_hidden.rows[0].cloneNode(true);
document.all("JMLB_ROWS").appendChild(newrow); //添加刚才克隆的一行
var len = document.all.JMLB_ROWS.rows;
var xh = document.getElementsByName("jmlbxh");
for(var i=1;i<=len.length;i++){
	xh[i].value=i+1;
}
/* var obj2 = document.getElementById("jmlbxh");
obj2.value=jmlbxh+1; */
obj.style.display="none";
obj.disabled=true;

}
//删除行---占地用途
function delRow_jmlb(obj){
var len = document.getElementById("JMLB_ROWS").rows.length;
var row = obj.parentNode.parentNode.rowIndex; //A标签所在行
if(confirm("你确认要删除吗？")){
	JMLB_ROWS.deleteRow(Number(row)-1);
	var len = document.all.JMLB_ROWS.rows;
	var xh = document.getElementsByName("jmlbxh");
	for(var i=0;i<=len.length;i++){
		xh[i].value=i+1;
	}
	getJMSYJ();
}else{
	return false;	
}

}




//计算
function jisuan(obj){
	//校验面积
	check_mj();
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
//面积的小数点尾数
function check_mj(){
	 var a = /^(\d+.?\d{0,2})$/;
	 var jsmj = document.getElementsByName("jsmj");
	var jmmj = document.getElementsByName("jmmj");
	if(jsmj.length<1){
		return false;
	}else{
		for(var i=0;i<jsmj.length;i++){
			var obj =jsmj[i]; 
			if(obj.value == ""){
				obj.value="0.00";
				return false;
			}
			if (!a.test(obj.value)) { 
				 alert("计税面积最多只能是两位小数!"); 
				 obj.focus();
				 return false; 
			 }
			var obj2 = jmmj[i];
			if(obj2.value == ""){
				obj2.value="0.00";
				return false;
			}
			if (!a.test(obj2.value)) { 
				 alert("减免面积最多只能是两位小数!"); 
				 obj2.focus();
				 return false; 
			 }
		}
		
		
	}
	  
} 

//计算计征税额
function jzse(){
	var syse = document.getElementsByName("syse");
	var jsmj = document.getElementsByName("jsmj");
	var jzse = document.getElementsByName("jzse");
	for(var i=0;i<jzse.length;i++){
		jzse[i].value =  parseFloat("0.00");
		jzse[i].value = (parseFloat(syse[i].value.substring(3)) * parseFloat(jsmj[i].value)).toFixed(2);
	}

}

//计算减免税额
function jmse(){
	var jsmj = document.getElementsByName("jsmj");
	var syse = document.getElementsByName("syse");
	var jmmj = document.getElementsByName("jmmj");
	var jmse = document.getElementsByName("jmse");
	for(var i=0;i<jmse.length-1;i++){
		if(parseFloat(jsmj[i].value)<parseFloat(jmmj[i].value)){
			alert("计税面积不能小于减免面积！");
			return false;
		}
		
		jmse[i].value =  parseFloat("0.00");
		if(jmmj[i].value =="" ){
			jmmj[i].value = "0.00";
		}
		jmse[i].value = (parseFloat(syse[i].value.substring(3)) * parseFloat(jmmj[i].value)).toFixed(2);
	}

}
//计算应税面积
function ysmj(){
	var jsmj = document.getElementsByName("jsmj");
	var jmmj = document.getElementsByName("jmmj");
	var ysmj = document.getElementsByName("ysmj");
	for(var i=0;i<jsmj.length;i++){
		ysmj[i].value =  parseFloat("0.00");
		ysmj[i].value = (parseFloat(jsmj[i].value) - parseFloat(jmmj[i].value)).toFixed(2);
		
	}
	
}
//计算应纳税额
function ynse(){
	var jzse = document.getElementsByName("jzse");
	var jmse = document.getElementsByName("jmse");
	var ynse = document.getElementsByName("ynse");
	for(var i=0;i<ynse.length;i++){
		ynse[i].value =  parseFloat("0.00");
		ynse[i].value = (parseFloat(jzse[i].value) - parseFloat(jmse[i].value)).toFixed(2);
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
	document.getElementById("hj_jsmj").value=jsmj_sum.toFixed(2);
	document.getElementById("hj_jzse").value="";
	document.getElementById("hj_jzse").value=jzse_sum.toFixed(2);
	document.getElementById("hj_jmmj").value="";
	document.getElementById("hj_jmmj").value=jmmj_sum.toFixed(2);
	document.getElementById("hj_jmse").value="";
	document.getElementById("hj_jmse").value=jmse_sum.toFixed(2);
	document.getElementById("hj_ysmj").value="";
	document.getElementById("hj_ysmj").value=ysmj_sum.toFixed(2);
	document.getElementById("hj_ynse").value="";
	document.getElementById("hj_ynse").value=ynse_sum.toFixed(2);
}

//======================================================================================================================================================

//减免类别
//输入计算机代码获取登记信息
	var yhdm = new Array();
	var yhmc = new Array();
	var yhzh = new Array();
	
 function getDJXX(){
	   yhdm.splice(0,yhdm.length);
	   yhmc.splice(0,yhmc.length);
	   yhzh.splice(0,yhzh.length);
	   
	   var xmlhttp_request;
	   var jsjdm = document.getElementById("jsjdm");
	   if(jsjdm.value=="" || jsjdm==null){
		   return false;
	   }
	   var nsrlxdm = document.getElementById("jsjdm");
	   if(window.ActiveXObject){
	  	       xmlhttp_request=new ActiveXObject("Microsoft.XMLHTTP");
	  	    }else if(window.XMLHttpRequest){
	  	       xmlhttp_request=new XMLHttpRequest();
	  	     } else {
	  	       return false;
	  	   }
	  	     xmlhttp_request.open("GET", "sydjxxlrAction.do?actionType=getDJXX&jsjdm="+jsjdm.value+"&nsrlxdm="+nsrlxdm.value, true);
	       	 xmlhttp_request.send(null);
	  	     xmlhttp_request.onreadystatechange =function zdyprocessAjaxResponse(){
	  	      var xmlDOM;
	          if (xmlhttp_request.readyState == 4) {
	          if (xmlhttp_request.status == 200) {
	         xmlDOM = xmlhttp_request.responseXML;
	         
	         /*  var leng = xmlDOM.getElementsByTagName("nsrmc").length;
	       		var obj_bc = document.getElementById("baocun");
	       		if(leng<1){
	       			alert("请先进行税务登记！");
	       			obj_bc.style.visibility="hidden";
	       		}else{
	       			obj_bc.style.visibility="visible";
	       		} */
	         
	         
	         
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
	       	 
	     	var obj2 = document.getElementById("khyhdm");
	       	 obj2.style.width="25%";
	       	var len = obj2.options.length;
        	for(var i = 0;i<len;i++){
        		obj2.options.remove(i);
        		i--;
        		len--;
        	}
        	var len2 = xmlDOM.getElementsByTagName("dm").length;
        	var yhdm_selected =document.getElementById("selected_yhdm").value;
        	var yhzh_selected =document.getElementById("yhzh").value;
     		for(var j=0;j<len2;j++){
     			yhdm[j]= xmlDOM.getElementsByTagName("dm")[j].firstChild.data;
     			yhmc[j]= xmlDOM.getElementsByTagName("mc")[j].firstChild.data;
     			yhzh[j]= xmlDOM.getElementsByTagName("zh")[j].firstChild.data;
     			oOption = document.createElement("option");
     		 	oOption.text= xmlDOM.getElementsByTagName("mc")[j].firstChild.data;
  	          	oOption.value= xmlDOM.getElementsByTagName("dm")[j].firstChild.data;
  	          	
     			if(xmlDOM.getElementsByTagName("dm")[j].firstChild.data ==yhdm_selected && xmlDOM.getElementsByTagName("zh")[j].firstChild.data == yhzh_selected){
     				oOption.selected =true;
     			}
  	          	obj2.add(oOption);
     		}
     		
	         
	       }
	     }
	  	 };
}

//银行选择
	function getyh(){
		var obj1 = document.getElementById("khyh");
	    var obj2 = document.getElementById("yhzh");
		var obj3 = document.getElementById("khyhdm");
		for(var i=0;i<yhdm.length;i++){
			if(obj3.selectedIndex == i){
			obj1.value="";
			obj1.value=yhmc[i];
			obj2.value="";
			obj2.value=yhzh[i];
			
		}
	
	}
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
	  	       return false;
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
		     		 //计算税额
		   	  	 jisuan();  
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
	   if(jmslb.length ==1){
		   var obj = document.getElementById("jmsyj");
   		   obj.value="";
		   return false;
	   }
	   if(jmslb.length == 2 && jmslb[0].value == ""){
		   var obj = document.getElementById("jmsyj");
       		   obj.value="";
       		   return false;
	   }
	   for(var i=0;i<jmslb.length-1;i++){
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
	        	obj.value="";
	        	var yj = xmlDOM.getElementsByTagName("jmsyjmc")[0].firstChild.data;
	        	obj.value = yj;
	     		}
	       }
	  	 };
}


//获取自然人
 function getZRR(){
	   var xmlhttp_request;
	   var dm = document.getElementById("sfzzlxdm").value;
	   var hm = document.getElementById("sfzzhm").value;
	   if(window.ActiveXObject){
	  	       xmlhttp_request=new ActiveXObject("Microsoft.XMLHTTP");
	  	    }else if(window.XMLHttpRequest){
	  	       xmlhttp_request=new XMLHttpRequest();
	  	     } else {
	  	       return false;
	  	   }
	  	     xmlhttp_request.open("GET", "sydjxxlrAction.do?actionType=getZRR&sfzzlxdm="+dm+"&sfzzhm="+hm, true);
	       	 xmlhttp_request.send(null);
	  	     xmlhttp_request.onreadystatechange = function jmsyjprocessAjaxResponse(){
	          if (xmlhttp_request.readyState == 4) {
	          if (xmlhttp_request.status == 200) {
	        	var xmlDOM = xmlhttp_request.responseXML;
	        	var zrr = xmlDOM.getElementsByTagName("dm")[0].firstChild.data;
	        	var obj_bc = document.getElementById("baocun");
	        	if(zrr == '0'){
	        		alert("请进行自然人登记！");
	        		obj_bc.style.visibility="hidden";
	        		return false;
	        	}else{
	        		obj_bc.style.visibility="visible";
	        	}
	        	/* var leng = xmlDOM.getElementsByTagName("jsjdm").length;
	       		var obj_bc = document.getElementById("baocun");
	       		if(leng<1){
	       			obj_bc.style.visibility="hidden";
	       		}else{
	       			obj_bc.style.visibility="visible";
	       		} */
	        	
	        	 var obj = document.getElementById("nsrmc");
		         if(xmlDOM.getElementsByTagName("mc")[0].firstChild.data ==""){
		        	 obj.value=""
		         }else{
		        	 obj.value = xmlDOM.getElementsByTagName("mc")[0].firstChild.data;
			       	 obj.text = xmlDOM.getElementsByTagName("mc")[0].firstChild.data;
		         }
		       	 var obj = document.getElementById("nsrxxdz");
		       	 if(xmlDOM.getElementsByTagName("dz")[0].firstChild.data =="null"){
		       	    obj.value = "";
		       	 }else{
		       		 obj.value = xmlDOM.getElementsByTagName("dz")[0].firstChild.data;
		       	 }
		       	 
		       	var obj = document.getElementById("jsjdm");
		       	 if(xmlDOM.getElementsByTagName("jsjdm")[0].firstChild.data ==""){
		       	    obj.value = "";
			       	 obj.text = "";
		       	 }else{
		       		 obj.value = xmlDOM.getElementsByTagName("jsjdm")[0].firstChild.data;
			       	 obj.text = xmlDOM.getElementsByTagName("jsjdm")[0].firstChild.data;
		       	 }
		        
	     		}
	       }
	  	 };
}
//验证时间
 function checkdate(){
	 var a = /^\d{8}$/;
	 if (!a.test(document.getElementById("zdsj").value)) { 
	 alert("日期格式不正确!");
	 return false; 
	 }else{
		 return true;
	 } 
	 
} 

 //只输入数值或两位小数
 //验证数值
 function check_number(obj){
	 var a = /^(\d+.?\d{0,2})$/;
	 if(""==obj.value){
		 obj.focus();
		 return false;
	 }
	 if (!a.test(obj.value)) { 
		 alert("实际占地面积最多只能是两位小数!"); 
		 obj.focus();
		 return false; 
	 }else{
		 return true;
	 }
	 
}

//验证数值
 function checkdnumber(){
	 var a = /^\d+(.)*\d*$/;
	 if (!a.test(document.getElementById("sjzdmj").value)) { 
		 alert("实际占地面积不正确!"); 
		 return false; 
	 }else{
		 return true;
	 }
	 
} 
//验证减免税额
 function yzjmse(){
	var bl=true;
 	var jsmj = document.getElementsByName("jsmj");
 	var syse = document.getElementsByName("syse");
 	var jmmj = document.getElementsByName("jmmj");
 	var jmse = document.getElementsByName("jmse");
 	for(var i=1;i<jmse.length;i++){
 		if(parseFloat(jsmj[i].value)<parseFloat(jmmj[i].value)){
 			alert("计税面积不能小于减免面积！");
 			bl= false;
 		}
 		
 	}
 	return bl;

 }
 
 
 function zdsj_check(){
	 var a = /^([\d]{4}(((0[13578]|1[02])((0[1-9])|([12][0-9])|(3[01])))|(((0[469])|11)((0[1-9])|([12][1-9])|30))|(02((0[1-9])|(1[0-9])|(2[1-8])))))|((((([02468][048])|([13579][26]))00)|([0-9]{2}(([02468][048])|([13579][26]))))(((0[13578]|1[02])((0[1-9])|([12][0-9])|(3[01])))|(((0[469])|11)((0[1-9])|([12][1-9])|30))|(02((0[1-9])|(1[0-9])|(2[1-9])))))$/;
	 var zdsj = document.getElementById("zdsj").value;
	 if(zdsj.length>8){
		   alert("输入时间长度不正确");
		   return false;
	   }else{
	 if(!a.test(zdsj)){
		 alert("占地时间格式不正确");
		 return false;
	 }else{
		 return true;
	 }
	   }
 }

 function check_jmmx(){
	 var val = document.getElementsByName("jmlbdm_jmmx");
	 for(var i=0;i<val.length-1;i++){
		 for(var j=i+1;j<val.length-1;j++){
			 if(val[i].value == val[j].value){
				 return false;
			 }
		 }
	 }
	 return true;
 }
 
//保存 
function befSave(flag){
	//验证减免类别
	 if(!check_jmmx()){
		 alert("减免类别不能重复！");
		return false;
	} 
	var jsmj_val = document.getElementsByName("jsmj");

	for(var j = 0;j<jsmj_val.length-1;j++){
		if(Number(jsmj_val[j].value) == 0){
			alert("计税面积只能大于零！");
			return false;
		}
		
	}
	
	 var sjzdmj = document.getElementById("sjzdmj").value;
	 var jsmj = document.getElementById("hj_jsmj").value;
	 
	 var jsjdm = document.getElementById("jsjdm").value;
	 var sfzzhm = document.getElementById("sfzzhm").value;
	 var lxrxm = document.getElementById("lxrxm").value;
	 var lxdh = document.getElementById("lxdh").value;
	 
	 var nsrlx = document.getElementById("nsrlx").value;
	 
	 var tdzldz = document.getElementById("tdzldz").value;
	 var jsxmmc = document.getElementById("jsxmmc").value;
	 var pzjdwh = document.getElementById("pzjdwh").value;
	 var pzjdmj = document.getElementById("pzjdmj").value;
	 var sjzdmj = document.getElementById("sjzdmj").value;
	 var zdsj = document.getElementById("zdsj").value;
	 
	 //批准占地文号与批准面积同时为空或同时不为空
	if((pzjdmj !="" && pzjdwh !="") || (pzjdmj=="" && pzjdwh == "")){
		
	}else{
		alert("批准占地文号与批准占地面积只能同时都为空或者都不为空");
		return false;
	}
	  if(!zdsj_check()){
		 return false;
	 }
	  
	 if(nsrlx== "1" && sfzzhm=="" ){
		 alert("身份证照号码不能为空！");
		 return false;
	 }
	 if(nsrlx=="0" && jsjdm ==""){
		 alert("计算机代码不能为空！");
		 return false;
	 }
	 if(lxrxm =="" ){
		 alert("联系人姓名不能为空！");
		 return false;
	 }
	 if(lxdh =="" ){
		 alert("联系电话不能为空！");
		 return false;
	 }
	 
	 
	 if(tdzldz ==""){
		 alert("土地坐落地址不能为空！");
		 return false;
	 }
	 if(jsxmmc ==""){
		 alert("建设项目不能为空！");
		 return false;
	 }
	 /* if(pzjdwh ==""){
		 alert("批准占地文号不能为空！");
		 return false; 
	 } */
	/*  if(pzjdmj ==""){
		 alert("批准占地面积不能为空！");
		 return false;
	 } */
	 if(sjzdmj ==""){
		 alert("实际占地面积不能为空！");
		 return false;
	 }
	 if(zdsj ==""){
		 alert("占地时间不能为空！");
		 return false;
	 }
	 
	 
	  if(parseFloat(sjzdmj).toFixed(2)!=parseFloat(jsmj).toFixed(2)){
		 alert("实际占地面积与计税面积不等，请核实！");
		 return false;
	 }
	 
	//验证日期
	if(!checkdate()){
		return false;
	}
	/*  //验证数值
	 if(!checkdnumber()){
		 return false;
	 } */
	 //计税面积减免面积比较
	 if(!yzjmse()){
		 return false;
	 } 
	 var hj_jsmj = document.getElementById("hj_jsmj").value;
	 var hj_jzse = document.getElementById("hj_jzse").value;
	 var hj_jmmj = document.getElementById("hj_jmmj").value;
	 var hj_jmse = document.getElementById("hj_jmse").value;
	 var hj_ysmj = document.getElementById("hj_ysmj").value;
	 var hj_ynse = document.getElementById("hj_ynse").value;
	
	 var sylxdm = document.getElementById("sylxdm").value;
	 var len = document.all.JMLB_ROWS.rows.length;
	 if(sylxdm=='0'){
		 if(parseFloat(hj_jzse).toFixed(2)!=parseFloat(hj_ynse).toFixed(2)){
			 alert("应纳税额与计征税额不等");
			 return false;
		 }
		 if(len>0){
			 alert("全征时不能输入减免税信息");
			 return false;
		 }
		 
	 }else{
		 if(len<1){
			 alert("非全征时必须输入减免税信息");
			 return false;
		 }
		 
		 //全免1
		 if(sylxdm == '1'){
			 if(parseFloat(hj_ynse).toFixed(2)!=0.00 || parseFloat(hj_jzse).toFixed(2) ==0.00){
				 alert("全免时税额不正确");
				 return false;
			 }
			 var fljmmj = document.getElementsByName("fljmmj");
			 var fljmse = document.getElementsByName("fljmse");
			 var fljmmj_sum = 0.00;
			 var fljmse_sum = 0.00;
			 
			 for(var j = 0;j<fljmmj.length-1;j++){
				 
				 fljmmj_sum = Number(fljmmj_sum) + Number(fljmmj[j].value);
				 fljmse_sum = Number(fljmse_sum) + Number(fljmse[j].value);
				 
				}
				
			 if(parseFloat(fljmmj_sum).toFixed(2) != parseFloat(hj_jmmj).toFixed(2) || parseFloat(fljmse_sum).toFixed(2) != parseFloat(hj_jmse).toFixed(2)){
				 alert("分类减免数据与减免数据不符");
				 return false;
			 }
		
			 //有征有免
		 }else{
			 
			 if(Number(hj_jzse)<=Number(hj_jmse)){
				 alert("计征税额应大于减免税额");
				 return false;
			 }

			 var fljmmj = document.getElementsByName("fljmmj");
			 var fljmse = document.getElementsByName("fljmse");
			 var fljmmj_sum = 0;
			 var fljmse_sum = 0;
			 
			 for(var j = 0;j<fljmmj.length-1;j++){
				 fljmmj_sum = Number(fljmmj_sum) + Number(fljmmj[j].value);
				 fljmse_sum = Number(fljmse_sum) + Number(fljmse[j].value);
				 
				}
			 
			 if(parseFloat(fljmmj_sum).toFixed(2) != parseFloat(hj_jmmj).toFixed(2) || parseFloat(fljmse_sum).toFixed(2) != parseFloat(hj_jmse).toFixed(2)){
				 alert("分类减免数据与减免数据不符");
				 return false;
			 }
			 
			 
		 }
		 
		 
		 
		 
	 }

	document.forms[0].actionType.value = "Update";
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


/*  批准占地文号           */

//占地批文号年度
function doOnNianduFocus(){
	var niandu = document.getElementById("pzzdwhnd");                          
	niandu.value="";
	var zdpwh = document.getElementById("pzjdwh");	
	zdpwh.value="";
}
function doOnNianduBlur(){
	var niandu = document.getElementById("pzzdwhnd");
	var zdpwh = document.getElementById("pzjdwh");							  //批文号
	var wenhao = document.getElementById("pzzdwhwh");						  //文号
	var check = true;
	if(niandu.value == "" )
	{
		niandu.value=" 年度";
		//niandu.style="width:50px; color:grey;";
		zdpwh.value="";
		check = false;
		return false;
	}
	if(niandu.value.length != 4 || isNaN(niandu.value))
		{
			alert("输入内容不合法，请重新输入年度");
			niandu.value=" 年度";
			//niandu.style="width:50px; color:grey;";
			zdpwh.value="";
			check = false;
			return false;
		}
	if(wenhao.value.indexOf('文号',0) != -1)
	{
		check = false;
		return false;
	}
	if(check == true)
	{
		var zdpwhval="京政地字["+ niandu.value+"]"+wenhao.value+"号";
		zdpwh.value = zdpwhval;
	}
	
}
//占地批文号文号
function doOnWenhaoFocus(){
	var wenhao = document.getElementById("pzzdwhwh");                          
	wenhao.value="";                                  	  							//年度
	var zdpwh = document.getElementById("pzjdwh");	
	zdpwh.value="";
}
function doOnWenhaoBlur(){
	var niandu = document.getElementById("pzzdwhnd");                          //年度
	var zdpwh = document.getElementById("pzjdwh");							  //批文号
	var wenhao = document.getElementById("pzzdwhwh");						  //文号
	var check = true;
	if(wenhao.value == "" )
	{
		wenhao.value=" 文号";
		//wenhao.style="width:50px; color:grey;";
		zdpwh.value="";
		check = false;
		return false;
	}
	
	if(isNaN(wenhao.value))
		{
			alert("输入内容不合法，请重新输入文号");
			wenhao.value=" 文号";
			//wenhao.style="width:50px; color:grey;";
			zdpwh.value="";
			check = false;
			return false;
		}
	
	if(niandu.value.indexOf('年度',0) != -1)
		{
			check = false;
			return false;
		}
	
	if(check == true)
	{
		var zdpwhval="京政地字["+ niandu.value+"]"+wenhao.value+"号";
		zdpwh.value = zdpwhval;
	}
}






</SCRIPT>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onload="fnonload();">
<html:form action="/webapp/smsb/sydjxxlrAction.do" method="post">
 <%@ include file="../../include/header.jsp"%>
 <%
 if("error".equals(form.getErrors())){
	 %>
            <DIV align=left><FONT color=#FF0000 
            size=2><STRONG>变更失败，请重新变更！</STRONG></FONT> 
	 <%
 }
 %>
<html:hidden property="actionType"/>

<TABLE class=black9 height=300 cellSpacing=0 cellPadding=0 width="90%" 
align=center border=0>
  <TBODY>
  <TR>
    <TD height=300 vAlign=top><BR>
      <TABLE class=table-99 cellSpacing=0 align=center>
        <TBODY>
        <TR>
          <TD class=1-td1 colSpan=2>税源变更</TD></TR>
        <TR>
          <TD class=1-td2 colSpan=2><BR>
          <DIV align=left><FONT color=#000000 
            size=1><STRONG>基本信息：</STRONG></FONT></div>
            <TABLE class=table-99 cellSpacing=0 cellPadding=0 width="80%" border=0 align = center>
              <TBODY>
              <TR class=black9>
                <TD class=2-td2-t-left width="20%" noWrap>申报序列号</TD>
                <TD class=2-td2-t-left noWrap><INPUT 
                  
                name=sbbxlh value =<bean:write name="sydjxxlrForm" property="sbbxlh"/> readonly class=inbright style="TEXT-ALIGN:left"></TD>
                <TD class=2-td2-t-left width="20%" noWrap>纳税人类型</TD>
                <TD class=2-td2-t-center noWrap>
                <SELECT 
                  onchange=is_nsrlx_display() id=nsrlx style="WIDTH: 36%" 
                  name=nsrlx>
                <%
                	if(form.getNsrlx().equals("0")){
                		%>
                		<OPTION selected value=0>单位</OPTION> 
                  		<!--  <OPTION value=1>个人</OPTION>  -->
                		<%
                	}else{
                		
                		%>
                		 <!-- <OPTION value=0>单位</OPTION> --> 
                  		<OPTION value=1 selected>个人</OPTION>
                		<% 
                	}
                %>
                  
                  
                  </SELECT>  
              </TD></TR>
               <TR class=black9 style="display:inline" id = tr_jsjdm>
                <TD class=2-td2-left noWrap>计算机代码</TD>
                <TD class=2-td2-left noWrap><INPUT id = "jsjdm" readonly class=inbright style="TEXT-ALIGN:left"
                 
                name=jsjdm value ='<bean:write name="sydjxxlrForm" property="jsjdm"/>'></TD>
                 <TD class=2-td2-left  noWrap>&nbsp;</TD>
                
                <TD class=2-td2-center noWrap>
                &nbsp;
                </TD>
              
                </TR> 
              <TR>
                <TD class=2-td2-left noWrap>税源类型</TD>
                <TD class=2-td2-left noWrap>
                <SELECT onchange=is_nsrlx_display() id=sylxdm style="WIDTH: 36%" name=sylxdm> 
                <%
                	if(form.getSylxdm().equals("0")){
                		%>
                  		<OPTION selected value=0>全征</OPTION>
                  		<OPTION  value=1>全免</OPTION>
                  		<OPTION  value=2>有征有免</OPTION> 
                		<%
                	}else if(form.getSylxdm().equals("1")){
                		%>
                  		<OPTION value=0 >全征</OPTION>
                  		<OPTION  selected value=1>全免</OPTION>
                  		<OPTION value=2>有征有免</OPTION> 
                		<%
                	}else{
                		%>
                  		<OPTION value=0>全征</OPTION>
                  		<OPTION  value=1 >全免</OPTION>
                		<OPTION selected value=2>有征有免</OPTION> 
                		<%
                	}
                
                %>
                  
                  </SELECT> 
                </TD>
                 <TD class=2-td2-left width="20%" noWrap>税额类型</TD>
                <TD class=2-td2-center noWrap>
                <select id = "syse_nd" onchange="getSyse();" style="WIDTH: 36%" name = "sysend">
                <%
                	if("2008".equals(form.getSysend())){
                		%>
                		<option value="2008" selected>新税额</option>
						<option value= "2007">旧税额</option>  
                		<%
                	}else{
                		%>
                		<option value="2008">新税额</option>
						<option value= "2007" selected>旧税额</option>  
                		<%
                	}
                %>
				     
             	</select>
				</TD>
                </TR> </TBODY>
               <TR>
               <TD class=2-td2-left width="20%" noWrap>纳税人名称</TD>
                <TD class=2-td2-center noWrap colspan=3>
                <INPUT name=nsrmc id="nsrmc" style="width:98%" readonly class=inbright style="TEXT-ALIGN:left" value='<bean:write name="sydjxxlrForm" property="nsrmc"/> '></TD>
              </TR>
              <TR>
                <TD class=2-td2-left width="20%" noWrap>纳税人详细地址</TD>
                <TD class=2-td2-center noWrap colspan=3><INPUT name=nsrxxdz style="width:98%" id = "nsrxxdz" readonly class=inbright style="TEXT-ALIGN:left" value='<bean:write name="sydjxxlrForm" property="nsrxxdz"/> '></TD>
              </TR>
              
             <TBODY id=qy>
              <TR class=black9>
                <TD class=2-td2-left width="20%" noWrap>纳税人所属行业</TD>
                <TD class=2-td2-left noWrap><INPUT type = "hidden" name=nsrsshy id = 'nsrsshy' value='<bean:write name="sydjxxlrForm" property="nsrsshy"/>'>
                <INPUT name=nsrsshymc size ='50%' id = 'nsrsshymc' readonly class=inbright style="TEXT-ALIGN:center" value='<bean:write name="sydjxxlrForm" property="nsrsshymc"/>'></TD>
                <TD class=2-td2-left width="20%" noWrap>企业登记注册类型</TD>
                <TD class=2-td2-center noWrap><INPUT type="hidden" name=qydjzclx id = "qydjzclx" value='<bean:write name="sydjxxlrForm" property="qydjzclx"/>'>
                <INPUT name=qydjzclxmc size ='50%' id = "qydjzclxmc" readonly class=inbright style="TEXT-ALIGN:center" value='<bean:write name="sydjxxlrForm" property="qydjzclxmc"/>'></TD></TR>
           
              <TR>
                <TD class=2-td2-left width="20%" noWrap>纳税人识别号</TD>
                <TD class=2-td2-left noWrap><INPUT size="26%" style="TEXT-ALIGN:center" name=nsrsbh id = nsrsbh readonly class=inbright value='<bean:write name="sydjxxlrForm" property="nsrsbh"/>'> </TD>
                 <TD class=2-td2-left width="20%" noWrap>&nbsp;</TD>
                <TD class=2-td2-center noWrap>&nbsp;
                 </TD></TR>
              <TR class=black9>
              <TD class=2-td2-left width="20%" noWrap>开户银行</TD>
                <TD class=2-td2-left noWrap><FONT COLOR="#FF0000">*</FONT><input id=selected_yhdm type="hidden" value ='<bean:write name="sydjxxlrForm" property="yhdm"/>'/>
                 <INPUT name=khyh id =khyh value='<bean:write name="sydjxxlrForm" property="khyh"/>'><select id = "khyhdm" name="yhdm" style="width:5%" onchange="getyh()">
                
                </select>
                 </TD>
              <TD class=2-td2-left width="20%" noWrap>银行账号</TD>
                <TD class=2-td2-center noWrap><FONT COLOR="#FF0000">*</FONT><INPUT name=yhzh id = yhzh value='<bean:write name="sydjxxlrForm" property="yhzh"/>'></TD>
          
                </TR>
             <TBODY>
              <TR class=black9>
              
              <TD class=2-td2-left width="20%" noWrap>联系人姓名</TD>
                <TD class=2-td2-left noWrap><FONT COLOR="#FF0000">*</FONT><INPUT name=lxrxm id = lxrxm value='<bean:write name="sydjxxlrForm" property="lxrxm"/>'></TD>
                <TD class=2-td2-left width="20%" noWrap>联系电话</TD>
                <TD class=2-td2-center noWrap><FONT COLOR="#FF0000">*</FONT><INPUT name=lxdh id=lxdh value='<bean:write name="sydjxxlrForm" property="lxdh"/>'></TD>
                
                </TR>
               <tbody id = "gr" style="display : none">
              <TR class=black9>
                <TD class=2-td2-left width="20%" noWrap>身份证照类型</TD>
                <TD class=2-td2-left noWrap><FONT COLOR="#FF0000">*</FONT><select name = "sfzzlxdm" style="WIDTH: 36%" id = sfzzlxdm>
                <%
                	if("02".equals(form.getSfzzlxdm())){
                		%>
                		<option value = ""></option>
	                <option value = "02" selected>身份证</option>
	                <option value = "03">军人证件</option>
	                <option value = "04">护照</option>
	                <option value = "05">港澳同胞回乡证</option>
	                <option value = "90">其他</option>
                		
                		<%
                	}else if("03".equals(form.getSfzzlxdm())){
                		%>
                		<option value = ""></option>
	                <option value = "02">身份证</option>
	                <option value = "03" selected>军人证件</option>
	                <option value = "04">护照</option>
	                <option value = "05">港澳同胞回乡证</option>
	                <option value = "90">其他</option>
                		
                		<%
                	}else if("04".equals(form.getSfzzlxdm())){
                		%>
                		<option value = ""></option>
	                <option value = "02">身份证</option>
	                <option value = "03">军人证件</option>
	                <option value = "04" selected>护照</option>
	                <option value = "05">港澳同胞回乡证</option>
	                <option value = "90">其他</option>
                		
                		<%
                	}else if("05".equals(form.getSfzzlxdm())){
                		%>
                		<option value = ""></option>
	                <option value = "02">身份证</option>
	                <option value = "03">军人证件</option>
	                <option value = "04">护照</option>
	                <option value = "05" selected>港澳同胞回乡证</option>
	                <option value = "90">其他</option>
                		
                		<%
                	}else if("90".equals(form.getSfzzlxdm())){
                		%>
                		<option value = ""></option>
	                <option value = "02">身份证</option>
	                <option value = "03">军人证件</option>
	                <option value = "04">护照</option>
	                <option value = "05">港澳同胞回乡证</option>
	                <option value = "90" selected>其他</option>
                		
                		<%
                	}else{
                		%>
                		<option value = "" selected></option>
	                <option value = "02">身份证</option>
	                <option value = "03">军人证件</option>
	                <option value = "04">护照</option>
	                <option value = "05">港澳同胞回乡证</option>
	                <option value = "90">其他</option>
                		
                		<%
                	}
                %>
                	
                </select>
                </TD> 
                <TD class=2-td2-left width="20%" noWrap>身份证照号码</TD>
                <TD class=2-td2-center noWrap><FONT COLOR="#FF0000">*</FONT><INPUT name=sfzzhm id=sfzzhm onblur="getZRR()" value='<bean:write name="sydjxxlrForm" property="sfzzhm"/>'>
                </TD></TR></TBODY>
              </TABLE><BR>
            <DIV align=left><FONT color=#000000 
            size=1><STRONG>土地信息：</STRONG></FONT> 
            <DIV><!--土地信息 -->
           <TABLE id=tdxx class=table-99 cellSpacing=0 cellPadding=0 
            width="80%" align=center border=0>
              <TBODY>
                <TR class=black9>
                <TD class=2-td2-t-left width="20%" noWrap>土地坐落地址</TD>
                <TD class=2-td2-t-center noWrap colspan= 3><FONT COLOR="#FF0000">*</FONT><INPUT name=tdzldz style="width:98%" id = tdzldz value='<bean:write name="sydjxxlrForm" property="tdzldz"/>'></TD>
                </TR>
               <TR>
                <TD class=2-td2-left width="20%" noWrap>建设项目名称</TD>
                <TD class=2-td2-center noWrap colspan = 3><FONT COLOR="#FF0000">*</FONT><INPUT name=jsxmmc style="width:98%" id = jsxmmc value='<bean:write name="sydjxxlrForm" property="jsxmmc"/>'></TD>
                </TR> 
              <TR class=black9>
                <TD class=2-td2-left width="20%" noWrap>批准占地文号</TD>
             <TD class=2-td2-left noWrap><INPUT name=pzjdwh type="hidden" value ="<bean:write name="sydjxxlrForm" property="pzjdwh"/>" id = pzjdwh >
                京政地字[<input type="text"  id="pzzdwhnd" style="width:50px; color:grey;" value='<bean:write name="sydjxxlrForm" property="whnd"/>' 
                onfocus="javascript:doOnNianduFocus()" onblur="javascript: doOnNianduBlur()">]<input type="text" id="pzzdwhwh" style="width:50px; color:grey;" value='<bean:write name="sydjxxlrForm" property="wh"/>' onfocus="javascript:doOnWenhaoFocus()" onblur="javascript:doOnWenhaoBlur()" >号</TD>
                
                <TD class=2-td2-left width="20%" noWrap>批准占地面积（批文农转用面积：平方米）</TD>
                <TD class=2-td2-center noWrap><INPUT name=pzjdmj size='26%' id = pzjdmj value='<bean:write name="sydjxxlrForm" property="pzjdmj"/>'></TD>
                </TR>
               <TR class=black9>
                <TD class=2-td2-left width="20%" noWrap>实际占地面积（与计税面积一致：平方米）</TD>
                <TD class=2-td2-left noWrap width="30%"><FONT COLOR="#FF0000">*</FONT><INPUT size="26%"  id =sjzdmj name=sjzdmj value ='<bean:write name="sydjxxlrForm" property="sjzdmj"/>' onblur="check_number(this)"></TD>
                <TD class=2-td2-left width="20%" noWrap>批准时间/实际占地时间(格式：YYYYMMDD)</TD>
                <TD class=2-td2-center noWrap width="30%"><FONT COLOR="#FF0000">*</FONT><INPUT size="26%"  name=zdsj id=zdsj onblur="zdsj_check()" value='<bean:write name="sydjxxlrForm" property="zdsj"/>'></TD></TR> 
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
              <%
            
              List zdyt = (List)form.getZdyt_list();
              List sbmxlist = (List)form.getSbmxlist();
              int m=1;
              %>
              <%
            	  for(int i=0;i<sbmxlist.size();i++){
            		  SBMXModel model = (SBMXModel)sbmxlist.get(i);  
            		  %>
            		  <TR class=black9 >
                		<TD class=2-td2-left noWrap><input  name= zdytxh value=<%=m %> size =2 class=inbright readOnly/></TD>
            		    <TD class=2-td2-left noWrap>
            			  <select name="zdyt">
            		  <%
            		  for(int j=0;j<zdyt.size();j++){
            			  LabelValueBean bean = (LabelValueBean)zdyt.get(j);
            			  if(model.getZdyt().equals(bean.getValue())){
            				  %>
                			  <option value="<%=  bean.getValue()%>" selected><%=bean.getLabel() %></option>
                			  <%
            			  }else{
            				  %>
                			  <option value="<%=  bean.getValue()%>"><%=bean.getLabel() %></option>
                			  <%
            			  }
            			  
            		  }
            		  %>
            		    </select>
                		</TD>
                		
                		<TD class=2-td2-left noWrap>
                		<select name="syse" style="WIDTH: 95%" onchange="jisuan(this)">
						<%
						List syse = (List)form.getSyse_list();
						for(int j=0;j<syse.size();j++){
							 LabelValueBean bean = (LabelValueBean)syse.get(j);
							 if(model.getSyse().equals(bean.getLabel())){
								  //if(model.getSyse()=(bean.getLabel())){
								 %>
									<option value="<%=bean.getValue() %>" selected><%=bean.getLabel() %></option>
									<%
							 }else{
								 %>
									<option value="<%=bean.getValue() %>"><%=bean.getLabel() %></option>
								<%
							 }
							
						}
						%>
						</select>
						</TD>
               			<TD class=2-td2-left noWrap><INPUT onblur=jisuan(this); size=12 style ="TEXT-ALIGN: right;"
                  value=<%=model.getJsmj() %> name=jsmj> </TD>
                		<TD class=2-td2-left noWrap><INPUT size=12 name=jzse class=inbright readOnly style ="TEXT-ALIGN: right;" value= <%=model.getJzse() %>> </TD>
                		<TD class=2-td2-left noWrap> <INPUT size=12 name=jmmj value ="<%=model.getJmmj() %>" onblur=jisuan(this); style ="TEXT-ALIGN: right;" /></TD>
                		<TD class=2-td2-left noWrap><INPUT  size=12  class=inbright readOnly
                  value="<%=model.getJmse() %>" name=jmse style ="TEXT-ALIGN: right;"> </TD>
                		<TD class=2-td2-left noWrap><INPUT class=inbright readOnly 
                  size=12 value=<%=model.getYsmj() %> name=ysmj style ="TEXT-ALIGN: right;"/> </TD>
                		<TD class=2-td2-left noWrap><INPUT class=inbright readOnly 
                  size=12 value=<%=model.getYnse() %> name=ynse style ="TEXT-ALIGN: right;"> </TD>
                		<TD class=2-td2-center noWrap><SPAN 
                  onclick=delRow_zdyt(this) onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>删除</U></SPAN> </TD></TR>
                		
            <%
            m++;
           }
             %>
             
             
            
             
		</TBODY>
              <TBODY>
              <TR class=black9>
                <TD class=2-td2-left noWrap>合计 </TD>
                <TD class=2-td2-left noWrap>&nbsp; </TD>
                <TD class=2-td2-left noWrap>&nbsp; </TD>
                <TD class=2-td2-left noWrap><INPUT id=hj_jsmj name = "hj_jsmj"
                  class=inbright readOnly size=12 value='<bean:write name="sydjxxlrForm" property="hj_jsmj"/>'></TD>
                <TD class=2-td2-left noWrap><INPUT id=hj_jzse name = "hj_jzse"
                  class=inbright readOnly size=12 value='<bean:write name="sydjxxlrForm" property="hj_jzse"/>'></TD>
                <TD class=2-td2-left noWrap><INPUT id=hj_jmmj name = "hj_jmmj"
                  class=inbright readOnly size=12 value='<bean:write name="sydjxxlrForm" property="hj_jmmj"/>'></TD>
                <TD class=2-td2-left noWrap><INPUT id=hj_jmse name = "hj_jmse"
                  class=inbright readOnly size=12 value='<bean:write name="sydjxxlrForm" property="hj_jmse"/>'></TD>
                          
                <TD class=2-td2-left noWrap><INPUT id=hj_ysmj name = "hj_ysmj"
                  class=inbright readOnly size=12 value='<bean:write name="sydjxxlrForm" property="hj_ysmj"/>'> </TD>
                <TD class=2-td2-left noWrap><INPUT id=hj_ynse name = "hj_ynse"
                  class=inbright readOnly size=12 value='<bean:write name="sydjxxlrForm" property="hj_ynse"/>'> </TD>
                <TD class=2-td2-center noWrap>&nbsp; </TD></TR></TBODY>
                <tr class=black9>
                <td  class=2-td2-left noWrap colspan=10><SPAN onclick="insertrow_zdyt()" 
                  onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>增加</U></SPAN>&nbsp;</td></tr>
                
                </TABLE><BR>
                
                <script>
                function display_jmsxx(){
                	if(document.getElementById("jmsxx").checked){
                		document.getElementById("JMLB_LIST").style.display="inline";
                	}else{
                		document.getElementById("JMLB_LIST").style.display="none";
                	}
                	
                }
                </script>
                
 			<DIV align=left>
 			
 			<FONT color=#000000 size=1><STRONG>减免税信息：<input type="checkbox" name="jmsxx" id= "jmsxx" onclick="display_jmsxx()"/></STRONG></FONT></div>
 			<TABLE id="JMLB_LIST" class=table-99 cellSpacing=0 cellPadding=0 width="80%" align=center border=0 style="display:none">
              <TBODY>
              <TR class=black9>
                <TD class=2-td2-t-left width="5%" noWrap>序号</TD>
                <TD class=2-td2-t-left width="30%" noWrap>减免类别</TD>
                <TD class=2-td2-t-left width="20%" noWrap>分类减免面积</TD>
                <TD class=2-td2-t-left width="20%" noWrap>分类减免税额</TD>
                <TD class=2-td2-t-center width="25%" noWrap>操作</TD></TR>
                
               
              <TBODY id="JMLB_ROWS">
              <%
              	Map map2 = (Map)form.getJmmx_map();
  				JMXXModel js = (JMXXModel)map2.get("00");
  				JMXXModel nc = (JMXXModel)map2.get("01");
  				JMXXModel kn = (JMXXModel)map2.get("02");
  				JMXXModel yly = (JMXXModel)map2.get("03");
  				JMXXModel yy = (JMXXModel)map2.get("04");
  				JMXXModel yey = (JMXXModel)map2.get("05");
  				JMXXModel xx1 = (JMXXModel)map2.get("0601");
  				JMXXModel xx2 = (JMXXModel)map2.get("0602");
  				JMXXModel qt2 = (JMXXModel)map2.get("07");
  				int n=1;
              %>
              
               <%
              if(js.getFljmmj() ==null || "".equals(js.getFljmmj())){
              }else{
            	  %>
            	 <script >
            	  document.getElementById("jmsxx").checked=true;
            	  </script>
            	   <TR class=black9>
                <TD class=2-td2-left noWrap><input name =jmlbxh value=<%=n++ %> size =2 class=inbright readOnly /> </TD>
 				<TD class=2-td2-left noWrap>
            	<select name="jmlbdm_jmmx" onchange="getJMSYJ();">
            		  <%
            		  List jmlblist = (List)form.getJmlb_list();
            		  for(int j=0;j<jmlblist.size();j++){
            			  LabelValueBean bean = (LabelValueBean)jmlblist.get(j);
            			  if("00".equals(bean.getValue())){
            				  %>
                			  <option value="<%=  bean.getValue()%>" selected><%=bean.getLabel() %></option>
                			  <%
            			  }else{
            				  %>
                			  <option value="<%=  bean.getValue()%>"><%=bean.getLabel() %></option>
                			  <%
            			  }
            			 
            		  }
            		  %>
            	</select>
                </TD>                 

                <TD class=2-td2-left noWrap> <INPUT size=12 
                  value=<%=js.getFljmmj() %> name=fljmmj style="text-align:right"></TD>
                <TD class=2-td2-left noWrap><INPUT size=12 
                  value=<%=js.getFljmse() %> name=fljmse style="text-align:right"> </TD>
               
                <TD class=2-td2-center noWrap><SPAN 
                  onclick=delRow_jmlb(this) onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>删除</U></SPAN> </TD></TR>
            	  
           		<%
            	  
              }
              %>
              
              
              
              
              <%
              if(nc.getFljmmj() ==null || "".equals(nc.getFljmmj())){
              }else{
            	  %>
            	  <script >
            	  document.getElementById("jmsxx").checked=true;
            	  </script>
            	   <TR class=black9>
                <TD class=2-td2-left noWrap><input name =jmlbxh value=<%=n++ %> size =2 class=inbright readOnly /> </TD>
 				<TD class=2-td2-left noWrap>
            	<select name="jmlbdm_jmmx" onchange="getJMSYJ();">
            		  <%
            		  List jmlblist = (List)form.getJmlb_list();
            		  for(int j=0;j<jmlblist.size();j++){
            			  LabelValueBean bean = (LabelValueBean)jmlblist.get(j);
            			  if("01".equals(bean.getValue())){
            				  %>
                			  <option value="<%=  bean.getValue()%>" selected><%=bean.getLabel() %></option>
                			  <%
            			  }else{
            				  %>
                			  <option value="<%=  bean.getValue()%>"><%=bean.getLabel() %></option>
                			  <%
            			  }
            		  }
            		  %>
            	</select>
                </TD>                 

                <TD class=2-td2-left noWrap> <INPUT size=12 
                  value=<%=nc.getFljmmj() %> name=fljmmj style="text-align:right"></TD>
                <TD class=2-td2-left noWrap><INPUT size=12 
                  value=<%=nc.getFljmse() %> name=fljmse style="text-align:right"> </TD>
               
                <TD class=2-td2-center noWrap><SPAN 
                  onclick=delRow_jmlb(this) onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>删除</U></SPAN> </TD></TR>
            	  
           		<%
            	  
              }
              %>
             
              <%
              if(kn.getFljmmj() ==null || "".equals(kn.getFljmmj())){
              }else{
            	  %>
            	  <script >
            	  document.getElementById("jmsxx").checked=true;
            	  </script>
            	   <TR class=black9>
                <TD class=2-td2-left noWrap><input name =jmlbxh value=<%=n++ %> size =2 class=inbright readOnly /> </TD>
 				<TD class=2-td2-left noWrap>
            	<select name="jmlbdm_jmmx" onchange="getJMSYJ();">
            		  <%
            		  List jmlblist = (List)form.getJmlb_list();
            		  for(int j=0;j<jmlblist.size();j++){
            			  LabelValueBean bean = (LabelValueBean)jmlblist.get(j);
            			  if("02".equals(bean.getValue())){
            				  %>
                			  <option value="<%=  bean.getValue()%>" selected><%=bean.getLabel() %></option>
                			  <%
            			  }else{
            				  %>
                			  <option value="<%=  bean.getValue()%>"><%=bean.getLabel() %></option>
                			  <%
            			  }
            		  }
            		  %>
            	</select>
                </TD>                 

                <TD class=2-td2-left noWrap> <INPUT size=12 
                  value=<%=kn.getFljmmj() %> name=fljmmj style="text-align:right"></TD>
                <TD class=2-td2-left noWrap><INPUT size=12 
                  value=<%=kn.getFljmse() %> name=fljmse style="text-align:right"> </TD>
               
                <TD class=2-td2-center noWrap><SPAN 
                  onclick=delRow_jmlb(this) onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>删除</U></SPAN> </TD></TR>
            	  
           		<%
            	  
              }
              %>
             
              <%
              if(yly.getFljmmj() ==null || "".equals(yly.getFljmmj())){
              }else{
            	  %>
            	 <script >
            	  document.getElementById("jmsxx").checked=true;
            	  </script>
            	   <TR class=black9>
                <TD class=2-td2-left noWrap><input name =jmlbxh value=<%=n++ %> size =2 class=inbright readOnly /> </TD>
 				<TD class=2-td2-left noWrap>
            	<select name="jmlbdm_jmmx" onchange="getJMSYJ();">
            		  <%
            		  List jmlblist = (List)form.getJmlb_list();
            		  for(int j=0;j<jmlblist.size();j++){
            			  LabelValueBean bean = (LabelValueBean)jmlblist.get(j);
            			  if("03".equals(bean.getValue())){
            				  %>
                			  <option value="<%=  bean.getValue()%>" selected><%=bean.getLabel() %></option>
                			  <%
            			  }else{
            				  %>
                			  <option value="<%=  bean.getValue()%>"><%=bean.getLabel() %></option>
                			  <%
            			  }
            		  }
            		  %>
            	</select>
                </TD>                 

                <TD class=2-td2-left noWrap> <INPUT size=12 
                  value=<%=yly.getFljmmj() %> name=fljmmj style="text-align:right"></TD>
                <TD class=2-td2-left noWrap><INPUT size=12 
                  value=<%=yly.getFljmse() %> name=fljmse style="text-align:right"> </TD>
               
                <TD class=2-td2-center noWrap><SPAN 
                  onclick=delRow_jmlb(this) onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>删除</U></SPAN> </TD></TR>
            	  
           		<%
            	  
              }
              %>
              
               <%
              if(yy.getFljmmj() ==null || "".equals(yy.getFljmmj())){
              }else{
            	  %>
            	  <script >
            	  document.getElementById("jmsxx").checked=true;
            	  </script>
            	   <TR class=black9>
                <TD class=2-td2-left noWrap><input name =jmlbxh value=<%=n++ %> size =2 class=inbright readOnly /> </TD>
 				<TD class=2-td2-left noWrap>
            	<select name="jmlbdm_jmmx" onchange="getJMSYJ();">
            		  <%
            		  List jmlblist = (List)form.getJmlb_list();
            		  for(int j=0;j<jmlblist.size();j++){
            			  LabelValueBean bean = (LabelValueBean)jmlblist.get(j);
            			  if("04".equals(bean.getValue())){
            				  %>
                			  <option value="<%=  bean.getValue()%>" selected><%=bean.getLabel() %></option>
                			  <%
            			  }else{
            				  %>
                			  <option value="<%=  bean.getValue()%>"><%=bean.getLabel() %></option>
                			  <%
            			  }
            		  }
            		  %>
            	</select>
                </TD>                 

                <TD class=2-td2-left noWrap> <INPUT size=12 
                  value=<%=yy.getFljmmj() %> name=fljmmj style="text-align:right"></TD>
                <TD class=2-td2-left noWrap><INPUT size=12 
                  value=<%=yy.getFljmse() %> name=fljmse style="text-align:right"> </TD>
               
                <TD class=2-td2-center noWrap><SPAN 
                  onclick=delRow_jmlb(this) onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>删除</U></SPAN> </TD></TR>
            	  
           		<%
            	  
              }
              %>
              
               <%
              if(yey.getFljmmj() ==null || "".equals(yey.getFljmmj())){
              }else{
            	  %>
            	  <script >
            	  document.getElementById("jmsxx").checked=true;
            	  </script>
            	   <TR class=black9>
                <TD class=2-td2-left noWrap><input name =jmlbxh value=<%=n++ %> size =2 class=inbright readOnly /> </TD>
 				<TD class=2-td2-left noWrap>
            	<select name="jmlbdm_jmmx" onchange="getJMSYJ();">
            		  <%
            		  List jmlblist = (List)form.getJmlb_list();
            		  for(int j=0;j<jmlblist.size();j++){
            			  LabelValueBean bean = (LabelValueBean)jmlblist.get(j);
            			  if("05".equals(bean.getValue())){
            				  %>
                			  <option value="<%=  bean.getValue()%>" selected><%=bean.getLabel() %></option>
                			  <%
            			  }else{
            				  %>
                			  <option value="<%=  bean.getValue()%>"><%=bean.getLabel() %></option>
                			  <%
            			  }
            		  }
            		  %>
            	</select>
                </TD>                 

                <TD class=2-td2-left noWrap> <INPUT size=12 
                  value=<%=yey.getFljmmj() %> name=fljmmj style="text-align:right"></TD>
                <TD class=2-td2-left noWrap><INPUT size=12 
                  value=<%=yey.getFljmse() %> name=fljmse style="text-align:right"> </TD>
               
                <TD class=2-td2-center noWrap><SPAN 
                  onclick=delRow_jmlb(this) onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>删除</U></SPAN> </TD></TR>
            	  
           		<%
            	  
              }
              %>
              
               <%
              if(xx1.getFljmmj() ==null || "".equals(xx1.getFljmmj())){
              }else{
            	  %>
            	  <script >
            	  document.getElementById("jmsxx").checked=true;
            	  </script>
            	   <TR class=black9>
                <TD class=2-td2-left noWrap><input name =jmlbxh value=<%=n++ %> size =2 class=inbright readOnly /> </TD>
 				<TD class=2-td2-left noWrap>
            	<select name="jmlbdm_jmmx">
            		  <%
            		  List jmlblist = (List)form.getJmlb_list();
            		  for(int j=0;j<jmlblist.size();j++){
            			  LabelValueBean bean = (LabelValueBean)jmlblist.get(j);
            			  if("0601".equals(bean.getValue())){
            				  %>
                			  <option value="<%=  bean.getValue()%>" selected><%=bean.getLabel() %></option>
                			  <%
            			  }else{
            				  %>
                			  <option value="<%=  bean.getValue()%>"><%=bean.getLabel() %></option>
                			  <%
            			  }
            		  }
            		  %>
            	</select>
                </TD>                 

                <TD class=2-td2-left noWrap> <INPUT size=12 
                  value=<%=xx1.getFljmmj() %> name=fljmmj style="text-align:right"></TD>
                <TD class=2-td2-left noWrap><INPUT size=12 
                  value=<%=xx1.getFljmse() %> name=fljmse style="text-align:right"> </TD>
               
                <TD class=2-td2-center noWrap><SPAN 
                  onclick=delRow_jmlb(this) onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>删除</U></SPAN> </TD></TR>
            	  
           		<%
            	  
              }
              %>
              
              
              <%
              if(xx2.getFljmmj() ==null || "".equals(xx2.getFljmmj())){
              }else{
            	  %>
            	  <script >
            	  document.getElementById("jmsxx").checked=true;
            	  </script>
            	   <TR class=black9>
                <TD class=2-td2-left noWrap><input name =jmlbxh value=<%=n++ %> size =2 class=inbright readOnly /> </TD>
 				<TD class=2-td2-left noWrap>
            	<select name="jmlbdm_jmmx">
            		  <%
            		  List jmlblist = (List)form.getJmlb_list();
            		  for(int j=0;j<jmlblist.size();j++){
            			  LabelValueBean bean = (LabelValueBean)jmlblist.get(j);
            			  if("0602".equals(bean.getValue())){
            				  %>
                			  <option value="<%=  bean.getValue()%>" selected><%=bean.getLabel() %></option>
                			  <%
            			  }else{
            				  %>
                			  <option value="<%=  bean.getValue()%>"><%=bean.getLabel() %></option>
                			  <%
            			  }
            		  }
            		  %>
            	</select>
                </TD>                 

                <TD class=2-td2-left noWrap> <INPUT size=12 
                  value=<%=xx2.getFljmmj() %> name=fljmmj style="text-align:right"></TD>
                <TD class=2-td2-left noWrap><INPUT size=12 
                  value=<%=xx2.getFljmse() %> name=fljmse style="text-align:right"> </TD>
               
                <TD class=2-td2-center noWrap><SPAN 
                  onclick=delRow_jmlb(this) onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>删除</U></SPAN> </TD></TR>
            	  
           		<%
            	  
              }
              %>
              
              
               <%
              if(qt2.getFljmmj() ==null || "".equals(qt2.getFljmmj())){
              }else{
            	  %>
            	  <script >
            	  document.getElementById("jmsxx").checked=true;
            	  </script>
            	   <TR class=black9>
                <TD class=2-td2-left noWrap><input name =jmlbxh value=<%=n++ %> size =2 class=inbright readOnly /> </TD>
 				<TD class=2-td2-left noWrap>
            	<select name="jmlbdm_jmmx" onchange="getJMSYJ();">
            		  <%
            		  List jmlblist7 = (List)form.getJmlb_list();
            		  for(int j=0;j<jmlblist7.size();j++){
            			  LabelValueBean bean = (LabelValueBean)jmlblist7.get(j);
            			  if("07".equals(bean.getValue())){
            				  %>
                			  <option value="<%=  bean.getValue()%>" selected><%=bean.getLabel() %></option>
                			  <%
            			  }else{
            				  %>
                			  <option value="<%=  bean.getValue()%>"><%=bean.getLabel() %></option>
                			  <%
            			  }
            		  }
            		  %>
            	</select>
                </TD>                 

                <TD class=2-td2-left noWrap> <INPUT size=12 
                  value=<%=qt2.getFljmmj() %> name=fljmmj style="text-align:right"></TD>
                <TD class=2-td2-left noWrap><INPUT size=12 
                  value=<%=qt2.getFljmse() %> name=fljmse style="text-align:right"> </TD>
               
                <TD class=2-td2-center noWrap><SPAN 
                  onclick=delRow_jmlb(this) onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>删除</U></SPAN> </TD></TR>
            	  
           		<%
            	  
              }
              %>
                       
             
                  </TBODY>
                  <tr class=black9>
                  <td class=2-td2-center noWrap colspan=5> <SPAN onclick=insertrow_jmlb() 
                  onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>增加</U></SPAN></td>
                  </tr>
                  <tr>
                  <td colspan=5><span><FONT color=#000000 size=1><STRONG>申报减免理由：</STRONG></FONT> </span></td>
                 
                  </tr>
                  <tr>
                     <td colspan =5>
            			<textarea id =jmsyj  name ="jmsyj"  style="width:98%" rows = 5 readonly ><bean:write name="sydjxxlrForm" property="jmsyj"/>
            			</textarea>
                  	</td>
                  </tr>
              </TABLE><BR>
		 	
             <TABLE id="" class=table-99 cellSpacing=0 cellPadding=0 width="80%" align=center border=0>
              <tr>
                  <td colspan=4><span><FONT color=#000000 size=1><STRONG>备注：</STRONG></FONT> </span></td>
                 
                  </tr><tr>
                     <td colspan =4>
                     <textarea id =bzms  name ="bzms"  style="width:98%" rows = 5><bean:write name="sydjxxlrForm" property="bzms"/></textarea>
                  </td>
                  
                  </tr>
              <TR class=black9>
                <TD class=2-td2-t-left width="5%" noWrap>是否市局审核</TD>
                
                <TD class=2-td2-t-center width="15%" noWrap>
                <SELECT style="WIDTH: 120px" name=sfsjsp> 
                <%
                if("1".equals(form.getSfsjsp())){
                	%>
                	<OPTION selected value=1>是</OPTION> 
                	<OPTION value=0 >否</OPTION>
                	<%
                }else{
                	%>
                	<OPTION value=1>是</OPTION> 
                	<OPTION value=0 selected>否</OPTION>
                	
                	<%
                }
                %>
                
                </SELECT> 
                </TD></TR>
            
           </TABLE><BR> 
           
            <TABLE width="100%" border=0>
              <TBODY>
              <TR>
              	<td width="42%">&nbsp;</td>
                <TD width="8%" id=baocun><INPUT onclick="befSave('Save');return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=bc1 style="CURSOR: hand" 
                  accessKey=s height=22 alt=保存 src="<%=static_contextpath%>images/bc-s1.jpg" 
                  type=image width=79 border=0></TD>
                <TD width="8%"><INPUT onclick="tuichu();return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=tc1 style="CURSOR: hand" 
                  accessKey=c height=22 alt=退出 src="<%=static_contextpath%>images/tc-c1.jpg" 
                  type=image width=79 border=0></TD>
                <TD 
            width="42%">&nbsp;</TD></TR></TBODY></TABLE><BR></DIV></DIV></DIV></DIV></TD></TR></TBODY></TABLE><BR></FORM>
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

</html:form>
<TABLE id=zdyt_list_hidden class=table-99 cellSpacing=0 cellPadding=0 width="80%" align=center border=0>
                 <tbody  id = zdyt_hidden disabled=true style="display:none">
                	<TR class=black9 >
                <TD class=2-td2-left noWrap width="5%"><input  name= zdytxh value=1 size =2 class=inbright readOnly/></TD>
                
                <TD class=2-td2-t-left noWrap width="10%">
               <select name="zdyt" onchange="jisuan(this)" >
             	<%
             	List zdytlist = (List)form.getZdyt_list();
             	for(int i=0;i<zdytlist.size();i++){
             		LabelValueBean bean = (LabelValueBean)zdytlist.get(i);
             		%>
             		<option value='<%=bean.getValue() %>'><%=bean.getLabel() %></option>
             		<%
             	}
             	%>
			
             
             
                </TD>
                <TD class=2-td2-left noWrap width="10%">
				
             	<select name="syse" onchange="jisuan(this)" style="width:95%">
             	<%
             	List syselist = (List)form.getSyse_list();
             	for(int i=0;i<syselist.size();i++){
             		LabelValueBean bean = (LabelValueBean)syselist.get(i);
             		%>
             		<option value='<%=bean.getValue() %>'><%=bean.getLabel() %></option>
             		<%
             	}
             	%>
				</select>
				</TD>
                <TD class=2-td2-left noWrap width="10%"><INPUT onblur=jisuan(this); size=12 style ="TEXT-ALIGN: right;"
                  value=0.00 name=jsmj> </TD>
                <TD class=2-td2-left noWrap width="10%"><INPUT size=12 name=jzse class=inbright readOnly style ="TEXT-ALIGN: right;" value= 0.00> </TD>
                <TD class=2-td2-left noWrap width="10%"> <INPUT size=12 name=jmmj value ="0.00" onblur=jisuan(this); style ="TEXT-ALIGN: right;" /></TD>
                <TD class=2-td2-left noWrap width="10%"><INPUT  size=12  class=inbright readOnly
                  value="0.00" name=jmse style ="TEXT-ALIGN: right;"> </TD>
                <TD class=2-td2-left noWrap width="10%"><INPUT class=inbright readOnly 
                  size=12 value=0.00 name=ysmj style ="TEXT-ALIGN: right;"/> </TD>
                <TD class=2-td2-left noWrap width="10%"><INPUT class=inbright readOnly 
                  size=12 value=0.00 name=ynse style ="TEXT-ALIGN: right;"> </TD>
                <TD class=2-td2-center noWrap width="15%"><SPAN 
                  onclick=delRow_zdyt(this) onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>删除</U></SPAN> </TD></TR>
                
                </tbody>
                </TABLE>

<TABLE id="jmlb_list_hidden" class=table-99 cellSpacing=0 cellPadding=0 width="80%" align=center border=0 style="display:none">
                
                <tbody id="jmlb_hidden" style="display:none" disabled=true>
                <TR class=black9>
                <TD class=2-td2-left noWrap width="5%"><input name =jmlbxh value=1 size =2 class=inbright readOnly /> </TD>
                <TD class=2-td2-t-left noWrap width="30%">
 
             	<select name="jmlbdm_jmmx" onchange="getJMSYJ();">
             	<%
             	List jmlblist2 = (List)form.getJmlb_list();
             	for(int i=0;i<jmlblist2.size();i++){
             		LabelValueBean bean = (LabelValueBean)jmlblist2.get(i);
             		%>
             		<option value='<%=bean.getValue() %>'><%=bean.getLabel() %></option>
             		<%
             	}
             	%>
             	</select>
                </TD>
                <TD class=2-td2-left noWrap width="20%"> <INPUT size=12 
                  value=0.00 name=fljmmj style="text-align:right"></TD>
                <TD class=2-td2-left noWrap width="20%"><INPUT size=12 
                  value=0.00 name=fljmse style="text-align:right"> </TD>
               
                <TD class=2-td2-center noWrap width="25%"><SPAN 
                  onclick=delRow_jmlb(this) onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>删除</U></SPAN> </TD></TR>
                
                </tbody>

              </TABLE>

</body>


</html:html>