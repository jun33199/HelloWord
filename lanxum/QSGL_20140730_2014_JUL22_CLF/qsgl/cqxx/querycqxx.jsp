<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="java.util.*"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.CqxxbForm"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Cqxxb"%>
<HTML>
<HEAD>
<TITLE>��ѯ����</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js"
	type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
	type=text/JavaScript></SCRIPT>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_file%>js/judge.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="<%=static_file%>js/calculate.js"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.forms[0].operationType.value=operationType;
  if(operationType=="Query" || operationType=="Export")
  {
    if (document.forms[0].lrrqq.value != "" && !checkDate(document.forms[0].lrrqq.value))
    {
      alert("¼��ʱ�����ʽ����ȷ�����������룡");
      document.forms[0].lrrqq.focus();
      return false;
    }
    if (document.forms[0].lrrqz.value != "" && !checkDate(document.forms[0].lrrqz.value))
    {
      alert("¼��ʱ��ֹ��ʽ����ȷ�����������룡");
      document.forms[0].lrrqz.focus();
      return false;
    }
    if (document.forms[0].lrrqq.value != "" &&
    document.forms[0].lrrqz.value != "" &&
    !cmpDate(document.forms[0].lrrqq.value,document.forms[0].lrrqz.value))
  	{
  	   alert("¼��ʱ����ʱ�䲻�ܴ���¼��ʱ��ֹʱ�䣬���������룡");
  	   document.forms[0].lrrqq.focus();
  	   return false;
  	}
  }
  document.forms[0].submit();
  return true;
}
</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0
	marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
	<jsp:param name="subtitle" value="��Ǩ��Ϣ&gt;��ѯ" />
	<jsp:param name="helpURL" value="" />
</jsp:include>
<SCRIPT language=javascript>
<!--

function popUp(){
    var server = '192.100.99.100';
    var port   = '80';
    props=window.open('<%=static_file%>' + '/about/about.htm','poppage','toolbars=0,scrollbars=0,location=0,statusbara=0,menubars=0,resizable=0,width=500,height=267');
}

//-->
</SCRIPT>
<br>
<%CqxxbForm form = (CqxxbForm) session.getAttribute("cqxxbForm");
			HashMap szqxMap = form.getSzqxMap();
			com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData) session
					.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
			String qxdm = userdata.getSsdwdm().substring(0, 2);
%>
<table align=center border=0 cellPadding=0 cellSpacing=0 height="80%"
	width=98%>
	<TBODY>
		<TR>
			<td vAlign=top>
			<table align=center cellSpacing=0 class=table-99>
				<TBODY>
					<TR>
						<td class=1-td1>��Ǩ��Ϣ-��ѯ</td>
					</TR>
					<TR>
						<td class=1-td2 vAlign=top><html:form action="/cqxx/cqxxQuery.do">
							<input type="hidden" name="operationType" value="">
							<input type="hidden" name="sfwh" value="0">
							<logic:equal name="cqxxbForm" property="status" scope="session"
								value="Result">
								<input type="hidden" name="changePage"
									value='<bean:write name="cqxxbForm" property="queryCache.currentPageNum"/>'>
								<input type="hidden" name="pageCount"
									value='<bean:write name="cqxxbForm" property="queryCache.pageCount"/>'>
							</logic:equal>
							<br>
							<table border="0" cellSpacing=0 class=table-60>
								<TBODY>
									<TR>
										<td class="2-td1-center">��������</td>
										<td class="2-td2-t-right">
										<DIV align=left><select name="szqx">
											<option value="00">ȫ��</option>
											<%Object[] qxdmArray = szqxMap.keySet().toArray();
				for (int i = 0; i < qxdmArray.length; i++) {
					String qxdmTmp = (String) qxdmArray[i];
					if (qxdmTmp.equals(qxdm)) {
						out.print("<option value=\"" + qxdmTmp + "\" selected>"
								+ szqxMap.get(qxdmArray[i]) + "</option>");
					} else {
						out.print("<option value=\"" + qxdmTmp + "\">"
								+ szqxMap.get(qxdmArray[i]) + "</option>");
					}
				}

				%>
											</selct></DIV>
										</td>
									</TR>
									<TR>
										<td class=2-td1-Bcenter width="50%">����Ǩ������&nbsp;</td>
										<td class="2-td2-right" width="50%">
										<DIV align=left><html:text property="bcqrmc" maxlength="" /></DIV>
										</td>
									</TR>
									<TR>
										<td class=2-td1-Bcenter width="50%">����Ǩ������&nbsp;</td>
										<td class="2-td2-right" width="50%">
										<DIV align=left><html:select property="bcqrlxdm">
											<html:option value="00">ȫ��</html:option>
											<html:option value="0">����</html:option>
											<html:option value="1">�Ǹ���</html:option>
										</html:select></DIV>
										</td>
									</TR>
									<TR>
										<td class=2-td1-Bcenter width="50%">����Ǩ��֤������&nbsp;</td>
										<td class="2-td2-right" width="50%">
										<DIV align=left><html:text property="zjhm" maxlength="" /></DIV>
										</td>
									</TR>
									<tr>
										<td class="2-td1-Bcenter">����Ǩ��ϸ��ַ</td>
										<td class="2-td2-right">
										<DIV align=left><html:text property="cqxxdz" maxlength="" />
										</DIV>
										</td>
									</tr>
									<TR>
										<td class=2-td1-Bcenter width="50%">�������&nbsp;</td>
										<td class="2-td2-right" width="50%">
										<DIV align=left><html:text property="bcjeq" maxlength="8"
											tabindex="0" size="8" />-<html:text property="bcjez"
											maxlength="8" tabindex="0" size="8" /></DIV>
										</td>
									</TR>
									<TR>
										<td class=2-td1-Bcenter width="50%">¼������&nbsp;</td>
										<td class="2-td2-right" width="50%">
										<DIV align=left><html:text property="lrrqq" maxlength="8"
											tabindex="0" size="8" />-<html:text property="lrrqz"
											maxlength="8" tabindex="0" size="8" />yyyyMMdd</DIV>
										</td>
									</TR>
									<tr>
										<td class="2-td1-Bcenter">��Ǩ��Ŀ����</td>
										<td class="2-td2-right">
										<DIV align=left><html:text property="cqxmmc" maxlength="" />
										</DIV>
										</td>
									</tr>
							</table>
							<BR>

							<DIV align=center><IMG name="query"
								onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
								onMouseOut="MM_swapImgRestore()"
								src="<%=static_file%>images/chaxun1.jpg"
								onclick="doSubmitForm('Query');" width="79" height="22"
								id="chaxun1" alt="��ѯ" style="cursor:hand">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <IMG name="toexcel"
								onMouseOver="MM_swapImage('toexcel1','','<%=static_file%>images/b-bcdexcel2.jpg',1)"
								onMouseOut="MM_swapImgRestore()"
								src="<%=static_file%>images/b-bcdexcel1.jpg"
								onclick="doSubmitForm('Export');" height="22" id="toexcel1"
								alt="���浽Excel" style="cursor:hand">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <IMG name="back"
								onclick="doSubmitForm('Return');"
								onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
								onMouseOut="MM_swapImgRestore()"
								src="<%=static_file%>images/tuichu1.jpg" width="79" height="22"
								id="tuichu1" alt="�˳�" style="cursor:hand"></DIV>
							<BR>

							<logic:equal name="cqxxbForm" property="status" scope="session"
								value="Result">
								<table cellspacing="0" class="table-99">
									<tr>
										<td class="2-td2-t-left">��ѯ���</td>
										<!--��������Ϊ�գ���ʾû�м�¼-->
										<logic:equal name="cqxxbForm" property="queryCache.pageCount"
											scope="session" value="0">
											<td class="2-td2-t-center">
											<div align="center">û����Ҫ�ļ�¼�������²�ѯ</div>
											</td>
										</logic:equal>
										<logic:notEqual name="cqxxbForm"
											property="queryCache.pageCount" scope="session" value="0">
											<td class="2-td2-t-center">
											<div align="right">��<bean:write name="cqxxbForm"
												property="queryCache.countNumber" />����¼ ��<bean:write
												name="cqxxbForm" property="queryCache.currentPageNum" />/ <bean:write
												name="cqxxbForm" property="queryCache.pageCount" />ҳ <input
												type="image" name="beginpage" value="��һҳ" alt="��һҳ"
												onclick="javascript:return FN_QSChangePage('diyiye');"
												onMouseOver="MM_swapImage('diyiye','','<%=static_file%>images/diyiye2.jpg',1)"
												onMouseOut="MM_swapImgRestore()"
												src="<%=static_file%>images/diyiye1.jpg" id="diyiye"> <input
												type="image" name="endpage" value="���һҳ" alt="���һҳ"
												onclick="javascript:return FN_QSChangePage('zuihouyiye');"
												onMouseOver="MM_swapImage('zuimoye','','<%=static_file%>images/zuimoye2.jpg',1)"
												onMouseOut="MM_swapImgRestore()"
												src="<%=static_file%>images/zuimoye1.jpg" id="zuimoye"> <!--���ֻ��һҳ�����水ť����ʾ-->
											<logic:equal name="cqxxbForm" property="queryCache.isOnePage"
												scope="session" value="false">
												<!--����ǵ�һҳ��ǰҳ����ʾ-->
												<logic:equal name="cqxxbForm" property="queryCache.hasPrev"
													scope="session" value="true">
													<input type="image" name="frontpage" value="ǰҳ" alt="ǰҳ"
														onclick="javascript:return FN_QSChangePage('shangyiye');"
														onMouseOver="MM_swapImage('shangyiye','','<%=static_file%>images/shangyiye2.jpg',1)"
														onMouseOut="MM_swapImgRestore()"
														src="<%=static_file%>images/shangyiye1.jpg" id="shangyiye">
												</logic:equal>
												<!--��������һҳ����ҳ����ʾ-->
												<logic:equal name="cqxxbForm" property="queryCache.hasNext"
													scope="session" value="true">
													<input type="image" name="backpage" value="��ҳ" alt="��ҳ"
														onclick="javascript:return FN_QSChangePage('xiayiye');"
														onMouseOver="MM_swapImage('xiayiye','','<%=static_file%>images/xaiyiye2.jpg',1)"
														onMouseOut="MM_swapImgRestore()"
														src="<%=static_file%>images/xaiyiye1.jpg" id="xiayiye">
												</logic:equal>
											</logic:equal></div>
											</td>
										</logic:notEqual>
									</tr>
								</table>
								<!--�����¼���Ϊ�գ�����ʾ-->
								<logic:notEqual name="cqxxbForm" property="queryCache.pageCount"
									scope="session" value="0">
									<br>
									<table cellspacing="0" class="table-99">
										<tr>
											<td class="2-td1-left">��Ǩ������</td>
											<td class="2-td1-left">��Ǩ��Ŀ����</td>
											<td class="2-td1-left">��Ǩ���֤</td>
											<td class="2-td1-left">��Ǩ��Χ</td>
											<td class="2-td1-left">��������</td>
											<td class="2-td1-left">����Ǩ������</td>
											<td class="2-td1-left">����Ǩ��֤������</td>
											<td class="2-td1-left">����Ǩ��֤������</td>
											<td class="2-td1-left">����Ǩ��ϸ��ַ</td>
											<td class="2-td1-left">�������</td>
											<td class="2-td1-left">��������</td>
											<td class="2-td1-left">�������</td>
											<td class="2-td1-left">����������ϸ�����ַ</td>
											<td class="2-td1-left">����������</td>
											<td class="2-td1-left">¼��ʱ��</td>
											<td class="2-td1-left">¼�����</td>
											<td class="2-td1-center">&nbsp;</td>
										</tr>
										<logic:iterate id="data" indexId="index" length="length"
											name="cqxxbForm" property="queryCache.currentPage"
											type="com.creationstar.bjtax.qsgl.BizLogic.vo.Cqxxb">
											<tr>
												<td class="2-td2-left">
												<div align="left"><bean:write name="data" property="cqrmc" />
												</td>
												<td class="2-td2-left">&nbsp;<bean:write name="data"
													property="cqxmmc" /></td>
												<td class="2-td2-left">&nbsp;<bean:write name="data"
													property="cqxkzh" /></td>
												<td class="2-td2-left">&nbsp;<bean:write name="data"
													property="cqfw" /></td>
												<td class="2-td2-left">&nbsp;<bean:write name="data"
													property="szqx" /></td>
												<td class="2-td2-left">&nbsp;<bean:write name="data"
													property="bcqrmc" /></td>
												<td class="2-td2-left">&nbsp;<bean:write name="data"
													property="zjhm" /></td>
												<td class="2-td2-left">&nbsp;<bean:write name="data"
													property="zjlxmc" /></td>
												<td class="2-td2-left">&nbsp;<bean:write name="data"
													property="cqxxdz" /></td>
												<td class="2-td2-left">&nbsp;<%=DataConvert.BigDecimal2String(data
											.getBcje())%></td>
												<td class="2-td2-left">&nbsp;<bean:write name="data"
													property="bclxmc" /></td>
												<td class="2-td2-left">&nbsp;<%=DataConvert.BigDecimal2String(data
											.getBcmj())%></td>
												<td class="2-td2-left">&nbsp;<bean:write name="data"
													property="bcfwdz" /></td>
												<td class="2-td2-left">&nbsp;<bean:write name="data"
													property="gjrmc" /></td>
												<td class="2-td2-left">&nbsp;<%=DataConvert.TimeStamp2String(data
											.getLrrq())%></td>
												<td class="2-td2-left">&nbsp;<bean:write name="data"
													property="swjgzzjgmc" /></td>
												<td class="2-td2-center">&nbsp; <a target="_blank"
													href="<%=response.encodeURL("/qsgl/cqxx/cqxxQuery.do?operationType=Print&cqxxbh=")%><bean:write name="data" property="cqxxbh"/>">
												��ӡ </a></td>
											</tr>
										</logic:iterate>
									</table>
									<br>
									<table cellspacing="0" class="table-99">
										<tr>
											<td align="right"><br>
											<input type="image" name="beginpage" value="��һҳ" alt="��һҳ"
												onclick="javascript:return FN_QSChangePage('diyiye');"
												onMouseOver="MM_swapImage('diyiye1','','<%=static_file%>images/diyiye2.jpg',1)"
												onMouseOut="MM_swapImgRestore()"
												src="<%=static_file%>images/diyiye1.jpg" width="79"
												height="22" id="diyiye1"> <input type="image" name="endpage"
												value="���һҳ" alt="���һҳ"
												onclick="javascript:return FN_QSChangePage('zuihouyiye');"
												onMouseOver="MM_swapImage('zuimoye1','','<%=static_file%>images/zuimoye2.jpg',1)"
												onMouseOut="MM_swapImgRestore()"
												src="<%=static_file%>images/zuimoye1.jpg" width="79"
												height="22" id="zuimoye1"> <!--���ֻ��һҳ�����水ť����ʾ--> <logic:equal
												name="cqxxbForm" property="queryCache.isOnePage"
												scope="session" value="false">
												<!--����ǵ�һҳ��ǰҳ����ʾ-->
												<logic:equal name="cqxxbForm" property="queryCache.hasPrev"
													scope="session" value="true">
													<input type="image" name="frontpage" value="ǰҳ" alt="ǰҳ"
														onclick="javascript:return FN_QSChangePage('shangyiye');"
														onMouseOver="MM_swapImage('shangyiye1','','<%=static_file%>images/shangyiye2.jpg',1)"
														onMouseOut="MM_swapImgRestore()"
														src="<%=static_file%>images/shangyiye1.jpg" width="79"
														height="22" id="shangyiye1">
												</logic:equal>
												<!--��������һҳ����ҳ����ʾ-->
												<logic:equal name="cqxxbForm" property="queryCache.hasNext"
													scope="session" value="true">
													<input type="image" name="backpage" value="��ҳ" alt="��ҳ"
														onclick="javascript:return FN_QSChangePage('xiayiye');"
														onMouseOver="MM_swapImage('xiayiye1','','<%=static_file%>images/xaiyiye2.jpg',1)"
														onMouseOut="MM_swapImgRestore()"
														src="<%=static_file%>images/xaiyiye1.jpg" width="79"
														height="22" id="xiayiye1">
												</logic:equal>
											</logic:equal></td>
										</tr>
									</table>
								</logic:notEqual>
								</div>
							</logic:equal>

						</html:form> <%@ include file="../include/Bottom.jsp"%>
</BODY>
</HTML>
