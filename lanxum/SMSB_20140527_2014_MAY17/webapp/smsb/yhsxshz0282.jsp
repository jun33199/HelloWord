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
<title>汇总代售单位销售情况</title>
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
<html:form action="/webapp/smsb/yhsxshzAction.do" method="POST">
<input type=hidden value="Show" name="actionType">
<html:hidden property="dsjsjdm" />
<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">汇总代售单位销售情况</td>
        </tr>
        <tr>
          <td class="1-td2">&nbsp;&nbsp; <br>
            <table cellspacing=0 class="table-99">
              <tr class="black9">
                <td colspan="2"><div align="right">金额单位：人民币元&nbsp;&nbsp;</div></td>
              </tr>
            </table>
            <br>
            <table cellspacing=0 class="table-99">
              <tr>
                <td class="3-td1-left"  width="25%">代售单位名称</td>
                <td class="3-td1-centen"><html:text property="dsdwmc" styleClass="inputnoborder" style="color:#3C5564" readonly="true" size="60" tabindex="-1" /></td>
              </tr>
							<tr>
                <td class="2-td2-left">汇总缴款书号</td>
                <td class="2-td2-center"><html:text property="jkpzh" styleClass="inputnoborder" style="color:#3C5564" readonly="true" size="18" tabindex="-1" /></td>
              </tr>
              <tr>
                <td class="2-td2-left">汇总税款</td>
                <td class="2-td2-center"><html:text property="sjse" styleClass="inputnoborder" style="color:#3C5564" readonly="true" size="18" tabindex="-1" /></td>
              </tr>
            </table>
						<br>
            <table width="150" border="0" cellpadding="0" cellspacing="0" class="table-99">
              <tr>
                <td>
									<div align="center">
										<input type="image" accesskey="g" src="<%=static_contextpath%>images/cxhz-g1.jpg" alt="撤消汇总" id="cxhz" onMouseOver="MM_swapImage('cxhz','','<%=static_contextpath%>images/cxhz-g2.jpg',1)" onMouseOut="MM_swapImage('cxhz','','<%=static_contextpath%>images/cxhz-g1.jpg',1)" onclick="document.forms[0].target='_self';doSubmitForm('Cxjks');return false;" style="cursor:hand">
										&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="image" accesskey="p" src="<%=static_contextpath%>images/dy-p1.jpg" alt="打印" id="dayin" onMouseOver="MM_swapImage('dayin','','<%=static_contextpath%>images/dy-p2.jpg',1)" onMouseOut="MM_swapImage('dayin','','<%=static_contextpath%>images/dy-p1.jpg',1)" onclick="printFunc();return false;" style="cursor:hand">
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="image" accesskey="f" src="<%=static_contextpath%>images/fh-f1.jpg" alt="返回" id="fanhui" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImage('fanhui','','<%=static_contextpath%>images/fh-f1.jpg',1)" onclick="document.forms[0].target='_self';doSubmitForm('Turnback');return false;" style="cursor:hand">
                  </div>
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
function printFunc(){
  document.forms[0].target="blank";
  doSubmitForm('Print');
}
</script>
</html:html>

