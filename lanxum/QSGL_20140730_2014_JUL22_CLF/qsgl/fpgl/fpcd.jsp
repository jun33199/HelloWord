<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl.FpcdForm"%>

<%@page import="com.creationstar.bjtax.qsgl.util.DateUtils"%>
<%@page import ="java.util.Date"%>
<%
    session = request.getSession(false);
    FpcdForm fpcdForm = (FpcdForm)session.getAttribute("FpcdForm");
%>

<HTML>

<HEAD><TITLE>��Ʊ�ش����</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>

<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<logic:equal name="FpcdForm" property="bccgbz" value="1">
<applet name="printer" codebase="<%=static_file%>printer" code="com.ttsoft.bjtax.webprint.FpglPagePrinter" width="0" height="0" archive="vprinter.jar">
</applet>
</logic:equal>
<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="��Ʊ����&gt;��Ʊ�ش����"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<html:form action="/fpgl/fpcd.do">
<html:hidden property="operationType"/>
<html:hidden property="fphms"/>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
      <table align="center" cellspacing="0" class="table-99">
	  <TBODY>
        <tr>
          <td class="1-td1">��Ʊ�ش����</td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
            <table width="75%" border="0" cellspacing="0" class="table-99">
            <tr>
                <td width="15%" class="2-td2-t-left"><div align="right"><strong>��ǰ����</strong></div></td>
                <td class="2-td2-t-left"><div align="center"> <%=DateUtils.displayValue(new Date(),"yyyy-MM-dd")%> </div></td>
                <td width="15%" class="2-td2-t-left"><div align="right"><strong>������λ����</strong></div></td>
                <td width="50%" class="2-td2-t-center"><div align="center"><bean:write name="FpcdForm" property="swjgzzjgmc" />&nbsp;</div></td>
              </tr>
            </table>
            <br>
            <table width="75%" border="0" cellspacing="0" bgcolor="f3f3f3" class="table-99">
             <tr>
                <td width="15%" class="2-td2-t-left"><div align="right"><strong>��ͬ���</strong></div></td>
                <td colspan="2" class="2-td2-t-left"><div align="left"><html:text property="htbh" maxlength="18" size="22"/>&nbsp;</div></td>
                <td rowspan="2" class="2-td2-t-center">
                <div align="center">
                <img onClick="doSubmitForm('Query')" alt="��ѯ" style="cursor:hand" src="<%=static_file%>/images/chaxun1.jpg"  name="Image1111" width="79" height="22" border="0" id="Image1111" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1111','','<%=static_file%>/images/chaxun2.jpg',1)" >
                </div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="right"><strong>��Ʊ����</strong></div></td>
                <td width="20%" class="2-td2-left"><div align="left">
                 <bean:define id="dto" name="FpcdForm" property="fpzlList"/>
                  <html:select property="fpzldm" style='width:165px'>
                    <html:options collection="dto" labelProperty="fpzlmc" property="fpzldm"/>
                  </html:select></div>
                </td>
                <td class="2-td2-left"><div align="left"><html:text property="qshm" size="15"/>&nbsp;</div></td>
              </tr>
            </table>
            <br>
            <table border="0" bgcolor="f3f3f3" cellspacing="0" class="table-99">
              <tr>
                <td width="42%" ><hr width="100%" class="hr1" size=1></td>
                <td width="14%" align="left" class="black9" > <div align="center"><strong>��ѯ����б�</strong></div></td>
                <td width="44%" ><hr width="100%" class="hr1" size=1></td>
              </tr>
            </table>
            <div id="Layer2" style="position:static; overflow: auto;  width: 850px; height: 200px">
            <table border="0" bgcolor="f3f3f3" cellspacing="0" class="table-99">
              <tr bordercolor="#9BB4CA" class="z-ta2-td1">
                <td nowrap width="5%" class="2-td1-left">ѡ��</td>
                <td nowrap width="5%" class="2-td1-left">���</td>
                <td nowrap width="10%" class="2-td1-left">��Ʊ����</td>
                <td nowrap width="10%" class="2-td1-left">��ͬ���</td>
                <td nowrap width="10%" class="2-td1-left">���λ</td>
                <td nowrap width="10%" class="2-td1-left">�տλ</td>
                <td nowrap width="10%" class="2-td1-left">���ݲ�Ȩ֤��</td>
                <td nowrap width="10%" class="2-td1-left">���������ַ</td>
                <td nowrap width="10%" class="2-td1-center">���</td>
              </tr>
              <logic:iterate id="dataList" name="FpcdForm" property="cxList" indexId="index">
                 <tr >
                    <td class="2-td2-left" ><input value="<bean:write name='dataList' property='fpzldm'/>:<bean:write  name='dataList' property='fphm'/>" type="radio" name="selectIndex" />&nbsp;</td>
                    <td class="2-td2-left" ><%=index.intValue()+1%>&nbsp;</td>
                    <td class="2-td2-left"><bean:write name='dataList' property='fpzldm'/><bean:write name='dataList' property='fphm'/>&nbsp;</td>
                    <td class="2-td2-left" ><bean:write name='dataList' property='htbh'/>&nbsp;</td>
                    <td class="2-td2-left" ><bean:write name='dataList' property='nsrmc_buyer'/>&nbsp;</td>
                    <td class="2-td2-left" ><bean:write name='dataList' property='nsrmc_seller'/>&nbsp;</td>
                    <td class="2-td2-left" ><bean:write name='dataList' property='fwcqzh'/>&nbsp;</td>
                    <td class="2-td2-left" ><bean:write name='dataList' property='fwzldz'/>&nbsp;</td>
                    <td class="2-td2-center" ><bean:write name='dataList' property='je'/>&nbsp;</td>
                 </tr>
                </logic:iterate>
            </table> 
            </div>           
            <br>

      <DIV align=center>
      	<logic:equal name="FpcdForm" property="fpbcbc" value="">
      		<img onClick="doSubmitForm('Save');" alt="��ӡ" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image121','','<%=static_file%>images/dayin2.jpg',1)"  src="<%=static_file%>images/dayin1.jpg" name="Image121" width="79" height="22" border="0" id="Image121" >
      	</logic:equal>
      	<img onClick="doSubmitForm('Clear');" alt="���" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image122','','<%=static_file%>images/qingkong2.jpg',1)"  src="<%=static_file%>images/qingkong1.jpg" name="Image122" width="79" height="22" border="0" id="Image122" >
      	<img onClick="doSubmitForm('Fpdk');" alt="ת��Ʊ����" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image123','','<%=static_file%>images/fpdk2.jpg',1)"  src="<%=static_file%>images/fpdk1.jpg" name="Image123" width="100" height="22" border="0" id="Image123" >
      	<img onclick="doSubmitForm('Return');" alt="�˳�"  onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">
      </DIV>
      <BR>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>
<script language=JavaScript type='text/JavaScript'>

function doSubmitForm(operationType)
{
	if(operationType=="Query")
    {
    	var htbh = document.forms[0].htbh.value;
        var fphm = document.forms[0].qshm.value;
        if(htbh=="" && fphm =='')
		{
			alert("��������Ӧ�Ĳ�ѯ������");
			return false;
		}
    }
    
    if(operationType=="Save")
    {	
        var saveFpKeys = new Array();
        //���ҳ�����е�checkbox
     	var checkboxlist = document.getElementsByName("selectIndex");
     	var count=0;//��¼��ѡ������ļ�¼��
     	for (var i = 0; i < checkboxlist.length; i++) 
     	{
	         if(checkboxlist.item(i).checked)
	         {
				  count++;
	         } 
		 }
		if(count==0)
		{
		  alert("��ѡ��Ҫ�ύ�����ݣ�");
		  return false;
		}
		else
		{
			if(!window.confirm("�Ƿ��ӡѡ�еķ�Ʊ����,��ȷ�ϣ�"))
			{
				return false;
			}
    		 for (var i = 0; i < checkboxlist.length; i++)
    		 {
		         if(checkboxlist.item(i).checked)
		         {
						var value=checkboxlist.item(i).value;
						saveFpKeys.push(value);
		      } 
		 	}
		} 
		document.forms[0].fphms.value = saveFpKeys;
		//alert(document.forms[0].fphms.value);
    }
    
    document.all.operationType.value = operationType;
    document.forms[0].submit();
}

 
 //��շ�Ʊ����
 function clearFpqshm()
 {
	document.forms[0].cxqshm.value = '';
 }
</script>

<script language=JavaScript type='text/JavaScript'>
 StartPrint();
function StartPrint()
{
	var bccgbz ="<%=fpcdForm.getBccgbz()%>" //����ɹ���־
	if(bccgbz == 1)
	{
		goprint();
	}
}
function goprint()
{
	var fpzldm = "<%=fpcdForm.getFpzldm()%>"; //��Ʊ�������
	var fphm = "<%=fpcdForm.getFphm()%>"; //��Ʊ����
    var kprq = "<%=fpcdForm.getKprq()%>"; //��Ʊ����
    var hyfl = "<%=fpcdForm.getHyfl()%>"; //��ҵ����
    var fkdw = "<%=fpcdForm.getNsrmc_buyer()%>"; //���λ
	var skdw = "<%=fpcdForm.getNsrmc_seller()%>"; //�տλ
	var dkdw = "<%=fpcdForm.getSwjgzzjgmc()%>"; //������λ
	var pm = "<%=fpcdForm.getPm()%>"; //ƷĿ����Ŀ��
	var dj = "<%=fpcdForm.getDj()%>"; //����
	var sl = "<%=fpcdForm.getSl()%>"; //����
	var je = "<%=fpcdForm.getJe()%>"; //���
	var fwcqzh = "<%=fpcdForm.getFwcqzh()%>"; //���ݲ�Ȩ֤��
	var fwzldz = "<%=fpcdForm.getFwzldz()%>"; //���������ַ
	var bzItem = "<%=fpcdForm.getBz()%>"; //��ע
	var bz ="";
	if(bzItem !=null && bzItem !="null" && bzItem !="")
	{
		bz="<%=fpcdForm.getBz()%>";
	}
	var xxhj = "<%=fpcdForm.getXxhj()%>"; //Сд�ϼ�
	var dxhj = "<%=fpcdForm.getDxhj()%>"; //��д�ϼ�
	var wszh = "<%=fpcdForm.getWszh()%>"; //��˰֤����
	var jdhm = "<%=fpcdForm.getJdhm()%>"; //�������
	var kpr = "<%=fpcdForm.getKpr()%>"; //��Ʊ��
	
	//alert("��Ʊ�������:"+fpzldm+" ����:"+fphm+" ��Ʊ����:"+kprq+" ��ҵ����:"+hyfl+" ���λ:"+fkdw+" �տλ:"+skdw+" ƷĿ:"+pm+" ����:"+dj+" ����:"+sl+" ���:"+je+" ��Ȩ:"+fwcqzh+" ��ַ:"+fwzldz+" ��ע:"+bz+" Сд�ϼ�:"+xxhj+" ��д�ϼ�:"+dxhj+" ��˰֤:"+wszh+" �������:"+jdhm+" ��Ʊ��:"+kpr);
    
    document.printer.setfpzldm(fpzldm);
    document.printer.setfphm(fphm);
    document.printer.setkprq(kprq);
    document.printer.sethyfl(hyfl);
    document.printer.setfkdw(fkdw);
    document.printer.setskdw(skdw);
    document.printer.setdkdw(dkdw);
    document.printer.setpm(pm);
    document.printer.setdj(dj);
    document.printer.setsl(sl);
    document.printer.setje(je);
    document.printer.setfwcqzh(fwcqzh);
    document.printer.setfwzldz(fwzldz);
    document.printer.setbz(bz);
    document.printer.setxxhj(xxhj);
    document.printer.setdxhj(dxhj);
    document.printer.setwszh(wszh);
    document.printer.setjdhm(jdhm);
    document.printer.setkpr(kpr);
    
    document.printer.startPrint();

}
</script>

