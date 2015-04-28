<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DateUtils"%>
<%@ page import ="java.util.Date"%>

<HTML><HEAD><TITLE>卖方缴税凭证打印</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>

function doSubmitForm(operationType)
{ 
	if(operationType=="DataSynchronism")
    {
    	if (document.forms[0].htbh.value == "")
		{
			alert("数据同步时合同编号不允许为空，请重新输入！");
			document.forms[0].htbh.focus();
			return false;
		}
    }	
    if(operationType=="QuerySbxx")
    {
    	if (document.forms[0].htbh.value == "")
		{
			alert("单笔查询合同编号不允许为空，请重新输入！");
			document.forms[0].htbh.focus();
			return false;
		}
    }
    
    //判断是否有房屋核定信息
	var hasHdxxVal = document.all.hasHdxx.value;
    if(operationType=="PrintFwhdxx")
    {
    	if(hasHdxxVal == "N")
		{
			alert("请核实是否已由税务机关对其进行核定！");
			return false;
		}
    }
    if(operationType=="PrintFwhdxx")
    {
		document.forms[0].target="_blank";
	}
	else
	{
		document.forms[0].target="";
	}
    
    var htbhValue = document.forms[0].htbh.value;
    if(operationType=="toFpdk" && htbhValue != "")
	{
		document.forms[0].action="/qsgl/fpgl/fpdk.do?operationType=Query&htbh ='"+htbhValue+"'";
		document.forms[0].submit();
		return false;
	}
	if(operationType=="Mfskzs")
	{
		document.forms[0].action="/qsgl/clfgl/mfskzs.do?operationType=Show";
		document.forms[0].submit();
		return false;
	}	
    document.all.operationType.value = operationType;
    document.forms[0].submit();	
}

function doSubmit(operationType,htbh,sbbh)
{
    document.all.operationType.value = operationType;
    document.forms[0].htbh1.value=htbh;
    document.forms[0].sbbh.value=sbbh;
    document.forms[0].submit();	
}


</SCRIPT>


<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="契税申报&gt;存量房交易管理&gt;卖方缴税凭证打印"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<SCRIPT language=javascript>
<!--



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
          <TD class=1-td1>个人存量房申报信息维护</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/clfgl/whsbxx.do" type="POST">
            <html:hidden property="operationType"/>
            <html:hidden name="mfskzsForm" property="hasHdxx"/><!-- 是否有核定信息 -->
            <input type="hidden" name="htbh1" value='<bean:write name="mfskzsForm" property="htbh1"/>'>
            <input type="hidden" name="sbbh" value='<bean:write name="mfskzsForm" property="sbbh"/>'>
            <input type="hidden" name="pzzhdm" value='<bean:write name="mfskzsForm" property="pzzhdm"/>'>            	            	
             <TABLE border="0" cellSpacing=0 class=table-99>
             	<TBODY>
             		<table  border=0 cellSpacing=0 class=tabled-99 width="100%">	
             			<tr>
             				<td class="2-td2-t-left" nowrap="nowrap" width="25%">当前日期</td>
             				<td class="2-td2-t-left" width="25%">
             					<div align="left"><%=DateUtils.displayValue(new Date(),"yyyyMMdd")%></div></td>
             				<td class="2-td2-t-left" nowrap="nowrap" width="25%">征收机关</td>
             				<td class="2-td2-t-center" colspan="5" nowrap="nowrap" width="25%">
             					<div align=left><bean:write name="mfskzsForm" property="zsjg" /></div></td>
             			</tr>						 
             			<tr>
             				<td class="2-td2-left" nowrap="nowrap" width="18%" >合同编号</td>
             				<td class="2-td2-left" nowrap="nowrap" width="18%">
             					<div align="left"><html:text property="htbh" size="18" maxlength="18"/></div></td>
             				<td class="2-td2-left" nowrap="nowrap" width="20%" >
             					<div align="left"> 
             					<DIV align=center> <IMG name="chaxun1" onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"  onclick = "doSubmitForm('QuerySbxx');return false;" width="79" height="22" id="chaxun1" alt="查询" style="cursor:hand"></DIV></div></td>
             				<td class="2-td2-center" nowrap="nowrap" width="30%" >
             				<DIV align=center> <IMG name="sjtb" onMouseOver="MM_swapImage('sjtb','','<%=static_file%>images/sjtb2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/sjtb1.jpg" onclick = "doSubmitForm('DataSynchronism');return false;" width="79" height="22" id="sjtb" alt="数据同步" style="cursor:hand">  </DIV> </td>				       
             			</tr>
             		</table>
             		<br>
             		<table  border="0" cellSpacing="0" class="tabled-99" width="100%" >
						<tr>
							<td colspan="6" class="2-td1-center">税务机关核定信息</td>
						</tr>															
					</table>
					<table  border="0" cellSpacing="0" class="tabled-99" width="100%" >
						<tr>
							<td colspan="6" class="2-td2-center">&nbsp;
								<logic:equal name="mfskzsForm" property="hasHdxx" value="Y">
								<img onclick="doSubmitForm('PrintFwhdxx')" alt="打印核定表"  onMouseOver="MM_swapImage('dyhdb','','<%=static_file%>images/dayinhdb2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/dayinhdb1.jpg" name="dyhdb1" width="100" height="22" id="dyhdb"  style="cursor:hand">
								</logic:equal>
							</td>
						</tr>															
					</table>
					<br>
					<table  border="0" cellSpacing="0" class="tabled-99" width="100%" >
						<tr>
							<td colspan="6" class="2-td1-center" style="border-bottom-style:none">卖方税款申报信息</td>
						</tr>															
					</table>
             		<table  border="0" cellSpacing="0" class="tabled-99" width="100%" >							 
             			<tr>
             				<td class="2-td1-left" nowrap="nowrap">序号</td>
                			<td class="2-td1-left" nowrap="nowrap">合同编号</td>
                			<td class="2-td1-left" nowrap="nowrap">申报表号</td>
                			<td class="2-td1-left" width="24%">纳税人名称</td>
                			<td class="2-td1-left" nowrap="nowrap" width="10%">税款合计金额</td>
							<td class="2-td1-center" nowrap="nowrap" colspan="3">&nbsp;</td>
			   			</tr>
							 
              			<logic:iterate id="dataList" name="mfskzsForm" property="sbxxHisList" indexId="index">   
      					<TR>
							<TD class="2-td2-left" noWrap><%=index.intValue()+1%>&nbsp;</td>	  
       						<TD class="2-td2-left" noWrap><bean:write name="dataList" property="htbh"/></TD>
        					<TD class="2-td2-left" noWrap><bean:write name="dataList" property="sbbh"/></TD>
        					<TD class="2-td2-left" noWrap><bean:write name="dataList" property="nsrmc"/></TD>
        					<TD class="2-td2-left" noWrap><bean:write name="dataList" property="sjhjje"/></TD>
							<td class="2-td2-left" nowrap="nowrap">
                  				<DIV align="center"> <IMG name="cswsz" onMouseOver="MM_swapImage('cswsz','','<%=static_file%>images/cswsz2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/cswsz1.jpg" onclick = "javascript:if(document.forms[0].pzzhdm.value==''){ return false;} else {doSubmit('SaveWsz','<bean:write name="dataList" property="htbh"/>','<bean:write name="dataList" property="sbbh"/>');return false;}"  width="79" height="22" id="cswsz" alt="生成完税证" style="cursor:hand"> </DIV></td>
                			<td class="2-td2-left" nowrap="nowrap">
                  				<DIV align="center"><IMG name="csjks" onMouseOver="MM_swapImage('csjks','','<%=static_file%>images/csjks2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/csjks1.jpg" onclick = "doSubmit('SaveJks','<bean:write name="dataList" property="htbh"/>','<bean:write name="dataList" property="sbbh"/>');return false;" width="79" height="22" id="csjks" alt="生成缴款书" style="cursor:hand"> </DIV> </td>
							<td class="2-td2-center" nowrap="nowrap">
                  				<DIV align="center"><IMG name="shanchu" onMouseOver="MM_swapImage('shanchu','','<%=static_file%>images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shanchu1.jpg" onclick = "doSubmit('Delete','<bean:write name="dataList" property="htbh"/>','<bean:write name="dataList" property="sbbh"/>');return false;" width="79" height="22" id="shanchu" alt="删除" style="cursor:hand"> </DIV> </td>
      					</TR> 
      					</logic:iterate>	
      				</table>						 							 				       
     
     			<BR>
				<BR>
				<BR>
				<BR>
              <DIV align="center">
    			  <img onClick="doSubmitForm('Mfskzs')" alt="转卖方税款征收" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image124','','<%=static_file%>images/mfskzs2.jpg',1)"  src="<%=static_file%>images/mfskzs1.jpg" name="Image124" width="110" height="22" border="0" id="Image124" >

    			  <img onClick="doSubmitForm('toFpdk');return false;" alt="转发票代开" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('zfpdk','','<%=static_file%>images/fpdk2.jpg',1)"  src="<%=static_file%>images/fpdk1.jpg" name="zfpdk" width="100" height="22" border="0" id="zfpdk" >

      			  <img onclick="doSubmitForm('Return')" alt="退出"  onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">	              	
              </DIV>
              
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</TABLE>
</BODY></HTML>
