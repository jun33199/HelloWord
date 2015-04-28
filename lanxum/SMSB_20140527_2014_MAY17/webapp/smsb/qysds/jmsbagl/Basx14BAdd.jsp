<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<%@ page import="com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx14b.web.Basx14BForm" %>
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
  		Basx14BForm basx = (Basx14BForm)request.getAttribute("basx14BForm");
  		int band = Integer.parseInt(basx.getBand());
  		int i = 2008;
  		if(basx.getGznd() != null && !basx.getGznd().equals("")){
  			i = Integer.parseInt(basx.getGznd());
  		}
	%>   
	<%/*初始化*/%> 
	function doInit(){
	    //初始化资料
		var zl = document.forms[0].zl.value.split(";");
		var zlState = document.forms[0].zlState.value.split(",");
		for(row_index=0;row_index<zl.length;row_index++){
			var new_row=Table1.insertRow(Table1.rows.length);
			zlnr = zl[row_index].substring(0,zl[row_index].indexOf("|"));
			zlsfkysc = zl[row_index].substring(zl[row_index].length-1,zl[row_index].length);
		    addzlmc(row_index,zlnr,new_row);
		     if(zlsfkysc == "1"){
		    	
		     	addzlxz(row_index,row_index,new_row,"1",zlState[row_index]);
		    	addzlan("1",row_index,new_row);
		    }else{
		     	addzlxz(row_index,row_index,new_row,"2","1",zlState[row_index]);
		    	addzlan("2",row_index,new_row);
		    }
		   
		}
		//判断是否有查询结果
		if(document.all("tzxxjg").value == "0"){
			//购置年度不可编辑
			document.getElementById("gznd").readOnly=true;
			document.getElementById("gznd").className='txtInput';
			//购置设备名称不可编辑
			document.getElementById("zysbmc").readOnly=true;
			document.getElementById("zysbmc").className='txtInput';
			//设置专用设备种类下拉框不可编辑
			document.all("zysblxdm").disabled="disabled";
			//设置投资年度的信息如果已经已完成备案标识如果为已完成，则不可编辑
			var band = document.all("band").value;
			var gznd = document.all("gznd").value;
			var tze_v = document.forms[0].tze.value.split(";");
			var kdke_v = document.forms[0].kdke.value.split(";");
			var sjdke_v = document.forms[0].sjdke.value.split(";");
			var jzdke_v = document.forms[0].jzdke.value.split(";");
			var bjkz_v = document.forms[0].bjkz.value.split(";");
			var num = 0;
			for(var n=gznd;n<=band;n++){
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
				document.all(bjkz).value = bjkz_v[num];
				if(bjkz_v[num]=="0"){
					document.all(sjdke).readOnly=true;
				}else{
					document.all(sjdke).readOnly=false;
				}
				num++;
			}
		}else{
			//购置年度可编辑
			document.getElementById("gznd").readOnly=false;
			document.getElementById("gznd").className='text-white';
			//购置设备名称可编辑
			document.getElementById("zysbmc").readOnly=false;
			document.getElementById("zysbmc").className='text-white';
			//设置专用设备种类下拉框可编辑
			document.all("zysblxdm").disabled="";
		}
		//初始化按钮，当为修改状态时显示保存
		var czlx = document.forms[0].czlx.value;
		if(czlx == "2"){
			document.all.jssq.style.display = "none";
		}else{
			document.all.bc.style.display = "none";
		}
		var searchAction=document.all("searchAction").value;
		if(searchAction==1)
		window.scroll(0,document.body.scrollHeight);
	}
	<%/*添加资料*/%>
	function addzl(){	
		var zlmc=document.getElementById("zlmc").value;
   	    var zlmc=zlmc.replace(/(^\s*)|(\s*$)/g, "");  	    			  
		if(zlmc != ""){
			row_index++;  
	    	var new_row=Table1.insertRow(Table1.rows.length); 
	    	addzlmc(row_index,zlmc,new_row);
	    	addzlxz(row_index,row_index,new_row,"2","1");
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
	    var new_col=new_row.insertCell(2);
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
	function addzlxz(xh,row_index,new_row,lx,state){
	    var new_col=new_row.insertCell(1);
	    if(row_index == 0){
	    	new_col.className="2-td2-t-left-textleft";
	    }else{
	    	new_col.className="2-td2-left-textleft";
	    }
	    var dxk 
	    if(lx == "1"){	
	    	if(state == '0'){
	    	dxk = "<input type=\"radio\" id=have_zl_radio_"+xh+"  name=zlradio"+xh+" value=\"0\" checked>有</input>&nbsp\;<input type=\"radio\"  id=lack_zl_radio_"+xh+" name=zlradio"+xh+"  value=\"1\" >无</input>";
	    	}else{
	    	dxk = "<input type=\"radio\" id=have_zl_radio_"+xh+"  name=zlradio"+xh+" value=\"0\">有</input>&nbsp\;<input type=\"radio\"  id=lack_zl_radio_"+xh+" name=zlradio"+xh+"  value=\"1\" checked>无</input>";
	    	}
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
		//	alert("减免税额和减免比例必填一项！");
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
		var zysbmc = document.all("zysbmc").value;
		if(zysbmc == "" ){
			alert("购置专用设备名称不能为空!");
			document.forms[0].zysbmc.focus();
			return false;
		}else{
			if(zysbmc.length>200){
				alert("购置专用设备名称输入过长！");
				document.forms[0].zysbmc.focus();
				return false;
			}
		}
		var zysblxdm = document.all("zysblxdm").value;
		 if(zysblxdm == "" ){
			alert("专用设备种类不能为空!");
			document.forms[0].zysblxdm.focus();
			return false;
		 }
		return true;
		
	}
	<%/*效验购置年度是否合法*/%>
	function checkGznd(){
		var band = document.all("band").value;
		var gznd = document.all("gznd").value;
		if(gznd == "" ){
			alert("购置年度不能为空!");
			document.forms[0].gznd.focus();
			return false;
		}else{
			//效验购置年度
			if(!isFullDate(gznd,1,1,true)){
				document.forms[0].gznd.focus();
				return false;
			}else{
				if(gznd<2008 || gznd>band || (band-gznd)>5){
					alert("购置年度不合法!");
					document.forms[0].gznd.focus();
					return false;
				}
			}
		}
		return true;
	}
	
	<%/*效验投资信息*/%>
	function calc(){
		var band = document.all("band").value;
		var gznd = document.all("gznd").value;
		if(gznd.length>0 && gznd>=2008){
		for(var n=gznd;n<=band;n++){
			var tze = "tze"+n;
			var kdke = "kdke"+n;
			var sjdke = "sjdke"+n;
			var jzdke = "jzdke"+n;
			if(n==gznd){
				//判断该年的当年新增投资额是否为空
				if(document.all(tze).value == "" || document.all(tze).value == "0.00"){					
					document.all(tze).value = "0.00"
					
				}else{
					//当年可抵免的应纳税额 = 当年购置设备投资额*0.1
					document.all(kdke).value = document.all(tze).value * 0.1;
					//数字进行四舍五入
					var num = round2(document.all(kdke).value);
					document.all(kdke).value = num;
					//将数字转换为小数点后两位
					formatCurrency(document.all(kdke),0);
				}
			}else{
				//当年购置设备投资额默认为0.00
				document.all(tze).value = "0.00";
				//该年的当年可抵免的应纳税额=去年的结转以后年度抵免的应纳税额
				var qnjzdke = "jzdke"+(n-1);
				document.all(kdke).value = document.all(qnjzdke).value;
			}
			//判断该年的当年实际抵扣应纳税所得额是否为空
			if(document.all(sjdke).value == ""){
				document.all(sjdke).value = "0.00";
			}
			//判断该年的当年实际抵扣应纳税所得额是否大于当年可抵扣应纳税所得额
			if((document.all(kdke).value-document.all(sjdke).value)<0){
				alert(n+"当年实际抵扣应纳税所得额必须小于或等于当年可抵扣应纳税所得额！");
				document.all(sjdke).value="";
				document.all(sjdke).focus();
				
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
	
		}else{
			alert("购置年度不能为空，且需大于等于2008！");
			document.all("gznd").focus();
			
		}
	}
	
	<%/*效验投资信息*/%>
	function checkTzxx(){
		var band = document.all("band").value;
		var gznd = document.all("gznd").value;
		for(var n=gznd;n<=band;n++){
			var tze = "tze"+n;
			var kdke = "kdke"+n;
			var sjdke = "sjdke"+n;
			var jzdke = "jzdke"+n;
			if(n==gznd){
				//判断该年的当年新增投资额是否为空
				if(document.all(tze).value == "" || document.all(tze).value == "0.00"){
					alert(n+"当年购置设备投资额必须填入投资额！");
					document.all(tze).focus();
					return false;
				}else{
					//当年可抵免的应纳税额 = 当年购置设备投资额*0.1
					document.all(kdke).value = document.all(tze).value * 0.1;
					//数字进行四舍五入
					var num = round2(document.all(kdke).value);
					document.all(kdke).value = num;
					//将数字转换为小数点后两位
					formatCurrency(document.all(kdke),0);
				}
			}else{
				//当年购置设备投资额默认为0.00
				document.all(tze).value = "0.00";
				//该年的当年可抵免的应纳税额=去年的结转以后年度抵免的应纳税额
				var qnjzdke = "jzdke"+(n-1);
				document.all(kdke).value = document.all(qnjzdke).value;
			}
			//判断该年的当年实际抵扣应纳税所得额是否为空
			if(document.all(sjdke).value == ""){
				document.all(sjdke).value = "0.00";
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
		return true;
		
	}
	<%/*保存投资信息*/%>
	function tzxx(){
		var band = document.all("band").value;
		var gznd = document.all("gznd").value;
		var tzestring = "";
		var kdkestring = "";
		var sjdkestring = "";
		var jzdkestring = "";
		var bjkzstring = "";
		for(var n=gznd;n<=band;n++){
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
	
	<%/*根据备案申请编号查询*/%>
	function chaxun(){
		zlqd();
		getRadioState();
		doSubmitForm('BasqbhSeach');
	}
	
	<%/*根据购置年度刷新页面抵免年度*/%>
	function doGznd(){
		//效验购置年度是否合法		
		if(checkGznd()){
			zlqd();
			getRadioState();
			doSubmitForm('Gznd');
		}
		
	}
	
	
		
	function getRadioState(){
		var tag = document.getElementsByTagName('input'); 
		var zlState='';
		for(var i=0;i <tag.length;i++){ 
		  var tagid=tag[i].id;
		   if(tagid.indexOf("lack_zl_radio_")>=0){
		   		var zlRadio=document.getElementById(tagid);
		   		if(zlRadio.checked==true){
		   			zlState+="1,"
		   		}
		   }
		    if(tagid.indexOf("have_zl_radio_")>=0){
		   		var zlRadio=document.getElementById(tagid);
		   		if(zlRadio.checked==true){
		   			zlState+="0,"
		   		}
		   }
		}
		document.all.zlState.value=zlState.substring(0,zlState.length-1);
	}
	
	<%/*接受申请*/%>
	function jssq()
	{
		if(checkZbxx() && checkGznd && checkYm()&& checkZlRadio() && checkTzxx()){
			zlqd();
			if(window.confirm("是否接受该纳税人的减免税备案申请？")){
				//用来处理投资信息
				tzxx();
				document.forms[0].zysblx.value = document.forms[0].zysblxdm.value;
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
	
	<%/*返回*/%>
	function back()
	{
		doSubmitForm('Back');
	}
	
  </script>
  
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onload="doInit()">
    <%@ include file="../../include/header.jsp" %> 
     
	  
      <html:form action="/webapp/smsb/qysds/jmsbagl/Basx14BAction.do" method="post">
        <html:hidden property="actionType" />
        <html:hidden property="basqwsh" />
        <html:hidden property="basqbh" />
        <html:hidden property="band" />
        <html:hidden property="jsjdm" />
        <html:hidden property="xh" />
        <html:hidden property="zl" />
        <html:hidden property="czlx" />
        <html:hidden property="clbs" />
        <html:hidden property="tze" />
        <html:hidden property="kdke" />
        <html:hidden property="sjdke" />
        <html:hidden property="jzdke" />
        <html:hidden property="bjkz" />
        <html:hidden property="tzxxjg" />
        <html:hidden property="zysblx" />
         <html:hidden property="searchAction" />
        <html:hidden property="zlState" />
        
        <table width="75%" align="center" cellspacing="0" height="480">
          <tr>
            <td class="1-td1" colspan="7" height="30">
              录入企业购置用于环境保护、节能节水、安全生产等专用设备的投资抵免税额事项  
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
                      	&nbsp;<bean:write name="basx14BForm" property="basqbh"/>
                      </td>
                      <td  class="2-td2-t-left">
                          <strong>计算机代码</strong>               
                      </td>
                      <td  class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx14BForm" property="jsjdm"/>
                      </td>                  
                      <td  class="2-td2-t-left">                       
                          <strong>纳税人名称</strong>
                      </td>
                      <td width="22%"  class="2-td2-t-center">
                      &nbsp;<bean:write name="basx14BForm" property="nsrmc"/>
                      </td>
                    </tr>
                    <tr>
                      <td  class="2-td2-left">                        
                          <strong>主管税务所</strong>    
                      </td>
                      <td  width="22%"class="2-td2-left">
                      	&nbsp;<bean:write name="basx14BForm" property="zgsws"/>
                      </td>
                      <td  class="2-td2-left">
                          <strong>经济类型</strong>               
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx14BForm" property="jjlx"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>所属行业</strong>
                      </td>
                      <td width="22%"  class="2-td2-center">
                      	&nbsp;<bean:write name="basx14BForm" property="sshy"/>
                      </td>
                    </tr>
                   <tr>
                      <td  class="2-td2-left">
                         <strong>联系人</strong>
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx14BForm" property="lxr"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>联系电话</strong>
                      </td>
                      <td width="22%"  class="2-td2-center" colspan="3">
                       &nbsp;<bean:write name="basx14BForm" property="lxdh"/>
                      </td>
                    </tr>
                  </table>
                  
                  
                  </br>
                  <table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">            
              <tr>
                <td class="2-td2-t-left" width="10%">
                  起始时间<font color="#FF0000">*</font>
                </td>
                <td class="2-td2-t-left" width="15%"> 
                  	<html:text property="qsrq" size="12" onclick="WdatePicker()"></html:text>
                </td>
                <td class="2-td2-t-left" width="10%">
                  截止时间<font color="#FF0000">*</font>
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
                  执行情况<font color="#FF0000">*</font>
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
            	  <table class="table-99" cellspacing="0">
                  	 <tr>
                      <td width="50%" class="2-td2-t-left" >
                        <div align="left">&nbsp;&nbsp;
                          往年备案申请编号
                        </div>
                      </td>
                      <td width="50%" class="2-td2-t-center" >
                        <div align="left">
                          <html:text property="wnbasqbh" size="30" tabindex="-1" ></html:text>&nbsp;&nbsp;&nbsp; 
                          <a style="cursor:hand" onClick="chaxun()" onMouseOut="MM_swapImgRestore()"
	                        onMouseOver="MM_swapImage('cx','','<%=static_contextpath%>images//chaxun2.jpg',1)">
	                        <img src="<%=static_contextpath%>images//chaxun1.jpg" name="cx" width="79" height="22" border="0" id="cx">
	                      </a>
                        </div>
                      </td>
                    </tr>    
                  </table>
                  </br>
            		<table class="table-99" cellspacing="0">
                    <tr>
                      <td  width="50%" class="2-td2-t-left" colspan="2">
                       <div align="left">&nbsp;&nbsp;
                          	购置年度&nbsp;<font color="#FF0000">*</font>&nbsp;
                        </div>
                      </td>
                      <td class="2-td2-t-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                          <html:text property="gznd" maxlength="4" size="17" tabindex="-1" onchange="doGznd()"></html:text>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left" colspan="2">
                        <div align="left">&nbsp;&nbsp;
                          	购置专用设备名称&nbsp;<font color="#FF0000">*</font>&nbsp;
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                          <html:text property="zysbmc" maxlength="200" size="17" tabindex="-1"></html:text>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left" colspan="2">
                        <div align="left">&nbsp;&nbsp;
                          	专用设备种类&nbsp;<font color="#FF0000">*</font>&nbsp;
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                          <html:select name="basx14BForm" property="zysblxdm">
		  						<logic:iterate id="data" indexId="index" length="length" name="basx14BForm"
		  							property="zysblxList" type="com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.DmVo">
		    					<logic:equal name="data" property="level" value="1">
		      						<optgroup label="<bean:write name="data" property="mc"/>">
		      						</optgroup>
		   		 				</logic:equal>
		    					<logic:equal name="data" property="level" value="2">
		      						<option value="<bean:write name="data" property="dm"/>">&nbsp;&nbsp;
		        						<bean:write name="data" property="mc" />
		     						 </option>
		    					</logic:equal>
		  						</logic:iterate>
							</html:select>
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
                      <td class="2-td2-left" width="7%">
                         抵免年度
                      </td>
                      <td class="2-td2-left" width="10%">
                          当年购置设备投资额
                      </td>
                      <td class="2-td2-left" width="11%">
                          当年可抵免的应纳税额
                      </td>
                      <td class="2-td2-left" width="11%">
                          当年实际抵免的应纳税额
                      </td>
                      <td class="2-td2-center" width="16%">
                          结转以后年度抵免的应纳税额
                      </td>                                                                                                        
                    <% 
                    boolean bool = true;
					for(;i<=band;i++){
						String tze="";
						String kdke="";
						String sjdke="";
						String jzdke="";
						String bjkz="";
						 String basqbhSearch=(String)request.getAttribute("BASX14B_BASQBHSEARCH");
						 
		                    if(basqbhSearch!=null && basqbhSearch.length()>0 && basqbhSearch.equals("0")){	
		                    	
								tze= "<input type=\"text\" name=\"tze"+i+"\" size=\"15\" tabindex=\"-1\" value=\"\"  style=\"background-color:#efefef\"  readonly=\"readonly\">";
								kdke = "<input type=\"text\" name=\"kdke"+i+"\" size=\"15\" tabindex=\"-1\"  style=\"background-color:#efefef\"  readonly=\"readonly\" value=\"\">";
								if(i==band)
								sjdke = "<input type=\"text\" name=\"sjdke"+i+"\" size=\"15\" tabindex=\"-1\" value=\"\" onblur=\"calc()\" onchange=\"change('2',"+i+")\">";
								else
								sjdke = "<input type=\"text\" name=\"sjdke"+i+"\" size=\"15\" tabindex=\"-1\" style=\"background-color:#efefef\"  readonly=\"readonly\" value=\"\">";
								jzdke = "<input type=\"text\" name=\"jzdke"+i+"\" size=\"15\" tabindex=\"-1\" style=\"background-color:#efefef\"  readonly=\"readonly\" value=\"\">";
							}else{		
							   	
								if(bool){
									tze= "<input type=\"text\" name=\"tze"+i+"\" size=\"15\" tabindex=\"-1\" value=\"\" onblur=\"calc()\" onchange=\"change('1',"+i+")\">";
								}else{
									tze= "<input type=\"text\" name=\"tze"+i+"\" size=\"15\" tabindex=\"-1\" value=\"\"  style=\"background-color:#efefef\"  readonly=\"readonly\">";
								}
								bool = false;
								kdke = "<input type=\"text\" name=\"kdke"+i+"\" size=\"15\" tabindex=\"-1\"  style=\"background-color:#efefef\"  readonly=\"readonly\" value=\"\">";
								sjdke = "<input type=\"text\" name=\"sjdke"+i+"\" size=\"15\" tabindex=\"-1\" value=\"\" onblur=\"calc()\" onchange=\"change('2',"+i+")\">";
								jzdke = "<input type=\"text\" name=\"jzdke"+i+"\" size=\"15\" tabindex=\"-1\"  style=\"background-color:#efefef\"  readonly=\"readonly\" value=\"\">";
								
		                    }
		                    bjkz = "<input type=\"hidden\" value=\"\" name=\"bjkz"+i+"\">";
						
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
	                        <div id="bc" style="display: inline;">
	      				  	<a style="cursor:hand" onClick="jssq()" onMouseOut="MM_swapImgRestore()"
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