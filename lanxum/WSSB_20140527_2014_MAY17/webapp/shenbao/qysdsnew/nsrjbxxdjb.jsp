<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ page import="com.ttsoft.bjtax.shenbao.constant.CodeConstant"%>
<%String static_contextpath = com.ttsoft.common.util.ResourceLocator
					.getStaticFilePath(request);
					
String _REQ_KEY_TEST_ORG_USE = (String)request.getAttribute("_REQ_KEY_TEST_ORG_USE");

String _REQ_KEY_TEST_ORG_TEST = (String)request.getAttribute("_REQ_KEY_TEST_ORG_TEST");

String _REQ_KEY_TEST_ORG_MSG = (String)request.getAttribute("_REQ_KEY_TEST_ORG_MSG");



			%>
<html>
	<head>
		<title>
			纳税人基本信息登记表
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
	//var strXml = '<taxdoc><xsltVersion><![CDATA[20061102]]></xsltVersion><schemaVersion><![CDATA[20061102]]></schemaVersion><ywlx><![CDATA[010027]]></ywlx><ywczlx><![CDATA[0]]></ywczlx><nsrjbxx><jsjdm><![CDATA[06000100]]></jsjdm><nsrsbh><![CDATA[11010880207313X000]]></nsrsbh><nsrmc><![CDATA[北京惠新志达科贸有限公司]]></nsrmc><sbnd><![CDATA[2006]]></sbnd><sknd><![CDATA[2005]]></sknd><ssjjlxdm><![CDATA[173]]></ssjjlxdm><ssjjlxmc><![CDATA[私营有限责任公司]]></ssjjlxmc><lxdh><![CDATA[88511275]]></lxdh><jydz><![CDATA[海淀马神庙1号一区核工业第二研究设计院情报中心1112#]]></jydz><sshydm><![CDATA[7600]]></sshydm><sshymc><![CDATA[专业技术服务业]]></sshymc><zsfsdm><![CDATA[03]]></zsfsdm><zsfsmc><![CDATA[查帐征收]]></zsfsmc><cwkjzddm><![CDATA[12]]></cwkjzddm><cwkjzdmc><![CDATA[12]]></cwkjzdmc><cwkjzddm_old><![CDATA[12]]></cwkjzddm_old><gzglxsdm><![CDATA[01]]></gzglxsdm><gzglxsmc><![CDATA[]]></gzglxsmc><gzglxsdm_old><![CDATA[01]]></gzglxsdm_old><jmlxdm><![CDATA[]]></jmlxdm><jmlxmc><![CDATA[]]></jmlxmc><jmlxdm_old><![CDATA[]]></jmlxdm_old><swjgzzjgdm><![CDATA[0601]]></swjgzzjgdm><swjgzzjgmc><![CDATA[海淀区地方税务局知春里所]]></swjgzzjgmc><cjr><![CDATA[HDFJCSYH]]></cjr><cjrq><![CDATA[2006-12-10]]></cjrq><lrr><![CDATA[06000100]]></lrr><lrrq><![CDATA[2006-12-10]]></lrrq><sbrq><![CDATA[]]></sbrq><xtjb><![CDATA[]]></xtjb><bbmsf><![CDATA[1,2,5,8,9,10,11,12,13,14,15,16,17,19,20]]></bbmsf><skssksrq><![CDATA[2005-01-01 ]]></skssksrq><skssjsrq><![CDATA[2005-12-31 ]]></skssjsrq><version><![CDATA[20060101]]></version><sqspbh><![CDATA[]]></sqspbh></nsrjbxx></taxdoc>';
	var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
	var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
	var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
	var xmlDoc;
	
	//解析xml
	function parseXmlOnLoad(){
		
		var js_REQ_KEY_TEST_ORG_USE = '<%=_REQ_KEY_TEST_ORG_USE%>';
		
		var js_REQ_KEY_TEST_ORG_TEST = '<%=_REQ_KEY_TEST_ORG_TEST%>';
		
		if(js_REQ_KEY_TEST_ORG_USE == '1'){
			
			if(js_REQ_KEY_TEST_ORG_TEST == '1'){
				alert('<%=_REQ_KEY_TEST_ORG_MSG%>');
			}else{
				alert('<%=_REQ_KEY_TEST_ORG_MSG%>');
				return false;
			}
			
		}
		
		var urlXSL="/XSLTWEB/model/010027/XSLT/" +strXSLTVersion+".xsl";
		//var urlXSL="shenbao/qysdsnew/nsrjbxxdjb.xsl";
	    if (strXml != ""){
	        if (! parseXml(strXml,urlXSL,"result"))
	            return false;
			xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
			xmlDoc.async = false;
			xmlDoc.loadXML(strXml);
	    }
	    
	    initNsrjbxx();
	
		return true;
	}
	
	function initNsrjbxx() {
	
		var cwkjzddm = document.getElementById("cwkjzddm").value;
		var cwkjzddm_old = document.getElementById("cwkjzddm_old").value;
		
		if (cwkjzddm == null || "" == cwkjzddm) { 
		
			document.getElementsById("cwkjzddm12").checked = true;
			
		} else { 
			
			var cwkjzddmid = "cwkjzddm" + cwkjzddm;
			
			//alert(cwkjzddmid);
			
			document.getElementById(cwkjzddmid).checked = true;
		}	
		
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
						if(tmp>100000){
							alert("资产总额金额超过10亿元，请核实！");
						}	
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
				    if(tmp>100000){
							alert("注册资本金额超过10亿元，请核实！");
						}	
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


	function getPostXml(objForm){	
		var retstr;
		
		setNsrjbxx();
		
		//基本数据
		getBasicXx("xsltVersion","/taxdoc");
		getBasicXx("schemaVersion","/taxdoc");
		getBasicXx("ywlx","/taxdoc");
		getBasicXx("ywczlx","/taxdoc");

		//纳税人基本信息
		applendElement("/taxdoc","nsrjbxx",["jsjdm","nsrsbh","nsrmc","sbnd","sknd","ssjjlxdm","ssjjlxmc","jydz",
						"sshydm","sshymc","zsfsdm","zsfsmc","cwkjzdmc","cwkjzddm_old","gzglxsmc","gzglxsdm_old",
						"jmlxmc","jmlxdm_old","swjgzzjgdm","swjgzzjgmc","cjr","cjrq","lrr","lrrq","sbrq","xtjb",
						"bbmsf","skssksrq","skssjsrq","version","sqspbh","lxdh","cwkjzddm","gzglxsdm","jmlxdm","zczbje","zcze"]);
		
		//去掉末尾自动添加的回车
		retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
		retstr = retstr.substr(0,retstr.length-2);
		
		return retstr;
	}
	
	function setNsrjbxx() {
		
		var i = 0 ;
		var length = 0;
		
		var cwkjzddm_show = "12";
		length = document.forms[0].cwkjzddm_show.length;		
		for (i = 0; i < length; i ++) {
			
			if (document.forms[0].cwkjzddm_show[i].checked) {
			
				cwkjzddm_show = document.forms[0].cwkjzddm_show[i].value;
				
			}
		
		}
		//alert("cwkjzddm_show:"+ cwkjzddm_show);	
		document.getElementById("cwkjzddm").value = cwkjzddm_show;
		
	}
	
	
	function changeLocalYwczlx(ywczlx){
		var rootNode = xmlDoc.documentElement;
	    var objCDATA =xmlDoc.createCDATASection(ywczlx);
			rootNode.selectSingleNode("//ywczlx").text = "";
			rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
	}
	
	    //响应计算机代码的回车查询
	function jsjdmQuery(){
		if(event.keyCode==13) 
			event.keyCode = 9;
	}
	
	function doSubmit(actionType){
		var form=document.forms[0];
		if(actionType=='Save'){
			doSave();
		}else{
			doReturn();
		}
	}

	//mass 修改 2013-12-6
	function doSave()
	{
		
		var old  = document.forms[0].ywczlx.value;
		//税源为总的不能申报起止日期
		var sbrq_q = "20140101";
		var sbrq_z = "20140201";
		//获取用户老的表id
		var bbmsf_old=document.forms[0].bbmsf.value;
		var bbmsf_new="";
		//税源标识
		var sybs=document.forms[0].sybs.value;
		//申报日期
		var sbrq=document.forms[0].sbrq.value;
		if(sbrq>=sbrq_q){
			var cwkjzddm = "";
			var cwkjzddm_show = document.getElementsByName("cwkjzddm_show");
			for(var i=0;i<3;i++){
				if(cwkjzddm_show[i].checked){
					cwkjzddm =cwkjzddm_show[i].value;
				}
			}
			if(cwkjzddm==<%=CodeConstant.CWKJZD01 %>){
				bbmsf_new="<%=CodeConstant.BBMSF10_2008 %>";
			}else if (cwkjzddm==<%=CodeConstant.CWKJZD02 %>){
				bbmsf_new="<%=CodeConstant.BBMSF20_2008 %>";
			}else if (cwkjzddm==<%=CodeConstant.CWKJZD03 %>) {
				bbmsf_new="<%=CodeConstant.BBMSF30_2008 %>";
			}
				
			if(sybs!=<%=CodeConstant.CODE_QYSDS_SYBS_OTHER %>){
				//当税源标识为”总“时
				if(sybs==<%=CodeConstant.CODE_QYSDS_SYBS_Z %>){
					if(sbrq<sbrq_z){
						alert("请在2014年2月1日（含）之后填写基本信息登记表");
					}else{
						bbmsf_new +="<%=CodeConstant.BBMSF17_2012 %>";
						doSumbit2013Ts(bbmsf_old,bbmsf_new);
					}
				}
				if(sybs==<%=CodeConstant.CODE_QYSDS_SYBS_F %>){
					alert("您企业只需填报企业所得税分支机构年度纳税申报表！");
				}
				if(sybs==<%=CodeConstant.CODE_QYSDS_SYBS_D %>){
					doSumbit2013Ts(bbmsf_old,bbmsf_new);
				}
			}else{
				alert("您企业的企业所得税不由地方税务局管辖。");
			}
		}else{
			SaveExec(old);
		}
		
	    return false; 
	}
	//mass 2013-12-6增加
	//提交数据进行判断是否提示
	function doSumbit2013Ts(bbmsf_old,bbmsf_new){
		var old  = document.forms[0].ywczlx.value;
		var queryFlag  = document.forms[0].queryFlag.value;
		var ywts="您企业已进行"+document.forms[0].sknd.value+"年度企业所得税汇算清缴申报，本次变更将清除当期申报数据，是否清除?";
		if(queryFlag =="1" && bbmsf_new!=bbmsf_old){
  			if(confirm(ywts)){
  				SaveExec(old);
  			}
  		}else{
  			SaveExec(old);
  		}
	}
	
	function doReturn(){
	    if(confirm(confirmReturn)){
			document.forms[0].actionType.value="Return";
				document.forms[0].submit();
					return true;
	    }else{
	    	return false;
	    }
	}
	
	
	function SaveExec(old)
	{
		if (confirm ("请如实填写基本信息表，如果填写错误可能会导致某些报表重复录入")) 
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
		
		}
		return true;
	}
	
	</script>
	
	<link href="<%=static_contextpath%>/css/text.css" rel="stylesheet" type="text/css">
	<link href="<%=static_contextpath%>/css/top-box.css" rel="stylesheet" type="text/css">
	<link href="css/beijing.css" rel="stylesheet" type="text/css">
	
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
 
	<form name="form1" method="post" action="czzsqyjbxxb.dc">
		<input name="actionType" type="hidden" id="actionType" value="Show">
	
		<table width="96%" align="center" cellspacing="0" class="table-99">
			<tr>
				<td align="center" valign="top" colspan="7">
					<jsp:include page="../../include/SbHeader.jsp" flush="true">
						<jsp:param name="name" value="纳税人基本信息登记表" />
						<jsp:param name="help_url" value="help/wssb/sbzl/lygfzwhztz/qysdsjd/qysdsjd-000.htm" />
					</jsp:include>
				</td>
			</tr>
			<tr>
				<td colspan="7">
					<br>
					<div id="result" align="center"></div>
				</TD>
			</TR>
			
			<tr>
				<td colspan="7">
					<br>
					<div align="left" style="color:red;">请更新企业所得税离线申报客户端软件</div>
				</TD>
			</TR>
			<tr>
				<td colspan="7">
					<div align="left" >
					<% 
					String[] link_array = (String[])(request.getAttribute("REQUEST_LINK_QYSDS")==null?"":request.getAttribute("REQUEST_LINK_QYSDS"));
					int count = link_array.length;
					int i = 0;
					for (i = 0; i < count; i++) {%>
					
					<A HREF="<%= link_array[i] %>" target="_blank">离线申报客户端软件下载地址<%=i+1 %></A><BR>
					
					<%} %>
										
					</div>
				</TD>
			</TR>
			<TR class="black9">
				<TD>
					<div  align="center">
					
					<% 
					
					boolean showSave = false;
					
					if(_REQ_KEY_TEST_ORG_USE == null || !"1".equals(_REQ_KEY_TEST_ORG_USE)){
						showSave = true;
					}
					
					
					if(_REQ_KEY_TEST_ORG_USE != null && "1".equals(_REQ_KEY_TEST_ORG_USE) && _REQ_KEY_TEST_ORG_TEST != null && "1".equals(_REQ_KEY_TEST_ORG_TEST)){
						showSave = true;
					}
					
					if(showSave) {
						
					%>
					
						<a style="cursor:hand" tabIndex="-1" onClick="javascript:return doSubmit('Save');return false;">
							<img name="spage" value="保存" alt="保存" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>/images/baocun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/baocun1.jpg" width="79" height="22" id="baocun">
						</a>
						
					<%}%>
						
						<a style="cursor:hand" tabIndex="-1" onClick="javascript:return doSubmit('Return');return false;">
							<img name="bpage" value="返回" alt="返回" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/fanhui1.jpg" width="79" height="22" id="fanhui">
						</a>
					</div>
					<div align="left">
					<font size="2">&nbsp;&nbsp;&nbsp;填报要求：纳税人年度申报企业所得税时须填报此表。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;填报说明：</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、纳税人名称：填报税务登记证所载纳税人的全称。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、计算机代码：填写地税机关核发的征收管理码。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、联系电话：填写纳税人单位办税人员联系电话（或手机号码）。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、所属经济类型、所属行业：按照税务登记表中的有关内容填写。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5、企业所得税征收方式：选择核定征收的企业，是指由税务机关根据其生产经营情况或财务会计核算情况，按照规定的标准、程序、权限和方法，核定应税所得率（纯益率）或应纳税额的一种征收方式。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6、企业类别：</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一般企业：是指除金融企业，事业单位、社会团体、民办非企业单位以外的企业。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金融企业：是指执行《金融企业会计制度》、《企业会计准则》的商业银行、政策银行、保险公司、证券公司、信托投资公司、租赁公司、担保公司、财务公司、典当公司等金融企业。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;事业单位、社会团体、民办非企业单位：是指执行《事业单位会计准则》或《民间非营利组织会计制度》的企业或单位。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7、注册资本：企业法人按照企业法人营业执照上的注册资本填写；事业单位按照《事业单位法人证书》的开办资金填写；社会团体按照《社会团体法人登记证书》的注册资金填写；民办非企业法人单位按照《民办非企业单位登记证书》的开办资金填写。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;资产总额：指企业拥有或控制的全部资产。包括流动资产、长期投资、固定资产、无形及递延资产、其他长期资产等，即为企业资产负债表的资产总计项。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;8、表中所列单项选择项：在符合选项的□中划"√"。</font><br>
					</div>
				</TD>
			</TR>
		</table>
		<br>
		<jsp:include page="../../include/bottom.jsp" flush="true"/>
	
	</form>

	</body>
</html>
