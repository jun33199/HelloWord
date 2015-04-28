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
<title>开具税收完税证明</title>
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
    <td class="1-td1">开具税收完税证明</td>
  </tr>
  <tr>
  <td class="1-td2"  colspan="2"> <br>
  	<table cellspacing="0" class="table-99" width="99%" height="27">
  		<tr class="black9">
  			<td align="center" nowrap  height="11">
  				<div align="center">填发日期：<bean:write name="wszmForm" property="tfrq"/> <html:hidden property="tfrq"/>&nbsp;</div>
            </td>
            <td align="center" nowrap  height="11">
            	<div align="right">&nbsp;征收机关名称：<bean:write name="wszmForm" property="swjgzzjgmc"/><html:hidden property="swjgzzjgmc"/> <html:hidden property="swjgzzjgdm"/></div>
            </td>
         </tr>
      </table>
      <table cellspacing=0 class="table-99" onkeydown="if(window.event.keyCode==13) window.event.keyCode=9">
      	<tr>
      		<td width="20%" class="3-td1-left"><div align="right">税收票证类型&nbsp;</div></td>
			<td colspan="5" class="3-td1-centen">
				<div align="left">&nbsp;<ttsoft:select property="sspzlxdm" codekey="SSPZLX" onchange="change()"/>&nbsp;</div>
			</td>
		</tr>
		
		<tr id="jks" style="display:block">
			<td class="2-td2-left"><div align="right">计算机代码&nbsp;</div></td>
			<td class="2-td2-left"><div align="left">&nbsp;<html:text property="query_jsjdm" size="15"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
			<td class="2-td2-left"><div align="right">税票号码&nbsp;</div></td>
			<td colspan="3" class="2-td2-center"><div align="left">&nbsp;<html:text property="query_sphm" size="20"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
		</tr>
		<tr id="wsz" style="display:block">
			<td nowrap class="2-td2-left"><div align="right">年度字别&nbsp;</div></td>
			<td class="2-td2-left"><div align="left">&nbsp;<html:text property="query_ndzb" size="15"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
			<td nowrap class="2-td2-left"><div align="right">收现缴款书号&nbsp;</div></td>
			<td colspan="3" class="2-td2-center"><div align="left">&nbsp;<html:text property="query_wszh" size="15"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
		</tr>
		<tr id="bd" style="display:block">
			<td nowrap class="2-td2-left"><div align="right">收现缴款书号&nbsp;</div></td>
			<td class="2-td2-left"><div align="left">&nbsp;<html:text property="query_bdwsz" size="15"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
			<td nowrap class="2-td2-left"><div align="right">号牌号码&nbsp;</div></td>
			<td class="2-td2-left"><div align="left">&nbsp;<html:text property="query_hphm" size="20"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
			<td nowrap class="2-td2-left"><div align="right">车架号&nbsp;</div></td>
			<td class="2-td2-center"><div align="left">&nbsp;<html:text property="query_cjbh" size="20"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
		</tr>
		<tr id="srths" style="display:block">
			<td nowrap class="2-td2-left"><div align="right">收入退还书号&nbsp;</div></td>
			<td colspan="5" class="2-td2-center"><div align="left">&nbsp;<html:text property="query_srthsh" size="20"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
		</tr>
		<tr id="wszm" style="display:block">
			<td class="2-td2-left"><div align="right">凭证类型&nbsp;</div></td>
			<td class="2-td2-left" colspan="5" ><div align="left">&nbsp;
			缴税<html:radio name="wszmForm" property="pzlxdm" value="<%=CodeConstant.WSZM_SGWSPZ_JS%>"></html:radio>&nbsp;&nbsp;
			退税<html:radio name="wszmForm" property="pzlxdm" value="<%=CodeConstant.WSZM_SGWSPZ_TS%>"></html:radio>&nbsp;(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
		</tr>
		<tr id="wszm2" style="display:block">
			<td nowrap class="2-td2-left"><div align="right">税收完税证明号&nbsp;</div></td>
			<td class="2-td2-left"><div align="left">&nbsp;<html:text property="query_wszm" size="15"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
			<td nowrap class="2-td2-left"><div align="right">收现缴款书号&nbsp;</div></td>
			<td class="2-td2-center"><div align="left">&nbsp;<html:text property="query_wszmh" size="15"/>(<FONT SIZE="" COLOR="#FF0000">*</FONT>)</div></td>
		</tr>
		<tr id="cxrq" style="display:block">
			<td nowrap class="2-td2-left"><div align="right">起始日期&nbsp;</div></td>
			<td class="2-td2-left"><div align="left">&nbsp;<html:text property="query_qsrq" size="15"/></div></td>
			<td nowrap class="2-td2-left"><div align="right">截止日期&nbsp;</div></td>
			<td colspan="3" class="2-td2-center"><div align="left">&nbsp;<html:text property="query_jzrq" size="15"/></div></td>
		</tr>
	  </table>
      <table width="98%" border="0" cellpadding="5" cellspacing="0">
        <tr  class="black9">
          <td colspan=3>带(<FONT SIZE="" COLOR="#FF0000">*</FONT>)的为必填项。日期输入格式为：YYYYMMDD。</td>
        </tr>
      </table>

      <br>
      <table width="94%" border="0" cellpadding="0" cellspacing="4">
        <tr  class="black9">
          <td width="20%">&nbsp;</td>
          <td width="20%" align="center"><input type="image" accesskey="c" tabIndex="-1" onClick="doQuery('Query'); return false;"  onMouseOver="MM_swapImage('bc1','','<%=static_contextpath%>images/chaxun2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="查询" id="bc1" src="<%=static_contextpath%>images/chaxun1.jpg" width="79" height="22"></td>
          <td width="8%"><input type="image" accesskey="x" tabIndex="-1" onclick="doSubmitForm('Dismiss');return false;" style="cursor:hand" onMouseOver="MM_swapImage('ds1','','<%=static_contextpath%>images/b-wh2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="维护" id="ds1" src="<%=static_contextpath%>images/b-wh1.jpg" width="79" border="0" height="22"></td>
          <td width="20%" align="center"><input type="image" accesskey="t" tabIndex="-1" onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc','','<%=static_contextpath%>images/tc-c1.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22"></td>
          <td width="20%">&nbsp;</td>
        </tr>
      </table>
      <br>
      
      <table width="94%" border="0" cellpadding="0" cellspacing="0" class="table-99">
        <tr>
          <td align="center" class="2-td1-center" nowrap>＝＝＝＝＝查询结果列表＝＝＝＝＝</td>
        </tr>
        <tr class="black9">
        <td class="1-td2">
            <table cellpadding="3" width="100%">
              <tr class="black9">
                <td align="right">
                	(第<bean:write name="wszmForm" property="nextPage"/>页/共<bean:write name="wszmForm" property="totalpage"/>页)
					<!--翻页功能开始-->
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="第一页" alt="第一页" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="上一页" alt="上一页" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="下一页" alt="下一页" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="最末页" alt="最末页" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<!--翻页功能开始-->
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
              	 <td nowrap class="2-1-td1-left" >原凭证号</td>
              	 <logic:equal name="wszmForm" property="sspzlxdm" value='<%=CodeConstant.WSZM_JKS%>'>
              	 	<td nowrap class="2-1-td1-left" >原凭证税票号</td>
              	 </logic:equal>
              	 <logic:notEqual name="wszmForm" property="sspzlxdm" value='<%=CodeConstant.WSZM_JKS%>'>
              	 	<td nowrap class="2-1-td1-left" >原凭证年度字别</td>
              	 </logic:notEqual>
                 <td nowrap class="2-1-td1-left" >纳税人识别号</td>
                 <td nowrap class="2-1-td1-left" >纳税人名称</td>
                 <td nowrap class="2-1-td1-left" >实缴金额</td>
                 <td nowrap class="2-1-td1-left" >所属税务机关</td>
                 <td nowrap class="2-1-td1-center" >完税证明已打印次数</td>
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
                	(第<bean:write name="wszmForm" property="nextPage"/>页/共<bean:write name="wszmForm" property="totalpage"/>页)
					<!--翻页功能开始-->
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="第一页" alt="第一页" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="前页" alt="前页" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="后页" alt="后页" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="最后一页" alt="最后一页" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<!--翻页功能开始-->
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
	
	//缴款书
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
	//完税证
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
	//保单
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
	//收入退还书
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
	//手工缴税完税凭证、手工退税完税凭证
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


//查询提交
function doQuery(ope)
{
    var sspzlxdm = document.all.sspzlxdm.value;
    
    //缴款书
    if(sspzlxdm == '<%=CodeConstant.WSZM_JKS%>')
    {
    	if (document.forms[0].query_jsjdm.value =="" && document.forms[0].query_sphm.value =="")
		{
			alert("计算机代码、税票号码必须输入至少一个！");
			document.forms[0].query_jsjdm.focus();
			return false;
		}
    }
    //完税证
    if((sspzlxdm == '<%=CodeConstant.WSZM_LSWSZ%>')||(sspzlxdm == '<%=CodeConstant.WSZM_GTWSZ%>')
    	||(sspzlxdm == '<%=CodeConstant.WSZM_QSWSZ%>')||(sspzlxdm == '<%=CodeConstant.WSZM_CCWSZ%>'))
    {
    	if (document.forms[0].query_ndzb.value =="" || document.forms[0].query_wszh.value =="")
		{
			alert("年度字别、收现缴款书号必须输入！");
			document.forms[0].query_wszh.focus();
			return false;
		}
    }
    
    //保单
    if(sspzlxdm == '<%=CodeConstant.WSZM_BD%>')
    {
    	if (document.forms[0].query_bdwsz.value =="" && document.forms[0].query_hphm.value =="" 
    		&& document.forms[0].query_cjbh.value =="")
		{
			alert("收现缴款书号、号牌号码、车架号必须输入至少一个！");
			document.forms[0].query_bdwsz.focus();
			return false;
		}
    }
    
    //收入退还书
    if(sspzlxdm == '<%=CodeConstant.WSZM_SRTHS%>')
    {
    	if (document.forms[0].query_srthsh.value =="")
		{
			alert("收入退还书号必须输入！");
			document.forms[0].query_srthsh.focus();
			return false;
		}
    }
    
    //手工完税凭证
    if(sspzlxdm == '<%=CodeConstant.WSZM_SGWSPZ%>')
    {
    	if (document.forms[0].query_wszm.value =="" && document.forms[0].query_wszmh.value =="")
		{
			alert("税收完税证明号、收现缴款书号必须输入至少一个！");
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
		//获取上一次操作类型
		temp=document.forms[0].actionType.value;
		//设置当前操作类型
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
			alert("没有任何已查询数据,请先查询...");
			return false;
		}
		if (type == "first")
		{
			if(tmpNextPage==1){
				alert("本次查询已到达第一页,请返回...");
				return false;
			}else{
					document.forms[0].nextPage.value="1";
			}
		}
		else if (type == "previous")
		{
			if(tmpNextPage==1){
				alert("本次查询已到达第一页,请返回...");
				return false;
			}else{
					document.forms[0].nextPage.value =(tmpNextPage-1)+"";
			}
		}
		else if (type == "next")
		{
			if(tmpNextPage>=tmpTotalPage){
				alert("本次查询已到达最后一页,请返回...");
				return false;
			}else{
					document.forms[0].nextPage.value =(tmpNextPage+1)+"";
			}
		}
		else if (type == "last")
		{
			if(tmpTotalPage==1){
				alert("本次查询只存在一页,请返回...");
				return false;
			}else if(tmpNextPage>=tmpTotalPage){
				alert("本次查询已到达最后一页,请返回...");
				return false;
			}
			else{
				document.forms[0].nextPage.value=document.forms[0].totalpage.value;
			}
		}
		//提交查询
		doQuery(temp);
		return false;
	}

</script>
</html:html>
