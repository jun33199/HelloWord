<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="java.math.BigDecimal"%>


<HTML><HEAD><TITLE>收现缴款书明细信息表</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>


<SCRIPT language=JavaScript type="text/JavaScript">

function doSubmitForm(operationType)
{
    if(operationType == 'Save')
    {
        if(!checkData() )
        {
            return false;
        }
    }

    document.forms[0].operationType.value=operationType;
  	document.forms[0].submit();

}

function checkData()
{
    if(document.forms[0].tfrq.value.length < 1)
    {
        alert("请输入填发日期");
        document.forms[0].tfrq.focus();
        return false;
    }
    if(document.forms[0].tfrq.value.length != 8)
    {
        alert("填发日期格式不对，请重新输入");
        document.forms[0].tfrq.focus();
        return false;
    }
    if(document.forms[0].skssksrq.value.length < 1)
    {
        alert("税款所属起始日期不能为空，请重新输入");
        document.forms[0].skssksrq.focus();
        return false;
    }
    if(document.forms[0].skssksrq.value.length != 8)
    {
        alert("税款所属起始日期格式不对，请重新输入");
        document.forms[0].skssksrq.focus();
        return false;
    }
    if(document.forms[0].skssjzrq.value.length < 1)
    {
        alert("税款所属截止日期不能为空，请重新输入");
        document.forms[0].skssjzrq.focus();
        return false;
    }
    if(document.forms[0].skssjzrq.value.length != 8)
    {
        alert("税款所属截止日期格式不对，请重新输入");
        document.forms[0].skssjzrq.focus();
        return false;
    }
    if(document.forms[0].jsje.value.length < 1)
    {
        alert("计税金额不能为空，请重新输入");
        document.forms[0].jsje.focus();
        return false;
    }
    if(document.forms[0].sjse.value.length < 1)
    {
        alert("实纳金额不能为空，请重新输入");
        document.forms[0].sjse.focus();
        return false;
    }

    return true;
}

</SCRIPT>


<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="信息维护&gt;补录契税收现缴款书"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>


 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>

         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>补录契税收现缴款书</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top><br>
<TABLE class=table-99 cellSpacing=0>
              <TBODY>
              <TR>
                <TD class=2-td1-center align=middle colSpan=5>中 华 人 民 共 和 国 契 税 完 税 证</TD></TR>
<html:form action="/xxwh/addWsz.do">
<html:hidden property="operationType"/>

<bean:define id="data1" name="wszForm" property="qswszz" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz"/>
<bean:define id="data2" name="wszForm" property="qswszmx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx"/>
              <TR>
                <TD align=middle colSpan=5>
                  <TABLE class=black9 height=30 cellSpacing=0 cellPadding=0 width="100%" border=0>
                    <TBODY>
                    <TR>
                      <TD width=320>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;填发日期: &nbsp;
<input type="text" name="tfrq" size="12" value="<%=DataConvert.TimeStamp2String(data1.getCjrq())%>">
                      </TD>
                      <TD width=136>&nbsp;收现缴款书字别:&nbsp;
<bean:write name="data1" property="ndzb" /> </TD>
                      <TD
                        width=184>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收现缴款书号:&nbsp;
<bean:write name="data1" property="wszh" />
                      </TD>
                      <TD width=160>
</TD></TR></TBODY></TABLE>
                      </TD>
                    </TR>
              <TR>
                <TD class=2-td2-t-left align=right bgColor=#f3f3f3>纳税人代码</TD>
                <TD class=2-td2-t-left align=left  bgColor=#f3f3f3>&nbsp;<bean:write name="data1" property="nsrjsjdm" />
                &nbsp;</TD>
                <TD class=2-td2-t-left align=right bgColor=#f3f3f3>纳税人名称</TD>
                <TD class=2-td2-t-center align=left bgColor=#f3f3f3 colSpan=2>&nbsp;<bean:write name="data1" property="nsrmc" />&nbsp;</TD>
               </TR>
              <TR>
                <TD class=2-td2-left align=right bgColor=#f3f3f3>
                  <P>契税(合同)成立日期</P></TD>
                <TD class=2-td2-left align=left bgColor=#f3f3f3>&nbsp;
<%=DataConvert.TimeStamp2String(data1.getHtclrq())%>
                &nbsp;</TD>
                <TD class=2-td2-left align=right bgColor=#f3f3f3>房地产位置</TD>
                <TD class=2-td2-center align=left bgColor=#f3f3f3 colSpan=2>&nbsp;
<bean:write name="data1" property="fdcwz" />
                &nbsp;</TD>
              </TR>
              <TR>
                <TD class=2-td2-left align=right bgColor=#f3f3f3>税 款 所 属 日 期</TD>
                <TD class=2-td2-left align=left bgColor=#f3f3f3>&nbsp;
                    <input type="text" name="skssksrq" size="12" value="<%=DataConvert.TimeStamp2String(data2.getSkssksrq())%>">
                    至
                    <input type="text" name="skssjzrq" size="12" value="<%=DataConvert.TimeStamp2String(data2.getSkssjsrq())%>">
                </TD>
                <TD class=2-td2-left align=right bgColor=#f3f3f3>地 址</TD>
                <TD class=2-td2-center align=left bgColor=#f3f3f3 colSpan=2>&nbsp;

                &nbsp;</TD>
              </TR>

              <TR>
                <TD class=2-td2-left align=middle bgColor=#f3f3f3>税 种 税 目

                <BR><bean:write name="data2" property="szsmmc" />&nbsp;

                </TD>
                <TD class=2-td2-left align=middle
                  bgColor=#f3f3f3>房地产权属转移面积(m2)


                  <BR><%=DataConvert.BigDecimal2String((BigDecimal)data2.getQszymj(),2)%>

                  </TD>
                <TD class=2-td2-left align=middle bgColor=#f3f3f3>计 税 金 额

                  <BR><input type="text" name="jsje" value="<%=DataConvert.BigDecimal2String((BigDecimal)data2.getJsje(),2)%>">

                  </TD>
                <TD class=2-td2-left align=middle
                bgColor=#f3f3f3>税率

                <BR><%=DataConvert.BigDecimal2String((BigDecimal)data2.getSl(),2)%>


                </TD>
                <TD class=2-td2-center align=middle bgColor=#f3f3f3>实 纳 金 额

                  <BR><input type="text" name="sjse" value="<%=DataConvert.BigDecimal2String((BigDecimal)data2.getSjse(),2)%>">


                  </TD>
               </TR>
              <TR>
                <TD class=2-td2-center align="middle" bgColor=#f3f3f3
                  colSpan=5>逾期&nbsp;&nbsp;<%=DataConvert.BigDecimal2String((BigDecimal)data1.getYqts(),0)%>&nbsp;&nbsp;天，每日按滞纳税款加收
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  &nbsp;&nbsp;&nbsp;<%=DataConvert.BigDecimal2String((BigDecimal)data1.getZnjzje(),2)%>&nbsp;&nbsp;&nbsp;‰ 的滞纳金 </TD></TR>
              <TR>
                <TD class=2-td2-left align=middle bgColor=#f3f3f3 colSpan=2>
                  <P>征收机关<BR><bean:write name="data1" property="swjgzzjgmc" /><BR>(盖章)</P></TD>
                  <TD colSpan=3 rowspan=2 class=2-td2-center valign=top bgColor=#f3f3f3>
                    <DIV align=left>备注：</DIV>
                  </TD>
                </TR>
              <TR>
                 <TD class=2-td2-left align=middle bgColor=#f3f3f3
                  colSpan=2>经办人(章) </TD>
              </TR>
              <TR>
                <TD align=middle bgColor=#f3f3f3 colSpan=10><BR>

                <IMG alt=保存 height=22 id=baocun name="btnSave"
                      onclick="doSubmitForm('Save');" onmouseout=MM_swapImgRestore()
                      onmouseover="MM_swapImage('baocun','','/StaticFile/images/baocun2.jpg',1)"
                      src="/StaticFile/images/baocun1.jpg" style="CURSOR: hand" width=79>


                 <IMG name="back" onclick="doSubmitForm('Back');"
          						onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          						onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          						width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">
                </TD>
               </TR>
              </TBODY>
              </TABLE>
                <BR>

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
