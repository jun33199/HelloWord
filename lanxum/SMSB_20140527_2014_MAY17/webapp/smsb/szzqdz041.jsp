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
<style type="text/css">
<!--
#floater{
	position:absolute;
	left:40%;
	top:16%;
	width:450;
	visibility:hidden;
	z-index:10;
}
.black9 {
	font-size: 9pt;
	color: #415967;
	white-space: pre;
}
-->
</style>
<script language="JavaScript" type="text/JavaScript">
function show(){
	floater.style.visibility = "visible";
	globleAction = true;
	startclock();
}
function ref(){
	window.location.reload();
}

var globleAction = false;

//计时
var timerID = null;
var timerRunning =false;
var starttime=new Date();
var startseconds=starttime.getSeconds();
var startminutes=starttime.getMinutes();
var starthours=starttime.getHours();
startmark=starthours*60*60+startminutes*60+startseconds

function stopclock (){
  if(timerRunning)
    clearTimeout(timerID);
  timerRunning = false;
}

function startclock () {
  stopclock();
  showtime();
}

function showtime () {
  var now = new Date();
  var nowseconds = now.getSeconds();
  var nowminutes=now.getMinutes();
  var nowhours=now.getHours();
  nowmark=nowhours*60*60+nowminutes*60+nowseconds
  period=nowmark-startmark
  period=(period>=0) ? period : period+86400
  seconds=period%60;
  minutes=((period-seconds)/60)%60;
  hours=(period-seconds-(60*minutes))/3600;
  var timeValue=""+((hours<10)?"0":"")+hours
  timeValue+=((minutes<10)?":0":":")+minutes
  timeValue+=((seconds<10)?":0":":")+seconds
  //face.innerHTML = timeValue;
  timerID = setTimeout("showtime()",1000);
  timerRunning = true;
}
</script>
<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

<title>税种与征期类型对照关系维护</title>

<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/list.js"></script>
<script language="JavaScript">
//引入增加行所用的字串
<%
    com.ttsoft.bjtax.smsb.zqwh.ZqwhHelper zHelper = new com.ttsoft.bjtax.smsb.zqwh.ZqwhHelper();
    com.ttsoft.bjtax.smsb.zqwh.ZqwhHelper zHelper2 = new com.ttsoft.bjtax.smsb.zqwh.ZqwhHelper();
    java.util.List szList = zHelper.getSzList();
    java.util.List djzclxList = zHelper2.getDjzclxList();
    
    out.println( zHelper.getJsOutput() );
    pageContext.setAttribute("szList",szList);
    pageContext.setAttribute("djzclxList",djzclxList);
%>


</script>

<link href="../css/text.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="./include/header.jsp"%>

<div id="floater"> 
  <table width="398" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="302" height="20"><font style="font-size:12pt;color:red;">正在运行...</font><img src="<%=static_contextpath%>images/wait.gif" border="0" width="194" height="20"></td>
      <td width="109" height="20" align="left" valign="middle" class="black9" id="face">&nbsp;</td>
  </tr>
</table>
</div>

<html:form action="/webapp/smsb/szzqdzAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="queryType" />
<html:hidden property="tempNd" />
<html:hidden property="tempDjzclx" />
<html:hidden property="tempSz" />
<html:hidden property="tempZqlx" />
<html:hidden property="importNd" />

<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0"  class='black9'>
  <tr>
    <td valign="top"> <br>
      <table  align="center" cellspacing="0" class="table-99" border="0">
        <tr class="black9">
          <td class="1-td1">税种与征期类型对照关系维护</td>
        </tr>
        <tr>
          <td valign="top" class="1-td2">
            <br>
            <div align="center">
             <table class="table-99" border="0" cellpadding="0" cellspacing="0">
              <tr>
               <td nowrap class="2-td2-t-left" width="15%">输入维护年份</td>
               <td nowrap class="2-td2-t-left" width="25%"><div align="left"><html:text property="headNd" size="10" onkeydown="if(window.event.keyCode==13) event.keyCode=9;" /></div></td>
               <td nowrap class="2-td2-t-left" width="15%">征期类型</td>
               <td nowrap class="2-td2-t-center" width="45%"><div align="left">			
					<ttsoft:define id="zqlxItems" codekey="ZQWH_ZQLX"/>
					<html:select property="headZqlx" onkeydown="if(window.event.keyCode==13) event.keyCode=9;" >
	                	<html:option value="*">全部</html:option>
	                	<html:options collection="zqlxItems" property="value" labelProperty="label"/>
	              	</html:select></div>
					</td>
              </tr>
              <tr >
               <td nowrap class="2-td2-t-left" width="15%">税种</td>
               <td nowrap class="2-td2-t-left" width="25%"><div align="left"><html:select property="headSz" onkeydown="if(window.event.keyCode==13) event.keyCode=9;">
               		<html:option value="*">全部</html:option>
                    <html:options collection="szList" property="value" labelProperty="label"/>
                    </html:select></div></td>
               <td nowrap class="2-td2-t-left" width="15%">登记注册类型</td>
               <td nowrap class="2-td2-t-center" width="45%"><div align="left">
					<ttsoft:define id="djzclxItems" codekey="ZQWH_DJZCLX"/>
					<html:select property="headDjzclx" onkeydown="if(window.event.keyCode==13) event.keyCode=9;" >
	                	<html:option value="*">全部</html:option>
	                	<html:options collection="djzclxItems" property="value" labelProperty="label"/>
	              	</html:select></div>
					</td>
              </tr>
              <tr>
               <td colspan="4" class="2-td2-t-center" >
               <table width="100%" border="0" height="12">
               <tr><td width="50%"><input type="button" accesskey="i" tabIndex="-1" name="importDz" value="根据源信息导入对照表并生成最终征期日历(I)" onclick="befImport('Import')"></span></td>
                  <td width="100%">&nbsp;<div align="right"><input type="image" accesskey="q" tabIndex="-1" onClick="befQuery('Query');return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="查询" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" style="cursor:hand">&nbsp;&nbsp;</div></td>
               </tr>
               </table>
               </td>
              </tr>
             </table>
             
            <br>    
              <table id="SzzqwhTable" width="89%"  class="table-99" cellspacing="0">
                <tr>
                  <td width="5%"  class="2-td1-left">&nbsp;</td>
                  <td width="5%"  class="2-td1-left">&nbsp;</td>
                  <td width="25%" class="2-td1-left">征期类型名称</td>
                  <td width="30%"  class="2-td1-left">税种</td>
                  <td width="35%"  class="2-td1-left">登记注册类型</td>
                </tr>
                <%int intCount=1;%>
              <bean:define id="resultlist" name="szzqdzForm" property="dataList"/>
              <logic:iterate id="items" name="resultlist" indexId="index">
                <bean:define id="item" name="items" type="java.util.Map"/>
                <tr>
                    <%
                      String zqlxdm = String.valueOf(item.get("zqlxdm"));
                      String szdm = String.valueOf(item.get("szdm"));
                      String djzclxdm= String.valueOf(item.get("djzclxdm"));
                      String szAndZqlxdm = szdm + "|" + zqlxdm + "|" + djzclxdm;
                      //System.out.println("intCount="+intCount+"<>djzclxdm"+djzclxdm);
                    %>
                  <td width="5%"  class="2-td2-left"><%=intCount%></td>
                  <td class="2-td2-left"> <html:checkbox property="deleteCheckBox" value="<%=szAndZqlxdm%>" />
                  </td>
                  <td class="2-td2-left">
                    <ttsoft:select property="zqlxdm" value="<%=zqlxdm%>" codekey="ZQWH_ZQLX"/>
                  </td>
                  <td class="2-td2-left">

                    <html:select property="szdm" value="<%=szdm%>">
                    <html:options collection="szList" property="value" labelProperty="label"/>
                    </html:select>
                  </td>
                  <td class="2-td2-left">
                    <html:select property="djzclxdm" value="<%=djzclxdm%>">
                    <html:options collection="djzclxList" property="value" labelProperty="label"/>
                    </html:select>
                  </td>
                </tr>
                <% intCount++; %>
              </logic:iterate>
              </table>
            </div>
            <div align="left" style="font-size:10pt;">&nbsp;&nbsp;<input type="checkbox" name="checkAllBox" value="1" onClick="checkAllFunc();">全选</div>
            <br>
            <div align="center"> <!--<input type="image" accesskey="m" src="<%=static_contextpath%>images/bysb-m1.jpg" alt="本月申报" name="Image2" onClick='doSelectSubmit("1");return false;' border="0" id="bysb" onMouseOver="this.src ='<%=static_contextpath%>images/bysb-m2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/bysb-m1.jpg';">
              &nbsp;&nbsp; <input type="image" accesskey="w" src="<%=static_contextpath%>images/qbsj-w1.jpg" alt="全部数据" name="Image2" onClick='doSelectSubmit("2");return false;' border="0" id="qbsj" onMouseOver="this.src ='<%=static_contextpath%>images/qbsj-w2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/qbsj-w1.jpg';">-->
              &nbsp;&nbsp; <input type="image" accesskey="a" src="<%=static_contextpath%>images/zj-a1.jpg" alt="增加" name="Image2" onclick="befAdd();return false;" border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/zj-a2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/zj-a1.jpg';">
              &nbsp;&nbsp; <input type="image" accesskey="d" src="<%=static_contextpath%>images/sc-d1.jpg" alt="删除" name="Image2" onclick='doDeleteSubmit();return false;' border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/sc-d2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/sc-d1.jpg';">
              &nbsp;&nbsp; <input type="image" accesskey="s" src="<%=static_contextpath%>images/bc-s1.jpg" alt="保存" name="Image2" onclick='befSave();return false;' border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/bc-s2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/bc-s1.jpg';">
              &nbsp;&nbsp; <input type="image" accesskey="f" src="<%=static_contextpath%>images/fh-f1.jpg" alt="返回" name="Image2" onclick='doSubmitForm("Return");return false' border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/fh-f2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/fh-f1.jpg';"></div>
            <br> <br>
             </td>
        </tr>
      </table>
      <br></td>
    </tr>
</table>
</html:form>
<%@ include file="./include/footer.jsp"%>
</body>
<script language="javascript">
function doSelectSubmit(strQueryType){
  document.forms[0].queryType.value = strQueryType;
  if(strQueryType=="1"){
//    now = new Date();
//    document.forms[0].headNd.value = "" + now.getYear();
  }else if(strQueryType=="2"){
    //维护年份Check
    strDate =document.all.headNd.value;
    if(isFullDate(strDate,1,"Notempty",true)==false){
      return false;
    }
  }
  doSubmitForm("Query");
}

function befQuery(actionType){
	if (globleAction==false){
		if(!isFullDate1(document.forms[0].headNd.value+"0101",0,"1","")) {
			alert("年度不合法！");	
			document.forms[0].headNd.select();
			return false;
		}
		else {
			document.forms[0].tempNd.value=document.forms[0].headNd.value;
			document.forms[0].tempZqlx.value=document.forms[0].headZqlx.value;
			document.forms[0].tempSz.value=document.forms[0].headSz.value;
			document.forms[0].tempDjzclx.value=document.forms[0].headDjzclx.value;
			
			doSubmitForm(actionType);
			show();
			return false;
		}
	}
}

function befAdd(){
	if(document.forms[0].tempNd.value!=""){
		addRow();
	}
	else {
		alert("请先查询！");
		return false;
	}
}

function befSave(){
	if (globleAction==false){
		if(document.forms[0].tempNd.value!=""){
			doSaveSubmit();
		}
		else {
			alert("请先查询！");
			return false;
		}
	}
}
function befImport(actionType){
	if (globleAction==false){
		if(!isFullDate1(document.forms[0].headNd.value+"0101",0,"1","")) {
			alert("年度不合法！");	
			document.forms[0].headNd.select();
			return false;
		}
		
		initialYear=prompt("请输入要导入的数据源的年份：", "");
		if (initialYear!="" && initialYear!=null) initialYear=x_Trim(initialYear);
		if(!isFullDate1(initialYear+"0101",0,"1","")){
			alert("年度不合法，请重新输入！");
			return false;
		}
		
		if(initialYear) {
			if (confirm("当前操作是把"+initialYear+"年度的对照数据导入到对照表，并生成"+document.forms[0].tempNd.value+"年度的对照数据!\n同时根据"+document.forms[0].tempNd.value+"年度的征期日历生成该年度的最终的征期日历！\n是否继续？")){
				document.forms[0].importNd.value=initialYear;
				document.forms[0].tempNd.value=document.forms[0].headNd.value;
				document.forms[0].actionType.value=actionType;
				
				document.forms[0].submit();
				show();
				return false;
			}
		}
	}
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
//删除提交
function doDeleteSubmit(){
	if (globleAction==false){
		if(checkBoxesCheck("deleteCheckBox")==false){
			return false;
		}
		if(confirm("该操作是删除当前对照表的信息，同时会删除相关的最终的征期日历信息！\n是否继续？")){
			document.forms[0].actionType.value = "Delete";
			
			document.forms[0].submit();
			show();
			//doSubmitForm("Delete");
		}
	}
}

//保存提交
function doSaveSubmit(){
  //维护年份Check
  strDate =document.all.headNd.value;
  if(isFullDate(strDate,1,"Notempty",true)==false){
    return false;
  }
  
  if(!checkTheSame("zqlxdm","szdm","djzclxdm")){
    return false;
  }
  if (confirm("该操作将根据当前查询条件和设置生成（或重新生成）"+document.forms[0].tempNd.value+"年度的最终使用的征期日历！\n是否继续？")){
  	document.forms[0].actionType.value='Save';
  	
  	document.forms[0].submit();
  	show();
  	//doSubmitForm("Save");
  }
}


//插入检验，三个列表中的任何一个不重复，并且不包含！
function checkTheSame(objName1,objName2,objName3) {
  var checkObj1 = document.all(objName1);
  var checkObj2 = document.all(objName2);
  var checkObj3 = document.all(objName3);
  
  var rowIndex = SzzqwhTable.rows.length;
  var allValue = new Array(rowIndex-1);
  var tempValue = new Array(rowIndex-1);
  
  if(checkObj1){
    if(!checkObj1.name){//有两个税种税目以上的情况，两行
      var objValue1 = new Array(checkObj1.length);
      var objValue2 = new Array(checkObj2.length);
      var objValue3 = new Array(checkObj3.length);
      
      for( i=0;i<checkObj1.length;i++){
        objValue1[i] = checkObj1[i].value;
        objValue2[i] = checkObj2[i].value;
        objValue3[i] = checkObj3[i].value;
        //alert(objValue2[i]);
        allValue[i] = objValue1[i]+"|"+objValue2[i]+"|"+objValue3[i];
        tempValue[i] = objValue1[i]+"|"+objValue2[i]+"|"+objValue3[i];
	  	//alert("dd=="+allValue[i]);
      }
	   
	  //查重
	  for( i=0; i<allValue.length; i++){
	  	var theValue1 = allValue[i];
	  	var theValueA1 = objValue1[i];
	  	var theValueA2 = objValue2[i];
	  	var theValueA3 = objValue3[i];
	  	var theSz1 = objValue2[i];
	  	for( j=i+1; j<tempValue.length; j++){
	  		var theValue2 = tempValue[j];
	  		var theValueB1 = objValue1[j];
		  	var theValueB2 = objValue2[j];
		  	var theValueB3 = objValue3[j];
	  		
	  		//登记注册类型包含
	  		if (theValueA3=="*" || theValueB3=="*" || theValueA3==theValueB3) {
	  			if ((theValueA2.indexOf(theValueB2)==0 || theValueB2.indexOf(theValueA2)==0)) {
	  				alert("第"+(i+1)+"行包含了（或部分被包含于）第"+(j+1)+"行，请检查！");
	  				return false;
	  			}
	  		}
	  		
	  	}//end of second loop
	  }//end of first loop

    }
  }
  return true;
}

function find_selectIndex(obj,value){
  if(value==null) value="";
  for(i=0;i<obj.options.length;i++){
    if(value==obj.options[i].value){
      break;
    }
  }
  if(i<obj.options.length){
    return i;
  }else{
    return -1;
  }
}

function addRow(){
	var rowIndex = SzzqwhTable.rows.length;
	var trNode = SzzqwhTable.insertRow(rowIndex);
	
	var tdClass = ["2-td2-left","2-td2-left","2-td2-left","2-td2-center","2-td2-center"];
	var tdAlign = ["left","left","left","left","left"];
	var tdContent = [rowIndex,'<a href="#" id="delIndex" onclick=delRow(this)>删除</a>',
		strZqlxSelect,
		strSzSelect,
		strDjzclxSelect,
		];
	//tdContent[0] = document.forms[0].all.select.outerHTML;
	//var trNode = document.createElement("TR");


	trNode.id ="_rowid";
	for( ii=0 ; ii < tdClass.length ; ii++ ) {
			var tdNode = document.createElement("TD");
			//if(ii==0) tdNode.id = "sfgl_td1"
			tdNode.className =tdClass[ii];
			tdNode.align=tdAlign[ii];
			tdNode.innerHTML=tdContent[ii];//.toString().replace("%rowIndex%",rowIndex);
			trNode.appendChild(tdNode);
	}//end for

    var selectObj1 = document.all("zqlxdm");
    var selectObj2 = document.all("szdm");
    
    //根据查询条件，设定新增行的却省值
    if (document.forms[0].tempZqlx.value!="*" && document.forms[0].tempZqlx.value!=""){
	    if (rowIndex==1){
	    	selectObj1.value=document.forms[0].tempZqlx.value;
	    }
	    else {
	    	selectObj1[rowIndex-1].value=document.forms[0].tempZqlx.value;
	    }
    }
    
    if (document.forms[0].tempSz.value!="*" && document.forms[0].tempSz.value!=""){
	    if (rowIndex==1){
	    	selectObj2.value=document.forms[0].tempSz.value;
	    }
	    else {
	    	selectObj2[rowIndex-1].value=document.forms[0].tempSz.value;
	    }
    }
}

function delRow(obj)
{
  if(confirm("确定删除此纪录？")){
    getObjRow(obj).removeNode(true);
	//重新计算行号
    var rowIndex = SzzqwhTable.rows.length;
	for( ii=1 ; ii < rowIndex; ii++ ) {
			var tdNode = SzzqwhTable.rows[ii].cells[0];
			tdNode.innerHTML=ii;
			//alert(tdNode.outerText);
	}//end for
  }
}

function getObjRow(obj){
  obj=obj.parentElement;
  while(obj.tagName!="TR"){
    obj=obj.parentElement;
  }
  return obj;
}

function fnOnLoad()
{
   document.forms[0].headNd.focus();
}

fnOnLoad();

function isFullDate1(strDate,dateKind,empty,isThrow){
	var year,mon,days;
	var err = "";
	if(dateKind==0){//检验8位年月日
		err = "";

		if(strDate.length==0){
			  if(empty!=null){
					err="日期必须不为空!\n";
				}
		}else{
			if(strDate.length!=8){
				err="日期格式不正确，必须是8位数字!\n";
			}else{
				var strYear = strDate.substr(0,4);
				var strMonth = strDate.substr(4,2);
				var strDay = strDate.substr(6,2);
				var objDate = new Date(strMonth+'-'+strDay+'-'+strYear);
				if (isNaN(objDate)){
					err="日期格式不正确!\n"
				}
				if(strYear*1<1900) {
					err="日期格式不正确!\n"
				}
				if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900)) {
					err="日期格式不正确!\n"
				}
				if (strMonth*1 != objDate.getMonth()*1+1){
					err="日期格式不正确!\n"
				}
				if (strDay*1 != objDate.getDate()*1) {
					err="日期格式不正确!\n"
				} //don't call getDay function.
			}
		}
		/*if(err!=""){
			if(isThrow){
				alert(alertStr);
			}
			return false;
		}
		return true;*/
	}else if(dateKind==1){//4位年
		err = "";
		if((strDate.charAt(0)=="0") || strDate.length!=4 || (!isDigit(strDate))){
			err = "不是合法的年份！";
		}
		//alert(err);
	}

	if(err!=""){
		if(isThrow){
			alert(err);
		}
		return false;
	}
	return true;
}

function x_Trim(sSource)
{
	return x_LTrim(x_RTrim(sSource));
}
function x_LTrim(sSource)
{
	var sTemp,sRight;
	sTemp=sSource.toString();
	sRight=sTemp;
	for(i=0;i<sTemp.length;i++)
	{
		if(sTemp.substr(i,1)==" ")
			sRight=sTemp.substring(i+1,sTemp.length)
		else
			break;
	}
	return sRight;
}

function x_RTrim(sSource)
{
	var sTemp,sLeft;
	//alert("test11="+sSource)
	sTemp=sSource.toString();
	sLeft=sTemp;
	for(i=sTemp.length-1;i>=0;i--)
	{
		if(sTemp.substr(i,1)==" ")
			sLeft=sTemp.substr(0,i)
		else
			break;
	}
	return sLeft;
}

//选中全选
function checkAllFunc(){
	//定义各行的checkbox
	var rowIndex = SzzqwhTable.rows.length;
	rowIndex = rowIndex -1;
	var checkObj = document.all("deleteCheckBox");
	if(document.forms[0].checkAllBox.checked==true){
		//循环选中
		if(checkObj){
			if (rowIndex==1){
				document.forms[0].deleteCheckBox.checked=true;
			}
			else{
				for( i=0;i<checkObj.length;i++){
					checkObj[i].checked=true;
				}
			}
		}
	}
	else {
		if(checkObj){
			if (rowIndex==1){
				document.forms[0].deleteCheckBox.checked=false;
			}
			else{
				for( i=0;i<checkObj.length;i++){
					checkObj[i].checked=false;
				}
			}
		}
	}
}


</script>

</html:html>
