<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

<title>征期类型维护</title>

<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/list.js">
</script>

<link href="../css/text.css" rel="stylesheet" type="text/css">

</head>
<%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/zqlxwhAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="strNow" />
<html:hidden property="tempZqlxdm" />

<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
  <!--<tr><td class="2-td1-center">输入维护年份&nbsp<html:text property="whnf" size="5" onkeydown="if(window.event.keyCode==13) return doShowHandle()" /></td>
  </tr>-->
  <tr>
    <td valign="top"> <br>
      <table align="center" cellspacing="0" class="table-99" border="0">
        <tr class="black9">
          <td class="1-td1">征期类型维护</td>
        </tr>
        <tr>
          <td valign="top" class="1-td2">
            <div align="center">
              <table width="100%" id="displayTable" class="table-99" cellspacing="0">
                <tr id="tableTitle" bgcolor="#C5CAD3">
                  <td class="2-td1-left">&nbsp;</td>
                  <td class="2-td1-left">征期类型名称</td>
                  <td class="2-td1-left">征期类<br>型代码</td>
                  <td class="2-td1-left">间隔<br>类型</td>
                  <td class="2-td1-left">税款所属期</td>
                  <td class="2-td1-left">征期开始日期</td>
                  <td class="2-td1-left">征期天数</td>
                  <td class="2-td1-left">征期周期</td>
                  <td class="2-td1-left">录入人</td>
                  <td class="2-td1-left">录入日期</td>
                  <td class="2-td1-left">停用日期</td>
                  <td class="2-td1-center">&nbsp;</td>
                </tr>
               <bean:define id="zqlist" name="zqlxwhForm" property="dataList"/>
               <logic:iterate id="items" name="zqlist" indexId="index">
                <bean:define id="item" name="items"/>
                <tr id='normal<ttsoft:write name="item" property="tyrq"/>'>
                 <% Object tyrq = ((Map)item).get("tyrq"); %>
                 <%if(tyrq==null){%>
                  <td class="2-td2-left"><input type="checkbox" name="tyCheckbox" value="<ttsoft:write name="item" property="zqlxdm"/>"></td>
                 <%}else{%>
                  <td class="2-td2-left">&nbsp</td>
                 <%}%>
                  <td class="2-td2-left"><ttsoft:write name="item" property="zqlxmc"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="zqlxdm"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="jglxdm"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="skssq"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="zqksrq"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="zqts"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="zqzq"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="lrr"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="lrrq"/>&nbsp</td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="tyrq"/>&nbsp</td>
                  <td class="2-td2-center"><div align="center"><a href="javascript:updateFunc('<ttsoft:write name="item" property="zqlxdm"/>')">修改</a></div></td>
                </tr>
               </logic:iterate>
              </table>
	      <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <tr>
                  <td width="5%">&nbsp;</td>
                  <td width="8%"  valign="bottom">
                    <div align="right"  class="black9">显示类型</div></td>
                  <td width="40%">
                    <div align="left"class="black9">
                      <input type="radio" name="radiobutton" value="all" onClick="doChangeDisplay(this.value)" checked>
                      全部征期类型
                      <input type="radio" name="radiobutton" value="normal" onClick="doChangeDisplay(this.value)">
                      正常征期类型
                      <input type="radio" name="radiobutton" value="stopUsing" onClick="doChangeDisplay(this.value)">
                      已停用征期类型</div></td>
                  <td width="9%"><input type="image" accesskey="a" src="<%=static_contextpath%>images/zj-a1.jpg" alt="增加" name="Image2" onClick="addRow();return false;" border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/zj-a2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/zj-a1.jpg';"></td>
                  <td width="9%"><input type="image" accesskey="d" src="<%=static_contextpath%>images/ty-d1.jpg" alt="停用" name="Image2" onClick='doUpdateSubmit();return false;' border="0" id="ty" onMouseOver="this.src ='<%=static_contextpath%>images/ty-d2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/ty-d1.jpg';"></td>
                  <td width="9%"><input type="image" accesskey="s" name="I2" type="image" value="保存"  onClick='doSaveSubmit();return false;' onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="保存" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="22" id="baocun"></td>
		  <!--<td width="9%"><input type="image" accesskey="k" name="Submit42" value="生成征期日历" onClick="doCreateCalendarSubmit();return false;" onMouseOver="MM_swapImage('sczqrl','','<%=static_contextpath%>images/sczqrl-k2.jpg',1)" onMouseOut="MM_swapImgRestore()"  src="<%=static_contextpath%>images/sczqrl-k1.jpg" width="116" height="22"  id="sczqrl" alt="生成征期日历" ></td>-->
                  <td width="9%"><input type="image" accesskey="f" src="<%=static_contextpath%>images/fh-f1.jpg" alt="返回" name="Image2" onClick='doSubmitForm("Return");return false;' border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/fh-f2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/fh-f1.jpg';"></a></td>
                  <td width="23%">&nbsp;</td>
                </tr>
              </table>

            </div>
            <div align="center">
              <p><font size="2">&nbsp; </font> &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
                &nbsp;&nbsp;</p>
<!--              <table width="99%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td> <hr width="100%" size="1" > </td>
                  <td width="99" align="center" class="black9"><strong><font color="#0000FF">注
                    意 事 项</font></strong></td>
                  <td> <hr width="100%" size="1" > </td>
                </tr>
              </table>
              <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
                <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                  <td height="47">&nbsp;&nbsp;&nbsp;1、征期类型名称不能为空：征期类型代码自动生成；征期开始日期是指在当月的开始日期；征期天数是一个征期的长度；征期周期是12位由1或0的组成的数字串，1代表当月有征期，0代表没有。<br>
                    &nbsp;&nbsp;&nbsp;2、增加操作和停用操作在点击‘生成征期日历’按钮以后保存数据库。 </td>
				  </tr>
              </table>-->
              <p>&nbsp;</p>
            </div>
          </td>
        </tr>
      </table><br>

    </td>
    </tr>
</table>
</html:form>
<%@ include file="./include/footer.jsp"%>
<script language="javascript">

//更新
function updateFunc(actionValue){
	document.forms[0].tempZqlxdm.value=actionValue;
	document.forms[0].target="";
	document.forms[0].actionType.value="Edit";
	document.forms[0].submit();
}

function insertCheck()
{
  var zqlxmc =document.all("zqlxmc");
  var zqlxdm =document.all("zqlxdm");
  var zqksrq =document.all("zqksrq");
  var zqts =document.all("zqts");
  var zqzq =document.all("zqzq");
  if(zqksrq){
    if(zqksrq.length){
      for( i=0;i<zqksrq.length;i++){
        strZqlxmc = zqlxmc[i].value + "";
        strZqlxdm = zqlxdm[i].value + "";
        strZqksrq = zqksrq[i].value;
        strZqts = zqts[i].value;
        strZqzq = zqzq[i].value + "";
        if(strZqlxmc.length<1){
          alert("输入格式错误，新增第" + (i+1) + "行征期类型名称必须输入。");
          return false;
        }
        if(strZqlxdm.length<1){
          alert("输入格式错误，新增第" + (i+1) + "行征期类型代码必须输入。");
          return false;
        }
        if((!fnIsIntNum(strZqksrq)) || (strZqksrq.length>2) || parseInt(strZqksrq)>31){
          alert("输入格式错误，新增第" + (i+1) + "行征期开始日期应输入1-31的数字。");
          return false;
        }
        if((!fnIsIntNum(strZqts))){
          alert("输入格式错误，新增第" + (i+1) + "行征期天数应输入数字。");
          return false;
        }
        if( strZqzq.length < 1 ){
          //do nothing
        }else if((!isNumber(strZqzq)) || (strZqzq.length!=12) ){
          alert("输入格式错误，新增第" + (i+1) + "行征期周期应输入12位适当的0、1字串。");
          return false;
        }else{
          for( j=0;j<strZqzq.length;j++) {
            if(strZqzq.charAt(j)!="1"&&strZqzq.charAt(j)!="0"){
              alert("输入格式错误，新增第" + (i+1) + "行征期周期应输入12位适当的0、1字串。");
              return false;
            }
          }
          //规律check
          if(isCircle(strZqzq)==false){
            alert("输入格式错误，新增第" + (i+1) + "行征期周期应输入12位适当的0、1字串。");
            return false;
          }
        }
      }
    }else{
      strZqlxmc = zqlxmc.value + "";
      strZqlxdm = zqlxdm.value + "";
      strZqksrq = zqksrq.value;
      strZqts = zqts.value;
      strZqzq = zqzq.value+"";
      if(strZqlxmc.length<1){
        alert("输入格式错误，新增第" + 1 + "行征期类型名称必须输入。");
        return false;
      }
      if(strZqlxdm.length<1){
        alert("输入格式错误，新增第" + 1 + "行征期类型代码必须输入。");
        return false;
      }
      if((!fnIsIntNum(strZqksrq)) || (strZqksrq.length>2) || parseInt(strZqksrq)>31){
        alert("输入格式错误，新增第" + 1 + "行征期开始日期应输入1-31的数字。");
        return false;
      }
      if((!fnIsIntNum(strZqts))){
        alert("输入格式错误，新增第" + 1 + "行征期天数应输入数字。");
        return false;
      }
      if( strZqzq.length < 1 ){
          //do nothing
      }else if((!isNumber(strZqzq)) || (strZqzq.length!=12) ){
        alert("输入格式错误，新增第" + 1 + "行征期周期应输入12位适当的0、1字串。");
        return false;
      }else{
        for( j=0;j<strZqzq.length;j++) {
          if(strZqzq.charAt(j)!='1'&& strZqzq.charAt(j)!='0'){
            alert("输入格式错误，新增第" + 1 + "行征期周期应输入12位适当的0、1字串。");
            return false;
          }
        }
        //规律check
        if(isCircle(strZqzq)==false){
          alert("输入格式错误，新增第" + 1 + "行征期周期应输入12位适当的0、1字串。");
          return false;
        }
      }
    }
  }
  return true;
}

//规律check
function isCircle(strZqzq)
{
  //输入的格式必须是有规律的
  haveOneflag = -1;
  standStep = 0;
  sumStep=0;
  trebleStrZqzq = strZqzq + strZqzq + strZqzq;
  for( j=0;j<trebleStrZqzq.length;j++) {
    if(trebleStrZqzq.charAt(j)=="1"){
      if(haveOneflag==-1){
        haveOneflag = 0;
      }else if(haveOneflag == 0){
        haveOneflag = 1;
      }else{
        haveOneflag = 2;
      }
      if(haveOneflag==1){
        standStep = sumStep;
      }else if(haveOneflag==2){
        if(sumStep!=standStep){
          return false;
        }
      }
      sumStep = 0;
    }else{
      sumStep = sumStep + 1;
    }
  }
  if(haveOneflag == -1){
        return false;
  }
  return true;

}
//checkboxes check
function checkBoxesCheck(checkboxesName)
{
  var checkbox =document.all(checkboxesName);
  if(checkbox){
    if(checkbox.length){
      for(i=0;i<checkbox.length;i++){
        if(checkbox[i].checked == true){
          return true;
        }
      }
      alert("请至少选择一项");
      return false;
    }else{
      if(checkbox.checked == true){
        return true;
      }else{
        alert("请至少选择一项");
        return false;
      }
    }
  }
  alert("没有可删除的数据");
  return false;
}

function doShowHandle(){
  //维护年份Check
  //strDate =document.all.whnf.value;
  //if(isFullDate(strDate,1,"Notempty",true)==false){
    //return false;
  //}
  return false;
}

//保存提交
function doSaveSubmit()
{
  //维护年份Check
  //strDate =document.all.whnf.value;
  //if(isFullDate(strDate,1,"Notempty",true)==false){
    //return false;
  //}
  if(insertCheck()==false){
    return false;
  }
//  if(doSubmitForm("Save")==false){
//    return false;
//  }
  doSubmitForm("Save");
}
//停用提交
function doUpdateSubmit()
{
  if(checkBoxesCheck("tyCheckbox")==false){
    return false;
  }
//  if(doSubmitForm("Update")==false){
//    return false;
//  }
  doSubmitForm("Update");
}


//生成征期日历
function doCreateCalendarSubmit()
{
  //维护年份Check
  //strDate =document.all.whnf.value;
  //if(isFullDate(strDate,1,"Notempty",true)==false){
    //return false;
  //}

  if(window.confirm("该操作将修改数据库中的数据,且不能自动恢复,请确认")){
    document.forms[0].actionType.value = "CreateCalendar";
    document.forms[0].submit();
  }
}

//显示项目变化
function doChangeDisplay(strDisplayType){

  var rows=displayTable.rows;
  if(strDisplayType=="all"){
    for(var i=0;i<rows.length;i++){
       rows[i].style.display='inline';
    }
  }else if(strDisplayType=="normal"){
    for(var i=0;i<rows.length;i++){
      if( rows[i].id!="normal" && rows[i].id!="_rowid" ){
        rows[i].style.display='none';
      }else{
        rows[i].style.display='inline';
      }
    }
  }else if(strDisplayType=="stopUsing"){
    for(var i=0;i<rows.length;i++){
      if(rows[i].id=="normal"){
        rows[i].style.display='none';
      }else{
        rows[i].style.display='inline';
      }
    }
  }
  tableTitle.style.display="inline";
}
//增加一行
function addRow(){
        //间隔类型
        var strJglxdm='<select name="jglxdm" ><option value="0">次</option>'
                     +'<option value="1">年</option><option value="2">半年</option>'
                     +'<option value="4">季</option></option><option value="12">月</option></select>';
//	var today = new Date();
//	var strToday = today.getYear()+""+(today.getMonth()+1)+""+today.getDate();
        var strToday = <ttsoft:write name="zqlxwhForm" property="strNow" />;
	var tdClass = ["2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-left","2-td2-center"];
	var tdAlign = ["left","left","left","left","left","left","left","left","left","left","left","left"];
	var tdContent = ['<a href="#" id="delIndex" onclick=delRow(this)>删除</a>',
		'<input type="text" name="zqlxmc" size="25" value = "">&nbsp;',
		'<input type="text" name="zqlxdm" size="5" value = "">&nbsp;',
                strJglxdm,
		'<select name="skssq" ><option value="0">上期</option><option value="1">本期</option><option value="2">上期累计</option><option value="3">本期累计</option> </select>',
		'<div align="center">第 <input type="text" name="zqksrq" size="2" value = ""> 天</div>',
		'共 <input type="text" name="zqts" size="2" value = ""> 天',
		'<input type="text" name="zqzq" size="12" maxlength="12" value = "">&nbsp;',
		'<bean:write name="zqlxwhForm" property="lrr"/>',
		strToday,
		'&nbsp;','&nbsp;'];
	//tdContent[0] = document.forms[0].all.select.outerHTML;
	//var trNode = document.createElement("TR");
	var rowIndex = displayTable.rows.length;
	var trNode = displayTable.insertRow(rowIndex);

	trNode.id ="_rowid";
	for( ii=0 ; ii < tdClass.length ; ii++ ) {
			var tdNode = document.createElement("TD");
			//if(ii==0) tdNode.id = "sfgl_td1"
			tdNode.className =tdClass[ii];
			tdNode.align=tdAlign[ii];
			tdNode.innerHTML=tdContent[ii];//.toString().replace("%rowIndex%",rowIndex);
			trNode.appendChild(tdNode);
	}//end for
        //alert(trNode.outerHTML);
	//document.all.ROW_LIST.insertAdjacentElement("BeforeBegin" , trNode);
	//formattable();
}
function delRow(obj)
{
  if(confirm("确定删除此纪录？")){
    getObjRow(obj).removeNode(true);
  }
}

function getObjRow(obj){
  obj=obj.parentElement;
  while(obj.tagName!="TR"){
    obj=obj.parentElement;
  }
  return obj;
}

//function fnOnLoad()
//{
   //document.forms[0].whnf.focus();
//}
//fnOnLoad();
</script>
</html:html>
