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
	document.forms[0].operationType.value=operationType;
	document.forms[0].submit();
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
          <html:form action="/qsbl/viewblSbWsz.do">
              <html:hidden property="operationType"/>


            <TABLE  id="JmsTable" border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
			  <BR>
          <TR>
            <TD class=2-td2-t-left >补录类型</TD>
            <TD class=2-td2-t-center colspan=3>
			<DIV align=left>
<logic:equal name="blAllSbWszForm" property="bllx" value="0">
            &nbsp;&nbsp;非汇总
</logic:equal>
<logic:equal name="blAllSbWszForm" property="bllx" value="1">
            &nbsp;&nbsp;汇总
</logic:equal>
             </DIV>
			 </TD>
          </TR>

			  <TR>
			  	<TD class=2-td2-left >缴款书号</TD>
			  	<html:hidden property="jksh"/>
			  	<TD class=2-td2-center colspan="3">
				<DIV align=left>&nbsp;
				<bean:write name="blAllSbWszForm" property="jksh" />
				</DIV>
				</TD>
			</tr>

				<tr>
				<TD class=2-td2-left width="30%">申报表号</TD>
				<TD class=2-td2-left width="30%">收现缴款书号</TD>
				<TD class=2-td2-left width="20%">年度字别</TD>
				<TD class=2-td2-center width="20%">票证种类</TD>

				</tr>


 <logic:iterate id="data" indexId="index" length="length" name="blAllSbWszForm" property="dataList"
                                                    type="com.creationstar.bjtax.qsgl.model.bo.BlJksBo">
				<tr>

				<TD class=2-td2-left>
				<DIV align=center><bean:write name="data" property="sbbh" /></DIV></td>

				<TD class=2-td2-left >
				<DIV align=center><bean:write name="data" property="wszh" /></DIV></td>

				<TD class=2-td2-left>
				<DIV align=center><bean:write name="data" property="ndzb" /></DIV></td>

				<TD class=2-td2-center>
				<DIV align=center>
				<logic:equal name="data" property="pzzldm" value="<%=Constants.WSZ_PZZLDM_SG%>">手工票
				</logic:equal>
				<logic:equal name="data" property="pzzldm" value="<%=Constants.WSZ_PZZLDM%>">机打票
				</logic:equal>
				</DIV>
				</TD>


			 </TR>

</logic:iterate>

		 
			</TBODY>
			</TABLE>


			<br>

            <DIV align=center>

            <IMG alt=保存 height=22 id=baocun name=Submit63
            onclick="checkSubmit('Save');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
            src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79>

            <IMG alt=返回 height=22 id=tuichu name=fanhui
            onclick="checkSubmit('Back');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('fanhui','','<%=static_file%>images/fanhui2.jpg',1)"
            src="<%=static_file%>images/fanhui1.jpg" style="CURSOR: hand" width=79>
            </DIV><BR>
<%@ include file="../include/Bottom.jsp" %>
	</TBODY>
	</TABLE>

     </html:form>

</BODY></HTML>

