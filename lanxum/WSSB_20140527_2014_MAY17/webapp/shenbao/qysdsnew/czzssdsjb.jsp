<%@ page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%String static_contextpath = com.ttsoft.common.util.ResourceLocator
					.getStaticFilePath(request);
%>

<html>
	<head>
		<title>
			����������ҵ����˰������˰�걨��
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
var InputName=['lrze','nstzzje','nstzjse','mbyqndks','ynssde','sysl','ynsdse','jmsdse','hznscyqyjdyjbl','sjyyjdsdse','ybdsdse'];
var InputNameAl=['�����ܶ�','�ӣ���˰�������Ӷ�','������˰�������ٶ�','�����ֲ���ǰ��ȿ�����','Ӧ��˰���ö�','����˰��','Ӧ������˰��','��������˰��','������˰��Ա��ҵ�͵�Ԥ�ɱ���','ʵ����Ԥ�ɵ�����˰��','Ӧ�����ˣ�������˰��'];
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xmlDoc;

//����xml
function parseXmlOnLoad()
{
	var urlXSL="/XSLTWEB/model/010025/XSLT/" +strXSLTVersion+".xsl";
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

	document.forms[0].sysl.className='text-gray';
	
	/*���м����ʸ�ʹ�ڰ��п��ã����򲻾߱������ʸ������εڰ��в�����༭*/
	if(document.forms[0].jmzg.value=='1'){
		//do nothing,�ڰ���Ĭ��Ϊ�ɱ༭	
	}else{
		document.forms[0].jmsdse.readOnly=true;
		document.forms[0].jmsdse.className='text-gray';
	}
	
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
	//�˶���Ϣ
	applendElement("/taxdoc","hdxx",["jmzg","ybjmsl","qyzslx","cyl","xzqy","dezsse"]);
	//�걨��Ϣ
	applendElement("/taxdoc","sbxx",["fsdm","jd","nd","sbrq","skssksrq","skssjsrq"]);
	//�걨����
	applendElement("/taxdoc","sbsj",["lrze","nstzzje","nstzjse","mbyqndks","ynssde","sysl","ynsdse","jmsdse","hznscyqyjdyjbl","sjyyjdsdse","ybdsdse"]);
	
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
  			//changeLocalYwczlx("3");
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
	for(i=1;i<InputName.length;i++){
		var item=document.getElementById(InputName[i]);
		if( (item.value)=="" || (item.value)==null ){				
				document.getElementById(InputName[i]).value='0.00';
		} 
	}
	
		//�жϷǸ���
	for(i=1;i<5;i++){
		var item=document.getElementById(InputName[i]);
		if( parseFloat(item.value)<0 ){
			alert( "'" + InputNameAl[i] + "'ӦΪ�Ǹ���");
			document.getElementById(InputName[i]).focus();
			document.getElementById(InputName[i]).select();
			return false;
		} 
	}
	
			//�жϷǸ���
	for(i=6;i<10;i++){
		var item=document.getElementById(InputName[i]);
		if( parseFloat(item.value)<0 ){
			alert( "'" + InputNameAl[i] + "'ӦΪ�Ǹ���");
			document.getElementById(InputName[i]).focus();
			document.getElementById(InputName[i]).select();
			return false;
		} 
	}
	
	
	var lrze = document.forms[0].lrze.value;       //1h
	var	nstzzje = document.forms[0].nstzzje.value;    //2h
	var	nstzjse = document.forms[0].nstzjse.value;       //3h
	var	mbyqndks = document.forms[0].mbyqndks.value;        //4h
	var	ynssde = document.forms[0].ynssde.value;        //5h
	var	sysl = document.forms[0].sysl.value;          //6h
	var	ynsdse = document.forms[0].ynsdse.value;          //7h
	var	jmsdse = document.forms[0].jmsdse.value;            //8h
	var	hznscyqyjdyjbl = document.forms[0].hznscyqyjdyjbl.value;     //9h
	var	sjyyjdsdse = document.forms[0].sjyyjdsdse.value;      //10h
	var	ybdsdse = document.forms[0].ybdsdse.value;       //11h
	
	
		//��֤��4h  H4<=H1+H2-H3
	var  mbyqndksValue = parseFloat(lrze) +  parseFloat(nstzzje) - parseFloat(nstzjse);
	if( mbyqndksValue < 0 ){
		if( parseFloat(document.forms[0].mbyqndks.value) != 0 ){
			alert( "��ʽ ���'H1+H2-H3<0'ʱ,��H4=0 ������H4="+document.forms[0].mbyqndks.value+" H1+H2-H3=" + Math.round(mbyqndksValue*100)/100 + " ���޸�");
			document.forms[0].mbyqndks.focus();
			document.forms[0].mbyqndks.select();
			return false;
		}
	} else {
		if( parseFloat(document.forms[0].mbyqndks.value) > Math.round(mbyqndksValue*100)/100 ){
			alert( "��ʽ'0��H4��H1+H2-H3',������H4="+document.forms[0].mbyqndks.value+" H1+H2-H3=" + Math.round(mbyqndksValue*100)/100 + " ���޸�");
			document.forms[0].mbyqndks.focus();
			document.forms[0].mbyqndks.select();
			return false;
		}
	}
	
	
		//��֤��5h H5=H1+H2-H3-H4
	var ynssdeValue = parseFloat(lrze) +  parseFloat(nstzzje) - parseFloat(nstzjse) - parseFloat(mbyqndks);
	
	if( ynssdeValue < 0 ){
		if( document.forms[0].ynssde.value != 0 ){
			alert("��ʽ ���H1+H2-H3-H4<0����H5=0 ������H5=" + document.forms[0].ynssde.value + " H1+H2-H3-H4=" + Math.round(ynssdeValue*100)/100 + " ���޸�");
			document.forms[0].ynssde.focus();
			document.forms[0].ynssde.select();
			return false;
		}
	} else {
		if( parseFloat(document.forms[0].ynssde.value) != Math.round(ynssdeValue*100)/100 ){
			alert( "��ʽ H5=H1+H2-H3-H4 ������H5=" + document.forms[0].ynssde.value + " H1+H2-H3-H4=" + Math.round(ynssdeValue*100)/100 + " ���޸�");
			document.forms[0].ynssde.focus();
			document.forms[0].ynssde.select();
			return false;
		}	
	
	
	}
	
	
	
	
		//��֤��7h H7=H6*H6
	var ynsdseValue = parseFloat(ynssde) * parseFloat(sysl);
	if( ynsdseValue < 0 ){
		if( document.forms[0].ynsdse.value != 0 ){
			alert("��ʽ ���H5��H6<0 �� H7=0 ������H7=" + document.forms[0].ynsdse.value + " H5��H6=" + Math.round(ynsdseValue*100)/100 + " ���޸�");
			document.forms[0].ynsdse.focus();
			document.forms[0].ynsdse.select();
			return false;
		}
	} else {
		if( parseFloat(document.forms[0].ynsdse.value) != Math.round(ynsdseValue*100)/100 ){
			alert("��ʽ H7=H5��H6 ������H7=" + document.forms[0].ynsdse.value + " H5��H6=" + Math.round(ynsdseValue*100)/100 + " ���޸�");
			document.forms[0].ynsdse.focus();
			document.forms[0].ynsdse.select();
			return false;
		}
	}
	
	
	
		//��֤��8h
	if( document.forms[0].ynsdse.value == 0 ){
		if( document.forms[0].jmsdse.value != 0 ){
			alert("��ʽ ���H7=0����H8=0 ������H7=" + document.forms[0].ynsdse.value +" H8=" + document.forms[0].jmsdse.value);
			document.forms[0].jmsdse.focus();
			document.forms[0].jmsdse.select();
			return false;
		}
	} else if( document.forms[0].ynsdse.value != 0){
		if( parseFloat(document.forms[0].jmsdse.value) > parseFloat(document.forms[0].ynsdse.value) ){
			alert("��ʽ'H8��H7'��������H7=" + document.forms[0].ynsdse.value +" H8=" + document.forms[0].jmsdse.value);
			document.forms[0].jmsdse.focus();
			document.forms[0].jmsdse.select();
			return false;
		}
	}
	
		//��֤9h
	var hznscyqyjdyjblValue = parseFloat(hznscyqyjdyjbl);
	if( hznscyqyjdyjblValue<0 || hznscyqyjdyjblValue>1 ){
		alert("���ݹ�ʽ'0=<X=<1'��H9��ֵӦΪ���ڵ���0С�ڵ���1");
		document.forms[0].hznscyqyjdyjbl.focus();
		document.forms[0].hznscyqyjdyjbl.select();
		return false;
	}
	
	
		//��֤11h
	var ybdsdseJdns = parseFloat(ynsdse) - parseFloat(jmsdse) - parseFloat(sjyyjdsdse);
	var ybdsdseHzns = (parseFloat(ynsdse) - parseFloat(jmsdse)) * parseFloat(hznscyqyjdyjbl) - parseFloat(sjyyjdsdse);
	if(document.forms[0].hznscyqyjdyjbl.value == 0){
		/*if( ybdsdseJdns < 0 ){
			if( document.forms[0].ybdsdse.value != 0 ){
				alert("��ʽ ���H7-H8-H10<0����H11=0 ������H11=" + document.forms[0].ybdsdse.value + " H7-H8-H10=" + Math.round(ybdsdseJdns*100)/100 + " ���޸�");
				document.forms[0].ybdsdse.focus();
				document.forms[0].ybdsdse.select();
				return false;
			}
		} else {*/
			if( parseFloat(document.forms[0].ybdsdse.value) != Math.round(ybdsdseJdns*100)/100 ){
				alert("��ʽ H11=H7-H8-H10 ������H11=" + document.forms[0].ybdsdse.value + " H7-H8-H10=" + Math.round(ybdsdseJdns*100)/100 + " ���޸�");
				document.forms[0].ybdsdse.focus();
				document.forms[0].ybdsdse.select();
				return false;
			}
		//}
	} else {
		/*if( ybdsdseHzns < 0 ){
			if( document.forms[0].ybdsdse.value != 0) {
				alert("��ʽ �����H7-H8����H9-H10<0����H11=0 ������H11=" + document.forms[0].ybdsdse.value + " ��H7-H8����H9-H10=" + Math.round(ybdsdseHzns*100)/100 + " ���޸�");
				document.forms[0].ybdsdse.focus();
				document.forms[0].ybdsdse.select();
				return false;
			}
		} else {*/
			if( parseFloat(document.forms[0].ybdsdse.value) != Math.round(ybdsdseHzns*100)/100 ){
				alert("��ʽ H11=��H7-H8����H9-H10 ������H11=" + document.forms[0].ybdsdse.value + " ��H7-H8����H9-H10=" + Math.round(ybdsdseHzns*100)/100 + " ���޸�");
				document.forms[0].ybdsdse.focus();
				document.forms[0].ybdsdse.select();
				return false;
			}
		//}			
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

//˰�� �Զ�����
function computeSl(obj){
	
	var sysl;
    
    if(document.forms[0].ynssde.value <= 30000.00){
       document.forms[0].sysl.value = 0.18;
    }
    else if(document.forms[0].ynssde.value  > 30000.00  &&  document.forms[0].ynssde.value  <= 100000.00){
        document.forms[0].sysl.value = 0.27;
    }
    else{
        document.forms[0].sysl.value = 0.33;
    }
    
    
    /*//���¼�����ҵ����˰��		
	
	var qyzslx = document.forms[0].qyzslx.value;
	
	if(qyzslx == 5 ||qyzslx == 1){
	//���¼�����ҵ
		document.forms[0].sysl.value = 0.15;	
	}
	*/
}

</script>

		<link href="<%=static_contextpath%>/css/text.css" rel="stylesheet" type="text/css">

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

		<form name="wrkcxForm" action="czzsqyjb.dc">
			<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="6b35a7b97d7fa2ddc8df56c592663bf5">
			<input name="actionType" type="hidden" id="actionType" value="Show">

			<table width="96%" align="center" cellspacing="0" class="table-99" onKeyDown="jsjdmQuery()">
				<tr>
					<td align="center" valign="top" colspan="7">
						<jsp:include page="../../include/SbHeader.jsp" flush="true">
							<jsp:param name="name" value="����������ҵ����˰������˰�걨��" />
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
