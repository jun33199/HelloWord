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
	List dmqyList = (List) session.getAttribute("DMQYLIST");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.syax.bjtax.shenbao.model.dm.FWYWFW"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.DMQYLX"%>
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
var dmqy= new Array(<%=dmqyList.size()%>);
<%
for(int i=0;i<dmqyList.size();i++)
{
	DMQYLX tmpJsfw = (DMQYLX)dmqyList.get(i);
	out.println("dmqy["+i+"] = [\""+tmpJsfw.getDMQYLXDM()+"\",\""+tmpJsfw.getDMQYLXMC()+"\"];");
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
var dmqylxdm = '<%=request.getSession().getAttribute("DMQYLXDM20")==null?"":request.getSession().getAttribute("DMQYLXDM20")%>';
//alert(dmqylxdm);
function parseXmlOnLoad()
{
	var xslPath='/XSLTWEB/model/030020/XSLT/basx020.xsl';

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
var obj=document.getElementById("dmqylxdmdiv");
//alert(obj);
var select1 = document.createElement("select");
        var ooption = new Array();
        for(var i=0;i<dmqy.length;i++)
        {
               select1.options[i] = new Option(dmqy[i][1], dmqy[i][0]);
                if(dmqylxdm!=""&&dmqy[i][0]==dmqylxdm){
                //alert(tempgxjslx);
               select1.options[i].selected=true;
               }
        }
        select1.id="dmqylxdm";
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
	getChildren(objTemp,"dmqylxdm");
	getChildren(objTemp,"zsbh");
	getChildren(objTemp,"zsqsrq");
	getChildren(objTemp,"zszzrq");
	getChildren(objTemp,"hlnd");
	getChildren(objTemp,"mzqsnd");
	getChildren(objTemp,"mzzznd");
	getChildren(objTemp,"jzqsnd");
	getChildren(objTemp,"jzzznd");
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
    var max = 9999;
    var min = 0000;

    var n = new Number(year);
    if ((n.valueOf() > max) || (n.valueOf() < min)) {
        return false;
    }
    return true;

}


function checkD1(obj){
	var t1 = trim(obj.value);
	if (t1==""){
	   return true;
	}
	while(t1.length>0 && t1.charAt(0)=='0')
	{
		t1 = t1.substring(1);
	}
	    	if (t1.length!=4){
	    	   alert("������4λ��ȣ�");	    	   
	    	   obj.focus();
	    	   obj.select();
	    	   return false;
	    	}
	    if(t1.length > 0 && isInt(t1)) {
	    	return true;
	    } else {
	      alert("������4λ��ȣ�");
	    	   obj.focus();
	    	   obj.select();
	    	   return false;
	    }	

}

function Trim(str) {
return str.replace(/(^\s*)|(\s*$)/g, "");
}

function checkYm()
{
	var zsbh = document.all("zsbh").value;
		if(zsbh == "" ){
			alert("���϶��Ķ�����ҵ֤���Ų���Ϊ��!");
			document.forms[0].zsbh.focus();
			return false;
		}
		var zsqsrq = document.all("zsqsrq").value;
		if(zsqsrq == "" ){
			alert("���϶��Ķ�����ҵ֤����Ч������Ϊ��!");
			//document.forms[0].zsqsrq.focus();
			return false;
		}
		 if (!formatTime(zsqsrq))
 		{
    		alert("���϶��Ķ�����ҵ֤����Ч�������ڸ�ʽ����");
    		//document.forms[0].zsqsrq.focus();
    		return false;
 		} 
		var zszzrq = document.all("zszzrq").value;
		if(zszzrq == "" ){
			alert("���϶��Ķ�����ҵ֤����Ч��ֹ����Ϊ��!");
			//document.forms[0].zszzrq.focus();
			return false;
		}
		if (!formatTime(zszzrq))
 		{
    		alert("���϶��Ķ�����ҵ֤����Ч����ֹ�ڸ�ʽ����");
    		//document.forms[0].zszzrq.focus();
    		return false;
 		}
 		 
 		if(zsqsrq>zszzrq)
 		{
 			alert("֤����Ч��ֹ����С��֤����Ч����!");
			//document.forms[0].zszzrq.focus();
			return false;
 		}
 		if(Trim(document.getElementById("hlnd").value).length<=0){
			alert("������Ȳ���Ϊ�գ�");
			return false;
		}else{
	 		if(!checkD1(document.getElementById("hlnd"))) {
		      return false;
	        }
	        if(document.getElementById("hlnd").value>document.getElementById("band").value){
	        alert("������Ȳ��ܴ��ڱ������");
	         return false;
	        }
 		}
 		
 		if(!checkD1(document.all("mzqsnd"))) {
	      return false;
        }	
	    if(!checkD1(document.all("mzzznd"))) {
	      return false;
        }	
	    if(!checkD1(document.all("jzqsnd"))) {
	      return false;
        }	
	    if(!checkD1(document.all("jzzznd"))) {
	      return false;
        }
        
        var qsnd=0;
        var jznd=0;
        if (trim(document.all("mzqsnd").value)!="" && trim(document.all("mzzznd").value)!="")
        {
         qsnd=parseInt(document.all("mzqsnd").value);
         jznd=parseInt(document.all("mzzznd").value);
        if (jznd<=qsnd){
          alert("������ֹ��ȱ������������ʼ��ȣ�");
          return false;
        }
        }else{
        if((trim(document.all("mzqsnd").value)!="" && trim(document.all("mzzznd").value)=="")||(trim(document.all("mzqsnd").value)=="" && trim(document.all("mzzznd").value)!="")){
         alert("������Ȳ���Ϊ�գ�");
         return false;
        }
        }
        if (trim(document.all("jzqsnd").value)!="" && trim(document.all("jzzznd").value)!="")
        {
         qsnd=parseInt(document.all("jzqsnd").value);
         jznd=parseInt(document.all("jzzznd").value);
        if (jznd<=qsnd){
          alert("������ֹ��ȱ�����ڼ�����ʼ��ȣ�");
          return false;
        }
        }else{
	        if((trim(document.all("jzqsnd").value)!="" && trim(document.all("jzzznd").value)=="")||(trim(document.all("jzqsnd").value)=="" && trim(document.all("jzzznd").value)!="")){
	         alert("������Ȳ���Ϊ�գ�");
	         return false;
	        }
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
   		if(!confirm("�Ƿ����ɱ�����?"))
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

//�����������
function doChengNd(){
    var pageHlnd=document.getElementById("hlnd").value;
    if(isNaN(pageHlnd)){
    alert("�������Ӧ��Ϊ��λ�����꣡");
    window.setTimeout("document.getElementById('hlnd').focus();", 50)  ; 
    return;
    }else{
    if(pageHlnd>2009||pageHlnd<(2009-5)){
    alert("������ȴ��ڻ�С�ڷ�Χ��");
    window.setTimeout("document.getElementById('hlnd').focus();", 50)  ; 
    return;
    }
    }
    var pageHlnd=document.getElementById("hlnd").value;
    if(pageHlnd.length != 4){
        alert("������4λ�������!");
        document.getElementById("HLND").focus();
        return;
    }else{
        document.getElementById("mzqsnd").value=parseInt(pageHlnd);
        document.getElementById("mzzznd").value=parseInt(pageHlnd)+2;
        document.getElementById("jzqsnd").value=parseInt(pageHlnd)+3;
        document.getElementById("jzzznd").value=parseInt(pageHlnd)+5;
    }

}

</script>


<title>¼�뾭�϶��Ķ�����ҵ����˰��������</title>

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


		<form name="Form1" method="post" action="/shenbao/basx020.dc"><input
			name="actionType" type="hidden" id="actionType"></input>
		<table width="90%" border="0" cellpadding="0" cellspacing="0"
			align="center">
			<!--  <input name="tbblx" type="hidden" id="tbblx">-->
			<tr>
				<td valign="top" class="title"><br>
				<table width="75%" cellspacing=0 border=0 class="table-99">
					<tr>
						<td class="1-td1">¼�뾭�϶��Ķ�����ҵ����˰��������</td>
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
