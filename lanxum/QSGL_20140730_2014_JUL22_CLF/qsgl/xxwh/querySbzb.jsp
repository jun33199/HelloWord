<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.SbState"%>
<%@ page import="com.creationstar.bjtax.qsgl.model.bo.QuerySbzbBo"%>


<HTML><HEAD><TITLE>申报状态信息查询表</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.forms[0].operationType.value=operationType;
  document.forms[0].submit();
}
function fnDel()
{
    for(var i=0;i<document.forms[0].elements.length;i++)
    {
        var e = document.forms[0].elements[i];
        if(e.checked)
            break;
    }
    if(i>=document.forms[0].elements.length)
    {
        alert("请您先选择要删除的项");
        return false;
    }
    // 判断是否选择了删除对象
    if(confirm("是否删除所选记录？"))
    {
       document.forms[0].operationType.value="Delete";
       document.forms[0].submit();
         return true;
    }
    return false;
}
</SCRIPT>


<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="信息维护&gt;申报状态信息查询表"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<SCRIPT language=javascript>
<!--



//-->
</SCRIPT>
<br>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>申报状态信息查询表</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/xxwh/querySbzb.do">
            <input type = "hidden" name = "operationType" value = "" >
            <html:hidden property="fromPage" value="0"/>
            <logic:equal name="querySbzbForm" property="status" scope="session" value="Result">
                <input type="hidden" name="changePage" value='<bean:write name="querySbzbForm" property="queryCache.currentPageNum"/>'>
                <input type="hidden" name="pageCount" value='<bean:write name="querySbzbForm" property="queryCache.pageCount"/>'>
            </logic:equal>
            <br>
             <TABLE border="0" cellSpacing=0 class=table-89>
              <TBODY>
               <tr>
                <td class="2-td2-t-left">申报表号</td>
                <td class="2-td2-t-center">
                    <div align=left>
                      <html:text property="sbbh" size="20" maxlength="20"/><FONT color=red>*</FONT>
                    </div>
                </td>
              </tr>


     </TABLE><BR>

      <DIV align=center>
      <IMG name="query"
          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
          onclick = "doSubmitForm('Query');"
          width="79" height="22" id="chaxun1" alt="查询" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;

        <IMG name="back"
          onclick="doSubmitForm('Return');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">
      </DIV><BR>


 <logic:equal name="querySbzbForm" property="status" scope="session" value="Result">
	<table cellspacing="0" class="table-99">
 	  <tr>
		<td><hr class="hr1"></td>
		<td width="140" class="b-black10">
			<div align="center">申报状态信息查询结果</div></td>
		<td><hr class="hr1"></td>
        <!--如果结果集为空，提示没有记录-->
           <logic:equal name="querySbzbForm" property="queryCache.pageCount" scope="session" value="0">
            <td class="2-td2-t-center"> <div align="center">没有你要的记录，请重新查询</div></td>
           </logic:equal>
           <logic:notEqual name="querySbzbForm" property="queryCache.pageCount" scope="session" value="0">
              <td class="2-td2-t-center"> <div align="right">
               共<bean:write name="querySbzbForm" property="queryCache.countNumber"/>条记录
               第<bean:write name="querySbzbForm" property="queryCache.currentPageNum"/>/
               <bean:write name="querySbzbForm" property="queryCache.pageCount"/>页
                  <input type="image" name="beginpage" value="第一页" alt="第一页"
                  onclick = "javascript:return FN_QSChangePage('diyiye');"
                  onMouseOver="MM_swapImage('diyiye','','<%=static_file%>images/diyiye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg" id="diyiye">
                  <input type="image" name="endpage" value="最后一页" alt="最后一页"
                  onclick = "javascript:return FN_QSChangePage('zuihouyiye');"
                  onMouseOver="MM_swapImage('zuimoye','','<%=static_file%>images/zuimoye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg" id="zuimoye" >
                <!--如果只有一页，下面按钮不显示-->
                <logic:equal name="querySbzbForm" property="queryCache.isOnePage" scope="session" value="false">
                  <!--如果是第一页，前页不显示-->
                  <logic:equal name="querySbzbForm" property="queryCache.hasPrev" scope="session" value="true">
                    <input type="image" name="frontpage" value="前页" alt="前页"
                    onclick = "javascript:return FN_QSChangePage('shangyiye');"
                    onMouseOver="MM_swapImage('shangyiye','','<%=static_file%>images/shangyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg" id="shangyiye">
                  </logic:equal>
                  <!--如果是最后一页，后页不显示-->
                  <logic:equal name="querySbzbForm" property="queryCache.hasNext" scope="session" value="true">
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
          <logic:notEqual name="querySbzbForm" property="queryCache.pageCount" scope="session" value="0">
          <br>


     <TABLE border="0" cellSpacing=0 class=table-99>
      <TBODY>
       <TR>

        <td class="2-td1-left" >删除标记</td>

       	<TD class="2-td1-left" width = "11%" >申报表号</TD>
        <TD class="2-td1-left" width = "11%" >申报日期</TD>
        <TD class="2-td1-left" width = "11%" >申报状态</TD>
        <TD class="2-td1-left" width = "11%" >是否个人</TD>
        <TD class="2-td1-left" width = "11%" >是否录入房土信息</TD>
        <TD class="2-td1-left" width = "11%" >是否录入拆迁信息</TD>
        <TD class="2-td1-left" width = "11%" >是否录入公有住房信息</TD>
        <TD class="2-td1-center" width = "11%" >是否录入房屋交换信息</TD>
      </TR>

      <logic:iterate id="data" indexId="index" length="length" name="querySbzbForm" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.model.bo.QuerySbzbBo">
            <tr>

                    <td class="2-td2-left">
                        <input type="checkbox" name="selectedIndex"
                           onclick="FN_setChkState()" value="<%=index.intValue()%>">
                    </td>

              <td class="2-td2-left">
                  <bean:write name="data" property="sbbh"/>
              </td>
              <td class="2-td2-left" style="word-break:break-all">

                  <%=DataConvert.TimeStamp2String(data.getSbrq())%>
              </td>
              <td class="2-td2-left">
                  <%=SbState.getStateName(data.getZtbs())%>
              </td>
              <td class="2-td2-left" style="word-break:break-all">
                 <%=data.getBooleanName(data.isPerson())%>

              </td>
              <td class="2-td2-left" style="word-break:break-all">
                 <%=data.getBooleanName(data.isExistFtxx())%>

              </td>
              <td class="2-td2-left">
                 <%=data.getBooleanName(data.isExistCqxx())%>

              </td>
               <td class="2-td2-left" style="word-break:break-all">
                  <%=data.getBooleanName(data.isExistGyzf())%>

              </td>
              <td class="2-td2-center">
                  <%=data.getBooleanName(data.isExistFwjh())%>
              </td>

            </tr>
      </logic:iterate>
          </table>
          <br>
       <table cellspacing="1" class="table-99">
          <tr>

                    <td class="2-td2-t-left"><div align="left">&nbsp;
                        <input type="checkbox" name="chkall" value="checkbox" onclick="javascript:All_quanxuan();">
                        是否全选&nbsp;
                        <input type="image" name="delete" value="删除" alt="删除"
                            onclick = "javascript:return fnDel();"
                            onMouseOver="MM_swapImage('shanchu','','<%=static_file%>images/shanchu2.jpg',1)"
                            onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shanchu1.jpg"
                            width="79" height="22" id="shanchu"></div>
                    </td>

            <td class="2-td2-t-center"><div align="right"><br>
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
               <logic:equal name="querySbzbForm" property="queryCache.isOnePage" scope="session" value="false">
                 <!--如果是第一页，前页不显示-->
                  <logic:equal name="querySbzbForm" property="queryCache.hasPrev" scope="session" value="true">
                <input type="image" name="frontpage" value="前页" alt="前页"
                onclick = "javascript:return FN_QSChangePage('shangyiye');"
                onMouseOver="MM_swapImage('shangyiye1','','<%=static_file%>images/shangyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg"
                width="79" height="22" id="shangyiye1">
                 </logic:equal>
                 <!--如果是最后一页，后页不显示-->
                  <logic:equal name="querySbzbForm" property="queryCache.hasNext" scope="session" value="true">
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


</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
