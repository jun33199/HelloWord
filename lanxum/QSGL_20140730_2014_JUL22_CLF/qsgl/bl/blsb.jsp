<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>

<HTML><HEAD><TITLE>补录</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>

<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<script language="javascript" src="../js/qscommon.js"></script>

<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
<META content="MSHTML 5.00.2919.6307" name=GENERATOR></HEAD>

<SCRIPT language=JavaScript>
function checkSubmit(operationType)
{

if(operationType=="Save")
	{
		if(document.forms[0].sbbh.value=="")
		{
			alert("请输入申报表号")
			document.forms[0].sbbh.focus();
			return false;
		}
		if(document.forms[0].sbbh.value.length!=19)
		{
			alert("申报表号长度应为19位")
			document.forms[0].sbbh.focus();
			return false;
		}

	}
	document.forms[0].operationType.value=operationType;

	document.forms[0].submit();
}


//改变个人和非个人的情况
function checkSelectBl()
{
	if(document.forms[0].bllx[1].checked)
	{
		document.all.operationType.value="ChangeBllx";
		document.forms[0].submit();
	}
    
}

</SCRIPT>
<!----本页面的头文件和本页帮助的路径----->

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="补录>补录业务关联"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>
<SCRIPT language=javascript>

</SCRIPT>
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>

         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>补录业务关联</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
          <html:form action="/qsbl/blSb.do">
              <html:hidden property="operationType"/>


            <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
			  <BR>
          <TR>
            <TD class=2-td2-t-left width="12%">补录类型</TD>
            <TD class=2-td2-t-center width="43%" colspan=3>
			<DIV align=left>
            <html:radio property="bllx" value="0" onclick="checkSelectBl()"/>非汇总
            <html:radio property="bllx" value="1" onclick="checkSelectBl()"/>汇总
             </DIV>
			 </TD>
          </TR>

			  <TR>
			  	<TD class=2-td2-left width="20%">缴款书号</TD>
			  	<html:hidden property="jksh"/>
			  	<TD class=2-td2-left width="30%">
				<DIV align=left>&nbsp;
				<bean:write name="blAllSbWszForm" property="jksh" />
				</DIV>
				</TD>
				<TD class=2-td2-left width="20%">申报表号</TD>
				<TD class=2-td2-center width="30%">
				<DIV align=left><html:text  property="sbbh" size="20" maxlength="19"/></DIV></TD>
			 </TR>
			</TBODY>
			</TABLE>

<BR>
            <DIV align=center>

            <IMG alt=保存 height=22 id=baocun name=Submit63
            onclick="checkSubmit('Save')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
            src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79>

            <IMG alt=返回 height=22 id=fanhui name=fanhui123
            onclick="checkSubmit('Return');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('fanhui','','<%=static_file%>images/fanhui2.jpg',1)"
            src="<%=static_file%>images/fanhui1.jpg" style="CURSOR: hand" width=79>
            </DIV><BR>


<%@ include file="../include/Bottom.jsp" %>
	</TBODY>	
	</TABLE>

     </html:form>

</BODY></HTML>

