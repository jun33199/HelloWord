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
<TITLE>�����еط�˰�����˰�����걨��ʾ��</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js"
	type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
	type=text/JavaScript></SCRIPT>
<script language="javascript" src="../js/qscommon.js"></script>
<script language="javascript" src="../js/gmit_selectcontrol.js"></script>

<script LANGUAGE="javascript" src="../js/Const.js"></script>
<script LANGUAGE="javascript" src="../js/DTColumn.js"></script>
<script LANGUAGE="javascript" src="../js/DynamicTable.js"></script>
<script LANGUAGE="javascript" src="../js/DataAwareTable.js"></script>
<script LANGUAGE="javascript" src="../js/DTColumn_Check.js"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  
	if(operationType=="Savesp" )
	{
		if (document.forms[0].sprq.value == "")
	  	{
	  	   alert("�������ڲ���Ϊ�գ����������룡");
	  	   document.forms[0].sprq.focus();
	  	   return false;
	  	}
	  	
	  	if(!checkDate(document.forms[0].sprq.value)){
	  	   alert("�������ڲ���ȷ����ȷ��ʽΪyyyymmdd�����ܴ���2100��С��1900�꣬���������룡");
	  	   document.forms[0].sprq.focus();
	  	   return false;
	  	}
	  	
	  	if (!cmpDate(document.forms[0].sprq.value,document.forms[0].xtdqsj.value))
	  	{
	  	   alert("�������ڲ��ܴ��ڵ�ǰʱ�䣬���������룡");
	  	   document.forms[0].sprq.focus();
	  	   return false;
	  	} 
	
		document.forms[0].spjgmc.value = document.forms[0].spjg.options[document.forms[0].spjg.selectedIndex].text;
		document.forms[0].spztmc.value = document.forms[0].spzt.options[document.forms[0].spzt.selectedIndex].text;
		
	}
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

</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0
	marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
	<jsp:param name="subtitle" value="��˰�����걨���>�����еط�˰�����˰�����걨��ʾ��" />
	<jsp:param name="helpURL" value="" />
</jsp:include>


<br>
<html:form action="/jmsb/jmsbSp.do">
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
							<TD class=1-td1>�����еط�˰�����˰�����걨��</TD>
						</TR>

						<TR>
							<TD class=1-td2 vAlign=top><html:hidden
								property="operationType" value="" /> <html:hidden
								property="subaction" value="" /> <html:hidden
								property="fwindex" /> <html:hidden property="fwtype" />
							<html:hidden property="xtdqsj" />
							<TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
								<TBODY>
									<BR>

									<bean:define id="sbzb" name="jmsbxxForm" property="voSbzb"
										type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb" />

									<TR>

										<TD class=2-td2-t-left width="15%">���κ�</TD>
										<html:hidden name="jmsbxxForm" property="pch" />
										<TD class=2-td2-t-left width="35%">
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voSbzb.drpch" /></DIV>
										</TD>

										<TD class=2-td2-t-left width="15%">����˰�걨���</TD>
										<html:hidden name="jmsbxxForm" property="sbbh" />
										<TD class=2-td2-t-center width="35%">
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voSbzb.sbbh" /></DIV>
										</TD>

									</TR>


									<TR>
										<TD class=2-td2-left width="15%">�ɿʽ</TD>
										<TD class=2-td2-left width="35%">
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voSbzb.jsfsmc" />
										<TD class=2-td2-left width="15%">�������ع����������</TD>
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
										<DIV align=left>������д����</DIV>
										</TD>
										<TD class=2-td2-center width="30%" colspan="2">&nbsp;</TD>

									</TR>

									<TR>
										<TD colspan="6" valign="top" width="100%">

										<table border="0" cellpadding="0" cellspacing="0"
											class="table-99" width="100%">
											<tr>
												<td width="20%" class="2-td2-left">��˰������</td>
												<td width="20%" class="2-td2-left">��ϵ�绰</td>
												<td width="20%" class="2-td2-left">���֤������</td>
												<td width="20%" class="2-td2-left">���֤������</td>
												<td width="10%" class="2-td2-left">����</td>
												<td width="10%" class="2-td2-center">��Ȩ������</td>
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
    ���в�Ȩ��
</logic:equal> <logic:equal name="nsrdata" property="cqrlx" value="0">
    ����Ȩ��
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
										<DIV align=right>�Ǹ�����</DIV>
										<DIV align=right>д����</DIV>
										<TD class=2-td2-t-left width="20%">
										<DIV align=right>��˰���������&nbsp;</DIV>
										</TD>
										<DIV align=right>&nbsp;</DIV>
										<TD class=2-td2-t-left width="20%">
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voFgrxx.jsjdm" /></DIV>
										</TD>

										<TD class=2-td2-t-left width="20%">
										<DIV align=right>��˰������&nbsp;</DIV>
										</TD>



										<TD colspan="2" class=2-td2-t-center width="25%">
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voFgrxx.nsrlxmc" /></DIV>
										</TD>
									</TR>
									<TR>


										<TD class=2-td2-left>
										<DIV align=right>��˰������&nbsp;</DIV>
										</TD>


										<TD colspan="4" class=2-td2-center
											style="word-break: break-all">
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voFgrxx.nsrmc" /></DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left>
										<DIV align=right>��������&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voFgrxx.khyhmc" /></DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=right>�����˺�&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center>
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voFgrxx.yhzh" /></DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left>
										<DIV align=right>��ϵ������&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voFgrxx.lxrxm" /></DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=right>��ϵ�绰&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center>
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voFgrxx.lxdh" /></DIV>
										</TD>
									</TR>
									<%
									}
									%>






									<!-- ���ݻ�����Ϣ-->
									<bean:define id="fwtdVo" name="jmsbxxForm" property="voTufwxx"
										type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx" />
									<tr>



										<TD class=2-td2-left rowspan="7">


										<DIV align=right>���ط���</DIV>
										<DIV align=right>Ȩ��ת��</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=right>���ز���Ŀ����&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center
											style="word-break: break-all">
										<DIV align=left><bean:write name="fwtdVo"
											property="fdcxmmc" /></DIV>
										</TD>
									</tr>



									<TR>
										<TD class=2-td2-left>
										<DIV align=right>��Լǩ��ʱ��&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=left><%=DataConvert.TimeStamp2String(fwtdVo.getHtqdsj())%>
										</DIV>
										</TD>


										<TD class=2-td2-left>
										<DIV align=right>����ԭ��&nbsp;</DIV>
										</TD>


										<TD colspan="2" class=2-td2-center>
										<DIV align=left><bean:write name="fwtdVo"
											property="flmc" />&nbsp;</DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left>
										<DIV align=right>���ء����������ַ&nbsp;</DIV>
										</TD>
										<TD colspan="4" class=2-td2-center
											style="word-break: break-all">
										<DIV align=left><bean:write name="fwtdVo"
											property="tdfwzldz" /></DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left>
										<DIV align=right>���ء�����Ȩ��ת������&nbsp;</DIV>
										</TD>
										<!--TD class=2-td2-left-->
										<TD colspan="4" class=2-td2-center
											style="word-break: break-all">
										<DIV align=left><bean:write name="fwtdVo"
											property="tdfwqszymc" />&nbsp;</DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left>
										<DIV align=right>���ء�����Ȩ��ת�����&nbsp;</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=left>���أ� <%=DataConvert.BigDecimal2String(fwtdVo.getTdfwqszymj())%>
										�O</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=right>���ݽ������&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center>
										<DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getFwjzmj())%>
										�O</DIV>
										</TD>
									</TR>



									<TR>
										<TD class=2-td2-left rowspan="2">
										<DIV align=right>�ɽ��۸�</DIV>
										<TD class=2-td2-left>
										<DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getCjjgrmb())%>
										Ԫ(�����)</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=right>˰����غ˶��۸�&nbsp;</DIV>
										</TD>
										<TD colspan="2" class=2-td2-center>
										<DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getPgjgrmb())%>
										Ԫ(�����)</DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left>
										<DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getCjjgwb())%>
										Ԫ(���)</DIV>
										</TD>
										<TD class=2-td2-left>
										<DIV align=left>���֣� <bean:write name="fwtdVo"
											property="bzmc" /></DIV>
										<DIV align=left>����:&nbsp;<%=DataConvert.BigDecimal2String(fwtdVo.getHldm(), 5)%>
										</DIV>
										</TD>
										<TD class=2-td2-center colspan="3">
										<DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getZhjgrmb())%>
										�ۺ�Ԫ(�����)</DIV>
										</TD>

									</tr>

									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>�����˰����˰����&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center width="85%">
										<DIV align=left><DIV align=left><%--%><bean:write name="jmsbxxForm"
											property="qsjmlbmc" /><% --%><%=qsjmlbmc %>&nbsp;</DIV></DIV>
										</TD>
									</TR>

									<% if(qtjmlybeizhu != null && !("".equals(qtjmlybeizhu))){ %>
									<TR>
										<TD class=2-td2-left width="15%";>
										<DIV align=right>������������&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center width="85%">
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="qtjmlybeizhu" /></DIV>
										</TD>
									</TR>
									<% }%>

									<TR>
										<TD class=2-td2-left>
										<DIV align=right>��ע&nbsp;</DIV>
										</TD>
										<TD colspan="5" class=2-td2-center>
										<DIV align=left>&nbsp;<bean:write name="jmsbxxForm"
											property="voSbzb.bz" /></DIV>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							
							<BR>


							<TABLE align=center border=0 cellSpacing=0 class=tabled-99 width="99%">
								<TBODY>
									<TR>
										<TD class=2-td2-t-left colspan="3">��ǰ״̬</TD>
										<TD class=2-td2-t-center colspan="3">
										<DIV align=left>&nbsp;<%=SbState.getStateName(jmsbxxBo.getVoSbzb()
									.getZtbs())%></DIV>
										</TD>
									</TR>
									<TR>
										<TD class=2-td2-left colspan="6">¼���������</TD>
									</TR>
									<TR>
										<TD class=2-td2-left width="12%">��������</TD>
										<TD class=2-td2-left width="25%">
											<DIV align=left>&nbsp;
											<html:hidden property="spjgmc" />
											<bean:define id="spjgList" name="jmsbxxForm"
												property="spjgList" /> 
											<html:select
												property="spjg" >
												<html:options collection="spjgList" labelProperty="swjgzzjgmc"
													property="swjgzzjgdm" />
											</html:select>
											</DIV>
										</TD>
										<TD class=2-td2-left width="12%">�������</TD>
										<TD class=2-td2-left width="25%">
											<DIV align=left>&nbsp;
											<html:hidden property="spztmc" />
											<bean:define id="spztList" name="jmsbxxForm"
												property="spztList" /> 
											<html:select
												property="spzt" >
												<html:options collection="spztList" labelProperty="bz"
													property="ztbs" />
											</html:select>
											</DIV>
										</TD>
										<TD class=2-td2-left width="12%">��������(yyyymmdd)</TD>
										<TD class=2-td2-center width="25%">
											<DIV align=left>&nbsp;<html:text name="jmsbxxForm"
												property="sprq" maxlength="8"/><FONT color=red>*</FONT></DIV>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							<BR>
							<BR>

							<DIV align=center>&nbsp;&nbsp;&nbsp;&nbsp;
							<IMG alt=�鿴�˶�֪ͨ�� height="22"
								 id=Printhdtzs name=Printhdtzs onclick="doPrintHdtzs()"
								onmouseout=MM_swapImgRestore()
								onmouseover="MM_swapImage('Printhdtzs','','<%=static_file%>images/q_ckhdtzs2.jpg',1)"
								src="<%=static_file%>images/q_ckhdtzs1.jpg"
								style="CURSOR: hand" >&nbsp;&nbsp;&nbsp;&nbsp;
								
							<IMG alt=���� height=22 id=baocun
								name=baocun onclick="doSubmitForm('Savesp')"
								onmouseout=MM_swapImgRestore()
								onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
								src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand"
								width=79>&nbsp;&nbsp;&nbsp;&nbsp;

							<IMG alt=�˳� height=22 id=tuichu name=tuichu
								onclick="doSubmitForm('Return')" onmouseout=MM_swapImgRestore()
								onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
								src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand"
								width=79></DIV>
							<BR>

							</html:form> <script language="javascript" type='text/JavaScript'>

</script> <%@ include file="../include/Bottom.jsp"%>
</BODY>
</HTML>
