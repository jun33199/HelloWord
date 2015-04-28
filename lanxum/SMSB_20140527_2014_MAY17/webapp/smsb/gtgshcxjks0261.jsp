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
<title>撤销缴款书</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/DTable.js"></script>
<script language=JavaScript src="../js/zhsb.js"></script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="./include/header.jsp"%>


<html:form action="/webapp/smsb/gtgshCxjksAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="lrrdm" />
<html:hidden property="headJkpzh" />
<html:hidden property="headJsjdm" />

<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9' height="300"> 
 <tr>
    <td valign="top" height="300"> <br>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1"  colspan="2">撤销缴款书</td>
        </tr>
        <tr>
          <td class="1-td2"  colspan="2"> <br>
            <!--<table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td nowrap class="2-td2-t-right">缴款凭单号</td>
                <td nowrap class="2-td2-t-left">&nbsp;</td>
              </tr>
            </table>
            <br>-->
			<table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0" id="SBJKZB_GTGSH" >
			<ttsoft:smsbtable form="gtgshCxjksForm" property="dataList" tableId="SBJKZB_GTGSH" hasTitle="true"/>
             </table>

             <table border="0" width="100%">
              <tr> 
                <td width="27%">&nbsp;</td>
                <!--<td width="8%"><img onclick="doSubmitForm('Delete')" style="cursor:hand" onMouseOver="MM_swapImage('sc1','','<%=static_contextpath%>images/shanchu2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="删除" id="sc1" src="<%=static_contextpath%>images/shanchu1.jpg" width="79" border="0" height="22"></td>-->
                <td width="9%"><input type="image" accesskey="f" tabIndex="-1" onclick="doGoBack('Back');return false;" style="cursor:hand"  onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="返回" id="fh1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" border="0"></td>
                <td width="9%"><input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22"></td>
                <td width="27%">&nbsp;</td>
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

</html:form>



<%@ include file="./include/footer.jsp"%>
	</td>
 </tr>
</table>
</body>
<script language=JavaScript >
var SBJKZB_GTGSH_list=new DTable(SBJKZB_GTGSH,jsArr_SBJKZB_GTGSH);
SBJKZB_GTGSH_list.tableTail=1;
function del(pzh,dm){
	if (pzh!="" && dm!=""){
		document.gtgshCxjksForm.headJkpzh.value=pzh;
		document.gtgshCxjksForm.headJsjdm.value=dm;
		//alert(document.gtgshCxjksForm.headJkpzh.value);
		//alert(document.gtgshCxjksForm.headJsjdm.value);
		if (confirm("该操作将删除页面中一行的数据,且不能自动恢复,请确认")){
			document.gtgshCxjksForm.target="";
			document.gtgshCxjksForm.actionType.value="Delete";
			document.gtgshCxjksForm.submit();
			//doSubmitForm('Delete');
		}
	}
}

function printFunc(pzh,dm){
	if (pzh!="" && dm!=""){
		document.gtgshCxjksForm.headJkpzh.value=pzh;
		document.gtgshCxjksForm.headJsjdm.value=dm;
		document.gtgshCxjksForm.target="blank";
		//alert(document.gtgshCxjksForm.headJkpzh.value);
		//alert(document.gtgshCxjksForm.headJsjdm.value);
		doSubmitForm('Print');
	}
}

function doGoBack(){
	document.gtgshCxjksForm.target="";
	doSubmitForm('Back');
}

</script>
</html:html>