<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ page import="com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.Constant"%>
<%@ page import="com.ttsoft.bjtax.shenbao.constant.CodeConstant"%>
<%@ page import="com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement.CheckJdlx"%>
<%@ page import="com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.CzzssdsjbVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.DmVo"%>
<%
    String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
	System.out.println("======================123=============================");
	HashMap codeMap=(HashMap)session.getAttribute("CZZSSDSJB_DM_MAPS");
	List mssrxmDmList = (List) codeMap.get("MssrxmDmList");
	List jzmzxmDmList = (List) codeMap.get("JzmzxmDmList");
	List jmxmDmList = (List) codeMap.get("JmxmDmList");
%>
<html>
<head>
<title>企业所得税月(季)度预缴纳税申报表(A类)</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type = "text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="gojs/XmlBuild.js"></script>

<script language="JavaScript">
//alert("javascript===============");
var mssrxmDmList= new Array(<%=mssrxmDmList.size()%>);
var jzmzxmDmList= new Array(<%=jzmzxmDmList.size()%>);
var jmxmDmList= new Array(<%=jmxmDmList.size()%>);
<%
	//初始化免税收入项目代码表数据
	for(int i=0;i<mssrxmDmList.size();i++)
	{
		DmVo mssrxmDmVo = (DmVo)mssrxmDmList.get(i);
	
		out.println("mssrxmDmList["+i+"] = [\""+mssrxmDmVo.getDm()+"\",\""+mssrxmDmVo.getMc()+"\",\""+mssrxmDmVo.getLevel()+"\"];");
	}
	//初始化减征、免征项目代码表数据
	for(int i=0;i<jzmzxmDmList.size();i++)
	{
		DmVo jzmzxmDmVo = (DmVo)jzmzxmDmList.get(i);
	
		out.println("jzmzxmDmList["+i+"] = [\""+jzmzxmDmVo.getDm()+"\",\""+jzmzxmDmVo.getMc()+"\",\""+jzmzxmDmVo.getLevel()+"\"];");
	}   
	//初始化减免项目代码表数据
	for(int i=0;i<jmxmDmList.size();i++)
	{
		DmVo jmxmDmVo = (DmVo)jmxmDmList.get(i);
	
		out.println("jmxmDmList["+i+"] = [\""+jmxmDmVo.getDm()+"\",\""+jmxmDmVo.getMc()+"\",\""+jmxmDmVo.getLevel()+"\"];");
	}                     
       
   
   
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
			   'zjgyftsdse', 'czjzfpsdse', 'fzjgyftsdse', 'zjgdlscjybmyftsdse', 'zjgycxfzjgyftsdse', 'fpbl', 'fpsdse'];
var InputNameAl=['纳税方式','总分机构',
				 '营业收入','营业成本', '利润总额', '加：特定业务计算的应纳税所得额','减：不征税收入', '免税收入','减征、免征应纳税所得额', '弥补以前年度亏损', '实际利润额', '税率','应纳所得税额', 
				 '减：减免所得税额','其中：符合条件的小型微利企业减免所得税额', '减：实际已预缴所得税额', '减：特定业务预缴（征）所得税额', '应补（退）所得税额', '减：以前年度多缴在本期抵缴所得税额', '本期实际应补（退）所得税额', 
				 '总机构应分摊所得税额', '财政集中分配所得税额', '分支机构应分摊所得税额', '其中：总机构独立生产经营部门应分摊所得税额', '总机构已撤销分支机构应分摊所得税额', '分配比例', '分配所得税额'];
var strXSLTVersion = '<%=request.getAttribute("CA_XML_XSLT_VERSION")==null?"":request.getAttribute("CA_XML_XSLT_VERSION")%>';
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xmlDoc;

//alert("strXSLTVersion==="+strXSLTVersion);
//解析xml
function parseXmlOnLoad()
{
	//alert("parseXmlOnLoad");
	var urlXSL="/XSLTWEB/model/010031/XSLT/" +strXSLTVersion+".xsl";
    if (strXml != "")
    {
        if (! parseXml(strXml,urlXSL,"result"))
            return false;
	xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	xmlDoc.async = false;
	xmlDoc.loadXML(strXml);
    }
	setInput();
	//新增input设置
	setInputNew();
	//初始化代码表
	initDmb();
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
	applendElement("/taxdoc","sbsj",["nsfs", "zfjg", "yysr", "yycb", "lrze", "tdjsynssde", "bzsr", "mssr", "jmzynssde","mbyqndks", "sjlre", "sl", "ynsdse", "jmsdse","xwqyjmsdse", "sjyyjsdse", "tdywyjsdse", "ybtsdse", "yqnddjsdse", "bqsjybtsdse", "zjgyftsdse", "czjzfpsdse", "fzjgyftsdse", "zjgdlscjybmyftsdse", "zjgycxfzjgyftsdse", "fpbl", "fpsdse"]);
	
	//从表申报数据
	applendCbsbsj();
	
	
	//去掉末尾自动添加的回车
	retstr = g_Doc.XMLHeader + g_Doc.XMLDoc.xml;
	retstr = retstr.substr(0,retstr.length-2);
	return retstr;
}

//从表申报数据
function applendCbsbsj(){

	var cbMssrxmYzhc=document.getElementsByName("cbMssrxmYzhc");
	if(cbMssrxmYzhc.length>0){
				//在根节点taxdoc下添加cbsbsj节点
		var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
		var objTemp = g_Doc.XMLDoc.createElement("cbsbsj");
		objNode.appendChild(objTemp);
		//添加明细信息明细信息
		for(var i = 0; i < cbMssrxmYzhc.length; i++)
		{
			if(cbMssrxmYzhc[i].value != null && cbMssrxmYzhc[i].value != "")
			{
				applendCbSbsjListElement("/taxdoc/cbsbsj","cbMssrxmList",["cbMssrxmDmhc","cbMssrxmDm","cbMssrxmYzhc","cbMssrxmYz"],i);
			}
		}
	}	else
	{
		//添加空明细信息节点
		applendElement("/taxdoc","cbsbsj",["cbMssrxmList"]);
	}


	var cbJzmzxmYzhc=document.getElementsByName("cbJzmzxmYzhc");
	if(cbJzmzxmYzhc.length>0){
				//在根节点taxdoc下添加cbsbsj节点
//		var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
//		var objTemp = g_Doc.XMLDoc.createElement("cbsbsj");
//		objNode.appendChild(objTemp);
		//添加明细信息明细信息
		for(var i = 0; i < cbJzmzxmYzhc.length; i++)
		{
			if(cbJzmzxmYzhc[i].value != null && cbJzmzxmYzhc[i].value != "")
			{
				applendCbSbsjListElement("/taxdoc/cbsbsj","cbJzmzxmList",["cbJzmzxmDmhc","cbJzmzxmDm","cbJzmzxmYzhc","cbJzmzxmYz"],i);
			}
		}
	}	else
	{
		//添加空明细信息节点
		applendElement("/taxdoc","cbsbsj",["cbJzmzxmList"]);
	}
	
	var cbJmxmYzhc=document.getElementsByName("cbJmxmYzhc");
	if(cbJmxmYzhc.length>0){
				//在根节点taxdoc下添加cbsbsj节点
//		var objNode = g_Doc.XMLDoc.selectSingleNode("/taxdoc");
//		var objTemp = g_Doc.XMLDoc.createElement("cbsbsj");
//		objNode.appendChild(objTemp);
		//添加明细信息明细信息
		for(var i = 0; i < cbJmxmYzhc.length; i++)
		{
			if(cbJmxmYzhc[i].value != null && cbJmxmYzhc[i].value != "")
			{
				applendCbSbsjListElement("/taxdoc/cbsbsj","cbJmxmList",["cbJmxmDmhc","cbJmxmDm","cbJmxmYzhc","cbJmxmYz"],i);
			}
		}
	}	else
	{
		//添加空明细信息节点
		applendElement("/taxdoc","cbsbsj",["cbJmxmList"]);
	}	
}

//增加从表明细信息节点
function applendCbSbsjListElement(root,node,tags,index)
{
	//alert("root = " + root + "\nnode = " + node);
	var objNode = g_Doc.XMLDoc.selectSingleNode(root);
	var objTemp = g_Doc.XMLDoc.createElement(node);
	for (i = 0; i < tags.length ; i++)
	{
		//明细数据域ID从12~19
		getMxxxChildren(objTemp,tags[i], index);
	}
	if (objTemp.hasChildNodes())
	{
		objNode.appendChild(objTemp);
	}
}
//明细信息子节点实际信息
function getMxxxChildren(temp, strTag, index)
{

	//alert("id = " + id + "\nstrTag = " + strTag);
	var element = document.getElementsByName(strTag)[index];
	var objTemp =temp;
	var node = g_Doc.XMLDoc.createElement(strTag);
	objTemp.appendChild(node);
	var strValue;
	if(element!=null)
	{
		strValue = formString(element.value);
	}

	//alert("strValue = " + strValue);
	var objCDATA = g_Doc.XMLDoc.createCDATASection(strValue);
	node.appendChild(objCDATA);
}
function setInput(){
	
	//alert("1");
	//初始化RADIO域
	initSelect();
	//alert("2");
	//初始化RADIO域可访问项
	changeSelect();
	//alert("3");
	//初始化行次11税率
	document.forms[0].sl.value="25.00";
	//alert("4");
	//初始化不可使用域
	checkFilter();
	//alert("5");
	initAddImage();
	//检查是否保存成功，如果保存成功则建议跳转
	<%
		if("1".equals((String)request.getAttribute(Constant.JUMP_FLAG_NAME))){
	%>
		//if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
		if(document.forms[0].lje_nsfs[0].checked==1){
			if(document.getElementById('sfxkh').value=="Y"){
				//alert("申报已成功！");
				//modified by zhangj,2014.12.08	
				if(confirm("请确认该企业本季度是否已享受固定资产加速折旧（扣除）政策？")){
					document.forms[0].actionType.value="JumpGdzc";
				}else{
					document.forms[0].actionType.value="JumpSuccess";
				}	
				document.forms[0].submit();
			}else{
				//保存成功，提示转总分机构申报表
				//if(confirm("保存成功，是否转入《企业所得税汇总纳税分支机构分配表》？")){
					alert("保存成功，将转入《企业所得税汇总纳税分支机构分配表》");
					doSubmit("Jump");
				//}
			}
		}else if(document.forms[0].lje_nsfs[1].checked==1){
			//modified by zhangj,2014.12.08	
			if(confirm("请确认该企业本季度是否已享受固定资产加速折旧（扣除）政策？")){
				document.forms[0].actionType.value="JumpGdzc";
			}else{
				document.forms[0].actionType.value="JumpSuccess";
			}	
			document.forms[0].submit();
		}
		
	<%
	}
	%>
	
}


	<%/*根据初始化隐藏域为单选框赋值并初始化隐藏域*/%>
	function initSelect(){
		//alert("initSelect...");
		//alert("document.forms[0].nsfs.value="+document.forms[0].nsfs.value);
		//alert("document.forms[0].zfjg.value="+document.forms[0].zfjg.value);
		
		//if(document.forms[0].sybs.value==<%=CodeConstant.CODE_QYSDS_SYBS_D%>){
		//	document.forms[0].lje_nsfs[1].checked=1;
		//}else if(document.forms[0].sybs.value==<%=CodeConstant.CODE_QYSDS_SYBS_Z%>){
		//	document.forms[0].lje_nsfs[0].checked=1;
		//	document.forms[0].lje_zfjg[0].checked=1;
		//}else if(document.forms[0].sybs.value==<%=CodeConstant.CODE_QYSDS_SYBS_F%>){
		//	document.forms[0].lje_nsfs[0].checked=1;
		//	document.forms[0].lje_zfjg[1].checked=1;
		//}
		
		//获取征管范围鉴定类型
		var jdlx = document.forms[0].zgfwjdlx.value;
		//独立纳税人---||---总分机构均在本市的总机构纳税人
		if(jdlx==<%=CheckJdlx.QYSDSZGFWJDLX_DLNSR%> || jdlx==<%=CheckJdlx.QYSDSZGFWJDLX_ZFJGJZBSZJGNSR%>)
		{
			document.forms[0].lje_nsfs[1].checked=1;
		}
		//跨省市分支机构纳税人
		if(jdlx==<%=CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR%>)
		{
			document.forms[0].lje_nsfs[0].checked = true;
			document.forms[0].lje_zfjg[1].checked = true;
		}
		
		//跨省市总机构纳税人
		if(jdlx==<%=CheckJdlx.QYSDSZGFWJDLX_KSSZJGNSR%>)
		{
		    document.forms[0].lje_nsfs[0].checked = true;
			document.forms[0].lje_zfjg[0].checked = true;
		}
			
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
			document.forms[0].lje_zfjg.disabled = false;
			if(document.forms[0].lje_zfjg[0].checked==0&&document.forms[0].lje_zfjg[1].checked==0){
				document.forms[0].lje_zfjg[0].checked = true;
				document.forms[0].lje_zfjg[1].checked = false;
			}
			//选择为总机构时，开放其子项选择权限
//			document.forms[0].lje_zfjg[0].disabled = false;
//			document.forms[0].lje_zfjg[1].disabled = false;
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


			//从表动态添加行屏蔽
			document.getElementById('tr_Mssrxm').style.display = "none";
			document.getElementById('tr_Jzmzxm').style.display = "none";
			document.getElementById('tr_Jmxm').style.display = "none";
			var adds=document.getElementsByName('add_image');
			if(adds!=null){
				for(var i=0;i<adds.length;i++){
					adds[i].style.display = "none";
				}
			}	

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
    	//compute_Row_30();
    	//compute_Row_31();
    	//compute_Row_32();
//    	document.forms[0].zjgyftsdse.value=((1*document.forms[0].ybtsdse.value/100)*25).toFixed(2);
//		document.forms[0].czjzfpsdse.value=((1*document.forms[0].ybtsdse.value/100)*25).toFixed(2);
//		document.forms[0].fzjgyftsdse.value=((1*document.forms[0].ybtsdse.value/100)*50).toFixed(2);
		
		//新需求变更 
		document.forms[0].zjgyftsdse.value=((1*document.forms[0].bqsjybtsdse.value/100)*25).toFixed(2);
		document.forms[0].czjzfpsdse.value=((1*document.forms[0].bqsjybtsdse.value/100)*25).toFixed(2);
		document.forms[0].fzjgyftsdse.value=((1*document.forms[0].bqsjybtsdse.value/100)*50).toFixed(2);
		
    }
  }
  
  <%/*计算第2行数据*/%>
  function compute_Row_3(){
    //alert("compute_Row_3");
    //判断输入的数据是否符合要求
    if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
    if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].yycb;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
  }
//modified by huohb,2014-07-31
	//对公用jsisNumJbCheck进行包装，因2014年季报需要对输入的数字进行校验，如只输入“-”，下面的自动计算会出现NaN,所以需要先对传输进来的值进行校验，然后转换才能进行计算
	function isNumJbCheck(obj,minValue,maxValue,empty,totalLength, decimalLength){
		formate(obj);
		return isNum(obj,minValue,maxValue,empty,totalLength, decimalLength);
		
	}
  <%/*计算第4行数据*/%>
  function compute_Row_5(){
    //alert("compute_Row_5");
    //判断输入的数据是否符合要求
    if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
    if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
    if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
    if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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

  <%/*计算第7行数据*/%>
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
    if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].mbyqndks;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
    var obj_4_5_6_7_8_jsjg = (1*document.forms[0].lrze.value+1*document.forms[0].tdjsynssde.value-1*document.forms[0].bzsr.value-1*document.forms[0].mssr.value-1*document.forms[0].jmzynssde.value).toFixed(2);
    //alert("obj_4_5_6_7_jsjg=="+obj_4_5_6_7_jsjg);
    if((1*obj_4_5_6_7_8_jsjg>0)&&(1*obj_input.value)>(1*obj_4_5_6_7_8_jsjg)){
      alert("弥补以前年度亏损（9行）应小于等于第4+5-6-7-8,且应大于等于0！");
      document.forms[0].mbyqndks.select();
      document.forms[0].mbyqndks.focus();
      //return false;
    }
    if((1*obj_4_5_6_7_8_jsjg>0)&&(1*obj_input.value<0)){
      alert("弥补以前年度亏损（9行）应大于等于0!");
      document.forms[0].mbyqndks.select();
      document.forms[0].mbyqndks.focus();
      //return false;
    }
    if((1*obj_4_5_6_7_8_jsjg<=0)&&(1*obj_input.value!=0)){
  	  alert("第4+5-6-7-8行小于等于0，弥补以前年度亏损（9行）应等于0!");
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
    if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
  		return false;                         
    }
    var obj_input=document.forms[0].sjlre;
    if(obj_input.value==""){
      obj_input.value="0.00";
    }
    
    document.forms[0].sjlre.value=(1*document.forms[0].lrze.value+1*document.forms[0].tdjsynssde.value-1*document.forms[0].bzsr.value-1*document.forms[0].mssr.value-1*document.forms[0].mbyqndks.value-1*document.forms[0].jmzynssde.value).toFixed(2);
    if((1*document.forms[0].sjlre.value)==""){
      document.forms[0].sjlre.value="0.00";
    }
    
    
    compute_Row_12();
    
    //计算小微企业减免所得税额
//    compute_Row_14();
  }
 
  <%/*计算第10行数据*/%>
  function compute_Row_11(){
    //alert("compute_Row_11");
    //判断输入的数据是否符合要求
    if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
    if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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

  <%/*计算第12行数据*/%>
  function compute_Row_13(){
    //alert("compute_Row_13");
    //判断输入的数据是否符合要求
    if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
      alert("减：减免所得税额（13行）应小于等于应纳所得税额（12行）,且应大于等于0！");
      document.forms[0].jmsdse.select();
      document.forms[0].jmsdse.focus();
      //return false;
    }
    if(1*obj_input.value<0){
      alert("减：减免所得税额（13行）应大于等于0!");
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
		
		//modified by zhangj 2015.3.2
		var million=10*10000;
		var nd=document.forms[0].nd.value;
		var jd=document.forms[0].jd.value;
		if((nd*1>2015)||(nd*1==2015&&jd*1>=1)){			
			million=20*10000;//对于申报所属期为2015年1季度以后的季度纳税申报的纳税人，环节1至6的判定关系中原有≤10万的判定标准修改为≤20万元。
		}
		//modified by zhangj end 
		
		xwblts="0";
		if(sjlre>30*10000  || sjlre<=0){
			//obj_input.value="0.00";
			inputValueTemp="0.00";
		}else{
			if(sfxkh=="Y"){
				if(sjlre<=million){
					//obj_input.value=(sjlre*0.15).toFixed(2);
					inputValueTemp=(sjlre*0.15).toFixed(2);
					//gyts("15");
					xwblts="15";
				}
				
				if(million<sjlre && sjlre<=30*10000){
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
							if(sjlre<=million && syndZbh25<=million){
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
						if(sjlre<=million && syndZbh6<=million){
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
		//将小微企业加到减免项目合计中
		cbsjHj('Jmxm_xw');
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
		
		//modified by zhangj 2015.3.2
		var million=10*10000;
		var nd=document.forms[0].nd.value;
		var jd=document.forms[0].jd.value;
		if((nd*1>2015)||(nd*1==2015&&jd*1>=1)){			
			million=20*10000;//对于申报所属期为2015年1季度以后的季度纳税申报的纳税人，环节1至6的判定关系中原有≤10万的判定标准修改为≤20万元。
		}
		//modified by zhangj end 
		
		
		xwblts="0";
		//判断行10是否小于等于300000并且不小于等于0，不符合就等于0
		if(sjlre>30*10000 || sjlre<=0){
			//obj_input.value="0.00";
			inputValueTemp="0.00";
		}else{
			//判断是否是新开户，
			if(sfxkh=="Y"){
				
				//如果是新开户则判断行10的值是否小于等于100000
				if(sjlre<=million){
					
					//如果行10的值小于100000
					//obj_input.value=(sjlre*0.15).toFixed(2);
					inputValueTemp=(sjlre*0.15).toFixed(2);
					//提示信息：本期申报您可以享受小型微利企业减半征收企业所得税优惠政策，所得减按50％计入应纳税所得额，按20%的税率缴纳企业所得税。
					gyts("15");
					xwblts="15";
				}
				//如果行10的值大于100000并且小于等于300000
				if(million<sjlre && sjlre<=30*10000){
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
							if(sjlre<=million && syndZbh25<=million){
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
						if(sjlre<=million && syndZbh6<=million){
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
    //alert("compute_Row_15");
        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
		//alert("compute_Row_16");
        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
		//alert("compute_Row_17");
        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
//		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
//			compute_Row_30();
//			compute_Row_31();
//			compute_Row_32();
//		}
		compute_Row_19();
	}
	
	<%/*计算第18行数据*/%>
	function compute_Row_18(){
		//alert("compute_Row_18");
        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
		//alert("compute_Row_19");
        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].bqsjybtsdse;
		if((obj_input.value)*1<0){
			obj_input.value="0.00";
		}
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		//计算行次19的域值
		document.forms[0].bqsjybtsdse.value=(1*document.forms[0].ybtsdse.value-1*document.forms[0].yqnddjsdse.value).toFixed(2);
		if((1*document.forms[0].bqsjybtsdse.value)<=0){
		//alert("第17行小于等于0");
			document.forms[0].bqsjybtsdse.value="0.00";
		}
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			compute_Row_30();
			compute_Row_31();
			compute_Row_32();
		}
	}

	<%/*计算第30行数据*/%>
	function compute_Row_30(){
		//alert("compute_Row_30");
        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].zjgyftsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
//		obj_input.value=((1*document.forms[0].ybtsdse.value/100)*25).toFixed(2);
		obj_input.value=((1*document.forms[0].bqsjybtsdse.value/100)*25).toFixed(2);
	}

	<%/*计算第31行数据*/%>
	function compute_Row_31(){
		//alert("compute_Row_31");
        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].czjzfpsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
//		obj_input.value=((1*document.forms[0].ybtsdse.value/100)*25).toFixed(2);
		
		obj_input.value=((1*document.forms[0].bqsjybtsdse.value/100)*25).toFixed(2);
	}
	
	<%/*计算第32行数据*/%>
	function compute_Row_32(){
		//alert("compute_Row_32");
        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
			return false;                         
		}
		var obj_input=document.forms[0].fzjgyftsdse;
		if(obj_input.value==""){
			obj_input.value="0.00";
		}
		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			//obj_input.value=((1*document.forms[0].ybtsdse.value/100)*50).toFixed(2);
			obj_input.value=((1*document.forms[0].bqsjybtsdse.value/100)*50).toFixed(2);
		}
//		if(1*obj_input.value<0){
//	      alert("分支机构应分摊所得税额（32行）应大于等于0!");
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
		//alert("compute_Row_33");
        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
		var obj_33_jsjg = (1*document.forms[0].zjgdlscjybmyftsdse.value).toFixed(2); 

		if(document.forms[0].lje_nsfs[0].checked==1&&document.forms[0].lje_zfjg[0].checked==1){
			if(1*obj_33_jsjg>(1*document.forms[0].fzjgyftsdse.value)){
		        alert("总机构独立生产经营部门应分摊所得税额（33行）应小于等于分支机构应分摊所得税额（32行）!");
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
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
		//alert("compute_Row_34");
        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
		//alert("compute_Row_35");
        //判断输入的数据是否符合要求
        if(!isNumJbCheck(getCellObject() , null, null, null, null, 2)){
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
			//重置从表数据
			doDeleteAllCbRow();
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
		if(document.getElementById('sfxkh').value=="Y"){
			alert("新开户无需查看分配表！");
			return false;
		}
		if(confirm("查看分配表将重新保存页面数据，请确认！"))
		{
			doSave();
		}
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
	
	//检查从表数据
	cbSaveCheck();
	
    return SaveExec(old);
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
		if(!isNumJbCheck(obj , null, null, null, null, 2)){
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
		if(temp=="-.00"){
			temp="-0.00";
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
 
 function initAddImage(){
  	var add_image=document.getElementsByName("add_image");
  	for(var i=0;i<add_image.length;i++){
  		add_image[i].setAttribute("src","<%=static_contextpath%>images/zbotton-jia2.gif");
  	}
 	
 	var delete_image=document.getElementsByName("delete_image");
 	for(var i=0;i<delete_image.length;i++){
 		delete_image[i].setAttribute("src","<%=static_contextpath%>images/zbotton-jian2.gif");
 	}
 }
		<%/*增加行*/%>
	function doMssrxmAddRow()
	{	


		//获取需要添加行的表格
		var table = document.getElementById("mssrxmAutoTable");

		//然后创建行(TR对象)
		var NewTr = document.createElement("tr");
		var NewTd1 = document.createElement("<td colspan=\"1\" width=\"5%\" nowrap class=\"2-td2-left\">");
			NewTd1.innerHTML="<div align=\"center\">&nbsp;&nbsp;<input type=\"hidden\" name=\"cbMssrxmYzhc\" value=\"\" id=\"\"></div>";
		var NewTd2 = document.createElement("<td colspan=\"2\" width=\"40%\" colspan=\"2\" nowrap class=\"2-td2-left\">");
			NewTd2.innerHTML="<div align=\"left\">&#160;&#160;&#160;&#160;</div>";
		var NewTd3 = document.createElement("<td width=\"34%\" colspan=\"2\" width=\"420\" nowrap class=\"2-td2-left\">");
			//NewTd3.innerHTML="<div align=\"center\"></div>";	
			NewTd3.innerHTML=document.getElementById("mssrxm_div").innerHTML;
		var NewTd4 = document.createElement("<td width=\"15%\" colspan=\"2\" width=\"180\" nowrap class=\"2-td2-center\">");
			NewTd4.innerHTML="<input type='text' name='cbMssrxmYz' id='' value='' size='13' maxlength='13' onblur='' onchange=\"checkNum(this);cbsjHj('Mssrxm')\"><img onclick=\"doDeleteCbRow(this,'Mssrxm')\" src=\"<%=static_contextpath%>images/zbotton-jian2.gif\" alt=\"增加\" name=\"add\" id=\"add\"  style=\"cursor:hand\">";
			//往新行里面填充单元格
			NewTr.appendChild(NewTd1);
			NewTr.appendChild(NewTd2);
			NewTr.appendChild(NewTd3);
			NewTr.appendChild(NewTd4);
			//alert(NewTr.innerHTML);
		//}		
		//添加行
		var LastTr = table.rows[table.rows.length - 1];

		LastTr.parentNode.appendChild(NewTr);
		//设置select 的样式
		var select= document.getElementById("mssrxmdm");
		//select.style.
		renameId("Mssrxm");
	}
		<%/*增加行*/%>
	function doJzmzxmAddRow()
	{	


		//获取需要添加行的表格
		var table = document.getElementById("jzmzxmAutoTable");

		//然后创建行(TR对象)
		var NewTr = document.createElement("tr");
		var NewTd1 = document.createElement("<td width=\"5%\" nowrap class=\"2-td2-left\">");
			NewTd1.innerHTML="<div align=\"center\">&nbsp;&nbsp;<input type=\"hidden\" name=\"cbJzmzxmYzhc\" value=\"\" id=\"\"></div>";
		var NewTd2 = document.createElement("<td colspan=\"2\" width=\"60%\"nowrap class=\"2-td2-left\">");
			NewTd2.innerHTML="<div align=\"left\">&#160;&#160;&#160;&#160;</div>";
		var NewTd3 = document.createElement("<td colspan=\"2\" width=\"15%\" nowrap class=\"2-td2-left\">");
			//NewTd3.innerHTML='<div align=\"center\"></div>';			
			NewTd3.innerHTML=document.getElementById("jzmzxm_div").innerHTML;
		var NewTd4 = document.createElement("<td colspan=\"2\" width=\"15%\" nowrap class=\"2-td2-center\">");
			NewTd4.innerHTML="<input type='text' name='cbJzmzxmYz' id='' value='' size='13' maxlength='13' onblur='' onchange=\"checkNum(this);cbsjHj('Jzmzxm')\"><img onclick=\"doDeleteCbRow(this,'Jzmzxm')\" src=\"<%=static_contextpath%>images/zbotton-jian2.gif\" alt=\"增加\" name=\"add\" id=\"add\"  style=\"cursor:hand\">";
			//往新行里面填充单元格
			NewTr.appendChild(NewTd1);
			NewTr.appendChild(NewTd2);
			NewTr.appendChild(NewTd3);
			NewTr.appendChild(NewTd4);
			//alert(NewTr.innerHTML);
		//}		
		//添加行
		var LastTr = table.rows[table.rows.length - 1];

		LastTr.parentNode.appendChild(NewTr);
		
		renameId("Jzmzxm");
	}
		<%/*增加行*/%>
	function doJmxmAddRow()
	{	


		//获取需要添加行的表格
		var table = document.getElementById("jmxmAutoTable");

		//然后创建行(TR对象)
		var NewTr = document.createElement("tr");
		var NewTd1 = document.createElement("<td width=\"5%\" nowrap class=\"2-td2-left\">");
			NewTd1.innerHTML="<div align=\"center\">&nbsp;&nbsp;<input type=\"hidden\" name=\"cbJmxmYzhc\" value=\"\" id=\"\"></div>";
		var NewTd2 = document.createElement("<td colspan=\"2\" width=\"60%\"nowrap class=\"2-td2-left\">");
			NewTd2.innerHTML="<div align=\"left\">&nbsp;</div>";
		var NewTd3 = document.createElement("<td colspan=\"2\" width=\"15%\" nowrap class=\"2-td2-left\">");
			//NewTd3.innerHTML='<div align=\"center\"></div>';			
			NewTd3.innerHTML=document.getElementById("jmxm_div").innerHTML;
		var NewTd4 = document.createElement("<td colspan=\"2\" width=\"15%\" nowrap class=\"2-td2-center\">");
			NewTd4.innerHTML="<input type='text' name='cbJmxmYz' id='' value='' size='13' maxlength='13' onblur='' onchange=\"checkNum(this);cbsjHj('Jmxm')\"><img onclick=\"doDeleteCbRow(this,'Jmxm')\" src=\"<%=static_contextpath%>images/zbotton-jian2.gif\" alt=\"增加\" name=\"add\" id=\"add\"  style=\"cursor:hand\">";
			//往新行里面填充单元格
			NewTr.appendChild(NewTd1);
			NewTr.appendChild(NewTd2);
			NewTr.appendChild(NewTd3);
			NewTr.appendChild(NewTd4);
			//alert(NewTr.innerHTML);
		//}		
		//添加行
		var LastTr = table.rows[table.rows.length - 1];

		LastTr.parentNode.appendChild(NewTr);
		
		renameId("Jmxm");
	}		

	//传入对象和从表的项目类型参数，删除其所在的行
	function doDeleteCbRow(obj,cbxmlx) {  
		var tableId;var xmYzhc;
		if("Mssrxm"==cbxmlx){
			tableId="mssrxmAutoTable";
			xmYzhc=document.getElementsByName("cbMssrxmYzhc");
		}else if("Jzmzxm"==cbxmlx){
			tableId="jzmzxmAutoTable";
			xmYzhc=document.getElementsByName("cbJzmzxmYzhc");
		}else if("Jmxm"==cbxmlx){
			tableId="jmxmAutoTable";
			xmYzhc=document.getElementsByName("cbJmxmYzhc");
		}	
		if(xmYzhc.length<=1){
			alert("最少需要保留一条记录，不能删除！");
			return false;
		}
		
		var tr = obj.parentNode.parentNode;
		//alert(tr.innerHTML);
	    var cbTable=document.getElementById(tableId);  

		cbTable.rows[0].parentNode.removeChild(tr);
		renameId(cbxmlx);
		cbsjHj(cbxmlx);
	} 
	
		//重置从表数据
	function doDeleteAllCbRow() {  
		var objs=document.getElementsByName("cbMssrxmYz");
		while(objs.length>1){
			var tr = objs[1].parentNode.parentNode;
			var cbTable=document.getElementById("mssrxmAutoTable");  		
			cbTable.rows[0].parentNode.removeChild(tr);		
		}
		objs[0].value="0.00";
		document.forms[0].mssrxmdm[0].value="0101";
		renameId("Mssrxm");
		
		var objs=document.getElementsByName("cbJzmzxmYz");
		while(objs.length>1){
			var tr = objs[1].parentNode.parentNode;
			var cbTable=document.getElementById("jzmzxmAutoTable");  		
			cbTable.rows[0].parentNode.removeChild(tr);		
		}
		objs[0].value="0.00";
		document.forms[0].jzmzxmdm[0].value="0101";
		renameId("Jzmzxm");
		
		var objs=document.getElementsByName("cbJmxmYz");
		while(objs.length>1){
			var tr = objs[1].parentNode.parentNode;
			var cbTable=document.getElementById("jmxmAutoTable");  		
			cbTable.rows[0].parentNode.removeChild(tr);		
		}
		objs[0].value="0.00";
		document.forms[0].jmxmdm[0].value="0101";
		renameId("Jmxm");

	} 	
	//传入从表的项目类型参数，计算相应的合计
	function cbsjHj(cbxmlx){
		var cbYzs;var hj;
		var cbsjHj=0;
		if("Mssrxm"==cbxmlx){
			//cbYzs=document.forms[0].cbMssrxmYz;
			cbYzs=document.getElementsByName("cbMssrxmYz");
			hj=document.getElementById("8_1");
		}else if("Jzmzxm"==cbxmlx){
			cbYzs=document.getElementsByName("cbJzmzxmYz");
			hj=document.getElementById("9_1");
		}else if("Jmxm"==cbxmlx){
			cbYzs=document.getElementsByName("cbJmxmYz");
			hj=document.getElementById("14_1");
			var xwjm=document.getElementById("15_1")==null?0:document.getElementById("15_1").value;
			cbsjHj+=parseFloat(xwjm==""?0:xwjm);			
		}else if("Jmxm_xw"==cbxmlx){
			cbYzs=document.getElementsByName("cbJmxmYz");
			hj=document.getElementById("14_1");
			var xwjm=document.getElementById("15_1")==null?0:document.getElementById("15_1").value;
			cbsjHj+=parseFloat(xwjm==""?0:xwjm);		
		}	
		
		for(var i=0;i<cbYzs.length;i++){			
			var cbYz=cbYzs[i].value;								
			if(null==cbYz||""==cbYz||"null"==cbYz){
				cbYz="0.00";
			}
			cbsjHj+=parseFloat(cbYz);
		}
		hj.value=parseFloat(cbsjHj).toFixed(2);
		
		if("Mssrxm"==cbxmlx){			
			window.event.srcElement.id="8_1";
			compute_Row_8();
		}else if("Jzmzxm"==cbxmlx){
			window.event.srcElement.id="9_1";
			compute_Row_8_1();
		}else if("Jmxm"==cbxmlx){
			window.event.srcElement.id="14_1";
			compute_Row_13();
		}else if("Jmxm_xw"==cbxmlx){
			window.event.srcElement.id="14_1";
			compute_Row_13();
			window.event.srcElement.id="15_1";
		}			
	}
 
 
 function insertMssrxmDmb(mssrxmdm,mssrxm_div)
{
var obj=mssrxm_div;

var select1 = document.createElement("select");
       // var ooption = new Array();
       var state = "0";
       var   oOption  =null;
        for(var i=0;i<mssrxmDmList.length;i++)
        {
        	if(mssrxmDmList[i][2] == "1"){
        		var   group   =   document.createElement('OPTGROUP');   
                group.label   =   mssrxmDmList[i][1]; 
                select1.appendChild(group); 
        	}else if(mssrxmDmList[i][2] == "2"){
        	   oOption   =   document.createElement("OPTION");   
				select1.options.add(oOption);   
				oOption.text= mssrxmDmList[i][1];
	            oOption.value= mssrxmDmList[i][0];
        	}
    
		
                if(mssrxmdm!=""&&mssrxmDmList[i][0]==mssrxmdm){
               oOption.selected=true;
               }
        }
        select1.id="mssrxmdm";
        select1.onchange="renameId('Mssrxm')";
        //免税收入
        select1.style.width="100%";
        obj.appendChild(select1);
}

 function insertJzmzxmDmb(jzmzxmdm,jzmzxm_div)
{
var obj=jzmzxm_div;
//alert(obj);
var select1 = document.createElement("select");
       // var ooption = new Array();
       var state = "0";
       var   oOption  =null;
        for(var i=0;i<jzmzxmDmList.length;i++)
        {
        	if(jzmzxmDmList[i][2] == "1"){
        		var   group   =   document.createElement('OPTGROUP');   
                group.label   =   jzmzxmDmList[i][1]; 
                select1.appendChild(group); 
        	}else if(jzmzxmDmList[i][2] == "2"){
        	   oOption   =   document.createElement("OPTION");   
				select1.options.add(oOption);   
				oOption.text= jzmzxmDmList[i][1];
	            oOption.value= jzmzxmDmList[i][0];
        	}
    
		
                if(jzmzxmdm!=""&&jzmzxmDmList[i][0]==jzmzxmdm){
               oOption.selected=true;
               }
        }
        select1.id="jzmzxmdm";
        select1.onchange="renameId('Jzmzxm')";
        //减征、免征应纳税所得额
        select1.style.width="100%";
        obj.appendChild(select1);
}

 function insertJmxmDmb(jmxmdm,jmxm_div)
{
//var obj=document.getElementById("jmxm_div");
var obj=jmxm_div;
var select1 = document.createElement("select");
       // var ooption = new Array();
       var state = "0";
       var   oOption  =null;
        for(var i=0;i<jmxmDmList.length;i++)
        {
        	if(jmxmDmList[i][2] == "1"){
        		var   group   =   document.createElement('OPTGROUP');   
                group.label   =   jmxmDmList[i][1]; 
                select1.appendChild(group); 
        	}else if(jmxmDmList[i][2] == "2"){
        	   oOption   =   document.createElement("OPTION");   
				select1.options.add(oOption);   
				oOption.text= jmxmDmList[i][1];
	            oOption.value= jmxmDmList[i][0];
        	}
    
		
               if(jmxmdm!=""&&jmxmDmList[i][0]==jmxmdm){
               	oOption.selected=true;
               }
        }
        select1.id="jmxmdm";
        select1.onchange="renameId('Jzmzxm')";
      	//减：减免所得税额
        select1.style.width="100%";
        obj.appendChild(select1);
} 


function initDmb(){
	var cbMssrxmDms=document.getElementsByName("cbMssrxmDm");
	for(var i=0;i<cbMssrxmDms.length;i++){
		insertMssrxmDmb(cbMssrxmDms[i].value,cbMssrxmDms[i].parentNode);
	}
	var cbJzmzxmDms=document.getElementsByName("cbJzmzxmDm");
	for(var i=0;i<cbJzmzxmDms.length;i++){
		insertJzmzxmDmb(cbJzmzxmDms[i].value,cbJzmzxmDms[i].parentNode);
	}
	var cbJmxmDms=document.getElementsByName("cbJmxmDm");
	for(var i=0;i<cbJmxmDms.length;i++){
		insertJmxmDmb(cbJmxmDms[i].value,cbJmxmDms[i].parentNode);
	}	
		
} 

	//传入从表的项目类型参数，对相应的从表进行重命名和赋值
	function renameId(cbxmlx){
		var mxnum;var cbYz;var cbYzhc;var cbDm;var cbDmhc;var xmdm;var xmPreId;
		if("Mssrxm"==cbxmlx){
			mxnum = document.getElementsByName("cbMssrxmYz").length;
			cbYz=document.getElementsByName("cbMssrxmYz");
			cbYzhc=document.getElementsByName("cbMssrxmYzhc");
			cbDm=document.getElementsByName("cbMssrxmDm");
			cbDmhc=document.getElementsByName("cbMssrxmDmhc");
			xmdm=document.getElementsByName("mssrxmdm");
			xmPreId="777.";
		}else if("Jzmzxm"==cbxmlx){
			mxnum = document.getElementsByName("cbJzmzxmYz").length;
			cbYz=document.getElementsByName("cbJzmzxmYz");
			cbYzhc=document.getElementsByName("cbJzmzxmYzhc");
			cbDm=document.getElementsByName("cbJzmzxmDm");
			cbDmhc=document.getElementsByName("cbJzmzxmDmhc");
			xmdm=document.getElementsByName("jzmzxmdm");
			xmPreId="888.";
		}else if("Jmxm"==cbxmlx){
			mxnum = document.getElementsByName("cbJmxmYz").length;
			cbYz=document.getElementsByName("cbJmxmYz");
			cbYzhc=document.getElementsByName("cbJmxmYzhc");
			cbDm=document.getElementsByName("cbJmxmDm");
			cbDmhc=document.getElementsByName("cbJmxmDmhc");
			xmdm=document.getElementsByName("jmxmdm");
			xmPreId="999.";
		}		
		for(var i=0;i<mxnum*2;i++){
			var j=i/2;
			cbDm[j].setAttribute("id",(i+1)+"");
			cbDm[j].setAttribute("value",xmdm[j].value);
			cbDmhc[j].setAttribute("value",xmPreId+(i+1));
			cbDmhc[j].setAttribute("id","cb"+cbxmlx+"Dmhc_"+(i+1));
			
			cbYz[j].setAttribute("id",(i+2)+"");
			cbYzhc[j].setAttribute("value",xmPreId+(i+2));
			cbYzhc[j].setAttribute("id","cb"+cbxmlx+"Yzhc_"+(i+2));

			i++;
		}
	}
	
function cbSaveCheck(){
	renameId("Mssrxm");
	renameId("Jzmzxm");
	renameId("Jmxm");
	cbsjHj("Mssrxm");
	cbsjHj("Jzmzxm");
	cbsjHj("Jmxm");
}
	function checkNum(obj)
	{
		//判断输入的数据是否符合要求
		if(!isNum(obj, null, null, null, null, 2)){
			obj.value="0.00";
			return false;			
		}
		//格式化数据
		formate(obj);
	}
	
	function setInputNew(){
		var mssrxmYzHj=document.getElementById("8_1");
		var jzmzxmYzHj=document.getElementById("9_1");
		var jmxmYzHj=document.getElementById("14_1");
		
		mssrxmYzHj.readOnly=true;
		mssrxmYzHj.style.backgroundColor="#FAEBD7";
		jzmzxmYzHj.readOnly=true;
		jzmzxmYzHj.style.backgroundColor="#FAEBD7";
		jmxmYzHj.readOnly=true;
		jmxmYzHj.style.backgroundColor="#FAEBD7";
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
<form name="czzsjb2014Form" action="czzsqyjb2014.dc">
<input name="actionType" type="hidden" id="actionType" value="Show">
	<table width="1000" align="center" cellspacing="0" class="table-99" onKeyDown="jsjdmQuery()">
		<tr>
		<td align="center" valign="top" colspan="7">
        <jsp:include page="../../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="企业所得税月(季)度预缴纳税申报表(A类)" />
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
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('View');return false;"><img name="spage" value="查看分配表" alt="查看分配表" onMouseOver="MM_swapImage('chakan','','<%=static_contextpath%>/images/chakfpb2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/chakfpb1.jpg" width="79" height="22" id="chakan"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return doSubmit('Delete');return false;"><img name="dpage" value="删除" alt="删除" onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>/images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/shanchu1.jpg" width="79" height="22" id="shanchu"></a>
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