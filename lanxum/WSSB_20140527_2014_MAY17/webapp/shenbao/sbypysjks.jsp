<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
    String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<html>
<head>
<title>北京市地方税务局申报缴款单</title>
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
		 alert("创建DOC对象出错");
		 return; 
	}
	if(!xmlDoc.loadXML(xmlStr))
	{
		alert("装载xml数据出错");
		return false;
	}
	if(!xslDoc.load(xslPath))
	{
		alert("装载xsl数据出错");
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
            <jsp:param name="name" value="查看缴款书" />
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
					<img src="<%=static_contextpath%>images/dayin1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/dayin2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/dayin1.jpg'" alt="打印" onClick="goprint()" style="cursor:hand">
                    &nbsp;&nbsp;&nbsp;&nbsp;
					<img src="<%=static_contextpath%>images/fanhui1.jpg" onMouseOver="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onClick="doReturn()" style="cursor:hand">
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
//	var szdm = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/szmc").text; //税种代码
//	var tfrq = xmlDoc.selectSingleNode("taxdoc/sbsj/sbrq").text; //添发日期
	var sklx = xmlDoc.selectSingleNode("taxdoc/sbsj/sklx").text;//税款类型   
	var dnbh = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/jkpzh").text;//电脑编号
	var zclx = xmlDoc.selectSingleNode("taxdoc/sbsj/djzclxmc").text; //登记注册类型	
	var tfrqn = xmlDoc.selectSingleNode("taxdoc/sbsj/sbrqn").text; //填发日期年
	var tfrqy = xmlDoc.selectSingleNode("taxdoc/sbsj/sbrqy").text; //填发日期月
	var tfrqr = xmlDoc.selectSingleNode("taxdoc/sbsj/sbrqr").text; //填发日期日			
//	var lsgx = xmlDoc.selectSingleNode("taxdoc/sbsj/lsgxmc").text; //隶属关系   
	var zsjg = xmlDoc.selectSingleNode("//zsswjgzzjgmc").text;//税务机关
	 
	var jkdwdm = xmlDoc.selectSingleNode("//jsjdm").text;//缴款单位代码
//	var jkdwdh = xmlDoc.selectSingleNode("taxdoc/sbsj/lxdh").text; //缴款单位电话 
	var jkdwqc = xmlDoc.selectSingleNode("//nsrmc").text;//缴款单位全称 
	var jkdwkhyh = xmlDoc.selectSingleNode("//yhmc").text; //缴款单位开户银行 
	var jkdwzh = xmlDoc.selectSingleNode("//zh").text; //缴款单位账号
	var skgk = '('+xmlDoc.selectSingleNode("taxdoc/sbsj/gkzzjgdm").text+')'+xmlDoc.selectSingleNode("taxdoc/sbsj/gkzzjgmc").text; //收款国库	
	var skxjrq = xmlDoc.selectSingleNode("//xjrq").text;//税款限缴日期	

	var jkjehj = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/sjjedx").text;//缴款金额合计(TBD)
	var jkjehj_nu = '￥'+xmlDoc.selectSingleNode("taxdoc/sbsj/jks/sjje").text;
	var swjg = xmlDoc.selectSingleNode("taxdoc/sbsj/swjgzzjgmc").text;//税务机关(TBD)	
	var jkdw = xmlDoc.selectSingleNode("//nsrmc").text;//缴款单位(TBD)
	var bz = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/bz").text; //备注(TBD)
	var cNodeList = xmlDoc.selectSingleNode("//sbsj");
	
	var clength = cNodeList.childNodes.length;
	var node;
	var yskmbm = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/yskmdm").text; //预算科目编码
	var yskmmc = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/yskmmc").text;//预算科目名称 
	var yskmjc = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/ysjcmc").text;//预算科目级次 	
	var jkmxpmmc = '';//缴款明细品目名称(TBD)
	var jkmxkssl = '';//缴款明细课税数量(TBD)
	var jkmxjsje = ''; //缴款明细计税金额(TBD)
	var sksssq = xmlDoc.selectSingleNode("taxdoc/sbsj/jks/skssksrq").text+"-"+xmlDoc.selectSingleNode("taxdoc/sbsj/jks/skssjsrq").text;//税款所属时期	
	var jkmxsjse = '';//缴款明细实缴税额(TBD)
	/*
	* 根据收入规划核算处要求，重新查询预算级次名称，预算级次显示为中央级、市级、区级
	* gaoyh added jkmxfcbl by 20131220
	*/
	var jkmxfcbl = '';//缴款明细分成比例(TBD)
	
	var sjse='';
	var tmp = '';
	for(var i=0;i<clength;i++){
		node = cNodeList.childNodes[i];
		for(var ii=0;ii<node.childNodes.length;ii++){
			tmp = node.childNodes[ii];
//税收票证升级项目取消缴款书税种税目代码的显示及打印
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
