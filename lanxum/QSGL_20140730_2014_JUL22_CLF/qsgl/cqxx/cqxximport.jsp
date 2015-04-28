<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<html>
<head>
<title>拆迁信息导入</title>
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
            alert("请输入导入文件的位置");
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
	<jsp:param name="subtitle" value="拆迁信息&gt;导入" />
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
						<td class="1-td1">拆迁信息导入</td>
					</tr>
					<tr>
						<td class="1-td2"><br>
						<html:form action="/cqxx/cqxximport.do"
							enctype="multipart/form-data">
							<html:hidden property="operationType" />
							<table cellspacing="0" class="table-60">
								<tr>
									<td class="2-td2-t-left">选择文件</td>
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
								id="dr" alt="上传" style="cursor:hand"> &nbsp;&nbsp;&nbsp;&nbsp; <IMG
								name="back" onclick="doSubmitForm('Return');"
								onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
								onMouseOut="MM_swapImgRestore()"
								src="<%=static_file%>images/tuichu1.jpg" width="79" height="22"
								id="tuichu1" alt="退出" style="cursor:hand"></DIV>


							<logic:greaterThan name="cqxxImportFileForm" property="size"
								value="0">
								<table border="0" cellSpacing=0 class=table-99>
									<tr>
										<td class="2-td1-left" width="50%">&nbsp;</td>
										<td class="2-td1-center" width="50%">&nbsp;</td>
									</tr>
									<tr>
										<td class="2-td2-left">成功导入数据</td>
										<td class="2-td2-center" style="word-break:break-all"><bean:write
											name="cqxxImportFileForm" property="size" />条</td>
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
											<td class="2-td1-center" colspan="12">错误数据如下</td>
										</tr>
										<tr>
											<td class="2-td1-left" width="9%">项目名称</td>
											<td class="2-td1-left" width="9%">建设单位</td>
											<td class="2-td1-left" width="6%">拆迁许可证号</td>
											<td class="2-td1-left" width="9%">拆迁许可证号审批时间</td>
											<td class="2-td1-left" width="13%">拆迁范围</td>
											<td class="2-td1-left" width="7%">姓名</td>
											<td class="2-td1-left" width="9%">身份证号</td>
											<td class="2-td1-left" width="9%">共居人名称</td>
											<td class="2-td1-left" width="5%">正式房屋间数</td>
											<td class="2-td1-left" width="5%">正式房屋建筑面积</td>
											<td class="2-td1-left" width="14%">房屋详细地址</td>
											<td class="2-td1-center" width="5%">错误类型</td>
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
