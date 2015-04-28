<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>


<HTML><HEAD><TITLE>查询已购公有住房使用信息</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>images/list.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js"></SCRIPT>
<script language="javascript" src="../js/qscommon.js"></script>

<SCRIPT language=JavaScript>
function doSubmitForm(operationType)
{
  document.all.operationType.value=operationType;
  if(operationType=="Query" )
  {
    if(document.forms[0].yggyzfqszsh.value=="" )
    {
        alert("查询条件不能为空！");
     	document.forms[0].yggyzfqszsh.focus();
    	return false;
    }
  }

  if(operationType=="Save" )
	{
		if( !isAllCharValid(document.forms[0].sye.value,"1234567890.,"))
		{
        alert("剩余额不能为空或不能为汉字，请重新输入！");
     	document.forms[0].sye.focus();
    	return false;
		}
	}
  document.forms[0].submit();
}



</SCRIPT>
<!----本页面的头文件和本页帮助的路径----->
<SCRIPT language=javascript src="<%=static_file%><%=static_file%>images/Header.js"></SCRIPT>

<SCRIPT language=JavaScript src="<%=static_file%><%=static_file%>images/swapImage.js"
type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css><LINK
href="<%=static_file%>css/piaozheng.css" rel=stylesheet type=text/css>
<META content="MSHTML 5.00.2919.6307" name=GENERATOR></HEAD>
<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
                       <jsp:param name="subtitle" value="信息维护>查询已购公有住房使用信息"/>
                       <jsp:param name="helpURL" value=""/>
                      </jsp:include>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD Align=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>查询已购公有住房使用信息</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/xxwh/queryYggyzf.do" method="post">
           <input type="hidden" name="operationType" value="">
            <INPUT name=actionType type=hidden value=Show> <INPUT name=ymbz type=hidden
            value=0>
            <br>
             <TABLE border="0" cellSpacing=0 class=table-89>
              <TBODY>
               <tr>
                <td class="2-td2-t-left">出售合同号码</td>
                <td class="2-td2-t-center">
                    <div align=left>
                    <html:text property="yggyzfqszsh"  size="25" /><FONT color=red>*</FONT>
					</div>
                </td>
              </tr>
             </TABLE><BR>

      <DIV align=center>
      <IMG style="CURSOR: hand" value="查询" onMouseOver="MM_swapImage('chaxun','','<%=static_file%>images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()"  src="<%=static_file%>images/chaxun1.jpg" width="79" height="22"  id="chaxun" alt="查询"  onclick = "doSubmitForm('Query');">&nbsp;&nbsp;
      <IMG alt=退出 height=22 id=tuichu name=tuichu
      onclick="doSubmitForm('Return');" onmouseout=MM_swapImgRestore()
      onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
      src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
      </DIV><BR>


  <logic:equal name="queryYggyzfForm" property="afterQuery" scope="session" value="true">

       <table cellspacing="0" class="table-99">
 	  <tr>
		<td><hr class="hr1"></td>
		<td width="140" class="b-black10">
			<div align="center">已购公有住房使用信息查询结果</div></td>
		<td><hr class="hr1"></td>
	  </tr>
	</table>

          <!--如果结果集为空，提示没有记录-->
           <logic:equal name="queryYggyzfForm" property="exist" scope="session" value="false">
             <TABLE border="0" cellSpacing=0 class=table-99>
               <TBODY>
                 <TR>
                   <td class="2-td2-t-center"> <div align="center">没有你要的记录，请重新查询</div></td>
                 </TR>
               </TABLE><br>
           </logic:equal>
           <logic:equal name="queryYggyzfForm" property="exist" scope="session" value="true">
             <TABLE border="0" cellSpacing=0 class=table-99>
               <TBODY>
                 <TR>
                   <TD  align="left" >已购公有住房查询信息 </TD>
                 </TR>
                 <TR>
                   <TD class="2-td1-left" width = "20%" >座落地址 </TD>
                   <TD class="2-td1-left" width = "12%" >出售合同（契约）签订时间</TD>
                   <TD class="2-td1-left" width = "12%" >建筑面积</TD>
                   <TD class="2-td1-left" width = "12%" >成交价格</TD>
                   <TD class="2-td1-center" width = "12%" >剩余额</TD>

                 </TR>

                 <TR>
                   <td class="2-td2-left" style="word-break:break-all"><bean:write name="queryYggyzfForm" property="zldz"/></td>
                   <td class="2-td2-left"><bean:write name="queryYggyzfForm" property="qdsj"/></td>
                   <td class="2-td2-left"><bean:write name="queryYggyzfForm" property="jzmj"/></td>
                   <td class="2-td2-left"><bean:write name="queryYggyzfForm" property="cjjg"/></td>
                   <td class="2-td2-center"><bean:write name="queryYggyzfForm" property="sye"/></td>

                 </TR>


				 <tr>
					<td align="center">剩余额调整</td>
					<td align="center"><html:text property="sye"/></td>
				 </tr>

               </TABLE><br>


      <DIV align=center>
        <IMG alt=保存 height=22 id=baocun name=Submit63
          onclick="doSubmitForm('Save')" onmouseout=MM_swapImgRestore()
          onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
          src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79>

          </DIV><BR>

                 <TABLE border="0" cellSpacing=0 class=table-99>
                   <TBODY>
                     <TR>
                       <TD  align="left"  >使用此已购公有住房的申报信息 </TD>
                     </TR>
                     <TR>
                       <TD class="2-td1-left" width = "32%" >申报表号 </TD>
                       <TD class="2-td1-left" width = "25%" >本次抵扣额</TD>
                        <TD class="2-td1-left" width = "25%" >申报人</TD>
                       <TD class="2-td1-center" width = "25%" >申报日期</TD>
                     </TR>
      <logic:iterate id="data" name="queryYggyzfForm" property="listSbxx"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbgyzf">
                     <TR>
                       <td class="2-td2-left">
                           <a href="<%=response.encodeURL("/qsgl/xxwh/directViewSbxx.do?operationType=Show&sbbh=") + data.getSbbh()%>" target="_blank">
                         <bean:write name="data" property="sbbh"/></td>
                       <td class="2-td2-left"><%=DataConvert.BigDecimal2String(data.getBcdke())%></td>
                        <td class="2-td2-left"><bean:write name="data" property="cjr"/></td>
                       <td class="2-td2-center"><%=DataConvert.TimeStamp2String(data.getCjrq())%></td>
                     </TR>
      </logic:iterate>
                   </TABLE><br>
      </logic:equal>
 </logic:equal>


</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
