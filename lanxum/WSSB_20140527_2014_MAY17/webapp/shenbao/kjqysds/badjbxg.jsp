<%@page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="shtml" %>
<%@ taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@ page import ="java.util.HashMap"%>
<%@ page import="com.ttsoft.bjtax.shenbao.sbzl.kjqysds.KjqysdsConstant"%>
<%@ page import="com.ttsoft.common.model.UserData"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
	System.out.println("menu url is ::" + (String)request.getSession().getAttribute(com.ttsoft.common.util.SessionKey.PARAM_MENU));
	// 总条数
	int totalCount = Integer.parseInt((String) request.getAttribute("totalCount"));
	// 当前页
	int currentPage = Integer.parseInt((String) request.getAttribute("currentPage"));
	// 总页数
	int totalPage = Integer.parseInt((String) request.getAttribute("totalPage"));
%>
<html>
<head>
<title>扣缴企业所得税备案登记表管理</title>
	<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" type = "text/JavaScript" src="js/function.js"></script>
	<script language="JavaScript" type = "text/JavaScript" src="js/gmit_selectcontrol.js"></script>
	<script language="JavaScript" type = "text/JavaScript" src="js/page.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="gojs/KjqysdsXmlBuild.js"></script>
	<style>
		.1-t-td2 {
			font-size: 9pt;
			color: #3C5564;
			background-color: f3f3f3;
			text-align: center;
			border-top: 1px solid #336699;
			border-right: 1px solid #336699;
			border-bottom: 1px solid #336699;
			border-left: 1px solid #336699;
			clip:  rect(-1px auto auto auto);
		}
		.2-td2-left-qysds1 {
			font-size: 9pt;
			line-height: 26px;
			color: #3E6071;
			text-align: left;
			border-top: 0px solid;
			border-right: 0px solid;
			border-bottom: 1px solid #9BB4CA;
			border-left: 1px solid #9BB4CA;
			background-color: #F3F5F8;
			padding-left: 20px;

		}
		.2-td2-t-left-qysds1 {
			font-size: 9pt;
			line-height: 26px;
			color: #3E6071;
			text-align: left;
			border-top: 1px solid #9BB4CA;
			border-right: 0px solid #9BB4CA;
			border-bottom: 1px solid #9BB4CA;
			border-left: 1px solid #9BB4CA;
			background-color: #F3F5F8;
			vertical-align: middle;
			padding-left: 20px;

		}
	</style>
</head>

<script language=JavaScript>
<%
	String containerName = "";
    UserData userdata = (UserData) session.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
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
var strSCHEMAVersion = '<%=request.getAttribute("CA_XML_SCHEMA_VERSION")==null?"":request.getAttribute("CA_XML_SCHEMA_VERSION")%>';
var strREQUEST_TOKEN = '<%=request.getAttribute("REQUEST_TOKEN")==null?"":request.getAttribute("REQUEST_TOKEN")%>';
var xmlDoc;

//解析xml
function parseXmlOnLoad()
{
	//var urlXSL="/XSLTWEB/model/010031/XSLT/" +strXSLTVersion+".xsl";
	var urlXSL="/XSLTWEB/model/010032/XSLT/badjbxg.xsl";
    if (strXml != "")
    {
        if (! parseXml(strXml,urlXSL,"badjb_output"))
            return false;
		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.async = false;
		xmlDoc.loadXML(strXml);
    }

	//doInit()
	//alert(document.getElementById("badjb_output").innerHTML);
	//document.getElementById("badjb_output").style.display = "";
    return true;
}

// 初始化页面信息
function doInit()
{
}

// 录入备案登记表
function doLr()
{	
	document.forms[0].actionType.value = "Show";
    document.forms[0].submit();
}

// 校验页面录入域的合法性
function checkValidity()
{
	
	return true;
}


// 提交
function doSubmit(actionType){
	var form=document.forms[0];
	if(actionType=='Reset'){
       clearInput();
       //setInput();
	}
	else if(actionType=='Modify')
	{
		if(isSelected(document.forms[0].dshRadio))
		{
			if(confirm("修改选中记录内容，是否继续？")){
				doModify();
			}
		}
		else{
			alert("请选择一条记录后再进行此操作！");
		}
	}
	else if(actionType=='Delete')
	{
		if(isSelected(document.forms[0].dshRadio))
		{
			if(confirm("当前操作会将删除数据库数据，且不可恢复，是否继续？")){
				doDelete();
			}
		}
	}
	else if(actionType=='Return'){
		doReturn();
	}
}

// 删除
function doDelete()
{
    var old  = document.forms[0].ywczlx.value;
    if((document.forms[0].ywczlx.value == 0))
    {
        document.forms[0].actionType.value="Delete";
		document.forms[0].badjbxh.value = getSelectedValue(document.forms[0].dshRadio);
		//alert(document.forms[0].badjbxh.value);
		document.forms[0].currentPage.value = "<%=currentPage%>";
        //将业务操作类型置为删除状态
        changeLocalYwczlx("3");

        if (g_objSI.Container != '')
        {
            if (!doSubmitXML(document.forms[0],"Delete",true,"",true))
            {
                changeLocalYwczlx(old);
                return false;
            }
        }
        else
        {
            if (!doSubmitXML(document.forms[0],"Delete",false,"",true))
            {
                changeLocalYwczlx(old);
                return false;
            }
        }
        return true;
    }
    else
    {
        return false;
    }
}

// 修改
function doModify(){
	var old  = document.forms[0].ywczlx.value;

	document.forms[0].badjbxh.value = getSelectedValue(document.forms[0].dshRadio);
	document.forms[0].currentPage.value = "<%=currentPage%>";
	document.forms[0].modifyFlag.value = "1";

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
        alert("未知的操作类型：" + document.forms[0].ywczlx.value);
        return false;
    }

	document.forms[0].actionType.value="Modify";
	if (g_objSI.Container != '')
	{
		if (!doSubmitXML(document.forms[0],"Modify",true,"",true))
		{
			document.forms[0].ywczlx.value = old;
			return false;
		}	
	}
	else
	{
		if (!doSubmitXML(document.forms[0],"Modify",false,"",true))
		{
			document.forms[0].ywczlx.value = old;
			return false;
		}	
	}
	return true;
}

// 返回
function doReturn()
{
    if(confirm(confirmReturn))
    {
		//document.forms[0].actionType.value="Return";
		document.forms[0].action = "quit.do";
		document.forms[0].submit();
		return true;
    }
	else
    {
    	return false;
    }
}

function moveFocus(){
	if(event.keyCode==13) event.keyCode = 9;
}


// 翻页操作
function doFy(pageNo)
{
	//alert("pageNo = " + pageNo);
	var currentPage = <%=currentPage%>;
	var totalPage = <%=totalPage%>;

	//翻页查询
    //直接输入跳转页号的查询：进行输入页号的校验：
	if(totalPage > 0)
	{
		if(currentPage == 1 && pageNo <=1)
		{
			alert("当前已经是第一页，无法进行翻页。");
			return false;
		}

		if(currentPage == totalPage && pageNo >= totalPage)
		{
			alert("当前已经是最末页，无法进行翻页。");
			return false;
		}
	}
	else
	{
		return;
	}

	var old  = document.forms[0].ywczlx.value;
    if(document.forms[0].ywczlx.value == 0)
    {
		document.forms[0].actionType.value = "Fy";
		//当前页
		document.forms[0].currentPage.value = pageNo;
		//将页面指向当前页面
		document.forms[0].target = "_self";

		if (g_objSI.Container != '')
		{
			if (!doSubmitXML(document.forms[0],"Fy",true,"",true))
			{
				changeLocalYwczlx(old);
				return false;
			}
		}
		else
		{	
			if (!doSubmitXML(document.forms[0],"Fy",false,"",true))
			{
				changeLocalYwczlx(old);
				return false;
			}	
		}
		return true;
	}
	else
	{
   		return false;
	}	
}

function changeLocalYwczlx(ywczlx)
{
    //alert("ywczlx = " + ywczlx);
    var rootNode = xmlDoc.documentElement;
    var objCDATA =xmlDoc.createCDATASection(ywczlx);
    rootNode.selectSingleNode("//ywczlx").text = "";
    rootNode.selectSingleNode("//ywczlx").appendChild(objCDATA);
}
</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="return parseXmlOnLoad();" onkeydown="moveFocus()">
<form name="badjbForm" action="badjb.dc">
<input name="actionType" type="hidden" id="actionType" value=""/>
<input name="currentPage" type="hidden" id="currentPage" />
<input name="badjbxh" type="hidden" id="badjbxh" />
<input name="modifyFlag" type="hidden" id="modifyFlag" />


<jsp:include page="../../include/KjqysdsHeader.jsp" flush="true" >
	<jsp:param name="name" value="扣缴企业所得税备案登记表管理" />
    <jsp:param name="help_url" value=""/>
</jsp:include>
<br/>
<table width="1000" align="center" cellspacing="0">
    <tr>
        <td class="1-td1">扣缴企业所得税备案登记表管理 </td>
    </tr>
	<tr>
		<td class="1-td2">
			<table>
				<tr>
					<td>
						<div id="badjb_output">&nbsp;</div>
					</td>
				</tr>
				<tr>
				<td>
					<table class="table-100" border="0">
						<tr class="2-td2-t-left">
							<td align="left" width="65%">&nbsp;&nbsp;记录数：<%=totalCount%>&nbsp;&nbsp;&nbsp; 当前页/总页数：<%=currentPage%>/<%=totalPage%> &nbsp; </td>
							<td align="right" >
								<img onclick="doFy(1)" onMouseOver="MM_swapImage('dy1','','<%=static_contextpath%>/images/diyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="第一页" id="dy1" src="<%=static_contextpath%>/images/diyiye1.jpg" width="79" height="22" style="cursor:hand" tabIndex="-1"/> 
								<img onclick="doFy(<%=currentPage-1%>)" onMouseOver="MM_swapImage('sy1','','<%=static_contextpath%>/images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="上一页" id="sy1" src="<%=static_contextpath%>/images/shangyiye1.jpg" width="79" height="22" style="cursor:hand" tabIndex="-1"/> 
								<img onclick="doFy(<%=currentPage+1%>)" onMouseOver="MM_swapImage('xy1','','<%=static_contextpath%>/images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="下一页" id="xy1" src="<%=static_contextpath%>/images/xaiyiye1.jpg" width="79" height="22" style="cursor:hand" tabIndex="-1"/> 
								<img onclick="doFy(<%=totalPage%>)" onMouseOver="MM_swapImage('zm1','','<%=static_contextpath%>/images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="最末页" id="zm1" src="<%=static_contextpath%>/images/zuimoye1.jpg" width="79" height="22" style="cursor:hand" tabIndex="-1"/> 
							</td>
						</tr>
					</table>
				</td>
			</tr>
			</table>
		</td>
	</tr>
	<TR class="black9">
		<TD> 
			<br/>
			<div align="center">
            &#160;&#160;
			<img style="cursor:hand"
				tabIndex="-1" onClick="doLr();return false;" accesskey="u"
                value="录入" alt="录入备案登记表"
                onMouseOver="MM_swapImage('luru','','<%=static_contextpath%>images/b-lr2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_contextpath%>images/b-lr1.jpg"
                id="luru" />
            &#160;&#160;
            <img
				style="cursor:hand" tabIndex="-1" onClick="return doSubmit('Modify');return false;"
                accesskey="s" value="修改" alt="修改"
                onMouseOver="MM_swapImage('xiugai','','<%=static_contextpath%>images/xiugai2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_contextpath%>images/xiugai1.jpg"
                id="xiugai" />
            &#160;&#160;
			<img
				style="cursor:hand" tabIndex="-1" onClick="if(checkValidity()){doSubmit('Delete')};return false;"
                accesskey="s" value="删除" alt="删除"
                onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/sc-d2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_contextpath%>images/sc-d1.jpg"
                id="shanchu" />
            &#160;&#160;
            <img value="返回" alt="返回到主页面"
				style="cursor:hand" tabIndex="-1" onClick="javascript:return doSubmit('Return');return false;"
                onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_contextpath%>images/fanhui1.jpg"
                id="fanhui" />
		</div></TD>
	</TR>
</table>
    <br/>
    <br/>
    <br/>
<jsp:include page="../../include/bottom.jsp" flush="true"/>
</form>

</body>
</html>
