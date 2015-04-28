<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsjbnew.web.CzqysdsjbForm"%>
<%@ page import="java.util.HashMap"%>
<html>
<head>
<title>查帐征收企业所得税季度纳税申报表</title>
<!-- modify  20140829 -->
<html:base/>
<!-- end 20140829 -->
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
<script language=JavaScript type="text/JavaScript"
	src="../../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/function.js"></script>
</head>

<script language="JavaScript">
		<%/*初始化*/%>	
	function doInit()
	{
		//initNumText("ljje",14);
		<%
		/*数据回显*/
		CzqysdsjbForm jbForm=(CzqysdsjbForm)request.getAttribute("czqysdsjbForm");
		if (jbForm!=null && jbForm.getQysdsjbList().size()>0){
			for(int i=0;i<jbForm.getQysdsjbList().size();i++){
				HashMap map=(HashMap)jbForm.getQysdsjbList().get(i);
				int hc=Integer.parseInt((String)map.get("hc"));
				String ljje=(String)map.get("ljje");
				%>
				obj = eval("document.getElementById('<%=hc%>_1')");
				obj.value = '<%=ljje%>';
				<% 
			}
		}
		%>
		
		/*页面初始化*/
		<%
			/*具有减免资格，使第八行可用，否则不具备减免资格，则屏蔽第八行不允许编辑*/
			if(jbForm!=null ){
				if(jbForm.getJmzg()!=null && jbForm.getJmzg().equals("1")){
					//do nothing,第八行默认为可编辑		
				}else{
				%>
					document.getElementById("8_1").readOnly=true;
					document.getElementById("8_1").className='text-gray';
				<%
				}
			}
		%>

		setFoucs();
	}	
	
	<%/*响应计算机代码的回车查询*/%>
	function jsjdmQuery(){
		if(event.keyCode==13) event.keyCode = 9;
	}
	
	<%/*设置输入计算机代码时的焦点变化*/%>
	function setFoucs(){
		if(document.forms[0].nsrmc.value==""){
			window.event.returnValue=false;
			document.forms[0].jsjdm.focus();
		}else{
			document.getElementById("1_1").focus();
		}	
	}	
	
	<%/*查询*/%>
	function doQuery(){
		var jsjdm;
		jsjdm = document.forms[0].jsjdm.value;
		if(jsjdm==""){
		    alert("计算机代码不允许是空！");
		    return false;
	  	}else{
		    doSubmitForm("Query");		    
	    	return false;
	  	}
	}
	
	<%/*保存*/%>
	function doSave()
	{
		if(document.forms[0].nsrmc.value==""){
			alert("基本信息不正确,保存失败,请重新输入!");
			return false;
		}
						
		if(!saveCheck(1,null))return false;
		if(!saveCheck(2,0))return false;
		if(!saveCheck(3,0))return false;
		if(!saveCheck(4,0))return false;
		if(!saveCheck(8,0))return false;
		if(!saveCheck(9,0))return false;
		if(!saveCheck(10,0))return false;		
		
		if (!check_row_8()) {
			return false;
		}
		if(!check_row_4())return false;
		
		if(!compareDate2Save(document.forms[0].skssjsrq))return false;
				
		doSubmitForm('Save');
		
	}
	<%/*审核*/%>
	function doCheck()
	{
		doSubmitForm('Check');
	}	
	<%/*清除*/%>
	function doReset()
	{
	    if(confirm("确认是否清除当前数据？"))
	    {
		   	for(var i=1; i < 15; i++){
				obj = eval("document.getElementById('" + i+"_1')");
				if(obj.value!="*")					
				 	obj.value="0.00";				
		   	}
	    }
	}
	
	<%/*删除*/%>
	function doDelete()
	{
		if(document.forms[0].nsrmc.value==""){
				alert("基本信息不正确,删除失败,请重新输入!");
				return false;
			}
		doSubmitForm('Delete');
	}
	
	
	//如果申报月份不属于企业所得税季度征期4、7、10则提示操作人员
	function checkZQ(sbrq,ksrq,jzrq,lx){
		if(!isDate(sbrq,"v")) return;
		getStartEndDate1(sbrq,ksrq,jzrq,lx);
		var inputDate = sbrq.value;
		mon = inputDate.substring(4,6);
		if(mon!='01' && mon!='04' && mon!='07' && mon!='10'){
			alert('注意：'+inputDate+'不属于征期。');
		}
	}
	
	/**
	* Notes: 获取当前日期的上一年/月/季的起止日期。
	* Version: 0.9.00
	* Author: Guoxh
	* Parames: flag 1,Last Year;2,Last Month;默认：输出季度
	*/
	function getStartEndDate1(oInput1,oInput2,oInput3,flag){
		var date_start,date_end;
		var year,mon,days;
		var strMon;
	
		var inputDate = oInput1.value;
	
		//是否合法日期
		if(isDate(oInput1,"v")){
			year = inputDate.substring(0,4);
			mon = inputDate.substring(4,6);
			days = inputDate.substring(6,8);
	
			if(flag == 1){//Last Year
				date_start = (year-1)+"0101";
				date_end = (year-1)+"1231";
			}else if(flag == 2){//Last Month
				var date2 = new Date(year,mon-1,-1);
				days = date2.getDate()+1;
				year = date2.getYear();
				mon = date2.getMonth()+1;
	
				date_start = year+""+formatMon(mon)+"01";
				date_end = year+""+formatMon(mon)+days;
				//date_start = year+""+formatMon(mon-1)+"01";
				//date_end = year+""+formatMon(mon-1)+days;
			}else{
				//mon = parseInt(mon);
				switch(mon){
					case "01":
					case "02":
					case "03":
						date_start = (year-1)+"0101";
						date_end = (year-1)+"1231";
						break;
					case "04":
					case "05":
					case "06":
						date_start = year+"0101";
						date_end = year+"0331";
						break;
					case "07":
					case "08":
					case "09":
						date_start = year+"0101";
						date_end = year+"0630";
						break;
					case "10":
					case "11":
					case "12":
						date_start = year+"0101";
						date_end = year+"0930";
						break;
				}
			}
	
			oInput2.value = date_start;
			oInput3.value = date_end;
		}
	}
	
	<%/*判断比较税款所属日期*/%>
	function compareDate(obj){
		
		var strDate1 = document.forms[0].skssksrq.value;		
		var strYear1 = strDate1.substr(0,4);		
		var strMonth1 = strDate1.substr(4,2);
 	 	var strDay1 = strDate1.substr(6,2);
 	 	
 	 	var strDate2 = document.forms[0].skssjsrq.value;
 	 	var strYear2 = strDate2.substr(0,4);		
		var strMonth2 = strDate2.substr(4,2);
 	 	var strDay2 = strDate2.substr(6,2);
 	 	
 	 	var sksbrq = document.forms[0].sbrq.value;
 	 	var strYear3 = sksbrq.substr(0,4);		
		var strMonth3 = sksbrq.substr(4,2);
 	 	var strDay3 = sksbrq.substr(6,2); 
 	 	
	  	var objDate1 = new Date(strMonth1+'-'+strDay1+'-'+strYear1);
	  	var objDate2 = new Date(strMonth2+'-'+strDay2+'-'+strYear2);
	  	var objDate3 = new Date(strMonth3+'-'+strDay3+'-'+strYear3);
	  	
	  	if(objDate2>objDate3){
	  		alert("税款结束时间不能大于申报日期");
	  		window.event.returnValue=false;
			document.forms[0].skssjsrq.focus();
			document.forms[0].skssjsrq.select();
			return false;
	  	}
  	  	
  		if(objDate1>=objDate2){
			if(obj == document.forms[0].skssjsrq){
				alert("税款结束时间不能小于或等于税款开始时间");
			}else{
				alert("税款开始时间不能大于或等于税款结束时间");	
			}
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;
		}
		
		if(strYear1!=strYear2){
			alert("税款开始时间与税款结束时间的年份不同，税款不能跨年！");
			return false;				
		}
			
	}
	
	//判断比较税款所属日期
	function compareDate2Save(obj){
		
		var strDate1 = document.forms[0].skssksrq.value;		
		var strYear1 = strDate1.substr(0,4);		
		var strMonth1 = strDate1.substr(4,2);
 	 	var strDay1 = strDate1.substr(6,2);
 	 	
 	 	var strDate2 = document.forms[0].skssjsrq.value;
 	 	var strYear2 = strDate2.substr(0,4);		
		var strMonth2 = strDate2.substr(4,2);
 	 	var strDay2 = strDate2.substr(6,2);
 	 	
 	 	var sksbrq = document.forms[0].sbrq.value;
 	 	var strYear3 = sksbrq.substr(0,4);		
		var strMonth3 = sksbrq.substr(4,2);
 	 	var strDay3 = sksbrq.substr(6,2); 
 	 	
  	var objDate1 = new Date(strMonth1+'-'+strDay1+'-'+strYear1);
  	var objDate2 = new Date(strMonth2+'-'+strDay2+'-'+strYear2);
  	var objDate3 = new Date(strMonth3+'-'+strDay3+'-'+strYear3);
  	
  	if(objDate2>objDate3){
  		alert("税款结束时间不能大于申报日期");
  		window.event.returnValue=false;
			document.forms[0].skssjsrq.focus();
			document.forms[0].skssjsrq.select();
			return false;
  	}
  	  	
  		if(objDate1>=objDate2){
			if(obj == document.forms[0].skssjsrq){
				alert("税款结束时间不能小于或等于税款开始时间");
			}else{
				alert("税款开始时间不能大于或等于税款结束时间");	
			}
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;
		}
		
		if(strYear1!=strYear2){
			alert("税款开始时间与税款结束时间的年份不同，税款不能跨年！");
			return false;				
		}
		
		return true;
			
	}
	


	<%/*判断 、赋值函数，用于第1、2、3、4行*/%>
	function compute_Row_5(zero,row){
		
		//判断输入的数据是否符合要求
		if(!isNum(getCellObject() , zero, 9999999999999, null, 16, 2)){
			return false;			
		}
		//计算第5、6、7、11行的值
		if(document.getElementById("1_1").value==""&&document.getElementById("2_1").value==""&&
			document.getElementById("3_1").value==""&&document.getElementById("4_1").value==""){
			document.getElementById("5_1").value = "";
			document.getElementById("6_1").value = "";
			document.getElementById("7_1").value = "";
			
			//计算第11行的值
			compute_row_11();
			
			return;
		}
		
		//根据1、2、3行输入的数据判断第4行数据是否要改动
		var row_1=convertSpace2Zero(document.getElementById("1_1").value);
		var row_2=convertSpace2Zero(document.getElementById("2_1").value);
		var row_3=convertSpace2Zero(document.getElementById("3_1").value);
		var row_4=convertSpace2Zero(document.getElementById("4_1").value);		
		
		var temp3 = parseFloat(row_1)+parseFloat(row_2)-parseFloat(row_3);		
		
		if(row!="4"){
			if(temp3<0&&parseFloat(row_4)!=0){
				if(confirm("“第1行+第2行-第3行”小于0时，第4行“减：弥补以前年度亏损”应为0! 修改第4行吗？")){
					window.event.returnValue=false;
					document.getElementById("4_1").focus();
					document.getElementById("4_1").select();
					return false;				
				}else{
					var temprow = parseInt(row)+1;					
					document.getElementById(temprow+'_1').focus();
					document.getElementById(temprow+'_1').select();
				}
			}
			if(temp3>=0&&parseFloat(row_4)>parseFloat(temp3)){
				if(confirm("当“第1行+第2行-第3行”大于等于0时，第4行“减：弥补以前年度亏损”应小于等于第1行+第2行-第3行，且第4行应大于等于0! 修改第4行吗？")){
					window.event.returnValue=false;
					document.getElementById("4_1").focus();
					document.getElementById("4_1").select();
					return false;				
				}else{
					var temprow = parseInt(row)+1;					
					document.getElementById(temprow+'_1').focus();
					document.getElementById(temprow+'_1').select();
				}
			}
		}
		
		//检验第4行输入的值是否符合要求
		if(row=="4"){
			if(!check_row_4())return false;
		}
		
		//计算并格式化第5行的值
		var obj=document.getElementById("5_1");
		var temp4 = parseFloat(row_1)+parseFloat(row_2)-parseFloat(row_3)-parseFloat(row_4);
		//obj.value=parseFloat(temp4);
		if( temp4 < 0 )
			obj.value = 0; 
		 else {
			obj.value=parseFloat(temp4);
		}
		formate(obj);	
		
		
		
		
		//计算第6行的值
		compute_row_6();
		//计算第7行的值
		compute_row_7();
		//计算第11行的值
		compute_row_11();
	}	
	
	
	<%/*在输入第4行，以及保存时对该值进行检验*/%>	
	function check_row_4(){
		
		var row_1=convertSpace2Zero(document.getElementById("1_1").value);
		var row_2=convertSpace2Zero(document.getElementById("2_1").value);
		var row_3=convertSpace2Zero(document.getElementById("3_1").value);
		var row_4=convertSpace2Zero(document.getElementById("4_1").value);
		var temp3 = parseFloat(row_1)+parseFloat(row_2)-parseFloat(row_3);
		
		if(temp3<0&&parseFloat(row_4)!=0){
			alert("“第1行+第2行-第3行”小于0时，第4行“减：弥补以前年度亏损”应为0!");
			window.event.returnValue=false;
			document.getElementById("4_1").focus();
			document.getElementById("4_1").select();
			return false;				
		}
		if(temp3>=0&&parseFloat(row_4)>parseFloat(temp3)){
			alert("当“第1行+第2行-第3行”大于等于0时，第4行“减：弥补以前年度亏损”应小于等于第1行+第2行-第3行，且第4行应大于等于0!");
			window.event.returnValue=false;
			document.getElementById("4_1").focus();
			document.getElementById("4_1").select();
			return false;
		}
		return true;	
	}
			
	<%/*计算第6行的函数*/%>
	function compute_row_6(){
		if(document.getElementById("5_1").value<=30000.00&&document.getElementById("5_1").value!="")
			document.getElementById("6_1").value=0.18;
		else if(document.getElementById("5_1").value>30000.00&&document.getElementById("5_1").value<=100000.00)
			document.getElementById("6_1").value=0.27;
		else document.getElementById("6_1").value=0.33;		
		
				
		var qyzslx = document.forms[0].qyzslx.value;
		
		/*
		if(qyzslx == 5 ||qyzslx == 1){
		//高新技术企业
			document.getElementById("6_1").value=0.15;	
		}
		*/
		
		
	}
		
	<%/*计算第7行的函数*/%>
	function compute_row_7(){
		var obj1=document.getElementById("5_1");
		var obj2=document.getElementById("6_1");
		var obj3=document.getElementById("7_1");
		obj3.value = Math.round((parseFloat(obj1.value)*parseFloat(obj2.value))*100)/100;
		if(obj3.value<0){
			obj3.value=0;
		}
		formate(obj3);		
	}
	
	<%/*校验第8行的值，用于其他函数调用以及保存操作时*/%>	
	function check_row_8(){
		if(document.getElementById("5_1").value ==""||document.getElementById("7_1").value =="")
		{
			return true;
		}
		
		if( document.getElementById("5_1").value == 0 ){
			if( document.getElementById("8_1").value != 0 ){
				alert("根据公式当H5=0时H8=0，‘应纳税所得额’值为0时，'减免所得税额'必须为0");
				window.event.returnValue = false;
				document.getElementById("8_1").focus();
				document.getElementById("8_1").select();
				return false;
			}
		} else {
			
			if( document.getElementById("8_1").value > parseFloat(document.getElementById("7_1").value)){
				
				alert("根据公式H8≤H7，'减免所得税额'的值必须小于等于'应纳所得税额'");
				window.event.returnValue = false;
				document.getElementById("8_1").focus();
				document.getElementById("8_1").select();
				return false;
			}
		}
			return true;
	}
	
	<%/*校验第9行的值，用于其他函数调用*/%>
	function check_row_9(){
		if(document.getElementById("9_1").value>1){
			alert("汇总纳税成员企业就地预缴比例应小于等于1，大于等于0");
			window.event.returnValue = false;
			document.getElementById("9_1").focus();
			document.getElementById("9_1").select();
			return false;		
		}
		return true;
	}
	
	<%/*计算11行的函数，用第8、9、10行输入时，以及其他函数调用*/%>
	function compute_row_11(zero,row){
		
		//判断输入的数据是否符合要求
		if(!isNum(getCellObject() , zero, 9999999999999, null, 16, 2)){
			return false;			
		}
		//校验第8行的值
		if(row==8){
			if (!check_row_8()) {
				return false;
			}			
		}
		//校验第9行的值
		if(row==9){
			if (!check_row_9()) {
				return false;
			}
		}
		
		//计算11行的值
		if(document.getElementById("7_1").value==""&&document.getElementById("8_1").value==""&&
			document.getElementById("10_1").value==""){
			document.getElementById("11_1").value = "";
			return;
		}
		
		var row_7 = convertSpace2Zero(document.getElementById("7_1").value);
		var row_8 = convertSpace2Zero(document.getElementById("8_1").value);
		var row_9 = convertSpace2Zero(document.getElementById("9_1").value);
		var row_10 = convertSpace2Zero(document.getElementById("10_1").value);					
				
		var ybdsdseJdns = parseFloat(row_7) - parseFloat(row_8)- parseFloat(row_10);
		var ybdsdseHzns = (parseFloat(row_7) - parseFloat(row_8))* parseFloat(row_9) - parseFloat(row_10);
		
		if(document.getElementById("9_1").value == 0||document.getElementById("9_1").value == ""){
			//if( ybdsdseJdns < 0 )
			//	document.getElementById("11_1").value = 0; 
			// else {
				document.getElementById("11_1").value = Math.round(ybdsdseJdns*100)/100;
			//}
		} else {
			//if( ybdsdseHzns < 0 ){
			//	 document.getElementById("11_1").value = 0;
			//} else {
				 document.getElementById("11_1").value = Math.round(ybdsdseHzns*100)/100 
			//}			
		}
		formate(document.getElementById("11_1"));
	}
	
	<%/*将空格转换为零，用于其他函数调用*/%>
	function convertSpace2Zero(inputValue){
		return inputValue==""?"0":inputValue;
	}
	
	<%/*保存时对第1、2、3、4、8、9、10的数据进行校验*/%>
	function saveCheck(row,zero){
		
		if(!isNum(document.getElementById(row+'_1'), zero, 9999999999999, null, 16, 2)){
			return false;			
		}
				
		if(row==9){
			if (!check_row_9()) {
				return false;
			}
		}
		return true;	
	}
	

</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="doInit()">
<%@include file="../include/header.jsp"%>
<br>

<html:form action="/webapp/smsb/qysds/czqysdsjbAction.do">

	<html:hidden property="actionType" />
	<html:hidden property="nsrsbh" />
	<html:hidden property="zgswjgzzjgmc" />
	<html:hidden property="swjgzzjgdm" />
	<html:hidden property="nsrmc" />
	<html:hidden property="lxdh" />
	<html:hidden property="jydz" />
	<html:hidden property="sknd" />
	<html:hidden property="qh" />
	<html:hidden property="bbqlx" />
	<html:hidden property="qxdm" />
	<html:hidden property="swjsjdm" />
	<html:hidden property="qyzslx" />
	<html:hidden property="cyl" />
	<html:hidden property="dezsse" />
	<html:hidden property="jmzg" />
	<html:hidden property="ybjmsl" />
	<html:hidden property="sysl" />

	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="jsjdmQuery()">
		<tr>
			<td class="1-td1" colspan="7">查帐征收企业所得税季度纳税申报表</td>
		</tr>

		<tr>
			<td class="1-td2" colspan="7"><br>
			<table cellspacing="0" class="table-99">

				<tr class="black9">
					<td align="center" nowrap>
					<div align="left">申报日期 <html:text property="sbrq" size="11"
						maxlength="8" readonly='true' style='text-align:right'
						onchange="checkZQ(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,3)" /></div>
					</td>
					<td align="left" nowrap>税款所属日期： <html:text property="skssksrq"
						size="11" maxlength="8"
						onchange="isDate(this,false);compareDate(this)" /> 至 <html:text
						property="skssjsrq" size="11" maxlength="8"
						onchange="isDate(this,false);compareDate(this)" /></td>
				</tr>
				<tr class="black9">
					<td align="left" nowrap>
					<div align="left">纳税人识别号：&nbsp; <bean:write name="czqysdsjbForm"
						property="nsrsbh" scope="request" filter="true" /></div>
					</td>

					<td align="right" nowrap>
					<div align="right">金额单位：元（列至角分）</div>
					</td>
				</tr>
			</table>
			<table class="table-99" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="15%" nowrap class="2-td2-t-left">计算机代码&nbsp;</td>
					<td width="15%" nowrap class="2-td2-t-left" align=left><html:text
						property="jsjdm" size="8" maxlength="8" onchange="doQuery()" /></td>
					<td width="15%" nowrap class="2-td2-t-left">主管税务机关&nbsp;</td>
					<td width="40%" nowrap class="2-td2-t-center" align=left>&nbsp; <bean:write
						name="czqysdsjbForm" property="zgswjgzzjgmc" scope="request"
						filter="true" /></td>
				</tr>
				<TR>
					<td width="15%" nowrap class="2-td2-left">纳税人名称&nbsp;</td>
					<td width="30%" nowrap class="2-td2-left" align=left>&nbsp; <bean:write
						name="czqysdsjbForm" property="nsrmc" scope="request"
						filter="true" /></td>
					<td width="10%" nowrap class="2-td2-left">联系电话&nbsp;</td>

					<td width="15%" nowrap class="2-td2-center">&nbsp;<bean:write
						name="czqysdsjbForm" property="lxdh" scope="request" filter="true" /></td>
				</tr>
				<tr>
					<td nowrap class="2-td2-left">生产经营地址&nbsp;</td>
					<td colspan=3 nowrap class="2-td2-center" align=left>&nbsp;<bean:write
						name="czqysdsjbForm" property="jydz" scope="request" filter="true" />
					</td>
				</tr>
			</table>
			<br>

			<TABLE class="table-99" align="center">
				<TR>
					<TD>
					<div id="Layer2" style="position:static;">
					<table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
						<tr>
							<!--<td class="2-td1-left" nowrap>行次</td>
				   									<td class="2-td1-left" nowrap>项目</td><!-->
							<td class="2-td1-left" nowrap>行次</td>
							<td class="2-td1-left" nowrap>项目</td>
							<td class="2-td1-center" nowrap>累计金额</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>1 <input type="hidden" name="hc"
								value="1" id="hc_1"></td>
							<td class="2-td2-left" nowrap>利润总额</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='1_1' style='text-align:right' value='0.00' size='13'
								onblur="formate(this)" onchange="compute_Row_5(null,'1')"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>2 <input type="hidden" name="hc"
								value="2" id="hc_2"></td>
							<td class="2-td2-left" nowrap>加：纳税调整增加额</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='2_1' style='text-align:right' value='0.00' size='13'
								onblur="formate(this)" onchange="compute_Row_5('0','2')"></td>
						</tr>

						<tr>
							<td class="2-td2-left" nowrap>3 <input type="hidden" name="hc"
								value="3" id="hc_3"></td>
							<td class="2-td2-left" nowrap>减：纳税调整减少额</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='3_1' style='text-align:right' value='0.00' size='13'
								onblur="formate(this)" onchange="compute_Row_5('0','3')"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>4 <input type="hidden" name="hc"
								value="4" id="hc_4"></td>
							<td class="2-td2-left" nowrap>减：弥补以前年度亏损</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='4_1' style='text-align:right' value='0.00' size='13'
								onblur="formate(this)" onchange="compute_Row_5('0','4')"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>5 <input type="hidden" name="hc"
								value="5" id="hc_5"></td>
							<td class="2-td2-left" nowrap>应纳税所得额（1+2-3-4）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='5_1' style='text-align:right' value='0.00' size='13'
								readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>6 <input type="hidden" name="hc"
								value="6" id="hc_6"></td>
							<td class="2-td2-left" nowrap>适用税率</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='6_1' style='text-align:right' value='0.00' size='13'
								readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>7 <input type="hidden" name="hc"
								value="7" id="hc_7"></td>
							<td class="2-td2-left" nowrap>应纳所得税额（5×6 ）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='7_1' style='text-align:right' value='0.00' size='13'
								readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>8 <input type="hidden" name="hc"
								value="8" id="hc_8"></td>
							<td class="2-td2-left" nowrap>减免所得税额</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='8_1' style='text-align:right' value='0.00' size='13'
								onblur="formate(this)" onchange="compute_row_11('0','8')"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>9 <input type="hidden" name="hc"
								value="9" id="hc_9"></td>
							<td class="2-td2-left" nowrap>汇总纳税成员企业就地预缴比例</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='9_1' style='text-align:right' value='0.00' size='13'
								onblur="formate(this)" onchange="compute_row_11('0','9')"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>10 <input type="hidden" name="hc"
								value="10" id="hc_10"></td>
							<td class="2-td2-left" nowrap>实际已预缴的所得税额</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='10_1' style='text-align:right' value='0.00' size='13'
								onblur="formate(this)" onchange="compute_row_11('0')"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>11 <input type="hidden" name="hc"
								value="11" id="hc_11"></td>
							<td class="2-td2-left" nowrap>应补（退）的所得税额[（7-8-10 ）或 （7-8）×9-10]</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='11_1' style='text-align:right' value='0.00' size='13'
								readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>12 <input type="hidden" name="hc"
								value="12" id="hc_12"></td>
							<td class="2-td2-left" nowrap>上一年度实际缴纳的企业所得税额</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='12_1' value='*' size='1' readonly='true'
								class='text-noborder' style="text-align:center"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>13 <input type="hidden" name="hc"
								value="13" id="hc_13"></td>
							<td class="2-td2-left" nowrap>本季（月）应预缴所得税额（12行÷4或12行÷12）</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='13_1' value='*' size='1' readonly='true'
								class='text-noborder' style="text-align:center"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>14 <input type="hidden" name="hc"
								value="14" id="hc_14"></td>
							<td class="2-td2-left" nowrap>本年实际已预缴的所得税额</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'
								id='14_1' value='*' size='1' readonly='true'
								class='text-noborder' style="text-align:center"></td>
						</tr>

					</table>
					</div>
					</TD>
				</TR>
				<BR>
				<TR class="black9">
					<TD>
					<div align="center"><input type="image" accesskey="q"
						onClick="doQuery();return false;"
						onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)"
						onMouseOut="MM_swapImgRestore()" value="查询"
						src="<%=static_contextpath%>images/cx-q1.jpg " width="79"
						height="22" id="chaxun" style="cursor:hand">
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="u"
						style="cursor:hand" onClick="doReset();return false;"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" name="Image13"
						width="79" height="22" border="0" id='qingchu'>
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="s"
						style="cursor:hand" onClick="doSave();return false;"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" name="Image13"
						width="79" height="22" border="0" id='baocun'>

					&nbsp;&nbsp;&nbsp;&nbsp;<input type="image" accesskey="x"
						style="cursor:hand" onClick="doDelete();return false;"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" name="Image13"
						width="79" height="22" border="0" id='shanchu'>
					&nbsp;&nbsp;&nbsp;&nbsp;<input type="image" accesskey="c"
						onClick="tuichu();return false;"
						onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" value="退出" id="tc1"
						src="<%=static_contextpath%>images/tc-c1.jpg" width="79"
						height="22" style="cursor:hand"></div>
					<BR>
					</TD>
				</TR>
			</TABLE>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>

	<%@ include file="../include/footer.jsp"%>
</html:form>
</body>
</html>
