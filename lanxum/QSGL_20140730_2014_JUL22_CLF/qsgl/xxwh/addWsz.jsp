<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>

<HTML><HEAD><TITLE>补录契税收现缴款书</TITLE>
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
        alert("请输入申报表号");
        document.forms[0].sbbh.focus();
        return false;
    }

    if(document.forms[0].wszh.value.length < 1)
    {
        alert("请输入收现缴款书号");
        document.forms[0].wszh.focus();
        return false;
    }

    if(document.forms[0].ndzb.value.length < 1)
    {
        alert("请输入年度字别");
        document.forms[0].ndzb.focus();
        return false;
    }

    if(document.forms[0].clbjdm.value == "<%=Constants.WSZ_CLBJDM_YJZ%>")
    {
        if(document.forms[0].jkpzh.value.length < 1)
        {
            alert("您选择了补录已汇总的收现缴款书，请输入缴款凭证号");
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
 <jsp:param name="subtitle" value="信息维护&gt;补录契税收现缴款书"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>补录契税收现缴款书</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
         <html:form action="/xxwh/addWsz.do">
         <html:hidden property="operationType"/>

            <br>
            <table cellspacing="0" class="table-60">
              <tr>
                <td class="2-td2-t-left">申报表号</td>
                <td  class="2-td2-t-center"><div align="left">
                    <html:text name = "wszForm" property="sbbh"  maxlength="20"/>
                    <FONT color=red>*</FONT>
                </div>
                </td>
               </tr>

               <tr>
                <td class="2-td2-left">收现缴款书号</td>
                <td  class="2-td2-center"><div align="left">
                    <html:text name = "wszForm" property="wszh" maxlength="20"/>
                    <FONT color=red>*</FONT>
                </div>
                </td>
               </tr>

               <tr>
                <td class="2-td2-left">年度字别</td>
                <td  class="2-td2-center"><div align="left">
                    <html:text name = "wszForm" property="ndzb" maxlength="4"/>
                    <FONT color=red>*</FONT>
                </div>
                </td>
               </tr>

               <tr>
                <td class="2-td2-left">票证种类</td>
                <td  class="2-td2-center"><div align="left">
                    <html:select property = "pzzldm">
                        <html:option value = "<%=Constants.WSZ_PZZLDM%>" >计算机打票</html:option>
                        <html:option value = "<%=Constants.WSZ_PZZLDM_SG%>" >手工填写票</html:option>
                    </html:select></div>
                </td>
              </tr>

              <tr>
                <td class="2-td2-left">是否分票</td>
                <td  class="2-td2-center"><div align="left">
                    <html:select property = "fp" >
                        <html:option value = "0" >否</html:option>
                        <html:option value = "1" >是</html:option>
                    </html:select></div>
                </td>
              </tr>

              <tr>
                <td class="2-td2-left">收现缴款书状态</td>
                <td  class="2-td2-center"><div align="left">
                    <html:select property = "clbjdm" onchange="change()">
                        <html:option value = "<%=Constants.WSZ_CLBJDM_YWS%>" >未汇总</html:option>
                        <html:option value = "<%=Constants.WSZ_CLBJDM_YJZ%>" >已汇总</html:option>
                    </html:select></div>
                </td>
              </tr>

              <tr>
                <td class="2-td2-left">缴款凭证号</td>
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
          width="79" height="22" id="chaxun1" alt="查询" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;

        <IMG name="back"
          onclick="doSubmitForm('Return');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">
      </DIV><BR>

</html:form>
<%@ include file="../include/Bottom.jsp" %>
<script language=JavaScript type='text/JavaScript'>
    document.forms[0].jkpzh.disabled = true;
</script>
</BODY></HTML>
