<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<HTML><HEAD><TITLE>����Ǩ��</TITLE>
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
  if(operationType=="Save" )
  {
    if(document.forms[0].xxzl.value=="" || document.forms[0].qxdm.value==""
       || document.forms[0].blbs.value=="" || document.forms[0].ztbs.value==""
       || document.forms[0].jsfsdm.value=="" )
    {
        alert("������������");
     	document.forms[0].qxdm.focus();
    	return false;
    }
  }else  if(operationType=="Update" )
  {
    if(document.forms[0].xxzl.value=="" || document.forms[0].qxdm.value==""
       || document.forms[0].jsfsdm.value=="" )
    {
        alert("������������");
     	document.forms[0].qxdm.focus();
    	return false;
    }
  }else  if(operationType=="Qyhz" )
  {
    if(document.forms[0].qxdm.value=="" )
    {
        alert("������������");
     	document.forms[0].qxdm.focus();
    	return false;
    }
  }
  /*else  if(operationType=="Return" )
  {
  document.forms[0].submit();
  return true;
  }*/
  //alert("operationType=="+operationType);
  return true;
}
</SCRIPT>



<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="��˰�걨&gt;����Ǩ��"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

				</td>
              </tr>
               </TBODY>
     </TABLE>
     
<TABLE align=center border=0 cellPadding=0 cellSpacing=0  width=100%>
  <TBODY>
  <TR>
    <TD vAlign=top width=100%>
      <TABLE align=center cellSpacing=0 class=table-99 width=100%>
        <TBODY>
        <TR>
          <TD class=1-td1 width=100%>����Ǩ��</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top width=100%>
            <html:form action="/sjqy/sjqy.do">
            <input type = "hidden" name = "operationType" value = "" >
             <TABLE border="0" cellSpacing=0 class=table-89 width=100%>
              <TBODY>
<tr>
                <td class="2-td2-t-left">��Ϣ����</td>
                <td class="2-td2-t-left" >
                    <div align=left>
		  			 <html:select property="xxzl" >
                        <html:option value="0">����</html:option>
                        <html:option value="1">�Ǹ���</html:option>
                        <html:option value="2">����</html:option>
                       </html:select>
					</div>
                </td>
                <td class="2-td2-t-left">��¼��ʶ</td>
                <td class="2-td2-t-left" >
                    <div align=left>
		  			 <html:select property="blbs" >
                        <html:option value="2">С������</html:option>
                        <html:option value="1">��¼</html:option>
                        <html:option value="0">�ǲ�¼</html:option>
                       </html:select>
					</div>
                </td>
                <td class="2-td2-t-left">������¼��</td>
                <td class="2-td2-t-center">
                    <div align=left>
		  			 <html:text property="cc" maxlength="6" tabindex="0"/>
					</div>
                </td>

</tr>
               <tr>
                <td class="2-td2-left">����</td>
                <td class="2-td2-left">
					<div align=left>
		  			     <html:select property="qxdm" >
                        <html:option value="01">����</html:option>
                        <html:option value="05">����</html:option>
                        <html:option value="11">��ƽ</html:option>
                        <html:option value="12">ͨ��</html:option>                        
                       </html:select>
					</div>
				</td>
                <td class="2-td2-left">��˰��ʽ</td>
                <td class="2-td2-left">
                    <div align=left>
                                          <bean:define id="data1" name="sjqyForm" property="jsfsList"  />
                                          <html:select property="jsfsdm" >
                                            <html:options collection="data1" labelProperty="jsfsmc" property="jsfsdm" />
                                          </html:select>
                    
					</div>
                </td>
				<td class="2-td2-left">״̬��ʶ</td>
                <td class="2-td2-center">
					<div align=left>
		  			     <html:select property="ztbs" >
                        <html:option value="1">��ӡ�걨��</html:option>
                        <html:option value="4">��ӡ�˶�֪ͨ��</html:option>
                        <html:option value="5">��ʱ�������ͬ��</html:option>
                        <html:option value="7">�Ѹ���</html:option>
                        <html:option value="99">�����</html:option>
                       </html:select>
					</div>
				</td>
              </tr>
     </TABLE><BR>

      <DIV align=center>
        
        <input type="submit" name="Submit1" value="��ʼǨ��" onclick="doSubmitForm('Save');" >
        
          &nbsp;&nbsp;
        <input type="submit" name="Submit2" value="������˰֤��ר�ýɿ���" onclick="doSubmitForm('Update');" >
          &nbsp;&nbsp;

        <!--input type="submit" name="Submit3" value="Ǩ�ƻ������ɽɿ���" onclick="doSubmitForm('Qyhz');" -->
          &nbsp;&nbsp;
        <input type="submit" name="SubmitTc" value="��  ��" onclick="doSubmitForm('Return');" >
          &nbsp;&nbsp;
      </DIV><BR>
 
 	<table cellspacing="0" class="table-99">
 	  <tr>
		<td><hr class="hr1"></td>
		<td width="140" class="b-black10">
			<div align="center">Ǩ�ƴ�������Ϣ</div></td>
		<td><hr class="hr1"></td>
             </tr>
          </table>
            <logic:notEqual name="sjqyForm" property="qybs" value="0">

      <TABLE border="0" cellSpacing=0 class=table-99>
      <TBODY>
       <TR>       	
            <logic:notEqual name="sjqyForm" property="qybs" value="3">
       	<TD class="2-td1-left" width = "5%" >�걨���</TD>
        <TD class="2-td1-left" width = "10%" >���루֤������/��������룩</TD>
        
        <logic:equal name="sjqyForm" property="qybs" value="1">
        <TD class="2-td1-left" width = "10%" >��˰������</TD>
        <TD class="2-td1-left" width = "10%" >���ͣ�֤������/��˰�����ͣ�</TD>
        <TD class="2-td1-left" width = "20%" >���ء����������ַ</TD>
        </logic:equal>
        
        <logic:equal name="sjqyForm" property="qybs" value="2">
        <TD class="2-td1-left" width = "5%" >���ش���</TD>
        <TD class="2-td1-left" width = "5%" >��˰֤��</TD>
        <TD class="2-td1-left" width = "5%" >ʵ��˰��</TD>
        </logic:equal>

        <TD class="2-td1-left" width = "%5" >������</TD>
          </logic:notEqual>
          
        <logic:equal name="sjqyForm" property="qybs" value="3">
        <TD class="2-td1-left" width = "10%" >�ɿ�����</TD>
        <TD class="2-td1-left" width = "5%" >���ش���</TD>
        <TD class="2-td1-left" width = "10%" >���������</TD>
        <TD class="2-td1-left" width = "20%" >��˰����</TD>
        <TD class="2-td1-left" width = "20%" >ʵ��˰��</TD>
        </logic:equal>
        
        <TD class="2-td1-left" width = "%5" >�������</TD>
        <TD class="2-td1-center" width = "15%" >������Ϣ</TD>
      </TR>
      <logic:iterate id="data" indexId="index" length="length" name="sjqyForm" property="ret"
            type="com.creationstar.bjtax.qsgl.model.bo.SjqyErrBo">
            <tr>
            <logic:notEqual name="sjqyForm" property="qybs" value="3">

              <td class="2-td2-left">                  
                  <bean:write name="data" property="sbbh"/>
              </td>
              <td class="2-td2-left">
                  <bean:write name="data" property="sfzjhm"/></a>
              </td>
              
        <logic:equal name="sjqyForm" property="qybs" value="1">
              <td class="2-td2-left" style="word-break:break-all">
                  <bean:write name="data" property="nsrmc"/></a>
              </td>
              <td class="2-td2-left">
                  <bean:write name="data" property="sfzjlx"/></a>
              </td>
              <td class="2-td2-left" style="word-break:break-all">
                  <bean:write name="data" property="fdcxmdz"/>
              </td>
        </logic:equal>
              
        <logic:equal name="sjqyForm" property="qybs" value="2">
        <TD class="2-td2-left" ><bean:write name="data" property="swjgdm"/></TD>
        <TD class="2-td2-left" ><bean:write name="data" property="wszh"/></TD>
        <TD class="2-td2-left" ><bean:write name="data" property="ynse"/></TD>
        </logic:equal>

              <td class="2-td2-left">
                  <bean:write name="data" property="slr"/>
              </td>
          </logic:notEqual>

        <logic:equal name="sjqyForm" property="qybs" value="3">
        <TD class="2-td2-left"  ><bean:write name="data" property="wszh"/></TD>
         <TD class="2-td2-left" ><bean:write name="data" property="swjgdm"/></TD>
        <TD class="2-td2-left"  ><bean:write name="data" property="sfzjhm"/></TD>
        <TD class="2-td2-left"  ><bean:write name="data" property="sbbh"/></TD>
        <TD class="2-td2-left"  ><bean:write name="data" property="ynse"/></TD>
        </logic:equal>
              
             <td class="2-td2-left">
                  <bean:write name="data" property="errcode"/>
              </td>
              <td class="2-td2-center">
                  <bean:write name="data" property="errmsg"/>
              </td>
            </tr>
      </logic:iterate>
          </table>
          
          </logic:notEqual>
          
          <br>
 
 
      
</html:form>

  <%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>

