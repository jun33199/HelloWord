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
%>
<html>
<head>
<title>扣缴企业所得税备案登记表</title>
	<link href="../../../css/text.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" type="text/JavaScript" src="../../../js/smsb_common.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="../../../js/treatImage.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="../../../js/gmit_selectcontrol.js"></script>
</head>

<script language=JavaScript>
// 初始化页面信息
function doInit()
{
}

// 审核
function doShenHe(type)
{
	var msg;
	if(type == 1)
	{
		msg = "请确认是否审核同意！"
	}
	else if(type == 2)
	{
		msg = "请确认是否审核不同意！";
	}

	if(document.forms[0].badjbbh.value == null || document.forms[0].badjbbh.value == "")
	{
		if(confirm(msg)){
			document.forms[0].ztbz.value = type;
			document.forms[0].actionType.value = "Shenhe";
			document.forms[0].submit();
		}
	}
	else{
		alert("当前备案登记表已经审核通过，无需再次审核！");
	}
}

// 返回
function doReturn()
{
	// 清空相关信息
	document.forms[0].jsjdm.value = "";
	document.getElementsByName("kjywrxx.kjrmc_cn")[0].value = "";
	document.forms[0].actionType.value = "Init";
	document.forms[0].target = "_self";
	document.forms[0].submit();
}

// 打印
function doPrint()
{
	document.forms[0].actionType.value = "Print";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
}
</script>


<%@ include file="../../include/header.jsp"%>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="doInit()">
<html:form action="/webapp/smsb/qysds/kjqysds/badjbAction.do" method="post">
<html:hidden property="actionType" />
<html:hidden property="ztbz" />
<html:hidden property="badjbbh" />
<html:hidden property="badjxh" />
<html:hidden property="tbrq" />
<html:hidden property="jsjdm" />
<html:hidden property="kjywrxx.kjrmc_cn" />
<html:hidden property="kjywrxx.kjrmc_en" />
<html:hidden property="kjywrxx.kjrnssbh" />
<html:hidden property="kjywrxx.kjrdz_cn" />
<html:hidden property="kjywrxx.kjryzbm" />
<html:hidden property="kjywrxx.kjrcwfzr" />
<html:hidden property="kjywrxx.kjrlxr" />
<html:hidden property="kjywrxx.kjrlxdh" />
<html:hidden property="kjywrxx.kjrczhm" />
<html:hidden property="fjmqyxx.fjmmc_cn" />
<html:hidden property="fjmqyxx.fjmmc_en" />
<html:hidden property="fjmqyxx.fjmgb" />
<html:hidden property="fjmqyxx.fjmgjdq" />
<html:hidden property="fjmqyxx.fjmgjdqmc" />
<html:hidden property="fjmqyxx.fjmdz_cn" />
<html:hidden property="fjmqyxx.fjmdz_en" />
<html:hidden property="fjmqyxx.fjmcwfzr" />
<html:hidden property="fjmqyxx.fjmlxr" />
<html:hidden property="fjmqyxx.fjmlxdh" />
<html:hidden property="fjmqyxx.fjmczhm" />
<html:hidden property="htxx.htmc" />
<html:hidden property="htxx.htbh" />
<html:hidden property="htxx.qyrq" />
<html:hidden property="htxx.htyxq" />
<html:hidden property="htxx.htje" />
<html:hidden property="htxx.bz" />
<html:hidden property="htxx.zfxm" />
<html:hidden property="htxx.fkcs" />
<html:hidden property="htxx.qtzlmc" />
<html:hidden property="fjmqyxx.fjmgbmc"/>
<html:hidden property="htxx.bzmc"/>

<table width="900" align="center" cellspacing="0">
    <tr>
        <td class="1-td1">扣缴企业所得税备案登记表 </td>
    </tr>
    <tr>
        <td class="1-td2">&nbsp;&nbsp;编号：<bean:write name="badjbForm" property="badjbbh" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            填报日期:<bean:write name="badjbForm" property="tbrq" scope="request" filter="true" />&nbsp;&nbsp;
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
                                        &nbsp;<bean:write name="badjbForm" property="jsjdm" scope="request" filter="true" />
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
										&nbsp;<bean:write name="badjbForm" property="kjywrxx.kjrmc_en" scope="request" filter="true" />
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
                                    <td width="7%" class="2-td2-left">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="kjywrxx.kjrcwfzr" scope="request" filter="true" />
										</div>
									</td>
                                    <td width="7%" class="2-td2-left">联系人：</td>
                                    <td width="8%" class="2-td2-left">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="kjywrxx.kjrlxr" scope="request" filter="true" />
										</div>
									</td>
                                    <td class="2-td2-left">电话：</td>
                                    <td width="12%" class="2-td2-left">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="kjywrxx.kjrlxdh" scope="request" filter="true" />
										</div>
									</td>
                                    <td width="7%" class="2-td2-left">传真：</td>
                                    <td width="12%" class="2-td2-center">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="kjywrxx.kjrczhm" scope="request" filter="true" />
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td rowspan="6" class="2-td2-left">非居民企业</td>
                                    <td class="2-td2-left-qysds1">中文名称：</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="fjmqyxx.fjmmc_cn" scope="request" filter="true" />
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">英文名称：</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="fjmqyxx.fjmmc_en" scope="request" filter="true" />
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">国别：</td>
                                    <td colspan="2" class="2-td2-left">
                                        <div align="left">
                                            &nbsp;<bean:write name="badjbForm" property="fjmqyxx.fjmgbmc" scope="request" filter="true" />
                                        </div>
                                    </td>
                                    <td class="2-td2-left">国家或地区：</td>
                                    <td colspan="4" class="2-td2-center">
										<div align="left">
                                            &nbsp;<bean:write name="badjbForm" property="fjmqyxx.fjmgjdqmc" scope="request" filter="true" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">其居民国地址（中文）：</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="fjmqyxx.fjmdz_cn" scope="request" filter="true" />
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">其居民国地址（英文）：</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="fjmqyxx.fjmdz_en" scope="request" filter="true" />
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">财务负责人：</td>
                                    <td class="2-td2-left">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="fjmqyxx.fjmcwfzr" scope="request" filter="true" />
										</div>
									</td>
                                    <td class="2-td2-left">联系人：</td>
                                    <td class="2-td2-left">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="fjmqyxx.fjmlxr" scope="request" filter="true" />
										</div>
									</td>
                                    <td class="2-td2-left">电话：</td>
                                    <td class="2-td2-left">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="fjmqyxx.fjmlxdh" scope="request" filter="true" />
										</div>
									</td>
                                    <td width="5%" class="2-td2-left">传真：</td>
                                    <td class="2-td2-center">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="fjmqyxx.fjmczhm" scope="request" filter="true" />
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td rowspan="9" class="2-td2-left">合同信息</td>
                                    <td class="2-td2-left-qysds1">合同或协议名称： </td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="htxx.htmc" scope="request" filter="true" />
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">合同编号：</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="htxx.htbh" scope="request" filter="true" />
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">合同签约日期： </td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="htxx.qyrq" scope="request" filter="true" />
										</div>
									</td>
                                </tr>
                                <tr>
                                    <td class="2-td2-left-qysds1">合同有效期限：</td>
                                    <td colspan="7" class="2-td2-center">
										<div align="left">
											&nbsp;<bean:write name="badjbForm" property="htxx.htyxq" scope="request" filter="true" />
										</div>
									</td>
                                    </tr>
                                    <tr>
                                        <td class="2-td2-left-qysds1">合同金额：</td>
                                        <td colspan="7" class="2-td2-center">
											<div align="left">
											&nbsp;<bean:write name="badjbForm" property="htxx.htje" scope="request" filter="true" />
										</div>
									</td>
                                    </tr>
                                    <tr>
                                        <td class="2-td2-left-qysds1">币种：</td>
                                        <td colspan="7" class="2-td2-center">
											<div align="left">
												&nbsp;<bean:write name="badjbForm" property="htxx.bzmc" scope="request" filter="true" />
											</div>
										</td>
                                    </tr>
                                    <tr>
                                        <td class="2-td2-left-qysds1">支付项目：</td>
                                        <td colspan="7" class="2-td2-center">
											<div align="left">
											&nbsp;<bean:write name="badjbForm" property="htxx.zfxm" scope="request" filter="true" />
										</div>
									</td>
                                    </tr>
                                    <tr>
                                        <td class="2-td2-left-qysds1">付款次数：</td>
                                        <td colspan="7" class="2-td2-center">
											<div align="left">
											&nbsp;<bean:write name="badjbForm" property="htxx.fkcs" scope="request" filter="true" />
										</div>
									</td>
                                    </tr>
                                    <tr>
                                        <td class="2-td2-left-qysds1">其他资料名称：</td>
                                        <td colspan="7" class="2-td2-center">
											<div align="left">
												&nbsp;<bean:write name="badjbForm" property="htxx.qtzlmc" scope="request" filter="true" />
											</div>
										</td>
                                    </tr>
                                </table>
                            </div>
                        </TD>
                    </TR>
                    <TR class="black9">
                        <TD> <div align="center">
                            <input type="image" style="cursor:hand"
                                tabIndex="-1" onClick="doShenHe(1);return false;" accesskey="y"
                                value="审合同意" alt="审合同意"
                                onMouseOver="MM_swapImage('shty','','<%=static_contextpath%>images/q_shty2.jpg',1)"
                                onMouseOut="MM_swapImgRestore()"
                                src="<%=static_contextpath%>images/q_shty1.jpg"
                                id="shty" tabIndex="-1"/>
                            &nbsp;&nbsp;
                            <input type="image"
                                style="cursor:hand" tabIndex="-1" onClick="doShenHe(2);return false;"
                                accesskey="s" value="审合不同意" alt="审合不同意"
                                onMouseOver="MM_swapImage('shbty','','<%=static_contextpath%>images/q_shbty2.jpg',1)"
                                onMouseOut="MM_swapImgRestore()"
                                src="<%=static_contextpath%>images/q_shbty1.jpg"
                                id="shbty" tabIndex="-1"/>
                            &nbsp;&nbsp;
							<input type="image"
                                style="cursor:hand" tabIndex="-1" onClick="doPrint();return false;"
                                accesskey="s" value="打印" alt="打印备案登记表"
                                onMouseOver="MM_swapImage('dayin','','<%=static_contextpath%>images/dayin2.jpg',1)"
                                onMouseOut="MM_swapImgRestore()"
                                src="<%=static_contextpath%>images/dayin1.jpg"
                                id="dayin" tabIndex="-1"/>
                            &nbsp;&nbsp;
                            <input type="image" value="返回" alt="返回到主页面"
                                style="cursor:hand" tabIndex="-1" onClick="doReturn();return false;"
                                onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
                                onMouseOut="MM_swapImgRestore()"
                                src="<%=static_contextpath%>images/fanhui1.jpg"
                                id="fanhui" tabIndex="-1"/>
                            </div>
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
