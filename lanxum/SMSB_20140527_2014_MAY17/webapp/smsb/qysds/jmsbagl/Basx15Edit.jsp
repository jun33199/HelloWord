<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
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
		var sdhjs = document.forms[0].sdhjs.value;
		if(sdhjs == "0"){
			document.getElementsByName("bm")[0].checked = "true";
			document.getElementById("TABLE_LIST1").style.display = "block";
          	document.getElementById("TABLE_LIST2").style.display = "none";
		}
		if(sdhjs == "1"){
			document.getElementsByName("bm")[1].checked = "true";
			document.getElementById("TABLE_LIST1").style.display = "none";
         	document.getElementById("TABLE_LIST2").style.display = "block";
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
	function change(name){
		var va = document.all(name).value;
		if(va !== ""){
			if(!(checkvalue(document.all(name),0)&&formatCurrency(document.all(name),0))){
				return false;
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
		if (document.getElementsByName("bm")[0].checked) {
			var gdzcmc_sd = document.all("gdzcmc_sd").value;
			if(gdzcmc_sd == "" ){
				alert("固定资产名称不能为空!");
				document.forms[0].gdzcmc_sd.focus();
				return false;
			}
			var gdzcyz_sd = document.all("gdzcyz_sd").value;
			if(gdzcyz_sd == ""){
				alert("固定资产原值不能为空！");
				document.forms[0].gdzcyz_sd.focus();
				return false;
			}else{
				if(!(checkvalue(document.all("gdzcyz_sd"),0)&&formatCurrency(document.all("gdzcyz_sd"),0))){
					return false;
				}
			}
			var gdzcjsjc_sd = document.all("gdzcjsjc_sd").value;
			if(gdzcjsjc_sd == ""){
				alert("固定资产计税基础不能为空！");
				document.forms[0].gdzcjsjc_sd.focus();
				return false;
			}else{
				if(!(checkvalue(document.all("gdzcjsjc_sd"),0)&&formatCurrency(document.all("gdzcjsjc_sd"),0))){
					return false;
				}
			}
			var sfgdzdnx_sd = document.forms[0].sfgdzdnx_sd.value;
			if(sfgdzdnx_sd == ""){
				alert("税法规定的最低年限不能为空！");
				document.forms[0].sfgdzdnx_sd.focus();
				return false;
			}
			var jszjzdnx_sd = document.forms[0].jszjzdnx_sd.value;
			if(jszjzdnx_sd == ""){
				alert("加速折旧的最低年限不能为空！");
				document.forms[0].jszjzdnx_sd.focus();
				return false;
			}
			var zjqsnd_sd = document.forms[0].zjqsnd_sd.value;
			if(zjqsnd_sd == ""){
				alert("计提折旧年度起不能为空！");
				document.forms[0].zjqsnd_sd.focus();
				return false;
			}else{
				//判断是否全为数字
				if(!isFullDate(zjqsnd_sd,1,1,true)){
					document.forms[0].zjqsnd_sd.focus();
					return false;
				}
			}
			var zjzznd_sd = document.forms[0].zjzznd_sd.value;
			if(zjzznd_sd == ""){
				alert("计提折旧年度止不能为空！");
				document.forms[0].zjzznd_sd.focus();
				return false;
			}else{
				//判断是否全为数字
				if(!isFullDate(zjzznd_sd,1,1,true)){
					document.forms[0].zjzznd_sd.focus();
					return false;
				}
			}
			if(zjqsnd_sd>zjzznd_sd){
				alert("计提折旧年度起不能晚于计提折旧年度止！");
				document.forms[0].zjzznd_sd.focus();
				return false;
			}
			var kkczje_sd = document.all("kkczje_sd").value;
			if(kkczje_sd == ""){
				alert("每年可扣除的折旧额不能为空！");
				document.forms[0].kkczje_sd.focus();
				return false;
			}else{
				if(!(checkvalue(document.all("kkczje_sd"),0)&&formatCurrency(document.all("kkczje_sd"),0))){
					return false;
				}
			}
			var yjtzjnx_sd = document.forms[0].yjtzjnx_sd.value;
			if(yjtzjnx_sd == ""){
				alert("已计提折旧的年限不能为空！");
				document.forms[0].yjtzjnx_sd.focus();
				return false;
			}
			var yjtzje_sd = document.all("yjtzje_sd").value;
			if(yjtzje_sd == ""){
				alert("已计提的折旧额不能为空！");
				document.forms[0].yjtzje_sd.focus();
				return false;
			}else{
				if(!(checkvalue(document.all("yjtzje_sd"),0)&&formatCurrency(document.all("yjtzje_sd"),0))){
					return false;
				}
			}
			 
		}
		
		if (document.getElementsByName("bm")[1].checked) {
			var gdzcmc_js = document.all("gdzcmc_js").value;
			if(gdzcmc_js == "" ){
				alert("固定资产名称不能为空!");
				document.forms[0].gdzcmc_js.focus();
				return false;
			}
			var gdzcyz_js = document.all("gdzcyz_js").value;
			if(gdzcyz_js == ""){
				alert("固定资产原值不能为空！");
				document.forms[0].gdzcyz_js.focus();
				return false;
			}else{
				if(!(checkvalue(document.all("gdzcyz_js"),0)&&formatCurrency(document.all("gdzcyz_js"),0))){
					return false;
				}
			}
			var gdzcjsjc_js = document.all("gdzcjsjc_js").value;
			if(gdzcjsjc_js == ""){
				alert("固定资产计税基础不能为空！");
				document.forms[0].gdzcjsjc_js.focus();
				return false;
			}else{
				if(!(checkvalue(document.all("gdzcjsjc_js"),0)&&formatCurrency(document.all("gdzcjsjc_js"),0))){
					return false;
				}
			}
			var jszjff_js = document.all("jszjff_js").value;
			if(jszjff_js == "" ){
				alert("请选择折旧方法!");
				document.forms[0].jszjff_js.focus();
				return false;
			}
			var nzje_js = document.all("nzje_js").value;
			if(nzje_js == ""){
				alert("年折旧额不能为空！");
				document.forms[0].nzje_js.focus();
				return false;
			}else{
				if(!(checkvalue(document.all("nzje_js"),0)&&formatCurrency(document.all("nzje_js"),0))){
					return false;
				}
			}
		}
		 
		 return true;
		
	}
	
	<%/*控制免征和减半显示*/%>
	function xzb() {
        if (document.getElementsByName("bm")[0].checked) {
          document.getElementById("TABLE_LIST1").style.display = "block";
          document.getElementById("TABLE_LIST2").style.display = "none";
        }
        if (document.getElementsByName("bm")[1].checked) {
          document.getElementById("TABLE_LIST1").style.display = "none";
          document.getElementById("TABLE_LIST2").style.display = "block";
        }
    }
	
	<%/*接受申请*/%>
	function jssq()
	{
		if(checkZbxx() && checkYm()&& checkZlRadio()){
			zlqd();
			if(window.confirm("是否接受该纳税人的减免税备案申请？")){
				if (document.getElementsByName("bm")[0].checked) {
					document.forms[0].sdhjs.value = '0';
				}
				if (document.getElementsByName("bm")[1].checked) {
					document.forms[0].sdhjs.value = '1';
				}
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

		<%/*保存*/%>
		function save()
		{
			if(checkZbxx() && checkYm()&& checkZlRadio()){
				zlqd();
				if (document.getElementsByName("bm")[0].checked) {
					document.forms[0].sdhjs.value = '0';
				}
				if (document.getElementsByName("bm")[1].checked) {
					document.forms[0].sdhjs.value = '1';
				}
		      		if(window.confirm("是否确定保存?")){
		      			document.forms[0].actionType.value="Jssq";
						document.forms[0].submit();
		      		}
	      		}
			
		}

		<%/*变更*/%>
		function bg()
		{
			if(checkZbxx() && checkYm()&& checkZlRadio()){
				zlqd();
				if (document.getElementsByName("bm")[0].checked) {
					document.forms[0].sdhjs.value = '0';
				}
				if (document.getElementsByName("bm")[1].checked) {
					document.forms[0].sdhjs.value = '1';
				}
		      		if(window.confirm("是否确定变更?")){
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
     
	  
      <html:form action="/webapp/smsb/qysds/jmsbagl/Basx15Action.do" method="post">
        <html:hidden property="actionType" />
        <html:hidden property="basqwsh" />
        <html:hidden property="basqbh" />
        <html:hidden property="band" />
        <html:hidden property="jsjdm" />
        <html:hidden property="xh" />
        <html:hidden property="zl" />
        <html:hidden property="czlx" />
        <html:hidden property="clbs" />
        <html:hidden property="sdhjs" />
        <table width="75%" align="center" cellspacing="0" height="480">
          <tr>
            <td class="1-td1" colspan="7" height="30">
              录入固定资产缩短折旧年限或加速折旧备案事项
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
                      	&nbsp;<bean:write name="basx15Form" property="basqbh"/>
                      </td>
                      <td  class="2-td2-t-left">
                          <strong>计算机代码</strong>               
                      </td>
                      <td  class="2-td2-t-left">
                      	&nbsp;<bean:write name="basx15Form" property="jsjdm"/>
                      </td>                  
                      <td  class="2-td2-t-left">                       
                          <strong>纳税人名称</strong>
                      </td>
                      <td width="22%"  class="2-td2-t-center">
                      &nbsp;<bean:write name="basx15Form" property="nsrmc"/>
                      </td>
                    </tr>
                    <tr>
                      <td  class="2-td2-left">                        
                          <strong>主管税务所</strong>    
                      </td>
                      <td  width="22%"class="2-td2-left">
                      	&nbsp;<bean:write name="basx15Form" property="zgsws"/>
                      </td>
                      <td  class="2-td2-left">
                          <strong>经济类型</strong>               
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx15Form" property="jjlx"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>所属行业</strong>
                      </td>
                      <td width="22%"  class="2-td2-center">
                      	&nbsp;<bean:write name="basx15Form" property="sshy"/>
                      </td>
                    </tr>
                   <tr>
                      <td  class="2-td2-left">
                         <strong>联系人</strong>
                      </td>
                      <td  class="2-td2-left">
                      	&nbsp;<bean:write name="basx15Form" property="lxr"/>
                      </td>                  
                      <td  class="2-td2-left">                       
                          <strong>联系电话</strong>
                      </td>
                      <td width="22%"  class="2-td2-center" colspan="3">
                       &nbsp;<bean:write name="basx15Form" property="lxdh"/>
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
                  执行情况<font color="#FF0000">
                            *
                          </font>
                </td>
                <td class="2-td2-center" colspan="7">
                	<div align="left">
                		&nbsp;<html:textarea property="jmszczxqk" rows="5" cols="90"></html:textarea>
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
                  			<input type="text" size="80"  name="zlmc" value="" />
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
            	  <table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
                    
					 <tr>
                <td class="2-td2-t-center" colspan="6">
                  缩短折旧年限：
                  <input type="radio" name="bm" value="0" checked="true" onClick="xzb()">
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;加速折旧（采取双倍余额递减法或年数总额法）： 
                  <input type="radio" name="bm" value="1" onClick="xzb()">
                </td>
              </tr>
                  </table>
                  </br>
            		<table class="table-99" cellspacing="0" id="TABLE_LIST1"
              style="display: block">
					<tr>
                      <td width="70%" class="2-td2-t-left">
                        <div align="left">
                          固定资产名称 <font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td  class="2-td2-t-center">
                        <div align="left">
                        	<html:text property="gdzcmc_sd" maxlength="11" size="15" tabindex="-1"></html:text>
                        </div>
                      </td>
                    </tr>
                    
                    
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          固定资产原值<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td  class="2-td2-center">
                        <div align="left">
                        	<html:text property="gdzcyz_sd" maxlength="11" size="15" tabindex="-1" onchange="change('gdzcyz_sd')"></html:text>元
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          固定资产计税基础<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td  class="2-td2-center">
                        <div align="left">
                        	<html:text property="gdzcjsjc_sd" maxlength="11" size="15" tabindex="-1" onchange="change('gdzcjsjc_sd')"></html:text>元
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          税法规定的最低年限<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td  class="2-td2-center">
                        <div align="left">
                          <html:text property="sfgdzdnx_sd" maxlength="4" size="15" tabindex="-1" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>年
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          加速折旧的最低年限<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td  class="2-td2-center">
                        <div align="left">
                        	<html:text property="jszjzdnx_sd" maxlength="4" size="15" tabindex="-1" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>年
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          计提折旧年度起 <font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div align="left">
                        	<html:text property="zjqsnd_sd" maxlength="4" size="15" tabindex="-1"></html:text>年
                        </div>
                      </td>
                    </tr>
                      <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          计提折旧年度止<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td class="2-td2-center">
                        <div align="left">
                        	<html:text property="zjzznd_sd" maxlength="4" size="15" tabindex="-1"></html:text>年
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td width="20%" class="2-td2-left">
                        <div align="left">
                          每年可扣除的折旧额<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td  class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                        	<html:text property="kkczje_sd" maxlength="11" size="15" tabindex="-1" onchange="change('kkczje_sd')"></html:text>元
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td width="20%" class="2-td2-left">
                        <div align="left">
                          已计提折旧的年限<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td  class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                          	<html:text property="yjtzjnx_sd" maxlength="4" size="15" tabindex="-1" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>年
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td width="20%" class="2-td2-left">
                        <div align="left">
                          已计提的折旧额<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td  class="2-td2-center">
                        <div id="mx_zsfsdm_epx_visible_1" align="left">
                          <html:text property="yjtzje_sd" maxlength="11" size="15" tabindex="-1" onchange="change('yjtzje_sd')"></html:text>元
                        </div>
                      </td>
                    </tr>                
                  </table >



				  <table class="table-99" cellspacing="0" id="TABLE_LIST2"
              style="display: none">
			 <tr>
                      <td width="50%" class="2-td2-t-left">
                        <div align="left">
                          固定资产名称<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td colspan="3" class="2-td2-t-center">
                        <div align="left">
	                          <html:text property="gdzcmc_js" maxlength="11" size="15" tabindex="-1"></html:text>
                        </div>
                      </td>
                    </tr>
                    
                    
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          固定资产原值 <font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td colspan="3" class="2-td2-center">
                        <div align="left">
	                          <html:text property="gdzcyz_js" maxlength="11" size="15" tabindex="-1" onchange="change('gdzcyz_js')"></html:text>元
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          固定资产计税基础 <font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td colspan="3" class="2-td2-center">
                        <div align="left">
                          	<html:text property="gdzcjsjc_js" maxlength="11" size="15" tabindex="-1" onchange="change('gdzcjsjc_js')"></html:text>元
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          计算折旧的方法<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td colspan="3" class="2-td2-center">
                        <div align="left">
                          	<html:select property="jszjff_js">
		                        <html:option value="">请选择折旧方法</html:option>
		                        <html:option value="0">双倍余额递减法</html:option>
		                        <html:option value="1">年数总额法</html:option>
	                        </html:select>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">
                        <div align="left">
                          年折旧额<font color="#FF0000">
                            *
                          </font>
                        </div>
                      </td>
                      <td colspan="3" class="2-td2-center">
                        <div align="left">
	                         <html:text property="nzje_js" maxlength="11" size="15" tabindex="-1" onchange="change('nzje_js')"></html:text>元
                        </div>
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
                  
            </td>
          </tr>
        </table>
        <br>
        <%@ include file="../../include/footer.jsp" %>
      </html:form>
  </body>

</html>