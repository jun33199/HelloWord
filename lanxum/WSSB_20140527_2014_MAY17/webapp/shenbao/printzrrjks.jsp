<%@page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/shenbao.tld" prefix="shenbao"%>

<%@page import="com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant"%>
<%@page import="com.ttsoft.bjtax.shenbao.zhsb.SessionKey"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.client.DeclareInfor"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb"%>
<%@page import="com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx"%>
<%@page import="com.ttsoft.bjtax.shenbao.util.JspUtil"%>
<%@page import="com.ttsoft.framework.util.Currency"%>

<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<html>
<head>
<title>�����еط�˰����걨�ɿ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>

<script language="JavaScript">
function doReturn()
{
	window.close();
//    document.forms[0].actionType.value = "ListJks";
//    document.forms[0].submit();
}
</script>

<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">
<table  width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  <td colspan=2 valign="top">
        <jsp:include page="../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="�鿴�ɿ���" />
        <jsp:param name="help_url" value="help/wssb/qyzhsb/jksmx/jksmxypys-000.htm"/>
        </jsp:include>

        <html:errors/>

<%
        String jksh = request.getParameter("jkshIndex");
        String sbbh = request.getParameter("sbbhIndex");

        HashMap jksMap = (HashMap)request.getSession().getAttribute(SessionKey.JKS);
        HashMap jks = (HashMap)((HashMap)jksMap.get(sbbh)).get(jksh);

        // һƱһ˰
        DeclareInfor declareInfor = (DeclareInfor)jks.get(ZhsbMapConstant.SBSJ);

        Sbjkzb zb = declareInfor.getSbjkzb();
        String skssksrq=JspUtil.format(zb.getSkssksrq()).substring(0,4)+JspUtil.format(zb.getSkssksrq()).substring(5,7)+JspUtil.format(zb.getSkssksrq()).substring(8,10);
        String skssjsrq=JspUtil.format(zb.getSkssjsrq()).substring(0,4)+JspUtil.format(zb.getSkssjsrq()).substring(5,7)+JspUtil.format(zb.getSkssjsrq()).substring(8,10);
        JspUtil.completeZbInfo(request, zb);
        List mxList = declareInfor.getSbjkmxInfo();
%>
 </td>
  </tr>
<tr><td>
<applet name="printer" code="com.ttsoft.bjtax.webprint.SSJKSPagePrinterNew" width="1" height="1" archive="<%=static_contextpath%>printer/vprinter.jar">
</applet>
</td></tr>
  <tr>
    <td valign="middle" align="center">
    <table width="80%" border="2" align="center" cellpadding="0" cellspacing="4" bordercolor="#9DB9D2">
        <tr>
          <td height="50" bgcolor="#F0F0F0">
          <div align="center"><span class="black9"><font size="5" color="#999999">�� �� �� �� �� �� ��<br>˰�սɿ���(���о���ר��)</font>
              </span> </div>
<!--            <p align="center"><font color="#ABC6CD">&nbsp;</font><font color="#d32e2e"><u>&nbsp;&nbsp;
        <%=JspUtil.format(zb.getSzmc())%>&nbsp;&nbsp;</u></font><font color="#2C556D">˰�սɿ���</font></p>
            <p align="center" class="black9"><font color="#999999"></font></p>--> </td>
        </tr>
        <tr>
          <td><table width="100%" cellpadding="4" cellspacing="0" class="black9" id="AutoNumber4" style="border-collapse: collapse">
              <tr>
                <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">˰�����ͣ�</font><font color="#d32e2e"><%=JspUtil.getSklxmc(request,zb.getSklxdm())%></font></td>
                <td width="30%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">���Ա�ţ�</font><font color="#d32e2e"><%=JspUtil.format(zb.getJkpzh())%></font></td>
                <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"></td>
              </tr>
              <tr>
                <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"></td>
                <td width="30%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"></td>
                <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"></td>
              </tr>                        	
              <tr>
                <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">�Ǽ�ע�����ͣ�</font></td>
                <td width="30%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">����ڣ�</font><font color="#d32e2e"><%=JspUtil.format(zb.getSbrq()).substring(0,4)%>��<%=JspUtil.format(zb.getSbrq()).substring(5,7)%>��<%=JspUtil.format(zb.getSbrq()).substring(8,10)%>��</font></td>
                <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">˰����أ�</font><font color="#d32e2e"><%=JspUtil.format(zb.getZsswjgzzjgmc())%></font></td>
              </tr>
            </table>
            <table width="100%" height="102" border="2" cellpaddng="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber3" style="border-collapse: collapse">
              <tr>
                <td width="50" rowspan="2" height="32"><div align="center"><font color="#2C556D">�ɿλ���ˣ�</font></div></td>
                <td width="50" height="16"><div align="center"><font color="#2C556D">ʶ���</font></div></td>
                <td width="300" height="16"><font color="#d32e2e"><%=JspUtil.format(zb.getJsjdm())%></font></td>                	
                <td width="70" height="16"><div align="center"><font color="#2C556D">��������</font></div></td>
                <td width="280" height="16"><font color="#d32e2e"><%=JspUtil.format(zb.getYhmc())%></font></td>
              </tr>
              <tr>
                <td width="50" height="16"><div align="center"><font color="#2C556D">��&nbsp;&nbsp;��</font></div></td>
                <td width="300" height="16"><font color="#d32e2e"><%=JspUtil.format(zb.getNsrmc())%></font></td>                	
                <td width="70" height="16"><div align="center"><font color="#2C556D">��&nbsp;&nbsp;&nbsp;&nbsp;��</font></div></td>
                <td width="280" height="16"><font color="#d32e2e"><%=JspUtil.format(zb.getZh())%></font></td>
              </tr>
              <tr>
                <td width="100" colspan="2" height="16"><div align="center"><font color="#2C556D">�տ����</font></div></td>
                <td width="300" height="16"><font color="#d32e2e">(<%=JspUtil.format(zb.getGkzzjgdm())%>)<%=JspUtil.format(zb.getGkzzjgmc())%></font></td>                	
                <td width="70" height="16"><div align="center"><font color="#2C556D">˰���޽�����</font></div></td>
                <td width="280" height="16"><font color="#d32e2e"><%=JspUtil.format(zb.getXjrq())%></font></td>
              </tr>              
            </table>
            <table width="100%" height="122" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber2" style="border-collapse: collapse">
              <tr>
                <td width="280" colspan="3" style="padding-top: 0" height="16" align="center"><font color="#2C556D">Ԥ���Ŀ</font></td>
                <td width="130" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">ƷĿ����</font></td>
                <td width="46" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">��˰����</font></td>                                              	
                <td width="58" rowspan="2" style="padding-top: 0" height="32" align="center"><p align="center"><font color="#2C556D">��˰������������</font></td>
                <td width="46" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">˰�ʻ�<br>��λ˰��</font></td>
                <td width="102" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">˰������ʱ��</font></td> 
                <td width="42" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">�ѽɻ�۳���</font></td>                 
                <td width="46" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">ʵ�ɽ��</font></td>
              </tr>
              <tr>
                <td width="80" style="padding-top: 0" height="16" align="center"><font color="#2C556D">Ԥ���Ŀ</font></td>
                <td width="160" style="padding-top: 0" height="16" align="center"><font color="#2C556D">����</font></td>
                <td width="80" style="padding-top: 0" height="16" align="center"><font color="#2C556D">����</font></td>
              </tr>              
<%
String pmmc = "";
String kssl = " ";
String jsje = "";
String sjse = "";
/*
* ��������滮���㴦Ҫ�����²�ѯԤ�㼶�����ƣ�Ԥ�㼶����ʾΪ���뼶���м�������
* gaoyh added fcblmc by 20131220
*/
String fcblmc = "";

for (int i=0; i<mxList.size(); i++)
{
    Sbjkmx mx = (Sbjkmx)mxList.get(i);
    JspUtil.completeMxInfo(request, mx);
    
    JspUtil.completeMxFcblmc(request, mx);
    fcblmc = fcblmc + JspUtil.format(mx.getYsjcmc()) +";;";
    
    pmmc = pmmc + JspUtil.format(mx.getSzsmmc()) + ";;";
    jsje = jsje + JspUtil.format(mx.getJsje()) + ";;";
    sjse = sjse + JspUtil.format(mx.getSjse()) + ";;";
%>
              <tr>
                <td width="80" style="padding-top: 0" height="16"><div align="center"><font color="#d32e2e"><%=JspUtil.format(zb.getYskmdm())%></font></div></td>
                <td width="160" style="padding-top: 0" height="16"><div align="center"><font color="#d32e2e"><%=JspUtil.format(zb.getYskmmc())%></font></div></td> 
                <td width="80" style="padding-top: 0" height="16"><div align="center"><font color="#d32e2e"><%=JspUtil.format(mx.getYsjcmc())%></font></div></td>                                                             	
                <td width="130" style="padding-top: 0" height="16"><div align="center"><font color="#d32e2e"><%=JspUtil.format(mx.getSzsmmc())%></font></div></td>
                <td width="46" style="padding-top: 0" height="16"><div align="center"><font color="#d32e2e"><%if(mx.getKssl()!=null) {out.print(mx.getKssl());kssl=kssl+mx.getKssl()+";;";}else{kssl=kssl+" ;;";}%></font></div></td>
                <td width="58" style="padding-top: 0" height="16"><div align="center"><font color="#d32e2e"><%=JspUtil.format(mx.getJsje())%></font></div></td>
                <td width="46" style="padding-top: 0" height="16"><div align="center"><font color="#d32e2e">&nbsp;</font></div></td>                  
                <td width="112" style="padding-top: 0" height="16"><div align="center"><font color="#d32e2e"><%=skssksrq%>-<%=skssjsrq%></font></div></td>                  
                <td width="32" style="padding-top: 0" height="16"><div align="center"><font color="#d32e2e">&nbsp;</font></div></td>                  
                <td width="46" style="padding-top: 0" height="16"><div align="center"><font color="#d32e2e"><%=JspUtil.format(mx.getSjse())%></font></div></td>
              </tr>
<%
}
%>
              <tr>
                <td width="80" style="padding-top: 0" height="15" align="right"><font color="#d32e2e">���ϼ�</font></td>              	
                <td colspan="8" style="padding-top: 0" height="15"><font color="#2C556D">����д����</font><font color="#d32e2e"><%=JspUtil.format(Currency.convert(zb.getSjje()))%></font></td>
                <td width="46" style="padding-top: 0" height="15" align="right"><font color="#d32e2e">��<%=JspUtil.format(zb.getSjje())%></font></td>
              </tr>
            </table>
            <table width="100%" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber5" style="border-collapse: collapse">
              <tr>
                <td width="15%"><div align="center"><font color="#2C556D">˰����أ� </font></div>
                  <p><div align="center"><font color="#d32e2e"><%=JspUtil.format(zb.getSwjgzzjgmc())%></font></div></p>
                  <p><font color="#2C556D">��Ʊ��</font><font color="#d32e2e"><%=JspUtil.format(zb.getNsrmc())%>
                    </font></td>              	
                <td width="15%"><div align="center"><font color="#2C556D">�ɿλ���ˣ��� </font></div>
                  <p><div align="center"><font color="#d32e2e"><%=JspUtil.format(zb.getNsrmc())%></font></div></p>
                  <p><font color="#2C556D">������
                    &nbsp;
                    </font></font></td>
                <td width="30%"><div align="center"><font color="#2C556D">���п��������ײ���ת�տλ�˻�</font></div>
                  <p><div align="center"><font color="#d32e2e">���⣨���У�����</font></div></p>
                  <p><div align="center"><font color="#2C556D">&nbsp;&nbsp;�� &nbsp;&nbsp;�� &nbsp;&nbsp;��</font></div></td>
                <td width="40%"><font color="#2C556D">��ע<br><%=JspUtil.format(zb.getBz())%>
                  </font></td>
              </tr>
            </table></td>
        </tr>
      </table>
<br>
<html:form method="POST" action="/zrrJks.do">
<html:hidden name="zrrJksForm" property="actionType"/>
                  <div align="center">
                    <img src="<%=static_contextpath%>images/dayin1.jpg" onmouseover="this.src='<%=static_contextpath%>images/dayin2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/dayin1.jpg'" alt="��ӡ" onclick="goprint()" style="cursor:hand">
                    &nbsp;&nbsp;&nbsp;&nbsp;
					<img src="<%=static_contextpath%>images/guanbi1.jpg" onmouseover="this.src='<%=static_contextpath%>images/guanbi2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/guanbi1.jpg'" alt="�ر�" onclick="doReturn()" style="cursor:hand">
                  </div>
                  <br>
</html:form>
 </td>
  </tr>
  <tr>
    <td valign="bottom" align="center">
    <%@ include file="../include/bottom.jsp" %>
 </td>
  </tr>
</table>
</body>
<script language="JavaScript">

function goprint(){
//    var szdm = "<%=JspUtil.format(zb.getSzmc())%>"; //˰�ִ���
    var  sklx = "<%=JspUtil.getSklxmc(request,zb.getSklxdm())%>"; //˰������
    var dnbh = "<%=JspUtil.format(zb.getJkpzh())%>"; //���Ա��    
//    var lsgx = "<%=JspUtil.format(zb.getLsgxmc())%>"; //������ϵ
    var zclx = ""; //ע������
//    var tfrq = "<%=JspUtil.format(zb.getSbrq())%>"; //�����    
    var tfrqn = "<%=JspUtil.format(zb.getSbrq()).substring(0,4)%>"; //�������
    var tfrqy = "<%=JspUtil.format(zb.getSbrq()).substring(5,7)%>"; //�������
    var tfrqr = "<%=JspUtil.format(zb.getSbrq()).substring(8,10)%>"; //�������        
    var zsjg = "<%=JspUtil.format(zb.getZsswjgzzjgmc())%>"; //���ջ���

    var jkdwdm = "<%=JspUtil.format(zb.getJsjdm())%>"; //�ɿλ����
//    var jkdwdh = "<%=JspUtil.format(zb.getJydzlxdm())%>"; //�ɿλ�绰
    var jkdwqc = "<%=JspUtil.format(zb.getNsrmc())%>"; //�ɿλȫ��
    var jkdwkhyh = "<%=JspUtil.format(zb.getYhmc())%>"; //�ɿλ��������
    var jkdwzh = "<%=JspUtil.format(zb.getZh())%>"; //�ɿλ�˺�
    var skgk = "(<%=JspUtil.format(zb.getGkzzjgdm())%>)<%=JspUtil.format(zb.getGkzzjgmc())%>"; //�տ����    
    var skxjrq = "<%=JspUtil.format(zb.getXjrq())%>"; //˰���޽�����
    
    
    var yskmbm = "<%=JspUtil.format(zb.getYskmdm())%>"; //Ԥ���Ŀ����
    var yskmmc = "<%=JspUtil.format(zb.getYskmmc())%>"; //Ԥ���Ŀ����
    var yskmjc = "<%=JspUtil.format(zb.getYsjcmc())%>"; //Ԥ���Ŀ����
    var  jkmxpmmc = "<%=pmmc.substring(0,pmmc.length()-2)%>"; //�ɿ���ϸƷĿ����(TBD)
    var  jkmxkssl = "<%=kssl.substring(0,kssl.length()-2)%>"; //�ɿ���ϸ��˰����(TBD)
    var  jkmxjsje = "<%=jsje.substring(0,jsje.length()-2)%>"; //�ɿ���ϸ��˰���(TBD)
    var sksssq = "<%=skssksrq%>-<%=skssjsrq%>"; //˰������ʱ��    
    var  jkmxsjse = "<%=sjse.substring(0,sjse.length()-2)%>"; //�ɿ���ϸʵ��˰��(TBD)

    var jkjehj = "<%=JspUtil.format(Currency.convert(zb.getSjje()))%>"; //�ɿ���ϼ�(TBD)
    var jkjehj_nu = "��<%=JspUtil.format(zb.getSjje())%>";

    var swjg = "<%=JspUtil.format(zb.getSwjgzzjgmc())%>"; //˰�����(TBD)
    var jkdw = "<%=JspUtil.format(zb.getNsrmc())%>"; //�ɿλ(TBD)
    var bz = " <%=JspUtil.format(zb.getBz())%>"; //��ע(TBD)
    
    var jkmxfcbl = "<%=fcblmc.substring(0,fcblmc.length()-2)%>"; //�ɿ���ϸ�ֳɱ���(TBD)




//    document.printer.setszdm(szdm);
    document.printer.setSklx(sklx);    
    document.printer.setdnbh(dnbh);
    document.printer.setzclx(zclx);    
//    document.printer.settfrq(tfrq);
    document.printer.settfrqn(tfrqn);
    document.printer.settfrqy(tfrqy);
    document.printer.settfrqr(tfrqr);
//    document.printer.setlsgx(lsgx);
    document.printer.setzsjg(zsjg);
    document.printer.setjkdwdm(jkdwdm);
//    document.printer.setjkdwdh(jkdwdh);
    document.printer.setjkdwqc(jkdwqc);
    document.printer.setjkdwkhyh(jkdwkhyh);
    document.printer.setjkdwzh(jkdwzh);
    document.printer.setskgk(skgk);    
    document.printer.setskxjrq(skxjrq);    
    document.printer.setyskmbm(yskmbm);
    document.printer.setyskmmc(yskmmc);
    document.printer.setyskmjc(yskmjc);
    document.printer.setjkmxpmmc(jkmxpmmc);
    document.printer.setjkmxkssl(jkmxkssl);
    document.printer.setjkmxjsje(jkmxjsje);
    document.printer.setsksssq(sksssq);
    document.printer.setjkmxsjse(jkmxsjse);
    document.printer.setjkjehj(jkjehj);
    document.printer.setjkjehj_nu(jkjehj_nu);
    document.printer.setswjg(swjg);
    document.printer.setbz(bz);
    
    document.printer.setjkmxfcbl(jkmxfcbl);

    document.printer.startPrint();
}
</script>
</html>
