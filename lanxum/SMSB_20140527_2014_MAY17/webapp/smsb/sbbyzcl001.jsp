<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%System.out.println("Page Starting.....");%>
<html:html>
<head>
<html:base/>
<title>�걨��ⲻһ�´��� </title>
	<link href="../css/text.css" rel="stylesheet" type="text/css">
	<link href="../css/top-box.css" rel="stylesheet" type="text/css">
	<link href="css/beijing.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="../js/treatImage.js"></script>
	<script language="JavaScript" src="../js/smsb_common.js"></script>
	<script language="JavaScript" src="../js/DTable.js"></script>
	<script language="JavaScript" src="../js/InputSelect.js"></script>
	<script language="JavaScript" src="../js/function.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
 <%@ include file="./include/header.jsp"%>
<script language="javascript" src="<%=static_contextpath%>js/inputchecker.js"></script>
<html:form action="/webapp/smsb/sbbyzclAction.do" method="post" >
	<html:hidden property="actionType" />
	<html:hidden property="parSbbh" />
	<html:hidden property="parJsjdm" />
	<html:hidden property="nsrmc" />
	<html:hidden property="nsrzt" />
	<html:hidden property="swjgzzjgdm" />
	<html:hidden property="swjgzzjgmc" />
	<html:hidden property="zgswjgzzjgdm" />
	<html:hidden property="zgswjgzzjgmc" />
	<html:hidden property="rq" />
	<html:hidden property="opeFlag" />
	<html:hidden property="qsqrq" />
	<table width="96%" align="center" cellspacing="0" class="table-99">
		<tr>
			<td class="1-td1">�걨��ⲻһ�´��� </td>
		</tr>
		<tr>
			<td class="1-td2">
				<br>
				<table cellspacing="0" class="table-99">
					<tr class="black9">
						<td align="left" nowrap>
							<div align="left">��ǰ���ڣ�<bean:write name="sbbyzclForm" property="rq"/></div>
						</td>
						<td align="right" nowrap>
							<div align="right">˰�������֯������<bean:write name="sbbyzclForm" property="swjgzzjgmc"/></div>
						</td>
					</tr>
				</table>
				<br>
				<table  cellspacing="0" class="table-99">
					<tr> 
					  <td width="20%" class="2-td2-t-left" nowrap>���</td>
					  <td width="30%" class="2-td2-t-left" nowrap> 
						<html:text property="queryNd" size="10" maxlength="4"/>
					  </td>
					  <td width="20%" class="2-td2-t-left" nowrap>���������</td>
					  <td width="30%" class="2-td2-t-center" nowrap>
						<html:text property="queryJsjdm" onchange="doit123('QueryDj'); return false;" size="10" maxlength="8"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden"  onClick="doit123('QueryDj'); return false;"  onMouseOver="MM_swapImage('QueryC1','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�Ǽǲ�ѯ" alt="�Ǽǲ�ѯ" id="QueryC1" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="21">&nbsp;
					  </td>
					</tr>
					<tr> 
					  <td width="20%" class="2-td2-left" nowrap>��˰��״̬</td>
					  <td width="30%" class="2-td2-left" nowrap> 
						<bean:write name="sbbyzclForm" property="nsrzt"/>&nbsp;
					  </td>
					  <td width="20%" class="2-td2-left" nowrap>��˰������</td>
					  <td width="30%" class="2-td2-center" nowrap>
						<bean:write name="sbbyzclForm" property="nsrmc"/>&nbsp;
					  </td>
					</tr>
					<tr> 
					  <td width="20%" class="2-td2-left" nowrap>��ѯ��ʼ����</td>
					  <td width="30%" class="2-td2-left" nowrap> 
						<html:text property="queryKsrq" size="10" maxlength="8"/>(yyyymmdd)
					  </td>
					  <td width="20%" class="2-td2-left" nowrap>��ѯ��������</td>
					  <td width="30%" class="2-td2-center" nowrap>
						<html:text property="queryJsrq" size="10" maxlength="8"/>(yyyymmdd)
					  </td>
					</tr>
					<tr> 
					  <td width="20%" class="2-td2-left" nowrap colspan='4'>
						<logic:notEqual name="sbbyzclForm" property="opeFlag" value="0">
						��������ť��ѯ�����걨��⼰δ������ݣ�&nbsp;&nbsp;&nbsp;&nbsp;<input type="image"  onClick="doit123('QuerySb'); return false;"  onMouseOver="MM_swapImage('QueryC','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�걨��ѯ" alt="�걨��ѯ" id="QueryC" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="21">&nbsp;
						</logic:notEqual>
					  </td>
					</tr>
				</table>
				<br>
				<table  cellspacing="0" class="table-99">
					<tr> 
					  <td class="2-td2-t-left" width="5%" nowrap>&nbsp;</td>
					  <td class="2-td2-t-left" width="13%" nowrap>�걨���</td>
					  <td class="2-td2-t-left" width="8%" nowrap>˰������</td>
					  <td class="2-td2-t-left" width="8%" nowrap>�걨����</td>
					  <td class="2-td2-t-left" width="10%" nowrap>ʵ�ɽ��ϼ�</td>
					  <td class="2-td2-t-left" width="10%" nowrap>�����ϼ�</td>
					  <td class="2-td2-t-left" width="8%" nowrap>���</td>
					  <td class="2-td2-t-left" width="10%" nowrap>&nbsp;</td>
					  <td class="2-td2-t-center" width="28%" nowrap>Ƿ˰ȷ������</td>
					</tr>
				</table>
				<% int indexId=1;%>
					<logic:iterate indexId="index" id="item1" name="sbbyzclForm" property="dataList" >
				<br>
				<table  cellspacing="0" class="table-99">
					<tr > 
					  <td class="2-td2-t-left" width="5%" nowrap><a href="sbbyzclAction.do?actionType=Detail&sbbh=<bean:write name='item1' property='sbbh'/>&jsjdm=<bean:write name='sbbyzclForm' property='queryJsjdm'/>&ksrq=<bean:write name='sbbyzclForm' property='queryKsrq'/>&jsrq=<bean:write name='sbbyzclForm' property='queryJsrq'/>&nd=<bean:write name='sbbyzclForm' property='queryNd'/>" target='_blank'>��ӡ</a></td>
					  <td onmouseover="this.style.cursor='hand'" onmouseout="this.style.cursor='default'" onclick="doChangeMx('SBBYZCL_MX_<bean:write name='item1' property='sbbh'/>')" class="2-td2-t-left" width="13%"  nowrap><bean:write name='item1' property='sbbh'/>&nbsp;</td>
					  <td class="2-td2-t-left" width="8%" nowrap><bean:write name='item1' property='sklx'/>&nbsp;</td>
					  <td class="2-td2-t-left" width="8%" nowrap><bean:write name='item1' property='sbrq'/>&nbsp;</td>
					  <td class="2-td2-t-left" width="10%" nowrap><bean:write name='item1' property='sjje'/>&nbsp;</td>
					  <td class="2-td2-t-left" width="10%" nowrap><bean:write name='item1' property='rkje'/>&nbsp;</td>
					  <td class="2-td2-t-left" width="8%" nowrap><bean:write name='item1' property='ce'/>&nbsp;</td>
					  <td class="2-td2-t-left" width="10%"  nowrap>&nbsp;<input onmouseover="this.style.cursor='hand'" onmouseout="this.style.cursor='default'" type="button" value="�ظ��걨" name="�ظ��걨"  onclick="return doMoveB('<bean:write name='item1' property='sbbh'/>');">&nbsp;</td>					  	
					  <td class="2-td2-t-center" width="28%" nowrap><input type="text" id="qsqrq<%=indexId%>" size="10" maxlength="8">(yyyymmdd)&nbsp;<input onmouseover="this.style.cursor='hand'" onmouseout="this.style.cursor='default'" type="button" value="תǷ˰" name="תǷ˰" onclick="return doMoveA('<bean:write name='item1' property='sbbh'/>','<bean:write name='item1' property='xjrq'/>','<bean:write name='sbbyzclForm' property='rq'/>',<%=indexId++%>);">&nbsp;</td>
					</tr>
				</table>
					<span id="SBBYZCL_MX_<bean:write name='item1' property='sbbh'/>" style="display:none">
							<table width="90%" cellspacing="0" >
								<tr>
									<td class="2-td2-t-left" nowrap>�ɿ�ƾ֤��</td>
									<td class="2-td2-t-left" nowrap>˰��</td>
									<td class="2-td2-t-left" nowrap>˰��������ʼ����</td>
									<td class="2-td2-t-left" nowrap>˰��������������</td>
									<td class="2-td2-t-left" nowrap>��ҳ����</td>
									<td class="2-td2-t-left" nowrap>ʵ�ɽ��</td>
									<td class="2-td2-t-left" nowrap>�����</td>
									<td class="2-td2-t-center" nowrap>���</td>
								</tr>
								<logic:iterate id="item2" name="item1" property="mxList" >
								<tr>
									<td class="2-td2-left" nowrap><bean:write name='item2' property='jkpzh'/>&nbsp;</td>
									<td class="2-td2-left" nowrap><bean:write name='item2' property='szmc'/>&nbsp;</td>
									<td class="2-td2-left" nowrap><bean:write name='item2' property='skssksrq'/>&nbsp;</td>
									<td class="2-td2-left" nowrap><bean:write name='item2' property='skssjsrq'/>&nbsp;</td>
									<td class="2-td2-left" nowrap><bean:write name='item2' property='zyrq'/>&nbsp;</td>
									<td class="2-td2-left" nowrap><bean:write name='item2' property='sjje'/>&nbsp;</td>
									<td class="2-td2-left" nowrap><bean:write name='item2' property='rkje'/>&nbsp;</td>
									<td class="2-td2-center" nowrap><bean:write name='item2' property='ce'/>&nbsp;</td>
								</tr>
								</logic:iterate>
							</table>
					</span>
					</logic:iterate>
				<br>
				<table width="94%" border="0" cellpadding="0" cellspacing="4">
					<tr valign="bottom">
						<td width="30%"> </td>
                        <td width="40%" align="center">	
							<input type="image"  onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="21">
						</td>
                        <td width="30%"></td>
                      </tr>
				</table>	
        <table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
           <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
             <td height="47" >
             	<p>
             	  &nbsp;&nbsp;&nbsp;&nbsp;������ֻ�����걨����ⲻһ�²�ѯ��˰��������ʾΪ�������������Բ鲹˰���͡��Ĵ���ɡ��������<br>             
              </p>
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
<script language="JavaScript">

	function doit123(ope){
		//alert("ope="+ope);
		if(document.forms[0].queryNd.value==""){
			alert("��ѯ�������걨��Ȳ���Ϊ�գ�");
			document.all.queryNd.focus();
			return false;
		}else if(document.forms[0].queryJsjdm.value==""){
			alert("��ѯ�����м�������벻��Ϊ�գ�");
			document.all.queryJsjdm.focus();
			return false;
		}else if(document.forms[0].queryKsrq.value==""||document.forms[0].queryJsrq.value==""){
			alert("��ѯ���������ڲ���Ϊ�գ�");
			document.all.queryKsrq.focus();
			return false;
		}
		//�ж���ʽ
		if(ope=="QueryDj"){//����ǲ�ѯ�Ǽ���Ϣ��ҪУ����������
			if(checkJsjdm(document.all.queryJsjdm.value)!=true){
				alert("��������ȷ�ļ�������룡");
				document.all.queryJsjdm.focus();
				return false;
			}
		}else if(ope=="QuerySb"){//����ǲ�ѯ�걨����Ҫ��������롢У����Ⱥ��������Ƿ���ȷ
			if(checkJsjdm(document.all.queryJsjdm.value)!=true){
				alert("��������ȷ�ļ�������룡");
				document.all.queryJsjdm.focus();
				return false;
			}else if(!isFullDate(document.all.queryNd.value,1,false,true)){
				//alert("��������ȷ����ȣ�");
				document.all.queryNd.focus();
				return false;
			}else if(!isFullDate(document.all.queryKsrq.value,0,false,true)){
				//alert("��������ȷ�Ĳ�ѯ��ʼ���ڣ�");
				document.all.queryKsrq.focus();
				return false;
			}else if(!isFullDate(document.all.queryJsrq.value,0,false,true)){
				//alert("��������ȷ�Ĳ�ѯ�������ڣ�");
				document.all.queryJsrq.focus();
				return false;
			}
		}else{//��������У��������������
			if(checkJsjdm(document.all.queryJsjdm.value)!=true){
				alert("��������ȷ�ļ�������룡");
				document.all.queryJsjdm.focus();
				return false;
			}else if(!isFullDate(document.all.queryNd.value,1,false,true)){
				//alert("��������ȷ����ȣ�");
				document.all.queryNd.focus();
				return false;
			}
		}
		//return false;
		doSubmitForm(ope);
	}

	function doChangeMx(mxid){
		var mx=document.getElementById(mxid);
		if(mx.style.display==""){
			mx.style.display="none";
		}else{
			mx.style.display="";
		}

	}

	function doMoveA(sbbh,xjrq,rq,num){
		
		var re = /-/g; 
		var rq= rq.replace(re,""); 
 
		var l1= document.getElementsByName("qsqrq"+num)[0].value;
		var l2=xjrq;
		var l3=rq;


		if(document.getElementsByName("qsqrq"+num)[0].value==""){
			alert("תǷ˰����ʱǷ˰ȷ�����ڲ���Ϊ�գ�");
			document.getElementsByName("qsqrq"+num)[0].focus();
			return false;
		}
		if(!isFullDate(document.getElementsByName("qsqrq"+num)[0].value,0,false,true)){
				document.getElementsByName("qsqrq"+num)[0].focus();
				return false;
			}
		if(l1<=l2||l1>l3) 
		{			
			alert("Ƿ˰ȷ�����ڱ������˰���޽�������С�ڵ��ڵ�ǰ����");
			return false;
		
  	}else{
		if(!confirm("��ȷ�Ͻ��걨���Ϊ"+sbbh+"�ĸñ��걨��תǷ˰����")){
			//alert("ȡ������");
			return false;
		}else{
			document.forms[0].parSbbh.value=sbbh;
			document.forms[0].qsqrq.value=document.getElementsByName("qsqrq"+num)[0].value;
			doit123("MoveA");
		}
	}
}
	function doMoveB(sbbh){
		if(!confirm("��ȷ�Ͻ��걨���Ϊ"+sbbh+"�ĸñ��걨���ظ��걨����")){
			//alert("ȡ������");
			return false;
		}else{
			document.forms[0].parSbbh.value=sbbh;
			doit123("MoveB");
		}
	}

</script>

</body>
</html:html>

<%System.out.println("Page End.....");%>