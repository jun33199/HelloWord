<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml" %>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeTable"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.YFFYLY"%>

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
	YFFYLY tmpJmlx = (YFFYLY)jmlxList.get(i);
	out.println("jmlx["+i+"] = [\""+tmpJmlx.getYFFYLYDM()+"\",\""+tmpJmlx.getYFFYLYMC()+"\"];");
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
	var xslPath='/XSLTWEB/model/030002/XSLT/yjkffy02edit.xsl';

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
  			var obj=document.forms[0].yffylydm;

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
	//基本数据
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//纳税人信息
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);

	applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc","ztdm","ztmc","szdm","szmc","lrrq"]);
	//申报数据
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
	<%/*效验页面元素*/%>
	function checkYm(){
		var yffyly = document.all("yffylydm").value;
		if(yffyly == "" ){
			alert("项目种类不能为空!");
			document.forms[0].yffylydm.focus();
			return false;
		}
		var xmmc = document.all("xmmc").value;
		if(xmmc == "" ){
			alert("项目名称不能为空!");
			document.forms[0].xmmc.focus();
			return false;
		}
		if(!formatCurrency10(document.all("jjkcje"),0)){
			document.forms[0].jjkcje.focus();
			return false;
		}
		var wxzcje = document.all("wxzcje").value;
		var dqkcje = document.all("dqkcje").value;
		if (wxzcje=="" && dqkcje==""){
			alert("研发费用金额至少选择其中一项填写!");
			document.forms[0].wxzcje.focus();
			return false;
		}
		if(!formatCurrency10(document.all("wxzcje"),1)){
			document.forms[0].wxzcje.focus();
			return false;
		}
		if(!formatCurrency10(document.all("dqkcje"),1)){
			document.forms[0].dqkcje.focus();
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
  alert("输入内容不能大于"+maxLength+"个字符 ！");
  return   false;
  }  
return true; 
  }  
	
// 将字符串格式化为货币格式
// par为0，验证空值
// par为1，不验证空值
function formatCurrency10(obj,par)
{
if (par==0 ){
  if (obj==null || obj.value==null || obj.value==""){
     alert("输入值为空！");
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
	
	var mcobj=document.all(strTag+"mc");
	if (strValue=="0"){
	  mcobj.value="是";
	}else{
    	mcobj.value="否";
	}
	
	return strValue;
}

//生成申报数据
function getSbsj(objForm)
{   
    var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
	var objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
	objNode.appendChild(objTemp);
	getChildren(objTemp,"xh");
	getChildren(objTemp,"xmmc");
	getChildren(objTemp,"dqkcje");
	getChildren(objTemp,"yffylydm");
	
	getChildren(objTemp,"wxzcje");	
	getChildren(objTemp,"jjkcje");
	
	
	document.forms[0].yffylymc.value= document.forms[0].yffylydm.options[document.forms[0].yffylydm.selectedIndex].innerText;
	getChildren(objTemp,"yffylymc");
	
	
	
}
function doSave(value){
	
		if(!checkYm() ){
		return false;
   }	
   if(!confirm("是否保存录入数据?"))
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
	if(!confirm("是否生成备案表?"))
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
document.forms[0].xmmc.value="";
document.forms[0].dqkcje.value="";
document.forms[0].jjkcje.value="";
document.forms[0].wxzcje.value="";

document.forms[0].yffylydm.options[0].selected=true;
}
 
 function getRadioValue(obj)
{
		var objLength = obj.length;
		var k=0;
		for (i=0;i<objLength;i++)
		{
			if(obj[i].checked == true)
			{
				k=i;//寻找radio数组中被选中的值的位置
				return obj[k].value;//给对象赋值
			}		
		}
		return "";
     
}
</script>


   <title>
      研究开发费用加计扣除备案事项
    </title>
 
  </head>
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
 onload="parseXmlOnLoad()">
  
  <table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="录入纳税人企业所得税减免税备案申请" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>
 

    <form name="Form1" method="post" action="/shenbao/yjkffy02.dc">
 	<input name="actionType" type="hidden" id="actionType"></input>
       <table width="770" border="0" cellpadding="0" cellspacing="0" align="center" >
        <tr>
          <td valign="top" class="title">
            <br>
            <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
              <tr>
                <td class="1-td1">
                  研究开发费用加计扣除备案事项
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
 												                     
                      <img src="<%=static_contextpath%>images/baocun1.jpg" onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="保存" onclick="return doSave()" style="cursor:hand">
                      </td>
                      <td>
                        
                        
                        
                        	<img src="<%=static_contextpath%>images/b_scbab1.jpg" onmouseover="this.src='<%=static_contextpath%>images/b_scbab2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/b_scbab1.jpg'" alt="生成备案表" onclick="return doEditZb()" style="cursor:hand">
                      </td>
                      <td>
                        <img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturn()" style="cursor:hand">
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
                            注 意 事 项
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
                     &nbsp;&nbsp; 1.项目名称：按照不同开发项目进行填列，该项为必录项，由纳税人在提交申请时录入系；
					<br>
					&nbsp;&nbsp;2.是否有自主、委托、合作研究开发项目计划书和研究开发费用预算：选择 “是”或 “否”，		该项为必选项，由纳税人在提交申请时录入系统；
					<br>
					&nbsp;&nbsp;3.是否有自主、委托、合作研究开发专门机构或项目组的编制情况和专业人员名单：选择 “		是”或 “否”，该项为必选项，由纳税人在提交申请时录入系统；
					<br>
					&nbsp;&nbsp;4.是否有自主、委托、合作研究开发项目当年研究开发费用发生情况归集表: 选择 “是”或 “		否”，该项为必选项，由纳税人在提交申请时录入系统；
					<br>
					&nbsp;&nbsp;5.是否有企业总经理办公会或董事会关于自主、委托、合作研究开发项目立项的决议文件:		选择 “是”或 “否”，该项为必选项，由纳税人在提交申请时录入系统；
					<br>
					&nbsp;&nbsp;6.是否有委托、合作研究开发项目的合同或协议: 选择 “是”或 “否”，该项为必选项，由纳		税人在提交申请时录入系统；
					<br>
					&nbsp;&nbsp;7.是否有研究开发项目的效用情况说明、研究成果报告等资料: 选择 “是”或 “否”，该项为		必选项，由纳税人在提交申请时录入系统；
					<br>
					&nbsp;&nbsp;8.中介机构鉴证报告: 选择 “有”或 “无”，该项为必选项，该项无论填写有无，均不作控制		，由纳税人在提交申请时录入系统；
					<br>
					&nbsp;&nbsp;9.研发费用金额（单位：元）：分为当期扣除金额和形成无形资产金额两项，该项为手工录		入项，由纳税人至少选择其中一项填写，在提交申请时录入系统；
					<br>
					&nbsp;&nbsp;10.加计扣除额（单位：元）：手工录入项，由纳税人在提交申请时录入系统； 
					<br>
					&nbsp;&nbsp;11.主管税务机关要求报送的其他资料：手工录入系统，该录入项为非必录项。
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
