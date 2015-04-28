<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml"%>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeTable"%>
<%@page import="java.util.List"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.NLMYJMXM"%>

<%
			String static_contextpath = com.ttsoft.common.util.ResourceLocator
			.getStaticFilePath(request);
			//List nlmyList = (List)session.getAttribute("NLMYJMXMLIST");
			List jzmzbsList0 = (List)session.getAttribute("JZMZBSLIST0");
			List jzmzbsList1 = (List)session.getAttribute("JZMZBSLIST1");
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
var nlmy0= new Array(<%=jzmzbsList0.size()%>);
var nlmy1= new Array(<%=jzmzbsList1.size()%>);
<%
for(int i=0;i<jzmzbsList0.size();i++)
{
	NLMYJMXM tmpbs0 = (NLMYJMXM)jzmzbsList0.get(i);
	out.println("nlmy0["+i+"] = [\""+tmpbs0.getNLMYJMXMDM()+"\",\""+tmpbs0.getNLMYJMXMMC()+"\"];");
}

for(int i=0;i<jzmzbsList1.size();i++)
{
	NLMYJMXM tmpbs1 = (NLMYJMXM)jzmzbsList1.get(i);
	out.println("nlmy1["+i+"] = [\""+tmpbs1.getNLMYJMXMDM()+"\",\""+tmpbs1.getNLMYJMXMMC()+"\"];");
}

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
var tempnlmyjmxmdm = '<%=request.getSession().getAttribute("NLMYJMXMDM")==null?"":request.getSession().getAttribute("NLMYJMXMDM")%>';
var nlmylx = '<%=request.getSession().getAttribute("NLMYLX05")==null?"":request.getSession().getAttribute("NLMYLX05")%>';
//alert(nlmylx);
function parseXmlOnLoad()
{
	var xslPath='/XSLTWEB/model/030005/XSLT/basx05.xsl';
	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    insertNlmy();
    insertNlmy1();
    xzb1()
    return true;
}
function insertNlmy()
{
var obj=document.getElementById("nlmyjmxmdmdiv0");
//alert(obj);
var select1 = document.createElement("select");
        var ooption = new Array();
        for(var i=0;i<nlmy0.length;i++)
        {
               select1.options[i] = new Option(nlmy0[i][1], nlmy0[i][0]);
                if(nlmylx!=""&&nlmy0[i][0]==nlmylx){
                //alert(tempgxjslx);
               select1.options[i].selected=true;
               }
        }
        select1.id="nlmyjmxmdm0";
        obj.appendChild(select1);
         //if(tempxsllx!=""&&tempxsllx=='VIEW')
               //select1.disabled=true;
}

function insertNlmy1()
{
var obj=document.getElementById("nlmyjmxmdmdiv1");
//alert(obj);
var select1 = document.createElement("select");
        var ooption = new Array();
        for(var i=0;i<nlmy1.length;i++)
        {
               select1.options[i] = new Option(nlmy1[i][1], nlmy1[i][0]);
                if(nlmylx!=""&&nlmy1[i][0]==nlmylx){
                //alert(tempgxjslx);
               select1.options[i].selected=true;
               }
        }
        select1.id="nlmyjmxmdm1";
        obj.appendChild(select1);
         //if(tempxsllx!=""&&tempxsllx=='VIEW')
               //select1.disabled=true;
}

function xzb1(){

	if(tempnlmyjmxmdm=="01"||tempnlmyjmxmdm=="02"||tempnlmyjmxmdm=="03"||
	   tempnlmyjmxmdm=="04"||tempnlmyjmxmdm=="05"||tempnlmyjmxmdm=="06"||
	   tempnlmyjmxmdm=="07"||tempnlmyjmxmdm=="08")
	{
		document.getElementById("TABLE_LIST1").style.display="block";
		document.getElementById("TABLE_LIST2").style.display="none";
		document.getElementsByName("bm")[0].checked="true";
		//document.getElementById("bm1").disabled="false";
		//document.getElementById("bm2").disabled="true";
	}else if(tempnlmyjmxmdm=="09"||tempnlmyjmxmdm=="10")
	{
		document.getElementById("TABLE_LIST1").style.display="none";
		document.getElementById("TABLE_LIST2").style.display="block";
		document.getElementsByName("bm")[1].checked="true";
		//document.getElementById("bm1").disabled="true";
		//document.getElementById("bm2").disabled="false";
	}else
	{
		document.getElementById("TABLE_LIST1").style.display="block";
		document.getElementById("TABLE_LIST2").style.display="none";

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

//生成申报数据
function getSbsj(objForm)
{
    var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
	var objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
	objNode.appendChild(objTemp);
	getChildren(objTemp,"xh");
    var index = 0;
    if(document.getElementsByName("bm")[0].checked){
        index = 0;
    }else{
        index = 1;
    }
    var dmVal = document.getElementById("nlmyjmxmdm"+index).value;
    var jmsdeVal = document.getElementById("jmsde"+index).value;
    setNdChildren(objTemp,"nlmyjmxmdm",dmVal);
    setNdChildren(objTemp,"jmsde",jmsdeVal);
}

function setNdChildren(temp,strTag,value)
{
		var objTemp=temp;
		var node=g_Doc.XMLDoc.createElement(strTag);
		objTemp.appendChild(node);
		var objCDATA = g_Doc.XMLDoc.createCDATASection(value);
		node.appendChild(objCDATA);

}
// 将字符串格式化为货币格式
// par为0，自动填充0.00
// par为1，不自动填充
function formatKsslJsje(obj)
{
	//alert("kssljsje");
	return (checkvalue(obj,0)&&formatCurrency(obj,0));
}

function doSave(){

	if(document.getElementsByName("bm")[0].checked){
		var jzsde = document.getElementById("jmsde0").value;
		if(jzsde.length==0){
			alert("免征项目所得不能为空!");
			document.forms[0].jmsde0.focus();
			return false;
		}
		if(jzsde<=0){
			alert("免征项目所得必须大于0!");
			document.forms[0].jmsde0.focus();
			return false;
		}
	}

	if(document.getElementsByName("bm")[1].checked){
		var jzsde = document.getElementById("jmsde1").value;
		if(jzsde.length==0){
			alert("减半征收项目所得不能为空!");
			document.forms[0].jmsde1.focus();
			return false;
		}
		if(jzsde<=0){
			alert("减半征收项目所得必须大于0!");
			document.forms[0].jmsde1.focus();
			return false;
		}
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

	if(document.getElementsByName("bm")[0].checked){
		var jzsde = document.getElementById("jmsde0").value;
		if(jzsde.length==0){
			alert("免征项目所得不能为空!");
			document.forms[0].jmsde0.focus();
			return false;
		}
		if(jzsde<=0){
			alert("免征项目所得必须大于0!");
			document.forms[0].jmsde0.focus();
			return false;
		}
	}

	if(document.getElementsByName("bm")[1].checked){
		var jzsde = document.getElementById("jmsde1").value;
		if(jzsde.length==0){
			alert("减半征收项目所得不能为空!");
			document.forms[0].jmsde1.focus();
			return false;
		}
		if(jzsde<=0){
			alert("减半征收项目所得必须大于0!");
			document.forms[0].jmsde1.focus();
			return false;
		}
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
document.forms[0].jjkcje.value="";
document.forms[0].zfgz.value="";

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


function xzb(){

	if(document.getElementsByName("bm")[0].checked){
		//document.getElementById("tbblx").value = 0;
		//alert(document.getElementsByName("bm")[0].value);
		document.getElementById("TABLE_LIST1").style.display="block";
		document.getElementById("TABLE_LIST2").style.display="none";
		//document.getElementsByName("bm")[1].disabled="true"
	}

	if(document.getElementsByName("bm")[1].checked){
		//document.getElementById("tbblx").value = 1;
		document.getElementById("TABLE_LIST1").style.display="none";
		document.getElementById("TABLE_LIST2").style.display="block";
		//document.getElementsByName("bm")[0].disabled="true"
	}
}


</script>


<title>录入从事农、林、牧、渔业项目的所得税减免税备案事项</title>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="parseXmlOnLoad()">

<table width="100%" height="100%" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" colspan=2 valign="top"><jsp:include
			page="../../include/SbHeader.jsp" flush="true">
			<jsp:param name="name" value="录入纳税人企业所得税减免税备案申请" />
			<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm" />
		</jsp:include> <html:errors />


		<form name="Form1" method="post" action="/shenbao/basx05.dc"><input
			name="actionType" type="hidden" id="actionType"></input>
		<table width="90%" border="0" cellpadding="0" cellspacing="0"
			align="center">
			<!--  <input name="tbblx" type="hidden" id="tbblx">-->
			<tr>
				<td valign="top" class="title"><br>
				<table width="75%" cellspacing=0 border=0 class="table-99">
					<tr>
						<td class="1-td1">录入从事农、林、牧、渔业项目的所得税减免税备案事项</td>
					</tr>
					<tr>
						<td class="2-td2-center">免征企业所得税: <input type="radio"
							name="bm" id="bm1" value="1" onClick="xzb()" checked="checked">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;减半征收企业所得税:
						<input type="radio" name="bm" id="bm2" value="2" onClick="xzb()"></td>
					</tr>
					<tr>
						<td class="1-td2"><br>
						<div id="result"></div>
						<br>
						<table width="100%" border="0" align="center">
							<tr align="center">


								<td><img src="<%=static_contextpath%>images/baocun1.jpg"
									onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'"
									onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'"
									alt="保存" onclick="return doSave()" style="cursor:hand"></td>
								<td><img src="<%=static_contextpath%>images/b_scbab1.jpg"
									onmouseover="this.src='<%=static_contextpath%>images/b_scbab2.jpg'"
									onmouseout="this.src='<%=static_contextpath%>images/b_scbab1.jpg'"
									alt="生成备案表" onclick="doEditZb()" style="cursor:hand" width="95" height="22"></td>
								
								<td><img src="<%=static_contextpath%>images/fanhui1.jpg"
									onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'"
									onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'"
									alt="返回" onclick="doReturn()" style="cursor:hand"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>

				</td>
			</tr>
		</table>
		</form>
	<tr>
		<td valign="bottom" align="center"><br>
		<jsp:include page="../../include/bottom.jsp" flush="true">
		</jsp:include></td>
	</tr>
</body>

</html>
