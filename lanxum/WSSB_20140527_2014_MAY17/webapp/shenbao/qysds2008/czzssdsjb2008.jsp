<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ page import="com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.Constant"%>

<%
    String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
	System.out.println("======================123=============================");
%>
<html>
<head>
<title>��ҵ����˰��(��)��Ԥ����˰�걨��(A��)</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type = "text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="gojs/XmlBuild.js"></script>

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
var InputName=['nsfs', 'zfjg', 'yysr', 'yycb', 'lrze', 'sl','ynsdse', 'jmsdse', 'sjyjsdse', 'ybtsdse', 'ftsdse','zyczjzsdse', 'fzjgftsdse', 'fpbl', 'fpsdse'];
var InputNameAl=['��˰��ʽ','�ֻܷ���','�ֻܷ���','Ӫҵ����','Ӫҵ�ɱ�', '�����ܶ�', '˰��','Ӧ������˰��', '��������˰��', 'ʵ���ѽ�����˰��', 'Ӧ�����ˣ�������˰��', '�ܻ���Ӧ��̯������˰��','����������з��������˰��', '��֧������̯������˰��', '�������', '���������˰��'];
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xmlDoc;

//����xml
function parseXmlOnLoad()
{
	var urlXSL="/XSLTWEB/model/010031/XSLT/" +strXSLTVersion+".xsl";
    if (strXml != "")
    {
        if (! parseXml(strXml,urlXSL,"result"))
            return false;
	xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	xmlDoc.async = false;
	xmlDoc.loadXML(strXml);
    }

	
	setInput();

    return true;
}

function setInput(){
	//alert("1");
	//��ʼ��RADIO��
	initSelect();
	//alert("2");
	//��ʼ��RADIO��ɷ�����
	changeSelect();
	//alert("3");
	//��ʼ���д�6˰��
	document.forms[0].sl.value="25.00";
	//alert("4");
	//��ʼ������ʹ����
	checkFilter();
	//alert("5");

	//����Ƿ񱣴�ɹ����������ɹ�������ת
	<%
		if("1".equals((String)request.getAttribute(Constant.JUMP_FLAG_NAME))){
	%>
		//alert("!!!!!!");
		//if(confirm("����ɹ����Ƿ�ת�롶��ҵ����˰������˰��֧�����������")){
		//	doSubmit("Jump");
		//}
			if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
				//����ɹ�����ʾת�ֻܷ����걨��
				//if(confirm("����ɹ����Ƿ�ת�롶��ҵ����˰������˰��֧�����������")){
					alert("����ɹ�����ת��ת�롶��ҵ����˰������˰��֧���������");
					doSubmit("Jump");
				//}
			}else if(document.forms[0].lje_nsfs[0].checked==1){
				alert("�걨�ѳɹ���");
			}
		
	<%
	}		
	%>
	
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
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm","nsrsbh"]);
	//�˶���Ϣ
	applendElement("/taxdoc","hdxx",["jmzg","ybjmsl","qyzslx","cyl","xzqy","dezsse"]);
	//�걨��Ϣ
	applendElement("/taxdoc","sbxx",["fsdm","jd","nd","sbrq","skssksrq","skssjsrq"]);
	//�걨����
	applendElement("/taxdoc","sbsj",["nsfs", "zfjg", "yysr", "yycb", "lrze", "sl","ynsdse", "jmsdse", "sjyjsdse", "ybtsdse", "zjgftsdse","zyczjzsdse", "fzjgftsdse", "jzjnfzjgsdse", "fpbl", "fpsdse"]);
	
	//ȥ��ĩβ�Զ���ӵĻس�
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	return retstr;
}

function changeLocalYwczlx(ywczlx)
{
	 var rootNode = xmlDoc.documentElement;
    var objCDATA =xmlDoc.createCDATASection(ywczlx);
		rootNode.selectSingleNode("//ywczlx").text = "";
		rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
}

    //��Ӧ���������Ļس���ѯ
function jsjdmQuery(){
	if(event.keyCode==13) event.keyCode = 9;
}
	

function doSubmit(actionType){
	var form=document.forms[0];
	if(actionType=='Reset'){
       clearInput();
       //setInput();
	}else if(actionType=='Save'){
		doSave();
	}else if(actionType=='Delete'){
		doDelete();
	}else if(actionType=='Jump'){
		document.forms[0].actionType.value="Jump";
		document.forms[0].submit();
	}else{
		doReturn();
	}
}

function clearInput(){
		if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
		{
			//��ʼ����ǰ����¼����
			document.forms[0].nsfs.value="0.00";
			document.forms[0].zfjg.value="";
			document.forms[0].yysr.value="0.00";
			document.forms[0].yycb.value="0.00";
			document.forms[0].lrze.value="0.00";
			document.forms[0].sl.value="25";
			document.forms[0].ynsdse.value="0.00";
			document.forms[0].jmsdse.value="0.00";
			document.forms[0].sjyjsdse.value="0.00";
			document.forms[0].ybtsdse.value="0.00";
			document.forms[0].zjgftsdse.value="0.00";
			document.forms[0].zyczjzsdse.value="0.00";
			document.forms[0].fzjgftsdse.value="0.00";
			document.forms[0].jzjnfzjgsdse.value="0.00";
			document.forms[0].fpbl.value="0.00";
			document.forms[0].fpsdse.value="0.00";
			//��ʼ��radio��
			//document.forms[0].lje_nsfs[0].checked=1;
			//document.forms[0].lje_nsfs[1].checked=0;
			//��ʼ������
			//changeSelect();			
		}
}

function doReturn()
{
    if(confirm(confirmReturn))
    {
		document.forms[0].actionType.value="Return";
			document.forms[0].submit();
				return true;
    }else
    {
    		return false;
    }
}

function doDelete()
{	
		var old  = document.forms[0].ywczlx.value;
    if((document.forms[0].ywczlx.value == 0) && confirm(confirmDelete))
    {
			document.forms[0].actionType.value="Delete";
  			changeLocalYwczlx("3");
				if (g_objSI.Container != '')
				{
						if (!doSubmitXML(document.forms[0],"Delete",true,xmlDoc.xml,true))
						{
							changeLocalYwczlx(old);
							return false;
						}
				}else
				{	
						if (!doSubmitXML(document.forms[0],"Delete",false,xmlDoc.xml,true))
						{
							changeLocalYwczlx(old);
							return false;
						}	
				}
				return true;
   }else
   {
   			return false;
   }
}

function doSave(){
		if(document.forms[0].nsrmc.value==""){
			alert("������Ϣ����ȷ,����ʧ��,����������!");
			document.forms[0].jsjdm.focus();
			return false;
		}		
		if(!saveCheck()){
			//alert("������д����ȷ���޷����棡");
			return false;
		}

	var old  = document.forms[0].ywczlx.value;
    return SaveExec(old);
}
	<%/*����ʱ�����ݽ���У��*/%>
	function saveCheck(){
		//
		if(document.forms[0].lje_nsfs[1].checked==1){
			if((document.forms[0].lje_zfjg[0].checked==0)&&(document.forms[0].lje_zfjg[1].checked==0)){
				alert("��ѡ���ֻܷ������ͣ�");
				return false;
			}
		}
		//alert("saveCheckFilter...");
		//��ͷ��˰����ѡ���Ϊ������˰ʱ��ϵͳ�����ű����2-9�У���10��22������
		if(document.forms[0].lje_nsfs[0].checked==1){
			var value_3=(document.forms[0].yysr.value)*1;
			var value_4=(document.forms[0].yycb.value)*1;
			var value_5=(document.forms[0].lrze.value)*1;
			var value_6=((document.forms[0].sl.value)*1)/100;
			var value_7=(document.forms[0].ynsdse.value)*1;
			var value_8=(document.forms[0].jmsdse.value)*1;
			var value_9=(document.forms[0].sjyjsdse.value)*1;
			var value_10=(document.forms[0].ybtsdse.value)*1;
			if(!isNum(document.forms[0].yysr , null, null, null, null, 2)){
			return false;                         
			}
			if(!isNum(document.forms[0].yycb , null, null, null, null, 2)){
			return false;                         
			}
			if(!isNum(document.forms[0].lrze , null, null, null, null, 2)){
			return false;                         
			}
			if(!isNum(document.forms[0].jmsdse , null, null, null, null, 2)){
			return false;                         
			}
			if(!isNum(document.forms[0].sjyjsdse , null, null, null, null, 2)){
			return false;                         
			}
			//alert("1");
			if((value_5*value_6).toFixed(2)>=0){
				if(!((value_7==(value_5*value_6).toFixed(2))&&(value_7>=0))){
				alert("��6��\"Ӧ������˰��\"����4�С���5�У��ҵ�6�С�0");
				return false;
				}
			}
			//if(!((value_7==(value_5*value_6))&&(value_7>=0))){
			//	alert("��6��\"Ӧ������˰��\"����4�С���5�У��ҵ�6�С�0");
			//	return false;
			//}
			//alert("2");
			if(!((((value_5*value_6).toFixed(2)<0)&&(value_7==0))||((value_5*value_6).toFixed(2)>=0))){				
				alert("����4�С���5�Щ�0�����6��=0��");
				return false;
			}
			//alert("3");
			if(!((value_8<=value_7)&&(value_8>=0))){
				alert("��7��\"��������˰��\"�ܵ�6�У��ҵ�7�С�0��");
				document.forms[0].jmsdse.select();
				document.forms[0].jmsdse.focus();
				return false;
			}
			//alert("3");
			if(value_9<0){
				alert("��8��\"ʵ���ѽ�����˰������˰��\"Ӧ��0��");
				document.forms[0].sjyjsdse.select();
				document.forms[0].sjyjsdse.focus();
				return false;
			}
			//alert("4");
			if((value_7*1-value_8*1-value_9*1).toFixed(2)>=0){
				if(!((value_10*1)==(value_7*1-value_8*1-value_9*1).toFixed(2))){
					alert("��9��\"Ӧ�����ˣ�����˰��\" ����6��-��7��-��8�С�");
					return false;
				}
			}
			//alert("5");

		//����ͷ��˰����ѡ���Ϊ������˰-�ܻ���ʱ���ű����2-9��18-20�У������д�����
		}else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			var value_3=(document.forms[0].yysr.value)*1;
			var value_4=(document.forms[0].yycb.value)*1;
			var value_5=(document.forms[0].lrze.value)*1;
			var value_6=((document.forms[0].sl.value)*1)/100;
			var value_7=(document.forms[0].ynsdse.value)*1;
			var value_8=(document.forms[0].jmsdse.value)*1;
			var value_9=(document.forms[0].sjyjsdse.value)*1;
			var value_10=(document.forms[0].ybtsdse.value)*1;
			//ȡ��20�е�ǰֵ
			var value_18 = (document.forms[0].fzjgftsdse.value)*1;
			//ȡ��20.1�е�ǰֵ
			var value_21 = (document.forms[0].jzjnfzjgsdse.value)*1;
			
			if(!isNum(document.forms[0].yysr , null, null, null, null, 2)){
			return false;                         
			}
			if(!isNum(document.forms[0].yycb , null, null, null, null, 2)){
			return false;                         
			}
			if(!isNum(document.forms[0].lrze , null, null, null, null, 2)){
			return false;                         
			}
			if(!isNum(document.forms[0].jmsdse , null, null, null, null, 2)){
			return false;                         
			}
			if(!isNum(document.forms[0].sjyjsdse , null, null, null, null, 2)){
			return false;                         
			}
			if(!isNum(document.forms[0].jzjnfzjgsdse , null, null, null, null, 2)){
			return false;                         
			}
			
			//alert("1");
			if((value_5*value_6).toFixed(2)>=0){
				if(!((value_7==(value_5*value_6).toFixed(2))&&(value_7>=0))){
				alert("��6��\"Ӧ������˰��\"����4�С���5�У��ҵ�6�С�0");
				return false;
				}
			}
			//if(!((value_7==(value_5*value_6))&&(value_7>=0))){
			//	alert("��6��\"Ӧ������˰��\"����4�С���5�У��ҵ�6�С�0");
			//	return false;
			//}
			//alert("2");
			if(!((((value_5*value_6).toFixed(2)<0)&&(value_7==0))||((value_5*value_6).toFixed(2)>=0))){				
				alert("����4�С���5�Щ�0�����6��=0��");
				return false;
			}
			//alert("3");
			if(!((value_8<=value_7)&&(value_8>=0))){
				alert("��7��\"��������˰��\"�ܵ�6�У��ҵ�7�С�0��");
				document.forms[0].jmsdse.select();
				document.forms[0].jmsdse.focus();
				return false;
			}	
			//alert("3");
			if(value_9<0){
				alert("��8��\"ʵ���ѽ�����˰������˰��\"Ӧ��0��");
				document.forms[0].sjyjsdse.select();
				document.forms[0].sjyjsdse.focus();
				return false;
			}
			//alert("4");
			if((value_7*1-value_8*1-value_9*1).toFixed(2)>=0){
				if(!((value_10*1)==(value_7*1-value_8*1-value_9*1).toFixed(2))){
					alert("��9��\"Ӧ�����ˣ�����˰��\" ����6��-��7��-��8�С�");
					return false;
				}
			}
			
			if(value_21*1 < 0 || value_21*1 > value_18*1)
			{
				alert("�ܻ������������������Ӫ���ŷ�̯������˰�20.1�У�Ӧ��С��0����С�ڵ��ڷ�֧������̯������˰�20�У�");
				document.forms[0].jzjnfzjgsdse.select();
				document.forms[0].jzjnfzjgsdse.focus();
				return false;
			}
			//alert("5");
		//����ͷ��˰����ѡ���Ϊ������˰-��֧����ʱ���ű����21��22�У������д�����
		}else if(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[1].checked==1){			
			if(!isNum(document.forms[0].fpbl , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].fpsdse , null, null, null, null, 2)){
				return false;                         
			}
			
		}else{		
			
			//alert("8");
		}
		return true;
	}

function SaveExec(old)
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
		return true;
}


function checkNumInput(obj)
{
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(obj , null, null, null, null, 2)){
			return false;			
		}
		//��ʽ������
		formate(obj);
}

	<%/*�����1������*/%>
	function compute_Row_1(){
		//alert("compute_Row_1");
		//
		if(document.forms[0].lje_nsfs[0].checked==1){
			document.forms[0].nsfs.value=document.forms[0].lje_nsfs[0].value;
		}
		if(document.forms[0].lje_nsfs[1].checked==1){
			document.forms[0].nsfs.value=document.forms[0].lje_nsfs[1].value;
		}
		//
		changeSelect();
		//
		compute_Row_2();

	}
	<%/*�����2������*/%>
	function compute_Row_2(){
		//alert("compute_Row_2");	//alert("compute_Row_2("+document.forms[0].lje_zfjg[0].value+")("+document.forms[0].lje_zfjg[1].value+")");
		//alert("document.forms[0].zfjg.value="+document.forms[0].zfjg.value);
		//
		if(document.forms[0].lje_zfjg[0].checked==1){
			document.forms[0].zfjg.value=document.forms[0].lje_zfjg[0].value;
		}else if(document.forms[0].lje_zfjg[1].checked==1){
			document.forms[0].zfjg.value=document.forms[0].lje_zfjg[1].value;
		}
		//alert("document.forms[0].zfjg.value="+document.forms[0].zfjg.value);
		//		
		checkFilter();
	}
	<%/*�����3������*/%>
	function compute_Row_3(){
		//alert("compute_Row_3");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].yysr;
		//if(!(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1)){
		//	if((obj_input.value)*1<0){
		//		obj_input.value="0.00";
		//	}
		//	if(obj_input.value==""){
		//		obj_input.value="0.00";
		//	}
		//}
		//
	}
	<%/*�����4������*/%>
	function compute_Row_4(){
		//alert("compute_Row_4");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].yycb;
		//if(!(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1)){
		//	if((obj_input.value)*1<0){
		//		obj_input.value="0.00";
		//	}
		//	if(obj_input.value==""){
		//		obj_input.value="0.00";
		//	}
		//}
		//
	}
	<%/*�����5������*/%>
	function compute_Row_5(){
		//alert("compute_Row_5");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].lrze;
		//if(!(document.forms[0].lje_nsfs[1].checked==1&&document.forms[0].lje_zfjg[0].checked==1)){
		//	if((obj_input.value)*1<0){
		//		obj_input.value="0.00";
		//	}
		//	if(obj_input.value==""){
		//		obj_input.value="0.00";
		//	}
		//}
		//
		compute_Row_7();
	}
	<%/*�����6������*/%>
	function compute_Row_6(){
		//alert("compute_Row_6");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].sl;
		if((obj_input.value)*1<0){
			obj_input.value="25";
		}
		if(obj_input.value==""){
			obj_input.value="25";
		}
		//
		compute_Row_7();
	}
	<%/*�����7�����ݣ�ע��˴�Ӧ�����Զ�����*/%>
	function compute_Row_7(){
		//alert("compute_Row_7");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].ynsdse;
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//
		document.forms[0].ynsdse.value=((1*document.forms[0].sl.value/100)*document.forms[0].lrze.value).toFixed(2);
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//
		if((1*obj_input.value)<(1*document.forms[0].jmsdse.value)){
			alert("����˰�7�У�ӦС�ڵ���Ӧ������˰�6�У�����Ӧ���ڵ���0");
			return;
		}
		if(1*document.forms[0].jmsdse.value<0){
			alert("����˰�7�У�Ӧ���ڵ���0��");
			return;
		}
		//
		compute_Row_10();
	}

	<%/*�����8������*/%>
	function compute_Row_8(){
		//alert("compute_Row_8");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].jmsdse;
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//
		if((1*obj_input.value)>(1*document.forms[0].ynsdse.value)){
			alert("����˰�7�У�ӦС�ڵ���Ӧ������˰�6�У�����Ӧ���ڵ���0");
			document.forms[0].jmsdse.select();
			document.forms[0].jmsdse.focus();
			return false;
		}
		if(1*document.forms[0].jmsdse.value<0){
			alert("����˰�7�У�Ӧ���ڵ���0��");
			document.forms[0].jmsdse.select();
			document.forms[0].jmsdse.focus();
			return false;
		}
		//
		compute_Row_10();
	}
	<%/*�����9������*/%>
	function compute_Row_9(){
		//alert("compute_Row_9");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].sjyjsdse;
		//if((obj_input.value)*1<0){
		//	obj_input.value="0.00";
		//}
		//if(obj_input.value==""){
		//	obj_input.value="0.00";
		//}
		//
		if((obj_input.value)*1<0){
			//obj_input.value="0.00";
			alert("��8�У�ʵ���ѽ�����˰�Ӧ��С��0!");
			document.forms[0].sjyjsdse.select();
			document.forms[0].sjyjsdse.focus();
			return false;
		}
		compute_Row_10();
	}
	<%/*�����10�����ݣ�ע��˴�Ӧ�����Զ�����*/%>
	function compute_Row_10(){
		//alert("compute_Row_10");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].ybtsdse;
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//�����д�10����ֵ
		document.forms[0].ybtsdse.value=(1*document.forms[0].ynsdse.value-1*document.forms[0].jmsdse.value-document.forms[0].sjyjsdse.value).toFixed(2);
		if((1*document.forms[0].ybtsdse.value)<0){
			document.forms[0].ybtsdse.value="0.00";
		}
		//�������ܽ���-�ܻ�������½����Զ�����
		//alert("document.forms[0].lje_nsfs[0].checked="+document.forms[0].lje_nsfs[0].checked+"|\n"+"|"+"document.forms[0].lje_zfjg[0].checked="+document.forms[0].lje_zfjg[0].checked+"|\n"+"|"+"document.forms[0].lje_nsfs[1].checked="+document.forms[0].lje_nsfs[1].checked+"|\n"+"document.forms[0].lje_zfjg[1].checked="+document.forms[0].lje_zfjg[1].checked);
		
		if(document.forms[0].lje_nsfs[1].checked==true&&document.forms[0].lje_zfjg[0].checked==true){
			//alert("��ʼ���㣡");
			/**
			 * ���ܾ�������ȡ��18-20���Զ����㣬��Ϊ��˰���ֹ�¼��
			 * modify by tum at 2008-9-24
			 */
			//compute_Row_16();
			//compute_Row_17();
			//compute_Row_18();
		}
	}
	
	<%/*�����10�����ݣ�ע��˴�Ӧ�����Զ�����*/%>
	function compute_Row_10_1(){
		var obj_input=document.forms[0].ybtsdse;
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//�����д�10����ֵ
		document.forms[0].ybtsdse.value=(1*document.forms[0].ynsdse.value-1*document.forms[0].jmsdse.value-document.forms[0].sjyjsdse.value).toFixed(2);
		if((1*document.forms[0].ybtsdse.value)<0){
			document.forms[0].ybtsdse.value="0.00";
		}
		//�������ܽ���-�ܻ�������½����Զ�����
		//alert("document.forms[0].lje_nsfs[0].checked="+document.forms[0].lje_nsfs[0].checked+"|\n"+"|"+"document.forms[0].lje_zfjg[0].checked="+document.forms[0].lje_zfjg[0].checked+"|\n"+"|"+"document.forms[0].lje_nsfs[1].checked="+document.forms[0].lje_nsfs[1].checked+"|\n"+"document.forms[0].lje_zfjg[1].checked="+document.forms[0].lje_zfjg[1].checked);
		
		if(document.forms[0].lje_nsfs[1].checked==true&&document.forms[0].lje_zfjg[0].checked==true){
			//alert("��ʼ���㣡");
			//hc_16
			document.forms[0].zjgftsdse.value=(1*document.forms[0].ybtsdse.value*0.25).toFixed(2);
			var obj_input=document.forms[0].zjgftsdse;
			if((obj_input.value)*1<0){
				obj_input.value="0.00";
			}
			if(obj_input.value==""){
				obj_input.value="0.00";
			}
			//hc_17
			document.forms[0].zyczjzsdse.value=(1*document.forms[0].ybtsdse.value*0.25).toFixed(2);
			var obj_input=document.forms[0].zyczjzsdse;
			if((obj_input.value)*1<0){
				obj_input.value="0.00";
			}
			if(obj_input.value==""){
				obj_input.value="0.00";
			}
			//hc_18
			document.forms[0].fzjgftsdse.value=(1*document.forms[0].ybtsdse.value*0.5).toFixed(2);
			var obj_input=document.forms[0].fzjgftsdse;
			if((obj_input.value)*1<0){
				obj_input.value="0.00";
			}
			if(obj_input.value==""){
				obj_input.value="0.00";
			}
			//hc_21
			var obj_input=document.forms[0].jzjnfzjgsdse;
			if(obj_input.value==""){
				obj_input.value="0.00";
			}
		}
	}
	<%/*�����16�����ݣ�ע��˴�Ӧ�����Զ�����*/%>
	function compute_Row_16(){
		//alert("compute_Row_16");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		//
		//document.forms[0].zjgftsdse.value=(1*document.forms[0].ybtsdse.value*0.25).toFixed(2);
		//
		var obj_input=document.forms[0].zjgftsdse;
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
	}
	<%/*�����17�����ݣ�ע��˴�Ӧ�����Զ�����*/%>
	function compute_Row_17(){
		//alert("compute_Row_17");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		//
		//document.forms[0].zyczjzsdse.value=(1*document.forms[0].ybtsdse.value*0.25).toFixed(2);
		//
		var obj_input=document.forms[0].zyczjzsdse;
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
	}
	<%/*�����18�����ݣ�ע��˴�Ӧ�����Զ�����*/%>
	function compute_Row_18(){
		//alert("compute_Row_18");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		//
		//document.forms[0].fzjgftsdse.value=(1*document.forms[0].ybtsdse.value*0.5).toFixed(2);
		//
		var obj_input=document.forms[0].fzjgftsdse;
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
	}
	<%/*�����18������*/%>
	function compute_Row_19(){
		//alert("compute_Row_19");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].fpbl;
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
	}
	<%/*�����20������*/%>
	function compute_Row_20(){
		//alert("compute_Row_20");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].fpsdse;
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
	}

	<%/*�����20.1�����ݡ�Ҫ����ڣ����Ҳ����ڵڣ���������*/%>
	function compute_Row_21()
	{
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		
		//ȡ��20�е�ǰֵ
		var value_18 = document.forms[0].fzjgftsdse.value;
		//ȡ��20.1�е�ǰֵ
		var value_21 = document.forms[0].jzjnfzjgsdse.value;
		//alert("value_18 = " + value_18 + "\nvalue_21 = " + value_21);
		if(value_21*1 < 0 || value_21*1 > value_18*1)
		{
			alert("�ܻ������������������Ӫ���ŷ�̯������˰�20.1�У�Ӧ��С��0����С�ڵ��ڷ�֧������̯������˰�20�У�");
			document.forms[0].jzjnfzjgsdse.select();
			document.forms[0].jzjnfzjgsdse.focus();
			return;
		}
	}

	<%/*���ݳ�ʼ��������Ϊ��ѡ��ֵ����ʼ��������*/%>
	function initSelect(){
		//alert("document.forms[0].nsfs.value="+document.forms[0].nsfs.value);
		//alert("document.forms[0].zfjg.value="+document.forms[0].zfjg.value);
		if(document.forms[0].nsfs.value=="1")
		{
			document.forms[0].lje_nsfs[1].checked=1;
			if(document.forms[0].zfjg.value=="1"){
				document.forms[0].lje_zfjg[0].checked=1;
			}
			if(document.forms[0].zfjg.value=="2"){
				document.forms[0].lje_zfjg[1].checked=1;
			}
		}
		else if(document.forms[0].nsfs.value=="2"){
			document.forms[0].lje_nsfs[0].checked=1;
		}
		//
		document.forms[0].nsfs.value="0.00";
		document.forms[0].zfjg.value="";
		//
		//alert("document.forms[0].lje_nsfs[0].value="+document.forms[0].lje_nsfs[0].value+"|\n"+"|"+"document.forms[0].lje_zfjg[0].value="+document.forms[0].lje_zfjg[0].value+"|\n"+"|"+"document.forms[0].lje_nsfs[1].value="+document.forms[0].lje_nsfs[1].value+"|\n"+"document.forms[0].lje_zfjg[1].value="+document.forms[0].lje_zfjg[1].value);
	}
	
	<%/*����ѡ�����õ�ѡ��ѡ�����*/%>
	function changeSelect()
	{
		
		//
		if(document.forms[0].lje_nsfs[0].checked==1){
			document.forms[0].nsfs.value=document.forms[0].lje_nsfs[0].value;
		}else if(document.forms[0].lje_nsfs[1].checked==1){
			document.forms[0].nsfs.value=document.forms[0].lje_nsfs[1].value;
		}
		//
		if(document.forms[0].lje_nsfs[0].checked == 1)
		{
			//ѡ��Ϊ������˰�ģ����������˰��ʽ����ѡ��ͬʱ����Щ������λ���ɱ༭
			document.forms[0].lje_zfjg[0].checked = false;
			document.forms[0].lje_zfjg[0].disabled = true;
			document.forms[0].lje_zfjg[1].checked = false;
			document.forms[0].lje_zfjg[1].disabled = true;
			document.forms[0].lje_zfjg.disabled = true;
			//
			document.forms[0].zfjg.value="";
		} else if(document.forms[0].lje_nsfs[1].checked == 1){
			document.forms[0].lje_zfjg.disabled = false;
			if(document.forms[0].lje_zfjg[0].checked==0&&document.forms[0].lje_zfjg[1].checked==0){
				document.forms[0].lje_zfjg[0].checked = true;
				document.forms[0].lje_zfjg[1].checked = false;
			}
			//ѡ��Ϊ�ܻ���ʱ������������ѡ��Ȩ��
			document.forms[0].lje_zfjg[0].disabled = false;
			document.forms[0].lje_zfjg[1].disabled = false;
			//
			if(document.forms[0].lje_zfjg[0].checked==1){
				document.forms[0].zfjg.value=document.forms[0].lje_zfjg[0].value;
			}else if(document.forms[0].lje_zfjg[1].checked==1){
				document.forms[0].zfjg.value=document.forms[0].lje_zfjg[1].value;
			}
		}else{
			//Ĭ�������Ϊ������˰
			document.forms[0].lje_nsfs[0].checked=1;
			document.forms[0].lje_nsfs[1].checked=0;
			//ѡ��Ϊ������˰�ģ����������˰��ʽ����ѡ��ͬʱ����Щ������λ���ɱ༭
			document.forms[0].lje_zfjg[0].checked = false;
			document.forms[0].lje_zfjg[0].disabled = true;
			document.forms[0].lje_zfjg[1].checked = false;
			document.forms[0].lje_zfjg[1].disabled = true;
			document.forms[0].lje_zfjg.disabled = true;
		}
	}

	function checkFilter()
	{
		//������˰
		if(document.forms[0].lje_nsfs[0].checked==true)
		{
			document.forms[0].yysr.disabled=false; //2
			document.forms[0].yycb.disabled=false; //3
			document.forms[0].lrze.disabled=false; //4
			document.forms[0].sl.disabled=false; //5
			document.forms[0].ynsdse.disabled=false; //6
			document.forms[0].jmsdse.disabled=false; //7
			document.forms[0].sjyjsdse.disabled=false; //8
			document.forms[0].ybtsdse.disabled=false; //9
			document.forms[0].zjgftsdse.disabled=true; //18
			document.forms[0].zyczjzsdse.disabled=true; //19
			document.forms[0].fzjgftsdse.disabled=true; //20
			document.forms[0].jzjnfzjgsdse.disabled=true;��//20.1
			document.forms[0].fpbl.disabled=true; //21
			document.forms[0].fpsdse.disabled=true;  //22

			//���������д�ֵ���
			if(document.forms[0].yysr.value == null || document.forms[0].yysr.value == "")
			{
				document.forms[0].yysr.value="0.00";
			}
			if(document.forms[0].yycb.value == null || document.forms[0].yycb.value == "")
			{
				document.forms[0].yycb.value="0.00";
			}
			if(document.forms[0].lrze.value == null || document.forms[0].lrze.value == "")
			{
				document.forms[0].lrze.value="0.00";
			}
			if(document.forms[0].sl.value == null || document.forms[0].sl.value == "")
			{
				document.forms[0].sl.value="0.00";
			}
			if(document.forms[0].ynsdse.value == null || document.forms[0].ynsdse.value == "")
			{
				document.forms[0].ynsdse.value="0.00";
			}
			if(document.forms[0].jmsdse.value == null || document.forms[0].jmsdse.value == "")
			{
				document.forms[0].jmsdse.value="0.00";
			}
			if(document.forms[0].sjyjsdse.value == null || document.forms[0].sjyjsdse.value == "")
			{
				document.forms[0].sjyjsdse.value="0.00";
			}
			if(document.forms[0].ybtsdse.value == null || document.forms[0].ybtsdse.value == "")
			{
				document.forms[0].ybtsdse.value="0.00";
			}
			document.forms[0].zjgftsdse.value="";
			document.forms[0].zyczjzsdse.value="";
			document.forms[0].fzjgftsdse.value="";
			document.forms[0].jzjnfzjgsdse.value=""; //20.1
			document.forms[0].fpbl.value="";
			document.forms[0].fpsdse.value="";

			document.forms[0].yysr.style.backgroundColor="";
			document.forms[0].yycb.style.backgroundColor="";
			document.forms[0].lrze.style.backgroundColor="";
			document.forms[0].sl.style.backgroundColor="";
			document.forms[0].ynsdse.style.backgroundColor="";
			document.forms[0].jmsdse.style.backgroundColor="";
			document.forms[0].sjyjsdse.style.backgroundColor="";
			document.forms[0].ybtsdse.style.backgroundColor="";
			document.forms[0].zjgftsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].zyczjzsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].fzjgftsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].jzjnfzjgsdse.style.backgroundColor="#aaaaaa"; //20.1
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa";
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa";

		}
		//������˰���ܻ���
		else if(document.forms[0].lje_nsfs[1].checked==true&&document.forms[0].lje_zfjg[0].checked==true){
			document.forms[0].yysr.disabled=false;
			document.forms[0].yycb.disabled=false;
			document.forms[0].lrze.disabled=false;
			document.forms[0].sl.disabled=false;
			document.forms[0].ynsdse.disabled=false;
			document.forms[0].jmsdse.disabled=false;
			document.forms[0].sjyjsdse.disabled=false;
			document.forms[0].ybtsdse.disabled=false;
			document.forms[0].zjgftsdse.disabled=false;
			document.forms[0].zyczjzsdse.disabled=false;
			document.forms[0].fzjgftsdse.disabled=false;
			document.forms[0].jzjnfzjgsdse.disabled=false;��//20.1
			document.forms[0].fpbl.disabled=true;
			document.forms[0].fpsdse.disabled=true;

			//���������д�ֵ���
			if(document.forms[0].yysr.value == null || document.forms[0].yysr.value == "")
			{
				document.forms[0].yysr.value="0.00";
			}
			if(document.forms[0].yycb.value == null || document.forms[0].yycb.value == "")
			{
				document.forms[0].yycb.value="0.00";
			}
			if(document.forms[0].lrze.value == null || document.forms[0].lrze.value == "")
			{
				document.forms[0].lrze.value="0.00";
			}
			if(document.forms[0].sl.value == null || document.forms[0].sl.value == "")
			{
				document.forms[0].sl.value="0.00";
			}
			if(document.forms[0].ynsdse.value == null || document.forms[0].ynsdse.value == "")
			{
				document.forms[0].ynsdse.value="0.00";
			}
			if(document.forms[0].jmsdse.value == null || document.forms[0].jmsdse.value == "")
			{
				document.forms[0].jmsdse.value="0.00";
			}
			if(document.forms[0].sjyjsdse.value == null || document.forms[0].sjyjsdse.value == "")
			{
				document.forms[0].sjyjsdse.value="0.00";
			}
			if(document.forms[0].ybtsdse.value == null || document.forms[0].ybtsdse.value == "")
			{
				document.forms[0].ybtsdse.value="0.00";
			}
			if(document.forms[0].zjgftsdse.value == null || document.forms[0].zjgftsdse.value == "")
			{
				document.forms[0].zjgftsdse.value="0.00";
			}
			if(document.forms[0].zyczjzsdse.value == null || document.forms[0].zyczjzsdse.value == "")
			{
				document.forms[0].zyczjzsdse.value="0.00";
			}
			if(document.forms[0].fzjgftsdse.value == null || document.forms[0].fzjgftsdse.value == "")
			{
				document.forms[0].fzjgftsdse.value="0.00";
			}
			if(document.forms[0].jzjnfzjgsdse.value == null || document.forms[0].jzjnfzjgsdse.value == "")
			{
				document.forms[0].jzjnfzjgsdse.value="0.00";
			}
			document.forms[0].fpbl.value="";
			document.forms[0].fpsdse.value="";

			document.forms[0].yysr.style.backgroundColor="";
			document.forms[0].yycb.style.backgroundColor="";
			document.forms[0].lrze.style.backgroundColor="";
			document.forms[0].sl.style.backgroundColor="";
			document.forms[0].ynsdse.style.backgroundColor="";
			document.forms[0].jmsdse.style.backgroundColor="";
			document.forms[0].sjyjsdse.style.backgroundColor="";
			document.forms[0].ybtsdse.style.backgroundColor="";
			document.forms[0].zjgftsdse.style.backgroundColor="";
			document.forms[0].zyczjzsdse.style.backgroundColor="";
			document.forms[0].fzjgftsdse.style.backgroundColor="";
			document.forms[0].jzjnfzjgsdse.style.backgroundColor=""; //20.1
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa";
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa";

		}
		//������˰����֧����
		else if(document.forms[0].lje_nsfs[1].checked==true&&document.forms[0].lje_zfjg[1].checked==true){
			//alert("3");
			document.forms[0].yysr.disabled=true;
			document.forms[0].yycb.disabled=true;
			document.forms[0].lrze.disabled=true;
			document.forms[0].sl.disabled=true;
			document.forms[0].ynsdse.disabled=true;
			document.forms[0].jmsdse.disabled=true;
			document.forms[0].sjyjsdse.disabled=true;
			document.forms[0].ybtsdse.disabled=true;
			document.forms[0].zjgftsdse.disabled=true;
			document.forms[0].zyczjzsdse.disabled=true;
			document.forms[0].fzjgftsdse.disabled=true;
			document.forms[0].jzjnfzjgsdse.disabled=true;��//20.1
			document.forms[0].fpbl.disabled=false;
			document.forms[0].fpsdse.disabled=false;

			//���������д�ֵ���
			document.forms[0].yysr.value="";
			document.forms[0].yycb.value="";
			document.forms[0].lrze.value="";
			//document.forms[0].sl.value="";
			document.forms[0].ynsdse.value="";
			document.forms[0].jmsdse.value="";
			document.forms[0].sjyjsdse.value="";
			document.forms[0].ybtsdse.value="";
			document.forms[0].zjgftsdse.value="";
			document.forms[0].zyczjzsdse.value="";
			document.forms[0].fzjgftsdse.value="";
			document.forms[0].jzjnfzjgsdse.value=""; //20.1
			if(document.forms[0].fpbl.value == null || document.forms[0].fpbl.value == "")
			{
				document.forms[0].fpbl.value="0.00";
			}
			if(document.forms[0].fpsdse.value == null || document.forms[0].fpsdse.value == "")
			{
				document.forms[0].fpsdse.value="0.00";
			}

			document.forms[0].yysr.style.backgroundColor="#aaaaaa";
			document.forms[0].yycb.style.backgroundColor="#aaaaaa";
			document.forms[0].lrze.style.backgroundColor="#aaaaaa";
			document.forms[0].sl.style.backgroundColor="#aaaaaa";
			document.forms[0].ynsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].jmsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].sjyjsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].ybtsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].zjgftsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].zyczjzsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].fzjgftsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].jzjnfzjgsdse.style.backgroundColor="#aaaaaa"; //20.1
			document.forms[0].fpbl.style.backgroundColor="";
			document.forms[0].fpsdse.style.backgroundColor="";

		}else{
			document.forms[0].yysr.disabled=false;
			document.forms[0].yycb.disabled=false;
			document.forms[0].lrze.disabled=false;
			document.forms[0].sl.disabled=false;
			document.forms[0].ynsdse.disabled=false;
			document.forms[0].jmsdse.disabled=false;
			document.forms[0].sjyjsdse.disabled=false;
			document.forms[0].ybtsdse.disabled=false;
			document.forms[0].zjgftsdse.disabled=true;
			document.forms[0].zyczjzsdse.disabled=true;
			document.forms[0].fzjgftsdse.disabled=true;
			document.forms[0].jzjnfzjgsdse.disabled=true;��//20.1
			document.forms[0].fpbl.disabled=true;
			document.forms[0].fpsdse.disabled=true;

			document.forms[0].yysr.style.backgroundColor="";
			document.forms[0].yycb.style.backgroundColor="";
			document.forms[0].lrze.style.backgroundColor="";
			document.forms[0].sl.style.backgroundColor="";
			document.forms[0].ynsdse.style.backgroundColor="";
			document.forms[0].jmsdse.style.backgroundColor="";
			document.forms[0].sjyjsdse.style.backgroundColor="";
			document.forms[0].ybtsdse.style.backgroundColor="";
			document.forms[0].zjgftsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].zyczjzsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].fzjgftsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].jzjnfzjgsdse.style.backgroundColor="#aaaaaa";��//20.1
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa";
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa";
		}
		//alert("4");
		readOnlyFilter();
	}

	function readOnlyFilter(){
		//������˰
		if(document.forms[0].lje_nsfs[0].checked==true){
			document.forms[0].yysr.readOnly=false;
			document.forms[0].yycb.readOnly=false;
			document.forms[0].lrze.readOnly=false;
			document.forms[0].sl.readOnly=true;
			document.forms[0].ynsdse.readOnly=true;
			document.forms[0].jmsdse.readOnly=false;
			document.forms[0].sjyjsdse.readOnly=false;
			document.forms[0].ybtsdse.readOnly=true;
			document.forms[0].zjgftsdse.readOnly=true;
			document.forms[0].zyczjzsdse.readOnly=true;
			document.forms[0].fzjgftsdse.readOnly=true;
			document.forms[0].jzjnfzjgsdse.readOnly=true;��//20.1
			document.forms[0].fpbl.readOnly=true;
			document.forms[0].fpsdse.readOnly=true;

			document.forms[0].yysr.style.backgroundColor="";
			document.forms[0].yycb.style.backgroundColor="";
			document.forms[0].lrze.style.backgroundColor="";
			document.forms[0].sl.style.backgroundColor="#FAEBD7";
			document.forms[0].ynsdse.style.backgroundColor="#FAEBD7";
			document.forms[0].jmsdse.style.backgroundColor="";
			document.forms[0].sjyjsdse.style.backgroundColor="";
			document.forms[0].ybtsdse.style.backgroundColor="#FAEBD7";
			document.forms[0].zjgftsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].zyczjzsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].fzjgftsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].jzjnfzjgsdse.style.backgroundColor="#aaaaaa";��//20.1
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa";
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa";

		}else if(document.forms[0].lje_nsfs[1].checked==true&&document.forms[0].lje_zfjg[0].checked==true){
			// ������˰-�ܻ���
			//compute_Row_10_1();
			document.forms[0].yysr.readOnly=false;
			document.forms[0].yycb.readOnly=false;
			document.forms[0].lrze.readOnly=false;
			document.forms[0].sl.readOnly=true;
			document.forms[0].ynsdse.readOnly=true;
			document.forms[0].jmsdse.readOnly=false;
			document.forms[0].sjyjsdse.readOnly=false;
			document.forms[0].ybtsdse.readOnly=true;
			document.forms[0].zjgftsdse.readOnly=false;
			document.forms[0].zyczjzsdse.readOnly=false;
			document.forms[0].fzjgftsdse.readOnly=false;
			document.forms[0].jzjnfzjgsdse.readOnly=false;��//20.1
			document.forms[0].fpbl.readOnly=true;
			document.forms[0].fpsdse.readOnly=true;

			document.forms[0].yysr.style.backgroundColor="";
			document.forms[0].yycb.style.backgroundColor="";
			document.forms[0].lrze.style.backgroundColor="";
			document.forms[0].sl.style.backgroundColor="#FAEBD7";
			document.forms[0].ynsdse.style.backgroundColor="#FAEBD7";
			document.forms[0].jmsdse.style.backgroundColor="";
			document.forms[0].sjyjsdse.style.backgroundColor="";
			document.forms[0].ybtsdse.style.backgroundColor="#FAEBD7";
			document.forms[0].zjgftsdse.style.backgroundColor=""
			document.forms[0].zyczjzsdse.style.backgroundColor="";
			document.forms[0].fzjgftsdse.style.backgroundColor="";
			document.forms[0].jzjnfzjgsdse.style.backgroundColor="";��//20.1
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa";
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa";

		}else if(document.forms[0].lje_nsfs[1].checked==true&&document.forms[0].lje_zfjg[1].checked==true){
			// ������˰-��֧����
			document.forms[0].yysr.readOnly=true;
			document.forms[0].yycb.readOnly=true;
			document.forms[0].lrze.readOnly=true;
			document.forms[0].sl.readOnly=true;
			document.forms[0].ynsdse.readOnly=true;
			document.forms[0].jmsdse.readOnly=true;
			document.forms[0].sjyjsdse.readOnly=true;
			document.forms[0].ybtsdse.readOnly=true;
			document.forms[0].zjgftsdse.readOnly=true;
			document.forms[0].zyczjzsdse.readOnly=true;
			document.forms[0].fzjgftsdse.readOnly=true;
			document.forms[0].jzjnfzjgsdse.readOnly=false;��//20.1
			document.forms[0].fpbl.readOnly=false;
			document.forms[0].fpsdse.readOnly=false;

			document.forms[0].yysr.style.backgroundColor="#aaaaaa";
			document.forms[0].yycb.style.backgroundColor="#aaaaaa";
			document.forms[0].lrze.style.backgroundColor="#aaaaaa";
			document.forms[0].sl.style.backgroundColor="#aaaaaa";
			document.forms[0].ynsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].jmsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].sjyjsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].ybtsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].zjgftsdse.style.backgroundColor="#aaaaaa"
			document.forms[0].zyczjzsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].fzjgftsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].jzjnfzjgsdse.style.backgroundColor="#aaaaaa";��//20.1
			document.forms[0].fpbl.style.backgroundColor="";
			document.forms[0].fpsdse.style.backgroundColor="";
		}else{
			document.forms[0].yysr.readOnly=false;
			document.forms[0].yycb.readOnly=false;
			document.forms[0].lrze.readOnly=false;
			document.forms[0].sl.readOnly=true;
			document.forms[0].ynsdse.readOnly=true;
			document.forms[0].jmsdse.readOnly=false;
			document.forms[0].sjyjsdse.readOnly=false;
			document.forms[0].ybtsdse.readOnly=true;
			document.forms[0].zjgftsdse.readOnly=true;
			document.forms[0].zyczjzsdse.readOnly=true;
			document.forms[0].fzjgftsdse.readOnly=true;
			document.forms[0].jzjnfzjgsdse.readOnly=true;��//20.1
			document.forms[0].fpbl.readOnly=true;
			document.forms[0].fpsdse.readOnly=true;

			document.forms[0].yysr.style.backgroundColor="";
			document.forms[0].yycb.style.backgroundColor="";
			document.forms[0].lrze.style.backgroundColor="";
			document.forms[0].sl.style.backgroundColor="#FAEBD7";
			document.forms[0].ynsdse.style.backgroundColor="#FAEBD7";
			document.forms[0].jmsdse.style.backgroundColor="";
			document.forms[0].sjyjsdse.style.backgroundColor="";
			document.forms[0].ybtsdse.style.backgroundColor="#FAEBD7";
			document.forms[0].zjgftsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].zyczjzsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].fzjgftsdse.style.backgroundColor="#aaaaaa";
			document.forms[0].jzjnfzjgsdse.style.backgroundColor="#aaaaaa";��//20.1
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa";
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa";
		}
		
	}
function formate(obj){

	if(obj.value==""||obj.value==null){
		obj.value="0.00";	
	}else{
		var temp = trim(obj.value+"");
		if(temp.indexOf(".")!=-1){
			var len=temp.indexOf(".")+1;
			if(temp.length-len==1)
			temp = temp+"0";
			var zs=trim(temp.substring(0,temp.indexOf(".")));
			if(zs==""){
				temp="0"+temp;
			}
		}else{
			temp=temp+".00";
		}
		obj.value=temp;	
		formateNum(obj);
	}		
}

/**
 * ��ʽ������������0��ͷ
 *
 * @param �ı���
 */
 function formateNum(obj){
 	var tempNum=obj.value;
 	var num=trim(tempNum.substring(0,tempNum.indexOf(".")));
 	if(num.length>1){
 		num=num.substring(0,num.length-1);
 		var i;
 		for(i=0;i<num.length;i++){
 			var itemp=num.substring(i,i+1);
 			if(itemp!="0"){
 				break;
 			}
 		}
 		tempNum=tempNum.substring(i,tempNum.length);
 		obj.value=tempNum;
 	}
 
 }	

</script>

<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">

<style>
input {
    font-size: 9pt;
    text-align: right;
}

.text-gray {
 color: #3E6071;
 background-color: #EEEEEE;
}
.text-noborder {
  font-size: 9pt;
 color: #3E6071;
 background-color: #F3F5F8;
 border-top: 0px none #F3F5F8;
 border-right: 0px none #F3F5F8;
 border-bottom: 0px none #F3F5F8;
 border-left: 0px none #F3F5F8;
}
</style>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="return parseXmlOnLoad();"> 
 <html:errors/>
<form name="czzsjbForm" action="czzsqyjb2008.dc">
<input name="actionType" type="hidden" id="actionType" value="Show">
	<table width="1000" align="center" cellspacing="0" class="table-99" onKeyDown="jsjdmQuery()">
		<tr>
		<td align="center" valign="top" colspan="7">
        <jsp:include page="../../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="��ҵ����˰��(��)��Ԥ����˰�걨��(A��)" />
        <jsp:param name="help_url" value="help/wssb/sbzl/lygfzwhztz/qysdsjd/qysdsjd-000.htm"/>
        </jsp:include>
    </td>
		</tr>
		<tr><td colspan="7"><br><div id="result" align="center"></div></td></tr>
		<tr>
		 <td align="center" colspan="7">
		 <div  align="center">
		  <table>
		    <TR class="black9">
				<TD>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Save');return false;"><img  name="spage" value="����" alt="����" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>/images/baocun2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/baocun1.jpg" width="79" height="22" id="baocun"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Delete');return false;"><img name="dpage" value="ɾ��" alt="ɾ��" onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>/images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/shanchu1.jpg" width="79" height="22" id="shanchu"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Reset');return false;"><img name="qpage" value="���" alt="���" onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>/images/qingchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/qingchu1.jpg"  width="79" height="22" id="qingchu"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Return');return false;"><img  name="bpage" value="����" alt="����" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/fanhui1.jpg"  width="79" height="22" id="fanhui"></a>
				
				</TD>
				</TR>
		    </table>
</div>
		  </td>
		</tr>
	</table>
	<br>
	<br>
	<br>

		<jsp:include page="../../include/bottom.jsp" flush="true"/>

    </td>
  </tr>
</table>
</form>

</body>
</html>