<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml" %>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeTable"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.YFFYLY"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
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
	var xslPath='/XSLTWEB/model/030002/XSLT/yjkffy02viewdetail.xsl';

	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    
    return true;
}

 
 
function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
}

function doViewZb()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="ViewZb";
	document.forms[0].submit();
}
</script>


   <title>
      �����¼������²�Ʒ���¹��շ������о��������ñ�������
    </title>
 
  </head>
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
 onload="parseXmlOnLoad()">
  
  <table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="¼����˰����ҵ����˰����˰��������" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>
 

    <form name="Form1" method="post" action="/shenbao/yjkffy02.dc">
 	<input name="actionType" type="hidden" id="actionType"></input>
       <table width="770" border="0" cellpadding="0" cellspacing="0" align="center" >
        <tr>
          <td valign="top" class="title">
            <br>
            <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
              <tr>
                <td class="1-td1">
                  �����¼������²�Ʒ���¹��շ������о��������ñ�������
                </td>
              </tr>
              <tr>
                <td class="1-td2">
                  
                  <br>                 
<div id="result"></div>
                  <br>
               
               
                  <table width="100%" border="0" align="center">
                    <tr align="center">  
                    	 
                               <td>
                               <img src="<%=static_contextpath%>images/b_ckbab1.jpg" onmouseover="this.src='<%=static_contextpath%>images/b_ckbab2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/b_ckbab1.jpg'" alt="�鿴������" onclick="return doViewZb()" style="cursor:hand">
                      </td>
                      <td>
                      </td>
                      <td>
                        <img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="doReturn()" style="cursor:hand">
                      </td>
                    </tr>
                  </table>
                  <br>
                  <!--
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
                     &nbsp;&nbsp; 1.��Ŀ���ƣ����ղ�ͬ������Ŀ�������У�����Ϊ��¼�����˰�����ύ����ʱ¼��ϵ��
					<br>
					&nbsp;&nbsp;2.�Ƿ���������ί�С������о�������Ŀ�ƻ�����о���������Ԥ�㣺ѡ�� ���ǡ��� ���񡱣�		����Ϊ��ѡ�����˰�����ύ����ʱ¼��ϵͳ��
					<br>
					&nbsp;&nbsp;3.�Ƿ���������ί�С������о�����ר�Ż�������Ŀ��ı��������רҵ��Ա������ѡ�� ��		�ǡ��� ���񡱣�����Ϊ��ѡ�����˰�����ύ����ʱ¼��ϵͳ��
					<br>
					&nbsp;&nbsp;4.�Ƿ���������ί�С������о�������Ŀ�����о��������÷�������鼯��: ѡ�� ���ǡ��� ��		�񡱣�����Ϊ��ѡ�����˰�����ύ����ʱ¼��ϵͳ��
					<br>
					&nbsp;&nbsp;5.�Ƿ�����ҵ�ܾ���칫����»����������ί�С������о�������Ŀ����ľ����ļ�:		ѡ�� ���ǡ��� ���񡱣�����Ϊ��ѡ�����˰�����ύ����ʱ¼��ϵͳ��
					<br>
					&nbsp;&nbsp;6.�Ƿ���ί�С������о�������Ŀ�ĺ�ͬ��Э��: ѡ�� ���ǡ��� ���񡱣�����Ϊ��ѡ�����		˰�����ύ����ʱ¼��ϵͳ��
					<br>
					&nbsp;&nbsp;7.�Ƿ����о�������Ŀ��Ч�����˵�����о��ɹ����������: ѡ�� ���ǡ��� ���񡱣�����Ϊ		��ѡ�����˰�����ύ����ʱ¼��ϵͳ��
					<br>
					&nbsp;&nbsp;8.�н������֤����: ѡ�� ���С��� ���ޡ�������Ϊ��ѡ�����������д���ޣ�����������		������˰�����ύ����ʱ¼��ϵͳ��
					<br>
					&nbsp;&nbsp;9.�з����ý���λ��Ԫ������Ϊ���ڿ۳������γ������ʲ�����������Ϊ�ֹ�¼		�������˰������ѡ������һ����д�����ύ����ʱ¼��ϵͳ��
					<br>
					&nbsp;&nbsp;10.�Ӽƿ۳����λ��Ԫ�����ֹ�¼�������˰�����ύ����ʱ¼��ϵͳ�� 
					<br>
					&nbsp;&nbsp;11.����˰�����Ҫ���͵��������ϣ��ֹ�¼��ϵͳ����¼����Ϊ�Ǳ�¼�
					<br>
					<br>
                      </td>
                    </tr>
                  </table>
                  -->
                  <br>
                  </td>
              </tr>
            </table>
        
            </td>
        </tr>
      </table>
    </form>
  
   	<tr><td valign="bottom" align="center"><br>
	<jsp:include page="../../include/bottom.jsp" flush="true">
	</jsp:include></td>
	</tr>
  </body>

</html>
