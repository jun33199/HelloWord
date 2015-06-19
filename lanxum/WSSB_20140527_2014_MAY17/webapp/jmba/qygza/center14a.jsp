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
        alert("����ר���豸��Ͷ�ʶ�������0��");
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
	getSbsj(objForm);

	//ȥ��ĩβ�Զ���ӵĻس�
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

//�����걨����
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
        alert("������4λ�������!");
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
    		<jsp:param name="name" value="¼�봴ҵͶ����ҵͶ�ʡ��ֿ�Ӧ��˰���ö��ʸ񱸰�����" />
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
                ¼�봴ҵͶ����ҵͶ�ʡ��ֿ�Ӧ��˰���ö��ʸ񱸰�����
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

onclick="javascript:return doSave();">
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
            <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1"
                  bordercolor="#FFFFFF" class="black9">
                    <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                      <td height="47">
                        <br>
                        &nbsp;&nbsp;1������ҵ���ò�ʵ��ʹ�õ�ר���豸���͡���Ϊ��ѡ�ͨ�������˵�ѡ��һ���ѡ�����ֱ�Ϊ����������ר���豸���������ܽ�ˮר���豸��������ȫ����ר���豸��
                        <br>
                        &nbsp;&nbsp;2������ҵ���ò�ʵ��ʹ�õ�ר���豸���ơ���Ϊ��������ݡ���������ר���豸��ҵ����˰�Ż�Ŀ¼���������ܽ�ˮר���豸��ҵ����˰�Ż�Ŀ¼��������ȫ����ר���豸��ҵ����˰�Ż�Ŀ¼��������豸���ơ�
                        <br>
                        &nbsp;&nbsp;3����������ȡ���Ϊ������������ʱ���2008�����ɣ����������-1���Զ�������ϵͳ�������������������Ϊ2010�꣬ϵͳ����Ϊ2009��ȣ����Ժ����ϵͳҲ�Զ�������
                        <br>
                        &nbsp;&nbsp;4���Ƿ��й���ר���豸��Ʊ���嵥
                        <br>
                        &nbsp;&nbsp;5���Ƿ�����ר���豸�̶��ʲ�ʹ�ÿ�Ƭ��ר���豸�̶��ʲ���ϸ��
                        <br>
                        &nbsp;&nbsp;6��ר���豸����Ŀ¼�涨�Ļ������������ܽ�ˮ����ȫ������ר���豸������
                        <br>
                        &nbsp;&nbsp;7���Ƿ���ר���豸ʹ���������
                        <br>
                        &nbsp;&nbsp;8������ר���豸��Ͷ�ʶΪ�������λԪ��
                        <br>
                        &nbsp;&nbsp;9��������ר���豸Ͷ�ʶ�ɵ����Ӧ��˰���Ϊ�Զ�������ɡ�ר���豸Ͷ�ʶ*10%�Զ����ɡ�
                        <br>
                        &nbsp;&nbsp;10������˰�����Ҫ���͵��������ϣ��ɲ�����Ա�ֹ�¼��ϵͳ��
                        <br>
                        <br>
                        &nbsp;&nbsp;3.1 �������Ŀ�б�������δ��д������˰���ύ���롣
                        <br>
                        &nbsp;&nbsp;3.2 ����ڶԡ���ҵ�������ڻ������������ܽ�ˮ����ȫ������ר���豸Ͷ�ʶ����˰���ʸ񱸰������ȫ�� ����ҵ�������ڻ������������ܽ�ˮ����ȫ������ר���豸Ͷ�ʶ����˰���������дȨ�޽��ſ����������
                        <br>
                        &nbsp;&nbsp;3.3 ����ѡ���Ƿ�������У����κ�һ��ѡ���������˰���ύ���롣
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
