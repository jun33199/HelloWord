<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<html>
<head>
<title>��Ǩ��Ϣ����</title>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>

<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
	type=text/JavaScript></SCRIPT>

<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>


<script language=JavaScript type='text/JavaScript'>

function doSubmitForm(operationType)
{

    if(operationType == 'UploadExcel')
    {
          if(document.forms[0].myFile.value.length < 1)
        {
            alert("�����뵼���ļ���λ��");
            document.forms[0].myFile.focus();
            return false;
        }

        document.all.operationType.value = operationType;

	}

    document.all.operationType.value = operationType;
	document.forms[0].submit();
return true;
}

</script>


</head>
<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0
	marginheight="0" marginwidth="0" onload="">
<jsp:include page="/include/Header.jsp" flush="true">
	<jsp:param name="subtitle" value="��Ǩ��Ϣ&gt;����" />
	<jsp:param name="helpURL" value="" />
</jsp:include>
<br>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%"
	width=770>
	<TBODY>
		<TR>
			<TD vAlign=top>
			<table align="center" cellspacing="0" class="table-99">
				<TBODY>
					<tr>
						<td class="1-td1">��Ǩ��Ϣ����</td>
					</tr>
					<tr>
						<td class="1-td2"><br>
						<html:form action="/cqxx/cqxximport.do"
							enctype="multipart/form-data">
							<html:hidden property="operationType" />
							<table cellspacing="0" class="table-60">
								<tr>
									<td class="2-td2-t-left">ѡ���ļ�</td>
									<td class="2-td2-t-center"><input type='file' name='myFile'
										size=30 class='input' id='myFile'><FONT color=red>*</FONT></td>
								</tr>
							</table>
							<br>
							<DIV align=center><IMG name="import"
								onMouseOver="MM_swapImage('dr','','<%=static_file%>images/b-dr2.jpg',1)"
								onMouseOut="MM_swapImgRestore()"
								src="<%=static_file%>images/b-dr1.jpg"
								onclick="doSubmitForm('UploadExcel');" width="79" height="22"
								id="dr" alt="�ϴ�" style="cursor:hand"> &nbsp;&nbsp;&nbsp;&nbsp; <IMG
								name="back" onclick="doSubmitForm('Return');"
								onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
								onMouseOut="MM_swapImgRestore()"
								src="<%=static_file%>images/tuichu1.jpg" width="79" height="22"
								id="tuichu1" alt="�˳�" style="cursor:hand"></DIV>


							<logic:greaterThan name="cqxxImportFileForm" property="size"
								value="0">
								<table border="0" cellSpacing=0 class=table-99>
									<tr>
										<td class="2-td1-left" width="50%">&nbsp;</td>
										<td class="2-td1-center" width="50%">&nbsp;</td>
									</tr>
									<tr>
										<td class="2-td2-left">�ɹ���������</td>
										<td class="2-td2-center" style="word-break:break-all"><bean:write
											name="cqxxImportFileForm" property="size" />��</td>
									</tr>
								</table>
							</logic:greaterThan>

							<br>

							<logic:equal name="cqxxImportFileForm" property="importIsSucceed"
								value="false">
								<logic:greaterThan name="cqxxImportFileForm" property="errSize"
									value="0">
									<table border="0" cellSpacing=0 class=table-99>
										<tr>
											<td class="2-td1-center" colspan="12">������������</td>
										</tr>
										<tr>
											<td class="2-td1-left" width="9%">��Ŀ����</td>
											<td class="2-td1-left" width="9%">���赥λ</td>
											<td class="2-td1-left" width="6%">��Ǩ���֤��</td>
											<td class="2-td1-left" width="9%">��Ǩ���֤������ʱ��</td>
											<td class="2-td1-left" width="13%">��Ǩ��Χ</td>
											<td class="2-td1-left" width="7%">����</td>
											<td class="2-td1-left" width="9%">���֤��</td>
											<td class="2-td1-left" width="9%">����������</td>
											<td class="2-td1-left" width="5%">��ʽ���ݼ���</td>
											<td class="2-td1-left" width="5%">��ʽ���ݽ������</td>
											<td class="2-td1-left" width="14%">������ϸ��ַ</td>
											<td class="2-td1-center" width="5%">��������</td>
										</tr>
										<logic:iterate id="data" indexId="index" length="length"
											name="cqxxImportFileForm" property="importErrRecords">
											<tr>
												<td class="2-td2-left"><bean:write name="data"
													property="cqxmmc" />&nbsp;</td>
												<td class="2-td2-left" style="word-break:break-all"><bean:write
													name="data" property="cqrmc" />&nbsp;</td>
												<td class="2-td2-left" style="word-break:break-all"><bean:write
													name="data" property="cqxkzh" />&nbsp;</td>
												<td class="2-td2-left" style="word-break:break-all"><bean:write
													name="data" property="cqxkzspsj" />&nbsp;</td>
												<td class="2-td2-left" style="word-break:break-all"><bean:write
													name="data" property="cqfw" />&nbsp;</td>
												<td class="2-td2-left" style="word-break:break-all"><bean:write
													name="data" property="bcqrmc" />&nbsp;</td>
												<td class="2-td2-left" style="word-break:break-all"><bean:write
													name="data" property="zjhm" />&nbsp;</td>
												<td class="2-td2-left" style="word-break:break-all"><bean:write
													name="data" property="gjrmc" />&nbsp;</td>
												<td class="2-td2-left" style="word-break:break-all"><bean:write
													name="data" property="zsfwjs" />&nbsp;</td>
												<td class="2-td2-left" style="word-break:break-all"><bean:write
													name="data" property="cqmj" />&nbsp;</td>
												<td class="2-td2-left" style="word-break:break-all"><bean:write
													name="data" property="cqxxdz" />&nbsp;</td>
												<td class="2-td2-center" style="word-break:break-all"><bean:write
													name="data" property="cwlxmc" />&nbsp;</td>
											</tr>
										</logic:iterate>
									</table>
								</logic:greaterThan>
							</logic:equal>


						</html:form> <%@ include file="../include/Bottom.jsp"%>
</body>
</html>
