<%@ page import="java.util.Map" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>��֪����</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="js/treatImage.js"></script>
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="./include/header.jsp"%>
<!-- ������˰ -->
<html:form action="/webapp/smsb/zhsbPgbsGzsxAction.do" method="POST" >
<html:hidden property="actionType" value="Init"/>
<html:hidden property="jsjdm" />
<html:hidden property="sbrq" />
<table width="100%" height="61%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center">
      <form name="apprizeForm" method="POST" action="PG3_SBZS_023.htm">
        <table cellspacing="0" class="table-98">
          <tr> 
            <td align="center" colspan="5" class="1-td1" id="gzsx_title"> <font size="3"><strong>��֪����&nbsp;&nbsp;��������룺</strong></font> 
            </td>
          </tr>
          <tr> 
            <td class="1-td2"><br>
              <table cellspacing="0" class="table-99">
			  <!-- <tr> 
                  <td class="2-td1-left" > ��������</td>
                  <td class="2-td1-center"> ��֪������ϸ��Ϣ</td>
                </tr> -->
              
              <!-- ������˰ -->
			  <bean:define id="items" name="zhsbPgbsGzsxActionForm" property="dataList"/>
				<logic:iterate id="item" name="items">
				<%
					String bz = (String)((Map)item).get("fzcbs");
					//��ʾ������֪����
					if(bz.equals("0")){
				%>               
				
				<tr> 
                  <td class="2-td2-left"><ttsoft:write name="item" property="gzsxfcrq" filter="false"/>&nbsp;</td>
                  <td class="2-td2-center"><ttsoft:write name="item" property="gzsxnr" filter="false"/>&nbsp;</td>
                </tr>
				<%
					}
				%>
				</logic:iterate>
				
				<!-- ������˰ -->
				<logic:equal name="zhsbPgbsGzsxActionForm" property="fzcbs" value="1">
				<tr> 
                  <!-- /* 1��������� */ -->
                  <td COLSPAN="5" bgcolor="#f3f3f3" class="black9"><b><br>
                    <font color='red'><b>���з�������Ϣ,�뵽�й�˰����ؽ�����ش���</b></font></b><br>
                    <!-- �밴����������ť�����걨ҳ�档 </td> -->
                  <!-- /* 2��������������������걨 */ -->
                  <!-- /* 2���������������ҵ״̬���벻�������������걨 */ -->
                  <!-- /* 2���������������ҵδ�Ǽǣ��������걨 */ -->
                </tr>
				<tr> 
                  <td class="2-td1-left" > ��������</td>
                  <td class="2-td1-center"> ��֪������ϸ��Ϣ</td>
                </tr>
				
				<!-- ������˰ -->
				<bean:define id="items" name="zhsbPgbsGzsxActionForm" property="dataList"/>
				<logic:iterate id="item" name="items">
				<%
					String bz = (String)((Map)item).get("fzcbs");
					//��ʾ��������֪����
					if(bz.equals("1")){
				%>               
				
				<tr> 
                  <td class="2-td2-left"><ttsoft:write name="item" property="gzsxfcrq" filter="false"/>&nbsp;</td>
                  <td class="2-td2-center" ><div align="left"><ttsoft:write name="item" property="gzsxnr" filter="false"/>&nbsp;</div></td>
                </tr>
				<%
					}
				%>
				</logic:iterate>
				</logic:equal>
                
                <tr> 
                  <td colspan="5" bgcolor="#f3f3f3"> <div align="center"> 
				  <!-- <img onclick="javascript:doZhsb()"  onMouseOver="MM_swapImage('whjks1','','<%=static_contextpath%>images/jixu2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="ά���ɿ���" id="whjks1" src="<%=static_contextpath%>images/jixu1.jpg" width="85" height="22"> -->
				  <input type="image" accesskey="f" tabIndex="-1" onclick="doZhsb('Back');return false;" style="cursor:hand"  onMouseOver="MM_swapImage('fh1','','<%=static_contextpath%>images/fh-f2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="����" id="fh1" src="<%=static_contextpath%>images/fh-f1.jpg" width="79" height="22" border="0">
                      
                      <br>
                      <br>
                    </div></td>
                </tr>
              </table>
              </td>
          </tr>
          <!--/* ��ø�֪����ϣ���ѭ�������и�֪��������ݶ���ʾ���� */-->
        </table>
        </form>
     </td>
  </tr>
</table>
</html:form>

 <%@ include file="./include/footer.jsp"%>
</body>
<script language="JavaScript">


function addTitle(){
	gzsx_title.innerText="��֪����	��������룺"+document.all.jsjdm.value;	
}
addTitle()


function checkVisitCount()
{
    var val = parseInt(VisitCounter.value);
    if (val > 0)
    {
        window.location = "shenbao/page_guoqi.jsp";
        return true;
    }
    val = val + 1;
    VisitCounter.value = val;
    return true;
}
//��ת���ɿ���ά������
function doZhsb(){
	//����¼����������
	
	document.all.actionType.value='Zhsb';
	document.forms[0].submit();
}

</script>          

</html:html>