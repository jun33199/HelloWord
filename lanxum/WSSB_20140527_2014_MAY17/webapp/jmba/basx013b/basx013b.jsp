<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.GXJSLY"%>
<%@page import="java.util.*"%>

<%
String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
List codeTable1 = (List)session.getAttribute("codeTable");
String ymlx =(String)request.getSession().getAttribute("XSLLX13b");
String state = (String)request.getSession().getAttribute("querystate");
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
<html>
<head>
<title>¼�봴ҵͶ����ҵ�ֿ�Ӧ��˰���ö������</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/calculate.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>

<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">

<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
<script language="JavaScript">

var codeTable1= new Array(<%=codeTable1.size()%>);

<%
out.println("codeTable1[0] = [\"\",\"\"];");
for(int i=0;i<codeTable1.size();i++)
{
	GXJSLY tmpl = (GXJSLY)codeTable1.get(i);
    int tmpI = i + 1;
	out.println("codeTable1["+tmpI+"] = [\""+tmpl.getGXJSLYDM()+"\",\""+tmpl.getGXJSLYMC()+"\"];");
}

%>
var tze='';
var dke='';
var dnkdke='';
var jze='';
var tznd='';
var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
//alert(strXml);
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';

var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var tempjszrlx = '<%=request.getAttribute("JSJRLX13b")==null?"":request.getAttribute("JSJRLX13b")%>';
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX13b")==null?"":request.getSession().getAttribute("XSLLX13b")%>';
function parseXmlOnLoad()
{
	var xslPath='';
 //alert(tempxsllx);
  if(tempxsllx=='VIEW'){
	 xslPath='/XSLTWEB/model/030013/XSLT/basx013bview.xsl';
	 }else{
	 xslPath='/XSLTWEB/model/030013/XSLT/basx013b.xsl';
	 }
	 //alert(xslPath);
	if (strXml != "")
    {
        //alert(strXml);
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    //alert("ffff");
    insertCodeTablel();
    var tmpState = document.getElementById("querystate").value;
//    alert(tmpState);

    if(tmpState == "1"){
        document.getElementById("btzqyssd").value = "0";
        document.getElementById("btzqyjsjdm").disabled=true;
        document.getElementById("swdjzh").disabled=true;
        document.getElementById("btzqymc").disabled=true;
    }else if(tmpState == "0"){
        document.getElementById("btzqyssd").value = "1";
        document.getElementById("btzqyjsjdm").disabled=true;
        document.getElementById("swdjzh").disabled=false;
        document.getElementById("btzqymc").disabled=false;
    }else{
        document.getElementById("btzqyssd").value = "0";
        document.getElementById("btzqyjsjdm").disabled=true;
        document.getElementById("swdjzh").disabled=false;
        document.getElementById("btzqymc").disabled=false;
    }
//    alert(document.getElementById("btzqyssd").value);
    if(document.getElementById("btzqyssd").value == "0"){
        document.getElementById("btzqyjsjdm").disabled=false;
    }else{
        document.getElementById("btzqyjsjdm").disabled=true;
    }
//ssd1();
  //  ssd2();
    return true;
}
function ssd2(){
//    alert("1111");
document.getElementById("btzqyjsjdm").value="";
document.getElementById("btzqyjsjdm").disabled=true;
document.getElementById("swdjzh").disabled=false;
        document.getElementById("btzqymc").disabled=false;
document.getElementById("btzqyssd").value = "1";

}
function ssd1(){
    var tmpState = document.getElementById("querystate").value;
    if(tmpState == "1"){
        document.getElementById("btzqyjsjdm").disabled=true;
        document.getElementById("swdjzh").disabled=true;
        document.getElementById("btzqymc").disabled=true;
    }else if(tmpState == "0"){
        document.getElementById("btzqyjsjdm").disabled=true;
        document.getElementById("swdjzh").disabled=false;
        document.getElementById("btzqymc").disabled=false;
    }else{
        document.getElementById("btzqyjsjdm").disabled=false;
        document.getElementById("swdjzh").disabled=false;
        document.getElementById("btzqymc").disabled=false;
    }

document.getElementById("btzqyjsjdm").disabled=false;
document.getElementById("btzqyssd").value = "0";
}
function insertCodeTablel()
{
var obj1=document.getElementById("zxgxjsqylydmdiv");

var select1 = document.createElement("select");

        var ooption = new Array();
        for(var i=1;i<codeTable1.length;i++)
        {
               select1.options[i] = new Option(codeTable1[i][1], codeTable1[i][0]);
                if(tempjszrlx!=""&&codeTable1[i][0]==tempjszrlx)
               select1.options[i].selected=true;
        }
        select1.id="gxjslydm";
        obj1.appendChild(select1);
        if(tempxsllx!=""&&tempxsllx=='VIEW')
               select1.disabled=true;
}


function getPostXml(objForm)
{	//alert("88888");
	var retstr;
	//��������
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//��˰����Ϣ
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);

	applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc"
    ,"fhwjmc","qsrq","ztdm","ztmc","jzrq","szdm","szmc","bsfsdm","bsfsmc"]);
	//�걨����
	getSbsj(objForm);

	//ȥ��ĩβ�Զ���ӵĻس�
	//alert(g_Doc.XMLHeader);
	//alert(g_Doc.XMLDoc.xml);
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	//alert(retstr);
	return retstr;
}
function getChildren(temp,strTag)
{
	//alert("strTag = " + strTag);
	var element=document.getElementById(strTag);
	var objTemp=temp;
	var node=g_Doc.XMLDoc.createElement(strTag);
	objTemp.appendChild(node);
	var strValue="";
	if(element!=null)
	{
	if(strTag=="tze"){
	strValue=Trim(tze);
	}else if(strTag=="dke"){
	strValue=Trim(dke);
	}else if(strTag=="dnkdke"){
	strValue=Trim(dnkdke);
	}else if(strTag=="jze"){
	strValue=Trim(jze);
	}else if(strTag=="btzqyssd"){
	 if(document.getElementsByName(strTag)[0].checked){
               strValue=formString("0");
           }else{
               strValue=formString("1");
           }
          // alert(strValue);
	}else if((strTag=="tznd")){
	strValue=tznd;
	}else{
		strValue=formString(element.value);
		}

//		 alert(element+strValue);
        //alert(strValue);
		var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
		node.appendChild(objCDATA);
	}
}

//�����걨����
function getSbsj(objForm)
{
	var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
	var objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
	objNode.appendChild(objTemp);
	getChildren(objTemp,"xh");
	getChildren(objTemp,"basqwsh");
	getChildren(objTemp,"jsjdm");
	getChildren(objTemp,"band");
	getChildren(objTemp,"gxjslydm");
	getChildren(objTemp,"swdjzh");
	getChildren(objTemp,"nsrmc");
    getChildren(objTemp,"lrr");
	getChildren(objTemp,"lrrq");
    getChildren(objTemp,"swjgzzjgdm");
    getChildren(objTemp,"btzqyssd");
    getChildren(objTemp,"btzqyjsjdm");
    getChildren(objTemp,"tze");
	getChildren(objTemp,"dke");
	getChildren(objTemp,"dnkdke");
	getChildren(objTemp,"jze");
	getChildren(objTemp,"btzqymc");
	getChildren(objTemp,"tznd");



}

function dofanhui(){
    document.forms[0].actionType.value="Return";
	document.forms[0].submit();
}

function doReturn()
{
document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
}

function change(lx,xh){
//alert(lx+"------"+xh);
		var tze = "TZE"+xh;
		var kdke = "DKE"+xh;
		var sjdke = "DNKDKE"+xh;
		var jzdke = "JZE"+xh;
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
				if(!(checkvalue(document.all(sjdke),0)&&formatCurrency(document.all(sjdke),0))){
					return false;
				}
			}
		}
	}
function checkTzxx(){
		var ymband=document.getElementById("band").value
		for(var n=2006;n<=ymband;n++){
			var tze = "TZE"+n;
			var kdke = "DKE"+n;
			var sjdke = "DNKDKE"+n;
			var jzdke = "JZE"+n;
			if(n==2006 || n==2007){
				//�жϸ���ĵ��굱������Ͷ�ʶ��Ƿ�Ϊ��
				if(document.all(tze).value == ""){
					document.all(tze).value = "0.00";
				}
				if(document.all(sjdke).value != "" && document.all(sjdke).value!="0.00"){
					alert(n+"Ͷ����ȵĵ���ʵ�ʵֿ�Ӧ��˰���ö�ӦΪ�㣡");
					document.all(sjdke).focus();
					return false;
				}else{
					document.all(kdke).value = "0.00";
					document.all(sjdke).value = "0.00";
					document.all(jzdke).value = "0.00";
				}
			}else{
				//�жϸ���ĵ��굱������Ͷ�ʶ��Ƿ�Ϊ��
				if(document.all(tze).value == ""){
					document.all(tze).value = "0.00";
				}
				//�жϸ���ĵ���ʵ�ʵֿ�Ӧ��˰���ö��Ƿ�Ϊ��
				if(document.all(sjdke).value == ""){
					document.all(sjdke).value = "0.00";
				}
				var kdknd = n-2;//ȡ��2��ǰ��Ͷ�����
				var ztze = 0;
				var qnnd = n-1;//ȥ��Ͷ�����
				var zsjdke = 0;
				//��ȡ2��ǰ��Ͷ�ʶ��ܺ�
				for(var m=2006;m<=kdknd;m++){
					var mtze = "TZE"+m;
					ztze += Number(document.all(mtze).value);
				}
				//ȡ��1��ǰ�ĵֿ۶��ܺ�
				for(var x=2006;x<=qnnd;x++){
					var xsjdke = "DNKDKE"+x;
					zsjdke += Number(document.all(xsjdke).value);
				}
				var kdktze = "TZE"+kdknd;//ȡ��2��ǰ��Ͷ�����ҳ��ġ���������Ͷ�ʶ��Ӧ�����
				var qnjzdke = "JZE"+qnnd;//ȡ��ȥ��Ͷ�����ҳ��ġ���ת�Ժ���ȵֿ�Ӧ��˰���ö��Ӧ�����
				//�ж�ǰ��Ͷ�ʶ��Ƿ�Ϊ�գ�ȥ�ꡱ��ת�Ժ���ȵֿ�Ӧ��˰���ö�Ƿ�Ϊ��
				if((document.all(kdktze).value != "" && document.all(kdktze).value!="0.00")||
					(document.all(qnjzdke).value != "" && document.all(qnjzdke).value!="0.00")){
					//�����Ϊ�գ������ĵ���ɵֿ�Ӧ��˰���ö�=2��ǰ�ĵ�������Ͷ�ʶ�*0.7
					document.all(kdke).value = ztze * 0.7 - zsjdke;
					//���ֽ�����������
					var num = round2(document.all(kdke).value);
					document.all(kdke).value = num;
					//������ת��ΪС�������λ
					formatCurrency(document.all(kdke),0);
				}else{
					//2��ǰͶ����ȵ�Ͷ�ʶ�Ϊ��
					document.all(kdke).value = "0.00";
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

		}
		return true;

	}


function checkYm(){
 if(document.getElementsByName("btzqyssd")[0].checked){
 if(Trim(document.getElementById("btzqyjsjdm").value).length<=0&&Trim(document.getElementById("swdjzh").value).length<=0){
 alert("�����������˰��Ǽ�֤�Ŷ�ѡһ¼�룡");
 return false;
 }
 }else{
 if(Trim(document.getElementById("swdjzh").value).length<=0){
 alert("˰��Ǽ�֤��Ϊ��¼���¼�룡");
 return false;
 }
 }
 if(Trim(document.getElementById("btzqymc").value).length<=0){
 alert("��˰������Ϊ��¼���¼�룡");
 return false;
 }
 if(Trim(document.getElementById("swdjzh").value).length<=0){
 alert("˰��Ǽ�֤��Ϊ��¼���¼�룡");
 return false;
 }
 if(Trim(document.getElementById("gxjslydm").value).length<=0){
 alert("��С���¼�����ҵ������Ϊ�գ�");
 return false;
 }
 return true;
}
	function Trim(str) {
return str.replace(/(^\s*)|(\s*$)/g, "");
}
function doSave(){
//alert(checkTzxx());
//alert(checkYm());
//alert(document.getElementById("btzqyjsjdm").value+"--00000000000");
if(checkTzxx()&&checkYm()){
var ymband=document.getElementById("band").value
//alert(ymband);
var tzeid='TZE';
var dkeid='DKE';
var dnkdkeid='DNKDKE';
var jzeid='JZE';
for(var i=2006;i<=ymband;i++){
tzeid='TZE';
dkeid='DKE';
dnkdkeid='DNKDKE';
jzeid='JZE';
tzeid=tzeid+i;
dkeid=dkeid+i;
dnkdkeid=dnkdkeid+i;
jzeid=jzeid+i;
tznd+=i+",";
tze+=document.getElementById(tzeid).value+",";
dke+=document.getElementById(dkeid).value+",";
dnkdke+=document.getElementById(dnkdkeid).value+",";
jze+=document.getElementById(jzeid).value+",";
}
	var old  = document.forms[0].ywczlx.value;
	if (confirm("�Ƿ񱣴�¼������?"))
	{
		 if (document.forms[0].ywczlx.value == 0)
		{
			document.forms[0].ywczlx.value = 1;
		}
		else if (document.forms[0].ywczlx.value == 1)
		{
			document.forms[0].ywczlx.value = 2;
		}
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
		return true;
	}else
	{
		return false;
	}
	}

}
function Trim(str) {
return str.replace(/(^\s*)|(\s*$)/g, "");
}

function swdjzhhqsj(){


if(Trim(document.getElementById("swdjzh").value).length<=0){
alert("˰��Ǽ�֤�Ų���Ϊ�գ�");
return false;
}
 var ssddm = document.getElementById("btzqyssd").value;
// alert(ssddm);
   if(ssddm=="1"){
       return;
   }
if (document.forms[0].ywczlx.value == 0)
		{
			document.forms[0].ywczlx.value = 1;
		}
		else if (document.forms[0].ywczlx.value == 1)
		{
			document.forms[0].ywczlx.value = 2;
		}
		document.forms[0].actionType.value="SwdjzhHqwnsj";
			if (g_objSI.Container != '')
			{
				if (!doSubmitXML(document.forms[0],"SwdjzhHqwnsj",true,"",true))
				{
					 document.forms[0].ywczlx.value = old;
					return false;
				}
			}
			else
			{
			   if(!doSubmitXML(document.forms[0],"SwdjzhHqwnsj",false,"",true))
				{
					 document.forms[0].ywczlx.value = old;
					return false;
			   }
			}
		return true;


}
function jsjdmhqsj(){
if(Trim(document.getElementById("btzqyjsjdm").value).length<=0){
alert("��������벻��Ϊ�գ�");
return false;
}
if (document.forms[0].ywczlx.value == 0)
		{
			document.forms[0].ywczlx.value = 1;
		}
		else if (document.forms[0].ywczlx.value == 1)
		{
			document.forms[0].ywczlx.value = 2;
		}
		document.forms[0].actionType.value="JsjdmHqwnsj";
			if (g_objSI.Container != '')
			{
				if (!doSubmitXML(document.forms[0],"JsjdmHqwnsj",true,"",true))
				{
					 document.forms[0].ywczlx.value = old;
					return false;
				}
			}
			else
			{
			   if(!doSubmitXML(document.forms[0],"JsjdmHqwnsj",false,"",true))
				{
					 document.forms[0].ywczlx.value = old;
					return false;
			   }
			}
		return true;

}
function doViewZb()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="ViewZb";
	document.forms[0].submit();
}

function doClear(){
	alert("���");
}
function doEditZb(){
//alert(document.getElementById("btzqyjsjdm").value);
	if(checkTzxx()&&checkYm()){
		//if(checkYm()==true){

	var ymband=document.getElementById("band").value
//alert(ymband);
var tzeid='TZE';
var dkeid='DKE';
var dnkdkeid='DNKDKE';
var jzeid='JZE';
for(var i=2006;i<=ymband;i++){
tzeid='TZE';
dkeid='DKE';
dnkdkeid='DNKDKE';
jzeid='JZE';
tzeid=tzeid+i;
dkeid=dkeid+i;
dnkdkeid=dnkdkeid+i;
jzeid=jzeid+i;
tznd+=i+",";
tze+=document.getElementById(tzeid).value+",";
dke+=document.getElementById(dkeid).value+",";
dnkdke+=document.getElementById(dnkdkeid).value+",";
jze+=document.getElementById(jzeid).value+",";
}
	var old  = document.forms[0].ywczlx.value;
	if (confirm("�Ƿ����ɱ�����?"))
	{
		document.forms[0].actionType.value="EditZb";

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
		}
		}else
	{
		return false;
	}
}

</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="¼�봴ҵͶ����ҵͶ�ʡ��ֿ�Ӧ��˰���ö������" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/basx013b.dc">
	<input name="actionType" type="hidden" id="actionType">
    <input name="querystate" type="hidden" id="querystate" value="<%=state%>">
<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9'>
 <tr>
    <td valign="top">
      <table align="center" cellspacing="0" class="table-99">
<tr>
                <td class="1-td1">
              ��ҵͶ����ҵ�ֿ�Ӧ��˰���ö������
                </td>
              </tr>
              <tr>
                <td class="1-td2">

                  <br>
				<div id="result"></div>
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
 </td>
</tr>
<tr><td valign="bottom" align="center">
<br>
<jsp:include page="../../include/bottom.jsp" flush="true" >
</jsp:include>
 </td>
</tr>
</table>
</body>
</html>
