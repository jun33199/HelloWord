<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxba.web.QyqssdsWxBaForm" %>

<html>
<head>
<title>�л����񹲺͹���ҵ����˰�������㱸����</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../../js/treatImage.js"></script>
<script language=JavaScript src="../../../js/compute.js"></script>
<script language=JavaScript src="../../../js/function.js"></script>
<script language=JavaScript src="../../../js/smsb_common.js"></script>
<script language=JavaScript src="../../../js/Bolan.js"></script>
<script language=JavaScript src="../../../js/MathString.js"></script>
<script language=JavaScript src="../../../js/Stack.js"></script>
<script language=JavaScript src="../../../js/reader.js"></script>
<script language="JavaScript" type="text/javascript" src="../../../js/My97DatePicker/WdatePicker.js"></script>


</head>
	<%
		com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxba.web.QyqssdsWxBaForm form = (com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxba.web.QyqssdsWxBaForm) request
				.getAttribute("qyqssdsWxBaForm2014");

	%>
	<script type="text/javascript" language="JavaScript">



<%/*����*/%>
function doQuery()
{
	if(trim(document.forms[0].jsjdm.value) == ""){
		alert("��������˰��ҵ��������룡");
		document.forms[0].jsjdm.focus();
		return false;
	}

	doSubmitForm('Query');
	return false;
}


//��Ӧ��������롢��ȵ��޸�
function doChange(){

  var jsjdm;
  jsjdm = document.forms[0].jsjdm.value;
  if(jsjdm=="" ){

    return false;
  
  }else{
    doQuery();  
  }
	
}


//��Ӧ���������Ļس���ѯ
function jsjdmQuery(){
	if(event.keyCode==13) doQuery();
}

//����
function doSave()
{  
  
  var jsjdm;
  var nsrmc;
  jsjdm = document.forms[0].jsjdm.value;
  nsrmc = document.forms[0].nsrmc.value;
	if(jsjdm==""){
    alert("����д��������룬ȷ����Ϣ������ٽ��б��棡");
    document.forms[0].jsjdm.focus();
		document.forms[0].jsjdm.select();
    return false;
 	}else{
		if(nsrmc==""){
			alert("����˰�˵Ļ�����Ϣ��û���������ѯ����������Ϣ���ٽ��б��棡\n�������������á��س�����������ѯ��ť��ִ�в�ѯ��");
			document.forms[0].jsjdm.focus();
			return false;
		}
	  	doSubmitForm("Save");
    return false;
    
  }
}

	//ɾ��
	function doDelete(){

		doSubmitForm("Delete");
		
	}


	

//����ת���걨����֮ǰ��������ʾ�Ƿ񱣴��Ѿ�¼������
function toSbzl(returnStr)
{
	window.location=returnStr;    

}
<%/*����*/%>
function doBack()
{
doSubmitForm("Back");
//window.history.back(-1); 
	
}
function doInit(){
	if(document.all.sfwxjxba[0].checked==false && document.all.sfwxjxba[1].checked==false){
		document.all.sfwxjxba[0].checked=true;
	}
}
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="doInit()" >
	<%@ include file="../../include/header.jsp"%>

	<br>



	<html:form action="/webapp/smsb/qyqssds/2014/qyqssdsWxBaAction2014.do" method="post" >
		<html:hidden property="actionType" />
		<html:hidden property="jsjdm" />
		<html:hidden property="nsrsbh" />
		<html:hidden property="nsrmc" />
		<html:hidden property="ssjjlxdm" />
		<html:hidden property="ssjjlxmc" />
		<html:hidden property="jydz" />
		<html:hidden property="sshydm" />
		<html:hidden property="sshymc" />
		<html:hidden property="cjr" />
		<html:hidden property="cjrq" />
		<html:hidden property="lrr" />
		<html:hidden property="lrrq" />
		<html:hidden property="xtjb" />
		<html:hidden property="bbmsf" />
		<html:hidden property="qsbaksrq" />
		<html:hidden property="iszhsb" />
		<html:hidden property="isQuery" />
		<html:hidden property="tbrq" />
		<table width="96%" align="center" cellspacing="0" class="table-99">
			<tr>
				<td class="1-td1" colspan="7">�л����񹲺͹���ҵ����˰�������㱸����</td>
			</tr>

			<tr>
				<td class="1-td2" colspan="7">
					<TABLE class="table-99" align="center">
						<TR>
							<TD>
								<div id="Layer2" style="position:static;">
									<table id="wrklistTable" border="1" cellspacing="0"
										class="table-99" align="center">

										<tr>
											<td class="2-td2-t-left" nowrap>���������&nbsp;</td>
											<td class="2-td2-t-left" nowrap><div align="center">&nbsp;<ttsoft:write name="qyqssdsWxBaForm2014" property="jsjdm"  scope="request" /></div>
											</td>
											<td class="2-td2-t-left" nowrap>��˰��ʶ���&nbsp;</td>
											<td class="2-td2-t-center" nowrap>
												<div align="center">
													&nbsp;&nbsp;
													<ttsoft:write name="qyqssdsWxBaForm2014" property="nsrsbh"
														scope="request" />
												</div></td>
										</tr>
										
										<tr>
											<td class="2-td2-left" nowrap>�������&nbsp;</td>
											<td  class="2-td2-left" nowrap>
													<div align="center">
													&nbsp;&nbsp;
													<ttsoft:write name="qyqssdsWxBaForm2014" property="tbrq"
														scope="request" />
												</div>
												<!-- <html:text property="qsbaksrq" onClick="WdatePicker()" ></html:text>-->
											</td>
											<td class="2-td2-left" nowrap>��˰������&nbsp;</td>
											<td class="2-td2-center" nowrap>
												<div align="center">
													&nbsp;&nbsp;
													<ttsoft:write name="qyqssdsWxBaForm2014" property="nsrmc"
														scope="request" />
												</div></td>
										</tr>
									<tr>
										<td  class="2-td2-left" nowrap="nowrap">�Ƿ���������㱸��&nbsp;</td>
										
										<td colspan="3" class="2-td2-center" nowrap="nowrap">
											<html:radio name="qyqssdsWxBaForm2014" property="sfwxjxba" value="0">��</html:radio>
											<html:radio name="qyqssdsWxBaForm2014" property="sfwxjxba" value="1">��</html:radio>
										</td>
										
									</tr>
										
										
									</table>
								</div></TD>
						</TR>
						<TR class="black9" align="center">
							<TD align="center"><br>
							<div  style="overflow:hidden; display:block; clear:both; text-align:center; ">
								<div  style="display:inline-block; clear:both;margin:0 auto; text-align:center;">
										<span id="baocundiv" >
										<img onclick="doSave();return false;"
										onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" value="����" id="baocun"
										src="<%=static_contextpath%>images/bc-s1.jpg" width="79"
										height="22" style="cursor:hand" />&nbsp;&nbsp;&nbsp;&nbsp; 
										</span> 
										<!-- ɾ����ť�����ϰ�ťʹ�� -->
										<span id="shanchudiv" >
										<a style="cursor:hand" onClick="doDelete()" onMouseOut="MM_swapImgRestore()"
				                        onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/shanchu2.jpg',1)">
				                        <img src="<%=static_contextpath%>images/shanchu1.jpg" name="shanchu" width="79" height="22" border="0" id="shanchu">
				                        </a>
										&nbsp;&nbsp;&nbsp;&nbsp;
										</span>
				                        <span >
										<img onclick="doBack()"
										onMouseOver="MM_swapImage('back','','<%=static_contextpath%>images/fanhui2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" value="�˳�" id="back"
										src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
										height="22" style="cursor:hand" /></span>
								</div>
								</div>
								</TD>
								
						</TR>
						<!--  
						<TR class="black9">
							<TD><br>
								<div align="left">
									<a
										href="javascript:toSbzl('/smsb/webapp/smsb/qyqssds/2014/qyqssdsMainAction2014.do?actionType=Show')">��ҵ��������˰�걨��(2014��)</a>
								</div></TD>
						</TR>
						-->
					</TABLE></td>
			</tr>
		</table>


		<%@ include file="../../include/footer.jsp"%>
	</html:form>

</body>
</html>
