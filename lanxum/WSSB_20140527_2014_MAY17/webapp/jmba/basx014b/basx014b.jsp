<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml"%>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeTable"%>
<%@page import="java.util.List"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.NLMYJMXM"%>

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
	List zysbList = (List) session.getAttribute("ZYSBLIST");
	String size = (String) session.getAttribute("size");
    String tmpdmnd = (String) session.getAttribute("tmpdmnd");
    String ymlx =(String)request.getSession().getAttribute("XSLLX14b");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.syax.bjtax.shenbao.model.dm.FWYWFW"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.DMQYLX"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.GROUPZYSBLX"%>
<html>

<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="JavaScript" type="text/JavaScript"
	src="js1/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/date.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js1/WdatePicker.js"></script>
<script language="JavaScript"type="text/javascript" src="<%=static_contextpath%>js/calculate.js">
    </script>

        <script language="JavaScript"type="text/javascript" src="js/compute.js">
    </script>

    <script language="JavaScript"type="text/javascript" src="js/smsb_common.js">
    </script>
    <script language="JavaScript"type="text/javascript"  src="js/Bolan.js">
    </script>
    <script language="JavaScript"type="text/javascript" src="js/MathString.js">
    </script>
    <script language="JavaScript"type="text/javascript" src="js/Stack.js">
    </script>
    <script language="JavaScript"type="text/javascript" src="js/reader.js">
    </script>
    <script language="JavaScript"type="text/javascript" src="js/gmit_selectcontrol.js">
    </script>
    <script language="JavaScript"type="text/javascript" src="js/qysdsnew.js">
    </script>


<link href="css/jmba.css" rel="stylesheet" type="text/css">

<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
<script language="JavaScript">
//alert(<%=static_contextpath%>+"js/function.js");
var zysb= new Array(<%=zysbList.size()%>);
<%
for(int i=0;i<zysbList.size();i++)
{
	GROUPZYSBLX tmpJsfw = (GROUPZYSBLX)zysbList.get(i);
	out.println("zysb["+i+"] = [\""+tmpJsfw.getZYSBLXDM()+"\",\""+tmpJsfw.getZYSBLXMC()+"\",\""+tmpJsfw.getLEVEL()+"\"];");
}
        String containerName = "";

        com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData)session.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
        if (userdata.getCaflag())
        {
	        containerName = userdata.getCert().getContainerName();
        }
        else
        {
	        containerName = "";
        }
%>
g_objSI.Container = "<%=containerName%>";
var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var zysblxdm = '<%=request.getSession().getAttribute("ZYSBLXDM14B")==null?"":request.getSession().getAttribute("ZYSBLXDM14B")%>';
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX14b")==null?"":request.getSession().getAttribute("XSLLX14b")%>';
//alert(dmqylxdm);
function parseXmlOnLoad()
{
	var xslPath='';
if(tempxsllx=='VIEW'){
	 xslPath='/XSLTWEB/model/030014/XSLT/basx014bview.xsl';
	 }else{
	 xslPath='/XSLTWEB/model/030014/XSLT/basx014b.xsl';
	 }
	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    insertJsfw();
    return true;
}

function insertJsfw()
{
var obj=document.getElementById("zysblxdmdiv");
//alert(obj);
var select1 = document.createElement("select");
        
         var   oOption  =null;
        for(var i=0;i<zysb.length;i++)
        {
             if(zysb[i][2] == "1"){
        		var   group   =   document.createElement('OPTGROUP');   
                group.label   =   zysb[i][1]; 
                select1.appendChild(group); 
        	}else if(zysb[i][2] == "2"){
        	   oOption   =   document.createElement("OPTION");   
				select1.options.add(oOption);   
				oOption.text= zysb[i][1];
	            oOption.value= zysb[i][0];
        	}
    
		
                if(zysblxdm!=""&&zysb[i][0]==zysblxdm){
                //alert(tempgxjslx);
               oOption.selected=true;
               }
        }
        select1.id="zysblxdm";
        obj.appendChild(select1);
         if(tempxsllx!=""&&tempxsllx=='VIEW')
               select1.disabled=true;
}

 function getPostXml(objForm)
{
	var retstr;
	//��������
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//��˰����Ϣ
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);

	applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc","ztdm","ztmc","szdm","szmc","lrrq"]);
	//�걨����
	getSbsj(objForm);

	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	//alert(retstr);
	return retstr;
}
function getChildren(temp,strTag)
{
	var element=document.getElementById(strTag);
	var objTemp=temp;
	var node=g_Doc.XMLDoc.createElement(strTag);
	objTemp.appendChild(node);
	var strValue="";
	if(element!=null)
	{
		strValue=formString(element.value);
		var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
		node.appendChild(objCDATA);
	}
}

//�����걨����
function getSbsj(objForm)
{

	var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
	var objTemp = null;
	var rows = document.getElementById("mxvosize").value;
	
    var startYear = "";
//    alert(document.getElementById("dmnd").value);
    if(document.getElementById("dmnd").value!=""){
        startYear = document.getElementById("dmnd").value;
//        var myDate = new Date();
//        var curband = parseInt(myDate.getFullYear()) -1;
//        alert(curband);
//        rows = curband - startYear;
    }else{
        startYear = "2008";
    }
//    alert(startYear);
//    alert(rows);
     for(var i =0;i<rows;i++){
        //alert(i);
        var firstYear = parseInt(startYear) + i;
       // alert(firstYear);
        objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
        objNode.appendChild(objTemp);
       //alert(document.getElementById("xh2008").value);
       //alert(document.getElementById("xh2009").value);
        var xhs = document.getElementById("xh"+firstYear).value;
        //alert(document.getElementById("XH"+firstYear));
        //alert(xh);
        setNdChildren(objTemp,"xh",xhs);

		getChildren(objTemp,"zysblxdm");
		getChildren(objTemp,"zysbmc");

		var tzezs = document.getElementById("tzezs"+firstYear).value;
		//alert(tzezs);
		var dnkdmse = document.getElementById("dnkdmse"+firstYear).value;
		var dmynse = document.getElementById("dmynse"+firstYear).value;
		var jze = document.getElementById("jze"+firstYear).value;
		//���깺���豸Ͷ�ʶ�
		setNdChildren(objTemp,"tzezs",tzezs);
		//����ɵ����Ӧ��˰��
		setNdChildren(objTemp,"dnkdmse",dnkdmse);
		//����ʵ�ʵ����Ӧ��˰��
		setNdChildren(objTemp,"dmynse",dmynse);
		//��ת�Ժ���ȵ����Ӧ��˰��
		setNdChildren(objTemp,"jze",jze);


		setNdChildren(objTemp,"dmnd",firstYear);
		getChildren(objTemp,"ywcbabs");
		getChildren(objTemp,"basqwsh");
		getChildren(objTemp,"jsjdm");

     }



}

function setNdChildren(temp,strTag,value)
{

		var objTemp=temp;
		var node=g_Doc.XMLDoc.createElement(strTag);
		objTemp.appendChild(node);
		var objCDATA = g_Doc.XMLDoc.createCDATASection(value);
		node.appendChild(objCDATA);


}




<%/*����ƶ�*/%>
	function change(lx,xh){

		var tze = "tzezs"+xh;

		var kdke = "dnkdmse"+xh;
		var sjdke = "dmynse"+xh;
		var jzdke = "jze"+xh;
		if(lx == "1"){
			var tze_v = document.all(tze).value;
			if(tze_v != ""){
				if(!(checkvalue(document.all(tze),0)&&formatCurrency(document.all(tze),0))){
					return false;
				}
			}
		}else{
			var sjdke_v = document.all(sjdke).value;
			if(sjdke_v != ""){
				if(!(checkvalue(document.all(tze),0)&&formatCurrency(document.all(tze),0))){
					return false;
				}
			}
		}
	}



//���ݿ��ơ�
function doChange(){

	if(document.getElementById("dmnd").value!=""){
        startYear = document.getElementById("dmnd").value;
    }else{
        startYear = "2008";
    }
    var pageTzezs=document.getElementById("tzezs"+startYear).value;
    var pageDnkdmse=document.getElementById("dnkdmse"+startYear).value;
    var pageDmynse=document.getElementById("dmynse"+startYear).value;
    var pageJze=document.getElementById("jze"+startYear).value;

    if(pageTzezs.length>0)
    {
    	document.getElementById("dnkdmse"+startYear).value=pageTzezs*0.1;
    }
    if(pageDmynse.length>0)
    {
    	document.getElementById("jze"+startYear).value=pageDnkdmse-pageDmynse;
    }
}




// ���ַ�����ʽ��Ϊ���Ҹ�ʽ
// parΪ0���Զ����0.00
// parΪ1�����Զ����
function formatKsslJsje(obj)
{

	doChange();
	//alert("kssljsje");
	return (checkvalue(obj,0)&&formatCurrency(obj,0));

}
function formatTime(str)
{

  var   r   =   str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
  if(r==null) return   false;
  var  d=  new  Date(r[1],   r[3]-1,   r[4]);
  return  (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);

}
//��֤���
function validateYear(year) {
    if (!year) {
        return false;
    }
    if (year.length != 4) {
        return false;
    }
    if (!(/^[0-9]+$/.test(year))) {
        return false;
    }
 var date = new Date();
    var max = date.getFullYear()-1;
    var min = 1925;

    var n = new Number(year);
    if ((n.valueOf() > max) || (n.valueOf() < min)) {
        return false;
    }
    return true;

}
/*
function checkYm()
{
		var zysbmc = document.all("zysbmc").value;
		if(zysbmc == "" ){
			alert("��ҵ���ò�ʵ��ʹ�õ�ר���豸���Ʋ���Ϊ��!");
			document.forms[0].zysbmc.focus();
			return false;
		}
		var gznd = document.all("gznd").value;
		if(gznd == "" ){
			alert("������Ȳ���Ϊ��!");
			document.forms[0].gznd.focus();
			return false;
		}
		if(isNaN(gznd))
		{
			alert("�������ֻ��Ϊ����!");
			document.forms[0].gznd.focus();
			return false;
		}
		if(!validateYear(gznd))
		{
			alert("������ȸ�ʽ����ȷ!����д��ȷ��ȣ�");
			document.forms[0].jtzjqsnd.focus();
			return false;
		}

		var tze = document.all("tze").value;
		if(tze == "" ){
			alert("����ר���豸��Ͷ�ʶ��Ϊ��!");
			document.forms[0].tze.focus();
			return false;
		}
		var dmynse = document.all("dmynse").value;
		if(dmynse == "" ){
			alert("����ר���豸Ͷ�ʶ�ɵ����Ӧ��˰���Ϊ��!");
			document.forms[0].dmynse.focus();
			return false;
		}

	return true;
}
*/
<%/*Ч�鹺������Ƿ�Ϸ�*/%>
	function checkGznd(){
//	alert("���������֤");
		var band =  document.getElementById("band").value;

//		alert(document.getElementById("dmnd"));
		var gznd = document.getElementById("dmnd").value;
//        alert(gznd);
		if(gznd == "" ){
			alert("������Ȳ���Ϊ��!");
			document.forms[0].dmnd.focus();
			return false;
		}else{
			//Ч�鹺�����
			if(!isFullDate(gznd,1,1,true)){
				document.forms[0].dmnd.focus();
				return false;
			}else{
				if(gznd<2008 || gznd>band || (band-gznd)>4){
					alert("������Ȳ��Ϸ�!");
					document.forms[0].dmnd.focus();
					return false;
				}
			}
		}
		return true;
	}
	<%/*Ч���ӱ���Ϣ*/%>
	function checkYm(){
		var zysbmc = document.all("zysbmc").value;
		if(zysbmc == "" ){
			alert("����ר���豸���Ʋ���Ϊ��!");
			document.forms[0].zysbmc.focus();
			return false;
		}
		var zysblxdm = document.all("zysblxdm").value;
		 if(zysblxdm == "" ){
			alert("ר���豸���಻��Ϊ��!");
			document.forms[0].zysblxdm.focus();
			return false;
		 }
		return true;

	}
	<%/*Ч��Ͷ����Ϣ*/%>
	function checkTzxx(){

		var band =document.getElementById("band").value;
		//alert(band);
		var gznd = document.all("dmnd").value;
		//alert(gznd);
		for(var n=gznd;n<=band;n++){
			var tze = "tzezs"+n;
			var kdke = "dnkdmse"+n;
			var sjdke = "dmynse"+n;
			var jzdke = "jze"+n;
			if(n==gznd){
				//�жϸ���ĵ�������Ͷ�ʶ��Ƿ�Ϊ��
				if(document.all(tze).value == "" || document.all(tze).value == "0.00"){
					alert(n+"���깺���豸Ͷ�ʶ��������Ͷ�ʶ");
					document.all(tze).focus();
					return false;
				}else{
					//����ɵ����Ӧ��˰�� = ���깺���豸Ͷ�ʶ�*0.1
					document.all(kdke).value = document.all(tze).value * 0.1;
					//���ֽ�����������
					var num = round2(document.all(kdke).value);
					document.all(kdke).value = num;
					//������ת��ΪС�������λ
					formatCurrency(document.all(kdke),0);
				}
			}else{
				//���깺���豸Ͷ�ʶ�Ĭ��Ϊ0.00
				document.all(tze).value = "0.00";
				//����ĵ���ɵ����Ӧ��˰��=ȥ��Ľ�ת�Ժ���ȵ����Ӧ��˰��
				var qnjzdke = "jze"+(n-1);
				document.all(kdke).value = document.all(qnjzdke).value;
			}
			//�жϸ���ĵ���ʵ�ʵֿ�Ӧ��˰���ö��Ƿ�Ϊ��
			if(document.all(sjdke).value == ""){
				document.all(sjdke).value = "0.00";
			}
			//�жϸ���ĵ���ʵ�ʵֿ�Ӧ��˰���ö��Ƿ���ڵ���ɵֿ�Ӧ��˰���ö�
			if((document.all(kdke).value-document.all(sjdke).value)<0){
				alert(n+"����ʵ�ʵֿ�Ӧ��˰���ö����С�ڻ���ڵ���ɵֿ�Ӧ��˰���ö");
				document.all(sjdke).focus();
				return false;
			}else{
				//����������������ת�Ժ���ȵֿ�Ӧ��˰���ö�=����ɵֿ�Ӧ��˰���ö�-����ʵ�ʵֿ�Ӧ��˰���ö�
				document.all(jzdke).value = document.all(kdke).value-document.all(sjdke).value;
				//���ֽ�����������
				var num = round2(document.all(jzdke).value);
				document.all(jzdke).value = num;
				//������ת��ΪС�������λ
				formatCurrency(document.all(jzdke),0);
			}

		}
		return true;

	}
function doSave(){
	//if(!checkYm())
	//{
	//	return false;
	//}
if(checkGznd() && checkYm() && checkTzxx()){
	var old  = document.forms[0].ywczlx.value;
if (confirm("�Ƿ񱣴�¼������?"))
	{
		document.forms[0].actionType.value="Save";
		if (g_objSI.Container != '')
		{
			if (!doSubmitXML(document.forms[0],"Save",true,"",true))
			{
					document.forms[0].ywczlx.value = old;

				return false;
			}
		}
		else
		{
			if(!doSubmitXML(document.forms[0],"Save",false,"",true))
			{

				document.forms[0].ywczlx.value = old;
				return false;
			}
		}
        //alert(document.getElementById("dmnd").value);
        document.forms[0].tmpdmnd.value= document.getElementById("tmpdmnd").value;
		return true;
		}else
	{
		return false;
	}
}
}
function doEditZb(){


if(checkGznd && checkYm() && checkTzxx()){
	var old  = document.forms[0].ywczlx.value;

		document.forms[0].actionType.value="EditZb";
if (confirm("�Ƿ����ɱ�����"))
	{
		if (g_objSI.Container != '')
		{
			if (!doSubmitXML(document.forms[0],"EditZb",true,"",true))
			{
					document.forms[0].ywczlx.value = old;
				return false;
			}
		}
		else
		{
			if(!doSubmitXML(document.forms[0],"EditZb",false,"",true))
			{
				document.forms[0].ywczlx.value = old;
				return false;
			}
		}
		return true;
		}else
	{
		return false;
	}
		}
}

function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
}


function doAjaxQueryWnsj(){
    var wnbasqwsh = document.getElementById("basqbh1").value;

    if(wnbasqwsh == null || wnbasqwsh == "" ){
        alert("���걸�������Ų���Ϊ��!");
        return;
    }

    var xmlhttp_request;
    if(window.ActiveXObject){
        xmlhttp_request=new ActiveXObject("Microsoft.XMLHTTP");
    }else if(window.XMLHttpRequest){
        xmlhttp_request=new XMLHttpRequest();
    } else {
        return;
    }
    document.getElementById("tmpwsh").value = wnbasqwsh;

    xmlhttp_request.open("GET", "/shenbao/basx014b.dc?actionType=AjaxShow&wnbasqwsh="+wnbasqwsh, true);
    xmlhttp_request.send(null);
    xmlhttp_request.onreadystatechange =function zdyprocessAjaxResponse(wnbasqwsh){
        var xmlDOM;
        if (xmlhttp_request.readyState == 4) {
            if (xmlhttp_request.status == 200) {

                xmlDOM = xmlhttp_request.responseText;
                //alert(xmlDOM);
                var ztStr = xmlDOM.substr(0,1);
                xmlDOM = xmlDOM.substr(1);
                var xslPath='/XSLTWEB/model/030014/XSLT/basx014b.xsl';

                if (xmlDOM != ""){
                    if (! parseXml(xmlDOM,xslPath,"result")){
                        return false;
                    }
                    insertJsfw();

                    document.getElementById("basqbh1").value = document.getElementById("tmpwsh").value;
                    if(ztStr != "0"){
//edit at 20100222
                        document.getElementById("mxvosize").value = ztStr;
//edit at 20100222

                        document.getElementById("dmnd").disabled=true;
                        document.getElementById("zysbmc").disabled=true;
                        document.getElementById("zysblxdm").disabled=true;
                    }else{
                        document.getElementById("dmnd").disabled=false;
                        document.getElementById("zysbmc").disabled=false;
                        document.getElementById("zysblxdm").disabled=false;
                    }
                }

            }
        }
    }

}


function doAjaxQueryDmnd(){
    var dmnd = document.getElementById("dmnd").value;

    if(dmnd == null || dmnd == "" ){
        alert("������Ȳ���Ϊ��!");
        return;
    }
      if(checkGznd()){
    var xmlhttp_request;
    if(window.ActiveXObject){
        xmlhttp_request=new ActiveXObject("Microsoft.XMLHTTP");
    }else if(window.XMLHttpRequest){
        xmlhttp_request=new XMLHttpRequest();
    } else {
        return;
    }
    document.getElementById("tmpdmnd").value = dmnd;

    xmlhttp_request.open("GET", "/shenbao/basx014b.dc?actionType=AjaxDmnd&querydmnd="+dmnd, true);
    xmlhttp_request.send(null);
    xmlhttp_request.onreadystatechange =function zdyprocessAjaxResponse(dmnd){
        var xmlDOM;
        if (xmlhttp_request.readyState == 4) {
            if (xmlhttp_request.status == 200) {

                xmlDOM = xmlhttp_request.responseText;
                var ztStr = xmlDOM.substr(0,1);
                xmlDOM = xmlDOM.substr(1);
               // alert(xmlDOM);
                var xslPath='/XSLTWEB/model/030014/XSLT/basx014b.xsl';

                if (xmlDOM != ""){
                    if (! parseXml(xmlDOM,xslPath,"result")){
                        return false;
                    }
                    insertJsfw();
                }
                document.getElementById("mxvosize").value = ztStr;
                document.getElementById("basqbh1").value = document.getElementById("tmpwsh").value;
                document.getElementById("dmnd").value = document.getElementById("tmpdmnd").value;
            }
        }
    }
    }
}
function doViewZb()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="ViewZb";
	document.forms[0].submit();
}

</script>


<title>¼����ҵ�������ڻ������������ܽ�ˮ����ȫ������ר���豸��Ͷ�ʵ���˰������</title>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="parseXmlOnLoad()">

<table width="100%" height="100%" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" colspan=2 valign="top"><jsp:include
			page="../../include/SbHeader.jsp" flush="true">
			<jsp:param name="name" value="¼����˰����ҵ����˰����˰��������" />
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
		</jsp:include> <html:errors />


		<form name="Form1" method="post" action="/shenbao/basx014b.dc"><input
			name="actionType" type="hidden" id="actionType"></input> <input
			name="mxvosize" type="hidden" id="mxvosize" value="<%=size%>">
             <input name="tmpwsh" type="hidden" id="tmpwsh" >
              <input name="tmpdmnd" type="hidden" id="tmpdmnd" value="<%=tmpdmnd%>">
		<table width="90%" border="0" cellpadding="0" cellspacing="0"
			align="center">
			<!--  <input name="tbblx" type="hidden" id="tbblx">-->
			<tr>
				<td valign="top" class="title"><br>
				<table width="75%" cellspacing=0 border=0 class="table-99">
					<tr>
						<td class="1-td1">¼����ҵ�������ڻ������������ܽ�ˮ����ȫ������ר���豸��Ͷ�ʵ���˰������</td>
					</tr>
					<tr>
						<td class="1-td2"><br>
						<div id="result"></div>
						<br />
						<table width="100%" border="0" align="center">
							<tr align="center">
<%if (!"VIEW".equals(ymlx)){%>
                    	<td >
                      	<img onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/baocun2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/baocun1.jpg" name="shanchu11" width="95" height="22" border="0" id="baocun"  alt="����"

onclick="javascript:return doSave();">
                      </td>


                      <td >
                      <img onMouseOver="MM_swapImage('scbab','','<%=static_contextpath%>images/b_scbab2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/b_scbab1.jpg" name="shanchu11" width="95" height="22" border="0" id="scbab"  alt="���ɱ�����"

onclick="doEditZb()">

                      	<!--<img src="<%=static_contextpath%>images/qc-u2.jpg" name="shanchu11" width="79" height="22" border="0" id="shanchu11" alt="�ύ"

onclick="javascript:return doClear();">-->
                      </td>
<%}else{%>
 <td >
 <img onMouseOver="MM_swapImage('ckbab','','<%=static_contextpath%>images/b_ckbab2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/b_ckbab1.jpg" name="shanchu11" width="95" height="22" border="0" id="ckbab"  alt="�鿴������"

onclick="doViewZb()">

                      	<!--<img src="<%=static_contextpath%>images/qc-u2.jpg" name="shanchu11" width="95" height="22" border="0" id="shanchu11" alt="�ύ"

onclick="javascript:return doClear();">-->
                      </td>
                  <%}%>
                      <td >
                        <img onclick="doReturn()" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/fanhui2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/fanhui1.jpg"
                        width="95" height="22">
                      </td>
                    </tr>
						</table>
						</td>
					</tr>
				</table>

				</td>
			</tr>
		</table>
		</form>
	<tr>
		<td valign="bottom" align="center"><br>
		<jsp:include page="../../include/bottom.jsp" flush="true">
		</jsp:include></td>
	</tr>
</body>

</html>
