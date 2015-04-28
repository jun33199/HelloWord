<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="com.ttsoft.bjtax.shenbao.taglib.SbzlTag"%>
<%@page import="com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.Grsds2014Constant"%>

<html>
<head>
<title>生产、经营所得个人所得税纳税申报表</title>
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

var sfzjlxdms;		//身份证件类型代码表
var gjdqs;			//国籍地区代码表

var endSave=false;

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

function doSubmit(actionType){
	var form=document.forms[0];
	if(actionType=='Return'){				//退出
		document.forms[0].action="quit.do";
		document.forms[0].submit();
		return true;
	}else if(actionType=='Save'){	
		document.forms[0].action="grsdsNdsbb2014.dc";
		document.forms[0].submit();
		return true;
	}else if(actionType=='Shangyy'){
		document.getElementById("query_jsjdm").value=document.getElementById("btzzxx_jsjdm").value;
		document.getElementById("query_sfzjlx").value=document.getElementById("tzzxx_sfzjlx").value;
		document.getElementById("query_sfzjhm").value=document.getElementById("tzzxx_sfzjhm").value;
		document.forms[0].action="grsdsJbxxb2014.dc";
		document.forms[0].submit();
		return true;
	}else if(actionType=='Fanhui'){	
		document.forms[0].action="grsdsSb2014.dc";
		document.forms[0].submit();
		return true;
	}else{
		document.forms[0].actionType.value="Return";
		document.forms[0].submit();
		return true;
	}
}

//解析xml
function parseXmlOnLoad()
{
	//alert("parseXmlOnLoad");
	var urlXSL="/XSLTWEB/model/040001/XSLT/" +strXSLTVersion+".xsl";	//grsdsndsbb2014
    if (strXml != "")
    {
        if (! parseXml(strXml,urlXSL,"result"))
            return false;
	xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	xmlDoc.async = false;
	xmlDoc.loadXML(strXml);
    }
	setInput();
	jiSuan();
	js();
	if(document.all.btzzxx_djzclx_3.checked) {//纳税人为合伙企业合伙人
		document.getElementById("hc_43").innerHTML="43=(38-39)*40-41-42";
	} else if(!document.all.btzzxx_djzclx_3.checked) {//纳税人为非合伙企业合伙人
		document.getElementById("hc_43").innerHTML="43=38-39-41-42";
	}
	
    return true;
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
	applendElement("/taxdoc","inf_qy",["btzzxx_jsjdm","btzzxx_name","btzzxx_nsrsbh","btzzxx_djzclx","btzzxx_npjzgrs",
	                                   "btzzxx_gzze","btzzxx_tzzrs","col_1","col_2","col_3","col_4","col_5","col_6",
	                                   "col_7","col_8","col_9","col_10","col_11","col_12","col_13","col_14","col_15",
	                                   "col_16","col_17","col_18","col_19","col_20","col_21","col_22","col_23","col_24",
	                                   "col_25","col_26","col_27","col_28","col_29","col_30","col_31","col_32","col_33",
	                                   "col_34","col_35","col_36","col_37","col_38","col_39"]);
	
	//投资者信息
	applendElement("/taxdoc","inf_gr",["skssqq","skssqz","tzzxx_gj","tzzxx_name","tzzxx_nsrsbh","tzzxx_sfzjhm","tzzxx_sfzjlx",
	                                   "col_40","col_41","col_42","col_43","col_44","col_45","col_46","col_47","col_48","col_49",
	                                   "col_50","col_51"]);
	
	
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

	function setInput()
	{
		sfzjlxdms= xmlDoc.selectNodes("/taxdoc/sfzjlxList/sfzjlx");
		gjdqs = xmlDoc.selectNodes("/taxdoc/gjList/gj");
		
		var btzzxx_djzclx = document.getElementById("btzzxx_djzclx");				//纳税人类型
		var btzzxx_djzclx_cb = document.getElementsByName("btzzxx_djzclx_cb");
		splitCheckBox(btzzxx_djzclx,btzzxx_djzclx_cb);
		
		var sfzjlx = xmlDoc.selectSingleNode("/taxdoc/inf_gr/tzzxx_sfzjlx").text;     //身份证件类型
		var sfzjlxSelect = document.getElementById("tzzxx_sfzjlx");       			 //身份证件类型
		addSfzjlxdmsSelect(sfzjlxSelect);
		sfzjlxSelect.value = sfzjlx;
		
		var gjdq = xmlDoc.selectSingleNode("/taxdoc/inf_gr/tzzxx_gj").text;    //国籍地区
		var gjdqSelect = document.getElementById("tzzxx_gj");
		addGjdqSelect(gjdqSelect);
		gjdqSelect.value = gjdq;
	}
	
	//以ajax方式post
	function ajaxPostXML()
	{
		var postStr= getPostInf();
		var ajax;
	    if(window.ActiveXObject){
	    	ajax=new ActiveXObject("Microsoft.XMLHTTP");
		}else if(window.XMLHttpRequest){
		    ajax=new XMLHttpRequest();
		} else {
		    return;
		}
	    ajax.open("GET", "/shenbao/grsdsNdsbb2014.dc" ,false);
	    ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
	    
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
	   
	   ajax.send(postStr);
	   return backResult;
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
	
	function beforeSubmit(){
		var btzzxx_djzclx = document.getElementById("btzzxx_djzclx");				//被投资单位登记注册类型
		var btzzxx_djzclx_cb = document.getElementsByName("btzzxx_djzclx_cb");
		joinCheckBox(btzzxx_djzclx,btzzxx_djzclx_cb);
	}
	
	function doSave()
	{
		beforeSubmit();
		var result = ajaxPostXML();
		if(result){
			endSave=true;
			alert("保存成功");
		}else{
			alert("保存失败请联系系统管理员");
		}
		return false;
	}

function jiSuan() {
	var btzzxx_djzclx_3 = document.getElementById("btzzxx_djzclx_3");
	
	document.all.col_1 .onchange = colChange;
	document.all.col_2 .onchange = colChange;
	document.all.col_3 .onchange = colChange;
	document.all.col_4 .onchange = colChange;
	document.all.col_5 .onchange = colChange;
	document.all.col_6 .onchange = colChange;
	document.all.col_7 .onchange = colChange;
	document.all.col_8 .onchange = colChange;
	document.all.col_9 .onchange = colChange;
	document.all.col_10.onchange = colChange;
	document.all.col_11.onchange = colChange;
	document.all.col_12.onchange = colChange;
	document.all.col_13.onchange = colChange;
	document.all.col_14.onchange = colChange;
	document.all.col_15.onchange = colChange;
	document.all.col_16.onchange = colChange;
	document.all.col_17.onchange = colChange;
	document.all.col_18.onchange = colChange;
	document.all.col_19.onchange = colChange;
	document.all.col_20.onchange = colChange;
	document.all.col_21.onchange = colChange;
	document.all.col_22.onchange = colChange;
	document.all.col_23.onchange = colChange;
	document.all.col_24.onchange = colChange;
	document.all.col_25.onchange = colChange;
	document.all.col_26.onchange = colChange;
	document.all.col_27.onchange = colChange;
	document.all.col_28.onchange = colChange;
	document.all.col_29.onchange = colChange;
	document.all.col_30.onchange = colChange;
	document.all.col_31.onchange = colChange;
	document.all.col_32.onchange = colChange;
	document.all.col_33.onchange = colChange;
	document.all.col_34.onchange = colChange;
	document.all.col_35.onchange = colChange;
	document.all.col_36.onchange = colChange;
	document.all.col_37.onchange = colChange;
	document.all.col_38.onchange = colChange;
	document.all.col_39.onchange = colChange;
	document.all.col_40.onchange = colChange40;
	document.all.col_41.onchange = colChange;
	document.all.col_42.onchange = colChange;
	document.all.col_43.onchange = colChange;
	document.all.col_44.onchange = colChange;
	document.all.col_45.onchange = colChange;
	document.all.col_46.onchange = colChange;
	document.all.col_47.onchange = colChange;
	document.all.col_48.onchange = colChange;
	document.all.col_49.onchange = colChange;
	document.all.col_50.onchange = colChange;
	document.all.col_51.onchange = colChange;
	
	btzzxx_djzclx_3.onblur=js;
	return true;
}

function colChange(obj){
	//for(var i=0;i<51;){
	//	var obj = eval("document.all.col_"+(++i));
		if(isNaN(this.value) || this.value=="" ){
			this.value = "0.00";
		}else{
			this.value = parseFloat(this.value).toFixed(2);
		}
	//}
	
	js();
}
function colChange40(obj){
	//for(var i=0;i<51;){
	//	var obj = eval("document.all.col_"+(++i));
	//isNaN函数用于验证非数字，但对空字符串无效。搞笑啊 changed 20150325
		if(isNaN(this.value) || this.value=="" ){
			this.value = "0.0000";
		}else{
			this.value = parseFloat(this.value).toFixed(4);
		}
	//}
	
	js();
}

function js() {
	var col_1 = parseFloat(document.all.col_1.value||"0");
	var col_2 = parseFloat(document.all.col_2.value||"0");
	var col_3 = parseFloat(document.all.col_3.value||"0");
	var col_4 = parseFloat(document.all.col_4.value||"0");
	var col_5 = parseFloat(document.all.col_5.value||"0");
	var col_6 = parseFloat(document.all.col_6.value||"0");
	var col_7 = parseFloat(document.all.col_7.value||"0");
	var col_9 = parseFloat(document.all.col_9.value||"0");
	var col_34 = parseFloat(document.all.col_34.value||"0");
	var col_37 = parseFloat(document.all.col_37.value||"0");
	var col_39 = parseFloat(document.all.col_39.value||"0");
	var col_40 = parseFloat(document.all.col_40.value||"0");
	var col_41 = parseFloat(document.all.col_41.value||"0");
	var col_42 = parseFloat(document.all.col_42.value||"0");
	var col_44 = parseFloat(document.all.col_44.value||"0");
	var col_45 = parseFloat(document.all.col_45.value||"0");
	var col_47 = parseFloat(document.all.col_47.value||"0");
	var col_49 = parseFloat(document.all.col_49.value||"0");
	var col_50 = parseFloat(document.all.col_50.value||"0");
	
	
	
	
	var col_8 = col_1 - col_2 - col_3 - col_4 - col_5 - col_6 - col_7;
	var col_38 = col_8 + col_9 - col_34 - col_37;
	var col_43 = 0;
	if(document.all.btzzxx_djzclx_3.checked) {//纳税人为合伙企业合伙人
		col_43= (col_38 - col_39) * col_40/100 - col_41 - col_42;
		document.getElementById("hc_43").innerHTML="43=(38-39)*40-41-42";
	} else if(!document.all.btzzxx_djzclx_3.checked) {//纳税人为非合伙企业合伙人
		col_43= col_38 - col_39 - col_41 - col_42;
		document.getElementById("hc_43").innerHTML="43=38-39-41-42";
	}
	var col_46 = col_43 * col_44 - col_45;
	var col_48 = col_46 - col_47;
	var col_51 = col_48 + col_49 - col_50;

	document.all.col_8.value = col_8.toFixed(2);
	document.all.col_38.value = col_38.toFixed(2);
	document.all.col_43.value = col_43.toFixed(2);
	document.all.col_46.value = col_46.toFixed(2);
	document.all.col_48.value = col_48.toFixed(2);
	document.all.col_51.value = col_51.toFixed(2);
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
	 
<form name="grsdsjbxx" action="grsdsNdsbb2014.dc">
<input name="actionType" type="hidden" id="actionType" value="Show">
<table width="97%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
     <td align="left" valign="top" >
    	<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="生产、经营所得个人所得税纳税申报表" />
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
					
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:doSave();"><img name="save" value="保存" alt="保存" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>/images/baocun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/baocun1.jpg" width="79" height="22" id="Baocun"></a>&nbsp;&nbsp;
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Shangyy');return false;"><img name="Shangyiye" value="上一页" alt="上一页" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>/images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>&nbsp;&nbsp;
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Fanhui');return false;"><img name="Fanhui" value="返回" alt="返回" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/fanhui1.jpg" width="79" height="22" id="fanhui"></a>&nbsp;&nbsp;
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Return');return false;"><img  name="Tuichu" value="退出" alt="退出" onMouseOver="MM_swapImage('tuichu','','<%=static_contextpath%>/images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/tuichu1.jpg"  width="79" height="22" id="tuichu"></a> 
				
				</TD>
				</TR>
		    	</table>
			</div>
	   </td>
	</tr>
	
    <tr>
	   <td align=left ><br/>
			
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
&nbsp;本表适用于查账征收“个体工商户的生产、经营所得”和“对企事业单位的承包经营、承租经营所得”个人所得税的个体工商户、承包承租经营者、个人独资企业投资者和合伙企业合伙人的个人所得税年度汇算清缴。纳税人在办理申报时，须同时附报附件2—《个人所得税基础信息表（B表）》。<br/>
&nbsp;合伙企业有两个或两个以上自然人投资者的，应分别填报本表。<br/>
二、	申报期限<br/>
&nbsp;个体工商户、个人独资企业投资者、合伙企业合伙人的生产、经营所得应纳个人所得税的年度纳税申报，应在年度终了后三个月内办理。<br/>
&nbsp;对企事业单位承包经营、承租经营者应纳个人所得税的年度纳税申报，应在年度终了后三十日内办理；纳税人一年内分次取得承包、承租经营所得的，应在年度终了后三个月内办理汇算清缴。<br/>
&nbsp;纳税人不能按规定期限办理纳税申报的，应当按照《中华人民共和国税收征收管理法》（以下简称税收征管法）及其实施细则的规定办理延期申报。<br/>
三、	本表各栏填写如下：<br/>
&nbsp;（一）表头项目<br/>
&nbsp;税款所属期：是指纳税人取得所得的应纳个人所得税款的所属期间，应填写具体的起止年月日。<br/>
&nbsp;（二）表内信息栏<br/>
&nbsp;&nbsp;1.	投资者信息栏：填写个体工商户业主、承包经营者、承租经营者、个人独资企业投资者、合伙企业合伙人的相关信息。<br/>
&nbsp;&nbsp;&nbsp;（1）	姓名：填写纳税人姓名。中国境内无住所个人，其姓名应当用中、外文同时填写。<br/>
&nbsp;&nbsp;&nbsp;（2）	身份证件类型：填写能识别纳税人唯一身份的有效证照名称。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;①	在中国境内有住所的个人，填写身份证、军官证、士兵证等证件名称。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;②	在中国境内无住所的个人，填写护照、港澳居民来往内地通行证、台湾居民来往大陆通行证等证照名称。<br/>
&nbsp;&nbsp;&nbsp;（3）	身份证件号码：填写纳税人身份证件上的号码。<br/>
&nbsp;&nbsp;&nbsp;（4）	国籍（地区）：填写纳税人的国籍或者地区。<br/>
&nbsp;&nbsp;&nbsp;（5）	纳税人识别号：在中国境内无住所的个人填写。有住所的个人不填写。该栏填写税务机关赋予的18位纳税人识别号。税务机关未赋予的，不填写。<br/>
&nbsp;&nbsp;&nbsp;税务机关赋予境内无住所个人的18位纳税人识别号，作为其唯一身份识别码，由纳税人到主管税务机关办理初次涉税事项，或扣缴义务人办理该纳税人初次扣缴申报时，由主管税务机关赋予。<br/>
&nbsp;&nbsp;2.	被投资单位信息栏：<br/>
&nbsp;&nbsp;&nbsp;（1）	名称：填写税务机关核发被投资单位税务登记证上载明的单位全称。<br/>
&nbsp;&nbsp;&nbsp;（2）	纳税人识别号：填写税务机关核发的税务登记证号码。<br/>
&nbsp;&nbsp;&nbsp;（3）	类型：纳税人根据自身情况在对应框内打“√”。<br/>
&nbsp;（三）表内各行的填写：<br/>
&nbsp;&nbsp1.	第1行“收入总额”：填写该投资单位在本期内取得的收入总额。<br/>
&nbsp;&nbsp;2.	第2行“成本”：填写该投资单位在本期内主要经营业务和其他经营业务发生的成本总额。<br/>
&nbsp;&nbsp;3.	第3行“营业费用”：填报该投资单位在销售商品和材料、提供劳务的过程中发生的各种费用。<br/>
&nbsp;&nbsp;4.	第4行“管理费用”：填报该投资单位为组织和管理企业生产经营发生的管理费用。<br/>
&nbsp;&nbsp;5.	第5行“财务费用”：填报该投资单位为筹集生产经营所需资金等发生的筹资费用。<br/>
&nbsp;&nbsp;6.	第6行“营业税金及附加”：填报该投资单位经营活动发生的营业税、消费税、城市维护建设税、资源税、土地增值税和教育费附加等相关税费。<br/>
&nbsp;&nbsp;7.	第8行“利润总额”：根据相关栏次计算。<br/>
&nbsp;&nbsp;第8行＝第1行―第2行―第3行―第4行―第5行―第6行―第7行<br/>
&nbsp;&nbsp;8.	第10行“超过规定标准扣除的项目”，是指被投资单位超过个人所得税法及其实施条例和相关税收法律法规政策规定的扣除标准，扣除的各种成本、费用和损失，应予调增应纳税所得额的部分。<br/>
&nbsp;&nbsp;9.	第24行“不允许扣除的项目”：是指规定不允许扣除，但被投资单位已将其扣除的各项成本、费用和损失，应予调增应纳税所得额的部分。<br/>
&nbsp;&nbsp;10.	第35行“国债利息收入”，是指企业将免于纳税、但已计入收入的因购买国债而取得的利息。<br/>
&nbsp;&nbsp;11.	第37行“以前年度损益调整”：是指以前年度发生的多计或少计的应纳税所得额。<br/>
&nbsp;&nbsp;12.	第38行“经纳税调整后的生产经营所得”：根据相关栏次计算。<br/>
&nbsp;&nbsp;第38行＝第8行＋第9行―第34行―第37行<br/>
&nbsp;&nbsp;13.	第39行“弥补以前年度亏损”：是指企业根据规定，以前年度亏损允许在税前弥补而相应调减的应纳税所得额。<br/>
&nbsp;&nbsp;14.	第40行“分配比例”：纳税人为合伙企业合伙人的，填写本栏。分配比例按照合伙企业分配方案中规定的该合伙人的比例填写；没有，则按人平均分配。<br/>
&nbsp;&nbsp;15.	第41行“允许扣除的其他费用”：是指按照法律法规规定可以税前扣除的其他费用。没有的，则不填。如：《国家税务总局关于律师事务所从业人员有关个人所得税问题的公告》（国家税务总局公告2012年第53号）第三条规定的事项。<br/>
&nbsp;&nbsp;16.	第42行“投资者减除费用”：是指按照税法及有关法律法规规定，在个体工商户业主、个人独资企业投资者和合伙企业合伙人的生产经营所得计征个人所得税时，可在税前扣除的投资者本人的生计减除费用。2011年9月1日起执行42000元/年标准；以后标准按国家政策规定执行。<br/>
&nbsp;&nbsp;17.	第43行“应纳税所得额”：根据不同情况，按相关行次计算填写。<br/>
&nbsp;&nbsp;&nbsp;（1）	纳税人为非合伙企业合伙人的<br/>
&nbsp;&nbsp;&nbsp;第43行＝第38行－第39行－第41行－第42行<br/>
&nbsp;&nbsp;&nbsp;（2）	纳税人为合伙企业合伙人的<br/>
&nbsp;&nbsp;&nbsp;第43行＝（第38行－第39行）×第40行－第41行－第42行<br/>
&nbsp;&nbsp;18.	第44行“税率”及第45行“速算扣除数”：按照税法第三条规定填写。<br/>
&nbsp;&nbsp;19.	第46行“应纳税额”：按相关栏次计算填写。<br/>
&nbsp;&nbsp;第46行＝第43行×第44行－第45行<br/>
&nbsp;&nbsp;20.	第48行“全年应缴税额”：按相关栏次计算填写。<br/>
&nbsp;&nbsp;第48行＝第46行－第47行<br/>
&nbsp;&nbsp;21.	第51行“应补（退）税额”：按相关栏次计算填写。<br/>
&nbsp;&nbsp;第51行＝第48行＋第49行－第50行<br/>

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