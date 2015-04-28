<%@ page contentType="text/html; charset=gb2312" language="java"   errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>

<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>个体工商户营业收入申报查询</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/DTable.js"></script>
<script language=JavaScript src="../js/zhsb.js"></script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="fnOnLoad()">
<%@ include file="./include/header.jsp"%>


<html:form action="/webapp/smsb/gtgshyysrCxAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="tempJsjdm" />
<html:hidden property="tempSbrq" />

<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9' height="300"> 
 <tr>
    <td valign="top" height="300"> <br>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1"  colspan="2">个体工商户营业收入申报查询</td>
        </tr>
        <tr>
          <td class="1-td2"  colspan="2"> <br>
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-99">
              <tr class="black9">
                <td nowrap class="2-td2-t-left" ><div align="right">计算机代码&nbsp;&nbsp;</div></td>
                <td nowrap class="2-td2-t-left"><div align="left"> &nbsp;
                    <html:text property="headJsjdm" size="8" maxlength="8" onKeyDown="if(event.keyCode==13) event.keyCode=9;" />
                  </div></td>
                <td nowrap class="2-td2-t-left" ><div align="center">申报日期&nbsp;&nbsp;</div></td>
                <td nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<html:text property="headSbrq" size="8" maxlength="8" onKeyDown="jsjdmQuery()" onblur="befQuery();return false;"/>(如：200808)</div></td>
               </tr>
            </table>
            <br>
			<table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="30%" nowrap class="2-td1-left"><div align="center" >申报日期</div></td>
                <td width="30%" nowrap class="2-td1-left"><div align="center" >税务登记证号</div></td>
                <td width="10%" nowrap class="2-td1-left"><div align="center" >纳税人名称</div></td>
                <td width="10%" nowrap class="2-td1-left"><div align="center" >修改</div></td>
                <td width="10%" nowrap class="2-td1-left"><div align="center" >删除</div></td>
              </tr>
              
            <bean:define id="items" name="gtgshyysrCxForm" property="dataList"/>
            <logic:iterate id="item" name="items" indexId="index">
              <tr> 
                <td width="35%" nowrap class="2-td2-t-center"><div align="center" ><ttsoft:write name="item" property="sbrq"/></div></td>
                <td width="35%" nowrap class="2-td2-t-center"><div align="center" ><ttsoft:write name="item" property="swdjzh"/></div></td>
                <td width="10%" nowrap class="2-td2-t-center"><div align="center" ><ttsoft:write name="item" property="nsrmc"/></div></td>
                <td width="10%" nowrap class="2-td2-t-center"><div align="center" ><a href="javascript:updateFunc('<ttsoft:write name="item" property="jsjdm"/>','<ttsoft:write name="item" property="sbrq"/>')">修改</a></div></td>
                <td width="10%" nowrap class="2-td2-t-center"><div align="center" ><a href="javascript:del('<ttsoft:write name="item" property="jsjdm"/>','<ttsoft:write name="item" property="sbrq"/>')">删除</a></div></td>
              </tr>
              
             </logic:iterate>
             
            </table>
				<br>
             <table border="0" width="100%">
              <tr> 
                <td width="27%">&nbsp;</td>
                <td width="9%"><input type="image" accesskey="f" tabIndex="-1" onclick="doGoBack('Back');return false;" style="cursor:hand"  onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="返回" id="fh1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" border="0"></td>
                <td width="9%"><input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22"></td>
                <td width="27%">&nbsp;</td>
              </tr>
            </table>
			<br>
          </td>
        </tr>
      </table>
	      
      <br>
    <!-- </td>
 </tr>
</table> -->

</html:form>



<%@ include file="./include/footer.jsp"%>
	</td>
 </tr>
</table>
</body>
<script language=JavaScript >

function del(dm,rq){
	if (dm!="" && rq!=""){
		document.forms[0].tempJsjdm.value=dm;
		document.forms[0].tempSbrq.value=rq;
		if (confirm("该操作将删除页面中一行的数据,且不能自动恢复,请确认")){
			document.forms[0].target="";
			document.forms[0].actionType.value="Delete";
			document.forms[0].submit();
		}
	}
}

function updateFunc(dm,rq){
	if (dm!="" && rq!=""){
		document.forms[0].tempJsjdm.value=dm;
		document.forms[0].tempSbrq.value=rq;
		//if (confirm("该操作将页面中一行的数据,且不能自动恢复,请确认")){
			document.forms[0].target="";
			document.forms[0].actionType.value="Update";
			document.forms[0].submit();
		//}
	}
}

function jsjdmQuery(){
	//if(window.event.keyCode==13){
		//doSubmitForm('Query');
	//}
	 if(event.keyCode==13) event.keyCode=9;
	 //befQuery();
}

function befQuery(){
	if (document.forms[0].headJsjdm.value=="" || document.forms[0].headSbrq.value==""){
		alert("计算机代码和申报日期都必须输入！");
		if (document.forms[0].headJsjdm.value==""){
			document.forms[0].headJsjdm.focus();
		}
		else {
			document.forms[0].headSbrq.focus();
		}
		return false;
	}
	else{
		if(!isFullDate1(document.forms[0].headSbrq.value+"01",0,"1","")) {
			alert("申报日期不合法！");	
			document.forms[0].headSbrq.select();
			return false;
		}
		else {
			doSubmitForm('Query');
			return false;
		}
	}
}

function doGoBack(){
	document.gtgshyysrCxForm.target="";
	doSubmitForm('Back');
}

function fnOnLoad(){
    document.forms[0].headJsjdm.focus();
}

function isFullDate1(strDate,dateKind,empty,isThrow){
	var year,mon,days;
	var err = "";
	if(dateKind==0){//检验8位年月日
		err = "";

		if(strDate.length==0){
			  if(empty!=null){
					err="日期必须不为空!\n";
				}
		}else{
			if(strDate.length!=8){
				err="日期格式不正确，必须是8位数字!\n";
			}else{
				var strYear = strDate.substr(0,4);
				var strMonth = strDate.substr(4,2);
				var strDay = strDate.substr(6,2);
				var objDate = new Date(strMonth+'-'+strDay+'-'+strYear);
				if (isNaN(objDate)){
					err="日期格式不正确!\n"
				}
				if(strYear*1<1900) {
					err="日期格式不正确!\n"
				}
				if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900)) {
					err="日期格式不正确!\n"
				}
				if (strMonth*1 != objDate.getMonth()*1+1){
					err="日期格式不正确!\n"
				}
				if (strDay*1 != objDate.getDate()*1) {
					err="日期格式不正确!\n"
				} //don't call getDay function.
			}
		}
		/*if(err!=""){
			if(isThrow){
				alert(alertStr);
			}
			return false;
		}
		return true;*/
	}else if(dateKind==1){//4位年
		err = "";
		if((strDate.charAt(0)=="0") || strDate.length!=4 || (!isDigit(strDate))){
			err = "不是合法的年份！";
		}
		//alert(err);
	}

	if(err!=""){
		if(isThrow){
			alert(err);
		}
		return false;
	}
	return true;
}

</script>
</html:html>