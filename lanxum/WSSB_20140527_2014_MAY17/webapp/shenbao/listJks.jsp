<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<html>
<head>
<title>�鿴�걨��Ϣ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">

<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>

<script language="JavaScript" type="text/JavaScript">

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

var xmlDoc;
function parseXmlOnLoad()
{

	var xslPath="/XSLTWEB/model/010002/XSLT/listJks.xsl";
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
	var objOutput = document.getElementById("tb");
	objOutput.innerHTML = httpStr;
}


function doExit()
{
	document.forms[0].actionType.value = "Return";
	document.forms[0].submit();
}

function doPrint(sbbh)
{
	document.forms[0].actionType.value = "Print";
	document.forms[0].sbbhIndex.value = sbbh;
	document.forms[0].submit();
}

function doDelete(sbbh, jksh)
{
	var isContainer;//�Ƿ���ǩ���û�
	var msg = "";
	var sbsjCN;
	var jksList;
	var smitemList;
	var deleteJks=false;
	var rootNode = xmlDoc.documentElement;
	var sbsjList  = rootNode.getElementsByTagName("sbsj");
	
	for(var i = 0;i<sbsjList.length;i++)
	{
		var currendSbbh = sbsjList.item(i).getElementsByTagName("sbbh").item(0).text;
		if(currendSbbh == sbbh)
		{
			sbsj = sbsjList.item(i);
			break;
		}
	}
	
	if (jksh == null || jksh == "")
	{
		msg = "��ȷ�����ϱ����걨��";
	}
	else
	{
		jksList = sbsj.getElementsByTagName("jks");
		smitemList = sbsj.getElementsByTagName("smitem");
		
		if(jksList.length==1){
			msg = "��ȷ�����ϱ����걨��";
		}else{
			deleteJks = true;
			msg = "��ȷ���������Žɿ�����\n��������ϵ���Ӫҵ˰�ɿ��飬�������丽��˰�ɿ��鼰�������йص�Ӫҵ˰�ɿ��顣";
		}
		
	}

	if (confirm(msg))
	{
		document.forms[0].actionType.value = "Delete";
		//document.forms[0].sbbhIndex.value = sbbh;
		//document.forms[0].jkshIndex.value = jksh;
		if(!deleteJks)
		{
			var objCDATA =xmlDoc.createCDATASection("3");
			rootNode.selectSingleNode("//ywczlx").text = "";
			rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
		}else{
			var objCDATA =xmlDoc.createCDATASection("2");
			rootNode.selectSingleNode("//ywczlx").text = "";
			rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
			var tmp;
			for(var i= 0;i<jksList.length;i++)
			{
				tmp = jksList.item(i).getElementsByTagName("jkpzh").item(0).text;
				if(tmp==jksh)
				{
					sbsj.removeChild(jksList.item(i));
					break;
				}
			}
			for(var t = 0;t<smitemList.length;t++){
				tmp = smitemList.item(t).getElementsByTagName("jkpzh").item(0).text;
				if(tmp==jksh)
				{
					sbsj.removeChild(smitemList.item(t));
					continue;
				}
				
			}
			
			
			
			
	    }
		for(i=0;i<sbsjList.length;i++)
		{
			rootNode.removeChild(sbsjList.item(i));
		}
		rootNode.appendChild(sbsj);
		
		if (g_objSI.Container != ''){isContainer = true;}
	   	
		if (doSubmitXML(document.forms[0],"Delete",isContainer,xmlDoc.xml,true))
	    {
		    return true;
	    }
	    else
	   {
			return false;
	    }	
	}
		
		
		
	
}

</script>


</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="parseXmlOnLoad();">

<form name="form1"  action="listJks.dc">
<input name="actionType" type="hidden"/>
<input name="jkshIndex" type="hidden"/>
<input name="sbbhIndex" type="hidden"/>
<table  width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  <td colspan=2 valign="top">
    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="�鿴�걨" />
		<jsp:param name="help_url" value="help/wssb/qyzhsb/ckjks/ckjks-000.htm"/>
    	</jsp:include>

<font color="blue" size="5">
<%
String msg = (String)request.getAttribute("QYSBSuccess");
if (msg != null)
{
out.println(msg);
}
%>
</font>

<html:errors />

<table width="96%" align="center" cellspacing="0" class="table-99">
                <tr>
					<td class="1-td1"  colspan="7">�걨ά��</td>
                </tr>
                <tr>
					<td class="1-td2"  colspan="7"><br/> 
					<div id="tb"></div>
					<table width="94%" border="0" cellpadding="0" cellspacing="4">
								<tr valign="bottom">
									<td align="center">
										<img src="<%=static_contextpath%>images/fanhui1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onClick="doExit()" style="cursor:hand">
									</td>
								</tr>
							</table>
				
					</td>
				</tr>
		</table>		
		</td>
  </tr>
  <tr>
    <td valign="bottom" align="center">
	<%@ include file="../include/bottom.jsp" %>
    </td>
  </tr>
</table>

</form>
</body>
</html>
