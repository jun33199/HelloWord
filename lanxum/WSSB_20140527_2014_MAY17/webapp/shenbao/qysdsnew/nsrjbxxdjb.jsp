<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ page import="com.ttsoft.bjtax.shenbao.constant.CodeConstant"%>
<%String static_contextpath = com.ttsoft.common.util.ResourceLocator
					.getStaticFilePath(request);
					
String _REQ_KEY_TEST_ORG_USE = (String)request.getAttribute("_REQ_KEY_TEST_ORG_USE");

String _REQ_KEY_TEST_ORG_TEST = (String)request.getAttribute("_REQ_KEY_TEST_ORG_TEST");

String _REQ_KEY_TEST_ORG_MSG = (String)request.getAttribute("_REQ_KEY_TEST_ORG_MSG");



			%>
<html>
	<head>
		<title>
			��˰�˻�����Ϣ�ǼǱ�
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
		<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
		<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>

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
	//var strXml = '<taxdoc><xsltVersion><![CDATA[20061102]]></xsltVersion><schemaVersion><![CDATA[20061102]]></schemaVersion><ywlx><![CDATA[010027]]></ywlx><ywczlx><![CDATA[0]]></ywczlx><nsrjbxx><jsjdm><![CDATA[06000100]]></jsjdm><nsrsbh><![CDATA[11010880207313X000]]></nsrsbh><nsrmc><![CDATA[��������־���ó���޹�˾]]></nsrmc><sbnd><![CDATA[2006]]></sbnd><sknd><![CDATA[2005]]></sknd><ssjjlxdm><![CDATA[173]]></ssjjlxdm><ssjjlxmc><![CDATA[˽Ӫ�������ι�˾]]></ssjjlxmc><lxdh><![CDATA[88511275]]></lxdh><jydz><![CDATA[����������1��һ���˹�ҵ�ڶ��о����Ժ�鱨����1112#]]></jydz><sshydm><![CDATA[7600]]></sshydm><sshymc><![CDATA[רҵ��������ҵ]]></sshymc><zsfsdm><![CDATA[03]]></zsfsdm><zsfsmc><![CDATA[��������]]></zsfsmc><cwkjzddm><![CDATA[12]]></cwkjzddm><cwkjzdmc><![CDATA[12]]></cwkjzdmc><cwkjzddm_old><![CDATA[12]]></cwkjzddm_old><gzglxsdm><![CDATA[01]]></gzglxsdm><gzglxsmc><![CDATA[]]></gzglxsmc><gzglxsdm_old><![CDATA[01]]></gzglxsdm_old><jmlxdm><![CDATA[]]></jmlxdm><jmlxmc><![CDATA[]]></jmlxmc><jmlxdm_old><![CDATA[]]></jmlxdm_old><swjgzzjgdm><![CDATA[0601]]></swjgzzjgdm><swjgzzjgmc><![CDATA[�������ط�˰���֪������]]></swjgzzjgmc><cjr><![CDATA[HDFJCSYH]]></cjr><cjrq><![CDATA[2006-12-10]]></cjrq><lrr><![CDATA[06000100]]></lrr><lrrq><![CDATA[2006-12-10]]></lrrq><sbrq><![CDATA[]]></sbrq><xtjb><![CDATA[]]></xtjb><bbmsf><![CDATA[1,2,5,8,9,10,11,12,13,14,15,16,17,19,20]]></bbmsf><skssksrq><![CDATA[2005-01-01 ]]></skssksrq><skssjsrq><![CDATA[2005-12-31 ]]></skssjsrq><version><![CDATA[20060101]]></version><sqspbh><![CDATA[]]></sqspbh></nsrjbxx></taxdoc>';
	var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
	var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
	var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
	var xmlDoc;
	
	//����xml
	function parseXmlOnLoad(){
		
		var js_REQ_KEY_TEST_ORG_USE = '<%=_REQ_KEY_TEST_ORG_USE%>';
		
		var js_REQ_KEY_TEST_ORG_TEST = '<%=_REQ_KEY_TEST_ORG_TEST%>';
		
		if(js_REQ_KEY_TEST_ORG_USE == '1'){
			
			if(js_REQ_KEY_TEST_ORG_TEST == '1'){
				alert('<%=_REQ_KEY_TEST_ORG_MSG%>');
			}else{
				alert('<%=_REQ_KEY_TEST_ORG_MSG%>');
				return false;
			}
			
		}
		
		var urlXSL="/XSLTWEB/model/010027/XSLT/" +strXSLTVersion+".xsl";
		//var urlXSL="shenbao/qysdsnew/nsrjbxxdjb.xsl";
	    if (strXml != ""){
	        if (! parseXml(strXml,urlXSL,"result"))
	            return false;
			xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
			xmlDoc.async = false;
			xmlDoc.loadXML(strXml);
	    }
	    
	    initNsrjbxx();
	
		return true;
	}
	
	function initNsrjbxx() {
	
		var cwkjzddm = document.getElementById("cwkjzddm").value;
		var cwkjzddm_old = document.getElementById("cwkjzddm_old").value;
		
		if (cwkjzddm == null || "" == cwkjzddm) { 
		
			document.getElementsById("cwkjzddm12").checked = true;
			
		} else { 
			
			var cwkjzddmid = "cwkjzddm" + cwkjzddm;
			
			//alert(cwkjzddmid);
			
			document.getElementById(cwkjzddmid).checked = true;
		}	
		
	}
//intFLag 0--���ڵ�����  �ʲ��ܶ�У��
//intFLag 1--������  ע���ʱ����У��
function formatData10(obj,intFlag)
{
					var tmpValue = trim(obj.value);
	var tmp = "";
	while(tmpValue.length>0 && tmpValue.charAt(0)=='0')
	{
		tmpValue = tmpValue.substring(1);
	}
	if(tmpValue.length <= 0)
	{
	   tmpValue=0;
	}
			switch (intFlag)
			{
			case 0:
				if(tmpValue == null || isNaN(tmpValue) || tmpValue < 0 ||!formatCurrency10(obj,0))
				{
					alert("�ʲ��ܶ����Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
				    tmp = tmpValue;
						if(tmp>100000){
							alert("�ʲ��ܶ����10��Ԫ�����ʵ��");
						}	
  				    return true;
				}
				break;
			case 1:
				
				if(tmpValue == null || isNaN(tmpValue) || tmpValue < 0 ||!formatCurrency10(obj,0))
				{
					alert("ע���ʱ�������Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
				    tmp = tmpValue;
				    if(tmp>100000){
							alert("ע���ʱ�����10��Ԫ�����ʵ��");
						}	
  				    return true;
				}

				break;
			default:
				return false;
				break;
			}
}

// ���ַ�����ʽ��Ϊ���Ҹ�ʽ
// parΪ0���Զ����0.00
// parΪ1�����Զ����
function formatCurrency10(obj,par)
{
	var tmpValue = trim(obj.value);
	var pointIndex = tmpValue.indexOf(".");
	if(pointIndex < 0)
	{
		if(tmpValue == null || tmpValue == "" || tmpValue == ".")
		{
			switch (par)
			{
			case 0:
				tmpValue = "0.00";
				break;
			case 1:
				tmpValue = "";
				break;
			default:
				break;
			}
		}
		else
		{
			tmpValue += ".00";
		}
	}
	else if(pointIndex == 0)
	{
		if(tmpValue.length > 1)
		{
			if(tmpValue.substring(1).length > 2)
			{
				return false;
			}
			else if(tmpValue.substring(1).length == 1)
			{
				tmpValue = "0" + tmpValue + "0";
			}
			else
			{
				tmpValue = "0" + tmpValue;
			}
		}
		else
		{
			switch (par)
			{
			case 0:
				tmpValue = "0.00";
				break;
			case 1:
				tmpValue = "";
				break;
			default:
				break;
			}
		}
	}
	else
	{
		afterpoint = (tmpValue.length-1) - pointIndex;
		if(afterpoint == 0)
		{
			tmpValue += "00";
		}
		else if(afterpoint > 2)
		{
			return false;
		}
		else if(afterpoint == 1)
		{
			tmpValue += "0";
		}
	}
	if(tmpValue > 9999999999999.99)
	{
		return false;
	}
	obj.value = tmpValue;
	return true;
}


	function getPostXml(objForm){	
		var retstr;
		
		setNsrjbxx();
		
		//��������
		getBasicXx("xsltVersion","/taxdoc");
		getBasicXx("schemaVersion","/taxdoc");
		getBasicXx("ywlx","/taxdoc");
		getBasicXx("ywczlx","/taxdoc");

		//��˰�˻�����Ϣ
		applendElement("/taxdoc","nsrjbxx",["jsjdm","nsrsbh","nsrmc","sbnd","sknd","ssjjlxdm","ssjjlxmc","jydz",
						"sshydm","sshymc","zsfsdm","zsfsmc","cwkjzdmc","cwkjzddm_old","gzglxsmc","gzglxsdm_old",
						"jmlxmc","jmlxdm_old","swjgzzjgdm","swjgzzjgmc","cjr","cjrq","lrr","lrrq","sbrq","xtjb",
						"bbmsf","skssksrq","skssjsrq","version","sqspbh","lxdh","cwkjzddm","gzglxsdm","jmlxdm","zczbje","zcze"]);
		
		//ȥ��ĩβ�Զ���ӵĻس�
		retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
		retstr = retstr.substr(0,retstr.length-2);
		
		return retstr;
	}
	
	function setNsrjbxx() {
		
		var i = 0 ;
		var length = 0;
		
		var cwkjzddm_show = "12";
		length = document.forms[0].cwkjzddm_show.length;		
		for (i = 0; i < length; i ++) {
			
			if (document.forms[0].cwkjzddm_show[i].checked) {
			
				cwkjzddm_show = document.forms[0].cwkjzddm_show[i].value;
				
			}
		
		}
		//alert("cwkjzddm_show:"+ cwkjzddm_show);	
		document.getElementById("cwkjzddm").value = cwkjzddm_show;
		
	}
	
	
	function changeLocalYwczlx(ywczlx){
		var rootNode = xmlDoc.documentElement;
	    var objCDATA =xmlDoc.createCDATASection(ywczlx);
			rootNode.selectSingleNode("//ywczlx").text = "";
			rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
	}
	
	    //��Ӧ���������Ļس���ѯ
	function jsjdmQuery(){
		if(event.keyCode==13) 
			event.keyCode = 9;
	}
	
	function doSubmit(actionType){
		var form=document.forms[0];
		if(actionType=='Save'){
			doSave();
		}else{
			doReturn();
		}
	}

	//mass �޸� 2013-12-6
	function doSave()
	{
		
		var old  = document.forms[0].ywczlx.value;
		//˰ԴΪ�ܵĲ����걨��ֹ����
		var sbrq_q = "20140101";
		var sbrq_z = "20140201";
		//��ȡ�û��ϵı�id
		var bbmsf_old=document.forms[0].bbmsf.value;
		var bbmsf_new="";
		//˰Դ��ʶ
		var sybs=document.forms[0].sybs.value;
		//�걨����
		var sbrq=document.forms[0].sbrq.value;
		if(sbrq>=sbrq_q){
			var cwkjzddm = "";
			var cwkjzddm_show = document.getElementsByName("cwkjzddm_show");
			for(var i=0;i<3;i++){
				if(cwkjzddm_show[i].checked){
					cwkjzddm =cwkjzddm_show[i].value;
				}
			}
			if(cwkjzddm==<%=CodeConstant.CWKJZD01 %>){
				bbmsf_new="<%=CodeConstant.BBMSF10_2008 %>";
			}else if (cwkjzddm==<%=CodeConstant.CWKJZD02 %>){
				bbmsf_new="<%=CodeConstant.BBMSF20_2008 %>";
			}else if (cwkjzddm==<%=CodeConstant.CWKJZD03 %>) {
				bbmsf_new="<%=CodeConstant.BBMSF30_2008 %>";
			}
				
			if(sybs!=<%=CodeConstant.CODE_QYSDS_SYBS_OTHER %>){
				//��˰Դ��ʶΪ���ܡ�ʱ
				if(sybs==<%=CodeConstant.CODE_QYSDS_SYBS_Z %>){
					if(sbrq<sbrq_z){
						alert("����2014��2��1�գ�����֮����д������Ϣ�ǼǱ�");
					}else{
						bbmsf_new +="<%=CodeConstant.BBMSF17_2012 %>";
						doSumbit2013Ts(bbmsf_old,bbmsf_new);
					}
				}
				if(sybs==<%=CodeConstant.CODE_QYSDS_SYBS_F %>){
					alert("����ҵֻ�����ҵ����˰��֧���������˰�걨��");
				}
				if(sybs==<%=CodeConstant.CODE_QYSDS_SYBS_D %>){
					doSumbit2013Ts(bbmsf_old,bbmsf_new);
				}
			}else{
				alert("����ҵ����ҵ����˰���ɵط�˰��ֹ�Ͻ��");
			}
		}else{
			SaveExec(old);
		}
		
	    return false; 
	}
	//mass 2013-12-6����
	//�ύ���ݽ����ж��Ƿ���ʾ
	function doSumbit2013Ts(bbmsf_old,bbmsf_new){
		var old  = document.forms[0].ywczlx.value;
		var queryFlag  = document.forms[0].queryFlag.value;
		var ywts="����ҵ�ѽ���"+document.forms[0].sknd.value+"�����ҵ����˰��������걨�����α������������걨���ݣ��Ƿ����?";
		if(queryFlag =="1" && bbmsf_new!=bbmsf_old){
  			if(confirm(ywts)){
  				SaveExec(old);
  			}
  		}else{
  			SaveExec(old);
  		}
	}
	
	function doReturn(){
	    if(confirm(confirmReturn)){
			document.forms[0].actionType.value="Return";
				document.forms[0].submit();
					return true;
	    }else{
	    	return false;
	    }
	}
	
	
	function SaveExec(old)
	{
		if (confirm ("����ʵ��д������Ϣ�������д������ܻᵼ��ĳЩ�����ظ�¼��")) 
		{
		    if (document.forms[0].ywczlx.value == 0)
		    {
		        document.forms[0].ywczlx.value = 1;
		    }
		    else if (document.forms[0].ywczlx.value == 1)
		    {
		        document.forms[0].ywczlx.value = 2;
		    }
		    else
		    {
		        alert("δ֪�Ĳ������ͣ�" + document.forms[0].ywczlx.value);
		        return false;
		    }
	
			document.forms[0].actionType.value="Save";
			if (g_objSI.Container != '')
			{
					if (!doSubmitXML(document.forms[0],"Save",true,"",true))
						{
								document.forms[0].ywczlx.value = old;
								return false;
						}	
			}else
			{
					if (!doSubmitXML(document.forms[0],"Save",false,"",true))
						{
								document.forms[0].ywczlx.value = old;
								return false;
						}	
			}
		
		}
		return true;
	}
	
	</script>
	
	<link href="<%=static_contextpath%>/css/text.css" rel="stylesheet" type="text/css">
	<link href="<%=static_contextpath%>/css/top-box.css" rel="stylesheet" type="text/css">
	<link href="css/beijing.css" rel="stylesheet" type="text/css">
	
	<style>
		input {
		    font-size: 9pt;
		    text-align: right;
		}
		
		.text-gray {
		 color: #3E6071;
		 background-color: #EEEEEE;
		}
	</style>
	</head>

	<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="return parseXmlOnLoad();">
 
	<form name="form1" method="post" action="czzsqyjbxxb.dc">
		<input name="actionType" type="hidden" id="actionType" value="Show">
	
		<table width="96%" align="center" cellspacing="0" class="table-99">
			<tr>
				<td align="center" valign="top" colspan="7">
					<jsp:include page="../../include/SbHeader.jsp" flush="true">
						<jsp:param name="name" value="��˰�˻�����Ϣ�ǼǱ�" />
						<jsp:param name="help_url" value="help/wssb/sbzl/lygfzwhztz/qysdsjd/qysdsjd-000.htm" />
					</jsp:include>
				</td>
			</tr>
			<tr>
				<td colspan="7">
					<br>
					<div id="result" align="center"></div>
				</TD>
			</TR>
			
			<tr>
				<td colspan="7">
					<br>
					<div align="left" style="color:red;">�������ҵ����˰�����걨�ͻ������</div>
				</TD>
			</TR>
			<tr>
				<td colspan="7">
					<div align="left" >
					<% 
					String[] link_array = (String[])(request.getAttribute("REQUEST_LINK_QYSDS")==null?"":request.getAttribute("REQUEST_LINK_QYSDS"));
					int count = link_array.length;
					int i = 0;
					for (i = 0; i < count; i++) {%>
					
					<A HREF="<%= link_array[i] %>" target="_blank">�����걨�ͻ���������ص�ַ<%=i+1 %></A><BR>
					
					<%} %>
										
					</div>
				</TD>
			</TR>
			<TR class="black9">
				<TD>
					<div  align="center">
					
					<% 
					
					boolean showSave = false;
					
					if(_REQ_KEY_TEST_ORG_USE == null || !"1".equals(_REQ_KEY_TEST_ORG_USE)){
						showSave = true;
					}
					
					
					if(_REQ_KEY_TEST_ORG_USE != null && "1".equals(_REQ_KEY_TEST_ORG_USE) && _REQ_KEY_TEST_ORG_TEST != null && "1".equals(_REQ_KEY_TEST_ORG_TEST)){
						showSave = true;
					}
					
					if(showSave) {
						
					%>
					
						<a style="cursor:hand" tabIndex="-1" onClick="javascript:return doSubmit('Save');return false;">
							<img name="spage" value="����" alt="����" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>/images/baocun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/baocun1.jpg" width="79" height="22" id="baocun">
						</a>
						
					<%}%>
						
						<a style="cursor:hand" tabIndex="-1" onClick="javascript:return doSubmit('Return');return false;">
							<img name="bpage" value="����" alt="����" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/fanhui1.jpg" width="79" height="22" id="fanhui">
						</a>
					</div>
					<div align="left">
					<font size="2">&nbsp;&nbsp;&nbsp;�Ҫ����˰������걨��ҵ����˰ʱ����˱�</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;�˵����</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1����˰�����ƣ��˰��Ǽ�֤������˰�˵�ȫ�ơ�</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2����������룺��д��˰���غ˷������չ����롣</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3����ϵ�绰����д��˰�˵�λ��˰��Ա��ϵ�绰�����ֻ����룩��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4�������������͡�������ҵ������˰��ǼǱ��е��й�������д��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5����ҵ����˰���շ�ʽ��ѡ��˶����յ���ҵ����ָ��˰����ظ�����������Ӫ���������ƺ�����������չ涨�ı�׼������Ȩ�޺ͷ������˶�Ӧ˰�����ʣ������ʣ���Ӧ��˰���һ�����շ�ʽ��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6����ҵ���</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;һ����ҵ����ָ��������ҵ����ҵ��λ��������塢������ҵ��λ�������ҵ��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������ҵ����ִָ�С�������ҵ����ƶȡ�������ҵ���׼�򡷵���ҵ���С��������С����չ�˾��֤ȯ��˾������Ͷ�ʹ�˾�����޹�˾��������˾������˾���䵱��˾�Ƚ�����ҵ��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ҵ��λ��������塢������ҵ��λ����ִָ�С���ҵ��λ���׼�򡷻�����Ӫ����֯����ƶȡ�����ҵ��λ��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7��ע���ʱ�����ҵ���˰�����ҵ����Ӫҵִ���ϵ�ע���ʱ���д����ҵ��λ���ա���ҵ��λ����֤�顷�Ŀ����ʽ���д��������尴�ա�������巨�˵Ǽ�֤�顷��ע���ʽ���д��������ҵ���˵�λ���ա�������ҵ��λ�Ǽ�֤�顷�Ŀ����ʽ���д��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ʲ��ܶָ��ҵӵ�л���Ƶ�ȫ���ʲ������������ʲ�������Ͷ�ʡ��̶��ʲ������μ������ʲ������������ʲ��ȣ���Ϊ��ҵ�ʲ���ծ����ʲ��ܼ��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;8���������е���ѡ����ڷ���ѡ��ġ��л�"��"��</font><br>
					</div>
				</TD>
			</TR>
		</table>
		<br>
		<jsp:include page="../../include/bottom.jsp" flush="true"/>
	
	</form>

	</body>
</html>
