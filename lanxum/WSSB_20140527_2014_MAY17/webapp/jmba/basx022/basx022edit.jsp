<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml" %>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeTable"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Jnjpjsgzlx"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
			List jmlxList = (List) request.getAttribute("CS");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

var jmlx= new Array(<%=jmlxList.size()%>);

<%
for(int i=0;i<jmlxList.size();i++)
{
	Jnjpjsgzlx tmpJmlx = (Jnjpjsgzlx)jmlxList.get(i);
	out.println("jmlx["+i+"] = [\""+tmpJmlx.getJnjpjsgzxmdm()+"\",\""+tmpJmlx.getJnjpjsgzxmmc()+"\"];");
}

%>
<%
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


function parseXmlOnLoad()
{
	var xslPath='/XSLTWEB/model/030022/XSLT/basx022edit.xsl';

	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    
    insertJmlx();
    //�Ƿ�����ͬ��Դ������Ŀת�ú�����
    //initSFZR();
    return true;
}

function initSFZR()
{
	if(document.getElementsByName("ZRHTXM")[0].checked)
	{
		document.getElementById("disp2").style.display = "inline";
	}
	if(document.getElementsByName("ZRHTXMYH")[0].checked)
	{
		document.getElementById("disp3").style.display = "inline";
	}
}

function insertJmlx()
{
  			var obj=document.forms[0].jnjpjsgzxmdm;

			for(var i=0;i<jmlx.length;i++)
			{
				var yValue=jmlx[i][0];
				var yName=jmlx[i][1];
				var b=true;
				if(yValue==obj.value)
				{
					b=false;
				}
				if(b)
				{
					var item=new Option(yName,yValue);
					obj.options.add(item);
				}					
			}
			if (obj.value==""){
			   obj.options[0].selected=true;
			}
 
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
	<%/*Ч��ҳ��Ԫ��*/%>
	function checkYm(){
		if( document.all("jnjpjsgzxmdm").value == "") {
			alert("��Ŀ���Ʋ���Ϊ�գ�");
			return false;
		}
		
		
		var dybzlmc = trim(document.all("dybzlmc").value);
		 if(dybzlmc == "" ){
			alert("ȡ�õ�һ����������֤���������Ʋ���Ϊ��!");
			document.forms[0].dybzlmc().focus();
			return false;
		 }
		
		if(document.all("dybrq").value == "") {
			alert("ȡ�õ�һ��������Ӫ�����ʱ�䲻��Ϊ�գ�");
			return false;
		}
		
		//У�鵥ѡ����Ϊyyn
		//var checkZRHTXM_Y = document.getElementsByName("ZRHTXM")[0].checked;
		//var checkZRHTXMYH_Y  = document.getElementsByName("ZRHTXMYH")[0].checked;
		//var checkZRHTXMYHWJ_Y = document.getElementsByName("ZRHTXMYHWJ")[0].checked;
		//if(checkZRHTXM_Y==true && checkZRHTXMYH_Y==true && checkZRHTXMYHWJ_Y==false)
			//{
			//	alert("¼�����ݲ��Ϸ�,��\"��Ŀת�ú�ͬ����Ŀԭ�����Żݱ����ļ�\"ѡ�����ʱ����¼�����ݱ���Ϊ��");
			//	return false;
			//}
		
	if(!checkYear()) {
	return false;
	}
	
		 return true;
		
	}
   function   checkLength(obj,maxLength1)   
  {
  var evalue=obj.value;
  evalue=evalue.replace(/[^\x00-\xff]/g,'**');
//alert(evalue.length+"  "+obj.maxLength);
  var maxLength=maxLength1;
  if (obj.maxLength!=null){
     maxLength=obj.maxLength;
  }
  if(evalue.length>maxLength){
  alert("�������ݲ��ܴ���"+maxLength+"���ַ� ��");
  return   false;
  }  
return true; 
  }  
// ���ַ�����ʽ��Ϊ���Ҹ�ʽ
// parΪ0���Զ����0.00
// parΪ1�����Զ����
function formatCurrency10(obj)
{
return checkvalue(obj,0)&&formatCurrency(obj,0);
}
	
function getRadioChildren(temp,strTag)
{
    var obj=document.getElementsByName(strTag);
	var objTemp=temp;
	var node=g_Doc.XMLDoc.createElement(strTag);
	objTemp.appendChild(node);
	var strValue=formString(getRadioValue(obj));
	var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
	node.appendChild(objCDATA);
	
	var mcobj=document.all(strTag+"mc");
	if (strValue=="0"){
	  mcobj.value="��";
	}else{
    	mcobj.value="��";
	}
	
	return strValue;
}

function getRadioChildren(temp,strTag)
{
	var element=document.getElementsByName(strTag);
	if(element!=null)
	{
		strValue="";
		if(element[0].checked){
			strValue=element[0].value;
		}
		if(element[1].checked){
			strValue=element[1].value;
		}
		strValue=formString(strValue);
		var objTemp=temp;
		var node=g_Doc.XMLDoc.createElement(strTag);
		objTemp.appendChild(node);
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
	getChildren(objTemp,"jnjpjsgzxmdm");
	
	getChildren(objTemp,"dybzlmc");	
	getChildren(objTemp,"dybrq");
	

	getChildren(objTemp,"jbzsqsnd");
	getChildren(objTemp,"jbzszznd");
	getChildren(objTemp,"mzqsnd");
	getChildren(objTemp,"mzzznd");
	//getRadioChildren(objTemp,"ZRHTXM");
	//getRadioChildren(objTemp,"ZRHTXMYH");
	//getRadioChildren(objTemp,"ZRHTXMYHWJ");
	
	document.forms[0].jnjpjsgzxmmc.value= document.forms[0].jnjpjsgzxmdm.options[document.forms[0].jnjpjsgzxmdm.selectedIndex].innerText;
	getChildren(objTemp,"jnjpjsgzxmmc");
	
}
	function addYear() {
	    var t1 = trim(document.all("dybrq").value);
		if (t1==""){
	   		return ;
		}
	    if(checkD1(document.all("dybrq"))) {
	    	var t=t1.substring(0,4);
	    	document.all("mzqsnd").value = t;
		    document.all("mzzznd").value = parseInt(t) + parseInt("2");
		    document.all("jbzsqsnd").value = parseInt(document.all("mzzznd").value) + parseInt("1");
		    document.all("jbzszznd").value = parseInt(document.all("jbzsqsnd").value) + parseInt("2");
	    } 
	}
	function checkYear() {
	    if(!checkD1Null(document.all("dybrq"))) {
	      return false;
        }	
	    if(!checkD1(document.all("mzqsnd"))) {
	      return false;
        }	
	    if(!checkD1(document.all("mzzznd"))) {
	      return false;
        }	
	    if(!checkD1(document.all("jbzsqsnd"))) {
	      return false;
        }	
	    if(!checkD1(document.all("jbzszznd"))) {
	      return false;
        }	
       
       return true;
    }
function checkD1Null(obj){
	var t1 = trim(obj.value);
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
function checkD16(obj){
	var t1 = trim(obj.value);
	while(t1.length>0 && t1.charAt(0)=='0')
	{
		t1 = t1.substring(1);
	}
	    	if (t1.length!=6){
	    	   alert("������6λ���£�");	    	   
	    	   obj.focus();
	    	   obj.select();
	    	   return false;
	    	}
	    if(t1.length>0 && fnCheckDate(t1.substring(0,4)+"-"+t1.substring(4,6),1) ) {
	    	return true;
	    } else {
	      alert("������6λ���£�");
	    	   obj.focus();
	    	   obj.select();
	    	   return false;
	    }	

}
function doSave(value){
	
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
document.forms[0].wjmc.value="";
document.forms[0].dybzlmc.value="";
document.forms[0].dybrq.value="";
document.forms[0].mzqsnd.value="";
document.forms[0].mzzznd.value="";
document.forms[0].jbzsqsnd.value="";
document.forms[0].jbzszznd.value="";

document.forms[0].jnjpjsgzxmdm.options[0].selected=true;
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
 
//ZRHTXM��ʾ
	function clickZRHTXM_Y()
	{
		
		document.getElementById("disp2").style.display="inline";
		if(document.getElementsByName("ZRHTXMYH")[0].checked)
		{document.getElementById("disp3").style.display="inline";}
	}
	
	//ZRHTXM����
	function clickZRHTXM_N()
	{
		document.getElementsByName("ZRHTXMYH")[0].checked=false;
		document.getElementsByName("ZRHTXMYH")[1].checked=false;
	    document.getElementsByName("ZRHTXMYHWJ")[0].checked=false;
		document.getElementsByName("ZRHTXMYHWJ")[1].checked=false;
		document.getElementById("disp3").style.display="none";
		document.getElementById("disp2").style.display="none";	
	}
	
	//ZRHTXMYH��ʾ
	function clickZRHTXMYH_Y()
	{
		document.getElementById("disp3").style.display="inline";	
	}
	
	//ZRHTXMYH����
	function clickZRHTXMYH_N()
	{
		document.getElementsByName("ZRHTXMYHWJ")[0].checked=false;
		document.getElementsByName("ZRHTXMYHWJ")[1].checked=false;
		document.getElementById("disp3").style.display="none";	
	}
</script>


   <title>
      ¼����ܷ���˾ʵʩ��ͬ��Դ������Ŀ�����ñ�������
    </title>
 
  </head>
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
 onload="parseXmlOnLoad()">
  
  <table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="¼����˰����ҵ����˰����˰��������" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>
 

    <form name="Form1" method="post" action="/shenbao/basx022.dc">
 	<input name="actionType" type="hidden" id="actionType"></input>
       <table width="770" border="0" cellpadding="0" cellspacing="0" align="center" >
        <tr>
          <td valign="top" class="title">
            <br>
            <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
              <tr>
                <td class="1-td1">
                  ¼����ܷ���˾ʵʩ��ͬ��Դ������Ŀ�����ñ�������
                </td>
              </tr>
              <tr>
                <td class="1-td2">
                  
                  <br>                 
<div id="result"></div>
                  <br>
               
               
                  <table width="100%" border="0" align="center">
                    <tr align="center">  
                    	 
                               <td>
 												                     
                      <img src="<%=static_contextpath%>images/baocun1.jpg" onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="����" onclick="return doSave()" style="cursor:hand">
                      </td>
                      <td>
                        
                        
                        
                        	<img src="<%=static_contextpath%>images/b_scbab1.jpg" onmouseover="this.src='<%=static_contextpath%>images/b_scbab2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/b_scbab1.jpg'" alt="���ɱ�����" onclick="return doEditZb()" style="cursor:hand">
                      </td>
                      <td>
                        <img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="doReturn()" style="cursor:hand">
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
  
   	<tr><td valign="bottom" align="center"><br>
	<jsp:include page="../../include/bottom.jsp" flush="true">
	</jsp:include></td>
	</tr>
  </body>

</html>
