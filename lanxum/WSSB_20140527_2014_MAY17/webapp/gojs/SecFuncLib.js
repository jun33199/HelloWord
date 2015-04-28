var objForm = null;
//此处为启用Microsofe XML组件
var objXML = new ActiveXObject("Microsoft.XMLDOM");

var SecFileNum = 0;
var strMessage = "";

/*
描述：
	系统登录
参数：
	FormName：表单名
	strUniqueID：证书唯一标示
	strPassword：私钥密码
返回值：
	无
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
		alert("证书无效");
		return false;
	}
	//alert(ret);
	ValidDay = parseInt(ret);
	
	if (parseInt(ret) <= 60 && parseInt(ret) > 0) {
		alert("您的证书还有" + ValidDay + "天过期，\n请您尽快到北京数字证书认证中心办理证书更新手续，\n否则会影响您的正常报税，造成不必要的麻烦和损失。\n证书用户注意查看告知事项，具体更新手续请登录:\nhttp://www.bjca.org.cn；咨询电话：82031677-8686。");
	}
	
	if(parseInt(ret) <= -45)
	{
	    alert("您的证书已过期 "+ -parseInt(ret) +" 天，超过了最后使用期限！\n请到北京数字证书认证中心办理证书更新手续，重新安装新证书才能继续使用！\n\n具体更新手续请登录:\nhttp://www.bjca.org.cn；咨询电话：82031677-8686。");
	    return false;
	}
		
	if(parseInt(ret) <= 0){
	    alert("您的证书已过期 "+ -parseInt(ret) +" 天，\n请尽快到北京数字证书认证中心办理证书更新手续，\n否则会影响您的正常报税，造成不必要的麻烦和损失。\n证书用户注意查看告知事项，具体更新手续请登录:\nhttp://www.bjca.org.cn；咨询电话：82031677-8686。");
	}
	
	ret = BJCASecClt.InitialSession(strUniqueID, strPassword);

	switch (ret) {

		case -1:
			alert("UniqueID 输入错误");
			return false;
		case -2:
			alert("读取证书路径错误");
			return false;
		case -3:
			alert("读取证书链路径错误");
			return false;
		case -4:
			alert("私钥设备出错");
			return false;
		case -5:
			alert("密码错误");
			return false;
		case -6:
			alert("证书链设备错误");
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
描述：
	数据加密
参数：
	strPlainText：明文数据
返回值
	ret：密文数据
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
描述：
	数据解密
参数：
	strSecretText：密文数据
返回值：
	ret：明文数据
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
描述：
	数字签名
参数：
	strSignData：等待签名数据
返回值：
	ret：签名结果
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
描述：
	验证签名
参数：
	strSignData：签名结果
	strUserCert：用户证书
	strOrigindata：原始数据
返回值
	ret：验证签名结果
	1：验证成功
	0：验证失败
	-1：输入参数错误
	-2：未初始化
	-3：签名解码错误
	-4：证书解码错误
*/
function gFuncVerifySign(strSignedData, strUserCert, strOrigindata) {

	var ret;
	ret = BJCASecClt.VerifySign(strSignedData, strUserCert, strOrigindata);
	return ret;
}

/*
描述：
	加密文件
参数：
	strFilePath：待加密文件路径
	EncType：加密类型（1 保存原文件，0 不保存原文件)
返回值：
	-1：控件初始化错误
	-2：未发现文件
	-3：已经加密
	-4：打开文件错误
	-5：加密错误
	1：加密成功
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
描述：
	解密文件
参数：
	strFilePath：加密文件路径
返回值：
	-1：控件初始化错误
	-2：未发现文件
	-3：打开文件错误
	-4：解密错误
	1：成功

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
描述：
	恢复加密后的文件
参数：
	strFilePath：加密文件的路径
	EncType：加密类型（1 保存原文件，0 不保存原文件)
返回值：
	-1：输入错误
	-2：未发现文件
	1：恢复成功
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
描述：
	文件下载
参数：
	strURL：文件的URL
	strAbsURL：绝对路径
	strActFrameName：控件所在框架的全路径名
	strFileName：下载完成后默认的文件名
	intAutoOpen：下载时候是否自动打开（1 是，0 否）
返回值：
	1：成功
	0：失败
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
描述：
	修改私钥密码
参数：
	UniqueID：证书唯一标示
	strOldPWD：旧密码
	strNewPWD：新密码
	strConfirmPWD：确认密码
返回值：
	0：成功

*/
function gFuncChangePWD(UniqueID, strOldPWD, strNewPWD, strConfirmPWD) {

	if (strNewPWD != strConfirmPWD) {
		alert("两次输入的密码不同!");
		return false;
	}

	if (UniqueID == "") {
		alert("UniqueID Error!");
		return false;
	}

	var ret;
	ret = BJCASecClt.ChangePWD(UniqueID, strOldPWD, strNewPWD);

	if (ret != 0) {
		alert("修改密码出错");
		return false
	}
	else {

		return 0;
	}


}

/*
描述：
	清除控件信息
参数：
	无
返回值：
	无
*/
function gFuncClearSession() {

	BJCASecClt.ClearSession();

}

//取证书详细资料，内部保留方法
function gFuncGetCertDetail(UniqueID, ItemNo) {

	var ret;
	if (UniqueID == "" || ItemNo > 23 || ItemNo < 0) {
		return false;
	}
	ret = BJCASecClt.GetCertDetail(UniqueID, ItemNo);
	return ret;
}



/*
描述：
	处理表单提交
参数：
	strFormName：表单对象全路径名
	strCtrlFramePath：控件所在的框架路径
	strAction：表单提交的Action，非Java系统输入绝对路径
返回值：
	无
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
								//这么写是为了方便Server端的代码编写
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
描述：
	处理表单提交
参数：
	strFormName：表单对象全路径名
	strCtrlFramePath：控件所在的框架路径
	strAction：表单提交的Action，非Java系统输入绝对路径
返回值：
	无
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
								//这么写是为了方便Server端的代码编写
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
 *组织字符包
 */
function gFuncFormItem2String(beforename, names, values) {
	if(strMessage == "") {
		strMessage = names + "=" + values;
	}
	strMessage = strMessage +"&"+ names + "=" + values;
	return strMessage;

}

/*
描述：
	显示Web页面
参数：
	strSignedData：Server端签名后的数据
	strServerCert：Server端证书
	RecvData：返回的密文数据
返回值：
	无
*/
function gFuncShowPage(strSignedData, strServerCert, RecvData) {

	strOrigindata = gFuncDeEncrypt(RecvData);
	if (gFuncVerifySign(strSignedData, strServerCert, strOrigindata) == 1) {
		document.write(strOrigindata);
	}
	else {
		document.write("验证数字签名失败!");
	}

}

//描述: 把表单项内容添加到XML中...
//参数: strPath XML路径，strTag XML标记，strValue 该标记值
//返回值：无
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
strAbsURL 绝对路径
strRelURL 相对路径
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
 * 读取证书的信息
 * 只对天谕key有效
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
		alert("请您确认证书已经复制！");
		return false;
	    }
	    return ret;
	}
	return ret;
}


/*
函数名: SignFileForm
参数: FormID	HTML 表单的ID
	FilePath	签名文件的路径
返回值:
	true 成功
	false 失败

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

