var szsmTree_xslDoc = false;

var szsmTree_xsl_path = "XSLT/szsmTree.xsl";
var onloadMsg = "����װ������....";
var loadError = "װ�����ݳ���!!!!!";
function send_request(action,xmlDoc,divObject){
	var requestXMldoc;
	var http_request = false;
	var url = "/shenbao/szsmTree.dc?actionType="+action+"&local="+local;
	//alert(url);
	//��ʼ��XMLHTTP
	if(window.XMLHttpRequest) { //Mozilla �����
		http_request = new XMLHttpRequest();
		if (http_request.overrideMimeType) {//����MiME ���
			http_request.overrideMimeType("text/xml");
		}
	}else if (window.ActiveXObject) { // IE �����
		try {
			http_request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				http_request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {}
		}
	}
	if(!http_request){
		alert("����XMLHTTP����ʧ��");
		 return false;
	}
	if(!szsmTree_xslDoc){
		szsmTree_xslDoc = new ActiveXObject("Microsoft.XMLDOM");
		if(!szsmTree_xslDoc){
			alert("����xslDoc����ʧ��");
			return false;
		}
		szsmTree_xslDoc.async = false;
		if(!szsmTree_xslDoc.load(szsmTree_xsl_path)){
			alert(loadError+"szsmTree_xslDocΪ��");
			return false;
		}
	}
	http_request.onreadystatechange=checkState;//ע��״̬�仯�¼�
	
	//������Ϣ
	http_request.open("POST",url,true);
	http_request.send(xmlDoc);
	
	function checkState(){//״̬�仯�¼�
		if(http_request.readyState == 4){
			if (http_request.status == 200) {// ҳ�����������Կ�ʼ������Ϣ
				requestXMldoc =  http_request.responseXML;
			} else {
				requestXMldoc = null;
			}
			if(requestXMldoc==null){
				alert("װ�����ݳ��������µ�¼�����ԣ�");
				divObject.innerHTML =  "װ�����ݳ��������µ�¼�����ԣ�";
				return false;
			}
			if(requestXMldoc.xml==("")){
				alert("���ӳ�ʱ���˳������µ�¼������������");
				divObject.innerHTML =  "���ӳ�ʱ���˳������µ�¼������������";
				//window.location.reload();
				return false;
			}
			requestXMldoc = addImagePath(requestXMldoc);
			trancformXML(requestXMldoc,divObject);
			
		}
	}
}

function trancformXML(xmlDoc,divObject){
	if(!szsmTree_xslDoc||!xmlDoc){
		divObject.innerHTML =  loadError;
	}
	divObject.innerHTML = xmlDoc.transformNode(szsmTree_xslDoc);
	try{
		userFunction();
	}catch(e){}
	
}

function addImagePath(request_xmlDoc){
	var rootElement = request_xmlDoc.documentElement;
	var imageNode = request_xmlDoc.createElement("imagePath");
	var imagePathstr = request_xmlDoc.createCDATASection(static_contextpath+"images/");
	imageNode.appendChild(imagePathstr);
	rootElement.appendChild(imageNode);
	return request_xmlDoc;
}

function loadRamoseSZSM(szsmdm,divitem,imgitem){
	if ((divitem.style.display == "") || (divitem.style.display == "none")){
            divitem.style.display = "block";
            imgitem.src = "" + static_contextpath + "images/minus.gif";
     }else { 
            divitem.style.display = "none"; 
            imgitem.src = "" + static_contextpath + "images/plus.gif"; 
     }
	if(divitem.innerHTML.length!=0)return false;
	divitem.innerHTML = onloadMsg;
	send_request("LoadRamoseSZSM&szsmdm="+szsmdm+"&local="+local,null,divitem);
}

function loadRootSZ(divObject){
	divObject.innerHTML = onloadMsg;
	send_request("LoadRootSZ","",divObject);
}
