<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ page import="com.ttsoft.bjtax.shenbao.constant.CodeConstant"%>
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);

	String _REQ_KEY_TEST_ORG_USE = (String) request
			.getAttribute("_REQ_KEY_TEST_ORG_USE");

	String _REQ_KEY_TEST_ORG_TEST = (String) request
			.getAttribute("_REQ_KEY_TEST_ORG_TEST");

	String _REQ_KEY_TEST_ORG_MSG = (String) request
			.getAttribute("_REQ_KEY_TEST_ORG_MSG");
%>
<html>
<head>
<title>中华人民共和国企业清算所得税备案表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript"
	src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/date.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js1/WdatePicker.js"></script>
<script language="JavaScript" src="js/calculate.js"></script>
<script language="JavaScript">
	<%String containerName = "";
			com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData) session
					.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
			if (userdata.getCaflag()) {
				containerName = userdata.getCert().getContainerName();
			} else {
				containerName = "";
			}%>
	
	g_objSI.Container = "<%=containerName%>";
	var strXml = '<%=request.getAttribute("CA_XML_DATA") == null
					? ""
					: request.getAttribute("CA_XML_DATA")%>';
	//var strXml = '<taxdoc><xsltVersion><![CDATA[20061102]]></xsltVersion><schemaVersion><![CDATA[20061102]]></schemaVersion><ywlx><![CDATA[010027]]></ywlx><ywczlx><![CDATA[0]]></ywczlx><nsrjbxx><jsjdm><![CDATA[06000100]]></jsjdm><nsrsbh><![CDATA[11010880207313X000]]></nsrsbh><nsrmc><![CDATA[北京惠新志达科贸有限公司]]></nsrmc><sbnd><![CDATA[2006]]></sbnd><sknd><![CDATA[2005]]></sknd><ssjjlxdm><![CDATA[173]]></ssjjlxdm><ssjjlxmc><![CDATA[私营有限责任公司]]></ssjjlxmc><lxdh><![CDATA[88511275]]></lxdh><jydz><![CDATA[海淀马神庙1号一区核工业第二研究设计院情报中心1112#]]></jydz><sshydm><![CDATA[7600]]></sshydm><sshymc><![CDATA[专业技术服务业]]></sshymc><zsfsdm><![CDATA[03]]></zsfsdm><zsfsmc><![CDATA[查帐征收]]></zsfsmc><cwkjzddm><![CDATA[12]]></cwkjzddm><cwkjzdmc><![CDATA[12]]></cwkjzdmc><cwkjzddm_old><![CDATA[12]]></cwkjzddm_old><gzglxsdm><![CDATA[01]]></gzglxsdm><gzglxsmc><![CDATA[]]></gzglxsmc><gzglxsdm_old><![CDATA[01]]></gzglxsdm_old><jmlxdm><![CDATA[]]></jmlxdm><jmlxmc><![CDATA[]]></jmlxmc><jmlxdm_old><![CDATA[]]></jmlxdm_old><swjgzzjgdm><![CDATA[0601]]></swjgzzjgdm><swjgzzjgmc><![CDATA[海淀区地方税务局知春里所]]></swjgzzjgmc><cjr><![CDATA[HDFJCSYH]]></cjr><cjrq><![CDATA[2006-12-10]]></cjrq><lrr><![CDATA[06000100]]></lrr><lrrq><![CDATA[2006-12-10]]></lrrq><sbrq><![CDATA[]]></sbrq><xtjb><![CDATA[]]></xtjb><bbmsf><![CDATA[1,2,5,8,9,10,11,12,13,14,15,16,17,19,20]]></bbmsf><skssksrq><![CDATA[2005-01-01 ]]></skssksrq><skssjsrq><![CDATA[2005-12-31 ]]></skssjsrq><version><![CDATA[20060101]]></version><sqspbh><![CDATA[]]></sqspbh></nsrjbxx></taxdoc>';
	var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION") == null
					? ""
					: request.getAttribute("CA_XML_XSLT_VERSION")%>';
	var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION") == null
					? ""
					: request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
	var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN") == null
					? ""
					: request.getAttribute("REQUEST_TOKEN")%>';
	var xmlDoc;
	
	//解析xml
	function parseXmlOnLoad(){
		
		var js_REQ_KEY_TEST_ORG_USE = '<%=_REQ_KEY_TEST_ORG_USE%>';
		
		var js_REQ_KEY_TEST_ORG_TEST = '<%=_REQ_KEY_TEST_ORG_TEST%>';
		
		if(js_REQ_KEY_TEST_ORG_USE == '1'){
			
			if(js_REQ_KEY_TEST_ORG_TEST == '1'){
				alert('<%=_REQ_KEY_TEST_ORG_MSG%>');
			}else{
				alert('<%=_REQ_KEY_TEST_ORG_MSG%>');
				return false;
			}
			
		}
		
		var urlXSL="/XSLTWEB/model/010036/XSLT/" +strXSLTVersion+".xsl";
		//var urlXSL="shenbao/qysdsnew/nsrjbxxdjb.xsl";
	    if (strXml != ""){
	        if (! parseXml(strXml,urlXSL,"result"))
	            return false;
			xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
			xmlDoc.async = false;
			xmlDoc.loadXML(strXml);
	    }
	    
	    initNsrjbxx();
	
		return true;
	}

	function initNsrjbxx() {
	
		//企业章程规定的经营期限届满
		var jyqxjm = document.getElementById("jyqxjm").value;
		if (jyqxjm != null && jyqxjm=="Y") { 
			document.getElementById("jyqxjm_Y").checked = true;
		} else { 
			document.getElementById("jyqxjm_N").checked = true;
		}	
		//企业股东会、股东大会或类似机构决议解散
		var gdjyjs = document.getElementById("gdjyjs").value;
		if (gdjyjs != null && gdjyjs=="Y") { 
			document.getElementById("gdjyjs_Y").checked = true;
		} else { 
			document.getElementById("gdjyjs_N").checked = true;
		}
		//企业依法被吊销营业执照、责令关闭或者被撤销
		var yfdxgb = document.getElementById("yfdxgb").value;
		if (yfdxgb != null && yfdxgb=="Y") { 
			document.getElementById("yfdxgb_Y").checked = true;
		} else { 
			document.getElementById("yfdxgb_N").checked = true;
		}
		//企业被人民法院依法予以解散或宣告破产
		var yfxgpc = document.getElementById("yfxgpc").value;
		if (yfxgpc != null && yfxgpc=="Y") { 
			document.getElementById("yfxgpc_Y").checked = true;
		} else { 
			document.getElementById("yfxgpc_N").checked = true;
		}
		//有关法律、行政法规规定清算
		var yfgdqs = document.getElementById("yfgdqs").value;
		if (yfgdqs != null && yfgdqs=="Y") { 
			document.getElementById("yfgdqs_Y").checked = true;
		} else { 
			document.getElementById("yfgdqs_N").checked = true;
		}
		//企业因其他原因解散或进行清算
		var qtyy = document.getElementById("qtyy").value;
		if (qtyy != null && qtyy=="Y") { 
			document.getElementById("qtyy_Y").checked = true;
		} else { 
			document.getElementById("qtyy_N").checked = true;
		}
		
		var bashztbs  = document.forms[0].baShztbs.value;
		if(bashztbs=="2"){
			document.getElementById("qsllry").disabled=true;
			document.getElementById("lxdh").disabled=true;
			document.getElementById("jyqxjm_Y").disabled=true;
			document.getElementById("jyqxjm_N").disabled=true;
			document.getElementById("gdjyjs_Y").disabled=true;
			document.getElementById("gdjyjs_N").disabled=true;
			document.getElementById("yfdxgb_Y").disabled=true;
			document.getElementById("yfdxgb_N").disabled=true;
			document.getElementById("yfxgpc_Y").disabled=true;
			document.getElementById("yfxgpc_N").disabled=true;
			document.getElementById("yfgdqs_Y").disabled=true;
			document.getElementById("yfgdqs_N").disabled=true;
			document.getElementById("qtyy_N").disabled=true;
			document.getElementById("qtyy_Y").disabled=true;
		}
	}

// 将字符串格式化为货币格式
// par为0，自动填充0.00
// par为1，不自动填充
function formatCurrency10(obj,par)
{
	var tmpValue = trim(obj.value);
	var pointIndex = tmpValue.indexOf(".");
	if(pointIndex < 0)
	{
		if(tmpValue == null || tmpValue == "" || tmpValue == ".")
		{
			switch (par)
			{
			case 0:
				tmpValue = "0.00";
				break;
			case 1:
				tmpValue = "";
				break;
			default:
				break;
			}
		}
		else
		{
			tmpValue += ".00";
		}
	}
	else if(pointIndex == 0)
	{
		if(tmpValue.length > 1)
		{
			if(tmpValue.substring(1).length > 2)
			{
				return false;
			}
			else if(tmpValue.substring(1).length == 1)
			{
				tmpValue = "0" + tmpValue + "0";
			}
			else
			{
				tmpValue = "0" + tmpValue;
			}
		}
		else
		{
			switch (par)
			{
			case 0:
				tmpValue = "0.00";
				break;
			case 1:
				tmpValue = "";
				break;
			default:
				break;
			}
		}
	}
	else
	{
		afterpoint = (tmpValue.length-1) - pointIndex;
		if(afterpoint == 0)
		{
			tmpValue += "00";
		}
		else if(afterpoint > 2)
		{
			return false;
		}
		else if(afterpoint == 1)
		{
			tmpValue += "0";
		}
	}
	if(tmpValue > 9999999999999.99)
	{
		return false;
	}
	obj.value = tmpValue;
	return true;
}


	function getPostXml(objForm){	
		var retstr;
		
		saveNsrjbxx();
		
		//基本数据
		getBasicXx("xsltVersion","/taxdoc");
		getBasicXx("schemaVersion","/taxdoc");
		getBasicXx("ywlx","/taxdoc");
		getBasicXx("ywczlx","/taxdoc");

		//纳税人基本信息
		applendElement("/taxdoc","nsrjbxx",["jsjdm","nsrsbh","nsrmc","ssjjlxdm","ssjjlxmc","qsllry","lxdh","jydz",
						"sshydm","sshymc","swjgzzjgdm","swjgzzjgmc","cjr","cjrq","lrr","lrrq",
						"tbrq","xtjb","bbmsf","qsbaksrq","qssbksrq","qssbjsrq","version","sqspbh","jyqxjm",
						"gdjyjs","yfdxgb","yfxgpc","yfgdqs","qtyy","baShztbs","baShtgrq","baShztMessage"]);
		
		//去掉末尾自动添加的回车
		retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
		retstr = retstr.substr(0,retstr.length-2);
		
		return retstr;
	}
	
	function saveNsrjbxx(){
		//企业章程规定的经营期限届满
		// var jyqxjm = document.getElementById("jyqxjm").value;
		if(document.getElementById("jyqxjm_Y").checked){
			document.getElementById("jyqxjm").value = "Y";
		}else{
			document.getElementById("jyqxjm").value = "N";
		}
		//企业股东会、股东大会或类似机构决议解散
		// var gdjyjs = document.getElementById("gdjyjs").value;
		if(document.getElementById("gdjyjs_Y").checked){
			document.getElementById("gdjyjs").value = "Y";
		}else{
			document.getElementById("gdjyjs").value = "N";
		}
		//企业依法被吊销营业执照、责令关闭或者被撤销
		// var yfdxgb = document.getElementById("yfdxgb").value;
		if(document.getElementById("yfdxgb_Y").checked){
			document.getElementById("yfdxgb").value ="Y";
		}else{
			document.getElementById("yfdxgb").value ="N";
		}
		//企业被人民法院依法予以解散或宣告破产
		// var yfxgpc = document.getElementById("yfxgpc").value;
		if(document.getElementById("yfxgpc_Y").checked){
			document.getElementById("yfxgpc").value ="Y";
		}else{
			document.getElementById("yfxgpc").value ="N";
		}
		//有关法律、行政法规规定清算
		// var yfgdqs = document.getElementById("yfgdqs").value;
		if(document.getElementById("yfgdqs_Y").checked){
			document.getElementById("yfgdqs").value = "Y";
		}else{
			document.getElementById("yfgdqs").value = "N";
		}
		//企业因其他原因解散或进行清算
		// var qtyy = document.getElementById("qtyy").value;
		if(document.getElementById("qtyy_Y").checked){
			document.getElementById("qtyy").value = "Y";
		}else{
			document.getElementById("qtyy").value = "N";
		}
		
		//清算备案开始日期
		//document.getElementById("qsbaksrq").value = 
	}
	function changeLocalYwczlx(ywczlx){
		var rootNode = xmlDoc.documentElement;
	    var objCDATA =xmlDoc.createCDATASection(ywczlx);
			rootNode.selectSingleNode("//ywczlx").text = "";
			rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
	}
	
	    //响应计算机代码的回车查询
	function jsjdmQuery(){
		if(event.keyCode==13) 
			event.keyCode = 9;
	}
	
	function doSubmit(actionType){
		var form=document.forms[0];
		if(actionType=='Save'){
			doSave();
		}else if(actionType=='Delete'){
			doDelete();
		}else{
			doReturn();
		}
	}
	function doDelete()
	{	
		var bashztbs  = document.forms[0].baShztbs.value;
		if(bashztbs=="2"){
			alert("您企业的企业清算所得税备案已经审核通过不能进行删除动作！");
			return;
		}
		if(bashztbs=="1"){
			alert("您企业的企业清算所得税备案已经提交不能进行删除动作！");
			return;
		}
		var old  = document.forms[0].ywczlx.value;
	    if((document.forms[0].ywczlx.value == 0) && confirm(confirmDelete))
	    {
				document.forms[0].actionType.value="Delete";
	  			changeLocalYwczlx("3");
					if (g_objSI.Container != '')
					{
							if (!doSubmitXML(document.forms[0],"Delete",true,xmlDoc.xml,true))
							{
								changeLocalYwczlx(old);
								return false;
							}
					}else
					{	
							if (!doSubmitXML(document.forms[0],"Delete",false,xmlDoc.xml,true))
							{
								changeLocalYwczlx(old);
								return false;
							}	
					}
					return true;
	   }else
	   {
	   			return false;
	   }
	}
	//mass 修改 2013-12-6
	function doSave()
	{
		
		var old  = document.forms[0].ywczlx.value;
		
		var bashztbs  = document.forms[0].baShztbs.value;
		if(bashztbs=="2"){
			alert("您企业的企业清算所得税备案已经审核通过不能进行修改！");
			return;
		}
		if(bashztbs=="1"){
			alert("您企业的企业清算所得税备案已经提交不能进行重复提交操作！");
			return;
		}
		<%/*--modified by huohb,2014-06-18--*/%>
		var qsllry = document.getElementById("qsllry").value;
		var lxdh = document.getElementById("lxdh").value;
		if(qsllry==""){
			alert("管理人或清算组联络人员不能为空");
			return false;
		}
		if(lxdh==""){
			alert("联系电话不能为空");
			return false;
		}
		if(doCheck()){
			saveNsrjbxx();
			SaveExec(old);
		}
		
	    return false; 
	}
	function formatTime(str)
	{
	
	  var   r   =   str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);     
	  if(r==null) return   false;     
	  var  d=  new  Date(r[1],   r[3]-1,   r[4]);     
	  return  (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);   
	
	}
	//业务校验
	function doCheck(){
//		var qsbaksrq = document.all("qsbaksrq").value;
//		if(qsbaksrq == "" ){
//			alert("清算开始日期不能为空!");
//			return false;
//		}
//		if (!formatTime(trim(qsbaksrq)))
//		{
//    		alert("清算开始日期格式错误！");
//    		//document.forms[0].qsbaksrq.focus();
//    		return false;
// 		}
		if(document.all.sfwxjxba.value=="0"){
			alert("该企业不需要做企业清算所得税备案,不能进行保存（如果已保存过数据，请进行删除操作）！");
			return false;
		}
 		if(!doCheckRadio()){
 			alert("企业清算类型必须有一项选择为是！");
 			return false;
 		}
		return true;
	}
	//校验是否选中一项
	function doCheckRadio(){
		
		if(document.getElementById("jyqxjm_Y").checked){
			return true;
		}
		//企业股东会、股东大会或类似机构决议解散
		if(document.getElementById("gdjyjs_Y").checked){
			return true;
		}
		//企业依法被吊销营业执照、责令关闭或者被撤销
		
		if(document.getElementById("yfdxgb_Y").checked){
			return true;
		}
		//企业被人民法院依法予以解散或宣告破产
		
		if(document.getElementById("yfxgpc_Y").checked){
			return true;
		}
		//有关法律、行政法规规定清算
		
		if(document.getElementById("yfgdqs_Y").checked){
			return true;
		}
		//企业因其他原因解散或进行清算
		
		if(document.getElementById("qtyy_Y").checked){
			return true;
		}
		return false;
	}
	function doReturn(){
	    if(confirm(confirmReturn)){
			document.forms[0].actionType.value="Return";
				document.forms[0].submit();
					return true;
	    }else{
	    	return false;
	    }
	}
	
	
	function SaveExec(old)
	{
		if (confirm ("请如实填写清算基本信息表(提交后将不能进行修改)")) 
		{
		    if (document.forms[0].ywczlx.value == 0)
		    {
		        document.forms[0].ywczlx.value = 1;
		    }
		    else if (document.forms[0].ywczlx.value == 1)
		    {
		        document.forms[0].ywczlx.value = 2;
		    }
		    else
		    {
		        alert("未知的操作类型：" + document.forms[0].ywczlx.value);
		        return false;
		    }
	
			document.forms[0].actionType.value="Save";
			if (g_objSI.Container != '')
			{
					if (!doSubmitXML(document.forms[0],"Save",true,"",true))
						{
								document.forms[0].ywczlx.value = old;
								return false;
						}	
			}else
			{
					if (!doSubmitXML(document.forms[0],"Save",false,"",true))
						{
								document.forms[0].ywczlx.value = old;
								return false;
						}	
			}
		
		}
		return true;
	}
	
	</script>

<link href="<%=static_contextpath%>/css/text.css" rel="stylesheet"
	type="text/css">
<link href="<%=static_contextpath%>/css/top-box.css" rel="stylesheet"
	type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">

<style>
input {
	font-size: 9pt;
	text-align: right;
}

.text-gray {
	color: #3E6071;
	background-color: #EEEEEE;
}
</style>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="return parseXmlOnLoad();">

	<form name="form1" method="post" action="qyqssdsba2014.dc">
		<input name="actionType" type="hidden" id="actionType" value="Show">

		<table width="96%" align="center" cellspacing="0" class="table-99">
			<tr>
				<td align="center" valign="top" colspan="7"><jsp:include
						page="../../include/SbHeader.jsp" flush="true">
						<jsp:param name="name" value="中华人民共和国企业清算所得税备案表" />
						<jsp:param name="help_url"
							value="help/wssb/sbzl/lygfzwhztz/qyqssds/qyqssds-000.htm" />
					</jsp:include></td>
			</tr>
			<tr>
				<td colspan="7"><br>
					<div id="result" align="center"></div></TD>
			</TR>
			<TR class="black9">
				<TD>
					<div align="center">

						<%
							boolean showSave = false;

							if (_REQ_KEY_TEST_ORG_USE == null
									|| !"1".equals(_REQ_KEY_TEST_ORG_USE)) {
								showSave = true;
							}

							if (_REQ_KEY_TEST_ORG_USE != null
									&& "1".equals(_REQ_KEY_TEST_ORG_USE)
									&& _REQ_KEY_TEST_ORG_TEST != null
									&& "1".equals(_REQ_KEY_TEST_ORG_TEST)) {
								showSave = true;
							}

							if (showSave) {
						%>

						<a style="cursor:hand" tabIndex="-1"
							onClick="javascript:return doSubmit('Save');return false;"> <img
							name="spage" value="保存" alt="保存"
							onMouseOver="MM_swapImage('tijiao','','<%=static_contextpath%>/images/tijiao2.jpg',1)"
							onMouseOut="MM_swapImgRestore()"
							src="<%=static_contextpath%>/images/tijiao1.jpg" width="79"
							height="22" id="tijiao"> </a>

						<%
							}
						%>

						<a style="cursor:hand" tabIndex="-1"
							onClick="javascript:return doSubmit('Delete');return false;">
							<img name="scpage" value="删除" alt="删除"
							onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>/images/shanchu2.jpg',1)"
							onMouseOut="MM_swapImgRestore()"
							src="<%=static_contextpath%>/images/shanchu1.jpg" width="79"
							height="22" id="shanchu"> </a>
							
						<a style="cursor:hand" tabIndex="-1"
							onClick="javascript:return doSubmit('Return');return false;">
							<img name="bpage" value="返回" alt="返回"
							onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)"
							onMouseOut="MM_swapImgRestore()"
							src="<%=static_contextpath%>/images/fanhui1.jpg" width="79"
							height="22" id="fanhui"> </a>
							
							
					</div></TD>
			</TR>
		</table>
		<br>
		<jsp:include page="../../include/bottom.jsp" flush="true" />

	</form>

</body>
</html>
