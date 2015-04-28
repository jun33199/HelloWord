<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>

<HTML><HEAD><TITLE>查询批量导入信息</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<script language="JavaScript" type="text/JavaScript" src="<%=static_file%>js/judge.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_file%>js/calculate.js"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType)
{
	if(operationType=="Delete"||operationType=="Check")
	{
		if(document.forms[0].chkall.checked==false)
		{
			alert("请选择要操作的对象")
			return false;
		}

	}
    if(operationType=="Query")
	{
        if(document.forms[0].drrq.value!="")
		{
            if(!checkDate(document.forms[0].drrq.value))
            {
                alert("导入日期格式不对")
                document.forms[0].drrq.focus();
                return false;
            }
		}
        if(document.forms[0].drrq.value==""&&document.forms[0].drpch.value==""
            &&document.forms[0].tgzmc.value==""
            &&document.forms[0].tgzjsjdm.value==""&&document.forms[0].nsrmc.value=="")
		{
			alert("必须至少再输入一个查询条件")
			return false;
		}
    }
    if(operationType=="Check")
	{
		if(!confirm("确认受理数据"))
		{
			return false;
		}
	}
  document.all.operationType.value=operationType;
  document.forms[0].submit();
}
</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
 <jsp:include page="/include/Header.jsp" flush="true">
                <jsp:param name="subtitle" value="批量受理&gt;查询批量导入信息"/>
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
          <td class=1-td1>批量导入信息-查询</td></TR>
        <TR>
          <td class=1-td2 vAlign=top>
            <html:form action="/plsl/plslQuery.do">
			<html:hidden property="from" value="cx"/>
            <html:hidden property="drlx" value=""/>
            <input type="hidden" name="operationType" value="">
              <logic:equal name="queryPlslForm" property="status" scope="session" value="Result">
                <input type="hidden" name="changePage" value='<bean:write name="queryPlslForm" property="queryCache.currentPageNum"/>'>
                <input type="hidden" name="pageCount" value='<bean:write name="queryPlslForm" property="queryCache.pageCount"/>'>
              </logic:equal>
            <br>
             <table border="0" cellSpacing=0 class=table-60>
              <TBODY>
               <TR>
                <td class=2-td1-center width="50%">
                  批次号&nbsp;
                </td>
                <td class="2-td2-t-right" width="50%">
                  <DIV align=left><html:text property="drpch" maxlength=""/></DIV>
                </td>
              </TR>
               <TR>
                <td class="2-td1-Bcenter">
                  状态&nbsp;
                </td>
                <td class="2-td2-right">
                  <DIV align=left>
				       <html:select name="queryPlslForm" property="sl" >
                        <html:option value="all">所有</html:option>
                        <html:option value="unreceived">未受理</html:option>
                        <html:option value="received">受理完成</html:option>
                      </html:select></DIV>
                </td>
              </TR>

               <TR>
                <td class=2-td1-Bcenter width="50%">
                  提供者名称&nbsp;
                </td>
                <td class="2-td2-right" width="50%">
                  <DIV align=left><html:text property="tgzmc" maxlength="" /></DIV>
                </td>
              </TR>
               <TR>
                <td class=2-td1-Bcenter width="50%">
                  提供者计算机代码&nbsp;
                </td>
                <td class="2-td2-right" width="50%">
                  <DIV align=left><html:text property="tgzjsjdm" maxlength="" /></DIV>
                </td>
              </TR>
               <TR>
                <td class=2-td1-Bcenter width="50%">
                  导入日期&nbsp;
                </td>
                <td class="2-td2-right" width="50%">
                  <DIV align=left><html:text property="drrq" maxlength="8" />(yyyymmdd)</DIV>
                </td>
              </TR>
               <TR>
                <td class=2-td1-Bcenter width="50%">
                  纳税人名称&nbsp;
                </td>
                <td class="2-td2-right" width="50%">
                  <DIV align=left><html:text property="nsrmc" maxlength="" /></DIV>
                </td>
              </TR>

			                <tr>
                <td class="2-td1-Bcenter">缴款方式</td>
                <td class="2-td2-right">
				<DIV align=left>
                                          <bean:define id="jsfs" name="queryPlslForm" property="jkfsList"  />
                                          <html:select property="jkfsdm">
                                            <html:options collection="jsfs" labelProperty="jsfsmc" property="jsfsdm" />
                                          </html:select>
				</DIV>
                                          <html:hidden property="jkfsmc"/>
                </td>
              </tr>
     </table><BR>

      <DIV align=center>
        <IMG name="query"
          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
          onclick = "doSubmitForm('Query');"
          width="79" height="22" id="chaxun1" alt="查询" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

     <logic:equal name="queryPlslForm" property="status" scope="session" value="Result">
        <IMG alt=删除 height=22 id=delete name="btnDelete"
            onclick="doSubmitForm('Delete');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('delete','','<%=static_file%>images/shanchu2.jpg',1)"
            src="<%=static_file%>images/shanchu1.jpg" style="CURSOR: hand" width=79>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </logic:equal>

        <IMG name="back"
          onclick="doSubmitForm('Return');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">
      </DIV><BR>

          <logic:equal name="queryPlslForm" property="status" scope="session" value="Result">
          <table cellspacing="0" class="table-99">
            <tr>
            <td class="2-td2-t-left">查询结果</td>
           <!--如果结果集为空，提示没有记录-->
           <logic:equal name="queryPlslForm" property="queryCache.pageCount" scope="session" value="0">
            <td class="2-td2-t-center"> <div align="center">没有你要的记录，请重新查询</div></td>
           </logic:equal>
           <logic:notEqual name="queryPlslForm" property="queryCache.pageCount" scope="session" value="0">
              <td class="2-td2-t-center"> <div align="right">
               共<bean:write name="queryPlslForm" property="queryCache.countNumber"/>条记录
               第<bean:write name="queryPlslForm" property="queryCache.currentPageNum"/>/
               <bean:write name="queryPlslForm" property="queryCache.pageCount"/>页
                  <input type="image" name="beginpage" value="第一页" alt="第一页"
                  onclick = "javascript:return FN_QSChangePage('diyiye');"
                  onMouseOver="MM_swapImage('diyiye','','<%=static_file%>images/diyiye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg" id="diyiye">
                  <input type="image" name="endpage" value="最后一页" alt="最后一页"
                  onclick = "javascript:return FN_QSChangePage('zuihouyiye');"
                  onMouseOver="MM_swapImage('zuimoye','','<%=static_file%>images/zuimoye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg" id="zuimoye" >
                <!--如果只有一页，下面按钮不显示-->
                <logic:equal name="queryPlslForm" property="queryCache.isOnePage" scope="session" value="false">
                  <!--如果是第一页，前页不显示-->
                  <logic:equal name="queryPlslForm" property="queryCache.hasPrev" scope="session" value="true">
                    <input type="image" name="frontpage" value="前页" alt="前页"
                    onclick = "javascript:return FN_QSChangePage('shangyiye');"
                    onMouseOver="MM_swapImage('shangyiye','','<%=static_file%>images/shangyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg" id="shangyiye">
                  </logic:equal>
                  <!--如果是最后一页，后页不显示-->
                  <logic:equal name="queryPlslForm" property="queryCache.hasNext" scope="session" value="true">
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
          <logic:notEqual name="queryPlslForm" property="queryCache.pageCount" scope="session" value="0">
          <br>
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="5%">选择</td>
              <td class="2-td1-left" width="10%">导入批次号</td>
              <td class="2-td1-left" width="9%">序号</td>
              <td class="2-td1-left" width="12%">纳税人名称</td>
              <td class="2-td1-left" width="12%">房地产项目</td>
              <td class="2-td1-left" width="12%">房地产地址</td>
              <td class="2-td1-left" width="10%">合同日期</td>
              <td class="2-td1-left" width="10%">成交价格</td>
			  <td class="2-td1-left" width="10%">缴税方式</td>
              <td class="2-td1-center" width="10%">导入时间</td>
            </tr>
      <logic:iterate id="data" indexId="index" length="length" name="queryPlslForm" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.model.bo.PlsbBo">
            <tr>
              <td class="2-td2-left">
<logic:equal name="queryPlslForm" property="sl"  value="received">
              <input type="checkbox" name="selectedIndex" disabled="true"
                     onclick="FN_setChkState()" value="<%=index.intValue()%>">
</logic:equal>序号

<logic:equal name="queryPlslForm" property="sl"  value="unreceived">
              <input type="checkbox" name="selectedIndex"
                     onclick="FN_setChkState()" value="<%=index.intValue()%>">
</logic:equal>

<logic:equal name="queryPlslForm" property="sl"  value="all">
              <input type="checkbox" name="selectedIndex" disabled="true"
                     onclick="FN_setChkState()" value="<%=index.intValue()%>">
</logic:equal>
              </td>
              <td class="2-td2-left"><div align="left">
                    <bean:write name="data" property="drzb.drpch"/></div>
              </td>
              <td class="2-td2-left"><div align="left">
			  <a href="<%=response.encodeURL("/qsgl/plsl/plsl.do?operationType=View&drlx=0&viewIndex=") + index.intValue()%>">
              &nbsp;<bean:write name="data" property="drzb.xh"/></div></a>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="drzb.nsrmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="drzb.fdcxmmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="drzb.fdcdz"/>
              </td>
              <td class="2-td2-left">
			  <bean:define id="dr" name="data" property="drzb" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb"/>
              &nbsp;<%=DataConvert.TimeStamp2String(dr.getHtqdrq())%>
              </td>
              <td class="2-td2-left">
              &nbsp; <%=DataConvert.BigDecimal2String(dr.getCjjg())%>
              </td>
              <td class="2-td2-left">
              &nbsp; <bean:write name="data" property="drpcInfo.jsfmc"/>
              </td>
              <td class="2-td2-center"><div align="left">
              &nbsp;<%=DataConvert.TimeStamp2String(dr.getDrsj())%></div>
              </td>
            </tr>
      </logic:iterate>
          </table>
          <br>
       <table cellspacing="0" class="table-99">
          <tr>
              <td colspan="8" class="black9"><br>

<logic:equal name="queryPlslForm" property="sl"  value="unreceived">
               <input type="checkbox" name="chkall" onclick="javascript:All_quanxuan();">
               是否全选
</logic:equal>
<logic:equal name="queryPlslForm" property="sl"  value="received">
               <input type="checkbox" name="chkall" disabled="true" onclick="javascript:All_quanxuan();">
               是否全选
</logic:equal>
<logic:equal name="queryPlslForm" property="sl"  value="all">
               <input type="checkbox" name="chkall" disabled="true" onclick="javascript:All_quanxuan();">
               是否全选
</logic:equal>

                <input type="image" name="delete" value="删除" alt="删除"
                onclick = "javascript:return doSubmitForm('Delete');"
                onMouseOver="MM_swapImage('shanchu','','<%=static_file%>images/shanchu2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shanchu1.jpg"
                width="79" height="22" id="shanchu">

				<input type="image" name="shouli" value="受理" alt="受理"
                onclick = "javascript:return doSubmitForm('Check');"
                onMouseOver="MM_swapImage('shouli','','<%=static_file%>images/shouli2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shouli1.jpg"
                width="79" height="22" id="shouli">
            </td>

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
               <logic:equal name="queryPlslForm" property="queryCache.isOnePage" scope="session" value="false">
                 <!--如果是第一页，前页不显示-->
                  <logic:equal name="queryPlslForm" property="queryCache.hasPrev" scope="session" value="true">
                <input type="image" name="frontpage" value="前页" alt="前页"
                onclick = "javascript:return FN_QSChangePage('shangyiye');"
                onMouseOver="MM_swapImage('shangyiye1','','<%=static_file%>images/shangyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg"
                width="79" height="22" id="shangyiye1">
                 </logic:equal>
                 <!--如果是最后一页，后页不显示-->
                  <logic:equal name="queryPlslForm" property="queryCache.hasNext" scope="session" value="true">
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

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
