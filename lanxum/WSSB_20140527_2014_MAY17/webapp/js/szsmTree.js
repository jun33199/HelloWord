var szsmTree_xslDoc = false;

var szsmTree_xsl_path = "XSLT/szsmTree.xsl";
var onloadMsg = "正在装载数据....";
var loadError = "装载数据出错!!!!!";
function send_request(action,xmlDoc,divObject){
	var requestXMldoc;
	var http_request = false;
	var url = "/shenbao/szsmTree.dc?actionType="+action+"&local="+local;
	//alert(url);
	//初始化XMLHTTP
	if(window.XMLHttpRequest) { //Mozilla 浏览器
		http_request = new XMLHttpRequest();
		if (http_request.overrideMimeType) {//设置MiME 类别
			http_request.overrideMimeType("text/xml");
		}
	}else if (window.ActiveXObject) { // IE 浏览器
		try {
			http_request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				http_request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {}
		}
	}
	if(!http_request){
		alert("创建XMLHTTP对象失败");
		 return false;
	}
	if(!szsmTree_xslDoc){
		szsmTree_xslDoc = new ActiveXObject("Microsoft.XMLDOM");
		if(!szsmTree_xslDoc){
			alert("创建xslDoc对象失败");
			return false;
		}
		szsmTree_xslDoc.async = false;
		if(!szsmTree_xslDoc.load(szsmTree_xsl_path)){
			alert(loadError+"szsmTree_xslDoc为空");
			return false;
		}
	}
	http_request.onreadystatechange=checkState;//注册状态变化事件
	
	//发送消息
	http_request.open("POST",url,true);
	http_request.send(xmlDoc);
	
	function checkState(){//状态变化事件
		if(http_request.readyState == 4){
			if (http_request.status == 200) {// 页面正常，可以开始处理信息
				requestXMldoc =  http_request.responseXML;
			} else {
				requestXMldoc = null;
			}
			if(requestXMldoc==null){
				alert("装载数据出错，请重新登录后重试！");
				divObject.innerHTML =  "装载数据出错，请重新登录后重试！";
				return false;
			}
			if(requestXMldoc.xml==("")){
				alert("连接超时请退出后重新登录服务器！！！");
				divObject.innerHTML =  "连接超时请退出后重新登录服务器！！！";
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
