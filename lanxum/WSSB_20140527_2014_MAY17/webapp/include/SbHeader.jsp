<%@page contentType="text/html; charset=GBK"%>
<%
	String sbheader_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>


<table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=sbheader_contextpath%>images/q-top-bg-01.jpg">
  <tr>
    <td><img width="495" height="58" src="<%=sbheader_contextpath%>images/q-top-tu-01.jpg"/></td>
    <td align="right"><img width="284" height="58" src="<%=sbheader_contextpath%>images/q-top-tu-02.jpg"/></td>
  </tr>
</table>
<script language="JavaScript" type="text/JavaScript" src="<%=sbheader_contextpath%>js/swapImages.js"></script>
<table width="100%" height="23" border="0" cellpadding="0" cellspacing="0" background="<%=sbheader_contextpath%>images/q-top-bg-02.jpg" class="black9">
  <tr background="<%=sbheader_contextpath%>images/q-top-bg-02.jpg">
    <td width="88%"><font color="#A4B9C6">综合服务管理信息系统</font>&gt;<font color="#7C9AAB">网上服务系统</font>&gt;<font color="#6F8EA2">综合申报</font>&gt;<%=request.getParameter("name")%></td>
    <td width="5%" nowrap><a href="logout.do"><img src="<%=sbheader_contextpath%>images/t-zhuxiao1.jpg" alt="注销" border=0 name="Image1" width="30" height="16" id="Image1" onMouseOver="this.src='<%=sbheader_contextpath%>images/t-zhuxiao2.jpg'" onMouseOut="this.src='<%=sbheader_contextpath%>images/t-zhuxiao1.jpg'"></a></td>
    <td width="7%" valign="bottom" nowrap>
      <img src="<%=sbheader_contextpath%>images/t-help1.jpg" name="Image2" id="Image2" onMouseOver="MM_showHideLayers('Layer1','','show');this.src='<%=sbheader_contextpath%>images/t-help2.jpg'" onMouseOut="this.src='<%=sbheader_contextpath%>images/t-help1.jpg';MM_showHideLayers('Layer1','','hide')" style="cursor:hand">
      <div id="Layer1" style="Z-INDEX: 1; WIDTH: 100px; POSITION: absolute; HEIGHT: 10px; right: 1px; top: 77px; visibility: hidden; margin-right: 1% "
            onMouseOut="MM_showHideLayers('Layer1','','hide')">
        <table width="80" cellspacing=0 onMouseOver="MM_showHideLayers('Layer1','','show')" >
          <tr>
              <td class="2-td2-t-center"><a href="<%=sbheader_contextpath%><%=request.getParameter("help_url")%>" target="_blank">帮助</a></td>
            </tr>
            <tr>
              <td class="2-td2-center"><div align="center"><a href="javascript:popUp()">关于</a></div></td>
            </tr>
        </table>
      </div>
    </td>
  </tr>
</table>

<script language="javascript">
<!--

function popUp(){
    props=window.open('<%=sbheader_contextpath%>' + '/about/about.htm','poppage','toolbars=0,scrollbars=0,location=0,statusbara=0,menubars=0,resizable=0,width=500,height=267');
}

//-->
</script>
