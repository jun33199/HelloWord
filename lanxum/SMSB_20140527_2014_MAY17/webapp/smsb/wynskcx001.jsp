<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html>
<head>
<title>无应纳税（费）款申报查询 </title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
 <%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/wynskcxAction.do" method="POST" >
	<html:hidden property="actionType" />
	<html:hidden property="headLrr"/>
	<html:hidden property="headLrrjb"/>
	<html:hidden property="headSwjgzzjgdm"/>
	<html:hidden property="headSwjgzzjgmc"/>
	<html:hidden property="headDqrq"/>
	<html:hidden property="pageSize"/>
	<html:hidden property="nextPage"/>
	<html:hidden property="totalpage"/>
	<html:hidden property="message"/>
	<table width="96%" align="center" cellspacing="0" class="table-99">
		<tr>
			<td class="1-td1"  colspan="7">企业申报查询 </td>
		</tr>
		<tr>
			<td class="1-td2"  colspan="7">
				<br>
				<table cellspacing="0" class="table-99">
					<tr class="black9">
						<td align="left" nowrap>
							<div align="left">当前日期：<bean:write name="wynskcxActionForm" property="headDqrq"/></div>
						</td>
						<td align="right" nowrap>
							<div align="right"><bean:write name="wynskcxActionForm" property="headSwjgzzjgmc"/></div>
						</td>
					</tr>
				</table>
				<br>
				<table  cellspacing="0" class="table-99">
					<tr> 
					  <td width="30%" class="2-td2-t-left">税务所</td>
					  <td width="70%" class="2-td2-t-center">
						<html:select property="querySwjgzzjg"><html:options property="swsList"/></html:select>
					  </td>
					</tr>
					<tr> 
					  <td width="30%" class="2-td2-left">企业状态</td>
					  <td width="70%" class="2-td2-center">
						<html:select property="queryQyzt"><html:options property="qyztList"/></html:select>
					  </td>
					</tr>
					<tr> 
					  <td width="30%" class="2-td2-left">申报类型</td>
					  <td width="70%" class="2-td2-center">
						<html:select property="querySblx"><html:options property="sblxList"/></html:select>
					  </td>
					</tr>
					<tr> 
					  <td width="30%" class="2-td2-left">登记注册类型</td>
					  <td width="70%" class="2-td2-center">
						<table width="60%" cellpadding=0 cellspacing=0><tr>
							<TD width="40%">
							<html:select property="queryDjzclx" multiple="multiple" size="4">
							<html:options property="djzclx"/>
							</html:select>
							</TD>
							<TD ALIGN="CENTER" width="20%">
							<INPUT TYPE="BUTTON" NAME="" VALUE="删除 >>" OnClick="JavaScript:AddItem()">
							<BR>
							<INPUT TYPE="BUTTON" NAME="" VALUE="<< 添加" OnClick="JavaScript:DeleteItem()">
							</TD>
							<TD width="40%">
							<html:select property="queryDjzclx2" multiple="multiple" size="4" >
							<html:options property="djzclx2"/>
							</html:select>

							</TD>
							</tr>
						</table>
						<html:hidden property="alldjzclx"/>
					  </td>
					</tr>
					<tr> 
					  <td width="30%" class="2-td2-left">计算机代码</td>
					  <td width="70%" class="2-td2-center"> <html:text property="queryJsjdm" size="10" maxlength="8"/></td>
					</tr>
					<tr> 
					  <td width="30%" class="2-td2-left">申报日期起</td>
					  <td width="70%" class="2-td2-center"> <html:text property="querySbrqq" size="10" maxlength="8"/></td>
					</tr>
					<tr> 
					  <td width="30%" class="2-td2-left">申报日期止</td>
					  <td width="70%" class="2-td2-center"> <html:text property="querySbrqz" size="10" maxlength="8"/></td>
					</tr>
					<tr> 
					  <td width="30%" class="2-td2-left">零申报查询</td>
					  <td width="70%" class="2-td2-center"><input type="image" accesskey="a"   onClick="doit('QueryA'); return false;"  onMouseOver="MM_swapImage('QueryA','','<%=static_contextpath%>images/lsbcx2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="零申报查询" alt="零申报查询(a)" id="QueryA" src="<%=static_contextpath%>images/lsbcx1.jpg" width="79" height="21">&nbsp;&nbsp;<input type="image" accesskey="s"   onClick="doit('ExportA'); return false;"  onMouseOver="MM_swapImage('ExportA','','<%=static_contextpath%>images/b-dcexcelb2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="零申报导出" alt="零申报导出(s)" id="ExportA" src="<%=static_contextpath%>images/b-dcexcelb1.jpg" width="79" height="21"></td>
					</tr>
					<tr> 
					  <td width="30%" class="2-td2-left">未申报查询</td>
					  <td width="70%" class="2-td2-center"><input type="image" accesskey="d"   onClick="doit('QueryB'); return false;"  onMouseOver="MM_swapImage('QueryB','','<%=static_contextpath%>images/wsbcx2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="未申报查询" alt="未申报查询(d)" id="QueryB" src="<%=static_contextpath%>images/wsbcx1.jpg" width="79" height="21">&nbsp;&nbsp;<input type="image" accesskey="f"   onClick="doit('ExportB'); return false;"  onMouseOver="MM_swapImage('ExportB','','<%=static_contextpath%>images/b-dcexcelb2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="未申报导出" alt="未申报导出(f)" id="ExportB" src="<%=static_contextpath%>images/b-dcexcelb1.jpg" width="79" height="21"></td>
					</tr>
				</table>
				<br>				
				<table width="100%" border="0" cellspacing="0" align="center" >
					<tr>
						<td width="43%" ><hr width="100%" class="hr1" size=1></td>
						<td width="14%" align="left" class="black9" >
							<div align="center"><strong>查询结果列表</strong></div>
						</td>
						<td width="43%" ><hr width="100%" class="hr1" size=1></td>
					</tr>
				</table>
				<br>
				<div align="right">
					(第<bean:write name="wynskcxActionForm" property="nextPage"/>页/共<bean:write name="wynskcxActionForm" property="totalpage"/>页)
					<!--翻页功能开始-->
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="第一页" alt="第一页" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="前页" alt="前页" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="后页" alt="后页" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="最后一页" alt="最后一页" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<!--翻页功能开始-->
				</div>
				<table width="100%" border="0" cellspacing="0" class="table-99" align="center" >
					<tr>
						<td class="2-td1-left" align="center" nowrap>&nbsp;</td>
						<td class="2-td1-left" align="center" nowrap>计算机代码</td>
						<td class="2-td1-left" align="center" nowrap>纳税人名称</td>
						<td class="2-td1-left" align="center" nowrap>企业登记注册类型</td>
						<td class="2-td1-left" align="center" nowrap>企业状态</td>
						<td class="2-td1-left" align="center" nowrap>联系电话</td>
						<td class="2-td1-center" align="center" nowrap>经营地址</td>
					</tr>
					<logic:iterate id="item" name="wynskcxActionForm" property="dataList" >
					<tr>
						<td class="2-td2-left" align="center"><bean:write name='item' property='index'/></td>
						<td class="2-td2-left" align="center"><bean:write name='item' property='jsjdm'/></td>
						<td class="2-td2-left" align="center"><bean:write name='item' property='nsrmc'/></td>
						<td class="2-td2-left" align="center"><bean:write name='item' property='djzclxmc'/></td>
						<td class="2-td2-left" align="center"><bean:write name='item' property='qyztmc'/></td>
						<td class="2-td2-left" align="center"><bean:write name='item' property='lxdh'/></td>
						<td class="2-td2-center" align="center"><bean:write name='item' property='jydz'/></td>
					</tr>
					</logic:iterate>
				</table>
				<br>
				<table width="94%" border="0" cellpadding="0" cellspacing="4">
					<tr valign="bottom">
						<td width="30%"> </td>
                        <td width="40%" align="center">	
							<input type="image" accesskey="c"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="21">
						</td>
                        <td width="30%"></td>
                      </tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
<%@ include file="./include/footer.jsp"%>
    </td>
  </tr>
</table>
</html:form>
<script language="JavaScript">

	function doit(ope){
		if(document.forms[0].querySbrqq.value==""||document.forms[0].querySbrqz.value==""){
			alert("查询条件中申报日期起止时间不能为空！");
			return false;
		}
		if(ope=='ExportA'||ope=='ExportB'){
			if(!window.confirm("确定要导出Excel查询结果文件？")){
				return false;
			}
		}


		//必须有登记注册类型
		if(document.all.queryDjzclx.length <= 1){
			alert("查询条件中的登记注册类型不能为空！");
			return false;
		}

		document.all.alldjzclx.value = "";
		for(var i = 1 ; i < document.all.queryDjzclx.length ; i++){
			document.all.alldjzclx.value = 	document.all.alldjzclx.value + document.all.queryDjzclx.options[i].value;
		}
		doSubmitForm(ope);
	}

	//响应计算机代码的回车查询
	function jsjdmQuery(){
		return false;
	}

	//进入页面时将焦点设为计算机代码录入
	// 页面进入焦点确定
	function fnOnLoad()
	{
		document.forms[0].queryJsjdm.focus();
		if(document.forms[0].message.value!=""){
			alert(document.forms[0].message.value);
		}

		//var tempArry = new Array();
		//for(var i = 0 ; i < document.all.querySblx.options.length; i++){
		//	tempArry[i] = document.all.querySblx.options[i].value;
		//}

		//tempArry.sort();

		//var ln11 = document.all.querySblx.options.length;
		//while(ln11--){
		//	document.all.querySblx.options[ln11] = null;
		//}

		//for(var i = 0 ; i < tempArry.length; i++){
		//	document.all.querySblx.add(new Option(tempArry[i],tempArry[i]));
		//}
	}

	function fn_ChangePage(type)
	{
		//获取上一次操作类型
		temp=document.forms[0].actionType.value;
		//设置当前操作类型
		if(temp=="QueryA"||temp=="ChangePageA"||temp=="ExportA"){
			temp="ChangePageA";
		}else if(temp=="QueryB"||temp=="ChangePageB"||temp=="ExportB"){
			temp="ChangePageB";
		}else{
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
		doit(temp);
		return false;
	}


	//增加选项
	  function AddItem(){
	var ln = document.all.queryDjzclx.length;
	var textArr = new Array(); 
	var valueArr = new Array();
	var textArr1 = new Array(); 
	var valueArr1 = new Array();

    for(var i = 1 ;i < document.all.queryDjzclx.length; i++){
		textArr1[i] = document.all.queryDjzclx.options[i].text; 
		valueArr1[i] = document.all.queryDjzclx.options[i].value;
	}	
	for(var j = 1 ; j < document.all.queryDjzclx2.length; j++){
		textArr1[i+j-1] = document.all.queryDjzclx2.options[j].text; 
		valueArr1[i+j-1] = document.all.queryDjzclx2.options[j].value;
	}


for (var i = 1; i < document.all.queryDjzclx.length; i++) 
{
  textArr[i] = document.all.queryDjzclx.options[i].text; 
  valueArr[i] = document.all.queryDjzclx.options[i].value;
  if(document.all.queryDjzclx.options[i].selected==true){
  	var bool = false;
	for(var j = 1 ; j < document.all.queryDjzclx2.length; j++){
		if(valueArr[i] == document.all.queryDjzclx2.options[j].value){
			bool = true;
		}
	}
	if(!bool){
  		document.all.queryDjzclx2.add(new Option(textArr[i],valueArr[i]));	
	}
  }
}
  
	var ln1 = document.all.queryDjzclx.length;
	while (ln1--)
	{
		if(ln1 > 0){
			document.all.queryDjzclx.options[ln1] = null;		
		}	
	}
	
	for(var i = 1 ; i < textArr1.length ; i++ ){
	    var bool2 = false;
		for(var j = 1 ;j < document.all.queryDjzclx2.length; j++){
			if(document.all.queryDjzclx2.options[j].value == valueArr1[i]){
			    bool2 = true;
			}
		}
		if(!bool2){
			document.all.queryDjzclx.add(new Option(textArr1[i],valueArr1[i]));
		}
	}

	var tempArry = new Array();
	var tempJ = 0 ;
	for(var i = 1 ; i < document.all.queryDjzclx2.length; i++){
		if(document.all.queryDjzclx2.options[i].value != ""){
			if(document.all.queryDjzclx2.options[i].value != null ){
				tempArry[tempJ] = document.all.queryDjzclx2.options[i].value;
				tempJ++;
			}
		}
	}

	tempArry.sort();

	var ln11 = document.all.queryDjzclx2.length;
	while(ln11--){
		if(ln11 > 0){
			document.all.queryDjzclx2.options[ln11] = null;
		}
	}

	for(var i = 0 ; i < tempArry.length; i++){
		document.all.queryDjzclx2.add(new Option(tempArry[i],tempArry[i]));
	}
}
	//减少选项
	  function DeleteItem(){
	var ln = document.all.queryDjzclx2.length;
	var textArr = new Array(); 
	var valueArr = new Array();
	var textArr1 = new Array(); 
	var valueArr1 = new Array();

    for(var i = 1 ;i < document.all.queryDjzclx2.length; i++){
		textArr1[i] = document.all.queryDjzclx2.options[i].text; 
		valueArr1[i] = document.all.queryDjzclx2.options[i].value;
	}	
	for(var j = 1 ; j < document.all.queryDjzclx.length; j++){
		textArr1[i+j-1] = document.all.queryDjzclx.options[j].text; 
		valueArr1[i+j-1] = document.all.queryDjzclx.options[j].value;
	}


for (var i = 1; i < document.all.queryDjzclx2.length; i++) 
{
  textArr[i] = document.all.queryDjzclx2.options[i].text; 
  valueArr[i] = document.all.queryDjzclx2.options[i].value;
  if(document.all.queryDjzclx2.options[i].selected==true){
  	var bool = false;
	for(var j = 1 ; j < document.all.queryDjzclx.length; j++){
		if(valueArr[i] == document.all.queryDjzclx.options[j].value){
			bool = true;
		}
	}
	if(!bool){
  		document.all.queryDjzclx.add(new Option(textArr[i],valueArr[i]));	
	}
  }
}
  
	var ln1 = document.all.queryDjzclx2.length;
	while (ln1--)
	{
		if(ln1 > 0){
			document.all.queryDjzclx2.options[ln1] = null;
		}
	}
	
	for(var i = 1 ; i < textArr1.length ; i++ ){
	    var bool2 = false;
		for(var j = 1 ;j < document.all.queryDjzclx.length; j++){
			if(document.all.queryDjzclx.options[j].value == valueArr1[i]){
			    bool2 = true;
			}
		}
		if(!bool2){
			document.all.queryDjzclx2.add(new Option(textArr1[i],valueArr1[i]));
		}
	}

	var tempArry = new Array();
	var tempJ = 0 ;
	for(var i = 1 ; i < document.all.queryDjzclx.length; i++){
		if(document.all.queryDjzclx.options[i].value != ""){
			if(document.all.queryDjzclx.options[i].value != null){
				tempArry[tempJ] = document.all.queryDjzclx.options[i].value;
				tempJ++;
			}
		}
	}

	tempArry.sort();

	var ln11 = document.all.queryDjzclx.length;
	while(ln11--){
		if(ln11 > 0){
			document.all.queryDjzclx.options[ln11] = null;
		}
	}

	for(var i = 0 ; i < tempArry.length; i++){
		document.all.queryDjzclx.add(new Option(tempArry[i],tempArry[i]));
	}
}

</script>


</body>
</html:html>