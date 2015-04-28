<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ page import="com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.QysdsNbConstant2014"%>
<%@ page import="com.ttsoft.bjtax.shenbao.constant.CodeConstant"%>
<%@ page import="com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement.CheckJdlx"%>
<%
    String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
	System.out.println("======================123=============================");
%>
<html>
<head>
<title>企业所得税分支机构年度纳税申报表</title>
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
			   'yysr', 'yycb', 'lrze', 'tdjsynssde', 'bzsr', 'mssr','jmzynssde','mbyqndks', 'sjlre', 'sl', 'ynsdse', 
			   'jmsdse', 'xwqyjmsdse','sjyyjsdse', 'tdywyjsdse', 'ybtsdse', 'yqnddjsdse', 'bqsjybtsdse', 
			   'zjgyftsdse', 'czjzfpsdse', 'fzjgyftsdse', 'zjgdlscjybmyftsdse', 'zjgycxfzjgyftsdse', 'fpbl', 'fpsdse','zczbje','zcze'];
var InputNameAl=['纳税方式','总分机构',
				 '营业收入','营业成本', '利润总额', '加：特定业务计算的应纳税所得额','减：不征税收入', '免税收入','减征、免征应纳税所得额', '弥补以前年度亏损', '实际利润额', '税率','应纳所得税额', 
				 '减：减免所得税额','其中：符合条件的小型微利企业减免所得税额', '减：实际已预缴所得税额', '减：特定业务预缴（征）所得税额', '应补（退）所得税额', '减：以前年度多缴在本期抵缴所得税额', '本期实际应补（退）所得税额', 
				 '总机构应分摊所得税额', '财政集中分配所得税额', '分支机构应分摊所得税额', '其中：总机构独立生产经营部门应分摊所得税额', '总机构已撤销分支机构应分摊所得税额', '分配比例', '分配所得税额','注册资本(万元，必填项)','资产总额(万元，必填项)'];
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xmlDoc;


//alert("strXSLTVersion==="+strXSLTVersion);
//解析xml
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
	applendElement("/taxdoc","sbxx",["fsdm","jd","nd","sbrq","skssksrq","skssjsrq","zgfwjdlx"]);
	//申报数据
	applendElement("/taxdoc","sbsj",["nsfs", "zfjg", "yysr", "yycb", "lrze", "tdjsynssde", "bzsr", "mssr", "jmzynssde","mbyqndks", "sjlre", "sl", "ynsdse", "jmsdse","xwqyjmsdse", "sjyyjsdse", "tdywyjsdse", "ybtsdse", "yqnddjsdse", "bqsjybtsdse", "zjgyftsdse", "czjzfpsdse", "fzjgyftsdse", "zjgdlscjybmyftsdse", "zjgycxfzjgyftsdse", "fpbl", "fpsdse","zczbje","zcze","queryFlag"]);
	
	//去掉末尾自动添加的回车
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	return retstr;
}


function setInput(){
	//alert("1");
	//初始化RADIO域
	initSelect();
	//alert("2");
	//初始化RADIO域可访问项
	changeSelect();
	//alert("3");
	//初始化行次10税率
	document.forms[0].sl.value="25.00";
	//alert("4");
	//初始化不可使用域
	checkFilter();
	//alert("5");

	//检查是否保存成功，如果保存成功则建议跳转
	<%if("1".equals((String)request.getAttribute(QysdsNbConstant2014.JUMP_FLAG_NAME))){%>
		//if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
		if(document.forms[0].lje_nsfs[0].checked==1){
			//保存成功，提示转总分机构申报表
			//if(confirm("保存成功，是否转入《企业所得税汇总纳税分支机构分配表》？")){
				alert("保存成功，将转入《企业所得税汇总纳税分支机构分配表》");
				doSubmit("Jump");
			//}
		}else if(document.forms[0].lje_nsfs[1].checked==1){
			alert("申报已成功！");
		}
		
	<%
	}
	%>
	
}

	<%/*根据初始化隐藏域为单选框赋值并初始化隐藏域*/%>
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
		
		//获取鉴定类型
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

	<%/*根据选择设置单选框选择情况*/%>
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
			//选择为独立纳税的，清除汇总纳税方式子项选择，同时将这些子项置位不可编辑
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
			//选择为总机构时，开放其子项选择权限
			document.forms[0].lje_zfjg[0].disabled = true;
			document.forms[0].lje_zfjg[1].disabled = true;
			//
			if(document.forms[0].lje_zfjg[0].checked==1){
				document.forms[0].zfjg.value=document.forms[0].lje_zfjg[0].value;
			}else if(document.forms[0].lje_zfjg[1].checked==1){
				document.forms[0].zfjg.value=document.forms[0].lje_zfjg[1].value;
			}
		}else{
			//默认情况下为独立纳税
			document.forms[0].lje_nsfs[0].checked=0;
			document.forms[0].lje_nsfs[1].checked=1;
			//选择为独立纳税的，清除汇总纳税方式子项选择，同时将这些子项置位不可编辑
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
		//独立纳税
		if(document.forms[0].lje_nsfs[1].checked==true)
		{
			document.forms[0].yysr.disabled=false; //2
			document.forms[0].yycb.disabled=false; //3
			document.forms[0].lrze.disabled=false; //4
			document.forms[0].tdjsynssde.disabled=false; //5
			document.forms[0].bzsr.disabled=false; //6
			document.forms[0].mssr.disabled=false; //7
			  
			document.forms[0].jmzynssde.disabled=false; //8
			
			document.forms[0].mbyqndks.disabled=false; //9
			document.forms[0].sjlre.disabled=false; //10
			document.forms[0].sl.disabled=false; //11
			document.forms[0].ynsdse.disabled=false; //12
			document.forms[0].jmsdse.disabled=false; //13
		
//			document.forms[0].xwqyjmsdse.disabled=false;//14
			
			document.forms[0].sjyyjsdse.disabled=false;//15
			document.forms[0].tdywyjsdse.disabled=false; //16
			document.forms[0].ybtsdse.disabled=false;  //17
			document.forms[0].yqnddjsdse.disabled=false; //18
			document.forms[0].bqsjybtsdse.disabled=false; //19
			document.forms[0].zjgyftsdse.disabled=true; //30
			document.forms[0].czjzfpsdse.disabled=true; //31
			document.forms[0].fzjgyftsdse.disabled=true; //32
			document.forms[0].zjgdlscjybmyftsdse.disabled=true; //33
//			document.forms[0].zjgycxfzjgyftsdse.disabled=true; //30
			document.forms[0].fpbl.disabled=true; //34
			document.forms[0].fpsdse.disabled=true; //35


			//将被屏蔽行次值清空
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
			
			if(document.forms[0].bqsjybtsdse.value == null || document.forms[0].bqsjybtsdse.value == "")
			{
				document.forms[0].bqsjybtsdse.value="0.00";
			}
			
			if(document.forms[0].xwqyjmsdse.value == null || document.forms[0].xwqyjmsdse.value == "")
			{
				document.forms[0].xwqyjmsdse.value="0.00";
			}
			
			if(document.forms[0].jmzynssde.value == null || document.forms[0].jmzynssde.value == "")
			{
				document.forms[0].jmzynssde.value="0.00";
			}
			
			document.forms[0].zjgyftsdse.value="";
			document.forms[0].czjzfpsdse.value="";
			document.forms[0].fzjgyftsdse.value="";
			document.forms[0].zjgdlscjybmyftsdse.value="";
//			document.forms[0].zjgycxfzjgyftsdse.value="";
			document.forms[0].fpbl.value="";
			document.forms[0].fpsdse.value="";
			
			document.forms[0].yysr.style.backgroundColor=""; //2
			document.forms[0].yycb.style.backgroundColor=""; //3
			document.forms[0].lrze.style.backgroundColor=""; //4
			document.forms[0].tdjsynssde.style.backgroundColor=""; //5
			document.forms[0].bzsr.style.backgroundColor=""; //6
			document.forms[0].mssr.style.backgroundColor=""; //7
			document.forms[0].jmzynssde.style.backgroundColor=""; //8
			document.forms[0].mbyqndks.style.backgroundColor=""; //9
			document.forms[0].sjlre.style.backgroundColor=""; //10
			document.forms[0].sl.style.backgroundColor=""; //11
			document.forms[0].ynsdse.style.backgroundColor=""; //12
			document.forms[0].jmsdse.style.backgroundColor=""; //13
			document.forms[0].xwqyjmsdse.style.backgroundColor=""; //14
			document.forms[0].sjyyjsdse.style.backgroundColor="";　//15
			document.forms[0].tdywyjsdse.style.backgroundColor=""; //16
			document.forms[0].ybtsdse.style.backgroundColor="";  //17
			document.forms[0].yqnddjsdse.style.backgroundColor=""; //18
			document.forms[0].bqsjybtsdse.style.backgroundColor=""; //19
			document.forms[0].zjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].czjzfpsdse.style.backgroundColor="#aaaaaa"; //31
			document.forms[0].fzjgyftsdse.style.backgroundColor="#aaaaaa"; //32
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor="#aaaaaa"; //33
//			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa"; //34
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa"; //35

		}
		//汇总纳税－总机构
		else if(document.forms[0].lje_nsfs[0].checked==true&&document.forms[0].lje_zfjg[0].checked==true){
			document.forms[0].yysr.disabled=false; //2
			document.forms[0].yycb.disabled=false; //3
			document.forms[0].lrze.disabled=false; //4
			document.forms[0].tdjsynssde.disabled=false; //5
			document.forms[0].bzsr.disabled=false; //6
			document.forms[0].mssr.disabled=false; //7
			document.forms[0].jmzynssde.disabled=false; //8
			document.forms[0].mbyqndks.disabled=false; //9
			document.forms[0].sjlre.disabled=false; //10
			document.forms[0].sl.disabled=false; //11
			document.forms[0].ynsdse.disabled=false; //12
			document.forms[0].jmsdse.disabled=false; //13
//			document.forms[0].xwqyjmsdse.disabled=false;//14
			document.forms[0].sjyyjsdse.disabled=false;//15
			document.forms[0].tdywyjsdse.disabled=false; //16
			document.forms[0].ybtsdse.disabled=false;  //17
			document.forms[0].yqnddjsdse.disabled=false; //18
			document.forms[0].bqsjybtsdse.disabled=false; //19
			document.forms[0].zjgyftsdse.disabled=false; //30
			document.forms[0].czjzfpsdse.disabled=false; //31
			document.forms[0].fzjgyftsdse.disabled=false; //32
			document.forms[0].zjgdlscjybmyftsdse.disabled=false; //33
//			document.forms[0].zjgycxfzjgyftsdse.disabled=false; //30
			document.forms[0].fpbl.disabled=true; //34
			document.forms[0].fpsdse.disabled=true; //35

			//将被屏蔽行次值清空
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
//			if(document.forms[0].zjgycxfzjgyftsdse.value == null || document.forms[0].zjgycxfzjgyftsdse.value == "")
//			{
//				document.forms[0].zjgycxfzjgyftsdse.value="0.00";
//			}


			if(document.forms[0].xwqyjmsdse.value == null || document.forms[0].xwqyjmsdse.value == "")
			{
				document.forms[0].xwqyjmsdse.value="0.00";
			}
			
			if(document.forms[0].jmzynssde.value == null || document.forms[0].jmzynssde.value == "")
			{
				document.forms[0].jmzynssde.value="0.00";
			}

			document.forms[0].fpbl.value="";
			document.forms[0].fpsdse.value="";
			
			document.forms[0].yysr.style.backgroundColor=""; //2
			document.forms[0].yycb.style.backgroundColor=""; //3
			document.forms[0].lrze.style.backgroundColor=""; //4
			document.forms[0].tdjsynssde.style.backgroundColor=""; //5
			document.forms[0].bzsr.style.backgroundColor=""; //6
			document.forms[0].mssr.style.backgroundColor=""; //7
			document.forms[0].jmzynssde.style.backgroundColor=""; //8
			document.forms[0].mbyqndks.style.backgroundColor=""; //9
			document.forms[0].sjlre.style.backgroundColor=""; //10
			document.forms[0].sl.style.backgroundColor=""; //11
			document.forms[0].ynsdse.style.backgroundColor=""; //12
			document.forms[0].jmsdse.style.backgroundColor=""; //13
			document.forms[0].xwqyjmsdse.style.backgroundColor=""; //14
			document.forms[0].sjyyjsdse.style.backgroundColor="";//15
			document.forms[0].tdywyjsdse.style.backgroundColor=""; //16
			document.forms[0].ybtsdse.style.backgroundColor="";  //17
			document.forms[0].yqnddjsdse.style.backgroundColor=""; //18
			document.forms[0].bqsjybtsdse.style.backgroundColor=""; //19
			document.forms[0].zjgyftsdse.style.backgroundColor=""; //30
			document.forms[0].czjzfpsdse.style.backgroundColor=""; //31
			document.forms[0].fzjgyftsdse.style.backgroundColor=""; //32
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor=""; //33
//			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor=""; //30
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa"; //34
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa"; //35

		}
		//汇总纳税－分支机构
		else if(document.forms[0].lje_nsfs[0].checked==true&&document.forms[0].lje_zfjg[1].checked==true){
			//alert("3");
			document.forms[0].yysr.disabled=true; //2
			document.forms[0].yycb.disabled=true; //3
			document.forms[0].lrze.disabled=true; //4
			document.forms[0].tdjsynssde.disabled=true; //5
			document.forms[0].bzsr.disabled=true; //6
			document.forms[0].mssr.disabled=true; //7
			document.forms[0].jmzynssde.disabled=true; //8
			document.forms[0].mbyqndks.disabled=true; //9
			document.forms[0].sjlre.disabled=true; //10
			document.forms[0].sl.disabled=true; //11
			document.forms[0].ynsdse.disabled=true; //12
			document.forms[0].jmsdse.disabled=true; //13
			document.forms[0].xwqyjmsdse.disabled=true; //14
			document.forms[0].sjyyjsdse.disabled=true;//15
			document.forms[0].tdywyjsdse.disabled=true; //16
			document.forms[0].ybtsdse.disabled=true;  //17
			document.forms[0].yqnddjsdse.disabled=true; //18
			document.forms[0].bqsjybtsdse.disabled=true; //19
			document.forms[0].zjgyftsdse.disabled=true; //30
			document.forms[0].czjzfpsdse.disabled=true; //31
			document.forms[0].fzjgyftsdse.disabled=false; //32
			document.forms[0].zjgdlscjybmyftsdse.disabled=true; //33
//			document.forms[0].zjgycxfzjgyftsdse.disabled=true; //30
			document.forms[0].fpbl.disabled=false; //34
			document.forms[0].fpsdse.disabled=false; //35
			
			//将被屏蔽行次值清空
			document.forms[0].yysr.value=""; //2
			document.forms[0].yycb.value=""; //3
			document.forms[0].lrze.value=""; //4
			document.forms[0].tdjsynssde.value=""; //5
			document.forms[0].bzsr.value=""; //6
			document.forms[0].mssr.value=""; //7
			document.forms[0].jmzynssde.value=""; //8
			document.forms[0].mbyqndks.value=""; //9
			document.forms[0].sjlre.value=""; //10
			//document.forms[0].sl.value=""; //11
			document.forms[0].ynsdse.value=""; //12
			document.forms[0].jmsdse.value=""; //13
			document.forms[0].xwqyjmsdse.value="";//14
			document.forms[0].sjyyjsdse.value="";//15
			document.forms[0].tdywyjsdse.value=""; //16
			document.forms[0].ybtsdse.value="";  //17
			document.forms[0].yqnddjsdse.value=""; //18
			document.forms[0].bqsjybtsdse.value=""; //19
			document.forms[0].zjgyftsdse.value=""; //30
			document.forms[0].czjzfpsdse.value=""; //31
			document.forms[0].zjgdlscjybmyftsdse.value=""; //33
//			document.forms[0].zjgycxfzjgyftsdse.value=""; //30
			
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
			

			
			document.forms[0].xwqyjmsdse.value="";
			document.forms[0].jmzynssde.value="";
			document.forms[0].xwqyjmsdse.style.backgroundColor="#aaaaaa"; //2
			document.forms[0].jmzynssde.style.backgroundColor="#aaaaaa"; //3
			
			document.forms[0].yysr.style.backgroundColor="#aaaaaa"; //2
			document.forms[0].yycb.style.backgroundColor="#aaaaaa"; //3
			document.forms[0].lrze.style.backgroundColor="#aaaaaa"; //4
			document.forms[0].tdjsynssde.style.backgroundColor="#aaaaaa"; //5
			document.forms[0].bzsr.style.backgroundColor="#aaaaaa"; //6
			document.forms[0].mssr.style.backgroundColor="#aaaaaa"; //7
			document.forms[0].mbyqndks.style.backgroundColor="#aaaaaa"; //9
			document.forms[0].sjlre.style.backgroundColor="#aaaaaa"; //10
			document.forms[0].sl.style.backgroundColor="#aaaaaa"; //11
			document.forms[0].ynsdse.style.backgroundColor="#aaaaaa"; //12
			document.forms[0].jmsdse.style.backgroundColor="#aaaaaa"; //13
			document.forms[0].sjyyjsdse.style.backgroundColor="#aaaaaa";　//15
			document.forms[0].tdywyjsdse.style.backgroundColor="#aaaaaa"; //14
			document.forms[0].ybtsdse.style.backgroundColor="#aaaaaa";  //17
			document.forms[0].yqnddjsdse.style.backgroundColor="#aaaaaa"; //18
			document.forms[0].bqsjybtsdse.style.backgroundColor="#aaaaaa"; //19
			document.forms[0].zjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].czjzfpsdse.style.backgroundColor="#aaaaaa"; //31
			document.forms[0].fzjgyftsdse.style.backgroundColor=""; //32
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor="#aaaaaa"; //33
//			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].fpbl.style.backgroundColor=""; //34
			document.forms[0].fpsdse.style.backgroundColor=""; //35

		}else{
			document.forms[0].yysr.disabled=false; //2
			document.forms[0].yycb.disabled=false; //3
			document.forms[0].lrze.disabled=false; //4
			document.forms[0].tdjsynssde.disabled=false; //5
			document.forms[0].bzsr.disabled=false; //6
			document.forms[0].mssr.disabled=false; //7
			document.forms[0].jmzynssde.disabled=false; //8
			document.forms[0].mbyqndks.disabled=false; //9
			document.forms[0].sjlre.disabled=false; //10
			document.forms[0].sl.disabled=false; //11
			document.forms[0].ynsdse.disabled=false; //12
			document.forms[0].jmsdse.disabled=false; //13
//			document.forms[0].xwqyjmsdse.disabled=false;//14
			document.forms[0].sjyyjsdse.disabled=false;//15
			document.forms[0].tdywyjsdse.disabled=false; //16
			document.forms[0].ybtsdse.disabled=false;  //17
			document.forms[0].yqnddjsdse.disabled=false; //18
			document.forms[0].bqsjybtsdse.disabled=false; //19
			document.forms[0].zjgyftsdse.disabled=true; //30
			document.forms[0].czjzfpsdse.disabled=true; //31
			document.forms[0].fzjgyftsdse.disabled=true; //32
			document.forms[0].zjgdlscjybmyftsdse.disabled=true; //33
//			document.forms[0].zjgycxfzjgyftsdse.disabled=true; //30
			document.forms[0].fpbl.disabled=true; //34
			document.forms[0].fpsdse.disabled=true; //35
			
			document.forms[0].yysr.style.backgroundColor=""; //2
			document.forms[0].yycb.style.backgroundColor=""; //3
			document.forms[0].lrze.style.backgroundColor=""; //4
			document.forms[0].tdjsynssde.style.backgroundColor=""; //5
			document.forms[0].bzsr.style.backgroundColor=""; //6
			document.forms[0].mssr.style.backgroundColor=""; //7
			document.forms[0].jmzynssde.style.backgroundColor=""; //8
			document.forms[0].mbyqndks.style.backgroundColor=""; //9
			document.forms[0].sjlre.style.backgroundColor=""; //10
			document.forms[0].sl.style.backgroundColor=""; //11
			document.forms[0].ynsdse.style.backgroundColor=""; //12
			document.forms[0].jmsdse.style.backgroundColor=""; //13
			document.forms[0].xwqyjmsdse.style.backgroundColor="";//14
			document.forms[0].sjyyjsdse.style.backgroundColor="";//15
			document.forms[0].tdywyjsdse.style.backgroundColor=""; //16
			document.forms[0].ybtsdse.style.backgroundColor="";  //17
			document.forms[0].yqnddjsdse.style.backgroundColor=""; //18
			document.forms[0].bqsjybtsdse.style.backgroundColor=""; //19
			document.forms[0].zjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].czjzfpsdse.style.backgroundColor="#aaaaaa"; //31
			document.forms[0].fzjgyftsdse.style.backgroundColor="#aaaaaa"; //32
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor="#aaaaaa"; //33
//			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa"; //34
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa"; //35
		}
		//alert("4");
		readOnlyFilter();
	}

	function readOnlyFilter(){
		//alert("readOnlyFilter");
		//独立纳税
		if(document.forms[0].lje_nsfs[1].checked==true){
			document.forms[0].yysr.readOnly=false; //2
			document.forms[0].yycb.readOnly=false; //3
			document.forms[0].lrze.readOnly=false; //4
			document.forms[0].tdjsynssde.readOnly=false; //5
			document.forms[0].bzsr.readOnly=false; //6
			document.forms[0].mssr.readOnly=false; //7
			document.forms[0].jmzynssde.readOnly=false; //8
			document.forms[0].mbyqndks.readOnly=false; //9
			document.forms[0].sjlre.readOnly=true; //10
			document.forms[0].sl.readOnly=true; //11
			document.forms[0].ynsdse.readOnly=true; //12
			document.forms[0].jmsdse.readOnly=false; //13
//			document.forms[0].xwqyjmsdse.readOnly=true;//14
			document.forms[0].sjyyjsdse.readOnly=false;//15
			document.forms[0].tdywyjsdse.readOnly=false; //16
			document.forms[0].ybtsdse.readOnly=true;  //17
			document.forms[0].yqnddjsdse.readOnly=false; //18
			document.forms[0].bqsjybtsdse.readOnly=true; //19
			document.forms[0].zjgyftsdse.readOnly=true; //30
			document.forms[0].czjzfpsdse.readOnly=true; //31
			document.forms[0].fzjgyftsdse.readOnly=true; //32
			document.forms[0].zjgdlscjybmyftsdse.readOnly=true; //33
//			document.forms[0].zjgycxfzjgyftsdse.readOnly=true; //30
			document.forms[0].fpbl.readOnly=true; //34
			document.forms[0].fpsdse.readOnly=true; //35
			
			document.forms[0].yysr.style.backgroundColor=""; //2
			document.forms[0].yycb.style.backgroundColor=""; //3
			document.forms[0].lrze.style.backgroundColor=""; //4
			document.forms[0].tdjsynssde.style.backgroundColor=""; //5
			document.forms[0].bzsr.style.backgroundColor=""; //6
			document.forms[0].mssr.style.backgroundColor=""; //7
			document.forms[0].jmzynssde.style.backgroundColor=""; //8
			document.forms[0].mbyqndks.style.backgroundColor=""; //9
			document.forms[0].sjlre.style.backgroundColor="#FAEBD7"; //10
			document.forms[0].sl.style.backgroundColor="#FAEBD7"; //11
			document.forms[0].ynsdse.style.backgroundColor="#FAEBD7"; //12
			document.forms[0].jmsdse.style.backgroundColor=""; //13
//			document.forms[0].xwqyjmsdse.style.backgroundColor="#FAEBD7";//14
			document.forms[0].sjyyjsdse.style.backgroundColor="";//15
			document.forms[0].tdywyjsdse.style.backgroundColor=""; //16
			document.forms[0].ybtsdse.style.backgroundColor="#FAEBD7";  //17
			document.forms[0].yqnddjsdse.style.backgroundColor=""; //18
			document.forms[0].bqsjybtsdse.style.backgroundColor="#FAEBD7"; //19
			document.forms[0].zjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].czjzfpsdse.style.backgroundColor="#aaaaaa"; //31
			document.forms[0].fzjgyftsdse.style.backgroundColor="#aaaaaa"; //32
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor="#aaaaaa"; //33
//			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa"; //34
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa"; //35

		}else if(document.forms[0].lje_nsfs[0].checked==true&&document.forms[0].lje_zfjg[0].checked==true){
			// 汇总纳税-总机构
			document.forms[0].yysr.readOnly=false; //2
			document.forms[0].yycb.readOnly=false; //3
			document.forms[0].lrze.readOnly=false; //4
			document.forms[0].tdjsynssde.readOnly=false; //5
			document.forms[0].bzsr.readOnly=false; //6
			document.forms[0].mssr.readOnly=false; //7
			document.forms[0].jmzynssde.readOnly=false; //8
			document.forms[0].mbyqndks.readOnly=false; //9
			document.forms[0].sjlre.readOnly=true; //10
			document.forms[0].sl.readOnly=true; //11
			document.forms[0].ynsdse.readOnly=true; //12
			document.forms[0].jmsdse.readOnly=false; //13
//			document.forms[0].xwqyjmsdse.readOnly=true; //14
			document.forms[0].sjyyjsdse.readOnly=false;//15
			document.forms[0].tdywyjsdse.readOnly=false; //16
			document.forms[0].ybtsdse.readOnly=true;  //17
			document.forms[0].yqnddjsdse.readOnly=false; //18
			document.forms[0].bqsjybtsdse.readOnly=true; //19
			document.forms[0].zjgyftsdse.readOnly=true; //30
			document.forms[0].czjzfpsdse.readOnly=true; //31
			document.forms[0].fzjgyftsdse.readOnly=true; //32
			document.forms[0].zjgdlscjybmyftsdse.readOnly=false; //33
//			document.forms[0].zjgycxfzjgyftsdse.readOnly=false; //30
			document.forms[0].fpbl.readOnly=true; //34
			document.forms[0].fpsdse.readOnly=true; //35
			
			document.forms[0].yysr.style.backgroundColor=""; //2
			document.forms[0].yycb.style.backgroundColor=""; //3
			document.forms[0].lrze.style.backgroundColor=""; //4
			document.forms[0].tdjsynssde.style.backgroundColor=""; //5
			document.forms[0].bzsr.style.backgroundColor=""; //6
			document.forms[0].mssr.style.backgroundColor=""; //7
			document.forms[0].jmzynssde.style.backgroundColor=""; //8
			document.forms[0].mbyqndks.style.backgroundColor=""; //9
			document.forms[0].sjlre.style.backgroundColor="#FAEBD7"; //10
			document.forms[0].sl.style.backgroundColor="#FAEBD7"; //11
			document.forms[0].ynsdse.style.backgroundColor="#FAEBD7"; //12
			document.forms[0].jmsdse.style.backgroundColor=""; //13
//			document.forms[0].xwqyjmsdse.style.backgroundColor="#FAEBD7";//14
			document.forms[0].sjyyjsdse.style.backgroundColor="";//15
			document.forms[0].tdywyjsdse.style.backgroundColor=""; //16
			document.forms[0].ybtsdse.style.backgroundColor="#FAEBD7";  //17
			document.forms[0].yqnddjsdse.style.backgroundColor=""; //18
			document.forms[0].bqsjybtsdse.style.backgroundColor="#FAEBD7"; //19
			document.forms[0].zjgyftsdse.style.backgroundColor="#FAEBD7"; //30
			document.forms[0].czjzfpsdse.style.backgroundColor="#FAEBD7"; //31
			document.forms[0].fzjgyftsdse.style.backgroundColor="#FAEBD7"; //32
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor=""; //33
//			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor=""; //30
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa"; //34
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa"; //35

		}else if(document.forms[0].lje_nsfs[0].checked==true&&document.forms[0].lje_zfjg[1].checked==true){
			// 汇总纳税-分支机构
			document.forms[0].yysr.readOnly=true; //2
			document.forms[0].yycb.readOnly=true; //3
			document.forms[0].lrze.readOnly=true; //4
			document.forms[0].tdjsynssde.readOnly=true; //5
			document.forms[0].bzsr.readOnly=true; //6
			document.forms[0].mssr.readOnly=true; //7
			document.forms[0].jmzynssde.readOnly=true; //8
			document.forms[0].mbyqndks.readOnly=true; //9
			document.forms[0].sjlre.readOnly=true; //10
			document.forms[0].sl.readOnly=true; //11
			document.forms[0].ynsdse.readOnly=true; //12
			document.forms[0].jmsdse.readOnly=true; //13
			document.forms[0].xwqyjmsdse.readOnly=true;//14
			document.forms[0].sjyyjsdse.readOnly=true;//15
			document.forms[0].tdywyjsdse.readOnly=true; //16
			document.forms[0].ybtsdse.readOnly=true;  //17
			document.forms[0].yqnddjsdse.readOnly=true; //18
			document.forms[0].bqsjybtsdse.readOnly=true; //19
			document.forms[0].zjgyftsdse.readOnly=true; //30
			document.forms[0].czjzfpsdse.readOnly=true; //31
			document.forms[0].fzjgyftsdse.readOnly=false; //32
			document.forms[0].zjgdlscjybmyftsdse.readOnly=true; //33
//			document.forms[0].zjgycxfzjgyftsdse.readOnly=true; //30
			document.forms[0].fpbl.readOnly=false; //34
			document.forms[0].fpsdse.readOnly=true; //35
			
			document.forms[0].yysr.style.backgroundColor="#aaaaaa"; //2
			document.forms[0].yycb.style.backgroundColor="#aaaaaa"; //3
			document.forms[0].lrze.style.backgroundColor="#aaaaaa"; //4
			document.forms[0].tdjsynssde.style.backgroundColor="#aaaaaa"; //5
			document.forms[0].bzsr.style.backgroundColor="#aaaaaa"; //6
			document.forms[0].mssr.style.backgroundColor="#aaaaaa"; //7
			document.forms[0].jmzynssde.style.backgroundColor="#aaaaaa"; //8
			document.forms[0].mbyqndks.style.backgroundColor="#aaaaaa"; //9
			document.forms[0].sjlre.style.backgroundColor="#FAEBD7"; //10
			document.forms[0].sl.style.backgroundColor="#aaaaaa"; //11
			document.forms[0].ynsdse.style.backgroundColor="#FAEBD7"; //12
			document.forms[0].jmsdse.style.backgroundColor="#aaaaaa"; //13
			document.forms[0].xwqyjmsdse.style.backgroundColor="#aaaaaa";//14
			document.forms[0].sjyyjsdse.style.backgroundColor="#aaaaaa";//15
			document.forms[0].tdywyjsdse.style.backgroundColor="#aaaaaa"; //16
			document.forms[0].ybtsdse.style.backgroundColor="#FAEBD7";  //17
			document.forms[0].yqnddjsdse.style.backgroundColor="#aaaaaa"; //18
			document.forms[0].bqsjybtsdse.style.backgroundColor="#FAEBD7"; //19
			document.forms[0].zjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].czjzfpsdse.style.backgroundColor="#aaaaaa"; //31
			document.forms[0].fzjgyftsdse.style.backgroundColor=""; //32
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor="#aaaaaa"; //33
//			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].fpbl.style.backgroundColor=""; //34
			document.forms[0].fpsdse.style.backgroundColor="#FAEBD7"; //35
		}else{
			document.forms[0].yysr.readOnly=false; //2
			document.forms[0].yycb.readOnly=false; //3
			document.forms[0].lrze.readOnly=false; //4
			document.forms[0].tdjsynssde.readOnly=false; //5
			document.forms[0].bzsr.readOnly=false; //6
			document.forms[0].mssr.readOnly=false; //7
			document.forms[0].jmzynssde.readOnly=false; //8
			document.forms[0].mbyqndks.readOnly=false; //9
			document.forms[0].sjlre.readOnly=true; //10
			document.forms[0].sl.readOnly=true; //11
			document.forms[0].ynsdse.readOnly=true; //12
			document.forms[0].jmsdse.readOnly=false; //13
//			document.forms[0].xwqyjmsdse.readOnly=true;//14
			document.forms[0].sjyyjsdse.readOnly=false;//15
			document.forms[0].tdywyjsdse.readOnly=false; //16
			document.forms[0].ybtsdse.readOnly=true;  //17
			document.forms[0].yqnddjsdse.readOnly=false; //18
			document.forms[0].bqsjybtsdse.readOnly=true; //19
			document.forms[0].zjgyftsdse.readOnly=true; //30
			document.forms[0].czjzfpsdse.readOnly=true; //31
			document.forms[0].fzjgyftsdse.readOnly=true; //32
			document.forms[0].zjgdlscjybmyftsdse.readOnly=true; //33
//			document.forms[0].zjgycxfzjgyftsdse.readOnly=true; //30
			document.forms[0].fpbl.readOnly=true; //34
			document.forms[0].fpsdse.readOnly=true; //35
			
			document.forms[0].yysr.style.backgroundColor=""; //2
			document.forms[0].yycb.style.backgroundColor=""; //3
			document.forms[0].lrze.style.backgroundColor=""; //4
			document.forms[0].tdjsynssde.style.backgroundColor=""; //5
			document.forms[0].bzsr.style.backgroundColor=""; //6
			document.forms[0].mssr.style.backgroundColor=""; //7
			document.forms[0].jmzynssde.style.backgroundColor=""; //8
			document.forms[0].mbyqndks.style.backgroundColor=""; //9
			document.forms[0].sjlre.style.backgroundColor="#FAEBD7"; //10
			document.forms[0].sl.style.backgroundColor="#FAEBD7"; //11
			document.forms[0].ynsdse.style.backgroundColor="#FAEBD7"; //12
			document.forms[0].jmsdse.style.backgroundColor=""; //13
//			document.forms[0].xwqyjmsdse.style.backgroundColor="#FAEBD7";//14
			document.forms[0].sjyyjsdse.style.backgroundColor="";//15
			document.forms[0].tdywyjsdse.style.backgroundColor=""; //16
			document.forms[0].ybtsdse.style.backgroundColor="#FAEBD7";  //17
			document.forms[0].yqnddjsdse.style.backgroundColor=""; //18
			document.forms[0].bqsjybtsdse.style.backgroundColor="#FAEBD7"; //19
			document.forms[0].zjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].czjzfpsdse.style.backgroundColor="#aaaaaa"; //31
			document.forms[0].fzjgyftsdse.style.backgroundColor="#aaaaaa"; //32
			document.forms[0].zjgdlscjybmyftsdse.style.backgroundColor="#aaaaaa"; //33
//			document.forms[0].zjgycxfzjgyftsdse.style.backgroundColor="#aaaaaa"; //30
			document.forms[0].fpbl.style.backgroundColor="#aaaaaa"; //34
			document.forms[0].fpsdse.style.backgroundColor="#aaaaaa"; //35
		}
		
	}

  <%/*计算--纳税方法--数据*/%>
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
  
  <%/*计算--总分机构--数据*/%>
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
  
  <%/*计算第2行数据*/%>
  function compute_Row_3(){
    //alert("compute_Row_3");
    //判断输入的数据是否符合要求
    if(!isNum(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].yysr;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
  }
  
  <%/*计算第3行数据*/%>
  function compute_Row_4(){
    //alert("compute_Row_4");
    //判断输入的数据是否符合要求
    if(!isNum(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].yycb;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
  }
  
  <%/*计算第4行数据*/%>
  function compute_Row_5(){
    //alert("compute_Row_5");
    //判断输入的数据是否符合要求
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
  
  <%/*计算第5行数据*/%>
  function compute_Row_6(){
    //alert("compute_Row_6");
    //判断输入的数据是否符合要求
    if(!isNum(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].tdjsynssde;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
    if(1*obj_input.value<0){
      alert("加：特定业务计算的应纳税所得额（5行）应大于等于0!");
      document.forms[0].tdjsynssde.select();
      document.forms[0].tdjsynssde.focus();
    }
    //compute_Row_9();
    compute_Row_10();
  }
  
  <%/*计算第6行数据*/%>
  function compute_Row_7(){
    //alert("compute_Row_7");
    //判断输入的数据是否符合要求
    if(!isNum(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].bzsr;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
    if(1*obj_input.value<0){
      alert("减：不征税收入（6行）应大于等于0!");
      document.forms[0].bzsr.select();
      document.forms[0].bzsr.focus();
    }
    //compute_Row_9();
    compute_Row_10();
  }
  
  <%/*计算第7行数据*/%>
  function compute_Row_8(){
    //alert("compute_Row_8");
    //判断输入的数据是否符合要求
    if(!isNum(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].mssr;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
    if(1*obj_input.value<0){
      alert("免税收入（7行）应大于等于0!");
      document.forms[0].mssr.select();
      document.forms[0].mssr.focus();
    }
    //compute_Row_9();
    compute_Row_10();
  }
  <%/*计算第8行数据*/%>
  function compute_Row_8_1(){
    //alert("compute_Row_8_1");
    //判断输入的数据是否符合要求
    if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].jmzynssde;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
    if(1*obj_input.value<0){
      alert("减征、免征应纳税所得额（8行）应大于等于0!");
      document.forms[0].jmzynssde.select();
      document.forms[0].jmzynssde.focus();
    }
    //compute_Row_9();
    compute_Row_10();
  }
  <%/*计算第9行数据*/%>
  function compute_Row_9(){
    //alert("compute_Row_9");
    //判断输入的数据是否符合要求
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
      alert("弥补以前年度亏损（8行）应小于等于第4+5-6-7,且应大于等于0！");
      document.forms[0].mbyqndks.select();
      document.forms[0].mbyqndks.focus();
      //return false;
    }
    if((1*obj_4_5_6_7_jsjg>0)&&(1*obj_input.value<0)){
      alert("弥补以前年度亏损（8行）应大于等于0!");
      document.forms[0].mbyqndks.select();
      document.forms[0].mbyqndks.focus();
      //return false;
    }
    if((1*obj_4_5_6_7_jsjg<=0)&&(1*obj_input.value!=0)){
  	  alert("第4+5-6-7行小于等于0，弥补以前年度亏损（8行）应等于0!");
  	  //obj_input.value="0.00";
  	  document.forms[0].mbyqndks.select();
      document.forms[0].mbyqndks.focus();
      //return false;
    }
    compute_Row_10();
  }
  
  <%/*计算第10行数据*/%>
  function compute_Row_10(){
    //alert("compute_Row_10");
    //判断输入的数据是否符合要求
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
  
  <%/*计算第11行数据*/%>
  function compute_Row_11(){
    //alert("compute_Row_11");
    //判断输入的数据是否符合要求
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
  
  <%/*计算第12行数据*/%>
  function compute_Row_12(){
    //alert("compute_Row_12");
    //判断输入的数据是否符合要求
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
    compute_Row_17();
  }

  <%/*计算第13行数据*/%>
  function compute_Row_13(){
    //alert("compute_Row_13");
    //判断输入的数据是否符合要求
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
      alert("减：减免所得税额（12行）应小于等于应纳所得税额（11行）,且应大于等于0！");
      document.forms[0].jmsdse.select();
      document.forms[0].jmsdse.focus();
      //return false;
    }
    if(1*obj_input.value<0){
      alert("减：减免所得税额（12行）应大于等于0!");
      document.forms[0].jmsdse.select();
      document.forms[0].jmsdse.focus();
      //return false;
    }
    compute_Row_17();
  }
    var inputValueTemp="0.00";
  var xwblts="0";
   <%/*计算第14行数据*/%>
  function compute_Row_14(){
    	//alert("compute_Row_14");
        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].xwqyjmsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		var sfxkh=document.forms[0].sfxkh.value;
		var syndZsfsdm=document.forms[0].syndzsfsdm.value;
		var syndFb5jyjg=document.forms[0].syndfb5jyjg.value;
		//10行录入数据
		var sjlreStr=document.forms[0].sjlre.value;
		var syndZbh6Str=document.forms[0].syndzbh6.value;
		var syndZbh25Str=document.forms[0].syndzbh25.value;
		var sjlre=0.00;
		var syndZbh6=0.00;
		var syndZbh25=0.00;
		if(sjlreStr!=null && sjlreStr!=""){
			sjlre=1*sjlreStr;
		}
		if(syndZbh6Str!=null && syndZbh6Str!=""){
			syndZbh6=1*syndZbh6Str;
		}
		if(syndZbh25Str!=null && syndZbh25Str!=""){
			syndZbh25=1*syndZbh25Str;
		}
		//alert("sfxkh: "+sfxkh);
		//alert("syndZsfsdm: "+syndZsfsdm);
		//alert("syndFb5jyjg: "+syndFb5jyjg);
		//alert("sjlre: "+sjlre);
		//alert("syndZbh6: "+syndZbh6);
		//alert("syndZbh25: "+syndZbh25);
		xwblts="0";
		if(sjlre>30*10000  || sjlre<=0){
			//obj_input.value="0.00";
			inputValueTemp="0.00";
		}else{
			if(sfxkh=="Y"){
				if(sjlre<=10*10000){
					//obj_input.value=(sjlre*0.15).toFixed(2);
					inputValueTemp=(sjlre*0.15).toFixed(2);
					//gyts("15");
					xwblts="15";
				}
				
				if(10*10000<sjlre && sjlre<=30*10000){
					//obj_input.value=(sjlre*0.05).toFixed(2);
					inputValueTemp=(sjlre*0.05).toFixed(2);
					//gyts("5");
					xwblts="5";
				}
				if(sjlre>30*10000){
					//obj_input.value="0.00";
					inputValueTemp="0.00";
				}
			}else{
				 if(syndZsfsdm==<%=CodeConstant.ZSFSDM_CZZS_03%>){//查账征收
					if(1*syndZbh25>30*10000){
						//obj_input.value="0.00";
						inputValueTemp="0.00";
					}else{
						if(syndFb5jyjg=="Y"){
							if(sjlre<=10*10000 && syndZbh25<=10*10000){
								//obj_input.value=(sjlre*0.15).toFixed(2);
								inputValueTemp=(sjlre*0.15).toFixed(2);
								//gyts("15");
								xwblts="15";
							}else{
								//obj_input.value=(sjlre*0.05).toFixed(2);
								inputValueTemp=(sjlre*0.05).toFixed(2);
								//gyts("5");
								xwblts="5";
							}
						}else{
							//obj_input.value="0.00";
							inputValueTemp="0.00";
						}
					}
				}else{
					if(1*syndZbh6>30*10000){
						//obj_input.value="0.00";
					}else{
						if(sjlre<=10*10000 && syndZbh6<=10*10000){
							//obj_input.value=(sjlre*0.15).toFixed(2);
							inputValueTemp=(sjlre*0.15).toFixed(2);
							//gyts("15");
							xwblts="15";
						}else{
							//obj_input.value=(sjlre*0.05).toFixed(2);
							inputValueTemp=(sjlre*0.05).toFixed(2);
							//gyts("5");
							//小微比例提示
							xwblts="5";
						}
					}
				}
			}
		}
	}
  
  function gyts(strBs){
		if(strBs=="15"){
			//alert("本期申报您企业可以享受小型微利企业减半征收企业所得税优惠政策，所得减按50％计入应纳税所得额，按20%的税率缴纳企业所得税。");
			xwblts="15";
		}
		
		if(strBs=="5"){
			//alert("本期申报您企业可以享受小型微利企业所得税优惠政策，按20%的税率缴纳企业所得税。");
			xwblts="5";
		}
	}
  function compute_xwqysdse(){
	    //是否新开户
  		var sfxkh=document.forms[0].sfxkh.value;
	    //上一年度征收方式代码
		var syndZsfsdm=document.forms[0].syndzsfsdm.value;
		var syndFb5jyjg=document.forms[0].syndfb5jyjg.value;
		//10行录入数据
		var sjlreStr=document.forms[0].sjlre.value;
		//上一年度主表行6
		var syndZbh6Str=document.forms[0].syndzbh6.value;
		//上一年度主表行25
		var syndZbh25Str=document.forms[0].syndzbh25.value;
		//实际利润额
		var sjlre=0.00;
		var syndZbh6=0.00;
		var syndZbh25=0.00;
		if(sjlreStr!=null && sjlreStr!=""){
			sjlre=1*sjlreStr;
		}
		if(syndZbh6Str!=null && syndZbh6Str!=""){
			syndZbh6=1*syndZbh6Str;
		}
		if(syndZbh25Str!=null && syndZbh25Str!=""){
			syndZbh25=1*syndZbh25Str;
		}
		
		//alert("sfxkh: "+sfxkh);
		//alert("syndZsfsdm: "+syndZsfsdm);
		//alert("syndFb5jyjg: "+syndFb5jyjg);
		//alert("sjlre: "+sjlre);
		//alert("syndZbh6: "+syndZbh6);
		//alert("syndZbh25: "+syndZbh25);
		xwblts="0";
		//判断行10是否小于等于300000并且不小于等于0，不符合就等于0
		if(sjlre>30*10000 || sjlre<=0){
			//obj_input.value="0.00";
			inputValueTemp="0.00";
		}else{
			//判断是否是新开户，
			if(sfxkh=="Y"){
				
				//如果是新开户则判断行10的值是否小于等于100000
				if(sjlre<=10*10000){
					
					//如果行10的值小于100000
					//obj_input.value=(sjlre*0.15).toFixed(2);
					inputValueTemp=(sjlre*0.15).toFixed(2);
					//提示信息：本期申报您可以享受小型微利企业减半征收企业所得税优惠政策，所得减按50％计入应纳税所得额，按20%的税率缴纳企业所得税。
					gyts("15");
					xwblts="15";
				}
				//如果行10的值大于100000并且小于等于300000
				if(10*10000<sjlre && sjlre<=30*10000){
					//obj_input.value=(sjlre*0.05).toFixed(2);
					inputValueTemp=(sjlre*0.05).toFixed(2);
					//提示信息：本期申报您可以享受小型微利企业所得税优惠政策，按20%的税率缴纳企业所得税。
					gyts("5");
					xwblts="5";
				}
				if(sjlre>30*10000){
					//obj_input.value="0.00";
					inputValueTemp="0.00";
				}
			}else{//如果不是新开户的话
				 //判断上一纳税年度征收方式是否为查账征收
				 if(syndZsfsdm==<%=CodeConstant.ZSFSDM_CZZS_03%>){//查账征收
					 
				 	//判断上一纳税年度汇算清缴申报表行25是否小于300000
					if(1*syndZbh25>30*10000){
						//obj_input.value="0.00";
						inputValueTemp="0.00";
					}else{
						if(syndFb5jyjg=="Y"){
							if(sjlre<=10*10000 && syndZbh25<=10*10000){
								//obj_input.value=(sjlre*0.15).toFixed(2);
								inputValueTemp=(sjlre*0.15).toFixed(2);
								gyts("15");
								xwblts="15";
							}else{
								//obj_input.value=(sjlre*0.05).toFixed(2);
								inputValueTemp=(sjlre*0.05).toFixed(2);
								gyts("5");
								xwblts="5";
							}
						}else{
							//obj_input.value="0.00";
							inputValueTemp="0.00";
						}
					}
				}else{
					if(1*syndZbh6>30*10000){
						//obj_input.value="0.00";
					}else{
						if(sjlre<=10*10000 && syndZbh6<=10*10000){
							//obj_input.value=(sjlre*0.15).toFixed(2);
							inputValueTemp=(sjlre*0.15).toFixed(2);
							gyts("15");
							xwblts="15";
						}else{
							//obj_input.value=(sjlre*0.05).toFixed(2);
							inputValueTemp=(sjlre*0.05).toFixed(2);
							gyts("5");
							xwblts="5";
						}
					}
				}
			}
		}
  }
  <%/*计算第15行数据*/%>
  function compute_Row_15(){
    //alert("compute_Row_14");
        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].sjyyjsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		compute_Row_17();
	}
	
	<%/*计算第16行数据*/%>
	function compute_Row_16(){
		//alert("compute_Row_15");
        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].tdywyjsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		compute_Row_17();
	}
	
	<%/*计算第17行数据*/%>
	function compute_Row_17(){
		//alert("compute_Row_16");
        //判断输入的数据是否符合要求
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
		//计算行次17的域值
		document.forms[0].ybtsdse.value=(1*document.forms[0].ynsdse.value-1*document.forms[0].jmsdse.value-1*document.forms[0].sjyyjsdse.value-1*document.forms[0].tdywyjsdse.value).toFixed(2);
		if((1*document.forms[0].ybtsdse.value)<0){
			document.forms[0].ybtsdse.value="0.00";
		}
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			compute_Row_30();
			compute_Row_31();
			compute_Row_32();
		}
		compute_Row_19();
	}
	
	<%/*计算第18行数据*/%>
	function compute_Row_18(){
		//alert("compute_Row_18");
        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].yqnddjsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value>1*document.forms[0].ybtsdse.value){
			alert("减：以前年度多缴在本期抵缴所得税额（18行）应小于等于应补（退）所得税额（17行），请核对！");
	      	document.forms[0].yqnddjsdse.select();
	      	document.forms[0].yqnddjsdse.focus();
		}
		if(1*obj_input.value<0){
	        alert("减：以前年度多缴在本期抵缴所得税额（18行）应大于等于0!");
	        document.forms[0].yqnddjsdse.select();
	        document.forms[0].yqnddjsdse.focus();
	      //return false;
	    }
		compute_Row_19();
	}
	
	<%/*计算第19行数据*/%>
	function compute_Row_19(){
		//alert("compute_Row_18");
        //判断输入的数据是否符合要求
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
		//计算行次17的域值
		document.forms[0].bqsjybtsdse.value=(1*document.forms[0].ybtsdse.value-1*document.forms[0].yqnddjsdse.value).toFixed(2);
		if((1*document.forms[0].bqsjybtsdse.value)<=0){
		//alert("第17行小于等于0");
			document.forms[0].bqsjybtsdse.value="0.00";
		}
	}

	<%/*计算第30行数据*/%>
	function compute_Row_30(){
		//alert("compute_Row_24");
        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].zjgyftsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		obj_input.value=((1*document.forms[0].ybtsdse.value/100)*25).toFixed(2);
	}

	<%/*计算第31行数据*/%>
	function compute_Row_31(){
		//alert("compute_Row_31");
        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].czjzfpsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		obj_input.value=((1*document.forms[0].ybtsdse.value/100)*25).toFixed(2);
	}
	
	<%/*计算第32行数据*/%>
	function compute_Row_32(){
		//alert("compute_Row_32");
        //判断输入的数据是否符合要求
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
//	      alert("分支机构应分摊所得税额（28行）应大于等于0!");
//	      document.forms[0].fzjgyftsdse.select();
//	      document.forms[0].fzjgyftsdse.focus();
//	      //return false;
//	    }
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
			compute_Row_35();
		}
	}
	
	<%/*计算第33行数据*/%>
	function compute_Row_33(){
		//alert("compute_Row_27");
        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].zjgdlscjybmyftsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
	        alert("总机构独立生产经营部门应分摊所得税额（33行）应大于等于0!");
	        document.forms[0].zjgdlscjybmyftsdse.select();
	        document.forms[0].zjgdlscjybmyftsdse.focus();
	      //return false;
	    }
		var obj_29_30_jsjg = (1*document.forms[0].zjgdlscjybmyftsdse.value+1*document.forms[0].zjgycxfzjgyftsdse.value).toFixed(2); 

		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(1*obj_29_30_jsjg>(1*document.forms[0].fzjgyftsdse.value)){
		        alert("总机构独立生产经营部门应分摊所得税额（33行）与总机构已撤销分支机构应分摊所得税额（32行）之和应小于等于分支机构应分摊所得税额（28行）!");
		        document.forms[0].zjgdlscjybmyftsdse.select();
		        document.forms[0].zjgdlscjybmyftsdse.focus();
		      //return false;
		    }
		}
	}
	
	<%/*计算第30行数据*/%>
	function compute_Row_28(){
		//alert("compute_Row_28");
        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].zjgycxfzjgyftsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(1*obj_input.value<0){
	        alert("总机构已撤销分支机构应分摊所得税额（30行）应大于等于0!");
	        document.forms[0].zjgycxfzjgyftsdse.select();
	        document.forms[0].zjgycxfzjgyftsdse.focus();
	      //return false;
	    }
		var obj_29_30_jsjg = (1*document.forms[0].zjgdlscjybmyftsdse.value+1*document.forms[0].zjgycxfzjgyftsdse.value).toFixed(2); 

		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(1*obj_29_30_jsjg>(1*document.forms[0].fzjgyftsdse.value)){
		        alert("总机构独立生产经营部门应分摊所得税额（29行）与总机构已撤销分支机构应分摊所得税额（30行）之和应小于等于分支机构应分摊所得税额（28行）!");
		        document.forms[0].zjgycxfzjgyftsdse.select();
		        document.forms[0].zjgycxfzjgyftsdse.focus();
		      //return false;
		    }
		}
	}
	
	<%/*计算第34行数据*/%>
	function compute_Row_34(){
		//alert("compute_Row_29");
        //判断输入的数据是否符合要求
        if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].fpbl;
		if((1*obj_input.value<0)||(1*obj_input.value>100)){
			alert("分配比例（34行）应填入0-100的数字，请核对！");
			document.forms[0].fpbl.select();
	        document.forms[0].fpbl.focus();
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
			compute_Row_35();
		}
	}
	
	<%/*计算第35行数据*/%>
	function compute_Row_35(){
		//alert("compute_Row_30");
        //判断输入的数据是否符合要求
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

	<%/*保存时对数据进行校验*/%>
	function saveCheck(){
		//
		if(document.forms[0].lje_nsfs[0].checked==1){
			if((document.forms[0].lje_zfjg[0].checked==0)&&(document.forms[0].lje_zfjg[1].checked==0)){
				alert("请选择总分机构类型！");
				return false;
			}
		}
		//alert("saveCheckFilter...");
		//表头纳税方法选项定义为独立纳税时，系统仅开放本表第2-19行，第20至35行屏蔽
		if(document.forms[0].lje_nsfs[1].checked==1){
			var value_3=(document.forms[0].yysr.value)*1;
			var value_4=(document.forms[0].yycb.value)*1;
			var value_5=(document.forms[0].lrze.value)*1;
			var value_6=(document.forms[0].tdjsynssde.value)*1;
			var value_7=(document.forms[0].bzsr.value)*1;
			var value_8=(document.forms[0].mssr.value)*1;
			var value_8_1=(document.forms[0].jmzynssde.value)*1;
			var value_9=(document.forms[0].mbyqndks.value)*1;
			var value_10=(document.forms[0].sjlre.value)*1;
			var value_11=((document.forms[0].sl.value)*1)/100;
			var value_12=(document.forms[0].ynsdse.value)*1;
			var value_13=(document.forms[0].jmsdse.value)*1;
			var value_14=(document.forms[0].xwqyjmsdse.value)*1;
			var value_15=(document.forms[0].sjyyjsdse.value)*1;
			var value_16=(document.forms[0].tdywyjsdse.value)*1;
			var value_17=(document.forms[0].ybtsdse.value)*1;
			var value_18=(document.forms[0].yqnddjsdse.value)*1;
			var value_19=(document.forms[0].bqsjybtsdse.value)*1;

			if(!isNumJbCheck(document.forms[0].yysr , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].yycb , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].lrze , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].tdjsynssde , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].bzsr , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].mssr , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].mbyqndks , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].ynsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].jmsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].sjyyjsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].tdywyjsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].yqnddjsdse , null, null, null, null, 2)){
				return false;                         
			}
			<%/*判断第5行数据*/%>
			if(value_6<0){
		      	alert("加：特定业务计算的应纳税所得额（5行）应大于等于0!");
		      	document.forms[0].tdjsynssde.select();
		      	document.forms[0].tdjsynssde.focus();
		      	return false;
		    }
		    <%/*判断第6行数据*/%>
		    if(value_7<0){
		      	alert("减：不征税收入（6行）应大于等于0!");
		      	document.forms[0].bzsr.select();
      		  	document.forms[0].bzsr.focus();
		      	return false;
		    }
		    <%/*判断第7行数据*/%>
		    if(value_8<0){
		      	alert("免税收入（7行）应大于等于0!");
		      	document.forms[0].mssr.select();
      			document.forms[0].mssr.focus();
		        return false;
		    }
		    <%/*判断第8行数据*/%>
		    if(value_8_1<0){
		      	alert("减征、免征应纳税所得额（8行）应大于等于0!");
		      	document.forms[0].jmzynssde.select();
      			document.forms[0].jmzynssde.focus();
		        return false;
		    }
			<%/*判断第9行数据*/%>
			var obj_4_5_6_7_jisuanjieguo = (value_5+value_6-value_7-value_8-value_8_1).toFixed(2);
			if((1*obj_4_5_6_7_jisuanjieguo>0)&&(value_9>obj_4_5_6_7_jisuanjieguo)){
				alert("弥补以前年度亏损（9行）应小于等于第4+5-6-7-8,且应大于等于0！");
				document.forms[0].mbyqndks.select();
        		document.forms[0].mbyqndks.focus();
				return false;
			}
			if((1*obj_4_5_6_7_jisuanjieguo>0)&&(value_9<0)){
				alert("弥补以前年度亏损（9行）应大于等于0!");
				document.forms[0].mbyqndks.select();
        		document.forms[0].mbyqndks.focus();
				return false;
			}
			if((1*obj_4_5_6_7_jisuanjieguo<=0)&&(value_9!=0)){
		  	    alert("第4+5-6-7-8行小于等于0，弥补以前年度亏损（9行）应等于0!");
		  	    document.forms[0].mbyqndks.select();
      			document.forms[0].mbyqndks.focus();
				return false;
		    }
			<%/*判断第9行数据*/%>
			//var obj_4_5_6_7_8_jisuanjieguo = (value_5+value_6-value_7-value_8-value_9).toFixed(2);
			//if(value_10>obj_4_5_6_7_8_jisuanjieguo){
			//	alert("实际利润额（9行）应小于等于第4+5-6-7-8");
			//	document.forms[0].sjlre.select();
        	//	document.forms[0].sjlre.focus();
			//	return false;
			//}
			
			<%/*判断第13行数据*/%>
			if(value_13>value_12){
				alert("减：减免所得税额（13行）应小于等于应纳所得税额（12行）,且应大于等于0！");
				document.forms[0].jmsdse.select();
        		document.forms[0].jmsdse.focus();
				return false;
			}
			if(value_13<0){
				alert("减：减免所得税额（13行）应大于等于0!");
				document.forms[0].jmsdse.select();
        		document.forms[0].jmsdse.focus();
				return false;
			}
			//校验第14行数据
			if(!checkedXwqysdse()){
			  	  document.forms[0].xwqyjmsdse.select();
		          document.forms[0].xwqyjmsdse.focus();
				  return false;
			 }
			 if(value_14>value_13){
				  alert("其中：符合条件的小型微利企业减免所得税额（14行）应小于等于减：减免所得税额（13行），请核对！");
		          document.forms[0].jmsdse.select();
		          document.forms[0].jmsdse.focus();
		          return false;
			 }
			<%/*判断第15行数据*/%>
			//var obj_11_12_13_14_jisuanjieguo = (value_12-value_13-value_14-value_15).toFixed(2);
			//if(value_16>obj_11_12_13_14_jisuanjieguo){
			//	alert("应补（退）所得额（15行）应小于等于第11-12-13-14,且应大于等于0！");
			//	document.forms[0].ybtsdse.select();
        	//	document.forms[0].ybtsdse.focus();
			//	return false;
			//}
			//if(value_16<0){
			//	alert("应补（退）所得额（15行）应大于等于0!");
			//	document.forms[0].ybtsdse.select();
        	//	document.forms[0].ybtsdse.focus();
			//	return false;
			//}
			<%/*判断第18行数据*/%>
			if(value_18>value_17){
				alert("减：以前年度多缴在本期抵缴所得税额（18行）应小于等于应补（退）所得税额（17行），请核对！");
	            document.forms[0].yqnddjsdse.select();
	            document.forms[0].yqnddjsdse.focus();
				return false;
			}
			if(value_18<0){
				alert("减：以前年度多缴在本期抵缴所得税额（18行）应大于等于0!");
	            document.forms[0].yqnddjsdse.select();
	            document.forms[0].yqnddjsdse.focus();
				return false;
			}	
			
		    
		//当表头纳税方法选项定义为汇总纳税-总机构时开放本表第2-19、30-33行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			var value_3=(document.forms[0].yysr.value)*1;
			var value_4=(document.forms[0].yycb.value)*1;
			var value_5=(document.forms[0].lrze.value)*1;
			var value_6=(document.forms[0].tdjsynssde.value)*1;
			var value_7=(document.forms[0].bzsr.value)*1;
			var value_8=(document.forms[0].mssr.value)*1;
			var value_8_1=(document.forms[0].jmzynssde.value)*1;
			var value_9=(document.forms[0].mbyqndks.value)*1;
			var value_10=(document.forms[0].sjlre.value)*1;
			var value_11=((document.forms[0].sl.value)*1)/100;
			var value_12=(document.forms[0].ynsdse.value)*1;
			var value_13=(document.forms[0].jmsdse.value)*1;
			var value_14=(document.forms[0].xwqyjmsdse.value)*1;
			var value_15=(document.forms[0].sjyyjsdse.value)*1;
			var value_16=(document.forms[0].tdywyjsdse.value)*1;
			var value_17=(document.forms[0].ybtsdse.value)*1;
			var value_18=(document.forms[0].yqnddjsdse.value)*1;
			var value_19=(document.forms[0].bqsjybtsdse.value)*1;
			var value_24=(document.forms[0].zjgyftsdse.value)*1;
			var value_31=(document.forms[0].czjzfpsdse.value)*1;
			var value_32=(document.forms[0].fzjgyftsdse.value)*1;
			var value_33=(document.forms[0].zjgdlscjybmyftsdse.value)*1;
//			var value_28=(document.forms[0].zjgycxfzjgyftsdse.value)*1;

			if(!isNumJbCheck(document.forms[0].yysr , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].yycb , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].lrze , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].tdjsynssde , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].bzsr , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].mssr , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].mbyqndks , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].ynsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].jmsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].sjyyjsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].tdywyjsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].yqnddjsdse , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].zjgdlscjybmyftsdse , null, null, null, null, 2)){
				return false;                         
			}
//			if(!isNum(document.forms[0].zjgycxfzjgyftsdse , null, null, null, null, 2)){
//				return false;                         
//			}
			
			<%/*判断第5行数据*/%>
			if(value_6<0){
		      	alert("加：特定业务计算的应纳税所得额（5行）应大于等于0!");
		      	document.forms[0].tdjsynssde.select();
		      	document.forms[0].tdjsynssde.focus();
		      	return false;
		    }
		    <%/*判断第6行数据*/%>
		    if(value_7<0){
		      	alert("减：不征税收入（6行）应大于等于0!");
		      	document.forms[0].bzsr.select();
      		  	document.forms[0].bzsr.focus();
		      	return false;
		    }
		    <%/*判断第7行数据*/%>
		    if(value_8<0){
		      	alert("免税收入（7行）应大于等于0!");
		      	document.forms[0].mssr.select();
      			document.forms[0].mssr.focus();
		        return false;
		    }
		    <%/*判断第8行数据*/%>
		    if(value_8_1<0){
		      	alert("减征、免征应纳税所得额（8行）应大于等于0!");
		      	document.forms[0].jmzynssde.select();
      			document.forms[0].jmzynssde.focus();
		        return false;
		    }
			<%/*判断第9行数据*/%>
			var obj_4_5_6_7_8_jisuanjieguo = (value_5+value_6-value_7-value_8-value_8_1).toFixed(2);
			if((1*obj_4_5_6_7_8_jisuanjieguo>0)&&(value_9>obj_4_5_6_7_8_jisuanjieguo)){
				alert("弥补以前年度亏损（9行）应小于等于第4+5-6-7-8,且应大于等于0！");
				document.forms[0].mbyqndks.select();
        		document.forms[0].mbyqndks.focus();
				return false;
			}
			if((1*obj_4_5_6_7_jisuanjieguo>0)&&(value_9<0)){
				alert("弥补以前年度亏损（9行）应大于等于0!");
				document.forms[0].mbyqndks.select();
        		document.forms[0].mbyqndks.focus();
				return false;
			}
			if((1*obj_4_5_6_7_jisuanjieguo<=0)&&(value_9!=0)){
		  	    alert("第4+5-6-7-8行小于等于0，弥补以前年度亏损（9行）应等于0!");
		  	    document.forms[0].mbyqndks.select();
      			document.forms[0].mbyqndks.focus();
				return false;
		    }
			<%/*判断第13行数据*/%>
			if(value_13>value_12){
				alert("减：减免所得税额（13行）应小于等于应纳所得税额（12行）,且应大于等于0！");
				document.forms[0].jmsdse.select();
        		document.forms[0].jmsdse.focus();
				return false;
			}
			if(value_13<0){
				alert("减：减免所得税额（13行）应大于等于0!");
				document.forms[0].jmsdse.select();
        		document.forms[0].jmsdse.focus();
				return false;
			}
			if(!checkedXwqysdse()){
			  	  document.forms[0].xwqyjmsdse.select();
		          document.forms[0].xwqyjmsdse.focus();
				  return false;
			 }
			 if(value_14>value_13){
				  alert("其中：符合条件的小型微利企业减免所得税额（14行）应小于等于减：减免所得税额（13行），请核对！");
		          document.forms[0].jmsdse.select();
		          document.forms[0].jmsdse.focus();
		          return false;
			 }
			<%/*判断第16行数据*/%>
			if(value_18>value_17){
				alert("减：以前年度多缴在本期抵缴所得税额（18行）应小于等于应补（退）所得税额（17行），请核对！");
	            document.forms[0].yqnddjsdse.select();
	            document.forms[0].yqnddjsdse.focus();
				return false;
			}
			if(value_18<0){
				alert("减：以前年度多缴在本期抵缴所得税额（18行）应大于等于0!");
	            document.forms[0].yqnddjsdse.select();
	            document.forms[0].yqnddjsdse.focus();
				return false;
			}
			<%/*判断第33行数据*/%>
			if(value_33<0){
				alert("总机构独立生产经营部门应分摊所得税额（33行）应大于等于0!");
		        document.forms[0].zjgdlscjybmyftsdse.select();
		        document.forms[0].zjgdlscjybmyftsdse.focus();
		        return false;
		    }
			<%/*判断第30行数据*/%>
//			if(value_28<0){
//				alert("总机构已撤销分支机构应分摊所得税额（30行）应大于等于0!");
//		        document.forms[0].zjgycxfzjgyftsdse.select();
//		        document.forms[0].zjgycxfzjgyftsdse.focus();
//		        return false;
//		    }
			<%/*判断第29+30是否大于第28行数据*/%>
			var obj_33_jsjg = (value_33).toFixed(2); 
			if(1*obj_33_jsjg>value_32){
		        alert("总机构独立生产经营部门应分摊所得税额（33行）应小于等于分支机构应分摊所得税额（32行）!");
		        return false;
		    }
			
		//当表头纳税方法选项定义为汇总纳税-分支机构时开放本表第32、34、35行，其余行次屏蔽
		}else if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
			var value_32=(document.forms[0].fzjgyftsdse.value)*1;
			var value_34=(document.forms[0].fpbl.value)*1;
			var value_35=(document.forms[0].fpsdse.value)*1;
			if(!isNumJbCheck(document.forms[0].fzjgyftsdse , null, null, null, null, 2)){
				return false;                         
			}			
			if(!isNumJbCheck(document.forms[0].fpbl , null, null, null, null, 2)){
				return false;                         
			}
			if(!isNumJbCheck(document.forms[0].fpsdse , null, null, null, null, 2)){
				return false;                         
			}
			<%/*判断第32行数据*/%>
//			if(value_32<0){
//		        alert("分支机构应分摊所得税额（32行）应大于等于0!");
//		        document.forms[0].fzjgyftsdse.select();
//		        document.forms[0].fzjgyftsdse.focus();
//		        return false;
//		    }
			<%/*判断第34行数据*/%>
			if((value_34<0)||(value_34>100)){
				alert("分配比例（34行）应填入0-100的数字，请核对！");
				document.forms[0].fpbl.select();
		        document.forms[0].fpbl.focus();
		        return false;
			}
			
		}else{		
			}
		return true;
	}
function checkedXwqysdse(){
		//计算小微企业所得税额
		compute_xwqysdse();
		var value_14=document.forms[0].xwqyjmsdse.value;
		
//		if(value_14*1==0){
//			if(xwblts=="15"){
//				//alert("本期申报您企业可以享受小型微利企业减半征收企业所得税优惠政策，所得减按50％计入应纳税所得额，按20%的税率缴纳企业所得税。");
//				if(confirm("本期申报您企业可以享受小型微利企业减半征收企业所得税优惠政策，所得减按50％计入应纳税所得额，按20%的税率缴纳企业所得税。是否不享受此优惠政策，继续保存申报数据？")){
//					return true;
//				}else{
//					return false;
//				}
//			}else if(xwblts=="5"){
//				//alert("本期申报您企业可以享受小型微利企业所得税优惠政策，按20%的税率缴纳企业所得税。");
//				if(confirm("本期申报您企业可以享受小型微利企业所得税优惠政策，按20%的税率缴纳企业所得税。是否不享受此优惠政策，继续保存申报数据？")){
//					return true;
//				}else{
//					return false;
//				}
//			}
//		}
		
		if(inputValueTemp!=value_14){
			if(inputValueTemp=="0.00"){
				alert("您企业不符合小型微利企业条件，第14行“其中：符合条件的小型微利企业减免所得税额”应填写0！");
				return false;
			}else //if(value_14!=="0.00")
			{
				alert("您企业符合小型微利企业条件，第14行“其中：符合条件的小型微利企业减免所得税额”应按照第10行“实际利润额”的"+xwblts+"%("+inputValueTemp+")进行填写");
				return false;
			}
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
		if(confirm("确认是否清除当前数据？"))
		{
			if(document.forms[0].lje_nsfs[1].checked==1){
				document.forms[0].yysr.value="0.00"; //2
				document.forms[0].yycb.value="0.00"; //3
				document.forms[0].lrze.value="0.00"; //4
				document.forms[0].tdjsynssde.value="0.00"; //5
				document.forms[0].bzsr.value="0.00"; //6
				document.forms[0].mssr.value="0.00"; //7
				document.forms[0].jmzynssde.value="0.00"; //8
				document.forms[0].mbyqndks.value="0.00"; //9
				document.forms[0].sjlre.value="0.00"; //10
				document.forms[0].sl.value="25"; //11
				document.forms[0].ynsdse.value="0.00"; //12
				document.forms[0].jmsdse.value="0.00"; //13
				document.forms[0].xwqyjmsdse.value="0.00"; //14
				document.forms[0].sjyyjsdse.value="0.00";//15
				document.forms[0].tdywyjsdse.value="0.00"; //14
				document.forms[0].ybtsdse.value="0.00";  //17
				document.forms[0].yqnddjsdse.value="0.00"; //18
				document.forms[0].bqsjybtsdse.value="0.00"; //19
			}
			if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
				document.forms[0].yysr.value="0.00"; //2
				document.forms[0].yycb.value="0.00"; //3
				document.forms[0].lrze.value="0.00"; //4
				document.forms[0].tdjsynssde.value="0.00"; //5
				document.forms[0].bzsr.value="0.00"; //6
				document.forms[0].mssr.value="0.00"; //7
				document.forms[0].jmzynssde.value="0.00"; //8
				document.forms[0].mbyqndks.value="0.00"; //9
				document.forms[0].sjlre.value="0.00"; //10
				document.forms[0].sl.value="25"; //11
				document.forms[0].ynsdse.value="0.00"; //12
				document.forms[0].jmsdse.value="0.00"; //13
				document.forms[0].xwqyjmsdse.value="0.00"; //14
				document.forms[0].sjyyjsdse.value="0.00";//15
				document.forms[0].tdywyjsdse.value="0.00"; //16
				document.forms[0].ybtsdse.value="0.00";  //17
				document.forms[0].yqnddjsdse.value="0.00"; //18
				document.forms[0].bqsjybtsdse.value="0.00"; //19
				document.forms[0].zjgyftsdse.value="0.00"; //30
				document.forms[0].czjzfpsdse.value="0.00"; //31
				document.forms[0].fzjgyftsdse.value="0.00"; //32
				document.forms[0].zjgdlscjybmyftsdse.value="0.00"; //33
//				document.forms[0].zjgycxfzjgyftsdse.value="0.00"; //30
			}
			if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[1].checked==1){
				document.forms[0].fzjgyftsdse.value="0.00"; //32
				document.forms[0].fpbl.value="0.00"; //34
				document.forms[0].fpsdse.value="0.00"; //35
			}
			
			document.forms[0].zczbje.value="0.00"; 
			document.forms[0].zcze.value="0.00"; 

			
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
			alert("独立纳税无需查看分配表！");
			return false;
		}
//		if(confirm("查看分配表将重新保存页面数据，请确认！"))
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
			alert("基本信息不正确,保存失败,请重新输入!");
			document.forms[0].jsjdm.focus();
			return false;
		}		
		if(!saveCheck()){
			//alert("数据填写不正确，无法保存！");
			return false;
		}

	var old  = document.forms[0].ywczlx.value;
    return SaveExec(old);
}

function doDelete()
{	
		var old  = document.forms[0].ywczlx.value;
    if((document.forms[0].ywczlx.value == 0) && confirm("是否删除申报资料(包括分配表)?"))
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

//响应计算机代码的回车查询
function jsjdmQuery(){
	if(event.keyCode==13) event.keyCode = 9;
}

function checkNumInput(obj)
{
		//判断输入的数据是否符合要求
		if(!isNum(obj , null, null, null, null, 2)){
			return false;			
		}
		//格式化数据
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
 
 
//intFLag 0--大于等于零  资产总额校验
//intFLag 1--大于零  注册资本金额校验
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
//modified by huohb,2014-07-31
	//对公用jsisNumJbCheck进行包装，因2014年季报需要对输入的数字进行校验，如只输入“-”，下面的自动计算会出现NaN,所以需要先对传输进来的值进行校验，然后转换才能进行计算
	function isNumJbCheck(obj,minValue,maxValue,empty,totalLength, decimalLength){
		formate(obj);
		return isNum(obj,minValue,maxValue,empty,totalLength, decimalLength);
		
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
<form name="czzsfzjgnb2014Form" action="czzsfzjgnb2014.dc">
<input name="actionType" type="hidden" id="actionType" value="Show">
	<table width="1000" align="center" cellspacing="0" class="table-99" onKeyDown="jsjdmQuery()">
		<tr>
		<td align="center" valign="top" colspan="7">
        <jsp:include page="../../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="企业所得税分支机构年度纳税申报表" />
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
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('View');return false;"><img name="spage" value="录入分配表" alt="录入分配表" onMouseOver="MM_swapImage('chakan','','images/insertb2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="images/insertb1.jpg" width="79" height="22" id="chakan"></a>
					<!--a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Delete');return false;"><img name="dpage" value="删除" alt="删除" onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>/images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/shanchu1.jpg" width="79" height="22" id="shanchu"></a  -->
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