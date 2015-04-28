<%@page contentType="text/html; charset=GBK"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<html>
<head>
<title>代征代扣代缴汇总</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<script language=JavaScript>

function savesbdata()
{
    document.forms[1].actionType.value = "Save";
    document.forms[1].submit();
        return true;
}

function queryHz()
{
    document.forms[1].actionType.value = "QueryHz";
    document.forms[1].submit();
        return true;
}

function back()
{
    document.forms[1].actionType.value = "Back";
    document.forms[1].submit();
        return true;
}

function splitPage(viewPage)
{
    document.forms[0].actionType.value = "SplitPage";
    document.forms[0].viewPageType.value = viewPage;
    document.forms[0].optJspTag.value  = "HZ";
    document.forms[0].submit();
    return true;
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
            <jsp:param name="name" value="代征代扣代缴汇总" />
		<jsp:param name="help_url" value="help/wssb/sbzl/dsdzdk/dsdzdk-002.htm"/>
        </jsp:include>

        <table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td valign="top">
         <br>
          <html:errors/>

<table align="center" cellspacing="0" class="table-99" border="0">
        <tr class="black9">
          <td class="1-td1">代售代征代扣税款汇总信息</td>
        </tr>
        <tr>
          <td valign="top" class="1-td2">
            <br>
            <table cellspacing="0" class="table-99">
               <tr class="black9">
                <td align="center" nowrap><div align="left">税款所属日期：
                   &nbsp;<shenbao:write name="dsdzdkForm" property="skssksrq"/>
                   至
                   &nbsp;<shenbao:write name="dsdzdkForm" property="skssjsrq"/></div>
                </td>
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
            <table width="100%" cellspacing=0 class="table-99">
              <tr>
                <td valign=top nowrap class="2-td1-left">序号</td>
                <td valign=top nowrap class="2-td1-left">汇总单号</td>
                <td valign=top nowrap class="2-td1-left">缴款凭证号</td>
                <td valign=top nowrap class="2-td1-center">实缴金额</td>
              </tr>
        <% int i=1; %>
<% com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.web.DsdzdkForm form =  (com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.web.DsdzdkForm)session.getAttribute("dsdzdkForm");
    int k =form.getHzpageindex();
%>
<logic:iterate id="hzItem" indexId="index" name="dsdzdkForm" property="hzInforList" scope="session">
              <tr>
                <td  nowrap class="2-td2-left"> <%= (k-1)*10 + i++ %></td>
                <td  nowrap class="2-td2-left"><div align="left">&nbsp;<bean:write name="hzItem" property="sbhzdh"/></div></td>
                <td  nowrap class="2-td2-left">
                  <div align="left">&nbsp;
                    <a href='dsdzdk.do?actionType=Print&sbbhIndex=<bean:write name="hzItem" property="sbbh"/>&jkshIndex=<bean:write name="hzItem" property="jkpzh"/>&src=BacktoHz'><bean:write name="hzItem" property="jkpzh"/></a>
                  </div>
                </td>
                <td  nowrap class="2-td2-center"><div align="right"><bean:write name="hzItem" property="sjse"/>&nbsp;</div></td>
              </tr>
</logic:iterate>
              <tr>
                <td class="2-td2-left">合计</td>
                <td valign=top class="2-td2-left">&nbsp;</td>
                <td valign=top class="2-td2-left">&nbsp;</td>
                <td valign=top class="2-td2-center"><div align="right"><bean:write name="dsdzdkForm" property="sjjehj"/>&nbsp;</div></td>
              </tr>
            </table>
            <br>
    <html:form method="POST" action="/dsdzdk.do">
    <html:hidden property = "actionType" />
        <html:hidden property = "viewPageType" />
    <html:hidden property = "optJspTag" />
        <input type="hidden" name="curpage" value=<bean:write name="dsdzdkForm" property="hzpageindex"/>>
    <input type="hidden" name="maxpage" value=<bean:write name="dsdzdkForm" property="hzmaxpageindex"/>>
    <table  class="table-99">
      <tr class="black9"><td align="right">
            第(<bean:write name="dsdzdkForm" property="hzpageindex"/>/<bean:write name="dsdzdkForm" property="hzmaxpageindex"/>)页
			<img src="<%=static_contextpath%>images/diyiye1.jpg" onmouseover="this.src='<%=static_contextpath%>images/diyiye2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/diyiye1.jpg'" alt="第一页" onclick="splitPage('FIRST')" style="cursor:hand">

			<img src="<%=static_contextpath%>images/shangyiye1.jpg" onmouseover="this.src='<%=static_contextpath%>images/shangyiye2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/shangyiye1.jpg'" alt="上一页" onclick="splitPage('FORWARD')" style="cursor:hand">

			<img src="<%=static_contextpath%>images/xaiyiye1.jpg" onmouseover="this.src='<%=static_contextpath%>images/xaiyiye2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/xaiyiye1.jpg'" alt="下一页" onclick="splitPage('NEXT')" style="cursor:hand">

			<img src="<%=static_contextpath%>images/zuimoye1.jpg" onmouseover="this.src='<%=static_contextpath%>images/zuimoye2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/zuimoye1.jpg'" alt="最末页" onclick="splitPage('LAST')" style="cursor:hand">
          </td></tr>
    </table>
    </html:form>
    <html:form method="POST" action="/dsdzdk.do">
        <html:hidden property = "actionType" />
        <table>
          <tr>
		    <td align="center">
				<img src="<%=static_contextpath%>images/b-cxhz1.jpg" onmouseover="this.src='<%=static_contextpath%>images/b-cxhz2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/b-cxhz1.jpg'" alt="撤消汇总" onclick="queryHz()" style="cursor:hand">
			  &nbsp;&nbsp;&nbsp;&nbsp;
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