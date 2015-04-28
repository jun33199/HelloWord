<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml"%>
<%@page import="java.util.List"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.JMBASXDM"%>

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
			List jmlxList = (List) session.getAttribute("LS");
%>
  
<html>
<head>
<title>减免税备案表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
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

<link href="css/jmba.css" rel="stylesheet"
	type="text/css">

<style>
input {
    font-size: 9pt;
    text-align: right;
}



</style>
<script language="JavaScript">

var jmlx= new Array(<%=jmlxList.size()%>);

<%
//for(int i=0;i<jmlxList.size();i++)
//{
//	JMBASXDM tmpJmlx = (JMBASXDM)jmlxList.get(i);
//	out.println("jmlx["+i+"] = [\""+tmpJmlx.getJMBASXDM()+"\",\""+tmpJmlx.getJMBASXMC()+"\"];");
//}

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
	var xslPath='/XSLTWEB/model/030000/XSLT/jmbaspb.xsl';

	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    
	testBasx22();
    //insertJmlx();
    return true;
}
//查看是否是备案事项22
function testBasx22()
{
	var basxdm = document.getElementById("jmbasxdm").value;
	if(basxdm=="0220")
	{
		document.getElementById("div1left").style.display="inline";
		document.getElementById("div2left").style.display="inline";
		document.getElementById("div3left").style.display="inline";
		document.getElementById("div1right").style.display="inline";
		document.getElementById("div2right").style.display="inline";
		document.getElementById("div3right").style.display="inline";
	}
}
<%/*生成备案事项名称*/%>
function makeSxmc(obj)
{
				var selectObj=document.getElementById("jmbasxdm");
			    var selectOptionText= selectObj.options[selectObj.selectedIndex].innerText;
			    document.forms[0].jmbasxmc.value=selectOptionText;
}
function insertJmlx()
{
  var obj=document.getElementById("jszrlxdiv");
  var oinnerHTML="";
  //add
  if (document.forms[0].basqwsh.value==null || document.forms[0].basqwsh.value==""){
    oinnerHTML="<select name=\"jmbasxdm\"  onchange=\"makeSxmc(this)\">";
        for(var i=0;i<jmlx.length;i++)
        {
            oinnerHTML=oinnerHTML+"<option value=\""+jmlx[i][0]+"\" >"+jmlx[i][1]+"</option>";
        }
        
	oinnerHTML=oinnerHTML+"</select>";
obj.innerHTML=oinnerHTML;
    document.forms[0].jmbasxmc.value=document.forms[0].jmbasxdm.options[0].innerText;

  }
	}
	
  //回车事件
  function onKeySubmit()
    	{
        	if(window.event.keyCode==13){
            	addzl();
   			}
    	}
    	
    	
  //初始化
 function addzl(){
	var zlmc=document.getElementById("zlmc").value;
	if(zlmc==""){
		alert("资料名称不能为空!");
		return false;
	}else
	{
	    if (!checkLength(document.all("zlmc"))){
			  return false;
			}
	    
	    var new_row=Table1.insertRow(Table1.rows.length);  
		var row_index=new_row.rowIndex;  
//alert(row_index);
	    new_row.setAttribute("id", "row"+row_index);   
	    var new_col=new_row.insertCell(0);
	    new_col.className="2-td2-left";
	    new_col.setAttribute("id",row_index);
	    new_col.innerHTML=zlmc+"<input type=\"hidden\" name=\"zlqd\" id=\"zlqd\" value='"+zlmc+"'></input>";  
	    new_col.innerHTML=new_col.innerHTML+"<input type=\"hidden\" name=\"sfkysc\" id=\"sfkysc\" value='0'></input>";  
	    var new_col1=new_row.insertCell(1);
	    new_col1.className="2-td2-center"; 
	    //new_col1.innerHTML="<a href=\"#\" id=\"delIndex\" onclick=\"delete_row(this);return false;\">删除此行</a>";  
	    new_col1.innerHTML="<input id=\"delIndex\" type=\"button\"	value=\" 删除  \" onclick=\"delete_row(this);return false;\" />";
	    document.getElementById("zlmc").value="";
	}
   	
 }
    
	//删除
function delete_row(obj)   
 {  
 if(confirm("是否删除此行数据!")){
	var delRows = Table1.rows.length-1;
//alert(delRows+" ds="+document.forms[0].delIndex.length);			
var idel=1;
		for(var i=0;i<delRows;i++)
		{
		if (document.forms[0].sfkysc[i].value=='1'){
		   idel=i;
		   continue;
		}
//alert("idel="+idel+"   i="+i);
if (document.forms[0].delIndex.length==null || document.forms[0].delIndex.length==1){
   Table1.deleteRow(i+1);
return;
}
if(document.forms[0].delIndex[i-idel-1] == obj){
       				Table1.deleteRow(i+1);
return;
                }
}
}     
 }
 

 function getPostXml(objForm)
{	var retstr;
	//基本数据
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//纳税人信息
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);

	applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc","bajmse","bajmbl"	,"fhwjmc","qsrq","ztdm","ztmc","jzrq","szdm","szmc","bsfsdm","bsfsmc","lrrq"]);
	//申报数据
	getSbsj(objForm);	
	
	//去掉末尾自动添加的回车
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
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
		strValue=formString(element.value);		
		var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
		node.appendChild(objCDATA);
	}	
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
  alert("减免税政策执行情况  应不小于1000个汉字 ！");
  return   false;
  }  
return true; 
  }  


//生成申报数据
function getSbsj(objForm)
{   
    var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
//alert(Table1.rows.length);
	var itemsize = Table1.rows.length-1;//
        if(itemsize == 1)
	{

	var objTemp = g_Doc.XMLDoc.createElement("bajlzlqd");
	objNode.appendChild(objTemp);
	var node=g_Doc.XMLDoc.createElement("zlqd");
	objTemp.appendChild(node);
	var strValue="";
		strValue=formString(document.forms[0].zlqd.value);		
		var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
		node.appendChild(objCDATA);

	 node=g_Doc.XMLDoc.createElement("sfkysc");
	objTemp.appendChild(node);
	 strValue="";
		strValue=formString(document.forms[0].sfkysc.value);		
		 objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
		node.appendChild(objCDATA);
		
	
}
        else if(itemsize > 1)
	{
			for(var i=0;i<itemsize;i++)
			{
    
	var objTemp = g_Doc.XMLDoc.createElement("bajlzlqd");
	objNode.appendChild(objTemp);
	var node=g_Doc.XMLDoc.createElement("zlqd");
	objTemp.appendChild(node);
	var strValue="";
		strValue=formString(document.forms[0].zlqd[i].value);		
		var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
		node.appendChild(objCDATA);

	 node=g_Doc.XMLDoc.createElement("sfkysc");
	objTemp.appendChild(node);
	 strValue="";
		strValue=formString(document.forms[0].sfkysc[i].value);		
		 objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
		node.appendChild(objCDATA);
	
	     }
	}
}
function doExit()
{
	document.forms[0].action = "quit.do";
	document.forms[0].submit();
} 
	<%/*效验页面元素*/%>
	function checkYm(){	
			var qsrq = document.forms[0].qsrq.value;
			var jzrq = document.forms[0].jzrq.value;
			if(qsrq!="" && jzrq!=""){
				d1Arr=qsrq.split('-');
				d2Arr=jzrq.split('-');
				v1=new Date(d1Arr[0],d1Arr[1],d1Arr[2]);
				v2=new Date(d2Arr[0],d2Arr[1],d2Arr[2]);
				if(v1>v2){
					alert("起始日期不能晚于截止日期");
					return false;
				}
			}else{
					alert("起始日期和截止日期不能为空！");
					return false;
			}
			
			if (trim(document.all("fhwjmc").value)==""){
					alert("减免税政策执行情况不能为空！");
					return false;
			
			}
		if (!checkLength(document.all("fhwjmc"),2000)){
			//alert("减免税政策执行情况长度不能大于1000!");
			document.forms[0].fhwjmc.focus();
			return false;
		
		}
			var bajmbl = trim(document.forms[0].bajmbl.value);
			var bajmse = trim(document.forms[0].bajmse.value);
			if (bajmbl!="" && bajmse!=""){
			alert("税额和比例只能录入一个！");
			return false;
			}
			//if (bajmbl=="" && bajmse==""){
			//alert("税额和比例必须录入一个！");
			//return false;
			//}
			if (bajmbl!=""){
		 if (!checkvalue(document.all("bajmbl"),2)){
		 //alert("请录入数字");
		 //document.forms[0].bajmbl.focus();
		 return false;
		 }
		 }
			if (bajmse!=""  ){
		 if (!checkvalue(document.all("bajmse"),0)){
//		 alert("请录入数字");
	//	 document.forms[0].bajmse.focus();
		 return false;
		 }
		 }
		 return true;

		
	}
  function   checkLength(obj)   
  {
  var evalue=obj.value;
  evalue=evalue.replace(/[^\x00-\xff]/g,'**');
//alert(evalue.length+"  "+obj.maxLength);
  if(evalue.length>obj.maxLength){
  alert("输入内容不能大于"+obj.maxLength+"个字符 ！");
  return   false;
  }  
return true; 
  }  
  
function canCommit(sfFields){
  return true;
}
function doCommit(){
	
		if(!checkYm() ){
		return false;
   }	
//	var sfFields=[];
//		if (!canCommit(sfFields)){
//		  return false;
//		}
    if(confirm("是否提交此条减免税备案申请？"))
    {

    if(confirm("是否生成打印文书？"))
    {
      return doSaveSubmit('1');
    }
	var old  = document.forms[0].ywczlx.value;
		
		document.forms[0].actionType.value="Commit";
		
		if (g_objSI.Container != '')
		{ 
			if (!doSubmitXML(document.forms[0],"Commit",true,"",true))
			{  
					document.forms[0].ywczlx.value = old;
				return false;
			}
		}
		else
		{  
			if(!doSubmitXML(document.forms[0],"Commit",false,"",true))
			{  
				document.forms[0].ywczlx.value = old;
				return false;
			}			   
		}
		return true;
	}
	return false;
}
  
function doSave(value){
		if(!checkYm() ){
		return false;
   }	
    if(confirm("是否保存此条减免税备案申请？"))
    {
      return doSaveSubmit(value);
    }
    return false;
}  
  
function doSaveSubmit(value){
	
	var old  = document.forms[0].ywczlx.value;
		
		document.forms[0].actionType.value="Save"+value;
		
		if (g_objSI.Container != '')
		{  //alert("4");
			if (!doSubmitXML(document.forms[0],"Save"+value,true,"",true))
			{  //alert("5");
					document.forms[0].ywczlx.value = old;
				return false;
			}
		}
		else
		{  //alert("6");
			if(!doSubmitXML(document.forms[0],"Save"+value,false,"",true))
			{  //alert("7");
				document.forms[0].ywczlx.value = old;
				return false;
			}			   
		}
		return true;
		
		
}
function doExit()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
}  
 
function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="ReturnEdit";
	document.forms[0].submit();
} 
</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" colspan="2" valign="top"><jsp:include
			page="../../../include/SbHeader.jsp" flush="true">
			<jsp:param name="name" value="减免税备案表" />
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
		</jsp:include> <html:errors />

		<form name="form1" method="POST" action="/shenbao/jmbaz.dc"><input
			name="actionType" type="hidden" id="actionType"> <input
			name="saveType" type="hidden" id="saveType">


		<TABLE width="900" border='0' cellpadding='0' cellspacing='0'
			align='center' class='black9'>
			<tr>
				<td valign="top">
				<table align="center" cellspacing="0" class="table-99">
					<tr>
						<td class="1-td1">减免税备案表</td>
					</tr>
					<tr>
						<td class="1-td2"><br>
						<div id="result"></div>
						</td>
					</tr>
					<tr>
						<br>
						<table width="100%" border="0" align="center">
							<tr align="center">

								<td width="9%">
								
								<!--img src="<%=static_contextpath%>images/b_lrbaxx1.jpg" onmouseover="this.src='<%=static_contextpath%>images/b_lrbaxx2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/b_lrbaxx1.jpg'" alt="录入备案信息" onclick="doSave('2')" style="cursor:hand"-->
								 <img src="<%=static_contextpath%>images/baocun1.jpg" onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="保存" onclick="return doSave('2')" style="cursor:hand">
								</td>
								<td width="9%">
								<img src="<%=static_contextpath%>images/tijiao1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tijiao2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tijiao1.jpg'" alt="提交" onclick="return doCommit()" style="cursor:hand">
								</td>
								<td width="9%">
								
								
									<!--img src="<%=static_contextpath%>images/b_scdyws1.jpg" onmouseover="this.src='<%=static_contextpath%>images/b_scdyws2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/b_scdyws1.jpg'" alt="保存并打印" onclick="doSave('1')" style="cursor:hand"-->
									<!--button onclick="doSave('1')">生成打印文书</button-->
								</td>
								<td width="9%">
									<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturn()" style="cursor:hand">
									</td>
								<td width="9%">
									
							<img src="<%=static_contextpath%>images/tuichu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tuichu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tuichu1.jpg'" alt="退出" onclick="doExit();" style="cursor:hand">
									
									</td>
							</tr>
						</table>
						<br>
					</tr>
				</table>
			</tr>
		</table>
		</td>
	</tr>
</table>
<tr>
	<td valign="bottom" align="center"><br>
	<jsp:include page="../../../include/bottom.jsp" flush="true">
	</jsp:include></td>
</tr>
</body>
</html>
