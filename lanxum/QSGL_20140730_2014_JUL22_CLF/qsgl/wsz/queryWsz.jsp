<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz"%>


<HTML><HEAD><TITLE>��ѯ���ֽɿ����</TITLE>
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
  if(operationType=="query" )
  {
    document.forms[0].operationType.value='Query';
    if(document.forms[0].zb.value=="" && document.forms[0].wszqshm.value==""
       && document.forms[0].wszjzhm.value=="" && document.forms[0].wszjzhm.value==""
       && document.forms[0].tfqsrq.value=="" && document.forms[0].tfjzrq.value=="" 
       && document.forms[0].nsrmc.value=="")
    {
        alert("����������һ���ѯ������");
     	document.forms[0].zb.focus();
    	return false;
    }
  }
  if(operationType=="quit")
  {
    document.forms[0].operationType.value='Return';
  }

  document.forms[0].submit();
}
</SCRIPT>


<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="���ֽɿ������&gt;��ѯ���ֽɿ����"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<html:form action="/wsz/queryWsz.do">
<html:hidden property="operationType"/>
            <logic:equal name="queryWszForm" property="status" scope="session" value="Result">
                <input type="hidden" name="changePage" value='<bean:write name="queryWszForm" property="queryCache.currentPageNum"/>'>
                <input type="hidden" name="pageCount" value='<bean:write name="queryWszForm" property="queryCache.pageCount"/>'>
            </logic:equal>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <table align="center" cellspacing="0" class="table-99">
	  <TBODY>
        <tr>
          <td class="1-td1">��ѯ���ֽɿ���</a></td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
            <table cellspacing="0" class="table-99">
              <tr>
                <td class="2-td2-t-left">�ֱ�</td>
                <td class="2-td2-t-left">
                    <html:text property="zb" />
                </td>
                <td class="2-td2-t-left">��˰������</td>
                <td class="2-td2-t-center"><html:text property="nsrmc" /></td>
              </tr>
              <tr>
                <td class="2-td2-left">���ֽɿ�����ʼ����</td>
                <td  class="2-td2-left">
                    <html:text property="wszqshm" />
                </td>
                <td class="2-td2-left">���ֽɿ����ֹ����</td>
                <td  class="2-td2-center">
                   <html:text property="wszjzhm" />
                </td>
              </tr>
              <tr>
                <td class="2-td2-left">���ʼ����</td>
                <td  class="2-td2-left">
                    <html:text property="tfqsrq" />
                </td>
                <td class="2-td2-left">���ֹ����</td>
                <td  class="2-td2-center">
                   <html:text property="tfjzrq" />
                </td>
              </tr>

 
            </table>            <br>

      <DIV align=center>
      <IMG name="query"
          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
          onclick = "doSubmitForm('query');"
          width="79" height="22" id="chaxun1" alt="��ѯ" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;

        <IMG name="back"
          onclick="doSubmitForm('quit');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="�˳�" style="cursor:hand">
      </DIV><BR>





 <logic:equal name="queryWszForm" property="status" scope="session" value="Result">
	<table cellspacing="0" class="table-99">
 	  <tr>
		<td><hr class="hr1"></td>
		<td width="140" class="b-black10">
			<div align="center">���ֽɿ����ѯ���</div></td>
		<td><hr class="hr1"></td>
        <!--��������Ϊ�գ���ʾû�м�¼-->
           <logic:equal name="queryWszForm" property="queryCache.pageCount" scope="session" value="0">
            <td class="2-td2-t-center"> <div align="center">û����Ҫ�ļ�¼�������²�ѯ</div></td>
           </logic:equal>
           <logic:notEqual name="queryWszForm" property="queryCache.pageCount" scope="session" value="0">
              <td class="2-td2-t-center"> <div align="right">
               ��<bean:write name="queryWszForm" property="queryCache.countNumber"/>����¼
               ��<bean:write name="queryWszForm" property="queryCache.currentPageNum"/>/
               <bean:write name="queryWszForm" property="queryCache.pageCount"/>ҳ
                  <input type="image" name="beginpage" value="��һҳ" alt="��һҳ"
                  onclick = "javascript:return FN_QSChangePage('diyiye');"
                  onMouseOver="MM_swapImage('diyiye','','<%=static_file%>images/diyiye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg" id="diyiye">
                  <input type="image" name="endpage" value="���һҳ" alt="���һҳ"
                  onclick = "javascript:return FN_QSChangePage('zuihouyiye');"
                  onMouseOver="MM_swapImage('zuimoye','','<%=static_file%>images/zuimoye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg" id="zuimoye" >
                <!--���ֻ��һҳ�����水ť����ʾ-->
                <logic:equal name="queryWszForm" property="queryCache.isOnePage" scope="session" value="false">
                  <!--����ǵ�һҳ��ǰҳ����ʾ-->
                  <logic:equal name="queryWszForm" property="queryCache.hasPrev" scope="session" value="true">
                    <input type="image" name="frontpage" value="ǰҳ" alt="ǰҳ"
                    onclick = "javascript:return FN_QSChangePage('shangyiye');"
                    onMouseOver="MM_swapImage('shangyiye','','<%=static_file%>images/shangyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg" id="shangyiye">
                  </logic:equal>
                  <!--��������һҳ����ҳ����ʾ-->
                  <logic:equal name="queryWszForm" property="queryCache.hasNext" scope="session" value="true">
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
          <logic:notEqual name="queryWszForm" property="queryCache.pageCount" scope="session" value="0">
          <br>


     <TABLE border="0" cellSpacing=0 class=table-99>
      <TBODY>
       <TR>
        <TD class="2-td1-left" width = "8%" >���ֽɿ����</TD>
        <TD class="2-td1-left" width = "8%" >�ֱ�</TD>
        <TD class="2-td1-left" width = "12%" >�걨���</TD>
        <TD class="2-td1-left" width = "8%" >�Ƿ����</TD>
        <TD class="2-td1-left" width = "7%" >��ӡ״̬</TD>
        <TD class="2-td1-left" width = "10%" >�ɿ�ƾ֤��</TD>
        <TD class="2-td1-left" width = "10%" >ʵ�ɽ��</TD>
        <TD class="2-td1-left" width = "12%" >�����</TD>
        <TD class="2-td1-left" width = "8%" >��˰�˴���</TD>
        <TD class="2-td1-left" width = "10%" >���յ�����</TD>
        <TD class="2-td1-center" width = "8%" >������Ա</TD>
      </TR>
      <logic:iterate id="data" indexId="index" length="length" name="queryWszForm" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.model.bo.QueryWszBo">

      <bean:define id="data1" name="data" property="qswszzVo" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz"/>

            <tr>
               <!-- ��˰֤����-->
              <td class="2-td2-left">
              <a href="<%=response.encodeURL("/qsgl/wsz/viewWsz.do?operationType=Show&viewIndex=") + index.intValue()%>">
                  <bean:write name="data1" property="wszh"/>
              </a>
              </td>
              <!--  �ֱ�  -->
              <td class="2-td2-left">
                  <bean:write name="data1" property="ndzb"/>
              </td>
              <!-- �걨���  -->
              <td class="2-td2-left" style="word-break:break-all">
                 &nbsp; <bean:write name="data1" property="sbbh"/>
              </td>
              <!--  �Ƿ���� -->
              <td class="2-td2-left">
              <%
              		if(data1.getSbhzdh()!=null&&!data1.getSbhzdh().equals(""))
              {%>
                  �ѻ���
               <%}
               else
               {%>
                  <font color="red">δ����</font>
               <% } %>

              </td>
              <!-- ��ӡ״̬  -->
              <td class="2-td2-left">
              <%
              if(Integer.parseInt(Constants.WSZ_CLBJDM_YWS) <= (Integer.parseInt(data1.getClbjdm())))
              {%>
                  �Ѵ�ӡ
              <%}
               else
               {
                   String url = "/qsgl/wsz/updateWszStatus.do?operationType=UpdateState&wszh=" + ((Qswszz)data1).getWszh()  + "&zb=" + ((Qswszz)data1).getNdzb() + "&pzzldm=" + ((Qswszz)data1).getPzzldm();
                   %>
                  <font color="red">δ��ӡ</color><br>
                  <a href="<%=response.encodeURL(url)%>">��Ϊ�Ѵ�ӡ</a>
               <% } %>
               <!--  �ɿ�ƾ֤�� -->
              <td class="2-td2-left">
              <%
              		if(data1.getJkpzh()!=null && !data1.getJkpzh().equals(""))
              {%>
                  <bean:write name="data1" property="jkpzh"/>
               <%}
               else
               {%>
                  --
               <% } %>

              </td>
              </td>
                <!-- ʵ��˰��  -->
              <td class="2-td2-left">
              &nbsp;<%=DataConvert.BigDecimal2String(data1.getHjsjje())%>
              </td>
                <!--�����   -->
              <td class="2-td2-left" style="word-break:break-all">
                 &nbsp; <%=DataConvert.TimeStamp2String(data1.getCjrq())%>
              </td>
               <!--  ��˰�˼�������� -->
              <td class="2-td2-left">
                  <bean:write name="data1" property="nsrjsjdm"/>&nbsp;
              </td>
                <!-- ���յ�����  -->
              <td class="2-td2-left">
                  <bean:write name="data1" property="zsdmc"/>&nbsp;
              </td>
                <!-- ������Ա  -->
              <td class="2-td2-center" style="word-break:break-all">
                 &nbsp; <bean:write name="data1" property="lrr"/>
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
               <logic:equal name="queryWszForm" property="queryCache.isOnePage" scope="session" value="false">
                 <!--����ǵ�һҳ��ǰҳ����ʾ-->
                  <logic:equal name="queryWszForm" property="queryCache.hasPrev" scope="session" value="true">
                <input type="image" name="frontpage" value="ǰҳ" alt="ǰҳ"
                onclick = "javascript:return FN_QSChangePage('shangyiye');"
                onMouseOver="MM_swapImage('shangyiye1','','<%=static_file%>images/shangyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg"
                width="79" height="22" id="shangyiye1">
                 </logic:equal>
                 <!--��������һҳ����ҳ����ʾ-->
                  <logic:equal name="queryWszForm" property="queryCache.hasNext" scope="session" value="true">
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
