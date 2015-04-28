<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>

<HTML><HEAD><TITLE>卖方申报信息打印</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<applet name="Vprinter" code="com.ttsoft.bjtax.webprint.SSTYWSZPageNewPrinter" width="1" height="1" archive="<%=static_file%>printer/vprinter.jar">
</applet>	
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="契税申报&gt;存量房交易管理&gt;卖方申报信息打印"/>
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
          <TD class=1-td1>收现缴款书</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/clfgl/mfskzs.do" type="POST">
            <html:hidden property="operationType"/>
            <html:hidden property="jsjdm" name ='mfskzsForm'/>
            <html:hidden property="wszxh" name ='mfskzsForm'/>
            <html:hidden property="sbbh" name ='mfskzsForm'/>
            <html:hidden property="htbh" name ='mfskzsForm'/>  
            <html:hidden property="bz" name ='mfskzsForm'/>   
            <html:hidden property="isPrintSuccess" name ='mfskzsForm'/>           	            	
             <TABLE border="0" cellSpacing=0 class=table-99>
              <TBODY>							 
               <tr>
			    			<td class="2-td2-left" nowrap="nowrap">序号</td>
                <td class="2-td2-left" nowrap="nowrap">年度子别</td>
                <td class="2-td2-left" nowrap="nowrap">收现缴款书号</td>
                <td class="2-td2-left" nowrap="nowrap">金额</td>
								<td class="2-td2-center" nowrap="nowrap">&nbsp;</td>
			   			</tr>							 
              <logic:iterate id="dataList" name="mfskzsForm" property="sbxxList" indexId="index">   
               <TR>
								<TD class="2-td2-left" noWrap><%=index.intValue()+1%>&nbsp;</td>	  
       					<TD class="2-td2-left" noWrap><bean:write name="dataList" property="ndzb"/></TD>
        				<TD class="2-td2-left" noWrap><bean:write name="dataList" property="wszh"/></TD>
        				<TD class="2-td2-left" noWrap><bean:write name="dataList" property="hjsjje"/></TD>
								<td class="2-td2-center" nowrap="nowrap">
                  <DIV align=center>
                  <IMG name="dywsz"
                      onMouseOver="MM_swapImage('dywsz','','<%=static_file%>images/dywsz2.jpg',1)"
                      onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/dywsz1.jpg"
                      onclick = "doSubmit('PrintWsz','<bean:write name="dataList" property="jsjdm"/>','<bean:write name="dataList" property="wszxh"/>');return false;"
                      width="79" height="22" id="dywsz" alt="打印收现缴款书" style="cursor:hand">
                  </DIV>
                </td>
      </TR> 
      </logic:iterate>	
     </TABLE>
     <br>
      		<DIV align=center>
      			<img onclick="doSubmitForm('Back');return false;" alt="转卖方税款征收"  onMouseOver="MM_swapImage('fanhui','','<%=static_file%>images/mfskzs2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/mfskzs1.jpg" name="fanhui" width="110" height="22" id="fanhui"  style="cursor:hand">&nbsp;
    			  <img onclick="doSubmitForm('Return');return false;" alt="退出"  onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">
      		</DIV>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY>

<script language=JavaScript type='text/JavaScript'>
function doSubmit(operationType,jsjdm,wszxh)
{    
		document.forms[0].jsjdm.value=jsjdm;
		document.forms[0].wszxh.value=wszxh;
		//alert(document.forms[0].jsjdm.value);	
		//alert(document.forms[0].wszxh.value);	
		//alert(document.forms[0].htbh.value);	
		//alert(document.forms[0].sbbh.value);				  
    document.all.operationType.value = operationType;
    document.forms[0].submit(); 	
}

function doSubmitForm(operationType)
{    
		  
    document.all.operationType.value = operationType;
    document.forms[0].submit(); 	
}

StartPrint();
function StartPrint()
{
	var dybs ="<bean:write name="mfskzsForm" property="dybs" />" //打印标志
	if(dybs == 1)
	{
		goprint();
	}
}
function goprint(){
    var wszh = "<bean:write name="mfskzsForm" property="headWszh" />";//在此加通用完税证号，
    var zclx = "<bean:write name="mfskzsForm" property="zclx" />";//注册类型
    var tfrq = "<bean:write name="mfskzsForm" property="tfrq" />";//填发日期
    var zsjg = "<bean:write name="mfskzsForm" property="zsjg" />";//征收机关
    var wsdwdm = "<bean:write name="mfskzsForm" property="jsjdm" />";//计算机代码
    var dz = "<bean:write name="mfskzsForm" property="dz" />";//地址
    var wsdwmc = "<bean:write name="mfskzsForm" property="nsrmc" />";
    var wsjehj = "￥<bean:write name="mfskzsForm" property="hjje" />";//合计金额
    var wsjehj_cn = "<bean:write name="mfskzsForm" property="hjjedx" />";//合计金额大写
    var wsbz = "<bean:write name="mfskzsForm" property="wszbz" />";//备注

    var wsmxsz = "<bean:write name="mfskzsForm" property="mxSz" />";
    var wsmxpmmc = "<bean:write name="mfskzsForm" property="mxPmmc" />";
    var wsmxkssl = "<bean:write name="mfskzsForm" property="mxKssl" />";
    var wsmxjsje = "<bean:write name="mfskzsForm" property="mxJsje" />";
    var wsmxsl = "<bean:write name="mfskzsForm" property="mxSl" />";
    var wsmxskssrq = "<bean:write name="mfskzsForm" property="mxSkssrq" />";
    var wsmxyjje = "<bean:write name="mfskzsForm" property="mxYjhkc" />";
    var wsmxsjje = "<bean:write name="mfskzsForm" property="mxSjse" />";
	
		document.Vprinter.setWszh(wszh);//在此添加完税证号赋值方法
    document.Vprinter.setZclx(zclx);
    document.Vprinter.setTfrq(tfrq);
    document.Vprinter.setZsjg(zsjg);
    document.Vprinter.setWsdwdm(wsdwdm);
    document.Vprinter.setDz(dz);
    document.Vprinter.setWsdwmc(wsdwmc);
    document.Vprinter.setWsbz(wsbz);

    document.Vprinter.setWsmxsz(wsmxsz);
    document.Vprinter.setWsmxpmmc(wsmxpmmc);
    document.Vprinter.setWsmxkssl(wsmxkssl);
    document.Vprinter.setWsmxjsje(wsmxjsje);
    document.Vprinter.setWsmxsl(wsmxsl);
    document.Vprinter.setWsmxskssrq(wsmxskssrq);
    document.Vprinter.setWsmxyjje(wsmxyjje);
    document.Vprinter.setWsmxsjje(wsmxsjje);
    document.Vprinter.setWsjehj(wsjehj);
    document.Vprinter.setWsjehj_cn(wsjehj_cn);

    document.Vprinter.startPrint();
	//打开确认
	fnOpen();

}
	function fnOpen(){
	   var ret = window.showModalDialog("printConfirm.jsp", "",
		  "dialogHeight: 150px;dialogWidth: 300px;status:0");
	   if(!ret)  {
		 alert("请确认！");
		 fnOpen();
	   }
	   if(ret=="yes"){
		//alert("old number="+ret);
		//打印成功
		document.all.isPrintSuccess.value="true";
		//return ret;
	   }else{
		document.all.isPrintSuccess.value="false";
	   //document.all.bz.value="1";

	   }
	   document.all.operationType.value = "QueryWsz";
       document.forms[0].submit(); 
	  
	}
	
</SCRIPT>
</HTML>
