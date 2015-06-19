<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
    String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<html>
<head>
<title>�����еط�˰����걨�ɿ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>

<script language="JavaScript">
function doReturn()
{
    document.forms[0].actionType.value = "ListJks";
    document.forms[0].submit();
}

var xmlStr = '<%=request.getAttribute("CA_XML_DATA")==null?"":request.getAttribute("CA_XML_DATA")%>';
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMEVersion = '<%=request.getAttribute("CA_XML_SCHEME_VERSION")==null?"":request.getAttribute("CA_XML_SCHEME_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xslPath="/XSLTWEB/model/010002/XSLT/sbypysjksnew.xsl";
var xmlDoc;
var xslDoc;
function parseXmlOnLoad(){
	xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	xslDoc = new ActiveXObject("Microsoft.XMLDOM");
	xmlDoc.async = false;
	xslDoc.async = false;
	xmlDoc.loadXML(xmlStr);
	xslDoc.load(xslPath);
	var httpStr = xmlDoc.transformNode(xslDoc);
	if (xmlDoc==null || xslDoc==null)
	{
		 alert("����DOC�������");
		 return; 
	}
	if(!xmlDoc.loadXML(xmlStr))
	{
		alert("װ��xml���ݳ���");
		return false;
	}
	if(!xslDoc.load(xslPath))
	{
		alert("װ��xsl���ݳ���");
		return false;
	}
	document.all.td.innerHTML = httpStr;
}
</script>

<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onLoad="parseXmlOnLoad()">
<table  width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  <td colspan=2 valign="top">
        <jsp:include page="../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="�鿴�ɿ���" />
        <jsp:param name="help_url" value="help/wssb/qyzhsb/jksmx/jksmxypys-000.htm"/>
        </jsp:include>
		<html:errors/>
	</td>
  </tr>
<tr><td>
<applet name="printer" code="com.ttsoft.bjtax.webprint.SSJKSPagePrinterNew" width="1" height="1" archive="<%=static_contextpath%>printer/vprinter.jar">
</applet>
</td></tr>
  <tr>
    <td valign="middle" align="center">
	<div id="td"></div>
<br>
<form name="form1" action="listJks.dc" method="post">
	<input name="strXmlData" type="hidden"/>
	<input name="actionType" type="hidden"/>
                  <div align="center">
					<img src="<%=static_contextpath%>images/dayin1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/dayin2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/dayin1.jpg'" alt="��ӡ" onClick="goprint()" style="cursor:hand">
                    &nbsp;&nbsp;&nbsp;&nbsp;
					<img src="<%=static_contextpath%>images/fanhui1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onClick="doReturn()" style="cursor:hand">
                  </div>
                  <br>
</form>
 </td>
  </tr>
  <tr>
    <td valign="bottom" align="center">
    <%@ include file="../include/bottom.jsp" %>
 </td>
  </tr>
</table>
</body>
<script language="JavaScript">

function goprint(){
   	xmlDoc.setProperty("SelectionLanguage","XPath");
//	var szdm = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/szmc").text; //˰�ִ���
//	var tfrq = xmlDoc.selectSingleNode("taxdoc/sbsj/sbrq").text; //������
	var sklx = xmlDoc.selectSingleNode("taxdoc/sbsj/sklx").text;//˰������   
	var dnbh = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/jkpzh").text;//���Ա��
	var zclx = xmlDoc.selectSingleNode("taxdoc/sbsj/djzclxmc").text; //�Ǽ�ע������	
	var tfrqn = xmlDoc.selectSingleNode("taxdoc/sbsj/sbrqn").text; //�������
	var tfrqy = xmlDoc.selectSingleNode("taxdoc/sbsj/sbrqy").text; //�������
	var tfrqr = xmlDoc.selectSingleNode("taxdoc/sbsj/sbrqr").text; //�������			
//	var lsgx = xmlDoc.selectSingleNode("taxdoc/sbsj/lsgxmc").text; //������ϵ   
	var zsjg = xmlDoc.selectSingleNode("//zsswjgzzjgmc").text;//˰�����
	 
	var jkdwdm = xmlDoc.selectSingleNode("//jsjdm").text;//�ɿλ����
//	var jkdwdh = xmlDoc.selectSingleNode("taxdoc/sbsj/lxdh").text; //�ɿλ�绰 
	var jkdwqc = xmlDoc.selectSingleNode("//nsrmc").text;//�ɿλȫ�� 
	var jkdwkhyh = xmlDoc.selectSingleNode("//yhmc").text; //�ɿλ�������� 
	var jkdwzh = xmlDoc.selectSingleNode("//zh").text; //�ɿλ�˺�
	var skgk = '('+xmlDoc.selectSingleNode("taxdoc/sbsj/gkzzjgdm").text+')'+xmlDoc.selectSingleNode("taxdoc/sbsj/gkzzjgmc").text; //�տ����	
	var skxjrq = xmlDoc.selectSingleNode("//xjrq").text;//˰���޽�����	

	var jkjehj = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/sjjedx").text;//�ɿ���ϼ�(TBD)
	var jkjehj_nu = '��'+xmlDoc.selectSingleNode("taxdoc/sbsj/jks/sjje").text;
	var swjg = xmlDoc.selectSingleNode("taxdoc/sbsj/swjgzzjgmc").text;//˰�����(TBD)	
	var jkdw = xmlDoc.selectSingleNode("//nsrmc").text;//�ɿλ(TBD)
	var bz = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/bz").text; //��ע(TBD)
	var cNodeList = xmlDoc.selectSingleNode("//sbsj");
	
	var clength = cNodeList.childNodes.length;
	var node;
	var yskmbm = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/yskmdm").text; //Ԥ���Ŀ����
	var yskmmc = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/yskmmc").text;//Ԥ���Ŀ���� 
	var yskmjc = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/ysjcmc").text;//Ԥ���Ŀ���� 	
	var jkmxpmmc = '';//�ɿ���ϸƷĿ����(TBD)
	var jkmxkssl = '';//�ɿ���ϸ��˰����(TBD)
	var jkmxjsje = ''; //�ɿ���ϸ��˰���(TBD)
	var sksssq = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/skssksrq").text+"-"+xmlDoc.selectSingleNode("taxdoc/sbsj/jks/skssjsrq").text;//˰������ʱ��	
	var jkmxsjse = '';//�ɿ���ϸʵ��˰��(TBD)
	/*
	* ��������滮���㴦Ҫ�����²�ѯԤ�㼶�����ƣ�Ԥ�㼶����ʾΪ���뼶���м�������
	* gaoyh added jkmxfcbl by 20131220
	*/
	var jkmxfcbl = '';//�ɿ���ϸ�ֳɱ���(TBD)
	
	var sjse='';
	var tmp = '';
	for(var i=0;i<clength;i++){
		node = cNodeList.childNodes[i];
		for(var ii=0;ii<node.childNodes.length;ii++){
			tmp = node.childNodes[ii];
//˰��Ʊ֤������Ŀȡ���ɿ���˰��˰Ŀ�������ʾ����ӡ
//			if(tmp.nodeName=='szsmdm'){
//				jkmxpmmc += tmp.text+" ";
//			}else 
				if(tmp.nodeName=='szsmmc'){
				jkmxpmmc += tmp.text+";;";
			}else if(tmp.nodeName=='kssl'){
				jkmxkssl += tmp.text+";;";
			}else if(tmp.nodeName=='jsje'){
				jkmxjsje += tmp.text+";;";
			}else if(tmp.nodeName=='sjse'){
				jkmxsjse += tmp.text+";;";
			}else if(tmp.nodeName=='yskmjc'){
				jkmxfcbl += tmp.text+";;";
			}
		}
	}
	jkmxpmmc = jkmxpmmc.substring(0,jkmxpmmc.length-2);
	jkmxkssl = jkmxkssl.substring(0,jkmxkssl.length-2);
	jkmxjsje = jkmxjsje.substring(0,jkmxjsje.length-2);
	jkmxsjse = jkmxsjse.substring(0,jkmxsjse.length-2);
	jkmxfcbl = jkmxfcbl.substring(0,jkmxfcbl.length-2);
	

//    document.printer.setszdm(szdm);
    document.printer.setSklx(sklx);
    document.printer.setdnbh(dnbh);
    document.printer.setzclx(zclx);    
//    document.printer.settfrq(tfrq);
    document.printer.settfrqn(tfrqn);
    document.printer.settfrqy(tfrqy);
    document.printer.settfrqr(tfrqr);            
//    document.printer.setlsgx(lsgx);
    document.printer.setzsjg(zsjg);
    document.printer.setjkdwdm(jkdwdm);
//    document.printer.setjkdwdh(jkdwdh);
    document.printer.setjkdwqc(jkdwqc);
    document.printer.setjkdwkhyh(jkdwkhyh);
    document.printer.setjkdwzh(jkdwzh);
    document.printer.setskgk(skgk);
    document.printer.setskxjrq(skxjrq);        
    document.printer.setyskmbm(yskmbm);
    document.printer.setyskmmc(yskmmc);
    document.printer.setyskmjc(yskmjc);
    document.printer.setjkmxpmmc(jkmxpmmc);
    document.printer.setjkmxkssl(jkmxkssl);
    document.printer.setjkmxjsje(jkmxjsje);
    document.printer.setsksssq(sksssq);    
    document.printer.setjkmxsjse(jkmxsjse);
    document.printer.setjkjehj(jkjehj);
    document.printer.setjkjehj_nu(jkjehj_nu);
    document.printer.setswjg(swjg);
    document.printer.setbz(bz);
    document.printer.setjkmxfcbl(jkmxfcbl);

    document.printer.startPrint();
}
</script>
</html>
