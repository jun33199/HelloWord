<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>


<HTML><HEAD><TITLE>�����걨��Ϣ��ӡ</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>


<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0">
<applet name="printer" codebase="<%=static_file%>printer" code="com.ttsoft.bjtax.webprint.DZJKZYJKSPrinter" width="0" height="0" archive="vprinter.jar">
</applet>
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="��˰�걨&gt;���������׹���&gt;�����걨��Ϣ��ӡ"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<SCRIPT language=javascript>
<!--



//-->
</SCRIPT>
<br>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>�������нɿ���</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
            <html:form action="/clfgl/mfskzs.do" type="POST">
            <html:hidden property="operationType"/>
			<html:hidden property="jsjdm" name ='mfskzsForm'/>
            <html:hidden property="sbbh" name ='mfskzsForm'/>   
             <TABLE border="0" cellSpacing=0 class=table-99>
              <TBODY>							 
               <tr>
			    			<td class="2-td2-left" nowrap="nowrap">���</td>
                <td class="2-td2-left" nowrap="nowrap">�걨���</td>
                <td class="2-td2-left" nowrap="nowrap">���</td>
								<td class="2-td2-center" nowrap="nowrap">&nbsp;</td>
			   			</tr>							 
              <logic:iterate id="dataList" name="mfskzsForm" property="sbxxList" indexId="index">   
               <TR>
								<TD class="2-td2-left" noWrap><%=index.intValue()+1%>&nbsp;</td>	  
       					<TD class="2-td2-left" noWrap><bean:write name="dataList" property="sbbh"/></TD>
        				<TD class="2-td2-left" noWrap><bean:write name="dataList" property="rkjehj"/></TD>
								<td class="2-td2-center" nowrap="nowrap">
                  <DIV align=center>
                  <IMG name="dyjks"
                      onMouseOver="MM_swapImage('dyjks','','<%=static_file%>images/dyjks2.jpg',1)"
                      onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/dyjks1.jpg"
                      onclick = "doSubmit('PrintJks','<bean:write name="dataList" property="jsjdm"/>','<bean:write name="dataList" property="sbbh"/>');return false;"
                      width="79" height="22" id="dyjks" alt="��ӡ�ɿ���" style="cursor:hand">
                  </DIV>
                </td>
      </TR> 
      </logic:iterate>	
     </TABLE>
     <br>
      		<DIV align=center>
      			<img onclick="doSubmitForm('Back');return false;" alt="ת����˰������"  onMouseOver="MM_swapImage('fanhui','','<%=static_file%>images/mfskzs2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/mfskzs1.jpg" name="fanhui" width="110" height="22" id="fanhui"  style="cursor:hand">&nbsp;
    			  <img onclick="doSubmitForm('Return');return false;" alt="�˳�"  onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">
      		</DIV>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY>

<script language=JavaScript type='text/JavaScript'>

function doSubmit(operationType,jsjdm,sbbh)
{    
		document.forms[0].jsjdm.value=jsjdm;
		document.forms[0].sbbh.value=sbbh;		  
    document.all.operationType.value = operationType;
    document.forms[0].submit(); 	
}
function doSubmitForm(operationType)
{    
		  
    document.all.operationType.value = operationType;
    document.forms[0].submit(); 	
}

StartPrint();
function StartPrint()
{
	var dybs ="<bean:write name="mfskzsForm" property="dybs" />" //��ӡ��־
	if(dybs == 1)
	{
		printTab();
	}
}

    function printTab(){

	var rqn="<bean:write name="mfskzsForm" property="sbrqn" />";
	var rqy="<bean:write name="mfskzsForm" property="sbrqy" />";
	var rqr="<bean:write name="mfskzsForm" property="sbrqr" />";    		


	var ysb="��";
	var sbxh="<bean:write name="mfskzsForm" property="sbbh" />";
	var wsb="";
	
	var nsrmc = "<bean:write name="mfskzsForm" property="nsrmc" />";//��˰������
    var fkrjsjdm = "<bean:write name="mfskzsForm" property="jsjdm" />";//�����˼��������
    var zsjgdm = ""; //���ջ��ش���
    
    var fkrmc = "<bean:write name="mfskzsForm" property="nsrmc" />";//���������� �ݶ���˰������
    var zsjgmc = "<bean:write name="mfskzsForm" property="swjgzzjgmc" />";//���ջ�������
    
    var fkrkhyhmc ="";//�����˿�����������
    var skgkmc = "<bean:write name="mfskzsForm" property="gkzzjgmc" />";//�տ��������
    
    var fkrzh = "";//�������˺�
    var gkqshhh = ""; //�����������к�

    var nsxmdm ="<bean:write name="mfskzsForm" property="mxSz" />";//��˰��Ŀ���� 
    var kssl = "";//��˰����
    var jsje = "";//��˰���
    var sjse = "<bean:write name="mfskzsForm" property="mxSjse" />"; //ʵ�ɽ��
    var jehjdx = "<bean:write name="mfskzsForm" property="hjjedx" />";  //���ϼƣ���д��
    var jehjxx = "��<bean:write name="mfskzsForm" property="hjje" />";  //���ϼƣ�Сд��
    var bz = "<bean:write name="mfskzsForm" property="bz" />"; //��ע
    
    document.printer.setNSRMC(nsrmc);
    document.printer.setRQN(rqn);
    document.printer.setRQY(rqy);
    document.printer.setRQR(rqr);
    document.printer.setYSB(ysb);
    document.printer.setSBXH(sbxh);
    document.printer.setWSB(wsb);
    document.printer.setFKRJSJDM(fkrjsjdm);
    document.printer.setZSJGDM(zsjgdm);
    document.printer.setFKRMC(fkrmc);
    document.printer.setZSJGMC(zsjgmc);
    document.printer.setFKRKHYHMC(fkrkhyhmc);
    document.printer.setSKGKMC(skgkmc);
    document.printer.setFKRZH(fkrzh);
    document.printer.setGKQSHHH(gkqshhh);
    document.printer.setNSXMDM(nsxmdm);
    document.printer.setKSSL(kssl);
    document.printer.setJSJE(jsje);
    document.printer.setSJSE(sjse);
    document.printer.setJEHJDX(jehjdx);
    document.printer.setJEHJXX(jehjxx);
    document.printer.setBZ(bz);
    
    document.printer.startPrint();
    }
    

</SCRIPT>

</HTML>
