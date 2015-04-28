<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html>
  <head>
    <title>北京市地方税务局申报缴款单</title>
    <link href="../css/text.css" rel="stylesheet" type="text/css"/>
    <link href="../css/top-box.css" rel="stylesheet" type="text/css"/>
    <link href="css/beijing.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="../js/treatImage.js" type="text/JavaScript"></script>
    <script language="JavaScript" src="../js/smsb_common.js" type="text/JavaScript"></script>
  </head>

  <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <%@ include file="./include/header.jsp"%>
    <html:form action="/webapp/smsb/qsjksbjkswhAction.do" method="POST" >

      <html:hidden property="actionType" value="Query"/>
      <html:hidden property="jkpzhStr" />
      <html:hidden property="sbbh" />
      <html:hidden property="jksType" />
      	<html:hidden property="gljkpzh" />
      		<html:hidden property="dzjkpzh" />
      			<html:hidden property="qsxh" />
      				<html:hidden property="sbje" />

      <table width="96%" align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">缴款书维护</td>
        </tr>
        <tr>
          <td class="1-td2"> <br/>
              <table cellspacing="0" class="table-99">
                  <tr class="black9">
                      <td width="20%" nowrap class="2-td2-t-left"><div align="right">税务计算机代码&nbsp;&nbsp;</div></td>
                      <td width="30%" nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<html:text property="jsjdm" tabindex="-1"  styleClass="inputnoborder"  size="30" readonly="true" style="color:#3C5564"/></div></td>
                      <td width="20%" nowrap class="2-td2-t-left"><div align="right">单位名称&nbsp;&nbsp;</div></td>
                      <td width="30%" nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<html:text property="nsrmc" tabindex="-1"  styleClass="inputnoborder"  size="30" readonly="true" style="color:#3C5564"/></div></td>
                  </tr>
              </table>
          </td>
        </tr>
      <bean:define id="lwitems" name="qsjksbjkswhActionForm" property="dataList" type="java.util.List"/>
      <bean:define id="nlwitems" name="qsjksbjkswhActionForm" property="nlwDataList" type="java.util.List"/>
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
                <td class="1-td1" >撤销联网银行缴款书</td>
              </tr>
              <tr>
                <td class="1-td2" > <br>
                  <table class="table-99" width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td nowrap class="2-td1-left">
                        <div align="center" >申报序号</div>
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
                            <a href="javascript:dellw('','<%=item.get("sbbh")%>','2','<%=item.get("cxbs")%>')">作废</a>
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
                          <a href="javascript:delnlw('<%=item.get("jkpzh")%>','','1','<%=item.get("dzjkpzh")%>','<%=item.get("cxbs")%>','<%=item.get("pzh")%>','<%=item.get("qsxh")%>','<%=item.get("sbje")%>')">作废</a>
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
    <script language="JavaScript" type="text/JavaScript">
    	
    	//撤销联网银行缴款书
      function dellw(pzh,sbbh,jkstype,cxbs)
      {
        if ((pzh!="" || sbbh!="") && (jkstype != ""))
        {
        	if(cxbs=='0')
        	{
          	document.qsjksbjkswhActionForm.jkpzhStr.value=pzh;
          	document.qsjksbjkswhActionForm.sbbh.value=sbbh;
          	document.qsjksbjkswhActionForm.jksType.value=jkstype;
          	if (confirm("该操作将删除页面中一行的数据,且不能自动恢复,请确认"))
          	{
            	document.qsjksbjkswhActionForm.target="";
            	document.qsjksbjkswhActionForm.actionType.value="Cx";
            	document.qsjksbjkswhActionForm.submit();
            	document.forms[0].submit();
          	}
          }
          else
          {
          	alert("该申报编号对应的联网缴款书有申报已入库，不能撤销");		
          }
        }

      }
      
      //撤销非联网银行缴款书
      function delnlw(pzh,sbbh,jkstype,gljkpzh,cxbs,dzjkpzh,qsxh,sbje)
      {
        if ((pzh!="" || sbbh!="") && (jkstype != ""))
        {
        	if(gljkpzh!="")
        	{
        		if(cxbs=='0')
        		{
          		document.qsjksbjkswhActionForm.jkpzhStr.value=pzh;
          		document.qsjksbjkswhActionForm.sbbh.value=sbbh;
          		document.qsjksbjkswhActionForm.jksType.value=jkstype;
          		document.qsjksbjkswhActionForm.gljkpzh.value=gljkpzh;
          		document.qsjksbjkswhActionForm.dzjkpzh.value=dzjkpzh;
          		document.qsjksbjkswhActionForm.qsxh.value=qsxh;
          		document.qsjksbjkswhActionForm.sbje.value=sbje;
          		if (confirm("该操作将删除页面中缴款凭单号为"+pzh+"和"+gljkpzh+"的两行关联数据,且不能自动恢复,请确认"))
          		{
            		document.qsjksbjkswhActionForm.target="";
            		document.qsjksbjkswhActionForm.actionType.value="Cx";
            		document.qsjksbjkswhActionForm.submit();
            		document.forms[0].submit();
          		}        			
        		}
            else
          	{
          		alert("与该缴款凭单号关联的该缴款凭单号为"+gljkpzh+"的申报已入库，不能撤销");		
          	}        		
        	}
        	else
        		{
          		document.qsjksbjkswhActionForm.jkpzhStr.value=pzh;
          		document.qsjksbjkswhActionForm.sbbh.value=sbbh;
          		document.qsjksbjkswhActionForm.jksType.value=jkstype;
          		document.qsjksbjkswhActionForm.gljkpzh.value=gljkpzh;
          		document.qsjksbjkswhActionForm.dzjkpzh.value=dzjkpzh;
          		document.qsjksbjkswhActionForm.qsxh.value=qsxh;
          		document.qsjksbjkswhActionForm.sbje.value=sbje;
          		if (confirm("该操作将删除页面中一行的数据,且不能自动恢复,请确认"))
          		{
            		document.qsjksbjkswhActionForm.target="";
            		document.qsjksbjkswhActionForm.actionType.value="Cx";
            		document.qsjksbjkswhActionForm.submit();
            		document.forms[0].submit();
          		}         			
        		}
        }

      }

      //返回到综合申报页面
      function doGoBack(ope){
        document.qsjksbjkswhActionForm.target="";
        document.forms[0].actionType.value=ope;
        document.forms[0].submit();
      }
      function printFunc(pzh,sbbh,jkstype){
        if ((pzh!="" || sbbh!="") && (jkstype != "")){
          document.qsjksbjkswhActionForm.jkpzhStr.value=pzh;
          document.qsjksbjkswhActionForm.sbbh.value=sbbh;
          document.qsjksbjkswhActionForm.jksType.value=jkstype;
          if (jkstype == '1'){
            document.qsjksbjkswhActionForm.target="";
            doSubmitForm('Ypys');
          }
          else{
            document.qsjksbjkswhActionForm.target="_blank";
            doSubmitForm('Ypds');
          }
        }
    }
    </script>
  </body>
</html:html>
