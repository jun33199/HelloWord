<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html>
<head>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<title>录入印花税购买情况查看</title>
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<style type="text/css">
<!--
@import url(../css/text.css);
-->
</style>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
<%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/yhsgmlrckAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="lrqsrq" />
<html:hidden property="lrjzrq" />
<html:hidden property="lrr" />
<html:hidden property="xspzh" />
<html:hidden property="dbjehj" />
<html:hidden property="ghxz" />
<html:hidden property="ghdwjsjdm" />
<html:hidden property="ghdwmc" />
<html:hidden property="zjhm" />
<html:hidden property="ghrmc" />
<html:hidden property="pgNum" />
<html:hidden property="length" />
<html:hidden property="pgSum" />

<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
  <tr> <!-- InstanceBeginEditable name="02" -->
    <td> <br>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">录入印花税购买情况查看</td>
        </tr>
        <tr>
          <td class="1-td2">&nbsp;&nbsp; <br>

		<table cellspacing=0 class="table-99" onkeydown="if(window.event.keyCode==13) window.event.keyCode=9">
			<tr>
				<td width="20%" class="3-td1-left"><div align="right">录入日期&nbsp;&nbsp;</div></td>
				<td class="3-td1-centen" colspan="3"><div align="left"> &nbsp;&nbsp;
					<ttsoft:write name='yhsgmlrcxForm' property='mxCjrq'/>
					</div>
				</td>
			</tr>
			<tr id="dw" >
				<td class="2-td2-left"><div align="right">购花单位（人）代码&nbsp;&nbsp;</div></td>
				<td class="2-td2-left"><div align="left"> &nbsp;&nbsp;
					<ttsoft:write name='yhsgmlrcxForm' property='mxDwdm'/>
				<td class="2-td2-left"><div align="right">购花单位（人）名称&nbsp;&nbsp;</div></td>
				<td class="2-td2-center"><div align="left">&nbsp;&nbsp;
					<ttsoft:write name='yhsgmlrcxForm' property='mxDwmc'/>
	                <tr>
                  </table>


                          <br>

                            <br>
                <table cellspacing=0 class="table-99" onkeydown="if(window.event.keyCode==13) window.event.keyCode=9">
                        <tr>
                                <td class="2-td1-left" align="center" >
                                        <span  style='font-family:宋体;"Times New Roman"'>序号</span></td>
                                <td valign=top class="2-td1-left">
                                        <span style='font-family:宋体;"Times New Roman"'>税票面值</span></td>
                                <td valign=top class="2-td1-left">
                                <span style='font-family:宋体;"Times New Roman"'>购票数量</span></td>
                                <td valign=top class="2-td1-center">
                                <span style='font-family:宋体;"Times New Roman"'>金额</span></td>
                        </tr>



              <bean:define id="yhslist" name="yhsgmlrcxForm" property="mxDataList"/>
              <% int yhsIndex = 1;%>
              <logic:iterate indexId="index" name="yhslist" id="itemMap">
              <bean:define id="item" name="itemMap"/>
                <tr id="yhsRow">
                   <td nowrap class="2-td2-left"><%=yhsIndex%></td>
                   <td nowrap class="2-td2-left"><ttsoft:write name="item" property="spmzmc"/>&nbsp;</td>
                   <td nowrap class="2-td2-left"><ttsoft:write name="item" property="gpsl"/>&nbsp;</td>
                   <td nowrap class="2-td2-center"><ttsoft:write name="item" property="je"/>&nbsp;</td>
                </tr>
                <%yhsIndex++;%>
              </logic:iterate>




              <tr>
                <td class="2-td2-left"> 合计</td>
                <td class="2-td2-left" colspan="2">&nbsp; </td>
                <td class="2-td2-center"><input type="text" name="hjje" size="12" tabindex="-1" value="<ttsoft:write name='yhsgmlrcxForm' property='mxHjje'/>" readonly="readonly" style="color:#3C5564" class="inputnoborder"></td>
              </tr>
            </table>&nbsp;
		            <table border="0" width="100%">
		              <tr>
		                <td width="50%">&nbsp; </td>
                                <td width="21%"><input name="I3" type="image" accesskey="f" value="返回"  onClick='doSubmitForm("Return");return false;' border=0 onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="返回" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" id="fanhui"> </td>
		                <td width="29%">&nbsp;</td>
		              </tr>
		            </table>
	                </tr>
		</table>




            <br>
            <br>
          </td>
        </tr>
      </table>
      <br>
    </html:form>
    <%@ include file="./include/footer.jsp"%>
    </td>
    <!-- InstanceEndEditable --></tr>
</table>
</body>
<!-- InstanceEnd --></html:html>
<script language="JavaScript">

</script>