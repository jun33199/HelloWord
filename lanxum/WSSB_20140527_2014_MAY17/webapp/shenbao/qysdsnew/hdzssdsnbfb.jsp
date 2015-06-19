<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%
    String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);

%>
<html>
<head>
<title>˰���Ż���ϸ��</title>
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
var InputName=['jmshj_je','xxwl_je','gxqy_je','mzzz_je','gdqyh_je','qt_je','qyrs','zcze','sshy'];
var InputNameAl=['����˰�ϼ�','����������С��΢����ҵ','������Ҫ�ص���ֵĸ��¼�����ҵ','�������εط�����ҵӦ���ɵ���ҵ����˰�����ڵط�����Ĳ���','������˰���Ż�','����','��ҵ��ҵ����','�ʲ��ܶ�','������ҵ'];
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var zxlx = '<%=request.getAttribute("zxlx")==null?"":request.getAttribute("zxlx")%>';

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

	var qyzslx;
	var cylValue = document.forms[0].cyl.value;
	var dezsseValue = document.forms[0].dezsse.value;
	var sshyValue = document.forms[0].sshy.value;
	//����
	if(zxlx=="2"){
		document.forms[0].xxwl_je.value ="0.00";
		document.forms[0].qyrs.value ="0";
		document.forms[0].zcze.value ="0.00";
		
		document.forms[0].xxwl_je.readOnly=true;
		document.forms[0].qyrs.readOnly=true;
		document.forms[0].zcze.readOnly=true;
		document.getElementById("hy47_1").disabled=true;
		document.getElementById("hy47_2").disabled=true;
		
		document.forms[0].xxwl_je.className='text-gray';
		document.forms[0].qyrs.className='text-gray';		
		document.forms[0].zcze.className='text-gray';
		document.forms[0].hyje.className='text-gray';
		document.getElementById("hy47_1").className='text-gray';
		document.getElementById("hy47_2").className='text-gray';
	  document.forms[0].sshy.value = "";
	  computeJe();
	}else{
	if(sshyValue=="01")
	{
	  document.getElementById("hy47_1").checked =  true;
	}
	else if(sshyValue=="02"){
	document.getElementById("hy47_2").checked =  true;
	}
	//document.forms[0].qyrs.value ="0";
	}
}
		<%/*����ѡ�����õ�ѡ��ѡ�����*/%>
		function changeSelect()
	  {
	  	if(document.getElementById("hy47_1").checked == true)
	  	{
	  		document.all("sshy").value = "01";
	  	}
	  	else
	  	{
	  		document.all("sshy").value = "02";
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
	applendElement("/taxdoc","nsrxx",["jsjdm","nsrmc","swjgzzjgdm"]);
	//�˶���Ϣ
	applendElement("/taxdoc","hdxx",["jmzg","ybjmsl","qyzslx","cyl","xzqy","dezsse"]);
	//�걨��Ϣ
	applendElement("/taxdoc","sbxx",["fsdm","jd","nd","sbrq","skssksrq","skssjsrq"]);
	//�걨����
	applendElement("/taxdoc","sbsj",["sbrqshow","jmshj_je","xxwl_je","gxqy_je","mzzz_je","gdqyh_je","qt_je","qyrs","zcze","sshy"]);
	
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
	for(i=0;i<InputName.length;i++){
		var item=document.getElementById(InputName[i]);
		if(InputName[i]=="qyrs")
		{
		  item.value="0";
		}else if(InputName[i]=="sshy")
		{
		  item.value="";
	  document.getElementById("hy47_1").checked =  false;
	  document.getElementById("hy47_2").checked =  false;
		  
		}
		else{
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

		//�жϿ�ֵ��Ϊ����ֵ��λΪ��
	for(i=0;i<InputName.length-1;i++){
		var item=document.getElementById(InputName[i]);
		if( (item.value)=="" || (item.value)==null ){			
			document.getElementById(InputName[i]).value='0.00';
		} 
	}
	
	var qyzslx = document.forms[0].qyzslx.value;
	
		//33
	
	var jmshj_je = document.forms[0].jmshj_je.value;
	
		//34
	
	var xxwl_je = document.forms[0].xxwl_je.value;
	
		//35
	
	var gxqy_je =document.forms[0].gxqy_je.value;
	
	
		//36
	
	var mzzz_je =document.forms[0].mzzz_je.value;
	
	
		//37
	
	var gdqyh_je =document.forms[0].gdqyh_je.value;
	
		//38
	
	var qt_je =document.forms[0].qt_je.value;
	
		//45
	
	var qyrs =document.forms[0].qyrs.value;
	
		//46
	
	var zcze = document.forms[0].zcze.value;
	
		//47
	
	var hyje = document.forms[0].sshy.value;
	var zbYnsdse = 
	
document.forms[0].zbYnsdse.value;
	var bjwwxl_je = Math.round(parseFloat(zbYnsdse)*0.05*100)/100;

		var bjz =  parseFloat(xxwl_je)+parseFloat(gxqy_je)+parseFloat(mzzz_je)+parseFloat(gdqyh_je)+parseFloat(qt_je);

	if(Math.round(bjz*100)/100!= jmshj_je)
	{
				alert("��ʽ ��33=��34+��35+��36+��37+��38 ��" + Math.round(bjz*100)/100 + "�����м���˰�ϼ� ��33=" + jmshj_je +  " ���޸�");
				
			     document.forms[0].jmshj_je.focus();
				document.forms[0].jmshj_je.select();
    		return false;
	}
	
	if(zxlx!="2"){
	  if(xxwl_je>0){
		 if(xxwl_je!=bjwwxl_je)
		 {
             alert("��ʽ ��34�з���������С��΢����ҵ ��Ϊ0ʱ������Ϊ�����3�е�5%�������3��Ϊ��" + zbYnsdse + " ��5%Ϊ��"+ bjwwxl_je        + " ����");
			    document.forms[0].xxwl_je.focus();
				document.forms[0].xxwl_je.select();
				return false;
		 }
	     if(hyje=="01"){
	       if(qyrs <= 0||qyrs >100){
				    alert("��ʽ ��34>0 ���� ��47 ������ҵΪ��ҵ��ҵʱ: ��45 >0��<=100 ��������ҵ��ҵ���� ��45=" + qyrs +  " ���޸�");
				    document.forms[0].qyrs.focus();
				    document.forms[0].qyrs.select();
    		    return false;
	       }
	       if(zcze <= 0||zcze >30000000){
				    alert("��ʽ ��34>0 ���� ��47 ������ҵΪ��ҵ��ҵʱ: ��46 >0��<=30000000 �������ʲ��ܶ� ��46=" + zcze +  " ���޸�");
				    document.forms[0].zcze.focus();
				    document.forms[0].zcze.select();
    		    return false;
	       }
	       
	     }
	     else if(hyje=="02"){
	       if(qyrs <= 0||qyrs >80){
				    alert("��ʽ H34>0 ���� ��47 ������ҵΪ������ҵʱ:��45 >0��<=80 ��������ҵ��ҵ���� ��45=" + qyrs +  " ���޸�");
				    document.forms[0].qyrs.focus();
				    document.forms[0].qyrs.select();
    		    return false;
	       }
	       if(zcze <= 0||zcze >10000000){
				    alert("��ʽ H34>0 ���� ��47 ������ҵΪ������ҵʱ: ��46 >0��<=10000000 �������ʲ��ܶ� ��46=" + zcze +  " ���޸�");
				    document.forms[0].zcze.focus();
				    document.forms[0].zcze.select();
    		    return false;
	       }
	      
	     }
	     else
	    {
				 alert("��ʽ ��34>0 ʱ����ѡ��������ҵ");
    		 return false;
	     
	    }
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
function formatData(obj)
{
	return (checkvalue(obj,1) && formatCurrency(obj,0) );
}
function formatData2(obj)
{
	return (checkvalue(obj,1) && formatCurrency(obj,0) && computeSl(obj));
}

//�Զ�����
function computeJe(){

	//33
	
	var jmshj_je = document.forms[0].jmshj_je.value;
	
		//34
	
	var xxwl_je = document.forms[0].xxwl_je.value;

		if(xxwl_je==null||xxwl_je=="")
	   {
		  xxwl_je = 0.00;
		  
	    }
		//35
	
	var gxqy_je =document.forms[0].gxqy_je.value;
	
	
		if(gxqy_je==null||gxqy_je=="")
	   {
		  gxqy_je = 0.00;
		  
	    }
		//36
	
	var mzzz_je =document.forms[0].mzzz_je.value;
	
	
		if(mzzz_je==null||mzzz_je=="")
	   {
		  mzzz_je = 0.00;
		  
	    }
		//37
	
	var gdqyh_je =document.forms[0].gdqyh_je.value;
	
	   if(gdqyh_je==null||gdqyh_je=="")
	   {
		  gdqyh_je = 0.00;
		  
	    }

		//38
	
	var qt_je =document.forms[0].qt_je.value;



		 if(qt_je==null||qt_je=="")
	   {
		  qt_je = 0.00;
		  
	    }	
		var bjz =  parseFloat(xxwl_je)+parseFloat(gxqy_je)+parseFloat(mzzz_je)+parseFloat(gdqyh_je)+parseFloat(qt_je);

  document.forms[0].jmshj_je.value = Math.round(bjz*100)/100;
   if(!isNumber(bjz))
   {
      document.forms[0].jmshj_je.value="";
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
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(getCellObject() , null, 9999999999999, null, 16, 2)){
			return false;			
		}
		//��ʽ������
		formate(obj);
}
function checkNumZsInput(obj)
{
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(getCellObject() , null, 9999999999999, null, 16, 0)){
			return false;	
		}
		//��ʽ������
		formateNumZs(obj);
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
 function formateNumZs(obj){
 	var tempNum=obj.value;
 	var num= tempNum;
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
<form name="hdzssdsnbFbForm" action="hdzsqynbfb.dc">
<input name="actionType" type="hidden" id="actionType" value="Show">
	<table width="96%" align="center" cellspacing="0" class="table-99" onKeyDown="jsjdmQuery()">
		<tr>
		<td align="center" valign="top" colspan="7">
        <jsp:include page="../../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="˰���Ż���ϸ��" />
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