<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>

<HTML><HEAD><TITLE>撤销收现缴款书</TITLE>
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
            alert("对不起，年度字别不能为空，请重新输入！");
            document.forms[0].zb.focus();
            return false;
        }
		if(document.forms[0].wszqshm.value == "")
        {
            alert("对不起，收现缴款书号码不能为空，请重新输入！");
            document.forms[0].wszqshm.focus();
            return false;
        }
		if(document.forms[0].wszjzhm.value == "")
        {
            alert("对不起，“请再确认收现缴款书号码”不能为空，请重新输入！");
            document.forms[0].wszjzhm.focus();
            return false;
        }
		if(document.forms[0].wszqshm.value != document.forms[0].wszjzhm.value)
        {
            alert("对不起，两次录入的收现缴款书号码不匹配，请重新输入！");
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
 <jsp:param name="subtitle" value="收现缴款书管理&gt;撤销收现缴款书"/>
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
          <TD class=1-td1>撤销收现缴款书</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
<html:form action="/wsz/cxWsz.do">
<html:hidden property="operationType"/>
<html:hidden property="yuan" value="cxwsz"/>
<html:hidden property="pzzldm"/>
            <br>
            <table cellspacing="0" class="table-60">
              <tr>
                <td class="2-td2-t-left">年度字别</td>
                <td class="2-td2-t-center">
                    <html:text property="zb" maxlength="4"/><FONT color=red>*</FONT>
                </td>
              </tr>
              <tr>
                <td class="2-td2-left">收现缴款书号码</td>
                <td  class="2-td2-center">
                    <html:text property="wszqshm"  maxlength="<%=Constants.PZZLCD%>"/><FONT color=red>*</FONT>
                </td>
               </tr>
               <tr>
                <td class="2-td2-left">请再确认收现缴款书号码</td>
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
          width="79" height="22" id="chaxun1" alt="查询" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;

        <IMG name="back"
          onclick="doSubmitForm('quit');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">
      </DIV><BR>

    <br>
 <logic:equal name="queryWszForm" property="status" scope="session" value="Result">
     <TABLE border="0" cellSpacing=0 class=table-99>
      <TBODY>
       <TR>
        <TD class="2-td1-left" width = "8%" >收现缴款书号</TD>
        <TD class="2-td1-left" width = "8%" >字别</TD>
        <TD class="2-td1-left" width = "12%" >申报表号</TD>
        <TD class="2-td1-left" width = "8%" >是否汇总</TD>
        <TD class="2-td1-left" width = "7%" >打印状态</TD>
        <TD class="2-td1-left" width = "10%" >实纳金额</TD>
        <TD class="2-td1-left" width = "12%" >填发日期</TD>
        <TD class="2-td1-left" width = "8%" >纳税人代码</TD>
        <TD class="2-td1-left" width = "10%" >征收点名称</TD>
        <TD class="2-td1-center" width = "18%" >征收人员</TD>
      </TR>
      <%
      	boolean cxFlag = false;
      %>
      <logic:iterate id="data" indexId="index" length="length" name="queryWszForm" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.model.bo.QueryWszBo">
      <bean:define id="data1" name="data" property="qswszzVo" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz"/>

            <tr>
               <!-- 完税证号码-->
              <td class="2-td2-left">
              <a href="<%=response.encodeURL("/qsgl/wsz/viewWsz.do?operationType=Show&viewIndex=") + index.intValue()%>">
                  <bean:write name="data1" property="wszh"/>&nbsp;
              </a>
              </td>
              <!--  字别  -->
              <td class="2-td2-left">
                  <bean:write name="data1" property="ndzb"/>&nbsp;
              </td>
              <!-- 申报表号  -->
              <td class="2-td2-left" style="word-break:break-all">
                  <bean:write name="data1" property="sbbh"/>&nbsp;
              </td>
              <!--  是否汇总 -->
              <td class="2-td2-left">
              <%
              pzzldm = data1.getPzzldm();
              if(data1.getSbhzdh()!=null&&!data1.getSbhzdh().equals(""))
              {
              		cxFlag = true;
              %>
                  已汇总
               <%}
               else
               {%>
                  未汇总
               <% } %>
               &nbsp;
              </td>
              <!-- 打印状态  -->
              <td class="2-td2-left">
              <%
              if(Integer.parseInt(Constants.WSZ_CLBJDM_YWS) <= (Integer.parseInt(data1.getClbjdm())))
              {%>
                  已打印
              <%}
               else
               {%>
                  未打印
               <% } %>
                  &nbsp;
              </td>
                <!-- 实纳税额  -->
              <td class="2-td2-left">
              &nbsp;<%=DataConvert.BigDecimal2String(data1.getHjsjje())%>
              </td>
                <!--填发日期   -->
              <td class="2-td2-left" style="word-break:break-all">
                 &nbsp; <%=DataConvert.TimeStamp2String(data1.getCjrq())%>
              </td>
               <!--  纳税人计算机代码 -->
              <td class="2-td2-left">
                  <bean:write name="data1" property="nsrjsjdm"/>&nbsp;
              </td>
                <!-- 征收点名称  -->
              <td class="2-td2-left">
                  <bean:write name="data1" property="zsdmc"/>&nbsp;
              </td>
                <!-- 征收人员  -->
              <td class="2-td2-center" style="word-break:break-all">
                 &nbsp; <bean:write name="data1" property="lrr"/>
              </td>

            </tr>
           </logic:iterate>
          </table><br>
              <%
              if(cxFlag)
              {%>
                  不能撤销汇总！
               <%}
               else
               {%>
                  <input name="btn" type="button" onclick="doCxWsz()" value="撤销">
               <% } %>
        </logic:equal>
          <br>
      </div>

</html:form>
<script type="text/javascript">
function doCxWsz()
{
		if(confirm("确实要撤销该条收现缴款书记录吗？"))
		{
		document.forms[0].pzzldm.value=<%=pzzldm %>;
    	document.forms[0].operationType.value='CxWsz';
    	document.forms[0].submit();
		}
}
</script>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
