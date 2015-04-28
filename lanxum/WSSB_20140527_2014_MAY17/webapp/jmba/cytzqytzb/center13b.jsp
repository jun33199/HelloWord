<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.GXJSLY"%>
<%@page import="java.util.*"%>

<%
String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
List codeTable1 = (List)session.getAttribute("codeTable1");
List codeTable2 = (List)session.getAttribute("codeTable2");
String bussStr = (String)session.getAttribute("buss");
String selIndex = (String)session.getAttribute("selIndex");
String size = (String)session.getAttribute("size");
String zcbashbj = (String)session.getAttribute("zcbashbj");
String wnwsh = (String)session.getAttribute("wnwsh");
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
<title>¼�봴ҵͶ����ҵͶ�ʡ��ֿ�Ӧ��˰���ö��ʸ񱸰�����</title>
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
//    Object obj = codeTable1.get(i);
	GXJSLY tmpl = (GXJSLY)codeTable1.get(i);
//    System.out.println("tmpl = "+obj.getClass());
	out.println("codeTable1["+i+"] = [\""+tmpl.getGXJSLYDM()+"\",\""+tmpl.getGXJSLYMC()+"\"];");
}

%>


var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';

var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
function parseXmlOnLoad()
{
	var xslPath="";
    var bs = "";
    //if("<%=bussStr%>"=="Save"){
        xslPath='jmba/cytzqytzb/center13b.xsl';
    //}else{
    //    xslPath='jmba/cytzqytzb/center13b1.xsl';
    //}
    //alert(xslPath);
	if (strXml != "")
    {
        //alert(strXml);
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    insertCodeTablel();
    checkIsZcba();
    return true;
}

function formatKsslJsje(obj)
{
	//alert("kssljsje");
	return (checkvalue(obj,0)&&formatCurrency(obj,0));
}

function checkIsZcba(){
    if(<%=zcbashbj%>=="0"){
        document.getElementById("BTZQYSWDJZH").disabled=true;
        document.getElementById("BTZQYMC").disabled=true;
        document.getElementById("BTZQYJSJDM").disabled=true;
        document.getElementById("GXJSLYDM").disabled=true;
        var rows = document.getElementById("mxvosize").value;
        rows = parseInt(rows)-1;
        var startYear = "2006";
        //alert(rows);
        for(var i =0;i<rows;i++){
            var firstYear = parseInt(startYear) + i;
            //alert(firstYear);
            //alert(document.getElementById("TZE"+firstYear));
            document.getElementById("TZE"+firstYear).disabled=true;
            if(firstYear > 2007){
                document.getElementById("DKE"+firstYear).disabled=true;
            }
        }

    }
}

function insertCodeTablel()
{
var obj1=document.getElementById("zxgxjsqylydmdiv");

var select1 = document.createElement("select");

        var ooption = new Array();
        for(var i=0;i<codeTable1.length;i++)
        {
               select1.options[i] = new Option(codeTable1[i][1], codeTable1[i][0]);
        }
        select1.id="GXJSLYDM";
        obj1.appendChild(select1);
}


function getPostXml(objForm)
{	//alert("88888");
	var retstr;
	//��������
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//��˰����Ϣ
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);

	applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc","bajmsehbl"
    ,"fhwjmc","qsrq","ztdm","ztmc","jzrq","szdm","szmc","bsfsdm","bsfsmc"]);
	//�걨����
    //alert("1111");
	getSbsj(objForm);

	//ȥ��ĩβ�Զ���ӵĻس�
	//alert(g_Doc.XMLHeader);
	//alert(g_Doc.XMLDoc.xml);
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	//alert(retstr);
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

//�����걨����
function getSbsj(objForm)
{
    //alert("1111111111111111");
	var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
	var objTemp = null;
    var rows = document.getElementById("mxvosize").value;
    var startYear = "2006";
    for(var i =0;i<rows;i++){
        //alert(i);
        var firstYear = parseInt(startYear) + i;
        //alert(firstYear);
        objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
        objNode.appendChild(objTemp);
        var xh = document.getElementById("XH"+firstYear).value;
        //alert(document.getElementById("XH"+firstYear));
        //alert(xh);
        setNdChildren(objTemp,"XH",xh);
        getChildren(objTemp,"BASQWSH");
        getChildren(objTemp,"JSJDM");
        getChildren(objTemp,"BAND");
        getChildren(objTemp,"SWJGZZJGDM");
        getChildren(objTemp,"GXJSLYDM");
        getChildren(objTemp,"BTZQYJSJDM");
        getChildren(objTemp,"BTZQYSWDJZH");
        getChildren(objTemp,"BTZQYMC");
        getChildren(objTemp,"BTZQYSSD");
        var tze = document.getElementById("TZE"+firstYear).value;
        setNdChildren(objTemp,"TZE",tze);
        if(firstYear == 2006){
            setNdChildren(objTemp,"DKE","");
            //alert("2006");
        }else if(firstYear == 2007){
            setNdChildren(objTemp,"DKE","");
            //alert("2007");
        }else{
            var dke = document.getElementById("DKE"+firstYear).value;
            setNdChildren(objTemp,"DKE",dke);
        }
        setNdChildren(objTemp,"TZND",firstYear);
        getChildren(objTemp,"SHBJ");
        getChildren(objTemp,"ZCBA");
        getChildren(objTemp,"CJR");
        getChildren(objTemp,"CJRQ");
        getChildren(objTemp,"LRR");
        getChildren(objTemp,"LRRQ");
    }



}


function setNdChildren(temp,strTag,je)
{
	var element=document.getElementById(strTag);
	if(element!=null)
	{
		var objTemp=temp;
		var node=g_Doc.XMLDoc.createElement(strTag);
		objTemp.appendChild(node);
		var objCDATA = g_Doc.XMLDoc.createCDATASection(je);
		node.appendChild(objCDATA);
	}

}
function dofanhui(){
    //document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
}

function doSave1(){
    var commitType = document.forms[0].buss.value;
    var bazt = document.forms[0].zcbashbj.value;
    alert(commitType+"   "+bazt);
    // || (commitType == "Save" && bazt =="0")
    if(commitType == "Update"){
        doSave();
    }else{
        checkSaveInfo();
    }
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

		document.forms[0].actionType.value=commitType;
        alert(document.forms[0].zcbashbj.value);
        document.forms[0].zcbashbj.value = "0";
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

function checkBtzqyJsjdm(){
    var btzjsjdm = document.getElementById("BTZQYJSJDM").value;

    if(btzjsjdm == null || btzjsjdm == "" ){
        alert("��Ͷ����ҵ��������벻��Ϊ��!");
    }
    var xmlhttp_request;
    if(window.ActiveXObject){
        xmlhttp_request=new ActiveXObject("Microsoft.XMLHTTP");
    }else if(window.XMLHttpRequest){
        xmlhttp_request=new XMLHttpRequest();
    } else {
        return;
    }

    xmlhttp_request.open("GET", "/shenbao/cytzqytzb14.dc?actionType=CheckBtzqyJsjdm&jsjdm="+btzjsjdm, true);
    xmlhttp_request.send(null);
    xmlhttp_request.onreadystatechange =function zdyprocessAjaxResponse(){
        var xmlDOM;
        if (xmlhttp_request.readyState == 4) {
            if (xmlhttp_request.status == 200) {

                xmlDOM = xmlhttp_request.responseXML;

                var cjx_length;

                cjx_length = xmlDOM.getElementsByTagName("btznsrxx").length;
                for (k=0; k<cjx_length;k++) {
                    var sfcz = xmlDOM.getElementsByTagName("sfcz")[k].firstChild.data;
                    var rrturnmsg = xmlDOM.getElementsByTagName("rrturnmsg")[k].firstChild.data;
                    var btzSwdjh = xmlDOM.getElementsByTagName("btzswdjzh")[k].firstChild.data;
                    var btzmc = xmlDOM.getElementsByTagName("btznsrmc")[k].firstChild.data;
                    if(sfcz == '0'){
                        document.getElementById("btzqyJsjdmDiv").innerHTML=rrturnmsg;
                        document.getElementById("BTZQYSWDJZH").disabled=false;
                        document.getElementById("BTZQYMC").disabled=false;
                        document.getElementById("BTZQYSSD").value="1";
                    }else{
                        document.getElementById("btzqyJsjdmDiv").innerHTML=rrturnmsg;
                        document.getElementById("BTZQYSWDJZH").value=btzSwdjh;
                        document.getElementById("BTZQYMC").value=btzmc;
                        document.getElementById("BTZQYSSD").value="0";
                        document.getElementById("BTZQYSWDJZH").disabled=true;
                        document.getElementById("BTZQYMC").disabled=true;
                    }
                }
            }
        }
    }
}


function checkSaveInfo(){
   // alert("1!");
    var btzjsjdm = document.getElementById("BTZQYJSJDM").value;
    var GXJSLYDM = document.getElementById("GXJSLYDM").value;
    var BTZQYSWDJZH = document.getElementById("BTZQYSWDJZH").value;
    if(btzjsjdm == null || btzjsjdm == "" ){
        alert("��Ͷ����ҵ��������벻��Ϊ��!");
    }
    var xmlhttp_request;
    if(window.ActiveXObject){
        xmlhttp_request=new ActiveXObject("Microsoft.XMLHTTP");
    }else if(window.XMLHttpRequest){
        xmlhttp_request=new XMLHttpRequest();
    } else {
        return;
    }
//alert("/shenbao/cytzqytzb14.dc?actionType=Check&jsjdm="+btzjsjdm+"&swdjzh="+BTZQYSWDJZH+"&lxdm="+GXJSLYDM);
    xmlhttp_request.open("GET", "/shenbao/cytzqytzb14.dc?actionType=Check&jsjdm="+btzjsjdm+"&swdjzh="+BTZQYSWDJZH+"&lxdm="+GXJSLYDM, true);
    xmlhttp_request.send(null);
    xmlhttp_request.onreadystatechange =function zdyprocessAjaxResponse(){
       // alert("1!");
        var xmlDOM;
        if (xmlhttp_request.readyState == 4) {
            if (xmlhttp_request.status == 200) {

                xmlDOM = xmlhttp_request.responseXML;

                var cjx_length;

                cjx_length = xmlDOM.getElementsByTagName("btznsrxx").length;
                for (k=0; k<cjx_length;k++) {
                    var sfcz = xmlDOM.getElementsByTagName("sfcz")[k].firstChild.data;
                    var rrturnmsg = xmlDOM.getElementsByTagName("rrturnmsg")[k].firstChild.data;
                    //alert(sfcz);
                    if(sfcz == '2'){
                        document.getElementById("btzqyJsjdmDiv").innerHTML=rrturnmsg;
                    }else{
                        doSave();
                    }
                }
            }
        }
    }
}

</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="parseXmlOnLoad();">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="¼�봴ҵͶ����ҵ�ֿ�Ӧ��˰���ö������" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/cytzqytzb14.dc">
	<input name="actionType" type="hidden" id="actionType">
    <input name="zcbashbj" type="hidden" id="zcbashbj" value="<%=zcbashbj%>">
    <input name="buss" type="hidden" id="buss" value="<%=bussStr%>">
    <input name="selIndex" type="hidden" id="selIndex" value="<%=selIndex%>">
    <input name="mxvosize" type="hidden" id="mxvosize" value="<%=size%>">
    <input name="checkinfo" type="hidden" id="checkinfo">
    <input name="wnwsh" type="hidden" id="wnwsh" value="<%=wnwsh%>">
<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9'>
 <tr>
    <td valign="top">
      <table align="center" cellspacing="0" class="table-99">
<tr>
                <td class="1-td1">
                ¼�봴ҵͶ����ҵ�ֿ�Ӧ��˰���ö������
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
                      	<img src="<%=static_contextpath%>images/baocun2.jpg"  name="shanchu11" width="79" height="22" border="0" id="shanchu11"  alt="����"

onclick="javascript:return doSave1();">
                      </td>


                      <td >
                      	<img src="<%=static_contextpath%>images/tijiao2.jpg" name="shanchu11" width="79" height="22" border="0" id="shanchu11" alt="�ύ"

onclick="javascript:return doCommit();">
                      </td>

                      <td >
                        <img onclick="javascript:return dofanhui();" alt="�˳�" name="tc1" width="79" height="22" border="0" id="tc1" src="<%=static_contextpath%>images/fanhui2.jpg">
                      </td>
                    </tr>
                  </table>
            <br>
            <br>
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
												<br>&nbsp;&nbsp;<font color="#FF0000">��ҵ����д2009������ʱ��Ӧ����2006��2008��������ݣ���2010�꿪ʼ��ֻ��д�������ݡ���Ҫȷ�������й��ڴ˶ε�����</font>
												<br>&nbsp;&nbsp;<font color="#FF0000">��Ӧ��ֿ۶��Ƿ��¼��Ҫ�ж϶�Ӧ��-2 �Ƿ���Ͷ�ʶ�</font>
												<br>&nbsp;&nbsp;<font color="#FF0000">���ÿ����Ͷ����ҵ�ļ�����򣺵ֿ���������=����ϵͳ���-3��֮ǰ��� ��Ͷ�ʶ�ϼƣ�*70% - ����ϵͳ���-2��֮ǰ��� �ĵֿ۶�ϼƣ�У��ʱ������ƫ�+-5��</font>
												<br>&nbsp;&nbsp;<font color="#FF0000">�������ߵ���ҳ��ֻչʾ�����������ݣ��Ժ��������ʱ˳��������һ��</font>
												<br>
                        <br>
                        &nbsp;&nbsp;1����˰�˼�������룺�ֹ�¼�봴ҵͶ����ҵͶ�ʵı�Ͷ����ҵ���������
                        <br>
                        &nbsp;&nbsp;2����˰�����ƣ��ֹ�¼�봴ҵͶ����ҵͶ�ʵı�Ͷ����ҵ����
                        <br>
                        &nbsp;&nbsp;3��2007�ꡢ2008�ꡢ2009��Ͷ�ʶ�ֹ�¼�봴ҵͶ����ҵ��ȡ��ȨͶ�ʷ�ʽͶ����δ���е���С���¼�����ҵ��Ͷ�ʶ�
                        <br>
                        &nbsp;&nbsp;4��2008��ֿ�Ӧ��˰���ö�ֹ�¼�봴ҵͶ����ҵ��2008�갴Ͷ�ʶ��70%�ֿ۵����Ӧ��˰���öΪ��˰��������д
                        <br>
                        &nbsp;&nbsp;5��2009��ֿ�Ӧ��˰���ö�ֹ�¼�봴ҵͶ����ҵ��2009�갴Ͷ�ʶ��70%�ֿ۵����Ӧ��˰���öΪ��˰��������д
                        <br>
                        &nbsp;&nbsp;6�����꣺Ϊϵͳ���-1���Զ�����
                        <br>
                        &nbsp;&nbsp;7������Ͷ�ʶ��д��ҵͶ����ҵ��ȡ��ȨͶ�ʷ�ʽͶ����δ���е���С���¼�����ҵ��Ͷ�ʶΪ��˰��������д
                        <br>
                        &nbsp;&nbsp;8������ֿ�Ӧ��˰���ö��д��ҵͶ����ҵ�ڵ��갴Ͷ�ʶ��70%�ֿ۵����Ӧ��˰���öΪ��˰��������д
                        <br>
                        &nbsp;&nbsp;ע����ҵ����д2009������ʱ��Ӧ����2006��2008��������ݣ���2010�꿪ʼ��ֻ��д�������ݡ�
                        <br>
                        <br>
                        &nbsp;&nbsp;3.1 �Դ�ҵͶ����ҵ�ֿ�Ӧ��˰���ö��ʸ񱸰���д��ȫ�������д�˱�
                        <br>
                        &nbsp;&nbsp;3.2 ��˰��ֻ���ύ�������벢��˰�������˳ɹ��󷽿ɿ��Ÿ���5��39�� �������ڻ������ʱ��д���ݣ����򣬶�Ӧ�д�ϵͳĬ��Ϊ������༭״̬
                      </td>
                    </tr>
                  </table>
                  <br>
                </td>
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
