<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.*" %>

<html>
<head>
<title>�˶��걨��Ϣ</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../../js/treatImage.js"></script>
<script language=JavaScript src="../../../js/compute.js"></script>
<script language=JavaScript src="../../../js/function.js"></script>
<script language=JavaScript src="../../../js/smsb_common.js"></script>
<script language=JavaScript src="../../../js/Bolan.js"></script>
<script language=JavaScript src="../../../js/MathString.js"></script>
<script language=JavaScript src="../../../js/Stack.js"></script>
<script language=JavaScript src="../../../js/reader.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" >
<%@ include file="../../include/header.jsp"%>
<br>
<%
			List mylist = (List) request.getSession(false)
			.getAttribute("qysdsbbjcList");
%>
<html:form action="/webapp/smsb/qysds/qysdsbbjc/qysdsBbjcAction.do" method="post">
	<html:hidden property="isQuery"/>
	<html:hidden property="actionType" />
	<html:hidden property="nsrsbhSubmit" id='nsrsbhSubmit'/>
	<html:hidden property="ssjjlxmcSubmit" id='ssjjlxmcSubmit' />
	<html:hidden property="nsrmcSubmit" id='nsrmcSubmit' />
	<html:hidden property="sshymcSubmit" id='sshymcSubmit' />
	<table width="96%" align="center" cellspacing="0" class="table-99">
		<tr>
			<td class="1-td1" colspan="7">�˶��걨��Ϣ</td>
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
						    <td class="2-td2-t-left" nowrap>���������</td>
							<td class="2-td2-t-left" nowrap><input type='text'
								name='jsjdm' id='jsjdm'
								value='<ttsoft:write name="qysdsBbjcForm" property="jsjdm"  scope="request" />'
								size='13' tabindex='2' onKeyDown="jsjdmQuery()">
							  <img onclick="doQuery();return false;"onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)" 
							  onMouseOut="MM_swapImgRestore()" value="��ѯ" id="tc1" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" style="cursor:hand"/>
							</td>
						    <td class="2-td2-t-left" nowrap>��˰��ʶ���</td>
							<td class="2-td2-t-center" nowrap="nowrap">
							<div align="left" id="nsrsbh">&nbsp;&nbsp;<ttsoft:write
								name="qysdsBbjcForm" property="nsrsbh" scope="request" />							
							</div>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>������������</td>
							<td class="2-td2-left" nowrap>
							    <div align="left" id="ssjjlxmc">&nbsp;&nbsp;<ttsoft:write
								name="qysdsBbjcForm" property="ssjjlxmc" scope="request" />
								</div>
							</td>
							<td class="2-td2-left" nowrap>��˰������</td>
							<td class="2-td2-center" nowrap>
							<div align="left" id="nsrmc">&nbsp;&nbsp;<ttsoft:write
								name="qysdsBbjcForm" property="nsrmc" scope="request" />							
							</div>
							</td>
						</tr>
						<tr>
						   <td class="2-td2-left" nowrap>������ҵ</td>
							<td class="2-td2-left" nowrap >
							    <div align="left" id="sshymc">&nbsp;&nbsp;<ttsoft:write
								name="qysdsBbjcForm" property="sshymc" scope="request" />
								</div>
							</td>
							<td class="2-td2-left"  nowrap>�걨ģ��</td>
							<td class="2-td2-center" nowrap>
							 <div align="center">
							 <select id="qysdssbfs" name="qysdssbfsdm" onchange="getJd()">
							        <option value=""> ��ѡ��</option>
							 <!-- ���������� -->
							 <%
							 	for(int i = 0;i<mylist.size();i++){
							 		SbzllxVo vo = (SbzllxVo)mylist.get(i);
							 		String dm = vo.getDm();
							 		String mc = vo.getMc();
							 		
							 %>
                  		    		 <option value="<%=dm %>"><%=mc %></option>
							 <%
							 	}
							  %>
							</select>
                  		    </div>  
                  		    </td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>�걨���</td>
							<td class="2-td2-left" nowrap>
							    <input type='text'
								name='sbnd' id='sbnd'
								value='<ttsoft:write name="qysdsBbjcForm" property="sbnd"  scope="request" />'
								size='13' tabindex='2'  >
							</td>
							<td class="2-td2-left"  nowrap>����</td>
							<td class="2-td2-center" nowrap>
							 <div align="center">
                  		    	<select id="jd" name="jddm" >
                  		    		  <option value=""> ��ѡ��</option>
                  		    		  <option value="1">��һ���� </option>  
                  		    		  <option value="2">�ڶ�����</option>     
                  		    		  <option value="3">��������</option>
                  		    		  <option value="4">���ļ���</option>              
                  		    	</select>
                  		     </div>
                  		    </td>
						</tr>
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD><br>
					<div align="center">
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <img onclick="nextStep();return false;"  onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="����" id="tc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="22" style="cursor:hand"/>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <img onclick="tuichu()"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand"/>
                  </div>
					</TD>
				</TR>
			</TABLE>
			</td>
		</tr>
	</table>
	<br>
	<div align="left">
					<font size="2">&nbsp;&nbsp;&nbsp;�Ҫ����˰������걨��ҵ����˰ʱ����˱�</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;�˵����</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1����˰�����ƣ��˰��Ǽ�֤������˰�˵�ȫ�ơ�</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2����˰���걨�ţ�</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3����ϵ�绰����д��˰�˵�λ��˰��Ա��ϵ�绰�����ֻ����룩��</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4�������������͡�������ҵ������˰��ǼǱ��е��й�������д��</font><br>
					</div>
	<br>
	<br>

	<%@ include file="../../include/footer.jsp"%>
</html:form>

</body>
</html>

<script type="text/javascript" language="JavaScript">
<% /*����*/ %>
function doQuery()
{	//У������ѯʱ�Ƿ������˼��������
	if(trim(document.forms[0].jsjdm.value) == ""){
		alert("��������˰��ҵ��������룡");
		document.forms[0].jsjdm.focus();
		return false;
	}
	doSubmitForm('Query');

}

<%/*���س�������*/%>
function jsjdmQuery(){
	if(event.keyCode==13){
	 doQuery();
	}
}


function nextStep(){
	//��֤�걨��ȣ����ܳ���Ŀǰ���ڵ���ȣ�
	var sbnd=document.forms[0].sbnd.value;
	var date=new Date();
	var year=date.getFullYear();
	document.forms[0].nsrsbhSubmit.value=document.getElementById("nsrsbh").innerText;
	//alert("��˰��ʶ���"+document.forms[0].nsrsbhSubmit.value);
	document.forms[0].ssjjlxmcSubmit.value=document.getElementById("ssjjlxmc").innerText;
	//alert("����������������"+document.forms[0].ssjjlxmcSubmit.value);
	document.forms[0].sshymcSubmit.value=document.getElementById("sshymc").innerText;
	//alert("������ҵ����"+document.forms[0].sshymcSubmit.value);
	document.forms[0].nsrmcSubmit.value=document.getElementById("nsrmc").innerText;
	//alert("��˰������"+document.forms[0].nsrmcSubmit.value);
	if(sbnd == ""){
		alert("�������걨��ȣ�");
		document.forms[0].sbnd.focus();
		return false;
	}else  if(sbnd>year){
	    alert("������걨��ȳ����˵�ǰ���ڣ����������룡����");
	    document.forms[0].sbnd.focus();
	    return false;
	}else if(sbnd<2006){
		alert("������걨��ȱ�����2006���Ժ�(����2006)������");
		document.forms[0].sbnd.focus();
		return false;
	}
	//��֤�Ƿ�ѡ�����շ�ʽ������Ϊ����ʱ����ѡ����Ӧ�ļ���
	var qysdssbfsdm=document.forms[0].qysdssbfsdm.value;
	if(qysdssbfsdm==""){
		alert("�걨��ʽ����Ϊ�գ���ѡ����Ӧ���걨��ʽ������");
		 document.forms[0].qysdssbfsdm.focus();
	     return false;
	 }
	
	 if(qysdssbfsdm=="04"||qysdssbfsdm=="05"||qysdssbfsdm=="06"){
	 	if(document.getElementById('jd').value==""){
	 		alert("��������Ӧ�ļ��ȣ�����");
	 		document.forms[0].jddm.focus();
	 		return false;
	 	}
	 	/*  //�жϼ�����ʱ���Ƿ�����ڸü����ڽ����걨
	 	if(sbnd==year){
			var jd=document.getElementById('jd').value;
			alert("++++++++++++���ȴ���"+jd);
			if(jd=="1"){
			  var upTime=sbnd+"0331";
			  alert("��ֹʱ��++++"+upTime);
			  if(upTime>date){
			  	alert("��һ���Ȼ�û��ɣ��ȸü�����ɺ�ſ��Խ����걨������");
			  	return false;
			  }
			}else if(jd=="2"){
			 var upTime=sbnd+"0630";
			  if(upTime>date){
			  	alert("�ڶ����Ȼ�û��ɣ��ȸü�����ɺ�ſ��Խ����걨������");
			  	return false;
			  }
			}else if(jd=="3"){
			 var upTime=sbnd+"0930";
			  if(upTime>date){
			  	alert("�������Ȼ�û��ɣ��ȸü�����ɺ�ſ��Խ����걨������");
			  	return false;
			  }
			}else if(jd=="4"){
			 var upTime=sbnd+"1231";
			 alert("��ֹʱ��4++++"+upTime);
			  if(upTime>date){
			  	alert("���ļ��Ȼ�û��ɣ��ȸü�����ɺ�ſ��Խ����걨������");
			  	return false;
			  }
			}
			
		}  */
	 }
	 //У������Ƿ�Ϊ��λ������
	 if(!isDigit(document.forms[0].sbnd.value)||document.forms[0].sbnd.value.length!=4){
		alert("��ȱ���Ϊ��λ���֣����������룡");
		document.forms[0].sbnd.focus();
		document.forms[0].sbnd.select();
		return false;
	}
	
	
	doSubmitForm('NextStep');

}
//�����걨��ʽ���Ƽ���
function getJd(){
	var qysdssbfsdm=document.forms[0].qysdssbfsdm.value;
	//alert("�걨��ʽ���룡����"+qysdssbfsdm);
	if(qysdssbfsdm=="01"||(qysdssbfsdm=="02")||(qysdssbfsdm=="03")||(qysdssbfsdm=="07")){
		document.getElementById('jd').disabled=true;
	}else if(qysdssbfsdm=="04"||qysdssbfsdm=="05"||qysdssbfsdm=="06"){
		document.getElementById('jd').disabled=false;
	}  
}

</script>
