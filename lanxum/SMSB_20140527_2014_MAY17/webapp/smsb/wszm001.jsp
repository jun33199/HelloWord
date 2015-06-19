<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page import="com.ttsoft.bjtax.smsb.wszm.web.WszmForm"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant" %>
<%@ page import="com.ttsoft.bjtax.smsb.util.JspUtil" %>
<html:html>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>

<style>
.Noprn 
{
	DISPLAY: none;
}
</style>

<head>
<title>����˰����˰֤��</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/reader.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="./include/header.jsp"%>
<%
    WszmForm form = (WszmForm)request.getAttribute("wszmForm");
%>

<html:form action="/webapp/smsb/wszmAction.do" method="POST" >
<html:hidden property="actionType" />
<html:hidden property="pageSize"/>
<html:hidden property="nextPage"/>
<html:hidden property="totalpage"/>
<table width="96%" align="center" cellspacing="0" class="table-99">
  <tr>
    <td class="1-td1">����˰����˰֤��</td>
  </tr>
  <tr>
  <td class="1-td2"  colspan="2"> <br>
  	<table cellspacing="0" class="table-99" width="99%" height="27">
  		<tr class="black9">
  			<td align="center" nowrap  height="11">
  				<div align="center">����ڣ�<bean:write name="wszmForm" property="tfrq"/> <html:hidden property="tfrq"/>&nbsp;</div>
            </td>
            <td align="center" nowrap  height="11">
            	<div align="right">&nbsp;���ջ������ƣ�<bean:write name="wszmForm" property="swjgzzjgmc"/><html:hidden property="swjgzzjgmc"/> <html:hidden property="swjgzzjgdm"/></div>
            </td>
         </tr>
      </table>
      <table cellspacing=0 class="table-99" onkeydown="if(window.event.keyCode==13) window.event.keyCode=9">
      	<tr>
      		<td width="20%" class="3-td1-left"><div align="right">˰��Ʊ֤����&nbsp;</div></td>
			<td colspan="5" class="3-td1-centen">
				<div align="left">&nbsp;<ttsoft:select property="sspzlxdm" codekey="SSPZLX" onchange="change()"/>&nbsp;</div>
			</td>
		</tr>
		
		<tr id="jks" style="display:block">
			<td class="2-td2-left"><div align="right">���������&nbsp;</div></td>
			<td class="2-td2-left"><div align="left">&nbsp;<html:text property="query_jsjdm" size="15"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
			<td class="2-td2-left"><div align="right">˰Ʊ����&nbsp;</div></td>
			<td colspan="3" class="2-td2-center"><div align="left">&nbsp;<html:text property="query_sphm" size="20"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
		</tr>
		<tr id="wsz" style="display:block">
			<td nowrap class="2-td2-left"><div align="right">����ֱ�&nbsp;</div></td>
			<td class="2-td2-left"><div align="left">&nbsp;<html:text property="query_ndzb" size="15"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
			<td nowrap class="2-td2-left"><div align="right">���ֽɿ����&nbsp;</div></td>
			<td colspan="3" class="2-td2-center"><div align="left">&nbsp;<html:text property="query_wszh" size="15"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
		</tr>
		<tr id="bd" style="display:block">
			<td nowrap class="2-td2-left"><div align="right">���ֽɿ����&nbsp;</div></td>
			<td class="2-td2-left"><div align="left">&nbsp;<html:text property="query_bdwsz" size="15"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
			<td nowrap class="2-td2-left"><div align="right">���ƺ���&nbsp;</div></td>
			<td class="2-td2-left"><div align="left">&nbsp;<html:text property="query_hphm" size="20"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
			<td nowrap class="2-td2-left"><div align="right">���ܺ�&nbsp;</div></td>
			<td class="2-td2-center"><div align="left">&nbsp;<html:text property="query_cjbh" size="20"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
		</tr>
		<tr id="srths" style="display:block">
			<td nowrap class="2-td2-left"><div align="right">�����˻����&nbsp;</div></td>
			<td colspan="5" class="2-td2-center"><div align="left">&nbsp;<html:text property="query_srthsh" size="20"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
		</tr>
		<tr id="wszm" style="display:block">
			<td class="2-td2-left"><div align="right">ƾ֤����&nbsp;</div></td>
			<td class="2-td2-left" colspan="5" ><div align="left">&nbsp;
			��˰<html:radio name="wszmForm" property="pzlxdm" value="<%=CodeConstant.WSZM_SGWSPZ_JS%>"></html:radio>&nbsp;&nbsp;
			��˰<html:radio name="wszmForm" property="pzlxdm" value="<%=CodeConstant.WSZM_SGWSPZ_TS%>"></html:radio>&nbsp;(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
		</tr>
		<tr id="wszm2" style="display:block">
			<td nowrap class="2-td2-left"><div align="right">˰����˰֤����&nbsp;</div></td>
			<td class="2-td2-left"><div align="left">&nbsp;<html:text property="query_wszm" size="15"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
			<td nowrap class="2-td2-left"><div align="right">���ֽɿ����&nbsp;</div></td>
			<td class="2-td2-center"><div align="left">&nbsp;<html:text property="query_wszmh" size="15"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
		</tr>
		<tr id="cxrq" style="display:block">
			<td nowrap class="2-td2-left"><div align="right">��ʼ����&nbsp;</div></td>
			<td class="2-td2-left"><div align="left">&nbsp;<html:text property="query_qsrq" size="15"/></div></td>
			<td nowrap class="2-td2-left"><div align="right">��ֹ����&nbsp;</div></td>
			<td colspan="3" class="2-td2-center"><div align="left">&nbsp;<html:text property="query_jzrq" size="15"/></div></td>
		</tr>
	  </table>
      <table width="98%" border="0" cellpadding="5" cellspacing="0">
        <tr  class="black9">
          <td colspan=3>��(<FONT SIZE="" COLOR="#FF0000">*</FONT>)��Ϊ��������������ʽΪ��YYYYMMDD��</td>
        </tr>
      </table>

      <br>
      <table width="94%" border="0" cellpadding="0" cellspacing="4">
        <tr  class="black9">
          <td width="20%">&nbsp;</td>
          <td width="20%" align="center"><input type="image" accesskey="c" tabIndex="-1" onClick="doQuery('Query'); return false;"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="��ѯ" id="bc1" src="<%=static_contextpath%>images/chaxun1.jpg" width="79" height="22"></td>
          <td width="8%"><input type="image" accesskey="x" tabIndex="-1" onclick="doSubmitForm('Dismiss');return false;" style="cursor:hand" onMouseOver="MM_swapImage('ds1','','<%=static_contextpath%>images/b-wh2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="ά��" id="ds1" src="<%=static_contextpath%>images/b-wh1.jpg" width="79" border="0" height="22"></td>
          <td width="20%" align="center"><input type="image" accesskey="t" tabIndex="-1" onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc','','<%=static_contextpath%>images/tc-c1.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22"></td>
          <td width="20%">&nbsp;</td>
        </tr>
      </table>
      <br>
      
      <table width="94%" border="0" cellpadding="0" cellspacing="0" class="table-99">
        <tr>
          <td align="center" class="2-td1-center" nowrap>������������ѯ����б���������</td>
        </tr>
        <tr class="black9">
        <td class="1-td2">
            <table cellpadding="3" width="100%">
              <tr class="black9">
                <td align="right">
                	(��<bean:write name="wszmForm" property="nextPage"/>ҳ/��<bean:write name="wszmForm" property="totalpage"/>ҳ)
					<!--��ҳ���ܿ�ʼ-->
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="��ĩҳ" alt="��ĩҳ" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<!--��ҳ���ܿ�ʼ-->
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr class="black9">
          <td align="right" colspan=2 nowrap>
          <div id="Layer2" style="position:static; overflow: auto;  width: 100%; height: 10%">
            <table width="100%" border="0" cellpadding="0" cellspacing="0"  align="center">
              <tr>
              	 <td nowrap class="2-1-td1-left" >&nbsp;</td>
              	 <td nowrap class="2-1-td1-left" >ԭƾ֤��</td>
              	 <logic:equal name="wszmForm" property="sspzlxdm" value='<%=CodeConstant.WSZM_JKS%>'>
              	 	<td nowrap class="2-1-td1-left" >ԭƾ֤˰Ʊ��</td>
              	 </logic:equal>
              	 <logic:notEqual name="wszmForm" property="sspzlxdm" value='<%=CodeConstant.WSZM_JKS%>'>
              	 	<td nowrap class="2-1-td1-left" >ԭƾ֤����ֱ�</td>
              	 </logic:notEqual>
                 <td nowrap class="2-1-td1-left" >��˰��ʶ���</td>
                 <td nowrap class="2-1-td1-left" >��˰������</td>
                 <td nowrap class="2-1-td1-left" >ʵ�ɽ��</td>
                 <td nowrap class="2-1-td1-left" >����˰�����</td>
                 <td nowrap class="2-1-td1-center" >��˰֤���Ѵ�ӡ����</td>
              </tr>
            	<bean:define id="dataList" name="wszmForm" property="dataList"/>
            	<logic:iterate name="dataList" indexId="index" id="item" type="com.ttsoft.bjtax.smsb.model.client.Wszm">
              	<tr>
              		<%
              				String nsrsbh ="";
              				String nsrmc ="";
              				String ysphm ="";
              				String ypzh ="";
              				String yndzb ="";
              				String ypzzldm ="";
              				String swjgdm ="";
              				String swjgmc ="";
              				String dycs ="";
              				if((item.getPzlydm() != null) && !item.getPzlydm().equals("")){
              					if((item.getPzlydm().equals(CodeConstant.WSZM_LSWSZ)) 
              					   || (item.getPzlydm().equals(CodeConstant.WSZM_GTWSZ)) 
              					   || (item.getPzlydm().equals(CodeConstant.WSZM_QSWSZ)) 
              					   || (item.getPzlydm().equals(CodeConstant.WSZM_CCWSZ)) 
              					   || (item.getPzlydm().equals(CodeConstant.WSZM_SGWSPZ)) 
              					   || (item.getPzlydm().equals(CodeConstant.WSZM_BD))){
              						if((item.getYwszh() != null) && !item.getYwszh().equals("")){ypzh = item.getYwszh();}
              					}else{
              						if((item.getYpzh() != null) && !item.getYpzh().equals("")){ypzh = item.getYpzh(); ysphm = item.getYsphm();}
              					}
              				}
              				
              				if((item.getYpzzldm() != null) && !item.getYpzzldm().equals("")){ypzzldm = item.getYpzzldm();}
              				if((item.getYndzb() != null) && !item.getYndzb().equals("")){yndzb = item.getYndzb();}
              				if((item.getNsrsbh() != null) && !item.getNsrsbh().equals("")){nsrsbh = item.getNsrsbh();}
              				if((item.getNsrmc() != null) && !item.getNsrmc().equals("")){nsrmc = item.getNsrmc();}
              				if((item.getSsswjgzzjgdm() != null) && !item.getSsswjgzzjgdm().equals("")){swjgdm = item.getSsswjgzzjgdm();}
              				if((item.getSsswjgzzjgdm() != null) && !item.getSsswjgzzjgdm().equals("")){swjgmc = item.getSsswjgzzjgmc();}
              				if((item.getDycs() != null) && !item.getDycs().equals("")){dycs = item.getDycs();}
              		%>
              		<td class="2-td2-left" >&nbsp;<%=index.intValue()+1%>&nbsp;</td>
              		<td class="2-td2-left" >&nbsp;<a href="wszmAction.do?actionType=Detail&nsrsbh=<%=nsrsbh%>&nsrmc=<%=nsrmc%>&ypzh=<%=item.getYpzh()%>&ywszh=<%=item.getYwszh()%>&index=<%=index.intValue()%>&yndzb=<%=yndzb%>&ypzzldm=<%=ypzzldm%>&sspzlxdm=<bean:write name="wszmForm" property="sspzlxdm"/>&hjje=<%=JspUtil.format(item.getHjje())%>&dycs=<%=dycs%>&swjgdm=<%=swjgdm%>&ysphm=<%=ysphm%>" target='_blank'><%=ypzh%></td>
              		<logic:equal name="wszmForm" property="sspzlxdm" value='<%=CodeConstant.WSZM_JKS%>'>
              	 		<td nowrap class=2-td2-left ><%=ysphm%></td>
              	 	</logic:equal>
              	 	<logic:notEqual name="wszmForm" property="sspzlxdm" value='<%=CodeConstant.WSZM_JKS%>'>
              	 		<td class="2-td2-left" >&nbsp;<%=yndzb%>&nbsp;</td>
              	 	</logic:notEqual>
              		<td class="2-td2-left" >&nbsp;<%=nsrsbh%>&nbsp;</td>
              		<td class="2-td2-left" >&nbsp;<%=nsrmc%>&nbsp;</td>
              		<td class="2-td2-left" >&nbsp;<%=JspUtil.format(item.getHjje())%>&nbsp;</td>
              		<td class="2-td2-left" >&nbsp;<%=swjgmc%></td>
              		<td class="2-td2-center" >&nbsp;<%=dycs%>&nbsp;</td>
              	</tr>
              </logic:iterate>
            </table>
            </div>
          </td>
        </tr>
        <tr class="black9">
          <td class="1-td2" align="right"  colspan="2">
            <table cellpadding="3" align="right">
              <tr class="black9">
                <td align="right">
                	(��<bean:write name="wszmForm" property="nextPage"/>ҳ/��<bean:write name="wszmForm" property="totalpage"/>ҳ)
					<!--��ҳ���ܿ�ʼ-->
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="ǰҳ" alt="ǰҳ" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="��ҳ" alt="��ҳ" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="���һҳ" alt="���һҳ" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<!--��ҳ���ܿ�ʼ-->
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</html:form>

<%@ include file="./include/footer.jsp"%>
</body>
<script language="JavaScript">

function change()
{

	var sspzlxdmValue = document.all.sspzlxdm.value;
		
	var jks = document.all("jks");
	var wsz = document.all("wsz");
	var bd = document.all("bd");
	var srths = document.all("srths");
	var wszm = document.all("wszm");
	var wszm2 = document.all("wszm2");
	var cxrq = document.all("cxrq");
	
	//�ɿ���
	if(sspzlxdmValue == '<%=CodeConstant.WSZM_JKS%>')
	{
		jks.style.display="block";
			document.all.query_jsjdm.disabled=false;
			document.all.query_sphm.disabled=false;
		wsz.style.display="none";
			document.all.query_ndzb.disabled=true;
			document.all.query_wszh.disabled=true;
		bd.style.display="none";
			document.all.query_bdwsz.disabled=true;
			document.all.query_hphm.disabled=true;
			document.all.query_cjbh.disabled=true;
		srths.style.display="none";
			document.all.query_srthsh.disabled=true;
		wszm.style.display="none";
		wszm2.style.display="none";
			document.all.query_wszm.disabled=true;
			document.all.query_wszmh.disabled=true;
		cxrq.style.display="block";
			document.all.query_jsjdm.disabled=false;
			document.all.query_sphm.disabled=false;
	
	}
	//��˰֤
	if((sspzlxdmValue == '<%=CodeConstant.WSZM_LSWSZ%>')
		||(sspzlxdmValue == '<%=CodeConstant.WSZM_GTWSZ%>')
		||(sspzlxdmValue == '<%=CodeConstant.WSZM_QSWSZ%>')
		||(sspzlxdmValue == '<%=CodeConstant.WSZM_CCWSZ%>'))
	{
		jks.style.display="none";
			document.all.query_jsjdm.disabled=true;
			document.all.query_sphm.disabled=true;
		wsz.style.display="block";
			document.all.query_ndzb.disabled=false;
			document.all.query_wszh.disabled=false;
		bd.style.display="none";
			document.all.query_bdwsz.disabled=true;
			document.all.query_hphm.disabled=true;
			document.all.query_cjbh.disabled=true;
		srths.style.display="none";
			document.all.query_srthsh.disabled=true;
		wszm.style.display="none";
		wszm2.style.display="none";
			document.all.query_wszm.disabled=true;
			document.all.query_wszmh.disabled=true;
		cxrq.style.display="block";
			document.all.query_jsjdm.disabled=false;
			document.all.query_sphm.disabled=false;
	}
	//����
	if(sspzlxdmValue == '<%=CodeConstant.WSZM_BD%>')
	{
		jks.style.display="none";
			document.all.query_jsjdm.disabled=true;
			document.all.query_sphm.disabled=true;
		wsz.style.display="none";
			document.all.query_ndzb.disabled=true;
			document.all.query_wszh.disabled=true;
		bd.style.display="block";
			document.all.query_bdwsz.disabled=false;
			document.all.query_hphm.disabled=false;
			document.all.query_cjbh.disabled=false;
		srths.style.display="none";
			document.all.query_srthsh.disabled=true;
		wszm.style.display="none";
		wszm2.style.display="none";
			document.all.query_wszm.disabled=true;
			document.all.query_wszmh.disabled=true;
		cxrq.style.display="block";
			document.all.query_jsjdm.disabled=false;
			document.all.query_sphm.disabled=false;
	
	}
	//�����˻���
	if(sspzlxdmValue == '<%=CodeConstant.WSZM_SRTHS%>')
	{
		jks.style.display="none";
			document.all.query_jsjdm.disabled=true;
			document.all.query_sphm.disabled=true;
		wsz.style.display="none";
			document.all.query_ndzb.disabled=true;
			document.all.query_wszh.disabled=true;
		bd.style.display="none";
			document.all.query_bdwsz.disabled=true;
			document.all.query_hphm.disabled=true;
			document.all.query_cjbh.disabled=true;
		srths.style.display="block";
			document.all.query_srthsh.disabled=false;
		wszm.style.display="none";
		wszm2.style.display="none";
			document.all.query_wszm.disabled=true;
			document.all.query_wszmh.disabled=true;
		cxrq.style.display="block";
			document.all.query_jsjdm.disabled=false;
			document.all.query_sphm.disabled=false;
	
	}
	//�ֹ���˰��˰ƾ֤���ֹ���˰��˰ƾ֤
	if(sspzlxdmValue == '<%=CodeConstant.WSZM_SGWSPZ%>')
	{
		jks.style.display="none";
			document.all.query_jsjdm.disabled=true;
			document.all.query_sphm.disabled=true;
		wsz.style.display="none";
			document.all.query_ndzb.disabled=true;
			document.all.query_wszh.disabled=true;
		bd.style.display="none";
			document.all.query_bdwsz.disabled=true;
			document.all.query_hphm.disabled=true;
			document.all.query_cjbh.disabled=true;
		srths.style.display="none";
			document.all.query_srthsh.disabled=true;
		wszm.style.display="block";
		wszm2.style.display="block";
			document.all.query_wszm.disabled=false;
			document.all.query_wszmh.disabled=false;
		cxrq.style.display="block";
			document.all.query_jsjdm.disabled=false;
			document.all.query_sphm.disabled=false;
	}
	
	if( (sspzlxdmValue != '<%=CodeConstant.WSZM_JKS%>')
		 && (sspzlxdmValue != '<%=CodeConstant.WSZM_LSWSZ%>')
		 && (sspzlxdmValue != '<%=CodeConstant.WSZM_GTWSZ%>')
		 && (sspzlxdmValue != '<%=CodeConstant.WSZM_QSWSZ%>')
		 && (sspzlxdmValue != '<%=CodeConstant.WSZM_CCWSZ%>')
		 && (sspzlxdmValue != '<%=CodeConstant.WSZM_BD%>')
		 && (sspzlxdmValue != '<%=CodeConstant.WSZM_SRTHS%>')
		 && (sspzlxdmValue != '<%=CodeConstant.WSZM_SGWSPZ%>'))
	{
		jks.style.display="none";
			document.all.query_jsjdm.disabled=true;
			document.all.query_sphm.disabled=true;
		wsz.style.display="none";
			document.all.query_ndzb.disabled=true;
			document.all.query_wszh.disabled=true;
		bd.style.display="none";
			document.all.query_bdwsz.disabled=true;
			document.all.query_hphm.disabled=true;
			document.all.query_cjbh.disabled=true;
		srths.style.display="none";
			document.all.query_srthsh.disabled=true;
		wszm.style.display="none";
			document.all.query_wszm.disabled=true;
		cxrq.style.display="none";
			document.all.query_jsjdm.disabled=true;
			document.all.query_sphm.disabled=true;
	}
}
change();


//��ѯ�ύ
function doQuery(ope)
{
    var sspzlxdm = document.all.sspzlxdm.value;
    
    //�ɿ���
    if(sspzlxdm == '<%=CodeConstant.WSZM_JKS%>')
    {
    	if (document.forms[0].query_jsjdm.value =="" && document.forms[0].query_sphm.value =="")
		{
			alert("��������롢˰Ʊ���������������һ����");
			document.forms[0].query_jsjdm.focus();
			return false;
		}
    }
    //��˰֤
    if((sspzlxdm == '<%=CodeConstant.WSZM_LSWSZ%>')||(sspzlxdm == '<%=CodeConstant.WSZM_GTWSZ%>')
    	||(sspzlxdm == '<%=CodeConstant.WSZM_QSWSZ%>')||(sspzlxdm == '<%=CodeConstant.WSZM_CCWSZ%>'))
    {
    	if (document.forms[0].query_ndzb.value =="" || document.forms[0].query_wszh.value =="")
		{
			alert("����ֱ����ֽɿ���ű������룡");
			document.forms[0].query_wszh.focus();
			return false;
		}
    }
    
    //����
    if(sspzlxdm == '<%=CodeConstant.WSZM_BD%>')
    {
    	if (document.forms[0].query_bdwsz.value =="" && document.forms[0].query_hphm.value =="" 
    		&& document.forms[0].query_cjbh.value =="")
		{
			alert("���ֽɿ���š����ƺ��롢���ܺű�����������һ����");
			document.forms[0].query_bdwsz.focus();
			return false;
		}
    }
    
    //�����˻���
    if(sspzlxdm == '<%=CodeConstant.WSZM_SRTHS%>')
    {
    	if (document.forms[0].query_srthsh.value =="")
		{
			alert("�����˻���ű������룡");
			document.forms[0].query_srthsh.focus();
			return false;
		}
    }
    
    //�ֹ���˰ƾ֤
    if(sspzlxdm == '<%=CodeConstant.WSZM_SGWSPZ%>')
    {
    	if (document.forms[0].query_wszm.value =="" && document.forms[0].query_wszmh.value =="")
		{
			alert("˰����˰֤���š����ֽɿ���ű�����������һ����");
			document.forms[0].query_wszm.focus();
			return false;
		}
    }
    
    document.forms[0].actionType.value = "Query";
    document.forms[0].target="_self";
    document.forms[0].submit();
}

function fn_ChangePage(type)
	{
		//��ȡ��һ�β�������
		temp=document.forms[0].actionType.value;
		//���õ�ǰ��������
		if(temp=="Query"||temp=="ChangePage"){
			temp="ChangePage";
		}else{
			temp="Show";
		}
		//
		var tmpNextPage=document.forms[0].nextPage.value*1;
		var tmpTotalPage=document.forms[0].totalpage.value*1;
		//alert("tmpNextPage:"+tmpNextPage+"|"+"tmpTotalPage:"+tmpTotalPage+"|"+"type:"+type);
		if(temp=="Show"){
			alert("û���κ��Ѳ�ѯ����,���Ȳ�ѯ...");
			return false;
		}
		if (type == "first")
		{
			if(tmpNextPage==1){
				alert("���β�ѯ�ѵ����һҳ,�뷵��...");
				return false;
			}else{
					document.forms[0].nextPage.value="1";
			}
		}
		else if (type == "previous")
		{
			if(tmpNextPage==1){
				alert("���β�ѯ�ѵ����һҳ,�뷵��...");
				return false;
			}else{
					document.forms[0].nextPage.value =(tmpNextPage-1)+"";
			}
		}
		else if (type == "next")
		{
			if(tmpNextPage>=tmpTotalPage){
				alert("���β�ѯ�ѵ������һҳ,�뷵��...");
				return false;
			}else{
					document.forms[0].nextPage.value =(tmpNextPage+1)+"";
			}
		}
		else if (type == "last")
		{
			if(tmpTotalPage==1){
				alert("���β�ѯֻ����һҳ,�뷵��...");
				return false;
			}else if(tmpNextPage>=tmpTotalPage){
				alert("���β�ѯ�ѵ������һҳ,�뷵��...");
				return false;
			}
			else{
				document.forms[0].nextPage.value=document.forms[0].totalpage.value;
			}
		}
		//�ύ��ѯ
		doQuery(temp);
		return false;
	}

</script>
</html:html>
