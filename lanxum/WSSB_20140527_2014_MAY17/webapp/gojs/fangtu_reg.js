//�Ƿ�
var arySelect_isnot = [["1","��"],["0","��"]];
var arySelect_sl=[["30.00","30.00"],["24.00","24.00"],["18.00","18.00"],["12.00","12.00"],["3.00","3.00"],["1.50","1.50"]];
//���û�����Ϣ
function setBaseInfo() {
	if ( rootNode ) {
		$("xsltVersion").value = rootNode.getElementsByTagName('xsltVersion')[0].text;
		$("schemaVersion").value = rootNode.getElementsByTagName('schemaVersion')[0].text;
		$("ywlx").value = rootNode.getElementsByTagName('ywlx')[0].text;
		$("ywczlx").value = rootNode.getElementsByTagName('ywczlx')[0].text;

		$("taxpayerId").value = rootNode.getElementsByTagName('taxpayerId')[0].text; 
		$("taxpayerName").value = rootNode.getElementsByTagName('taxpayerName')[0].text;
		$("jsjdm").value = rootNode.getElementsByTagName('jsjdm')[0].text;
		$("inputDate").value = rootNode.getElementsByTagName('inputDate')[0].text;
		$("cat").value = rootNode.getElementsByTagName('cat')[0].text;
		$("destCat").value = rootNode.getElementsByTagName('destCat')[0].text;
		
		$("totalPageNum").value = rootNode.getElementsByTagName('totalPageNum')[0].text;
		$("totalItemsNum").value = rootNode.getElementsByTagName('totalItemsNum')[0].text;
		$("currentPageNum").value = rootNode.getElementsByTagName('currentPageNum')[0].text;
		$("pageSize").value = rootNode.getElementsByTagName('pageSize')[0].text;
		
		if ( $("sp_totalItemsNum") )
			$("sp_totalItemsNum").innerText = $("totalItemsNum").value;
		if ( $("sp_currentPageNum") )
			$("sp_currentPageNum").innerText = $("currentPageNum").value;
		if ( $("sp_totalPageNum") )
			$("sp_totalPageNum").innerText = $("totalPageNum").value;
			
		if ( $("print_jsjdm") )
			$("print_jsjdm").value = rootNode.getElementsByTagName('jsjdm')[0].text;
	}
}

//���� Button
function doSave()
{
    daList.saveAllData();
    //�����ֶμ��
    //isCheck = DataSourceCheck(daList);
	var isCheck = DataSourceCheck(daList);
	
    /*
    if(!Char_Vaildate(document.forms[0],'')){
         alert("����������ڷǷ��ַ���");
		 isCheck=false;
	}
	*/
	
    if(isCheck == false){
        return;
    }
    
	$("actionType").value = "Show";

    var saveConfirm=confirm("ȷ�ϱ��浱ǰ�޸ģ�");
    if(saveConfirm==true)
    {
		return SaveExec();  
    }
}



function SaveExec()
{
	//alert("begin SaveExec");
	if (g_objSI.Container != '')
	{
		doSubmitXML(document.forms[0],"Show",true,"",true)
	}else
	{
		doSubmitXML(document.forms[0],"Show",false,"",true)
	}
}

function TurnPage()
{
	//alert("begin SaveExec");
	if (g_objSI.Container != '')
	{
		doSubmitPageXML(document.forms[0],"Show",true,"",true)
	}else
	{
		doSubmitPageXML(document.forms[0],"Show",false,"",true)
	}
}

//��ҳ�ã�����ȡ��������
function getTurnPageXml(objForm)
{	
	var retstr;
	//��������
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	
	
	getBasicXx("jsjdm","/taxdoc");
	getBasicXx("taxpayerName","/taxdoc");
	getBasicXx("taxpayerId","/taxdoc");
	getBasicXx("inputDate","/taxdoc");
	getBasicXx("cat","/taxdoc");
	getBasicXx("destCat","/taxdoc");

//alert("getBasicXx");
	getBasicXx("totalPageNum","/taxdoc");
	getBasicXx("totalItemsNum","/taxdoc");
	getBasicXx("currentPageNum","/taxdoc");
	getBasicXx("pageSize","/taxdoc");

//alert("getFangtuInfo");
	//��������
	//getFangtuInfo(objForm);	

	//ȥ��ĩβ�Զ���ӵĻس�
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	return retstr;
}


//��ҳ����ȡ�ø���Ԫ�أ������ Post��xml ����
function getPostXml(objForm)
{	
	var retstr;
	//��������
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	
	
	getBasicXx("jsjdm","/taxdoc");
	getBasicXx("taxpayerName","/taxdoc");
	getBasicXx("taxpayerId","/taxdoc");
	getBasicXx("inputDate","/taxdoc");
	getBasicXx("cat","/taxdoc");
	getBasicXx("destCat","/taxdoc");

//alert("getBasicXx");
	getBasicXx("totalPageNum","/taxdoc");
	getBasicXx("totalItemsNum","/taxdoc");
	getBasicXx("currentPageNum","/taxdoc");
	getBasicXx("pageSize","/taxdoc");

//alert("getFangtuInfo");
	//��������
	getFangtuInfo(objForm);	

	//ȥ��ĩβ�Զ���ӵĻس�
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	return retstr;
}

//���´���XmlBuild.js�е�doSubmitXML����
function fangtu_doSubmitPageXML(objForm,aType,isSign,xmldata,ifsubmit)
{	
	//alert("begin fangtu_doSubmitXML");
	var strToPost;
	initXMLObject();
	//alert("after initXMLObject");
	//Check Parameter ////////////////////////////
	if (!objForm)
	{
		alert("�޴�: " + strFormName +" ����");
		return false;
	}
	if (!document.all("XML_Form"))
	{
		document.body.insertAdjacentHTML("BeforeEnd",IntHtml.HTMLFormString);
	}
	else
	{
		document.all("XML_Form").strSignData.value = "";
		document.all("XML_Form").actionType.value = "";
		document.all("XML_Form").appType.value = "";
		document.all("XML_Form").strXMLData.value = "";
		document.all("XML_Form").REQUEST_TOKEN.value = "";
	}
	var strToPost = "";
	var strtmp = "";
	if (xmldata != "")
	{
		strtmp = xmldata;
	}
	else
	{
		strtmp = getTurnPageXml(objForm);
	}
	strToPost = strtmp.replace(/\r\n/g,"");
	//alert("after getPostXml");
	var sSignData="";
	var strAppType="";
	if(isSign)
	{
		strAppType = gGetGlobalAppType();
		sSignData = gSign(strToPost);
		if(sSignData == -1)
		{
			alert("��ǩ��ʧ�ܣ�");
			return false;
		}
	}
	document.all("XML_Form").strXMLData.value = strToPost;
	document.all("XML_Form").actionType.value = aType;
	document.all("XML_Form").strSignData.value = sSignData;
	document.all("XML_Form").appType.value = strAppType;
	document.all("XML_Form").REQUEST_TOKEN.value = strREQUEST_TOKEN;
    /*
	alert("ǩ�������" + document.all("XML_Form").strSignData.value);
	alert("actionType:" + document.all("XML_Form").actionType.value);
	alert("appType:" + document.all("XML_Form").appType.value);
	alert("��������XML��" + strToPost);
	alert("REQUEST_TOKEN:" + document.all("XML_Form").REQUEST_TOKEN.value);
	*/
	
	document.all("XML_Form").action = objForm.action;
	document.all("XML_Form").target = objForm.target;
	//Submit Form/////////////////////////////////
	if (ifsubmit)
	{
		document.all("XML_Form").submit();
	}
	return true;
}

//���´���XmlBuild.js�е�doSubmitXML����
function fangtu_doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit)
{	
	//alert("begin fangtu_doSubmitXML");
	var strToPost;
	initXMLObject();
	//alert("after initXMLObject");
	//Check Parameter ////////////////////////////
	if (!objForm)
	{
		alert("�޴�: " + strFormName +" ����");
		return false;
	}
	if (!document.all("XML_Form"))
	{
		document.body.insertAdjacentHTML("BeforeEnd",IntHtml.HTMLFormString);
	}
	else
	{
		document.all("XML_Form").strSignData.value = "";
		document.all("XML_Form").actionType.value = "";
		document.all("XML_Form").appType.value = "";
		document.all("XML_Form").strXMLData.value = "";
		document.all("XML_Form").REQUEST_TOKEN.value = "";
	}
	var strToPost = "";
	var strtmp = "";
	if (xmldata != "")
	{
		strtmp = xmldata;
	}
	else
	{
		strtmp = getPostXml(objForm);
	}
	strToPost = strtmp.replace(/\r\n/g,"");
	//alert("after getPostXml");
	var sSignData="";
	var strAppType="";
	if(isSign)
	{
		strAppType = gGetGlobalAppType();
		sSignData = gSign(strToPost);
		if(sSignData == -1)
		{
			alert("��ǩ��ʧ�ܣ�");
			return false;
		}
	}
	document.all("XML_Form").strXMLData.value = strToPost;
	document.all("XML_Form").actionType.value = aType;
	document.all("XML_Form").strSignData.value = sSignData;
	document.all("XML_Form").appType.value = strAppType;
	document.all("XML_Form").REQUEST_TOKEN.value = strREQUEST_TOKEN;
    /*
	alert("ǩ�������" + document.all("XML_Form").strSignData.value);
	alert("actionType:" + document.all("XML_Form").actionType.value);
	alert("appType:" + document.all("XML_Form").appType.value);
	alert("��������XML��" + strToPost);
	alert("REQUEST_TOKEN:" + document.all("XML_Form").REQUEST_TOKEN.value);
	*/
	
	document.all("XML_Form").action = objForm.action;
	document.all("XML_Form").target = objForm.target;
	//Submit Form/////////////////////////////////
	if (ifsubmit)
	{
		document.all("XML_Form").submit();
	}
	return true;
}


//��Ӧ���, ��ҳ��ѡȡ��Ӧ�� input ��,���xml����
// listname: xml �ڵ������
// rowcount: ҳ���ϼ�¼�ĸ���
// list: listname �����ֱ�ӽڵ�
// regList: regVO �����ֱ�ӽڵ�
// alterList: alterVO �����ֱ�ӽڵ�
function toXml(listname, rowcount, list, regList, alterList) {
	//alert("begin toXml");
	var totalRow = rowcount;
	
	for ( var i=0; i<totalRow; i++) {
	
		var parent = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
		var child = g_Doc.XMLDoc.createElement(listname);
		parent.appendChild(child);
		var tmp= g_Doc.XMLDoc.selectNodes("//"+listname);
		
		for(var j=0;j<list.length;j++) {
			
			var tagName = list[j];
			var value = qt("list["+ i + "]." + tagName);
			
			//ת���Ƿ��ַ�
			try {
				value = formString( value );
			} catch (e) { }
			
			var node2 = g_Doc.XMLDoc.createElement( tagName );
			tmp[i].appendChild( node2 );
			
			var objCDATA = g_Doc.XMLDoc.createCDATASection(value)
			node2.appendChild(objCDATA);
		}
		
		var child_reg = g_Doc.XMLDoc.createElement("regVO");
		tmp[i].appendChild( child_reg );
		
		for(var j=0;j<regList.length;j++) {
			
			var tagName = regList[j];
			var value = qt("list["+ i + "].regVO." + tagName);
			
			//ת���Ƿ��ַ�
			//value = formString( value );
			
			var node2 = g_Doc.XMLDoc.createElement( tagName );
			child_reg.appendChild( node2 );
			
			var objCDATA = g_Doc.XMLDoc.createCDATASection(value)
			node2.appendChild(objCDATA);
		}
		
		var child_alter = g_Doc.XMLDoc.createElement("alterVO");
		tmp[i].appendChild( child_alter );
		
		for(var j=0;j<alterList.length;j++) {
			
			var tagName = alterList[j];
			
			var value = qt("list["+ i + "].alterVO." + tagName);
			
			//ת���Ƿ��ַ�
			//value = formString( value );
			
			var node2 = g_Doc.XMLDoc.createElement( tagName );
			child_alter.appendChild( node2 );
			
			var objCDATA = g_Doc.XMLDoc.createCDATASection(value)
			node2.appendChild(objCDATA);
		}		
		
	}
				
	//alert("end toXml");
					 
	//alert(g_Doc.XMLDoc.xml);
	//$("div").innerText = g_Doc.XMLDoc.xml;

}
function qt( _domain ) {
//	    if ( $( _domain ) ) {
//        	return $F( _domain );
//        }
		
		//alert( _domain );
		if ( document.getElementsByName( _domain ) ) {
			var arr = document.getElementsByName( _domain );
			if ( arr.length == 1 ) {
				return $F( _domain );
			}
		}
		
        return "";
}
