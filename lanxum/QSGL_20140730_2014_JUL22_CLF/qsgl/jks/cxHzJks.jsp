<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>

<HTML><HEAD><TITLE>�����ɿ���</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType)
{
  document.all.operationType.value=operationType;
  if(operationType=="get" )
  {
		if(document.forms[0].jkpzh.value == "")
        {
            alert("�Բ��𣬽ɿ���Ų���Ϊ�գ����������룡");
            document.forms[0].jkpzh.focus();
            return false;
        }
    if(document.forms[0].jkpzh.value.length < 8)
        {
            alert("�Բ��𣬽ɿ���Ų�������8λ�����������룡");
            document.forms[0].jkpzh.focus();
            return false;
        }
		if(document.forms[0].re_jkpzh.value == "")
        {
            alert("�Բ��𣬡�����ȷ�Ͻɿ���š�����Ϊ�գ����������룡");
            document.forms[0].re_jkpzh.focus();
            return false;
        }
		if(document.forms[0].jkpzh.value != document.forms[0].re_jkpzh.value)
        {
            alert("�Բ�������¼��Ľɿ���Ų�ƥ�䣬���������룡");
            document.forms[0].re_jkpzh.focus();
            return false;
        }
    document.forms[0].operationType.value='Get';
  }
  if(operationType=="quit")
  {
    document.forms[0].operationType.value='Return';
  }
  document.forms[0].submit();
}

function doCxJks()
{
		if(confirm("ȷʵҪ�����������ܵĽɿ����¼��"))
		{
    	document.forms[0].operationType.value='CxJks';
    	document.forms[0].submit();
		}
}
</SCRIPT>
</HEAD>

<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="�ɿ������&gt;�����ɿ���"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>�����ɿ���</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
<html:form action="/jks/cxHzJks.do">
<html:hidden property="operationType"/>
<html:hidden property="yuan" value="cxwsz"/>
            <br>
            <table cellspacing="0" class="table-60">
              <tr>
                <td class="2-td2-t-left">��ѡ�����ɽɿ���ķ�ʽ</td>
                <td  class="2-td2-t-center">
                    <html:select property = "scfs" >
                        <html:option value = "<%=Constants.JKS_SJLY_HZ%>" >��������</html:option>
                        <html:option value = "<%=Constants.JKS_SJLY_FHZ%>" >�ǻ�������</html:option>
                    </html:select>
                </td>
              </tr>
              <tr>
                <td class="2-td2-left">�ɿ����</td>
                <td  class="2-td2-center">
                    <html:text property="jkpzh"  maxlength="20"/><FONT color=red>*</FONT>
                </td>
               </tr>
               <tr>
                <td class="2-td2-left">����ȷ�Ͻɿ����</td>
                <td  class="2-td2-center">
                   <input name="re_jkpzh"  type="text" maxlength="20"/><FONT color=red>*</FONT>
                </td>
              </tr>
            </table>
            <br>

      <DIV align=center>
      <IMG name="query"
          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
          onclick = "doSubmitForm('get');"
          width="79" height="22" id="chaxun1" alt="��ѯ" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;

        <IMG name="back"
          onclick="doSubmitForm('quit');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="�˳�" style="cursor:hand">
      </DIV><BR>

    <br>
 <logic:equal name="hzWsz2JksForm" property="status" scope="session" value="Result">
     <TABLE border="0" cellSpacing=0 class=table-99>
      <TBODY>
       <TR>
        <TD class="2-td1-left" width = "16%" >�ɿ����</TD>
        <TD class="2-td1-left" width = "10%" >˰������</TD>
        <TD class="2-td1-left" width = "15%" >ʵ�ɽ��</TD>
        <TD class="2-td1-left" width = "10%" >���ֽɿ�������</TD>
        <TD class="2-td1-left" width = "16%" >�걨���</TD>
		<TD class="2-td1-center" width = "33%" >��ע</TD>
      </TR>
      <logic:iterate id="data1" indexId="index" length="length" name="hzWsz2JksForm" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb">

       	<TR>
          <td class="2-td2-left"><bean:write name="data1" property="jkpzh" /></td>
          <td class="2-td2-left"><%=DataConvert.TimeStamp2String(data1.getSbrq())%></td>
          <td class="2-td2-left">��<%=DataConvert.BigDecimal2String(data1.getSjje(),2)%></td>
          <td class="2-td2-left">
              <%if(Constants.JKS_SJLY_HZ.equals(data1.getSjly()))
              {
              %>
              <a href="<%=response.encodeURL("/qsgl/jks/viewWszList.do?operationType=QueryWsz&type=2&yuan=returnCx&viewIndex=") + index.intValue()%>">
                  <bean:write name="data1" property="kssl" />
              </a>
             <%}else
             {
             %>
             --
            <%} %>
          </td>
          <td class="2-td2-left">
              <%if(data1.getSbbh() == null || data1.getSbbh().equals("") )
              {
              %>
              --

             <%}else
             {
             %>
             <bean:write name="data1" property="sbbh" />
            <%} %>
          </td>
		  <td class="2-td2-center"><bean:write name="data1" property="bz" />&nbsp;</td>
        </TR>
           </logic:iterate>
          </table><br>
          <input name="btn" type="button" onclick="doCxJks()" value="����">
        </logic:equal>
          <br>
      </div>

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
