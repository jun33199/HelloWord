<%
	String footer_contextpath = com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator.getContextPath(request);
	//企业所得税2006版操作成功提示信息（以对话框形式弹出）
	String oprationSuccess = (String)request.getAttribute(com.ttsoft.bjtax.smsb.constant.CodeConstant.SMSB_SAVE_SUCCESS);	
%>
<table width="100%" height="33" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td background="<%=footer_contextpath%>images/q-bottom-bg-01.jpg" height="9px" colspan="2" nowrap></td>
  </tr>
  <tr>
    <td nowrap><font size="2">&nbsp;&nbsp;&nbsp;* 必须使用 IE 5.5 以上，分辨率 800*600 浏览本网站</font></td>
    <td height="24" align="right"><img src="<%=footer_contextpath%>images/q-bottom-tu-01.jpg" alt="承办单位：清华同方软件股份有限公司2008_APR02" width="184" height="24"></td>
  </tr>
</table>
<!-- <table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=footer_contextpath%>images/q-bottom-bg-01.jpg" class="black9">
  <tr background="<%=footer_contextpath%>images/q-bottom-bg-01.jpg">
    <td width="51%">&nbsp;&nbsp;&nbsp;* 建议使用 IE 5.5 以上、分辨率 800*600 以上浏览本网站
    </td>
    <td width="49%" align="right"><img src="<%=footer_contextpath%>images/q-bottom-tu-01.jpg" alt="承办单位：清华同方软件股份有限公司" width="171" height="43"></td>
  </tr>
</table> -->
<script language="JavaScript">
<%
	 
	//企业所得税2006版操作成功提示信息（以对话框形式弹出）
	if(oprationSuccess!=null && !oprationSuccess.trim().equals("")){
		out.println("alert('"+oprationSuccess+"');");
	}
 %>
 </script>