<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.nstzmxzjb.web.NstzmxzjbForm" %>
<%@ page import ="java.util.HashMap"%>
<%@ page import="com.syax.creports.Constants"%>
<html>
<head>
<title>��˰����������Ŀ��ϸ��</title>
<link href="../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/function.js"></script>
</head>
<script language=JavaScript>

	
/*��ʼ��*/
function doInit()
{
	   document.forms[0].nstzzjMax.value='3';
	    
	    
		//��ʼ���ı���onChange�¼�����
		initNumText("bqfss",38);
		initNumText("sqkcxe",38);
		initNumText("nstzje",38);
		initNumText("nstzzj_bqfss",parseInt(document.forms[0].nstzzjMax.value));
		initNumText("nstzzj_sqkcxe",parseInt(document.forms[0].nstzzjMax.value));
		initNumText("nstzzj_nstzje",parseInt(document.forms[0].nstzzjMax.value));
	
	//ɾ�����У��������������
		var list=document.getElementById("nstzzjmxb_list");
		for(var i=parseInt(document.forms[0].nstzzjMax.value);i>3;i--){
			list.deleteRow( parseInt(39+parseInt(i-1)) );
		}
	  
	<%
	NstzmxzjbForm nstzmxzjbForm=(NstzmxzjbForm)request.getAttribute("nstzmxzjbForm");
	if (nstzmxzjbForm!=null && nstzmxzjbForm.getDataList().size()>0){
		for(int i=0;i<nstzmxzjbForm.getDataList().size();i++){
			HashMap map=(HashMap)nstzmxzjbForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			System.out.println("hc ==" + hc);
			String bqfss=(String)map.get("bqfss");
			String sqkcxe=(String)map.get("sqkcxe");
			String nstzje=(String)map.get("nstzje");
			%>
			obj = eval("document.getElementById('<%=hc%>_1')");
			obj.value = '<%=bqfss%>';
			obj = eval("document.getElementById('<%=hc%>_2')");
			obj.value = '<%=sqkcxe%>';
			obj = eval("document.getElementById('<%=hc%>_3')");
			obj.value = '<%=nstzje%>';
			
			<% 
		}
	}
	
	if (nstzmxzjbForm!=null && nstzmxzjbForm.getNstzzj_List().size()>3)
	{
			//��̬������
			int dynamicRows=nstzmxzjbForm.getNstzzj_List().size()-3;
			for(int i=0;i<dynamicRows;i++)
			{
			%>
			  DynamicAddNstzzj();
			<%
			}
		}
		
		//��ֵ
		if(nstzmxzjbForm!=null )
		{
			for(int i=0;i<nstzmxzjbForm.getNstzzj_List().size();i++)
			{
				HashMap map=(HashMap)nstzmxzjbForm.getNstzzj_List().get(i);
				String strL1=(String)map.get("xm");
				String strL2=(String)map.get("nstzzj_bqfss");
				String strL3=(String)map.get("nstzzj_sqkcxe");
				String strL4=(String)map.get("nstzzj_nstzje");
				%>
				setNstzzjJEValue('<%=i%>','<%=strL1%>','<%=strL2%>','<%=strL3%>','<%=strL4%>');
				<%
			}
		}
	
	%>
}
function DynamicAddNstzzj()
{
   var list=document.getElementById("nstzzjmxb_list");

		//��ǰ������׼�������������
		var hc=document.forms[0].nstzzjMax.value;

		var newRow=list.insertRow(39+parseInt(hc));
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp";

		//��Ŀ
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds2';
		newCell.innerHTML="<input type='text' name='xm'  value='' size='25' tabindex='2'>";
		
		//���ڷ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='nstzzj_bqfss'  value='' size='13' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//˰ǰ�۳��޶�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='nstzzj_sqkcxe'  value='' size='13' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��˰�������
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='nstzzj_nstzje'   value='' size='13' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk' value=''>";
		
		//��ǰ����˰������Ŀ�������������1
		document.forms[0].nstzzjMax.value=parseInt(document.forms[0].nstzzjMax.value)+1;
}
function setNstzzjJEValue(i,L1,L2,L3,L4)
{
   var qekc_L1=document.all("xm");
   qekc_L1[parseInt(i)].value=L1
		
   var qekc_L2=document.all("nstzzj_bqfss");
   qekc_L2[parseInt(i)].value=L2
   
   var qekc_L2=document.all("nstzzj_sqkcxe");
   qekc_L2[parseInt(i)].value=L3
   
   var qekc_L2=document.all("nstzzj_nstzje");
   qekc_L2[parseInt(i)].value=L4
   
}

<%/*����*/%>
	function doSave()
	{
      var list="<%=Constants.QYSDS_CONTROL_CHAR_FOR_JS%>";
		if (!Char_Vaildate(document.forms[0],list)){
			return false;	
		}
      doSubmitForm('Save');
		
	}
	
	<%/*���*/%>
	function doReset()
	{
	    if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
	    {
	      <%
		   	for(int i=1; i<=38; i++){
		   	%>
				obj = eval("document.getElementById('<%=i%>_1')");
				if(obj.readOnly!=true)
				obj.value = "";
				
				obj = eval("document.getElementById('<%=i%>_2')");
				if(obj.readOnly!=true)
				obj.value = "";
				
				obj = eval("document.getElementById('<%=i%>_3')");
				obj.value = "";
				<%
		   	}
		   	%>
		   	clean('xm','nstzzj_bqfss','nstzzj_sqkcxe','nstzzj_nstzje',document.forms[0].nstzzjMax.value);
	    }
	}
	
	<%/*���������̬���е�����*/%>
	function clean(L1,L2,L3,L4,maxRow){
		var arr1=document.all(L1);
		var arr2=document.all(L2);	
		var arr3=document.all(L3);	
		var arr4=document.all(L4);	
		
		for (var i=0;i<parseInt(maxRow);i++){					
			
				arr1[parseInt(i)].value=""	;		
		
				arr2[parseInt(i)].value=""	;			
			
				arr3[parseInt(i)].value=""	;			
		
				arr4[parseInt(i)].value=""	;			
		}	
	}
	
	
	<%/*ɾ��*/%>
	function doDelete()
	{
		doSubmitForm('Delete');
	}
	
	<%/*���*/%>
	function doChecked()
	{
	   var list="<%=Constants.QYSDS_CONTROL_CHAR_FOR_JS%>";
		if (!Char_Vaildate(document.forms[0],list)){
			return false;	
		}
	   doSubmitForm('Check');
	}
	
		<%/*����*/%>
		function doExit()
		{
			doSubmitForm('Exit');
		}
	<%/*������*/%>
	function InsertNSTZRow()
	{
	    var list=document.getElementById("nstzzjmxb_list");

		//��ǰ����˰������Ŀ�����������
		var hc=document.forms[0].nstzzjMax.value;

		var newRow=list.insertRow(39+parseInt(hc));
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp";

		//��Ŀ
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds2';
		newCell.innerHTML="<input type='text' name='xm' id='xm_"+parseInt(hc)+1+"_1' value='' size='25' tabindex='2'>";
		
		//���ڷ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='nstzzj_bqfss' id='nstzzj_bqfss_"+parseInt(hc)+1+"_1' value='' size='13' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//˰ǰ�۳��޶�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='nstzzj_sqkcxe' id='nstzzj_sqkcxe_"+parseInt(hc)+1+"_2' value='' size='13' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		//��˰�������
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='nstzzj_nstzje' id='nstzzj_nstzje_"+parseInt(hc)+1+"_2' value='' size='13' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk' value=''>";
		
		//��ǰ����˰������Ŀ�������������1
		document.forms[0].nstzzjMax.value=parseInt(document.forms[0].nstzzjMax.value)+1;
	}
    <%/*ɾ����*/%>
    function RomoveNSTZRow()
	{
	   //��ѡ��
		var arrChk=document.all("chk");
		var list=document.getElementById("nstzzjmxb_list");
		
		//��ǰ������׼�������������
		var hc=document.forms[0].nstzzjMax.value;
		if(parseInt(document.forms[0].nstzzjMax.value)>3){
			for(var i=parseInt(parseInt(document.forms[0].nstzzjMax.value)-1);i>=3;i--){
				if(arrChk.length==undefined){
					if(arrChk.checked){
						if(txt_null("muti",i)){
							if(window.confirm("����׼�������е� "+parseInt(parseInt(i)+1) +" �����ݲ�Ϊ�գ�ȷ��ɾ����"))
							{
								list.deleteRow( parseInt(39+parseInt(i)) );
								document.forms[0].nstzzjMax.value=parseInt(document.forms[0].nstzzjMax.value)-1;
							}else{
								arrChk.checked=false;
							}
						}else{
							list.deleteRow( parseInt(39+parseInt(i)) );
							document.forms[0].nstzzjMax.value=parseInt(document.forms[0].nstzzjMax.value)-1;
						}
					}
				}else{
					if(arrChk[i-3].checked)
					{
						if(txt_null("muti",i))
						{
							if(window.confirm("����׼�������е� "+parseInt(parseInt(i)+1) +" �����ݲ�Ϊ�գ�ȷ��ɾ����"))
							{
								list.deleteRow( parseInt(39+parseInt(i)) );
								document.forms[0].nstzzjMax.value=parseInt(document.forms[0].nstzzjMax.value)-1;
							}else{
								arrChk[i-3].checked=false;
							}
						}else{
							list.deleteRow( parseInt(39+parseInt(i)) );
							document.forms[0].nstzzjMax.value=parseInt(document.forms[0].nstzzjMax.value)-1;
						}
					}
				}
				
			}
		}	
	}
	//�ж���˰������Ŀ���������Ƿ�Ϊ��
	//��Ϊ�գ�����true
	//Ϊ�շ���false
	function txt_null(s,i){
		if (s=="1"){   
			if (document.all("xm").value!="" && document.all("xm").value!=undefined)  {
				return true;
			} 
			if   (document.all("nstzzj_bqfss").value!="" && document.all("nstzzj_bqfss").value!=undefined)   {
				return true;
			}  
			if   (document.all("nstzzj_sqkcxe").value!="" && document.all("nstzzj_sqkcxe").value!=undefined)   {
				return true;
			}  
			if   (document.all("nstzzj_nstzje").value!="" && document.all("nstzzj_nstzje").value!=undefined)   {
				return true;
			}  
		}else{   
			if   (document.all("xm")[i].value!="")   {
				return true;
			}   
			if   (document.all("nstzzj_bqfss")[i].value!="")   {
				return true;
			}  
			if   (document.all("nstzzj_sqkcxe")[i].value!="")   {
				return true;
			}  
			if   (document.all("nstzzj_nstzje")[i].value!="")   {
				return true;
			}   
		}
		return false;
	}
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0" onLoad="doInit()">
		<%@ include file="../include/header.jsp"%>
 <br>
    <html:form 
			action="/webapp/smsb/qysds/nstzmxzjbAction.do" method="post">
			
		<html:hidden property="actionType" />
		<html:hidden property="nextTableURL" />
	    <html:hidden property="previousTableURL" />
        <%/*��˰����������Ŀ������*/%>
	<input type='hidden' name='nstzzjMax' value='3' />
	<table width="96%" align="center" cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1"  colspan="7">��˰����������Ŀ��ϸ�� </td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="nstzmxzjbForm" property="sbrq" scope="request" filter="true" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��λ��Ԫ�������Ƿ֣�
			</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;���������:<bean:write
				name="nstzmxzjbForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="nstzmxzjbForm" property="nsrmc" scope="request" filter="true" />&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>	
		<tr>
			<td class="1-td2"  colspan="7">
				<TABLE class="table-99" align="center">
				<TR>
				<TD>
				<div id="Layer2" style="position:static; ">
				<table id="nstzzjmxb_list" border="1" cellspacing="0" class="table-99" align="center" >
                  <tr> 
                    <td rowspan="2" class="2-td1-left">�д�</td>
                    <td rowspan="2" class="2-td1-left">��Ŀ</td>
                    <td class="2-td1-left">���ڷ�����</td>
					<td class="2-td1-left">˰ǰ�۳��޶�</td>					
                    <td class="2-td1-left">��˰�������</td>     
                  </tr>
				  <tr> 
                    <td class="2-td1-left">1</td>
                    <td class="2-td1-left">2</td>                    
                    <td class="2-td1-center">3</td>       
                  </tr>
				  
                  <tr> 
                    <td class="2-td2-left">1</td>
                    <td class="2-td2-left-qysds1">����н��֧��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='1_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='1_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='1_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="1"/>
					</td>
				 </tr>
				<tr>
					<td class="2-td2-left">2</td>
                    <td class="2-td2-left-qysds1">���ᾭ��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='2_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='2_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='2_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="2"/>
					</td>					  
				</tr>
				<tr>
				    <td class="2-td2-left">3</td>
                    <td class="2-td2-left-qysds1">ְ��������</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='3_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='3_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='3_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="3"/>
					</td>	
				</tr>	
					
				<tr>
				   <td class="2-td2-left">4</td>
                    <td class="2-td2-left-qysds1">ְ����������</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='4_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='4_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='4_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="4"/>
					</td>
				</tr>	
				<tr>
				   <td class="2-td2-left">5</td>
                    <td class="2-td2-left-qysds1">��Ϣ֧��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='5_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='5_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='5_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="5"/>
					</td>	
				</tr>	
					
				<tr>
				    <td class="2-td2-left">6</td>
                    <td class="2-td2-left-qysds1">ҵ���д���</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='6_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='6_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='6_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="6"/>
					</td>	
                </tr>
				<tr>
				    <td class="2-td2-left">7</td>
                    <td class="2-td2-left-qysds1">����ת����ǰ���ȷ�ϵ�ʱ���Բ���</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='7_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='7_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='7_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="7"/>
					</td>	
				</tr>	
				<tr>
				     <td class="2-td2-left">8</td>
                    <td class="2-td2-left-qysds1">�۾ɡ�̯��֧��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='8_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='8_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='8_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="8"/>
					</td>	
				</tr>	
				<tr>
				     <td class="2-td2-left">9</td>
                    <td class="2-td2-left-qysds1">���֧��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='9_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='9_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='9_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="9"/>
					</td>	
				</tr>	
				<tr>
				    <td class="2-td2-left">10</td>
                    <td class="2-td2-left-qysds1">ҵ��������</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='10_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='10_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='10_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="10"/>
					</td>	
				</tr>	
				<tr>
				    <td class="2-td2-left">11</td>
                    <td class="2-td2-left-qysds1">����Ӷ��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='11_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='11_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='11_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="11"/>
					</td>	
				</tr>	
				<tr> 
				    <td class="2-td2-left">12</td>
                    <td class="2-td2-left-qysds1">��ȨͶ��ת�þ���ʧ</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='12_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='12_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='12_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="12"/>
					</td>
				    	
				</tr>	
				<tr>
				    <td class="2-td2-left">13</td>
                    <td class="2-td2-left-qysds1">�Ʋ���ʧ</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='13_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='13_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='13_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="13"/>
					</td>	
				</tr>	
				<tr>
				    <td class="2-td2-left">14</td>
                    <td class="2-td2-left-qysds1">����׼����</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='14_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='14_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='14_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="14"/>
					</td>	
				</tr>	
				<tr>
				    <td class="2-td2-left">15</td>
                    <td class="2-td2-left-qysds1">������ᱣ���Խɿ�</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='15_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='15_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='15_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="15"/>
					</td>
				    	
				</tr>	
				<tr>
				    <td class="2-td2-left">16</td>
                    <td class="2-td2-left-qysds2">���У��������ϱ���</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='16_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='16_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='16_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="16"/>
					</td>	
				</tr>	
				<tr>
				     <td class="2-td2-left">17</td>
                    <td class="2-td2-left-qysds2">ʧҵ����</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='17_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='17_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='17_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="17"/>
					</td>	
				</tr>	
				<tr>
				    <td class="2-td2-left">18</td>
                    <td class="2-td2-left-qysds2">����ҽ�Ʊ���</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='18_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='18_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='18_3' value='' size='13' tabindex='2'>
						<input type="hidden" name="hc" value="18"/>
					</td>	
				</tr>	
				<tr>
				    <td class="2-td2-left">19</td>
                    <td class="2-td2-left-qysds2">������������</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='19_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='19_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='19_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="19"/>
					</td>	
				</tr>	
				<tr>
				     <td class="2-td2-left">20</td>
                    <td class="2-td2-left-qysds2">���˱���</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='20_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='20_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='20_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="20"/>
					</td>	
				</tr>	
				<tr>
				   <td class="2-td2-left">21</td>
                    <td class="2-td2-left-qysds2">�������ϱ���</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='21_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='21_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='21_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="21"/>
					</td>	
				</tr>	
					<tr>
					<td class="2-td2-left">22</td>
                    <td class="2-td2-left-qysds2">����ҽ�Ʊ���</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='22_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='22_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='22_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="22"/>
					</td>
                  </tr> 	
				 <tr>                     
					<td class="2-td2-left">23</td>
                    <td class="2-td2-left-qysds1">�Ͻ��ܻ��������</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='23_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='23_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='23_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="23"/>
					</td>
                  </tr> 
				  <tr> 
					<td class="2-td2-left">24</td>
                    <td class="2-td2-left-qysds1">ס��������</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='24_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='24_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='24_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="24"/>
					</td>
                  </tr> 
				  <tr> 
					<td class="2-td2-left">25</td>
                    <td class="2-td2-left-qysds1">��������ĸ���׼���𡡣�26��+��33�У�</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='25_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='25_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='25_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="25"/>
					</td>
                  </tr> 
				  <tr> 
					<td class="2-td2-left">26</td>
                    <td class="2-td2-left-qysds2">���У��������׼��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='26_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='26_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='26_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="26"/>
					</td>
                  </tr> 
				  <tr> 
                    <td class="2-td2-left">27</td>
                    <td class="2-td2-left-qysds2">�̶��ʲ���ֵ׼��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='27_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='27_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='27_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="27"/>
					</td>
                  </tr> 
				  <tr> 
					<td class="2-td2-left">28</td>
                    <td class="2-td2-left-qysds2">�����ʲ���ֵ׼��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='28_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='28_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='28_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="28"/>
					</td>
                  </tr> 
				  <tr> 
					<td class="2-td2-left">29</td>
                    <td class="2-td2-left-qysds2">�ڽ����̵���׼��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='29_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='29_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='29_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="29"/>
					</td>
                  </tr> 
				  <tr> 
					<td class="2-td2-left">30</td>
                    <td class="2-td2-left-qysds2">��Ӫ֤ȯ����׼��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='30_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='30_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='30_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="30"/>
					</td>
                  </tr> 
				  <tr>                    
					<td class="2-td2-left">31</td>
                    <td class="2-td2-left-qysds2">����׼��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='31_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='31_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='31_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="31"/>
					</td>
                  </tr> 
				  <tr>                     
					<td class="2-td2-left">32</td>
                    <td class="2-td2-left-qysds2">��������׼������ת��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='32_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='32_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='32_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="32"/>
					</td>
                  </tr> 
				  <tr>                     
					<td class="2-td2-left">33</td>
                    <td class="2-td2-left-qysds2">����׼��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='33_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='33_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='33_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="33"/>
					</td>
                  </tr> 
				  <tr>                    
					<td class="2-td2-left">34</td>
                    <td class="2-td2-left-qysds1">����֧��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='34_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='34_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='34_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="34"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">35</td>
                    <td class="2-td2-left-qysds1">�������޹ص�֧��</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='35_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='35_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='35_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="35"/>
					</td>
                  </tr> 
				  <tr>                    
					<td class="2-td2-left">36</td>
                    <td class="2-td2-left-qysds1">����Ԥ�������Ԥ�����󣨷��ز�ҵ�����</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='36_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='36_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='36_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="36"/>
					</td>
                  </tr> 
				  <tr>                   
					<td class="2-td2-left">37</td>
                    <td class="2-td2-left-qysds1">������˰������Ŀ</td>
                    <td class="2-td2-left"><input type='text'  name='bqfss' id='37_1' value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='37_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='37_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="37"/>
					</td>
					<td class="2-td2-left"><input type="button" tabindex='-1' value="����"
								onclick="InsertNSTZRow()"></td>

							<td class="2-td2-center"><input type="button" tabindex='-1' value='ɾ��'
								onClick="RomoveNSTZRow()"></td>
                  </tr> 
				  <tr>                    
					<td class="2-td2-left">38</td>
                    <td class="2-td2-left-qysds2"><input type='text'  name='xm' id='xm_1' value='' size='25' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='nstzzj_bqfss' id='nstzzj_bqfss_1'  value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='nstzzj_sqkcxe' '  value='' id='nstzzj_sqkcxe_1' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzzj_nstzje'  value='' size='13' id='nstzzj_nstzje_1' tabindex='2'>					
					</td>
                  </tr> 
				  <tr>                     
					<td class="2-td2-left">39</td>
                    <td class="2-td2-left-qysds2"><input type='text'  name='xm' id='xm_2' value='' size='25' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='nstzzj_bqfss' id='nstzzj_bqfss_2'  value='' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='nstzzj_sqkcxe' id='nstzzj_sqkcxe_2' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzzj_nstzje' id='nstzzj_nstzje_2' value='' size='13' tabindex='2'>					
					</td>
                  </tr> 
				  <tr>                     
					<td class="2-td2-left">40</td>
                    <td class="2-td2-left-qysds2"><input type='text'  name='xm'  value='' size='25' id='xm_3' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='nstzzj_bqfss'  value='' id='nstzzj_bqfss_3' size='13' tabindex='2'></td>
                    <td class="2-td2-left"><input type='text'  name='nstzzj_sqkcxe' id='nstzzj_sqkcxe_3' value='' size='13' tabindex='2'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzzj_nstzje' id='nstzzj_nstzje_3' value='' size='13' tabindex='2'>					
					</td>
                  </tr> 
				  <tr>                    
					<td class="2-td2-left">41</td>
                    <td class="2-td2-left-qysds1">�ϼƣ�1��+��15��+23��+24��+25��+34��+��37�У�</td>
                    <td class="2-td2-left"><input type='text' name='bqfss' id='38_1' value='*' tabindex='-1'
														size='1' readonly='true' class='text-noborder'></td>
                    <td class="2-td2-left"><input type='text'  name='sqkcxe' id='38_2' value='*' tabindex='-1' size='1' class='text-noborder' readonly='true'></td> 
                    <td class="2-td2-center"><input type='text'  name='nstzje' id='38_3' value='' size='13' tabindex='2'>
					<input type="hidden" name="hc" value="38"/>
					</td>
                  </tr> 
                </table>
			        </div>
				</TD>
				</TR>
				<TR class="black9">
				<TD>
				<div align="center">
				        <a style="cursor:hand" id="previous"
						onclick="gotoPreviousTable()"><img name="xpage" value="��һ�ű�"
						alt="��д��һ�ű�"
						onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/shangyiye1.jpg" id="previoustable"> </a>&nbsp;&nbsp;
						<a style="cursor:hand" id="next"
						onclick="gotoNextTable()"><img name="spage" value="��һ�ű�"
						alt="��д��һ�ű�"
						onMouseOver="MM_swapImage('nexttable','','<%=static_contextpath%>images/xaiyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/xaiyiye1.jpg" id="nexttable"> </a>&nbsp;&nbsp;
				        <input type="image" style="cursor:hand"
						tabIndex="-1" onClick="doReset();return false;" accesskey="u"
						value="���" alt="���ҳ���������Ϣ"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" 
						id="qingchu" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1" onClick="doSave();return false;"
						accesskey="s" value="����" alt="����"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" 
						id="baocun" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doChecked();return false;" accesskey="d"
						value="����У��" alt="���ڹ�ϵУ��"
						onMouseOver="MM_swapImage('jiaoyan','','../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../images/b-bdjyd1.jpg"
						 id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doDelete();return false;" accesskey="x"
						value="ȫ��ɾ��" alt="ɾ�������������ݣ��Ҳ��ɻָ�"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" 
						id="shanchu" /> &nbsp;&nbsp; <input type="image" value="����" alt="���ص���ҵ����˰�걨��ҳ��"
						style="cursor:hand" tabIndex="-1" onClick="doExit();return false;"
						onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/fanhui1.jpg" 
						id="fanhui" /></div>
				</TD>
				</TR>
				</TABLE>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>

<%@ include file="../include/footernew.jsp"%>
</html:form>

</body>
</html>