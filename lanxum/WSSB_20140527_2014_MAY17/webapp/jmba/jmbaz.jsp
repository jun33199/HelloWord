<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml" %>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeTable"%>


<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
	String zq=(String)request.getAttribute(JmbazForm.ZQ_ATTRIBUTE_NAME);
%>
<jsp:useBean id="jmbazForm" scope="session" class="com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm"/>

<html>
<head>
<title>¼����˰����ҵ����˰����˰��������</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>

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
	var xslPath='/XSLTWEB/model/030000/XSLT/jmbaz.xsl';

	if (strXml != "")
    {
	xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	var xslDoc = new ActiveXObject("Microsoft.XMLDOM");
	xmlDoc.async = false;
	xslDoc.async = false;
	
	if (xmlDoc==null || xslDoc==null)
	{
		 alert("����DOC�������");
		 return; 
	}
	if(xmlDoc.loadXML(strXml)==false)
	{
		alert("װ��xml���ݳ���");
		return false;
	}
	if(xslDoc.load(xslPath)==false)
	{
		alert("װ��xsl���ݳ���");
		return false;
	}
	var httpStr = xmlDoc.transformNode(xslDoc);
	var objOutput = document.getElementById("result");
	objOutput.innerHTML = httpStr;
    }
    return true;
}

function initPage()
{
 
} 

function doExit()
{
	document.forms[0].action = "quit.do";
	document.forms[0].submit();
} 
 function doAdd()
{				
	if (document.forms[0].jmbasxdmadd.value==null || document.forms[0].jmbasxdmadd.value==""){
	  alert("��ѡ�����˰���!");
	  return false;
	}
	 var rootNode = xmlDoc.documentElement;
		var ztbs  = rootNode.selectSingleNode("//sfxz").text;
			//�Ӻ�̨ȡ�ñ�ʶ�������ʶΪN�Ļ������ý�������		
			if(ztbs=="N")
			{
				alert("����ҵ������ҵ����˰Ӧ���������ܽ�����ز��� ");
				return false;
						
			}
	
	document.forms[0].actionType.value="Add";
	document.forms[0].submit();
   
}
 function doEdit(basqwshPara,basxdmPara)
{
	document.forms[0].actionType.value="Edit";
	document.forms[0].basqwsh.value=basqwshPara;
	document.forms[0].basxdm.value=basxdmPara;
	document.forms[0].submit();
   
}
function doDelete(basqwshPara,basxdmPara)
{	
	
	var isContainer;//�Ƿ���ǩ���û�
	var rootNode = xmlDoc.documentElement;
	var zbNode =xmlDoc.selectSingleNode("/taxdoc");
	var mxList  = zbNode.getElementsByTagName("jmsbajl");
	
    if(confirm("�Ƿ�ɾ����������˰�������룿"))
    {
	document.forms[0].actionType.value="Delete";
			var objCDATA =xmlDoc.createCDATASection("3");
			rootNode.selectSingleNode("//ywczlx").text = "";
			rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
			for(var i= 0;i<mxList.length;i++)
			{
				tmp = mxList.item(i).getElementsByTagName("basqwsh").item(0).text;
				if(tmp!=basqwshPara)
				{
					zbNode.removeChild(mxList.item(i));
					
				}
			}
		
		if (g_objSI.Container != ''){isContainer = true;}

		if (doSubmitXML(document.forms[0],"Delete",isContainer,xmlDoc.xml,true))
	    {
		    return true;
	    }
	    else
	   {
			return false;
	    }	
    }else
    {
    		return false;
    }

}

 function doView(basqwshPara,basxdmPara)
{
	document.forms[0].actionType.value="View";
	document.forms[0].basxdm.value=basxdmPara;
	document.forms[0].basqwsh.value=basqwshPara;
	document.forms[0].submit();
   
}

 function doRollback(basqwshPara,basxdmPara)
{	
	
	var isContainer;//�Ƿ���ǩ���û�
	var rootNode = xmlDoc.documentElement;
	var zbNode =xmlDoc.selectSingleNode("/taxdoc");
	var mxList  = zbNode.getElementsByTagName("jmsbajl");
	
    if(confirm("�Ƿ񽫴�������˰�������볷�أ�"))
    {
	document.forms[0].actionType.value="Rollback";
			var objCDATA =xmlDoc.createCDATASection("2");
			rootNode.selectSingleNode("//ywczlx").text = "";
			rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
			
			for(var i= 0;i<mxList.length;i++)
			{
				tmp = mxList.item(i).getElementsByTagName("basqwsh").item(0).text;
				
				if(tmp!=basqwshPara)
				{
					zbNode.removeChild(mxList.item(i));
					
				}
			}
		//rootNode.removeChild(zbNode);
		//rootNode.appendChild(zbNode);
		
		if (g_objSI.Container != ''){isContainer = true;}

		if (doSubmitXML(document.forms[0],"Rollback",isContainer,xmlDoc.xml,true))
	    {
		    return true;
	    }
	    else
	   {
			return false;
	    }	
    }else
    {
    		return false;
    }

}     
      function doQuery() {      	
		document.forms[0].actionType.value="Query";
			document.forms[0].submit();
      
      }
      
      function msg(type){
      	if(type==1)
      		return window.confirm("�Ƿ�ɾ����������˰�������룿");
      	if(type==2)
      		return window.confirm("�Ƿ񽫴�������˰�������볷�أ�");	
      }
     
    </script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="¼����˰����ҵ����˰����˰��������" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/jmbaz.dc">
	<input name="actionType" type="hidden" id="actionType"></input>
    <input name="basqwsh" type="hidden" id="basqwsh"></input>
        <input name="basxdm" type="hidden" id="basxdm"></input>
        
                        <font color="0000ff">
<%
String msg = (String)request.getAttribute("hintmessage");
if (msg == null)
{
	
	msg = "&nbsp;<br/>";
}
out.print(msg);
%>
                </font>
                                <%if ("0".equals(zq)){%>
        
      <table width="980" border="0" cellpadding="0" cellspacing="0" align="center">
        <tr>
          <td valign="top" class="title">
            <br>
            <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
              <tr>
                <td class="1-td1">
                  ¼����˰��λ��������ҵ����˰��������
                </td>
              </tr>
              <tr>
                <td class="1-td2">
                         	
                  <br>

<table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">                   
                 
                    <tr>
                      <td class="2-td2-t-left">                       
                          ����˰���
                      </td>
                      <td colspan="3" class="2-td2-t-center">
                        <div align="left">
 <shtml:select name="jmbazForm" property="jmbasxdmadd"  >
       	 <shtml:option value="">  </shtml:option>    
        <shtml:options collection="JMBA_JMBASXDM_LIST" property="JMBASXDM" labelProperty="JMBASXMC"/>
      </shtml:select>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="4" class="2-td2-center" height="40">
                      
                        
                            <a style="cursor:hand" onClick="doAdd()" onMouseOut="MM_swapImgRestore()"
                        onMouseOver="MM_swapImage('baocun1','','<%=static_contextpath%>images//xinzeng2.jpg',1)">
                          <img src="<%=static_contextpath%>images//xinzeng1.jpg" name="shanchu11" width="79" height="22"
                          border="0" id="baocun1">
                          
                        </a>
                     
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <img onclick="doExit()" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images//tuichu2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images//tuichu1.jpg"
                        width="79" height="22">
                      
                      </td>
                    </tr>
                 
                  </table>
      <%}%>
                  <br>
        
        
        
<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9'>
 <tr>
    <td valign="top">
      <table align="center" cellspacing="0" class="table-99">
<tr>
                <td class="1-td1">
                  ��ѯ��˰����ҵ����˰����˰��������
                </td>
              </tr>
              <tr>
                <td class="1-td2">
                         	
                  <br>

 									<table cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">                   
                    <tr>
                    	 <td class="2-td2-t-left">
                          �������
                      </td>
                      <td class="2-td2-t-left">
                        <div align="left">
                          
                        <shtml:text name="jmbazForm" property="band" size="4" />
                        </div>
                      </td>
                     
                      <td width="15%" class="2-td2-t-left">
                          ����״̬
                      </td>
                      <td width="35%" class="2-td2-t-center">                       
                          <div align="left">
                                <shtml:select name="jmbazForm" property="ztdm"  >
                          	 <shtml:option  value="">
                          	 
                            </shtml:option>                          
                            <shtml:option value="1">
                              �ѱ���δ�ύ
                            </shtml:option>       
                            <shtml:option value="3">
                              ���ύδ���
                            </shtml:option>
                            <shtml:option value="4">
                             �����ͨ�� 
                            </shtml:option>   
                            <shtml:option value="5">
                              ���δͨ��
                            </shtml:option>                                  
                             <shtml:option value="6">
                             ������ 
                            </shtml:option>                       
      </shtml:select>
                       
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="2-td2-left">                       
                          ����˰���
                      </td>
                      <td colspan="3" class="2-td2-center">
                        <div align="left">
                                 <shtml:select name="jmbazForm" property="jmbasxdm"  >
       	 <shtml:option value="">  </shtml:option>    
        <shtml:options collection="JMBA_JMBASXDM_LIST" property="JMBASXDM" labelProperty="JMBASXMC"/>
      </shtml:select>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="4" class="2-td2-center" height="40">
                      
                          <a style="cursor:hand" onClick="doQuery()" onMouseOut="MM_swapImgRestore()"
                          onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images//chaxun2.jpg',1)">
                            <img src="<%=static_contextpath%>images//chaxun1.jpg" name="chaxun" width="79" height="22" border="0"
                            id="chaxun">
                          </a>
                           
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <img onclick="doExit()" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images//tuichu2.jpg',1)"
                        onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images//tuichu1.jpg"
                        width="79" height="22">
                      
                      </td>
                    </tr>
                 
                  </table>
   
                  
        
				<div id="result"></div>
		
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
<%@ include file="../include/bottom.jsp" %>
 </td>
</tr>
</table>
</body>
</html>