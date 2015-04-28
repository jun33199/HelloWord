<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>

<HTML><HEAD><TITLE>批量申报受理</TITLE>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType)
{
  document.all.operationType.value=operationType;
  if(operationType=="check" )
	  {
		document.forms[0].operationType.value='Check';
	  }
  else if(operationType=="quit")
	  {
		document.forms[0].operationType.value='Return';
	  }

  document.forms[0].submit();
}

function getsel(){
  var selstr='';
  var checkObj;
  checkObj=document.getElementsByName("selOne");
  len = checkObj.length;
  for (i=0;i<len; i++){
    if(checkObj[i].checked){
        selstr=selstr+i+',';
    }
  }
  document.forms[0].operationType.value='Check';
  document.forms[0].selcfdr.value=selstr;
  document.forms[0].submit();
}

function selAll(){
    var checkObj;
    var len;
    checkObj=document.getElementsByName("selOne");
    len = checkObj.length;
    if (document.forms[0].seldr.checked){
        for (i=0;i<len;i++){
            checkObj[i].checked=true;
        }
    }else{
        for (i=0;i<len;i++){
            checkObj[i].checked=false;
        }
    }
}
</SCRIPT>


<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="批量受理&gt;批量申报受理"/>
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
          <TD class=1-td1>批量申报受理</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/plsl_swry/plsl">
            <input type = "hidden" name = "operationType" value = "" >
			<html:hidden property="from" value="dr"/>
            <html:hidden property="selcfdr"/>
            <logic:equal name="queryPlslForm2" property="status" scope="session" value="Result">
                <input type="hidden" name="changePage" value='<bean:write name="queryPlslForm2" property="queryCache.currentPageNum"/>'>
                <input type="hidden" name="pageCount" value='<bean:write name="queryPlslForm2" property="queryCache.pageCount"/>'>
            </logic:equal>
            <br>


             <TABLE border="0" cellSpacing=0 class=table-89>
              <TBODY>


			</TABLE>
			<BR>

      <DIV align=center>
	   <IMG name="sjsh" onclick="doSubmitForm('check');"
                onMouseOver="MM_swapImage('sjsh1','','<%=static_file%>images/qs_sjsh2.jpg',1)"
                onMouseOut="MM_swapImgRestore()"  src="<%=static_file%>images/qs_sjsh1.jpg"
                width="79"
                height="22"
                id="sjsh1"
                alt="数据审核"
                style="cursor:hand">

        <IMG name="back"
          onclick="doSubmitForm('quit');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">
      </DIV><BR>


 <logic:equal name="queryPlslForm2" property="status" scope="session" value="Result">
	<table cellspacing="0" class="table-99">
 	  <tr>
		<td><hr class="hr1"></td>
		<td width="140" class="b-black10">
			<div align="center">批量受理信息</div></td>
		<td><hr class="hr1"></td>
        <!--如果结果集为空，提示没有记录-->
           <logic:equal name="queryPlslForm2" property="queryCache.pageCount" scope="session" value="0">
            <td class="2-td2-t-center"> <div align="center">没有你要的记录，请重新查询</div></td>
           </logic:equal>
           <logic:notEqual name="queryPlslForm2" property="queryCache.pageCount" scope="session" value="0">
              <td class="2-td2-t-center"> <div align="right">
               共<bean:write name="queryPlslForm2" property="queryCache.countNumber"/>条记录
               第<bean:write name="queryPlslForm2" property="queryCache.currentPageNum"/>/
               <bean:write name="queryPlslForm2" property="queryCache.pageCount"/>页
                  <input type="image" name="beginpage" value="第一页" alt="第一页"
                  onclick = "javascript:return FN_QSChangePage('diyiye');"
                  onMouseOver="MM_swapImage('diyiye','','<%=static_file%>images/diyiye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg" id="diyiye">
                  <input type="image" name="endpage" value="最后一页" alt="最后一页"
                  onclick = "javascript:return FN_QSChangePage('zuihouyiye');"
                  onMouseOver="MM_swapImage('zuimoye','','<%=static_file%>images/zuimoye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg" id="zuimoye" >
                <!--如果只有一页，下面按钮不显示-->
                <logic:equal name="queryPlslForm2" property="queryCache.isOnePage" scope="session" value="false">
                  <!--如果是第一页，前页不显示-->
                  <logic:equal name="queryPlslForm2" property="queryCache.hasPrev" scope="session" value="true">
                    <input type="image" name="frontpage" value="前页" alt="前页"
                    onclick = "javascript:return FN_QSChangePage('shangyiye');"
                    onMouseOver="MM_swapImage('shangyiye','','<%=static_file%>images/shangyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg" id="shangyiye">
                  </logic:equal>
                  <!--如果是最后一页，后页不显示-->
                  <logic:equal name="queryPlslForm2" property="queryCache.hasNext" scope="session" value="true">
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
          <logic:notEqual name="queryPlslForm2" property="queryCache.pageCount" scope="session" value="0">
          <br>



     <TABLE border="0" cellSpacing=0 class=table-99>
      <TBODY>
       <TR>
       	<TD class="2-td1-left" width = "8%" >批次号</TD>
        <TD class="2-td1-left" width = "18%" >纳税人名称</TD>
        <TD class="2-td1-left" width = "15%" >计算机代码</TD>
        <TD class="2-td1-left" width = "%18" >房地产项目名称</TD>
        <TD class="2-td1-left" width = "26%" >土地、房屋座落地址</TD>
        <TD class="2-td1-center" width = "15%" >成交价格</TD>
      </TR>
      <logic:iterate id="data" indexId="index" length="length" name="queryPlslForm2" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb">
            <tr>
              <td class="2-td2-left">
                  <a href="<%=response.encodeURL("/qsgl/plsl_swry/plsl.do?operationType=View&drlx=0&viewIndex=") + index.intValue()%>">
                  &nbsp;<bean:write name="data" property="drpch"/></a>
              </td>
              <td class="2-td2-left" style="word-break:break-all">
                  &nbsp;<bean:write name="data" property="nsrmc"/></a>
              </td>
              <td class="2-td2-left">
                  &nbsp;<bean:write name="data" property="nsrjsjdm"/></a>
              </td>
              <td class="2-td2-left" style="word-break:break-all">
                  &nbsp;<bean:write name="data" property="fdcxmmc"/>
              </td>
              <td class="2-td2-left" style="word-break:break-all">
                  &nbsp;<bean:write name="data" property="fdcdz"/>
              </td>
              <td class="2-td2-center" >
                  &nbsp;<%=DataConvert.BigDecimal2String(data.getCjjg())%>
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
               <logic:equal name="queryPlslForm2" property="queryCache.isOnePage" scope="session" value="false">
                 <!--如果是第一页，前页不显示-->
                  <logic:equal name="queryPlslForm2" property="queryCache.hasPrev" scope="session" value="true">
                <input type="image" name="frontpage" value="前页" alt="前页"
                onclick = "javascript:return FN_QSChangePage('shangyiye');"
                onMouseOver="MM_swapImage('shangyiye1','','<%=static_file%>images/shangyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg"
                width="79" height="22" id="shangyiye1">
                 </logic:equal>
                 <!--如果是最后一页，后页不显示-->
                  <logic:equal name="queryPlslForm2" property="queryCache.hasNext" scope="session" value="true">
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

          <br>
          <logic:notEqual name="queryPlslForm2" property="size" scope="session" value="0">

              <table cellspacing="0" class="table-99">
                  <tr>
                      <td><hr width="100%" size="1"></td>
                      <td width="98" align="center" class="black9"><strong>已导入过的数据</strong></td>
                      <td><hr width="100%" size="1"></td>
                  </tr>
              </table>
                  <div align="center">（以下纳税人在最近一年内对以下房屋土地坐落地址已经进行过申报，请确认是否需要审核）</div>
              <table border="0" cellSpacing=0 class=table-99>
                 <tr>
                     <td class="2-td1-left" width = "4%" >选择</td>
                     <td class="2-td1-left" width = "8%" >批次号</td>
                     <td class="2-td1-left" width = "18%" >纳税人名称</td>
                     <td class="2-td1-left" width = "15%" >计算机代码</td>
                     <td class="2-td1-left" width = "%18" >房地产项目名称</td>
                     <td class="2-td1-left" width = "26%" >土地、房屋座落地址</td>
                     <td class="2-td1-center" width = "15%" >成交价格</td>
                 </td>
             <logic:iterate id="data" indexId="index" length="length" name="queryPlslForm2" property="resultList"
                           type="com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb">
            <tr>
              <td class="2-td2-left">
                  <input type="checkbox" name="selOne" value="">
              </td>
               <td class="2-td2-left">
                  <a href="<%=response.encodeURL("/qsgl/plsl_swry/plsl.do?operationType=View&drlx=1&viewIndex=") + index.intValue()%>">
                  &nbsp;<bean:write name="data" property="drpch"/></a>
              </td>
              <td class="2-td2-left" style="word-break:break-all">
                  &nbsp;<bean:write name="data" property="nsrmc"/></a>
              </td>
              <td class="2-td2-left">
                  &nbsp;<bean:write name="data" property="nsrjsjdm"/></a>
              </td>
              <td class="2-td2-left" style="word-break:break-all">
                  &nbsp;<bean:write name="data" property="fdcxmmc"/>
              </td>
              <td class="2-td2-left" style="word-break:break-all">
                  &nbsp;<bean:write name="data" property="fdcdz"/>
              </td>
              <td class="2-td2-center" >
                  &nbsp;<%=DataConvert.BigDecimal2String(data.getCjjg())%>
              </td>
            </tr>
          </logic:iterate>
             <tr>
                  <td class="2-td2-left">
                     <input type="checkbox" name="seldr" onclick="selAll()">
                  </td>
                  <td class="2-td2-center" colspan="6">
                      <input type="button" name="qzdr" value="重复审核" onclick="getsel()"/>
                  </td>
             </tr>
          </table>
          </logic:notEqual>

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
