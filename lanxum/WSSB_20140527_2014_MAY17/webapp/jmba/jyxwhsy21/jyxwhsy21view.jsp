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
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/date.js"></script>
<script language="JavaScript" type="text/javascript" src="js1/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript" src="js/qysdsnew.js"></script>
<script language="JavaScript" type="text/javascript" src="js/calculate.js"></script>
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
function parseXmlOnLoad()
{
	var xslPath='/XSLTWEB/model/030019/XSLT/jyxwhsy21view.xsl';
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

	applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc"	,"fhwjmc","qsrq","ztdm","ztmc","jzrq","szdm","szmc","bsfsdm","bsfsmc"]);
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
	{ 
		strValue=formString(element.value);			
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
	getChildren(objTemp,"BASQWSH");
	getChildren(objTemp,"JSJDM");
	getChildren(objTemp,"BAND");
	getChildren(objTemp,"WHSYDWLXDM");

	getChildren(objTemp,"JMSE");
	getChildren(objTemp,"LRR");
	getChildren(objTemp,"LRRQ");	
}

function doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit)
{
	var strToPost;
	initXMLObject();
	
	if (!objForm)
	{
		return false;
	}
	if (!document.all("XML_Form"))
	{
		document.body.insertAdjacentHTML("BeforeEnd",IntHtml.HTMLFormString);
	}
	else
	{
		document.all("XML_Form").strSignData.value = "";
		document.all("XML_Form").actionType.value = "";
		document.all("XML_Form").appType.value = "";
		document.all("XML_Form").strXMLData.value = "";
		document.all("XML_Form").REQUEST_TOKEN.value = "";
	}
	var strToPost = "";
	var strtmp = "";
	if (xmldata != "")
	{
		strtmp = xmldata;
	}
	else
	{
		strtmp = getPostXml(objForm);
	}
	strToPost = strtmp.replace(/\r\n/g,"");
	var sSignData="";
	var strAppType="";
	if(isSign)
	{
		strAppType = gGetGlobalAppType();
		sSignData = gSign(strToPost);
		if(sSignData == -1)
		{
			return false;
		}
	}
	document.all("XML_Form").strXMLData.value = strToPost;
	document.all("XML_Form").actionType.value = aType;
	document.all("XML_Form").strSignData.value = sSignData;
	document.all("XML_Form").appType.value = strAppType;
	//alert(document.all("XML_Form").strXMLData.value);
	//document.all("XML_Form").REQUEST_TOKEN.value = strREQUEST_TOKEN;

	document.all("XML_Form").action = objForm.action;
	document.all("XML_Form").target = objForm.target;
	//Submit Form/////////////////////////////////
	if (ifsubmit)
	{
		//alert('保存提交！');
		document.all("XML_Form").submit();
	}
	return true;
}

function checkall(){
  if(document.forms[0].fudm.value == ''){
    alert('文化事业单位类型代码不能为空！');
	return false;
  }
  if(document.forms[0].JMSE.value == ''){
    alert('减免税额不能为空！');
    document.forms[0].JMSE.focus();
	return false;
  }else{
    if (checkJmse())
      return true;
    else
      return false;
  }        
}


function doSave(){
    if(!checkall()){
       return false;
    }
    document.forms[0].WHSYDWLXDM.value = document.forms[0].fudm.value
	var old  = document.forms[0].ywczlx.value;
	//alert(old);
	if (confirm(confirmSave))
	{   //alert("1");
		 if (document.forms[0].ywczlx.value == 0)
		{  //alert("2");
			document.forms[0].ywczlx.value = 1;
		}
		else if (document.forms[0].ywczlx.value == 1)
		{  //alert("3");
			document.forms[0].ywczlx.value = 2;
		}
		document.forms[0].actionType.value="Save";
			if (g_objSI.Container != '')
			{  //alert("4");
				if (!doSubmitXML(document.forms[0],"Save",true,"",true))
				{  //alert("5");
					 document.forms[0].ywczlx.value = old;
					return false;
				}
			}
			else
			{  //alert("6");
			   if(!doSubmitXML(document.forms[0],"Save",false,"",true))
				{  //alert("7");
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

function doCommit(){
    if(!checkall()){
       return false;
    }
    document.forms[0].WHSYDWLXDM.value = document.forms[0].fudm.value
	var old  = document.forms[0].ywczlx.value;
	//alert(old);
	if (confirm(confirmSave))
	{   //alert("1");
		 if (document.forms[0].ywczlx.value == 0)
		{  //alert("2");
			document.forms[0].ywczlx.value = 1;
		}
		else if (document.forms[0].ywczlx.value == 1)
		{  //alert("3");
			document.forms[0].ywczlx.value = 2;
		}
		document.forms[0].actionType.value="Commit";
			if (g_objSI.Container != '')
			{  //alert("4");
				if (!doSubmitXML(document.forms[0],"Commit",true,"",true))
				{  //alert("5");
					 document.forms[0].ywczlx.value = old;
					return false;
				}
			}
			else
			{  //alert("6");
			   if(!doSubmitXML(document.forms[0],"Commit",false,"",true))
				{  //alert("7");
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

function clickzt(lx,val)
{
  if (lx == 1) {
    document.forms[0].SFYQYMD.value =val;
  }
  if (lx == 2) {
    document.forms[0].SFYZZFAPFH.value =val;
  }
  if (lx == 3) {
    document.forms[0].SFBLGSYYZZ.value =val;
  }
  if (lx == 4) {
    document.forms[0].SFYZXSYDWZM.value =val;
  }      
  if (lx == 5) {
    document.forms[0].SFCJSHBX.value =val;
  }  
  if (lx == 6) {
    document.forms[0].SFYBGZBJG.value =val;
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
		var jmse = document.all("JMSE").value;
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
		var jmse1 = jmse;
		jmse = round2(jmse1);
		document.all("JMSE").value = jmse;
		return true;
}

function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
} 
function doViewZb()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="ViewZb";
	document.forms[0].submit();
}
</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="../../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="录入经营性文化事业单位转制企业减免税备案事项" />
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
                       <img onMouseOver="MM_swapImage('ckbab','','<%=static_contextpath%>images/b_ckbab2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/b_ckbab1.jpg" name="shanchu11" width="95" height="22" border="0" id="ckbab"  alt="查看备案表" 

onclick="doViewZb()">
                      	<!--<img src="<%=static_contextpath%>images/qc-u2.jpg" name="shanchu11" width="79" height="22" border="0" id="shanchu11" alt="提交" 

onclick="javascript:return doClear();">-->
                      </td>                  	
                      <td >
                       <img onclick="doReturn()" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/fanhui2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/fanhui1.jpg"
                        width="95" height="22">
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

