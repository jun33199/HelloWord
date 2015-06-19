<%@ page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%String static_contextpath = com.ttsoft.common.util.ResourceLocator
					.getStaticFilePath(request);

			%>
<html>
	<head>
		<title>
			�˶�������ҵ����˰������˰�걨��
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
var InputName=['srze_bqs','srze_ljs','yssdl_bqs','yssdl_ljs','ynssde_bqs','ynssde_ljs','sysl_bqs','sysl_ljs','yjsdse_bqs','yjsdse_ljs','sjyyjdsdse_bqs','sjyyjdsdse_ljs','ybdsdse_bqs','ybdsdse_ljs'];
var InputNameAl=['�����ܶ����','�����ܶ��ۼ���','Ӧ˰�����ʱ�����','Ӧ˰�������ۼ���','Ӧ��˰���ö����','Ӧ��˰���ö��ۼ���','����˰�ʱ�����','����˰���ۼ���','Ӧ������˰�����','Ӧ������˰���ۼ���','����ʵ����Ԥ�ɵ�����˰�����','����ʵ����Ԥ�ɵ�����˰���ۼ���','Ӧ�����ˣ�������˰�����','Ӧ�����ˣ�������˰���ۼ���'];
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xmlDoc;

//����xml
function parseXmlOnLoad()
{
	
	var urlXSL="/XSLTWEB/model/010023/XSLT/" +strXSLTVersion+".xsl";
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
	//����
	if(document.forms[0].qyzslx.value == 2 || document.forms[0].qyzslx.value == 5 ){
		document.forms[0].yssdl_bqs.value = Math.round(cylValue*100)/100 ;
		document.forms[0].yssdl_ljs.value = Math.round(cylValue*100)/100 ;
		
		document.forms[0].yssdl_bqs.readOnly=true;
		document.forms[0].yssdl_ljs.readOnly=true;
		document.forms[0].sysl_bqs.readOnly=true;
		document.forms[0].sysl_ljs.readOnly=true;
		
		document.forms[0].yssdl_bqs.className='text-gray';
		document.forms[0].yssdl_ljs.className='text-gray';		
		document.forms[0].sysl_bqs.className='text-gray';
		document.forms[0].sysl_ljs.className='text-gray';
		
	}
	//���� 
	else if(document.forms[0].qyzslx.value != 2 || document.forms[0].qyzslx.value != 5 ){
	
		document.forms[0].sysl_bqs.value = Math.round(dezsseValue*100)/100;
		document.forms[0].sysl_ljs.value = Math.round(dezsseValue*100)/100 ;
	
		document.forms[0].srze_bqs.readOnly=true;
		document.forms[0].srze_ljs.readOnly=true;
		document.forms[0].yssdl_bqs.readOnly=true;
		document.forms[0].yssdl_ljs.readOnly=true;
		document.forms[0].sysl_bqs.readOnly=true;
		document.forms[0].sysl_ljs.readOnly=true;
		
		document.forms[0].srze_bqs.className='text-gray';
		document.forms[0].srze_ljs.className='text-gray';
		document.forms[0].yssdl_bqs.className='text-gray';
		document.forms[0].yssdl_ljs.className='text-gray';
		document.forms[0].sysl_bqs.className='text-gray';
		document.forms[0].sysl_ljs.className='text-gray';

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
	applendElement("/taxdoc","sbsj",["sbrqshow","srze_bqs","srze_ljs","yssdl_bqs","yssdl_ljs","ynssde_bqs","ynssde_ljs","sysl_bqs","sysl_ljs","yjsdse_bqs","yjsdse_ljs","sjyyjdsdse_bqs","sjyyjdsdse_ljs","ybdsdse_bqs","ybdsdse_ljs"]);
	
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
	if(event.keyCode==13) 
		event.keyCode = 9;
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
		item.value="0.00";
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
	for(i=0;i<InputName.length;i++){
		var item=document.getElementById(InputName[i]);
		if( (item.value)=="" || (item.value)==null ){			
			document.getElementById(InputName[i]).value='0.00';
		} 
	}
	
	
		//�жϷǸ���
	for(i=4;i<12;i++){
		var item=document.getElementById(InputName[i]);
		if( item.value<0 ){
			alert( "'" + InputNameAl[i] + "'ӦΪ�Ǹ���");
			document.getElementById(InputName[i]).focus();
			return false;
		} 
	}
	
		
	
		//�жϡ������ܶ�ۼ������ڵ��ڱ�����
	if(parseFloat(document.forms[0].srze_ljs.value) < parseFloat(document.forms[0].srze_bqs.value) ){
		alert( "'�����ܶ�'�ۼ���Ӧ���ڵ��ڱ�����" );
		document.forms[0].srze_ljs.focus();
		document.forms[0].srze_ljs.select();
		return false;
	}
	
	
		//�ж�4h=1h*3h Ӧ��˰���ö�
	var ynssdeBqs = parseFloat(document.forms[0].srze_bqs.value) * parseFloat(document.forms[0].cyl.value);
	var ynssdeLjs = parseFloat(document.forms[0].srze_ljs.value) * parseFloat(document.forms[0].cyl.value);
	var qyzslx;
	
	if(parseFloat(document.forms[0].qyzslx.value) == 2 || parseFloat(document.forms[0].qyzslx.value) == 5 ){
	
		if(ynssdeBqs<0){
			if(parseFloat(document.forms[0].ynssde_bqs.value) != 0){			
				alert("��ʽ ��H1��H3<0 ʱ H4=0 �����б�����H4=" + document.forms[0].ynssde_bqs.value + " H1��H3=" + Math.round(ynssdeBqs*100)/100 + " ���޸�");
				document.forms[0].ynssde_bqs.focus();
				document.forms[0].ynssde_bqs.select();
				return false;			
			}
		}else{
			if(parseFloat(document.forms[0].ynssde_bqs.value) != Math.round(ynssdeBqs*100)/100 ){
				alert("��ʽ H4=H1��H3 �����б�����H4=" + document.forms[0].ynssde_bqs.value + " H1��H3=" + Math.round(ynssdeBqs*100)/100 + " ���޸�");
				document.forms[0].ynssde_bqs.focus();
				document.forms[0].ynssde_bqs.select();
				return false;
			} 
			
		}
		
		if(ynssdeLjs<0){
			if(parseFloat(document.forms[0].ynssde_ljs.value) != 0){
				alert("��ʽ ��H1��H3<0 ʱ H4=0 �������ۼ���H4=" + document.forms[0].ynssde_ljs.value + " H1��H3=" + Math.round(ynssdeLjs*100)/100 + " ���޸�");
				document.forms[0].ynssde_ljs.focus();
				document.forms[0].ynssde_ljs.select();
				return false;			
			}
		
		}else{		
			 if (parseFloat(document.forms[0].ynssde_ljs.value) != Math.round(ynssdeLjs*100)/100 ){
				alert("��ʽ H4=H1��H3 �������ۼ���H4=" + document.forms[0].ynssde_ljs.value + " H1��H3=" + Math.round(ynssdeLjs*100)/100 + " ���޸�");
				document.forms[0].ynssde_ljs.focus();
				document.forms[0].ynssde_ljs.select();
				return false;
			}
		}
	}
	
	
		//�ж�6h=4h*5h Ӧ������˰��
	var yjsdseBqs = parseFloat(document.forms[0].ynssde_bqs.value) * parseFloat(document.forms[0].sysl_bqs.value);
	var yjsdseLjs = parseFloat(document.forms[0].ynssde_ljs.value) * parseFloat(document.forms[0].sysl_ljs.value);
	
	
	if( yjsdseBqs < 0 ){
		if( document.forms[0].yjsdse_bqs.value != 0 ){
			alert("��ʽ ���H4��H5<0 �� H6=0 �����б�����H6=" + document.forms[0].yjsdse_bqs.value + " H4��H5=" + Math.round(yjsdseBqs*100)/100 + " ���޸�");
			document.forms[0].yjsdse_bqs.focus();
			document.forms[0].yjsdse_bqs.select();
			return false;
		}
	} else {
		if( parseFloat(document.forms[0].yjsdse_bqs.value) != Math.round(yjsdseBqs*100)/100  ){
			alert("��ʽ H6=H4��H5 �����б�����H6=" + document.forms[0].yjsdse_bqs.value + " H4��H5=" + Math.round(yjsdseBqs*100)/100 + " ���޸�");
			document.forms[0].yjsdse_bqs.focus();
			document.forms[0].yjsdse_bqs.select();
			return false;
		}
	}
	
	if( yjsdseLjs < 0 ){
		if( document.forms[0].yjsdse_ljs.value != 0 ){
			alert("��ʽ ���H4��H5<0 �� H6=0 �����б�����H6=" + document.forms[0].yjsdse_ljs.value + " H4��H5=" + Math.round(yjsdseLjs*100)/100 + " ���޸�");
			document.forms[0].yjsdse_ljs.focus();
			document.forms[0].yjsdse_ljs.select();
			return false;
		}
	} else {
		if( parseFloat(document.forms[0].yjsdse_ljs.value) != Math.round(yjsdseLjs*100)/100  ){
			alert("��ʽ H6=H4��H5 �������ۼ���H6=" + document.forms[0].yjsdse_ljs.value + " H4��H5=" + Math.round(yjsdseLjs*100)/100 + " ���޸�");
			document.forms[0].yjsdse_ljs.focus();
			document.forms[0].yjsdse_ljs.select();
			return false;
		}
	}	
		
		
			//�ж�8h=6h-7h Ӧ�����ˣ�������˰��
	var ybdsdseBqs = parseFloat(document.forms[0].yjsdse_bqs.value) - parseFloat(document.forms[0].sjyyjdsdse_bqs.value);
	var ybdsdseLjs = parseFloat(document.forms[0].yjsdse_ljs.value) - parseFloat(document.forms[0].sjyyjdsdse_ljs.value);


	/*if( ybdsdseBqs < 0 ){
		if( document.forms[0].ybdsdse_bqs.value != 0 ){
			alert("��ʽ ��'������H6-H7<0'ʱ��������H8=0 �����б�����H8=" + document.forms[0].ybdsdse_bqs.value + " H6-H7=" + Math.round(ybdsdseBqs*100)/100 + " ���޸�");
			document.forms[0].ybdsdse_bqs.focus();
			document.forms[0].ybdsdse_bqs.select();
			return false;
		}
	} else {*/
		if( parseFloat(document.forms[0].ybdsdse_bqs.value) != Math.round(ybdsdseBqs*100)/100  ){
			alert("��ʽ H8=H6-H7 �����б�����H8=" + document.forms[0].ybdsdse_bqs.value + " H6-H7=" + Math.round(ybdsdseBqs*100)/100 + " ���޸�");
			document.forms[0].ybdsdse_bqs.focus();
			document.forms[0].ybdsdse_bqs.select();
			return false;
		}
	//}	
	
	/*if( ybdsdseLjs < 0 ){
		if( document.forms[0].ybdsdse_ljs.value != 0 ){
			alert("��ʽ ��'�ۼ���H6-H7<0'ʱ���ۼ���H8=0 �������ۼ���H8=" + document.forms[0].ybdsdse_ljs.value + " H6-H7=" + Math.round(ybdsdseLjs*100)/100 + " ���޸�");
			document.forms[0].ybdsdse_ljs.focus();
			document.forms[0].ybdsdse_ljs.select();
			return false;
		}
	} else {*/
		if( parseFloat(document.forms[0].ybdsdse_ljs.value) != Math.round(ybdsdseLjs*100)/100  ){
			alert("��ʽ H8=H6-H7 �������ۼ���H8=" + document.forms[0].ybdsdse_ljs.value + " H6-H7=" + Math.round(ybdsdseLjs*100)/100 + " ���޸�");
			document.forms[0].ybdsdse_ljs.focus();
			document.forms[0].ybdsdse_ljs.select();
			return false;
		}
	//}

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

//˰�� �Զ�����
function computeSl(obj){
	//����
	if(document.forms[0].qyzslx.value == 2 || document.forms[0].qyzslx.value == 5 ){
	
		var sysl_bqs;
	    var sysl_ljs;
	    
		if( obj == eval("document.forms[0].ynssde_bqs") ){
		    if(document.forms[0].ynssde_bqs.value <= 30000.00){
		       document.forms[0].sysl_bqs.value = 0.18;
		    }
		    else if(document.forms[0].ynssde_bqs.value  > 30000.00  &&  document.forms[0].ynssde_bqs.value  <= 100000.00){
		        document.forms[0].sysl_bqs.value = 0.27;
		    }
		    else{
		        document.forms[0].sysl_bqs.value = 0.33;
		    }
		}else if( obj == eval("document.forms[0].ynssde_ljs") ){		    
		    if(document.forms[0].ynssde_ljs.value <= 30000.00){
		       document.forms[0].sysl_ljs.value = 0.18;
		    }
		    else if(document.forms[0].ynssde_ljs.value  > 30000.00  &&  document.forms[0].ynssde_ljs.value  <= 100000.00){
		        document.forms[0].sysl_ljs.value = 0.27;
		    }
		    else{
		        document.forms[0].sysl_ljs.value = 0.33;
		    }
		}
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

		<form name="form1" method="post" action="hdzsqyjb.dc">
			<input name="actionType" type="hidden" id="actionType" value="Show">
			<table width="96%" align="center" cellspacing="0" class="table-99" onKeyDown="jsjdmQuery()">
				<tr>
					<td align="center" valign="top" colspan="7">
						<jsp:include page="../../include/SbHeader.jsp" flush="true">
							<jsp:param name="name" value="�˶�������ҵ����˰������˰�걨��" />
							<jsp:param name="help_url" value="help/wssb/sbzl/lygfzwhztz/qysdsjd/qysdsjd-000.htm" />
						</jsp:include>
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<br>
						<div id="result" align="center"></div>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="7">
						<div align="center">
							<table>
								<TR class="black9">
									<TD>
										<a style="cursor:hand" tabIndex="-1" onClick="javascript:return doSubmit('Save');return false;">
											<img name="spage" value="����" alt="����" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>/images/baocun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/baocun1.jpg" width="79" height="22" id="baocun">
										</a>
										<a style="cursor:hand" tabIndex="-1" onClick="javascript:return doSubmit('Delete');return false;">
											<img name="dpage" value="ɾ��" alt="ɾ��" onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>/images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/shanchu1.jpg" width="79" height="22" id="shanchu">
										</a>
										<a style="cursor:hand" tabIndex="-1" onClick="javascript:return doSubmit('Reset');return false;">
											<img name="qpage" value="���" alt="���" onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>/images/qingchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/qingchu1.jpg" width="79" height="22" id="qingchu">
										</a>
										<a style="cursor:hand" tabIndex="-1" onClick="javascript:return doSubmit('Return');return false;">
											<img name="bpage" value="����" alt="����" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/fanhui1.jpg" width="79" height="22" id="fanhui">
										</a>

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
