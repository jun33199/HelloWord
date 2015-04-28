<%@page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.hzWsz2JksForm" %>
<%@page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="java.math.BigDecimal"%>

<%@ include file="/include/include.jsp"%>



<HTML><HEAD><TITLE>电子缴库专用缴款书</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>

<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>



<%
    session = request.getSession(false);
    hzWsz2JksForm hzWsz2JksForm = (hzWsz2JksForm)session.getAttribute("hzWsz2JksForm");
%>

<script type="text/javascript">
function doSubmitForm(operationType)
{
    document.forms[0].operationType.value=operationType;
  	document.forms[0].submit();
}



function doprint()
{
	fp = open('','printmail','top=2000,left=0');
	fp.document.writeln(pauls.innerHTML);
	fp.document.writeln("<script"+">window.print();<"+"/script>");
	fp.top.location.reload();
	fp.close();
}

function goprint(){

	var rqn="<%=hzWsz2JksForm.getTfrq().substring(0,4)%>";
	var rqy="<%=hzWsz2JksForm.getTfrq().substring(4,6)%>";
	var rqr="<%=hzWsz2JksForm.getTfrq().substring(6)%>";
	var ysb="√";
	var sbxh="<%=hzWsz2JksForm.getSbbh()%>";
	var wsb="";
 
 	var nsrmc = "<%=hzWsz2JksForm.getSwjg()%>";//纳税人名称
    var fkrjsjdm = "<%=hzWsz2JksForm.getJkdwdm()%>";//付款人计算机代码
     var zsjgdm = " "; //征收机关代码
    var fkrmc = document.getElementById("fkrmc_re").value;//付款人名称
    var zsjgmc = "<%=hzWsz2JksForm.getZsjg()%>";//征收机关名称
    var fkrkhyhmc = document.getElementById("fkrkhyhmc_re").value;//付款人开户银行名称
    var skgkmc = "<%=hzWsz2JksForm.getSkgk()%>";//收款国库名称
    var fkrzh = document.getElementById("fkrzh_re").value;//付款人账号
    var gkqshhh = " "; //国库清算行行号
    var nsxmdm ="74 契税";//纳税项目代码 
    var kssl=";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;";//课税数量
    var jsje = ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;";//计税金额
    var sjse = "<%=hzWsz2JksForm.getJkjehj_nu()%>"; //实缴金额
    var jehjdx = "<%=hzWsz2JksForm.getJkjehj()%>";  //金额合计（大写）
    var jehjxx = "<%=hzWsz2JksForm.getJkjehj_nu()%>";  //金额合计（小写）
    var bz = "<%=hzWsz2JksForm.getBz()%>"; //备注
 
 	document.printer.setNSRMC(nsrmc);
    document.printer.setRQN(rqn);
    document.printer.setRQY(rqy);
    document.printer.setRQR(rqr);
    document.printer.setYSB(ysb);
    document.printer.setSBXH(sbxh);
    document.printer.setWSB(wsb);
    document.printer.setFKRJSJDM(fkrjsjdm);
    document.printer.setZSJGDM(zsjgdm);
    document.printer.setFKRMC(fkrmc);
    document.printer.setZSJGMC(zsjgmc);
    document.printer.setFKRKHYHMC(fkrkhyhmc);
    document.printer.setSKGKMC(skgkmc);
    document.printer.setFKRZH(fkrzh);
    document.printer.setGKQSHHH(gkqshhh);
    document.printer.setNSXMDM(nsxmdm);
    document.printer.setKSSL(kssl);
    document.printer.setJSJE(jsje);
    document.printer.setSJSE(sjse);
    document.printer.setJEHJDX(jehjdx);
    document.printer.setJEHJXX(jehjxx);
    document.printer.setBZ(bz);
    
    document.printer.startPrint();
}


</script>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0" onload="goprint()">

<applet name="printer" codebase="<%=static_file%>printer"  code="com.ttsoft.bjtax.webprint.DZJKZYJKSPrinter" width="1" height="1" archive="vprinter.jar">
</applet>

<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="收现缴款书汇总&gt;打印缴款书"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>
</TD>
	    </TR>
		   </TBODY>
           </TABLE>

        <html:form action="/jks/printHzWsz.do" >
        <html:hidden property = "operationType" />

<table  width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
  <td  valign="top">

	<table width="96%" align="center" cellspacing="0" class="table-99" id="pauls">
		<tr>
			<td class="1-td1" colspan="7"><div align="center"><font size="4pt"  color="red"><b>电子缴库专用缴款书</b></font></div></td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7" align="center">
				<br><div align="center"><bean:write name="hzWsz2JksForm" property="dzjktfrq" /></div><br>
			</td>
		</tr>
		<tr>
			<td class="1-td2"  colspan="7" valign="top"> <br>
				<table class="table-99" cellspacing="0" width="98%" >
					<tr class="black9">
						<td align="left">&nbsp;□ 已申报   申报序号：<u><bean:write name="hzWsz2JksForm" property="sbbh" /></u>&nbsp;&nbsp;□ 未申报&nbsp;
						<td align="right">金额单位：人民币元</td>
					</tr>
				</table>
				<table class="table-99" cellspacing="0" width="98%" border="1">
					<tr>
						<td width="20%" class="2-td2-t-left">
							<div align="center"><strong>纳税人计算机代码</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-t-center" colspan="3">
							<div align="center">&nbsp;&nbsp;<bean:write name="hzWsz2JksForm" property="jkdwdm" /></div>
						</td>						
					</tr>
					<tr>
						<td width="20%" class="2-td2-t-left">
							<div align="center"><strong>纳税人名称</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-t-left">
							<div align="center">&nbsp;&nbsp;<bean:write name="hzWsz2JksForm" property="swjg" /></div>
						</td>
						<td width="20%" class="2-td2-t-left">
							<div align="center"><strong>征收机关代码</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-t-center">
							<div align="center">&nbsp;&nbsp;21100000000</div>
						</td>
					</tr>
					<tr>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>付款人名称</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-left">
							<div align="center">&nbsp;&nbsp;<INPUT border=0 id='fkrmc_re'
														name='fkrmc_re'  value='<%=hzWsz2JksForm.getJkdwqc()%>'
														 class="inputnormal"></INPUT>&nbsp;
														</div>
						</td>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>征收机关名称</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-center">
							<div align="center">&nbsp;&nbsp;<bean:write name="hzWsz2JksForm" property="zsjg" />
                            </div>
						</td>
					</tr>
					<tr>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>付款人开户银行名称</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-left">
							<div align="center">&nbsp;&nbsp;<INPUT border=0 id='fkrkhyhmc_re'
														name='fkrkhyhmc_re' value=' '
														class="inputnormal"></INPUT>&nbsp;</div>
						</td>
						<td width="20%" class="2-td2-left">
							<div align="center"><strong>收款国库名称</strong>&nbsp;&nbsp;</div>
						</td>
						<td width="30%" class="2-td2-center">
							<div align="center">&nbsp;&nbsp;
                                <bean:write name="hzWsz2JksForm" property="skgk" />
                            </div>
						</td>
					</tr>
					<tr>
						<td class="2-td2-left">
							<div align="center"><strong>付款人帐号</strong>&nbsp;&nbsp;</div>
						</td>
						<td class="2-td2-left">
							<div align="center">&nbsp;&nbsp;<INPUT border=0 id='fkrzh_re'
														name='fkrzh_re' value=' '
														class="inputnormal"></INPUT>&nbsp;</div>
						</td>
						<td class="2-td2-left">
							<div align="center"><strong>国库清算行行号</strong>&nbsp;&nbsp;</div>
						</td>
						<td class="2-td2-center">
							<div align="center">&nbsp;&nbsp;011100000003</div>
						</td>
					</tr>
				</table>
				<br>
				<br>
				<table class="table_99" cellspacing="0" width="98%" border="1">
					<tr>
							  <td class="2-td1-left"><div align="center">纳税项目代码</div></td>
							  <td class="2-td1-left"><div align="center">课税数量</div></td>
							  <td class="2-td1-left"><div align="center">计税金额</div></td>
							  <td class="2-td1-center"><div align="center">实缴税额</div></td>
					</tr>
					<tr>
							  <td class="2-td2-left"><div align="center">&nbsp;&nbsp;74 契税</div></td>
							  <td class="2-td2-left"><div align="center">&nbsp;&nbsp;</div></td>
							  <td class="2-td2-left"><div align="center">&nbsp;&nbsp;</div></td>
							  <td class="2-td2-center"><div align="center"><bean:write name="hzWsz2JksForm" property="jkjehj_nu" />&nbsp;&nbsp;</div></td>
					</tr>
					<tr>
						<td class="2-td2-left">
							<div align="center"><strong>金额合计(大写):</strong>&nbsp;&nbsp;</div>
						</td>
						<td class="2-td2-left">
							<div align="center">&nbsp;&nbsp;<bean:write name="hzWsz2JksForm" property="jkjehj" /></div>
                        </td>
						<td class="2-td2-left">
							<div align="center"><strong>金额合计(小写):</strong>&nbsp;&nbsp;</div>
						</td>
						<td colspan="2" class="2-td2-center">
							<div align="center">&nbsp;&nbsp;<bean:write name="hzWsz2JksForm" property="jkjehj_nu" /></div>
						</td>
					</tr>
							<tr>
						<td class="2-td2-left">
							<div align="center"><strong> <br>付款人 盖章 <br>   经办人（章） <br></strong>&nbsp;&nbsp;</div>
						</td>
						<td class="2-td2-left">
							<div align="center"><strong> <br>税务机关 <br>（章） <br></strong>&nbsp;&nbsp;</div>
						</td>
						<td class="2-td2-left">
							<div align="center"><strong> <br>银行记账员  <br>盖章 <br></strong>&nbsp;&nbsp;</div>
						</td>
						<td colspan="2" class="2-td2-center">
							<div align="left"><strong>&nbsp;&nbsp;备注： <br> <br></strong>&nbsp;&nbsp;<bean:write name="hzWsz2JksForm" property="bz" /></div>
						</td>
					</tr>
				</table>
				
		
			</td>
		</tr>
		
	</table>
	
	
	
<BR>

                    <DIV align=center>
                    <IMG alt=打印 id=dayin name=dayin
                      onclick="goprint()"
                      onMouseOver="MM_swapImage('dayin','','<%=static_file%>images/dayin2.jpg',1)"
                      onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/dayin1.jpg"
                      width="79" style="cursor:hand">
                    <IMG alt=退出 height=22 name=tuichu
                      onclick="doSubmitForm('ReturnPrepage');"
                      onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
                      onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
                      width="79" id="tuichu1" style="cursor:hand">

                    </DIV><BR>
</html:form>


  <tr>
  <td>

<%@ include file="../include/Bottom.jsp" %>	
	
 </td>
  </tr>
</table>

 
 
 
</body>
</html>