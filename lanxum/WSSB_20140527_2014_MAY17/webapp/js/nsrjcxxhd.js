var jsjdm;
var win;
var body;
var bg;
window.onload = function() {
	win = document.getElementById("win"); //获取小窗口
	bg = document.getElementById("bg");//获取背景框
	body = document.body;//获取当前body  
	setData();
}

var pageSize = 3;

var tzfPage = 1;
var tzfBegin = (tzfPage - 1) * pageSize;
var tzfEnd = tzfPage * pageSize - 1;
var tzfs;
var tzfTotalPages;
var tzfTotalRows;

var fzjgPage = 1;
var fzjgBegin = (fzjgPage - 1) * pageSize;
var fzjgEnd = fzjgPage * pageSize - 1;
var fzjgs;
var fzjgTotalPages;
var fzjgTotalRows;

var dkdjPage = 1;
var dkdjBegin = (dkdjPage - 1) * pageSize;
var dkdjEnd = dkdjPage * pageSize - 1;
var dkdjs;
var dkdjTotalPages;
var dkdjTotalRows;

var zjlxs;
/**
 * 放置页面数据
 */
function setData() {
	zjlxs = xml.selectNodes("/nsrjcxxhd/zjlxs/zjlx");
	var cwfzrzjlxSel = document.all.cwfzrzjlxdm;
	buildSfzjlxSelect(cwfzrzjlxSel, zjlxs);
	var bsrzjlxSel = document.all.bsrzjlxdm;
	buildSfzjlxSelect(bsrzjlxSel, zjlxs);
	
	if(xml.selectSingleNode("/nsrjcxxhd/djjbsj")) {
		setJbsj(xml);
	}
	if(xml.selectSingleNode("/nsrjcxxhd/zjg")) {
		setZjg(xml);
	}
	if(xml.selectSingleNode("/nsrjcxxhd/add")) {
		setAdd(xml);
	}
	if(xml.selectSingleNode("/nsrjcxxhd/fr")) {
		setFr(xml);
	}
	if(xml.selectSingleNode("/nsrjcxxhd/bsr")) {
		setBsr(xml);
	}
	if(xml.selectSingleNode("/nsrjcxxhd/cwfzr")) {
		setCwfzr(xml);
	}
	if(xml.selectSingleNode("/nsrjcxxhd/swdl")) {
		setSwdl(xml);
	}
	
	dkdjs = xml.selectNodes("/nsrjcxxhd/dkdjs/dkdj");
	dkdjTotalRows = dkdjs.length;
	dkdjTotalPages = parseInt(dkdjTotalRows / pageSize);
	if(dkdjTotalRows % pageSize != 0) {
		dkdjTotalPages = dkdjTotalPages + 1;
	}
	if(dkdjTotalPages) {
		setDkdj();
	} else {
		document.all.dkdjPageSize.innerHTML = 0;
		document.all.dkdjTotalPages.innerHTML = 0;
	}
	
	tzfs = xml.selectNodes("/nsrjcxxhd/tzfs/tzf");
	tzfTotalRows = tzfs.length;
	tzfTotalPages = parseInt(tzfTotalRows / pageSize);
	if(tzfTotalRows % pageSize != 0) {
		tzfTotalPages = tzfTotalPages + 1;
	}
	if(tzfTotalPages) {
		setTzf();
	} else {
		document.all.tzfPageSize.innerHTML = 0;
		document.all.tzfTotalPages.innerHTML = 0;
	}
	
	fzjgs = xml.selectNodes("/nsrjcxxhd/fzjgs/fzjg");
	fzjgTotalRows = fzjgs.length;
	fzjgTotalPages = parseInt(fzjgTotalRows / pageSize);
	if(fzjgTotalRows % pageSize != 0) {
		fzjgTotalPages = fzjgTotalPages + 1;
	}
	if(fzjgTotalPages) {
		setFzjg();
	} else {
		document.all.fzjgPageSize.innerHTML = 0;
		document.all.fzjgTotalPages.innerHTML = 0;
	}
}

/**
 * 验证Email
 */
function checkEmail(strEmail) {
	if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1) {
		return true;
	} else {
		return false;
	}
}

/**
 * 验证邮政编码
 */
function checkZip(zip) {
	var reg = /^[1-9]\d{5}(?!\d)$/;
	if(reg.test(zip) == false) {
		return false;
	}
	return true;
}

/**
 * 检验税务代理信息
 * @returns {Boolean}
 */
function checkSwdl() {
	var swdlmc = document.all.swdlmc.value;
	var swdlswdjzh = document.all.swdlswdjzh.value;
	
	if(!swdlmc && swdlswdjzh){
		return false;
	} else if(swdlmc && !swdlswdjzh){
		return false;
	}
	return true;
}


//以ajax方式post
function save()
{
	var zcdzyb = document.all.zcdzyb.value;
	if(!checkZip(zcdzyb)) {
		alert("注册地址邮政编码格式不正确！");
		return false;
	}
	var zcdzlxdh = document.all.zcdzlxdh.value;
	var jydz = document.all.jydz.value;
	var jydzyb = document.all.jydzyb.value;
	if(!checkZip(jydzyb)) {
		alert("生产经营地址邮政编码格式不正确！");
		return false;
	}
	var jydzlxdm = document.all.jydzlxdm.value;
	
	var frgddh = document.all.frgddh.value;
	var fryddh = document.all.fryddh.value;
	var frdzyx = document.all.frdzyx.value;
	if(frdzyx) {
		if(!checkEmail(frdzyx)) {
			alert("法人电子邮箱格式不正确！");
			return false;
		}
	}
	
	var cwfzrxm = document.all.cwfzrxm.value;
	var cwfzrzjlxdm = document.all.cwfzrzjlxdm.value;
	var cwfzrzjhm = document.all.cwfzrzjhm.value;
	var cwfzrgddh = document.all.cwfzrgddh.value;
	var cwfzryddh = document.all.cwfzryddh.value;
	var cwfzrdzyx = document.all.cwfzrdzyx.value;
	if(cwfzrdzyx) {
		if(!checkEmail(cwfzrdzyx)) {
			alert("财务负责人电子邮箱格式不正确！");
			return false;
		}
	}
	
	var bsrxm = document.all.bsrxm.value;
	var bsrzjlxdm = document.all.bsrzjlxdm.value;
	var bsrzjhm = document.all.bsrzjhm.value;
	var bsrgddh = document.all.bsrgddh.value;
	var bsryddh = document.all.bsryddh.value;
	var bsrdzyx = document.all.bsrdzyx.value;
	if(bsrdzyx) {
		if(!checkEmail(bsrdzyx)) {
			alert("办税人电子邮箱格式不正确！");
			return false;
		}
	}
	
	if(!checkSwdl()) {
		alert("税务代理人名称、纳税人识别号必须全部录入或全部为空！");
		return false;
	}
	
	var swdlmc = document.all.swdlmc.value;
	var swdlswdjzh = document.all.swdlswdjzh.value;
	var swdlgddh = document.all.swdlgddh.value;
	var swdldzyx = document.all.swdldzyx.value;
	
	if(swdldzyx) {
		if(!checkEmail(swdldzyx)) {
			alert("税务代理电子邮箱格式不正确！");
			return false;
		}
	}
	
	var strXMLData = "<?xml version='1.0' encoding='utf-8' ?><nsrjcxxhd>"+
				"<zcdzyb><![CDATA["+zcdzyb+"]]></zcdzyb>"+
				"<zcdzlxdh><![CDATA["+zcdzlxdh+"]]></zcdzlxdh>"+
				"<jydz><![CDATA["+jydz+"]]></jydz>"+
				"<jydzyb><![CDATA["+jydzyb+"]]></jydzyb>"+
				"<jydzlxdm><![CDATA["+jydzlxdm+"]]></jydzlxdm>"+
				"<cwfzrxm><![CDATA["+cwfzrxm+"]]></cwfzrxm>"+
				"<cwfzrzjlxdm><![CDATA["+cwfzrzjlxdm+"]]></cwfzrzjlxdm>"+
				"<cwfzrzjhm><![CDATA["+cwfzrzjhm+"]]></cwfzrzjhm>"+
				"<cwfzrgddh><![CDATA["+cwfzrgddh+"]]></cwfzrgddh>"+
				"<cwfzryddh><![CDATA["+cwfzryddh+"]]></cwfzryddh>"+
				"<cwfzrdzyx><![CDATA["+cwfzrdzyx+"]]></cwfzrdzyx>"+
				"<bsrxm><![CDATA["+bsrxm+"]]></bsrxm>"+
				"<bsrzjlxdm><![CDATA["+bsrzjlxdm+"]]></bsrzjlxdm>"+
				"<bsrzjhm><![CDATA["+bsrzjhm+"]]></bsrzjhm>"+
				"<bsrgddh><![CDATA["+bsrgddh+"]]></bsrgddh>"+
				"<bsryddh><![CDATA["+bsryddh+"]]></bsryddh>"+
				"<bsrdzyx><![CDATA["+bsrdzyx+"]]></bsrdzyx>"+
				
				"<frgddh><![CDATA["+frgddh+"]]></frgddh>"+
				"<fryddh><![CDATA["+fryddh+"]]></fryddh>"+
				"<frdzyx><![CDATA["+frdzyx+"]]></frdzyx>"+
				
				"<swdlmc><![CDATA["+swdlmc+"]]></swdlmc>"+
				"<swdlswdjzh><![CDATA["+swdlswdjzh+"]]></swdlswdjzh>"+
				"<swdlgddh><![CDATA["+swdlgddh+"]]></swdlgddh>"+
				"<swdldzyx><![CDATA["+swdldzyx+"]]></swdldzyx>"+
				
				"</nsrjcxxhd>";
	strXMLData = strXMLData.replace(/\r\n/g,"");
	var ajax;
    if(window.ActiveXObject){
    	ajax=new ActiveXObject("Microsoft.XMLHTTP");
	}else if(window.XMLHttpRequest){
	    ajax=new XMLHttpRequest();
	} else {
	    return;
	}
    ajax.open("POST", "/shenbao/nsrjcxxhd.dc" ,true);
    ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=GBK"); 
    
    //返回数据的处理函数
    ajax.onreadystatechange = function(){
        if (ajax.readyState == 4 && ajax.status == 200) {
        	var rtnXml = ajax.responseXML;
        	var msg = rtnXml.selectSingleNode("/nsrjcxxhd/save/msg").text;
			if("success" == msg) {
				alert("修改成功！");
				tuichu();
			} else {
				alert("保存数据时发生异常，请与管理员联系！");
			}
        }
   };
	var htmlInf="actionType=Save&strXMLData=";
   ajax.send(htmlInf + strXMLData);
}

/**
 * 保存修改
 */
function save2() {
	var zcdzyb = document.all.zcdzyb.value;
	if(!checkZip(zcdzyb)) {
		alert("注册地址邮政编码格式不正确！");
		return false;
	}
	var zcdzlxdh = document.all.zcdzlxdh.value;
	var jydz = document.all.jydz.value;
	var jydzyb = document.all.jydzyb.value;
	if(!checkZip(jydzyb)) {
		alert("生产经营地址邮政编码格式不正确！");
		return false;
	}
	var jydzlxdm = document.all.jydzlxdm.value;
	
	var cwfzrxm = document.all.cwfzrxm.value;
	var cwfzrzjlxdm = document.all.cwfzrzjlxdm.value;
	var cwfzrzjhm = document.all.cwfzrzjhm.value;
	var cwfzrgddh = document.all.cwfzrgddh.value;
	var cwfzryddh = document.all.cwfzryddh.value;
	var cwfzrdzyx = document.all.cwfzrdzyx.value;
	if(!checkEmail(cwfzrdzyx)) {
		alert("财务负责人电子邮箱格式不正确！");
		return false;
	}
	
	var bsrxm = document.all.bsrxm.value;
	var bsrzjlxdm = document.all.bsrzjlxdm.value;
	var bsrzjhm = document.all.bsrzjhm.value;
	var bsrgddh = document.all.bsrgddh.value;
	var bsryddh = document.all.bsryddh.value;
	var bsrdzyx = document.all.bsrdzyx.value;
	if(!checkEmail(bsrdzyx)) {
		alert("办税人电子邮箱格式不正确！");
		return false;
	}
	
	var url = '/shenbao/nsrjcxxhd.dc?actionType=Save&zcdzyb=' + zcdzyb + '&zcdzlxdh=' + zcdzlxdh + '&jydz=' + jydz + '&jydzyb=' + jydzyb + '&jydzlxdm=' + jydzlxdm
	+ '&cwfzrxm=' + cwfzrxm + '&cwfzrzjlxdm=' + cwfzrzjlxdm + '&cwfzrzjhm=' + cwfzrzjhm + '&cwfzrgddh=' + cwfzrgddh + '&cwfzryddh=' + cwfzryddh + '&cwfzrdzyx=' + cwfzrdzyx
	+ '&bsrxm=' + bsrxm + '&bsrzjlxdm=' + bsrzjlxdm + '&bsrzjhm=' + bsrzjhm + '&bsrgddh=' + bsrgddh + '&bsryddh=' + bsryddh + '&bsrdzyx=' + bsrdzyx;
	
	var xmlhttp_request2;
	if (window.ActiveXObject) {
		xmlhttp_request2 = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		xmlhttp_request2 = new XMLHttpRequest();
	} else {
		return;
	}
	xmlhttp_request2.open("GET", url, true);
	xmlhttp_request2.onreadystatechange = function zdyprocessAjaxResponse() {
		if (xmlhttp_request2.readyState == 4) {
			if (xmlhttp_request2.status == 200) {
				var rtnXml = xmlhttp_request2.responseXML;
				var msg = rtnXml.selectSingleNode("/nsrjcxxhd/save/msg").text;
				if("success" == msg) {
					alert("修改成功！");
					tuichu();
				}
			}
		}
	};
	xmlhttp_request2.send(null);
}

/**
 * 构建身份证件类型下拉选
 * @param sel
 * @param zjlxs
 */
function buildSfzjlxSelect(sel, zjlxs) {
	var zjlxTotalRows = zjlxs.length;
	for(var i=0; i<zjlxTotalRows; i++) {
		sel.add(new Option(zjlxs[i].lastChild.text,zjlxs[i].firstChild.text));
	}
}

/**
 * 放置总机构
 * @param xml
 */
function setZjg(xml) {
	var zjgnsrmc = xml.selectSingleNode("/nsrjcxxhd/zjg/nsrmc").text;
	var zjgswdjzh = xml.selectSingleNode("/nsrjcxxhd/zjg/zjgswdjzh").text;
	var zjgscjydz = xml.selectSingleNode("/nsrjcxxhd/zjg/scjydz").text;
	var zjgjyfw = xml.selectSingleNode("/nsrjcxxhd/zjg/zjgjyfw").text;
	var zjgfrdbxm = xml.selectSingleNode("/nsrjcxxhd/zjg/frdbxm").text;
	var zjgscjydzlxdh = xml.selectSingleNode("/nsrjcxxhd/zjg/scjydzlxdh").text;
	var zjgzcdzyb = xml.selectSingleNode("/nsrjcxxhd/zjg/zcdzyb").text;
	
	document.all.zjgnsrmc.innerHTML = zjgnsrmc;
	document.all.zjgswdjzh.innerHTML = zjgswdjzh;
	document.all.zjgscjydz.innerHTML = zjgscjydz;
	document.all.zjgjyfw.innerHTML = zjgjyfw;
	document.all.zjgfrdbxm.innerHTML = zjgfrdbxm;
	document.all.zjgscjydzlxdh.innerHTML = zjgscjydzlxdh;
	document.all.zjgzcdzyb.innerHTML = zjgzcdzyb;
}

/**
 * 放置税务代理
 * @param xml
 */
function setSwdl(xml) {
	var swdlmc = xml.selectSingleNode("/nsrjcxxhd/swdl/mc").text;
	var swdlswdjzh = xml.selectSingleNode("/nsrjcxxhd/swdl/swdjzh").text;
	var swdlgddh = xml.selectSingleNode("/nsrjcxxhd/swdl/gddh").text;
	var swdldzyx = xml.selectSingleNode("/nsrjcxxhd/swdl/dzyx").text;
	
	document.all.swdlmc.value = swdlmc;
	document.all.swdlswdjzh.value = swdlswdjzh;
	document.all.swdlgddh.value = swdlgddh;
	document.all.swdldzyx.value = swdldzyx;
}
/**
 * 放置Add信息
 * @param xml
 */
function setAdd(xml) {
	var kyslrq = xml.selectSingleNode("/nsrjcxxhd/add/kyslrq").text;
	var xcjyqx= xml.selectSingleNode("/nsrjcxxhd/add/xcjyqx").text;
	var zjmc= xml.selectSingleNode("/nsrjcxxhd/add/zjmc").text;
	var zjhm= xml.selectSingleNode("/nsrjcxxhd/add/zjhm").text;
	var cyrs= xml.selectSingleNode("/nsrjcxxhd/add/cyrs").text;
	var wjrs= xml.selectSingleNode("/nsrjcxxhd/add/wjrs").text;
	var dwxz = xml.selectSingleNode("/nsrjcxxhd/add/dwxz").text;
	
	var zczbbzdm_one = xml.selectSingleNode("/nsrjcxxhd/add/zczbbzdm_one").text;
	var zczbje_one = xml.selectSingleNode("/nsrjcxxhd/add/zczbje_one").text;
	var zcbbzdm_two = xml.selectSingleNode("/nsrjcxxhd/add/zcbbzdm_two").text;
	var zczbje_two = xml.selectSingleNode("/nsrjcxxhd/add/zczbje_two").text;
	var zcbbzdm_three = xml.selectSingleNode("/nsrjcxxhd/add/zcbbzdm_three").text;
	var zczbje_three = xml.selectSingleNode("/nsrjcxxhd/add/zczbje_three").text;
	
	var pzcljgdm = xml.selectSingleNode("/nsrjcxxhd/add/pzcljgdm").text;
	
	document.all.kyslrq.innerHTML = kyslrq;
	document.all.xcjyqx.innerHTML = xcjyqx;
	document.all.zjmc.innerHTML = zjmc;
	document.all.zjhm.innerHTML = zjhm;
	document.all.cyrs.innerHTML = cyrs;
	document.all.wjrs.innerHTML = wjrs;
	document.all.dwxz.innerHTML = dwxz;
	
	document.all.zczbbzdm_one.innerHTML = zczbbzdm_one;
	document.all.zczbje_one.innerHTML = zczbje_one;
	document.all.zcbbzdm_two.innerHTML = zcbbzdm_two;
	document.all.zczbje_two.innerHTML = zczbje_two;
	document.all.zcbbzdm_three.innerHTML = zcbbzdm_three;
	document.all.zczbje_three.innerHTML = zczbje_three;
	
	document.all.pzcljgdm.innerHTML = pzcljgdm;
}

/**
 * 设置法人
 * @param xml
 */
function setFr(xml) {
	var frxm = xml.selectSingleNode("/nsrjcxxhd/fr/xm").text;
	var frzjlxdm = xml.selectSingleNode("/nsrjcxxhd/fr/zjlxdm").text;
	var frzjhm = xml.selectSingleNode("/nsrjcxxhd/fr/zjhm").text;
	var frgddh = xml.selectSingleNode("/nsrjcxxhd/fr/gddh").text;
	var fryddh = xml.selectSingleNode("/nsrjcxxhd/fr/yddh").text;
	var frdzyx = xml.selectSingleNode("/nsrjcxxhd/fr/dzyx").text;
	
	document.all.frxm.innerHTML = frxm;
	document.all.frzjlxdm.innerHTML = frzjlxdm;
	document.all.frzjhm.innerHTML = frzjhm;
	document.all.frgddh.value = frgddh;
	document.all.fryddh.value = fryddh;
	document.all.frdzyx.value = frdzyx;
}

/**
 * 设置财务负责人
 * @param xml
 */
function setCwfzr(xml) {
	var cwfzrxm = xml.selectSingleNode("/nsrjcxxhd/cwfzr/xm").text;
	var cwfzrzjlxdm = xml.selectSingleNode("/nsrjcxxhd/cwfzr/zjlxdm").text;
	var cwfzrzjhm = xml.selectSingleNode("/nsrjcxxhd/cwfzr/zjhm").text;
	var cwfzrgddh = xml.selectSingleNode("/nsrjcxxhd/cwfzr/gddh").text;
	var cwfzryddh = xml.selectSingleNode("/nsrjcxxhd/cwfzr/yddh").text;
	var cwfzrdzyx = xml.selectSingleNode("/nsrjcxxhd/cwfzr/dzyx").text;
	
	document.all.cwfzrxm.value = cwfzrxm;
	selectedItemByText(document.all.cwfzrzjlxdm, cwfzrzjlxdm);
	document.all.cwfzrzjhm.value = cwfzrzjhm;
	document.all.cwfzrgddh.value = cwfzrgddh;
	document.all.cwfzryddh.value = cwfzryddh;
	document.all.cwfzrdzyx.value = cwfzrdzyx;
}

/**
 * 设置办税人
 * @param xml
 */
function setBsr(xml) {
	var bsrxm = xml.selectSingleNode("/nsrjcxxhd/bsr/xm").text;
	var bsrzjlxdm = xml.selectSingleNode("/nsrjcxxhd/bsr/zjlxdm").text;
	var bsrzjhm = xml.selectSingleNode("/nsrjcxxhd/bsr/zjhm").text;
	var bsrgddh = xml.selectSingleNode("/nsrjcxxhd/bsr/gddh").text;
	var bsryddh = xml.selectSingleNode("/nsrjcxxhd/bsr/yddh").text;
	var bsrdzyx = xml.selectSingleNode("/nsrjcxxhd/bsr/dzyx").text;
	
	document.all.bsrxm.value = bsrxm;
	selectedItemByText(document.all.bsrzjlxdm, bsrzjlxdm);
	document.all.bsrzjhm.value = bsrzjhm;
	document.all.bsrgddh.value = bsrgddh;
	document.all.bsryddh.value = bsryddh;
	document.all.bsrdzyx.value = bsrdzyx;
}

/**
 * 设置select中text="objItemText"的第一个Item为选中
 */  
function selectedItemByText(objSelect, objItemText) {
	var optionLen = objSelect.options.length;
	for ( var i = 0; i < optionLen; i++) {
		if (objSelect.options[i].text == objItemText) {
			objSelect.options[i].selected = true;
			break;
		}
	}
}

/**
 * 放置基本数据
 * @param xml
 */
function setJbsj(xml) {
	var nsrmc = xml.selectSingleNode("/nsrjcxxhd/djjbsj/nsrmc").text;
	var jsjdm = xml.selectSingleNode("/nsrjcxxhd/djjbsj/jsjdm").text;
	var zzjgdm = xml.selectSingleNode("/nsrjcxxhd/djjbsj/zzjgdm").text;
	var djzclxdm = xml.selectSingleNode("/nsrjcxxhd/djjbsj/djzclxdm").text;
	var zcdz = xml.selectSingleNode("/nsrjcxxhd/djjbsj/zcdz").text;
	var jyfw = xml.selectSingleNode("/nsrjcxxhd/djjbsj/jyfw").text;
	var swdjzh = xml.selectSingleNode("/nsrjcxxhd/djjbsj/swdjzh").text;
	var yyzzh = xml.selectSingleNode("/nsrjcxxhd/djjbsj/yyzzh").text;
	var zcdzyb = xml.selectSingleNode("/nsrjcxxhd/djjbsj/zcdzyb").text;
	var zcdzlxdh = xml.selectSingleNode("/nsrjcxxhd/djjbsj/zcdzlxdh").text;
	var jydz = xml.selectSingleNode("/nsrjcxxhd/djjbsj/jydz").text;
	var jydzyb = xml.selectSingleNode("/nsrjcxxhd/djjbsj/jydzyb").text;
	var jydzlxdm = xml.selectSingleNode("/nsrjcxxhd/djjbsj/jydzlxdm").text;
	var hsxsdm = xml.selectSingleNode("/nsrjcxxhd/djjbsj/hsxsdm").text;
	var qyzy = xml.selectSingleNode("/nsrjcxxhd/djjbsj/qyzy").text;
	var kjzddm = xml.selectSingleNode("/nsrjcxxhd/djjbsj/kjzddm").text;
	//var zczbbzdm = xml.selectSingleNode("/nsrjcxxhd/djjbsj/zczbbzdm").text;
	var zczbje = xml.selectSingleNode("/nsrjcxxhd/djjbsj/zczbje").text;
	var gjbzhydm = xml.selectSingleNode("/nsrjcxxhd/djjbsj/gjbzhydm").text;
	
	var zrrtzblhj = xml.selectSingleNode("/nsrjcxxhd/djjbsj/zrrtzblhj").text;
	var wzztzblhj = xml.selectSingleNode("/nsrjcxxhd/djjbsj/wzztzblhj").text;
	var swdjzh = xml.selectSingleNode("/nsrjcxxhd/djjbsj/swdjzh").text;
	
	document.all.nsrmc.innerHTML = nsrmc;
	document.all.jsjdm.innerHTML = jsjdm;
	document.all.zzjgdm.innerHTML = zzjgdm;
	document.all.djzclxdm.innerHTML = djzclxdm;
	document.all.zcdz.innerHTML = zcdz;
	document.all.yyzzh.innerHTML = yyzzh;
	document.all.jyfw.innerHTML = jyfw;
	document.all.swdjzh.innerHTML = swdjzh;
	document.all.zcdzyb.value = zcdzyb;
	document.all.zcdzlxdh.value = zcdzlxdh;
	document.all.jydz.value = jydz;
	document.all.jydzyb.value = jydzyb;
	document.all.jydzlxdm.value = jydzlxdm;
	document.all.hsxsdm.innerHTML = hsxsdm;
	document.all.qyzy.innerHTML = qyzy;
	document.all.kjzddm.innerHTML = kjzddm;
	//document.all.zczbbzdm.innerHTML = zczbbzdm;
	document.all.zczbje.innerHTML = zczbje;
	document.all.gjbzhydm.innerHTML = gjbzhydm;
	
	document.all.zrrtzblhj.innerHTML = zrrtzblhj;
	document.all.wzztzblhj.innerHTML = wzztzblhj;
}

/*分支机构开始===============================================================================================*/
/**
 * 放置分支机构
 */
function setFzjg() {
	fzjgBegin = (fzjgPage - 1) * pageSize;
	fzjgEnd = fzjgPage * pageSize;

	if(fzjgEnd > fzjgTotalRows) {
		fzjgEnd = fzjgTotalRows;
	}
	for(var i=fzjgBegin; i<fzjgEnd; i++) {
		buildFzjgTr(fzjgs[i]);
	}
	document.all.fzjgPageSize.innerHTML = fzjgPage;
	document.all.fzjgTotalPages.innerHTML = fzjgTotalPages;
}

/**
 * 分支机构分页
 */
function fzjgPagination(flag) {
	if(fzjgTotalPages) {
		if("next" == flag) {
			fzjgPage ++;
		} else if("previous" == flag) {
			fzjgPage --;
		}
		if(fzjgPage > fzjgTotalPages) {
			fzjgPage = fzjgTotalPages;
		} 
		if(fzjgPage < 1) {
			fzjgPage = 1;
		}
		
		var fzjgTbody = document.all.fzjgTbody;
		var rowCount = fzjgTbody.rows.length;
		for(var i=0; i<rowCount; i++) {
			fzjgTbody.deleteRow(0);
		}
		setFzjg();
	}
}

/**
 * 添加一行分支机构
 * @param tzf
 */
function buildFzjgTr(fzjg) {
	var fzjgTbody = document.all.fzjgTbody;
	var rowCount = fzjgTbody.rows.length;
	
	var newRow = fzjgTbody.insertRow(rowCount++);	//新增一行
	
	var C1 = newRow.insertCell(0);
	C1.setAttribute("className","2-td2-left");
	C1.innerHTML=fzjg.selectSingleNode("fzjgswdjzh").text;
	
	var C2 = newRow.insertCell(1);
	C2.setAttribute("className","2-td2-center");
	C2.innerHTML=fzjg.selectSingleNode("nsrmc").text;
	
	var C3 = newRow.insertCell(2);
	C3.setAttribute("className","2-td2-right");
	C3.innerHTML=fzjg.selectSingleNode("zcdz").text;
}
/*分支机构结束===============================================================================================*/

/*投资方开始===============================================================================================*/
/**
 * 放置投资方信息
 */
function setTzf() {
	tzfBegin = (tzfPage - 1) * pageSize;
	tzfEnd = tzfPage * pageSize;
	if(tzfEnd > tzfTotalRows) {
		tzfEnd = tzfTotalRows;
	}
	
	for(var i=tzfBegin; i<tzfEnd; i++) {
		buildTzfTr(tzfs[i]);
	}
	document.all.tzfPageSize.innerHTML = tzfPage;
	document.all.tzfTotalPages.innerHTML = tzfTotalPages;
}

/**
 * 投资方分页
 */
function tzfPagination(flag) {
	if(tzfTotalPages) {
		if("next" == flag) {
			tzfPage ++;
		} else if("previous" == flag) {
			tzfPage --;
		}
		if(tzfPage > tzfTotalPages) {
			tzfPage = tzfTotalPages;
		} 
		if(tzfPage < 1) {
			tzfPage = 1;
		}
		
		var tzfTbody = document.all.tzfTbody;
		var rowCount = tzfTbody.rows.length;
		for(var i=0; i<rowCount; i++) {
			tzfTbody.deleteRow(0);
		}
		setTzf();
	}
}


/**
 * 添加一行投资方
 * @param tzf
 */
function buildTzfTr(tzf) {
	var tzfTbody = document.all.tzfTbody;
	var rowCount = tzfTbody.rows.length;
	
	var newRow = tzfTbody.insertRow(rowCount++);	//新增一行
	
	var C0 = newRow.insertCell(0);
	C0.setAttribute("className","2-td2-left");
	C0.innerHTML= "&nbsp;" + tzf.selectSingleNode("tzfmc").text;
	
	var C1 = newRow.insertCell(1);
	C1.setAttribute("className","2-td2-center");
	C1.innerHTML= "&nbsp;" + tzf.selectSingleNode("tzfjjxz").text;
	
	var C2 = newRow.insertCell(2);
	C2.setAttribute("className","2-td2-right");
	C2.innerHTML= "&nbsp;" + tzf.selectSingleNode("tzbl").text;
	
	var C3 = newRow.insertCell(3);
	C3.setAttribute("className","2-td2-right");
	C3.innerHTML= "&nbsp;" + tzf.selectSingleNode("zjlxdm").text;
	
	var C4 = newRow.insertCell(4);
	C4.setAttribute("className","2-td2-right");
	C4.setAttribute("colSpan",2);
	C4.innerHTML= "&nbsp;" + tzf.selectSingleNode("zjhm").text;
	
	var C5 = newRow.insertCell(5);
	C5.setAttribute("className","2-td2-right");
	C5.setAttribute("colSpan",2);
	C5.innerHTML= "&nbsp;" + tzf.selectSingleNode("gjdz").text;
}
/*投资方结束===============================================================================================*/

/*代扣代缴开始===============================================================================================*/
/**
 * 放置代扣代缴信息
 */
function setDkdj() {
	dkdjBegin = (dkdjPage - 1) * pageSize;
	dkdjEnd = dkdjPage * pageSize;
	if(dkdjEnd > dkdjTotalRows) {
		dkdjEnd = dkdjTotalRows;
	}
	
	for(var i=dkdjBegin; i<dkdjEnd; i++) {
		buildDkdjTr(dkdjs[i]);
	}
	document.all.dkdjPageSize.innerHTML = dkdjPage;
	document.all.dkdjTotalPages.innerHTML = dkdjTotalPages;
}

/**
 * 代扣代缴分页
 */
function dkdjPagination(flag) {
	if(dkdjTotalPages) {
		if("next" == flag) {
			dkdjPage ++;
		} else if("previous" == flag) {
			dkdjPage --;
		}
		if(dkdjPage > dkdjTotalPages) {
			dkdjPage = dkdjTotalPages;
		} 
		if(dkdjPage < 1) {
			dkdjPage = 1;
		}
		
		var dkdjTbody = document.all.dkdjTbody;
		var rowCount = dkdjTbody.rows.length;
		for(var i=0; i<rowCount; i++) {
			dkdjTbody.deleteRow(0);
		}
		setDkdj();
	}
}

/**
 * 添加一行代扣代缴
 * @param tzf
 */
function buildDkdjTr(dkdj) {
	var dkdjTbody = document.all.dkdjTbody;
	var rowCount = dkdjTbody.rows.length;
	
	var newRow = dkdjTbody.insertRow(rowCount++);	//新增一行
	var C0 = newRow.insertCell(0);
	C0.setAttribute("className","2-td2-center");
	C0.innerHTML= "&nbsp;" + dkdj.selectSingleNode("dkdjyw").text;
	var C1 = newRow.insertCell(1);
	C1.setAttribute("className","2-td2-right");
	C1.innerHTML= "&nbsp;" + dkdj.selectSingleNode("dkdjsz").text;
}
/*代扣代缴结束===============================================================================================*/

/**
 * 退出到主菜单
 */
function tuichu()
{
	if(mainURL==null || mainURL=="")
	{
		mainURL="/";
	}
	window.location=mainURL;
}
