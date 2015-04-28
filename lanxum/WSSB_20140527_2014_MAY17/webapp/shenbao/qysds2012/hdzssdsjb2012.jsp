<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%
    String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);

%>
<html>
<head>
<title>企业所得税月(季)度预缴纳税申报表(B类)</title>
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
var InputNameAl=['收入总额','不征税收入','免税收入','应税收入额','税务机关核定的应税所得率','应纳税所得额','税率','应纳所得税额','已预缴所得税额','应补（退）所得税额'];
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xmlDoc;

//解析xml
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
	
			<%/*企业征收类型*/%>
		var qyzslx=document.forms[0].qyzslx.value;
			
		if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){
			document.getElementById('1_1').focus();
			<%/*应税所得率(亦即“纯益率”)*/%>
			<%/*设置第5行*/%>
			var cylValue = document.forms[0].cyl.value;

			var cylValue51 = document.getElementById('5_1').value;
			
			if(cylValue51=="null"||cylValue51==null||parseFloat(cylValue51)==0 || cylValue51==""){
				//document.getElementById('5_1').value = Math.round(cylValue*100)/100;
				document.getElementById('5_1').value = parseFloat(cylValue*100)/100;
				formate(document.getElementById('5_1'));		
			}
			var obj = document.forms[0].yssdlView;
			obj.readOnly=true;
			obj.className='text-gray';
			//alert("document.getElementById('5_1').value====="+document.getElementById('5_1').value);
			//obj.value=parseFloat(document.getElementById('5_1').value)*100+"%";
			obj.value=(parseFloat(document.getElementById('5_1').value)*100).toFixed(2)+"%";
			
			<%/*设置第6行*/%>
			var obj = document.forms[0].ynssde;
			obj.readOnly=true;
			obj.className='text-gray';
			<%/*设置第10行*/%>
			document.forms[0].sl.value="0.25";	
			document.getElementById('10_1_1').value=parseFloat(document.getElementById('10_1').value)*100+"%";
			var obj = document.getElementById('10_1_1');
			obj.readOnly=true;
			obj.className='text-gray';
				
		}
		if(qyzslx ==4){
			<%/*定额征收*/%>
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
	//基本数据
	getBasicXx("xsltVersion","/taxdoc");
	getBasicXx("schemaVersion","/taxdoc");
	getBasicXx("ywlx","/taxdoc");
	getBasicXx("ywczlx","/taxdoc");
	//纳税人信息
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm","nsrsbh"]);
	//核定信息
	applendElement("/taxdoc","hdxx",["jmzg","ybjmsl","qyzslx","cyl","xzqy","dezsse"]);
	//申报信息
	applendElement("/taxdoc","sbxx",["fsdm","jd","nd","sbrq","skssksrq","skssjsrq"]);
	//申报数据
	applendElement("/taxdoc","sbsj",["syze","bzssr","mssr","yssre","yssdl","ynssde","sl","ynsdse","yyjsdse","ybsdse"]);
	
	//去掉末尾自动添加的回车
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

    //响应计算机代码的回车查询
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
			if(confirm("该操作将保存页面数据,且不能自动恢复,请确认填写数据是否正确！")){
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
		if(confirm("确认是否清除当前数据？"))
		{
	   		//企业征税类型
			var qyzslx=document.forms[0].qyzslx.value;
			
	   		if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){
	   			for(var i=1; i < 14; i++){
					obj = eval("document.getElementById('" + i+ "_1')");
					if (obj.value!="*"&&i!=5&&i!=10)
						obj.value = "0.00";
	   			}
	   		}
	   		else if(qyzslx == 4){
	   			for(var i=11; i < 14; i++){
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
		alert("基本信息不正确,保存失败,请重新输入!");
		document.forms[0].jsjdm.focus();
		return false;
	}
	<%/*企业征税类型*/%>
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
		alert("现第2行数据小于零，请核对！");
		window.event.returnValue=false;
		document.getElementById('2_1').focus();
		document.getElementById('2_1').select();
		return false;
	}
	if(parseFloat(document.getElementById('3_1').value)<0){
		alert("现第3行数据小于零，请核对！");
		window.event.returnValue=false;
		document.getElementById('3_1').focus();
		document.getElementById('3_1').select();
		return false;
	}
	if(parseFloat(document.getElementById('11_1').value)<0){
		alert("现第11行数据小于零，请核对！");
		window.event.returnValue=false;
		document.getElementById('11_1').focus();
		document.getElementById('11_1').select();
		return false;
	}
	/* modify by guoxh, 2012-2-24
	if(parseFloat(document.getElementById('13_1').value)<0){
		alert("输入的金额必须大于等于0!");
		window.event.returnValue=false;
		document.getElementById('13_1').focus();
		document.getElementById('13_1').select();
		return false;
	}*/
	if(parseFloat(document.getElementById('12_1').value)<0){
		alert("现第12行数据小于零，请核对！");
		window.event.returnValue=false;
		document.getElementById('12_1').focus();
		document.getElementById('12_1').select();
		return false;
	}
	
	/* modify by guoxh, 2012-2-24
	if(parseFloat(document.getElementById('13_1').value)>parseFloat(document.getElementById('12_1').value)){
		alert("第13行的值必须小于等于第12行的值!");
		window.event.returnValue=false;
		document.getElementById('13_1').focus();
		document.getElementById('13_1').select();
		return false;
	}*/

	if((parseFloat(document.getElementById('2_1').value)>0)&&(parseFloat(document.getElementById('2_1').value)>parseFloat(document.getElementById('1_1').value))){
		alert("现第2行数据大于第1行数据，请核对！");
	}

	if((parseFloat(document.getElementById('3_1').value)>0)&&(parseFloat(document.getElementById('3_1').value)>(parseFloat(document.getElementById('1_1').value)-parseFloat(document.getElementById('2_1').value)))){
		alert("现第3行数据大于第1行减第2行数据，请核对！");
	}
	return true;
}

function doSave(){
	var old  = document.forms[0].ywczlx.value;
    return SaveExec(old);
}

<%/*保存时对第1、14行的数据进行校验*/%>
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
        alert("未知的操作类型：" + document.forms[0].ywczlx.value);
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

//验证数据的合法性
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
 * 格式化输入数据以0开头
 *
 * @param 文本框
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
	* Notes: 本函数由第一、二、三行触发，并顺序计算申报表关联行的数值。适用于2012版B类表
	* Author: Guoxh, 2012-2-28
	*/
	function compute_Row_1(idName){
		var ynssds; 
		var ybsds;
		if(parseFloat(document.getElementById(idName).value)<0){
			alert("输入的金额必须大于等于0!");
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
			//第2行≤第1行
			if(row2Val > row1Val){
				alert("第2行的值必须小于等于第1行的值!");
				window.event.returnValue=false;
				document.getElementById('2_1').focus();
				document.getElementById('2_1').select();
				return false;
			}
			//第3行≤第1-2行
			if(row3Val > (row1Val-row2Val)){
				alert("第3行的值必须小于等于第1行减去第2行的差值!");
				window.event.returnValue=false;
				document.getElementById('3_1').focus();
				document.getElementById('3_1').select();
				return false;
			}
		}
		if(document.getElementById('1_1').value==""){
			ynssds=0;
		}else{
			//4行 应税收入额（1-2-3）
			yssre=Math.round((parseFloat(document.getElementById('1_1').value)-parseFloat(document.getElementById('2_1').value)-parseFloat(document.getElementById('3_1').value))*100)/100;
			document.getElementById('4_1').value=parseFloat(yssre);
			formate(document.getElementById('4_1'));

			//4行 X 5行，guoxh，2012-2-24
			ynssds= Math.round((parseFloat(yssre) * parseFloat(document.getElementById('5_1').value))*100)/100;
		}
		document.getElementById('6_1').value = parseFloat(ynssds);
		formate(document.getElementById('6_1'));
		var ynsdse =Math.round((parseFloat(document.getElementById('6_1').value) * parseFloat(document.getElementById('10_1').value))*100)/100;
			
		document.getElementById('11_1').value = parseFloat(ynsdse);
		formate(document.getElementById('11_1'));
		
		ybsds=Math.round((parseFloat(document.getElementById('11_1').value)-parseFloat(document.getElementById('12_1').value))*100)/100;
		if(ybsds>0){
			document.getElementById('13_1').value=parseFloat(ybsds);
			
		}
		else{
			document.getElementById('13_1').value=0;
		}
		formate(document.getElementById('13_1'));
	}
	<%/*第1行输入判断*/%>
	function compute_Row_1(){
		var ynssds; 
		var ybsds;
		if(document.getElementById('1_1').value==""){
			document.getElementById('1_1').value=0;
		}
		if(parseFloat(document.getElementById('1_1').value)<0){
			alert("现第1行数据小于零，请核对！");
		}
		if(document.getElementById('1_1').value==""){
			ynssds=0;
		}else{
			//4行 应税收入额（1-2-3）
			yssre=Math.round((parseFloat(document.getElementById('1_1').value)-parseFloat(document.getElementById('2_1').value)-parseFloat(document.getElementById('3_1').value))*100)/100;
			document.getElementById('4_1').value=parseFloat(yssre);
			formate(document.getElementById('4_1'));

			//4行 *5行
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
	
		ybsds=Math.round((parseFloat(document.getElementById('11_1').value)-parseFloat(document.getElementById('12_1').value))*100)/100;
		if(ybsds>0){
			document.getElementById('13_1').value=parseFloat(ybsds);			
		}
		else{
			document.getElementById('13_1').value=0;
		}
		formate(document.getElementById('13_1'));

		compute_Row_2();
		//compute_Row_3();
	}
	<%/*第2行输入判断*/%>
	function compute_Row_2(){
		var ynssds; 
		var ybsds;
		if(document.getElementById('2_1').value==""){
			document.getElementById('2_1').value=0;
		}
		if(parseFloat(document.getElementById('2_1').value)<0){
			alert("现第2行数据小于零，请核对！");
		}
		if((parseFloat(document.getElementById('2_1').value)>0)&&(parseFloat(document.getElementById('2_1').value)>parseFloat(document.getElementById('1_1').value))){
			alert("现第2行数据大于第1行数据，请核对！");
		}
		if(document.getElementById('1_1').value==""){
			ynssds=0;
		}else{
			//4行 应税收入额（1-2-3）
			yssre=Math.round((parseFloat(document.getElementById('1_1').value)-parseFloat(document.getElementById('2_1').value)-parseFloat(document.getElementById('3_1').value))*100)/100;
			document.getElementById('4_1').value=parseFloat(yssre);
			formate(document.getElementById('4_1'));

			//4行 *5行
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
	
		ybsds=Math.round((parseFloat(document.getElementById('11_1').value)-parseFloat(document.getElementById('12_1').value))*100)/100;
		if(ybsds>0){
			document.getElementById('13_1').value=parseFloat(ybsds);			
		}
		else{
			document.getElementById('13_1').value=0;
		}
		formate(document.getElementById('13_1'));
		compute_Row_3();
	}

	<%/*第3行输入判断*/%>
	function compute_Row_3(){
		var ynssds; 
		var ybsds;
		if(document.getElementById('3_1').value==""){
			document.getElementById('3_1').value=0;
		}
		if(parseFloat(document.getElementById('3_1').value)<0){
			alert("现第3行数据小于零，请核对！");
		}
		if((parseFloat(document.getElementById('3_1').value)>0)&&(parseFloat(document.getElementById('3_1').value)>(parseFloat(document.getElementById('1_1').value)-parseFloat(document.getElementById('2_1').value)))){
			alert("现第3行数据大于第1行减第2行数据，请核对！");
		}
		if(document.getElementById('1_1').value==""){
			ynssds=0;
		}else{
			//4行 应税收入额（1-2-3）
			yssre=Math.round((parseFloat(document.getElementById('1_1').value)-parseFloat(document.getElementById('2_1').value)-parseFloat(document.getElementById('3_1').value))*100)/100;
			document.getElementById('4_1').value=parseFloat(yssre);
			formate(document.getElementById('4_1'));

			//4行 *5行
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
	
		ybsds=Math.round((parseFloat(document.getElementById('11_1').value)-parseFloat(document.getElementById('12_1').value))*100)/100;
		if(ybsds>0){
			document.getElementById('13_1').value=parseFloat(ybsds);			
		}
		else{
			document.getElementById('13_1').value=0;
		}
		formate(document.getElementById('13_1'));
	}
	
	<%/*第11行输入判断*/%>
	function compute_Row_11(){
		var ybsds;
		if(parseFloat(document.getElementById('11_1').value)<0){
			alert("现第11行数据小于零，请核对！");
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
		ybsds=Math.round((parseFloat(document.getElementById('11_1').value)-parseFloat(document.getElementById('12_1').value))*100)/100;
			if(ybsds>0){
				document.getElementById('13_1').value=parseFloat(ybsds);
			}
			else{
				document.getElementById('13_1').value=0;
			}
			formate(document.getElementById('13_1'));
	}
			<%/*第12行输入判断*/%>
	function compute_Row_12(){
		var ybsds;
		if(parseFloat(document.getElementById('12_1').value)<0){
			alert("现第12行数据小于零，请核对！");
			//window.event.returnValue=false;
			//document.getElementById('12_1').focus();
			//document.getElementById('12_1').select();
			//return false;
		}
		if(document.getElementById('12_1').value==""){
			document.getElementById('12_1').value=0;
		}
		ybsds=Math.round((parseFloat(document.getElementById('11_1').value)-parseFloat(document.getElementById('12_1').value))*100)/100;
			if(ybsds>0){
				document.getElementById('13_1').value=parseFloat(ybsds);			
			}
			else{
				document.getElementById('13_1').value=0;
			}
			formate(document.getElementById('13_1'));
	}
		function checkNumInput(obj)
{
		//判断输入的数据是否符合要求
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;			
		}
		//格式化数据
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
<form name="hdzsjbForm" action="hdzsqyjb2012.dc">
<input name="actionType" type="hidden" id="actionType" value="Show">
	<table width="96%" align="center" cellspacing="0" class="table-99" onKeyDown="jsjdmQuery()">
		<tr>
		<td align="center" valign="top" colspan="7">
        <jsp:include page="../../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="企业所得税月(季)度预缴纳税申报表(B类)" />
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
					<a style="cursor:hand" tabindex="-1" onClick="javascript:return doSubmit('Save');return false;"><img  name="spage" value="保存" alt="保存" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>/images/baocun2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/baocun1.jpg" width="79" height="22" id="baocun"></a>
					<a style="cursor:hand" tabindex="-1" onClick="javascript:return doSubmit('Delete');return false;"><img name="dpage" value="删除" alt="删除" onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>/images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/shanchu1.jpg" width="79" height="22" id="shanchu"></a>
					<a style="cursor:hand" tabindex="-1" onClick="javascript:return doSubmit('Reset');return false;"><img name="qpage" value="清除" alt="清除" onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>/images/qingchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/qingchu1.jpg"  width="79" height="22" id="qingchu"></a>
					<a style="cursor:hand" tabindex="-1" onClick="javascript:return doSubmit('Return');return false;"><img  name="bpage" value="返回" alt="返回" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/fanhui1.jpg"  width="79" height="22" id="fanhui"></a>
				
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
