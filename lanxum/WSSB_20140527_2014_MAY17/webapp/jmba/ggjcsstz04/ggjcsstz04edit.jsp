<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml" %>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeTable"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.GGJCSSXMLX"%>

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
	GGJCSSXMLX tmpJmlx = (GGJCSSXMLX)jmlxList.get(i);
	out.println("jmlx["+i+"] = [\""+tmpJmlx.getGGJCSSXMLXDM()+"\",\""+tmpJmlx.getGGJCSSXMLXMC()+"\"];");
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
	var xslPath='/XSLTWEB/model/030004/XSLT/ggjcsstz04edit.xsl';

	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    
    insertJmlx();
    return true;
}


function insertJmlx()
{
  			var obj=document.forms[0].ggjcssxmlxdm;

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
		if( document.all("ggjcssxmlxdm").value == "") {
			alert("����������ʩ��Ŀ���Ͳ���Ϊ�գ�");
			return false;
		}
		if(document.all("dybrq").value == "") {
			alert("ȡ�õ�һ��������Ӫ�����ʱ�䲻��Ϊ�գ�");
			return false;
		}
		var wjmc = trim(document.all("wjmc").value);
		if(wjmc == "" ){
			alert("�ļ����Ʋ���Ϊ��!");
			document.forms[0].wjmc.focus();
			return false;
		}
		var wh = trim(document.all("wh").value);
		 if(wh == "" ){
			alert("�ĺŲ���Ϊ��!");
			document.forms[0].wh.focus();
			return false;
		 }
		
		var dybzlmc = trim(document.all("dybzlmc").value);
		 if(dybzlmc == "" ){
			alert("��һ���������Ʋ���Ϊ��!");
			document.forms[0].dybzlmc().focus();
			return false;
		 }
		
		
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

//�����걨����
function getSbsj(objForm)
{   
    var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
	var objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
	objNode.appendChild(objTemp);
	getChildren(objTemp,"xh");
	getChildren(objTemp,"wjmc");
	getChildren(objTemp,"wh");
	getChildren(objTemp,"ggjcssxmlxdm");
	
	getChildren(objTemp,"dybzlmc");	
	getChildren(objTemp,"dybrq");
	

	getChildren(objTemp,"jzqsnd");
	getChildren(objTemp,"jzzznd");
	getChildren(objTemp,"mzqsnd");
	getChildren(objTemp,"mzzznd");

	
	document.forms[0].ggjcssxmlxmc.value= document.forms[0].ggjcssxmlxdm.options[document.forms[0].ggjcssxmlxdm.selectedIndex].innerText;
	getChildren(objTemp,"ggjcssxmlxmc");
	
}
	function addYear() {
	    var t1 = trim(document.all("dybrq").value);
	
	    if(checkD1(document.all("dybrq"))) {
	    	var t=t1.substring(0,4);
	    	document.all("mzqsnd").value = t;
		    document.all("mzzznd").value = parseInt(t) + parseInt("2");
		    document.all("jzqsnd").value = parseInt(document.all("mzzznd").value) + parseInt("1");
		    document.all("jzzznd").value = parseInt(document.all("jzqsnd").value) + parseInt("2");
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
        }
        if (trim(document.all("jzqsnd").value)!="" && trim(document.all("jzzznd").value)!="")
        {
         qsnd=parseInt(document.all("jzqsnd").value);
         jznd=parseInt(document.all("jzzznd").value);
        if (jznd<=qsnd){
          alert("������ֹ��ȱ�����ڼ�����ʼ��ȣ�");
          return false;
        }
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
document.forms[0].jzqsnd.value="";
document.forms[0].jzzznd.value="";

document.forms[0].ggjcssxmlxdm.options[0].selected=true;
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
</script>


   <title>
      ¼����¹����ص���ֵĹ���������ʩ��ĿͶ�ʾ�Ӫ�����ü��ⱸ������
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
 

    <form name="Form1" method="post" action="/shenbao/ggjcsstz04.dc">
 	<input name="actionType" type="hidden" id="actionType"></input>
       <table width="770" border="0" cellpadding="0" cellspacing="0" align="center" >
        <tr>
          <td valign="top" class="title">
            <br>
            <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
              <tr>
                <td class="1-td1">
                  ¼����¹����ص���ֵĹ���������ʩ��ĿͶ�ʾ�Ӫ�����ü��ⱸ������
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
                  <br>
                  <!--
                  <table width="99%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>
                        <hr width="100%" size="1" style="color='#003399'">
                      </td>
                      <td width="99" align="center" class="black9">
                        <strong>
                          <font color="#0000FF">
                            ע �� �� ��
                          </font>
                        </strong>
                      </td>
                      <td>
                        <hr width="100%" size="1" style="color='#003399'">
                      </td>
                    </tr>
                  </table>
                  <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1"
                  bordercolor="#FFFFFF" class="black9">
                    <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                      <td height="47">
 					&nbsp;&nbsp;1����Ժ��ҵ���ܲ��Ż�����Ͷ�����ܲ��ŵĺ�׼�ļ����Ƽ��ĺţ�����˰�����в���ֽ�������ύ����˰����ء� <br>
					&nbsp;&nbsp;2ȡ�õ�һ����������֤�����ϣ���д���н��ʵ���Ʊ��������š���˰�����ṩֽ�ʲ��ϲ��ύ����˰����� <br>
					&nbsp;&nbsp;3ȡ�õ�һ��������Ӫ�����ʱ��Ϊ������ɸ��������˵�ѡ����Ӧʱ�����С���˰�����ṩֽ�ʲ��ϲ��ύ����˰����ء�
					<br>
					&nbsp;&nbsp;4��Ŀ�������˵��Ϊ��ѡ�����˰���ṩֽ�ʲ��ϲ��ύ����˰����ء� <br>
					&nbsp;&nbsp;5����˰�����Ҫ���͵��������ϣ��ɲ�����Ա�ֹ�¼��ϵͳ�� <br>
					&nbsp;&nbsp;6������ȣ�_______��D�D_______�ꡣ�����������Ŀȡ�õ�һ��������Ӫ����������˰����𣬵������ա�
					<br>
					&nbsp;&nbsp;7����������ȣ�_______�ꡪ��_______�ꡣ�����������Ŀȡ�õ�һ��������Ӫ����������˰��ȵĵ�����ʼ���������ա�
					<br>
					<br>
					<br>
					&nbsp;&nbsp;3��1�������б�����Ŀ����Ϊ�գ������ֹ���ύ�������롣 <br>
					&nbsp;&nbsp;3��2��ҵ������ҵ����˰�������������Ŀȡ�õ�һ��������Ӫ����������˰�����ʼ���㣬�Ե��������򳬹����߹涨�����ڣ���������ֻ����ѯʹ�ã���ֹ¼�롣
					<br>
					&nbsp;&nbsp;3��3����˰���걨����˰������ͨ�����ʱ�����л����񹲺͹���ҵ����˰����걨��A�ࣩ������5��˰���Ż���ϸ����29�п��ţ�������д���ݣ����򣬽�ֹ¼�롣</td>
					<br>
					<br>
                      </td>
                    </tr>
                  </table>
                  -->
                  <br>
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
