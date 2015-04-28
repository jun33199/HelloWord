<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@page import ="com.ttsoft.bjtax.smsb.sgsswszmlr.common.Constant" %>
<style type="text/css">
<!--
@import url(../css/text.css);
-->
</style>

<html:html>
<head>
<title>手工录入税收完税证明维护</title>
<SCRIPT LANGUAGE="jscript" src="../js/treatImage.js"></SCRIPT>
<SCRIPT LANGUAGE="jscript" src="../js/smsb_common.js"></SCRIPT>
<script language=JavaScript src="../js/function.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
<%@ include file="include/header.jsp"%>
<html:form action="/webapp/sgsswszmlr/sgsswszmlrAction.do" method="POST">
<html:hidden property="actionType"/>
<html:hidden property="pageSize"/>
<html:hidden property="nextPage"/>
<html:hidden property="totalpage"/>
<html:hidden property="wszmKey"/>

<table width="94%" align="center" cellspacing="0" class="table-99">
   <tr>
      <td class="1-td1">手工录入税收完税证明维护</td>
   </tr>
   <tr>
      <td class="1-td2"  colspan="7">
   <br>
   <table width="70%" cellspacing="0" class="table-99">
      <tr class="black9">
      	 <td width="12%" align="center" nowrap class="2-td2-t-left"> 
			<div align="right">纳税人识别号
            &nbsp;
			</div></td>
            <td class="2-td2-t-left" width="19%" align="center" nowrap><div align="left">
			<html:text property="query_nsrsbh"  size="25"  maxlength="50" tabIndex="-1"/>
			</div></td>
         <td width="12%" align="center" nowrap class="2-td2-t-left"> 
		 <div align="center">纳税人名称</div></td>
            <td class="2-td2-t-left" width="11%" align="center" nowrap>
			<div align="left">
            <html:text property="query_nsrmc"  size="25" maxlength="200" tabIndex="-1"/>
			</div></td>
         <td width="13%" align="center" class="2-td2-t-left" nowrap> 
         	<div align="center" >完税证明号码</div>
         	</td>
	     <td class="2-td2-t-center" width="32%" align="center" nowrap>
	     	<div align="left">
	     	<html:text property="query_wspzh"  size="25" maxlength="10" tabIndex="-1"/>
	     	</div></td>
      </tr>
   </table>
   <br><br><br>
   <table width="99%" cellpadding="0" cellspacing="0" class="table-99">
      <tr>
      	 <td nowrap class="2-td2-t-left" width="2%"><div align = "center">序号</div></td>
      	 <td nowrap class="2-td2-t-left" width="8%"><div align = "center">完税证明号码</div></td>
      	  <td nowrap class="2-td2-t-left" width="10%"><div align = "center">录入原凭证类型</div></td>
      	 <td nowrap class="2-td2-t-left" width="20%"><div align = "center">纳税人识别号</div></td>
	     <td nowrap class="2-td2-t-left" width="20%"><div align = "center">纳税人名称</div></td>
	     <td nowrap class="2-td2-t-left" width="5%"><div align = "center">金额合计</div></td>
	     <!-- <td nowrap class="2-td2-t-left" width="10%"><div align = "center">已打印次数</div></td> -->
	     <td nowrap class="2-td2-t-left" width="5%"><div align = "center">状态</div></td>
	     <td nowrap class="2-td2-t-center" width="10%"><div align = "center">操作</div></td>
      </tr>  
      <logic:iterate id="item1" name="sgsswszmlrForm" property="queryList_onePage" >
      <tr>
         <td class="2-td2-left" width="2%" align="center">
         <bean:write name='item1' property='indexId'/>&nbsp;</td>
         <td class="2-td2-left" width="8%" align="center"><a href="#" onclick="do2ShowMXorUpdate('<bean:write name="item1" property="PZZLDM"/>','<bean:write name="item1" property="WSZH"/>','<bean:write name="item1" property="NDZB"/>','<bean:write name='item1' property='HJJE'/>');"><bean:write name='item1' property='WSZH'/></a>&nbsp;</td>
         <td class="2-td2-left" width="10%" align="center">
         	<logic:equal  name="item1" property="PZLXDM" value="0">
         	缴税凭证
         	</logic:equal>
         	<logic:notEqual  name="item1" property="PZLXDM" value="0">
         	退税凭证
         	</logic:notEqual>
         </td>
         <td class="2-td2-textleft" width="20%" align="center"><bean:write name='item1' property='NSRSBH'/>&nbsp;</td>
         <td class="2-td2-left" width="20%" align="center"><bean:write name='item1' property='NSRMC'/>&nbsp;</td>
		 <td class="2-td2-left" width="5%" align="center"><bean:write name='item1' property='HJJE'/>&nbsp;</td>
		 <%-- <td class="2-td2-left" width="10%" align="center"><bean:write name='item1' property='DYCS'/>&nbsp;</td> --%>
		 <td class="2-td2-left" width="5%" align="center">
		 <logic:equal  name="item1" property="YXBZ" value="0">
		 正常
		 </logic:equal>
		 <logic:notEqual name="item1" property="YXBZ" value="0" >
		 已作废
		 </logic:notEqual>
		 &nbsp;</td>
		 
		 <td class="2-td2-center" width="10%" align="center" nowrap="nowrap">
		 <logic:equal  name="item1" property="YXBZ" value="0">
			 <a href="#" title="点击链接作废出具票据" onclick="doCancle('<bean:write name="item1" property="PZZLDM"/>','<bean:write name="item1" property="WSZH"/>','<bean:write name="item1" property="NDZB"/>','<bean:write name='item1' property='HJJE'/>','<bean:write name='item1' property='cjwszmBYothers'/>');">
	         	作废</a>
			 <a href="#" title="点击链接重新打印" onclick="doRePrint('<bean:write name="item1" property="PZZLDM"/>','<bean:write name="item1" property="WSZH"/>','<bean:write name="item1" property="NDZB"/>','<bean:write name='item1' property='HJJE'/>');">
	          	<logic:notEqual name="item1" property="DYCS" value="0">
	          		重打
	          	</logic:notEqual>
	            <logic:equal  name="item1" property="DYCS" value="0">
	          		打印</logic:equal></a>
		</logic:equal>&nbsp;
		 </td>						
      </tr>
      </logic:iterate>  
</table>
<br>
<div align="right">
   (第<bean:write name="sgsswszmlrForm" property="nextPage"/>页/共<bean:write name="sgsswszmlrForm" property="totalpage"/>页)
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
      <td height="47"  > &nbsp;&nbsp;1．该表中“纳税人识别号”、“纳税人名称”、“完税证明号码”三个查询条件可独立。
                     <br>&nbsp;&nbsp;2．如果票据的状态为“已作废”则在“操作”一栏不显示任何可选操作项，即作废票不能进行作废和打印。
                     <br>&nbsp;&nbsp;3．如果要作废的完税证明已经被其他模块使用并出具了有效完税证明，则不能进行作废和修改。
                     <br>&nbsp;&nbsp;4．如果完税证明已经打印，则不能进行修改。</td>
   </tr>
</table>
   
</table>
</html:form>
<%@ include file="include/footer.jsp"%>

<script language="JavaScript">

//进入页面时将焦点设为计算机代码录入
function fnOnLoad()
{
	//显示提示信息
	var actionType = document.forms[0].actionType.value;
	var saveIsSucc = '<bean:write name="sgsswszmlrForm" property="saveIsSucc" />';
	var message = '<bean:write name="sgsswszmlrForm" property="message" />';
	
	if(message!=""){
		alert(message);
	}else{
		if(actionType=="Save" || actionType=="Update"){
			if(saveIsSucc == '<%=Constant.CONS_SGLR_SAVEISSUCC_Y%>'){
				alert("保存成功!");
			}
		}else{
			// 页面进入焦点确定
			document.forms[0].query_nsrsbh.focus();
		}
	}
	
}

/**
 * 查看明细 或者 修改信息
 */
	function do2ShowMXorUpdate(pzzldm,wspzh,ndzb,hjje){
		document.forms[0].target="";
		if(pzzldm != "" && wspzh != "" && ndzb !="" && hjje !=""){
			document.forms[0].wszmKey.value=pzzldm+"-"+wspzh+"-"+ndzb+"-"+hjje;
			doSubmitForm("ShowOne");
			return false;
		}
	}
	/**
	*进入原号码打印预览页面
	*/
	function doRePrint(pzzldm,wspzh,ndzb,hjje){
		//document.forms[0].target="_blank";
		if(pzzldm != "" && wspzh != "" && ndzb !=""  && hjje !=""){
			document.forms[0].wszmKey.value=pzzldm+"-"+wspzh+"-"+ndzb+"-"+hjje;
			doSubmitForm("Print");
			return false;
		}
		
		
	}
	
	function doCancle(pzzldm,wspzh,ndzb,hjje,cjwszmBYothers){
		
		if(pzzldm != "" && wspzh != "" && ndzb !=""  && hjje !="" && cjwszmBYothers != ""){
			if(cjwszmBYothers == '<%=Constant.CONS_SGLR_CJWSZM_BY_OTHERS_Y%>'){
				alert("该完税证明已经被其他模块使用并出具了有效完税证明，不能进行作废，请确认！");
				return false;
			}
			if(confirm("该操作将作废该票，请确认！")){
				document.forms[0].wszmKey.value=pzzldm+"-"+wspzh+"-"+ndzb+"-"+hjje;
				doSubmitForm("Cancel");
				return false;
			}else{
				return false;
			}
		}
	}
	

	// 查询条件校验及跳转 
	function doQuery() {
		document.forms[0].target="";
		if(trim(document.forms[0].query_nsrsbh.value) != "" || trim(document.forms[0].query_nsrmc.value) != "" || trim(document.forms[0].query_wspzh.value) != "") {
			document.forms[0].nextPage.value = 1;
			doSubmitForm("Query");
			return false;
			
		} else {
			alert("请输入查询条件");
			return false;
		}
	}
	
	// 翻页
	function fn_ChangePage(type)
	{
		//获取上一次操作类型
		temp=document.all.actionType.value;
		//设置当前操作类型
		if(temp=="Query" || temp=="ChangePage"){
			//temp="ChangePage";
			temp="Query";
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


