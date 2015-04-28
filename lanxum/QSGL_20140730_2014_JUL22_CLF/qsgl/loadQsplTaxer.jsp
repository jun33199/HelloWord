<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<HTML><HEAD><TITLE>契税批量处理客户端下载</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>

function doSubmitForm(operationType)
{

    document.all.operationType.value = operationType;
	document.forms[0].submit();

}

</script>

<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="契税批量处理客户端下载"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>

  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>

        <TR>
          <TD class=1-td1><strong>契税批量处理客户端下载</strong></TD></TR>


      <TR>
        <td class=1-td2 vAlign=top>

            <br>
            <DIV align=center>
            <html:form action="/plsl/plslQuery.do">

             <br>
<a href="<%=static_file%>download/BJTAX_QSPL_SOFTWARE(TAXER).exe" >下载客户端</a>
             <br>
             <br>
            
            <html:hidden property="operationType"/>
        <input type="hidden" name="jdid" value="<%=request.getParameter("jdid")%>">            
          <input type="hidden" name="menu_url" value="<%=request.getParameter("menu_url")%>">          
               <IMG
                name="back"
                onclick="doSubmitForm('Return');"
                onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"
                src="<%=static_file%>images/tuichu1.jpg"
                width="79"
                height="22"
                id="tuichu1"
                alt="退出"
                style="cursor:hand">
        
</html:form>
</DIV>
</td>
</tr>
        </table>

            </td>
          </tr>
        </table>

<%@ include file="include/Bottom.jsp" %>
</BODY></HTML>
        
        