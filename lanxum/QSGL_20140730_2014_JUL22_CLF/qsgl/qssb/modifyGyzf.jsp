<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<HTML><HEAD><TITLE>已购公有住房/经济适用住房信息修改表</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src=<%=static_file%>js/list.js"
type=text/JavaScript></SCRIPT>

<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js"></SCRIPT>
<script language="javascript" src="../js/qscommon.js"></script>

<SCRIPT language=JavaScript>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  if(operationType=="Save")
  {

   if(document.forms[0].zldz.value == "")
        {
            alert("对不起，座落地址不能为空，请重新输入！");
            document.forms[0].zldz.focus();
            return false;
        }
   if(document.forms[0].cshtqdsj.value == "" || !checkDate(document.forms[0].cshtqdsj.value))
        {
            alert("对不起，出售合同（契约）签订时间不能为空或时间格式输入不正确，请重新输入！");
            document.forms[0].cshtqdsj.focus();
            return false;
        }
   if (!cmpDate(document.forms[0].cshtqdsj.value,document.forms[0].xtdqsj.value))
  	{
  	   alert("出售合同（契约）签订时间不能大于当前时间，请重新输入！");
  	   document.forms[0].cshtqdsj.focus();
  	   return false;
  	}        
        
	    if (!FN_QSCheckNumberDigit(document.forms[0].jzmj.value,2,"房屋建筑面积"))
	    {
	      document.forms[0].jzmj.focus();
	      return false;
	    }
	    else if (parseFloat(document.forms[0].jzmj.value) == 0)
	    {
          alert("房屋建筑面积不能为零！");
	      document.forms[0].jzmj.focus();
	      return false;
	    }

		if(document.forms[0].cjjg.value == "" || !isAllCharValid(document.forms[0].cjjg.value,"1234567890.,"))
        {
            alert("对不起，成交价格不能为空或不能为汉字，请重新输入！");
            document.forms[0].cjjg.focus();
            return false;
        }
		if(document.forms[0].bcdke.value == "" || !isAllCharValid(document.forms[0].bcdke.value,"1234567890.,"))
        {
            alert("对不起，本次抵扣额不能为空或不能为汉字，请重新输入！");
            document.forms[0].bcdke.focus();
            return false;
        }
        
        if(parseFloat(document.forms[0].jzmj.value) == 0)
        {
            alert("对不起，建筑面积不能为零！");
            document.forms[0].jzmj.focus();
            return false;
        }
                
        if(parseFloat(document.forms[0].cjjg.value) == 0)
        {
            alert("对不起，成交价格不能为零！");
            document.forms[0].cjjg.focus();
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
		if (!fnChangeCjjg())
		   return false;
		if (!fnChangeBcdke())
		   return false;		

    document.forms[0].operationType.value='Save';
  }
  else if(operationType=="quit")
  {
    document.forms[0].operationType.value='Return';
  }
  document.forms[0].submit();
}

//成交价格变动
function fnChangeCjjg()
{
   if (!FN_QSCheckNumberDigit(document.forms[0].cjjg.value,2,"成交价格"))   
   {
      document.forms[0].cjjg.focus();
      return false;
   }
   if (!parseFloat(document.forms[0].cjjg.value))
       return;

   fCjjg = parseFloat(document.forms[0].cjjg.value);
   fOldCjjg = parseFloat(document.forms[0].oldCjjg.value);

   if (!parseFloat(document.forms[0].bckdke.value))
   	   fBckdke = 0.00;
   else
       fBckdke = document.forms[0].bckdke.value;

   if (!parseFloat(document.forms[0].bcdke.value))
   	   fBcdke = 0.00;
   else
       fBcdke = document.forms[0].bcdke.value;

   if (!parseFloat(document.forms[0].sye.value))
   	   fSye = 0.00;
   else
       fSye = document.forms[0].sye.value;

   //本次可使用补偿额=补偿额-原先补偿额+可使用补偿额
   //剩余补偿额=可使用补偿额-本次使用额
   fBckdke = floatminus(fCjjg , fOldCjjg) + parseFloat(fBckdke);
   //剩余补偿额=可使用补偿额-本次使用额
   fSye  = floatminus(fBckdke , fBcdke);
   document.forms[0].bckdke.value = fBckdke;
   document.forms[0].sye.value = fSye;
   labelBckdke.innerText = document.forms[0].bckdke.value;
   labelSye.innerText = document.forms[0].sye.value;

   document.forms[0].oldCjjg.value = fCjjg;
   
   if (fSye < 0)
   {
      alert("剩余额不能小于零!");
      document.forms[0].cjjg.focus();
      return false;
   }
   return true;   
}

//本次使用补偿额与补偿剩余额联动，自动等于本次可使用补偿额-减去本次使用补偿额
function fnChangeBcdke()
{
   if (!FN_QSCheckNumberDigit(document.forms[0].bcdke.value,2,"本次抵扣额"))   
   {
      document.forms[0].bcdke.focus();
      return false;
   }
   
   document.forms[0].sye.value = floatminus(document.forms[0].bckdke.value , document.forms[0].bcdke.value);
   labelSye.innerText = document.forms[0].sye.value;
   
   fSye = parseFloat(document.forms[0].sye.value);
   if (fSye < 0)
   {
      alert("剩余额不能小于零!");
      document.forms[0].bcdke.focus();
      return false;
   }
   return true;     
}

function fn_change()
{
	if(document.forms[0].syeywbz1.checked==true)
	{
		document.forms[0].bcdke.readOnly=true
	}
	else
	{
		document.forms[0].bcdke.readOnly=false
	}
}

function fnOnLoad()
{
   document.forms[0].bckdke.value = floatplus(parseFloat(document.forms[0].sye.value) , parseFloat(document.forms[0].bcdke.value));
   labelBckdke.innerText = document.forms[0].bckdke.value;
   labelSye.innerText = document.forms[0].sye.value;
   if (document.forms[0].cjjg.value != "")
   {
       document.forms[0].oldCjjg.value = document.forms[0].cjjg.value;
   }
   else
   {
       document.forms[0].oldCjjg.value = '0.00';
   }
}


</SCRIPT>
<!----本页面的头文件和本页帮助的路径----->
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
 <jsp:param name="subtitle" value="契税申报&gt;已购公有住房/经济适用住房信息修改表"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>

         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>契税申报表-已购公有住房/经济适用住房上市情况</TD></TR>
    <TR>
      <TD class=1-td2 vAlign=top>
       <html:form action="/qssb/modifyGyzf.do">
       <html:hidden property="xtdqsj"/>
          <html:hidden property="operationType" value=""/>
        <INPUT name=actionType type=hidden value=Show>
        <INPUT name=ymbz type=hidden value=0>
        <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
          <TBODY>
                      <BR>
          <TR>
            <TD class=2-td2-t-left width="15%">
             <DIV align=right>申报表号&nbsp; </DIV>
          </TD>
          <TD colspan="5" class=2-td2-t-center width="15%">
            <DIV align=left><bean:write name="yggyzfForm" property="sbbh" /></DIV></TD>
          </TR>
         <TR>
             <TD class=2-td2-left width="8%" rowspan = "7">
            <DIV align=right>	已购公有住房</DIV>
            <DIV align=right>/经济适用住房</DIV>
             <DIV align=right>上市出售情况</DIV>
                             <br>
                             </TD>
            <TD   class=2-td2-left width="25%">
             <DIV  align=right>出售合同号码&nbsp;</DIV>
          </TD>
          <TD colspan="4" class=2-td2-center width="15%">
            <DIV align=left><bean:write name="yggyzfForm" property="yggyzfqszsh" />
              </DIV></TD>
            </TR>
					<TR>
           <TD class=2-td2-left width="15%";>
             <DIV align=right>房屋权属证书号&nbsp; </DIV>
          </TD>
           <TD colspan="4" class=2-td2-center width="25%" >
            <DIV align=left><html:text property="fwqszsh"  maxlength="15" size="30"/></DIV></TD>
            </TR>            
          <TR>
           <TD class=2-td2-left width="15%";>
             <DIV align=right>座落地址&nbsp; </DIV>
          </TD>
           <TD colspan="4" class=2-td2-center width="25%" >
            <DIV align=left><html:text property="zldz"  maxlength="100" size="50"/> <FONT color=red>*</FONT></DIV></TD>
            </TR>
          <TR>
         <TD  class=2-td2-left width="15%";>
           <div align=right>出售合同（契约）签订时间&nbsp;</DIV>
        </TD>
        <TD colspan="4" class=2-td2-center width="25%">
          <DIV align=left><html:text property="cshtqdsj"  /> (yyyymmdd)<FONT color=red>*</FONT></DIV></TD>
          </TR>
        <TR>
          <TD class=2-td2-left width="15%";>
            <DIV align=right> 建筑面积&nbsp; </DIV>
          </TD>
          <TD class=2-td2-left width="25%">
            <DIV align=left><html:text property="jzmj"  maxlength="14" /> m2 <FONT color=red>*</FONT></DIV></TD>
            <TD class=2-td2-left width="17%">
              <DIV align=right>成交价格&nbsp;</DIV></TD>
              <TD colspan="2"  class=2-td2-center width="32%">
                <DIV align=left><html:text property="cjjg"  maxlength="15" onchange="fnChangeCjjg()"/><FONT color=red>*</FONT> 元(人民币)</DIV></TD>
                <input type="hidden" name="oldCjjg">
                </TR>
          <TR>
            <TD class=2-td2-left width="15%";>
              <DIV align=right>本次可抵扣额&nbsp; </DIV>
            </TD>
            <TD   class=2-td2-left width="25%">
              <DIV align=left><label id="labelBckdke">0.00</label>
                元(人民币) </DIV></TD>
                <html:hidden property="bckdke" />
                <TD class=2-td2-left width="17%">
                  <DIV align=right>本次抵扣额&nbsp;</DIV></TD>
                  <TD colspan="2"  class=2-td2-center width="32%">
                    <DIV align=left><html:text property="bcdke" maxlength="15" onchange="javascript:fnChangeBcdke()"/>
                      元(人民币)<FONT color=red>*</FONT></DIV></TD>

                    </TR>
             <TR>
             <TD class=2-td2-left width="17%">
            <DIV align=right>剩余额&nbsp;</DIV></TD>
          <TD colspan="4"  class=2-td2-center width="32%">
            <DIV align=left><label id="labelSye">0.00</label>
            元(人民币)</DIV></TD>
            <html:hidden property="sye" />
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
	document.forms[0].bcdke.readOnly=true
}
else
{
	document.forms[0].bcdke.readOnly=false
}
</script>