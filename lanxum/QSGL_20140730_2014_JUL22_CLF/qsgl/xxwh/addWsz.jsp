<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>

<HTML><HEAD><TITLE>��¼��˰���ֽɿ���</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>

<script language=JavaScript type='text/JavaScript'>

function doSubmitForm(operationType)
{
    if(operationType == 'Get')
    {
        if(!checkData())
        {
            return false;
        }
    }

    document.all.operationType.value = operationType;
    document.forms[0].submit();
}


function checkData()
{
    if(document.forms[0].sbbh.value.length < 1)
    {
        alert("�������걨���");
        document.forms[0].sbbh.focus();
        return false;
    }

    if(document.forms[0].wszh.value.length < 1)
    {
        alert("���������ֽɿ����");
        document.forms[0].wszh.focus();
        return false;
    }

    if(document.forms[0].ndzb.value.length < 1)
    {
        alert("����������ֱ�");
        document.forms[0].ndzb.focus();
        return false;
    }

    if(document.forms[0].clbjdm.value == "<%=Constants.WSZ_CLBJDM_YJZ%>")
    {
        if(document.forms[0].jkpzh.value.length < 1)
        {
            alert("��ѡ���˲�¼�ѻ��ܵ����ֽɿ��飬������ɿ�ƾ֤��");
            document.forms[0].jkpzh.focus();
            return false;
        }
    }

    return true;
}

function change()
{
    if(document.forms[0].clbjdm.value == "<%=Constants.WSZ_CLBJDM_YJZ%>")
    {
        document.forms[0].jkpzh.disabled = false;
    }
    else if(document.forms[0].clbjdm.value == "<%=Constants.WSZ_CLBJDM_YWS%>")
    {
        document.forms[0].jkpzh.disabled = true;
    }
}

</script>

</HEAD>

<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="��Ϣά��&gt;��¼��˰���ֽɿ���"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>��¼��˰���ֽɿ���</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
         <html:form action="/xxwh/addWsz.do">
         <html:hidden property="operationType"/>

            <br>
            <table cellspacing="0" class="table-60">
              <tr>
                <td class="2-td2-t-left">�걨���</td>
                <td  class="2-td2-t-center"><div align="left">
                    <html:text name = "wszForm" property="sbbh"  maxlength="20"/>
                    <FONT color=red>*</FONT>
                </div>
                </td>
               </tr>

               <tr>
                <td class="2-td2-left">���ֽɿ����</td>
                <td  class="2-td2-center"><div align="left">
                    <html:text name = "wszForm" property="wszh" maxlength="20"/>
                    <FONT color=red>*</FONT>
                </div>
                </td>
               </tr>

               <tr>
                <td class="2-td2-left">����ֱ�</td>
                <td  class="2-td2-center"><div align="left">
                    <html:text name = "wszForm" property="ndzb" maxlength="4"/>
                    <FONT color=red>*</FONT>
                </div>
                </td>
               </tr>

               <tr>
                <td class="2-td2-left">Ʊ֤����</td>
                <td  class="2-td2-center"><div align="left">
                    <html:select property = "pzzldm">
                        <html:option value = "<%=Constants.WSZ_PZZLDM%>" >�������Ʊ</html:option>
                        <html:option value = "<%=Constants.WSZ_PZZLDM_SG%>" >�ֹ���дƱ</html:option>
                    </html:select></div>
                </td>
              </tr>

              <tr>
                <td class="2-td2-left">�Ƿ��Ʊ</td>
                <td  class="2-td2-center"><div align="left">
                    <html:select property = "fp" >
                        <html:option value = "0" >��</html:option>
                        <html:option value = "1" >��</html:option>
                    </html:select></div>
                </td>
              </tr>

              <tr>
                <td class="2-td2-left">���ֽɿ���״̬</td>
                <td  class="2-td2-center"><div align="left">
                    <html:select property = "clbjdm" onchange="change()">
                        <html:option value = "<%=Constants.WSZ_CLBJDM_YWS%>" >δ����</html:option>
                        <html:option value = "<%=Constants.WSZ_CLBJDM_YJZ%>" >�ѻ���</html:option>
                    </html:select></div>
                </td>
              </tr>

              <tr>
                <td class="2-td2-left">�ɿ�ƾ֤��</td>
                <td  class="2-td2-center"><div align="left">
                    <html:text name = "wszForm" property="jkpzh" maxlength="20"/>
                </div>
                </td>
               </tr>

            </table>
            <br>

      <DIV align=center>
      <IMG name="query"
          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
          onclick = "doSubmitForm('Get');"
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
<script language=JavaScript type='text/JavaScript'>
    document.forms[0].jkpzh.disabled = true;
</script>
</BODY></HTML>
