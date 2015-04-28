<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.ttsoft.bjtax.smsb.wszm.web.WszmCxForm"%>

<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>
<%
    WszmCxForm form = (WszmCxForm)request.getAttribute("wszmCxForm");
%>
<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>ά����˰֤��</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/DTable.js"></script>
<script language=JavaScript src="../js/zhsb.js"></script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  onLoad="fnOnLoad()">
<%@ include file="./include/header.jsp"%>

<html:form action="/webapp/smsb/wszmCxAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="headPzzldm" />
<html:hidden property="headZhdm" />
<html:hidden property="headLrr" />
<html:hidden property="headWszh" />
<html:hidden property="headNdzb" />

<TABLE width="100%" border='0' cellpadding='0' cellspacing='0' align='left' class='black9' height="300"> 
 <tr>
    <td valign="top" height="300"> <br>
      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1"  colspan="2">ά����˰֤��</td>
        </tr>
        <tr>
          <td class="1-td2"  colspan="2"> <br>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
              	<td nowrap class="3-td1-left"><div align="right">��˰֤����&nbsp;</div></td>
              	<td nowrap class="3-td1-left"><div align="left">&nbsp;<html:text property="tempWszh" size="15" onKeyDown="if(event.keyCode==13) event.keyCode=9;" /></div></td>
                <td nowrap class="3-td1-left"><div align="right">����ֱ�&nbsp;</div></td>
                <td nowrap class="3-td1-left"><div align="left">&nbsp;<html:text property="tempNdzb" size="15" onKeyDown="if(event.keyCode==13) event.keyCode=9;" /></div></td>
                <td nowrap class="3-td1-left"><div align="right">��ӡ��ʶ&nbsp;</div></td>
                <td nowrap class="3-td1-centen"><html:select property="tempClbjdm" onKeyDown="if(event.keyCode==13) event.keyCode=9;" ><html:option value="*"> ȫ�� </html:option><html:option value="0"> δ��ӡ </html:option><html:option value="1"> �Ѵ�ӡ </html:option></html:select></td>
              </tr>
              <tr>
              	<td nowrap class="2-td2-left"><div align="right">��ʼ����&nbsp;</div></td>
              	<td nowrap class="2-td2-left"><div align="left">&nbsp;<html:text property="query_qsrq" size="15" onKeyDown="if(event.keyCode==13) event.keyCode=9;" /></div></td>
              	<td nowrap class="2-td2-left"><div align="right">��ֹ����&nbsp;</div></td>
              	<td nowrap class="2-td2-left"><div align="left">&nbsp;<html:text property="query_jzrq" size="15" onKeyDown="if(event.keyCode==13) event.keyCode=9;" /></div></td>
              	<td nowrap class="2-td2-center" colspan="2" ><div align="center"><input type="image" accesskey="q" tabIndex="-1" onClick="befQuery('Query');return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" style="cursor:hand">&nbsp;&nbsp;</div></td>
              </tr>
            </table>
             
            <br>    
              <table id="SzzqwhTable" width="89%"  class="table-99" cellspacing="0">
                <tr>
                  <td class="2-td1-left">��˰֤����</td>
                  <td class="2-td1-left">���</td>
                  <td class="2-td1-left">��˰��ʶ���</td>
                  <td class="2-td1-left">��˰������</td>
                  <td class="2-td1-left">���ϱ��</td>
                  <td class="2-td1-left">��ӡ���</td>
                  <td class="2-td1-left">��������</td>
                  <td colspan="2" class="2-td1-center">����</td>
                </tr>
            <bean:define id="items" name="wszmCxForm" property="dataList"/>
			<%
				double jehj = 0;
				int total = 0;
			%>
            <logic:iterate id="item" name="items" indexId="index">
			<%				
				jehj+=java.lang.Double.parseDouble((String)((java.util.Map)item).get("hjje"));
				total+=1;
			%>
              <tr> 
                  <td class="2-td2-left"><ttsoft:write name="item" property="wszh"/></td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="hjje"/></td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="nsrsbh"/></td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="nsrmc"/></td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="yxflag"/></td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="printflag"/></td>
                  <td class="2-td2-left"><ttsoft:write name="item" property="cjrq"/></td>
                  <td nowrap class="2-td2-left"><div align="center" ><a href="javascript:befDelete('<ttsoft:write name="item" property="wszh"/>','<ttsoft:write name="item" property="ndzb"/>','<ttsoft:write name="item" property="yxbz"/>','<ttsoft:write name="item" property="pzzldm"/>')">�������ϱ��</a></div></td>
                  <td nowrap class="2-td2-center"><div align="center" ><a href="javascript:befUpdate('<ttsoft:write name="item" property="wszh"/>','<ttsoft:write name="item" property="ndzb"/>','<ttsoft:write name="item" property="dybz"/>','<ttsoft:write name="item" property="pzzldm"/>');">���ô�ӡ���</a></div></td>
              </tr>
              
             </logic:iterate>
			 <%
				java.math.BigDecimal temp = new java.math.BigDecimal(jehj);
				temp = temp.setScale(2, java.math.BigDecimal.ROUND_HALF_UP );

			 %>
				<tr> 
                  <td width="12%"  class="2-td2-left">�ϼƣ�<%=total%> �ţ�</td>
                  <td width="12%"  class="2-td2-left"><%=temp%>��Ԫ��</td>
                  <td width="13%" class="2-td2-left">&nbsp;</td>
                  <td width="13%" class="2-td2-left">&nbsp;</td>
                  <td width="10%"  class="2-td2-left">&nbsp;</td>
                  <td width="10%" class="2-td2-left">&nbsp;</td>
                  <td width="10%" class="2-td2-left">&nbsp;</td>
                  <td width="10%" class="2-td2-left">&nbsp;</td>
                  <td width="10%" class="2-td2-center">&nbsp;</td>
              </tr>
             <table border="0" width="100%">
              <tr> 
                <td width="27%"> </td>
                <td width="9%"><input type="image" accesskey="f" tabIndex="-1" onclick="doSubmitForm('Back');return false;" style="cursor:hand"  onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="fh1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" border="0"></td>
                <td width="9%"><input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22"></td>
                <td width="27%"> </td>
              </tr>
            </table>
			<br>
          </td>
        </tr>
      </table>
	      
      <br>
    <!-- </td>
 </tr>
</table> -->

</html:form>


<%@ include file="./include/footer.jsp"%>
	</td>
 </tr>
</table>
</body>
<script language=JavaScript >

function jsjdmQuery()
{
	//if(window.event.keyCode==13){
		//doSubmitForm('Query')
	//}
	if(event.keyCode==13) event.keyCode=9;
}

function befDelete(wsz,nd,flag,pzzldm)
{
	if(flag=="0") 
	{
		document.forms[0].headWszh.value=wsz;
		document.forms[0].headNdzb.value=nd;
		document.forms[0].headPzzldm.value=pzzldm;
		if (confirm("�ò��������ϵ�ǰ��˰֤�����ò������ָܻ���\n�Ƿ������"))
		{
			document.forms[0].actionType.value="Delete";
			document.forms[0].submit();
			return false;
		}
	}
	else{
		alert("����˰֤���Ѿ����ϣ�");		
		//return false;
	}
	
}

function befUpdate(wsz,nd,flag,pzzldm)
{
	if(flag=="0") 
	{
		document.forms[0].headWszh.value=wsz;
		document.forms[0].headNdzb.value=nd;
		document.forms[0].headPzzldm.value=pzzldm;
		if (confirm("�ò�����Ϊ��ǰ��˰֤�������ô�ӡ��ǣ�\n�Ƿ������"))
		{
			document.forms[0].actionType.value="Update";
			document.forms[0].submit();
			return false;
		}
	}
	else{
		alert("����˰֤���Ѿ���ӡ��");		
		//return false;
	}
}

function befQuery(actionType)
{
	if(document.forms[0].tempWszh.value==""
		 && document.forms[0].tempNdzb.value==""
		 && document.forms[0].query_qsrq.value=="" 
		 && document.forms[0].query_jzrq.value=="")
	{
		alert("��������Ӧ�Ĳ�ѯ������");	
	}
	else {
		doSubmitForm(actionType);
		return false;
	}
}

function fnOnLoad()
{
    document.forms[0].tempNdzb.focus();
}

</script>
</html:html>