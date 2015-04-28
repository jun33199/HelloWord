<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml" %>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeTable"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.ZYZHLYZL"%>

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
	ZYZHLYZL tmpJmlx = (ZYZHLYZL)jmlxList.get(i);
	out.println("jmlx["+i+"] = [\""+tmpJmlx.getZYZHLYZLDM()+"\",\""+tmpJmlx.getZYZHLYZLMC()+"\"];");
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
	var xslPath='/XSLTWEB/model/030001/XSLT/zyzhly01edit.xsl';

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
  			var obj=document.forms[0].zyzhlyzldm;

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
		 var zyzhlyzl = document.all("zyzhlyzldm").value;
		 if(zyzhlyzl == "" ){
			alert("��Դ�ۺ��������಻��Ϊ��!");
			document.forms[0].zyzhlyzldm.focus();
			return false;
		 }
		 
		 var zsbh = trim(document.all("zsbh").value);
		 if(zsbh == "" ){
			alert("��Դ�ۺ������϶�֤���Ų���Ϊ��!");
			document.forms[0].zsbh.focus();
			return false;
		 }
		 
		 var zsyxksrq = document.all("zsyxksrq").value;
		 if(zsyxksrq == "" ){
			alert("֤����Ч����ʼ���ڲ���Ϊ��!");
			document.forms[0].zsyxksrq.focus();
			return false;
		 }
		 var zsyxjzrq = document.all("zsyxjzrq").value;
		 if(zsyxjzrq == "" ){
			alert("֤����Ч����ֹ���ڲ���Ϊ��!");
			document.forms[0].zsyxjzrq.focus();
			return false;
		 }
		 var qdsr = trim(document.all("qdsr").value);
		if(qdsr == ""){
			alert("�ۺ�������Դ�������Ϲ��Ҳ�ҵ���߹涨�Ĳ�Ʒ��ȡ�õ����벻��Ϊ�գ�");
			document.forms[0].qdsr.focus();
			return false;
		}else{
			if (!formatCurrency10(document.all("qdsr"),1)){ 
				alert("�ۺ�������Դ�������Ϲ��Ҳ�ҵ���߹涨�Ĳ�Ʒ��ȡ�õ������ʽ����ȷ��"); 
				document.forms[0].qdsr.focus();
				return false; 
			}
		}
		 return true;
		
	}
	<%/*Ч������*/%>
	function checkDate(){
		var zsyxksrq = document.forms[0].zsyxksrq.value;
		var zsyxjzrq = document.forms[0].zsyxjzrq.value;
		d1Arr=zsyxksrq.split('-');
		d2Arr=zsyxjzrq.split('-');
		v1=new Date(d1Arr[0],d1Arr[1],d1Arr[2]);
		v2=new Date(d2Arr[0],d2Arr[1],d2Arr[2]);
		if(v1>v2){
			alert("��ʼ���ڲ���������ֹ����");
			return false;
		}
		return true;
	}
	<%/*����ƶ�*/%>
	function change(){
		var qdsr = document.all("qdsr").value;
		if(qdsr == ""){
			alert("�ۺ�������Դ�������Ϲ��Ҳ�ҵ���߹涨�Ĳ�Ʒ��ȡ�õ����벻��Ϊ�գ�");
			document.forms[0].qdsr.focus();
			return false;
		}else{
			
			flg=0;
			zfgs = 0;
			cmp="0123456789.";
			for (var i=0;i<qdsr.length;i++){  
				tst=qdsr.substring(i,i+1);
				if (cmp.indexOf(tst)<0){
					flg++; 
				}else{
					if(tst == "."){
						zfgs++;
					}
				}
			} 
			if (flg!=0 || zfgs > 1){ 
				alert("�ۺ�������Դ�������Ϲ��Ҳ�ҵ���߹涨�Ĳ�Ʒ��ȡ�õ������ʽ����ȷ��"); 
				document.forms[0].qdsr.focus();
				return false; 
			}
		}
		formatCurrency(document.forms[0].qdsr,0);
		var qdsr1 = qdsr;
		qdsr = round2(qdsr1);
		document.all("qdsr").value = qdsr;
		var jjsr = round2(qdsr*0.1);
		document.all("jjsr").value = jjsr;
	}
function formatCurrency10(obj,par)
{
if (par==0 ){
  if (obj==null || obj.value==null || obj.value==""){
     alert("����ֵΪ�գ�");
     return false;
  }
}
return checkvalue(obj,0);//&&formatCurrency(obj,0)
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
	getChildren(objTemp,"zyzhlyzldm");

	getChildren(objTemp,"zsyxksrq");
	getChildren(objTemp,"zsyxjzrq");
	getChildren(objTemp,"qdsr");
	getChildren(objTemp,"jjsr");
	getChildren(objTemp,"zsbh");
	
	document.forms[0].zyzhlyzlmc.value= document.forms[0].zyzhlyzldm.options[document.forms[0].zyzhlyzldm.selectedIndex].innerText;
	getChildren(objTemp,"zyzhlyzlmc");
	
	
	
}
function doSave(value){
	
	if(!checkYm() || !checkDate()){
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
document.forms[0].wh.value="";
document.forms[0].qdsr.value="";
document.forms[0].jjsr.value="";
document.forms[0].zsbh.value="";
document.forms[0].zyzhlyzldm.options[0].selected=true;
document.forms[0].zsyxksrq.value="";
document.forms[0].zsyxjzrq.value="";
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
      ��Դ�ۺ�������ҵ����Ŀ�����������ҵ����˰��������
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
 

    <form name="Form1" method="post" action="/shenbao/zyzhly01.dc">
 	<input name="actionType" type="hidden" id="actionType"></input>
       <table width="770" border="0" cellpadding="0" cellspacing="0" align="center" >
        <tr>
          <td valign="top" class="title">
            <br>
            <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
              <tr>
                <td class="1-td1">
                  ¼����Դ�ۺ�������ҵ����Ŀ�����������ҵ����˰��������
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
                        &nbsp;&nbsp;1. ��Դ�ۺ������϶�֤���ţ��ֹ�¼��֤����;
                        <br>
                        &nbsp;&nbsp;2. ֤�����Ч�ڣ�ͨ�����������ʽѡ��֤�����Ч����ֹ����;
                        <br>
                        &nbsp;&nbsp;3. �����з�չ�͸ĸ�ίԱ�ṫ����Դ�ۺ�������ҵ�϶���ҵ�������ļ����ֱ�¼���ļ����Ƽ��ļ���,������������Դ�ۺ�������������ݷ��д���д����Դ�ۺ��������ࡱ�����дο����������ӣ�˰�����һ��Ҫ�ϸ����ļ��е���Ŀ�ѹأ�;
                        <br>
                        &nbsp;&nbsp;4. �Ƿ��ύ��ҵʵ����Դ�ۺ�����������ֱ������Դ�ۺ����������������ѡ�� ���ǡ��� ����;
                        <br>
                        &nbsp;&nbsp;5. �ۺ�������Դ�������Ϲ��Ҳ�ҵ���߹涨�Ĳ�Ʒ��ȡ�õ����루��λ��Ԫ�����ֹ�¼����;
                        <br>
                        &nbsp;&nbsp;6. ����������λ��Ԫ�����������Զ����ɣ���������=���ۺ�������Դ�������Ϲ��Ҳ�ҵ���߹涨�Ĳ�Ʒ��ȡ�õ����롱*10%
                        ;
                        <br>
                        <br>
                        &nbsp;&nbsp;3.1���������ĿΪ�������δ��д������˰���ύ���롣
                        <br>
                        &nbsp;&nbsp;3.2 ����ѡ���Ƿ�������У����κ�һ��ѡ���������˰���ύ���롣
                        <br>
                        &nbsp;&nbsp;3.3 ��˰��ֻ���ύ�������벢��˰�������˳ɹ��󷽿ɿ��š��л����񹲺͹���ҵ����˰�����˰�걨��A�ࣩ����������˰���Ż���ϸ����7�У������ڻ������ʱ��д���ݣ����򣬶�Ӧ�д�ϵͳĬ��Ϊ������༭״̬��
                        <br>
                        <br>
                        &nbsp;&nbsp;1. ��˽����������һ��ѡ��Ϊ���ǡ��ſ�ȷ������ҵ�ۺ�������Դ���������Ϲ��Ҳ�ҵ���߹涨�Ĳ�Ʒ��ȡ�õ����롱����������˳ɹ�����������Ϊʵ���Բ����ϣ��ܾ��ñ�����Ŀ�����롣��
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
