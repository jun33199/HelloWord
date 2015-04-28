<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<head>
<title>
批量统计明细
</title>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
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
          <td class=1-td1>批量信息-统计明细</td></TR>
        <TR>
          <td class=1-td2 vAlign=top>
<html:form action="/plsl/pltjDetail.do">
    <input type="hidden" name="operationType" value="">
    <html:hidden name="queryPlcxMxForm" property="pch"/>
     <html:hidden name="queryPlcxMxForm" property="zt"/>
     
          <logic:equal name="queryPlcxMxForm" scope="session" property="status" value="Result">
              <input type="hidden" name="changePage" value='<bean:write name="queryPlcxMxForm" property="queryCache.currentPageNum"/>'>
              <input type="hidden" name="pageCount" value='<bean:write name="queryPlcxMxForm" property="queryCache.pageCount"/>'>
          <table cellspacing="0" class="table-99">
            <tr>
            <td class="2-td2-t-left">查询结果</td>
           <!--如果结果集为空，提示没有记录-->
           <logic:equal name="queryPlcxMxForm" property="queryCache.pageCount" scope="session" value="0">
            <td class="2-td2-t-center"> <div align="center">没有你要的记录，请重新查询</div></td>
           </logic:equal>
           <logic:notEqual name="queryPlcxMxForm" property="queryCache.pageCount" scope="session" value="0">
              <td class="2-td2-t-center"> <div align="right">
               共<bean:write name="queryPlcxMxForm" property="queryCache.countNumber"/>条记录
               第<bean:write name="queryPlcxMxForm" property="queryCache.currentPageNum"/>/
               <bean:write name="queryPlcxMxForm" property="queryCache.pageCount"/>页
                  <input type="image" name="beginpage" value="第一页" alt="第一页"
                  onclick = "javascript:return FN_QSChangePage('diyiye');"
                  onMouseOver="MM_swapImage('diyiye','','<%=static_file%>images/diyiye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg" id="diyiye">
                  <input type="image" name="endpage" value="最后一页" alt="最后一页"
                  onclick = "javascript:return FN_QSChangePage('zuihouyiye');"
                  onMouseOver="MM_swapImage('zuimoye','','<%=static_file%>images/zuimoye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg" id="zuimoye" >
                <!--如果只有一页，下面按钮不显示-->
                <logic:equal name="queryPlcxMxForm" property="queryCache.isOnePage" scope="session" value="false">
                  <!--如果是第一页，前页不显示-->
                  <logic:equal name="queryPlcxMxForm" property="queryCache.hasPrev" scope="session" value="true">
                    <input type="image" name="frontpage" value="前页" alt="前页"
                    onclick = "javascript:return FN_QSChangePage('shangyiye');"
                    onMouseOver="MM_swapImage('shangyiye','','<%=static_file%>images/shangyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg" id="shangyiye">
                  </logic:equal>
                  <!--如果是最后一页，后页不显示-->
                  <logic:equal name="queryPlcxMxForm" property="queryCache.hasNext" scope="session" value="true">
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
          <logic:notEqual name="queryPlcxMxForm" property="queryCache.pageCount" scope="session" value="0">
          <br>
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="10%">批次号</td>
              <td class="2-td1-left" width="10%">状态信息</td>
              <td class="2-td1-left" width="10%">申报表号</td>
              <td class="2-td1-left" width="10%">纳税人名称</td>
              <td class="2-td1-left" width="10%">纳税人计算机代码</td>
              <td class="2-td1-left" width="10%">房地产项目名称</td>
              <td class="2-td1-left" width="10%">房地产地址</td>
              <td class="2-td1-left" width="10%">合同签订日期</td>
              <td class="2-td1-left" width="10%">计税金额</td>
              <td class="2-td1-center" width="10%">应纳税额</td>
          </tr>
      <logic:iterate id="data" indexId="index" length="length" name="queryPlcxMxForm" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.model.bo.PlcxMxBo">
            <tr>
              <td class="2-td2-left"><div align="left">
                    <bean:write name="data" property="pch"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="zt"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="sbbh"/>
              </td>
              <td class="2-td2-left">
                 <bean:write name="data" property="nsrmc"/>
              </td>
              <td class="2-td2-left">
                  <bean:write name="data" property="jsjdm"/>
              </td>
              <td class="2-td2-left">
                  <bean:write name="data" property="fdcxmmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="fdcdz"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="htqdrq"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="jsje"/>
              </td>
              <td class="2-td2-center">
              &nbsp;<bean:write name="data" property="ynse"/>
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
               <logic:equal name="queryPlcxMxForm" property="queryCache.isOnePage" scope="session" value="false">
                 <!--如果是第一页，前页不显示-->
                  <logic:equal name="queryPlcxMxForm" property="queryCache.hasPrev" scope="session" value="true">
                <input type="image" name="frontpage" value="前页" alt="前页"
                onclick = "javascript:return FN_QSChangePage('shangyiye');"
                onMouseOver="MM_swapImage('shangyiye1','','<%=static_file%>images/shangyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg"
                width="79" height="22" id="shangyiye1">
                 </logic:equal>
                 <!--如果是最后一页，后页不显示-->
                  <logic:equal name="queryPlcxMxForm" property="queryCache.hasNext" scope="session" value="true">
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
            <IMG name="toexcel"
          onMouseOver="MM_swapImage('toexcel1','','<%=static_file%>images/b-bcdexcel2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/b-bcdexcel1.jpg"
          onclick = "doSubmitForm('Export');"
           height="22" id="toexcel1" alt="保存到Excel" style="cursor:hand">
          &nbsp;
    
    
    <IMG name="back"
          onclick="doSubmitForm('Back');"
          onMouseOver="MM_swapImage('fanhui1','','<%=static_file%>images/fanhui2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/fanhui1.jpg"
          width="79" height="22" id="fanhui1" alt="返回" style="cursor:hand">
   &nbsp;
    <IMG name="back"
          onclick="doSubmitForm('Return');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
