<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<HTML><HEAD><TITLE>��˰�����걨</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  if(operationType=="query" ){
    document.forms[0].operationType.value='Query';
  }else if(operationType=="add"){
    alert('1');
    document.forms[0].operationType.value='Add';
    alert('2');
  }else if(operationType=="quit"){
    document.forms[0].operationType.value='Return';
  }else if(operationType=="get"){
    document.forms[0].operationType.value='Get';
    return;
  }
  document.forms[0].submit();
}
</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
 <jsp:include page="/include/Header.jsp" flush="true">
                <jsp:param name="subtitle" value="��˰�걨&gt;���ӷ���������Ϣ"/>
                <jsp:param name="helpURL" value=""/>
              </jsp:include>
<SCRIPT language=javascript>
<!--

function popUp(){
    var server = '192.100.99.100';
    var port   = '80';
    props=window.open('<%=static_file%>' + '/about/about.htm','poppage','toolbars=0,scrollbars=0,location=0,statusbara=0,menubars=0,resizable=0,width=500,height=267');
}

//-->
</SCRIPT>
<br>


<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>¼����˰�����걨��</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/qssb/addQsjmsb.do">
            <input type = "hidden" name = "operationType" value = "" >
            <TABLE background=./images/biaopqian-bg.jpg border=0 cellPadding=0 cellSpacing=0 width="100%">
             <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
               <TR>
                <TD class=2-td2-t-left width="15%">
                  <DIV align=right>�����걨���&nbsp; </DIV>
                </TD>
                <TD class=2-td2-t-left width="15%">
                  <DIV align=left><html:text name = "qsjmsbForm" property = "sbbh" maxlength="19"/>&nbsp;
                  <input type="button" value="��ѯ" onclick="doSubmitForm('Get')"></DIV>
                </TD>
                <TD  class=2-td2-t-left width="15%">
                   <DIV align=right>�����걨���&nbsp; </DIV>
                </TD>
                   <DIV align=right>&nbsp; </DIV>
                <TD class=2-td2-t-center width="50%">
                    <DIV align=left><bean:write name = "qsjmsbForm" property = "jmsbbh"/>&nbsp;</DIV>
               </TD>
              </TR>
               <TR>
                <TD class=2-td2-left width="15%">
                  <DIV align=right>��˰������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left width="15%">
                  <DIV align=left><bean:write name = "qsjmsbForm" property = "nsrmc"/>&nbsp;</DIV>
                </TD>
                <TD class=2-td2-left width="15%">
                   <DIV align=right>��˰������&nbsp; </DIV>
                </TD>
                   <DIV align=right>&nbsp; </DIV>
                <TD class=2-td2-center width="50%">
                <DIV align=left size=30><bean:write name = "qsjmsbForm" property = "nsrlx"/>&nbsp;</DIV>
               </TD>
              </TR>
              <TR>
               <TD class=2-td2-left  >
                 <DIV align=right>���������(��λ)&nbsp; </DIV>
               </TD>
               <TD class=2-td2-left width="15%">
                 <DIV align=left><bean:write name = "qsjmsbForm" property = "jsjdm"/>&nbsp;</DIV>
               </TD>
                <TD class=2-td2-left width="20%">
                  <DIV align=right>��ϵ�绰&nbsp;</DIV>
                </TD>
                <TD class=2-td2-center width="30%">
                   <DIV align=left><bean:write name = "qsjmsbForm" property = "lxdh"/>&nbsp;</DIV>
                </TD>
              </TR>
               <TR>
                <TD class=2-td2-left width="15%";>
                  <DIV align=right>��˰�˸��˺Ϸ������֤������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left width="25%">
                  <DIV align=left><bean:write name = "qsjmsbForm" property = "nsrzj"/>&nbsp;</DIV>
                 </TD>
                <TD class=2-td2-left width="19%">
                  <DIV align=right>���˺Ϸ����֤������&nbsp;</DIV>
                </TD>
                <TD class=2-td2-center width="33%">
                   <DIV align=left><bean:write name = "qsjmsbForm" property = "sfzjhm"/>&nbsp;</DIV>
                </TD>
              </TR>
     </TABLE><BR>

     <TABLE align=center cellSpacing=0 class=table-99>
      <TBODY>
          <TR>
              <TD class=2-td2-t-left width="8%" rowspan = "7">
                  <DIV align=right>	���ط���</DIV>
                  <DIV align=right>Ȩ��ת��</DIV>
                  <DIV align=right>���</DIV>
              </TD>
          </TR>
          <TR>
              <TD class=2-td2-t-left  >
                 <DIV align=right>���ز���Ŀ����&nbsp; </DIV>
              </TD>
              <TD class=2-td2-t-left width="22%">
                 <DIV align=left><bean:write name = "qsjmsbForm" property = "fdcxmmc"/>&nbsp;</DIV>
              </TD>
              <TD class=2-td2-t-left width="19%">
                 <DIV align=right>��ͬǩ��ʱ��&nbsp;</DIV>
              </TD>
              <TD colspan="2"  class=2-td2-t-center width="33%">
                  <DIV align=left><bean:write name = "qsjmsbForm" property = "htqdsj"/>&nbsp;</DIV>
              </TD>
              </TR>
              <TR>
                <TD class=2-td2-left width="20%";>
                   <DIV align=right>���ء����������ַ&nbsp; </DIV>
                </TD>
                <TD colspan="5" class=2-td2-center width="19%">
                   <DIV align=left><bean:write name = "qsjmsbForm" property = "zldz"/>&nbsp;</DIV>
                </TD>
               </TR>
               <TR>
                   <TD  class=2-td2-left width="20%";>
                    <DIV align=right>���ء�����Ȩ��������ʽ&nbsp; </DIV>
                   </TD>
                   <TD colspan="4"  class=2-td2-center width="15%">
                     <DIV align=left><bean:write name = "qsjmsbForm" property = "csxs"/>&nbsp;</DIV>
                   </TD>
                </TR>
                <TR>
                  <TD class=2-td2-left width="20%";>
                   <DIV align=right>���ء�����Ȩ���������&nbsp; </DIV>
                  </TD>
                  <TD class=2-td2-left width="22%">
                   <DIV align=left><bean:write name = "qsjmsbForm" property = "csmj"/> m2&nbsp; </DIV>
                  </TD>
                  <TD class=2-td2-left width="17%">
                    <DIV align=right>���ݽ������&nbsp;</DIV>
                  </TD>
                  <TD colspan="2"  class=2-td2-center width="32%">
                    <DIV align=left><bean:write name = "qsjmsbForm" property = "fwjzmj"/> m2&nbsp;</DIV>
                  </TD>
               </TR>
               <TR>
                  <TD class=2-td2-left width="8%" rowspan = "2">
                    <DIV align=right>�ɽ��۸�</DIV>
                    <DIV align=right>�������۸�</DIV>
                  <TD  class=2-td2-left width="5%">
                    <DIV align=left><bean:write name = "qsjmsbForm" property = "cjjgrmb"/>Ԫ(�����)</DIV>
                  </TD>
                  <TD class=2-td2-left width="17%">
                    <DIV align=right>˰����غ˶��۸�&nbsp; </DIV></TD>
                  <TD  colspan="2"  class=2-td2-center width="32%">
                     <DIV align=left><bean:write name = "qsjmsbForm" property = "pgjgrmb"/>Ԫ(�����)</DIV></TD>
                  </TR>
               <TR>
                  <TD class=2-td2-left width="15%";>
                    <DIV align=left><bean:write name = "qsjmsbForm" property = "cjjgwb"/>Ԫ(���) </DIV>
                  </TD>
                  <TD class=2-td2-left width="25%">
                    <DIV align=left><bean:write name = "qsjmsbForm" property = "wbmc"/></DIV>
                  </TDa>
                  <DIV align=left><bean:write name = "qsjmsbForm" property = "hl"/>���� </DIV>
                  </TD>
                  <TD class=2-td2-center  width="17%">
                    <DIV align=left><bean:write name = "qsjmsbForm" property = "zhrmb"/>�ۺ�Ԫ(�����) </DIV>
                  </TR>
          </TABLE><BR>
        <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
            <TR>
                <TD class=2-td2-t-left width="15%";>
                   <DIV align=right>�걨��������&nbsp; </DIV>
                </TD>
                <TD colspan="5" class=2-td2-t-center width="84">
                  <div align="left">1�����һ��ء���ҵ��λ��������塢���µ�λ�������ء��������ڰ칫����ѧ��ҽ�ơ����к;�����ʩ��
                      <html:multibox name = "qsjmsbForm" property = "sbjmzc" value = "1" />
                  </div>
                  <DIV align=left>
                      <textarea name = "bz" cols="45" rows="5" value="" readonly="true"></textarea>
                  </DIV>
                </TD>
            </TR>
            <TR>
                 <TD  colspan="6" class=2-td2-left width="15%";>
                   <DIV align=left>&nbsp&nbsp&nbsp&nbsp<B>˰����������ʾ��</B><br>&nbsp;&nbsp;&nbsp;&nbsp;��ʵ��������걨�������������˰��˰��Ȩ���������ṩ�����ء�����Ȩ��ת�Ʊ䶯������������ĸ�������Ӧ��ʵ�ɿ������걨��ʵ������걨�����ݡ��л����񹲺͹�˰�����չ�������ʵʩϸ��Ĺ涨��˰����ؿ��Դ�һ��Ԫ���µķ��������صģ���һ��Ԫ��������Ԫ���µķ�����ɷ���ģ�����׷���������Ρ�&nbsp; </DIV>
                </TD>

            </TR>
            <TR>
                <TD colspan="3" class=2-td2-left width="15%";>
                   <DIV align=left>��˰��ǩ�£�&nbsp; </DIV>
                   <DIV align=left>���˴���ǩ�£�&nbsp; </DIV>
                </TD>
                <TD colspan="3" class=2-td2-center width="84">
                 <DIV align=left>�걨��ǩ�£�&nbsp; </DIV>
                 <DIV align=left>(��Ȩί����)&nbsp; </DIV>
                </TD>
               </TBODY></TABLE><BR>

            <DIV align=center>

        <IMG name="add"
          onClick= "doSubmitForm('add');"
          onMouseOver="MM_swapImage('tianjia','','<%=static_file%>images/tianjia2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tianjia1.jpg"
          width="79" height="22" id="tianjia" alt="����" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;

        <IMG name="back"
          onclick="doSubmitForm('quit');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="�˳�" style="cursor:hand">
            </DIV><BR>

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY>
</HTML>
