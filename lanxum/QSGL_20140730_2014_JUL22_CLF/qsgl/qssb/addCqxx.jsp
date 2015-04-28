<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<HTML><HEAD><TITLE>录入拆迁信息表</TITLE>
  <META content="text/html; charset=gb2312" http-equiv=Content-Type>
 <SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
          type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js"></SCRIPT>
<script language="javascript" src="../js/qscommon.js"></script>

<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css><LINK
  href="<%=static_file%>css/piaozheng.css" rel=stylesheet type=text/css>
  <META content="MSHTML 5.00.2919.6307" name=GENERATOR></HEAD>

<SCRIPT language=JavaScript>
function doSubmitForm(operationType)
{
      document.forms[0].operationType.value=operationType;
      if(operationType=="Save")
   {
        document.forms[0].operationType.value='Save';

          if(document.forms[0].fjmc.value == "")
        {
            alert("对不起，拆迁协议号码不能为空，请重新输入！");
            document.forms[0].fjmc.focus();
            return false;
        }

		          if(document.forms[0].zsdmc.value == "")
        {
            alert("对不起，拆迁协议号码不能为空，请重新输入！");
            document.forms[0].zsdmc.focus();
            return false;
        }
		          if(document.forms[0].nd.value == "")
        {
            alert("对不起，拆迁协议号码不能为空，请重新输入！");
            document.forms[0].nd.focus();
            return false;
        }

        		          if(document.forms[0].nd.value.length!=4)
        {
            alert("对不起，拆迁协议号码中年度信息应为4位，请检查！");
            document.forms[0].nd.focus();
            return false;
        }


		if(document.forms[0].lsh.value == "")
        {
            alert("对不起，拆迁协议号码不能为空，请重新输入！");
            document.forms[0].lsh.focus();
            return false;
        }

         if(document.forms[0].bcqfwzldz.value == "")
        {
            alert("对不起，被拆迁房屋坐落地址不能为空，请重新输入！");
            document.forms[0].bcqfwzldz.focus();
            return false;
        }

         if(document.forms[0].cqbce.value == "" || !isAllCharValid(document.forms[0].cqbce.value,"1234567890.,"))
        {
            alert("对不起，拆迁补偿额不能为空或不能为汉字，请重新输入！");
            document.forms[0].cqbce.focus();
            return false;
        }

        if(document.forms[0].bcsybce.value == "" || !isAllCharValid(document.forms[0].bcsybce.value,"1234567890.,"))
        {
            alert("对不起，本次使用补偿额不能为空或不能为汉字，请重新输入！");
            document.forms[0].bcsybce.focus();
            return false;
        }


        if(parseFloat(document.forms[0].cqbce.value) == 0)
        {
            alert("对不起，拆迁补偿额不能为零！");
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

     else if(operationType=="quit")
     {
            document.forms[0].operationType.value='Return';
     }
            document.forms[0].submit();
}

//新增录入或者修改时，与本次可使用补偿额和补偿剩余额联动，自动等于拆迁补偿额
function fnChangeCqbce()
{
   if (!FN_QSCheckNumberDigit(document.forms[0].cqbce.value,2,"拆迁补偿额"))
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

   //新增拆迁信息本次可使用补偿额=补偿额
   //现存拆迁信息本次可使用补偿额=补偿额变化 + 本次可使用
   if (document.forms[0].first.value == "true")
   {
      fBcksybce = fCqbce;
   }
   else
   {
      fBcksybce = floatplus(floatminus(fCqbce,fOldCqbce),parseFloat(fBcksybce));
   }
   //剩余补偿额=可使用补偿额-本次使用额
   fCqbcsye  = floatminus(fBcksybce,fBcsybce);
   document.forms[0].bcksybce.value = fBcksybce;
   document.forms[0].cqbcsye.value = fCqbcsye;
   labelBcksybce.innerText = document.forms[0].bcksybce.value;
   labelCqbcsye.innerText = document.forms[0].cqbcsye.value;

   document.forms[0].oldCqbce.value = fCqbce;

   if (fCqbcsye < 0)
   {
      alert("拆迁补偿剩余额不能小于零!");
      document.forms[0].cqbce.focus();
      return false;
   }
   return true;
}

//本次使用补偿额与补偿剩余额联动，自动等于本次可使用补偿额-减去本次使用补偿额
function fnChangeBcsybce()
{
   if (!FN_QSCheckNumberDigit(document.forms[0].bcsybce.value,2,"本次使用补偿额"))
   {
      document.forms[0].bcsybce.focus();
      return false;
   }

   document.forms[0].cqbcsye.value = floatminus(document.forms[0].bcksybce.value , document.forms[0].bcsybce.value);
   labelCqbcsye.innerText = document.forms[0].cqbcsye.value;

	fCqbcsye = parseFloat(document.forms[0].cqbcsye.value);
   if (fCqbcsye < 0)
   {
      alert("拆迁补使用补偿额不能大于本次可使用补偿额！");
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
   if (document.forms[0].bcsybce.value == "")
   {
      document.forms[0].bcsybce.value = "0.00";
   }
}


        </SCRIPT>
        <!----本页面的头文件和本页帮助的路径----->

  <BODY bgColor=#ffffff leftMargin=0
    onload="MM_preloadImages('<%=static_file%>imagess/dayin2.jpg','<%=static_file%>imagess/fanhui2.jpg','<%=static_file%>imagess/tuichu2.jpg','<%=static_file%>imagess/diyitiao2.jpg','<%=static_file%>imagess/shangyitiao2.jpg','<%=static_file%>imagess/zuimotiao2.jpg','<%=static_file%>imagess/xinzeng2.jpg','<%=static_file%>imagess/shanchu2.jpg'),fnOnLoad()"
    topMargin=0 marginheight="0" marginwidth="0">
                      <jsp:include page="/include/Header.jsp" flush="true">
                       <jsp:param name="subtitle" value="契税申报>录入拆迁信息表"/>
                       <jsp:param name="helpURL" value=""/>
                      </jsp:include>
<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
  <TBODY>
    <TR>
      <TD vAlign=top>

    <TABLE align=center cellSpacing=0 class=table-99>
      <TBODY>
        <TR>
          <TD class=1-td1>契税申报表-拆迁情况</TD></TR>
      <TR>
      <TD class=1-td2 vAlign=top>
        <html:form action="/qssb/addCqxx.do">
          <html:hidden property="operationType" value=""/>
          <html:hidden property="person" />
          <html:hidden property="first" />
          <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
            <TBODY>
                <BR>
            <TR>
              <TD class=2-td2-t-left width="25%">
                <DIV align=right>申报表号&nbsp; </DIV>
              </TD>
              <TD colspan="5" class=2-td2-t-center width="15%">
                <html:hidden name="cqxxForm" property="sbbh"/>
                <DIV align=left>&nbsp;<bean:write name="cqxxForm" property="sbbh" /></DIV></TD>
              </TR>
              <TR>
                <TD class=2-td2-left width="8%" rowspan = "4">
                  <DIV align=right>	拆迁</DIV>
                  <DIV align=right>情况</DIV><br>
                  </TD>
                  <TD class=2-td2-left width="25%">
                    <DIV align=right>拆迁协议号码&nbsp; </DIV>
                  </TD>
                  <!--TD colspan="4" class=2-td2-center width="15%">
                    <DIV align=left><html:text property="cqxyhm" maxlength="30" onchange="doSubmitForm('Get')"/><FONT color=red>*</FONT>
                    </DIV></TD-->
					<html:hidden property="cqxyhm" />

                  <TD class=2-td2-center colspan="4">
                    <DIV align=left>京<html:text property="fjmc" size="6" maxlength="6"/>地税<html:text property="zsdmc" size="6" maxlength="6"/>契拆字<html:text property="nd" size="4" maxlength="4"/>第<html:text property="lsh" size="4" maxlength="5" onchange="doSubmitForm('Get')"/>号<FONT color=red>*</FONT>
                    </DIV></TD>



                  </TR>
              <TR>
                <TD class=2-td2-left width="15%";>
                  <DIV align=right>被拆迁房屋坐落地址&nbsp; </DIV>
                </TD>
                <TD colspan="4" class=2-td2-center width="25%">
                  <DIV align=left><html:text property="bcqfwzldz" size="40" maxlength="100"/><FONT color=red>*</FONT> </DIV></TD>
                </TR>
            <TR>
              <TD class=2-td2-left width="15%";>
                <DIV align=right> 拆迁补偿额&nbsp; </DIV>
              </TD>
              <TD  class=2-td2-left width="25%">
                <DIV align=left><html:text property="cqbce" maxlength="15" onchange="fnChangeCqbce()"/><FONT color=red>*</FONT> 元(人民币) </DIV></TD>
                     <input type="hidden" name="oldCqbce">
                <TD class=2-td2-left width="17%">
                  <DIV align=right>本次可使用补偿额&nbsp;</DIV></TD>
                  <TD colspan="2"  class=2-td2-center width="25%">
                    <DIV align=left><label id="labelBcksybce">0.00</label>
                     元(人民币) </DIV></TD>
                     <html:hidden property="bcksybce" />
                  </TR>
          <TR>
            <TD class=2-td2-left width="15%";>
              <DIV align=right>本次使用补偿额&nbsp; </DIV>
            </TD>
            <TD class=2-td2-left width="25%">
              <DIV align=left><html:text property="bcsybce" maxlength="15" onchange="javascript:fnChangeBcsybce()"/><FONT color=red>*</FONT> 元(人民币)</DIV></TD>
              <TD class=2-td2-left width="17%">
                <DIV align=right>拆迁补偿剩余额&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="32%">
                  <DIV align=left><label id="labelCqbcsye">0.00</label>
                   元(人民币)</DIV></TD>
                   <html:hidden property="cqbcsye"  />
                </TR>

  </TBODY></TABLE><BR>

<DIV align=left>
              <html:hidden property="syeywbz"  />
              <input type="checkbox" name="syeywbz1" onclick="fn_change()"/>剩余额是否为0
</DIV><BR>

      <DIV align=center>
        <IMG alt=保存 height=22 id=baocun name=Submit63
          onclick="doSubmitForm('Save')" onmouseout=MM_swapImgRestore()
          onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
          src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79>

          <IMG alt=退出 height=22 id=tuichu name=tuichu
            onclick="doSubmitForm('Return')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
          </DIV><BR>

  </html:form>
  <%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>

<script language="javascript">
if (document.forms[0].lsh.value != "")
{
    document.forms[0].bcqfwzldz.focus();
}
else
{
    document.forms[0].fjmc.focus();
}

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
