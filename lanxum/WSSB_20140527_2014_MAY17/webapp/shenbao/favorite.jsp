<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="com.ttsoft.bjtax.shenbao.szsm.web.FavoriteForm"%>
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<script>
var static_contextpath = "<%=static_contextpath%>";
var local = "favorite.jsp";
var myForm ;
var tmpCheck_Szsmdm = new Array();
var tmpHtmlStr;

</script>
<script language="javascript" src="js/szsmTree.js"></script>
<html>
<head>
<title>配置税种税目</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript">

<%
FavoriteForm form = (FavoriteForm)request.getAttribute("favoriteForm");
List toBeAlertSzsm = form.getToBeAlertSzsm();
String[] str;
//System.out.println("------toBeAlertSzsm-------"+toBeAlertSzsm.size());
StringBuffer szsmtmp = new StringBuffer("var szsmtmp = [");
for (int i=0; i<toBeAlertSzsm.size(); i++)
{
		szsmtmp=szsmtmp.append("[");
		str=(String[]) toBeAlertSzsm.get(i);
		for(int j=0;j<str.length;j++)
		{	
			szsmtmp=szsmtmp.append("\"").append(str[j]).append("\"");
			if(j!=str.length-1)
			{
				szsmtmp=szsmtmp.append(",");
			}else
			{
				szsmtmp=szsmtmp.append("]");
			}
		}
		if(i!=toBeAlertSzsm.size()-1)
		{
			szsmtmp=szsmtmp.append(",");
		}
}
szsmtmp=szsmtmp.append("];");
out.println(szsmtmp.toString());

//start 获取委托代征税种税目  tujb 201406
List wtdzszsm = form.getWtdzszsm();
//System.out.println("1 ------wtdzszsm-------"+wtdzszsm.size());
String[] strWtdzszsm;
StringBuffer wtdzszsmtmp = new StringBuffer("var wtdzszsmtmp = [");
for (int i=0; i<wtdzszsm.size(); i++)
{
		wtdzszsmtmp=wtdzszsmtmp.append("[");
		strWtdzszsm=(String[]) wtdzszsm.get(i);
		for(int j=0;j<strWtdzszsm.length;j++)
		{	
			wtdzszsmtmp=wtdzszsmtmp.append("\"").append(strWtdzszsm[j]).append("\"");
			if(j!=strWtdzszsm.length-1)
			{
				wtdzszsmtmp=wtdzszsmtmp.append(",");
			}else
			{
				wtdzszsmtmp=wtdzszsmtmp.append("]");
			}
		}
		if(i!=wtdzszsm.size()-1)
		{
			wtdzszsmtmp=wtdzszsmtmp.append(",");
		}
}
wtdzszsmtmp=wtdzszsmtmp.append("];");
out.println(wtdzszsmtmp.toString());
//end 获取委托代征税种税目  tujb 201406
%>

function doSave()
{ 	
    var element = document.getElementsByName("checked_szsmdm");
   	for (var i=0; i<element.length; i++)
    {
        var tsnyArr = new Array();
			  tsnyArr = getTsny(szsmtmp,element[i].value);
				if(tsnyArr.length != 0){					 
					var checkResult = doAlertCheck(tsnyArr);
				  if(checkResult == false){
							return  checkResult;
						}						 
				}
    }	
	
	/*if(document.forms[0].checked_szsmdm!=undefined){
		alert(document.forms[0].checked_szsmdm.length);
	}else{
		alert("错误没有提交集合");
	}*/
	
    document.forms[0].actionType.value = "Save";
    document.forms[0].submit();
}

function doReturn()
{
	document.forms[0].action = "szsm.do";
	document.forms[0].submit();
}

function adjust(szsmdm, szsmmc)
{
	try {
		var obj = eval("deleteMe");
	}
	catch(e) {
		addRow(szsmdm, szsmmc);
		resetIndex();

		return;
	}

	if (obj == null)
	{
		addRow(szsmdm, szsmmc);
		resetIndex();

		return;
	}

    var found = false;
	if (obj.length == null)
	{
        if (deleteMe.value == szsmdm)
		{
	        Table_Master.deleteRow(1);
			resetIndex();
			return;
		}
	}

    var length = deleteMe.length;
   	for (var i=0; i<length; i++)
    {
   	    if (deleteMe[i].value == szsmdm)
       	{
            found = true;
   	        break;
       	}
    }

    if (found)
    {
        Table_Master.deleteRow(i+1);
    }
    else
    {
		addRow(szsmdm, szsmmc);
    }

	resetIndex();
}

function addRow(szsmdm, szsmmc)
{
	var otr = Table_Master.insertRow();

    otr.height = "20";

	rowlength = Table_Master.rows.length-1;

	var cell = otr.insertCell();
    cell.align = "center";
    cell.width = "15";
	cell.bgColor = "DCE4E9";

	var cell = otr.insertCell();
	cell.innerHTML = "<input type='checkbox' checked name='checked_szsmdm' value='"+szsmdm+"'></input>";
	cell.style.display="none";

	var cell = otr.insertCell();
	cell.bgColor = "DCE4E9";
	cell.innerText = " " + szsmmc;// + "(" + szsmdm + ")";

	cell = otr.insertCell();
    cell.align = "center";
    cell.width = "30";
	cell.bgColor = "F1DFCF";
	cell.innerHTML = "<a href='#' id='deleteMe' value='" + szsmdm + "' onclick='deleteRow(this); return false;'>删除</a>";
	/*try{
		alert(document.forms[0].checked_szsmdm.length);
	}catch(e){
		try{
			alert(document.forms[0].checked_szsmdm.value);
		}catch(e){alert("添加checkbox失败"+e);}
		
	}*/
	
	
	
}

function deleteRow(obj)
{
	var index = findSelfIndex(obj);
	Table_Master.deleteRow(index+1);

	resetIndex();

	adjustSzsmTree(obj.value);

	return false;
}

function adjustSzsmTree(szsmdm)
{
	if(document.forms[0].check_szsmdm!=undefined){
		try{
				for (var i=0; i<document.forms[0].check_szsmdm.length; i++)
				{
					var obj = document.forms[0].check_szsmdm[i];
					if (obj.value == szsmdm && obj.checked)
					{
						obj.checked = false;
						return true;
					}
				}
				tmpCheck_Szsmdm[tmpCheck_Szsmdm.length] = szsmdm;
				//alert("将"+tmpCheck_Szsmdm[tmpCheck_Szsmdm.length-1]+"加入现在集合内有"+tmpCheck_Szsmdm.length+"个元素");
		}catch(e){
			var obj = document.forms[0].check_szsmdm;
			if (obj.value == szsmdm && obj.checked)
			{
				obj.checked = false;
			}
		}
		
	}else{
		tmpCheck_Szsmdm[tmpCheck_Szsmdm.length] = szsmdm;
		//alert("将"+tmpCheck_Szsmdm[tmpCheck_Szsmdm.length-1]+"加入现在集合内有"+tmpCheck_Szsmdm.length+"个元素");
		
	}
	
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

function resetIndex()
{
	var rows = Table_Master.rows;
	var length = rows.length;
	for (var i=1; i<length; i++)
	{
		rows[i].cells[0].innerText = i;
	}
}

function clearCheck()
{
	var myobj = document.forms[0].check_szsmdm;
	if (myobj == null)
	{
		return;
	}

	var length = document.forms[0].check_szsmdm.length;
	if (length == null)
	{
		if (document.forms[0].check_szsmdm.checked)
		{
			if (notFound(document.forms[0].check_szsmdm.value))
			{
				document.forms[0].check_szsmdm.checked = false;
			}
		}
	}

	var length = document.forms[0].check_szsmdm.length;
	for (var i=0; i<length; i++)
	{
		var obj = document.forms[0].check_szsmdm[i];
		if (obj.checked)
		{
			if (notFound(obj.value))
			{
				obj.checked = false;
			}
		}
	}
	
	
}

function notFound(checkvalue)
{
	try {
		var obj = eval("deleteMe");
	}
	catch(e) {
		return true;
	}

	var length = deleteMe.length;
	if (length == null)
	{
		return (deleteMe.value != checkvalue);
	}

	for (var i=0; i<deleteMe.length; i++)
	{
		if (deleteMe[i].value == checkvalue)
		{
			return false;
		}
	}

	return true;
}

function loadSZinfo(divObject){
var divObject = document.getElementById("szsmTree");
	loadRootSZ(divObject);
}
function ToggleDisplay(szsmdm,divitem, imgitem){
	loadRamoseSZSM(szsmdm,divitem,imgitem);
	
}
function userFunction(){
	//以下部分检查被载入的CHECKBOX里有没有已经被删除的项，如果有将其设为未选中
		for(var tmp=0;tmp<tmpCheck_Szsmdm.length;tmp++){
				var tmpSzsmdm = tmpCheck_Szsmdm[tmp];
				if(tmpSzsmdm==null)continue;
				//alert(tmpSzsmdm);
					if(document.forms[0].check_szsmdm!=undefined){
						for (var i=0; i<document.forms[0].check_szsmdm.length; i++)
						{
							var obj = document.forms[0].check_szsmdm[i];
							//alert(obj.value);
							if (obj.value == tmpSzsmdm)
							{
								//alert("发现"+obj.value+"删除");
								obj.checked = false;
								tmpCheck_Szsmdm[tmp] = null;
								break;
							}
						}
					}else{
						break;
					}
		}
	
}

//根据关键值返回提示内容
function getTsny(arrayName,key){
  var obj = eval(arrayName);
  var tempArr = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==key) tempArr.push(obj[ii]);
  }
  return tempArr;
}


//检查提示信息是否在提示有效日期内
function doAlertCheck(tsnyArr){	  
	  //alert("tsnyArr=="+tsnyArr);		
		//var szsmdm = tsnyArr[0][0];//税种税目代码
		var txlxdm = tsnyArr[0][1];//提示类型代码	
		var tsny = tsnyArr[0][2];//提示内容			
		var tsksrq = tsnyArr[0][3];//提示开始日期
		var tsjsrq = tsnyArr[0][4];//提示结束日期	
		var nowdate = tsnyArr[0][5];//系统当前日期
		var szsmmc = tsnyArr[0][6];//税种税目名称
		
		if(txlxdm == "01")
		{			
		    //判断系统当前日期是否在提示开始日期和提示结束日期范围内，是则提示tsny中的信息
		    var inRangeOfTsrq = false;
		    if(tsksrq != ""){
		    	if(parseFloat(nowdate) < parseFloat(tsksrq)){
		    		inRangeOfTsrq = true;
		    	}			
		    }
		    
		    if(tsksrq == ""){
		    	if(tsjsrq != ""){
		    		if(parseFloat(nowdate) > parseFloat(tsjsrq)){
		    			inRangeOfTsrq = true;
		    		}			
		    	}		
		    }
        
		    if(tsjsrq != ""){
		    	if(parseFloat(nowdate) > parseFloat(tsjsrq)){
		    		inRangeOfTsrq = true;
		    	}			
		    }
		    
		    if(tsjsrq == ""){
		    	if(tsksrq != ""){
		    		if(parseFloat(nowdate) < parseFloat(tsksrq)){
		    			inRangeOfTsrq = true;
		    		}			
		    	}	
		    }
        
		    				
		    //提示信息如下：		
		    //如果当前日期在提示日期范围内，则提示
		    if(inRangeOfTsrq == false){
		    	if(tsny != '' && tsny != null){
		    		if(!window.confirm(tsny+"\n\n"+"是否继续？点击“确定”继续进行申报，点击“取消”须删除该税种税目后继续申报。")){
		    			return false;//不同意，返回
		    		}
		    	}
		    }		
		}
		if(txlxdm == "02")
		{
			if(tsny != '' && tsny != null){
				alert(tsny+"\n\n"+"点击“确定”须删除该税种税目后继续设置保存。");
				return false;
			}
		}		
}

function parseXMLOnLoad(){
	clearCheck();
	loadSZinfo();
	myForm = document.forms[0];
	
}

</script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<STYLE TYPE='text/css'>
a:link {
    color: #6D929C;
    text-decoration: none;
}
a:visited {
    color: #6d929c;
    text-decoration: none;
}
a:hover {
    color: #00CC99;
    text-decoration: underline;
}
a:active {
    color: #A0A0A0;
    text-decoration: none;
}
DIV.indent {margin-left:15} 
</STYLE>
</head>
<body onLoad="parseXMLOnLoad();" bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top">

    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="配置税种税目" />
		<jsp:param name="help_url" value="help/wssb/qyzhsb/szcysm/szcysm-000.htm"/>
    	</jsp:include>
    </td>
  </tr>
<!-- self-->
<tr>
 <td>
<br>
<table width="100%" border="0" align="center" cellspacing="0" bgcolor="#FFFFFF" class="table-98">
          <tr>
            <td class="1-td1" colspan="2">选择要保存的税目</td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td width="45%" valign="top" class="1-td2">
<br>
  <div align="center">
  <html:form action="favorite.do">
  <html:hidden property="actionType" />
                <table id="Table_Master" width="75%" border="0" cellpadding="0" cellspacing="1" bgcolor="#7E98A5" class="black9" >
                  <tr>
                    <td colspan="3" bgcolor='#DCE4E9'>
                      税目名称
                    </td>
                  </tr>

 
                <logic:iterate name="favoriteForm" property="previousSzsm" id="data" indexId="i">
				
                  <tr height="20">
                    <td bgcolor="#DCE4E9" align="center" width="15"><%=i.intValue() + 1%></td>
                    <td bgcolor="#DCE4E9">&nbsp;<bean:write name="data" property="szsmmc"/><!--(<bean:write name="data" property="szsmdm"/>)--></td>
                    <td bgcolor="#F1DFCF" align="center" width="30"><a href="#" id="deleteMe" value='<bean:write name="data" property="szsmdm"/>' onClick="deleteRow(this); return false;">删除</a><div style="display: none;"><input type="checkbox" checked="checked" name="checked_szsmdm" value='<bean:write name="data" property="szsmdm"/>'/></div></td>
					
                  </tr>
                </logic:iterate>
              </table>
			  <br>
				<img src="<%=static_contextpath%>images/baocun1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/baocun2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="保存" onClick="doSave()" style="cursor:hand">
				&nbsp;&nbsp;
				<img src="<%=static_contextpath%>images/fanhui1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onClick="doReturn()" style="cursor:hand">
              </div>
            </td>
            <td rowspan="2" width="55%" valign="top" align="center" class="1-td2">
			<div  align="left" id="szsmTree"></div>
            
            </td>
        </tr>

</table>
</html:form>
<br>
<!--self end-->

    </td>
  </tr>
<tr>
  <td valign="bottom" align="center">
	<%@ include file="../include/bottom.jsp" %>
  </td>
</tr>
 </table>
</body>
</html>
