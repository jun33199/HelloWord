<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import ="java.util.HashMap"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.web.BadjbForm"%>

<%
	// 获取BadjbForm
	BadjbForm form = (BadjbForm) request.getAttribute("badjbForm");
	// 非居民国别
	String fjmgb = form.getFjmqyxx().getFjmgb();
	System.out.println("fjmgb = " +fjmgb);
	// 币种
	String bzdm = form.getHtxx().getBz();
	System.out.println("bzdm = " +bzdm);
	System.out.println("flag = " + form.getModifyFlag());

%>
<html>
<head>
<title>录入扣缴企业所得税合同备案登记表</title>
	<link href="../../../css/text.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" type="text/JavaScript" src="../../../js/smsb_common.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="../../../js/treatImage.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="../../../js/gmit_selectcontrol.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="../../../js/function.js"></script>
</head>

<script language=JavaScript>
<%
    // 构造国家列表
    if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ) != null)
    {
        out.print("var arySelect_gjdq = new Array(");
        String[][] gjdq = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ);
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
    if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ) != null)
    {
        out.print("var arySelect_bz = new Array(");
        String[][] bz = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ);
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
// 初始化页面信息
function doInit()
{
	// 初始化国家下拉菜单
	_addOptionsToSelect(document.forms[0].fjmgbSelect, arySelect_gjdq);
	// 初始化币种下拉菜单
	_addOptionsToSelect(document.forms[0].bzSelect, arySelect_bz);

	//设置下拉菜单初始值
	<%
		if(null != fjmgb && !"".equals(fjmgb))
		{
	%>
			_selectOptionByValue(document.forms[0].fjmgbSelect, "<%=fjmgb%>");
	<%
		}
		if(null != bzdm && !"".equals(bzdm))
		{
	%>
			_selectOptionByValue(document.forms[0].bzSelect, "<%=bzdm%>");
	<%
		}
	%>

	// 初始化单选框
	changeRadio(document.forms[0].fjmgbSelect.value);
}

//敲回车时移动焦点（计算机代码输入框除外）
function moveFocus(){
	if(event.keyCode==13 && document.activeElement.id != "htqtzlmc") event.keyCode = 9;
}

// 回车查询纳税人信息
function doJsjdmQuery()
{
	if(event.keyCode==13) 
	{
		doQuery();
	}
}

// 查询扣缴人信息
function doQuery()
{
	//alert("doQuery");
	document.forms[0].actionType.value = "QueryKjrInfo";
    document.forms[0].submit();
}

// 清除页面信息
function doReset()
{
	var step = false;
	if(step)
	{
		// 扣缴人计算机代码
		document.forms[0].jsjdm.value = "";
		// 扣缴人中文名称
		document.getElementById("kjywrmc_cn").innerText = "";
		// 扣缴人英文名称
		document.forms[0].kjywrmc_en.value = "";
	}
	
	document.forms[0].actionType.value = "Show";
    document.forms[0].submit();
}

// 校验页面录入域的合法性
function checkValidity()
{
	// 扣缴人计算机代码
	if(document.forms[0].jsjdm.value == null || document.forms[0].jsjdm.value == "")
	{
		alert("请输入扣缴义务人计算机代码！");
		document.forms[0].jsjdm.focus();
		return false;
	}
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

// 校验日期格式
function checkDateInput(obj)
{
	if(obj.value != null && obj.value !="")
	{
		isDate(obj);
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

// 设置单选框
function changeRadio(value)
{
	// 如果选中香港(HKG)、澳门(MAC)、台湾(TWN)，则选中港澳台，否而选中外国
	if(value == "HKG" || value == "MAC" || value == "TWN")
	{
		document.getElementById("gat").checked = true;
		//document.getElementsByName("fjmqyxx.fjmgjdq")[0].value = "01";
		document.getElementById("fjmqyxx.fjmgjdq").value = "01";
	}
	else
	{
		document.getElementById("wg").checked = true;
		//document.getElementsByName("fjmqyxx.fjmgjdq")[0].value = "02";
		document.getElementById("fjmqyxx.fjmgjdq").value = "02";
	}
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


<%@ include file="../../include/header.jsp"%>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="doInit()">
<html:form action="/webapp/smsb/qysds/kjqysds/badjbAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="fjmqyxx.fjmgjdq" />
<html:hidden property="modifyFlag" />
<html:hidden property="badjxh" />

<table width="1000" align="center" cellspacing="0" onkeydown="moveFocus()">
    <tr>
        <td class="1-td1">录入扣缴企业所得税合同备案登记表 </td>
    </tr>
    <tr>
        <td class="1-td2">编号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            填报日期:<bean:write name="badjbForm" property="tbrq" scope="request" filter="true" /><html:hidden property="tbrq" />
        </td>
    </tr>
    <tr>
        <td class="1-td2">
            <table class="table-99" align="center">
                <TR>
                    <TD> <div id="Layer2" style="position:static; ">
                        <table class="table-99" border="1" cellspacing="0" align="center">
                            <tr>
                                <td width="10%" rowspan="6" class="2-td2-t-left">扣缴义务人</td>
                                <td width="18%" class="2-td2-t-left-qysds1">计算机代码：</td>
                                <td colspan="7" class="2-td2-t-center">
                                    <div align="left">
                                        &nbsp;<html:text property="jsjdm" size="20" maxlength="8" onfocus="this.select()" onKeyDown="doJsjdmQuery()"/>&nbsp;&nbsp;<input type="button" value="查询" tabIndex="-1" onclick="doQuery()" />
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="2-td2-left-qysds1">中文名称：</td>
                                <td colspan="7" class="2-td2-center">
									<div id="kjywrmc_cn" align="left">
										&nbsp;<bean:write name="badjbForm" property="kjywrxx.kjrmc_cn" scope="request" filter="true" />
									</div>
								</td>
                            </tr>
                            <tr>
                                <td class="2-td2-left-qysds1">英文名称：</td>
                                <td colspan="7" class="2-td2-center">
									<div align="left">
										&nbsp;<html:text property="kjywrxx.kjrmc_en" styleId="kjywrmc_en" size="80" onfocus="this.select()"/>
									</div>
								</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">扣缴义务人纳税识别号：</td>
                                    <td colspan="7" class="2-td2-center"><div align="left">&nbsp;<bean:write name="badjbForm" property="kjywrxx.kjrnssbh" scope="request" filter="true" /></div></td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">地址：</td>
                                    <td colspan="3" class="2-td2-left"><div align="left">&nbsp;<bean:write name="badjbForm" property="kjywrxx.kjrdz_cn" scope="request" filter="true" /></div></td>
                                    <td width="9%" class="2-td2-left">邮编：</td>
                                    <td colspan="3" class="2-td2-center"><div align="left">&nbsp;<bean:write name="badjbForm" property="kjywrxx.kjryzbm" scope="request" filter="true" /></div></td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">财务负责人：</td>
                                    <td width="15%" class="2-td2-left">
										<div align="left">
											&nbsp;<html:text property="kjywrxx.kjrcwfzr" size="18" styleId="kjywrcwfzr" onfocus="this.select()"/>
										</div>
									</td>
                                    <td width="7%" class="2-td2-left">联系人：</td>
                                    <td width="15%" class="2-td2-left">
										<div align="left">
											&nbsp;<html:text property="kjywrxx.kjrlxr" size="18" styleId="kjywrlxr" onfocus="this.select()"/>
										</div>
									</td>
                                    <td class="2-td2-left">电话：</td>
                                    <td width="8%" class="2-td2-left">
										<div align="left">
											&nbsp;<html:text property="kjywrxx.kjrlxdh" size="15" styleId="kjywrdh" onfocus="this.select()"/>
										</div>
									</td>
                                    <td width="7%" class="2-td2-left">传真：</td>
                                    <td width="8%" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="kjywrxx.kjrczhm" size="15" styleId="kjywrcz" onfocus="this.select()" />
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td rowspan="6" class="2-td2-left">非居民企业</td>
                                    <td class="2-td2-left-qysds1">中文名称：</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmmc_cn" size="80" styleId="fjmqymc_cn" onfocus="this.select()"/>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">英文名称：</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmmc_en" size="80" styleId="fjmqymc_en" onfocus="this.select()"/>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">国别：</td>
                                    <td colspan="2" class="2-td2-left">
                                        <div align="left">
                                            &nbsp;<html:select name="badjbForm" styleId="fjmgbSelect" property="fjmqyxx.fjmgb" onChange="changeRadio(this.value)"/>
                                        </div>
                                    </td>
                                    <td class="2-td2-left">国家或地区：</td>
                                    <td colspan="4" class="2-td2-center">
                                        港澳台<input type="radio" name="gjdq" id="gat" value="01" disabled="true"/>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        外国<input type="radio" name="gjdq" id="wg" value="02" disabled="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">其居民国地址（中文）：</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmdz_cn" size="80" styleId="fjmqyjmgdz_cn" onfocus="this.select()"/>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">其居民国地址（英文）：</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmdz_en" size="80" styleId="fjmqyjmgdz_en" onfocus="this.select()"/>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">财务负责人：</td>
                                    <td class="2-td2-left">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmcwfzr" size="18" styleId="fjmqycwfzr" onfocus="this.select()"/>
										</div>
									</td>
                                    <td class="2-td2-left">联系人：</td>
                                    <td class="2-td2-left">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmlxr" size="18" styleId="fjmqylxr" onfocus="this.select()"/>
										</div>
									</td>
                                    <td class="2-td2-left">电话：</td>
                                    <td class="2-td2-left">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmlxdh" size="15" styleId="fjmqydh" onfocus="this.select()" />
										</div>
									</td>
                                    <td width="5%" class="2-td2-left">传真：</td>
                                    <td class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="fjmqyxx.fjmczhm" size="15" styleId="fjmqycz" onfocus="this.select()"/>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td rowspan="9" class="2-td2-left">合同信息</td>
                                    <td class="2-td2-left-qysds1">合同或协议名称： </td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="htxx.htmc" size="80" styleId="htxymc" onfocus="this.select()"/>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">合同编号：</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="htxx.htbh" size="80" styleId="htxybh" onfocus="this.select()"/>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">合同签约日期： </td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="htxx.qyrq" styleId="htqyrq" size="70" onblur="checkDateInput(this)"/>&nbsp;<font color="red">（日期填写格式：YYYYMMDD）</font>
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">合同有效期限：</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<html:text property="htxx.htyxq" styleId="htyxqx" size="70" onblur="checkDateInput(this);"/>&nbsp;<font color="red">（日期填写格式：YYYYMMDD）</font>
										</div>
									</td>
                                    </tr>
                                    <tr>
                                        <td class="2-td2-left-qysds1">合同金额：</td>
                                        <td colspan="7" class="2-td2-center">
											<div align="left">
											&nbsp;<html:text property="htxx.htje" size="80" styleId="htzje" onfocus="this.select()" onblur="checkNumInput(this, 1)"/>
										</div>
									</td>
                                    </tr>
                                    <tr>
                                        <td class="2-td2-left-qysds1">币种：</td>
                                        <td colspan="7" class="2-td2-center">
											<div align="left">
												&nbsp;<html:select styleId="bzSelect" property="htxx.bz" />
											</div>
										</td>
                                    </tr>
                                    <tr>
                                        <td class="2-td2-left-qysds1">支付项目：</td>
                                        <td colspan="7" class="2-td2-center">
											<div align="left">
											&nbsp;<html:text property="htxx.zfxm" size="80" styleId="htzfxm" onfocus="this.select()"/>
										</div>
									</td>
                                    </tr>
                                    <tr>
                                        <td class="2-td2-left-qysds1">付款次数：</td>
                                        <td colspan="7" class="2-td2-center">
											<div align="left">
											&nbsp;<html:text property="htxx.fkcs" size="80" styleId="htfkcs" onfocus="this.select()" onblur="checkNumInput(this, 2)"/>
										</div>
									</td>
                                    </tr>
                                    <tr>
                                        <td class="2-td2-left-qysds1">其他资料名称：</td>
                                        <td colspan="7" class="2-td2-center">
											<div align="left">
												&nbsp;<html:textarea property="htxx.qtzlmc" rows="3" cols="80%" styleId="htqtzlmc" onfocus="this.select()"/>
											</div>
										</td>
                                    </tr>
                                </table>
                            </div>
                        </TD>
                    </TR>
                    <TR class="black9">
                        <TD> 
							<br/>
							<div align="center">
                            <input type="image" style="cursor:hand"
                                tabIndex="-1" onClick="doReset();return false;" accesskey="R"
                                value="重填" alt="清除页面输入框信息"
                                onMouseOver="MM_swapImage('chuntian','','<%=static_contextpath%>images/chongtian2.jpg',1)"
                                onMouseOut="MM_swapImgRestore()"
                                src="<%=static_contextpath%>images/chongtian1.jpg"
                                id="chuntian" />
                            &nbsp;&nbsp;
                            <input type="image"
                                style="cursor:hand" tabIndex="-1" onClick="if(checkValidity()){doSubmitForm('Save')};return false;"
                                accesskey="s" value="保存" alt="保存"
                                onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
                                onMouseOut="MM_swapImgRestore()"
                                src="<%=static_contextpath%>images/bc-s1.jpg"
                                id="baocun" />
                            &nbsp;&nbsp;
                            <input type="image" value="返回" alt="返回到主页面"
                                style="cursor:hand" tabIndex="-1" onClick="tuichu();return false;"
                                onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
                                onMouseOut="MM_swapImgRestore()"
                                src="<%=static_contextpath%>images/fanhui1.jpg"
                                id="fanhui" />
                            </div>
							<br/>
                        </TD>
                    </TR>
                </TABLE>
            </td>
        </tr>
    </table>
    <br/>
    <br/>
    <br/>
<%@ include file="../../include/footernew.jsp"%>
</html:form>

</body>
</html>
