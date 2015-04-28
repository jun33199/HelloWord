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
<title>导入代售单位印花税销售情况</title>
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/function.js"></script>
<style type="text/css">
<!--
@import url(../css/text.css);
-->
</style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()" >
<%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/yhsxsdrAction.do"  method="POST">
<input type=hidden value="Show" name="actionType">
<html:hidden property="lrrq" />
<html:hidden property="sjly" />
<html:hidden property="swjgzzjgdm" />
<html:hidden property="fsdm" />
<html:hidden property="lrr" />
<html:hidden property="pgNum" />
<html:hidden property="length" />
<html:hidden property="pgSum" />
<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">导入代售单位印花税销售情况</td>
        </tr>
        <tr>
          <td class="1-td2">
            <br>
            <table cellspacing=0 class="table-99">
              <tr class="black9">
                <td><div align="left">&nbsp;&nbsp;导入日期：<html:text property="cjsj" styleClass="inputnoborder" style="color:#3C5564" readonly="true" size="10" tabindex="-1"/></div></td>
                <td><div align="right">&nbsp;请选择导入的文件：</div></td>
                <td><div id="divFile" align="left"><html:file property="theFile" />&nbsp;<input type="image" accesskey="i" src="<%=static_contextpath%>images/dr-i1.jpg" alt="导入" id="load" onMouseOver="MM_swapImage('load','','<%=static_contextpath%>images/dr-i2.jpg',1)" onMouseOut="MM_swapImage('load','','<%=static_contextpath%>images/dr-i1.jpg',1)" onclick="doLoad();return false;" style="cursor:hand;vertical-align:text-bottom" ></div></td>
                <td><div align="right">金额单位：人民币元&nbsp;&nbsp;</div></td>
              </tr>
            </table>
            <br>
            <table cellspacing=0 class="table-99">
              <tr>
                <td class="3-td1-left">代售单位计算机代码</td>
                <td class="3-td1-left"><html:text property="dsjsjdm" styleClass="inputnoborder" style="color:#3C5564" readonly="true" size="10" tabindex="-1" /></td>
                <td class="3-td1-left">代售单位名称</td>
                <td class="3-td1-left"><html:text property="dsdwmc" styleClass="inputnoborder" style="color:#3C5564" readonly="true" size="40" tabindex="-1" /></td>
                <td class="3-td1-centen">代售单位联系电话</td>
                <td class="3-td1-right"><html:text property="dsdwlxdh" styleClass="inputnoborder" style="color:#3C5564" readonly="true" size="15" tabindex="-1" /></td>
							</tr>
						</table>
            <br>
            <table cellspacing=0 class="table-99">
              <tr>
                <td class="2-td1-left">序号</td>
                <td class="2-td1-left">销售凭证号</td>
                <td class="2-td1-left">购花单位(人)名称</td>
                <td class="2-td1-left">税票面值</td>
                <td class="2-td1-left">购票数量</td>
                <td class="2-td1-center">金额</td>
              </tr>
              <bean:define id="yhslist" name="yhsxsdrForm" property="dataList"/>
              <%
                com.ttsoft.bjtax.smsb.yhsgl.web.YhsxsdrForm form = (com.ttsoft.bjtax.smsb.yhsgl.web.YhsxsdrForm)request.getAttribute("yhsxsdrForm");
                int pgLength = form.getLength();
                int pgSum = form.getPgSum();
                int pgNum = form.getPgNum();
                int start = pgLength*(pgNum-1);
              %>
              <% int yhsIndex = start+1;%>
              <logic:iterate indexId="index" name="yhslist" id="itemMap"  offset="<%=String.valueOf(start)%>" length="<%=String.valueOf(pgLength)%>">
              <bean:define id="item" name="itemMap"/>
              <tr id="yhsRow">
                <td nowrap class="2-td2-left"><%=yhsIndex++%></td>
                <td nowrap class="2-td2-left"><input type="text" name="xspzh" value='<ttsoft:write name="item" property="xspzh"/>' class="inputnoborder" style="color:#3C5564" readonly size="8" tabindex="-1"></td>
                <td nowrap class="2-td2-left"><input type="text" name="dwmc" value='<ttsoft:write name="item" property="dwmc"/>' class="inputnoborder" style="color:#3C5564" readonly size="60" tabindex="-1"></td>
                <td nowrap class="2-td2-left"><input type="text" name="spmzje" value='<ttsoft:write name="item" property="spmzje"/>' class="inputnoborder" style="color:#3C5564" readonly size="8" tabindex="-1"></td>
                <td nowrap class="2-td2-left"><input type="text" name="gpsl" value='<ttsoft:write name="item" property="gpsl"/>' class="inputnoborder" style="color:#3C5564" readonly size="5" tabindex="-1"></td>
                <td nowrap class="2-td2-center"><input type="text" name="je" value='<ttsoft:write name="item" property="je"/>' class="inputnoborder" style="color:#3C5564" readonly size="12" tabindex="-1"></td>
              </tr>
              </logic:iterate>
              <%if(pgNum==pgSum && pgNum!=0) {%>
              <tr>
                <td class="2-td2-left">合计</td>
                <td class="2-td2-left" colspan="3">&nbsp;</td>
                <td class="2-td2-left">&nbsp;<html:text property="gpslhj" styleClass="inputnoborder" style="color:#3C5564" readonly="true" size="10" /></td>
                <td class="2-td2-center">&nbsp;<html:text property="hjje" styleClass="inputnoborder" style="color:#3C5564" readonly="true" size="10" /></td>
              </tr>
              <%} else {%>
                <html:hidden property="gpslhj" />
                <html:hidden property="hjje" />
              <%}%>
            </table>
						<br>
            <table  class="table-99">
              <tr class="black9">
                <td align="right">第(<%=pgNum%>/<%=pgSum%>)页
                  <%if(pgNum > 1 ) {%>
                    <img src="<%=static_contextpath%>images/diyiye1.jpg" alt="第一页" id="first" onMouseOver="MM_swapImage('first','','<%=static_contextpath%>images/diyiye2.jpg',1)" onMouseOut="MM_swapImage('first','','<%=static_contextpath%>images/diyiye1.jpg',1)" onclick="goToPage('1')" style="cursor:hand">
                    <img src="<%=static_contextpath%>images/shangyiye1.jpg" alt="上一页" id="backward" onMouseOver="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye1.jpg',1)" onclick="goToPage('<%=pgNum-1%>')" style="cursor:hand">
                  <%}%>
                  <%if(pgNum < pgSum) {%>
                    <img src="<%=static_contextpath%>images/xaiyiye1.jpg" alt="下一页" id="forward" onMouseOver="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye1.jpg',1)" onclick="goToPage('<%=pgNum+1%>')" style="cursor:hand">
                    <img src="<%=static_contextpath%>images/zuimoye1.jpg" alt="最末页" id="last" onMouseOver="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye1.jpg',1)" onclick="goToPage('<%=pgSum%>')" style="cursor:hand">
                  <%}%>
                </td>
              </tr>
            </table>
            <table width="150" border="0" cellpadding="0" cellspacing="0" class="table-99">
              <tr>
                <div align="center">
									<input type="image" accesskey="z" src="<%=static_contextpath%>images/hzjks-z1.jpg" alt="汇总" id="huizong" onMouseOver="MM_swapImage('huizong','','<%=static_contextpath%>images/hzjks-z2.jpg',1)" onMouseOut="MM_swapImage('huizong','','<%=static_contextpath%>images/hzjks-z1.jpg',1)" onclick="doHzjks();return false;" style="cursor:hand">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="image" accesskey="g" src="<%=static_contextpath%>images/cxhz-g1.jpg" alt="撤消汇总" id="cxhz" onMouseOver="MM_swapImage('cxhz','','<%=static_contextpath%>images/cxhz-g2.jpg',1)" onMouseOut="MM_swapImage('cxhz','','<%=static_contextpath%>images/cxhz-g1.jpg',1)" onclick="doCxjks();return false;" style="cursor:hand">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="image" accesskey="c" onClick="tuichu();return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand">
                </div>
              </tr>
            </table>
						<br>
          </td>
        </tr>
      </table>
<%@ include file="./include/footer.jsp"%>
     </td>
    </tr>
</table>
<br>
</html:form>

</body>
<script language="JavaScript">

function doLoad(){
  if(document.all.theFile.value!=""){
    if(window.confirm("即将导入新的数据,请确认")){
      document.all.yhsxsdrForm.encoding = "multipart/form-data";
      doSubmitForm("Load");
    }
  }
  else alert("请先通过『浏览』找到要导入的文件！");
}

function doHzjks() {
  var yhsrow = document.all("yhsRow");
  if( (yhsrow && yhsrow.length) || yhsrow) {
    if(window.confirm("即将汇总导入的数据，请确认")){
      divFile.innerHTML = "";
      document.all.yhsxsdrForm.encoding = "application/x-www-form-urlencoded";
      doSubmitForm("Hzjks");
    }
  }
  else alert("请先导入数据文件再汇总！")
}

function doCxjks() {
  divFile.innerHTML = "";
  document.all.yhsxsdrForm.encoding = "application/x-www-form-urlencoded";
  doSubmitForm('Cxjks')
}

function goToPage(pgNum) {
  document.all.pgNum.value = pgNum;
  divFile.innerHTML = "";
  document.all.yhsxsdrForm.encoding = "application/x-www-form-urlencoded";
  doSubmitForm('Gotopage');
}

function fnOnLoad()
{
  document.forms[0].theFile.focus();
}
</script>
</html:html>
