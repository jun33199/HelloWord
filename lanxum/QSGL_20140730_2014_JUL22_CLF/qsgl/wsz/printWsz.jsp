<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.SbState"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.QueryWszForm"%>
<%@ page import="java.math.BigDecimal"%>

<HTML><HEAD><TITLE>收现缴款书打印</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<%
    session = request.getSession(false);
    QueryWszForm wszForm = (QueryWszForm)session.getAttribute("queryWszForm");
    if(wszForm == null)
    {
        wszForm = new QueryWszForm();
    }
    //System.out.println("wszForm.getSl()="+wszForm.getSl());

%>

<SCRIPT language=JavaScript type="text/JavaScript">
function checkSubmit(operationType)
{

    document.forms[0].operationType.value=operationType;
  	document.forms[0].submit();

}
</SCRIPT>

<script type="text/javascript">
function goprint(){

    var htbh = "<%=wszForm.getHtbh()%>";//合同编号 zzb 090831 add

    var tfrq = "<%=wszForm.getTfrq()%>";
    var wszh = "<%=wszForm.getWszh()%>";
    var nsrdm = "<%=wszForm.getNsrdm()%>";
    var nsrmc = "<%=wszForm.getNsrmc()%>";

    var htqdrq = "契约(合同)成立日期:"+"<%=wszForm.getHtqdrq()%>";

    var fdcwz = "<%=wszForm.getFdcwz()%>";
    var jbr = "<%=wszForm.getJbr()%>";

    var skssksrq = "<%=wszForm.getSkssksrq()%>";
	var skssjsrq = "-" + "<%=wszForm.getSkssjzrq()%>"
    var dz = "<%=wszForm.getDz()%>";
	var szsm = "<%=wszForm.getSzsmmc()%>";
	var qszymj = "房地产权属转移面积(㎡):"+"<%=wszForm.getQszymj()%>";
    var jsje = "￥" +"<%=wszForm.getJsje()%>";
	var sl = "<%=wszForm.getSl()%>";
	//if (sl != null && sl != "")  sl=sl*100;
	//alert(sl)
	var sjse = "￥" +"<%=wszForm.getSjse()%>";
	var yqts = "<%=wszForm.getYqts()%>";
	var znj = "<%=wszForm.getZnj()%>";
	var jehj_dx = "<%=wszForm.getJehj_dx()%>";
	var jehj = "￥" + "<%=wszForm.getJehj()%>";
	var zsjg = "<%=wszForm.getZsjg()%>";
	var bz = "<%=wszForm.getBz()%>";

    document.printer.sethtbh(htbh); //合同编号 zzb 090831 add
    document.printer.setszsm(szsm);
	document.printer.settfrq(tfrq);
	document.printer.setwszh(wszh);
	document.printer.setnsrdm(nsrdm);
	document.printer.setnsrmc(nsrmc);
	document.printer.sethtqdrq(htqdrq);
	document.printer.setfdcwz(fdcwz);
	document.printer.setskssrq(skssksrq, skssjsrq);
	//document.printer.setdz(dz);
	document.printer.setqszymj(qszymj);
	document.printer.setjsje(jsje);
	document.printer.setsl(sl);
	document.printer.setsjse(sjse);
	//document.printer.setyqts(yqts);
	//document.printer.setznj(znj);
	document.printer.setjehj_dx(jehj_dx);
	document.printer.setjehj(jehj);
	document.printer.setzsjg(zsjg);
    document.printer.setbz(bz);
    document.printer.setjbr(jbr);

    document.printer.startPrint();

    if(confirm("打印是否成功？") )
    {
        checkSubmit('UpdateState');
    }
    else
    {
        if(confirm("变号打印？"))
        {
            checkSubmit('Change');
        }
        else
        {
            alert("点击确定开始原号打印");
            goprint();
        }
    }


}
</script>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0" onload="goprint()">
<applet name="printer" codebase="<%=static_file%>printer" code="com.ttsoft.bjtax.webprint.QsWszPageNewPrinter" width="1" height="1" archive="vprinter.jar">
</applet>
<jsp:include page="/include/Header.jsp" flush="true">
<jsp:param name="subtitle" value="收现缴款书管理>收现缴款书明细信息表"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<TABLE cellSpacing=0 cellPadding=0 height="100%" width=850 align=center border=0>
  <TBODY>
  <TR>
    <TD align=left><BR>
        <LI><jsp:include page="/include/MessageInclude.jsp" flush="true"/></LI>
      <TABLE class=table-98 height=400 cellSpacing=0 align=center>
        <TBODY>
        <TR>
          <TD class=1-td1>收现缴款书打印</TD></TR>
	        <TR>
	          <TD class=1-td2><BR>
	<html:form action="/wsz/printWsz.do">
	<html:hidden property="operationType"/>
	
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
              <p><font color="#2C556D">合同编号:<%=wszForm.getHtbh()%></font>
              <p><font color="#2C556D" size="">(<bean:write name="queryWszForm" property="zb" />)京地现<font color="#d32e2e"><bean:write name="queryWszForm" property="wszh" /></font></font></p>
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
          <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">填发日期：</font><font color="#d32e2e"><bean:write name="queryWszForm" property="tfrq" /></font></td>
          <td width="32%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">税务机关：</font><font color="#d32e2e"><bean:write name="queryWszForm" property="zsjg" /></font></td>
        </tr>
      </table>
            
      <table width="100%" height="97" border="2" cellpaddng="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber3" style="border-collapse: collapse">
        <tr> 
          <td width="90" height="45" align="center" nowrap>纳税人识别号</td>
          <td width="313" height="45"><font color="#d32e2e"><bean:write name="queryWszForm" property="nsrdm" /></font></td>
          <td width="87" height="45" align="center" nowrap><font color="#2C556D">纳税人名称</font></td>
          <td width="252" height="45"><font color="#d32e2e"><bean:write name="queryWszForm" property="nsrmc" /></font></td>
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
          <td width="95" style="padding-top: 0" height="34" align="center">税率或单位税额</td>
          <td width="123" style="padding-top: 0" height="34" align="center">税款所属时期</td>
          <td width="102" style="padding-top: 0" height="34" align="center"> <p align="center">已缴或扣除额</td>
          <td width="76" style="padding-top: 0" height="34" align="center"><font color="#2C556D">实缴税额</font></td>
        </tr>
        <!--The loop begin-->
        <tr>
          <td style="padding-top: 0"><font color="#d32e2e">契税</font></td>
          <td style="padding-top: 0"><font color="#d32e2e"><bean:write name="queryWszForm" property="szsmmc" /></font></td>
          <td style="padding-top: 0"><font color="#d32e2e">&nbsp;</font></td>
          <td style="padding-top: 0" ><font color="#d32e2e"><bean:write name="queryWszForm" property="jsje" /></font></td>
          <td style="padding-top: 0" ><font color="#d32e2e"><bean:write name="queryWszForm" property="sl" /></font></td>
          <td style="padding-top: 0" ><font color="#d32e2e"><bean:write name="queryWszForm" property="skssksrq" />-<bean:write name="queryWszForm" property="skssjzrq" /></font></td>
          <td style="padding-top: 0" ><font color="#d32e2e">&nbsp;</font></td>
          <td style="padding-top: 0" ><font color="#d32e2e"><bean:write name="queryWszForm" property="sjse" /></font></td>
        </tr>
        <!--The end of loop-->
        <tr> 
          <td width="80" style="padding-top: 0" height="15"><font color="#2C556D">金额合计</font></td>
          <td colspan="6" style="padding-top: 0" height="15"><font color="#2C556D">（大写）</font><font color="#d32e2e"><bean:write name="queryWszForm" property="jehj_dx" /></font></td>
          <td width="46" style="padding-top: 0" height="15"><div align="left"><font color="#d32e2e">￥<bean:write name="queryWszForm" property="jehj" /></font></div></td>
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
          	<p><font color="#d32e2e"><bean:write name="queryWszForm" property="jbr" /></font>
          	<p></td>
          <td width="55%" >
          	<p><font color="#2C556D">备注：</font>
            	<font color="#d32e2e">&nbsp;&nbsp;<bean:write name="queryWszForm" property="bz" /></font>
            <p><font color="#2C556D">契约(合同)成立日期：</font> 
            	<font color="#d32e2e"><bean:write name="queryWszForm" property="htqdrq" /></font>
            	<font color="#2C556D">&nbsp;&nbsp;&nbsp;&nbsp;房地产权属转移面积(m2)：</font>
            	<font color="#d32e2e"><bean:write name="queryWszForm" property="qszymj" /></font>
            </td>
        </tr>
      </table></td>
        </tr>
      </table>
      
      <TABLE class=table-99 cellSpacing=0>
      	<TBODY>
      		<TR>
      			<TD align=middle bgColor=#f3f3f3 colSpan=10><BR>
      				<IMG id=dayin style="CURSOR: hand" onmouseover="MM_swapImage('dayin','','<%=static_file%>images/dayin2.jpg',1)" onclick="goprint()" onmouseout=MM_swapImgRestore() alt=打印 src="<%=static_file%>images/dayin1.jpg" value=打印> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	<IMG id=tuichu style="CURSOR: hand" onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)" onclick="checkSubmit('Return')" onmouseout=MM_swapImgRestore() alt=退出 src="<%=static_file%>images/tuichu1.jpg" value=quit>
                </TD>
            </TR>
        </TBODY>
      </TABLE>
</html:form>

<TABLE border=0 cellPadding=0 cellSpacing=0 height=33 width="100%">
  <TBODY>
  <TR>
    <TD background=<%=static_file%>images/q-bottom-bg-01.jpg colSpan=2 height=9 noWrap></TD></TR>
  <TR>
    <TD noWrap><FONT size=2>&nbsp;&nbsp;&nbsp;*必须使用 IE 5.5 以上，分辨率 800*600 浏览本网站</FONT></TD>
    <TD align=right height=24><IMG alt=承办单位：清华同方软件股份有限公司 height=24 src="<%=static_file%>images/q-bottom-tu-01.jpg" width=184></TD></TR></TBODY></TABLE>
</BODY></HTML>
