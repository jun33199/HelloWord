<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>撤销印花税购买情况汇总</title>
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/function.js"></script>
<style type="text/css">
<!--
@import url(../css/text.css);
-->
</style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="./include/header.jsp"%>
<%
    java.util.List peopleList = com.ttsoft.bjtax.smsb.yhsgl.YhsglHelper.getPeopleSelect(userData.getSsdwdm());
    pageContext.setAttribute("PeopleList",peopleList);
%>
<html:form action="/webapp/smsb/yhsgmhzcxAction.do" method="POST">
<input type=hidden value="Show" name="actionType">
<html:hidden property="dsjsjdm" />
<html:hidden property="cxsbbh" />
<html:hidden property="cxjkpzh" />

<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">撤销印花税购买情况汇总</td>
        </tr>
        <tr>
          <td class="1-td2">&nbsp;&nbsp; <br>
            <table cellspacing=0 class="table-99">
              <tr class="black9">
                <td>
                  <div align="right">选择撤销对象：&nbsp;&nbsp;</div>
                </td>
                <td>&nbsp;
                  <html:radio property="cxhzdx" value="0" onclick="init()" />征收机关
                  <html:radio property="cxhzdx" value="1" onclick="init()" />印花税销售人员
                  <%--<html:text property="yhsxsry" size="15" styleClass="txtInput" readonly="true"/>--%>
                  <html:select property="yhsxsry">
                      <html:options collection="PeopleList" property="value" labelProperty="label"/>
                  </html:select>
                  <input type="image" accesskey="q" src="<%=static_contextpath%>images/cx-q1.jpg" alt="查询" id="cxhz" onMouseOver="MM_swapImage('cxhz','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImage('cxhz','','<%=static_contextpath%>images/cx-q1.jpg',1)" onclick="befQuery();return false;" style="cursor:hand;vertical-align:text-bottom">
                </td>
                <td><div align="right">金额单位：人民币元&nbsp;&nbsp;</div></td>
              </tr>
            </table>
            <br>
            <table width="100%" cellspacing=0 class="table-99">
              <tr>
                <td valign=top nowrap class="2-td1-left">序号</td>
                <td valign=top nowrap class="2-td1-left">申报表号</td>
                <td valign=top nowrap class="2-td1-left">实缴金额</td>
                <td valign=top nowrap class="2-td1-center">&nbsp;</td>
              </tr>
              <bean:define id="jkslist" name="yhsgmhzcxForm" property="dataList"/>
              <% int jksIndex = 1;%>
              <logic:iterate indexId="index" name="jkslist" id="itemMap">
                <bean:define id="item" name="itemMap"/>
                <tr id="jksRow">
                   <td nowrap class="2-td2-left"><%=jksIndex%></td>
                   <td nowrap class="2-td2-left">
                     <input type="hidden" name="sbbh" value='<ttsoft:write name="item" property="sbbh"/>'>
                     <a href="javascript:printFunc('<ttsoft:write name="item" property="jkpzh"/>','<ttsoft:write name="item" property="sbbh"/>')"><ttsoft:write name="item" property="sbbh"/>
                   </td>
                   <td nowrap class="2-td2-left"><input type="text" name="sjje" value='<ttsoft:write name="item" property="sjje"/>' class="inputnoborder" style="color:#3C5564" readonly size="10"></td>
                   <td nowrap class="2-td2-center"><a href="#" onclick="doCsjks('<%=jksIndex++%>');return false;"/>撤销</a></td>
                </tr>
              </logic:iterate>
          </table>
          <br>
            <table>
              <tr>
                <td align="center">
                <input type="image" accesskey="f" src="<%=static_contextpath%>images/fh-f1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fh-f2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fh-f1.jpg'" alt="返回" onclick="befBack();return false;" style="cursor:hand">
		</td>
              </tr>
            </table>
            <br>
          </td>
        </tr>
      </table>
      <%@ include file="./include/footer.jsp"%>
    </td>
  </tr>
</table>
<br>
</html:form>

</body>
<script language="JavaScript">
//init
function init(){
  if(document.all.cxhzdx[0].checked==true){
    document.all.yhsxsry.disabled=true;
  }
  else {
    document.all.yhsxsry.disabled=false;
  }
}
init();

function doCsjks(lineNo) {
  var jksrow=document.all("jksRow");
  if(jksrow.length) {
    jksrow = jksrow[lineNo-1];
  }
  document.all.cxsbbh.value=jksrow.all('sbbh').value;
  if(window.confirm("即将撤销缴款书 "+document.all.cxsbbh.value+" ，请确认")){
  	document.forms[0].target="";
    doSubmitForm("Cxjks");
  }
}

function befQuery(){
	document.forms[0].target="";
	doSubmitForm('Query');
	return false;
}

function befBack(){
	document.forms[0].target="";
	doSubmitForm('Turnback')
	return false;
}

function printFunc(pzh,sbbh){
	document.all.cxjkpzh.value=pzh;
	document.all.cxsbbh.value=sbbh;
	document.forms[0].target="blank";
	doSubmitForm('Print');
}

</script>
</html:html>
