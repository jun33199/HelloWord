<%@ page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="java.util.*"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Wssbyy"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeConstant"%>
<%String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
			request.getAttribute("Msg");
	List wsyyList = (List) session.getAttribute("wssbyyArray");
	
%>

<html>
<head>
<title>��Ӧ��˰���ѣ����걨��¼��</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<script language="JavaScript" type="text/JavaScript"
	src="js/function.js"></script>
<script language="JavaScript" type = "text/JavaScript" src="js/gmit_selectcontrol.js"></script>
<script language="JavaScript" type="text/JavaScript"	src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript"	src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/JavaScript">
//started added by qianchao 2006-2-7
var wsyyArray= new Array(<%=wsyyList.size()%>);
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
        
    	for(int i=0;i<wsyyList.size();i++)
    	{
    		Wssbyy tmpWsyy = (Wssbyy)wsyyList.get(i);
    		out.println("wsyyArray["+i+"] = [\""+tmpWsyy.getWssbyydm()+"\",\""+tmpWsyy.getWssbyymc()+"\"];");
    	}
    	// ������˰����ԭ��
        if(session.getAttribute("wssbyy_Array") != null)
            {
                out.print("var wsyy_Array = new Array(");
                String[][] wssbyy = (String[][])session.getAttribute("wssbyy_Array");
                for(int i = 0; i < wssbyy.length; i++)
                {
                    if(i != 0)
                    {
        				out.print(",[\"" + wssbyy[i][0] + "\",\"" +wssbyy[i][1] +"\"]");
                    }
                    else
                    {
                        out.print("[\"" + wssbyy[i][0] + "\",\"" +wssbyy[i][1] +"\"]");
                    }
                }
                out.println(");");
            } 
%>
g_objSI.Container = "<%=containerName%>";
var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xmlDoc;
//ended   added by qianchao 2006-2-7


<%/*\u4FDD\u5B58*/%>                              

//����xml
function parseXmlOnLoad()
{
	var xslPath="/XSLTWEB/model/010003/XSLT/" + strXSLTVersion + ".xsl";
	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    //doInit();
    insertWsyy();
    docheckSelected();
    return true;
}

function insertWsyy()
{
	var obj=document.forms[0].wssbyydm;
	var showValue=obj.value;
	var ite=new Option("","");
	obj.remove(ite);
	for(var i=0;i<wsyyArray.length;i++)
	{
		var yValue=wsyyArray[i][0];
		var yName=wsyyArray[i][1];
		var item=new Option(yName,yValue);
		obj.options.add(item);
	}
	obj.value=showValue;
}


/**
 * add by tangchangfu 2014-04-08 ��˰����˰��Ŀ
 */
function docheckSelected()
{
	var objVal = document.all.wssbyydm.value;//��˰�걨����
	var bzobj = document.all.QtWssbyymc;//��ע��Ϣ
	var trObj_bz = document.all.id_bz;
	
	//���ѡ�����˰�걨ԭ��Ϊ������������Ҫ�����¼�뱸ע��Ϣ
	if(objVal != "" && objVal =="<%=CodeConstant.WSSBYY_QTYY_99%>")
	{
	
		//��ʾ��ע��
		//trObj_bz.style.display='';
		bzobj.disabled="";
		bzobj.readonly="";
		if(replaceBlank(bzobj.value)=="")
		{
			alert("��ѡ�����˰�걨ԭ��Ϊ������������д�걨ԭ��������ע��������100�֣���");
			//���㶨λ����ע��
			bzobj.focus();
			return false;
		}
		else
		{
			if(replaceBlank(bzobj.value).length>100)
			{
				alert("��ע��Ϣ�����ܳ���100���֣�");
				bzobj.focus();
				return false;
			}
		}
	}
	else
	{
		//ѡ���ǡ��������������ر�ע��
		bzobj.disabled="disabled";
		bzobj.readonly="readonly";
		bzobj.value="";
	}
	return true;
}

//�����걨����
function getSbsj(objForm)
{
	var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
	var objTemp = g_Doc.XMLDoc.createElement("sbsj");
	objNode.appendChild(objTemp);
	objForm.wssbyymc.value = objForm.wssbyydm.options[objForm.wssbyydm.selectedIndex].text;
	getChildren(objTemp,"sbcz");
	getChildren(objTemp,"sbbh");
	getChildren(objTemp,"wssbyydm");
	getChildren(objTemp,"wssbyymc");
	getChildren(objTemp,"QtWssbyymc");
}

function doSave()
{
	//alert("1:"+document.forms[0].wsyySelect.value);
	//alert("2:"+document.forms[0].wssbyydm.value);
	//alert("3:"+document.forms[0].wssbyymc.value);
	var wssbyymc = document.forms[0].QtWssbyymc.value;
	var wssbyydm = document.forms[0].wssbyydm.value;
	var saveCan=document.forms[0].sbcz.value;
	var old  = document.forms[0].ywczlx.value;
	
	if(trim(saveCan) != 0){
		/*if(trim(saveCan)==2)
		{
			alert("����������Ӧ��˰���걨��");
		}else
		{
			alert("�������Ѿ�������Ӧ��˰���걨��");
		}*/
		return false;
	}
	if(wssbyydm == null || wssbyydm =="")
	{
		alert("��ѡ����Ӧ���걨ԭ��");
		return false;
	}
	if(wssbyydm == '<%=CodeConstant.WSSBYY_QTYY_99%>' )
	{
		if(!docheckSelected())
		{
			return false;
		}
	}
	if(confirm(confirmSave))
	{
		if (document.forms[0].ywczlx.value == 0)
		{
			document.forms[0].ywczlx.value = 1;
		}
		else if (document.forms[0].ywczlx.value == 1)
		{
			document.forms[0].ywczlx.value = 2;
		}
		document.forms[0].actionType.value = "Save";

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
	else
	{
		return false;
	}
}

<%/*\u8FD4\u56DE*/%>
function doReturn()
{

    if(confirm(confirmReturn))
    {
       	document.forms[0].actionType.value = "Return";
       // document.forms[0].submit();
		 if (!doSubmitXML(document.forms[0],"Return",false,"return",true))
		{
		  return false;
		}
        return true;
    }
    else
    {
       	return false;
    }
}
<%/*\u5220\u9664*/%>
function doDelete()
{	
	var old  = document.forms[0].ywczlx.value;
	var saveCan=document.forms[0].sbcz.value;
	if(trim(saveCan) != 1){
		if(trim(saveCan)!=2)
		{
			alert("������û�н�����Ӧ��˰���걨��");
		}		
		return false;
	}
    if(confirm(confirmDelete))
    {
		changeYwczlx("3");
       	document.forms[0].actionType.value = "Delete";
		if (g_objSI.Container != '')
		{
	        if (!doSubmitXML(document.forms[0],"Delete",true,g_Doc.parseXmlDoc.xml,true))
	        {
				changeYwczlx(old);
		        return false;
	        }
	    }
	    else
	    {
	        if(!doSubmitXML(document.forms[0],"Delete",false,g_Doc.parseXmlDoc.xml,true))
			{
				changeYwczlx(old);
				return false;
			}
			
	    }
		return true;
    }
    else
    {
       	return false;
    }
}


/**
 * ��������ʽȥ���ո�
 *add by tangchangfu 2014-04-08 ��˰����˰��Ŀ
 */
function replaceBlank(Val)
{
	return Val.replace(/(^\s*)|(\s*$)/g, "");
}

//ͳ����������
function countSl()
{
   var maxl=100; //�ܳ�
   var s=document.getElementById("QtWssbyymc").value.length;
   if(s>maxl)document.getElementById("QtWssbyymc").value=document.getElementById("QtWssbyymc").value.substr(0,maxl-1)
   else document.getElementById("spnId").innerHTML="�����룺"+s+"/"+maxl+" ��"
}
</script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet"
	type="text/css">
<style>
input {
    font-size: 9pt;
}
</style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" style="margin: 0" onLoad="parseXmlOnLoad();">

<table width="97%" height="100%" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" valign="top"><jsp:include
			page="../include/SbHeader.jsp" flush="true">
			<jsp:param name="name" value="��Ӧ��˰���ѣ����걨��" />
			<jsp:param name="help_url"
				value="help/wssb/qyzhsb/wsksb/wsksb001.htm" />
		</jsp:include></td>
	</tr>
	<!-- self-->
	<tr>
		<td>
		<form name="form1" method="POST" action="/shenbao/wsksb.dc"><input
			name="actionType" type="hidden" id="actionType">
		<table width="100%" border="0" align="center" cellspacing="0"
			bgcolor="#FFFFFF" class="table-99">
			<tr>
				<td class="1-td1">��Ӧ��˰���ѣ����걨��</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td width="82%" valign="top" align="center" class="1-td2">
				<table border="0" align="center" width="100%" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>
						<div id="result"></div>
						<br>
						</td>
					</tr>
					<tr>
						<td align="center" valign="top">

						<table cellspacing=0 width="80%">
							<tr>
								<td align="left"><span
									style='font-size:16.0pt;font-family:����_GB2312;color:black'>
								&nbsp;&nbsp;&nbsp;&nbsp;���ݡ��л����񹲺͹�˰�����չ���ʵʩϸ�򡷵���ʮ�����Ĺ涨Ҫ���ҵ�λ������ˣ��걨����Ӧ�ɵ����еط�˰���ѡ����ӵȿ����Ϊ�㡣
								<br>
								&nbsp;&nbsp;&nbsp;&nbsp;��ȷ�������걨������ʵ�ɿ�����Ը��е��ɴ˲�������ط������Ρ� <br>
								�ش��걨�� </span></td>
							</tr>
						</table>
						<input name="bz" type="hidden" id="bz"> <br>
						<br>
						<br>
						<br>
						<table>
							<tr>
								<div align="center">
								<td><img src="<%=static_contextpath%>images/baocun1.jpg" onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="����" onclick="return doSave()" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td><img src="<%=static_contextpath%>images/shanchu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/shanchu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/shanchu1.jpg'" alt="ɾ��" onclick="doDelete()" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td><img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="doReturn()" style="cursor:hand"></td>
								</div>
							</tr>
						</table>
						</td>
					</tr>

				</table>
				</td>
			</tr>
			<tr>
				<td class="1-td2" valign="top">
				<div align="left"><br>
				<div class="b-black12"><strong>�걨��ϸ����¼��</strong></div>
				<shenbao:sbzl frompage="ReturnWs" /></div>
				</td>
			</tr>
			<tr align="center">
				<td></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td valign="bottom">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				<hr width="100%" class="hr1" size=1>
				</td>
				<td width="99" align="center" class="black9"><strong><font
					color="#0000FF">�� ʾ �� Ϣ</font></strong></td>
				<td>
				<hr width="100%" class="hr1" size=1>
				</td>
			</tr>
		</table>
		<table width="99%" border="1" align="center" cellpadding="1"
			cellspacing="1" bordercolor="#FFFFFF" class="black9">
			<tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				<td height="47">
				<P></P>
				<ul>
					<br>
					<logic:iterate id='item' name='Msg'>
						<bean:write name='item' filter="false" />
						<br>
					</logic:iterate>
				</ul>
				</td>
			</tr>
		</table>
		</form>
		</td>
	</tr>
	<tr>
		<td valign="bottom" align="center"><%@ include
			file="../include/bottom.jsp"%></td>
	</tr>
</table>
</body>
</html>

