<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web.QyqssdsBaForm" %>

<html>
<head>
<title>�л����񹲺͹���ҵ��������˰������</title>
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
		com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web.QyqssdsBaForm form = (com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web.QyqssdsBaForm) request
				.getAttribute("qyqssdsBaForm2014");
	//�������� 0:���ϣ�1������
	String sqlx = form.getSqlx();
	//������˱�ʶ
	String bashbs = form.getBaShztbs();
	//�걨��˱�ʶ
	String sbshbs = form.getSbShztbs();
	%>
	<script type="text/javascript" language="JavaScript">

function doShow(){
	 //���水ť
	 var baocun = document.getElementById("baocundiv");
	 //�������밴ť
	 var jieshoushenqing = document.getElementById("jieshoushenqingdiv");
	 //�ܾ����밴ť
	 var jujueshenqing = document.getElementById("jujueshenqingdiv");
	 //ɾ����ť
	 var shanchu = document.getElementById("shanchudiv");
	 //������ť
	 var chexiao = document.getElementById("chexiaodiv");
	 //��ѯ��ť
	 var chaxun = document.getElementById("chaxun");

	 //������˱�ʶ
	 var shbs="<%=form.getBaShztbs()==null ?"0":form.getBaShztbs()%>";
	 //�걨��˱�ʶ
	 var sbshbs ="<%=form.getSbShztbs()==null?"0":form.getSbShztbs()%>";
	 var sqlx ="<%=form.getSqlx()==null?"2":form.getSqlx()%>";
	 //�������� 13��ˣ�14�鿴��15���
	 var czlx ="<%=form.getCzlx()%>";
	 var qsllry=document.getElementById("qsllry");
	 var lxdh=document.getElementById("lxdh");
	 chaxun.style.display="none";
	 //����ģ��Ĳ����޸ģ������水ť������
	baocun.style.display="none";
	chexiao.style.display="none";
	 if(sqlx=="0"){
	 	 qsllry.disabled =true;
		 lxdh.disabled =true;
		 for(var i=0;i<2;i++){
		 	document.getElementsByName("jyqxjm")[i].disabled=true;
			document.getElementsByName("gdjyjs")[i].disabled=true;
			document.getElementsByName("yfdxgb")[i].disabled=true;
			document.getElementsByName("yfxgpc")[i].disabled=true;
			document.getElementsByName("yfgdqs")[i].disabled=true;
		    document.getElementsByName("qtyy")[i].disabled=true;
		 }
		 if(shbs=="2" || shbs=="3"){
			 //��˳ɹ���ɾ���;ܾ�����ͽ������밴ť������
			 jieshoushenqing.style.display="none";
			// shanchu.style.display="none";
			 jujueshenqing.style.display="none";
		 }
		 
	 }else if(sqlx=="1"){
		 //�����걨����Ҫ��������;ܾ����밴ť
		 jieshoushenqing.style.display="none";
		 jujueshenqing.style.display="none";
		 if(shbs=="2"){
		 	 qsllry.disabled =true;
			 lxdh.disabled =true;
		 for(var i=0;i<2;i++){
		 	document.getElementsByName("jyqxjm")[i].disabled=true;
			document.getElementsByName("gdjyjs")[i].disabled=true;
			document.getElementsByName("yfdxgb")[i].disabled=true;
			document.getElementsByName("yfxgpc")[i].disabled=true;
			document.getElementsByName("yfgdqs")[i].disabled=true;
		    document.getElementsByName("qtyy")[i].disabled=true;
		 }
			 //��˳ɹ��ı����ɾ����ť������
			 baocun.style.display="none";
			 //shanchu.style.display="none";
			 //�걨��˱�ʶΪ�ɹ��Ļ������ܳ���
			 if(sbshbs=="2"){
				 chexiao.style.display="none";
			 }
		 }
		 if(shbs=="4"){
			 chexiao.style.display="none";
		 }
	 }//zhangj added start

	 	//jujueshenqing.style.display="none";	 
	    if(czlx=="15"){
	     shanchu.style.display="none";
	    }
	    
	   if("2"==shbs){
	   		if("true"==document.all.saveSuccess.value){
	   			doPrintHz();	   			
	   		}	
	   		document.all.saveSuccess.value="false";   		
	   }else{
	   		document.getElementById("printHz").style.display="none";
	   }
	 //zhangj added end
}

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
  var sknd;
  jsjdm = document.forms[0].jsjdm.value;
  sknd = document.forms[0].sknd.value;

  if(jsjdm=="" ){

    return false;
  
  }else{
    doQuery();  
  }
	
}
//���������������޸������½��в�ѯ  ���޸Ĳ�ѯ��־
//add by wangcy  2013-12-06
function doChangeQuery(){
	document.forms[0].isQuery.value="0";
}

//��Ӧ���������Ļس���ѯ
function jsjdmQuery(){
	if(event.keyCode==13) doQuery();
}

//����
function doAccept()
{  
  
  var jsjdm;
  var nsrmc;
  jsjdm = document.forms[0].jsjdm.value;
  nsrmc = document.forms[0].nsrmc.value;
  var shbs="<%=form.getBaShztbs()==null ?"0":form.getBaShztbs()%>";
  var qsllry=document.forms[0].qsllry.value;
  var lxdh=document.forms[0].lxdh.value;
	if("2"==shbs){
	alert("����ҵ����ҵ��������˰���������ͨ���������ظ���ˣ�");
	}else if(""==qsllry || qsllry==null){
	alert("�����˻�������������Ա����Ϊ�գ�");
	}else if(""==lxdh || lxdh==null){
	alert("��ϵ�绰����Ϊ�գ�");
	}else if(jsjdm==""){
    alert("����д��������룬ȷ����Ϣ������ٽ��б��棡");
    document.forms[0].jsjdm.focus();
		document.forms[0].jsjdm.select();
    return false;
 	 }else{
	   var jyqxjm = document.getElementsByName("jyqxjm")[0];
		var gdjyjs = document.getElementsByName("gdjyjs")[0];
		var yfdxgb = document.getElementsByName("yfdxgb")[0];
		var yfxgpc = document.getElementsByName("yfxgpc")[0];
		var yfgdqs = document.getElementsByName("yfgdqs")[0];
		var qtyy = document.getElementsByName("qtyy")[0];
		if(jyqxjm.checked==false&&gdjyjs.checked==false&&yfdxgb.checked==false&&yfxgpc.checked==false&&yfgdqs.checked==false&&qtyy.checked==false){
			alert("��ҵ������Ŀ��Ϣ��ѡһ����");
			return false;
		}
		if(nsrmc==""){
			alert("����˰�˵Ļ�����Ϣ��û���������ѯ����������Ϣ���ٽ��б��棡\n�������������á��س�����������ѯ��ť��ִ�в�ѯ��");
			document.forms[0].jsjdm.focus();
			return false;
		}
		var isQuery=document.forms[0].isQuery.value;
	  	if(isQuery!="1"){
	  		alert("����������˰����Ƚ������޸�,������ִ�в�ѯ������");
	  		return false;
	  	}
		////�ж����е�ѡ��ť���Ƿ��б�ѡ�еģ������û��ѡ�У���ô�Ͳ��ܱ���
	  	if(confirm("����ʵ��д������Ϣ�������д������ܻᵼ��ĳЩ�����ظ�¼��")){
	  		doSubmit();
	    }
    return false;
    
  }
}
	function doSubmit()
	{
		
		document.all.saveSuccess.value="true";
		doSubmitForm("Accept");
		
		
	}
	//��������
	/*
	function doAccept(){
		
		doSubmitForm("Accept");
	}*/
	//�ܾ�����
	function doRefuse(){
	var shbs="<%=form.getBaShztbs()==null ?"0":form.getBaShztbs()%>";
	if("1"==shbs){
	doSubmitForm("Refuse");
	}else{
	alert("����ҵ����ҵ��������˰���������ύδ���״̬������ִ����˲��أ�");
	}
		
	}
	//ɾ��
	function doDelete(){
	var shbs="<%=form.getBaShztbs()==null ?"0":form.getBaShztbs()%>";
	if(""==shbs){
		alert("����ҵ����ҵ��������˰����δ�ύ���������ϣ�");
	}else{
		doSubmitForm("Delete");
	}
		
	}
	//����
	function doCancle(){
		doSubmitForm("Cancle");
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
function doPrintHz(){
	document.forms[0].target="_blank";	
	doSubmitForm("PrintHz");
	document.forms[0].target="";
}


/**	//�˳�
function tuichu(){
	if(returnStr==null || returnStr==""){
		returnStr="zhsbAction.do";
	}
	//��������ۺ��걨�����걨����ҳ�棬���˳����ۺ��걨ҳ��
	if(document.all.iszhsb && document.all.iszhsb.value=='1')
		returnStr="zhsbAction.do?actionType=Show";
	window.location=returnStr;
}**/
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="doShow()">
	<%@ include file="../../include/header.jsp"%>

	<br>



	<html:form action="/webapp/smsb/qyqssds/2014/qyqssdsBaAction2014.do" method="post">
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
		<html:hidden property="saveSuccess" />
		<table width="96%" align="center" cellspacing="0" class="table-99">
			<tr>
				<td class="1-td1" colspan="7">�л����񹲺͹���ҵ��������˰������</td>
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
											<td class="2-td2-t-left" nowrap><div align="center">&nbsp;<ttsoft:write name="qyqssdsBaForm2014" property="jsjdm"  scope="request" /></div>
											</td>
											<td class="2-td2-t-left" nowrap>��˰��ʶ���&nbsp;</td>
											<td class="2-td2-t-center" nowrap>
												<div align="center">
													&nbsp;&nbsp;
													<ttsoft:write name="qyqssdsBaForm2014" property="nsrsbh"
														scope="request" />
												</div></td>
										</tr>
										
										<tr>
											<td class="2-td2-left" nowrap>������ʼ����&nbsp;</td>
											<td  class="2-td2-left" nowrap>
													<div align="center">
													&nbsp;&nbsp;
													<ttsoft:write name="qyqssdsBaForm2014" property="qsbaksrq"
														scope="request" />
												</div>
												<!-- <html:text property="qsbaksrq" onClick="WdatePicker()" ></html:text>-->
											</td>
											<td class="2-td2-left" nowrap>��˰������&nbsp;</td>
											<td class="2-td2-center" nowrap>
												<div align="center">
													&nbsp;&nbsp;
													<ttsoft:write name="qyqssdsBaForm2014" property="nsrmc"
														scope="request" />
												</div></td>
										</tr>
										
										<tr>
											<td class="2-td2-left" nowrap>�����˻�������������Ա&nbsp;</td>
											<td class="2-td2-left" nowrap><input type='text'
												name='qsllry' id='qsllry'
												value='<ttsoft:write name="qyqssdsBaForm2014" property="qsllry"  scope="request" />'
												size='20' tabindex='2'>
											</td>
											<td class="2-td2-left" nowrap>��ϵ�绰&nbsp;</td>
											<td class="2-td2-center" nowrap><input type='text'
												name='lxdh' id='lxdh'
												value='<ttsoft:write name="qyqssdsBaForm2014" property="lxdh"  scope="request" />'
												size='20' tabindex='2'>
											</td>
										</tr>
										
										<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">��ҵ�³̹涨�ľ�Ӫ���޽���&nbsp;</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
										<html:radio name="qyqssdsBaForm2014" property="jyqxjm" value="Y">��</html:radio>
										<html:radio name="qyqssdsBaForm2014" property="jyqxjm" value="N" >��</html:radio>
											 <!--<html:hidden property="jyqxjm" />
											 <input type="Radio" name="jyqxjm_show" id="jyqxjm_Y" value="Y"/>�� 
											 <input type="Radio" name="jyqxjm_show" id="jyqxjm_N" value="N" />�� 
											 -->
										</td>
										
									</tr>
									
									
	
									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">��ҵ�ɶ��ᡢ�ɶ��������ƻ��������ɢ&nbsp;</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
										<html:radio name="qyqssdsBaForm2014" property="gdjyjs" value="Y">��</html:radio>
										<html:radio name="qyqssdsBaForm2014" property="gdjyjs" value="N">��</html:radio>
											<!--<html:hidden property="gdjyjs" />
											<input type="Radio" name="gdjyjs_show" id="gdjyjs_Y" value="Y" />��
											<input type="Radio" name="gdjyjs_show" id="gdjyjs_N" value="N"/>��
											-->
										</td>
										
									</tr>

									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">��ҵ����������Ӫҵִ�ա�����رջ��߱�����&nbsp;</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
										<html:radio name="qyqssdsBaForm2014" property="yfdxgb" value="Y">��</html:radio>
										<html:radio name="qyqssdsBaForm2014" property="yfdxgb" value="N">��</html:radio>
											<!--<html:hidden property="yfdxgb" />
											<input type="Radio" name="yfdxgb_show" id="yfdxgb_Y" value="Y"/>��
											<input type="Radio" name="yfdxgb_show" id="yfdxgb_N" value="N"/>��
											-->
										</td>
										
									</tr>

									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">��ҵ������Ժ�������Խ�ɢ�������Ʋ�&nbsp;</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
										<html:radio name="qyqssdsBaForm2014" property="yfxgpc" value="Y">��</html:radio>
										<html:radio name="qyqssdsBaForm2014" property="yfxgpc" value="N">��</html:radio>
											<!--<html:hidden property="yfxgpc" />
											<input type="Radio" name="yfxgpc_show" id="yfxgpc_Y" value="Y"/>��
											<input type="Radio" name="yfxgpc_show" id="yfxgpc_N" value="N"/>��
											-->
										</td>
										
									</tr>

									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">�йط��ɡ���������涨����&nbsp;</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
										<html:radio name="qyqssdsBaForm2014" property="yfgdqs" value="Y">��</html:radio>
										<html:radio name="qyqssdsBaForm2014" property="yfgdqs" value="N">��</html:radio>
											<!--<html:hidden property="yfgdqs" />
											<input type="Radio" name="yfgdqs_show" id="yfgdqs_Y" value="Y"/>��
											<input type="Radio" name="yfgdqs_show" id="yfgdqs_N" value="N"/>��
											-->
										</td>
										
									</tr>

									<tr>
										<td colspan="2" class="2-td2-left" nowrap="nowrap" style="text-align:right">��ҵ������ԭ���ɢ���������&nbsp;</td>
										
										<td colspan="2" class="2-td2-center" nowrap="nowrap" style="text-align:left">
										<html:radio name="qyqssdsBaForm2014" property="qtyy" value="Y">��</html:radio>
										<html:radio name="qyqssdsBaForm2014" property="qtyy" value="N">��</html:radio>
											<!--<html:hidden property="qtyy" />
											<input type="Radio" name="qtyy_show" id="qtyy_Y" value="Y"/>��
											<input type="Radio" name="qtyy_show" id="qtyy_N" value="N"/>��
											-->
										</td>
										
									</tr>

									<tr>
										<td  class="2-td2-left" nowrap="nowrap">���״̬��ʾ&nbsp;</td>
										
										<td colspan="3" class="2-td2-center" nowrap="nowrap">
											<div align="left" style="color:red;">
												&nbsp;<ttsoft:write name="qyqssdsBaForm2014" property="baShztMessage" scope="request" />
											</div>
										</td>
										
									</tr>
										
										
									</table>
								</div></TD>
						</TR>
						<TR class="black9" align="center">
							<TD align="center"><br>
							<div  style="overflow:hidden; display:block; clear:both; text-align:center; ">
								<div  style="display:inline-block; clear:both;margin:0 auto; text-align:center;">
								<span id="printHz">
									&nbsp;&nbsp;&nbsp;&nbsp; <img onclick="doPrintHz()"
										onMouseOver="MM_swapImage('printHz','','<%=static_contextpath%>images/dayin2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" value="��ӡ��ִ" id="printHz"
										src="<%=static_contextpath%>images/dayin1.jpg" width="79"
										height="22" style="cursor:hand" /> &nbsp;&nbsp;&nbsp;&nbsp; 
								</span>
								<span id="chaxun">
									&nbsp;&nbsp;&nbsp;&nbsp; <img onclick="doQuery();return false;"
										onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)"
										onMouseOut="MM_swapImgRestore()" value="��ѯ" id="chaxun"
										src="<%=static_contextpath%>images/cx-q1.jpg" width="79"
										height="22" style="cursor:hand" /> &nbsp;&nbsp;&nbsp;&nbsp; 
										</span>
										<span id="baocundiv" >
										<img onclick="doSave();return false;"
										onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" value="����" id="baocun"
										src="<%=static_contextpath%>images/bc-s1.jpg" width="79"
										height="22" style="cursor:hand" />&nbsp;&nbsp;&nbsp;&nbsp; 
										</span> 
										<span id="jieshoushenqingdiv" >
										<a style="cursor:hand" onClick="doAccept();return false;" onMouseOut="MM_swapImgRestore()"
				                        onMouseOver="MM_swapImage('jishoushenqing','','../../../images/b_shtg2.jpg',1)">
				                        <img src="../../../images/b_shtg1.jpg" name="jishoushenqing" width="79" height="22" border="0" id="jishoushenqing">
				                        </a>
				                        &nbsp;&nbsp;&nbsp;&nbsp;
				                        </span>
				                        <span id="jujueshenqingdiv" >
				                        <a style="cursor:hand" onClick="doRefuse();return false;" onMouseOut="MM_swapImgRestore()"
				                        onMouseOver="MM_swapImage('jujueshenqing','','../../../images/b_shbh2.jpg',1)">
				                        <img src="../../../images/b_shbh1.jpg" name="jujueshenqing" width="79" height="22" border="0" id="jujueshenqing">
				                        </a>
				                        &nbsp;&nbsp;&nbsp;&nbsp;
										</span>
										<!-- ɾ����ť�����ϰ�ťʹ�� -->
										<span id="shanchudiv" >
										<a style="cursor:hand" onClick="doDelete()" onMouseOut="MM_swapImgRestore()"
				                        onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/b-zf2.jpg',1)">
				                        <img src="<%=static_contextpath%>images/b-zf1.jpg" name="shanchu" width="79" height="22" border="0" id="shanchu">
				                        </a>
										&nbsp;&nbsp;&nbsp;&nbsp;
										</span>
										<span id="chexiaodiv" >
										<a style="cursor:hand" onClick="doCancle()" onMouseOut="MM_swapImgRestore()"
				                        onMouseOver="MM_swapImage('chexiao','','<%=static_contextpath%>images/b-cx2.jpg',1)">
				                        <img src="<%=static_contextpath%>images/b-cx1.jpg" name="chexiao" width="79" height="22" border="0" id="chexiao">
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
