<%@ page contentType="text/html; charset=GBK" %>
<%@page import="com.ttsoft.bjtax.shenbao.jmssb.web.CjrjyjmsForm"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="declare" %>
<%@page import="java.util.*"%>


<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);

	//��λ����list
	List dwxzList = (List) request.getAttribute("dwxzList");
	//out.print(dwxzList);
%>

<html>
<head>
<title>���òм��˾�ҵ��ҵӪҵ˰����˰�걨��</title>
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
	//��ʾ��λ����
	var dwxzlx= new Array(<%=dwxzList.size()%>);
	<%
	for(int i=0;i<dwxzList.size();i++){
		String tmpJmlx = (String)dwxzList.get(i);
		out.println("dwxzlx["+i+"] = [\""+tmpJmlx.substring(0,2)+"\",\""+tmpJmlx.substring(3)+"\"];");
	}
	
	%>

	//��ʾdiv1��div2���ͼƬ��ʾ����
	function insertDivPic(){
		//alert("div1PicShow");
		//XSL�ж���div1��div2����ʾ��ͼƬ
		//alert("1.1............");
		document.getElementById("div1PicShow").innerHTML="&nbsp;&nbsp;<input type='image' accesskey='q' tabIndex='-1' onClick='doQuery(); return false;'  alt='ȷ��' src='<%=static_contextpath%>images/queren1.jpg' width='79' height='22' id='chaxun' style='cursor:hand'>&nbsp;&nbsp;<input type='image' accesskey='c' tabIndex='-1' onClick='doReturn(); return false;' alt='����'  src='<%=static_contextpath%>images/fanhui1.jpg' width='79' height='22' id='fanhui' style='cursor: hand'>";
		//alert("1.2............");
		document.getElementById("div2PicShow").innerHTML="<input type='image' tabIndex='-1' onClick='doSaveCjr();return false;' src='<%=static_contextpath%>images/bc-s1.jpg' alt='����' width='79' height='22' id='baocun_cjr' name ='baocun_cjr' style='cursor: hand'>	 &nbsp;&nbsp;&nbsp;&nbsp;	<input type='image' accesskey='u' tabIndex='-1'	onClick='ql();return false; ' src=' <%=static_contextpath%>images/qc-u1.jpg ' alt='���' width='79' height='22' id='qingchu' style='cursor: hand'> &nbsp;&nbsp;&nbsp;&nbsp; <input type='image' accesskey='c' tabIndex='-1' onClick='doReturn(); return false;' alt='����' id='fanhui'	src='<%=static_contextpath%>images/fanhui1.jpg' width='79' height='22' style='cursor: hand'>";
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

	//����xml
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
	    //����select��div�е�ͼƬֵ
	    insertDivPic();
	    insertJmlx();
	    //�����������ʾ�ڶ���div��  
	    loadView();
		return true;
	}
	
	

	  //�����������ʾ�ڶ���div��  
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

	//��ʾselect�����б�ֵ����
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
	//��������
	getBasicXx("xsltVersion","/taxdoc");	
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//��˰����Ϣ
	applendElement("/taxdoc","nsrxx",["swjgzzjgdm","jsjdm","nsrmc","zcdz","jyfw","swdjzh"]);
	//�걨��Ϣ
	applendElement("/taxdoc","sbxx",["lrr","signFlag","sqspbh","success","isError","fsdm","skssrq","dwxz","zgzrs","cjrzgrs","cjrybl","jyfw","ynyyssr","yjyysse","xsyhzzse","byyjzyysxe","syyjzyysxe","bykjzyysxe","bysjjzyysye","bysjyesse","bymjzyysxe","lrrq"]);
	
	//�걨����
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
    		<jsp:param name="name" value="���òм��˾�ҵ��ҵӪҵ˰����˰�걨��" />
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
     <td class="1-td1">���òм��˾�ҵ��ҵӪҵ˰����˰�걨��</td>
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
					<strong><font color="#0000FF">ע �� �� ��</font> </strong>
				</td>
				<td>
					<hr width="100%" size="1">
				</td>
			</tr>
		</table>
		<table width="99%" border="1" align="center" cellpadding="1"
			cellspacing="1" bordercolor="#FFFFFF" class="black9">
			<tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				<td height="47">&nbsp; 1�������ܲ�˰[2007]92���ļ���һ���涨��������Ӫҵ˰����˰�ˣ�Ӧ��ÿ�����˺�10���������ܵ�˰���ر��ͱ��� 
					<br/>
					&nbsp;&nbsp;2��������λ���ʡ�������д������ҵ��ä�˰�Ħ���������ƻ�����������λ��
					<br/>
					&nbsp;&nbsp;3���������òм�ְ��������������д��˰��ʵ�ʰ��õķ��ϲ�˰[2007]92���ļ��涨�Ĳм�ְ��������
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
	       alert("��������벻�����ǿգ�"+jsjdm);
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

	// ��ʾ��Ϣ
	var warnMessage = "";
	// ׷�ӵ���ϸ��ʾ��Ϣ
	var alertStr="";

// ���������Ϣ��ʽ
function checkInput() {
	warnMessage = "";//��վ�����Ϣ
	if(trim(document.forms[0].jsjdm.value) == "") {
		warnMessage += "*��������벻��Ϊ��\n";
	} else if(!/^[A-Za-z0-9]+$/.test(trim(document.forms[0].jsjdm.value))) {
		warnMessage += "*���������ֻ�ܰ�����ĸ������\n";
	}
	if(trim(document.forms[0].skssrq.value) == "") {
		warnMessage += "\n*˰������ʱ�䲻��Ϊ��\n";
	} else if(!/^\d{6}$/.test(trim(document.forms[0].skssrq.value))) {
		warnMessage += "\n*˰������ʱ���ʽΪYYYYMM\n";
	} else if(!/^[0-9]+$/.test(trim(document.forms[0].skssrq.value))){
	    warnMessage += "\n*˰������ʱ���ʽΪYYYYMM\n";
	}
	
	if(trim(document.forms[0].zgzrs.value) == "") {
		warnMessage += "\n*ְ������������Ϊ��\n";
	} else if(!isDigit(trim(document.forms[0].zgzrs.value))) {
		warnMessage += "\n*ְ��������Ϊ����\n"
	}
	
	if(trim(document.forms[0].cjrzgrs.value) == "") {
		warnMessage += "\n*���òм�ְ����������Ϊ��\n";
	} else if(!isDigit(trim(document.forms[0].cjrzgrs.value))) {
		warnMessage += "\n*���òм�ְ������Ϊ����\n"
	}
	
	if(trim(document.forms[0].cjrybl.value) == "") {
		warnMessage += "\n*�м���ְ��ռְ���������ı�������Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].cjrybl, 0, 100, "", 6, 3)) {
		warnMessage += "\n*�м���ְ��ռְ��������" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].ynyyssr.value) == "") {
		warnMessage += "\n*����Ӫҵ˰Ӧ˰���벻��Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].ynyyssr, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*����Ӫҵ˰Ӧ˰����" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].yjyysse.value) == "") {
		warnMessage += "\n*����Ӧ��Ӫҵ˰˰���Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].yjyysse, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*����Ӧ��Ӫҵ˰˰��" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].xsyhzzse.value) == "") {
		warnMessage += "\n*���У������ܱ��Ż����ߵ�˰���Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].xsyhzzse, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*���У������ܱ��Ż����ߵ�˰��" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].byyjzyysxe.value) == "") {
		warnMessage += "\n*����Ӧ����Ӫҵ˰�޶��Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].byyjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*����Ӧ����Ӫҵ˰�޶�" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].syyjzyysxe.value) == "") {
		warnMessage += "\n*����δ����Ӫҵ˰�޶��Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].syyjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*����δ����Ӫҵ˰�޶�" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bykjzyysxe.value) == "") {
		warnMessage += "\n*���¿ɼ���Ӫҵ˰�޶��Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].bykjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*���¿ɼ���Ӫҵ˰�޶�" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bysjjzyysye.value) == "") {
		warnMessage += "\n*����ʵ�ʼ���Ӫҵ˰˰���Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].bysjjzyysye, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*����ʵ�ʼ���Ӫҵ˰˰��" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bysjyesse.value) == "") {
		warnMessage += "\n*���¼���Ӫҵ˰��Ӧ��Ӫҵ˰˰���Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].bysjyesse, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*���¼���Ӫҵ˰��Ӧ��Ӫҵ˰˰��" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].bymjzyysxe.value) == "") {
		warnMessage += "\n*����δ����Ӫҵ˰�޶��Ϊ��\n";
	} else if(!isEqualNum(document.forms[0].bymjzyysxe, 0, 999999999999999, "", 14, 2)) {
		warnMessage += "\n*����δ����Ӫҵ˰�޶�" + alertStr + "\n";
		alertStr = "";
	}
	
	if(trim(document.forms[0].lrrq.value) == "") {
		warnMessage += "\n*¼�����ڲ���Ϊ��";
	} else if(!isDate(document.forms[0].lrrq,"")) {
		warnMessage += "\n*¼�����ڸ�ʽΪYYYYMMDD";
	}
}

// ����Ƿ������ʽҪ��
function checkEquation() {

	if(parseFloat(trim(document.forms[0].byyjzyysxe.value)) != (35000/12*(parseFloat(trim(document.forms[0].cjrzgrs.value)))).toFixed(2)) {
		warnMessage += "\n*����Ӧ����Ӫҵ˰�޶� = 35000/12*���òм�ְ������ \n";
	}
	
	<%--
	if(parseFloat(trim(document.forms[0].syyjzyysxe.value)) != ) {
		warnMessage += "\n*����δ����Ӫҵ˰�޶� = ����δ����Ӫҵ˰�޶�(����)\n";
	}
	--%>
	
	if(parseFloat(trim(document.forms[0].bykjzyysxe.value)) != (parseFloat(trim(document.forms[0].byyjzyysxe.value)) + parseFloat(trim(document.forms[0].syyjzyysxe.value))).toFixed(2)) {
		warnMessage += "\n*���¿ɼ���Ӫҵ˰�޶� = ����Ӧ����Ӫҵ˰�޶� + ����δ����Ӫҵ˰�޶�\n";
	}
	
	if(parseFloat(trim(document.forms[0].xsyhzzse.value)) >= parseFloat(trim(document.forms[0].bykjzyysxe.value))) {
		if(parseFloat(trim(document.forms[0].bysjjzyysye.value)) != parseFloat(trim(document.forms[0].bykjzyysxe.value))) {
			warnMessage += "\n*����ʵ�ʼ���Ӫҵ˰˰�� = ���¿ɼ���Ӫҵ˰�޶�\n";
		}
	} else {
		if(parseFloat(trim(document.forms[0].bysjjzyysye.value)) != parseFloat(trim(document.forms[0].xsyhzzse.value))) {
			warnMessage += "\n*����ʵ�ʼ���Ӫҵ˰˰�� = ���У������ܱ��Ż����ߵ�˰��\n";
		}
	}
	
	if(parseFloat(trim(document.forms[0].bysjyesse.value)) != (parseFloat(trim(document.forms[0].yjyysse.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2)) {
		warnMessage += "\n*���¼���Ӫҵ˰��Ӧ��Ӫҵ˰˰�� = ����Ӧ��Ӫҵ˰˰�� - ����ʵ�ʼ���Ӫҵ˰˰��\n";
	}
	
	if(parseFloat(trim(document.forms[0].bymjzyysxe.value)) != (parseFloat(trim(document.forms[0].bykjzyysxe.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2)) {
		warnMessage += "\n*����δ����Ӫҵ˰�޶� = ���¿ɼ���Ӫҵ˰�޶� - ����ʵ�ʼ���Ӫҵ˰˰��\n";
	}
	
	//alert("-----------------------------------------");
	//alert("35000/12*���òм�ְ������ = " + (35000/12*(parseFloat(trim(document.forms[0].cjrzgrs.value)))).toFixed(2));
	//alert("����Ӧ����Ӫҵ˰�޶� + ����δ����Ӫҵ˰�޶� = " + (parseFloat(trim(document.forms[0].byyjzyysxe.value)) + parseFloat(trim(document.forms[0].syyjzyysxe.value))).toFixed(2));
	//alert("����Ӧ��Ӫҵ˰˰�� - ����ʵ�ʼ���Ӫҵ˰˰�� = " + (parseFloat(trim(document.forms[0].yjyysse.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2));
	//alert("���¿ɼ���Ӫҵ˰�޶� - ����ʵ�ʼ���Ӫҵ˰˰�� = " + (parseFloat(trim(document.forms[0].bykjzyysxe.value)) - parseFloat(trim(document.forms[0].bysjjzyysye.value))).toFixed(2));
	//alert("-----------------------------------------");
}


// ����
function doSaveCjr() {
	// ��������ʽ
	checkInput();
	if(warnMessage == "") {
		// ����ʽУ��
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

//function  : ��������ַ����Ƿ����������
//parameters: DigitString: String,������ַ�����ֵ
//			  totalLength: int, ���ֵĳ������ƣ�������С���㣩
//			  decimalLength: int��С���ĳ�������
//            totalLength-decimalLength �������ֵ�����
//return    : false: �ַ����а�������������ַ�
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
		//��С�����ֳ���Ϊ0ʱ ��С��������Ϊnull
		if(decimalLength=="0"){
			decimalLength=null;
			or_decimalLength=0;
			}
		//��С�����ֳ��Ȳ�Ϊnull ���û��С������С������00
		if(decimalLength!=null){if(DigitString.indexOf(".")<0) DigitString+=".00";}
    if (decimalLength!=null)
    {   //С����Ϊ��
        re = re+"\\.[\\d]{1,"+ decimalLength +"}"
    }

    if (totalLength == null)
    {   //δ�趨�ܳ���
        re= "\\d*"+re;
    }
    else
    {   //�趨�ܳ���
        //��С������Ϊ�յ�����£������ж����ֵĳ���
        var intergerLength = totalLength;
        if (decimalLength!=null)
        {   //��С�����ֲ�Ϊ���жϣ�������λ����С����λ���Ƿ���ȷ
            intergerLength = totalLength-decimalLength;
        }
        //re="[\\d]{0,"+ intergerLength +"}"+re;
		re="[\\d]{1,"+ intergerLength +"}"+re;//�������С���㿪ʼ
    }
    re = new RegExp("^(-){0,1}"+re+"$","g");//���ֻ� modify 2003/11/11 �����жϸ�����

    //���ַ���Ϊ�ա�λ�����ԡ�����ƥ�������ʱ
		//var meetRequest=true;
    if(re.exec(DigitString) == null)
    {
			  alertStr+="��ֵ����Ϊ���� "
				if(totalLength!=null) alertStr+="�ܳ���Ϊ"+totalLength+"λ ";
				if(havedecimalLength) {
					alertStr+="С�����ĳ���Ϊ"+or_decimalLength+"λ ";
				}
        succeed=false;
    }
		//��ָ���ַ�����Χʱ �ַ�������ָ����Χ��
		if(maxValue!=null){
			if(parseFloat(DigitString)>parseFloat(maxValue)){
				alertStr+="���ֲ�����"+maxValue+" ";
				succeed= false;
			}
		}
		if(minValue!=null){
			if(parseFloat(DigitString)<parseFloat(minValue)){
				alertStr+="���ֲ�С��"+minValue+" ";
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
		truthBeTold = window.confirm("�ò�����ɾ�����ݿ��е�����,�Ҳ����Զ��ָ�,��ȷ��");
		break;
	case 'Update':
		truthBeTold = window.confirm("�ò������޸����ݿ��е�����,�Ҳ����Զ��ָ�,��ȷ��");
		break;
  case 'Save':
		truthBeTold = window.confirm("�ò������޸����ݿ��е�����,�Ҳ����Զ��ָ�,��ȷ��");
		break;
	case 'Check':
		truthBeTold = window.confirm("�ò������޸����ݿ��е�����,�Ҳ����Զ��ָ�,��ȷ��");
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
	//���ذ�ťʵ�ַ���
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
		//�˳�
	function tuichu(){
		if(returnStr==null || returnStr==""){
			returnStr="zhsbAction.do";
		}
		//��������ۺ��걨�����걨����ҳ�棬���˳����ۺ��걨ҳ��
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