<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html>
  <%
  response.setHeader("pragma", "no-cache");
  response.setHeader("Cache-control", "no-cache, no-store");
  response.setHeader("Expires", "0");
  %>

  <head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <title>汇总印花税购买情况</title>
    <script language=JavaScript src="../js/treatImage.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="../js/smsb_common.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="../js/function.js"></script>
    <style type="text/css">
      <!--
      @import url(../css/text.css);
      -->
    </style>
  </head>
  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" align="center" onload = "fnOnLoad()" >
    <%@ include file="./include/header.jsp"%>
    <%
    java.util.List peopleList = com.ttsoft.bjtax.smsb.yhsgl.YhsglHelper.getPeopleSelect(userData.getSsdwdm());
    pageContext.setAttribute("PeopleList",peopleList);
    %>
    <html:form action="/webapp/smsb/yhsgmhzAction.do" method="POST">
      <input type=hidden value="Show" name="actionType">
      <html:hidden property="dsjsjdm" />
      <html:hidden property="lrr" />
      <html:hidden property="zsjgdm" />
      <html:hidden property="strNow" />
      <table width="100%" height="60%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td>
          <table align="center" cellspacing="0" class="table-99">
            <tr>
              <td class="1-td1">汇总印花税购买情况</td>
            </tr>
            <tr>
              <td class="1-td2">&nbsp;&nbsp;
                <table cellspacing=0 class="table-99">
                  <tr class="black9">
                    <td><div align="right">汇总日期：&nbsp;&nbsp;</div></td>
                    <td> &nbsp;&nbsp;
                      <html:text property="hzqsrq" size="10"/> 至
                      <html:text property="hzjsrq" size="10"/>
                    </td>
                  </tr>
                  <tr class="black9">
                    <td><div align="right">选择汇总对象：&nbsp;&nbsp;</div></td>
                    <td>&nbsp;
                      <html:radio property="hzdx" value="0" onclick="change()" />征收机关
                      <html:radio property="hzdx" value="1" onclick="change()" />印花税销售人员
                      <%--<html:text property="yhsxsry" value="sb_smsb" size="15" styleClass="txtInput" readonly="true"/>--%>
                      <html:select property="yhsxsry">
                        <html:options collection="PeopleList" property="value" labelProperty="label"/>
                      </html:select>
                    </td>
                    <td><div align="right">金额单位：人民币元&nbsp;&nbsp;</div></td>
                  </tr>
                </table>
                <br>
                <table cellspacing=0 class="table-99">
                  <tr>
                    <td class="3-td1-left"  width="25%">汇总对象名称</td>
                    <td class="2-td2-t-center"><html:text property="hzdxmc" size="60" styleClass="inputnoborder" style="color:#3C5564" readonly="true"/></td>
                  </tr>
                  <tr>
                    <td class="2-td2-left">申报编号</td>
                    <td class="2-td2-center"><html:text property="sbbh" size="20" styleClass="inputnoborder" style="color:#3C5564" readonly="true"/></td>
                  </tr>
                  <tr>
                    <td class="2-td2-left">汇总缴款书号</td>
                    <td class="2-td2-center"><html:text property="jkpzh" size="20" styleClass="inputnoborder" style="color:#3C5564" readonly="true"/></td>
                  </tr>
                  <tr>
                    <td class="2-td2-left">汇总税款</td>
                    <td class="2-td2-center"><html:text property="sjje" size="20" styleClass="inputnoborder" style="color:#3C5564" readonly="true"/></td>
                  </tr>
                </table>
                <br>
                <table width="150" border="0" cellpadding="0" cellspacing="0" class="table-99">
                  <tr>
                    <div align="center">
                      <input type="image" accesskey="z" src="<%=static_contextpath%>images/hzjks-z1.jpg" alt="汇总" id="huizong" onMouseOver="MM_swapImage('huizong','','<%=static_contextpath%>images/hzjks-z2.jpg',1)" onMouseOut="MM_swapImage('huizong','','<%=static_contextpath%>images/hzjks-z1.jpg',1)" onclick="document.forms[0].target='_self';doHzjks();return false;" style="cursor:hand">
                      &nbsp;&nbsp;&nbsp;&nbsp;
                      <input type="image" accesskey="g" src="<%=static_contextpath%>images/cxhz-g1.jpg" alt="撤消汇总" id="cxhz" onMouseOver="MM_swapImage('cxhz','','<%=static_contextpath%>images/cxhz-g2.jpg',1)" onMouseOut="MM_swapImage('cxhz','','<%=static_contextpath%>images/cxhz-g1.jpg',1)" onclick="document.forms[0].target='_self';doSubmitForm('Cxjks');return false;" style="cursor:hand">
                      &nbsp;&nbsp;&nbsp;&nbsp;
                      <input type="image" accesskey="p" img src="<%=static_contextpath%>images/dy-p1.jpg" alt="打印" id="dayin" onMouseOver="MM_swapImage('dayin','','<%=static_contextpath%>images/dy-p2.jpg',1)" onMouseOut="MM_swapImage('dayin','','<%=static_contextpath%>images/dy-p1.jpg',1)" onclick="printFunc();return false;" style="cursor:hand">
                      &nbsp;&nbsp;&nbsp;&nbsp;
                      <input type="image" accesskey="c" onClick="document.forms[0].target='_self';tuichu();return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand">
                    </div>
                  </tr>
                </table>
                <br>
              </td>
            </tr>
          </table>
          <%@ include file="./include/footer.jsp"%>
        </td>
      </tr>
    </table>
  </html:form>
  <br>
  </body>
  <script language="JavaScript">
  function change()
  {
    if(document.all.hzdx[0].checked==true){
      document.all.yhsxsry.disabled=true;
      document.all.hzdxmc.value ="";
      document.all.sbbh.value ="";
      document.all.jkpzh.value ="";
      document.all.sjje.value ="";
    }
    else {
      document.all.yhsxsry.disabled=false;
      document.all.hzdxmc.value ="";
      document.all.sbbh.value ="";
      document.all.jkpzh.value ="";
      document.all.sjje.value ="";
    }
  }
  //init
  function init(){
    if(document.all.hzdx[0].checked==true){
      document.all.yhsxsry.disabled=true;
    }
    else {
      document.all.yhsxsry.disabled=false;
    }
  }

  init();

  function doHzjks() {
    //汇总起始日期Check
    strHzqsrq =document.all.hzqsrq.value;
    if(isFullDate(strHzqsrq,0,null,false)==false){
      alert("汇总起始日期格式不正确。");
      return false;
    }
    strnow = <ttsoft:write name="yhsgmhzForm" property="strNow" />; //getNowTime();
    if( compare(strnow,strHzqsrq)<0 ){
      alert("汇总起始日期应小于等于当前日期。");
      return false;
    }

    //汇总截至日期Check
    strHzjsrq =document.all.hzjsrq.value;
    if(isFullDate(strHzjsrq,0,null,false)==false){
      alert("汇总截止日期格式不正确。");
      return false;
    }
    if( compare(strnow,strHzjsrq)<0 ){
      alert("汇总截止日期应小于等于当前日期。");
      return false;
    }

    //汇总起始日期和汇总截至日期关系Check
    if( compare(strHzjsrq,strHzqsrq) < 0 ){
      alert("汇总起始日期应小于汇总截至日期。");
      return false;
    }
    if(window.confirm("即将汇总未汇总的印花税销售数据，请确认")){
      doSubmitForm('Hzjks');
    }
  }

  function compare(value1,value2){
    if(parseInt(value1)<parseInt(value2)){
      return -1;
    }else if(parseInt(value1)==parseInt(value2)){
      return 0;
    }else{
      return 1;
    }
  }
  //得到当前日期
  //function getNowTime()
  //{
    //  now = new Date();
    //  intMonth =parseInt(now.getMonth())+1;
    //  strMonth = "";
    //  strDate = "";
    //  if(intMonth<10){
      //   strMonth = "0"+ intMonth;
      //  }else{
        //   strMonth = intMonth;
        //  }
        //  if(parseInt(now.getDate())<10){
          //   strDate = "0"+ now.getDate();
          //  }else{
            //   strDate = now.getDate();
            //  }
            //  strnow = "" + now.getYear() + strMonth + strDate;
            //  return strnow;
            //}
  function fnOnLoad()
  {
    document.forms[0].hzqsrq.focus();
  }
  function printFunc(){
    var strSjje = document.all.sjje.value + "";
    strSjje = trim(strSjje);
    if(strSjje.length == 0 ){
      alert("请先汇总，然后再进行打印！");
      return false;
    }
    document.forms[0].target="blank";
    doSubmitForm('Print');
  }
</script>
</html:html>
