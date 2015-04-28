<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html>
<head>
<title>无应纳税（费）款申报 </title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad();">
 <%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/wynsksbAction.do" method="POST" >

<html:hidden property="actionType" />
<html:hidden property="swjgzzjgdm"/>
<html:hidden property="sbbh"/>
<html:hidden property="wssbyynr"/><!-- add by tangchangfu 2014-04-08 无税减免税项目 -->


      <table width="96%" align="center" cellspacing="0" class="table-99">
                <tr>
                  <td class="1-td1"  colspan="7">无应纳税（费）款申报 </td>
                </tr>

                <tr>
                  <td class="1-td2"  colspan="7">
				  <br>
				  <table cellspacing="0" class="table-99">
					<tr class="black9">
						<td align="left" nowrap>
							<div align="left">申报日期：
							<html:text size="8" maxLength="8" property="sbrq" tabIndex="1" onchange="setSkssrq(this)" onKeyDown="if(event.keyCode==13)	event.keyCode = 9;"/>
							</div>
						</td>
						<td align="center" nowrap>
							<div align="left">税款所属日期：
											 <html:text property="skssksrq" size="8" maxlength="8" tabIndex="2" onchange="isDate(this,false);" onKeyDown="if(event.keyCode==13)	event.keyCode = 9;"/>&nbsp;至&nbsp;
											 <html:text property="skssjsrq" size="8" maxlength="8" tabIndex="3" onchange="isDate(this,false);" onKeyDown="if(event.keyCode==13)	event.keyCode = 9;"/>
							</div>
						</td>
					</tr>
					</table>
				  <br>
				  <table  cellspacing="0" class="table-99">
					<tr> 
					  <td class="2-td2-t-left">计算机代码</td>
					  <td class="2-td2-t-left"> <div align="left"><html:text property="jsjdm"   size="10" maxlength="10" onchange="if(this.value!='') doSubmitForm('Query');" onKeyDown="return jsjdmQuery();"/></div></td>
					  <td class="2-td2-t-left">纳税人名称</td>
					  <td class="2-td2-t-center"> <html:text property="nsrmc" tabIndex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/></td>
					</tr>
					
					<tr> 
					  <td class="2-td2-left">主管税务机关</td>
					  <td class="2-td2-left"><html:text property="swjgzzjgmc" tabIndex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/></td>
					  <td class="2-td2-left">联系电话</td>
					  <td class="2-td2-center"><html:text property="zcdzlxdh" tabIndex="-1"  styleClass="inputnoborder"    size="30" readonly="true" style="color:#3C5564"/></td>
					</tr>
					
					<tr>
					 	<td class="2-td2-left">无税申报原因<font color="red">*</font></td>
						<td class="2-td2-center" colspan="3">
							<div align="left">
								<ttsoft:select property="wssbyydm" codekey="WSSB_YY" onchange="docheckSelected()" onkeydown="if(event.keyCode==13) event.keyCode=9;" /><br>
							</div>
						</td>

					</tr>
					<tr id="id_bz" style="display:none">
						<td class="2-td2-left">备注</td>
						<td class="2-td2-center" colspan="3">
							<div align="left">
								<html:textarea property="bz" cols="80"  rows="6"></html:textarea>
							</div>
						</td>
					</tr>
				  </table>
                    <br>
                    
					<table width="100%" border="0" cellspacing="0" class="table-99" align="center" >
						<tr>
						  <td class="2-td1-left">申报日期</td>
						  <td class="2-td1-left">税款所属开始日期</td>
						  <td class="2-td1-left">税款所属截止日期</td>
						  <td class="2-td1-left">无税申报原因</td>
						  <td class="2-td1-left">录入人</td>
						  <td class="2-td1-center">&nbsp;</td>
						</tr>
					<logic:iterate id="item" name="wynsksbActionForm" property="dataList" >
					<%
						com.ttsoft.bjtax.shenbao.model.domain.Wynsksb wynsksb = (com.ttsoft.bjtax.shenbao.model.domain.Wynsksb)item;
						String lrr = wynsksb.getLrrmc();
					if(lrr == null || "".equals(lrr)){
						lrr = wynsksb.getLrr();
						
					}
					 %>
						<tr>
						  <td class="2-td2-left">&nbsp;<%=com.ttsoft.bjtax.sfgl.common.util.SfDateUtil.getDate(wynsksb.getSbrq())%></td>
						  <td class="2-td2-left">&nbsp;<%=com.ttsoft.bjtax.sfgl.common.util.SfDateUtil.getDate(wynsksb.getSkssksrq())%></td>
						  <td class="2-td2-left">&nbsp;<%=com.ttsoft.bjtax.sfgl.common.util.SfDateUtil.getDate(wynsksb.getSkssjsrq())%></td>
						  <td class="2-td2-left">&nbsp;<%=wynsksb.getWssbyynr()%></td>
						  <td class="2-td2-left">&nbsp;<%=lrr%></td>
						  <td class="2-td2-center">&nbsp;<input type="image"  onClick="doDelete('<%=wynsksb.getSbbh()%>'); return false;"  onMouseOver="MM_swapImage('<%=wynsksb.getSbbh()%>','','<%=static_contextpath%>images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="删除" id="<%=wynsksb.getSbbh()%>" src="<%=static_contextpath%>images/shanchu1.jpg" width="79" height="22"></td>
						</tr>
					</logic:iterate>
					  </table>
					<br>
                    <table width="94%" border="0" cellpadding="0" cellspacing="4">
                      <tr valign="bottom">
                        <td width="30%"> </td>
                        <td width="40%" align="center">
						<input type="image" accesskey="s"  onClick=" doSubmitCheck('Save'); return false;"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="保存" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="21"> 
						&nbsp;&nbsp;&nbsp;
						
						<input type="image" accesskey="c"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="21">
					  </td>
                        <td width="30%"></td>
                      </tr>
                    </table>
					<table width="94%" border="0" cellpadding="0" cellspacing="0">
                <tr valign="top"></tr>
                <td colspan="7"> <ul>
                    <li id="foldheader" style="list-style-image:url('<%=static_contextpath%>images/zbotton-jia2.gif')"> 
                      申报资料录入</li>
                    <ul id="foldinglist" style="display:none">
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">1 
                        <a href="javascript:toSbzl('qysdsjbAction.do?actionType=Query','o')">企业所得税季度纳税申报表</a></li>
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">2 
                        <a href="javascript:toSbzl('qysdsnbAction.do?actionType=Query','y')">企业所得税年度申报表</a></li>
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">3 
                        <a href="javascript:toSbzl('qyjbcwzbAction.do?actionType=Query','m')">企业基本财务指标情况表</a></li>
                      <li id="foldheader" style="list-style-image:url('<%=static_contextpath%>images/zbotton-jia2.gif')">4 
                        外企营业税申报表</li>
                      <ul id="foldinglist" style="display:none">
                        <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')"><a href="javascript:toSbzl('wqyysassbAction.do?actionType=Query','q')"> 
                          按实申报</a></li>
                        <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')"><a href="javascript:toSbzl('wqyyshdzsAction.do?actionType=Query','q')"> 
                          核定征收</a></li>
                        <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')"><a href="javascript:toSbzl('wqyysjfhsAction.do?actionType=Query','q')"> 
                          经费换算</a></li>
                      </ul>
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">5 
                        <a href="javascript:toSbzl('yhsAction.do?actionType=Query','y')">印花税年度申报表</a></li>
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">6 
                        <a href="javascript:toSbzl('dsdzdkAction.do?actionType=Query','m')">代售代征代扣申报</a></li>
                      <li id="foldheader" style="list-style-image:url('<%=static_contextpath%>images/zbotton-jia2.gif')">7 
                        个人独资个人合伙个人所得税申报表</li>
                      <ul id="foldinglist" style="display:none">
                        <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')"><a href="javascript:toSbzl('czzsjdAction.do?actionType=Query','o')">查帐征收个人独资企业和合伙企业投资者个人所得税季度申报表</a></li>
                       <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">
                        <a href="javascript:toSbzl('czzsnbAction.do?actionType=Query','y')">查账征收个人独资企业和合伙企业投资者个人所得税年报</a></li>
                      
                      </ul>
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">8 
                        <a href="javascript:toSbzl('jmssbAction.do?actionType=Query')">减免税申报资料</a></li>
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">9 
                        <a href="javascript:toSbzl('gtgshsdsAction.do?actionType=Query','y')">查账征收个体工商户所得税表年度(月份)申报表</a></li>
                      <li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">10 
                        <a href="javascript:toSbzl('gtgshyysrAction.do?actionType=Query','m')">个体工商户营业申报表</a></li>
						<li style="list-style-image:url('<%=static_contextpath%>images/none.gif')">11 
                        <a href="javascript:toSbzl('sydwshttsrsbAction.do?actionType=Query','y')">企业所得税_事业单位、社会团体收入申报表</a></li>
					
						
                    </ul>
                  </ul></td>
              </table>
	     </td>
		</tr>
	  </table>


<%@ include file="./include/footer.jsp"%>
    </td>
  </tr>
</table>
</html:form>
<script language="JavaScript">
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

document.onclick=change;
function doSubmitCheck(ope){
	if (document.forms[0].nsrmc.value!=""){
		//add by tangchanhgfu 2014-04-08 无税减免税项目 如果是保存，则校验如果无申报原因选择为“其他”，则要求备注栏必须填写内容
		if(ope == "Save"){
			if(!docheckSelected()){
				return false;
			}
		}
		doSubmitForm(ope);
	}
}

//响应计算机代码的回车查询
function jsjdmQuery(){
	
//	if(event.keyCode==13) {		event.keyCode = 9;	}
	if(event.keyCode==13) {
		doSubmitForm('Query');
		return false;
	}
	return true;
}

//进入页面时将焦点设为计算机代码录入
// 页面进入焦点确定
function fnOnLoad(){
    document.forms[0].jsjdm.focus();
    docheckSelected();
    return false;
}

/****如果该纳税人为非正常户，则显示提示信息****/
/****20040216 Shi Yanfeng****/
var nsrzt = "<ttsoft:write name="wynsksbActionForm" property="nsrzt" filter="false"/>";

checkNsrzt();

function doDelete(sbbh){
	document.all.sbbh.value=sbbh;
	doSubmitForm("Delete");
}

//转移到申报资料使用的税款所属日期
<ttsoft:write name="wynsksbActionForm" property="skssrqArr" filter="false"/>

//在跳转到申报资料之前，必须提示是否保存已经录入数据
function toSbzl(returnStr,type)
{
	if((document.forms[0].jsjdm.value!='' && document.forms[0].nsrmc.value!='' && document.forms[0].sbrq.value!='')){
		//var truthBeTold = window.confirm("该操作将无法保存已录入数据,请确认");
		if( true){
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

		returnStr+="&jsjdm="+document.forms[0].jsjdm.value+"&sbrq="+document.forms[0].sbrq.value+"&lrrq="+document.forms[0].sbrq.value+ssrq;	
			window.location=returnStr;    
		}
	}else{
		alert('请确认已经录入申报日期和税务计算机代码');
	}
}
//
function setSkssrq(sbrq){
	//getStartEndDate(sbrq,document.forms[0].skssksrq,document.forms[0].skssjsrq,2);
	getSkssrqMonth(sbrq,document.forms[0].skssksrq,document.forms[0].skssjsrq);
}
function getSkssrqMonth(oInput1,oInput2,oInput3){
	if(isDate(oInput1,"v")){
		var date_start,date_end;
		var year,mon,days;

		var inputDate = oInput1.value;

		year = inputDate.substring(0,4);
		mon = inputDate.substring(4,6);
		days = inputDate.substring(6,8);

		var date2 = new Date(year,mon-1,-1);
		days = date2.getDate()+1;
		year = date2.getFullYear();
		mon = date2.getMonth()+1;

		date_start = year+""+formatMon(mon)+"01";
		date_end = year+""+formatMon(mon)+days;

		oInput2.value = date_start;
		oInput3.value = date_end;
	}
}

/**
 * add by tangchangfu 2014-04-08 无税减免税项目
 */
function docheckSelected(){
	var trObj_bz = document.all.id_bz;
	var bzobj = document.forms[0].bz;//备注信息
	var objVal = document.all.wssbyydm.value;//无税申报代码
	
	
	//如果选择的无税申报原因为“其他”，则要求必须录入备注信息
	if(objVal != "" && objVal =="99"){
		//显示备注栏
		trObj_bz.style.display='';
		if(replaceBlank(bzobj.value)==""){
			alert("您选择的无税申报原因为：其他。需填写备注说明（注：不多于100字）！");
			//焦点定位到备注栏
			bzobj.focus();
			return false;
		}else{
			if(replaceBlank(bzobj.value).length>100){
				alert("备注信息栏不能超过100个字！");
				bzobj.focus();
				return false;
			}
			bzobj.value = replaceBlank(bzobj.value);
		}
	}else{
		//选择不是”其他“，则隐藏备注栏
		trObj_bz.style.display='none';
		bzobj.value="";
	}
	return true;
}

/**
 * 用正则表达式去掉空格
 *add by tangchangfu 2014-04-08 无税减免税项目
 */
function replaceBlank(Val)
	{
	    // 用正则表达式将前后空格
	    // 用空字符串替代。
	    return Val.replace(/(^\s*)|(\s*$)/g, "");
	}
</script>


</body>
</html:html>