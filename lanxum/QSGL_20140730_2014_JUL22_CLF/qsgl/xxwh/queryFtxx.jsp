<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>

<HTML><HEAD><TITLE>查询房屋土地使用信息</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>images/list.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>

<SCRIPT language=JavaScript src="<%=static_file%>images/inputchecker.js"></SCRIPT>

<SCRIPT language=JavaScript>
function doSubmitForm(operationType)
{
  document.all.operationType.value=operationType;
  if(operationType=="Query" )
  {
    if(document.forms[0].tdfwid.value=="" )
    {
        alert("查询条件不能为空！");
     	document.forms[0].tdfwid.focus();
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
                       <jsp:param name="subtitle" value="信息维护>查询房屋土地使用信息"/>
                       <jsp:param name="helpURL" value=""/>
                      </jsp:include>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD Align=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>查询房屋土地使用信息</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/xxwh/queryFtxx.do" method="post">
           <input type="hidden" name="operationType" value="">
            <INPUT name=actionType type=hidden value=Show> <INPUT name=ymbz type=hidden
            value=0>
            <br>
             <TABLE border="0" cellSpacing=0 class=table-89>
              <TBODY>
               <tr>
                <td class="2-td2-t-left">土地、房屋唯一标识</td>
                <td class="2-td2-t-center">
                    <div align=left>
                    <html:text property="tdfwid"  size="25" /><FONT color=red>*</FONT>
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


  <logic:equal name="queryFtxxForm" property="afterQuery" scope="session" value="true">

       <table cellspacing="0" class="table-99">
 	  <tr>
		<td><hr class="hr1"></td>
		<td width="140" class="b-black10">
			<div align="center">查询房屋土地使用信息的结果</div></td>
		<td><hr class="hr1"></td>
	  </tr>
	</table>

          <!--如果结果集为空，提示没有记录-->
           <logic:equal name="queryFtxxForm" property="exist" scope="session" value="false">
             <TABLE border="0" cellSpacing=0 class=table-99>
               <TBODY>
                 <TR>
                   <td class="2-td2-t-center"> <div align="center">没有你要的记录，请重新查询</div></td>
                 </TR>
               </TABLE><br>
           </logic:equal>
           <logic:equal name="queryFtxxForm" property="exist" scope="session" value="true">
             <TABLE border="0" cellSpacing=0 class=table-99>
               <TBODY>
                 <TR>
                   <TD  align="left" >土地房屋详细信息 </TD>
                 </TR>



		                <tr>

				      <TD class=2-td2-t-left >
				        <DIV align=right>房地产项目名称&nbsp;</DIV></TD>
				        <TD colspan="4" class=2-td2-t-center style="word-break:break-all">
				          <DIV align=left>&nbsp;<bean:write name="queryFtxxForm" property="fdcxmmx"/></DIV></TD>
				  </TR>
				  <TR>
			          <TD class=2-td2-left >
			            <DIV align=right>合约签订时间&nbsp; </DIV>
			          </TD>
			          <TD class=2-td2-left >
			            <DIV align=left>&nbsp;
                                   <bean:write name="queryFtxxForm" property="htqdsj"/>
                                    </DIV></TD>


			            <TD class=2-td2-left >
			              <DIV align=right>购房原因&nbsp;</DIV></TD>


			              <TD colspan="2"  class=2-td2-center >
			                <DIV align=left>
			                  <bean:write name="queryFtxxForm" property="flmc"/>&nbsp
			                </DIV></TD>
		          </TR>
		          <TR>
		            <TD class=2-td2-left >
		              <DIV align=right>土地、房屋权属转移类型&nbsp; </DIV> </TD>
		              <TD class=2-td2-left >
		                <DIV align=left>
		                  <bean:write name="queryFtxxForm" property="tdfwqszylxmc"/>&nbsp; </DIV></TD>
		                  <TD class=2-td2-left >
		                    <DIV align=right>房屋类别&nbsp;</DIV></TD>
		                    <TD colspan="2"  class=2-td2-center >
		                      <DIV align=left><bean:write name="queryFtxxForm" property="fwlxmc"/>
		                        &nbsp; </DIV></TD>
		          </TR>
		          <TR>
		            <TD  class=2-td2-left >
		              <DIV align=right>土地、房屋座落地址&nbsp; </DIV>
		            </TD>
		            <TD colspan="4"  class=2-td2-center style="word-break:break-all">
		              <DIV align=left>&nbsp;<bean:write name="queryFtxxForm" property="tdfwzldz"/></DIV></TD>
		            </TR>
                    <TR>
                      <TD class=2-td2-left >
                        <DIV align=right>土地、房屋权属转移面积&nbsp; </DIV>
                      </TD>
                      <TD  class=2-td2-left >
                        <DIV align=left>土地：
                          <bean:write name="queryFtxxForm" property="tdfwqszymj"/>
                          ㎡ </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>房屋建筑面积&nbsp;</DIV></TD>
                          <TD colspan="2"  class=2-td2-center >
                            <DIV align=left>
                           <bean:write name="queryFtxxForm" property="fwjzmj"/>
                              ㎡</DIV></TD>
                    </TR>
                    <TR>
	                    <TD class=2-td2-left rowspan = "2">
	                      <DIV align=right>成交价格（评估价格）</DIV>
	                      <TD  class=2-td2-left >
	                        <DIV align=left>
                                  <bean:write name="queryFtxxForm" property="cjjgrmb"/>
                                  元(人民币) </DIV></TD>
	                        <TD class=2-td2-left >
	                          <DIV align=right>税务机关核定价格&nbsp; </DIV></TD>
	                          <TD  colspan="2"  class=2-td2-center >
	                            <DIV align=left>
                                      <bean:write name="queryFtxxForm" property="pgjgrmb"/>
                                      元(人民币) </DIV></TD>
                      </TR>
                      <TR>
                        <TD class=2-td2-left >
                          <DIV align=left>
                             <bean:write name="queryFtxxForm" property="cjjgwb"/>
                            元(外币) </DIV></TD>
                          <TD class=2-td2-left >
                            <DIV align=left>
                             币种： <bean:write name="queryFtxxForm" property="bzmc"/>
                            </DIV>
                            <DIV align=left>
                              汇率:&nbsp;<bean:write name="queryFtxxForm" property="hl"/>
                               </DIV></TD>
                            <TD class=2-td2-center  >
                              <DIV align=left>
                                <bean:write name="queryFtxxForm" property="zhjgrmb"/>
                                折合元(人民币) </DIV></TD>



                       </tr>
                       <TR>
                                 <TD class=2-td2-left >
			            <DIV align=right>申报人&nbsp; </DIV>
			          </TD>
			          <TD class=2-td2-left >
			            <DIV align=left>&nbsp;
                                   <bean:write name="queryFtxxForm" property="cjr"/>
                                    </DIV></TD>

                                  <TD class=2-td2-left >
			            <DIV align=right>申报日期&nbsp; </DIV>
			          </TD>
			          <TD class=2-td2-center >
			            <DIV align=left>&nbsp;
                                   <bean:write name="queryFtxxForm" property="cjrq"/>
                                    </DIV></TD>
                          </tr>






               </TABLE><br><br>

                 <TABLE border="0" cellSpacing=0 class=table-99>
                   <TBODY>
                     <TR>
                       <TD  align="left"  >使用此房屋土地的申报信息 </TD>
                     </TR>
                     <TR>
                       <TD class="2-td1-left" width = "30%" >申报表号 </TD>
                       <TD class="2-td1-left" width = "30%" >申报人 </TD>
                       <TD class="2-td1-center" width = "40%" >申报日期</TD>
                     </TR>
      <logic:iterate id="data" name="queryFtxxForm" property="listSbxx"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbtdfwgl">
                     <TR>
                       <td class="2-td2-left">
                           <a href="<%=response.encodeURL("/qsgl/xxwh/directViewSbxx.do?operationType=Show&sbbh=") + data.getSbbh()%>" target="_blank">
                         <bean:write name="data" property="sbbh"/></td>
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
