<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>

<HTML><HEAD><TITLE>�������ֽɿ���</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType)
{
  document.all.operationType.value=operationType;
  if(operationType=="get" )
  {
		if(document.forms[0].zb.value == "")
        {
            alert("�Բ�������ֱ���Ϊ�գ����������룡");
            document.forms[0].zb.focus();
            return false;
        }
		if(document.forms[0].wszqshm.value == "")
        {
            alert("�Բ������ֽɿ�����벻��Ϊ�գ����������룡");
            document.forms[0].wszqshm.focus();
            return false;
        }
		if(document.forms[0].wszjzhm.value == "")
        {
            alert("�Բ��𣬡�����ȷ�����ֽɿ�����롱����Ϊ�գ����������룡");
            document.forms[0].wszjzhm.focus();
            return false;
        }
		if(document.forms[0].wszqshm.value != document.forms[0].wszjzhm.value)
        {
            alert("�Բ�������¼������ֽɿ�����벻ƥ�䣬���������룡");
            document.forms[0].wszjzhm.focus();
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
</SCRIPT>
</HEAD>

<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="���ֽɿ������&gt;�������ֽɿ���"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
<% String pzzldm=""; %>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>�������ֽɿ���</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
<html:form action="/wsz/cxWsz.do">
<html:hidden property="operationType"/>
<html:hidden property="yuan" value="cxwsz"/>
<html:hidden property="pzzldm"/>
            <br>
            <table cellspacing="0" class="table-60">
              <tr>
                <td class="2-td2-t-left">����ֱ�</td>
                <td class="2-td2-t-center">
                    <html:text property="zb" maxlength="4"/><FONT color=red>*</FONT>
                </td>
              </tr>
              <tr>
                <td class="2-td2-left">���ֽɿ������</td>
                <td  class="2-td2-center">
                    <html:text property="wszqshm"  maxlength="<%=Constants.PZZLCD%>"/><FONT color=red>*</FONT>
                </td>
               </tr>
               <tr>
                <td class="2-td2-left">����ȷ�����ֽɿ������</td>
                <td  class="2-td2-center">
                   <html:text property="wszjzhm"  maxlength="<%=Constants.PZZLCD%>"/><FONT color=red>*</FONT>
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
 <logic:equal name="queryWszForm" property="status" scope="session" value="Result">
     <TABLE border="0" cellSpacing=0 class=table-99>
      <TBODY>
       <TR>
        <TD class="2-td1-left" width = "8%" >���ֽɿ����</TD>
        <TD class="2-td1-left" width = "8%" >�ֱ�</TD>
        <TD class="2-td1-left" width = "12%" >�걨���</TD>
        <TD class="2-td1-left" width = "8%" >�Ƿ����</TD>
        <TD class="2-td1-left" width = "7%" >��ӡ״̬</TD>
        <TD class="2-td1-left" width = "10%" >ʵ�ɽ��</TD>
        <TD class="2-td1-left" width = "12%" >�����</TD>
        <TD class="2-td1-left" width = "8%" >��˰�˴���</TD>
        <TD class="2-td1-left" width = "10%" >���յ�����</TD>
        <TD class="2-td1-center" width = "18%" >������Ա</TD>
      </TR>
      <%
      	boolean cxFlag = false;
      %>
      <logic:iterate id="data" indexId="index" length="length" name="queryWszForm" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.model.bo.QueryWszBo">
      <bean:define id="data1" name="data" property="qswszzVo" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz"/>

            <tr>
               <!-- ��˰֤����-->
              <td class="2-td2-left">
              <a href="<%=response.encodeURL("/qsgl/wsz/viewWsz.do?operationType=Show&viewIndex=") + index.intValue()%>">
                  <bean:write name="data1" property="wszh"/>&nbsp;
              </a>
              </td>
              <!--  �ֱ�  -->
              <td class="2-td2-left">
                  <bean:write name="data1" property="ndzb"/>&nbsp;
              </td>
              <!-- �걨���  -->
              <td class="2-td2-left" style="word-break:break-all">
                  <bean:write name="data1" property="sbbh"/>&nbsp;
              </td>
              <!--  �Ƿ���� -->
              <td class="2-td2-left">
              <%
              pzzldm = data1.getPzzldm();
              if(data1.getSbhzdh()!=null&&!data1.getSbhzdh().equals(""))
              {
              		cxFlag = true;
              %>
                  �ѻ���
               <%}
               else
               {%>
                  δ����
               <% } %>
               &nbsp;
              </td>
              <!-- ��ӡ״̬  -->
              <td class="2-td2-left">
              <%
              if(Integer.parseInt(Constants.WSZ_CLBJDM_YWS) <= (Integer.parseInt(data1.getClbjdm())))
              {%>
                  �Ѵ�ӡ
              <%}
               else
               {%>
                  δ��ӡ
               <% } %>
                  &nbsp;
              </td>
                <!-- ʵ��˰��  -->
              <td class="2-td2-left">
              &nbsp;<%=DataConvert.BigDecimal2String(data1.getHjsjje())%>
              </td>
                <!--�����   -->
              <td class="2-td2-left" style="word-break:break-all">
                 &nbsp; <%=DataConvert.TimeStamp2String(data1.getCjrq())%>
              </td>
               <!--  ��˰�˼�������� -->
              <td class="2-td2-left">
                  <bean:write name="data1" property="nsrjsjdm"/>&nbsp;
              </td>
                <!-- ���յ�����  -->
              <td class="2-td2-left">
                  <bean:write name="data1" property="zsdmc"/>&nbsp;
              </td>
                <!-- ������Ա  -->
              <td class="2-td2-center" style="word-break:break-all">
                 &nbsp; <bean:write name="data1" property="lrr"/>
              </td>

            </tr>
           </logic:iterate>
          </table><br>
              <%
              if(cxFlag)
              {%>
                  ���ܳ������ܣ�
               <%}
               else
               {%>
                  <input name="btn" type="button" onclick="doCxWsz()" value="����">
               <% } %>
        </logic:equal>
          <br>
      </div>

</html:form>
<script type="text/javascript">
function doCxWsz()
{
		if(confirm("ȷʵҪ�����������ֽɿ����¼��"))
		{
		document.forms[0].pzzldm.value=<%=pzzldm %>;
    	document.forms[0].operationType.value='CxWsz';
    	document.forms[0].submit();
		}
}
</script>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
