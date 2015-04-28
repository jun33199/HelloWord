<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jwsdmxb.web.JwsdmxbForm"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.syax.creports.Constants"%>

<html>
<head>
<title>境外所得税抵扣计算明细表</title>
<link href="../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript"
	src="../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/qysdsnew.js"></script>
</head>

<script language="JavaScript">
	<%/*初始化页面*/%>
	function doInit()
	{
				doInitNumText();
		<%
		JwsdmxbForm jwsdmxbForm=(JwsdmxbForm)request.getAttribute("jwsdmxbForm");
		
		
				
		/**/
		if(jwsdmxbForm!=null && jwsdmxbForm.getDataList().size()>8){
			/*存在增加行时，先循环插入空行，再将值赋给所有子行显示（包括静态子行、动态子行）*/
			
			//动态子行数
			int dynamicRows=jwsdmxbForm.getDataList().size()-8;
			for(int i=0;i<dynamicRows;i++){
				%>
				DynamicAdd();
				<%
			}
		}
		
		//赋值
		if(jwsdmxbForm!=null ){
			for(int i=0;i<jwsdmxbForm.getDataList().size();i++){
				HashMap map=(HashMap)jwsdmxbForm.getDataList().get(i);
				String strL1=(String)map.get("L1");
				String strL2=(String)map.get("L2");
				String strL3=(String)map.get("L3");
				String strL4=(String)map.get("L4");
				String strL5=(String)map.get("L5");
				String strL6=(String)map.get("L6");
				String strL7=(String)map.get("L7");
				String strL8=(String)map.get("L8");
				String strL9=(String)map.get("L9");
				String strL10=(String)map.get("L10");
				String strL11=(String)map.get("L11");
				String strL12=(String)map.get("L12");
				String strL13=(String)map.get("L13");
				System.out.println(i+"----"+strL1);
				System.out.println(i+"----"+strL2);
				System.out.println(i+"----"+strL3);
				System.out.println(i+"----"+strL4);
				System.out.println(i+"----"+strL5);
				System.out.println(i+"----"+strL6);
				System.out.println(i+"----"+strL7);
				System.out.println(i+"----"+strL8);
				System.out.println(i+"----"+strL9);
				System.out.println(i+"----"+strL10);
				System.out.println(i+"----"+strL11);
				System.out.println(i+"----"+strL12);
				System.out.println(i+"----"+strL13);
				%>
				setValue('<%=i%>','<%=strL1%>','<%=strL2%>','<%=strL3%>','<%=strL4%>','<%=strL5%>','<%=strL6%>','<%=strL7%>','<%=strL8%>','<%=strL9%>','<%=strL10%>','<%=strL11%>','<%=strL12%>','<%=strL13%>');
				<%
			}
		}
		
		%>
		
//		doInitNumText();
		
	   	var value=document.forms[0].jwsddk.value;
	    var list=document.getElementById("jwsd_list");
		var numRow=list.rows.length-2;
		if(value=="01"){
			graychange1(8,4,numRow,true,'text-gray');
		}
		if(value=="02"){
			graychange1(13,1,numRow,true,'text-gray');	
		}    	    
	}
	
	//第3行内容 从ActionForm中取List数据插入到table	
	function DynamicAdd()
	{
		var list=document.getElementById("jwsd_list");

		var newRow=list.insertRow(list.rows.length-1);
		//复选框
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'name='chk' value=''>";
		//国家(地区) 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L1' value='' size='8' tabindex='2'>";
		
		//境外所得  
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L2' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//弥补以前年度亏损
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L3' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//免税所得
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L4' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//境外应纳税所得额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L5' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//法定税率 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L6' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//境外所得应纳税额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L7' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//境外已缴纳的所得税税额 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L8' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//境外所得税扣除限额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L9' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//超过境外所得税扣除限额的余额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L10' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//本年可抵扣以前年度所得税额 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L11' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//前五年境外所得已缴税款未抵扣余额 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L12' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//定率抵扣额 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L13' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";

	}
	
	//全额扣除子行赋值
	function setValue(i,L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,L12,L13){
		var jwsdL1=document.all("L1");
		jwsdL1[parseInt(i)].value=L1
		
		var jwsdL2=document.all("L2");
		jwsdL2[parseInt(i)].value=L2
		
		var jwsdL3=document.all("L3");
		jwsdL3[parseInt(i)].value=L3
		
		var jwsdL4=document.all("L4");
		jwsdL4[parseInt(i)].value=L4
		
		var jwsdL5=document.all("L5");
		jwsdL5[parseInt(i)].value=L5
		
		var jwsdL6=document.all("L6");
		jwsdL6[parseInt(i)].value=L6
		
		var jwsdL7=document.all("L7");
		jwsdL7[parseInt(i)].value=L7
		
		var jwsdL8=document.all("L8");
		jwsdL8[parseInt(i)].value=L8
		
		var jwsdL9=document.all("L9");
		jwsdL9[parseInt(i)].value=L9
		
		var jwsdL10=document.all("L10");
		jwsdL10[parseInt(i)].value=L10
		
		var jwsdL11=document.all("L11");
		jwsdL11[parseInt(i)].value=L11
		
		var jwsdL12=document.all("L12");
		jwsdL12[parseInt(i)].value=L12
		
		var jwsdL13=document.all("L13");
		jwsdL13[parseInt(i)].value=L13
	}
	
	//插入子行
	function InsertNewRow(){	
		var list=document.getElementById("jwsd_list");

		//插入新的行
		var newRow=list.insertRow(list.rows.length-1);
	
		//复选框
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk' value=''>";
		
		//国家(地区)
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';         
		newCell.innerHTML="<input type='text' name='L1' value='' size='8' tabindex='2'>";
							
		//境外所得
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L2' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//弥补以前年度亏损
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L3' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//免税所得
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L4' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//境外应纳税所得额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L5' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//法定税率
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L6' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//境外所得应纳税额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L7' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//境外已缴纳的所得税税额 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L8' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//境外所得税扣除限额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L9' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//超过境外所得税扣除限额的余额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L10' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//本年可抵扣以前年度所得税额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L11' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//前五年境外所得已缴税款未抵扣余额 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='L12'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//定率抵扣额 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='text' name='L13'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//抵扣方法变化时，子行页面表现
		graychange(document.forms[0].jwsddk,0);
	}
	

	function RomoveRow()
	{
		//复选框
		var arrChk=document.all("chk");
		var list=document.getElementById("jwsd_list");
		
		//初始情况下，只有一个Checkbox时，不形成数组，故直接引用
		//当有多个checkbox时，或由多个删除为一个时，其实还是在数组里，所以
		//特作如下判断
		if(arrChk!=null){
			if(arrChk.length==undefined){
				if(arrChk.checked){
					if(window.confirm("确认删除吗？")){
						list.deleteRow(9);
					}else{
						arrChk.checked=false;
					}
				}
			}
			
			if(arrChk.length>=1){
				for(var i=arrChk.length-1;i>=0;i--){
					if(arrChk[i].checked){
						if(window.confirm("确认删除吗？")){
							list.deleteRow(i+9);
						}else{
							arrChk[i].checked=false;
						}
					}
				}
			}
		}
		
		return false;
	}
	
	//判断删除行内容是否为空
	//不为空，返回true
	//为空返回false
	function txt_null(s,i){
		if (s=="1"){   
			if 	 (document.all("L1").value!="" && document.all("L1").value!=undefined)	{
				return true;
			} 
			if   (document.all("L2").value!="" && document.all("L2").value!=undefined)	{
				return true;
			}  
			if   (document.all("L3").value!="" && document.all("L3").value!=undefined)	{
				return true;
			}  
			if   (document.all("L4").value!="" && document.all("L4").value!=undefined)	{
				return true;
			}  
			if   (document.all("L5").value!="" && document.all("L5").value!=undefined)	{
				return true;
			}  
			if   (document.all("L6").value!="" && document.all("L6").value!=undefined)	{
				return true;
			}  
			if   (document.all("L7").value!="" && document.all("L7").value!=undefined)	{
				return true;
			}  
			if   (document.all("L8").value!="" && document.all("L8").value!=undefined)	{
				return true;
			}  
			if   (document.all("L9").value!="" && document.all("L9").value!=undefined)	{
				return true;
			}  
			if   (document.all("L10").value!="" && document.all("L10").value!=undefined)	{
				return true;
			}  
			if   (document.all("L11").value!="" && document.all("L11").value!=undefined)	{
				return true;
			}  
			if   (document.all("L12").value!="" && document.all("L12").value!=undefined)	{
				return true;
			}  
			if   (document.all("L13").value!="" && document.all("L13").value!=undefined)	{
				return true;
			}  
		}else{   
			if   (document.all("L1")[i].value!="")   {
				return true;
			}   
			if   (document.all("L2")[i].value!="")   {
				return true;
			}  
			if   (document.all("L3")[i].value!="")   {
				return true;
			} 
			if   (document.all("L4")[i].value!="")   {
				return true;
			}
			if   (document.all("L5")[i].value!="")   {
				return true;
			}
			if   (document.all("L6")[i].value!="")   {
				return true;
			}
			if   (document.all("L7")[i].value!="")   {
				return true;
			}
			if   (document.all("L8")[i].value!="")   {
				return true;
			}
			if   (document.all("L9")[i].value!="")   {
				return true;
			}
			if   (document.all("L10")[i].value!="")   {
				return true;
			}
			if   (document.all("L11")[i].value!="")   {
				return true;
			}
			if   (document.all("L12")[i].value!="")   {
				return true;
			}
			if   (document.all("L13")[i].value!="")   {
				return true;
			}		   
		}
		return false;
	}
	
	<%/*保存*/%>
	function doSave()
	{
		var list="<%=Constants.QYSDS_CONTROL_CHAR_FOR_JS%>";
		if (!Char_Vaildate(document.forms[0],list)){
			return false;	
		}
		doSubmitForm('Save');
	}
	<%/*单表校验*/%>
	function doCheck()
	{
		var list="<%=Constants.QYSDS_CONTROL_CHAR_FOR_JS%>";
		if (!Char_Vaildate(document.forms[0],list)){
			return false;	
		}
		doSubmitForm('Check');
	}
	<%/*清除页面数据*/%>
	function doReset()
	{
	    if(confirm("确认是否清除当前数据？"))
	    {
	    	var list=document.getElementById("jwsd_list");
			var numRow=list.rows.length-2;
	    	var arr = new Array();	    	
	    	for(var i=0;i<13;i++){
	    		arr[i]=document.all('L'+(i+1));
	    		for(var j=0;j<parseInt(numRow);j++){	    			
	    			if(arr[i][j].readOnly!=true){
		    			arr[i][j].value="";	  
	    			}	    			  			    	
	    		}
	    	}
	    }
	}	
	<%/*删除*/%>
	function doDelete()
	{	
		doSubmitForm('Delete');
	}
	
	<%/*返回*/%>
	function doExit()
	{
		doSubmitForm('Exit');
	}
	
	<%/*单元格输入数据格式的控制*/%>
	function doInitNumText(){
		var list=document.getElementById("jwsd_list");
		var numRow=list.rows.length-2;
	    for(var i=2;i<14;i++){
	    	var L ='L'+i;
	    	initNumText(L,numRow);
	    }	
	}
	
	<%/*抵扣方法变换时页面表现的控制*/%>
	function graychange(obj,num0){
		//抵扣方法为“01”或者“02”
		var valu = obj.options[obj.selectedIndex].value;
		var list=document.getElementById("jwsd_list");
		//取得可输入的总行数
		var numRow=list.rows.length-2;
		
		if(valu=="01"){
			graychange1(8,4,numRow,true,'text-gray',num0);
			graychange1(13,1,numRow,false,'',num0);
		}
		if(valu=="02"){
			graychange1(13,1,numRow,true,'text-gray',num0);
			graychange1(8,4,numRow,false,'',num0);
		}	
	}
	<%/*页面变化的具体实现*/%>
	function graychange1(num1,num2,num3,flag1,flag2,num0){
		//num0为是否对动态子行操作的标志
		//num1为从第几列开始变化，num2需要发生变化的列的数量，num3表的总行数
		//falg1只读与否的标志，falg2单元格的样式
		var arr = new Array();
		if(num0==0){
			for(var i=0;i<num2;i++){
				arr[i]=document.all('L'+(num1+i));
				for(var j=num3-2;j<num3-1;j++){
					arr[i][j].readOnly=flag1;
					arr[i][j].className=flag2;
					arr[i][j].value='';
					if(flag1){
						arr[i][j].tabIndex='-1';
					}else{
						arr[i][j].tabIndex='2';
					}
				}
			}
		}else{
			for(var i=0;i<num2;i++){
				arr[i]=document.all('L'+(num1+i));
				for(var j=0;j<num3;j++){
					arr[i][j].readOnly=flag1;
					arr[i][j].className=flag2;
					arr[i][j].value='';
					if(flag1){
						arr[i][j].tabIndex='-1';
					}else{
						arr[i][j].tabIndex='2';
					}
				}		
			}
		}
		//InitTabindex(document.forms[0]);				
	}
	
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" style="margin:0" onload="doInit()">
<%@include file="../include/header.jsp"%>

<html:form action="/webapp/smsb/qysds/jwsdmxbAction.do">
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />

	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">境外所得税抵扣计算明细表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="jwsdmxbForm" property="sbrq" scope="request" filter="true" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额单位：元（列至角分）
			</td>
		</tr>
		<tr>
			<td class="1-td2">&nbsp;&nbsp;计算机代码:<bean:write name="jwsdmxbForm"
				property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="jwsdmxbForm" property="nsrmc" scope="request" filter="true" />&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			抵扣方法：<html:select property="jwsddk" onchange="graychange(this)" >
				<html:option value="<%=CodeConstant.JWSDDK01%>">定率抵扣</html:option>
				<html:option value="<%=CodeConstant.JWSDDK02%>">分国不分项限额抵扣</html:option>
			</html:select></td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">
			<TABLE class="table-99" align="center">
				<TR>
					<TD>
					<table id="jwsd_list" border="1" cellspacing="0" class="table-99"
						align="center">
						<tr>
							<td class="2-td1-left">选择</td>
							<td class="2-td1-left">国家(地区)</td>
							<td class="2-td1-left">境外所得</td>
							<td class="2-td1-left">弥补以前年度亏损</td>
							<td class="2-td1-left">免税所得</td>
							<td class="2-td1-left">境外应纳税所得额</td>
							<td class="2-td1-left">法定税率</td>
							<td class="2-td1-left">境外所得应纳税额</td>
							<td class="2-td1-left">境外已缴纳的所得税税额</td>
							<td class="2-td1-left">境外所得税扣除限额</td>
							<td class="2-td1-left">超过境外所得税扣除限额的余额</td>
							<td class="2-td1-left">本年可抵扣以前年度所得税额</td>
							<td class="2-td1-left">前五年境外所得已缴税款未抵扣余额</td>
							<td class="2-td1-center">定率抵扣额</td>
						</tr>
						<tr>
							<td class="2-td2-left">&nbsp;</td>
							<td class="2-td2-left" nowrap>1</td>
							<td class="2-td2-left" nowrap>2</td>
							<td class="2-td2-left" nowrap>3</td>
							<td class="2-td2-left" nowrap>4</td>
							<td class="2-td2-left" nowrap>5(2-3-4)</td>
							<td class="2-td2-left" nowrap>6</td>
							<td class="2-td2-left" nowrap>7(5×6)</td>
							<td class="2-td2-left" nowrap>8</td>
							<td class="2-td2-left" nowrap>9</td>
							<td class="2-td2-left" nowrap>10(8-9)</td>
							<td class="2-td2-left" nowrap>11</td>
							<td class="2-td2-left" nowrap>12</td>
							<td class="2-td2-center" nowrap>13</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>&nbsp</td>
							<input type='hidden' name='hc' id='hc_1' value='1'>
							<td class="2-td2-left" nowrap><input type='text' name='L1'
								id='1_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L2'
								id='1_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L3'
								id='1_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L4'
								id='1_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L5'
								id='1_5' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L6'
								id='1_6' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L7'
								id='1_7' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L8'
								id='1_8' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L9'
								id='1_9' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L10'
								id='1_10' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L11'
								id='1_11' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L12'
								id='1_12' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" nowrap><input type='text' name='L13'
								id='1_13' value='' size='8' tabindex='2'></td>
						</tr>
						<tr>
							<input type='hidden' name='hc' id='hc_2' value='2'>
							<td class="2-td2-left" nowrap>&nbsp</td>
							<td class="2-td2-left" nowrap><input type='text' name='L1'
								id='2_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L2'
								id='2_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L3'
								id='2_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L4'
								id='2_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L5'
								id='2_5' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L6'
								id='2_6' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L7'
								id='2_7' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L8'
								id='2_8' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L9'
								id='2_9' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L10'
								id='2_10' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L11'
								id='2_11' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L12'
								id='2_12' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" nowrap><input type='text' name='L13'
								id='2_13' value='' size='8' tabindex='2'></td>
						</tr>
						<tr>
							<input type='hidden' name='hc' id='hc_3' value='3'>
							<td class="2-td2-left" nowrap>&nbsp</td>
							<td class="2-td2-left" nowrap><input type='text' name='L1'
								id='3_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L2'
								id='3_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L3'
								id='3_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L4'
								id='3_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L5'
								id='3_5' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L6'
								id='3_6' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L7'
								id='3_7' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L8'
								id='3_8' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L9'
								id='3_9' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L10'
								id='3_10' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L11'
								id='3_11' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L12'
								id='3_12' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" nowrap><input type='text' name='L13'
								id='3_13' value='' size='8' tabindex='2'></td>
						</tr>
						<tr>

							<td class="2-td2-left" nowrap>&nbsp</td>
							<input type='hidden' name='hc' id='hc_4' value='4'>
							<td class="2-td2-left" nowrap><input type='text' name='L1'
								id='4_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L2'
								id='4_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L3'
								id='4_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L4'
								id='4_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L5'
								id='4_5' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L6'
								id='4_6' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L7'
								id='4_7' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L8'
								id='4_8' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L9'
								id='4_9' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L10'
								id='4_10' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L11'
								id='4_11' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L12'
								id='4_12' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" nowrap><input type='text' name='L13'
								id='4_13' value='' size='8' tabindex='2'></td>
						</tr>
						<tr>

							<td class="2-td2-left" nowrap>&nbsp</td>
							<input type='hidden' name='hc' id='hc_5' value='5'>
							<td class="2-td2-left" nowrap><input type='text' name='L1'
								id='5_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L2'
								id='5_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L3'
								id='5_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L4'
								id='5_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L5'
								id='5_5' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L6'
								id='5_6' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L7'
								id='5_7' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L8'
								id='5_8' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L9'
								id='5_9' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L10'
								id='5_10' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L11'
								id='5_11' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L12'
								id='5_12' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" nowrap><input type='text' name='L13'
								id='5_13' value='' size='8' tabindex='2'></td>
						</tr>
						<tr>

							<td class="2-td2-left" nowrap>&nbsp</td>
							<input type='hidden' name='hc' id='hc_6' value='6'>
							<td class="2-td2-left" nowrap><input type='text' name='L1'
								id='6_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L2'
								id='6_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L3'
								id='6_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L4'
								id='6_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L5'
								id='6_5' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L6'
								id='6_6' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L7'
								id='6_7' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L8'
								id='6_8' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L9'
								id='6_9' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L10'
								id='6_10' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L11'
								id='6_11' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L12'
								id='6_12' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" nowrap><input type='text' name='L13'
								id='6_13' value='' size='8' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>&nbsp</td>
							<input type='hidden' name='hc' id='hc_7' value='7'>
							<td class="2-td2-left" nowrap><input type='text' name='L1'
								id='7_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L2'
								id='7_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L3'
								id='7_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L4'
								id='7_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L5'
								id='7_5' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L6'
								id='7_6' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L7'
								id='7_7' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L8'
								id='7_8' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L9'
								id='7_9' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L10'
								id='7_10' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L11'
								id='7_11' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L12'
								id='7_12' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" nowrap><input type='text' name='L13'
								id='7_13' value='' size='8' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>&nbsp;</td>
							<input type='hidden' name='hc' id='hc_8' value='8'>
							<td class="2-td2-left" nowrap><input type='text' name='L1'
								id='8_1' value='合计' size='3' tabindex='-1' readonly="true"
								class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L2'
								id='8_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L3'
								id='8_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L4'
								id='8_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L5'
								id='8_5' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L6'
								id='8_6' value='' size='8' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L7'
								id='8_7' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L8'
								id='8_8' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L9'
								id='8_9' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L10'
								id='8_10' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L11'
								id='8_11' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" nowrap><input type='text' name='L12'
								id='8_12' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" nowrap><input type='text' name='L13'
								id='8_13' value='' size='8' tabindex='2'></td>
						</tr>
					</table>
					</TD>
				</TR>
				<TR class="black9">
					<TD>

					<div align="center"><a style="cursor:hand" id="previous"
				onclick="gotoPreviousTable()"><img name="spage" value="上一张表"
				alt="填写上一张表"
				onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				src="<%=static_contextpath%>images/shangyiye1.jpg" id="previoustable">
			</a>&nbsp;&nbsp;
			
			 <a style="cursor:hand" id="next"
				onclick="gotoNextTable()"><img name="xpage" value="下一张表"
				alt="填写下一张表"
				onMouseOver="MM_swapImage('nexttable','','<%=static_contextpath%>images/xaiyiye2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				src="<%=static_contextpath%>images/xaiyiye1.jpg" id="nexttable"> </a>&nbsp;&nbsp;
					<input type="image" style="cursor:hand"
						tabIndex="-1" onClick="doReset();return false;" accesskey="u"
						value="清除" alt="清除页面输入框信息"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" 
						id="qingchu" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="InsertNewRow();return false;" accesskey="s" value="增加"
						alt="增加一行"
						onMouseOver="MM_swapImage('addrow','','<%=static_contextpath%>images/zj-a2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/zj-a1.jpg" 
						id="addrow" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="RomoveRow();return false;" accesskey="s" value="删除"
						alt="删除选中的行"
						onMouseOver="MM_swapImage('delete','','<%=static_contextpath%>images/sc-d2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/sc-d1.jpg" 
						id="delete" /> &nbsp;&nbsp;<input type="image"
						style="cursor:hand" tabIndex="-1" onClick="doSave();return false;"
						accesskey="s" value="保存" alt="保存"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" 
						id="baocun" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doCheck();return false;" accesskey="d" value="单表校验"
						alt="表内关系校验"
						onMouseOver="MM_swapImage('jiaoyan','','../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../images/b-bdjyd1.jpg"
						 id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doDelete();return false;" accesskey="x" value="全部删除"
						alt="删除本表所有数据，且不可恢复"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" 
						id="shanchu" /> &nbsp;&nbsp; <input type="image" value="返回"
						alt="返回到企业所得税年报主页面" style="cursor:hand" tabIndex="-1"
						onClick="doExit();return false;"
						onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/fanhui1.jpg" 
						id="fanhui" /></div>
					</TD>
				</TR>
			</TABLE>
			</td>
		</tr>
	</table>

	<%@ include file="../include/footernew.jsp"%>
</html:form>

</body>
</html>


