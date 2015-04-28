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
<title>�����еط�˰����걨�ɿ</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="./include/header.jsp"%>

<html:form action="/webapp/smsb/jksPrintAction.do" method="POST">
<html:hidden property="actionType" />
<html:hidden property="headJkpzh" />
<html:hidden property="headJsjdm" />
<html:hidden property="headSjly" />


<applet name="printer" code="com.ttsoft.bjtax.webprint.SSJKSPagePrinterNew" width="0" height="0" archive="<%=static_contextpath%>printer/vprinter.jar">
</applet>

    <table width="80%" border="2" align="center" cellpadding="0" cellspacing="4" bordercolor="#9DB9D2">
        <tr>
          <td height="147" bgcolor="#F0F0F0">
          <div align="center"><span class="black9"><font size="5" color="#999999">�� �� �� �� �� �� ��<br>˰�սɿ���(���о���ר��)</font>
              </span> </div>
<!--            <p align="center"><font color="#ABC6CD">&nbsp;</font><font color="#d32e2e"><u>&nbsp;&nbsp;
		<bean:write name="jksPrintForm" property="szdm" />&nbsp;&nbsp;</u></font><font color="#2C556D">˰�սɿ���</font></p> 
            <p align="center" class="black9"><font color="#999999"></font></p>--> </td>
        </tr>
        <tr>
          <td><table width="100%" cellpadding="4" cellspacing="0" class="black9" id="AutoNumber4" style="border-collapse: collapse">
              <tr>
                <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">˰�����ͣ�</font><font color="#d32e2e"><bean:write name="jksPrintForm" property="sklx" /></font></td>
                <td width="30%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">���Ա�ţ�</font><font color="#d32e2e"><bean:write name="jksPrintForm" property="headJkpzh" /></font></td>
                <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"></td>
              </tr>
              <tr>
                <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"></td>
                <td width="30%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"></td>
                <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"></td>
              </tr>                        	
              <tr>
                <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">�Ǽ�ע�����ͣ�</font><font color="#d32e2e"><bean:write name="jksPrintForm" property="headZclxmc" /></font></td>
                <td width="30%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">����ڣ�</font><font color="#d32e2e"><bean:write name="jksPrintForm" property="headTfrqn" />��<bean:write name="jksPrintForm" property="headTfrqy" />��<bean:write name="jksPrintForm" property="headTfrqr" />��</font></td>
                <td width="35%" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF"><font color="#2C556D">˰����أ�</font><font color="#d32e2e"><bean:write name="jksPrintForm" property="headZsjgmc" /></font></td>
              </tr>
            </table>
            <table width="100%" height="102" border="2" cellpaddng="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber3" style="border-collapse: collapse">
              <tr>
                <td width="50" rowspan="2" height="32"><div align="center"><font color="#2C556D">�ɿλ���ˣ�</font></div></td>
                <td width="50" height="16"><div align="center"><font color="#2C556D">ʶ���</font></div></td>
                <td width="300" height="16"><font color="#d32e2e"><bean:write name="jksPrintForm" property="dm" /></font></td>                	
                <td width="70" height="16"><div align="center"><font color="#2C556D">��������</font></div></td>
                <td width="280" height="16"><font color="#d32e2e"><bean:write name="jksPrintForm" property="khyh" /></font></td>
              </tr>
              <tr>
                <td width="50" height="16"><div align="center"><font color="#2C556D">��&nbsp;&nbsp;��</font></div></td>
                <td width="300" height="16"><font color="#d32e2e"><bean:write name="jksPrintForm" property="qc" /></font></td>                	
                <td width="70" height="16"><div align="center"><font color="#2C556D">��&nbsp;&nbsp;&nbsp;&nbsp;��</font></div></td>
                <td width="280" height="16"><font color="#d32e2e"><bean:write name="jksPrintForm" property="zh" /></font></td>
              </tr>
              <tr>
                <td width="100" colspan="2" height="16"><div align="center"><font color="#2C556D">�տ����</font></div></td>
                <td width="300" height="16"><font color="#d32e2e"><bean:write name="jksPrintForm" property="skgk" /></font></td>                	
                <td width="70" height="16"><div align="center"><font color="#2C556D">˰���޽�����</font></div></td>
                <td width="280" height="16">
                
                <logic:equal name="jksPrintForm" property="xjrqEdit" value="true">
                 <html:text name="jksPrintForm" property="editSkxjrq"  size="12" onKeyDown="if(event.keyCode==13) event.keyCode=9;" maxlength="10" style="font-size: 9pt;color:#d32e2e;"/>
                </logic:equal>
                
                <!--���ص�Ԫ˰���⴦��-->	
               <logic:equal name="jksPrintForm" property="xjrqEdit" value="false">
                <bean:define id="tempXjrq" name="jksPrintForm" property="skxjrq"/>
                <% 
                if(tempXjrq!=null&&!tempXjrq.equals("")){
                %>
                <font color="#d32e2e"><%=tempXjrq.toString().substring(0,4)+tempXjrq.toString().substring(5,7)+tempXjrq.toString().substring(8,10)%></font>
                <% 	
                }
                %>
                <html:hidden property="editSkxjrq" />
                </logic:equal>
                
                </td>
              </tr>              
            </table>
            <table width="100%" height="122" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber2" style="border-collapse: collapse">
              <tr>
              	<td width="280" colspan="3" style="padding-top: 0" height="16" align="center"><font color="#2C556D">Ԥ&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;Ŀ</font></td>
              	<td width="130" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">Ʒ&nbsp;Ŀ&nbsp;��&nbsp;��</font></td>
              	<td width="46" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">��˰����</font></td>
                <td width="58" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">��˰����<br>��������</font></td>
                <td width="46" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">˰&nbsp;��&nbsp;��<br>��λ˰��</font></td>
                <td width="112" rowspan="2" style="padding-top: 0" height="32" align="center"><p align="center"><font color="#2C556D">˰&nbsp;&nbsp;��<br>����ʱ��</font></td>
								<td width="32" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">��&nbsp;��&nbsp;��<br>��&nbsp;��&nbsp;��</font></td>                	
                <td width="46" rowspan="2" style="padding-top: 0" height="32" align="center"><font color="#2C556D">ʵ&nbsp;��&nbsp;˰&nbsp;��</font></td>              
              </tr>
              <tr>
              	<td width="80" style="padding-top: 0" height="16" align="center"><font color="#2C556D">��&nbsp;&nbsp;��</font></td>
              	<td width="160" style="padding-top: 0" height="16" align="center"><font color="#2C556D">��&nbsp;&nbsp;��</font></td>
              	<td width="80" style="padding-top: 0" height="16" align="center"><font color="#2C556D">��&nbsp;&nbsp;��</font></td>
              </tr>
  				<!--The loop begin-->
                <bean:define id="jkslist" name="jksPrintForm" property="dataList"/>
                <logic:iterate indexId="index" name="jkslist" id="itemMap">
          		<bean:define id="item" name="itemMap"/>
              <tr>
              	<td style="padding-top: 0" height="16"><font color="#d32e2e"><bean:write name="jksPrintForm" property="yskmdm" /></font></td>
              	<td style="padding-top: 0" height="16"><font color="#d32e2e"><bean:write name="jksPrintForm" property="yskmmc" /></font></td>
              	<td style="padding-top: 0" height="16"><font color="#d32e2e"><ttsoft:write name="item" property="fcbl"/></font></td>
              	<td style="padding-top: 0" height="16"><font color="#d32e2e"><ttsoft:write name="item" property="szsmmc"/></font></td>
                <td style="padding-top: 0" height="16"><div align="right"><font color="#d32e2e"><ttsoft:write name="item" property="kssl"/></font></div></td>
                <td style="padding-top: 0" height="16"><div align="right"><font color="#d32e2e"><ttsoft:write name="item" property="jsje"/></font></div></td>                	              		
                <td style="padding-top: 0" height="16"><font color="#d32e2e">&nbsp;</font></td>
                <td style="padding-top: 0" height="16"><font color="#d32e2e"><bean:write name="jksPrintForm" property="skssksrq" />-<bean:write name="jksPrintForm" property="skssjsrq" /></font></td>
                <td style="padding-top: 0" height="16"><font color="#d32e2e">&nbsp;</font></td>		
                <td style="padding-top: 0" height="16"><div align="right"><font color="#d32e2e"><ttsoft:write name="item" property="sjse"/></font></div></td>
              </tr>
				</logic:iterate>
                <!--The end of loop-->
              <tr>
                <td width="80" style="padding-top: 0" height="15"><font color="#2C556D">���ϼ�</font></td>              	
                <td colspan="8" style="padding-top: 0" height="15"><font color="#2C556D">����д��</font><font color="#d32e2e"><bean:write name="jksPrintForm" property="hjjedx" /></font></td>
                <td width="46" style="padding-top: 0" height="15"><div align="right"><font color="#d32e2e">��<bean:write name="jksPrintForm" property="hjje" /></font></div></td>
              </tr>
            </table>
            <table width="100%" border="2" cellpadding="4" cellspacing="0" bordercolor="#9DB9D2" class="black9" id="AutoNumber5" style="border-collapse: collapse">
              <tr>
                <td width="15%"><div align="center"><font color="#2C556D">˰����أ� </font></div>
                  <p><div align="center"><font color="#d32e2e"><bean:write name="jksPrintForm" property="dfswjg" /></font></div></p>
                  <p><div align="center"><font color="#2C556D">�����£�</font></div>	
                  <p><font color="#2C556D"><div align="left">��Ʊ�� &nbsp;&nbsp;</font><font color="#d32e2e"><bean:write name="jksPrintForm" property="tpr" /></font></div></td>
                <td width="15%"><div align="center"><font color="#2C556D">�ɿλ���ˣ� </font></div>
                  <p><div align="center"><font color="#d32e2e"><bean:write name="jksPrintForm" property="jkdw" /></font></div></p>
                  <p><div align="center"><font color="#2C556D">�����£�</font></div>	                  	
                  <p><div align="left"><font color="#2C556D">������ &nbsp;&nbsp;</font></font></div></td>
                <td width="30%"><div align="center"><font color="#2C556D">���п��������ײ���ת�տλ�˻� </font></div>
                  <p><div align="center"><font color="#2C556D">���⣨���У�����</font></div></p>
                  <p><div align="center"><font color="#2C556D">&nbsp;&nbsp;�� &nbsp;&nbsp;�� &nbsp;&nbsp;��</font></font></div></td>                  	
                <td width="40%"><div align="left"><font color="#2C556D">��ע��</font><font color="#d32e2e">
                <logic:equal name="jksPrintForm" property="bzVisible" value="true">
                 <bean:write name="jksPrintForm" property="bz" />
                </logic:equal>
                </font></div>               	                			
                	</td>
              </tr>
            </table></td>
        </tr>
      </table>
<br>

                  <div align="center">
                  <logic:equal name="jksPrintForm" property="saveBtnVisible" value="true">
					<input type="image" accesskey="s" tabIndex="-1" src="<%=static_contextpath%>images/bc-s1.jpg" onmouseover="this.src='<%=static_contextpath%>images/bc-s2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/bc-s1.jpg'" alt="����" onclick="befSave('Save');return false;" style="cursor:hand">
					&nbsp;&nbsp;&nbsp;&nbsp;
				  </logic:equal>
				  	
					<input type="image" accesskey="p" tabIndex="-1" src="<%=static_contextpath%>images/dy-p1.jpg" onmouseover="this.src='<%=static_contextpath%>images/dy-p2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/dy-p1.jpg'" alt="��ӡ" onclick="goprint();return false;" style="cursor:hand">
                    &nbsp;&nbsp;&nbsp;&nbsp;
					<input type="image" accesskey="c" tabIndex="-1" src="<%=static_contextpath%>images/tc-c1.jpg" onmouseover="this.src='<%=static_contextpath%>images/tc-c2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/tc-c1.jpg'" alt="�ر�" onclick="window.close();return false;" style="cursor:hand">
                  </div>
                  <br>
</html:form>

<%@ include file="./include/footer.jsp"%>

 </td>
  </tr>
</table>
</body>
<script language="JavaScript">
<%
  String token = "";
  if (session != null){
     token = (String) session.getAttribute(org.apache.struts.action.Action.TRANSACTION_TOKEN_KEY);
  }
%>
opener.document.all("org.apache.struts.taglib.html.TOKEN").value='<%=token%>';

</script>


<script language="JavaScript">

var xjrqEdit = "<bean:write name="jksPrintForm" property="xjrqEdit" />";
function fnOnLoad(){
	if(xjrqEdit=='true'){
     document.jksPrintForm.editSkxjrq.focus();
	}
}

function befSave(actionFlag){
	if(xjrqEdit=='true'){
		//alert('test2');
	 if (isDate(document.jksPrintForm.editSkxjrq,"")){
		doSubmitForm(actionFlag);
	 }
   }
}

function goprint(){
 if(document.printer.readyState==4){
//    var szdm = "<bean:write name="jksPrintForm" property="szdm" />"; //˰�ִ���
    var sklx = "<bean:write name="jksPrintForm" property="sklx" />"; //˰������
//    var tfrq = "<bean:write name="jksPrintForm" property="headTfrq" />"; //�����
    var dnbh = "<bean:write name="jksPrintForm" property="headJkpzh" />"; //���Ա��
    var zclx = "<bean:write name="jksPrintForm" property="headZclxmc" />"; //�Ǽ�ע������    
    var tfrqn = "<bean:write name="jksPrintForm" property="headTfrqn" />"; //�������
    var tfrqy = "<bean:write name="jksPrintForm" property="headTfrqy" />"; //�������
    var tfrqr = "<bean:write name="jksPrintForm" property="headTfrqr" />"; //�������            
//    var lsgx = "<bean:write name="jksPrintForm" property="headLsgx" />"; //������ϵ
    var zsjg = "<bean:write name="jksPrintForm" property="headZsjgmc" />"; //˰�����

    var jkdwdm = "<bean:write name="jksPrintForm" property="dm" />"; //�ɿλʶ���
//    var jkdwdh = "<bean:write name="jksPrintForm" property="dh" />"; //�ɿλ�绰
    var jkdwqc = "<bean:write name="jksPrintForm" property="qc" />"; //�ɿλ����
    var jkdwkhyh = "<bean:write name="jksPrintForm" property="khyh" />"; //�ɿλ��������
    var jkdwzh = "<bean:write name="jksPrintForm" property="zh" />"; //�ɿλ�˺�
    var skgk = "<bean:write name="jksPrintForm" property="skgk" />"; //�տ����    
    var skxjrq = "<bean:write name="jksPrintForm" property="skxjrq" />"; //˰���޽�����    

    var yskmbm = "<bean:write name="jksPrintForm" property="yskmdm" />"; //Ԥ���Ŀ����
    var yskmmc = "<bean:write name="jksPrintForm" property="yskmmc" />"; //Ԥ���Ŀ����
//    var yskmjc = "<bean:write name="jksPrintForm" property="yskmjc" />"; //Ԥ���Ŀ����
    var jkmxfcbl = "<bean:write name="jksPrintForm" property="mxFcbl" />"; //Ԥ���Ŀ�ֳɱ���
    var  jkmxpmmc = "<bean:write name="jksPrintForm" property="mxPmmc"/>"; //�ɿ���ϸƷĿ����(TBD)
    var  jkmxkssl = "<bean:write name="jksPrintForm" property="mxKssl"/>"; //�ɿ���ϸ��˰����(TBD)
    var  jkmxjsje = "<bean:write name="jksPrintForm" property="mxJsje"/>"; //�ɿ���ϸ��˰���(TBD)
    var sksssq = "<bean:write name="jksPrintForm" property="skssksrq" /> - <bean:write name="jksPrintForm" property="skssjsrq" />"; //˰������ʱ��    
    var  jkmxsjse = "<bean:write name="jksPrintForm" property="mxSjse"/>"; //�ɿ���ϸʵ��˰��(TBD)

    var jkjehj = "<bean:write name="jksPrintForm" property="hjjedx" />"; //�ɿ���ϼ�(TBD)
    var hjje = "��<bean:write name="jksPrintForm" property="hjje" />";//�ϼƽ��Сд

    var swjg = "<bean:write name="jksPrintForm" property="dfswjg" />"; //˰�����(TBD)
    var jkdw = "<bean:write name="jksPrintForm" property="jkdw" />"; //�ɿλ(TBD)
    var bz = "<bean:write name="jksPrintForm" property="bz" />"; //��ע(TBD)


	 //alert(szdm);



		//δ����handģʽ 20131115 zhangyj
    //The second format!//�˴β���Ԥ�㼶��
    var jkmxyjkc = " ";
    var sksssq_qs = "<bean:write name="jksPrintForm" property="skssksrq" />";
    var sksssq_jz = "<bean:write name="jksPrintForm" property="skssjsrq" />";
    var jkmxslse = " ";




    //�жϴ�ӡ��ʽ/��ʽ
    var printBack = fnOpen();
    //alert(printBack);
    if (printBack=="machine"){
    	//old
		//The first format!
	    //document.printer.setszdm(szdm);
	    //document.printer.setlsgx(lsgx);	    
	    document.printer.setsklx(sklx); //The new
	    document.printer.setdnbh(dnbh);		    
	    document.printer.setzclx(zclx);
	    //document.printer.settfrq(tfrq);
	    document.printer.settfrqn(tfrqn);	    
	    document.printer.settfrqy(tfrqy);	
	    document.printer.settfrqr(tfrqr);
	    document.printer.setzsjg(zsjg);
	    document.printer.setjkdwdm(jkdwdm);
	    //document.printer.setjkdwdh(jkdwdh);
	    document.printer.setjkdwkhyh(jkdwkhyh);	    
	    document.printer.setjkdwqc(jkdwqc);
	    document.printer.setjkdwzh(jkdwzh);
	    document.printer.setskgk(skgk);
	    document.printer.setskxjrq(skxjrq);	    
	    document.printer.setyskmbm(yskmbm);
	    document.printer.setyskmmc(yskmmc);
	    //document.printer.setyskmjc(yskmjc);
	    document.printer.setjkmxfcbl(jkmxfcbl);	    
	    document.printer.setjkmxpmmc(jkmxpmmc);
	    document.printer.setjkmxkssl(jkmxkssl);
	    document.printer.setjkmxjsje(jkmxjsje);
	    document.printer.setsksssq(sksssq);
	    document.printer.setjkmxsjse(jkmxsjse);
	    document.printer.setjkjehj(jkjehj);
	    document.printer.setjkjehj_nu(hjje);
	    document.printer.setswjg(swjg);	    
	    document.printer.setjkdw(jkdw);
	    document.printer.setbz(bz);    
    	document.printer.startPrint();
    	//window.close();
    	//return false;
    }
    if (printBack=="hand"){
    	//new
	    document.pt_printer.setSzmc(szdm);
	    document.pt_printer.setSklx(sklx);//The new 
	    document.pt_printer.setTfrq(tfrq);
	    document.pt_printer.setLsgx(lsgx);
	    document.pt_printer.setZclx(zclx);
			document.pt_printer.setDnbh(dnbh);
	    document.pt_printer.setZsjg(zsjg);
	    document.pt_printer.setJkdwdm(jkdwdm);
	    document.pt_printer.setJkdwdh(jkdwdh);
	    document.pt_printer.setJkdwqc(jkdwqc);
	    document.pt_printer.setJkdwkhyh(jkdwkhyh);
	    document.pt_printer.setJkdwzh(jkdwzh);
	    document.pt_printer.setYskmbm(yskmbm);
	    document.pt_printer.setYskmmc(yskmmc);
	    document.pt_printer.setSksssq(sksssq_qs, sksssq_jz);
	    document.pt_printer.setSkxjrq(skxjrq);
	    document.pt_printer.setJkmxpmmc(jkmxpmmc);
	    document.pt_printer.setJkmxkssl(jkmxkssl);
	    document.pt_printer.setJkmxjsje(jkmxjsje);
	    document.pt_printer.setJkmxslse(jkmxslse);
	    document.pt_printer.setJkmxyjkc(jkmxyjkc);
	    document.pt_printer.setJkmxsjse(jkmxsjse);
	
	    document.pt_printer.setJkjehj_cn(hjje);
			document.pt_printer.setJkjehj_nu(hjje);
	
	    document.pt_printer.setJkdw(jkdw);
	    document.pt_printer.setBz(bz);
    
    	document.pt_printer.startPrint();
    	
    	//return false;
    }
 }else{
	 alert("��ӡ�ؼ�δ�ɹ����أ��޷����д�ӡ��");
	 return false;
 }
}

//��ӡ����Ʊ��Modified by lufeng 2003-12-19
function fnOpen(){
   return "machine";
}

// ����Ҫ��ӡ�ֹ�Ʊ��Modified by lufeng 2003-12-19
//function fnOpen(){
//   var ret = window.showModalDialog("printChoose.html", "",
//      "dialogHeight: 150px;dialogWidth: 350px;status:0");
//   if (ret=="hand" || ret=="machine" || ret=="failure") {
//   	return ret;
//   }
//   else {
//   	 alert("��ȷ�ϣ�");
//   	 return fnOpen();
//   	 //return "";
//   }
//}


</script>
</html:html>