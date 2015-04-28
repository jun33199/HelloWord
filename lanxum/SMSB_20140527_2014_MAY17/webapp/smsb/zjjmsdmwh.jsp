<%@ page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import ="com.ttsoft.bjtax.smsb.sgsswszmlr.common.Constant" %>
<%@page import ="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>

<html>
<head>
<title>�ּܾ���˰����¼��</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<META content="text/html; charset=GBK" http-equiv=Content-Type>
<script language=JavaScript src="../js/DynamicTable.js"
	type="text/javascript"></script>
<script language=JavaScript src="../js/function.js"></script>

<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script type="text/javascript">
	var title_rows_count=2;//ָ sglrTable ���̶�������ռ������
	var bottom_rows_count=1;//ָ sglrTable ���̶�ĩ����ռ������
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
	*ͨ������˰���ʴ���������С�����
	*/
	function doFilterJmxzxldm_byDLDM(obj){
		//��ǰѡ������ֵ
		var select_dldm= obj.value;
		
		//��õ�ǰ�е�����td�����±�Ϊ2
		var jmxzdm_xl_td_Obj = obj.parentNode.parentNode.cells[2];
		
		//��õ�ǰ�м�������С��
		var jmxzxlObj = jmxzdm_xl_td_Obj.childNodes[0];
		//ɾ����ǰС��select��ǩ����
		jmxzdm_xl_td_Obj.removeChild(jmxzxlObj);
		//����ѡ�������������С�������˵�
		var valuesArr_jmxz_xl = getJmxz_dl_xl_Arr("jmxz_xl",select_dldm);
		select_oNewItem = fnAddSelect(jmxzdm_xl_td_Obj,"sglr_select_jmxzdm_xl", valuesArr_jmxz_xl, "");
		//alert(jmxzxlObj.id);
	}
	
	
	//�����ϴ�ѡ�е�˰�ִ����Լ�������롢С�����
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
		//����ǰ�����е�˰�֡�������ࡢ����С���ó��ϴ�ѡ���
		var table_obj = document.all(table_id);
		if(table_obj){
			var tableChildsArr = table_obj.rows;//��õ�ǰ���������
			//���������
			if(tableChildsArr && tableChildsArr.length >0){
				var newAddTR_OBJ = tableChildsArr[tableChildsArr.length -2];//
				if(newAddTR_OBJ){
					//ѡ��˰�֡�������ࡢС��
					var newAddTR_child_OBJs = newAddTR_OBJ.childNodes;//��������е�����TD����
					if(newAddTR_child_OBJs){
						newAddTR_child_OBJs[0].childNodes[0].value=default_SZDM;//ѡ��˰��
						newAddTR_child_OBJs[1].childNodes[0].value=default_DLDM;//ѡ�д���
						//���С��
						doFilterJmxzxldm_byDLDM(newAddTR_child_OBJs[1].childNodes[0]);
						if(default_XLDM != ""){
							newAddTR_child_OBJs[2].childNodes[0].value=default_XLDM;//ѡ��С��
						}
					}
					
				}
			}
			
		}
	}
	
	//������ʱ�ж��Ƿ�ǰN������û��ĵ�Ԫ��������������������д���
	function doAddOneRow(table_id, trId) {

		//����ǰ�ж��Ƿ���δ��д���߸�ʽ���Ե�����
		if (!checkAllDate(table_id,"newAdd")) {
			return false;
		}

		//ִ����������
		addLabelCol(trId,bottom_rows_count);
		
		//��������������ֵ
		doSelecedDefault(table_id);
	}
	

	/*
	 * �ύ��Ϣ
	 */
	function doSubmit(actionType, tableID, targetID) {
		document.forms[0].target="";
		//����
		if(actionType=="Save" || actionType=="Update"){
			//�ύǰ�ж��Ƿ���δ��д���߸�ʽ���Ե�����
			if (!checkAllDate(tableID,"save")) {
				return false;
			}
			
			if(actionType=="Update"){
				document.forms[0].updateType.value="<%=CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL%>";
			}			
	
			//��ö�̬����е�����
			getTableXXContext_local(tableID, title_rows_count, bottom_rows_count, targetID);
			
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
		for ( var i = 2; i < table_rows_len - 1; i++) {
			for ( var j = 0; j < table_obj.rows[i].cells.length - 1; j++) {
				var tempVal = table_obj.rows[i].cells[j].childNodes[0];
				var tempTagId = tempVal.id;
				//���б�ע���Ǳ�����
				if (tempVal.value == "" && tempTagId !="sglr_bz") {
					alert("��Ϣ��д����ȷ������δ��д��Ϣ�");
					tempVal.focus();
					return false;
				}

				//��Ϊ�գ����������Ƿ���ϸ�ʽҪ��
				if (checkDate(tempVal) == false ) {
					return false;
				}
			}
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

		//��ע��Ϣ���Ǳ�����
		if (objVALUE == "" && objID !="sglr_bz") {
			alert("����д��Ϣ��");
			obj.focus();
			return pass;
		}

		//˰��
		if (objID == "sglr_select_zssmdm") {
			if (objVALUE == "") {
				alert("��ѡ���Ӧ˰�֣�");
				obj.select();
				return pass;
			}
		}
		//�������ʴ���
		if (objID == "sglr_select_jmxzdm_dl") {
			if (objVALUE == "") {
				alert("��ѡ���Ӧ�������ʴ��࣡");
				obj.select();
				return pass;
			}
		}
		//��������С��
		if (objID == "sglr_select_jmxzdm_xl") {
			if (objVALUE == "") {
				alert("��ѡ���Ӧ��������С�࣡");
				obj.select();
				return pass;
			}
		}
		//����˰���ʹ���
		if (objID == "sglr_jmslxdm") {
			if (objVALUE.length != 0 && objVALUE.length > 20) {
				alert("����˰���ʹ������ֻ��¼��20���֣����ѳ����������"+(objVALUE.length-20)+"���֣���ȷ�ϣ�");
				obj.select();
				return pass;
			}
		}
		
		//����˰��������
		if (objID == "sglr_jmslxmc") {
			if (objVALUE.length != 0 && objVALUE.length > 200) {
				alert("����˰�����������ֻ��¼��200���֣����ѳ����������"+(objVALUE.length-200)+"���֣���ȷ�ϣ�");
				obj.select();
				return pass;
			}
		}
		
		//����˰������ʼ���
		if (objID == "sglr_jmszcqsnd") {
			if (objVALUE.length != 0 && objVALUE.length > 4) {
				alert("����˰������ʼ�����������������ܳ���4�����֣��磺2014�����飡");
				obj.select();
				return pass;
			}else if ((objVALUE.length ==4 && !isDigit(objVALUE))|| objVALUE.length <4){
				alert("����˰������ʼ�����д��������д��ȷ��λ�꣬�磺2014�����飡");
				obj.select();
				return pass;
			}
		}
		
		//�ĺ�
		if (objID == "sglr_wh") {
			if (objVALUE.length != 0 && objVALUE.length > 100) {
				alert("�ĺ����ֻ��¼��100���֣����ѳ����������"+(objVALUE.length-100)+"���֣���ȷ�ϣ�");
				obj.select();
				return pass;
			}
		}
		
		//��ע
		if (objID == "sglr_bz") {
			if (objVALUE.length != 0 && objVALUE.length > 100) {
				alert("��ע���ֻ��¼��100���֣����ѳ����������"+(objVALUE.length-100)+"���֣���ȷ�ϣ�");
				obj.select();
				return pass;
			}
		}
		

		pass = true;
		return pass;
	}
	
	//�ûس�ʱ�ƶ����㣨����������������⣩
	function moveFocus(){
		if(event.keyCode==13) event.keyCode = 9;
	}
</script>
<script type="text/javascript">
	//�����������֮�󷵻�ʱ����Ϣ
	function parseSglrmxInfo(sglrmx, tableId, hidPropertyObj) {
		if (sglrmx != null && sglrmx != "") {
			if (sglrmx.indexOf("^") > 0) {
				var infoArr = sglrmx.split("^");

				for ( var index = 0; index < infoArr.length; index++) {
					var tempInfo = infoArr[index];
					//���ý�������������ÿ��������Ϣ����ʾ
					parseOneGoup(tempInfo, tableId, hidPropertyObj);
				}
			} else {
				parseOneGoup(sglrmx, tableId, hidPropertyObj);
			}
		}

	}

	//����¼����Ϣ
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
			//alert("��Ϣ����"+count);

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

					//���ò���ʾÿ����Ϣ
					isSuccess = showOneGroupInfo(oneGroupInfo, tableobj);

					if (!isSuccess) {
						alert("��ʾ¼����Ϣ����");
						return isSuccess;
					}

					//ƴ���ύ����̨����Ϣ
					var tempOneGroupValue = value_0 + "~" + value_1 + "~"
							+ value_2 + "~" + value_3 + "~" + value_4 + "~"
							+ value_5+"~"+ value_6+"~"+ value_7;
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
		var sglr_select_zssmdm_Cell = otr_n.insertCell();
		sglr_select_zssmdm_Cell.className = "2-td2-left";
		sglr_select_zssmdm_Cell.width="5%";
		var valuesArr = getSzsmArr();
		var select_oNewItem = fnAddSelect(sglr_select_zssmdm_Cell,"sglr_select_zssmdm", valuesArr, arr[0]);
		
		//��n(n>=2)�еڶ��� 
		var sglr_select_jmxzdm_dl_Cell = otr_n.insertCell();
		sglr_select_jmxzdm_dl_Cell.className = "2-td2-left";
		sglr_select_jmxzdm_dl_Cell.width="5%";
		var valuesArr_jmxz_dl = getJmxz_dl_xl_Arr("jmxz_dl","");
		select_oNewItem = fnAddSelect(sglr_select_jmxzdm_dl_Cell,"sglr_select_jmxzdm_dl", valuesArr_jmxz_dl, arr[1]);

		//��n(n>=2)�е�����
		var sglr_select_jmxzdm_xl_Cell = otr_n.insertCell();
		sglr_select_jmxzdm_xl_Cell.className = "2-td2-left";
		sglr_select_jmxzdm_xl_Cell.width="5%";
		var valuesArr_jmxz_xl = getJmxz_dl_xl_Arr("jmxz_xl",arr[1]);
		select_oNewItem = fnAddSelect(sglr_select_jmxzdm_xl_Cell,"sglr_select_jmxzdm_xl", valuesArr_jmxz_xl, arr[2]);

		//��n(n>=2)�е����� 
		var sglr_jmslxdm_Cell = otr_n.insertCell();
		sglr_jmslxdm_Cell.className = "2-td2-left";
		sglr_jmslxdm_Cell.width="3%";
		fnAddInput(sglr_jmslxdm_Cell, arr[3], "sglr_jmslxdm", "text");

		//��n(n>=2)�е����� 
		var sglr_jmslxmc_Cell = otr_n.insertCell();
		sglr_jmslxmc_Cell.className = "2-td2-left";
		sglr_jmslxmc_Cell.width="20%";
		fnAddTextarea(sglr_jmslxmc_Cell, arr[4], "sglr_jmslxmc");

		//��n(n>=2)�е����� 
		var sglr_jmszcqsnd_Cell = otr_n.insertCell();
		sglr_jmszcqsnd_Cell.className = "2-td2-left";
		sglr_jmszcqsnd_Cell.width="3%";
		fnAddInput(sglr_jmszcqsnd_Cell, arr[5], "sglr_jmszcqsnd", "text");

		//��n(n>=2)�е����� 
		var sglr_wh_Cell = otr_n.insertCell();
		sglr_wh_Cell.className = "2-td2-left";
		sglr_wh_Cell.width="5%";
		fnAddInput(sglr_wh_Cell, arr[6], "sglr_wh", "text");

		//��n(n>=2)�еڰ��� 
		var sglr_bz_Cell = otr_n.insertCell();
		sglr_bz_Cell.className = "2-td2-left";
		sglr_bz_Cell.width="10%";
		fnAddTextarea(sglr_bz_Cell, arr[7], "sglr_bz");

		//��n(n>=2)�еھ��� 
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

	//��ָ��Ԫ���´���һ��imput�����
	function fnAddInput(cellObj, newItemValue, newItemId, inputType) {
		var oNewItem = document.createElement("INPUT");
		//����������
		if (inputType == "text") {
			oNewItem.value = newItemValue;
			//�������
			if (newItemId == "sglr_jmszcqsnd") {
				oNewItem.style.width="60px";
			}
			
			//����޸ģ�����Ϊ���ɱ༭
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
	
	//����һ��textarea
	function fnAddTextarea(cellObj, newItemValue, newItemId){
		var oNewItem = document.createElement("TEXTAREA");
		oNewItem.id = newItemId;
		oNewItem.name = newItemId;
		oNewItem.value = newItemValue;
		oNewItem.rows="3";
		oNewItem.style.width="100%";
		cellObj.insertAdjacentElement("afterBegin", oNewItem);
	}

	//��ָ��Ԫ���´���һ��select������
	function fnAddSelect(cellObj, newItemId, valuesArr, value) {

		//����һ��select��ǩ
		var oNewItem = document.createElement("SELECT");
		oNewItem.id = newItemId;
		//if(newItemId == "sglr_select_zssmdm"){
			oNewItem.style.width="100px";
		//}
		
		//�����������¼�
		if(newItemId == "sglr_select_jmxzdm_dl"){
			addEvent(oNewItem, 'change', function() {
				doFilterJmxzxldm_byDLDM(oNewItem);saveDefaulValues(oNewItem.value,'jmxzdl')
			});
		}
		
		//��С�������¼�  
		if(newItemId == "sglr_select_jmxzdm_xl"){
			addEvent(oNewItem, 'change', function() {
				saveDefaulValues(oNewItem.value,'jmxzxl')
			});
		}
		
		//��˰������¼�  
		if(newItemId == "sglr_select_zssmdm"){
			addEvent(oNewItem, 'change', function() {
				saveDefaulValues(oNewItem.value,'szdm')
			});
		}
		

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
	//��ü������ʴ��ࡢС��Arr
	function getJmxz_dl_xl_Arr(xzType,jmxzdm_dl) {
		var jmxzArr = new Array();
		//�������
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
		
		//����С��
		if(xzType=="jmxz_xl"){
			<logic:iterate id="item" name="zjjmsdmwhForm"  property ="jmxzxlList" indexId="index">
			var jmxzdm_xl_1 = '<bean:write name="item" property="jmxzdm"/>';
			var jmxzmc_xl_1 = '<bean:write name="item" property="jmxzmc"/>';
	
			var oneJmxz_xl = new Array();
			oneJmxz_xl.push(jmxzdm_xl_1);
			oneJmxz_xl.push(jmxzmc_xl_1);
			//�������ʴ��಻Ϊ�գ���ǰ�����ǹ��˲�������ѡ����࣬������ӦС��
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
		//������ϸ��Ϣ
		if(sglrmx !=""){
			parseSglrmxInfo(sglrmx,'sglrTable','allNewAddInfo');
		}
		
		//��ʾ��ʾ��Ϣ
		var message = '<bean:write name="zjjmsdmwhForm" property="message" />';
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
	
	//����Ϣƴ�ӵ�һ�𴫵���̨���д���,��¼֮����^�ָ��¼�е�ÿһ����~�ָ�
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
            		alert("��Ϣ��д����ȷ������δ��д��Ϣ�");
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
	
	//ȥ���س�
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
		
		<!--ά������������ ��ѯ��������ҳ��Ϣ-->
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
		<html:hidden property="modifyKey_jmslxdm"/><!-- �޸����� -->
		<html:hidden property="pageSize"/>
		<html:hidden property="nextPage"/>
		<html:hidden property="totalpage"/>
	
		<table align="center" cellspacing="0" class="table-100" style="width: 100%" onkeydown="moveFocus()">
			<tr>
				<td class="1-td1" colspan=4 valign="top">�ּܾ���˰����¼��</td>
			</tr>
			<tr>
				<td class="1-td2" style="width: 100%">
					<table style="width: 100%;" cellspacing="0" class="table-99">
						<tr>
							<td colspan="8">
								<table style="width: 100%;" cellspacing="0" class="table-99" id="sglrTable">
									<tr style="display='none'"></tr>
									<tr>
										<td class="2-td2-left"  nowrap="nowrap">˰��</td>
										<td class="2-td2-left"  nowrap="nowrap">�������ʴ���</td>
										<td class="2-td2-left"  nowrap="nowrap">��������С��</td>
										<td class="2-td2-left"  nowrap="nowrap">����˰���ʹ���</td>
										<td class="2-td2-left"  nowrap="nowrap">����˰��������</td>
										<td class="2-td2-left"  nowrap="nowrap">��ʼ���</td>
										<td class="2-td2-left"  nowrap="nowrap">�ĺ�</td>
										<td class="2-td2-left"  nowrap="nowrap">��ע</td>
										<td class="2-td2-center"nowrap="nowrap">ѡ��</td>
									</tr>
									<logic:notPresent name="zjjmsdmwhForm" property="modifyKey_jmslxdm">
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
															onClick="removeLabelCol('sglrTable');"
															alt="ɾ������ѡ����" style="cursor: hand"
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
										<IMG alt="������д��Ϣ��ϵͳ" height=22 id=baocun name=baocun
											onclick="doSubmit('Save','sglrTable','allNewAddInfo')"
											onmouseout=MM_swapImgRestore()
											onmouseover="MM_swapImage('baocun','','<%=static_contextpath%>images/baocun2.jpg',1)"
											src="<%=static_contextpath%>images/baocun1.jpg"
											style='CURSOR: hand' width=79> 
									</logic:equal>
									
									<logic:equal name="zjjmsdmwhForm"  property="actionType" value="Show">
										<IMG alt="������д��Ϣ��ϵͳ" height=22 id=baocun name=baocun
											onclick="doSubmit('Save','sglrTable','allNewAddInfo')"
											onmouseout=MM_swapImgRestore()
											onmouseover="MM_swapImage('baocun','','<%=static_contextpath%>images/baocun2.jpg',1)"
											src="<%=static_contextpath%>images/baocun1.jpg"
											style='CURSOR: hand' width=79>
									</logic:equal>
									
									<logic:notEqual name="zjjmsdmwhForm"  property="actionType" value="Save">
										<logic:notEqual name="zjjmsdmwhForm"  property="actionType" value="Show">
											<logic:present name="zjjmsdmwhForm" property="modifyKey_jmslxdm">
												<IMG alt=�����޸���Ϣ  height=22 id=b-bcbg name=b-bcbg 
												     onclick="doSubmit('Update','sglrTable','allNewAddInfo')" 
												     onmouseout=MM_swapImgRestore() 
												     onmouseover="MM_swapImage('b-bcbg','','<%=static_contextpath%>images/b-bcbg2.jpg',1)" 
												     src="<%=static_contextpath%>images/b-bcbg1.jpg" 
												     style='CURSOR: hand' width=79>
											</logic:present>
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
										<logic:notPresent name="zjjmsdmwhForm" property="modifyKey_jmslxdm">
											<IMG alt="��ת������˰¼��ά����������" height=22 id=weih name=weih
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
							<td class="2-td2-t-left">¼����</td>
							<td class="2-td2-t-left"><div align="left">
									<bean:write name="zjjmsdmwhForm" property="lrrmc" />
								</div></td>
							<td class="2-td2-t-left">¼������</td>
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