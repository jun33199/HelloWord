<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%
    String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<html>
<head>
<title>核定征收企业所得税年度纳税申报表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type = "text/JavaScript" src="js/function.js"></script>
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
//,"zcze_ljs","zczbje"
var InputName=['srze_ljs','yssdl_ljs','ynssde_bqs','sysl_ljs','yjsdse_ljs','sjyyjdsdse_ljs','sjyyjdsdse_bqs','ybdsdse_ljs'];
var InputNameAl=['收入总额','税务机关核定的应税所得率','应纳税所得额','税率','应纳所得税额（计算）','减免所得税额','已预缴所得税额','应补（退）所得税额' ];
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xmlDoc;

//解析xml
function parseXmlOnLoad()
{

	var urlXSL="/XSLTWEB/model/010024/XSLT/" +strXSLTVersion+".xsl";
	//	var urlXSL="shenbao/qysdsnew/hdzssdsnb.xsl";
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

	var qyzslx = document.forms[0].qyzslx.value;
	var cylValue = document.forms[0].cyl.value;
	if(cylValue==null||cylValue=="")
	{
	  cylValue = "0.00";
	}
	var dezsseValue = document.forms[0].dezsse.value;
	//定额
	if(qyzslx == 4 ){
		
		document.forms[0].srze_ljs.readOnly=true;
		document.forms[0].ynssde_bqs.readOnly=true;
		
		document.forms[0].srze_ljs.className='text-gray';
		document.forms[0].ynssde_bqs.className='text-gray';
		
	}
	else
	{
    document.forms[0].yjsdse_ljs.readOnly=true;	
		document.forms[0].yjsdse_ljs.className='text-gray';
		document.forms[0].yssdl_ljs.value = Math.round(cylValue*100);
	}
	computeYbtsdse();

}
	function ssshmx(){


    var allInput=[document.forms[0].srze_ljs,document.forms[0].sjyyjdsdse_bqs];
	for(var i=0; i<allInput.length; i++)
	{
        if(!formatData(allInput[i]))
		{
			return false;
		}

	}

		document.forms[0].actionType.value="Fb";
		if (g_objSI.Container != '')
		{
				if (!doSubmitXML(document.forms[0],"Fb",true,"",true))
					{
							document.forms[0].ywczlx.value = old;
							return false;
					}	
		}else
		{
				if (!doSubmitXML(document.forms[0],"Fb",false,"",true))
					{
							document.forms[0].ywczlx.value = old;
							return false;
					}	
		}
		return true;
				
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
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);
	//核定信息
	applendElement("/taxdoc","hdxx",["jmzg","ybjmsl","qyzslx","cyl","xzqy","dezsse"]);
	//申报信息
	applendElement("/taxdoc","sbxx",["fsdm","jd","nd","sbrq","skssksrq","skssjsrq"]);
	//申报数据
	applendElement("/taxdoc","sbsj",["srze_ljs","yssdl_ljs","ynssde_bqs","sysl_ljs","yjsdse_ljs","sjyyjdsdse_ljs","sjyyjdsdse_bqs","ybdsdse_ljs" ,"zcze_ljs","zczbje"]);
	
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
		doSave();
	}else if(actionType=='Delete'){
		doDelete();
	}else{
		doReturn();
	}
}

function clearInput(){
	for(i=0;i<InputName.length;i++){
	if(InputName[i]!="sysl_ljs"&&InputName[i]!="sjyyjdsdse_ljs"&&InputName[i]!="yssdl_ljs"){
		var item=document.getElementById(InputName[i]);
		item.value="0.00";
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

		//判断空值，为空则将值复位为零
/*	for(i=0;i<InputName.length;i++){
		var item=document.getElementById(InputName[i]);
		if( (item.value)=="" || (item.value)==null ){			
			document.getElementById(InputName[i]).value='0.00';
		} 
	}

		var item=document.getElementById("zczbje");
		var item1=document.getElementById("zcze_ljs");
		if( (item.value)==null || (trim(item.value))=="" || (item1.value)==null) || (trim(item1.value)=="") ){
		   alert("输入值错误,请检查!");
		   return false;			
        }	

*/
	
		//判断非负数
		
	
		var qyzslx = document.forms[0].qyzslx.value;
	
			//1
	
		var srze_ljs = document.forms[0].srze_ljs.value;
	
			//2
	
		var yssdl_ljs = document.forms[0].yssdl_ljs.value;
	
			//3
	
		var ynssde_bqs =document.forms[0].ynssde_bqs.value;
	
	
			//15
	
		var ybdsdse_ljs =document.forms[0].ybdsdse_ljs.value;
	
	
			//14
	
		var sjyyjdsdse_bqs =document.forms[0].sjyyjdsdse_bqs.value;
	
			//13
	
		var sjyyjdsdse_ljs =document.forms[0].sjyyjdsdse_ljs.value;
	
			//12
	
		var yjsdse_ljs =document.forms[0].yjsdse_ljs.value;
	
			//11
	
		var sysl_ljs = document.forms[0].sysl_ljs.value;
	

			
	var xxwl_je =  document.forms[0].xxwl_je.value;
	var xxwl_bjz = ynssde_bqs*0.05;
	if(xxwl_je!=null&&xxwl_je!=""&&parseFloat(xxwl_je)!=0)
	{
		if(parseFloat(xxwl_je)!=Math.round(xxwl_bjz*100)/100)
		{
			alert("附表第34行不为零并且不为本表第三行的百分之五！ 请检查");
			document.forms[0].sjyyjdsdse_ljs.focus();
			return false;
		}

	}
		
	
			var bjz =  parseFloat(yjsdse_ljs)-parseFloat(sjyyjdsdse_ljs)-parseFloat(sjyyjdsdse_bqs);

	
			if(Math.round(bjz*100)/100!= ybdsdse_ljs)
	{
	   
			if(bjz < 0&&ybdsdse_ljs == 0){
    		
			}else
{
	
			   alert("公式 行15=行12-行13-行14 而表中应补（退）所得税额行15=" + document.forms[0].ybdsdse_ljs.value +  " 请修改");
				
			    document.forms[0].ybdsdse_ljs.focus();
				document.forms[0].ybdsdse_ljs.select();
    		   return false;
 
			}
	}
	if(parseFloat(sjyyjdsdse_ljs)>parseFloat(yjsdse_ljs))
	{
				alert("行13减免所得税额必须小于等于行12应纳所得税额, 请修改");
				document.forms[0].yjsdse_ljs.focus();
				document.forms[0].yjsdse_ljs.select();
    		return false;
	}

	
/**	if(ybdsdse_ljs<0){
				
	alert("行15 应补（退）所得税额 不能小于0，请修改");
				
	document.forms[0].ybdsdse_ljs.focus();
	 document.forms[0].ybdsdse_ljs.select();
    		return false;}**/	

	
   if(qyzslx != 4 ){
	var bjz2 = parseFloat(srze_ljs) * (parseFloat(yssdl_ljs)/100);
			if(parseFloat(ynssde_bqs) != Math.round(bjz2*100)/100 ){
				alert("公式 行3=行1×行2 而表中应纳税所得额行3=" + document.forms[0].ynssde_bqs.value + " 行1×行2=" + Math.round(bjz2*100)/100 + " 请修改");
				document.forms[0].ynssde_bqs.focus();
				document.forms[0].ynssde_bqs.select();
				return false;
				}
	var bjz3 = parseFloat(ynssde_bqs) * (parseFloat(sysl_ljs)/100);
			if(parseFloat(yjsdse_ljs) != Math.round(bjz3*100)/100 ){
				alert("公式 行12=行3×行11 而表中应纳所得税额行12=" + document.forms[0].yjsdse_ljs.value + " 行3×行11=" + Math.round(bjz3*100)/100 + " 请修改");
				document.forms[0].yjsdse_ljs.focus();
				document.forms[0].yjsdse_ljs.select();
				return false;
	  
	}
	}
		
	var old  = document.forms[0].ywczlx.value;

    return SaveExec(old);
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
function formatData(obj)
{
	return (checkvalue(obj,1) && formatCurrency(obj,0) );
}
function formatData2(obj)
{
	return (checkvalue(obj,1) && formatCurrency(obj,0) && computeSl(obj));
}

//intFLag 0--大于等于零  资产总额校验
//intFLag 1--大于零  注册资本金额校验
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
					alert("资产总额必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
				    tmp = tmpValue;
  				    return true;
				}
				break;
			case 1:
				
				if(tmpValue == null || isNaN(tmpValue) || tmpValue < 0 ||!formatCurrency10(obj,0))
				{
					alert("注册资本金额必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
					obj.focus();
					obj.select();
					return false;
				}
				else
				{
				    tmp = tmpValue;
  				    return true;
				}

				break;
			default:
				return false;
				break;
			}
}

// 将字符串格式化为货币格式
// par为0，自动填充0.00
// par为1，不自动填充
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



// 自动计算
function computeYnssde()
{

	var qyzslx = document.forms[0].qyzslx.value;
	
   //1
	
   var srze_ljs = document.forms[0].srze_ljs.value;
	
   if(srze_ljs==null||srze_ljs==""||!isNumber(srze_ljs))
   {
       srze_ljs= "0.00";
   }
  //2
	
   var yssdl_ljs = document.forms[0].yssdl_ljs.value;
	

  if(qyzslx != 4 ){
   
	   var bjz2 = parseFloat(srze_ljs) * (parseFloat(yssdl_ljs)/100); 
  
	  document.forms[0].ynssde_bqs.value=Math.round(bjz2*100)/100;
   
	   if(!isNumber(bjz2))
   {
      document.forms[0].ynssde_bqs.value="";
   } 
   
 
	   computeYnssdse();
  
	   computeYbtsdse();
   
	   return false;
 
	   }

}
   

function computeYnssdse()
   {

	
   var qyzslx = document.forms[0].qyzslx.value;
	
   //3
	
   var ynssde_bqs =document.forms[0].ynssde_bqs.value;
	
	
	   //15
	
   var ybdsdse_ljs =document.forms[0].ybdsdse_ljs.value;
	
	
	   //14
	
   var sjyyjdsdse_bqs =document.forms[0].sjyyjdsdse_bqs.value;
	
	   //13
	
   var sjyyjdsdse_ljs =document.forms[0].sjyyjdsdse_ljs.value;
	
	   //12
	
   var yjsdse_ljs =document.forms[0].yjsdse_ljs.value;
	
	   //11

   var sysl_ljs = document.forms[0].sysl_ljs.value;
	
	   
if(qyzslx != 4 ){
   
	   var bjz2 = parseFloat(ynssde_bqs) * (parseFloat(sysl_ljs)/100);  
 
	   document.forms[0].yjsdse_ljs.value=Math.round(bjz2*100)/100;
 
	   if(!isNumber(bjz2))
   
	   {
     
	     document.forms[0].yjsdse_ljs.value="";
  
	   }    
  
	   computeYbtsdse();
  
	   return false;

	   }
   

}

function computeYbtsdse(){


	   var qyzslx = document.forms[0].qyzslx.value;
	
	   //3
	
      var ynssde_bqs =document.forms[0].ynssde_bqs.value;
	
	
		  //15
	
	  var ybdsdse_ljs =document.forms[0].ybdsdse_ljs.value;
	
	
		  //14
	
	  var sjyyjdsdse_bqs =document.forms[0].sjyyjdsdse_bqs.value;
	
   if(sjyyjdsdse_bqs==null||sjyyjdsdse_bqs==""||!isNumber(sjyyjdsdse_bqs))
   {
       sjyyjdsdse_bqs= "0.00";
   }

		  //13
	
	  var sjyyjdsdse_ljs =document.forms[0].sjyyjdsdse_ljs.value;
	
		  //12
	
	  var yjsdse_ljs =document.forms[0].yjsdse_ljs.value;
	
		  //11
	
	  var sysl_ljs = document.forms[0].sysl_ljs.value;
	

	
	  var bjz =  parseFloat(yjsdse_ljs)-parseFloat(sjyyjdsdse_ljs)-parseFloat(sjyyjdsdse_bqs);
	
		  //if(bjz<0)

		  //{
	document.forms[0].ybdsdse_ljs.value = "0.00";
	 
		  //return false;
	
		  //}

  
		  document.forms[0].ybdsdse_ljs.value=Math.round(bjz*100)/100;
     
		  if(!isNumber(bjz))
   {
     
		  document.forms[0].ybdsdse_ljs.value="";
   
		  } 

  
		  return false;
	  

}

function isNumber(str)
{
	//if(!Number(str))
	if(isNaN(Number(str)))
		return false;
	return true;
}
function checkNumInput(obj)
{
		//判断输入的数据是否符合要求
		if(!isNum(getCellObject() , null, 9999999999999, null, 16, 2)){
			return false;			
		}
		//格式化数据
		formate(obj);
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
</style>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="return parseXmlOnLoad();"> 
 <html:errors/>
<form name="hdzssdsnbForm" action="hdzsqynb.dc">
<input name="actionType" type="hidden" id="actionType" value="Show">
	<table width="96%" align="center" cellspacing="0" class="table-99" onKeyDown="jsjdmQuery()">
		<tr>
		<td align="center" valign="top" colspan="7">
        <jsp:include page="../../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="中华人民共和国企业所得税年度纳税申报表(B类)" />
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
					
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Save');return false;"><img  name="spage" value="保存" alt="保存" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>/images/baocun2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/baocun1.jpg" width="79" height="22" id="baocun"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Delete');return false;"><img name="dpage" value="全部删除" alt="全部删除" onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>/images/qbsc-x2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/qbsc-x1.jpg" width="79" height="22" id="shanchu"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Reset');return false;"><img name="qpage" value="清除" alt="清除" onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>/images/qingchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/qingchu1.jpg"  width="79" height="22" id="qingchu"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Return');return false;"><img  name="bpage" value="返回" alt="返回" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/fanhui1.jpg"  width="79" height="22" id="fanhui"></a>
				
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