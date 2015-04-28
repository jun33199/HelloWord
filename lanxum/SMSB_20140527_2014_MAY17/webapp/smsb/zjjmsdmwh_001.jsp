<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@page import ="com.ttsoft.bjtax.smsb.sgsswszmlr.common.Constant" %>
<%@page import ="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<style type="text/css">
<!--
@import url(../css/text.css);
-->
</style>

<html:html>
<head>
<title>总局减免税代码维护</title>
<SCRIPT LANGUAGE="jscript" src="../js/treatImage.js"></SCRIPT>
<SCRIPT LANGUAGE="jscript" src="../js/smsb_common.js"></SCRIPT>
<script language=JavaScript src="../js/function.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
<%@ include file="include/header.jsp"%>
<html:form action="/webapp/jccswh/zjjmsdmwhAction.do" method="POST">
<html:hidden property="actionType"/>

<html:hidden property="query_szdm"/>
<html:hidden property="query_jmslxdldm"/>
<html:hidden property="query_jmslxxldm"/>

<html:hidden property="modifyKey_jmslxdm"/>
<html:hidden property="updateType"/>
<html:hidden property="pageSize"/>
<html:hidden property="nextPage"/>
<html:hidden property="totalpage"/>



<table width="94%" align="center" cellspacing="0" class="table-99">
   <tr>
      <td class="1-td1">总局减免税代码维护查询</td>
   </tr>
   <tr>
      <td class="1-td2"  colspan="7">
   <br>
   <table width="70%" cellspacing="0" class="table-99">
      <tr class="black9">
      	 <td width="12%" align="center" nowrap class="2-td2-t-left"> 
			<div align="right">减免税类型代码
            &nbsp;
			</div></td>
            <td class="2-td2-t-left" width="19%" align="center" nowrap><div align="left">
			<html:text property="query_jmslxdm"  size="25"  maxlength="50" tabIndex="-1"/>
			</div></td>
         <td width="12%" align="center" nowrap class="2-td2-t-left"> 
		 <div align="center">文号</div></td>
            <td class="2-td2-t-left" width="11%" align="center" nowrap>
			<div align="left">
            <html:text property="query_wh"  size="25" maxlength="200" tabIndex="-1"/>
			</div></td>
         <td width="13%" align="center" class="2-td2-t-left" nowrap> 
         	<div align="center" >税种</div>
         	</td>
	     <td class="2-td2-t-center" width="32%" align="center" nowrap>
	     	<div align="left">
	     	   <select id="selectedSzdm" style="width=300px">
						<logic:iterate id="item" name="zjjmsdmwhForm" property="smsmList" indexId="index">
							<option value='<bean:write name='item' property='szsmdm'/>'>
								<bean:write name="item" property="szsmmc" />
							</option>
						</logic:iterate>
				</select>
	     	</div></td>
      </tr>
      <tr class="black9">
      	 <td width="12%" align="center" nowrap class="2-td2-left"> 
			<div align="right">减免税政策起始年度
            &nbsp;
			</div></td>
            <td class="2-td2-left" width="19%" align="center" nowrap><div align="left">
			<html:text property="query_jmszcqsnd"  size="25"  maxlength="50" tabIndex="-1"/>
			</div></td>
         <td width="12%" align="center" nowrap class="2-td2-left"> 
		 <div align="center">减免税类型大类代码</div></td>
            <td class="2-td2-left" width="11%" align="center" nowrap>
			<div align="left">
				<select id="selectedDL" onchange="doFilterJmxzxldm_byDLDM(this)" style="width=100%">
						<logic:iterate id="item" name="zjjmsdmwhForm" property="jmxzdlList"
							indexId="index">
							<option value='<bean:write name="item" property="jmxzdm"/>'>
								<bean:write name="item" property="jmxzmc" />
							</option>
						</logic:iterate>
				</select>
			</div></td>
         <td width="13%" align="center" class="2-td2-left" nowrap> 
         	<div align="center" >减免税类型小类代码</div>
         	</td>
	     <td class="2-td2-center" width="32%" align="left" nowrap>
		     <div align="left">
		     		<select id="selectedXL" style="width=300px">
						<logic:iterate id="item" name="zjjmsdmwhForm" property="jmxzxlList"
							indexId="index">
							<option value='<bean:write name="item" property="jmxzdm"/>'>
								<bean:write name="item" property="jmxzmc" />
							</option>
						</logic:iterate>
					</select>
			</div>
		</td>
      </tr>
      <tr class="black9">
      	 <td width="12%" align="center" nowrap class="2-td2-left"> 
			<div align="right">录入开始日期
            &nbsp;
			</div></td>
            <td class="2-td2-left" width="19%" align="center" nowrap><div align="left">
			<html:text property="query_lrrqKS"  size="25"  maxlength="8" tabIndex="-1"/>
			</div></td>
         <td width="12%" align="center" nowrap class="2-td2-left"> 
		 <div align="center">录入结束日期</div></td>
            <td class="2-td2-left" width="11%" align="center" nowrap>
			<div align="left">
            <html:text property="query_lrrqJS"  size="25" maxlength="8" tabIndex="-1"/>
			</div></td>
      	<td width="12%" align="center" nowrap class="2-td2-left"> 
      		<div align="center">有效标识
            &nbsp;
			</div></td>
            <td class="2-td2-center" align="center" nowrap><div align="left">
				<html:select property="query_yxbs" style="width=200px">
					<html:option value="">全部</html:option>
					<html:option value="0">有效</html:option>
					<html:option value="9">无效</html:option>
				</html:select>
			</div></td>
      </tr>
   </table>
   <br>
   <div align="center">
   <input type="image" accesskey="q" tabIndex="-1" onClick="doQuery(); return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)" onMouseOut="MM_swapImgRestore()" alt="查询" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
   <input type="image" accesskey="c" tabIndex="-1" onClick="tuichu(); return false;" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor: hand">&nbsp;&nbsp;&nbsp;&nbsp;
   <img src="<%=static_contextpath%>images/fanhui1.jpg" value="返回到新增页面" name="fanhui" width="79" height="22" border="0" onClick="doFanHui();return false;" id="fanhui" alt="返回到新增页面" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()">
</div>
<br><br>
   <table width="99%" cellpadding="0" cellspacing="0" class="table-99">
      <tr>
      	 <td nowrap class="2-td1-left" width="2%"><div align = "center">序号</div></td>
      	 <td nowrap class="2-td1-left" width="5%"><div align = "center">税种</div></td>
      	  <td nowrap class="2-td1-left" width="5%"><div align = "center">减免性质大类</div></td>
      	 <td nowrap class="2-td1-left" width="5%"><div align = "center">减免性质小类</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">减免税类型代码</div></td>
	     <td nowrap class="2-td1-left" width="20%"><div align = "center">减免税类型名称</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">起始年度</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">文号</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">录入人</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">录入\修改日期</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">注销标记</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">有效标记</div></td>
	     <td nowrap class="2-td1-left" width="5%"><div align = "center">备注</div></td>
	     <td nowrap class="2-td1-center" width="5%"><div align = "center">操作</div></td>
      </tr>  
      <logic:iterate id="item1" name="zjjmsdmwhForm" property="queryList_onePage" >
      <tr onclick="changeStyle(this)" id="<bean:write name='item1' property='jmslxdm'/>">
         <td class="2-td2-left"  align="center" style="	font-size: 10pt;font-weight: bold;">
         <bean:write name='item1' property='indexId'/>&nbsp;</td>
         <td class="2-td2-left"  align="center"><bean:write name='item1' property='sz'/>&nbsp;</td>
         <td class="2-td2-left"  align="center"><bean:write name='item1' property='jmslxdldmmc'/>&nbsp;</td>
         <td class="2-td2-left"  align="center"><bean:write name='item1' property='jmslxxldmmc'/>&nbsp;</td>
         <td class="2-td2-left" align="center"><bean:write name='item1' property='jmslxdm'/>&nbsp;</td>
         <td class="2-td2-textleft"  align="center"><bean:write name='item1' property='jmslxmc'/>&nbsp;</td>
         <td class="2-td2-left" align="center"><bean:write name='item1' property='jmszcqsnd'/>&nbsp;</td>
         <td class="2-td2-textleft" align="center"><bean:write name='item1' property='wh'/>&nbsp;</td>
         <td class="2-td2-left"  align="center"><bean:write name='item1' property='lrr'/>&nbsp;</td>
         <td class="2-td2-left" align="center"><bean:write name='item1' property='lrrq'/>&nbsp;</td>
         <td class="2-td2-left"  align="center">
         	<logic:present name="item1" property="zxbz">
				 <logic:equal  name="item1" property="zxbz" value="0">
				 正常
				 </logic:equal>
				 <logic:notEqual name="item1" property="zxbz" value="0" >
				 <font color="red">已注销</font>
				 </logic:notEqual>
         	</logic:present>
         	<logic:notPresent name="item1" property="zxbz">
         	正常
         	</logic:notPresent>
		 &nbsp;</td>
		 <td class="2-td2-left"  align="center">
		 <logic:present name="item1" property="yxbs">
			 <logic:equal  name="item1" property="yxbs" value="0">
			 有效
			 </logic:equal>
			 <logic:notEqual name="item1" property="yxbs" value="0" >
			 <font color="red">无效</font>
			 </logic:notEqual>
		</logic:present>
		<logic:notPresent name="item1" property="yxbs">
		有效
		</logic:notPresent>
		 &nbsp;</td>
		 <td class="2-td2-textleft" align="center"><bean:write name='item1' property='bz'/>&nbsp;</td>
		 
		 <td class="2-td2-center" align="center" nowrap="nowrap">
		 		<logic:present name="item1" property="yxbs">
					 <logic:equal  name="item1" property="yxbs" value="0">
					  	<a href="#" title="点击链接进行修改" onclick="do2ShowMXorUpdate('<bean:write name="item1" property="jmslxdm"/>');">修改</a>
					 </logic:equal>
				</logic:present>
				<logic:notPresent name="item1" property="yxbs">
			 		<a href="#" title="点击链接进行修改" onclick="do2ShowMXorUpdate('<bean:write name="item1" property="jmslxdm"/>');">修改</a>
				</logic:notPresent>
				
	         	
			 <a href="#" title="点击链接注销该条信息" onclick="doChangeYxbs('<bean:write name="item1" property="jmslxdm"/>');">
				 <logic:present name="item1" property="yxbs">
					 <logic:equal  name="item1" property="yxbs" value="0">置成无效</logic:equal>
					 <logic:notEqual name="item1" property="yxbs" value="0" >置成有效 </logic:notEqual>
				</logic:present>
				<logic:notPresent name="item1" property="yxbs">置成无效</logic:notPresent></a>
		 </td>						
      </tr>
      </logic:iterate>  
</table>
<br>
<div align="right">
   (第<bean:write name="zjjmsdmwhForm" property="nextPage"/>页/共<bean:write name="zjjmsdmwhForm" property="totalpage"/>页)
   <!--翻页功能开始-->
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="第一页" alt="第一页" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="前页" alt="前页" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="后页" alt="后页" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="最后一页" alt="最后一页" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
   <!--翻页功能开始-->
</div>
<br>
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
      <td height="47"  > &nbsp;&nbsp;1．录入日期为八位数字，格式为”yyyymmdd“,如20140101。</td>
   </tr>
</table>
   
</table>
</html:form>
<%@ include file="include/footer.jsp"%>

<script language="JavaScript">

//进入页面时将焦点设为减免税类型代码
function fnOnLoad()
{
	
	//选中修改记录
	var selected_tr_id =  document.forms[0].modifyKey_jmslxdm.value;
	if(selected_tr_id != null && selected_tr_id != ""){
		var selected_tr_obj = document.all(selected_tr_id);
		changeStyle(selected_tr_obj);
	}
	
	//显示提示信息
	var actionType = document.forms[0].actionType.value;
	var saveIsSucc = '<bean:write name="zjjmsdmwhForm" property="saveIsSucc" />';
	var message = '<bean:write name="zjjmsdmwhForm" property="message" />';
	
	if(message!=""){
		alert(message);
	}else{
		if(actionType=="Save" || actionType=="Update"){
			if(saveIsSucc == '<%=Constant.CONS_SGLR_SAVEISSUCC_Y%>'){
				alert("保存成功!");
			}
		}else{
			// 页面进入焦点确定
			document.forms[0].query_jmslxdm.focus();
		}
	}
	
	//选中所有下拉
	selectedAll();
	
}

/**
 * 初始化时，选中所有下拉
 */
function  selectedAll(){
	//获取到查询条件中的下拉值
		var szdm = document.forms[0].query_szdm.value;
		var jmslxdldm = document.forms[0].query_jmslxdldm.value;
		var jmslxxldm = document.forms[0].query_jmslxxldm.value;
	
	
	
	//根据减免性质代码大类过滤小类并选中
		document.all("selectedDL").value=jmslxdldm;
		doFilterJmxzxldm_byDLDM(document.all("selectedDL"));
	
	
	//根据下拉值选中下拉列表中的值
		document.all("selectedSzdm").value= szdm;
		document.all("selectedXL").value=jmslxxldm;
}



/**
 * 查看明细 或者 修改信息
 */
	function do2ShowMXorUpdate(jmslxdm){
		if(jmslxdm !=""){
			document.forms[0].modifyKey_jmslxdm.value = jmslxdm;
			doSubmitForm("ShowOne");
			return false;
		}
	}
	
	function doChangeYxbs(jmslxdm){
		if(jmslxdm != ""){
			if(confirm("是否改变该信息的有效状态，请确认！")){
				document.forms[0].modifyKey_jmslxdm.value = jmslxdm;
				document.forms[0].updateType.value = "<%=CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_YXBS%>";
				doSubmitForm("Update");
				return false;
			}else{
				return false;
			}
		}
	}
	

	// 查询条件校验及跳转 
	function doQuery() {
		//检查查询值
		if(!doCheck()){
			return false;
		}
		getSelectedValues();
		document.forms[0].target="";
		document.forms[0].nextPage.value = 1;
		doSubmitForm("Query");
		return false;
	}
	
	function doFanHui(){
		getSelectedValues();
		doSubmitForm("Show");
	}
	
	/**
	*执行检查
	*/
	function doCheck(){
		var lrrqKS = document.all("query_lrrqKS");
		var lrrqJS = document.all("query_lrrqJS");
		
		//校验开始、结束日期是否合法
		if(lrrqKS.value != "" && !isDate(lrrqKS,null)){
			return false;
		}
		
		if(lrrqJS.value !="" && !isDate(lrrqJS,null)){
			return false;
		}
		
		return true;
		
	}
	
	/**
	*将选中行改为选中状态
	*
	*/
	var selectedTR_OBJ;//记录上次被选中的TR 对象
	function changeStyle(trObj){
		if(trObj && selectedTR_OBJ != trObj){
			selectedTR_OBJ =  trObj
			//当前行置成选中状态
			var cNodes = trObj.childNodes;
			for(var index=0;index < cNodes.length; index ++){
				//alert("1--"+cNodes[index].tagName);
				//alert("2--"+cNodes[index].className);
				if(cNodes[index].className == "2-td2-left"){
					cNodes[index].className=cNodes[index].className+"-center1-selected";
				}else{
					cNodes[index].className=cNodes[index].className+"-selected";
				}
				
			}
			
			//其他行置成未选中状态
			var re1 = /-center1-selected/g;
			var re =/-selected/g;
			var tableObj = trObj.parentNode;//获得table对象
			var allOtherTrs = tableObj.childNodes;//获得当前table下的所有行
			//index =1 不对标题行进行操作
			for(var index =1; index < allOtherTrs.length; index ++ ){
				if(trObj != allOtherTrs[index]){
					//获得当前TR 下的 所有 TD
					var tempTDs = allOtherTrs[index].childNodes;
					//遍历每行的TD，并把他们从选中状态置为非选中状态
					for(var cIndex =0; cIndex < tempTDs.length; cIndex++){
						var cTDObj = tempTDs[cIndex];
						// 用 "" 替换 "-center-selected"。
						// 用 "" 替换 "-selected"。
						cTDObj.className = cTDObj.className.replace(re1, "").replace(re, "");   
						//alert(cTDObj.className);
					}
				}
			}
			
		}
	}
	
	/**
	*获得各个查询条件的值
	*/
	function getSelectedValues(){
		
		var szdm = document.all("selectedSzdm").value;
		var jmslxdldm =document.all("selectedDL").value;
		var jmslxxldm =document.all("selectedXL").value;
		
		document.forms[0].query_szdm.value=szdm;
		document.forms[0].query_jmslxdldm.value=jmslxdldm;
		document.forms[0].query_jmslxxldm.value=jmslxxldm;
		
		
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
<script type="text/javascript">
	/**
	*通过减免税性质大类代码过滤小类代码
	*/
	function doFilterJmxzxldm_byDLDM(obj){
		//当前选择大类的值
		var select_dldm= obj.value;
		
		//获得当前行第三列td对象，下标为2
		var jmxzdm_xl_td_Obj = obj.parentNode.parentNode.parentNode.cells[5];
		
		//获得当前行减免性质小类
		var jmxzxlObj = jmxzdm_xl_td_Obj.childNodes[0].childNodes[0];
		//删除当前小类select标签对象
		jmxzdm_xl_td_Obj.childNodes[0].removeChild(jmxzxlObj);
		//按照选择大类重新生成小类下拉菜单
		var valuesArr_jmxz_xl = getJmxz_dl_xl_Arr("jmxz_xl",select_dldm);//query_jmslxxldm
		select_oNewItem = fnAddSelect(jmxzdm_xl_td_Obj.childNodes[0],"selectedXL", valuesArr_jmxz_xl, "");
		//alert(jmxzxlObj.id);
		
		
		
	}
	
	//获得减免性质大类、小类Arr
	function getJmxz_dl_xl_Arr(xzType,jmxzdm_dl) {
		var jmxzArr = new Array();
		
		//构造小类
		if(xzType=="jmxz_xl"){
			
			//增加空项
			if(jmxzdm_dl != ""){
				var blankJmxz_xl = new Array();
				blankJmxz_xl.push("");
				blankJmxz_xl.push("");
				jmxzArr.push(blankJmxz_xl);
			}
			
			
			<logic:iterate id="item" name="zjjmsdmwhForm"  property ="jmxzxlList" indexId="index">
			var jmxzdm_xl_1 = '<bean:write name="item" property="jmxzdm"/>';
			var jmxzmc_xl_1 = '<bean:write name="item" property="jmxzmc"/>';
	
			var oneJmxz_xl = new Array();
			oneJmxz_xl.push(jmxzdm_xl_1);
			oneJmxz_xl.push(jmxzmc_xl_1);
			//减免性质大类不为空，则当前操作是过滤操作，即选择大类，带出对应小类
			if(jmxzdm_dl != ""){
				//alert(jmxzdm_dl+"---"+jmxzdm_xl_1.substr(1,4));
				if(jmxzdm_dl==jmxzdm_xl_1.substr(0,4)){
					jmxzArr.push(oneJmxz_xl);
				}
			}else{
				jmxzArr.push(oneJmxz_xl);
			}
			</logic:iterate>
		}

		return jmxzArr;
	}
	
	//在指定元素下创建一个select下来框
	function fnAddSelect(cellObj, newItemId, valuesArr, value) {

		//创建一个select标签
		var oNewItem = document.createElement("SELECT");
		oNewItem.id = newItemId;
		//if(newItemId == "sglr_select_zssmdm"){
			oNewItem.style.width="300px";
		//}

		for ( var index = 0; index < valuesArr.length; index++) {
			var oValueArr = valuesArr[index];
			var optionValue = oValueArr[0];//option的值
			var optionName = oValueArr[1];//option的名称

			//创建一个option标签
			var optionObj = document.createElement("OPTION");
			optionObj.innerHTML = optionName;
			optionObj.value = optionValue;
			if (value == optionValue) {
				optionObj.selected = true;
			}

			//
			oNewItem.insertAdjacentElement("beforeEnd", optionObj);
		}
		cellObj.insertAdjacentElement("afterBegin", oNewItem);

		return oNewItem;
	}
	
	




</script>
</body>
</html:html>


