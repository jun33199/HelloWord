<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<HTML><HEAD><TITLE>增加政策维护表</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  if(operationType=="save"){
    document.forms[0].operationType.value='Save';
  }else if(operationType=="quit"){
    document.forms[0].operationType.value='Return';
  }
  document.forms[0].submit();
}
</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0
onload="MM_preloadImages('<%=static_file%>images/fanhui2.jpg','<%=static_file%>images/tuichu2.jpg'," topMargin=0 marginheight="0" marginwidth="0">
 <jsp:include page="/include/Header.jsp" flush="true">
                <jsp:param name="subtitle" value="信息维护&gt;增加政策维护表"/>
                <jsp:param name="helpURL" value=""/>
              </jsp:include>
<script language=javascript>
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
      <table align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>契税政策维护-增加</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/xxwh/addZcwh.do">
              <html:hidden property="operationType" value=""/>
            <br>
             <table border="0" cellSpacing=0 class=table-60>
              <TBODY>
               <TR>
                <TD class=2-td1-center width="50%">
                  指标代码&nbsp;
                </TD>
                <TD class="2-td2-t-right" width="50%">
                  <DIV align=left><html:text property="zbdm" maxlength="4"/><font color="red">*</font></DIV>
                </TD>
              </TR>
               <TR>
                <TD class="2-td1-Bcenter">
                  指标名称&nbsp;
                </TD>
                <TD class="2-td2-right">
                  <DIV align=left><html:text property="zbmc" maxlength="100"/><font color="red">*</font></DIV>
                </TD>
              </TR>
               <TR>
                <TD class="2-td1-Bcenter">
                  指标值&nbsp;
                </TD>
                <TD class="2-td2-right">
                  <DIV align=left><html:text property="zbz" maxlength="100"/><font color="red">*</font></DIV>
                </TD>
              </TR>
               <TR>
                <TD class="2-td1-Bcenter">
                  生效起始时间&nbsp;
                </TD>
                <TD class="2-td2-right">
                  <DIV align=left><html:text property="qssj" /><font color="red">*</font></DIV>
                </TD>
              </TR>
               <TR>
                <TD class="2-td1-Bcenter">
                  生效截止时间&nbsp;
                </TD>
                <TD class="2-td2-right">
                  <DIV align=left><html:text property="jzsj" /><font color="red">*</font></DIV>
                </TD>
              </TR>
               <TR>
                <TD class="2-td1-Bcenter">
                  备注&nbsp;
                </TD>
                <TD class="2-td2-right">
                  <DIV align=left><html:text property="bz" maxlength="100"/><font color="red">*</font></DIV>
                </TD>
              </TR>
     </TABLE><BR>

      <DIV align=center>
        <IMG name="baocun"
          onClick= "doSubmitForm('save');"
          onMouseOver="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/baocun1.jpg"
          width="79" height="22" id="baocun" alt="保存" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

        <IMG name="tuichu1"
          onclick="doSubmitForm('quit');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">
      </DIV><BR>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
