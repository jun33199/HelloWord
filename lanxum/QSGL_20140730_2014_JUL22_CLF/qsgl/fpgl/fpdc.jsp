<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl.FpdcForm"%>

<%@page import="com.creationstar.bjtax.qsgl.util.DateUtils"%>
<%@page import ="java.util.Date"%>
<%
    session = request.getSession(false);
    FpdcForm fpdcForm = (FpdcForm)session.getAttribute("FpdcForm");
%>

<HTML>

<HEAD><TITLE>������Ʊ����</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>

<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="��Ʊ����&gt;������Ʊ����"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<html:form action="/fpgl/fpdc.do">
<html:hidden property="operationType"/>
<html:hidden property="fphms"/>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <table align="center" cellspacing="0" class="table-99">
	  <TBODY>
        <tr>
          <td class="1-td1">������Ʊ����</td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
            <table width="75%" border="0" cellspacing="0" class="table-99">
            <tr>
                <td width="15%" class="2-td2-t-left"><div align="right"><strong>��ǰ����</strong></div></td>
                <td class="2-td2-t-left"><div align="center"> <%=DateUtils.displayValue(new Date(),"yyyy-MM-dd")%> </div></td>
                <td width="15%" class="2-td2-t-left"><div align="right"><strong>������λ����</strong></div></td>
                <td width="50%" class="2-td2-t-center"><div align="center"><bean:write name="FpdcForm" property="swjgzzjgmc" />&nbsp;</div></td>
              </tr>
            </table>
            <br>
            <table width="75%" border="0" bgcolor="f3f3f3" class="table-99" cellspacing="0">
             <tr>
                <td width="15%" class="2-td2-t-left"><div align="right"><strong>��ͬ���</strong></div></td>
                <td width="25%" colspan="3" class="2-td2-t-left"><div align="left"><html:text property="htbh" maxlength="18" size="22"/>&nbsp;</div></td>
                <td width="15%" class="2-td2-t-left"><div align="center"><strong>����״̬</strong></div></td>
                <td rowspan="3" class="2-td2-t-center">
                <div align="center">
                <img onClick="doSubmitForm('Query')" alt="��ѯ" style="cursor:hand" src="<%=static_file%>/images/chaxun1.jpg"  name="Image1111" width="79" height="22" border="0" id="Image1111" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1111','','<%=static_file%>/images/chaxun2.jpg',1)" >
                </div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="right"><strong>��Ʊ����</strong></div></td>
                <td width="20%" class="2-td2-left"><div align="left">
                 <bean:define id="dto" name="FpdcForm" property="fpzlList"/>
                  <html:select property="fpzldm" style='width:165px'>
                    <html:options collection="dto" labelProperty="fpzlmc" property="fpzldm"/>
                  </html:select></div>
                </td>
                <td colspan="2" class="2-td2-left"><div align="left"><html:text property="qshm" size="15"/>&nbsp;</div></td>
                <td rowspan="2" nowrap class="2-td2-left"><div align="center">
					<html:select property="dczt">
						<html:option value="0">δ����</html:option>
						<html:option value="1">�ѵ���</html:option>
						<html:option value="-1">ȫ��</html:option>
                    </html:select>
                  </div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="right"><strong>��ʼ����</strong></div></td>
                <td class="2-td2-left"><div align="left"><html:text property="qsrq" size="15"/>&nbsp;</div></td>
                <td width="15%" class="2-td2-left"><div align="right"><strong>��ֹ����</strong></div></td>
                <td class="2-td2-left"><div align="left"><html:text property="jzrq" size="15"/>&nbsp;</div></td>
              </tr>
            </table>
            <br>
            <table border="0" bgcolor="f3f3f3" cellspacing="0" class="table-99">
              <tr>
                <td width="42%" ><hr width="100%" class="hr1" size=1></td>
                <td width="14%" align="left" class="black9" > <div align="center"><strong>��ѯ����б�</strong></div></td>
                <td width="44%" ><hr width="100%" class="hr1" size=1></td>
              </tr>
            </table>
            <div id="Layer2" style="position:static; overflow: auto;  width: 750px; height: 200px">
            <table border="0" bgcolor="f3f3f3" cellspacing="0" class="table-99">
              <tr bordercolor="#9BB4CA" class="z-ta2-td1">
              <td nowrap width="5%" class="2-td1-left" nowrap><div align="right"><input type="checkbox" id="chkAll" name="chkAll" onclick="CheckAll(this.form)"/>ȫѡ</div></td>
                <td nowrap width="5%" class="2-td1-left">���</td>
                <td nowrap width="10%" class="2-td1-left">��Ʊ����</td>
                <td nowrap width="10%" class="2-td1-left">��ͬ���</td>
                <td nowrap width="10%" class="2-td1-left">���λ</td>
                <td nowrap width="10%" class="2-td1-left">�տλ</td>
                <td nowrap width="10%" class="2-td1-left">���ݲ�Ȩ֤��</td>
                <td nowrap width="10%" class="2-td1-left">���������ַ</td>
                <td nowrap width="10%" class="2-td1-left">���</td>
                <td nowrap width="10%" class="2-td1-center">����״̬</td>
              </tr>
              <logic:iterate id="dataList" name="FpdcForm" property="cxList" indexId="index">
                 <tr >
                 	<td class="2-td2-left" ><input value="<bean:write name='dataList' property='fpzldm'/>:<bean:write  name='dataList' property='fphm'/>" type="checkbox" name="checkbox" />&nbsp;</td>
                    <td class="2-td2-left" ><%=index.intValue()+1%>&nbsp;</td>
                    <td class="2-td2-left"><bean:write name='dataList' property='fpzldm'/><bean:write name='dataList' property='fphm'/>&nbsp;</td>
                    <td class="2-td2-left" ><bean:write name='dataList' property='htbh'/>&nbsp;</td>
                    <td class="2-td2-left" ><bean:write name='dataList' property='nsrmc_buyer'/>&nbsp;</td>
                    <td class="2-td2-left" ><bean:write name='dataList' property='nsrmc_seller'/>&nbsp;</td>
                    <td class="2-td2-left" ><bean:write name='dataList' property='fwcqzh'/>&nbsp;</td>
                    <td class="2-td2-left" ><bean:write name='dataList' property='fwzldz'/>&nbsp;</td>
                    <td class="2-td2-left" ><bean:write name='dataList' property='je'/>&nbsp;</td>
                    <td class="2-td2-center" ><bean:write name='dataList' property='dczt'/>&nbsp;</td>
                 </tr>
                </logic:iterate>
            </table> 
            </div>           
            <br>

      <DIV align=center>
      	<logic:equal name="FpdcForm" property="fpbcbc" value="">
      		<img onClick="doSubmitForm('Save');" alt="����" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image121','','<%=static_file%>images/b-dc2.jpg',1)"  src="<%=static_file%>images/b-dc1.jpg" name="Image121" width="79" height="22" border="0" id="Image121" >
      	</logic:equal>
      	<img onClick="doSubmitForm('Clear');" alt="���" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image122','','<%=static_file%>images/qingkong2.jpg',1)"  src="<%=static_file%>images/qingkong1.jpg" name="Image122" width="79" height="22" border="0" id="Image122" >
      	<img onclick="doSubmitForm('Return');" alt="�˳�"  onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">
      </DIV>
      <BR>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
<script language=JavaScript type='text/JavaScript'>

function doSubmitForm(operationType)
{
	if(operationType=="Query")
	{
		if(document.forms[0].qsrq.value!='')
		{
			if(!checkDate(document.forms[0].qsrq.value))
        	{
            	alert("����ȷ��дʱ��Σ�YYYYMMDD�����磺20130101"); 
            	document.forms[0].qsrq.focus();
            	return false;
        	}
    	}
    	if(document.forms[0].jzrq.value!='')
    	{
        	if(!checkDate(document.forms[0].jzrq.value))
        	{
            	alert("����ȷ��дʱ��Σ�YYYYMMDD�����磺20130101");
            	document.forms[0].jzrq.focus();
            	return false;
        	}
    	}
	}
    
    if(operationType=="Save")
    {	
        var saveFpKeys = new Array();
        //���ҳ�����е�checkbox
     	var checkboxlist = document.getElementsByName("checkbox");
     	var count=0;//��¼��ѡ������ļ�¼��
     	for (var i = 0; i < checkboxlist.length; i++) 
     	{
	         if(checkboxlist.item(i).checked)
	         {
				  count++;
	         } 
		 }
		if(count==0)
		{
		  alert("��ѡ��Ҫ���������ݣ�");
		  return false;
		}
		else
		{
			if(!window.confirm("�Ƿ񵼳�ѡ�еķ�Ʊ����,��ȷ�ϣ�"))
			{
				return false;
			}
    		 for (var i = 0; i < checkboxlist.length; i++)
    		 {
		         if(checkboxlist.item(i).checked)
		         {
						var value=checkboxlist.item(i).value;
						saveFpKeys.push(value);
		      } 
		 	}
		} 
		document.forms[0].fphms.value = saveFpKeys;
		//alert(document.forms[0].fphms.value);
    }
    
    document.all.operationType.value = operationType;
    document.forms[0].submit();
}

	function CheckAll(form){
		for (var i=0;i<form.elements.length;i++){
			var e = form.elements[i];
		if (e.Name != 'chkAll'&&e.disabled==false)
			e.checked = form.chkAll.checked;
		}
		
		return false;
	}
 
 //��շ�Ʊ����
 function clearFpqshm()
 {
	document.forms[0].cxqshm.value = '';
 }
</script>


