<%@ page import="com.ttsoft.common.model.UserData"%>
<%@ page import="com.ttsoft.common.util.SessionKey"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<%@ page import="com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator"%>
<script language="JavaScript">
<%
	//由应用安全取得用户信息
	UserData userData = (UserData)session.getAttribute(SessionKey.USER_DATA);
	//取得总控退出登陆url
	String loginUrl = (String)session.getAttribute(SessionKey.PARAM_LOGOUT);
	//取得总控主菜单url
	String menuUrl = (String)session.getAttribute(SessionKey.PARAM_MENU);
	//取得当前页标题
	String head_title = (String)request.getAttribute(CodeConstant.SMSB_HEADER_TITLE); //title值
	String position = (String)request.getAttribute(CodeConstant.SMSB_HEADER_POSITION);//当前位置
	String helpUrl = (String)request.getAttribute(CodeConstant.SMSB_HEADER_HELP);//帮助
	//静态文件目录
	String static_contextpath = SfResourceLocator.getContextPath(request);
	//保存成功提示信息
	String saveMsg = (String)request.getAttribute(CodeConstant.SMSB_SAVE_SUCCESS);	

	if(saveMsg==null)
		saveMsg="";
	//The check result of tables relation
	String check=(String)request.getAttribute(CodeConstant.CHECK_RESULT_HTML);
	if(check!=null && !"".equals(check.trim())){
	
	%>
		a=350 ; //width
		b=280;  //height
		l=eval(screen.Width-a)/2; //left
		t=eval(screen.Height-b)/2; //top
	 	var newwin = window.open("","","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,depended=yes,width="+a+",height="+b+",left="+l+",top="+t);
	 	var d = newwin.document.open();
		d.write("<%=check%>");
	 	d.close();
	<%
	}

%>

	returnStr = "<%=menuUrl%>";
//弹出关于窗口
function popUp(){
    props=window.open('<%=static_contextpath%>'+'/about/about.htm','poppage','toolbars=0,scrollbars=0,location=0,statusbara=0,menubars=0,resizable=0,width=500,height=267');
}
//弹出帮助窗口
function popUpHelp(){       props=window.open('<%=static_contextpath%><%=helpUrl%>','poppage','toolbars=0,scrollbars=1,location=0,statusbara=0,menubars=0');
}
</SCRIPT>

<style>
.inputalignright{
    font-size: 9pt;
    text-align: right;
}
.inbright {
    font-size: 9pt;
    background :#F3F5F8;
	text-align: right;
    background-color: #F3F5F8;
    border: 0px;
}
</style>

 
 <table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=static_contextpath%>/images/q-top-bg-01.jpg">
  <tr>
    <td><img height="58" src="<%=static_contextpath%>/images/q-top-tu-01.jpg"/></td>
    <td align="right"><img height="58" src="<%=static_contextpath%>/images/q-top-tu-02.jpg"/></td>
  </tr>
 </table>
  <table width="100%" height="23" border="0" cellpadding="0" cellspacing="0" background="<%=static_contextpath%>images/q-top-bg-02.jpg" class="black9">
   <tr background="<%=static_contextpath%>images/q-top-bg-02.jpg">
    <td width="88%"><%=position%></td>
    <td width="5%" align="center"><a href="<%=loginUrl%>" tabindex="-1"><img src="<%=static_contextpath%>images/t-zhuxiao1.jpg" alt="注销" border=0 name="Image1" width="30" height="16" id="Image1" onMouseOver="MM_swapImage('Image1','','<%=static_contextpath%>images/t-zhuxiao2.jpg',1)" onMouseOut="MM_swapImgRestore()"></a></td>
   <td width="7%" valign="bottom" nowrap>
   <a href="#" tabindex="-1" onMouseOut="MM_swapImgRestore();MM_showHideLayers('Layer1','','hide')" onMouseOver="MM_swapImage('Image9','','<%=static_contextpath%>images/t-help2.jpg',1);MM_showHideLayers('Layer1','','show')">
        <img src="<%=static_contextpath%>images/t-help1.jpg" alt="帮助" name="Image9" width="30" height="16" border="0"></a>


           <div id="Layer1" style="Z-INDEX: 1; WIDTH: 100px; POSITION: absolute; HEIGHT: 8px; left: 90%; top: 77px; visibility: hidden; margin-right: 10% "
                onMouseOut="MM_showHideLayers('Layer1','','hide')">
              <table width="80" cellspacing=0 onMouseOver="MM_showHideLayers('Layer1','','show')" class="black9">
              <tbody>
                <tr>
                  <td class="2-td2-t-center"><a href="javascript:popUpHelp()">帮助</a></td>
                </tr>
                <tr>
                  <td class="2-td2-center"><a href="javascript:popUp()">关于</a></td>
                </tr>
              </tbody>
            </table>
          </div>
   </td>
  </tr>
 </table>

 <%
	//显示保存成功提示
	if(saveMsg!=null && !saveMsg.trim().equals("")){
		out.println("<font color=\"blue\" size=\"5\">"+saveMsg+"</font>");
	}
%>

 <br>

 <html:errors/>