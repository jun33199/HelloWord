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
    <title>����ӡ��˰�������</title>
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
              <td class="1-td1">����ӡ��˰�������</td>
            </tr>
            <tr>
              <td class="1-td2">&nbsp;&nbsp;
                <table cellspacing=0 class="table-99">
                  <tr class="black9">
                    <td><div align="right">�������ڣ�&nbsp;&nbsp;</div></td>
                    <td> &nbsp;&nbsp;
                      <html:text property="hzqsrq" size="10"/> ��
                      <html:text property="hzjsrq" size="10"/>
                    </td>
                  </tr>
                  <tr class="black9">
                    <td><div align="right">ѡ����ܶ���&nbsp;&nbsp;</div></td>
                    <td>&nbsp;
                      <html:radio property="hzdx" value="0" onclick="change()" />���ջ���
                      <html:radio property="hzdx" value="1" onclick="change()" />ӡ��˰������Ա
                      <%--<html:text property="yhsxsry" value="sb_smsb" size="15" styleClass="txtInput" readonly="true"/>--%>
                      <html:select property="yhsxsry">
                        <html:options collection="PeopleList" property="value" labelProperty="label"/>
                      </html:select>
                    </td>
                    <td><div align="right">��λ�������Ԫ&nbsp;&nbsp;</div></td>
                  </tr>
                </table>
                <br>
                <table cellspacing=0 class="table-99">
                  <tr>
                    <td class="3-td1-left"  width="25%">���ܶ�������</td>
                    <td class="2-td2-t-center"><html:text property="hzdxmc" size="60" styleClass="inputnoborder" style="color:#3C5564" readonly="true"/></td>
                  </tr>
                  <tr>
                    <td class="2-td2-left">�걨���</td>
                    <td class="2-td2-center"><html:text property="sbbh" size="20" styleClass="inputnoborder" style="color:#3C5564" readonly="true"/></td>
                  </tr>
                  <tr>
                    <td class="2-td2-left">���ܽɿ����</td>
                    <td class="2-td2-center"><html:text property="jkpzh" size="20" styleClass="inputnoborder" style="color:#3C5564" readonly="true"/></td>
                  </tr>
                  <tr>
                    <td class="2-td2-left">����˰��</td>
                    <td class="2-td2-center"><html:text property="sjje" size="20" styleClass="inputnoborder" style="color:#3C5564" readonly="true"/></td>
                  </tr>
                </table>
                <br>
                <table width="150" border="0" cellpadding="0" cellspacing="0" class="table-99">
                  <tr>
                    <div align="center">
                      <input type="image" accesskey="z" src="<%=static_contextpath%>images/hzjks-z1.jpg" alt="����" id="huizong" onMouseOver="MM_swapImage('huizong','','<%=static_contextpath%>images/hzjks-z2.jpg',1)" onMouseOut="MM_swapImage('huizong','','<%=static_contextpath%>images/hzjks-z1.jpg',1)" onclick="document.forms[0].target='_self';doHzjks();return false;" style="cursor:hand">
                      &nbsp;&nbsp;&nbsp;&nbsp;
                      <input type="image" accesskey="g" src="<%=static_contextpath%>images/cxhz-g1.jpg" alt="��������" id="cxhz" onMouseOver="MM_swapImage('cxhz','','<%=static_contextpath%>images/cxhz-g2.jpg',1)" onMouseOut="MM_swapImage('cxhz','','<%=static_contextpath%>images/cxhz-g1.jpg',1)" onclick="document.forms[0].target='_self';doSubmitForm('Cxjks');return false;" style="cursor:hand">
                      &nbsp;&nbsp;&nbsp;&nbsp;
                      <input type="image" accesskey="p" img src="<%=static_contextpath%>images/dy-p1.jpg" alt="��ӡ" id="dayin" onMouseOver="MM_swapImage('dayin','','<%=static_contextpath%>images/dy-p2.jpg',1)" onMouseOut="MM_swapImage('dayin','','<%=static_contextpath%>images/dy-p1.jpg',1)" onclick="printFunc();return false;" style="cursor:hand">
                      &nbsp;&nbsp;&nbsp;&nbsp;
                      <input type="image" accesskey="c" onClick="document.forms[0].target='_self';tuichu();return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand">
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
    //������ʼ����Check
    strHzqsrq =document.all.hzqsrq.value;
    if(isFullDate(strHzqsrq,0,null,false)==false){
      alert("������ʼ���ڸ�ʽ����ȷ��");
      return false;
    }
    strnow = <ttsoft:write name="yhsgmhzForm" property="strNow" />; //getNowTime();
    if( compare(strnow,strHzqsrq)<0 ){
      alert("������ʼ����ӦС�ڵ��ڵ�ǰ���ڡ�");
      return false;
    }

    //���ܽ�������Check
    strHzjsrq =document.all.hzjsrq.value;
    if(isFullDate(strHzjsrq,0,null,false)==false){
      alert("���ܽ�ֹ���ڸ�ʽ����ȷ��");
      return false;
    }
    if( compare(strnow,strHzjsrq)<0 ){
      alert("���ܽ�ֹ����ӦС�ڵ��ڵ�ǰ���ڡ�");
      return false;
    }

    //������ʼ���ںͻ��ܽ������ڹ�ϵCheck
    if( compare(strHzjsrq,strHzqsrq) < 0 ){
      alert("������ʼ����ӦС�ڻ��ܽ������ڡ�");
      return false;
    }
    if(window.confirm("��������δ���ܵ�ӡ��˰�������ݣ���ȷ��")){
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
  //�õ���ǰ����
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
      alert("���Ȼ��ܣ�Ȼ���ٽ��д�ӡ��");
      return false;
    }
    document.forms[0].target="blank";
    doSubmitForm('Print');
  }
</script>
</html:html>
