<%@ page contentType="text/html; charset=GBK" %>
<%
  String hstatic_file = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=hstatic_file%>images/q-top-bg-01.jpg">
  <tr background="<%=hstatic_file%>images/q-top-bg-01.jpg">
    <td><img src="<%=hstatic_file%>images/q-top-tu-01.jpg"></td>
    <td align="right"><img src="<%=hstatic_file%>images/q-top-tu-02.jpg"></td>
  </tr>
</table>
<table width="100%" height="23" border="0" cellpadding="0" cellspacing="0" background="<%=hstatic_file%>images/q-top-bg-02.jpg" class="black9">
  <tr background="<%=hstatic_file%>images/q-top-bg-02.jpg">
    <td width="81%">
      <%
         String subTitle = request.getParameter("subtitle");
         if (subTitle == null)
         {
           subTitle = "";
         }



         String helpURL = request.getParameter("helpURL");
		 helpURL = "qsgl/qsgl_help.html";
         if (helpURL == null)
         {
         		helpURL = "qsgl/qsgl_help.html";
         }
      %>
      <font color="#A4B9C6">综合服务管理信息系统</font>&gt;<font color="#7C9AAB">契税管理</font>&gt;<font color="#567587"><%=subTitle%></font>
    </td>
    <td align="center" valign="bottom" nowrap><a href="/qsgl/logout.do" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image8','','<%=hstatic_file%>images/t-zhuxiao2.jpg',1)">
      <img src="<%=hstatic_file%>images/t-zhuxiao1.jpg" alt="退出登录" name="Image8" width="30" height="16" border="0"></a>&nbsp;
     <a href="#" onMouseOut="MM_swapImgRestore();MM_showHideLayers('Layer1','','hide')" onMouseOver="MM_swapImage('Image9','','<%=hstatic_file%>images/t-help2.jpg',1);MM_showHideLayers('Layer1','','show')">
        <img src="<%=hstatic_file%>images/t-help1.jpg" alt="帮助" name="Image9" width="30" height="16" border="0">
      </a>


           <div id="Layer1" style="Z-INDEX: 1; WIDTH: 100px; POSITION: absolute; HEIGHT: 8px; left: 90%; top: 76px; visibility: hidden; margin-right: 10% "
                onMouseOut="MM_showHideLayers('Layer1','','hide')">
              <table width="80" cellspacing=0 onMouseOver="MM_showHideLayers('Layer1','','show')" class="black9">
              <tbody>
                <tr>
                  <td class="2-td2-t-center"><a href="<%=hstatic_file%>help/<%=helpURL%>" target="_blank">帮助</a></td>
                </tr>
                <tr>
                  <td class="2-td2-center"><a href="javascript:popUp()">关于</a></td>
                </tr>
              </tbody>
            </table>
          </div>

    <!--</div>-->
    </td>
  </tr>
</table>



<script language="javascript">
<!--

function popUp(){
    props=window.open('<%=hstatic_file%>' + '/about/about.htm','poppage','toolbars=0,scrollbars=0,location=0,statusbara=0,menubars=0,resizable=0,width=500,height=267');
}

//-->
</script>
<br>
<TABLE align=center border=0 cellPadding=0 cellSpacing=0  width="98%">
  <TBODY>
  <TR>
    <TD vAlign=top>
