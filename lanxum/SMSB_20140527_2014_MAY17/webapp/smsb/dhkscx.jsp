<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.web.DhkscxForm"%>
<%@ page import="com.ttsoft.common.util.CodeConstants"%>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.model.PKJBSJModel" %>
<%@ page import="com.ttsoft.bjtax.smsb.lwpk.model.PLKKDHCXModel" %>
<%@ page import="java.util.*" %>
<%
DhkscxForm form=(DhkscxForm)request.getAttribute("dhkscxForm");
PKJBSJModel dhcxmodel = form.getPkjbsjModel();
List plkkdhcxModelList = form.getPlkkdhcxlist();

%>
<html:html>
<head>
<title>����������˰��ѯ</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
<script language="javascript" src="../js/gmit_selectcontrol.js"></script>
</head>
<SCRIPT LANGUAGE="JavaScript">
	 function doit(ope){
		 rep = /^[0-1]{1}[0-9]{1}$/;
		 if(document.forms[0].jsjdm.value==""){
				alert("��ѯ�����м�������벻��Ϊ�գ�");
				return false;
			}
		 if(document.forms[0].jsjdm.value.length!=8){
			 alert("��ѯ�����м�������볤�Ȳ���ȷ��");
				return false;
		 }
		if(isFullDate(document.forms[0].nd.value,1,"noempty",true)==false){
			alert("��ѯ��������ȸ�ʽ����ȷ��");
			return false;
		}
		if(document.forms[0].kkqsyf.value==""){
			alert("��ѯ��������ʼ�·ݲ���Ϊ�գ�");
			return false;
		}
		if(rep.test(document.forms[0].kkqsyf.value)==false){
			alert("��ʼ�·ݸ�ʽ����ȷ��");
			return false;
		}
		if(document.forms[0].kkzzyf.value==""){
			alert("��ѯ��������ֹ�·ݲ���Ϊ�գ�");
			return false;
		}
		if(rep.test(document.forms[0].kkzzyf.value)==false){
			alert("��ֹ�·ݸ�ʽ����ȷ��");
			return false;
		}
		if(document.forms[0].kkqsyf.value > document.forms[0].kkzzyf.value){
			alert("��ʼ�·ݲ��ܴ�����ֹ�·ݣ�");
			return false;
		}
		doSubmitForm(ope);
	}
	
	//����ҳ��ʱ��������Ϊ��ѯ���¼��
  // ҳ����뽹��ȷ��
   function fnOnLoad()
  {
	<%
	if(request.getSession().getAttribute("is_show").equals("false") && plkkdhcxModelList == null){
		%>
		alert("δ��ѯ���������������ݣ�");
		<%
		request.getSession().removeAttribute("is_show");
	}
	%>
    document.forms[0].jsjdm.focus();
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
		doit(temp);
		return false;
	}
	//����Excel�ύ
function doToExcelSubmit(){

if(document.all.totalpage.value==""||document.all.totalpage.value==null||document.all.totalpage.value=="0")
	{
		alert("���Ȳ�ѯ��");
		return false;
	}
 if(document.forms[0].nd.value==""){
		alert("��ѯ��������Ȳ���Ϊ�գ�");
		return false;
	}
if(!window.confirm("ȷ��Ҫ����Excel��ѯ����ļ���")){
		return false;
	}
       doSubmitForm('SaveExcel');
       return true; 
}
//-->
</SCRIPT>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
 <%@ include file="./include/header.jsp"%>
 
<html:form action="/webapp/smsb/dhkscxAction.do" method="POST" >
  <html:hidden property="actionType" />
	<html:hidden property="pageSize"/>
	<html:hidden property="nextPage"/>
	<html:hidden property="totalpage"/>
	<html:hidden property="message"/>
	<table width="96%" align="center" cellspacing="0" class="table-99">
		<tr>
			<td class="1-td1"  colspan="7">����������˰��ѯ </td>
		</tr>
		<tr>
			<td class="1-td2"  colspan="7">
				<br>
				<table  cellspacing="0" class="table-99">
				    <tr> 
					  <td width="25%" class="2-td2-t-left">���������</td>
					  <td width="15%" class="2-td2-t-left">
					  	<div align="left">
					     <html:text property="jsjdm" size="10" maxlength="8"/> <FONT SIZE="" COLOR="#FF0000">*</FONT>           
            			</div>
					  </td>
					  <td width="25%" class="2-td2-t-left">���</td>
					  <td width="15%" class="2-td2-t-left">				    
						<div align="left">
              				<html:text property="nd" size="8" maxlength="4"/><FONT SIZE="" COLOR="#FF0000">*</FONT>
                      </div>
                       </td>
                       <td rowspan="2" width="20%" class="2-td2-t-center">
                       <div>
                        <input name="I2" type="image" accesskey="q" value="��ѯ"  onclick="doit('Query');return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" align="middle">
                      </div>
                       </td>
					</tr>
					<tr> 
					  <td width="25%" class="2-td2-left">�ۿ���ʼ�·�</td>
					  <td width="15%" class="2-td2-left">
					  <div align="left">
					  <html:text property="kkqsyf" size="10" maxlength="2"/>
					  <span>MM�磺01<FONT SIZE="" COLOR="#FF0000">*</FONT></span>
						</div>
					  </td>
					  <td width="25%" class="2-td2-left">�ۿ���ֹ�·�</td>
					  <td width="15%" class="2-td2-left">
					  <div align="left">
						<html:text property="kkzzyf" size="10" maxlength="2"/>
						<span>MM�磺12<FONT SIZE="" COLOR="#FF0000">*</FONT></span>
					  </div>
					  </td>
					</tr>
				</table>
				<br>
			<%
			if(dhcxmodel!=null){
			%>
			<DIV style="FONT-SIZE: 13px; HEIGHT: 15px" 
            align=center><STRONG>��˰�˻�����Ϣ</STRONG></DIV>
            <DIV id="">
            <TABLE class=table-99 id=table_LIST cellSpacing=0 border=0>
              <TBODY>
              <TR>
                <TD class=2-td1-left width="20%">��˰������</TD>
                <TD class=2-td1-left width="14%">���������ˣ������ˣ�</TD>
				<TD class=2-td1-left width="13%">�̶��绰</TD>
                <TD class=2-td1-left width="13%">�ƶ��绰</TD>
                <TD class=2-td1-left width="20%">ע���ַ</TD>
                <TD class=2-td1-center width="20%">��Ӫ��ַ</TD>
              <TR id=ROW_LIST>
               <bean:define id="model" name="dhkscxForm" property="pkjbsjModel" />
                <TD class=2-td2-left>&nbsp;<bean:write name='model' property='nsrmc'/></TD>
                <TD class=2-td2-left>&nbsp;<bean:write name='model' property='zrr'/></TD>
                <TD class=2-td2-left>&nbsp;<bean:write name='model' property='gddh'/></TD>
                <TD class=2-td2-left>&nbsp;<bean:write name='model' property='yddh'/></TD>
                <TD class=2-td2-left>&nbsp;<bean:write name='model' property='zcdz'/></TD>
                <TD class=2-td2-center>&nbsp;<bean:write name='model' property='jydz'/></TD>
			  </TBODY></TABLE></DIV>
			  <br>
			<%
			}
			%>
			
			<%
            if(plkkdhcxModelList!=null){
         
            %>	
		
            <DIV style="FONT-SIZE: 13px; HEIGHT: 15px" 
            align=center><STRONG>��ѯ����б�</STRONG></DIV>
            <DIV id="">
            <TABLE class=table-99 id=table_LIST cellSpacing=0 border=0>
              <TBODY>
              <TR>
                <TD class=2-td1-left width="5%">���</TD>
                <TD class=2-td1-left width="15%">˰Ʊ����</TD>
				<TD class=2-td1-left width="15%">˰��</TD>
                <TD class=2-td1-left width="15%">ʵ�ɽ��</TD>
                <TD class=2-td1-left width="15%">�ۿ�ʱ��</TD>
                <TD class=2-td1-left width="15%">�ۿ�״̬</TD>
                <TD class=2-td1-center width="20%">�ۿ�������</TD></TR>
              <logic:iterate id="item" name="dhkscxForm" property="plkkdhcxlist" type="com.ttsoft.bjtax.smsb.lwpk.model.PLKKDHCXModel" indexId="indexid">
					<tr>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<%=indexid.intValue()+1%></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='sphm'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='szmc'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='sjje'/></td>	
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='kksj'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='kkzt'/></td>
						<td class="2-td2-center" align="center" nowrap>&nbsp;<bean:write name='item' property='kksbyy'/></td>

					</tr>
			</logic:iterate>
			  </TBODY></TABLE>
  
			  </DIV>	
			
				<TABLE class="table-99" align="center">
				<TR class="black9">
				<TD>
				<div  align="left">
					<!--��ҳ���ܿ�ʼ-->
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="ǰҳ" alt="ǰҳ" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="��ҳ" alt="��ҳ" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="���һҳ" alt="���һҳ" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<!--��ҳ���ܿ�ʼ-->
				</div>
				</TD>
				<td align="right">
				 <DIV class=black9 id=layerView align=right><STRONG>������λ��Ϊ��<SPAN 
            class=tishi><bean:write name="dhkscxForm" property="nextPage"/></SPAN>ҳ����<SPAN class=tishi><bean:write name="dhkscxForm" property="totalpage"/></SPAN>ҳ��<SPAN 
            class=tishi><bean:write name="dhkscxForm" property="zts"/></SPAN>����¼��</STRONG></DIV>
				
				</td>
				</TR>
				</TABLE>
			 <%
			  }
              %>
				<br>
				<table width="100%" border="0" cellpadding="0" cellspacing="4">
					<tr valign="bottom">
						<td width="50%" align="right">
							<input name="toexcel" type="image" accesskey="s"  onClick="doToExcelSubmit();return false;"  onMouseOver="MM_swapImage('toexcel1','','<%=static_contextpath%>images/b-bcdexcel2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="���浽Excel" id="toexcel1" src="<%=static_contextpath%>images/b-bcdexcel1.jpg" width="100" height="22" >
						</td>
                        <td width="50%" align="left">	
							<input type="image" accesskey="c"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22">
						</td>
                        
                      </tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
<%@ include file="./include/footer.jsp"%>
    </td>
  </tr>
</table>
</html:form>
</body>
</html:html>