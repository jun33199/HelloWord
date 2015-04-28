<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.ttsoft.common.model.UserData"%>
<%@ page import="com.ttsoft.common.util.SessionKey"%>
<% String static_file = Constants.getStaticContextPath(); %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!--初始化UserData-->
<%
UserData us = new UserData();
us.yhmc="赵博";
us.yhid="0001";
request.getSession(false).setAttribute(SessionKey.USER_DATA, us);
%>

<HTML><HEAD><TITLE>功能导航</TITLE>
<META content="text/html; charset=GBK" http-equiv=Content-Type>
<META content=no-cache http-equiv=pragma>

<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/tree.js" type=text/JavaScript></SCRIPT>
<STYLE type=text/css>@import url( <%=static_file%>css/text.css );</STYLE>

<META content="MSHTML 5.00.2919.6307" name=GENERATOR></HEAD>
<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>

<TABLE background=<%=static_file%>images/q-top-bg-01.jpg border=0 cellPadding=0 cellSpacing=0 width="100%">
  <TBODY>
  <TR background="<%=static_file%>images/q-top-bg-01.jpg">
    <TD><IMG src="<%=static_file%>images/q-top-tu-01.jpg"></TD>
    <TD align=right><IMG src="<%=static_file%>images/q-top-tu-02.jpg"></TD></TR></TBODY></TABLE>
<TABLE background=<%=static_file%>images/q-top-bg-02.jpg border=0 cellPadding=0 cellSpacing=0 class=black9 height=23 width="100%">
  <TBODY>
  <TR background="<%=static_file%>/images/q-top-bg-02.jpg">
    <TD width="81%"><FONT color=#a4b9c6>综合服务管理信息系统</FONT>&gt;<FONT
      color=#7c9aab>应用软件资源安全管理</FONT>&gt;<FONT color=#567587>功能清单</FONT> </TD>
    <TD align=middle noWrap vAlign=bottom><A
      href="http://192.100.99.100/logout.do" onmouseout=MM_swapImgRestore()
      onmouseover="MM_swapImage('Image8','','<%=static_file%>/images/t-zhuxiao2.jpg',1)"><IMG
      alt=退出登录 border=0 height=16 name=Image8 src="<%=static_file%>images/t-zhuxiao1.jpg"
      width=30></A>&nbsp; <A href="http://192.100.99.100/login.do#"
      onmouseout="MM_swapImgRestore();MM_showHideLayers('Layer1','','hide')"
      onmouseover="MM_swapImage('Image9','','<%=static_file%>/images/t-help2.jpg',1);MM_showHideLayers('Layer1','','show')"><IMG
      alt=帮助 border=0 height=16 name=Image9 src="<%=static_file%>images/t-help1.jpg"
      width=30> </A>
      <DIV id=Layer1 onmouseout="MM_showHideLayers('Layer1','','hide')"
      style="HEIGHT: 8px; LEFT: 90%; MARGIN-RIGHT: 10%; POSITION: absolute; TOP: 76px; VISIBILITY: hidden; WIDTH: 100px; Z-INDEX: 1">
      <TABLE cellSpacing=0 class=black9
      onmouseover="MM_showHideLayers('Layer1','','show')" width=80>
        <TBODY>
        <TR>
          <TD class=2-td2-t-center><A
            href="<%=static_file%>help/yyaq/yhdlgl/yhdlgl-002.htm"
            target=_blank>帮助</A></TD></TR>
        <TR>
          <TD class=2-td2-center><A
        href="javascript:popUp()">关于</A></TD></TR></TBODY></TABLE></DIV><!--</div>--></TD></TR></TBODY></TABLE>
<SCRIPT language=javascript>
<!--

function popUp(){
    var server = '192.100.99.100';
    var port   = '80';
    props=window.open('<%=static_file%>' + '/about/about.htm','poppage','toolbars=0,scrollbars=0,location=0,statusbara=0,menubars=0,resizable=0,width=500,height=267');
}

//-->
</SCRIPT>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="85%" width="98%">
  <TBODY>
  <TR>
    <TD align=middle vAlign=top>
      <FORM method=post>
      <INPUT name=jdid type=hidden>
      <INPUT name=menu_url type=hidden value=http://192.100.99.100:80/mainMenu.do>
      <INPUT name=login_url type=hidden value=http://192.100.99.100:80/logout.do></FORM>
      <TABLE align=center cellPadding=0 cellSpacing=0 class=table-100 width="100%" border="0">
        <TBODY>
        <TR>
          <TD align=left vAlign=top>
            <DIV id=divNavBar>
            <TABLE>
              <TBODY>
              <TR class=NavBar_FontSize>
                <TD align=middle id=AC onmouseover=clickOnBar(this) style="CURSOR: hand">契税管理</TD>
              </TR>
              </TBODY>
             </TABLE>
            <DIV id=divAC   style="DISPLAY: none">
            <DIV id=ACM
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; PADDING-LEFT: 10px" jdid="313"
            url="#" open="false" ywmc="契税管理"
            imageOpen="<%=static_file%>images/bookopen.gif"
            image="<%=static_file%>images/book.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
         <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/book.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>契税申报征收 </TD>

             </TR>
             </TBODY></TABLE>
            <DIV id=ACMA
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9853" url="#" open="false" ywmc="契税申报"
            imageOpen="<%=static_file%>images/bookopen.gif"
            image="<%=static_file%>images/book.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/book.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>契税申报 </TD>
              <TR></TR></TBODY></TABLE>

            <DIV id=ACMA1
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="./查询申报数据信息.htm"
            open="false" ywmc="查询申报数据信息"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>查询申报数据信息 </TD>
              <TR></TR></TBODY></TABLE></DIV>

            <DIV id=ACMAA
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="./个人申报表.htm"
            open="false" ywmc="录入个人申报信息"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>录入个人申报信息 </TD>
              <TR></TR></TBODY></TABLE></DIV>
            <DIV id=ACMAB
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9599"
            url="./非个人申报表.htm"
            open="false" ywmc="录入非个人申报信息"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
           <TABLE border=0 cellPadding=0 cellSpacing=0>
             <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>录入非个人申报信息 </TD>
              <TR></TR></TBODY></TABLE></DIV>

            <DIV id=ACMAC
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9599"
            url="./减免契税申报表.htm"
            open="false" ywmc="录入契税减免申报表"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
           <TABLE border=0 cellPadding=0 cellSpacing=0>
             <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>录入契税减免申报表 </TD>
              <TR></TR></TBODY></TABLE></DIV>



            <DIV id=ACMBB
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="契税数据导入.htm"
            open="false" ywmc="申报数据导入"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>申报数据导入</TD>
              <TR></TR></TBODY></TABLE></DIV></DIV>


         <DIV id=ACMF
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9853" url="#" open="false" ywmc="不征契税录入"
            imageOpen="<%=static_file%>images/bookopen.gif"
            image="<%=static_file%>images/book.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/book.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>不征契税录入
              </TD>
             </TBODY></TABLE>
			 <DIV id=ACMFE
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9599"
            url="./北京市地方税务局不征契税信息采集表（个人）.htm"
            open="false" ywmc="录入北京市地方税务局不征契税信息采集表（个人）"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
           <TABLE border=0 cellPadding=0 cellSpacing=0>
             <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>录入北京市地方税务局不征契税信息采集表（个人）</TD>
              <TR></TR></TBODY></TABLE></DIV>

            <DIV id=ACMFD
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9599"
            url="./北京市地方税务局不征契税信息采集表（非个人）.htm"
            open="false" ywmc="录入北京市地方税务局不征契税信息采集表（非个人）"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
           <TABLE border=0 cellPadding=0 cellSpacing=0>
             <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>录入北京市地方税务局不征契税信息采集表（非个人）</TD>
              <TR></TR></TBODY></TABLE></DIV></DIV>

			<DIV id=ACMB
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9853" url="#" open="false" ywmc="收现缴款书管理"
            imageOpen="<%=static_file%>images/bookopen.gif"
            image="<%=static_file%>images/book.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/book.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>收现缴款书管理

              </TD>
             </TBODY></TABLE>
            <DIV id=ACMB0
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="查询收现缴款书.htm"
            open="false" ywmc="查询收现缴款书"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>查询收现缴款书</TD>
              <TR></TR></TBODY></TABLE></DIV>

            <DIV id=ACMBA
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="./生成并打印收现缴款书.htm"
            open="false" ywmc="生成收现缴款书"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>生成并打印收现缴款书（补）</TD>
              <TR></TR></TBODY></TABLE></DIV>

            <DIV id=ACMBC
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="生成并打印缴款书.htm"
            open="false" ywmc="生成缴款书"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>生成并打印缴款书</TD>
              <TR></TR></TBODY></TABLE></DIV>

            <DIV id=ACMBD
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="./撤销收现缴款书.htm"
            open="false" ywmc="撤销收现缴款书"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>撤销收现缴款书（作废）</TD>
              <TR></TR></TBODY></TABLE></DIV></DIV></DIV>

		<DIV id=ACM0
	            onclick=window.event.cancelBubble=true;clickOnEntity(this);
	            ondragstart="return false" onselectstart="return false"
	            style="COLOR: #7c9aab; CURSOR: hand; PADDING-LEFT: 10px" jdid="313"
	            url="#" open="false" ywmc="审核"
	            imageOpen="<%=static_file%>images/bookopen.gif"
	            image="<%=static_file%>images/book.gif">
       <TABLE cellPadding=0 cellSpacing=0>
         <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/book.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>审核 </TD>
             </TR>
         </TBODY></TABLE>

		 <DIV id=ACM01
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="./即时办理.htm"
            open="false" ywmc="即时办理"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>即时办理</TD>
              <TR></TR></TBODY></TABLE></DIV>

			<DIV id=ACM02
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="./正常审批.htm"
            open="false" ywmc="正常审批"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>正常审批</TD>
              <TR></TR></TBODY></TABLE></DIV>

			<DIV id=ACM03            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="./个人审核及收款.htm"
            open="false" ywmc="审核及收款"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>审核及收款</TD>
              <TR></TR></TBODY></TABLE></DIV></DIV>

	  <DIV id=ACM1
	            onclick=window.event.cancelBubble=true;clickOnEntity(this);
	            ondragstart="return false" onselectstart="return false"
	            style="COLOR: #7c9aab; CURSOR: hand; PADDING-LEFT: 10px" jdid="313"
	            url="#" open="false" ywmc="缴款书管理"
	            imageOpen="<%=static_file%>images/bookopen.gif"
	            image="<%=static_file%>images/book.gif">
       <TABLE cellPadding=0 cellSpacing=0>
         <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/book.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>缴款书管理 </TD>
             </TR>
         </TBODY></TABLE>
        <DIV id=ACM1A
        onclick=window.event.cancelBubble=true;clickOnEntity(this);
        ondragstart="return false" onselectstart="return false"
        style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
        jdid="9599"
        url="./汇总生成缴款书.htm"
        open="false" ywmc="汇总生成缴款书"
        imageOpen="<%=static_file%>images/paper.gif"
        image="<%=static_file%>images/paper.gif">
       <TABLE border=0 cellPadding=0 cellSpacing=0>
         <TBODY>
          <TR>
            <TD vAlign=center><IMG border=0 id=image
              src="<%=static_file%>images/paper.gif"></IMG> </TD>
            <TD noWrap
            style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
            vAlign=center>汇总生成缴款书</TD>
          <TR></TR></TBODY></TABLE></DIV>

        <DIV id=ACM1B
        onclick=window.event.cancelBubble=true;clickOnEntity(this);
        ondragstart="return false" onselectstart="return false"
        style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
        jdid="9599"
        url="./打印缴款书.htm"
        open="false" ywmc="打印缴款书"
        imageOpen="<%=static_file%>images/paper.gif"
        image="<%=static_file%>images/paper.gif">
       <TABLE border=0 cellPadding=0 cellSpacing=0>
         <TBODY>
          <TR>
            <TD vAlign=center><IMG border=0 id=image
              src="<%=static_file%>images/paper.gif"></IMG> </TD>
            <TD noWrap
            style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
            vAlign=center>打印缴款书（补打）</TD>
          <TR></TR></TBODY></TABLE></DIV>

        <DIV id=ACM1C
        onclick=window.event.cancelBubble=true;clickOnEntity(this);
        ondragstart="return false" onselectstart="return false"
        style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
        jdid="9599"
        url="./缴款书二次确认.htm"
        open="false" ywmc="缴款书二次确认"
        imageOpen="<%=static_file%>images/paper.gif"
        image="<%=static_file%>images/paper.gif">
       <TABLE border=0 cellPadding=0 cellSpacing=0>
         <TBODY>
          <TR>
            <TD vAlign=center><IMG border=0 id=image
              src="<%=static_file%>images/paper.gif"></IMG> </TD>
            <TD noWrap
            style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
            vAlign=center>缴款书二次确认</TD>
          <TR></TR></TBODY></TABLE></DIV>

        <DIV id=ACM1D
        onclick=window.event.cancelBubble=true;clickOnEntity(this);
        ondragstart="return false" onselectstart="return false"
        style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
        jdid="9599"
        url="./撤销汇总缴款书.htm"
        open="false" ywmc="撤销汇总缴款书"
        imageOpen="<%=static_file%>images/paper.gif"
        image="<%=static_file%>images/paper.gif">
       <TABLE border=0 cellPadding=0 cellSpacing=0>
         <TBODY>
          <TR>
            <TD vAlign=center><IMG border=0 id=image
              src="<%=static_file%>images/paper.gif"></IMG> </TD>
            <TD noWrap
            style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
            vAlign=center>撤销汇总缴款书</TD>
          <TR></TR></TBODY></TABLE></DIV>
	  </DIV>

	  <DIV id=ACM2
	            onclick=window.event.cancelBubble=true;clickOnEntity(this);
	            ondragstart="return false" onselectstart="return false"
	            style="COLOR: #7c9aab; CURSOR: hand; PADDING-LEFT: 10px" jdid="313"
	            url="#" open="false" ywmc="契税管理"
	            imageOpen="<%=static_file%>images/bookopen.gif"
	            image="<%=static_file%>images/book.gif">
       <TABLE cellPadding=0 cellSpacing=0>
         <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/book.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>信息维护 </TD>
             </TR>
         </TBODY></TABLE>

        <DIV id=ACM2A
        onclick=window.event.cancelBubble=true;clickOnEntity(this);
        ondragstart="return false" onselectstart="return false"
        style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
        jdid="9599"
        url="/qsgl/xxwh/queryZcwh.do"
        open="false" ywmc="政策维护"
        imageOpen="<%=static_file%>images/paper.gif"
        image="<%=static_file%>images/paper.gif">
       <TABLE border=0 cellPadding=0 cellSpacing=0>
         <TBODY>
          <TR>
            <TD vAlign=center><IMG border=0 id=image src="<%=static_file%>images/paper.gif"></IMG> </TD>
            <TD noWrap
            style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
            vAlign=center>政策维护</TD>
          <TR></TR></TBODY></TABLE></DIV>

          <DIV id=ACM2B
          onclick=window.event.cancelBubble=true;clickOnEntity(this);
          ondragstart="return false" onselectstart="return false"
          style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
          jdid="9598"
          url="./补录个人申报表.htm"
          open="false" ywmc="补录个人申报信息"
          imageOpen="<%=static_file%>images/paper.gif"
          image="<%=static_file%>images/paper.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
            <TBODY>
            <TR>
              <TD vAlign=center><IMG border=0 id=image
                src="<%=static_file%>images/paper.gif"></IMG> </TD>
              <TD noWrap
              style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
              vAlign=center>补录个人申报信息 </TD>
            <TR></TR></TBODY></TABLE></DIV>

          <DIV id=ACM2C
          onclick=window.event.cancelBubble=true;clickOnEntity(this);
          ondragstart="return false" onselectstart="return false"
          style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
          jdid="9598"
          url="./补录非个人申报表.htm"
          open="false" ywmc="补录非个人申报信息"
          imageOpen="<%=static_file%>images/paper.gif"
          image="<%=static_file%>images/paper.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
            <TBODY>
            <TR>
              <TD vAlign=center><IMG border=0 id=image
                src="<%=static_file%>images/paper.gif"></IMG> </TD>
              <TD noWrap
              style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
              vAlign=center>补录非个人申报信息 </TD>
            <TR></TR></TBODY></TABLE></DIV>

          <DIV id=ACM2D
          onclick=window.event.cancelBubble=true;clickOnEntity(this);
          ondragstart="return false" onselectstart="return false"
          style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
          jdid="9598"
          url="./补录减免契税申报表.htm"
          open="false" ywmc="补录减免契税申报表"
          imageOpen="<%=static_file%>images/paper.gif"
          image="<%=static_file%>images/paper.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
            <TBODY>
            <TR>
              <TD vAlign=center><IMG border=0 id=image
                src="<%=static_file%>images/paper.gif"></IMG> </TD>
              <TD noWrap
              style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
              vAlign=center>补录减免契税申报表 </TD>
            <TR></TR></TBODY></TABLE></DIV>

           <DIV id=ACM2E
          onclick=window.event.cancelBubble=true;clickOnEntity(this);
          ondragstart="return false" onselectstart="return false"
          style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
          jdid="9598"
          url="./补录契税收现缴款书.htm"
          open="false" ywmc="补录契税收现缴款书"
          imageOpen="<%=static_file%>images/paper.gif"
          image="<%=static_file%>images/paper.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
            <TBODY>
            <TR>
              <TD vAlign=center><IMG border=0 id=image
                src="<%=static_file%>images/paper.gif"></IMG> </TD>
              <TD noWrap
              style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
              vAlign=center>补录契税收现缴款书 </TD>
            <TR></TR></TBODY></TABLE></DIV>

          <DIV id=ACM2F
          onclick=window.event.cancelBubble=true;clickOnEntity(this);
          ondragstart="return false" onselectstart="return false"
          style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
          jdid="9598"
          url="./取消审核.htm"
          open="false" ywmc="作废契税核定通知书"
          imageOpen="<%=static_file%>images/paper.gif"
          image="<%=static_file%>images/paper.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
            <TBODY>
            <TR>
              <TD vAlign=center><IMG border=0 id=image
                src="<%=static_file%>images/paper.gif"></IMG> </TD>
              <TD noWrap
              style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
              vAlign=center>撤销契税核定通知书 </TD>
            <TR></TR></TBODY></TABLE></DIV>

          <DIV id=ACM2G
          onclick=window.event.cancelBubble=true;clickOnEntity(this);
          ondragstart="return false" onselectstart="return false"
          style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
          jdid="9598"
          url="./取消审核.htm"
          open="false" ywmc="取消审核"
          imageOpen="<%=static_file%>images/paper.gif"
          image="<%=static_file%>images/paper.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
            <TBODY>
            <TR>
              <TD vAlign=center><IMG border=0 id=image
                src="<%=static_file%>images/paper.gif"></IMG> </TD>
              <TD noWrap
              style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
              vAlign=center>取消审核</TD>
            <TR></TR></TBODY></TABLE></DIV>

		<DIV id=ACM2G
          onclick=window.event.cancelBubble=true;clickOnEntity(this);
          ondragstart="return false" onselectstart="return false"
          style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
          jdid="9598"
          url="./维护收现缴款书打印状态.htm"
          open="false" ywmc="维护收现缴款书打印状态"
          imageOpen="<%=static_file%>images/paper.gif"
          image="<%=static_file%>images/paper.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
            <TBODY>
            <TR>
              <TD vAlign=center><IMG border=0 id=image
                src="<%=static_file%>images/paper.gif"></IMG> </TD>
              <TD noWrap
              style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
              vAlign=center>维护收现缴款书打印状态</TD>
            <TR></TR></TBODY></TABLE></DIV>

	  </DIV>
	  </TD></TR></TBODY></TABLE>

  <TR>
    <TD align=middle vAlign=bottom >
      <TABLE border=0 cellPadding=0 cellSpacing=0 height=33 width="100%">
        <TBODY>
        <TR>
          <TD background=<%=static_file%>images/q-bottom-bg-01.jpg colSpan=2 height=9
          noWrap></TD></TR>
        <TR>
          <TD noWrap><FONT size=2>&nbsp;&nbsp;&nbsp;* 必须使用 IE 5.5 以上，分辨率
            800*600 浏览本网站</FONT></TD>
          <TD align=right height=24><IMG alt=承办单位：清华同方软件股份有限公司 height=24
            src="<%=static_file%>images/q-bottom-tu-01.jpg"
  width=184></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></BODY></HTML>
