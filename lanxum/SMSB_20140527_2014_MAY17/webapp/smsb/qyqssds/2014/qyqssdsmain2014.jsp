<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm"%>
<jsp:useBean
	type="com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm"
	scope="request" id="qyqssdsBaseForm" />

<html>
<head>
<title>企业所得税清算申报</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="../../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../../js/function.js"></script>
<script language="JavaScript" type="text/javascript" src="../../../js/My97DatePicker/WdatePicker.js"></script>
</head>
<%
com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm form = (com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm) request
				.getAttribute("qyqssdsBaseForm");
	//申请类型 0:网上，1：上门
	String sqlx = form.getSqlx();
	//备案审核标识
	String bashbs = form.getBaShztbs();
	//申报审核标识
	String sbshbs = form.getSbShztbs();
	%>
<script language="JavaScript">
	
function doShow(){
	 //保存按钮
	 var baocun = document.getElementById("baocundiv");
	 //全表校验按钮
	 var quanbiaojiaoyan = document.getElementById("quanbiaojiaoyandiv");
	 //接受申请按钮
	 var jieshoushenqing = document.getElementById("jieshoushenqingdiv");
	 //拒绝申请按钮
	 var jujueshenqing = document.getElementById("jujueshenqingdiv");
	 //删除按钮
	 var shanchu = document.getElementById("shanchudiv");
	 //备案审核标识
	 var shbs="<%=form.getBaShztbs()==null ?"0":form.getBaShztbs()%>";
	 //申报审核标识
	 var sbshbs ="<%=form.getSbShztbs()==null?"0":form.getSbShztbs()%>";
	 var sqlx ="<%=form.getSqlx()==null?"2":form.getSqlx()%>";
	 //申报审核状态标识为2的情况下，清算申报结束日期不可编辑
	 if(sbshbs=="2"){
		 var qssbjsrqText=document.getElementById("qssbjsrq");
		 qssbjsrqText.readOnly=true;
	 }
	 if(sqlx=="0"){
			 //网上申请的不需要修改
			
			 if(sbshbs==""){
				 jieshoushenqing.style.display="none";
			 }
			 //审核通过的只需要有作废和返回按钮
			 if(sbshbs=="2" || sbshbs=="3"){
				 baocun.style.display="none";
				 quanbiaojiaoyan.style.display="none";
				 jieshoushenqing.style.display="none";
				 jujueshenqing.style.display="none";
			 }
	 }else if(sqlx=="1"){
		 //上门申报不需要拒绝申请按钮
		 jujueshenqing.style.display="none";
		 if(sbshbs==""){
			 jieshoushenqing.style.display="none";
		 }
		 //审核通过的只需要有作废和返回的按钮
		 if(sbshbs=="2"){
			 //接受申请按钮不可用
			 jieshoushenqing.style.display="none";
			 //审核成功的保存和删除按钮不可用
			 baocun.style.display="none";
			 quanbiaojiaoyan.style.display="none";
			 jujueshenqing.style.display="none";
		 }
	 }
}
	// 检测用户是否输入计算机代码
	function check_jsjdm(){
		if(trim(document.forms[0].jsjdm.value) == "" || document.forms[0].nsrmc.value == ""){
			alert("请输入纳税企业计算机代码！");
			document.forms[0].jsjdm.focus();
			document.forms[0].jsjdm.select();
			return false;
		}
		return true;
	}
	
	//敲回车时移动焦点（计算机代码输入框除外）
	function moveFocus(){
		if(event.keyCode==13) event.keyCode = 9;
	}
	
	//响应计算机代码的回车查询
	function jsjdmQuery(){
		if(event.keyCode==13) doQuery();
	}
	
	//按计算机代码查询
	function doQuery()
	{
		if(trim(document.forms[0].jsjdm.value) == ""){
			alert("请输入纳税企业计算机代码！");
			document.forms[0].jsjdm.focus();
			return false;
		}
		doSubmitForm('Query');
	}
	
	//进行全表校验
	function doCheck(){
		if (!check_jsjdm()){
			return false;
		}
		
		doSubmitForm('Check');
	}
	
	function checkChangeOfQsrq(){
		//效验起始截止日期
		var qssbksrq = document.forms[0].qssbksrq.value;
		var qssbjsrq = document.forms[0].qssbjsrq.value;
		if(qssbksrq == ""){
			alert("请填写清算开始日期！");
			document.forms[0].qssbksrq.focus();
			return false;
		}
		if(qssbjsrq == ""){
			alert("请填写清算结束日期！");
			document.forms[0].qssbjsrq.focus();
			return false;
		}
		//起始日期不能大于截止日期
		if(!checkDate(qssbksrq,qssbjsrq)){
			return false;
		}
		return true;
	}
	function link2Table(tableId){
		
		if(!checkChangeOfQsrq()){
			return false;
		}else{
			document.getElementById("TableID_"+tableId).href=document.getElementById("TableID_"+tableId).href+"&qssbksrq="+document.forms[0].qssbksrq.value+"&qssbjsrq="+document.forms[0].qssbjsrq.value
			+"&sbShztbs="+document.forms[0].sbShztbs.value+"&sqlx="+<%=form.getSqlx()==null?"2":form.getSqlx()%>;
			return true;
		}
	}
	//接受申请
	function doAccept(){
		doSubmitForm("Accept");
	}
	//拒绝申请
	function doRefuse(){
	var sbshbs ="<%=form.getSbShztbs()==null?"0":form.getSbShztbs()%>";
	if(!("1"==sbshbs)){
		alert("该企业的企业清算所得税申报不是提交未审核状态，不能执行审核驳回！");
	}else{
		doSubmitForm("Refuse");
	}		
	}
	//保存
	function doSave(){
		doSubmitForm("Save");
	}
	//作废
	function doDelete(){
	var sbshbs ="<%=form.getSbShztbs()==null?"0":form.getSbShztbs()%>";
	if(""==sbshbs){
		alert("该企业的企业清算所得税申报未提交，不能作废！");
	}else{
		doSubmitForm("Delete");
	}			
	}
	<%/*返回*/%>
	function doBack()
	{
		doSubmitForm("Back");
	}
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="doShow();">
	<%@include file="../../include/header.jsp"%>
	<html:form action="/webapp/smsb/qyqssds/2014/qyqssdsMainAction2014.do"
		method="POST">
		<html:hidden property="actionType" value="Show" />
		<html:hidden property="nsrmc" />
		<html:hidden property="swjgzzjgdm" />
		<html:hidden property="ssjjlx" />
		<html:hidden property="sshy" />
		<html:hidden property="zgswjgzzjgmc" />
		<html:hidden property="linkUrlHTML" />
		<html:hidden property="jydz" />
		<html:hidden property="qsllry" />
		<html:hidden property="lxdh" />
		<html:hidden property="hid_qssbksrq" />
		<html:hidden property="hid_qssbjsrq" />
		<html:hidden property="tbrq" />
		<html:hidden property="qssbksrq" />
		<html:hidden property="sbShztbs" />
		<html:hidden property="jsjdm" />
		<div id="smsb_qysdsnb">
			<table align="center" cellspacing="0" class="table-99"
				onkeydown="moveFocus()">
				<tr>
					<td class="1-td1">企业清算所得税申报</td>
				</tr>
				<tr>
					<td valign="top" class="1-td2"><br>
						<table cellspacing="0" class="table-99">
							<tr class="black9">
								<td align="left" nowrap>
									<div align="left">
										填报日期：
										<bean:write name="qyqssdsBaseForm" property="tbrq"
											scope="request" filter="true" />
									</div></td>
								<td align="center" nowrap>&nbsp;</td>
							</tr>
							<tr class="black9">
								<td align="left" nowrap>
									<div align="left">
										纳税人识别号：&nbsp;
										<bean:write name="qyqssdsBaseForm" property="nsrsbh"
											scope="request" filter="true" />
									</div></td>

								<td align="right" nowrap>
									<div align="right">金额单位：元（列至角分）</div></td>
							</tr>
						</table>

						<table class="table-99" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="15%" nowrap class="2-td2-t-left">计算机代码&nbsp;</td>
								<td width="15%" nowrap class="2-td2-t-left" align=left>							  
								 <!--<html:text								
										property="jsjdm" size="20" maxlength="8" tabindex="4"
										onKeyDown="jsjdmQuery()"  readonly="true"/>-->
									<bean:write name="qyqssdsBaseForm" property="jsjdm"	scope="request" filter="true" />									
								</td>
								<td width="15%" nowrap class="2-td2-t-left">主管税务机关&nbsp;</td>
								<td width="40%" nowrap class="2-td2-t-center" align=left>&nbsp;
									<bean:write name="qyqssdsBaseForm" property="zgswjgzzjgmc"
										scope="request" filter="true" />
								</td>
							</tr>
							<TR>
								<td width="15%" nowrap class="2-td2-left">纳税人名称&nbsp;</td>
								<td width="30%" nowrap class="2-td2-left" align=left>&nbsp;
									<bean:write name="qyqssdsBaseForm" property="nsrmc"
										scope="request" filter="true" />
								</td>
								<td width="10%" nowrap class="2-td2-left">生产经营地址&nbsp;</td>

								<td width="15%" nowrap class="2-td2-center">&nbsp;<bean:write
										name="qyqssdsBaseForm" property="jydz" scope="request"
										filter="true" />
								</td>
							</tr>
							<tr>
								<td nowrap class="2-td2-left">管理人或清算组联络人员&nbsp;</td>
								<td  nowrap class="2-td2-left" align=left>
								&nbsp;<bean:write name="qyqssdsBaseForm" property="qsllry" scope="request"
										filter="true" />
								</td>
								<td nowrap class="2-td2-left">联系电话&nbsp;</td>
								<td  nowrap class="2-td2-center" align=left>
								&nbsp;<bean:write name="qyqssdsBaseForm" property="lxdh" scope="request"
										filter="true" />
								</td>
							</tr>
							<tr>
								<td class="2-td2-left" nowrap>清算起始日期&nbsp;</td>
								<td  class="2-td2-left" nowrap>
									<bean:write name="qyqssdsBaseForm" property="qssbksrq"/>
								</td>
								<td class="2-td2-left" nowrap>清算结束日期&nbsp;</td>
								<td  class="2-td2-center" nowrap>
									<html:text property="qssbjsrq" onClick="WdatePicker()" ></html:text> 
								</td>
							</tr>
							<tr>
								<td  class="2-td2-left" nowrap="nowrap">审核状态提示&nbsp;</td>	
									<td colspan="3" class="2-td2-center" nowrap="nowrap">
										<div align="left" style="color:red;">
											&nbsp;<bean:write name="qyqssdsBaseForm" property="sbShztMessage" scope="request" />
										</div>
								</td>	
							</tr>
						</table> <br>

						<table id="tableQysds">
							<tr>

								<bean:write name="qyqssdsBaseForm" property="linkUrlHTML"
									scope="request" filter="false" />

							</tr>

						</table> <br> <br> <br></td>
				</tr>
				<TR class="black9">
					<TD>
						<div align="center">
							
								<span id="baocundiv" >
										<img onclick="doSave();return false;"
										onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" value="保存" id="baocun"
										src="<%=static_contextpath%>images/bc-s1.jpg" width="79"
										height="22" style="cursor:hand" />&nbsp;&nbsp;&nbsp;&nbsp; 
										</span> 
										<span id="quanbiaojiaoyandiv">
								<input type="image"
								accesskey="q" tabIndex="-1" onClick="doCheck();return false;"
								onMouseOver="MM_swapImage('qbjy','','../../../images/b-qbjy2.jpg',1)"
								onMouseOut="MM_swapImgRestore()" value="全表校验" alt="表间关系校验"
								id="qbjy" src="../../../images/b-qbjy1.jpg" style="cursor:hand">

							&nbsp;&nbsp;&nbsp;&nbsp; </span>
							<span id="jieshoushenqingdiv" >
										<a style="cursor:hand" onClick="doAccept();return false;" onMouseOut="MM_swapImgRestore()"
				                        onMouseOver="MM_swapImage('jishoushenqing','','../../../images/b_shtg2.jpg',1)">
				                        <img src="../../../images/b_shtg1.jpg" name="jishoushenqing" width="79" height="22" border="0" id="jishoushenqing">
				                        </a>
				                        &nbsp;&nbsp;&nbsp;&nbsp;
				                        </span>
				                        <span id="jujueshenqingdiv" >
				                        <a style="cursor:hand" onClick="doRefuse();return false;" onMouseOut="MM_swapImgRestore()"
				                        onMouseOver="MM_swapImage('jujueshenqing','','../../../images/b_shbh2.jpg',1)">
				                        <img src="../../../images/b_shbh1.jpg" name="jujueshenqing" width="79" height="22" border="0" id="jujueshenqing">
				                        </a>
				                        &nbsp;&nbsp;&nbsp;&nbsp;
										</span>
								<!-- 删除按钮做作废按钮使用 -->
										<span id="shanchudiv" >
										<a style="cursor:hand" onClick="doDelete()" onMouseOut="MM_swapImgRestore()"
				                        onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/b-zf2.jpg',1)">
				                        <img src="<%=static_contextpath%>images/b-zf1.jpg" name="shanchu" width="79" height="22" border="0" id="shanchu">
				                        </a>
							 &nbsp;&nbsp;&nbsp;&nbsp;
								 <span >
										<img onclick="doBack()"
										onMouseOver="MM_swapImage('back','','<%=static_contextpath%>images/fanhui2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" value="返回" id="back"
										src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
										height="22" style="cursor:hand" /></span>
						</div></TD>
				</TR>
			</table>
		</div>
		<br>
		<%@ include file="../../include/footernew.jsp"%>
	</html:form>
</body>
</html>
