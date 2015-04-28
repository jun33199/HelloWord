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
<title>核定征收</title>
<style type="text/css">
<!--
@import url(../css/text.css);
-->
</style>

<script language="JavaScript" type="text/JavaScript" src="../js/list.js"></script>
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/function.js"></script>
<script language=JavaScript src="../js/MathString.js"></script>
<script language=JavaScript src="../js/Stack.js"></script>
<script language=JavaScript src="../js/Bolan.js"></script>
<script language=JavaScript src="../js/reader.js"></script>
<script language=JavaScript src="../js/InputSelect.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/DTable.js"></script>
<script language="JavaScript" type="text/JavaScript">
//退出界面
function fnReturn()
{
    location.href="PG3_SBZS_023.htm";
}

//请求提交
function doSubmit(method){
	document.all.actionType.value=method;
	document.all.wqyyshdzsForm.submit();
	return false;
}
function inputCheck_zs(obj){
      	if(!Number(obj.value) || obj.value <= 0){
		alert("输入域必须为正数！");
		window.event.returnValue=false;
                obj.focus();
                obj.select();
		return false;
      	}
	return true;
}
function inputCheck_cxs(obj){
      	if(!Number(obj.value) || obj.value <= 0 ||obj.value >=1){
		alert("输入域必须为纯小数！");
		window.event.returnValue=false;
                obj.focus();
                obj.select();
		return false;
      	}
	return true;
}
function inputCheck_ff(obj){
      	if(!Number(obj.value) && obj.value != 0 || obj.value < 0){
		alert("输入域必须为非负数！");
		window.event.returnValue=false;
                obj.focus();
                obj.select();
		return false;
      	}
	return true;
}
function jsjdmQuery(){
        if(event.keyCode==13) event.keyCode = 9;
}
function showhj(){
	// if(document.forms[0].actionType.value=="Show")
	document.forms[0].jsjdm.focus();
  if(document.forms[0].actionType.value=="Query")
  {  computeSameSum("bqybse","hj");
     document.forms[0].jsjdm.focus();
   }
}

function doSave()
{
  var ok=1;
   var szsmdm;
jsjdm = document.forms[0].jsjdm.value;

        nsrmc = document.forms[0].nsrmc.value;

	if((jsjdm==null || jsjdm=="")||(nsrmc==null || nsrmc=="")){
        alert("页面信息填写不正确！");
        }
        else{
        if(document.forms[0].szsmdm){
        if(document.forms[0].szsmdm.length){
          for(i = 0; i < document.forms[0].szsmdm.length; i++){
          szsmdm=document.forms[0].szsmdm[i].value;
           if(szsmdm==""||szsmdm==null)
          {  k=i+1;
           alert("外企营业税的第"+k+"行的外企营业税税目没有填写，请填写");
           ok=0;
          }

          }

        }else
        {
           szsmdm=document.forms[0].szsmdm.value;
           if(szsmdm==""||szsmdm==null)
          {
            alert("外企营业税的第1行的外企营业税税目没有填写，请填写");
           ok=0;
          }
        }
        }
        }
 if(ok==1)
	{doSubmitForm("Save");
         return false;
        }
}
function doDelete()
{ var jsjdm;
	jsjdm = document.forms[0].jsjdm.value;
	var nsrmc;
	jsjdm = document.forms[0].jsjdm.value;
        nsrmc = document.forms[0].nsrmc.value;
	if((jsjdm==null || jsjdm=="")||(nsrmc==null || nsrmc=="")){
        alert("页面信息填写不正确！");
        //document.forms[0].jsjdm.focus();
        return false;
        }else{
	doSubmitForm("Delete");
        return false;
        }
}
</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onLoad="showhj()">
<script language="JavaScript" type="text/JavaScript">

function checkVisitCount()
{
    var val = parseInt(VisitCounter.value);
    if (val > 0)
    {
        window.location = "shenbao/page_guoqi.jsp";
        return true;
    }
    val = val + 1;
    VisitCounter.value = val;
    return true;
}
</script>
<input type="hidden" name="VisitCounter" value="0">

<%@ include file="./include/header.jsp"%>

<html:form action="/webapp/smsb/wqyyshdzsAction.do" method="POST">
<html:hidden property="actionType"/>
<html:hidden property="cjrq"/>
<html:hidden property="lrr"/>
<html:hidden property="fsdm"/>
<html:hidden property="lrrq"/>
<html:hidden property="swjgzzjgdm"/>
<html:hidden property="szdm"/>
<html:hidden property="szmc"/>
<html:hidden property="zsffdm"/>
<html:hidden property="zsffmc"/>
<html:hidden property="iszhsb"/>
<html:hidden property="qxdm"/>

<INPUT TYPE="hidden" NAME="szsmdm_focus">

<table width="100%" border="0" cellpadding="0" cellspacing="0" onkeydown="jsjdmQuery()">
  <tr>
    <td>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1"><%=head_title%></td>
        </tr>
        <tr>
          <td class="1-td2"><br>
            <table cellspacing="0" class="table-99">
              <tr>
                  <td colspan="2" class="2-td2-t-left">税务计算机代码&nbsp;
                    <html:text property="jsjdm" size="8" maxlength="8" onchange="return doSubmit('Query')" onKeyDown="jsjdmQuery()"/></td>
                  <td class="2-td2-t-center">申报单位名称</td>
              </tr>
              <tr>
              	<td class="2-td2-left"><div align="left">申报日期：
                <html:text property="sbrq" size="11" maxlength="8" onchange="getStartEndDate(this,document.forms[0].skssksrq,document.forms[0].skssjsrq)"/>
                      </div></td>
		<td class="2-td2-left"><div align="right">税款所属日期：
                     <!-- <html:text property="skssksrq" size="11" maxlength="8" onchange="compareDate('skssksrq','skssjsrq',0,this)"/>
                      至&nbsp;<html:text property="skssjsrq" size="11" maxlength="8" onchange="compareDate('skssksrq','skssjsrq',0,this)"/>-->
                    <html:text property="skssksrq" size="11" maxlength="8" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,3,0)"/>至
                    <html:text property="skssjsrq" size="11" maxlength="8" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,3,1)"/>
               </div></td>
                <td width="250" class="2-td2-center">&nbsp;<ttsoft:write name="wqyyshdzsForm" property="nsrmc"/>&nbsp;
                      <html:hidden property="nsrmc"/></td>
              </tr>
            </table>
            <br>

            <table width="100%" border="0" cellpadding="0" cellspacing="0" id="WQYYS" onkeydown='return dokeydown(this.id,"szsmdm")'  onmouseup='return dokeyUp(this.id)'>
                  <ttsoft:smsbtable form="wqyyshdzsForm" property="dataList" tableId="WQYYS" hasTitle="true"/>
            <DIV id=divSfTemp></DIV>
            <tr>
                  <td colspan="11" class="2-td2-center"><div align="right">本期实际应补税额合计：&nbsp;
                  <input align="right" type="textfield" name="hj" class="inbright" readonly tabIndex="-1"></div></td>
            </tr>
            </table>

            <br>
            <!-- 异常处理的页面输出 -->
            <!-- 操作标识 -->
            <input name="operType" type="hidden">
            <br><center> <input type="image" accesskey="a" tabIndex="-1" onclick="javascript:if(document.forms[0].jsjdm.value=='' || document.forms[0].nsrmc.value=='') {return false;}else{addRow('WQYYS'); return false;}" onMouseOver="MM_swapImage('tianjia','','<%=static_contextpath%>images/zj-a2.jpg',1)" onMouseOut="MM_swapImgRestore()"  src="<%=static_contextpath%>images/zj-a1.jpg" width="79" height="22" id="tianjia" alt="增加" style="cursor:hand"/>
              &nbsp;&nbsp;&nbsp;&nbsp;  <input type="image" accesskey="s" tabIndex="-1"  onClick="doSave(); return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/bc-s1.jpg" alt="保存"  width="79" height="22" id="baocun" style="cursor:hand" >
                  &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="d" tabIndex="-1" onClick="javascript:deleteRow('WQYYS',null); return false;"  onMouseOver="MM_swapImage('sc2','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="删除" id="sc2" src="<%=static_contextpath%>images/sc-d1.jpg" width="79" height="22">
                   &nbsp;&nbsp;&nbsp;&nbsp;<input type="image" accesskey="x" tabIndex="-1"  onClick="doDelete();return false;"onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg ',1)" onMouseOut="MM_swapImgRestore()" alt="清除" src="<%=static_contextpath%>images/qbsc-x1.jpg" width="79" height="22" id="shanchu" style="cursor:hand">
                  &nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="c" tabIndex="-1" onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand">
            &nbsp; &nbsp;</center><br>
          </td>
        </tr>
      </table><br>
<div id="szsmdm_epodDateLayer" style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" onclick="this.style.display='none'"><select id=sel size="11" onclick='selectClick("szsmdm","WQYYS")' onkeyup='selectMove("szsmdm","WQYYS")' onfocusout=selectClick("szsmdm","WQYYS")></select></div>

    </td>
  </tr>
</table>
</html:form>
<%@ include file="./include/footer.jsp"%>
<SCRIPT LANGUAGE="JavaScript">
<!--
/****如果该纳税人为非正常户，则显示提示信息****/
/****20050817 HU xiaofeng****/
var nsrzt = <ttsoft:write name="wqyyshdzsForm" property="nsrzt" filter="false"/>;
checkNsrzt();
/*************end*****************/
</SCRIPT>
</body>
<script language=JavaScript >
var WQYYS_list = new DTable(WQYYS,jsArr_WQYYS);
WQYYS_list.tableTail=1;
<ttsoft:write name="wqyyshdzsForm" property="scriptStr" filter="false"/>


mathArray[0] = new MathString("hdsre=htcje*yjl");
mathArray[1] = new MathString("ynse=jsje*sl");
mathArray[2] = new MathString("bqybse=ynse-yinse");

</script>
</html:html>