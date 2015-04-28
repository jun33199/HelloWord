<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>

<HTML><HEAD><TITLE>汇总生成缴款书</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>

<SCRIPT language=JavaScript type=text/JavaScript>
function doSubmitForm(operationType)
{
  	document.all.operationType.value=operationType;

    if(operationType == 'HzWsz' && document.forms[0].hzfsdm.options[document.forms[0].hzfsdm.selectedIndex].value!=<%=Constants.WSZ_HZFS_PL%>)
    {
        if(document.forms[0].qsrq.value=="" || !checkDate(document.forms[0].qsrq.value))
        {
            alert("起始日期不能为空或格式不正确，请重新输入！");
            document.forms[0].qsrq.focus();
            return false;
        }
        if(document.forms[0].jzrq.value=="" || !checkDate(document.forms[0].jzrq.value))
        {
            alert("截止日期不能为空或格式不正确，请重新输入！");
            document.forms[0].jzrq.focus();
            return false;
        }
    }
    
    if(operationType == 'HzWsz' && document.forms[0].hzfsdm.options[document.forms[0].hzfsdm.selectedIndex].value==<%=Constants.WSZ_HZFS_PL%>)
    {
        if(document.forms[0].drpch.value=="")
        {    
	    		        alert("由于选择了“按照批次汇总”，因此必须录入批次号！");
            	document.forms[0].drpch.focus();
            	return false;
        	}
     }
     
     document.forms[0].jsfsmc.value = document.forms[0].jsfsdm.options[document.forms[0].jsfsdm.selectedIndex].text;
     document.forms[0].hzfsmc.value = document.forms[0].hzfsdm.options[document.forms[0].hzfsdm.selectedIndex].text;
     
     
   	document.forms[0].submit();
}

</SCRIPT>
</HEAD>
<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="缴款书管理&gt;汇总收现缴款书生成缴款书"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>汇总生成缴款书</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
<html:form action="/jks/hzWsz2Jks.do" >
<html:hidden property="operationType" />
<html:hidden property="hzfsmc" />
<html:hidden property="jsfsmc" />
            <br>
             <TABLE border="0" cellSpacing=0 class=table-60>
              <TBODY>
               <TR>
                <TD class = "2-td1-center" width="40%">
                  汇总公式&nbsp;
                </TD>
                <TD class="2-td2-t-right" width="60%">
                  <DIV align=left>
					<html:select property = "hzfsdm">
						<html:option value = "<%=Constants.WSZ_HZFS_GR%>">按个人汇总</html:option>
						<html:option value = "<%=Constants.WSZ_HZFS_PL%>">按批次汇总</html:option>
						<!--<html:option value = "<%=Constants.WSZ_HZFS_ZSD%>">按征收点汇总</html:option>
						<html:option value = "<%=Constants.WSZ_HZFS_SWS%>">按税务机关（所）汇总</html:option>-->
					</html:select>
				  </DIV>
                </TD>
              </TR>
              <TR>
                <TD class="2-td1-Bcenter">
                  导入的批次号&nbsp;
                </TD>
                <TD class="2-td2-right">
                  <DIV align=left>
					<html:text property="drpch" />
				  </DIV>
                </TD>
              </TR>
               <TR>
                <TD class="2-td1-Bcenter">
                  起始日期&nbsp;
                </TD>
                <TD class="2-td2-right">
                  <DIV align=left>
					<html:text property="qsrq" />（形如：20050101）
				  </DIV>
                </TD>
              </TR>
			  <TR>
                <TD class="2-td1-Bcenter">
                  截止日期&nbsp;
                </TD>
                <TD class="2-td2-right">
                  <DIV align=left>
					<html:text property="jzrq"/>（形如：20050101）
				  </DIV>
                </TD>
              </TR>
			  <TR>
                <TD class = "2-td1-Bcenter" width="40%">
                  缴税方式&nbsp;
                </TD>
                <TD class="2-td2-right" width="60%">
                  <DIV align=left>
                    <html:select property="jsfsdm" >
                      <html:option value="<%=Constants.WSZ_JSFS_XJ%>">现金</html:option>
                      <html:option value="<%=Constants.WSZ_JSFS_SK%>">刷卡</html:option>
                      <html:option value="<%=Constants.WSZ_JSFS_ZP%>">支票</html:option>
                    </html:select>
				  </DIV>
         </TD>
        </TR>
     </TABLE><BR>

      <DIV align=center>
    <logic:notEqual name="hzWsz2JksForm" property="status" scope="session" value="Result">
      <input type = "button" name = "bt1" value = "汇 总" onclick="doSubmitForm('HzWsz')">
   </logic:notEqual>
      <IMG alt=退出 height=22 id=tuichu name=tuichu
      onclick="doSubmitForm('Return');" onmouseout=MM_swapImgRestore()
      onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
      src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
      </DIV><BR>

<logic:equal name="hzWsz2JksForm" property="status" scope="session" value="Result">
     <TABLE border="0" cellSpacing=0 class=table-99>
      <TBODY>
       <TR>
        <TD class="2-td1-left" width = "%30" >缴款书号</TD>
        <TD class="2-td1-left" width = "15%" >被汇总的收现缴款书</TD>
        <TD class="2-td1-left" width = "12%" >税款日期</TD>
        <TD class="2-td1-left" width = "15%" >实纳金额</TD>
		<TD class="2-td1-center" width = "33%" >备注</TD>
      </TR>
      <logic:iterate id="data1" indexId="index" length="length" name="hzWsz2JksForm" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb">

       	<TR>
          <td class="2-td2-left">
              <a href="<%=response.encodeURL("/qsgl/jks/printHzWsz.do?operationType=PrintHzWsz&viewIndex=") + index.intValue()%>">
                  <bean:write name="data1" property="jkpzh" />
              </a>
          </td>
          <td class="2-td2-left">
          	<a href="<%=response.encodeURL("/qsgl/jks/viewWszList.do?operationType=QueryWsz&type=2&yuan=returnHz&viewIndex=") + index.intValue()%>">
          		<bean:write name="data1" property="kssl"/>
      		</a>
          </td>
          <td class="2-td2-left"><%=DataConvert.TimeStamp2String(data1.getSbrq())%>&nbsp;</td>
          <td class="2-td2-left">￥<%=DataConvert.BigDecimal2String(data1.getSjje(),2)%>&nbsp;</td>
		  <td class="2-td2-center"><bean:write name="data1" property="bz" />&nbsp;</td>
        </TR>
    </logic:iterate>
     </TABLE><br>
   </logic:equal>

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
