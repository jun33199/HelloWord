<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.common.util.CodeConstants"%>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.model.PKJBSJModel" %>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.model.PLKKDHCXModel" %>
<%@ page import="java.util.*" %>
<html:html>
<head>
<title>����ռ��˰���߽ɿ���</title>
<link href="../../css/text.css" rel="stylesheet" type="text/css">
<link href="../../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../js/treatImage.js"></script>
<script language="JavaScript" src="../../js/smsb_common.js"></script>
<script language="JavaScript" src="../../js/DTable.js"></script>
<script language="JavaScript" src="../../js/InputSelect.js"></script>
<script language="JavaScript" src="../../js/function.js"></script>
<script language="javascript" src="../../js/gmit_selectcontrol.js"></script>
</head>
<SCRIPT LANGUAGE="JavaScript">
	 function doit(ope){
		 
		 if(document.forms[0].nsrmc.value==""
				 &&document.forms[0].jsjdm.value==""
				 &&document.forms[0].pzjdwh.value==""
				 &&document.forms[0].sbbxlh.value==""){
				alert("��ѯ������˰�����ơ���������롢��׼ռ���ĺš��걨���кŲ���ȫΪ�գ�");
				return false;
	     }
		doSubmitForm(ope);
	}
	 function doCkJks(){
		var cxJgTsFlag = "<bean:write name="gdzysCjjksForm" property="cxJgTsFlag" />";
		if(cxJgTsFlag=='1'||(cxJgTsFlag=='0'&&document.gdzysCjjksForm.actionType.value=='Show')){
			alert("��ѡ��Ҫ���ߵĽɿ������ݣ�");
			return false;
		}else{
		 var xuanze=document.getElementsByName("xuanze");
		 for(var i=0;i<xuanze.length;i++){
		   if (xuanze[i].checked)
			{
			    var temp=xuanze[i].value.split("#");
			    document.gdzysCjjksForm.cxSbbxlh.value=temp[0];
			    document.gdzysCjjksForm.flag.value=temp[1];
			    break;
			}
		 }
		 document.gdzysCjjksForm.target="_blank";
		 doSubmitForm("CkJks");
		 document.gdzysCjjksForm.target="";
		} 
	 }
	//����ҳ��ʱ��������Ϊ��ѯ���¼��
  // ҳ����뽹��ȷ��
   function fnOnLoad()
  {

  }
</SCRIPT>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
  <%@ include file="../../include/header.jsp" %> 
<html:form action="/webapp/smsb/gdzys/gdzysCjjksAction.do" method="POST" >
  <html:hidden property="actionType" />
  <html:hidden property="cxSbbxlh" />
  <html:hidden property="flag" />
	<table  width="80%"  align="center" cellspacing="0" >
		<tr>
			<td class="1-td1"  colspan="7">���߽ɿ��� </td>
		</tr>
		<tr>
			<td class="1-td2"  colspan="7">
				<br>
				<table  cellspacing="0" class="table-99">
				    <tr> 
					  <td width="25%" class="2-td2-t-left">��˰������</td>
					  <td width="15%" class="2-td2-t-left">
					  	<div align="left">
					     <html:text property="nsrmc" size="20" maxlength="10"/></FONT>           
            			</div>
					  </td>
					  <td width="25%" class="2-td2-t-left">���������</td>
					  <td width="15%" class="2-td2-t-left">				    
						<div align="left">
              				<html:text property="jsjdm" size="15" />
                      </div>
                       </td>
                       <td rowspan="2" width="20%" class="2-td2-t-center">
                       <div>
                        <input name="I2" type="image" accesskey="q" value="��ѯ"  onclick="doit('Query');return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" align="middle">
                      </div>
                       </td>
					</tr>
					<tr> 
					  <td width="25%" class="2-td2-left">��׼ռ���ĺ�</td>
					  <td width="15%" class="2-td2-left">
					  <div align="left">
					  <html:text property="pzjdwh" size="20"/>
				      </div>
					  </td>
					  <td width="25%" class="2-td2-left">�걨���к�</td>
					  <td width="15%" class="2-td2-left">
					  <div align="left">
						<html:text property="sbbxlh" size="15" />
					  </div>
					  </td>
					</tr>
				</table>
				<br>
	
			<DIV style="FONT-SIZE: 13px; HEIGHT: 15px" 
            align=center><STRONG>��ѯ����б�</STRONG>
            </DIV>
            
            <DIV id="">
            <TABLE class=table-99 id=table_LIST cellSpacing=0 border=0>
              <TBODY>
              <TR>
                <TD class=2-td1-left width="5%">ѡ��</TD>
                <TD class=2-td1-left width="8%">�걨���к�</TD>
                <TD class=2-td1-left width="10%">��˰������</TD>
				<TD class=2-td1-left width="8%">���������</TD>
                <TD class=2-td1-left width="15%">��׼ռ���ĺ�</TD>
                <TD class=2-td1-left width="10%">��˰���</TD>
                <TD class=2-td1-left width="10%">�������</TD>
                <TD class=2-td1-left width="10%">Ӧ��˰��</TD>
                <TD class=2-td1-center width="10%">�걨����</TD>
                </TR>
              <logic:iterate id="jkslist" name="gdzysCjjksForm" property="dataList" indexId="indexid">
                    <bean:define id="item" name="jkslist"/>
                     <tr>
                        <td class="2-td2-left" align="center" nowrap><input type="radio" name="xuanze" value="<ttsoft:write name="jkslist" property="sbbxlh"/>#<ttsoft:write name="jkslist" property="flag"/>" <%=indexid.intValue()==0?"checked":""%>/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="sbbxlh"/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="nsrmc"/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="jsjdm"/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="pzjdwh"/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="jsmj"/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="jmmj"/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="ynse"/></td>
						<%
					    String sfsjsp=(String)((HashMap)item).get("sfsjsp");
					    %>
					    <% 
					    if(sfsjsp.equals("0")){
					    %>
					     <td class="2-td2-center" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="qrrq"/></td>
					    <% 
					    }else{
					    %>
					     <td class="2-td2-center" align="center" nowrap>&nbsp;<ttsoft:write name="jkslist" property="sjqrrq"/></td>
					    <% 
					    }
					    %>
					</tr>
			 </logic:iterate>
			  </TBODY></TABLE>
  
			  </DIV>
			  	<logic:equal name="gdzysCjjksForm" property="cxJgTsFlag" value="1">
			  	 <DIV style="FONT-SIZE: 13px; HEIGHT: 15px" 
                 align=center><FONT SIZE="2" COLOR="#FF0000">û�������ѯ�����Ľ����¼��</STRONG>
                 </DIV>	
				</logic:equal>				
				<br>
				<table width="100%" border="0" cellpadding="0" cellspacing="4">
					<tr valign="bottom">
						<td width="50%" align="right">
							<input name="gdzys_cjjks" type="image" accesskey="s"  onClick="doCkJks();return false;"  onMouseOver="MM_swapImage('gdzys_cjjks','','<%=static_contextpath%>images/gdzys/gdzys_cjjks2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="���߽ɿ���" id="gdzys_cjjks" src="<%=static_contextpath%>images/gdzys/gdzys_cjjks1.jpg" width="100" height="22" >
						</td>
                        <td width="50%" align="left">	
							<input type="image" accesskey="c"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22">
						</td>
                        
                      </tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
 <%@ include file="../../include/footer.jsp" %>
    </td>
  </tr>
</table>
</html:form>
</body>
</html:html>