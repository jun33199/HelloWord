<%@page pageEncoding="GBK"%>
<%@ page contentType="text/html; charset=GBK" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/fangtu-lx.tld" prefix="fangtu" %>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%@ page import="com.ttsoft.bjtax.shenbao.fangtu.web.FangtuPrintForm" %>
<%@ page import="com.ttsoft.bjtax.shenbao.model.domain.CZFWBGXX" %>
<%@ page import="com.ttsoft.bjtax.shenbao.model.domain.CZTDBGXX" %>
<%@ page import="com.ttsoft.bjtax.shenbao.model.domain.CZUFWBGXX" %>
<%@ page import="com.ttsoft.bjtax.shenbao.model.domain.CZUTDBGXX" %>
<%@ page import="com.ttsoft.bjtax.shenbao.model.domain.ZYFWBGXX" %>
<%@ page import="com.ttsoft.bjtax.shenbao.model.domain.ZYTDBGXX" %>
<%@ page import="com.ttsoft.framework.util.JspUtil" %>
<%@ page import="com.ttsoft.common.model.UserData"%>
<%@ page import="com.ttsoft.common.util.SessionKey"%>
<%@ page import="com.ttsoft.common.util.ResourceLocator"%><%
	String static_contextpath =com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<title>打印房屋、土地变更情况登记表</title>
<style>
<!--
* {
	
font-family: 宋体; font-size: 12pt;

}

#table_2 {
	border: 1px solid #000;
}
#table_2 td {
	border: 1px solid #000;
}


-->
</style>
</head>

<body>

<%
	FangtuPrintForm form = (FangtuPrintForm)request.getAttribute("fangtuPrintForm");
	List bgziyongFWList = form.getBgziyongFWList();
	List bgchengzuFWList = form.getBgchengzuFWList();
	List bgchuzuFWList = form.getBgchuzuFWList();
	List bgziyongTDList = form.getBgziyongTDList();
	List bgchengzuTDList = form.getBgchengzuTDList();
	List bgchuzuTDList = form.getBgchuzuTDList();
	int size = bgziyongFWList.size();
	if(bgchengzuFWList.size() > size)
	{
		size = bgchengzuFWList.size();
	}
	if(bgchuzuFWList.size() > size)
	{
		size = bgchuzuFWList.size();
	}
	if(bgziyongTDList.size() > size)
	{
		size = bgziyongTDList.size();
	}
	if(bgchengzuTDList.size() > size)
	{
		size = bgchengzuTDList.size();
	}
	if(bgchuzuTDList.size() > size)
	{
		size = bgchuzuTDList.size();
	}
	int pages;
	if(size % 2 != 0)
	{
		pages = (int)((size-1)/2) + 1;
	}
	else
	{
		pages = (int)(size/2);
	}
	for(int i=0; i<pages; i++)
	{
		CZFWBGXX czfwbg1 = null;
		CZTDBGXX cztdbg1 = null;
		CZUFWBGXX czufwbg1 = null;
		CZUTDBGXX czutdbg1 = null;
		ZYFWBGXX zyfwbg1 = null;
		ZYTDBGXX zytdbg1 = null;

		CZFWBGXX czfwbg2 = null;
		CZTDBGXX cztdbg2 = null;
		CZUFWBGXX czufwbg2 = null;
		CZUTDBGXX czutdbg2 = null;
		ZYFWBGXX zyfwbg2 = null;
		ZYTDBGXX zytdbg2 = null;
		/*
		CZFWBGXX czfwbg1 = new CZFWBGXX();
		CZTDBGXX cztdbg1 = new CZTDBGXX();
		CZUFWBGXX czufwbg1 = new CZUFWBGXX();
		CZUTDBGXX czutdbg1 = new CZUTDBGXX();
		ZYFWBGXX zyfwbg1 = new ZYFWBGXX();
		ZYTDBGXX zytdbg1 = new ZYTDBGXX();

		CZFWBGXX czfwbg2 = new CZFWBGXX();
		CZTDBGXX cztdbg2 = new CZTDBGXX();
		CZUFWBGXX czufwbg2 = new CZUFWBGXX();
		CZUTDBGXX czutdbg2 = new CZUTDBGXX();
		ZYFWBGXX zyfwbg2 = new ZYFWBGXX();
		ZYTDBGXX zytdbg2 = new ZYTDBGXX();
		*/

		if(i*2 <= bgchuzuFWList.size()-1)
		{
			czfwbg1 = (CZFWBGXX)bgchuzuFWList.get(i*2);
		}
		if(i*2+1 <= bgchuzuFWList.size()-1)
		{
			czfwbg2 = (CZFWBGXX)bgchuzuFWList.get(i*2 + 1);
		}
		if(i*2 <= bgchuzuTDList.size()-1)
		{
			cztdbg1 = (CZTDBGXX)bgchuzuTDList.get(i*2);
		}
		if(i*2+1 <= bgchuzuTDList.size()-1)
		{
			cztdbg2 = (CZTDBGXX)bgchuzuTDList.get(i*2 + 1);
		}
		if(i*2 <= bgchengzuFWList.size()-1)
		{
			czufwbg1 = (CZUFWBGXX)bgchengzuFWList.get(i*2);
		}
		if(i*2+1 <= bgchengzuFWList.size()-1)
		{
			czufwbg2 = (CZUFWBGXX)bgchengzuFWList.get(i*2 + 1);
		}
		if(i*2 <= bgchengzuTDList.size()-1)
		{
			czutdbg1 = (CZUTDBGXX)bgchengzuTDList.get(i*2);
		}
		if(i*2+1 <= bgchengzuTDList.size()-1)
		{
			czutdbg2 = (CZUTDBGXX)bgchengzuTDList.get(i*2 + 1);
		}
		if(i*2 <= bgziyongFWList.size()-1)
		{
			zyfwbg1 = (ZYFWBGXX)bgziyongFWList.get(i*2);
		}
		if(i*2+1 <= bgziyongFWList.size()-1)
		{
			zyfwbg2 = (ZYFWBGXX)bgziyongFWList.get(i*2 + 1);
		}
		if(i*2 <= bgziyongTDList.size()-1)
		{
			zytdbg1 = (ZYTDBGXX)bgziyongTDList.get(i*2);
		}
		if(i*2+1 <= bgziyongTDList.size()-1)
		{
			zytdbg2 = (ZYTDBGXX)bgziyongTDList.get(i*2 + 1);
		}

		pageContext.removeAttribute("zyfwbg1");
		pageContext.removeAttribute("zyfwbg2");
		pageContext.removeAttribute("zytdbg1");
		pageContext.removeAttribute("zytdbg2");
		pageContext.removeAttribute("czfwbg1");
		pageContext.removeAttribute("czfwbg2");
		pageContext.removeAttribute("cztdbg1");
		pageContext.removeAttribute("cztdbg2");
		pageContext.removeAttribute("czufwbg1");
		pageContext.removeAttribute("czufwbg2");
		pageContext.removeAttribute("czutdbg1");
		pageContext.removeAttribute("czutdbg2");

		if(zyfwbg1 != null)
			pageContext.setAttribute("zyfwbg1",zyfwbg1);
		if(zyfwbg2 != null)
			pageContext.setAttribute("zyfwbg2",zyfwbg2);
		if(zytdbg1 != null)
			pageContext.setAttribute("zytdbg1",zytdbg1);
		if(zytdbg2 != null)
			pageContext.setAttribute("zytdbg2",zytdbg2);
		if(czfwbg1 != null)
			pageContext.setAttribute("czfwbg1",czfwbg1);
		if(czfwbg2 != null)
			pageContext.setAttribute("czfwbg2",czfwbg2);
		if(cztdbg1 != null)
			pageContext.setAttribute("cztdbg1",cztdbg1);
		if(cztdbg2 != null)
			pageContext.setAttribute("cztdbg2",cztdbg2);
		if(czufwbg1 != null)
			pageContext.setAttribute("czufwbg1",czufwbg1);
		if(czufwbg2 != null)
			pageContext.setAttribute("czufwbg2",czufwbg2);
		if(czutdbg1 != null)
			pageContext.setAttribute("czutdbg1",czutdbg1);
		if(czutdbg2 != null)
			pageContext.setAttribute("czutdbg2",czutdbg2);
%>

<table width="100%" border="0">
	<tr>
		<td colspan="3" align="center" height="50">
		<div style=" font-weight:bold; font-size:14pt">房屋、土地变更情况登记表</div></td>
	</tr>
	<tr height="30">
		<td>税务计算机代码：<bean:write name='fangtuPrintForm' property='jsjdm'/></td>
		<td>纳税人名称：<bean:write name='fangtuPrintForm' property='taxpayerName'/></td>
		<td>　</td>
	</tr>
	<tr height="30">
		<td>纳税人识别号：<bean:write name='fangtuPrintForm' property='taxpayerId'/></td>
		<td>填表日期：年月日</td>
		<td>单位：元、平方米</td>
	</tr>
</table>
<table border="1" width="100%" id="table_2"  cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2" rowspan="2" align="center">项目</td>
		<td colspan="2" align="center">变更1</td>
		<td colspan="2" align="center">变更2</td>
	</tr>
	<tr>
		<td align="center" width="21%">变更前</td>
		<td align="center" width="21%">变更后</td>
		<td align="center" width="21%">变更前</td>
		<td align="center" width="21%">变更后</td>
	</tr>
	<tr>
		<td rowspan="12" width="23">自用房屋</td>
		<td width="14%">产权证书号</td>
		<td width="21%"><logic:present name="zyfwbg1"><bean:write name='zyfwbg1' property='bgqcqzsh'/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg1"><bean:write name='zyfwbg1' property='bghcqzsh'/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><bean:write name='zyfwbg2' property='bgqcqzsh'/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><bean:write name='zyfwbg2' property='bghcqzsh'/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">房屋坐落</td>
		<td width="21%"><logic:present name="zyfwbg1"><bean:write name='zyfwbg1' property='bgqfwzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg1"><bean:write name='zyfwbg1' property='bghfwzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><bean:write name='zyfwbg2' property='bgqfwzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><bean:write name='zyfwbg2' property='bghfwzl'/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">房产原值</td>
		<td width="21%"><logic:present name="zyfwbg1"><fmt:formatNumber value="${zyfwbg1.bgqfcyz}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg1"><fmt:formatNumber value="${zyfwbg1.bghfcyz}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><fmt:formatNumber value="${zyfwbg2.bgqfcyz}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><fmt:formatNumber value="${zyfwbg2.bghfcyz}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">税务机关估值</td>
		<td width="21%"><logic:present name="zyfwbg1"><fmt:formatNumber value="${zyfwbg1.bgqswjggz}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg1"><fmt:formatNumber value="${zyfwbg1.bghswjggz}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><fmt:formatNumber value="${zyfwbg2.bgqswjggz}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><fmt:formatNumber value="${zyfwbg2.bghswjggz}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">其中：应税原值</td>
		<td width="21%"><logic:present name="zyfwbg1"><fmt:formatNumber value="${zyfwbg1.bgqqzysyz}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg1"><fmt:formatNumber value="${zyfwbg1.bghqzysyz}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><fmt:formatNumber value="${zyfwbg2.bgqqzysyz}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><fmt:formatNumber value="${zyfwbg2.bghqzysyz}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">应税房产原值变更原因</td>
		<td colspan="2">
			<logic:present name="zyfwbg1" property="ysfcyzbgyy">
			<logic:equal name="zyfwbg1" property="ysfcyzbgyy" value="0">
			新增<input type=checkbox checked>减少<input type=checkbox >原值变更<input type=checkbox >
			</logic:equal>
			<logic:equal name="zyfwbg1" property="ysfcyzbgyy" value="1">
			新增<input type=checkbox >减少<input type=checkbox checked>原值变更<input type=checkbox >
			</logic:equal>
			<logic:equal name="zyfwbg1" property="ysfcyzbgyy" value="2">
			新增<input type=checkbox >减少<input type=checkbox >原值变更<input type=checkbox checked>
			</logic:equal>
			</logic:present>  </td>
		<td colspan="2">
			<logic:present name="zyfwbg2" property="ysfcyzbgyy">
			<logic:equal name="zyfwbg2" property="ysfcyzbgyy" value="0">
			新增<input type=checkbox checked>减少<input type=checkbox >原值变更<input type=checkbox >
			</logic:equal>
			<logic:equal name="zyfwbg2" property="ysfcyzbgyy" value="1">
			新增<input type=checkbox >减少<input type=checkbox checked>原值变更<input type=checkbox >
			</logic:equal>
			<logic:equal name="zyfwbg2" property="ysfcyzbgyy" value="2">
			新增<input type=checkbox >减少<input type=checkbox >原值变更<input type=checkbox checked>
			</logic:equal>
			</logic:present>  </td>
	</tr>
	<tr>
		<td width="14%">其中：减免税原值</td>
		<td width="21%"><logic:present name="zyfwbg1"><fmt:formatNumber value="${zyfwbg1.bgqqzmsyz}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg1"><fmt:formatNumber value="${zyfwbg1.bghqzmsyz}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><fmt:formatNumber value="${zyfwbg2.bgqqzmsyz}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><fmt:formatNumber value="${zyfwbg2.bghqzmsyz}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">减免税原值变更原因</td>
		<td colspan="2">
			<logic:present name="zyfwbg1" property="jmsyzbgyy">
			<logic:equal name="zyfwbg1" property="jmsyzbgyy" value="0">
			法定<input type=checkbox checked>大修<input type=checkbox >其他<input type=checkbox >
			</logic:equal>
			<logic:equal name="zyfwbg1" property="jmsyzbgyy" value="1">
			法定<input type=checkbox >大修<input type=checkbox checked>其他<input type=checkbox >
			</logic:equal>
			<logic:equal name="zyfwbg1" property="jmsyzbgyy" value="2">
			法定<input type=checkbox >大修<input type=checkbox ><br>其他<input type=checkbox checked>文件号
			<fangtu:formoptions collectionName="fangtuPrintForm" collectionProperty="zhengceList" name="zyfwbg1" property="jmzcdm"/>
			</logic:equal>
			</logic:present>  </td>
		<td colspan="2">
			<logic:present name="zyfwbg2" property="jmsyzbgyy">
			<logic:equal name="zyfwbg2" property="jmsyzbgyy" value="0">
			法定<input type=checkbox checked>大修<input type=checkbox >其他<input type=checkbox >
			</logic:equal>
			<logic:equal name="zyfwbg2" property="jmsyzbgyy" value="1">
			法定<input type=checkbox >大修<input type=checkbox checked>其他<input type=checkbox >
			</logic:equal>
			<logic:equal name="zyfwbg2" property="jmsyzbgyy" value="2">
			法定<input type=checkbox >大修<input type=checkbox ><br>其他<input type=checkbox checked>文件号
			<fangtu:formoptions collectionName="fangtuPrintForm" collectionProperty="zhengceList" name="zyfwbg2" property="jmzcdm"/>
			</logic:equal>
			</logic:present>  
		</td>
	</tr>
	<tr>
		<td width="14%">减免税期限</td>
		<td width="21%"><logic:present name="zyfwbg1"><fmt:formatDate value="${zyfwbg1.jmsqxq}" pattern="yyyyMMdd"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg1"><fmt:formatDate value="${zyfwbg1.jmsqxz}" pattern="yyyyMMdd"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><fmt:formatDate value="${zyfwbg2.jmsqxq}" pattern="yyyyMMdd"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><fmt:formatDate value="${zyfwbg2.jmsqxz}" pattern="yyyyMMdd"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">年应纳税额</td>
		<td width="21%"><logic:present name="zyfwbg1"><fmt:formatNumber value="${zyfwbg1.bgqnynse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg1"><fmt:formatNumber value="${zyfwbg1.bghnynse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><fmt:formatNumber value="${zyfwbg2.bgqnynse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><fmt:formatNumber value="${zyfwbg2.bghnynse}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">代缴</td>
		<td width="21%"><logic:present name="zyfwbg1" property="bgqsfdj">
			<logic:equal name="zyfwbg1" property="bgqsfdj" value="0">是</logic:equal>
			<logic:equal name="zyfwbg1" property="bgqsfdj" value="1">否</logic:equal>
			</logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg1" property="bghsfdj">
			<logic:equal name="zyfwbg1" property="bghsfdj" value="0">是</logic:equal>
			<logic:equal name="zyfwbg1" property="bghsfdj" value="1">否</logic:equal>
			</logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2" property="bgqsfdj">
			<logic:equal name="zyfwbg2" property="bgqsfdj" value="0">是</logic:equal>
			<logic:equal name="zyfwbg2" property="bgqsfdj" value="1">否</logic:equal>
			</logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2" property="bghsfdj">
			<logic:equal name="zyfwbg2" property="bghsfdj" value="0">是</logic:equal>
			<logic:equal name="zyfwbg2" property="bghsfdj" value="1">否</logic:equal>
			</logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">其他</td>
		<td width="21%"><logic:present name="zyfwbg1"><bean:write name='zyfwbg1' property='bgqbz'/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg1"><bean:write name='zyfwbg1' property='bghbz'/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><bean:write name='zyfwbg2' property='bgqbz'/></logic:present>　</td>
		<td width="21%"><logic:present name="zyfwbg2"><bean:write name='zyfwbg2' property='bghbz'/></logic:present>　</td>
	</tr>
	<tr>
		<td rowspan="5" width="23">出租房屋</td>
		<td width="14%">产权证书号</td>
		<td width="21%"><logic:present name="czfwbg1"><bean:write name='czfwbg1' property='bgqcqzsh'/></logic:present>　</td>
		<td width="21%"><logic:present name="czfwbg1"><bean:write name='czfwbg1' property='bghcqzsh'/></logic:present>　</td>
		<td width="21%"><logic:present name="czfwbg2"><bean:write name='czfwbg2' property='bgqcqzsh'/></logic:present>　</td>
		<td width="21%"><logic:present name="czfwbg2"><bean:write name='czfwbg2' property='bghcqzsh'/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">房屋坐落</td>
		<td width="21%"><logic:present name="czfwbg1"><bean:write name='czfwbg1' property='bgqfwzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="czfwbg1"><bean:write name='czfwbg1' property='bghfwzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="czfwbg2"><bean:write name='czfwbg2' property='bgqfwzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="czfwbg2"><bean:write name='czfwbg2' property='bghfwzl'/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">年租金收入</td>
		<td width="21%"><logic:present name="czfwbg1"><fmt:formatNumber value="${czfwbg1.bgqnzjsr}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="czfwbg1"><fmt:formatNumber value="${czfwbg1.bghnzjsr}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="czfwbg2"><fmt:formatNumber value="${czfwbg2.bgqnzjsr}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="czfwbg2"><fmt:formatNumber value="${czfwbg2.bghnzjsr}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">年应纳税额</td>
		<td width="21%"><logic:present name="czfwbg1"><fmt:formatNumber value="${czfwbg1.bgqnynse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="czfwbg1"><fmt:formatNumber value="${czfwbg1.bghnynse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="czfwbg2"><fmt:formatNumber value="${czfwbg2.bgqnynse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="czfwbg2"><fmt:formatNumber value="${czfwbg2.bghnynse}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">其他</td>
		<td width="21%"><logic:present name="czfwbg1"><bean:write name='czfwbg1' property='bgqbz'/></logic:present>　</td>
		<td width="21%"><logic:present name="czfwbg1"><bean:write name='czfwbg1' property='bghbz'/></logic:present>　</td>
		<td width="21%"><logic:present name="czfwbg2"><bean:write name='czfwbg2' property='bgqbz'/></logic:present>　</td>
		<td width="21%"><logic:present name="czfwbg2"><bean:write name='czfwbg2' property='bghbz'/></logic:present>　</td>
	</tr>
	<tr>
		<td rowspan="5" width="23">承租房屋</td>
		<td width="14%">出租人名称</td>
		<td width="21%"><logic:present name="czufwbg1"><bean:write name='czufwbg1' property='bgqczrmc'/></logic:present>　</td>
		<td width="21%"><logic:present name="czufwbg1"><bean:write name='czufwbg1' property='bghczrmc'/></logic:present>　</td>
		<td width="21%"><logic:present name="czufwbg2"><bean:write name='czufwbg2' property='bgqczrmc'/></logic:present>　</td>
		<td width="21%"><logic:present name="czufwbg2"><bean:write name='czufwbg2' property='bghczrmc'/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">证件类型</td>
		<td width="21%"><logic:present name="czufwbg1">
		<fangtu:lx name="czufwbg1" property='bgqzjlxdm' codeList="ZJLX_LIST"/>
		</logic:present>
			　</td>
		<td width="21%"><logic:present name="czufwbg1">
		<fangtu:lx name="czufwbg1" property='bghzjlxdm' codeList="ZJLX_LIST"/>
		</logic:present>
			　</td>
		<td width="21%"><logic:present name="czufwbg2">
		<fangtu:lx name="czufwbg1" property='bgqzjlxdm' codeList="ZJLX_LIST"/>
		</logic:present>
			　</td>
		<td width="21%"><logic:present name="czufwbg2">
		<fangtu:lx name="czufwbg1" property='bghzjlxdm' codeList="ZJLX_LIST"/>
		</logic:present>
			　</td>
	</tr>
	<tr>
		<td width="14%">出租人证件号码</td>
		<td width="21%"><logic:present name="czufwbg1"><bean:write name='czufwbg1' property='bgqczrzjhm'/></logic:present>　</td>
		<td width="21%"><logic:present name="czufwbg1"><bean:write name='czufwbg1' property='bghczrzjhm'/></logic:present>　</td>
		<td width="21%"><logic:present name="czufwbg2"><bean:write name='czufwbg2' property='bgqczrzjhm'/></logic:present>　</td>
		<td width="21%"><logic:present name="czufwbg2"><bean:write name='czufwbg2' property='bghczrzjhm'/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">承租房屋坐落</td>
		<td width="21%"><logic:present name="czufwbg1"><bean:write name='czufwbg1' property='bgqczfwzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="czufwbg1"><bean:write name='czufwbg1' property='bghczfwzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="czufwbg2"><bean:write name='czufwbg2' property='bgqczfwzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="czufwbg2"><bean:write name='czufwbg2' property='bghczfwzl'/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">年租金</td>
		<td width="21%"><logic:present name="czufwbg1"><fmt:formatNumber value="${czufwbg1.bgqnzj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="czufwbg1"><fmt:formatNumber value="${czufwbg1.bghnzj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="czufwbg2"><fmt:formatNumber value="${czufwbg2.bgqnzj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="czufwbg2"><fmt:formatNumber value="${czufwbg2.bghnzj}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td rowspan="12" width="23">自用土地</td>
		<td width="14%">土地证书号</td>
		<td width="21%"><logic:present name="zytdbg1"><bean:write name='zytdbg1' property='bgqtdzsh'/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg1"><bean:write name='zytdbg1' property='bghtdzsh'/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><bean:write name='zytdbg2' property='bgqtdzsh'/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><bean:write name='zytdbg2' property='bghtdzsh'/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">土地坐落</td>
		<td width="21%"><logic:present name="zytdbg1"><bean:write name='zytdbg1' property='bgqtdzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg1"><bean:write name='zytdbg1' property='bghtdzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><bean:write name='zytdbg2' property='bgqtdzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><bean:write name='zytdbg2' property='bghtdzl'/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">土地面积</td>
		<td width="21%"><logic:present name="zytdbg1"><fmt:formatNumber value="${zytdbg1.bgqtdmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg1"><fmt:formatNumber value="${zytdbg1.bghtdmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><fmt:formatNumber value="${zytdbg2.bgqtdmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><fmt:formatNumber value="${zytdbg2.bghtdmj}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">其中：应税面积</td>
		<td width="21%"><logic:present name="zytdbg1"><fmt:formatNumber value="${zytdbg1.bgqqzysmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg1"><fmt:formatNumber value="${zytdbg1.bghqzysmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><fmt:formatNumber value="${zytdbg2.bgqqzysmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><fmt:formatNumber value="${zytdbg2.bghqzysmj}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">应税面积变更的原因</td>
		<td colspan="2">
			<logic:present name="zytdbg1" property="ysmjbgyy">
			<logic:equal name="zytdbg1" property="ysmjbgyy" value="0">
			新增<input type=checkbox checked>减少<input type=checkbox >面积变更<input type=checkbox >
			</logic:equal>
			<logic:equal name="zytdbg1" property="ysmjbgyy" value="1">
			新增<input type=checkbox >减少<input type=checkbox checked>面积变更<input type=checkbox >
			</logic:equal>
			<logic:equal name="zytdbg1" property="ysmjbgyy" value="2">
			新增<input type=checkbox >减少<input type=checkbox >面积变更<input type=checkbox checked>
			</logic:equal>
			</logic:present>  </td>
		<td colspan="2">
			<logic:present name="zytdbg2" property="ysmjbgyy">
			<logic:equal name="zytdbg2" property="ysmjbgyy" value="0">
			新增<input type=checkbox checked>减少<input type=checkbox >面积变更<input type=checkbox >
			</logic:equal>
			<logic:equal name="zytdbg2" property="ysmjbgyy" value="1">
			新增<input type=checkbox >减少<input type=checkbox checked>面积变更<input type=checkbox >
			</logic:equal>
			<logic:equal name="zytdbg2" property="ysmjbgyy" value="2">
			新增<input type=checkbox >减少<input type=checkbox >面积变更<input type=checkbox checked>
			</logic:equal>
			</logic:present>  </td>
	</tr>
	<tr>
		<td width="14%">其中：减免税面积</td>
		<td width="21%"><logic:present name="zytdbg1"><fmt:formatNumber value="${zytdbg1.bgqqzmsmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg1"><fmt:formatNumber value="${zytdbg1.bghqzmsmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><fmt:formatNumber value="${zytdbg2.bgqqzmsmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><fmt:formatNumber value="${zytdbg2.bghqzmsmj}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">减免税面积变更原因</td>
		<td colspan="2">
			<logic:present name="zytdbg1" property="jmsyzbgyy">
			<logic:equal name="zytdbg1" property="jmsyzbgyy" value="0">
			法定<input type=checkbox checked>搬迁<input type=checkbox>荒山<input type=checkbox>其他<input type=checkbox>
			</logic:equal>
			<logic:equal name="zytdbg1" property="jmsyzbgyy" value="1">
			法定<input type=checkbox>搬迁<input type=checkbox checked>荒山<input type=checkbox>其他<input type=checkbox>
			</logic:equal>
			<logic:equal name="zytdbg1" property="jmsyzbgyy" value="2">
			法定<input type=checkbox >搬迁<input type=checkbox>荒山<input type=checkbox checked>其他<input type=checkbox>
			</logic:equal>
			<logic:equal name="zytdbg1" property="jmsyzbgyy" value="3">
			法定<input type=checkbox >搬迁<input type=checkbox>荒山<input type=checkbox><br>其他<input type=checkbox checked>文件号
			<fangtu:formoptions collectionName="fangtuPrintForm" collectionProperty="zhengceList" name="zytdbg1" property="jmzcdm"/>
			</logic:equal>
			</logic:present>  </td>
		<td colspan="2">
			<logic:present name="zytdbg2" property="jmsyzbgyy">
			<logic:equal name="zytdbg2" property="jmsyzbgyy" value="0">
			法定<input type=checkbox checked>搬迁<input type=checkbox>荒山<input type=checkbox>其他<input type=checkbox>
			</logic:equal>
			<logic:equal name="zytdbg2" property="jmsyzbgyy" value="1">
			法定<input type=checkbox>搬迁<input type=checkbox checked>荒山<input type=checkbox>其他<input type=checkbox>
			</logic:equal>
			<logic:equal name="zytdbg2" property="jmsyzbgyy" value="2">
			法定<input type=checkbox >搬迁<input type=checkbox>荒山<input type=checkbox checked>其他<input type=checkbox>
			</logic:equal>
			<logic:equal name="zytdbg2" property="jmsyzbgyy" value="3">
			法定<input type=checkbox >搬迁<input type=checkbox>荒山<input type=checkbox><br>其他<input type=checkbox checked>文件号
			<fangtu:formoptions collectionName="fangtuPrintForm" collectionProperty="zhengceList" name="zytdbg2" property="jmzcdm"/>
			</logic:equal>
			</logic:present>  
		</td>
	</tr>
	<tr>
		<td width="14%">减免税期限</td>
		<td width="21%"><logic:present name="zytdbg1"><fmt:formatDate value="${zytdbg1.jmsqxq}" pattern="yyyyMMdd"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg1"><fmt:formatDate value="${zytdbg1.jmsqxz}" pattern="yyyyMMdd"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><fmt:formatDate value="${zytdbg2.jmsqxq}" pattern="yyyyMMdd"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><fmt:formatDate value="${zytdbg2.jmsqxz}" pattern="yyyyMMdd"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">每平方米税额</td>
		<td width="21%"><logic:present name="zytdbg1"><fmt:formatNumber value="${zytdbg1.bgqmpfmse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg1"><fmt:formatNumber value="${zytdbg1.bghmpfmse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><fmt:formatNumber value="${zytdbg2.bgqmpfmse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><fmt:formatNumber value="${zytdbg2.bghmpfmse}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">年应纳税额</td>
		<td width="21%"><logic:present name="zytdbg1"><fmt:formatNumber value="${zytdbg1.bgqnynse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg1"><fmt:formatNumber value="${zytdbg1.bghnynse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><fmt:formatNumber value="${zytdbg2.bgqnynse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><fmt:formatNumber value="${zytdbg2.bghnynse}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">代缴</td>
		<td width="21%"><logic:present name="zytdbg1" property="bgqsfdj">
			<logic:equal name="zytdbg1" property="bgqsfdj" value="0">是</logic:equal>
			<logic:equal name="zytdbg1" property="bgqsfdj" value="1">否</logic:equal>
			</logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg1" property="bghsfdj">
			<logic:equal name="zytdbg1" property="bghsfdj" value="0">是</logic:equal>
			<logic:equal name="zytdbg1" property="bghsfdj" value="1">否</logic:equal>
			</logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2" property="bgqsfdj">
			<logic:equal name="zytdbg2" property="bgqsfdj" value="0">是</logic:equal>
			<logic:equal name="zytdbg2" property="bgqsfdj" value="1">否</logic:equal>
			</logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2" property="bghsfdj">
			<logic:equal name="zytdbg2" property="bghsfdj" value="0">是</logic:equal>
			<logic:equal name="zytdbg2" property="bghsfdj" value="1">否</logic:equal>
			</logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">其他</td>
		<td width="21%"><logic:present name="zytdbg1"><bean:write name='zytdbg1' property='bgqbz'/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg1"><bean:write name='zytdbg1' property='bghbz'/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><bean:write name='zytdbg2' property='bgqbz'/></logic:present>　</td>
		<td width="21%"><logic:present name="zytdbg2"><bean:write name='zytdbg2' property='bghbz'/></logic:present>　</td>
	</tr>
	<tr>
		<td rowspan="8" width="23">出租土地</td>
		<td width="14%">土地证书号</td>
		<td width="21%"><logic:present name="cztdbg1"><bean:write name='cztdbg1' property='bgqtdzsh'/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg1"><bean:write name='cztdbg1' property='bghtdzsh'/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><bean:write name='cztdbg2' property='bgqtdzsh'/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><bean:write name='cztdbg2' property='bghtdzsh'/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">土地坐落</td>
		<td width="21%"><logic:present name="cztdbg1"><bean:write name='cztdbg1' property='bgqtdzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg1"><bean:write name='cztdbg1' property='bghtdzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><bean:write name='cztdbg2' property='bgqtdzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><bean:write name='cztdbg2' property='bghtdzl'/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">土地面积</td>
		<td width="21%"><logic:present name="cztdbg1"><fmt:formatNumber value="${cztdbg1.bgqtdmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg1"><fmt:formatNumber value="${cztdbg1.bghtdmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><fmt:formatNumber value="${cztdbg2.bgqtdmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><fmt:formatNumber value="${cztdbg2.bghtdmj}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">其中：减免税面积</td>
		<td width="21%"><logic:present name="cztdbg1"><fmt:formatNumber value="${cztdbg1.bgqqzmsmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg1"><fmt:formatNumber value="${cztdbg1.bghqzmsmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><fmt:formatNumber value="${cztdbg2.bgqqzmsmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><fmt:formatNumber value="${cztdbg2.bghqzmsmj}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">其中：应税面积</td>
		<td width="21%"><logic:present name="cztdbg1"><fmt:formatNumber value="${cztdbg1.bgqqzysmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg1"><fmt:formatNumber value="${cztdbg1.bghqzysmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><fmt:formatNumber value="${cztdbg2.bgqqzysmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><fmt:formatNumber value="${cztdbg2.bghqzysmj}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">每平方米税额</td>
		<td width="21%"><logic:present name="cztdbg1"><fmt:formatNumber value="${cztdbg1.bgqmpfmse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg1"><fmt:formatNumber value="${cztdbg1.bghmpfmse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><fmt:formatNumber value="${cztdbg2.bgqmpfmse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><fmt:formatNumber value="${cztdbg2.bghmpfmse}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">年应纳税额</td>
		<td width="21%"><logic:present name="cztdbg1"><fmt:formatNumber value="${cztdbg1.bgqnynse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg1"><fmt:formatNumber value="${cztdbg1.bghnynse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><fmt:formatNumber value="${cztdbg2.bgqnynse}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><fmt:formatNumber value="${cztdbg2.bghnynse}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">其他</td>
		<td width="21%"><logic:present name="cztdbg1"><bean:write name='cztdbg1' property='bgqbz'/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg1"><bean:write name='cztdbg1' property='bghbz'/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><bean:write name='cztdbg2' property='bgqbz'/></logic:present>　</td>
		<td width="21%"><logic:present name="cztdbg2"><bean:write name='cztdbg2' property='bghbz'/></logic:present>　</td>
	</tr>
	<tr>
		<td rowspan="6" width="23">承租土地</td>
		<td width="14%">出租人名称</td>
		<td width="21%"><logic:present name="czutdbg1"><bean:write name='czutdbg1' property='bgqczrmc'/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg1"><bean:write name='czutdbg1' property='bghczrmc'/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg2"><bean:write name='czutdbg2' property='bgqczrmc'/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg2"><bean:write name='czutdbg2' property='bghczrmc'/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">证件类型</td>
		<td width="21%"><logic:present name="czutdbg1"><fangtu:lx name="czutdbg1" property='bgqzjlxdm' codeList="ZJLX_LIST"/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg1"><fangtu:lx name="czutdbg1" property='bghzjlxdm' codeList="ZJLX_LIST"/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg2"><fangtu:lx name="czutdbg2" property='bgqzjlxdm' codeList="ZJLX_LIST"/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg2"><fangtu:lx name="czutdbg2" property='bghzjlxdm' codeList="ZJLX_LIST"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">出租人证件号码</td>
		<td width="21%"><logic:present name="czutdbg1"><bean:write name='czutdbg1' property='bgqczrzjhm'/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg1"><bean:write name='czutdbg1' property='bghczrzjhm'/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg2"><bean:write name='czutdbg2' property='bgqczrzjhm'/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg2"><bean:write name='czutdbg2' property='bghczrzjhm'/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">承租土地坐落</td>
		<td width="21%"><logic:present name="czutdbg1"><bean:write name='czutdbg1' property='bgqcztdzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg1"><bean:write name='czutdbg1' property='bghcztdzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg2"><bean:write name='czutdbg2' property='bgqcztdzl'/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg2"><bean:write name='czutdbg2' property='bghcztdzl'/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">土地面积</td>
		<td width="21%"><logic:present name="czutdbg1"><fmt:formatNumber value="${czutdbg1.bgqtdmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg1"><fmt:formatNumber value="${czutdbg1.bghtdmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg2"><fmt:formatNumber value="${czutdbg2.bgqtdmj}" pattern="0.00"/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg2"><fmt:formatNumber value="${czutdbg2.bghtdmj}" pattern="0.00"/></logic:present>　</td>
	</tr>
	<tr>
		<td width="14%">其他</td>
		<td width="21%"><logic:present name="czutdbg1"><bean:write name='czutdbg1' property='bgqbz'/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg1"><bean:write name='czutdbg1' property='bghbz'/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg2"><bean:write name='czutdbg2' property='bgqbz'/></logic:present>　</td>
		<td width="21%"><logic:present name="czutdbg2"><bean:write name='czutdbg2' property='bghbz'/></logic:present>　</td>
	</tr>
</table>
<p>填表人：盖章（签字）</p>
<% if(i != pages-1){ %>
<div style="PAGE-BREAK-AFTER: always"></div>
<%
	}
	}
%>


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
