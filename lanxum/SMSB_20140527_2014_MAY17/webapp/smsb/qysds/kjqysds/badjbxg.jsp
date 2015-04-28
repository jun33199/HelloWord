<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import ="java.util.HashMap"%>
<%@ page import ="java.util.List"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.web.BadjbForm"%>

<%
	System.out.println("1111111");
	// 获取BadjbForm
	BadjbForm form = (BadjbForm) request.getAttribute("badjbForm");


	// 记录数
	int totalCount = form.getTotalCount();
	// 总页数
	int totalPage = form.getTotalPage();
	// 当前页
	int currentPage = form.getCurrentPage();
	// 记录集合
	List recordList = form.getRecordList();
%>
<html>
<head>
    <title>修改扣缴企业所得税合同备案登记表</title>
    <link href="../../../css/text.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" type="text/JavaScript" src="../../../js/smsb_common.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="../../../js/treatImage.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="../../../js/gmit_selectcontrol.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="../../../js/page.js"></script>
</head>

<script language="JavaScript">
// 初始化页面信息
function doInit()
{

}

// 查询扣缴人信息
function doQuery()
{
	if(checkValidity()){
		document.forms[0].actionType.value = "QueryKjrRecords";
		document.forms[0].submit();
	}
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
	// 扣缴义务人计算机代码
	if(document.forms[0].jsjdm.value == null || document.forms[0].jsjdm.value == ""){
		alert("请输入扣缴义务人计算机代码！");
		document.forms[0].jsjdm.focus();
		return false;
	}
    return true;
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

	document.forms[0].actionType.value = "QueryKjrRecords";
	//当前页
	document.forms[0].currentPage.value = pageNo;
	//将页面指向当前页面
	document.forms[0].target = "_self";
	document.forms[0].submit();
}

// 下一步
function doNext()
{
	
	if(isSelected(document.forms[0].dshRadio))
	{
		document.forms[0].badjxh.value = getSelectedValue(document.forms[0].dshRadio);
		document.forms[0].actionType.value = "Show";
		document.forms[0].submit();
	}
	else{
		alert("请选择一条记录后再进行此操作！");
	}
}
//删除
function doDelete(){
	if(isSelected(document.forms[0].dshRadio))
	{
		document.forms[0].badjxh.value = getSelectedValue(document.forms[0].dshRadio);
		document.forms[0].actionType.value = "Delete";
		doSubmitForm("Delete");
	}
	else{
		alert("请选择一条记录后再进行此操作！");
	}
}

</script>

<%@ include file="../../include/header.jsp"%>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="doInit()">
<html:form action="/webapp/smsb/qysds/kjqysds/badjbAction.do" method="post">
<html:hidden property="actionType" />
<html:hidden property="badjxh" value=""/>
<html:hidden property="currentPage"/>
<html:hidden property="modifyFlag" value="true"/>


<table width="1000" align="center" cellspacing="0">
    <tr>
        <td class="1-td1"  colspan="9">修改扣缴企业所得税合同备案登记表</td>
    </tr>
    <tr>
        <td class="1-td2">
			<table class="table-99" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<table width="99%" border="0" align="center" cellpadding="1" cellspacing="0">
							<tr> 
								<td>
									<table class="table-100" border="1" cellspacing="0" cellpadding="0">
										<tr> 
											<td width="20%" class="2-td2-t-left">扣缴义务人计算机代码：</td>
											<td width="30%" class="2-td2-t-left"> <div align="left">&nbsp;<html:text property="jsjdm" size="20" maxlength="8" onfocus="this.select()" /></div></td>
											<td width="20%" class="2-td2-t-left">扣缴义务人名称：</td>
											<td width="30%" class="2-td2-t-center">&nbsp;<bean:write name="badjbForm" property="kjywrxx.kjrmc_cn" scope="request" filter="true" /></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td align="center"> 
									<input name="query" type="image" id="chaxun" style="cursor:hand" accesskey="q" tabIndex="-1" onClick="doQuery();return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="查询" src="<%=static_contextpath%>images/chaxun1.jpg" alt="查询待处理备案登记表信息" /> 
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table border="1" cellspacing="0" class="table-99" align="center" >
							<tr> 
								<td width="6%" align="center" class="2-td2-t-left">&nbsp;</td>
								<td width="22%" align="center" class="2-td2-t-left"><strong>合同名称</strong></td>
								<td width="22%" align="center" class="2-td2-t-left"><strong>合同编号</strong></td>
								<td width="10%" align="center" class="2-td2-t-left"><strong>合同签约日期</strong></td>
								<td width="10%" align="center" class="2-td2-t-left"><strong>合同有效期</strong></td>
								<td width="10%" align="center" class="2-td2-t-left"><strong>合同金额</strong></td>
								<td width="10%" align="center" class="2-td2-t-left"><strong>填报日期</strong></td>
								<td width="10%" align="center" class="2-td2-t-center"><strong>审核状态</strong></td>
							</tr>
              <%
						// 显示查询结果
						if(recordList != null && recordList.size() > 0)
						{
							for(int i = 0; i < recordList.size(); i++)
							{
								// 获取List封装对象
								HashMap map = (HashMap) recordList.get(i);
					%>
							<tr> 
								<td class="2-td2-left">&nbsp; <input type="radio" name="dshRadio" value="<%=(String) map.get("badjxh")%>"/></td>
								<td class="2-td2-left">&nbsp;<%=(String) map.get("htmc")%></td>
								<td class="2-td2-left">&nbsp;<%=(String) map.get("htbh")%></td>
								<td class="2-td2-left">&nbsp;<%=(String) map.get("htqyrq")%></td>
								<td class="2-td2-left">&nbsp;<%=(String) map.get("htyxq")%></td>
								<td class="2-td2-left">&nbsp;<%=(String) map.get("htje")%></td>
								<td class="2-td2-left">&nbsp;<%=(String) map.get("tbrq")%></td>
								<td class="2-td2-center">&nbsp;<%=(String) map.get("shzt")%></td>
							</tr>
              <%
							}
						}
					%>
            </table>
						<table class="table-100" border="0">
							<tr class="2-td2-t-left">
								<td align="left">&nbsp;&nbsp;记录数：<%=totalCount%>&nbsp;&nbsp;&nbsp; 当前页/总页数：<%=currentPage%>/<%=totalPage%> &nbsp; </td>
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
		</TD>
    </TR>
    <TR class="black9">
        <TD>
            <div align="center">
                <img style="cursor:hand"
                    tabIndex="-1" onClick="doNext();return false;" accesskey="n"
                    value="下一步" alt="下一步操作"
                    onMouseOver="MM_swapImage('xiayibu','','<%=static_contextpath%>images/xiayibu2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()"
                    src="<%=static_contextpath%>images/xiayibu1.jpg"
                    id="xiayibu" tabIndex="-1"/>
                    &nbsp;&nbsp;
                 <img style="cursor:hand"
                    tabIndex="-1" onClick="doDelete();return false;" accesskey="s"
                    value="删除" alt="删除"
                    onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/shanchu2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()"
                    src="<%=static_contextpath%>images/shanchu1.jpg"
                    id="shanchu" tabIndex="-1"/>
                &nbsp;&nbsp;
                <img value="返回" alt="返回到主页面"
                    style="cursor:hand" tabIndex="-1" onClick="tuichu();return false;"
                    onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()"
                    src="<%=static_contextpath%>images/fanhui1.jpg"
                    id="fanhui" tabIndex="-1"/>
            </div>
        </TD>
    </TR>
</TABLE>
<br/>
<br/>
<br/>
<%@ include file="../../include/footernew.jsp"%>
</html:form>

</body>
</html>
