<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<HTML><HEAD><TITLE>��Ǩ��Ϣ�޸ı�</TITLE>
  <META content="text/html; charset=gb2312" http-equiv=Content-Type>
    <SCRIPT language=JavaScript src=<%=static_file%>js/list.js"
      type=text/JavaScript></SCRIPT>
<script language="javascript" src="../js/qscommon.js"></script>

      <SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>

      <SCRIPT language=JavaScript>
		function doSubmitForm(operationType){
		  document.forms[0].operationType.value=operationType;
		  
		  
		  if (document.forms[0].operationType.value == "Save")
		  {
		
		         if(document.forms[0].bcqfwzldz.value == "")
		        {
		            alert("�Բ��𣬱���Ǩ���������ַ����Ϊ�գ����������룡");
		            document.forms[0].bcqfwzldz.focus();
		            return false;
		        }
		
		         if(document.forms[0].cqbce.value == "" || !isAllCharValid(document.forms[0].cqbce.value,"1234567890.,"))
		        {
		            alert("�Բ��𣬲�Ǩ�������Ϊ�ջ���Ϊ���֣����������룡");
		            document.forms[0].cqbce.focus();
		            return false;
		        }
		
		        if(document.forms[0].bcsybce.value == "" || !isAllCharValid(document.forms[0].bcsybce.value,"1234567890.,"))
		        {
		            alert("�Բ��𣬱���ʹ�ò������Ϊ�ջ���Ϊ���֣����������룡");
		            document.forms[0].bcsybce.focus();
		            return false;
		        }
		
		        if(parseFloat(document.forms[0].cqbce.value) == 0)
		        {
		            alert("�Բ��𣬲�Ǩ�������Ϊ�㣡");
		            document.forms[0].cqbce.focus();
		            return false;
		        }
	if(document.forms[0].syeywbz1.checked==true)
	{
		document.forms[0].syeywbz.value="on"
	}
	else
	{
		document.forms[0].syeywbz.value="off"
	}
				if (!fnChangeCqbce())
				   return false;
				if (!fnChangeBcsybce())
				   return false;				
		  }
		  
		  
	      document.forms[0].submit();
		}

//����¼������޸�ʱ���뱾�ο�ʹ�ò�����Ͳ���ʣ����������Զ����ڲ�Ǩ������
function fnChangeCqbce()
{
   if (!FN_QSCheckNumberDigit(document.forms[0].cqbce.value,2,"��Ǩ������"))   
   {
      document.forms[0].cqbce.focus();
      return false;
   }
   
   if (!parseFloat(document.forms[0].cqbce.value))
       return;

   fCqbce = parseFloat(document.forms[0].cqbce.value);
   fOldCqbce = parseFloat(document.forms[0].oldCqbce.value);

   if (!parseFloat(document.forms[0].bcksybce.value))
   	   fBcksybce = 0.00;
   else
       fBcksybce = document.forms[0].bcksybce.value;

   if (!parseFloat(document.forms[0].bcsybce.value))
   	   fBcsybce = 0.00;
   else
       fBcsybce = document.forms[0].bcsybce.value;

   if (!parseFloat(document.forms[0].cqbcsye.value))
   	   fCqbcsye = 0.00;
   else
       fCqbcsye = document.forms[0].cqbcsye.value;

   //���ο�ʹ�ò�����=������-ԭ�Ȳ�����+��ʹ�ò�����
   //ʣ�ಹ����=��ʹ�ò�����-����ʹ�ö�
   fBcksybce = floatplus(floatminus(fCqbce,fOldCqbce) , parseFloat(fBcksybce));
   fCqbcsye  = floatminus(fBcksybce,fBcsybce);
   document.forms[0].bcksybce.value = fBcksybce;
   document.forms[0].cqbcsye.value = fCqbcsye;
   labelBcksybce.innerText = document.forms[0].bcksybce.value;
   labelCqbcsye.innerText = document.forms[0].cqbcsye.value;

   document.forms[0].oldCqbce.value = fCqbce;
   
   if (fCqbcsye < 0)
   {
      alert("��Ǩ����ʣ����С����!");
      document.forms[0].cqbce.focus();
      return false;
   }
   return true;   
}

//����ʹ�ò������벹��ʣ����������Զ����ڱ��ο�ʹ�ò�����-��ȥ����ʹ�ò�����
function fnChangeBcsybce()
{
   if (!FN_QSCheckNumberDigit(document.forms[0].bcsybce.value,2,"����ʹ�ò�����"))   
   {
      document.forms[0].bcsybce.focus();
      return false;
   }

   document.forms[0].cqbcsye.value = floatminus(document.forms[0].bcksybce.value,document.forms[0].bcsybce.value);
   labelCqbcsye.innerText = document.forms[0].cqbcsye.value;
   
	fCqbcsye = parseFloat(document.forms[0].cqbcsye.value);
   if (fCqbcsye < 0)
   {
      alert("��Ǩ����ʣ����С����!");
      document.forms[0].bcsybce.focus();
      return false;
   }
   return true;   
}

function fn_change()
{
	if(document.forms[0].syeywbz1.checked==true)
	{
		document.forms[0].bcsybce.readOnly=true
	}
	else
	{
		document.forms[0].bcsybce.readOnly=false
	}
}

function fnOnLoad()
{
   document.forms[0].bcksybce.value = floatplus(parseFloat(document.forms[0].cqbcsye.value), parseFloat(document.forms[0].bcsybce.value));
   labelBcksybce.innerText = document.forms[0].bcksybce.value;
   labelCqbcsye.innerText = document.forms[0].cqbcsye.value;
   if (document.forms[0].cqbce.value != "")
   {
       document.forms[0].oldCqbce.value = document.forms[0].cqbce.value;
   }
   else
   {
       document.forms[0].oldCqbce.value = '0.00';
   }

}


</SCRIPT>
<!----��ҳ���ͷ�ļ��ͱ�ҳ������·��----->
<SCRIPT language=javascript src="<%=static_file%>js/Header.js"></SCRIPT>

<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
  type=text/JavaScript></SCRIPT>
  <LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css><LINK
    href="<%=static_file%>css/piaozheng.css" rel=stylesheet type=text/css>
    <META content="MSHTML 5.00.2919.6307" name=GENERATOR></HEAD>
    <BODY bgColor=#ffffff leftMargin=0
      onload="MM_preloadImages('<%=static_file%>imagess/dayin2.jpg','<%=static_file%>imagess/fanhui2.jpg','<%=static_file%>imagess/tuichu2.jpg','<%=static_file%>imagess/diyitiao2.jpg','<%=static_file%>imagess/shangyitiao2.jpg','<%=static_file%>imagess/zuimotiao2.jpg','<%=static_file%>imagess/xinzeng2.jpg','<%=static_file%>imagess/shanchu2.jpg'),fnOnLoad()"
      topMargin=0 marginheight="0" marginwidth="0">
                        <jsp:include page="/include/Header.jsp" flush="true">
                         <jsp:param name="subtitle" value="��˰�걨>��Ǩ��Ϣ�޸ı�"/>
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
<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
  <TBODY>
    <TR>
      <TD vAlign=top>

      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
          <TR>
            <TD class=1-td1>��˰�걨��-��Ǩ���</TD></TR>
            <TR>
              <TD class=1-td2 vAlign=top>
                <html:form action="/qssb/modifyCqxx.do">
                  <html:hidden property="operationType" value=""/>
                  <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
                    <TBODY>
                      <BR>
              <TR>
                <TD class=2-td2-t-left width="15%">
                  <DIV align=right>�걨����&nbsp; </DIV>
                </TD>
                <TD colspan="5" class=2-td2-t-center width="15%">
                  <html:hidden name="cqxxForm" property="sbbh"/>
                  <DIV align=left>&nbsp;<bean:write name="cqxxForm" property="sbbh" /> </DIV></TD>
                </TR>
                <TR>
                  <TD class=2-td2-left width="8%" rowspan = "4">
                    <DIV align=right>	��Ǩ</DIV>
                    <DIV align=right>���</DIV><br>
                    </TD>
                    <TD class=2-td2-left width="25%">
                      <DIV align=right>��ǨЭ�����&nbsp; </DIV>
                    </TD>
                    <TD colspan="4" class=2-td2-center width="15%">
                      <DIV align=left>&nbsp;<bean:write name="cqxxForm" property="cqxyhm" /> </DIV></TD>
                    </TR>
                <TR>
                  <TD class=2-td2-left width="15%";>
                    <DIV align=right>����Ǩ���������ַ&nbsp; </DIV>
                  </TD>
                  <TD colspan="4" class=2-td2-center width="25%">
                    <DIV align=left><html:text property="bcqfwzldz" maxlength="100" /> <FONT color=red>*</FONT> </DIV></TD>
                  </TR>
                <TR>
                  <TD class=2-td2-left width="15%";>
                    <DIV align=right> ��Ǩ������&nbsp; </DIV>
                  </TD>
                  <TD  class=2-td2-left width="25%">
                    <DIV align=left><html:text property="cqbce" maxlength="15" onchange="fnChangeCqbce()"/><FONT color=red>*</FONT> Ԫ(�����) </DIV></TD>
                    <input type="hidden" name="oldCqbce">
                      <TD class=2-td2-left width="17%">
                        <DIV align=right>���ο�ʹ�ò�����&nbsp;</DIV></TD>
                        <TD colspan="2"  class=2-td2-center width="25%">
                          <DIV align=left><label id="labelBcksybce">0.00</label>
                            Ԫ(�����) </DIV></TD>
                            <html:hidden property="bcksybce" />
                          </TR>
                <TR>
                  <TD class=2-td2-left width="15%";>
                    <DIV align=right>����ʹ�ò�����&nbsp; </DIV>
                  </TD>
                  <TD class=2-td2-left width="25%">
                    <DIV align=left><html:text property="bcsybce" maxlength="15" onchange="fnChangeBcsybce()"/><FONT color=red>*</FONT> Ԫ(�����)</DIV></TD>
                    <TD class=2-td2-left width="17%">
                      <DIV align=right>��Ǩ����ʣ���&nbsp;</DIV></TD>
                      <TD colspan="2"  class=2-td2-center width="32%">
                        <DIV align=left><label id="labelCqbcsye">0.00</label>
                          Ԫ(�����)</DIV></TD>
                          <html:hidden property="cqbcsye"  />
                        </TR>

    </TBODY></TABLE><BR>

<DIV align=left>
              <html:hidden property="syeywbz"  />
              <input type="checkbox" name="syeywbz1" onclick="fn_change()"/>ʣ����Ƿ�Ϊ0
</DIV><BR>

      <DIV align=center>
        <IMG alt=���� height=22 id=baocun name=Submit63
          onclick="doSubmitForm('Save')" onmouseout=MM_swapImgRestore()
          onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
          src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79>

          <IMG alt=�˳� height=22 id=tuichu name=tuichu
            onclick="doSubmitForm('Return')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
          </DIV><BR>

    </html:form>
    <%@ include file="../include/Bottom.jsp" %>
  </BODY></HTML>
<script language="javascript">


if(document.forms[0].syeywbz.value=="on")
{
	document.forms[0].syeywbz1.checked=true
}
else
{
	document.forms[0].syeywbz1.checked=false
}
if(document.forms[0].syeywbz1.checked==true)
{
	document.forms[0].bcsybce.readOnly=true
}
else
{
	document.forms[0].bcsybce.readOnly=false
}
</script>