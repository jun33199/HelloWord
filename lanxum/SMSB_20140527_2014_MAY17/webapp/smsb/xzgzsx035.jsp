<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>

<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Language" content="zh-cn">



<link href="../css/beijing.css" rel="stylesheet" type="text/css">
<title>新增告知事项</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
</head>
<%@ include file="./include/header.jsp"%>
<%
    java.util.List dqjsList = com.ttsoft.bjtax.smsb.gzwh.GzwhHelper.getDqjsSelect(userData.getSsdwdm());
    pageContext.setAttribute("DqjsList",dqjsList);
%>
<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0"  onload="init()">
<html:form action="/webapp/smsb/xzgzsxAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="saveType" />
<html:hidden property="modifyIndex" />
<html:hidden property="pgNum" />
<html:hidden property="length" />
<html:hidden property="pgSum" />

<html:hidden property="chooseTypeRadio" />
<html:hidden property="jsjdm" />
<html:hidden property="nsrmc" />
<html:hidden property="qylx" />
<html:hidden property="gzlx" />
<html:hidden property="gzqsrq" />
<html:hidden property="gzjzrq" />
<html:hidden property="hylb" />
<html:hidden property="dqjs" />
<html:hidden property="jhfs" />
<html:hidden property="strNow" />
<html:hidden property="mxChooseTypeRadioHidden" />
<html:hidden property="mxNsrmc" size="20" readonly="true" tabindex="-1" />

<table width="820" height="302" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td>
    	<br>
      	<table id=gzsxTable align="center" cellspacing="0"  class="table-99" width=98%>
	       <tr>
	          <td class="1-td1" >新增告知事项</td>
	        </tr>
	        <tr>
	          <td class="1-td2" >
	           	  <table cellspacing="0" class="table-99" width="98%">
              <tr>
		<td width="16%" class="3-td1-left"><div align="center"><B>发送方式</B></div></td>
		<td colspan="3" class="3-td1-centen" colspan="3">
		<div align="left"><html:radio value="1" property="mxChooseTypeRadio" onclick="init()" />计算机代码方式
                  <html:radio value="2" property="mxChooseTypeRadio" onclick="init()"/>群发方式
				  <html:radio value="3" property="mxChooseTypeRadio" onclick="init()"/>计算机代码导入方式
		</div>
		</td>
              </tr>
              

              <tr id="df" style="display:block">
                <td width="16%" class="2-td2-left" nowrap>
                  <div align="center">输入计算机代码</div></td>
                <td width="38%" class="2-td2-left"><div align="left"><html:text property="mxJsjdm" size="40" onchange="return doGetNsrmcSubmit()" onkeydown="if(window.event.keyCode==13) { window.event.keyCode=9; }"/></div></td>
                <td width="16%" class="2-td2-left"><div align="center">纳税人名称</div></td>
                <td id="tdMxNsrmc" width="30%" class="2-td2-center"><div align="left"><ttsoft:write name='gzsxwhForm' property='mxNsrmc'/></div></td>
              </tr>
			
              <tr id="qf1" style="display:block">
                <td class="2-td2-left"><div align="center">选择企业类型</div></td>
                <td class="2-td2-left"><div align="left"><ttsoft:define id="djzclxItems" codekey="ZQWH_DJZCLX"/>
                	<html:select property="mxQylx" style="width:220px">
                                	 <html:option value="0">* 所有企业类型</html:option>
                                         <html:options collection="djzclxItems" property="value" labelProperty="label"/>
                                       </html:select></div>
                <td class="2-td2-left" nowrap><div align="center">&nbsp;&nbsp;选择行业类别&nbsp;&nbsp;</div></td>
                <td class="2-td2-center"><div align="left"><ttsoft:define id="gjbzhyItems" codekey="ZBZL_GJBZHY"/>
                	<html:select property="mxHylb" style="width:220px">
                                	 <html:option value="0">* 所有行业类别</html:option>
                                         <html:options collection="gjbzhyItems" property="value" labelProperty="label"/>
                                       </html:select></div></td>
              </tr>
              
              <tr id="qf2" style="display:block">
                <td class="2-td2-left"><div align="center">选择地区局所</div></td>
                <td class="2-td2-left"><div align="left"><%--<ttsoft:define id="swjgzzjgItems" codekey="GZWH_SWJGZZJG"/>--%>
                	<html:select property="mxDqjs" style="width:220px">
                                	 <%--<html:option value="0">* 所有地区局所</html:option>--%>
                                         <%--<html:options collection="swjgzzjgItems" property="value" labelProperty="label"/>--%>
                                         <html:options collection="DqjsList" property="value" labelProperty="label"/>
                                       </html:select></div>
                <td class="2-td2-left"><div align="center">选择结合方式</div></td>
                <td class="2-td2-center"><div align="left">
                <html:select size="1" property="mxJhfs">
                                	   <html:option value="0">与</html:option>
					   <html:option value="1">或</html:option>
					 </html:select></div></td>
              </tr>
			  <!-- 20040308 Shi Yanfeng 导入计算机代码方式 -->
			  <tr id="dr" style="display:block">
                <td width="16%" class="2-td2-left" nowrap>
                  <div align="center">请选择导入的文件</div></td>
                <td width="38%" class="2-td2-left"><div id="divFile" align="left"><html:file property="excelFile"/></div></td>
                <td width="16%" class="2-td2-left"><div align="right">&nbsp;</div></td>
                <td  width="30%" class="2-td2-center"><div align="left">&nbsp;</div></td>
              </tr>
			  <!-- 20040308 Shi Yanfeng 导入计算机代码方式 -->
              <tr>
                <td class="2-td2-left"><div align="center">选择告知类型</div></td>
                <td class="2-td2-left"><div align="left">
                	<html:select size="1" property="mxGzlx">
                                	   <html:option value="0">正常信息</html:option>
					   <html:option value="1">异常信息</html:option>
					 </html:select></div></td>
                <td class="2-td2-left">&nbsp;</td>
                <td class="2-td2-center">&nbsp;</td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center">告知起始日期</div></td>
                <td class="2-td2-left"><div align="left"><html:text property="mxGzqsrq" size="20"/></div></td>
                <td class="2-td2-left"><div align="center">告知截止日期</div></td>
                <td class="2-td2-center"><div align="left"><html:text property="mxGzjzrq" size="20" /></div></td>
              </tr>
              <tr>
                <td class="2-td2-left" valign="top">告知事项详细信息<br>
                	<div style="color:red">(系统将自动截取告知内容前20个字做为告知事项标题!)</div></td>
                <td colspan="3" class="2-td2-center"><div align="left">
                    <html:textarea rows="10" property="mxGzsxxxxx" cols="80" ></html:textarea>
                  </div></td>
              </tr>
            </table>
			<br>
			<table border="0" width="100%">
              <tr>
                <td width="35%">&nbsp; </td>
                <td width="17%"><input name="I2" type="image" accesskey="s" value="保存"  onClick="doSaveSubmit();return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="保存" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="22" id="baocun"></td>
                <td width="21%"><input name="I3" type="image" accesskey="f" value="返回"  onClick='goEncBack();return false;' border=0 onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="返回" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" id="fanhui"> </td>
                <td width="27%">&nbsp;</td>
              </tr>
            </table>
		      </td>
	   	    </tr>
	    </table>
    </td>
  </tr>
 </table>
</html:form>
</body>
<%@ include file="./include/footer.jsp"%>
<script language="JavaScript">
//onload

function init()
{
  var oRadio = document.all("mxChooseTypeRadio");

  //转换页面功能
  if(document.forms[0].saveType.value=="Insert"){
    document.forms[0].mxJsjdm.readOnly = false;
    document.forms[0].mxChooseTypeRadio[0].disabled = false;
    document.forms[0].mxChooseTypeRadio[1].disabled = false;
	document.forms[0].mxChooseTypeRadio[2].disabled = false;
    gzsxTable.rows[0].cells[0].innerText = "新增告知事项";
    document.title = "新增告知事项";
  }else if(document.forms[0].saveType.value=="Update"){
    document.forms[0].mxJsjdm.readOnly = true;
    document.forms[0].mxChooseTypeRadio[0].disabled = true;
    document.forms[0].mxChooseTypeRadio[1].disabled = true;
	document.forms[0].mxChooseTypeRadio[2].disabled = true;
    gzsxTable.rows[0].cells[0].innerText = "修改告知事项";
    document.title = "修改告知事项";
  }
  //radio控制
  if(oRadio[0].checked==true){

    df.style.display="block";
    document.forms[0].mxJsjdm.disabled=false;
    qf1.style.display="none";
    document.forms[0].mxQylx.disabled=true;
    document.forms[0].mxQylx.options[0].selected=true;
    document.forms[0].mxHylb.disabled=true;
    document.forms[0].mxHylb.options[0].selected=true;
    qf2.style.display="none";
    document.forms[0].mxDqjs.disabled=true;
    document.forms[0].mxDqjs.options[0].selected=true;
    document.forms[0].mxJhfs.disabled=true;
    document.forms[0].mxJhfs.options[0].selected=true;
	dr.style.display="none";

  }else if(oRadio[1].checked==true){
    df.style.display="none";
    document.forms[0].mxJsjdm.disabled=true;
    document.forms[0].mxJsjdm.value="";
    document.forms[0].mxNsrmc.value="";
    document.all.tdMxNsrmc.innerText="  ";
    qf1.style.display="block";
    document.forms[0].mxQylx.disabled=false;
    document.forms[0].mxHylb.disabled=false;
    qf2.style.display="block";
    document.forms[0].mxDqjs.disabled=false;
    document.forms[0].mxJhfs.disabled=false;
	dr.style.display="none";
  }else if(oRadio[2].checked==true){
    df.style.display="none";
    document.forms[0].mxJsjdm.disabled=true;
    document.forms[0].mxJsjdm.value="";
    document.forms[0].mxNsrmc.value="";
    document.all.tdMxNsrmc.innerText="  ";
    qf1.style.display="none";
    document.forms[0].mxQylx.disabled=true;
    document.forms[0].mxQylx.options[0].selected=true;
    document.forms[0].mxHylb.disabled=true;
    document.forms[0].mxHylb.options[0].selected=true;
    qf2.style.display="none";
    document.forms[0].mxDqjs.disabled=true;
    document.forms[0].mxDqjs.options[0].selected=true;
    document.forms[0].mxJhfs.disabled=true;
    document.forms[0].mxJhfs.options[0].selected=true;
	dr.style.display="block";
  }

//  if(oRadio[0].checked==true){
//    document.forms[0].mxQylx.disabled=true;
//    document.forms[0].mxQylx.options[0].selected=true;
//    document.forms[0].mxJsjdm.disabled=false;
//  }else if(oRadio[1].checked==true){
//    document.forms[0].mxJsjdm.disabled=true;
//    document.forms[0].mxJsjdm.value="";
//    document.forms[0].mxNsrmc.value="";
//    document.forms[0].mxQylx.disabled=false;
//  }
}

function doGetNsrmcSubmit(){
  var oRadio = document.all("mxChooseTypeRadio");
  if(oRadio[0].checked==true
     && document.forms[0].mxChooseTypeRadio[0].disabled == false){
    strJsjdm = document.forms[0].mxJsjdm.value+ "";
    if(strJsjdm == "*"){
      document.all.tdMxNsrmc.innerText="  ";
      document.all.mxNsrmc.value="";
      return ;
    }else if(strJsjdm.indexOf(",")>=0){
      document.all.tdMxNsrmc.innerText="  ";
      document.all.mxNsrmc.value="";
      var strJsjdmArray;
      strJsjdmArray = strJsjdm.split(",");
      strJsjdmArray.sort();
      for(i=0;i<strJsjdmArray.length-1;i++){
        if( trim(strJsjdmArray[i]).length==0 ){
          alert("输入的计算机代码组格式错误,请用逗号隔开计算机代码，最后一位不加逗号，请检查！");
          return ;
        }
        if(strJsjdmArray[i]==strJsjdmArray[i+1]){
          alert("输入的计算机代码组中有重复的计算机代码,请检查！");
          return ;
        }
      }
      return ;
    }else if( trim(strJsjdm).length == 0 ) {
      alert("选择输入计算机代码，则必须输入计算机代码，格式为8位代码")
      document.all.tdMxNsrmc.innerText="  ";
      document.all.mxNsrmc.value="";
      return ;
    }
  }else{
    document.all.tdMxNsrmc.innerText="  ";
    document.all.mxNsrmc.value="";
    return false;
  }
  //清空file标签
  clearFileTag();
  if(doSubmitForm("GetNsrmc")==false){
    return false;
  }
  return false;
}

//保存提交
function doSaveSubmit()
{
  //选择输入计算机代码，计算机代码check
  var oRadio = document.all("mxChooseTypeRadio");
  if(oRadio[0].checked==true
     && document.forms[0].mxChooseTypeRadio[0].disabled == false
     && document.forms[0].mxChooseTypeRadio[1].disabled == false){
    strJsjdm = document.forms[0].mxJsjdm.value+"";
    if(strJsjdm == "*"){
      document.all.tdMxNsrmc.innerText="  ";
      document.all.mxNsrmc.value="";
      //return true;
    }else if(strJsjdm.indexOf(",")>=0){
      document.all.tdMxNsrmc.innerText="  ";
      document.all.mxNsrmc.value="";
      var strJsjdmArray;
      strJsjdmArray = strJsjdm.split(",");
      strJsjdmArray.sort();
      for(i=0;i<strJsjdmArray.length-1;i++){
        if( trim(strJsjdmArray[i]).length==0 ){
          alert("输入的计算机代码组格式错误,请用逗号隔开计算机代码，最后一位不加逗号，请检查！");
          return false;
        }
        if(strJsjdmArray[i]==strJsjdmArray[i+1]){
          alert("输入的计算机代码组中有重复的计算机代码,请检查！");
          return false;
        }
      }
    }else if( trim(strJsjdm).length == 0 ){
      alert("选择输入计算机代码，则必须输入计算机代码，格式为8位代码")
      document.all.tdMxNsrmc.innerText="  ";
      document.all.mxNsrmc.value="";
      return false;
    }
  }
//  if(document.forms[0].saveType.value=="Insert"){
//    if(oRadio[1].checked==true){
//      if(document.all.mxQylx.value=="-1"){
//        alert("选择输入企业类型，则必须选择一个企业类型");
//        return false;
//      }
//    }
//  }

  strnow = <ttsoft:write name="gzsxwhForm" property="strNow" />; //getNowTime();

  //征期起始日期Check
  strGzqsrq =document.all.mxGzqsrq.value;
  if(isFullDate(strGzqsrq,0,"notEmpty",false)==false){
    alert("必须输入告知起始日期，日期格式为YYYYMMDD。");
    return false;
  }
  if(document.forms[0].saveType.value=="Insert"){
    if( compare(strGzqsrq,strnow)<0 ){
      alert("告知起始日期应大于等于当前日期。");
      return false;
    }
  }
  //征期截至日期Check
  strGzjzrq =document.all.mxGzjzrq.value;
  if(isFullDate(strGzjzrq,0,"notEmpty",false)==false){
    alert("必须输入告知截止日期，日期格式为YYYYMMDD。");
    return false;
  }
  if(document.forms[0].saveType.value=="Insert"){
    if( compare(strGzjzrq,strnow)<0 ){
      alert("告知截止日期应大于等于当前日期。");
      return false;
    }
  }
 //征期起始日期和征期截至日期关系Check
  if( compare(strGzjzrq,strGzqsrq)<0 ){
    alert("sdsadsadsad");
    alert("征期起始日期应小于征期截至日期。");
    return false;
  }
 //2009.3.31wcl做修改，目的：增加两个时间的间隔判断
 if(datebetween(strGzjzrq,strGzqsrq)==false){
  alert("征期起始日期与截至日期间隔最大为三个月！。");
  return false;
  }
  
  //告知事项详细信息
  strGzsxxxxx =document.all.mxGzsxxxxx.value+"";
  if (strGzsxxxxx.length<1){
    alert("必须输入告知事项详细信息");
    return false;
  }
 /* if (strGzsxxxxx.length>350){
    alert("告知事项详细信息最大长度为350个汉字或字母");
    return false;
  }
  */ //源代码保留，2009.3.31wcl修改，实现关于告知事项字数增加的变更
  if (strGzsxxxxx.length>2000){
    alert("告知事项详细信息最大长度为2000个汉字或字母");
    return false;
  }
  //alert(strGzsxxxxx.length);
  if(strGzsxxxxx.indexOf("$")!=-1){
   alert("告知事项内容中不能有----$!请检查！" );
    return false;
  }
  if(strGzsxxxxx.indexOf("\\t")!=-1){
   alert("告知事项内容中不能有----tab!请检查！" );
    return false;
  }
  if(oRadio[0].checked==true || oRadio[1].checked==true){
	//清空file标签
	clearFileTag();
  }else{
	//设置file标签
	setFileTag();
  }
  if(window.confirm("该操作将修改数据库中的数据,且不能自动恢复,请确认")){
    if(document.forms[0].saveType.value=="Insert"){
      document.forms[0].actionType.value = "Save";
    }else if(document.forms[0].saveType.value=="Update"){
      document.forms[0].actionType.value = "Update";
    }
    //用隐藏域解决disabled
    document.all("mxChooseTypeRadioHidden").value =
                        getRadioValue( document.all("mxChooseTypeRadio") );
    document.forms[0].submit();
  }
}

function compare(value1,value2){
  if(parseInt(value1)<parseInt(value2)){
    return -1;
  }else if(parseInt(value1)==parseInt(value2)){
    return 0;
  }else{
    return ;
  }
}
//得到当前日期
//function getNowTime()
//{
//  now = new Date();
//  intMonth =parseInt(now.getMonth())+1;
//  strMonth = "";
//  strDate = "";
//  if(intMonth<10){
//   strMonth = "0"+ intMonth;
//  }else{
//   strMonth = intMonth;
//  }
//  if(parseInt(now.getDate())<10){
//   strDate = "0"+ now.getDate();
//  }else{
//   strDate = now.getDate();
//  }
//  strnow = "" + now.getYear() + strMonth + strDate;
//  return strnow;
//}
function getRadioValue(obj){
  var value = null;
  for(var i=0;i<obj.length;i++){
    if(obj[i].checked){
      value = obj[i].value;
      break;
    }
  }
  return value;

}

/**20040308 Shi Yanfeng 返回之前改变
document.all.gzsxwhForm.encoding = "multipart/form-data";
document.all.gzsxwhForm.encoding = "application/x-www-form-urlencoded";
**/
function goEncBack(){
	//首先清空html:file标签
	divFile.innerHTML = "";
	//然后重设encoding 
	document.all.gzsxwhForm.encoding = "application/x-www-form-urlencoded";
	doSubmitForm("Return");
	return false;
}
//清空html:file标签
function clearFileTag(){
	//首先清空html:file标签
	divFile.innerHTML = "";
	//然后重设encoding 
	document.all.gzsxwhForm.encoding = "application/x-www-form-urlencoded";
}
//设置file标签
function setFileTag(){
	document.all.gzsxwhForm.encoding = "multipart/form-data";
}
/*
2009.3.31wcl新增js函数，目的：判断告知时间范围是三个月
*/
function datebetween(value1,value2){
//alert("hello");
  if((parseInt(value1)-parseInt(value2))>300){
   alert(parseInt(value1)-parseInt(value2));
   return false;}
   return ;
  
}
</script>
</html:html>