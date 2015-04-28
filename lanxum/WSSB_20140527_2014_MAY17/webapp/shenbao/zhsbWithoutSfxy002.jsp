<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@page import = "java.util.Map"%>
<%@page import = "java.util.Iterator"%>
<%@page import = "java.util.List"%>
<%@page import = "com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import = "com.ttsoft.bjtax.dj.model.YHZH"%>
<%@page import = "com.ttsoft.bjtax.shenbao.model.domain.Zqrl"%>
<%@page import = "com.ttsoft.bjtax.shenbao.model.client.SbjkmxData"%>
<%@page import = "com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import = "com.ttsoft.bjtax.shenbao.constant.CodeConstant"%>
<%@page import = "java.text.SimpleDateFormat"%>
<%@page import = "java.util.Date"%>


<%
String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<%@ include file="zhsbFunc.jsp" %>
<html>
<head>
<title>�걨����¼��</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/zhsb.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/JavaScript">
//started added by qianchao 2006-2-7
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
var szsmID=new Array();
//ended   added by qianchao 2006-2-7
<%
List zcjkList = writeItemSize(SessionKey.ZCJK_LIST, session, out);
List bjqsList = writeItemSize(SessionKey.BJQS_LIST, session, out);
List sdjjList = writeItemSize(SessionKey.SDJJ_LIST, session, out);
writeFjsInfo(SessionKey.ZCJK_FJS_INFO, session, out);
writeFjsInfo(SessionKey.BJQS_FJS_INFO, session, out);
writeFjsInfo(SessionKey.SDJJ_FJS_INFO, session, out);

%>

<%
List bankInfoList = (List)session.getAttribute("bankinfo");
out.print("var bankInfo = [");

for(int i=0; i<bankInfoList.size(); i++)
{
    YHZH yhzh = (YHZH)bankInfoList.get(i);
    out.print("[\"" + yhzh.getZh() + "\",\"" + yhzh.getKhyhmc() + "\",\""+yhzh.getYhdm()+"\"],");
}

out.println("[\"\",\"\",\"\"]];");

//��ȡӡ��˰��������
SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
String month = df.format(new Date()).substring(0, 6); // ��ǰ����:YYYYMM
List yhsZqrlList = (List)session.getAttribute("YhsZqrlInfor");
out.print("var YhsZqrlInfor = [");

for(int i=0; i<yhsZqrlList.size(); i++)
{
    Zqrl yhszqrl = (Zqrl)yhsZqrlList.get(i);
    String qsrq = df.format(yhszqrl.getZqqsrq()).substring(0, 8);
    String jzrq = df.format(yhszqrl.getZqzzrq()).substring(0, 8);
    out.print("[\"" + yhszqrl.getSzsmdm() + "\",\"" + qsrq + "\",\"" + jzrq + "\"],");
}

out.println("[\"\",\"\",\"\"]];");
%>

function setBankName(obj)
{
    for (var i=0; i<bankInfo.length; i++)
    {
        if (bankInfo[i][0] == obj.value)
        {
            bankName.innerText = " " + bankInfo[i][1];
			document.forms[0].yhmc.value=" " + bankInfo[i][1];
			document.forms[0].yhdm.value=" "+bankInfo[i][2];
            break;
        }
    }
}
<%
	int len= 0;
	if(zcjkList != null && zcjkList.size() >0)
    {
    	len = zcjkList.size();
    	String[] szsmdm=new String[len];
		boolean aksslj=false;
	 	for (int i=0; i<len; i++)
		{
			SbjkmxData mxData = (SbjkmxData)zcjkList.get(i);
			aksslj=mxData.isAksslj();
			szsmdm[i]=mxData.getSzsmdm();
			out.println("szsmID["+i+"] = "+szsmdm[i]+";");
		}
    }
    if(sdjjList != null && sdjjList.size() >0)
    {
    	len = sdjjList.size();
    	String[] szsmdm=new String[len];
		boolean aksslj=false;
	 	for (int i=0; i<len; i++)
		{
			SbjkmxData mxData = (SbjkmxData)sdjjList.get(i);
			aksslj=mxData.isAksslj();
			szsmdm[i]=mxData.getSzsmdm();
			out.println("szsmID["+i+"] = "+szsmdm[i]+";");
		}
    }
	
	%> 
//����xml
function parseXmlOnLoad()
{
	var xslPath="/XSLTWEB/model/010002/XSLT/" +strXSLTVersion+".xsl";
	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
	addBank();
	reSzsmid();
	hiddenSbfs();
    return true;
}
//
function getLocalPostXml(objForm)
{
	var retstr;
	//��������
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//��˰����Ϣ
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);
	//�걨��Ϣ
	applendElement("/taxdoc","sbxx",["fsdm"]);
	//�걨����
	getSbsj(objForm);	
	//ȥ��ĩβ�Զ���ӵĻس�
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	return retstr;
}

//�����걨����
function getSbsj(objForm)
{
	var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
	var objTemp = g_Doc.XMLDoc.createElement("sbsj");
	objNode.appendChild(objTemp);
	//objNode = g_Doc.XMLDoc.createElement("sbsj");
	//objTemp.appendChild(objNode);
	getChildren(objTemp,"sskkbz");
	getChildren(objTemp,"lwzt");
	getChildren(objTemp,"yhdm");
	getChildren(objTemp,"yhmc");
	getChildren(objTemp,"zh");
	getChildren(objTemp,"sbrq");
	getChildren(objTemp,"sbbh");
	getChildren(objTemp,"sklxdm");
	getChildren(objTemp,"sklx");
	getChildren(objTemp,"gkzzjgdm");
	getChildren(objTemp,"gkzzjgmc");
	getChildren(objTemp,"zsswjgzzjgdm");
	getChildren(objTemp,"zsswjgzzjgmc");
	getChildren(objTemp,"swjgzzjgdm");
	getChildren(objTemp,"swjgzzjgmc");
	getChildren(objTemp,"djzclxmc");
	getChildren(objTemp,"lxdh");
	getChildren(objTemp,"lsgxmc");
	getChildren(objTemp,"sfkyzf");
	//var objTemp = g_Doc.XMLDoc.createElement("smitemlist");
	//objNode.appendChild(objTemp);
	var total=document.forms(0).length-4;
	outer:
	for(var j=29;j<total;)
	{
		objNode=g_Doc.XMLDoc.createElement("smitem");
		objTemp.appendChild(objNode);
		
		for(var i=j;i<j+12;i++)
		{
			var objName=objForm.elements(i).name;
			//alert(objName);
			var position=objName.indexOf("_");
			strName=objName.substring(0,position);
			if(strName=="szsmID"){strName="szsmdm";}
			//alert(strName);
			if(strName=="hj"){break outer;}
			strValue=objForm.elements(i).value;
			node2=g_Doc.XMLDoc.createElement(strName);
			objNode.appendChild(node2);
			var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
			node2.appendChild(objCDATA);
		}
		j+=12;
	}	
}




var jklx = [["zcjk","�����ɿ�"],["bjqs","����Ƿ˰"],["sdjj","ί�д���"]];

function checkZcjk(index)
{
	var itemsize = itemsize_zcjk;
	if (index==1)
	{
		itemsize = itemsize_bjqs;
	}
	else if (index==2)
	{
		itemsize = itemsize_sdjj;
	}
	if (itemsize == 1)
	{
        if(eval("document.forms[0].kssl_" + jklx[index][0]).length==1)
        {
            var kssl = eval("document.forms[0].kssl_" + jklx[index][0])[0];
        }else
        {
            var kssl = eval("document.forms[0].kssl_" + jklx[index][0]);
        }
        if(eval("document.forms[0].szsmID_" + jklx[index][0]).length==1)
        {
            var szsmdm = eval("document.forms[0].szsmID_" + jklx[index][0])[0].value;
        }else
        {
            var szsmdm = eval("document.forms[0].szsmID_" + jklx[index][0]).value;
        }
        if(eval("document.forms[0].jsje_" + jklx[index][0]).length==1)
        {
            var jsje = eval("document.forms[0].jsje_" + jklx[index][0])[0];
        }else
        {
           var jsje = eval("document.forms[0].jsje_" + jklx[index][0]);
        }
        if(eval("document.forms[0].sjse_" + jklx[index][0])==1)
        {
            var sjse = eval("document.forms[0].sjse_" + jklx[index][0])[0];
        }else
        {
            var sjse = eval("document.forms[0].sjse_" + jklx[index][0]);
        }
        
        //Junbing Tu 201407  
        if(eval("document.forms[0].sbfs_" + jklx[index][0]).length==1)
        {
            var sbfs = eval("document.forms[0].sbfs_" + jklx[index][0])[0];
        }else
        {
            var sbfs = eval("document.forms[0].sbfs_" + jklx[index][0]);
        }
        if (sbfs.type == "checkbox")
		{
			if(sbfs.checked)
			{
				sbfs.value = "1";
			}
			else
			{
				sbfs.value = "0";
			}
		}

		if (kssl.type == "text")
		{
			if( !kssl.readOnly )
			{
				if(szsmdm.indexOf("11") == 0 || szsmdm.indexOf("88") == 0)
				{
					if (kssl.value == "")
					{
						alert(jklx[index][1] + "��˰��������Ϊ�գ����������д���ݿ���¼��0��");
						kssl.focus();
						return false;
					}
					if(kssl.value < 0)
					{
						alert(jklx[index][1] + "��˰����������ڵ���0��");
						kssl.focus();
						return false;
					}
				}
				else
				{
					if (kssl.value == "")
					{
						alert(jklx[index][1] + "��˰��������Ϊ�գ�");
						kssl.focus();
						return false;
					}
					if(kssl.value <= 0)
					{
						alert(jklx[index][1] + "��˰�����������0��");
						kssl.focus();
						return false;
					}
				}
			}

/*			if( !kssl.readOnly && kssl.value == "")
			{
				alert(jklx[index][1] + "��˰��������Ϊ�գ�");
				kssl.focus();
				return false;
			}
			else
			{
				if(szsmdm.indexOf("11") == 0 || szsmdm.indexOf("88") == 0)
				{
					if(kssl.value < 0)
					{
						alert(jklx[index][1] + "��˰�����������0��");
						kssl.focus();
						return false;
					}

				}
				else
				{
					if(kssl.value <= 0)
					{
						alert(jklx[index][1] + "��˰�����������0��");
						kssl.focus();
						return false;
					}
				}
			}
*/
		}
		if (!jsje.readOnly && jsje.value == "")
		{
			alert(jklx[index][1] + "��˰����Ϊ�գ�");
			jsje.focus();
			return false;
		}
		else
		{
			if(szsmdm.indexOf("11") == 0 || szsmdm.indexOf("88") == 0)
			{
				if (jsje.value < 0)
				{
					alert(jklx[index][1] + "��˰���������0��");
					jsje.focus();
					return false;
				}
			}
			else
			{
				if (jsje.value <= 0)
				{
					alert(jklx[index][1] + "��˰���������0��");
					jsje.focus();
					return false;
				}
			}
		}

		if (!sjse.readOnly && sjse.value == "")
		{
			alert(jklx[index][1] + "ʵ��˰���Ϊ�գ���ȡ��0�걨��");
			sjse.focus();
			return false;
		}
		else if (sjse.value <= 0)
		{
			alert(jklx[index][1] + "ʵ��˰��������0����ȡ��0�걨��");
			sjse.focus();
			return false;
		}
		
		//Junbing Tu 201407
		if(!checkYhsZqrl(szsmdm,sbfs))
			return false;
		if(sbfs.checked)
		{
			if(!window.confirm("�������걨�а���������ӡ��˰˰����Ƿ������"))
			return false;
		}
	}
	else if (itemsize > 1)
	{
		for(var i=0; i<itemsize; i++)
		{
			//Junbing Tu 201407
			var sbfs = eval("document.forms[0].sbfs_" + jklx[index][0]);
			if (sbfs[i].type == "checkbox")
			{
				if(sbfs[i].checked)
				{
					sbfs[i].value = "1";
				}
				else
				{
					sbfs[i].value = "0";
				}
			}
			
			var kssl = eval("document.forms[0].kssl_" + jklx[index][0]);
			var szsmdm = eval("document.forms[0].szsmID_" + jklx[index][0]);
			if (kssl[i].type == "text")
			{
				if( !kssl[i].readOnly )
				{
					if(szsmdm[i].value.indexOf("11") == 0 || szsmdm[i].value.indexOf("88") == 0)
					{
						if (kssl[i].value == "")
						{
							alert(jklx[index][1] + "��˰��������Ϊ�գ����������д���ݿ���¼��0��");
							kssl[i].focus();
							return false;
						}
						if(kssl[i].value < 0)
						{
							alert(jklx[index][1] + "��˰����������ڵ���0��");
							kssl[i].focus();
							return false;
						}
					}
					else
					{
						if (kssl[i].value == "")
						{
							alert(jklx[index][1] + "��˰��������Ϊ�գ�");
							kssl[i].focus();
							return false;
						}
						if(kssl[i].value <= 0)
						{
							alert(jklx[index][1] + "��˰�����������0��");
							kssl[i].focus();
							return false;
						}
					}
				}
			}
			var jsje = eval("document.forms[0].jsje_" + jklx[index][0]);
			if (!jsje[i].readOnly && jsje[i].value == "")
			{
				alert(jklx[index][1] + "��˰����Ϊ�գ�");
				jsje[i].focus();
				return false;
			}
			else
			{
				if(szsmdm[i].value.indexOf("11") == 0 || szsmdm[i].value.indexOf("88") == 0)
				{
					if (jsje[i].value < 0)
					{
						alert(jklx[index][1] + "��˰���������0��");
						jsje[i].focus();
						return false;
					}
				}
				else
				{
					if (jsje[i].value <= 0)
					{
						alert(jklx[index][1] + "��˰���������0��");
						jsje[i].focus();
						return false;
					}
				}
			}
			var sjse = eval("document.forms[0].sjse_" + jklx[index][0]);
			if (!sjse[i].readOnly && sjse[i].value == "")
			{
				alert(jklx[index][1] + "ʵ��˰���Ϊ�գ���ȡ��0�걨��");
				sjse[i].focus();
				return false;
			}
			else if (sjse[i].value <= 0)
			{
				alert(jklx[index][1] + "ʵ��˰��������0����ȡ��0�걨��");
				sjse[i].focus();
				return false;
			}
			if(!checkYhsZqrl(szsmdm[i].value,sbfs[i]))
				return false;
		}
		for(var i=0; i<itemsize; i++)
		{
			//Junbing Tu 201407
			var sbfs = eval("document.forms[0].sbfs_" + jklx[index][0]);
			if(sbfs[i].checked)
			{
				if(!window.confirm("�������걨�а���������ӡ��˰˰����Ƿ������")){
					return false;
				}else{
					break;
				}
			}
		}
	}

	return true;
}

//�ж�ӡ��˰���걨��1.˰������Ϊ��ί�д����������ڷ�Χ�⣬�޷��걨����ӡ��˰˰�2.�������걨ӡ��˰ Junbing Tu 201407
function checkYhsZqrl(szsmdm,sbfs)
{
	var sklxdm = document.forms[0].sklxdm.value;
	var sbrq = document.forms[0].sbrq.value;
	sbrq = sbrq.replace(/\-/g,"");
	for(var j=0;j<YhsZqrlInfor.length;j++)
	{
		var szsm = YhsZqrlInfor[j][0];
		var qsrq = YhsZqrlInfor[j][1];
		var jzrq = YhsZqrlInfor[j][2];
		if(szsmdm == szsm && sklxdm == '<%=CodeConstant.SKLXDM_SDJJ%>' && sbfs.checked == false && sbrq > jzrq)
		{
			alert("ӡ��˰�����룺"+szsmdm+"����Ӧ�������ѹ����޷��걨����˰�\n�����걨����ӡ��˰˰��뵽����˰����������걨��\n�����걨����ӡ��˰˰���ѡ�񡰱���˰��");
			return false;
		}
	}
	return true;
}

</script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onLoad="parseXmlOnLoad()&&onloadcomputer();">

<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
    <td align="center" valign="top">

        <jsp:include page="../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="�걨����¼��" />
        <jsp:param name="help_url" value="help/wssb/qyzhsb/sbsjlr/sbsjlr-000.htm"/>
        </jsp:include>
    </td>
  </tr>
   <tr>
    <td><html:errors/><br></td>
  </tr>
<!-- self-->
<tr>
 <td>
<br>
<form name="form1" method="POST" action="/shenbao/zhsbWithoutSfxy.dc">
	<input name="actionType" type="hidden" id="actionType">
<table width="100%"  border="0" align="center" cellspacing="0" bgcolor="#FFFFFF" class="table-99">
    <tr>
        <td class="1-td1" colspan="2">�ۺ��걨</td>
    </tr>
    <tr bgcolor="#FFFFFF">
        <td width="82%" rowspan="2" valign="top" align="center" class="1-td2">
            <table border="0" align="center" width="100%" cellpadding="0" cellspacing = "0">
                <tr><td><div id="result"></td></tr>
			</table>
               <br>
			<table cellspacing="0" class="table-99">			
				<tr class="black9">							
					<td align="center" nowrap="nowrap"><div align="center">
						<img src="<%=static_contextpath%>images/createjks1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/createjks2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/createjks1.jpg'" alt="���ɽɿ���" onClick="doSave(0);" style="cursor:hand">
			              &nbsp;&nbsp;&nbsp;&nbsp;
						<img src="<%=static_contextpath%>images/fanhui1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onClick="doReturn()" style="cursor:hand">
					</div></td>
				</tr>			
			</table>

            </form>

    </tr>
</table>
<table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">

              <tr>

                <td width="40%"> <hr width="100%" size="1" class="hr1" >

                </td>

                <td width="20%" align="center" class="black9"><strong><font color="#0000FF">ע������</font></strong></td>

                <td width="40%"> <hr width="100%" size="1" class="hr1" >

                </td>

              </tr>

            </table>

            <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">

              <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">

                <td height="47"  >
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;1.���ڻ�����Ϳͻ����ó��Ļ������֣���˰������д���ǻ����Ķ�����<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;2.��ѡ���걨��˰ĿΪ��������˰ʱ����˰������д������<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;3.�걨�ͳ�ʱ����˰������������<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;4.ӡ��˰˰������ѡȡΪ��ί�д��������ҡ�ӡ��˰��˰Ŀ��ѡ�񡾱���˰��ģ�������ӡ��˰��������۵�λ���ν��ɱ���˰�
                </p>

                  </tr>

            </table>
	<script language="javascript">
	function addBank()
	{
		var obj=document.forms(0).zh;
		if(trim(obj.options[0].value)=="")
		{
			var it=new Option("","");
			obj.options.remove(it);
		}
		for(var i=0;i<bankInfo.length;i++)
		{
			var yName=bankInfo[i][0];
			var yValue=bankInfo[i][0];
			var b=true;
			for(var j=0;j<obj.options.length;j++)
			{
				var va=obj.options(j).value;
				if(yValue==va)
				{
					b=false;
					break;
				}
			}
			if(b)
			{
				var item=new Option(yName,yValue);
				obj.options.add(item);
			}					
		}
		setBankName(document.forms(0).zh);
	}
	//var szsmID=new Array();
	

	function reSzsmid()
	{
		//alert("1:"+document.forms[0].sklxdm.value);
		if(document.forms[0].sklxdm.value =='<%=CodeConstant.SKLXDM_ZCJK%>')
		{
			for(var k=0;k<document.forms[0].szsmID_zcjk.length;k++)
			{
				szsmID[k]=document.forms[0].szsmID_zcjk[k].value;
				//alert(szsmID[k]+"kkkk");
			}
		}
	    if(document.forms[0].sklxdm.value =='<%=CodeConstant.SKLXDM_SDJJ%>')
		{
			for(var k=0;k<document.forms[0].szsmID_sdjj.length;k++)
			{
				szsmID[k]=document.forms[0].szsmID_sdjj[k].value;
				//alert(szsmID[k]+"kkkk");
			}
		}
		
	}
		
	function checkKssl(obj)
	{
		var j;
		//alert("2:"+document.forms[0].sklxdm.value);
		if(document.forms[0].sklxdm.value =='<%=CodeConstant.SKLXDM_ZCJK%>')
		{
			for(var i=0;i<document.forms(0).kssl_zcjk.length;i++)
			{
				if(obj==document.forms(0).kssl_zcjk[i])
				{
					j=i;
					break;
				}
			
			}
			if(checkvalue(obj,0)&&formatCurrency(obj,0))
			{
				//alert(j+","+szsmID[j]);
				//for(var k=0;k<szsmID.length;k++)
				//{
				//	alert(szsmID[k]+"**");
				//	}
				run(obj,j,szsmID[j]);
			}
			else{
				return false;
			}
		}
	    if(document.forms[0].sklxdm.value =='<%=CodeConstant.SKLXDM_SDJJ%>')
		{
			for(var i=0;i<document.forms(0).kssl_sdjj.length;i++)
			{
				if(obj==document.forms(0).kssl_sdjj[i])
				{
					j=i;
					break;
				}
			
			}
			if(checkvalue(obj,0)&&formatCurrency(obj,0))
			{
				//alert(j+","+szsmID[j]);
				//for(var k=0;k<szsmID.length;k++)
				//{
				//	alert(szsmID[k]+"**");
				//	}
				run(obj,j,szsmID[j]);
			}
			else{
				return false;
			}
		}
	}
	function checkJsje(obj)
	{
		//alert("jsje");
		
		var j;
		//alert("3:"+document.forms[0].sklxdm.value);
		if(document.forms[0].sklxdm.value =='<%=CodeConstant.SKLXDM_ZCJK%>')
		{
			for(var i=0;i<document.forms(0).jsje_zcjk.length;i++)
			{
				if(obj==document.forms(0).jsje_zcjk[i])
				{
					j=i;
					break;
				}
			
			}
			if(checkvalue(obj,0)&&formatCurrency(obj,0))
			{
				//alert(j+","+szsmID[j]);
				//for(var k=0;k<szsmID.length;k++)
				//{
				//	alert(szsmID[k]+"**");
			//	}
				run(obj,j,szsmID[j]);
			}
			else{
				return false;
			}
		}
	    if(document.forms[0].sklxdm.value =='<%=CodeConstant.SKLXDM_SDJJ%>')
		{
			for(var i=0;i<document.forms(0).jsje_sdjj.length;i++)
			{
				if(obj==document.forms(0).jsje_sdjj[i])
				{
					j=i;
					break;
				}
			
			}
			if(checkvalue(obj,0)&&formatCurrency(obj,0))
			{
				//alert(j+","+szsmID[j]);
				//for(var k=0;k<szsmID.length;k++)
				//{
				//	alert(szsmID[k]+"**");
			//	}
				run(obj,j,szsmID[j]);
			}
			else{
				return false;
			}
		}
	}
	function checkSjse(obj)
	{
		//alert("sjse");
		var j;
		//alert("4:"+document.forms[0].sklxdm.value);
		if(document.forms[0].sklxdm.value =='<%=CodeConstant.SKLXDM_ZCJK%>')
		{
			for(var i=0;i<document.forms(0).sjse_zcjk.length;i++)
			{
				if(obj==document.forms(0).sjse_zcjk[i])
				{
					j=i;
					break;
				}
			}
			if(checkvalue(obj,0)&&formatCurrency(obj,0))
			{
				//alert(j+","+szsmID[j]);
				//for(var k=0;k<szsmID.length;k++)
			//	{
				//	alert(szsmID[k]+"**");
			//	}
				run(obj,j,szsmID[j]);
			}
			else{
				return false;
			}
		}
	    if(document.forms[0].sklxdm.value =='<%=CodeConstant.SKLXDM_SDJJ%>')
		{
			for(var i=0;i<document.forms(0).sjse_sdjj.length;i++)
			{
				if(obj==document.forms(0).sjse_sdjj[i])
				{
					j=i;
					break;
				}
			}
			if(checkvalue(obj,0)&&formatCurrency(obj,0))
			{
				//alert(j+","+szsmID[j]);
				//for(var k=0;k<szsmID.length;k++)
			//	{
				//	alert(szsmID[k]+"**");
			//	}
				run(obj,j,szsmID[j]);
			}
			else{
				return false;
			}
		}
	}


function hiddenSbfs()
{
	var sklxdm = document.forms[0].sklxdm.value;
	if(sklxdm =='<%=CodeConstant.SKLXDM_SDJJ%>')
	{
		var itemsize = itemsize_sdjj;
		if(itemsize == 1)
		{
			
			var szsmdm = document.forms[0].szsmdm.value;
			szsmdm = szsmdm.substr(0,2);
			if(szsmdm != '16')
			{
				document.forms[0].sbfs.style.display="none";
			}
		}
		if(itemsize > 1)
		{
			for(var i=0; i<itemsize; i++)
			{
				var szsmdm = document.forms[0].szsmdm[i].value;
				szsmdm = szsmdm.substr(0,2);
				if(szsmdm != '16')
				{
					document.forms[0].sbfs[i].style.display="none";
				}
			}
		}
	}
}
	

//����ʽ
function  changeJE(je)
{
	var strJe = je.toString();
    var rs = strJe.indexOf('.');  
     
    if (rs < 0) {  
         rs = strJe.length;  
         strJe += '.';  
    }  
    while (strJe.length <= rs + 2) {  
         strJe += '0';  
    }  
    return strJe;  
}	
</script>
<!--self end-->

    </td>
  </tr>
<tr>
  <td valign="bottom" align="center">
    <%@ include file="../include/bottom.jsp" %>
  </td>
</tr>
 </table>
</body>
</html>
