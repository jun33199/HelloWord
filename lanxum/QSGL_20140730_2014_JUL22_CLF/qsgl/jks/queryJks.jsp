<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb"%>


<HTML><HEAD><TITLE>��ѯ�ɿ���</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>


<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType)
{
  document.all.operationType.value=operationType;
  if(operationType=="Query" )
  {
    if(document.forms[0].jkpzh.value=="" && document.forms[0].sbqsrq.value=="" && document.forms[0].sbjzrq.value=="")
    {
      alert("����������һ���ѯ������");
     	document.forms[0].jkpzh.focus();
    	return false;
    }
  }
  document.forms[0].submit();
}
</SCRIPT>


<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="�ɿ������&gt;��ѯ�ɿ���"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<html:form action="/jks/queryJks.do">
<html:hidden property="operationType"/>
<logic:equal name="jksForm" property="status" scope="session" value="Result">
    <input type="hidden" name="changePage" value='<bean:write name="jksForm" property="queryCache.currentPageNum"/>'>
    <input type="hidden" name="pageCount" value='<bean:write name="jksForm" property="queryCache.pageCount"/>'>
</logic:equal>


<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <table align="center" cellspacing="0" class="table-99">
	  <TBODY>
        <tr>
          <td class="1-td1">��ѯ�ɿ���</a></td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
            <table cellspacing="0" class="table-60">
              <tr>
                <td class="2-td2-t-left">��ѡ�����ɽɿ���ķ�ʽ</td>
                <td  class="2-td2-t-center"><div align="left">
                    <html:select property = "scfs" >
                        <html:option value = "<%=Constants.JKS_SJLY_HZ%>" >��������</html:option>
                        <html:option value = "<%=Constants.JKS_SJLY_FHZ%>" >�ǻ�������</html:option>
                        <html:option value = "<%=Constants.JKS_SJLY_XH%>" >��¼�޸�����</html:option>
                    </html:select></div>
                </td>
              </tr>
              <tr>
                <td class="2-td2-left">�ɿ�ƾ֤��</td>
                <td  class="2-td2-center"><div align="left">
                    <html:text name = "jksForm" property="jkpzh" /></div>
                </td>
               </tr>
               <tr>
                <td class="2-td2-left">�걨��ʼ����</td>
                <td  class="2-td2-center"><div align="left">
                   <html:text name = "jksForm" property="sbqsrq" />���磺20050101</div>
                </td>
              </tr>
              <tr>
                <td class="2-td2-left">�걨��ֹ����</td>
                <td  class="2-td2-center"><div align="left">
                   <html:text name = "jksForm" property="sbjzrq" />���磺20050101</div>
                </td>
              </tr>
            </table> <br>

      <DIV align=center>
      <IMG name="query"
          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
          onclick = "doSubmitForm('Query');"
          width="79" height="22" id="chaxun1" alt="��ѯ" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;

        <IMG name="back"
          onclick="doSubmitForm('Return');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="�˳�" style="cursor:hand">
      </DIV><BR>

<logic:equal name="jksForm" property="status" scope="session" value="Result">
	<table cellspacing="0" class="table-99">
 	  <tr>
		<td><hr class="hr1"></td>
		<td width="140" class="b-black10">
			<div align="center">�ɿ����ѯ���</div></td>
		<td><hr class="hr1"></td>
        <!--��������Ϊ�գ���ʾû�м�¼-->
           <logic:equal name="jksForm" property="queryCache.pageCount" scope="session" value="0">
            <td class="2-td2-t-center"> <div align="center">û����Ҫ�ļ�¼�������²�ѯ</div></td>
           </logic:equal>
           <logic:notEqual name="jksForm" property="queryCache.pageCount" scope="session" value="0">
              <td class="2-td2-t-center"> <div align="right">
               ��<bean:write name="jksForm" property="queryCache.countNumber"/>����¼
               ��<bean:write name="jksForm" property="queryCache.currentPageNum"/>/
               <bean:write name="jksForm" property="queryCache.pageCount"/>ҳ
                  <input type="image" name="beginpage" value="��һҳ" alt="��һҳ"
                  onclick = "javascript:return FN_QSChangePage('diyiye');"
                  onMouseOver="MM_swapImage('diyiye','','<%=static_file%>images/diyiye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg" id="diyiye">
                  <input type="image" name="endpage" value="���һҳ" alt="���һҳ"
                  onclick = "javascript:return FN_QSChangePage('zuihouyiye');"
                  onMouseOver="MM_swapImage('zuimoye','','<%=static_file%>images/zuimoye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg" id="zuimoye" >
                <!--���ֻ��һҳ�����水ť����ʾ-->
                <logic:equal name="jksForm" property="queryCache.isOnePage" scope="session" value="false">
                  <!--����ǵ�һҳ��ǰҳ����ʾ-->
                  <logic:equal name="jksForm" property="queryCache.hasPrev" scope="session" value="true">
                    <input type="image" name="frontpage" value="ǰҳ" alt="ǰҳ"
                    onclick = "javascript:return FN_QSChangePage('shangyiye');"
                    onMouseOver="MM_swapImage('shangyiye','','<%=static_file%>images/shangyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg" id="shangyiye">
                  </logic:equal>
                  <!--��������һҳ����ҳ����ʾ-->
                  <logic:equal name="jksForm" property="queryCache.hasNext" scope="session" value="true">
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
        </logic:equal>
          <!--�����¼���Ϊ�գ�����ʾ-->
          <logic:notEqual name="jksForm" property="queryCache.pageCount" scope="session" value="0">
          <br>

  <TABLE border="0" cellSpacing=0 class=table-99>
      <TBODY>
       <TR>
        <TD class="2-td1-left" width = "16%" >�ɿ����</TD>
        <TD class="2-td1-left" width = "10%" >˰������</TD>
        <TD class="2-td1-left" width = "15%" >ʵ�ɽ��</TD>
        <TD class="2-td1-left" width = "10%" >���ֽɿ�������</TD>
        <TD class="2-td1-left" width = "16%" >�걨���</TD>
		<TD class="2-td1-center" width = "33%" >��ע</TD>
      </TR>

  <logic:iterate id="data" indexId="index" length="length" name="jksForm" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.model.bo.JksBo">

    <bean:define id="data1" name="data" property="sbjkzb" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb"/>
        <TR>
          <td class="2-td2-left">
              <!--<a href="<%=response.encodeURL("/qsgl/jks/viewJks.do?operationType=ShowJks&viewIndex=") + index.intValue()%>">-->
                  <bean:write name="data1" property="jkpzh" />&nbsp;
              <!--</a>-->
          </td>
          <td class="2-td2-left"><%=DataConvert.TimeStamp2String(data1.getSbrq())%>&nbsp;</td>
          <td class="2-td2-left">��<%=DataConvert.BigDecimal2String(data1.getSjje(),2)%>&nbsp;</td>
          <td class="2-td2-left">
              <%if(Constants.JKS_SJLY_HZ.equals(data1.getSjly()))
              {
              %>
              <a href="<%=response.encodeURL("/qsgl/jks/viewWszList.do?operationType=QueryWsz&type=1&yuan=returnQuery&viewIndex=") + index.intValue()%>">
              <bean:write name="data1" property="kssl" />
              </a>
             <%}else
             {
             %>
             --
            <%} %>
          </td>
          <td class="2-td2-left">
              <%if(data1.getSbbh() == null || data1.getSbbh().equals("") )
              {
              %>
              --

             <%}else
             {
             %>
             <bean:write name="data1" property="sbbh" />&nbsp;
            <%} %>
          </td>
		  <td class="2-td2-center"><bean:write name="data1" property="bz" />&nbsp;</td>
        </TR>

  </logic:iterate>
   </table><br>

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
               <logic:equal name="jksForm" property="queryCache.isOnePage" scope="session" value="false">
                 <!--����ǵ�һҳ��ǰҳ����ʾ-->
                  <logic:equal name="jksForm" property="queryCache.hasPrev" scope="session" value="true">
                <input type="image" name="frontpage" value="ǰҳ" alt="ǰҳ"
                onclick = "javascript:return FN_QSChangePage('shangyiye');"
                onMouseOver="MM_swapImage('shangyiye1','','<%=static_file%>images/shangyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg"
                width="79" height="22" id="shangyiye1">
                 </logic:equal>
                 <!--��������һҳ����ҳ����ʾ-->
                  <logic:equal name="jksForm" property="queryCache.hasNext" scope="session" value="true">
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

</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
