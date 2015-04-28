<%@ page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import ="com.ttsoft.bjtax.smsb.sgsswszmlr.common.Constant" %>

<html>
<head>
<title>手工录入税收完税证明</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<META content="text/html; charset=GBK" http-equiv=Content-Type>
<script language=JavaScript src="../js/DynamicTable.js"
	type="text/javascript"></script>
<script language=JavaScript src="../js/function.js"></script>

<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script type="text/javascript">
	var title_rows_count = 2;//指 sglrTable 表格固定标题所占的行数
	var bottom_rows_count = 2;//指 sglrTable 表格固定末行所占的行数
	/**
	 * 获得品目名称
	 */
	function getPmmc(obj) {
		var p_nodeObj = obj.parentNode.parentNode.cells[2].childNodes[0];
		var szsmArr = obj.value.split("-");
		var szsmmc ="";
		for(var index =1; index < szsmArr.length; index ++){
			if(index == 1){
				szsmmc = szsmArr[index];
			}else{
				szsmmc = szsmmc +"-"+ szsmArr[index];
			}
		}
		
		if (szsmmc != "") {
			p_nodeObj.innerHTML = szsmmc;
		} else {
			p_nodeObj.innerHTML = "&nbsp;";
		}
	}

	/**
	 * 计算合计金额
	 *		obj:当前实缴金额输入框对象
	 *		table_name：当前table名称
	 *		xxhj_div_ID：小写金额显示div 的ID
	 *      dxhj_div_ID：大写金额显示div 的ID
	 */
	var sjjeHJ = 0.00;
	function setHJJE(obj, table_name, xxhj_div_ID, dxhj_div_ID) {
		//校验当前所填金额是否合格，不合格，则返回并选中
		var sjje = obj.value;
		if (sjje == "" || parseFloat(sjje) == "NaN"
				|| !checkNumber(sjje, 15, 2, true)) {
			alert("必须为数字，\n小数点后的长度为2位,最大长度为15位，\n且输入值需大于等于零！");
			obj.select();
			return false;
		} else {
			obj.value = changeJE(sjje);
		}

		//合格，则相加计算
		ReCountAll(table_name, xxhj_div_ID, dxhj_div_ID);
		return false;
	}

	//重新进行计算合计金额
	function ReCountAll(table_name, xxhj_div_ID, dxhj_div_ID) {
		sjjeHJ = 0.00;//重置合计金额
		var table_obj = document.getElementById(table_name);
		for ( var i = 2; i < table_obj.rows.length - 2; i++) {
			var nodeOBJ = table_obj.rows[i].cells[table_obj.rows[i].cells.length - 2].childNodes[0];
			var nodeOBJ_value = nodeOBJ.value;
			if (nodeOBJ_value == "" || parseFloat(nodeOBJ_value) == "NaN"
					|| !checkNumber(nodeOBJ_value, 15, 2, true)) {
				nodeOBJ_value = 0.00;
			}
			//进行计算
			sjjeHJ = changeJE(Math
					.round((parseFloat(sjjeHJ) + parseFloat(nodeOBJ_value)) * 100.00) / 100.00);
		}

		//计算结束，显示计算结果
		doShowCaculate(sjjeHJ, xxhj_div_ID, dxhj_div_ID);

	}
	
	/**
	*显示计算金额
	*/
	function doShowCaculate(sjjeHJ, xxhj_div_ID, dxhj_div_ID){
		document.getElementById(xxhj_div_ID).innerHTML = sjjeHJ;//小写
		document.forms[0].sjjeHJ.value=sjjeHJ;//设置到隐藏域
		//大写
		if (dxhj_div_ID != "" && dxhj_div_ID != "0.00" && dxhj_div_ID != 0) {
			document.getElementById(dxhj_div_ID).innerHTML = "<font color=\"red\">（大写）</font>"
					+ getChineseMoney(formatNumber(sjjeHJ));
		} else {
			document.getElementById(dxhj_div_ID).innerHTML = "<font color=\"red\">（大写）</font>";
		}
	}
	
	
	

	//新增行时判断是否前N行有无没填的单元格，有则不允许新增，必填写完成
	function doAddOneRow(table_id, trId) {

		//新增前判断是否有未填写或者格式不对的数据
		if (!checkAllDate(table_id,"newAdd")) {
			return false;
		}

		//执行新增操作
		addLabelCol(trId, bottom_rows_count);
	}

	/*
	 * 提交信息
	 */
	function doSubmit(actionType, tableID, targetID) {
		document.forms[0].target="";
		//保存
		if(actionType=="Save" || actionType=="Update"){
			
			if(actionType=="Update"){
				var dybz = '<bean:write name="sgsswszmlrForm" property="dybz"/>';
				var cjwszmBYothers = '<bean:write name="sgsswszmlrForm" property="cjwszmBYothers"/>';
				//是否已经打印
				if(dybz != "" && dybz == '<%=Constant.CONS_SGLR_DYBZ_1%>'){
					alert("该证明已经打印，不能修改！");
					return false;
				}
				
				//是否被出具过完税证明
				if(cjwszmBYothers != "" && cjwszmBYothers == '<%=Constant.CONS_SGLR_CJWSZM_BY_OTHERS_Y%>'){
					alert("该证明已经被其他模块使用并出具了有效完税证明，不能进行修改，请确认！");
					return false;
				}
			}
			
			//判断必填项是否已经填写
			var nsrsbhOBJ = document.forms[0].nsrsbh;
			var nsrmcOBJ = document.forms[0].nsrmc;
			
			//判断纳税人识别号是否为空
			if(nsrsbhOBJ.value == null || nsrsbhOBJ.value ==""){
				alert("纳税人识别号为必填项，不能为空！");
				nsrsbhOBJ.focus();
				return false;
			}
			
			//判断纳税人名称是否为空
			if(nsrmcOBJ.value == null || nsrmcOBJ.value ==""){
				alert("纳税人名称为必填项，不能为空！");
				nsrmcOBJ.focus();
				return false;
			}
			
			//判断动态表是否填入了信息
			var table_obj = document.getElementById(tableID);
			var table_rows_len = table_obj.rows.length;
			if(table_rows_len == 4){
				alert("未录入完税证明信息，不能保存！");			
				return false;
			}
			
			//备注字段字数限制70字
			var bzObj = document.forms[0].bz;
			var bzValue = bzObj.value;
			if(bzValue != "" && bzValue.length > 70){
				alert("备注信息最多只能录入70个字，现已超出最大限制"+(bzValue.length-70)+"个字，请确认！");
				return false;
			}
			
			//提交前判断是否有未填写或者格式不对的数据
			if (!checkAllDate(tableID,"save")) {
				return false;
			}
			
	
			//获得动态表格中的数据
			getTableXXContext(tableID, title_rows_count, bottom_rows_count, targetID);
	
			if (confirm("是否将所填信息保存到系统，请确认！")) {
				document.forms[0].actionType.value = actionType;
				document.forms[0].submit();
				return false;
			} else {
				return false;
			}
		}
		
		//提交
			document.forms[0].actionType.value = actionType;
			document.forms[0].submit();
	}

	//提交总校验
	function checkAllDate(table_name,actionType) {
		var table_obj = document.getElementById(table_name);
		var table_rows_len = table_obj.rows.length;
		for ( var i = 2; i < table_rows_len - 2; i++) {
			for ( var j = 0; j < table_obj.rows[i].cells.length - 1; j++) {
				var tempVal = table_obj.rows[i].cells[j].childNodes[0];
				var tempTagName = tempVal.tagName;
				if (tempTagName != "DIV" && tempVal.value == "") {
					alert("信息填写不正确，含有未填写信息项！");
					tempVal.focus();
					return false;
				}

				//不为空，则检查数据是否符合格式要求
				if (tempTagName != "DIV" && checkDate(tempVal) == false) {
					return false;
				}
			}
		}
		
		//只能录入5行，加四行固定行一共该表最大行数为9行
		if(table_rows_len == 9 && actionType == "newAdd"){
			alert("该表最多只允许新增5行，现已经达到最大限制数，请确认！");
			return false;
		}
		
		return true;
	}

	//检查信息项是否输入正确
	function checkDate(obj) {
		//是否检查通过
		var pass = false;

		//获得当前对象的id
		var objID = obj.id;
		if (objID == "") {
			alert("页面发生错误，请退出页面重新进行录入!");
			return pass;
		}

		//对象的值
		var objVALUE = obj.value;

		if (objVALUE == "") {
			alert("请填写信息！");
			obj.focus();
			return pass;
		}

		//原凭证号
		if (objID == "sglr_old_pzh") {
			if (objVALUE.length != 0 && objVALUE.length > 30) {
				alert("原凭证号录入位数过长，最大不能超过30位，请检查！");
				obj.select();
				return pass;
			}
		}
		//税种
		if (objID == "sglr_select_zssmdm") {
			if (objVALUE == "") {
				alert("请选择对应税种！");
				obj.select();
				return pass;
			}
		}

		//品目名称

		//税款所属期 -起
		if (objID == "sglr_skssksrq") {
			if (isFullDate(objVALUE, 0, 'noempty', true) == false) {
				obj.select();
				return pass;
			}
		}

		//税款所属期-止
		if (objID == "sglr_skssjsrq") {
			if (isFullDate(objVALUE, 0, 'noempty', true) == false) {
				obj.select();
				return pass;
			}
		}

		//入（退）库日期
		if (objID == "sglr_rk_tkrq") {
			if (isFullDate(objVALUE, 0, 'noempty', true) == false) {
				obj.select();
				return pass;
			}
		}

		//实缴（退）金额
		if (objID == "sglr_sjje") {
			if (checkNumber(objVALUE, 15, 2, true) == false) {
				alert("必须为数字，\n小数点后的长度为2位,最大长度为15位，\n且输入值需大于等于零！");
				obj.select();
				return pass;
			}
		}

		pass = true;
		return pass;
	}

	//金额格式
	function changeJE(je) {
		var strJe = je.toString();
		var rs = strJe.indexOf('.');

		if (rs < 0) {
			rs = strJe.length;
			strJe += '.';
		}
		while (strJe.length <= rs + 2) {
			strJe += '0';
		}
		return strJe;
	}

	//function  : 检查输入字符串是否由数字组成
	//parameters: DigitString: String,待检查字符串的值
	//			  totalLength: int, 数字的长度限制（不包括小数点）
	//			  decimalLength: int，小数的长度限制
	//           totalLength-decimalLength 整数部分的限制
	//           isPositive 是否为非负数 true表示一定要是非负数，false表示一定要是负数，null表示任意
	//return    : false: 字符串中包含除数字外的字符
	//			  true : else
	function checkNumber(DigitString, totalLength, decimalLength, isPositive) {
		var re = "";

		if (isPositive == "true" || isPositive == true) {//如果是非负数
			if (isNaN(DigitString * 1) || DigitString * 1 < 0)
				return false;
		}
		if (isPositive == "false" || isPositive == false) {//如果是负数
			if (isNaN(DigitString * 1) || DigitString * 1 >= 0)
				return false;
		}

		if (isNaN(DigitString)) {
			return false;
		}
		if (decimalLength != null && decimalLength != 0) { //小数不为空
			re = re + "\\.[\\d]{1," + decimalLength + "}"
		}

		if (totalLength == null) { //未设定总长度
			re = "\\d*" + re;
		} else { //设定总长度
			//当小数部分为空的情况下，就是判断数字的长度
			var intergerLength = totalLength;
			if (decimalLength != null) { //当小数部分不为空判断，整数的位数和小数的位数是否正确
				intergerLength = totalLength - decimalLength;
			}
			re = "([\\d]{0," + intergerLength + "}" + re + "|[\\d]{0,"
					+ intergerLength + "})";
		}
		re = new RegExp("^[-|+]?" + re + "$", "g");

		//当字符串为空、位数不对、不能匹配成数字时
		if (re.exec(DigitString) == null) {
			return false;
		}
		return true;
	}
	
	//敲回车时移动焦点（计算机代码输入框除外）
	function moveFocus(){
		if(event.keyCode==13) event.keyCode = 9;
	}
</script>
<script type="text/javascript">
	//解析保存完成之后返回时的买卖方信息
	function parseSglrmxInfo(sglrmx, tableId, hidPropertyObj) {
		if (sglrmx != null && sglrmx != "") {
			if (sglrmx.indexOf("^") > 0) {
				var infoArr = sglrmx.split("^");

				for ( var index = 0; index < infoArr.length; index++) {
					var tempInfo = infoArr[index];
					//调用解析方法，解析每个买卖方的信息并显示
					parseOneGoup(tempInfo, tableId, hidPropertyObj);
				}
			} else {
				parseOneGoup(sglrmx, tableId, hidPropertyObj);
			}
		}

	}

	//设置买卖双方信息
	function parseOneGoup(info, tableId, hidPropertyObj) {
		var isSuccess = false;

		if (info == null || info == "") {
			return isSuccess;
		}

		var tableobj = document.getElementById(tableId);
		var AllGroupInfoArr = info.split("~");
		var submitInfo = "";

		if (AllGroupInfoArr != null && AllGroupInfoArr != "") {
			var count = AllGroupInfoArr.length / 6;
			//alert("信息组数"+count);

			if (count != null && count != 0) {
				var oneGroupInfo = new Array();
				for (zsIndex = 0; zsIndex < count; zsIndex++) {
					var value_0 = AllGroupInfoArr[0 + (zsIndex * 6)];
					var value_1 = AllGroupInfoArr[1 + (zsIndex * 6)];
					var value_2 = AllGroupInfoArr[2 + (zsIndex * 6)];
					var value_3 = AllGroupInfoArr[3 + (zsIndex * 6)];
					var value_4 = AllGroupInfoArr[4 + (zsIndex * 6)];
					var value_5 = AllGroupInfoArr[5 + (zsIndex * 6)];

					oneGroupInfo.push(value_0);
					oneGroupInfo.push(value_1);
					oneGroupInfo.push(value_2);//证件类型代码
					oneGroupInfo.push(value_3);
					oneGroupInfo.push(value_4);
					oneGroupInfo.push(value_5);

					//alert("RETURN::"+DSzjdm);

					//设置并显示每组信息
					isSuccess = showOneGroupInfo(oneGroupInfo, tableobj);

					if (!isSuccess) {
						alert("显示手工录入信息出错！");
						return isSuccess;
					}

					//拼接提交到后台的信息
					var tempOneGroupValue = value_0 + "~" + value_1 + "~"
							+ value_2 + "~" + value_3 + "~" + value_4 + "~"
							+ value_5;
					if (zsIndex == 0) {
						submitInfo = tempOneGroupValue;
					} else {
						submitInfo = submitInfo + "^" + tempOneGroupValue;
					}
					//重置信息
					oneGroupInfo = new Array();
				}
				//设置提交隐藏域的值
				if (hidPropertyObj != null && hidPropertyObj != "") {
					hidPropertyObj.value = submitInfo;
				}
			}
		}

		return true;
	}

	/*
	 *desc 根据不同浏览器给元素添加事件
	 *parma object obj(要添加事件的对象)
	 *parma string eventname(要添加的事件名)
	 *parma string callback(回调函数名)
	 *return void
	 */
	var addEvent = function(obj, eventname, callback) {

		//其他浏览器
		if (window.addEventListener) {
			obj.addEventListener(eventname, callback, false);
		}
		//ie
		else {
			obj.attachEvent('on' + eventname, callback);
		}

		return;

	}

	//把每组信息设置到td的innerHTML中显示
	function showOneGroupInfo(arr, tableobj) {

		if (tableobj == null || tableobj == "") {
			return false;
		}

		//新建行
		var tabLength = tableobj.rows.length;//重新再算一次rows长度
		var otr_n = tableobj.insertRow(tabLength - bottom_rows_count);

		//第n(n>=2)行第一列 
		var sglr_old_pzh_Cell = otr_n.insertCell();
		sglr_old_pzh_Cell.className = "2-td2-left";
		fnAddInput(sglr_old_pzh_Cell, arr[0], "sglr_old_pzh", "text");

		//第n(n>=2)行第二列  
		var sglr_select_zssmdm_Cell = otr_n.insertCell();
		sglr_select_zssmdm_Cell.className = "2-td2-left";
		var valuesArr = getSzsmArr();
		//sglr_select_zssmdm_Cell.onchange=getPmmc(sglr_select_zssmdm_Cell);
		var select_oNewItem = fnAddSelect(sglr_select_zssmdm_Cell,
				"sglr_select_zssmdm", valuesArr, arr[1]);

		//第n(n>=2)行第三列
		var szsmArr = arr[1].split("-");
		var szsmmc = "";
		for(var ind=1 ;ind < szsmArr.length; ind ++){
			if(ind == 1){
				szsmmc = szsmArr[ind];
			}else{
				szsmmc = szsmmc +"-"+ szsmArr[ind];
			}
		}
		var sglr_select_zssmmc_Cell = otr_n.insertCell();
		sglr_select_zssmmc_Cell.className = "2-td2-left";
		sglr_select_zssmmc_Cell.noWrap=true;
		fnAddDIV(sglr_select_zssmmc_Cell, szsmmc, "sglr_select_zssmmc")
		//select_oNewItem.onChange=function(){getPmmc(this);};

		//第n(n>=2)行第四列 
		var sglr_skssksrq_Cell = otr_n.insertCell();
		sglr_skssksrq_Cell.className = "2-td2-left";
		fnAddInput(sglr_skssksrq_Cell, arr[2], "sglr_skssksrq", "text");

		//第n(n>=2)行第五列 
		var upperline_Cell = otr_n.insertCell();
		upperline_Cell.className = "2-td2-left";
		upperline_Cell.innerHTML = "<div align=\"center\">-</div>";

		//第n(n>=2)行第六列 
		var sglr_skssjsrq_Cell = otr_n.insertCell();
		sglr_skssjsrq_Cell.className = "2-td2-left";
		fnAddInput(sglr_skssjsrq_Cell, arr[3], "sglr_skssjsrq", "text");

		//第n(n>=2)行第七列 
		var sglr_rk_tkrq_Cell = otr_n.insertCell();
		sglr_rk_tkrq_Cell.className = "2-td2-left";
		fnAddInput(sglr_rk_tkrq_Cell, arr[4], "sglr_rk_tkrq", "text");

		//第n(n>=2)行第八列 
		var sglr_sjje_Cell = otr_n.insertCell();
		sglr_sjje_Cell.className = "2-td2-left";
		fnAddInput(sglr_sjje_Cell, arr[5], "sglr_sjje", "text");

		//第n(n>=2)行第九列 
		var checkbox_value_Cell = otr_n.insertCell();
		checkbox_value_Cell.className = "2-td2-center";
		fnAddInput(checkbox_value_Cell, arr[6], "labelFlag", "checkbox");

		return true;
	}

	//在指定元素下创建一个imput输入框
	function fnAddInput(cellObj, newItemValue, newItemId, inputType) {
		var oNewItem = document.createElement("INPUT");
		//如果是输入框
		if (inputType == "text") {
			oNewItem.value = newItemValue;
			//增加时间校验函数
			if (newItemId == "sglr_skssksrq" || newItemId == "sglr_skssjsrq"
					|| newItemId == "sglr_rk_tkrq") {
				addEvent(oNewItem, 'change', function() {
					checkDate(oNewItem)
				});
			}
			//设置合计
			if (newItemId == "sglr_sjje") {
				addEvent(oNewItem, 'change', function() {
					setHJJE(oNewItem, 'sglrTable', 'sjjeHJ_XX', 'sjjeHJ_DX')
				});
			}
		}
		oNewItem.id = newItemId;
		oNewItem.name = newItemId;
		oNewItem.type = inputType;
		cellObj.insertAdjacentElement("afterBegin", oNewItem);

	}

	//在指定元素下创建一个select下来框
	function fnAddSelect(cellObj, newItemId, valuesArr, value) {

		//创建一个select标签
		var oNewItem = document.createElement("SELECT");
		oNewItem.id = newItemId;

		for ( var index = 0; index < valuesArr.length; index++) {
			var oValueArr = valuesArr[index];
			var optionValue = oValueArr[0];//option的值
			var optionName = oValueArr[1];//option的名称

			//创建一个option标签
			var optionObj = document.createElement("OPTION");
			optionObj.innerHTML = optionName;
			optionObj.value = optionValue;
			if (value == optionValue) {
				optionObj.selected = true;
			}

			//
			oNewItem.insertAdjacentElement("beforeEnd", optionObj);
		}
		if (newItemId == "sglr_select_zssmdm") {
			addEvent(oNewItem, 'change', function() {
				getPmmc(oNewItem)
			});
		}
		cellObj.insertAdjacentElement("afterBegin", oNewItem);

		return oNewItem;
	}

	/**
	 * 新增一个DIV
	 */
	function fnAddDIV(cellObj, newItemValue, newItemId) {
		var oNewItem = document.createElement("DIV");
		oNewItem.id = newItemId;
		oNewItem.innerHTML = newItemValue;
		cellObj.insertAdjacentElement("afterBegin", oNewItem);
	}

	//获得税目ARR
	function getSzsmArr() {
		var szsmArr = new Array();
		<logic:iterate id="item" name="sgsswszmlrForm"  property ="smsmList" indexId="index">
		var szsmdm_1 = '<bean:write name="item" property="szsmmc"/>';
		var szsmmc_1 = '<bean:write name="item" property="szsmmc"/>';

		var oneSzsm = new Array();
		oneSzsm.push(szsmdm_1);
		oneSzsm.push(szsmmc_1);

		//
		szsmArr.push(oneSzsm);
		</logic:iterate>

		return szsmArr;
	}
	
	function init(){
		var saveIsSucc = '<bean:write name="sgsswszmlrForm" property="saveIsSucc" />';
		var sglrmx = '<bean:write name="sgsswszmlrForm" property="wsxxAll" />';
		//回显明细信息
		if(sglrmx !=""){
			parseSglrmxInfo(sglrmx,'sglrTable','wsxxAll');
		}
		
		//初始化合计金额
		var actionType = document.forms[0].actionType.value;
		if (actionType == "Show"){
			sjjeHJ = 0.00;
			doShowCaculate(sjjeHJ,'sjjeHJ_XX','sjjeHJ_DX');
		}else{
			sjjeHJ = changeJE('<bean:write name="sgsswszmlrForm"  property="sjjeHJ"/>');
			doShowCaculate(sjjeHJ,'sjjeHJ_XX','sjjeHJ_DX');
		}
		
		//显示备注信息
		var bz = '<bean:write name="sgsswszmlrForm"  property="bz"/>';
		if(bz !=""){
			bz = bz +"<br>打印次数：0（TODO）";
			
		}
		
		//显示提示信息
		var message = '<bean:write name="sgsswszmlrForm" property="message" />';
		if(message!=""){
			alert(message);
		}else{
			if(actionType=="Save" || actionType=="Update"){
				if(saveIsSucc == '<%=Constant.CONS_SGLR_SAVEISSUCC_Y%>'){
					alert("保存成功!");
				}
				
				if(saveIsSucc == '<%=Constant.CONS_SGLR_SAVEISSUCC_N%>'){
						alert("保存失败，请重试!");
				}
			}
		}
		return false;
	}
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="init();">
	<%@ include file="./include/header.jsp"%>
	<link href="<%=static_contextpath%>css/text.css" rel="stylesheet"
		type="text/css">
	<html:form action="/webapp/sgsswszmlr/sgsswszmlrAction.do"
		method="POST">
		<html:hidden property="actionType" />
		<html:hidden property="wsxxAll" />
		<html:hidden property="sjjeHJ"/>
		<html:hidden property="wszmKey"/>
		
		<!-- 维护界面隐藏域 -->
		<html:hidden property="pageSize"/>
		<html:hidden property="nextPage"/>
		<html:hidden property="totalpage"/>
		<html:hidden property="query_nsrsbh"/>
		<html:hidden property="query_nsrmc"/>
		<html:hidden property="query_wspzh"/>
	
		<table align="center" cellspacing="0" class="table-100"
			style="width: 100%" onkeydown="moveFocus()">
			<tr>
				<td class="1-td1" colspan=4 valign="top">手工录入税收完税证明</td>
			</tr>
			<tr>
				<td class="1-td2" style="width: 100%">
					<table style="width: 100%;" cellspacing="0" class="table-99">
						<tr>
							<td class="2-td2-t-left">填发日期</td>
							<td class="2-td2-t-left"><div align="left">
									<bean:write name="sgsswszmlrForm" property="tfrq" />
								</div></td>
							<td class="2-td2-t-left">税务机关</td>
							<td class="2-td2-t-center"><div align="left">
									<bean:write name="sgsswszmlrForm" property="swjgmc" />
								</div></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap="nowrap">纳税人识别号<font
								color="red">*</font></td>
							<td class="2-td2-left"><div align="left">
									<html:text name="sgsswszmlrForm" property="nsrsbh" size="30" maxlength="50"/>
								</div></td>
							<td class="2-td2-left" nowrap="nowrap">纳税人名称<font
								color="red">*</font></td>
							<td class="2-td2-center"><div align="left">
									<html:text name="sgsswszmlrForm" property="nsrmc" size="60" maxlength="200"/>
								</div></td>
						</tr>
						<tr>
							<td class="2-td2-left">录入凭证类型<font color="red">*</font></td>
							<td class="2-td2-center" colspan="3"><div align="left">
									缴税凭证
									<html:radio name="sgsswszmlrForm" property="pzlxdm" value="0"></html:radio>
									退税凭证
									<html:radio name="sgsswszmlrForm" property="pzlxdm" value="1"></html:radio>
								</div></td>
						</tr>
						<tr>
							<td colspan="8">
								<table style="width: 100%;" cellspacing="0" class="table-99"
									id="sglrTable">
									<tr>
										<td class="2-td2-left" rowspan="2" nowrap="nowrap">原凭证号</td>
										<td class="2-td2-left" rowspan="2" nowrap="nowrap">税种</td>
										<td class="2-td2-left" rowspan="2" nowrap="nowrap">品目名称</td>
										<td class="2-td2-left" colspan="3" nowrap="nowrap">税款所属期</td>
										<td class="2-td2-left" rowspan="2" nowrap="nowrap">入（退）库日期</td>
										<td class="2-td2-left" rowspan="2" nowrap="nowrap">实缴（退）金额</td>
										<td class="2-td2-center" rowspan="2" colspan="5" width="5%"
											nowrap="nowrap">操作</td>
									</tr>
									<tr>
										<td class="2-td2-left">起</td>
										<td class="2-td2-left">-</td>
										<td class="2-td2-left">止</td>
									</tr>
									<tr>
										<td colspan="13" align="center" class="2-td2-center">
											<div align="center">
												<img onClick="doAddOneRow('sglrTable','labelsample');"
													alt="新增一行" style="cursor: hand"
													onMouseOver="MM_swapImage('add','','<%=static_contextpath%>images/xinzeng2.jpg',1)"
													onMouseOut="MM_swapImgRestore()" id="add"
													src="<%=static_contextpath%>images/xinzeng1.jpg" width="79"
													height="22"> 
													
													<img
													onClick="removeLabelCol('sglrTable');ReCountAll('sglrTable','sjjeHJ_XX','sjjeHJ_DX')"
													alt="删除所有选中行" style="cursor: hand"
													onMouseOver="MM_swapImage('del','','<%=static_contextpath%>images/shanchu2.jpg',1)"
													onMouseOut="MM_swapImgRestore();" id="del"
													src="<%=static_contextpath%>images/shanchu1.jpg" width="79"
													height="22">
											</div>
										</td>
									</tr>
									<tr>
										<td class="2-td2-left" width="10%">金额合计</td>
										<td class="2-td2-left" colspan="6"><div align="left"
												id="sjjeHJ_DX">
												<font color="red">（大写）</font>
											</div></td>
										<td class="2-td2-center" width="10%" colspan="6"><div
												id="sjjeHJ_XX">&nbsp;</div></td>
									</tr>
								</table>
								<table style="width: 100%;" cellspacing="0" class="table-99">
									<tr>
										<td class="2-td2-left" rowspan="3" width="10%">备注</td>
										<td class="2-td2-center" rowspan="3" colspan="6">
											<div align="left">
												<html:textarea property="bz" rows="6" style="width=100%"></html:textarea>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="10">
							<br>
							<br>
								<div align="center">
									<!-- doSubmitForm('Save') -->
										<logic:equal name="sgsswszmlrForm"  property="actionType" value="Save">
										<IMG alt="保存填写信息到系统" height=22 id=baocun name=baocun
											onclick="doSubmit('Save','sglrTable','wsxxAll')"
											onmouseout=MM_swapImgRestore()
											onmouseover="MM_swapImage('baocun','','<%=static_contextpath%>images/baocun2.jpg',1)"
											src="<%=static_contextpath%>images/baocun1.jpg"
											style='CURSOR: hand' width=79> 
									</logic:equal>
									
									<logic:equal name="sgsswszmlrForm"  property="actionType" value="Show">
										<IMG alt="保存填写信息到系统" height=22 id=baocun name=baocun
											onclick="doSubmit('Save','sglrTable','wsxxAll')"
											onmouseout=MM_swapImgRestore()
											onmouseover="MM_swapImage('baocun','','<%=static_contextpath%>images/baocun2.jpg',1)"
											src="<%=static_contextpath%>images/baocun1.jpg"
											style='CURSOR: hand' width=79> 
									</logic:equal>
									
									<logic:notEqual name="sgsswszmlrForm"  property="actionType" value="Save">
										<logic:notEqual name="sgsswszmlrForm"  property="actionType" value="Show">
											<logic:equal name="sgsswszmlrForm" property="yxbz" value="0">
												<IMG alt=保存修改信息  height=22 id=b-bcbg name=b-bcbg 
												     onclick="doSubmit('Update','sglrTable','wsxxAll')" 
												     onmouseout=MM_swapImgRestore() 
												     onmouseover="MM_swapImage('b-bcbg','','<%=static_contextpath%>images/b-bcbg2.jpg',1)" 
												     src="<%=static_contextpath%>images/b-bcbg1.jpg" 
												     style='CURSOR: hand' width=79>
											</logic:equal>
												<img
													src="<%=static_contextpath%>images/fanhui1.jpg" value="返回"
													name="fanhui" width="79" height="22" border="0"
													onClick="doSubmit('Query','','');return false;"
													id="fanhui" alt="返回"
													onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
													onMouseOut="MM_swapImgRestore()">
										</logic:notEqual>
									</logic:notEqual>
												
									<input type="image"
										onClick="tuichu();return false;"
										onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tuichu2.jpg',1)"
										alt="退出到菜单选择界面" onMouseOut="MM_swapImgRestore()" id="tc1"
										src="<%=static_contextpath%>images/tuichu1.jpg" width="79"
										height="22" style="cursor: hand">
									
								</div>
								<br>
								<br>
							</td>
						</tr>
						<tr>
							<td class="2-td2-t-left">录入人</td>
							<td class="2-td2-t-left"><div align="left">
									<bean:write name="sgsswszmlrForm" property="lrrmc" />
								</div></td>
							<td class="2-td2-t-left">录入日期</td>
							<td class="2-td2-t-center"><div align="left">
									<bean:write name="sgsswszmlrForm" property="lrrq" />
								</div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table style="display: none">
			<tr id="labelsample" height="24">
				<td class=2-td2-left width="10%"><input id="sglr_old_pzh"
					type="text" onchange="checkDate(this)"></td>
				<td class=2-td2-left width="20%"><select
					id="sglr_select_zssmdm" onchange="getPmmc(this)" style="width:350px">
						<logic:iterate id="item" name="sgsswszmlrForm" property="smsmList"
							indexId="index">
							<option value="<bean:write name="item" property="szsmmc"/>">
								<bean:write name="item" property="szsmmc" />
							</option>
						</logic:iterate>
				</select></td>
				<td class=2-td2-left width="15%"><div id="sglr_select_zssmmc">&nbsp;</div></td>
				<td class=2-td2-left width="5%"><input id="sglr_skssksrq"
					type="text" onchange="checkDate(this)"></td>
				<td class=2-td2-left width="5%"><div align="center">-</div></td>
				<td class=2-td2-left width="5%"><input id="sglr_skssjsrq"
					type="text" onchange="checkDate(this)"></td>
				<td class=2-td2-left width="5%"><input id="sglr_rk_tkrq"
					type="text" onchange="checkDate(this)"></td>
				<td class=2-td2-left width="10%"><input id="sglr_sjje"
					type="text"
					onchange="setHJJE(this,'sglrTable','sjjeHJ_XX','sjjeHJ_DX')"></td>
				<td class=2-td2-center align="center" width="5%"><input
					type="checkbox" name="labelFlag" id="labelFlag" value=""></td>
			</tr>
		</table>
		<br>
		<br>

		<table width="100%" align="center" cellspacing="0" valign="bottom">

			<tr align="center">
				<td><%@ include file="./include/footer.jsp"%>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html>