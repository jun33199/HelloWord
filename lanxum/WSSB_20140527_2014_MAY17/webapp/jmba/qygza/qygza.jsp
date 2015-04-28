<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);


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
<title>��ҵ�������ڻ������������ܽ�ˮ����ȫ������ר���豸Ͷ�ʶ����Ӧ��˰���ʸ񱸰�����</title>
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

var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';

var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';

function parseXmlOnLoad()
{
	var xslPath='jmba/qygza/qygza.xsl';

	if (strXml != "")
    {
        //alert(strXml);
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    return true;
}


function doAdd(){
//    alert("add");
    document.forms[0].actionType.value="Add";
    document.forms[0].submit();
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

function doDel(selIndex){
    if(confirm("��ȷ��ɾ��")){
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
</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="��ҵ�������ڻ������������ܽ�ˮ����ȫ������ר���豸Ͷ�ʶ����Ӧ��˰���ʸ񱸰�����" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/qygza15.dc">
	<input name="actionType" type="hidden" id="actionType">
    <input name="selIndex" type="hidden" id="selIndex">
<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9'>
 <tr>
    <td valign="top">
      <table align="center" cellspacing="0" class="table-99">
<tr>
                <td class="1-td1">
                ��ҵ�������ڻ������������ܽ�ˮ����ȫ������ר���豸Ͷ�ʶ����Ӧ��˰���ʸ񱸰�����
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
                      	<img src="<%=static_contextpath%>images/xinzeng1.jpg"  name="shanchu11" width="79" height="22" border="0" id="shanchu11"  alt="����"

onclick="javascript:return doAdd();">
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
            <table width="99%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td> <hr width="100%" size="1" style="color='#003399'"> </td>
                <td width="99" align="center" class="black9"><strong><font color="#0000FF">ע
                  �� �� ��</font></strong></td>
                <td> <hr width="100%" size="1" style="color='#003399'"> </td>
              </tr>
            </table>
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
