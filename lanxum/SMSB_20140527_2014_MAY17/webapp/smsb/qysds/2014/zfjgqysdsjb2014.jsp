<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web.ZfjgqysdsjbForm"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<html>
<head>
<title>��ҵ����˰������˰��֧���������</title>
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
<script language=JavaScript type="text/JavaScript"
	src="../../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/function.js"></script>
</head>

<script language="JavaScript">
<%
	//����ɹ���ʾ��Ϣ
	String saveMessage = (String)request.getAttribute(CodeConstant.SMSB_SAVE_SUCCESS);	
	if(saveMessage==null)
	{
		saveMessage="";
	}
	//��ʾ����ɹ���ʾ
	if(saveMessage != null && !saveMessage.trim().equals(""))
	{
%>
		alert("<%=saveMessage%>");
<%
	}
%>

	<%/*��ʼ��*/%>	
	function doInit()
	{
		//ҳ���ʼ��ϸ����
		var maxSize = document.forms[0].fzjgnsrsbh.length;
		<%
			/*��֧�������ݻ���*/
			ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm)request.getAttribute("zfjg2014Form");
			if((zfjgForm!=null) && (zfjgForm.getQysdsjbList().size()==0) && (zfjgForm.getJglx().equals("2")))
			{
		%>	
			obj = eval("document.getElementById('12.1_1')");
			obj.value = '<%=zfjgForm.getFzjgnsrsbh()%>';

			obj = eval("document.getElementById('13.1_1')");
			obj.value = '<%=zfjgForm.getFzjgnsrmc()%>';	
		
			obj = eval("document.getElementById('17.1_1')");
			obj.value = '<%=zfjgForm.getFzjgfpbl()%>%';
			
			obj = eval("document.getElementById('18.1_1')");
			obj.value = '<%=zfjgForm.getFzjgfpse()%>';
		
			obj = eval("document.getElementById('10_1')");
			obj.value = '<%=zfjgForm.getFzjgfpbl()%>%';	
			obj = eval("document.getElementById('11_1')");
			obj.value = '<%=zfjgForm.getFzjgfpse()%>';	
		<%
			}
			
			if (zfjgForm!=null && zfjgForm.getQysdsjbList().size()>0)
			{
				//��ϸ�����������
				int maxIndex = zfjgForm.getMaxIndex();
				System.out.println("maxIndex = " + maxIndex);
		%>
				//�����ϸ���������������ҳ��Ĭ����ϸ����,�򲹳�������
				if(maxSize < <%=maxIndex%>)
				{
					for(var i = 0; i<(<%=maxIndex%> - maxSize); i++)
					{
						doAddRow();
					}
				}
		<%
				//�������
				int size = zfjgForm.getQysdsjbList().size();
				System.out.println("size = " + size);
				//���size ��ֵΪ8��15����˵����ѯ��������Ϊϵͳ�Զ��������ݣ���Ҫ���������ʶ�ʲ�����г�ʼ��
				if(zfjgForm.getJglx().equals("1")){
					if(size==8){
						for(int i=7; i<10; i++){
		%>
							obj = eval("document.getElementById('<%=i%>_1')");
							obj.value = '0.00';
		<%					
						}
					}
				}
				if(zfjgForm.getJglx().equals("2")){
					if(size==15){
						for(int i=7; i<10; i++){
		%>
						obj = eval("document.getElementById('<%=i%>_1')");
						obj.value = '0.00';
		<%				
						}
					}
				}
				for(int i=0; i<size; i++)
				{
					HashMap map = (HashMap) zfjgForm.getQysdsjbList().get(i);
					String hc = (String) map.get("hc");
					String value =(String)map.get("value");
					System.out.println(hc+"------"+value);
					
		%>
					obj = eval("document.getElementById('<%=hc%>_1')");
					obj.value = '<%=value%>';		
		<% 
				}							
			}	
			//���������͸�ʽ�����Ŀֻ������ɫ����	
			if((zfjgForm!=null)&& (zfjgForm.getJglx().equals("2")))
			{
		%>	
			document.getElementById('12.1_1').readOnly="true";
			document.getElementById('12.1_1').style.backgroundColor="#EEEEEE";
			document.getElementById('13.1_1').readOnly="true";
			document.getElementById('13.1_1').style.backgroundColor="#EEEEEE";
			document.getElementById('17.1_1').readOnly="true";
			document.getElementById('17.1_1').style.backgroundColor="#EEEEEE";
			document.getElementById('18.1_1').readOnly="true";
			document.getElementById('18.1_1').style.backgroundColor="#EEEEEE";												
		<%	
		}else{
		%>
			document.getElementById('1_1').readOnly="true";
			document.getElementById('1_1').style.backgroundColor="#EEEEEE";
			document.getElementById('2_1').readOnly="true";
			document.getElementById('2_1').style.backgroundColor="#EEEEEE";
			document.getElementById('3_1').readOnly="true";
			document.getElementById('3_1').style.backgroundColor="#EEEEEE";
			document.getElementById('4_1').readOnly="true";
			document.getElementById('4_1').style.backgroundColor="#EEEEEE";
			document.getElementById('5_1').readOnly="true";
			document.getElementById('5_1').style.backgroundColor="#EEEEEE";				
		<%	
			}
		%>


				
		
		//����ȡ�����������¼���ҳ���֧������̯˰��
		//compute_row_17();
		setFoucs();



			//addded by zhangj 2014.11.28
		<%

		if("1".equals(zfjgForm.getSAVE_FLAG())){
		%>
			if(document.forms[0].jglx.value=='1'){//�ܻ�����ת����֧��������ת
				//����ɹ�����ʾת�ֻܷ����걨��
				if(confirm("��ȷ�ϸ���ҵ�������Ƿ������̶ܹ��ʲ������۾ɣ��۳������ߣ�")){						
					doSubmitForm('JumpGdzcjszjyjqk');
				}			
			}
		<%
		}	
		%>	

	}	
	
	<%/*��Ӧ���������Ļس���ѯ*/%>
	function jsjdmQuery(){
		if(event.keyCode==13) event.keyCode = 9;
	}
	
	<%/*����������������ʱ�Ľ���仯*/%>
	function setFoucs(){
		//alert(document.forms[0].jglx.value);
		if(document.forms[0].jglx.value  == "2"){
			document.getElementById("1_1").focus();
		}else{
			document.getElementById("12.1_1").focus();
		}	
	}	
	
	<%/*������*/%>
	function doAddRow()
	{
		//��ȡ���з�֧������ϸ��Ϣ����
		var mxnum = document.forms[0].fzjgnsrsbh.length;
		//alert("mxnum = " + mxnum);

		//��ȡ��Ҫ����еı��
		var table = document.getElementById("table_30");

		//Ȼ�󴴽���(TR����)
		var NewTr = document.createElement("tr");
		//���ñ����
		for(var i=12; i<19; i++)
		{
			var NewTd;
			if(i == 18)
			{
				NewTd = document.createElement("<td align=left nowrap class='2-td2-center'>");
			}
			else
			{
				NewTd = document.createElement("<td nowrap class='2-td2-left' align=left>");
			}
			var end = "." + (mxnum + 1) + "_1";
			var id = i + end;
			//alert("id = " + id);
			switch(i)
			{
				case 12:
					NewTd.innerHTML = "<td><input type='text' name='fzjgnsrsbh' id=" + id + " size='19' maxlength='18' onblur='checkNsrsbh(\"" + end + "\")'></td>";
					//alert(NewTd.innerHTML);
					break;
				case 13:
					NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjgmc' id=\"" + id + "\" size='40'></td>";
					break;
				case 14:
					NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjgsrze' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur='formate(this)' onchange='compute_Row_7(\"" + end + "\")'></td>";
					break;
				case 15:
					NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjggzze' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur='formate(this)' onchange='compute_Row_8(\"" + end + "\")'></td>";
					break;
				case 16:
					NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjgzcze' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur='formate(this)' onchange='compute_Row_9(\"" + end + "\")'></td>";
					break;
				case 17:
					NewTd.innerHTML = "<td align=left nowrap class='2-td2-left'><input type='text' name='fzjgfpbl' id=\"" + id + "\" style='text-align:right' value='0' size='13' onblur='checkPercent(this)' onchange='compute_Row_10(\"" + end + "\")'></td>";
					break;
				case 18:
					NewTd.innerHTML = "<td align=left nowrap class='2-td2-center'><input type='text' name='fzjgfpse' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur='checkInput(this)' onchange='compute_Row_11(\"" + end + "\")'></td>";
					break;				
			}
			//alert(NewTd.innerHTML);
			//������������䵥Ԫ��
			NewTr.appendChild(NewTd);
		}		
		//�����
		var LastTr = table.rows[table.rows.length - 1];

		LastTr.parentNode.appendChild(NewTr);


		//���ÿ��е�Ԫ��Ŀ�����+1
		var fzjgqk = document.getElementById("fzjgqk");
		//alert("fzjgqk rowspan = " + fzjgqk.rowSpan);
		//alert("fzjgqk rowspanaaa = " + document.all.fzjgqk.rowSpan);
		fzjgqk.rowSpan += 1;
		//alert("fzjgqk rowspan = " + fzjgqk.rowSpan);
	}
	
	<%/*��ѯ*/%>
<%/*	function doQuery(){
		var jsjdm;
		jsjdm = document.forms[0].jsjdm.value;
		if(jsjdm==""){
		    alert("��������벻�����ǿգ�");
		    return false;
	  	}else{
		    doSubmitForm("Query");    
	    	return false;
	  	}
	}
*/%>
	<%/*����*/%>
	function doSave()
	{

		if(document.forms[0].zjgmc.value == "" || document.forms[0].zjgmc.value == null){
			alert("������Ϣ����ȷ,�������ܻ�������!");
			return false;
		}
		
		if(document.forms[0].nsrsbh.value == "" || document.forms[0].nsrsbh.value == null){
			alert("������Ϣ����ȷ,��������˰��ʶ���!");
			return false;
		}

		if(document.forms[0].ynsdse.value == "" || document.forms[0].ynsdse.value == null){
			alert("������Ϣ����ȷ,������Ӧ������˰��!");
			return false;
		}
		
		if(document.forms[0].zjgftse.value == "" || document.forms[0].zjgftse.value == null){
			alert("������Ϣ����ȷ,�������ܻ�����̯����˰��!");
			return false;
		}

		if(document.forms[0].zjgfpse.value == "" || document.forms[0].zjgfpse.value == null){
			alert("������Ϣ����ȷ,�������ܻ����������з�������˰��!");
			return false;
		}
		<%/*-modified by huohb,2014-06-18-*/%>
		<%/*-У��Ӫҵ���룬ְ��н�꣬�ʲ��ܶ����Ϊ����-*/%>
		//Ӫҵ����
		var fzjgsrze=document.getElementsByName("fzjgsrze");
		//Ӫҵ����
		var fzjggzze=document.getElementsByName("fzjggzze");
		//Ӫҵ����
		var fzjgzcze=document.getElementsByName("fzjgzcze");
		
		for(var i = 0;i<fzjgsrze.length;i++){
			var srze = fzjgsrze[i].value;
			var gzze = fzjggzze[i].value;
			var zcze = fzjgzcze[i].value;
			if(srze<0){
				alert("Ӫҵ���벻��������С��0����");
				return false;
			}
			if(gzze<0){
				alert("ְ��н�겻��������С��0����");
				return false;
			}
			if(zcze<0){
				alert("�ʲ��ܶ��������С��0����");
				return false;
			}
		}		
		if(!saveCheck()){
			return;
		}
		if(!saveCheckNew()){
			return;
		}
		if(!compareDate2Save(document.forms[0].skssjsrq))
		{
			return;
		}

		doSubmitForm('Save');		
	}
	function saveCheckNew()
	{	
		var tempFpblHj=document.getElementById("10_1").value;
		var fzjgfpblHj=tempFpblHj.substring(0, (tempFpblHj.length-1));
		
		var fzjgftse=document.getElementById("6_1").value;
		var fzjgfpseHj=document.getElementById("11_1").value;

		
		
	 	if(document.forms[0].jglx.value=='1')
	    {
	    	//�ܻ���
			if(!(parseFloat(fzjgfpblHj).toFixed(2)-parseFloat("100.00").toFixed(2)==0)){
				alert("��֧������������ϼ�Ӧ����100%��");
				return false;
			}

			if(Math.abs((parseFloat(fzjgftse).toFixed(2) - parseFloat(fzjgfpseHj).toFixed(2))).toFixed(2) > 0.01)
			{
				alert("��֧������̯������˰�������ڷ���˰��ϼƣ�");
				return false;
			}
			
	    }else if(document.forms[0].jglx.value=='2'){
	    	//��֧����
	    	if((parseFloat(fzjgfpblHj).toFixed(2) < 0) || ((parseFloat(fzjgfpblHj).toFixed(2)-parseFloat("100").toFixed(2)) > 0)){
				alert("��֧������������ϼ�ӦС�ڵ���100%�Ҵ��ڵ���0��");
				return false;
			}
				
			if((parseFloat(fzjgfpseHj).toFixed(2)-parseFloat(fzjgftse).toFixed(2)) >0){
				alert("��֧��������˰��ϼ�ӦС�ڵ��ڷ�֧������̯������˰��");
				return false; 
			}
	    }
		
		
		
		return true;
	}

	<%/*���*/%>
	function doReset()
	{
	    if(confirm("ȷ���Ƿ������ǰҳ�����ݣ�"))
	    {
	    
	    if(document.forms[0].jglx.value=='1')
	    {
			//����ܻ�������
			for(var i=1; i<12; i++)
			{
				//��̯˰������
				if((i == 1)||(i == 2)||(i == 3)||(i == 4)||(i == 5)||(i == 6))
				{
					continue;
				}	else
				{
					obj = eval("document.getElementById('" + i+"_1')");
					if(i == 10)
					{
						obj.value = "0";
					}
					else
					{
						obj.value = "0.00";
					}
					}
			}
			
			// �����֧������ϸ��Ϣ
			var nsrsbhArrLength = document.forms[0].fzjgnsrsbh.length;
		   	for(var j=12; j < 19; j++)
			{
				for(var k=0; k<nsrsbhArrLength; k++)
				{
					obj = eval("document.getElementById('" + j + "." + (k+1) + "_1')");
					//��֧������˰����Ϣ
					if(j < 14)	
					{
						obj.value = "";
					}
					//���������Ϣ
					else if(j == 17)
					{
						obj.value = "0";
					}
					else
					{
						obj.value = "0.00";
					}
				}							
		   }		   	
	    }
	    
	    if(document.forms[0].jglx.value=='2')
	    {
			//����ܻ�������
			for(var i=1; i<12; i++)
			{
				//��̯˰������
				if(i == 6)
				{
					continue;
				}	else
				{
					obj = eval("document.getElementById('" + i+"_1')");
					if(i < 6)
					{
						obj.value = "";
					}
					else if(i == 10)
					{
						obj.value = document.getElementById('17.1_1').value;
					}
					else if(i == 11)
					{
						obj.value = document.getElementById('18.1_1').value;
					}else{
						obj.value ="0.00";
					}
				}
			}
			
			// �����֧������ϸ��Ϣ
			var nsrsbhArrLength = document.forms[0].fzjgnsrsbh.length;
		   	for(var j=12; j < 19; j++)
			{
				for(var k=0; k<nsrsbhArrLength; k++)
				{
					obj = eval("document.getElementById('" + j + "." + (k+1) + "_1')");
					if(k==0){
					//��֧������һ����˰����Ϣ
					if((j == 12)||(j == 13)||(j == 17)||(j == 18))	
					{
						continue;
					}else
					{
						obj.value = "0.00";
					}					
					}else{
					//��֧������������˰����Ϣ
					if(j < 14)	
					{
						obj.value = "";
					}
					//���������Ϣ
					else if(j == 17)
					{
						obj.value = "0";
					}
					else
					{
						obj.value = "0.00";
					}					
					}
				}							
		   }		   	
	    }
	    
	    } 
		setFoucs();
	}

	
	<%/*ɾ��*/%>
	function doDelete()
	{
//		if(document.forms[0].nsrmc.value==""){
//				alert("������Ϣ����ȷ,ɾ��ʧ��,����������!");
//				return false;
//			}
		doSubmitForm('Delete');
		
//		doSubmitForm('Jump');	
	}


	<%/*����������ܻ����ϼ��������֧���������������������*/%>
<%/*	function compute_row_16_by_row_8()
	{
		var nsrsbhArr = document.forms[0].fzjgnsrsbh;
		//alert("nsrsbhArr.length = " + nsrsbhArr.length);
		for(i=0; i<nsrsbhArr.length; i++)
		{
			var nsrsbh = nsrsbhArr[i].value;
			//alert("nsrsbh_" + (i + 1) + " = " + nsrsbh);
			if(nsrsbh != null && nsrsbh != "")
			{
				//ƴ��ǰ�еĺ�׺
				var id = "." + (i + 1) + "_1";
				//alert("aaa id = " + id);
				compute_row_16(id);
			}
			else
			{
				continue;
			}
		}
	}
*/%>


	function isNumber(id)
	{		
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			document.getElementById(id).value ='0.00';
			return false;			
		}
	}

	<%/*�����֧���������ϼ�*/%>
	function compute_Row_7(id)
	{
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			document.getElementById('14'+id).value ='0.00';
			return false;			
		}
		
		//���㲢��ʽ����7�е�ֵ
		var nsrsbhArr = document.forms[0].fzjgnsrsbh;
		var hj=0.00;
		for(i=1; i<=nsrsbhArr.length; i++)
		{
			 var je=convertSpace2Zero(document.getElementById("14." + i +"_1").value);
			 hj=parseFloat(je) + parseFloat(hj);
		}
		
		var obj = document.getElementById("7_1");
		if(parseFloat(hj)>0){
				obj.value = parseFloat(hj).toFixed(2);				
		}
		else{
				obj.value = parseFloat("0").toFixed(2);
		}			
		formate(obj);
		
	}

	<%/*�����֧�������ʶ�ϼ�*/%>
	function compute_Row_8(id)
	{
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			document.getElementById('15'+id).value =0.00;
			return false;			
		}
		
		//���㲢��ʽ����8�е�ֵ
		var nsrsbhArr = document.forms[0].fzjgnsrsbh;
		var hj=0.00;
		for(i=1; i<=nsrsbhArr.length; i++)
		{
			 var je=convertSpace2Zero(document.getElementById("15." + i +"_1").value);
			 hj=parseFloat(je) + parseFloat(hj);
		}
		
		var obj = document.getElementById("8_1");
		if(parseFloat(hj)>0){
				obj.value = parseFloat(hj).toFixed(2);				
		}
		else{
				obj.value = parseFloat("0").toFixed(2);
		}			
		formate(obj);
		
	}

	<%/*�����֧�����ʲ���ϼ�*/%>
	function compute_Row_9(id)
	{
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			document.getElementById('16'+id).value =0.00;
			return false;			
		}
		
		//���㲢��ʽ����9�е�ֵ
		var nsrsbhArr = document.forms[0].fzjgnsrsbh;
		var hj=0.00;
		for(i=1; i<=nsrsbhArr.length; i++)
		{
			 var je=convertSpace2Zero(document.getElementById("16." + i +"_1").value);
			 hj=parseFloat(je) + parseFloat(hj);
		}
		
		var obj = document.getElementById("9_1");
		if(parseFloat(hj)>0){
				obj.value = parseFloat(hj).toFixed(2);				
		}
		else{
				obj.value = parseFloat("0").toFixed(2);
		}			
		formate(obj);
		
	}
	
	<%/*�����֧������������ϼ�*/%>
	function compute_Row_10(id)
	{
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			document.getElementById('17'+id).value =0.00;
			return false;			
		}
		
		//���㲢��ʽ����10�е�ֵ
		var nsrsbhArr = document.forms[0].fzjgnsrsbh;
		var hj=0.00;
		for(i=1; i<=nsrsbhArr.length; i++)
		{
			 var je=convertSpace2Zero(document.getElementById("17." + i +"_1").value);
			 hj=parseFloat(je) + parseFloat(hj);
		}

		var obj = document.getElementById("10_1");
		if(parseFloat(hj)>0){
				//obj.value = hj;
				obj.value = parseFloat(hj).toFixed(2);				
		}
		else{
				//obj.value = 0.00;
				obj.value = parseFloat("0").toFixed(2);
		}	
		formateParcent(obj);		
		
	}
	
	<%/*�����֧��������˰��ϼ�*/%>
	function compute_Row_11(id)
	{
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			document.getElementById('18'+id).value =0.00;
			return false;			
		}
		
		//���㲢��ʽ����11�е�ֵ
		var nsrsbhArr = document.forms[0].fzjgnsrsbh;
		var hj=0.00;
		for(i=1; i<=nsrsbhArr.length; i++)
		{
			 var je=convertSpace2Zero(document.getElementById("18." + i +"_1").value);
			 hj=parseFloat(je) + parseFloat(hj);
		}
		
		var obj = document.getElementById("11_1");
//		if(parseFloat(hj)>0){
				obj.value = parseFloat(hj).toFixed(2);				
//		}
//		else{
//				obj.value = parseFloat("0").toFixed(2);
//		}			
		formate(obj);
		
	}
	
	/**
	* Notes: ��ȡ��ǰ���ڵ���һ��/��/������ֹ���ڡ�
	* Version: 0.9.00
	* Author: Guoxh
	* Parames: flag 1,Last Year;2,Last Month;Ĭ�ϣ��������
	*/
<%/*	function getStartEndDate1(oInput1,oInput2,oInput3,flag){
		var date_start,date_end;
		var year,mon,days;
		var strMon;
	
		var inputDate = oInput1.value;
	
		//�Ƿ�Ϸ�����
		if(isDate(oInput1,"v")){
			year = inputDate.substring(0,4);
			mon = inputDate.substring(4,6);
			days = inputDate.substring(6,8);
	
			if(flag == 1){//Last Year
				date_start = (year-1)+"0101";
				date_end = (year-1)+"1231";
			}else if(flag == 2){//Last Month
				var date2 = new Date(year,mon-1,-1);
				days = date2.getDate()+1;
				year = date2.getYear();
				mon = date2.getMonth()+1;
	
				date_start = year+""+formatMon(mon)+"01";
				date_end = year+""+formatMon(mon)+days;
				//date_start = year+""+formatMon(mon-1)+"01";
				//date_end = year+""+formatMon(mon-1)+days;
			}else{
				//mon = parseInt(mon);
				switch(mon){
					case "01":
					case "02":
					case "03":
						date_start = (year-1)+"0101";
						date_end = (year-1)+"1231";
						break;
					case "04":
					case "05":
					case "06":
						date_start = year+"0101";
						date_end = year+"0331";
						break;
					case "07":
					case "08":
					case "09":
						date_start = year+"0101";
						date_end = year+"0630";
						break;
					case "10":
					case "11":
					case "12":
						date_start = year+"0101";
						date_end = year+"0930";
						break;
				}
			}
	
			oInput2.value = date_start;
			oInput3.value = date_end;
		}
	}
*/%>	
	<%/*�жϱȽ�˰����������*/%>
	function compareDate(obj){
		
		var strDate1 = document.forms[0].skssksrq.value;		
		var strYear1 = strDate1.substr(0,4);		
		var strMonth1 = strDate1.substr(4,2);
 	 	var strDay1 = strDate1.substr(6,2);
 	 	
 	 	var strDate2 = document.forms[0].skssjsrq.value;
 	 	var strYear2 = strDate2.substr(0,4);		
		var strMonth2 = strDate2.substr(4,2);
 	 	var strDay2 = strDate2.substr(6,2);
 	 	
 	 	var sksbrq = document.forms[0].sbrq.value;
 	 	var strYear3 = sksbrq.substr(0,4);		
		var strMonth3 = sksbrq.substr(4,2);
 	 	var strDay3 = sksbrq.substr(6,2); 
 	 	
	  	var objDate1 = new Date(strMonth1+'-'+strDay1+'-'+strYear1);
	  	var objDate2 = new Date(strMonth2+'-'+strDay2+'-'+strYear2);
	  	var objDate3 = new Date(strMonth3+'-'+strDay3+'-'+strYear3);
	  	
	  	if(objDate2>objDate3){
	  		alert("˰�����ʱ�䲻�ܴ����걨����");
	  		window.event.returnValue=false;
			document.forms[0].skssjsrq.focus();
			document.forms[0].skssjsrq.select();
			return false;
	  	}
  	  	
  		if(objDate1>=objDate2){
			if(obj == document.forms[0].skssjsrq){
				alert("˰�����ʱ�䲻��С�ڻ����˰�ʼʱ��");
			}else{
				alert("˰�ʼʱ�䲻�ܴ��ڻ����˰�����ʱ��");	
			}
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;
		}
		
		if(strYear1!=strYear2){
			alert("˰�ʼʱ����˰�����ʱ�����ݲ�ͬ��˰��ܿ��꣡");
			return false;				
		}
			
	}

	
	//�жϱȽ�˰����������
	function compareDate2Save(obj){
		
		var strDate1 = document.forms[0].skssksrq.value;		
		var strYear1 = strDate1.substr(0,4);		
		var strMonth1 = strDate1.substr(4,2);
 	 	var strDay1 = strDate1.substr(6,2);
	 	 	
 	 	var strDate2 = document.forms[0].skssjsrq.value;
 	 	var strYear2 = strDate2.substr(0,4);		
		var strMonth2 = strDate2.substr(4,2);
 	 	var strDay2 = strDate2.substr(6,2);
	 	 	
 	 	var sksbrq = document.forms[0].sbrq.value;
 	 	var strYear3 = sksbrq.substr(0,4);		
		var strMonth3 = sksbrq.substr(4,2);
 	 	var strDay3 = sksbrq.substr(6,2); 
	 	 	
		var objDate1 = new Date(strMonth1+'-'+strDay1+'-'+strYear1);
		var objDate2 = new Date(strMonth2+'-'+strDay2+'-'+strYear2);
		var objDate3 = new Date(strMonth3+'-'+strDay3+'-'+strYear3);
	  	
		if(objDate2>objDate3){
			alert("˰�����ʱ�䲻�ܴ��ڵ�ǰ����");
			window.event.returnValue=false;
				document.forms[0].skssjsrq.focus();
				document.forms[0].skssjsrq.select();
				return false;
		}
  	  	
  		if(objDate1>=objDate2){
			if(obj == document.forms[0].skssjsrq){
				alert("˰�����ʱ�䲻��С�ڻ����˰�ʼʱ��");
			}else{
				alert("˰�ʼʱ�䲻�ܴ��ڻ����˰�����ʱ��");	
			}
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;
		}
		
		if(strYear1!=strYear2){
			alert("˰�ʼʱ����˰�����ʱ�����ݲ�ͬ��˰��ܿ��꣡");
			return false;				
		}
		
		return true;
			
	}
	
	<%/*���ո�ת��Ϊ�㣬����������������*/%>
	function convertSpace2Zero(inputValue){
		return inputValue==""?"0":inputValue;
	}
	
	<%/*����ʱ��ҳ�����ݽ���У��*/%>
	function saveCheck()
	{	
		
		//����ϼ��н���Ƿ���ȷ����У���������Ƿ���д
		var nsrsbhArr = document.forms[0].fzjgnsrsbh;
		var srehj=0.00;
		var gzehj=0.00;
		var zcehj=0.00;
		var fpblhj=0.00;
		var fpsehj=0.00;
		for(i=1; i<=nsrsbhArr.length; i++)
		{
			 var sre=convertSpace2Zero(document.getElementById("14." + i +"_1").value);
			 var gze=convertSpace2Zero(document.getElementById("15." + i +"_1").value);
			 var zce=convertSpace2Zero(document.getElementById("16." + i +"_1").value);
			 var fpbl=convertSpace2Zero(document.getElementById("17." + i +"_1").value);
			 var fpse=convertSpace2Zero(document.getElementById("18." + i +"_1").value);
			 srehj=parseFloat(sre) + parseFloat(srehj);
			 gzehj=parseFloat(gze) + parseFloat(gzehj);
			 zcehj=parseFloat(zce) + parseFloat(zcehj);
			 fpblhj=parseFloat(fpbl) + parseFloat(fpblhj);
			 fpsehj=parseFloat(fpse) + parseFloat(fpsehj);
			 
				if(!((sre==0)&&(gze==0)&&(zce==0)&&(fpbl==0)&&(fpse==0)))
				{
					if((document.getElementById("12." + i +"_1").value) == null || (document.getElementById("12." + i +"_1").value) == "")
					{
						alert("�뽫��" + i + "�з�֧���������Ϣ���������󱣴棡");
						document.getElementById("12." + i +"_1").focus();
						return false;						
					}
					if((document.getElementById("13." + i +"_1").value) == null || (document.getElementById("13." + i +"_1").value) == "")
					{
						alert("�뽫��" + i + "�з�֧���������Ϣ���������󱣴棡");					
						document.getElementById("13." + i +"_1").focus();
						return false;
					}	
				}

		}
		
		for(i=2; i<=nsrsbhArr.length; i++)
		{
			 var nsrsbh=document.getElementById("12." + i +"_1").value;
			 var fzjgmc=document.getElementById("13." + i +"_1").value;
			  
			  
				if((nsrsbh!="")&&(fzjgmc!=""))
				{
					if((document.getElementById("12." + (i-1) +"_1").value) == "" && (document.getElementById("13." + (i-1) +"_1").value) == "")
					{
						alert("�뽫��" + (i-1) + "�з�֧���������Ϣ����������");
						document.getElementById("12." + (i-1) +"_1").focus();
						return false;						
					}
				}

		}

		//if((parseFloat(document.getElementById("7_1").value) - parseFloat(srehj)) != 0)		
		if((parseFloat(document.getElementById("7_1").value).toFixed(2) - parseFloat(srehj).toFixed(2)) != 0)
		{
			alert("����֧���������֮����ϼ������ȣ����ʵ�޸ĺ��ٱ��棡");
			return false;
		}
		//if((parseFloat(document.getElementById("8_1").value) - parseFloat(gzehj)) != 0)
		if((parseFloat(document.getElementById("8_1").value).toFixed(2) - parseFloat(gzehj).toFixed(2)) != 0)
		{
			alert("����֧�������ʶ�֮����ϼ������ȣ����ʵ�޸ĺ��ٱ��棡");
			return false;
		}
		//if((parseFloat(document.getElementById("9_1").value) - parseFloat(zcehj)) != 0)
		if((parseFloat(document.getElementById("9_1").value).toFixed(2) - parseFloat(zcehj).toFixed(2)) != 0)
		{
			alert("����֧�����ʲ���֮����ϼ������ȣ����ʵ�޸ĺ��ٱ��棡");
			return false;
		}
		//if((parseFloat(document.getElementById("10_1").value) - parseFloat(fpblhj)) != 0)
		if((parseFloat(document.getElementById("10_1").value).toFixed(2) - parseFloat(fpblhj).toFixed(2)) != 0)
		{
			alert("����֧�����������֮����ϼ������ȣ����ʵ�޸ĺ��ٱ��棡");
			return false;
		}
		//if((parseFloat(document.getElementById("11_1").value) - parseFloat(fpsehj)) != 0)
		
		//������0.01��Χ��
		if(-0.01<=(parseFloat(document.getElementById("11_1").value).toFixed(2) - parseFloat(fpsehj).toFixed(2)) <= 0.01)
		{
			alert("����֧��������˰��֮����ϼ������ȣ����ʵ�޸ĺ��ٱ��棡");
			return false;
		}

				
		return true;	
	}

	function checkInput(obj)
	{
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;			
		}
		//��ʽ������
		formate(obj);
	}
	
	<%//У��������¼����%>
	function checkPercent(obj)
	{
		var temp = obj.value;
		var end = temp.substring(temp.length-1);		
		//alert("end = " + end);
		//�������Ϊ%��β����ȥ��%�ź���
		if(end == "%")
		{
			temp = temp.substring(0, (temp.length-1));
		}
		obj.value = temp;
		formate(obj);
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(obj, null, null, null, null, 2)){
			return false;			
		}
		//������ֵΪ��ʱ����ʼ��Ϊ0
		formateParcent(obj);
		if(((obj.value).length==1)&&(obj.value == "%"))
		{
			obj.value = "0";
		}
	}

	//�ж���˰��ʶ����Ƿ������ֻ��ַ����
	function checkNsrsbh(id)
	{
		//alert("id = " + id);
		var obj = document.getElementById("12" + id);
		var nsrsbh = obj.value;
		/*if(nsrsbh.length == 0)
		{
			alert("��������˰��ʶ���!");
			obj.focus();
			return;
		}*/
	
	    for(var loc=0;loc<nsrsbh.length;loc++)
		{
			//alert("loc = " + loc);
			//alert("nsrsbh.chatAt(loc) = " + nsrsbh.charAt(loc));
			if(!((('a'<=nsrsbh.charAt(loc)) && (nsrsbh.charAt(loc)<='z')) || (('A'<=nsrsbh.charAt(loc)) && (nsrsbh.charAt(loc)<='Z')) || (('0'<=nsrsbh.charAt(loc)) && (nsrsbh.charAt(loc)<='9'))))
			{
				alert("��˰��ʶ���ֻ��Ϊ���ֻ��ַ�������������!");
				obj.focus();
				obj.select();
				return;
			}
		}
	}

	//��ת��������ҳ��
	function jumpToCZZS(){
		//document.forms[0].ActionType="Jump";
		//alert("�˴���Ҫ��дת����ҵ����˰������˰��֧������������ܵ�����");
		if(confirm("ȷ�������ز��������걨ҳ�棬�����ᱣ�浱ǰ����ݣ��Ƿ������"))
		{
			doSubmitForm('Jump');	
		}
	}

	/**
 * ��ʽ����������ΪС�������λ
 * 
 * @param �ı���
 */
function formate(obj){
	//if(!isNumber(obj.value)){
	//	alert("��ʹ�����ָ�ʽ���룡");
	//	obj.focus();
	//	return false;
	//}
	if(obj.value==""||obj.value==null){
		obj.value="0.00";
		return false;	
	}else{
		var temp = trim(obj.value+"");
		if(temp.indexOf(".")!=-1){
			var len=temp.indexOf(".")+1;
			if(temp.length-len==1)
			temp = temp+"0";
			var zs=trim(temp.substring(0,temp.indexOf(".")));
			if(zs==""){
				temp="0"+temp;
			}
		}else{
			temp=temp+".00";
		}
		obj.value=temp;	
		formateNum(obj);
	}		
}
/**
 * ��ʽ������������0��ͷ
 *
 * @param �ı���
 */
 function formateNum(obj){
 	var tempNum=obj.value;
 	var num=trim(tempNum.substring(0,tempNum.indexOf(".")));
 	if(num.length>1){
 		num=num.substring(0,num.length-1);
 		var i;
 		for(i=0;i<num.length;i++){
 			var itemp=num.substring(i,i+1);
 			if(itemp!="0"){
 				break;
 			}
 		}
 		tempNum=tempNum.substring(i,tempNum.length);
 		obj.value=tempNum;
 	}
 
 }	
</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="doInit()">
<%@include file="../../include/header.jsp"%>
<br>

<html:form action="/webapp/smsb/qysds/zfjgqysdsjb2014Action.do">
	<html:hidden property="actionType" />
	<html:hidden property="nsrmc"/>
	<html:hidden property="jglx"/>
	<html:hidden property="swjgzzjgdm" />
	<html:hidden property="qxdm"/>

	<table width="96%" align="center" cellspacing="0" class="table-99"
			onkeydown="jsjdmQuery()">
		<tr>			
			<td class="1-td1" colspan="7">�л����񹲺͹���ҵ����˰������˰��֧������ҵ����˰�����2014</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7"><br>
			<table cellspacing="0" class="table-99">
				<tr class="black9">
					<td align="center" nowrap>
					<div align="left">�걨���� <html:text property="sbrq" size="11"
						maxlength="8" readonly='true' style='text-align:right'/></div>
					</td>
					<!--html:text property="sbrq" size="11" maxlength="8" style="display:none"/-->
					<td colspan="2" align="left" nowrap>˰�������ڼ䣺
						<html:text property="skssksrq" size="11" maxlength="8" readonly='true' onchange="isDate(this,false);compareDate(this)" styleClass="text-gray"/> 
						�� 
						<html:text property="skssjsrq" size="11" maxlength="8" readonly='true' onchange="isDate(this,false);compareDate(this)" styleClass="text-gray"/>
					</td>
				</tr>
					<tr class="black9"> 
				  <td width="30%" align="left" nowrap> 
					<div align="left">��˰��������룺&nbsp; <html:text property="jsjdm" size="12" readonly='true' maxlength="8" styleClass="text-gray"/></div></td>
				  <td width="53%" align="left" nowrap>&nbsp;
					</td>
				  <td width="18%" align="right" nowrap>&nbsp;</td>
				</tr>			
				
				
				<tr class="black9"> 
				  <td width="30%" align="left" nowrap> 
					<div align="left">�ܻ������ƣ�&nbsp; <html:text property="zjgmc" styleId="1_1" size="32" /></div></td>
				  <td width="53%" align="left" nowrap>&nbsp;
					</td>
				  <td width="18%" align="right" nowrap>��λ�������Ԫ�������Ƿ֣�</td>
				</tr>
			  </table>
			
      <table id="table_30" class="table-99" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td colspan="2" width="15%"  nowrap class="2-td2-t-left">�ܻ�����˰��ʶ���</td>
          <td width="25%"  align=left nowrap class="2-td2-t-left">Ӧ������˰��</td>
          <td colspan="2" width="25%"  nowrap class="2-td2-t-left">�ܻ�����̯����˰��</td>
          <td width="15%" align=left nowrap class="2-td2-t-left">�ܻ����������з�������˰��</td>
          <td colspan="2"  width="20%" align=left nowrap class="2-td2-t-center">��֧������̯����˰��</td>
        </tr>
        <tr> 
          <td colspan="2" nowrap class="2-td2-left" align=left>
			<html:text property="nsrsbh" styleId="2_1" size="19" maxlength="18"/>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<html:text property="ynsdse" styleId="3_1" style='text-align:right' size="19" onchange="isNumber('3_1')"/> 
		  </td>
          <td colspan="2" nowrap class="2-td2-left" align=left>
			<html:text property="zjgftse" styleId="4_1" style='text-align:right' size="19" onchange="isNumber('4_1')"/>  
          </td>
          <td nowrap class="2-td2-left" align=left>
			<html:text property="zjgfpse" style='text-align:right' styleId="5_1" size="19" onchange="isNumber('5_1')"/>   
		  </td>
          <td colspan="2"  align=left nowrap class="2-td2-center">
			<html:text property="fzjgftse" style='text-align:right' styleId="6_1" size="19" readonly='true' styleClass="text-gray"/>   
        </tr>
        <tr> 
          <td rowspan="19" id="fzjgqk" valign="middle" nowrap class="2-td2-left">��֧�������</td>
          <td rowspan="2" align=left nowrap class="2-td2-left">��֧������˰��ʶ���</td>
          <td rowspan="2" nowrap class="2-td2-left">��֧��������</td>
          <td colspan="3" align=left nowrap class="2-td2-left">��������</td>
          <td width="10%" rowspan="2" align=left nowrap class="2-td2-left">�������</td>
          <td width="12%" rowspan="2" align=left nowrap class="2-td2-center">����˰��</td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left">Ӫҵ����</td>
          <td nowrap class="2-td2-left">ְ��н��</td>
          <td nowrap class="2-td2-left">�ʲ��ܶ�</td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='12.1_1' size='19' maxlength='18' onblur="checkNsrsbh('.1_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='13.1_1' name='fzjgmc' size='40'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='14.1_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_7('.1_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='15.1_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_8('.1_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='16.1_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_9('.1_1')">
		  </td>
          <td align=left nowrap class="2-td2-left">
			<input type='text' name='fzjgfpbl' id='17.1_1' style='text-align:right' value='0' size='13' onblur="checkPercent(this)" onchange="compute_Row_10('.1_1')">
		  </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='18.1_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" onchange="compute_Row_11('.1_1')">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='12.2_1' size='19' maxlength='18' onblur="checkNsrsbh('.2_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='13.2_1' name='fzjgmc' size='40'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='14.2_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_7('.2_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='15.2_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_8('.2_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='16.2_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_9('.2_1')">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='17.2_1' style='text-align:right' value='0' size='13' onblur="checkPercent(this)" onchange="compute_Row_10('.2_1')"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='18.2_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" onchange="compute_Row_11('.2_1')">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='12.3_1' size='19' maxlength='18' onblur="checkNsrsbh('.3_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='13.3_1' name='fzjgmc' size='40'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='14.3_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_7('.3_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='15.3_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_8('.3_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='16.3_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_9('.3_1')">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='17.3_1' style='text-align:right' value='0' size='13' onblur="checkPercent(this)"  onchange="compute_Row_10('.3_1')"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='18.3_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" onchange="compute_Row_11('.3_1')">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='12.4_1' size='19' maxlength='18' onblur="checkNsrsbh('.4_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='13.4_1' name='fzjgmc' size='40'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='14.4_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_7('.4_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='15.4_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_8('.4_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='16.4_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_9('.4_1')">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
			<input type='text' name='fzjgfpbl' id='17.4_1' style='text-align:right' value='0' size='13' onblur="checkPercent(this)" onchange="compute_Row_10('.4_1')">
		  </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='18.4_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" onchange="compute_Row_11('.4_1')">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='12.5_1' size='19' maxlength='18' onblur="checkNsrsbh('.5_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='13.5_1' name='fzjgmc' size='40'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='14.5_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_7('.5_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='15.5_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_8('.5_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='16.5_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_9('.5_1')">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='17.5_1' style='text-align:right' value='0' size='13' onblur="checkPercent(this)" onchange="compute_Row_10('.5_1')"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='18.5_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" onchange="compute_Row_11('.5_1')">
		  </td>
        </tr>
        <tr> 
          <td align=left nowrap class="2-td2-left"> 
            <input type='text' name='fzjgnsrsbh' id='12.6_1' size='19' maxlength='18' onblur="checkNsrsbh('.6_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='13.6_1' name='fzjgmc' size='40'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='14.6_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_7('.6_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='15.6_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_8('.6_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='16.6_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_9('.6_1')">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='17.6_1' style='text-align:right' value='0' size='13' onblur="checkPercent(this)" onchange="compute_Row_10('.6_1')"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='18.6_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" onchange="compute_Row_11('.6_1')">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='12.7_1' size='19' maxlength='18' onblur="checkNsrsbh('.7_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='13.7_1' name='fzjgmc' size='40'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='14.7_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_7('.7_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='15.7_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_8('.7_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='16.7_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_9('.7_1')">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='17.7_1' style='text-align:right' value='0' size='13' onblur="checkPercent(this)" onchange="compute_Row_10('.7_1')"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='18.7_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" onchange="compute_Row_11('.7_1')">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='12.8_1' size='19' maxlength='18' onblur="checkNsrsbh('.8_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='13.8_1' name='fzjgmc' size='40'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='14.8_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_7('.8_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='15.8_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_8('.8_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='16.8_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_9('.8_1')">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='17.8_1' style='text-align:right' value='0' size='13' onblur="checkPercent(this)" onchange="compute_Row_10('.8_1')"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='18.8_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" onchange="compute_Row_11('.8_1')">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='12.9_1' size='19' maxlength='18' onblur="checkNsrsbh('.9_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='13.9_1' name='fzjgmc' size='40'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='14.9_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_7('.9_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='15.9_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_8('.9_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='16.9_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_9('.9_1')">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='17.9_1' style='text-align:right' value='0' size='13' onblur="checkPercent(this)" onchange="compute_Row_10('.9_1')"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='18.9_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" onchange="compute_Row_11('.9_1')">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='12.10_1' size='19' maxlength='18' onblur="checkNsrsbh('.10_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='13.10_1' name='fzjgmc' size='40'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='14.10_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_7('.10_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='15.10_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_8('.10_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='16.10_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_9('.10_1')">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='17.10_1' style='text-align:right' value='0' size='13' onblur="checkPercent(this)" onchange="compute_Row_10('.10_1')"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='18.10_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" onchange="compute_Row_11('.10_1')">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='12.11_1' size='19' maxlength='18' onblur="checkNsrsbh('.11_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='13.11_1' name='fzjgmc' size='40'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='14.11_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_7('.11_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='15.11_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_8('.11_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='16.11_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_9('.11_1')">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='17.11_1' style='text-align:right' value='0' size='13' onblur="checkPercent(this)" onchange="compute_Row_10('.11_1')"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='18.11_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" onchange="compute_Row_11('.11_1')">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='12.12_1' size='19' maxlength='18' onblur="checkNsrsbh('.12_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='13.12_1' name='fzjgmc' size='40'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='14.12_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_7('.12_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='15.12_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_8('.12_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='16.12_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_9('.12_1')">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='17.12_1' style='text-align:right' value='0' size='13' onblur="checkPercent(this)" onchange="compute_Row_10('.12_1')"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='18.12_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" onchange="compute_Row_11('.12_1')">
		  </td>
        </tr>
        <tr> 
          <td height="19" align=left nowrap class="2-td2-left"> 
            <input type='text' name='fzjgnsrsbh' id='12.13_1' size='19' maxlength='18' onblur="checkNsrsbh('.13_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='13.13_1' name='fzjgmc' size='40'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='14.13_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_7('.13_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='15.13_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_8('.13_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='16.13_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_9('.13_1')">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='17.13_1' style='text-align:right' value='0' size='13' onblur="checkPercent(this)" onchange="compute_Row_10('.13_1')"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='18.13_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" onchange="compute_Row_11('.13_1')">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='12.14_1' size='19' maxlength='18' onblur="checkNsrsbh('.14_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='13.14_1' name='fzjgmc' size='40'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='14.14_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_7('.14_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='15.14_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_8('.14_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='16.14_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_9('.14_1')">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='17.14_1' style='text-align:right' value='0' size='13' onblur="checkPercent(this)" onchange="compute_Row_10('.14_1')"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='18.14_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" onchange="compute_Row_11('.14_1')">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='12.15_1' size='19' maxlength='18' onblur="checkNsrsbh('.15_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='13.15_1' name='fzjgmc' size='40'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='14.15_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_7('.15_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='15.15_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_8('.15_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='16.15_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_9('.15_1')">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='17.15_1' style='text-align:right' value='0' size='13' onblur="checkPercent(this)" onchange="compute_Row_10('.15_1')"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='18.15_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)" onchange="compute_Row_11('.15_1')">
		  </td>
        </tr>
        </table>
      <table  class="table-99" border="0" cellpadding="0" cellspacing="0">       
        <tr> 
          <td width="14%" nowrap class="2-td2-left" align=left>
			�ϼ�
		  </td>
          <td width="25%" nowrap class="2-td2-left" align=left>
			---
		  </td>
          <td width="13%" nowrap class="2-td2-left" align=left>
			<html:text property="srehj" styleId="7_1" style='text-align:right' size="13" readonly='true' styleClass="text-gray" /> 
		  </td>
          <td width="13%" nowrap class="2-td2-left" align=left>
			<html:text property="gzehj" styleId="8_1" style='text-align:right' size="13" readonly='true' styleClass="text-gray" /> 
		  </td>
          <td width="15%" nowrap class="2-td2-left" align=left>
			<html:text property="zcehj" styleId="9_1" style='text-align:right' size="13" readonly='true' styleClass="text-gray" /> 
		  </td>
          <td width="10%" align=left nowrap class="2-td2-left"> 
			<html:text property="fpblhj" styleId="10_1" style='text-align:right' size="13" readonly='true' styleClass="text-gray" /> 
          </td>
          <td width="12%" align=left nowrap class="2-td2-center">
			<html:text property="fpsehj" styleId="11_1" style='text-align:right' size="13" readonly='true' styleClass="text-gray" /> 
		  </td>
        </tr>               
      </table>
			<br>

			<TABLE class="table-99" align="center">
        <BR>
        <TR class="black9"> 
          <TD> <div align="center">
              <input type="image" accesskey="a"
						onClick="doAddRow();return false;"
						onMouseOver="MM_swapImage('zengjia','','<%=static_contextpath%>images/zj-a2.jpg ',1)"
						onMouseOut="MM_swapImgRestore()" alt="������"
						src="<%=static_contextpath%>images/zj-a1.jpg " width="79"
						height="22" id="zengjia" style="cursor:hand">
			  &nbsp;&nbsp;&nbsp;&nbsp;
              <input type="image" accesskey="u"
						style="cursor:hand" onClick="doReset();return false;"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" name="Image132"
						width="79" height="22" border="0" id='qingchu'>
              &nbsp;&nbsp;&nbsp;&nbsp; 
              <input type="image" accesskey="s"
						style="cursor:hand" onClick="doSave();return false;"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" name="Image13"
						width="79" height="22" border="0" id='baocun'>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <input type="image" accesskey="x"
						style="cursor:hand" onClick="doDelete();return false;"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" name="Image13"
						width="79" height="22" border="0" id='shanchu'>
              &nbsp;&nbsp;&nbsp;&nbsp;
			  <input type="image" accesskey="r"
						style="cursor:hand" onClick="jumpToCZZS();return false;"
						onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/fanhui1.jpg" name="fanhui"
						width="79" height="22" border="0" id='fanhui' alt="���ز��������걨ҳ��">
              &nbsp;&nbsp;&nbsp;&nbsp;
              <input type="image" accesskey="c"
						onClick="tuichu();return false;"
						onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1"
						src="<%=static_contextpath%>images/tc-c1.jpg" width="79"
						height="22" style="cursor:hand">
            </div>
            <BR> </TD>
        </TR>
      </TABLE>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>

	<%@ include file="../../include/footer.jsp"%>
</html:form>
</body>
</html>
