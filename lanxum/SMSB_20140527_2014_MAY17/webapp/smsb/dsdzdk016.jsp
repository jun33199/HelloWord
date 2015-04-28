<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.dsdzdk.web.DsdzdkForm" %>

<jsp:useBean type="com.ttsoft.bjtax.smsb.sbzl.dsdzdk.web.DsdzdkForm" scope="request" id="dsdzdkForm" />

<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">

<title>代征代扣代缴导入</title>

<script language="JavaScript" type="text/JavaScript" src="../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../js/smsb_common.js"></script>
<style type="text/css">
<!--
	@import url(../css/text.css);
-->
</style>

<script language=JavaScript>
//响应计算机代码的回车查询
function jsjdmQuery(){
	if(window.event.srcElement && window.event.srcElement.type && window.event.srcElement.type=="file") return;
 	if(window.event.keyCode==13) window.event.keyCode = 9;
}

function doQuery()
{
	if(trim(document.forms[0].jsjdm.value) == ""){
		alert("请输入纳税企业计算机代码！");
		return;
	}
	if(!isDate(document.forms[0].sbrq, "true")){
		return;
	}

	document.forms[0].nsrmc.value = "";
	document.forms[0].qylxdh.value = "";
        document.forms[0].target='_self';
	doSubmitForm("Query");
}

function doSave()
{
	if(!check_jsjdm()) return false;

	if(trim(document.forms[0].recordcount.value) == "0"){
		alert("没有数据可供汇总！");
		return;
	}
        document.forms[0].target='_self';
	doSubmitForm("Save");
}

function doInput()
{
	if(!check_jsjdm()) return false;
        document.forms[0].target='_self';
	doSubmitForm("Input");
}

function doUpload()
{
	if(!check_jsjdm()) return false;

    var theFile = document.forms[0].excelFile.value;
    if(theFile == null || theFile == "" ){
    	alert("请您选择导入的文件!");
		return false;
    }else{
		var arrfile = theFile.split(".");
		if(arrfile.length<2 || arrfile[1] != "xls"){
			alert("只能上传excel文件!");
			return false;
		}
	}
        document.forms[0].target='_self';
	doSubmitForm("Upload");
}

function doHzsbjkd()
{
	if(!check_jsjdm()) return false;

	if(trim(document.forms[0].recordcount.value) != "0"){
		if(!confirm("此操作将清除当前导入未汇总的数据，请确认!")){
			return false;
		}
	}
        document.forms[0].target='_self';
	doSubmitForm("Hzsbjkd");

}
function doHzsbjkdp()
{
	if(!check_jsjdm()) return false;

	if(trim(document.forms[0].recordcount.value) != "0"){
		if(!confirm("此操作将清除当前导入未汇总的数据，请确认!")){
			return false;
		}
	}
        document.forms[0].target='_self';
	doSubmitForm("Hzsbjkdp");

}
function check_jsjdm(){
	if(trim(document.forms[0].jsjdm.value) == "" || document.forms[0].nsrmc.value == ""){
		alert("请输入纳税企业计算机代码！");
		return false;
	}
        document.forms[0].target='_self';
	return true;
}

function goToPage(pgNum) {
  	document.all.pgNum.value = pgNum;
        document.forms[0].target='_self';
  	doSubmitForm('Gotopage');
}
function showhj(){
	document.forms[0].jsjdm.focus();
}

</script>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onLoad="showhj()">
<%@ include file="include/header.jsp" %>
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" onkeydown="jsjdmQuery()">
<tr>
<td align="center" colspan=2>



<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
<tr>
<td valign="top"><br>

	<table align="center" cellspacing="0" class="table-99" border="0">
	<tr class="black9">
		<td class="1-td1">代售代征代扣税款申报表</td>
	</tr>
	<tr>
		<td valign="top" class="1-td2"><br>

		<html:form method="POST" action="/webapp/smsb/dsdzdkAction.do"  enctype="multipart/form-data">

		<html:hidden property="actionType" />
		<html:hidden property="nsrmc" />
		<html:hidden property="qylxdh" />
		<html:hidden property="cjrq"/>
		<html:hidden property="lrrq"/>
		<html:hidden property="lrr"/>
		<html:hidden property="fsdm"/>
		<html:hidden property="swjgzzjgdm"/>
		<html:hidden property="sjly"/>
		<html:hidden property="iszhsb"/>
		<html:hidden property="qxdm"/>

    	<html:hidden property="pgNum" />
    	<html:hidden property="length" />
    	<html:hidden property="pgSum" />

		<input type="hidden" name="recordcount" value="<%=dsdzdkForm.getMxDataList().size() %>" />
		<table width="100%" cellspacing=0 class="table-99">
	   	<tr class="black9">
			<td align="center" nowrap>
			<div align="left">申报日期：&nbsp;
				<html:text property="sbrq"  size="8" maxLength="8" onchange="getStartEndDate(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,1) " />
			</div>
			<td align="center" nowrap>
			<div align="left">税款所属日期：&nbsp;
				<!--<html:text property="skssksrq"  size="8" maxLength="8" onchange="compareDate('skssksrq','skssjsrq',0,this)"  />至&nbsp;
				<html:text property="skssjsrq"  size="8" maxLength="8" onchange="compareDate('skssksrq','skssjsrq',0,this)"  />-->
                               <html:text property="skssksrq" size="11" maxlength="8" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,0,0)"/>至
                               <html:text property="skssjsrq" size="11" maxlength="8" onchange="compareSkssrqDate('sbrq','skssksrq','skssjsrq',0,this,0,1)"/>
			</div>
			</td>
			<td align="right" nowrap>金额单位：人民币元</td>
	  	</tr>
	 	</table>

		<table width="100%" cellspacing=0 class="table-99">
  		<tr>
    		<td width="10%" nowrap class="2-td2-t-left">计算机代码</td>
    		<td width="15%" nowrap class="2-td2-t-left">&nbsp;
    			<html:text property="jsjdm" size="8" maxLength="8" onchange="doQuery();return false;"    />
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

		<!-- contents at here -->

		<table width="100%" cellspacing=0 class="table-99">
      	<tr>
        	<td valign=top nowrap class="2-td1-left">序号</td>
        	<td valign=top nowrap class="2-td1-left">税（费）种类</td>
        	<td valign=top nowrap class="2-td1-left">纳税项目</td>
        	<td valign=top nowrap class="2-td1-left">税率(单位税额)</td>
        	<td valign=top nowrap class="2-td1-left">被代征人名称 </td>
        	<td valign=top nowrap class="2-td1-left">计税金额</td>
        	<td valign=top nowrap class="2-td1-left">实缴税额</td>
        	<td valign=top nowrap class="2-td1-left">完税证号</td>
        	<td valign=top nowrap class="2-td1-center">票证种类代码</td>
      	</tr>
        <%
          DsdzdkForm form = (DsdzdkForm)request.getAttribute("dsdzdkForm");
          int pgLength = form.getLength();
          int pgSum = form.getPgSum();
          int pgNum = form.getPgNum();
          int start = pgLength*(pgNum-1);
        %>
      	<% int sdIndex=start + 1; %>
		<logic:iterate id="item" name="dsdzdkForm" property="mxDataList" scope="request" offset="<%=String.valueOf(start)%>" length="<%=String.valueOf(pgLength)%>">
      	<tr>
        	<td nowrap class="2-td2-left"> <%=sdIndex++ %> </td>
        	<td  nowrap class="2-td2-left">&nbsp;
        		<ttsoft:write name="item" property="szmc"/>
         	</td>
        	<td nowrap class="2-td2-left">&nbsp;
        		<ttsoft:write name="item" property="szsmmc"/>

        	</td>
        	<td nowrap class="2-td2-left">&nbsp;
        		<ttsoft:write name="item" property="sl"/>

        	</td>
        	<td nowrap class="2-td2-left">&nbsp;
        		<ttsoft:write name="item" property="bdzrmc"/>

        	</td>
        	<td nowrap valign=top class="2-td2-left">&nbsp;
        		<ttsoft:write name="item" property="jsje"/>

        	</td>
        	<td nowrap class="2-td2-left">&nbsp;
        		<ttsoft:write name="item" property="sjse"/>

        	</td>
        	<td nowrap class="2-td2-left">&nbsp;
        		<ttsoft:write name="item" property="wszh"/>

        	</td>
        	<td nowrap class="2-td2-center">&nbsp;
        		<ttsoft:write name="item" property="pzzldm"/>

        	</td>
      	</tr>
		</logic:iterate>
<%if(pgNum==pgSum && pgNum!=0) {%>
      	<tr>
        	<td class="2-td2-left">合计</td>
        	<td valign=top class="2-td2-left">&nbsp;</td>
        	<td valign=top class="2-td2-left">&nbsp;</td>
        	<td valign=top class="2-td2-left">&nbsp;</td>
        	<td valign=top class="2-td2-left">&nbsp;</td>
        	<td valign=top class="2-td2-left">&nbsp;
        		<bean:write name="dsdzdkForm" property="jsjehj"/>
        	</td>
        	<td valign=top class="2-td2-left">&nbsp;
        		<bean:write name="dsdzdkForm" property="sjsehj"/>
        	</td>
        	<td valign=top class="2-td2-left">&nbsp;</td>
        	<td valign=top class="2-td2-center">&nbsp;</td>
      	</tr>  <%}%>
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


<logic:iterate id="item" name="dsdzdkForm" property="mxDataList" scope="request" >

        		<input type="hidden" name="szdm"
        			value="<ttsoft:write name="item" property="szdm"/>" />
        		<input type="hidden" name="szmc"
        			value="<ttsoft:write name="item" property="szmc"/>" />

        		<input type="hidden" name="szsmdm"
        			value="<ttsoft:write name="item" property="szsmdm"/>" />
        		<input type="hidden" name="szsmmc"
        			value="<ttsoft:write name="item" property="szsmmc"/>" />

        		<input type="hidden" name="sl"
        			value="<ttsoft:write name="item" property="sl"/>" />

        		<input type="hidden" name="bdzrmc"
        			value="<ttsoft:write name="item" property="bdzrmc"/>" />

        		<input type="hidden" name="jsje"
        			value="<ttsoft:write name="item" property="jsje"/>" />

        		<input type="hidden" name="sjse"
        			value="<ttsoft:write name="item" property="sjse"/>" />

        		<input type="hidden" name="wszh"
        			value="<ttsoft:write name="item" property="wszh"/>" />

        		<input type="hidden" name="pzzldm"
        			value="<ttsoft:write name="item" property="pzzldm"/>" />

		</logic:iterate>

      <br>
    	请选择上传的文件:	<html:file property="excelFile" onkeydown="if(window.event.keyCode==13){window.event.returnValue=false;}" /><br>
		<div align="center">
			 <input type="image" accesskey="i" tabIndex="-1" style="cursor:hand"
				onClick="doUpload();return false;"
				onMouseOver="MM_swapImage('shangchuan','','<%=static_contextpath%>images/dr-i2.jpg ',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/dr-i1.jpg " name="Image13" width="79" height="22" border="0" id='shangchuan' >

		</div><br><br>

		<div align="center">
			<input type="image" accesskey="z" tabIndex="-1" style="cursor:hand"
				onClick="doSave();return false;"
				onMouseOver="MM_swapImage('huizong','','<%=static_contextpath%>images/hzjks-z2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				src="<%=static_contextpath%>images/hzjks-z1.jpg" name="Image13" width="116" height="22" border="0" id='huizong'>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="image" accesskey="p" tabIndex="-1" style="cursor:hand"
				onClick="doHzsbjkdp();return false;"
				onMouseOver="MM_swapImage('p','','<%=static_contextpath%>images/dy-p2.jpg ',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/dy-p1.jpg " name="Image13" width="79" height="22" border="0" id='p'>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="image" accesskey="g" tabIndex="-1" style="cursor:hand"
				onClick="doHzsbjkd();return false;"
				onMouseOver="MM_swapImage('a','','<%=static_contextpath%>images/cxhz-g2.jpg ',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/cxhz-g1.jpg " name="Image13" width="79" height="22" border="0" id='a'>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="image" accesskey="n" tabIndex="-1"  style="cursor:hand"
				onClick="doInput();return false;"
				onMouseOver="MM_swapImage('lr','','<%=static_contextpath%>images/lr-n2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				src="<%=static_contextpath%>images/lr-n1.jpg" name="Image13" width="79" height="22" border="0" id='lr'>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="image" accesskey="c" tabIndex="-1"  style="cursor:hand"
			    onClick="tuichu();return false;"
				onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/tc-c2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				 src="<%=static_contextpath%>images/tc-c1.jpg" name="Image13" width="79" height="22" border="0" id='fanhui'>
			</a>
		</div><br><br>

		</html:form>

   		</td>
  	</tr>
  	</table>

	</td>
</tr>
</table><br>

<%@ include file="include/footer.jsp" %>

</td>
</tr>
</table>
<script language="javascript"> 
/****如果该纳税人为非正常户，则显示提示信息****/
/****20050817 Huxiaofeng****/
var nsrzt = <ttsoft:write name="dsdzdkForm" property="nsrzt" filter="false"/>;
checkNsrzt();
/**************end *******/
</script>
</body>
</html:html>