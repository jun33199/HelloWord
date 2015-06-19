<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="java.util.*"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Jmfl"%>
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
    List jmflList = (List)session.getAttribute("GXJSFUDM");
%>
<html>
<head>
<title>20 18���϶��ļ����Ƚ��ͷ�����ҵ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/date.js"></script>
<script language="JavaScript" type="text/javascript" src="js1/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript" src="js/qysdsnew.js"></script>
<script language="JavaScript" type="text/javascript" src="js/calculate.js"></script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">

<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
<script language="JavaScript">
var jmfl= new Array(<%=jmflList.size()%>);
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
        
        for(int i=0;i<jmflList.size();i++)
        {
        	Jmfl tmpJmfl = (Jmfl)jmflList.get(i);
        	out.println("jmfl["+i+"] = [\""+tmpJmfl.getJmfldm()+"\",\""+tmpJmfl.getJmflmc()+"\"];");
        }        
%>
g_objSI.Container = "<%=containerName%>";
var strXml = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
//var xmlDoc;

function parseXml_test(strXml,urlXSL,idname)
{
	//alert('0000');
	var objOutput = document.getElementById(idname);
	//alert('111111');
	var b1=g_Doc.parseXmlDoc.loadXML(strXml);
	//alert(b1);
	//alert('2222222');
	var b2=g_Doc.xslDoc.load(urlXSL);
	//alert(b2);
	//alert('3333333');
	if(b1==false)
	{
		//alert('444444');
		return false;
	}
	if(b2==false)
	{
		//alert('555555');
		return false;
	}
	//alert('6666666')
	objOutput.innerHTML = g_Doc.parseXmlDoc.transformNode(g_Doc.xslDoc);
	return true;
}
function parseXmlOnLoad()
{
    //document.forms[0].StartDate1.value = document.all("ZSQSRQ").value;
    //document.forms[0].StartDate2.value = document.all("ZSZZRQ").value;	
	//alert(strXml);
	//insertJmfl();
	
    var xslPath='jmba/jsxjqy20/jsxjqy20.xsl';
	if (strXml != "")
    {
        if (! parseXml_test(strXml,xslPath,"result")){
        //alert("dd");
        return false;
        }
    }
    //alert(result)
    insertJmfl();
    fudmload();
    return true;
}

function getPostXml(objForm)
{	
	var retstr;
	//��������
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//��˰����Ϣ
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);

	applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc","bajmsehbl"	,"fhwjmc","qsrq","ztdm","ztmc","jzrq","szdm","szmc","bsfsdm","bsfsmc"]);
	//�걨����
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
	var element=document.getElementById(strTag);
	var objTemp=temp;
	var node=g_Doc.XMLDoc.createElement(strTag);
	objTemp.appendChild(node);
	
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
	var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
	var objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
	objNode.appendChild(objTemp);
	getChildren(objTemp,"XH");
	getChildren(objTemp,"BASQWSH");
	getChildren(objTemp,"JSJDM");
	getChildren(objTemp,"BAND");
	getChildren(objTemp,"FWYWFWDM");
	getChildren(objTemp,"SFYJSXJFWQY");
	getChildren(objTemp,"ZSBH");
	getChildren(objTemp,"ZSQSRQ");
	getChildren(objTemp,"ZSZZRQ");
	getChildren(objTemp,"SFYNSHGZM");
	getChildren(objTemp,"JMSE");
	getChildren(objTemp,"QTZL");
	getChildren(objTemp,"CJR");
	getChildren(objTemp,"CJRQ");
	getChildren(objTemp,"LRR");
	getChildren(objTemp,"LRRQ");	
}

function doSubmitXML(objForm,aType,isSign,xmldata,ifsubmit)
{
	var strToPost;
	initXMLObject();
	
	if (!objForm)
	{
		return false;
	}
	if (!document.all("XML_Form"))
	{
		document.body.insertAdjacentHTML("BeforeEnd",IntHtml.HTMLFormString);
	}
	else
	{
		document.all("XML_Form").strSignData.value = "";
		document.all("XML_Form").actionType.value = "";
		document.all("XML_Form").appType.value = "";
		document.all("XML_Form").strXMLData.value = "";
		document.all("XML_Form").REQUEST_TOKEN.value = "";
	}
	var strToPost = "";
	var strtmp = "";
	if (xmldata != "")
	{
		strtmp = xmldata;
	}
	else
	{
		strtmp = getPostXml(objForm);
	}
	strToPost = strtmp.replace(/\r\n/g,"");
	var sSignData="";
	var strAppType="";
	if(isSign)
	{
		strAppType = gGetGlobalAppType();
		sSignData = gSign(strToPost);
		if(sSignData == -1)
		{
			return false;
		}
	}
	document.all("XML_Form").strXMLData.value = strToPost;
	document.all("XML_Form").actionType.value = aType;
	document.all("XML_Form").strSignData.value = sSignData;
	document.all("XML_Form").appType.value = strAppType;
	//alert(document.all("XML_Form").strXMLData.value);
	//document.all("XML_Form").REQUEST_TOKEN.value = strREQUEST_TOKEN;
	document.all("XML_Form").action = objForm.action;
	document.all("XML_Form").target = objForm.target;
	//Submit Form/////////////////////////////////
	if (ifsubmit)
	{
	    //alert('�����ύ��');
		document.all("XML_Form").submit();
	}
	return true;
}

function checkall(){
  if(document.forms[0].ZSBH.value == ''){
    alert('֤���Ų���Ϊ�գ�');
    document.forms[0].ZSBH.focus();
	return false;
  }
  if(document.forms[0].fudm.value == ''){
    alert('�����Ƚ��ͷ���ҵ��Χ����Ϊ�գ�');
	return false;
  }
  if(document.forms[0].SFYNSHGZM.value == '1'){
    alert('û������ϸ�֤����');
	return false;
  }  
  if(document.forms[0].ZSQSRQ.value == ''){
    document.forms[0].ZSQSRQ.focus();
    alert('֤����Ч������Ϊ�գ�');
	return false;
  }
  if(document.forms[0].ZSZZRQ.value == ''){
    alert('֤����Ч��ֹ����Ϊ�գ�');
    document.forms[0].ZSZZRQ.focus();
	return false;
  }
  if(document.forms[0].JMSE.value == ''){
    alert('����Ԥ�Ƶļ���˰���Ϊ�գ�');
    document.forms[0].JMSE.focus();
	return false;
  }else{
    if(checkJmse())
     return true;
    else
     return false;
  }        
}


function doSave(){
    if(!checkall()){
       return false;
    }
    document.forms[0].FWYWFWDM.value = document.forms[0].fudm.value
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
		document.forms[0].actionType.value="Save";
			if (g_objSI.Container != '')
			{  //alert("4");
				if (!doSubmitXML(document.forms[0],"Save",true,"",true))
				{  //alert("5");
					 document.forms[0].ywczlx.value = old;
					return false;
				}
			}
			else
			{  //alert("6");
			   if(!doSubmitXML(document.forms[0],"Save",false,"",true))
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
    if(!checkall()){
       return false;
    }
    document.forms[0].FWYWFWDM.value = document.forms[0].fudm.value
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
		document.forms[0].actionType.value="Commit";
			if (g_objSI.Container != '')
			{  //alert("4");
				if (!doSubmitXML(document.forms[0],"Commit",true,"",true))
				{  //alert("5");
					 document.forms[0].ywczlx.value = old;
					return false;
				}
			}
			else
			{  //alert("6");
			   if(!doSubmitXML(document.forms[0],"Commit",false,"",true))
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


function fudmload()
{
  document.forms[0].fudm.value =document.forms[0].FWYWFWDM.value;
}
function clickhgzm(lx)
{
  document.forms[0].SFYNSHGZM.value =lx;
}

function insertJmfl()
{
	var yValue;
	var yName;
	var item
	var obj = document.forms[0].fudm;
	for(var i=0;i<jmfl.length;i++)
	{
		yValue=jmfl[i][0];
		yName=jmfl[i][1];
		item=new Option(yName,yValue);
		obj.options.add(item);						
	}
}

function checkJmse(){
		var jmse = document.all("JMSE").value;
		if(jmse == ""){
			alert("����Ԥ�Ƶļ���˰���Ϊ�գ�");
			document.forms[0].JMSE.focus();
			return false;
		}else{
			flg=0;
			zfgs = 0;
			cmp="0123456789.";
			for (var i=0;i<jmse.length;i++){  
				tst=jmse.substring(i,i+1);
				if (cmp.indexOf(tst)<0){
					flg++; 
				}else{
					if(tst == "."){
						zfgs++;
					}
				}
			} 
			if (flg!=0 || zfgs > 1){ 
				alert("����Ԥ�Ƶļ���˰���ʽ����ȷ��"); 
				document.forms[0].JMSE.focus();
				return false; 
			}
		}
		formate(document.forms[0].JMSE);
		var jmse1 = jmse;
		jmse = round2(jmse1);
		document.all("JMSE").value = jmse;
		return true;
}

function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
} 
</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="parseXmlOnLoad()">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="../../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="¼�뾭�϶��ļ����Ƚ��ͷ�����ҵ��������" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>

<form name="form1" method="POST" action="/shenbao/lrjsxjfwqy.dc">
	<input name="actionType" type="hidden" id="actionType">
	
<table  width="770"  border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
<td valign="top" class="title"> <br>

      <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
        <tr>
          <td class="1-td1" >20 18���϶��ļ����Ƚ��ͷ�����ҵ</td>
        </tr>
        <tr>
            <td class="1-td2">
              <div id="result"></div>   
              
                  <table width="100%" border="0" align="center">
                    <tr align="center">  
                    	 
                    	<td >
                    	<img src="<%=static_contextpath%>images/baocun1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/baocun2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="����" onclick="javascript:return doSave();">
                      </td>   
                    	
                    	 
                      <td >
                      <img src="<%=static_contextpath%>images/tijiao1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/tijiao2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/tijiao1.jpg'" alt="�ύ" onclick="javascript:return doCommit();">
                      	
                      </td>           
                    	
                      <td >
                        <img src="<%=static_contextpath%>images/fanhui1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="javascript:history.go(-1);">
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
<br>&nbsp;&nbsp;1�Ƿ��о��϶��ļ����Ƚ��ͷ�����ҵ֤�飬ѡ�� ���ǡ��� ���񡱣�����Ϊ��ѡ�����˰�����ύ����ʱ¼��ϵͳ��
<br>&nbsp;&nbsp;2֤����Ϊ��¼�������ϱ��¼�����
<br>&nbsp;&nbsp;3֤����Ч��Ϊ������ɸ��������˵�ѡ����Ӧʱ�����С�
<br>&nbsp;&nbsp;4�Ƿ�������ϸ�֤����ѡ���ǡ��򡰷���Ŀ��
<br>&nbsp;&nbsp;5�������15%Ԥ�Ƶļ���˰���������ɲ�����Ա�ֹ�¼��ϵͳ����������������ұ�����λС������λ��Ԫ��
<br>&nbsp;&nbsp;6����˰�����Ҫ���͵��������ϣ��ֹ�¼��ϵͳ����¼����Ϊ�Ǳ�¼�

<br><br>
<br>&nbsp;&nbsp;3.1�������б�����Ŀ����Ϊ�գ������ֹ���ύ�������롣
<br>&nbsp;&nbsp;3.2����˰���걨����˰������ͨ�����ʱ�����л����񹲺͹���ҵ����˰����걨��A�ࣩ������5��˰���Ż���ϸ����38�п��ţ�������д���ݣ����򣬶�Ӧ�д�ϵͳĬ��Ϊ������༭״̬������38�У���Ҫ�����������֧�֣����ڱ༭״̬ʱ��ϵͳ������˰���Ż���ϸ��������д������Ӧ����˰��Ŀ��������丽�����˰��Ŀ�ϼ�����ֵ����38�С�
<br>&nbsp;&nbsp;3.3 �������С�����˰�����Ҫ���͵��������ϡ�Ϊѡ¼������Ƿ�¼����������ƣ�
</td>
			  </tr>
            </table>
            
            <br> 
            </td>
					
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
<%@ include file="../../../include/bottom.jsp" %>
 </td>
</tr>
</table>
</body>
</html>

