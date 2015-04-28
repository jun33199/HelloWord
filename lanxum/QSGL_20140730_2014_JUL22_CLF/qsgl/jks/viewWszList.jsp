<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>

<HTML><HEAD><TITLE>缴款书中被汇总的收现缴款书</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType)
{
  document.all.operationType.value=operationType;
  document.forms[0].submit();
}
</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="缴款书管理&gt;查询缴款书&gt;收现缴款书列表"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<html:form action="/jks/viewWszList.do">
<html:hidden property="operationType"/>
<logic:equal name="queryWszForm" property="status" scope="session" value="Result">
    <input type="hidden" name="changePage" value='<bean:write name="queryWszForm" property="queryCache.currentPageNum"/>'>
    <input type="hidden" name="pageCount" value='<bean:write name="queryWszForm" property="queryCache.pageCount"/>'>
    <input type="hidden" name="yuan" value='<bean:write name="queryWszForm" property="yuan"/>'>
</logic:equal>


<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
   <TD vAlign=top>
      <table align="center" cellspacing="0" class="table-99">
	  <TBODY>
        <tr>
          <td class="1-td1">收现缴款书列表</a></td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
            <table cellspacing="0" border = "0">

        </table> <br>

<logic:equal name="queryWszForm" property="status" scope="session" value="Result">
	<table cellspacing="0" class="table-99">
 	  <tr>
		<td><hr class="hr1"></td>
		<td width="140" class="b-black10">
			<div align="center">收现缴款书列表</div></td>
		<td><hr class="hr1"></td>
        <!--如果结果集为空，提示没有记录-->
           <logic:equal name="queryWszForm" property="queryCache.pageCount" scope="session" value="0">
            <td class="2-td2-t-center"> <div align="center">没有找到匹配的收现缴款书!!!</div></td>
           </logic:equal>
           <logic:notEqual name="queryWszForm" property="queryCache.pageCount" scope="session" value="0">
              <td class="2-td2-t-center"> <div align="right">
               共<bean:write name="queryWszForm" property="queryCache.countNumber"/>条记录
               第<bean:write name="queryWszForm" property="queryCache.currentPageNum"/>/
               <bean:write name="queryWszForm" property="queryCache.pageCount"/>页
                  <input type="image" name="beginpage" value="第一页" alt="第一页"
                  onclick = "javascript:return FN_QSChangePage('diyiye');"
                  onMouseOver="MM_swapImage('diyiye','','<%=static_file%>images/diyiye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg" id="diyiye">
                  <input type="image" name="endpage" value="最后一页" alt="最后一页"
                  onclick = "javascript:return FN_QSChangePage('zuihouyiye');"
                  onMouseOver="MM_swapImage('zuimoye','','<%=static_file%>images/zuimoye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg" id="zuimoye" >
                <!--如果只有一页，下面按钮不显示-->
                <logic:equal name="queryWszForm" property="queryCache.isOnePage" scope="session" value="false">
                  <!--如果是第一页，前页不显示-->
                  <logic:equal name="queryWszForm" property="queryCache.hasPrev" scope="session" value="true">
                    <input type="image" name="frontpage" value="前页" alt="前页"
                    onclick = "javascript:return FN_QSChangePage('shangyiye');"
                    onMouseOver="MM_swapImage('shangyiye','','<%=static_file%>images/shangyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg" id="shangyiye">
                  </logic:equal>
                  <!--如果是最后一页，后页不显示-->
                  <logic:equal name="queryWszForm" property="queryCache.hasNext" scope="session" value="true">
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
        </logic:equal>
          <!--如果记录结果为空，不显示-->
          <logic:notEqual name="queryWszForm" property="queryCache.pageCount" scope="session" value="0">
          <br>

  <TABLE border="0" cellSpacing=0 class=table-99>
      <TBODY>
       <TR>
        <TD class="2-td1-left" width = "12%" >收现缴款书号</TD>
        <TD class="2-td1-left" width = "8%" >字别</TD>
        <TD class="2-td1-left" width = "18%" >申报表号</TD>
        <TD class="2-td1-left" width = "12%" >实纳金额</TD>
        <TD class="2-td1-left" width = "12%" >填发日期</TD>
        <TD class="2-td1-left" width = "12%" >纳税人代码</TD>
        <TD class="2-td1-left" width = "12%" >征收点名称</TD>
        <TD class="2-td1-center" width = "10%" >征收人员</TD>
      </TR>

  <logic:iterate id="data" indexId="index" length="length" name="queryWszForm" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz">

        <TR>
          <tr>
               <!-- 完税证号码-->
              <td class="2-td2-left">
                  <bean:write name="data" property="wszh"/>
              </td>
              <!--  字别  -->
              <td class="2-td2-left">
                  <bean:write name="data" property="ndzb"/>&nbsp;
              </td>
              <!-- 申报表号  -->
              <td class="2-td2-left" style="word-break:break-all">
                 <bean:write name="data" property="sbbh"/>&nbsp;
              </td>
                <!-- 实纳税额  -->
              <td class="2-td2-left">
              ￥<%=DataConvert.BigDecimal2String(data.getHjsjje())%>
              </td>
                <!--填发日期   -->
              <td class="2-td2-left" style="word-break:break-all">
                 <%=DataConvert.TimeStamp2String(data.getCjrq())%>&nbsp;
              </td>
               <!--  纳税人计算机代码 -->
              <td class="2-td2-left">
                  <bean:write name="data" property="nsrjsjdm"/>&nbsp;
              </td>
                <!-- 征收点名称  -->
              <td class="2-td2-left">
                  <bean:write name="data" property="zsdmc"/>&nbsp;
              </td>
                <!-- 征收人员  -->
              <td class="2-td2-center" style="word-break:break-all">
                 <bean:write name="data" property="lrr"/>&nbsp;
              </td>
        </TR>

  </logic:iterate>
   </table><br>

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
               <logic:equal name="queryWszForm" property="queryCache.isOnePage" scope="session" value="false">
                 <!--如果是第一页，前页不显示-->
                  <logic:equal name="queryWszForm" property="queryCache.hasPrev" scope="session" value="true">
                <input type="image" name="frontpage" value="前页" alt="前页"
                onclick = "javascript:return FN_QSChangePage('shangyiye');"
                onMouseOver="MM_swapImage('shangyiye1','','<%=static_file%>images/shangyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg"
                width="79" height="22" id="shangyiye1">
                 </logic:equal>
                 <!--如果是最后一页，后页不显示-->
                  <logic:equal name="queryWszForm" property="queryCache.hasNext" scope="session" value="true">
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
      <DIV align=center>
        <IMG name="back"
          onclick="doSubmitForm('ReturnPrePage');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">
      </DIV><BR>

</html:form>
<%@ include file="../include/Bottom.jsp" %>

</body>
</html>
