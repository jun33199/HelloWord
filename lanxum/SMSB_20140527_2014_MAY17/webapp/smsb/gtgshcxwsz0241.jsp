<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=gb2312" %>

<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>撤销收现缴款书</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/DTable.js"></script>
<script language=JavaScript src="../js/zhsb.js"></script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="fnOnLoad()">
<%@ include file="./include/header.jsp"%>


<html:form action="/webapp/smsb/gtgshCxwszAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="headPzzldm" />
<html:hidden property="headZhdm" />
<html:hidden property="headLrr" />
<html:hidden property="headWszxh" />
<html:hidden property="headWszh" />
<html:hidden property="headNdzb" />
<html:hidden property="headNsrjsjdm" />

<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9' height="300"> 
 <tr>
    <td valign="top" height="300"> <br>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1"  colspan="2">撤销收现缴款书</td>
        </tr>
        <tr>
          <td class="1-td2"  colspan="2"> <br>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td nowrap class="2-td2-t-right">年度字别</td>
                <td nowrap class="2-td2-t-left"><html:text property="tempNdzb" size="10" onKeyDown="if(event.keyCode==13) event.keyCode=9;" /></td>
                <td nowrap class="2-td2-t-right">打印标识</td>
                <td nowrap class="2-td2-t-left"><html:select property="headClbjdm" onKeyDown="if(event.keyCode==13) event.keyCode=9;" ><html:option value="*"> 全部 </html:option><html:option value="0"> 未打印 </html:option><html:option value="1"> 已打印 </html:option></html:select></td>
                <td nowrap class="2-td2-t-left"><div align="right"><input type="image" accesskey="q" tabIndex="-1" onClick="befQuery('Query');return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="查询" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" style="cursor:hand">&nbsp;&nbsp;</div></td>
              </tr>
            </table>
            
            <br>    
              <table id="SzzqwhTable" width="89%"  class="table-99" cellspacing="0">
                <tr>
                  <td width="20%"  class="2-td1-left">收现缴款书号</td>
                  <td width="15%"  class="2-td1-left">金额</td>
                  <td width="15%" class="2-td1-left">纳税人计算机代码</td>
                  <td width="15%"  class="2-td1-left">打印标记</td>
                  <td width="15%" class="2-td1-left">创建时间</td>
                  <td width="5%" class="2-td1-left">&nbsp;</td>
                  <td width="15%" class="2-td1-center">&nbsp;</td>
                </tr>
            <bean:define id="items" name="gtgshCxwszForm" property="dataList"/>
			<%
				double jehj = 0;
				int total = 0;
			%>
            <logic:iterate id="item" name="items" indexId="index">
			<%				
				jehj+=java.lang.Double.parseDouble((String)((java.util.Map)item).get("hjsjje"));
				total+=1;
			%>
              <tr> 
                  <td width="20%"  class="2-td2-left"><ttsoft:write name="item" property="wszh"/></td>
                  <td width="15%"  class="2-td2-left"><ttsoft:write name="item" property="hjsjje"/></td>
                  <td width="15%" class="2-td2-left"><ttsoft:write name="item" property="nsrjsjdm"/></td>
                  <td width="15%"  class="2-td2-left"><ttsoft:write name="item" property="printflag"/></td>
                  <td width="15%" class="2-td2-left"><ttsoft:write name="item" property="cjrq"/></td>
                <td width="5%" class="2-td2-left"><div align="center" ><a href="javascript:befDelete('<ttsoft:write name="item" property="nsrjsjdm"/>','<ttsoft:write name="item" property="wszxh"/>','<ttsoft:write name="item" property="wszh"/>','<ttsoft:write name="item" property="ndzb"/>','<ttsoft:write name="item" property="pzzldm"/>')">撤销</a></div></td>
                <td width="15%" class="2-td2-center"><div align="center" ><a href="javascript:befUpdate('<ttsoft:write name="item" property="nsrjsjdm"/>','<ttsoft:write name="item" property="wszxh"/>','<ttsoft:write name="item" property="wszh"/>','<ttsoft:write name="item" property="ndzb"/>','<ttsoft:write name="item" property="clbjdm"/>','<ttsoft:write name="item" property="pzzldm"/>')">设置打印标记</a></div></td>
              </tr>
              
             </logic:iterate>
			 <%
				java.math.BigDecimal temp = new java.math.BigDecimal(jehj);
				temp = temp.setScale(2, java.math.BigDecimal.ROUND_HALF_UP );

			 %>
				<tr> 
                  <td width="20%"  class="2-td2-left">合计（<%=total%> 张）</td>
                  <td width="15%"  class="2-td2-left"><%=temp%>（元）</td>
                  <td width="15%" class="2-td2-left">&nbsp;</td>
                  <td width="15%"  class="2-td2-left">&nbsp;</td>
                  <td width="15%" class="2-td2-left">&nbsp;</td>
				<td width="5%" class="2-td2-left">&nbsp;</td>
                <td width="15%" class="2-td2-center">&nbsp;</td>
              </tr>
             <table border="0" width="100%">
              <tr> 
                <td width="27%"> </td>
                <!--<td width="8%"><input type="image" accesskey="x" tabIndex="-1" onclick="doSubmitForm('Delete');return false;" style="cursor:hand" onMouseOver="MM_swapImage('sc1','','<%=static_contextpath%>images/cx-x2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="撤销" id="sc1" src="<%=static_contextpath%>images/cx-x1.jpg" width="79" border="0" height="22"></td>-->
                <td width="9%"><input type="image" accesskey="f" tabIndex="-1" onclick="doSubmitForm('Back');return false;" style="cursor:hand"  onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="返回" id="fh1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" border="0"></td>
                <td width="9%"><input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22"></td>
                <td width="27%"> </td>
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
function jsjdmQuery(){
	//if(window.event.keyCode==13){
		//doSubmitForm('Query');
	//}
	 if(event.keyCode==13) event.keyCode=9;
	 //befQuery();
}

function befUpdate(jsjdm,xh,wsz,nd,flag,pzzldm){
	if(flag=="0") {
		document.forms[0].headNsrjsjdm.value=jsjdm;
		document.forms[0].headWszxh.value=xh;
		document.forms[0].headWszh.value=wsz;
		document.forms[0].headNdzb.value=nd;
		document.forms[0].headPzzldm.value=pzzldm;
		if (confirm("该操作将为当前收现缴款书号设置打印标记！\n是否继续？")){
			document.forms[0].actionType.value="Update";
			document.forms[0].submit();
			return false;
		}
	}
	else{
		alert("该收现缴款书已经打印！");		
		//return false;
	}
}

function befDelete(jsjdm,xh,wsz,nd,pzzldm){
	document.forms[0].headNsrjsjdm.value=jsjdm;
	document.forms[0].headWszxh.value=xh;
	document.forms[0].headWszh.value=wsz;
	document.forms[0].headNdzb.value=nd;
	document.forms[0].headPzzldm.value=pzzldm;
	if (confirm("该操作将撤销当前收现缴款书！该操作不能回复！\n是否继续？")){
		document.forms[0].actionType.value="Delete";
		document.forms[0].submit();
		return false;
	}
}

function befQuery(actionType){
	if(!isFullDate1(document.forms[0].tempNdzb.value+"0101",0,"1","")) {
		alert("年度字别不合法！");	
		document.forms[0].tempNdzb.select();
		return false;
	}
	else {
		doSubmitForm(actionType);
		return false;
	}
}

function fnOnLoad(){
    document.forms[0].tempNdzb.focus();
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