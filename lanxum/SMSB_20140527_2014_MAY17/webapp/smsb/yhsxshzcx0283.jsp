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
<title>撤销印花税代售单位销售汇总</title>
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
<html:form action="/webapp/smsb/yhsxshzcxAction.do" method="POST">
<input type=hidden value="Show" name="actionType">
<html:hidden property="cxjkpzh" />
<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">撤销印花税代售单位销售汇总</td>
        </tr>
        <tr>
          <td class="1-td2">&nbsp;&nbsp; <br>
            <table cellspacing=0 class="table-99">
              <tr class="black9">
                <td colspan="2"><div align="right">金额单位：人民币元&nbsp;&nbsp;</div></td>
              </tr>
            </table>
            <br>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td nowrap class="3-td1-left">代售单位计算机代码</td>
                <td nowrap class="3-td1-left"><html:text property="dsjsjdm" size="8" onchange="doQuery();return false;"  onkeydown="if(window.event.keyCode==13) event.keyCode=9;"/></td>
                <td nowrap class="3-td1-left">代售单位名称</td>
                <td nowrap class="3-td1-left"><html:text property="dsdwmc" styleClass="inputnoborder" style="color:#3C5564" readonly="true" size="40" tabindex="-1" /></td>
                <td nowrap class="3-td1-centen">代售单位联系电话</td>
                <td nowrap class="3-td1-right"><html:text property="dsdwlxdh" styleClass="inputnoborder" style="color:#3C5564" readonly="true" size="15" tabindex="-1" /></td>
              </tr>
            </table>
            <br>
            <input type="image" accesskey="q" src="<%=static_contextpath%>images/cx-q1.jpg" alt="查询" id="cxhz" onMouseOver="MM_swapImage('cxhz','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImage('cxhz','','<%=static_contextpath%>images/cx-q1.jpg',1)" onclick="doQuery();return false;" style="cursor:hand;vertical-align:text-bottom">
            <br><br>
						<table width="100%" cellspacing=0 class="table-99">
              <tr>
                <td valign=top nowrap class="2-td1-left">序号</td>
                <td valign=top nowrap class="2-td1-left">缴款凭证号</td>
                <td valign=top nowrap class="2-td1-left">实缴金额</td>
								<td valign=top nowrap class="2-td1-center">&nbsp;</td>
              </tr>
              <bean:define id="jkslist" name="yhsxshzcxForm" property="dataList"/>
              <% int jksIndex = 1;%>
              <logic:iterate indexId="index" name="jkslist" id="itemMap">
                <bean:define id="item" name="itemMap"/>
                <tr id="jksRow">
                   <td nowrap class="2-td2-left"><%=jksIndex%></td>
                   <td nowrap class="2-td2-left"><input type="text" name="jkpzh" value='<ttsoft:write name="item" property="jkpzh"/>' class="inputnoborder" style="color:#3C5564" readonly size="18" tabindex="-1"></td>
                   <td nowrap class="2-td2-left"><input type="text" name="sjje" value='<ttsoft:write name="item" property="sjje"/>' class="inputnoborder" style="color:#3C5564" readonly size="10" tabindex="-1"></td>
                   <td nowrap class="2-td2-center"><a href="#" onclick="doCsjks('<%=jksIndex++%>');return false;"/>撤销</a></td>
                </tr>
              </logic:iterate>
            </table>
						<br>
            <table>
						<tr><td align="center">
				<input type="image" accesskey="f" src="<%=static_contextpath%>images/fh-f1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fh-f2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fh-f1.jpg'" alt="返回" onclick="doSubmitForm('Turnback');return false;" style="cursor:hand">
		</td></tr>
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
//得到企业名称
function doGetQymcSubmit(){
    strJsjdm = document.forms[0].dsjsjdm.value+ "";
    if( trim(strJsjdm).length!=8 && trim(strJsjdm).length!=7 ) {
      document.forms[0].dsdwmc.value="";
      alert("计算机代码格式为8位代码")
      return false;
    }
  if(doSubmitForm('Reader')==false){
    return false;
  }
}

function doCsjks(lineNo) {
  var jksrow=document.all("jksRow");
  if(jksrow.length) {
    jksrow = jksrow[lineNo-1];
  }
  document.all.cxjkpzh.value=jksrow.all('jkpzh').value;
  if(window.confirm("即将撤销缴款书 "+document.all.cxjkpzh.value+" ，请确认")){
    doSubmitForm("Cxjks");
  }
}

function doQuery() {
  if(document.all.dsjsjdm.value!="")
    doSubmitForm('Query');
  else
    alert("请先输入要查询的 *代售单位计算机代码* ！");
}
function fnOnLoad()
{
  document.forms[0].dsjsjdm.focus();
}
</script>
</html:html>

