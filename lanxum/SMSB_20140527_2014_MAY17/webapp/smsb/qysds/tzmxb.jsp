
<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.tzmxb.web.TzmxbForm"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.syax.creports.Constants"%>

<html>
<head>
<title>Ͷ������(��ʧ)��ϸ��</title>
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
<script language="JavaScript" type="text/JavaScript"
	src="../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/function.js"></script>
</head>
<script language=JavaScript>
	/*��ʼ��*/
	function doInit()
	{	       
	        //����װ��ҳ��ʱ���������������Ϊ0
			document.forms[0].dqzqtzMax.value='3';
			document.forms[0].cqzqtzMax.value='3';
			document.forms[0].dqgqtzMax.value='2';
			document.forms[0].cqgqtzMax.value='2';
			
			//��ʼ���ı���onChange�¼�����
			    initNumText("tzsy",7);
			    initNumText("ynqysds",7);
			    initNumText("tzzrjsr",7);
			    initNumText("cstzcb",7);
			    initNumText("jscbtz",7);
			    initNumText("tzzrcb",7);
			    initNumText("tzzrsd",7);
			    
			    initNumText("dqzqtz_tzsy",parseInt(document.forms[0].dqzqtzMax.value));
			    initNumText("dqzqtz_tzzrjsr",parseInt(document.forms[0].dqzqtzMax.value));
			    initNumText("dqzqtz_cstzcb",parseInt(document.forms[0].dqzqtzMax.value));
			    initNumText("dqzqtz_tzzrcb",parseInt(document.forms[0].dqzqtzMax.value));
			    initNumText("dqzqtz_tzzrsd",parseInt(document.forms[0].dqzqtzMax.value));
			    
			    initNumText("cqzqtz_tzsy",parseInt(document.forms[0].cqzqtzMax.value));
			    initNumText("cqzqtz_tzzrjsr",parseInt(document.forms[0].cqzqtzMax.value));
			    initNumText("cqzqtz_cstzcb",parseInt(document.forms[0].cqzqtzMax.value));
			    initNumText("cqzqtz_jscbtz",parseInt(document.forms[0].cqzqtzMax.value));
			    initNumText("cqzqtz_tzzrcb",parseInt(document.forms[0].cqzqtzMax.value));
			    initNumText("cqzqtz_tzzrsd",parseInt(document.forms[0].cqzqtzMax.value));
			    
			    initNumText("dqgqtz_qybl",parseInt(document.forms[0].dqgqtzMax.value));
			    initNumText("dqgqtz_sl",parseInt(document.forms[0].dqgqtzMax.value));
			    initNumText("dqgqtz_tzsy",parseInt(document.forms[0].dqgqtzMax.value));
			    initNumText("dqgqtz_ynqysds",parseInt(document.forms[0].dqgqtzMax.value));
			    initNumText("dqgqtz_tzzrjsr",parseInt(document.forms[0].dqgqtzMax.value));
			    initNumText("dqgqtz_cstzcb",parseInt(document.forms[0].dqgqtzMax.value));
			    initNumText("dqgqtz_jscbtz",parseInt(document.forms[0].dqgqtzMax.value));
			    initNumText("dqgqtz_tzzrcb",parseInt(document.forms[0].dqgqtzMax.value));
			    initNumText("dqgqtz_tzzrsd",parseInt(document.forms[0].dqgqtzMax.value));
			    
			    initNumText("cqgqtz_qybl",parseInt(document.forms[0].cqgqtzMax.value));
			    initNumText("cqgqtz_sl",parseInt(document.forms[0].cqgqtzMax.value));
			    initNumText("cqgqtz_tzsy",parseInt(document.forms[0].cqgqtzMax.value));
			    initNumText("cqgqtz_ynqysds",parseInt(document.forms[0].cqgqtzMax.value));
			    initNumText("cqgqtz_tzzrjsr",parseInt(document.forms[0].cqgqtzMax.value));
			    initNumText("cqgqtz_cstzcb",parseInt(document.forms[0].cqgqtzMax.value));
			    initNumText("cqgqtz_jscbtz",parseInt(document.forms[0].cqgqtzMax.value));
			    initNumText("cqgqtz_tzzrcb",parseInt(document.forms[0].cqgqtzMax.value));
			    initNumText("cqgqtz_tzzrsd",parseInt(document.forms[0].cqgqtzMax.value));
			    
			   

	        var list=document.getElementById("tzmxb_list");
			for(var i=parseInt(document.forms[0].dqzqtzMax.value);i>3;i--)
			{
				list.deleteRow( parseInt(5+parseInt(i-1)) );
			}
			for(var i=parseInt(document.forms[0].cqzqtzMax.value);i>3;i--)
			{
				list.deleteRow( parseInt(6+parseInt(i-1)) );
			}
			for(var i=parseInt(document.forms[0].dqgqtzMax.value);i>2;i--)
			{
				list.deleteRow( parseInt(8+parseInt(i-1)) );
			}
			for(var i=parseInt(document.forms[0].cqgqtzMax.value);i>2;i--)
			{
				list.deleteRow( parseInt(9+parseInt(i-1)) );
			}
			
		         
			<%
			TzmxbForm tzmxbForm=(TzmxbForm)request.getAttribute("tzmxbForm");
			/*��ʾ������������*/
			if(tzmxbForm.getGqtzzrss()!=null && !tzmxbForm.getGqtzzrss().equals(""))
			{
			   String value78 = tzmxbForm.getGqtzzrss();
			
			%>
			obj = eval("document.getElementById('1')");
			obj.value = '<%=value78%>';
			<%
			}
			%>
			
			<%
			if(tzmxbForm.getGqsszrsqkcqe()!=null && !tzmxbForm.getGqsszrsqkcqe().equals(""))
			{
			String value79 = tzmxbForm.getGqsszrsqkcqe();
			
			%>
			obj = eval("document.getElementById('2')");
			obj.value = '<%=value79%>';
			<%
			}
			%>
					
			<%
			if(tzmxbForm.getNstze()!=null &&!tzmxbForm.getNstze().equals(""))
			{
			String value80 = tzmxbForm.getNstze();
			
			%>
			obj = eval("document.getElementById('3')");
			obj.value = '<%=value80%>';
			<%
			}
			%>
					
			<%
			if(tzmxbForm.getJzhkce()!=null &&!tzmxbForm.getJzhkce().equals(""))
			{
			String value81 = tzmxbForm.getJzhkce();
			
			%>
			obj = eval("document.getElementById('4')");
			obj.value = '<%=value81%>';	
			<%
			}
					
			
			
			if (tzmxbForm!=null && tzmxbForm.getDataList().size()>0)
			{
				/*��ʾ�̶���������*/
				for(int i=0;i<tzmxbForm.getDataList().size();i++)
				{
					HashMap map=(HashMap)tzmxbForm.getDataList().get(i);
					int hc=Integer.parseInt((String)map.get("hc"));
					String btzqyszd=(String)map.get("btzqyszd");
					String qybl=(String)map.get("qybl");
					String sl=(String)map.get("sl");
					String tzsy=(String)map.get("tzsy");
					String ynqysds=(String)map.get("ynqysds");
					String tzzrjsr=(String)map.get("tzzrjsr");
					String cstzcb=(String)map.get("cstzcb");
					String jscbtz=(String)map.get("jscbtz");
					String tzzrcb=(String)map.get("tzzrcb");
					String tzzrsd=(String)map.get("tzzrsd");
									
					%>
					obj = eval("document.getElementById('btzqyszd_<%=hc%>')");
					obj.value = '<%=btzqyszd%>';
					
					obj = eval("document.getElementById('qybl_<%=hc%>')");
					obj.value = '<%=qybl%>';
					
					obj = eval("document.getElementById('sl_<%=hc%>')");
					obj.value = '<%=sl%>';
					
					obj = eval("document.getElementById('tzsy_<%=hc%>')");
					obj.value = '<%=tzsy%>';
					
					obj = eval("document.getElementById('ynqysds_<%=hc%>')");
					obj.value = '<%=ynqysds%>';
					
					obj = eval("document.getElementById('tzzrjsr_<%=hc%>')");
					obj.value = '<%=tzzrjsr%>';
					
					obj = eval("document.getElementById('cstzcb_<%=hc%>')");
					obj.value = '<%=cstzcb%>';
					
					obj = eval("document.getElementById('jscbtz_<%=hc%>')");
					obj.value = '<%=jscbtz%>';
					
					obj = eval("document.getElementById('tzzrcb_<%=hc%>')");
					obj.value = '<%=tzzrcb%>';
					
					obj = eval("document.getElementById('tzzrsd_<%=hc%>')");
					obj.value = '<%=tzzrsd%>';
					
					
					<% 
				}
			}
			if(tzmxbForm!=null && tzmxbForm.getDqzqtzList().size()>3)
			{
			    //��̬������
			int dynamicRows=tzmxbForm.getDqzqtzList().size()-3;
			for(int i=0;i<dynamicRows;i++)
			{
			%>
			  DynamicAddDqzqtz();
			<%
			}
			}
			//��ֵ
		if(tzmxbForm!=null )
		{
			for(int i=0;i<tzmxbForm.getDqzqtzList().size();i++)
			{
				HashMap map=(HashMap)tzmxbForm.getDqzqtzList().get(i);
				String strL1=(String)map.get("dqzqtz_tzzczl");
				String strL2=(String)map.get("dqzqtz_btzqyszd");
				String strL3=(String)map.get("dqzqtz_qybl");
				String strL4=(String)map.get("dqzqtz_sl");
				String strL5=(String)map.get("dqzqtz_tzsy");
				String strL6=(String)map.get("dqzqtz_ynqysds");
				String strL7=(String)map.get("dqzqtz_tzzrjsr");
				String strL8=(String)map.get("dqzqtz_cstzcb");
				String strL9=(String)map.get("dqzqtz_jscbtz");
				String strL10=(String)map.get("dqzqtz_tzzrcb");
				String strL11=(String)map.get("dqzqtz_tzzrsd");
				%>
				setDqzqtzValue('<%=i%>','<%=strL1%>','<%=strL2%>','<%=strL3%>','<%=strL4%>','<%=strL5%>',
				'<%=strL6%>','<%=strL7%>','<%=strL8%>','<%=strL9%>','<%=strL10%>','<%=strL11%>');
				
				<%
			}
		}
			if(tzmxbForm!=null && tzmxbForm.getCqzqtzList().size()>3)
			{
			   //��̬������
			int dynamicRows=tzmxbForm.getCqzqtzList().size()-3;
			for(int i=0;i<dynamicRows;i++)
			{
			%>
			  DynamicAddCqzqtz();
			<%
			}
			}
			//��ֵ
		if(tzmxbForm!=null )
		{
			for(int i=0;i<tzmxbForm.getCqzqtzList().size();i++)
			{
				HashMap map=(HashMap)tzmxbForm.getCqzqtzList().get(i);
				String strL1=(String)map.get("cqzqtz_tzzczl");
				String strL2=(String)map.get("cqzqtz_btzqyszd");
				String strL3=(String)map.get("cqzqtz_qybl");
				String strL4=(String)map.get("cqzqtz_sl");
				String strL5=(String)map.get("cqzqtz_tzsy");
				String strL6=(String)map.get("cqzqtz_ynqysds");
				String strL7=(String)map.get("cqzqtz_tzzrjsr");
				String strL8=(String)map.get("cqzqtz_cstzcb");
				String strL9=(String)map.get("cqzqtz_jscbtz");
				String strL10=(String)map.get("cqzqtz_tzzrcb");
				String strL11=(String)map.get("cqzqtz_tzzrsd");
				%>
				setCqzqtzValue('<%=i%>','<%=strL1%>','<%=strL2%>','<%=strL3%>','<%=strL4%>','<%=strL5%>',
				'<%=strL6%>','<%=strL7%>','<%=strL8%>','<%=strL9%>','<%=strL10%>','<%=strL11%>');
				
				<%
			}
		}
			if(tzmxbForm!=null && tzmxbForm.getDqgqtzList().size()>2)
			{
			    //��̬������
			int dynamicRows=tzmxbForm.getDqgqtzList().size()-2;
			for(int i=0;i<dynamicRows;i++)
			{
			%>
			  DynamicAddDqgqtz();
			<%
			}
			}
			//��ֵ
		if(tzmxbForm!=null )
		{
			for(int i=0;i<tzmxbForm.getDqgqtzList().size();i++)
			{
				HashMap map=(HashMap)tzmxbForm.getDqgqtzList().get(i);
				String strL1=(String)map.get("dqgqtz_tzzczl");
				String strL2=(String)map.get("dqgqtz_btzqyszd");
				String strL3=(String)map.get("dqgqtz_qybl");
				String strL4=(String)map.get("dqgqtz_sl");
				String strL5=(String)map.get("dqgqtz_tzsy");
				String strL6=(String)map.get("dqgqtz_ynqysds");
				String strL7=(String)map.get("dqgqtz_tzzrjsr");
				String strL8=(String)map.get("dqgqtz_cstzcb");
				String strL9=(String)map.get("dqgqtz_jscbtz");
				String strL10=(String)map.get("dqgqtz_tzzrcb");
				String strL11=(String)map.get("dqgqtz_tzzrsd");
				%>
				setDqgqtzValue('<%=i%>','<%=strL1%>','<%=strL2%>','<%=strL3%>','<%=strL4%>','<%=strL5%>',
				'<%=strL6%>','<%=strL7%>','<%=strL8%>','<%=strL9%>','<%=strL10%>','<%=strL11%>');
				
				<%
			}
		}
			
			if(tzmxbForm!=null && tzmxbForm.getCqgqtzList().size()>2)
			{
			   //��̬������
			int dynamicRows=tzmxbForm.getCqgqtzList().size()-2;
			for(int i=0;i<dynamicRows;i++)
			{
			%>
			  DynamicAddCqgqtz();
			<%
			}
			}
			//��ֵ
		if(tzmxbForm!=null )
		{
			for(int i=0;i<tzmxbForm.getCqgqtzList().size();i++)
			{
				HashMap map=(HashMap)tzmxbForm.getCqgqtzList().get(i);
				String strL1=(String)map.get("cqgqtz_tzzczl");
				String strL2=(String)map.get("cqgqtz_btzqyszd");
				String strL3=(String)map.get("cqgqtz_qybl");
				String strL4=(String)map.get("cqgqtz_sl");
				String strL5=(String)map.get("cqgqtz_tzsy");
				String strL6=(String)map.get("cqgqtz_ynqysds");
				String strL7=(String)map.get("cqgqtz_tzzrjsr");
				String strL8=(String)map.get("cqgqtz_cstzcb");
				String strL9=(String)map.get("cqgqtz_jscbtz");
				String strL10=(String)map.get("cqgqtz_tzzrcb");
				String strL11=(String)map.get("cqgqtz_tzzrsd");
				%>
				setCqgqtzValue('<%=i%>','<%=strL1%>','<%=strL2%>','<%=strL3%>','<%=strL4%>','<%=strL5%>',
				'<%=strL6%>','<%=strL7%>','<%=strL8%>','<%=strL9%>','<%=strL10%>','<%=strL11%>');
				
				<%
			}
		}
			%>
		}
		
			//��ActionForm��ȡList���ݲ��뵽table	
		function DynamicAddDqzqtz()
		{
		   
			var list=document.getElementById("tzmxb_list");
	
			//��ǰ������ծȨͶ�ʡ����������
			var hc=document.forms[0].dqzqtzMax.value;
	
			var newRow=list.insertRow(6+parseInt(hc));
			
			//�д�
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="&nbsp";
	
			//Ͷ���ʲ�����
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='dqzqtz_tzzczl'  value='' size='8' tabindex='2'>";
			
			//��Ͷ����ҵ���ڵ�
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='dqzqtz_btzqyszd'  value='' size='1' readonly='true' tabindex='-1' class='text-noborder'>";
			
			//ռ��Ͷ����ҵȨ�����
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='dqzqtz_qybl'  value='' size='1' readonly='true' tabindex='-1' class='text-noborder'>";
			
			//��Ͷ����ҵ������ҵ����˰˰��
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='dqzqtz_sl'  value='' size='1' readonly='true' tabindex='-1' class='text-noborder'>";
			
			//Ͷ�����棨�������棩
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='dqzqtz_tzsy'  value='' size='8' tabindex='2'  style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
			
			//Ӧ��˰��Ͷ������������ҵ����˰
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='dqzqtz_ynqysds'  value='' size='1' readonly='true' tabindex='-1' class='text-noborder'>";
			
			//Ͷ��ת�þ�����
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='dqzqtz_tzzrjsr'  value='' size='8' tabindex='2'  style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
			
			//��ʼͶ�ʳɱ�
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='dqzqtz_cstzcb'  value='' size='8' tabindex='2'  style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
			
			//��˰�ɱ�����
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='dqzqtz_jscbtz'  value='' size='1' readonly='true' tabindex='-1' class='text-noborder'>";
			
			//Ͷ��ת�óɱ�
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='dqzqtz_tzzrcb'  value='' size='8' tabindex='2'  style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
			
			//Ͷ��ת�����û���ʧ
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='dqzqtz_tzzrsd'  value='' size='8' tabindex='2'  style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
			
			//��ѡ��
			var newCell=newRow.insertCell();
			newCell.className='2-td2-center';
			newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='dqzqtz_chk' value=''>";
			
			//��ǰ������ծȨͶ�ʡ������������1
			document.forms[0].dqzqtzMax.value=parseInt(document.forms[0].dqzqtzMax.value)+1;
			
		}
	
	   //��ActionForm��ȡList���ݲ��뵽table	
		function DynamicAddCqzqtz()
		{
		   var list=document.getElementById("tzmxb_list");
	        var dqzqtzhc = document.forms[0].dqzqtzMax.value;
			//��ǰ������ծȨͶ�ʡ����������
			var hc=document.forms[0].cqzqtzMax.value;
	
			var newRow=list.insertRow(7+parseInt(dqzqtzhc)+parseInt(hc));
			
			//�д�
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="&nbsp";
		
		    //Ͷ���ʲ�����
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='cqzqtz_tzzczl'  value='' size='8' tabindex='2'>";
			
			//��Ͷ����ҵ���ڵ�
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='cqzqtz_btzqyszd'  value='' size='1' readonly='true' tabindex='-1' class='text-noborder'>";
			
			//ռ��Ͷ����ҵȨ�����
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='cqzqtz_qybl'  value='' size='1' readonly='true' tabindex='-1' class='text-noborder'>";
			
			//��Ͷ����ҵ������ҵ����˰˰��
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='cqzqtz_sl'  value='' size='1' readonly='true' tabindex='-1' class='text-noborder'>";
			
			//Ͷ�����棨�������棩
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='cqzqtz_tzsy'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
			
			//Ӧ��˰��Ͷ������������ҵ����˰
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='cqzqtz_ynqysds'  value='' size='1' readonly='true' tabindex='-1' class='text-noborder'>";
			
			//Ͷ��ת�þ�����
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='cqzqtz_tzzrjsr'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
			
			//��ʼͶ�ʳɱ�
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='cqzqtz_cstzcb'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
			
			//��˰�ɱ�����
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='cqzqtz_jscbtz'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
			
			//Ͷ��ת�óɱ�
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='cqzqtz_tzzrcb'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
			
			//Ͷ��ת�����û���ʧ
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='cqzqtz_tzzrsd'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
			
			//��ѡ��
			var newCell=newRow.insertCell();
			newCell.className='2-td2-center';
			newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='cqzqtz_chk' value=''>";
		
		    //��ǰ������ծȨͶ�ʡ������������1
			document.forms[0].cqzqtzMax.value=parseInt(document.forms[0].cqzqtzMax.value)+1;
		}
	
	
	   //��ActionForm��ȡList���ݲ��뵽table	
		function DynamicAddDqgqtz()
	   {
	      var list=document.getElementById("tzmxb_list");
	      var dqzqtzhc=document.forms[0].dqzqtzMax.value;
			var cqzqtzhc=document.forms[0].cqzqtzMax.value;
			
			
			//��ǰ�����ڹ�ȨͶ�ʡ����������
			var hc=document.forms[0].dqgqtzMax.value;
	
			var newRow=list.insertRow(9+parseInt(hc)+parseInt(dqzqtzhc)+parseInt(cqzqtzhc));
			
			//�д�
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="&nbsp";
	      
	        //Ͷ���ʲ�����
			var newCell=newRow.insertCell();
			newCell.className='2-td2-left';
			newCell.innerHTML="<input type='text' name='dqgqtz_tzzczl'  value='' size='8' tabindex='2'>";
			
			//��Ͷ����ҵ���ڵ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_btzqyszd'  value='' size='8' tabindex='2'>";
		
		//ռ��Ͷ����ҵȨ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_qybl'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��Ͷ����ҵ������ҵ����˰˰��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_sl'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ�����棨�������棩
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_tzsy'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ӧ��˰��Ͷ������������ҵ����˰
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_ynqysds'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ��ת�þ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_tzzrjsr'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��ʼͶ�ʳɱ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_cstzcb'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��˰�ɱ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_jscbtz'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ��ת�óɱ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_tzzrcb'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ��ת�����û���ʧ
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_tzzrsd'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='dqgqtz_chk' value=''>";
		
		//��ǰ�����ڹ�ȨͶ�ʡ������������1
		document.forms[0].dqgqtzMax.value=parseInt(document.forms[0].dqgqtzMax.value)+1;
   
   }
   
    //��ActionForm��ȡList���ݲ��뵽table	
	function DynamicAddCqgqtz()
	{
	   var list=document.getElementById("tzmxb_list");
       var dqzqtzhc=document.forms[0].dqzqtzMax.value;
       var cqzqtzhc=document.forms[0].cqzqtzMax.value;
       var dqgqtzhc=document.forms[0].dqgqtzMax.value;
		//��ǰ�����ڹ�ȨͶ�ʡ����������
		var hc=document.forms[0].cqgqtzMax.value;

		var newRow=list.insertRow(10+parseInt(hc)+parseInt(dqzqtzhc)+parseInt(cqzqtzhc)+parseInt(dqgqtzhc));
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp";
		
		//Ͷ���ʲ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_tzzczl'  value='' size='8' tabindex='2'>";
		
		//��Ͷ����ҵ���ڵ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_btzqyszd'  value='' size='8' tabindex='2'>";
		
		//ռ��Ͷ����ҵȨ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_qybl'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��Ͷ����ҵ������ҵ����˰˰��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_sl'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ�����棨�������棩
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_tzsy'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ӧ��˰��Ͷ������������ҵ����˰
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_ynqysds'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ��ת�þ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_tzzrjsr'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��ʼͶ�ʳɱ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_cstzcb'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��˰�ɱ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_jscbtz'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ��ת�óɱ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_tzzrcb'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ��ת�����û���ʧ
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_tzzrsd'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='cqgqtz_chk' value=''>";
		
		//��ǰ�����ڹ�ȨͶ�ʡ������������1
		document.forms[0].cqgqtzMax.value=parseInt(document.forms[0].cqgqtzMax.value)+1;	
	}
	//������ծȨͶ�ʸ�ֵ
function setDqzqtzValue(i,L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11)
{
   var qekc_L1=document.all("dqzqtz_tzzczl");
   qekc_L1[parseInt(i)].value=L1
   
   var qekc_L2=document.all("dqzqtz_btzqyszd");
   qekc_L2[parseInt(i)].value=L2
   
   var qekc_L3=document.all("dqzqtz_qybl");
   qekc_L3[parseInt(i)].value=L3
   
   var qekc_L4=document.all("dqzqtz_sl");
   qekc_L4[parseInt(i)].value=L4
   
   var qekc_L5=document.all("dqzqtz_tzsy");
   qekc_L5[parseInt(i)].value=L5
   
   var qekc_L6=document.all("dqzqtz_ynqysds");
   qekc_L6[parseInt(i)].value=L6
   
   var qekc_L7=document.all("dqzqtz_tzzrjsr");
   qekc_L7[parseInt(i)].value=L7
   
   var qekc_L8=document.all("dqzqtz_cstzcb");
   qekc_L8[parseInt(i)].value=L8
   
   var qekc_L9=document.all("dqzqtz_jscbtz");
   qekc_L9[parseInt(i)].value=L9
   
   var qekc_L10=document.all("dqzqtz_tzzrcb");
   qekc_L10[parseInt(i)].value=L10
   
   var qekc_L11=document.all("dqzqtz_tzzrsd");
   qekc_L11[parseInt(i)].value=L11
}
//������ծȨͶ�ʸ�ֵ
function setCqzqtzValue(i,L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11)
{
   var qekc_L1=document.all("cqzqtz_tzzczl");
   qekc_L1[parseInt(i)].value=L1
   
   var qekc_L2=document.all("cqzqtz_btzqyszd");
   qekc_L2[parseInt(i)].value=L2
   
   var qekc_L3=document.all("cqzqtz_qybl");
   qekc_L3[parseInt(i)].value=L3
   
   var qekc_L4=document.all("cqzqtz_sl");
   qekc_L4[parseInt(i)].value=L4
   
   var qekc_L5=document.all("cqzqtz_tzsy");
   qekc_L5[parseInt(i)].value=L5
   
   var qekc_L6=document.all("cqzqtz_ynqysds");
   qekc_L6[parseInt(i)].value=L6
   
   var qekc_L7=document.all("cqzqtz_tzzrjsr");
   qekc_L7[parseInt(i)].value=L7
   
   var qekc_L8=document.all("cqzqtz_cstzcb");
   qekc_L8[parseInt(i)].value=L8
   
   var qekc_L9=document.all("cqzqtz_jscbtz");
   qekc_L9[parseInt(i)].value=L9
   
   var qekc_L10=document.all("cqzqtz_tzzrcb");
   qekc_L10[parseInt(i)].value=L10
   
   var qekc_L11=document.all("cqzqtz_tzzrsd");
   qekc_L11[parseInt(i)].value=L11
}
//�����ڹ�ȨͶ�ʸ�ֵ
function setDqgqtzValue(i,L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11)
{
   var qekc_L1=document.all("dqgqtz_tzzczl");
   qekc_L1[parseInt(i)].value=L1
   
   var qekc_L2=document.all("dqgqtz_btzqyszd");
   qekc_L2[parseInt(i)].value=L2
   
   var qekc_L3=document.all("dqgqtz_qybl");
   qekc_L3[parseInt(i)].value=L3
   
   var qekc_L4=document.all("dqgqtz_sl");
   qekc_L4[parseInt(i)].value=L4
   
   var qekc_L5=document.all("dqgqtz_tzsy");
   qekc_L5[parseInt(i)].value=L5
   
   var qekc_L6=document.all("dqgqtz_ynqysds");
   qekc_L6[parseInt(i)].value=L6
   
   var qekc_L7=document.all("dqgqtz_tzzrjsr");
   qekc_L7[parseInt(i)].value=L7
   
   var qekc_L8=document.all("dqgqtz_cstzcb");
   qekc_L8[parseInt(i)].value=L8
   
   var qekc_L9=document.all("dqgqtz_jscbtz");
   qekc_L9[parseInt(i)].value=L9
   
   var qekc_L10=document.all("dqgqtz_tzzrcb");
   qekc_L10[parseInt(i)].value=L10
   
   var qekc_L11=document.all("dqgqtz_tzzrsd");
   qekc_L11[parseInt(i)].value=L11
}
//�����ڹ�ȨͶ�ʸ�ֵ
function setCqgqtzValue(i,L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11)
{
   var qekc_L1=document.all("cqgqtz_tzzczl");
   qekc_L1[parseInt(i)].value=L1
   
   var qekc_L2=document.all("cqgqtz_btzqyszd");
   qekc_L2[parseInt(i)].value=L2
   
   var qekc_L3=document.all("cqgqtz_qybl");
   qekc_L3[parseInt(i)].value=L3
   
   var qekc_L4=document.all("cqgqtz_sl");
   qekc_L4[parseInt(i)].value=L4
   
   var qekc_L5=document.all("cqgqtz_tzsy");
   qekc_L5[parseInt(i)].value=L5
   
   var qekc_L6=document.all("cqgqtz_ynqysds");
   qekc_L6[parseInt(i)].value=L6
   
   var qekc_L7=document.all("cqgqtz_tzzrjsr");
   qekc_L7[parseInt(i)].value=L7
   
   var qekc_L8=document.all("cqgqtz_cstzcb");
   qekc_L8[parseInt(i)].value=L8
   
   var qekc_L9=document.all("cqgqtz_jscbtz");
   qekc_L9[parseInt(i)].value=L9
   
   var qekc_L10=document.all("cqgqtz_tzzrcb");
   qekc_L10[parseInt(i)].value=L10
   
   var qekc_L11=document.all("cqgqtz_tzzrsd");
   qekc_L11[parseInt(i)].value=L11
}
	function doSave()
	{
		var list="<%=Constants.QYSDS_CONTROL_CHAR_FOR_JS%>";
		if (!Char_Vaildate(document.forms[0],list)){
			return false;	
		}
		doSubmitForm('Save');
	}
	
	
	function doReset()
	{
	    if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
	    {
		   	for(var i=1; i<=7; i++)
		   	{
				obj = eval("document.forms[0].btzqyszd_" + i);
				if(obj.readOnly!=true )
				obj.value = "";
				
				obj = eval("document.forms[0].qybl_" + i);
				if(obj.readOnly!=true )
				obj.value = "";
				
				obj = eval("document.forms[0].sl_" + i);
				if(obj.readOnly!=true )
				obj.value = "";
				
				obj = eval("document.forms[0].tzsy_" + i);
				if(obj.readOnly!=true )
				obj.value = "";
				
				obj = eval("document.forms[0].ynqysds_" + i);
				if(obj.readOnly!=true )
				obj.value = "";
				
				obj = eval("document.forms[0].tzzrjsr_" + i);
				if(obj.readOnly!=true)
				obj.value = "";
				
				obj = eval("document.forms[0].cstzcb_" + i);
				if(obj.readOnly!=true )
				obj.value = "";
				
				obj = eval("document.forms[0].jscbtz_" + i);
				if(obj.readOnly!=true )
				obj.value = "";
				
				obj = eval("document.forms[0].tzzrcb_" + i);
				if(obj.readOnly!=true )
				obj.value = "";
				
				obj = eval("document.forms[0].tzzrsd_" + i);
				if(obj.readOnly!=true )
				obj.value = "";
				
				
		   	}
		   	clean('dqzqtz_tzzczl','dqzqtz_btzqyszd','dqzqtz_qybl','dqzqtz_sl','dqzqtz_tzsy','dqzqtz_ynqysds',
		   	'dqzqtz_tzzrjsr','dqzqtz_cstzcb','dqzqtz_jscbtz','dqzqtz_tzzrcb','dqzqtz_tzzrsd',document.forms[0].dqzqtzMax.value);
		    
		    clean('cqzqtz_tzzczl','cqzqtz_btzqyszd','cqzqtz_qybl','cqzqtz_sl','cqzqtz_tzsy','cqzqtz_ynqysds',
		   	'cqzqtz_tzzrjsr','cqzqtz_cstzcb','cqzqtz_jscbtz','cqzqtz_tzzrcb','cqzqtz_tzzrsd',document.forms[0].cqzqtzMax.value);
		    
		    clean('dqgqtz_tzzczl','dqgqtz_btzqyszd','dqgqtz_qybl','dqgqtz_sl','dqgqtz_tzsy','dqgqtz_ynqysds',
		   	'dqgqtz_tzzrjsr','dqgqtz_cstzcb','dqgqtz_jscbtz','dqgqtz_tzzrcb','dqgqtz_tzzrsd',document.forms[0].dqgqtzMax.value);
		   	
		     clean('cqgqtz_tzzczl','cqgqtz_btzqyszd','cqgqtz_qybl','cqgqtz_sl','cqgqtz_tzsy','cqgqtz_ynqysds',
		   	'cqgqtz_tzzrjsr','cqgqtz_cstzcb','cqgqtz_jscbtz','cqgqtz_tzzrcb','cqgqtz_tzzrsd',document.forms[0].cqgqtzMax.value);
	    
	         document.forms[0].gqtzzrss.value='';
	         document.forms[0].gqsszrsqkcqe.value='';
	         document.forms[0].nstze.value='';
	         document.forms[0].jzhkce.value='';
	    }
	}
	
	<%/*���������̬���е�����*/%>
	function clean(L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,maxRow){
		var arr1=document.all(L1);
		var arr2=document.all(L2);
		var arr3=document.all(L3);	
		var arr4=document.all(L4);	
		var arr5=document.all(L5);	
		var arr6=document.all(L6);
		var arr7=document.all(L7);	
		var arr8=document.all(L8);	
		var arr9=document.all(L9);	
		var arr10=document.all(L10);	
		var arr11=document.all(L11);
		for (var i=0;i<parseInt(maxRow);i++){					
			if( arr1[parseInt(i)].readOnly!=true)
				arr1[parseInt(i)].value=""	;		
		    if( arr2[parseInt(i)].readOnly!=true)
				arr2[parseInt(i)].value=""	;			
			if(arr3[parseInt(i)].readOnly!=true )
				arr3[parseInt(i)].value=""	;			
			if(arr4[parseInt(i)].readOnly!=true )
				arr4[parseInt(i)].value=""	;			
			if(arr5[parseInt(i)].readOnly!=true)
				arr5[parseInt(i)].value="";				
			if(arr6[parseInt(i)].readOnly!=true )
				arr6[parseInt(i)].value="";
			if(arr7[parseInt(i)].readOnly!=true )
				arr7[parseInt(i)].value=""	;			
			if(arr8[parseInt(i)].readOnly!=true )
				arr8[parseInt(i)].value=""	;			
			if(arr9[parseInt(i)].readOnly!=true )
				arr9[parseInt(i)].value=""	;			
			if(arr10[parseInt(i)].readOnly!=true )
				arr10[parseInt(i)].value="";				
			if(arr11[parseInt(i)].readOnly!=true )
				arr11[parseInt(i)].value="";	
		}	
	}
	
	
	function doDelete()
	{
		doSubmitForm('Delete');
	}
	
	function InsertDQZQTZRow()
	{
		var list=document.getElementById("tzmxb_list");

		//��ǰ������ծȨͶ�ʡ����������
		var hc=document.forms[0].dqzqtzMax.value;

		var newRow=list.insertRow(6+parseInt(hc));
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp";

		//Ͷ���ʲ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqzqtz_tzzczl' value='' size='8' tabindex='2'>";
		
		//��Ͷ����ҵ���ڵ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqzqtz_btzqyszd' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'>";
		
		//ռ��Ͷ����ҵȨ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqzqtz_qybl' value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'>";
		
		//��Ͷ����ҵ������ҵ����˰˰��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqzqtz_sl'  value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'>";
		
		//Ͷ�����棨�������棩
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqzqtz_tzsy'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ӧ��˰��Ͷ������������ҵ����˰
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqzqtz_ynqysds'  value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'>";
		
		//Ͷ��ת�þ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqzqtz_tzzrjsr'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��ʼͶ�ʳɱ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqzqtz_cstzcb'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��˰�ɱ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqzqtz_jscbtz'  value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'>";
		
		//Ͷ��ת�óɱ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqzqtz_tzzrcb'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ��ת�����û���ʧ
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqzqtz_tzzrsd'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1' tabindex='-1' name='dqzqtz_chk' value=''>";
		
		//��ǰ������ծȨͶ�ʡ������������1
		document.forms[0].dqzqtzMax.value=parseInt(document.forms[0].dqzqtzMax.value)+1;
		
	}
	
	//ɾ������ծȨͶ������
	function RomoveDQZQTZRow()
	{
		//��ѡ��
		var arrChk=document.all("dqzqtz_chk");
		var list=document.getElementById("tzmxb_list");
		
		//��ǰ������ծȨͶ�ʡ����������
		var hc=document.forms[0].dqzqtzMax.value;
		if(parseInt(document.forms[0].dqzqtzMax.value)>3)
		{
			for(var i=parseInt(parseInt(document.forms[0].dqzqtzMax.value)-1);i>=3;i--)
			{
				if(arrChk.length==undefined)
				{
					if(arrChk.checked)
					{
						if(txt_dqzqtz_null("muti",i))
						{
							if(window.confirm("����ծȨͶ�����е� "+parseInt(parseInt(i)+1) +" �����ݲ�Ϊ�գ�ȷ��ɾ����"))
							{
								list.deleteRow( parseInt(6+parseInt(i)) );
								document.forms[0].dqzqtzMax.value=parseInt(document.forms[0].dqzqtzMax.value)-1;
							}else{
								arrChk.checked=false;
							}
						}else{
							list.deleteRow( parseInt(6+parseInt(i)) );
							document.forms[0].dqzqtzMax.value=parseInt(document.forms[0].dqzqtzMax.value)-1;
						}
					}
				}else{
					if(arrChk[i-3].checked)
					{
						if(txt_dqzqtz_null("muti",i))
						{
							if(window.confirm("����ծȨͶ�����е� "+parseInt(parseInt(i)+1) +" �����ݲ�Ϊ�գ�ȷ��ɾ����"))
							{
								list.deleteRow( parseInt(6+parseInt(i)) );
								document.forms[0].dqzqtzMax.value=parseInt(document.forms[0].dqzqtzMax.value)-1;
							}else{
								arrChk[i-3].checked=false;
							}
						}else{
							list.deleteRow( parseInt(6+parseInt(i)) );
							document.forms[0].dqzqtzMax.value=parseInt(document.forms[0].dqzqtzMax.value)-1;
						}
					}
				}
				
			}
		}	
	}

//�ж϶���ծȨͶ�����������Ƿ�Ϊ��
	//��Ϊ�գ�����true
	//Ϊ�շ���false
	function txt_dqzqtz_null(s,i)
	{
		
			if   (document.all("dqzqtz_tzzczl")[i].value!="")   
			{
				return true;
			}   
			 
			if   (document.all("dqzqtz_tzsy")[i].value!="")   
			{
				return true;
			}   
			if   (document.all("dqzqtz_tzzrjsr")[i].value!="")   
			{
				return true;
			}  
			if   (document.all("dqzqtz_cstzcb")[i].value!="")  
			 {
				return true;
			}   
			
			if   (document.all("dqzqtz_tzzrcb")[i].value!="")  
			 {
				return true;
			}  
			if   (document.all("dqzqtz_tzzrsd")[i].value!="")  
			 {
				return true;
			}  
		return false;
	}
	
	//����ծȨͶ��
	function InsertCQZQTZRow()
	{
		var list=document.getElementById("tzmxb_list");

		//��ǰ������ծȨͶ�ʡ����������
		var hc=document.forms[0].dqzqtzMax.value;
		
		//��ǰ������ծȨͶ�ʡ� ���������
         var cqhc=document.forms[0].cqzqtzMax.value;
		var newRow=list.insertRow(7+parseInt(hc)+parseInt(cqhc));
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp";

		//Ͷ���ʲ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqzqtz_tzzczl'  value='' size='8' tabindex='2'>";
		
		//��Ͷ����ҵ���ڵ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqzqtz_btzqyszd'  value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'>";
		
		//ռ��Ͷ����ҵȨ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqzqtz_qybl'  value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'>";
		
		//��Ͷ����ҵ������ҵ����˰˰��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqzqtz_sl'  value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'>";
		
		//Ͷ�����棨�������棩
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqzqtz_tzsy'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ӧ��˰��Ͷ������������ҵ����˰
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';		
		newCell.innerHTML="<input type='text' name='cqzqtz_ynqysds'  value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'>";
		
		//Ͷ��ת�þ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqzqtz_tzzrjsr'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��ʼͶ�ʳɱ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqzqtz_cstzcb'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��˰�ɱ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqzqtz_jscbtz'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ��ת�óɱ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqzqtz_tzzrcb'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ��ת�����û���ʧ
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqzqtz_tzzrsd'  value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='cqzqtz_chk' value=''>";
		
		//��ǰ������ծȨͶ�ʡ������������1
		document.forms[0].cqzqtzMax.value=parseInt(document.forms[0].cqzqtzMax.value)+1;
		
	}
	
	//ɾ������ծȨͶ������
	function RomoveCQZQTZRow()
	{
		//��ѡ��
		var arrChk=document.all("cqzqtz_chk");
		var list=document.getElementById("tzmxb_list");
		var dqzqtzhc = document.forms[0].dqzqtzMax.value;
		
		if(parseInt(document.forms[0].cqzqtzMax.value)>3)
		{
			for(var i=parseInt(parseInt(document.forms[0].cqzqtzMax.value)-1);i>=3;i--)
			{
				if(arrChk.length==undefined)
				{
					if(arrChk.checked)
					{
					   if(txt_cqzqtz_null("muti",i))
					   {
						if(window.confirm("����ծȨͶ�����е� "+parseInt(parseInt(i)+1) +" �����ݲ�Ϊ�գ�ȷ��ɾ����"))
						{
							list.deleteRow( parseInt(7+parseInt(i)+parseInt(dqzqtzhc)) );
							document.forms[0].cqzqtzMax.value=parseInt(document.forms[0].cqzqtzMax.value)-1;
						}else{
							arrChk.checked=false;
						}
					}else{
						list.deleteRow( parseInt(7+parseInt(i)+parseInt(dqzqtzhc)) );
						document.forms[0].cqzqtzMax.value=parseInt(document.forms[0].cqzqtzMax.value)-1;
					}
				}
		}else{
		if(arrChk[i-3].checked)
		{
		   if(txt_cqzqtz_null("muti",i))
		   {
		      if(window.confirm("����ծȨͶ�����е� "+parseInt(parseInt(i)+1) +" �����ݲ�Ϊ�գ�ȷ��ɾ����"))
		      {
		        list.deleteRow(7+parseInt(dqzqtzhc)+parseInt(i));
				document.forms[0].cqzqtzMax.value=parseInt(document.forms[0].cqzqtzMax.value)-1;
			  }else{
					arrChk[i-3].checked=false;
			  }
			}else{
					list.deleteRow(7+parseInt(dqzqtzhc)+parseInt(i));
					document.forms[0].cqzqtzMax.value=parseInt(document.forms[0].cqzqtzMax.value)-1;
			}
		}
			}
		}
	}		
}

//�жϳ���ծȨͶ�����������Ƿ�Ϊ��
	//��Ϊ�գ�����true
	//Ϊ�շ���false
	function txt_cqzqtz_null(s,i)
	{		
	        
			if   (document.all("cqzqtz_tzzczl")[i].value!="")   {
				return true;
			}  
		
			if   (document.all("cqzqtz_tzsy")[i].value!="")   {
				return true;
			} 
		
			if   (document.all("cqzqtz_tzzrjsr")[i].value!="")   {
				return true;
			}  
			
			if   (document.all("cqzqtz_cstzcb")[i].value!="")   {
				return true;
			} 
		 
			if   (document.all("cqzqtz_jscbtz")[i].value!="")   {
				return true;
			} 
		
			if   (document.all("cqzqtz_tzzrcb")[i].value!="")   {
				return true;
			} 
	
			if   (document.all("cqzqtz_tzzrsd")[i].value!="")   {
				return true;
			}  
		return false;
	}
	

	function InsertDQGQTZRow()
	{
		var list=document.getElementById("tzmxb_list");

		//��ǰ������ծȨͶ�ʡ����������
		var hc=document.forms[0].dqzqtzMax.value;
		var cqhc=document.forms[0].cqzqtzMax.value;
        var gqhc=document.forms[0].dqgqtzMax.value;
		var newRow=list.insertRow(9+parseInt(hc)+parseInt(cqhc)+parseInt(gqhc));
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp";

		//Ͷ���ʲ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_tzzczl' id='dqgqtz_tzzczl_"+parseInt(hc)+1+"_1' value='' size='8' tabindex='2'>";
		
		//��Ͷ����ҵ���ڵ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_btzqyszd' id='dqgqtz_btzqyszd_"+parseInt(hc)+1+"_1' value='' size='8' tabindex='2'>";
		
		//ռ��Ͷ����ҵȨ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_qybl' id='dqgqtz_qybl_"+parseInt(hc)+1+"_2' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��Ͷ����ҵ������ҵ����˰˰��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_sl' id='dqgqtz_sl_"+parseInt(hc)+1+"_3' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ�����棨�������棩
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_tzsy' id='dqgqtz_tzsy_"+parseInt(hc)+1+"_4' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ӧ��˰��Ͷ������������ҵ����˰
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_ynqysds' id='dqgqtz_ynqysds_"+parseInt(hc)+1+"_5' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ��ת�þ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_tzzrjsr' id='dqgqtz_tzzrjsr_"+parseInt(hc)+1+"_6' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��ʼͶ�ʳɱ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_cstzcb' id='dqgqtz_cstzcb_"+parseInt(hc)+1+"_7' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��˰�ɱ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_jscbtz' id='dqgqtz_jscbtz_"+parseInt(hc)+1+"_8' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ��ת�óɱ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_tzzrcb' id='dqgqtz_tzzrcb_"+parseInt(hc)+1+"_9' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ��ת�����û���ʧ
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='dqgqtz_tzzrsd' id='dqgqtz_tzzrsd_"+parseInt(hc)+1+"_10' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='dqgqtz_chk' value=''>";
		
		//��ǰ�����ڹ�ȨͶ�ʡ������������1
		document.forms[0].dqgqtzMax.value=parseInt(document.forms[0].dqgqtzMax.value)+1;
		
	}
	
	//ɾ�����ڹ�ȨͶ������
	function RomoveDQGQTZRow()
	{
		//��ѡ��
		var arrChk=document.all("dqgqtz_chk");
		var list=document.getElementById("tzmxb_list");
		var dqzqtzhc=document.forms[0].dqzqtzMax.value;
		var cqzqtzhc=document.forms[0].cqzqtzMax.value;
		
		if(parseInt(document.forms[0].dqgqtzMax.value)>2)
		{
			for(var i=parseInt(parseInt(document.forms[0].dqgqtzMax.value)-1);i>=2;i--)
			{
				if(arrChk.length==undefined)
				{
					if(arrChk.checked)
					{
					   if(txt_dqgqtz_null("muti",i))
					   {
						 if(window.confirm("���ڹ�ȨͶ�����е� "+parseInt(parseInt(i)+1) +" �����ݲ�Ϊ�գ�ȷ��ɾ����"))
						 {
							list.deleteRow( parseInt(parseInt(dqzqtzhc)+parseInt(cqzqtzhc)+9+parseInt(i)) );
							document.forms[0].dqgqtzMax.value=parseInt(document.forms[0].dqgqtzMax.value)-1;
						}else{
							arrChk.checked=false;
						}
					}else{
						list.deleteRow( parseInt(parseInt(dqzqtzhc)+parseInt(cqzqtzhc)+9+parseInt(i)) );
						document.forms[0].dqgqtzMax.value=parseInt(document.forms[0].dqgqtzMax.value)-1;
					}
				}
			
		}	else{
		     if(arrChk[i-2].checked)
		     {
		        if(txt_dqgqtz_null("muti",i))
		        {			
							if(window.confirm("���ڹ�ȨͶ�����е� "+parseInt(parseInt(i)+1) +" �����ݲ�Ϊ�գ�ȷ��ɾ����"))
							{
								list.deleteRow(parseInt(dqzqtzhc)+parseInt(cqzqtzhc)+9+parseInt(i));
								document.forms[0].dqgqtzMax.value=parseInt(document.forms[0].dqgqtzMax.value)-1;
							}else{
								arrChk[i-2].checked=false;
							}
						}else{
							list.deleteRow(parseInt(dqzqtzhc)+parseInt(cqzqtzhc)+9+parseInt(i));
							document.forms[0].dqgqtzMax.value=parseInt(document.forms[0].dqgqtzMax.value)-1;
						}
				}
			}
		}
	}
}



//�ж϶��ڹ�ȨͶ�����������Ƿ�Ϊ��
	//��Ϊ�գ�����true
	//Ϊ�շ���false
	function txt_dqgqtz_null(s,i){
		if (s=="1"){   
			if (document.all("dqgqtz_tzzczl").value!="" && document.all("dqgqtz_tzzczl").value!=undefined)  {
				return true;
			} 
			if   (document.all("dqgqtz_btzqyszd").value!="" && document.all("dqgqtz_btzqyszd").value!=undefined)   {
				return true;
			}  
			if   (document.all("dqgqtz_qybl").value!="" && document.all("dqgqtz_qybl").value!=undefined)   {
				return true;
			}  
			if   (document.all("dqgqtz_sl").value!="" && document.all("dqgqtz_sl").value!=undefined)   {
				return true;
			}  
			if (document.all("dqgqtz_tzsy").value!="" && document.all("dqgqtz_tzsy").value!=undefined)  {
				return true;
			} 
			if   (document.all("dqgqtz_ynqysds").value!="" && document.all("dqgqtz_ynqysds").value!=undefined)   {
				return true;
			}  
			if   (document.all("dqgqtz_tzzrjsr").value!="" && document.all("dqgqtz_tzzrjsr").value!=undefined)   {
				return true;
			}  
			if   (document.all("dqgqtz_cstzcb").value!="" && document.all("dqgqtz_cstzcb").value!=undefined)   {
				return true;
			}  
			if (document.all("dqgqtz_jscbtz").value!="" && document.all("dqgqtz_jscbtz").value!=undefined)  {
				return true;
			} 
			if   (document.all("dqgqtz_tzzrcb").value!="" && document.all("dqgqtz_tzzrcb").value!=undefined)   {
				return true;
			}  
			if   (document.all("dqgqtz_tzzrsd").value!="" && document.all("dqgqtz_tzzrsd").value!=undefined)   {
				return true;
			}  
			
		}else{   
			if   (document.all("dqgqtz_tzzczl")[i].value!="")   {
				return true;
			}   
			if   (document.all("dqgqtz_btzqyszd")[i].value!="")   {
				return true;
			}  
			if   (document.all("dqgqtz_qybl")[i].value!="")   {
				return true;
			}  
			if   (document.all("dqgqtz_sl")[i].value!="")   {
				return true;
			}  
			if   (document.all("dqgqtz_tzsy")[i].value!="")   {
				return true;
			}   
			if   (document.all("dqgqtz_ynqysds")[i].value!="")   {
				return true;
			}  
			if   (document.all("dqgqtz_tzzrjsr")[i].value!="")   {
				return true;
			}  
			if   (document.all("dqgqtz_cstzcb")[i].value!="")   {
				return true;
			}   
			if   (document.all("dqgqtz_jscbtz")[i].value!="")   {
				return true;
			}   
			if   (document.all("dqgqtz_tzzrcb")[i].value!="")   {
				return true;
			}  
			if   (document.all("dqgqtz_tzzrsd")[i].value!="")   {
				return true;
			}  
		
		}
		return false;
	}
	


	function InsertCQGQTZRow()
	{
		var list=document.getElementById("tzmxb_list");

		//��ǰ������ծȨͶ�ʡ����������
		var  cqgqhc=document.forms[0].cqgqtzMax.value;
        var hc=document.forms[0].dqzqtzMax.value;
		var cqhc=document.forms[0].cqzqtzMax.value;
        var gqhc=document.forms[0].dqgqtzMax.value;
		var newRow=list.insertRow(10+parseInt(hc)+parseInt(cqhc)+parseInt(gqhc)+parseInt(cqgqhc));
		
		//�д�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="&nbsp";

		//Ͷ���ʲ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_tzzczl' id='cqgqtz_tzzczl_"+parseInt(hc)+1+"_1' value='' size='8' tabindex='2'>";
		
		//��Ͷ����ҵ���ڵ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_btzqyszd' id='cqgqtz_btzqyszd_"+parseInt(hc)+1+"_1' value='' size='8' tabindex='2'>";
		
		//ռ��Ͷ����ҵȨ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_qybl' id='cqgqtz_qybl_"+parseInt(hc)+1+"_2' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��Ͷ����ҵ������ҵ����˰˰��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_sl' id='cqgqtz_sl_"+parseInt(hc)+1+"_3' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ�����棨�������棩
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_tzsy' id='cqgqtz_tzsy_"+parseInt(hc)+1+"_4' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ӧ��˰��Ͷ������������ҵ����˰
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_ynqysds' id='cqgqtz_ynqysds_"+parseInt(hc)+1+"_5' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ��ת�þ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_tzzrjsr' id='cqgqtz_tzzrjsr_"+parseInt(hc)+1+"_6' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��ʼͶ�ʳɱ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_cstzcb' id='cqgqtz_cstzcb_"+parseInt(hc)+1+"_7' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��˰�ɱ�����
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_jscbtz' id='cqgqtz_jscbtz_"+parseInt(hc)+1+"_8' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ��ת�óɱ�
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_tzzrcb' id='cqgqtz_tzzrcb_"+parseInt(hc)+1+"_9' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//Ͷ��ת�����û���ʧ
		var newCell=newRow.insertCell();
		newCell.className='2-td2-left';
		newCell.innerHTML="<input type='text' name='cqgqtz_tzzrsd' id='cqgqtz_tzzrsd_"+parseInt(hc)+1+"_10' value='' size='8' tabindex='2' style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'>";
		
		//��ѡ��
		var newCell=newRow.insertCell();
		newCell.className='2-td2-center';
		newCell.innerHTML="<input type='checkbox' tabindex='-1'  name='cqgqtz_chk' value=''>";
		
		//��ǰ�����ڹ�ȨͶ�ʡ������������1
		document.forms[0].cqgqtzMax.value=parseInt(document.forms[0].cqgqtzMax.value)+1;
		
	}
	
	//ɾ�����ڹ�ȨͶ������
	function RomoveCQGQTZRow()
	{
		//��ѡ��
		var arrChk=document.all("cqgqtz_chk");
		var list=document.getElementById("tzmxb_list");
		
        var dqzq=document.forms[0].dqzqtzMax.value;
		var cqzq=document.forms[0].cqzqtzMax.value;
        var dqgq=document.forms[0].dqgqtzMax.value;

		//��ǰ�����ڹ�ȨͶ�ʡ����������
		var hc=document.forms[0].cqgqtzMax.value;

		if(parseInt(document.forms[0].cqgqtzMax.value)>2)
		{
			for(var i=parseInt(parseInt(document.forms[0].cqgqtzMax.value)-1);i>=2;i--)
			{
				if(arrChk.length==undefined)
				{
				  if(arrChk.checked)
				  {
					if(txt_cqgqtz_null("muti",i))
					{
						if(window.confirm("���ڹ�ȨͶ�����е� "+parseInt(parseInt(i)+1) +" �����ݲ�Ϊ�գ�ȷ��ɾ����"))
						{
							list.deleteRow( parseInt(10+parseInt(i)+parseInt(dqzq)+parseInt(cqzq)+parseInt(dqgq)) );
							document.forms[0].cqgqtzMax.value=parseInt(document.forms[0].cqgqtzMax.value)-1;
						}else{
							arrChk.checked=false;
						}
					}else{
						list.deleteRow( parseInt(10+parseInt(i)+parseInt(dqzq)+parseInt(cqzq)+parseInt(dqgq)) );
						document.forms[0].cqgqtzMax.value=parseInt(document.forms[0].cqgqtzMax.value)-1;
					}
				}
		}else{
			if(arrChk[i-2].checked)
			{
						if(txt_cqgqtz_null('1',null))
						{
							if(window.confirm("���ڹ�ȨͶ�����е� "+parseInt(parseInt(i)+1) +" �����ݲ�Ϊ�գ�ȷ��ɾ����"))
							{
								list.deleteRow(10+parseInt(dqzq)+parseInt(cqzq)+parseInt(dqgq)+parseInt(i));
								document.forms[0].cqgqtzMax.value=parseInt(document.forms[0].cqgqtzMax.value)-1;
							}else{
								arrChk[i-2].checked=false;
							}
						}else{
							list.deleteRow(10+parseInt(dqzq)+parseInt(cqzq)+parseInt(dqgq)+parseInt(i));
							document.forms[0].cqgqtzMax.value=parseInt(document.forms[0].cqgqtzMax.value)-1;
						}
				}
			}
		}
	}
}

//�жϳ��ڹ�ȨͶ�����������Ƿ�Ϊ��
	//��Ϊ�գ�����true
	//Ϊ�շ���false
	function txt_cqgqtz_null(s,i){
		if (s=="1"){   
			if (document.all("cqgqtz_tzzczl").value!="" && document.all("cqgqtz_tzzczl").value!=undefined)  {
				return true;
			} 
			if   (document.all("cqgqtz_btzqyszd").value!="" && document.all("cqgqtz_btzqyszd").value!=undefined)   {
				return true;
			}  
			if   (document.all("cqgqtz_qybl").value!="" && document.all("cqgqtz_qybl").value!=undefined)   {
				return true;
			}  
			if   (document.all("cqgqtz_sl").value!="" && document.all("cqgqtz_sl").value!=undefined)   {
				return true;
			}  
			if (document.all("cqgqtz_tzsy").value!="" && document.all("cqgqtz_tzsy").value!=undefined)  {
				return true;
			} 
			if   (document.all("cqgqtz_ynqysds").value!="" && document.all("cqgqtz_ynqysds").value!=undefined)   {
				return true;
			}  
			if   (document.all("cqgqtz_tzzrjsr").value!="" && document.all("cqgqtz_tzzrjsr").value!=undefined)   {
				return true;
			}  
			if   (document.all("cqgqtz_cstzcb").value!="" && document.all("cqgqtz_cstzcb").value!=undefined)   {
				return true;
			}  
			if (document.all("cqgqtz_jscbtz").value!="" && document.all("cqgqtz_jscbtz").value!=undefined)  {
				return true;
			} 
			if   (document.all("cqgqtz_tzzrcb").value!="" && document.all("cqgqtz_tzzrcb").value!=undefined)   {
				return true;
			}  
			if   (document.all("cqgqtz_tzzrsd").value!="" && document.all("cqgqtz_tzzrsd").value!=undefined)   {
				return true;
			}  
			
		}else{   
		
			if   (document.all("cqgqtz_tzzczl")[i].value!="")   {
				return true;
			}   
			if   (document.all("cqgqtz_btzqyszd")[i].value!="")   {
				return true;
			}  
			if   (document.all("cqgqtz_qybl")[i].value!="")   {
				return true;
			}  
			if   (document.all("cqgqtz_sl")[i].value!="")   {
				return true;
			}  
			if   (document.all("cqgqtz_tzsy")[i].value!="")   {
				return true;
			}   
			if   (document.all("cqgqtz_ynqysds")[i].value!="")   {
				return true;
			}  
			if   (document.all("cqgqtz_tzzrjsr")[i].value!="")   {
				return true;
			}  
			if   (document.all("cqgqtz_cstzcb")[i].value!="")   {
				return true;
			}   
			if   (document.all("cqgqtz_jscbtz")[i].value!="")   {
				return true;
			}   
			if   (document.all("cqgqtz_tzzrcb")[i].value!="")   {
				return true;
			}  
			if   (document.all("cqgqtz_tzzrsd")[i].value!="")   {
				return true;
			}  
		
		}
		return false;
	}
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
	
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInit()">
<%@ include file="../include/header.jsp"%>
<br>

<html:form action="/webapp/smsb/qysds/tzmxbAction.do">
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
	<%/*����ծȨͶ����������к�*/

			%>
	<input type='hidden' name='dqzqtzMax' value='3' />
	<%/*����ծȨͶ����������к�*/

			%>
	<input type='hidden' name='cqzqtzMax' value='3' />
	<%/*���ڹ�ȨͶ����������к�*/

			%>
	<input type='hidden' name='dqgqtzMax' value='2' />
	<%/*���ڹ�ȨͶ����������к�*/

			%>
	<input type='hidden' name='cqgqtzMax' value='2' />

	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">Ͷ������(��ʧ)��ϸ��</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="tzmxbForm" property="sbrq" scope="request" filter="true" />
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
				name="tzmxbForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="tzmxbForm" property="nsrmc" scope="request" filter="true" />&nbsp;
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
			<TABLE class="table-99" align="center">
				<TR>
					<TD>
					<div id="Layer2" style="position:static; ">
					<table id="tzmxb_list" border="1" cellspacing="0" class="table-99"
						align="center">
						<tr>
							<td rowspan="4" class="2-td1-left">�д�</td>
							<td rowspan="4" class="2-td1-left">Ͷ���ʲ�����</td>
							<td rowspan="3" class="2-td1-left">��Ͷ����ҵ���ڵ�</td>
							<td rowspan="3" class="2-td1-left">ռ��Ͷ����ҵȨ�����</td>
							<td rowspan="3" class="2-td1-left">��Ͷ����ҵ������ҵ����˰˰��</td>
							<td rowspan="3" class="2-td1-left">Ͷ�����棨�������棩</td>
							<td rowspan="3" class="2-td1-left">Ӧ��˰��Ͷ������������ҵ����˰</td>
							<td colspan="5" class="2-td1-center">Ͷ��ת�����û���ʧ(��������)</td>
						</tr>
						<tr>
							<td rowspan="2" class="2-td1-left">Ͷ��ת�þ�����</td>
							<td colspan="3" class="2-td1-left">Ͷ��ת�óɱ�</td>
							<td rowspan="2" class="2-td1-center">Ͷ��ת�����û���ʧ</td>
						</tr>
						<tr>
							<td class="2-td1-left">��ʼͶ�ʳɱ�</td>
							<td class="2-td1-left">��˰�ɱ�����</td>
							<td class="2-td1-center">Ͷ��ת�óɱ�</td>
						</tr>
						<tr>
							<td class="2-td2-left">1</td>
							<td class="2-td2-left">2</td>
							<td class="2-td2-left">3</td>
							<td class="2-td2-left">4</td>
							<td class="2-td2-left">5</td>
							<td class="2-td2-left">6</td>
							<td class="2-td2-left">7</td>
							<td class="2-td2-left">8</td>
							<td class="2-td2-left">9</td>
							<td class="2-td2-center">10</td>
						</tr>
						<tr>
							<td class="2-td2-left">1</td>
							<td class="2-td2-left">һ��ծȨͶ�ʣ�С�ƣ�</td>
							<td class="2-td2-left"><input type='text' name='btzqyszd'
								id='btzqyszd_1' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='qybl' id='qybl_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='sl' id='sl_1'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='tzsy' id='tzsy_1'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='ynqysds'
								id='ynqysds_1' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='tzzrjsr'
								id='tzzrjsr_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cstzcb'
								id='cstzcb_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='jscbtz'
								id='jscbtz_1' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='tzzrcb'
								id='tzzrcb_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tzzrsd'
								id='tzzrsd_1' value='' size='8' tabindex='2'></td>
							<input type="hidden" name="hc" value="1" />
						</tr>
						<tr>
							<td class="2-td2-left">2</td>
							<td class="2-td2-left">��һ������ծȨͶ�ʣ�С�ƣ�</td>
							<td class="2-td2-left"><input type='text' name='btzqyszd'
								id='btzqyszd_2' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='qybl' id='qybl_2'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='sl' id='sl_2'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='tzsy' id='tzsy_2'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='ynqysds'
								id='ynqysds_2' value='' tabindex='-1' size='8' class='text-noborder'
								readonly='true'></td>
							<td class="2-td2-left"><input type='text' name='tzzrjsr'
								id='tzzrjsr_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cstzcb'
								id='cstzcb_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='jscbtz'
								id='jscbtz_2' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='tzzrcb'
								id='tzzrcb_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tzzrsd'
								id='tzzrsd_2' value='' size='8' tabindex='2'></td>
							<input type="hidden" name="hc" value="2" />
							<td class="2-td2-left"><input type="button" tabindex='-1' value="����"
								onclick="InsertDQZQTZRow()"></td>
							<td class="2-td2-center"><input type="button" tabindex='-1' value='ɾ��'
								onClick="RomoveDQZQTZRow()"></td>
						</tr>
						<tr>
							<td class="2-td2-left">3</td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_tzzczl'
								id="dqzqtz_tzzczl_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_btzqyszd'
								id="dqzqtz_btzqyszd_1" value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_qybl'
								id="dqzqtz_qybl_1" value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_sl'
								id="dqzqtz_sl_1" value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_tzsy'
								id="dqzqtz_tzsy_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_ynqysds'
								id="dqzqtz_ynqysds_1" value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_tzzrjsr'
								id="dqzqtz_tzzrjsr_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_cstzcb'
								value='' id="dqzqtz_cstzcb_1" size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_jscbtz'
								id="dqzqtz_jscbtz_1" value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_tzzrcb'
								id="dqzqtz_tzzrcb_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='dqzqtz_tzzrsd'
								id="dqzqtz_tzzrsd_1" value='' size='8' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">4</td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_tzzczl'
								id="dqzqtz_tzzczl_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_btzqyszd'
								id="dqzqtz_btzqyszd_2" value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_qybl'
								id="dqzqtz_qybl_2" value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_sl'
								id="dqzqtz_sl_2" value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_tzsy'
								id="dqzqtz_tzsy_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_ynqysds'
								id="dqzqtz_ynqysds_2" value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_tzzrjsr'
								id="dqzqtz_tzzrjsr_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_cstzcb'
								id="dqzqtz_cstzcb_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_jscbtz'
								id="dqzqtz_jscbtz_2" value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_tzzrcb'
								id="dqzqtz_tzzrcb_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='dqzqtz_tzzrsd'
								id="dqzqtz_tzzrsd_2" value='' size='8' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">5</td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_tzzczl'
								id="dqzqtz_tzzczl_3" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_btzqyszd'
								id="dqzqtz_btzqyszd_3" value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_qybl'
								id="dqzqtz_qybl_3" value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_sl'
								id="dqzqtz_sl_3" value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_tzsy'
								id="dqzqtz_tzsy_3" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_ynqysds'
								id="dqzqtz_ynqysds_3" value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_tzzrjsr'
								id="dqzqtz_tzzrjsr_3" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_cstzcb'
								id="dqzqtz_cstzcb_3" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_jscbtz'
								id="dqzqtz_jscbtz_3" value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='dqzqtz_tzzrcb'
								id="dqzqtz_tzzrcb_3" value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='dqzqtz_tzzrsd'
								id="dqzqtz_tzzrsd_3" value='' size='8' tabindex='2'></td>
						</tr>

						<tr>
							<td class="2-td2-left">6</td>
							<td class="2-td2-left">����������ծȨͶ�ʣ�С�ƣ�</td>
							<td class="2-td2-left"><input type='text' name='btzqyszd'
								id='btzqyszd_3' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='qybl' id='qybl_3'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='sl' id='sl_3'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='tzsy' id='tzsy_3'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='ynqysds'
								id='ynqysds_3' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='tzzrjsr'
								id='tzzrjsr_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cstzcb'
								id='cstzcb_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='jscbtz'
								id='jscbtz_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='tzzrcb'
								id='tzzrcb_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tzzrsd'
								id='tzzrsd_3' value='' size='8' tabindex='2'></td>
							<input type="hidden" name="hc" value="3" />
							<td class="2-td2-left"><input type="button" tabindex='-1' value="����"
								onclick="InsertCQZQTZRow()"></td>
							<td class="2-td2-center"><input type="button" tabindex='-1' value='ɾ��'
								onClick="RomoveCQZQTZRow()"></td>
						</tr>
						<tr>
							<td class="2-td2-left">7</td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_tzzczl'
								id="cqzqtz_tzzczl_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_btzqyszd'
								value='*' tabindex='-1' size='1' id="cqzqtz_btzqyszd_1" readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_qybl'
								value='*' tabindex='-1' size='1' id="cqzqtz_qybl_1" readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_sl'
								value='*' tabindex='-1' size='1' id="cqzqtz_sl_1" readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_tzsy'
								id="cqzqtz_tzsy_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_ynqysds'
								value='*' tabindex='-1' size='1' id="cqzqtz_ynqysds_1" readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_tzzrjsr'
								id="cqzqtz_tzzrjsr_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_cstzcb'
								value='' id="cqzqtz_cstzcb_1" size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_jscbtz'
								id="cqzqtz_jscbtz_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_tzzrcb'
								id="cqzqtz_tzzrcb_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='cqzqtz_tzzrsd'
								id="cqzqtz_tzzrsd_1" value='' size='8' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">8</td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_tzzczl'
								id="cqzqtz_tzzczl_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_btzqyszd'
								value='*' tabindex='-1' size='1' id="cqzqtz_btzqyszd_2" readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_qybl'
								value='*' tabindex='-1' size='1' id="cqzqtz_qybl_2" readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_sl'
								value='*' tabindex='-1' size='1' id="cqzqtz_sl_2" readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_tzsy'
								id="cqzqtz_tzsy_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_ynqysds'
								value='*' tabindex='-1' size='1' id="cqzqtz_ynqysds_2" readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_tzzrjsr'
								id="cqzqtz_tzzrjsr_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_cstzcb'
								value='' id="cqzqtz_cstzcb_2" size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_jscbtz'
								id="cqzqtz_jscbtz_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_tzzrcb'
								id="cqzqtz_tzzrcb_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='cqzqtz_tzzrsd'
								id="cqzqtz_tzzrsd_2" value='' size='8' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">9</td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_tzzczl'
								id="cqzqtz_tzzczl_3" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_btzqyszd'
								value='*' tabindex='-1' size='1' id="cqzqtz_btzqyszd_3" readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_qybl'
								value='*' tabindex='-1' size='1' id="cqzqtz_qybl_3" readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_sl'
								value='*' tabindex='-1' size='1' id="cqzqtz_sl_3" readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_tzsy'
								id="cqzqtz_tzsy_3" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_ynqysds'
								value='*' tabindex='-1' size='1' id="cqzqtz_ynqysds_3" readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_tzzrjsr'
								id="cqzqtz_tzzrjsr_3" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_cstzcb'
								value='' id="cqzqtz_cstzcb_3" size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_jscbtz'
								id="cqzqtz_jscbtz_3" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqzqtz_tzzrcb'
								id="cqzqtz_tzzrcb_3" value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='cqzqtz_tzzrsd'
								id="cqzqtz_tzzrsd_3" value='' size='8' tabindex='2'></td>
						</tr>

						<tr>
							<td class="2-td2-left">10</td>
							<td class="2-td2-left">������ȨͶ�ʣ�С�ƣ�</td>
							<td class="2-td2-left"><input type='text' name='btzqyszd'
								id='btzqyszd_4' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='qybl' id='qybl_4'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='sl' id='sl_4'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='tzsy' id='tzsy_4'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='ynqysds'
								id='ynqysds_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='tzzrjsr'
								id='tzzrjsr_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cstzcb'
								id='cstzcb_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='jscbtz'
								id='jscbtz_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='tzzrcb'
								id='tzzrcb_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tzzrsd'
								id='tzzrsd_4' value='' size='8' tabindex='2'></td>
							<input type="hidden" name="hc" value="4" />
						</tr>
						<tr>
							<td class="2-td2-left">11</td>
							<td class="2-td2-left">��һ�����ڹ�ȨͶ�ʣ�С�ƣ�</td>
							<td class="2-td2-left"><input type='text' name='btzqyszd'
								id='btzqyszd_5' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='qybl' id='qybl_5'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='sl' id='sl_5'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='tzsy' id='tzsy_5'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='ynqysds'
								id='ynqysds_5' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='tzzrjsr'
								id='tzzrjsr_5' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cstzcb'
								id='cstzcb_5' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='jscbtz'
								id='jscbtz_5' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='tzzrcb'
								id='tzzrcb_5' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tzzrsd'
								id='tzzrsd_5' value='' size='8' tabindex='2'></td>
							<input type="hidden" name="hc" value="5" />
							<td class="2-td2-left"><input type="button" tabindex='-1' value="����"
								onclick="InsertDQGQTZRow()"></td>
							<td class="2-td2-center"><input type="button" tabindex='-1' value='ɾ��'
								onClick="RomoveDQGQTZRow()"></td>

						</tr>
						<tr>
							<td class="2-td2-left">12</td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_tzzczl'
								id="dqgqtz_tzzczl_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_btzqyszd'
								id="dqgqtz_btzqyszd_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_qybl'
								id="dqgqtz_qybl_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_sl'
								id="dqgqtz_sl_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_tzsy'
								id="dqgqtz_tzsy_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_ynqysds'
								id="dqgqtz_ynqysds_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_tzzrjsr'
								id="dqgqtz_tzzrjsr_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_cstzcb'
								id="dqgqtz_cstzcb_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_jscbtz'
								id="dqgqtz_jscbtz_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_tzzrcb'
								id="dqgqtz_tzzrcb_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='dqgqtz_tzzrsd'
								id="dqgqtz_tzzrsd_1" value='' size='8' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">13</td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_tzzczl'
								id="dqgqtz_tzzczl_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_btzqyszd'
								id="dqgqtz_btzqyszd_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_qybl'
								id="dqgqtz_qybl_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_sl'
								id="dqgqtz_sl_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_tzsy'
								id="dqgqtz_tzsy_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_ynqysds'
								id="dqgqtz_ynqysds_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_tzzrjsr'
								id="dqgqtz_tzzrjsr_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_cstzcb'
								id="dqgqtz_cstzcb_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_jscbtz'
								id="dqgqtz_jscbtz_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='dqgqtz_tzzrcb'
								id="dqgqtz_tzzrcb_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='dqgqtz_tzzrsd'
								id="dqgqtz_tzzrsd_2" value='' size='8' tabindex='2'></td>
						</tr>

						<tr>
							<td class="2-td2-left">14</td>
							<td class="2-td2-left">���������ڹ�ȨͶ�ʣ�С�ƣ�</td>
							<td class="2-td2-left"><input type='text' name='btzqyszd'
								id='btzqyszd_6' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='qybl' id='qybl_6'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='sl' id='sl_6'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='tzsy' id='tzsy_6'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='ynqysds'
								id='ynqysds_6' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='tzzrjsr'
								id='tzzrjsr_6' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cstzcb'
								id='cstzcb_6' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='jscbtz'
								id='jscbtz_6' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='tzzrcb'
								id='tzzrcb_6' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tzzrsd'
								id='tzzrsd_6' value='' size='8' tabindex='2'></td>
							<input type="hidden" name="hc" value="6" />
							<td class="2-td2-left"><input type="button" tabindex='-1' value="����"
								onclick="InsertCQGQTZRow()"></td>
							<td class="2-td2-center"><input type="button" tabindex='-1' value='ɾ��'
								onClick="RomoveCQGQTZRow()"></td>
						</tr>
						<tr>
							<td class="2-td2-left">15</td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_tzzczl'
								id="cqgqtz_tzzczl_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_btzqyszd'
								id="cqgqtz_btzqyszd_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_qybl'
								id="cqgqtz_qybl_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_sl'
								id="cqgqtz_sl_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_tzsy'
								id="cqgqtz_tzsy_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_ynqysds'
								id="cqgqtz_ynqysds_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_tzzrjsr'
								id="cqgqtz_tzzrjsr_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_cstzcb'
								id="cqgqtz_cstzcb_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_jscbtz'
								id="cqgqtz_jscbtz_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_tzzrcb'
								id="cqgqtz_tzzrcb_1" value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='cqgqtz_tzzrsd'
								id="cqgqtz_tzzrsd_1" value='' size='8' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left">16</td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_tzzczl'
								id="cqgqtz_tzzczl_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_btzqyszd'
								id="cqgqtz_btzqyszd_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_qybl'
								id="cqgqtz_qybl_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_sl'
								id="cqgqtz_sl_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_tzsy'
								id="cqgqtz_tzsy_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_ynqysds'
								id="cqgqtz_ynqysds_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_tzzrjsr'
								id="cqgqtz_tzzrjsr_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_cstzcb'
								id="cqgqtz_cstzcb_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_jscbtz'
								id="cqgqtz_jscbtz_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cqgqtz_tzzrcb'
								id="cqgqtz_tzzrcb_2" value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='cqgqtz_tzzrsd'
								id="cqgqtz_tzzrsd_2" value='' size='8' tabindex='2'></td>
						</tr>

						<tr>
							<td class="2-td2-left">17</td>
							<td class="2-td2-left">�ϼ�</td>
							<td class="2-td2-left"><input type='text' name='btzqyszd'
								id='btzqyszd_7' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='qybl' id='qybl_7'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='sl' id='sl_7'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='tzsy' id='tzsy_7'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='ynqysds'
								id='ynqysds_7' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='tzzrjsr'
								id='tzzrjsr_7' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='cstzcb'
								id='cstzcb_7' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='jscbtz'
								id='jscbtz_7' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='tzzrcb'
								id='tzzrcb_7' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='tzzrsd'
								id='tzzrsd_7' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<input type="hidden" name="hc" value="7" />
						</tr>
						<tr>
							<td colspan="12" class="2-td2-center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��������</td>
						</tr>
						<tr>
							<td colspan="4" class="2-td2-left-qysds1">1����ǰ��Ƚ�ת�ڱ����˰ǰ�۳��Ĺ�ȨͶ��ת����ʧ</td>
							<td colspan="2" class="2-td2-left"><input type='text'
								name='gqtzzrss' id='1' value='' size='13' tabindex='2'
								style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'></td>
							<td colspan="5" class="2-td2-left-qysds1">2������ȹ�ȨͶ��ת����ʧ˰ǰ�۳��޶�</td>
							<td class="2-td2-center"><input type='text' name='gqsszrsqkcqe'
								id='2' value='' size='13' tabindex='2'
								style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'></td>
						</tr>
						<tr>
							<td colspan="4" class="2-td2-left-qysds1">3��Ͷ��ת�þ���ʧ��˰������</td>
							<td colspan="2" class="2-td2-left"><input type='text'
								name='nstze' id='3' value='' size='13' tabindex='2'
								style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'></td>
							<td colspan="5" class="2-td2-left-qysds1">4��Ͷ��ת�þ���ʧ��ת�Ժ���ȿ۳����ۼƣ�</td>
							<td class="2-td2-center"><input type='text' name='jzhkce' id='4'
								value='' size='13' tabindex='2'
								style='text-align:right' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)'></td>
						</tr>
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center"><a style="cursor:hand" id="previous"
						onclick="gotoPreviousTable()"><img name="xpage" value="��һ�ű�"
						alt="��д��һ�ű�"
						onMouseOver="MM_swapImage('previoustable','','<%=static_contextpath%>images/shangyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/shangyiye1.jpg"
						id="previoustable"> </a>&nbsp;&nbsp; <a style="cursor:hand"
						id="next" onclick="gotoNextTable()"><img name="spage" value="��һ�ű�"
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
						onClick="doChecked();return false;" accesskey="d" value="����У��"
						alt="���ڹ�ϵУ��"
						onMouseOver="MM_swapImage('jiaoyan','','../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../images/b-bdjyd1.jpg"
						id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doDelete();return false;" accesskey="x" value="ȫ��ɾ��"
						alt="ɾ�������������ݣ��Ҳ��ɻָ�"
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
