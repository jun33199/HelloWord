<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ page import="com.ttsoft.bjtax.shenbao.constant.CodeConstant"%>
<%
    String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);

%>
<html>
<head>
<title>�˶�������ҵ����˰�����˰�걨��</title>
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
var InputName=['syze','bzssr','mssr','yssre','yssdl','ynssde','sl','ynsdse','yyjsdse','ybsdse'];
var InputNameAl=['�����ܶ�','����˰����','��˰����','Ӧ˰�����','˰����غ˶���Ӧ˰������','Ӧ��˰���ö�','˰��','Ӧ������˰��','��Ԥ������˰��','Ӧ�����ˣ�����˰��'];
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xmlDoc;

//����xml
function parseXmlOnLoad()
{
	var urlXSL="/XSLTWEB/model/010024/XSLT/" +strXSLTVersion+".xsl";
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
			<%/*���õ�5��*/%>
			var cylValue = document.forms[0].cyl.value;
			//alert("cylValue====>>>"+cylValue);
			var cylValue51 = document.getElementById('5_1').value;
			//alert("cylValue51====>>>"+cylValue51);
			if(cylValue51=="null"||cylValue51==null||parseFloat(cylValue51)==0 || cylValue51==""){
				//document.getElementById('5_1').value = Math.round(cylValue*100)/100;
				document.getElementById('5_1').value = parseFloat(cylValue*100)/100;
				//alert("document.getElementById('5_1').value====>>>"+document.getElementById('5_1').value);
				formate(document.getElementById('5_1'));		
			}
			var obj = document.forms[0].yssdlView;
			obj.readOnly=true;
			obj.className='text-gray';
			//alert("document.getElementById('5_1').value====="+document.getElementById('5_1').value);
			//obj.value=parseFloat(document.getElementById('5_1').value)*100+"%";
			obj.value=(parseFloat(document.getElementById('5_1').value)*100).toFixed(2)+"%";
			//alert("obj.value====>>>"+obj.value);
			
			<%/*���õ�6��*/%>
			var obj = document.forms[0].ynssde;
			obj.readOnly=true;
			obj.className='text-gray';
			<%/*���õ�10��*/%>
			document.forms[0].sl.value="0.25";	
			document.getElementById('10_1_1').value=parseFloat(document.getElementById('10_1').value)*100+"%";
			var obj = document.getElementById('10_1_1');
			obj.readOnly=true;
			obj.className='text-gray';
				
		}
		if(qyzslx ==4){
			<%/*��������*/%>
			setStar(document.forms[0].syze);
			setStar(document.forms[0].bzssr);
			setStar(document.forms[0].mssr);
			setStar(document.forms[0].yssre);
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
		
		
		//ְ����������ΪС��
		if(document.forms[0].zgrs.value=="0.00"){
			document.forms[0].zgrs.value="0";	
		}		
}

function setStar(obj){
	obj.readOnly=true;
	obj.className='text-noborder';
	obj.value='*';
	obj.style.cssText='text-align:center';
	obj.size='1';
	obj.onblur="";
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
	applendElement("/taxdoc","sbsj",["syze","bzssr","mssr","yssre","yssdl","ynssde","sl","ynsdse","xwqyjmsdse","yyjsdse","ybsdse","swjghdynsdse","zczb","zcze","zgrs","sshydm"]);
	
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
		if(checkToSave()){
			if(confirm("�ò���������ҳ������,�Ҳ����Զ��ָ�,��ȷ����д�����Ƿ���ȷ��")){
				doSave();
			}
		}
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
	   			for(var i=1; i < 15; i++){
					obj = eval("document.getElementById('" + i+ "_1')");
					if (obj.value!="*"&&i!=5&&i!=10)
						obj.value = "0.00";
	   			}
	   		}
	   		else if(qyzslx == 4){
	   			for(var i=11; i < 15; i++){
					obj = eval("document.getElementById('" + i+ "_1')");
					obj.value = "0.00";
	   			}
	   		}
	   		else{
	   			for(var i=1;i<9;i++){
	   				obj = eval("document.getElementById('" + i+ "_1')");
					if (obj.value!="*")
						obj.value = "0.00";
	   			}
	   		}
	   		document.forms[0].zczb.value = "0.00";
	   		document.forms[0].zcze.value = "0.00";
	   		
	   		//xiezk0228��� ע���ʱ���ְ��������
	   		document.getElementById("zczb").value="0.00";
	   		document.getElementById("zcze").value="0.00";
	   		document.getElementById("zgrs").value="0";
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

function checkToSave(){
	if(document.forms[0].nsrmc.value==""){
		alert("������Ϣ����ȷ,����ʧ��,����������!");
		document.forms[0].jsjdm.focus();
		return false;
	}
	
	//formatData10(document.forms[0].zcze.value, 0);
	//formatData10(document.forms[0].zczb.value, 1);
	
	<%/*��ҵ��˰����*/%>
	var qyzslx=document.forms[0].qyzslx.value;
	if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){
		if(!saveCheck(1,null))return false;
		//modify by guoxh, 2012-2-24, start
		//if(!saveCheck(13,null))return false;
		//if(!saveCheck(14,null))return false;
		if(!saveCheck(12,null))return false;
		//modify by guoxh, 2012-2-24, end
	}
	if(qyzslx==4){
		//modify by guoxh, 2012-2-24, start
		//if(!saveCheck(12,null))return false;
		//if(!saveCheck(13,null))return false;
		//if(!saveCheck(14,null))return false;
		if(!saveCheck(11,null))return false;
		if(!saveCheck(12,null))return false;
		//modify by guoxh, 2012-2-24, end
	}
	if(parseFloat(document.getElementById('2_1').value)<0){
		alert("�ֵ�2������С���㣬��˶ԣ�");
		window.event.returnValue=false;
		document.getElementById('2_1').focus();
		document.getElementById('2_1').select();
		return false;
	}
	if(parseFloat(document.getElementById('3_1').value)<0){
		alert("�ֵ�3������С���㣬��˶ԣ�");
		window.event.returnValue=false;
		document.getElementById('3_1').focus();
		document.getElementById('3_1').select();
		return false;
	}
	if(parseFloat(document.getElementById('11_1').value)<0){
		alert("�ֵ�11������С���㣬��˶ԣ�");
		window.event.returnValue=false;
		document.getElementById('11_1').focus();
		document.getElementById('11_1').select();
		return false;
	}
	/* modify by guoxh, 2012-2-24
	if(parseFloat(document.getElementById('13_1').value)<0){
		alert("����Ľ�������ڵ���0!");
		window.event.returnValue=false;
		document.getElementById('13_1').focus();
		document.getElementById('13_1').select();
		return false;
	}*/
	if(!checkedXwqysdse()){
			document.getElementById('12_1').focus();
			document.getElementById('12_1').select();
			return false;
		}
	if(parseFloat(document.getElementById('13_1').value)<0){
		alert("�ֵ�13������С���㣬��˶ԣ�");
		window.event.returnValue=false;
		document.getElementById('13_1').focus();
		document.getElementById('13_1').select();
		return false;
	}
	
	/* modify by guoxh, 2012-2-24
	if(parseFloat(document.getElementById('13_1').value)>parseFloat(document.getElementById('12_1').value)){
		alert("��13�е�ֵ����С�ڵ��ڵ�12�е�ֵ!");
		window.event.returnValue=false;
		document.getElementById('13_1').focus();
		document.getElementById('13_1').select();
		return false;
	}*/

	if((parseFloat(document.getElementById('2_1').value)>0)&&(parseFloat(document.getElementById('2_1').value)>parseFloat(document.getElementById('1_1').value))){
		alert("�ֵ�2�����ݴ��ڵ�1�����ݣ���˶ԣ�");
	}

	if((parseFloat(document.getElementById('3_1').value)>0)&&(parseFloat(document.getElementById('3_1').value)>(parseFloat(document.getElementById('1_1').value)-parseFloat(document.getElementById('2_1').value)))){
		alert("�ֵ�3�����ݴ��ڵ�1�м���2�����ݣ���˶ԣ�");
	}
	
	return true;
}

function doSave(){
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


	/**
	* Notes: �������ɵ�һ���������д�������˳������걨������е���ֵ��������2012��B���
	* Author: Guoxh, 2012-2-28
	*/
	function compute_Row_1(idName){
		var ynssds; 
		var ybsds;
		if(parseFloat(document.getElementById(idName).value)<0){
			alert("����Ľ�������ڵ���0!");
			window.event.returnValue=false;
			document.getElementById(idName).focus();
			document.getElementById(idName).select();
			return false;
		}
		if(document.getElementById('2_1').value==""){
			document.getElementById('2_1').value=0;
		}
		if(document.getElementById('3_1').value==""){
			document.getElementById('3_1').value=0;
		}
		if(document.getElementById('1_1').value!=""){
			row1Val = parseFloat(document.getElementById('1_1').value);
			row2Val = parseFloat(document.getElementById('2_1').value);
			row3Val = parseFloat(document.getElementById('3_1').value);
			//��2�Сܵ�1��
			if(row2Val > row1Val){
				alert("��2�е�ֵ����С�ڵ��ڵ�1�е�ֵ!");
				window.event.returnValue=false;
				document.getElementById('2_1').focus();
				document.getElementById('2_1').select();
				return false;
			}
			//��3�Сܵ�1-2��
			if(row3Val > (row1Val-row2Val)){
				alert("��3�е�ֵ����С�ڵ��ڵ�1�м�ȥ��2�еĲ�ֵ!");
				window.event.returnValue=false;
				document.getElementById('3_1').focus();
				document.getElementById('3_1').select();
				return false;
			}
		}
		if(document.getElementById('1_1').value==""){
			ynssds=0;
		}else{
			//4�� Ӧ˰����1-2-3��
			yssre=Math.round((parseFloat(document.getElementById('1_1').value)-parseFloat(document.getElementById('2_1').value)-parseFloat(document.getElementById('3_1').value))*100)/100;
			document.getElementById('4_1').value=parseFloat(yssre);
			formate(document.getElementById('4_1'));

			//4�� X 5�У�guoxh��2012-2-24
			ynssds= Math.round((parseFloat(yssre) * parseFloat(document.getElementById('5_1').value))*100)/100;
		}
		document.getElementById('6_1').value = parseFloat(ynssds);
		formate(document.getElementById('6_1'));
		var ynsdse =Math.round((parseFloat(document.getElementById('6_1').value) * parseFloat(document.getElementById('10_1').value))*100)/100;
			
		document.getElementById('11_1').value = parseFloat(ynsdse);
		formate(document.getElementById('11_1'));
		
		ybsds=Math.round((parseFloat(document.getElementById('11_1').value)-parseFloat(document.getElementById('12_1').value)-parseFloat(document.getElementById('13_1').value))*100)/100;
		if(ybsds>0){
			document.getElementById('14_1').value=parseFloat(ybsds);
			
		}
		else{
			document.getElementById('14_1').value=0;
		}
		formate(document.getElementById('14_1'));
	}
	<%/*��1�������ж�*/%>
	function compute_Row_1(){
		var ynssds; 
		var ybsds;
		if(document.getElementById('1_1').value==""){
			document.getElementById('1_1').value=0;
		}
		if(parseFloat(document.getElementById('1_1').value)<0){
			alert("�ֵ�1������С���㣬��˶ԣ�");
		}
		if(document.getElementById('1_1').value==""){
			ynssds=0;
		}else{
			//4�� Ӧ˰����1-2-3��
			yssre=Math.round((parseFloat(document.getElementById('1_1').value)-parseFloat(document.getElementById('2_1').value)-parseFloat(document.getElementById('3_1').value))*100)/100;
			document.getElementById('4_1').value=parseFloat(yssre);
			formate(document.getElementById('4_1'));

			//4�� *5��
			ynssds= Math.round((parseFloat(yssre) * parseFloat(document.getElementById('5_1').value))*100)/100;
			if(ynssds<=0){
				ynssds=0
			}
		}
		document.getElementById('6_1').value = parseFloat(ynssds);
		formate(document.getElementById('6_1'));
		var ynsdse =Math.round((parseFloat(document.getElementById('6_1').value) * parseFloat(document.getElementById('10_1').value))*100)/100;
		
		document.getElementById('11_1').value = parseFloat(ynsdse);
		formate(document.getElementById('11_1'));
	
		ybsds=Math.round((parseFloat(document.getElementById('11_1').value)-parseFloat(document.getElementById('12_1').value)-parseFloat(document.getElementById('13_1').value))*100)/100;
		if(ybsds>0){
			document.getElementById('14_1').value=parseFloat(ybsds);			
		}
		else{
			document.getElementById('14_1').value=0;
		}
		formate(document.getElementById('14_1'));

		compute_Row_2();
		//compute_Row_3();
	}
	<%/*��2�������ж�*/%>
	function compute_Row_2(){
		var ynssds; 
		var ybsds;
		if(document.getElementById('2_1').value==""){
			document.getElementById('2_1').value=0;
		}
		if(parseFloat(document.getElementById('2_1').value)<0){
			alert("�ֵ�2������С���㣬��˶ԣ�");
		}
		if((parseFloat(document.getElementById('2_1').value)>0)&&(parseFloat(document.getElementById('2_1').value)>parseFloat(document.getElementById('1_1').value))){
			alert("�ֵ�2�����ݴ��ڵ�1�����ݣ���˶ԣ�");
		}
		if(document.getElementById('1_1').value==""){
			ynssds=0;
		}else{
			//4�� Ӧ˰����1-2-3��
			yssre=Math.round((parseFloat(document.getElementById('1_1').value)-parseFloat(document.getElementById('2_1').value)-parseFloat(document.getElementById('3_1').value))*100)/100;
			document.getElementById('4_1').value=parseFloat(yssre);
			formate(document.getElementById('4_1'));

			//4�� *5��
			ynssds= Math.round((parseFloat(yssre) * parseFloat(document.getElementById('5_1').value))*100)/100;
			if(ynssds<=0){
				ynssds=0
			}
		}
		document.getElementById('6_1').value = parseFloat(ynssds);
		formate(document.getElementById('6_1'));
		var ynsdse =Math.round((parseFloat(document.getElementById('6_1').value) * parseFloat(document.getElementById('10_1').value))*100)/100;
		
		document.getElementById('11_1').value = parseFloat(ynsdse);
		formate(document.getElementById('11_1'));
	
		ybsds=Math.round((parseFloat(document.getElementById('11_1').value)-parseFloat(document.getElementById('12_1').value)-parseFloat(document.getElementById('13_1').value))*100)/100;
		if(ybsds>0){
			document.getElementById('14_1').value=parseFloat(ybsds);			
		}
		else{
			document.getElementById('14_1').value=0;
		}
		formate(document.getElementById('14_1'));
		compute_Row_3();
	}

	<%/*��3�������ж�*/%>
	function compute_Row_3(){
		var ynssds; 
		var ybsds;
		if(document.getElementById('3_1').value==""){
			document.getElementById('3_1').value=0;
		}
		if(parseFloat(document.getElementById('3_1').value)<0){
			alert("�ֵ�3������С���㣬��˶ԣ�");
		}
		if((parseFloat(document.getElementById('3_1').value)>0)&&(parseFloat(document.getElementById('3_1').value)>(parseFloat(document.getElementById('1_1').value)-parseFloat(document.getElementById('2_1').value)))){
			alert("�ֵ�3�����ݴ��ڵ�1�м���2�����ݣ���˶ԣ�");
		}
		if(document.getElementById('1_1').value==""){
			ynssds=0;
		}else{
			//4�� Ӧ˰����1-2-3��
			yssre=Math.round((parseFloat(document.getElementById('1_1').value)-parseFloat(document.getElementById('2_1').value)-parseFloat(document.getElementById('3_1').value))*100)/100;
			document.getElementById('4_1').value=parseFloat(yssre);
			formate(document.getElementById('4_1'));

			//4�� *5��
			ynssds= Math.round((parseFloat(yssre) * parseFloat(document.getElementById('5_1').value))*100)/100;
			if(ynssds<=0){
				ynssds=0
			}
		}
		document.getElementById('6_1').value = parseFloat(ynssds);
		formate(document.getElementById('6_1'));
		var ynsdse =Math.round((parseFloat(document.getElementById('6_1').value) * parseFloat(document.getElementById('10_1').value))*100)/100;
		
		document.getElementById('11_1').value = parseFloat(ynsdse);
		formate(document.getElementById('11_1'));
	
		ybsds=Math.round((parseFloat(document.getElementById('11_1').value)-parseFloat(document.getElementById('12_1').value)-parseFloat(document.getElementById('13_1').value))*100)/100;
		if(ybsds>0){
			document.getElementById('14_1').value=parseFloat(ybsds);			
		}
		else{
			document.getElementById('14_1').value=0;
		}
		formate(document.getElementById('14_1'));
	}
	
	<%/*��11�������ж�*/%>
	function compute_Row_11(){
		var ybsds;
		if(parseFloat(document.getElementById('11_1').value)<0){
			alert("�ֵ�11������С���㣬��˶ԣ�");
			//window.event.returnValue=false;
			//document.getElementById('11_1').focus();
			//document.getElementById('11_1').select();
			//return false;
		}
		if(document.getElementById('11_1').value==""){
			document.getElementById('11_1').value=0;
		}
		if(document.getElementById('12_1').value==""){
			document.getElementById('12_1').value=0;
		}
		ybsds=Math.round((parseFloat(document.getElementById('11_1').value)-parseFloat(document.getElementById('12_1').value)-parseFloat(document.getElementById('13_1').value))*100)/100;
			if(ybsds>0){
				document.getElementById('14_1').value=parseFloat(ybsds);
			}
			else{
				document.getElementById('14_1').value=0;
			}
			formate(document.getElementById('14_1'));
	}
	var inputValueTemp="0.00";
	var xwblts="0";
	<%/*��12�������ж�*/%>
	function compute_Row_12(){

		var obj_input=document.getElementById('12_1');
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		var sfxkh=document.forms[0].sfxkh.value;
		var syndZsfsdm=document.forms[0].syndzsfsdm.value;
		var syndFb5jyjg=document.forms[0].syndfb5jyjg.value;
		//10��¼������
		var ynsdseStr=document.getElementById('6_1').value;;
		var syndZbh6Str=document.forms[0].syndzbh6.value;
		var syndZbh25Str=document.forms[0].syndzbh25.value;
		var zczeStr=document.getElementById("zcze").value;
		var zgrsStr=document.getElementById("zgrs").value;
		var sshyStr=document.getElementById("sshydm").value;
		var ynsdse=0.00;
		var syndZbh6=0.00;
		var syndZbh25=0.00;
		var zcze=0.00;
		var zgrs=0;
		var sshy=0;
		if(ynsdseStr!=null && ynsdseStr!=""){
			ynsdse=1*ynsdseStr;
		}
		if(syndZbh6Str!=null && syndZbh6Str!=""){
			syndZbh6=1*syndZbh6Str;
		}
		if(syndZbh25Str!=null && syndZbh25Str!=""){
			syndZbh25=1*syndZbh25Str;
		}
		if(zczeStr!=null && zczeStr!=""){
			zcze=1*zczeStr;
		}
		if(zgrsStr!=null && zgrsStr!=""){
			zgrs=1*zgrsStr;
		}
		if(sshyStr!=null && sshyStr!=""){
			sshy=1*sshyStr;
		}			
		//alert("sfxkh: "+sfxkh);
		//alert("syndZsfsdm: "+syndZsfsdm);
		//alert("syndFb5jyjg: "+syndFb5jyjg);
		//alert("ynsdse: "+ynsdse);
		//alert("syndZbh6: "+syndZbh6);
		//alert("syndZbh25: "+syndZbh25);
		
		//modified by zhangj 2015.3.2
		var million=10*10000;
		var nd=document.forms[0].nd.value;
		//var jd=document.forms[0].jd.value;
		if(nd*1>=2015){			
			million=20*10000;//�����걨������Ϊ2014�꼰���Ժ�������˰�걨����˰�ˣ�����1��6���ж���ϵ��ԭ�С�10����ж���׼�޸�Ϊ��20��Ԫ��
		}
		//modified by zhangj end 
		
		xwblts="0";
		if(ynsdse>30*10000 || ynsdse<=0){
			//obj_input.value="0.00";
			inputValueTemp="0.00";
		}else{
			if((zgrs<=100&&zcze<=3000&&(610<=sshy&&sshy<=4690))||(zgrs<=80&&zcze<=1000&&!(610<=sshy&&sshy<=4690))){
				if(ynsdse<=million){
						//obj_input.value=(ynsdse*0.15).toFixed(2);
					inputValueTemp=(ynsdse*0.15).toFixed(2);
						//gyts("15");
					xwblts="15";
				}
					
				if(million<ynsdse&&ynsdse<=30*10000){
						//obj_input.value=(ynsdse*0.05).toFixed(2);
					inputValueTemp=(ynsdse*0.05).toFixed(2);
						//gyts("5");
					xwblts="5";
				}
					
				if(ynsdse>30*10000){
						//obj_input.value="0.00";
					inputValueTemp="0.00";
				}				
			}else{
				inputValueTemp="0.00";
			}
		}
/*		
		if(ynsdse>30*10000 || ynsdse<=0){
			//obj_input.value="0.00";
			inputValueTemp="0.00";
		}else{
			if(sfxkh=="Y"){
				if(ynsdse<=million){
					//obj_input.value=(ynsdse*0.15).toFixed(2);
					inputValueTemp=(ynsdse*0.15).toFixed(2);
					//gyts("15");
					xwblts="15";
				}
				
				if(million<ynsdse && ynsdse<=30*10000){
					//obj_input.value=(ynsdse*0.05).toFixed(2);
					inputValueTemp=(ynsdse*0.05).toFixed(2);
					//gyts("5");
					xwblts="5";
				}
				if(ynsdse>30*10000){
					//obj_input.value="0.00";
					inputValueTemp="0.00";
				}
			}else{
				 if(syndZsfsdm==<%=CodeConstant.ZSFSDM_CZZS_03%>){//��������
					if(1*syndZbh25>30*10000){
					//obj_input.value="0.00";
					inputValueTemp="0.00";	
					}else{
						if(syndFb5jyjg=="Y"){
							if(ynsdse<=million && syndZbh25<=million){
								//obj_input.value=(ynsdse*0.15).toFixed(2);
								inputValueTemp=(ynsdse*0.15).toFixed(2);
								//gyts("15");
								xwblts="15";
							}else{
								//obj_input.value=(ynsdse*0.05).toFixed(2);
								inputValueTemp=(ynsdse*0.05).toFixed(2);
								//gyts("5");
								xwblts="5";
							}
						}else{
							//obj_input.value="0.00";
							inputValueTemp="0.00";
						}
					}
				}else{
					if(1*syndZbh6>30*10000){
						//obj_input.value="0.00";
						inputValueTemp="0.00";
					}else{
						if(ynsdse<=million && syndZbh6<=million){
							//obj_input.value=(ynsdse*0.15).toFixed(2);
							inputValueTemp=(ynsdse*0.15).toFixed(2);
							//gyts("15");
							xwblts="15";
						}else{
							//obj_input.value=(ynsdse*0.05).toFixed(2);
							inputValueTemp=(ynsdse*0.05).toFixed(2);
							//gyts("5");
							xwblts="5";
						}
					}
				}
			}
		}*/
		
		
		if(document.getElementById('11_1').value==""){
			document.getElementById('11_1').value=0;
		}
		if(document.getElementById('13_1').value==""){
			document.getElementById('13_1').value=0;
		}
		if(document.getElementById('12_1').value==""){
			document.getElementById('12_1').value=0;
		}
		ybsds=Math.round((parseFloat(document.getElementById('11_1').value)-parseFloat(document.getElementById('12_1').value)-parseFloat(document.getElementById('13_1').value))*100)/100;
			if(ybsds>0){
				document.getElementById('14_1').value=parseFloat(ybsds);
			}
			else{
				document.getElementById('14_1').value=0;
			}
			formate(document.getElementById('14_1'));
		
	}
	
	function gyts(strBs){
		if(strBs=="15"){
			//alert("�����걨����ҵ��������С��΢����ҵ����������ҵ����˰�Ż����ߣ����ü���50������Ӧ��˰���ö��20%��˰�ʽ�����ҵ����˰��");
			xwblts="15";
		}
		
		if(strBs=="5"){
			//alert("�����걨����ҵ��������С��΢����ҵ����˰�Ż����ߣ���20%��˰�ʽ�����ҵ����˰��");
			xwblts="5";
		}
	}
	//����С΢��ҵ����˰˰��
	function compute_xwqysdse(){
		var sfxkh=document.forms[0].sfxkh.value;
		var syndZsfsdm=document.forms[0].syndzsfsdm.value;
		var syndFb5jyjg=document.forms[0].syndfb5jyjg.value;
		//10��¼������
		var ynsdseStr=document.getElementById('6_1').value;;
		var syndZbh6Str=document.forms[0].syndzbh6.value;
		var syndZbh25Str=document.forms[0].syndzbh25.value;
		var zczeStr=document.getElementById("zcze").value;
		var zgrsStr=document.getElementById("zgrs").value;
		var sshyStr=document.getElementById("sshydm").value;
		var ynsdse=0.00;
		var syndZbh6=0.00;
		var syndZbh25=0.00;
		var zcze=0.00;
		var zgrs=0;
		var sshy=0;
		if(ynsdseStr!=null && ynsdseStr!=""){
			ynsdse=1*ynsdseStr;
		}
		if(syndZbh6Str!=null && syndZbh6Str!=""){
			syndZbh6=1*syndZbh6Str;
		}
		if(syndZbh25Str!=null && syndZbh25Str!=""){
			syndZbh25=1*syndZbh25Str;
		}
		if(zczeStr!=null && zczeStr!=""){
			zcze=1*zczeStr;
		}
		if(zgrsStr!=null && zgrsStr!=""){
			zgrs=1*zgrsStr;
		}
		if(sshyStr!=null && sshyStr!=""){
			sshy=1*sshyStr;
		}	
		
		//alert("sfxkh: "+sfxkh);
		//alert("syndZsfsdm: "+syndZsfsdm);
		//alert("syndFb5jyjg: "+syndFb5jyjg);
		//alert("ynsdse: "+ynsdse);
		//alert("syndZbh6: "+syndZbh6);
		//alert("syndZbh25: "+syndZbh25);

		//modified by zhangj 2015.3.2
		var million=10*10000;
		var nd=document.forms[0].nd.value;
		//var jd=document.forms[0].jd.value;
		if(nd*1>=2015){			
			million=20*10000;//�����걨������Ϊ2014�꼰���Ժ�������˰�걨����˰�ˣ�����1��6���ж���ϵ��ԭ�С�10����ж���׼�޸�Ϊ��20��Ԫ��
		}
		//modified by zhangj end 
		
		xwblts="0";
		if(ynsdse>30*10000 || ynsdse<=0){
			//obj_input.value="0.00";
			inputValueTemp="0.00";
		}else{
			if((zgrs<=100&&zcze<=3000&&(610<=sshy&&sshy<=4690))||(zgrs<=80&&zcze<=1000&&!(610<=sshy&&sshy<=4690))){
				if(ynsdse<=million){
						//obj_input.value=(ynsdse*0.15).toFixed(2);
					inputValueTemp=(ynsdse*0.15).toFixed(2);
						//gyts("15");
					xwblts="15";
				}
					
				if(million<ynsdse&&ynsdse<=30*10000){
						//obj_input.value=(ynsdse*0.05).toFixed(2);
					inputValueTemp=(ynsdse*0.05).toFixed(2);
						//gyts("5");
					xwblts="5";
				}
					
				if(ynsdse>30*10000){
						//obj_input.value="0.00";
					inputValueTemp="0.00";
				}				
			}else{
				inputValueTemp="0.00";
			}
		}
/*				
		if(ynsdse>30*10000 || ynsdse<=0){
			//obj_input.value="0.00";
			inputValueTemp="0.00";
		}else{
			if(sfxkh=="Y"){
				if(ynsdse<=million){
					//obj_input.value=(ynsdse*0.15).toFixed(2);
					inputValueTemp=(ynsdse*0.15).toFixed(2);
					gyts("15");
					xwblts="15";
				}
				
				if(million<ynsdse && ynsdse<=30*10000){
					//obj_input.value=(ynsdse*0.05).toFixed(2);
					inputValueTemp=(ynsdse*0.05).toFixed(2);
					gyts("5");
					xwblts="5";
				}
				if(ynsdse>30*10000){
					//obj_input.value="0.00";
					inputValueTemp="0.00";
				}
			}else{
				 if(syndZsfsdm==<%=CodeConstant.ZSFSDM_CZZS_03%>){//��������
					if(1*syndZbh25>30*10000){
					//obj_input.value="0.00";
					inputValueTemp="0.00";	
					}else{
						if(syndFb5jyjg=="Y"){
							if(ynsdse<=million && syndZbh25<=million){
								//obj_input.value=(ynsdse*0.15).toFixed(2);
								inputValueTemp=(ynsdse*0.15).toFixed(2);
								gyts("15");
								xwblts="15";
							}else{
								//obj_input.value=(ynsdse*0.05).toFixed(2);
								inputValueTemp=(ynsdse*0.05).toFixed(2);
								gyts("5");
								xwblts="5";
							}
						}else{
							//obj_input.value="0.00";
							inputValueTemp="0.00";
						}
					}
				}else{
					if(1*syndZbh6>30*10000){
						//obj_input.value="0.00";
						inputValueTemp="0.00";
					}else{
						if(ynsdse<=million && syndZbh6<=million){
							//obj_input.value=(ynsdse*0.15).toFixed(2);
							inputValueTemp=(ynsdse*0.15).toFixed(2);
							gyts("15");
							xwblts="15";
						}else{
							//obj_input.value=(ynsdse*0.05).toFixed(2);
							inputValueTemp=(ynsdse*0.05).toFixed(2);
							gyts("5");
							xwblts="5";
						}
					}
				}
			}
		}*/
	}
	function checkedXwqysdse(){
		//����С΢��ҵ����˰��
		compute_xwqysdse();
		var value_12=document.getElementById('12_1').value;
		
//		if(value_12*1==0){
//			if(xwblts=="15"){
//				//alert("�����걨����ҵ��������С��΢����ҵ����������ҵ����˰�Ż����ߣ����ü���50������Ӧ��˰���ö��20%��˰�ʽ�����ҵ����˰��");
//				if(confirm("�����걨����ҵ��������С��΢����ҵ����������ҵ����˰�Ż����ߣ����ü���50������Ӧ��˰���ö��20%��˰�ʽ�����ҵ����˰���Ƿ����ܴ��Ż����ߣ����������걨���ݣ�")){
//					return true;
//				}else{
//					return false;
//				}
//			}else if(xwblts=="5"){
//				//alert("�����걨����ҵ��������С��΢����ҵ����˰�Ż����ߣ���20%��˰�ʽ�����ҵ����˰��");
//				if(confirm("�����걨����ҵ��������С��΢����ҵ����˰�Ż����ߣ���20%��˰�ʽ�����ҵ����˰���Ƿ����ܴ��Ż����ߣ����������걨���ݣ�")){
//					return true;
//				}else{
//					return false;
//				}
//			}
//		}
		if(inputValueTemp!=value_12){
			if(inputValueTemp=="0.00"){
				alert("����ҵ������С��΢����ҵ��������12�С���������������С��΢����ҵ��������˰�Ӧ��д0��");
				return false;
			}else{
				alert("����ҵ����С��΢����ҵ��������12�С���������������С��΢����ҵ��������˰�Ӧ���յ�6�С�Ӧ��˰���ö��"+xwblts+"%("+inputValueTemp+")������д��");
				return false;
			}
		}
		return true;
	}
			<%/*��13�������ж�*/%>
	function compute_Row_13(){
		var ybsds;
		if(parseFloat(document.getElementById('14_1').value)<0){
			alert("�ֵ�13������С���㣬��˶ԣ�");
			//window.event.returnValue=false;
			//document.getElementById('12_1').focus();
			//document.getElementById('12_1').select();
			//return false;
		}
		if(document.getElementById('14_1').value==""){
			document.getElementById('14_1').value=0;
		}
		ybsds=Math.round((parseFloat(document.getElementById('11_1').value)-parseFloat(document.getElementById('12_1').value)-parseFloat(document.getElementById('13_1').value))*100)/100;
			if(ybsds>0){
				document.getElementById('14_1').value=parseFloat(ybsds);			
			}
			else{
				document.getElementById('14_1').value=0;
			}
			formate(document.getElementById('14_1'));
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
		
		//xiezk0228����У��ע����������ΪС��
		function checkNumInputZhengShu(obj)
		{
			//�ж�����������Ƿ����Ҫ��
			if(!isNum(getCellObject() , null, null, null, null, 0)){
				obj.focus();
				obj.select();
				return false;			
			}
			//��ʽ������
			formateZhengShu(obj);
		}

		//xiezk0228��֤���ݵĺϷ�������
		function formateZhengShu(obj){
			if(obj.value==""||obj.value==null){
				obj.value="0";	
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
				}
				obj.value=temp;	
				formateNum(obj);
			}		
		}
		
//intFLag 0--���ڵ�����  �ʲ��ܶ�У��
//intFLag 1--������  ע���ʱ����У��
//intFLag 2--������ ְ������
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
			    
				if(tmp*1>10*10000){//10��
					alert("�ʲ��ܶ���ڵ���10��Ԫ�����ʵ��");
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
				if(tmp*1>10*10000){//10��
					alert("ע���ʱ��ܶ���ڵ���10��Ԫ�����ʵ��");
				}			    
				return true;
			}

			break;
			//xiezk0228
		case 2:
			if(tmpValue == null || isNaN(tmpValue) || tmpValue < 0 ||!formatCurrency10(obj,1))
			{
				alert("ְ����������Ϊ���֣�\nС�����ĳ���Ϊ0λ��\n������ֵ����ڵ����㣡");
				obj.focus();
				obj.select();
				return false;
			}
			else
			{
			    tmp = tmpValue;
			    obj.value=tmp;

				if(tmp*1>10000){
					alert("ְ����������1���ˣ����ʵ��");
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
<form name="hdzssdsnb2014Form" action="hdzsqynb2014.dc">
<input name="actionType" type="hidden" id="actionType" value="Show">
	<table width="96%" align="center" cellspacing="0" class="table-99" onKeyDown="jsjdmQuery()">
		<tr>
		<td align="center" valign="top" colspan="7">
        <jsp:include page="../../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="�˶�������ҵ����˰�����˰�걨��" />
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
