<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<html>
<head>
<title>查看缴款书</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript">

//started added by qianchao 2006-2-8
<%
        String containerName = "";

        com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData)session.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
        if (userdata.getCaflag())
        {
	        containerName = userdata.getCert().getContainerName();
        }
        else
        {
	        containerName = "";
        }
		containerName = "";
%>
g_objSI.Container = "<%=containerName%>";
//ended   added by qianchao 2006-2-8
function doExit()
{
    document.forms[0].actionType.value = "Return";
    document.forms[0].target="_self";
    document.forms[0].submit();
}

function doDelete(sbbh)
{
    var msg = "";
    msg = "你确定作废本次申报所有缴款书吗？";
    if (confirm(msg))
    {
        document.forms[0].actionType.value = "Delete";
        document.forms[0].sbbhIndex.value=sbbh;
        document.forms[0].target="_self";
		//started added by qianchao 2006-2-8
		if (g_objSI.Container != '')
		{
	        if (!doSecuritySubmit(document.forms[0]))
	        {
		        return false;
	        }
	        else
	        {
	        	return true;
	        }
	    }
	    else
	    {
	        document.forms[0].submit();
	        return true;
	    }
		//ended   added by qianchao 2006-2-8
    }
}
function printFunc(pzh,sbbh,lwstate){
    if (pzh!="" || sbbh!=""){
        document.forms[0].actionType.value = "ViewOne";
        document.forms[0].jkshIndex.value=pzh;
        document.forms[0].sbbhIndex.value=sbbh;
        document.forms[0].lwState.value=lwstate;
        document.forms[0].target="_blank";
        document.forms[0].submit();
    }
}

</script>


</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table  width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  <td colspan=2 valign="top">
        <jsp:include page="../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="查看缴款书" />
        <jsp:param name="help_url" value="help/wssb/qyzhsb/ckjks/ckjks-000.htm"/>
    </jsp:include>

    <html:form action="zrrJks.do">
      <html:hidden property="actionType"/>
      <html:hidden property="jkshIndex"/>
      <html:hidden property="sbbhIndex"/>
      <html:hidden property="jsjdm"/>
      <html:hidden property="lwState" />
      <html:errors />
<font color="blue" size="5">
	<%=request.getAttribute("showMsg")==null?"":request.getAttribute("showMsg") %>
</font>
      <table width="96%" align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">缴款书维护</td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
              <table cellspacing="0" class="table-99">
                  <tr class="black9">
                      <td width="20%" nowrap class="2-td2-t-left"><div align="right">税务计算机代码&nbsp;&nbsp;</div></td>
                      <td width="30%" nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="zrrJksForm" property="jsjdm"/></div></td>
                      <td width="20%" nowrap class="2-td2-t-left"><div align="right">单位名称&nbsp;&nbsp;</div></td>
                      <td width="30%" nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<bean:write name="zrrJksForm" property="nsrmc"/></div></td>
                  </tr>
              </table>
          </td>
        </tr>
        <bean:define id="lwitems" name="zrrJksForm" property="dataList" type="java.util.List"/>
        <bean:define id="nlwitems" name="zrrJksForm" property="nlwDataList" type="java.util.List"/>
        <%
        if ((lwitems.size() > 0) || (nlwitems.size() > 0))
        {
            if (lwitems.size() > 0)
            {
        %>

        <tr>
          <td class="1-td2">
            <br>
            <table width="96%" align="center" cellspacing="0" class="table-99">
              <tr>
                <td class="1-td1" >撤销联网银行缴款书</td>
              </tr>
              <tr>
                <td class="1-td2" >
                  <br>
                  <table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0" >
                    <tr>
                      <td nowrap class="2-td1-left">
                        <div align="center" >申报表号</div>
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
                            <img onclick="doDelete('<%=item.get("sbbh")%>');" onMouseOver="this.src='<%=static_contextpath%>images/zuofei2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/zuofei1.jpg'" src="<%=static_contextpath%>images/zuofei1.jpg" alt="作废" width="79" height="22" style="Cursor:hand">
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
                                <img onclick="doDelete('<%=item.get("sbbh")%>');" onMouseOver="this.src='<%=static_contextpath%>images/zuofei2.jpg'" onMouseOut="this.src='<%=static_contextpath%>images/zuofei1.jpg'" src="<%=static_contextpath%>images/zuofei1.jpg" alt="作废" width="79" height="22" style="Cursor:hand"/>
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
          <td class="1-td2"> <br>
			<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="返回" onclick="doExit()" style="cursor:hand">
          </td>
        </tr>
  </html:form>
      </table>
    </td>
  </tr>
  <tr>
    <td valign="bottom" align="center">
         <%@ include file="../include/bottom.jsp" %>
    </td>
  </tr>
</table>
</body>
</html>
