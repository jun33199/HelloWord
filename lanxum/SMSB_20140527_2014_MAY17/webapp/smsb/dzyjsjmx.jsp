<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator"%>

<html:html>
<%
    response.setHeader("pragma", "no-cache");
    response.setHeader("Cache-control", "no-cache, no-store");
    response.setHeader("Expires", "0");
	//��̬�ļ�Ŀ¼
	String static_contextpath = SfResourceLocator.getContextPath(request);

    Map sjmap = (HashMap)request.getAttribute("CA_DZQMYJSJ");
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>����ԭ��������ϸ</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<style type="text/css">
.inputstype {
background-color:#ECF2F4;
	font-size: 9pt;
	text-align: center;
	border: 0px;
	color: #000000;
}
</style>

<script language="JavaScript" src="../js/treatImage.js"></script>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<br>
<br>
<br>
<br>
<table width="96%" align="center" cellspacing="0" class="table-99">
  <tr>
    <td class="1-td1">����ԭ��������ϸ</td>
  </tr>
  <tr>
    <td class="1-td2">
      <br>
      <table width="94%" border="0" cellpadding="0" cellspacing="0" class="table-99">
        <tr class="black9"> 
          <td class="2-td1-left" width="9%">ԭ�����к�</td>
          <td class="2-td1-left" width="9%">���������</td>
          <td class="2-td1-left" width="9%">֤�����к�</td>
          <td class="2-td1-left" >ҵ������</td>
          <td class="2-td1-left" width="5%">ҵ�����</td>
          <td class="2-td1-left" >ҵ�����ֵ</td>
          <td class="2-td1-center" width="15%">ԭ���γ�ʱ��</td>
        </tr>
        <tr>
          <td class="2-td2-left" ><%=sjmap.get("lsh")%></td>
          <td class="2-td2-left" ><%=sjmap.get("jsjdm")%></td>
          <td class="2-td2-left" ><%=sjmap.get("zsxlh")%></td>
          <td class="2-td2-left" ><%=sjmap.get("ywlx")%></td>
          <td class="2-td2-left" ><%=sjmap.get("czlx")%></td>
          <td class="2-td2-left" ><strong><%=sjmap.get("ywuid")%></strong></td>
          <td class="2-td2-center" ><%=sjmap.get("jssj")%></td>
        </tr>
      </table>
      <br>
      <table width="94%" border="0" cellpadding="0" cellspacing="0" class="table-99">
        <tr class="black9"> 
          <td class="2-td1-center" >ԭ������</td>
        </tr>
        <tr>
          <td class="2-td2-center" >
            <textarea name="test" cols='120' rows="15" readonly="readonly" wrap="virtual" class="inputstype">
              <%=sjmap.get("yjsj")%>
            </textarea>
          </td>
        </tr>
      </table>
      <br>
      <table width="94%" border="0" cellpadding="0" cellspacing="0" class="table-99">
        <tr class="black9"> 
          <td class="2-td1-center" >ǩ��ֵ</td>
        </tr>
        <tr>
          <td class="2-td2-center" >
            <textarea name="test" cols='120' rows="3" readonly="readonly" wrap="virtual" class="inputstype">
              <%=sjmap.get("qmsj")%>
            </textarea>
          </td>
        </tr>
      </table>
      <br>
      <table width="94%" border="0" cellpadding="0" cellspacing="4">
        <tr valign="bottom"> 
          <td width="25%">&nbsp;</td>
          <td align="center"><input type="image" accesskey="c" tabIndex="-1"   onClick="window.close()"  onMouseOver="MM_swapImage('gb1','','<%=static_contextpath%>images/guanbi1.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�ر�" id="tc1" src="<%=static_contextpath%>images/guanbi2.jpg" width="79" height="22"></td>
          <td width="25%">&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
  
</table>
</body>
</html:html>
