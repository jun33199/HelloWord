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
	List jsfwList = (List) session.getAttribute("JSFWLIST");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.syax.bjtax.shenbao.model.dm.FWYWFW"%>
<html>

<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
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
<script language="JavaScript" src="js/calculate.js">
    </script>

<link href="css/jmba.css" rel="stylesheet" type="text/css">

<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
<script language="JavaScript">
var jsfw= new Array(<%=jsfwList.size()%>);
<%
for(int i=0;i<jsfwList.size();i++)
{
	FWYWFW tmpJsfw = (FWYWFW)jsfwList.get(i);
	out.println("jsfw["+i+"] = [\""+tmpJsfw.getFWYWFWDM()+"\",\""+tmpJsfw.getFWYWFWMC()+"\"];");
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
var jsfwlx = '<%=request.getSession().getAttribute("FWYWDM18")==null?"":request.getSession().getAttribute("FWYWDM18")%>';
//alert(jsfwlx);
function parseXmlOnLoad()
{
	var xslPath='/XSLTWEB/model/030018/XSLT/basx018.xsl';

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
var obj=document.getElementById("jsfwdmdiv");
//alert(obj);
var select1 = document.createElement("select");
        var ooption = new Array();
        for(var i=0;i<jsfw.length;i++)
        {
               select1.options[i] = new Option(jsfw[i][1], jsfw[i][0]);
                if(jsfwlx!=""&&jsfw[i][0]==jsfwlx){
                //alert(tempgxjslx);
               select1.options[i].selected=true;
               }
        }
        select1.id="fwywfwdm";
        obj.appendChild(select1);
         //if(tempxsllx!=""&&tempxsllx=='VIEW')
               //select1.disabled=true;
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
	var objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
	objNode.appendChild(objTemp);
	getChildren(objTemp,"xh");
	getChildren(objTemp,"fwywfwdm");
	getChildren(objTemp,"zsbh");
	getChildren(objTemp,"zsqsrq");
	getChildren(objTemp,"zszzrq");
	getChildren(objTemp,"jmse");
}
// ���ַ�����ʽ��Ϊ���Ҹ�ʽ
// parΪ0���Զ����0.00
// parΪ1�����Զ����
function formatKsslJsje(obj)
{
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
function checkYm()
{
	var zsbh = document.all("zsbh").value;
		if(zsbh == "" ){
			alert("�����Ƚ��ͷ�����ҵ֤���Ų���Ϊ��!");
			document.forms[0].zsbh.focus();
			return false;
		}
		var zsqsrq = document.all("zsqsrq").value;
		if(zsqsrq == "" ){
			alert("�����Ƚ��ͷ�����ҵ֤����Ч������Ϊ��!");
			//document.forms[0].zsqsrq.focus();
			return false;
		}
		if (!formatTime(zsqsrq))
 		{
    		alert("�����Ƚ��ͷ�����ҵ֤����Ч�����ʽ����");
    		//document.forms[0].zszzrq.focus();
    		return false;
 		}
		var zszzrq = document.all("zszzrq").value;
		if(zszzrq == "" ){
			alert("�����Ƚ��ͷ�����ҵ֤����Ч��ֹ����Ϊ��!");
			//document.forms[0].zszzrq.focus();
			return false;
		}
		if (!formatTime(zszzrq))
 		{
    		alert("�����Ƚ��ͷ�����ҵ֤����Ч��ֹ��ʽ����");
    		//document.forms[0].zszzrq.focus();
    		return false;
 		}
 		if(zsqsrq>zszzrq)
 		{
 			alert("֤����Ч��ֹ����С��֤����Ч����!");
			//document.forms[0].zszzrq.focus();
			return false;
 		}
		var jmse = document.all("jmse").value;
		if(jmse == "" ){
			alert("����Ԥ�Ƶļ���˰���Ϊ��!");
			document.forms[0].jmse.focus();
			return false;
		}
	return true;
}
	function doSave(){
		
		if(!checkYm() ){
		return false;
   		}
   		if(!confirm("�Ƿ񱣴�¼������?"))
		{
			return false;
		}
		var old  = document.forms[0].ywczlx.value;
		
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
		
}
function doEditZb(){
		if(!checkYm() ){
			return false;
   		}
		if(!confirm("�Ƿ����ɱ�����"))
		{
			return false;
		}
		var old  = document.forms[0].ywczlx.value;
		
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
 
function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
}
function doReset(){
document.forms[0].jjkcje.value="";
document.forms[0].zfgz.value="";

}
 
 	function getRadioValue(obj)
	{
		var objLength = obj.length;
		var k=0;
		for (i=0;i<objLength;i++)
		{
			if(obj[i].checked == true)
			{
				k=i;//Ѱ��radio�����б�ѡ�е�ֵ��λ��
				return obj[k].value;//������ֵ
			}		
		}
		return "";
     
	}


// ���ַ�����ʽ��Ϊ���Ҹ�ʽ
// parΪ0���Զ����0.00
// parΪ1�����Զ����
function formatKsslJsje(obj)
{
	//alert("kssljsje");
	return (checkvalue(obj,0)&&formatCurrency(obj,0));
}

</script>


<title>¼�뾭�϶��ļ����Ƚ��ͷ�����ҵ����˰��������</title>

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


		<form name="Form1" method="post" action="/shenbao/basx018.dc"><input
			name="actionType" type="hidden" id="actionType"></input>
		<table width="90%" border="0" cellpadding="0" cellspacing="0"
			align="center">
			<!--  <input name="tbblx" type="hidden" id="tbblx">-->
			<tr>
				<td valign="top" class="title"><br>
				<table width="75%" cellspacing=0 border=0 class="table-99">
					<tr>
						<td class="1-td1">¼�뾭�϶��ļ����Ƚ��ͷ�����ҵ����˰��������</td>
					</tr>
					<tr>
						<td class="1-td2"><br>
						<div id="result"></div>
						<br />
						<table width="100%" border="0" align="center">
							<tr align="center">


								<td><img src="<%=static_contextpath%>images/baocun1.jpg"
									onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'"
									onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'"
									alt="����" onclick="return doSave()" style="cursor:hand"></td>
								<td><img src="<%=static_contextpath%>images/b_scbab1.jpg"
									onmouseover="this.src='<%=static_contextpath%>images/b_scbab2.jpg'"
									onmouseout="this.src='<%=static_contextpath%>images/b_scbab1.jpg'"
									alt="���ɱ�����" onclick="doEditZb()" style="cursor:hand" width="95" height="22"></td>
								
								<td><img src="<%=static_contextpath%>images/fanhui1.jpg"
									onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'"
									onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'"
									alt="����" onclick="doReturn()" style="cursor:hand"></td>
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
