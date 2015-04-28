<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=gb2312" %>

<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>缴款书录入</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="fnOnLoad();whenReload('SBJKMX_GTGSH')">
<%@ include file="./include/header.jsp"%>


<html:form action="/webapp/smsb/gtgshJkslrAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="lrr" />
<html:hidden property="jsjdm" />
<html:hidden property="gjbzhydm" />
<html:hidden property="djzclxdm" />
<html:hidden property="djzclxmc" />

<link href="css/beijing.css" rel="stylesheet" type="text/css">
      <TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9' height="300">
 <tr>
    <td valign="top" height="300"> <br>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1"  colspan="2">缴款书录入</td>
        </tr>
        <tr>
          <td class="1-td2"  colspan="2"> <br>
            <table cellspacing="0" class="table-99" width="99%" height="27">
              <tr class="black9">
                <td align="center" nowrap  height="11">
        	        <div align="center">填发日期：<bean:write name="gtgshJkslrForm" property="sbrq"/> <html:hidden property="sbrq"/>&nbsp;&nbsp;
        	        </div>
                </td>
                <td align="center" nowrap  height="11">
                	<div align="right">&nbsp;征收机关名称：<bean:write name="gtgshJkslrForm" property="swjgzzjgmc"/><html:hidden property="swjgzzjgmc"/> <html:hidden property="swjgzzjgdm"/></div>
                </td>
              </tr>
            </table><br>

             <table class="table-99" border="0" cellpadding="0" cellspacing="0">
              <tr class="black9">
               <td nowrap class="2-td2-t-left">纳税人计算机代码</td>
               <td nowrap class="2-td2-t-left"><html:text property="nsrjsjdm" onKeyDown="jsjdmQuery()" onchange="doSubmitForm('Query')" /></td>
               <td nowrap class="2-td2-t-left">纳税人名称 </td>
               <td nowrap class="2-td2-t-center"><html:text property="nsrmc" styleClass="inputnoborder"  onkeydown="if(event.keyCode==13) event.keyCode=9;" size="30" readonly="true"/></td>
              </tr><!--
              <tr class="black9">
               <td nowrap class="2-td2-t-left">缴款书号</td>
               <td nowrap class="2-td2-t-left"><html:text property="jkpzh" styleClass="inputnoborder" size="30" readonly="true"/></td>
               <td nowrap class="2-td2-t-left">缴款书合计金额</td>
               <td nowrap class="2-td2-t-center"><html:text property="jkshjje" styleClass="inputnoborder" size="30" readonly="true"/></td>
              </tr>-->

             </table>
             <br>
			<table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0" id="SBJKMX_GTGSH"  onkeydown=dokeydown(this.id,'szsmdm')  onmouseup='return dokeyUp(this.id)'>
			<ttsoft:smsbtable form="gtgshJkslrForm" property="dataList" tableId="SBJKMX_GTGSH" hasTitle="true"/>
			<DIV id=divSfTemp></DIV>
              <tr>
                <td nowrap class="2-td2-left">合计</td>
                <td nowrap class="2-td2-left" colspan="6">&nbsp;</td>
                <td nowrap class="2-td2-center"><html:text property="hjsjje" onkeydown="if(event.keyCode==13) event.keyCode=9;" size="15" styleClass="inbright" /></td>
              </tr>

             </table>
               <table border="0" width="100%">
              <tr>
                <td width="27%">&nbsp;</td>
                <td width="7%"><input type="image" accesskey="a" tabIndex="-1" onclick="befAdd();return false;" style="cursor:hand" onMouseOver="MM_swapImage('zj1','','<%=static_contextpath%>images/zj-a2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="新增" id="zj1" src="<%=static_contextpath%>images/zj-a1.jpg" width="79" border="0" height="22"></td>
                <td width="8%"><input type="image" accesskey="d" tabIndex="-1" onclick="befDel();return false;" style="cursor:hand"  onMouseOver="MM_swapImage('sc1','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="删除" id="sc1" src="<%=static_contextpath%>images/sc-d1.jpg" width="79" border="0" height="22"></td>
                <td width="8%"><input type="image" accesskey="s" tabIndex="-1" onclick="befSave('Save');return false;" style="cursor:hand" onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="保存" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" border="0" height="22"></td>
                <td width="8%"><input type="image" accesskey="w" tabIndex="-1" onclick="befDismiss('Dismiss');return false;" style="cursor:hand" onMouseOver="MM_swapImage('ds1','','<%=static_contextpath%>images/whjks-w2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="撤销" id="ds1" src="<%=static_contextpath%>images/whjks-w1.jpg" width="110" border="0" height="22"></td>
                <td width="9%"><input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22"></td>
                <td width="33%">&nbsp;</td>
              </tr>
            </table>
			<br>
          </td>
        </tr>
      </table>

      <br>
    <!-- </td>
 </tr>
</table> -->
<div id=szsmdm_epodDateLayer style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" ><select id=sel onclick=selectClick("szsmdm","SBJKMX_GTGSH") onkeyup=selectMove("szsmdm","SBJKMX_GTGSH")  onfocusout=selectClick("szsmdm","SBJKMX_GTGSH") onkeydown=if(window.event.keyCode==13)selectClick("szsmdm","SBJKMX_GTGSH")></select></div>

<INPUT TYPE="hidden" NAME="szsmdm_focus">
</html:form>



<%@ include file="./include/footer.jsp"%>
</td>
 </tr>
</table>

</body>
<script language=JavaScript >
var SBJKMX_GTGSH_list=new DTable(SBJKMX_GTGSH,jsArr_SBJKMX_GTGSH);
SBJKMX_GTGSH_list.tableTail=1;
function jsjdmQuery(){
	//if(window.event.keyCode==13){
		//doSubmitForm('Query')
	//}
	if(event.keyCode==13) event.keyCode = 9;
	return false;
}

function befAdd(){
	if (document.gtgshJkslrForm.gjbzhydm.value!="" || document.gtgshJkslrForm.nsrmc.value!=""){
		tableid="SBJKMX_GTGSH";
    	var rows = eval(tableid).rows;
    	if (rows.length<6){
			addRow('SBJKMX_GTGSH',null,'szsmdm');
		}
	}
}

function befDel(){
	tableid="SBJKMX_GTGSH";
	var rows = eval(tableid).rows;
	//alert(rows.length+"<>hhhh");
	if (rows.length>2){
		deleteRow('SBJKMX_GTGSH',null,'szsmdm')
	}
}

function befDismiss(actType){
	if (document.gtgshJkslrForm.nsrjsjdm.value!=""){
		doSubmitForm(actType);
	}
}

function befSave(){
	if (document.gtgshJkslrForm.gjbzhydm.value!="" || document.gtgshJkslrForm.nsrmc.value!=""){
		tableid="SBJKMX_GTGSH";
    	var rows = eval(tableid).rows;
    	//alert(rows.length+"<>hhhh");
		if (rows.length>6) {
			alert("一次录入不能多于4条信息！");
		}
		if (rows.length>2 && rows.length< 7){
			doSubmitCheck('Save');
		}
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
			//alert("11111111");
			var obj_value = def_obj.value;
			if(obj_value.indexOf("11")!=0 && obj_value.indexOf("88")!=0){
				obj.readOnly = true;
				obj.className = "inputnoborder";
				cnt_obj.focus();
			}
			if(obj_value.indexOf("11")==0 || obj_value.indexOf("88")==0){
				obj.className = "inputalignright";
			}
		}	
	}
}

function fnOnLoad(){
    document.forms[0].nsrjsjdm.focus();
}

//检查并提交，需要检查申报日期、税务计算机代码
//纳税项目代码、税款所属日期、课税数量、计税金额、实际缴税额
function doSubmitCheck(ope){
	//必须录入计算机代码
	if(document.forms[0].nsrjsjdm.value==''|| document.forms[0].nsrmc.value=='') return false;
	//检查每一列的值是否合法
	for(var ii=1;ii<SBJKMX_GTGSH.rows.length-1;ii++){		
		if(SBJKMX_GTGSH.rows[ii].all("skssksrq") && !isDate(SBJKMX_GTGSH.rows[ii].all("skssksrq"),false)) {
			alert("税款所属起始日期不合法！");		
			return false;
		}
		if(SBJKMX_GTGSH.rows[ii].all("skssjsrq") && !isDate(SBJKMX_GTGSH.rows[ii].all("skssjsrq"),false)) {
			alert("税款所属截止日期不合法！");		
			return false;
		}
		//起始日期要小于截止日期
		if(compare(SBJKMX_GTGSH.rows[ii].all("skssksrq"),SBJKMX_GTGSH.rows[ii].all("skssjsrq"))<0) {
			alert("起始日期必须小于或等于截止日期！");
			return false;
		}
		//课税数量不为空时必须为合法的整数
		if(SBJKMX_GTGSH.rows[ii].all("kssl") &&SBJKMX_GTGSH.rows[ii].all("kssl").value!=null&& !isNum(SBJKMX_GTGSH.rows[ii].all("kssl"),0)) {
			alert("课税数量不为空时必须为合法的整数！");		
			return false;
		}
		//if(SBJKMX_GTGSH.rows[ii].all("jsje") && !isNum(SBJKMX_GTGSH.rows[ii].all("jsje"),0,null,true)) {
			//alert("计税金额不合法！");		
			//return false;
		//}
		//if(SBJKMX_GTGSH.rows[ii].all("sjse") && !isNum(SBJKMX_GTGSH.rows[ii].all("sjse"),0,null,true)) {
			//alert("实际缴税额不合法！");		
			//return false;
		//}
		var obj_name=SBJKMX_GTGSH.rows[ii].all("szsmdm");
		var obj_value = obj_name.value;
		if (obj_value.indexOf("11")!=0 && obj_value.indexOf("88")!=0) {
			if(SBJKMX_GTGSH.rows[ii].all("jsje") && !isNum(SBJKMX_GTGSH.rows[ii].all("jsje"),0,null,true)) {
				alert("计税金额不合法！");		
				return false;
			}
			if(SBJKMX_GTGSH.rows[ii].all("sjse") && !isNum(SBJKMX_GTGSH.rows[ii].all("sjse"),0,null,true)) {
				alert("实际缴税额不合法！");		
				return false;
			}
		}
		//选择了车船，则课税数量必须录入
		if (obj_value.indexOf("11")==0 || obj_value.indexOf("88")==0) {
			if(SBJKMX_GTGSH.rows[ii].all("kssl") && !isNum(SBJKMX_GTGSH.rows[ii].all("kssl"),0,null,true)) {
				alert("课税数量不合法！");		
				return false;
			}
			//计税金额、实际缴税额不为空时，必须为合法数字
			if(SBJKMX_GTGSH.rows[ii].all("jsje") && SBJKMX_GTGSH.rows[ii].all("jsje").value!=null && !isNum(SBJKMX_GTGSH.rows[ii].all("jsje"),0)) {
				alert("计税金额不为空时必须为合法的数字！");		
				return false;
			}
			if(SBJKMX_GTGSH.rows[ii].all("sjse") && SBJKMX_GTGSH.rows[ii].all("sjse").value!=null && !isNum(SBJKMX_GTGSH.rows[ii].all("sjse"),0)) {
				alert("实际缴税额不为空时必须为合法的数字！");		
				return false;
			}
		}
	}
	
	doSubmitForm(ope);
}

//下拉列表使用的js数组
<ttsoft:write name="gtgshJkslrForm" property="scriptStr" filter="false"/>
//是否现实附加税标志
isadditions = true;


//重新计算附加税的计税金额和应缴金额
function resetFjs(){	
	tableid="SBJKMX_GTGSH";
    var rows = eval(tableid).rows;
	for(var ii=0;ii<rows.length;ii++){
		//察看每一行的附加税标志，如果为2是附加税，重新计算附加税
		var obj = rows[ii].all("sffjs");
		if(!obj) continue;
		//alert("sffjs="+obj.value);
		//如果是文化事业建设费需要根据计税金额单独计算，而不是通过主税的实缴计算
		var szsmStr = rows[ii].all("szsmdm").value;
		//取前两位，如果是'53'则为文化事业建设费
		szsmStr = szsmStr.substr(0,2);
		if(obj.value=="2" && szsmStr!='53') countFjs(rows,ii);

	}
	//重新计算系统合计
	computeSameSum("sjse","hjsjje","SBJKMX_GTGSH");

	hj();
}
function publicMethod(){
	//alert("publicmethod");
	resetFjs();
	//计算附加税为文化事业建设费
	resetWhjssy()
}
//检查输入数据
function checkSjseInput(obj){	
	var obj1 = eval(obj);
	//alert(obj1.value + isNum(obj1.value,0));
	//if(!isNum(obj1.value,0)){
	if(!isNum(obj1,0,null,false)){
		//obj1.value='0';
		return false;
	}
	return true;
}
//根据所有的行，计算附加税
//rowIndex标示附加税的行
function countFjs(obj,rowIndex){
	//alert("附加税行="+rowIndex);
	//得到所有行对象
	var rows = eval(obj);
	//得到该附加税的sjse
	var sjsehj = getFjsSj(obj,rowIndex);	
	//附加税税率
	var sl = rows[rowIndex].all("sl").value;
	//设附加税的计税金额为主税实缴税额的合计
	rows[rowIndex].all("jsje").value = Math.round(parseFloat(sjsehj)*100)/100;
	//设附加税的实缴税额为计税金额*主税实缴税额合计
	//使用Math.round来保证小数点后只有两位小数
	rows[rowIndex].all("sjse").value = Math.round(parseFloat(sjsehj)*parseFloat(sl)*100)/100;
	
}
//计算附加税的主税的实缴合计
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

hjArray[0]=new Array("sjse","hjsjje","SBJKMX_GTGSH");
//将合计项清零
function initHj(){	
	document.forms[0].hjsjje.value='0';
}
initHj();

//计算文化事业建设费的计税和实缴
//文化事业建设费的计税金额为主税的计税金额
function resetWhjssy(){
	tableid="SBJKMX_GTGSH";
    var rows = eval(tableid).rows;
	for(var ii=0;ii<rows.length;ii++){
		//察看每一行的附加税标志，如果为2是附加税，重新计算附加税
		var obj = rows[ii].all("sffjs");
		if(!obj) continue;
		//alert("sffjs="+obj.value);
		//if(obj.value=="2") countFjs(rows,ii);
		//如果是文化事业建设费需要根据计税金额单独计算，而不是通过主税的实缴计算
		var szsmStr = rows[ii].all("szsmdm").value;
		//取前两位，如果是'53'则为文化事业建设费
		szsmStr = szsmStr.substr(0,2);
		if(obj.value=="2" && szsmStr=='53') countWhjssy(rows,ii);

	}
	//重新计算系统合计
	computeSameSum("sjse","hjsjje","SBJKMX_GTGSH");

	hj();
}
//计算文化事业建设费的计税和实缴
function countWhjssy(obj,rowIndex){
	//得到所有行对象
	var rows = eval(obj);
	//得到该附加税的sjse
	var jsjehj = getFjsJs(obj,rowIndex);	
	//附加税税率
	var sl = rows[rowIndex].all("sl").value;
	//设附加税的计税金额为主税实缴税额的合计
	rows[rowIndex].all("jsje").value = Math.round(parseFloat(jsjehj)*100)/100;
	//设附加税的实缴税额为计税金额*主税实缴税额合计
	//使用Math.round来保证小数点后只有两位小数
	rows[rowIndex].all("sjse").value = Math.round(parseFloat(jsjehj)*parseFloat(sl)*100)/100;
}
//计算附加税的主税的计税金额合计
function getFjsJs(obj,rowIndex){	
	//得到所有行对象
	var rows = eval(obj);
	//得到该附加税的所有主税
	var fSzsm = getFathers(rows[rowIndex].all("szsmdm").value);
	//计税合计
	var jsjehj =0;
	//如果该主税已经添加到列表，合计计税额
	var sss = ","+fSzsm.toString()+",";
	for(var ii=0;ii<rows.length;ii++){
		if(rows[ii].all("szsmdm")){
			//是明细数据行
			if(sss.indexOf(","+rows[ii].all("szsmdm").value+",")>=0 && rows[ii].all("jsje").value!=''){
				//是附加税主税，并且有计税金额
				//累加计税税额				
				jsjehj = parseFloat(jsjehj) + parseFloat(rows[ii].all("jsje").value);
			}
		}
	}	
	return jsjehj;
}


//计税金额变化时计算相关的附加税和实缴
function computeRow1(obj){	
	if(checkSjseInput(obj)){		
		//计算实缴
		computeSjje(obj);
		//计算附加税非文化事业建设费
	    resetFjs();
		//计算文化建设事业费
		resetWhjssy();
	}

}

//计算实缴金额
function computeSjje(obj){
	var oRow = getObjRow(obj);
	//按数量计标示
	var asljbs = oRow.all("asljbs").value;
	//计税金额
	var jsje = oRow.all("jsje").value;
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

</script>
</html:html>