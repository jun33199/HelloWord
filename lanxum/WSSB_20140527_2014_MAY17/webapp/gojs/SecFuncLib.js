var objForm = null;
//�˴�Ϊ����Microsofe XML���
var objXML = new ActiveXObject("Microsoft.XMLDOM");

var SecFileNum = 0;
var strMessage = "";

/*
������
	ϵͳ��¼
������
	FormName������
	strUniqueID��֤��Ψһ��ʾ
	strPassword��˽Կ����
����ֵ��
	��
*/
function gFuncLogin(FormName, strUniqueID, strPassword)
{
	var ret;
	var ValidDay;

	if (strUniqueID == "" || strPassword == "") {
		alert("Input Error!");
		return false;
	}

	ret = BJCASecClt.GetCertDetail(strUniqueID, 23);

	if (ret == "Read_Cert_Error!") {
		alert("֤����Ч");
		return false;
	}
	//alert(ret);
	ValidDay = parseInt(ret);
	
	if (parseInt(ret) <= 60 && parseInt(ret) > 0) {
		alert("����֤�黹��" + ValidDay + "����ڣ�\n�������쵽��������֤����֤���İ���֤�����������\n�����Ӱ������������˰����ɲ���Ҫ���鷳����ʧ��\n֤���û�ע��鿴��֪�����������������¼:\nhttp://www.bjca.org.cn����ѯ�绰��82031677-8686��");
	}
	
	if(parseInt(ret) <= -45)
	{
	    alert("����֤���ѹ��� "+ -parseInt(ret) +" �죬���������ʹ�����ޣ�\n�뵽��������֤����֤���İ���֤��������������°�װ��֤����ܼ���ʹ�ã�\n\n��������������¼:\nhttp://www.bjca.org.cn����ѯ�绰��82031677-8686��");
	    return false;
	}
		
	if(parseInt(ret) <= 0){
	    alert("����֤���ѹ��� "+ -parseInt(ret) +" �죬\n�뾡�쵽��������֤����֤���İ���֤�����������\n�����Ӱ������������˰����ɲ���Ҫ���鷳����ʧ��\n֤���û�ע��鿴��֪�����������������¼:\nhttp://www.bjca.org.cn����ѯ�绰��82031677-8686��");
	}
	
	ret = BJCASecClt.InitialSession(strUniqueID, strPassword);

	switch (ret) {

		case -1:
			alert("UniqueID �������");
			return false;
		case -2:
			alert("��ȡ֤��·������");
			return false;
		case -3:
			alert("��ȡ֤����·������");
			return false;
		case -4:
			alert("˽Կ�豸����");
			return false;
		case -5:
			alert("�������");
			return false;
		case -6:
			alert("֤�����豸����");
			return false;
		case 0:
			// Login System Part ...

			BJCASecClt.serverCertPEM = strServerCert;

			var objLogin = eval(FormName);
			var objSignData = eval(FormName + "." + "BJCA_SignData");

			if (objLogin == null || objSignData == null)
                        {
				return false;
			}
			objSignData.value = BJCASecClt.Sign(strRandom);
			var objUserCert = eval(FormName + "." + "BJCA_UserCert");
			objUserCert.value = BJCASecClt.UserCert;
            return true;
	}

}

/*
������
	���ݼ���
������
	strPlainText����������
����ֵ
	ret����������
*/
function gFuncEncrypt(strPlainText) {

	var ret;
	if (strPlainText == "") {
		alert("PlainText is empty!");
		return false;
	}
	else {
		ret = BJCASecClt.EncryptPEM(strPlainText);
	}

	return ret;
}


/*
������
	���ݽ���
������
	strSecretText����������
����ֵ��
	ret����������
*/
function gFuncDeEncrypt(strSecretText) {

	var ret;
	if (strSecretText == "") {
		alert("SecretText is empty!")
		return false;
	}
	else {
		ret = BJCASecClt.DeEncryptPEM(strSecretText);
	}
	return ret;
}

/*
������
	����ǩ��
������
	strSignData���ȴ�ǩ������
����ֵ��
	ret��ǩ�����
*/
function gFuncSign(strSignData) {

	var ret;

	if (strSignData == "") {
		alert("SignData is empty!");
		return false;
	}
	else {
		ret = BJCASecClt.Sign(strSignData);
	}

	return ret;
}


/*
������
	��֤ǩ��
������
	strSignData��ǩ�����
	strUserCert���û�֤��
	strOrigindata��ԭʼ����
����ֵ
	ret����֤ǩ�����
	1����֤�ɹ�
	0����֤ʧ��
	-1�������������
	-2��δ��ʼ��
	-3��ǩ���������
	-4��֤��������
*/
function gFuncVerifySign(strSignedData, strUserCert, strOrigindata) {

	var ret;
	ret = BJCASecClt.VerifySign(strSignedData, strUserCert, strOrigindata);
	return ret;
}

/*
������
	�����ļ�
������
	strFilePath���������ļ�·��
	EncType���������ͣ�1 ����ԭ�ļ���0 ������ԭ�ļ�)
����ֵ��
	-1���ؼ���ʼ������
	-2��δ�����ļ�
	-3���Ѿ�����
	-4�����ļ�����
	-5�����ܴ���
	1�����ܳɹ�
*/
function gFuncEncryptFile(strFilePath, EncType) {

	var ret;

	if (objXML.xml == "") {
		objXML.loadXML("<ClientMSG><FormHeader></FormHeader><FormDetail></FormDetail><SecFileItem></SecFileItem><CertInfo></CertInfo></ClientMSG>");
	}
	if (strFilePath == "") {
		alert("Input Error")
		return false;
	}
	ret = BJCASecClt.EncryptFile(strFilePath, EncType);
	if (ret != 1) {
		parent.frames("main").frames("main").document.all("ret").value=ret;
		//alert(ret);
		return false;
	}
	else {
		SecFileNum = SecFileNum + 1;
		gFuncFormItem2XML("/ClientMSG/SecFileItem", "BJCA_SecFile" + SecFileNum, strFilePath);
		return ret;
	}

}


function gFuncEncryptFileEx(strFilePath, EncType) {

	var ret;

	if (strFilePath == "") {
		alert("Input Error");
		return false;
	}

	ret = BJCASecClt.EncryptFile(strFilePath, EncType);

	if (ret != 1) {
		alert("gFuncEncryptFileEx:"+ret);
		return false;
	}

	return ret;

}

/*
������
	�����ļ�
������
	strFilePath�������ļ�·��
����ֵ��
	-1���ؼ���ʼ������
	-2��δ�����ļ�
	-3�����ļ�����
	-4�����ܴ���
	1���ɹ�

*/
function gFuncDeEncryptFile(strFilePath) {

	var ret;
	if (strFilePath == "") {
		alert("Input Error");
		return false;
	}
	ret = BJCASecClt.DeEncryptFile(strFilePath);
	if (ret != 1) {
		alert(ret);
		return false;
	}
	else {
		return ret;
	}


}

/*
������
	�ָ����ܺ���ļ�
������
	strFilePath�������ļ���·��
	EncType���������ͣ�1 ����ԭ�ļ���0 ������ԭ�ļ�)
����ֵ��
	-1���������
	-2��δ�����ļ�
	1���ָ��ɹ�
*/
function gFuncRestoreFile(strFilePath, EncType) {

	var ret;
	if (strFilePath == "") {
		alert("Input Error");
		return false;
	}
	ret = BJCASecClt.RestoreFile(strFilePath, EncType);

	if (ret == 1) {
		return ret;
	}
	else {
		parent.frames("main").frames("main").document.all("ret").value=ret;
		return false;
	}

}
function gFuncLoadPage(strURL, strAbsURL, strCtrlFramePath, Frame) {

	objFrame = Frame;

	if (objFrame == null || strURL == "" || strCtrlFramePath =="") {
		alert("Input Error");
		return false;
	}

	strURL = gFuncGetRealURL(strAbsURL, strURL);

	index = strURL.indexOf('?');
	if(index!=-1)
	{
		strUrlTemp = strURL.substr(0,index);
	}
	else
	{
		strUrlTemp = strURL;
	}

	objFrame.location.href = strUrlTemp +  "?CtrlFramePath=" + strCtrlFramePath+"&url=" + gFuncEncrypt(strURL) + "&UserCertSN=" + BJCASecClt.UserCertSN ;

}

function gFuncLoadPage(strURL, strAbsURL, Frame) {

	objFrame = Frame;

	if ( strURL == "") {
		alert("Input Error");
		return false;
	}

	strURL = gFuncGetRealURL(strAbsURL, strURL);

	index = strURL.indexOf('?');
	if(index!=-1)
	{
		strUrlTemp = strURL.substr(0,index);
	}
	else
	{
		strUrlTemp = strURL;
	}

	location.href = strUrlTemp +  "?CtrlFramePath=&url=" + gFuncEncrypt(strURL) + "&UserCertSN=" + BJCASecClt.UserCertSN ;

}


/*
������
	�ļ�����
������
	strURL���ļ���URL
	strAbsURL������·��
	strActFrameName���ؼ����ڿ�ܵ�ȫ·����
	strFileName��������ɺ�Ĭ�ϵ��ļ���
	intAutoOpen������ʱ���Ƿ��Զ��򿪣�1 �ǣ�0 ��
����ֵ��
	1���ɹ�
	0��ʧ��
*/
function gFuncDownloadFile(strURL, strAbsURL, strActFrameName, strFileName, intAutoOpen) {

	var ret;

	if (intAutoOpen != 1) {
		intAutoOpen = 0;
	}

	if (strURL == "" || strFileName == "" || strActFrameName == "") {
		alert("Input Error!");
		return false;
	}

	strURL = gFuncGetRealURL(strAbsURL, strURL);


	index = strURL.indexOf('?');
	if(index!=-1)
	{
		strDownloadFile = strURL.substr(0,index);
	}
	else
	{
		strDownloadFile = strURL;
	}

	var strTarget = strDownloadFile + "?url=" + gFuncEncrypt(strURL) + "&UserCertSN=" + BJCASecClt.UserCertSN + "&CtrlFramePath=" + strActFrameName;

	ret = BJCASecClt.DownloadFile(strTarget, strFileName, intAutoOpen);

	return false;

}

/*
������
	�޸�˽Կ����
������
	UniqueID��֤��Ψһ��ʾ
	strOldPWD��������
	strNewPWD��������
	strConfirmPWD��ȷ������
����ֵ��
	0���ɹ�

*/
function gFuncChangePWD(UniqueID, strOldPWD, strNewPWD, strConfirmPWD) {

	if (strNewPWD != strConfirmPWD) {
		alert("������������벻ͬ!");
		return false;
	}

	if (UniqueID == "") {
		alert("UniqueID Error!");
		return false;
	}

	var ret;
	ret = BJCASecClt.ChangePWD(UniqueID, strOldPWD, strNewPWD);

	if (ret != 0) {
		alert("�޸��������");
		return false
	}
	else {

		return 0;
	}


}

/*
������
	����ؼ���Ϣ
������
	��
����ֵ��
	��
*/
function gFuncClearSession() {

	BJCASecClt.ClearSession();

}

//ȡ֤����ϸ���ϣ��ڲ���������
function gFuncGetCertDetail(UniqueID, ItemNo) {

	var ret;
	if (UniqueID == "" || ItemNo > 23 || ItemNo < 0) {
		return false;
	}
	ret = BJCASecClt.GetCertDetail(UniqueID, ItemNo);
	return ret;
}



/*
������
	������ύ
������
	strFormName��������ȫ·����
	strCtrlFramePath���ؼ����ڵĿ��·��
	strAction�����ύ��Action����Javaϵͳ�������·��
����ֵ��
	��
*/
function gFuncSubmitXml(objForm, strAction) {

	if (objForm == null) {
		alert("Form Error");
		return false;
	}

	//Add a hidden item ...

	var strSecItem = "<input type=\"hidden\" name=\"BJCA_SubmitString\" value=\"\">";
	var strSignItem = "<input type=\"hidden\" name=\"BJCA_SignData\" value=\"\">";
	var strUserCertItem = "<input type=\"hidden\" name=\"BJCA_UserCert\" value=\"\">";

	if (objForm.BJCA_SubmitString == null) {
		objForm.insertAdjacentHTML("BeforeEnd",strSecItem);
	}
	if (objForm.BJCA_SignData == null) {
		objForm.insertAdjacentHTML("BeforeEnd",strSignItem);
	}
	if (objForm.BJCA_UserCert == null) {
		objForm.insertAdjacentHTML("BeforeEnd",strUserCertItem);
	}

	//Initialize submit XML string ...
	objXML.async = false;
	if (objXML.xml == "") {
		objXML.loadXML("<ClientMSG><FormHeader></FormHeader><FormDetail></FormDetail><SecFileItem></SecFileItem><CertInfo></CertInfo></ClientMSG>");
	}
	var i, j, k;
	var strCheckBoxList = "";
	var strRadioBoxList = "";
	//Get Form Action ...

	//Add action info to XML String ...
	gFuncFormItem2XML("/ClientMSG/FormHeader", "action", strAction);
	//Add form encoding info to XML String ...
	gFuncFormItem2XML("/ClientMSG/FormHeader", "encoding", objForm.encoding);

	//Add form detail , all over the form ...
	for (i=0; i< objForm.length; i++) {

		if (objForm.item(i).type != "button" & objForm.item(i).type != "image" &
			objForm.item(i).type != "submit" & objForm.item(i).type != "reset" &
			objForm.item(i).name != "BJCA_SubmitString" & objForm.item(i).name != null) {

			//Normal item ...
			if (objForm.item(i).type != "checkbox" & objForm.item(i).type != "radio" & objForm.item(i).type != "") {
				gFuncFormItem2XML("/ClientMSG/FormDetail", objForm.item(i).name, objForm.item(i).value);
			}

			//Checkbox ...
			if (objForm.item(i).type == "checkbox") {

				var strCheckBoxName = objForm.item(i).name;
				var strCheckBoxValue = "";

				if (strCheckBoxList.indexOf(strCheckBoxName) == -1) {
					for (j=0; j< objForm.length; j++) {
						if (objForm.item(j).name == strCheckBoxName & objForm.item(j).checked) {
							if (strCheckBoxValue == "") {
								//strCheckBoxValue = objForm.item(j).name + "=" + objForm.item(j).value;
								//��ôд��Ϊ�˷���Server�˵Ĵ����д
								strCheckBoxValue = objForm.item(j).value;
							}
							else {
								strCheckBoxValue = strCheckBoxValue + "&" + objForm.item(j).name + "=" + objForm.item(j).value;
							}
						}
					}
					gFuncFormItem2XML("/ClientMSG/FormDetail", strCheckBoxName, strCheckBoxValue);
					strCheckBoxList = strCheckBoxList + "&&&" + strCheckBoxName;
				}
			}

			//File ...
			if(objForm.item(i).type=="file"){
				gFuncFormItem2XML("/ClientMSG/FormDetail", objForm.item(i).name, BJCASecClt.SignFile(objForm.item(i).value));
			}

			//Radiobox ...
			if (objForm.item(i).type == "radio") {

				var strRadioBoxName = objForm.item(i).name;

				if (strRadioBoxList.indexOf(strRadioBoxName) == -1) {
					for (k=0; k < objForm.length; k++) {
						if (objForm.item(k).checked & objForm.item(k).name == strRadioBoxName) {
							gFuncFormItem2XML("/ClientMSG/FormDetail", strRadioBoxName, objForm.item(k).value);
							break;
						}
					}
					strRadioBoxList = strRadioBoxList + "&&&" + strRadioBoxName;
				}
			}

			//Over ..

		}

	}
	//Add cert info
	//alert(BJCASecClt.UserCert);
	gFuncFormItem2XML("/ClientMSG/CertInfo", "UserCert", BJCASecClt.UserCert);
	gFuncFormItem2XML("/ClientMSG/CertInfo", "UserCertSN", BJCASecClt.UserCertSN);
	gFuncFormItem2XML("/ClientMSG/CertInfo", "UserCertUniqueID", BJCASecClt.UserCertUniqueID);

	//sunhongbin
	gFuncFormItem2XML("/ClientMSG/FormDetail", "signData", BJCASecClt.Sign(objXML.xml));

	//Sign ...
	gFuncFormItem2XML("/ClientMSG/CertInfo", "SignData", BJCASecClt.Sign(objXML.xml));

	var SubmitString = "<?xml version='1.0' encoding='GB2312' ?>" + objXML.xml;

	objForm.BJCA_SignData.value = BJCASecClt.Sign(SubmitString);
	objForm.BJCA_SubmitString.value = SubmitString;
	objForm.BJCA_UserCert.value = BJCASecClt.UserCert;


	objForm.submit();
	objForm = null;

	SecFileNum = 0;
	objXML.loadXML("<ClientMSG><FormHeader></FormHeader><FormDetail></FormDetail><SecFileItem></SecFileItem><CertInfo></CertInfo></ClientMSG>");
}

/*
������
	������ύ
������
	strFormName��������ȫ·����
	strCtrlFramePath���ؼ����ڵĿ��·��
	strAction�����ύ��Action����Javaϵͳ�������·��
����ֵ��
	��
*/

function gFuncSubmit(objForm, strAction) {

	if (objForm == null) {
		alert("Form Error");
		return false;
	}
	//Add a hidden item ...

	var strSecItem = "<input type=\"hidden\" name=\"BJCA_SubmitString\" value=\"\">";
	var strSignItem = "<input type=\"hidden\" name=\"BJCA_SignData\" value=\"\">";
	var strUserCertItem = "<input type=\"hidden\" name=\"BJCA_UserCert\" value=\"\">";

	if (objForm.BJCA_SubmitString == null) {
		objForm.insertAdjacentHTML("BeforeEnd",strSecItem);
	}
	if (objForm.BJCA_SignData == null) {
		objForm.insertAdjacentHTML("BeforeEnd",strSignItem);
	}
	if (objForm.BJCA_UserCert == null) {
		objForm.insertAdjacentHTML("BeforeEnd",strUserCertItem);
	}
	//Initialize submit XML string ...
	var i, j, k;
	var strCheckBoxList = "";
	var strRadioBoxList = "";
	//Get Form Action ...

	//Add action info to XML String ...
	gFuncFormItem2String("/ClientMSG/FormHeader", "action", strAction);
	//Add form encoding info to XML String ...
	gFuncFormItem2String("/ClientMSG/FormHeader", "encoding", objForm.encoding);

	//Add form detail , all over the form ...
	for (i=0; i< objForm.length; i++) {

		if (objForm.item(i).type != "button" & objForm.item(i).type != "image" &
			objForm.item(i).type != "submit" & objForm.item(i).type != "reset" &
			objForm.item(i).name != "BJCA_SubmitString" & objForm.item(i).name != null) {

			//Normal item ...
			if (objForm.item(i).type != "checkbox" & objForm.item(i).type != "radio" & objForm.item(i).type != "") {
				gFuncFormItem2String("/ClientMSG/FormDetail", objForm.item(i).name, objForm.item(i).value);
			}

			//Checkbox ...
			if (objForm.item(i).type == "checkbox") {

				var strCheckBoxName = objForm.item(i).name;
				var strCheckBoxValue = "";

				if (strCheckBoxList.indexOf(strCheckBoxName) == -1) {
					for (j=0; j< objForm.length; j++) {
						if (objForm.item(j).name == strCheckBoxName & objForm.item(j).checked) {
							if (strCheckBoxValue == "") {
								//strCheckBoxValue = objForm.item(j).name + "=" + objForm.item(j).value;
								//��ôд��Ϊ�˷���Server�˵Ĵ����д
								strCheckBoxValue = objForm.item(j).value;
							}
							else {
								strCheckBoxValue = strCheckBoxValue + "&" + objForm.item(j).name + "=" + objForm.item(j).value;
							}
						}
					}
					gFuncFormItem2String("/ClientMSG/FormDetail", strCheckBoxName, strCheckBoxValue);
					strCheckBoxList = strCheckBoxList + "&&&" + strCheckBoxName;
				}
			}

			//File ...
			if(objForm.item(i).type=="file"){
				gFuncFormItem2String("/ClientMSG/FormDetail", objForm.item(i).name, BJCASecClt.SignFile(objForm.item(i).value));
			}

			//Radiobox ...
			if (objForm.item(i).type == "radio") {

				var strRadioBoxName = objForm.item(i).name;

				if (strRadioBoxList.indexOf(strRadioBoxName) == -1) {
					for (k=0; k < objForm.length; k++) {
						if (objForm.item(k).checked & objForm.item(k).name == strRadioBoxName) {
							gFuncFormItem2String("/ClientMSG/FormDetail", strRadioBoxName, objForm.item(k).value);
							break;
						}
					}
					strRadioBoxList = strRadioBoxList + "&&&" + strRadioBoxName;
				}
			}

			//Over ..

		}

	}
	//Add cert info
	//alert(BJCASecClt.UserCert);
	gFuncFormItem2String("/ClientMSG/CertInfo", "UserCert", BJCASecClt.UserCert);
	gFuncFormItem2String("/ClientMSG/CertInfo", "UserCertSN", BJCASecClt.UserCertSN);
	gFuncFormItem2String("/ClientMSG/CertInfo", "UserCertUniqueID", BJCASecClt.UserCertUniqueID);

	//sunhongbin
	var SubmitString = strMessage;
	objForm.BJCA_SignData.value = BJCASecClt.Sign(SubmitString);
	objForm.BJCA_SubmitString.value = SubmitString;
	objForm.BJCA_UserCert.value = BJCASecClt.UserCert;


	objForm.submit();
	objForm = null;

	SecFileNum = 0;
}

/**
 *��֯�ַ���
 */
function gFuncFormItem2String(beforename, names, values) {
	if(strMessage == "") {
		strMessage = names + "=" + values;
	}
	strMessage = strMessage +"&"+ names + "=" + values;
	return strMessage;

}

/*
������
	��ʾWebҳ��
������
	strSignedData��Server��ǩ���������
	strServerCert��Server��֤��
	RecvData�����ص���������
����ֵ��
	��
*/
function gFuncShowPage(strSignedData, strServerCert, RecvData) {

	strOrigindata = gFuncDeEncrypt(RecvData);
	if (gFuncVerifySign(strSignedData, strServerCert, strOrigindata) == 1) {
		document.write(strOrigindata);
	}
	else {
		document.write("��֤����ǩ��ʧ��!");
	}

}

//����: �ѱ���������ӵ�XML��...
//����: strPath XML·����strTag XML��ǣ�strValue �ñ��ֵ
//����ֵ����
function gFuncFormItem2XML(strPath, strTag, strValue) {

	if (strPath == "" || strTag == "") {
		return false;
	}

	strTag = strTag.replace("[", "BJCA_A");
	strTag = strTag.replace("[", "BJCA_A");
	strTag = strTag.replace("]", "BJCA_B");
	strTag = strTag.replace("]", "BJCA_B");

	var objNode = objXML.selectSingleNode(strPath);
	var objTemp = objXML.createElement(strTag);
	objNode.appendChild(objTemp);
	var objNode = objXML.selectSingleNode(strPath + "/" + strTag);

	var objCDATA = objXML.createCDATASection(strTag);
	objCDATA.text = strValue;
	objNode.appendChild(objCDATA);
	/*
	//Normal ..
	var objNode = objXML.selectSingleNode(strPath);
	var objTemp = objXML.createElement(strTag);
	objTemp.text = strValue;
	objNode.appendChild(objTemp);
	*/
}

function UrlEncode(str){
	return str;
	var str1 = str;
	var i,c,ret="",strSpecial="!\"#$%&'()*+,/:;<=>?@[\]^`{|}~%";
	for(i=0;i<str1.length;i++){
			c=str1.charAt(i);
			if(c==" ")
				str1=str1.replace(" ","+");
			else if(strSpecial.indexOf(c)!=-1)
			{
				var temp = "%"+str1.charCodeAt(i).toString(16);
				str1 =str1.replace(c,temp);
				i=i+temp.length - 1;
			}

	}
	return str1;
}

/*
Get a real URL ...
strAbsURL ����·��
strRelURL ���·��
*/
function gFuncGetRealURL(strAbsURL, strRelURL) {

	var strRealURL = "";
	var strWebPath = "";
	var strTemp = strRelURL;

	strTemp = strRelURL.toUpperCase();

	//The strRelURL aleady is AbsURL ...
	if (strTemp.indexOf("HTTP://") != -1) {
		strRealURL = strRelURL;
	}
	else {
		if (strRelURL.substr(0,1) != "/") {
			//The first of strRelURL is not '/' ...
			strWebPath = strAbsURL.substr(0, strAbsURL.lastIndexOf("/") + 1);
			strRealURL = strWebPath + strRelURL;
		}
		else {
			//The first of strRelURL is '/' ...
			strTemp = strAbsURL;
			strTemp = strTemp.substr(strTemp.indexOf("//") + 2, strTemp.length);
			strTemp = strTemp.substr(strTemp.indexOf("/") + 1, strTemp.length);
			strAbsURL = strAbsURL.substr(0, strAbsURL.indexOf(strTemp) - 1);
			strRealURL = strAbsURL + strRelURL;
		}
	}

	return strRealURL;
}

/**
 * ��ȡ֤�����Ϣ
 * ֻ������key��Ч
 */
function getCertDetailInfosFromKey(iNumber) {

	var ret = BJCASecClt.GetCertDetailInfo_KEY(iNumber);
	alert(ret);
	if (ret == ""|| ret == 'Open_Cert_Error!'||ret=='Get_Error') {
		return false;
	}
	return ret;
}

function getCertDetailInfos(iNumber) {

	var ret = BJCASecClt.GetCertDetailInfo("a:/UserCert.der", iNumber, 1);
	if(ret == '' || ret == 'Open_Cert_Error!'|| ret=='Get_Error') {

	    ret = BJCASecClt.GetCertDetailInfo_KEY(iNumber);
	    if (ret == ""|| ret == 'Open_Cert_Error!'||ret=='Get_Error') {
		alert("����ȷ��֤���Ѿ����ƣ�");
		return false;
	    }
	    return ret;
	}
	return ret;
}


/*
������: SignFileForm
����: FormID	HTML ����ID
	FilePath	ǩ���ļ���·��
����ֵ:
	true �ɹ�
	false ʧ��

*/
function SignFileForm(FormID, FilePath)
{
	var strHideFileHTML = "<input type=\"hidden\" name=\"BJCA_SignData\" value=\"\">";

	if (BJCASecClt == null)
	{
		alert("ActiveX Control is not be create");
		return false;
	}

	if (FormID == null)
	{
		alert("Form Error");
		return false;
	}

	if (FilePath == "")
	{
		alert("Get FilePath Error");
		return false;

	}

	if (FormID.BJCA_SignData == null)
	{
		FormID.insertAdjacentHTML("BeforeEnd",strHideFileHTML);
	}

	FormID.BJCA_SignData.value = BJCASecClt.SignFile(FilePath);

	return true;

}

