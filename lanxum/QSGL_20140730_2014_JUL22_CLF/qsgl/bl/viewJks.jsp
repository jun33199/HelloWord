<%@ page contentType="text/html; charset=GBK"%>

<%@ page import="java.math.BigDecimal"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="java.math.BigDecimal"%>

<%@ include file="/include/include.jsp"%>

<HTML><HEAD><TITLE>����ɿ���</TITLE>
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
 <jsp:param name="subtitle" value="�ɿ������&gt;��ѯ�ɿ���&gt;����ɿ���"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>


<TABLE align=center  border=0 cellPadding=0 cellSpacing=0 >
	<TBODY>
		<TR>
		  <TD align=center  class=0><B><FONT SIZE="6" COLOR="">��˰�ɿ���</FONT></B></TD>
	    </TR>
 </TBODY></TABLE><BR>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height=580 width=770>
<TBODY>
	   <TR>
		<TD  class=2-td2-t-center> <DIV align=center><B><FONT SIZE="4" COLOR="">�л����񹲺͹�</FONT></B></DIV><BR>
		  <DIV align=center><U>&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;˰&nbsp;&nbsp;&nbsp;&nbsp;</U>&nbsp;˰�սɿ���</DIV></TD>
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
             	<TD class=0 width="10%">������ϵ:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="data1" property="lsgxmc" /></TD>
             	<TD class=0 width="10%">˰������:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="data1" property="sklxmc" /></TD>
             	<TD class=0 width="10%">���Ա��:</TD>
				<TD align=left class=0 width="22%"><bean:write name="data1" property="jkpzh" /></TD>
               </TR>
               <TR>
             	<TD class=0 width="10%">ע������:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="data1" property="djzclxmc" /></TD>
             	<TD class=0 width="10%">�����:</TD>
             	<TD align=left class=0 width="22%"><%=DataConvert.TimeStamp2String(data1.getCjrq())%></TD>
             	<TD class=0 width="10%">���ջ���:</TD>
				<TD align=left class=0 width="22%"><bean:write name="data1" property="swjgzzjgmc" /></TD>
                </TR></TBODY></TABLE><BR>

		    <TABLE border=1 cellSpacing=0 class=table-99 width="75%">
		    <TR>
		    	<TD rowspan=4 class=2-td2-t-left width="5%">�ɿλ(��)</TD>
		    	<TD class=2-td2-t-left width="8%">����</TD>
		    	<TD align=left class=2-td2-t-left width="12%"><bean:write name="data1" property="jsjdm" />&nbsp;</TD>
		    	<TD class=2-td2-t-left width="5%">�绰</TD>
		    	<TD align=left class=2-td2-t-left width="15%"><bean:write name="data1" property="jydzlxdm" />&nbsp;</TD>
				<TD rowspan=3 class=2-td2-t-left width="10%">Ԥ���Ŀ</TD>
		    	<TD class=2-td2-t-left width="5%">����</TD>
		    	<TD align=left class=2-td2-t-center width="30%"><bean:write name="data1" property="yskmdm" />&nbsp;</TD>
		    </TR>
		    <TR>

		    	<TD class=2-td2-left width="8%">ȫ��</TD>
		    	<TD colspan=3 class=2-td2-left width="12%"><bean:write name="data1" property="nsrmc" />&nbsp;</TD>
		    	<TD class=2-td2-left width="5%">����</TD>
		    	<TD class=2-td2-center width="30%"><bean:write name="data1" property="yskmmc" />&nbsp;</TD>
		    </TR>
		    <TR>
		    	<TD class=2-td2-left width="8%">��������</TD>
		    	<TD align=left colspan="3 " class=2-td2-left width="12%"><bean:write name="data1" property="yhmc" />&nbsp;</TD>
		    	<TD class=2-td2-left width="5%">����</TD>
		    	<TD align=left class=2-td2-center width="30%"><bean:write name="data1" property="ysjcmc" />&nbsp;</TD>
		    </TR>
		    <TR>
		    	<TD class=2-td2-left width="5%">�ʺ�</TD>
		    	<TD align=left colspan="3" class=2-td2-left width="15%"><bean:write name="data1" property="zh" />&nbsp;</TD>
		    	<TD class=2-td2-left width="5%">�տ����</TD>
		    	<TD align=left colspan="2" class=2-td2-center width="30%"><bean:write name="data1" property="gkzzjgmc" />&nbsp;</TD>
		    </TR>
			 <TR>
		    	<TD  colspan="5" align=left class=2-td2-left width="5%"><div align="left">˰������ʱ��:<%=DataConvert.TimeStamp2String(data1.getSkssksrq())%>&nbsp;��&nbsp;<%=DataConvert.TimeStamp2String(data1.getSkssjsrq())%></div></TD>
		    	<TD  colspan="3" class=2-td2-center width="10%"><div align="left">�ɿ��޽�����:<%=DataConvert.TimeStamp2String(data1.getXjrq())%>&nbsp;</div></TD>

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
                width=120><STRONG>Ʊ֤��Ϣ�б�</STRONG></TD>
                <TD>
                  <HR class=hr1 SIZE=1 width="100%">
                </TD></TR></TBODY></TABLE>

          <TABLE border=1 cellSpacing=0 class=table-99 width="75%">
           <TBODY>
		   <TR>
           	<TD colspan="2" class=2-td2-t-left width="20%">��Ŀ����</TD>
           	<TD class=2-td2-t-left width="20%">��˰����</TD>
           	<TD class=2-td2-t-left width="20%">��˰������������</TD>
           	<TD class=2-td2-t-center width="20%">ʵ��˰��</TD>
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
                  <DIV align=left>�ɿλ(��):</DIV>
				  <DIV align=left><bean:write name="data1" property="nsrmc" /></DIV>
				  <DIV align=left>������(��):</DIV></TD>
                <TD class=2-td2-t-left width="25%">
                   <DIV align=left>�ط�˰�����:</DIV>
				  <DIV align=left><bean:write name="data1" property="swjgzzjgmc" /></DIV>
				  <DIV align=left>��Ʊ��:</DIV></TD>
                <TD class=2-td2-t-left width="5%">
                  <DIV align=left>��ע:</DIV></TD>
				 <TD  class=2-td2-t-center width="84">
                 <DIV align=left><bean:write name="data1" property="bz" /></DIV>&nbsp;</TD>
                </DIV></TD></TR></TBODY></TABLE><BR>


				    <DIV align="center">
                    <IMG alt=��¼ height=22 name=tuichu
                      onclick="doSubmitForm('BlFhz');"
                      onMouseOver="MM_swapImage('bl1','','<%=static_file%>images/qs_bu2.jpg',1)"
                      onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/qs_bu1.jpg"
                      width="79" id="bl1" style="cursor:hand">


                    <IMG alt=���� height=22 name=tuichu
                      onclick="doSubmitForm('Return');"
                      onMouseOver="MM_swapImage('fanhui1','','<%=static_file%>images/fanhui2.jpg',1)"
                      onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/fanhui1.jpg"
                      width="79" id="fanhui1" style="cursor:hand">
                    </DIV>


</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>

