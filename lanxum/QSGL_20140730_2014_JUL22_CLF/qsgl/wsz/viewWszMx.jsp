<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@page import="com.ttsoft.framework.util.Currency"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.SbState"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.sql.Timestamp"%>


<HTML><HEAD><TITLE>收现缴款书明细信息表</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>


<SCRIPT language=JavaScript>
function checkSubmit(operationType)
{

	  document.forms[0].operationType.value=operationType;
  	document.forms[0].submit();

}


</SCRIPT>
<!----本页面的头文件和本页帮助的路径----->

<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
<link href="<%=static_file%>css/top-box.css" rel="stylesheet" type="text/css">
</HEAD>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="收现缴款书管理&gt;收现缴款书明细信息表"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>


 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=850>
  <TBODY>
  <TR>
    <TD vAlign=top>

         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>查询收现缴款书明细信息</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top><br>
<TABLE class=table-99 cellSpacing=0>
              <TBODY>
<html:form action="/wsz/viewWsz.do">
<html:hidden property="operationType"/>
<html:hidden property="yuan"/>
<bean:define id="data" name="queryWszForm" property="mxbo" type="com.creationstar.bjtax.qsgl.model.bo.QueryWszBo"/>
<bean:define id="data1" name="data" property="qswszzVo" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz"/>
              <TR>
                <TD align=middle colSpan=5>
                
                <table width="100%" border="2" align="center" cellpadding="0" cellspacing="4" bordercolor="#9DB9D2">
        <tr>
          <td height="120" bgcolor="#F0F0F0">
          <div align="center">
        <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="28%" align="right">&nbsp;</td>
            <td width="41%" align="right"> <div align="center"><span class="black9"><font size="5" color="#999999">中华人民共和国</font> 
                </span> </div>
              <p align="center"><font color="#2C556D">&nbsp; 
                税收缴款书(税务收现专用)</font></p></td>
            <td width="31%" valign="baseline"><p>&nbsp;</p>
              <p><font color="#2C556D" size="">(<bean:write name="data1" property="ndzb" />)京地现<font color="#d32e2e"><bean:write name="data1" property="wszh" /></font></font></p>
              <p>&nbsp;</p></td>
          </tr>
        </table>
      </div>
            
      <p align="center" class="black9"><font color="#999999"></font></p></td>
        </tr>
        <tr>
          <td><table width="100%" cellpadding="4" cellspacing="0" class="black9" id="AutoNumber4" style="border-collapse: collapse">
        <tr> 
          <td width="33%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">&nbsp;&nbsp;登记注册类型：</font><font color="#d32e2e">&nbsp;</font></td>
          <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">填发日期：</font><font color="#d32e2e"><%=DataConvert.TS2String(data1.getCjrq())%></font></td>
          <td width="32%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">税务机关：</font><font color="#d32e2e"><bean:write name="queryWszForm" property="zsjg" /></font></td>
        </tr>
      </table>
            
      <table width="100%" height="97" border="2" cellpaddng="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber3" style="border-collapse: collapse">
        <tr> 
          <td width="90" height="45" align="center" nowrap>纳税人识别号</td>
          <td width="313" height="45"><font color="#d32e2e"><bean:write name="data1" property="nsrjsjdm" /></font></td>
          <td width="87" height="45" align="center" nowrap><font color="#2C556D">纳税人名称</font></td>
          <td width="252" height="45"><font color="#d32e2e"><bean:write name="data1" property="nsrmc" /></font></td>
        </tr>
        <tr> 
          <td height="48" align="center">地址</td>
          <td height="48" colspan="3"><font color="#d32e2e"><bean:write name="queryWszForm" property="fdcwz" /></font></td>
        </tr>
      </table>
      <table width="100%" height="150" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber2" style="border-collapse: collapse">
        <tr> 
          <td width="92" style="padding-top: 0" align="center" nowrap>税种</td>
          <td width="126" style="padding-top: 0" align="center" nowrap>品目名称</td>
          <td width="80" style="padding-top: 0" align="center">课税数量</td>
          <td width="114" style="padding-top: 0" height="34" align="center">计税金额或销售收入</td>
          <td width="99" style="padding-top: 0" height="34" align="center">税率或单位税额</td>
          <td width="119" style="padding-top: 0" height="34" align="center">税款所属时期</td>
          <td width="102" style="padding-top: 0" height="34" align="center"> <p align="center">已缴或扣除额</td>
          <td width="76" style="padding-top: 0" height="34" align="center"><font color="#2C556D">实缴税额</font></td>
        </tr>
        <!--The loop begin-->
        <tr>
          <% double hjje = 0.00;%>
          <td style="padding-top: 0"><font color="#d32e2e">契税</font></td>
          <td style="padding-top: 0"><font color="#d32e2e">
          	<logic:iterate id="data2" indexId="index" length="length" name="queryWszForm" property="smlist">
                <%=data2%>
             </logic:iterate></font></td>
          <td style="padding-top: 0"><font color="#d32e2e">&nbsp;</font></td>
          <td style="padding-top: 0" ><font color="#d32e2e">
            <logic:iterate id="data4" indexId="index" length="length" name="queryWszForm" property="jsjelist">
                 <%=DataConvert.BigDecimal2String((BigDecimal)data4)%>
            </logic:iterate></font></td>
          <td style="padding-top: 0" ><font color="#d32e2e">
            <logic:iterate id="data5" indexId="index" length="length" name="queryWszForm" property="sllist">
                <%=DataConvert.BigDecimal2String((BigDecimal)data5,2)%>
            </logic:iterate></font></td>
          <td style="padding-top: 0" ><font color="#d32e2e">
          	<logic:iterate id="data7" indexId="index" length="length" name="queryWszForm" property="skssrqlist">
                <%=data7%>
            </logic:iterate></font></font></td>
          <td style="padding-top: 0" ><font color="#d32e2e">&nbsp;</td>
          <td style="padding-top: 0" ><font color="#d32e2e">
             <logic:iterate id="data6" indexId="index" length="length" name="queryWszForm" property="snjelist">
                  <%=DataConvert.BigDecimal2String((BigDecimal)data6)%>
                  <%
           			hjje +=DataConvert.BigDecimal2double((BigDecimal)data6);
               	  %>
             </logic:iterate></font></td>
        </tr>
        <!--The end of loop-->
        
        <tr> 
          <td colspan="7" style="padding-top: 0" height="15"><font color="#2C556D">金额合计（大写）：</font>
          	<font color="#d32e2e">
          	<% BigDecimal Dqyzwhj = new BigDecimal(hjje);%>
          	<%=Currency.convert(Dqyzwhj.setScale(2,BigDecimal.ROUND_HALF_UP))%>
          	</font></td>
          <td width="76" style="padding-top: 0" height="15"><div align="left"><font color="#d32e2e">￥<%=hjje%></font></div></td>
        </tr>
      </table>
            
      <table width="100%" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber5" style="border-collapse: collapse">
        <tr> 
          <td width="15%" height="103"><font color="#2C556D">税务机关： </font> 
            <p><font color="#d32e2e"><bean:write name="queryWszForm" property="zsjg" /></font>
            <p></td>
          <td width="15%"><font color="#2C556D">代征单位： </font> 
            <p><font color="#d32e2e"></font>
            <p></td>
          <td width="15%"><font color="#2C556D">填票人：</font> 
          	<p><font color="#d32e2e"><bean:write name="data1" property="cjr" /></font>
          	<p></td>
          <td width="55%" >
          	<p><font color="#2C556D">备注：</font>
            	<font color="#d32e2e">&nbsp;&nbsp;<bean:write name="queryWszForm" property="bz" /></font>
            <p><font color="#2C556D">契约(合同)成立日期：</font> 
            	<font color="#d32e2e"><%=DataConvert.TimeStamp2String(data1.getHtclrq())%></font>
            	<font color="#2C556D">&nbsp;&nbsp;&nbsp;&nbsp;房地产权属转移面积(m2)：</font>
            	<font color="#d32e2e">
            	<logic:iterate id="data3" indexId="index" length="length" name="queryWszForm" property="mjlist">
                  <%=DataConvert.BigDecimal2String((BigDecimal)data3)%>
                </logic:iterate></font>
            </td>
        </tr>
      </table></td>
        </tr>
      </table>
       </TD>
     </TR>
     <TR>
     	<TD align=middle bgColor=#f3f3f3 colSpan=10><BR>
     		<IMG name="back" onclick="checkSubmit('Return');" onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg" width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">
        </TD>
     </TR>
   </TBODY>
</TABLE>
<BR>

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
