<%@ page contentType="text/html; charset=GBK"%>

<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.hzWsz2JksForm" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="java.math.BigDecimal"%>

<%@ include file="/include/include.jsp"%>

<HTML><HEAD><TITLE>��ӡ�ɿ���</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>

<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<%
    session = request.getSession(false);
    hzWsz2JksForm hzWsz2JksForm = (hzWsz2JksForm)session.getAttribute("hzWsz2JksForm");
%>

<script type="text/javascript">
function doSubmitForm(operationType)
{
    document.forms[0].operationType.value=operationType;
  	document.forms[0].submit();
}
</script>

<script type="text/javascript">

function goprint()
{
//    var szdm = "<%=hzWsz2JksForm.getSzdm()%>"; //˰�ִ���
    var sklx = "<%=hzWsz2JksForm.getSklx()%>"; //˰������
    var jkpzh = "<%=hzWsz2JksForm.getJkpzh()%>"; //���Ա��
    var zclx = "<%=hzWsz2JksForm.getZclx()%>"; //ע������        
//    var tfrq = "<%=hzWsz2JksForm.getTfrq()%>"; //�����
    var tfrqn = "<%=hzWsz2JksForm.getTfrqn()%>"; //�������
    var tfrqy = "<%=hzWsz2JksForm.getTfrqy()%>"; //�������
    var tfrqr = "<%=hzWsz2JksForm.getTfrqr()%>"; //�������            
//    var lsgx = "<%=hzWsz2JksForm.getLsgx()%>"; //������ϵ
    var zsjg = "<%=hzWsz2JksForm.getZsjg()%>"; //���ջ���
   
    var jkdwdm = "<%=hzWsz2JksForm.getJkdwdm()%>"; //�ɿλ����
//    var jkdwdh = "<%=hzWsz2JksForm.getJkdwdh()%>"; //�ɿλ�绰
    var jkdwqc = "<%=hzWsz2JksForm.getJkdwqc()%>"; //�ɿλȫ��
    var jkdwkhyh = "<bean:write name="hzWsz2JksForm" property="jkdwkhyh" />"; //�ɿλ��������
    var jkdwzh = "<bean:write name="hzWsz2JksForm" property="jkdwzh" />"; //�ɿλ�ʺ�
    var skgk = "(<%=hzWsz2JksForm.getJhh()%>)<%=hzWsz2JksForm.getSkgk()%>"; //�տ����
//    var jhh = "<%=hzWsz2JksForm.getJhh()%>";    //������
    var skxjrq = "<%=hzWsz2JksForm.getSkxjrq()%>"; //˰���޽�����

    var yskmbm = "<%=hzWsz2JksForm.getYskmbm()%>"; //Ԥ���Ŀ����
    var yskmmc = "<%=hzWsz2JksForm.getYskmmc()%>"; //Ԥ���Ŀ����
//    var yskmjc = "<%=hzWsz2JksForm.getYskmjc()%>"; //Ԥ���Ŀ����
    var  jkmxfcbl = "<%=hzWsz2JksForm.getJkmxfcbl()%>"; //�ɿ���ϸ�ֳɱ���    
    var  jkmxpmmc = "<%=hzWsz2JksForm.getJkmxxmmc()%>"; //�ɿ���ϸ��Ŀ����
    var  jkmxkssl = "<%=hzWsz2JksForm.getJkmxkssl()%>"; //�ɿ���ϸ��˰����
    var  jkmxjsje = "<%=hzWsz2JksForm.getJkmxjsje()%>"; //�ɿ���ϸ��˰���
    var sksssq = "<%=hzWsz2JksForm.getSksskssq()%>" + "-" + "<%=hzWsz2JksForm.getSkssjssq()%>"; //˰������ʱ��    
    var  jkmxsjse = "<%=hzWsz2JksForm.getJkmxsjse()%>"; //�ɿ���ϸʵ��˰��

    var jkjehj = "<%=hzWsz2JksForm.getJkjehj()%>"; //�ɿ���ϼ�
    var jkjehj_nu = "<%=hzWsz2JksForm.getJkjehj_nu()%>";  //�ɿ���ϼ�����
    var swjg = "<%=hzWsz2JksForm.getSwjg()%>"; //˰�����
    var jkdw = "<%=hzWsz2JksForm.getJkdw()%>"; //�ɿλ
    var bz = "<%=hzWsz2JksForm.getBz()%>"; //��ע



//    document.printer.setszdm(szdm);
	  document.printer.setsklx(sklx);
	  document.printer.setdnbh(jkpzh);
    document.printer.setzclx(zclx);  	  
//    document.printer.settfrq(tfrq);
    document.printer.settfrqn(tfrqn);
    document.printer.settfrqy(tfrqy);
    document.printer.settfrqr(tfrqr); 
 
//    document.printer.setlsgx(lsgx);
    document.printer.setzsjg(zsjg);
    document.printer.setjkdwdm(jkdwdm);
//    document.printer.setjkdwdh(jkdwdh);
    document.printer.setjkdwqc(swjg);
    document.printer.setjkdwkhyh(jkdwkhyh);
    document.printer.setjkdwzh(jkdwzh);
    document.printer.setskgk(skgk);

    document.printer.setskxjrq(skxjrq);        
    document.printer.setyskmbm(yskmbm);
    document.printer.setyskmmc(yskmmc);
//    document.printer.setyskmjc(yskmjc);
    document.printer.setjkmxfcbl(jkmxfcbl);    
    document.printer.setjkmxpmmc(jkmxpmmc);
    document.printer.setjkmxkssl(jkmxkssl);
    document.printer.setjkmxjsje(jkmxjsje);
    document.printer.setsksssq(sksssq);    
    document.printer.setjkmxsjse(jkmxsjse);

    document.printer.setjkjehj(jkjehj);
    document.printer.setjkjehj_nu(jkjehj_nu);

    document.printer.setswjg(swjg);
		document.printer.setjkdw(swjg);
    document.printer.setbz(bz);
//    document.printer.setgkjhh(jhh);

    document.printer.startPrint();

}

</script>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0" onload="goprint()">
<applet name="printer" codebase="<%=static_file%>printer" code="com.ttsoft.bjtax.webprint.SSJKSPagePrinterNew" width="0" height="0" archive="vprinter.jar">
</applet>
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="���&gt;��˼��տ�&gt;��ӡ�ɿ���"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>
<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height=580 width=770>
<TBODY>
	   <TR>
		<TD  class=2-td2-t-center> <DIV align=center><B><FONT SIZE="4" COLOR="">�л����񹲺͹�<br>˰�սɿ���(���о���ר��)</FONT></B></DIV></TD>
		</TR>

        <TR>
        <TD class=1-td2><BR>
        <html:form action="/jks/printHzWsz.do" >
        <html:hidden property = "operationType" />

            <TABLE border=0 cellSpacing=0 class=table-99 width="88%">
              <TBODY>
               <TR>
             	<TD class=0 width="15%">˰������:</TD>
             	<TD align=left class=0 width="17%"><bean:write name="hzWsz2JksForm" property="sklx" /></TD>
             	<TD class=0 width="10%">���Ա��:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="hzWsz2JksForm" property="jkpzh" /></TD>
             	<TD class=0 width="10%">&nbsp;</TD>
							<TD align=left class=0 width="22%">&nbsp;</TD>
               </TR>
               <TR>
             	<TD class=0 width="15%">�Ǽ�ע������:</TD>
             	<TD align=left class=0 width="17%"><bean:write name="hzWsz2JksForm" property="zclx" /></TD>
             	<TD class=0 width="10%">�����:</TD>
             	<TD align=left class=0 width="22%"><bean:write name="hzWsz2JksForm" property="tfrqn" />��<bean:write name="hzWsz2JksForm" property="tfrqy" />��<bean:write name="hzWsz2JksForm" property="tfrqr" />��</TD>
             	<TD class=0 width="10%">˰�����:</TD>
							<TD align=left class=0 width="22%"><bean:write name="hzWsz2JksForm" property="zsjg" /></TD>
                </TR></TBODY></TABLE><BR>

		    <TABLE border=1 cellSpacing=0 class=table-99 width="75%">
		    <TR>
		    	<TD rowspan=2  class=2-td2-t-left width="8%">�ɿλ(��)</TD>
		    	<TD class=2-td2-t-left width="7%">ʶ���</TD>
		    	<TD align=left class=2-td2-t-left width="30%"><bean:write name="hzWsz2JksForm" property="jkdwdm" />&nbsp;</TD>
		    	<TD class=2-td2-t-left width="10%">��������</TD>
		    	<TD align=left class=2-td2-t-center width="35%"><bean:write name="hzWsz2JksForm" property="jkdwkhyh" />&nbsp;</TD>
		    </TR>
		    <TR>

		    	<TD class=2-td2-left width="7%">����</TD>
		    	<TD class=2-td2-left width="30%"><bean:write name="hzWsz2JksForm" property="swjg" />&nbsp;</TD>
		    	<TD class=2-td2-left width="10%">�˺�</TD>
		    	<TD class=2-td2-center width="35%"><bean:write name="hzWsz2JksForm" property="jkdwzh" />&nbsp;</TD>
		    </TR>
		    <TR>
		    	<TD colspan="2" class=2-td2-left width="15%">�տ����</TD>
		    	<TD align=left class=2-td2-left width="30%">(<bean:write name="hzWsz2JksForm" property="jhh" />)<bean:write name="hzWsz2JksForm" property="skgk" />&nbsp;</TD>
		    	<TD class=2-td2-left width="10%">�ɿ��޽�����</TD>
		    	<TD align=left class=2-td2-center width="35%"><bean:write name="hzWsz2JksForm" property="skxjrq" />&nbsp;</TD>
		    </TR>
		    </TABLE>
		    
          <TABLE border=1 cellSpacing=0 class=table-99 width="75%">
           <TBODY>
		   		<TR>
		   			<TD colspan="3" class=2-td2-t-left width="25%">Ԥ&nbsp;��&nbsp;��&nbsp;Ŀ</TD>
           	<TD rowspan="2" class=2-td2-t-left width="10%">ƷĿ����</TD>
           	<TD rowspan="2" class=2-td2-t-left width="8%">��˰����</TD>
           	<TD rowspan="2" class=2-td2-t-left width="10%">��˰����<br>��������</TD>
           	<TD rowspan="2" class=2-td2-t-left width="8%">˰�ʻ�<br>��λ˰��</TD>
           	<TD rowspan="2" class=2-td2-t-left width="14%">˰������ʱ��</TD> 
           	<TD rowspan="2" class=2-td2-t-left width="5%">�ѽɻ�<br>�۳���</TD>            	
           	<TD rowspan="2" class=2-td2-t-center width="10%">ʵ�ɽ��</TD>
           </TR>
		   		<TR>
		   			<TD class=2-td2-t-left width="8%">��&nbsp;&nbsp;��</TD>
		   			<TD class=2-td2-t-left width="9%">��&nbsp;&nbsp;��</TD>
		   			<TD class=2-td2-t-left width="8%">��&nbsp;&nbsp;��</TD>
           </TR>
 <logic:iterate id="data" indexId="index" length="length" name="hzWsz2JksForm" property="mxList" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx"  >
           <TR>
           	<TD class=2-td2-left><bean:write name="hzWsz2JksForm" property="yskmbm" />&nbsp;</TD>
           	<TD class=2-td2-left><bean:write name="hzWsz2JksForm" property="yskmmc" />&nbsp;</TD>
           	<TD class=2-td2-left><bean:write name="data" property="yskmfcbl" />&nbsp;</TD>
           	<TD class=2-td2-left><bean:write name="data" property="szsmmc" />&nbsp;</TD>
           	<TD class=2-td2-left><bean:write name="data" property="kssl"/>&nbsp;</TD>
           	<TD class=2-td2-left>��<%=DataConvert.BigDecimal2String((BigDecimal)data.getJsje(),2,false)%>&nbsp;</TD>
           	<TD class=2-td2-left>&nbsp;</TD>
           	<TD class=2-td2-left><bean:write name="hzWsz2JksForm" property="sksskssq" />-<bean:write name="hzWsz2JksForm" property="skssjssq" /></TD>
           	<TD class=2-td2-left>&nbsp;</TD>
           	<TD class=2-td2-center>��<%=DataConvert.BigDecimal2String((BigDecimal)data.getSjse(),2,false)%>&nbsp;</TD>
           </TR>
</logic:iterate>
		   		<TR>
		   			<TD class=2-td2-t-left width="8%">���ϼ�</TD>
		   			<TD class=2-td2-t-left colspan="8" width="9%">����д��<bean:write name="hzWsz2JksForm" property="jkjehj" />&nbsp;</TD>
		   			<TD class=2-td2-t-center width="8%"><bean:write name="hzWsz2JksForm" property="jkjehj_nu" />&nbsp;</TD>
           </TR>
		   </TBODY>
           </TABLE>
           
            <TABLE border=0 cellSpacing=0 class=table-99 width="75%">
              <TBODY>
              <TR>
                <TD class=2-td2-t-left width="20%">
                   <DIV align=center>˰�����:</DIV>
				  				 <DIV align=center><bean:write name="hzWsz2JksForm" property="swjg" /></DIV>
				  				 <DIV align=left>��Ʊ��</DIV></TD>              	
                <TD class=2-td2-t-left width="20%">
                  <DIV align=center>�ɿλ(��)</DIV>
                  <DIV align=center><bean:write name="hzWsz2JksForm" property="swjg" /></DIV>
				  				<DIV align=left>������</DIV></TD>
                <TD class=2-td2-t-left width="30%">
                  <DIV align=center>���п��������ײ���ת�տλ�˻�</DIV>
				  				<DIV align=center>���⣨���У�����</DIV>
				  				<DIV align=center>&nbsp;&nbsp;�� &nbsp;&nbsp;�� &nbsp;&nbsp;��</DIV></TD>
                <TD class=2-td2-t-center width="30%">
                  <DIV align=left>��ע</DIV>
                  <DIV align=left><bean:write name="hzWsz2JksForm" property="bz" /></DIV></TD>
                </DIV></TD></TR></TBODY></TABLE><BR>

                    <DIV align=center>
                    <IMG alt=��ӡ id=dayin name=dayin
                      onclick="goprint()"
                      onMouseOver="MM_swapImage('dayin','','<%=static_file%>images/dayin2.jpg',1)"
                      onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/dayin1.jpg"
                      width="79" style="cursor:hand">
                    <IMG alt=�˳� height=22 name=tuichu
                      onclick="doSubmitForm('ReturnPrepage');"
                      onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
                      onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg"
                      width="79" id="tuichu1" style="cursor:hand">

                    </DIV><BR>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
