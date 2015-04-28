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
%>
<html>
<head>
<title>扣缴企业所得税备案登记表</title>
	<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" type = "text/JavaScript" src="js/function.js"></script>
	<script language="JavaScript" type = "text/JavaScript" src="js/gmit_selectcontrol.js"></script>
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

    // 构造国家列表
    if(session.getAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ) != null)
    {
        out.print("var arySelect_gjdq = new Array(");
        String[][] gjdq = (String[][])session.getAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ);
        for(int i = 0 ; i < gjdq.length ; i++)
        {
            if(i != 0)
            {
				if(gjdq[i][0] != null)
				{
					out.print(",[\"" + gjdq[i][0] + "\",\"" +gjdq[i][1] +"\"]");
				}
            }
            else
            {
                out.print("[\"" + gjdq[i][0] + "\",\"" +gjdq[i][1] +"\"]");
            }
        }
        out.println(");");
    }

	// 构造币种列表
    if(session.getAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ) != null)
    {
        out.print("var arySelect_bz = new Array(");
        String[][] bz = (String[][])session.getAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ);
        for(int i = 0 ; i < bz.length ; i++)
        {
            if(i != 0)
            {
                out.print(",[\"" + bz[i][0] + "\",\"" +bz[i][1] +"\"]");
            }
            else
            {
                out.print("[\"" + bz[i][0] + "\",\"" +bz[i][1] +"\"]");
            }
        }
        out.println(");");
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
	var urlXSL="/XSLTWEB/model/010032/XSLT/badjblr.xsl";
    if (strXml != "")
    {
        if (! parseXml(strXml,urlXSL,"badjb_output"))
            return false;
    }

	doInit()
	//alert(document.getElementById("badjb_output").innerHTML);
	//document.getElementById("badjb_output").style.display = "";
    return true;
}

// 初始化页面信息
function doInit()
{
	// 初始化国家下拉菜单
	_addOptionsToSelect(document.forms[0].fjmgbSelect, arySelect_gjdq);
	// 初始化币种下拉菜单
	_addOptionsToSelect(document.forms[0].bzSelect, arySelect_bz);

	//设置下拉菜单初始值
	var fjmgbdm = document.forms[0].fjmgbdm.value;
	if(null != fjmgbdm && "" != fjmgbdm)
	{
		_selectOptionByValue(document.forms[0].fjmgbSelect, fjmgbdm);
	}

	var bzdm = document.forms[0].bzdm.value;
	if(null != bzdm && "" != bzdm)
	{
		_selectOptionByValue(document.forms[0].bzSelect, bzdm);
	}
	// 初始化单选框
	changeRadio(document.forms[0].fjmgbSelect.value);
}

// 回车查询纳税人信息
function doJsjdmQuery()
{
	if(event.keyCode==13) doQuery();
}

// 查询扣缴人信息
function doQuery()
{
	document.forms[0].actionType.value = "QueryKjrInfo";
    document.forms[0].submit();
}

// 清除页面信息
function doReset()
{	
	document.forms[0].actionType.value = "Show";
    document.forms[0].submit();
}

// 校验页面录入域的合法性
function checkValidity()
{
	// 扣缴人英文名称
	if(document.getElementById("kjywrmc_en").value == null || document.getElementById("kjywrmc_en").value == "")
	{
		alert("请输入扣缴义务人英文名称！");
		document.getElementById("kjywrmc_en").focus();
		return false;
	}
	//修改时间2010-12-06  author:guoxj
	//扣缴英文名称过长进行提示
	if(document.getElementById("kjywrmc_en").value!=null)
	{
		var kjywrmc = document.getElementById("kjywrmc_en").value;
	     if(kjywrmc.length>50){
			 alert("输入扣缴义务人英文名称过长！");
			document.getElementById("kjywrmc_en").focus();
			return false;
		}else{
		   return true;
		}
	}
	// 扣缴人财务负责人
	if(document.getElementById("kjywrcwfzr").value == null || document.getElementById("kjywrcwfzr").value == "")
	{
		alert("请输入扣缴义务人财务负责人！");
		document.getElementById("kjywrcwfzr").focus();
		return false;
	}
	// 扣缴人联系人
	if(document.getElementById("kjywrlxr").value == null || document.getElementById("kjywrlxr").value == "")
	{
		alert("请输入扣缴义务人联系人！");
		document.getElementById("kjywrlxr").focus();
		return false;
	}
	// 扣缴人电话
	if(document.getElementById("kjywrdh").value == null || document.getElementById("kjywrdh").value == "")
	{
		alert("请输入扣缴义务人电话！");
		document.getElementById("kjywrdh").focus();
		return false;
	}
	// 扣缴人传真
	if(document.getElementById("kjywrcz").value == null || document.getElementById("kjywrcz").value == "")
	{
		alert("请输入扣缴义务人传真！");
		document.getElementById("kjywrcz").focus();
		return false;
	}

	// 非居民企业中文名称
	if(document.getElementById("fjmqymc_cn").value == null || document.getElementById("fjmqymc_cn").value == "")
	{
		alert("请输入非居民企业中文名称！");
		document.getElementById("fjmqymc_cn").focus();
		return false;
	}
	// 非居民企业英文名称
	if(document.getElementById("fjmqymc_en").value == null || document.getElementById("fjmqymc_en").value == "")
	{
		alert("请输入非居民企业英文名称！");
		document.getElementById("fjmqymc_en").focus();
		return false;
	}
	// 非居民企业国家或地区
	if(document.getElementById("gat").checked == false && document.getElementById("wg").checked == false)
	{
		alert("请选择非居民企业的国家或地区！");
		document.getElementById("gat").focus();
		return false;
	}
	// 非居民企业居民国地址（中文）
	if(document.getElementById("fjmqyjmgdz_cn").value == null || document.getElementById("fjmqyjmgdz_cn").value == "")
	{
		alert("请输入非居民企业其居民国中文地址！");
		document.getElementById("fjmqyjmgdz_cn").focus();
		return false;
	}
	// 非居民企业居民国地址（英文）
	if(document.getElementById("fjmqyjmgdz_en").value == null || document.getElementById("fjmqyjmgdz_en").value == "")
	{
		alert("请输入非居民企业其居民国英文地址！");
		document.getElementById("fjmqyjmgdz_en").focus();
		return false;
	}
	// 非居民企业财务负责人
	if(document.getElementById("fjmqycwfzr").value == null || document.getElementById("fjmqycwfzr").value == "")
	{
		alert("请输入非居民企业财务负责人！");
		document.getElementById("fjmqycwfzr").focus();
		return false;
	}
	// 非居民企业联系人
	if(document.getElementById("fjmqylxr").value == null || document.getElementById("fjmqylxr").value == "")
	{
		alert("请输入非居民企业联系人！");
		document.getElementById("fjmqylxr").focus();
		return false;
	}
	// 非居民企业电话
	if(document.getElementById("fjmqydh").value == null || document.getElementById("fjmqydh").value == "")
	{
		alert("请输入非居民企业电话！");
		document.getElementById("fjmqydh").focus();
		return false;
	}	
	// 非居民企业传真
	if(document.getElementById("fjmqycz").value == null || document.getElementById("fjmqycz").value == "")
	{
		alert("请输入非居民企业传真！");
		document.getElementById("fjmqycz").focus();
		return false;
	}

	// 合同或协议名称
	if(document.getElementById("htxymc").value == null || document.getElementById("htxymc").value == "")
	{
		alert("请输入合同或协议名称！");
		document.getElementById("htxymc").focus();
		return false;
	}
	// 合同编号
	if(document.getElementById("htxybh").value == null || document.getElementById("htxybh").value == "")
	{
		alert("请输入合同编号！");
		document.getElementById("htxybh").focus();
		return false;
	}
	// 合同签约日期
	if(document.getElementById("htqyrq").value == null || document.getElementById("htqyrq").value == "")
	{
		alert("请输入合同签约日期！");
		document.getElementById("htqyrq").focus();
		return false;
	}
	// 校验合同签约日期
	//if(!checkQyrq(document.getElementById("htqyrq")))
	//{
		//return false;
	//}
	// 合同有效期限
	if(document.getElementById("htyxqx").value == null || document.getElementById("htyxqx").value == "")
	{
		alert("请输入合同有效期限！");
		document.getElementById("htyxqx").focus();
		return false;
	}
	// 合同金额
	if(document.getElementById("htzje").value == null || document.getElementById("htzje").value == "")
	{
		alert("请输入合同金额！");
		document.getElementById("htzje").focus();
		return false;
	}
	// 支付项目
	if(document.getElementById("htzfxm").value == null || document.getElementById("htzfxm").value == "")
	{
		alert("请输入合同支付项目！");
		document.getElementById("htzfxm").focus();
		return false;
	}
	// 付款次数
	if(document.getElementById("htfkcs").value == null || document.getElementById("htfkcs").value == "")
	{
		alert("请输入合同付款次数！");
		document.getElementById("htfkcs").focus();
		return false;
	}
	// 其他资料名称
	if(document.getElementById("htqtzlmc").value == null || document.getElementById("htqtzlmc").value == "")
	{
		alert("请输入合同其他资料名称！");
		document.getElementById("htqtzlmc").focus();
		return false;
	}
	// 判断合同签约日期与合同有效期关系
	if(!compareHtDate())
	{
		return false;
	}

	return true;
}

/**
 * 检验数字录入格式
 * obj 需要校验的对象
 * type 校验类型：1-带小数的校验|2-整数校验
 */
function checkNumInput(obj, type)
{
	if(type == 1)
	{
		//判断输入的数据是否符合要求
		if(!isNum(obj , null, null, null, null, 2)){
			return false;			
		}
		//格式化数据
		//formate(obj);
	}
	else if(type == 2)
	{
		if((obj.value != null && obj.value != "")){
			if(!fnIsIntNum(obj.value))
			{
				alert("输入的信息不符合规范，请输入大于1的整数！");
				obj.select();
				obj.focus();
				return;
			}
			else{
				obj.value = Number(obj.value);
			}
		}
	}
}

// 校验电话号码
function isTel(object)
{
	// + 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(2-4位)"
    var s = document.getElementById(object.id).value; 
    var pattern =/^(([0\+]\d{2,3}-)?(0\d{2,4})-)(\d{7,8})(-(\d{2,4}))?$/;
	var mobile = /^(((13[0-9]{1})|159|(15[0-9]{1}))+\d{8})$/;
    //var pattern =/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/; 
    if(s!="")
    {
		if(!pattern.exec(s) && !mobile.test(s))
        {
			alert('请输入正确的电话号码或手机号码:电话号码格式允许国家代码(2到3位)-区号(2到4位)-电话号码(7到8位)-分机号(2-4位)"');
            object.select();
            object.focus();
			return;
        }
    }
}

// 校验日期格式
function checkDateInput(obj)
{
	if(obj.value != null && obj.value !="")
	{
		isDate(obj);
	}
}

// 校验合同签约日期
function checkQyrq(obj)
{
	var strDate1 = obj.value;
    var strYear1 = strDate1.substr(0,4);
    var strMonth1 = strDate1.substr(4,2);
    var strDay1 = strDate1.substr(6,2);

	strDate1 = strYear1 + "/" + strMonth1 + "/" + strDay1;
	
	strDate1 = new Date(strDate1);
	strDate2 = new Date();
	//alert("strDate1 = " + strDate1 + "\nstrDate2 = " + strDate2);

	if(strDate1 > strDate2)
	{
		alert("合同签约日期不能大于当前时间！");
		obj.select();
		obj.focus();
		return false;
	}

	var times = strDate2.getTime() - strDate1.getTime();
	// 除以毫秒级日期数量级，换算所差天数
	var days = parseInt(times / (1000 * 60 * 60 * 24));
	//alert("相差天数: " + days);

    if(days > 30)
	{
		alert("合同签约日期与当前时间相差30天以上，请上门办理此业务！");
		return false;
	}

	return true;
}

// 提交
function doSubmit(actionType){
	var form=document.forms[0];
	if(actionType=='Reset'){
       clearInput();
       //setInput();
	}
	else if(actionType=='Save')
	{
		if(confirm("当前操作会将您报备的登记表信息上传至税务机关，是否继续？")){
			doSave();
		}
	}
	else if(actionType=='Delete')
	{
		if(confirm("当前操作会将删除数据库数据，且不可恢复，是否继续？")){
			doDelete();
		}
	}
	else if(actionType=='Exit'){
		doExit();
	}
	else if(actionType=='Return'){
		doReturn();
	}
}

// 保存
function doSave(){
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
	}
	else
	{
		if (!doSubmitXML(document.forms[0],"Save",false,"",true))
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
		document.forms[0].actionType.value="Return";
		document.forms[0].submit();
		return true;
    }
	else
    {
    	return false;
    }
}

// 退出
function doExit()
{
    if(confirm("是否退出？"))
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

// 设置单选框
function changeRadio(value)
{
	// 如果选中香港(HKG)、澳门(MAC)、台湾(TWN)，则选中港澳台，否而选中外国
	if(value == "HKG" || value == "MAC" || value == "TWN")
	{
		document.getElementById("gat").checked = true;
		document.getElementById("fjmgjdq").value = "01";
	}
	else
	{
		document.getElementById("wg").checked = true;
		document.getElementById("fjmgjdq").value = "02";
	}
}

function moveFocus(){
	if(event.keyCode==13 && document.activeElement.id != "htqtzlmc") event.keyCode = 9;
}


// 日期比较
function compareHtDate()
{
	var strDate1 = document.forms[0].htyxqx.value;		
	var strYear1 = strDate1.substr(0,4);		
	var strMonth1 = strDate1.substr(4,2);
 	var strDay1 = strDate1.substr(6,2);
 	 	
 	var strDate2 = document.forms[0].htqyrq.value;
 	var strYear2 = strDate2.substr(0,4);		
	var strMonth2 = strDate2.substr(4,2);
 	var strDay2 = strDate2.substr(6,2);

	var htyxq = new Date(strMonth1+'-'+strDay1+'-'+strYear1);
	var htqyrq = new Date(strMonth2+'-'+strDay2+'-'+strYear2);
	
	if(!(htyxq > htqyrq)){
		alert("合同有效期限必须大于合同签约日期，请修改！");
		window.event.returnValue=false;
		//document.forms[0].htyxqx.focus();
		//document.forms[0].htyxqx.select();
		return false;
	}

	return true;
}
</script>


<!--%@ include file="../../include/KjqysdsHeader.jsp"%-->

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="return parseXmlOnLoad();" onkeydown="moveFocus()">
<form name="badjbForm" action="badjb.dc">
<input name="actionType" type="hidden" id="actionType" value=""/>
<input name="currentPage" type="hidden" id="currentPage" value="1"/>


<jsp:include page="../../include/KjqysdsHeader.jsp" flush="true" >
	<jsp:param name="name" value="报备企业所得税合同备案登记表" />
    <jsp:param name="help_url" value=""/>
</jsp:include>
<br/>
<table width="1000" align="center" cellspacing="0">
    <tr>
        <td class="1-td1">扣缴企业所得税备案登记表 </td>
    </tr>
	<tr>
		<td class="1-td2">
			<div id="badjb_output">&nbsp;</div>
		</td>
	</tr>
	<TR class="black9">
		<TD> 
			<br/>
			<div align="center">
            &#160;&#160;
			<img style="cursor:hand"
				tabIndex="-1" onClick="doReset();return false;" accesskey="u"
                value="清除" alt="清除页面输入框信息"
                onMouseOver="MM_swapImage('chongtian','','<%=static_contextpath%>images/chongtian2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_contextpath%>images/chongtian1.jpg"
                id="chongtian" />
            &#160;&#160;
            <img
				style="cursor:hand" tabIndex="-1" onClick="if(checkValidity()){doSubmit('Save')};return false;"
                accesskey="s" value="保存" alt="保存"
                onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_contextpath%>images/bc-s1.jpg"
                id="baocun" />
            &#160;&#160;
            <img value="返回" alt="返回到修改管理页面"
				style="cursor:hand" tabIndex="-1" onClick="javascript:return doSubmit('Return');return false;"
                onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_contextpath%>images/fanhui1.jpg"
                id="fanhui" />
			&#160;&#160;
            <img value="退出" alt="退回到主页面"
				style="cursor:hand" tabIndex="-1" onClick="javascript:return doSubmit('Exit');return false;"
                onMouseOver="MM_swapImage('tuichu','','<%=static_contextpath%>images/tuichu2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_contextpath%>images/tuichu1.jpg"
                id="tuichu" />
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
