<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import="com.ttsoft.bjtax.shenbao.taglib.SbzlTag"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.FriendHelper"%>

<html>
<head>
<title>��֪����</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<script language=JavaScript>
<%/*����*/%>
function doReturn()
{
	document.forms[0].action = "quit.do";
	document.forms[0].submit();
}

function doNext()
{
	document.forms[0].action = "szsm.do";
	document.forms[0].submit();
}

function doWsk()
{
	document.forms[0].action = "wsksb.dc?actionType=BeforeShow";	
	document.forms[0].submit();
}
function fkEdit(value1,value2,value3){
//alert('<bean:write name="gzsxForm" property="jsjdm"/>');
   // alert(value1+"-----jsjdm");
  //  alert(value2+"-----gzsx_id");
  //  alert(value3+"-----nsrmc");
   // document.forms[0].jsjdm=value1;
    //document.forms[0].gzsx_id=value2;
   // document.forms[0].nsrmc=value3;
    document.forms[0].action = "gzsxfk.do?actionType=Show&jsjdm="+value1+"&gzsx_id="+value2+"&nsrmc="+value3;
	document.forms[0].submit();
}


function initButton()
{
	var yssbObj = document.getElementById("yssb");
	var wssbObj = document.getElementById("wssb");
    var jmsbObj = document.getElementById("jmsb");
    
	if(!window.confirm("�����м���˰��Ŀ��������ȷ�����������˰�걨������û�м���˰��Ŀ��������ȡ����������˰�걨����˰�걨��"))
	{
		yssbObj.style.display = "block";
    	wssbObj.style.display = "block";
    	jmsbObj.style.display = "none";
	}
    else
    {
        wssbObj.style.display = "none";
        yssbObj.style.display = "none";
        <%
    		String cur = String.valueOf(System.currentTimeMillis());
            String jsjdm = FriendHelper.getUserData((HttpServletRequest)pageContext.getRequest()).yhid;
    		out.println("document.forms[0].action = \"jms.dc?actionType=Show&identifyCode=" + cur + jsjdm+"\";");
            out.println("document.forms[0].submit();");
    	%>
    }  
}

function doHdswdjxx() {
	var w = window.open ('nsrjcxxhd.dc?actionType=Init');
	w.moveTo(0,0);
	w.resizeTo(screen.width, window.screen.availHeight); 
}
</script>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">

<table width="97%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>

    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="��֪����" />
		<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>

    	<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td valign="top">
	     <br>
	      <html:errors/>
	     <table  align="center" cellspacing="0" class="table-99">
		<!-- write your code here--->
	        <tr>
	          <td class="1-td1">��֪����</td>
	        </tr>
                <tr>
	          <td class="1-td1">���������:<bean:write name="gzsxForm" property="jsjdm"/></td>
                </tr>
	        <tr>
	          <td class="1-td2"><br>
<html:form action="gzsxfk.do">
<input type="hidden" name="jsjdm" value="">
<input type="hidden" name="gzsx_id" value="">
<input type="hidden" name="nsrmc" value="">
<TABLE border=0 cellPadding=0 cellSpacing=0 width="98%">
	<TBODY>
        <TR>
        	<TD class=2-td1-left width="20%">��֪��������</TD>
            <TD class=2-td1-left width="70%">��֪�������</TD>
            <TD class=2-td1-center width="10%">����</TD>
<logic:iterate id="zcGzsx" name="gzsxForm" property="zcGzList">
        <TR>
            <TD class=2-td2-left><shenbao:write name="zcGzsx" property="gzsxfcrq"/></TD>
            <TD class=2-td2-left><a href=gzsx.do?actionType=Cknr&gzsxnr=<shenbao:write name="zcGzsx" property="gzsxnr" /> target=_blank ><shenbao:write name="zcGzsx" property="gzsxnrbt" /></a>&nbsp</TD>
            <TD  class="2-td2-center"><a href="#" onClick="fkEdit('<bean:write name="gzsxForm" property="jsjdm"/>','<bean:write name="zcGzsx" property="gzsx_id"/>','<bean:write name="gzsxForm" property="nsrmc"/>')" >����</a>
            </TD>
	</TR>
</logic:iterate>
	</TBODY>
</TABLE><BR>


      <logic:equal name="gzsxForm" property="hasFzcInfo" value="1">
        <font color='red'><b>���з�������Ϣ,������������˰������ϵ��</b></font>
        <TABLE border=0 cellPadding=0 cellSpacing=0 width="98%">
                <TBODY>
                <TR>
                        <TD class=2-td1-left width="20%">��֪��������</TD>
                        <TD class=2-td1-left width="70%">��֪�������</TD>
                        <TD class=2-td1-center width="10%">����</TD>
        <logic:iterate id="fzcGzsx" name="gzsxForm" property="fzcGzList">
                <TR>
                        <TD class=2-td2-left><shenbao:write name="fzcGzsx" property="gzsxfcrq"/></TD>
                        <TD class=2-td2-left><a href=gzsx.do?actionType=Cknr&gzsxnr=<shenbao:write name="fzcGzsx" property="gzsxnr" /> target=_blank ><shenbao:write name="fzcGzsx" property="gzsxnrbt" /></a>&nbsp</TD>
                        <td  class="2-td2-center"><a href="#" onClick="fkEdit('<bean:write name="gzsxForm" property="jsjdm"/>','<bean:write name="fzcGzsx" property="gzsx_id"/>','<bean:write name="gzsxForm" property="nsrmc"/>')" >����</a>
            </td>
            
                </TR>
        </logic:iterate>
                </TBODY>
        </TABLE><BR>

      </logic:equal>

<TABLE border=0 cellPadding=0 cellSpacing=0 width="98%" height="22px">
        <TBODY>
        	<TR height="22px">
        	<TD width="10%">&nbsp;</TD>
        	<TD width="10%">&nbsp;</TD>
			<TD >
				<DIV align="center" >
				<table >
				<tr>
					<td>
						<img id="jmsb" src="<%=static_contextpath%>images/jmssb1.jpg" onmouseover="this.src='<%=static_contextpath%>images/jmssb2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/jmssb1.jpg'" alt="����˰�걨" onclick="initButton();" style="border:0;cursor:hand">
					</td>
					<td id="yssb" style="display:none">
						&nbsp;
						<img src="<%=static_contextpath%>images/yssb1.jpg" onmouseover="this.src='<%=static_contextpath%>images/yssb2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/yssb1.jpg'" alt="��˰�걨" onclick="doNext();" style="border:0;cursor:hand">
					</td>
					<td id="wssb" style="display:none">
						&nbsp;
						<img src="<%=static_contextpath%>images/wssb1.jpg" onmouseover="this.src='<%=static_contextpath%>images/wssb2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/wssb1.jpg'" alt="��˰�걨" onclick="doWsk();" style="border:0;cursor:hand">
					</td>
					<td>
						&nbsp;
						<img id="hddjxx" src="<%=static_contextpath%>images/hdswdjxx1.jpg" onmouseover="this.src='<%=static_contextpath%>images/hdswdjxx2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/hdswdjxx1.jpg'" alt="�˶�˰��Ǽ���Ϣ" onclick="doHdswdjxx();" style="border:0;cursor:hand">					
					</td>
					<TD> 
						&nbsp;
			  			<img src="<%=static_contextpath%>images/tuichu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tuichu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tuichu1.jpg'" alt="�˳�" onclick="doReturn();" style="cursor:hand">
					</TD>	
				</tr>
				</table> 
				</DIV>
			</TD>

			<TD width="10%">&nbsp;</TD>
			<TD width="10%">&nbsp;</TD>
		</TR>
	</TBODY>
</TABLE>
</html:form>
<table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">
       <tr>
          <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
          <td width="20%" align="center" class="black9"><strong><font color="#0000FF">ע������</font></strong></td>
           <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
        </tr>
      </table>
      <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
         <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
            <td height="47"  >
              <p style ="color:red">
                  &nbsp;&nbsp;&nbsp;&nbsp;1.������˰�걨����˰�걨ǰ�����ȵ��������˰�걨����ť��<br>
                  &nbsp;&nbsp;&nbsp;&nbsp;2.Ϊ������˰�˰�˰�������˰����ˮƽ������������˶�˰��Ǽ���Ϣ����ť���˶�˰��Ǽ���Ϣ�����б仯���뼰ʱ�����<br>
               </p>
             </td>
          </tr>
      </table>
	  <br>
	          </td>
	         </tr>
	     </table>
	    </td>
	  </tr>
    	</table>
<br>
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