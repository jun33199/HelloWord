<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>����ԭ�����ݲ�ѯ</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/function.js"></script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<%@ include file="./include/header.jsp"%>

<%
    com.ttsoft.bjtax.smsb.dzyj.web.DzyjcxActionForm form = (com.ttsoft.bjtax.smsb.dzyj.web.DzyjcxActionForm)request.getAttribute("dzyjcxActionForm");
    int pgLength = form.getPageLength();
    int pgSum = form.getPgSum();
    int pgNum = form.getPgNum();
    int start = pgLength*(pgNum-1);
    int Length = String.valueOf(pgSum).length();
%>

<html:form action="/webapp/smsb/dzyjcxAction.do" method="POST" >
<html:hidden property="actionType" />
<html:hidden property="pgNum" />
<html:hidden property="pageLength" />
<html:hidden property="pgSum" />
<html:hidden property="oneItemLsh" />


<table width="96%" align="center" cellspacing="0" class="table-99">
  <tr>
    <td class="1-td1">����ԭ�����ݲ�ѯ</td>
  </tr>
  <tr>
    <td class="1-td2">
      <br>
      <table width="98%" border="0" cellpadding="5" cellspacing="0"  >
        <tr class="black9">
          <td class="2-td2-t-center" nowrap width="10%">
              ���������(<FONT SIZE="" COLOR="#FF0000">*</FONT>)��
               &nbsp;
               <html:text property="jsjdm" size="8" maxlength="8" ></html:text>
          </td>
          <td class="2-td2-t-right" nowrap >
            <div align="left">
              &nbsp;��˰������:<bean:write name="dzyjcxActionForm" property="nsrmc"/>&nbsp;
            </div>
          </td>
          <td class="2-td2-t-right" nowrap width="12%">
            ��ѯʱ���(<FONT SIZE="" COLOR="#FF0000">*</FONT>)��
            <html:text property="qssj" size="8" maxlength="8" onchange="isDate(this,false);" ></html:text> ��
            <html:text property="jzsj" size="8" maxlength="8" onchange="isDate(this,false);" ></html:text>
          </td>
        </tr>
      </table>
      <table width="98%" border="0" cellpadding="5" cellspacing="0"  >
        <tr class="black9">
          <td class="2-td2-center" nowrap>
               ҵ�����ͣ�
             
             <bean:define id="ywlxList" name="dzyjcxActionForm" property="ywlxList" />
             <html:select name="dzyjcxActionForm" property="ywlx">
                <option value="0">ȫ��</option>
                <html:options collection="ywlxList" property="value" labelProperty="label"/>             
             </html:select>

          </td>
          <td class="2-td2-right" nowrap>
               �������ͣ�
             <select name="czlx">
               <option value="0"></option>
               <option value="1">����</option>
               <option value="2">�޸�</option>
               <option value="3">ɾ��</option>
             </select>
          </td>
          <!--
          <td class="2-td2-right" nowrap>
              ҵ�����ֵ��<input name="ywuid" type="text" size="30" maxlength="100" > 
          </td>
          -->
        </tr>
        <tr  class="black9"> 
          <td colspan=3>��(<FONT SIZE="" COLOR="#FF0000">*</FONT>)��Ϊ��������ۺ��걨���ҵ�����ֵ��Ϊ�걨��š�</td>
        </tr>
      </table>
      <br>
      <table width="94%" border="0" cellpadding="0" cellspacing="4">
        <tr  class="black9"> 
          <td width="20%">&nbsp;</td>
          <td width="20%" align="center"><input type="image" accesskey="c" tabIndex="-1" onClick="doQuery(); return false;"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="��ѯ" id="bc1" src="<%=static_contextpath%>images/chaxun1.jpg" width="79" height="22"></td>
          <td width="20%" align="center"><input type="image" accesskey="q" tabIndex="-1" onClick="doQuXiao(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/quxiao2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="ȡ��" id="tc1" src="<%=static_contextpath%>images/quxiao1.jpg" width="79" height="22"></td>
          <td width="20%" align="center"><input type="image" accesskey="t" tabIndex="-1" onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22"></td>
          <td width="20%">&nbsp;</td>
        </tr>
      </table>
      <br>
      <table width="94%" border="0" cellpadding="0" cellspacing="0" class="table-99">
        <tr> 
          <td align="center" class="1-td1" nowrap>������������ѯ�������������</td>
        </tr>
        <!--
        <tr class="black9">
          <td class="1-td2">
            <table cellpadding="3" width="100%">
              <tr class="black9">
                <td align="right">
                  �� <bean:write name="dzyjcxActionForm" property="totalRecord"/> ����¼ &nbsp;&nbsp;
                  ��(<input type="text" name="gotoPG" value="<%=pgNum%>" size="<%=Length%>" onchange="doGotoPG()">/<%=pgSum%>)ҳ
                  <%if(pgNum > 1 ) {%>
                    <img src="<%=static_contextpath%>images/diyiye1.jpg" alt="��һҳ" id="first" onMouseOver="MM_swapImage('first','','<%=static_contextpath%>images/diyiye2.jpg',1)" onMouseOut="MM_swapImage('first','','<%=static_contextpath%>images/diyiye1.jpg',1)" onclick="goToPage('1');return false;" style="cursor:hand">
                    <img src="<%=static_contextpath%>images/shangyiye1.jpg" alt="��һҳ" id="backward" onMouseOver="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye1.jpg',1)" onclick="goToPage('<%=pgNum-1%>');return false;" style="cursor:hand">
                  <%}%>
                  <%if(pgNum < pgSum) {%>
                    <img src="<%=static_contextpath%>images/xaiyiye1.jpg" alt="��һҳ" id="forward" onMouseOver="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye1.jpg',1)" onclick="goToPage('<%=pgNum+1%>');return false;" style="cursor:hand">
                    <img src="<%=static_contextpath%>images/zuimoye1.jpg" alt="��ĩҳ" id="last" onMouseOver="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye1.jpg',1)" onclick="goToPage('<%=pgSum%>');return false;" style="cursor:hand">
                  <%}%>
                </td>
              </tr>
            </table>
          </td>
        </tr>
        -->
        <tr class="black9"> 
          <td align="right" colspan=2 nowrap>
            <table width="100%" border="0" cellpadding="0" cellspacing="0"  align="center">
              <tr>
                 <td class="2-1-td1-left" width="10%">ԭ�����к�</td>
                 <td class="2-1-td1-left" >ҵ������</td>
                 <td class="2-1-td1-left" >ҵ�����</td>
                 <td class="2-1-td1-left" >ҵ�����ֵ</td>
                 <td class="2-1-td1-center" >ԭ���γ�ʱ��</td>
              </tr>
              <bean:define id="dataList" name="dzyjcxActionForm" property="dataList"/>
              <logic:iterate name="dataList" id="item" type="java.util.Map">
              <tr>
                 <td class="2-1-td1-left" width="10%"><a href="javascript:showOneItem(<%=item.get("lsh")%>)"><%=item.get("lsh")%></a></td>
                 <td class="2-1-td1-left" ><%=item.get("ywlxmc")%></td>
                 <td class="2-1-td1-left" ><%=item.get("ywczmc")%></td>
                 <td class="2-1-td1-left" ><%=item.get("ywuid")%></td>
                 <td class="2-1-td1-center"><%=item.get("jssj")%></td>
              </tr>
              </logic:iterate>
            </table>
          </td>
        </tr>
        <!--
        <tr class="black9">
          <td class="1-td2" align="right"  colspan=2 >
            <table cellpadding="3" align="right">
              <tr class="black9">
                <td align="right">��(<input type="text" name="gotoPG" value="<%=pgNum%>" size="<%=Length%>" onchange="doGotoPG()">/<%=pgSum%>)ҳ
                  <%if(pgNum > 1 ) {%>
                    <img src="<%=static_contextpath%>images/diyiye1.jpg" alt="��һҳ" id="first" onMouseOver="MM_swapImage('first','','<%=static_contextpath%>images/diyiye2.jpg',1)" onMouseOut="MM_swapImage('first','','<%=static_contextpath%>images/diyiye1.jpg',1)" onclick="goToPage('1');return false;" style="cursor:hand">
                    <img src="<%=static_contextpath%>images/shangyiye1.jpg" alt="��һҳ" id="backward" onMouseOver="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImage('backward','','<%=static_contextpath%>images/shangyiye1.jpg',1)" onclick="goToPage('<%=pgNum-1%>');return false;" style="cursor:hand">
                  <%}%>
                  <%if(pgNum < pgSum) {%>
                    <img src="<%=static_contextpath%>images/xaiyiye1.jpg" alt="��һҳ" id="forward" onMouseOver="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImage('forward','','<%=static_contextpath%>images/xaiyiye1.jpg',1)" onclick="goToPage('<%=pgNum+1%>');return false;" style="cursor:hand">
                    <img src="<%=static_contextpath%>images/zuimoye1.jpg" alt="��ĩҳ" id="last" onMouseOver="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImage('last','','<%=static_contextpath%>images/zuimoye1.jpg',1)" onclick="goToPage('<%=pgSum%>');return false;" style="cursor:hand">
                  <%}%>
                </td>
              </tr>
            </table>
          </td>
        </tr>
        -->
      </table>
    </td>
  </tr>
</table>
</html:form>

<%@ include file="./include/footer.jsp"%>
</body>
<script language="JavaScript">
function goToPage(pgNum) {
  document.all.pgNum.value = pgNum;
  doSubmitForm('Query');
}

function doGotoPG() {
  var gotoPG = document.all.gotoPG;
  var thisPG = document.all.pgNum;
  if(!fnIsIntNum(gotoPG.value)){
    gotoPG.value=thisPG.value;
    return;
  }
  if(parseInt(gotoPG.value)>parseInt(document.all.pgSum.value)){
    gotoPG.value=thisPG.value;
    return;
   }
  if(gotoPG.value!=thisPG.value) {
    thisPG.value = gotoPG.value;
    doSubmitForm('Query');
  }
//  return;
}
//��ѯ�ύ
function doQuery()
{
    if (document.forms[0].jsjdm.value.length != 8)
    {
        alert("������������Ϊ8λ��");
        return false;
    }
    if (! isDate(document.forms[0].qssj,false))
    {
        document.forms[0].qssj.focus = true;
        return false;
    }
    if (! isDate(document.forms[0].jzsj,false))
    {
        document.forms[0].jzsj.focus = true;
        return false;
    }

    if (document.forms[0].qssj.value.substring(0,6) != 
        document.forms[0].jzsj.value.substring(0,6))
    {
        alert("��ֹʱ�������ͬ��ͬ�£�");
        return false;
    }
    document.forms[0].actionType.value = "Query";
    document.forms[0].target="_self";
    document.forms[0].submit();
}

function showOneItem(lsh)
{
    document.forms[0].oneItemLsh.value = lsh;
    document.forms[0].actionType.value = "OneItem";
    document.forms[0].target="_blank";
    document.forms[0].submit();
}

function doQuXiao()
{
    document.forms[0].actionType.value = "Show";
    document.forms[0].target="_self";
    document.forms[0].submit();
}
</script>
</html:html>
