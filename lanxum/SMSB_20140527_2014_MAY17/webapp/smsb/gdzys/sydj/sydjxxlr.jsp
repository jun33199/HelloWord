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
 	 GdzysSydjxxlrForm form = (GdzysSydjxxlrForm)request.getAttribute("sydjxxlrForm");
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


//������˰��������ʾ��ͬ������
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

//�����--ռ����;
	function insertrow_zdyt(){
	var obj = document.getElementById("zdyt_hidden");
	obj.style.display="inline";
	obj.disabled=false;
	var newrow = document.all.zdyt_hidden.rows[0].cloneNode(true);
	document.all("ZDYT_ROWS").appendChild(newrow); //��Ӹղſ�¡��һ��
	var len = document.all.ZDYT_ROWS.rows;
	var xh = document.getElementsByName("zdytxh");
	for(var i=1;i<=len.length;i++){
		xh[i].value=i+1;
	}
	obj.style.display="none";
	obj.disabled=true;
	//�ϼ�
	hj();
	
	}
//ɾ����---ռ����;
function delRow_zdyt(obj){
	var len = document.getElementById("ZDYT_ROWS").rows.length;
	var row = obj.parentNode.parentNode.rowIndex; //A��ǩ������
	if(confirm("��ȷ��Ҫɾ����")){
		ZDYT_ROWS.deleteRow(Number(row)-1);
		var len = document.all.ZDYT_ROWS.rows;
		var xh = document.getElementsByName("zdytxh");
		for(var i=1;i<=len.length;i++){
			xh[i].value=i+1;
		}
		hj();
		
	}else{
		return false;	
	}

}

//�����--ռ����;
function insertrow_jmlb(){
var obj = document.getElementById("jmlb_hidden");
obj.style.display="inline";
obj.disabled=false;
var newrow = document.all.jmlb_hidden.rows[0].cloneNode(true);
document.all("JMLB_ROWS").appendChild(newrow); //��Ӹղſ�¡��һ��
var len = document.all.JMLB_ROWS.rows;
var xh = document.getElementsByName("jmlbxh");
for(var i=1;i<=len.length;i++){
	xh[i].value=i+1;
}
obj.style.display="none";
obj.disabled=true;

}
//ɾ����---ռ����;
function delRow_jmlb(obj){
var len = document.getElementById("JMLB_ROWS").rows.length;
var row = obj.parentNode.parentNode.rowIndex; //A��ǩ������
if(confirm("��ȷ��Ҫɾ����")){
	JMLB_ROWS.deleteRow(Number(row)-1);
	var len = document.all.JMLB_ROWS.rows;
	var xh = document.getElementsByName("jmlbxh");
	for(var i=1;i<=len.length;i++){
		xh[i].value=i+1;
	}
	getJMSYJ();
}else{
	return false;	
}

}

//����
function jisuan(){
	//У�����
	check_mj();
	//����˰��
	jzse();
	//����˰��
	jmse();
	//Ӧ˰���
	ysmj();
	//Ӧ��˰��
	ynse();
	//�ϼ�
	hj();
	
	
}
//�����С����β��
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
				 alert("��˰������ֻ������λС��!"); 
				 obj.focus();
				 return false; 
			 }
			var obj2 = jmmj[i];
			if(obj2.value == ""){
				obj2.value="0.00";
				return false;
			}
			if (!a.test(obj2.value)) { 
				 alert("����������ֻ������λС��!"); 
				 obj2.focus();
				 return false; 
			 }
		}
		
		
	}
	  
} 


//�������˰��
function jzse(){
	var syse = document.getElementsByName("syse");
	var jsmj = document.getElementsByName("jsmj");
	var jzse = document.getElementsByName("jzse");
	for(var i=0;i<jzse.length;i++){
		jzse[i].value =  parseFloat("0.00");
		jzse[i].value = (parseFloat(syse[i].value.substring(3)) * parseFloat(jsmj[i].value)).toFixed(2);
	}

}

//�������˰��
function jmse(){
	var jsmj = document.getElementsByName("jsmj");
	var syse = document.getElementsByName("syse");
	var jmmj = document.getElementsByName("jmmj");
	var jmse = document.getElementsByName("jmse");
	for(var i=0;i<jmse.length-1;i++){
		if(parseFloat(jsmj[i].value)<parseFloat(jmmj[i].value)){
			alert("��˰�������С�ڼ��������");
			return false;
		}
		
		jmse[i].value =  parseFloat("0.00");
		if(jmmj[i].value =="" ){
			jmmj[i].value = "0.00";
		}
		jmse[i].value = (parseFloat(syse[i].value.substring(3)) * parseFloat(jmmj[i].value)).toFixed(2);
	}

}
//����Ӧ˰���
function ysmj(){
	var jsmj = document.getElementsByName("jsmj");
	var jmmj = document.getElementsByName("jmmj");
	var ysmj = document.getElementsByName("ysmj");
	for(var i=0;i<jsmj.length;i++){
		ysmj[i].value =  parseFloat("0.00");
		ysmj[i].value = (parseFloat(jsmj[i].value) - parseFloat(jmmj[i].value)).toFixed(2);
		
	}
	
}
//����Ӧ��˰��
function ynse(){
	var jzse = document.getElementsByName("jzse");
	var jmse = document.getElementsByName("jmse");
	var ynse = document.getElementsByName("ynse");
	for(var i=0;i<ynse.length;i++){
		ynse[i].value =  parseFloat("0.00");
		ynse[i].value = (parseFloat(jzse[i].value) - parseFloat(jmse[i].value)).toFixed(2);
	}
}

//����ϼ�
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
	var yhdm = new Array();
	var yhmc = new Array();
	var yhzh = new Array();
//�������
//�������������ȡ�Ǽ���Ϣ
 function getDJXX(){
	   yhdm.splice(0,yhdm.length);
	   yhmc.splice(0,yhmc.length);
	   yhzh.splice(0,yhzh.length);
	   
	   
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
	       		var leng = xmlDOM.getElementsByTagName("nsrmc").length;
	       		var obj_bc = document.getElementById("baocun");
	       		if(leng<1){
	       			alert("���Ƚ���˰��Ǽǣ�");
	       			obj_bc.style.visibility="hidden";
	       		}else{
	       			obj_bc.style.visibility="visible";
	       		}
	         
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
	       	 
	       	 var obj = document.getElementById("khyh");
	       	 obj.value = xmlDOM.getElementsByTagName("yhmc")[0].firstChild.data;
	       	 
	       	var obj = document.getElementById("yhzh");
	       	 obj.value = xmlDOM.getElementsByTagName("yhzh")[0].firstChild.data;
	       	  
	       	 /* ��ʼ20131213 */
	       	 
	       	var obj2 = document.getElementById("khyhdm");
	       	 obj2.style.width="25%";
	       	var len = obj2.options.length;
        	for(var i = 0;i<len;i++){
        		obj2.options.remove(i);
        		i--;
        		len--;
        	}
        	var len2 = xmlDOM.getElementsByTagName("dm").length;
     		for(var j=0;j<len2;j++){
     			yhdm[j]= xmlDOM.getElementsByTagName("dm")[j].firstChild.data;
     			yhmc[j]= xmlDOM.getElementsByTagName("mc")[j].firstChild.data;
     			yhzh[j]= xmlDOM.getElementsByTagName("zh")[j].firstChild.data;
     			oOption = document.createElement("option");
     		 	oOption.text= xmlDOM.getElementsByTagName("mc")[j].firstChild.data;
  	          	oOption.value= xmlDOM.getElementsByTagName("dm")[j].firstChild.data;
     			if(xmlDOM.getElementsByTagName("dm")[j].firstChild.data == xmlDOM.getElementsByTagName("yhdm")[0].firstChild.data && xmlDOM.getElementsByTagName("zh")[j].firstChild.data == xmlDOM.getElementsByTagName("yhzh")[0].firstChild.data){
     				oOption.selected =true;
     				
     			}
  	          	obj2.add(oOption);
     		}
	       	 
     		
	         
	       }
	     }
	  	 };
	  	
}

	//����ѡ��
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

//��ȡ����˰��

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
		     	 	 //����˰��
		   	  	 jisuan();
	        	}
	        	
	       }
	     }
	  	 };
	 
}
//��ȡ����˰����
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


//��ȡ��Ȼ��
 function getZRR(){
	   var xmlhttp_request;
	   var dm = document.getElementById("sfzzlxdm").value;
	   var hm = document.getElementById("sfzzhm").value;
	   if(window.ActiveXObject){
	  	       xmlhttp_request=new ActiveXObject("Microsoft.XMLHTTP");
	  	    }else if(window.XMLHttpRequest){
	  	       xmlhttp_request=new XMLHttpRequest();
	  	     } else {
	  	       return;
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
	        		alert("�������Ȼ�˵Ǽǣ�");
	        		obj_bc.style.visibility="hidden";
	        		return false;
	        	}else{
	        		obj_bc.style.visibility="visible";
	        	}
	        	
	        	
	        	var leng = xmlDOM.getElementsByTagName("jsjdm").length;
	       		var obj_bc = document.getElementById("baocun");
	       		if(leng<1){
	       			obj_bc.style.visibility="hidden";
	       		}else{
	       			obj_bc.style.visibility="visible";
	       		}
	        	
	        	 var obj = document.getElementById("nsrmc");
		         if(xmlDOM.getElementsByTagName("mc")[0].firstChild.data ==""){
		        	 obj.value=""
		         }else{
		        	 obj.value = xmlDOM.getElementsByTagName("mc")[0].firstChild.data;
		         }
		       	 
		       	var obj = document.getElementById("jsjdm");
		       	 if(xmlDOM.getElementsByTagName("jsjdm")[0].firstChild.data ==""){
		       	    obj.value = "";
		       	 }else{
		       		 obj.value = xmlDOM.getElementsByTagName("jsjdm")[0].firstChild.data;
		       	 }
		       	 
		       	var obj = document.getElementById("nsrxxdz");
		       	 if(xmlDOM.getElementsByTagName("dz")[0].firstChild.data =="null"){
		       	    obj.value = "";
		       	 }else{
		       		 obj.value = xmlDOM.getElementsByTagName("dz")[0].firstChild.data;
		       	 }
		        
	     		}
	       }
	  	 };
}


//��֤ʱ��
 function checkdate(){
	 var a = /^\d{8}$/;
	 if (!a.test(document.getElementById("zdsj").value)) { 
	 alert("���ڸ�ʽ����ȷ!");
	 return false; 
	 }else{
		 return true;
	 } 
	 
} 

//��֤��ֵ
 function checkdnumber(){
	 var a = /^\d+(.)*\d*$/;
	 if (!a.test(document.getElementById("sjzdmj").value)) { 
		 alert("ʵ��ռ���������ȷ!"); 
		 return false; 
	 }else{
		 return true;
	 }
	 
} 
//��֤����˰��
 function yzjmse(){
	var bl=true;
 	var jsmj = document.getElementsByName("jsmj");
 	var syse = document.getElementsByName("syse");
 	var jmmj = document.getElementsByName("jmmj");
 	var jmse = document.getElementsByName("jmse");
 	for(var i=0;i<jmse.length;i++){
 		if(parseFloat(jsmj[i].value)<parseFloat(jmmj[i].value)){
 			alert("��˰�������С�ڼ��������");
 			bl = false;
 		}
 		
 	}
 	return bl;
 	

 }
 
 //ֻ������ֵ����λС��
 //��֤��ֵ
 function check_number(obj){
	 var a = /^(\d+.?\d{0,2})$/;
	 if(""==obj.value){
		 obj.focus();
		 return false;
	 }
	 if (!a.test(obj.value)) { 
		 alert("ʵ��ռ��������ֻ������λС��!"); 
		 obj.focus();
		 return false; 
	 }else{
		 return true;
	 }
	 
} 
 function zdsj_check(){
	 var a = /^([\d]{4}(((0[13578]|1[02])((0[1-9])|([12][0-9])|(3[01])))|(((0[469])|11)((0[1-9])|([12][1-9])|30))|(02((0[1-9])|(1[0-9])|(2[1-8])))))|((((([02468][048])|([13579][26]))00)|([0-9]{2}(([02468][048])|([13579][26]))))(((0[13578]|1[02])((0[1-9])|([12][0-9])|(3[01])))|(((0[469])|11)((0[1-9])|([12][1-9])|30))|(02((0[1-9])|(1[0-9])|(2[1-9])))))$/;
	 var zdsj = document.getElementById("zdsj").value;
	 if(zdsj.length>8){
		   alert("����ʱ�䳤�Ȳ���ȷ");
		   return false;
	   }else{
	 if(!a.test(zdsj)){
		 alert("ռ��ʱ���ʽ����ȷ");
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
 

//���� 
function befSave(flag){
	//��֤�������
	 if(!check_jmmx()){
		 alert("����������ظ���");
		return false;
	} 
	var jsmj_val = document.getElementsByName("jsmj");

	for(var j = 0;j<jsmj_val.length-1;j++){
		if(Number(jsmj_val[j].value) == 0){
			alert("��˰���ֻ�ܴ����㣡");
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
	 
	 //��׼ռ���ĺ�����׼���ͬʱΪ�ջ�ͬʱ��Ϊ��
	if((pzjdmj !="" && pzjdwh !="") || (pzjdmj=="" && pzjdwh == "")){
		
	}else{
		alert("��׼ռ���ĺ�����׼ռ�����ֻ��ͬʱ��Ϊ�ջ��߶���Ϊ��");
		return false;
	}
	 if(!zdsj_check()){
		 return false;
	 }

	 if(nsrlx== "1" && sfzzhm=="" ){
		 alert("���֤�պ��벻��Ϊ�գ�");
		 return false;
	 }
	 if(nsrlx=="0" && jsjdm ==""){
		 alert("��������벻��Ϊ�գ�");
		 return false;
	 }
	 if(lxrxm =="" ){
		 alert("��ϵ����������Ϊ�գ�");
		 return false;
	 }
	 if(lxdh =="" ){
		 alert("��ϵ�绰����Ϊ�գ�");
		 return false;
	 }
	 
	 if(tdzldz ==""){
		 alert("���������ַ����Ϊ�գ�");
		 return false;
	 }
	 if(jsxmmc ==""){
		 alert("������Ŀ����Ϊ�գ�");
		 return false;
	 } 
	
	 if(sjzdmj ==""){
		 alert("ʵ��ռ���������Ϊ�գ�");
		 return false;
	 }
	 if(zdsj ==""){
		 alert("ռ��ʱ�䲻��Ϊ�գ�");
		 return false;
	 }
	 
	 
	  if(parseFloat(sjzdmj).toFixed(2)!=parseFloat(jsmj).toFixed(2)){
		 alert("ʵ��ռ��������˰������ȣ����ʵ��");
		 return false;
	 }

	//��֤����
	if(!checkdate()){
		return false;
	}
	 //��֤��ֵ
	 if(!checkdnumber()){
		 return false;
	 }
	 //��˰�����������Ƚ�
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
			 alert("Ӧ��˰�������˰���");
			 return false;
		 }
		 if(len>0){
			 alert("ȫ��ʱ�����������˰��Ϣ");
			 return false;
		 }
		 
	 }else{
		 if(len<1){
			 alert("��ȫ��ʱ�����������˰��Ϣ");
			 return false;
		 }
		 
		 //ȫ��1
		 if(sylxdm == '1'){
			 if(parseFloat(hj_ynse).toFixed(2)!=0.00 || parseFloat(hj_jzse).toFixed(2) ==0.00){
				 alert("ȫ��ʱ˰���ȷ");
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
				 alert("�������������������ݲ���");
				 return false;
			 }
		
			 //��������
		 }else{
			 if(Number(hj_jzse)<=Number(hj_jmse)){
				 alert("����˰��Ӧ���ڼ���˰��");
				 return false;
			 }

			 var fljmmj = document.getElementsByName("fljmmj");
			 var fljmse = document.getElementsByName("fljmse");
			 var fljmmj_sum = 0;
			 var fljmse_sum = 0;
			 
			 for(var j = 0;j<fljmmj.length;j++){
				 fljmmj_sum = Number(fljmmj_sum) + Number(fljmmj[j].value);
				 fljmse_sum = Number(fljmse_sum) + Number(fljmse[j].value);
				 
				}
			 
			 if(parseFloat(fljmmj_sum).toFixed(2) != parseFloat(hj_jmmj).toFixed(2) || parseFloat(fljmse_sum).toFixed(2) != parseFloat(hj_jmse).toFixed(2)){
				 alert("�������������������ݲ���");
				 return false;
			 } 
			 
		 }
		 
	 }

	document.forms[0].actionType.value = "Save";
	document.forms[0].submit();  
}
//��ӡ�걨�� 
function printsbb(){
	document.forms[0].actionType.value = "PrintSBB";
	document.forms[0].submit();  
}
//�����걨��
function printjmsbb(){
	document.forms[0].actionType.value = "PrintJMSBB";
	document.forms[0].submit();  
}
/*  ��׼ռ���ĺ�           */

//ռ�����ĺ����
function doOnNianduFocus(){
	var niandu = document.getElementById("pzzdwhnd");                          
	niandu.value="";
}
function doOnNianduBlur(){
	var niandu = document.getElementById("pzzdwhnd");
	var zdpwh = document.getElementById("pzjdwh");							  //���ĺ�
	var wenhao = document.getElementById("pzzdwhwh");						  //�ĺ�
	var check = true;
	if(niandu.value == "" )
	{
		niandu.value=" ���";
		//niandu.style="width:50px; color:grey;";
		zdpwh.value="";
		check = false;
		return false;
	}
	if(niandu.value.length != 4 || isNaN(niandu.value))
		{
			alert("�������ݲ��Ϸ����������������");
			niandu.value=" ���";
			//niandu.style="width:50px; color:grey;";
			zdpwh.value="";
			check = false;
			return false;
		}
	if(wenhao.value.indexOf('�ĺ�',0) != -1)
	{
		
		check = false;
		return false;
	}
	if(check == true)
	{
		var zdpwhval="��������["+ niandu.value+"]"+wenhao.value+"��";
		zdpwh.value = zdpwhval;
	}
}
//ռ�����ĺ��ĺ�
function doOnWenhaoFocus(){
	var wenhao = document.getElementById("pzzdwhwh");                          
	wenhao.value="";                                  	  							//���
	
}
function doOnWenhaoBlur(){
	var niandu = document.getElementById("pzzdwhnd");                          //���
	var zdpwh = document.getElementById("pzjdwh");							  //���ĺ�
	var wenhao = document.getElementById("pzzdwhwh");						  //�ĺ�
	var check = true;
	if(wenhao.value == "" )
	{
		wenhao.value=" �ĺ�";
		//wenhao.style="width:50px; color:grey;";
		zdpwh.value="";
		check = false;
		return false;
	}
	
	if(isNaN(wenhao.value))
		{
			alert("�������ݲ��Ϸ��������������ĺ�");
			wenhao.value=" �ĺ�";
			//wenhao.style="width:50px; color:grey;";
			zdpwh.value="";
			check = false;
			return false;
		}
	
	if(niandu.value.indexOf('���',0) != -1)
		{
			check = false;
			return false;
		}
	
	if(check == true)
	{
		var zdpwhval="��������["+ niandu.value+"]"+wenhao.value+"��";
		zdpwh.value = zdpwhval;
	}
}












</SCRIPT>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
 <%@ include file="../../include/header.jsp"%>
<%
 if("error".equals(form.getErrors())){
	 %>
            <DIV align=left><FONT color=#FF0000 
            size=2><STRONG>����ʧ�ܣ�������¼��˰Դ��Ϣ��</STRONG></FONT> 
	 <%
 }
 %>
<html:form action="/webapp/smsb/sydjxxlrAction.do" method="post">
<html:hidden property="actionType"/>

<TABLE class=black9 height=300 cellSpacing=0 cellPadding=0 width="90%" 
align=center border=0>
  <TBODY>
  <TR>
    <TD height=300 vAlign=top><BR>
      <TABLE class=table-99 cellSpacing=0 align=center>
        <TBODY>
        <TR>
          <TD class=1-td1 colSpan=2>˰Դ�Ǽ�¼��</TD></TR>
        <TR>
          <TD class=1-td2 colSpan=2><BR>
          <DIV align=left><FONT color=#000000 
            size=1><STRONG>������Ϣ��</STRONG></FONT></div>
            <TABLE class=table-99 cellSpacing=0 cellPadding=0 width="80%" border=0 align = center>
              <TBODY>
              <TR class=black9>
                <TD class=2-td2-t-left width="20%" noWrap>�걨���к�</TD>
                <TD class=2-td2-t-left noWrap><INPUT 
                  onkeydown="if(event.keyCode==13) event.keyCode=9;" 
                name=sbbxlh value =<bean:write name="sydjxxlrForm" property="sbbxlh"/> readonly class=inbright style="TEXT-ALIGN:left"></TD>
                <TD class=2-td2-t-left width="20%" noWrap>��˰������</TD>
                <TD class=2-td2-t-center noWrap>
                <SELECT 
                  onchange=is_nsrlx_display() id=nsrlx style="WIDTH: 36%" 
                  name=nsrlx> <OPTION selected value=0>��λ</OPTION> <OPTION 
                    value=1>����</OPTION></SELECT>  
              </TD></TR>
               <TR class=black9 style="display:inline" id = tr_jsjdm>
                <TD class=2-td2-left noWrap>���������</TD>
                <TD class=2-td2-left noWrap><FONT COLOR="#FF0000">*</FONT><INPUT id = "jsjdm" onblur = "getDJXX();"
                  onkeydown="if(event.keyCode==13) event.keyCode=9;" 
                name=jsjdm></TD>
                 <TD class=2-td2-left  noWrap>&nbsp;</TD>
                
                <TD class=2-td2-center noWrap>
                &nbsp;
                </TD>
              
                </TR> 
              <TR>
                <TD class=2-td2-left noWrap>˰Դ����</TD>
                <TD class=2-td2-left noWrap>
                <SELECT 
                  onchange=is_nsrlx_display() id=sylxdm style="WIDTH: 36%" name=sylxdm> 
                  <OPTION selected value=0>ȫ��</OPTION>
                  <OPTION value=1>ȫ��</OPTION>
                  <OPTION value=2>��������</OPTION>
                  </SELECT> 
                </TD>
                 <TD class=2-td2-left width="20%" noWrap>˰������</TD>
                <TD class=2-td2-center noWrap>
                <select id = "syse_nd" onchange="getSyse();" style="WIDTH: 36%" name = "sysend">
				<option value="2008">��˰��</option>
				<option value= "2007">��˰��</option>       
             	</select>
				</TD>
                </TR> </TBODY>
               <TR>
               <TD class=2-td2-left width="20%" noWrap>��˰������</TD>
                <TD class=2-td2-center noWrap colspan=3><INPUT name=nsrmc id="nsrmc" style="width:98%" readonly class=inbright style="TEXT-ALIGN:left"></TD>
              </TR>
              <TR>
                <TD class=2-td2-left width="20%" noWrap>��˰����ϸ��ַ</TD>
                <TD class=2-td2-center noWrap colspan=3><INPUT name=nsrxxdz style="width:98%" id = "nsrxxdz" readonly class=inbright style="TEXT-ALIGN:left"></TD>
              </TR>
              
             <TBODY id=qy>
              <TR class=black9>
                <TD class=2-td2-left width="20%" noWrap>��˰��������ҵ</TD>
                <TD class=2-td2-left noWrap><INPUT type = "hidden" name=nsrsshy id = 'nsrsshy'><INPUT size ='50%'  name=nsrsshymc id = 'nsrsshymc' readonly class=inbright style="TEXT-ALIGN:center"></TD>
                <TD class=2-td2-left width="20%" noWrap>��ҵ�Ǽ�ע������</TD>
                <TD class=2-td2-center noWrap><INPUT type="hidden" name=qydjzclx id = "qydjzclx"><INPUT  size ='50%' name=qydjzclxmc id = "qydjzclxmc" readonly class=inbright style="TEXT-ALIGN:center"></TD></TR>
           
              <TR>
                <TD class=2-td2-left width="20%" noWrap>��˰��ʶ���</TD>
                <TD class=2-td2-left noWrap><INPUT size="26%" name=nsrsbh id = nsrsbh readonly class=inbright style="TEXT-ALIGN:center"></TD>
                
                  <TD class=2-td2-left width="20%" noWrap>&nbsp;</TD>
                <TD class=2-td2-center noWrap>&nbsp;<!-- <FONT COLOR="#FF0000">*</FONT>
                 <INPUT name=khyh id =khyh><select id = "khyhdm" name="yhdm" style="width:5%" onchange="getyh()">
                </select> -->
                 </TD></TR>
              <TR class=black9>
               <TD class=2-td2-left width="20%" noWrap>��������</TD>
                <TD class=2-td2-left noWrap><FONT COLOR="#FF0000">*</FONT>
                 <INPUT name=khyh id =khyh><select id = "khyhdm" name="yhdm" style="width:5%" onchange="getyh()">
                
                </select>
                 </TD>
               <TD class=2-td2-left width="20%" noWrap>�����˺�</TD>
                <TD class=2-td2-center noWrap><FONT COLOR="#FF0000">*</FONT><INPUT name=yhzh id = yhzh></TD>
                </TR>
             <TBODY>
              <TR>
              <TD class=2-td2-left width="20%" noWrap>��ϵ������</TD>
                <TD class=2-td2-left noWrap><FONT COLOR="#FF0000">*</FONT><INPUT name=lxrxm id = lxrxm></TD>
                <TD class=2-td2-left width="20%" noWrap>��ϵ�绰</TD>
                <TD class=2-td2-center noWrap><FONT COLOR="#FF0000">*</FONT><INPUT name=lxdh id=lxdh></TD>
                
                </TR>
               <tbody id = "gr" style="display : none">
              <TR class=black9>
                <TD class=2-td2-left width="20%" noWrap>���֤������</TD>
                <TD class=2-td2-left noWrap><FONT COLOR="#FF0000">*</FONT><select name = "sfzzlxdm" style="WIDTH: 37%" id = sfzzlxdm>
                	<option value = ""></option>
	                <option value = "02">���֤</option>
	                <option value = "03">����֤��</option>
	                <option value = "04">����</option>
	                <option value = "05">�۰�ͬ������֤</option>
	                <option value = "90">����</option>
                </select>
                </TD> 
                <TD class=2-td2-left width="20%" noWrap>���֤�պ���</TD>
                <TD class=2-td2-center noWrap><FONT COLOR="#FF0000">*</FONT><INPUT name=sfzzhm id=sfzzhm onblur="getZRR()">
                </TD></TR></TBODY>
              </TABLE><BR>
            <DIV align=left><FONT color=#000000 
            size=1><STRONG>������Ϣ��</STRONG></FONT> 
            <DIV><!--������Ϣ -->
           <TABLE id=tdxx class=table-99 cellSpacing=0 cellPadding=0 
            width="80%" align=center border=0>
              <TBODY>
                <TR class=black9>
                <TD class=2-td2-t-left width="20%" noWrap>���������ַ</TD>
                <TD class=2-td2-t-center noWrap colspan= 3><FONT COLOR="#FF0000">*</FONT><INPUT name=tdzldz  style="width:98%" value ="" id = tdzldz></TD>
                </TR>
               <TR>
                <TD class=2-td2-left width="20%" noWrap>������Ŀ����</TD>
                <TD class=2-td2-center noWrap colspan = 3><FONT COLOR="#FF0000">*</FONT><INPUT name=jsxmmc style="width:98%" value ="" id = jsxmmc></TD>
                </TR> 
              <TR class=black9>
                <TD class=2-td2-left width="20%" noWrap>��׼ռ���ĺ�</TD>
                 <TD class=2-td2-left noWrap><INPUT name=pzjdwh type="hidden" value ="" id = pzjdwh >
                ��������[<input type="text"  id="pzzdwhnd" style="width:50px; color:grey;" value="&nbsp;���" 
                onfocus="javascript:doOnNianduFocus()" onblur="javascript: doOnNianduBlur()">]<input type="text" id="pzzdwhwh" style="width:50px; color:grey;" value="&nbsp;�ĺ�" onfocus="javascript:doOnWenhaoFocus()" onblur="javascript:doOnWenhaoBlur()" >��</TD>
                
                
                
                
                
                
                
                
                
                <TD class=2-td2-left width="20%" noWrap>��׼ռ�����������ũת�������ƽ���ף�</TD>
                <TD class=2-td2-center noWrap><INPUT  size ='26%' name=pzjdmj value ="" id = pzjdmj></TD>
                </TR>
               <TR class=black9>
                <TD class=2-td2-left width="20%" noWrap>ʵ��ռ����������˰���һ�£�ƽ���ף�</TD>
                <TD class=2-td2-left noWrap width="30%"><FONT COLOR="#FF0000">*</FONT><INPUT  size ='26%' id =sjzdmj name=sjzdmj value ="" onblur="check_number(this)"></TD>
                <TD class=2-td2-left width="20%" noWrap>��׼ʱ��/ʵ��ռ��ʱ��(��ʽ��YYYYMMDD)</TD>
                <TD class=2-td2-center noWrap width="30%"><FONT COLOR="#FF0000">*</FONT><INPUT  size ='26%' name=zdsj value ="" id=zdsj onblur="zdsj_check();"></TD></TR> 
              </TBODY></TABLE> <BR>
              
              
              <TABLE id=ZDYT_LIST class=table-99 cellSpacing=0 cellPadding=0 
            width="80%" align=center border=0>
              <TBODY>
              <TR class=black9>
                <TD class=2-td2-t-left width="5%" noWrap>���</TD>
                <TD class=2-td2-t-left width="10%" noWrap>ռ����;</TD>
                <TD class=2-td2-t-left width="10%" noWrap>����˰�Ԫ/ƽ���ף�</TD>
                <TD class=2-td2-t-left width="10%" noWrap>��˰�����ƽ���ף�</TD>
                <TD class=2-td2-t-left width="10%" noWrap>����˰�Ԫ��</TD>
                <TD class=2-td2-t-left width="10%" noWrap>���������ƽ���ף�</TD>
                <TD class=2-td2-t-left width="10%" noWrap>����˰�Ԫ��</TD>
                <TD class=2-td2-t-left width="10%" noWrap>Ӧ˰�����ƽ���ף�</TD>
                <TD class=2-td2-t-left width="10%" noWrap>Ӧ��˰�Ԫ��</TD>
                <TD class=2-td2-t-center width="15%" noWrap>����</TD></TR>
               
                
              <TBODY id=ZDYT_ROWS>
              <TR class=black9 >
                <TD class=2-td2-left noWrap><input  name= zdytxh value=1 size =2 class=inbright readOnly/></TD>
                
                <TD class=2-td2-t-left noWrap>
               
                <bean:define id="zdyt_list" name="sydjxxlrForm" property="zdyt_list" />
                <html:select  property="zdyt">
                <html:options collection="zdyt_list" property="value" labelProperty="label"/>             
             </html:select> 
                </TD>
                <TD class=2-td2-left noWrap>
				<bean:define id="syse_list" name="sydjxxlrForm" property="syse_list" />
                <html:select property="syse" onchange="jisuan()"  style="WIDTH: 95%">
                <html:options collection="syse_list" property="value" labelProperty="label"/>             
             	</html:select> 
				
				</TD>
                <TD class=2-td2-left noWrap><INPUT onblur=jisuan(); size=12 style ="TEXT-ALIGN: right;"
                  value=0.00 name=jsmj> </TD>
                <TD class=2-td2-left noWrap><INPUT size=12 name=jzse class=inbright readOnly style ="TEXT-ALIGN: right;" value= 0.00> </TD>
                <TD class=2-td2-left noWrap> <INPUT size=12 name=jmmj value ="0.00" onblur=jisuan(); style ="TEXT-ALIGN: right;" /></TD>
                <TD class=2-td2-left noWrap><INPUT  size=12  class=inbright readOnly
                  value="0.00" name=jmse style ="TEXT-ALIGN: right;"> </TD>
                <TD class=2-td2-left noWrap><INPUT class=inbright readOnly 
                  size=12 value=0.00 name=ysmj style ="TEXT-ALIGN: right;"/> </TD>
                <TD class=2-td2-left noWrap><INPUT class=inbright readOnly 
                  size=12 value=0.00 name=ynse style ="TEXT-ALIGN: right;"> </TD>
                <TD class=2-td2-center noWrap><SPAN 
                  onclick=delRow_zdyt(this) onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>ɾ��</U></SPAN> </TD></TR></TBODY>
              <TBODY>
              <TR class=black9>
                <TD class=2-td2-left noWrap>�ϼ� </TD>
                <TD class=2-td2-left noWrap>&nbsp; </TD>
                <TD class=2-td2-left noWrap>&nbsp; </TD>
                <TD class=2-td2-left noWrap><INPUT id=hj_jsmj name = "hj_jsmj"
                  class=inbright readOnly size=12 value=0></TD>
                <TD class=2-td2-left noWrap><INPUT id=hj_jzse name = "hj_jzse"
                  class=inbright readOnly size=12 value=0></TD>
                <TD class=2-td2-left noWrap><INPUT id=hj_jmmj name = "hj_jmmj"
                  class=inbright readOnly size=12 value=0></TD>
                <TD class=2-td2-left noWrap><INPUT id=hj_jmse name = "hj_jmse"
                  class=inbright readOnly size=12 value=0></TD>
                          
                <TD class=2-td2-left noWrap><INPUT id=hj_ysmj name = "hj_ysmj"
                  class=inbright readOnly size=12 value=0> </TD>
                <TD class=2-td2-left noWrap><INPUT id=hj_ynse name = "hj_ynse"
                  class=inbright readOnly size=12 value=0> </TD>
                <TD class=2-td2-center noWrap>&nbsp; </TD></TR></TBODY>
                <tr class=black9>
                <td  class=2-td2-left noWrap colspan=10><SPAN onclick="insertrow_zdyt()" 
                  onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>����</U></SPAN>&nbsp;</td></tr>
                
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
 			
 			<FONT color=#000000 size=1><STRONG>����˰��Ϣ��<input type="checkbox" name="jmsxx" id= "jmsxx" onclick="display_jmsxx()"/></STRONG></FONT></div>
 			<TABLE id="JMLB_LIST" class=table-99 cellSpacing=0 cellPadding=0 width="80%" align=center border=0 style="display:none">
              <TBODY>
              <TR class=black9>
                <TD class=2-td2-t-left width="5%" noWrap>���</TD>
                <TD class=2-td2-t-left width="30%" noWrap>�������</TD>
                <TD class=2-td2-t-left width="20%" noWrap>����������</TD>
                <TD class=2-td2-t-left width="20%" noWrap>�������˰��</TD>
                <TD class=2-td2-t-center width="25%" noWrap>����</TD></TR>
                
             
                
                
              <TBODY id="JMLB_ROWS">
                  </TBODY>
                  <tr class=black9>
                  <td class=2-td2-center noWrap colspan=5> <SPAN onclick=insertrow_jmlb() 
                  onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>����</U></SPAN></td>
                  </tr>
                  <tr>
                  <td colspan=5><span><FONT color=#000000 size=1><STRONG>�걨�������ɣ�</STRONG></FONT> </span></td>
                 
                  </tr><tr>
                     <td colspan =5>
            <textarea id =jmsyj  name ="jmsyj"  style="width:98%" rows = 5 readonly >
            </textarea>
 
                  </td>
                  
                  </tr>
                  
         
              </TABLE><BR>
		 	
             <TABLE id="" class=table-99 cellSpacing=0 cellPadding=0 width="80%" align=center border=0>
              <TR class=black9>
                <TD class=2-td2-t-left width="5%" noWrap>�Ƿ��о����</TD>
                
                <TD class=2-td2-t-center width="10%" noWrap>
                <SELECT style="WIDTH: 10%" name=sfsjsp> 
                <OPTION value=1>��</OPTION> 
                <OPTION value=0 selected>��</OPTION>
                </SELECT> 
                </TD></TR>
            
           </TABLE><BR> 
           
            <TABLE width="100%" border=0>
              <TBODY>
              <TR>
                 <TD width="42%">&nbsp;</TD>
                <TD width="8%" id = baocun><INPUT onclick="befSave('Save');return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=bc1 style="CURSOR: hand" 
                  accessKey=s height=22 alt=���� src="<%=static_contextpath%>images/bc-s1.jpg" 
                  type=image width=79 border=0></TD>
                <TD width="8%"><INPUT onclick="tuichu();return false;" 
                  tabIndex=-1 
                  onmouseover="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" 
                  onmouseout=MM_swapImgRestore() id=tc1 style="CURSOR: hand" 
                  accessKey=c height=22 alt=�˳� src="<%=static_contextpath%>images/tc-c1.jpg" 
                  type=image width=79 border=0></TD>
                <TD 
            width="42%">&nbsp;</TD></TR></TBODY></TABLE><BR></DIV></DIV></DIV></DIV></TD></TR></TBODY></TABLE><BR></FORM>
      <TABLE height=33 cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD height=9 background=<%=static_contextpath%>images/q-bottom-bg-01.jpg colSpan=2 
          noWrap></TD></TR>
        <TR>
          <TD noWrap><FONT size=2>&nbsp;&nbsp;&nbsp;* ����ʹ�� IE 5.5 ���ϣ��ֱ��� 
            800*600 �������վ</FONT></TD>
          <TD height=24 align=right><IMG alt=�а쵥λ���廪ͬ������ɷ����޹�˾2008_APR11_01 
            src="<%=static_contextpath%>images/q-bottom-tu-01.jpg" width=184 
        height=24></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>

</html:form>


<TABLE id=zdyt_list_hidden class=table-99 cellSpacing=0 cellPadding=0 width="80%" align=center border=0>
                 <tbody  id = zdyt_hidden disabled=true style="display:none">
                	<TR class=black9 >
                <TD class=2-td2-left noWrap width="5%"><input  name= zdytxh value=1 size =2 class=inbright readOnly/></TD>
                
                <TD class=2-td2-t-left noWrap width="10%">
               <select name="zdyt" onchange="jisuan()" >
             	<%
             	List zdytlist = (List)form.getZdyt_list();
             	for(int i=0;i<zdytlist.size();i++){
             		LabelValueBean bean = (LabelValueBean)zdytlist.get(i);
             		%>
             		<option value='<%=bean.getValue() %>'><%=bean.getLabel() %></option>
             		<%
             	}
             	%>
				</select>
              
             
                </TD>
                <TD class=2-td2-left noWrap width="10%">
				
             	<select name="syse" onchange="jisuan()" style="width:95%">
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
                <TD class=2-td2-left noWrap width="10%"><INPUT onblur=jisuan(); size=12 style ="TEXT-ALIGN: right;"
                  value=0.00 name=jsmj> </TD>
                <TD class=2-td2-left noWrap width="10%"><INPUT size=12 name=jzse class=inbright readOnly style ="TEXT-ALIGN: right;" value= 0.00> </TD>
                <TD class=2-td2-left noWrap width="10%"> <INPUT size=12 name=jmmj value ="0.00" onblur=jisuan(); style ="TEXT-ALIGN: right;" /></TD>
                <TD class=2-td2-left noWrap width="10%"><INPUT  size=12  class=inbright readOnly
                  value="0.00" name=jmse style ="TEXT-ALIGN: right;"> </TD>
                <TD class=2-td2-left noWrap width="10%"><INPUT class=inbright readOnly 
                  size=12 value=0.00 name=ysmj style ="TEXT-ALIGN: right;"/> </TD>
                <TD class=2-td2-left noWrap width="10%"><INPUT class=inbright readOnly 
                  size=12 value=0.00 name=ynse style ="TEXT-ALIGN: right;"> </TD>
                <TD class=2-td2-center noWrap width="15%"><SPAN 
                  onclick=delRow_zdyt(this) onmouseover="this.style.color='red'" 
                  onmouseout="this.style.color='black'" 
                  style="CURSOR: pointer"><U>ɾ��</U></SPAN> </TD></TR>
                
                </tbody>
                </TABLE>

<TABLE id="jmlb_list_hidden" class=table-99 cellSpacing=0 cellPadding=0 width="80%" align=center border=0 style="display:none">
                
                <tbody id="jmlb_hidden" style="display:none" disabled=true>
                <TR class=black9>
                <TD class=2-td2-left noWrap width="5%"><input id = "jmlbxh" name =jmlbxh value='1' size =2 class=inbright readOnly /> </TD>
                <TD class=2-td2-t-left noWrap width="30%">
             	<select name="jmlbdm_jmmx" onchange="getJMSYJ();">
             	<%
             	List jmlblist = (List)form.getJmlb_list();
             	for(int i=0;i<jmlblist.size();i++){
             		LabelValueBean bean = (LabelValueBean)jmlblist.get(i);
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
                  style="CURSOR: pointer"><U>ɾ��</U></SPAN> </TD></TR>
                
                </tbody>

              </TABLE>




</body>




</html:html>