<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="java.util.*"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.JSZRLX"%>
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
String ymlx =(String)request.getSession().getAttribute("XSLLX07");
%>

<html>
<head>
<title>���������ļ���װ������</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/date.js"></script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">

<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
<script language="JavaScript">


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
var hje = '<%=request.getSession().getAttribute("HJE")==null?"0":request.getSession().getAttribute("HJE")%>';
var tempxsllx = '<%=request.getSession().getAttribute("XSLLX07")==null?"":request.getSession().getAttribute("XSLLX07")%>';


function parseXmlOnLoad()
{   var xslPath=''
  if(tempxsllx=='VIEW'){
	 xslPath='jmba/fhtjjszrsd07/fhtjjszrsdlistview07.xsl';
	 }else{
	 xslPath='jmba/fhtjjszrsd07/fhtjjszrsdlist07.xsl';
	 }

	if (strXml != "")
    {   // alert(strXml);
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    jshje();
    return true;
}
function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
  if(tempxsllx=='VIEW'){
	document.forms[0].actionType.value="View";
	}else{
	document.forms[0].actionType.value="Edit";
	}
	document.forms[0].submit();
}
function jshje(){
//alert(document.getElementById("hje"));
document.getElementById("hje").innerHTML=hje;
}
function doAdd(){
    //alert("add");
    document.form1.actionType.value="Add";
    
   // alert(document.form1.actionType.value);
    document.form1.submit();
}
function doDel(selIndex){
if(confirm("ȷ��ɾ���������ݣ�")){
    document.forms[0].selIndex.value=selIndex;
    document.forms[0].actionType.value="Del";
    document.forms[0].submit();
    }
}

function doEditor(selIndex){
    document.forms[0].selIndex.value=selIndex;
    document.forms[0].actionType.value="Editor";
    document.forms[0].submit();
}

function doCommit(){
	document.forms[0].actionType.value="Commit";
    document.forms[0].submit();
}


</script>


</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="/include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="���������ļ���ת�����ü�����ҵ����˰��������" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/fhtjjszrsd.dc">
		<input name="actionType" type="hidden" id="actionType">
    <input name="selIndex" type="hidden" id="selIndex">
    
<table  width="100%"  border="0" cellpadding="0" cellspacing="0" align="center" >
	<tr>
<td valign="top" class="title"> <br>

      <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
        <tr>
          <td class="1-td1" >���������ļ���ת�����ü�����ҵ����˰��������</td>
        </tr>
        <tr>
          <td class="1-td2">
		      
        <br>


            <div id="result"></div>
                 <table width="100%" border="0" align="center">
                    <tr align="center">
  <%if (!"VIEW".equals(ymlx)){%>
                    	<td >
                      	<img src="<%=static_contextpath%>images/xinzeng2.jpg"  name="xinzeng11" width="79" height="22" border="0" id="shanchu11"  alt="����" 

onclick="javascript:return doAdd();">
                      </td>


                      <td >
                      	<img src="<%=static_contextpath%>images/tijiao2.jpg" name="xinzeng11" width="79" height="22" border="0" id="shanchu11" alt="�ύ" 

onclick="javascript:return doCommit();">
                      </td>
 <%}%>
                      <td >
                        <img onclick="doReturn()" alt="�˳�" name="tc1" width="79" height="22" border="0" id="tc1" src="<%=static_contextpath%>images/fanhui2.jpg">
                      </td>
                    </tr>
                  </table>
            <br>
            <table width="99%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td> <hr width="100%" size="1" style="color='#003399'"> </td>
                <td width="99" align="center" class="black9"><strong><font color="#0000FF">ע
                  �� �� ��</font></strong></td>
                <td> <hr width="100%" size="1" style="color='#003399'"> </td>
              </tr>
            </table>
            <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
              <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                    <td height="47">
<br>&nbsp;&nbsp;1�Ƿ����ڷ��������ļ���ת�����ã�������������˰�����в��ύ����˰����ء�
<br>&nbsp;&nbsp;2����ѧ�������������϶��ǼǼ���ת�ú�ͬΪ��¼���ѡ���л��ޣ����ṩֽ�����ϲ��ύ����˰����ء�
<br>&nbsp;&nbsp;3����ת�ú�ͬ�϶��Ǽ�֤�����϶��ǼǱ�Ϊ��¼���ѡ���л��ޣ����ṩֽ�����ϲ��ύ����˰����ء�
<br>&nbsp;&nbsp;4ʵ�ʷ����ļ�����������ϸ��Ϊ��¼���ѡ���л��ޣ����ṩֽ�����ϲ��ύ����˰����ء�
<br>&nbsp;&nbsp;5��Ŀ���ú����������Ϊ�����ѡ���л��ޣ������ύ����˰����ء�
<br>&nbsp;&nbsp;6����˰�����Ҫ���͵���������Ϊ��ѡ��¼����ɲ�����Ա�ֹ�¼��ϵͳ��
<br>&nbsp;&nbsp;7ȡ�ü���ת������Ϊ��¼����ɲ�����Ա�ֹ�¼�롣
<br><br>
<br>&nbsp;&nbsp;3��1�������б�¼�����Ϊ�գ���δ¼������������˰������ύ���롣
<br>&nbsp;&nbsp;3��2����˰���걨����˰������ͨ�����ʱ�����л����񹲺͹���ҵ����˰����걨��A�ࡱ����5��˰���Ż���ϸ����31�п��ţ�������д���ݣ����򣬶�Ӧ

�д�ϵͳĬ��Ϊ������༭״̬��
</td>
			  </tr>
            </table><br> </td>
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
<%@ include file="/include/bottom.jsp" %>
 </td>
</tr>
</table>
</body>
</html>