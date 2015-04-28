<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="com.ttsoft.bjtax.shenbao.taglib.SbzlTag"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.FriendHelper"%>
<%@page import="com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.Grsds2014Constant"%>
<html>
<head>
<title>个人所得税基础信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="gojs/XmlBuild.js"></script>
<script language=JavaScript>

var strXSLTVersion = '<%=request.getAttribute(Grsds2014Constant.XSLT_GRSDS_VERSION)==null?"":request.getAttribute(Grsds2014Constant.XSLT_GRSDS_VERSION)%>';
var strXml = '<%=request.getAttribute(Grsds2014Constant.XSLT_GRSDS_DATE)==null?"":request.getAttribute(Grsds2014Constant.XSLT_GRSDS_DATE)%>';
var sfzjlxdms;			//身份证件类型代码表
var gjdqs;				//国籍地区代码表
var swjgzzjgs;			//税务机关组织机构
var hys;			    //行业
var djzclxs;			    //登记注册类型

var endSave = false;

//增加身份证件类型代码表
function addSfzjlxdmsSelect(obj)
{
	for(var j=0;j<sfzjlxdms.length;j++){
		oOption = document.createElement("option");
    	oOption.text= sfzjlxdms[j].selectSingleNode("text").text;
    	oOption.value= sfzjlxdms[j].selectSingleNode("value").text;
    	obj.add(oOption);
	}
}

//增加国籍代码表
function addGjdqSelect(obj)
{
	for(var j=0;j<gjdqs.length;j++){
		oOption = document.createElement("option");
    	oOption.text= gjdqs[j].selectSingleNode("text").text;
    	oOption.value= gjdqs[j].selectSingleNode("value").text;
    	obj.add(oOption);
	}
}

//增加主管税务机关
function addSwjgzzjgSelect(obj)
{
	for(var j=0;j<swjgzzjgs.length;j++){
		oOption = document.createElement("option");
    	oOption.text= swjgzzjgs[j].selectSingleNode("text").text;
    	oOption.value= swjgzzjgs[j].selectSingleNode("value").text;
    	obj.add(oOption);
	}
}
//行业
function addHySelect(obj){
	for(var j=0;j<hys.length;j++){
		oOption = document.createElement("option");
    	oOption.text= hys[j].selectSingleNode("text").text;
    	oOption.value= hys[j].selectSingleNode("value").text;
    	obj.add(oOption);
	}
}

//登记注册类型
function addDjzclxSelect(obj){
	for(var j=0;j<djzclxs.length;j++){
		oOption = document.createElement("option");
    	oOption.text= djzclxs[j].selectSingleNode("text").text;
    	oOption.value= djzclxs[j].selectSingleNode("value").text;
    	obj.add(oOption);
	}
}

function doSubmit(actionType){
	var form=document.forms[0];
	if(actionType=='Return'){				//退出
		document.forms[0].action="quit.do";
		document.forms[0].submit();
		return true;
	}else if(actionType=='Next'){
		document.getElementById("query_jsjdm").value=document.getElementById("qyxx_jsjdm").value;
		document.getElementById("query_sfzjlx").value=document.getElementById("grxx_sfzjlx").value;
		document.getElementById("query_sfzjhm").value=document.getElementById("grxx_sfzjhm").value;
		document.forms[0].action="grsdsNdsbb2014.dc";
		document.forms[0].submit();
		return true;
	}else if(actionType=='Fanhui'){	
		document.forms[0].action="grsdsSb2014.dc";
		document.forms[0].submit();
		return true;
	}else{
		document.forms[0].action="quit.do";
		document.forms[0].submit();
		return true;
	}
}

//解析xml
function parseXmlOnLoad()
{
	//alert("parseXmlOnLoad");
	var urlXSL="/XSLTWEB/model/040002/XSLT/" +strXSLTVersion+".xsl";	//grsdsjbxx2014
    if (strXml != "")
    {
        if (! parseXml(strXml,urlXSL,"result"))
            return false;
	xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	xmlDoc.async = false;
	xmlDoc.loadXML(strXml);
    }
	setInput(xmlDoc);
    return true;
}

//添加特殊值
function setInput(xmlDoc)
{
	sfzjlxdms= xmlDoc.selectNodes("/taxdoc/sfzjlxList/sfzjlx");
	gjdqs = xmlDoc.selectNodes("/taxdoc/gjList/gj");
	swjgzzjgs = xmlDoc.selectNodes("/taxdoc/swjgzzjgList/swjgzzjg");
	hys = xmlDoc.selectNodes("/taxdoc/gjbzhyList/gjbzhy");
	djzclxs = xmlDoc.selectNodes("/taxdoc/djzclxList/djzclx");
	
	var grxx_nsrlx = document.getElementById("grxx_nsrlx");				//纳税人类型
	var grxx_nsrlx_cb = document.getElementsByName("grxx_nsrlx_cb");
	splitCheckBox(grxx_nsrlx,grxx_nsrlx_cb);
	
	var grxx_sfyjjnqk = document.getElementById("grxx_sfyjjnqk");				//三费一金缴纳情况
	var grxx_sfyjjnqk_cb = document.getElementsByName("grxx_sfyjjnqk_cb");
	splitCheckBox(grxx_sfyjjnqk,grxx_sfyjjnqk_cb);
	
	var grxx_sfcjrlsgl = document.getElementById("grxx_sfcjrlsgl");				//是否残疾人烈属孤老
	var grxx_sfcjrlsgl_cb = document.getElementsByName("grxx_sfcjrlsgl_cb");
	splitCheckBox(grxx_sfcjrlsgl,grxx_sfcjrlsgl_cb);
	
	var grxx_tzzlx = document.getElementById("grxx_tzzlx");					//投资者类型
	var grxx_tzzlx_cb = document.getElementsByName("grxx_tzzlx_cb");
	splitCheckBox(grxx_tzzlx,grxx_tzzlx_cb);

	
	
	
	// 身份证件类型
	var sfzjlx = xmlDoc.selectSingleNode("/taxdoc/grxx/grxx_sfzjlx").text;    
	var sfzjlxSelect = document.getElementById("grxx_sfzjlx");
	addSfzjlxdmsSelect(sfzjlxSelect);
	sfzjlxSelect.value = sfzjlx;
	
	//国籍
	var grxx_wzsnsr_gj = xmlDoc.selectSingleNode("/taxdoc/grxx/grxx_wzsnsr_gj").text;	//无住所纳税人国籍
	var grxx_wzsnsr_jwzfggb =  xmlDoc.selectSingleNode("taxdoc/grxx/grxx_wzsnsr_jwzfggb").text; //支付国国别
	var gjSelect1 = document.getElementById("grxx_wzsnsr_gj");
	var gjSelect2 = document.getElementById("grxx_wzsnsr_jwzfggb");
	addGjdqSelect(gjSelect1);
	addGjdqSelect(gjSelect2);
	gjSelect1.value=grxx_wzsnsr_gj;
	gjSelect2.value=grxx_wzsnsr_jwzfggb;
	
	//主管税务机关
	var qyxx_zgswjg = xmlDoc.selectSingleNode("taxdoc/qyxxvo/qyxx_zgswjg").text; //主管税务机关
	var swjgzzjgSelect = document.getElementById("qyxx_zgswjg");
	addSwjgzzjgSelect(swjgzzjgSelect)
	swjgzzjgSelect.value = qyxx_zgswjg;
	
	
	//国家标准行业
	var qyxx_hy = xmlDoc.selectSingleNode("taxdoc/qyxxvo/qyxx_hy").text; //主管税务机关
	var hySelect = document.getElementById("qyxx_hy");
	addHySelect(hySelect)
	hySelect.value = qyxx_hy;
	
	//登记注册类型
	var qyxx_djzclx = xmlDoc.selectSingleNode("taxdoc/qyxxvo/qyxx_djzclx").text; //登记注册类型
	var djzclxSelect = document.getElementById("qyxx_djzclx");
	addDjzclxSelect(djzclxSelect)
	djzclxSelect.value = qyxx_djzclx;
	
	//职务
	var grxx_zw = xmlDoc.selectSingleNode("taxdoc/grxx/grxx_zw").text; //主管税务机关
	document.getElementById("grxx_zw").value=grxx_zw;
	
	//有境外所得纳税人地址类型
	var grxx_yjwsd_dzlx = xmlDoc.selectSingleNode("taxdoc/grxx/grxx_yjwsd_dzlx").text; 
	document.getElementById("grxx_yjwsd_dzlx").value=grxx_yjwsd_dzlx;
	
	//所得税征收方式
	var qyxx_sdszsfs = xmlDoc.selectSingleNode("taxdoc/qyxxvo/qyxx_sdszsfs").text; 
	document.getElementById("qyxx_sdszsfs").value=qyxx_sdszsfs;
	
	//性别
	var grxx_wzsnsr_xb = xmlDoc.selectSingleNode("taxdoc/grxx/grxx_wzsnsr_xb").text; 
	document.getElementById("grxx_wzsnsr_xb").value=grxx_wzsnsr_xb;
	
	//是否税收协定缔约国对防居民
	var grxx_wzsnsr_sfssxddygdfjm = xmlDoc.selectSingleNode("taxdoc/grxx/grxx_wzsnsr_sfssxddygdfjm").text; 
	document.getElementById("grxx_wzsnsr_sfssxddygdfjm").value=grxx_wzsnsr_sfssxddygdfjm;
	
	//支付地
	var grxx_wzsnsr_zfd = xmlDoc.selectSingleNode("taxdoc/grxx/grxx_wzsnsr_zfd").text; 
	document.getElementById("grxx_wzsnsr_zfd").value=grxx_wzsnsr_zfd;
}

	//覆盖buildxml里的该方法 生成xml数据
	function getPostXml()
	{
		var retstr;
		
		//基本数据
		getBasicXx("xsltVersion","/taxdoc");
		getBasicXx("schemaVersion","/taxdoc");
		getBasicXx("ywlx","/taxdoc");
		getBasicXx("ywczlx","/taxdoc");
		
		
		//被投资企业信息
		applendElement("/taxdoc","qyxxvo",["qyxx_btzzxm","qyxx_djzclx","qyxx_dz","qyxx_hy","qyxx_jsjdm",
		                                   "qyxx_kjywrbh","qyxx_nsrsbh","qyxx_sdszsfs","qyxx_yzbm","qyxx_zgswjg"]);
		
		//投资者信息
		applendElement("/taxdoc","grxx",[ "grxx_cjdjqk","grxx_email","grxx_jnlxdz",
		                                   "grxx_lxdh","grxx_nsrlx","grxx_sfcjrlsgl","grxx_sfyjjnqk",
		                                   "grxx_sfzjhm","grxx_sfzjlx","grxx_tzzlx","grxx_wzsnsr_csd",
		                                   "grxx_wzsnsr_csrq","grxx_wzsnsr_gj","grxx_wzsnsr_jnrzsgdw_dz",
		                                   "grxx_wzsnsr_jnrzsgdw_kjywrbm","grxx_wzsnsr_jnrzsgdw_mc",
		                                   "grxx_wzsnsr_jnrzsgdw_yzbm","grxx_wzsnsr_jnspqydw_dz","grxx_wzsnsr_nsrsbh",
		                                   "grxx_wzsnsr_jnspqydw_kjywrbm","grxx_wzsnsr_jnspqydw_mc",
		                                   "grxx_wzsnsr_jnspqydw_yzbm","grxx_wzsnsr_jnzw","grxx_wzsnsr_jwpqdw_dz",
		                                   "grxx_wzsnsr_jwpqdw_mc","grxx_wzsnsr_jwzfggb","grxx_wzsnsr_jwzw",
		                                   "grxx_wzsnsr_ldjyzhm","grxx_wzsnsr_lhsj","grxx_wzsnsr_rzqx",
		                                   "grxx_wzsnsr_sfssxddygdfjm","grxx_wzsnsr_xb","grxx_wzsnsr_yjljdd",
		                                   "grxx_wzsnsr_yjljsj","grxx_wzsnsr_zfd","grxx_xl","grxx_xm",
		                                   "grxx_yjwsd_dz","grxx_yjwsd_dzlx","grxx_yjwsd_yzbm","grxx_yzbm",
		                                   "grxx_zw","grxx_zy","grxx_nd","grxx_rzsgdwmc","grxx_rzsgdwnsrsbh"]);
		
		
		//去掉末尾自动添加的回车
		retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
		retstr = retstr.substr(0,retstr.length-2);
		
		return retstr;
	}

//生成基本信息
function getPostInf()
{
	initXMLObject();
	var htmlInf="actionType=Save&appType=&strSignData=&REQUEST_TOKEN=&strXMLData=";
	var xml = getPostXml().replace(/\r\n/g,"");
	return htmlInf+xml;
}

//填写多选框信息
function setCheckBoxInf(){
	var grxx_nsrlx = document.getElementById("grxx_nsrlx");				//纳税人类型
	var grxx_nsrlx_cb = document.getElementsByName("grxx_nsrlx_cb");
	joinCheckBox(grxx_nsrlx,grxx_nsrlx_cb);
	
	var grxx_sfyjjnqk = document.getElementById("grxx_sfyjjnqk");				//三费一金缴纳情况
	var grxx_sfyjjnqk_cb = document.getElementsByName("grxx_sfyjjnqk_cb");
	joinCheckBox(grxx_sfyjjnqk,grxx_sfyjjnqk_cb);
	
	var grxx_sfcjrlsgl = document.getElementById("grxx_sfcjrlsgl");				//是否残疾人烈属孤老
	var grxx_sfcjrlsgl_cb = document.getElementsByName("grxx_sfcjrlsgl_cb");
	joinCheckBox(grxx_sfcjrlsgl,grxx_sfcjrlsgl_cb);
	
	var grxx_tzzlx = document.getElementById("grxx_tzzlx");					//投资者类型
	var grxx_tzzlx_cb = document.getElementsByName("grxx_tzzlx_cb");
	joinCheckBox(grxx_tzzlx,grxx_tzzlx_cb);
	
}

function joinCheckBox(obj1,obj2){
	var length = obj2.length;
	var result="";
	for(var i=0;i<length;i++){
		if(obj2[i].checked){
			result+=obj2[i].value;
			result+="|";
		}
	}
	obj1.value=result;
}

function splitCheckBox(obj1,obj2){
	var values = obj1.value.split("|");
	for(var i=0;i<values.length;i++){
		for(var j=0;j<obj2.length;j++){
			if(values[i]==obj2[j].value){
				obj2[j].checked=true;
			}
		}
	}
}

//以ajax方式post
function ajaxPostXML()
{
	setCheckBoxInf();
	var postStr= getPostInf();
	var ajax;
    if(window.ActiveXObject){
    	ajax=new ActiveXObject("Microsoft.XMLHTTP");
	}else if(window.XMLHttpRequest){
	    ajax=new XMLHttpRequest();
	} else {
	    return;
	}
    ajax.open("POST", "/shenbao/grsdsJbxxb2014.dc" ,false);
    ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8"); 
    
    var backResult = false;
    
  //返回数据的处理函数
    ajax.onreadystatechange = function(){
        if (ajax.readyState == 4 && ajax.status == 200)
        {
        	var xmlDOM1 = ajax.responseXML;
     		var result = xmlDOM1.selectSingleNode("/re").text;
				if("SUCCESS"==result)
				{
					backResult=true;
				}
        }
   };
   //alert(postStr);
   ajax.send(postStr);
   return backResult;
}

//为select增加身份证件类型代码表
function addSfzjlxdmsSelect(obj)
{
	for(var j=0;j<sfzjlxdms.length;j++){
		oOption = document.createElement("option");
    	oOption.text= sfzjlxdms[j].selectSingleNode("text").text;
    	oOption.value= sfzjlxdms[j].selectSingleNode("value").text;
    	obj.add(oOption);
	}
}

//保存还返回此页面
function doSave()
{
	if(!check()) {
		return false;
	}
	var result = ajaxPostXML();
	if(result){
		endSave=true;
		alert("保存成功，请点击”下一页“填写申报表");
	}else{
		alert("保存失败请联系系统管理员");
	}
}

/**
 * 数据校验
 */
function check() {
	// 姓名
	var grxx_xm = document.all.grxx_xm.value;
	if(!grxx_xm) {
		alert("姓名不能为空！")
		return false;
	}
	
	// 来华时间
	var grxx_wzsnsr_lhsj = document.all.grxx_wzsnsr_lhsj.value;
	if(grxx_wzsnsr_lhsj && !checkDate(grxx_wzsnsr_lhsj)) {
		alert("来华时间格式不正确！正确格式如：20010101");
		return false;
	}
	
	// 出生日期
	var grxx_wzsnsr_csrq = document.all.grxx_wzsnsr_csrq.value;
	if(grxx_wzsnsr_csrq && !checkDate(grxx_wzsnsr_csrq)) {
		alert("出生日期格式不正确！正确格式如：20010101");
		return false;
	}
	
	// 预计离境时间
	var grxx_wzsnsr_yjljsj = document.all.grxx_wzsnsr_yjljsj.value;
	if(grxx_wzsnsr_yjljsj && !checkDate(grxx_wzsnsr_yjljsj)) {
		alert("预计离境时间格式不正确！正确格式如：20010101");
		return false;
	}
	
	//电子邮箱
	var grxx_email = document.all.grxx_email.value;
	if(grxx_email && !checkEmail(grxx_email)) {
		alert("电子邮箱格式不正确！");
		return false;
	}
	
	//邮政编码
	var grxx_yzbm = document.all.grxx_yzbm.value;
	var grxx_yjwsd_yzbm = document.all.grxx_yjwsd_yzbm.value;
	var grxx_yjwsd_yzbm = document.all.grxx_yjwsd_yzbm.value;
	var grxx_wzsnsr_jnrzsgdw_yzbm = document.all.grxx_wzsnsr_jnrzsgdw_yzbm.value;
	var grxx_wzsnsr_jnspqydw_yzbm = document.all.grxx_wzsnsr_jnspqydw_yzbm.value;
	if((grxx_yzbm && !checkZip(grxx_yzbm))||(grxx_yjwsd_yzbm && !checkZip(grxx_yjwsd_yzbm))||(grxx_wzsnsr_jnrzsgdw_yzbm && !checkZip(grxx_wzsnsr_jnrzsgdw_yzbm))||(grxx_wzsnsr_jnspqydw_yzbm && !checkZip(grxx_wzsnsr_jnspqydw_yzbm))) 
	{
		alert("邮政编码格式不正确！");
		return false;
	}
	return true;
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
 * 检查时间格式
 */
function checkDate(dateVal) {
	if(!dateVal) {
		return false;
	}
	if (/^\d{4}1(0|1|2)(0|1|2)\d{1}$/.test(dateVal) 
			|| /^\d{4}0(1|2|3|4|5|6|7|8|9)(0|1|2)\d{1}$/.test(dateVal)
			|| /^\d{4}1(0|1|2)3(0|1)$/.test(dateVal)
			|| /^\d{4}0(1|2|3|4|5|6|7|8|9)3(0|1)$/.test(dateVal)) {
		return true;
	}
	return false;
}


function nextPage()
{
	if(!endSave){
		if(confirm("您还未保存当前数据是否保存？")){
			if(!check()) {
				return false;
			}
			
			var result = ajaxPostXML();
			if(result){
				alert("保存成功");
				return doSubmit('Next');
			}else{
				alert("保存失败，请联系系统管理员");
				return false;
			}
		}else{
			return doSubmit('Next');
		}
	}else{
		return doSubmit('Next');
	}
}
function clickTbsm(){
	var txsm = document.getElementById("tbsm");
	var display = txsm.style.display;
	if(display=="none"){
		txsm.style.display="";
	}else{
		txsm.style.display="none";
	}
}



</script>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onLoad="return parseXmlOnLoad();">
	 
<form method="post" name="grsdsjbxx" action="grsdsJbxxb2014.dc">
<input name="actionType" type="hidden" id="actionType" value="Show">
<table width="97%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
     <td align="left" valign="top" >
    	<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="个人所得税基本信息" />
		<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>
	 </td>
    </tr>
    	
    <tr><td><html:errors/></td></tr>
    
 	<!-- xsl样式表 -->
	<tr><td><br/><div id="result" align="center" ></div></td></tr>
    
    <!-- 按钮 -->
    <tr>
	  <td align="center" valign="bottom">
	  		<br/>
		 	<div  align="center">
		  		<table>
		    	<TR class="black9">
				<TD>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:doSave();"><img name="save" value="保存" alt="保存" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>/images/baocun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/baocun1.jpg" width="79" height="22" id="save"></a>&nbsp;&nbsp;
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return nextPage();return false;"><img name="save" value="下一页" alt="下一页" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>/images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/xaiyiye1.jpg" width="79" height="22" id="xiayiye"></a>&nbsp;&nbsp;
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Fanhui');return false;"><img name="fanhui" value="返回" alt="返回" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/fanhui1.jpg" width="79" height="22" id="fanhui"></a>&nbsp;&nbsp;
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Return');return false;"><img  name="tuichu" value="退出" alt="退出" onMouseOver="MM_swapImage('tuichu','','<%=static_contextpath%>/images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/tuichu1.jpg"  width="79" height="22" id="tuichu"></a> 
				
				</TD>
				</TR>
		    	</table>
			</div>
	   </td>
	</tr>
	
   <tr>
	   <td class="1-td2-center" valign="bottom"><br/>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
				 <tr>
				    <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				    <td width="20%" align="center" class="black9">	<a style="cursor:hand;text-decoration:underline;"  tabIndex="-1" onClick="javascript:clickTbsm();"><strong><font color="#0000FF">请点击查看填表说明</font></strong></a></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47" id="tbsm" style="display:none;">
						一、	适用范围<br/>
&nbsp;&nbsp;本表适用于自然人纳税人基础信息的填报。<br/>
&nbsp;&nbsp;各地税务机关可根据本地实际，由自然人纳税人初次向税务机关办理相关涉税事宜时填报本表；初次申报后，以后仅需在信息发生变化时填报。<br/>
二、	本表各栏填写如下：<br/>
&nbsp;(一)	表头栏<br/>
&nbsp;&nbsp;1.	姓名：填写纳税人姓名。中国境内无住所个人，其姓名应当分别用中、外两种文字填写。<br/>
&nbsp;&nbsp;2.	身份证件类型：填写纳税人有效身份证件（照）名称。中国居民，填写身份证、军官证、士兵证等证件名称；中国境内无住所个人，填写护照、港澳居民来往内地通行证、台湾居民来往大陆通行证等证照名称。<br/>
&nbsp;&nbsp;3.	身份证件号码：填写身份证件上的号码。<br/>
&nbsp;&nbsp;4.	纳税人类型：纳税人根据自身情况在对应框内打“√”，可多选。<br/>
&nbsp;&nbsp;&nbsp;（1）	有任职受雇单位：是指纳税人有固定任职受雇单位。<br/>
&nbsp;&nbsp;&nbsp;（2）	无任职受雇单位（不含股东投资者）：是指纳税人为自由职业者，没有与任何单位签订任职受雇合同；不含企业股东、个体工商户、个人独资企业投资者、合伙企业合伙人、承包承租经营者。<br/>
&nbsp;&nbsp;&nbsp;（3）	投资者：是指有对外投资的纳税人。<br/>
&nbsp;&nbsp;&nbsp;（4）	无住所个人：是指在中国境内无住所的纳税人。“无住所”是相对有住所而言；在中国境内有住所的个人，是指因户籍、家庭、经济利益关系而在中国境内习惯性居住的个人。<br/>
&nbsp;&nbsp;5.	任职受雇单位名称及纳税人识别号：填写纳税人签订任职受雇合同的单位名称全称及其在税务机关办理登记时的纳税人识别号。前列填名称，后列填纳税人识别号。<br/>
&nbsp;&nbsp;与多家单位签订合同的，须分行列示。没有则不填。<br/>
&nbsp;&nbsp;6.	“三费一金”缴纳情况：纳税人根据自己缴纳社会保险费情况在“基本养老保险费”、“基本医疗保险费”、“失业保险费”、“住房公积金”对应框内打“√”；如果都没有缴纳的，在“无”栏打“√”。<br/>
&nbsp;&nbsp;7.	电子邮箱：填写税务机关能与纳税人联系的电子邮箱地址。<br/>
&nbsp;&nbsp;8.	境内联系地址、邮政编码：填写税务机关能与纳税人联系的有效中国境内联系地址和邮政编码。<br/>
&nbsp;&nbsp;9.	联系电话：填写税务机关能与纳税人联系的电话。<br/>
&nbsp;&nbsp;10.	职业：填写纳税人所从事的职业。职业分类按劳动和社会保障部门的国标填写。<br/>
&nbsp;&nbsp;11.	职务：填写纳税人在任职受雇单位所担任的职务，在“高层”、“中层”、“普通”三项前选择其一打“√”。<br/>
&nbsp;&nbsp;12.	学历：填写纳税人取得的最终学历。<br/>
&nbsp;&nbsp;13.	是否残疾人/烈属/孤老：符合本栏情况的，在对应框前打“√”；否则，在“否”栏打“√”。<br/>
&nbsp;(二)	有境外所得的纳税人填写栏：纳税人从中国境外取得所得的填写本栏；没有则不填。<br/>
&nbsp;纳税人在选填此栏时，应根据《国家税务总局关于印发〈个人所得税自行纳税申报办法（试行）〉的通知》第十一条第二款“从中国境外取得所得的，向中国境内户籍所在地主管税务机关申报。在中国境内有户籍，但户籍所在地与中国境内经常居住地不一致的，选择并固定向其中一地主管税务机关申报。在中国境内没有户籍的，向中国境内经常居住地主管税务机关申报”的规定选择填写。<br/>
&nbsp;选择后，纳税人在“户籍所在地”或“经常居住地”对应框内打“√”并填写具体地址。<br/>
&nbsp;(三)	投资者纳税人填写栏：由自然人股东、投资者填写。如果没有对外投资的，则不填。<br/>
&nbsp;&nbsp;1.	投资者类型：纳税人根据自身情况在对应框内打“√”，可多选。<br/>
&nbsp;&nbsp;2.	被投资单位信息：填写纳税人对外投资单位的有关信息。<br/>
&nbsp;&nbsp;&nbsp;（1）	纳税人名称：填写税务机关核发的被投资单位税务登记证载明的纳税人名称全称。投资多家单位的，需分别列示。<br/>
&nbsp;&nbsp;&nbsp;（2）	扣缴义务人编码：填写税务机关核发的税务登记证号码。<br/>
&nbsp;&nbsp;&nbsp;（3）	地址、邮政编码：填写投资者投资单位的地址和邮政编码。<br/>
&nbsp;&nbsp;&nbsp;（4）	登记注册类型：填写被投资单位在工商行政管理机关登记注册的类型。<br/>
&nbsp;&nbsp;&nbsp;分内资企业（国有企业、集体企业、股份合作企业、联营企业、有限责任公司、股份有限公司、私营企业和其他企业）、港澳台商投资企业和外商投资企业三大类。[注：内资企业需填至括号内的企业类型。]<br/>
&nbsp;&nbsp;&nbsp;（5）	行业：按照国民经济行业分类国家标准填写至大类。<br/>
&nbsp;&nbsp;&nbsp;（6）	所得税征收类型：填写被投资单位所得税的征收方式。<br/>
&nbsp;&nbsp;&nbsp;（7）	主管税务机关：填写被投资单位的主管税务机关名称。<br/>
&nbsp;&nbsp;&nbsp;（8）	股东及其他投资者填写栏：由自然人股东和其他投资者填写。个体工商户主、个人独资企业投资者、合伙企业合伙人、承包承租经营者不填写此栏。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;①公司股本（投资）总额：填写被投资单位的公司股本（投资）总额。<br/>
&nbsp;&nbsp&nbsp;&nbsp;;②个人股本（投资）总额：填写自然人股东、投资者在被投资单位个人投资的股本（投资）额。<br/>
&nbsp;(四)	无住所纳税人填写栏：由在中国境内无住所纳税人填写。其他则不填。<br/>
&nbsp;&nbsp;（1）	纳税人识别号：填写主管税务机关赋予的18位纳税人识别号。该纳税人识别号作为境内无住所个人的唯一身份识别码，由纳税人到主管税务机关办理初次涉税事项，或者扣缴义务人办理该纳税人初次扣缴申报时，由主管税务机关授予。<br/>
&nbsp;&nbsp;（2）	国籍（地区）：填写纳税人的国籍或者地区。<br/>
&nbsp;&nbsp;（3）	出生地：填写纳税人出生地的国籍及地区。<br/>
&nbsp;&nbsp;（4）	劳动就业证号码：填写纳税人在中国境内劳动就业证上的号码。<br/>
&nbsp;&nbsp;（5）	境内职务：填写该纳税人在境内公司担任的职务。<br/>
&nbsp;&nbsp;（6）	境外职务：填写该纳税人在境外公司担任的职务。<br/>
&nbsp;&nbsp;（7）	是否税收协定缔约国对方居民：纳税人来自于与中国签订避免双重征税协定的国家或地区的，在“是”栏对应框内打“√”；否则，在“否”栏打“√”。<br/>
&nbsp;&nbsp;（8）	来华时间：填写纳税人到达中国境内的年月日。<br/>
&nbsp;&nbsp;（9）	任职期限：填写纳税人在任职受雇单位的任职期限。<br/>
&nbsp;&nbsp;（10）	预计离境时间：填写纳税人预计离境的年月日。<br/>
&nbsp;&nbsp;（11）	预计离境地点：填写纳税人预计离境的地点。<br/>
&nbsp;&nbsp;（12）	境内任职受雇单位：填写纳税人签订任职受雇合同的单位的相关信息。如果填写本栏，则境内受聘签约单位栏不用填写。<br/>
&nbsp;&nbsp;&nbsp;①名称：填写纳税人任职受雇单位的名称全称。<br/>
&nbsp;&nbsp;&nbsp;②扣缴义务人编码：填写税务机关确定的任职受雇单位的税务编码号码。<br/>
&nbsp;&nbsp;&nbsp;③地址、邮政编码：填写任职受雇单位的地址和邮政编码。<br/>
&nbsp;&nbsp;（13）	境内受聘签约单位：填写纳税人受聘或签约单位的相关信息。如果填写本栏，则上栏境内任职受雇单位栏则不用填写。<br/>
&nbsp;&nbsp;&nbsp;①名称：填写纳税人受聘签约单位的名称全称。<br/>
&nbsp;&nbsp;&nbsp;②扣缴义务人编码：填写税务机关确定的受聘签约单位的税务编码号码。<br/>
&nbsp;&nbsp;&nbsp;③地址、邮政编码：填写受聘签约单位的地址和邮政编码。<br/>
&nbsp;&nbsp;（14）	境外派遣单位：如果纳税人有境外派遣单位的，填写本栏。否则不填写。<br/>
&nbsp;&nbsp;&nbsp;①名称：填写纳税人境外派遣单位的名称全称，用中、外两种文字填写。<br/>
&nbsp;&nbsp;&nbsp;②地址：填写境外派遣单位的地址。<br/>
&nbsp;&nbsp;（15）	支付地：填写纳税人取得的所得的支付地，在“境内支付”、“境外支付”和“境、内外同时支付”三种类型中选择一种填写。<br/>
&nbsp;&nbsp;（16）	境外支付地国别（地区）：如果纳税人取得的所得支付地为国外的，填写境外支付地的国别或地区名称。<br/>
				</td>
				    </tr>
				</table>
	  		<br>
	   </td>
	</tr>
	
  	<!-- 注意事项 -->
   	<tr>
	   <td class="1-td2-center" valign="bottom"><br/>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
				 <tr>
				    <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				    <td width="20%" align="center" class="black9"><strong><font color="#0000FF">注意事项</font></strong></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47"  >
				        <p style ="color:red">
				            &nbsp;&nbsp;&nbsp;&nbsp;谨声明：此表是根据《中华人民共和国个人所得税法》及其实施条例和国家相关法律法规规定填写的，是真实的、完整的、可靠的。<br>
				         </p>
				       </td>
				    </tr>
				</table>
	  			<br>
	   </td>
	</tr>
	
	<!-- 底 -->
	<tr>
  	<td valign="bottom" align="center">
		<%@ include file="../../include/bottom.jsp" %>
  	</td>
	</tr>
 </table>
 </form>
</body>
</html>