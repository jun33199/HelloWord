<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<HTML>
<HEAD>
<TITLE>������������Ϣ��ѯ</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
	type=text/JavaScript></SCRIPT>
<script language=JavaScript src="../js/clfgl_util.js"
	type="text/javascript"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
<LINK href="../css/clfgl.css" rel=stylesheet type=text/css>


</HEAD>
<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0
	marginheight="0" marginwidth="0">
	<jsp:include page="/include/Header.jsp" flush="true">
		<jsp:param name="subtitle" value="����������>������������Ϣ��ѯ" />
		<jsp:param name="helpURL" value="" />
	</jsp:include>


	<TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=100%>
		<tr>
			<td vAlign=top>
				<TABLE align=center border=0 cellSpacing=0 height="100%" width=100%>
					<tr>
						<td vAlign=top>

							<TABLE align=center cellSpacing=0 class=table-99>
								<tr>
									<td class=1-td1>���������ײ�ѯ</td>
								</tr>
								<tr>
									<td class=1-td2><br> <html:form
											action="/clfgl/clfjyxxcx.do" type="POST">
											<html:hidden name="clfxxcjForm" property="operationType"
												value="" />
											<logic:equal name="clfjyxxCXForm" property="status"
												scope="session" value="Result">
												<input type="hidden" name="changePage"
													value='<bean:write name="clfjyxxCXForm" property="queryCache.currentPageNum"/>'>
												<input type="hidden" name="pageCount"
													value='<bean:write name="clfjyxxCXForm" property="queryCache.pageCount"/>'>
											</logic:equal>

											<table border=0 cellSpacing=0 class=tabled-99 width="100%">
												<tr>
													<td class=2-td2-t-left width="10%">��ʼ����</td>
													<td class=2-td2-t-left width="20%"><div align="left">
															<html:text onchange="isDate(this);" name="clfjyxxCXForm"
																property="query_qsrq" />
														</div></td>
													<td class=2-td2-t-left width="10%">��ֹ����</td>
													<td class=2-td2-t-left width="20%"><div align="left">
															<html:text onchange="isDate(this);" name="clfjyxxCXForm"
																property="query_jzrq" />
														</div></td>
													<td class=2-td2-t-left width="10%">��ͬ���</td>
													<td class=2-td2-t-left width="20%"><div align="left">
															<html:text name="clfjyxxCXForm" property="query_htbh" />
														</div></td>
													<td class=2-td2-t-center rowspan="3"><IMG alt=��ѯ
														height=22 id=chaxun1 name=chaxun1
														onclick="doAction('Query');"
														onmouseout=MM_swapImgRestore()
														onmouseover="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
														src="<%=static_file%>images/chaxun1.jpg"
														style="CURSOR: hand" width=79></td>
												</tr>
												<tr>
													<td class=2-td2-left>��������</td>
													<td class=2-td2-left><div align="left">
															<html:text name="clfjyxxCXForm" property="query_sellerN" />
														</div></td>
													<td class=2-td2-left>������</td>
													<td class=2-td2-left><div align="left">
															<html:text name="clfjyxxCXForm" property="query_buyerN" />
														</div></td>
													<td class=2-td2-left>����Ȩ��ת������</td>
													<td class=2-td2-left><div align="left">
															<html:select  name="clfjyxxCXForm" property="query_fwqszylx" style='width:50%'>
																<html:option value="">��ѡ��</html:option>
																<html:option value="05">���ݽ���</html:option>
																<html:option value="04">��������</html:option>
																<html:option value="06">��Ժ�ж�</html:option>
																<html:option value="03">��������</html:option>
															</html:select>
														</div></td>
												</tr>
												<tr>
													<td class=2-td2-left>��С�������</td>
													<td class=2-td2-left><div align="left">
															<html:text name="clfjyxxCXForm" property="query_minJzmj" />
														</div></td>
													<td class=2-td2-left>��������</td>
													<td class=2-td2-left><div align="left">
															<html:text name="clfjyxxCXForm" property="query_maxJzmj" />
														</div></td>
													<td class=2-td2-left colspan="2"> <div align="left">
															&nbsp;
														</div></td>
												</tr>
											</table>

											<br>
											<table border=0 cellSpacing=0 class=tabled-99 width="100%">
																			<tr>
									<td colspan="9">
										<table cellspacing="0" class="table-99">
											<tr>
												<td width="45%">
													<hr width="100%" class="hr1" size=1>
												</td>
												<td width="10%" align="left" class="black9">
													<div align="center">
														<strong>��ѯ���</strong>
													</div>
												</td>
												<td width="45%">
													<hr width="100%" class="hr1" size=1>
												</td>
											</tr>
										</table>
									</td>
								</tr>
												<tr>
													<td class=2-td1-left>��ͬ���</td>
													<td class=2-td1-left>��������</td>
													<td class=2-td1-left>������</td>
													<td class=2-td1-left>���������ַ</td>
													<td class=2-td1-left>����Ȩ��ת������</td>
													<td class=2-td1-left>�������</td>
													<td class=2-td1-left>��������</td>
													<td class=2-td1-left>��ͬ�ܼ�</td>
													<td class=2-td1-left>����˰��ʵ�ɽ��</td>
													<td class=2-td1-center>��˰���ܶ�</td>
												</tr>

												<%-- 									<logic:iterate   name ="clfjyxxCXForm" property="resList"  id="data" indexId="index">
									<bean:define id="cjxx" name="data" property="clfxxcjBo" />
									
										<tr>
											<td class=2-td2-left><bean:write name ="cjxx" property="allSellerNames4jyxxcx"/>&nbsp;</td>
											<td class=2-td2-left><bean:write name ="cjxx" property="allBuyerNames4jyxxcx"/>&nbsp;</td>
											<td class=2-td2-left><bean:write name ="cjxx" property="fwzldz"/>&nbsp;</td>
											<td class=2-td2-left><bean:write name ="cjxx" property="fwqszylx"/>&nbsp;</td>
											<td class=2-td2-left><bean:write name ="cjxx" property="fwjzmj"/>&nbsp;</td>
											<td class=2-td2-left><bean:write name ="cjxx" property="htwsqyrq"/>&nbsp;</td>
											<td class=2-td2-left><bean:write name ="cjxx" property="htzj"/>&nbsp;</td>
											<td class=2-td2-left><bean:write name ="data" property="all_ynse_hj"/>&nbsp;</td>
											<td class=2-td2-center>&nbsp;</td>	
										</tr>
									</logic:iterate>	 --%>
												<logic:notEqual name="clfjyxxCXForm"
													property="queryCache.pageCount" scope="session" value="0">
													<logic:iterate name="clfjyxxCXForm"
														property="queryCache.currentPage" id="data"
														indexId="index">
														<bean:define id="cjxx" name="data" property="clfxxcjBo" />

														<tr>
															<td class=2-td2-left><bean:write name="cjxx"
																	property="htbh" />&nbsp;</td>
															<td class=2-td2-left><bean:write name="cjxx"
																	property="allSellerNames4jyxxcx" />&nbsp;</td>
															<td class=2-td2-left><bean:write name="cjxx"
																	property="allBuyerNames4jyxxcx" />&nbsp;</td>
															<td class=2-td2-left><bean:write name="cjxx"
																	property="fwzldz" />&nbsp;</td>
															<td class=2-td2-left><bean:write name="cjxx"
																	property="fwqszylxmc" />&nbsp;</td>
															<td class=2-td2-left><bean:write name="cjxx"
																	property="fwjzmj" />&nbsp;</td>
															<td class=2-td2-left><bean:write name="cjxx"
																	property="htwsqyrq" />&nbsp;</td>
															<td class=2-td2-left><bean:write name="cjxx"
																	property="htzj" />&nbsp;</td>
															<td class=2-td2-left><bean:write name="data"
																	property="all_sjje_hj" />&nbsp;</td>
															<td class=2-td2-center><bean:write name="data"
																	property="all_qs_ynse_hj" />&nbsp;</td>
														</tr>
													</logic:iterate>



												</logic:notEqual>


											</table>
											<br>
											<logic:notEqual name="clfjyxxCXForm"
												property="queryCache.pageCount" scope="session" value="0">
												<table cellspacing="0" class="table-99">
													<tr>
														<logic:notEqual name="clfjyxxCXForm"
															property="queryCache.pageCount" scope="session" value="0">
															<td class="2-td2-t-center">
																<div align="right">
																	��
																	<bean:write name="clfjyxxCXForm"
																		property="queryCache.countNumber" />
																	����¼ ��
																	<bean:write name="clfjyxxCXForm"
																		property="queryCache.currentPageNum" />
																	/
																	<bean:write name="clfjyxxCXForm"
																		property="queryCache.pageCount" />
																	ҳ <input type="image" name="beginpage" value="��һҳ"
																		alt="��һҳ"
																		onclick="javascript:return FN_QSChangePage('diyiye');"
																		onMouseOver="MM_swapImage('diyiye','','<%=static_file%>images/diyiye2.jpg',1)"
																		onMouseOut="MM_swapImgRestore()"
																		src="<%=static_file%>images/diyiye1.jpg" id="diyiye">
																	<input type="image" name="endpage" value="���һҳ"
																		alt="���һҳ"
																		onclick="javascript:return FN_QSChangePage('zuihouyiye');"
																		onMouseOver="MM_swapImage('zuimoye','','<%=static_file%>images/zuimoye2.jpg',1)"
																		onMouseOut="MM_swapImgRestore()"
																		src="<%=static_file%>images/zuimoye1.jpg" id="zuimoye">
																	<!--���ֻ��һҳ�����水ť����ʾ-->
																	<logic:equal name="clfjyxxCXForm"
																		property="queryCache.isOnePage" scope="session"
																		value="false">
																		<!--����ǵ�һҳ��ǰҳ����ʾ-->
																		<logic:equal name="clfjyxxCXForm"
																			property="queryCache.hasPrev" scope="session"
																			value="true">
																			<input type="image" name="frontpage" value="ǰҳ"
																				alt="ǰҳ"
																				onclick="javascript:return FN_QSChangePage('shangyiye');"
																				onMouseOver="MM_swapImage('shangyiye','','<%=static_file%>images/shangyiye2.jpg',1)"
																				onMouseOut="MM_swapImgRestore()"
																				src="<%=static_file%>images/shangyiye1.jpg"
																				id="shangyiye">
																		</logic:equal>
																		<!--��������һҳ����ҳ����ʾ-->
																		<logic:equal name="clfjyxxCXForm"
																			property="queryCache.hasNext" scope="session"
																			value="true">
																			<input type="image" name="backpage" value="��ҳ"
																				alt="��ҳ"
																				onclick="javascript:return FN_QSChangePage('xiayiye');"
																				onMouseOver="MM_swapImage('xiayiye','','<%=static_file%>images/xaiyiye2.jpg',1)"
																				onMouseOut="MM_swapImgRestore()"
																				src="<%=static_file%>images/xaiyiye1.jpg"
																				id="xiayiye">
																		</logic:equal>
																	</logic:equal>
																</div>
															</td>
														</logic:notEqual>
													</tr>
												</table>
											</logic:notEqual>
											<table>
												<tr>
													<td id="ShowBeforeScan" colspan="4"><IMG alt=����
														height=22 id=daochu1 name=daochu1
														onclick="doAction('Export')"
														onmouseout=MM_swapImgRestore()
														onmouseover="MM_swapImage('daochu1','','<%=static_file%>images/b-dc2.jpg',1)"
														src="<%=static_file%>images/b-dc1.jpg"
														style="CURSOR: hand" width=79> <IMG alt=�����ѯ����
														height=22 id=qingchu1 name=qingchu1
														onclick="doAction('Show')" onmouseout=MM_swapImgRestore()
														onmouseover="MM_swapImage('qingchu1','','<%=static_file%>images/qingchu2.jpg',1)"
														src="<%=static_file%>images/qingchu1.jpg"
														style="CURSOR: hand" width=79> <IMG alt=�˳� height=22
														id=tuichu1 name=tuichu1 onclick="doAction('Return');"
														onmouseout=MM_swapImgRestore()
														onmouseover="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
														src="<%=static_file%>images/tuichu1.jpg"
														style="CURSOR: hand" width=79></td>
												</tr>

											</table>
										</html:form> <%@ include file="../include/Bottom.jsp"%>
									</td>
								</tr>
							</TABLE>
						</td>
					</tr>
				</TABLE>
			</td>
		</tr>
	</TABLE>

</BODY>
<script type="text/javascript">
	function doAction(operationType) {
		if (operationType == "Query" || operationType == "Export") {
			if (dataCheck()) {
				doSubmitForm(operationType);
			}
			return false;
		}
		doSubmitForm(operationType);
	}

	function doSubmitForm(operationType) {
		var truthBeTold;
		switch (operationType) {
		case 'Return':
			truthBeTold = true;
			break;
		case 'Query':
			truthBeTold = true;
			break;
		case 'Export':
			truthBeTold = true;
			break;
		case 'Show':
			truthBeTold = true;
			break;
		default:
			break;
		}
		if (!truthBeTold) {
			return false;
		}
		document.all.operationType.value = operationType;
		document.forms[0].submit();
	}

	function dataCheck() {
		var query_htbh = document.forms[0].query_htbh.value;
		var query_qsrq = document.forms[0].query_qsrq.value;
		var query_jzrq = document.forms[0].query_jzrq.value;
		var query_minJzmj=document.forms[0].query_minJzmj.value;
		var query_maxJzmj=document.forms[0].query_maxJzmj.value;
		if (query_qsrq != "") {
			if (!isDate(document.forms[0].query_qsrq)) {
				alert("����¼�������𲻺Ϸ�,��ȷ��ʽ��20120101�����飡");
				document.forms[0].query_qsrq.select();
				return false;
			}
		}

		if (query_jzrq != "") {
			if (!isDate(document.forms[0].query_jzrq)) {
				alert("����¼������ֹ���Ϸ�,��ȷ��ʽ��20120101�����飡");
				document.forms[0].query_jzrq.select();
				return false;
			}
		}

		if (query_qsrq != "" && query_jzrq != "") {
			if (parseFloat(query_qsrq) > parseFloat(query_jzrq)) {
				alert("��ʼ���ڲ��ܴ��ڽ�ֹ���ڣ����飡");
				document.forms[0].query_qsrq.select();
				return false;
			}

			if (parseFloat(query_jzrq) < parseFloat(query_qsrq)) {
				alert("��ֹ���ڲ���С����ʼ���ڣ����飡");
				document.forms[0].query_jzrq.select();
				return false;
			}
		}
		
		if(query_minJzmj != null && query_minJzmj !=""){
			if(!checkNumber(query_minJzmj,16,3,true))
        	{
            	alert("����С���ݽ������������Ϊ���֣�\nС�����ĳ������Ϊ3λ��\n������ֵ����ڵ����㣡");
            	document.forms[0].query_minJzmj.select();
            	return false;
        	}
		}
		if(query_maxJzmj != null && query_maxJzmj !=""){
			if(!checkNumber(query_maxJzmj,16,3,true))
        	{
            	alert("������ݽ������������Ϊ���֣�\nС�����ĳ������Ϊ3λ��\n������ֵ����ڵ����㣡");
            	document.forms[0].query_maxJzmj.select();
            	return false;
        	}
		}
		return true;

	}

</script>
</HTML>
