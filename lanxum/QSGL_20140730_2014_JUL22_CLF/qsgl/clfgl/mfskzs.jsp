<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@page import="com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.MfskzsForm"%>

<HTML><HEAD><TITLE>卖方税款征收</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
</HEAD>


<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0" onload="checkFwyz('');init()">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="契税申报&gt;存量房交易管理&gt;卖方税款征收"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<SCRIPT language=javascript>

</SCRIPT>
<br>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>卖方税款征收</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/clfgl/mfskzs.do" type="POST">
            <html:hidden property="operationType"/>
            <html:hidden property="nsrmc" name ='mfskzsForm'/>
            <html:hidden name="mfskzsForm" property="hasHdxx"/><!-- 是否有核定信息 -->
            <html:hidden name="mfskzsForm" property="fwhdlxdm"/> <!--房屋核定类型代码 -->
            <html:hidden  property="scriptStr" />
            <input type="hidden" name="tdzzssl" value='<bean:write name="mfskzsForm" property="tdzzssl"/>'> <!-- 土地增值税税率 -->
            <input type="hidden" name="tfrq" value='<bean:write name="mfskzsForm" property="tfrq"/>'>
            <input type="hidden" name="zsjg" value='<bean:write name="mfskzsForm" property="zsjg"/>'>
            <input type="hidden" name="sbbh" value='<bean:write name="mfskzsForm" property="sbbh"/>'>
			<input type="hidden" name="sbbh_his" value='<bean:write name="mfskzsForm" property="sbbh_his"/>'>
			<input type="hidden" name="htbh1" value='<bean:write name="mfskzsForm" property="htbh1"/>'>	
			<input type="hidden" name="htwsqyrq" value='<bean:write name="mfskzsForm" property="htwsqyrq"/>'>
			<input type="hidden" name="rqcs" value='<bean:write name="mfskzsForm" property="rqcs"/>'>
             <TABLE border="0" cellSpacing=0 class=table-99>
              <TBODY>
							 <tr>
                <td class="2-td2-t-left" nowrap="nowrap" width="25%">填发日期</td>
                <td class="2-td2-t-left" width="25%">
                    <div align=left>
						<bean:write name="mfskzsForm" property="tfrq" />
					</div>
                </td>
                <td class="2-td2-t-left" nowrap="nowrap" width="25%">征收机关</td>
                <td class="2-td2-t-center" nowrap="nowrap" width="25%">
                    <div align=left>
						<bean:write name="mfskzsForm" property="zsjg" />
					</div>
                </td>
							 </tr>
               <tr>
                <td class="2-td2-left" nowrap="nowrap">合同编号</td>
                <td class="2-td2-left" nowrap="nowrap">
                    <div align=left>
						<html:text property="htbh" size="20" maxlength="20"/>
					</div>
                </td>
                <td class="2-td2-center" nowrap="nowrap" colspan="2">
					<div align=left>
      <DIV align=center>
      <IMG name="query"
          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
          onclick = "javascript:if(document.forms[0].htbh.value==''){ return false;} else {doSubmitForm('QueryGr');return false;}"
          width="79" height="22" id="chaxun1" alt="查询" style="cursor:hand">
      </DIV>						
					</div>
				       </td>
							 </tr>				       
						<tr>
                <td class="2-td2-left" nowrap="nowrap">纳税人名称</td>
                <td class="2-td2-left">
                    <div align=left>
						   &nbsp;<bean:write name="mfskzsForm" property="nsrmc"/>
					</div>
                </td>
                <td class="2-td2-left" nowrap="nowrap">证件号码</td>
                <td class="2-td2-center">
                    <div align=left>
						  &nbsp;<bean:write name="mfskzsForm" property="zjhm"/>
					</div>
                </td>				       
              </tr>
              							 </tr>				       
						<tr>
				<td class="2-td2-left" nowrap="nowrap">房屋核定类型</td>
                <td colspan="3" class="2-td2-center" nowrap="nowrap">
					<div align="center">经核定为住房&nbsp;<input type="radio" name="fwhdlx" value="<%=Constants.FWHDLX_HOUSING%>" disabled="disabled" onclick=""/>
                	&nbsp;经核定为非住房&nbsp;<input type="radio" name="fwhdlx" value="<%=Constants.FWHDLX_NONHOUSING%>" disabled="disabled" onclick=""/>
                	</div>
                </td>			       
              </tr>
     </TABLE>
       <br>
				<div id="Layer2" style="position:static; overflow: auto;  width: 860px; height: 120px">
					<TABLE class=table-99 cellSpacing=0 align=center id="sbxxHis">
              <TBODY>
              <TR>
                <TD colspan="11">--------------卖方上次缴纳税款信息--------------</TD></TR>
       <TR>
       	<TD class="2-td1-left" height="28" noWrap>纳税人姓名</TD>
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
       	<TD class="2-td1-left" height="28" noWrap><bean:write name="dataList" property="nsrmc_his"/></TD>
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
          <table>
          	<tr>
          		<td colspan="8">-------------------缴纳信息录入------------------</td>
          	</tr>
          </table>
          	
		  <table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0" id="CLFJKMX"  onkeydown=dokeydown(this.id,'szsmdm')  onmouseup='return dokeyUp(this.id)'>
			<ttsoft:smsbtable form="mfskzsForm" property="dataList" tableId="CLFJKMX" hasTitle="true"/>
			<DIV id=divSfTemp></DIV>
              <tr>
                <td nowrap class="2-td2-left">合计</td>
                <td nowrap class="2-td2-left" colspan="5">&nbsp;</td>
                <td nowrap class="2-td2-left"><html:text property="hjjsje" readonly="true" onkeydown="if(event.keyCode==13) event.keyCode=9;" size="12" styleClass="inputnoborder" /></td>
                <td nowrap class="2-td2-left"><html:text property="hjsjse" readonly="true" onkeydown="if(event.keyCode==13) event.keyCode=9;" size="12" styleClass="inputnoborder" /></td>
                <td nowrap class="2-td2-left"><html:text property="hjjmje" readonly="true" onkeydown="if(event.keyCode==13) event.keyCode=9;" size="12" styleClass="inputnoborder" /></td>
                <td nowrap class="2-td2-center"><html:text property="hjyjje" readonly="true" onkeydown="if(event.keyCode==13) event.keyCode=9;" size="12" styleClass="inputnoborder" /></td>
              </tr>
           </table>
           <table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0">
          	<tr id="jwyzId" >
              	<td nowrap class="2-td2-left" width="17%">&nbsp;建委查询房屋原值</td>
                <td nowrap class="2-td2-center" ><div align="left"> &nbsp;<html:text property="fwyz" size="20" maxlength="20" onchange="publicMethod()"/></div></td>
             </tr>
          </table>
							
										<BR>
										<BR>
										<BR>
      		<DIV align=center>
      			<img onClick="javascript:if(document.forms[0].htbh.value!=document.forms[0].htbh1.value || document.forms[0].htbh1.value==''){ return false;} else {befAdd('Add');return false;}" alt="增加" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('zengjia','','<%=static_file%>images/tianjia2.jpg',1)"  src="<%=static_file%>images/tianjia1.jpg" name="zengjia" width="79" height="22" border="0" id="zengjia" >&nbsp;
      			<img onClick="befSave('Save');return false;" alt="保存" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"  src="<%=static_file%>images/baocun1.jpg" name="baocun" width="79" height="22" border="0" id="baocun" >&nbsp;
       			<img onClick="befDel();return false;" alt="删除" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('shanchu','','<%=static_file%>images/shanchu2.jpg',1)"  src="<%=static_file%>images/shanchu1.jpg" name="shanchu" width="79" height="22" border="0" id="shanchu" >&nbsp;
       			<img onclick="doSubmitForm('toClfswjghdxxlr');return false;" alt="返回"  onMouseOver="MM_swapImage('fanhui1','','<%=static_file%>images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/fanhui1.jpg" name="back" width="79" height="22" id="fanhui1"  style="cursor:hand">
       			<img onclick="doSubmitForm('Return');return false;" alt="退出"  onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">       			
      			<BR>
      			<img onClick="doSubmitForm('toWhsbxx');return false;" alt="维护申报信息" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('whsbxx','','<%=static_file%>images/whsbxx2.jpg',1)"  src="<%=static_file%>images/whsbxx1.jpg" name="whsbxx" width="79" height="22" border="0" id="whsbxx" >&nbsp;
       			<img onClick="doSubmitForm('toFpdk');return false;" alt="转发票代开" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('zfpdk','','<%=static_file%>images/fpdk2.jpg',1)"  src="<%=static_file%>images/fpdk1.jpg" name="zfpdk" width="100" height="22" border="0" id="zfpdk" >&nbsp;
       			<img onClick="doSubmitForm('toAddSbGr');return false;" alt="转契税申报" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('zqssb','','<%=static_file%>images/qssb2.jpg',1)"  src="<%=static_file%>images/qssb1.jpg" name="zqssb" width="100" height="22" border="0" id="zqssb" >&nbsp;
      			<img onClick="doSubmitForm('toClfxxcj');return false;" alt="转存量房信息采集" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('zclfxxcj','','<%=static_file%>images/clfxxcj2.jpg',1)"  src="<%=static_file%>images/clfxxcj1.jpg" name="zclfxxcj" width="130" height="22" border="0" id="zclfxxcj" >&nbsp;
      			
      		</DIV>

<div id=szsmdm_epodDateLayer style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" ><select id=sel onclick=selectClick("szsmdm","CLFJKMX") onkeyup=selectMove("szsmdm","CLFJKMX")  onfocusout=selectClick("szsmdm","CLFJKMX") onkeydown=if(window.event.keyCode==13)selectClick("szsmdm","CLFJKMX")></select></div>

<INPUT TYPE="hidden" NAME="szsmdm_focus">
      		
</html:form>
<%@ include file="../include/BottomMF.jsp" %>

<script language=JavaScript type='text/JavaScript'>

var szsmdm_value ="";
var szsmdm_hd_value ="";
//alert(szsmdm_value.length +" ： "+szsmdm_hd_value.length);
function doSubmitForm(operationType)
{
	if(operationType=="Save")
	{
		if (!confirm("您的操作是：“保存”。是否继续？"))
    	return false;
	}
	
	var htbhValue = document.forms[0].htbh.value;
	if(htbhValue != "")
	{
		if(operationType=="toWhsbxx")
		{
			document.forms[0].action="/qsgl/clfgl/whsbxx.do?operationType=QuerySbxx&htbh ='"+htbhValue+"'&htbh1 ='"+htbhValue+"'";
			document.forms[0].submit();
			return false;
		}
		if(operationType=="toFpdk")
		{
			document.forms[0].action="/qsgl/fpgl/fpdk.do?operationType=Query&htbh ='"+htbhValue+"'";
			document.forms[0].submit();
			return false;
		}
	}
	else
	{
		if(operationType=="toWhsbxx")
		{
			alert("请输入相应的查询条件！");
			document.forms[0].htbh.focus();
			return false;
		}
	}
	
	
    document.all.operationType.value = operationType;
    document.forms[0].submit();
}
</script>
<script language=JavaScript >
var CLFJKMX_list=new DTable(CLFJKMX,jsArr_CLFJKMX);
CLFJKMX_list.tableTail=1;

//The conditions before action.
function befAdd(type){

	if(document.all.fwhdlxdm.value==""||document.all.fwhdlxdm.value==null){
		alert("房屋核定类型为空，请执行查询操作！");
		return false;
	}
	//获得建委查询房屋原值
	if (!checkFwyz(type))
	{
		return false;
	}
	tableid="CLFJKMX";
	var rows = eval(tableid).rows;
	//alert(rows.length+"<>hhhh");
	if (rows.length<16){
		addRow('CLFJKMX',null,'szsmdm');
	}
}

function befDel(){
	tableid="CLFJKMX";
	var rows = eval(tableid).rows;
	//alert(rows.length+"<>hhhh");
	if (rows.length>2){
		deleteRow('CLFJKMX',null,'szsmdm')
	}
}

//调整完税证录入条数4-->5 Add By TuJunbing @@2012-7-13
function befSave(actionFlag){
	//判断个人所得税征收方式是否正确
	if (!checkgrsdszsfs())
	{
		return false;
	}
	tableid="CLFJKMX";
	var rows = eval(tableid).rows;
	//alert(rows.length+"<>hhhh");
	if (rows.length>16) {
		alert("一次录入不能多于14条信息！");
	}
	if (rows.length>2 && rows.length< 17){
		//doSubmitForm(actionFlag);
		doSubmitCheck(actionFlag);
	}
}


//当保存不成功，返回的时候，重新这是读写规则。
function whenReload(tableid){
	var rows = eval(tableid).rows;
	//alert(rows.length+"<>hhhh");
	if (rows.length>2){
		for(var ii=1;ii<rows.length-1;ii++){
			var obj = rows[ii].all("kssl");
			var def_obj = rows[ii].all("szsmdm");
			var cnt_obj = rows[ii].all("sjse");
			var obj_value = def_obj.value;
			//alert(obj_value);
			//if(obj_value!="1" && obj_value!="2"){
			/*if(obj_value.indexOf("11")!=0 && obj_value.indexOf("88")!=0){
				obj.readOnly = true;
				obj.className = "inputnoborder";
				cnt_obj.focus();
			}
			//if (obj_value=="1" || obj_value=="2"){
			else{
				obj.className = "inputalignright";
			}*/
		}	
	}
}



function fnOnLoad(){
    //document.forms[0].nsrmc.focus();
    if(szsmdm_value.length !=0)
    {
    	var szsmdm=szsmdm_value.length;
    	//alert(szsmdm);
    	//alert(szsmdm_value);
    	//befAdd();;
    	//alert("CLFJKMX.rows.length:"+CLFJKMX.rows.length);
    	//系统判定首先录入营业税
    	if(CLFJKMX.rows.length > 2 )
    	{
    		var szsmTemp = CLFJKMX.rows[1].all("szsmdm").value
    		szsmTemp = szsmTemp.substr(0,2);
    		if( szsmTemp !="02")
    		{
    			alert("为确保税额计算正确性，请首先录入“营业税”税种税目！");
    			deleteRow1('CLFJKMX',null,'szsmdm');
    		}
    	}
    }
    
}

//检查并提交，需要检查申报日期、税务计算机代码
//纳税项目代码、税款所属日期、课税数量、计税金额、实际缴税额
function doSubmitCheck(ope){
//	tableid="sbxxHis";
//	var sbxxnum = eval(tableid).rows;
		
	//检查每一列的值是否合法
	for(var ii=1;ii<CLFJKMX.rows.length-1;ii++){
		var checkSzsmdm = "";
		var checkSkssjsrq = "";	
		if(CLFJKMX.rows[ii].all("szsmdm")) {	
			var szsmdmVal = CLFJKMX.rows[ii].all("szsmdm").value;
			if(szsmdmVal==""){
				alert("税种税目代码不可为空");	
				CLFJKMX.rows[ii].all("szsmdm").focus();
				return false;
			}
			else{
				checkSzsmdm = szsmdmVal;			
			}	
		}			
		if(CLFJKMX.rows[ii].all("skssksrq") && !isDate(CLFJKMX.rows[ii].all("skssksrq"),false)) {
			alert("税款所属起始日期不合法！");		
			return false;
		}
		if(CLFJKMX.rows[ii].all("skssjsrq") && !isDate(CLFJKMX.rows[ii].all("skssjsrq"),false)) {
			alert("税款所属截止日期不合法！");		
			return false;
		}
		//起始日期要小于截止日期
		if(compare(CLFJKMX.rows[ii].all("skssksrq"),CLFJKMX.rows[ii].all("skssjsrq"))<0) {
			alert("起始日期必须小于或等于截止日期！");
			return false;
		}
		
		//2007年城镇土地使用税新旧税率衔接
		//背景：2007-1-1日起城镇土地使用税税额调整，2007-1-1日后采用新税额，之前采用旧税额
		if(CLFJKMX.rows[ii].all("szsmdm")&&CLFJKMX.rows[ii].all("szsmdm").value.substr(0,2)=="15"){
			if(CLFJKMX.rows[ii].all("skssksrq")&&CLFJKMX.rows[ii].all("skssjsrq")){
				var ksnd=CLFJKMX.rows[ii].all("skssksrq").value.substr(0,4);
				var jsnd=CLFJKMX.rows[ii].all("skssjsrq").value.substr(0,4);
				if(ksnd<=2006&&jsnd>2006){
					alert("2007年1月1日起城镇土地使用税税额调整为原来的三倍，请将税款所属期拆分为：\n"+
					CLFJKMX.rows[ii].all("skssksrq").value+"-20061231\n"+
					"20070101-"+CLFJKMX.rows[ii].all("skssjsrq").value+"\n"+
					"分两张完税证进行录入！");
					CLFJKMX.rows[ii].all("skssjsrq").value="20061231";
					CLFJKMX.rows[ii].all("skssjsrq").focus();
					return false;
				}
			}
		}
		//课税数量不为空时必须为合法的整数
		if(CLFJKMX.rows[ii].all("kssl") &&CLFJKMX.rows[ii].all("kssl").value!=null&& !isNum(CLFJKMX.rows[ii].all("kssl"),0)) {
			alert("课税数量不为空时必须为合法的整数！");		
			return false;
		}
		var obj_name=CLFJKMX.rows[ii].all("szsmdm");
		var obj_value = obj_name.value;
		if(CLFJKMX.rows[ii].all("jsje") && !isNum(CLFJKMX.rows[ii].all("jsje"),0,null,true)) 
		{
			alert("计税金额不合法！");		
			return false;
		}
		if(CLFJKMX.rows[ii].all("sjse") && !isNum(CLFJKMX.rows[ii].all("sjse"),0,null,true)) 
		{
			alert("应缴金额不合法！");		
			return false;
		}
		if(CLFJKMX.rows[ii].all("jmje") && !isNum(CLFJKMX.rows[ii].all("jmje"),0,null,true)) 
		{
			alert("减免金额不合法！");		
			return false;
		}
		if(CLFJKMX.rows[ii].all("yjje") && !isNum(CLFJKMX.rows[ii].all("yjje"),0,null,true)) 
		{
			alert("实缴金额不合法！");		
			return false;
		}

		//如果有查询到卖方上次缴纳税款信息,则如果填报个人所得税,只能选择:050801 房屋转让所得(按20%税率)
//		if(sbxxnum.length>2 && CLFJKMX.rows[ii].all("szsmdm").value.substr(0,2)=="05")
//		{
//			 if(CLFJKMX.rows[ii].all("szsmdm").value!="050801")
//			 {
//			 	alert("房屋有上次交易价格，个人所得税只能按照房屋转让所得(按20%税率)征收！");	
//			 	return false;
//			 }
//		}
	}
	//检查是否有未录入的税目，added by zhangj
	if(!checkNsxmdm()){
		return false;
	}
	doSubmitForm(ope);
}


//下拉列表使用的js数组
<ttsoft:write name="mfskzsForm" property="scriptStr" filter="false"/>

//是否现实附加税标志
isadditions = true;

function publicMethod(){
	//alert("publicmethod");
	//resetFjs();
	//计算附加税为文化事业建设费
	//resetWhjssy();
	//重新设定输入框编辑性
	//changePrope();

	computePrope();
	getYYSsYjjehj();
	computeYhs();
	recomputeTdzzs();
	getOutGrsdsYjjehj();
	recomputeGrsds();
}


//检查输入数据
function checkSjseInput(obj){	
	var obj1 = eval(obj);
	
	var oRow = getObjRow(obj);	
	//如果被修改行为附加税则不重新计算
	if(oRow.all("sffjs").value=='2'){ 
		//重新计算系统合计
		computeSameSum("jsje","hjjsje","CLFJKMX");
		computeSameSum("sjse","hjsjse","CLFJKMX");
		computeSameSum("jmje","hjjmje","CLFJKMX");
		computeSameSum("yjje","hjyjje","CLFJKMX");
		hj();
		return false;
	}

	return true;
}


hjArray[0]=new Array("jsje","hjjsje","CLFJKMX");
hjArray[1]=new Array("sjse","hjsjse","CLFJKMX");
hjArray[2]=new Array("jmje","hjjmje","CLFJKMX");
hjArray[3]=new Array("yjje","hjyjje","CLFJKMX");
//将合计项清零
function initHj(){
	document.forms[0].hjjsje.value='0.00';
	document.forms[0].hjsjse.value='0.00';
	document.forms[0].hjjmje.value='0.00';	
	document.forms[0].hjyjje.value='0.00';
}
initHj();


//计税金额变化时计算相关的附加税和实缴
function computeRow1(obj){	
	if(checkSjseInput(obj)){		
		//计算实缴
		//computeSjje(obj);
		//计算附加税非文化事业建设费
	    //resetFjs();
		//计算文化建设事业费
		//resetWhjssy();
		//重新设定输入框编辑性
		//changePrope();

		computePrope();
		getYYSsYjjehj();
		computeYhs();
		recomputeTdzzs();
		getOutGrsdsYjjehj();
		recomputeGrsds();
	}
}

//计算实缴金额
function computeSjje(obj){
	var oRow = getObjRow(obj);
	//按数量计标示
	var asljbs = oRow.all("asljbs").value;
	//计税金额
	var jsje = oRow.all("jsje").value;
	if(jsje=='')jsje=0;
	//税率
	var sl = oRow.all("sl").value;
	//计税基数
	//var jsjs = oRow.all("jsjs").value;
	//课税数量
	var kssl = oRow.all("kssl").value;
	//alert(asljbs);
	//1.当选中的税目代码(szsmdm)对应的税种税目代码表(dmdb.sb_dm_szsm)中的asljbs为空的,页面不做计算；
	if(asljbs!=''){
		 //2.选中的税目代码(szsmdm)对应的税种税目代码表(dmdb.sb_dm_szsm)中的　asljbs＝０，页面的实际缴税额＝页面的计税金额×dmdb.sb_dm_szsmdm.sl；
		if(asljbs=='0'){
			oRow.all("sjse").value=Math.round(parseFloat(jsje)*parseFloat(sl)*100)/100;
		}
		//3.选中的税目代码(szsmdm)对应的税种税目代码表(dmdb.sb_dm_szsm)中的　asljbs＝１或者＝３，页面的实际缴税额＝页面的计税金额×dmdb.sb_dm_szsmdm.jsjs；
		if(asljbs=='1' || asljbs=='3'){
	    //根据税款所属开始日期决定土地税税率 added by wen qing yu 2007-5-29
	    if(oRow.all("szsmdm").value.substr(0,2)==15){
		    if(oRow.all("skssksrq").value.substr(0,4)<2007){
					 if(oRow.all("szsmdm").value==150010)
					   { sl=10; }
					 if(oRow.all("szsmdm").value==150020)
					   { sl=8;  }
           if(oRow.all("szsmdm").value==150030)
             { sl=6;  }
				   if(oRow.all("szsmdm").value==150040)
				     { sl=4;  }
					 if(oRow.all("szsmdm").value==150050)
					   { sl=1;  }	
				   if(oRow.all("szsmdm").value==150060)
				     { sl=0.5;}
				}else{
					if(oRow.all("szsmdm").value==150010)
					   { sl=30; }
					if(oRow.all("szsmdm").value==150020)
					   { sl=24;  }
					if(oRow.all("szsmdm").value==150030)
             { sl=18;  }
					if(oRow.all("szsmdm").value==150040)
				     { sl=12;  }
					if(oRow.all("szsmdm").value==150050)
					   { sl=3;  }	
					if(oRow.all("szsmdm").value==150060)
				     { sl=1.5;}
				}
				oRow.all("sl").value=sl;
			}
			//oRow.all("sjse").value=Math.round(parseFloat(jsje)*parseFloat(jsjs)*100)/100;
			oRow.all("sjse").value=Math.round(parseFloat(jsje)*parseFloat(sl)*100)/100;
		}
		//4.选中的税目代码(szsmdm)对应的税种税目代码表(dmdb.sb_dm_szsm)中的　asljbs＝２，页面的实际缴税额＝页面的课税数量×dmdb.sb_dm_szsmdm.jsjs；
		if(asljbs=='2'){
			//oRow.all("sjse").value=Math.round(parseFloat(kssl)*parseFloat(jsjs)*100)/100; 
			oRow.all("sjse").value=Math.round(parseFloat(kssl)*parseFloat(sl)*100)/100; 
		}
	
	}
	
}

//变更输入项编辑性
function changePrope()
{
	//tableid="CLFJKMX";
	//var rows = eval(tableid).rows;
	for(var ii=1;ii<CLFJKMX.rows.length-1;ii++)
	{
		//var obj = rows[ii].all("sffjs");
		//if(!obj) continue;
		var szsmStr = CLFJKMX.rows[ii].all("szsmdm").value;
		var jsje = CLFJKMX.rows[ii].all("jsje");//计税金额
		var sjse = CLFJKMX.rows[ii].all("sjse");//应缴金额
		var jmje = CLFJKMX.rows[ii].all("jmje");//减免金额
		var yjje = CLFJKMX.rows[ii].all("yjje");//实缴金额
		//alert(szsmStr+" 计税金额："+jsje.readOnly+" 应缴金额："+sjse.readOnly+" 减免金额："+jmje.readOnly+" 实缴金额："+yjje.readOnly);
		//如果不是'080040'，则不可编辑
		if(szsmStr == '080040')
		{
			jsje.removeAttribute('readOnly');
			jsje.className = "inputalignright";
			
			sjse.removeAttribute('readOnly');
			sjse.className = "inputalignright";
			
			jmje.removeAttribute('readOnly');
			jmje.className = "inputalignright";
			
			yjje.removeAttribute('readOnly');
			yjje.className = "inputalignright";
		}
	}
	//重新计算系统合计
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
}

//计算营业税及附加税、个人所得税、土地增值税税额
function computePrope()
{
	//tableid="CLFJKMX";
	//var rows = eval(tableid).rows;
	var sbbh = document.forms[0].sbbh.value;
	
	var hdjsje = changeJE(0.00);
	var ygffpje = changeJE(0.00);
	var cjssl = changeJE(0.00);
	
	//获得核定信息
	var hdxxArr = new Array();
    if(szsmdm_hd_value.length !=0)
    {
		hdxxArr = getHdxx(szsmdm_hd_value,sbbh);

		//是否找到
		if(hdxxArr.length != 0)
		{
			//alert(tsnyArr);
			var sbbh = hdxxArr[0][0];
			var htbh = hdxxArr[0][1];					
			var yyszsfs = hdxxArr[0][2];
			hdjsje = hdxxArr[0][5];
			ygffpje = hdxxArr[0][9];
			cjssl = hdxxArr[0][15];
		}
	}
	for(var ii=1;ii<CLFJKMX.rows.length-1;ii++)
	{
		//var obj = rows[ii].all("sffjs");
		//if(!obj) continue;
		var szsmdm = CLFJKMX.rows[ii].all("szsmdm").value;
		var jsje = CLFJKMX.rows[ii].all("jsje");//计税金额
		var sjse = CLFJKMX.rows[ii].all("sjse");//应缴金额
		var jmje = CLFJKMX.rows[ii].all("jmje");//减免金额
		var yjje = CLFJKMX.rows[ii].all("yjje");//实缴金额
		var sl = CLFJKMX.rows[ii].all("sl").value; //税率
		var kssl = CLFJKMX.rows[ii].all("kssl");//课税数量
				
		//alert("sbbh:"+sbbh+" htbh："+htbh+" yyszsfs："+yyszsfs+" grsdszsfs："+grsdszsfs+" tdzzszsfs："+tdzzszsfs+" hdjsje："+hdjsje+" qdfcqsje："+qdfcqsje+" qdfcyhsje："+qdfcyhsje+" hlfy："+hlfy+" ygffpje："+ygffpje);
		//alert(sbbh);
		
		var szsmStr;
		szsmStr = szsmdm.substr(0,2);
		//营业税
		if(szsmStr=='02')
		{
			getYysInfo(yyszsfs,hdjsje,ygffpje,jsje,sjse,jmje,yjje,sl);
			//document.all("htbh").focus();
		}
		//营业税附加税
		var fjsbz = CLFJKMX.rows[ii].all("sffjs").value;
		if(fjsbz=="2" && szsmStr!='53')
		{
			countFjs(fjsbz,szsmStr,yyszsfs,CLFJKMX.rows,ii,cjssl);
		}
	}

	//重新计算系统合计
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
}

//计算个人所得税
function recomputeGrsds()
{
	//tableid="CLFJKMX";
	//var rows = eval(tableid).rows;
	var hdjsje = changeJE(0.00);
	var qdfcqsje = changeJE(0.00);
	var qdfcyhsje = changeJE(0.00);
	var hlfy = changeJE(0.00);
	var ygffpje = changeJE(0.00);
	var htzj = changeJE(0.00);
	var fwhdlxdm="";
	var sbbh = document.forms[0].sbbh.value;

	//获得核定信息
	var hdxxArr = new Array();
    if(szsmdm_hd_value.length !=0)
    {
		hdxxArr = getHdxx(szsmdm_hd_value,sbbh);

		//是否找到
		if(hdxxArr.length != 0)
		{
			//alert(tsnyArr);
			var sbbh = hdxxArr[0][0];
			var htbh = hdxxArr[0][1];					
			var grsdszsfs = hdxxArr[0][3];	
			hdjsje = hdxxArr[0][5];
			qdfcqsje = hdxxArr[0][6];
			qdfcyhsje = hdxxArr[0][7];
			hlfy = hdxxArr[0][8];
			ygffpje = hdxxArr[0][9];
			htzj = hdxxArr[0][16];
			fwhdlxdm=hdxxArr[0][17];
		}
	}
	for(var ii=1;ii<CLFJKMX.rows.length-1;ii++)
	{
		//var obj = rows[ii].all("sffjs");
		//if(!obj) continue;
		var szsmdm = CLFJKMX.rows[ii].all("szsmdm").value;
		var jsje = CLFJKMX.rows[ii].all("jsje");//计税金额
		var sjse = CLFJKMX.rows[ii].all("sjse");//应缴金额
		var jmje = CLFJKMX.rows[ii].all("jmje");//减免金额
		var yjje = CLFJKMX.rows[ii].all("yjje");//实缴金额
		var sl = CLFJKMX.rows[ii].all("sl").value; //税率
		var kssl = CLFJKMX.rows[ii].all("kssl");//课税数量
		
		var szsmStr;
		szsmStr = szsmdm.substr(0,2);
		if(szsmStr == '05')
		{
			countGrsds(szsmStr,grsdszsfs,ygffpje,hdjsje,qdfcqsje,qdfcyhsje,hlfy,jsje,yjje,sl,sjse,jmje,yjje,kssl,htzj,fwhdlxdm);
		}
	}

	//重新计算系统合计
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
	//alert(ygffpje);
	return ygffpje;
}

//计算土地增值税
function recomputeTdzzs()
{
	//tableid="CLFJKMX";
	//var rows = eval(tableid).rows;
	var hdjsje = changeJE(0.00);
	var qdfcqsje = changeJE(0.00);
	var hlfy = changeJE(0.00);
	var ygffpje = changeJE(0.00);
	var qdtdsyqzfje = changeJE(0.00);
	var jfpgjg = changeJE(0.00);
	var jgpgfy = changeJE(0.00);
	var anjjse = changeJE(0.00);
	var fwhdlxdm = "";
	
	var sbbh = document.forms[0].sbbh.value;
	var htwsqyrq = document.forms[0].htwsqyrq.value;
	var rqcs = document.forms[0].rqcs.value;
	
	//获得核定信息
	var hdxxArr = new Array();
    if(szsmdm_hd_value.length !=0)
    {
		hdxxArr = getHdxx(szsmdm_hd_value,sbbh);

		//是否找到
		if(hdxxArr.length != 0)
		{
			//alert(tsnyArr);
			var sbbh = hdxxArr[0][0];
			var htbh = hdxxArr[0][1];					
			var tdzzszsfs = hdxxArr[0][4];
			hdjsje = hdxxArr[0][5];
			qdfcqsje = hdxxArr[0][6];
			hlfy = hdxxArr[0][8];
			ygffpje = hdxxArr[0][9];
			var tdzzssbfs = hdxxArr[0][10];
			qdtdsyqzfje = hdxxArr[0][11];
			jfpgjg = hdxxArr[0][12];
			jgpgfy = hdxxArr[0][13];
			anjjse = hdxxArr[0][14];
			fwhdlxdm=hdxxArr[0][17];
		}
	}
	for(var ii=1;ii<CLFJKMX.rows.length-1;ii++)
	{
		//var obj = rows[ii].all("sffjs");
		//if(!obj) continue;
		var szsmdm = CLFJKMX.rows[ii].all("szsmdm").value;
		var jsje = CLFJKMX.rows[ii].all("jsje");//计税金额
		var sjse = CLFJKMX.rows[ii].all("sjse");//应缴金额
		var jmje = CLFJKMX.rows[ii].all("jmje");//减免金额
		var yjje = CLFJKMX.rows[ii].all("yjje");//实缴金额
		var sl = CLFJKMX.rows[ii].all("sl").value; //税率
		var kssl = CLFJKMX.rows[ii].all("kssl");//课税数量
		
		var szsmStr;
		szsmStr = szsmdm.substr(0,2);
		if(szsmStr == '08')
		{
			countTdzzs(tdzzszsfs,tdzzssbfs,ygffpje,hdjsje,qdfcqsje,qdtdsyqzfje,jfpgjg,jgpgfy,hlfy,jsje,yjje,sjse,jmje,yjje,kssl,htwsqyrq,anjjse,rqcs,fwhdlxdm);
		}
	}

	//重新计算系统合计
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
}


//营业税主税
/*BR2.2.3.4 卖方税款征收之营业税及附加税计算规则：
1．如果营业税征收方式是【免征营业税】或者【全额征收营业税】，则计税金额均为：【计税收入确认方式及金额】方式确认的金额；
2. 如果营业税征收方式是【差额正税营业税】，则计税金额均为：【计税收入确认方式及金额】方式确认的金额减【原购房发票金额】；
3．税率均为“5%”；
4．如果营业税征收方式是【免征营业税】，则应缴金额为计税金额乘以税率，减免金额为应纳税额，实缴税额为0；
5．如果营业税征收方式是【全额征收营业税】或【差额正税营业税】，则应缴税额为计税金额乘以税率，减免金额为0，实缴税额为应纳税额。
6．营业税的附加税费城建税、教育费附加、地方教育附加的计税金额均为营业税的应纳税额，其应纳税税额为计税金额乘以税率，减免税额依据营业税征收方式，
   如果营业税征收方式是【免征营业税】，则减免税额为应纳税额，实缴税额为0；
   如果营业税征收方式为【全额征收营业税】或【差额正税营业税】，则减免税额为0，实缴税额为应纳税额。
*/
function getYysInfo(yyszsfs,hdjsje,ygffpje,jsje,sjse,jmje,yjje,sl)
{
	//alert(hdjsje +" : "+jsje.value);
	
	if(hdjsje =='NaN' || hdjsje =="" || parseFloat(hdjsje) < 0)
		hdjsje = changeJE(0.00);
	if(ygffpje =='NaN' || ygffpje =="" || parseFloat(ygffpje) < 0)
		ygffpje = changeJE(0.00);
	if(sl =='NaN' || sl =="" || parseFloat(sl) < 0)
		sl = changeJE(0.00);
				
	//营业税计税金额
	if( jsje.value == "" || jsje.value == 'NaN')
	{
		if(yyszsfs == '<%=Constants.YSSZSFS_MINUS%>')
		{
			var strValue = Math.round((parseFloat(hdjsje)-parseFloat(ygffpje))*100)/100;
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
			
			jsje.value = changeJE(strValue);
		}
		else
		{
			jsje.value = changeJE(Math.round(parseFloat(hdjsje)*100)/100);
		}
	}
	//营业税应缴金额、减免金额、实缴税额
	if( (sjse.value == "" || sjse.value == 'NaN'))
	{
		if(yyszsfs == '<%=Constants.YSSZSFS_NOT%>')
		{
			if(jsje.value =='NaN' || jsje.value =="" || parseFloat(jsje.value) < 0)
				jsje.value = changeJE(0.00);
			
			var strValue = changeJE(Math.round(parseFloat(jsje.value)*parseFloat(sl)*100)/100);
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
			
			sjse.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
			jmje.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
			yjje.value = changeJE(0.00);
		}
		else
		{
			var strValue = Math.round(parseFloat(jsje.value)*parseFloat(sl)*100)/100;
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
			
			sjse.value = changeJE(strValue);
			jmje.value = changeJE(0.00);
			yjje.value = changeJE(strValue);
		}
	}
	//重新计算系统合计
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
}

//根据所有的行，计算附加税
//rowIndex标示附加税的行
function countFjs(fjsbz,szsmStr,yyszsfs,obj,rowIndex,cjssl){
	//alert("附加税行="+rowIndex);
	//得到所有行对象
	var rows = eval(obj);
	//得到该附加税的sjse
	var sjsehj = getFjsSj(obj,rowIndex);
	if(sjsehj =='NaN' || sjsehj =="" )
		sjsehj = changeJE(0.00);
	//alert("sjsehj:"+sjsehj);
	//得到该附加税的jmje
	var jmje = rows[rowIndex].all("jmje").value;
	//附加税税率
	var sl = rows[rowIndex].all("sl").value;
	if(sl =='NaN' || sl =="" )
		sl = changeJE(0.00);
	if(fjsbz=="2" && szsmStr == "10")
	{
		sl = cjssl;
	}
	//alert(sl);
	//设附加税的计税金额为主税应缴税额的合计
	rows[rowIndex].all("jsje").value = changeJE(Math.round(parseFloat(sjsehj)*100)/100);
	//设附加税的应缴税额为计税金额*税率
	rows[rowIndex].all("sjse").value = changeJE(Math.round(parseFloat(sjsehj)*parseFloat(sl)*100)/100);
	
	//得到附加税的sjse
	var sjseValue = rows[rowIndex].all("sjse").value;
	//设附加税减免金额
	if(sjseValue =='NaN' || sjseValue =="" || parseFloat(sjseValue) < 0)
		sjseValue = changeJE(0.00);
	if(yyszsfs == '<%=Constants.YSSZSFS_NOT%>')
	{
			
		rows[rowIndex].all("jmje").value = changeJE(sjseValue);
		rows[rowIndex].all("yjje").value = changeJE(0.00);
	}
	else
	{
		//alert("1");
		rows[rowIndex].all("jmje").value = changeJE(0.00);
		rows[rowIndex].all("yjje").value = changeJE(sjseValue);
	}
	//重新计算系统合计
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
}

//计税个人所得税
/*
BR2.2.3.6 卖方税款征收之个人所得税计税规则：
1．如果【原购房发票金额】金额大于零，卖方税款查询选定契税记录，或者【原购房发票金额】金额为零并且卖方税款查询未选定或者未查询到契税记录，并且【建委查询房屋原值】大于零时，则个人所得税按照【据实征收】方式计算税额；
   如果【原购房发票金额】为零，并且卖方契税税款没有查询到，并且【建委查询房屋原值】录入值为零时，则按【核定征收】方式计算税额；
2．【据实征收】方式计税金额计算规则如下：
  A．【计税收入确认方式及金额】方式确认的金额减去”房屋原值“；
     ”房屋原值”定义规则为：如果【原购房发票金额】或者和契税的计税金额，有一个不为零，则按孰高原则；
     如果两者皆为零，则为【建委查询房屋原值】；
  B．在A的基础上减去【取得房地产时所缴纳的契税金额】；
  C．在B的基础上减去【取得房地产时所缴纳的印花税金额】；
  D．在C的基础上减去【合理费用】；
  E．在D的基础上减去本次申报中除个人所得税外其他所有税费的实缴税额后的结果为计税金额；
3．【据实征收】方式申报的税率为20%。
4．【核定征收】方式计税金额为【计税收入确认方式及金额】方式确认的金额。
5．【核定征收】方式申报的税率为1%。
6．个人所得税应纳税额为计税金额乘以税率；
7．如果个人所得税征收方式为【免征个人所得税】，则不计算个人所得税应纳税额；
8．如果个人所得税征收方式为【征收个人所得税】，则减免金额为零，实缴金额为应纳税额。
*/
/*
BR2.2.3.6.2“非住房类”卖方税款征收之个人所得税计算规则
1．如果个人所得税征收方式为【免征个人所得税】，则不计算个人所得税应纳税额；
2．如果个人所得税征收方式为【征收个人所得税】，则按照如下计算规则计算个人所得税：
A．计税价格为：【计税收入确认方式及金额】
B．在A的基础上减去【原购房发票金额】； 
C．在C的基础上减去本次申报中除个人所得税外其他所有税费的实缴税额后的结果为计税金额；
3．税率为20%。
4．个人所得税应纳税额为计税金额乘以税率；
5．如果个人所得税征收方式为【征收个人所得税】，则减免金额为零，实缴金额为应纳税额。

*/
function countGrsds(szsmStr,grsdszsfs,ygffpje,hdjsje,qdfcqsje,qdfcyhsje,hlfy,jsje,yjje,sl,sjse,jmje,yjje,kssl,htzj,fwhdlxdm)
{
	var sbbh_his = document.all("sbbh_his").value; //获得申报编号
	var jwyzValue = document.forms[0].fwyz.value; //获得房屋原值
	var jsje_his = getMfscjnInfo(); //获得契税查询金额
	var otherJe = getOutGrsdsYjjehj(); //获得除个人所得税应缴金额外的实缴金额合计数

	if(jwyzValue =='NaN' || jwyzValue =="" || parseFloat(jwyzValue) < 0)
		jwyzValue = changeJE(0.00);
	if(hdjsje =='NaN' || hdjsje =="" || parseFloat(hdjsje) < 0)
		hdjsje = changeJE(0.00);
	if(ygffpje =='NaN' || ygffpje =="" || parseFloat(ygffpje) < 0)
		ygffpje = changeJE(0.00);
	if(qdfcqsje =='NaN' || qdfcqsje =="" || parseFloat(qdfcqsje) < 0)
		qdfcqsje = changeJE(0.00);
	if(qdfcyhsje =='NaN' || qdfcyhsje =="" || parseFloat(qdfcyhsje) < 0)
		qdfcyhsje = changeJE(0.00);
	if(hlfy =='NaN' || hlfy =="" || parseFloat(hlfy) < 0)
		hlfy = changeJE(0.00);	
	if(sl =='NaN' || sl =="" || parseFloat(sl) < 0)
		sl = changeJE(0.00);	
	if(yjje.value =='NaN' || yjje.value =="" || parseFloat(yjje.value) < 0)
		yjje.value = changeJE(0.00);
	if(jsje_his =='NaN' || jsje_his =="" || parseFloat(jsje_his) < 0)
		jsje_his = changeJE(0.00);
	//如果房屋核定类型为住房，则有核定征收、据实征收两种方式；如果为非住房，只有据实征收方式，但据实征收的规则与住房略有不同，added by zhangj
	if(fwhdlxdm=="0"){		
	//alert(hdjsje + " : "+ygffpje + " : "+ sbbh_his + " : "+ jsje_his + " : "+ otherJe + " ： " +jwyzValue + " : "+sl);
	//alert("ygffpje:"+ygffpje+" sbbh_his："+sbbh_his+" jwyzValue:"+jwyzValue);
	//按【核定征收】方式计算税额	“050802”
	if((changeJE(ygffpje) == "0.00") && (changeJE(jsje_his) == "0.00") && (changeJE(jwyzValue) == "0.00"))
	{	
		jsje.value = changeJE(Math.round(parseFloat(hdjsje)*100)/100);
		
		var strValue = changeJE(Math.round(parseFloat(jsje.value)*parseFloat(sl)*100)/100);
		if(parseFloat(strValue) < 0)
		{
			strValue = changeJE(0.00);	
		}
		sjse.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
		jmje.value = changeJE(0.00);
		yjje.value = changeJE(strValue);
		//alert("hdjsje:"+hdjsje+" jsje.value:"+jsje.value+" sl:"+sl+" sjse.value:"+sjse.value+" jmje.value:"+jmje.value+" yjje.value:"+yjje.value+" strValue:"+strValue);
	}
	//按【据实征收】方式计算税额 “050801”
	else
	{
		var tempJe = changeJE(0.00);
		if((changeJE(ygffpje) == "0.00") && (changeJE(jsje_his) == "0.00"))
		{
			tempJe = changeJE(parseFloat(jwyzValue));
		}
		else
		{
			if(parseFloat(ygffpje)>parseFloat(jsje_his))
			{
				tempJe = changeJE(parseFloat(ygffpje));
			}
			else
			{
				tempJe = changeJE(parseFloat(jsje_his));
			}
		}
		var strValue = changeJE(Math.round((parseFloat(hdjsje)-parseFloat(tempJe)-parseFloat(qdfcqsje)-parseFloat(qdfcyhsje)-parseFloat(hlfy)-parseFloat(otherJe))*100)/100);
		if(parseFloat(strValue) < 0)
		{
			strValue = changeJE(0.00);	
		}
		
		jsje.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
		//alert(" hdjsje:"+hdjsje + " ygffpje:"+ygffpje + " qdfcqsje:"+ qdfcqsje + " jsje_his:"+jsje_his+" qdfcyhsje:"+ qdfcyhsje + " hlfy:"+ hlfy + " otherJe:"+ otherJe+ " strValue:"+ strValue+" tempJe:"+tempJe);
		var tmpValue = changeJE(Math.round(parseFloat(jsje.value)*parseFloat(sl)*100)/100);
		if(parseFloat(tmpValue) < 0)
		{
			tmpValue = changeJE(0.00);	
		}
		
		sjse.value = changeJE(Math.round(parseFloat(tmpValue)*100)/100);
		jmje.value = changeJE(0.00);
		yjje.value = changeJE(tmpValue);
	}
	}else if(fwhdlxdm=="1"){
		//非住房据实征收方式（added by zhangj）
		//htzj为合同总价，即为网签价格，hdjsje为核定价格和网签价格较高着	
		var strValue = changeJE(Math.round((parseFloat(hdjsje)-parseFloat(qdfcqsje)-parseFloat(ygffpje)-parseFloat(qdfcyhsje)-parseFloat(hlfy)-parseFloat(otherJe))*100)/100);
		if(parseFloat(strValue) < 0)
		{
			strValue = changeJE(0.00);	
		}
		
		jsje.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
		//alert(" hdjsje:"+hdjsje + " ygffpje:"+ygffpje + " qdfcqsje:"+ qdfcqsje + " jsje_his:"+jsje_his+" qdfcyhsje:"+ qdfcyhsje + " hlfy:"+ hlfy + " otherJe:"+ otherJe+ " strValue:"+ strValue+" tempJe:"+tempJe);
		var tmpValue = changeJE(Math.round(parseFloat(jsje.value)*parseFloat(sl)*100)/100);
		if(parseFloat(tmpValue) < 0)
		{
			tmpValue = changeJE(0.00);	
		}
		
		sjse.value = changeJE(Math.round(parseFloat(tmpValue)*100)/100);
		jmje.value = changeJE(0.00);
		yjje.value = changeJE(tmpValue);
	}else{
		alert("房屋核定类型不正确");
	}
	
	//重新计算系统合计
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
	
}

////计税土地增值税
/*
1．如果【土地增值税征收方式】选择【提供购房发票征收土地增值税】，则计算规则如下：
（1）计税金额：【计税收入确认方式及金额】方式确认的金额（即“网签价格”与“核定计税价格”较高者，以下同类，不再描述）。
（2）扣除额=【原购房发票金额】加【按年加计数额】加+【取得房地产时所缴纳的契税金额】+【本次申报的营业税、城建税、教育费附加、地方教育附加和印花税的实缴税额】；
（3）土地增值额=【计税金额】减【扣除额】；
（4）适用税率是通过【土地增值额】/【扣除额】的比例获得；
对照关系如下：
A．【土地增值额】/【扣除额】之比小于等于0.5，则税率30%；
B．【土地增值额】/【扣除额】之比大于0.5并且小于等于1，则税率40%；
C．【土地增值额】/【扣除额】之比大于1并且小于等于2，则税率50%；
D．【土地增值额】/【扣除额】之比大于2，则税率60%。
（5）速算扣除系数是通过【土地增值额】的范围获得；对照关系如下:
A．【土地增值额】/【扣除额】之比小于等于0.5，则速算扣除数0%；
B． 【土地增值额】/【扣除额】之比大于0.5并且小于等于1，则速算扣除数5%；
C．【土地增值额】/【扣除额】之比大于1并且小于等于2，则速算扣除数15%；
D．【土地增值额】/【扣除额】之比大于2，则速算扣除数35%。
（6）土地增值税应纳税额=【土地增值额】乘以【适用税率】减【扣除额】乘以【速算扣除系数】。

2．如果【土地增值税征收方式】选择【提供评估报告征收土地增值税】，则计算规则如下；
（1）计税金额：【计税收入确认方式及金额】方式确认的金额。
（2）扣除额=【取得土地使用权所支付的金额】加【旧房及建筑物评估价格】加【价格评估费用】加【本次申报的营业税、城建税、教育费附加、地方教育附加和印花税的实缴税额】；
（3）土地增值额=【计税金额】减【扣除额】；
（4）适用税率是通过【土地增值额】/【扣除额】的比例获得；
对照关系如下：
A．【土地增值额】/【扣除额】之比小于等于0.5，则税率30%；
B．【土地增值额】/【扣除额】之比大于0.5并且小于等于1，则税率40%；
C．【土地增值额】/【扣除额】之比大于1并且小于等于2，则税率50%；
D．【土地增值额】/【扣除额】之比大于2，则税率60%。
（5）速算扣除系数是通过【土地增值额】的范围获得；对照关系如下:
A．【土地增值额】/【扣除额】之比小于等于0.5，则速算扣除数0%；
B．【土地增值额】/【扣除额】之比大于0.5并且小于等于1，则速算扣除数5%；
C．【土地增值额】/【扣除额】之比大于1并且小于等于2，则速算扣除数15%；
D．【土地增值额】/【扣除额】之比大于2，则速算扣除数35%。
（6）土地增值税应纳税额=【土地增值额】乘以【适用税率】减【扣除额】乘以【速算扣除系数】。
3．如果【土地增值税征收方式】为【核定征收土地增值税】，则计税金额*税率，税率为【5%】。
4．如果【土地增值税征收方式】选择【不征收土地增值税】，则不需要计算土地增值税；
5．如果【土地增值税征收方式】选择【免征土地增值税】，不计算土地增值税，也不进行减免会计核算。

*/
function countTdzzs(tdzzszsfs,tdzzssbfs,ygffpje,hdjsje,qdfcqsje,qdtdsyqzfje,jfpgjg,jgpgfy,hlfy,jsje,yjje,sjse,jmje,yjje,kssl,htwsqyrq,anjjse,rqcs,fwhdlxdm)
{
	//获得申报编号
	var sbbh_his = document.all("sbbh_his").value;

	if(hdjsje =='NaN' || hdjsje =="" || parseFloat(hdjsje) < 0)
		hdjsje = changeJE(0.00);
	if(ygffpje =='NaN' || ygffpje =="" || parseFloat(ygffpje) < 0)
		ygffpje = changeJE(0.00);
	if(qdfcqsje =='NaN' || qdfcqsje =="" || parseFloat(qdfcqsje) < 0)
		qdfcqsje = changeJE(0.00);
	if(qdtdsyqzfje =='NaN' || qdtdsyqzfje =="" || parseFloat(qdtdsyqzfje) < 0)
		qdtdsyqzfje = changeJE(0.00);
	if(jfpgjg =='NaN' || jfpgjg =="" || parseFloat(jfpgjg) < 0)
		jfpgjg = changeJE(0.00);
	if(jgpgfy =='NaN' || jgpgfy =="" || parseFloat(jgpgfy) < 0)
		jgpgfy = changeJE(0.00);
	if(anjjse =='NaN' || anjjse =="" || parseFloat(anjjse) < 0)
		anjjse = changeJE(0.00);
	
	//获得营业税及附加税实缴金额合计数
	var yyssjJe = getYYSsYjjehj();
	var yhssjJe = getYhssjje();
	//alert(hdjsje + " : "+ygffpje + " : "+ sbbh_his + " : "+ htwsqyrq + " : "+ qdtdsyqzfje + " : "+ jfpgjg+ " : "+ jgpgfy + " : "+ htwsqyrq + " : " +yyssjJe);
	//alert(" szsmStr:"+szsmStr+" tdzzszsfs:"+tdzzszsfs+" tdzzssbfs:"+tdzzssbfs+" hdjsje:"+hdjsje+" htwsqyrq:"+htwsqyrq+" anjjse:"+anjjse)
	//alert("rqcs:"+parseFloat(rqcs)+" htwsqyrq:"+parseFloat(htwsqyrq));
	
	//住房和非住房的土地增殖税计算方式相同（modified by zhangj）
		//非住房的土地增值税征收方式(added by zhangj)
		var strValue = changeJE(0.00);
		jsje.value = changeJE(Math.round(parseFloat(hdjsje)*100)/100);
		if(tdzzszsfs=='<%=Constants.TDZZSZSFS_GFFPZS%>'){
		//1.提供购房发票征收土地增值税
			var kce = changeJE(Math.round((parseFloat(ygffpje)+parseFloat(anjjse)+parseFloat(qdfcqsje)+parseFloat(yyssjJe)+parseFloat(yhssjJe))*100)/100); //扣除额
			var tdzze = changeJE(Math.round((parseFloat(jsje.value)-parseFloat(kce))*100)/100); //土地增值额
			if(parseFloat(tdzze) < 0)
			{
				tdzze = changeJE(0.00);	
			}
			
			strValue = getTdzzsSlKcs(tdzze,kce);
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
			
			sjse.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
		}else if(tdzzszsfs=='<%=Constants.TDZZSZSFS_PGBGZS%>'){
		//2.提供评估报告征收土地增值税
			var kce = changeJE(Math.round((parseFloat(qdtdsyqzfje)+parseFloat(jfpgjg)+parseFloat(jgpgfy)+parseFloat(yyssjJe)+parseFloat(yhssjJe))*100)/100); //扣除额
			var tdzze = changeJE(Math.round((parseFloat(jsje.value)-parseFloat(kce))*100)/100); //土地增值额
			if(parseFloat(tdzze) < 0)
			{
				tdzze = changeJE(0.00);	
			}
			
			strValue = getTdzzsSlKcs(tdzze,kce);
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
			sjse.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
		
		}else if(tdzzszsfs=='<%=Constants.TDZZSZSFS_HDZS%>'){
		//3.核定征收土地增值税
			var sl = changeJE(0.01);
			if(parseFloat(htwsqyrq) >= parseFloat(rqcs))
			{
				sl = changeJE(0.05);
			}
			//alert("parseFloat(htwsqyrq)："+parseFloat(htwsqyrq)+" parseFloat(rqcs)："+parseFloat(rqcs)+" sl："+sl);
			strValue = changeJE(Math.round(parseFloat(jsje.value)*parseFloat(sl)*100)/100);
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
		
			sjse.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
			document.all.tdzzssl.value = sl;
		
		}else if(tdzzszsfs=='<%=Constants.TDZZSZSFS_FREE%>'){
		//5.免征土地增值税
			var sl = changeJE(0.01);
			if(parseFloat(htwsqyrq) >= parseFloat(rqcs))
			{
				sl = changeJE(0.05);
			}
			//alert("parseFloat(htwsqyrq)："+parseFloat(htwsqyrq)+" parseFloat(rqcs)："+parseFloat(rqcs)+" sl："+sl);
			strValue = changeJE(Math.round(parseFloat(jsje.value)*parseFloat(sl)*100)/100);
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
		
			sjse.value = changeJE(Math.round(parseFloat(strValue)*100)/100);
			document.all.tdzzssl.value = sl;
		}else{
		//4.不征收土地增值税
		}		
		//减免金额
		var jmjeTemp = getTdzssJmje(tdzzszsfs,strValue);
		if(parseFloat(jmjeTemp) < 0)
		{
			jmjeTemp = changeJE(0.00);	
		}
		
		jmje.value = changeJE(Math.round(parseFloat(jmjeTemp)*100)/100);

		//实缴税额
		var yjjeTemp = getTdzssYjje(tdzzszsfs,strValue);
		if(parseFloat(yjjeTemp) < 0)
		{
			yjjeTemp = changeJE(0.00);	
		}
		
		yjje.value = changeJE(Math.round(parseFloat(yjjeTemp)*100)/100);		
	

	//重新计算系统合计
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
}


//土地增值税减免金额
function getTdzssJmje(tdzzszsfs,strValue)
{
	var jmjeValue = changeJE(0.00);
	if(tdzzszsfs == '<%=Constants.TDZZSZSFS_FREE%>')
	{
		jmjeValue = changeJE(strValue);
	}
	if((tdzzszsfs == '<%=Constants.TDZZSZSFS_ZS%>') || (tdzzszsfs == '<%=Constants.TDZZSZSFS_GFFPZS%>')|| (tdzzszsfs == '<%=Constants.TDZZSZSFS_HDZS%>')|| (tdzzszsfs == '<%=Constants.TDZZSZSFS_PGBGZS%>') )
	{
		jmjeValue = changeJE(0.00);
	}

	return jmjeValue;
}

//土地增值税实缴金额
function getTdzssYjje(tdzzszsfs,strValue)
{
	var yjjeValue = changeJE(0.00);
	if(tdzzszsfs == '<%=Constants.TDZZSZSFS_FREE%>' )
	{
		yjjeValue = changeJE(0.00);
	}
	if((tdzzszsfs == '<%=Constants.TDZZSZSFS_ZS%>') || (tdzzszsfs == '<%=Constants.TDZZSZSFS_GFFPZS%>')|| (tdzzszsfs == '<%=Constants.TDZZSZSFS_HDZS%>')|| (tdzzszsfs == '<%=Constants.TDZZSZSFS_PGBGZS%>'))
	{
		yjjeValue = changeJE(strValue);
	}
	return yjjeValue;
}


//计算除个人所得税实缴金额外的其他税实缴金额合计数
function getOutGrsdsYjjehj()
{
	//实缴税额合计
	var yjjehj =changeJE(0.00);
	for(var ii=1;ii <CLFJKMX.rows.length-1;ii++)
	{
		var szsmStr = CLFJKMX.rows[ii].all("szsmdm").value;
		var yjje = CLFJKMX.rows[ii].all("yjje").value;//实缴金额
		if(yjje =='NaN' || yjje =="" )
			yjje = changeJE(0.00);
		szsmStr = szsmStr.substr(0,2);
		if( szsmStr != '05')
		{
			yjjehj = changeJE(parseFloat(yjjehj) + parseFloat(yjje));
		}
	}
	return yjjehj;
}

//计算营业税及附加税实缴金额合计数
function getYYSsYjjehj()
{
	//实缴税额合计
	var yysyjjehj =changeJE(0.00);
	for(var ii=1;ii <CLFJKMX.rows.length-1;ii++)
	{
		var szsmStr = CLFJKMX.rows[ii].all("szsmdm").value;
		var fjsbz = CLFJKMX.rows[ii].all("sffjs").value;
		var yjje = CLFJKMX.rows[ii].all("yjje").value;//实缴金额
		if(yjje =='NaN' || yjje =="" )
			yjje = changeJE(0.00);
		szsmStr = szsmStr.substr(0,2);
		if( (szsmStr == '02') || (fjsbz=="2" && szsmStr!='53'))
		{
			yysyjjehj = changeJE(parseFloat(yysyjjehj) + parseFloat(yjje));
		}
	}
	return yysyjjehj;
}
//印花税实缴金额合计数 ,added by zhangj
function getYhssjje(){
	//实缴税额合计
	var yhssjje =changeJE(0.00);
	for(var ii=1;ii <CLFJKMX.rows.length-1;ii++)
	{
		var szsmStr = CLFJKMX.rows[ii].all("szsmdm").value;
		var fjsbz = CLFJKMX.rows[ii].all("sffjs").value;
		var yjje = CLFJKMX.rows[ii].all("yjje").value;//实缴金额
		if(yjje =='NaN' || yjje =="" )
			yjje = changeJE(0.00);
		szsmStr = szsmStr.substr(0,2);
		if(szsmStr=='16')
		{
			yhssjje = changeJE(parseFloat(yhssjje) + parseFloat(yjje));
		}
	}
	return yhssjje;
}


//计算附加税的主税的应缴金额
function getFjsSj(obj,rowIndex){	
	//得到所有行对象
	var rows = eval(obj);
	//得到该附加税的所有主税
	var fSzsm = getFathers(rows[rowIndex].all("szsmdm").value);
	//实际缴税额合计
	var sjsehj =0;
	//如果该主税已经添加到列表，合计实际缴税额
	var sss = ","+fSzsm.toString()+",";
	for(var ii=0;ii<rows.length;ii++){
		if(rows[ii].all("szsmdm")){
			//是明细数据行
			if(sss.indexOf(","+rows[ii].all("szsmdm").value+",")>=0 && rows[ii].all("sjse").value!=''){
				//是附加税主税，并且有实缴金额
				//累加实缴税额				
				sjsehj = parseFloat(sjsehj) + parseFloat(rows[ii].all("sjse").value);
			}
		}
	}
	return sjsehj;
}


function getHdxx(arrayName,key){
  var obj = get_obj(arrayName);
  //alert(obj.length);
  var tempArr = new Array();
  for(var ii=0;ii<obj.length;ii++){
		if(obj[ii][0]==key) tempArr.push(obj[ii]);
  }
  return tempArr;
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

//建委房屋原值处理规则
////1.上次缴纳信息为空  || 缴纳计税金额为 0；
////2.原购房发票金额为 0。 
function checkFwyz(type)
{		
	var jwyzId = document.all("jwyzId");
		//added by zhangj
	setFwhdlxdm();
	if(document.all.fwhdlxdm.value!="0"){
		jwyzId.style.display="none";
		document.all.fwyz.disabled=true;
		return true;
	}else{
	
	//原过来判断逻辑
		
	//判断是否有房屋核定信息
	var hasHdxxVal = document.all.hasHdxx.value;
	
	//alert(type+" : "+hasHdxxVal);
	if(type == "Add" && hasHdxxVal == "N")
	{
		alert("请核实是否已由税务机关对其进行核定！");
		return false;
	}
	
	var sbbh = document.forms[0].sbbh.value;
	var fwyz = document.forms[0].fwyz.value;
	var scjnxxTemp = getMfscjnInfo(); //上次缴纳信息
	//获得核定信息个人所得税征收方式
	var hdxxArray = new Array();
	var ygffpjeTemp ="";
	var grsdszsfs ="";
	if(szsmdm_hd_value.length !=0)
    {
		hdxxArray = getHdxx(szsmdm_hd_value,sbbh);
		if(hdxxArray.length != 0)
		{
			grsdszsfs = hdxxArray[0][3];
			ygffpjeTemp = hdxxArray[0][9];	
		}
	}
	if(document.forms[0].htbh1.value!='')
	{
		//alert(ygffpjeTemp +" : "+scjnxxTemp + " : " +grsdszsfs);
		if((ygffpjeTemp　!=　"" && changeJE(ygffpjeTemp) == "0.00") && (changeJE(scjnxxTemp) == "0.00") && (grsdszsfs　!=　""　&&　grsdszsfs == '<%=Constants.GRSDSZSFS_ZS%>'))
		{
			if(fwyz == "")
			{
				alert("建委查询房屋原值必须输入！");
				document.forms[0].fwyz.focus();
				return false;
			}
			else
			{
				if (!FN_QSCheckNumberDigit(fwyz,2,"建委查询房屋原值"))
				{
					document.forms[0].fwyz.focus();
					return false;
				}
			}
		}
		else
		{
			jwyzId.style.display="none";
			document.all.fwyz.disabled=true;
		}
	}
	else
	{
		jwyzId.style.display="none";
		document.all.fwyz.disabled=true;
	}
	return true;
	
	}
	
}


//检测个人所得税征收方式
function checkgrsdszsfs()
{	var fwhdlxdm="";
	var sbbh_his = document.all("sbbh_his").value; //获得申报编号
	var sbbh = document.forms[0].sbbh.value;
	var ygffpjeTemp = changeJE(0.00);
	var jwyzValue = document.forms[0].fwyz.value; //获得房屋原值
	if(jwyzValue =='NaN' || jwyzValue =="")
		jwyzValue = changeJE(0.00);
		
	//获得核定信息个人所得税征收方式
	var hdxxArray = new Array();
	if(szsmdm_hd_value.length !=0)
    {
		hdxxArray = getHdxx(szsmdm_hd_value,sbbh);
		if(hdxxArray.length != 0)
		{
			ygffpjeTemp = hdxxArray[0][9];
			fwhdlxdm = hdxxArray[0][17];
		}
	}
	for(var ii=1;ii<CLFJKMX.rows.length-1;ii++)
	{	
		// modified by zhangj
		var szsmdm = CLFJKMX.rows[ii].all("szsmdm").value;
		if(fwhdlxdm==<%=Constants.FWHDLX_NONHOUSING%> && szsmdm=='050802'){
			alert("房屋核定类型为非住房，个人所得税应为【据实征收】(税目：050801)方式，请删除后重新录入正确税目。");
			return false;
		}else if(fwhdlxdm==<%=Constants.FWHDLX_HOUSING%>){

			//alert("ygffpjeTemp:"+changeJE(ygffpjeTemp)+" sbbh_his:"+sbbh_his+" jwyzValue:"+changeJE(jwyzValue)+" szsmdm:"+szsmdm);
			if((changeJE(ygffpjeTemp) == "0.00") && (sbbh_his == "" || sbbh_his == "NaN") && (changeJE(jwyzValue) == "0.00") && (szsmdm == '050801'))
			{
				alert("根据“原购房发票金额”、“卖方上次缴纳税款信息”、“建委查询房屋原值”相关值判断，个人所得税应为【核定征收】(税目：050802)方式，请删除后重新录入正确税目。");
				return false;
			}
			if(((changeJE(ygffpjeTemp) != "0.00") || (sbbh_his != "" && sbbh_his != "NaN") || (changeJE(jwyzValue) != "0.00")) && (szsmdm == '050802'))
			{
				alert("根据“原购房发票金额”、“卖方上次缴纳税款信息”、“建委查询房屋原值”相关值判断，个人所得税应为【据实征收】(税目：050801)方式，请删除后重新录入正确税目。");
				return false;
			}
		}
	}
	return true;
}


//获得契税上次缴纳信息
function getMfscjnInfo()
{
	var jsje_his = changeJE(0.00);
	<logic:iterate id="dataList" name="mfskzsForm" property="sbxxHisList" indexId="index">
	<%if (0 == index.intValue()) { %>         
      jsje_his = changeJE('<bean:write name="dataList" property="jsje_his"/>'); 
    <% }%>	
	</logic:iterate>
	return jsje_his;
}

//function  : 检查输入字符串是否由数字组成
//parameters: DigitString: String,待检查字符串的值
//			  totalLength: int, 数字的长度限制（不包括小数点）
//			  decimalLength: int，小数的长度限制
//            totalLength-decimalLength 整数部分的限制
//            isPositive 是否为非负数 true表示一定要是非负数，false表示一定要是负数，null表示任意 add by zhangp 2003-09-25
//return    : false: 字符串中包含除数字外的字符
//			  true : else
function checkNumber1(DigitString, totalLength, decimalLength,isPositive)
{
    var re = "";
    
    if(isPositive == "true" || isPositive == true)
    {//如果是非负数（值大于0）
        if(isNaN(DigitString*1) || !(DigitString*1 > 0))
            return false;
    }
    if(isPositive == "false" || isPositive == false)
    {//如果是负数
        if(isNaN(DigitString*1) || DigitString*1>=0)
            return false;
    }

    
    if (decimalLength!=null && decimalLength != 0)
    {   //小数不为空
        re = re+"\\.[\\d]{1,"+ decimalLength +"}"
    }

    if (totalLength == null)
    {   //未设定总长度
        re= "\\d*"+re;
    }
    else
    {   //设定总长度
        //当小数部分为空的情况下，就是判断数字的长度
        var intergerLength = totalLength;
        if (decimalLength!=null)
        {   //当小数部分不为空判断，整数的位数和小数的位数是否正确
            intergerLength = totalLength-decimalLength;
        }
        re="([\\d]{0,"+ intergerLength +"}"+re+"|[\\d]{0,"+intergerLength+"})";
    }
    re = new RegExp("^"+re+"$","g");

    //当字符串为空、位数不对、不能匹配成数字时
    if(re.exec(DigitString) == null)
    {
        return false;
    }
    return true;
}

function deleteRow1(tableid,cellIndex,nameHead)
{
	eval("var tempDTable="+tableid+"_list");
	//var truthBeTold = window.confirm("该操作将删除页面中一行的数据,且不能自动恢复,请确认");
	//hj();//定义在function里面 计算合计 
	//if(!truthBeTold)	return false;
	var row=tempDTable.doGetRow(tempDTable.CurrentRow);

	var obj = row.all("szsmdm_old");
	var father="";
	if(obj) father = obj.value;

	tempDTable.deleteRow(tempDTable.CurrentRow)
	if(cellIndex!=null)	tempDTable.focusAt(tempDTable.CurrentRow,cellIndex);
	if(nameHead!=null) delSonRow(nameHead,tableid,father);
	publicMethod();
	hj();//定义在function里面 计算合计 
}

//获得土地增值税适用税率和速算扣除数
function getTdzzsSlKcs(tdzze,kce)
{
	var sl = changeJE(0.00);
	var slkcs = changeJE(0.00);
	var slkcxs = changeJE(0.00);

	//增值额未超过扣除项目金额50%的部分 税率:30 速算扣除数:0;
	if(parseFloat(tdzze) <= parseFloat(kce)*0.50)
	{
		sl= changeJE(0.30);
		slkcs = changeJE(0.00);
		slkcxs = changeJE(0.00);
	}

	//增值额超过扣除项目金额50%，未超过100%的部分 税率:40 速算扣除数:扣除项目金额×5%;
	if((parseFloat(tdzze) > parseFloat(kce)*0.50) && (parseFloat(tdzze) <= parseFloat(kce)*1.00))
	{
		sl= changeJE(0.40);
		slkcs = changeJE(Math.round(parseFloat(kce)*0.050*100)/100);
		slkcxs = changeJE(0.05);
	}

	//增值额超过扣除项目金额100%，未超过200%的部分 税率:50 速算扣除数:扣除项目金额×15%;
	if((parseFloat(tdzze) > parseFloat(kce)*1.00) && (parseFloat(tdzze) <= parseFloat(kce)*2.00))
	{
		sl= changeJE(0.50);
		slkcs = changeJE(Math.round(parseFloat(kce)*0.15*100)/100);
		slkcxs = changeJE(0.15);
	}

	//增值额超过扣除项目金额200%的部分 税率:60 速算扣除数:扣除项目金额×35%;
	if(parseFloat(tdzze) > parseFloat(kce)*2.00)
	{
		sl= changeJE(0.60);
		slkcs = changeJE(Math.round(parseFloat(kce)*0.35*100)/100);
		slkcxs = changeJE(0.35);
	}
	
	document.all.tdzzssl.value = sl;
	
	//alert("sl:"+sl+" slkcxs:"+slkcxs);
	return changeJE(Math.round(((parseFloat(tdzze)*parseFloat(sl))-(parseFloat(kce)*parseFloat(slkcxs)))*100)/100);
}

//added by zhangj start
//计算印花税
function computeYhs(){
	var htzj = changeJE(0.00);
	
	var sbbh = document.forms[0].sbbh.value;
	var fwhdlxdm="";
	var yhszsfs="";
	
	//获得核定信息
	var hdxxArr = new Array();
    if(szsmdm_hd_value.length !=0)
    {
		hdxxArr = getHdxx(szsmdm_hd_value,sbbh);

		//是否找到
		if(hdxxArr.length != 0)
		{
			htzj = hdxxArr[0][16];
			fwhdlxdm=hdxxArr[0][17];
			yhszsfs=hdxxArr[0][18];
		}
	}
	for(var ii=1;ii<CLFJKMX.rows.length-1;ii++)
	{
		//var obj = rows[ii].all("sffjs");
		//if(!obj) continue;
		var szsmdm = CLFJKMX.rows[ii].all("szsmdm").value;
		var jsje = CLFJKMX.rows[ii].all("jsje");//计税金额
		var sjse = CLFJKMX.rows[ii].all("sjse");//应缴金额
		var jmje = CLFJKMX.rows[ii].all("jmje");//减免金额
		var yjje = CLFJKMX.rows[ii].all("yjje");//实缴金额
		var sl = CLFJKMX.rows[ii].all("sl").value; //税率
		var kssl = CLFJKMX.rows[ii].all("kssl");//课税数量
		
		var szsmStr;
		szsmStr = szsmdm.substr(0,2);
		if(szsmStr == '16')
		{
			jsje.value=changeJE(Math.round(parseFloat(htzj)*100)/100);
			var strValue = changeJE(Math.round(parseFloat(htzj)*parseFloat(sl)*100)/100);
			if(parseFloat(strValue) < 0)
			{
				strValue = changeJE(0.00);	
			}
			if(yhszsfs==<%=Constants.YHSZSFS_ZS%>){
				sjse.value = strValue;
				jmje.value=changeJE(0.00);	
				yjje.value=strValue;
			}else{
				sjse.value = strValue;
				jmje.value=	strValue;
				yjje.value=changeJE(0.00);				
			}

		}
	}

	//重新计算系统合计
	computeSameSum("jsje","hjjsje","CLFJKMX");
	computeSameSum("sjse","hjsjse","CLFJKMX");
	computeSameSum("jmje","hjjmje","CLFJKMX");
	computeSameSum("yjje","hjyjje","CLFJKMX");
	hj();
}

//给房屋核定类型代码赋值
	function setFwhdlxdm(){
		var fwhdlxdm="";
		var sbbh = document.forms[0].sbbh.value;
	//获得核定信息
		var hdxxArr = new Array();
    	if(szsmdm_hd_value.length !=0)
    	{
			hdxxArr = getHdxx(szsmdm_hd_value,sbbh);

		//是否找到
			if(hdxxArr.length != 0)
			{
				fwhdlxdm=hdxxArr[0][17];
				if(document.all.fwhdlxdm.value==""||document.all.fwhdlxdm.value==null){
					document.all.fwhdlxdm.value=fwhdlxdm;
				}				
			}
		}
		if(document.all.fwhdlxdm.value=="0"){
			document.all.fwhdlx[0].checked=true;
		}else if(document.all.fwhdlxdm.value==<%=Constants.FWHDLX_NONHOUSING%>){
			document.all.fwhdlx[1].checked=true;
		}else{
			document.all.fwhdlx[0].checked=false;
			document.all.fwhdlx[1].checked=false;
		}		
	}
//检查是否还有未添加的纳税项目代码
	function checkNsxmdm(){
		var htzj=0;
		var fwhdlxdm="";
		var sbbh = document.forms[0].sbbh.value;
		setFwhdlxdm();		
    	if(szsmlist.length !=0)
    	{
    		var sbbh_his = document.all("sbbh_his").value; //获得申报编号
    		var ygffpjeTemp = changeJE(0.00);
			var jwyzValue = document.forms[0].fwyz.value; //获得房屋原值
			if(jwyzValue =='NaN' || jwyzValue =="")
				jwyzValue = changeJE(0.00);
		
				//获得核定信息个人所得税征收方式
			var hdxxArray = new Array();
			if(szsmdm_hd_value.length !=0)
   			{
				hdxxArray = getHdxx(szsmdm_hd_value,sbbh);
				if(hdxxArray.length != 0)
				{
					ygffpjeTemp = hdxxArray[0][9];
					htzj=hdxxArray[0][16];
					fwhdlxdm=hdxxArray[0][17];
				}	
			}
			for(var i=0;i<szsmlist.length;i++){
				
				//if(szsmlist[i][0].substr(0,2)=="16"&&changeJE(Math.round(parseFloat(htzj)*parseFloat(szsmlist[i][12])*100)/100)<500){
				if(szsmlist[i][0].substr(0,2)=="16"){
					continue;
				}							
				if(fwhdlxdm==<%=Constants.FWHDLX_NONHOUSING%> && szsmlist[i][0]=="050802"){
					continue;
				}else if(fwhdlxdm==<%=Constants.FWHDLX_HOUSING%>){
					if((changeJE(ygffpjeTemp) == "0.00") && (sbbh_his == "" || sbbh_his == "NaN") && (changeJE(jwyzValue) == "0.00") && (szsmlist[i][0] == '050801'))
					{
						continue;
					}
					if(((changeJE(ygffpjeTemp) != "0.00") || (sbbh_his != "" && sbbh_his != "NaN") || (changeJE(jwyzValue) != "0.00")) && (szsmlist[i][0] == '050802'))
					{
						continue;
					}
				}
				
				if(hasExited(szsmlist[i][0])){
					continue;
				}else{
					alert("纳税项目代码["+szsmlist[i][0]+"]未添加！请添加后再保存！");
					return false;				
				}
			}
			return true;
		}
		return false;

	}
//检查纳税项目代码是否已存在	
	function hasExited(szsmdm){
		for(var j=1;j<CLFJKMX.rows.length-1;j++){
			if(szsmdm==CLFJKMX.rows[j].all("szsmdm").value){		
				return true;			
			}
		}
		return false;
	}
function init(){
	
if(document.all.fwhdlxdm.value=="1" && (document.all.scriptStr.value=="" ||document.all.scriptStr.value==null) ){
		doSubmitForm('QueryFzfGr');

	}
}
//added by zhangj end
</script>
</BODY></HTML>
