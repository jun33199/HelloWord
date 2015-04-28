<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>

<style type="text/css">
<!--
@import url(../css/text.css);
-->
</style>

<html:html>
<head>
<title>安置残疾人就业企业营业税减免税查询统计表</title>
<SCRIPT LANGUAGE="jscript" src="../js/treatImage.js"></SCRIPT>
<SCRIPT LANGUAGE="jscript" src="../js/smsb_common.js"></SCRIPT>
<script language=JavaScript src="../js/function.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
<%@ include file="include/header.jsp"%>
<html:form action="/webapp/smsb/cjrjyjmscxtjAciton" method="POST">
<html:hidden property="actionType"/>
<html:hidden property="headSwjgzzjgdm"/>
<html:hidden property="pageSize"/>
<html:hidden property="nextPage"/>
<html:hidden property="totalpage"/>
<html:hidden property="message"/>

<table width="94%" align="center" cellspacing="0" class="table-99">
   <tr>
      <td class="1-td1">安置残疾人就业企业营业税减免税查询统计表</td>
   </tr>
   <tr>
      <td class="1-td2"  colspan="7">
   <br>
   <table width="70%" cellspacing="0" class="table-99">
      <tr class="black9">
      	 <td width="12%" align="center" nowrap class="2-td2-t-left"> 
			<div align="right">计算机代码
            &nbsp;
			</div></td>
            <td class="2-td2-t-left" width="19%" align="center" nowrap><div align="left">
			<html:text property="queryJsjdm"  size="25"  maxlength="8" tabIndex="-1"/>
			</div></td>
         <td width="12%" align="center" nowrap class="2-td2-t-left"> 
		 <div align="center">企业名称</div></td>
            <td class="2-td2-t-left" width="11%" align="center" nowrap>
			<div align="left">
            <html:text property="queryQymc"  size="25" maxlength="200" tabIndex="-1"/>
			</div></td>
         <td width="13%" align="center" class="2-td2-t-left" nowrap> 
         	<div align="center" >主管税务机关</div>
         	</td>
	     <td class="2-td2-t-center" width="32%" align="center" nowrap>
	     	<div align="left">
	     	<html:select property = "queryZgswjgdm"><html:options property="zgswjgList" /></html:select></div></td>
      </tr>
      <tr>
         <td width="11%" align="center" nowrap class="2-td2-left"> 
		 <div align="right">查询日期 起始</div>
		 </td>
		 <td width="19%" align="center" nowrap class="2-td2-left">
         <div align="left">
         <html:text property="queryCxqsrq" maxlength="8" size="25"  tabIndex="-1"/></div></td>
         <td class="2-td2-left">截至</td>
         <td width="19%" align="center" nowrap class="2-td2-center" colspan="3">
		 			<div align="left">
         <html:text property="queryCxjzrq" maxlength="8" size="25"  tabIndex="-1"/></div>
		 </td>
      </tr>
   </table>
   <br><br><br>
   <table width="99%" cellpadding="0" cellspacing="0" class="table-99">
      <tr width="150" class="black9">
		<td rowspan="2" width="4%" nowrap class="2-td2-t-left"><div align = "center">序号</div></td>
		<td colspan="6" nowrap class="2-td2-t-left"><div align ="center">审批结果</div></td>
		<td rowspan="2" nowarp class="2-td2-t-left"><div align ="center">实际减征营业税额</div></td>
		<td rowspan="2" nowarp class="2-td2-t-center"><div align ="center">安置残疾职工人数</div></td>
      </tr>
      <tr>
      	 <td nowrap class="2-td2-left" width="8%"><div align = "center">计算机代码</div></td>
      	 <td nowrap class="2-td2-left" width="22%"><div align = "center">企业名称</div></td>
	     <td nowrap class="2-td2-left" width="16%"><div align = "center">审批表编号</div></td>
	     <td nowrap class="2-td2-left" width="8%"><div align = "center">年减征营业税限额</div></td>
	     <td nowrap class="2-td2-left" width="9%"><div align = "center">减征开始日期</div></td>
	     <td nowrap class="2-td2-left" width="9%"><div align = "center">减征截止日期</div></td>
      </tr>  
      <logic:iterate id="item1" name="cjrjyjmscxtjForm" property="dataList" >
      <tr>
         <td class="2-td2-left" width="4%" align="center"><bean:write name='item1' property='indexId'/>&nbsp;</td>
         <td class="2-td2-left" width="8%" align="center"><bean:write name='item1' property='jsjdm'/>&nbsp;</td>
         <td class="2-td2-textleft" width="22%" align="center"><bean:write name='item1' property='qymc'/>&nbsp;</td>
         <td class="2-td2-left" width="16%" align="center"><bean:write name='item1' property='spbbh'/>&nbsp;</td>
		 <td class="2-td2-left" width="6%" align="center"><bean:write name='item1' property='njzyysxe'/>&nbsp;</td>
		 <td class="2-td2-left" width="8%" align="center"><bean:write name='item1' property='jzksrq'/>&nbsp;</td>
	     <td class="2-td2-left" width="8%" align="center"><bean:write name='item1' property='jzjzrq'/>&nbsp;</td>
		 <td class="2-td2-left" width="8%" align="center"><bean:write name='item1' property='sjjzyyse'/>&nbsp;</td>	
		 <td class="2-td2-center" width="9%" align="center"><bean:write name='item1' property='azcjzgrs'/>&nbsp;</td>						
      </tr>
      </logic:iterate>  
      <tr>
	  <td nowrap class="2-td2-left-yys-back" height="28" ><div align = "center">合计</div></td>
	  <td nowrap class="2-td2-left-yys-back" height="28" align="center">-------</td>
	  <td nowrap class="2-td2-left-yys-back" height="28" align="center">-------</td>
	  <td nowrap class="2-td2-left-yys-back" height="28" align="center">-------</td>
	  <td nowrap class="2-td2-left-yys-back" height="28" width="8%">&nbsp;<bean:write name='cjrjyjmscxtjForm' property='njzyysxeTotal'/></td>
	  <td nowrap class="2-td2-left-yys-back" height="28" width="8%" align="center">-------</td>
	  <td nowrap class="2-td2-left-yys-back" height="28" width="8%" align="center">-------</td>
	  <td nowrap class="2-td2-left-yys-back" height="28">&nbsp;<bean:write name='cjrjyjmscxtjForm' property='sjjzyyseTotal'/></td>
	  <td nowrap class="2-td2-center-yys-back" height="28">&nbsp;<bean:write name='cjrjyjmscxtjForm' property='azcjzgrsTotal'/></td>
      </tr>
</table>
<br>
<div align="right">
   (第<bean:write name="cjrjyjmscxtjForm" property="nextPage"/>页/共<bean:write name="cjrjyjmscxtjForm" property="totalpage"/>页)
   <!--翻页功能开始-->
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="第一页" alt="第一页" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="前页" alt="前页" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="后页" alt="后页" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="最后一页" alt="最后一页" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
   <!--翻页功能开始-->
</div>
<br>
<div align="center">
   <input type="image" accesskey="q" tabIndex="-1" onClick="doQuery(); return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)" onMouseOut="MM_swapImgRestore()" alt="查询" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
   <input type="image" accesskey="c" tabIndex="-1" onClick="tuichu(); return false;" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor: hand">
</div>
<br>
<table width="99%" border="0" cellpadding="0" cellspacing="0">
   <tr>
      <td>
         <hr width="100%" size="1" >
      </td>
	  <td width="99" align="center" class="black9">
	     <strong><font color="#0000FF">注 意 事 项</font></strong>
	  </td>
	  <td><hr width="100%" size="1" ></td>
   </tr>
</table>
<table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
      <td height="47"  > &nbsp;&nbsp;1．该表中“企业名称”,“计算机代码”和“主管税务机关”三个查询条件可独立，但同时必须与“查询日期”条件捆绑使用。<br>&nbsp;&nbsp;2．其中“企业名称”一览输入字数不做限制；计算机代码为8位数字或字母。<br>&nbsp;&nbsp;3．其中“查询日期”中的时间设定可从“日历”框进行选择。<br>&nbsp;&nbsp;4．其中“主管税务机关”为下拉菜单，且有级次权限设定。即所级税务机关只能查询本所，区级税务机关可查询所辖区县的所有税务所，市级税务机关可查询本市范围内所有区县情况<br>&nbsp;&nbsp;5．其中“合计”一栏查询系统可自动累加。</td>
   </tr>
</table>
   
</table>
</html:form>
<%@ include file="include/footer.jsp"%>

<script language="JavaScript">

	// 查询条件校验及跳转 
	function doQuery() {

		if(trim(document.forms[0].queryJsjdm.value) != "" || trim(document.forms[0].queryQymc.value) != "" || (document.forms[0].queryZgswjgdm.value).substr(5,(document.forms[0].queryZgswjgdm.value).length) != "全部") {
			if(trim(document.forms[0].queryJsjdm.value) != "") {
				if(!/^[A-Za-z0-9]{8}$/.test(trim(document.forms[0].queryJsjdm.value))) {
					alert("计算机代码由8位数字与字母组成");
					return false;
				}
			}
			if(trim(document.forms[0].queryCxqsrq.value) == "") {
				alert("请输入查询起止时间");
				return false;
			} else {
				var cxjzrqNull = false;
				var isEarlyThan = false;
				if(trim(document.forms[0].queryCxjzrq.value) == "") {
					cxjzrqNull = true;
					isEarlyThan = true;
				} else {
					cxjzrqNull = isDate(document.forms[0].queryCxjzrq);
					isEarlyThan = (compare(document.forms[0].queryCxqsrq,document.forms[0].queryCxjzrq)>=0);
				}
				if(isDate(document.forms[0].queryCxqsrq,"") && cxjzrqNull) {
					if (isEarlyThan){
document.forms[0].nextPage.value = 1;
						doSubmitForm("Query");
						return false;
					} else {
						alert("起始日期必须小于或等于截止日期！");
					}
				} 
			}
		} else {
			alert("请输入查询条件");
			return false;
		}
	}

	//进入页面时将焦点设为计算机代码录入
	// 页面进入焦点确定
	function fnOnLoad()
	{
		document.forms[0].queryJsjdm.focus();
		if(document.forms[0].message.value!=""){
			alert(document.forms[0].message.value);
		}
	}
	
	// 翻页
	function fn_ChangePage(type)
	{
		//获取上一次操作类型
		temp=document.all.actionType.value;
		//设置当前操作类型
		if(temp=="Query" || temp=="ChangePage"){
			temp="ChangePage";
		} else{
			temp="Show";
		}
		//
		var tmpNextPage=document.forms[0].nextPage.value*1;
		var tmpTotalPage=document.forms[0].totalpage.value*1;
		//alert("tmpNextPage:"+tmpNextPage+"|"+"tmpTotalPage:"+tmpTotalPage+"|"+"type:"+type);
		if(temp=="Show"){
			alert("没有任何已查询数据,请先查询...");
			return false;
		}
		if (type == "first")
		{
			if(tmpNextPage==1){
				alert("本次查询已到达第一页,请返回...");
				return false;
			}else{
					document.forms[0].nextPage.value="1";
			}
		}
		else if (type == "previous")
		{
			if(tmpNextPage==1){
				alert("本次查询已到达第一页,请返回...");
				return false;
			}else{
					document.forms[0].nextPage.value =(tmpNextPage-1)+"";
			}
		}
		else if (type == "next")
		{
			if(tmpNextPage>=tmpTotalPage){
				alert("本次查询已到达最后一页,请返回...");
				return false;
			}else{
					document.forms[0].nextPage.value =(tmpNextPage+1)+"";
			}
		}
		else if (type == "last")
		{
			if(tmpTotalPage==1){
				alert("本次查询只存在一页,请返回...");
				return false;
			}else if(tmpNextPage>=tmpTotalPage){
				alert("本次查询已到达最后一页,请返回...");
				return false;
			}
			else{
				document.forms[0].nextPage.value=document.forms[0].totalpage.value;
			}
		}
		//提交查询
		doSubmitForm(temp);
		return false;
	}
	
</script>
</body>
</html:html>


