<%@ page import="java.util.Iterator,
                 java.util.Map"%>
<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>
<head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Language" content="zh-cn">
<link href="../css/beijing.css" rel="stylesheet" type="text/css">
<title>�ۿ�״̬��ѯ</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
</head>
<%
    com.ttsoft.bjtax.smsb.gghsb.web.KkztcxForm form = (com.ttsoft.bjtax.smsb.gghsb.web.KkztcxForm)request.getAttribute("kkztcxForm");
	boolean Excelfalg=false;
      if (form.getDataList() == null || form.getDataList().isEmpty() ) {
	Excelfalg=true;
      }
    %>
<style type="text/css">
<!--
@import url(StaticFile/css/text.css);
@import url(../css/error.css);
-->
</style>
<script language="javascript" src="StaticFile/js/common.js"></script>
<script language="javascript" src="StaticFile/js/swapImages.js"></script>
<BODY  bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
<%@ include file="./include/header.jsp"%>
<!--header begin-->
<html:form action="/webapp/smsb/kkztcxAction.do" method="POST" >

<html:hidden property="actionType" />
<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0"  class='black9'>
  <tr>
    <td valign="top"> <br>
      <table  align="center" cellspacing="0" class="table-99" border="0">
       <tr>
    <td valign="top"> <br>
      <table  align="center" cellspacing="0" class="table-99" border="0">
        <tr class="black9">
          <td class="1-td1">�ۿ�״̬��ѯ</td>
        </tr>
		 <br>
        <tr>
          <td valign="top" class="1-td2">
              <table width="98%" border="0"  cellspacing="0"  class="table-99">
                   <tr>
                <td class="3-td1-left" nowrap><div align="right">���:&nbsp;&nbsp;</div></td>
                <td class="3-td1-left" nowrap><div align="left">&nbsp;&nbsp;<html:text property="nd" size="10"  /><font color="#FF0000">&nbsp;*</font></div>

				</td>
				<td class="3-td1-left" nowrap><div align="right">����:&nbsp;&nbsp;</div></td>
                  <td class="3-td1-left" nowrap><div align="left">&nbsp;&nbsp;<html:select property="zq">
                       <html:option value="01">1��</html:option>
					   <html:option value="02">2��</html:option>
					   <html:option value="03">3��</html:option>
					   <html:option value="04">4��</html:option>
					   <html:option value="05">5��</html:option>
					   <html:option value="06">6��</html:option>
					   <html:option value="07">7��</html:option>
					   <html:option value="08">8��</html:option>
					   <html:option value="09">9��</html:option>
					   <html:option value="10">10��</html:option>
					   <html:option value="11">11��</html:option>
					   <html:option value="12">12��</html:option>
  					 </html:select> </div></td>
                <td class="2-td2-center"><input name="I2" type="image" accesskey="q" value="��ѯ"  onclick='doSelectSubmit();return false;' onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun"></td>
              </tr>
              </table>  </div>
            <br>
			<!--<div id="Layer2" style="position:static; overflow: auto;  width: 778px; height: 200px">-->





			 <table id="kkztlistTable" cellspacing="0" border="1" class="table-99">
				<!--bean:define id="kkztlist" name="kkztcxForm" property="dataList"/-->
        <tr>
           <td rowspan="2" class="2-td1-left">���ط־�</td>
            <td colspan="4" class="2-td1-left">���ɿۿ���Ϣ</td>
            <td colspan="3" class="2-td1-left">���е�һ�οۿ���Ϣ</td>
            <td colspan="3" class="2-td1-left">���еڶ��οۿ���Ϣ</td>
            <td colspan="4" class="2-td1-center">���пۿ���Ϣ�ϼ�</td>
        </tr>
        <tr bordercolor="#9BB4CA">
              <td class="2-td1-Bleft" height="2">ʱ��</td>
              <td class="2-td1-Bleft" height="2">����</td>
              <td class="2-td1-Bleft"  height="2">����</td>
              <td class="2-td1-Bleft"  height="2">���</td>
              <td class="2-td1-Bleft"  height="2">����</td>
              <td class="2-td1-Bleft"  height="2">�ɹ�����</td>
              <td class="2-td1-Bleft"  height="2">�ɹ����</td>
              <td class="2-td1-Bleft"  height="2">����</td>
              <td class="2-td1-Bleft"  height="2">�ɹ�����</td>
              <td class="2-td1-Bleft"  height="2">�ɹ����</td>
              <td class="2-td1-Bleft"  height="2">�ɹ������ϼ�</td>
              <td class="2-td1-Bleft"  height="2">�ɹ����ϼ�</td>
              <td class="2-td1-Bleft"  height="2">���ɹ������ϼ�</td>
              <td class="2-td1-Bcenter"  height="2">���ɹ����ϼ�</td>
        </tr>
				<logic:iterate id="item" name="kkztcxForm"  property="dataList"  >
                <!--bean:define id="item" name="items"/-->

				<tr>
                <td nowrap class="2-td2-left" height="2"><bean:write name="item" property="qxfj"/>&nbsp</td>
                <td nowrap class="2-td2-left" height="2"><bean:write name="item" property="sckkxxsj"/>&nbsp</td>
                <td nowrap class="2-td2-left" height="2"><bean:write name="item" property="kkzhs"/>&nbsp</td>
                <td nowrap class="2-td2-left"  height="2"><bean:write name="item" property="kkxxzbs"/>&nbsp</td>
                <td nowrap class="2-td2-left" height="2"><bean:write name="item" property="sckkxxje"/>&nbsp</td>
                <td nowrap class="2-td2-left" height="2"><bean:write name="item" property="yckkcgrq"/>&nbsp</td>
                <td nowrap class="2-td2-left" height="2"><bean:write name="item" property="yckkcgbs"/>&nbsp</td>
                <td nowrap class="2-td2-left"  height="2"><bean:write name="item" property="yckkcgje"/>&nbsp</td>
                <td nowrap class="2-td2-left"  height="2"><bean:write name="item" property="eckkcgrq"/>&nbsp</td>
                <td nowrap class="2-td2-left"  height="2"><bean:write name="item" property="eckkcgbs"/>&nbsp</td>
                <td nowrap class="2-td2-left"  height="2"><bean:write name="item" property="eckkcgje"/>&nbsp</td>
                <td nowrap class="2-td2-left"  height="2"><bean:write name="item" property="kkcgzbs"/>&nbsp</td>
                <td nowrap class="2-td2-left"  height="2"><bean:write name="item" property="kkcgzje"/>&nbsp</td>
                <td nowrap class="2-td2-left"  height="2"><bean:write name="item" property="kkbcgzbs"/>&nbsp</td>
                <td nowrap class="2-td2-center"  height="2"><bean:write name="item" property="kkbcgzje"/>&nbsp</td>
				</tr>
				</logic:iterate>
    		</table></div>

	          <table border="0" width="100%">
              <tr>
                <td width="22%">&nbsp; </td>
                <td width="15%"><input name="toexcel" type="image" accesskey="s"  onClick="doToExcelSubmit();return false;"  onMouseOver="MM_swapImage('toexcel1','','<%=static_contextpath%>images/b-bcdexcel2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="���浽Excel" id="toexcel1" src="<%=static_contextpath%>images/b-bcdexcel1.jpg" width="100" height="22"></td>
                <td width="15%"><input name="tuichu" type="image" accesskey="c"   onClick="tuichu();return false;"           onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"            onMouseOut="MM_swapImgRestore()" value="�˳�"        id="tc1"      src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22"></td>
                <td width="18%">&nbsp;</td>
              </tr>
            </table>

            <br>
            <table width="99%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td><hr width="100%" size="1" ></td>
                    <td width="99" align="center" class="black9">
                        <strong><font color="#0000FF">ע �� �� ��</font></strong>
                    </td>
                    <td><hr width="100%" size="1" ></td>
                </tr>
            </table>

            <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
                <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                    <td >
                        <br>&nbsp;&nbsp;���Ϊ�������ʽΪYYYY,��2005��<br><br>
                    </td>
                </tr>
            </table>

            </td>
   	    </tr>
	    </table>
    </td>
  </tr>
 </table>
 </table>
</html:form>
<%@ include file="./include/footer.jsp"%>
</BODY>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
<!--
//����Excel�ύ
function doToExcelSubmit(){
    //���������
    var nd = document.all.nd;
	if(nd.value==null || nd.value==""){
		alert("��ȱ�������");
		return;
	}
	<%=Excelfalg?"alert(\"���Ȳ�ѯ��\");return;":""%>
        doSubmitForm('SaveExcel');
}
//�����ύ
function doSelectSubmit()
{
    //���������
    var nd = document.all.nd;
	if(nd.value==null || nd.value==""){
		alert("��ȱ�������");
	return;
	}
				document.forms[0].style.cursor="wait";
        doSubmitForm('Query');
}
//-->
</SCRIPT>
</html:html>
