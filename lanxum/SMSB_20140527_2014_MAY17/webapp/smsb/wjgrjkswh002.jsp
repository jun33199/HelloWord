<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html>
  <%
  response.setHeader("pragma", "no-cache");
  response.setHeader("Cache-control", "no-cache, no-store");
  response.setHeader("Expires", "0");
  %>

  <head>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <title>北京市地方税务局申报缴款单</title>
    <link href="../css/text.css" rel="stylesheet" type="text/css"/>
    <link href="../css/top-box.css" rel="stylesheet" type="text/css"/>
    <link href="css/beijing.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" type="text/JavaScript" src="../js/treatImage.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="../js/smsb_common.js"></script>
    <script language="JavaScript" type="text/JavaScript" src="../js/DTable.js"></script>
  </head>

  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <%@ include file="./include/header.jsp"%>
    <html:form action="/webapp/smsb/wjgrJkswhAction.do" method="POST" >

      <html:hidden property="actionType" value="Show"/>
      <html:hidden property="gjdm" />
      <html:hidden property="zjlxdm" />
      <html:hidden property="sbrq" />
      <html:hidden property="headJkpzh" />
      <html:hidden property="sbbh" />
      <html:hidden property="jksType" />
      <table width="96%" align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">缴款书维护</td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
            <table cellspacing="0" class="table-99" >
              <tr class="black9">
                <td nowrap class="2-td2-t-left"><strong>税务计算机代码</strong></td>
                <td nowrap class="2-td2-t-left"><div align="left"><html:text property="jsjdm" styleClass="inputnoborder" style="color:#3C5564" readonly="true"  size="20"/></div></td>
                <td nowrap class="2-td2-t-left"><strong>纳税人名称</strong></td>
                <td nowrap class="2-td2-t-center"><div align="left"><html:text property="nsrmc" styleClass="inputnoborder" style="color:#3C5564" readonly="true" size="80"/></div></td>
              </tr>
              <tr class="black9">
                <td nowrap class="2-td2-left"><strong>国籍</strong></td>
                <td nowrap class="2-td2-left"><div align="left"><ttsoft:dmmc property="gjdm" codekey="GJDQ" /></div></td>
                <td nowrap class="2-td2-left"><strong>证件类型</strong></td>
                <td nowrap class="2-td2-center"><div align="left"><ttsoft:dmmc property="zjlxdm" codekey="ZJLX" /></div></td>
              </tr>
              <tr class="black9">
                <td nowrap class="2-td2-left"><strong>证件号码</strong></td>
                <td nowrap class="2-td2-left" ><div align="left"><html:text property="zjhm" styleClass="inputnoborder" style="color:#3C5564" readonly="true"  size="20"/></div></td>
                <td nowrap class="2-td2-left"><strong>&nbsp;</strong></td>
                <td nowrap class="2-td2-center">&nbsp;</td>
              </tr>
            </table>
            <br>
          </td>
        </tr>
      <bean:define id="lwitems" name="wjgrJkswhActionForm" property="dataList" type="java.util.List"/>
      <bean:define id="nlwitems" name="wjgrJkswhActionForm" property="nlwDataList" type="java.util.List"/>
        <%
        if ((lwitems.size() > 0) || (nlwitems.size() > 0))
        {
            if (lwitems.size() > 0)
            {
        %>

        <tr>
          <td class="1-td2" > <br>
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
                            <a href="javascript:del('<%=item.get("sbbh")%>')">作废</a>
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
          <td class="1-td2">
            <br>
            <table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0" >
              <tr>
                <td class="1-td1">撤销非联网银行缴款书</td>
              </tr>
              <logic:iterate id="item" name="nlwitems" type="java.util.Map">
              <tr>
                  <td class="1-td2"><br></td>
              </tr>
              <tr>
                <td class="1-td2">
                  <table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td colspan="3" >
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                          <tr>
                            <th nowrap class="2-td1-left">
                                <div align="center" >申报日期：</div>
                            </th>
                            <td nowrap class="2-td1-left">
                                <div align="center" ><%=item.get("sbrq")%></div>
                            </td>
                            <th nowrap class="2-td1-left">
                                <div align="center" >申报表号：</div>
                            </th>
                            <td nowrap class="2-td1-left">
                                <div align="center" ><%=item.get("sbbh")%></div>
                            </td>
                            <td nowrap class="2-td1-center">
                              <div align="center">
                                <img onclick="del('<%=item.get("sbbh")%>');" onMouseOver="this.src='<%=static_contextpath%>images/zuofei2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/zuofei1.jpg'" src="<%=static_contextpath%>images/zuofei1.jpg" alt="作废" width="79" height="22" style="Cursor:hand"/>
                              </div>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr>
                      <td nowrap class="2-td1-left">
                        <div align="center" >缴款凭单号</div>
                      </td>
                      <td nowrap class="2-td1-left">
                        <div align="center" >税种税目名称</div>
                      </td>
                      <td nowrap class="2-td1-center">
                        <div align="center" >实缴金额</div>
                      </td>
                    </tr>
                    <%
                    List jkpzhitems = (List)item.get("JKPZLIST");
                    pageContext.setAttribute("jkpzhitems",jkpzhitems);
                    %>
                    <logic:iterate id="jkpz" name="jkpzhitems" type="java.util.Map">
                    <tr>
                      <td nowrap class="2-td2-left">
                        <div align="center">
                          <a href="javascript:printFunc('<%=jkpz.get("jkpzh")%>','<%=item.get("sbbh")%>','1')"><%=jkpz.get("jkpzh")%></a>
                        </div>
                      </td>
                      <td nowrap class="2-td2-left">
                          <div align="center"><%=jkpz.get("szmc")%></div>
                      </td>
                      <td nowrap class="2-td2-center">
                          <div align="center"><%=jkpz.get("sjje")%></div>
                      </td>
                    </tr>
                    </logic:iterate>
                  </table>
                </td>
              </tr>
              </logic:iterate>

            </table>
          </td>
        </tr>
        <%
              }
        }
        %>
        <tr>
          <td>
            <table width="94%" border="0" cellpadding="0" cellspacing="4">
              <tr valign="bottom">
                <td width="49%"> </td>
                <td width="12%">
                  <input type="image" accesskey="f" tabIndex="-1" onclick="doGoBack('Back');return false;" style="cursor:hand"  onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="返回" id="fh1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" border="0"/>&nbsp;&nbsp;
                  <input type="image" accesskey="c" tabIndex="-1"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22"/>
                </td>
                <td width="0%">&nbsp;</td>
              </tr>
            </table>
            <br>
            <%@ include file="./include/footer.jsp"%>
          </td>
        </tr>
      </table>
    </html:form>
  </body>


  <script language="JavaScript" type="text/JavaScript">
  function del(sbbh){
    if(confirm("是否要删除该缴款书信息,请确认！")){
      document.forms[0].sbbh.value=sbbh;
      document.forms[0].actionType.value='Cx';
      document.forms[0].target="";
      document.forms[0].submit();
    }
  }

  function printFunc(pzh,sbbh,jkstype){
    if ((pzh !="") || (sbbh != "")){
      document.forms[0].headJkpzh.value=pzh;
      document.forms[0].sbbh.value=sbbh;
      document.forms[0].jksType.value=jkstype;
      document.forms[0].target="blank";
      document.forms[0].actionType.value='Print';
      document.forms[0].target="_blank";
      document.forms[0].submit();
    }
  }

  function doGoBack(){
    document.forms[0].target="";
    doSubmitForm('Back');
  }
  </script>


</html:html>
