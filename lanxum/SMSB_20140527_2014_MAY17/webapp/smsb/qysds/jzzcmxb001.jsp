<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jzzcmxb.web.JzzcmxbForm"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.syax.creports.Constants"%>
<html>
<head>
<title>����֧����ϸ��</title>
<link href="../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript"
	src="../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/smsb_common.js"></script>
<script language="JavaScript" src="../../js/DTable.js"></script>
<script language=JavaScript src="../../js/reader.js"></script>
<script language=JavaScript src="../../js/compute.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/function.js"></script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="doInit()">
<%@include file="../include/header.jsp"%>
<script language="JavaScript">
	<%/*��ʼ��*/%>	
	function doInit()	
	{
		document.forms[0].qekcMax.value='3';
		document.forms[0].bfzskcMax.value='3';
		document.forms[0].bfzwkcMax.value='3';
		document.forms[0].bfzydwkcMax.value='3';
		document.forms[0].fgyjjxMax.value='3';		
		initNumText("L2",7);
		initNumText("L3",7);
		initNumText("L4",7);
		initNumText("L5",7);
		initNumText("qekc_L3",parseInt(document.forms[0].qekcMax.value));
		initNumText("bfzskc_L3",parseInt(document.forms[0].bfzskcMax.value));
		initNumText("bfzwkc_L3",parseInt(document.forms[0].bfzwkcMax.value));
		initNumText("bfzydwkc_L3",parseInt(document.forms[0].bfzydwkcMax.value));
		initNumText("fgyjjx_L3",parseInt(document.forms[0].fgyjjxMax.value));
		
		//ɾ�����У��������������
		var list=document.getElementById("jzzc_list");
		for(var i=(parseInt(document.forms[0].qekcMax.value)-3);i>0;i--){
			list.deleteRow( parseInt(4+3+parseInt(i)) );
		}
		for(var i=parseInt(document.forms[0].bfzskcMax.value)-3;i>0;i--){
			list.deleteRow( parseInt(5+3+3+parseInt(i)) );
		}
		for(var i=parseInt(document.forms[0].bfzwkcMax.value)-3;i>0;i--){
			list.deleteRow( parseInt(6+3+3+3+parseInt(i)) );
		}
		for(var i=parseInt(document.forms[0].bfzydwkcMax.value)-3;i>0;i--){
			list.deleteRow( parseInt(7+3+3+3+3+parseInt(i)) );
		}
		for(var i=parseInt(document.forms[0].fgyjjxMax.value)-3;i>0;i--){
			list.deleteRow( parseInt(8+3+3+3+3+3+parseInt(i)) );
		}
		
		<%
		JzzcmxbForm jzzcmxbForm=(JzzcmxbForm)request.getAttribute("jzzcmxbForm");
		if (jzzcmxbForm!=null && jzzcmxbForm.getDataList().size()>0){
			/*��ʾ�̶���������*/
			for(int i=0;i<jzzcmxbForm.getDataList().size();i++){
				HashMap map=(HashMap)jzzcmxbForm.getDataList().get(i);
				int hc=Integer.parseInt((String)map.get("hc"));
				String L1=(String)map.get("L1");
				String L2=(String)map.get("L2");
				String L3=(String)map.get("L3");
				String L4=(String)map.get("L4");
				String L5=(String)map.get("L5");
				%>
				obj = eval("document.getElementById('L1_<%=hc%>')");
				obj.value = '<%=L1%>';
				
				obj = eval("document.getElementById('L2_<%=hc%>')");
				obj.value = '<%=L2%>';
				
				obj = eval("document.getElementById('L3_<%=hc%>')");
				obj.value = '<%=L3%>';
				
				obj = eval("document.getElementById('L4_<%=hc%>')");
				obj.value = '<%=L4%>';
				
				obj = eval("document.getElementById('L5_<%=hc%>')");
				obj.value = '<%=L5%>';
				<% 
			}
		}
		
		/*ȫ��۳��ľ����ϼ�*/
		if(jzzcmxbForm!=null && jzzcmxbForm.getQekclist().size()>3){
			/*����������ʱ����ѭ��������У��ٽ�ֵ��������������ʾ��������̬���С���̬���У�*/
			
			//��̬������
			int dynamicRows=jzzcmxbForm.getQekclist().size()-3;
			for(int i=0;i<dynamicRows;i++){
				%>
				DynamicAddQekc();
				<%
			}
		}
		
		//��ֵ
		if(jzzcmxbForm!=null ){
			for(int i=0;i<jzzcmxbForm.getQekclist().size();i++){
				HashMap map=(HashMap)jzzcmxbForm.getQekclist().get(i);
				String strL1=(String)map.get("qekc_L1");
				String strL2=(String)map.get("qekc_L2");
				String strL3=(String)map.get("qekc_L3");
				String strL4=(String)map.get("qekc_L4");
				String strL5=(String)map.get("qekc_L5");
				String strL6=(String)map.get("qekc_L6");
				%>
				setQekcValue('<%=i%>','<%=strL1%>','<%=strL2%>','<%=strL3%>','<%=strL4%>','<%=strL5%>','<%=strL6%>');
				<%
			}
		}
		
		/*��10%�۳��ľ����ϼ�*/
		if(jzzcmxbForm!=null && jzzcmxbForm.getBfzskclist().size()>3){
			/*����������ʱ����ѭ��������У��ٽ�ֵ��������������ʾ��������̬���С���̬���У�*/
			
			//��̬������
			int dynamicRows=jzzcmxbForm.getBfzskclist().size()-3;
			for(int i=0;i<dynamicRows;i++){
				%>
				DynamicAddBfzskc();
				<%
			}
		}
		
		//��ֵ
		if(jzzcmxbForm!=null ){
			for(int i=0;i<jzzcmxbForm.getBfzskclist().size();i++){
				HashMap map=(HashMap)jzzcmxbForm.getBfzskclist().get(i);
				String strL1=(String)map.get("bfzskc_L1");
				String strL2=(String)map.get("bfzskc_L2");
				String strL3=(String)map.get("bfzskc_L3");
				String strL4=(String)map.get("bfzskc_L4");
				String strL5=(String)map.get("bfzskc_L5");
				String strL6=(String)map.get("bfzskc_L6");
				%>
				setBfzskcValue('<%=i%>','<%=strL1%>','<%=strL2%>','<%=strL3%>','<%=strL4%>','<%=strL5%>','<%=strL6%>');
				<%
			}
		}	
		
		/*��3%�۳��ľ����ϼ�*/
		if(jzzcmxbForm!=null && jzzcmxbForm.getBfzwkclist().size()>3){
			/*����������ʱ����ѭ��������У��ٽ�ֵ��������������ʾ��������̬���С���̬���У�*/
			
			//��̬������
			int dynamicRows=jzzcmxbForm.getBfzwkclist().size()-3;
			for(int i=0;i<dynamicRows;i++){
				%>
				DynamicAddBfzwkc();
				<%
			}
		}
		
		//��ֵ
		if(jzzcmxbForm!=null ){
			for(int i=0;i<jzzcmxbForm.getBfzwkclist().size();i++){
				HashMap map=(HashMap)jzzcmxbForm.getBfzwkclist().get(i);
				String strL1=(String)map.get("bfzwkc_L1");
				String strL2=(String)map.get("bfzwkc_L2");
				String strL3=(String)map.get("bfzwkc_L3");
				String strL4=(String)map.get("bfzwkc_L4");
				String strL5=(String)map.get("bfzwkc_L5");
				String strL6=(String)map.get("bfzwkc_L6");
				%>
				setBfzwkcValue('<%=i%>','<%=strL1%>','<%=strL2%>','<%=strL3%>','<%=strL4%>','<%=strL5%>','<%=strL6%>');
				<%
			}
		}	
		
		
		/*��1.5%�۳��ľ����ϼ�*/
		if(jzzcmxbForm!=null && jzzcmxbForm.getBfzydwkclist().size()>3){
			/*����������ʱ����ѭ��������У��ٽ�ֵ��������������ʾ��������̬���С���̬���У�*/
			
			//��̬������
			int dynamicRows=jzzcmxbForm.getBfzydwkclist().size()-3;
			for(int i=0;i<dynamicRows;i++){
				%>
				DynamicAddBfzydwkc();
				<%
			}
		}
		
		//��ֵ
		if(jzzcmxbForm!=null ){
			for(int i=0;i<jzzcmxbForm.getBfzydwkclist().size();i++){
				HashMap map=(HashMap)jzzcmxbForm.getBfzydwkclist().get(i);
				String strL1=(String)map.get("bfzydwkc_L1");
				String strL2=(String)map.get("bfzydwkc_L2");
				String strL3=(String)map.get("bfzydwkc_L3");
				String strL4=(String)map.get("bfzydwkc_L4");
				String strL5=(String)map.get("bfzydwkc_L5");
				String strL6=(String)map.get("bfzydwkc_L6");
				%>
				setBfzydwkcValue('<%=i%>','<%=strL1%>','<%=strL2%>','<%=strL3%>','<%=strL4%>','<%=strL5%>','<%=strL6%>');
				<%
			}
		}	
		
		
		
		/*�ǹ���ȼ��Ծ����ϼ�*/
		if(jzzcmxbForm!=null && jzzcmxbForm.getFgyjjxlist().size()>3){
			/*����������ʱ����ѭ��������У��ٽ�ֵ��������������ʾ��������̬���С���̬���У�*/
			
			//��̬������
			int dynamicRows=jzzcmxbForm.getFgyjjxlist().size()-3;
			for(int i=0;i<dynamicRows;i++){
				%>
				DynamicAddFgyjjx();
				<%
			}
		}
		
		//��ֵ
		if(jzzcmxbForm!=null ){
			for(int i=0;i<jzzcmxbForm.getFgyjjxlist().size();i++){
				HashMap map=(HashMap)jzzcmxbForm.getFgyjjxlist().get(i);
				String strL1=(String)map.get("fgyjjx_L1");
				String strL2=(String)map.get("fgyjjx_L2");
				String strL3=(String)map.get("fgyjjx_L3");
				String strL4=(String)map.get("fgyjjx_L4");
				String strL5=(String)map.get("fgyjjx_L5");
				String strL6=(String)map.get("fgyjjx_L6");
				%>
				setFgyjjxValue('<%=i%>','<%=strL1%>','<%=strL2%>','<%=strL3%>','<%=strL4%>','<%=strL5%>','<%=strL6%>');
				<%
			}
		}		
		%>
	}	
	
	
	//2���д� �������� ��ActionForm��ȡList���ݲ��뵽table	
	function DynamicAddQekc()
	{
		//initNumText("qekc_L3",parseInt(document.forms[0].qekcMax.value));
		
		var list=document.getElementById("jzzc_list");

		//��ǰ��ȫ��۳��ľ����ϼơ����������
		var hc=document.forms[0].qekcMax.value;
		
		var newRow=list.insertRow(4+parseInt(hc));
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp;";
		
		//������Ŀ
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds3';
		newCell.innerHTML="<input type='text' name='qekc_L1' value='' size='20' tabindex='2'>";
		
		//��Ӫ������������һ�������
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qekc_L2' value='' size='13' tabindex='2'>";
		
		//���
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qekc_L3' value='' size='13' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//�����۳�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qekc_L4' value='*' tabindex='-1' size='1' readonly=true class='text-noborder' style='text-align:center'>";
		
		//�۳��޶�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qekc_L5' value='*' tabindex='-1' size='1' readonly=true class='text-noborder' style='text-align:center'>";
		
		//����˰ǰ�۳��Ĺ���ȼ��Ծ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qekc_L6' value='*' tabindex='-1' size='1' readonly=true class='text-noborder' style='text-align:center'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk_1' value=''>";
		
		//��ǰ��ȫ��۳��ľ����ϼơ������������1
		document.forms[0].qekcMax.value=parseInt(document.forms[0].qekcMax.value)+1;
		
	}
	
	//ȫ��۳����и�ֵ
	function setQekcValue(i,L1,L2,L3,L4,L5,L6)
	{
		var qekc_L1=document.all("qekc_L1");
		qekc_L1[parseInt(i)].value=L1
		
		var qekc_L2=document.all("qekc_L2");
		qekc_L2[parseInt(i)].value=L2
		
		var qekc_L3=document.all("qekc_L3");
		qekc_L3[parseInt(i)].value=L3
		
		var qekc_L4=document.all("qekc_L4");
		qekc_L4[parseInt(i)].value=L4
		
		var qekc_L5=document.all("qekc_L5");
		qekc_L5[parseInt(i)].value=L5
		
		var qekc_L6=document.all("qekc_L6");
		qekc_L6[parseInt(i)].value=L6
	}
	
	//3���д� �������� ��ActionForm��ȡList���ݲ��뵽table	
	function DynamicAddBfzskc()
	{		
		//initNumText("bfzskc_L3",parseInt(document.forms[0].bfzskcMax.value));
		
		var list=document.getElementById("jzzc_list");

		//��ǰ��ȫ��۳��ľ����ϼơ����������
		var qekchc=document.forms[0].qekcMax.value;

		//��ǰ����10%�۳��ľ����ϼ� �����������
		var bfzskchc=document.forms[0].bfzskcMax.value;
		
		var newRow=list.insertRow(5+parseInt(qekchc)+parseInt(bfzskchc));
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp;";

		//������Ŀ
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds3';
		newCell.innerHTML="<input type='text' name='bfzskc_L1' value='' size='20' tabindex='2'>";
		                                                       
		//��Ӫ������������һ�������                         
		var newCell=newRow.insertCell();                       
		newCell.className='2-td2-left';                        
		newCell.innerHTML="<input type='text' name='bfzskc_L2' value='' size='13' tabindex='2'>";
		
		//���
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzskc_L3'value='' size='13' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//�����۳�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzskc_L4'value='*' tabindex='-1' size='1' readonly=true class='text-noborder' style='text-align:center'>";
		
		//�۳��޶�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzskc_L5'value='*' tabindex='-1' size='1' readonly=true class='text-noborder' style='text-align:center'>";
		
		//����˰ǰ�۳��Ĺ���ȼ��Ծ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzskc_L6'value='*' tabindex='-1' size='1' readonly=true class='text-noborder' style='text-align:center'>";
		
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk_2' value=''>";
		
		//��ǰ����10%�۳��ľ����ϼơ������������1
		document.forms[0].bfzskcMax.value=parseInt(document.forms[0].bfzskcMax.value)+1;
		
	}
	
	//��10%�۳��ľ����ϼ����и�ֵ
	function setBfzskcValue(i,L1,L2,L3,L4,L5,L6)
	{
		var bfzskc_L1=document.all("bfzskc_L1");
		bfzskc_L1[parseInt(i)].value=L1
		
		var bfzskc_L2=document.all("bfzskc_L2");
		bfzskc_L2[parseInt(i)].value=L2
		
		var bfzskc_L3=document.all("bfzskc_L3");
		bfzskc_L3[parseInt(i)].value=L3
		
		var bfzskc_L4=document.all("bfzskc_L4");
		bfzskc_L4[parseInt(i)].value=L4
		
		var bfzskc_L5=document.all("bfzskc_L5");
		bfzskc_L5[parseInt(i)].value=L5
		
		var bfzskc_L6=document.all("bfzskc_L6");
		bfzskc_L6[parseInt(i)].value=L6
	}
	
	//4���д� �������� ��ActionForm��ȡList���ݲ��뵽table	
	function DynamicAddBfzwkc()
	{		
		//initNumText("bfzwkc_L3",parseInt(document.forms[0].bfzwkcMax.value));
		
		var list=document.getElementById("jzzc_list");

		//��ǰ��ȫ��۳��ľ����ϼơ����������
		var qekchc=document.forms[0].qekcMax.value;

		//��ǰ����10%�۳��ľ����ϼơ����������
		var bfzskchc=document.forms[0].bfzskcMax.value;
		
		//��ǰ����3%�۳��ľ����ϼơ����������
		var bfzwkchc=document.forms[0].bfzwkcMax.value;
		
		var newRow=list.insertRow(6+parseInt(qekchc)+parseInt(bfzskchc)+parseInt(bfzwkchc));
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp;";

		//������Ŀ
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds3';
		newCell.innerHTML="<input type='text' name='bfzwkc_L1' value='' size='20' tabindex='2'>";
		
		//��Ӫ������������һ�������
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzwkc_L2' value='' size='13' tabindex='2'>";
		
		//���
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzwkc_L3' value='' size='13' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//�����۳�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzwkc_L4' value='*' tabindex='-1' size='1' readonly=true class='text-noborder' style='text-align:center'>";
		                                                              
		//�۳��޶�                                                    
		var newCell=newRow.insertCell();                              
		newCell.className='2-td2-left';                               
		newCell.innerHTML="<input type='text' name='bfzwkc_L5' value='*' tabindex='-1' size='1' readonly=true class='text-noborder' style='text-align:center'>";
		                                                              
		//����˰ǰ�۳��Ĺ���ȼ��Ծ�����                              
		var newCell=newRow.insertCell();                              
		newCell.className='2-td2-left';                               
		newCell.innerHTML="<input type='text' name='bfzwkc_L6' value='*' tabindex='-1' size='1' readonly=true class='text-noborder' style='text-align:center'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk_3' value=''>";
		
		//��ǰ����3%�۳��ľ����ϼơ������������1
		document.forms[0].bfzwkcMax.value=parseInt(document.forms[0].bfzwkcMax.value)+1;
		
	}
	
	//ȫ��۳����и�ֵ
	function setBfzwkcValue(i,L1,L2,L3,L4,L5,L6)
	{
		var bfzwkc_L1=document.all("bfzwkc_L1");
		bfzwkc_L1[parseInt(i)].value=L1
		
		var bfzwkc_L2=document.all("bfzwkc_L2");
		bfzwkc_L2[parseInt(i)].value=L2
		
		var bfzwkc_L3=document.all("bfzwkc_L3");
		bfzwkc_L3[parseInt(i)].value=L3
		
		var bfzwkc_L4=document.all("bfzwkc_L4");
		bfzwkc_L4[parseInt(i)].value=L4
		
		var bfzwkc_L5=document.all("bfzwkc_L5");
		bfzwkc_L5[parseInt(i)].value=L5
		
		var bfzwkc_L6=document.all("bfzwkc_L6");
		bfzwkc_L6[parseInt(i)].value=L6
	}
	
	//5���д� �������� ��ActionForm��ȡList���ݲ��뵽table	
	function DynamicAddBfzydwkc(hc,L1,L2,L3,L4,L5,L6)
	{		
		//initNumText("bfzydwkc_L3",parseInt(document.forms[0].bfzydwkcMax.value));
		
		var list=document.getElementById("jzzc_list");

		//��ǰ��ȫ��۳��ľ����ϼơ����������
		var qekchc=document.forms[0].qekcMax.value;

		//��ǰ����10%�۳��ľ����ϼơ����������
		var bfzskchc=document.forms[0].bfzskcMax.value;
		
		//��ǰ����3%�۳��ľ����ϼơ����������
		var bfzwkchc=document.forms[0].bfzwkcMax.value;
	
		//��ǰ����1.5%�۳��ľ����ϼơ����������
		var bfzydwkchc=document.forms[0].bfzydwkcMax.value;
		
		var newRow=list.insertRow(7+parseInt(qekchc)+parseInt(bfzskchc)+parseInt(bfzwkchc)+parseInt(bfzydwkchc));
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp;";

		//������Ŀ
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds3';
		newCell.innerHTML="<input type='text' name='bfzydwkc_L1' value='' size='20' tabindex='2'>";
		
		//��Ӫ������������һ�������
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzydwkc_L2' value='' size='13' tabindex='2'>";
		
		//���
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzydwkc_L3' value='' size='13' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//�����۳�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzydwkc_L4' value='*' tabindex='-1' size='1' readonly=true class='text-noborder' style='text-align:center'>";
		
		//�۳��޶�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzydwkc_L5' value='*' tabindex='-1' size='1' readonly=true class='text-noborder' style='text-align:center'>";
		
		//����˰ǰ�۳��Ĺ���ȼ��Ծ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzydwkc_L6' value='*' tabindex='-1' size='1' readonly=true class='text-noborder' style='text-align:center'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk_4' value=''>";
		
		//��ǰ����1.5%�۳��ľ����ϼơ������������1
		document.forms[0].bfzydwkcMax.value=parseInt(document.forms[0].bfzydwkcMax.value)+1;
		
	}
	
	//��1.5%�۳��ľ����ϼ�
	function setBfzydwkcValue(i,L1,L2,L3,L4,L5,L6){
		var bfzydwkc_L1=document.all("bfzydwkc_L1");
		bfzydwkc_L1[parseInt(i)].value=L1
		
		var bfzydwkc_L2=document.all("bfzydwkc_L2");
		bfzydwkc_L2[parseInt(i)].value=L2
		
		var bfzydwkc_L3=document.all("bfzydwkc_L3");
		bfzydwkc_L3[parseInt(i)].value=L3
		
		var bfzydwkc_L4=document.all("bfzydwkc_L4");
		bfzydwkc_L4[parseInt(i)].value=L4
		
		var bfzydwkc_L5=document.all("bfzydwkc_L5");
		bfzydwkc_L5[parseInt(i)].value=L5
		
		var bfzydwkc_L6=document.all("bfzydwkc_L6");
		bfzydwkc_L6[parseInt(i)].value=L6
	}
	
	//6���д� �������� ��ActionForm��ȡList���ݲ��뵽table	
	function DynamicAddFgyjjx(hc,L1,L2,L3,L4,L5,L6)
	{		
		//initNumText("fgyjjx_L3",parseInt(document.forms[0].fgyjjxMax.value));
		
		var list=document.getElementById("jzzc_list");

		//��ǰ��ȫ��۳��ľ����ϼơ����������
		var qekchc=document.forms[0].qekcMax.value;

		//��ǰ����10%�۳��ľ����ϼơ����������
		var bfzskchc=document.forms[0].bfzskcMax.value;
		
		//��ǰ����3%�۳��ľ����ϼơ����������
		var bfzwkchc=document.forms[0].bfzwkcMax.value;
	
		//��ǰ����1.5%�۳��ľ����ϼơ����������
		var bfzydwkchc=document.forms[0].bfzydwkcMax.value;

		//��ǰ���ǹ���ȼ��Ծ����ϼơ����������
		var fgyjjxhc=document.forms[0].fgyjjxMax.value;
		
		var newRow=list.insertRow(8+parseInt(qekchc)+parseInt(bfzskchc)+parseInt(bfzwkchc)+parseInt(bfzydwkchc)+parseInt(fgyjjxhc));
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp;";

		//������Ŀ
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds2';
		newCell.innerHTML="<input type='text' name='fgyjjx_L1' value='' size='20' tabindex='2'>";
		
		//��Ӫ������������һ�������
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='fgyjjx_L2' value='' size='13' tabindex='2'>";
		
		//���
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='fgyjjx_L3' value='' size='13' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//�����۳�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='fgyjjx_L4' value='*' tabindex='-1' size='1' readonly=true class='text-noborder' style='text-align:center'>";
		
		//�۳��޶�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='fgyjjx_L5' value='*' tabindex='-1' size='1' readonly=true class='text-noborder' style='text-align:center'>";
		
		//����˰ǰ�۳��Ĺ���ȼ��Ծ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='fgyjjx_L6' value='*' tabindex='-1' size='1' readonly=true class='text-noborder' style='text-align:center'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk_5' value=''>";
		
		//��ǰ���ǹ���ȼ��Ծ����ϼơ������������1
		document.forms[0].fgyjjxMax.value=parseInt(document.forms[0].fgyjjxMax.value)+1;
	}
	
	//�ǹ���ȼ��Ծ����ϼ�
	function setFgyjjxValue(i,L1,L2,L3,L4,L5,L6){
		var fgyjjx_L1=document.all("fgyjjx_L1");
		fgyjjx_L1[parseInt(i)].value=L1
		
		var fgyjjx_L2=document.all("fgyjjx_L2");
		fgyjjx_L2[parseInt(i)].value=L2
		
		var fgyjjx_L3=document.all("fgyjjx_L3");
		fgyjjx_L3[parseInt(i)].value=L3
		
		var fgyjjx_L4=document.all("fgyjjx_L4");
		fgyjjx_L4[parseInt(i)].value=L4
		
		var fgyjjx_L5=document.all("fgyjjx_L5");
		fgyjjx_L5[parseInt(i)].value=L5
		
		var fgyjjx_L6=document.all("fgyjjx_L6");
		fgyjjx_L6[parseInt(i)].value=L6
	}
	
	//����"ȫ��۳��ľ����ϼ�"����
	function InsertQekcRow()
	{
		var list=document.getElementById("jzzc_list");

		//��ǰ��ȫ��۳������������
		var hc=document.forms[0].qekcMax.value;
		
		//�����µ�����
		var newRow=list.insertRow(4+parseInt(hc));
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp;";

		//������Ŀ
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds3';
		newCell.innerHTML="<input type='text' name='qekc_L1' id='qekc_L1_"+parseInt(hc)+"' value='' size='20' tabindex='2'>";
		
		//��Ӫ������������һ�������
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qekc_L2' id='qekc_L2_"+parseInt(hc)+"' value='' size='13' tabindex='2'>";
		
		//���
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qekc_L3' id='qekc_L3_"+parseInt(hc)+"' value='' size='13'  style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' tabindex='2'>";
		
		//�����۳�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qekc_L4' id='qekc_L4_"+parseInt(hc)+"' value='*' tabindex='-1' size='1' class='text-noborder' readonly='true'>";
		
		//�۳��޶� 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qekc_L5' id='qekc_L5_"+parseInt(hc)+"' value='*' tabindex='-1' size='1' class='text-noborder' readonly='true'>";
		
		
		//����˰ǰ�۳��Ĺ���ȼ��Ծ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='qekc_L6' id='qekc_L6_"+parseInt(hc)+"' value='*' tabindex='-1' size='1' class='text-noborder' readonly='true'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk_1' value=''>";
		
		//��ǰ��ȫ��۳��ľ����ϼơ������������1
		document.forms[0].qekcMax.value=parseInt(document.forms[0].qekcMax.value)+1;
		
	}
	
	//ɾ��"ȫ��۳��ľ����ϼ�"����
	function RomoveQekcRow( )
	{
		//��ѡ��
		var arrChk=document.all("chk_1");
		var list=document.getElementById("jzzc_list");
		
		//��ǰ��ȫ��۳��ľ����ϼơ����������
		var hc=document.forms[0].qekcMax.value;
		
		if(parseInt(document.forms[0].qekcMax.value)>3){

			if(!window.confirm("ȷ��ɾ����")){
				return;
			}

			if(arrChk.length==undefined){
				if(arrChk.checked){
					list.deleteRow(4+3);
					document.forms[0].qekcMax.value=parseInt(document.forms[0].qekcMax.value)-1;
				}
			}else{				
				for(var i=parseInt(parseInt(document.forms[0].qekcMax.value)-1);i>=3;i--){
					if(arrChk[i-3].checked){
						list.deleteRow( parseInt(4+parseInt(i)));
						document.forms[0].qekcMax.value=parseInt(document.forms[0].qekcMax.value)-1;
					}
				}
			}
			
		}else{
				alert("��δ�����κΡ�ȫ��۳��ľ����ϼơ����У�");		
		}		
	}	


	
	//���롰��10%�۳��ľ����ϼơ�����
	function InsertBfzskcRow()
	{
		var list=document.getElementById("jzzc_list");

		//��ǰ��ȫ��۳��ľ����ϼơ����������
		var qekchc=document.forms[0].qekcMax.value;
		
		//��ǰ����10%�۳��ľ����ϼơ����������
		var bfzskchc=document.forms[0].bfzskcMax.value;	
		
		//������
		var newRow=list.insertRow(5+parseInt(qekchc)+parseInt(bfzskchc));		
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp;";

		//������Ŀ����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds3';
		newCell.innerHTML="<input type='text' name='bfzskc_L1' id='bfzskc_L1_"+parseInt(bfzskchc)+"' value='' size='20' tabindex='2'>";
		
		//��Ӫ������������һ�������
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzskc_L2' id='bfzskc_L2_"+parseInt(bfzskchc)+"' value='' size='13' tabindex='2'>";
		
		//���
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzskc_L3' id='bfzskc_L3_"+parseInt(bfzskchc)+"' value='' size='13' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//�����۳�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzskc_L4' id='bfzskc_L4_"+parseInt(bfzskchc)+"' value='*' tabindex='-1' size='1' class='text-noborder' style='text-align:center' readonly='true'>";
		
		//�۳��޶� 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzskc_L5' id='bfzskc_L5_"+parseInt(bfzskchc)+"' value='*' tabindex='-1' size='1' class='text-noborder' style='text-align:center' readonly='true'>";
		
		//����˰ǰ�۳��Ĺ���ȼ��Ծ����� 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzskc_L6' id='bfzskc_L6_"+parseInt(bfzskchc)+"' value='*' tabindex='-1' size='1' class='text-noborder' style='text-align:center' readOnly='true'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk_2' value=''>";
		
		//��ǰ����10%�۳��ľ����ϼ�"�����������1
		document.forms[0].bfzskcMax.value=parseInt(document.forms[0].bfzskcMax.value)+1;
		
	}
	
	
	//ɾ��"��10%�۳��ľ����ϼ�"����
	function RomoveBfzskcRow()
	{

		//��ѡ��
		var arrChk=document.all("chk_2");
		var list=document.getElementById("jzzc_list");		
		var qekchc=document.forms[0].qekcMax.value;

		if(parseInt(document.forms[0].bfzskcMax.value)>3){

			if(!window.confirm("ȷ��ɾ����")){
				return;
			}

			if(arrChk.length==undefined){
				if(arrChk.checked){
					list.deleteRow(parseInt(qekchc)+5+3);
					document.forms[0].bfzskcMax.value=parseInt(document.forms[0].bfzskcMax.value)-1;
				}
			}else{				
				for(var i=parseInt(parseInt(document.forms[0].bfzskcMax.value)-1);i>=3;i--){
					if(arrChk[i-3].checked){
						list.deleteRow( parseInt(5+parseInt(qekchc)+parseInt(i)));
						document.forms[0].bfzskcMax.value=parseInt(document.forms[0].bfzskcMax.value)-1;
					}
				}
			}
			
		}else{
				alert("��δ�����κΡ���10%�۳��ľ����ϼơ����У�");		
		}
	}	

	
	//���롰��3%�۳��ľ����ϼơ�������
	function InsertBfzwkcRow()
	{
		
		var list=document.getElementById("jzzc_list");

		//��ǰ��ȫ��۳��ľ����ϼơ����������
		var qekchc=document.forms[0].qekcMax.value;
		
		//��ǰ����10%�۳��ľ����ϼơ����������
		var bfzskchc=document.forms[0].bfzskcMax.value;	
		
		//��ǰ����3%�۳��ľ����ϼơ����������
		var bfzwkchc=document.forms[0].bfzwkcMax.value;
		
		//��������
		var newRow=list.insertRow(6+parseInt(qekchc)+parseInt(bfzskchc)+parseInt(bfzwkchc));		
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp;";

		//������Ŀ����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds3';
		newCell.innerHTML="<input type='text' name='bfzwkc_L1' id='bfzwkc_L1_"+parseInt(bfzwkchc)+"' value='' size='20' tabindex='2'>";
		
		//��Ӫ������������һ�������
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzwkc_L2' id='bfzwkc_L2_"+parseInt(bfzwkchc)+"' value='' size='13' tabindex='2'>";
		
		//���
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzwkc_L3' id='bfzwkc_L3_"+parseInt(bfzwkchc)+"' value='' size='13' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//�����۳�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzwkc_L4' id='bfzwkc_L4_"+parseInt(bfzwkchc)+"' value='*' tabindex='-1' size='1' class='text-noborder' style='text-align:center' readonly='true'>";
		
		//�۳��޶� 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzwkc_L5' id='bfzwkc_L5_"+parseInt(bfzwkchc)+"' value='*' tabindex='-1' size='1' class='text-noborder' style='text-align:center' readonly='true'>";
		
		//����˰ǰ�۳��Ĺ���ȼ��Ծ����� 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzwkc_L6' id='bfzwkc_L6_"+parseInt(bfzwkchc)+"' value='*' tabindex='-1' size='1' class='text-noborder' style='text-align:center' readOnly='true'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk_3' value=''>";
		
		//��ǰ����3%�۳��ľ����ϼ�"�����������1
		document.forms[0].bfzwkcMax.value=parseInt(document.forms[0].bfzwkcMax.value)+1;
		
	}	
	
	//ɾ��"��3%�۳��ľ����ϼ�"����
	function RomoveBfzwkcRow()
	{
		
		//��ѡ��
		var arrChk=document.all("chk_3");
		var list=document.getElementById("jzzc_list");		
		var qekchc=document.forms[0].qekcMax.value;
		var bfzskchc=document.forms[0].bfzskcMax.value;
		
		if(parseInt(document.forms[0].bfzwkcMax.value)>3){

			if(!window.confirm("ȷ��ɾ����")){
				return;
			}

			if(arrChk.length==undefined){
				if(arrChk.checked){
					list.deleteRow(parseInt(qekchc)+parseInt(bfzskchc)+6+3);
					document.forms[0].bfzwkcMax.value=parseInt(document.forms[0].bfzwkcMax.value)-1;
				}
			}else{				
				for(var i=parseInt(parseInt(document.forms[0].bfzwkcMax.value)-1);i>=3;i--){
					if(arrChk[i-3].checked){
						list.deleteRow(parseInt(6+parseInt(qekchc)+parseInt(bfzskchc)+parseInt(i)));
						document.forms[0].bfzwkcMax.value=parseInt(document.forms[0].bfzwkcMax.value)-1;
					}
				}
			}
			
		}else{
				alert("��δ�����κ� ��3%�۳��ľ����ϼ� ���У�");		
		}
	}
	
	
	//����"��1.5%�۳��ľ����ϼ�"����
	function InsertBfzydwkcRow()
	{
		
		var list=document.getElementById("jzzc_list");

		//��ǰ��ȫ��۳��ľ����ϼơ����������
		var qekchc=document.forms[0].qekcMax.value;
		
		//��ǰ����10%�۳��ľ����ϼơ����������
		var bfzskchc=document.forms[0].bfzskcMax.value;	
		
		//��ǰ����3%�۳��ľ����ϼơ����������
		var bfzwkchc=document.forms[0].bfzwkcMax.value;
		
		//��ǰ����1.5%�۳��ľ����ϼơ����������
		var bfzydwkchc=document.forms[0].bfzydwkcMax.value;
		
		//��������
		var newRow=list.insertRow(7+parseInt(qekchc)+parseInt(bfzskchc)+parseInt(bfzwkchc)+parseInt(bfzydwkchc));		
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp;";

		//������Ŀ����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds3';
		newCell.innerHTML="<input type='text' name='bfzydwkc_L1' id='bfzydwkc_L1_"+parseInt(bfzydwkchc)+"' value='' size='20' tabindex='2'>";
		
		//��Ӫ������������һ�������
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzydwkc_L2' id='bfzydwkc_L2_"+parseInt(bfzydwkchc)+"' value='' size='13' tabindex='2'>";
		
		//���
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzydwkc_L3' id='bfzydwkc_L3_"+parseInt(bfzydwkchc)+"' value='' size='13' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//�����۳�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzydwkc_L4' id='bfzydwkc_L4_"+parseInt(bfzydwkchc)+"' value='*' tabindex='-1' size='1' class='text-noborder' style='text-align:center' readonly='true'>";
		
		//�۳��޶� 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzydwkc_L5' id='bfzydwkc_L5_"+parseInt(bfzydwkchc)+"' value='*' tabindex='-1' size='1' class='text-noborder' style='text-align:center' readonly='true'>";
		
		//����˰ǰ�۳��Ĺ���ȼ��Ծ����� 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='bfzydwkc_L6' id='bfzydwkc_L6_"+parseInt(bfzydwkchc)+"' value='*' tabindex='-1' size='1' class='text-noborder' style='text-align:center' readonly='true'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk_4' value=''>";
		
		//��ǰ����1.5%�۳��ľ����ϼ�"�����������1
		document.forms[0].bfzydwkcMax.value=parseInt(document.forms[0].bfzydwkcMax.value)+1;
		
	}	
	
	//ɾ��"��1.5%�۳��ľ����ϼ�"����
	function RomoveBfzydwkcRow()
	{
		
		//��ѡ��
		var arrChk=document.all("chk_4");
		var list=document.getElementById("jzzc_list");		
		var qekchc=document.forms[0].qekcMax.value;
		var bfzskchc=document.forms[0].bfzskcMax.value;
		var bfzwkchc=document.forms[0].bfzwkcMax.value;
		
		if(parseInt(document.forms[0].bfzydwkcMax.value)>3){

			if(!window.confirm("ȷ��ɾ����")){
				return;
			}

			if(arrChk.length==undefined){
				if(arrChk.checked){
					list.deleteRow(parseInt(qekchc)+parseInt(bfzskchc)+parseInt(bfzwkchc)+7+3);
					document.forms[0].bfzydwkcMax.value=parseInt(document.forms[0].bfzydwkcMax.value)-1;
				}
			}else{				
				for(var i=parseInt(parseInt(document.forms[0].bfzydwkcMax.value)-1);i>=3;i--){
					if(arrChk[i-3].checked){
						list.deleteRow(parseInt(7+parseInt(qekchc)+parseInt(bfzskchc)+parseInt(bfzwkchc)+parseInt(i)));
						document.forms[0].bfzydwkcMax.value=parseInt(document.forms[0].bfzydwkcMax.value)-1;
					}
				}
			}
			
		}else{
				alert("��δ�����κ� ��1.5%�۳��ľ����ϼ� ���У�");		
		}
	}	
	
	
	//����"�ǹ���ȼ��Ծ����ϼ�"����
		
			function InsertFgyjjxRow()
	{
			
		
		var list=document.getElementById("jzzc_list");

		//��ǰ��ȫ��۳��ľ����ϼơ����������
		var qekchc=document.forms[0].qekcMax.value;
		
		//��ǰ����10%�۳��ľ����ϼơ����������
		var bfzskchc=document.forms[0].bfzskcMax.value;	
		
		//��ǰ����3%�۳��ľ����ϼơ����������
		var bfzwkchc=document.forms[0].bfzwkcMax.value;
		
		//��ǰ����1.5%�۳��ľ����ϼơ����������
		var bfzydwkchc=document.forms[0].bfzydwkcMax.value;
		
		//��ǰ���ǹ���ȼ��Ծ����ϼ�"���������
		var fgyjjxhc=document.forms[0].fgyjjxMax.value;
		
		//��������
		var newRow=list.insertRow(8+parseInt(qekchc)+parseInt(bfzskchc)+parseInt(bfzwkchc)+parseInt(bfzydwkchc)+parseInt(fgyjjxhc));
		
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp;";

		//������Ŀ����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left-qysds2';
		newCell.innerHTML="<input type='text' name='fgyjjx_L1' id='fgyjjx_L1_"+parseInt(fgyjjxhc)+"' value='' size='20' tabindex='2'>";
		
		//��Ӫ������������һ�������
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='fgyjjx_L2' id='fgyjjx_L2_"+parseInt(fgyjjxhc)+"' value='' size='13' tabindex='2'>";
		
		//���
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='fgyjjx_L3' id='fgyjjx_L3_"+parseInt(fgyjjxhc)+"' value='' size='13' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//�����۳�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='fgyjjx_L4' id='fgyjjx_L4_"+parseInt(fgyjjxhc)+"' value='*' tabindex='-1' size='1' class='text-noborder' style='text-align:center' readonly='true'>";
		
		//�۳��޶� 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='fgyjjx_L5' id='fgyjjx_L5_"+parseInt(fgyjjxhc)+"' value='*' tabindex='-1' size='1' class='text-noborder' style='text-align:center' readonly='true'>";
		
		//����˰ǰ�۳��Ĺ���ȼ��Ծ����� 
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='fgyjjx_L6' id='fgyjjx_L6_"+parseInt(fgyjjxhc)+"' value='*' tabindex='-1' size='1' class='text-noborder' style='text-align:center' readonly='true'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='chk_5' value=''>";
		
		//��ǰ���ǹ���ȼ��Ծ���"�����������1
		document.forms[0].fgyjjxMax.value=parseInt(document.forms[0].fgyjjxMax.value)+1;
		
	}
	
	
	//ɾ��"�ǹ���ȼ��Ծ����ϼ�"����
	function RomoveFgyjjxRow()
	{
		
		//��ѡ��
		var arrChk=document.all("chk_5");
		var list=document.getElementById("jzzc_list");		
		var qekchc=document.forms[0].qekcMax.value;
		var bfzskchc=document.forms[0].bfzskcMax.value;
		var bfzwkchc=document.forms[0].bfzwkcMax.value;
		var bfzydwkchc=document.forms[0].bfzydwkcMax.value;
			
		if(parseInt(document.forms[0].fgyjjxMax.value)>3){

			if(!window.confirm("ȷ��ɾ����")){
				return;
			}

			if(arrChk.length==undefined){
				if(arrChk.checked){
					list.deleteRow(parseInt(qekchc)+parseInt(bfzskchc)+parseInt(bfzwkchc)+parseInt(bfzydwkchc)+8+3);
					document.forms[0].fgyjjxMax.value=parseInt(document.forms[0].fgyjjxMax.value)-1;
				}
			}else{				
				for(var i=parseInt(parseInt(document.forms[0].fgyjjxMax.value)-1);i>=3;i--){
					if(arrChk[i-3].checked){
						list.deleteRow(parseInt(8+parseInt(qekchc)+parseInt(bfzskchc)+parseInt(bfzwkchc)+parseInt(bfzydwkchc)+parseInt(i)));
						document.forms[0].fgyjjxMax.value=parseInt(document.forms[0].fgyjjxMax.value)-1;
					}
				}
			}
			
		}else{
				alert("��δ�����κΡ��ǹ���ȼ��Ծ����ϼơ����У�");		
		}
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
	<%/*���ڹ�ϵУ��*/%>
	function doCheck()
	{
		var list="<%=Constants.QYSDS_CONTROL_CHAR_FOR_JS%>";
		if (!Char_Vaildate(document.forms[0],list)){
			return false;	
		}
		doSubmitForm('Check');
	}	
	
	<%/*���*/%>
	function doReset()
	{
	    //��������̶��е�����
	    if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
	    {
		   	for(var i=1; i<=7; i++){
				obj = eval("document.getElementById('L1_" + i+"')");
				if(obj.readOnly!=true)
					obj.value = "";
				obj = eval("document.getElementById('L2_" + i+"')");
				if(obj.readOnly!=true)
					obj.value = "";
				obj = eval("document.getElementById('L3_" + i+"')");
				if(obj.readOnly!=true)
					obj.value = "";
				obj = eval("document.getElementById('L4_" + i+"')");
				if(obj.readOnly!=true)
					obj.value = "";
				obj = eval("document.getElementById('L5_" + i+"')");
				if(obj.readOnly!=true)
					obj.value = "";
			}
			//���������̬���е�����
			clean('qekc_L1','qekc_L2','qekc_L3','qekc_L4','qekc_L5','qekc_L6',document.forms[0].qekcMax.value);
			clean('bfzskc_L1','bfzskc_L2','bfzskc_L3','bfzskc_L4','bfzskc_L5','bfzskc_L6',document.forms[0].bfzskcMax.value);
			clean('bfzwkc_L1','bfzwkc_L2','bfzwkc_L3','bfzwkc_L4','bfzwkc_L5','bfzwkc_L6',document.forms[0].bfzwkcMax.value);
			clean('bfzydwkc_L1','bfzydwkc_L2','bfzydwkc_L3','bfzydwkc_L4','bfzydwkc_L5','bfzydwkc_L6',document.forms[0].bfzydwkcMax.value);	
			clean('fgyjjx_L1','fgyjjx_L2','fgyjjx_L3','fgyjjx_L4','fgyjjx_L5','fgyjjx_L6',document.forms[0].fgyjjxMax.value);	
		}
	}
	
	<%/*���������̬���е�����*/%>
	function clean(L1,L2,L3,L4,L5,L6,maxRow){
		var arr1=document.all(L1);
		var arr2=document.all(L2);	
		var arr3=document.all(L3);	
		var arr4=document.all(L4);	
		var arr5=document.all(L5);	
		var arr6=document.all(L6);
		for (var i=0;i<parseInt(maxRow);i++){					
			if(arr1[parseInt(i)].readOnly!=true)
				arr1[parseInt(i)].value=""	;		
			if(arr2[parseInt(i)].readOnly!=true)
				arr2[parseInt(i)].value=""	;			
			if(arr3[parseInt(i)].readOnly!=true)
				arr3[parseInt(i)].value=""	;			
			if(arr4[parseInt(i)].readOnly!=true)
				arr4[parseInt(i)].value=""	;			
			if(arr5[parseInt(i)].readOnly!=true)
				arr5[parseInt(i)].value="";				
			if(arr6[parseInt(i)].readOnly!=true)
				arr6[parseInt(i)].value="";
		}	
	}
	
	<%/*ɾ��*/%>
	function doDelete()
	{
		doSubmitForm('Delete');
	}
	
	
	<%/*����*/%>
	function doExit()
	{
		doSubmitForm('Exit');
	}	
		

</script>

<html:form action="/webapp/smsb/qysds/jzzcmxbAction.do">

	<html:hidden property="actionType" />
	<input type="hidden" name="qekcMax" value="3" />
	<input type="hidden" name="bfzskcMax" value="3" />
	<input type="hidden" name="bfzwkcMax" value="3" />
	<input type="hidden" name="bfzydwkcMax" value="3" />
	<input type="hidden" name="fgyjjxMax" value="3" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />

	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">����֧����ϸ��</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="jzzcmxbForm" property="sbrq" scope="request" filter="true" />
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
				name="jzzcmxbForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="jzzcmxbForm" property="nsrmc" scope="request" filter="true" />&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">
			<div id="Layer2" style="position:static; overflow: auto; ">
			<table id="jzzc_list" border="1" cellspacing="0" class="table-99"
				align="center">
				<tr>
					<td width="2%" rowspan="2" class="2-td1-left">�д�</td>
					<td width="22%" rowspan="2" class="2-td1-left">������Ŀ</td>
					<td width="14%" class="2-td1-left">��Ӫ������������һ�������</td>
					<td width="13%" class="2-td1-left">���</td>
					<td width="5%" class="2-td1-left">�����۳�����</td>
					<td width="13%" class="2-td1-left">�۳��޶�</td>
					<td width="13%" class="2-td1-center">����˰ǰ�۳��Ĺ���ȼ��Ծ�����</td>
				</tr>
				<tr>
					<td class="2-td2-left">1</td>
					<td class="2-td2-left" p>2</td>
					<td class="2-td2-left">3</td>
					<td class="2-td2-left">4</td>
					<td class="2-td2-center">5 ��2��4ʱ��5��2����2��4ʱ��5��4</td>
				</tr>

				<tr>
					<input type="hidden" name="hc" value="1" />
					<td class="2-td2-left" nowrap>1</td>
					<td class="2-td2-left-qysds1" nowrap>һ������ȼ��Ծ����ϼ�</td>
					<td class="2-td2-left" nowrap><input type='text' name='L1'
						id='L1_1' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'
						style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L2'
						id='L2_1' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L3'
						id='L3_1' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'
						style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L4'
						id='L4_1' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'
						style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text' name='L5'
						id='L5_1' value='' size='13' tabindex='2'></td>
				</tr>
				<tr>
					<input type="hidden" name="hc" value="2" />
					<td class="2-td2-left" nowrap>2</td>
					<td class="2-td2-left-qysds2" nowrap>��һ��ȫ��۳��ľ����ϼ�</td>
					<td class="2-td2-left" nowrap><input type='text' name='L1'
						id='L1_2' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'
						style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L2'
						id='L2_2' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L3'
						id='L3_2' value='100%' tabindex='-1' size='3' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L4'
						id='L4_2' value='' size='13' tabindex='2'></td>
					<td class="2-td2-center" nowrap><input type='text' name='L5'
						id='L5_2' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left"><input name="button" type="button" tabindex='-1'
						onClick="InsertQekcRow()" value="����"></td>
					<td class="2-td2-center"><input name="button" type="button" tabindex='-1'
						onClick="RomoveQekcRow()" value='ɾ��'></td>
				</tr>
				<tr>
					<td class="2-td2-left" nowrap>3</td>
					<td class="2-td2-left-qysds3" nowrap><input type='text'
						name='qekc_L1' id='qekc_L1_1' value='' size='20' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='qekc_L2'
						id='qekc_L2_1' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='qekc_L3'
						id='qekc_L3_1' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='qekc_L4'
						id='qekc_L4_1' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='qekc_L5'
						id='qekc_L5_1' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text' name='qekc_L6'
						id='qekc_L6_1' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
				</tr>
				<tr>
					<td class="2-td2-left" nowrap>4</td>
					<td class="2-td2-left-qysds3" nowrap><input type='text'
						name='qekc_L1' id='qekc_L1_2' value='' size='20' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='qekc_L2'
						id='qekc_L2_2' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='qekc_L3'
						id='qekc_L3_2' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='qekc_L4'
						id='qekc_L4_2' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='qekc_L5'
						id='qekc_L5_2' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text' name='qekc_L6'
						id='qekc_L6_2' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
				</tr>
				<tr>
					<td class="2-td2-left" nowrap>5</td>
					<td class="2-td2-left-qysds3" nowrap><input type='text'
						name='qekc_L1' id='qekc_L1_3' value='' size='20' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='qekc_L2'
						id='qekc_L2_3' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='qekc_L3'
						id='qekc_L3_3' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='qekc_L4'
						id='qekc_L4_3' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='qekc_L5'
						id='qekc_L5_3' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text' name='qekc_L6'
						id='qekc_L6_3' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
				</tr>

				<tr>
					<input type="hidden" name="hc" value="3" />
					<td class="2-td2-left" nowrap>6</td>
					<td class="2-td2-left-qysds2" nowrap>��������10%�۳��ľ����ϼ�</td>
					<td class="2-td2-left" nowrap><input type='text' name='L1'
						id='L1_3' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'
						style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L2'
						id='L2_3' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L3'
						id='L3_3' value='10%' tabindex='-1' size='2' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L4'
						id='L4_3' value='' size='13' tabindex='2'></td>
					<td class="2-td2-center" nowrap><input type='text' name='L5'
						id='L5_3' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left"><input name="button" type="button" tabindex='-1'
						value="����" onClick="InsertBfzskcRow()"></td>
					<td class="2-td2-center"><input name="button" type="button" tabindex='-1'
						value='ɾ��' onClick="RomoveBfzskcRow()"></td>
				</tr>
				<tr>
					<td class="2-td2-left" nowrap>7</td>
					<td class="2-td2-left-qysds3" nowrap><input type='text'
						name='bfzskc_L1' id='bfzskc_L1_1' value='' size='20' tabindex='2'>
					</td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzskc_L2'
						id='bfzskc_L2_1' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzskc_L3'
						id='bfzskc_L3_1' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzskc_L4'
						id='bfzskc_L4_1' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzskc_L5'
						id='bfzskc_L5_1' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text' name='bfzskc_L6'
						id='bfzskc_L6_1' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
				</tr>
				<tr>
					<td class="2-td2-left" nowrap>8</td>
					<td class="2-td2-left-qysds3" nowrap><input type='text'
						name='bfzskc_L1' id='bfzskc_L1_2' value='' size='20' tabindex='2'>
					</td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzskc_L2'
						id='bfzskc_L2_2' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzskc_L3'
						id='bfzskc_L3_2' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzskc_L4'
						id='bfzskc_L4_2' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzskc_L5'
						id='bfzskc_L5_2' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text' name='bfzskc_L6'
						id='bfzskc_L6_2' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
				</tr>
				<tr>
					<td class="2-td2-left" nowrap>9</td>
					<td class="2-td2-left-qysds3" nowrap><input type='text'
						name='bfzskc_L1' id='bfzskc_L1_3' value='' size='20' tabindex='2'>
					</td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzskc_L2'
						id='bfzskc_L2_3' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzskc_L3'
						id='bfzskc_L3_3' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzskc_L4'
						id='bfzskc_L4_3' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzskc_L5'
						id='bfzskc_L5_3' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text' name='bfzskc_L6'
						id='bfzskc_L6_3' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
				</tr>


				<tr>
					<input type="hidden" name="hc" value="4" />
					<td class="2-td2-left" nowrap>10</td>
					<td class="2-td2-left-qysds2" nowrap>��������3%�۳��ľ����ϼ�</td>
					<td class="2-td2-left" nowrap><input type='text' name='L1'
						id='L1_4' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'
						style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L2'
						id='L2_4' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L3'
						id='L3_4' value='3%' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L4'
						id='L4_4' value='' size='13' tabindex='2'></td>
					<td class="2-td2-center" nowrap><input type='text' name='L5'
						id='L5_4' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left"><input name="button" type="button" tabindex='-1'
						value="����" onClick="InsertBfzwkcRow()"></td>
					<td class="2-td2-center"><input name="button" type="button" tabindex='-1'
						value='ɾ��' onClick="RomoveBfzwkcRow()"></td>
				</tr>
				<tr>
					<td class="2-td2-left" nowrap>11</td>
					<td class="2-td2-left-qysds3" nowrap><input type='text'
						name='bfzwkc_L1' id='bfzwkc_L1_1' value='' size='20' tabindex='2'>
					</td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzwkc_L2'
						id='bfzwkc_L2_1' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzwkc_L3'
						id='bfzwkc_L3_1' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzwkc_L4'
						id='bfzwkc_L4_1' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzwkc_L5'
						id='bfzwkc_L5_1' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text' name='bfzwkc_L6'
						id='bfzwkc_L6_1' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
				</tr>
				<tr>
					<td class="2-td2-left" nowrap>12</td>
					<td class="2-td2-left-qysds3" nowrap><input type='text'
						name='bfzwkc_L1' id='bfzwkc_L1_2' value='' size='20' tabindex='2'>
					</td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzwkc_L2'
						id='bfzwkc_L2_2' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzwkc_L3'
						id='bfzwkc_L3_2' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzwkc_L4'
						id='bfzwkc_L4_2' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzwkc_L5'
						id='bfzwkc_L5_2' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text' name='bfzwkc_L6'
						id='bfzwkc_L6_2' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
				</tr>
				<tr>
					<td class="2-td2-left" nowrap>13</td>
					<td class="2-td2-left-qysds3" nowrap><input type='text'
						name='bfzwkc_L1' id='bfzwkc_L1_3' value='' size='20' tabindex='2'>
					</td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzwkc_L2'
						id='bfzwkc_L2_3' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzwkc_L3'
						id='bfzwkc_L3_3' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzwkc_L4'
						id='bfzwkc_L4_3' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzwkc_L5'
						id='bfzwkc_L5_3' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text' name='bfzwkc_L6'
						id='bfzwkc_L6_3' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
				</tr>




				<tr>
					<input type="hidden" name="hc" value="5" />
					<td class="2-td2-left" nowrap>14</td>
					<td class="2-td2-left-qysds2" nowrap>���ģ���1.5%�۳��ľ����ϼ�</td>
					<td class="2-td2-left" nowrap><input type='text' name='L1'
						id='L1_5' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'
						style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L2'
						id='L2_5' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L3'
						id='L3_5' value='1.50%' tabindex='-1' size='6' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L4'
						id='L4_5' value='' size='13' tabindex='2'></td>
					<td class="2-td2-center" nowrap><input type='text' name='L5'
						id='L5_5' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left"><input name="button" type="button" tabindex='-1'
						value="����" onClick="InsertBfzydwkcRow()"></td>
					<td class="2-td2-center"><input name="button" type="button" tabindex='-1'
						value='ɾ��' onClick="RomoveBfzydwkcRow()"></td>
				</tr>
				<tr>
					<td class="2-td2-left" nowrap>15</td>
					<td class="2-td2-left-qysds3" nowrap><input type='text'
						name='bfzydwkc_L1' id='bfzydwkc_L1_1' value='' size='20'
						tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzydwkc_L2'
						id='bfzydwkc_L2_1' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzydwkc_L3'
						id='bfzydwkc_L3_1' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzydwkc_L4'
						id='bfzydwkc_L4_1' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzydwkc_L5'
						id='bfzydwkc_L5_1' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text'
						name='bfzydwkc_L6' id='bfzydwkc_L6_1' value='*' tabindex='-1' size='1'
						readonly='true' class='text-noborder' style='text-align:center'></td>
				</tr>
				<tr>
					<td class="2-td2-left" nowrap>16</td>
					<td class="2-td2-left-qysds3" nowrap><input type='text'
						name='bfzydwkc_L1' id='bfzydwkc_L1_2' value='' size='20'
						tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzydwkc_L2'
						id='bfzydwkc_L2_2' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzydwkc_L3'
						id='bfzydwkc_L3_2' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzydwkc_L4'
						id='bfzydwkc_L4_2' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzydwkc_L5'
						id='bfzydwkc_L5_2' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text'
						name='bfzydwkc_L6' id='bfzydwkc_L6_2' value='*' tabindex='-1' size='1'
						readonly='true' class='text-noborder' style='text-align:center'></td>
				</tr>
				<tr>
					<td class="2-td2-left" nowrap>17</td>
					<td class="2-td2-left-qysds3" nowrap><input type='text'
						name='bfzydwkc_L1' id='bfzydwkc_L1_3' value='' size='20'
						tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzydwkc_L2'
						id='bfzydwkc_L2_3' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzydwkc_L3'
						id='bfzydwkc_L3_3' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzydwkc_L4'
						id='bfzydwkc_L4_3' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='bfzydwkc_L5'
						id='bfzydwkc_L5_3' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text'
						name='bfzydwkc_L6' id='bfzydwkc_L6_3' value='*' tabindex='-1' size='1'
						readonly='true' class='text-noborder' style='text-align:center'></td>
				</tr>

				<tr>
					<input type="hidden" name="hc" value="6" />
					<td class="2-td2-left" nowrap>18</td>
					<td class="2-td2-left-qysds1" nowrap>�����ǹ���ȼ��Ծ����ϼ�</td>
					<td class="2-td2-left" nowrap><input type='text' name='L1'
						id='L1_6' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'
						style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L2'
						id='L2_6' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L3'
						id='L3_6' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'
						style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L4'
						id='L4_6' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'
						style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text' name='L5'
						id='L5_6' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'
						style='text-align:center'></td>
					<td class="2-td2-left"><input name="button" type="button" tabindex='-1'
						value="����" onClick="InsertFgyjjxRow()"></td>
					<td class="2-td2-center"><input name="button" type="button" tabindex='-1'
						value='ɾ��' onClick="RomoveFgyjjxRow()"></td>
				</tr>

				<tr>
					<td class="2-td2-left" nowrap>19</td>
					<td class="2-td2-left-qysds2" nowrap><input type='text'
						name='fgyjjx_L1' id='fgyjjx_L1_1' value='' size='20' tabindex='2'>
					</td>
					<td class="2-td2-left" nowrap><input type='text' name='fgyjjx_L2'
						id='fgyjjx_L2_1' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='fgyjjx_L3'
						id='fgyjjx_L3_1' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='fgyjjx_L4'
						id='fgyjjx_L4_1' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='fgyjjx_L5'
						id='fgyjjx_L5_1' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text' name='fgyjjx_L6'
						id='fgyjjx_L6_1' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
				</tr>
				<tr>
					<td class="2-td2-left" nowrap>20</td>
					<td class="2-td2-left-qysds2" nowrap><input type='text'
						name='fgyjjx_L1' id='fgyjjx_L1_2' value='' size='20' tabindex='2'>
					</td>
					<td class="2-td2-left" nowrap><input type='text' name='fgyjjx_L2'
						id='fgyjjx_L2_2' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='fgyjjx_L3'
						id='fgyjjx_L3_2' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='fgyjjx_L4'
						id='fgyjjx_L4_2' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='fgyjjx_L5'
						id='fgyjjx_L5_2' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text' name='fgyjjx_L6'
						id='fgyjjx_L6_2' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
				</tr>
				<tr>
					<td class="2-td2-left" nowrap>21</td>
					<td class="2-td2-left-qysds2" nowrap><input type='text'
						name='fgyjjx_L1' id='fgyjjx_L1_3' value='' size='20' tabindex='2'>
					</td>
					<td class="2-td2-left" nowrap><input type='text' name='fgyjjx_L2'
						id='fgyjjx_L2_3' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='fgyjjx_L3'
						id='fgyjjx_L3_3' value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='fgyjjx_L4'
						id='fgyjjx_L4_3' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='fgyjjx_L5'
						id='fgyjjx_L5_3' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
					<td class="2-td2-center" nowrap><input type='text' name='fgyjjx_L6'
						id='fgyjjx_L6_3' value='*' tabindex='-1' size='1' readonly='true'
						class='text-noborder' style='text-align:center'></td>
				</tr>


				<tr>
					<input type="hidden" name="hc" value="7" />
					<td class="2-td2-left" nowrap>22</td>
					<td class="2-td2-left-qysds1" nowrap>�����ϼ�</td>
					<td class="2-td2-left" nowrap><input type='text' name='L1'
						id='L1_7' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'
						style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L2' id=L2_7
						value='' size='13' tabindex='2'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L3'
						id='L3_7' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'
						style='text-align:center'></td>
					<td class="2-td2-left" nowrap><input type='text' name='L4'
						id='L4_7' value='' size='13' tabindex='-1' readonly='true'></td>
					<td class="2-td2-center" nowrap><input type='text' name='L5'
						id='L5_7' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'
						style='text-align:center'></td>
				</tr>
			</table>
			</div>
			</TD>
		</TR>
		<TR class="black9">
			<TD>
			<div align="center"><a style="cursor:hand" id="previous"
				onclick="gotoPreviousTable()"><img name="spage" value="��һ�ű�"
				alt="��д��һ�ű�"
				onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				src="<%=static_contextpath%>images/shangyiye1.jpg" id="previoustable">
			</a>&nbsp;&nbsp;
			
			 <a style="cursor:hand" id="next"
				onclick="gotoNextTable()"><img name="xpage" value="��һ�ű�"
				alt="��д��һ�ű�"
				onMouseOver="MM_swapImage('nexttable','','<%=static_contextpath%>images/xaiyiye2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				src="<%=static_contextpath%>images/xaiyiye1.jpg" id="nexttable"> </a>&nbsp;&nbsp;
			<input type="image" style="cursor:hand" tabIndex="-1"
				onClick="doReset();return false;" accesskey="u" value="���"
				alt="���ҳ���������Ϣ"
				onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				src="<%=static_contextpath%>images/qc-u1.jpg" id="qingchu" />
			&nbsp;&nbsp; <input type="image" style="cursor:hand" tabIndex="-1"
				onClick="doSave();return false;" accesskey="s" value="����" alt="����"
				onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				src="<%=static_contextpath%>images/bc-s1.jpg" id="baocun" />
			&nbsp;&nbsp; <input type="image" style="cursor:hand" tabIndex="-1"
				onClick="doCheck();return false;" accesskey="d" value="����У��"
				alt="���ڹ�ϵУ��"
				onMouseOver="MM_swapImage('jiaoyan','','../../images/b-bdjyd2.jpg',1)"
				onMouseOut="MM_swapImgRestore()" src="../../images/b-bdjyd1.jpg"
				id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
				style="cursor:hand" tabIndex="-1" onClick="doDelete();return false;"
				accesskey="x" value="ȫ��ɾ��" alt="ɾ�������������ݣ��Ҳ��ɻָ�"
				onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				src="<%=static_contextpath%>images/qbsc-x1.jpg" id="shanchu" />
			&nbsp;&nbsp; <input type="image" value="����" alt="���ص���ҵ����˰�걨��ҳ��"
				style="cursor:hand" tabIndex="-1" onClick="doExit();return false;"
				onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
				onMouseOut="MM_swapImgRestore()"
				src="<%=static_contextpath%>images/fanhui1.jpg" id="fanhui" /></div>
			</TD>
		</TR>
	</table>
	<%@ include file="../include/footernew.jsp"%>
</html:form>
</body>
</html>


