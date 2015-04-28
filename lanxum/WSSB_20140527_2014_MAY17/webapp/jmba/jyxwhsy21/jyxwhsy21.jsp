<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="java.util.*"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Jmfl"%>
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
    List jmflList = (List)session.getAttribute("GXJSFUDM");
%>
<html>
<head>
<title>经营性文化事业单位转制企业减免税备案事项</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/WdatePicker.js"></script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">

<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
<script language="JavaScript">
var jmfl= new Array(<%=jmflList.size()%>);
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
        
        for(int i=0;i<jmflList.size();i++)
        {
        	Jmfl tmpJmfl = (Jmfl)jmflList.get(i);
        	out.println("jmfl["+i+"] = [\""+tmpJmfl.getJmfldm()+"\",\""+tmpJmfl.getJmflmc()+"\"];");
        }        
%>
g_objSI.Container = "<%=containerName%>";
var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';

var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
function parseXmlOnLoad()
{
	var xslPath='/XSLTWEB/model/030019/XSLT/jyxwhsy21.xsl';
	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    insertJmfl();
    fudmload();
    return true;
    
}

function getPostXml(objForm)
{	
	var retstr;
	//基本数据
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//纳税人信息
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);

	applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc","fhwjmc","qsrq","ztdm","ztmc","jzrq","szdm","szmc","bsfsdm","bsfsmc"]);
	//申报数据
	getSbsj(objForm);	
	
	//去掉末尾自动添加的回车
	//alert(g_Doc.XMLHeader);
	//alert(g_Doc.XMLDoc.xml);
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
	
	if(element!=null)
	{   if(strTag=="WHSYDWLXDM"){
	   strValue=formString(document.forms[0].fudm.value);
	}else{
		strValue=formString(element.value);	
		}		
		var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
		node.appendChild(objCDATA);
	}	
}


//生成申报数据
function getSbsj(objForm)
{
	var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
	var objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
	objNode.appendChild(objTemp);
	getChildren(objTemp,"XH");
	getChildren(objTemp,"basqwsh");
	getChildren(objTemp,"JSJDM");
	getChildren(objTemp,"BAND");
	getChildren(objTemp,"WHSYDWLXDM");
	getChildren(objTemp,"JMSE");
	getChildren(objTemp,"LRR");
	getChildren(objTemp,"LRRQ");	
}



function checkall(){

  if(document.forms[0].fudm.value == ''){
    alert('文化事业单位类型代码不能为空！');
	return false;
  }  
  if(isNaN(document.forms[0].JMSE.value)){
alert("减免税额必须位数字！");
return false;
}else{
if(document.forms[0].JMSE.value.length>17||Trim(document.forms[0].JMSE.value).length<=0){
alert("减免税额不能为空最大长度最长为17位！");
return false;
}
}  
  
  return true;     
}

function Trim(str) {
return str.replace(/(^\s*)|(\s*$)/g, "");
}
function doSave(){

    if(!checkall()){
       return false;
    }
   
	var old  = document.forms[0].ywczlx.value;
	if (confirm("是否保存录入数据?"))
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





function fudmload()
{
  document.forms[0].fudm.value =document.forms[0].WHSYDWLXDM.value;
}

function insertJmfl()
{
	var yValue;
	var yName;
	var item
	var obj = document.forms[0].fudm;
	for(var i=0;i<jmfl.length;i++)
	{
		yValue=jmfl[i][0];
		yName=jmfl[i][1];
		item=new Option(yName,yValue);
		obj.options.add(item);						
	}
}

function checkJmse(){
		var jmse = document.forms[0].JMSE.value;
		if(jmse == ""){
			alert("减免税额不能为空！");
			document.forms[0].JMSE.focus();
			return false;
		}else{
			flg=0;
			zfgs = 0;
			cmp="0123456789.";
			for (var i=0;i<jmse.length;i++){  
				tst=jmse.substring(i,i+1);
				if (cmp.indexOf(tst)<0){
					flg++; 
				}else{
					if(tst == "."){
						zfgs++;
					}
				}
			} 
			if (flg!=0 || zfgs > 1){ 
				alert("减免税额格式不正确！"); 
				document.forms[0].JMSE.focus();
				return false; 
			}
		}
		formate(document.forms[0].JMSE);
		//var jmse1 = jmse;
		//jmse = round2(jmse1);
		//document.all("JMSE").value = jmse;
		return true;
}

function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
} 

function doEditZb(){
	
		if(checkall()==true){

	
	var old  = document.forms[0].ywczlx.value;
	if (confirm("是否生成备案表?"))
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
    	<jsp:include page="../../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="经营性文化事业单位转制企业减免税备案事项" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/lrjyxwhfwsy.dc">
	<input name="actionType" type="hidden" id="actionType">
	
<table  width="770"  border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
<td valign="top" class="title"> <br>

      <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
        <tr>
          <td class="1-td1" >经营性文化事业单位转制企业减免税备案事项</td>
        </tr>
        <tr>
          <td class="1-td2">
              <div id="result"></div>
            
                  <table width="100%" border="0" align="center">
                    <tr align="center">  
                    	 
                    	<td >
                    	<img onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/baocun2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/baocun1.jpg" name="shanchu11" style="cursor:hand" border="0" id="baocun"  alt="保存" 

onclick="javascript:return doSave();">
                      </td>   
                    	
                    	 
                      <td >
                       <img onMouseOver="MM_swapImage('scbab','','<%=static_contextpath%>images/b_scbab2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/b_scbab1.jpg" name="shanchu11" width="95" height="22" border="0" id="scbab" style="cursor:hand" alt="生成备案表" 

onclick="doEditZb()">
                     <!--   <img src="<%=static_contextpath%>images/tijiao1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/tijiao2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/tijiao1.jpg'" alt="提交" onClick="doCommit();" style="cursor:hand">-->
                      	
                      </td>           
                    	
                      <td >
                        <img onclick="doReturn()" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/fanhui2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" style="cursor:hand" value="退出" id="tc1" src="<%=static_contextpath%>images/fanhui1.jpg">
                      </td>
                    </tr>
                  </table>
            <br>
           <br> </td>
					</tr>
				</table>
				
				<input type="hidden" name="rdnd" value="2009">
				<div id=hiddenArea></div>
			</td>
	</tr>
</table>
</form>
 </td>
</tr>
<tr><td valign="bottom" align="center">
<br>
<%@ include file="../../../include/bottom.jsp" %>
 </td>
</tr>
</table>
</body>
</html>

