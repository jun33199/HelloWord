<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.ttsoft.bjtax.smsb.sgsswszmlr.web.SgsswszmlrForm"%>
<%@ page import="com.ttsoft.bjtax.smsb.sgsswszmlr.vo.SgsswszmVo"%>
<%@ page import="com.ttsoft.bjtax.smsb.sgsswszmlr.vo.SgsswszmMXVo"%>
<%
	SgsswszmlrForm form = (SgsswszmlrForm)request.getAttribute("sgsswszmlrForm");
	SgsswszmVo pVo = form.getPrintVo();
	SgsswszmMXVo printMx = pVo.getPrintMx();
	
	String thisWarUrl=com.ttsoft.common.util.ResourceLocator.getServerURL(request)+ request.getContextPath();
%>
<html:html>
<%@ include file="./include/header.jsp"%>
<script language=JavaScript src="../js/treatImage.js"></script>
<applet name="printer" codebase="<%=static_contextpath%>printer" code="com.ttsoft.bjtax.webprint.SSDZZZZYWSZPrinterNew " width="0" height="0" archive="vprinter.jar">
</applet>
	<html:form action="/webapp/sgsswszmlr/sgsswszmlrAction.do" method="POST">
	<html:hidden property="actionType"/>
	<html:hidden property="wszmKey"/>
	<html:hidden property="pageSize"/>
	<html:hidden property="nextPage"/>
	<html:hidden property="totalpage"/>
	<html:hidden property="query_nsrsbh"/>
	<html:hidden property="query_nsrmc"/>
	<html:hidden property="query_wspzh"/>
	
	<bean:define id="items" name="sgsswszmlrForm" property="printVo"/>
	
	<table width="80%" border="2" align="center" cellpadding="0" cellspacing="0" bordercolor="#9DB9D2">
		<tr>
			<td height="147" bgcolor="#F0F0F0">
				<div align="center">
					<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="100%" align="center">
								<div align="center">
									<span class="black9">
					                            <font color="#2C556D" size="4">&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(<bean:write name="items" property="NDZB"/>)京地证</font><font color="#d32e2e"><bean:write name="items" property="WSZH"/>&nbsp;</font>
											<br><font size="5" color="#999999">中&nbsp;&nbsp;华&nbsp;&nbsp;人&nbsp;&nbsp;民&nbsp;&nbsp;共&nbsp;&nbsp;和&nbsp;&nbsp;国 </font>
								            <br><font size="5" color="#2C556D">税&nbsp;&nbsp;收&nbsp;&nbsp;&nbsp;完&nbsp;&nbsp;&nbsp;税&nbsp;&nbsp;&nbsp;证&nbsp;&nbsp;&nbsp;明</font>
								                <hr width="27%"color="#2C556D">
									</span>
								</div>
								<font color="#2C556D">&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
								填发日期：</font><font color="#d32e2e"><bean:write name="items" property="TFRQ"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#2C556D">税务机关：</font><font color="#d32e2e"><bean:write name="items" property="SWJGMC"/></font>
							</td>
						</tr>
					</table>
				</div>
			</td>
        </tr>
		<tr>
			<td bgcolor="#F0F0F0">
				<table width="100%" border="1" cellpaddng="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber3" style="border-collapse: collapse">
					<tr>
						<td width="95px" height="40" align="center" nowrap="nowrap"><font color="#2C556D">纳税人识别号</font></td>
						<td width="260px" height="40" align="center">
								<font color="#d32e2e"><bean:write name="items" property="NSRSBH"/>&nbsp;</font>
						</td>
						<td width="104" height="40" align="center"><font color="#2C556D">纳税人名称</font></td>
						<td width="313" height="40" align="center">
							<font color="#d32e2e"><bean:write name="items" property="NSRMC"/>&nbsp;</font>
						</td>						
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td bgcolor="#F0F0F0">
				<table width="100%" height="100%" border="1" cellpaddng="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber3" style="border-collapse: collapse">
					<tr>
						<td align="center"  height="40" nowrap width="100px">
							<font color="#2C556D">原凭证号</font>
						</td>					
						<td align="center"  height="40" nowrap >
							<font color="#2C556D">税&nbsp;种</font>
						</td>					
						<td align="center"  height="40" nowrap >
							<font color="#2C556D">品目名称</font>
						</td>
						<td align="center"  height="40" nowrap >
							<font color="#2C556D">税款所属时期</font>
						</td>
						<td align="center"  height="40" nowrap >
							<font color="#2C556D">入（退）库日期</font>
						</td>						
						<td align="center"  height="40" nowrap width="114px" >
							<font color="#2C556D">实缴（退）金额</font>
						</td>
					</tr>
					<%
					String[] szmcArr = printMx.getSelect_szmc().split(";;");
					String[] szsmArr = printMx.getSelect_zssmmc().split(";;");
					String[] sjseArr = printMx.getSjje().split(";;");
					String[] old_wspzhArr = printMx.getOld_pzh().split(";;");
					String[] rk_tk_rqArr = printMx.getRk_tkrq().split(";;");
					String[] skssqArr = printMx.getSkssrq().split(";;");
					for(int i=0,j=szsmArr.length; i<j; i++){
					%>
					<tr>
						<td align="center"  height="50" nowrap>
							<font color="#d32e2e"><%=old_wspzhArr[i]%>&nbsp;</font>
						</td>					
						<td align="center"  height="50" nowrap>
							<font color="#d32e2e"><%=szmcArr[i]%>&nbsp;</font>
						</td>					
						<td align="center"  height="50" nowrap>
							<font color="#d32e2e"><%= szsmArr[i]%>&nbsp;</font>
						</td>
						<td align="center"  height="50" nowrap >
							<font color="#d32e2e"><%=skssqArr[i] %>&nbsp;</font>
						</td>
						<td align="center"  height="50" nowrap >
							<font color="#d32e2e"><%= rk_tk_rqArr[i]%>&nbsp;</font>
						</td>						
						<td align="center"  height="50" nowrap >
							<font color="#d32e2e"><%= sjseArr[i]%>&nbsp;</font>
						</td>
					</tr>
					<%
					}
					%>
				</table>
			</td>
		</tr>
		<tr>
			<td bgcolor="#F0F0F0">
				<table width="100%" height="100%" border="1" cellpaddng="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber3" style="border-collapse: collapse">
					
					<tr>
						<td align="center" width="100px" height="40" nowrap>
							<font color="#2C556D">金额合计</font>
						</td>
						<td align="right" height="40" nowrap="nowrap">
							<div align="left"><font color="#2C556D">(大写)</font><font color="#d32e2e"><bean:write name="items" property="HJJEDX"/>&nbsp;&nbsp;</font></div>
						</td>
						<td width="114px" align="center" nowrap="nowrap">
						<font color="#d32e2e">￥<%=pVo.getHJJE() %></font>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height="100">
			<td bgcolor="#F0F0F0">
				<table width="100%" height="100%" border="1" cellpaddng="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber3" style="border-collapse: collapse">
					<tr>
						<td align="center" valign="top"  nowrap>
							<font color="#2C556D">税务机关</font>
							<br><br><br><br>
							<font color="#2C556D">(盖章)</font>
						</td>
						<td align="center" valign="top"  nowrap>
							<font color="#2C556D">填票人</font>
							<br><br><bean:write name="sgsswszmlrForm" property="lrrmc"/><br><br>
						</td>
						<td align="center" valign="top" width="400" nowrap>
							<div align="left" >
							    <font color="#2C556D">备注：</font><BR>
							    <font color="#d32e2e">
								第<bean:write name="items" property="CURRENT_DYCS"/>次打印<BR>
								随机验证码：<bean:write name="items" property="SJM"/><BR>
								<bean:write name="items" property="BZ"/>
								</font>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
      </table><br>
	<br>
	  <table border="0" width="800" align="center">
		<tr>
			<td align="center">
				<table id="4" width="100%"  class="table-99" cellspacing="0" align="center">
					<tr>
						<td align="center">
							<div id="dayinDIV" align="center">
								<img
									onClick="printTab()"
									alt="打印完税证明" style="cursor: hand"
									onMouseOver="MM_swapImage('dayin','','<%=static_contextpath%>images/dayin2.jpg',1)"
									onMouseOut="MM_swapImgRestore();" id="dayin"
									src="<%=static_contextpath%>images/dayin1.jpg" width="79"
									height="22">
									&nbsp;&nbsp;
								<img
									onClick="doit('Query')"
									alt="返回维护查询界面" style="cursor: hand"
									onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
									onMouseOut="MM_swapImgRestore();" id="fanhui"
									src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
									height="22">
									&nbsp;&nbsp;
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	  </table>
</html:form>
<script type="text/javascript">
	function _onLoad(){
	}

	function doit(actionType){
		document.all.actionType.value=actionType;
	    document.forms[0].submit();
	}
		  
function printTab(){
    //年度字别
    var ndzb="<bean:write name="items" property="NDZB"/>";
    //完税证号
    var wszh="<bean:write name="items" property="WSZH"/>";
	//填发日期
	var tfrq = "<bean:write name="items" property="TFRQ"/>";
    //填发日期年
    var rqn=tfrq.substring(0,4);
    //填发日期月
    var rqy=tfrq.substring(4,6);
    //填发日期日
    var rqr=tfrq.substring(6,8);
    //税务登记代码
    var swdjdm ="<bean:write name="items" property="NSRSBH"/>";
    
    //征收机关
    var zsjg="<bean:write name="items" property="SWJGMC"/>";
    //纳税人全称
    var nsrqc="<bean:write name="items" property="NSRMC"/>";
    //税（费）种
    var SZ="<%=printMx.getSelect_szmc()%>";
    //税款所属时期
    var skssrq = "<%=printMx.getSkssrq()%>";
    //实缴金额
    var sjje="<%=printMx.getSjje()%>";
    //金额合计（大写）
    var  hjdxje="<bean:write name="items" property="HJJEDX"/>";
    //金额合计（小写）
    var  hjxxje="￥<bean:write name="items" property="HJJE"/>";
    //填票人
    var tpr = "<bean:write name="sgsswszmlrForm" property="lrrmc"/>";//ADD BY TANGCHANGFU 新票样项目后期增加 填票人 2014-03-12 by tangchangfu
    
    //备注1
	var  bz1="第<bean:write name="items" property="CURRENT_DYCS"/>次打印";
	var  bz2="随机验证码：<bean:write name="items" property="SJM"/>";
	var  bz3="<bean:write name="items" property="BZ"/>";
	
	
	//
	var old_wspzh="<%=printMx.getOld_pzh()%>";
	var pmmc = "<%=printMx.getSelect_zssmmc()%>";
	var rk_tk_rq = "<%=printMx.getRk_tkrq()%>";

    document.printer.setNDZB(ndzb);
    document.printer.setWSZH(wszh);
    document.printer.setRQN(rqn);
    document.printer.setRQY(rqy);
    document.printer.setRQR(rqr);
    document.printer.setSWDJDM(swdjdm);
    document.printer.setZSJG(zsjg);
    document.printer.setNSRQC(nsrqc);
    document.printer.setSZ(SZ);
    document.printer.setSKSSRQ(skssrq);
    document.printer.setSJJE(sjje);
    document.printer.setJEHJ(hjxxje);
    document.printer.setJEHJDX(hjdxje);
    document.printer.setTPR(tpr);
    document.printer.setBZ1(bz1);
    document.printer.setBZ2(bz2);
    document.printer.setBZ3(bz3);
    
    //新增
    document.printer.setOLD_WSPZH(old_wspzh);
    document.printer.setPMMC(pmmc);
    document.printer.setRK_TK_RQ(rk_tk_rq);

    document.printer.startPrint();
	onPrint();
}




//打印主方法
	function onPrint(){
		fnOpen();
		return false;
	}

	function fnOpen(){
		var ret= window.showModalDialog("<%=thisWarUrl%>/webapp/smsb/printConfirm.html", "", "dialogHeight: 200px;dialogWidth: 300px;status:0");
	   //alert("fnOpen: "+ret);
	   if(!ret)  {
		 alert("请确认！");
		 fnOpen();
	   }
	   if(ret=="yes"){
		printSuccess();
		return ret;
	   }
	   if(ret=="yes_second"){
		//alert("old number="+ret);
		printTab();
		return ret;
	   }
	   if(ret=="no_second"){
		//alert("get number="+ret);
		rePrint();
		return ret;
	   }
	}

	function rePrint(){
		document.all.query_wspzh.value="";
		doit('ReprintNewPH');
	}

	function printSuccess(){
		doit('PrintSuccess');
	}
</script>
<%@ include file="./include/footer.jsp"%>
</html:html>