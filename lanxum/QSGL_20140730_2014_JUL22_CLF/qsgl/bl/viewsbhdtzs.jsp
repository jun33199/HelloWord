<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.ReceivePlslForm"%>
<%@ page import="java.util.ArrayList"%>
<HTML><HEAD><TITLE>显示核定通知书信息</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<script language="javascript" src="<%=static_file%>/js/inputchecker.js"></script>
<script language="javascript" src="../js/qscommon.js"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>



<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
  <jsp:include page="/include/Header.jsp" flush="true">
   <jsp:param name="subtitle" value="补录>申报核定通知书"/>
    <jsp:param name="helpURL" value=""/>
  </jsp:include>

<script language=JavaScript>
function doSubmitForm(operationType)
{
	document.forms[0].operationType.value=operationType;
	document.forms[0].submit();
}
 </script>

<html:form action="/qsbl/viewSbHdtzs.do">
<html:hidden property="operationType"/>



  <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
    <TBODY>
</TBODY>
</TABLE>


<table>
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="12%">申报表号</td>
              <td class="2-td1-center" width="18%">核定通知书号</td>
            </tr>
<logic:iterate id="sbhd" indexId="index" length="length" name="blAllSbWszForm" property="sbhdList"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Hdtzs">
            <tr>
              <td class="2-td2-left">
              &nbsp;<bean:write name="sbhd" property="sbbh"/>
              </td>
              <td class="2-td2-center">
              &nbsp;<bean:write name="sbhd" property="hdtzsh"/>
              </td>
            </tr>
      </logic:iterate>
          </table>
          <br>
	<tr>
  			<td align=center>
            <IMG alt=退出 height=22 id=tuichu name=tuichu
            onclick="doSubmitForm('Return')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
			</td>

	</tr>
      <%@ include file="../include/Bottom.jsp" %>
</table>

</html:form>

</BODY></HTML>


