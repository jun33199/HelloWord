<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="com.ttsoft.bjtax.shenbao.taglib.SbzlTag"%>
<%@page import="com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.Grsds2014Constant"%>

<html>
<head>
<title>��������Ӫ���ø�������˰��˰�걨��</title>
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

var sfzjlxdms;		//���֤�����ʹ����
var gjdqs;			//�������������

var endSave=false;

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

function doSubmit(actionType){
	var form=document.forms[0];
	if(actionType=='Return'){				//�˳�
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

//����xml
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
	if(document.all.btzzxx_djzclx_3.checked) {//��˰��Ϊ�ϻ���ҵ�ϻ���
		document.getElementById("hc_43").innerHTML="43=(38-39)*40-41-42";
	} else if(!document.all.btzzxx_djzclx_3.checked) {//��˰��Ϊ�Ǻϻ���ҵ�ϻ���
		document.getElementById("hc_43").innerHTML="43=38-39-41-42";
	}
	
    return true;
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
	applendElement("/taxdoc","inf_qy",["btzzxx_jsjdm","btzzxx_name","btzzxx_nsrsbh","btzzxx_djzclx","btzzxx_npjzgrs",
	                                   "btzzxx_gzze","btzzxx_tzzrs","col_1","col_2","col_3","col_4","col_5","col_6",
	                                   "col_7","col_8","col_9","col_10","col_11","col_12","col_13","col_14","col_15",
	                                   "col_16","col_17","col_18","col_19","col_20","col_21","col_22","col_23","col_24",
	                                   "col_25","col_26","col_27","col_28","col_29","col_30","col_31","col_32","col_33",
	                                   "col_34","col_35","col_36","col_37","col_38","col_39"]);
	
	//Ͷ������Ϣ
	applendElement("/taxdoc","inf_gr",["skssqq","skssqz","tzzxx_gj","tzzxx_name","tzzxx_nsrsbh","tzzxx_sfzjhm","tzzxx_sfzjlx",
	                                   "col_40","col_41","col_42","col_43","col_44","col_45","col_46","col_47","col_48","col_49",
	                                   "col_50","col_51"]);
	
	
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

	function setInput()
	{
		sfzjlxdms= xmlDoc.selectNodes("/taxdoc/sfzjlxList/sfzjlx");
		gjdqs = xmlDoc.selectNodes("/taxdoc/gjList/gj");
		
		var btzzxx_djzclx = document.getElementById("btzzxx_djzclx");				//��˰������
		var btzzxx_djzclx_cb = document.getElementsByName("btzzxx_djzclx_cb");
		splitCheckBox(btzzxx_djzclx,btzzxx_djzclx_cb);
		
		var sfzjlx = xmlDoc.selectSingleNode("/taxdoc/inf_gr/tzzxx_sfzjlx").text;     //���֤������
		var sfzjlxSelect = document.getElementById("tzzxx_sfzjlx");       			 //���֤������
		addSfzjlxdmsSelect(sfzjlxSelect);
		sfzjlxSelect.value = sfzjlx;
		
		var gjdq = xmlDoc.selectSingleNode("/taxdoc/inf_gr/tzzxx_gj").text;    //��������
		var gjdqSelect = document.getElementById("tzzxx_gj");
		addGjdqSelect(gjdqSelect);
		gjdqSelect.value = gjdq;
	}
	
	//��ajax��ʽpost
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
		var btzzxx_djzclx = document.getElementById("btzzxx_djzclx");				//��Ͷ�ʵ�λ�Ǽ�ע������
		var btzzxx_djzclx_cb = document.getElementsByName("btzzxx_djzclx_cb");
		joinCheckBox(btzzxx_djzclx,btzzxx_djzclx_cb);
	}
	
	function doSave()
	{
		beforeSubmit();
		var result = ajaxPostXML();
		if(result){
			endSave=true;
			alert("����ɹ�");
		}else{
			alert("����ʧ������ϵϵͳ����Ա");
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
	//isNaN����������֤�����֣����Կ��ַ�����Ч����Ц�� changed 20150325
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
	if(document.all.btzzxx_djzclx_3.checked) {//��˰��Ϊ�ϻ���ҵ�ϻ���
		col_43= (col_38 - col_39) * col_40/100 - col_41 - col_42;
		document.getElementById("hc_43").innerHTML="43=(38-39)*40-41-42";
	} else if(!document.all.btzzxx_djzclx_3.checked) {//��˰��Ϊ�Ǻϻ���ҵ�ϻ���
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
    		<jsp:param name="name" value="��������Ӫ���ø�������˰��˰�걨��" />
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
					
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:doSave();"><img name="save" value="����" alt="����" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>/images/baocun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/baocun1.jpg" width="79" height="22" id="Baocun"></a>&nbsp;&nbsp;
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Shangyy');return false;"><img name="Shangyiye" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>/images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>&nbsp;&nbsp;
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Fanhui');return false;"><img name="Fanhui" value="����" alt="����" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/fanhui1.jpg" width="79" height="22" id="fanhui"></a>&nbsp;&nbsp;
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Return');return false;"><img  name="Tuichu" value="�˳�" alt="�˳�" onMouseOver="MM_swapImage('tuichu','','<%=static_contextpath%>/images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/tuichu1.jpg"  width="79" height="22" id="tuichu"></a> 
				
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
				    <td width="20%" align="center" class="black9">	<a style="cursor:hand;text-decoration:underline;"  tabIndex="-1" onClick="javascript:clickTbsm();"><strong><font color="#0000FF">�����鿴���˵��</font></strong></a></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47" id="tbsm" style="display:none;">
				        	
						һ��	���÷�Χ<br/>
&nbsp;���������ڲ������ա����幤�̻�����������Ӫ���á��͡�������ҵ��λ�ĳа���Ӫ�����⾭Ӫ���á���������˰�ĸ��幤�̻����а����⾭Ӫ�ߡ����˶�����ҵͶ���ߺͺϻ���ҵ�ϻ��˵ĸ�������˰��Ȼ�����ɡ���˰���ڰ����걨ʱ����ͬʱ��������2������������˰������Ϣ��B������<br/>
&nbsp;�ϻ���ҵ������������������Ȼ��Ͷ���ߵģ�Ӧ�ֱ������<br/>
����	�걨����<br/>
&nbsp;���幤�̻������˶�����ҵͶ���ߡ��ϻ���ҵ�ϻ��˵���������Ӫ����Ӧ�ɸ�������˰�������˰�걨��Ӧ��������˺��������ڰ���<br/>
&nbsp;������ҵ��λ�а���Ӫ�����⾭Ӫ��Ӧ�ɸ�������˰�������˰�걨��Ӧ��������˺���ʮ���ڰ�����˰��һ���ڷִ�ȡ�óа������⾭Ӫ���õģ�Ӧ��������˺��������ڰ��������ɡ�<br/>
&nbsp;��˰�˲��ܰ��涨���ް�����˰�걨�ģ�Ӧ�����ա��л����񹲺͹�˰�����չ����������¼��˰�����ܷ�������ʵʩϸ��Ĺ涨���������걨��<br/>
����	���������д���£�<br/>
&nbsp;��һ����ͷ��Ŀ<br/>
&nbsp;˰�������ڣ���ָ��˰��ȡ�����õ�Ӧ�ɸ�������˰��������ڼ䣬Ӧ��д�������ֹ�����ա�<br/>
&nbsp;������������Ϣ��<br/>
&nbsp;&nbsp;1.	Ͷ������Ϣ������д���幤�̻�ҵ�����а���Ӫ�ߡ����⾭Ӫ�ߡ����˶�����ҵͶ���ߡ��ϻ���ҵ�ϻ��˵������Ϣ��<br/>
&nbsp;&nbsp;&nbsp;��1��	��������д��˰���������й�������ס�����ˣ�������Ӧ�����С�����ͬʱ��д��<br/>
&nbsp;&nbsp;&nbsp;��2��	���֤�����ͣ���д��ʶ����˰��Ψһ��ݵ���Ч֤�����ơ�<br/>
&nbsp;&nbsp;&nbsp;&nbsp;��	���й�������ס���ĸ��ˣ���д���֤������֤��ʿ��֤��֤�����ơ�<br/>
&nbsp;&nbsp;&nbsp;&nbsp;��	���й�������ס���ĸ��ˣ���д���ա��۰ľ��������ڵ�ͨ��֤��̨�����������½ͨ��֤��֤�����ơ�<br/>
&nbsp;&nbsp;&nbsp;��3��	���֤�����룺��д��˰�����֤���ϵĺ��롣<br/>
&nbsp;&nbsp;&nbsp;��4��	����������������д��˰�˵Ĺ������ߵ�����<br/>
&nbsp;&nbsp;&nbsp;��5��	��˰��ʶ��ţ����й�������ס���ĸ�����д����ס���ĸ��˲���д��������д˰����ظ����18λ��˰��ʶ��š�˰�����δ����ģ�����д��<br/>
&nbsp;&nbsp;&nbsp;˰����ظ��辳����ס�����˵�18λ��˰��ʶ��ţ���Ϊ��Ψһ���ʶ���룬����˰�˵�����˰����ذ��������˰�����۽������˰������˰�˳��ο۽��걨ʱ��������˰����ظ��衣<br/>
&nbsp;&nbsp;2.	��Ͷ�ʵ�λ��Ϣ����<br/>
&nbsp;&nbsp;&nbsp;��1��	���ƣ���д˰����غ˷���Ͷ�ʵ�λ˰��Ǽ�֤�������ĵ�λȫ�ơ�<br/>
&nbsp;&nbsp;&nbsp;��2��	��˰��ʶ��ţ���д˰����غ˷���˰��Ǽ�֤���롣<br/>
&nbsp;&nbsp;&nbsp;��3��	���ͣ���˰�˸�����������ڶ�Ӧ���ڴ򡰡̡���<br/>
&nbsp;���������ڸ��е���д��<br/>
&nbsp;&nbsp1.	��1�С������ܶ����д��Ͷ�ʵ�λ�ڱ�����ȡ�õ������ܶ<br/>
&nbsp;&nbsp;2.	��2�С��ɱ�������д��Ͷ�ʵ�λ�ڱ�������Ҫ��Ӫҵ���������Ӫҵ�����ĳɱ��ܶ<br/>
&nbsp;&nbsp;3.	��3�С�Ӫҵ���á������Ͷ�ʵ�λ��������Ʒ�Ͳ��ϡ��ṩ����Ĺ����з����ĸ��ַ��á�<br/>
&nbsp;&nbsp;4.	��4�С�������á������Ͷ�ʵ�λΪ��֯�͹�����ҵ������Ӫ�����Ĺ�����á�<br/>
&nbsp;&nbsp;5.	��5�С�������á������Ͷ�ʵ�λΪ�Ｏ������Ӫ�����ʽ�ȷ����ĳ��ʷ��á�<br/>
&nbsp;&nbsp;6.	��6�С�Ӫҵ˰�𼰸��ӡ������Ͷ�ʵ�λ��Ӫ�������Ӫҵ˰������˰������ά������˰����Դ˰��������ֵ˰�ͽ����Ѹ��ӵ����˰�ѡ�<br/>
&nbsp;&nbsp;7.	��8�С������ܶ������������μ��㡣<br/>
&nbsp;&nbsp;��8�У���1�ШD��2�ШD��3�ШD��4�ШD��5�ШD��6�ШD��7��<br/>
&nbsp;&nbsp;8.	��10�С������涨��׼�۳�����Ŀ������ָ��Ͷ�ʵ�λ������������˰������ʵʩ���������˰�շ��ɷ������߹涨�Ŀ۳���׼���۳��ĸ��ֳɱ������ú���ʧ��Ӧ�����Ӧ��˰���ö�Ĳ��֡�<br/>
&nbsp;&nbsp;9.	��24�С�������۳�����Ŀ������ָ�涨������۳�������Ͷ�ʵ�λ�ѽ���۳��ĸ���ɱ������ú���ʧ��Ӧ�����Ӧ��˰���ö�Ĳ��֡�<br/>
&nbsp;&nbsp;10.	��35�С���ծ��Ϣ���롱����ָ��ҵ��������˰�����Ѽ�������������ծ��ȡ�õ���Ϣ��<br/>
&nbsp;&nbsp;11.	��37�С���ǰ����������������ָ��ǰ��ȷ����Ķ�ƻ��ټƵ�Ӧ��˰���ö<br/>
&nbsp;&nbsp;12.	��38�С�����˰�������������Ӫ���á�������������μ��㡣<br/>
&nbsp;&nbsp;��38�У���8�У���9�ШD��34�ШD��37��<br/>
&nbsp;&nbsp;13.	��39�С��ֲ���ǰ��ȿ��𡱣���ָ��ҵ���ݹ涨����ǰ��ȿ���������˰ǰ�ֲ�����Ӧ������Ӧ��˰���ö<br/>
&nbsp;&nbsp;14.	��40�С��������������˰��Ϊ�ϻ���ҵ�ϻ��˵ģ���д����������������պϻ���ҵ���䷽���й涨�ĸúϻ��˵ı�����д��û�У�����ƽ�����䡣<br/>
&nbsp;&nbsp;15.	��41�С�����۳����������á�����ָ���շ��ɷ���涨����˰ǰ�۳����������á�û�еģ�����磺������˰���ֹܾ�����ʦ��������ҵ��Ա�йظ�������˰����Ĺ��桷������˰���ֹܾ���2012���53�ţ��������涨�����<br/>
&nbsp;&nbsp;16.	��42�С�Ͷ���߼������á�����ָ����˰�����йط��ɷ���涨���ڸ��幤�̻�ҵ�������˶�����ҵͶ���ߺͺϻ���ҵ�ϻ��˵�������Ӫ���ü�����������˰ʱ������˰ǰ�۳���Ͷ���߱��˵����Ƽ������á�2011��9��1����ִ��42000Ԫ/���׼���Ժ��׼���������߹涨ִ�С�<br/>
&nbsp;&nbsp;17.	��43�С�Ӧ��˰���ö�����ݲ�ͬ�����������дμ�����д��<br/>
&nbsp;&nbsp;&nbsp;��1��	��˰��Ϊ�Ǻϻ���ҵ�ϻ��˵�<br/>
&nbsp;&nbsp;&nbsp;��43�У���38�У���39�У���41�У���42��<br/>
&nbsp;&nbsp;&nbsp;��2��	��˰��Ϊ�ϻ���ҵ�ϻ��˵�<br/>
&nbsp;&nbsp;&nbsp;��43�У�����38�У���39�У�����40�У���41�У���42��<br/>
&nbsp;&nbsp;18.	��44�С�˰�ʡ�����45�С�����۳�����������˰���������涨��д��<br/>
&nbsp;&nbsp;19.	��46�С�Ӧ��˰�����������μ�����д��<br/>
&nbsp;&nbsp;��46�У���43�С���44�У���45��<br/>
&nbsp;&nbsp;20.	��48�С�ȫ��Ӧ��˰�����������μ�����д��<br/>
&nbsp;&nbsp;��48�У���46�У���47��<br/>
&nbsp;&nbsp;21.	��51�С�Ӧ�����ˣ�˰�����������μ�����д��<br/>
&nbsp;&nbsp;��51�У���48�У���49�У���50��<br/>

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