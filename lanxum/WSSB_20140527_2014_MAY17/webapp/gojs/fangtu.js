/**
  �л���ͬҵ��ҳ��
*/
function fw_ziyong() {
	if ( confirm("��ȷ��Ҫ�ύ��ҳ������") ) {
		$("destCat").value = "1";
		doSave();
	}
}
function fw_chuzu() {
	if ( confirm("��ȷ��Ҫ�ύ��ҳ������") ) {
		$("destCat").value = "2";
		doSave();
	}
}
function fw_chengzu() {
	if ( confirm("��ȷ��Ҫ�ύ��ҳ������") ) {
		$("destCat").value = "3";
		doSave();
	}
}
function td_ziyong() {
	if ( confirm("��ȷ��Ҫ�ύ��ҳ������") ) {
		$("destCat").value = "4";
		doSave();
	}
}
function td_chuzu() {
	if ( confirm("��ȷ��Ҫ�ύ��ҳ������") ) {
		$("destCat").value = "5";
		doSave();
	}
}
function td_zhengzu() {
	if ( confirm("��ȷ��Ҫ�ύ��ҳ������") ) {
		$("destCat").value = "6";
		doSave();
	}
}

//���ص����˵�
function doReturn(){
    document.forms[0].actionType.value = "Return";
    document.forms[0].submit();
}

//��ѯ��˰��(�����û�ʹ�ô˷����������м���������ʽУ��)
function queryTaxpayer() {
	document.forms[0].actionType.value = "Show";
	document.forms[0].submit();
}

//��ѯ��˰�ˣ��ڲ��û�ʹ�ô˷��������м���������ʽУ�飩
function queryTaxpayer_SM() {
	if (! checkJsjdm(document.forms[0].jsjdm.value)) 
	{
		alert("����������ʽ����!!");
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
    var flagConfirm = confirm("ȷ��ɾ����ǰ��¼��");
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
		alert("У�����");
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
			alert("����δ��������ݣ����ȱ����ٸ��ˣ�");
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

//������ڸ�ʽ, ����canNull�����ж��Ƿ����ڿ���Ϊ��
function ft_checkDate( _date, _canNull) {
	if ( _canNull ) {
		if ( _date == "" )
			return true;
	}
		
	return checkDate( _date );
	
}

//��֤���ڷ�Χ�Ľ������ڱ������ڿ�ʼ����
function ft_checkDateB( _beginDate, _endDate ){
	if(parseFloat(_beginDate) > parseFloat(_endDate)) return false;
	return true;
}

//check ����˰������, ����˰����ֹ ����
function checkJMDate( cur_number, row_msg) {
	if ( ! ft_checkDate( $F("list["+cur_number+"].alterVO.jmsqxq"), true) ) {
		alert("��"+ row_msg +"�� ����˰���������ڸ�ʽ����[yyyymmdd]");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.jmsqxq");
		focus_it[0].focus();
		return false;
	}
	if ( ! ft_checkDate( $F("list["+cur_number+"].alterVO.jmsqxz"), true) ) {
		alert("��"+ row_msg +"�� ����˰����ֹ���ڸ�ʽ����[yyyymmdd]");
		var focus_it = document.getElementsByName("list["+cur_number+"].alterVO.jmsqxz");
		focus_it[0].focus();
		return false;
	}
	if ( $F("list["+cur_number+"].alterVO.jmsqxq") != "" && $F("list["+cur_number+"].alterVO.jmsqxz")!= "" ) {
		if ( ! ft_checkDateB( $F("list["+cur_number+"].alterVO.jmsqxq"), $F("list["+cur_number+"].alterVO.jmsqxz") ) ) {
			alert("��"+ row_msg +"�� ����˰�����������ڼ���˰����ֹ");
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