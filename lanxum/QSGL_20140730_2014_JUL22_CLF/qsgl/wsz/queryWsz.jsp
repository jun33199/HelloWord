<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz"%>


<HTML><HEAD><TITLE>查询收现缴款书表</TITLE>
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
  if(operationType=="query" )
  {
    document.forms[0].operationType.value='Query';
    if(document.forms[0].zb.value=="" && document.forms[0].wszqshm.value==""
       && document.forms[0].wszjzhm.value=="" && document.forms[0].wszjzhm.value==""
       && document.forms[0].tfqsrq.value=="" && document.forms[0].tfjzrq.value=="" 
       && document.forms[0].nsrmc.value=="")
    {
        alert("请至少输入一项查询条件！");
     	document.forms[0].zb.focus();
    	return false;
    }
  }
  if(operationType=="quit")
  {
    document.forms[0].operationType.value='Return';
  }

  document.forms[0].submit();
}
</SCRIPT>


<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="收现缴款书管理&gt;查询收现缴款书表"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<html:form action="/wsz/queryWsz.do">
<html:hidden property="operationType"/>
            <logic:equal name="queryWszForm" property="status" scope="session" value="Result">
                <input type="hidden" name="changePage" value='<bean:write name="queryWszForm" property="queryCache.currentPageNum"/>'>
                <input type="hidden" name="pageCount" value='<bean:write name="queryWszForm" property="queryCache.pageCount"/>'>
            </logic:equal>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <table align="center" cellspacing="0" class="table-99">
	  <TBODY>
        <tr>
          <td class="1-td1">查询收现缴款书</a></td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
            <table cellspacing="0" class="table-99">
              <tr>
                <td class="2-td2-t-left">字别</td>
                <td class="2-td2-t-left">
                    <html:text property="zb" />
                </td>
                <td class="2-td2-t-left">纳税人名称</td>
                <td class="2-td2-t-center"><html:text property="nsrmc" /></td>
              </tr>
              <tr>
                <td class="2-td2-left">收现缴款书起始号码</td>
                <td  class="2-td2-left">
                    <html:text property="wszqshm" />
                </td>
                <td class="2-td2-left">收现缴款书截止号码</td>
                <td  class="2-td2-center">
                   <html:text property="wszjzhm" />
                </td>
              </tr>
              <tr>
                <td class="2-td2-left">填发起始日期</td>
                <td  class="2-td2-left">
                    <html:text property="tfqsrq" />
                </td>
                <td class="2-td2-left">填发截止日期</td>
                <td  class="2-td2-center">
                   <html:text property="tfjzrq" />
                </td>
              </tr>

 
            </table>            <br>

      <DIV align=center>
      <IMG name="query"
          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
          onclick = "doSubmitForm('query');"
          width="79" height="22" id="chaxun1" alt="查询" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;

        <IMG name="back"
          onclick="doSubmitForm('quit');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">
      </DIV><BR>





 <logic:equal name="queryWszForm" property="status" scope="session" value="Result">
	<table cellspacing="0" class="table-99">
 	  <tr>
		<td><hr class="hr1"></td>
		<td width="140" class="b-black10">
			<div align="center">收现缴款书查询结果</div></td>
		<td><hr class="hr1"></td>
        <!--如果结果集为空，提示没有记录-->
           <logic:equal name="queryWszForm" property="queryCache.pageCount" scope="session" value="0">
            <td class="2-td2-t-center"> <div align="center">没有你要的记录，请重新查询</div></td>
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
        <TD class="2-td1-left" width = "8%" >收现缴款书号</TD>
        <TD class="2-td1-left" width = "8%" >字别</TD>
        <TD class="2-td1-left" width = "12%" >申报表号</TD>
        <TD class="2-td1-left" width = "8%" >是否汇总</TD>
        <TD class="2-td1-left" width = "7%" >打印状态</TD>
        <TD class="2-td1-left" width = "10%" >缴款凭证号</TD>
        <TD class="2-td1-left" width = "10%" >实纳金额</TD>
        <TD class="2-td1-left" width = "12%" >填发日期</TD>
        <TD class="2-td1-left" width = "8%" >纳税人代码</TD>
        <TD class="2-td1-left" width = "10%" >征收点名称</TD>
        <TD class="2-td1-center" width = "8%" >征收人员</TD>
      </TR>
      <logic:iterate id="data" indexId="index" length="length" name="queryWszForm" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.model.bo.QueryWszBo">

      <bean:define id="data1" name="data" property="qswszzVo" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz"/>

            <tr>
               <!-- 完税证号码-->
              <td class="2-td2-left">
              <a href="<%=response.encodeURL("/qsgl/wsz/viewWsz.do?operationType=Show&viewIndex=") + index.intValue()%>">
                  <bean:write name="data1" property="wszh"/>
              </a>
              </td>
              <!--  字别  -->
              <td class="2-td2-left">
                  <bean:write name="data1" property="ndzb"/>
              </td>
              <!-- 申报表号  -->
              <td class="2-td2-left" style="word-break:break-all">
                 &nbsp; <bean:write name="data1" property="sbbh"/>
              </td>
              <!--  是否汇总 -->
              <td class="2-td2-left">
              <%
              		if(data1.getSbhzdh()!=null&&!data1.getSbhzdh().equals(""))
              {%>
                  已汇总
               <%}
               else
               {%>
                  <font color="red">未汇总</font>
               <% } %>

              </td>
              <!-- 打印状态  -->
              <td class="2-td2-left">
              <%
              if(Integer.parseInt(Constants.WSZ_CLBJDM_YWS) <= (Integer.parseInt(data1.getClbjdm())))
              {%>
                  已打印
              <%}
               else
               {
                   String url = "/qsgl/wsz/updateWszStatus.do?operationType=UpdateState&wszh=" + ((Qswszz)data1).getWszh()  + "&zb=" + ((Qswszz)data1).getNdzb() + "&pzzldm=" + ((Qswszz)data1).getPzzldm();
                   %>
                  <font color="red">未打印</color><br>
                  <a href="<%=response.encodeURL(url)%>">置为已打印</a>
               <% } %>
               <!--  缴款凭证号 -->
              <td class="2-td2-left">
              <%
              		if(data1.getJkpzh()!=null && !data1.getJkpzh().equals(""))
              {%>
                  <bean:write name="data1" property="jkpzh"/>
               <%}
               else
               {%>
                  --
               <% } %>

              </td>
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


</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
