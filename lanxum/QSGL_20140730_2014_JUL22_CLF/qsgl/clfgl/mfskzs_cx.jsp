<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>

<HTML><HEAD><TITLE>������˰��ѯ</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>

function doSubmitForm(operationType)
{
    if(operationType=="Confirm")
    {	
      var saveSbbhKeys = new Array();
        //���ҳ�����е�checkbox
     	var checkboxlist = document.getElementsByName("selectIndex");
     	var count=0;//��¼��ѡ������ļ�¼��
     	for (var i = 0; i < checkboxlist.length; i++) 
     	{
	         if(checkboxlist.item(i).checked)
	         {
				  count++;
	         } 
		 }
		 
		  if(!window.confirm("���ȴ�ӡ��ȷ�ϣ�"))
			{
				return false;
			}			 
		 
		if(count==0&&checkboxlist.length>0)
		{
		  //alert("��ѡ�����ȷ�ϵ����ݣ�");
		  //return false;
		  if(!window.confirm("���������Ƿ��޶�Ӧ�Ĳ�ѯ���,��ȷ��"))
			{
				return false;
			}		
		}
    		 for (var i = 0; i < checkboxlist.length; i++)
    		 {
		         if(checkboxlist.item(i).checked)
		         {
						var value=checkboxlist.item(i).value;
						saveSbbhKeys.push(value);
		        } 
		 	   }			
		document.forms[0].sbbh_his.value = saveSbbhKeys;
		    
    document.all.operationType.value = operationType;
    document.forms[0].submit();
    }
    
    if(operationType=="QueryDh")
    {
    	var cxzjhm = document.forms[0].cxzjhm.value;
    	var nsrmc_his = document.forms[0].nsrmc_his.value;
    	var tdfwzldz_his = document.forms[0].tdfwzldz_his.value;
      	if(cxzjhm=="" && nsrmc_his=="")
		{
			alert("֤���������˰�����Ʋ���ͬʱΪ�գ�");
			return false;
		}
		if(nsrmc_his!="" && tdfwzldz_his==""){
			alert("����д����˰�����ƣ���¼�뷿�ݵ�ַ��");
			return false;
		}			    
    	document.all.operationType.value = operationType;
    	document.forms[0].submit();	    	
    }
    
    if(operationType=="PrintQrb")
    {
      var saveSbbhKeys = new Array();				
     	var checkboxlist = document.getElementsByName("selectIndex");
     	
     	var count=0;//��¼��ѡ������ļ�¼��
     	for (var i = 0; i < checkboxlist.length; i++) 
     	{
	         if(checkboxlist.item(i).checked)
	         {
				   count++;
	         } 
		 }
		 if(count==0&&checkboxlist.length>0)
		 {
		 	
		 	if(!window.confirm("���������Ƿ��޶�Ӧ�Ĳ�ѯ���,��ȷ��"))
			{
		    alert("��ѡ�����ȷ�ϴ�ӡ�����ݣ�");
		    return false;
			}
			else
			{
					window.print();
					return false; 
			}	
		 } 
		     	
    	for (var i = 0; i < checkboxlist.length; i++)
    	{
		      if(checkboxlist.item(i).checked)
		      {
						var value=checkboxlist.item(i).value;
						saveSbbhKeys.push(value);
		      } 
		 	}
     	if(checkboxlist.length>0) 
     	{
    		document.all.operationType.value = operationType;
    		document.forms[0].sbbh_his.value = saveSbbhKeys;
    		document.forms[0].target = "_blank";
    		document.forms[0].submit();
    		document.forms[0].target = "_self";       		
		  }
		  else
		  {
		  	window.print(); 	
		  }				

    }
    
    if(operationType=="Back")
    {			    
    document.all.operationType.value = operationType;
    document.forms[0].submit();	    	
    }    	    	
}

</SCRIPT>


<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="��˰�걨&gt;���������׹���&gt;������˰��ѯ"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<SCRIPT language=javascript>
//<!--



//-->
</SCRIPT>
<br>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>��˰��ѯ</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/clfgl/mfskzs.do" type="POST">
            <html:hidden property="operationType"/>
            <html:hidden property="sbbh_his"/>
            <html:hidden property="fwhdlxdm"/><!-- ���ݺ˶����ʹ��� -->
            <input type="hidden" name="tfrq" value='<bean:write name="mfskzsForm" property="tfrq"/>'>
            <input type="hidden" name="zsjg" value='<bean:write name="mfskzsForm" property="zsjg"/>'>            	
            <input type="hidden" name="htbh" value='<bean:write name="mfskzsForm" property="htbh"/>'>
            <input type="hidden" name="sbbh" value='<bean:write name="mfskzsForm" property="sbbh"/>'>            	
            <input type="hidden" name="nsrmc" value='<bean:write name="mfskzsForm" property="nsrmc"/>'>            	
            <input type="hidden" name="zjhm" value='<bean:write name="mfskzsForm" property="zjhm"/>'>
            <input type="hidden" name="htwsqyrq" value='<bean:write name="mfskzsForm" property="htwsqyrq"/>'>
            <input type="hidden" name="rqcs" value='<bean:write name="mfskzsForm" property="rqcs"/>'>
             <TABLE border="0" cellSpacing=0 class=table-99>
              <TBODY>							 
               <tr>
                <td class="2-td2-t-left" nowrap="nowrap" width="25%">֤������</td>
                <td class="2-td2-t-left" width="25%">
                    <div align=left>
						<html:text property="cxzjhm" size="25" maxlength="20"/>
					</div>
                </td>
                <td class="2-td2-t-left" nowrap="nowrap" width="25%">��˰������</td>
				<td class="2-td2-t-center" nowrap="nowrap" width="25%">
                    <div align=left>
						   <html:text property="nsrmc_his" size="25" maxlength="20"/>
					</div>
                </td>
               </tr>
               <tr> 	
                <td class="2-td2-left" nowrap="nowrap">���ݵ�ַ</td>
				<td class="2-td2-left" nowrap="nowrap">
                    <div align=left>
						   <html:text property="tdfwzldz_his" size="25" maxlength="20"/>
					</div>
                    </td>
                    <td class="2-td2-center" nowrap="nowrap" colspan="2">
					<div align=left>
                      <DIV align=center>
                      <IMG name="query"
                      onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
                      onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
                      onclick = "doSubmitForm('QueryDh');return false;"
                      width="79" height="22" id="chaxun1" alt="��ѯ" style="cursor:hand">
                      </DIV>						
					</div>
				    </td>
                </tr>    
     </TABLE>
       <br>
				<div id="Layer2" style="position:static; overflow: auto;  width: 860px; height: 300px">
					<TABLE class=table-99 cellSpacing=0 align=center >
              <TBODY>
              <TR>
                <TD colspan="11">--------------�����ϴν���˰����Ϣ--------------</TD></TR>
       <TR>
       	<TD class="2-td1-left" height="30" noWrap>ѡ��</TD>
        <TD class="2-td1-left" noWrap>���</TD>         	
       	<TD class="2-td1-left" noWrap>��˰������</TD>
        <TD class="2-td1-left" noWrap>֤������</TD>
        <TD class="2-td1-left" noWrap>���ز�λ��</TD>
        <TD class="2-td1-left" noWrap>��˰���</TD>
        <TD class="2-td1-left" noWrap>���ز�Ȩ��ת�����</TD>
        <TD class="2-td1-left" noWrap>����Ȩ��ת������</TD>
        <TD class="2-td1-left" noWrap>��ͬǩ������</TD>        
        <TD class="2-td1-left" noWrap>��������Ȩ֤��</TD>                                     	
        <TD class="2-td1-left" noWrap>֤������</TD>
        <TD class="2-td1-left" noWrap>�걨����</TD>
        <TD class="2-td1-left" noWrap>˰��</TD>
        <TD class="2-td1-center" noWrap>Ӧ�ɽ��</TD>
      </TR>
              <logic:iterate id="dataList" name="mfskzsForm" property="sbxxHisList" indexId="index">   
      <TR>
       	<TD class="2-td1-left" height="30" noWrap><input value="<bean:write  name='dataList' property='sbbh_his'/>" type="radio" name="selectIndex" /></TD>
       	<TD class="2-td1-left" noWrap><%=index.intValue()+1%>&nbsp;</td>      	
       	<TD class="2-td1-left" noWrap><bean:write name="dataList" property="nsrmc_his"/></TD>
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="sfzjhm_his"/></TD>
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="tdfwzldz_his"/></TD>
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="jsje_his"/></TD>
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="fwjzmj_his"/></TD>
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="fwqszylx_his"/></TD>
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="htqdsj_his"/></TD>        	        	
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="fwsyqzh_his"  filter="false"/></TD>
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="sfzjlxmc_his"/></TD>        	
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="sbrq_his"/></TD>
        <TD class="2-td1-left" noWrap><bean:write name="dataList" property="sl_his"/></TD>
        <TD class="2-td1-center" noWrap><bean:write name="dataList" property="ynse_his"/></TD>
      </TR> 
      </logic:iterate>         
							</TBODY>
						</TABLE>
					</div>
          <br>
					<br>
      		<DIV align=center>
      			<img onClick="doSubmitForm('Confirm');return false;" alt="ȷ��" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('queding','','<%=static_file%>images/queding2.jpg',1)"  src="<%=static_file%>images/queding1.jpg" name="queding" width="79" height="22" border="0" id="queding" >&nbsp;
      			<img onClick="doSubmitForm('PrintQrb');return false;" alt="��ӡ" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('dayin','','<%=static_file%>images/dayin2.jpg',1)"  src="<%=static_file%>images/dayin1.jpg" name="dayin" width="79" height="22" border="0" id="dayin" >&nbsp;
    			  <img onclick="doSubmitForm('Back');return false;" alt="����"  onMouseOver="MM_swapImage('fanhui','','<%=static_file%>images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/fanhui1.jpg" name="fanhui" width="79" height="22" id="fanhui"  style="cursor:hand">
      		</DIV>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
