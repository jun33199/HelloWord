<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<html:html>
<head>  
<title>告知事项反馈编辑</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
function doReturn()
{
    document.all.actionType.value="fanhui"
    document.forms[0].submit();
	//document.forms[0].action = "quit.do";
	//document.forms[0].submit();
}
//保存提交
function doSaveSubmit()
{
  //告知事项反馈信息
  nsrfk =document.all.nsrfk.value+"";
  if (nsrfk.length<1){
    alert("请输入内容！");
    return false;
  }
  if (nsrfk.length>400){
    alert("数据长度在400字之内！");
    return false;
  }
            var jsjdm='<bean:write name="gzsxForm" property="jsjdm" scope="request"/>';
            var gzsx_id='<bean:write name="gzsxForm" property="gzsx_id" scope="request"/>';
            var nsrmc='<bean:write name="gzsxForm" property="nsrmc" scope="request"/>';
            var savetype='<bean:write name="gzsxForm" property="savetype" scope="request"/>';
           
        
             
  if(window.confirm("该操作将保存您的反馈信息,且不能自动恢复,请确认")){
  //document.forms[0].action = "gzsxfk.do?actionType=Save";
    document.forms[0].action = "gzsxfk.do?actionType=Save&jsjdm="+jsjdm+"&gzsx_id="
          +gzsx_id+"&nsrmc=" +nsrmc+"&savetype=" +savetype;
    document.forms[0].submit();
  }
 
}
function loadform(){
//alert("比较麻烦进不来了");

var czbs = '<bean:write name="gzsxForm" property="fknrsavebs"/>';
if (czbs == '1') {
      alert("更新成功！");
    }else if (czbs=='2'){
    alert("更新失败！");
    }
}

</script>
</head>
<body onload="loadform()" bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
<table width="97%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>
    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="告知事项反馈" />
		<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>
    	<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td valign="top">
	     <br>
	      <html:errors/>
	     <table  align="center" cellspacing="0" class="table-99">
		<!-- write your code here--->
	        <tr>
	          <td class="1-td1">告知事项反馈编辑</td>
	        </tr>            
	        <tr>
	          <td class="1-td2"><br>
<html:form action="gzsx.do" method="POST">
<html:hidden property="actionType" />
<TABLE border=0 cellPadding=0 cellSpacing=0 width="98%">
	<TBODY>
        <TR>
        	<TD  width="100%"><br>
        	<div align="center">
                <textarea name="nsrfk" style="width:95%" rows="10"><bean:write name="gzsxForm" property="nsrfk"/></textarea>
              <div/>
        	</TD>
            
	</TR>
	</TBODY>
</TABLE><BR>
<TABLE border=0 cellPadding=0 cellSpacing=0 width="98%">
        <TBODY>
        	<TR>
			<TD height="40" colspan="8"> <DIV align="center">
		  	<img src="<%=static_contextpath%>images/baocun2.jpg"  alt="保存" onclick="doSaveSubmit();" style="cursor:hand">
			&nbsp;&nbsp;&nbsp;
		  	<img src="<%=static_contextpath%>images/fh-f1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fh-f2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tuichu1.jpg'" alt="退出" onclick="doReturn();" style="cursor:hand">
			</DIV>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</html:form>
	          </td>
	         </tr>
	     </table>
	    </td>
	  </tr>
    	</table>
<br>
    </td>
  </tr>
<tr>
  <td valign="bottom" align="center">
	<%@ include file="../include/bottom.jsp" %>
  </td>
</tr>
 </table>
</body>
</html:html>