<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ page import="com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2012.QysdsNbConstant"%>
<%@ page import="com.ttsoft.bjtax.shenbao.constant.CodeConstant"%>
<%@ page import="com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement.CheckJdlx"%>
<%
    String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
	System.out.println("======================123=============================");
%>
<html>
<head>
<title>��ҵ����˰��֧���������˰�걨��</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type = "text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="gojs/XmlBuild.js"></script>

<script language="JavaScript">
//alert("javascript===============");

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
//alert("strXml==="+strXml);
var InputName=['nsfs', 'zfjg', 
			   'yysr', 'yycb', 'lrze', 'tdjsynssde', 'bzsr', 'mssr', 'mbyqndks', 'sjlre', 'sl', 'ynsdse', 
			   'jmsdse', 'sjyyjsdse', 'tdywyjsdse', 'ybtsdse', 'yqnddjsdse', 'bqsjybtsdse', 
			   'zjgyftsdse', 'czjzfpsdse', 'fzjgyftsdse', 'zjgdlscjybmyftsdse', 'zjgycxfzjgyftsdse', 'fpbl', 'fpsdse',,'zczbje','zcze'];
var InputNameAl=['��˰��ʽ','�ֻܷ���',
				 'Ӫҵ����','Ӫҵ�ɱ�', '�����ܶ�', '�ӣ��ض�ҵ������Ӧ��˰���ö�','��������˰����', '��˰����', '�ֲ���ǰ��ȿ���', 'ʵ�������', '˰��','Ӧ������˰��', 
				 '������������˰��', '����ʵ����Ԥ������˰��', '�����ض�ҵ��Ԥ�ɣ���������˰��', 'Ӧ�����ˣ�����˰��', '������ǰ��ȶ���ڱ��ڵֽ�����˰��', '����ʵ��Ӧ�����ˣ�����˰��', 
				 '�ܻ���Ӧ��̯����˰��', '�������з�������˰��', '��֧����Ӧ��̯����˰��', '���У��ܻ�������������Ӫ����Ӧ��̯����˰��', '�ܻ����ѳ�����֧����Ӧ��̯����˰��', '�������', '��������˰��','ע���ʱ�(��Ԫ��������)','�ʲ��ܶ�(��Ԫ��������)'];
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xmlDoc;

//alert("strXSLTVersion==="+strXSLTVersion);
//����xml
function parseXmlOnLoad()
{
	//alert("parseXmlOnLoad");
	var urlXSL="/XSLTWEB/model/010033/XSLT/" +strXSLTVersion+".xsl";
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
	applendElement("/taxdoc","sbxx",["fsdm","jd","nd","sbrq","skssksrq","skssjsrq","zgfwjdlx"]);
	//�걨����
	applendElement("/taxdoc","sbsj",["nsfs", "zfjg", "yysr", "yycb", "lrze", "tdjsynssde", "bzsr", "mssr", "mbyqndks", "sjlre", "sl", "ynsdse", "jmsdse", "sjyyjsdse", "tdywyjsdse", "ybtsdse", "yqnddjsdse", "bqsjybtsdse", "zjgyftsdse", "czjzfpsdse", "fzjgyftsdse", "zjgdlscjybmyftsdse", "zjgycxfzjgyftsdse", "fpbl", "fpsdse","zczbje","zcze","queryFlag"]);
	
	//ȥ��ĩβ�Զ���ӵĻس�
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	return retstr;
}


function setInput(){
	//alert("1");
	//��ʼ��RADIO��
	initSelect();
	//alert("2");
	//��ʼ��RADIO��ɷ�����
	changeSelect();
	//alert("3");
	//��ʼ���д�10˰��
	document.forms[0].sl.value="25.00";
	//alert("4");
	//��ʼ������ʹ����
	checkFilter();
	//alert("5");

	//����Ƿ񱣴�ɹ����������ɹ�������ת
	<%
		if("1".equals((String)request.getAttribute(QysdsNbConstant.JUMP_FLAG_NAME))){
	%>
		//if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
		if(document.forms[0].lje_nsfs[0].checked==1){
			//����ɹ�����ʾת�ֻܷ����걨��
			//if(confirm("����ɹ����Ƿ�ת�롶��ҵ����˰������˰��֧�����������")){
				alert("����ɹ�����ת�롶��ҵ����˰������˰��֧���������");
				doSubmit("Jump");
			//}
		}else if(document.forms[0].lje_nsfs[1].checked==1){
			alert("�걨�ѳɹ���");
		}
		
	<%
	}
	%>
	
}

	<%/*���ݳ�ʼ��������Ϊ��ѡ��ֵ����ʼ��������*/%>
	function initSelect(){
		//alert("initSelect...");
		//alert("document.forms[0].nsfs.value="+document.forms[0].nsfs.value);
		//alert("document.forms[0].sybs.value="+document.forms[0].sybs.value);

		//if(document.forms[0].sybs.value==<%=CodeConstant.CODE_QYSDS_SYBS_D%>){
		//	document.forms[0].lje_nsfs[1].checked=1;
		//}else if(document.forms[0].sybs.value==<%=CodeConstant.CODE_QYSDS_SYBS_Z%>){
		//	document.forms[0].lje_nsfs[0].checked=1;
		//	document.forms[0].lje_zfjg[0].checked=1;
		//}else if(document.forms[0].sybs.value==<%=CodeConstant.CODE_QYSDS_SYBS_F%>){
		//	document.forms[0].lje_nsfs[0].checked=1;
		//	document.forms[0].lje_zfjg[1].checked=1;
		//}
		
		//��ȡ��������
		var jdlx = document.forms[0].zgfwjdlx.value;
		if(jdlx==<%=CheckJdlx.QYSDSZGFWJDLX_DLNSR%> || jdlx==<%=CheckJdlx.QYSDSZGFWJDLX_ZFJGJZBSZJGNSR%>)
		{
			document.forms[0].lje_nsfs[1].checked=1;
			document.forms[0].lje_nsfs[1].disabled=false;
		}
		
		if(jdlx==<%=CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR%>)
		{
			document.forms[0].lje_nsfs[0].checked = true;
			document.forms[0].lje_nsfs[0].disabled=false;
			
			document.forms[0].lje_zfjg[1].checked = true;
			document.forms[0].lje_zfjg[1].disabled=false;
		}
		
		
		if(jdlx==<%=CheckJdlx.QYSDSZGFWJDLX_KSSZJGNSR%>)
		{
			document.forms[0].lje_nsfs[0].checked = true;
			document.forms[0].lje_nsfs[0].disabled=false;
			
			document.forms[0].lje_zfjg[0].checked = true;
			document.forms[0].lje_zfjg[0].disabled=false;
		}
		
		
		//alert("3");
		document.forms[0].nsfs.value="0.00";
		document.forms[0].zfjg.value="";
	}

	<%/*����ѡ�����õ�ѡ��ѡ�����*/%>
	function changeSelect()
	{
		//alert("changeSelect...");
		//alert("document.forms[0].lje_nsfs[0].value=="+document.forms[0].lje_nsfs[0].value);
		//alert("document.forms[0].lje_nsfs[1].value=="+document.forms[0].lje_nsfs[1].value);
		if(document.forms[0].lje_nsfs[0].checked==1){
			document.forms[0].nsfs.value=document.forms[0].lje_nsfs[0].value;
		}else if(document.forms[0].lje_nsfs[1].checked==1){
			document.forms[0].nsfs.value=document.forms[0].lje_nsfs[1].value;
		}

		if(document.forms[0].lje_nsfs[1].checked == 1)
		{
			//ѡ��Ϊ������˰�ģ����������˰��ʽ����ѡ��ͬʱ����Щ������λ���ɱ༭
			document.forms[0].lje_zfjg[0].checked = false;
			document.forms[0].lje_zfjg[0].disabled = true;
			document.forms[0].lje_zfjg[1].checked = false;
			document.forms[0].lje_zfjg[1].disabled = true;
			document.forms[0].lje_zfjg.disabled = true;
			//
			document.forms[0].zfjg.value="";
		} else if(document.forms[0].lje_nsfs[0].checked == 1){
			document.forms[0].lje_zfjg.disabled = true;
			if(document.forms[0].lje_zfjg[0].checked==0&&document.forms[0].lje_zfjg[1].checked==0){
				document.forms[0].lje_zfjg[0].checked = true;
				document.forms[0].lje_zfjg[1].checked = false;
			}
			//ѡ��Ϊ�ܻ���ʱ������������ѡ��Ȩ��
			document.forms[0].lje_zfjg[0].disabled = true;
			document.forms[0].lje_zfjg[1].disabled = true;
			//
			if(document.forms[0].lje_zfjg[0].checked==1){
				document.forms[0].zfjg.value=document.forms[0].lje_zfjg[0].value;
			}else if(document.forms[0].lje_zfjg[1].checked==1){
				document.forms[0].zfjg.value=document.forms[0].lje_zfjg[1].value;
			}
		}else{
			//Ĭ�������Ϊ������˰
			document.forms[0].lje_nsfs[0].checked=0;
			document.forms[0].lje_nsfs[1].checked=1;
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
		//alert("checkFilter");
		//������˰
		if(document.forms[0].lje_nsfs[1].checked==true)
		{
			document.forms[0].yysr.disabled=false; //2
			document.forms[0].yycb.disabled=false; //3
			document.forms[0].lrze.disabled=false; //4
			document.forms[0].tdjsynssde.disabled=false; //5
			document.forms[0].bzsr.disabled=false; //6
			document.forms[0].mssr.disabled=false; //7
			document.forms[0].mbyqndks.disabled=false; //8
			document.forms[0].sjlre.disabled=false; //9
			document.forms[0].sl.disabled=false; //10
			document.forms[0].ynsdse.disabled=false; //11
			document.forms[0].jmsdse.disabled=false; //12
			document.forms[0].sjyyjsdse.disabled=false;��//13
			document.forms[0].tdywyjsdse.disabled=false; //14
			document.forms[0].ybtsdse.disabled=false;  //15
			document.forms[0].yqnddjsdse.disabled=false; //16
			document.forms[0].bqsjybtsdse.disabled=false; //17
			document.forms[0].zjgyftsdse.disabled=true; //26
			document.forms[0].czjzfpsdse.disabled=true; //27
			document.forms[0].fzjgyftsdse.disabled=true; //28
			document.forms[0].zjgdlscjybmyftsdse.disabled=true; //29
			document.forms[0].zjgycxfzjgyftsdse.disabled=true; //30
			document.forms[0].fpbl.disabled=true; //31
			document.forms[0].fpsdse.disabled=true; //32


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
			if(document.forms[0].tdjsynssde.value == null || document.forms[0].tdjsynssde.value == "")
			{
				document.forms[0].tdjsynssde.value="0.00";
			}
			if(document.forms[0].bzsr.value == null || document.forms[0].bzsr.value == "")
			{
				document.forms[0].bzsr.value="0.00";
			}
			if(document.forms[0].mssr.value == null || document.forms[0].mssr.value == "")
			{
				document.forms[0].mssr.value="0.00";
			}
			if(document.forms[0].mbyqndks.value == null || document.forms[0].mbyqndks.value == "")
			{
				document.forms[0].mbyqndks.value="0.00";
			}
			if(document.forms[0].sjlre.value == null || document.forms[0].sjlre.value == "")
			{
				document.forms[0].sjlre.value="0.00";
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
			if(document.forms[0].sjyyjsdse.value == null || document.forms[0].sjyyjsdse.value == "")
			{
				document.forms[0].sjyyjsdse.value="0.00";
			}
			if(document.forms[0].tdywyjsdse.value == null || document.forms[0].tdywyjsdse.value == "")
			{
				document.forms[0].tdywyjsdse.value="0.00";
			}
			if(document.forms[0].ybtsdse.value == null || document.forms[0].ybtsdse.value == "")
			{
				document.forms[0].ybtsdse.value="0.00";
			}
			if(document.forms[0].yqnddjsdse.value == null || document.forms[0].yqnddjsdse.value == "")
			{
				document.forms[0].yqnddjsdse.value="0.00";
			}
			if(document.forms[0].bqsjybtsdse.value == null || document.forms[0].bqsjybtsdse.value == "")
			{
				document.forms[0].bqsjybtsdse.value="0.00";
			}
			
			document.forms[0].zjgyftsdse.value="";
			document.forms[0].czjzfpsdse.value="";
			document.forms[0].fzjgyftsdse.value="";
			document.forms[0].zjgdlscjybmyftsdse.value="";
			document.forms[0].zjgycxfzjgyftsdse.value="";
			document.forms[0].fpbl.value="";
			document.forms[0].fpsdse.value="";
			
			document.forms[0].yysr.style.backgroundColor=""; //2
			document.forms[0].yycb.style.backgroundColor=""; //3
			document.forms[0].lrze.style.backgroundColor=""; //4
			document.forms[0].tdjsynssde.style.backgroundColor=""; //5
			document.forms[0].bzsr.style.backgroundColor=""; //6
			document.forms[0].mssr.style.backgroundColor=""; //7
			document.forms[0].mbyqndks.style.backgroundColor=""; //8
			document.forms[0].sjlre.style.backgroundColor=""; //9
			document.forms[0].sl.style.backgroundColor=""; //10
			document.forms[0].ynsdse.style.backgroundColor=""; //11
			document.forms[0].jmsdse.style.backgroundColor=""; //12
			document.forms[0].sjyyjsdse.style.backgroundColor="";��//13
			document.forms[0].tdywyjsdse.style.backgroundColor=""; //14
			document.forms[0].ybtsdse.style.backgroundColor="";  //15
			document.forms[0].yqnddjsdse.style.backgroundColor=""; //16
			document.forms[0].bqsjybtsdse.style.backgroundColor=""; //17
			document.forms[0].zjgyftsdse.style.backgroundColor="#aaaaaa"; //26
			document.forms[0].czjzfpsdse.style.backgroundColor="#aaaaaa"; //27
			document.forms[0].fzjgyftsdse.style.backgroundColor="#aaaaaa"; //28
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor="#aaaaaa"; //29
			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa"; //31
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa"; //32

		}
		//������˰���ܻ���
		else if(document.forms[0].lje_nsfs[0].checked==true&&document.forms[0].lje_zfjg[0].checked==true){
			document.forms[0].yysr.disabled=false; //2
			document.forms[0].yycb.disabled=false; //3
			document.forms[0].lrze.disabled=false; //4
			document.forms[0].tdjsynssde.disabled=false; //5
			document.forms[0].bzsr.disabled=false; //6
			document.forms[0].mssr.disabled=false; //7
			document.forms[0].mbyqndks.disabled=false; //8
			document.forms[0].sjlre.disabled=false; //9
			document.forms[0].sl.disabled=false; //10
			document.forms[0].ynsdse.disabled=false; //11
			document.forms[0].jmsdse.disabled=false; //12
			document.forms[0].sjyyjsdse.disabled=false;��//13
			document.forms[0].tdywyjsdse.disabled=false; //14
			document.forms[0].ybtsdse.disabled=false;  //15
			document.forms[0].yqnddjsdse.disabled=false; //16
			document.forms[0].bqsjybtsdse.disabled=false; //17
			document.forms[0].zjgyftsdse.disabled=false; //26
			document.forms[0].czjzfpsdse.disabled=false; //27
			document.forms[0].fzjgyftsdse.disabled=false; //28
			document.forms[0].zjgdlscjybmyftsdse.disabled=false; //29
			document.forms[0].zjgycxfzjgyftsdse.disabled=false; //30
			document.forms[0].fpbl.disabled=true; //31
			document.forms[0].fpsdse.disabled=true; //32

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
			if(document.forms[0].tdjsynssde.value == null || document.forms[0].tdjsynssde.value == "")
			{
				document.forms[0].tdjsynssde.value="0.00";
			}
			if(document.forms[0].bzsr.value == null || document.forms[0].bzsr.value == "")
			{
				document.forms[0].bzsr.value="0.00";
			}
			if(document.forms[0].mssr.value == null || document.forms[0].mssr.value == "")
			{
				document.forms[0].mssr.value="0.00";
			}
			if(document.forms[0].mbyqndks.value == null || document.forms[0].mbyqndks.value == "")
			{
				document.forms[0].mbyqndks.value="0.00";
			}
			if(document.forms[0].sjlre.value == null || document.forms[0].sjlre.value == "")
			{
				document.forms[0].sjlre.value="0.00";
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
			if(document.forms[0].sjyyjsdse.value == null || document.forms[0].sjyyjsdse.value == "")
			{
				document.forms[0].sjyyjsdse.value="0.00";
			}
			if(document.forms[0].tdywyjsdse.value == null || document.forms[0].tdywyjsdse.value == "")
			{
				document.forms[0].tdywyjsdse.value="0.00";
			}
			if(document.forms[0].ybtsdse.value == null || document.forms[0].ybtsdse.value == "")
			{
				document.forms[0].ybtsdse.value="0.00";
			}
			if(document.forms[0].yqnddjsdse.value == null || document.forms[0].yqnddjsdse.value == "")
			{
				document.forms[0].yqnddjsdse.value="0.00";
			}
			if(document.forms[0].bqsjybtsdse.value == null || document.forms[0].bqsjybtsdse.value == "")
			{
				document.forms[0].bqsjybtsdse.value="0.00";
			}
			if(document.forms[0].zjgyftsdse.value == null || document.forms[0].zjgyftsdse.value == "")
			{
				document.forms[0].zjgyftsdse.value="0.00";
			}
			if(document.forms[0].czjzfpsdse.value == null || document.forms[0].czjzfpsdse.value == "")
			{
				document.forms[0].czjzfpsdse.value="0.00";
			}
			if(document.forms[0].fzjgyftsdse.value == null || document.forms[0].fzjgyftsdse.value == "")
			{
				document.forms[0].fzjgyftsdse.value="0.00";
			}			
			if(document.forms[0].zjgdlscjybmyftsdse.value == null || document.forms[0].zjgdlscjybmyftsdse.value == "")
			{
				document.forms[0].zjgdlscjybmyftsdse.value="0.00";
			}
			if(document.forms[0].zjgycxfzjgyftsdse.value == null || document.forms[0].zjgycxfzjgyftsdse.value == "")
			{
				document.forms[0].zjgycxfzjgyftsdse.value="0.00";
			}

			document.forms[0].fpbl.value="";
			document.forms[0].fpsdse.value="";
			
			document.forms[0].yysr.style.backgroundColor=""; //2
			document.forms[0].yycb.style.backgroundColor=""; //3
			document.forms[0].lrze.style.backgroundColor=""; //4
			document.forms[0].tdjsynssde.style.backgroundColor=""; //5
			document.forms[0].bzsr.style.backgroundColor=""; //6
			document.forms[0].mssr.style.backgroundColor=""; //7
			document.forms[0].mbyqndks.style.backgroundColor=""; //8
			document.forms[0].sjlre.style.backgroundColor=""; //9
			document.forms[0].sl.style.backgroundColor=""; //10
			document.forms[0].ynsdse.style.backgroundColor=""; //11
			document.forms[0].jmsdse.style.backgroundColor=""; //12
			document.forms[0].sjyyjsdse.style.backgroundColor="";��//13
			document.forms[0].tdywyjsdse.style.backgroundColor=""; //14
			document.forms[0].ybtsdse.style.backgroundColor="";  //15
			document.forms[0].yqnddjsdse.style.backgroundColor=""; //16
			document.forms[0].bqsjybtsdse.style.backgroundColor=""; //17
			document.forms[0].zjgyftsdse.style.backgroundColor=""; //26
			document.forms[0].czjzfpsdse.style.backgroundColor=""; //27
			document.forms[0].fzjgyftsdse.style.backgroundColor=""; //28
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor=""; //29
			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor=""; //30
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa"; //31
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa"; //32

		}
		//������˰����֧����
		else if(document.forms[0].lje_nsfs[0].checked==true&&document.forms[0].lje_zfjg[1].checked==true){
			//alert("3");
			document.forms[0].yysr.disabled=true; //2
			document.forms[0].yycb.disabled=true; //3
			document.forms[0].lrze.disabled=true; //4
			document.forms[0].tdjsynssde.disabled=true; //5
			document.forms[0].bzsr.disabled=true; //6
			document.forms[0].mssr.disabled=true; //7
			document.forms[0].mbyqndks.disabled=true; //8
			document.forms[0].sjlre.disabled=true; //9
			document.forms[0].sl.disabled=true; //10
			document.forms[0].ynsdse.disabled=true; //11
			document.forms[0].jmsdse.disabled=true; //12
			document.forms[0].sjyyjsdse.disabled=true;��//13
			document.forms[0].tdywyjsdse.disabled=true; //14
			document.forms[0].ybtsdse.disabled=true;  //15
			document.forms[0].yqnddjsdse.disabled=true; //16
			document.forms[0].bqsjybtsdse.disabled=true; //17
			document.forms[0].zjgyftsdse.disabled=true; //26
			document.forms[0].czjzfpsdse.disabled=true; //27
			document.forms[0].fzjgyftsdse.disabled=false; //28
			document.forms[0].zjgdlscjybmyftsdse.disabled=true; //29
			document.forms[0].zjgycxfzjgyftsdse.disabled=true; //30
			document.forms[0].fpbl.disabled=false; //31
			document.forms[0].fpsdse.disabled=false; //32
			
			//���������д�ֵ���
			document.forms[0].yysr.value=""; //2
			document.forms[0].yycb.value=""; //3
			document.forms[0].lrze.value=""; //4
			document.forms[0].tdjsynssde.value=""; //5
			document.forms[0].bzsr.value=""; //6
			document.forms[0].mssr.value=""; //7
			document.forms[0].mbyqndks.value=""; //8
			document.forms[0].sjlre.value=""; //9
			//document.forms[0].sl.value=""; //10
			document.forms[0].ynsdse.value=""; //11
			document.forms[0].jmsdse.value=""; //12
			document.forms[0].sjyyjsdse.value="";��//13
			document.forms[0].tdywyjsdse.value=""; //14
			document.forms[0].ybtsdse.value="";  //15
			document.forms[0].yqnddjsdse.value=""; //16
			document.forms[0].bqsjybtsdse.value=""; //17
			document.forms[0].zjgyftsdse.value=""; //26
			document.forms[0].czjzfpsdse.value=""; //27
			document.forms[0].zjgdlscjybmyftsdse.value=""; //29
			document.forms[0].zjgycxfzjgyftsdse.value=""; //30
			
			if(document.forms[0].fzjgyftsdse.value == null || document.forms[0].fzjgyftsdse.value == "")
			{
				document.forms[0].fzjgyftsdse.value="0.00";
			}
			if(document.forms[0].fpbl.value == null || document.forms[0].fpbl.value == "")
			{
				document.forms[0].fpbl.value="0.00";
			}
			if(document.forms[0].fpsdse.value == null || document.forms[0].fpsdse.value == "")
			{
				document.forms[0].fpsdse.value="0.00";
			}

			document.forms[0].yysr.style.backgroundColor="#aaaaaa"; //2
			document.forms[0].yycb.style.backgroundColor="#aaaaaa"; //3
			document.forms[0].lrze.style.backgroundColor="#aaaaaa"; //4
			document.forms[0].tdjsynssde.style.backgroundColor="#aaaaaa"; //5
			document.forms[0].bzsr.style.backgroundColor="#aaaaaa"; //6
			document.forms[0].mssr.style.backgroundColor="#aaaaaa"; //7
			document.forms[0].mbyqndks.style.backgroundColor="#aaaaaa"; //8
			document.forms[0].sjlre.style.backgroundColor="#aaaaaa"; //9
			document.forms[0].sl.style.backgroundColor="#aaaaaa"; //10
			document.forms[0].ynsdse.style.backgroundColor="#aaaaaa"; //11
			document.forms[0].jmsdse.style.backgroundColor="#aaaaaa"; //12
			document.forms[0].sjyyjsdse.style.backgroundColor="#aaaaaa";��//13
			document.forms[0].tdywyjsdse.style.backgroundColor="#aaaaaa"; //14
			document.forms[0].ybtsdse.style.backgroundColor="#aaaaaa";  //15
			document.forms[0].yqnddjsdse.style.backgroundColor="#aaaaaa"; //16
			document.forms[0].bqsjybtsdse.style.backgroundColor="#aaaaaa"; //17
			document.forms[0].zjgyftsdse.style.backgroundColor="#aaaaaa"; //26
			document.forms[0].czjzfpsdse.style.backgroundColor="#aaaaaa"; //27
			document.forms[0].fzjgyftsdse.style.backgroundColor=""; //28
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor="#aaaaaa"; //29
			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].fpbl.style.backgroundColor=""; //31
			document.forms[0].fpsdse.style.backgroundColor=""; //32

		}else{
			document.forms[0].yysr.disabled=false; //2
			document.forms[0].yycb.disabled=false; //3
			document.forms[0].lrze.disabled=false; //4
			document.forms[0].tdjsynssde.disabled=false; //5
			document.forms[0].bzsr.disabled=false; //6
			document.forms[0].mssr.disabled=false; //7
			document.forms[0].mbyqndks.disabled=false; //8
			document.forms[0].sjlre.disabled=false; //9
			document.forms[0].sl.disabled=false; //10
			document.forms[0].ynsdse.disabled=false; //11
			document.forms[0].jmsdse.disabled=false; //12
			document.forms[0].sjyyjsdse.disabled=false;��//13
			document.forms[0].tdywyjsdse.disabled=false; //14
			document.forms[0].ybtsdse.disabled=false;  //15
			document.forms[0].yqnddjsdse.disabled=false; //16
			document.forms[0].bqsjybtsdse.disabled=false; //17
			document.forms[0].zjgyftsdse.disabled=true; //26
			document.forms[0].czjzfpsdse.disabled=true; //27
			document.forms[0].fzjgyftsdse.disabled=true; //28
			document.forms[0].zjgdlscjybmyftsdse.disabled=true; //29
			document.forms[0].zjgycxfzjgyftsdse.disabled=true; //30
			document.forms[0].fpbl.disabled=true; //31
			document.forms[0].fpsdse.disabled=true; //32
			
			document.forms[0].yysr.style.backgroundColor=""; //2
			document.forms[0].yycb.style.backgroundColor=""; //3
			document.forms[0].lrze.style.backgroundColor=""; //4
			document.forms[0].tdjsynssde.style.backgroundColor=""; //5
			document.forms[0].bzsr.style.backgroundColor=""; //6
			document.forms[0].mssr.style.backgroundColor=""; //7
			document.forms[0].mbyqndks.style.backgroundColor=""; //8
			document.forms[0].sjlre.style.backgroundColor=""; //9
			document.forms[0].sl.style.backgroundColor=""; //10
			document.forms[0].ynsdse.style.backgroundColor=""; //11
			document.forms[0].jmsdse.style.backgroundColor=""; //12
			document.forms[0].sjyyjsdse.style.backgroundColor="";��//13
			document.forms[0].tdywyjsdse.style.backgroundColor=""; //14
			document.forms[0].ybtsdse.style.backgroundColor="";  //15
			document.forms[0].yqnddjsdse.style.backgroundColor=""; //16
			document.forms[0].bqsjybtsdse.style.backgroundColor=""; //17
			document.forms[0].zjgyftsdse.style.backgroundColor="#aaaaaa"; //26
			document.forms[0].czjzfpsdse.style.backgroundColor="#aaaaaa"; //27
			document.forms[0].fzjgyftsdse.style.backgroundColor="#aaaaaa"; //28
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor="#aaaaaa"; //29
			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa"; //31
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa"; //32
		}
		//alert("4");
		readOnlyFilter();
	}

	function readOnlyFilter(){
		//alert("readOnlyFilter");
		//������˰
		if(document.forms[0].lje_nsfs[1].checked==true){
			document.forms[0].yysr.readOnly=false; //2
			document.forms[0].yycb.readOnly=false; //3
			document.forms[0].lrze.readOnly=false; //4
			document.forms[0].tdjsynssde.readOnly=false; //5
			document.forms[0].bzsr.readOnly=false; //6
			document.forms[0].mssr.readOnly=false; //7
			document.forms[0].mbyqndks.readOnly=false; //8
			document.forms[0].sjlre.readOnly=true; //9
			document.forms[0].sl.readOnly=true; //10
			document.forms[0].ynsdse.readOnly=true; //11
			document.forms[0].jmsdse.readOnly=false; //12
			document.forms[0].sjyyjsdse.readOnly=false;��//13
			document.forms[0].tdywyjsdse.readOnly=false; //14
			document.forms[0].ybtsdse.readOnly=true;  //15
			document.forms[0].yqnddjsdse.readOnly=false; //16
			document.forms[0].bqsjybtsdse.readOnly=true; //17
			document.forms[0].zjgyftsdse.readOnly=true; //26
			document.forms[0].czjzfpsdse.readOnly=true; //27
			document.forms[0].fzjgyftsdse.readOnly=true; //28
			document.forms[0].zjgdlscjybmyftsdse.readOnly=true; //29
			document.forms[0].zjgycxfzjgyftsdse.readOnly=true; //30
			document.forms[0].fpbl.readOnly=true; //31
			document.forms[0].fpsdse.readOnly=true; //32
			
			document.forms[0].yysr.style.backgroundColor=""; //2
			document.forms[0].yycb.style.backgroundColor=""; //3
			document.forms[0].lrze.style.backgroundColor=""; //4
			document.forms[0].tdjsynssde.style.backgroundColor=""; //5
			document.forms[0].bzsr.style.backgroundColor=""; //6
			document.forms[0].mssr.style.backgroundColor=""; //7
			document.forms[0].mbyqndks.style.backgroundColor=""; //8
			document.forms[0].sjlre.style.backgroundColor="#FAEBD7"; //9
			document.forms[0].sl.style.backgroundColor="#FAEBD7"; //10
			document.forms[0].ynsdse.style.backgroundColor="#FAEBD7"; //11
			document.forms[0].jmsdse.style.backgroundColor=""; //12
			document.forms[0].sjyyjsdse.style.backgroundColor="";��//13
			document.forms[0].tdywyjsdse.style.backgroundColor=""; //14
			document.forms[0].ybtsdse.style.backgroundColor="#FAEBD7";  //15
			document.forms[0].yqnddjsdse.style.backgroundColor=""; //16
			document.forms[0].bqsjybtsdse.style.backgroundColor="#FAEBD7"; //17
			document.forms[0].zjgyftsdse.style.backgroundColor="#aaaaaa"; //26
			document.forms[0].czjzfpsdse.style.backgroundColor="#aaaaaa"; //27
			document.forms[0].fzjgyftsdse.style.backgroundColor="#aaaaaa"; //28
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor="#aaaaaa"; //29
			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa"; //31
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa"; //32

		}else if(document.forms[0].lje_nsfs[0].checked==true&&document.forms[0].lje_zfjg[0].checked==true){
			// ������˰-�ܻ���
			document.forms[0].yysr.readOnly=false; //2
			document.forms[0].yycb.readOnly=false; //3
			document.forms[0].lrze.readOnly=false; //4
			document.forms[0].tdjsynssde.readOnly=false; //5
			document.forms[0].bzsr.readOnly=false; //6
			document.forms[0].mssr.readOnly=false; //7
			document.forms[0].mbyqndks.readOnly=false; //8
			document.forms[0].sjlre.readOnly=true; //9
			document.forms[0].sl.readOnly=true; //10
			document.forms[0].ynsdse.readOnly=true; //11
			document.forms[0].jmsdse.readOnly=false; //12
			document.forms[0].sjyyjsdse.readOnly=false;��//13
			document.forms[0].tdywyjsdse.readOnly=false; //14
			document.forms[0].ybtsdse.readOnly=true;  //15
			document.forms[0].yqnddjsdse.readOnly=false; //16
			document.forms[0].bqsjybtsdse.readOnly=true; //17
			document.forms[0].zjgyftsdse.readOnly=true; //26
			document.forms[0].czjzfpsdse.readOnly=true; //27
			document.forms[0].fzjgyftsdse.readOnly=true; //28
			document.forms[0].zjgdlscjybmyftsdse.readOnly=false; //29
			document.forms[0].zjgycxfzjgyftsdse.readOnly=false; //30
			document.forms[0].fpbl.readOnly=true; //31
			document.forms[0].fpsdse.readOnly=true; //32
			
			document.forms[0].yysr.style.backgroundColor=""; //2
			document.forms[0].yycb.style.backgroundColor=""; //3
			document.forms[0].lrze.style.backgroundColor=""; //4
			document.forms[0].tdjsynssde.style.backgroundColor=""; //5
			document.forms[0].bzsr.style.backgroundColor=""; //6
			document.forms[0].mssr.style.backgroundColor=""; //7
			document.forms[0].mbyqndks.style.backgroundColor=""; //8
			document.forms[0].sjlre.style.backgroundColor="#FAEBD7"; //9
			document.forms[0].sl.style.backgroundColor="#FAEBD7"; //10
			document.forms[0].ynsdse.style.backgroundColor="#FAEBD7"; //11
			document.forms[0].jmsdse.style.backgroundColor=""; //12
			document.forms[0].sjyyjsdse.style.backgroundColor="";��//13
			document.forms[0].tdywyjsdse.style.backgroundColor=""; //14
			document.forms[0].ybtsdse.style.backgroundColor="#FAEBD7";  //15
			document.forms[0].yqnddjsdse.style.backgroundColor=""; //16
			document.forms[0].bqsjybtsdse.style.backgroundColor="#FAEBD7"; //17
			document.forms[0].zjgyftsdse.style.backgroundColor="#FAEBD7"; //26
			document.forms[0].czjzfpsdse.style.backgroundColor="#FAEBD7"; //27
			document.forms[0].fzjgyftsdse.style.backgroundColor="#FAEBD7"; //28
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor=""; //29
			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor=""; //30
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa"; //31
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa"; //32

		}else if(document.forms[0].lje_nsfs[0].checked==true&&document.forms[0].lje_zfjg[1].checked==true){
			// ������˰-��֧����
			document.forms[0].yysr.readOnly=true; //2
			document.forms[0].yycb.readOnly=true; //3
			document.forms[0].lrze.readOnly=true; //4
			document.forms[0].tdjsynssde.readOnly=true; //5
			document.forms[0].bzsr.readOnly=true; //6
			document.forms[0].mssr.readOnly=true; //7
			document.forms[0].mbyqndks.readOnly=true; //8
			document.forms[0].sjlre.readOnly=true; //9
			document.forms[0].sl.readOnly=true; //10
			document.forms[0].ynsdse.readOnly=true; //11
			document.forms[0].jmsdse.readOnly=true; //12
			document.forms[0].sjyyjsdse.readOnly=true;��//13
			document.forms[0].tdywyjsdse.readOnly=true; //14
			document.forms[0].ybtsdse.readOnly=true;  //15
			document.forms[0].yqnddjsdse.readOnly=true; //16
			document.forms[0].bqsjybtsdse.readOnly=true; //17
			document.forms[0].zjgyftsdse.readOnly=true; //26
			document.forms[0].czjzfpsdse.readOnly=true; //27
			document.forms[0].fzjgyftsdse.readOnly=false; //28
			document.forms[0].zjgdlscjybmyftsdse.readOnly=true; //29
			document.forms[0].zjgycxfzjgyftsdse.readOnly=true; //30
			document.forms[0].fpbl.readOnly=false; //31
			document.forms[0].fpsdse.readOnly=true; //32
			
			document.forms[0].yysr.style.backgroundColor="#aaaaaa"; //2
			document.forms[0].yycb.style.backgroundColor="#aaaaaa"; //3
			document.forms[0].lrze.style.backgroundColor="#aaaaaa"; //4
			document.forms[0].tdjsynssde.style.backgroundColor="#aaaaaa"; //5
			document.forms[0].bzsr.style.backgroundColor="#aaaaaa"; //6
			document.forms[0].mssr.style.backgroundColor="#aaaaaa"; //7
			document.forms[0].mbyqndks.style.backgroundColor="#aaaaaa"; //8
			document.forms[0].sjlre.style.backgroundColor="#FAEBD7"; //9
			document.forms[0].sl.style.backgroundColor="#aaaaaa"; //10
			document.forms[0].ynsdse.style.backgroundColor="#FAEBD7"; //11
			document.forms[0].jmsdse.style.backgroundColor="#aaaaaa"; //12
			document.forms[0].sjyyjsdse.style.backgroundColor="#aaaaaa";��//13
			document.forms[0].tdywyjsdse.style.backgroundColor="#aaaaaa"; //14
			document.forms[0].ybtsdse.style.backgroundColor="#FAEBD7";  //15
			document.forms[0].yqnddjsdse.style.backgroundColor="#aaaaaa"; //16
			document.forms[0].bqsjybtsdse.style.backgroundColor="#FAEBD7"; //17
			document.forms[0].zjgyftsdse.style.backgroundColor="#aaaaaa"; //26
			document.forms[0].czjzfpsdse.style.backgroundColor="#aaaaaa"; //27
			document.forms[0].fzjgyftsdse.style.backgroundColor=""; //28
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor="#aaaaaa"; //29
			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].fpbl.style.backgroundColor=""; //31
			document.forms[0].fpsdse.style.backgroundColor="#FAEBD7"; //32
		}else{
			document.forms[0].yysr.readOnly=false; //2
			document.forms[0].yycb.readOnly=false; //3
			document.forms[0].lrze.readOnly=false; //4
			document.forms[0].tdjsynssde.readOnly=false; //5
			document.forms[0].bzsr.readOnly=false; //6
			document.forms[0].mssr.readOnly=false; //7
			document.forms[0].mbyqndks.readOnly=false; //8
			document.forms[0].sjlre.readOnly=true; //9
			document.forms[0].sl.readOnly=true; //10
			document.forms[0].ynsdse.readOnly=true; //11
			document.forms[0].jmsdse.readOnly=false; //12
			document.forms[0].sjyyjsdse.readOnly=false;��//13
			document.forms[0].tdywyjsdse.readOnly=false; //14
			document.forms[0].ybtsdse.readOnly=true;  //15
			document.forms[0].yqnddjsdse.readOnly=false; //16
			document.forms[0].bqsjybtsdse.readOnly=true; //17
			document.forms[0].zjgyftsdse.readOnly=true; //26
			document.forms[0].czjzfpsdse.readOnly=true; //27
			document.forms[0].fzjgyftsdse.readOnly=true; //28
			document.forms[0].zjgdlscjybmyftsdse.readOnly=true; //29
			document.forms[0].zjgycxfzjgyftsdse.readOnly=true; //30
			document.forms[0].fpbl.readOnly=true; //31
			document.forms[0].fpsdse.readOnly=true; //32
			
			document.forms[0].yysr.style.backgroundColor=""; //2
			document.forms[0].yycb.style.backgroundColor=""; //3
			document.forms[0].lrze.style.backgroundColor=""; //4
			document.forms[0].tdjsynssde.style.backgroundColor=""; //5
			document.forms[0].bzsr.style.backgroundColor=""; //6
			document.forms[0].mssr.style.backgroundColor=""; //7
			document.forms[0].mbyqndks.style.backgroundColor=""; //8
			document.forms[0].sjlre.style.backgroundColor="#FAEBD7"; //9
			document.forms[0].sl.style.backgroundColor="#FAEBD7"; //10
			document.forms[0].ynsdse.style.backgroundColor="#FAEBD7"; //11
			document.forms[0].jmsdse.style.backgroundColor=""; //12
			document.forms[0].sjyyjsdse.style.backgroundColor="";��//13
			document.forms[0].tdywyjsdse.style.backgroundColor=""; //14
			document.forms[0].ybtsdse.style.backgroundColor="#FAEBD7";  //15
			document.forms[0].yqnddjsdse.style.backgroundColor=""; //16
			document.forms[0].bqsjybtsdse.style.backgroundColor="#FAEBD7"; //17
			document.forms[0].zjgyftsdse.style.backgroundColor="#aaaaaa"; //26
			document.forms[0].czjzfpsdse.style.backgroundColor="#aaaaaa"; //27
			document.forms[0].fzjgyftsdse.style.backgroundColor="#aaaaaa"; //28
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor="#aaaaaa"; //29
			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa"; //31
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa"; //32
		}
		
	}

  <%/*����--��˰����--����*/%>
  function compute_Row_1(){
    //alert("compute_Row_1");
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
  
  <%/*����--�ֻܷ���--����*/%>
  function compute_Row_2(){
    //alert("compute_Row_2");
    if(document.forms[0].lje_zfjg[0].checked==1){
      document.forms[0].zfjg.value=document.forms[0].lje_zfjg[0].value;
    }else if(document.forms[0].lje_zfjg[1].checked==1){
      document.forms[0].zfjg.value=document.forms[0].lje_zfjg[1].value;
    }     
    checkFilter();
    
    if(document.forms[0].lje_nsfs[0].checked==true){
    	document.forms[0].fzjgyftsdse.value="0.00";
    }
    if(document.forms[0].lje_nsfs[1].checked==true){
    	document.forms[0].fzjgyftsdse.value="";
    }
    
    if(document.forms[0].lje_nsfs[0].checked==true&&document.forms[0].lje_zfjg[0].checked==true){
    	//alert("document.forms[0].lje_nsfs[0].checked==="+document.forms[0].lje_nsfs[0].checked);
    	//alert("document.forms[0].ybtsdse.value===="+document.forms[0].ybtsdse.value);
    	//compute_Row_24();
    	//compute_Row_25();
    	//compute_Row_26();
    	document.forms[0].zjgyftsdse.value=((1*document.forms[0].ybtsdse.value/100)*25).toFixed(2);
		document.forms[0].czjzfpsdse.value=((1*document.forms[0].ybtsdse.value/100)*25).toFixed(2);
		document.forms[0].fzjgyftsdse.value=((1*document.forms[0].ybtsdse.value/100)*50).toFixed(2);
		
    }
  }
  
  <%/*�����2������*/%>
  function compute_Row_3(){
    //alert("compute_Row_3");
    //�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].yysr;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
  }
  
  <%/*�����3������*/%>
  function compute_Row_4(){
    //alert("compute_Row_4");
    //�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].yycb;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
  }
  
  <%/*�����4������*/%>
  function compute_Row_5(){
    //alert("compute_Row_5");
    //�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
      return false;                         
    }
    var obj_input=document.forms[0].lrze;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
    //compute_Row_9();
    compute_Row_10();
  }
  
  <%/*�����5������*/%>
  function compute_Row_6(){
    //alert("compute_Row_6");
    //�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].tdjsynssde;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
    if(1*obj_input.value<0){
      alert("�ӣ��ض�ҵ������Ӧ��˰���ö5�У�Ӧ���ڵ���0!");
      document.forms[0].tdjsynssde.select();
      document.forms[0].tdjsynssde.focus();
    }
    //compute_Row_9();
    compute_Row_10();
  }
  
  <%/*�����6������*/%>
  function compute_Row_7(){
    //alert("compute_Row_7");
    //�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].bzsr;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
    if(1*obj_input.value<0){
      alert("��������˰���루6�У�Ӧ���ڵ���0!");
      document.forms[0].bzsr.select();
      document.forms[0].bzsr.focus();
    }
    //compute_Row_9();
    compute_Row_10();
  }
  
  <%/*�����7������*/%>
  function compute_Row_8(){
    //alert("compute_Row_8");
    //�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].mssr;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
    if(1*obj_input.value<0){
      alert("��˰���루7�У�Ӧ���ڵ���0!");
      document.forms[0].mssr.select();
      document.forms[0].mssr.focus();
    }
    //compute_Row_9();
    compute_Row_10();
  }

  <%/*�����8������*/%>
  function compute_Row_9(){
    //alert("compute_Row_9");
    //�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].mbyqndks;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
    var obj_4_5_6_7_jsjg = (1*document.forms[0].lrze.value+1*document.forms[0].tdjsynssde.value-1*document.forms[0].bzsr.value-1*document.forms[0].mssr.value).toFixed(2);
    //alert("obj_4_5_6_7_jsjg=="+obj_4_5_6_7_jsjg);
    if((1*obj_4_5_6_7_jsjg>0)&&(1*obj_input.value)>(1*obj_4_5_6_7_jsjg)){
      alert("�ֲ���ǰ��ȿ���8�У�ӦС�ڵ��ڵ�4+5-6-7,��Ӧ���ڵ���0��");
      document.forms[0].mbyqndks.select();
      document.forms[0].mbyqndks.focus();
      //return false;
    }
    if((1*obj_4_5_6_7_jsjg>0)&&(1*obj_input.value<0)){
      alert("�ֲ���ǰ��ȿ���8�У�Ӧ���ڵ���0!");
      document.forms[0].mbyqndks.select();
      document.forms[0].mbyqndks.focus();
      //return false;
    }
    if((1*obj_4_5_6_7_jsjg<=0)&&(1*obj_input.value!=0)){
  	  alert("��4+5-6-7��С�ڵ���0���ֲ���ǰ��ȿ���8�У�Ӧ����0!");
  	  //obj_input.value="0.00";
  	  document.forms[0].mbyqndks.select();
      document.forms[0].mbyqndks.focus();
      //return false;
    }
    compute_Row_10();
  }
  
  <%/*�����9������*/%>
  function compute_Row_10(){
    //alert("compute_Row_10");
    //�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].sjlre;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
    
    document.forms[0].sjlre.value=(1*document.forms[0].lrze.value+1*document.forms[0].tdjsynssde.value-1*document.forms[0].bzsr.value-1*document.forms[0].mssr.value-1*document.forms[0].mbyqndks.value).toFixed(2);
    if((1*document.forms[0].sjlre.value)==""){
      document.forms[0].sjlre.valuee="0.00";
    }
    compute_Row_12();
  }
  
  <%/*�����10������*/%>
  function compute_Row_11(){
    //alert("compute_Row_11");
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
    compute_Row_12();
  }
  
  <%/*�����11������*/%>
  function compute_Row_12(){
    //alert("compute_Row_12");
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
    
    document.forms[0].ynsdse.value=((1*document.forms[0].sl.value/100)*document.forms[0].sjlre.value).toFixed(2);
    if(1*obj_input.value<0){
      obj_input.value="0.00";
    }
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
    compute_Row_16();
  }

  <%/*�����12������*/%>
  function compute_Row_13(){
    //alert("compute_Row_13");
    //�ж�����������Ƿ����Ҫ��
    if(!isNum(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].jmsdse;
    //if((obj_input.value)*1<0){
    //  obj_input.value="0.00";
    //}
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
    
    if((1*obj_input.value)>(1*document.forms[0].ynsdse.value)){
      alert("������������˰�12�У�ӦС�ڵ���Ӧ������˰�11�У�,��Ӧ���ڵ���0��");
      document.forms[0].jmsdse.select();
      document.forms[0].jmsdse.focus();
      //return false;
    }
    if(1*obj_input.value<0){
      alert("������������˰�12�У�Ӧ���ڵ���0!");
      document.forms[0].jmsdse.select();
      document.forms[0].jmsdse.focus();
      //return false;
    }
    compute_Row_16();
  }
  
  <%/*�����13������*/%>
  function compute_Row_14(){
    //alert("compute_Row_14");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].sjyyjsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		compute_Row_16();
	}
	
	<%/*�����14������*/%>
	function compute_Row_15(){
		//alert("compute_Row_15");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].tdywyjsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		compute_Row_16();
	}
	
	<%/*�����15������*/%>
	function compute_Row_16(){
		//alert("compute_Row_16");
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
		//�����д�15����ֵ
		document.forms[0].ybtsdse.value=(1*document.forms[0].ynsdse.value-1*document.forms[0].jmsdse.value-1*document.forms[0].sjyyjsdse.value-1*document.forms[0].tdywyjsdse.value).toFixed(2);
		if((1*document.forms[0].ybtsdse.value)<0){
			document.forms[0].ybtsdse.value="0.00";
		}
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			compute_Row_24();
			compute_Row_25();
			compute_Row_26();
		}
		compute_Row_18();
	}
	
	<%/*�����16������*/%>
	function compute_Row_17(){
		//alert("compute_Row_17");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].yqnddjsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value>1*document.forms[0].ybtsdse.value){
			alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�16�У�ӦС�ڵ���Ӧ�����ˣ�����˰�15�У�����˶ԣ�");
	      	document.forms[0].yqnddjsdse.select();
	      	document.forms[0].yqnddjsdse.focus();
		}
		if(1*obj_input.value<0){
	        alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�16�У�Ӧ���ڵ���0!");
	        document.forms[0].yqnddjsdse.select();
	        document.forms[0].yqnddjsdse.focus();
	      //return false;
	    }
		compute_Row_18();
	}
	
	<%/*�����17������*/%>
	function compute_Row_18(){
		//alert("compute_Row_18");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].bqsjybtsdse;
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//�����д�17����ֵ
		document.forms[0].bqsjybtsdse.value=(1*document.forms[0].ybtsdse.value-1*document.forms[0].yqnddjsdse.value).toFixed(2);
		if((1*document.forms[0].bqsjybtsdse.value)<=0){
		//alert("��17��С�ڵ���0");
			document.forms[0].bqsjybtsdse.value="0.00";
		}
	}

	<%/*�����26������*/%>
	function compute_Row_24(){
		//alert("compute_Row_24");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].zjgyftsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		obj_input.value=((1*document.forms[0].ybtsdse.value/100)*25).toFixed(2);
	}

	<%/*�����27������*/%>
	function compute_Row_25(){
		//alert("compute_Row_25");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].czjzfpsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		obj_input.value=((1*document.forms[0].ybtsdse.value/100)*25).toFixed(2);
	}
	
	<%/*�����28������*/%>
	function compute_Row_26(){
		//alert("compute_Row_26");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].fzjgyftsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			obj_input.value=((1*document.forms[0].ybtsdse.value/100)*50).toFixed(2);
		}
//		if(1*obj_input.value<0){
//	      alert("��֧����Ӧ��̯����˰�28�У�Ӧ���ڵ���0!");
//	      document.forms[0].fzjgyftsdse.select();
//	      document.forms[0].fzjgyftsdse.focus();
//	      //return false;
//	    }
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
			compute_Row_30();
		}
	}
	
	<%/*�����29������*/%>
	function compute_Row_27(){
		//alert("compute_Row_27");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].zjgdlscjybmyftsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
	        alert("�ܻ�������������Ӫ����Ӧ��̯����˰�29�У�Ӧ���ڵ���0!");
	        document.forms[0].zjgdlscjybmyftsdse.select();
	        document.forms[0].zjgdlscjybmyftsdse.focus();
	      //return false;
	    }
		var obj_29_30_jsjg = (1*document.forms[0].zjgdlscjybmyftsdse.value+1*document.forms[0].zjgycxfzjgyftsdse.value).toFixed(2); 

		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(1*obj_29_30_jsjg>(1*document.forms[0].fzjgyftsdse.value)){
		        alert("�ܻ�������������Ӫ����Ӧ��̯����˰�29�У����ܻ����ѳ�����֧����Ӧ��̯����˰�30�У�֮��ӦС�ڵ��ڷ�֧����Ӧ��̯����˰�28�У�!");
		        document.forms[0].zjgdlscjybmyftsdse.select();
		        document.forms[0].zjgdlscjybmyftsdse.focus();
		      //return false;
		    }
		}
	}
	
	<%/*�����30������*/%>
	function compute_Row_28(){
		//alert("compute_Row_28");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].zjgycxfzjgyftsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
	        alert("�ܻ����ѳ�����֧����Ӧ��̯����˰�30�У�Ӧ���ڵ���0!");
	        document.forms[0].zjgycxfzjgyftsdse.select();
	        document.forms[0].zjgycxfzjgyftsdse.focus();
	      //return false;
	    }
		var obj_29_30_jsjg = (1*document.forms[0].zjgdlscjybmyftsdse.value+1*document.forms[0].zjgycxfzjgyftsdse.value).toFixed(2); 

		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(1*obj_29_30_jsjg>(1*document.forms[0].fzjgyftsdse.value)){
		        alert("�ܻ�������������Ӫ����Ӧ��̯����˰�29�У����ܻ����ѳ�����֧����Ӧ��̯����˰�30�У�֮��ӦС�ڵ��ڷ�֧����Ӧ��̯����˰�28�У�!");
		        document.forms[0].zjgycxfzjgyftsdse.select();
		        document.forms[0].zjgycxfzjgyftsdse.focus();
		      //return false;
		    }
		}
	}
	
	<%/*�����31������*/%>
	function compute_Row_29(){
		//alert("compute_Row_29");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].fpbl;
		if((1*obj_input.value<0)||(1*obj_input.value>100)){
			alert("���������31�У�Ӧ����0-100�����֣���˶ԣ�");
			document.forms[0].fpbl.select();
	        document.forms[0].fpbl.focus();
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
			compute_Row_30();
		}
	}
	
	<%/*�����32������*/%>
	function compute_Row_30(){
		//alert("compute_Row_30");
        //�ж�����������Ƿ����Ҫ��
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].fpsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
			obj_input.value=((1*document.forms[0].fzjgyftsdse.value/100)*(1*document.forms[0].fpbl.value)).toFixed(2);
		}
//		if((obj_input.value)*1<0){
//			obj_input.value="0.00";
//		}
	}	

	<%/*����ʱ�����ݽ���У��*/%>
	function saveCheck(){
		//
		if(document.forms[0].lje_nsfs[0].checked==1){
			if((document.forms[0].lje_zfjg[0].checked==0)&&(document.forms[0].lje_zfjg[1].checked==0)){
				alert("��ѡ���ֻܷ������ͣ�");
				return false;
			}
		}
		//alert("saveCheckFilter...");
		//��ͷ��˰����ѡ���Ϊ������˰ʱ��ϵͳ�����ű����2-17�У���18��32������
		if(document.forms[0].lje_nsfs[1].checked==1){
			var value_3=(document.forms[0].yysr.value)*1;
			var value_4=(document.forms[0].yycb.value)*1;
			var value_5=(document.forms[0].lrze.value)*1;
			var value_6=(document.forms[0].tdjsynssde.value)*1;
			var value_7=(document.forms[0].bzsr.value)*1;
			var value_8=(document.forms[0].mssr.value)*1;
			var value_9=(document.forms[0].mbyqndks.value)*1;
			var value_10=(document.forms[0].sjlre.value)*1;
			var value_11=((document.forms[0].sl.value)*1)/100;
			var value_12=(document.forms[0].ynsdse.value)*1;
			var value_13=(document.forms[0].jmsdse.value)*1;
			var value_14=(document.forms[0].sjyyjsdse.value)*1;
			var value_15=(document.forms[0].tdywyjsdse.value)*1;
			var value_16=(document.forms[0].ybtsdse.value)*1;
			var value_17=(document.forms[0].yqnddjsdse.value)*1;
			var value_18=(document.forms[0].bqsjybtsdse.value)*1;

			if(!isNum(document.forms[0].yysr , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].yycb , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].lrze , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].tdjsynssde , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].bzsr , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].mssr , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].mbyqndks , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].ynsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].jmsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].sjyyjsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].tdywyjsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].yqnddjsdse , null, null, null, null, 2)){
				return false;                         
			}
			<%/*�жϵ�5������*/%>
			if(value_6<0){
		      	alert("�ӣ��ض�ҵ������Ӧ��˰���ö5�У�Ӧ���ڵ���0!");
		      	document.forms[0].tdjsynssde.select();
		      	document.forms[0].tdjsynssde.focus();
		      	return false;
		    }
		    <%/*�жϵ�6������*/%>
		    if(value_7<0){
		      	alert("��������˰���루6�У�Ӧ���ڵ���0!");
		      	document.forms[0].bzsr.select();
      		  	document.forms[0].bzsr.focus();
		      	return false;
		    }
		    <%/*�жϵ�7������*/%>
		    if(value_8<0){
		      	alert("��˰���루7�У�Ӧ���ڵ���0!");
		      	document.forms[0].mssr.select();
      			document.forms[0].mssr.focus();
		        return false;
		    }
			<%/*�жϵ�8������*/%>
			var obj_4_5_6_7_jisuanjieguo = (value_5+value_6-value_7-value_8).toFixed(2);
			if((1*obj_4_5_6_7_jisuanjieguo>0)&&(value_9>obj_4_5_6_7_jisuanjieguo)){
				alert("�ֲ���ǰ��ȿ���8�У�ӦС�ڵ��ڵ�4+5-6-7,��Ӧ���ڵ���0��");
				document.forms[0].mbyqndks.select();
        		document.forms[0].mbyqndks.focus();
				return false;
			}
			if((1*obj_4_5_6_7_jisuanjieguo>0)&&(value_9<0)){
				alert("�ֲ���ǰ��ȿ���8�У�Ӧ���ڵ���0!");
				document.forms[0].mbyqndks.select();
        		document.forms[0].mbyqndks.focus();
				return false;
			}
			if((1*obj_4_5_6_7_jisuanjieguo<=0)&&(value_9!=0)){
		  	    alert("��4+5-6-7��С�ڵ���0���ֲ���ǰ��ȿ���8�У�Ӧ����0!");
		  	    document.forms[0].mbyqndks.select();
      			document.forms[0].mbyqndks.focus();
				return false;
		    }
			<%/*�жϵ�9������*/%>
			//var obj_4_5_6_7_8_jisuanjieguo = (value_5+value_6-value_7-value_8-value_9).toFixed(2);
			//if(value_10>obj_4_5_6_7_8_jisuanjieguo){
			//	alert("ʵ������9�У�ӦС�ڵ��ڵ�4+5-6-7-8");
			//	document.forms[0].sjlre.select();
        	//	document.forms[0].sjlre.focus();
			//	return false;
			//}
			
			<%/*�жϵ�12������*/%>
			if(value_13>value_12){
				alert("������������˰�12�У�ӦС�ڵ���Ӧ������˰�11�У�,��Ӧ���ڵ���0��");
				document.forms[0].jmsdse.select();
        		document.forms[0].jmsdse.focus();
				return false;
			}
			if(value_13<0){
				alert("������������˰�12�У�Ӧ���ڵ���0!");
				document.forms[0].jmsdse.select();
        		document.forms[0].jmsdse.focus();
				return false;
			}
			<%/*�жϵ�15������*/%>
			//var obj_11_12_13_14_jisuanjieguo = (value_12-value_13-value_14-value_15).toFixed(2);
			//if(value_16>obj_11_12_13_14_jisuanjieguo){
			//	alert("Ӧ�����ˣ����ö15�У�ӦС�ڵ��ڵ�11-12-13-14,��Ӧ���ڵ���0��");
			//	document.forms[0].ybtsdse.select();
        	//	document.forms[0].ybtsdse.focus();
			//	return false;
			//}
			//if(value_16<0){
			//	alert("Ӧ�����ˣ����ö15�У�Ӧ���ڵ���0!");
			//	document.forms[0].ybtsdse.select();
        	//	document.forms[0].ybtsdse.focus();
			//	return false;
			//}
			<%/*�жϵ�16������*/%>
			if(value_17>value_16){
				alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�16�У�ӦС�ڵ���Ӧ�����ˣ�����˰�15�У�����˶ԣ�");
	            document.forms[0].yqnddjsdse.select();
	            document.forms[0].yqnddjsdse.focus();
				return false;
			}
			if(value_17<0){
				alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�16�У�Ӧ���ڵ���0!");
	            document.forms[0].yqnddjsdse.select();
	            document.forms[0].yqnddjsdse.focus();
				return false;
			}	
			
		    
		//����ͷ��˰����ѡ���Ϊ������˰-�ܻ���ʱ���ű����2-17��26-30�У������д�����
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			var value_3=(document.forms[0].yysr.value)*1;
			var value_4=(document.forms[0].yycb.value)*1;
			var value_5=(document.forms[0].lrze.value)*1;
			var value_6=(document.forms[0].tdjsynssde.value)*1;
			var value_7=(document.forms[0].bzsr.value)*1;
			var value_8=(document.forms[0].mssr.value)*1;
			var value_9=(document.forms[0].mbyqndks.value)*1;
			var value_10=(document.forms[0].sjlre.value)*1;
			var value_11=((document.forms[0].sl.value)*1)/100;
			var value_12=(document.forms[0].ynsdse.value)*1;
			var value_13=(document.forms[0].jmsdse.value)*1;
			var value_14=(document.forms[0].sjyyjsdse.value)*1;
			var value_15=(document.forms[0].tdywyjsdse.value)*1;
			var value_16=(document.forms[0].ybtsdse.value)*1;
			var value_17=(document.forms[0].yqnddjsdse.value)*1;
			var value_18=(document.forms[0].bqsjybtsdse.value)*1;
			var value_24=(document.forms[0].zjgyftsdse.value)*1;
			var value_25=(document.forms[0].czjzfpsdse.value)*1;
			var value_26=(document.forms[0].fzjgyftsdse.value)*1;
			var value_27=(document.forms[0].zjgdlscjybmyftsdse.value)*1;
			var value_28=(document.forms[0].zjgycxfzjgyftsdse.value)*1;

			if(!isNum(document.forms[0].yysr , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].yycb , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].lrze , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].tdjsynssde , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].bzsr , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].mssr , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].mbyqndks , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].ynsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].jmsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].sjyyjsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].tdywyjsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].yqnddjsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].zjgdlscjybmyftsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].zjgycxfzjgyftsdse , null, null, null, null, 2)){
				return false;                         
			}
			
			<%/*�жϵ�5������*/%>
			if(value_6<0){
		      	alert("�ӣ��ض�ҵ������Ӧ��˰���ö5�У�Ӧ���ڵ���0!");
		      	document.forms[0].tdjsynssde.select();
		      	document.forms[0].tdjsynssde.focus();
		      	return false;
		    }
		    <%/*�жϵ�6������*/%>
		    if(value_7<0){
		      	alert("��������˰���루6�У�Ӧ���ڵ���0!");
		      	document.forms[0].bzsr.select();
      		  	document.forms[0].bzsr.focus();
		      	return false;
		    }
		    <%/*�жϵ�7������*/%>
		    if(value_8<0){
		      	alert("��˰���루7�У�Ӧ���ڵ���0!");
		      	document.forms[0].mssr.select();
      			document.forms[0].mssr.focus();
		        return false;
		    }
			<%/*�жϵ�8������*/%>
			var obj_4_5_6_7_jisuanjieguo = (value_5+value_6-value_7-value_8).toFixed(2);
			if((1*obj_4_5_6_7_jisuanjieguo>0)&&(value_9>obj_4_5_6_7_jisuanjieguo)){
				alert("�ֲ���ǰ��ȿ���8�У�ӦС�ڵ��ڵ�4+5-6-7,��Ӧ���ڵ���0��");
				document.forms[0].mbyqndks.select();
        		document.forms[0].mbyqndks.focus();
				return false;
			}
			if((1*obj_4_5_6_7_jisuanjieguo>0)&&(value_9<0)){
				alert("�ֲ���ǰ��ȿ���8�У�Ӧ���ڵ���0!");
				document.forms[0].mbyqndks.select();
        		document.forms[0].mbyqndks.focus();
				return false;
			}
			if((1*obj_4_5_6_7_jisuanjieguo<=0)&&(value_9!=0)){
		  	    alert("��4+5-6-7��С�ڵ���0���ֲ���ǰ��ȿ���8�У�Ӧ����0!");
		  	    document.forms[0].mbyqndks.select();
      			document.forms[0].mbyqndks.focus();
				return false;
		    }
			<%/*�жϵ�12������*/%>
			if(value_13>value_12){
				alert("������������˰�12�У�ӦС�ڵ���Ӧ������˰�11�У�,��Ӧ���ڵ���0��");
				document.forms[0].jmsdse.select();
        		document.forms[0].jmsdse.focus();
				return false;
			}
			if(value_13<0){
				alert("������������˰�12�У�Ӧ���ڵ���0!");
				document.forms[0].jmsdse.select();
        		document.forms[0].jmsdse.focus();
				return false;
			}
			<%/*�жϵ�16������*/%>
			if(value_17>value_16){
				alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�16�У�ӦС�ڵ���Ӧ�����ˣ�����˰�15�У�����˶ԣ�");
	            document.forms[0].yqnddjsdse.select();
	            document.forms[0].yqnddjsdse.focus();
				return false;
			}
			if(value_17<0){
				alert("������ǰ��ȶ���ڱ��ڵֽ�����˰�16�У�Ӧ���ڵ���0!");
	            document.forms[0].yqnddjsdse.select();
	            document.forms[0].yqnddjsdse.focus();
				return false;
			}
			<%/*�жϵ�29������*/%>
			if(value_27<0){
				alert("�ܻ�������������Ӫ����Ӧ��̯����˰�29�У�Ӧ���ڵ���0!");
		        document.forms[0].zjgdlscjybmyftsdse.select();
		        document.forms[0].zjgdlscjybmyftsdse.focus();
		        return false;
		    }
			<%/*�жϵ�30������*/%>
			if(value_28<0){
				alert("�ܻ����ѳ�����֧����Ӧ��̯����˰�30�У�Ӧ���ڵ���0!");
		        document.forms[0].zjgycxfzjgyftsdse.select();
		        document.forms[0].zjgycxfzjgyftsdse.focus();
		        return false;
		    }
			<%/*�жϵ�29+30�Ƿ���ڵ�28������*/%>
			var obj_29_30_jsjg = (value_27+value_28).toFixed(2); 
			if(1*obj_29_30_jsjg>value_26){
		        alert("�ܻ�������������Ӫ����Ӧ��̯����˰�29�У����ܻ����ѳ�����֧����Ӧ��̯����˰�30�У�֮��ӦС�ڵ��ڷ�֧����Ӧ��̯����˰�28�У�!");
		        return false;
		    }
			
		//����ͷ��˰����ѡ���Ϊ������˰-��֧����ʱ���ű����28��31��32�У������д�����
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
			var value_26=(document.forms[0].fzjgyftsdse.value)*1;
			var value_29=(document.forms[0].fpbl.value)*1;
			var value_30=(document.forms[0].fpsdse.value)*1;
			if(!isNum(document.forms[0].fzjgyftsdse , null, null, null, null, 2)){
				return false;                         
			}			
			if(!isNum(document.forms[0].fpbl , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNum(document.forms[0].fpsdse , null, null, null, null, 2)){
				return false;                         
			}
			<%/*�жϵ�28������*/%>
//			if(value_26<0){
//		        alert("��֧����Ӧ��̯����˰�28�У�Ӧ���ڵ���0!");
//		        document.forms[0].fzjgyftsdse.select();
//		        document.forms[0].fzjgyftsdse.focus();
//		        return false;
//		    }
			<%/*�жϵ�31������*/%>
			if((value_29<0)||(value_29>100)){
				alert("���������31�У�Ӧ����0-100�����֣���˶ԣ�");
				document.forms[0].fpbl.select();
		        document.forms[0].fpbl.focus();
		        return false;
			}
			
		}else{		
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

		document.forms[0].actionType.value="Jump";
		if (g_objSI.Container != '')
		{
				if (!doSubmitXML(document.forms[0],"Jump",true,"",true))
					{
							document.forms[0].ywczlx.value = old;
							return false;
					}	
		}else
		{
				if (!doSubmitXML(document.forms[0],"Jump",false,"",true))
					{
							document.forms[0].ywczlx.value = old;
							return false;
					}	
		}
		return true;
}

function changeLocalYwczlx(ywczlx)
{
	//alert("changeLocalYwczlx");
	 var rootNode = xmlDoc.documentElement;
    var objCDATA =xmlDoc.createCDATASection(ywczlx);
		rootNode.selectSingleNode("//ywczlx").text = "";
		rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
}

function clearInput(){
		if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
		{
			if(document.forms[0].lje_nsfs[1].checked==1){
				document.forms[0].yysr.value="0.00"; //2
				document.forms[0].yycb.value="0.00"; //3
				document.forms[0].lrze.value="0.00"; //4
				document.forms[0].tdjsynssde.value="0.00"; //5
				document.forms[0].bzsr.value="0.00"; //6
				document.forms[0].mssr.value="0.00"; //7
				document.forms[0].mbyqndks.value="0.00"; //8
				document.forms[0].sjlre.value="0.00"; //9
				document.forms[0].sl.value="25"; //10
				document.forms[0].ynsdse.value="0.00"; //11
				document.forms[0].jmsdse.value="0.00"; //12
				document.forms[0].sjyyjsdse.value="0.00";��//13
				document.forms[0].tdywyjsdse.value="0.00"; //14
				document.forms[0].ybtsdse.value="0.00";  //15
				document.forms[0].yqnddjsdse.value="0.00"; //16
				document.forms[0].bqsjybtsdse.value="0.00"; //17
			}
			if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
				document.forms[0].yysr.value="0.00"; //2
				document.forms[0].yycb.value="0.00"; //3
				document.forms[0].lrze.value="0.00"; //4
				document.forms[0].tdjsynssde.value="0.00"; //5
				document.forms[0].bzsr.value="0.00"; //6
				document.forms[0].mssr.value="0.00"; //7
				document.forms[0].mbyqndks.value="0.00"; //8
				document.forms[0].sjlre.value="0.00"; //9
				document.forms[0].sl.value="25"; //10
				document.forms[0].ynsdse.value="0.00"; //11
				document.forms[0].jmsdse.value="0.00"; //12
				document.forms[0].sjyyjsdse.value="0.00";��//13
				document.forms[0].tdywyjsdse.value="0.00"; //14
				document.forms[0].ybtsdse.value="0.00";  //15
				document.forms[0].yqnddjsdse.value="0.00"; //16
				document.forms[0].bqsjybtsdse.value="0.00"; //17
				document.forms[0].zjgyftsdse.value="0.00"; //26
				document.forms[0].czjzfpsdse.value="0.00"; //27
				document.forms[0].fzjgyftsdse.value="0.00"; //28
				document.forms[0].zjgdlscjybmyftsdse.value="0.00"; //29
				document.forms[0].zjgycxfzjgyftsdse.value="0.00"; //30
			}
			if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
				document.forms[0].fzjgyftsdse.value="0.00"; //28
				document.forms[0].fpbl.value="0.00"; //31
				document.forms[0].fpsdse.value="0.00"; //32
			}
			
		}
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
	}else if(actionType=='View'){
		if(document.forms[0].lje_nsfs[1].checked==1){
			alert("������˰����鿴�����");
			return false;
		}
//		if(confirm("�鿴��������±���ҳ�����ݣ���ȷ�ϣ�"))
//		{
			doSave();
//		}
	}else if(actionType=='Jump'){
		document.forms[0].actionType.value="Jump";
		document.forms[0].submit();
	}else{
		doReturn();
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

function doDelete()
{	
		var old  = document.forms[0].ywczlx.value;
    if((document.forms[0].ywczlx.value == 0) && confirm("�Ƿ�ɾ���걨����(���������)?"))
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

//��Ӧ���������Ļس���ѯ
function jsjdmQuery(){
	if(event.keyCode==13) event.keyCode = 9;
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
 
 
//intFLag 0--���ڵ�����  �ʲ��ܶ�У��
//intFLag 1--������  ע���ʱ����У��
function formatData10(obj,intFlag)
{
	formateNum(obj);
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
<form name="czzsqynb2012" action="czzsqynb2012.dc">
<input name="actionType" type="hidden" id="actionType" value="Show">
	<table width="1000" align="center" cellspacing="0" class="table-99" onKeyDown="jsjdmQuery()">
		<tr>
		<td align="center" valign="top" colspan="7">
        <jsp:include page="../../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="��ҵ����˰��֧���������˰�걨��" />
        <jsp:param name="help_url" value="help/wssb/sbzl/lygfzwhztz/qysdsnd/qysdsnd-000.htm"/>
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
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('View');return false;"><img name="spage" value="¼������" alt="¼������" onMouseOver="MM_swapImage('chakan','','images/insertb2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="images/insertb1.jpg" width="79" height="22" id="chakan"></a>
					<!--a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Delete');return false;"><img name="dpage" value="ɾ��" alt="ɾ��" onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>/images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/shanchu1.jpg" width="79" height="22" id="shanchu"></a  -->
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