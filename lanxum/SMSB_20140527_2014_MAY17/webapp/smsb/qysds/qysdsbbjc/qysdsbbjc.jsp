<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.*" %>

<html>
<head>
<title>核定申报信息</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../../js/treatImage.js"></script>
<script language=JavaScript src="../../../js/compute.js"></script>
<script language=JavaScript src="../../../js/function.js"></script>
<script language=JavaScript src="../../../js/smsb_common.js"></script>
<script language=JavaScript src="../../../js/Bolan.js"></script>
<script language=JavaScript src="../../../js/MathString.js"></script>
<script language=JavaScript src="../../../js/Stack.js"></script>
<script language=JavaScript src="../../../js/reader.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" >
<%@ include file="../../include/header.jsp"%>
<br>
<%
			List mylist = (List) request.getSession(false)
			.getAttribute("qysdsbbjcList");
%>
<html:form action="/webapp/smsb/qysds/qysdsbbjc/qysdsBbjcAction.do" method="post">
	<html:hidden property="isQuery"/>
	<html:hidden property="actionType" />
	<html:hidden property="nsrsbhSubmit" id='nsrsbhSubmit'/>
	<html:hidden property="ssjjlxmcSubmit" id='ssjjlxmcSubmit' />
	<html:hidden property="nsrmcSubmit" id='nsrmcSubmit' />
	<html:hidden property="sshymcSubmit" id='sshymcSubmit' />
	<table width="96%" align="center" cellspacing="0" class="table-99">
		<tr>
			<td class="1-td1" colspan="7">核定申报信息</td>
		</tr>

		<tr>
			<td class="1-td2" colspan="7">
			<TABLE class="table-99" align="center">
				<TR>
					<TD>
					<div id="Layer2" style="position:static;">
					<table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
						<tr>
						    <td class="2-td2-t-left" nowrap>计算机代码</td>
							<td class="2-td2-t-left" nowrap><input type='text'
								name='jsjdm' id='jsjdm'
								value='<ttsoft:write name="qysdsBbjcForm" property="jsjdm"  scope="request" />'
								size='13' tabindex='2' onKeyDown="jsjdmQuery()">
							  <img onclick="doQuery();return false;"onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)" 
							  onMouseOut="MM_swapImgRestore()" value="查询" id="tc1" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" style="cursor:hand"/>
							</td>
						    <td class="2-td2-t-left" nowrap>纳税人识别号</td>
							<td class="2-td2-t-center" nowrap="nowrap">
							<div align="left" id="nsrsbh">&nbsp;&nbsp;<ttsoft:write
								name="qysdsBbjcForm" property="nsrsbh" scope="request" />							
							</div>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>所属经济类型</td>
							<td class="2-td2-left" nowrap>
							    <div align="left" id="ssjjlxmc">&nbsp;&nbsp;<ttsoft:write
								name="qysdsBbjcForm" property="ssjjlxmc" scope="request" />
								</div>
							</td>
							<td class="2-td2-left" nowrap>纳税人名称</td>
							<td class="2-td2-center" nowrap>
							<div align="left" id="nsrmc">&nbsp;&nbsp;<ttsoft:write
								name="qysdsBbjcForm" property="nsrmc" scope="request" />							
							</div>
							</td>
						</tr>
						<tr>
						   <td class="2-td2-left" nowrap>所属行业</td>
							<td class="2-td2-left" nowrap >
							    <div align="left" id="sshymc">&nbsp;&nbsp;<ttsoft:write
								name="qysdsBbjcForm" property="sshymc" scope="request" />
								</div>
							</td>
							<td class="2-td2-left"  nowrap>申报模块</td>
							<td class="2-td2-center" nowrap>
							 <div align="center">
							 <select id="qysdssbfs" name="qysdssbfsdm" onchange="getJd()">
							        <option value=""> 请选择</option>
							 <!-- 加载下拉框 -->
							 <%
							 	for(int i = 0;i<mylist.size();i++){
							 		SbzllxVo vo = (SbzllxVo)mylist.get(i);
							 		String dm = vo.getDm();
							 		String mc = vo.getMc();
							 		
							 %>
                  		    		 <option value="<%=dm %>"><%=mc %></option>
							 <%
							 	}
							  %>
							</select>
                  		    </div>  
                  		    </td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>申报年度</td>
							<td class="2-td2-left" nowrap>
							    <input type='text'
								name='sbnd' id='sbnd'
								value='<ttsoft:write name="qysdsBbjcForm" property="sbnd"  scope="request" />'
								size='13' tabindex='2'  >
							</td>
							<td class="2-td2-left"  nowrap>季度</td>
							<td class="2-td2-center" nowrap>
							 <div align="center">
                  		    	<select id="jd" name="jddm" >
                  		    		  <option value=""> 请选择</option>
                  		    		  <option value="1">第一季度 </option>  
                  		    		  <option value="2">第二季度</option>     
                  		    		  <option value="3">第三季度</option>
                  		    		  <option value="4">第四季度</option>              
                  		    	</select>
                  		     </div>
                  		    </td>
						</tr>
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD><br>
					<div align="center">
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <img onclick="nextStep();return false;"  onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="保存" id="tc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="22" style="cursor:hand"/>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <img onclick="tuichu()"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand"/>
                  </div>
					</TD>
				</TR>
			</TABLE>
			</td>
		</tr>
	</table>
	<br>
	<div align="left">
					<font size="2">&nbsp;&nbsp;&nbsp;填报要求：纳税人年度申报企业所得税时须填报此表。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;填报说明：</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、纳税人名称：填报税务登记证所载纳税人的全称。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、纳税人申报号：</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、联系电话：填写纳税人单位办税人员联系电话（或手机号码）。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、所属经济类型、所属行业：按照税务登记表中的有关内容填写。</font><br>
					</div>
	<br>
	<br>

	<%@ include file="../../include/footer.jsp"%>
</html:form>

</body>
</html>

<script type="text/javascript" language="JavaScript">
<% /*查找*/ %>
function doQuery()
{	//校验点击查询时是否输入了计算机代码
	if(trim(document.forms[0].jsjdm.value) == ""){
		alert("请输入纳税企业计算机代码！");
		document.forms[0].jsjdm.focus();
		return false;
	}
	doSubmitForm('Query');

}

<%/*按回车键查找*/%>
function jsjdmQuery(){
	if(event.keyCode==13){
	 doQuery();
	}
}


function nextStep(){
	//验证申报年度（不能超过目前日期的年度）
	var sbnd=document.forms[0].sbnd.value;
	var date=new Date();
	var year=date.getFullYear();
	document.forms[0].nsrsbhSubmit.value=document.getElementById("nsrsbh").innerText;
	//alert("纳税人识别号"+document.forms[0].nsrsbhSubmit.value);
	document.forms[0].ssjjlxmcSubmit.value=document.getElementById("ssjjlxmc").innerText;
	//alert("所属经济类型名称"+document.forms[0].ssjjlxmcSubmit.value);
	document.forms[0].sshymcSubmit.value=document.getElementById("sshymc").innerText;
	//alert("所属行业名称"+document.forms[0].sshymcSubmit.value);
	document.forms[0].nsrmcSubmit.value=document.getElementById("nsrmc").innerText;
	//alert("纳税人名称"+document.forms[0].nsrmcSubmit.value);
	if(sbnd == ""){
		alert("请输入申报年度！");
		document.forms[0].sbnd.focus();
		return false;
	}else  if(sbnd>year){
	    alert("输入的申报年度超过了当前日期，请重新输入！！！");
	    document.forms[0].sbnd.focus();
	    return false;
	}else if(sbnd<2006){
		alert("输入的申报年度必须是2006年以后(包括2006)！！！");
		document.forms[0].sbnd.focus();
		return false;
	}
	//验证是否选中征收方式，并且为季度时必须选中相应的季度
	var qysdssbfsdm=document.forms[0].qysdssbfsdm.value;
	if(qysdssbfsdm==""){
		alert("申报方式不可为空，请选择相应的申报方式！！！");
		 document.forms[0].qysdssbfsdm.focus();
	     return false;
	 }
	
	 if(qysdssbfsdm=="04"||qysdssbfsdm=="05"||qysdssbfsdm=="06"){
	 	if(document.getElementById('jd').value==""){
	 		alert("请输入相应的季度！！！");
	 		document.forms[0].jddm.focus();
	 		return false;
	 	}
	 	/*  //判断季报的时候，是否可以在该季度内进行申报
	 	if(sbnd==year){
			var jd=document.getElementById('jd').value;
			alert("++++++++++++季度代码"+jd);
			if(jd=="1"){
			  var upTime=sbnd+"0331";
			  alert("截止时间++++"+upTime);
			  if(upTime>date){
			  	alert("第一季度还没完成，等该季度完成后才可以进行申报！！！");
			  	return false;
			  }
			}else if(jd=="2"){
			 var upTime=sbnd+"0630";
			  if(upTime>date){
			  	alert("第二季度还没完成，等该季度完成后才可以进行申报！！！");
			  	return false;
			  }
			}else if(jd=="3"){
			 var upTime=sbnd+"0930";
			  if(upTime>date){
			  	alert("第三季度还没完成，等该季度完成后才可以进行申报！！！");
			  	return false;
			  }
			}else if(jd=="4"){
			 var upTime=sbnd+"1231";
			 alert("截止时间4++++"+upTime);
			  if(upTime>date){
			  	alert("第四季度还没完成，等该季度完成后才可以进行申报！！！");
			  	return false;
			  }
			}
			
		}  */
	 }
	 //校验年度是否为四位的数字
	 if(!isDigit(document.forms[0].sbnd.value)||document.forms[0].sbnd.value.length!=4){
		alert("年度必须为四位数字，请重新输入！");
		document.forms[0].sbnd.focus();
		document.forms[0].sbnd.select();
		return false;
	}
	
	
	doSubmitForm('NextStep');

}
//根据申报方式控制季度
function getJd(){
	var qysdssbfsdm=document.forms[0].qysdssbfsdm.value;
	//alert("申报方式代码！！！"+qysdssbfsdm);
	if(qysdssbfsdm=="01"||(qysdssbfsdm=="02")||(qysdssbfsdm=="03")||(qysdssbfsdm=="07")){
		document.getElementById('jd').disabled=true;
	}else if(qysdssbfsdm=="04"||qysdssbfsdm=="05"||qysdssbfsdm=="06"){
		document.getElementById('jd').disabled=false;
	}  
}

</script>
