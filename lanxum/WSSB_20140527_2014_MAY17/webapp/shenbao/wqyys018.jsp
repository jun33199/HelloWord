<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Szsm"%>
<%@page import="java.util.List"%>
<%

	String static_contextpath= com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
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
  	String slArrayStr = "";
	String szsmmcArrayStr = "";
	String szsmdmArrayStr = "";
	String szmctmp = "";
	String szdmtmp = "";
	String sl = "";
	Szsm szsm = null;
	List szsmList = (List)session.getAttribute("szsmlist");
	if(szsmList!=null||szsmList.size()!=0)
	{
		int szsmListSize = szsmList.size();
		for(int i=0;i<szsmListSize;i++)
		{
			szsm = (Szsm)szsmList.get(i);
			if(szsm.getSl()==null){
				sl=" ";
			}else{
				sl = ""+szsm.getSl();
			}
			
			if(i==szsmListSize-1){
				szsmmcArrayStr += "'"+szsm.getSzsmmc()+"'";
				szsmdmArrayStr += "'"+szsm.getSzsmdm()+"'";
				slArrayStr += "'"+sl+"'";
			}else{
				szsmmcArrayStr += "'"+szsm.getSzsmmc()+"',";
				szsmdmArrayStr += "'"+szsm.getSzsmdm()+"',";
				slArrayStr += "'"+sl+"',";
			}
		}
		
	}
%>

<html>
<head>
<title>北京市外国企业营业税纳税申报</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
</head>
<script language="JavaScript">
var isContainer;
g_objSI.Container = "<%=containerName%>";
if (g_objSI.Container != ''){isContainer = true;}
	   	
	/*if (doSubmitXML(document.forms[0],"Delete",isContainer,xmlDoc.xml,true))
	{
		 return true;
	}
	 else
	{
		return false;
	}*/
var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var isNewSenBao = false;
var remember = 0;
var button = "<img src='<%=static_contextpath%>images/tianjia1.jpg' style='cursor:hand' alt='增加' overSrc='<%=static_contextpath%>images/tianjia2.jpg' onclick='doAdd();return false;'/>&nbsp;&nbsp;&nbsp;&nbsp;<img src='<%=static_contextpath%>images/baocun1.jpg' style='cursor:hand' alt='保存' overSrc='<%=static_contextpath%>images/baocun2.jpg' onclick='doSave();'/>&nbsp;&nbsp;&nbsp;&nbsp;<img src='<%=static_contextpath%>images/shanchu1.jpg' style='cursor:hand' alt='删除' overSrc='<%=static_contextpath%>images/shanchu2.jpg' onclick='doDelete();'/>&nbsp;&nbsp;&nbsp;&nbsp;";
function doAdd()
{

itemsize = mainTable.rows.length-3;
var row = mainTable.insertRow(2 + itemsize);

var cell = row.insertCell();
cell.className = "2-td2-left";
cell.innerText = itemsize + 1;

var cell1 = row.insertCell();
cell1.className = "2-td2-left";
var yourSelect = document.createElement("<select name=\"szsmdm\" style=\"width:250\" onchange=\"selChange(this)\">");
var selectDefault = true;
var selectStore;
for(var i=0;i<document.forms[0].vosl.length;i++){
	var yourOption;
	yourOption = document.createElement("<option>");
	yourOption.text=document.forms[0].voszsmmc[i].value;
	yourOption.value=document.forms[0].voszsmdm[i].value;
	yourSelect.options.add(yourOption);
	if(selectDefault && i >= remember && document.forms[0].voszsmdm[i].value.length == 6){
		selectStore = i;
		selectDefault = false;
	}
}
cell1.appendChild(yourSelect);

var cell2 = row.insertCell();
cell2.className = "2-td2-left";
var htcje = document.createElement("<input maxlength=\"15\" size=\"15\" name=\"htcje\" value=\"0.00\" onChange=\"return(checkvalue(this,0)&&formatCurrency(this,0)&&jfzceChange(this));\">");
cell2.appendChild(htcje);

var cell21 = row.insertCell();
cell21.className = "2-td2-left";
var yjl = document.createElement("<input maxlength=\"5\" size=\"5\" name=\"yjl\" value=\"0.00\" onChange=\"return(checkvalue(this,3)&&jfzceChange(this));\">");
cell21.appendChild(yjl);

var cell3 = row.insertCell();
cell3.className="2-td2-left";
cell3.innerHTML = "<div id=\"hdsreDiv\" align=\"right\">0.00</div><input type=\"hidden\" name=\"hdsre\" value=\"0.00\">";

var cell4 = row.insertCell();
cell4.className = "2-td2-left";
var jsje = document.createElement("<input maxlength=\"15\" size=\"15\" name=\"jsje\" value=\"0.00\" onChange=\"return(checkvalue(this,0)&&formatCurrency(this,0)&&computer(this,'jsje'));\">");
cell4.appendChild(jsje);

var cell5 = row.insertCell();
cell5.className="2-td2-left";
cell5.innerHTML = "<div id=\"slDiv\" align=\"left\">"+document.forms[0].vosl[remember].value+"</div><input type=\"hidden\" name=\"sl\" value="+document.forms[0].vosl[remember].value+">";

var cell6 = row.insertCell();
cell6.className="2-td2-left";
cell6.innerHTML = "<div id=\"ynseDiv\" align=\"right\">0.00</div><input type=\"hidden\" name=\"ynse\" value=\"0\">";

var cell7 = row.insertCell();
cell7.className = "2-td2-left";
var yjnse = document.createElement("<input maxlength=\"15\" size=\"15\" name=\"yjnse\" value=\"0.00\" onChange=\"return(checkvalue(this,0)&&formatCurrency(this,0)&&computer(this,'yjnse'));\">");
cell7.appendChild(yjnse);

var cell8 = row.insertCell();
cell8.className="2-td2-left";
cell8.innerHTML = "<div id=\"bqybseDiv\" align=\"right\">0.00</div><input type=\"hidden\" name=\"bqybse\" value=\"0\">";

var cell9 = row.insertCell();
cell9.className = "2-td2-center";
cell9.noWrap = true;
cell9.innerHTML = "<a href='#' id='deleteMe' onclick='deleteRow(this);return false;'>删除</a>";

yourSelect.selectedIndex = selectStore;
selChange(yourSelect);

return false;
}
function getSbsj(objForm)
{
	var rootNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
	var total=document.getElementsByName("htcje").length;
	var nodeNameArray = new Array("szsmdm","szsmmc","jsje","sl","ynse","yjnse","bqybse","htcje","yjl","hdsre");
	for(var j=0;j<total;j++)
	{	
		var sbsjNode=g_Doc.XMLDoc.createElement("sbsj");
		for(var i = 0;i<nodeNameArray.length;i++){
			var tmpNodeName = nodeNameArray[i];
			var tmpNode= g_Doc.XMLDoc.createElement(tmpNodeName);
			var tmpValue;
			if(tmpNodeName != "szsmmc"){
				tmpValue = document.getElementsByName(tmpNodeName).item(j).value;
			}else{
				tmpValue = document.getElementsByName("szsmdm").item(j).options[document.getElementsByName("szsmdm").item(j).selectedIndex].text;
			}
			var objCDATA = g_Doc.XMLDoc.createCDATASection(tmpValue);
			tmpNode.appendChild(objCDATA);
			sbsjNode.appendChild(tmpNode);
			
		}
		rootNode.appendChild(sbsjNode);
	}
}
function doDelete(){
	if(isNewSenBao)
	{
		return false;
	}
	if(confirm(confirmDelete)){
		var rootNode = xmlDoc.documentElement;
		if(rootNode.getElementsByTagName("sbsj").length!=0){
			var szsmListNode = rootNode.getElementsByTagName("szsmList").item(0);
			rootNode.removeChild(szsmListNode);
		}
		var objCDATA =xmlDoc.createCDATASection("3");
		rootNode.selectSingleNode("//ywczlx").text = "";
		rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
		if (doSubmitXML(document.forms[0],"Delete",isContainer,xmlDoc.xml,true))
		{
			 return true;
		}
	 		else
		{
			return false;
		}
	 }else{
        return false;
    }
}
function checkDuplicate(){
	itemsize = mainTable.rows.length - 3;
	for(i = 0;i < itemsize - 1; i++){
		for(j = i + 1;j < itemsize; j++){
			if(document.forms[0].szsmdm[i].value == document.forms[0].szsmdm[j].value){
				return j;
			}
		}
	}
	return -1;
}
function doSave(){
	itemsize = mainTable.rows.length - 3;
	if(itemsize == 0){
		alert("不能提交空的申报表，请添加申报资料或者返回,如果要删除申报表请点击屏幕下方的删除按钮！");
		return false;
    }else{
		if(!confirm(confirmSave)){return false;}
        var index = checkDuplicate();
		if(index != -1){
			alert("税种税目不能重复！");
			document.forms[0].szsmdm[index].focus();
			return false;
		}

	itemsize = mainTable.rows.length - 3;
	if(itemsize == 0){
		return false;
	}
	if(itemsize == 1){
			if(document.forms[0].htcje.value == "")
			{
				alert("合同成交额不能为空！");
				document.forms[0].htcje.focus();
				document.forms[0].htcje.select();
				return false;
			}
			if(document.forms[0].yjl.value == "")
			{
				alert("佣金率不能为空！");
				document.forms[0].yjl.focus();
				document.forms[0].yjl.select();
				return false;
			}
			if(document.forms[0].yjl.value == 0)
			{
				alert("佣金率不能为0！");
				document.forms[0].yjl.focus();
				document.forms[0].yjl.select();
				return false;
			}
			if(document.forms[0].jsje.value == "")
			{
				alert("计税金额不能为空！");
				document.forms[0].jsje.focus();
				document.forms[0].jsje.select();
				return false;
			}
			if(document.forms[0].ynse.value == "")
			{
				alert("已纳税额不能为空！");
				document.forms[0].ynse.focus();
				document.forms[0].ynse.select();
				return false;
			}
	}else{
			for(var j = 0;j < itemsize; j++)
			{
				if(document.forms[0].htcje[j].value == "")
				{
					alert("合同成交额不能为空！");
					document.forms[0].htcje[j].focus();
					document.forms[0].htcje[j].select();
					return false;
				}
				if(document.forms[0].yjl[j].value == "")
				{
					alert("佣金率不能为空！");
					document.forms[0].yjl[j].focus();
					document.forms[0].yjl[j].select();
					return false;
				}
				if(document.forms[0].yjl[j].value == 0)
				{
					alert("佣金率不能为0！");
					document.forms[0].yjl[j].focus();
					document.forms[0].yjl[j].select();
					return false;
				}
				if(document.forms[0].jsje[j].value == "")
				{
					alert("计税金额不能为空！");
					document.forms[0].jsje[j].focus();
					document.forms[0].jsje[j].select();
					return false;
				}
				if(document.forms[0].ynse[j].value == "")
				{
					alert("已纳税额不能为空！");
					document.forms[0].ynse[j].focus();
					document.forms[0].ynse[j].select();
					return false;
				}
			}
	}
		var rootNode = xmlDoc.documentElement;
		if(rootNode.getElementsByTagName("sbsj").length!=0){
			document.forms[0].ywczlx.value = 2;
		}else{
			document.forms[0].ywczlx.value = 1;
		}
		if (doSubmitXML(document.forms[0],"Save",isContainer,"",true)){
			 return true;
		} else{
			return false;
		}		
	}

}

function doReturn(){
	if(confirm(confirmReturn)){
        	document.forms[0].actionType.value = "Return";
			document.forms[0].submit();
        	return true;
    	}else{
        	return false;
    	}
}

function deleteRow(obj)
{
	if(confirm("确定删除？")){
		var index = findSelfIndex(obj);
		mainTable.deleteRow(index+2);
		resetIndex();
		computeHJ();
	}
	return false;
}

function findSelfIndex(obj)
{
	var length = deleteMe.length;
	if (length == null)
	{
		return 0;
	}

	for (var i=0; i<deleteMe.length; i++)
	{
		if (deleteMe[i] == obj)
		{
			return i;
		}
	}
}
function findInputObjIndex(obj,inputName){
	
	var myForm = document.forms[0];
	var length = myForm(inputName).length;
	if(length == null){
		return 0;
	}
	for(var i =0;i<length;i++){
		if(myForm(inputName)[i]==obj){
			return i;
		}
	}
}

function resetIndex()
{
	var rows = mainTable.rows;
	var length = rows.length;
	for (var i=2; i<length - 1; i++)
	{
		rows[i].cells[0].innerText = i - 1;
	}
}

function formatDIV(obj)
{
	var tmpValue = trim(obj.innerText);
	var pointIndex = tmpValue.indexOf(".");
	if(pointIndex < 0)
	{
		if(tmpValue == null || tmpValue == "" || tmpValue == ".")
		{
			tmpValue = "0.00";
		}
		else
		{
			tmpValue += ".00";
		}
	}
	else
	{
		afterpoint = (tmpValue.length-1) - pointIndex;
		if(afterpoint == 0)
		{
			tmpValue += "00";
		}
		else if(afterpoint == 1)

		{
			tmpValue += "0";
		}
	}
	obj.innerText = tmpValue;
}

function computer(obj,name)
{
	var i = findInputObjIndex(obj,name);
	itemsize = mainTable.rows.length - 3;

    if(itemsize > 1)
    {
        var sl = document.forms[0].sl[i].value;
        if(checkvalue(document.forms[0].jsje[i],0))
        {
			var jsje = document.forms[0].jsje[i].value;
        }
	else
	{
		var jsje = 0;
	}
        if(checkvalue(document.forms[0].yjnse[i],0))
        {
        	var yjnse = document.forms[0].yjnse[i].value;
        }
	else
	{
		var yjnse = 0;
	}
        var ynse = jsje * sl;
        var bqybse = ynse - yjnse;
	var ynseRound = Math.round(ynse*100)/100;
        document.forms[0].ynse[i].value = ynseRound;
	ynseDiv[i].innerText = ynseRound;
	formatDIV(ynseDiv[i]);
	var bqybseRound = Math.round(bqybse*100)/100;
        document.forms[0].bqybse[i].value = bqybseRound;
	bqybseDiv[i].innerText = bqybseRound;
	formatDIV(bqybseDiv[i]);
    }
    else if(itemsize == 1)
    {
        var sl = document.forms[0].sl.value;
        if(checkvalue(document.forms[0].jsje,0))
        {
		var jsje = document.forms[0].jsje.value;
        }
	else
	{
		var jsje = 0;
	}
        if(checkvalue(document.forms[0].yjnse,0))
        {
        	var yjnse = document.forms[0].yjnse.value;
        }
	else
	{
		var yjnse = 0;
	}
        var ynse = jsje * sl;
        var bqybse = ynse - yjnse;

	var ynseRound = Math.round(ynse*100)/100;
        document.forms[0].ynse.value = ynseRound;
	ynseDiv.innerText = ynseRound;
	formatDIV(ynseDiv);
	var bqybseRound = Math.round(bqybse*100)/100;
        document.forms[0].bqybse.value = bqybseRound;
	bqybseDiv.innerText = bqybseRound;
	formatDIV(bqybseDiv);
    }
    computeHJ();
}
function selChange(obj)
{
	var index = obj.selectedIndex;
	itemsize = mainTable.rows.length - 3;
	var i = 0;
	for(j = 0;j < itemsize; j++)
	{
		if(itemsize==1?document.forms[0].szsmdm == obj:document.forms[0].szsmdm[j] == obj)
		{
			for(n = index;n < obj.options.length; n++)
			{
				if(obj.options[n].value.length == 6)
				{
					obj.selectedIndex = n;
					index = n;
					break;
				}
			}
			i = j;
			break;
		}
	}
    if(itemsize > 1){
	slDiv[i].innerText = document.forms[0].vosl[index].value;
        document.forms[0].sl[i].value = document.forms[0].vosl[index].value;
    }else{
	slDiv.innerText = document.forms[0].vosl[index].value;
        document.forms[0].sl.value = document.forms[0].vosl[index].value;
    }
    computer(document.forms[0].jsje[i],'jsje');

	remember = index;
}

function jfzceChange(obj)
{
	itemsize = mainTable.rows.length - 3;
	var i = 0;
	for(j = 0;j < itemsize; j++)
	{
		if(document.forms[0].htcje[j] == obj || document.forms[0].yjl[j] == obj)
		{
			i = j;
			break;
		}
	}
	var htcje = 0;
	var yjl = 0;
    	if(itemsize > 1)
    	{
        	if(checkvalue(document.forms[0].htcje[i],0))
                {
			htcje = parseFloat(document.forms[0].htcje[i].value);
        	}
        	if(checkvalue(document.forms[0].yjl[i],0))
                {
			yjl = parseFloat(document.forms[0].yjl[i].value);
        	}
        	var hdsre = htcje * yjl;
        	document.forms[0].hdsre[i].value = Math.round(hdsre*100)/100;
		hdsreDiv[i].innerText = document.forms[0].hdsre[i].value;
		formatDIV(hdsreDiv[i]);
    	}
    	else if(itemsize == 1)
	{
		if(checkvalue(document.forms[0].htcje,0))
		{
			htcje = parseFloat(document.forms[0].htcje.value);
		}
		if(checkvalue(document.forms[0].yjl,0))
                {
			yjl = parseFloat(document.forms[0].yjl.value);
        	}
        	var hdsre = htcje * yjl;
		document.forms[0].hdsre.value = Math.round(hdsre*100)/100;
		hdsreDiv.innerText = document.forms[0].hdsre.value;
		formatDIV(hdsreDiv);
    	}
}
var loaded = false;
var done  =  false;
var xmlDoc;
var xslDoc;
function parseXml()
{
	var xslPath = "/XSLTWEB/model/010014/XSLT/"+strXSLTVersion+".xsl";
	xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
    xslDoc = new ActiveXObject("Microsoft.XMLDOM");
	xmlDoc.async = false;
	xslDoc.async = false;
	if (xmlDoc==null || xslDoc==null)
	{
		 alert("创建DOC对象出错");
		 return; 
	}
	if(!xmlDoc.loadXML(strXml))
	{
		alert("装载xml数据出错");
		return false;
	}
	if(!xslDoc.load(xslPath))
	{
		alert("装载xsl数据出错");
		return false;
	}

	var rootNode = xmlDoc.documentElement;
	var szsmmcArray = new Array(<%=szsmmcArrayStr%>);
	var szsmdmArray = new Array(<%=szsmdmArrayStr%>);
	var slArray = new Array(<%=slArrayStr%>);
	var szsmListNode = xmlDoc.createElement("szsmList");
	for(var i = 0;i<szsmmcArray.length;i++)
	{	
		var szsmNode = xmlDoc.createElement("szsm");
		var szsmmcNode = xmlDoc.createElement("szsmmc");
		var szsmdmNode = xmlDoc.createElement("szsmdm");
		var slNode = xmlDoc.createElement("sl");
		var szsmmcNodeValue = xmlDoc.createCDATASection(szsmmcArray[i]);
		var szsmdmNodeValue = xmlDoc.createCDATASection(szsmdmArray[i]);
		var slNodeValue = xmlDoc.createCDATASection(slArray[i]);
		szsmmcNode.appendChild(szsmmcNodeValue);
		szsmdmNode.appendChild(szsmdmNodeValue);
		slNode.appendChild(slNodeValue);
		szsmNode.appendChild(szsmdmNode);
		szsmNode.appendChild(szsmmcNode);
		szsmNode.appendChild(slNode);
		szsmListNode.appendChild(szsmNode);
	}
	rootNode.appendChild(szsmListNode);
	if(rootNode.getElementsByTagName("sbsj").length==0)
	{
		isNewSenBao = true;
	}
	var httpStr;
	try{
		httpStr = xmlDoc.transformNode(xslDoc);
	}catch(e){
		alert("数据载入异常"+e);
		return false;
	}
	document.all.tb.innerHTML = httpStr;
	if(rootNode.getElementsByTagName("done").item(0).text==1)
	{
		done = true;
		document.all.bt.innerHTML = button + document.all.bt.innerHTML;
	}
	computeHJ();
}
function computeHJ(){
	if(done)
	{
		itemsize = mainTable.rows.length - 3;
		if(itemsize == 0){
			document.forms[0].hjDiv.value = itemsize;
			return false;
		}
		var length = document.forms[0].bqybse.length;
		if (length == null && itemsize == 1){
			document.forms[0].hjDiv.value = document.forms[0].bqybse.value;
		}else{
			var hj = 0;
			for(var k = 0; k < document.forms[0].bqybse.length; k++){
				hj = Math.round((hj + parseFloat(document.forms[0].bqybse[k].value))*100)/100;
				document.forms[0].hjDiv.value = hj;
			}
		}
		formatCurrency(document.forms[0].hjDiv,0);
	}

}
function htcjeOnChange(obj,par1,par2,par3)
{
	return(checkvalue(obj,par1)&&formatCurrency(obj,par2)&&jfzceChange(obj,par3));
}
function yjlOnChange(obj,par1,par2)
{
	return(checkvalue(obj,par1)&&jfzceChange(obj,par2));
}
function jsjeAndyjnseOnChange(obj,par1,par2,obj,name)
{
	return(checkvalue(obj,par1)&&formatCurrency(obj,par2)&&computer(obj,name));
}
</script>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onLoad="javascript: parseXml();">

<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>

    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="外国企业营业税纳税申报" />
		<jsp:param name="help_url" value="help/wssb/sbzl/wqyys/hdzs/wqyys-003.htm"/>
    	</jsp:include>

    	<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
	 		 <tr>
	    		<td valign="top"><br>
	     			 <html:errors/>
					<table  align="center" cellspacing="0" class="table-99">
	        			<tr>
	          				<td class="1-td1">北京市外国企业营业税纳税申报表</td>
	        			</tr>
							<td class="1-td2"><br/>
							<form action="wqyys.dc" method="post">
							<input type="hidden" name="actionType"/>
								<div id="tb"/>
							</form>
							</td>
						<tr>
							<td>

								<TABLE border=0 cellPadding=0 cellSpacing=0 width="100%">
										<TBODY>
											<TR>
											<TD height="40" colspan="8"> 
											<DIV align="center" id="bt">
											<img src="<%=static_contextpath%>images/fanhui1.jpg" alt="返回" overSrc="<%=static_contextpath%>images/fanhui2.jpg" style="cursor:hand" onClick="doReturn();"/>
											</DIV>
											</TD>
										</TR>
									</TBODY>
								</TABLE><BR>

							</td>
						</tr>
					</table>
	    		</td>
	  		</tr>
    	</table>
    </td>
  </tr>
  <tr>
    <td valign="bottom" align="center">
<br>
	<%@ include file="../include/bottom.jsp" %>
    </td>
  </tr>
 </table>
</body>

</html>