<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<HTML><HEAD><TITLE>��ѯ����</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<script language="JavaScript" type="text/JavaScript" src="<%=static_file%>js/judge.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_file%>js/calculate.js"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  if(operationType=="Query" || operationType=="Export")
  {
      if(document.forms[0].sjtgz.value=="" &&
         document.forms[0].pch.value=="" &&
         document.forms[0].drsjBegin.value=="" &&
         document.forms[0].drsjEnd.value=="" &&
         document.forms[0].tjsjBegin.value=="" &&
         document.forms[0].tjsjEnd.value=="")
    {
        alert("��������дһ����ѯ������");
        return null;
    }
    if (document.forms[0].drsjBegin.value != "" && !checkDate(document.forms[0].drsjBegin.value))
    {
      alert("����ʱ�����ʽ����ȷ�����������룡");
      document.forms[0].drsjBegin.focus();
      return false;
    }
    if (document.forms[0].drsjEnd.value != "" && !checkDate(document.forms[0].drsjEnd.value))
    {
      alert("����ʱ��ֹ��ʽ����ȷ�����������룡");
      document.forms[0].drsjEnd.focus();
      return false;
    }
    if (document.forms[0].drsjBegin.value != "" &&
    document.forms[0].drsjEnd.value != "" &&
    !cmpDate(document.forms[0].drsjBegin.value,document.forms[0].drsjEnd.value))
  	{
  	   alert("����ʱ����ʱ�䲻�ܴ��ڵ���ʱ��ֹʱ�䣬���������룡");
  	   document.forms[0].drsjBegin.focus();
  	   return false;
  	}
    if (document.forms[0].tjsjBegin.value != "" && !checkDate(document.forms[0].tjsjBegin.value))
    {
      alert("�ύʱ�����ʽ����ȷ�����������룡");
      document.forms[0].tjsjBegin.focus();
      return false;
    }
    if (document.forms[0].tjsjEnd.value != "" && !checkDate(document.forms[0].tjsjEnd.value))
    {
      alert("�ύʱ��ֹ��ʽ����ȷ�����������룡");
      document.forms[0].tjsjEnd.focus();
      return false;
    }
    if (document.forms[0].tjsjBegin.value != "" &&
    document.forms[0].tjsjEnd.value != "" &&
    !cmpDate(document.forms[0].tjsjBegin.value,document.forms[0].tjsjEnd.value))
  	{
  	   alert("�ύʱ����ʱ�䲻�ܴ����ύʱ��ֹʱ�䣬���������룡");
  	   document.forms[0].tjsjBegin.focus();
  	   return false;
  	}
  }
  document.forms[0].submit();
}
</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
 <jsp:include page="/include/Header.jsp" flush="true">
                <jsp:param name="subtitle" value="������Ϣ&gt;������ѯ"/>
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
<table align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <td vAlign=top>
      <table align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <td class=1-td1>������Ϣ-ͳ��</td></TR>
        <TR>
          <td class=1-td2 vAlign=top>
            <html:form action="/plsl_swry/pltjQuery.do">
		      <input type="hidden" name="operationType" value="">
              <logic:equal name="queryPlcxForm2" property="status" scope="session" value="Result">
                <input type="hidden" name="changePage" value='<bean:write name="queryPlcxForm2" property="queryCache.currentPageNum"/>'>
                <input type="hidden" name="pageCount" value='<bean:write name="queryPlcxForm2" property="queryCache.pageCount"/>'>
              </logic:equal>
            <br>
             <table border="0" cellSpacing=0 class=table-60>
              <TBODY>
               <TR>
                <td class=2-td1-center width="50%">
                  �����ṩ������&nbsp;
                </td>
                <td class="2-td2-t-right" width="50%">
                  <DIV align=left><html:text property="sjtgz" maxlength="" tabindex="0"/></DIV>
                </td>
              </TR>
               <TR>
                <td class=2-td1-center width="50%">
                  ���κ�&nbsp;
                </td>
                <td class="2-td2-t-right" width="50%">
                  <DIV align=left><html:text property="pch" maxlength="" tabindex="0"/></DIV>
                </td>
              </TR>
              <TR>
                <td class=2-td1-center width="50%">
                  ����ʱ���&nbsp;
                </td>
                <td class="2-td2-t-right" width="50%">
                  <DIV align=left><html:text property="drsjBegin" maxlength="8" tabindex="0" size="8"/>-<html:text property="drsjEnd" maxlength="8" tabindex="0" size="8"/>yyyyMMdd</DIV>
                </td>
              </TR>
              <TR>
                <td class=2-td1-center width="50%">
                  �ύʱ���&nbsp;
                </td>
                <td class="2-td2-t-right" width="50%">
                  <DIV align=left><html:text property="tjsjBegin" maxlength="8" tabindex="0" size="8"/>-<html:text property="tjsjEnd" maxlength="8" tabindex="0" size="8"/>yyyyMMdd</DIV>
                </td>
              </TR>
     </table><BR>

      <DIV align=center>
        <IMG name="query"
          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
          onclick = "doSubmitForm('Query');"
          width="79" height="22" id="chaxun1" alt="��ѯ" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

  <!--
        <IMG name="toexcel"
          onMouseOver="MM_swapImage('toexcel1','','<\%=static_file%>images/b-bcdexcel2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<\%=static_file%>images/b-bcdexcel1.jpg"
          onclick = "doSubmitForm('Export');"
           height="22" id="toexcel1" alt="���浽Excel" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
-->
        <IMG name="back"
          onclick="doSubmitForm('Return');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="�˳�" style="cursor:hand">
      </DIV><BR>

          <logic:equal name="queryPlcxForm2" property="status" scope="session" value="Result">
          <table cellspacing="0" class="table-99">
            <tr>
            <td class="2-td2-t-left">��ѯ���</td>
           <!--��������Ϊ�գ���ʾû�м�¼-->
           <logic:equal name="queryPlcxForm2" property="queryCache.pageCount" scope="session" value="0">
            <td class="2-td2-t-center"> <div align="center">û����Ҫ�ļ�¼�������²�ѯ</div></td>
           </logic:equal>
           <logic:notEqual name="queryPlcxForm2" property="queryCache.pageCount" scope="session" value="0">
              <td class="2-td2-t-center"> <div align="right">
               ��<bean:write name="queryPlcxForm2" property="queryCache.countNumber"/>����¼
               ��<bean:write name="queryPlcxForm2" property="queryCache.currentPageNum"/>/
               <bean:write name="queryPlcxForm2" property="queryCache.pageCount"/>ҳ
                  <input type="image" name="beginpage" value="��һҳ" alt="��һҳ"
                  onclick = "javascript:return FN_QSChangePage('diyiye');"
                  onMouseOver="MM_swapImage('diyiye','','<%=static_file%>images/diyiye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg" id="diyiye">
                  <input type="image" name="endpage" value="���һҳ" alt="���һҳ"
                  onclick = "javascript:return FN_QSChangePage('zuihouyiye');"
                  onMouseOver="MM_swapImage('zuimoye','','<%=static_file%>images/zuimoye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg" id="zuimoye" >
                <!--���ֻ��һҳ�����水ť����ʾ-->
                <logic:equal name="queryPlcxForm2" property="queryCache.isOnePage" scope="session" value="false">
                  <!--����ǵ�һҳ��ǰҳ����ʾ-->
                  <logic:equal name="queryPlcxForm2" property="queryCache.hasPrev" scope="session" value="true">
                    <input type="image" name="frontpage" value="ǰҳ" alt="ǰҳ"
                    onclick = "javascript:return FN_QSChangePage('shangyiye');"
                    onMouseOver="MM_swapImage('shangyiye','','<%=static_file%>images/shangyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg" id="shangyiye">
                  </logic:equal>
                  <!--��������һҳ����ҳ����ʾ-->
                  <logic:equal name="queryPlcxForm2" property="queryCache.hasNext" scope="session" value="true">
                    <input type="image" name="backpage" value="��ҳ" alt="��ҳ"
                    onclick = "javascript:return FN_QSChangePage('xiayiye');"
                    onMouseOver="MM_swapImage('xiayiye','','<%=static_file%>images/xaiyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/xaiyiye1.jpg" id="xiayiye">
                  </logic:equal>
                </logic:equal>
                </div></td>
             </logic:notEqual>
            </tr>
          </table>
          <!--�����¼���Ϊ�գ�����ʾ-->
          <logic:notEqual name="queryPlcxForm2" property="queryCache.pageCount" scope="session" value="0">
          <br>
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="10%">���κ�</td>
              <td class="2-td1-left" width="10%">����ʱ��</td>
              <td class="2-td1-left" width="20%">�ύʱ��</td>
              <td class="2-td1-left" width="10%">�������</td>
              <td class="2-td1-left" width="10%">�ύ����</td>
              <td class="2-td1-left" width="10%">����ͨ������</td>
              <td class="2-td1-left" width="10%">���ͨ������</td>
              <td class="2-td1-left" width="10%">����ͨ������</td>
              <td class="2-td1-center" width="10%">����ͨ��Ӧ��˰��</td>
          </tr>
      <logic:iterate id="data" indexId="index" length="length" name="queryPlcxForm2" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.model.bo.PlcxBo2">
            <tr>
              <td class="2-td2-left"><div align="left">
              <a href="<%=response.encodeURL("/qsgl/plsl_swry/pltjDetail.do?operationType=Query&zt="+Constants.PC+"&pch=")%><bean:write name="data" property="pch"/>">
                    <bean:write name="data" property="pch"/>
                </a>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="drsj"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="tjsj"/>
              </td>
              <td class="2-td2-left">
              <logic:notEqual value="0" name="data" property="drbs">
                  <a href="<%=response.encodeURL("/qsgl/plsl_swry/pltjDetail.do?operationType=Query&zt="+Constants.DRZB_ZT_XINZENG+"&pch=")%><bean:write name="data" property="pch"/>">
                      <bean:write name="data" property="drbs"/></a>
              </logic:notEqual>
              <logic:equal value="0" name="data" property="drbs">
                  <bean:write name="data" property="drbs"/>
              </logic:equal>
              </td>
              <td class="2-td2-left">
              <logic:notEqual value="0" name="data" property="tjbs">
                  <a href="<%=response.encodeURL("/qsgl/plsl_swry/pltjDetail.do?operationType=Query&zt="+Constants.DRZB_ZT_RECIVE+"&pch=")%><bean:write name="data" property="pch"/>">
                      <bean:write name="data" property="tjbs"/></a>
              </logic:notEqual>
              <logic:equal value="0" name="data" property="tjbs">
                  <bean:write name="data" property="tjbs"/>
              </logic:equal>
              </td>
              <td class="2-td2-left">
              <logic:notEqual value="0" name="data" property="sltgbs">
                  <a href="<%=response.encodeURL("/qsgl/plsl_swry/pltjDetail.do?operationType=Query&zt="+Constants.SBZB_ZT_SHOULI+"&pch=")%><bean:write name="data" property="pch"/>">
                      <bean:write name="data" property="sltgbs"/></a>
              </logic:notEqual>
              <logic:equal value="0" name="data" property="sltgbs">
                  <bean:write name="data" property="sltgbs"/>
              </logic:equal>
              </td>
              <td class="2-td2-left">
              <logic:notEqual value="0" name="data" property="shtgbs">
                  <a href="<%=response.encodeURL("/qsgl/plsl_swry/pltjDetail.do?operationType=Query&zt="+Constants.SBZB_ZT_SHENHE+"&pch=")%><bean:write name="data" property="pch"/>">
                      <bean:write name="data" property="shtgbs"/></a>
              </logic:notEqual>
              <logic:equal value="0" name="data" property="shtgbs">
                  <bean:write name="data" property="shtgbs"/>
              </logic:equal>
              </td>
              <td class="2-td2-left">
              <logic:notEqual value="0" name="data" property="fstgbs">
                  <a href="<%=response.encodeURL("/qsgl/plsl_swry/pltjDetail.do?operationType=Query&zt="+Constants.SBZB_ZT_FUHE+"&pch=")%><bean:write name="data" property="pch"/>">
                      <bean:write name="data" property="fstgbs"/></a>
              </logic:notEqual>
              <logic:equal value="0" name="data" property="fstgbs">
                  <bean:write name="data" property="fstgbs"/>
              </logic:equal>
              </td>
              <td class="2-td2-center">
              &nbsp;<bean:write name="data" property="sumYnse"/>
              </td>
            </tr>
      </logic:iterate>
          </table>
          <br>
       <table cellspacing="0" class="table-99">
          <tr>
            <td align="right"><br>
                <input type="image" name="beginpage" value="��һҳ" alt="��һҳ"
                onclick = "javascript:return FN_QSChangePage('diyiye');"
                onMouseOver="MM_swapImage('diyiye1','','<%=static_file%>images/diyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg"
                width="79" height="22" id="diyiye1">
                <input type="image" name="endpage" value="���һҳ" alt="���һҳ"
                onclick = "javascript:return FN_QSChangePage('zuihouyiye');"
                onMouseOver="MM_swapImage('zuimoye1','','<%=static_file%>images/zuimoye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg"
                width="79" height="22" id="zuimoye1" >
                <!--���ֻ��һҳ�����水ť����ʾ-->
               <logic:equal name="queryPlcxForm2" property="queryCache.isOnePage" scope="session" value="false">
                 <!--����ǵ�һҳ��ǰҳ����ʾ-->
                  <logic:equal name="queryPlcxForm2" property="queryCache.hasPrev" scope="session" value="true">
                <input type="image" name="frontpage" value="ǰҳ" alt="ǰҳ"
                onclick = "javascript:return FN_QSChangePage('shangyiye');"
                onMouseOver="MM_swapImage('shangyiye1','','<%=static_file%>images/shangyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg"
                width="79" height="22" id="shangyiye1">
                 </logic:equal>
                 <!--��������һҳ����ҳ����ʾ-->
                  <logic:equal name="queryPlcxForm2" property="queryCache.hasNext" scope="session" value="true">
                   <input type="image" name="backpage" value="��ҳ" alt="��ҳ"
                   onclick = "javascript:return FN_QSChangePage('xiayiye');"
                   onMouseOver="MM_swapImage('xiayiye1','','<%=static_file%>images/xaiyiye2.jpg',1)"
                   onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/xaiyiye1.jpg"
                   width="79" height="22" id="xiayiye1">
                 </logic:equal>
                </logic:equal>
            </td>
          </tr>
        </table>
        </logic:notEqual>
      </div>
    </logic:equal>

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
