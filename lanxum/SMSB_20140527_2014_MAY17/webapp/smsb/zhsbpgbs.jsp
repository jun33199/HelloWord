<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>


<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>北京市地方税务局申报缴款单</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>


</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
<!-- <table  width="100%" height="61%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
   <td colspan=2> -->
<%@ include file="./include/header.jsp"%>

<!-- 评估补税 -->
<html:form action="/webapp/smsb/zhsbPgbsAction.do" method="POST" >

<html:hidden property="actionType" value="Show"/>
<html:hidden property="yhdm" />
<html:hidden property="yhmc" />
<html:hidden property="sklxmc" />

      <table width="96%" align="center" cellspacing="0" class="table-99">
                <tr>
                  <td class="1-td1"  colspan="7">纳税评估税款</td>
                </tr>
                <tr>
                  <td class="1-td2"  colspan="7"> <br>
				  	<table width="93%" cellspacing="0" class="table-99">
                      <tr class="black9">
                        <td width="11%" align="center" nowrap> <div align="right">申报日期：</div></td>
                        <td width="11%" align="center" nowrap><div align="left">
							<html:text property="sbrq"    size="10" onchange="if(isDate(this,false)) changeDateQ();"  onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                          </div></td>
                        <td width="21%" align="center" nowrap> <div align="right">税款类型：
                            &nbsp; </div></td>
                        <td width="39%" align="center" nowrap><div align="left">
                            <!-- <select size="1" name="sklxdm" onchange="clearTable('SBJKMX')">
                              <option value=1>正常税款</option>
                              <option value=4>补缴欠税</option>
                              <option value=8>三代解缴</option>
                            </select> -->

                            <!-- 评估补税 -->
                            <select disabled>
                            	<option>纳税评估税款</option>
                            </select>
                            <!--
							<ttsoft:select property="sklxdm" codekey="ZHSB_SKLX" onchange="clearTableSklx('SBJKMX')" onkeydown="if(event.keyCode==13) event.keyCode=9;" /><br>
						<ttsoft:define id="ZHSB_SKLX" codekey="ZHSB_SKLX"/>-->
                          </div></td>

                        <td width="14%" align="center" nowrap> <div align="center">金额单位：元</div></td>
                        <td width="4%" align="center" nowrap>&nbsp;</td>
                      </tr>
                    </table>
                    <br> <table width="94%" border="0" cellpadding="0" cellspacing="0">
                      <tr class="black9">
                        <td width="17%" nowrap class="2-td2-t-left">税务计算机代码</td>
                        <td width="35%" nowrap class="2-td2-t-left">
						<html:text property="jsjdm"   size="20" onchange="if(this.value!='') doSubmitForm('Query')" onKeyDown="jsjdmQuery()"/>
						</td>
                        <td width="24%" nowrap class="2-td2-t-left">单位名称</td>
                        <td width="24%" nowrap class="2-td2-t-center">
						<html:text property="nsrmc" tabIndex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/>
						</td>
                      </tr>
                      <tr>
                        <td nowrap class="2-td2-left">缴款银行名称</td>
                        <td nowrap class="2-td2-left" id="bankName">&nbsp
						<!-- <html:text property="yhmc" styleClass="inputnoborder"   size="30" readonly="true"/> -->
						</td>
                        <td nowrap class="2-td2-left">缴款银行帐号</td>
                        <td nowrap class="2-td2-center"><!-- <select name=zh>
                            <option>基本帐号</option>
                            <option>其他帐号</option>
                          </select> -->

                          <!-- 评估补税 -->
						  <bean:define id="bankList" name="zhsbPgbsActionForm" property="bankList" />
                              <html:select property="zh" onchange="setBankName(this)"  onkeydown="enterAddRow()">
                                 <html:options collection="bankList" property="zh" labelProperty="zh"/>
                              </html:select>
						  </td>
                      </tr>
                    </table>
                    <br>
				<table width="94%" border="0" cellpadding="0" cellspacing="0" height="114" id="SBJKMX" onkeydown="return dokeydown(this.id,'szsmdm')"  onmouseup='return dokeyUp(this.id)'>
				<!-- 评估补税 -->
				<ttsoft:smsbtable form="zhsbPgbsActionForm" property="dataList"  tableId="SBJKMX" hasTitle="true"/>
					  <DIV id=divSfTemp></DIV>
                      <tr>
                        <td nowrap class="2-td2-left" height="28">合计</td>
                        <td nowrap class="2-td2-left" height="28">
						<!-- <html:text property="lrhj"    size="10" onchange="isNum(this,null,null,false,null,2)" styleClass="inputalignright" />
						-->
						&nbsp;
						</td>
                        <td nowrap class="2-td2-left" height="28"> 　</td>
                        <td nowrap class="2-td2-left" height="28"> 　</td>
                        <td nowrap class="2-td2-left" height="28"> 　</td>
                        <td nowrap class="2-td2-left" height="28"> 　</td>
                        <td nowrap class="2-td2-left" height="28"> 　</td>
                        <td nowrap class="2-td2-center" height="28"> &nbsp<!-- <html:hidden property="xthj" /> --><html:text property="xthj" size="10"  styleClass="inputnoborder" /></td>
                      </tr>
                    </table>
			        <table width="94%" border="0" cellpadding="0" cellspacing="4">
                      <tr valign="bottom">
                        <td width="51%"> </td>
                        <td width="9%"><input type="image" accesskey="w" tabIndex="-1" onclick="javascript:doJkswh(); return false;"  onMouseOver="MM_swapImage('whjks1','','<%=static_contextpath%>images/whjks-w2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="维护缴款书" id="whjks1" src="<%=static_contextpath%>images/whjks-w1.jpg" width="110" height="22"></td>
                        <td width="9%"> <input type="image" accesskey="a" tabIndex="-1" onclick="javascript:if(document.forms[0].jsjdm.value=='' || document.forms[0].nsrmc.value==''){ return false;} else {addRow('SBJKMX',null,'szsmdm');return false;}"  onMouseOver="MM_swapImage('zj1','','<%=static_contextpath%>images/zj-a2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="增加" id="zj1" src="<%=static_contextpath%>images/zj-a1.jpg" width="79" height="22"></td>
                        <td width="9%" ><input type="image" accesskey="d" tabIndex="-1" onClick="javascript:deleteRow('SBJKMX',null,'szsmdm'); return false;"  onMouseOver="MM_swapImage('sc2','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="删除" id="sc2" src="<%=static_contextpath%>images/sc-d1.jpg" width="79" height="22"></td>
                        <td width="9%"><input type="image" accesskey="s" tabIndex="-1" onClick="doSubmitCheck('Save'); return false;"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="保存" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="22"></td>
                        <!-- <td width="9%"><input type="image" accesskey="p" tabIndex="-1"  onClick=""  onMouseOver="MM_swapImage('dy1','','<%=static_contextpath%>images/dy-p2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="打印" id="dy1" src="<%=static_contextpath%>images/dy-p1.jpg" width="79" height="22"></td> -->
                        <td width="15%"><input type="image" accesskey="c" tabIndex="-1"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22"></td>
                        <td width="0%">&nbsp;</td>
                      </tr>
                    </table>



      <br>
	     </td>
		</tr>
	  </table>
	  <div id=szsmdm_epodDateLayer style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" ><select id=sel onclick=selectClick("szsmdm","SBJKMX") onkeyup=selectMove("szsmdm","SBJKMX")  onfocusout=selectClick("szsmdm","SBJKMX") onkeydown="if(window.event.keyCode==13) selectClick('szsmdm','SBJKMX')"></select></div>

<INPUT TYPE="hidden" NAME="szsmdm_focus">
</html:form>


<%@ include file="./include/footer.jsp"%>
    <!-- </td>
  </tr>
</table> -->
<script language="javascript">
function change(){
	if(!document.all)
		return
	if (event.srcElement.id=="foldheader") {
		var srcIndex = event.srcElement.sourceIndex
		var nested = document.all[srcIndex+1]
		if (nested.style.display=="none") {
			nested.style.display=''
			event.srcElement.style.listStyleImage="url(<%=static_contextpath%>images/zbotton-jian2.gif)"
		}
		else {
			nested.style.display="none"
			event.srcElement.style.listStyleImage="url(<%=static_contextpath%>images/zbotton-jia2.gif)"
		}
	}
}

document.onclick=change
//响应计算机代码的回车查询
function jsjdmQuery(){
	//if(document.all.jsjdm.value!='' && window.event.keyCode==13){
	//	doSubmitForm('Query')
	//}
	if(event.keyCode==13) event.keyCode = 9;
}

//是否现实附加税标示
isadditions = true;
//isadditions = <ttsoft:write name="zhsbPgbsActionForm" property="isadditons" filter="false"/>;
//评估补税,是否外资企业
iswz=<ttsoft:write name="zhsbPgbsActionForm" property="wz" filter="false"/>;
var SBJKMX_list=new DTable(SBJKMX,jsArr_SBJKMX);
//SBJKMX_list.tableHead=1;
SBJKMX_list.tableTail=1;

//评估补税
<ttsoft:write name="zhsbPgbsActionForm" property="bankJsArray" filter="false"/>

function setBankName(obj)
{

	for (var i=0; i<bankInfo.length; i++)
	{
		if (bankInfo[i][0] == obj.value)
		{
			document.all.yhmc.value = bankInfo[i][1];
			document.all.yhdm.value = bankInfo[i][2];
			bankName.innerText = " " + bankInfo[i][1];
			break;
		}
	}
	/********如果该用户不存在银行账户的时候，将银行信息置空**********/
	/********20040209 Shi Yanfeng*********************/
	if(bankInfo.length==0){
		document.all.yhmc.value ='';
		document.all.yhdm.value ='';
	}

}
setBankName(document.all.zh);
/****如果该纳税人为非正常户，则显示提示信息****/
/****20040216 Shi Yanfeng****/
//评估补税
var nsrzt = <ttsoft:write name="zhsbPgbsActionForm" property="nsrzt" filter="false"/>;
checkNsrzt();
//评估补税,转移到申报资料使用的税款所属日期
<ttsoft:write name="zhsbPgbsActionForm" property="skssrqArr" filter="false"/>

//在跳转到申报资料之前，必须提示是否保存已经录入数据
function toSbzl(returnStr,type)
{
	if((document.forms[0].jsjdm.value!='' && document.forms[0].nsrmc.value!='' && document.forms[0].sbrq.value!='')){
		var truthBeTold = window.confirm("该操作将无法保存已录入数据,请确认");
		if( truthBeTold){
			//税款所属日期

			var ssrq =  "";
			if(type=='q'){
				//季度
				ssrq="&skssjsrq="+skssrqArr[3]+"&skssksrq="+skssrqArr[2];
			}
			if(type=='m'){
				//月份
				ssrq="&skssjsrq="+skssrqArr[1]+"&skssksrq="+skssrqArr[0];
			}
			if(type=='y'){
				//年度
				ssrq="&skssjsrq="+skssrqArr[5]+"&skssksrq="+skssrqArr[4]
			}
			if(type=='o'){
				//特殊季度型
				ssrq="&skssjsrq="+skssrqArr[7]+"&skssksrq="+skssrqArr[6]
			}

		returnStr+="&iszhsb=1&jsjdm="+document.forms[0].jsjdm.value+"&sbrq="+document.forms[0].sbrq.value+"&lrrq="+document.forms[0].sbrq.value+ssrq;
			window.location=returnStr;
		}
	}else{
		alert('请确认已经录入申报日期和税务计算机代码');
	}
}
//跳转到缴款书维护界面
function doJkswh(){
	//必须录入计算机代码
	if(document.forms[0].jsjdm.value=='' || document.forms[0].nsrmc.value=='') return false;
	document.all.actionType.value='Jkswh';
	document.forms[0].submit();
}

//检查并提交，需要检查申报日期、税务计算机代码
//纳税项目代码、税款所属日期、课税数量、计税金额、实际缴税额
function doSubmitCheck(ope){
	//必须录入计算机代码
	if(document.forms[0].jsjdm.value==''|| document.forms[0].nsrmc.value=='') return false;
	//申报日期
	if(!isDate(document.forms[0].sbrq,false)) return false;
	//没有插入明细数据
	if(SBJKMX.rows.length<=2) return false;

	//检查每一列的值是否合法
	for(var ii=0;ii<SBJKMX.rows.length;ii++){
		if(SBJKMX.rows[ii].all("szsmdm") && SBJKMX.rows[ii].all("szsmdm").value=="") {
			alert("税种税目代码不可为空");
			SBJKMX.rows[ii].all("szsmdm").focus();
			//SBJKMX.rows[ii].all("szsmdm").select();
			return false;
		}
		if(SBJKMX.rows[ii].all("skssksrq") && !isDate(SBJKMX.rows[ii].all("skssksrq"),false)) {
			//alert("税款所属起始日期不合法");
			return false;
		}
		if(SBJKMX.rows[ii].all("skssjsrq") && !isDate(SBJKMX.rows[ii].all("skssjsrq"),false)) {
			//alert("税款所属截止日期不合法");
			return false;
		}

		if(SBJKMX.rows[ii].all("skssjsrq") &&SBJKMX.rows[ii].all("skssksrq")&& parseFloat(SBJKMX.rows[ii].all("skssksrq").value) > parseFloat(SBJKMX.rows[ii].all("skssjsrq").value)){
			alert("税款所属起始日期大于税款所属截止日期");
			SBJKMX.rows[ii].all("skssksrq").focus();
			return false;
		}

		//2007年城镇土地使用税新旧税率衔接
		//背景：2007-1-1日起城镇土地使用税税额调整，2007-1-1日后采用新税额，之前采用旧税额
		if(SBJKMX.rows[ii].all("szsmdm")&&SBJKMX.rows[ii].all("szsmdm").value.substr(0,2)=="15"){
			if(SBJKMX.rows[ii].all("skssksrq")&&SBJKMX.rows[ii].all("skssjsrq")){
				var ksnd=SBJKMX.rows[ii].all("skssksrq").value.substr(0,4);
				var jsnd=SBJKMX.rows[ii].all("skssjsrq").value.substr(0,4);
				if(ksnd<=2006&&jsnd>2006){
					alert("2007年1月1日起城镇土地使用税税额调整为原来的三倍，请将税款所属期拆分为：\n"+
					SBJKMX.rows[ii].all("skssksrq").value+"-20061231\n"+
					"20070101-"+SBJKMX.rows[ii].all("skssjsrq").value+"\n"+
					"分两次进行申报录入！");
					SBJKMX.rows[ii].all("skssjsrq").value="20061231";
					SBJKMX.rows[ii].all("skssjsrq").focus();
					return false;
				}
			}
		}
		//增加了对车船税，车船使用税税款所属日期的判断，车船税
		//税款开始日期不小于2007年1月1日，车船使用税税款结束
		//日期不大于2006年12月31日
		//循环时从1开始，因为第一行是标题行  added  by yuwq  2007-09-11
		if(SBJKMX.rows[ii].all("szsmdm")&&SBJKMX.rows[ii].all("skssksrq")){
			if(SBJKMX.rows[ii].all("szsmdm").value.substr(0,2)=="09")
			 {
			  if(SBJKMX.rows[ii].all("skssksrq").value<20070101)
			  {
				alert("车船税-税款所属开始日期不小于2007年1月1日！");
				return false;
				}
			 }
			 if(SBJKMX.rows[ii].all("szsmdm").value.substr(0,2)=="11" ||SBJKMX.rows[ii].all("szsmdm").value.substr(0,2)=="88")
			 {
			  if(SBJKMX.rows[ii].all("skssjsrq").value>=20070101)
			  {
				alert("车船使用(牌照)税-税款所属结束日期不大于2006年12月31日！");
				return false;
				}
			 }
		}


		//课税数量不为空时必须为合法的整数
		if(SBJKMX.rows[ii].all("kssl")  && (SBJKMX.rows[ii].all("asljbs").value=="1" || SBJKMX.rows[ii].all("asljbs").value=="2")&& !isNum(SBJKMX.rows[ii].all("kssl"),0,null,true,null,5)) {
			//alert("课税数量不为空时必须为合法的整数");
			return false;
		}
		if(SBJKMX.rows[ii].all("jsje") && !isNum(SBJKMX.rows[ii].all("jsje"),0,null,true)) {
			//alert("计税金额不合法");
			return false;
		}
		if(SBJKMX.rows[ii].all("sjse") && !isNum(SBJKMX.rows[ii].all("sjse"),0,null,true)) {
			//alert("实际缴税额不合法");
			return false;
		}
		if(SBJKMX.rows[ii].all("sjse") && SBJKMX.rows[ii].all("sjse").value==0) {
			alert("已经取消零申报，实际缴税额应大于零");
			SBJKMX.rows[ii].all("sjse").focus();
			return false;
		}

	}
	//检查是否有重申报
	if(!checkMult()){
		alert("存在重复申报数据");
		return false;
	}

	//检查车船税的税款所属期 add by 梁龙武 2006－12－31
	/*if(!checkCar()){
		alert("根据国务院新近颁发的《中华人民共和国车船税暂行条例》的规定，原《中华人民共和国车船使用税暂行条例》、《中华人民共和国车船使用牌照税暂行条例》同时作废，暂停我市的2007年度的车船税征收工作。对于以前年度的补缴税款仍可进行缴纳，税款与滞纳金的计算截止日期设定为2006年12月31日，请修改税款所属期！！！");
		return false;
	}*/
	//if(parseFloat(document.forms[0].lrhj.value)!=parseFloat(document.forms[0].xthj.value)) {
	//	alert("录入合计金额与系统合计金额不符\n系统合计金额="+document.forms[0].xthj.value);
		//document.forms[0].lrhj.focus();
	//	document.forms[0].lrhj.select();
	//	return false;
	//}
	doSubmitForm(ope);
}
//不可以重复申报相同税种税目相同税款所属日期
function checkMult(){
	//保存每个相同税种税目相同税款所属日期的申报条数
	var count = new Array();
	//检查每一列的值是否合法
	for(var ii=0;ii<SBJKMX.rows.length;ii++){
		if(SBJKMX.rows[ii].all("szsmdm")){
			//只检查明细数据，除去表头和表尾
			//添加新的条目
			count.push(new Array(SBJKMX.rows[ii].all("szsmdm").value, SBJKMX.rows[ii].all("skssksrq").value, SBJKMX.rows[ii].all("skssjsrq").value));

		}
	}
	//检查是否有重复数据
	count.sort();
	for(var i=0;i<count.length;i++){
		for(var j=i+1; j<count.length; j++){
			if(count[i][0]==count[j][0] && count[i][1]==count[j][1] && count[i][2]==count[j][2]){
				return false;
			}
		}
	}

	return true;
}

//检查车船税的税款所属期 add by 梁龙武 2006－12－31
function checkCar(){

    //保存每个相同税种税目相同税款所属日期的申报条数
	var count = new Array();
	//检查每一列的值是否合法
	for(var ii=0;ii<SBJKMX.rows.length;ii++){
		if(SBJKMX.rows[ii].all("szsmdm")){
			//只检查明细数据，除去表头和表尾
			//添加新的条目
			count.push(new Array(SBJKMX.rows[ii].all("szsmdm").value, SBJKMX.rows[ii].all("skssksrq").value, SBJKMX.rows[ii].all("skssjsrq").value));

		}
	}
	//检查是否有重复数据
	count.sort();
	for(var i=0;i<count.length;i++){

		if((count[i][0]).substring(0,2) == '11' || (count[i][0]).substring(0,2) == '88') {
			if (count[i][1] > '20061231' || count[i][2] > '20061231') {
				return false;
			}
		}

	}

	return true;

}

//评估补税
<ttsoft:write name="zhsbPgbsActionForm" property="scriptStr" filter="false"/>
//重新计算附加税的计税金额和应缴金额
function resetFjs(){
	tableid="SBJKMX";
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
		if(obj.value=="2" && szsmStr!='53') countFjs(rows,ii);

	}
	//重新计算系统合计
	computeSameSum("sjse","xthj","SBJKMX");

	hj();
}
function publicMethod(){
	//alert("publicmethod");
	//计算附加税非文化事业建设费
	resetFjs();
	//计算附加税为文化事业建设费
	resetWhjssy()
}
//检查输入数据
function checkSjseInput(obj){
	var obj1 = eval(obj);

	var oRow = getObjRow(obj);
	//如果被修改行为附加税则不重新计算
	if(oRow.all("sffjs").value=='2'){
		//重新计算系统合计
		computeSameSum("sjse","xthj","SBJKMX");
		hj();
		return false;
	}
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
	if(sjsehj==0) return;
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
//计算文化事业建设费的计税和实缴
//文化事业建设费的计税金额为主税的计税金额
function resetWhjssy(){
	tableid="SBJKMX";
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
	computeSameSum("sjse","xthj","SBJKMX");

	hj();
}
//计算文化事业建设费的计税和实缴
function countWhjssy(obj,rowIndex){
	//得到所有行对象
	var rows = eval(obj);
	//得到该附加税的sjse
	var jsjehj = getFjsJs(obj,rowIndex);
	if(jsjehj==0) return;
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


hjArray[0]=new Array("sjse","xthj","SBJKMX");
//将合计项清零
function initHj(){
	//document.forms[0].lrhj.value='0';
	document.forms[0].xthj.value='0';
}
initHj();

/*//记录原税款类型值
var ysklx = document.forms[0].sklxdm.value;

//选择税款类型清空列表数据
function clearTableSklx(obj){
	//alert(ysklx);

	if(clearTable('SBJKMX')==false){
		document.forms[0].sklxdm.value=ysklx;
		doSubmitForm("Query");
	}else{
		ysklx= document.forms[0].sklxdm.value;
		doSubmitForm("Query");
	}
}*/

//计税金额变化时计算相关的附加税和实缴
function computeRow1(obj){
	//if(checkSjseInput(obj)){
		if(isNum(obj,0,null,false)){
		//计算实缴
		computeSjje(obj);
		//计算附加税非文化事业建设费
		var oRow = getObjRow(obj);
		if(oRow.all("sffjs").value!='2'){
			//如果修改行为附加税则不重新计算附加税
			resetFjs();
			//计算文化建设事业费
			resetWhjssy();
		}else{
			//重新计算系统合计
			computeSameSum("sjse","xthj","SBJKMX");
			hj();
		}
	}

}
//车访土税种代码
var cftszdm = ",12,89,11,88,15,76,";
//计算实缴金额
function computeSjje(obj){
	var oRow = getObjRow(obj);
	//按数量计标示
	var asljbs = oRow.all("asljbs").value;
	//计税金额
	var jsje = oRow.all("jsje").value;
	if(jsje=='') jsje=0;
	//税率
	var sl = oRow.all("sl").value;
	if(sl=='') sl=0;
	//计税基数
	var jsjs = oRow.all("jsjs").value;
	//课税数量
	var kssl = oRow.all("kssl").value;
	//税种税目代码
	var szsmdm = oRow.all("szsmdm").value;
	//20040408 Shi yanfeng
	//请注意，如果车、房产、土地税如果税款所属时期为半年，则其计算出的税额应该为全年的一半。
	var months=getBMonths(oRow.all("skssksrq").value,oRow.all("skssjsrq").value)+1;

	//if(szsmdm=='300000') alert(asljbs);
	//1.当选中的税目代码(szsmdm)对应的税种税目代码表(dmdb.sb_dm_szsm)中的asljbs为空的,页面不做计算；
	if(asljbs!='' && szsmdm!='120010' && szsmdm!='890001'){
		 //2.选中的税目代码(szsmdm)对应的税种税目代码表(dmdb.sb_dm_szsm)中的　asljbs＝０，页面的实际缴税额＝页面的计税金额×dmdb.sb_dm_szsmdm.sl；
		if(asljbs=='0'){
			oRow.all("sjse").value=Math.round(parseFloat(jsje)*parseFloat(sl)*100)/100;
		}
		//3.选中的税目代码(szsmdm)对应的税种税目代码表(dmdb.sb_dm_szsm)中的　asljbs＝１或者＝３，页面的实际缴税额＝页面的计税金额×dmdb.sb_dm_szsmdm.jsjs；
		if(asljbs=='1' || asljbs=='3'){
		//根据输入日期判断是否需要改变记税基数
		  if(oRow.all("skssksrq").value.substr(0,4)<2007&&oRow.all("szsmdm").value.substr(0,2)==15)
				 {
					 if(oRow.all("szsmdm").value==150010)
					   { jsjs=10; }
					 if(oRow.all("szsmdm").value==150020)
					   { jsjs=8;  }
           if(oRow.all("szsmdm").value==150030)
             { jsjs=6;  }
				   if(oRow.all("szsmdm").value==150040)
				     { jsjs=4;  }
					 if(oRow.all("szsmdm").value==150050)
					   {jsjs=1;  }
				   if(oRow.all("szsmdm").value==150060)
				     { jsjs=0.5;}
				   //oRow.all("sl").value=jsjs;
				}
			oRow.all("sjse").value=Math.round(parseFloat(jsje)*parseFloat(jsjs)*100)/100;
			//20040408 Shi yanfeng
			//请注意，如果车、房产、土地税如果税款所属时期为半年，则其计算出的税额应该为全年的一半。

			if(months==6 && cftszdm.indexOf(szsmdm.substr(0,2))>0){
				oRow.all("sjse").value=Math.round(oRow.all("sjse").value/2);
			}
		}
		//4.选中的税目代码(szsmdm)对应的税种税目代码表(dmdb.sb_dm_szsm)中的　asljbs＝２，页面的实际缴税额＝页面的课税数量×dmdb.sb_dm_szsmdm.jsjs；
		if(asljbs=='2'){
			oRow.all("sjse").value=Math.round(parseFloat(kssl)*parseFloat(jsjs)*100)/100;
		}

	}else if(szsmdm=='120010' || szsmdm=='890001'){
		//如果选择的是120010税目，则用（计税金额×70％）计算后，再乘以税率计算出实际缴税额；
		oRow.all("sjse").value=Math.round(parseFloat(jsje)*parseFloat(sl)*100*0.7)/100;
	}

}

//进入页面时将焦点设为计算机代码录入
// 页面进入焦点确定
function fnOnLoad()
{
    document.forms[0].jsjdm.focus();
}
//当焦点在账号列表的时候，回车新增一行
function enterAddRow(){
	if(event.keyCode==13) {
		if(document.forms[0].jsjdm.value=='' || document.forms[0].nsrmc.value==''){
			return false;
		} else {
			addRow('SBJKMX',null,'szsmdm');
			return false;
		}
	}
}
//改变申报日期的时候，重新根据计算机代码查询
function changeDateQ(){
	if(document.forms[0].jsjdm.value!='' && document.forms[0].nsrmc.value!=''){
		doSubmitForm("Query");
	}
}

//带出当前行及附加税(附加税代码不可改,不会触发此方法)
//内外资分类代码为1或2时只有当选择026,0277下的税种，并且该税种的税率不为5%时则带出535070或535610，其他情况均不带出附加税。
function setAdditions(nameHead,tableid,obj){
//	as("setAdditions");
	/*****外籍个人用来显示币种代码列表******/
	if(nameHead=="bzdm"){
		var values = get_subobj("bzlist",obj.value);
		var fields = get_obj("bzlist_fields");
		var row = getObjRow(obj);
				var oldCode = row.all("bzdm_old").value;
		//alert(row.rowIndex+" :"+obj.value+" :"+oldCode)
		//值未变时不作任何操作
		if(obj.value==oldCode) return;

	//不合法的数据使值不变（或清空：values内全部赋空值，不return，会连带清空所带附加税）
		if(!values){
			obj.value=oldCode;
		}
		setTheRow(fields,values,row);
		//焦点位置标志
		document.all(nameHead+"_focus").value = "0";
		//关闭div
	  document.all(nameHead+"_epodDateLayer").style.display = 'none';
		tempSelect.srcObj.select();
		return;
	}
	/*****外籍个人用来显示币种代码列表******/
	var values = get_subobj("szsmlist",obj.value);
	var fields = get_obj("szsmlist_fields");
	var row = getObjRow(obj);
	//原税目代码

	var oldCode = row.all("szsmdm_old").value;
		//alert(row.rowIndex+" :"+obj.value+" :"+oldCode)
		//值未变时不作任何操作
	if(obj.value==oldCode) return;

	//不合法的数据使值不变（或清空：values内全部赋空值，不return，会连带清空所带附加税）
	if(!values){
		obj.value=oldCode;
		return;
	}

	//设置当前行
	setTheRow(fields,values,row);
	//删除该税目代码
	delSzsmList(nameHead,obj.value)

	//焦点位置标志
	document.all(nameHead+"_focus").value = "0";
	//关闭div
	document.all(nameHead+"_epodDateLayer").style.display = 'none';
	tempSelect.srcObj.select();


	if(!isadditions){
		delSonRow(nameHead,tableid,oldCode);
		return;
	}
	//主税税率
	var sl = row.all("sl").value;
	//主税税种税目代码
	var szsmdm = row.all("szsmdm").value;

	//查找附加税(szsmlist_add：税种税目代码和附加税的对应关系)
	if(!iswz){
		//不是外资企业
		var list = get_subobjs("szsmlist_add",obj.value);//附加税对象
		var addCode;
		for(var ii=0;ii<list.length;ii++){
			var addCode = list[ii][1];//附加税代码
			if(findRowIndex(tableid,addCode)>=0) continue;
			var row = addSonRow(tableid);//新增一行，并返回行对象
			var values = get_subobj("szsmlist",addCode);
			setTheRow(fields,values,row);
			//delSzsmList(addCode)//maybe not needed
		}
		//本行税目代码改变时,判断是否删除之前的附加税
		delSonRow(nameHead,tableid,oldCode);
	}else if((szsmdm.substr(0,3)=='026' || szsmdm.substr(0,4)=='0277') && sl!=0.05){
		//是外资企业
		//内外资分类代码为1或2时只有当选择026,0277下的税种，并且该税种的税率不为5%时则带出535070或535610，其他情况均不带出附加税。.
		var list = get_subobjs("szsmlist_add",obj.value);//附加税对象
		var addCode;
		for(var ii=0;ii<list.length;ii++){
			var addCode = list[ii][1];//附加税代码
			//带出535070或535610
			//alert(addCode)
			if(addCode!='535070' && addCode!='535610') continue;
			if(findRowIndex(tableid,addCode)>=0) continue;
			var row = addSonRow(tableid);//新增一行，并返回行对象
			var values = get_subobj("szsmlist",addCode);
			setTheRow(fields,values,row);
			//delSzsmList(addCode)//maybe not needed
		}
		//本行税目代码改变时,判断是否删除之前的附加税
		delSonRow(nameHead,tableid,oldCode);

	}

}


//覆写'../js/smsb_common.js'中的tuchu()方法  zzb  090407
	//退出
function tuichu(){
	if(returnStr==null || returnStr==""){
		//评估补税
		returnStr="zhsbPgbsAction.do";
	}
	//如果是由综合申报进入申报资料页面，则退出到综合申报页面
	if(document.all.iszhsb && document.all.iszhsb.value=='1')
		//评估补税
		returnStr="zhsbPgbsAction.do?actionType=Show";
	window.location=returnStr;
}
</script>


</body>
</html:html>
