<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>

<HTML><HEAD><TITLE>卖方契税查询</TITLE>
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
        //获得页面所有的checkbox
     	var checkboxlist = document.getElementsByName("selectIndex");
     	var count=0;//记录被选定保存的记录数
     	for (var i = 0; i < checkboxlist.length; i++) 
     	{
	         if(checkboxlist.item(i).checked)
	         {
				  count++;
	         } 
		 }
		 
		  if(!window.confirm("请先打印再确认！"))
			{
				return false;
			}			 
		 
		if(count==0&&checkboxlist.length>0)
		{
		  //alert("请选择最后确认的数据！");
		  //return false;
		  if(!window.confirm("以上数据是否无对应的查询结果,请确认"))
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
			alert("证件号码和纳税人名称不能同时为空！");
			return false;
		}
		if(nsrmc_his!="" && tdfwzldz_his==""){
			alert("您填写了纳税人名称，请录入房屋地址！");
			return false;
		}			    
    	document.all.operationType.value = operationType;
    	document.forms[0].submit();	    	
    }
    
    if(operationType=="PrintQrb")
    {
      var saveSbbhKeys = new Array();				
     	var checkboxlist = document.getElementsByName("selectIndex");
     	
     	var count=0;//记录被选定保存的记录数
     	for (var i = 0; i < checkboxlist.length; i++) 
     	{
	         if(checkboxlist.item(i).checked)
	         {
				   count++;
	         } 
		 }
		 if(count==0&&checkboxlist.length>0)
		 {
		 	
		 	if(!window.confirm("以上数据是否无对应的查询结果,请确认"))
			{
		    alert("请选择最后确认打印的数据！");
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
 <jsp:param name="subtitle" value="契税申报&gt;存量房交易管理&gt;卖方契税查询"/>
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
          <TD class=1-td1>契税查询</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/clfgl/mfskzs.do" type="POST">
            <html:hidden property="operationType"/>
            <html:hidden property="sbbh_his"/>
            <html:hidden property="fwhdlxdm"/><!-- 房屋核定类型代码 -->
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
                <td class="2-td2-t-left" nowrap="nowrap" width="25%">证件号码</td>
                <td class="2-td2-t-left" width="25%">
                    <div align=left>
						<html:text property="cxzjhm" size="25" maxlength="20"/>
					</div>
                </td>
                <td class="2-td2-t-left" nowrap="nowrap" width="25%">纳税人名称</td>
				<td class="2-td2-t-center" nowrap="nowrap" width="25%">
                    <div align=left>
						   <html:text property="nsrmc_his" size="25" maxlength="20"/>
					</div>
                </td>
               </tr>
               <tr> 	
                <td class="2-td2-left" nowrap="nowrap">房屋地址</td>
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
                      width="79" height="22" id="chaxun1" alt="查询" style="cursor:hand">
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
                <TD colspan="11">--------------卖方上次缴纳税款信息--------------</TD></TR>
       <TR>
       	<TD class="2-td1-left" height="30" noWrap>选中</TD>
        <TD class="2-td1-left" noWrap>序号</TD>         	
       	<TD class="2-td1-left" noWrap>纳税人姓名</TD>
        <TD class="2-td1-left" noWrap>证件号码</TD>
        <TD class="2-td1-left" noWrap>房地产位置</TD>
        <TD class="2-td1-left" noWrap>计税金额</TD>
        <TD class="2-td1-left" noWrap>房地产权属转移面积</TD>
        <TD class="2-td1-left" noWrap>房屋权属转移类型</TD>
        <TD class="2-td1-left" noWrap>合同签订日期</TD>        
        <TD class="2-td1-left" noWrap>房屋所有权证号</TD>                                     	
        <TD class="2-td1-left" noWrap>证件类型</TD>
        <TD class="2-td1-left" noWrap>申报日期</TD>
        <TD class="2-td1-left" noWrap>税率</TD>
        <TD class="2-td1-center" noWrap>应纳金额</TD>
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
      			<img onClick="doSubmitForm('Confirm');return false;" alt="确定" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('queding','','<%=static_file%>images/queding2.jpg',1)"  src="<%=static_file%>images/queding1.jpg" name="queding" width="79" height="22" border="0" id="queding" >&nbsp;
      			<img onClick="doSubmitForm('PrintQrb');return false;" alt="打印" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('dayin','','<%=static_file%>images/dayin2.jpg',1)"  src="<%=static_file%>images/dayin1.jpg" name="dayin" width="79" height="22" border="0" id="dayin" >&nbsp;
    			  <img onclick="doSubmitForm('Back');return false;" alt="返回"  onMouseOver="MM_swapImage('fanhui','','<%=static_file%>images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/fanhui1.jpg" name="fanhui" width="79" height="22" id="fanhui"  style="cursor:hand">
      		</DIV>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
