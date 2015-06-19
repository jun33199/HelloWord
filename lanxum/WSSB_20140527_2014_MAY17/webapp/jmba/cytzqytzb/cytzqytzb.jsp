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
<title>��ҵͶ����ҵ�ֿ�Ӧ��˰���ö������</title>
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
	var xslPath='jmba/cytzqytzb/cytzqytzb.xsl';

	if (strXml != "")
    {
        //alert(strXml);
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



function doAdd(){
    //alert("add");
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

function doDel(swjdjzh,lxdm){
    //alert(swjdjzh+"  "+lxdm);
    if(confirm("��ȷ��ɾ��")){
        document.forms[0].swdjzh.value=swjdjzh;
        document.forms[0].lxdm.value=lxdm;
        document.forms[0].actionType.value="Del";
        document.forms[0].submit();
    }
}

function doEditor(btzjsj,swjdjzh,lxdm,zcbabs){
    //alert(btzjsj +" "+ swjdjzh+" "+lxdm+" "+zcbabs);
    document.forms[0].btzjsjdm.value=btzjsj;
    document.forms[0].swdjzh.value=swjdjzh;
    document.forms[0].lxdm.value=lxdm;
    document.forms[0].zcbabs.value=zcbabs;
    document.forms[0].zcbashbj.value="1";
    document.forms[0].actionType.value="Editor";
    document.forms[0].submit();
}

function doZcba(wnwsh,btzjsj,swjdjzh,lxdm,zcbabs){
    alert(wnwsh);
    document.forms[0].btzjsjdm.value=btzjsj;
    document.forms[0].swdjzh.value=swjdjzh;
    document.forms[0].lxdm.value=lxdm;
    document.forms[0].wnwsh.value=wnwsh;
    document.forms[0].zcbabs.value=zcbabs;
    document.forms[0].zcbashbj.value="0";
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
    		<jsp:param name="name" value="��ҵͶ����ҵ�ֿ�Ӧ��˰���ö������" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/cytzqytzb14.dc">
    <input name="actionType" type="hidden" id="actionType">
    <input name="swdjzh" type="hidden" id="swdjzh">
    <input name="wnwsh" type="hidden" id="wnwsh">
    <input name="lxdm" type="hidden" id="lxdm">
    <input name="btzjsjdm" type="hidden" id="btzjsjdm">
    <input name="zcbabs" type="hidden" id="zcbabs">
    <input name="zcbashbj" type="hidden" id="zcbashbj">
    <input name="gxjsly" type="hidden" id="gxjsly">
<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9'>
 <tr>
    <td valign="top">
      <table align="center" cellspacing="0" class="table-99">
<tr>
                <td class="1-td1">
                    ��ҵͶ����ҵ�ֿ�Ӧ��˰���ö������
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
                        <img onclick="javascript:return doReturn();" alt="�˳�" name="tc1" width="79" height="22" border="0" id="tc1" src="<%=static_contextpath%>images/fanhui2.jpg">
                      </td>
                    </tr>
                  </table>
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
