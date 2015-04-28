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
<meta http-equiv="Content-Language" content="zh-cn">
<link href="../css/beijing.css" rel="stylesheet" type="text/css">
<title>已阅读清单</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/compute.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
</head>

<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
<%@ include file="./include/header.jsp"%>

<html:form action="/webapp/smsb/gzsxfkcxAction.do" method="POST" >
<html:hidden property="actionType" />
<html:hidden property="nsrmc" />
<html:hidden property="pgNum" />
<html:hidden property="jsjdm" />
<html:hidden property="gzqsrq" />
<html:hidden property="gzjzrq" />


<table width="780" height="302" border="0" cellpadding="0" cellspacing="0"  align="center">
  <tr>
    <td>
    	<br>
      	<table align="center" cellspacing="0"  class="table-99"  width="98%">
	       <tr>
	          <td class="1-td1" ><bean:write name="gzsxwhForm" property="nsrmc"/>对告知事项浏览情况汇总（共<bean:write name="gzsxwhForm" property="jlcount"/>条！）</td>
	        </tr>
	        <tr>
	          <td class="1-td2" >
			  	  <br>
            <table id="gzsxlistTable" border="1" cellpadding="0" cellspacing="0" class="table-99" height="1" width="99%">
              <tr>
 
                <td width="16%" class="2-td1-left" height="1">告知事项编号</td>
                <td width="16%" class="2-td1-left" height="1">告知内容标题</td>
                <td width="20%" class="2-td1-left" height="1">告知事项目的单位</td>
                <td width="16%" class="2-td1-left" height="1">告知发出日期</td>
                <td width="16%" class="2-td1-left" height="1">阅读情况</td>
                <td width="16%" class="2-td1-center" height="1">反馈情况</td>

              </tr>
      
          <logic:iterate id="data" indexId="index" length="length" name="gzsxwhForm" property="jsjdmgzsxlilst" type="com.ttsoft.bjtax.smsb.model.client.swjgtolist">
               <tr>
                <td width="16%" class="2-td2-left" height="23"><ttsoft:write name="data" property="gzsx_id"/>&nbsp</td>
                <td width="16%" class="2-td2-left" height="23"><a href=gzsxfkcxAction.do?actionType=getgzsxnr&gzsx_id=<ttsoft:write name="data" property="gzsx_id"/> target=_blank><ttsoft:write name="data" property="gzsxnrbt"/></a>&nbsp</td>
                <td width="20%" class="2-td2-left" height="23"><ttsoft:write name="data" property="swjgzzjg"/>&nbsp</td>
                <td width="16%" class="2-td2-left" height="23"><ttsoft:write name="data" property="gzsxfcrq"/>&nbsp</td>
                <td width="16%" class="2-td2-left" height="23"><ttsoft:write name="data" property="ydsj"/>&nbsp</td>
                <td width="16%" class="2-td2-left" height="23"><a href=gzsxfkcxAction.do?actionType=getfknr&fknr=<ttsoft:write name="data" property="fknr"/> target=_blank>查看反馈</a>&nbsp</td>
                </tr>
              </logic:iterate>
            </table>
            <br>
            <table border="0" width="100%">
              <tr>
              
              <td width="5%">&nbsp; </td>
                <td width="17%"><input name="I2" type="image" accesskey="e" value="导出Excel"  onClick="exportExcel();return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/b-dcexcelb2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="导出Excel" src="<%=static_contextpath%>images/b-dcexcelb1.jpg" width="100" height="25" id="daochu"></td>
                <td width="40%"><input name="I3" type="image" accesskey="f" value="返回"  onClick='goEncBack();return false;' border=0 onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="返回" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" id="fanhui"> </td>
                <td width="17%"><input name="I2" type="image" accesskey="s" value="首页"  onClick="doQuery(1);return false;" alt="首页" src="<%=static_contextpath%>images/diyiye2.jpg" width="79" height="22" id="shouye"></td>
                <td width="17%"><input name="I3" type="image" accesskey="f" value="上一页"  onClick='doQuery(0);return false;' border=0  alt="上一页" src="<%=static_contextpath%>images/shangyiye2.jpg" width="79" height="22" id="shangy"> </td>
                <td width="17%"><input name="I2" type="image" accesskey="s" value="下一页"  onClick="doQuery(-1);return false;"  alt="下一页" src="<%=static_contextpath%>images/xaiyiye2.jpg" width="79" height="22" id="xiay"></td>
                <td width="17%"><input name="I3" type="image" accesskey="f" value="末页"  onClick="doQuery(-2);return false;" border=0  alt="末页" src="<%=static_contextpath%>images/zuimoye2.jpg" width="79" height="22" id="moy"> </td>
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
function goEncBack(){
	//然后重设encoding 

	document.all.actionType.value="Return";
    
   document.forms[0].submit();
}
 function exportExcel(){
 //alert("daochu ");
     document.all.actionType.value="ExportExcel1";
     document.all.nsrmc.value='<bean:write name="gzsxwhForm" property="nsrmc"/>';
     document.forms[0].submit();
   }
   function doQuery(num) {
     var pageIndex = parseInt('<bean:write name="gzsxwhForm" property="pgNum"/>');
      //alert(pageIndex);
      var pageNum = parseInt('<bean:write name="gzsxwhForm"  property="pgSum"/>');
       // alert(pageNum);
      if (num == 1) {
       document.all.pgNum.value="1";
      }else if (num == 0) {
        if (pageIndex > 1) {
         document.all.pgNum.value=pageIndex-1;
        }else{
         document.all.pgNum.value="1"
        }
      }else if (num == -1) {
        if (pageIndex <= pageNum-1) {
         document.all.pgNum.value=pageIndex+1;
        }else{
          document.all.pgNum.value=pageNum;
        }
      } else if (num ==-2){
        document.all.pgNum.value=pageNum;
      } 
      /*else {
         if ((num > pageNum)||(num < 1)) {
           alert("页面超出范围！");
           document.form1.pageIndex.value=pageNum;
         } else {
           document.form1.pageIndex.value=num;
         }
      }*/
      document.all.jsjdm.value='<bean:write name="gzsxwhForm" property="jsjdm"/>';
      document.all.gzqsrq.value='<bean:write name="gzsxwhForm" property="gzqsrq"/>';
      document.all.gzjzrq.value='<bean:write name="gzsxwhForm" property="gzjzrq"/>';
     
    document.all.actionType.value="query";
    
   document.forms[0].submit();
   }
</script>
</html:html>
