<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<HTML>

<HEAD><TITLE>发票作废管理</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>




<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="发票管理&gt;发票作废管理"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<html:form action="/fpgl/fpzf.do">
<html:hidden property="operationType"/>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <table align="center" cellspacing="0" class="table-99">
	  <TBODY>
        <tr>
          <td class="1-td1">发票作废管理</td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
            <table width="75%" border="0" cellspacing="0" class="table-99" id='TABLE_LIST'>
              <tr>
                <td width="15%" class="2-td2-t-left"><div align="right"><strong>发票号码</strong></div></td>
                <td width="15%" class="2-td2-t-left"><div align="left">
                 	<bean:define id="dto" name="FpzfForm" property="fpzlList"/>
                  	<html:select property="fpzldm" style='width:165px'>
                   	 <html:options collection="dto" labelProperty="fpzlmc" property="fpzldm"/>
                  	</html:select></div>
                </td>
                <td class="2-td2-t-center"><div align="left"><html:text property="qshm" size="15"/>&nbsp;</div></td>
              </tr>
              <tr>
              	<td width="15%" class="2-td2-left"><div align="right"><strong>确认发票号码</strong></div></td>
               	<td width="15%" class="2-td2-left"><div align="left">
               		<bean:define id="dta" name="FpzfForm" property="cxfpzlList"/>
               		<html:select property="cxfpzldm" style='width:165px'>
               		<html:options collection="dta" labelProperty="cxfpzlmc" property="cxfpzldm"/>
               		</html:select></div></td>
               	<td class="2-td2-center"><div align="left"><html:text  property="cxqshm" size="15"/>&nbsp;</div></td>
               </tr>
            </table>
            
            <hr width="99%" class="hr1" size=1>
			<table width="75%" border="0" cellspacing="0" class="table-99">
              <tr>
                <td width="22%" class="2-td2-t-left">
                  <div align="right">录入人</div></td>
                <td width="31%" class="2-td2-t-left">
                  <div align="left">
                    <bean:write name="FpzfForm" property="lrr" />&nbsp;
                </div></td>
                <td width="16%" class="2-td2-t-left">
                  <div align="right">录入日期</div></td>
                <td width="31%" class="2-td2-t-center">
                  <div align="left">
                    <bean:write name="FpzfForm" property="lrrq" />&nbsp;
                </div></td>
              </tr>
            </table>     
            
            <br>

      <DIV align=center>
      	<img onClick="doSubmitForm('Save');" alt="保存" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image121','','<%=static_file%>images/baocun2.jpg',1)"  src="<%=static_file%>images/baocun1.jpg" name="Image121" width="79" height="22" border="0" id="Image121" >
      	<img onClick="doSubmitForm('Clear');" alt="清空" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image122','','<%=static_file%>images/qingkong2.jpg',1)"  src="<%=static_file%>images/qingkong1.jpg" name="Image122" width="79" height="22" border="0" id="Image122" >
      	<img onClick="doSubmitForm('Fpdk');" alt="转发票代开" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image123','','<%=static_file%>images/fpdk2.jpg',1)"  src="<%=static_file%>images/fpdk1.jpg" name="Image123" width="100" height="22" border="0" id="Image123" >
      	<img onclick="doSubmitForm('Return');" alt="退出"  onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">
      </DIV>
      <BR>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
<script language=JavaScript type='text/JavaScript'>

function doSubmitForm(operationType)
{
	
	if(operationType=="Save")
    {	
        var fpzldm = document.forms[0].fpzldm.value;
        var cxfpzldm = document.forms[0].cxfpzldm.value;
        var fphm = document.forms[0].qshm.value;
        var cxfphm = document.forms[0].cxqshm.value;
        
        if(fphm=="" && cxfphm =='')
		{
			alert("请输入相应的条件！");
			return false;
		}
        
        if(fpzldm!=cxfpzldm)
        { 
        	alert("所选发票种类不一致，请重新选择！"); 
        	return false;    
        }
        if(fphm!=cxfphm)
        { 
        	alert("所输发票号码不一致，请重新输入！"); 
        	return false;    
        }
        
    }
    
    document.all.operationType.value = operationType;
    document.forms[0].submit();
}

</script>

