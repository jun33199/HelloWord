<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.hdzsssyhmx.web.HdzsssyhmxForm"%>
<%@ page import="java.util.HashMap"%>

<html>
<head>
<title>税收优惠明细表</title>
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

<script language="JavaScript">
		<%/*初始化*/%>	
		function doInit()
		{
		
		//初始化文本框onChange事件处理
		initNumText("je",47);
		var row_45=document.getElementById("45_1");
		row_45.style.textAlign ="right";
		row_45.onkeyup = function(){formateSign();}
		row_45.onblur = function(){return isNum(getCellObject() , null, 9999999999999, null, 16, 0);}
		<%
		HdzsssyhmxForm nbForm=(HdzsssyhmxForm)request.getAttribute("hdzsssyhmxForm2008");
		String wlrdbs = nbForm.getWlrdbs();
			if (nbForm!=null && nbForm.getDataList().size()>0){
				for(int i=0;i<nbForm.getDataList().size();i++){
					HashMap map=(HashMap)nbForm.getDataList().get(i);
					int hc=Integer.parseInt((String)map.get("hc"));
					String je=(String)map.get("je");
					%>
					obj = eval("document.getElementById('<%=hc%>_1')");
					obj.value = '<%=je%>';
					<% 
				}
			}
			%>
      document.all("34_1").focus();
	    if(document.all("47_1").value == "01"){
	    	document.forms[0].hy47_1.checked = true;
	    	document.forms[0].hy47_2.checked = false;
	    }
	    if(document.all("47_1").value == "02"){
	    	document.forms[0].hy47_1.checked = false;
	    	document.forms[0].hy47_2.checked = true;
	    }
	    if(document.all("47_1").value != "01" && document.all("47_1").value != "02"){
	    	document.forms[0].hy47_1.checked = false;
	    	document.forms[0].hy47_2.checked = false;
	    }
	    
	    var obj= document.getElementById('33_1');
		  obj.readOnly=true;
		  obj.className='text-gray';
		  
	    if(getValues("zbh3")==0||getValues("zbh3")>300000){
	    	document.all("38_1").focus();
	      var obj= document.getElementById('34_1');
		  	obj.readOnly=true;
		  	obj.className='text-gray';
		  	obj.value="";
		  	compute_Row_33init();
		  	var obj= document.getElementById('45_1');
		  	obj.readOnly=true;
		  	obj.className='text-gray';
		  	obj.value="";
		  	var obj= document.getElementById('46_1');
		  	obj.readOnly=true;
		  	obj.className='text-gray';
		  	obj.value="";
		  	var obj= document.getElementById('47_1');
		  	obj.value="";
		  	document.forms[0].hy47_1.checked = false;
	    	document.forms[0].hy47_2.checked = false;
	    	document.forms[0].hy47_1.disabled = true;
	    	document.forms[0].hy47_2.disabled = true;
		  }
		}		
		
		<%/*保存*/%>
		function doSave()
		{

		if(!doCheckmethod()){
				return;
			}
			doSubmitForm('Save');
		}
    
    function getValues(str){
		var strv = document.getElementById(str).value;
		if(strv=="")
		   return 0;
		else
			 return parseFloat(strv);  
	}
	
    function doCheck()
		{
			if(doCheckmethod()){
        alert("审核通过！");
			}
		}
		
		<%/*表内关系校验*/%>
		function doCheckmethod()
		{

			var zbh3 = getValues("zbh3");
			var info = "";
			if(getValues('34_1')!=0&&getValues('34_1')!=Math.round(zbh3*0.05*100)/100){
				alert("第34行只能为0或主表第3行的5% 主表第3行=" + zbh3 + " 第3行×5%=" + Math.round(zbh3*0.05*100)/100);
				return false;
			}
				
		//33行=34行＋35行＋36行＋37行＋38行
		var hj5 = getValues('34_1')+getValues('35_1')+getValues('36_1')+getValues('37_1')+getValues('38_1');
		if(getValues('33_1')!=Math.round(hj5*100)/100)
		{
			info = "不符合公式：33行=34行＋35行＋36行＋37行＋38行，请重新输入！\n";
			alert(info);
			formate(document.getElementById('33_1'));
			return false;
		}
		//第47行为必填项，既：不允许此三行数据值为0或空
		if(getValues('34_1')>0&&zbh3>0&&zbh3<=300000){
	  	if(getValues('47_1')==0)
	  	{
	  		info = "第47行为必填项，必须选择所属行业\n";
	  		alert(info);
	  		formate(document.getElementById('47_1'));
	  		return false;
	  	}
	  }

	  if(getValues('45_1')<0)
	  {
	  	info = "第45行不能为负数！\n";
	  	alert(info);
	  	//formate(document.getElementById('46_1'));
	  	document.all("45_1").focus();
	  	return false;
	  }

		if(getValues('34_1')>0&&zbh3>0&&zbh3<=300000){
	  	if(getValues('45_1')<=0||getValues('46_1')<=0)
	  	{
	  		info = "第45、46行为必填项，且必须大于0！\n";
	  		alert(info);
	  		//formate(document.getElementById('46_1'));
	  		document.all("45_1").focus();
	  		return false;
	  	}
	  }
		
		//当本表第34行>0，并且主表第3行>0且<=300000时
		if(getValues('34_1')>0&&zbh3>0&&zbh3<=300000){
			//如果本表第47行=工业企业，则本表第45行>0且<=100，同时本表第46行>0且<=30000000
		if(getValues('47_1')==1){
			if(!(getValues('45_1')>0&&getValues('45_1')<=100))
			{
		  	info = "公式 行34>0 并且 行47 所属行业为工业企业时: 行45 >0且<=100 而表中企业从业人数 行45=" + getValues('45_1') +  " 请修改\n";
		  	alert(info);
		  	//formate(document.getElementById('46_1'));
		  	document.all("45_1").focus();
		  	return false;
		  }
		  if(!(getValues('46_1')>0&&getValues('46_1')<=30000000))
			{
		  	info = "公式 行34>0 并且 行47 所属行业为工业企业时: 行46 >0且<=30000000 而表中资产总额 行46=" + getValues('46_1') +  " 请修改\n";
		  	alert(info);
		  	formate(document.getElementById('46_1'));
		  	document.all("46_1").focus();
		  	return false;
		  }
		}
		if(getValues('47_1')==2){
		  if(!(getValues('45_1')>0&&getValues('45_1')<=80))
			{
		  	info = "公式 H34>0 并且 行47 所属行业为其他企业时:行45 >0且<=80 而表中企业从业人数 行45=" + getValues('45_1') +  " 请修改\n";
		  	alert(info);
		  	//formate(document.getElementById('46_1'));
		  	document.all("45_1").focus();
		  	return false;
		  }
		  if(!(getValues('46_1')>0&&getValues('46_1')<=10000000))
			{
		  	info = "公式 H34>0 并且 行47 所属行业为其他企业时: 行46 >0且<=10000000 而表中资产总额 行46=" + getValues('46_1') +  " 请修改\n";
		  	alert(info);
		  	formate(document.getElementById('46_1'));
		  	document.all("46_1").focus();
		  	return false;
		  }
		}
		}

		if(info!=""){
				return false;
			}
			else{
				return true;
			}
		}
		function compute_Row_33init(){
			var ybsds=Math.round((getValues('34_1')+getValues('35_1')+getValues('36_1')+getValues('37_1')+getValues('38_1'))*100)/100;
			document.getElementById('33_1').value=parseFloat(ybsds);
			formate(document.getElementById('33_1'));
		}
		function compute_Row_33(obj){
			if(!numberyz(obj))
			  return;
			checkNumInput(obj);
			var ybsds=Math.round((getValues('34_1')+getValues('35_1')+getValues('36_1')+getValues('37_1')+getValues('38_1'))*100)/100;
			document.getElementById('33_1').value=parseFloat(ybsds);
			formate(document.getElementById('33_1'));
		}
		
		//数值验证
		function numberyz(obj){
			var str=obj.value.replace(/\s+/gm,'');
			if(str.length!=obj.value.length){
				return false;
			}
			if(!isNumber(obj.value)){
	    	return false;
	    }
	    var temp = obj.value;
	    if(temp.indexOf(".")!=-1){
	      var len=temp.indexOf(".");
	      var cz = temp.length-len;
        if(cz<2||cz>3){
        	return false;
        }
      }
	    return true;
	  }
		
		<%/*清除*/%>
		function doReset()
		{
			if(confirm("确认是否清除当前数据？"))
			{
		   		for(var i=1; i < 48; i++){
					  obj = eval("document.getElementById('" + i+"_1')");
					  obj.value = "";
				  }
		   		document.forms[0].hy47_1.checked = false;
	 	      document.forms[0].hy47_2.checked = false;
			}
		}

      function checkNumInput(obj)

{

                   //判断输入的数据是否符合要求

                   if(!isNum(getCellObject() , null, null, null, null, 2)){

                            return false;                         

                   }

                   //格式化数据

                   formate(obj);

}

 

//验证数据的合法性

function formate(obj){

 

         if(obj.value==""||obj.value==null){

                   obj.value="0.00";       

         }else{

                   var temp = trim(obj.value+"");

                   if(temp.indexOf(".")!=-1){

                            var len=temp.indexOf(".")+1;

                            if(temp.length-len==1)

                            temp = temp+"0";

                            var zs=trim(temp.substring(0,temp.indexOf(".")));

                            if(zs==""){

                                     temp="0"+temp;

                            }

                   }else{

                            temp=temp+".00";

                   }

                   obj.value=temp;        

                   formateNum(obj);

         }                 

}

 

 function formateNum(obj){

         var tempNum=obj.value;

         var num=trim(tempNum.substring(0,tempNum.indexOf(".")));

         if(num.length>1){

                   num=num.substring(0,num.length-1);

                   var i;

                   for(i=0;i<num.length;i++){

                            var itemp=num.substring(i,i+1);

                            if(itemp!="0"){

                                     break;

                            }

                   }

                   tempNum=tempNum.substring(i,tempNum.length);

                   obj.value=tempNum;

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
		
		<%/*返回主表*/%>
		function doReturn()
		{
			doSubmitForm('Return');
		}
		
		<%/*根据选择设置单选框选择情况*/%>
		function changeSelect()
	  {
	  	if(document.forms[0].hy47_1.checked == true)
	  	{
	  		//选择为独立纳税的，清除汇总纳税方式子项选择，同时将这些子项置位不可编辑
	  		document.all("47_1").value = "01";
	  	}
	  	else
	  	{
	  		document.all("47_1").value = "02";
	  	}
	  }
	  /**
       * 格式化输入数据以0开头
       *
       * @param 文本框
       */
       function formateNum_a(obj){
       var tempNum=obj.value;
       if(tempNum.indexOf(".")!=-1)
          return;
       	var num=tempNum;
       	if(num.length>1){
       		num=num.substring(0,num.length-1);
       		var i;
       		for(i=0;i<num.length;i++){
       			var itemp=num.substring(i,i+1);
       			if(itemp!="0"){
       				break;
       			}
       		}
       		tempNum=tempNum.substring(i,tempNum.length);
       		obj.value=tempNum;
       	}
       
       }
		
	</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInit()">
<%@include file="../../include/header.jsp"%>
<br>

<html:form action="/webapp/smsb/qysds/2008/hdzsssyhmxAction2008">
	<html:hidden property="jsjdm" />
	<html:hidden property="sbrq" />
		
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
	<html:hidden property="sysl" />
	<html:hidden property="zsfs"/>
	<html:hidden property="zbh3"/>
		<% if("no".equals(null)){ %>
<font color=red>您尚未符合小型微利企业政策。请确认您所填写的减免税金额。</font>
    <% } %>
	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">税收优惠明细表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;申报日期:<bean:write
				name="hdzsssyhmxForm2008" property="sbrq" scope="request" filter="true" />
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
				name="hdzsssyhmxForm2008" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;纳税人名称：<bean:write
				name="hdzsssyhmxForm2008" property="nsrmc" scope="request" filter="true" />&nbsp;
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
					<div id="Layer2" style="position:static;">
					<table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
						<tr>
							<!--<td class="2-td1-left" nowrap>行次</td>
									   	<td class="2-td1-left" nowrap>项目</td><!-->
							<td class="2-td1-left" nowrap>行次</td>
							<td class="2-td1-left" nowrap>项目</td>
							<td class="2-td1-center" nowrap>金额</td>
							<td class="2-td1-left" nowrap>行次</td>
							<td class="2-td1-left" nowrap>项目</td>
							<td class="2-td1-center" nowrap>金额</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>1 <input type="hidden" name="hc"
								value="1" id="hc_1"></td>
							<td class="2-td2-left-qysds1" nowrap>一、免税收入（2＋3＋4＋5）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='1_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>25 <input type="hidden" name="hc"
								value="25" id="hc_25"></td>
							<td class="2-td2-left-qysds1" nowrap>   （二）减税所得（26＋27＋28）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='25_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>2 <input type="hidden" name="hc"
								value="2" id="hc_2"></td>
							<td class="2-td2-left-qysds1" nowrap>    1、国债利息收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='2_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>26 <input type="hidden" name="hc"
								value="26" id="hc_26"></td>
							<td class="2-td2-left-qysds1" nowrap>      1、花卉、茶以及其他饮料作物和香料作物的种植</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='26_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>

						<tr>
							<td class="2-td2-left" nowrap>3 <input type="hidden" name="hc"
								value="3" id="hc_3"></td>
							<td class="2-td2-left-qysds1" nowrap>    2、符合条件的居民企业之间的股息、红利等权益性投资收益</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='3_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>27 <input type="hidden" name="hc"
								value="27" id="hc_27"></td>
							<td class="2-td2-left-qysds1" nowrap>      2、海水养殖、内陆养殖</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='27_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>4 <input type="hidden" name="hc"
								value="4" id="hc_4"></td>
							<td class="2-td2-left-qysds1" nowrap>    3、符合条件的非营利组织的收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='4_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>28 <input type="hidden" name="hc"
								value="28" id="hc_28"></td>
							<td class="2-td2-left-qysds1" nowrap>      3、其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='28_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>5 <input type="hidden" name="hc"
								value="5" id="hc_5"></td>
							<td class="2-td2-left-qysds1" nowrap>    4、其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='5_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>29 
							  <input type="hidden" name="hc"
								value="29" id="hc_29"></td>
							<td class="2-td2-left-qysds1">   （三）从事国家重点扶持的公共基础设施项目投资经营的所得</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='29_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>6 <input type="hidden" name="hc"
								value="6" id="hc_6"></td>
							<td class="2-td2-left-qysds1" nowrap>二、减计收入（7＋8）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='6_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>30 
							  <input type="hidden" name="hc"
								value="30" id="hc_30"></td>
							<td class="2-td2-left-qysds1" nowrap>   （四）从事符合条件的环境保护、节能节水项目的所得</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='30_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>7 <input type="hidden" name="hc"
								value="7" id="hc_7"></td>
							<td class="2-td2-left-qysds1">    1、企业综合利用资源,生产符合国家产业政策规定的产品所取得的收入</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='7_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>31 
							  <input type="hidden" name="hc"
								value="31" id="hc_31"></td>
							<td class="2-td2-left-qysds1" nowrap>   （五）符合条件的技术转让所得</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='31_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>8 <input type="hidden" name="hc"
								value="8" id="hc_8"></td>
							<td class="2-td2-left-qysds1" nowrap>    2、其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='8_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>32 
							  <input type="hidden" name="hc"
								value="32" id="hc_32"></td>
							<td class="2-td2-left-qysds1" nowrap>   （六）其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='32_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>9 <input type="hidden" name="hc"
								value="9" id="hc_9"></td>
							<td class="2-td2-left-qysds1" nowrap>三、加计扣除额合计（10＋11＋12＋13）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='9_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>33 <input type="hidden" name="hc"
								value="33" id="hc_33"></td>
							<td class="2-td2-left-qysds1" nowrap>五、减免税合计（34＋35＋36＋37＋38）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='33_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>10 <input type="hidden" name="hc"
								value="10" id="hc_10"></td>
							<td class="2-td2-left-qysds1" nowrap>    1、开发新技术、新产品、新工艺发生的研究开发费用</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='10_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>34 <input type="hidden" name="hc"
								value="34" id="hc_34"></td>
							<td class="2-td2-left-qysds1" nowrap>   （一）符合条件的小型微利企业</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='34_1' onchange="compute_Row_33(this)" value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>11 <input type="hidden" name="hc"
								value="11" id="hc_11"></td>
							<td class="2-td2-left-qysds1" nowrap>    2、安置残疾人员所支付的工资</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='11_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>35 <input type="hidden" name="hc"
								value="35" id="hc_35"></td>
							<td class="2-td2-left-qysds1" nowrap>   （二）国家需要重点扶持的高新技术企业</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='35_1' style='text-align:right' readonly='true' class="text-gray" onchange="compute_Row_33(this)" value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>12 <input type="hidden" name="hc"
								value="12" id="hc_12"></td>
							<td class="2-td2-left-qysds1" nowrap>    3、国家鼓励安置的其他就业人员支付的工资</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='12_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>36 
							  <input type="hidden" name="hc"
								value="36" id="hc_36"></td>
							<td class="2-td2-left-qysds1">   （三）民族自治地方的企业应缴纳的企业所得税中属于地方分享的部分</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='36_1' style='text-align:right' readonly='true' class="text-gray" onchange="compute_Row_33(this)" value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>13 <input type="hidden" name="hc"
								value="13" id="hc_13"></td>
							<td class="2-td2-left-qysds1" nowrap>    4、其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='13_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>37 
							  <input type="hidden" name="hc"
								value="37" id="hc_37"></td>
							<td class="2-td2-left-qysds1" nowrap>   （四）过渡期税收优惠</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='37_1' style='text-align:right' readonly='true' class="text-gray" onchange="compute_Row_33(this)" value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>14 <input type="hidden" name="hc"
								value="14" id="hc_14"></td>
							<td class="2-td2-left-qysds1" nowrap>四、减免所得额合计（15＋25＋29＋30＋31＋32）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='14_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>38 
							  <input type="hidden" name="hc"
								value="38" id="hc_38"></td>
							<td class="2-td2-left-qysds1" nowrap>   （五）其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='38_1' onchange="compute_Row_33(this)" value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>15 <input type="hidden" name="hc"
								value="15" id="hc_15"></td>
							<td class="2-td2-left-qysds1" nowrap>   （一）免税所得（16＋17＋…＋24）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='15_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>39 
							  <input type="hidden" name="hc"
								value="39" id="hc_39"></td>
							<td class="2-td2-left-qysds1" nowrap>六、创业投资企业抵扣的应纳税所得额</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='39_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>16 <input type="hidden" name="hc"
								value="16" id="hc_16"></td>
							<td class="2-td2-left-qysds1">      1、蔬菜、谷物、薯类、油料、豆类、棉花、麻类、糖料、水果、坚果的种植</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='16_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>40 
							  <input type="hidden" name="hc"
								value="40" id="hc_40"></td>
							<td class="2-td2-left-qysds1" nowrap>七、抵免所得税额合计（41＋42＋43＋44）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='40_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>17 <input type="hidden" name="hc"
								value="17" id="hc_17"></td>
							<td class="2-td2-left-qysds1" nowrap>      2、农作物新品种的选育</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='17_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>41 
							  <input type="hidden" name="hc"
								value="41" id="hc_41"></td>
							<td class="2-td2-left-qysds1" nowrap>   （一）企业购置用于环境保护专用设备的投资额抵免的税额</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='41_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>18 <input type="hidden" name="hc"
								value="18" id="hc_18"></td>
							<td class="2-td2-left-qysds1" nowrap>      3、中药材的种植</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='18_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>42 
							  <input type="hidden" name="hc"
								value="42" id="hc_42"></td>
							<td class="2-td2-left-qysds1" nowrap>   （二）企业购置用于节能节水专用设备的投资额抵免的税额</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='42_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>19 <input type="hidden" name="hc"
								value="19" id="hc_19"></td>
							<td class="2-td2-left-qysds1" nowrap>      4、林木的培育和种植</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='19_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>43 
							  <input type="hidden" name="hc"
								value="43" id="hc_43"></td>
							<td class="2-td2-left-qysds1" nowrap>   （三）企业购置用于安全生产专用设备的投资额抵免的税额</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='43_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>20 <input type="hidden" name="hc"
								value="20" id="hc_20"></td>
							<td class="2-td2-left-qysds1" nowrap>      5、牲畜、家禽的饲养</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='20_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>44 <input type="hidden" name="hc"
								value="44" id="hc_44"></td>
							<td class="2-td2-left-qysds1" nowrap>   （四）其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='44_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>21 <input type="hidden" name="hc"
								value="21" id="hc_21"></td>
							<td class="2-td2-left-qysds1" nowrap>      6、林产品的采集</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='21_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>45 <input type="hidden" name="hc"
								value="45" id="hc_45"></td>
							<td class="2-td2-left-qysds1" nowrap>企业从业人数（全年平均人数）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='45_1' onchange="formateNum_a(this)" value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>22 <input type="hidden" name="hc"
								value="22" id="hc_22"></td>
							<td class="2-td2-left-qysds1">      7、灌溉、农产品初加工、兽医、农技推广、农机作业和维修等农、林、牧、渔服务业项目</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='22_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>46 <input type="hidden" name="hc"
								value="46" id="hc_46"></td>
							<td class="2-td2-left-qysds1" nowrap>资产总额（全年平均数）</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='46_1' onchange="checkNumInput(this)" value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>23 <input type="hidden" name="hc"
								value="23" id="hc_23"></td>
							<td class="2-td2-left-qysds1" nowrap>      8、远洋捕捞</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='23_1' value='' size='13' tabindex='1' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>47 <input type="hidden" name="hc"
								value="47" id="hc_47"></td>
							<td class="2-td2-left-qysds1" nowrap>所属行业（工业企业    其他企业    ）</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='je'
								id='47_1' value=''>
								<input type="Radio" name="hyje" id="hy47_1" value="01" onclick="changeSelect()" onchange="changeSelect()">工业企业
								<input type="Radio" name="hyje" id="hy47_2" value="02" onclick="changeSelect()" onchange="changeSelect()">其他企业
								</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>24 <input type="hidden" name="hc"
								value="24" id="hc_24"></td>
							<td class="2-td2-left-qysds1" nowrap>      9、其他</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='24_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>&nbsp;</td>
							<td class="2-td2-left-qysds1" nowrap>&nbsp;</td>
							<td class="2-td2-center" nowrap>&nbsp;</td>
						</tr>
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center">
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
					&nbsp;&nbsp; <input type="image" value="返回" alt="返回到企业所得税年报主页面"
						style="cursor:hand" tabIndex="-1" onClick="doReturn();return false;"
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