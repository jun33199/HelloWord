<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<%@ page import="com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx13b.web.Basx13BForm" %>
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
  		Basx13BForm basx = (Basx13BForm)request.getAttribute("basx13BForm");
  	// 构造减免税备案事项代码
	    if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GXJSQYLYDM) != null)
	    {
	        out.print("var arySelect_gxjsly = new Array(");
	        out.print("[\"\",\"\"]");
	        String[][] gxjslydm = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GXJSQYLYDM);
	        for(int i = 0 ; i < gxjslydm.length ; i++) 
	        {
	         	out.print(",[\"" + gxjslydm[i][0] + "\",\"" +gxjslydm[i][1] +"\"]");
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
		    if(zlsfkysc == "1"){
		    	addzlan("1",row_index,new_row);
		    }else{
		    	addzlan("2",row_index,new_row);
		    }
		}
		
		_addOptionsToSelect(document.forms[0].gxjslySelect, arySelect_gxjsly);
		document.forms[0].gxjslySelect.value = document.all("gxjslydm").value;
		//设置高新技术领域下拉框不可编辑
		document.all("gxjsly").disabled="disabled";
		//设置投资年度的信息如果已经已完成备案标识如果为已完成，则不可编辑
		var band = document.all("band").value;
		var tze_v = document.forms[0].tze.value.split(";");
		var kdke_v = document.forms[0].kdke.value.split(";");
		var sjdke_v = document.forms[0].sjdke.value.split(";");
		var jzdke_v = document.forms[0].jzdke.value.split(";");
		var bjkz_v = document.forms[0].bjkz.value.split(";");
		var num = 0;
		for(var n=2006;n<=band;n++){
			var tze = "tze"+n;
			var kdke = "kdke"+n;
			var sjdke = "sjdke"+n;
			var jzdke = "jzdke"+n;
			var bjkz = "bjkz"+n;
			if(tze_v[num] == "null" || tze_v[num] == ""){
				document.all(tze).value = "0.00";
			}else{
				document.all(tze).value = tze_v[num];
				formatCurrency(document.all(tze),0);
			}
			if(kdke_v[num] == "null" || kdke_v[num] == ""){
				document.all(kdke).value = "0.00";
			}else{
				document.all(kdke).value = kdke_v[num];
				formatCurrency(document.all(kdke),0);
			}
			if(sjdke_v[num] == "null" || sjdke_v[num] == ""){
				document.all(sjdke).value = "0.00";
			}else{
				document.all(sjdke).value = sjdke_v[num];
				formatCurrency(document.all(sjdke),0);
			}
			if(jzdke_v[num] == "null" || jzdke_v[num] == ""){
				document.all(jzdke).value = "0.00";
			}else{
				document.all(jzdke).value = jzdke_v[num];
				formatCurrency(document.all(jzdke),0);
			}
			//判断已完成备案表示是否为已完成，已完成该条记录不可编辑
			document.all(bjkz).value = bjkz_v[num];
			if(bjkz_v[num]=="0"){
				document.all(tze).readOnly=true;
				document.all(sjdke).readOnly=true;
			}else{
				document.all(tze).readOnly=false;
				document.all(sjdke).readOnly=false;
			}
			num++;
		}
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
		var value=zlmc.replace(/(^\s*)|(\s*$)/g, "");  
      	if(value.length==0){
      		alert("国家税务总局或北京市地方税务局要求提交的其他资料内容不能全部为空格");
      		document.all.zlmc.focus();
      		return;
          	} 	  
		if(zlmc != ""){
			row_index++;  
	    	var new_row=Table1.insertRow(Table1.rows.length); 
	    	addzlmc(row_index,zlmc,new_row);
	    	addzlan("2",row_index,new_row);
	    	document.getElementById("zlmc").value = "";
		}else{
			alert("国家税务总局或北京市地方税务局要求提交的其他资料内容不能为空");
		}   
	    
	}
	<%/*添加资料名称*/%>
	function addzlmc(row_index,zlmc,new_row){
	    new_row.setAttribute("id", "row"+row_index);   
	    var new_col=new_row.insertCell(0);
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
	    var new_col=new_row.insertCell(1);
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
	<%/*删除资料行*/%>
	function delete_row(rname){
		if(window.confirm("是否删除该资料？")){
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
	function change(lx,xh){
		var tze = "tze"+xh;
		var kdke = "kdke"+xh;
		var sjdke = "sjdke"+xh;
		var jzdke = "jzdke"+xh;
		if(lx == "1"){
			var tze_v = document.all(tze).value;
			if(tze_v != ""){
				if(!(checkvalue(document.all(tze),0)&&formatCurrency(document.all(tze),0))){
					return false;
				}
			}
		}else{
			var sjdke_v = document.all(sjdke).value;
			if(sjdke_v != ""){
				if(!(checkvalue(document.all(sjdke),0)&&formatCurrency(document.all(sjdke),0))){
					return false;
				}
			}
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

		if(bajmse !="" && bajmbl!=""){
			alert("减免税额和减免比例只能选择一项填写！");
			document.forms[0].bajmse.focus();
			return false;
			}
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
		var btzqyswdjzh = document.all("btzqyswdjzh").value;
		if(btzqyswdjzh == "" ){
			alert("被投资企业税务登记证号不能为空!");
			document.forms[0].btzqyswdjzh.focus();
			return false;
		}
		var btzqymc = document.all("btzqymc").value;
		 if(btzqymc == "" ){
			alert("被投资企业名称不能为空!");
			document.forms[0].btzqymc.focus();
			return false;
		 }
		 var gxjsly = document.all("gxjsly").value;
		 if(gxjsly == "" ){
			alert("中小高新技术企业领域不能为空!");
			document.forms[0].gxjsly.focus();
			return false;
		 }
		 return true;
		
	}
	
		<%/*鼠标移动*/%>
	function calc(){
		var band = document.all("band").value;
		for(var n=2006;n<=band;n++){
			var tze = "tze"+n;
			var kdke = "kdke"+n;
			var sjdke = "sjdke"+n;
			var jzdke = "jzdke"+n;
			if(n==2006 || n==2007){
				
				if(document.all(sjdke).value != "" && document.all(sjdke).value!="0.00"){
				
					
				}else{
					document.all(kdke).value = "0.00";
					
					document.all(jzdke).value = "0.00";
				}
			}else{
				
				//判断该年的当年实际抵扣应纳税所得额是否为空
				
				var kdknd = n-2;//取得2年前的投资年度
				var ztze = 0;
				var qnnd = n-1;//去年投资年度
				var zsjdke = 0;
				//获取2年前的投资额总和
				for(var m=2006;m<=kdknd;m++){
					var mtze = "tze"+m;
					ztze += Number(document.all(mtze).value);
				}
				//取得1年前的抵扣额总和
				for(var x=2006;x<=qnnd;x++){
					var xsjdke = "sjdke"+x;
					zsjdke += Number(document.all(xsjdke).value);
				}
				var kdktze = "tze"+kdknd;//取得2年前的投资年度页面的”当年新增投资额“对应输入框
				var qnjzdke = "jzdke"+qnnd;//取得去年投资年度页面的”结转以后年度抵扣应纳税所得额“对应输入框
				//判断前年投资额是否为空，去年”结转以后年度抵扣应纳税所得额“是否为空
				if((document.all(kdktze).value != "" && document.all(kdktze).value!="0.00")||
					(document.all(qnjzdke).value != "" && document.all(qnjzdke).value!="0.00")){
					//如果不为空，则该年的当年可抵扣应纳税所得额=2年前的当年新增投资额*0.7
					document.all(kdke).value = ztze * 0.7 - zsjdke;
					//数字进行四舍五入
					var num = round2(document.all(kdke).value);
					document.all(kdke).value = num;
					//将数字转换为小数点后两位
					formatCurrency(document.all(kdke),0);
				}else{
					//2年前投资年度的投资额为空
					document.all(kdke).value = "0.00";
				}
				
				//判断该年的当年实际抵扣应纳税所得额是否大于当年可抵扣应纳税所得额
				
					//如果满足条件，则结转以后年度抵扣应纳税所得额=当年可抵扣应纳税所得额-当年实际抵扣应纳税所得额
					document.all(jzdke).value = document.all(kdke).value-document.all(sjdke).value;
					//数字进行四舍五入
					var num = round2(document.all(jzdke).value);
					document.all(jzdke).value = num;
					//将数字转换为小数点后两位
					formatCurrency(document.all(jzdke),0);
			
			}
			
		}
		
		
	}
	
	<%/*效验投资信息*/%>
	function checkTzxx(){
		var band = document.all("band").value;
		for(var n=2006;n<=band;n++){
			var tze = "tze"+n;
			var kdke = "kdke"+n;
			var sjdke = "sjdke"+n;
			var jzdke = "jzdke"+n;
			if(n==2006 || n==2007){
				//判断该年的当年当年新增投资额是否为空
				if(document.all(tze).value == ""){
					document.all(tze).value = "0.00";
				}
				if(document.all(sjdke).value != "" && document.all(sjdke).value!="0.00"){
					alert(n+"投资年度的当年实际抵扣应纳税所得额应为零！");
					document.all(sjdke).focus();
					return false;
				}else{
					document.all(kdke).value = "0.00";
					document.all(sjdke).value = "0.00";
					document.all(jzdke).value = "0.00";
				}
			}else{
				//判断该年的当年当年新增投资额是否为空
				if(document.all(tze).value == ""){
					document.all(tze).value = "0.00";
				}
				//判断该年的当年实际抵扣应纳税所得额是否为空
				if(document.all(sjdke).value == ""){
					document.all(sjdke).value = "0.00";
				}
				var kdknd = n-2;//取得2年前的投资年度
				var ztze = 0;
				var qnnd = n-1;//去年投资年度
				var zsjdke = 0;
				//获取2年前的投资额总和
				for(var m=2006;m<=kdknd;m++){
					var mtze = "tze"+m;
					ztze += Number(document.all(mtze).value);
				}
				//取得1年前的抵扣额总和
				for(var x=2006;x<=qnnd;x++){
					var xsjdke = "sjdke"+x;
					zsjdke += Number(document.all(xsjdke).value);
				}
				var kdktze = "tze"+kdknd;//取得2年前的投资年度页面的”当年新增投资额“对应输入框
				var qnjzdke = "jzdke"+qnnd;//取得去年投资年度页面的”结转以后年度抵扣应纳税所得额“对应输入框
				//判断前年投资额是否为空，去年”结转以后年度抵扣应纳税所得额“是否为空
				if((document.all(kdktze).value != "" && document.all(kdktze).value!="0.00")||
					(document.all(qnjzdke).value != "" && document.all(qnjzdke).value!="0.00")){
					//如果不为空，则该年的当年可抵扣应纳税所得额=2年前的当年新增投资额*0.7
					document.all(kdke).value = ztze * 0.7 - zsjdke;
					//数字进行四舍五入
					var num = round2(document.all(kdke).value);
					document.all(kdke).value = num;
					//将数字转换为小数点后两位
					formatCurrency(document.all(kdke),0);
				}else{
					//2年前投资年度的投资额为空
					document.all(kdke).value = "0.00";
				}
				
				//判断该年的当年实际抵扣应纳税所得额是否大于当年可抵扣应纳税所得额
				if((document.all(kdke).value-document.all(sjdke).value)<0){
					alert(n+"当年实际抵扣应纳税所得额必须小于或等于当年可抵扣应纳税所得额！");
					document.all(sjdke).focus();
					return false;
				}else{
					//如果满足条件，则结转以后年度抵扣应纳税所得额=当年可抵扣应纳税所得额-当年实际抵扣应纳税所得额
					document.all(jzdke).value = document.all(kdke).value-document.all(sjdke).value;
					//数字进行四舍五入
					var num = round2(document.all(jzdke).value);
					document.all(jzdke).value = num;
					//将数字转换为小数点后两位
					formatCurrency(document.all(jzdke),0);
				}
			}
			
		}
		return true;
	}
	<%/*保存投资信息*/%>
	function tzxx(){
		var band = document.all("band").value;
		var tzestring = "";
		var kdkestring = "";
		var sjdkestring = "";
		var jzdkestring = "";
		var bjkzstring = "";
		for(var n=2006;n<=band;n++){
			var tze = "tze"+n;
			var kdke = "kdke"+n;
			var sjdke = "sjdke"+n;
			var jzdke = "jzdke"+n;
			var bjkz = "bjkz"+n;
			tzestring = tzestring + "\;"+document.all(tze).value;
			kdkestring = kdkestring+"\;"+document.all(kdke).value;
			sjdkestring = sjdkestring+"\;"+document.all(sjdke).value;
			jzdkestring = jzdkestring+"\;"+document.all(jzdke).value;
			if(document.all(bjkz).value == ""){
				bjkzstring = bjkzstring +"\;1";
			}else{
				bjkzstring = bjkzstring +"\;"+document.all(bjkz).value;
			}
		}
		document.all.tze.value=tzestring.substring(1,tzestring.length);
		document.all.kdke.value=kdkestring.substring(1,kdkestring.length);
		document.all.sjdke.value=sjdkestring.substring(1,sjdkestring.length);
		document.all.jzdke.value=jzdkestring.substring(1,jzdkestring.length);
		document.all.bjkz.value=bjkzstring.substring(1,bjkzstring.length);
	}
	<%/*接受申请*/%>
	function jssq()
	{
		if(checkZbxx() && checkYm() && checkTzxx()){
			zlqd();
			if(window.confirm("是否接受该纳税人的减免税备案申请？")){
				//用来处理下拉框保存后页面回显
				document.forms[0].gxjslydm.value = document.forms[0].gxjsly.value;
				//用来处理投资信息
				tzxx();
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
	function save()
	{
		if(checkZbxx() && checkYm()&& checkTzxx()){
			zlqd();
			if(window.confirm("是否确定保存?")){
				//用来处理下拉框保存后页面回显
			    	tzxx();
				    document.forms[0].gxjslydm.value = document.forms[0].gxjsly.value;
	      			document.forms[0].actionType.value="Jssq";
					document.forms[0].submit();
	      		
      		}
		}
	}
	
	function bg()
	{
		if(checkZbxx() && checkYm()&& checkTzxx()){
			zlqd();
			if(window.confirm("是否确定变更?")){
				//用来处理下拉框保存后页面回显
			    	tzxx();
				    document.forms[0].gxjslydm.value = document.forms[0].gxjsly.value;
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
	
  </script>
  
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onload="doInit()">
    <%@ include file="../../include/header.jsp" %> 
     
	  
      <html:form action="/webapp/smsb/qysds/jmsbagl/Basx13BAction.do" method="post">
        <html:hidden property="actionType" />
        <html:hidden property="basqwsh" />
        <html:hidden property="basqbh" />
        <html:hidden property="band" />
        <html:hidden property="jsjdm" />
        <html:hidden property="xh" />
        <html:hidden property="zl" />
        <html:hidden property="czlx" />
        <html:hidden property="clbs" />
        <html:hidden property="btzqyssd" />
        <html:hidden property="gxjslydm" />
        <html:hidden property="tze" />
        <html:hidden property="kdke" />
        <html:hidden property="sjdke" />
        <html:hidden property="jzdke" />
        <html:hidden property="bjkz" />
        <html:hidden property="tzxxjg" />
        <table width="75%" align="center" cellspacing="0" height="480">
          <tr>
            <td class="1-td1" colspan="7" height="30">
             录入创业投资企业抵扣应纳税所得额备案事项
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
                      	&nbsp;<bean:write name="basx13BForm" property="basqbh"/>
                      </td>
                      <td  class="2-td2-t-left">
                          <strong>计算机代码</strong>               
                      </td>
                      <td  class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx13BForm" property="jsjdm"/>
                      </td>                  
                      <td  class="2-td2-t-left">                       
                          <strong>纳税人名称</strong>
                      </td>
                      <td width="22%"  class="2-td2-t-center">
                      &nbsp;<bean:write name="basx13BForm" property="nsrmc"/>
                      </td>
                    </tr>
                    <tr>
                      <td  class="2-td2-left">                        
                          <strong>主管税务所</strong>    
                      </td>
                      <td  width="22%"class="2-td2-left">
                      	&nbsp;<bean:write name="basx13BForm" property="zgsws"/>
                      </td>
                      <td  class="2-td2-left">
                          <strong>经济类型</strong>               
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx13BForm" property="jjlx"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>所属行业</strong>
                      </td>
                      <td width="22%"  class="2-td2-center">
                      	&nbsp;<bean:write name="basx13BForm" property="sshy"/>
                      </td>
                    </tr>
                   <tr>
                      <td  class="2-td2-left">
                         <strong>联系人</strong>
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx13BForm" property="lxr"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>联系电话</strong>
                      </td>
                      <td width="22%"  class="2-td2-center" colspan="3">
                       &nbsp;<bean:write name="basx13BForm" property="lxdh"/>
                      </td>
                    </tr>
                  </table>
                  
                  
                  </br>
                  <table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">            
              <tr>
                <td class="2-td2-t-left" width="10%">
                  起始时间
                <font color="#FF0000">*</font>
                </td>
                <td class="2-td2-t-left" width="15%"> 
                  	<html:text property="qsrq" size="12" onclick="WdatePicker()"></html:text>
                </td>
                <td class="2-td2-t-left" width="10%">
                  截止时间
                  <font color="#FF0000">*</font>
                </td>
                <td class="2-td2-t-left" width="15%">
                	<html:text property="jzrq" size="12" onclick="WdatePicker()"></html:text>
                </td>                
            
                <td class="2-td2-t-left" width="10%">
                  减免税额
                  
                </td>
                <td class="2-td2-t-left" width="15%">
                  	<html:text property="bajmse"  size="10" />&nbsp;元
                </td>
                <td class="2-td2-t-left" width="10%">
                  减免比例
                  
                </td>
                <td class="2-td2-t-center" width="15%">
	                <html:text property="bajmbl"  size="10" />&nbsp;%
                </td>
                
              </tr>
              <tr>
                <td class="2-td2-left">
                  减免税政策
                  <br/>
                  执行情况
                  <font color="#FF0000">*</font>
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
                  			<input type="text" size="75"  name="zlmc" value="" />
                  		</td>
                  		<td class="2-td2-t-center">
                  			<a style="cursor:hand" onClick="addzl()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('tianjia','','<%=static_contextpath%>images//tianjia2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//tianjia1.jpg" name="tianjia" width="79" height="22" border="0" id="tianjia">
	                        </a>
                  		</td>
                  	</tr>
                  </table>	 
                  </br>
                  <td>
              </tr>
            </table>
                  
            	  </br>
            		<table class="table-99" cellspacing="0">
					
                    <tr>
                      <td rowspan="5" width="30%" class="2-td2-t-left">
                        <div align="center">
                          被投资企业
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td width="30%"  class="2-td2-t-left">
                        <div align="left">&nbsp;&nbsp;
                          	计算机代码
                        </div>
                      </td>
                      <td class="2-td2-t-center">
                        <div align="left">
                        	<html:text property="btzqyjsjdm" size="30" tabindex="-1" maxlength="8" readonly="readonly" styleClass="txtInput"></html:text>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">&nbsp;&nbsp;
                         	税务登记证号&nbsp;<font color="#FF0000">*</font>&nbsp;
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div align="left">
                        	<html:text property="btzqyswdjzh" size="30" tabindex="-1" readonly="readonly" styleClass="txtInput"></html:text>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">&nbsp;&nbsp;
                          	纳税人名称&nbsp;<font color="#FF0000">*</font>&nbsp;
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div align="left">
                        	<html:text property="btzqymc" size="30" tabindex="-1" readonly="readonly" styleClass="txtInput"></html:text>
                        </div>
                      </td>
                    </tr>
                  	 <tr>
                      <td width="10%" class="2-td2-left">
                        <div align="left">&nbsp;&nbsp;
                          	中小高新技术企业领域&nbsp;<font color="#FF0000">*</font>&nbsp;
                        </div>
                      </td>
                      <td class="2-td2-center">
                      	<div align="left">
	                        <html:select name="basx13BForm" styleId="gxjslySelect" property="gxjsly" />
                      	</div>
                      </td>
                    </tr>   
                  </table>
                   <br>
                  <table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
                  	<tr>
                  		<td class="2-td2-t-center" colspan="5">
                  			单位：元
                  		</td>
                  	</tr>
                     <tr>
                      <td class="2-td2-left" width="11%">
                          投资年度
                      </td>
                      <td class="2-td2-left" width="11%">
                          当年新增投资额
                      </td>
                      <td class="2-td2-left" width="11%">
                          当年可抵扣应纳税所得额
                      </td>
                      <td class="2-td2-left" width="11%">
                          当年实际抵扣应纳税所得额
                      </td>
                      <td class="2-td2-center" width="11%">
                          结转以后年度抵扣应纳税所得额
                      </td>                                                                                                        
                    <% int band = Integer.parseInt(basx.getBand());
					if(band<2009){
						//band = 2009;
					}
					for(int i = 2006;i<=band;i++){
						String tze = "<input type=\"text\" name=\"tze"+i+"\" size=\"15\" tabindex=\"-1\" value=\"\" onblur=\"calc()\"  onchange=\"change('1',"+i+")\">";
						String kdke =  "<input type=\"text\" name=\"kdke"+i+"\" size=\"15\" tabindex=\"-1\" style=\"background-color:#efefef\" readonly=\"readonly\" value=\"\">";
						String sjdke = "<input type=\"text\" name=\"sjdke"+i+"\" size=\"15\" tabindex=\"-1\" value=\"\" onblur=\"calc()\"  onchange=\"change('2',"+i+")\">";
						String jzdke = "<input type=\"text\" name=\"jzdke"+i+"\" size=\"15\" tabindex=\"-1\" style=\"background-color:#efefef\" readonly=\"readonly\" value=\"\">";
						String bjkz = "<input type=\"hidden\" value=\"\" name=\"bjkz"+i+"\">";
					%>
					<tr>
                      <td class="2-td2-left" >
                        <div align="center">
                          <%=i %>
                        </div>
                      </td>
                       <td class="2-td2-left">
                        <div align="center">
                          <%=tze %>
                        </div>
                      </td>
                      <td  class="2-td2-left">
                        <div id="mx_zsfsdm_epx_visible_1" align="center">
                          <%=kdke %>
                        </div>
                      </td>
                       <td class="2-td2-left">
                        <div align="center">
                          <%=sjdke %>
                        </div>
                      </td>
                      <td  class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="center">
                          <%=jzdke %>
                          <%=bjkz %>
                        </div>
                      </td>                      
                    </tr>
                    <% }%>                       
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
                        	<html:text property="mr_lrrq" readonly="true" style="background-color:#efefef"></html:text>
                        </div>
                      </td>
                      <td width="10%" class="2-td2-t-left">
                        <div align="right">
                          	备案年度
                        </div>
                      </td>
                      <td width="30%" class="2-td2-t-left">
                        <div align="left">
                        	<html:text property="mr_band" readonly="true" style="background-color:#efefef"></html:text>
                        </div>
                      </td>
                      <td width="10%" class="2-td2-t-left">
                        <div align="right">
                          录入人
                        </div>
                      </td>
                      <td width="30%" class="2-td2-t-center">
                        <div align="left">
                        	<html:text property="mr_lrr" readonly="true" style="background-color:#efefef"></html:text>
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