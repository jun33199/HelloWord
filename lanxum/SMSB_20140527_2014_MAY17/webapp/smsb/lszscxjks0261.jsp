<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="com.ttsoft.bjtax.smsb.lszs.web.*" %>
<%@ page import="java.util.*" %>

<%@ page contentType="text/html; charset=gb2312" %>

<html:html>
  <%
  response.setHeader("pragma", "no-cache");
  response.setHeader("Cache-control", "no-cache, no-store");
  response.setHeader("Expires", "0");
  %>

  <head>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <title>撤销缴款书</title>
    <link href="../css/text.css" rel="stylesheet" type="text/css"/>
    <link href="../css/top-box.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" type="text/JavaScript" src="../js/treatImage.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="../js/smsb_common.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="../js/function.js"></script>

  </head>

  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <%@ include file="./include/header.jsp"%>

    <html:form action="/webapp/smsb/lszsCxjksAction.do" method="POST">
      <html:hidden property="actionType" />
      <html:hidden property="lrrdm" />
      <html:hidden property="headJkpzh" />
      <html:hidden property="headSbbh" />
      <html:hidden property="headJsjdm" />
      <html:hidden property="jksType" />


      <TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9' height="300">
        <tr>
          <td class="1-td1">缴款书维护</td>
        </tr>

      <bean:define id="lwitems" name="lszsCxjksForm" property="dataList" type="java.util.List"/>
      <bean:define id="nlwitems" name="lszsCxjksForm" property="nlwDataList" type="java.util.List"/>
        <%
        if ((lwitems.size() > 0) || (nlwitems.size() > 0))
        {
            if (lwitems.size() > 0)
            {
        %>

        <tr>
          <td valign="top" > <br>
            <table align="center" cellspacing="0" class="table-99">
              <tr>
                <td class="1-td1" >撤销联网银行缴款书</td>
              </tr>
              <tr>
                <td class="1-td2" > <br>
                  <table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td nowrap class="2-td1-left">
                        <div align="center" >申报编号</div>
                      </td>
                      <td nowrap class="2-td1-left">
                        <div align="center" >申报日期</div>
                      </td>
                      <td nowrap class="2-td1-left">
                        <div align="center" >实缴金额</div>
                      </td>
                      <td nowrap class="2-td1-center">
                        <div align="center" >作废</div>
                      </td>
                    </tr>
                    <logic:iterate id="item" name="lwitems" type="java.util.Map">
                      <tr>
                        <td nowrap class="2-td2-left">
                          <div align="center">
                            <a href="javascript:printFunc('','<%=item.get("sbbh")%>','<%=item.get("jsjdm")%>','2')"><%=item.get("sbbh")%></a>
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
                            <a href="javascript:del('','<%=item.get("sbbh")%>','<%=item.get("jsjdm")%>','2')">作废</a>
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
          <td valign="top"> <br>
            <table align="center" cellspacing="0" class="table-99">
              <tr>
                <td class="1-td1">撤销非联网银行缴款书</td>
              </tr>
              <tr>
                <td class="1-td2"> <br>
                  <table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td nowrap class="2-td1-left">
                        <div align="center" >缴款凭单号</div>
                      </td>
                      <td nowrap class="2-td1-left">
                        <div align="center" >税种税目名称</div>
                      </td>
                      <td nowrap class="2-td1-left">
                        <div align="center" >申报日期</div>
                      </td>
                      <td nowrap class="2-td1-left">
                        <div align="center" >实缴金额</div>
                      </td>
                      <td nowrap class="2-td1-center">
                        <div align="center" >作废</div>
                      </td>
                    </tr>
                    <logic:iterate id="item" name="nlwitems" type="java.util.Map">
                    <tr>
                      <td nowrap class="2-td2-left">
                        <div align="center">
                          <a href="javascript:printFunc('<%=item.get("jkpzh")%>','','<%=item.get("jsjdm")%>','1')"><%=item.get("jkpzh")%></a>
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
                          <a href="javascript:del('<%=item.get("jkpzh")%>','','<%=item.get("jsjdm")%>','1')">作废</a>
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
                      <td width="9%"><input type="image" accesskey="f" tabIndex="-1" onclick="doGoBack('Back');return false;" style="cursor:hand"  onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="返回" id="fh1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" border="0">
                      </td>
                      <td width="9%"><input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22">
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
  </body>
  <script language=JavaScript type="text/JavaScript" >
    function del(pzh,sbbh,dm,jkstype){

      if ((pzh!="" || sbbh!="") && (dm != "")){
        document.lszsCxjksForm.headJkpzh.value=pzh;
        document.lszsCxjksForm.headSbbh.value=sbbh;
        document.lszsCxjksForm.headJsjdm.value=dm;
        document.lszsCxjksForm.jksType.value=jkstype;
        if (confirm("该操作将删除页面中一行的数据,且不能自动恢复,请确认")){
          document.lszsCxjksForm.target="";
          document.lszsCxjksForm.actionType.value="Delete";
          document.lszsCxjksForm.submit();
          //doSubmitForm('Delete');
        }
      }
    }

    function printFunc(pzh,sbbh,dm,jkstype){
      if ((pzh!="" || sbbh!="") && (dm != "")){
        document.lszsCxjksForm.headJkpzh.value=pzh;
        document.lszsCxjksForm.headSbbh.value=sbbh;
        document.lszsCxjksForm.headJsjdm.value=dm;
        document.lszsCxjksForm.jksType.value=jkstype;
        document.lszsCxjksForm.target="_blank";
        //alert(document.gtgshCxjksForm.headJkpzh.value);
        //alert(document.gtgshCxjksForm.headJsjdm.value);
        doSubmitForm('Print');
      }
    }

    function doGoBack(){
      document.lszsCxjksForm.target="";
      doSubmitForm('Back');
    }

    </script>
    </html:html>
