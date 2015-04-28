<%@ page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import ="com.ttsoft.bjtax.smsb.sgsswszmlr.common.Constant" %>
<%@page import ="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>

<html>
<head>
<title>总局减免税代码录入</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<META content="text/html; charset=GBK" http-equiv=Content-Type>
<script language=JavaScript src="../js/DynamicTable.js"
	type="text/javascript"></script>
<script language=JavaScript src="../js/function.js"></script>

<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script type="text/javascript">
	var title_rows_count=2;//指 sglrTable 表格固定标题所占的行数
	var bottom_rows_count=1;//指 sglrTable 表格固定末行所占的行数
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
	*通过减免税性质大类代码过滤小类代码
	*/
	function doFilterJmxzxldm_byDLDM(obj){
		//当前选择大类的值
		var select_dldm= obj.value;
		
		//获得当前行第三列td对象，下标为2
		var jmxzdm_xl_td_Obj = obj.parentNode.parentNode.cells[2];
		
		//获得当前行减免性质小类
		var jmxzxlObj = jmxzdm_xl_td_Obj.childNodes[0];
		//删除当前小类select标签对象
		jmxzdm_xl_td_Obj.removeChild(jmxzxlObj);
		//按照选择大类重新生成小类下拉菜单
		var valuesArr_jmxz_xl = getJmxz_dl_xl_Arr("jmxz_xl",select_dldm);
		select_oNewItem = fnAddSelect(jmxzdm_xl_td_Obj,"sglr_select_jmxzdm_xl", valuesArr_jmxz_xl, "");
		//alert(jmxzxlObj.id);
	}
	
	
	//定义上次选中的税种代码以及大类代码、小类代码
	var default_SZDM="";
	var default_DLDM="";
	var default_XLDM="";
	
	function saveDefaulValues(objValue,objType){
		if(objType =="szdm"){
			default_SZDM = objValue;
		}
		
		if(objType =="jmxzdl"){
			default_DLDM = objValue;
		}
		
		if(objType =="jmxzxl"){
			default_XLDM = objValue;
		}
		
		//alert("sz::"+default_SZDM+"  dl::"+default_DLDM+"  xl::"+default_XLDM);
	}
	
	function doSelecedDefault(table_id){
		//将当前新增行的税种、减免大类、减免小类置成上次选择的
		var table_obj = document.all(table_id);
		if(table_obj){
			var tableChildsArr = table_obj.rows;//获得当前表的所有行
			//获得新增行
			if(tableChildsArr && tableChildsArr.length >0){
				var newAddTR_OBJ = tableChildsArr[tableChildsArr.length -2];//
				if(newAddTR_OBJ){
					//选中税种、减免大类、小类
					var newAddTR_child_OBJs = newAddTR_OBJ.childNodes;//获得新增行的所有TD对象
					if(newAddTR_child_OBJs){
						newAddTR_child_OBJs[0].childNodes[0].value=default_SZDM;//选中税种
						newAddTR_child_OBJs[1].childNodes[0].value=default_DLDM;//选中大类
						//获得小类
						doFilterJmxzxldm_byDLDM(newAddTR_child_OBJs[1].childNodes[0]);
						if(default_XLDM != ""){
							newAddTR_child_OBJs[2].childNodes[0].value=default_XLDM;//选中小类
						}
					}
					
				}
			}
			
		}
	}
	
	//新增行时判断是否前N行有无没填的单元格，有则不允许新增，必填写完成
	function doAddOneRow(table_id, trId) {

		//新增前判断是否有未填写或者格式不对的数据
		if (!checkAllDate(table_id,"newAdd")) {
			return false;
		}

		//执行新增操作
		addLabelCol(trId,bottom_rows_count);
		
		//设置新增行下拉值
		doSelecedDefault(table_id);
	}
	

	/*
	 * 提交信息
	 */
	function doSubmit(actionType, tableID, targetID) {
		document.forms[0].target="";
		//保存
		if(actionType=="Save" || actionType=="Update"){
			//提交前判断是否有未填写或者格式不对的数据
			if (!checkAllDate(tableID,"save")) {
				return false;
			}
			
			if(actionType=="Update"){
				document.forms[0].updateType.value="<%=CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL%>";
			}			
	
			//获得动态表格中的数据
			getTableXXContext_local(tableID, title_rows_count, bottom_rows_count, targetID);
			
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
		for ( var i = 2; i < table_rows_len - 1; i++) {
			for ( var j = 0; j < table_obj.rows[i].cells.length - 1; j++) {
				var tempVal = table_obj.rows[i].cells[j].childNodes[0];
				var tempTagId = tempVal.id;
				//其中备注不是必填项
				if (tempVal.value == "" && tempTagId !="sglr_bz") {
					alert("信息填写不正确，含有未填写信息项！");
					tempVal.focus();
					return false;
				}

				//不为空，则检查数据是否符合格式要求
				if (checkDate(tempVal) == false ) {
					return false;
				}
			}
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

		//备注信息不是必填项
		if (objVALUE == "" && objID !="sglr_bz") {
			alert("请填写信息！");
			obj.focus();
			return pass;
		}

		//税种
		if (objID == "sglr_select_zssmdm") {
			if (objVALUE == "") {
				alert("请选择对应税种！");
				obj.select();
				return pass;
			}
		}
		//减免性质大类
		if (objID == "sglr_select_jmxzdm_dl") {
			if (objVALUE == "") {
				alert("请选择对应减免性质大类！");
				obj.select();
				return pass;
			}
		}
		//减免性质小类
		if (objID == "sglr_select_jmxzdm_xl") {
			if (objVALUE == "") {
				alert("请选择对应减免性质小类！");
				obj.select();
				return pass;
			}
		}
		//减免税类型代码
		if (objID == "sglr_jmslxdm") {
			if (objVALUE.length != 0 && objVALUE.length > 20) {
				alert("减免税类型代码最多只能录入20个字，现已超出最大限制"+(objVALUE.length-20)+"个字，请确认！");
				obj.select();
				return pass;
			}
		}
		
		//减免税类型名称
		if (objID == "sglr_jmslxmc") {
			if (objVALUE.length != 0 && objVALUE.length > 200) {
				alert("减免税类型名称最多只能录入200个字，现已超出最大限制"+(objVALUE.length-200)+"个字，请确认！");
				obj.select();
				return pass;
			}
		}
		
		//减免税政策起始年度
		if (objID == "sglr_jmszcqsnd") {
			if (objVALUE.length != 0 && objVALUE.length > 4) {
				alert("减免税政策起始年度字数过长，最大不能超过4个数字，如：2014，请检查！");
				obj.select();
				return pass;
			}else if ((objVALUE.length ==4 && !isDigit(objVALUE))|| objVALUE.length <4){
				alert("减免税政策起始年度填写错误，请填写正确四位年，如：2014，请检查！");
				obj.select();
				return pass;
			}
		}
		
		//文号
		if (objID == "sglr_wh") {
			if (objVALUE.length != 0 && objVALUE.length > 100) {
				alert("文号最多只能录入100个字，现已超出最大限制"+(objVALUE.length-100)+"个字，请确认！");
				obj.select();
				return pass;
			}
		}
		
		//备注
		if (objID == "sglr_bz") {
			if (objVALUE.length != 0 && objVALUE.length > 100) {
				alert("备注最多只能录入100个字，现已超出最大限制"+(objVALUE.length-100)+"个字，请确认！");
				obj.select();
				return pass;
			}
		}
		

		pass = true;
		return pass;
	}
	
	//敲回车时移动焦点（计算机代码输入框除外）
	function moveFocus(){
		if(event.keyCode==13) event.keyCode = 9;
	}
</script>
<script type="text/javascript">
	//解析保存完成之后返回时的信息
	function parseSglrmxInfo(sglrmx, tableId, hidPropertyObj) {
		if (sglrmx != null && sglrmx != "") {
			if (sglrmx.indexOf("^") > 0) {
				var infoArr = sglrmx.split("^");

				for ( var index = 0; index < infoArr.length; index++) {
					var tempInfo = infoArr[index];
					//调用解析方法，解析每条新增信息并显示
					parseOneGoup(tempInfo, tableId, hidPropertyObj);
				}
			} else {
				parseOneGoup(sglrmx, tableId, hidPropertyObj);
			}
		}

	}

	//设置录入信息
	function parseOneGoup(info, tableId, hidPropertyObj) {
		var isSuccess = false;

		if (info == null || info == "") {
			return isSuccess;
		}

		var tableobj = document.getElementById(tableId);
		var AllGroupInfoArr = info.split("~");
		var submitInfo = "";

		if (AllGroupInfoArr != null && AllGroupInfoArr != "") {
			var count = AllGroupInfoArr.length / 9;
			//alert("信息组数"+count);

			if (count != null && count != 0) {
				var oneGroupInfo = new Array();
				for (zsIndex = 0; zsIndex < count; zsIndex++) {
					var value_0 = AllGroupInfoArr[0 + (zsIndex * 9)];
					var value_1 = AllGroupInfoArr[1 + (zsIndex * 9)];
					var value_2 = AllGroupInfoArr[2 + (zsIndex * 9)];
					var value_3 = AllGroupInfoArr[3 + (zsIndex * 9)];
					var value_4 = AllGroupInfoArr[4 + (zsIndex * 9)];
					var value_5 = AllGroupInfoArr[5 + (zsIndex * 9)];
					var value_6 = AllGroupInfoArr[6 + (zsIndex * 9)];
					var value_7 = AllGroupInfoArr[7 + (zsIndex * 9)];

					oneGroupInfo.push(value_0);
					oneGroupInfo.push(value_1);
					oneGroupInfo.push(value_2);
					oneGroupInfo.push(value_3);
					oneGroupInfo.push(value_4);
					oneGroupInfo.push(value_5);
					oneGroupInfo.push(value_6);
					oneGroupInfo.push(value_7);

					//alert("RETURN::"+DSzjdm);

					//设置并显示每组信息
					isSuccess = showOneGroupInfo(oneGroupInfo, tableobj);

					if (!isSuccess) {
						alert("显示录入信息出错！");
						return isSuccess;
					}

					//拼接提交到后台的信息
					var tempOneGroupValue = value_0 + "~" + value_1 + "~"
							+ value_2 + "~" + value_3 + "~" + value_4 + "~"
							+ value_5+"~"+ value_6+"~"+ value_7;
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
		var sglr_select_zssmdm_Cell = otr_n.insertCell();
		sglr_select_zssmdm_Cell.className = "2-td2-left";
		sglr_select_zssmdm_Cell.width="5%";
		var valuesArr = getSzsmArr();
		var select_oNewItem = fnAddSelect(sglr_select_zssmdm_Cell,"sglr_select_zssmdm", valuesArr, arr[0]);
		
		//第n(n>=2)行第二列 
		var sglr_select_jmxzdm_dl_Cell = otr_n.insertCell();
		sglr_select_jmxzdm_dl_Cell.className = "2-td2-left";
		sglr_select_jmxzdm_dl_Cell.width="5%";
		var valuesArr_jmxz_dl = getJmxz_dl_xl_Arr("jmxz_dl","");
		select_oNewItem = fnAddSelect(sglr_select_jmxzdm_dl_Cell,"sglr_select_jmxzdm_dl", valuesArr_jmxz_dl, arr[1]);

		//第n(n>=2)行第三列
		var sglr_select_jmxzdm_xl_Cell = otr_n.insertCell();
		sglr_select_jmxzdm_xl_Cell.className = "2-td2-left";
		sglr_select_jmxzdm_xl_Cell.width="5%";
		var valuesArr_jmxz_xl = getJmxz_dl_xl_Arr("jmxz_xl",arr[1]);
		select_oNewItem = fnAddSelect(sglr_select_jmxzdm_xl_Cell,"sglr_select_jmxzdm_xl", valuesArr_jmxz_xl, arr[2]);

		//第n(n>=2)行第四列 
		var sglr_jmslxdm_Cell = otr_n.insertCell();
		sglr_jmslxdm_Cell.className = "2-td2-left";
		sglr_jmslxdm_Cell.width="3%";
		fnAddInput(sglr_jmslxdm_Cell, arr[3], "sglr_jmslxdm", "text");

		//第n(n>=2)行第五列 
		var sglr_jmslxmc_Cell = otr_n.insertCell();
		sglr_jmslxmc_Cell.className = "2-td2-left";
		sglr_jmslxmc_Cell.width="20%";
		fnAddTextarea(sglr_jmslxmc_Cell, arr[4], "sglr_jmslxmc");

		//第n(n>=2)行第六列 
		var sglr_jmszcqsnd_Cell = otr_n.insertCell();
		sglr_jmszcqsnd_Cell.className = "2-td2-left";
		sglr_jmszcqsnd_Cell.width="3%";
		fnAddInput(sglr_jmszcqsnd_Cell, arr[5], "sglr_jmszcqsnd", "text");

		//第n(n>=2)行第七列 
		var sglr_wh_Cell = otr_n.insertCell();
		sglr_wh_Cell.className = "2-td2-left";
		sglr_wh_Cell.width="5%";
		fnAddInput(sglr_wh_Cell, arr[6], "sglr_wh", "text");

		//第n(n>=2)行第八列 
		var sglr_bz_Cell = otr_n.insertCell();
		sglr_bz_Cell.className = "2-td2-left";
		sglr_bz_Cell.width="10%";
		fnAddTextarea(sglr_bz_Cell, arr[7], "sglr_bz");

		//第n(n>=2)行第九列 
		var checkbox_value_Cell = otr_n.insertCell();
		checkbox_value_Cell.className = "2-td2-center";
		checkbox_value_Cell.width="1%";
		var modifyKey_jmslxdm = document.forms[0].modifyKey_jmslxdm.value;
		if(modifyKey_jmslxdm == ""){
			fnAddInput(checkbox_value_Cell, '', "labelFlag", "checkbox");
		}else{
			checkbox_value_Cell.innerHTML="&nbsp;";
		}

		return true;
	}

	//在指定元素下创建一个imput输入框
	function fnAddInput(cellObj, newItemValue, newItemId, inputType) {
		var oNewItem = document.createElement("INPUT");
		//如果是输入框
		if (inputType == "text") {
			oNewItem.value = newItemValue;
			//特殊控制
			if (newItemId == "sglr_jmszcqsnd") {
				oNewItem.style.width="60px";
			}
			
			//如果修改，则置为不可编辑
			if(newItemId == "sglr_jmslxdm"){
				oNewItem.style.width="100%";
				var modifyKey_jmslxdm = document.forms[0].modifyKey_jmslxdm.value;
				if(modifyKey_jmslxdm != ""){
					oNewItem.readOnly=true;
					oNewItem.style.background="silver";
						addEvent(oNewItem, 'keydown', function() {
							moveFocus()
						});
				}
			}
		}
		
		oNewItem.id = newItemId;
		oNewItem.name = newItemId;
		oNewItem.type = inputType;
		cellObj.insertAdjacentElement("afterBegin", oNewItem);

	}
	
	//新增一个textarea
	function fnAddTextarea(cellObj, newItemValue, newItemId){
		var oNewItem = document.createElement("TEXTAREA");
		oNewItem.id = newItemId;
		oNewItem.name = newItemId;
		oNewItem.value = newItemValue;
		oNewItem.rows="3";
		oNewItem.style.width="100%";
		cellObj.insertAdjacentElement("afterBegin", oNewItem);
	}

	//在指定元素下创建一个select下来框
	function fnAddSelect(cellObj, newItemId, valuesArr, value) {

		//创建一个select标签
		var oNewItem = document.createElement("SELECT");
		oNewItem.id = newItemId;
		//if(newItemId == "sglr_select_zssmdm"){
			oNewItem.style.width="100px";
		//}
		
		//给大类增加事件
		if(newItemId == "sglr_select_jmxzdm_dl"){
			addEvent(oNewItem, 'change', function() {
				doFilterJmxzxldm_byDLDM(oNewItem);saveDefaulValues(oNewItem.value,'jmxzdl')
			});
		}
		
		//给小类增加事件  
		if(newItemId == "sglr_select_jmxzdm_xl"){
			addEvent(oNewItem, 'change', function() {
				saveDefaulValues(oNewItem.value,'jmxzxl')
			});
		}
		
		//给税种添加事件  
		if(newItemId == "sglr_select_zssmdm"){
			addEvent(oNewItem, 'change', function() {
				saveDefaulValues(oNewItem.value,'szdm')
			});
		}
		

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
		<logic:iterate id="item" name="zjjmsdmwhForm"  property ="smsmList" indexId="index">
		var szsmdm_1 = '<bean:write name="item" property="szsmdm"/>';
		var szsmmc_1 = '<bean:write name="item" property="szsmmc"/>';

		var oneSzsm = new Array();
		oneSzsm.push(szsmdm_1);
		oneSzsm.push(szsmmc_1);

		//
		szsmArr.push(oneSzsm);
		</logic:iterate>

		return szsmArr;
	}
	//获得减免性质大类、小类Arr
	function getJmxz_dl_xl_Arr(xzType,jmxzdm_dl) {
		var jmxzArr = new Array();
		//构造大类
		if(xzType=="jmxz_dl"){
			<logic:iterate id="item" name="zjjmsdmwhForm"  property ="jmxzdlList" indexId="index">
			var jmxzdm_dl_1 = '<bean:write name="item" property="jmxzdm"/>';
			var jmxzmc_dl_1 = '<bean:write name="item" property="jmxzmc"/>';
	
			var oneJmxz_dl = new Array();
			oneJmxz_dl.push(jmxzdm_dl_1);
			oneJmxz_dl.push(jmxzmc_dl_1);
	
			//
			jmxzArr.push(oneJmxz_dl);
			</logic:iterate>
		}
		
		//构造小类
		if(xzType=="jmxz_xl"){
			<logic:iterate id="item" name="zjjmsdmwhForm"  property ="jmxzxlList" indexId="index">
			var jmxzdm_xl_1 = '<bean:write name="item" property="jmxzdm"/>';
			var jmxzmc_xl_1 = '<bean:write name="item" property="jmxzmc"/>';
	
			var oneJmxz_xl = new Array();
			oneJmxz_xl.push(jmxzdm_xl_1);
			oneJmxz_xl.push(jmxzmc_xl_1);
			//减免性质大类不为空，则当前操作是过滤操作，即选择大类，带出对应小类
			if(jmxzdm_dl != ""){
				//alert(jmxzdm_dl+"---"+jmxzdm_xl_1.substr(1,4));
				if(jmxzdm_dl==jmxzdm_xl_1.substr(0,4)){
					jmxzArr.push(oneJmxz_xl);
				}
			}else{
				jmxzArr.push(oneJmxz_xl);
			}
			</logic:iterate>
		}

		return jmxzArr;
	}	
	
	
	
	function init(){
		var saveIsSucc = '<bean:write name="zjjmsdmwhForm" property="saveIsSucc" />';
		var sglrmx = '<bean:write name="zjjmsdmwhForm" property="allNewAddInfo" />';
		var actionType ='<bean:write name="zjjmsdmwhForm" property="actionType" />';
		//回显明细信息
		if(sglrmx !=""){
			parseSglrmxInfo(sglrmx,'sglrTable','allNewAddInfo');
		}
		
		//显示提示信息
		var message = '<bean:write name="zjjmsdmwhForm" property="message" />';
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
	
	//将信息拼接到一起传到后台进行处理,记录之间用^分割，记录中的每一项用~分隔
	function getTableXXContext_local(table_name,start_row,end_row,target){	
		var table_obj = document.getElementById(table_name);
	    var arr = new Array();
	    var str;
	    for(var i=start_row;i <table_obj.rows.length-end_row;i++){
	        var b = new Array(); 
            for(var j=0;j <table_obj.rows[i].cells.length-1;j++){     
            	var tempVal = table_obj.rows[i].cells[j].childNodes[0];
            	
            	var tempTagName = tempVal.tagName;
            	var tempTagId   = tempVal.id;
            	
            	if(tempTagName != "DIV"&& tempVal.value == "" && tempTagId !="sglr_bz"){
            		alert("信息填写不正确，含有未填写信息项！");
            		tempVal.focus();
            		return false;
            	}
            	if(tempTagName != "DIV"){
            		b.push(clertBR(tempVal.value));
            	}
            }
            str = b.join("~");
            arr.push(str);
        }
	    //alert(arr.join("^"));
	    	document.getElementById(target).value = arr.join("^");
	    return true;
	}
	
	//去掉回车
	function clertBR(key)
	{
		key = key.replace(/<\/?.+?>/g,"");
		key = key.replace(/[\r\n \"]/g,"");
		key = key.replace(/(^\s*)|(\s*$)/g,"");

		return key;
	}
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="init();">
	<%@ include file="./include/header.jsp"%>
	<link href="<%=static_contextpath%>css/text.css" rel="stylesheet"
		type="text/css">
	<html:form action="/webapp/jccswh/zjjmsdmwhAction.do" method="POST">
		<html:hidden property="actionType" />
		<html:hidden property="allNewAddInfo" />
		
		<!--维护界面隐藏域 查询条件、分页信息-->
		<html:hidden property="query_jmslxdm"/>
		<html:hidden property="query_wh"/>
		<html:hidden property="query_szdm"/>
		<html:hidden property="query_jmszcqsnd"/>
		<html:hidden property="query_jmslxdldm"/>
		<html:hidden property="query_jmslxxldm"/>
		<html:hidden property="query_lrrqKS"/>
		<html:hidden property="query_lrrqJS"/>
		<html:hidden property="query_yxbs"/>
		
		<html:hidden property="updateType"/>
		<html:hidden property="modifyKey_jmslxdm"/><!-- 修改主键 -->
		<html:hidden property="pageSize"/>
		<html:hidden property="nextPage"/>
		<html:hidden property="totalpage"/>
	
		<table align="center" cellspacing="0" class="table-100" style="width: 100%" onkeydown="moveFocus()">
			<tr>
				<td class="1-td1" colspan=4 valign="top">总局减免税代码录入</td>
			</tr>
			<tr>
				<td class="1-td2" style="width: 100%">
					<table style="width: 100%;" cellspacing="0" class="table-99">
						<tr>
							<td colspan="8">
								<table style="width: 100%;" cellspacing="0" class="table-99" id="sglrTable">
									<tr style="display='none'"></tr>
									<tr>
										<td class="2-td2-left"  nowrap="nowrap">税种</td>
										<td class="2-td2-left"  nowrap="nowrap">减免性质大类</td>
										<td class="2-td2-left"  nowrap="nowrap">减免性质小类</td>
										<td class="2-td2-left"  nowrap="nowrap">减免税类型代码</td>
										<td class="2-td2-left"  nowrap="nowrap">减免税类型名称</td>
										<td class="2-td2-left"  nowrap="nowrap">起始年度</td>
										<td class="2-td2-left"  nowrap="nowrap">文号</td>
										<td class="2-td2-left"  nowrap="nowrap">备注</td>
										<td class="2-td2-center"nowrap="nowrap">选择</td>
									</tr>
									<logic:notPresent name="zjjmsdmwhForm" property="modifyKey_jmslxdm">
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
															onClick="removeLabelCol('sglrTable');"
															alt="删除所有选中行" style="cursor: hand"
															onMouseOver="MM_swapImage('del','','<%=static_contextpath%>images/shanchu2.jpg',1)"
															onMouseOut="MM_swapImgRestore();" id="del"
															src="<%=static_contextpath%>images/shanchu1.jpg" width="79"
															height="22">
													</div>
												</td>
											</tr>
									</logic:notPresent>
									<logic:present name="zjjmsdmwhForm" property="modifyKey_jmslxdm">
											<tr style="display='none'"></tr>
									</logic:present>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="10">
							<br>
							<br>
								<div align="center">
									<!-- doSubmitForm('Save') -->
								    <logic:equal name="zjjmsdmwhForm"  property="actionType" value="Save">
										<IMG alt="保存填写信息到系统" height=22 id=baocun name=baocun
											onclick="doSubmit('Save','sglrTable','allNewAddInfo')"
											onmouseout=MM_swapImgRestore()
											onmouseover="MM_swapImage('baocun','','<%=static_contextpath%>images/baocun2.jpg',1)"
											src="<%=static_contextpath%>images/baocun1.jpg"
											style='CURSOR: hand' width=79> 
									</logic:equal>
									
									<logic:equal name="zjjmsdmwhForm"  property="actionType" value="Show">
										<IMG alt="保存填写信息到系统" height=22 id=baocun name=baocun
											onclick="doSubmit('Save','sglrTable','allNewAddInfo')"
											onmouseout=MM_swapImgRestore()
											onmouseover="MM_swapImage('baocun','','<%=static_contextpath%>images/baocun2.jpg',1)"
											src="<%=static_contextpath%>images/baocun1.jpg"
											style='CURSOR: hand' width=79>
									</logic:equal>
									
									<logic:notEqual name="zjjmsdmwhForm"  property="actionType" value="Save">
										<logic:notEqual name="zjjmsdmwhForm"  property="actionType" value="Show">
											<logic:present name="zjjmsdmwhForm" property="modifyKey_jmslxdm">
												<IMG alt=保存修改信息  height=22 id=b-bcbg name=b-bcbg 
												     onclick="doSubmit('Update','sglrTable','allNewAddInfo')" 
												     onmouseout=MM_swapImgRestore() 
												     onmouseover="MM_swapImage('b-bcbg','','<%=static_contextpath%>images/b-bcbg2.jpg',1)" 
												     src="<%=static_contextpath%>images/b-bcbg1.jpg" 
												     style='CURSOR: hand' width=79>
											</logic:present>
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
										<logic:notPresent name="zjjmsdmwhForm" property="modifyKey_jmslxdm">
											<IMG alt="跳转到减免税录入维护操作界面" height=22 id=weih name=weih
												onclick="doSubmit('ToWeiHu','','')"
												onmouseout=MM_swapImgRestore()
												onmouseover="MM_swapImage('weih','','<%=static_contextpath%>images/b-wh2.jpg',1)"
												src="<%=static_contextpath%>images/b-wh1.jpg"
												style='CURSOR: hand' width=79>
										</logic:notPresent>
								</div>
								<br>
								<br>
							</td>
						</tr>
						<tr>
							<td class="2-td2-t-left">录入人</td>
							<td class="2-td2-t-left"><div align="left">
									<bean:write name="zjjmsdmwhForm" property="lrrmc" />
								</div></td>
							<td class="2-td2-t-left">录入日期</td>
							<td class="2-td2-t-center"><div align="left">
									<bean:write name="zjjmsdmwhForm" property="lrrq" />
								</div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table style="display: none">
			<tr id="labelsample" height="24">
				<td class=2-td2-left width="5%">
				<select id="sglr_select_zssmdm" onchange="saveDefaulValues(this.value,'szdm')" style="width=100px">
						<logic:iterate id="item" name="zjjmsdmwhForm" property="smsmList"
							indexId="index">
							<option value="<bean:write name="item" property="szsmdm"/>">
								<bean:write name="item" property="szsmmc" />
							</option>
						</logic:iterate>
				</select></td>
				<td class=2-td2-left width="5%">
				<select id="sglr_select_jmxzdm_dl" onchange="doFilterJmxzxldm_byDLDM(this);saveDefaulValues(this.value,'jmxzdl')" style="width=100px">
						<logic:iterate id="item" name="zjjmsdmwhForm" property="jmxzdlList"	indexId="index">
							<option value="<bean:write name="item" property="jmxzdm"/>">
								<bean:write name="item" property="jmxzmc" />
							</option>
						</logic:iterate>
				</select></td>
				<td class=2-td2-left width="5%">
					<select id="sglr_select_jmxzdm_xl" onchange="saveDefaulValues(this.value,'jmxzxl')" style="width=100px">
							<logic:iterate id="item" name="zjjmsdmwhForm" property="jmxzxlList"
								indexId="index">
								<option value="<bean:write name="item" property="jmxzdm"/>">
									<bean:write name="item" property="jmxzmc" />
								</option>
							</logic:iterate>
					</select></td>
				<td class=2-td2-left width="3%"><input id="sglr_jmslxdm"type="text" style="width=100%"></td>
				<td class=2-td2-left width="15%"><textarea id="sglr_jmslxmc" rows="3" style="width=100%"></textarea></td>
				<td class=2-td2-left width="3%"><input id="sglr_jmszcqsnd" type="text" style="width=60px"></td>
				<td class=2-td2-left width="5%"><input id="sglr_wh" type="text"></td>
				<td class=2-td2-left width="10%"><textarea id="sglr_bz" rows="3" style="width=100%"></textarea></td>
				<td class=2-td2-center align="center" width="1%"><input type="checkbox" name="labelFlag" id="labelFlag" value=""></td>
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