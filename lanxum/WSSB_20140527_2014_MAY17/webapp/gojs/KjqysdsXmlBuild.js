
var g_Doc = null;
var IntHtml= null;
g_Doc = new XMLDocWrapper();
IntHtml = new initHtmlForm();

function initHtmlForm()
{
	//Add a SecX Form ////////////////////////////
	this.HTMLFormString = "<Form ID=\"XML_Form\" Name=\"XML_Form\" action=\"\" Method=\"POST\" >";
	this.HTMLFormString += "<input type=\"hidden\" ID=\"strSignData\" name=\"strSignData\" value=\"\"/>";
	this.HTMLFormString += "<input type=\"hidden\" ID=\"actionType\" name=\"actionType\" value=\"\"/>";
	this.HTMLFormString += "<input type=\"hidden\" ID=\"appType\" name=\"appType\" value=\"\"/>";
	this.HTMLFormString += "<input type=\"hidden\" ID=\"strXMLData\" name=\"strXMLData\" value=\"\"/>";
	this.HTMLFormString += "<input type=\"hidden\" ID=\"REQUEST_TOKEN\" name=\"REQUEST_TOKEN\" value=\"\"/>";
	this.HTMLFormString += "</Form>";

}

function parseXml(strXml,urlXSL,idname)
{
	//alert(idname);
	var objOutput = document.getElementById(idname);
	var b1=g_Doc.parseXmlDoc.loadXML(strXml);
	var b2=g_Doc.xslDoc.load(urlXSL);
	if(b1==false)
	{
		alert("����xml���ݳ���");
		return false;
	}
	if(b2==false)
	{
		alert("����xsl�ļ�����");
		return false;
	}
	objOutput.innerHTML = g_Doc.parseXmlDoc.transformNode(g_Doc.xslDoc);
	//document.write(objOutput.innerHTML);
	//alert("objOutput.outterHTML \n" + objOutput.innerHTML);

	return true;
}
function changeYwczlx(ywczlx)
{
	var rootNode = g_Doc.parseXmlDoc.documentElement;
	var objCDATA =g_Doc.parseXmlDoc.createCDATASection(ywczlx);
	rootNode.selectSingleNode("//ywczlx").text = "";
	rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
}
function doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit)
{	
	var strToPost;
	initXMLObject();
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

	//alert("ǩ�������" + document.all("XML_Form").strSignData.value);
	//alert("actionType:" + document.all("XML_Form").actionType.value);
	//alert("appType:" + document.all("XML_Form").appType.value);
	//alert("��������XML��" + strToPost);
	//alert("REQUEST_TOKEN:" + document.all("XML_Form").REQUEST_TOKEN.value);

	document.all("XML_Form").action = objForm.action;
	document.all("XML_Form").target = objForm.target;
	//Submit Form/////////////////////////////////
	if (ifsubmit)
	{
		document.all("XML_Form").submit();
	}
	return true;
}
function XMLDocWrapper()
{
	//��������xml�ַ�����XMLDOM����
	this.XMLDoc = new ActiveXObject("Microsoft.XMLDOM");
	if(this.XMLDoc==null)
	{
		alert("�޷�����XMLDOM����");
		return false;
	}
	this.XMLDoc.async = false;
	//��������xml�ַ�����XMLDOM����
	this.parseXmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	if(this.parseXmlDoc==null)
	{
		alert("�޷�����XMLDOM����");
		return false;
	}
	this.parseXmlDoc.async = false;
	
	this.xslDoc = new ActiveXObject("Microsoft.XMLDOM");
	if(this.xslDoc==null)
	{
		alert("�޷�����XSLDOM����");
		return false;
	}
	this.xslDoc.async=false;	
	this.XMLHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	this.XMLHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	this.XMLDoc.loadXML("<taxdoc></taxdoc>");
}
function initXMLObject()
{
	//g_Doc.XMLDoc.removeAll();
	g_Doc.XMLDoc.loadXML("<taxdoc></taxdoc>");
}

function getPostXml(objForm)
{	
	var retstr;
	//��������
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	// �����
	getBasicXx("tbrq","/taxdoc");
	// ��ǰҳ
	getBasicXx("currentPage","/taxdoc");
	// �����Ǽ����
	getBasicXx("badjbxh","/taxdoc");
	// �޸ı��
	getBasicXx("modifyFlag","/taxdoc");

	// �۽���������Ϣ
	applendElement("/taxdoc","kjywrxx",["kjrjsjdm","kjrnssbh","kjrmc_cn","kjrmc_en","kjrdz_cn","kjrcwfzr","kjrlxr","kjrlxdh","kjryzbm","kjrczhm"]);
	// �Ǿ�����ҵ��Ϣ
	applendElement("/taxdoc","fjmqyxx",["fjmgb","fjmgjdq","fjmgjdqmc","fjmmc_cn","fjmmc_en","fjmdz_cn","fjmdz_en","fjmcwfzr","fjmlxr","fjmlxdh","fjmczhm"]);
	// ��ͬ��Ϣ
	applendElement("/taxdoc","htxx",["htbh","htmc","qyrq","htyxq","htje","bz","zfxm","fkcs","qtzlmc"]);
	
	//�걨����
	//getSbsj(objForm);	
	
	//ȥ��ĩβ�Զ���ӵĻس�
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	return retstr;
}

function getBasicXx(strTag,strPath)
{
	
	var objNode = g_Doc.XMLDoc.selectSingleNode(strPath);
	
	var objTemp = g_Doc.XMLDoc.createElement(strTag);
	objNode.appendChild(objTemp);
	
	var strValue=formString(document.getElementById(strTag).value);
	var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
	objTemp.appendChild(objCDATA);
}

function getChildren(temp,strTag)
{
	var element=document.getElementById(strTag);
	if(element!=null)
	{
		strValue=formString(element.value);
		var objTemp=temp;
		var node=g_Doc.XMLDoc.createElement(strTag);
		objTemp.appendChild(node);
		var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
		node.appendChild(objCDATA);
	}
	
}
function applendElement(root,node,tags)
{
	var objNode = g_Doc.XMLDoc.selectSingleNode(root);
	var objTemp = g_Doc.XMLDoc.createElement(node);
	for (i = 0; i < tags.length ; i++)
	{
		getChildren(objTemp,tags[i]);
	}
	if (objTemp.hasChildNodes())
	{
		objNode.appendChild(objTemp);
	}
}

function dealXMLResponse()
{
	if (g_Doc.XMLHttpRequest.readyState==1)
	{
		document.getElementById("result").innerHTML='���������ѯ��������Ժ򡣡�������������';
	}
	if (g_Doc.XMLHttpRequest.readyState==4)
	{
		var objOutput = document.getElementById("result");
		if (g_Doc.XMLHttpRequest.responseXML.xml == "")
		{
			objOutput.innerHTML = "<font color='red'>�����޷���ѯ����Ҫ�����ݣ�</font> <p>�޴˼�����������Ȩ�޲�ѯҪ������ݡ�";
		}
		else
		{

			var xslDoc;
			xslDoc = new ActiveXObject("Microsoft.XMLDOM");
			xslDoc.async=false;
			var b2=xslDoc.load(xsltFilePath);
			if(b2==false)
			{
				alert("����xsl�ļ�����" + xsltFilePath);
				return false;
			}
			objOutput.innerHTML = g_Doc.XMLHttpRequest.responseXML.transformNode(xslDoc);
			//���ñ��صĺ���ִ�к��������⴦��
			dealResponseLocal(g_Doc.XMLHttpRequest.responseXML);
		}
	}
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//@desc    load a page(some html) via xmlhttp,and display on a container
//@param   url          the url of the page will load,such as "index.php"
//@param   request      request string to be sent,such as "action=1&name=surfchen"
//@param   method       POST or GET
//@param   containerid          the container id,the loaded page will display in container.innerHTML
//@usage 
//         ajaxLoadPage('index.php','action=1&name=surfchen','POST',document.getElementById('my_home'))
//         suppose there is a html element of "my_home" id,such as "<span id='my_home'></span>" 
//@author  SurfChen <surfchen@gmail.com>
//@url     http://www.surfchen.org/
//@license http://www.gnu.org/licenses/gpl.html GPL
function ajaxLoadPage(url,request,method,containerid,translateFunction)
{
	method=method.toUpperCase();

	if (g_Doc.XMLHttpRequest == null)
	{
		return false;
	}
	if (method=='GET')
	{
		urls=url.split("?");
		if (urls[1]=='' || typeof urls[1]=='undefined')
		{
			url=urls[0]+"?"+request;
		}
		else
		{
			url=urls[0]+"?"+urls[1]+"&"+request;
		}
		
		request=null;//for GET method,g_Doc.XMLHttpRequest should send NULL
	}
	g_Doc.XMLHttpRequest.open(method,url,true);
	if (method=="POST")
	{
		g_Doc.XMLHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
	}
	if (translateFunction != null)
	{
		g_Doc.XMLHttpRequest.onreadystatechange=translateFunction;
	}
	else
	{
		g_Doc.XMLHttpRequest.onreadystatechange=dealXMLResponse;
	}
	g_Doc.XMLHttpRequest.send(request);
}
//@desc    transform the elements of a form object and their values into request string( such as "action=1&name=surfchen")
//@param   form_obj          the form object
//@usage   formToRequestString(document.form1)
//@notice  this function can not be used to upload a file.if there is a file input element,the func will take it as a text input.
//         as I know,because of the security,in most of the browsers,we can not upload a file via xmlhttp.
//         a solution is iframe.
//@author  SurfChen <surfchen@gmail.com>
//@url     http://www.surfchen.org/
//@license http://www.gnu.org/licenses/gpl.html GPL
function formToRequestString(form_obj)
{
	var query_string='';
	var and='';
	//alert(form_obj.length);
	for (i=0;i<form_obj.length ;i++ )
	{
		e=form_obj[i];
		if (e.name!='')
		{
			if (e.type=='select-one')
			{
				element_value=e.options[e.selectedIndex].value;
			}
			else if (e.type=='checkbox' || e.type=='radio')
			{
				if (e.checked==false)
				{
					break;	
				}
				element_value=e.value;
			}
			else
			{
				element_value=e.value;
			}
			query_string+=and+e.name+'='+element_value.replace(/\&/g,"%26");
			and="&"
		}
		
	}
	return query_string;
}
//@desc    no refresh submit(ajax) by using ajaxLoadPage and formToRequestString
//@param   form_obj          the form object
//@param   containerid          the container id,the loaded page will display in container.innerHTML
//@usage   ajaxFormSubmit(document.form1,document.getElementById('my_home'))
//@author  SurfChen <surfchen@gmail.com>
//@url     http://www.surfchen.org/
//@license http://www.gnu.org/licenses/gpl.html GPL
function ajaxFormSubmit(form_obj,containerid,translateFunction)
{
	ajaxLoadPage(form_obj.getAttributeNode("action").value,formToRequestString(form_obj),form_obj.method,containerid,translateFunction)
}

//////////////////////////////�������ߺ���

// ȥ���ַ���ǰ��Ŀո�
// @parameter strValue ��Ҫ������ַ�����
// @return String ǰ��ո���Ʊ����ȥ��
function trimString(strValue) {
    if (strValue==null) {
        return null;
    }
    var returnValue = strValue;

    //ɾ���ַ���ǰ��Ŀո�(=32=0x20)���Ʊ��(=09=0x09)
    //�Լ������ַ��Ŀո�(=41377=0xA1A1)
    while (returnValue.length>0) {
        var code = returnValue.charCodeAt(0);
        if (code==32 || code==9 || code==41377) {
            returnValue = returnValue.substr(1);
        } else {
            break;
        }
    }
    //ɾ���ַ�������Ŀո�(SPACE=20)���Ʊ��(TAB=09)
    //�Լ������ַ��Ŀո�(=41377=0xA1A1)
    while (returnValue.length>0) {
        var index = returnValue.length-1;
        var code = returnValue.charCodeAt(index);
        if (code==32 || code==9 || code==41377) {
            returnValue = returnValue.substr(0,index);
        } else {
            break;
        }
    }
    return returnValue;
}
//ȥ��ͷβ�ո��滻������
//1)	������XML�����У���Ҫ��ҳ��չʾ�����ݣ������������Ӣ���ַ����ַ���ϣ�
//       ��"""��"&lt;"��"&gt;"��"&amp;"��"&apos;"��"&quot;"����
//          ��ҳ����뽫������INPUT��ǩչʾ�����ң��ֺ�";"����ת��Ϊ���ķֺ�"��"
//2)	������XML�����У���Ҫ��ҳ��չʾ�����ݣ������������Ӣ���ַ����ַ���ϣ���"'"��"]]>"�������������¹�������ݽ���ת����
// 	   '�����ţ�ʹ�����ĵ�����"��"����
// 	   ]]> ��ʹ��"]]��"����
//3)	������XML�����е����ݣ���������س����з�����ҳ����뽫�س����з��ÿո���ġ�
// fsha modified: ���Ӷ�����"���滻, �滻�ɡ�,
function formString(strValue) {
	if ( strValue == undefined ) return strValue;
	var retstr;
	var strFindArray = [/&lt;/g,/&gt;/g,/&amp;/g,/&apos;/g,/&quot;/g,/\x27/g,/]]>/g,/\r\n/g,/"/g];
	var strRepArray = ["&lt��","&gt��","&amp��","&apos��","&quot��","��","]]��"," ","��"];
	var strp;
	for (var i = 0;i < strFindArray.length; i ++)
	{
		retstr = strValue;
		strValue = retstr.replace(strFindArray[i],strRepArray[i]);
	}
	retstr = trimString(strValue);
	if (retstr == null)
	{
		return "";
	}
	return retstr;
}
