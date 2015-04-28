<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<html>
<head>
<title>操作结果</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<script language="javascript">
<%/*退出*/%>
function doExit()
{
	document.forms[0].action = "quit.do";
	document.forms[0].submit();
}
</script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>

    	<jsp:include page="../include/SbHeader.jsp" flush="true" >
    		<jsp:param name="name" value="操作结果" />
    	</jsp:include>


<table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="middle">

      <table align="center" cellspacing="0" class="table-99">
        <tr>
          <td class="1-td1">
                <font color="0000ff">
<%@page import="com.ttsoft.bjtax.shenbao.constant.SuccessConstant"%>
<%
String msg = (String)request.getAttribute(SuccessConstant.SUCCESS_MESSAGE);
if (msg == null)
{
	//msg = "操作成功";
	msg = "&nbsp;<br>";
}
out.print(msg);
%>
                </font>
				

         </td>
       </tr>
       <tr>
		 <td class="1-td2"><html:errors/><br>
	     </td>
       </tr>
         
         <tr>
	       <td class="1-td2"><br>
	            <table>
                   <tr>
                      <td class="1-td1">
                           填写其他申报资料：
                      </td>
                   </tr>
                   <tr>
                      <td class="2-td2">
                         <div align="left">
                            <shenbao:sbzl />
                         </div>
			          </td>
                   </tr>
                   <tr>
                      <td class="2-td2" align="center"><a href="szsm.do">有税申报</a>
                      </td>
                   </tr>
                   <tr>
                      <td class="2-td2" align="center"><a href="wsksb.dc?actionType=Show">无税申报</a>
                      </td>
                   </tr>
        		   <tr>
                      <td align="center">
                        <form action="#" method="post">
							<img src="<%=static_contextpath%>images/tuichu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tuichu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tuichu1.jpg'" alt="退出" onclick="doExit();" style="cursor:hand">
                        </form>
                      </td>
                   </tr>
	            </table>
	        </td>
	      </tr>
	   </table>

    </td>
  </tr>
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