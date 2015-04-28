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
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

<title>代售代征代扣税款申报表</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">

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

function inputCheck_zs(obj){
	return isNum(obj, 0, null, 1, 13, 2);
}
function inputCheck_cxs(obj){
	return isNum(obj, null, 1, 1, 6, 4);
}
function inputCheck_ff(obj){
	return isNum(obj, 0, null, 1, 13, 2);
}


function jsjdmQuery(){
      if(event.keyCode==13) event.keyCode = 9;
}



function doReturn(){
	var rowLength = DSDZDKMX_list.doGetRowLength();
	if(rowLength > 2){
		if(!confirm("当前录入的数据没有汇总，将会丢失！确认要返回吗？"))
			return false;
	}
        document.forms[0].target='_self';
	doSubmitForm('Query');
        return  false;
}
function wszh(a){
var oRow = getObjRow(a);
valuess=oRow.all("wszh").value
if(valuess.length<=20)
{}
else
{alert("本行的完税种号码长度不能超过4位");
 a.value="";
}
}
function pzzldms(a){
var oRow = getObjRow(a);
valuess=oRow.all("pzzldm").value
if(valuess.length<=4)
{}
else
{alert("本行的票证种类代码长度不能超过4位");
 a.value="";
}
}
function doSave(){
	var rowLength = DSDZDKMX_list.doGetRowLength();
        var k;
        var sl;
        var bdzrmc;
        var wszh;
        var pzzldm;
        var sbhzdh;
        var jsje;
        var sjse;
        var szsmdm;
        var ok=1;
	if(rowLength <= 2){
		alert("没有数据可供汇总!");
		return false;
	}
          if(document.forms[0].szsmdm.length){
          for(i = 0; i < document.forms[0].szsmdm.length; i++){
          szsmdm=document.forms[0].szsmdm[i].value;
          sl=document.forms[0].sl[i].value;
          bdzrmc=document.forms[0].bdzrmc[i].value;
          wszh=document.forms[0].wszh[i].value;
          pzzldm=document.forms[0].pzzldm[i].value;
          sbhzdh=document.forms[0].sbhzdh[i].value;
          jsje=document.forms[0].jsje[i].value;
          sjse=document.forms[0].sjse[i].value;

           if(szsmdm==""||szsmdm==null)
          {  k=i+1;
           alert("代售代征代扣税款表的第"+k+"行的代售代征代扣税款税种税目没有填写，请填写");
           ok=0;
          }
           if(sl==""||sl==null)
          {  k=i+1;
           alert("代售代征代扣税款表的第"+k+"行的代售代征代扣税款税率没有填写，请填写");
           ok=0;
          }
          if(bdzrmc==""||bdzrmc==null)
          {  k=i+1;
           alert("代售代征代扣税款表的第"+k+"行的代售代征代扣税款被代征人名称没有填写，请填写");
           ok=0;
          }
          if(wszh==""||wszh==null)
          {  k=i+1;
           alert("代售代征代扣税款表的第"+k+"行的代售代征代扣税款完税证号没有填写，请填写");
           ok=0;
          }
          if(pzzldm==""||pzzldm==null)
          {  k=i+1;
           alert("代售代征代扣税款表的第"+k+"行的代售代征代扣税款票证种类代码没有填写，请填写");
           ok=0;
          }

          if(jsje==""||jsje==null)
          {  k=i+1;
           alert("代售代征代扣税款表的第"+k+"行的代售代征代扣税款计税金额没有填写，请填写");
           ok=0;
          }
          if(sjse==""||sjse==null)
          {  k=i+1;
           alert("代售代征代扣税款表的第"+k+"行的代售代征代扣税款实缴税额没有填写，请填写");
           ok=0;
          }


          }
          }
          else{
           szsmdm=document.forms[0].szsmdm.value;
          sl=document.forms[0].sl.value;
          bdzrmc=document.forms[0].bdzrmc.value;
          wszh=document.forms[0].wszh.value;
          pzzldm=document.forms[0].pzzldm.value;
          sbhzdh=document.forms[0].sbhzdh.value;
          jsje=document.forms[0].jsje.value;
          sjse=document.forms[0].sjse.value;
           if(szsmdm==""||szsmdm==null)
          {
           alert("代售代征代扣税款表的第1行的代售代征代扣税款税种税目没有填写，请填写");
           ok=0;
          }
           if(sl==""||sl==null)
          {
           alert("代售代征代扣税款表的第1行的代售代征代扣税款税率没有填写，请填写");
           ok=0;
          }
          if(bdzrmc==""||bdzrmc==null)
          {
           alert("代售代征代扣税款表的第1行的代售代征代扣税款被代征人名称没有填写，请填写");
           ok=0;
          }
          if(wszh==""||wszh==null)
          {
           alert("代售代征代扣税款表的第1行的代售代征代扣税款完税证号没有填写，请填写");
           ok=0;
          }
          if(pzzldm==""||pzzldm==null)
          {
           alert("代售代征代扣税款表的第1行的代售代征代扣税款票证种类代码没有填写，请填写");
           ok=0;
          }

          if(jsje==""||jsje==null)
          {
           alert("代售代征代扣税款表的第1行的代售代征代扣税款计税金额没有填写，请填写");
           ok=0;
          }
          if(sjse==""||sjse==null)
          {
           alert("代售代征代扣税款表的第1行的代售代征代扣税款实缴税额没有填写，请填写");
           ok=0;
          }
          }
	 if(ok==1)
	{document.forms[0].target='_self';doSubmitForm("Save");
         return false;
        }else
        {return false; }
}



</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">

<%@ include file="include/header.jsp" %>

<html:form action="/webapp/smsb/dsdzdkAction.do" method="POST">
<html:hidden property="actionType"/>
<html:hidden property="cjrq"/>
<html:hidden property="lrrq"/>
<html:hidden property="lrr"/>
<html:hidden property="fsdm"/>
<html:hidden property="swjgzzjgdm"/>
<html:hidden property="qylxdh"/>
<html:hidden property="sjly"/>
<html:hidden property="pzzl"/>
<html:hidden property="qxdm"/>

<table width="100%" border="0" cellpadding="0" cellspacing="0" onkeydown="jsjdmQuery()">
  <tr>
    <td valign="top"> <br>
 		<table align="center" cellspacing="0" class="table-99" border="0">
        <tr class="black9">
          <td class="1-td1">代售代征代扣税款申报表</td>
        </tr>
        <tr>
          <td valign="top" class="1-td2"><br>

		<table width="100%" cellspacing=0 class="table-99">
	   	<tr class="black9">
			<td align="center" nowrap>
			<div align="left">录入日期：&nbsp;
				<input type="text" name="sbrq" value="<ttsoft:write name="dsdzdkForm" property="sbrq"/>" size="8" maxLength="8" onchange="getStartEndDate(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,1) "  class="inputnoborder" readonly />
			</div>
			<td align="center" nowrap>
			<div align="left">税款所属日期：&nbsp;
				<input type="text" name="skssksrq"  value="<ttsoft:write name="dsdzdkForm" property="skssksrq"/>" size="8" maxLength="8" onchange="compareDate('skssksrq','skssjsrq',0,this)"  class="inputnoborder" readonly/>至&nbsp;
				<input type="text" name="skssjsrq" value="<ttsoft:write name="dsdzdkForm" property="skssjsrq"/>"  size="8" maxLength="8" onchange="compareDate('skssksrq','skssjsrq',0,this)"  class="inputnoborder" readonly/>
			</div>
			</td>
			<td align="right" nowrap>金额单位：人民币元</td>
	  	</tr>
	 	</table>

		<table width="100%" cellspacing=0 class="table-99">
  		<tr>
    		<td width="10%" nowrap class="2-td2-t-left">计算机代码</td>
    		<td width="15%" nowrap class="2-td2-t-left">&nbsp;
    			<input type="text" name="jsjdm" value="<ttsoft:write name="dsdzdkForm" property="jsjdm"/>" size="8" maxLength="8" onchange="doQuery()"  onKeyDown="jsjdmQuery()"  class="inputnoborder" readonly tabIndex="-1"/>
    		</td>
    		<td width="15%" nowrap class="2-td2-t-left">单位名称(盖章)</td>
    		<td width="35%" nowrap class="2-td2-t-left">&nbsp;
    			<bean:write name="dsdzdkForm" property="nsrmc"/>
    		</td>
    		<td width="10%" nowrap class="2-td2-t-left">联系电话</td>
    		<td width="15%" nowrap class="2-td2-t-center">&nbsp;
    			<bean:write name="dsdzdkForm" property="qylxdh"/>
    		</td>
  		</tr>
		</table><br>

            <table width="100%"  cellspacing=0 class="table-99" id="DSDZDKMX" onkeydown='return dokeydown(this.id,"szsmdm")'  onmouseup='return dokeyUp(this.id)'>
                  	<ttsoft:smsbtable form="dsdzdkForm" property="dataList" tableId="DSDZDKMX" hasTitle="true"/>
            		<DIV id=divSfTemp></DIV>
            <tr>
                  	<td colspan="7" class="2-td2-left" ><div align="right">合计&nbsp;&nbsp;</div></td>
                  	<td class="2-td2-left">
						<div align="center"><input type="textfield" name="jshj" size="12" class="inbright" readonly></div>
					</div>
					<td class="2-td2-center">
						<div align="center"><input type="textfield" name="sjhj" size="12" class="inbright" readonly></div>

            </tr>
            </table>
            <br>
             <input type="image" accesskey="a" tabIndex="-1" src="<%=static_contextpath%>images/zj-a1.jpg" onclick="javascript:addRow('DSDZDKMX'); return false;" alt="增加" name="Image2" border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/zj-a2.jpg';" onMouseOut="this.src ='<%=static_contextpath%>images/zj-a1.jpg'" width="79" height="22">
              &nbsp;&nbsp;&nbsp;&nbsp;
             <input type="image" accesskey="d" tabIndex="-1" onClick="javascript:deleteRow('DSDZDKMX',null); return false;"  onMouseOver="MM_swapImage('sc2','','<%=static_contextpath%>images/sc-d2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="删除" id="sc2" src="<%=static_contextpath%>images/sc-d1.jpg" width="79" height="22">
              &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="image" accesskey="z"  tabIndex="-1" onClick="doSave();return false;" src="<%=static_contextpath%>images/hzjks-z1.jpg " alt="汇总" name="Image2" border="0" id="Image2" onMouseOver="this.src ='<%=static_contextpath%>images/hzjks-z2.jpg '" onMouseOut="this.src ='<%=static_contextpath%>images/hzjks-z1.jpg'" width="116" height="22">
              &nbsp;&nbsp;&nbsp;&nbsp;
			<input type="image" accesskey="f" tabIndex="-1"   style="cursor:hand"
			    onClick="doReturn();return false;"
				onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fh-f2.jpg ',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/fh-f1.jpg" name="Image13" width="79" height="22" border="0" id='fanhui'></div><br> <br>
            </td>
        </tr>
      </table>

		<div id="szsmdm_epodDateLayer" style="position: absolute; width: 142; height: 166; z-index: 9998; display: none" onclick="this.style.display='none'">
<select id=sel size="11" onclick='selectClick("szsmdm","DSDZDKMX")' onkeyup='selectMove("szsmdm","DSDZDKMX")'  onfocusout='selectClick("szsmdm","DSDZDKMX")'>
			</select>
<INPUT TYPE="hidden" NAME="szsmdm_focus">
		</div>
      <br></td>
    </tr>
</table>
</html:form>
<%@ include file="./include/footer.jsp"%>
</body>

<script language="javascript">

var DSDZDKMX_list = new DTable(DSDZDKMX,jsArr_DSDZDKMX);
DSDZDKMX_list.tableTail=1;
<ttsoft:write name="dsdzdkForm" property="scriptStr" filter="false"/>

</script>
</html:html>