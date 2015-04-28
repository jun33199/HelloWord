<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm"%>
<jsp:useBean
	type="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm"
	scope="request" id="qysdsNewForm" />

<html>
<head>
<title>企业所得税年度纳税申报</title>
<!--modify 20141011  -->
<html:base/>
<!-- end -->
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/function.js"></script>
</head>
<script language="JavaScript">
	
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
	
	// 检查税款所属日期在查询之后是否被修改过
	function checkChangeOfSkssrq(){
		ksrq=document.forms[0].skssksrq.value;
		jsrq=document.forms[0].skssjsrq.value;
		h_ksrq=document.forms[0].hid_skssksrq.value;
		h_jsrq=document.forms[0].hid_skssjsrq.value;
		
		if(h_ksrq==""||h_jsrq==""){
			return true;
		}
		if(h_ksrq!=ksrq||h_jsrq!=jsrq){
			if(confirm("您修改了税款所属期，请按照新的税款所属期执行查询。或者重置税款所属期为最后一次成功查询时的税款所属期，要重置吗？")){
					document.forms[0].skssksrq.value=h_ksrq;
					document.forms[0].skssjsrq.value=h_jsrq;
			}
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
	
		if(!isDate(document.forms[0].sbrq, "true")){
			document.forms[0].sbrq.focus();
			return false;
		}
		
		if(!compareDate(document.forms[0].skssjsrq)){
			return false;
		}
		
		doSubmitForm('Query');
	}
	
	//进行全表校验
	function doCheck(){
		if (!check_jsjdm()){
			return false;
		}
		
		if(!checkChangeOfSkssrq()){
			return false;
		}
		doSubmitForm('Check');
	}
	
	<%/*判断比较税款所属日期*/%>
	function compareDate(obj){
		
		if(!isDate(obj, "true")){
			return false;
		}
		
		var strDate1 = document.forms[0].skssksrq.value;		
		var strYear1 = strDate1.substr(0,4);		
		var strMonth1 = strDate1.substr(4,2);
 	 	var strDay1 = strDate1.substr(6,2);
 	 	
 	 	var strDate2 = document.forms[0].skssjsrq.value;
 	 	var strYear2 = strDate2.substr(0,4);		
		var strMonth2 = strDate2.substr(4,2);
 	 	var strDay2 = strDate2.substr(6,2);
 	 	
 	 	var sksbrq = document.forms[0].sbrq.value;
 	 	var strYear3 = sksbrq.substr(0,4);		
		var strMonth3 = sksbrq.substr(4,2);
 	 	var strDay3 = sksbrq.substr(6,2); 
 	 	
	  	var objDate1 = new Date(strMonth1+'-'+strDay1+'-'+strYear1);
	  	var objDate2 = new Date(strMonth2+'-'+strDay2+'-'+strYear2);
	  	var objDate3 = new Date(strMonth3+'-'+strDay3+'-'+strYear3);
	  	
	  	if(objDate2>objDate3){
	  		alert("税款结束时间不能大于申报日期");
	  		window.event.returnValue=false;
			document.forms[0].skssjsrq.focus();
			document.forms[0].skssjsrq.select();
			return false;
	  	}
  	  	
  		if(objDate1>=objDate2){
			if(obj == document.forms[0].skssjsrq){
				alert("税款结束时间不能小于或等于税款开始时间");
			}else{
				alert("税款开始时间不能大于或等于税款结束时间");	
			}
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;
		}
		
		if(strYear1!=strYear2){
			alert("税款开始时间与税款结束时间的年份不同，税款不能跨年！");
			return false;				
		}
		
		if(parseInt(strYear1)<2008){
			alert("您正在使用的企业所得税申报资料录入系统为2008版，该版本支持的最小税款年度为2008年\n2007（含2007）以前的税款年度请使用旧版本系统！");
			return false;	
		}
		
		return true;
	}
	
	//判断比较税款所属日期
	function compareDate2Save(obj){
		
		var strDate1 = document.forms[0].skssksrq.value;		
		var strYear1 = strDate1.substr(0,4);		
		var strMonth1 = strDate1.substr(4,2);
 	 	var strDay1 = strDate1.substr(6,2);
 	 	
 	 	var strDate2 = document.forms[0].skssjsrq.value;
 	 	var strYear2 = strDate2.substr(0,4);		
		var strMonth2 = strDate2.substr(4,2);
 	 	var strDay2 = strDate2.substr(6,2);
 	 	
 	 	var sksbrq = document.forms[0].sbrq.value;
 	 	var strYear3 = sksbrq.substr(0,4);		
		var strMonth3 = sksbrq.substr(4,2);
 	 	var strDay3 = sksbrq.substr(6,2); 
 	 	
	  	var objDate1 = new Date(strMonth1+'-'+strDay1+'-'+strYear1);
	  	var objDate2 = new Date(strMonth2+'-'+strDay2+'-'+strYear2);
	  	var objDate3 = new Date(strMonth3+'-'+strDay3+'-'+strYear3);
	  	
	  	if(objDate2>objDate3){
	  		alert("税款结束时间不能大于申报日期");
	  		window.event.returnValue=false;
				document.forms[0].skssjsrq.focus();
				document.forms[0].skssjsrq.select();
				return false;
	  	}
  	  	
  		if(objDate1>=objDate2){
			if(obj == document.forms[0].skssjsrq){
				alert("税款结束时间不能小于或等于税款开始时间");
			}else{
				alert("税款开始时间不能大于或等于税款结束时间");	
			}
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;
		}
		
		if(strYear1!=strYear2){
			alert("税款开始时间与税款结束时间的年份不同，税款不能跨年！");
			return false;				
		}
		
		return true;
			
	}
	
	function link2Table(tableId){
		
		if(!checkChangeOfSkssrq()){
			document.getElementById("TableID_"+tableId).href="#";
			return false;
		}
		document.getElementById("TableID_"+tableId).href=document.getElementById("TableID_"+tableId).href+"&skksrq="+document.forms[0].skssksrq.value+"&skjsrq="+document.forms[0].skssjsrq.value;
	}
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">
<%@include file="../../include/header.jsp"%>
<html:form action="/webapp/smsb/qysds/2008/qysdsMainAction2008.do" method="POST">
	<html:hidden property="actionType" value="Show" />
	<html:hidden property="nsrmc" />
	<html:hidden property="sknd" />
	<html:hidden property="swjgzzjgdm" />
	<html:hidden property="ssjjlx" />
	<html:hidden property="sshy" />
	<html:hidden property="ckzd" />
	<html:hidden property="gzglxs" />
	<html:hidden property="jmlx" />
	<html:hidden property="zgswjgzzjgmc" />
	<html:hidden property="linkUrlHTML" />
	<html:hidden property="jydz" />
	<html:hidden property="lxdh" />
	<html:hidden property="hid_skssksrq" />
	<html:hidden property="hid_skssjsrq" />


	<div id="smsb_qysdsnb">
	<table align="center" cellspacing="0" class="table-99"
		onkeydown="moveFocus()">
		<tr>
			<td class="1-td1">企业所得税年度纳税申报</td>
		</tr>
		<tr>
			<td valign="top" class="1-td2"><br>
			<table cellspacing="0" class="table-99">
				<tr class="black9">
					<td align="left" nowrap>
					<div align="left">申报日期： <html:text size="8" maxLength="8"
						property="sbrq" tabIndex="1"
						onchange="getStartEndDate(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,1)" />
					</div>
					</td>
					<td align="center" nowrap>
					<div align="left">税款所属日期： <html:text property="skssksrq" size="11"
						maxlength="8" tabIndex="2"
						onchange="compareDate(this)" />至
					<html:text property="skssjsrq" size="11" maxlength="8" tabIndex="3"
						onchange="compareDate(this)" />
					</div>
					</td>
				</tr>
				<tr class="black9">
					<td align="left" nowrap>
					<div align="left">纳税人识别号：&nbsp; <bean:write name="qysdsNewForm"
						property="nsrsbh" scope="request" filter="true" /></div>
					</td>

					<td align="right" nowrap>
					<div align="right">金额单位：元（列至角分）</div>
					</td>
				</tr>
			</table>

			<table class="table-99" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="15%" nowrap class="2-td2-t-left">计算机代码&nbsp;</td>
					<td width="15%" nowrap class="2-td2-t-left" align=left><html:text
						property="jsjdm" size="8" maxlength="8" tabindex="4" onKeyDown="jsjdmQuery()" /></td>
					<td width="15%" nowrap class="2-td2-t-left">主管税务机关&nbsp;</td>
					<td width="40%" nowrap class="2-td2-t-center" align=left>&nbsp; <bean:write
						name="qysdsNewForm" property="zgswjgzzjgmc" scope="request"
						filter="true" /></td>
				</tr>
				<TR>
					<td width="15%" nowrap class="2-td2-left">纳税人名称&nbsp;</td>
					<td width="30%" nowrap class="2-td2-left" align=left>&nbsp; <bean:write
						name="qysdsNewForm" property="nsrmc" scope="request" filter="true" /></td>
					<td width="10%" nowrap class="2-td2-left">联系电话&nbsp;</td>

					<td width="15%" nowrap class="2-td2-center">&nbsp;<bean:write
						name="qysdsNewForm" property="lxdh" scope="request" filter="true" /></td>
				</tr>
				<tr>
					<td nowrap class="2-td2-left">生产经营地址&nbsp;</td>
					<td colspan=3 nowrap class="2-td2-align-left" align=left>&nbsp;<bean:write
						name="qysdsNewForm" property="jydz" scope="request" filter="true" />
					</td>
				</tr>
			</table>

			<br>

			<table id="tableQysds">
				<tr>

					<bean:write name="qysdsNewForm" property="linkUrlHTML"
						scope="request" filter="false" />

				</tr>

			</table>
			<br>
			<br>
			<br>
			</td>
		</tr>
		<TR class="black9">
			<TD>
			<div align="center"><input type="image" accesskey="q"
				onClick="doQuery();return false;"
				onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)"
				onMouseOut="MM_swapImgRestore()" value="查询"
				src="<%=static_contextpath%>images/cx-q1.jpg " id="chaxun"
				style="cursor:hand"> &nbsp;&nbsp; <input type="image"
				accesskey="q" tabIndex="-1" onClick="doCheck();return false;"
				onMouseOver="MM_swapImage('qbjy','','../../../images/b-qbjy2.jpg',1)"
				onMouseOut="MM_swapImgRestore()" value="全表校验" alt="表间关系校验" id="qbjy"
				src="../../../images/b-qbjy1.jpg" style="cursor:hand">&nbsp;&nbsp; <input
				type="image" accesskey="c" tabIndex="-1"
				onClick="tuichu();return false;"
				onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
				onMouseOut="MM_swapImgRestore()" value="退出" id="tc1"
				src="<%=static_contextpath%>images/tc-c1.jpg" style="cursor:hand"></div>
			</TD>
		</TR>
	</table>
	</div>
	<br>
	<%@ include file="../../include/footernew.jsp"%>
</html:form>
</body>
</html>
