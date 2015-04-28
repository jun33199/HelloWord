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
<title>收现缴款书录入</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="fnOnLoad();whenReload('LSSWSZMX')">
<%@ include file="./include/header.jsp"%>


<html:form action="/webapp/smsb/lszsWszlrAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="zhdm" />
<html:hidden property="pzzldm" />
<html:hidden property="lrr" />
<html:hidden property="jsjdm" />
<html:hidden property="swjgzzjgdm2" />
<html:hidden property="gjbzhydm" />
<html:hidden property="djzclxdm" />
<html:hidden property="djzclxmc" />
<html:hidden property="dz" />
<html:hidden property="wszh"/>

<link href="css/beijing.css" rel="stylesheet" type="text/css">
      <TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9' height="300">
 <tr>
    <td valign="top" height="300"> <br>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1"  colspan="2">收现缴款书录入</td>
        </tr>
        <tr>
          <td class="1-td2"  colspan="2"> <br>
            <table cellspacing="0" class="table-99" width="99%" height="27">
              <!--<tr class="black9">
                     <td colspan="2" align="center" nowrap  height="12">
                	完税证号：<bean:write name="lszsWszlrForm" property="wszh"/></td>
              </tr>-->
              <tr class="black9">
                <td align="center" nowrap  height="11">
        	        <div align="center">填发日期：<bean:write name="lszsWszlrForm" property="lrrq"/> <html:hidden property="lrrq"/>&nbsp;&nbsp;
        	        </div>
                </td>
                <td align="center" nowrap  height="11">
                	<div align="right">&nbsp;征收机关名称：<bean:write name="lszsWszlrForm" property="swjgzzjgmc"/><html:hidden property="swjgzzjgmc"/> <html:hidden property="swjgzzjgdm"/></div>
                </td>
              </tr>
            </table><br>

             <table class="table-99" border="0" cellpadding="0" cellspacing="0">
              <tr class="black9">
              <td nowrap class="2-td2-t-left">　纳税人名称</td>
              <td nowrap class="2-td2-t-left"><div align="left">&nbsp;<html:text property="nsrmc" onkeydown="if(event.keyCode==13) event.keyCode=9;" /></div></td>
               <td nowrap class="2-td2-t-left">税款类型</td>
               <td nowrap class="2-td2-t-left">
               		<div align="left">&nbsp;
               			<ttsoft:select property="sklxdm" codekey="LSSB_SKLX" onkeydown="if(event.keyCode==13) event.keyCode=9;" /><br>
						<ttsoft:define id="LSSB_SKLX" codekey="LSSB_SKLX"/>
                    </div></td>
              </tr>
              <tr>
                  <td nowrap class="2-td2-left">证件类型</td>
                  <td nowrap class="2-td2-left"><div align="left">&nbsp;
					<ttsoft:select property="zjlxdm" codekey="ZJLX" />
					</div>
                  </td>
                  <td nowrap class="2-td2-left">证件号码</td>
                <td nowrap class="2-td2-center"><div align="left">&nbsp;<html:text property="zjhm" onkeydown="if(event.keyCode==13) event.keyCode=9;" /></div></td>
              </tr>
             </table>
             <br>
			<table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0" id="LSSWSZMX"  onkeydown=dokeydown(this.id,'szsmdm')  onmouseup='return dokeyUp(this.id)'>
			<ttsoft:smsbtable form="lszsWszlrForm" property="dataList" tableId="LSSWSZMX" hasTitle="true"/>
			<DIV id=divSfTemp></DIV>
              <tr>
                <td nowrap class="2-td2-left">合计</td>
                <td nowrap class="2-td2-left" colspan="6">&nbsp;</td>
                <td nowrap class="2-td2-center"><html:text property="hjsjje" readonly="true" onkeydown="if(event.keyCode==13) event.keyCode=9;" size="15" styleClass="inbright" /></td>
              </tr>
             </table>
               <table border="0" width="100%">
              <tr>
              	<td width="27%">&nbsp;</td>
                <td width="7%"><input type="image" accesskey="a" tabIndex="-1" onclick="befAdd();return false;" style="cursor:hand" onMouseOver="MM_swapImage('zj1','','<%=static_contextpath%>images/zj-a2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="新增" id="zj1" src="<%=static_contextpath%>images/zj-a1.jpg" width="79" border="0" height="22"></td>
                <td width="8%"><input type="image" accesskey="d" tabIndex="-1" onclick="befDel();return false;" style="cursor:hand"  onMouseOver="MM_swapImage('sc1','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="删除" id="sc1" src="<%=static_contextpath%>images/sc-d1.jpg" width="79" border="0" height="22"></td>
                <td width="8%"><input type="image" accesskey="s" tabIndex="-1" onclick="befSave('Save');return false;" style="cursor:hand" onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="保存" id="bc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" border="0" height="22"></td>
                <td width="8%"><input type="image" accesskey="x" tabIndex="-1" onclick="doSubmitForm('Dismiss');return false;" style="cursor:hand" onMouseOver="MM_swapImage('ds1','','<%=static_contextpath%>images/cx-x2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="撤销" id="ds1" src="<%=static_contextpath%>images/cx-x1.jpg" width="79" border="0" height="22"></td>
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
<div id=szsmdm_epodDateLayer style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" ><select id=sel onclick=selectClick("szsmdm","LSSWSZMX") onkeyup=selectMove("szsmdm","LSSWSZMX")  onfocusout=selectClick("szsmdm","LSSWSZMX") onkeydown=if(window.event.keyCode==13)selectClick("szsmdm","LSSWSZMX")></select></div>

<INPUT TYPE="hidden" NAME="szsmdm_focus">
</html:form>



<%@ include file="./include/footer.jsp"%>
</td>
 </tr>
</table>
</body>
<script language=JavaScript >
var LSSWSZMX_list=new DTable(LSSWSZMX,jsArr_LSSWSZMX);
LSSWSZMX_list.tableTail=1;

//The conditions before action.
function befAdd(){
	tableid="LSSWSZMX";
	var rows = eval(tableid).rows;
	//alert(rows.length+"<>hhhh");
	if (rows.length<9){
		addRow('LSSWSZMX',null,'szsmdm');
	}
}

function befDel(){
	tableid="LSSWSZMX";
	var rows = eval(tableid).rows;
	//alert(rows.length+"<>hhhh");
	if (rows.length>2){
		deleteRow('LSSWSZMX',null,'szsmdm')
	}
}

//调整完税证录入条数4-->5 Add By TuJunbing @@2012-7-13
//调整完税证录入条数5-->7 Modified By zhangyj @@2013-12-24
function befSave(actionFlag){
	tableid="LSSWSZMX";
	var rows = eval(tableid).rows;
	//alert(rows.length+"<>hhhh");
	if (rows.length>9) {
		alert("一次录入不能多于7条信息！");
	}
	if (rows.length>2 && rows.length< 10){
		//doSubmitForm(actionFlag);
		doSubmitCheck(actionFlag);
	}
}

//TSNY判断   ChangeBy Tujunbing 2012-08-30
//检查并比较提示日期、执行日期，需要税种税目代码、税款所属结束日期
function doCheck_szsm_tsny(szsmdm,skssjsrq){
	//alert("szsmdm=="+szsmdm+"==skssjsrq=="+skssjsrq);
	//获得税种税目代码对应提示信息
	var tsnyArr = new Array();
	tsnyArr = getTsny(szsmdm_ts_value,szsmdm);
	//是否找到
	if(tsnyArr.length != 0){
		//alert(tsnyArr);
		var tsny = tsnyArr[0][1];//提示内容
		var nowdate1 = tsnyArr[0][2];//系统日期						
		var tsksrq = tsnyArr[0][3];//提示开始日期
		var tsjsrq = tsnyArr[0][4];//提示结束日期	
		var zxksrq = tsnyArr[0][5];//执行开始日期
		var zxjsrq = tsnyArr[0][6];//执行结束日期
		
		//判断系统当前日期是否在提示开始日期和提示结束日期范围内，是则提示tsny中的信息
		var inRangeOfTsrq = false;
		//系统日期 >= 提示开始日期
		if(tsksrq != ""){
			//如果系统日期 >= 提示开始日期
			//alert("parseFloat(nowdate1)::"+parseFloat(nowdate1));
			//alert("parseFloat(tsksrq)::"+parseFloat(tsksrq));
			//alert("系统日期 >= 提示开始日期::"+(parseFloat(nowdate1) >= parseFloat(tsksrq)));
			if(parseFloat(nowdate1) >= parseFloat(tsksrq)){
				inRangeOfTsrq = true;
			}			
		}
		//系统日期 <= 提示结束日期
		if(tsjsrq != ""){
			//如果系统日期 <= 提示结束日期
			//alert("系统日期 <= 提示结束日期::"+(parseFloat(nowdate1) <= parseFloat(tsjsrq)));
			if(tsksrq != ""){
				if(parseFloat(nowdate1) >= parseFloat(tsksrq)){
					if(parseFloat(nowdate1) <= parseFloat(tsjsrq)){
						inRangeOfTsrq = true;
					}else{
						inRangeOfTsrq = false;
					}
				} 
			}
			if(tsksrq == ""){
				if(parseFloat(nowdate1) <= parseFloat(tsjsrq)){
					inRangeOfTsrq = true;
				}else{
					inRangeOfTsrq = false;
				}
			}				
		}
		
		//判断税款所属结束日期是否在执行开始日期和执行结束日期范围外，是则提示"输入的税款所属日期不在征期日期范围内！"
		var outRangeOfZxrq = false;
		//税款所属结束日期 < 执行开始日期
		if(zxksrq != ""){
			//税款所属结束日期 < 执行开始日期
			//alert("parseFloat(skssjsrq)::"+parseFloat(skssjsrq));
			//alert("parseFloat(zxksrq)::"+parseFloat(zxksrq));
			//alert("税款所属结束日期 < 执行开始日期::"+(parseFloat(skssjsrq) < parseFloat(zxksrq)));
			if(parseFloat(skssjsrq) < parseFloat(zxksrq)){
				outRangeOfZxrq = true;
			}		
		}
		//税款所属结束日期 > 执行开始日期
		if(zxjsrq != ""){
			//税款所属结束日期>执行结束日期
			//alert("(parseFloat(skssjsrq) ::"+(parseFloat(skssjsrq)));
			//alert("parseFloat(zxjsrq)::"+(parseFloat(zxjsrq)));
			//alert("税款所属结束日期>执行结束日期::"+(parseFloat(skssjsrq) > parseFloat(zxjsrq)));
			if(parseFloat(skssjsrq) > parseFloat(zxjsrq)){
				outRangeOfZxrq = true;
			}						
		}				
		//如果提示日期为空，则显示提示内容;再判断执行日期是否在规定时间范围内。
		if(tsksrq == "" && tsjsrq == ""){
			inRangeOfTsrq = true;
		}			
		//如果提示日期和执行日期都为空，则只显示提示内容
		if(tsksrq == "" && tsjsrq == "" && zxksrq == "" && zxjsrq == ""){
			inRangeOfTsrq = true;
			outRangeOfZxrq = false;
		}			
		//提示信息如下：		
		//如果当前日期在提示日期范围内，则提示
		if(inRangeOfTsrq == true){
		  //alert("---------"+tsny);
			if(tsny != '' && tsny != null && tsny !='null'){
				if(!window.confirm(tsny+'\n\n'+"是否继续？  点击“确定”继续进行申报，点击“取消”须删除该税种税目后继续申报。" )){
					return false;//不同意，返回
				}
			}
		}	
	}
}

//所属日期判断  ChangeBy Tujunbing 2012-08-30
//检查并比较提示日期、执行日期，需要税种税目代码、税款所属结束日期  
function doCheck_szsm_zxrq(szsmdm,skssjsrq){
	//alert("szsmdm=="+szsmdm+"==skssjsrq=="+skssjsrq);
	//获得税种税目代码对应提示信息
	var tsnyArr = new Array();
	tsnyArr = getTsny(szsmdm_ts_value,szsmdm);
	//是否找到
	if(tsnyArr.length != 0){
		//alert(tsnyArr);
		var tsny = tsnyArr[0][1];//提示内容
		var nowdate = tsnyArr[0][2];//系统日期						
		var tsksrq = tsnyArr[0][3];//提示开始日期
		var tsjsrq = tsnyArr[0][4];//提示结束日期	
		var zxksrq = tsnyArr[0][5];//执行开始日期
		var zxjsrq = tsnyArr[0][6];//执行结束日期
		
		//判断系统当前日期是否在提示开始日期和提示结束日期范围内，是则提示tsny中的信息
		var inRangeOfTsrq = false;
		//系统日期 >= 提示开始日期
		if(tsksrq != ""){
			//如果系统日期 >= 提示开始日期
			//alert("parseFloat(nowdate)::"+parseFloat(nowdate));
			//alert("parseFloat(tsksrq)::"+parseFloat(tsksrq));
			//alert("系统日期 >= 提示开始日期::"+(parseFloat(nowdate) >= parseFloat(tsksrq)));
			if(parseFloat(nowdate) >= parseFloat(tsksrq)){
				inRangeOfTsrq = true;
			}			
		}
		//系统日期 <= 提示结束日期
		if(tsjsrq != ""){
			//如果系统日期 <= 提示结束日期
			//alert("系统日期 <= 提示结束日期::"+(parseFloat(nowdate) <= parseFloat(tsjsrq)));
			if(tsksrq != ""){
				if(parseFloat(nowdate) >= parseFloat(tsksrq)){
					if(parseFloat(nowdate) <= parseFloat(tsjsrq)){
						inRangeOfTsrq = true;
					}else{
						inRangeOfTsrq = false;
					}
				} 
			}
			if(tsksrq == ""){
				if(parseFloat(nowdate) <= parseFloat(tsjsrq)){
						inRangeOfTsrq = true;
					}else{
						inRangeOfTsrq = false;
				}
			}				
		}
		
		//判断税款所属结束日期是否在执行开始日期和执行结束日期范围外，是则提示"输入的税款所属日期不在征期日期范围内！"
		var outRangeOfZxrq = false;
		//税款所属结束日期 < 执行开始日期
		if(zxksrq != ""){
			//税款所属结束日期 < 执行开始日期
			//alert("税款所属结束日期 < 执行开始日期::"+(parseFloat(skssjsrq) < parseFloat(zxksrq)));
			if(parseFloat(skssjsrq) < parseFloat(zxksrq)){
				outRangeOfZxrq = true;
			}		
		}
		//税款所属结束日期 > 执行开始日期
		if(zxjsrq != ""){
			//税款所属结束日期>执行结束日期
			//alert("税款所属结束日期>执行结束日期::"+(parseFloat(skssjsrq) > parseFloat(zxjsrq)));
			if(parseFloat(skssjsrq) > parseFloat(zxjsrq)){
				outRangeOfZxrq = true;
			}						
		}					
		//如果提示日期为空，则显示提示内容;再判断执行日期是否在规定时间范围内。
		if(tsksrq == "" && tsjsrq == ""){
			inRangeOfTsrq = true;
		}				
		//如果提示日期和执行日期都为空，则只显示提示内容
		if(zxksrq == "" && zxjsrq == ""){
			inRangeOfTsrq = true;
			outRangeOfZxrq = false;
		}			
		//提示信息如下：						
		//如果税款所属日期在执行日期范围外，则提示
		if(outRangeOfZxrq == true){
			alert("税种税目代码"+szsmdm+": 输入的税款所属日期不在有效日期范围内!");
			return false;//不同意，返回
		}		
	}
}

//根据关键值返回提示内容 ChangeBy Tujunbing 2012-08-30
function getTsny(arrayName,key){
  var obj_array = get_obj(arrayName);
  var tempArr = new Array();
  for(var ii=0;ii<obj_array.length;ii++){
		if(obj_array[ii][0]==key) tempArr.push(obj_array[ii]);
  }
  return tempArr;
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
			if(obj_value.indexOf("11")!=0 && obj_value.indexOf("88")!=0){
				obj.readOnly = true;
				obj.className = "inputnoborder";
				cnt_obj.focus();
			}
			//if (obj_value=="1" || obj_value=="2"){
			else{
				obj.className = "inputalignright";
			}
		}	
	}
}

function fnOnLoad(){
    document.forms[0].nsrmc.focus();
}

//检查并提交，需要检查申报日期、税务计算机代码
//纳税项目代码、税款所属日期、课税数量、计税金额、实际缴税额
function doSubmitCheck(ope){
	//ChangeBy Tujunbing 2012-08-30
	//必须录入计算机代码
	if(document.forms[0].nsrmc.value=='') {
		alert("纳税人名称不能为空！");
		document.forms[0].nsrmc.focus();
		return false;
	}
	
	//检查身份证的合法性
	if (document.forms[0].zjlxdm.value=="02"){
		if (!checkIdentityCard(document.forms[0].zjhm.value,"aa")){
			document.forms[0].zjhm.focus();
			return false;
		}
	}
	//检查其它证件号码的合法性
	if (document.forms[0].zjlxdm.value!="02" && document.forms[0].zjlxdm.value!="80"){
		//if (!isDigit(document.forms[0].zjhm.value)){
		if (document.forms[0].zjhm.value==""){
			alert("证件号码不合法！");
			document.forms[0].zjhm.focus();
			return false;
		}
	}
		
	//检查每一列的值是否合法
	for(var ii=1;ii<LSSWSZMX.rows.length-1;ii++){
		var checkSzsmdm = "";
		var checkSkssjsrq = "";	
		if(LSSWSZMX.rows[ii].all("szsmdm")) {	
			var szsmdmVal = LSSWSZMX.rows[ii].all("szsmdm").value;
			if(szsmdmVal==""){
				alert("税种税目代码不可为空");	
				LSSWSZMX.rows[ii].all("szsmdm").focus();
				return false;
			}
			else{
				checkSzsmdm = szsmdmVal;			
			}	
		}			
		if(LSSWSZMX.rows[ii].all("skssksrq") && !isDate(LSSWSZMX.rows[ii].all("skssksrq"),false)) {
			alert("税款所属起始日期不合法！");		
			return false;
		}
		if(LSSWSZMX.rows[ii].all("skssjsrq") && !isDate(LSSWSZMX.rows[ii].all("skssjsrq"),false)) {
			alert("税款所属截止日期不合法！");		
			return false;
		}
		//起始日期要小于截止日期
		if(compare(LSSWSZMX.rows[ii].all("skssksrq"),LSSWSZMX.rows[ii].all("skssjsrq"))<0) {
			alert("起始日期必须小于或等于截止日期！");
			return false;
		}//检查税款所属截止日期 ChangeBy Tujunbing 2012-08-30
		else{
			if(LSSWSZMX.rows[ii].all("skssjsrq")){
				checkSkssjsrq = LSSWSZMX.rows[ii].all("skssjsrq").value;
			}
		}
		//2007年城镇土地使用税新旧税率衔接
		//背景：2007-1-1日起城镇土地使用税税额调整，2007-1-1日后采用新税额，之前采用旧税额
		if(LSSWSZMX.rows[ii].all("szsmdm")&&LSSWSZMX.rows[ii].all("szsmdm").value.substr(0,2)=="15"){
			if(LSSWSZMX.rows[ii].all("skssksrq")&&LSSWSZMX.rows[ii].all("skssjsrq")){
				var ksnd=LSSWSZMX.rows[ii].all("skssksrq").value.substr(0,4);
				var jsnd=LSSWSZMX.rows[ii].all("skssjsrq").value.substr(0,4);
				if(ksnd<=2006&&jsnd>2006){
					alert("2007年1月1日起城镇土地使用税税额调整为原来的三倍，请将税款所属期拆分为：\n"+
					LSSWSZMX.rows[ii].all("skssksrq").value+"-20061231\n"+
					"20070101-"+LSSWSZMX.rows[ii].all("skssjsrq").value+"\n"+
					"分两张收现缴款书进行录入！");
					LSSWSZMX.rows[ii].all("skssjsrq").value="20061231";
					LSSWSZMX.rows[ii].all("skssjsrq").focus();
					return false;
				}
			}
		}
		//课税数量不为空时必须为合法的整数
		if(LSSWSZMX.rows[ii].all("kssl") &&LSSWSZMX.rows[ii].all("kssl").value!=null&& !isNum(LSSWSZMX.rows[ii].all("kssl"),0)) {
			alert("课税数量不为空时必须为合法的整数！");		
			return false;
		}
		var obj_name=LSSWSZMX.rows[ii].all("szsmdm");
		var obj_value = obj_name.value;
		if (obj_value.indexOf("11")!=0 && obj_value.indexOf("88")!=0) {
			if(LSSWSZMX.rows[ii].all("jsje") && !isNum(LSSWSZMX.rows[ii].all("jsje"),0,null,true)) {
				alert("计税金额不合法！");		
				return false;
			}
			if(LSSWSZMX.rows[ii].all("sjse") && !isNum(LSSWSZMX.rows[ii].all("sjse"),0,null,true)) {
				alert("实际缴税额不合法！");		
				return false;
			}
		}
		//选择了车船，则课税数量必须录入
		if (obj_value.indexOf("11")==0 || obj_value.indexOf("88")==0) {
			if(LSSWSZMX.rows[ii].all("kssl") && !isNum(LSSWSZMX.rows[ii].all("kssl"),0,null,true)) {
				alert("课税数量不合法！");		
				return false;
			}
			//计税金额、实际缴税额不为空时，必须为合法数字
			if(LSSWSZMX.rows[ii].all("jsje") && LSSWSZMX.rows[ii].all("jsje").value!=null && !isNum(LSSWSZMX.rows[ii].all("jsje"),0)) {
				alert("计税金额不为空时必须为合法的数字！");		
				return false;
			}
			if(LSSWSZMX.rows[ii].all("sjse") && LSSWSZMX.rows[ii].all("sjse").value!=null && !isNum(LSSWSZMX.rows[ii].all("sjse"),0)) {
				alert("实际缴税额不为空时必须为合法的数字！");		
				return false;
			}
		}
		//验证 ChangeBy Tujunbing 2012-08-30
		if(checkSzsmdm != "" && checkSkssjsrq != ""){
			var checkResult_tsny ="";
			var checkResult_zxrq = "";
			//调取TSNY:doCheck_szsm_tsny function
			checkResult_tsny = doCheck_szsm_tsny(checkSzsmdm,checkSkssjsrq);
			//调取税款所属期:doCheck_szsm_zxrq function
			checkResult_zxrq = doCheck_szsm_zxrq(checkSzsmdm,checkSkssjsrq);
			if(checkResult_tsny == false){
				return checkResult_tsny;
			}
			if(checkResult_zxrq == false){
				return checkResult_zxrq;
			}       
		}
	}
	doSubmitForm(ope);
}



//下拉列表使用的js数组
<ttsoft:write name="lszsWszlrForm" property="scriptStr" filter="false"/>
//是否现实附加税标志
isadditions = true;
//重新计算附加税的计税金额和应缴金额
function resetFjs(){	
	tableid="LSSWSZMX";
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
	computeSameSum("sjse","hjsjje","LSSWSZMX");

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
	
	var oRow = getObjRow(obj);	
	//如果被修改行为附加税则不重新计算
	if(oRow.all("sffjs").value=='2'){ 
		//重新计算系统合计
		computeSameSum("sjse","hjsjje","LSSWSZMX");
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

hjArray[0]=new Array("sjse","hjsjje","LSSWSZMX");
//将合计项清零
function initHj(){	
	document.forms[0].hjsjje.value='0';
}
initHj();



//计算文化事业建设费的计税和实缴
//文化事业建设费的计税金额为主税的计税金额
function resetWhjssy(){
	tableid="LSSWSZMX";
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
	computeSameSum("sjse","hjsjje","LSSWSZMX");

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

</script>
</html:html>