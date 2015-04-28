<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.ZYSBLX"%>
<%@page import="java.util.*"%>

<%
String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
List codeTable1 = (List)session.getAttribute("codeTable1");
List codeTable2 = (List)session.getAttribute("codeTable2");
String bussStr = (String)session.getAttribute("buss");
String selIndex = (String)session.getAttribute("selIndex");
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
<html>
<head>
<title>录入创业投资企业投资、抵扣应纳税所得额资格备案事项</title>
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
<script language="JavaScript">

var codeTable1= new Array(<%=codeTable1.size()%>);

<%
for(int i=0;i<codeTable1.size();i++)
{
	ZYSBLX tmpl = (ZYSBLX)codeTable1.get(i);
	out.println("codeTable1["+i+"] = [\""+tmpl.getZYSBLXDM()+"\",\""+tmpl.getZYSBLXMC()+"\"];");
}

%>


var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';

var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
function parseXmlOnLoad()
{
	var xslPath='jmba/qygza/center14a.xsl';

	if (strXml != "")
    {
//        alert(strXml);
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    insertCodeTablel();
    return true;
}

function doYnse(){
    var tzeVal=document.getElementById("tze").value;
//    alert(tzeVal);
    if(tzeVal == 0 || tzeVal == "0.00"){
        alert("购置专用设备的投资额必须大于0！");
    }
    var dmynseVal=document.getElementById("dmynse");
    dmynseVal.value  = parseFloat(tzeVal) * 0.10;
}



function insertCodeTablel()
{
var obj=document.getElementById('zysblxdmdiv');
//alert(obj);

var select1 = document.createElement("select");

        var ooption = new Array();
        for(var i=0;i<codeTable1.length;i++)
        {
               select1.options[i] = new Option(codeTable1[i][1], codeTable1[i][0]);
        }
        select1.id="zysblxdm";
        obj.appendChild(select1);
}


function getPostXml(objForm)
{	//alert("88888");
	var retstr;
	//基本数据
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//纳税人信息
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);

	applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc","bajmsehbl"
    ,"fhwjmc","qsrq","ztdm","ztmc","jzrq","szdm","szmc","bsfsdm","bsfsmc"]);
	//申报数据
	getSbsj(objForm);

	//去掉末尾自动添加的回车
	//alert(g_Doc.XMLHeader);
	//alert(g_Doc.XMLDoc.xml);
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	alert(retstr);
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
	   if(strTag=="sfgmfpjqd" || strTag=="sfsykphmxz" ||strTag=="sfsygdsm" ||strTag=="sfsyqksm" ){

           if(document.getElementsByName(strTag)[0].checked){
               strValue=formString("1");
           }else{
               strValue=formString("0");
           }
           alert(strTag +" "+strValue);
	  }else{
		strValue=formString(element.value);
      }
//		 alert(element+strValue);
        //alert(strValue);
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
	getChildren(objTemp,"basqwsh");
	getChildren(objTemp,"jsjdm");
	getChildren(objTemp,"band");
    getChildren(objTemp,"swjgzzjgdm");
	getChildren(objTemp,"zysblxdm");
	getChildren(objTemp,"zysbmc");
	getChildren(objTemp,"gznd");
	getChildren(objTemp,"sfgmfpjqd");
	getChildren(objTemp,"sfsykphmxz");
	getChildren(objTemp,"sfsygdsm");
	getChildren(objTemp,"sfsyqksm");
	getChildren(objTemp,"tze");
	getChildren(objTemp,"dmynse");
    getChildren(objTemp,"qtzl");
	getChildren(objTemp,"cjr");
    getChildren(objTemp,"cjrq");
    getChildren(objTemp,"lrr");
	getChildren(objTemp,"lrrq");




}

function doChengNd(){

    var pageHlnd=document.getElementById("gznd").value;
    if(pageHlnd.length != 4){
        alert("请输入4位年度数字!");
        document.getElementById("gznd").focus();
        return;
    }
}

function formatKsslJsje(obj)
{
	return (checkvalue(obj,0)&&formatCurrency(obj,0)) && doYnse();
}

function dofanhui(){
    document.forms[0].actionType.value="Return";
	document.forms[0].submit();
}

function doSave(){
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
        var commitType = document.forms[0].buss.value;
        //alert(commitType);return;
		document.forms[0].actionType.value=commitType;
			if (g_objSI.Container != '')
			{  //alert("4");
				if (!doSubmitXML(document.forms[0],commitType,true,"",true))
				{  //alert("5");
					 document.forms[0].ywczlx.value = old;
					return false;
				}
			}
			else
			{  //alert("6");
			   if(!doSubmitXML(document.forms[0],commitType,false,"",true))
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
         var commitType = document.forms[0].buss.value;
		document.forms[0].actionType.value=commitType;
			if (g_objSI.Container != '')
			{
				if (!doSubmitXML(document.forms[0],commitType,true,"",true))
				{
					 document.forms[0].ywczlx.value = old;
					return false;
				}
			}
			else
			{
			   if(!doSubmitXML(document.forms[0],commitType,false,"",true))
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



</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="录入创业投资企业投资、抵扣应纳税所得额资格备案事项" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/qygza15.dc">
	<input name="actionType" type="hidden" id="actionType">
    <input name="buss" type="hidden" id="buss" value="<%=bussStr%>">
    <input name="selIndex" type="hidden" id="selIndex" value="<%=selIndex%>">
<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9'>
 <tr>
    <td valign="top">
      <table align="center" cellspacing="0" class="table-99">
<tr>
                <td class="1-td1">
                录入创业投资企业投资、抵扣应纳税所得额资格备案事项
                </td>
              </tr>
              <tr>
                <td class="1-td2">

                  <br>
				<div id="result"></div>
            </td>



    </tr>
    <tr>

                  <table width="100%" border="0" align="center">
                    <tr align="center">

                    	<td >
                      	<img src="<%=static_contextpath%>images/baocun2.jpg"  name="shanchu11" width="79" height="22" border="0" id="shanchu11"  alt="保存"

onclick="javascript:return doSave();">
                      </td>


                      <td >
                      	<img src="<%=static_contextpath%>images/tijiao2.jpg" name="shanchu11" width="79" height="22" border="0" id="shanchu11" alt="提交"

onclick="javascript:return doCommit();">
                      </td>

                      <td >
                        <img onclick="javascript:return dofanhui();" alt="退出" name="tc1" width="79" height="22" border="0" id="tc1" src="<%=static_contextpath%>images/fanhui2.jpg">
                      </td>
                    </tr>
                  </table>
            <br>
            <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1"
                  bordercolor="#FFFFFF" class="black9">
                    <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                      <td height="47">
                        <br>
                        &nbsp;&nbsp;1、“企业购置并实际使用的专用设备类型”：为必选项。通过下拉菜单选择一项。可选项共三项分别为“环境保护专用设备”，“节能节水专用设备”，“安全生产专用设备”
                        <br>
                        &nbsp;&nbsp;2、“企业购置并实际使用的专用设备名称”：为必填项。根据《环境保护专用设备企业所得税优惠目录》、《节能节水专用设备企业所得税优惠目录》，《安全生产专用设备企业所得税优惠目录》填报具体设备名称。
                        <br>
                        &nbsp;&nbsp;3、“购置年度”：为必填项。购置年度时间除2008年外由（服务器年度-1）自动带出且系统锁死（例：服务器年度为2010年，系统带出为2009年度），以后年度系统也自动带出。
                        <br>
                        &nbsp;&nbsp;4、是否有购买专用设备发票及清单
                        <br>
                        &nbsp;&nbsp;5、是否设置专用设备固定资产使用卡片或专用设备固定资产明细帐
                        <br>
                        &nbsp;&nbsp;6、专用设备属于目录规定的环境保护、节能节水、安全生产等专用设备的声明
                        <br>
                        &nbsp;&nbsp;7、是否有专用设备使用情况声明
                        <br>
                        &nbsp;&nbsp;8、购置专用设备的投资额：为必填项。单位元。
                        <br>
                        &nbsp;&nbsp;9、“购置专用设备投资额可抵免的应纳税额”：为自动生成项。由“专用设备投资额”*10%自动生成。
                        <br>
                        &nbsp;&nbsp;10、主管税务机关要求报送的其他资料，由操作人员手工录入系统。
                        <br>
                        <br>
                        &nbsp;&nbsp;3.1 本表各栏目中必填项如未填写则不能向税务提交申请。
                        <br>
                        &nbsp;&nbsp;3.2 填报人在对“企业购置用于环境保护、节能节水、安全生产等专用设备投资额抵免税额资格备案表”填报完全后， “企业购置用于环境保护、节能节水、安全生产等专用设备投资额抵免税额备案表”在填写权限将放开，允许填报。
                        <br>
                        &nbsp;&nbsp;3.3 表中选择是否的条件中，有任何一条选择否，则不能向税务提交申请。
                      </td>
                    </tr>
                  </table>
                  <br> </td>
					</tr>
				</table>
    </tr>
  </table>
    </td>
 </tr>
</table>
</form>
 </td>
</tr>
<tr><td valign="bottom" align="center">
<br>
<jsp:include page="../../include/bottom.jsp" flush="true" >
</jsp:include>
 </td>
</tr>
</table>
</body>
</html>
