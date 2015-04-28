<%@page contentType="text/html; charset=GBK"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%@page import="com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.web.DsdzdkForm"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.client.SdwszsbhzCx"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Sdwszsbhz"%>


<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<html>
<head>
<title>撤消代售代征代扣汇总</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<script language=JavaScript>

function splitPage(viewPage)
{
    document.forms[0].actionType.value   = "SplitPage";
    document.forms[0].viewPageType.value = viewPage;
    document.forms[0].optJspTag.value  = "DR";
    return true;
}

function deletekao(index)
{
    if (confirm("您确定要删除此汇总单的汇总数据吗?"))
    {
        document.forms[0].actionType.value = "EraseHz";
        document.forms[0].curEraseIndex.value = index;
        document.forms[0].submit();
        return true;
    }
    else
    {
        return false;
    }
}

function view(index)
{
    document.forms[0].actionType.value  = "ViewJkpzh";
    document.forms[0].curEraseIndex.value = index;
    document.forms[0].submit();
    return true;
}

function back()
{
    document.forms[0].actionType.value  = "Back";
    document.forms[0].submit();
}

function checksplit(imageindex)
{
    var curpage = document.forms[0].curpage.value
    var maxpage = document.forms[0].maxpage.value
    if((curpage ==0 && maxpage ==0) || maxpage ==1 )
    {
    return true;
    }
    else
    {
       if(curpage == 1 )
       {
         if(imageindex == 1 || imageindex ==2 )
         {
        return true;
         }
       else
       {
          return false;
       }
    }
    else
    {
       if(curpage == maxpage)
       {
         if(imageindex == 3 || imageindex ==4)
         {
           return true;
         }
         else
         {
            return false;
         }
    return true;
       }
       else
       {
        return false;
       }
    }
  }
    return true;
}
</script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">


<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>

        <jsp:include page="../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="撤消代售代征代扣汇总" />
		<jsp:param name="help_url" value="help/wssb/sbzl/dsdzdk/dsdzdk-003.htm"/>
        </jsp:include>

        <table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td valign="top">
         <br>
          <html:errors/>

<table align="center" cellspacing="0" class="table-99" border="0">
        <tr class="black9">
          <td class="1-td1">撤消代售代征代扣汇总</td>
        </tr>
        <tr>
          <td valign="top" class="1-td2">
            <br>
            <table cellspacing="0" class="table-99">
               <tr class="black9">
                <td align="right" nowrap>金额单位：人民币元</td>
              </tr>
             </table>

            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td nowrap class="2-td2-t-left"><div align="right">计算机代码&nbsp;&nbsp;</div></td>
                <td nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="dsdzdkForm" property="jsjdm"/></div></td>
                <td nowrap class="2-td2-t-left"><div align="right">单位名称&nbsp;&nbsp;</div></td>
                <td nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="dsdzdkForm" property="dwmc"/></div></td>
                <td nowrap class="2-td2-t-left"><div align="right">联系电话&nbsp;&nbsp;</div></td>
                <td nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<bean:write name="dsdzdkForm" property="lxdh"/></div></td>
              </tr>
            </table>
            <br>
    <html:form method="POST" action="/dsdzdk.do">
    <html:hidden property = "actionType" />
    <html:hidden property = "viewPageType" />
    <html:hidden property = "optJspTag" />
    <html:hidden property = "curEraseIndex" />
            <table width="100%" cellspacing=0 class="table-99">
              <tr>
                <td valign=top nowrap class="2-td1-left">&nbsp;</td>
                <td valign=top nowrap class="2-td1-left">汇总日期</td>
                <td valign=top nowrap class="2-td1-left">汇总单号</td>
                <td valign=top nowrap class="2-td1-left">缴款凭证张数</td>
                <td valign=top nowrap class="2-td1-left">实缴金额合计</td>
                <td valign=top nowrap class="2-td1-center">&nbsp;</td>
              </tr>
<logic:iterate id="sdwszsbhzcx" indexId="index" name="dsdzdkForm" property="erasehzdList" scope="session">
              <tr>
        <td  nowrap class="2-td2-left"> <a href="#" onclick="javascript:return view(<shenbao:write name="index"/>)"</a> 查看 </td>
                <td nowrap class="2-td2-left"><div align="left">&nbsp;<shenbao:write name="sdwszsbhzcx" property="hzrq"/></div></td>
                <td nowrap class="2-td2-left"><div align="left">&nbsp;<bean:write name="sdwszsbhzcx" property="sbhzdh"/>&nbsp;</div></td>
                <td nowrap class="2-td2-left"><div align="right"><bean:write name="sdwszsbhzcx" property="jkpzhNum"/></div></td>
                <td nowrap class="2-td2-left"><div align="right"><shenbao:write name="sdwszsbhzcx" property="sjjehz"/>&nbsp;</div></td>
        <td  nowrap class="2-td2-center"><a href="#" onclick="javascript:return deletekao(<shenbao:write name="index"/>)"<a/> 删除 </td>
              </tr>
</logic:iterate>
            </table>
            <br>
<table width="100%" cellspacing=0 class="table-99">
              <tr>
                <td nowrap class="2-td1-left">序号</td>
                <td nowrap class="2-td1-left">汇总单号</td>
                <td nowrap class="2-td1-left">缴款凭证号</td>
                <td nowrap class="2-td1-center">实缴金额</td>
              </tr>
<% int i=1; %>
<logic:iterate id="sdwszsbhz" indexId="indexjkpzh" name="dsdzdkForm" property="erasejkpzhList" scope="session">
              <tr>
                <td  nowrap class="2-td2-left"> <%= i++ %></td>
                <td  nowrap class="2-td2-left"><div align="left">&nbsp;<bean:write name="sdwszsbhz" property="sbhzdh"/></div></td>
                <td  nowrap class="2-td2-left">
                  <div align="left">&nbsp;
                     <a href='dsdzdk.do?actionType=Print&sbbhIndex=<bean:write name="sdwszsbhz" property="sbbh"/>&jkshIndex=<bean:write name="sdwszsbhz" property="jkpzh"/>&src=BacktoCx'><bean:write name="sdwszsbhz" property="jkpzh"/></a>
                  </div>
                </td>
                <td  nowrap class="2-td2-center"><div align="right"><shenbao:write name="sdwszsbhz" property="sjse"/>&nbsp;</div></td>
              </tr>
</logic:iterate>
            </table>
    <br>
        <input type="hidden" name="curpage" value=<bean:write name="dsdzdkForm" property="pageindex"/>>
    <input type="hidden" name="maxpage" value=<bean:write name="dsdzdkForm" property="maxpageindex"/>>
    </html:form>

    <html:form method="POST" action="/dsdzdk.do">
        <html:hidden property = "actionType" />
        <table>
          <tr>
             <td align="center">
				<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="back()" style="cursor:hand">
             </td>
          </tr>
        </table>
    </html:form>
             </td>
        </tr>
      </table>

        </td>
      </tr>
        </table>
    </td>
  </tr>
  <tr>
    <td valign="bottom" align="center">
 <br>
    <%@ include file="../include/bottom.jsp" %>
    </td>
  </tr>
 </table>
</body>
</html>