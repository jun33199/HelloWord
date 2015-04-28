<%@page pageEncoding="GBK"%>
<%@ page contentType="text/html; charset=GBK" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/fangtu-lx.tld" prefix="fangtu" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ page import="com.ttsoft.bjtax.shenbao.fangtu.web.FangtuPrintForm" %>
<%@ page import="com.ttsoft.framework.util.JspUtil" %>
<%@ page import="com.ttsoft.common.model.UserData"%>
<%@ page import="com.ttsoft.common.util.SessionKey"%>
<%@ page import="com.ttsoft.common.util.ResourceLocator"%><%
	String static_contextpath =com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>打印房屋、土地情况登记表</title>
<STYLE type=text/css media="all">.print-left {
	BORDER-RIGHT: 0px solid; BORDER-TOP: 0px solid; FONT-SIZE: 9pt; BORDER-LEFT: #000000 1px solid; COLOR: #000000; LINE-HEIGHT: 20px; BORDER-BOTTOM: #000000 1px solid; BACKGROUND-COLOR: #ffffff; TEXT-ALIGN: center
}
.print-top-left {
	BORDER-RIGHT: 0px solid; BORDER-TOP: #000000 1px solid; FONT-SIZE: 9pt; BORDER-LEFT: #000000 1px solid; COLOR: #000000; LINE-HEIGHT: 20px; BORDER-BOTTOM: #000000 1px solid; BACKGROUND-COLOR: #ffffff; TEXT-ALIGN: center
}
.print-center {
	BORDER-RIGHT: #000000 1px solid; BORDER-TOP: 0px solid; FONT-SIZE: 9pt; BORDER-LEFT: #000000 1px solid; COLOR: #000000; LINE-HEIGHT: 20px; BORDER-BOTTOM: #000000 1px solid; BACKGROUND-COLOR: #ffffff; TEXT-ALIGN: center
}
.print-t-center {
	BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; FONT-SIZE: 9pt; BORDER-LEFT: #000000 1px solid; COLOR: #000000; LINE-HEIGHT: 20px; BORDER-BOTTOM: #000000 1px solid; BACKGROUND-COLOR: #ffffff; TEXT-ALIGN: center
}

@page { size: landscape; }

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	/*background-color: #CAD7F7;*/
}
td {
	font-size:12pt; 
	font-family:arial,宋体;
	border: 1pt solid #000000;
}

.table_1 {
	border: 1pt solid #000000;
	border-collapse: collapse;
	
}

.td_title {
	width: 1; text-align:center; vertical-align:middle
}

.td_1 {
	width: 150px;
}
.td_2 {
	width: 80px;
}
.td_3 {
	width: 80px;
}
.td_4 {
	width: 80px;
}
.td_5 {
	width: 80px;
}
.td_6 {
	width: 80px;
}
.td_7 {
	width: 80px;
}
.td_8 {
	width: 80px;
}

.td_9 {
	width: 80px;
}


</STYLE>
</STYLE>
</head>

<body>
<%
	FangtuPrintForm form = (FangtuPrintForm)request.getAttribute("fangtuPrintForm");
	List ziyongFWList = form.getZiyongFWList();
	List chengzuFWList = form.getChengzuFWList();
	List chuzuFWList = form.getChuzuFWList();
	List ziyongTDList = form.getZiyongTDList();
	List chengzuTDList = form.getChengzuTDList();
	List chuzuTDList = form.getChuzuTDList();

	int ziyongFWsize = ziyongFWList.size();
	int chengzuFWsize = chengzuFWList.size();
	int chuzuFWsize = chuzuFWList.size();
	int ziyongTDsize = ziyongTDList.size();
	int chengzuTDsize = chengzuTDList.size();
	int chuzuTDsize = chuzuTDList.size();
%>

<table class="table_1">
	<tr>
		<td colspan="6" height="80" align="center" style="font-weight:bold">
					
						房屋、土地情况登记表
				</td>
		<td colspan="4">填表日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</td>
	</tr>
	<tr>
		<td colspan="2">税务计算机代码：<bean:write name='fangtuPrintForm' property='jsjdm'/></td>
		<td colspan="2">纳税人名称：<bean:write name='fangtuPrintForm' property='taxpayerName'/></td>
		<td colspan="2">纳税人识别号：<bean:write name='fangtuPrintForm' property='taxpayerId'/></td>
		<td colspan="4">单位：元、平方米</td>
	</tr>
	<tr>
		<td width="10" class="td_title" rowspan="<% if(ziyongFWsize <= 5) { %>7<%}%><% else{ %><%=ziyongFWsize+2%><% }%>">自用房屋</td>
		<td class="td_1">房屋坐落</td>
		<td class="td_2">产权证书号</td>
		<td class="td_3">房产原值</td>
		<td class="td_4">税务机关估值</td>
		<td class="td_5">其中免税原值</td>
		<td class="td_6">其中应税原值</td>
		<td class="td_7">年应纳税额</td>
		<td class="td_8">是否代缴</td>
		<td class="td_9">备注</td>		
	</tr>
	<%
		int i = 0;
	%>
	<logic:iterate id="item" name="fangtuPrintForm" property="ziyongFWList">
	<tr>
		<td class="td_1"><bean:write name='item' property='fwzl'/>  </td>
		<td class="td_2"><bean:write name='item' property='cqzsh'/>　</td>
		<td class="td_3"><fmt:formatNumber value="${item.fcyz}" pattern="0.00"/>　</td>
		<td class="td_4"><fmt:formatNumber value="${item.swjggz}" pattern="0.00"/>　</td>
		<td class="td_5"><fmt:formatNumber value="${item.qzmsyz}" pattern="0.00"/>　</td>
		<td class="td_6"><fmt:formatNumber value="${item.qzysyz}" pattern="0.00"/>　</td>
		<td class="td_7"><fmt:formatNumber value="${item.nynse}" pattern="0.00"/>　</td>
		<td class="td_8">
		<logic:equal name="item" property="sfdj" value="0">
		是
		</logic:equal>
		<logic:equal name="item" property="sfdj" value="1">
		否
		</logic:equal>　</td>
		<td class="td_9"><bean:write name='item' property='bz'/>　</td>
	</tr>
	<%
		i++;
	%>
	</logic:iterate>
	<%
		if(i < 5)
		{
			for(; i<5; i++)
			{
	%>
		<tr>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td>　</td>
				<td>　</td>
			</tr>
	<%
			}
		}
	%>
	<tr>
		<td colspan="2">小计</td>
		<td><fmt:formatNumber value="${fangtuPrintForm.fwZiyongTotal.fcyz}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${fangtuPrintForm.fwZiyongTotal.swjggz}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${fangtuPrintForm.fwZiyongTotal.qzmsyz}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${fangtuPrintForm.fwZiyongTotal.qzysyz}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${fangtuPrintForm.fwZiyongTotal.nynse}" pattern="0.00"/>　</td>
		<td>　</td>
				<td>　</td>
			</tr>
	<tr>
		<td rowspan="<% if(chuzuFWsize <= 5) { %>7<%}%><% else{ %><%=chuzuFWsize+2%><% }%>">出租房屋</td>
		<td>房屋坐落</td>
		<td>产权证书号</td>
		<td colspan="2">年租金收入</td>
		<td colspan="4">年应纳税额</td>
		<td>备注</td>
	</tr>
	<%
		i = 0;
	%>
	<logic:iterate id="item" name="fangtuPrintForm" property="chuzuFWList">
	<tr>
		<td><bean:write name='item' property='fwzl'/>  </td>
		<td><bean:write name='item' property='cqzsh'/>　</td>
		<td colspan="2"><fmt:formatNumber value="${item.nzjsr}" pattern="0.00"/>　</td>
		<td colspan="4"><fmt:formatNumber value="${item.nynse}" pattern="0.00"/>　</td>
					<td><bean:write name='item' property='bz'/>　</td>
				</tr>
	<%
		i++;
	%>
	</logic:iterate>
	<%
		if(i < 5)
		{
			for(; i<5; i++)
			{
	%>
		<tr>
			<td>　</td>
			<td>　</td>
			<td colspan="2">　</td>
			<td colspan="4">　</td>
				<td>　</td>
			</tr>
	<%
			}
		}
	%>
	<tr>
		<td colspan="2">小计</td>
		<td colspan="2"><fmt:formatNumber value="${fangtuPrintForm.fwChuzuTotal.nzjsr}" pattern="0.00"/>　</td>
		<td colspan="4"><fmt:formatNumber value="${fangtuPrintForm.fwChuzuTotal.nynse}" pattern="0.00"/>　</td>
				<td>　</td>
			</tr>
	<tr>
		<td rowspan="<% if(chengzuFWsize <= 5) { %>6<%}%><% else{ %><%=chengzuFWsize+2%><% }%>">承租房屋</td>
		<td>出租人名称</td>
		<td>证件类型</td>
		<td>出租人证件号码</td>
		<td colspan="3">承租房屋坐落</td>
		<td colspan="2">年租金</td>
		<td>备注</td>
	</tr>
	<%
		i = 0;
	%>
	<logic:iterate id="item" name="fangtuPrintForm" property="chengzuFWList">
	<tr>
		<td><bean:write name='item' property='czrmc'/>  </td>
		<td>
		<fangtu:lx name="item" property='zjlxdm' codeList="ZJLX_LIST"/>　</td>
		<td><bean:write name='item' property='czrzjhm'/>　</td>
		<td colspan="3"><bean:write name='item' property='czfwzl'/>　</td>
		<td colspan="2"><fmt:formatNumber value="${item.nzj}" pattern="0.00"/>　</td>
					<td><bean:write name='item' property='bz'/>　</td>
				</tr>
	<%
		i++;
	%>
	</logic:iterate>
	<%
		if(i < 5)
		{
			for(; i<5; i++)
			{
	%>
		<tr>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td colspan="3">　</td>
			<td colspan="2">　</td>
				<td>　</td>
			</tr>
	<%
			}
		}
	%>
	<tr>
		<td rowspan="<% if(ziyongTDsize <= 5) { %>7<%}%><% else{ %><%=ziyongTDsize+2%><% }%>">自用土地</td>
		<td>土地坐落</td>
		<td>土地证书号</td>
		<td>土地面积</td>
		<td>其中免税面积</td>
		<td>其中应税面积</td>
		<td>每平方米税额</td>
		<td>年应纳税额</td>
		<td>是否代缴</td>
		<td>备注</td>
	</tr>
	<%
		i = 0;
	%>
	<logic:iterate id="item" name="fangtuPrintForm" property="ziyongTDList">
	<tr>
		<td><bean:write name='item' property='tdzl'/>  </td>
		<td><bean:write name='item' property='tdzsh'/>　</td>
		<td><fmt:formatNumber value="${item.tdmj}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${item.qzmsmj}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${item.qzysmj}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${item.mpfmse}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${item.nynse}" pattern="0.00"/>　</td>
		<td>
		<logic:equal name="item" property="sfdj" value="0">
		是
		</logic:equal>
		<logic:equal name="item" property="sfdj" value="1">
		否
		</logic:equal>　</td>
					<td><bean:write name='item' property='bz'/>　</td>
				</tr>
	<%
		i++;
	%>
	</logic:iterate>
	<%
		if(i < 5)
		{
			for(; i<5; i++)
			{
	%>
		<tr>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td>　</td>
				<td>　</td>
			</tr>
	<%
			}
		}
	%>
	<tr>
		<td colspan="2">小计</td>
		<td><fmt:formatNumber value="${fangtuPrintForm.tdZiyongTotal.tdmj}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${fangtuPrintForm.tdZiyongTotal.qzmsmj}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${fangtuPrintForm.tdZiyongTotal.qzysmj}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${fangtuPrintForm.tdZiyongTotal.mpfmse}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${fangtuPrintForm.tdZiyongTotal.nynse}" pattern="0.00"/>　</td>
		<td>　</td>
				<td>　</td>
			</tr>
	<tr>
		<td rowspan="<% if(chuzuTDsize <= 5) { %>7<%}%><% else{ %><%=chuzuTDsize+2%><% }%>">出租土地<p>　</td>
		<td>土地坐落</td>
		<td>土地证书号</td>
		<td>土地面积</td>
		<td>其中免税面积</td>
		<td>其中应税面积</td>
		<td>每平方米税额</td>
		<td colspan="2">年应纳税额</td>
		<td>备注</td>
	</tr>
	<%
		i = 0;
	%>
	<logic:iterate id="item" name="fangtuPrintForm" property="chuzuTDList">
	<tr>
		<td><bean:write name='item' property='tdzl'/>  </td>
		<td><bean:write name='item' property='tdzsh'/>　</td>
		<td><fmt:formatNumber value="${item.tdmj}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${item.qzmsmj}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${item.qzysmj}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${item.mpfmse}" pattern="0.00"/>　</td>
		<td colspan="2"><fmt:formatNumber value="${item.nynse}" pattern="0.00"/>　</td>
					<td><bean:write name='item' property='bz'/>　</td>
				</tr>
	<%
		i++;
	%>
	</logic:iterate>
	<%
		if(i < 5)
		{
			for(; i<5; i++)
			{
	%>
		<tr>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td colspan="2">　</td>
				<td>　</td>
			</tr>
	<%
			}
		}
	%>
	<tr>
		<td colspan="2">小计</td>
		<td><fmt:formatNumber value="${fangtuPrintForm.tdChuzuTotal.tdmj}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${fangtuPrintForm.tdChuzuTotal.qzmsmj}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${fangtuPrintForm.tdChuzuTotal.qzysmj}" pattern="0.00"/>　</td>
		<td><fmt:formatNumber value="${fangtuPrintForm.tdChuzuTotal.mpfmse}" pattern="0.00"/>　</td>
		<td colspan="2"><fmt:formatNumber value="${fangtuPrintForm.tdChuzuTotal.nynse}" pattern="0.00"/>　</td>
				<td>　</td>
			</tr>
	<tr>
		<td rowspan="<% if(chengzuTDsize <= 5) { %>6<%}%><% else{ %><%=chengzuTDsize+2%><% }%>">承租土地</td>
		<td>出租人名称</td>
		<td>证件类型</td>
		<td>出租人证件号码</td>
		<td colspan="3">承租土地坐落</td>
		<td colspan="2">土地面积</td>
		<td>备注</td>
	</tr>
	<%
		i = 0;
	%>
	<logic:iterate id="item" name="fangtuPrintForm" property="chengzuTDList">
	<tr>
		<td><bean:write name='item' property='czrmc'/>  </td>
		<td>
		<fangtu:lx name="item" property='zjlxdm' codeList="ZJLX_LIST"/>  
		</td>
		<td><bean:write name='item' property='czrzjhm'/>　</td>
		<td colspan="3"><bean:write name='item' property='cztdzl'/>　</td>
		<td colspan="2"><fmt:formatNumber value="${item.tdmj}" pattern="0.00"/>　</td>
					<td><bean:write name='item' property='bz'/>　</td>
				</tr>
	<%
		i++;
	%>
	</logic:iterate>
	<%
		if(i < 5)
		{
			for(; i<5; i++)
			{
	%>
		<tr>
			<td>　</td>
			<td>　</td>
			<td>　</td>
			<td colspan="3">　</td>
			<td colspan="2">　</td>
				<td>　</td>
			</tr>
	<%
			}
		}
	%>
</table>

<p>填表人：盖章（签字）</p>

<br><br><br><br>
<div id="dayin" align="center">
<img style="cursor:hand" onclick="printTab()" src="<%=static_contextpath%>/images/dayin1.jpg" width="79" height="22" id="dayin1"/>&nbsp;&nbsp;<img style="cursor:hand" onclick="window.close()" src="<%=static_contextpath%>/images/guanbi1.jpg" width="79" height="22" id="guanbi"/>
</div>
<!-- <OBJECT id=WebBrowser classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0>
  </OBJECT>
 -->
<SCRIPT LANGUAGE="JavaScript">
<!--
	function printTab(){
			 document.all.dayin.style.display = "none";
			 window.print();
			 //document.all.WebBrowser.ExecWB (6,1);
			 document.all.dayin.style.display = "";
	}
//-->
</SCRIPT>

</body>

</html>