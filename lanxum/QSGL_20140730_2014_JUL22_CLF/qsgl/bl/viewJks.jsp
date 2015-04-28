<%@ page contentType="text/html; charset=GBK"%>

<%@ page import="java.math.BigDecimal"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="java.math.BigDecimal"%>

<%@ include file="/include/include.jsp"%>

<HTML><HEAD><TITLE>浏览缴款书</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>

<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>


<SCRIPT language=JavaScript>
function doSubmitForm(operationType)
{
    document.forms[0].operationType.value=operationType;
  	document.forms[0].submit();
}
</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="缴款书管理&gt;查询缴款书&gt;浏览缴款书"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>


<TABLE align=center  border=0 cellPadding=0 cellSpacing=0 >
	<TBODY>
		<TR>
		  <TD align=center  class=0><B><FONT SIZE="6" COLOR="">契税缴款书</FONT></B></TD>
	    </TR>
 </TBODY></TABLE><BR>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height=580 width=770>
<TBODY>
	   <TR>
		<TD  class=2-td2-t-center> <DIV align=center><B><FONT SIZE="4" COLOR="">中华人民共和国</FONT></B></DIV><BR>
		  <DIV align=center><U>&nbsp;&nbsp;&nbsp;&nbsp;契&nbsp;税&nbsp;&nbsp;&nbsp;&nbsp;</U>&nbsp;税收缴款书</DIV></TD>
		</TR>

        <TR>
        <TD class=1-td2><BR>
        <html:form action="/qsbl/viewJks.do" >
        <html:hidden property = "operationType" />
<bean:define id="data" name="blJksForm" property="jksBo" type="com.creationstar.bjtax.qsgl.model.bo.JksBo"/>
<bean:define id="data1" name="data" property="sbjkzb" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb"/>
            <TABLE border=0 cellSpacing=0 class=table-99 width="88%">
              <TBODY>
               <TR>
             	<TD class=0 width="10%">隶属关系:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="data1" property="lsgxmc" /></TD>
             	<TD class=0 width="10%">税款类型:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="data1" property="sklxmc" /></TD>
             	<TD class=0 width="10%">电脑编号:</TD>
				<TD align=left class=0 width="22%"><bean:write name="data1" property="jkpzh" /></TD>
               </TR>
               <TR>
             	<TD class=0 width="10%">注册类型:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="data1" property="djzclxmc" /></TD>
             	<TD class=0 width="10%">填发日期:</TD>
             	<TD align=left class=0 width="22%"><%=DataConvert.TimeStamp2String(data1.getCjrq())%></TD>
             	<TD class=0 width="10%">征收机关:</TD>
				<TD align=left class=0 width="22%"><bean:write name="data1" property="swjgzzjgmc" /></TD>
                </TR></TBODY></TABLE><BR>

		    <TABLE border=1 cellSpacing=0 class=table-99 width="75%">
		    <TR>
		    	<TD rowspan=4 class=2-td2-t-left width="5%">缴款单位(人)</TD>
		    	<TD class=2-td2-t-left width="8%">代码</TD>
		    	<TD align=left class=2-td2-t-left width="12%"><bean:write name="data1" property="jsjdm" />&nbsp;</TD>
		    	<TD class=2-td2-t-left width="5%">电话</TD>
		    	<TD align=left class=2-td2-t-left width="15%"><bean:write name="data1" property="jydzlxdm" />&nbsp;</TD>
				<TD rowspan=3 class=2-td2-t-left width="10%">预算科目</TD>
		    	<TD class=2-td2-t-left width="5%">编码</TD>
		    	<TD align=left class=2-td2-t-center width="30%"><bean:write name="data1" property="yskmdm" />&nbsp;</TD>
		    </TR>
		    <TR>

		    	<TD class=2-td2-left width="8%">全称</TD>
		    	<TD colspan=3 class=2-td2-left width="12%"><bean:write name="data1" property="nsrmc" />&nbsp;</TD>
		    	<TD class=2-td2-left width="5%">名称</TD>
		    	<TD class=2-td2-center width="30%"><bean:write name="data1" property="yskmmc" />&nbsp;</TD>
		    </TR>
		    <TR>
		    	<TD class=2-td2-left width="8%">开户银行</TD>
		    	<TD align=left colspan="3 " class=2-td2-left width="12%"><bean:write name="data1" property="yhmc" />&nbsp;</TD>
		    	<TD class=2-td2-left width="5%">级次</TD>
		    	<TD align=left class=2-td2-center width="30%"><bean:write name="data1" property="ysjcmc" />&nbsp;</TD>
		    </TR>
		    <TR>
		    	<TD class=2-td2-left width="5%">帐号</TD>
		    	<TD align=left colspan="3" class=2-td2-left width="15%"><bean:write name="data1" property="zh" />&nbsp;</TD>
		    	<TD class=2-td2-left width="5%">收款国库</TD>
		    	<TD align=left colspan="2" class=2-td2-center width="30%"><bean:write name="data1" property="gkzzjgmc" />&nbsp;</TD>
		    </TR>
			 <TR>
		    	<TD  colspan="5" align=left class=2-td2-left width="5%"><div align="left">税款所属时期:<%=DataConvert.TimeStamp2String(data1.getSkssksrq())%>&nbsp;至&nbsp;<%=DataConvert.TimeStamp2String(data1.getSkssjsrq())%></div></TD>
		    	<TD  colspan="3" class=2-td2-center width="10%"><div align="left">缴款限缴日期:<%=DataConvert.TimeStamp2String(data1.getXjrq())%>&nbsp;</div></TD>

		    </TR>
		    </TABLE>
			 <HR class=hr1 SIZE=1 width="99%">
            <TABLE cellSpacing=0 class=table-99>
              <TBODY>
              <TR>
                <TD>
                  <HR class=hr1 SIZE=1 width="100%">
                </TD>
                <TD align=middle class=black9
                width=120><STRONG>票证信息列表</STRONG></TD>
                <TD>
                  <HR class=hr1 SIZE=1 width="100%">
                </TD></TR></TBODY></TABLE>

          <TABLE border=1 cellSpacing=0 class=table-99 width="75%">
           <TBODY>
		   <TR>
           	<TD colspan="2" class=2-td2-t-left width="20%">项目名称</TD>
           	<TD class=2-td2-t-left width="20%">课税数量</TD>
           	<TD class=2-td2-t-left width="20%">计税金额或销售收入</TD>
           	<TD class=2-td2-t-center width="20%">实缴税额</TD>
           </TR>

 <logic:iterate id="data2" indexId="index" length="length" name="data" property="resultList" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx"  >
           <TR>
           	<TD colspan="2" class=2-td2-left width="20%"><bean:write name="data2" property="szsmmc" />&nbsp;</TD>
           	<TD class=2-td2-left width="20%"><bean:write name="data2" property="kssl"/>&nbsp;</TD>
           	<TD class=2-td2-left width="20%"><%=DataConvert.BigDecimal2String((BigDecimal)data2.getJsje(),2)%>&nbsp;</TD>
           	<TD class=2-td2-center width="20%"><%=DataConvert.BigDecimal2String((BigDecimal)data2.getSjse(),2)%>&nbsp;</TD>
           </TR>
</logic:iterate>

		   </TBODY>
           </TABLE>
          <HR class=hr1 SIZE=1 width="99%">

            <TABLE border=0 cellSpacing=0 class=table-99 width="75%">
              <TBODY>
              <TR>
                <TD class=2-td2-t-left width="25%">
                  <DIV align=left>缴款单位(人):</DIV>
				  <DIV align=left><bean:write name="data1" property="nsrmc" /></DIV>
				  <DIV align=left>经办人(章):</DIV></TD>
                <TD class=2-td2-t-left width="25%">
                   <DIV align=left>地方税务机关:</DIV>
				  <DIV align=left><bean:write name="data1" property="swjgzzjgmc" /></DIV>
				  <DIV align=left>填票人:</DIV></TD>
                <TD class=2-td2-t-left width="5%">
                  <DIV align=left>备注:</DIV></TD>
				 <TD  class=2-td2-t-center width="84">
                 <DIV align=left><bean:write name="data1" property="bz" /></DIV>&nbsp;</TD>
                </DIV></TD></TR></TBODY></TABLE><BR>


				    <DIV align="center">
                    <IMG alt=补录 height=22 name=tuichu
                      onclick="doSubmitForm('BlFhz');"
                      onMouseOver="MM_swapImage('bl1','','<%=static_file%>images/qs_bu2.jpg',1)"
                      onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/qs_bu1.jpg"
                      width="79" id="bl1" style="cursor:hand">


                    <IMG alt=返回 height=22 name=tuichu
                      onclick="doSubmitForm('Return');"
                      onMouseOver="MM_swapImage('fanhui1','','<%=static_file%>images/fanhui2.jpg',1)"
                      onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/fanhui1.jpg"
                      width="79" id="fanhui1" style="cursor:hand">
                    </DIV>


</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>

