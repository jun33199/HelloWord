<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>

<style type="text/css">
<!--
@import url(../css/text.css);
-->
</style>

<html:html>
<head>
<title>���òм��˾�ҵ��ҵӪҵ˰����˰��ѯͳ�Ʊ�</title>
<SCRIPT LANGUAGE="jscript" src="../js/treatImage.js"></SCRIPT>
<SCRIPT LANGUAGE="jscript" src="../js/smsb_common.js"></SCRIPT>
<script language=JavaScript src="../js/function.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
<%@ include file="include/header.jsp"%>
<html:form action="/webapp/smsb/cjrjyjmscxtjAciton" method="POST">
<html:hidden property="actionType"/>
<html:hidden property="headSwjgzzjgdm"/>
<html:hidden property="pageSize"/>
<html:hidden property="nextPage"/>
<html:hidden property="totalpage"/>
<html:hidden property="message"/>

<table width="94%" align="center" cellspacing="0" class="table-99">
   <tr>
      <td class="1-td1">���òм��˾�ҵ��ҵӪҵ˰����˰��ѯͳ�Ʊ�</td>
   </tr>
   <tr>
      <td class="1-td2"  colspan="7">
   <br>
   <table width="70%" cellspacing="0" class="table-99">
      <tr class="black9">
      	 <td width="12%" align="center" nowrap class="2-td2-t-left"> 
			<div align="right">���������
            &nbsp;
			</div></td>
            <td class="2-td2-t-left" width="19%" align="center" nowrap><div align="left">
			<html:text property="queryJsjdm"  size="25"  maxlength="8" tabIndex="-1"/>
			</div></td>
         <td width="12%" align="center" nowrap class="2-td2-t-left"> 
		 <div align="center">��ҵ����</div></td>
            <td class="2-td2-t-left" width="11%" align="center" nowrap>
			<div align="left">
            <html:text property="queryQymc"  size="25" maxlength="200" tabIndex="-1"/>
			</div></td>
         <td width="13%" align="center" class="2-td2-t-left" nowrap> 
         	<div align="center" >����˰�����</div>
         	</td>
	     <td class="2-td2-t-center" width="32%" align="center" nowrap>
	     	<div align="left">
	     	<html:select property = "queryZgswjgdm"><html:options property="zgswjgList" /></html:select></div></td>
      </tr>
      <tr>
         <td width="11%" align="center" nowrap class="2-td2-left"> 
		 <div align="right">��ѯ���� ��ʼ</div>
		 </td>
		 <td width="19%" align="center" nowrap class="2-td2-left">
         <div align="left">
         <html:text property="queryCxqsrq" maxlength="8" size="25"  tabIndex="-1"/></div></td>
         <td class="2-td2-left">����</td>
         <td width="19%" align="center" nowrap class="2-td2-center" colspan="3">
		 			<div align="left">
         <html:text property="queryCxjzrq" maxlength="8" size="25"  tabIndex="-1"/></div>
		 </td>
      </tr>
   </table>
   <br><br><br>
   <table width="99%" cellpadding="0" cellspacing="0" class="table-99">
      <tr width="150" class="black9">
		<td rowspan="2" width="4%" nowrap class="2-td2-t-left"><div align = "center">���</div></td>
		<td colspan="6" nowrap class="2-td2-t-left"><div align ="center">�������</div></td>
		<td rowspan="2" nowarp class="2-td2-t-left"><div align ="center">ʵ�ʼ���Ӫҵ˰��</div></td>
		<td rowspan="2" nowarp class="2-td2-t-center"><div align ="center">���òм�ְ������</div></td>
      </tr>
      <tr>
      	 <td nowrap class="2-td2-left" width="8%"><div align = "center">���������</div></td>
      	 <td nowrap class="2-td2-left" width="22%"><div align = "center">��ҵ����</div></td>
	     <td nowrap class="2-td2-left" width="16%"><div align = "center">��������</div></td>
	     <td nowrap class="2-td2-left" width="8%"><div align = "center">�����Ӫҵ˰�޶�</div></td>
	     <td nowrap class="2-td2-left" width="9%"><div align = "center">������ʼ����</div></td>
	     <td nowrap class="2-td2-left" width="9%"><div align = "center">������ֹ����</div></td>
      </tr>  
      <logic:iterate id="item1" name="cjrjyjmscxtjForm" property="dataList" >
      <tr>
         <td class="2-td2-left" width="4%" align="center"><bean:write name='item1' property='indexId'/>&nbsp;</td>
         <td class="2-td2-left" width="8%" align="center"><bean:write name='item1' property='jsjdm'/>&nbsp;</td>
         <td class="2-td2-textleft" width="22%" align="center"><bean:write name='item1' property='qymc'/>&nbsp;</td>
         <td class="2-td2-left" width="16%" align="center"><bean:write name='item1' property='spbbh'/>&nbsp;</td>
		 <td class="2-td2-left" width="6%" align="center"><bean:write name='item1' property='njzyysxe'/>&nbsp;</td>
		 <td class="2-td2-left" width="8%" align="center"><bean:write name='item1' property='jzksrq'/>&nbsp;</td>
	     <td class="2-td2-left" width="8%" align="center"><bean:write name='item1' property='jzjzrq'/>&nbsp;</td>
		 <td class="2-td2-left" width="8%" align="center"><bean:write name='item1' property='sjjzyyse'/>&nbsp;</td>	
		 <td class="2-td2-center" width="9%" align="center"><bean:write name='item1' property='azcjzgrs'/>&nbsp;</td>						
      </tr>
      </logic:iterate>  
      <tr>
	  <td nowrap class="2-td2-left-yys-back" height="28" ><div align = "center">�ϼ�</div></td>
	  <td nowrap class="2-td2-left-yys-back" height="28" align="center">-------</td>
	  <td nowrap class="2-td2-left-yys-back" height="28" align="center">-------</td>
	  <td nowrap class="2-td2-left-yys-back" height="28" align="center">-------</td>
	  <td nowrap class="2-td2-left-yys-back" height="28" width="8%">&nbsp;<bean:write name='cjrjyjmscxtjForm' property='njzyysxeTotal'/></td>
	  <td nowrap class="2-td2-left-yys-back" height="28" width="8%" align="center">-------</td>
	  <td nowrap class="2-td2-left-yys-back" height="28" width="8%" align="center">-------</td>
	  <td nowrap class="2-td2-left-yys-back" height="28">&nbsp;<bean:write name='cjrjyjmscxtjForm' property='sjjzyyseTotal'/></td>
	  <td nowrap class="2-td2-center-yys-back" height="28">&nbsp;<bean:write name='cjrjyjmscxtjForm' property='azcjzgrsTotal'/></td>
      </tr>
</table>
<br>
<div align="right">
   (��<bean:write name="cjrjyjmscxtjForm" property="nextPage"/>ҳ/��<bean:write name="cjrjyjmscxtjForm" property="totalpage"/>ҳ)
   <!--��ҳ���ܿ�ʼ-->
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="ǰҳ" alt="ǰҳ" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="��ҳ" alt="��ҳ" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
   <a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="���һҳ" alt="���һҳ" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
   <!--��ҳ���ܿ�ʼ-->
</div>
<br>
<div align="center">
   <input type="image" accesskey="q" tabIndex="-1" onClick="doQuery(); return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" style="cursor:hand">&nbsp;&nbsp;&nbsp;&nbsp;
   <input type="image" accesskey="c" tabIndex="-1" onClick="tuichu(); return false;" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor: hand">
</div>
<br>
<table width="99%" border="0" cellpadding="0" cellspacing="0">
   <tr>
      <td>
         <hr width="100%" size="1" >
      </td>
	  <td width="99" align="center" class="black9">
	     <strong><font color="#0000FF">ע �� �� ��</font></strong>
	  </td>
	  <td><hr width="100%" size="1" ></td>
   </tr>
</table>
<table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
      <td height="47"  > &nbsp;&nbsp;1���ñ��С���ҵ���ơ�,����������롱�͡�����˰����ء�������ѯ�����ɶ�������ͬʱ�����롰��ѯ���ڡ���������ʹ�á�<br>&nbsp;&nbsp;2�����С���ҵ���ơ�һ�����������������ƣ����������Ϊ8λ���ֻ���ĸ��<br>&nbsp;&nbsp;3�����С���ѯ���ڡ��е�ʱ���趨�ɴӡ������������ѡ��<br>&nbsp;&nbsp;4�����С�����˰����ء�Ϊ�����˵������м���Ȩ���趨��������˰�����ֻ�ܲ�ѯ����������˰����ؿɲ�ѯ��Ͻ���ص�����˰�������м�˰����ؿɲ�ѯ���з�Χ�������������<br>&nbsp;&nbsp;5�����С��ϼơ�һ����ѯϵͳ���Զ��ۼӡ�</td>
   </tr>
</table>
   
</table>
</html:form>
<%@ include file="include/footer.jsp"%>

<script language="JavaScript">

	// ��ѯ����У�鼰��ת 
	function doQuery() {

		if(trim(document.forms[0].queryJsjdm.value) != "" || trim(document.forms[0].queryQymc.value) != "" || (document.forms[0].queryZgswjgdm.value).substr(5,(document.forms[0].queryZgswjgdm.value).length) != "ȫ��") {
			if(trim(document.forms[0].queryJsjdm.value) != "") {
				if(!/^[A-Za-z0-9]{8}$/.test(trim(document.forms[0].queryJsjdm.value))) {
					alert("�����������8λ��������ĸ���");
					return false;
				}
			}
			if(trim(document.forms[0].queryCxqsrq.value) == "") {
				alert("�������ѯ��ֹʱ��");
				return false;
			} else {
				var cxjzrqNull = false;
				var isEarlyThan = false;
				if(trim(document.forms[0].queryCxjzrq.value) == "") {
					cxjzrqNull = true;
					isEarlyThan = true;
				} else {
					cxjzrqNull = isDate(document.forms[0].queryCxjzrq);
					isEarlyThan = (compare(document.forms[0].queryCxqsrq,document.forms[0].queryCxjzrq)>=0);
				}
				if(isDate(document.forms[0].queryCxqsrq,"") && cxjzrqNull) {
					if (isEarlyThan){
document.forms[0].nextPage.value = 1;
						doSubmitForm("Query");
						return false;
					} else {
						alert("��ʼ���ڱ���С�ڻ���ڽ�ֹ���ڣ�");
					}
				} 
			}
		} else {
			alert("�������ѯ����");
			return false;
		}
	}

	//����ҳ��ʱ��������Ϊ���������¼��
	// ҳ����뽹��ȷ��
	function fnOnLoad()
	{
		document.forms[0].queryJsjdm.focus();
		if(document.forms[0].message.value!=""){
			alert(document.forms[0].message.value);
		}
	}
	
	// ��ҳ
	function fn_ChangePage(type)
	{
		//��ȡ��һ�β�������
		temp=document.all.actionType.value;
		//���õ�ǰ��������
		if(temp=="Query" || temp=="ChangePage"){
			temp="ChangePage";
		} else{
			temp="Show";
		}
		//
		var tmpNextPage=document.forms[0].nextPage.value*1;
		var tmpTotalPage=document.forms[0].totalpage.value*1;
		//alert("tmpNextPage:"+tmpNextPage+"|"+"tmpTotalPage:"+tmpTotalPage+"|"+"type:"+type);
		if(temp=="Show"){
			alert("û���κ��Ѳ�ѯ����,���Ȳ�ѯ...");
			return false;
		}
		if (type == "first")
		{
			if(tmpNextPage==1){
				alert("���β�ѯ�ѵ����һҳ,�뷵��...");
				return false;
			}else{
					document.forms[0].nextPage.value="1";
			}
		}
		else if (type == "previous")
		{
			if(tmpNextPage==1){
				alert("���β�ѯ�ѵ����һҳ,�뷵��...");
				return false;
			}else{
					document.forms[0].nextPage.value =(tmpNextPage-1)+"";
			}
		}
		else if (type == "next")
		{
			if(tmpNextPage>=tmpTotalPage){
				alert("���β�ѯ�ѵ������һҳ,�뷵��...");
				return false;
			}else{
					document.forms[0].nextPage.value =(tmpNextPage+1)+"";
			}
		}
		else if (type == "last")
		{
			if(tmpTotalPage==1){
				alert("���β�ѯֻ����һҳ,�뷵��...");
				return false;
			}else if(tmpNextPage>=tmpTotalPage){
				alert("���β�ѯ�ѵ������һҳ,�뷵��...");
				return false;
			}
			else{
				document.forms[0].nextPage.value=document.forms[0].totalpage.value;
			}
		}
		//�ύ��ѯ
		doSubmitForm(temp);
		return false;
	}
	
</script>
</body>
</html:html>


