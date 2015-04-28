<%@ page contentType="text/html; charset=GBK" %>
<%@page import="com.ttsoft.bjtax.shenbao.jmssb.web.CjrjyjmsForm"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="declare" %>
<%@page import="java.util.*"%>


<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);

	//单位性质list
	List dwxzList = (List) request.getAttribute("dwxzList");
	//out.print(dwxzList);
%>

<html>
<head>
<title>安置残疾人就业企业营业税减免税申报表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/swapImages.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
</head>


<script language="JavaScript" >
	//显示单位性质
	var dwxzlx= new Array(<%=dwxzList.size()%>);
	<%
	for(int i=0;i<dwxzList.size();i++){
		String tmpJmlx = (String)dwxzList.get(i);
		out.println("dwxzlx["+i+"] = [\""+tmpJmlx.substring(0,2)+"\",\""+tmpJmlx.substring(3)+"\"];");
	}
	
	%>

	//显示div1和div2层的图片显示方法
	function insertDivPic(){
		//alert("div1PicShow");
		//XSL中定义div1，div2中显示的图片
		//alert("1.1............");
		document.getElementById("div1PicShow").innerHTML="&nbsp;&nbsp;<input type='image' accesskey='q' tabIndex='-1' onClick='doQuery(); return false;'  alt='确认' src='<%=static_contextpath%>images/queren1.jpg' width='79' height='22' id='chaxun' style='cursor:hand'>&nbsp;&nbsp;<input type='image' accesskey='c' tabIndex='-1' onClick='doReturn(); return false;' alt='返回'  src='<%=static_contextpath%>images/fanhui1.jpg' width='79' height='22' id='fanhui' style='cursor: hand'>";
		//alert("1.2............");
		document.getElementById("div2PicShow").innerHTML="<input type='image' tabIndex='-1' onClick='doSaveCjr();return false;' src='<%=static_contextpath%>images/bc-s1.jpg' alt='保存' width='79' height='22' id='baocun_cjr' name ='baocun_cjr' style='cursor: hand'>	 &nbsp;&nbsp;&nbsp;&nbsp;	<input type='image' accesskey='u' tabIndex='-1'	onClick='ql();return false; ' src=' <%=static_contextpath%>images/qc-u1.jpg ' alt='清除' width='79' height='22' id='qingchu' style='cursor: hand'> &nbsp;&nbsp;&nbsp;&nbsp; <input type='image' accesskey='c' tabIndex='-1' onClick='doReturn(); return false;' alt='返回' id='fanhui'	src='<%=static_contextpath%>images/fanhui1.jpg' width='79' height='22' style='cursor: hand'>";
	}

	<%
       String containerName = "";
       com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData)session.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
       if (userdata.getCaflag()){
		containerName = userdata.getCert().getContainerName();
       }
       else{
			containerName = "";
       }
	%>
		g_objSI.Container = "<%=containerName%>";
		var strXml = '<%=request.getAttribute("CA_XML_DATA") == null ? ""
				: request.getAttribute("CA_XML_DATA")%>';
		var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION") == null ? ""
						: request.getAttribute("CA_XML_XSLT_VERSION")%>';
		//var strXSLTVersion ='CJR4001';
		var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION") == null ? ""
						: request.getAttribute("CA_XML_SCHEME_VERSION")%>';
		//var strSCHEMEVersion = 'CJR4001';
		var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN") == null ? ""
				: request.getAttribute("REQUEST_TOKEN")%>';

	//解析xml
	function parseXmlOnLoad()
	{
		var xslPath="/XSLTWEB/model/020040/XSLT/" +strXSLTVersion+".xsl";
		//alert("xslPath="+xslPath);
	    //alert("0..");
		if (strXml != "")
	    {
	        if (! parseXml(strXml,xslPath,"result"))
	            return false;
	    }
	    //加载select和div中的图片值
	    insertDivPic();
	    insertJmlx();
	    //检查标记用以显示第二个div层  
	    loadView();
		return true;
	}
	
	

	  //检查标记用以显示第二个div层  
	  function loadView(){ 
		  var signFlagVal = document.forms[0].signFlagVal.value;
		  //alert("signFlagVal="+signFlagVal+"|");
		  if(!(signFlagVal == null || signFlagVal == "")){
		     hidden();
		     show();
		  }
		  var isError= document.forms[0].isError.value;
		  if(!(isError==null || isError=="")){
			//alert(isError+"test isError");
			alert(isError);
		  }
		  var success= document.forms[0].success.value;
		  if(!(success==null || success=="")){
		     alert(success+"+issuccess");
		  }
		}

		function show(){
			document.getElementById("div2").style.display="";
			//alert(document.getElementById("div").style.display)
		}
		function hidden(){
			document.getElementById("div1").style.display="none";
			//alert(document.getElementById("div").style.display)
		}

	//显示select下拉列表值方法
	function insertJmlx(){
		var obj = document.forms[0].dwxz;		
		for(var i=0;i<dwxzlx.length;i++){
			var yValue=dwxzlx[i][0];
			var yName=dwxzlx[i][1];
			var b=true;
			if(yValue==obj.value){
				b=false;
			}
			if(b){
				var item=new Option(yName,yValue);
				obj.options.add(item);
			}					
		}
	}
	
	
 function getPostXml(objForm){
 	//alert("getPostXml...");
	var retstr;
	//基本数据
	getBasicXx("xsltVersion","/taxdoc");	
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//纳税人信息
	applendElement("/taxdoc","nsrxx",["swjgzzjgdm","jsjdm","nsrmc","zcdz","jyfw","swdjzh"]);
	//申报信息
	applendElement("/taxdoc","sbxx",["lrr","signFlag","sqspbh","success","isError","fsdm","skssrq","dwxz","zgzrs","cjrzgrs","cjrybl","jyfw","ynyyssr","yjyysse","xsyhzzse","byyjzyysxe","syyjzyysxe","bykjzyysxe","bysjjzyysye","bysjyesse","bymjzyysxe","lrrq"]);
	
	//申报数据
	//getSbsj(objForm);	
		
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
 	//alert(retstr);
	return retstr;
}

</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onLoad="parseXmlOnLoad();">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><html:errors/><br></td>
  </tr>
  <tr>
    <td align="center" valign="top">

    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="安置残疾人就业企业营业税减免税申报表" />
		<jsp:param name="help_url" value="help/wssb/sbzl/qyjbcwzb/qyjbcwzb-000.htm"/>
    	</jsp:include>
    </td>
  </tr>
  <tr>
    <td valign="top">
     <br>
<form name="Form1" method="post" action="/shenbao/cjrjyjmsb.dc">
<input name="actionType" type="hidden" id="actionType" value="Show">
<table cellspacing="0" class="table-99">
  <tr>
     <td class="1-td1">安置残疾人就业企业营业税减免税申报表</td>
  </tr>
  <tr>
     <td valign="top" class="1-td2">
		<div id="result"></div>
	<br>
	<br>
     </td>
  </tr>
</table>
<br>
		<br/>
		<table width="99%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<hr width="100%" size="1">
				</td>
				<td width="99" align="center" class="black9">
					<strong><font color="#0000FF">注 意 事 项</font> </strong>
				</td>
				<td>
					<hr width="100%" size="1">
				</td>
			</tr>
		</table>
		<table width="99%" border="1" align="center" cellpadding="1"
			cellspacing="1" bordercolor="#FFFFFF" class="black9">
			<tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				<td height="47">&nbsp; 1．凡享受财税[2007]92号文件第一条规定按月征减营业税的纳税人，应于每月终了后10日内向主管地税机关报送本表； 
					<br/>
					&nbsp;&nbsp;2．本表“单位性质”栏，填写福利企业，盲人按摩机构，工疗机构或其他单位；
					<br/>
					&nbsp;&nbsp;3．本表“安置残疾职工人数栏”，填写纳税人实际安置的符合财税[2007]92号文件规定的残疾职工人数。
				</td>
			</tr>
		</table>
		<p>
			&nbsp;
		</p>

<script language="javascript">
  
	function doQuery(){
		var jsjdm;
		jsjdm = document.forms[0].jsjdm.value;
		if(jsjdm==null || jsjdm==""){
	       alert("计算机代码不允许是空！"+jsjdm);
	       //document.forms[0].jsjdm.focus();
	       return true;
	       }
		doSubmitForm("Query");
	  	//return false;
  	}
  	
  	
  	function ql(){
		document.forms[0].bysjyesse.value = "";
		document.forms[0].bymjzyysxe.value = "";
	    document.forms[0].zgzrs.value = "";
	    document.forms[0].cjrzgrs.value = "";
	    document.forms[0].cjrybl.value = "" ;
	    document.forms[0].ynyyssr.value = "" ;
	    document.forms[0].yjyysse.value = "";
	    document.forms[0].xsyhzzse.value = "";
	    document.forms[0].byyjzyysxe.value = "";
	    document.forms[0].syyjzyysxe.value = "";
	    document.forms[0].bykjzyysxe.value="";
		document.forms[0].bysjjzyysye.value = "";
  }
	function printFunc(){
	  document.forms[0].target="blank";
	  doSubmitForm('Print');
	}

	// 提示信息
	var warnMessage = "";
	// 追加的详细提示信息
	var alertStr="";

// 检查输入信息格式
function checkInput() {
	warnMessage = "";//清空警告信息
	if(trim(document.forms[0].jsjdm.value) == "") {
		warnMessage += "*计算机代码不能为空\n";
	} else if(!/^[A-Za-z0-9]+$/.test(trim(document.forms[0].jsjdm.value))) {
		warnMessage += "*计算机代码只能包含字母和数据\n";
	}
	if(trim(document.forms[0].skssrq.value) == "") {
		warnMessage += "\n*税款所属时间不能为空\n";
	} else if(!/^\d{6}$/.test(trim(document.forms[0].skssrq.value))) {
		warnMessage += "\n*税款所属时间格式为YYYYMM\n";
	} else if(!/^[0-9]+$/.test(trim(document.forms[0].skssrq.value))){
	    warnMessage += "\n*税款所属时间格式为YYYYMM\n";
	}
	
	if(trim(document.forms[0].zgzrs.value) == "") {
		warnMessage += "\n*职工总人数不能为空\n";
	} else if(!isDigit(trim(document.forms[0].zgzrs.value))) {
		warnMessage += "\n*职工总人数为整数\n"
	}
	
	if(trim(document.forms[0].cjrzgrs.value) == "") {
		warnMessage += "\n*安置残疾职工人数不能为空\n";
	} else if(!isDigit(trim(document.forms[0].cjrzgrs.value))) {
		warnMessage += "\n*安置残疾职工人数为整数\n"
	}
	
	if(trim(document.forms[0].cjrybl.value) == "") {
		warnMessage += "\n*残疾人职工占职工总人数的比例不能为空\n";
	} else if(!isEqualNum(document.forms[0].cjrybl, 0, 100, "", 6, 3)) {
		warnMessage += "\n*残疾人职工占职工总人数" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].ynyyssr.value) == "") {
		warnMessage += "\n*本月营业税应税收入不能为空\n";
	} else if(!isEqualNum(document.forms[0].ynyyssr, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*本月营业税应税收入" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].yjyysse.value) == "") {
		warnMessage += "\n*本月应交营业税税额不能为空\n";
	} else if(!isEqualNum(document.forms[0].yjyysse, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*本月应交营业税税额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].xsyhzzse.value) == "") {
		warnMessage += "\n*其中：可享受本优惠政策的税额不能为空\n";
	} else if(!isEqualNum(document.forms[0].xsyhzzse, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*其中：可享受本优惠政策的税额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].byyjzyysxe.value) == "") {
		warnMessage += "\n*本月应减征营业税限额不能为空\n";
	} else if(!isEqualNum(document.forms[0].byyjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*本月应减征营业税限额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].syyjzyysxe.value) == "") {
		warnMessage += "\n*上月未减征营业税限额不能为空\n";
	} else if(!isEqualNum(document.forms[0].syyjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*上月未减征营业税限额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bykjzyysxe.value) == "") {
		warnMessage += "\n*本月可减征营业税限额不能为空\n";
	} else if(!isEqualNum(document.forms[0].bykjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*本月可减征营业税限额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bysjjzyysye.value) == "") {
		warnMessage += "\n*本月实际减征营业税税额不能为空\n";
	} else if(!isEqualNum(document.forms[0].bysjjzyysye, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*本月实际减征营业税税额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bysjyesse.value) == "") {
		warnMessage += "\n*本月减征营业税后应交营业税税额不能为空\n";
	} else if(!isEqualNum(document.forms[0].bysjyesse, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*本月减征营业税后应交营业税税额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bymjzyysxe.value) == "") {
		warnMessage += "\n*本月未减征营业税限额不能为空\n";
	} else if(!isEqualNum(document.forms[0].bymjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*本月未减征营业税限额" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].lrrq.value) == "") {
		warnMessage += "\n*录入日期不能为空";
	} else if(!isDate(document.forms[0].lrrq,"")) {
		warnMessage += "\n*录入日期格式为YYYYMMDD";
	}
}

// 检查是否满足等式要求
function checkEquation() {

	if(parseFloat(trim(document.forms[0].byyjzyysxe.value)) != (35000/12*(parseFloat(trim(document.forms[0].cjrzgrs.value)))).toFixed(2)) {
		warnMessage += "\n*本月应减征营业税限额 = 35000/12*安置残疾职工人数 \n";
	}
	
	<%--
	if(parseFloat(trim(document.forms[0].syyjzyysxe.value)) != ) {
		warnMessage += "\n*上月未减征营业税限额 = 本月未减征营业税限额(上月)\n";
	}
	--%>
	
	if(parseFloat(trim(document.forms[0].bykjzyysxe.value)) != (parseFloat(trim(document.forms[0].byyjzyysxe.value)) + parseFloat(trim(document.forms[0].syyjzyysxe.value))).toFixed(2)) {
		warnMessage += "\n*本月可减征营业税限额 = 本月应减征营业税限额 + 上月未减征营业税限额\n";
	}
	
	if(parseFloat(trim(document.forms[0].xsyhzzse.value)) >= parseFloat(trim(document.forms[0].bykjzyysxe.value))) {
		if(parseFloat(trim(document.forms[0].bysjjzyysye.value)) != parseFloat(trim(document.forms[0].bykjzyysxe.value))) {
			warnMessage += "\n*本月实际减征营业税税额 = 本月可减征营业税限额\n";
		}
	} else {
		if(parseFloat(trim(document.forms[0].bysjjzyysye.value)) != parseFloat(trim(document.forms[0].xsyhzzse.value))) {
			warnMessage += "\n*本月实际减征营业税税额 = 其中：可享受本优惠政策的税额\n";
		}
	}
	
	if(parseFloat(trim(document.forms[0].bysjyesse.value)) != (parseFloat(trim(document.forms[0].yjyysse.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2)) {
		warnMessage += "\n*本月减征营业税后应交营业税税额 = 本月应交营业税税额 - 本月实际减征营业税税额\n";
	}
	
	if(parseFloat(trim(document.forms[0].bymjzyysxe.value)) != (parseFloat(trim(document.forms[0].bykjzyysxe.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2)) {
		warnMessage += "\n*本月未减征营业税限额 = 本月可减征营业税限额 - 本月实际减征营业税税额\n";
	}
	
	//alert("-----------------------------------------");
	//alert("35000/12*安置残疾职工人数 = " + (35000/12*(parseFloat(trim(document.forms[0].cjrzgrs.value)))).toFixed(2));
	//alert("本月应减征营业税限额 + 上月未减征营业税限额 = " + (parseFloat(trim(document.forms[0].byyjzyysxe.value)) + parseFloat(trim(document.forms[0].syyjzyysxe.value))).toFixed(2));
	//alert("本月应交营业税税额 - 本月实际减征营业税税额 = " + (parseFloat(trim(document.forms[0].yjyysse.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2));
	//alert("本月可减征营业税限额 - 本月实际减征营业税税额 = " + (parseFloat(trim(document.forms[0].bykjzyysxe.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2));
	//alert("-----------------------------------------");
}


// 保存
function doSaveCjr() {
	// 检查输入格式
	checkInput();
	if(warnMessage == "") {
		// 检查等式校验
		checkEquation();
		if(warnMessage == "") {
			//doSubmitForm("Save");
		} else {
			alert(warnMessage);
			warnMessage = "";
			return false;
		}
	} else {
		alert(warnMessage);
		warnMessage = "";
			return false;
	}
	var old  = document.forms[0].ywczlx.value;
		document.forms[0].actionType.value="Save";
		if (g_objSI.Container != ''){
			if (!doSubmitXML(document.forms[0],"Save",true,"",true)){  
				document.forms[0].ywczlx.value = old;
				return false;
			}
		}
		else{  
			if(!doSubmitXML(document.forms[0],"Save",false,"",true)){  
				document.forms[0].ywczlx.value = old;
				return false;
			}
		}
		return false;
}

//function  : 检查输入字符串是否由数字组成
//parameters: DigitString: String,待检查字符串的值
//			  totalLength: int, 数字的长度限制（不包括小数点）
//			  decimalLength: int，小数的长度限制
//            totalLength-decimalLength 整数部分的限制
//return    : false: 字符串中包含除数字外的字符
//			  true : else
function isEqualNum(obj,minValue,maxValue,empty,totalLength, decimalLength)
{
	  if(window.event.keyCode!=13 && window.event.keyCode!=0) return true;
		var DigitString=trim(obj.value);
		var succeed=true;
		var havedecimalLength=true;
		var or_decimalLength=decimalLength;
		if(DigitString.length==0){
		  if(empty!=null){
				succeed=false;
		  }else{
		    return true;
		  }
		}
		if(decimalLength==null){
			decimalLength=2;
			or_decimalLength=2;
		}
		var re = "";
		//当小数部分长度为0时 将小数部分设为null
		if(decimalLength=="0"){
			decimalLength=null;
			or_decimalLength=0;
			}
		//当小数部分长度不为null 如果没有小数点在小数点后加00
		if(decimalLength!=null){if(DigitString.indexOf(".")<0) DigitString+=".00";}
    if (decimalLength!=null)
    {   //小数不为空
        re = re+"\\.[\\d]{1,"+ decimalLength +"}"
    }

    if (totalLength == null)
    {   //未设定总长度
        re= "\\d*"+re;
    }
    else
    {   //设定总长度
        //当小数部分为空的情况下，就是判断数字的长度
        var intergerLength = totalLength;
        if (decimalLength!=null)
        {   //当小数部分不为空判断，整数的位数和小数的位数是否正确
            intergerLength = totalLength-decimalLength;
        }
        //re="[\\d]{0,"+ intergerLength +"}"+re;
		re="[\\d]{1,"+ intergerLength +"}"+re;//不允许从小数点开始
    }
    re = new RegExp("^(-){0,1}"+re+"$","g");//郭现华 modify 2003/11/11 可以判断负数。

    //当字符串为空、位数不对、不能匹配成数字时
		//var meetRequest=true;
    if(re.exec(DigitString) == null)
    {
			  alertStr+="的值必须为数字 "
				if(totalLength!=null) alertStr+="总长度为"+totalLength+"位 ";
				if(havedecimalLength) {
					alertStr+="小数点后的长度为"+or_decimalLength+"位 ";
				}
        succeed=false;
    }
		//当指定字符串范围时 字符串不在指定范围内
		if(maxValue!=null){
			if(parseFloat(DigitString)>parseFloat(maxValue)){
				alertStr+="数字不大于"+maxValue+" ";
				succeed= false;
			}
		}
		if(minValue!=null){
			if(parseFloat(DigitString)<parseFloat(minValue)){
				alertStr+="数字不小于"+minValue+" ";
				succeed= false;
			}
		}
    return succeed;
}
function doSubmitForm(actionType){

	var truthBeTold = true;// = window.confirm("");
	switch (actionType)
	{
	case 'Delete':
		truthBeTold = window.confirm("该操作将删除数据库中的数据,且不能自动恢复,请确认");
		break;
	case 'Update':
		truthBeTold = window.confirm("该操作将修改数据库中的数据,且不能自动恢复,请确认");
		break;
  case 'Save':
		truthBeTold = window.confirm("该操作将修改数据库中的数据,且不能自动恢复,请确认");
		break;
	case 'Check':
		truthBeTold = window.confirm("该操作将修改数据库中的数据,且不能自动恢复,请确认");
		break;
  case 'Query':
		truthBeTold = true;
		break;
	default:
		break;
	}
  if(!truthBeTold){
		return false;
	}
	
	document.all.actionType.value=actionType;
		document.forms[0].submit();
	}
	//返回按钮实现方法
	function doReturn(){
		if(confirm(confirmReturn)){
	       	document.forms[0].actionType.value = "Return";
			//alert(document.forms[0].actionType.value);
			if (!doSubmitXML(document.forms[0],"Return",false,"return",true)){
			  return false;
			}
	       	return true;
	    }
		else{
	       		return false;
	    }
	}

	var returnStr="";
		//退出
	function tuichu(){
		if(returnStr==null || returnStr==""){
			returnStr="zhsbAction.do";
		}
		//如果是由综合申报进入申报资料页面，则退出到综合申报页面
		if(document.all.iszhsb && document.all.iszhsb.value=='1')
			returnStr="zhsbAction.do?actionType=Show";
		window.location=returnStr;
	}
	
</script>

    </td>
  </tr>
  <tr>
    <td valign="bottom" align="center">
<%@ include file="../include/bottom.jsp" %>
    </td>
  </tr>
</table>
</form>
</body>
</html>