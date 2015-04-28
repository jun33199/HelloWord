<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/control.tld" prefix="control"%>

<%@page import="com.ttsoft.common.util.SessionKey"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.SbState"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf"%>
<%@page import="com.creationstar.bjtax.qsgl.VisionLogic.form.JmsbxxForm"%>

									<%
	JmsbxxForm bForm = (JmsbxxForm)session.getAttribute("jmsbxxForm");
    String qtjmlybeizhu = bForm.getQtjmlybeizhu();
    String qsjmlbmc = bForm.getQsjmlbmc();
 %>

<HTML>
<HEAD>
<TITLE>北京市地方税务局契税减免申报显示表</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js"
	type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
	type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;

   document.forms[0].submit();
}

function doRedirect(subaction,index,type){
  document.forms[0].fwindex.value = index;
  document.forms[0].fwtype.value = type;
  document.all.operationType.value="Redirect";
  document.forms[0].subaction.value=subaction;

  document.forms[0].submit();
}

function doPrintHdtzs()
{
    window.open("/qsgl/jmsb/printJmsbHdtzs.do?sbbh="+document.forms[0].sbbh.value,"print","toolbar=no,location=yes,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=640,height=480");
}
function doUpdateHdtzsfwhm()
{
    window.open("/qsgl/jmsb/modJmsbHdtzsfwhm.do?sbbh="+document.forms[0].sbbh.value);
    document.getElementById("Printhdtzs").style.display="block";
}
function doUpdateHdtzshm()
{
    window.open("/qsgl/jmsb/modJmsbHdtzshm.do?sbbh="+document.forms[0].sbbh.value);
}

</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0
	marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
	<jsp:param name="subtitle" value="契税减免申报审核>北京市地方税务局契税减免申报显示表" />
	<jsp:param name="helpURL" value="" />
</jsp:include>


<br>
<html:form action="/jmsb/jmsbSh.do">
	<bean:define id="jmsbxxBo" name="jmsbxxForm" property="data"
		type="com.creationstar.bjtax.qsgl.model.bo.JmsbxxBo" />
	<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%"
		width="98%">
		<TBODY>
			<TR>
				<TD vAlign=top>

				<TABLE align=center cellSpacing=0 class=table-99>
					<TBODY>
						<TR>
							<TD class=1-td1>北京市地方税务局契税减免申报表</TD>
						</TR>

						<TR>
							<TD class=1-td2 vAlign=top><html:hidden
								property="operationType" value="" /> <html:hidden
								property="subaction" value="" /> <html:hidden
								property="fwindex" /> <html:hidden property="fwtype" />
							<TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
								<TBODY>
									<BR>

									<bean:define id="sbzb" name="jmsbxxForm" property="voSbzb"
										type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb" />

									<TR>

										<TD class=2-td2-t-left width="15%">批次号</TD>
										<html:hidden name="jmsbxxForm" property="pch" />
										<TD class=2-td2-t-left width="35%">
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voSbzb.drpch" /></DIV>
										</TD>

										<TD class=2-td2-t-left width="15%">减免税申报表号</TD>
										<html:hidden name="jmsbxxForm" property="sbbh" />
										<TD class=2-td2-t-center width="35%">
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voSbzb.sbbh" /></DIV>
										</TD>

									</TR>


									<TR>
										<TD class=2-td2-left width="15%">缴款方式</TD>
										<TD class=2-td2-left width="35%">
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voSbzb.jsfsmc" />
										<TD class=2-td2-left width="15%">房屋土地管理部门受理号</TD>
										<TD class=2-td2-center width="35%">
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voSbzb.fwtdbmdm" /></DIV>
										</TD>
									</TR>
								</TBODY>
							</TABLE>

							<TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
								<TBODY>
									<%
									if (jmsbxxBo.isPerson()) {
									%>
									<html:hidden property="person" value="true" />
									<TR>
										<TD class=2-td2-left width="70%" colspan="4">
										<DIV align=left>个人填写部分</DIV>
										</TD>
										<TD class=2-td2-center width="30%" colspan="2">&nbsp;</TD>

									</TR>

									<TR>
										<TD colspan="6" valign="top" width="100%">

										<table border="0" cellpadding="0" cellspacing="0"
											class="table-99" width="100%">
											<tr>
												<td width="20%" class="2-td2-left">纳税人名称</td>
												<td width="20%" class="2-td2-left">联系电话</td>
												<td width="20%" class="2-td2-left">身份证件类型</td>
												<td width="20%" class="2-td2-left">身份证件号码</td>
												<td width="10%" class="2-td2-left">国籍</td>
												<td width="10%" class="2-td2-center">产权人类型</td>
											</tr>

											<logic:iterate id="nsrdata" indexId="index" length="length"
												name="jmsbxxForm" property="nsrList">
												<tr>
													<td width="20%" class="2-td2-left"><bean:write
														name="nsrdata" property="nsrmc" /></td>
													<td width="20%" class="2-td2-left"><bean:write
														name="nsrdata" property="lxdh" />&nbsp;</td>
													<td width="20%" class="2-td2-left"><bean:write
														name="nsrdata" property="sfzjlxmc" /></td>
													<td width="20%" class="2-td2-left"><bean:write
														name="nsrdata" property="sfzjhm" /></td>
													<td width="10%" class="2-td2-left"><bean:write
														name="nsrdata" property="gjmc" /></td>
													<td width="10%" class="2-td2-center"><logic:equal
														name="nsrdata" property="cqrlx" value="1">
    共有产权人
</logic:equal> <logic:equal name="nsrdata" property="cqrlx" value="0">
    主产权人
</logic:equal></td>
												</tr>
											</logic:iterate>

										</table>
										</TD>
									</TR>
									<%
									}
									%>

									<%
									if (!jmsbxxBo.isPerson()) {
									%>
									<html:hidden property="person" value="false" />
									<TR>
										<TD class=2-td2-t-left width="15%" rowspan="4">
										<DIV align=right>非个人填</DIV>
										<DIV align=right>写部分</DIV>
										<TD class=2-td2-t-left width="20%">
										<DIV align=right>纳税计算机代码&nbsp;</DIV>
										</TD>
										<DIV align=right>&nbsp;</DIV>
										<TD class=2-td2-t-left width="20%">
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voFgrxx.jsjdm" /></DIV>
										</TD>

										<TD class=2-td2-t-left width="20%">
										<DIV align=right>纳税人类型&nbsp;</DIV>
										</TD>



										<TD colspan="2" class=2-td2-t-center width="25%">
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voFgrxx.nsrlxmc" /></DIV>
										</TD>
									</TR>
									<TR>


										<TD class=2-td2-left>
										<DIV align=right>纳税人名称&nbsp;</DIV>
										</TD>


										<TD colspan="4" class=2-td2-center
											style="word-break: break-all">
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voFgrxx.nsrmc" /></DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left>
										<DIV align=right>开户银行&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voFgrxx.khyhmc" /></DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=right>银行账号&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center>
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voFgrxx.yhzh" /></DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left>
										<DIV align=right>联系人姓名&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voFgrxx.lxrxm" /></DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=right>联系电话&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center>
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voFgrxx.lxdh" /></DIV>
										</TD>
									</TR>
									<%
									}
									%>






									<!-- 房屋基本信息-->
									<bean:define id="fwtdVo" name="jmsbxxForm" property="voTufwxx"
										type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx" />
									<tr>



										<TD class=2-td2-left rowspan="7">


										<DIV align=right>土地房屋</DIV>
										<DIV align=right>权属转移</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=right>房地产项目名称&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center
											style="word-break: break-all">
										<DIV align=left><bean:write name="fwtdVo"
											property="fdcxmmc" /></DIV>
										</TD>
									</tr>



									<TR>
										<TD class=2-td2-left>
										<DIV align=right>合约签订时间&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=left><%=DataConvert.TimeStamp2String(fwtdVo.getHtqdsj())%>
										</DIV>
										</TD>


										<TD class=2-td2-left>
										<DIV align=right>购房原因&nbsp;</DIV>
										</TD>


										<TD colspan="2" class=2-td2-center>
										<DIV align=left><bean:write name="fwtdVo"
											property="flmc" />&nbsp;</DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left>
										<DIV align=right>土地、房屋座落地址&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center
											style="word-break: break-all">
										<DIV align=left><bean:write name="fwtdVo"
											property="tdfwzldz" /></DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left>
										<DIV align=right>土地、房屋权属转移类型&nbsp;</DIV>
										</TD>
										<!--TD class=2-td2-left-->
										<TD colspan="4" class=2-td2-center
											style="word-break: break-all">
										<DIV align=left><bean:write name="fwtdVo"
											property="tdfwqszymc" />&nbsp;</DIV>
										</TD>
										<!--TD class=2-td2-left>
										<DIV align=right>房屋类别&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center>
										<DIV align=left><bean:write name="fwtdVo"
											property="fwlxmc" />&nbsp;</DIV>
										</TD-->
									</TR>
									<TR>
										<TD class=2-td2-left>
										<DIV align=right>土地、房屋权属转移面积&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=left>土地： <%=DataConvert.BigDecimal2String(fwtdVo.getTdfwqszymj())%>
										㎡</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=right>房屋建筑面积&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center>
										<DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getFwjzmj())%>
										㎡</DIV>
										</TD>
									</TR>


									<%-- %><TR>
										<TD class=2-td2-left>
										<DIV align=right>容积率&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=left>
										<%
										if (fwtdVo.getRjl() == null || fwtdVo.getRjl().equals("")) {
										%> <FONT color=red>请选择容积率</FONT> <%
 		} else {
 		if (fwtdVo.getRjl().equals("00")) {
 %> 1.0以下 <%
 		}
 		if (fwtdVo.getRjl().equals("01")) {
 %> 1.0以上含1.0 <%
 	}
 	}
 %>&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=right>土地级次&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center>
										<DIV align=left>
										<%
										if (fwtdVo.getTdjc() == null || fwtdVo.getTdjc().equals("")) {
										%> <FONT color=red>请选择土地级次</FONT>&nbsp; <%
 		} else {
 		if (fwtdVo.getTdjc().equals("01")) {
 %> 一级&nbsp; <%
 		}
 		if (fwtdVo.getTdjc().equals("02")) {
 %> 二级&nbsp; <%
 }
 %> <%
 if (fwtdVo.getTdjc().equals("03")) {
 %> 三级&nbsp; <%
 		}
 		if (fwtdVo.getTdjc().equals("04")) {
 %> 四级&nbsp; <%
 }
 %> <%
 if (fwtdVo.getTdjc().equals("05")) {
 %> 五级&nbsp; <%
 		}
 		if (fwtdVo.getTdjc().equals("06")) {
 %> 六级&nbsp; <%
 }
 %> <%
 if (fwtdVo.getTdjc().equals("07")) {
 %> 七级&nbsp; <%
 		}
 		if (fwtdVo.getTdjc().equals("08")) {
 %> 八级&nbsp; <%
 }
 %> <%
 if (fwtdVo.getTdjc().equals("09")) {
 %> 九级&nbsp; <%
 		}
 		if (fwtdVo.getTdjc().equals("10")) {
 %> 十级&nbsp; <%
 }
 %> <%
 if (fwtdVo.getTdjc().equals("11")) {
 %> 六至十级&nbsp; <%
 	}
 	}
 %> &nbsp;</DIV>
										</TD>
									</TR><% --%>



									<TR>
										<TD class=2-td2-left rowspan="2">
										<DIV align=right>成交价格</DIV>
										<TD class=2-td2-left>
										<DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getCjjgrmb())%>
										元(人民币)</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=right>税务机关核定价格&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center>
										<DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getPgjgrmb())%>
										元(人民币)</DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left>
										<DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getCjjgwb())%>
										元(外币)</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=left>币种： <bean:write name="fwtdVo"
											property="bzmc" /></DIV>
										<DIV align=left>汇率:&nbsp;<%=DataConvert.BigDecimal2String(fwtdVo.getHldm(), 5)%>
										</DIV>
										</TD>
										<TD class=2-td2-center colspan="3">
										<DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getZhjgrmb())%>
										折合元(人民币)</DIV>
										</TD>

									</tr>

									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>申请减税、免税理由&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center width="85%">
										<DIV align=left><DIV align=left><%--%><bean:write name="jmsbxxForm"
											property="qsjmlbmc" /><% --%><%=qsjmlbmc %>&nbsp;</DIV></DIV>
										</TD>
									</TR>

									<% if(qtjmlybeizhu != null && !("".equals(qtjmlybeizhu))){ %>
									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>其它减免理由&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center width="85%">
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="qtjmlybeizhu" /></DIV>
										</TD>
									</TR>
									<% }%>
									

									<TR>
										<TD class=2-td2-left>
										<DIV align=right>备注&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center>
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voSbzb.bz" /></DIV>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							<BR>




							<DIV align=center><%-- if ((jmsbxxBo.getState().equals(Constants.ZB_ZTBS_JS_TY)))
	  	     {
	  	  --%> 
	  	  <img alt=修改核定通知书号 height="22" id=xghdtzshm name=xghdtzshm onclick="doUpdateHdtzshm()"
								onmouseout=MM_swapImgRestore()
								onmouseover="MM_swapImage('xghdtzshm','','<%=static_file%>images/q-xghdtzshm2.jpg',1)"
								src="<%=static_file%>images/q-xghdtzshm1.jpg" style="CURSOR: hand" >&nbsp;&nbsp;&nbsp;&nbsp;
	  	  
	  	  <img alt=防伪号码 height="22" id=Fwhm name=Fwhm onclick="doUpdateHdtzsfwhm()"
								onmouseout=MM_swapImgRestore()
								onmouseover="MM_swapImage('Fwhm','','<%=static_file%>images/b-fwhm2.jpg',1)"
								src="<%=static_file%>images/b-fwhm1.jpg" style="CURSOR: hand" >&nbsp;&nbsp;&nbsp;&nbsp;

							<img alt=打印核定通知书 height="22" id=Printhdtzs name=Printhdtzs
								onclick="doPrintHdtzs()" onmouseout=MM_swapImgRestore()
								onmouseover="MM_swapImage('Printhdtzs','','<%=static_file%>images/q_dyhdtzs2.jpg',1)"
								src="<%=static_file%>images/q_dyhdtzs1.jpg"
								style="CURSOR: hand; display: none" >&nbsp;&nbsp;&nbsp;&nbsp; <%--
	  	     }
	  	  %>
	  	  <%   else
	  	     {
	  	  %>
	  	  
	  	  			            		<img alt=审核同意   id=tongyi name=tongyi
		onclick="doSubmitForm('Confirm')" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('tongyi','','<%=static_file%>images/q_shty2.jpg',1)"
		src="<%=static_file%>images/q_shty1.jpg" style="CURSOR: hand">
          &nbsp;&nbsp;&nbsp;&nbsp;
	  	  			            		<img alt=审核不同意   id=butongyi name=butongyi
		onclick="doSubmitForm('Reject')" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('butongyi','','<%=static_file%>images/q_shbty2.jpg',1)"
		src="<%=static_file%>images/q_shbty1.jpg" style="CURSOR: hand">    

          &nbsp;&nbsp;&nbsp;&nbsp;
         <%
             }
         --%> <IMG alt=退出 height=22 id=tuichu name=tuichu
								onclick="doSubmitForm('Return')" onmouseout=MM_swapImgRestore()
								onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
								src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand"
								width=79></DIV>
							<BR>

							</html:form> <script language="javascript" type='text/JavaScript'>

</script> <%@ include file="../include/Bottom.jsp"%>
</BODY>
</HTML>
