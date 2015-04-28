<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<%@ page import="com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx06.web.Basx06Form" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator"%>
<html>  
  <head>
    <title>减免税备案记录</title>
    <link href="../../../css/text.css" rel="stylesheet" type="text/css">
	
	<script language="JavaScript" type="text/javascript"
		src="../../../js/My97DatePicker/WdatePicker.js"></script>
    <script language=JavaScript src="../../../js/treatImage.js">
    </script>
    <script language=JavaScript src="../../../js/compute.js">
    </script>
    <script language=JavaScript src="../../../js/function.js">
    </script>
    <script language=JavaScript src="../../../js/smsb_common.js">
    </script>
    <script language=JavaScript src="../../../js/Bolan.js">
    </script>
    <script language=JavaScript src="../../../js/MathString.js">
    </script>
    <script language=JavaScript src="../../../js/Stack.js">
    </script>
    <script language=JavaScript src="../../../js/reader.js">
    </script>
    <script language=JavaScript src="../../../js/gmit_selectcontrol.js">
    </script>
    <script language="JavaScript" src="../../../js/qysdsnew.js">
    </script>
    <script language="JavaScript" src="../../../js/calculate.js">
    </script>
  </head>
    <script language="JavaScript">
	function onKeyDown() { // 禁止刷新，回退
	 if ((event.keyCode == 8 && event.srcElement.type ==undefined) || (event.keyCode == 116)) {
	    event.keyCode = 0;
	    event.returnValue = false;
	   }
	}
	document.onkeydown = onKeyDown;
  </script>
  <script type="text/javascript" language="JavaScript">
  <%
  		Basx06Form basx = (Basx06Form)request.getAttribute("basx06Form");
  	// 构造减免税备案事项代码
	    if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JNJSXMLXDM) != null)
	    {
	        out.print("var arySelect_lyzl = new Array(");
	        out.print("[\"\",\"\"]");
	        String[][] lyzldm = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_JNJSXMLXDM);
	        for(int i = 0 ; i < lyzldm.length ; i++) 
	        {
	         	out.print(",[\"" + lyzldm[i][0] + "\",\"" +lyzldm[i][1] +"\"]");
	        }
	        out.println(");");
	    }
	%>   
	<%/*初始化*/%> 
	function doInit(){
	    //初始化资料
		var zl = document.forms[0].zl.value.split(";");
		for(row_index=0;row_index<zl.length;row_index++){
			var new_row=Table1.insertRow(Table1.rows.length);
			zlnr = zl[row_index].substring(0,zl[row_index].indexOf("|"));
			zlsfkysc = zl[row_index].substring(zl[row_index].length-1,zl[row_index].length);
		    addzlmc(row_index,zlnr,new_row);
		    if(document.forms[0].czlx.value=="1")addzlxz(row_index,row_index,new_row,"1");
		    if(zlsfkysc == "1"){
		    	addzlan("1",row_index,new_row);
		    }else{
		    	addzlan("2",row_index,new_row);
		    }
		}
		
		_addOptionsToSelect(document.forms[0].lyzlSelect, arySelect_lyzl);
		document.forms[0].lyzlSelect.value = document.all("jnjsxmlxdm").value;
		
		//初始化按钮，当为修改状态时显示保存
		var czlx = document.forms[0].czlx.value;

		if(czlx == "2"){
			document.all.jssq.style.display = "none";
			document.all.bg.style.display = "none";
		}else if(czlx == "6"){
			document.all.jssq.style.display = "none";
			document.all.bc.style.display = "none";
		}else{
			document.all.bc.style.display = "none";
			document.all.bg.style.display = "none";
		}
		
	}
	
	<%/*添加资料*/%>
	function addzl(){	
		var zlmc=document.getElementById("zlmc").value;
   	    var zlmc=zlmc.replace(/(^\s*)|(\s*$)/g, "");  	    			  
		if(zlmc != ""){
			row_index++;  
	    	var new_row=Table1.insertRow(Table1.rows.length); 
	    	addzlmc(row_index,zlmc,new_row);
	    	if(document.forms[0].czlx.value=="1")addzlxz(row_index,row_index,new_row,"2");
	    	addzlan("2",row_index,new_row);
	    	document.getElementById("zlmc").value = "";
		}else{
			document.getElementById("zlmc").value = "";
			alert("国家税务总局或北京市地方税务局要求提交的其他资料内容不能为空");
		}
	}
	
	<%/*添加资料名称*/%>
	function addzlmc(row_index,zlmc,new_row){
	    new_row.setAttribute("id", "row"+row_index);   
	    var new_col=new_row.insertCell();
	    if(row_index == 0){
	    	new_col.className="2-td2-t-left-textleft";
	    }else{
	    	new_col.className="2-td2-left-textleft";
	    }
	    new_col.setAttribute("id",row_index);
	    new_col.innerHTML=zlmc;
	}
	<%/*初始资料添加按钮*/%>
	function addzlan(lx,row_index,new_row){
	    var new_col=new_row.insertCell();
	    if(row_index == 0){
	    	new_col.className="2-td2-t-center";
	    }else{
	    	new_col.className="2-td2-center";
	    }
	    if(lx == "1"){
	    	new_col.innerHTML="&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;";  
	    }else{
	    new_col.innerHTML="<img src='"+"<%=SfResourceLocator.getContextPath(request)%>"+"images//shanchu2.jpg'  onclick=delete_row('row"+row_index+ "') name='shanchu' width='79' height='22' border='0' id='shanchu'>";
	    }
	    
	}
		<%/*初始资料添加按钮*/%>
	function addzlxz(xh,row_index,new_row,lx){
	    var new_col=new_row.insertCell();
	    if(row_index == 0){
	    	new_col.className="2-td2-t-left-textleft";
	    }else{
	    	new_col.className="2-td2-left-textleft";
	    }
	    var dxk 
	    if(lx == "1"){	
	    	dxk = "<input type=\"radio\"   name=zlradio"+xh+" value=\"0\">有</input>&nbsp\;<input type=\"radio\"  id=lack_zl_radio_"+xh+" name=zlradio"+xh+"  value=\"1\" checked>无</input>";
	    }else{	    	
	    	dxk ="&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;&nbsp\;";   
	    }
	    new_col.innerHTML=dxk;  
	    
	}
	
	function checkZlRadio(){
		var tag = document.getElementsByTagName('input'); 
		
		for(var i=0;i <tag.length;i++){ 
		  var tagid=tag[i].id;
		   if(tagid.indexOf("lack_zl_radio_")>=0){
		   		var zlRadio=document.getElementById(tagid);
		   		if(zlRadio.checked==true){
		   			alert("资料清单有无选项必须全部为有才可以接受申请！");
		   			return false;
		   		}
		   }
		}
		return true;
	}
	<%/*删除资料行*/%>
	function delete_row(rname){
	  if(window.confirm("是否删除该资料?")){	 
		var zlstring = "";
    	var i; 
	    i=Table1.rows(rname).rowIndex;
	    Table1.deleteRow(i);
	    var zl = document.forms[0].zl.value.split(";"); 
	    for(var n=0;n<zl.length;n++){
	    	if(i!=n){
	    		zlstring += zl[n]+"\;";
	    	}
	    }
	    document.all.zl.value=zlstring.substring(0,zlstring.length-1);
	    }
    } 
    <%/*保存资料清单*/%>
	function zlqd(){
		var zlstring = document.all.zl.value;
		var zl = document.forms[0].zl.value.split(";");
		var i = zl.length+1;
		for(;i<=row_index;i++){
			if(document.getElementById(i)!=null){
				var temp=document.getElementById(i).innerHTML;
				zlstring+="\;"+temp+"|0";
			}
		}
		document.all.zl.value=zlstring;
	}
	<%/*鼠标移动*/%>
	function change(){
		var qdsr = document.all("qdsr").value;
		if(qdsr == ""){
			alert("综合利用资源生产符合国家产业政策规定的产品所取得的收入不能为空！");
			document.forms[0].qdsr.focus();
			return false;
		}else{
			if(!(checkvalue(document.all("qdsr"),0)&&formatCurrency(document.all("qdsr"),0))){
				return false;
			}
		}
		var qdsr = document.all("qdsr").value;
		var jjsr = round2(qdsr*0.1);
		document.all("jjsr").value = jjsr;
	}
	
	<%/*减免税额和减免比例控制*/%>
	function sehblkz(lx){
		if(lx == "1"){
			document.forms[0].bajmbl.value = "";
			document.getElementById("bajmse").readOnly=false;
			document.getElementById("bajmbl").readOnly=true;
		}else{
			document.forms[0].bajmse.value = "";
			document.getElementById("bajmse").readOnly=true;
			document.getElementById("bajmbl").readOnly=false;
		}
	}
	<%/*效验主表信息*/%>
	function checkZbxx(){
		//效验起始截止日期
		var qsrq = document.forms[0].qsrq.value;
		var jzrq = document.forms[0].jzrq.value;
		if(qsrq == ""){
			alert("请填写起始日期！");
			document.forms[0].qsrq.focus();
			return false;
		}
		if(jzrq == ""){
			alert("请填写截止日期！");
			document.forms[0].jzrq.focus();
			return false;
		}
		//起始日期不能大于截止日期
		if(!checkDate(qsrq,jzrq)){
			return false;
		}
		//效验纳税额和比例
		var bajmse = document.forms[0].bajmse.value;
		var bajmbl = document.forms[0].bajmbl.value;
		if(bajmse != ""){
			if(!checkvalue(document.all("bajmse"),0)){
				return false;
			}
		}
		if(bajmbl != ""){
			if(!checkvalue(document.all("bajmbl"),2)){
				return false;
			}
		}
		//if(bajmse == "" && bajmbl == ""){
		//	alert("请填写减免税额或减免比例！");
		//	document.forms[0].bajmse.focus();
		//	return false;
		//}
		//减免税政策执行情况
		var jmszczxqk = document.forms[0].jmszczxqk.value;
		if(jmszczxqk == ""){
			alert("请填写减免税政策执行情况！");
			document.forms[0].jmszczxqk.focus();
			return false;
		}
		
		return true;
	}
	<%/*效验子表信息*/%>
	function checkYm(){
	    var bajmse = document.all("bajmse").value;
	    var bajmbl = document.all("bajmbl").value;
	    //if(bajmse.length == 0 && bajmbl.length == 0) {
	    //	alert("减免税额减、减免比例必须填写一项");
	    //   return false;
	    //}
	    if(bajmse.length > 0 && bajmbl.length > 0) {
	    	alert("减免税额和减免比例只能选择一项填写！");
	        return false;
	    }
		if(bajmse != ""){
			if(!checkvalue(document.all("bajmse"),0)){
				return false;
			}
		}
		if(bajmbl != ""){
			if(!checkvalue(document.all("bajmbl"),2)){
				return false;
			}
		}	    	
		if( document.all("jnjsxmlx").value == "") {
			alert("项目所得类型不能为空！");
			document.forms[0].jnjsxmlx.focus();
			return false;
		}
		if(document.all("zlmcdm").value == "") {
			alert("有关部门认定的项目证明资料名称不能为空！");
		    document.forms[0].zlmcdm.focus();			
			return false;
		}
		if(document.all("dybsrnd").value == "") {
			alert("取得第一笔收入的时间不能为空！");
		    document.forms[0].dybsrnd.focus();			
			return false;
		}
		var dybsrnd=document.all.dybsrnd.value;
		var band=document.all.band.value;
		var reg=/^[0-9]{4}$/
		if(dybsrnd==""){
			alert('"取得第一笔收入的时间"不能为空！');
			document.all.dybsrnd.value="";
			document.all.dybsrnd.focus();
			return false;
			}
		if(dybsrnd != ""){
			if(!reg.test(dybsrnd)){
				alert('"取得第一笔收入的时间"的输入格式不正确！');
				document.all.dybsrnd.focus();
				return false;
				}
			if(parseInt(dybsrnd)>parseInt(band)){
				alert('"取得第一笔收入的时间"不能早于"备案年度"！');
				document.all.dybsrnd.focus();
				document.all.dybsrnd.value="";
				return false;
				}
		}		
		var mzqsnd = document.all("mzqsnd").value;		
		var mzzznd = document.all("mzzznd").value;		
		var jzqsnd = document.all("jzqsnd").value;		
		var jzzznd = document.all("jzzznd").value;
		
		if(isNotNull(mzqsnd)){
			if(!isFullDate(mzqsnd,1,null,false)){
				alert("免征起始年度格式不正确！");
				return false;
			}
		}
		if(isNotNull(mzzznd)){
			if(!isFullDate(mzzznd,1,null,false)){
				alert("免征终止年度格式不正确！");
				return false;
			}
		}
		if(isNotNull(jzqsnd)){
			if(!isFullDate(jzqsnd,1,null,false)){
				alert("减半征收起始年度格式不正确！");
				return false;
			}
		}
		if(isNotNull(jzzznd)){
			if(!isFullDate(jzzznd,1,null,false)){
				alert("减半征收终止年度格式不正确！");
				return false;
			}
		}
		if(isNotNull(mzqsnd)&&isNotNull(mzzznd)){
			if(mzqsnd>mzzznd){
				alert("免征起始年度不能大于免征终止年度");
				return false;
			}
		}
		if(isNotNull(jzqsnd)&&isNotNull(jzzznd)){
			if(jzqsnd>jzzznd){
				alert("减征起始年度不能大于减征终止年度");
				return false;
			}
		}
		if(isNotNull(mzqsnd)&&isNotNull(jzqsnd)){
			if(mzqsnd>jzqsnd){
				alert("免征起始年度不能大于减征起始年度");
				return false;
			}
		}
		if(isNotNull(mzqsnd)&&isNotNull(jzzznd)){
			if(mzqsnd>jzzznd){
				alert("免征起始年度不能大于减征终止年度");
				return false;
			}
		}
		if(isNotNull(mzzznd)&&isNotNull(jzqsnd)){
			if(mzzznd>jzqsnd){
				alert("免征终止年度不能大于减征起始年度");
				return false;
			}
		}
		if(isNotNull(mzzznd)&&isNotNull(jzzznd)){
			if(mzzznd>jzzznd){
				alert("免征终止年度不能大于减征终止年度");
				return false;
			}
		}								
		return true;
	}
	<%/*接受申请*/%>
	function jssq()
	{
		if(checkZbxx() && checkYm()&& checkZlRadio()){
			zlqd();
			if(window.confirm("是否接受该纳税人的减免税备案申请？")){
				//用来处理下拉框保存后页面回显
				document.forms[0].jnjsxmlxdm.value = document.forms[0].jnjsxmlx.value;
	      		if(window.confirm("是否跳转至文书打印预览页面")){
	      			document.forms[0].actionType.value="SMShPrint";
					document.forms[0].submit();
	      		}else{
	      			document.forms[0].actionType.value="Jssq";
					document.forms[0].submit();
	      		}
      		}
		}
	}
	
	<%/*修改保存*/%>
  	function save()
	{
		if(checkZbxx() && checkYm()&& checkZlRadio()){
			zlqd();
			if(window.confirm("是否确定保存?")){
				//用来处理下拉框保存后页面回显
				    document.forms[0].jnjsxmlxdm.value = document.forms[0].jnjsxmlx.value;
	      			document.forms[0].actionType.value="Jssq";
					document.forms[0].submit();
	      		
      		}
		}
	}	
	
		<%/*修改保存*/%>
  	function bg()
	{
		if(checkZbxx() && checkYm()&& checkZlRadio()){
			zlqd();
			if(window.confirm("是否确定变更?")){
				//用来处理下拉框保存后页面回显
				    document.forms[0].jnjsxmlxdm.value = document.forms[0].jnjsxmlx.value;
	      			document.forms[0].actionType.value="Bg";
					document.forms[0].submit();
	      		
      		}
		}
	}	
	<%/*返回*/%>
	function back()
	{
		doSubmitForm('Back');
	}
	<%/*增加年*/%>	
	function addYear() {
    var t = document.all("dybsrnd").value;
    if( validateDybsrnd()) {
    	document.all("mzqsnd").value = t;
	    document.all("mzzznd").value = parseInt(t) + parseInt("2");
	    document.all("jzqsnd").value = parseInt(document.all("mzzznd").value) + parseInt("1");
	    document.all("jzzznd").value = parseInt(document.all("jzqsnd").value) + parseInt("2");
    } else {
      document.forms[0].dybsrnd.focus();
      return false; 
    }	
	
}

	<%/*增加年*/%>	
	function changeYear() {
    var t = document.all("dybsrnd").value;
  	var band = document.all("band").value;    
    if( validateDybsrnd() && t < band) {
        /*
    	document.all("mzqsnd").value = t;
	    document.all("mzzznd").value = parseInt(t) + parseInt("2");
	    document.all("jzqsnd").value = parseInt(document.all("mzzznd").value) + parseInt("1");
	    document.all("jzzznd").value = parseInt(document.all("jzqsnd").value) + parseInt("2");
	    */
	    return true;
    } else {
	  alert("输入错误，请从新输入！");
	  document.all.dybsrnd.value="";    
      document.forms[0].dybsrnd.focus();
      return false; 
    }	
	
}

function validateDybsrnd() {
	var dybsrnd = document.all("dybsrnd").value;
  	var band = document.all("band").value;	
	if(!isInt(dybsrnd)) {
	   alert("取得第一笔收入的时间必须是4位数字 如：2005");
	   document.all.dybsrnd.value="";
	   document.forms[0].dybsrnd.focus();
       return false;
	}
	if(dybsrnd.length != 4) {
	   alert("取得第一笔收入的时间必须是4位数字 如：2005");
	   document.all.dybsrnd.value="";
	   document.forms[0].dybsrnd.focus();
     return false;
	}
	/*
	if(dybsrnd >= band || dybsrnd < band - 5) {
  	   alert("取得第一笔收入的时间必须是大于等于备案年度减5小于备案年度");
  	   document.all.dybsrnd.value="";
  	   document.forms[0].dybsrnd.focus();
       return false;
  	}
  	*/	
	return true;
}	
	<%/*验证免征年度*/%>
	function validateMznd() {
	  var mzqsnd = document.all("mzqsnd").value;
	  var mzzznd = document.all("mzzznd").value;
	  if(mzqsnd > mzzznd) {
	  	alert("免征起始年度不能大于免征终止年度！");
	  	document.all.mzqsnd.value=""; 	
	  }
	  if(mzqsnd.length != 4) {
	   alert("免征起始年度必须是4位数字 如：2005");
	   document.all.mzqsnd.value="";
	   document.forms[0].mzqsnd.focus();
	 }
	  if(mzzznd.length != 4) {
	   alert("免征终止年度必须是4位数字 如：2005");
	   document.all.mzzznd.value="";
	   document.forms[0].mzzznd.focus();
	 }	 	
	   return true;	     	  
	}

	<%/*验证减证年度*/%>
	function validateJznd() {
	  var jzqsnd = document.all("jzqsnd").value;
	  var jzzznd = document.all("jzzznd").value;
	  if(jzqsnd > jzzznd) {
	  	alert("减征起始年度不能大于免征终止年度！");
	  	document.all.jzqsnd.value=""; 	
	  }
	  if(jzqsnd.length != 4) {
	   alert("减征起始年度必须是4位数字 如：2005");
	   document.all.jzqsnd.value="";
	   document.forms[0].jzqsnd.focus();
	 }
	  if(jzzznd.length != 4) {
	   alert("减征终止年度必须是4位数字 如：2005");
	   document.all.jzzznd.value="";
	   document.forms[0].jzzznd.focus();
	 }
	 return true;		   	  
	}
  </script>
  
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onload="doInit()">
    <%@ include file="../../include/header.jsp" %> 
     
	  
      <html:form action="/webapp/smsb/qysds/jmsbagl/Basx06Action.do" method="post">
        <html:hidden property="actionType" />
        <html:hidden property="basqwsh" />
        <html:hidden property="basqbh" />
        <html:hidden property="band" />
        <html:hidden property="jsjdm" />
        <html:hidden property="xh" />
        <html:hidden property="jnjsxmlxdm" />
        <html:hidden property="zl" />
        <html:hidden property="czlx" />
        <html:hidden property="clbs" />
        <table width="75%" align="center" cellspacing="0" height="480">
          <tr>
            <td class="1-td1" colspan="7" height="30">
              录入从事符合条件的环境保护、节能节水项目的所得税减免税备案事项
            </td>
          </tr>
          <br>
          <tr>
            <td class="1-td2" colspan="7" valign="top">
            <br>
            	<table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
                    <tr>
                      <td  class="2-td2-t-left">
                          <strong>备案申请编号</strong>
                      </td>
                      <td  width="22%"class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx06Form" property="basqbh"/>
                      </td>
                      <td  class="2-td2-t-left">
                          <strong>计算机代码</strong>               
                      </td>
                      <td  class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx06Form" property="jsjdm"/>
                      </td>                  
                      <td  class="2-td2-t-left">                       
                          <strong>纳税人名称</strong>
                      </td>
                      <td width="22%"  class="2-td2-t-center">
                      &nbsp;<bean:write name="basx06Form" property="nsrmc"/>
                      </td>
                    </tr>
                    <tr>
                      <td  class="2-td2-left">                        
                          <strong>主管税务所</strong>    
                      </td>
                      <td  width="22%"class="2-td2-left">
                      	&nbsp;<bean:write name="basx06Form" property="zgsws"/>
                      </td>
                      <td  class="2-td2-left">
                          <strong>经济类型</strong>               
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx06Form" property="jjlx"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>所属行业</strong>
                      </td>
                      <td width="22%"  class="2-td2-center">
                      	&nbsp;<bean:write name="basx06Form" property="sshy"/>
                      </td>
                    </tr>
                   <tr>
                      <td  class="2-td2-left">
                         <strong>联系人</strong>
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx06Form" property="lxr"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>联系电话</strong>
                      </td>
                      <td width="22%"  class="2-td2-center" colspan="3">
                       &nbsp;<bean:write name="basx06Form" property="lxdh"/>
                      </td>
                    </tr>
                  </table>
                  
                  
                  </br>
                  <table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">            
              <tr>
                <td class="2-td2-t-left" width="10%">
                  起始时间<font color="red">*</font>
                </td>
                <td class="2-td2-t-left" width="15%"> 
                  	<html:text property="qsrq" size="12" onclick="WdatePicker()"></html:text>
                </td>
                <td class="2-td2-t-left" width="10%">
                  截止时间<font color="red">*</font>
                </td>
                <td class="2-td2-t-left" width="15%">
                	<html:text property="jzrq" size="12" onclick="WdatePicker()"></html:text>
                </td>                
            
                <td class="2-td2-t-left" width="10%">
                  减免税额
                </td>
                <td class="2-td2-t-left" width="15%">
                  	<html:text property="bajmse" size="10"/>元
                </td>
                <td class="2-td2-t-left" width="10%">
                  减免比例
                </td>
                <td class="2-td2-t-center" width="15%">
	                <html:text property="bajmbl" size="10"/>%
                </td>
                
              </tr>
              <tr>
                <td class="2-td2-left">
                  减免税政策
                  <br/>
                  执行情况：<font color="red">*</font>
                </td>
                <td class="2-td2-center" colspan="7">
                	<div align="left">
                		&nbsp;&nbsp;&nbsp;<html:textarea property="jmszczxqk" rows="5" cols="90"></html:textarea>
                	</div>
                  <td>
              </tr>
              <tr>
                <td class="2-td2-center" colspan="8">
                	<br/>
                  <div align="left">
                    &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                    减免税备案资料清单：
                  </div>
                  
                  <table width="95%" cellspacing=0 border=0 id="Table1">                   
                  </table>
                  </br>
                  <table width="95%" cellspacing=0 border=0>  
                  	<tr>
                  		<td class="2-td2-t-left">
                  			国家税务总局或北京市地方税务局要求提交的其他资料
                  		</td>
                  		<td class="2-td2-t-left">
                  			<input type="text" size="60"  name="zlmc" value="" />
                  		</td>
                  		<td class="2-td2-t-center">
                  			<img src="<%=static_contextpath%>images//tianjia2.jpg" onclick="addzl()" name="tianjia" width="79" height="22" border="0" id="tianjia">              			
                  		</td>
                  	</tr>
                  </table>	 
                  </br>
                  <td>
              </tr>
            </table>
                  
            	  </br>
			<table class="table-99" cellspacing="0" width="98%">
				<tr width="98%">
					<td width="40%" class="2-td2-t-left">
					<div align="left">项目所得类型<font color="red">
					* </font></div>
					</td>
					<td class="2-td2-t-center" >
					<div id="mx_zsfsdm_epx_visible_1" align="left"><html:select
						name="basx06Form" styleId="lyzlSelect" property="jnjsxmlx"
						style="width:222px" /></div>
					</td>
				</tr>
				<tr>
					<td  class="2-td2-left">
					<div align="left">有关部门认定的项目证明资料名称<font color="red">
					* </font></div>
					</td>
					<td colspan="3" class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left"><html:text
						property="zlmcdm" maxlength="200" size="30" ></html:text></div>
					</td>
				</tr>
				<tr>
					<td  class="2-td2-left">
					<div align="left">取得第一笔收入的时间<font color="red">
					* </font></div>
					</td>
					<td colspan="3" class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left"><html:text
						property="dybsrnd" maxlength="4" size="30"></html:text>年</div>

					</td>
				</tr>
				<tr>
					<td  class="2-td2-left">
					<div align="left">免征年度</div>
					</td>
					<td colspan="3" class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left">从 <html:text
						property="mzqsnd" maxlength="4" size="5"></html:text> 年至
					<html:text property="mzzznd" maxlength="4" size="5"></html:text>
					年</div>
					</td>
				</tr>
				<tr>
					<td  class="2-td2-left">
					<div align="left">减半征收年度</div>
					</td>
					<td colspan="3" class="2-td2-center">
					<div id="mx_zsfsdm_epx_visible_1" align="left">从 <html:text
						property="jzqsnd" maxlength="4" size="5"></html:text> 年至
					<html:text property="jzzznd" maxlength="4" size="5"></html:text>
					年</div>
					</td>
				</tr>
			</table>
                 <br/>
                 <table class="table-99" cellspacing="0">
                    <tr>
                      <td width="13%" class="2-td2-t-left">
                        <div align="right">
                          录入日期
                        </div>
                      </td>
                      <td width="27%" class="2-td2-t-left">
                        <div align="left">
                        	<html:text property="mr_lrrq" readonly="readonly" style="background-color:#efefef"></html:text>
                        </div>
                      </td>
                      <td width="10%" class="2-td2-t-left">
                        <div align="right">
                          	备案年度
                        </div>
                      </td>
                      <td width="30%" class="2-td2-t-left">
                        <div align="left">
                        	<html:text property="mr_band" readonly="readonly" style="background-color:#efefef"></html:text>
                        </div>
                      </td>
                      <td width="10%" class="2-td2-t-left">
                        <div align="right">
                          录入人
                        </div>
                      </td>
                      <td width="30%" class="2-td2-t-center">
                        <div align="left">
                        	<html:text property="mr_lrr" readonly="readonly" style="background-color:#efefef"></html:text>
                        </div>
                      </td>
                    </tr>
                  </table>
                  <br/>
                  <table width="100%" border="0" align="center">
                    <tr align="center">      
	                    <td>
	                    	<div id="jssq" style="display: inline;">
	      				  	<a style="cursor:hand" onClick="jssq()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('jishoushenqing','','<%=static_contextpath%>images//b_jssq2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//b_jssq1.jpg" name="jishoushenqing" width="79" height="22" border="0" id="jishoushenqing">
	                        </a>
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                        </div>
	                        <div id="bg" style="display: inline;">
	      				  	<a style="cursor:hand" onClick="bg()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('bgzx','','<%=static_contextpath%>images//b-bgzx2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//b-bgzx1.jpg" name="bgzx" width="95" height="22" border="0" id="bgzx">
	                        </a>
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                        </div>
	                        <div id="bc" style="display: inline;">
	      				  	<a style="cursor:hand" onClick="save()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images//baocun2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//baocun1.jpg" name="baocun" width="79" height="22" border="0" id="baocun">
	                        </a>
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                        </div>
	                        <a style="cursor:hand" onClick="back()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images//fanhui2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//fanhui1.jpg" name="fanhui" width="79" height="22" border="0" id="fanhui">
	                        </a>
	                    </td>
                    </tr>
                  </table> 
                  <br>                  
                  <br>
            </td>
          </tr>
        </table>
        <br>
        <%@ include file="../../include/footer.jsp" %>
      </html:form>
  </body>

</html>