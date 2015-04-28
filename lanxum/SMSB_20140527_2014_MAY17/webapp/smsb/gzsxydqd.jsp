<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>


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
<script language="JavaScript">
function exportydqdExcel(){
  document.all.gzsxnrbt.value='已阅读：'+'<bean:write name="gzsxwhForm" property="gzsxnrbt"/>'+'的清单.';
  document.all.actionType.value="exportydqdExcel";
  document.forms[0].submit();
}
function doQuery(num) {
     var pageIndex = parseInt(document.all.pgNum.value);
      var pageNum = parseInt(document.all.pgSum.value);
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
    document.all.actionType.value="getydqd";
    
   document.forms[0].submit();
   }
   function loadform(){
        var ydcs = '<bean:write name="gzsxwhForm" property="ydcs"/>';
        document.all.ydcs.value = ydcs;
       }
</script>
</head>

<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="loadform()">
<%@ include file="./include/header.jsp"%>

<html:form action="/webapp/smsb/gzsxfkcxAction.do" method="POST" >
<html:hidden property="actionType" />
<html:hidden property="gzsxnrbt" />
<html:hidden property="pgNum" />
<html:hidden property="pgSum" />
<html:hidden property="ydcs" />
<table width="780" height="302" border="0" cellpadding="0" cellspacing="0"  align="center">
  <tr>
    <td>
    	<br>
      	<table align="center" cellspacing="0"  class="table-99"  width="98%">
	       <tr>
	          <td class="1-td1" >已阅读："<bean:write name="gzsxwhForm" property="gzsxnrbt"/>"的清单.(记录总数：<bean:write name="gzsxwhForm" property="jlcount"/>)</td>
	        </tr>
	        	
	        <tr>
	          <td class="1-td2" >
			  	  <br>
            <table id="gzsxlistTable" border="1" cellpadding="0" cellspacing="0" class="table-99" height="1" width="99%">
              <tr>
 
                <td width="25%" class="2-td1-left" height="1">计算机代码</td>
                <td width="25%" class="2-td1-left" height="1">纳税人名称</td>
                <td width="25%" class="2-td1-left" height="1">阅读时间</td>
                <td width="25%" class="2-td1-left" height="1">答复时间</td>

              </tr>
      
          <logic:iterate id="data" indexId="index" length="length" name="gzsxwhForm" property="tjgzsxlilst" type="com.ttsoft.bjtax.smsb.model.client.nsrtogzsx">
               <tr>
                <td width="25%" class="2-td2-left" height="23"><ttsoft:write name="data" property="jsjdm"/>&nbsp</td>
                <td width="25%" class="2-td2-left" height="23"><ttsoft:write name="data" property="nsrmc"/>&nbsp</td>
                <td width="25%" class="2-td2-left" height="23"><ttsoft:write name="data" property="ydsj"/>&nbsp</td>
                <td width="25%" class="2-td2-left" height="23"><a href=gzsxfkcxAction.do?actionType=getfknr&fknr=<ttsoft:write name="data" property="fknr"/> target=_blank >查看反馈</a>&nbsp</td>
                </tr>
              </logic:iterate>
            </table>
            <br>
	         <table border="0" width="100%">
              <tr>
              <td width="5%">&nbsp; </td>
                <td width="40%"><input name="I2" type="image" accesskey="e" value="导出Excel"  onClick="exportydqdExcel();return false;" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/b-dcexcelb2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="导出Excel" src="<%=static_contextpath%>images/b-dcexcelb1.jpg" width="100" height="25" id="daochu"></td>
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
</html:html>
