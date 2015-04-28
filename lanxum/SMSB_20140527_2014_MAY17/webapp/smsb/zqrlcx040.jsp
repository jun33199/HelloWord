<%@ page contentType="text/html; charset=GBK" %>
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
<title>征期日历查询</title>

<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/list.js">
</script>
<link href="../css/text.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="./include/header.jsp"%>

<html:form action="/webapp/smsb/zqrlcxAction.do" method="POST">
<html:hidden property="actionType" />
<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
  <tr><td class="2-td1-center">输入维护年份&nbsp<html:text property="whnf" size="5" onkeydown="if(window.event.keyCode==13) {doQuerySubmit();return false;}" /></td>
  </tr>
  <tr>
    <td valign="top"> <br>
      <table align="center" cellspacing="0" class="table-99" border="0">
        <tr class="black9">
          <td class="1-td1">征期日历</td>
        </tr>
        <tr>
          <td valign="top" class="1-td2">
            <p>&nbsp; </p>
            <div align="center">
              <table width="100%"  class="table-99" cellspacing="0">
                <tr>
                  <td class="2-td1-left">征期类型代码</td>
                  <td class="2-td1-left">征期类型名称</td>
                  <td class="2-td1-left">税款所属起始日期</td>
                  <td class="2-td1-left">税款所属截止日期</td>
                  <td class="2-td1-left">征期起始日期</td>
                  <td class="2-td1-center">征期截止日期</td>
                </tr>
              <bean:define id="zqlist" name="zqrlcxForm" property="dataList"/>
              <logic:iterate id="items" name="zqlist" indexId="index">
                <bean:define id="item" name="items"/>
                <tr>
                  <td class="2-td2-left"><font size="2"><ttsoft:write name="item" property="zqlxdm"/>&nbsp</font></td>
                  <td class="2-td2-left"><font size="2"><ttsoft:write name="item" property="zqlxmc"/>&nbsp</font></td>
                  <td class="2-td2-left"><font size="2"><ttsoft:write name="item" property="zqssrqq"/>&nbsp</font></td>
                  <td class="2-td2-left"><font size="2"><ttsoft:write name="item" property="zqssrqz"/>&nbsp</font></td>
                  <td class="2-td2-left"><div align="center"><font size="2"><ttsoft:write name="item" property="zqqsrq"/>&nbsp</font></div></td>
                  <td class="2-td2-center"><ttsoft:write name="item" property="zqzzrq"/>&nbsp</td>
                </tr>
              </logic:iterate>
              </table>
            </div>
            <br>
            <div align="center">查询方式：<html:radio value="1" property="selectTypeRadio" onclick="doQuerySubmit()" />指定年份<html:radio value="2" property="selectTypeRadio" onclick="doQuerySubmit()"  />全部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

征期类型：
              <ttsoft:define id="zqlxItems" codekey="ZQWH_ZQLX"/>
              <html:select property="headZqlx">
                <html:option value="*">全部征期类型</html:option>
                <html:options collection="zqlxItems" property="value" labelProperty="label"/>
              </html:select><br>


              &nbsp;&nbsp;&nbsp;
              <input type="image" accesskey="q" src="<%=static_contextpath%>images/cx-q1.jpg" alt="查询" name="Image2" onclick="doQuerySubmit();return false;" border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/cx-q2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/cx-q1.jpg';">
              &nbsp;&nbsp;&nbsp;<input type="image" accesskey="f" src="<%=static_contextpath%>images/fh-f1.jpg" alt="返回" name="Image2" onClick='doSubmitForm("Return");return false;' border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/fh-f2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/fh-f1.jpg';"></a></div>
            <br> <br>
             </td>
        </tr>
      </table>
      <br></td>
    </tr>
</table>
</html:form>
<%@ include file="./include/footer.jsp"%>
<script language="javascript">
//查询提交
function doQuerySubmit()
{
  //维护年份Check
  var strDate =document.all.whnf.value;
  if(isFullDate(strDate,1,"Notempty",true)==false){
    return false;
  }

//  if(doSubmitForm("Query")==false){
//    return false;
//  }
  doSubmitForm("Query");
}
</script>
</html:html>
