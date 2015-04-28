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
<title>操作结果</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<script language="javascript">

<%/*退出*/%>
function doExit()
{
    document.forms[0].action = "/shenbao/quit.dc?actionType=Quit";
	document.forms[0].target = "_self";
    document.forms[0].submit();
}
function doReturn()
{
    document.forms[0].action = "<%=request.getAttribute("REQ_KEY_RETURN_ADDRESS")%>";
	document.forms[0].target = "_self";
    document.forms[0].submit();
}
function doDownload(lsh)
{
	document.forms[0].action = '/redirect/qmyj/dzyjsjmx.dc?actionType=Download&lsh=' + lsh;
	document.forms[0].target = "_self";
    document.forms[0].submit();
}
</script>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">


<form action="#" method="post">
<input type="hidden" name="menu_url"  value='<%=session.getAttribute("menu_url")%>'>
<input type="hidden" name="login_url" value='<%=session.getAttribute("login_url")%>'>
<input type="hidden" name="jdid"      value='<%=session.getAttribute("jdid")%>'>


<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" colspan=2>
      <jsp:include page="../include/SbHeader.jsp" flush="true" >
        <jsp:param name="name" value="操作结果" />
      </jsp:include>
      <table width="100%" height="61%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td  class="1-td2" valign="middle">

     <table align="center" cellspacing="0" class="table-99">

			  <%
        if (request.getAttribute("REQ_SHENBAO_HUIZHI_KEY") != null)
        {
          Object obj = request.getAttribute("REQ_SHENBAO_HUIZHI_KEY");
          int showDown = 2;//0:无下载；  1：string  2：list
          if (obj instanceof java.lang.String)
          {
            if (((String)obj).equals(""))
            {
              showDown = 0;
            }
            else
            {
              showDown = 1;
            }
          }
          if (showDown <= 0)
          {
			  %>
              <tr>
                <td class="1-td1"> <font color="0000ff"><%=request.getAttribute("REQ_KEY_SUCCESS_MSG")%>

				</font>
                </td>
              </tr>

	<%
          } else
          {
			  %>

              <tr>
                <td class="1-td1"> <font color="0000ff"><%=request.getAttribute("REQ_KEY_SUCCESS_MSG")%>
				 <br>
			请您下载此“电子申报回执”，并保存为.txt文件。<br>
            征期过后三个月，请点击登录界面的“下载已归档的电子申报回执”。
				</font>
                </td>
              </tr>


			  <tr>
                <td class="1-td2"><br>
                  <table align="center" cellspacing="0" class="table-99" >
                    <tr class="black9">
                      <th class="2-td1-left">
                        电子原件编号
                      </th>
                      <th class="2-td1-qd">
                        下载回执
                      </th>
                    </tr>
			  <%
          }
          switch (showDown)
          {
            case 1:
            {
			  %>
                    <tr>
                      <td class="2-td2-left">
                        <%=request.getAttribute("REQ_SHENBAO_HUIZHI_KEY")%>
                      </td>
                      <td class="2-td2-center">
                        <a href='#' onclick='doDownload("<%=request.getAttribute("REQ_SHENBAO_HUIZHI_KEY")%>");'>下载 </a>
                      </td>
                    </tr>
			 <%
               break;
            }
            case 2:
            {
              List hzlist = (List)obj;
              String hzbh = "";
              for (int i = 0;i < hzlist.size();i++)
              {
                hzbh = (String)hzlist.get(i);
       %>
                    <tr>
                      <td class="2-td2-left">
                        <%=hzbh%>
                      </td>
                      <td class="2-td2-center">
                        <a href='#' onclick='doDownload("<%=hzbh%>");'>下载 </a>
                      </td>
                    </tr>
			 <%
              }
               break;
            }
          }
			 %>
                  </table>
				          <br>
                </td>
              </tr>
			 <%
			 }
			 %>

              <tr>
                <td class="1-td2" align="center">
				        <br>
                  <table  align="center" cellspacing="0" class="table-99" >
                    <tr>
                      <td width="20%">
                        &nbsp;
                      </td>
                      <td>
                        <img style="cursor:hand" onclick="doReturn()" src="<%=static_contextpath%>images/fanhui1.jpg"
                        onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'"
                        onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'"
                        width="79" height="22" id="fanhui" alt="返回" />
                      </td>
                      <td>
                        <img style="cursor:hand" onclick="doExit()"   src="<%=static_contextpath%>images/tuichu1.jpg"
                        onmouseover="this.src='<%=static_contextpath%>images/tuichu2.jpg'"
                        onmouseout="this.src='<%=static_contextpath%>images/tuichu1.jpg'"
                        width="79" height="22" id="tuichu" alt="退出" />
                      </td>
                      <td width="20%">
                        &nbsp;
                      </td>
                    </tr>
                  </table>

      <!--table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
              <tr>
                <td width="40%"> <hr width="100%" size="1" class="hr1" >
                </td>
                <td width="20%" align="center" class="black9"><strong><font color="#0000FF">提示信息</font></strong></td>
                <td width="40%"> <hr width="100%" size="1" class="hr1" >
                </td>
              </tr>
            </table>
            <table width="98%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
              <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                <td>&nbsp;&nbsp;&nbsp;&nbsp;1.请将回执文件保存为txt文件。<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;2.回执文件请在3个月内下载。
              </tr>
            </table-->


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
</form>
</body>
</html>
