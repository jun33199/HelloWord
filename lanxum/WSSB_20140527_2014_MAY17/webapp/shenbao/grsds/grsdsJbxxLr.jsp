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
<title>��������˰������Ϣ</title>
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
var sfzjlxdms;			//���֤�����ʹ����
var gjdqs;				//�������������
var swjgzzjgs;			//˰�������֯����
var hys;			    //��ҵ
var djzclxs;			    //�Ǽ�ע������

var endSave = false;

//�������֤�����ʹ����
function addSfzjlxdmsSelect(obj)
{
	for(var j=0;j<sfzjlxdms.length;j++){
		oOption = document.createElement("option");
    	oOption.text= sfzjlxdms[j].selectSingleNode("text").text;
    	oOption.value= sfzjlxdms[j].selectSingleNode("value").text;
    	obj.add(oOption);
	}
}

//���ӹ��������
function addGjdqSelect(obj)
{
	for(var j=0;j<gjdqs.length;j++){
		oOption = document.createElement("option");
    	oOption.text= gjdqs[j].selectSingleNode("text").text;
    	oOption.value= gjdqs[j].selectSingleNode("value").text;
    	obj.add(oOption);
	}
}

//��������˰�����
function addSwjgzzjgSelect(obj)
{
	for(var j=0;j<swjgzzjgs.length;j++){
		oOption = document.createElement("option");
    	oOption.text= swjgzzjgs[j].selectSingleNode("text").text;
    	oOption.value= swjgzzjgs[j].selectSingleNode("value").text;
    	obj.add(oOption);
	}
}
//��ҵ
function addHySelect(obj){
	for(var j=0;j<hys.length;j++){
		oOption = document.createElement("option");
    	oOption.text= hys[j].selectSingleNode("text").text;
    	oOption.value= hys[j].selectSingleNode("value").text;
    	obj.add(oOption);
	}
}

//�Ǽ�ע������
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
	if(actionType=='Return'){				//�˳�
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

//����xml
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

//�������ֵ
function setInput(xmlDoc)
{
	sfzjlxdms= xmlDoc.selectNodes("/taxdoc/sfzjlxList/sfzjlx");
	gjdqs = xmlDoc.selectNodes("/taxdoc/gjList/gj");
	swjgzzjgs = xmlDoc.selectNodes("/taxdoc/swjgzzjgList/swjgzzjg");
	hys = xmlDoc.selectNodes("/taxdoc/gjbzhyList/gjbzhy");
	djzclxs = xmlDoc.selectNodes("/taxdoc/djzclxList/djzclx");
	
	var grxx_nsrlx = document.getElementById("grxx_nsrlx");				//��˰������
	var grxx_nsrlx_cb = document.getElementsByName("grxx_nsrlx_cb");
	splitCheckBox(grxx_nsrlx,grxx_nsrlx_cb);
	
	var grxx_sfyjjnqk = document.getElementById("grxx_sfyjjnqk");				//����һ��������
	var grxx_sfyjjnqk_cb = document.getElementsByName("grxx_sfyjjnqk_cb");
	splitCheckBox(grxx_sfyjjnqk,grxx_sfyjjnqk_cb);
	
	var grxx_sfcjrlsgl = document.getElementById("grxx_sfcjrlsgl");				//�Ƿ�м�����������
	var grxx_sfcjrlsgl_cb = document.getElementsByName("grxx_sfcjrlsgl_cb");
	splitCheckBox(grxx_sfcjrlsgl,grxx_sfcjrlsgl_cb);
	
	var grxx_tzzlx = document.getElementById("grxx_tzzlx");					//Ͷ��������
	var grxx_tzzlx_cb = document.getElementsByName("grxx_tzzlx_cb");
	splitCheckBox(grxx_tzzlx,grxx_tzzlx_cb);

	
	
	
	// ���֤������
	var sfzjlx = xmlDoc.selectSingleNode("/taxdoc/grxx/grxx_sfzjlx").text;    
	var sfzjlxSelect = document.getElementById("grxx_sfzjlx");
	addSfzjlxdmsSelect(sfzjlxSelect);
	sfzjlxSelect.value = sfzjlx;
	
	//����
	var grxx_wzsnsr_gj = xmlDoc.selectSingleNode("/taxdoc/grxx/grxx_wzsnsr_gj").text;	//��ס����˰�˹���
	var grxx_wzsnsr_jwzfggb =  xmlDoc.selectSingleNode("taxdoc/grxx/grxx_wzsnsr_jwzfggb").text; //֧��������
	var gjSelect1 = document.getElementById("grxx_wzsnsr_gj");
	var gjSelect2 = document.getElementById("grxx_wzsnsr_jwzfggb");
	addGjdqSelect(gjSelect1);
	addGjdqSelect(gjSelect2);
	gjSelect1.value=grxx_wzsnsr_gj;
	gjSelect2.value=grxx_wzsnsr_jwzfggb;
	
	//����˰�����
	var qyxx_zgswjg = xmlDoc.selectSingleNode("taxdoc/qyxxvo/qyxx_zgswjg").text; //����˰�����
	var swjgzzjgSelect = document.getElementById("qyxx_zgswjg");
	addSwjgzzjgSelect(swjgzzjgSelect)
	swjgzzjgSelect.value = qyxx_zgswjg;
	
	
	//���ұ�׼��ҵ
	var qyxx_hy = xmlDoc.selectSingleNode("taxdoc/qyxxvo/qyxx_hy").text; //����˰�����
	var hySelect = document.getElementById("qyxx_hy");
	addHySelect(hySelect)
	hySelect.value = qyxx_hy;
	
	//�Ǽ�ע������
	var qyxx_djzclx = xmlDoc.selectSingleNode("taxdoc/qyxxvo/qyxx_djzclx").text; //�Ǽ�ע������
	var djzclxSelect = document.getElementById("qyxx_djzclx");
	addDjzclxSelect(djzclxSelect)
	djzclxSelect.value = qyxx_djzclx;
	
	//ְ��
	var grxx_zw = xmlDoc.selectSingleNode("taxdoc/grxx/grxx_zw").text; //����˰�����
	document.getElementById("grxx_zw").value=grxx_zw;
	
	//�о���������˰�˵�ַ����
	var grxx_yjwsd_dzlx = xmlDoc.selectSingleNode("taxdoc/grxx/grxx_yjwsd_dzlx").text; 
	document.getElementById("grxx_yjwsd_dzlx").value=grxx_yjwsd_dzlx;
	
	//����˰���շ�ʽ
	var qyxx_sdszsfs = xmlDoc.selectSingleNode("taxdoc/qyxxvo/qyxx_sdszsfs").text; 
	document.getElementById("qyxx_sdszsfs").value=qyxx_sdszsfs;
	
	//�Ա�
	var grxx_wzsnsr_xb = xmlDoc.selectSingleNode("taxdoc/grxx/grxx_wzsnsr_xb").text; 
	document.getElementById("grxx_wzsnsr_xb").value=grxx_wzsnsr_xb;
	
	//�Ƿ�˰��Э����Լ���Է�����
	var grxx_wzsnsr_sfssxddygdfjm = xmlDoc.selectSingleNode("taxdoc/grxx/grxx_wzsnsr_sfssxddygdfjm").text; 
	document.getElementById("grxx_wzsnsr_sfssxddygdfjm").value=grxx_wzsnsr_sfssxddygdfjm;
	
	//֧����
	var grxx_wzsnsr_zfd = xmlDoc.selectSingleNode("taxdoc/grxx/grxx_wzsnsr_zfd").text; 
	document.getElementById("grxx_wzsnsr_zfd").value=grxx_wzsnsr_zfd;
}

	//����buildxml��ĸ÷��� ����xml����
	function getPostXml()
	{
		var retstr;
		
		//��������
		getBasicXx("xsltVersion","/taxdoc");
		getBasicXx("schemaVersion","/taxdoc");
		getBasicXx("ywlx","/taxdoc");
		getBasicXx("ywczlx","/taxdoc");
		
		
		//��Ͷ����ҵ��Ϣ
		applendElement("/taxdoc","qyxxvo",["qyxx_btzzxm","qyxx_djzclx","qyxx_dz","qyxx_hy","qyxx_jsjdm",
		                                   "qyxx_kjywrbh","qyxx_nsrsbh","qyxx_sdszsfs","qyxx_yzbm","qyxx_zgswjg"]);
		
		//Ͷ������Ϣ
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
		
		
		//ȥ��ĩβ�Զ���ӵĻس�
		retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
		retstr = retstr.substr(0,retstr.length-2);
		
		return retstr;
	}

//���ɻ�����Ϣ
function getPostInf()
{
	initXMLObject();
	var htmlInf="actionType=Save&appType=&strSignData=&REQUEST_TOKEN=&strXMLData=";
	var xml = getPostXml().replace(/\r\n/g,"");
	return htmlInf+xml;
}

//��д��ѡ����Ϣ
function setCheckBoxInf(){
	var grxx_nsrlx = document.getElementById("grxx_nsrlx");				//��˰������
	var grxx_nsrlx_cb = document.getElementsByName("grxx_nsrlx_cb");
	joinCheckBox(grxx_nsrlx,grxx_nsrlx_cb);
	
	var grxx_sfyjjnqk = document.getElementById("grxx_sfyjjnqk");				//����һ��������
	var grxx_sfyjjnqk_cb = document.getElementsByName("grxx_sfyjjnqk_cb");
	joinCheckBox(grxx_sfyjjnqk,grxx_sfyjjnqk_cb);
	
	var grxx_sfcjrlsgl = document.getElementById("grxx_sfcjrlsgl");				//�Ƿ�м�����������
	var grxx_sfcjrlsgl_cb = document.getElementsByName("grxx_sfcjrlsgl_cb");
	joinCheckBox(grxx_sfcjrlsgl,grxx_sfcjrlsgl_cb);
	
	var grxx_tzzlx = document.getElementById("grxx_tzzlx");					//Ͷ��������
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

//��ajax��ʽpost
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
    
  //�������ݵĴ�����
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

//Ϊselect�������֤�����ʹ����
function addSfzjlxdmsSelect(obj)
{
	for(var j=0;j<sfzjlxdms.length;j++){
		oOption = document.createElement("option");
    	oOption.text= sfzjlxdms[j].selectSingleNode("text").text;
    	oOption.value= sfzjlxdms[j].selectSingleNode("value").text;
    	obj.add(oOption);
	}
}

//���滹���ش�ҳ��
function doSave()
{
	if(!check()) {
		return false;
	}
	var result = ajaxPostXML();
	if(result){
		endSave=true;
		alert("����ɹ�����������һҳ����д�걨��");
	}else{
		alert("����ʧ������ϵϵͳ����Ա");
	}
}

/**
 * ����У��
 */
function check() {
	// ����
	var grxx_xm = document.all.grxx_xm.value;
	if(!grxx_xm) {
		alert("��������Ϊ�գ�")
		return false;
	}
	
	// ����ʱ��
	var grxx_wzsnsr_lhsj = document.all.grxx_wzsnsr_lhsj.value;
	if(grxx_wzsnsr_lhsj && !checkDate(grxx_wzsnsr_lhsj)) {
		alert("����ʱ���ʽ����ȷ����ȷ��ʽ�磺20010101");
		return false;
	}
	
	// ��������
	var grxx_wzsnsr_csrq = document.all.grxx_wzsnsr_csrq.value;
	if(grxx_wzsnsr_csrq && !checkDate(grxx_wzsnsr_csrq)) {
		alert("�������ڸ�ʽ����ȷ����ȷ��ʽ�磺20010101");
		return false;
	}
	
	// Ԥ���뾳ʱ��
	var grxx_wzsnsr_yjljsj = document.all.grxx_wzsnsr_yjljsj.value;
	if(grxx_wzsnsr_yjljsj && !checkDate(grxx_wzsnsr_yjljsj)) {
		alert("Ԥ���뾳ʱ���ʽ����ȷ����ȷ��ʽ�磺20010101");
		return false;
	}
	
	//��������
	var grxx_email = document.all.grxx_email.value;
	if(grxx_email && !checkEmail(grxx_email)) {
		alert("���������ʽ����ȷ��");
		return false;
	}
	
	//��������
	var grxx_yzbm = document.all.grxx_yzbm.value;
	var grxx_yjwsd_yzbm = document.all.grxx_yjwsd_yzbm.value;
	var grxx_yjwsd_yzbm = document.all.grxx_yjwsd_yzbm.value;
	var grxx_wzsnsr_jnrzsgdw_yzbm = document.all.grxx_wzsnsr_jnrzsgdw_yzbm.value;
	var grxx_wzsnsr_jnspqydw_yzbm = document.all.grxx_wzsnsr_jnspqydw_yzbm.value;
	if((grxx_yzbm && !checkZip(grxx_yzbm))||(grxx_yjwsd_yzbm && !checkZip(grxx_yjwsd_yzbm))||(grxx_wzsnsr_jnrzsgdw_yzbm && !checkZip(grxx_wzsnsr_jnrzsgdw_yzbm))||(grxx_wzsnsr_jnspqydw_yzbm && !checkZip(grxx_wzsnsr_jnspqydw_yzbm))) 
	{
		alert("���������ʽ����ȷ��");
		return false;
	}
	return true;
}

/**
 * ��֤Email
 */
function checkEmail(strEmail) {
	if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1) {
		return true;
	} else {
		return false;
	}
}

/**
 * ��֤��������
 */
function checkZip(zip) {
	var reg = /^[1-9]\d{5}(?!\d)$/;
	if(reg.test(zip) == false) {
		return false;
	}
	return true;
}


/**
 * ���ʱ���ʽ
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
		if(confirm("����δ���浱ǰ�����Ƿ񱣴棿")){
			if(!check()) {
				return false;
			}
			
			var result = ajaxPostXML();
			if(result){
				alert("����ɹ�");
				return doSubmit('Next');
			}else{
				alert("����ʧ�ܣ�����ϵϵͳ����Ա");
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
    		<jsp:param name="name" value="��������˰������Ϣ" />
		<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>
	 </td>
    </tr>
    	
    <tr><td><html:errors/></td></tr>
    
 	<!-- xsl��ʽ�� -->
	<tr><td><br/><div id="result" align="center" ></div></td></tr>
    
    <!-- ��ť -->
    <tr>
	  <td align="center" valign="bottom">
	  		<br/>
		 	<div  align="center">
		  		<table>
		    	<TR class="black9">
				<TD>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:doSave();"><img name="save" value="����" alt="����" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>/images/baocun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/baocun1.jpg" width="79" height="22" id="save"></a>&nbsp;&nbsp;
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return nextPage();return false;"><img name="save" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>/images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/xaiyiye1.jpg" width="79" height="22" id="xiayiye"></a>&nbsp;&nbsp;
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Fanhui');return false;"><img name="fanhui" value="����" alt="����" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/fanhui1.jpg" width="79" height="22" id="fanhui"></a>&nbsp;&nbsp;
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Return');return false;"><img  name="tuichu" value="�˳�" alt="�˳�" onMouseOver="MM_swapImage('tuichu','','<%=static_contextpath%>/images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/tuichu1.jpg"  width="79" height="22" id="tuichu"></a> 
				
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
				    <td width="20%" align="center" class="black9">	<a style="cursor:hand;text-decoration:underline;"  tabIndex="-1" onClick="javascript:clickTbsm();"><strong><font color="#0000FF">�����鿴���˵��</font></strong></a></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47" id="tbsm" style="display:none;">
						һ��	���÷�Χ<br/>
&nbsp;&nbsp;������������Ȼ����˰�˻�����Ϣ�����<br/>
&nbsp;&nbsp;����˰����ؿɸ��ݱ���ʵ�ʣ�����Ȼ����˰�˳�����˰����ذ��������˰����ʱ����������걨���Ժ��������Ϣ�����仯ʱ���<br/>
����	���������д���£�<br/>
&nbsp;(һ)	��ͷ��<br/>
&nbsp;&nbsp;1.	��������д��˰���������й�������ס�����ˣ�������Ӧ���ֱ����С�������������д��<br/>
&nbsp;&nbsp;2.	���֤�����ͣ���д��˰����Ч���֤�����գ����ơ��й�������д���֤������֤��ʿ��֤��֤�����ƣ��й�������ס�����ˣ���д���ա��۰ľ��������ڵ�ͨ��֤��̨�����������½ͨ��֤��֤�����ơ�<br/>
&nbsp;&nbsp;3.	���֤�����룺��д���֤���ϵĺ��롣<br/>
&nbsp;&nbsp;4.	��˰�����ͣ���˰�˸�����������ڶ�Ӧ���ڴ򡰡̡����ɶ�ѡ��<br/>
&nbsp;&nbsp;&nbsp;��1��	����ְ�ܹ͵�λ����ָ��˰���й̶���ְ�ܹ͵�λ��<br/>
&nbsp;&nbsp;&nbsp;��2��	����ְ�ܹ͵�λ�������ɶ�Ͷ���ߣ�����ָ��˰��Ϊ����ְҵ�ߣ�û�����κε�λǩ����ְ�ܹͺ�ͬ��������ҵ�ɶ������幤�̻������˶�����ҵͶ���ߡ��ϻ���ҵ�ϻ��ˡ��а����⾭Ӫ�ߡ�<br/>
&nbsp;&nbsp;&nbsp;��3��	Ͷ���ߣ���ָ�ж���Ͷ�ʵ���˰�ˡ�<br/>
&nbsp;&nbsp;&nbsp;��4��	��ס�����ˣ���ָ���й�������ס������˰�ˡ�����ס�����������ס�����ԣ����й�������ס���ĸ��ˣ���ָ�򻧼�����ͥ�����������ϵ�����й�����ϰ���Ծ�ס�ĸ��ˡ�<br/>
&nbsp;&nbsp;5.	��ְ�ܹ͵�λ���Ƽ���˰��ʶ��ţ���д��˰��ǩ����ְ�ܹͺ�ͬ�ĵ�λ����ȫ�Ƽ�����˰����ذ���Ǽ�ʱ����˰��ʶ��š�ǰ�������ƣ���������˰��ʶ��š�<br/>
&nbsp;&nbsp;���ҵ�λǩ����ͬ�ģ��������ʾ��û�����<br/>
&nbsp;&nbsp;6.	������һ�𡱽����������˰�˸����Լ�������ᱣ�շ�����ڡ��������ϱ��շѡ���������ҽ�Ʊ��շѡ�����ʧҵ���շѡ�����ס�������𡱶�Ӧ���ڴ򡰡̡��������û�н��ɵģ��ڡ��ޡ����򡰡̡���<br/>
&nbsp;&nbsp;7.	�������䣺��д˰�����������˰����ϵ�ĵ��������ַ��<br/>
&nbsp;&nbsp;8.	������ϵ��ַ���������룺��д˰�����������˰����ϵ����Ч�й�������ϵ��ַ���������롣<br/>
&nbsp;&nbsp;9.	��ϵ�绰����д˰�����������˰����ϵ�ĵ绰��<br/>
&nbsp;&nbsp;10.	ְҵ����д��˰�������µ�ְҵ��ְҵ���ఴ�Ͷ�����ᱣ�ϲ��ŵĹ�����д��<br/>
&nbsp;&nbsp;11.	ְ����д��˰������ְ�ܹ͵�λ�����ε�ְ���ڡ��߲㡱�����в㡱������ͨ������ǰѡ����һ�򡰡̡���<br/>
&nbsp;&nbsp;12.	ѧ������д��˰��ȡ�õ�����ѧ����<br/>
&nbsp;&nbsp;13.	�Ƿ�м���/����/���ϣ����ϱ�������ģ��ڶ�Ӧ��ǰ�򡰡̡��������ڡ������򡰡̡���<br/>
&nbsp;(��)	�о������õ���˰����д������˰�˴��й�����ȡ�����õ���д������û�����<br/>
&nbsp;��˰����ѡ�����ʱ��Ӧ���ݡ�����˰���ֹܾ���ӡ������������˰������˰�걨�취�����У�����֪ͨ����ʮһ���ڶ�����й�����ȡ�����õģ����й����ڻ������ڵ�����˰������걨�����й������л��������������ڵ����й����ھ�����ס�ز�һ�µģ�ѡ�񲢹̶�������һ������˰������걨�����й�����û�л����ģ����й����ھ�����ס������˰������걨���Ĺ涨ѡ����д��<br/>
&nbsp;ѡ�����˰���ڡ��������ڵء��򡰾�����ס�ء���Ӧ���ڴ򡰡̡�����д�����ַ��<br/>
&nbsp;(��)	Ͷ������˰����д��������Ȼ�˹ɶ���Ͷ������д�����û�ж���Ͷ�ʵģ����<br/>
&nbsp;&nbsp;1.	Ͷ�������ͣ���˰�˸�����������ڶ�Ӧ���ڴ򡰡̡����ɶ�ѡ��<br/>
&nbsp;&nbsp;2.	��Ͷ�ʵ�λ��Ϣ����д��˰�˶���Ͷ�ʵ�λ���й���Ϣ��<br/>
&nbsp;&nbsp;&nbsp;��1��	��˰�����ƣ���д˰����غ˷��ı�Ͷ�ʵ�λ˰��Ǽ�֤��������˰������ȫ�ơ�Ͷ�ʶ�ҵ�λ�ģ���ֱ���ʾ��<br/>
&nbsp;&nbsp;&nbsp;��2��	�۽������˱��룺��д˰����غ˷���˰��Ǽ�֤���롣<br/>
&nbsp;&nbsp;&nbsp;��3��	��ַ���������룺��дͶ����Ͷ�ʵ�λ�ĵ�ַ���������롣<br/>
&nbsp;&nbsp;&nbsp;��4��	�Ǽ�ע�����ͣ���д��Ͷ�ʵ�λ�ڹ�������������صǼ�ע������͡�<br/>
&nbsp;&nbsp;&nbsp;��������ҵ��������ҵ��������ҵ���ɷݺ�����ҵ����Ӫ��ҵ���������ι�˾���ɷ����޹�˾��˽Ӫ��ҵ��������ҵ�����۰�̨��Ͷ����ҵ������Ͷ����ҵ�����ࡣ[ע��������ҵ�����������ڵ���ҵ���͡�]<br/>
&nbsp;&nbsp;&nbsp;��5��	��ҵ�����չ��񾭼���ҵ������ұ�׼��д�����ࡣ<br/>
&nbsp;&nbsp;&nbsp;��6��	����˰�������ͣ���д��Ͷ�ʵ�λ����˰�����շ�ʽ��<br/>
&nbsp;&nbsp;&nbsp;��7��	����˰����أ���д��Ͷ�ʵ�λ������˰��������ơ�<br/>
&nbsp;&nbsp;&nbsp;��8��	�ɶ�������Ͷ������д��������Ȼ�˹ɶ�������Ͷ������д�����幤�̻��������˶�����ҵͶ���ߡ��ϻ���ҵ�ϻ��ˡ��а����⾭Ӫ�߲���д������<br/>
&nbsp;&nbsp;&nbsp;&nbsp;�ٹ�˾�ɱ���Ͷ�ʣ��ܶ��д��Ͷ�ʵ�λ�Ĺ�˾�ɱ���Ͷ�ʣ��ܶ<br/>
&nbsp;&nbsp&nbsp;&nbsp;;�ڸ��˹ɱ���Ͷ�ʣ��ܶ��д��Ȼ�˹ɶ���Ͷ�����ڱ�Ͷ�ʵ�λ����Ͷ�ʵĹɱ���Ͷ�ʣ��<br/>
&nbsp;(��)	��ס����˰����д���������й�������ס����˰����д���������<br/>
&nbsp;&nbsp;��1��	��˰��ʶ��ţ���д����˰����ظ����18λ��˰��ʶ��š�����˰��ʶ�����Ϊ������ס�����˵�Ψһ���ʶ���룬����˰�˵�����˰����ذ��������˰������߿۽������˰������˰�˳��ο۽��걨ʱ��������˰��������衣<br/>
&nbsp;&nbsp;��2��	����������������д��˰�˵Ĺ������ߵ�����<br/>
&nbsp;&nbsp;��3��	�����أ���д��˰�˳����صĹ�����������<br/>
&nbsp;&nbsp;��4��	�Ͷ���ҵ֤���룺��д��˰�����й������Ͷ���ҵ֤�ϵĺ��롣<br/>
&nbsp;&nbsp;��5��	����ְ����д����˰���ھ��ڹ�˾���ε�ְ��<br/>
&nbsp;&nbsp;��6��	����ְ����д����˰���ھ��⹫˾���ε�ְ��<br/>
&nbsp;&nbsp;��7��	�Ƿ�˰��Э����Լ���Է�������˰�����������й�ǩ������˫����˰Э���Ĺ��һ�����ģ��ڡ��ǡ�����Ӧ���ڴ򡰡̡��������ڡ������򡰡̡���<br/>
&nbsp;&nbsp;��8��	����ʱ�䣺��д��˰�˵����й����ڵ������ա�<br/>
&nbsp;&nbsp;��9��	��ְ���ޣ���д��˰������ְ�ܹ͵�λ����ְ���ޡ�<br/>
&nbsp;&nbsp;��10��	Ԥ���뾳ʱ�䣺��д��˰��Ԥ���뾳�������ա�<br/>
&nbsp;&nbsp;��11��	Ԥ���뾳�ص㣺��д��˰��Ԥ���뾳�ĵص㡣<br/>
&nbsp;&nbsp;��12��	������ְ�ܹ͵�λ����д��˰��ǩ����ְ�ܹͺ�ͬ�ĵ�λ�������Ϣ�������д������������ƸǩԼ��λ��������д��<br/>
&nbsp;&nbsp;&nbsp;�����ƣ���д��˰����ְ�ܹ͵�λ������ȫ�ơ�<br/>
&nbsp;&nbsp;&nbsp;�ڿ۽������˱��룺��д˰�����ȷ������ְ�ܹ͵�λ��˰�������롣<br/>
&nbsp;&nbsp;&nbsp;�۵�ַ���������룺��д��ְ�ܹ͵�λ�ĵ�ַ���������롣<br/>
&nbsp;&nbsp;��13��	������ƸǩԼ��λ����д��˰����Ƹ��ǩԼ��λ�������Ϣ�������д������������������ְ�ܹ͵�λ��������д��<br/>
&nbsp;&nbsp;&nbsp;�����ƣ���д��˰����ƸǩԼ��λ������ȫ�ơ�<br/>
&nbsp;&nbsp;&nbsp;�ڿ۽������˱��룺��д˰�����ȷ������ƸǩԼ��λ��˰�������롣<br/>
&nbsp;&nbsp;&nbsp;�۵�ַ���������룺��д��ƸǩԼ��λ�ĵ�ַ���������롣<br/>
&nbsp;&nbsp;��14��	������ǲ��λ�������˰���о�����ǲ��λ�ģ���д������������д��<br/>
&nbsp;&nbsp;&nbsp;�����ƣ���д��˰�˾�����ǲ��λ������ȫ�ƣ����С�������������д��<br/>
&nbsp;&nbsp;&nbsp;�ڵ�ַ����д������ǲ��λ�ĵ�ַ��<br/>
&nbsp;&nbsp;��15��	֧���أ���д��˰��ȡ�õ����õ�֧���أ��ڡ�����֧������������֧�����͡���������ͬʱ֧��������������ѡ��һ����д��<br/>
&nbsp;&nbsp;��16��	����֧���ع��𣨵������������˰��ȡ�õ�����֧����Ϊ����ģ���д����֧���صĹ����������ơ�<br/>
				</td>
				    </tr>
				</table>
	  		<br>
	   </td>
	</tr>
	
  	<!-- ע������ -->
   	<tr>
	   <td class="1-td2-center" valign="bottom"><br/>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
				 <tr>
				    <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				    <td width="20%" align="center" class="black9"><strong><font color="#0000FF">ע������</font></strong></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47"  >
				        <p style ="color:red">
				            &nbsp;&nbsp;&nbsp;&nbsp;���������˱��Ǹ��ݡ��л����񹲺͹���������˰��������ʵʩ�����͹�����ط��ɷ���涨��д�ģ�����ʵ�ġ������ġ��ɿ��ġ�<br>
				         </p>
				       </td>
				    </tr>
				</table>
	  			<br>
	   </td>
	</tr>
	
	<!-- �� -->
	<tr>
  	<td valign="bottom" align="center">
		<%@ include file="../../include/bottom.jsp" %>
  	</td>
	</tr>
 </table>
 </form>
</body>
</html>