<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml" %>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.constant.CodeTable"%>
<%@page import="com.syax.bjtax.shenbao.model.dm.ZYZHLYZL"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
			List jmlxList = (List) request.getAttribute("CS");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
  <head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="JavaScript" type="text/JavaScript"
	src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_contextpath%>js/date.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js1/WdatePicker.js"></script>
    <script language="JavaScript" src="js/calculate.js">
    </script>

<link href="css/jmba.css" rel="stylesheet" type="text/css">

<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
<script language="JavaScript">

var jmlx= new Array(<%=jmlxList.size()%>);

<%
for(int i=0;i<jmlxList.size();i++)
{
	ZYZHLYZL tmpJmlx = (ZYZHLYZL)jmlxList.get(i);
	out.println("jmlx["+i+"] = [\""+tmpJmlx.getZYZHLYZLDM()+"\",\""+tmpJmlx.getZYZHLYZLMC()+"\"];");
}

%>
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


function parseXmlOnLoad()
{
	var xslPath='/XSLTWEB/model/030001/XSLT/zyzhly01edit.xsl';

	if (strXml != "")
    {
        if (! parseXml(strXml,xslPath,"result"))
            return false;
    }
    
    insertJmlx();
    return true;
}


function insertJmlx()
{
  			var obj=document.forms[0].zyzhlyzldm;

			for(var i=0;i<jmlx.length;i++)
			{
				var yValue=jmlx[i][0];
				var yName=jmlx[i][1];
				var b=true;
				if(yValue==obj.value)
				{
					b=false;
				}
				if(b)
				{
					var item=new Option(yName,yValue);
					obj.options.add(item);
				}					
			}
 
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

	applendElement("/taxdoc","jmsbajl",["basqbh","basqwsh","band","jmbasxdm","jmbasxmc","ztdm","ztmc","szdm","szmc","lrrq"]);
	//申报数据
	getSbsj(objForm);	
	
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	return retstr;
}
function getChildren(temp,strTag)
{
	var element=document.getElementById(strTag);
	var objTemp=temp;
	var node=g_Doc.XMLDoc.createElement(strTag);
	objTemp.appendChild(node);
	var strValue="";
	if(element!=null)
	{ 	
		strValue=formString(element.value);		
		var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
		node.appendChild(objCDATA);
	}	
}
	<%/*效验页面元素*/%>
	function checkYm(){
		var wjmc = trim(document.all("wjmc").value);
		if(wjmc == "" ){
			alert("文件名称不能为空!");
			document.forms[0].wjmc.focus();
			return false;
		}
		var wh = trim(document.all("wh").value);
		 if(wh == "" ){
			alert("文号不能为空!");
			document.forms[0].wh.focus();
			return false;
		 }
		 var zyzhlyzl = document.all("zyzhlyzldm").value;
		 if(zyzhlyzl == "" ){
			alert("资源综合利用种类不能为空!");
			document.forms[0].zyzhlyzldm.focus();
			return false;
		 }
		 
		 var zsbh = trim(document.all("zsbh").value);
		 if(zsbh == "" ){
			alert("资源综合利用认定证书编号不能为空!");
			document.forms[0].zsbh.focus();
			return false;
		 }
		 
		 var zsyxksrq = document.all("zsyxksrq").value;
		 if(zsyxksrq == "" ){
			alert("证书有效期起始日期不能为空!");
			document.forms[0].zsyxksrq.focus();
			return false;
		 }
		 var zsyxjzrq = document.all("zsyxjzrq").value;
		 if(zsyxjzrq == "" ){
			alert("证书有效期终止日期不能为空!");
			document.forms[0].zsyxjzrq.focus();
			return false;
		 }
		 var qdsr = trim(document.all("qdsr").value);
		if(qdsr == ""){
			alert("综合利用资源生产符合国家产业政策规定的产品所取得的收入不能为空！");
			document.forms[0].qdsr.focus();
			return false;
		}else{
			if (!formatCurrency10(document.all("qdsr"),1)){ 
				alert("综合利用资源生产符合国家产业政策规定的产品所取得的收入格式不正确！"); 
				document.forms[0].qdsr.focus();
				return false; 
			}
		}
		 return true;
		
	}
	<%/*效验日期*/%>
	function checkDate(){
		var zsyxksrq = document.forms[0].zsyxksrq.value;
		var zsyxjzrq = document.forms[0].zsyxjzrq.value;
		d1Arr=zsyxksrq.split('-');
		d2Arr=zsyxjzrq.split('-');
		v1=new Date(d1Arr[0],d1Arr[1],d1Arr[2]);
		v2=new Date(d2Arr[0],d2Arr[1],d2Arr[2]);
		if(v1>v2){
			alert("起始日期不能晚于终止日期");
			return false;
		}
		return true;
	}
	<%/*鼠标移动*/%>
	function change(){
		var qdsr = document.all("qdsr").value;
		if(qdsr == ""){
			alert("综合利用资源生产符合国家产业政策规定的产品所取得的收入不能为空！");
			document.forms[0].qdsr.focus();
			return false;
		}else{
			
			flg=0;
			zfgs = 0;
			cmp="0123456789.";
			for (var i=0;i<qdsr.length;i++){  
				tst=qdsr.substring(i,i+1);
				if (cmp.indexOf(tst)<0){
					flg++; 
				}else{
					if(tst == "."){
						zfgs++;
					}
				}
			} 
			if (flg!=0 || zfgs > 1){ 
				alert("综合利用资源生产符合国家产业政策规定的产品所取得的收入格式不正确！"); 
				document.forms[0].qdsr.focus();
				return false; 
			}
		}
		formatCurrency(document.forms[0].qdsr,0);
		var qdsr1 = qdsr;
		qdsr = round2(qdsr1);
		document.all("qdsr").value = qdsr;
		var jjsr = round2(qdsr*0.1);
		document.all("jjsr").value = jjsr;
	}
function formatCurrency10(obj,par)
{
if (par==0 ){
  if (obj==null || obj.value==null || obj.value==""){
     alert("输入值为空！");
     return false;
  }
}
return checkvalue(obj,0);//&&formatCurrency(obj,0)
}
	
function getRadioChildren(temp,strTag)
{
    var obj=document.getElementsByName(strTag);
	var objTemp=temp;
	var node=g_Doc.XMLDoc.createElement(strTag);
	objTemp.appendChild(node);
	var strValue=formString(getRadioValue(obj));
	var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
	node.appendChild(objCDATA);
	return strValue;
}

//生成申报数据
function getSbsj(objForm)
{   
    var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc/jmsbajl");
	var objTemp = g_Doc.XMLDoc.createElement("qysdsjmba");
	objNode.appendChild(objTemp);
	getChildren(objTemp,"xh");
	getChildren(objTemp,"wjmc");
	getChildren(objTemp,"wh");
	getChildren(objTemp,"zyzhlyzldm");

	getChildren(objTemp,"zsyxksrq");
	getChildren(objTemp,"zsyxjzrq");
	getChildren(objTemp,"qdsr");
	getChildren(objTemp,"jjsr");
	getChildren(objTemp,"zsbh");
	
	document.forms[0].zyzhlyzlmc.value= document.forms[0].zyzhlyzldm.options[document.forms[0].zyzhlyzldm.selectedIndex].innerText;
	getChildren(objTemp,"zyzhlyzlmc");
	
	
	
}
function doSave(value){
	
	if(!checkYm() || !checkDate()){
		return false;
   }
   
   	if(!confirm("是否保存录入数据?"))
	{
		return false;
	}	
	var old  = document.forms[0].ywczlx.value;
		
		document.forms[0].actionType.value="Save";
		
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
 
 
function doEditZb(){
	
		if(!checkYm() ){
		return false;
   }
   if(!confirm("是否生成备案表?"))
	{
		return false;
	}	
	
	var old  = document.forms[0].ywczlx.value;
		
		document.forms[0].actionType.value="EditZb";
		
		if (g_objSI.Container != '')
		{ 
			if (!doSubmitXML(document.forms[0],"EditZb",true,"",true))
			{  
					document.forms[0].ywczlx.value = old;
				return false;
			}
		}
		else
		{  
			if(!doSubmitXML(document.forms[0],"EditZb",false,"",true))
			{  
				document.forms[0].ywczlx.value = old;
				return false;
			}			   
		}
		return true;
}
 
function doReturn()
{
	document.forms[0].action = "/shenbao/jmbaz.dc";
	document.forms[0].actionType.value="Show";
	document.forms[0].submit();
}
function doReset(){
document.forms[0].wjmc.value="";
document.forms[0].wh.value="";
document.forms[0].qdsr.value="";
document.forms[0].jjsr.value="";
document.forms[0].zsbh.value="";
document.forms[0].zyzhlyzldm.options[0].selected=true;
document.forms[0].zsyxksrq.value="";
document.forms[0].zsyxjzrq.value="";
}
 
 function getRadioValue(obj)
{
		var objLength = obj.length;
		var k=0;
		for (i=0;i<objLength;i++)
		{
			if(obj[i].checked == true)
			{
				k=i;//寻找radio数组中被选中的值的位置
				return obj[k].value;//给对象赋值
			}		
		}
		return "";
     
}
</script>


   <title>
      资源综合利用企业（项目）申请减免企业所得税备案事项
    </title>
 
  </head>
  
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
 onload="parseXmlOnLoad()">
  
  <table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2 valign="top">
    	<jsp:include page="../../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="录入纳税人企业所得税减免税备案申请" />
		<jsp:param name="help_url" value="help/wssb/sbzl/jms/jms-000.htm"/>
    	</jsp:include>
        <html:errors/>
 

    <form name="Form1" method="post" action="/shenbao/zyzhly01.dc">
 	<input name="actionType" type="hidden" id="actionType"></input>
       <table width="770" border="0" cellpadding="0" cellspacing="0" align="center" >
        <tr>
          <td valign="top" class="title">
            <br>
            <table width="75%" cellspacing=0 border=0 class="table-99" style="TABLE-LAYOUT:fixed">
              <tr>
                <td class="1-td1">
                  录入资源综合利用企业（项目）申请减免企业所得税备案事项
                </td>
              </tr>
              <tr>
                <td class="1-td2">
                  
                  <br>                 
<div id="result"></div>
                  <br>
               
               
                  <table width="100%" border="0" align="center">
                    <tr align="center">  
                    	
                               <td>
 												                     
                      <img src="<%=static_contextpath%>images/baocun1.jpg" onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="保存" onclick="return doSave()" style="cursor:hand">
                      </td>
                      <td>
                        
                        
                        
                        	<img src="<%=static_contextpath%>images/b_scbab1.jpg" onmouseover="this.src='<%=static_contextpath%>images/b_scbab2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/b_scbab1.jpg'" alt="生成备案表" onclick="return doEditZb()" style="cursor:hand">
                      </td>
                      <td>
                        <img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doReturn()" style="cursor:hand">
                      </td>
                    </tr>
                  </table>
                  <br>
                  <!--
                  <table width="99%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>
                        <hr width="100%" size="1" style="color='#003399'">
                      </td>
                      <td width="99" align="center" class="black9">
                        <strong>
                          <font color="#0000FF">
                            注 意 事 项
                          </font>
                        </strong>
                      </td>
                      <td>
                        <hr width="100%" size="1" style="color='#003399'">
                      </td>
                    </tr>
                  </table>
                  <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1"
                  bordercolor="#FFFFFF" class="black9">
                    <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                      <td height="47">
                        &nbsp;&nbsp;1. 资源综合利用认定证书编号：手工录入证书编号;
                        <br>
                        &nbsp;&nbsp;2. 证书的有效期：通过日历表的形式选择证书的有效期起止日期;
                        <br>
                        &nbsp;&nbsp;3. 北京市发展和改革委员会公布资源综合利用企业认定企业名单的文件：分别录入文件名称及文件号,并按名单上资源综合利用种类的内容分行次填写“资源综合利用种类”栏，行次可无限量增加（税务机关一定要严格按照文件中的项目把关）;
                        <br>
                        &nbsp;&nbsp;4. 是否提交企业实际资源综合利用情况及分别核算资源综合利用收入的声明：选择 “是”或 “否”;
                        <br>
                        &nbsp;&nbsp;5. 综合利用资源生产符合国家产业政策规定的产品所取得的收入（单位：元）：手工录入项;
                        <br>
                        &nbsp;&nbsp;6. 减计收入额（单位：元）：该栏次自动生成，生成数据=“综合利用资源生产符合国家产业政策规定的产品所取得的收入”*10%
                        ;
                        <br>
                        <br>
                        &nbsp;&nbsp;3.1备案表各栏目为必填项，如未填写则不能向税务提交申请。
                        <br>
                        &nbsp;&nbsp;3.2 表中选择是否的条件中，有任何一条选择否，则不能向税务提交申请。
                        <br>
                        &nbsp;&nbsp;3.3 纳税人只有提交备案申请并经税务机关审核成功后方可开放“中华人民共和国企业所得税年度纳税申报表（A类）——附表五税收优惠明细表”第7行，允许在汇算清缴时填写数据，否则，对应行次系统默认为不允许编辑状态。
                        <br>
                        <br>
                        &nbsp;&nbsp;1. 审核结果中至少有一行选择为“是”才可确定“企业综合利用资源，生产符合国家产业政策规定的产品所取得的收入”备案事项审核成功，否则则视为实质性不符合，拒绝该备案项目的申请。；
                        <br>
                      </td>
                    </tr>
                  </table>
                  -->
                  <br>
                  </td>
              </tr>
            </table>
        
            </td>
        </tr>
      </table>
    </form>
  
   	<tr><td valign="bottom" align="center"><br>
	<jsp:include page="../../include/bottom.jsp" flush="true">
	</jsp:include></td>
	</tr>
  </body>

</html>
