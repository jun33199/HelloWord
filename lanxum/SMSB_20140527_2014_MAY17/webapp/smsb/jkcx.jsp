<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.jkcx.web.JkcxForm"%>
<%@ page import="com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb" %>
<%@ page import="com.ttsoft.bjtax.smsb.util.JspUtil" %>
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>
<head>
<title>综合申报查询</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/function.js"></script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<%@ include file="./include/header.jsp"%>
<%
    JkcxForm form = (JkcxForm)request.getAttribute("jkcxForm");
    int pgLength = form.getPageLength();
    int pgSum = form.getPgSum();
    int pgNum = form.getPgNum();
    int start = pgLength*(pgNum-1);
    int Length = String.valueOf(pgSum).length();
%>

<html:form action="/webapp/smsb/jkcxAction.do" method="POST" >
<html:hidden property="actionType" />
<html:hidden property="pgNum" />
<html:hidden property="pageLength" />
<html:hidden property="pgSum" />

<table width="96%" align="center" cellspacing="0" class="table-99">
  <tr>
    <td class="1-td1">综合申报查询</td>
  </tr>
  <tr>
    <td class="1-td2">
      <br>
      <table width="98%" border="0" cellpadding="5" cellspacing="0">
        <tr class="black9">
          <td nowrap class="2-td2-t-left">
            <div align="right">计算机代码(<FONT SIZE="" COLOR="#FF0000">*</FONT>)&nbsp;&nbsp;
            </div>
          </td>
          <td nowrap class="2-td2-t-left">
            <div align="left">&nbsp;&nbsp;
              <html:text property="jsjdm" size="8" maxlength="8"/>
            </div>
          </td>
          <td nowrap class="2-td2-t-left">
            <div align="right">税款类型&nbsp;&nbsp;
            </div>
          </td>
          <td nowrap class="2-td2-t-left">
            <div align="left">&nbsp;&nbsp;
              <html:select name="jkcxForm" property="sklx">
                <option value="0">全部</option>
                <option value="1">正常</option>
                <option value="2">四代解缴</option>
                <option value="3">跨区税款</option>
                <option value="4">自查补税</option>
                <option value="5">检查补税</option>
                <option value="6">纳税评估税款</option>
                <option value="7">补缴欠税</option>
                <option value="8">行政处罚税款</option>
              </html:select>
            </div>
          </td>
          <td nowrap class="2-td2-t-left"><div align="right">申报日期(<FONT SIZE="" COLOR="#FF0000">*</FONT>)&nbsp;&nbsp;</div></td>
          <td nowrap class="2-td2-t-center">
            <div align="left">&nbsp;&nbsp;
              <html:text property="sbrq" size="6" maxlength="6"/>(YYYYmm)
            </div>
          </td>
        </tr>
      </table>
      <table width="98%" border="0" cellpadding="5" cellspacing="0">
        <tr  class="black9">
          <td colspan=3>带(<FONT SIZE="" COLOR="#FF0000">*</FONT>)的为必填项。对综合申报表的业务关联值即为申报序号。</td>
        </tr>
      </table>
      <br>
      <table width="94%" border="0" cellpadding="0" cellspacing="4">
        <tr  class="black9">
          <td width="20%">&nbsp;</td>
          <td width="20%" align="center"><input type="image" accesskey="c" tabIndex="-1" onClick="doQuery(); return false;"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="查询" id="bc1" src="<%=static_contextpath%>images/chaxun1.jpg" width="79" height="22"></td>
          <td width="20%" align="center"><input type="image" accesskey="t" tabIndex="-1" onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc','','<%=static_contextpath%>images/tc-c1.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22"></td>
          <td width="20%">&nbsp;</td>
        </tr>
      </table>
      <br>
      <table width="94%" border="0" cellpadding="0" cellspacing="0" class="table-99">
        <tr>
          <td align="center" class="1-td1" nowrap>＝＝＝＝＝查询结果＝＝＝＝＝</td>
        </tr>
        <tr class="black9">
          <td class="1-td2">
            <table cellpadding="3" width="100%">
              <tr class="black9">
                <td align="right">
                  <!--
                  共 <bean:write name="jkcxForm" property="totalRecord"/> 条记录 &nbsp;&nbsp;
                  第(<input type="text" name="gotoPG" value="<%=pgNum%>" size="<%=Length%>" onchange="doGotoPG()">/<%=pgSum%>)页
                  <%if(pgNum > 1 ) {%>
                    <img src="<%=static_contextpath%>images/diyiye1.jpg" alt="第一页" id="first" onMouseOver="MM_swapImage('first','','<%=static_contextpath%>images/diyiye2.jpg',1)" onMouseOut="MM_swapImage('first','','<%=static_contextpath%>images/diyiye1.jpg',1)" onclick="goToPage('1');return false;" style="cursor:hand">
                    <img src="<%=static_contextpath%>images/shangyiye1.jpg" alt="上一页" id="backward" onMouseOver="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye1.jpg',1)" onclick="goToPage('<%=pgNum-1%>');return false;" style="cursor:hand">
                  <%}%>
                  <%if(pgNum < pgSum) {%>
                    <img src="<%=static_contextpath%>images/xaiyiye1.jpg" alt="下一页" id="forward" onMouseOver="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye1.jpg',1)" onclick="goToPage('<%=pgNum+1%>');return false;" style="cursor:hand">
                    <img src="<%=static_contextpath%>images/zuimoye1.jpg" alt="最末页" id="last" onMouseOver="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye1.jpg',1)" onclick="goToPage('<%=pgSum%>');return false;" style="cursor:hand">
                  <%}%>
                  -->
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr class="black9">
          <td align="right" colspan=2 nowrap>
            <table width="100%" border="0" cellpadding="0" cellspacing="0"  align="center">
              <tr>
                 <td class="2-1-td1-left" width="10%">申报序号</td>
                 <td class="2-1-td1-left" >申报日期</td>
                 <td class="2-1-td1-left" >税票号</td>
                 <td class="2-1-td1-left" >税款类型</td>
                 <td class="2-1-td1-left" >实缴金额</td>
                 <td class="2-1-td1-left" >缴款日期</td>
                 <td class="2-1-td1-left" >入库日期</td>
                 <td class="2-1-td1-center" >&nbsp;</td>
              </tr>
              <bean:define id="dataList" name="jkcxForm" property="dataList"/>
              <logic:iterate name="dataList" indexId="index" id="item" type="com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb">
              <tr>
                 <td class="2-1-td1-left" width="10%">&nbsp;
                   <a href="jkcxAction.do?actionType=Detail&sbbh=<%=item.getSbbh()%>&index=<%=index.intValue()%>&jsjdm=<%=form.getJsjdm()%>" target='_blank'><%=JspUtil.format(item.getSbbh())%></a>
                 </td>
                 <td class="2-1-td1-left" >&nbsp;<%=JspUtil.format(item.getSbrq())%></td>
                 <td class="2-1-td1-left" >&nbsp;<%=item.getSphm()%></td>
                 <td class="2-1-td1-left" >&nbsp;<%=item.getSklxmc()%></td>
                 <td class="2-1-td1-left" >&nbsp;<%=JspUtil.format(item.getSjje())%></td>
                 <td class="2-1-td1-left" >&nbsp;<%=JspUtil.format(item.getJksj())%></td>
                 <td class="2-1-td1-left" >&nbsp;<%=JspUtil.format(item.getZyrq())%></td>
                 <td class="2-1-td1-center">&nbsp;
                   <%
                   if(item.getJksj()==null &&item.getZyrq()==null)
                   {
                   %>
                   <a href="jkcxAction.do?actionType=Print&jkpzh=<%=item.getJkpzh()%>&jsjdm=<%=form.getJsjdm()%>&sjly=<%=item.getSjly()%>&sbbh=<%=item.getSbbh()%>" target='_blank'>打印</a>
                   <%
                   }
                   %>
                 </td>
              </tr>
              </logic:iterate>
            </table>
          </td>
        </tr>
        <tr class="black9">
          <td class="1-td2" align="right"  colspan=2 >
            <!--
            <table cellpadding="3" align="right">
              <tr class="black9">
                <td align="right">第(<input type="text" name="gotoPG" value="<%=pgNum%>" size="<%=Length%>" onchange="doGotoPG()">/<%=pgSum%>)页
                  <%if(pgNum > 1 ) {%>
                    <img src="<%=static_contextpath%>images/diyiye1.jpg" alt="第一页" id="first" onMouseOver="MM_swapImage('first','','<%=static_contextpath%>images/diyiye2.jpg',1)" onMouseOut="MM_swapImage('first','','<%=static_contextpath%>images/diyiye1.jpg',1)" onclick="goToPage('1');return false;" style="cursor:hand">
                    <img src="<%=static_contextpath%>images/shangyiye1.jpg" alt="上一页" id="backward" onMouseOver="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye1.jpg',1)" onclick="goToPage('<%=pgNum-1%>');return false;" style="cursor:hand">
                  <%}%>
                  <%if(pgNum < pgSum) {%>
                    <img src="<%=static_contextpath%>images/xaiyiye1.jpg" alt="下一页" id="forward" onMouseOver="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye1.jpg',1)" onclick="goToPage('<%=pgNum+1%>');return false;" style="cursor:hand">
                    <img src="<%=static_contextpath%>images/zuimoye1.jpg" alt="最末页" id="last" onMouseOver="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye1.jpg',1)" onclick="goToPage('<%=pgSum%>');return false;" style="cursor:hand">
                  <%}%>
                </td>
              </tr>
            </table>
            -->
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</html:form>

<%@ include file="./include/footer.jsp"%>
</body>
<script language="JavaScript">
function goToPage(pgNum) {
  document.all.pgNum.value = pgNum;
  doSubmitForm('Query');
}

function doGotoPG() {
  var gotoPG = document.all.gotoPG;
  var thisPG = document.all.pgNum;
  if(!fnIsIntNum(gotoPG.value)){
    gotoPG.value=thisPG.value;
    return;
  }
  if(parseInt(gotoPG.value)>parseInt(document.all.pgSum.value)){
    gotoPG.value=thisPG.value;
    return;
   }
  if(gotoPG.value!=thisPG.value) {
    thisPG.value = gotoPG.value;
    doSubmitForm('Query');
  }
//  return;
}
//查询提交
function doQuery()
{
    if (document.forms[0].jsjdm.value.length != 8)
    {
      alert("计算机代码必须为8位！");
      return false;
    }
    document.forms[0].actionType.value = "Query";
    document.forms[0].target="_self";
    document.forms[0].submit();
}

function showOneItem(lsh)
{
//    document.forms[0].oneItemLsh.value = lsh;
    document.forms[0].actionType.value = "OneItem";
    document.forms[0].target="_blank";
    document.forms[0].submit();
}

function doQuXiao()
{
    document.forms[0].actionType.value = "Show";
    document.forms[0].target="_self";
    document.forms[0].submit();
}
</script>
</html:html>
