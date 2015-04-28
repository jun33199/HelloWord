<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.HdtzsForm"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>

<HTML>
<HEAD>
<TITLE>录入修改核定通知书防伪号码</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js"
	type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
	type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js"
	type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js"
	type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>

<%
HdtzsForm form = (HdtzsForm) session.getAttribute("hdtzsForm");
%>

</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){

  document.all.operationType.value=operationType;
   if(operationType=="Update" ){
          if (!checkFwhm(document.forms[0].fwhm.value))
			   {
			   	  alert("防伪号码长度必须是11位数字！");
			      document.forms[0].fwhm.focus();
			      return false;
			   }
        }

  document.forms[0].operationType.value=operationType;


  document.forms[0].submit();
}

/**
 * 防伪号码必须是11位数字
 **/
function checkFwhm(fwhm)
{
    var regExp = /^[\d]{11}$/g;
    if(regExp.exec(fwhm) == null)
    {
        return false;
    }
    return true;    
}

</SCRIPT>


<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0
	marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
	<jsp:param name="subtitle" value="契税申报&gt;录入修改核定通知书防伪号码" />
	<jsp:param name="helpURL" value="" />
</jsp:include>

<SCRIPT language=javascript>
<!--



//-->
</SCRIPT>
<br>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%"
	width=770>
	<TBODY>
		<TR>
			<TD vAlign=top>
			<TABLE align=center cellSpacing=0 class=table-99>
				<TBODY>
					<TR>
						<TD class=1-td1>录入修改核定通知书防伪号码</TD>
					</TR>
					<TR>
						<TD class=1-td2 vAlign=top><html:form
							action="/qssb/modHdtzsfwhm.do">
							<input type="hidden" name="operationType" value="">

							<br>

							<TABLE border="0" cellSpacing=0 class=table-89>
								<TBODY>
									<tr>
										<td class="2-td2-t-left">申报表号</td>
										<td class="2-td2-t-center" colspan="5">
										<div align=left>&nbsp;<bean:write name="hdtzsForm"
											property="sbbh" /></div>
										</td>
									</tr>
									<tr>
										<td class="2-td2-left">核定通知书号</td>
										<td class="2-td2-t-center" colspan="5">
										<div align=left>&nbsp;<bean:write name="hdtzsForm"
											property="hdtzsh" /></div>
										</td>
									</tr>
									<tr>
										<td class="2-td2-left">防伪号码</td>
										<td class="2-td2-t-center" colspan="5">
										<div align=left>
										
										<% if(form.getFwhm()==null || "".equals(form.getFwhm())){ %>
										
										<html:text property="fwhm" maxlength="11" value='' />
										
										
											<%} else{%>
											
											<html:text property="fwhm" maxlength="11"
											value='<%=form.getFwhm()%>' />
											
											
											<%} %>
											
											</div>
										</td>
									</tr>
							</TABLE>
							<BR>

							<DIV align=center><IMG name="query"
								onMouseOver="MM_swapImage('baocun1','','<%=static_file%>images/baocun2.jpg',1)"
								onMouseOut="MM_swapImgRestore()"
								src="<%=static_file%>images/baocun1.jpg"
								onclick="doSubmitForm('Update');" width="79" height="22"
								id="chaxun1" alt="保存" style="cursor:hand">
								
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							
							<IMG src="<%=static_file%>images/tc-c1.jpg"
								onmouseover="this.src='<%=static_file%>images/tc-c2.jpg'"
								onmouseout="this.src='<%=static_file%>images/tc-c1.jpg'"
								alt="关闭" onclick="window.close();return false;"
								style="cursor:hand"></DIV>
							<BR>




						</html:form> <%@ include file="../include/Bottom.jsp"%>
</BODY>
</HTML>
