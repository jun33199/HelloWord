<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%
    String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);

%>
<html>
<head>
<title>��ҵ����˰��(��)��Ԥ����˰�걨��(B��)</title>
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
var InputName=['syze','yssdl','ynssde','sl','ynsdse','jmsdse','yyjsdse','ybsdse'];
var InputNameAl=['�����ܶ�','˰����غ˶���Ӧ˰������','Ӧ��˰���ö�','˰��','Ӧ������˰��','��������˰��','��Ԥ������˰��','Ӧ�����ˣ�����˰��'];
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xmlDoc;

//����xml
function parseXmlOnLoad()
{
	var urlXSL="/XSLTWEB/model/010029/XSLT/" +strXSLTVersion+".xsl";
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

	var cylValue = document.forms[0].cyl.value;
	var dezsseValue = document.forms[0].dezsse.value;
	
			<%/*��ҵ��������*/%>
		var qyzslx=document.forms[0].qyzslx.value;
			
		if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){
			document.getElementById('1_1').focus();
			<%/*Ӧ˰������(�༴�������ʡ�)*/%>
			<%/*���õ�2��*/%>
			var cylValue = document.forms[0].cyl.value;

			var cylValue21 = document.getElementById('2_1').value;
			if(cylValue21=="null"||cylValue21==null||parseFloat(cylValue21)==0 || cylValue21==""){
				document.getElementById('2_1').value = Math.round(cylValue*100)/100;
				formate(document.getElementById('2_1'));		
			}
			var obj = document.forms[0].yssdlView;
			obj.readOnly=true;
			obj.className='text-gray';
			obj.value=parseFloat(document.getElementById('2_1').value)*100+"%";
			<%/*���õ�3��*/%>
			var obj = document.forms[0].ynssde;
			obj.readOnly=true;
			obj.className='text-gray';
			<%/*���õ�11��*/%>
			document.forms[0].sl.value="0.25";	
			document.getElementById('11_1_1').value=parseFloat(document.getElementById('11_1').value)*100+"%";
			var obj = document.getElementById('11_1_1');
			obj.readOnly=true;
			obj.className='text-gray';
				
		}
		if(qyzslx ==4){
			<%/*��������*/%>
			var obj = document.forms[0].syze;
			obj.readOnly=true;
			obj.className='text-noborder';
			obj.value='*';
			obj.style.cssText='text-align:center';
			obj.size='1';
			obj.onblur="";
			var obj = document.forms[0].yssdlView;
			obj.className='text-noborder';
			obj.value='*';
			obj.style.cssText='text-align:center';
			obj.size='1';
			var obj = document.forms[0].ynssde;
			obj.readOnly='true';
			obj.className='text-noborder';
			obj.value='*';
			obj.style.cssText='text-align:center';
			obj.size='1';
			var obj = document.forms[0].slView;
			obj.readOnly='true';
			obj.className='text-noborder';
			obj.style.cssText='text-align:center';
			obj.value='*';
			obj.size='1';
			var obj= document.forms[0].ynsdse;
			obj.readOnly=false;
			obj.className='text-border';
			obj.focus();
		}
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
	applendElement("/taxdoc","sbsj",["syze","yssdl","ynssde","sl","ynsdse","jmsdse","yyjsdse","ybsdse"]);
	
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
       setInput();
	}else if(actionType=='Save'){
		doSave();
	}else if(actionType=='Delete'){
		doDelete();
	}else{
		doReturn();
	}
}

function clearInput(){
		if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
		{
	   		//��ҵ��˰����
			var qyzslx=document.forms[0].qyzslx.value;
			
	   		if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){
	   			for(var i=1; i < 16; i++){
					obj = eval("document.getElementById('" + i+ "_1')");
					if (obj.value!="*"&&i!=2&&i!=11)
						obj.value = "0.00";
	   			}
	   		}
	   		else if(qyzslx == 4){
	   			for(var i=12; i < 16; i++){
					obj = eval("document.getElementById('" + i+ "_1')");
					obj.value = "0.00";
	   			}
	   		}
	   		else{
	   			for(var i=1;i<6;i++){
	   				obj = eval("document.getElementById('" + i+ "_1')");
					if (obj.value!="*")
						obj.value = "0.00";
	   			}
	   		}
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
		
		
		
		<%/*��ҵ��˰����*/%>
		var qyzslx=document.forms[0].qyzslx.value;
		if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){
			if(!saveCheck(1,null))return false;
			if(!saveCheck(13,null))return false;
			if(!saveCheck(14,null))return false;
		}
		if(qyzslx==4){
			if(!saveCheck(12,null))return false;
			if(!saveCheck(13,null))return false;
			if(!saveCheck(14,null))return false;	
		}
		if(parseFloat(document.getElementById('12_1').value)<0){
			alert("����Ľ�������ڵ���0!");
			window.event.returnValue=false;
			document.getElementById('12_1').focus();
			document.getElementById('12_1').select();
			return false;
		}
		if(parseFloat(document.getElementById('13_1').value)<0){
			alert("����Ľ�������ڵ���0!");
			window.event.returnValue=false;
			document.getElementById('13_1').focus();
			document.getElementById('13_1').select();
			return false;
		}
		if(parseFloat(document.getElementById('14_1').value)<0){
			alert("����Ľ�������ڵ���0!");
			window.event.returnValue=false;
			document.getElementById('14_1').focus();
			document.getElementById('14_1').select();
			return false;
		}
		
		if(parseFloat(document.getElementById('13_1').value)>parseFloat(document.getElementById('12_1').value)){
			alert("��13�е�ֵ����С�ڵ��ڵ�12�е�ֵ!");
			window.event.returnValue=false;
			document.getElementById('13_1').focus();
			document.getElementById('13_1').select();
			return false;
		}
		

	var old  = document.forms[0].ywczlx.value;
    return SaveExec(old);
}
		<%/*����ʱ�Ե�1��14�е����ݽ���У��*/%>
	function saveCheck(row,zero){
		
		if(!isNum(document.getElementById(row+'_1'), zero, null, null, null, 2)){
			return false;			
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

//��֤���ݵĺϷ���
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


	<%/*�����3,12������*/%>
	function compute_Row_1(){
		var ynssds; 
		var ybsds;
		if(parseFloat(document.getElementById('1_1').value)<0){
			alert("����Ľ�������ڵ���0!");
			window.event.returnValue=false;
			document.getElementById('1_1').focus();
			document.getElementById('1_1').select();
			return false;
		}
		if(document.getElementById('1_1').value==""){
			ynssds=0;
		}
		else{
			ynssds= Math.round((parseFloat(document.getElementById('1_1').value) * parseFloat(document.getElementById('2_1').value))*100)/100;
		}
		document.getElementById('3_1').value = parseFloat(ynssds);
		formate(document.getElementById('3_1'));
		var ynsdse =Math.round((parseFloat(document.getElementById('3_1').value) * parseFloat(document.getElementById('11_1').value))*100)/100;
			
		document.getElementById('12_1').value = parseFloat(ynsdse);
		formate(document.getElementById('12_1'));
		
		ybsds=Math.round((parseFloat(document.getElementById('12_1').value)-parseFloat(document.getElementById('13_1').value)-parseFloat(document.getElementById('14_1').value))*100)/100;
			if(ybsds>0){
				document.getElementById('15_1').value=parseFloat(ybsds);
				
			}
			else{
				document.getElementById('15_1').value=0;
			}
			formate(document.getElementById('15_1'));
	}
	<%/*��13�������ж�*/%>
	function compute_Row_13(){
		var ybsds;
		if(parseFloat(document.getElementById('13_1').value)>parseFloat(document.getElementById('12_1').value)){
			alert("����Ľ�����С�ڵ��ڵ�12�е�ֵ!");
			window.event.returnValue=false;
			document.getElementById('13_1').focus();
			document.getElementById('13_1').select();
			return false;
		}
		if(parseFloat(document.getElementById('13_1').value)<0){
			alert("����Ľ�������ڵ���0!");
			window.event.returnValue=false;
			document.getElementById('13_1').focus();
			document.getElementById('13_1').select();
			return false;
		}
		if(document.getElementById('13_1').value==""){
			document.getElementById('13_1').value=0;
		}
		ybsds=Math.round((parseFloat(document.getElementById('12_1').value)-parseFloat(document.getElementById('13_1').value)-parseFloat(document.getElementById('14_1').value))*100)/100;
			if(ybsds>0){
				document.getElementById('15_1').value=parseFloat(ybsds);
				
			}
			else{
				document.getElementById('15_1').value=0;
			}
			formate(document.getElementById('15_1'));
		
	}
		<%/*��12�������ж�*/%>
	function compute_Row_12(){
		var ybsds;
		if(parseFloat(document.getElementById('12_1').value)<0){
			alert("����Ľ�������ڵ���0!");
			window.event.returnValue=false;
			document.getElementById('12_1').focus();
			document.getElementById('12_1').select();
			return false;
		}
		if(document.getElementById('12_1').value==""){
			document.getElementById('12_1').value=0;
		}
		ybsds=Math.round((parseFloat(document.getElementById('12_1').value)-parseFloat(document.getElementById('13_1').value)-parseFloat(document.getElementById('14_1').value))*100)/100;
			if(ybsds>0){
				document.getElementById('15_1').value=parseFloat(ybsds);
				
			}
			else{
				document.getElementById('15_1').value=0;
			}
			formate(document.getElementById('15_1'));
	}
			<%/*��14�������ж�*/%>
	function compute_Row_14(){
		var ybsds;
		if(parseFloat(document.getElementById('14_1').value)<0){
			alert("����Ľ�������ڵ���0!");
			window.event.returnValue=false;
			document.getElementById('14_1').focus();
			document.getElementById('14_1').select();
			return false;
		}
		if(document.getElementById('14_1').value==""){
			document.getElementById('14_1').value=0;
		}
		ybsds=Math.round((parseFloat(document.getElementById('12_1').value)-parseFloat(document.getElementById('13_1').value)-parseFloat(document.getElementById('14_1').value))*100)/100;
			if(ybsds>0){
				document.getElementById('15_1').value=parseFloat(ybsds);			
			}
			else{
				document.getElementById('15_1').value=0;
			}
			formate(document.getElementById('15_1'));
	}
		function checkNumInput(obj)
{
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;			
		}
		//��ʽ������
		formate(obj);
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
<form name="hdzsjbForm" action="hdzsqyjb2008.dc">
<input name="actionType" type="hidden" id="actionType" value="Show">
	<table width="96%" align="center" cellspacing="0" class="table-99" onKeyDown="jsjdmQuery()">
		<tr>
		<td align="center" valign="top" colspan="7">
        <jsp:include page="../../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="��ҵ����˰��(��)��Ԥ����˰�걨��(B��)" />
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
					<a style="cursor:hand" tabindex="-1" onClick="javascript:return doSubmit('Save');return false;"><img  name="spage" value="����" alt="����" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>/images/baocun2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/baocun1.jpg" width="79" height="22" id="baocun"></a>
					<a style="cursor:hand" tabindex="-1" onClick="javascript:return doSubmit('Delete');return false;"><img name="dpage" value="ɾ��" alt="ɾ��" onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>/images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/shanchu1.jpg" width="79" height="22" id="shanchu"></a>
					<a style="cursor:hand" tabindex="-1" onClick="javascript:return doSubmit('Reset');return false;"><img name="qpage" value="���" alt="���" onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>/images/qingchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/qingchu1.jpg"  width="79" height="22" id="qingchu"></a>
					<a style="cursor:hand" tabindex="-1" onClick="javascript:return doSubmit('Return');return false;"><img  name="bpage" value="����" alt="����" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/fanhui1.jpg"  width="79" height="22" id="fanhui"></a>
				
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
