<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/dc-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>
<%@page import = "java.util.Map"%>
<%@page import = "java.util.Iterator"%>
<%@page import = "java.util.List"%>
<%@page import = "com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import = "com.ttsoft.bjtax.shenbao.zhsb.web.ZhsbForm"%>
<%@page import = "com.ttsoft.bjtax.shenbao.constant.CodeConstant"%>
<%@page import = "com.ttsoft.bjtax.dj.model.YHZH"%>
<%@page import = "com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb"%>
<%@page import = "com.ttsoft.bjtax.shenbao.model.client.SbjkmxData"%>
<%@page import = "com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.TinyTools" %>
<%
String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<%@ include file="zhsbFunc.jsp" %>

<html>
<head>
<title>���걨����</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/zhsb.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/BJCASecurityWare.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/XmlBuild.js"></script>
<script language="JavaScript" type="text/JavaScript">
//started added by qianchao 2006-2-7
<%
String containerName = "";

com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData)session.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
ZhsbForm form = (ZhsbForm)session.getAttribute("ZHSBFORM");
List ysbList = form.getByysbList();
if (userdata.getCaflag())
{
    containerName = userdata.getCert().getContainerName();
}
else
{
    containerName = "";
}
%>

function doShow()
{
    document.forms[0].actionType.value = "Show";
    document.forms[0].submit();
}
function doReturn()
{
    document.forms[0].actionType.value = "Return";
    document.forms[0].submit();
}
</script>

<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
    <table width="97%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td align="center" valign="top">
                <jsp:include page="../include/SbHeader.jsp" flush="true" >
                <jsp:param name="name" value="���걨����" />
                </jsp:include>
            </td>
        </tr>
        <tr>
            <td><html:errors/><br></td>
        </tr>
        <!-- self-->
        <tr>
            <td>
                <form name="form" method="POST" action="/shenbao/zhsbWithoutSfxy.dc">
                    <input name="actionType" type="hidden">
                    <table width="100%"  border="0" align="center" cellspacing="0" bgcolor="#FFFFFF" class="table-99">
                        <tr>
                            <td class="1-td1" colspan="2">���������걨������£�</td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="82%" rowspan="2" valign="top" align="center" class="1-td2">
                                <table cellspacing=0 border=0 class="table-99" id="TABLE_LIST">
                                    <tr>
                                        <td width="4%" class="2-td1-left">���</td>
                                        <td width="12%" class="2-td1-left">�걨ʱ��</td>
                                        <td width="9%" class="2-td1-left">�걨���</td>
                                        <td width="8%" class="2-td1-left">���</td>
                                        <td width="12%" class="2-td1-center">״̬</td>
                                    </tr>
                                    <%
                                    for (int i=0;i<ysbList.size() ; i++) {
                                      Sbjkzb zb = (Sbjkzb)ysbList.get(i);
                                      String bz = zb.getBz();
                                      if("1".equals(zb.getBz())){
                                          %>
                                    <tr id=ROW_LIST>
                                        <td class="2-td2-left"><font color="red"><%=i+1%></font></td>
                                        <td class="2-td2-left"><font color="red"><%=TinyTools.Date2String(zb.getCjrq(),"yyyy-MM-dd HH:mm:ss")%></font></td>
                                        <td class="2-td2-left"><font color="red"><%=zb.getSbbh()%></font></td>
                                        <td class="2-td2-left"><font color="red"><%=zb.getSjje()%></font></td>
                                        <td class="2-td2-center">
                                            <font color="red">
                                            <%
                                            String zwbs = zb.getZwbs();
                                            String last = zwbs.substring(zwbs.length()-1);
                                            //Ϊ'0'='δ�ɿ�'��Ϊ'2'='�ѽɿ�'��Ϊ'9'='�ɿ���'
                                            if("0".equals(last))
                                            {
                                                out.print("δ�ɿ�");
                                            }else if("2".equals(last)){
                                                out.print("�ѽɿ�");
                                            }else if("9".equals(last)){
                                                out.print("������");
                                            }else{
                                                out.print(last);
                                            }
                                            %>
                                         </font>
                                        </td>
                                    </tr>
                                          <%
                                          }else{
                                          %>
                                    <tr id=ROW_LIST>
                                        <td class="2-td2-left"><%=i+1%></td>
                                        <td class="2-td2-left"><%=TinyTools.Date2String(zb.getCjrq(),"yyyy-MM-dd HH:mm:ss")%></td>
                                        <td class="2-td2-left"><%=zb.getSbbh()%></td>
                                        <td class="2-td2-left"><%=zb.getSjje()%></td>
                                        <td class="2-td2-center">
                                            <%
                                            String zwbs = zb.getZwbs();
                                            String last = zwbs.substring(zwbs.length()-1);
                                            //Ϊ'0'='δ�ɿ�'��Ϊ'2'='�ѽɿ�'��Ϊ'9'='�ɿ���'
                                            if("0".equals(last))
                                            {
                                                out.print("δ�ɿ�");
                                            }else if("2".equals(last)){
                                                out.print("�ѽɿ�");
                                            }else if("9".equals(last)){
                                                out.print("������");
                                            }else{
                                                out.print(last);
                                            }
                                            %>
                                        </td>
                                    </tr>
                                          <%
                                          }
                                    }
                                    %>
                                </table>
                                <br>
                                <table cellspacing="0" class="table-99">
                                    <tr class="black9">
                                        <td align="center" nowrap="nowrap"><div align="center">
                                            <img src="<%=static_contextpath%>images/jixu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/jixu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/jixu1.jpg'" alt="����" onclick="doShow();" style="cursor:hand">
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                            <img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="doReturn()" style="cursor:hand">
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </form>
            </td>
        </tr>
        <tr>
            <td align="center">
            <table width="99%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
              <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
                <td height="47"  >
                <p>
                    &nbsp;&nbsp;&nbsp;&nbsp;1.������˶��Ѿ��걨�����������<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;2.״̬Ϊ�������С��������ô��걨�޷�ȷ�������Ƿ��Ѿ���ת˰�������������ϵ���˶��ʺ���<br>
                    &nbsp;&nbsp;&nbsp;&nbsp;3.��ʾΪ��ɫ���걨���ݣ�Ϊ�����30�����ڽ��е��걨������ע�Ⲣ�����ʵ�������ظ��걨 </p>
                  </tr>
            </table>
            </td>
        </tr>
        <!--self end-->
        <tr>
            <td valign="bottom" align="center">
                <%@ include file="../include/bottom.jsp" %>
            </td>
        </tr>
    </table>
</body>
</html>
