<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="java.util.*"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.CqxxbForm"%>
<%@ page import="com.creationstar.bjtax.qsgl.BizLogic.vo.Cqxxb"%>
<%@ page
	import="com.creationstar.bjtax.qsgl.BizLogic.vo.CqxxImportErrbvo"%>
<HTML>
<HEAD>
<TITLE>��Ǩ��Ϣ���������Ϣά��</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>

<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>

<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js"
	type=text/JavaScript></SCRIPT>

<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"
	type=text/JavaScript></SCRIPT>

<script language="JavaScript" type="text/JavaScript"
	src="<%=static_file%>js/judge.js"></script>

<script language="JavaScript" type="text/JavaScript"
	src="<%=static_file%>js/calculate.js"></script>

<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>
<%CqxxbForm form = (CqxxbForm) session.getAttribute("cqxxbForm");
			HashMap szqxMap = form.getSzqxMap();
			com.ttsoft.common.model.UserData userdata = (com.ttsoft.common.model.UserData) session
					.getAttribute(com.ttsoft.common.util.SessionKey.USER_DATA);
			String qxdm = userdata.getSsdwdm().substring(0, 2);
%>


<script language=JavaScript type='text/JavaScript'>

function doSubmitThis(operationType){

  document.forms[0].operationType.value=operationType;
  
  
  if(operationType=="QueryErr" )
  {
	   if (document.forms[0].lrrqq.value != "" && !checkDate(document.forms[0].lrrqq.value))
	   {
	     alert("¼��ʱ�����ʽ����ȷ�����������룡");
	     document.forms[0].lrrqq.focus();
	     return false;
	   }
	   if (document.forms[0].lrrqz.value != "" && !checkDate(document.forms[0].lrrqz.value))
	   {
	     alert("¼��ʱ��ֹ��ʽ����ȷ�����������룡");
	     document.forms[0].lrrqz.focus();
	     return false;
	   }
	   if (document.forms[0].lrrqq.value != "" &&
	   document.forms[0].lrrqz.value != "" &&
	   !cmpDate(document.forms[0].lrrqq.value,document.forms[0].lrrqz.value))
	 	{
	 	   alert("¼��ʱ����ʱ�䲻�ܴ���¼��ʱ��ֹʱ�䣬���������룡");
	 	   document.forms[0].lrrqq.focus();
	 	   return false;
	 	}
  }
  
  if(operationType=="SaveErr")
  {
		var itr="false";
  
  		<%if(form.getSizeErrIs()>0){%>
  
  		if (document.forms[0].selectedIndexIs.length == null)
	    {
	        if (document.forms[0].selectedIndexIs.checked)
	        {
	            itr="true";
	        }	        
	    }else {
	  
		   	for(var i=0;i<document.forms[0].selectedIndexIs.length;i++)
		    {
		        var e = document.forms[0].selectedIndexIs[i];
		        if(e.checked)
		        {
		        	itr="true";
		            break;
		        }
		    }
	    }
		    
  		<%}%>
  		
	    <%if(form.getSizeErrNo()>0){%>
	    
	    if (document.forms[0].selectedIndexNo.length == null)
	    {
	        if (document.forms[0].selectedIndexNo.checked)
	        {
	            itr="true";
	        }	        
	    }else {
	    
		    for(var i=0;i<document.forms[0].selectedIndexNo.length;i++)
		    {
		        var e = document.forms[0].selectedIndexNo[i];
		        if(e.checked)
		        {
		        	itr="true";
		            break;
		        }
		    }
	    }
	    <%}%>
	    
	    if(itr == "false")
	    {
	        alert("������ѡ��Ҫ�޸ĵ���");
	        return false;
	    }
	    
	    //�ж���ʽ���ݼ�������ʽ���ݽ�������Ƿ������֡�
	    
	    if(isNumberE()){} else return false;
	    
	    
	    // �ж��Ƿ�ѡ�����޸Ķ���
	    if(confirm("�Ƿ��޸���ѡ��¼��"))
	    {
			<%if(form.getSizeErrIs()>0){%>
			
	       	var thereIs = FN_setChkStatea("Is");  
	 
	  	   	document.forms[0].testIs.value = thereIs;
  	   	
			<%}%>
				
			<%if(form.getSizeErrNo()>0){%>
		
	    	var thereNo = FN_setChkStatea("No"); 
	 
	  	   	document.forms[0].testNo.value = thereNo;
	  	   	
	  	   	<%}%>
	  	   	

	     }else  return false; 
	     
	     
	     
	     document.forms[0].operationType.value="SaveErr";
  
  }
  
  document.forms[0].submit();
  return true;

}


// ��ȡ��ʼ�ͽ���������ֶεĿո�
function delSpace(obj) {
       obj.value = obj.value.replace(/[ ]/g,"");
}

//�ж���ʽ���ݼ�������ʽ���ݽ�������Ƿ������֡�

function isNumberE(){

		var row =0;
		var i = 1;
		var e;
		var the = "";

		<%if(form.getSizeErrIs()>0){%>
		if (document.forms[0].selectedIndexIs.length == null)
	    {
				e = document.forms[0].selectedIndexIs;			
	
		        if (e.checked) {
		        
		        	var esd =document.getElementsByName("Is0");
	
					var str="00";	
						
					for (var k = 0; k<esd.length; k++){
	
						if(esd[k].checked){
	
							str=esd[k].value;
							
						}
					}
		        
		        
					if(str=="02"){
					
						var es;
						
						es =document.forms[0].zsfwjs_a;	
						
						if(FN_QSCheckNumber(es.value,"��"+i+"����ʽ���ݼ���")) {}else return false;
						
						es =document.forms[0].cqmj_a;
						
						if(FN_QSCheckNumber(es.value,"��"+i+"��ʽ���ݽ������")) {}else return false;		
					}		
					
		        }
		            
				i = i + 1;
	             
	    }else {

		 	for(var row=0; row<document.forms[0].selectedIndexIs.length; row++)
		    {
	
		        e = document.forms[0].selectedIndexIs[row];			
	
		        if (e.checked) {
		        
		        
		        	var esd =document.getElementsByName("Is"+row);
	
					var str="00";	
						
					for (var k = 0; k<esd.length; k++){
	
						if(esd[k].checked){
	
							str=esd[k].value;
							
						}
					}
		        
		        
					if(str=="02"){	        	
					
						var es;
						
						es =document.forms[0].zsfwjs_a[row];	
						
						if(FN_QSCheckNumber(es.value,"��"+i+"����ʽ���ݼ���")) {}else return false;
						
						es =document.forms[0].cqmj_a[row];
						
						if(FN_QSCheckNumber(es.value,"��"+i+"��ʽ���ݽ������")) {}else return false;		
					}		
					
					
		        }
		            
				i = i + 1;
		    }
		    
		}
		
		<%}%>
			
		<%if(form.getSizeErrNo()>0){%>
		
		if (document.forms[0].selectedIndexNo.length == null)
	    {
				e = document.forms[0].selectedIndexNo;			
	
		        if (e.checked) {
		        
		        	var esd =document.getElementsByName("No0");
	
					var str="00";	
						
					for (var k = 0; k<esd.length; k++){
	
						if(esd[k].checked){
	
							str=esd[k].value;
							
						}
					}
		        
		        
					if(str=="02"){
					
						var es;
						
						es =document.forms[0].zsfwjs_b;	
						
						if(FN_QSCheckNumber(es.value,"��"+i+"����ʽ���ݼ���")) {}else return false;
						
						es =document.forms[0].cqmj_b;
						
						if(FN_QSCheckNumber(es.value,"��"+i+"��ʽ���ݽ������")) {}else return false;		
					}		
					
		        }
		            
				i = i + 1;
	             
	    }else {
	    
		    for(var row=0; row<document.forms[0].selectedIndexNo.length; row++)
		    {
				
	
		        e = document.forms[0].selectedIndexNo[row];			
	
		        if (e.checked) {
		        
		        	var esd =document.getElementsByName("No"+row);
	
					var str="00";	
						
					for (var k = 0; k<esd.length; k++){
	
						if(esd[k].checked){
	
							str=esd[k].value;
							
						}
					}
		        
		        
					if(str=="02"){
					
						var es;
						
						es =document.forms[0].zsfwjs_b[row];	
						
						if(FN_QSCheckNumber(es.value,"��"+i+"����ʽ���ݼ���")) {}else return false;
						
						es =document.forms[0].cqmj_b[row];
						
						if(FN_QSCheckNumber(es.value,"��"+i+"��ʽ���ݽ������")) {}else return false;		
					}		
					
		        }  
		            
				i = i + 1;
		    }
	    }
	    
	    <%}%>
	    
	    return true;

}


//���ǡ��������
function FN_setChkStatea(strValue)
{

	if(strValue=="Is"){

		var row =0;
		var i = 0;
		var e;
		var the = new Array();


		if (document.forms[0].selectedIndexIs.length == null)
	    {
	    
 			e = document.forms[0].selectedIndexIs;			
	
	        if (e.checked) {
	        	
				the[i]="00";
				
				var es =document.getElementsByName("Is0");

				for (var k = 0; k<es.length; k++){
				
					if(es[k].checked){
	
						the[i]=es[k].value;
							
					}
					
				}
				
				i = i + 1;	
			}    
	    
	    }else{
		
		 	for(var row=0; row<document.forms[0].selectedIndexIs.length; row++)
		    {
				
	
		        e = document.forms[0].selectedIndexIs[row];			
	
		        if (e.checked) {	 	
		        	
					the[i]="00";
					
					var es =document.getElementsByName("Is"+row);
	
					for (var k = 0; k<es.length; k++){
	
						if(es[k].checked){
	
							the[i]=es[k].value;
							
						}
					}
					
					i = i + 1;
					
		        }       
	
		    }
	    }

    }
    
	if(strValue=="No"){

		var row =0;
		var i = 0;
		var e;
		var the = new Array();
		
		
		if (document.forms[0].selectedIndexNo.length == null)
	    {
	    
 			e = document.forms[0].selectedIndexNo;
	
	        if (e.checked) {
	        	
				the[i]="00";
				
				var es =document.getElementsByName("No0");

				for (var k = 0; k<es.length; k++){
				
					if(es[k].checked){
	
						the[i]=es[k].value;
							
					}
					
				}
				
				i = i + 1;	
			}    
	    
	    }else{

		 	for(var row=0; row<document.forms[0].selectedIndexNo.length; row++)
		    {
				
	
		        e = document.forms[0].selectedIndexNo[row];			
	
		        if (e.checked) {	
		        	
					the[i]="00";
					
					var es =document.getElementsByName("No"+row);	
	
					for (var k = 0; k<es.length; k++){
	
						if(es[k].checked){
	
							the[i]=es[k].value;
							
						}
					}
					i = i + 1;
					
		        }       
	
		    }
		    
		}

    }
    
    
    return the;
    

   
}


</SCRIPT>

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0
	marginheight="0" marginwidth="0">
<jsp:include page="/include/Header.jsp" flush="true">
	<jsp:param name="subtitle" value="��Ǩ��Ϣ&gt;���������Ϣά��" />
	<jsp:param name="helpURL" value="" />
</jsp:include>
<SCRIPT language=javascript>
<!--

function popUp(){
    var server = '192.100.99.100';
    var port   = '80';
    props=window.open('<%=static_file%>' + '/about/about.htm','poppage','toolbars=0,scrollbars=0,location=0,statusbara=0,menubars=0,resizable=0,width=500,height=267');
}

//-->
</SCRIPT>
<br>


<table align=center border=0 cellPadding=0 cellSpacing=0 height="80%"
	width=98%>
	<TBODY>
		<TR>
			<td vAlign=top>
			<table align=center cellSpacing=0 class=table-99>
				<TBODY>
					<TR>
						<td class=1-td1>��Ǩ��Ϣ-���������Ϣά��</td>
					</TR>
					<TR>
						<td class=1-td2 vAlign=top><html:form action="/cqxx/cqxxWhErr.do">
							<input type="hidden" name="operationType" value="">
							<input type="hidden" name="sfwh" value="00">
							<input type="hidden" name="cqxxbh">

							<input type="hidden" name="testIs">

							<input type="hidden" name="testNo">

							<br>
							<table border="0" cellSpacing=0 class=table-60>
								<TBODY>
									<TR>
										<td class="2-td1-center">��������</td>
										<td class="2-td2-t-right">
										<DIV align=left><select name="szqx">
											<option value="00">ȫ��</option>
											<%Object[] qxdmArray = szqxMap.keySet().toArray();
				for (int i = 0; i < qxdmArray.length; i++) {
					String qxdmTmp = (String) qxdmArray[i];
					if (qxdmTmp.equals(qxdm)) {
						out.print("<option value=\"" + qxdmTmp + "\" selected>"
								+ szqxMap.get(qxdmArray[i]) + "</option>");
					} else {
						out.print("<option value=\"" + qxdmTmp + "\">"
								+ szqxMap.get(qxdmArray[i]) + "</option>");
					}
				}

				%>
											</selct></DIV>
										</td>
									</TR>
									<TR>
										<td class=2-td1-Bcenter width="50%">����Ǩ������&nbsp;</td>
										<td class="2-td2-right" width="50%">
										<DIV align=left><html:text property="bcqrmc" maxlength="" /></DIV>
										</td>
									</TR>
									<TR>
										<td class=2-td1-Bcenter width="50%">����Ǩ������&nbsp;</td>
										<td class="2-td2-right" width="50%">
										<DIV align=left><html:select property="bcqrlxdm">
											<html:option value="00">ȫ��</html:option>
											<html:option value="0">����</html:option>
											<html:option value="1">�Ǹ���</html:option>
										</html:select></DIV>
										</td>
									</TR>
									<TR>
										<td class=2-td1-Bcenter width="50%">����Ǩ��֤������&nbsp;</td>
										<td class="2-td2-right" width="50%">
										<DIV align=left><html:text property="zjhm" maxlength="" /></DIV>
										</td>
									</TR>
									<tr>
										<td class="2-td1-Bcenter">����Ǩ��ϸ��ַ</td>
										<td class="2-td2-right">
										<DIV align=left><html:text property="cqxxdz" maxlength="" />
										</DIV>
										</td>
									</tr>
									<TR>
										<td class=2-td1-Bcenter width="50%">�������&nbsp;</td>
										<td class="2-td2-right" width="50%">
										<DIV align=left><html:text property="bcjeq" maxlength="8"
											tabindex="0" size="8" />-<html:text property="bcjez"
											maxlength="8" tabindex="0" size="8" /></DIV>
										</td>
									</TR>
									<TR>
										<td class=2-td1-Bcenter width="50%">¼������&nbsp;</td>
										<td class="2-td2-right" width="50%">
										<DIV align=left><html:text property="lrrqq" maxlength="8"
											tabindex="0" size="8" />-<html:text property="lrrqz"
											maxlength="8" tabindex="0" size="8" />yyyyMMdd</DIV>
										</td>
									</TR>
									<tr>
										<td class="2-td1-Bcenter">��Ǩ��Ŀ����</td>
										<td class="2-td2-right">
										<DIV align=left><html:text property="cqxmmc" maxlength="" />
										</DIV>
										</td>
									</tr>
							</table>
							<BR>

							<DIV align=center><IMG name="query"
								onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
								onMouseOut="MM_swapImgRestore()"
								src="<%=static_file%>images/chaxun1.jpg"
								onclick="doSubmitThis('QueryErr');" width="79" height="22"
								id="chaxun1" alt="��ѯ" style="cursor:hand">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <IMG name="back"
								onclick="doSubmitThis('Return');"
								onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)"
								onMouseOut="MM_swapImgRestore()"
								src="<%=static_file%>images/tuichu1.jpg" width="79" height="22"
								id="tuichu1" alt="�˳�" style="cursor:hand"></DIV>
							<BR>

							<logic:equal name="cqxxbForm" property="status" scope="session"
								value="Result">
								<table cellspacing="0" class="table-99">
									<tr>
										<td class="2-td2-t-center">��ѯ���</td>
										<!--��������Ϊ�գ���ʾû�м�¼-->
										<logic:lessEqual name="cqxxbForm" property="size" value="0">
											<td class="2-td2-t-center">
											<div align="center">û����Ҫ�ļ�¼�������²�ѯ</div>
											</td>

										</logic:lessEqual>
									</tr>
								</table>
								<!--�����¼���Ϊ�գ�����ʾ-->

								<logic:greaterThan name="cqxxbForm" property="size" value="0">
									<br>
									<div id="LayerErr"
										style="position:static; overflow: auto;  width: 970px; height: 420px">
									<table cellspacing="0" class="table-99" align="center">
										<tr>
											<td class="2-td1-left" nowrap>���</td>
											<td class="2-td1-left" nowrap>ѡ��</td>
											<td class="2-td1-left" nowrap>����</td>
											<td class="2-td1-left" nowrap>����</td>
											<td class="2-td1-left" nowrap>��������</td>
											<td class="2-td1-left" nowrap>��Ǩ������</td>
											<td class="2-td1-left" nowrap>��Ǩ��Ŀ����</td>
											<td class="2-td1-left" nowrap>��Ǩ���֤</td>
											<td class="2-td1-left" nowrap>��Ǩ��Χ</td>
											<td class="2-td1-left" nowrap>����Ǩ������</td>
											<td class="2-td1-left" nowrap>����Ǩ��֤������</td>
											<td class="2-td1-left" nowrap>����Ǩ��ϸ��ַ</td>
											<td class="2-td1-left" nowrap>��ʽ���ݼ���</td>
											<td class="2-td1-left" nowrap>��ʽ���ݽ������</td>
											<td class="2-td1-center" nowrap>����������</td>											
										</tr>


										<logic:greaterThan name="cqxxbForm" property="sizeErrIs"
											value="0">


											<logic:iterate id="cqxxErr" indexId="index" length="length"
												name="cqxxbForm" property="resultListErrIs"
												type="com.creationstar.bjtax.qsgl.BizLogic.vo.CqxxErr">
												
												<tr>
													<td class="2-td2-left" nowrap>&nbsp;</td>
												
													<td class="2-td2-left" nowrap>&nbsp;</td>

													<td class="2-td2-left" nowrap>&nbsp;</td>

													<td class="2-td2-left" nowrap>&nbsp;</td>
													
													<td class="2-td2-left" nowrap>&nbsp;</td>

													<td class="2-td2-left" nowrap>&nbsp;<bean:write
														name="cqxxErr" property="cqxxb.cqrmc" />&nbsp;</td>

													<td class="2-td2-left" nowrap>&nbsp;<bean:write
														name="cqxxErr" property="cqxxb.cqxmmc" />&nbsp;</td>

													<td class="2-td2-left" nowrap>&nbsp;<bean:write
														name="cqxxErr" property="cqxxb.cqxkzh" />&nbsp;</td>

													<td class="2-td2-left" nowrap>&nbsp;<bean:write
														name="cqxxErr" property="cqxxb.cqfw" />&nbsp;</td>

													<td class="2-td2-left" nowrap>&nbsp;<bean:write
														name="cqxxErr" property="cqxxb.bcqrmc" />&nbsp;</td>

													<td class="2-td2-left" nowrap>&nbsp;<bean:write
														name="cqxxErr" property="cqxxb.zjhm" />&nbsp;</td>

													<td class="2-td2-left" nowrap>&nbsp;<bean:write
														name="cqxxErr" property="cqxxb.cqxxdz" />&nbsp;</td>

													<td class="2-td2-left" nowrap>&nbsp;<bean:write
														name="cqxxErr" property="cqxxb.zsfwjs" />&nbsp;</td>

													<td class="2-td2-left" nowrap>&nbsp;<bean:write
														name="cqxxErr" property="cqxxb.cqmj" />&nbsp;</td>

													<td class="2-td2-center" nowrap>&nbsp;<bean:write
														name="cqxxErr" property="cqxxb.gjrmc" />&nbsp;</td>
														
													
												</tr>
												
												

												<tr>
												
													<td class="2-td2-left" nowrap><%=index.intValue()+1%></td>
													
													<td class="2-td2-left" nowrap>&nbsp;<input type="checkbox"
														name="selectedIndexIs" value="<%=index.intValue()%>">&nbsp;</td>

													<td class="2-td2-left" nowrap>&nbsp;<input type="radio"
														id="<%="Is"+index.intValue()%>"
														name="<%="Is"+index.intValue()%>"
														value="<%=Constants.CQXXCWB_CWLX_01%>" >&nbsp;</td>

													<td class="2-td2-left" nowrap>&nbsp;<input type="radio"
														id="<%="Is"+index.intValue()%>"
														name="<%="Is"+index.intValue()%>"
														value="<%=Constants.CQXXCWB_CWLX_02%>">&nbsp;</td>
														
													<td class="2-td2-left" nowrap>&nbsp;<bean:write
														name="cqxxErr" property="cqxxImportErrbvo.cwlxmc" />&nbsp;</td>

													<td class="2-td2-left" nowrap><INPUT border=0
														name='cqrmc_a' maxlength=50 onchange='delSpace(this);'
														value='<%=cqxxErr.getCqxxImportErrbvo().getCqrmc()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0
														name='cqxmmc_a' maxlength=50 onchange='delSpace(this);'
														value='<%=cqxxErr.getCqxxImportErrbvo().getCqxmmc()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0
														name='cqxkzh_a' maxlength=20 onchange='delSpace(this);'
														value='<%=cqxxErr.getCqxxImportErrbvo().getCqxkzh()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0 name='cqfw_a'
														maxlength=50 onchange='delSpace(this);'
														value='<%=cqxxErr.getCqxxImportErrbvo().getCqfw()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0
														name='bcqrmc_a' maxlength=10 onchange='delSpace(this);'
														value='<%=cqxxErr.getCqxxImportErrbvo().getBcqrmc()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0 name='zjhm_a'
														maxlength=20 onchange='delSpace(this);'
														value='<%=cqxxErr.getCqxxImportErrbvo().getZjhm()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0
														name='cqxxdz_a' maxlength=50 onchange='delSpace(this);'
														value='<%=cqxxErr.getCqxxImportErrbvo().getCqxxdz()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0
														name='zsfwjs_a' maxlength=5 onchange='delSpace(this);'
														value='<%=cqxxErr.getCqxxImportErrbvo().getZsfwjs()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0 name='cqmj_a'
														maxlength=17 onchange='delSpace(this);'
														value='<%=cqxxErr.getCqxxImportErrbvo().getCqmj()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

													<td class="2-td2-center" nowrap><INPUT border=0
														name='gjrmc_a' maxlength=50 onchange='delSpace(this);'
														value='<%=cqxxErr.getCqxxImportErrbvo().getGjrmc()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

														
												
												</tr>


												
											</logic:iterate>

										</logic:greaterThan>


										<logic:greaterThan name="cqxxbForm" property="sizeErrNo"
											value="0">

											<logic:iterate id="cqxxImportErrbvo" indexId="index2"
												length="length" name="cqxxbForm" property="resultListErrNo"
												type="com.creationstar.bjtax.qsgl.BizLogic.vo.CqxxImportErrbvo">

												<tr>
													<td class="2-td2-left" nowrap><%=form.getSizeErrIs()+index2.intValue()+1%></td>
												
													<td class="2-td2-left" nowrap>&nbsp;<input type="checkbox"
														name="selectedIndexNo" value="<%=index2.intValue()%>">&nbsp;</td>

													<td class="2-td2-left" nowrap>&nbsp;<input type="radio"
														id="<%="No"+index2.intValue()%>"
														name="<%="No"+index2.intValue()%>"
														value="<%=Constants.CQXXCWB_CWLX_01%>" >&nbsp;</td>

													<td class="2-td2-left" nowrap>&nbsp;<input type="radio"
														id="<%="No"+index2.intValue()%>"
														name="<%="No"+index2.intValue()%>"
														value="<%=Constants.CQXXCWB_CWLX_02%>">&nbsp;</td>
														
													<td class="2-td2-left" nowrap>&nbsp;<bean:write
														name="cqxxImportErrbvo" property="cwlxmc" />&nbsp;</td>

													<td class="2-td2-left" nowrap><INPUT border=0
														name='cqrmc_b' maxlength=50 onchange='delSpace(this);'
														value='<%=cqxxImportErrbvo.getCqrmc()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0
														name='cqxmmc_b' maxlength=50 onchange='delSpace(this);'
														value='<%=cqxxImportErrbvo.getCqxmmc()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0
														name='cqxkzh_b' maxlength=20 onchange='delSpace(this);'
														value='<%=cqxxImportErrbvo.getCqxkzh()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0 name='cqfw_b'
														maxlength=50 onchange='delSpace(this);'
														value='<%=cqxxImportErrbvo.getCqfw()%>' style='width:100%'
														class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0
														name='bcqrmc_b' maxlength=10 onchange='delSpace(this);'
														value='<%=cqxxImportErrbvo.getBcqrmc()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0 name='zjhm_b'
														maxlength=20 onchange='delSpace(this);'
														value='<%=cqxxImportErrbvo.getZjhm()%>' style='width:100%'
														class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0
														name='cqxxdz_b' maxlength=50 onchange='delSpace(this);'
														value='<%=cqxxImportErrbvo.getCqxxdz()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0
														name='zsfwjs_b' maxlength=5 onchange='delSpace(this);'
														value='<%=cqxxImportErrbvo.getZsfwjs()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

													<td class="2-td2-left" nowrap><INPUT border=0 name='cqmj_b'
														maxlength=17 onchange='delSpace(this);'
														value='<%=cqxxImportErrbvo.getCqmj()%>' style='width:100%'
														class="inputnormal"></INPUT></td>

													<td class="2-td2-center" nowrap><INPUT border=0
														name='gjrmc_b' maxlength=50 onchange='delSpace(this);'
														value='<%=cqxxImportErrbvo.getGjrmc()%>'
														style='width:100%' class="inputnormal"></INPUT></td>

												</tr>

											</logic:iterate>

										</logic:greaterThan>
									</table>
									</div>
									<br>
									<table cellspacing="0" class="table-99">
										<tr>

											<td class="2-td2-t-left">
											<div align="left">&nbsp; <IMG name="baocun"
												onMouseOver="MM_swapImage('baocun1','','<%=static_file%>images/baocun2.jpg',1)"
												onMouseOut="MM_swapImgRestore()"
												src="<%=static_file%>images/baocun1.jpg"
												onclick="doSubmitThis('SaveErr');" width="79" height="22"
												id="chaxun1" alt="����" style="cursor:hand"></div>
											</td>

										</tr>
									</table>

								</logic:greaterThan>

							</logic:equal>

						</html:form> <%@ include file="../include/Bottom.jsp"%>
</BODY>
</HTML>
