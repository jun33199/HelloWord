<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<HTML><HEAD><TITLE>�ɿ����Ʊά��</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>

<script language=JavaScript type='text/JavaScript'>

function doSubmitForm(operationType)
{
    if(operationType == "Update")
    {
        for(i = 0; i < 2; i++)
        {
            if(document.forms[0].jkpzhao[i].value.length < 1 )
            {
                alert("�ɿ�ƾ֤�Ų���Ϊ�գ�����������");
                document.forms[0].jkpzhao[i].focus();
                return false;
            }
        }

        if(document.forms[0].jkpzhao[0].value == document.forms[0].jkpzhao[1].value)
        {
            alert("�����ɿ�ƾ֤�Ų�����ȣ�����������");
            return false;
        }

    }

    document.all.operationType.value = operationType;
    document.forms[0].submit();
}

</script>

</HEAD>

<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="��Ϣά��&gt;�ɿ����Ʊά��"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>�ɿ����Ʊά��</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
<html:form action="/xxwh/fpHzJks.do">
<html:hidden property="operationType"/>
<html:hidden property="yuan" value="cxwsz"/>
            <br>
            <table cellspacing="0" class="table-60">
              <tr>
                <td class="2-td2-t-left">�ɿ����</td>
                <td  class="2-td2-t-center">
                    <html:text property="jkpzhao"  maxlength="20"/><FONT color=red>*</FONT>
                </td>
               </tr>
               <tr>
                <td class="2-td2-left">��Ʊ�Ľɿ����</td>
                <td  class="2-td2-center">
                   <input name="jkpzhao"  type="text" maxlength="20"/><FONT color=red>*</FONT>
                </td>
              </tr>
            </table>
            <br>

      <DIV align=center>
      <IMG name="query"
          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
          onclick = "doSubmitForm('Update');"
          width="79" height="22" id="chaxun1" alt="��ѯ" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;

        <IMG name="back"
          onclick="doSubmitForm('Return');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="�˳�" style="cursor:hand">
      </DIV><BR>

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
