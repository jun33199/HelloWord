<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<HTML><HEAD><TITLE>��ѯ����ά����</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<script language="JavaScript" type="text/JavaScript" src="<%=static_file%>js/judge.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_file%>js/calculate.js"></script>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  document.forms[0].submit();
}
</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
 <jsp:include page="/include/Header.jsp" flush="true">
                <jsp:param name="subtitle" value="��Ϣά��&gt;��ѯ����ά����"/>
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
          <td class=1-td1>��˰����ά��-��ѯ</td></TR>
        <TR>
          <td class=1-td2 vAlign=top>
            <html:form action="/xxwh/queryZcwh.do">
            <input type="hidden" name="operationType" value="">
              <logic:equal name="queryZcwhForm" property="status" scope="session" value="Result">
                <input type="hidden" name="changePage" value='<bean:write name="queryZcwhForm" property="queryCache.currentPageNum"/>'>
                <input type="hidden" name="pageCount" value='<bean:write name="queryZcwhForm" property="queryCache.pageCount"/>'>
              </logic:equal>
            <br>
             <table border="0" cellSpacing=0 class=table-60>
              <TBODY>
               <TR>
                <td class=2-td1-center width="50%">
                  ָ�����&nbsp;
                </td>
                <td class="2-td2-t-right" width="50%">
                  <DIV align=left><html:text property="zbdm" maxlength="4" tabindex="0"/></DIV>
                </td>
              </TR>
               <TR>
                <td class="2-td1-Bcenter">
                  ָ������&nbsp;
                </td>
                <td class="2-td2-right">
                  <DIV align=left><html:text property="zbmc" maxlength="100" tabindex="0"/></DIV>
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

        <IMG name="add"
          onClick= "doSubmitForm('Add');"
          onMouseOver="MM_swapImage('tianjia','','<%=static_file%>images/tianjia2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tianjia1.jpg"
          width="79" height="22" id="tianjia" alt="����" style="cursor:hand">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

        <IMG name="back"
          onclick="doSubmitForm('Return');"
          onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
          width="79" height="22" id="tuichu1" alt="�˳�" style="cursor:hand">
      </DIV><BR>

          <logic:equal name="queryZcwhForm" property="status" scope="session" value="Result">
          <table cellspacing="0" class="table-99">
            <tr>
            <td class="2-td2-t-left">��ѯ���</td>
           <!--��������Ϊ�գ���ʾû�м�¼-->
           <logic:equal name="queryZcwhForm" property="queryCache.pageCount" scope="session" value="0">
            <td class="2-td2-t-center"> <div align="center">û����Ҫ�ļ�¼�������²�ѯ</div></td>
           </logic:equal>
           <logic:notEqual name="queryZcwhForm" property="queryCache.pageCount" scope="session" value="0">
              <td class="2-td2-t-center"> <div align="right">
               ��<bean:write name="queryZcwhForm" property="queryCache.countNumber"/>����¼
               ��<bean:write name="queryZcwhForm" property="queryCache.currentPageNum"/>/
               <bean:write name="queryZcwhForm" property="queryCache.pageCount"/>ҳ
                  <input type="image" name="beginpage" value="��һҳ" alt="��һҳ"
                  onclick = "javascript:return FN_ChangePage('diyiye');"
                  onMouseOver="MM_swapImage('diyiye','','<%=static_file%>images/diyiye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg" id="diyiye">
                  <input type="image" name="endpage" value="���һҳ" alt="���һҳ"
                  onclick = "javascript:return FN_ChangePage('zuihouyiye');"
                  onMouseOver="MM_swapImage('zuimoye','','<%=static_file%>images/zuimoye2.jpg',1)"
                  onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg" id="zuimoye" >
                <!--���ֻ��һҳ�����水ť����ʾ-->
                <logic:equal name="queryZcwhForm" property="queryCache.isOnePage" scope="session" value="false">
                  <!--����ǵ�һҳ��ǰҳ����ʾ-->
                  <logic:equal name="queryZcwhForm" property="queryCache.hasPrev" scope="session" value="true">
                    <input type="image" name="frontpage" value="ǰҳ" alt="ǰҳ"
                    onclick = "javascript:return FN_ChangePage('shangyiye');"
                    onMouseOver="MM_swapImage('shangyiye','','<%=static_file%>images/shangyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg" id="shangyiye">
                  </logic:equal>
                  <!--��������һҳ����ҳ����ʾ-->
                  <logic:equal name="queryZcwhForm" property="queryCache.hasNext" scope="session" value="true">
                    <input type="image" name="backpage" value="��ҳ" alt="��ҳ"
                    onclick = "javascript:return FN_ChangePage('xiayiye');"
                    onMouseOver="MM_swapImage('xiayiye','','<%=static_file%>images/xaiyiye2.jpg',1)"
                    onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/xaiyiye1.jpg" id="xiayiye">
                  </logic:equal>
                </logic:equal>
                </div></td>
             </logic:notEqual>
            </tr>
          </table>
          <!--�����¼���Ϊ�գ�����ʾ-->
          <logic:notEqual name="queryZcwhForm" property="queryCache.pageCount" scope="session" value="0">
          <br>
          <table cellspacing="0" class="table-99">
            <tr>
              <td class="2-td1-left" width="5%">ѡ��</td>
              <td class="2-td1-left" width="10%">ָ�����</td>
              <td class="2-td1-left" width="15%">ָ������</td>
              <td class="2-td1-left" width="10%">ָ��ֵ</td>
              <td class="2-td1-left" width="10%">��Ч��ʼʱ��</td>
              <td class="2-td1-left" width="10%">��Ч��ʼʱ��</td>
              <td class="2-td1-left" width="10%">¼����</td>
              <td class="2-td1-left" width="10%">¼������</td>
              <td class="2-td1-center" width="20%">��ע</td>
            </tr>
      <logic:iterate id="data" indexId="index" length="length" name="queryZcwhForm" property="queryCache.currentPage"
            type="com.creationstar.bjtax.qsgl.BizLogic.vo.Zcwh">
            <tr>
              <td class="2-td2-left">
              <input type="checkbox" name="selectedIndex"
                     onclick="FN_setChkState()" value="<%=index.intValue()%>">
              </td>
              <td class="2-td2-left"><div align="left">
                    <bean:write name="data" property="zbdm"/></div>
              </td>
              <td class="2-td2-left"><div align="left">
              &nbsp;<bean:write name="data" property="zbmc"/></div>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="zbz"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="sxqsrq"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="sxjzrq"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="lrr"/>
              </td>
              <td class="2-td2-left">
              &nbsp;<bean:write name="data" property="lrrq"/>
              </td>
              <td class="2-td2-center" style="word-break:break-all"><div align="left">
              &nbsp;<bean:write name="data" property="bz"/></div>
              </td>
            </tr>
      </logic:iterate>
          </table>
          <br>
       <table cellspacing="0" class="table-99">
          <tr>
              <td colspan="8" class="black9"><br>
               <input type="checkbox" name="chkall" onclick="javascript:All_quanxuan();">
               �Ƿ�ȫѡ
                <input type="image" name="delete" value="ɾ��" alt="ɾ��"
                onclick = "javascript:return doSubmitForm('Delete');"
                onMouseOver="MM_swapImage('shanchu','','<%=static_file%>images/shanchu2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shanchu1.jpg"
                width="79" height="22" id="shanchu">
                <input type="image" name="add" value="����" alt="����"
                 onclick = "javascript:return doSubmitForm('Add');"
                 onMouseOver="MM_swapImage('tianjia','','<%=static_file%>images/tianjia2.jpg',1)"
                 onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tianjia1.jpg"
                 width="79" height="22" id="tianjia">
            </td>
            <td align="right"><br>
                <input type="image" name="beginpage" value="��һҳ" alt="��һҳ"
                onclick = "javascript:return FN_ChangePage('diyiye');"
                onMouseOver="MM_swapImage('diyiye1','','<%=static_file%>images/diyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/diyiye1.jpg"
                width="79" height="22" id="diyiye1">
                <input type="image" name="endpage" value="���һҳ" alt="���һҳ"
                onclick = "javascript:return FN_ChangePage('zuihouyiye');"
                onMouseOver="MM_swapImage('zuimoye1','','<%=static_file%>images/zuimoye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/zuimoye1.jpg"
                width="79" height="22" id="zuimoye1" >
                <!--���ֻ��һҳ�����水ť����ʾ-->
               <logic:equal name="queryZcwhForm" property="queryCache.isOnePage" scope="session" value="false">
                 <!--����ǵ�һҳ��ǰҳ����ʾ-->
                  <logic:equal name="queryZcwhForm" property="queryCache.hasPrev" scope="session" value="true">
                <input type="image" name="frontpage" value="ǰҳ" alt="ǰҳ"
                onclick = "javascript:return FN_ChangePage('shangyiye');"
                onMouseOver="MM_swapImage('shangyiye1','','<%=static_file%>images/shangyiye2.jpg',1)"
                onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/shangyiye1.jpg"
                width="79" height="22" id="shangyiye1">
                 </logic:equal>
                 <!--��������һҳ����ҳ����ʾ-->
                  <logic:equal name="queryZcwhForm" property="queryCache.hasNext" scope="session" value="true">
                   <input type="image" name="backpage" value="��ҳ" alt="��ҳ"
                   onclick = "javascript:return FN_ChangePage('xiayiye');"
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
