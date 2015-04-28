<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="declare" %>

<%
String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);

%>

<html>
<head>
<title>企业基本财务指标情况表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
</head>

<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/swapImages.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" >
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

//解析xml
function parseXmlOnLoad()
{
	var xslPath="/XSLTWEB/model/010011/XSLT/" +strXSLTVersion+".xsl";
	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
	//设置财务指标的tabIndex
	for(var i=1;i<=document.forms[0].qycwzbhc.length;i++){
		document.forms[0].qycwzbhc[i-1].tabIndex = -1;

		eval("document.forms[0].cwzb_C_"+i).tabIndex = tabIndex++;
		eval("document.forms[0].cwzb_C_"+i).onchange = computecwzb;
		eval("document.forms[0].cwzb_M_"+i).tabIndex = tabIndex++;
		eval("document.forms[0].cwzb_M_"+i).onchange = computecwzb;
	}
	return true;
}
//生成申报数据
function getSbsj(objForm)
{
	var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
	var objTemp = g_Doc.XMLDoc.createElement("sbsjlist");
	objNode.appendChild(objTemp);
	var total=document.forms(0).length-3;
	outer:
	for(var j=14;j<total;)
	{
		objNode=g_Doc.XMLDoc.createElement("sbsj");
		objTemp.appendChild(objNode);
		for(var i=j;i<j+4;i++)
		{
			var objName=objForm.elements(i).name;
			//alert(objName);
			var subStr=objName.split("qycwzb");
			var strName=subStr[1];
			//alert(strName);
			strValue=objForm.elements(i).value;
			node2=g_Doc.XMLDoc.createElement(strName);
			objNode.appendChild(node2);
			var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
			node2.appendChild(objCDATA);
		}
		j+=4;
	}	
}

var tabIndex = 1;
//根据ID获得对应的行次
function getHcByID(id){
      var index = -1;
      for(var i=0;i<id.length;i++){
         if(id.charAt(i) == '_'){
              index = i;
         }
      }

      if(index < 0){
      	alert("ID:"+id+",不正确！")
      	return false;
      }
      return parseInt(id.substring(index+1,id.length));

}
</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onLoad="parseXmlOnLoad();">

<form name="form1" method="POST" action="/shenbao/qycwzb.dc">

<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><html:errors/><br></td>
  </tr>
  <tr>
    <td align="center" valign="top">

    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="企业基本财务指标情况表" />
		<jsp:param name="help_url" value="help/wssb/sbzl/qyjbcwzb/qyjbcwzb-000.htm"/>
    	</jsp:include>
    </td>
  </tr>



  <tr>
    <td valign="top">
     <br>

	<input name="actionType" type="hidden" id="actionType" value="Show">

<table cellspacing="0" class="table-99">
  <tr>
     <td class="1-td1">企业基本财务指标情况表</td>
  </tr>
  <tr>
     <td valign="top" class="1-td2">
		<div id="result"></div>
	<br>
	<div align="center">
		<img src="<%=static_contextpath%>images/baocun1.jpg" onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="保存" onclick="javascript:return fnSave();" style="cursor:hand">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<img src="<%=static_contextpath%>images/shanchu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/shanchu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/shanchu1.jpg'" alt="删除" onclick="javascript:return fnRemove();" style="cursor:hand">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="javascript:return fnReturn()" style="cursor:hand">
	</div>
	<br>
     </td>
  </tr>
</table>
<br>

</form>

<script language="javascript">
var cwzb_jsgss = new Array();

function computecwzb()
{
 var id = trim(window.event.srcElement.id+"");
 if(!checkcwzbValue(id)){
    window.event.srcElement.select();
    return false;
 }else{
     computecwzbGS(id);
 }
}
function computecwzbGS(myid)
{
  var stack = new Stack();
  var strmyid = "" + myid;
  var hc = strmyid;
  for(var i=0;i<cwzb_jsgss.length;i++)
  {
      if(cwzb_jsgss[i].isInExpress(hc))
      {
          var retBolan = cwzb_jsgss[i].compute();
          if(retBolan != null)
          {
              setValue(retBolan.operator, retBolan.value);
              stack.push(retBolan.operator);
          }
      }
  }
  while(stack.length() > 0)
  {
      var nHc = stack.pop();
      computecwzbGS(nHc);
  }
}


//检查财务指标的数据
function checkcwzbValue(id){
  var hc = getHcByID(id)

  var obj = eval("document.forms[0]."+id);
  var xmmc = eval("document.forms[0].qycwzbxmmc_"+hc).value;
  var value = trim(obj.value);
  if(value == ""){
  	return true;
  }
  if(!checkvalue(obj,1))
  {
    return false;
  }

  //必须是数字
  if(isNaN(value)){
     alert(xmmc+"( "+hc+" ) 应为是数字");
     return false;
  }

  /*if(hc == 40 && !isDigit(value))
  {
    alert(xmmc+"( "+hc+" ) 必须为正整数");
    return false;
  }

  if(hc != 6 && parseFloat(value)<0){
    alert(xmmc+"( "+hc+" )不能为负数");
    return false;
  }
*/
  if(!checkPoint(value,2)){
    alert(xmmc+"( "+hc+" ) 小数位数不能大于2位");
    return false;
  }
  if(!checkIntPoint(value,13)){
     	   alert(xmmc+"( "+hc+" ) 整数位数不能大于13位");
           return false;
  }

//  obj.value = round(value);

  formatCurrency(obj,0);

  return true;
}


</script>




<script language="javascript">

//从某行次取数据
function getValue(hc)
{
    var o = document.forms[0].name + "." + hc;
    var obj = eval(o);
    if (obj.value == "")
    {
        return "0.0";
    }
    return obj.value;
}

//设置某一行次的value
function setValue(hc, value)
{
    var o = document.forms[0].name + "." + hc;

    eval(o).value = round(value);
}
function round(value){
    var temp = value * 100;
    temp = Math.round(temp);
    return temp/100;
}

function checkPoint(text , point){
	var value = trim(text+"");
	var index =value.indexOf(".");
	if(index >= 0){
		var x = value.substring(index+1);
		if(x.length > point){
			return false;
		}
	}
	return true;
}

//检查整数位数
function checkIntPoint(text,point){
var value = parseFloat(text);
var t = 1
for(var i=1;i<=point;i++){
   t *=  10;
}
return (value < t);

}

</script>


<script language="javascript">
function fnSave(){
	var old  = document.forms[0].ywczlx.value;
	if (confirm(confirmSave))
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
function fnRemove(){
	var old  = document.forms[0].ywczlx.value;
	if ((document.forms[0].ywczlx.value == 1) && confirm(confirmDelete))
	{
		changeYwczlx("3");
		document.forms[0].actionType.value="Remove";
		if (g_objSI.Container != '')
			{
				if (!doSubmitXML(document.forms[0],"Remove",true,g_Doc.parseXmlDoc.xml,true))
				{
					changeYwczlx(old);
					return false;
				}
			}
			else
			{
			   if(!doSubmitXML(document.forms[0],"Remove",false,g_Doc.parseXmlDoc.xml,true))
				{
				   changeYwczlx(old);
					return false;
			   }
			}
		 return true;
	}else
	{
		return false;
	}
}
function fnReturn(){
	if (confirm(confirmReturn))
	{
		document.forms[0].actionType.value="Return";
		if(!doSubmitXML(document.forms[0],"Return",false,"return",true))
		{
			return false;
		}
		return true;
	}else
	{
		return false;
	}
}
</script>

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

