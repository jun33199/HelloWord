<%@ page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import ="com.ttsoft.bjtax.smsb.sgsswszmlr.common.Constant" %>

<html>
<head>
<title>�ֹ�¼��˰����˰֤��</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<META content="text/html; charset=GBK" http-equiv=Content-Type>
<script language=JavaScript src="../js/DynamicTable.js"
	type="text/javascript"></script>
<script language=JavaScript src="../js/function.js"></script>

<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script type="text/javascript">
	var title_rows_count = 2;//ָ sglrTable ���̶�������ռ������
	var bottom_rows_count = 2;//ָ sglrTable ���̶�ĩ����ռ������
	/**
	 * ���ƷĿ����
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
	 * ����ϼƽ��
	 *		obj:��ǰʵ�ɽ����������
	 *		table_name����ǰtable����
	 *		xxhj_div_ID��Сд�����ʾdiv ��ID
	 *      dxhj_div_ID����д�����ʾdiv ��ID
	 */
	var sjjeHJ = 0.00;
	function setHJJE(obj, table_name, xxhj_div_ID, dxhj_div_ID) {
		//У�鵱ǰ�������Ƿ�ϸ񣬲��ϸ��򷵻ز�ѡ��
		var sjje = obj.value;
		if (sjje == "" || parseFloat(sjje) == "NaN"
				|| !checkNumber(sjje, 15, 2, true)) {
			alert("����Ϊ���֣�\nС�����ĳ���Ϊ2λ,��󳤶�Ϊ15λ��\n������ֵ����ڵ����㣡");
			obj.select();
			return false;
		} else {
			obj.value = changeJE(sjje);
		}

		//�ϸ�����Ӽ���
		ReCountAll(table_name, xxhj_div_ID, dxhj_div_ID);
		return false;
	}

	//���½��м���ϼƽ��
	function ReCountAll(table_name, xxhj_div_ID, dxhj_div_ID) {
		sjjeHJ = 0.00;//���úϼƽ��
		var table_obj = document.getElementById(table_name);
		for ( var i = 2; i < table_obj.rows.length - 2; i++) {
			var nodeOBJ = table_obj.rows[i].cells[table_obj.rows[i].cells.length - 2].childNodes[0];
			var nodeOBJ_value = nodeOBJ.value;
			if (nodeOBJ_value == "" || parseFloat(nodeOBJ_value) == "NaN"
					|| !checkNumber(nodeOBJ_value, 15, 2, true)) {
				nodeOBJ_value = 0.00;
			}
			//���м���
			sjjeHJ = changeJE(Math
					.round((parseFloat(sjjeHJ) + parseFloat(nodeOBJ_value)) * 100.00) / 100.00);
		}

		//�����������ʾ������
		doShowCaculate(sjjeHJ, xxhj_div_ID, dxhj_div_ID);

	}
	
	/**
	*��ʾ������
	*/
	function doShowCaculate(sjjeHJ, xxhj_div_ID, dxhj_div_ID){
		document.getElementById(xxhj_div_ID).innerHTML = sjjeHJ;//Сд
		document.forms[0].sjjeHJ.value=sjjeHJ;//���õ�������
		//��д
		if (dxhj_div_ID != "" && dxhj_div_ID != "0.00" && dxhj_div_ID != 0) {
			document.getElementById(dxhj_div_ID).innerHTML = "<font color=\"red\">����д��</font>"
					+ getChineseMoney(formatNumber(sjjeHJ));
		} else {
			document.getElementById(dxhj_div_ID).innerHTML = "<font color=\"red\">����д��</font>";
		}
	}
	
	
	

	//������ʱ�ж��Ƿ�ǰN������û��ĵ�Ԫ��������������������д���
	function doAddOneRow(table_id, trId) {

		//����ǰ�ж��Ƿ���δ��д���߸�ʽ���Ե�����
		if (!checkAllDate(table_id,"newAdd")) {
			return false;
		}

		//ִ����������
		addLabelCol(trId, bottom_rows_count);
	}

	/*
	 * �ύ��Ϣ
	 */
	function doSubmit(actionType, tableID, targetID) {
		document.forms[0].target="";
		//����
		if(actionType=="Save" || actionType=="Update"){
			
			if(actionType=="Update"){
				var dybz = '<bean:write name="sgsswszmlrForm" property="dybz"/>';
				var cjwszmBYothers = '<bean:write name="sgsswszmlrForm" property="cjwszmBYothers"/>';
				//�Ƿ��Ѿ���ӡ
				if(dybz != "" && dybz == '<%=Constant.CONS_SGLR_DYBZ_1%>'){
					alert("��֤���Ѿ���ӡ�������޸ģ�");
					return false;
				}
				
				//�Ƿ񱻳��߹���˰֤��
				if(cjwszmBYothers != "" && cjwszmBYothers == '<%=Constant.CONS_SGLR_CJWSZM_BY_OTHERS_Y%>'){
					alert("��֤���Ѿ�������ģ��ʹ�ò���������Ч��˰֤�������ܽ����޸ģ���ȷ�ϣ�");
					return false;
				}
			}
			
			//�жϱ������Ƿ��Ѿ���д
			var nsrsbhOBJ = document.forms[0].nsrsbh;
			var nsrmcOBJ = document.forms[0].nsrmc;
			
			//�ж���˰��ʶ����Ƿ�Ϊ��
			if(nsrsbhOBJ.value == null || nsrsbhOBJ.value ==""){
				alert("��˰��ʶ���Ϊ���������Ϊ�գ�");
				nsrsbhOBJ.focus();
				return false;
			}
			
			//�ж���˰�������Ƿ�Ϊ��
			if(nsrmcOBJ.value == null || nsrmcOBJ.value ==""){
				alert("��˰������Ϊ���������Ϊ�գ�");
				nsrmcOBJ.focus();
				return false;
			}
			
			//�ж϶�̬���Ƿ���������Ϣ
			var table_obj = document.getElementById(tableID);
			var table_rows_len = table_obj.rows.length;
			if(table_rows_len == 4){
				alert("δ¼����˰֤����Ϣ�����ܱ��棡");			
				return false;
			}
			
			//��ע�ֶ���������70��
			var bzObj = document.forms[0].bz;
			var bzValue = bzObj.value;
			if(bzValue != "" && bzValue.length > 70){
				alert("��ע��Ϣ���ֻ��¼��70���֣����ѳ����������"+(bzValue.length-70)+"���֣���ȷ�ϣ�");
				return false;
			}
			
			//�ύǰ�ж��Ƿ���δ��д���߸�ʽ���Ե�����
			if (!checkAllDate(tableID,"save")) {
				return false;
			}
			
	
			//��ö�̬����е�����
			getTableXXContext(tableID, title_rows_count, bottom_rows_count, targetID);
	
			if (confirm("�Ƿ�������Ϣ���浽ϵͳ����ȷ�ϣ�")) {
				document.forms[0].actionType.value = actionType;
				document.forms[0].submit();
				return false;
			} else {
				return false;
			}
		}
		
		//�ύ
			document.forms[0].actionType.value = actionType;
			document.forms[0].submit();
	}

	//�ύ��У��
	function checkAllDate(table_name,actionType) {
		var table_obj = document.getElementById(table_name);
		var table_rows_len = table_obj.rows.length;
		for ( var i = 2; i < table_rows_len - 2; i++) {
			for ( var j = 0; j < table_obj.rows[i].cells.length - 1; j++) {
				var tempVal = table_obj.rows[i].cells[j].childNodes[0];
				var tempTagName = tempVal.tagName;
				if (tempTagName != "DIV" && tempVal.value == "") {
					alert("��Ϣ��д����ȷ������δ��д��Ϣ�");
					tempVal.focus();
					return false;
				}

				//��Ϊ�գ����������Ƿ���ϸ�ʽҪ��
				if (tempTagName != "DIV" && checkDate(tempVal) == false) {
					return false;
				}
			}
		}
		
		//ֻ��¼��5�У������й̶���һ���ñ��������Ϊ9��
		if(table_rows_len == 9 && actionType == "newAdd"){
			alert("�ñ����ֻ��������5�У����Ѿ��ﵽ�������������ȷ�ϣ�");
			return false;
		}
		
		return true;
	}

	//�����Ϣ���Ƿ�������ȷ
	function checkDate(obj) {
		//�Ƿ���ͨ��
		var pass = false;

		//��õ�ǰ�����id
		var objID = obj.id;
		if (objID == "") {
			alert("ҳ�淢���������˳�ҳ�����½���¼��!");
			return pass;
		}

		//�����ֵ
		var objVALUE = obj.value;

		if (objVALUE == "") {
			alert("����д��Ϣ��");
			obj.focus();
			return pass;
		}

		//ԭƾ֤��
		if (objID == "sglr_old_pzh") {
			if (objVALUE.length != 0 && objVALUE.length > 30) {
				alert("ԭƾ֤��¼��λ������������ܳ���30λ�����飡");
				obj.select();
				return pass;
			}
		}
		//˰��
		if (objID == "sglr_select_zssmdm") {
			if (objVALUE == "") {
				alert("��ѡ���Ӧ˰�֣�");
				obj.select();
				return pass;
			}
		}

		//ƷĿ����

		//˰�������� -��
		if (objID == "sglr_skssksrq") {
			if (isFullDate(objVALUE, 0, 'noempty', true) == false) {
				obj.select();
				return pass;
			}
		}

		//˰��������-ֹ
		if (objID == "sglr_skssjsrq") {
			if (isFullDate(objVALUE, 0, 'noempty', true) == false) {
				obj.select();
				return pass;
			}
		}

		//�루�ˣ�������
		if (objID == "sglr_rk_tkrq") {
			if (isFullDate(objVALUE, 0, 'noempty', true) == false) {
				obj.select();
				return pass;
			}
		}

		//ʵ�ɣ��ˣ����
		if (objID == "sglr_sjje") {
			if (checkNumber(objVALUE, 15, 2, true) == false) {
				alert("����Ϊ���֣�\nС�����ĳ���Ϊ2λ,��󳤶�Ϊ15λ��\n������ֵ����ڵ����㣡");
				obj.select();
				return pass;
			}
		}

		pass = true;
		return pass;
	}

	//����ʽ
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

	//function  : ��������ַ����Ƿ����������
	//parameters: DigitString: String,������ַ�����ֵ
	//			  totalLength: int, ���ֵĳ������ƣ�������С���㣩
	//			  decimalLength: int��С���ĳ�������
	//           totalLength-decimalLength �������ֵ�����
	//           isPositive �Ƿ�Ϊ�Ǹ��� true��ʾһ��Ҫ�ǷǸ�����false��ʾһ��Ҫ�Ǹ�����null��ʾ����
	//return    : false: �ַ����а�������������ַ�
	//			  true : else
	function checkNumber(DigitString, totalLength, decimalLength, isPositive) {
		var re = "";

		if (isPositive == "true" || isPositive == true) {//����ǷǸ���
			if (isNaN(DigitString * 1) || DigitString * 1 < 0)
				return false;
		}
		if (isPositive == "false" || isPositive == false) {//����Ǹ���
			if (isNaN(DigitString * 1) || DigitString * 1 >= 0)
				return false;
		}

		if (isNaN(DigitString)) {
			return false;
		}
		if (decimalLength != null && decimalLength != 0) { //С����Ϊ��
			re = re + "\\.[\\d]{1," + decimalLength + "}"
		}

		if (totalLength == null) { //δ�趨�ܳ���
			re = "\\d*" + re;
		} else { //�趨�ܳ���
			//��С������Ϊ�յ�����£������ж����ֵĳ���
			var intergerLength = totalLength;
			if (decimalLength != null) { //��С�����ֲ�Ϊ���жϣ�������λ����С����λ���Ƿ���ȷ
				intergerLength = totalLength - decimalLength;
			}
			re = "([\\d]{0," + intergerLength + "}" + re + "|[\\d]{0,"
					+ intergerLength + "})";
		}
		re = new RegExp("^[-|+]?" + re + "$", "g");

		//���ַ���Ϊ�ա�λ�����ԡ�����ƥ�������ʱ
		if (re.exec(DigitString) == null) {
			return false;
		}
		return true;
	}
	
	//�ûس�ʱ�ƶ����㣨����������������⣩
	function moveFocus(){
		if(event.keyCode==13) event.keyCode = 9;
	}
</script>
<script type="text/javascript">
	//�����������֮�󷵻�ʱ����������Ϣ
	function parseSglrmxInfo(sglrmx, tableId, hidPropertyObj) {
		if (sglrmx != null && sglrmx != "") {
			if (sglrmx.indexOf("^") > 0) {
				var infoArr = sglrmx.split("^");

				for ( var index = 0; index < infoArr.length; index++) {
					var tempInfo = infoArr[index];
					//���ý�������������ÿ������������Ϣ����ʾ
					parseOneGoup(tempInfo, tableId, hidPropertyObj);
				}
			} else {
				parseOneGoup(sglrmx, tableId, hidPropertyObj);
			}
		}

	}

	//��������˫����Ϣ
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
			//alert("��Ϣ����"+count);

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
					oneGroupInfo.push(value_2);//֤�����ʹ���
					oneGroupInfo.push(value_3);
					oneGroupInfo.push(value_4);
					oneGroupInfo.push(value_5);

					//alert("RETURN::"+DSzjdm);

					//���ò���ʾÿ����Ϣ
					isSuccess = showOneGroupInfo(oneGroupInfo, tableobj);

					if (!isSuccess) {
						alert("��ʾ�ֹ�¼����Ϣ����");
						return isSuccess;
					}

					//ƴ���ύ����̨����Ϣ
					var tempOneGroupValue = value_0 + "~" + value_1 + "~"
							+ value_2 + "~" + value_3 + "~" + value_4 + "~"
							+ value_5;
					if (zsIndex == 0) {
						submitInfo = tempOneGroupValue;
					} else {
						submitInfo = submitInfo + "^" + tempOneGroupValue;
					}
					//������Ϣ
					oneGroupInfo = new Array();
				}
				//�����ύ�������ֵ
				if (hidPropertyObj != null && hidPropertyObj != "") {
					hidPropertyObj.value = submitInfo;
				}
			}
		}

		return true;
	}

	/*
	 *desc ���ݲ�ͬ�������Ԫ������¼�
	 *parma object obj(Ҫ����¼��Ķ���)
	 *parma string eventname(Ҫ��ӵ��¼���)
	 *parma string callback(�ص�������)
	 *return void
	 */
	var addEvent = function(obj, eventname, callback) {

		//���������
		if (window.addEventListener) {
			obj.addEventListener(eventname, callback, false);
		}
		//ie
		else {
			obj.attachEvent('on' + eventname, callback);
		}

		return;

	}

	//��ÿ����Ϣ���õ�td��innerHTML����ʾ
	function showOneGroupInfo(arr, tableobj) {

		if (tableobj == null || tableobj == "") {
			return false;
		}

		//�½���
		var tabLength = tableobj.rows.length;//��������һ��rows����
		var otr_n = tableobj.insertRow(tabLength - bottom_rows_count);

		//��n(n>=2)�е�һ�� 
		var sglr_old_pzh_Cell = otr_n.insertCell();
		sglr_old_pzh_Cell.className = "2-td2-left";
		fnAddInput(sglr_old_pzh_Cell, arr[0], "sglr_old_pzh", "text");

		//��n(n>=2)�еڶ���  
		var sglr_select_zssmdm_Cell = otr_n.insertCell();
		sglr_select_zssmdm_Cell.className = "2-td2-left";
		var valuesArr = getSzsmArr();
		//sglr_select_zssmdm_Cell.onchange=getPmmc(sglr_select_zssmdm_Cell);
		var select_oNewItem = fnAddSelect(sglr_select_zssmdm_Cell,
				"sglr_select_zssmdm", valuesArr, arr[1]);

		//��n(n>=2)�е�����
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

		//��n(n>=2)�е����� 
		var sglr_skssksrq_Cell = otr_n.insertCell();
		sglr_skssksrq_Cell.className = "2-td2-left";
		fnAddInput(sglr_skssksrq_Cell, arr[2], "sglr_skssksrq", "text");

		//��n(n>=2)�е����� 
		var upperline_Cell = otr_n.insertCell();
		upperline_Cell.className = "2-td2-left";
		upperline_Cell.innerHTML = "<div align=\"center\">-</div>";

		//��n(n>=2)�е����� 
		var sglr_skssjsrq_Cell = otr_n.insertCell();
		sglr_skssjsrq_Cell.className = "2-td2-left";
		fnAddInput(sglr_skssjsrq_Cell, arr[3], "sglr_skssjsrq", "text");

		//��n(n>=2)�е����� 
		var sglr_rk_tkrq_Cell = otr_n.insertCell();
		sglr_rk_tkrq_Cell.className = "2-td2-left";
		fnAddInput(sglr_rk_tkrq_Cell, arr[4], "sglr_rk_tkrq", "text");

		//��n(n>=2)�еڰ��� 
		var sglr_sjje_Cell = otr_n.insertCell();
		sglr_sjje_Cell.className = "2-td2-left";
		fnAddInput(sglr_sjje_Cell, arr[5], "sglr_sjje", "text");

		//��n(n>=2)�еھ��� 
		var checkbox_value_Cell = otr_n.insertCell();
		checkbox_value_Cell.className = "2-td2-center";
		fnAddInput(checkbox_value_Cell, arr[6], "labelFlag", "checkbox");

		return true;
	}

	//��ָ��Ԫ���´���һ��imput�����
	function fnAddInput(cellObj, newItemValue, newItemId, inputType) {
		var oNewItem = document.createElement("INPUT");
		//����������
		if (inputType == "text") {
			oNewItem.value = newItemValue;
			//����ʱ��У�麯��
			if (newItemId == "sglr_skssksrq" || newItemId == "sglr_skssjsrq"
					|| newItemId == "sglr_rk_tkrq") {
				addEvent(oNewItem, 'change', function() {
					checkDate(oNewItem)
				});
			}
			//���úϼ�
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

	//��ָ��Ԫ���´���һ��select������
	function fnAddSelect(cellObj, newItemId, valuesArr, value) {

		//����һ��select��ǩ
		var oNewItem = document.createElement("SELECT");
		oNewItem.id = newItemId;

		for ( var index = 0; index < valuesArr.length; index++) {
			var oValueArr = valuesArr[index];
			var optionValue = oValueArr[0];//option��ֵ
			var optionName = oValueArr[1];//option������

			//����һ��option��ǩ
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
	 * ����һ��DIV
	 */
	function fnAddDIV(cellObj, newItemValue, newItemId) {
		var oNewItem = document.createElement("DIV");
		oNewItem.id = newItemId;
		oNewItem.innerHTML = newItemValue;
		cellObj.insertAdjacentElement("afterBegin", oNewItem);
	}

	//���˰ĿARR
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
		//������ϸ��Ϣ
		if(sglrmx !=""){
			parseSglrmxInfo(sglrmx,'sglrTable','wsxxAll');
		}
		
		//��ʼ���ϼƽ��
		var actionType = document.forms[0].actionType.value;
		if (actionType == "Show"){
			sjjeHJ = 0.00;
			doShowCaculate(sjjeHJ,'sjjeHJ_XX','sjjeHJ_DX');
		}else{
			sjjeHJ = changeJE('<bean:write name="sgsswszmlrForm"  property="sjjeHJ"/>');
			doShowCaculate(sjjeHJ,'sjjeHJ_XX','sjjeHJ_DX');
		}
		
		//��ʾ��ע��Ϣ
		var bz = '<bean:write name="sgsswszmlrForm"  property="bz"/>';
		if(bz !=""){
			bz = bz +"<br>��ӡ������0��TODO��";
			
		}
		
		//��ʾ��ʾ��Ϣ
		var message = '<bean:write name="sgsswszmlrForm" property="message" />';
		if(message!=""){
			alert(message);
		}else{
			if(actionType=="Save" || actionType=="Update"){
				if(saveIsSucc == '<%=Constant.CONS_SGLR_SAVEISSUCC_Y%>'){
					alert("����ɹ�!");
				}
				
				if(saveIsSucc == '<%=Constant.CONS_SGLR_SAVEISSUCC_N%>'){
						alert("����ʧ�ܣ�������!");
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
		
		<!-- ά������������ -->
		<html:hidden property="pageSize"/>
		<html:hidden property="nextPage"/>
		<html:hidden property="totalpage"/>
		<html:hidden property="query_nsrsbh"/>
		<html:hidden property="query_nsrmc"/>
		<html:hidden property="query_wspzh"/>
	
		<table align="center" cellspacing="0" class="table-100"
			style="width: 100%" onkeydown="moveFocus()">
			<tr>
				<td class="1-td1" colspan=4 valign="top">�ֹ�¼��˰����˰֤��</td>
			</tr>
			<tr>
				<td class="1-td2" style="width: 100%">
					<table style="width: 100%;" cellspacing="0" class="table-99">
						<tr>
							<td class="2-td2-t-left">�����</td>
							<td class="2-td2-t-left"><div align="left">
									<bean:write name="sgsswszmlrForm" property="tfrq" />
								</div></td>
							<td class="2-td2-t-left">˰�����</td>
							<td class="2-td2-t-center"><div align="left">
									<bean:write name="sgsswszmlrForm" property="swjgmc" />
								</div></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap="nowrap">��˰��ʶ���<font
								color="red">*</font></td>
							<td class="2-td2-left"><div align="left">
									<html:text name="sgsswszmlrForm" property="nsrsbh" size="30" maxlength="50"/>
								</div></td>
							<td class="2-td2-left" nowrap="nowrap">��˰������<font
								color="red">*</font></td>
							<td class="2-td2-center"><div align="left">
									<html:text name="sgsswszmlrForm" property="nsrmc" size="60" maxlength="200"/>
								</div></td>
						</tr>
						<tr>
							<td class="2-td2-left">¼��ƾ֤����<font color="red">*</font></td>
							<td class="2-td2-center" colspan="3"><div align="left">
									��˰ƾ֤
									<html:radio name="sgsswszmlrForm" property="pzlxdm" value="0"></html:radio>
									��˰ƾ֤
									<html:radio name="sgsswszmlrForm" property="pzlxdm" value="1"></html:radio>
								</div></td>
						</tr>
						<tr>
							<td colspan="8">
								<table style="width: 100%;" cellspacing="0" class="table-99"
									id="sglrTable">
									<tr>
										<td class="2-td2-left" rowspan="2" nowrap="nowrap">ԭƾ֤��</td>
										<td class="2-td2-left" rowspan="2" nowrap="nowrap">˰��</td>
										<td class="2-td2-left" rowspan="2" nowrap="nowrap">ƷĿ����</td>
										<td class="2-td2-left" colspan="3" nowrap="nowrap">˰��������</td>
										<td class="2-td2-left" rowspan="2" nowrap="nowrap">�루�ˣ�������</td>
										<td class="2-td2-left" rowspan="2" nowrap="nowrap">ʵ�ɣ��ˣ����</td>
										<td class="2-td2-center" rowspan="2" colspan="5" width="5%"
											nowrap="nowrap">����</td>
									</tr>
									<tr>
										<td class="2-td2-left">��</td>
										<td class="2-td2-left">-</td>
										<td class="2-td2-left">ֹ</td>
									</tr>
									<tr>
										<td colspan="13" align="center" class="2-td2-center">
											<div align="center">
												<img onClick="doAddOneRow('sglrTable','labelsample');"
													alt="����һ��" style="cursor: hand"
													onMouseOver="MM_swapImage('add','','<%=static_contextpath%>images/xinzeng2.jpg',1)"
													onMouseOut="MM_swapImgRestore()" id="add"
													src="<%=static_contextpath%>images/xinzeng1.jpg" width="79"
													height="22"> 
													
													<img
													onClick="removeLabelCol('sglrTable');ReCountAll('sglrTable','sjjeHJ_XX','sjjeHJ_DX')"
													alt="ɾ������ѡ����" style="cursor: hand"
													onMouseOver="MM_swapImage('del','','<%=static_contextpath%>images/shanchu2.jpg',1)"
													onMouseOut="MM_swapImgRestore();" id="del"
													src="<%=static_contextpath%>images/shanchu1.jpg" width="79"
													height="22">
											</div>
										</td>
									</tr>
									<tr>
										<td class="2-td2-left" width="10%">���ϼ�</td>
										<td class="2-td2-left" colspan="6"><div align="left"
												id="sjjeHJ_DX">
												<font color="red">����д��</font>
											</div></td>
										<td class="2-td2-center" width="10%" colspan="6"><div
												id="sjjeHJ_XX">&nbsp;</div></td>
									</tr>
								</table>
								<table style="width: 100%;" cellspacing="0" class="table-99">
									<tr>
										<td class="2-td2-left" rowspan="3" width="10%">��ע</td>
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
										<IMG alt="������д��Ϣ��ϵͳ" height=22 id=baocun name=baocun
											onclick="doSubmit('Save','sglrTable','wsxxAll')"
											onmouseout=MM_swapImgRestore()
											onmouseover="MM_swapImage('baocun','','<%=static_contextpath%>images/baocun2.jpg',1)"
											src="<%=static_contextpath%>images/baocun1.jpg"
											style='CURSOR: hand' width=79> 
									</logic:equal>
									
									<logic:equal name="sgsswszmlrForm"  property="actionType" value="Show">
										<IMG alt="������д��Ϣ��ϵͳ" height=22 id=baocun name=baocun
											onclick="doSubmit('Save','sglrTable','wsxxAll')"
											onmouseout=MM_swapImgRestore()
											onmouseover="MM_swapImage('baocun','','<%=static_contextpath%>images/baocun2.jpg',1)"
											src="<%=static_contextpath%>images/baocun1.jpg"
											style='CURSOR: hand' width=79> 
									</logic:equal>
									
									<logic:notEqual name="sgsswszmlrForm"  property="actionType" value="Save">
										<logic:notEqual name="sgsswszmlrForm"  property="actionType" value="Show">
											<logic:equal name="sgsswszmlrForm" property="yxbz" value="0">
												<IMG alt=�����޸���Ϣ  height=22 id=b-bcbg name=b-bcbg 
												     onclick="doSubmit('Update','sglrTable','wsxxAll')" 
												     onmouseout=MM_swapImgRestore() 
												     onmouseover="MM_swapImage('b-bcbg','','<%=static_contextpath%>images/b-bcbg2.jpg',1)" 
												     src="<%=static_contextpath%>images/b-bcbg1.jpg" 
												     style='CURSOR: hand' width=79>
											</logic:equal>
												<img
													src="<%=static_contextpath%>images/fanhui1.jpg" value="����"
													name="fanhui" width="79" height="22" border="0"
													onClick="doSubmit('Query','','');return false;"
													id="fanhui" alt="����"
													onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
													onMouseOut="MM_swapImgRestore()">
										</logic:notEqual>
									</logic:notEqual>
												
									<input type="image"
										onClick="tuichu();return false;"
										onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tuichu2.jpg',1)"
										alt="�˳����˵�ѡ�����" onMouseOut="MM_swapImgRestore()" id="tc1"
										src="<%=static_contextpath%>images/tuichu1.jpg" width="79"
										height="22" style="cursor: hand">
									
								</div>
								<br>
								<br>
							</td>
						</tr>
						<tr>
							<td class="2-td2-t-left">¼����</td>
							<td class="2-td2-t-left"><div align="left">
									<bean:write name="sgsswszmlrForm" property="lrrmc" />
								</div></td>
							<td class="2-td2-t-left">¼������</td>
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