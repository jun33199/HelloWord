<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.ttsoft.common.model.UserData"%>
<%@ page import="com.ttsoft.common.util.SessionKey"%>
<% String static_file = Constants.getStaticContextPath(); %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!--��ʼ��UserData-->
<%
UserData us = new UserData();
us.yhmc="�Բ�";
us.yhid="0001";
request.getSession(false).setAttribute(SessionKey.USER_DATA, us);
%>

<HTML><HEAD><TITLE>���ܵ���</TITLE>
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
    <TD width="81%"><FONT color=#a4b9c6>�ۺϷ��������Ϣϵͳ</FONT>&gt;<FONT
      color=#7c9aab>Ӧ�������Դ��ȫ����</FONT>&gt;<FONT color=#567587>�����嵥</FONT> </TD>
    <TD align=middle noWrap vAlign=bottom><A
      href="http://192.100.99.100/logout.do" onmouseout=MM_swapImgRestore()
      onmouseover="MM_swapImage('Image8','','<%=static_file%>/images/t-zhuxiao2.jpg',1)"><IMG
      alt=�˳���¼ border=0 height=16 name=Image8 src="<%=static_file%>images/t-zhuxiao1.jpg"
      width=30></A>&nbsp; <A href="http://192.100.99.100/login.do#"
      onmouseout="MM_swapImgRestore();MM_showHideLayers('Layer1','','hide')"
      onmouseover="MM_swapImage('Image9','','<%=static_file%>/images/t-help2.jpg',1);MM_showHideLayers('Layer1','','show')"><IMG
      alt=���� border=0 height=16 name=Image9 src="<%=static_file%>images/t-help1.jpg"
      width=30> </A>
      <DIV id=Layer1 onmouseout="MM_showHideLayers('Layer1','','hide')"
      style="HEIGHT: 8px; LEFT: 90%; MARGIN-RIGHT: 10%; POSITION: absolute; TOP: 76px; VISIBILITY: hidden; WIDTH: 100px; Z-INDEX: 1">
      <TABLE cellSpacing=0 class=black9
      onmouseover="MM_showHideLayers('Layer1','','show')" width=80>
        <TBODY>
        <TR>
          <TD class=2-td2-t-center><A
            href="<%=static_file%>help/yyaq/yhdlgl/yhdlgl-002.htm"
            target=_blank>����</A></TD></TR>
        <TR>
          <TD class=2-td2-center><A
        href="javascript:popUp()">����</A></TD></TR></TBODY></TABLE></DIV><!--</div>--></TD></TR></TBODY></TABLE>
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
                <TD align=middle id=AC onmouseover=clickOnBar(this) style="CURSOR: hand">��˰����</TD>
              </TR>
              </TBODY>
             </TABLE>
            <DIV id=divAC   style="DISPLAY: none">
            <DIV id=ACM
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; PADDING-LEFT: 10px" jdid="313"
            url="#" open="false" ywmc="��˰����"
            imageOpen="<%=static_file%>images/bookopen.gif"
            image="<%=static_file%>images/book.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
         <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/book.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>��˰�걨���� </TD>

             </TR>
             </TBODY></TABLE>
            <DIV id=ACMA
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9853" url="#" open="false" ywmc="��˰�걨"
            imageOpen="<%=static_file%>images/bookopen.gif"
            image="<%=static_file%>images/book.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/book.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>��˰�걨 </TD>
              <TR></TR></TBODY></TABLE>

            <DIV id=ACMA1
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="./��ѯ�걨������Ϣ.htm"
            open="false" ywmc="��ѯ�걨������Ϣ"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>��ѯ�걨������Ϣ </TD>
              <TR></TR></TBODY></TABLE></DIV>

            <DIV id=ACMAA
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="./�����걨��.htm"
            open="false" ywmc="¼������걨��Ϣ"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>¼������걨��Ϣ </TD>
              <TR></TR></TBODY></TABLE></DIV>
            <DIV id=ACMAB
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9599"
            url="./�Ǹ����걨��.htm"
            open="false" ywmc="¼��Ǹ����걨��Ϣ"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
           <TABLE border=0 cellPadding=0 cellSpacing=0>
             <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>¼��Ǹ����걨��Ϣ </TD>
              <TR></TR></TBODY></TABLE></DIV>

            <DIV id=ACMAC
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9599"
            url="./������˰�걨��.htm"
            open="false" ywmc="¼����˰�����걨��"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
           <TABLE border=0 cellPadding=0 cellSpacing=0>
             <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>¼����˰�����걨�� </TD>
              <TR></TR></TBODY></TABLE></DIV>



            <DIV id=ACMBB
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="��˰���ݵ���.htm"
            open="false" ywmc="�걨���ݵ���"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>�걨���ݵ���</TD>
              <TR></TR></TBODY></TABLE></DIV></DIV>


         <DIV id=ACMF
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9853" url="#" open="false" ywmc="������˰¼��"
            imageOpen="<%=static_file%>images/bookopen.gif"
            image="<%=static_file%>images/book.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/book.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>������˰¼��
              </TD>
             </TBODY></TABLE>
			 <DIV id=ACMFE
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9599"
            url="./�����еط�˰��ֲ�����˰��Ϣ�ɼ������ˣ�.htm"
            open="false" ywmc="¼�뱱���еط�˰��ֲ�����˰��Ϣ�ɼ������ˣ�"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
           <TABLE border=0 cellPadding=0 cellSpacing=0>
             <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>¼�뱱���еط�˰��ֲ�����˰��Ϣ�ɼ������ˣ�</TD>
              <TR></TR></TBODY></TABLE></DIV>

            <DIV id=ACMFD
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9599"
            url="./�����еط�˰��ֲ�����˰��Ϣ�ɼ����Ǹ��ˣ�.htm"
            open="false" ywmc="¼�뱱���еط�˰��ֲ�����˰��Ϣ�ɼ����Ǹ��ˣ�"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
           <TABLE border=0 cellPadding=0 cellSpacing=0>
             <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>¼�뱱���еط�˰��ֲ�����˰��Ϣ�ɼ����Ǹ��ˣ�</TD>
              <TR></TR></TBODY></TABLE></DIV></DIV>

			<DIV id=ACMB
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9853" url="#" open="false" ywmc="���ֽɿ������"
            imageOpen="<%=static_file%>images/bookopen.gif"
            image="<%=static_file%>images/book.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/book.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>���ֽɿ������

              </TD>
             </TBODY></TABLE>
            <DIV id=ACMB0
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="��ѯ���ֽɿ���.htm"
            open="false" ywmc="��ѯ���ֽɿ���"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>��ѯ���ֽɿ���</TD>
              <TR></TR></TBODY></TABLE></DIV>

            <DIV id=ACMBA
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="./���ɲ���ӡ���ֽɿ���.htm"
            open="false" ywmc="�������ֽɿ���"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>���ɲ���ӡ���ֽɿ��飨����</TD>
              <TR></TR></TBODY></TABLE></DIV>

            <DIV id=ACMBC
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="���ɲ���ӡ�ɿ���.htm"
            open="false" ywmc="���ɽɿ���"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>���ɲ���ӡ�ɿ���</TD>
              <TR></TR></TBODY></TABLE></DIV>

            <DIV id=ACMBD
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="./�������ֽɿ���.htm"
            open="false" ywmc="�������ֽɿ���"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>�������ֽɿ��飨���ϣ�</TD>
              <TR></TR></TBODY></TABLE></DIV></DIV></DIV>

		<DIV id=ACM0
	            onclick=window.event.cancelBubble=true;clickOnEntity(this);
	            ondragstart="return false" onselectstart="return false"
	            style="COLOR: #7c9aab; CURSOR: hand; PADDING-LEFT: 10px" jdid="313"
	            url="#" open="false" ywmc="���"
	            imageOpen="<%=static_file%>images/bookopen.gif"
	            image="<%=static_file%>images/book.gif">
       <TABLE cellPadding=0 cellSpacing=0>
         <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/book.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>��� </TD>
             </TR>
         </TBODY></TABLE>

		 <DIV id=ACM01
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="./��ʱ����.htm"
            open="false" ywmc="��ʱ����"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>��ʱ����</TD>
              <TR></TR></TBODY></TABLE></DIV>

			<DIV id=ACM02
            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="./��������.htm"
            open="false" ywmc="��������"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>��������</TD>
              <TR></TR></TBODY></TABLE></DIV>

			<DIV id=ACM03            onclick=window.event.cancelBubble=true;clickOnEntity(this);
            ondragstart="return false" onselectstart="return false"
            style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
            jdid="9598"
            url="./������˼��տ�.htm"
            open="false" ywmc="��˼��տ�"
            imageOpen="<%=static_file%>images/paper.gif"
            image="<%=static_file%>images/paper.gif">
            <TABLE border=0 cellPadding=0 cellSpacing=0>
              <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/paper.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>��˼��տ�</TD>
              <TR></TR></TBODY></TABLE></DIV></DIV>

	  <DIV id=ACM1
	            onclick=window.event.cancelBubble=true;clickOnEntity(this);
	            ondragstart="return false" onselectstart="return false"
	            style="COLOR: #7c9aab; CURSOR: hand; PADDING-LEFT: 10px" jdid="313"
	            url="#" open="false" ywmc="�ɿ������"
	            imageOpen="<%=static_file%>images/bookopen.gif"
	            image="<%=static_file%>images/book.gif">
       <TABLE cellPadding=0 cellSpacing=0>
         <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/book.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>�ɿ������ </TD>
             </TR>
         </TBODY></TABLE>
        <DIV id=ACM1A
        onclick=window.event.cancelBubble=true;clickOnEntity(this);
        ondragstart="return false" onselectstart="return false"
        style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
        jdid="9599"
        url="./�������ɽɿ���.htm"
        open="false" ywmc="�������ɽɿ���"
        imageOpen="<%=static_file%>images/paper.gif"
        image="<%=static_file%>images/paper.gif">
       <TABLE border=0 cellPadding=0 cellSpacing=0>
         <TBODY>
          <TR>
            <TD vAlign=center><IMG border=0 id=image
              src="<%=static_file%>images/paper.gif"></IMG> </TD>
            <TD noWrap
            style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
            vAlign=center>�������ɽɿ���</TD>
          <TR></TR></TBODY></TABLE></DIV>

        <DIV id=ACM1B
        onclick=window.event.cancelBubble=true;clickOnEntity(this);
        ondragstart="return false" onselectstart="return false"
        style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
        jdid="9599"
        url="./��ӡ�ɿ���.htm"
        open="false" ywmc="��ӡ�ɿ���"
        imageOpen="<%=static_file%>images/paper.gif"
        image="<%=static_file%>images/paper.gif">
       <TABLE border=0 cellPadding=0 cellSpacing=0>
         <TBODY>
          <TR>
            <TD vAlign=center><IMG border=0 id=image
              src="<%=static_file%>images/paper.gif"></IMG> </TD>
            <TD noWrap
            style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
            vAlign=center>��ӡ�ɿ��飨����</TD>
          <TR></TR></TBODY></TABLE></DIV>

        <DIV id=ACM1C
        onclick=window.event.cancelBubble=true;clickOnEntity(this);
        ondragstart="return false" onselectstart="return false"
        style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
        jdid="9599"
        url="./�ɿ������ȷ��.htm"
        open="false" ywmc="�ɿ������ȷ��"
        imageOpen="<%=static_file%>images/paper.gif"
        image="<%=static_file%>images/paper.gif">
       <TABLE border=0 cellPadding=0 cellSpacing=0>
         <TBODY>
          <TR>
            <TD vAlign=center><IMG border=0 id=image
              src="<%=static_file%>images/paper.gif"></IMG> </TD>
            <TD noWrap
            style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
            vAlign=center>�ɿ������ȷ��</TD>
          <TR></TR></TBODY></TABLE></DIV>

        <DIV id=ACM1D
        onclick=window.event.cancelBubble=true;clickOnEntity(this);
        ondragstart="return false" onselectstart="return false"
        style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
        jdid="9599"
        url="./�������ܽɿ���.htm"
        open="false" ywmc="�������ܽɿ���"
        imageOpen="<%=static_file%>images/paper.gif"
        image="<%=static_file%>images/paper.gif">
       <TABLE border=0 cellPadding=0 cellSpacing=0>
         <TBODY>
          <TR>
            <TD vAlign=center><IMG border=0 id=image
              src="<%=static_file%>images/paper.gif"></IMG> </TD>
            <TD noWrap
            style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
            vAlign=center>�������ܽɿ���</TD>
          <TR></TR></TBODY></TABLE></DIV>
	  </DIV>

	  <DIV id=ACM2
	            onclick=window.event.cancelBubble=true;clickOnEntity(this);
	            ondragstart="return false" onselectstart="return false"
	            style="COLOR: #7c9aab; CURSOR: hand; PADDING-LEFT: 10px" jdid="313"
	            url="#" open="false" ywmc="��˰����"
	            imageOpen="<%=static_file%>images/bookopen.gif"
	            image="<%=static_file%>images/book.gif">
       <TABLE cellPadding=0 cellSpacing=0>
         <TBODY>
              <TR>
                <TD vAlign=center><IMG border=0 id=image
                  src="<%=static_file%>images/book.gif"></IMG> </TD>
                <TD noWrap
                style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
                vAlign=center>��Ϣά�� </TD>
             </TR>
         </TBODY></TABLE>

        <DIV id=ACM2A
        onclick=window.event.cancelBubble=true;clickOnEntity(this);
        ondragstart="return false" onselectstart="return false"
        style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
        jdid="9599"
        url="/qsgl/xxwh/queryZcwh.do"
        open="false" ywmc="����ά��"
        imageOpen="<%=static_file%>images/paper.gif"
        image="<%=static_file%>images/paper.gif">
       <TABLE border=0 cellPadding=0 cellSpacing=0>
         <TBODY>
          <TR>
            <TD vAlign=center><IMG border=0 id=image src="<%=static_file%>images/paper.gif"></IMG> </TD>
            <TD noWrap
            style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
            vAlign=center>����ά��</TD>
          <TR></TR></TBODY></TABLE></DIV>

          <DIV id=ACM2B
          onclick=window.event.cancelBubble=true;clickOnEntity(this);
          ondragstart="return false" onselectstart="return false"
          style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
          jdid="9598"
          url="./��¼�����걨��.htm"
          open="false" ywmc="��¼�����걨��Ϣ"
          imageOpen="<%=static_file%>images/paper.gif"
          image="<%=static_file%>images/paper.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
            <TBODY>
            <TR>
              <TD vAlign=center><IMG border=0 id=image
                src="<%=static_file%>images/paper.gif"></IMG> </TD>
              <TD noWrap
              style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
              vAlign=center>��¼�����걨��Ϣ </TD>
            <TR></TR></TBODY></TABLE></DIV>

          <DIV id=ACM2C
          onclick=window.event.cancelBubble=true;clickOnEntity(this);
          ondragstart="return false" onselectstart="return false"
          style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
          jdid="9598"
          url="./��¼�Ǹ����걨��.htm"
          open="false" ywmc="��¼�Ǹ����걨��Ϣ"
          imageOpen="<%=static_file%>images/paper.gif"
          image="<%=static_file%>images/paper.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
            <TBODY>
            <TR>
              <TD vAlign=center><IMG border=0 id=image
                src="<%=static_file%>images/paper.gif"></IMG> </TD>
              <TD noWrap
              style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
              vAlign=center>��¼�Ǹ����걨��Ϣ </TD>
            <TR></TR></TBODY></TABLE></DIV>

          <DIV id=ACM2D
          onclick=window.event.cancelBubble=true;clickOnEntity(this);
          ondragstart="return false" onselectstart="return false"
          style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
          jdid="9598"
          url="./��¼������˰�걨��.htm"
          open="false" ywmc="��¼������˰�걨��"
          imageOpen="<%=static_file%>images/paper.gif"
          image="<%=static_file%>images/paper.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
            <TBODY>
            <TR>
              <TD vAlign=center><IMG border=0 id=image
                src="<%=static_file%>images/paper.gif"></IMG> </TD>
              <TD noWrap
              style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
              vAlign=center>��¼������˰�걨�� </TD>
            <TR></TR></TBODY></TABLE></DIV>

           <DIV id=ACM2E
          onclick=window.event.cancelBubble=true;clickOnEntity(this);
          ondragstart="return false" onselectstart="return false"
          style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
          jdid="9598"
          url="./��¼��˰���ֽɿ���.htm"
          open="false" ywmc="��¼��˰���ֽɿ���"
          imageOpen="<%=static_file%>images/paper.gif"
          image="<%=static_file%>images/paper.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
            <TBODY>
            <TR>
              <TD vAlign=center><IMG border=0 id=image
                src="<%=static_file%>images/paper.gif"></IMG> </TD>
              <TD noWrap
              style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
              vAlign=center>��¼��˰���ֽɿ��� </TD>
            <TR></TR></TBODY></TABLE></DIV>

          <DIV id=ACM2F
          onclick=window.event.cancelBubble=true;clickOnEntity(this);
          ondragstart="return false" onselectstart="return false"
          style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
          jdid="9598"
          url="./ȡ�����.htm"
          open="false" ywmc="������˰�˶�֪ͨ��"
          imageOpen="<%=static_file%>images/paper.gif"
          image="<%=static_file%>images/paper.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
            <TBODY>
            <TR>
              <TD vAlign=center><IMG border=0 id=image
                src="<%=static_file%>images/paper.gif"></IMG> </TD>
              <TD noWrap
              style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
              vAlign=center>������˰�˶�֪ͨ�� </TD>
            <TR></TR></TBODY></TABLE></DIV>

          <DIV id=ACM2G
          onclick=window.event.cancelBubble=true;clickOnEntity(this);
          ondragstart="return false" onselectstart="return false"
          style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
          jdid="9598"
          url="./ȡ�����.htm"
          open="false" ywmc="ȡ�����"
          imageOpen="<%=static_file%>images/paper.gif"
          image="<%=static_file%>images/paper.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
            <TBODY>
            <TR>
              <TD vAlign=center><IMG border=0 id=image
                src="<%=static_file%>images/paper.gif"></IMG> </TD>
              <TD noWrap
              style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
              vAlign=center>ȡ�����</TD>
            <TR></TR></TBODY></TABLE></DIV>

		<DIV id=ACM2G
          onclick=window.event.cancelBubble=true;clickOnEntity(this);
          ondragstart="return false" onselectstart="return false"
          style="COLOR: #7c9aab; CURSOR: hand; DISPLAY: none; PADDING-LEFT: 10px"
          jdid="9598"
          url="./ά�����ֽɿ����ӡ״̬.htm"
          open="false" ywmc="ά�����ֽɿ����ӡ״̬"
          imageOpen="<%=static_file%>images/paper.gif"
          image="<%=static_file%>images/paper.gif">
          <TABLE border=0 cellPadding=0 cellSpacing=0>
            <TBODY>
            <TR>
              <TD vAlign=center><IMG border=0 id=image
                src="<%=static_file%>images/paper.gif"></IMG> </TD>
              <TD noWrap
              style="FONT-FAMILY: Verdana; FONT-SIZE: 11px; PADDING-LEFT: 7px; font-color: black"
              vAlign=center>ά�����ֽɿ����ӡ״̬</TD>
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
          <TD noWrap><FONT size=2>&nbsp;&nbsp;&nbsp;* ����ʹ�� IE 5.5 ���ϣ��ֱ���
            800*600 �������վ</FONT></TD>
          <TD align=right height=24><IMG alt=�а쵥λ���廪ͬ������ɷ����޹�˾ height=24
            src="<%=static_file%>images/q-bottom-tu-01.jpg"
  width=184></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></BODY></HTML>
