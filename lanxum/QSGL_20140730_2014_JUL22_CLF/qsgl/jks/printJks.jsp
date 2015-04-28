<%@ page contentType="text/html; charset=GBK"%>

<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.JksForm" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="java.math.BigDecimal"%>

<%@ include file="/include/include.jsp"%>

<HTML><HEAD><TITLE>打印缴款书</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>

<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<%
    session = request.getSession(false);
    JksForm jksForm = (JksForm)session.getAttribute("jksForm");
%>

<script type="text/javascript">
function doSubmitForm(operationType)
{
    document.forms[0].operationType.value=operationType;
  	document.forms[0].submit();
}
</script>

<script type="text/javascript">

function goprint()
{

//    var szdm = "<%=jksForm.getSzdm()%>"; //税种代码
    var sklx = "<%=jksForm.getSklx()%>"; //税款类型
    var dnbh = "<%=jksForm.getJkpzh()%>"; //电脑编号    
    var htbh = "<%=jksForm.getHtbh()%>";  //合同编号 zzb 090831 add
    var zclx = "<%=jksForm.getZclx()%>"; //注册类型         
//    var tfrq = "<%=jksForm.getTfrq()%>"; //填发日期
    var tfrqn = "<%=jksForm.getTfrqn()%>"; //填发日期
    var tfrqy = "<%=jksForm.getTfrqy()%>"; //填发日期
    var tfrqr = "<%=jksForm.getTfrqr()%>"; //填发日期
    var zsjg = "<%=jksForm.getZsjg()%>"; //征收机关               
//    var lsgx = "<%=jksForm.getLsgx()%>"; //隶属关系
//    var jhh = "<%=jksForm.getJhh()%>";    //交换号
    var skgk = "(<bean:write name="jksForm" property="jhh" />)<bean:write name="jksForm" property="skgk" />"; //收款国库  
    var skxjrq = "<%=jksForm.getSkxjrq()%>"; //税款限缴日期
    var jkdwdm = "<%=jksForm.getJkdwdm()%>"; //缴款单位代码
//    var jkdwdh = "<%=jksForm.getJkdwdh()%>"; //缴款单位电话
    var jkdwqc = "<%=jksForm.getJkdwqc()%>"; //缴款单位全称    
    var jkdwkhyh = "<bean:write name="jksForm" property="jkdwkhyh" />"; //缴款单位开户银行          
    var jkdwzh = "<bean:write name="jksForm" property="jkdwzh" />"; //缴款单位帐号
    var yskmbm = "<%=jksForm.getYskmbm()%>"; //预算科目编码
    var yskmmc = "<%=jksForm.getYskmmc()%>"; //预算科目名称
//    var yskmjc = "<%=jksForm.getYskmjc()%>"; //预算科目级次
    var  jkmxfcbl = "<%=jksForm.getJkmxfcbl()%>"; //预算科目分成比例    
    var  jkmxpmmc = "<%=jksForm.getJkmxxmmc()%>"; //缴款明细项目名称
    var  jkmxkssl = "<%=jksForm.getJkmxkssl()%>"; //缴款明细课税数量
    var  jkmxjsje = "<%=jksForm.getJkmxjsje()%>"; //缴款明细计税金额
    var sksssq = "<%=jksForm.getSksskssq()%>" + "-" + "<%=jksForm.getSkssjssq()%>"; //税款所属时期  
    var  jkmxsjse = "<%=jksForm.getJkmxsjse()%>"; //缴款明细实缴税额
    var jkjehj = "<%=jksForm.getJkjehj()%>"; //缴款金额合计
    var jkjehj_nu = "<%=jksForm.getJkjehj_nu()%>";  //缴款金额合计数字
    var swjg = "<%=jksForm.getSwjg()%>"; //税务机关
    var jkdw = "<%=jksForm.getJkdw()%>"; //缴款单位
    var bz = "<%=jksForm.getBz()%>"; //备注
    var fdcwz = "房地产位置：" + "<%=jksForm.getFdcwz()%>";  //房地产位置


		document.printer.setsklx(sklx);
		document.printer.setdnbh(dnbh);
    document.printer.sethtbh(htbh);
//    document.printer.setszdm(szdm);
    document.printer.setzclx(zclx);
//    document.printer.settfrq(tfrq);
    document.printer.settfrqn(tfrqn);
    document.printer.settfrqy(tfrqy);
    document.printer.settfrqr(tfrqr);
//    document.printer.setlsgx(lsgx);
    document.printer.setzsjg(zsjg);
    document.printer.setjkdwdm(jkdwdm);
//    document.printer.setjkdwdh(jkdwdh);
    document.printer.setjkdwqc(jkdwqc);
    document.printer.setjkdwkhyh(jkdwkhyh);
    document.printer.setjkdwzh(jkdwzh);
    document.printer.setskgk(skgk);
    document.printer.setskxjrq(skxjrq);    
        
    document.printer.setyskmbm(yskmbm);
    document.printer.setyskmmc(yskmmc);
//    document.printer.setyskmjc(yskmjc);
    document.printer.setjkmxfcbl(jkmxfcbl);
    document.printer.setjkmxpmmc(jkmxpmmc);
    document.printer.setjkmxkssl(jkmxkssl);
    document.printer.setjkmxjsje(jkmxjsje);
	  document.printer.setsksssq(sksssq);    
    document.printer.setjkmxsjse(jkmxsjse);
	    
    document.printer.setjkjehj(jkjehj);
    document.printer.setjkjehj_nu(jkjehj_nu);


    document.printer.setswjg(swjg);
		document.printer.setjkdw(jkdw);    
    document.printer.setbz(bz);
//    document.printer.setgkjhh(jhh);
    document.printer.setfdcwz(fdcwz);

    document.printer.startPrint();

}

</script>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0" onload="goprint()">
<applet name="printer" codebase="<%=static_file%>printer" code="com.ttsoft.bjtax.webprint.SSJKSPagePrinterNew" width="0" height="0" archive="vprinter.jar">
</applet>
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="审核&gt;审核及收款&gt;打印缴款书"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>
<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height=580 width=770>
<TBODY>
	   <TR>
		<TD  class=2-td2-t-center> <DIV align=center><B><FONT SIZE="4" COLOR="">中华人民共和国<br>税收缴款书(银行经收专用)</FONT></B></DIV></TD>
		</TR>

        <TR>
        <TD class=1-td2><BR>
        <html:form action="/jks/printJks.do" >
        <html:hidden property = "operationType" />

<!--
<%-- zzb 090831 add begin--%>
  <TABLE border=0 cellSpacing=0 class=table-99 width="88%">
    <TBODY>
       <TR>
           <TD align=right class=0 width="80%">合同编号:</TD>
           <TD align=left class=0><bean:write name="jksForm" property="htbh" /></TD>
        </TR>
   </TABLE><BR>
<%-- zzb 090831 add end--%>
-->
            <TABLE border=0 cellSpacing=0 class=table-99 width="88%">
              <TBODY>
               <TR>
             	<TD class=0 width="15%">税款类型:</TD>
             	<TD align=left class=0 width="17%"><bean:write name="jksForm" property="sklx" /></TD>
             	<TD class=0 width="10%">电脑编号:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="jksForm" property="jkpzh" /></TD>
             	<TD class=0 width="10%">合同编号:</TD>
							<TD align=left class=0 width="22%"><bean:write name="jksForm" property="htbh" /></TD>
               </TR>
               <TR>
             	<TD class=0 width="15%">登记注册类型:</TD>
             	<TD align=left class=0 width="17%"><bean:write name="jksForm" property="zclx" /></TD>
              <TD class=0 width="10%">填发日期:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="jksForm" property="tfrqn" />年<bean:write name="jksForm" property="tfrqy" />月<bean:write name="jksForm" property="tfrqr" />日</TD>
             	<TD class=0 width="10%">征收机关:</TD>
							<TD align=left class=0 width="22%"><bean:write name="jksForm" property="zsjg" /></TD>
                </TR></TBODY></TABLE>
		    <TABLE border=1 cellSpacing=0 class=table-99 width="75%">
		    <TR>
		    	<TD rowspan=2 class=2-td2-t-left width="8%">缴款单位(人)</TD>
		    	<TD class=2-td2-t-left width="7%">识别号</TD>
		    	<TD align=left class=2-td2-t-left width="30%"><bean:write name="jksForm" property="jkdwdm" />&nbsp;</TD>
		    	<TD class=2-td2-t-left width="10%">开户银行</TD>
		    	<TD align=left class=2-td2-t-center width="35%"><bean:write name="jksForm" property="jkdwkhyh" />&nbsp;</TD>
		    </TR>
		    <TR>
		    	<TD class=2-td2-left width="7%">名称</TD>
		    	<TD class=2-td2-left width="30%"><bean:write name="jksForm" property="jkdwqc" />&nbsp;</TD>
		    	<TD class=2-td2-left width="10%">账号</TD>
		    	<TD class=2-td2-center width="35%"><bean:write name="jksForm" property="jkdwzh" />&nbsp;</TD>
		    </TR>
		    <TR>
		    	<TD colspan="2" class=2-td2-left width="15%">收款国库</TD>
		    	<TD align=left class=2-td2-left width="30%">(<bean:write name="jksForm" property="jhh" />)<bean:write name="jksForm" property="skgk" />&nbsp;</TD>
		    	<TD class=2-td2-left width="10%">税款限缴日期</TD>
		    	<TD align=left class=2-td2-center width="35%"><bean:write name="jksForm" property="skxjrq" />&nbsp;</TD>
		    </TR>
		    </TABLE>
          <TABLE border=1 cellSpacing=0 class=table-99 width="75%">
           <TBODY>
		   		<TR>
		   			<TD colspan="3" class=2-td2-t-left width="25%">预&nbsp;算&nbsp;科&nbsp;目</TD>
           	<TD rowspan="2" class=2-td2-t-left width="10%">品目名称</TD>
           	<TD rowspan="2" class=2-td2-t-left width="8%">课税数量</TD>
           	<TD rowspan="2" class=2-td2-t-left width="10%">计税金额或<br>销售收入</TD>
           	<TD rowspan="2" class=2-td2-t-left width="8%">税率或<br>单位税额</TD>
           	<TD rowspan="2" class=2-td2-t-left width="14%">税款所属时期</TD> 
           	<TD rowspan="2" class=2-td2-t-left width="5%">已缴或<br>扣除额</TD>            	
           	<TD rowspan="2" class=2-td2-t-center width="10%">实缴金额</TD>
           </TR>
		   		<TR>
		   			<TD class=2-td2-t-left width="8%">编&nbsp;&nbsp;码</TD>
		   			<TD class=2-td2-t-left width="9%">名&nbsp;&nbsp;称</TD>
		   			<TD class=2-td2-t-left width="8%">级&nbsp;&nbsp;次</TD>
           </TR>
 <logic:iterate id="data" indexId="index" length="length" name="jksForm" property="mxList" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx"  >
           <TR>
           	<TD class=2-td2-left><bean:write name="jksForm" property="yskmbm" />&nbsp;</TD>
           	<TD class=2-td2-left><bean:write name="jksForm" property="yskmmc" />&nbsp;</TD>
           	<TD class=2-td2-left><bean:write name="data" property="yskmfcbl"/>&nbsp;</TD>
           	<TD class=2-td2-left><bean:write name="data" property="szsmmc" />&nbsp;</TD>
           	<TD class=2-td2-left><bean:write name="data" property="kssl"/>&nbsp;</TD>
           	<TD class=2-td2-left>￥<%=DataConvert.BigDecimal2String((BigDecimal)data.getJsje())%>&nbsp;</TD>
           	<TD class=2-td2-left>&nbsp;</TD>
           	<TD class=2-td2-left><bean:write name="jksForm" property="sksskssq" />-<bean:write name="jksForm" property="skssjssq" /></TD>
           	<TD class=2-td2-left>&nbsp;</TD>
           	<TD class=2-td2-center>￥<%=DataConvert.BigDecimal2String((BigDecimal)data.getSjse())%>&nbsp;</TD>
           </TR>
</logic:iterate>
		   		<TR>
		   			<TD class=2-td2-t-left width="8%">金额合计</TD>
		   			<TD class=2-td2-t-left colspan="8" width="9%">（大写）<bean:write name="jksForm" property="jkjehj" />&nbsp;</TD>
		   			<TD class=2-td2-t-center width="8%"><bean:write name="jksForm" property="jkjehj_nu" />&nbsp;</TD>
           </TR>
		   </TBODY>
           </TABLE>
            <TABLE border=0 cellSpacing=0 class=table-99 width="75%">
              <TBODY>
              <TR>
                <TD class=2-td2-t-left width="20%">
                   <DIV align=center>税务机关:</DIV>
				  				 <DIV align=center><bean:write name="jksForm" property="swjg" /></DIV>
				  				 <DIV align=left>填票人</DIV></TD>              	
                <TD class=2-td2-t-left width="20%">
                  <DIV align=center>缴款单位(人)</DIV>
                  <DIV align=center><bean:write name="jksForm" property="nsrmc" /></DIV>
				  				<DIV align=left>经办人</DIV></TD>
                <TD class=2-td2-t-left width="30%">
                  <DIV align=center>上列款项已收妥并划转收款单位账户</DIV>
				  				<DIV align=center>国库（银行）盖章</DIV>
				  				<DIV align=center>&nbsp;&nbsp;年 &nbsp;&nbsp;月 &nbsp;&nbsp;日</DIV></TD>
                <TD class=2-td2-t-center width="30%">
                  <DIV align=left>备注</DIV>
                  <DIV align=left><bean:write name="jksForm" property="bz" /></DIV></TD>
                </DIV></TD></TR></TBODY></TABLE><BR>
										<DIV align=left>房地产位置：<bean:write name="jksForm" property="fdcwz" /></DIV><BR>
                    <DIV align=center>
                    <IMG alt=打印 id=dayin name=dayin
                      onclick="goprint()"
                      onMouseOver="MM_swapImage('dayin','','<%=static_file%>images/dayin2.jpg',1)"
                      onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/dayin1.jpg"
                      width="79" style="cursor:hand">
                    <IMG alt=退出 height=22 name=tuichu
                      onclick="doSubmitForm('Return');"
                      onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
                      onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
                      width="79" id="tuichu1" style="cursor:hand">

                    </DIV><BR>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
