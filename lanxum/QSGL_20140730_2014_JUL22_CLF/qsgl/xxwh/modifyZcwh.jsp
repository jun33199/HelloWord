<HTML><HEAD><TITLE>修改政策维护表</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="./js/list.js"
type=text/JavaScript></SCRIPT>

<SCRIPT language=JavaScript src="./js/inputchecker.js"></SCRIPT>

<SCRIPT language=JavaScript>
function checkSubmit(actionType)
{
}
function linkPage(para)
{
    document.forms[0].ymbz.value = para;
    doSubmitForm('TurnPage');
}
</SCRIPT>
<!----本页面的头文件和本页帮助的路径----->
<SCRIPT language=javascript src="./js/Header.js"></SCRIPT>

<SCRIPT language=JavaScript src="./js/swapImage.js"
type=text/JavaScript></SCRIPT>
<LINK href="./css/text.css" rel=stylesheet type=text/css><LINK
href="./css/piaozheng.css" rel=stylesheet type=text/css>
<META content="MSHTML 5.00.2919.6307" name=GENERATOR></HEAD>
<BODY bgColor=#ffffff leftMargin=0
onload="MM_preloadImages('http://192.100.99.99//images/dayin2.jpg','http://192.100.99.99//images/fanhui2.jpg','http://192.100.99.99//images/tuichu2.jpg','http://192.100.99.99//images/diyitiao2.jpg','http://192.100.99.99//images/shangyitiao2.jpg','http://192.100.99.99//images/zuimotiao2.jpg','http://192.100.99.99//images/xinzeng2.jpg','http://192.100.99.99//images/shanchu2.jpg')"
topMargin=0 marginheight="0" marginwidth="0">
 <jsp:include page="/include/Header.jsp" flush="true">
                <jsp:param name="subtitle" value="信息维护&gt;修改政策维护表"/>
                <jsp:param name="helpURL" value=""/>
              </jsp:include>
<SCRIPT language=javascript>
<!--

function popUp(){
    var server = '192.100.99.100';
    var port   = '80';
    props=window.open('http://' + server + ':' + port + '/StaticFile/about/about.htm','poppage','toolbars=0,scrollbars=0,location=0,statusbara=0,menubars=0,resizable=0,width=500,height=267');
}

//-->
</SCRIPT>
<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>契税政策维护-修改</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <FORM action=/pzgl/pages/fclq/fclq001.do
            encType=multipart/form-data;charset=GBK method=post
            name=Fclq001Form><INPUT name=org.apache.struts.taglib.html.TOKEN
            type=hidden value=85fef853635235d36fc0048e4f9dd30> <INPUT
            name=actionType type=hidden value=Show> <INPUT name=ymbz type=hidden
            value=0>
            <br>
             <TABLE border="0" cellSpacing=0 class=table-60>
              <TBODY>
               <TR>
                <TD class=2-td1-center width="50%">
                  指标代码&nbsp;
                </TD>
                <TD class="2-td2-t-right" width="50%">
                  <DIV align=left><INPUT maxLength=4 name=nd size=22 value="ZBDM"><font color="red">*</font></DIV>
                </TD>
              </TR>
               <TR>
                <TD class="2-td1-Bcenter">
                  指标名称&nbsp;
                </TD>
                <TD class="2-td2-right">
                  <DIV align=left><INPUT maxLength=100 name=nd size=22 value="ZBMC"><font color="red">*</font></DIV>
                </TD>
              </TR>
               <TR>
                <TD class="2-td1-Bcenter">
                  指标值&nbsp;
                </TD>
                <TD class="2-td2-right">
                  <DIV align=left><INPUT maxLength=100 name=nd size=22 value="9432"><font color="red">*</font></DIV>
                </TD>
              </TR>
               <TR>
                <TD class="2-td1-Bcenter">
                  有效时间段&nbsp;
                </TD>
                <TD class="2-td2-right">
                  <DIV align=left><INPUT maxLength=4 name=nd size=22" value="2004-01-01"><font color="red">*</font></DIV>
                </TD>
              </TR>
               <TR>
                <TD class="2-td1-Bcenter">
                  备注&nbsp;
                </TD>
                <TD class="2-td2-right">
                  <DIV align=left><INPUT maxLength=4 name=nd size=22" value="注意有效时间段的问题"><font color="red">*</font></DIV>
                </TD>
              </TR>
     </TABLE><BR>

      <DIV align=center>
      <IMG alt=保存 height=22 id=baocun name=baocun
      onclick="checkSubmit('Save')" onmouseout=MM_swapImgRestore()
      onmouseover="MM_swapImage('tuichu','','http://192.100.99.99/images/tuichu2.jpg',1)"
      src="./images/baocun1.jpg" style="CURSOR: hand" width=79>
      <IMG alt=退出 height=22 id=tuichu name=tuichu
      onclick="return history.back();" onmouseout=MM_swapImgRestore()
      onmouseover="MM_swapImage('tuichu','','http://192.100.99.99/images/tuichu2.jpg',1)"
      src="./images/tuichu1.jpg" style="CURSOR: hand" width=79>
      </DIV><BR>

            <TABLE border=0 cellPadding=0 cellSpacing=0 width="99%">
              <TBODY>
              <TR>
                <TD>
                  <HR class=hr1 SIZE=1 width="100%">
                </TD>
                <TD align=middle class=black9 width=99><STRONG><FONT
                  color=#0000ff>提 示 信 息</FONT></STRONG></TD>
                <TD>
                  <HR class=hr1 SIZE=1 width="100%">
                </TD></TR></TBODY></TABLE>
            <TABLE align=center border=1 borderColor=#ffffff cellPadding=1
            cellSpacing=1 class=black9 width="99%">
              <TBODY>
              <TR bgColor=#f6f6f6 borderColor=#9bb4ca>
                <TD height=47>
                  <P></P>
                  <UL><BR>
                    <LI><FONT
            color=#cc0000></FONT><BR></LI></UL></TD></TR></TBODY></TABLE>
            <TABLE border=0 cellPadding=0 cellSpacing=0 width="99%">
              <TBODY>
              <TR>
                <TD>
                  <HR SIZE=1
                  style="BORDER-BOTTOM: dashed; BORDER-LEFT: dashed; BORDER-RIGHT: dashed; BORDER-TOP: dashed; COLOR: #003399"
                  width="100%">
                </TD>
                <TD align=middle class=black9 width=99><STRONG><FONT
                  color=#0000ff>注 意 事 项</FONT></STRONG> </TD>
                <TD>
                  <HR SIZE=1
                  style="BORDER-BOTTOM: dashed; BORDER-LEFT: dashed; BORDER-RIGHT: dashed; BORDER-TOP: dashed; COLOR: #003399"
                  width="100%">
                </TD></TR></TBODY></TABLE>
            <TABLE align=center border=1 borderColor=#ffffff cellPadding=1
            cellSpacing=1 class=black9 width="99%">
              <TBODY>
              <TR bgColor=#f6f6f6 borderColor=#9bb4ca>
                <TD height=47><BR>&nbsp;&nbsp;&nbsp;&nbsp; 点击“退出”按钮即可结束此项业务;
                  <BR>&nbsp;&nbsp;&nbsp;&nbsp;</TD></TR></TBODY></TABLE><BR></TD></TR></FORM></TBODY></TABLE><BR></TD></TR></TBODY></TABLE>
<TABLE border=0 cellPadding=0 cellSpacing=0 height=33 width="100%">
  <TBODY>
  <TR>
    <TD background=./images/q-bottom-bg-01.jpg colSpan=2 height=9
    noWrap></TD></TR>
  <TR>
    <TD noWrap><FONT size=2>&nbsp;&nbsp;&nbsp;*必须使用 IE 5.5 以上，分辨率 800*600
      浏览本网站</FONT></TD>
    <TD align=right height=24><IMG alt=承办单位：清华同方软件股份有限公司 height=24
      src="./images/q-bottom-tu-01.jpg"
width=184></TD></TR></TBODY></TABLE></BODY></HTML>
