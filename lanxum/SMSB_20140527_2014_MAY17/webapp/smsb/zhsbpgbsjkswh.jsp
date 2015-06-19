<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html>
  <head>
    <title>�����еط�˰����걨�ɿ</title>
    <link href="../css/text.css" rel="stylesheet" type="text/css"/>
    <link href="../css/top-box.css" rel="stylesheet" type="text/css"/>
    <link href="css/beijing.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="../js/treatImage.js" type="text/JavaScript"></script>  
    <script language="JavaScript" src="../js/smsb_common.js" type="text/JavaScript"></script>
  </head>

  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <%@ include file="./include/header.jsp"%>
    
    <!-- ������˰ -->
    <html:form action="/webapp/smsb/zhsbPgbsjkswhAction.do" method="POST" >

      <html:hidden property="actionType" value="Query"/>
      <html:hidden property="jkpzhStr" />
      <html:hidden property="sbbh" />
      <html:hidden property="jksType" />

      <table width="96%" align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">�ɿ���ά��</td>
        </tr>
        <tr>
          <td class="1-td2"> <br/>
              <table cellspacing="0" class="table-99">
                  <tr class="black9">
                      <td width="20%" nowrap class="2-td2-t-left"><div align="right">˰����������&nbsp;&nbsp;</div></td>
                      <td width="30%" nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<html:text property="jsjdm" tabindex="-1"  styleClass="inputnoborder"  size="30" readonly="true" style="color:#3C5564"/></div></td>
                      <td width="20%" nowrap class="2-td2-t-left"><div align="right">��λ����&nbsp;&nbsp;</div></td>
                      <td width="30%" nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<html:text property="nsrmc" tabindex="-1"  styleClass="inputnoborder"  size="30" readonly="true" style="color:#3C5564"/></div></td>
                  </tr>
              </table>
          </td>
        </tr>
      <!-- ������˰ -->
      <bean:define id="lwitems" name="zhsbPgbsjkswhActionForm" property="dataList" type="java.util.List"/>
      <bean:define id="nlwitems" name="zhsbPgbsjkswhActionForm" property="nlwDataList" type="java.util.List"/>
        <%
        if ((lwitems.size() > 0) || (nlwitems.size() > 0))
        {
            if (lwitems.size() > 0)
            {
        %>

        <tr>
          <td class="1-td2" > <br/>
            <table align="center" cellspacing="0" class="table-99">
              <tr>
                <td class="1-td1" >�����������нɿ���</td>
              </tr>
              <tr>
                <td class="1-td2" > <br>
                  <table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td nowrap class="2-td1-left">
                        <div align="center" >�걨���</div>
                      </td>
                      <td nowrap class="2-td1-left">
                        <div align="center" >�걨����</div>
                      </td>
                      <td nowrap class="2-td1-left">
                        <div align="center" >ʵ�ɽ��</div>
                      </td>
                      <td nowrap class="2-td1-center">
                        <div align="center" >����</div>
                      </td>
                    </tr>
                    <logic:iterate id="item" name="lwitems" type="java.util.Map">
                      <tr>
                        <td nowrap class="2-td2-left">
                          <div align="center">
                            <a href="javascript:printFunc('','<%=item.get("sbbh")%>','2')"><%=item.get("sbbh")%></a>
                          </div>
                        </td>
                        <td nowrap class="2-td2-left">
                          <div align="center"><%=item.get("sbrq")%></div>
                        </td>
                        <td nowrap class="2-td2-left">
                          <div align="center"><%=item.get("sjje")%></div>
                        </td>
                        <td nowrap class="2-td2-center">
                          <div align="center">
                            <a href="javascript:del('','<%=item.get("sbbh")%>','2')">����</a>
                          </div>
                        </td>
                      </tr>
                    </logic:iterate>
                  </table>
                  <br>
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <%
            }
            if (nlwitems.size() > 0)
            {
       %>
        <tr>
          <td valign="top"> <br/>
            <table align="center" cellspacing="0" class="table-99">
              <tr>
                <td class="1-td1">�������������нɿ���</td>
              </tr>
              <tr>
                <td class="1-td2"> <br>
                  <table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td nowrap class="2-td1-left">
                        <div align="center" >�ɿ�ƾ����</div>
                      </td>
                      <td nowrap class="2-td1-left">
                        <div align="center" >˰��˰Ŀ����</div>
                      </td>
                      <td nowrap class="2-td1-left">
                        <div align="center" >�걨����</div>
                      </td>
                      <td nowrap class="2-td1-left">
                        <div align="center" >ʵ�ɽ��</div>
                      </td>
                      <td nowrap class="2-td1-center">
                        <div align="center" >����</div>
                      </td>
                    </tr>
                    <logic:iterate id="item" name="nlwitems" type="java.util.Map">
                    <tr>
                      <td nowrap class="2-td2-left">
                        <div align="center">
                          <a href="javascript:printFunc('<%=item.get("jkpzh")%>','','1')"><%=item.get("jkpzh")%></a>
                        </div>
                      </td>
                      <td nowrap class="2-td2-left">
                        <div align="center"><%=item.get("szmc")%></div>
                      </td>
                      <td nowrap class="2-td2-left">
                        <div align="center"><%=item.get("sbrq")%></div>
                      </td>
                      <td nowrap class="2-td2-left">
                        <div align="center"><%=item.get("sjje")%></div>
                      </td>
                      <td nowrap class="2-td2-center">
                        <div align="center">
                          <a href="javascript:del('<%=item.get("jkpzh")%>','','1')">����</a>
                        </div>
                      </td>
                    </tr>
                    </logic:iterate>
                  </table>
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <%
              }
        }
        %>
        <tr>
          <td class="1-td2">
            <table align="center" cellspacing="0" class="table-99">
              <tr>
                <td>
                  <table border="0" width="100%">
                    <tr>
                      <td width="27%">&nbsp;
                      </td>
                      <td width="9%"><input type="image" accesskey="f" tabIndex="-1" onclick="doGoBack('Back');return false;" style="cursor:hand"  onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="fh1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" border="0">
                      </td>
                      <td width="9%"><input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22">
                      </td>
                      <td width="27%">&nbsp;</td>
                    </tr>
                  </table>
                  <br>
                </td>
              </tr>
            </table>
            <br>
            <%@ include file="./include/footer.jsp"%>
          </td>
        </tr>
      </table>
    </html:form>
    <script language="JavaScript" type="text/JavaScript">
      function del(pzh,sbbh,jkstype)
      {
        if ((pzh!="" || sbbh!="") && (jkstype != ""))
        {
          //������˰
          document.zhsbPgbsjkswhActionForm.jkpzhStr.value=pzh;
          document.zhsbPgbsjkswhActionForm.sbbh.value=sbbh;
          document.zhsbPgbsjkswhActionForm.jksType.value=jkstype;
          if (confirm("�ò�����ɾ��ҳ����һ�е�����,�Ҳ����Զ��ָ�,��ȷ��"))
          {
          	//������˰
            document.zhsbPgbsjkswhActionForm.target="";
            document.zhsbPgbsjkswhActionForm.actionType.value="Cx";
            document.zhsbPgbsjkswhActionForm.submit();
            document.forms[0].submit();
          }
        }
      }

      //���ص��ۺ��걨ҳ��
      function doGoBack(ope){
      	//������˰
        document.zhsbPgbsjkswhActionForm.target="";
        document.forms[0].actionType.value=ope;
        document.forms[0].submit();
      }
      function printFunc(pzh,sbbh,jkstype){
        if ((pzh!="" || sbbh!="") && (jkstype != "")){
          //������˰
          document.zhsbPgbsjkswhActionForm.jkpzhStr.value=pzh;
          document.zhsbPgbsjkswhActionForm.sbbh.value=sbbh;
          document.zhsbPgbsjkswhActionForm.jksType.value=jkstype;
          if (jkstype == '1'){
          	//������˰
            document.zhsbPgbsjkswhActionForm.target="";
            doSubmitForm('Ypys');
          }
          else{
          	//������˰
            document.zhsbPgbsjkswhActionForm.target="_blank";
            doSubmitForm('Ypds');
          }
        }
    }


//��д'../js/smsb_common.js'�е�tuchu()����  zzb  090407
	//�˳�
function tuichu(){
	if(returnStr==null || returnStr==""){
		//������˰
		returnStr="zhsbPgbsAction.do";
	}
	//��������ۺ��걨�����걨����ҳ�棬���˳����ۺ��걨ҳ��
	if(document.all.iszhsb && document.all.iszhsb.value=='1')
		//������˰
		returnStr="zhsbPgbsAction.do?actionType=Show";
	window.location=returnStr;
}
    </script>
  </body>
</html:html>
