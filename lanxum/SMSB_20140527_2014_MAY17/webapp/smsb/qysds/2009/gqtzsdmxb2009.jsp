
<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.gqtzsdmxb.web.GqtzsdmxbForm"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.syax.creports.Constants"%>

<html>
<head>
<title>长期股权投资所得（损失）明细表</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/function.js"></script>
</head>
<script language=JavaScript>
	/*初始化*/
	function doInits()
	{	
			doInitNumText();
			
		
		<%
		GqtzsdmxbForm jwsdmxbForm=(GqtzsdmxbForm)request.getAttribute("gqtzsdmxbForm2009");

		/**/
		if(jwsdmxbForm!=null && jwsdmxbForm.getDataList().size()>8){
			/*存在增加行时，先循环插入空行，再将值赋给所有子行显示（包括静态子行、动态子行）*/
			
			//动态子行数
			int dynamicZjdmRows=jwsdmxbForm.getDataList().size()-8;
			for(int i=0;i<dynamicZjdmRows;i++){
				%>
				InsertRow();
				<%
			}
		}

		%>
		<%
		//赋值
		if(jwsdmxbForm!=null ){
			for(int i=0;i<jwsdmxbForm.getDataList().size();i++){
				HashMap map=(HashMap)jwsdmxbForm.getDataList().get(i);
				String hc =(String)map.get("hc");
				
				String strL1 =(String)map.get("C1")==null?"":(String)map.get("C1");
				String strL2 =(String)map.get("C2")==null?"":(String)map.get("C2");
				String strL3 =(String)map.get("C3")==null?"":(String)map.get("C3");
				String strL4 =(String)map.get("C4")==null?"":(String)map.get("C4");
				String strL5 =(String)map.get("C5")==null?"":(String)map.get("C5");
				String strL6 =(String)map.get("C6")==null?"":(String)map.get("C6");
				String strL7 =(String)map.get("C7")==null?"":(String)map.get("C7");
				String strL8 =(String)map.get("C8")==null?"":(String)map.get("C8");
				String strL9 =(String)map.get("C9")==null?"":(String)map.get("C9");
				String strL10=(String)map.get("C10")==null?"":(String)map.get("C10");
				String strL11=(String)map.get("C11")==null?"":(String)map.get("C11");
				String strL12=(String)map.get("C12")==null?"":(String)map.get("C12");
				String strL13=(String)map.get("C13")==null?"":(String)map.get("C13");
				String strL14=(String)map.get("C14")==null?"":(String)map.get("C14");
				String strL15=(String)map.get("C15")==null?"":(String)map.get("C15");
				String strL16=(String)map.get("C16")==null?"":(String)map.get("C16");
				%>
				setZjdmValue('<%=hc%>','<%=strL1%>','<%=strL2%>','<%=strL3%>','<%=strL4%>','<%=strL5%>','<%=strL6%>','<%=strL7%>','<%=strL8%>','<%=strL9%>','<%=strL10%>','<%=strL11%>','<%=strL12%>','<%=strL13%>','<%=strL14%>','<%=strL15%>','<%=strL16%>');
				<%
			}
			

			
			for(int i=0;i<jwsdmxbForm.getHjList().size();i++){
					HashMap map=(HashMap)jwsdmxbForm.getHjList().get(i);
					int hc=Integer.parseInt((String)map.get("hjhc"));
					String hjje=(String)map.get("hjje");
					%>
					obj = eval("document.getElementById('HJ_<%=hc%>')");
					obj.value = '<%=hjje%>';
					<% 
			}
			for(int i=0;i<jwsdmxbForm.getSbbczlList().size();i++){
					HashMap map=(HashMap)jwsdmxbForm.getSbbczlList().get(i);
			    int hc=Integer.parseInt((String)map.get("hc1"));
			    System.out.println("hc ==" + hc);
			   String P_1=(String)map.get("P_1");
			    String P_2=(String)map.get("P_2");
			    String P_3=(String)map.get("P_3");
			   String P_4=(String)map.get("P_4");
			   String P_5=(String)map.get("P_5");
					%>
					obj = eval("document.getElementById('P_1_<%=hc%>')");
					obj.value = '<%=P_1%>';
					obj = eval("document.getElementById('P_2_<%=hc%>')");
					obj.value = '<%=P_2%>';
					obj = eval("document.getElementById('P_3_<%=hc%>')");
					obj.value = '<%=P_3%>';
					obj = eval("document.getElementById('P_4_<%=hc%>')");
					obj.value = '<%=P_4%>';
					obj = eval("document.getElementById('P_5_<%=hc%>')");
					obj.value = '<%=P_5%>';
					<% 
			}
			String bz32 =  jwsdmxbForm.getBz32();
		  String item58 = jwsdmxbForm.getItem58();
			%>
					obj = eval("document.getElementById('bz32')");
					obj.value = '<%=bz32%>';
					obj = eval("document.getElementById('item58')");
					obj.value = '<%=item58%>';
			
			<%
			
			
			
				
		}
		
		%>
		
			 obj = eval("document.getElementById('P_1_1')");
			 obj.value = '<%=jwsdmxbForm.getNd5()%>';
			 obj = eval("document.getElementById('P_1_2')");
			 obj.value = '<%=jwsdmxbForm.getNd4()%>';
			 obj = eval("document.getElementById('P_1_3')");
			 obj.value = '<%=jwsdmxbForm.getNd3()%>';
			 obj = eval("document.getElementById('P_1_4')");
			 obj.value = '<%=jwsdmxbForm.getNd2()%>';
			 obj = eval("document.getElementById('P_1_5')");
			 obj.value = '<%=jwsdmxbForm.getNd1()%>';

       
		}
		
			function setZjdmValue(hc,L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,L12,L13,L14,L15,L16){
		var indexHc=parseInt(hc)-1;
		var zjdmL1=document.all("C1");
		zjdmL1[parseInt(indexHc)].value=L1;
		
		var zjdmL2=document.all("C2");
		zjdmL2[parseInt(indexHc)].value=L2;
		
		var zjdmL3=document.all("C3");
		zjdmL3[parseInt(indexHc)].value=L3;
		
		var zjdmL4=document.all("C4");
		zjdmL4[parseInt(indexHc)].value=L4;
		
		var zjdmL5=document.all("C5");
		zjdmL5[parseInt(indexHc)].value=L5;
		
		var zjdmL6=document.all("C6");
		zjdmL6[parseInt(indexHc)].value=L6;
		
		var zjdmL7=document.all("C7");
		zjdmL7[parseInt(indexHc)].value=L7;
		
		var zjdmL8=document.all("C8");
		zjdmL8[parseInt(indexHc)].value=L8;
		
		var zjdmL9=document.all("C9");
		zjdmL9[parseInt(indexHc)].value=L9;
		
		var zjdmL10=document.all("C10");
		zjdmL10[parseInt(indexHc)].value=L10;
		
		var zjdmL11=document.all("C11");
		zjdmL11[parseInt(indexHc)].value=L11;
		
		var zjdmL12=document.all("C12");
		zjdmL12[parseInt(indexHc)].value=L12;
		
		var zjdmL13=document.all("C13");
		zjdmL13[parseInt(indexHc)].value=L13;
		
		var zjdmL14=document.all("C14");
		zjdmL14[parseInt(indexHc)].value=L14;
		
		var zjdmL15=document.all("C15");
		zjdmL15[parseInt(indexHc)].value=L15;
		
		var zjdmL16=document.all("C16");
		zjdmL16[parseInt(indexHc)].value=L16;
		
		
	}
	<%/*单元格输入数据格式的控制*/%>
	function doInitNumText(){
	    for(var i=2;i<17;i++){
	    	var L ='C'+i;
	    	initNumText(L);
	    }	
	    
	    for(var i=2;i<6;i++){
	    	var L ='P_'+i;
	    	initNumText(L);
	    }
	    
	    initNumText("hjje");
	   
	}
	
	function ResetHc(){
		var list=document.getElementById("jwsd_list");
		
		var maxrow=parseInt(document.forms[0].maxZjdmRowIndex.value);
		for(var i=12;i<parseInt(maxrow);i++){
			list.rows(i).cells(1).innerHTML=parseInt(i)-parseInt(3);
		}
	}
		
			//插入子行
	function InsertRow(){	
		var list=document.getElementById("jwsd_list");
		
		var maxrow=document.forms[0].maxZjdmRowIndex.value;
		var hcIndex =parseInt(document.forms[0].maxZjdmRowIndex.value)+1;

        var idindex = parseInt(hcIndex)-4;
		
		//插入新的行
		var newRow=list.insertRow(parseInt(maxrow));

		//复选框
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chkZJ' value=''>";

		//行次
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML=hcIndex-4;
		
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';         
		newCell.innerHTML="<input type='text' name='C1' value='' size='8' tabindex='1' maxLength='100' id ='" + idindex + "_1'>";
							
		//境外所得
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='C2' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1' id ='" + idindex + "_2'>";
		
		//境外所得换算含税所得
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='C3' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1' id ='" + idindex + "_3'>";
		
		//弥补以前年度亏损
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='C4' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1' id ='" + idindex + "_4'>";
		
		//免税所得
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='C5' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1' id ='" + idindex + "_5'>";
		
		//弥补亏损前境外应税所得额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='C6' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1' id ='" + idindex + "_6'>";
		
		//可弥补境内亏损
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='C7' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1' id ='" + idindex + "_7'>";
		
		//境外应纳税所得额 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='C8' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1' id ='" + idindex + "_8'>";
		
		//税率(%)
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='C9' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1' id ='" + idindex + "_9'>";
		
		//境外所得应纳税额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='C10' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1' id ='" + idindex + "_10'>";
		
		//境外所得可抵免税额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='C11' value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1' id ='" + idindex + "_11'>";
		
		//境外所得税款抵免限额 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='C12'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1' id ='" + idindex + "_12'>";
		
		//本年可抵免的境外所得税款 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='text' name='C13'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1' id ='" + idindex + "_13'>";
		
		//未超过境外所得税款抵免限额的余额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='C14'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1' id ='" + idindex + "_14'>";

		//本年可抵免以前年度所得税额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='C15'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1' id ='" + idindex + "_15'>";
		
		//前五年境外所得已缴税款未抵免余额
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='text' name='C16'  value='' size='8' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1' id ='" + idindex + "_16'><input type='hidden' name='hc'  id='hc_" + hcIndex + "' value= '" + hcIndex+ "' />";		
		
		//最大行加 1 
		document.forms[0].maxZjdmRowIndex.value=parseInt(document.forms[0].maxZjdmRowIndex.value)+1;
				
	}
	function RomoveRow()
	{
		//复选框
		var arrChk=document.all("chkZJ");
		var list=document.getElementById("jwsd_list");
		var flag =true;
		
		//初始情况下，只有一个Checkbox时，不形成数组，故直接引用
		//当有多个checkbox时，或由多个删除为一个时，其实还是在数组里，所以
		//特作如下判断
		if(arrChk!=null){
			if(arrChk.length==undefined){
				if(arrChk.checked){
					if(window.confirm("该操作只是从页面上删除增加的数据行，确认要删除选中的数据行吗？")){
						list.deleteRow(12);
						document.forms[0].maxZjdmRowIndex.value=parseInt(document.forms[0].maxZjdmRowIndex.value)-1;
  					return false;
					}else{
						arrChk.checked=false;
					}
				}else{
					alert("请先选中要删除的数据行！");
					return false;
				}
			}
			
			if(arrChk.length>=1){
				for(var i=arrChk.length-1;i>=0;i--){
					if(arrChk[i].checked){
						flag=false;
						if(window.confirm("该操作只是从页面上删除增加的数据行，确认要删除选中的数据行吗？")){
							break;
						}else{
							return false;
						}
					}
				}
				
				if(flag){
					alert("请先选中要删除的数据行！");
					return false;
				}
			}
			
			if(arrChk.length>=1){
				for(var i=arrChk.length-1;i>=0;i--){
					if(arrChk[i].checked){
							list.deleteRow(i+12);
							document.forms[0].maxZjdmRowIndex.value=parseInt(document.forms[0].maxZjdmRowIndex.value)-1;
					}
				}
				ResetHc();
				return false;
			}

		}
		return false;
	}


    function checkTzqy()
	{
		var hs = document.forms[0].C1.length;
		for(var i=1;i<=hs;i++ )
		{
			for(var j=2;j<17;j++)
			{
				var elementId = i+"_"+j;
		        var obj = document.getElementById(elementId);
				if(obj.value!=null&&obj.value!="")
				{
				    if(document.getElementById(i+"_1").value==null||document.getElementById(i+"_1").value==""){
					alert("请输入第" + i +"行的被投资企业名称");
                    document.getElementById(i+"_1").focus();
					return false;
					}

				}

			}
		}
		return true;
	}
	function doSave()
	{
		if (checkTzqy())
		{
					var list="<%=Constants.QYSDS_CONTROL_CHAR_FOR_JS%>";
		            if (!Char_Vaildate(document.forms[0],list)){
			         return false;	
		             }
		            doSubmitForm('Save');

		}
	}
	
	
	function doReset()
	{
	    if(confirm("确认是否清除当前数据？"))
	    {
	    	var maxZjRow=parseInt(document.forms[0].maxZjdmRowIndex.value)-4;
	    	var arr = new Array();	    	
	    	for(var i=0;i<16;i++){
	    		arr[i]=document.all('C'+(i+1));
	    		for(var j=0;j<parseInt(maxZjRow);j++){	    			
	    			if(arr[i][j].readOnly!=true){
		    			arr[i][j].value="";	  
	    			}	    			  			    	
	    		}
	    	}
				
	    	
	    	for(var i=17;i<=31;i++){
	    		var obj=document.getElementById("HJ_"+i);
	    		obj.value="";
	    	}
	    		var obj=document.getElementById("bz32");
	    		obj.value="";
	    		var obj=document.getElementById("item58");
	    		obj.value="";
	    	
	    	for(var i=1;i<=5;i++){
	    		var obj=document.getElementById("P_2_"+i);
	    		obj.value="";
	    		var obj=document.getElementById("P_3_"+i);
	    		obj.value="";
	    		var obj=document.getElementById("P_4_"+i);
	    		obj.value="";
	    		var obj=document.getElementById("P_5_"+i);
	    		obj.value="";
	    	}
	    	
	    	
	    }
	}
	
	
	
	function doDelete()
	{
		doSubmitForm('Delete');
	}
	

	function doChecked()
	{

		if (checkTzqy())
		{

		 var list="<%=Constants.QYSDS_CONTROL_CHAR_FOR_JS%>";
		if (!Char_Vaildate(document.forms[0],list)){
			return false;	
		}
		 doSubmitForm('Check');
		  
		}
	}
		<%/*返回*/%>
		function doExit()
		{
			doSubmitForm('Exit');
		}
		
		function checkBz(obj)
		{
		  if(obj.value.length >100)
		  {
		     alert("输入不能大于100个字符！");
			 window.event.returnValue=false;
			obj.focus();
			obj.select();
		  }
		}
	
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInits()">
<%@ include file="../../include/header.jsp"%>
<br>

<html:form action="/webapp/smsb/qysds/2009/gqtzsdmxbAction.do">
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
		<input type="hidden" name="maxZjdmRowIndex" value="12">


	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">长期股权投资所得（损失）明细表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="gqtzsdmxbForm2009" property="sbrq" scope="request" filter="true" />
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
			<td class="1-td2" colspan="7">&nbsp;&nbsp;计算机代码:<bean:write
				name="gqtzsdmxbForm2009" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="gqtzsdmxbForm2009" property="nsrmc" scope="request" filter="true" />&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">
			<TABLE class="table-99" align="center">
				<TR>
					<TD>
					<div id="Layer2" style="position:static; ">
					<table id="jwsd_list" border="1" cellspacing="0" class="table-99"
						align="center">
						<tr>
							<td rowspan="3" colspan="2" class="2-td1-left">行次</td>
							<td rowspan="3" class="2-td1-left">被投资企业</td>
							<td rowspan="3" class="2-td1-left">期初投资额</td>
							<td rowspan="3" class="2-td1-left">本年度增（减）投资额</td>
							<td colspan="2" class="2-td1-left">投资成本</td>
							<td colspan="5" class="2-td1-left">股息红利</td>
							<td colspan="6" class="2-td1-center">投资转让所得（损失）</td>
						</tr>
						<tr>
							<td rowspan="2" class="2-td1-left">初始投资成本</td>
							<td rowspan="2" class="2-td1-left">权益法核算对初始投资成本调整产生的收益</td>
							<td rowspan="2" class="2-td1-center">会计核算投资收益</td>
							<td rowspan="2" class="2-td1-center">会计投资损益</td>
							<td colspan="2" class="2-td1-center">税收确认的股息红利</td>
							<td rowspan="2" class="2-td1-center">会计与税收的差异</td>
							<td rowspan="2" class="2-td1-center">投资转让净收入</td>
							<td rowspan="2" class="2-td1-center">投资转让的会计成本</td>
							<td rowspan="2" class="2-td1-center">投资转让的税收成本</td>
							<td rowspan="2" class="2-td1-center">会计上确认的转让所得或损失</td>
							<td rowspan="2" class="2-td1-center">按税收计算的投资转让所得或损失</td>
							<td rowspan="2" class="2-td1-center">会计与税收的差异</td>
						</tr>
						<tr>
							<td class="2-td1-left">免税收入</td>
							<td class="2-td1-left">全额征税收入</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">选择</td>
							<td class="2-td2-left">1</td>
							<td class="2-td2-left">2</td>
							<td class="2-td2-left">3</td>
							<td class="2-td2-left">4</td>
							<td class="2-td2-left">5</td>
							<td class="2-td2-left">6（7＋14）</td>
							<td class="2-td2-left">7</td>
							<td class="2-td2-left">8</td>
							<td class="2-td2-left">9</td>
							<td class="2-td2-left">10（7－8－9）</td>
							<td class="2-td2-left">11</td>
							<td class="2-td2-left">12</td>
							<td class="2-td2-left">13</td>
							<td class="2-td2-left">14（11－12）</td>
							<td class="2-td2-left">15（11－13）</td>
							<td class="2-td2-center">16（14－15）</td>
						</tr>
						<tr>
							<td class="2-td2-left" >&nbsp;&nbsp;</td>
							<td class="2-td2-left">1</td>
							<td class="2-td2-left" nowrap><input type='text' name='C1'
								id='1_1' value='' size='8' tabindex='1' maxLength='100'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C2'
								id='1_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C3'
								id='1_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C4'
								id='1_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C5'
								id='1_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C6'
								id='1_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C7'
								id='1_7' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C8'
								id='1_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C9'
								id='1_9' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C10'
								id='1_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C11'
								id='1_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C12'
								id='1_12' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C13'
								id='1_13' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C14'
								id='1_14' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C15'
								id='1_15' value='' size='8' tabindex='1'></td>
							<td class="2-td2-center" nowrap><input type='text' name='C16'
								id='1_16' value='' size='8' tabindex='1'></td>
							<input type="hidden" name="hc"  id='hc_1' value="1" />
						</tr>
						<tr>
							<td class="2-td2-left" >&nbsp;&nbsp;</td>
							<td class="2-td2-left">2</td>
							<td class="2-td2-left" nowrap><input type='text' name='C1'
								id='2_1' value='' size='8' tabindex='1' maxLength='100'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C2'
								id='2_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C3'
								id='2_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C4'
								id='2_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C5'
								id='2_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C6'
								id='2_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C7'
								id='2_7' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C8'
								id='2_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C9'
								id='2_9' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C10'
								id='2_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C11'
								id='2_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C12'
								id='2_12' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C13'
								id='2_13' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C14'
								id='2_14' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C15'
								id='2_15' value='' size='8' tabindex='1'></td>
							<td class="2-td2-center" nowrap><input type='text' name='C16'
								id='2_16' value='' size='8' tabindex='1'></td>
							<input type="hidden" name="hc"  id='hc_2' value="2" />
						</tr>
						<tr>
							<td class="2-td2-left" >&nbsp;&nbsp;</td>
							<td class="2-td2-left">3</td>
							<td class="2-td2-left" nowrap><input type='text' name='C1'
								id='3_1' value='' size='8' tabindex='1' maxLength='100'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C2'
								id='3_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C3'
								id='3_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C4'
								id='3_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C5'
								id='3_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C6'
								id='3_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C7'
								id='3_7' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C8'
								id='3_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C9'
								id='3_9' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C10'
								id='3_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C11'
								id='3_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C12'
								id='3_12' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C13'
								id='3_13' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C14'
								id='3_14' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C15'
								id='3_15' value='' size='8' tabindex='1'></td>
							<td class="2-td2-center" nowrap><input type='text' name='C16'
								id='3_16' value='' size='8' tabindex='1'></td>
							<input type="hidden" name="hc"  id='hc_3' value="3" />
						</tr>
						<tr>
							<td class="2-td2-left" >&nbsp;&nbsp;</td>
							<td class="2-td2-left">4</td>
							<td class="2-td2-left" nowrap><input type='text' name='C1'
								id='4_1' value='' size='8' tabindex='1' maxLength='100'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C2'
								id='4_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C3'
								id='4_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C4'
								id='4_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C5'
								id='4_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C6'
								id='4_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C7'
								id='4_7' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C8'
								id='4_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C9'
								id='4_9' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C10'
								id='4_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C11'
								id='4_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C12'
								id='4_12' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C13'
								id='4_13' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C14'
								id='4_14' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C15'
								id='4_15' value='' size='8' tabindex='1'></td>
							<td class="2-td2-center" nowrap><input type='text' name='C16'
								id='4_16' value='' size='8' tabindex='1'></td>
							<input type="hidden" name="hc"  id='hc_4' value="4" />
						</tr>
						<tr>
							<td class="2-td2-left" >&nbsp;&nbsp;</td>
							<td class="2-td2-left">5</td>
							<td class="2-td2-left" nowrap><input type='text' name='C1'
								id='5_1' value='' size='8' tabindex='1' maxLength='100'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C2'
								id='5_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C3'
								id='5_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C4'
								id='5_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C5'
								id='5_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C6'
								id='5_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C7'
								id='5_7' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C8'
								id='5_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C9'
								id='5_9' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C10'
								id='5_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C11'
								id='5_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C12'
								id='5_12' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C13'
								id='5_13' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C14'
								id='5_14' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C15'
								id='5_15' value='' size='8' tabindex='1'></td>
							<td class="2-td2-center" nowrap><input type='text' name='C16'
								id='5_16' value='' size='8' tabindex='1'></td>
							<input type="hidden" name="hc"  id='hc_5' value="5" />
						</tr>
						<tr>
							<td class="2-td2-left" >&nbsp;&nbsp;</td>
							<td class="2-td2-left">6</td>
							<td class="2-td2-left" nowrap><input type='text' name='C1'
								id='6_1' value='' size='8' tabindex='1' maxLength='100'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C2'
								id='6_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C3'
								id='6_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C4'
								id='6_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C5'
								id='6_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C6'
								id='6_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C7'
								id='6_7' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C8'
								id='6_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C9'
								id='6_9' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C10'
								id='6_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C11'
								id='6_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C12'
								id='6_12' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C13'
								id='6_13' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C14'
								id='6_14' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C15'
								id='6_15' value='' size='8' tabindex='1'></td>
							<td class="2-td2-center" nowrap><input type='text' name='C16'
								id='6_16' value='' size='8' tabindex='1'></td>
							<input type="hidden" name="hc"  id='hc_6' value="6" />
						</tr>
						<tr>
							<td class="2-td2-left" >&nbsp;&nbsp;</td>
							<td class="2-td2-left">7</td>
							<td class="2-td2-left" nowrap><input type='text' name='C1'
								id='7_1' value='' size='8' tabindex='1' maxLength='100'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C2'
								id='7_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C3'
								id='7_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C4'
								id='7_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C5'
								id='7_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C6'
								id='7_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C7'
								id='7_7' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C8'
								id='7_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C9'
								id='7_9' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C10'
								id='7_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C11'
								id='7_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C12'
								id='7_12' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C13'
								id='7_13' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C14'
								id='7_14' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C15'
								id='7_15' value='' size='8' tabindex='1'></td>
							<td class="2-td2-center" nowrap><input type='text' name='C16'
								id='7_16' value='' size='8' tabindex='1'></td>
							<input type="hidden" name="hc"  id='hc_7' value="7" />
						</tr>
						<tr>
							<td class="2-td2-left" >&nbsp;&nbsp;</td>
							<td class="2-td2-left">8</td>
							<td class="2-td2-left" nowrap><input type='text' name='C1'
								id='8_1' value='' size='8' tabindex='1' maxLength='100'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C2'
								id='8_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C3'
								id='8_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C4'
								id='8_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C5'
								id='8_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C6'
								id='8_6' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C7'
								id='8_7' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C8'
								id='8_8' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C9'
								id='8_9' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C10'
								id='8_10' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C11'
								id='8_11' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C12'
								id='8_12' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C13'
								id='8_13' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C14'
								id='8_14' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap><input type='text' name='C15'
								id='8_15' value='' size='8' tabindex='1'></td>
							<td class="2-td2-center" nowrap><input type='text' name='C16'
								id='8_16' value='' size='8' tabindex='1'></td>
							<input type="hidden" name="hc"  id='hc_8' value="8" />
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">合计</td>
							<td class="2-td2-left" nowrap>*</td>
							<td class="2-td2-left" nowrap>
								<input type='hidden' name='hjhc' id='hjhc_17' value='17'>
                <input type='text' name='hjje' id='HJ_17' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>
								<input type='hidden' name='hjhc' id='hjhc_18' value='18'>
                <input type='text' name='hjje' id='HJ_18' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>
								<input type='hidden' name='hjhc' id='hjhc_19' value='19'>
                <input type='text' name='hjje' id='HJ_19' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>
								<input type='hidden' name='hjhc' id='hjhc_20' value='20'>
                <input type='text' name='hjje' id='HJ_20' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>
								<input type='hidden' name='hjhc' id='hjhc_21' value='21'>
                <input type='text' name='hjje' id='HJ_21' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>
								<input type='hidden' name='hjhc' id='hjhc_22' value='22'>
                <input type='text' name='hjje' id='HJ_22' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>
								<input type='hidden' name='hjhc' id='hjhc_23' value='23'>
                <input type='text' name='hjje' id='HJ_23' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>
								<input type='hidden' name='hjhc' id='hjhc_24' value='24'>
                <input type='text' name='hjje' id='HJ_24' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>
								<input type='hidden' name='hjhc' id='hjhc_25' value='25'>
                <input type='text' name='hjje' id='HJ_25' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>
								<input type='hidden' name='hjhc' id='hjhc_26' value='26'>
                <input type='text' name='hjje' id='HJ_26' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>
								<input type='hidden' name='hjhc' id='hjhc_27' value='27'>
                <input type='text' name='hjje' id='HJ_27' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>
								<input type='hidden' name='hjhc' id='hjhc_28' value='28'>
                <input type='text' name='hjje' id='HJ_28' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>
								<input type='hidden' name='hjhc' id='hjhc_29' value='29'>
                <input type='text' name='hjje' id='HJ_29' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left" nowrap>
								<input type='hidden' name='hjhc' id='hjhc_30' value='30'>
                <input type='text' name='hjje' id='HJ_30' value='' size='8' tabindex='1'></td>
							<td class="2-td2-center" nowrap>
								<input type='hidden' name='hjhc' id='hjhc_31' value='31'>
                <input type='text' name='hjje' id='HJ_31' value='' size='8' tabindex='1'></td>
						</tr>
						
						<tr>
							<td colspan="18" class="2-td2-center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投资损失补充资料</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">行次</td>
							<td class="2-td2-left">项目</td>
							<td class="2-td2-left">年度</td>
							<td colspan="2" class="2-td2-left"">当年度结转金额</td>
							<td class="2-td2-left"">已弥补金额</td>
							<td colspan="2" class="2-td2-left"">本年度弥补金额</td>
							<td colspan="3" class="2-td2-left"">结转以后年度待弥补金额</td>
							<td rowspan="7" colspan="6" class="2-td2-center"">备注:<TEXTAREA style="OVERFLOW: auto" id = "bz32" name="bz32" rows="10" cols="40" onblur ='checkBz(this)'></TEXTAREA></td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">1</td>
							<td class="2-td2-left">第一年</td>
							<td class="2-td2-left"><input type='text' name='P_1'
								id='P_1_1' value='' readonly='true' size='8' style='text-align:center' onblur ='isNum(this , 1000, 9999, null, 4, 0)' tabindex='1'></td>
							<td colspan="2" class="2-td2-left""><input type='text' name='P_2'
								id='P_2_1' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left""><input type='text' name='P_3'
								id='P_3_1' value='' size='8' tabindex='1'></td>
							<td colspan="2" class="2-td2-left""><input type='text' name='P_4'
								id='P_4_1' value='' size='8' tabindex='1'></td>
							<td colspan="3" class="2-td2-center""><input type='text' name='P_5'
								id='P_5_1' value='' size='8' tabindex='1'></td><input type="hidden" name="hc1"  id='hc_1' value="1" />
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">2</td>
							<td class="2-td2-left">第二年</td>
							<td class="2-td2-left"><input type='text' name='P_1'
								id='P_1_2' value='' readonly='true' size='8'  style='text-align:center' onblur ='isNum(this , 1000, 9999, null, 4, 0)' tabindex='1'></td>
							<td colspan="2" class="2-td2-left""><input type='text' name='P_2'
								id='P_2_2' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left""><input type='text' name='P_3'
								id='P_3_2' value='' size='8' tabindex='1'></td>
							<td colspan="2" class="2-td2-left""><input type='text' name='P_4'
								id='P_4_2' value='' size='8' tabindex='1'></td>
							<td colspan="3" class="2-td2-center""><input type='text' name='P_5'
								id='P_5_2' value='' size='8' tabindex='1'></td><input type="hidden" name="hc1"  id='hc_2' value="2" />
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">3</td>
							<td class="2-td2-left">第三年</td>
							<td class="2-td2-left"><input type='text' name='P_1'
								id='P_1_3' value='' readonly='true' size='8'  style='text-align:center' onblur ='isNum(this , 1000, 9999, null, 4, 0)' tabindex='1'></td>
							<td colspan="2" class="2-td2-left""><input type='text' name='P_2'
								id='P_2_3' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left""><input type='text' name='P_3'
								id='P_3_3' value='' size='8' tabindex='1'></td>
							<td colspan="2" class="2-td2-left""><input type='text' name='P_4'
								id='P_4_3' value='' size='8' tabindex='1'></td>
							<td colspan="3" class="2-td2-center""><input type='text' name='P_5'
								id='P_5_3' value='' size='8' tabindex='1'></td><input type="hidden" name="hc1"  id='hc_3' value="3" />
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">4</td>
							<td class="2-td2-left">第四年</td>
							<td class="2-td2-left"><input type='text' name='P_1'
								id='P_1_4' value='' readonly='true' size='8' style='text-align:center' onblur ='isNum(this , 1000, 9999, null, 4, 0)' tabindex='1'></td>
							<td colspan="2" class="2-td2-left""><input type='text' name='P_2'
								id='P_2_4' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left""><input type='text' name='P_3'
								id='P_3_4' value='' size='8' tabindex='1'></td>
							<td colspan="2" class="2-td2-left""><input type='text' name='P_4'
								id='P_4_4' value='' size='8' tabindex='1'></td>
							<td colspan="3" class="2-td2-center""><input type='text' name='P_5'
								id='P_5_4' value='' size='8' tabindex='1'></td><input type="hidden" name="hc1"  id='hc_4' value="4" />
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">5</td>
							<td class="2-td2-left">第五年</td>
							<td class="2-td2-left"><input type='text' name='P_1'
								id='P_1_5' value='' readonly='true' size='8' style='text-align:center' onblur ='isNum(this , 1000, 9999, null, 4, 0)' tabindex='1'></td>
							<td colspan="2" class="2-td2-left""><input type='text' name='P_2'
								id='P_2_5' value='' size='8' tabindex='1'></td>
							<td class="2-td2-left""><input type='text' name='P_3'
								id='P_3_5' value='' size='8' tabindex='1'></td>
							<td colspan="2" class="2-td2-left""><input type='text' name='P_4'
								id='P_4_5' value='' size='8' tabindex='1'></td>
							<td colspan="3" class="2-td2-center""><input type='text' name='P_5'
								id='P_5_5' value='' size='8' tabindex='1'></td><input type="hidden" name="hc1"  id='hc_5' value="5" />
						</tr>
						<tr>
							<td colspan= "7" class="2-td2-left"">以前年度结转在本年度税前扣除的股权投资转让损失</td>
							<td colspan="5"  class="2-td2-center""><input type='text' name='item58'
								id='item58' value='' size='15' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='1'></td>
						</tr>


					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center"><input type="image" style="cursor:hand" tabIndex="-1"
						onClick="InsertRow();return false;" accesskey="a" value="增加"
						alt="增加一行"
						onMouseOver="MM_swapImage('zj','','<%=static_contextpath%>images/qysds_zjh2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qysds_zjh1.jpg" id="zj"/>&nbsp;&nbsp;
						<a style="cursor:hand" id="sch"
						onclick="RomoveRow()"><img name="sch" value="删除"
						alt="删除选择行"
						onMouseOver="MM_swapImage('sc','','<%=static_contextpath%>images/qysds_sch2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qysds_sch1.jpg"
						id="sc"></a>&nbsp;&nbsp;
					    <a style="cursor:hand" id="previous"
						onclick="gotoPreviousTable()"><img name="xpage" value="上一张表"
						alt="填写上一张表"
						onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/shangyiye1.jpg"
						id="previoustable"> </a>&nbsp;&nbsp;
					  <a style="cursor:hand" id="next"
						onclick="gotoNextTable()"><img name="spage" value="下一张表"
						alt="填写下一张表"
						onMouseOver="MM_swapImage('nexttable','','<%=static_contextpath%>images/xaiyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/xaiyiye1.jpg" id="nexttable"> </a>&nbsp;&nbsp;
					<input type="image" style="cursor:hand" tabIndex="-1"
						onClick="doReset();return false;" accesskey="u" value="清除"
						alt="清除页面输入框信息"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" id="qingchu" />
					&nbsp;&nbsp; <input type="image" style="cursor:hand" tabIndex="-1"
						onClick="doSave();return false;" accesskey="s" value="保存" alt="保存"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" id="baocun" />
					&nbsp;&nbsp; <input type="image" style="cursor:hand" tabIndex="-1"
						onClick="doChecked();return false;" accesskey="d" value="单表校验"
						alt="表内关系校验"
						onMouseOver="MM_swapImage('jiaoyan','','../../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../../images/b-bdjyd1.jpg"
						id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doDelete();return false;" accesskey="x" value="全部删除"
						alt="删除本表所有数据，且不可恢复"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" id="shanchu" />
					&nbsp;&nbsp; <input type="image" value="返回" alt="返回到企业所得税年报主页面"
						style="cursor:hand" tabIndex="-1" onClick="doExit();return false;"
						onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/fanhui1.jpg" id="fanhui" /></div>
					</TD>
				</TR>
			</TABLE>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>

	<%@ include file="../../include/footernew.jsp"%>
</html:form>

</body>
</html>
