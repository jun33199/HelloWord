<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html>
<head>
<title>��������˰������Ϣ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<script language=JavaScript>
function doReturn()
{
	document.forms[0].action = "quit.do";
	document.forms[0].submit();
}
</script>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">

<table width="97%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>

    	<jsp:include page="../include/header.jsp" flush="true" >
    		<jsp:param name="name" value="��������˰������Ϣ¼��" />
		<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>

    	<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td valign="top">
	     <br>
	      <html:errors/>
	     <table cellspacing="0" class="table-99">
		<!-- begin ========================================================================================--->
            <tr>
            	<td>
            	<form>
       			<table cellspacing="0" width="100%">
       				<tr>
       					<td class="1-td1" colspan="6">��������˰������Ϣ</td>
       				</tr>
       				<tr>
       					<td class="2-td2-left">������</td>
       					<td class="2-td2-center"><input type="text"/></td>
       					<td class="2-td2-right">���֤�����ͣ�</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">���֤�����룺</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left" rowspan="3">��ְ�ܹ͵�λ���Ƽ���˰��ʶ��ţ�</td>
       					<td class="2-td2-center" colspan="3"><input type="text"/></td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3"><input type="text"/></td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="3"><input type="text"/></td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left">������һ�𡱽��������</td>
       					<td class="2-td2-center" colspan="3">
							<input type="checkbox">�������ϱ��շ�
							<input type="checkbox">����ҽ�Ʊ��շ�
							<input type="checkbox">ʧҵ���շ�
							<input type="checkbox">ס��������
						</td>
       					<td class="2-td2-right">�������䣺</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left">������ϵ��ַ��</td>
       					<td class="2-td2-center" colspan="3">
       						ʡ��<input type="text"/>&nbsp;&nbsp;
       						�У�<input type="text"/>&nbsp;&nbsp;
       						�����أ���<input type="text"/>&nbsp;&nbsp;
						</td>
       					<td class="2-td2-right">�������룺</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left">��ϵ�绰��</td>
       					<td class="2-td2-center" colspan="3">
       						�ֻ���<input type="text"/>&nbsp;&nbsp;
       						�̶��绰��<input type="text"/>
						</td>
       					<td class="2-td2-right">ְҵ��</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left">ְ��</td>
       					<td class="2-td2-center" colspan="3">
       						<input type="radio">�߲�
       						<input type="radio">�в�
       						<input type="radio">��ͨ
							&nbsp;&nbsp;&nbsp;&nbsp;��ֻѡһ��
						</td>
       					<td class="2-td2-right">ѧ����</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left">�Ƿ�м���/����/���ϣ�</td>
       					<td class="2-td2-center" colspan="3">
       						<input type="checkbox">�м�
							<input type="checkbox">����
							<input type="checkbox">����
							<input type="checkbox">��
						</td>
       					<td class="2-td2-right">�м��ȼ������</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left">���������о�������˰����д</td>
       					<td class="2-td2-center">
       						<input type="radio" name="e">�������ڵ� 
       						<input type="radio" name="e">������ס��
       					</td>
       					<td class="2-td2-right" colspan="4">
       						ʡ��<input type="text"/>&nbsp;&nbsp;
       						�У�<input type="text"/>&nbsp;&nbsp;
       						�����أ���<input type="text"/>&nbsp;&nbsp;&nbsp;&nbsp;
       						�������룺<input type="text"/>
						</td>
       				</tr>
       				<tr>
       					<td class="2-td2-left" rowspan="7">��������Ͷ������˰����д</td>
       					<td class="2-td2-center">Ͷ�������ͣ�</td>
       					<td class="2-td2-right" colspan="4">
       						<input type="checkbox">���幤�̻�
       						<input type="checkbox">���˶�����ҵͶ����
       						<input type="checkbox">�ϻ���ҵ��
       						<input type="checkbox">�а������⾭Ӫ��
       						<input type="checkbox">�ɶ�
       						<input type="checkbox">����Ͷ����
       						&nbsp;&nbsp;&nbsp;&nbsp;���ɶ�ѡ��
       					</td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" rowspan="4">��Ͷ�ʵ�λ��Ϣ</td>
       					<td class="2-td2-right">���ƣ�</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">�ۿ������˱��룺</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-right">��ַ��</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">�������룺</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-right">�Ǽ�ע�����ͣ�</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">��ҵ��</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-right">����˰���շ�ʽ��</td>
       					<td class="2-td2-right">
							<input type="radio">��������
       						<input type="radio">�˶�����
							&nbsp;&nbsp;&nbsp;&nbsp;��ֻѡһ��
						</td>
       					<td class="2-td2-right">����˰����أ�</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="5">�����ɹɶ�������Ͷ������д</td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">��˾�ɱ���Ͷ�ʣ��ܶ�</td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       					<td class="2-td2-right">���˹ɱ���Ͷ�ʣ���</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-left" rowspan="13">����������ס����˰����д</td>
       					<td class="2-td2-center">��˰��ʶ��ţ�</td>
       					<td class="2-td2-right" colspan="4"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">��������������</td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       					<td class="2-td2-right">�����أ�</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">�Ա�</td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       					<td class="2-td2-right">�������ڣ�</td>
       					<td class="2-td2-right">
							<input type="text" style="width: 70px;">&nbsp;��&nbsp;
							<input type="text" style="width: 70px;">&nbsp;��&nbsp;
							<input type="text" style="width: 70px;">&nbsp;��&nbsp;
						</td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">�Ͷ���ҵ֤�ţ�</td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       					<td class="2-td2-right">�Ƿ�˰��Э����Լ���Է�����</td>
       					<td class="2-td2-right">
       						<input type="radio"/>��
       						<input type="radio"/>��
       					</td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">����ְ��</td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       					<td class="2-td2-right">����ְ��</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">����ʱ�䣺</td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       					<td class="2-td2-right">��ְ���ޣ�</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">Ԥ���뾳ʱ�䣺</td>
       					<td class="2-td2-right" colspan="2"><input type="text"/></td>
       					<td class="2-td2-right">Ԥ���뾳�ص㣺</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" rowspan="2">������ְ�ܹ͵�λ</td>
       					<td class="2-td2-right">���ƣ�</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">�ۿ������˱��룺</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-right">��ַ��</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">�������룺</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" rowspan="2">������ƸǩԼ��ַ</td>
       					<td class="2-td2-right">���ƣ�</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">�ۿ������˱��룺</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-right">��ַ��</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">�������룺</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">������ǲ��λ</td>
       					<td class="2-td2-right">���ƣ�</td>
       					<td class="2-td2-right"><input type="text"/></td>
       					<td class="2-td2-right">��ַ��</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center">֧����</td>
       					<td class="2-td2-right" colspan="2">
       						<input type="radio"/>����֧��
       						<input type="radio"/>����֧��
       						<input type="radio"/>���ڡ���ͬʱ֧��
       						&nbsp;&nbsp;&nbsp;&nbsp;��ֻѡһ��
       					</td>
       					<td class="2-td2-right">����֧�������𣨵�������</td>
       					<td class="2-td2-right"><input type="text"/></td>
       				</tr>
       				<tr>
       					<td class="2-td2-center" colspan="6">
       						<img src="<%=static_contextpath%>images/tuichu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tuichu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tuichu1.jpg'" alt="�˳�" onclick="doReturn();" style="cursor:hand">
       					</td>
       				</tr>
       			</table>
       			</form>
       			</td>
            </tr>
	    <!-- end ==========================================================================================--->
	        <tr>
	          <td class="1-td2-center"><br>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
				 <tr>
				    <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				    <td width="20%" align="center" class="black9"><strong><font color="#0000FF">ע������</font></strong></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47"  >
				        <p style ="color:red">
				            &nbsp;&nbsp;&nbsp;&nbsp;���������˱��Ǹ��ݡ��л����񹲺͹���������˰��������ʵʩ�����͹�����ط��ɷ���涨��д�ģ�����ʵ�ġ������ġ��ɿ��ġ�<br>
				         </p>
				       </td>
				    </tr>
				</table>
	  			<br>
	          </td>
	         </tr>
	     </table>
	    </td>
	  </tr>
    	</table>
<br>
    </td>
  </tr>
<tr>
  <td valign="bottom" align="center">
	<%@ include file="../include/footer.jsp" %>
  </td>
</tr>
 </table>
</body>
</html>