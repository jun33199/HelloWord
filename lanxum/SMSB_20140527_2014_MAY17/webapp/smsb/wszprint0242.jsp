<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=gb2312" %>

<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<head>  
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<title>�����еط�˰������ֽɿ����ӡԤ��</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="goprint()">
<%@ include file="./include/header.jsp"%>   
    
<!--the print archive
<applet name="Vprinter" code="com.ttsoft.bjtax.webprint.SSTYWSZPagePrinter" width="1" height="1" archive="vprinter.jar">
</applet>-->

<applet name="Vprinter" code="com.ttsoft.bjtax.webprint.SSTYWSZPageNewPrinter" width="1" height="1" archive="<%=static_contextpath%>printer/vprinter.jar">
</applet>

    <table width="80%" border="2" align="center" cellpadding="0" cellspacing="4" bordercolor="#9DB9D2">
        <tr>
          <td height="147" bgcolor="#F0F0F0">
          <div align="center">
        <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="28%" align="right">&nbsp;</td>
            <td width="41%" align="right"> <div align="center"><span class="black9"><font size="5" color="#999999">�л����񹲺͹�</font> 
                </span> </div>
              <p align="center"><font color="#2C556D">&nbsp; 
                ˰�սɿ���(˰������ר��)</font></p></td>
            <td width="31%" valign="baseline"><p>&nbsp;</p>
              <p><font color="#2C556D" size="">(<bean:write name="wszPrintForm" property="ndzb" />)������<font color="#d32e2e"><bean:write name="wszPrintForm" property="headWszh" /></font></font></p>
              <p>&nbsp;</p></td>
          </tr>
        </table>
      </div>
            
      <p align="center" class="black9"><font color="#999999"></font></p></td>
        </tr>
        <tr>
          <td><table width="100%" cellpadding="4" cellspacing="0" class="black9" id="AutoNumber4" style="border-collapse: collapse">
        <tr> 
          <td width="33%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">&nbsp;&nbsp;�Ǽ�ע�����ͣ�</font><font color="#d32e2e"><bean:write name="wszPrintForm" property="zclx" /></font></td>
          <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">����ڣ�</font><font color="#d32e2e"><bean:write name="wszPrintForm" property="tfrq" /></font></td>
          <td width="32%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">˰����أ�</font><font color="#d32e2e"><bean:write name="wszPrintForm" property="zsjg" /></font></td>
        </tr>
      </table>
            
      <table width="100%" height="97" border="2" cellpaddng="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber3" style="border-collapse: collapse">
        <tr> 
          <td width="90" height="45" align="center" nowrap>��˰��ʶ���</td>
          <td width="313" height="45"><font color="#d32e2e"><bean:write name="wszPrintForm" property="nsrjsjdm" /></font></td>
          <td width="87" height="45" align="center" nowrap><font color="#2C556D">��˰������</font></td>
          <td width="252" height="45"><font color="#d32e2e"><bean:write name="wszPrintForm" property="nsrmc" /></font></td>
        </tr>
        <tr> 
          <td height="48" align="center">��ַ</td>
          <td height="48" colspan="3"><font color="#d32e2e"><bean:write name="wszPrintForm" property="dz" /></font></td>
        </tr>
      </table>
      <table width="100%" height="305" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber2" style="border-collapse: collapse">
        <tr> 
          <td width="100" style="padding-top: 0" align="center" nowrap>˰��</td>
          <td width="126" style="padding-top: 0" align="center" nowrap>ƷĿ����</td>
          <td width="80" style="padding-top: 0" align="center">��˰����</td>
          <td width="114" style="padding-top: 0" height="34" align="center">��˰������������</td>
          <td width="99" style="padding-top: 0" height="34" align="center">˰�ʻ�λ˰��</td>
          <td width="119" style="padding-top: 0" height="34" align="center">˰������ʱ��</td>
          <td width="102" style="padding-top: 0" height="34" align="center"> <p align="center">�ѽɻ�۳���</td>
          <td width="76" style="padding-top: 0" height="34" align="center"><font color="#2C556D">ʵ��˰��</font></td>
        </tr>
        <!--The loop begin-->
        <bean:define id="jkslist" name="wszPrintForm" property="dataList"/>
        <logic:iterate indexId="index" name="jkslist" id="itemMap">
        <bean:define id="item" name="itemMap"/>
        <tr>
          <td style="padding-top: 0"><font color="#d32e2e"><ttsoft:write name="item" property="szmc"/></font></td>
          <td style="padding-top: 0"><font color="#d32e2e"><ttsoft:write name="item" property="szsmmc"/></font></td>
          <td style="padding-top: 0"><font color="#d32e2e"><ttsoft:write name="item" property="kssl"/></font></td>
          <td style="padding-top: 0" ><font color="#d32e2e"><ttsoft:write name="item" property="jsje"/></font></td>
          <td style="padding-top: 0" ><font color="#d32e2e"><ttsoft:write name="item" property="sl"/></font></td>
          <td style="padding-top: 0" ><font color="#d32e2e"><ttsoft:write name="item" property="skssksrq" />-<ttsoft:write name="item" property="skssjsrq" /></font></td>
          <td style="padding-top: 0" ><font color="#d32e2e"><ttsoft:write name="item" property="yjhkc"/></font></td>
          <td style="padding-top: 0" ><font color="#d32e2e"><ttsoft:write name="item" property="sjse"/></font></td>
        </tr>
		</logic:iterate>
        <!--The end of loop-->
        
        <tr> 
          <td width="80" style="padding-top: 0" height="15"><font color="#2C556D">���ϼ�</font></td>
          <td colspan="6" style="padding-top: 0" height="15"><font color="#2C556D">����д��</font><font color="#d32e2e"><bean:write name="wszPrintForm" property="hjjedx" /></font></td>
          <td width="46" style="padding-top: 0" height="15"><div align="left"><font color="#d32e2e">��<bean:write name="wszPrintForm" property="hjje" /></font></div></td>
        </tr>
      </table>
            
      <table width="100%" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber5" style="border-collapse: collapse">
        <tr> 
          <td width="15%" height="103"><font color="#2C556D">˰����أ� </font> 
            <p><font color="#d32e2e"><bean:write name="wszPrintForm" property="dfswjg" /></font>
            <p></td>
          <td width="15%"><font color="#2C556D">������λ�� </font> 
            <p><font color="#d32e2e"><bean:write name="wszPrintForm" property="wtdzdw" /></font>
            <p></td>
          <td width="15%"><font color="#2C556D">��Ʊ�ˣ�</font> 
          	<p><font color="#d32e2e"><bean:write name="wszPrintForm" property="tpr" /></font>
          	<p></td>
          <td width="55%" ><font color="#2C556D">��ע��</font>
            <p><font color="#d32e2e"><bean:write name="wszPrintForm" property="bz" /></font>
            <p>&nbsp;</td>
        </tr>
      </table></td>
        </tr>
      </table>
<br>

<html:form action="/webapp/smsb/wszPrintAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="fromPage" />
<html:hidden property="jsjdm" />
<html:hidden property="headWszh" />
<html:hidden property="zhdm" />
<html:hidden property="pzzldm" />
<html:hidden property="swjgzzjgdm" />
<html:hidden property="swjgzzjgmc" />
<html:hidden property="ndzb" />

                  <div align="center">
					<input type="image" accesskey="p" tabIndex="-1" src="<%=static_contextpath%>images/dy-p1.jpg" onmouseover="this.src='<%=static_contextpath%>images/dy-p2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/dy-p1.jpg'" alt="��ӡ" onclick="goprint();return false;" style="cursor:hand">
                    &nbsp;&nbsp;&nbsp;&nbsp;
					<input type="image" accesskey="f" tabIndex="-1" src="<%=static_contextpath%>images/fh-f1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fh-f2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fh-f1.jpg'" alt="����" onclick="doSubmitForm('<bean:write name="wszPrintForm" property="fromPage" />');return false;" style="cursor:hand">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="image" accesskey="c" tabIndex="-1" onClick="tuichu();return false;" style="cursor:hand" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" border="0" height="22">
                  </div>
                  <br>
</html:form>

	
<%@ include file="./include/footer.jsp"%>

 </td>
  </tr>
</table>
</body>
<script type="text/javascript">

function goprint(){
    var wszh = "<bean:write name="wszPrintForm" property="headWszh" />";//�ڴ˼�ͨ����˰֤�ţ�
    var zclx = "<bean:write name="wszPrintForm" property="zclx" />";//ע������
    var tfrq = "<bean:write name="wszPrintForm" property="tfrq" />";//�����
    var zsjg = "<bean:write name="wszPrintForm" property="zsjg" />";//���ջ���
    var wsdwdm = "<bean:write name="wszPrintForm" property="nsrjsjdm" />";//���������
    var dz = "<bean:write name="wszPrintForm" property="dz" />";//��ַ
    var wsdwmc = "<bean:write name="wszPrintForm" property="nsrmc" />";
    var wsjehj = "��<bean:write name="wszPrintForm" property="hjje" />";//�ϼƽ��
    var wsjehj_cn = "<bean:write name="wszPrintForm" property="hjjedx" />";//�ϼƽ���д
    var wsbz = "<bean:write name="wszPrintForm" property="bz" />";//��ע

    var wsmxsz = "<bean:write name="wszPrintForm" property="mxSz" />";
    var wsmxpmmc = "<bean:write name="wszPrintForm" property="mxPmmc" />";
    var wsmxkssl = "<bean:write name="wszPrintForm" property="mxKssl" />";
    var wsmxjsje = "<bean:write name="wszPrintForm" property="mxJsje" />";
    var wsmxsl = "<bean:write name="wszPrintForm" property="mxSl" />";
    var wsmxskssrq = "<bean:write name="wszPrintForm" property="mxSkssrq" />";
    var wsmxyjje = "<bean:write name="wszPrintForm" property="mxYjhkc" />";
    var wsmxsjje = "<bean:write name="wszPrintForm" property="mxSjse" />";

	document.Vprinter.setWszh(wszh);//�ڴ������˰֤�Ÿ�ֵ����
    document.Vprinter.setZclx(zclx);
    document.Vprinter.setTfrq(tfrq);
    document.Vprinter.setZsjg(zsjg);
    document.Vprinter.setWsdwdm(wsdwdm);
    document.Vprinter.setDz(dz);
    document.Vprinter.setWsdwmc(wsdwmc);
    document.Vprinter.setWsbz(wsbz);

    document.Vprinter.setWsmxsz(wsmxsz);
    document.Vprinter.setWsmxpmmc(wsmxpmmc);
    document.Vprinter.setWsmxkssl(wsmxkssl);
    document.Vprinter.setWsmxjsje(wsmxjsje);
    document.Vprinter.setWsmxsl(wsmxsl);
    document.Vprinter.setWsmxskssrq(wsmxskssrq);
    document.Vprinter.setWsmxyjje(wsmxyjje);
    document.Vprinter.setWsmxsjje(wsmxsjje);
    document.Vprinter.setWsjehj(wsjehj);
    document.Vprinter.setWsjehj_cn(wsjehj_cn);

    document.Vprinter.startPrint();
	
	//�жϴ�ӡ�Ƿ�ɹ�
	fnOpen();
}

function fnOpen(){
   var ret = window.showModalDialog("printConfirm.html", "", 
      "dialogHeight: 200px;dialogWidth: 300px;status:0");
   if(!ret)  {
     alert("��ȷ�ϣ�");
	 fnOpen();
   }
   if(ret=="yes"){
   	//alert("old number="+ret);
   	//��ӡ�ɹ�
   	printSuccess();
   	return ret;
   }
   if(ret=="yes_second"){
   	//alert("old number="+ret);
   	goprint();
   	return ret;
   }
   if(ret=="no_second"){
   	//alert("get number="+ret);
   	rePrint();
   	return ret;
   }

}


function rePrint(){
	if (document.wszPrintForm.headWszh.value!="") {
		//alert('Reprint');
		doSubmitForm('Reprint');
	}
}

function printSuccess(){
	if (document.wszPrintForm.headWszh.value!="") {
		//alert('Reprint');
		doSubmitForm('Success');
	}
}

</script>
</html:html>