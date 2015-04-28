<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<HTML><HEAD><TITLE>契税减免申报</TITLE>
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
                <jsp:param name="subtitle" value="契税申报&gt;增加房产基本信息"/>
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
          <TD class=1-td1>录入契税减免申报表</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/qssb/addQsjmsb.do">
            <input type = "hidden" name = "operationType" value = "" >
            <TABLE background=./images/biaopqian-bg.jpg border=0 cellPadding=0 cellSpacing=0 width="100%">
             <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
               <TR>
                <TD class=2-td2-t-left width="15%">
                  <DIV align=right>关联申报表号&nbsp; </DIV>
                </TD>
                <TD class=2-td2-t-left width="15%">
                  <DIV align=left><html:text name = "qsjmsbForm" property = "sbbh" maxlength="19"/>&nbsp;
                  <input type="button" value="查询" onclick="doSubmitForm('Get')"></DIV>
                </TD>
                <TD  class=2-td2-t-left width="15%">
                   <DIV align=right>减免申报表号&nbsp; </DIV>
                </TD>
                   <DIV align=right>&nbsp; </DIV>
                <TD class=2-td2-t-center width="50%">
                    <DIV align=left><bean:write name = "qsjmsbForm" property = "jmsbbh"/>&nbsp;</DIV>
               </TD>
              </TR>
               <TR>
                <TD class=2-td2-left width="15%">
                  <DIV align=right>纳税人名称&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left width="15%">
                  <DIV align=left><bean:write name = "qsjmsbForm" property = "nsrmc"/>&nbsp;</DIV>
                </TD>
                <TD class=2-td2-left width="15%">
                   <DIV align=right>纳税人类型&nbsp; </DIV>
                </TD>
                   <DIV align=right>&nbsp; </DIV>
                <TD class=2-td2-center width="50%">
                <DIV align=left size=30><bean:write name = "qsjmsbForm" property = "nsrlx"/>&nbsp;</DIV>
               </TD>
              </TR>
              <TR>
               <TD class=2-td2-left  >
                 <DIV align=right>计算机代码(单位)&nbsp; </DIV>
               </TD>
               <TD class=2-td2-left width="15%">
                 <DIV align=left><bean:write name = "qsjmsbForm" property = "jsjdm"/>&nbsp;</DIV>
               </TD>
                <TD class=2-td2-left width="20%">
                  <DIV align=right>联系电话&nbsp;</DIV>
                </TD>
                <TD class=2-td2-center width="30%">
                   <DIV align=left><bean:write name = "qsjmsbForm" property = "lxdh"/>&nbsp;</DIV>
                </TD>
              </TR>
               <TR>
                <TD class=2-td2-left width="15%";>
                  <DIV align=right>纳税人个人合法性身份证件种类&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left width="25%">
                  <DIV align=left><bean:write name = "qsjmsbForm" property = "nsrzj"/>&nbsp;</DIV>
                 </TD>
                <TD class=2-td2-left width="19%">
                  <DIV align=right>个人合法身份证件号码&nbsp;</DIV>
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
                  <DIV align=right>	土地房屋</DIV>
                  <DIV align=right>权属转移</DIV>
                  <DIV align=right>情况</DIV>
              </TD>
          </TR>
          <TR>
              <TD class=2-td2-t-left  >
                 <DIV align=right>房地产项目名称&nbsp; </DIV>
              </TD>
              <TD class=2-td2-t-left width="22%">
                 <DIV align=left><bean:write name = "qsjmsbForm" property = "fdcxmmc"/>&nbsp;</DIV>
              </TD>
              <TD class=2-td2-t-left width="19%">
                 <DIV align=right>合同签定时间&nbsp;</DIV>
              </TD>
              <TD colspan="2"  class=2-td2-t-center width="33%">
                  <DIV align=left><bean:write name = "qsjmsbForm" property = "htqdsj"/>&nbsp;</DIV>
              </TD>
              </TR>
              <TR>
                <TD class=2-td2-left width="20%";>
                   <DIV align=right>土地、房屋座落地址&nbsp; </DIV>
                </TD>
                <TD colspan="5" class=2-td2-center width="19%">
                   <DIV align=left><bean:write name = "qsjmsbForm" property = "zldz"/>&nbsp;</DIV>
                </TD>
               </TR>
               <TR>
                   <TD  class=2-td2-left width="20%";>
                    <DIV align=right>土地、房屋权属承受形式&nbsp; </DIV>
                   </TD>
                   <TD colspan="4"  class=2-td2-center width="15%">
                     <DIV align=left><bean:write name = "qsjmsbForm" property = "csxs"/>&nbsp;</DIV>
                   </TD>
                </TR>
                <TR>
                  <TD class=2-td2-left width="20%";>
                   <DIV align=right>土地、房屋权属承受面积&nbsp; </DIV>
                  </TD>
                  <TD class=2-td2-left width="22%">
                   <DIV align=left><bean:write name = "qsjmsbForm" property = "csmj"/> m2&nbsp; </DIV>
                  </TD>
                  <TD class=2-td2-left width="17%">
                    <DIV align=right>房屋建筑面积&nbsp;</DIV>
                  </TD>
                  <TD colspan="2"  class=2-td2-center width="32%">
                    <DIV align=left><bean:write name = "qsjmsbForm" property = "fwjzmj"/> m2&nbsp;</DIV>
                  </TD>
               </TR>
               <TR>
                  <TD class=2-td2-left width="8%" rowspan = "2">
                    <DIV align=right>成交价格</DIV>
                    <DIV align=right>（评估价格）</DIV>
                  <TD  class=2-td2-left width="5%">
                    <DIV align=left><bean:write name = "qsjmsbForm" property = "cjjgrmb"/>元(人民币)</DIV>
                  </TD>
                  <TD class=2-td2-left width="17%">
                    <DIV align=right>税务机关核定价格&nbsp; </DIV></TD>
                  <TD  colspan="2"  class=2-td2-center width="32%">
                     <DIV align=left><bean:write name = "qsjmsbForm" property = "pgjgrmb"/>元(人民币)</DIV></TD>
                  </TR>
               <TR>
                  <TD class=2-td2-left width="15%";>
                    <DIV align=left><bean:write name = "qsjmsbForm" property = "cjjgwb"/>元(外币) </DIV>
                  </TD>
                  <TD class=2-td2-left width="25%">
                    <DIV align=left><bean:write name = "qsjmsbForm" property = "wbmc"/></DIV>
                  </TDa>
                  <DIV align=left><bean:write name = "qsjmsbForm" property = "hl"/>汇率 </DIV>
                  </TD>
                  <TD class=2-td2-center  width="17%">
                    <DIV align=left><bean:write name = "qsjmsbForm" property = "zhrmb"/>折合元(人民币) </DIV>
                  </TR>
          </TABLE><BR>
        <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
            <TR>
                <TD class=2-td2-t-left width="15%";>
                   <DIV align=right>申报减免理由&nbsp; </DIV>
                </TD>
                <TD colspan="5" class=2-td2-t-center width="84">
                  <div align="left">1、国家机关、事业单位、社会团体、军事单位承受土地、房屋用于办公、教学、医疗、科研和军事设施的
                      <html:multibox name = "qsjmsbForm" property = "sbjmzc" value = "1" />
                  </div>
                  <DIV align=left>
                      <textarea name = "bz" cols="45" rows="5" value="" readonly="true"></textarea>
                  </DIV>
                </TD>
            </TR>
            <TR>
                 <TD  colspan="6" class=2-td2-left width="15%";>
                   <DIV align=left>&nbsp&nbsp&nbsp&nbsp<B>税务机关真诚提示您</B><br>&nbsp;&nbsp;&nbsp;&nbsp;如实办理减免申报您有依法申请减税免税的权利。您所提供的土地、房屋权属转移变动情况及申请减免的附送资料应真实可靠。若申报不实或虚假申报，根据《中华人民共和国税收征收管理法》及实施细则的规定，税务机关可以处一万元以下的罚款；情节严重的，处一万元以上五万元以下的罚款。构成犯罪的，依法追究刑事责任。&nbsp; </DIV>
                </TD>

            </TR>
            <TR>
                <TD colspan="3" class=2-td2-left width="15%";>
                   <DIV align=left>纳税人签章：&nbsp; </DIV>
                   <DIV align=left>法人代表签章：&nbsp; </DIV>
                </TD>
                <TD colspan="3" class=2-td2-center width="84">
                 <DIV align=left>申报人签章：&nbsp; </DIV>
                 <DIV align=left>(授权委托人)&nbsp; </DIV>
                </TD>
               </TBODY></TABLE><BR>

            <DIV align=center>

        <IMG name="add"
          onClick= "doSubmitForm('add');"
          onMouseOver="MM_swapImage('tianjia','','<%=static_file%>images/tianjia2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tianjia1.jpg"
          width="79" height="22" id="tianjia" alt="保存" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;

        <IMG name="back"
          onclick="doSubmitForm('quit');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="退出" style="cursor:hand">
            </DIV><BR>

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY>
</HTML>
