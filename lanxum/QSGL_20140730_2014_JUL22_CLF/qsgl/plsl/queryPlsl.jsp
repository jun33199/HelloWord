<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>

<HTML><HEAD><TITLE>��ѯ����������Ϣ</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<script language="JavaScript" type="text/JavaScript" src="<%=static_file%>js/judge.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_file%>js/calculate.js"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType)
{
	if(operationType=="Delete"||operationType=="Check")
	{
		if(document.forms[0].chkall.checked==false)
		{
			alert("��ѡ��Ҫ�����Ķ���")
			return false;
		}

	}
    if(operationType=="Query")
	{
        if(document.forms[0].drrq.value!="")
		{
            if(!checkDate(document.forms[0].drrq.value))
            {
                alert("�������ڸ�ʽ����")
                document.forms[0].drrq.focus();
                return false;
            }
		}
        if(document.forms[0].drrq.value==""&&document.forms[0].drpch.value==""
            &&document.forms[0].tgzmc.value==""
            &&document.forms[0].tgzjsjdm.value==""&&document.forms[0].nsrmc.value=="")
		{
			alert("��������������һ����ѯ����")
			return false;
		}
    }
    if(operationType=="Check")
	{
		if(!confirm("ȷ����������"))
		{
			return false;
		}
	}
  document.all.operationType.value=operationType;
  document.forms[0].submit();
}
</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
 <jsp:include page="/include/Header.jsp" flush="true">
                <jsp:param name="subtitle" value="��������&gt;��ѯ����������Ϣ"/>
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
          <td class=1-td1>����������Ϣ-��ѯ</td></TR>
        <TR>
          <td class=1-td2 vAlign=top>
            <html:form action="/plsl/plslQuery.do">
			<html:hidden property="from" value="cx"/>
            <html:hidden property="drlx" value=""/>
            <input type="hidden" name="operationType" value="">
              <logic:equal name="queryPlslForm" property="status" scope="session" value="Result">
                <input type="hidden" name="changePage" value='<bean:write name="queryPlslForm" property="queryCache.currentPageNum"/>'>
                <input type="hidden" name="pageCount" value='<bean:write name="queryPlslForm" property="queryCache.pageCount"/>'>
              </logic:equal>
            <br>
             <table border="0" cellSpacing=0 class=table-60>
              <TBODY>
               <TR>
                <td class=2-td1-center width="50%">
                  ���κ�&nbsp;
                </td>
                <td class="2-td2-t-right" width="50%">
                  <DIV align=left><html:text property="drpch" maxlength=""/></DIV>
                </td>
              </TR>
               <TR>
                <td class="2-td1-Bcenter">
                  ״̬&nbsp;
                </td>
                <td class="2-td2-right">
                  <DIV align=left>
				       <html:select name="queryPlslForm" property="sl" >
                        <html:option value="all">����</html:option>
                        <html:option value="unreceived">δ����</html:option>
                        <html:option value="received">�������</html:option>
                      </html:select></DIV>
                </td>
              </TR>

               <TR>
                <td class=2-td1-Bcenter width="50%">
                  �ṩ������&nbsp;
                </td>
                <td class="2-td2-right" width="50%">
                  <DIV align=left><html:text property="tgzmc" maxlength="" /></DIV>
                </td>
              </TR>
               <TR>
                <td class=2-td1-Bcenter width="50%">
                  �ṩ�߼��������&nbsp;
                </td>
                <td class="2-td2-right" width="50%">
                  <DIV align=left><html:text property="tgzjsjdm" maxlength="" /></DIV>
                </td>
              </TR>
               <TR>
                <td class=2-td1-Bcenter width="50%">
                  ��������&nbsp;
                </td>
                <td class="2-td2-right" width="50%">
                  <DIV align=left><html:text property="drrq" maxlength="8" />(yyyymmdd)</DIV>
                </td>
              </TR>
               <TR>
                <td class=2-td1-Bcenter width="50%">
                  ��˰������&nbsp;
                </td>
                <td class="2-td2-right" width="50%">
                  <DIV align=left><html:text property="nsrmc" maxlength="" /></DIV>
                </td>
              </TR>

			                <tr>
                <td class="2-td1-Bcenter">�ɿʽ</td>
                <td class="2-td2-right">
				<DIV align=left>
                                          <bean:define id="jsfs" name="queryPlslForm" property="jkfsList"  />
                                          <html:select property="jkfsdm">
                                            <html:options collection="jsfs" labelProperty="jsfsmc" property="jsfsdm" />
                                          </html:select>
				</DIV>
                                          <html:hidden property="jkfsmc"/>
                </td>
              </tr>
     </table><BR>

      <DIV align=center>
        <IMG name="query"
          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
          onclick = "doSubmitForm('Query');"
          width="79" height="22" id="chaxun1" alt="��ѯ" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

     <logic:equal name="queryPlslForm" property="status" scope="session" value="Result">
        <IMG alt=ɾ�� height=22 id=delete name="btnDelete"
            onclick="doSubmitForm('Delete');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('delete','','<%=static_file%>images/shanchu2.jpg',1)"
            src="<%=static_file%>images/shanchu1.jpg" style="CURSOR: hand" width=79>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </logic:equal>

        <IMG name="back"
          onclick="doSubmitForm('Return');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="�˳�" style="cursor:hand">
      </DIV><BR>

          <logic:equal name="queryPlslForm" property="status" scope="session" value="Result">
          <table cellspacing="0" class="table-99">
            <tr>
            <td class="2-td2-t-left">��ѯ���</td>
           <!--��������Ϊ�գ���ʾû�м�¼-->
           <logic:equal name="queryPlslForm" property="queryCache.pageCount" scope="session" value="0">
            <td class="2-td2-t-center"> <div align="center">û����Ҫ�ļ�¼�������²�ѯ</div></td>
           </logic:equal>
           <logic:notEqual name="queryPlslForm" property="queryCache.pageCount" scope="session" value="0">
              <td class="2-td2-t-center"> <div align="right">
               ��<bean:write name="queryPlslForm" property="queryCache.countNumber"/>����¼
               ��<bean:write name="queryPlslForm" property="queryCache.currentPageNum"/>/
               <bean:write name="queryPlslForm" property="queryCache.pageCount"/>ҳ
                  <input type="image" name="beginpage" value="��һҳ" alt="��һҳ"
                  onclick = "javascript:return FN_QSChangePage('diyiye');"
                  onMouseOver="MM_swapImage('diyiye','','<%=static_file%>images/diyiye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg" id="diyiye">
                  <input type="image" name="endpage" value="���һҳ" alt="���һҳ"
                  onclick = "javascript:return FN_QSChangePage('zuihouyiye');"
                  onMouseOver="MM_swapImage('zuimoye','','<%=static_file%>images/zuimoye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg" id="zuimoye" >
                <!--���ֻ��һҳ�����水ť����ʾ-->
                <logic:equal name="queryPlslForm" property="queryCache.isOnePage" scope="session" value="false">
                  <!--����ǵ�һҳ��ǰҳ����ʾ-->
                  <logic:equal name="queryPlslForm" property="queryCache.hasPrev" scope="session" value="true">
                    <input type="image" name="frontpage" value="ǰҳ" alt="ǰҳ"
                    onclick = "javascript:return FN_QSChangePage('shangyiye');"
                    onMouseOver="MM_swapImage('shangyiye','','<%=static_file%>images/shangyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg" id="shangyiye">
                  </logic:equal>
                  <!--��������һҳ����ҳ����ʾ-->
                  <logic:equal name="queryPlslForm" property="queryCache.hasNext" scope="session" value="true">
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
          <logic:notEqual name="queryPlslForm" property="queryCache.pageCount" scope="session" value="0">
          <br>
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="5%">ѡ��</td>
              <td class="2-td1-left" width="10%">�������κ�</td>
              <td class="2-td1-left" width="9%">���</td>
              <td class="2-td1-left" width="12%">��˰������</td>
              <td class="2-td1-left" width="12%">���ز���Ŀ</td>
              <td class="2-td1-left" width="12%">���ز���ַ</td>
              <td class="2-td1-left" width="10%">��ͬ����</td>
              <td class="2-td1-left" width="10%">�ɽ��۸�</td>
			  <td class="2-td1-left" width="10%">��˰��ʽ</td>
              <td class="2-td1-center" width="10%">����ʱ��</td>
            </tr>
      <logic:iterate id="data" indexId="index" length="length" name="queryPlslForm" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.model.bo.PlsbBo">
            <tr>
              <td class="2-td2-left">
<logic:equal name="queryPlslForm" property="sl"  value="received">
              <input type="checkbox" name="selectedIndex" disabled="true"
                     onclick="FN_setChkState()" value="<%=index.intValue()%>">
</logic:equal>���

<logic:equal name="queryPlslForm" property="sl"  value="unreceived">
              <input type="checkbox" name="selectedIndex"
                     onclick="FN_setChkState()" value="<%=index.intValue()%>">
</logic:equal>

<logic:equal name="queryPlslForm" property="sl"  value="all">
              <input type="checkbox" name="selectedIndex" disabled="true"
                     onclick="FN_setChkState()" value="<%=index.intValue()%>">
</logic:equal>
              </td>
              <td class="2-td2-left"><div align="left">
                    <bean:write name="data" property="drzb.drpch"/></div>
              </td>
              <td class="2-td2-left"><div align="left">
			  <a href="<%=response.encodeURL("/qsgl/plsl/plsl.do?operationType=View&drlx=0&viewIndex=") + index.intValue()%>">
              &nbsp;<bean:write name="data" property="drzb.xh"/></div></a>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="drzb.nsrmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="drzb.fdcxmmc"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="drzb.fdcdz"/>
              </td>
              <td class="2-td2-left">
			  <bean:define id="dr" name="data" property="drzb" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb"/>
              &nbsp;<%=DataConvert.TimeStamp2String(dr.getHtqdrq())%>
              </td>
              <td class="2-td2-left">
              &nbsp; <%=DataConvert.BigDecimal2String(dr.getCjjg())%>
              </td>
              <td class="2-td2-left">
              &nbsp; <bean:write name="data" property="drpcInfo.jsfmc"/>
              </td>
              <td class="2-td2-center"><div align="left">
              &nbsp;<%=DataConvert.TimeStamp2String(dr.getDrsj())%></div>
              </td>
            </tr>
      </logic:iterate>
          </table>
          <br>
       <table cellspacing="0" class="table-99">
          <tr>
              <td colspan="8" class="black9"><br>

<logic:equal name="queryPlslForm" property="sl"  value="unreceived">
               <input type="checkbox" name="chkall" onclick="javascript:All_quanxuan();">
               �Ƿ�ȫѡ
</logic:equal>
<logic:equal name="queryPlslForm" property="sl"  value="received">
               <input type="checkbox" name="chkall" disabled="true" onclick="javascript:All_quanxuan();">
               �Ƿ�ȫѡ
</logic:equal>
<logic:equal name="queryPlslForm" property="sl"  value="all">
               <input type="checkbox" name="chkall" disabled="true" onclick="javascript:All_quanxuan();">
               �Ƿ�ȫѡ
</logic:equal>

                <input type="image" name="delete" value="ɾ��" alt="ɾ��"
                onclick = "javascript:return doSubmitForm('Delete');"
                onMouseOver="MM_swapImage('shanchu','','<%=static_file%>images/shanchu2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shanchu1.jpg"
                width="79" height="22" id="shanchu">

				<input type="image" name="shouli" value="����" alt="����"
                onclick = "javascript:return doSubmitForm('Check');"
                onMouseOver="MM_swapImage('shouli','','<%=static_file%>images/shouli2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shouli1.jpg"
                width="79" height="22" id="shouli">
            </td>

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
               <logic:equal name="queryPlslForm" property="queryCache.isOnePage" scope="session" value="false">
                 <!--����ǵ�һҳ��ǰҳ����ʾ-->
                  <logic:equal name="queryPlslForm" property="queryCache.hasPrev" scope="session" value="true">
                <input type="image" name="frontpage" value="ǰҳ" alt="ǰҳ"
                onclick = "javascript:return FN_QSChangePage('shangyiye');"
                onMouseOver="MM_swapImage('shangyiye1','','<%=static_file%>images/shangyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg"
                width="79" height="22" id="shangyiye1">
                 </logic:equal>
                 <!--��������һҳ����ҳ����ʾ-->
                  <logic:equal name="queryPlslForm" property="queryCache.hasNext" scope="session" value="true">
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
