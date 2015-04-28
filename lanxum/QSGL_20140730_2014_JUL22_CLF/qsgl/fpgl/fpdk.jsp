<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@page import="com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl.FpdkForm"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@page import="com.creationstar.bjtax.qsgl.util.DateUtils"%>
<%@page import ="java.util.Date"%>
<%@include file="/include/include.jsp"%>
<%
    session = request.getSession(false);
    FpdkForm fpdkForm = (FpdkForm)session.getAttribute("FpdkForm");
%>

<HTML>

<HEAD><TITLE>发票代开管理</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0" onload="changeWSZH()" >

<logic:equal name="FpdkForm" property="bccgbz" value="1">
<applet name="printer" codebase="<%=static_file%>printer" code="com.ttsoft.bjtax.webprint.FpglPagePrinter" width="0" height="0" archive="vprinter.jar">
</applet>
</logic:equal>

<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="发票管理&gt;发票代开管理"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<html:form action="/fpgl/fpdk.do">
<html:hidden property="operationType"/>
<html:hidden property="hdjg"/>
<html:hidden property="bzValueSubm"/>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <table align="center" cellspacing="0" class="table-99">
	  <TBODY>
        <tr>
          <td class="1-td1"><a>发票代开管理</a></td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
            <table width="75%" border="0" cellspacing="0" class="table-99">
              <tr>
                <td width="15%" class="2-td2-t-left"><div align="right"><strong>开票日期</strong></div></td>
                <td class="2-td2-t-left"><div align="center"> <%=DateUtils.displayValue(new Date(),"yyyy-MM-dd")%> </div></td>
                <td width="15%" class="2-td2-t-left"><div align="right"><strong>代开单位名称</strong></div></td>
                <td width="50%" class="2-td2-t-center"><div align="center"><bean:write name="FpdkForm" property="swjgzzjgmc" />&nbsp;</div></td>
              </tr>
              <tr>
                <td width="15%" class="2-td2-left"><div align="right"><strong>合同编号</strong></div></td>
                <td width="25%" class="2-td2-left"><div align="left"><html:text property="htbh" maxlength="18" size="22"/>&nbsp;<FONT color=red>*</FONT></div></td>
                <td colspan="2" class="2-td2-center">
                <div align="center">
                <img onClick="doSubmitForm('Query')" alt="查询" style="cursor:hand" src="<%=static_file%>/images/chaxun1.jpg"  name="Image1111" width="79" height="22" border="0" id="Image1111" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1111','','<%=static_file%>/images/chaxun2.jpg',1)" >
                </div></td>
              </tr>
              </table>
            <br>
            <table width="75%" border="0" cellspacing="0" bgcolor="f3f3f3" class="table-99">
              <tr>
                <td width="15%" class="2-td2-t-left"><div align="right"><strong>行业分类</strong></div></td>
                <td colspan="4" class="2-td2-t-center"><div align="left"><html:text property="hyfl" size="30" readonly="true" /></div></td>
              </tr>
              <tr>
                <td nowrap width="15%" class="2-td2-left"><div align="right">&nbsp;<strong>付款单位（个人）</strong></div></td>
                <td colspan="4" class="2-td2-center"><div align="left"><html:text property="nsrmc_buyer" size="81" readonly="true"/></div></td>
              </tr>
              <tr>
                <td nowrap width="15%" class="2-td2-left"><div align="right">&nbsp;<strong>收款单位（个人）</strong></div></td>
                <td colspan="4" class="2-td2-center"><div align="left"><html:text property="nsrmc_seller" size="81" readonly="true"/></div></td>
              </tr>
              <tr>
                
                <td colspan="2" class="2-td2-left"><div align="center"><strong>品</strong>&nbsp;&nbsp;<strong>目</strong></div></td>
                <td width="15%" class="2-td2-left"><div align="center"><strong>单</strong>&nbsp;&nbsp;<strong>价</strong></div></td>
                <td width="15%" class="2-td2-left"><div align="center"><strong>数</strong>&nbsp;&nbsp;<strong>量</strong></div></td>
                <td width="15%" class="2-td2-center"><div align="center"><strong>金</strong>&nbsp;&nbsp;<strong>额</strong></div></td>
              </tr>
              <tr>
                
              	<td colspan="2" class="2-td2-left"><div align="center">&nbsp;&nbsp;&nbsp;<html:text name="FpdkForm" property="pm" size="30" readonly="true" /></div></td>
                <td width="15%" class="2-td2-left"><div align="center"><html:text name="FpdkForm" property="dj" size="14" readonly="true"/></div></td>
                <td width="15%" class="2-td2-left"><div align="center"><html:text name="FpdkForm" property="sl" size="8" readonly="true" /></div></td>
                <td width="15%" class="2-td2-center"><div align="center"><html:text name="FpdkForm" property="je" size="14" readonly="true" /></div></td>
              </tr>
              <tr>
                <td width="15%" class="2-td2-left"><div align="right"><strong>房屋产权证书</strong></div></td>
                <td colspan="4" class="2-td2-center"><div align="left"><html:text name="FpdkForm" property="fwcqzh" size="81" readonly="true" /></div></td>
              </tr>
              <tr>
                <td width="15%" class="2-td2-left"><div align="right"><strong>房屋坐落地址</strong></div></td>
                <td colspan="4" class="2-td2-center"><div align="left"><html:text name="FpdkForm" property="fwzldz" size="81" readonly="true" /></div></td>
              </tr>
              <tr>
                <td width="15%" class="2-td2-left"><div align="right"><strong>备注</strong></div></td>
                <td colspan="4" class="2-td2-center">
                	<div align="left" id ="hdjeID"><html:text name="FpdkForm" property="bzValue" size="81"/></div>
                	<div align="left"><html:textarea property="bz" rows="3" cols="80" onkeypress="return imposeMaxLength(this, 200);" /></div></td>
              </tr>
              <tr>
                <td width="15%" class="2-td2-left"><div align="right"><strong>小写合计</strong></div></td>
                <td colspan="4" class="2-td2-center"><div align="left"><html:text property="xxhj" name="FpdkForm" size="81" readonly="true" /></div></td>
              </tr>
              <tr>
                <td width="15%" class="2-td2-left"><div align="right"><strong>大写合计</strong></div></td>
                <td colspan="4" class="2-td2-center"><div align="left"><html:text property="dxhj" name="FpdkForm" size="81" readonly="true"/></div></td>
              </tr>
              <tr>
                <td width="15%" class="2-td2-left"><div align="right"><strong>完税凭证号码</strong></div></td>
				<td colspan="4" class="2-td2-center"><div align="left"><html:text property="wszh" size="30"/>&nbsp;<FONT color=red>*</FONT></div></td>
              </tr>
              <tr>
                <td width="15%" class="2-td2-left"><div align="right"><strong>机打票号</strong></div></td>
                <td width="15%" class="2-td2-left"><div align="left">
                  <bean:define id="dto" name="FpdkForm" property="fpzlList"/>
                  <html:select property="fpzldm" onchange="clearFpqshm()" disabled="true" style='width:165px'>
                    <html:options collection="dto" labelProperty="fpzlmc" property="fpzldm"/>
                  </html:select></div>
                </td>
                <td colspan="3" class="2-td2-center"><div align="left"><html:text property="qshm" size="15" readonly="true"/>&nbsp;<FONT color=red>*</FONT></div></td>
              </tr>
              <tr>
                <td width="15%" class="2-td2-left"><div align="right"><strong>开票人</strong></div></td>
                <td colspan="4" class="2-td2-center"><div align="left"><html:text property="kpr" size="30"/>&nbsp;<FONT color=red>*</FONT></div></td>
              </tr>
            </table>
            
            <br>

      		<DIV align=center>
      			<logic:equal name="FpdkForm" property="fpbcxx" value="">
      				<img onClick="doSubmitForm('Save');" alt="保存并打印" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image121','','<%=static_file%>images/baochundy2.jpg',1)"  src="<%=static_file%>images/baochundy1.jpg" name="Image121" width="90" height="22" border="0" id="Image121" >
       			</logic:equal>
       			<img onClick="doSubmitForm('Fpzf')" alt="作废" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image122','','<%=static_file%>images/zuofei2.jpg',1)"  src="<%=static_file%>images/zuofei1.jpg" name="Image122" width="79" height="22" border="0" id="Image122" >
       			<img onClick="doSubmitForm('Clear')" alt="清空" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image123','','<%=static_file%>images/qingkong2.jpg',1)"  src="<%=static_file%>images/qingkong1.jpg" name="Image123" width="79" height="22" border="0" id="Image123" >
       			<img onClick="doSubmitForm('Mfskzs')" alt="转卖方税款征收" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image124','','<%=static_file%>images/mfskzs2.jpg',1)"  src="<%=static_file%>images/mfskzs1.jpg" name="Image124" width="120" height="22" border="0" id="Image124" >
       			<img onClick="doSubmitForm('AddSbGr')" alt="转契税申报" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image125','','<%=static_file%>images/qssb2.jpg',1)"  src="<%=static_file%>images/qssb1.jpg" name="Image125" width="100" height="22" border="0" id="Image125" >
      			<!--  
      			<img onClick="doSubmitForm('Clfxxcj')" alt="转存量房信息采集" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image126','','<%=static_file%>images/clfxxcj2.jpg',1)"  src="<%=static_file%>images/clfxxcj1.jpg" name="Image126" width="130" height="22" border="0" id="Image126" >
      			-->
      			<img onclick="doSubmitForm('Return')" alt="退出"  onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">
      		</DIV>
      <BR>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
<script language=JavaScript type='text/JavaScript'>

//禁止刷新
//function document.onkeydown()
//{
//	if(event.keyCode==116)
//	{
//		event.keyCode=0;
//		event.cancelBubble=true;
//		return false;
//	}
//}

//判断完税凭证（查询之后有值不可修改，无值可修改）
function changeWSZH()
{
	var wszh = "<%=fpdkForm.getWszh()%>"; //完税证号码
	if(wszh !="" && wszh != null && wszh != "null")
	{
		document.forms[0].wszh.disabled = true;
	}
	
	//获取备注核定值
	var hdjeID = document.all("hdjeID");
	var jeTemp = document.all.hdjg.value;
	if(parseFloat(jeTemp) <= 0.00)
	{
		hdjeID.style.display="none";
		document.all.hdjg.disabled=true;
	}
	else
	{
		hdjeID.style.display="block";
		document.all.bzValue.value = "核定计税价格："+jeTemp+"。 ";
		document.all.bzValue.disabled=true;
		putObjectValue(document.forms[0].bzValueSubm,"",document.all.bzValue.value);
	}
}

function doSubmitForm(operationType)
{
	if(operationType=="Query")
    {
        if(document.forms[0].htbh.value=="")
		{
			alert("请输入相应的查询条件！");
			document.forms[0].htbh.focus();
			return false;
		}
		
		//变更select 属性disable值
		if(document.forms[0].fpzldm.disabled ="true")
		{

			document.forms[0].fpzldm.removeAttribute('disabled');
		}
    }
    
	if(operationType=="Save")
	{
		var maxJe =<%=Constants.FP_MAX_JE%>;
		
		if(document.forms[0].je.value - maxJe > 0 )
		{
			if (!confirm("合同总金额超过单张发票最高限额，请确认是否继续！"))
			return false;
		}
		
		if(document.forms[0].hyfl.value=="")
		{
			alert("行业分类不可为空！");
			document.forms[0].hyfl.focus();
			return false;
		}
		
		if(document.forms[0].nsrmc_buyer.value=="")
		{
			alert("付款单位（个人）不可为空！");
			document.forms[0].nsrmc_buyer.focus();
			return false;
		}
		
		if(document.forms[0].nsrmc_seller.value=="")
		{
			alert("收款单位（个人）不可为空！");
			document.forms[0].nsrmc_seller.focus();
			return false;
		}
		
		if(document.forms[0].fwcqzh.value=="")
		{
			alert("房屋产权证书不可为空！");
			document.forms[0].fwcqzh.focus();
			return false;
		}
		
		if(document.forms[0].fwzldz.value=="")
		{
			alert("房屋坐落地址不可为空！");
			document.forms[0].fwzldz.focus();
			return false;
		}
		
		if(document.forms[0].wszh.value=="")
		{
			alert("完税凭证号码不可为空！");
			document.forms[0].wszh.focus();
			return false;
		}
		
		if(document.forms[0].qshm.value=="")
		{
			alert("机打票号不可为空！");
			document.forms[0].qshm.focus();
			return false;
		}
		
		if(document.forms[0].kpr.value=="")
		{
			alert("开票人不可为空！");
			document.forms[0].kpr.focus();
			return false;
		}
		
		if(document.forms[0].dj.value=="")
		{
			alert("单价不可为空！");
			document.forms[0].dj.focus();
			return false;
		}
        
        if(document.forms[0].sl.value=="")
		{
			alert("数量不可为空！");
			document.forms[0].sl.focus();
			return false;
		}
        if(!checkFormNumber(document.forms[0].sl,"数量"))
        {
            sl.focus();
            return false;
        }
        
        if(document.forms[0].je.value=="")
		{
			alert("金额不可为空！");
			return false;
		}
		
		if (!confirm("您的操作是：“保存”。是否继续？"))
    	return false;
	}
	
	if(operationType=="Clear")
    {
        if(!confirm("您的操作将清除页面中的所有数据，是否继续？"))
       		return false;
    }
    
    document.all.operationType.value = operationType;
    document.forms[0].submit();
}

 //获得行业分类和品目 默认值
 getApprove();
 function getApprove()
 {
	document.forms[0].hyfl.value = '销售不动产';
	document.forms[0].pm.value = '存量房交易';
	document.forms[0].sl.value = '1';
 }
 
 //清空发票号码
 function clearFpqshm()
 {
	document.forms[0].qshm.value = '';
 }

//检查输入数字的正确性
function checkFormNumber(ob,name)
{
	ob.required = "true";//验证非空
	ob.captionName = name;
	if(!checker_checkInput(ob))
	{
		return false;
	}
	else if(!checkNumber(ob.value,null,null))
	{//验证数字正确性
		alert("“"+ob.captionName+"”不是正确数字！");
		ob.select();
		return false;
	}else{
		return true;
	}
}

//置值方法
function putObjectValue(hiddProperty,td_id,obj_value)
{
	if(hiddProperty != null && hiddProperty != "")
	{
		hiddProperty.value=obj_value;
	}
	return true;
}

//金额格式
function  changeJE(je)
{
	var strJe = je.toString();
    var rs = strJe.indexOf('.');  
     
    if (rs < 0) {  
         rs = strJe.length;  
         strJe += '.';  
    }  
    while (strJe.length <= rs + 2) {  
         strJe += '0';  
    }  
    return strJe;  
}

//限制textarea输入最长值
function imposeMaxLength(Object, MaxLen) 
{ 
	return (Object.value.length <MaxLen); 
} 
</script>

<script language=JavaScript type='text/JavaScript'>
 StartPrint();
function StartPrint()
{
	var bccgbz ="<%=fpdkForm.getBccgbz()%>" //保存成功标志
	if(bccgbz == 1)
	{
		goprint();
	}
}
function goprint()
{
	
<%
  for(int index =0; index < fpdkForm.getPrintList().size();index ++){
    FpdkForm tempPrintForm = (FpdkForm)fpdkForm.getPrintList().get(index);
%>
	var fpzldm = "<%=tempPrintForm.getFpzldm()%>"; //发票种类代码
	var fphm = "<%=tempPrintForm.getFphm()%>"; //发票号码
    var kprq = "<%=tempPrintForm.getKprq()%>"; //开票日期
    var hyfl = "<%=tempPrintForm.getHyfl()%>"; //行业分类
    var fkdw = "<%=tempPrintForm.getNsrmc_buyer()%>"; //付款单位
	var skdw = "<%=tempPrintForm.getNsrmc_seller()%>"; //收款单位
	var dkdw = "<%=tempPrintForm.getSwjgzzjgmc()%>"; //代开单位
	var pm = "<%=tempPrintForm.getPm()%>"; //品目（项目）
	var dj = "<%=tempPrintForm.getDj()%>"; //单价
	var sl = "<%=tempPrintForm.getSl()%>"; //数量
	var je = "<%=tempPrintForm.getJe()%>"; //金额
	var fwcqzh = "<%=tempPrintForm.getFwcqzh()%>"; //房屋产权证号
	var fwzldz = "<%=tempPrintForm.getFwzldz()%>"; //房屋坐落地址
	var bzItem = "<%=tempPrintForm.getBz()%>"; //备注
	var bz ="";
	if(bzItem !=null && bzItem !="null" && bzItem !="")
	{
		bz="<%=tempPrintForm.getBz()%>";
	}
	var xxhj = "<%=tempPrintForm.getXxhj()%>"; //小写合计
	var dxhj = "<%=tempPrintForm.getDxhj()%>"; //大写合计
	var wszh = "<%=tempPrintForm.getWszh()%>"; //完税证号码
	var jdhm = "<%=tempPrintForm.getJdhm()%>"; //机打号码
	var kpr = "<%=tempPrintForm.getKpr()%>"; //开票人
	
	//alert("发票种类代码:"+fpzldm+" 号码:"+fphm+" 开票日期:"+kprq+" 行业分类:"+hyfl+" 付款单位:"+fkdw+" 收款单位:"+skdw+" 品目:"+pm+" 单价:"+dj+" 数量:"+sl+" 金额:"+je+" 产权:"+fwcqzh+" 地址:"+fwzldz+" 备注:"+bz+" 小写合计:"+xxhj+" 大写合计:"+dxhj+" 完税证:"+wszh+" 机打号码:"+jdhm+" 开票人:"+kpr);
    
    document.printer.setfpzldm(fpzldm);
    document.printer.setfphm(fphm);
    document.printer.setkprq(kprq);
    document.printer.sethyfl(hyfl);
    document.printer.setfkdw(fkdw);
    document.printer.setskdw(skdw);
    document.printer.setdkdw(dkdw);
    document.printer.setpm(pm);
    document.printer.setdj(dj);
    document.printer.setsl(sl);
    document.printer.setje(je);
    document.printer.setfwcqzh(fwcqzh);
    document.printer.setfwzldz(fwzldz);
    document.printer.setbz(bz);
    document.printer.setxxhj(xxhj);
    document.printer.setdxhj(dxhj);
    document.printer.setwszh(wszh);
    document.printer.setjdhm(jdhm);
    document.printer.setkpr(kpr);
    
    document.printer.startPrint();
	<%
	}
	%>
}
</script>
