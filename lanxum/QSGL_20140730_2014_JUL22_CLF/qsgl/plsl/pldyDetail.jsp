<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<head>
<title>
批量统计明细
</title>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<script language="JavaScript" type="text/JavaScript" src="<%=static_file%>js/judge.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_file%>js/calculate.js"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</head>
<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  document.forms[0].submit();
}
//翻页操作
function FN_QSChangePage(opType)
{
    document.forms[0].operationType.value = "ChangePageDetail";
    //如果点击"第一页"
    if (opType == "diyiye")
    {
        document.forms[0].changePage.value = "1"; return true;
    }
    //如果点中"上一页"
    if (opType == "shangyiye")
    {
        document.forms[0].changePage.value = parseInt(document.forms[0].changePage.value)-1;
        return true;
    }
    //如果点中"下一页"
    if (opType == "xiayiye")
    {
        document.forms[0].changePage.value = parseInt(document.forms[0].changePage.value)+1;
        return true;
    }
    //如果点中"最后一页"
    if(opType == "zuihouyiye")
    {
        document.forms[0].changePage.value = document.forms[0].pageCount.value;
        return true;
    }
    return false;
}
</SCRIPT>
<body bgcolor="#ffffff">
<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
 <jsp:include page="/include/Header.jsp" flush="true">
                <jsp:param name="subtitle" value="批量信息&gt;批量查询"/>
                <jsp:param name="helpURL" value=""/>
              </jsp:include>
<SCRIPT language=javascript>
<!--

function popUp(){
    var server = '192.100.99.100';
    var port   = '80';
    props=window.open('<%=static_file%>' + '/about/about.htm','poppage','toolbars=0,scrollbars=0,location=0,statusbara=0,menubars=0,resizable=0,width=500,height=267');
}

//-->
</SCRIPT>
<br>
<table align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <td vAlign=top>
      <table align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <td class=1-td1>批量信息-打印明细</td></TR>
        <TR>
          <td class=1-td2 vAlign=top>
<html:form action="/plsl/pldy.do">
    <input type="hidden" name="operationType" value="">
          <logic:equal name="pldyForm" scope="session" property="status" value="Result">
              <input type="hidden" name="changePage" value='<bean:write name="pldyForm" property="queryCache.currentPageNum"/>'>
              <input type="hidden" name="pageCount" value='<bean:write name="pldyForm" property="queryCache.pageCount"/>'>
          <table cellspacing="0" class="table-99">
            <tr>
            <td class="2-td2-t-left">查询结果</td>
           <!--如果结果集为空，提示没有记录-->
           <logic:equal name="pldyForm" property="queryCache.pageCount" scope="session" value="0">
            <td class="2-td2-t-center"> <div align="center">没有你要的记录，请重新查询</div></td>
           </logic:equal>
           <logic:notEqual name="pldyForm" property="queryCache.pageCount" scope="session" value="0">
              <td class="2-td2-t-center"> <div align="right">
               共<bean:write name="pldyForm" property="queryCache.countNumber"/>条记录
               第<bean:write name="pldyForm" property="queryCache.currentPageNum"/>/
               <bean:write name="pldyForm" property="queryCache.pageCount"/>页
                  <input type="image" name="beginpage" value="第一页" alt="第一页"
                  onclick = "javascript:return FN_QSChangePage('diyiye');"
                  onMouseOver="MM_swapImage('diyiye','','<%=static_file%>images/diyiye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg" id="diyiye">
                  <input type="image" name="endpage" value="最后一页" alt="最后一页"
                  onclick = "javascript:return FN_QSChangePage('zuihouyiye');"
                  onMouseOver="MM_swapImage('zuimoye','','<%=static_file%>images/zuimoye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg" id="zuimoye" >
                <!--如果只有一页，下面按钮不显示-->
                <logic:equal name="pldyForm" property="queryCache.isOnePage" scope="session" value="false">
                  <!--如果是第一页，前页不显示-->
                  <logic:equal name="pldyForm" property="queryCache.hasPrev" scope="session" value="true">
                    <input type="image" name="frontpage" value="前页" alt="前页"
                    onclick = "javascript:return FN_QSChangePage('shangyiye');"
                    onMouseOver="MM_swapImage('shangyiye','','<%=static_file%>images/shangyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg" id="shangyiye">
                  </logic:equal>
                  <!--如果是最后一页，后页不显示-->
                  <logic:equal name="pldyForm" property="queryCache.hasNext" scope="session" value="true">
                    <input type="image" name="backpage" value="后页" alt="后页"
                    onclick = "javascript:return FN_QSChangePage('xiayiye');"
                    onMouseOver="MM_swapImage('xiayiye','','<%=static_file%>images/xaiyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/xaiyiye1.jpg" id="xiayiye">
                  </logic:equal>
                </logic:equal>
                </div></td>
             </logic:notEqual>
            </tr>
          </table>
          <!--如果记录结果为空，不显示-->
          <logic:notEqual name="pldyForm" property="queryCache.pageCount" scope="session" value="0">
          <br>
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="10%">批次号</td>
              <td class="2-td1-left" width="10%">状态信息</td>
              <td class="2-td1-left" width="10%">申报表号</td>
              <td class="2-td1-left" width="10%">计税金额</td>
              <td class="2-td1-left" width="10%">应纳税额</td>
              <td class="2-td1-left" width="10%">土地、房屋座落地址</td>
              <td class="2-td1-left" width="10%">房屋类别</td>
              <td class="2-td1-left" width="10%">房屋建筑面积</td>
              <td class="2-td1-left" width="10%">折合价格（人民币）</td>
              <td class="2-td1-center" width="10%">合同（契约）签订时间</td>
          </tr>
      <logic:iterate id="data" indexId="index" length="length" name="pldyForm" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.model.bo.PldrBo">
            <tr>
              <td class="2-td2-left"><div align="left">
                    <bean:write name="data" property="pch"/>
              </td>
              <td class="2-td2-left">
                  <logic:equal name="data" property="sbzb.ztbs" value="0">
                        已受理
                </logic:equal>
                  <logic:equal name="data" property="sbzb.ztbs" value="1">
                        受理完毕
                </logic:equal>
                  <logic:equal name="data" property="sbzb.ztbs" value="4">
                        已审核
                </logic:equal>
                  <logic:equal name="data" property="sbzb.ztbs" value="7">
                        已复核
                </logic:equal>
                  <logic:equal name="data" property="sbzb.ztbs" value="99">
                        已复核
                </logic:equal>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="sbzb.sbbh"/>
              </td>
              <td class="2-td2-left">
                  &nbsp;<%=DataConvert.double2Currency(DataConvert.BigDecimal2double(data.getSbzb().getJsje()),2)%>
              </td>
              <td class="2-td2-left">
                  &nbsp;<%=DataConvert.double2Currency(DataConvert.BigDecimal2double(data.getSbzb().getYnse()),2)%>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="tufwxx.tdfwzldz"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="tufwxx.fwlxmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="tufwxx.fwjzmj"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<%=DataConvert.double2Currency(DataConvert.BigDecimal2double(data.getTufwxx().getZhjgrmb()),2)%>
              </td>
              <td class="2-td2-center">
              &nbsp;<%=DataConvert.TimeStamp2String(data.getTufwxx().getHtqdsj())%>
              </td>
            </tr>
      </logic:iterate>
          </table>
          <br>
       <table cellspacing="0" class="table-99">
          <tr>
            <td align="right"><br>
                <input type="image" name="beginpage" value="第一页" alt="第一页"
                onclick = "javascript:return FN_QSChangePage('diyiye');"
                onMouseOver="MM_swapImage('diyiye1','','<%=static_file%>images/diyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg"
                width="79" height="22" id="diyiye1">
                <input type="image" name="endpage" value="最后一页" alt="最后一页"
                onclick = "javascript:return FN_QSChangePage('zuihouyiye');"
                onMouseOver="MM_swapImage('zuimoye1','','<%=static_file%>images/zuimoye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg"
                width="79" height="22" id="zuimoye1" >
                <!--如果只有一页，下面按钮不显示-->
               <logic:equal name="pldyForm" property="queryCache.isOnePage" scope="session" value="false">
                 <!--如果是第一页，前页不显示-->
                  <logic:equal name="pldyForm" property="queryCache.hasPrev" scope="session" value="true">
                <input type="image" name="frontpage" value="前页" alt="前页"
                onclick = "javascript:return FN_QSChangePage('shangyiye');"
                onMouseOver="MM_swapImage('shangyiye1','','<%=static_file%>images/shangyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg"
                width="79" height="22" id="shangyiye1">
                 </logic:equal>
                 <!--如果是最后一页，后页不显示-->
                  <logic:equal name="pldyForm" property="queryCache.hasNext" scope="session" value="true">
                   <input type="image" name="backpage" value="后页" alt="后页"
                   onclick = "javascript:return FN_QSChangePage('xiayiye');"
                   onMouseOver="MM_swapImage('xiayiye1','','<%=static_file%>images/xaiyiye2.jpg',1)"
                   onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/xaiyiye1.jpg"
                   width="79" height="22" id="xiayiye1">
                 </logic:equal>
                </logic:equal>
            </td>
          </tr>
        </table>
        </logic:notEqual>
      </div>
    </logic:equal>
    <IMG name="back"
          onclick="doSubmitForm('Back');"
          onMouseOver="MM_swapImage('fanhui1','','<%=static_file%>images/fanhui2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/fanhui1.jpg"
          width="79" height="22" id="fanhui1" alt="返回" style="cursor:hand">
    <IMG name="back"
          onclick="doSubmitForm('Print');"
          onMouseOver="MM_swapImage('dayin1','','<%=static_file%>images/dayin2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/dayin1.jpg"
          width="79" height="22" id="dayin1" alt="打印" style="cursor:hand">
    <IMG name="back"
          onclick="doSubmitForm('Return');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
