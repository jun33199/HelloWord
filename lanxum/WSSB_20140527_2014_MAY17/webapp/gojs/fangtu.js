/**
  切换不同业务页面
*/
function fw_ziyong() {
	if ( confirm("您确定要提交本页数据吗？") ) {
		$("destCat").value = "1";
		doSave();
	}
}
function fw_chuzu() {
	if ( confirm("您确定要提交本页数据吗？") ) {
		$("destCat").value = "2";
		doSave();
	}
}
function fw_chengzu() {
	if ( confirm("您确定要提交本页数据吗？") ) {
		$("destCat").value = "3";
		doSave();
	}
}
function td_ziyong() {
	if ( confirm("您确定要提交本页数据吗？") ) {
		$("destCat").value = "4";
		doSave();
	}
}
function td_chuzu() {
	if ( confirm("您确定要提交本页数据吗？") ) {
		$("destCat").value = "5";
		doSave();
	}
}
function td_zhengzu() {
	if ( confirm("您确定要提交本页数据吗？") ) {
		$("destCat").value = "6";
		doSave();
	}
}

//返回到主菜单
function doReturn(){
    document.forms[0].actionType.value = "Return";
    document.forms[0].submit();
}

//查询纳税人(网上用户使用此方法，不进行计算机代码格式校验)
function queryTaxpayer() {
	document.forms[0].actionType.value = "Show";
	document.forms[0].submit();
}

//查询纳税人（内部用户使用此方法，进行计算机代码格式校验）
function queryTaxpayer_SM() {
	if (! checkJsjdm(document.forms[0].jsjdm.value)) 
	{
		alert("计算机代码格式错误!!");
		return;
	} else {
		document.forms[0].actionType.value = "Show";
		document.forms[0].submit();
	}
}

function add(handler)
{
    yhlistlent = yhlistlent + 1;

    var oTable = eval(handler);

    oTable.appendRow();
    oTable.focusAt(oTable.getCurrentRow(), 1);
}

function deleteRow(handler)
{
    yhlistlent = yhlistlent - 1;
    var oTable = eval(handler);

    var dataSource_del = oTable.getData();
    var numRow_del = dataSource_del.length;
    if (numRow_del == 0) {
        yhlistlent = 0;
        return;
    }
    var flagConfirm = confirm("确认删除当前纪录！");
    if (flagConfirm == true) {
        oTable.deleteRow(oTable.DynamicTable.CurrentRow);
    }
}

function first(handler)
{
    var oTable = eval(handler);
    oTable.firstRow();
}

function previous(handler)
{
    var oTable = eval(handler);
    oTable.previousRow();
}

function next(handler)
{
    var oTable = eval(handler);
    oTable.nextRow();
}

function lastRow(handler)
{
    var oTable = eval(handler);
    oTable.lastRow();
}

function doSave()
{
	daList.saveAllData();
		
	var isCheck = DataSourceCheck(daList);
	if (!isCheck) {
		alert("校验错误。");
		return;
	}

		
	document.forms[0].dataSource_gm.value = daList.formatDataWithDelete( constRowSeparator,constColSeparator);
	//alert( document.forms[0].dataSource_gm.value );

	$("actionType").value = "Show";

	document.forms[0].submit();		
}

function modify()
{
	daList.modifyRow( daList.DynamicTable.CurrentRow);
}

function doAudit()
{
	daList.saveAllData();

	var data = daList.getData();
	for (var i = 0; i < data.length;i++)
	{
		var j = data[i].length;
		if(data[i][j-1] ==1 || data[i][j-1] == 2  || data[i][j-1] == 3)
		{
			alert("您有未保存的数据，请先保存再复核！");
			return;
		}
	}
		
	document.forms[0].dataSource_gm.value = daList.formatData2( constRowSeparator,constColSeparator);

	$("actionType").value = "Audit";

	document.forms[0].submit();		
}

function doPrint(actiontype)
{
	$("regPrint").target  = "_blank";
	$("regPrint").submit();	

	//document.forms[0].target = "_blank";
	//document.forms[0].action = "fangtuPrintAction.do";
	//$("actionType").value = actiontype;
	//document.forms[0].submit();	
	//document.forms[0].action = "fangtu.do";
}

//检查日期格式, 根据canNull参数判断是否日期可以为空
function ft_checkDate( _date, _canNull) {
	if ( _canNull ) {
		if ( _date == "" )
			return true;
	}
		
	return checkDate( _date );
	
}

//验证日期范围的截至日期必须晚于开始日期
function ft_checkDateB( _beginDate, _endDate ){
	if(parseFloat(_beginDate) > parseFloat(_endDate)) return false;
	return true;
}

//check 减免税期限起, 减免税期限止 日期
function checkJMDate( cur_number, row_msg) {
	if ( ! ft_checkDate( $F("list["+cur_number+"].alterVO.jmsqxq"), true) ) {
		alert("第"+ row_msg +"行 减免税期限起日期格式错误[yyyymmdd]");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.jmsqxq");
		focus_it[0].focus();
		return false;
	}
	if ( ! ft_checkDate( $F("list["+cur_number+"].alterVO.jmsqxz"), true) ) {
		alert("第"+ row_msg +"行 减免税期限止日期格式错误[yyyymmdd]");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.jmsqxz");
		focus_it[0].focus();
		return false;
	}
	if ( $F("list["+cur_number+"].alterVO.jmsqxq") != "" && $F("list["+cur_number+"].alterVO.jmsqxz")!= "" ) {
		if ( ! ft_checkDateB( $F("list["+cur_number+"].alterVO.jmsqxq"), $F("list["+cur_number+"].alterVO.jmsqxz") ) ) {
			alert("第"+ row_msg +"行 减免税期限起不能晚于减免税期限止");
			var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.jmsqxz");
			focus_it[0].focus();
			return false;
		}
	}
	return true;	
}

function remarkIsEmpty(dataSource, i, remarkPosition ) {

	if ( getStringLength(trimString(dataSource[i][remarkPosition]) ) == 0 ) {
		return true;
	}
	return false;
	
}