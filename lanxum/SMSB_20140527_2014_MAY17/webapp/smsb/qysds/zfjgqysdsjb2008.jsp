<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsjb2008.web.ZfjgqysdsjbForm"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<html>
<head>
<title>��ҵ����˰������˰��֧���������</title>
<!--modify by yugw  -->
<html:base/>
<!-- the end -->
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
			ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm)request.getAttribute("zfjgForm");
			
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
				for(int i=0; i<size; i++)
				{
					HashMap map = (HashMap) zfjgForm.getQysdsjbList().get(i);
					String hc = (String) map.get("hc");
					String value =(String)map.get("value");
		%>
					obj = eval("document.getElementById('<%=hc%>_1')");
					obj.value = '<%=value%>';
		<% 
				}
			}
		%>
		//����ȡ�����������¼���ҳ���֧������̯˰��
		//compute_row_17();
		setFoucs();
	}	
	
	<%/*��Ӧ���������Ļس���ѯ*/%>
	function jsjdmQuery(){
		if(event.keyCode==13) event.keyCode = 9;
	}
	
	<%/*����������������ʱ�Ľ���仯*/%>
	function setFoucs(){
		//alert(document.forms[0].nsrsbh.value);
		if(document.forms[0].nsrsbh.value == null || document.forms[0].nsrsbh.value == ""){
			window.event.returnValue=false;
			document.forms[0].jsjdm.focus();
		}else{
			document.getElementById("1_1").focus();
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
		for(var i=10; i<18; i++)
		{
			var NewTd;
			if(i == 17)
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
				case 10:
					NewTd.innerHTML = "<td><input type='text' name='fzjgnsrsbh' id=" + id + " size='19' maxlength='18' onblur='checkNsrsbh(\"" + end + "\")'></td>";
					//alert(NewTd.innerHTML);
					break;
				case 11:
					NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjgmc' id=\"" + id + "\" size='19'></td>";
					break;
				case 12:
					NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjgsrze' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur='formate(this)' onchange='compute_Row_15(\"" + end + "\")'></td>";
					break;
				case 13:
					NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjggzze' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur='formate(this)' onchange='compute_Row_15(\"" + end + "\")'></td>";
					break;
				case 14:
					NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjgzcze' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur='formate(this)' onchange='compute_Row_15(\"" + end + "\")'></td>";
					break;
				case 15:
					NewTd.innerHTML = "<td nowrap class='2-td2-left' align=left><input type='text' name='fzjghj' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' readonly='true' class='text-gray'></td>";
					break;
				case 16:
					NewTd.innerHTML = "<td align=left nowrap class='2-td2-left'><input type='text' name='fzjgfpbl' id=\"" + id + "\" style='text-align:right' value='0' size='5' onblur='checkPercent(this)'></td>";
					break;
				case 17:
					NewTd.innerHTML = "<td align=left nowrap class='2-td2-center'><input type='text' name='fzjgfpse' id=\"" + id + "\" style='text-align:right' value='0.00' size='13' onblur=\"checkInput(this)\"></td>";
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
	function doQuery(){
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

	<%/*����*/%>
	function doSave()
	{
		if(document.forms[0].nsrsbh.value == "" || document.forms[0].nsrsbh.value == null){
			alert("������Ϣ����ȷ,����������!");
			return false;
		}
						
		if(!saveCheck()){
			//alert("save check is false");
			return;
		}
		
		if(!compareDate2Save(document.forms[0].skssjsrq))
			return;
		
		doSubmitForm('Save');		
	}

	<%/*���*/%>
	function doCheck()
	{
		doSubmitForm('Check');
	}

	<%/*���*/%>
	function doReset()
	{
	    if(confirm("ȷ���Ƿ������ǰҳ�����ݣ�"))
	    {
			//����ܻ�������
			for(var i=1; i<10; i++)
			{
				//��̯˰������
				if(i == 9)
				{
					continue;
				}	
				//�ų��������ܻ�����Ϣ
				if(i != 3 && i!= 4)
				{
					obj = eval("document.getElementById('" + i+"_1')");
					if(i < 5)
					{
						obj.value = "";
					}
					else if(i >= 5)
					{
						obj.value = "0.00";
					}
				}
			}
			
			// �����֧������ϸ��Ϣ
			var nsrsbhArrLength = document.forms[0].fzjgnsrsbh.length;
		   	for(var j=10; j < 18; j++)
			{
				for(var k=0; k<nsrsbhArrLength; k++)
				{
					obj = eval("document.getElementById('" + j + "." + (k+1) + "_1')");
					//��֧������˰����Ϣ
					if(j < 12)	
					{
						obj.value = "";
					}
					//���������Ϣ
					else if(j == 16)
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
		setFoucs();
	}
	
	<%/*ɾ��*/%>
	function doDelete()
	{
		if(document.forms[0].nsrmc.value==""){
				alert("������Ϣ����ȷ,ɾ��ʧ��,����������!");
				return false;
			}
		doSubmitForm('Delete');
	}
	
	<%/*�����ܻ����������غϼ���*/%>
	function compute_row_8()
	{		
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;			
		}
			
		//����5��6��7������������жϵ�8�������Ƿ�Ҫ�Ķ�
		var row_5=convertSpace2Zero(document.getElementById("5_1").value);
		var row_6=convertSpace2Zero(document.getElementById("6_1").value);
		var row_7=convertSpace2Zero(document.getElementById("7_1").value);
		
		
		//���㲢��ʽ����8�е�ֵ
		var obj = document.getElementById("8_1");
		var temp8 = parseFloat(row_5) + parseFloat(row_6) + parseFloat(row_7);
		//obj.value=parseFloat(temp8);
		obj.value = parseFloat(temp8).toFixed(2);
		formate(obj);
		/*
			����2008-3-21���Խ��Ҫ��,ȡ���ϼ�����0У��;ȡ���������������˰���Զ�����
		if(temp8 <= 0)
		{
			alert("�ܻ����ϼ����������0�����ʵ���޸ģ�");
			return;
		}
		
	
		//����������ܻ����ϼ��������֧���������������������
		compute_row_16_by_row_8();
		*/
	}

	<%/*����������ܻ����ϼ��������֧���������������������*/%>
	function compute_row_16_by_row_8()
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

	<%/*������ֻ������������غϼ������������������˰��*/%>
	function compute_Row_15(id)
	{
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;			
		}
		
		//alert("id = " + id);

		//����12(�����ܶ�)��12(�����ܶ�)��14(�ʲ��ܶ�)������������жϵ�15(�ϼ�)�������Ƿ�Ҫ�Ķ�
		var row_12 = convertSpace2Zero(document.getElementById("12" + id).value);
		var row_13 = convertSpace2Zero(document.getElementById("13" + id).value);
		var row_14 = convertSpace2Zero(document.getElementById("14" + id).value);
		
		
		//���㲢��ʽ����15�е�ֵ
		var obj = document.getElementById("15" + id);
		var temp15 = parseFloat(row_12) + parseFloat(row_13) + parseFloat(row_14);
		//obj.value=parseFloat(temp8);		
		obj.value = parseFloat(temp15).toFixed(2);
		formate(obj);
		/*
			����2008-3-21���Խ��Ҫ��,ȡ���ϼ�����0У��;ȡ���������������˰���Զ�����
		if(temp15 <= 0)
		{
			alert("����֧�����ϼ����������0�����ʵ���޸ģ�");
			return;
		}
		

		//�����Ӧ��16�������
		compute_row_16(id);
		*/
		//������17(����˰��)
		//compute_row_17(id);
	}
	
	<%/*������ֻ������������*/%>
	function compute_row_16(id)
	{
		var obj_16 = document.getElementById("16" + id);
		var obj_17 = document.getElementById("17" + id);
		var temp;
		//�ж�����������Ƿ����Ҫ��
		if(!isNum(getCellObject() , null, null, null, null, 2)){
			return false;			
		}
		
		//�����ܻ����ϼ�������������������˰��
		var row_8 = convertSpace2Zero(document.getElementById("8_1").value);
		//alert(parseFloat(row_8));
		if(parseFloat(row_8) == 0)
		{
			//�ܽṹ�ϼƽ��Ϊ0,���Ӧ��֧�����ķ������Ϊ0
			obj_16.value = 0;
			//formate(obj_16);

			obj_17.value = 0;
			formate(obj_17);
		}
		else
		{
			//��ȡ��ǰ��֧�����ϼ���
			var row_15 = convertSpace2Zero(document.getElementById("15" + id).value);
			temp = parseFloat((Math.abs(parseFloat(row_15)) / Math.abs(parseFloat(row_8))).toFixed(4));
			obj_16.value = parseFloat(temp);
			//alert("obj_16.value = " + obj_16.value);
			formate2Parcent(obj_16, obj_16.value);
			
			//�ܻ�����̯˰��
			var row_9 = convertSpace2Zero(document.getElementById("9_1").value);
			//alert("row_9 = " + row_9 + "\nobj_17 = " + obj_17.value);
			obj_17.value = parseFloat(parseFloat(row_9) * temp).toFixed(2);
			formate(obj_17);
		}
	}

	<%/*������ֻ���������˰��*/%>
	function compute_row_17()
	{
		//�ܻ�����̯˰��
		var row_9 = convertSpace2Zero(document.getElementById("9_1").value);
		var fzjg = document.forms[0].fzjgnsrsbh;
		for(var i=0; i<fzjg.length; i++)
		{
			if(fzjg[i].value != null && fzjg[i].vlaue != "")
			{
				//��֧�����������
				var row_16 = document.forms[0].fzjgfpbl[i];
				var fpbl = formateParcent2Num(row_16);
				//��֧��������˰��
				var row_17 = document.forms[0].fzjgfpse[i];
				row_17.value = parseFloat(parseFloat(row_9) * fpbl).toFixed(2);
				formate(row_17);
			}
		}
	}
	
	
	//����걨�·ݲ�������ҵ����˰��������4��7��10����ʾ������Ա
	function checkZQ(sbrq,ksrq,jzrq,lx){
		if(!isDate(sbrq,"v")) return;
		getStartEndDate1(sbrq,ksrq,jzrq,lx);
		var inputDate = sbrq.value;
		mon = inputDate.substring(4,6);
		if(mon!='01' && mon!='04' && mon!='07' && mon!='10'){
			alert('ע�⣺'+inputDate+'���������ڡ�');
		}
	}
	
	/**
	* Notes: ��ȡ��ǰ���ڵ���һ��/��/������ֹ���ڡ�
	* Version: 0.9.00
	* Author: Guoxh
	* Parames: flag 1,Last Year;2,Last Month;Ĭ�ϣ��������
	*/
	function getStartEndDate1(oInput1,oInput2,oInput3,flag){
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

	<%/*�жϱȽϷ��������Ч��*/%>
	function compareFpblyxq(obj){
		
		var strDate1 = document.forms[0].fpblyxqq.value;		
		var strYear1 = strDate1.substr(0,4);		
		var strMonth1 = strDate1.substr(4,2);
 	 	var strDay1 = strDate1.substr(6,2);
 	 	
 	 	var strDate2 = document.forms[0].fpblyxqz.value;
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
	  	
	  	//if(objDate2>objDate3){
	  		//alert("���������Ч��ĩ���ܴ��ڵ�ǰ����");
	  		//window.event.returnValue=false;
			//document.forms[0].fpblyxqz.focus();
			//document.forms[0].fpblyxqz.select();
			//return false;
	  	//}
  	  	
  		//if(objDate1>=objDate2){
			//if(obj == document.forms[0].fpblyxqz){
				//alert("���������Ч��ĩ����С�ڻ���ڷ��������Ч�ڳ�");
			//}else{
				//alert("���������Ч�ڳ����ܴ��ڻ���ڷ��������Ч��ĩ");	
			//}
			//window.event.returnValue=false;
			//obj.focus();
			//obj.select();
			//return false;
		//}			
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
		//������������Ч���Ƿ�����д
		var fpblyxqq = document.getElementById("1_1").value;
		var fpblyxqz = document.getElementById("2_1").value;
		//alert("fpblyxqq = " + fpblyxqq + "\nfpblyxqz = " + fpblyxqz);
		if(fpblyxqq == null || fpblyxqq == "" || fpblyxqz == null || fpblyxqz == "")
		{
			alert("���������Ч��Ϊ���������д�󱣴棡");
			if(fpblyxqq == null || fpblyxqq == "") 
			{
				document.getElementById("1_1").focus();
				return false;
			}
			if(fpblyxqz == null || fpblyxqz == "") 
			{
				document.getElementById("2_1").focus();
				return false;
			}			
		}
		
		//У���ܻ����ϼ����Ƿ���ڷ�֧�����ϼ���֮��
		/* ����2008-3-21���Խ��,ȡ���ܻ����ϼƷǸ�����֧��������ϼƷǸ����ܻ����ϼƵ��ڸ����֧�����ϼ���֮������
		var zjghj = document.getElementById("8_1").value;
		if(zjghj <= 0)
		{
			alert("�ܻ����ϼ����������0�����ʵ���޸ģ�");
			return false;
		}*/
		//alert("zjghj = " + zjghj);
		var fzjghj = 0;
		var fzjg = document.forms[0].fzjghj;
		var fzjgnsrsbh = document.forms[0].fzjgnsrsbh;
		var fzjgmc = document.forms[0].fzjgmc;
		//alert("fzjg.length = " + fzjg.length);
		for(i=0; i<fzjg.length; i++)
		{
			//alert("i = " + i);
			//alert("parseFloat(fzjg[i].value) = " + parseFloat(fzjg[i].value) + "\nfzjgnsrsbh[i].value = " + fzjgnsrsbh[i].value + "\nfzjgnsrmc[i].value = " + fzjgnsrmc[i].value);
			if(fzjgnsrsbh[i].value != null && fzjgnsrsbh[i].value != "" && fzjgmc[i].value != null && fzjgmc[i].value != "")
			{
				//alert("11111111");
				/*if(fzjg[i].value <= 0)
				{
					alert("��" + (i + 1) + "�з�֧�����ϼ����������0�����ʵ���޸ģ�");
					return false;
				}
				else
				{
					fzjghj = parseFloat(fzjghj) + parseFloat(fzjg[i].value);
				}*/
			}
			else
			{
				//alert("22222222");
				if(parseFloat(fzjg[i].value) != 0)
				{
					alert("�뽫��" + (i + 1) + "�з�֧���������Ϣ���������󱣴棡");
					if(fzjgnsrsbh[i].value == null || fzjgnsrsbh[i].value == "")
					{
						fzjgnsrsbh[i].focus();
						return false;						
					}
					if(fzjgmc[i].value == null || fzjgmc[i].value == ""){
						fzjgmc[i].focus();
						return false;
					}					
				}
			}
			
		/*			//�жϵ�ǰ��Ҫ�ύ�ķ�֧��������˰����ƻ�ֵ���ܻ�����̯˰��*����������Ƿ���ȣ���������ʾ�޸�
			var cur_ftse = document.getElementById("9_1").value;
			//alert("cur_ftse = " + document.forms[0].fzjgfpbl[i].value);
			var cur_mxfpbl = formateParcent2Num(document.forms[0].fzjgfpbl[i]);
			//alert("cur_mxfpbl = " + cur_mxfpbl);
			var cur_mxftse = document.forms[0].fzjgfpse[i].value;
			//alert("cur_ftse = " + cur_ftse + "\ncur_mxfpbl = " + cur_mxfpbl + "\ncur_mxftse = " + cur_mxftse);
			if(parseFloat(cur_ftse * cur_mxfpbl).toFixed(2) != cur_mxftse)
			{
				alert("��" + (i + 1) + "�з�֧��������˰����Ϸ���˰���Ӧ���������޸ĺ󱣴棡\n��ǰ��̯˰��x������� = " + parseFloat(cur_ftse * cur_mxfpbl).toFixed(2) + "\n��ǰ��֧��������˰�� = " +��cur_mxftse);
				document.forms[0].fzjgfpse[i].focus();
				document.forms[0].fzjgfpse[i].select();
				return false;
			}
			*/
		}
		//alert("parseFloat(fzjghj) = " + parseFloat(fzjghj) + "\nparseFloat(zjghj) = " + parseFloat(zjghj));
		/*
		if((parseFloat(fzjghj) - parseFloat(zjghj)) != 0)
		{
			alert("����֧�����ϼ���֮�����ܻ����ϼ������ȣ����ʵ�޸ĺ��ٱ��棡");
			return false;
		}
		*/
		

		//У���֧�����������
		/*var fpbl = document.forms[0].fzjgfpbl;
		var fpblhj = 0;
		for(var j=0; j<fpbl.length; j++)
		{
			//alert("parseFloat(fpbl[j].value) = " + parseFloat(fpbl[j].value));
			fpblhj = parseFloat(fpblhj) + parseFloat(fpbl[j].value);
		}
		//alert("parseFloat(fpblhj) = " + parseFloat(fpblhj));
		if(parseFloat(zjghj) == 0 && parseFloat(fpblhj) != 0)
		{
			//�ܻ����ϼ���Ϊ0,�������������Ϊ0
			return false;
		}
		if(parseFloat(zjghj) != 0 && ((parseFloat(fpblhj) - 1) != 0))
		{
			//�ܻ����ϼ�����Ϊ0,��������������ϼ�Ϊ1
			alert("����֧�����������֮�Ͳ�����100%�����ʵ�޸ĺ��ٱ��棡");
			return false;			
		}

		//У���̯˰���ϵ
		var zjgftse = document.forms[0].ftse.value;
		var fpsehj = 0;
		var fpse = document.forms[0].fzjgfpse;
		for(var k=0; k<fpse.length; k++)
		{
			fpsehj = parseFloat(fpsehj) + parseFloat(fpse[k].value);
		}
		if((parseFloat(fpsehj) - parseFloat(zjgftse)) != 0)
		{
			alert("����֧��������˰��ϼ����֧������̯˵��˰��ȣ����ʵ�޸ĺ��ٱ��棡");
		}
		*/
		
		if(!isNum(document.getElementById('5_1'), null, null, null, null, 2)){
				return false;			
		}
		if(!isNum(document.getElementById('6_1'), null, null, null, null, 2)){
				return false;			
		}
		if(!isNum(document.getElementById('7_1'), null, null, null, null, 2)){
				return false;			
		}
		var nsrsbhArrLength = document.forms[0].fzjgnsrsbh.length;
		for(var j=10; j < 18; j++)
			{
				for(var k=0; k<nsrsbhArrLength; k++)
				{
					obj = eval("document.getElementById('" + j + "." + (k+1) + "_1')");
					//��֧������˰����Ϣ
					if(j==12 || j==13 || j==14 || j==17)	
					{
						if(!isNum(obj, null, null, null, null, 2)){
							return false;			
						}
					}

				}							
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

		//�ж�����������Ƿ����Ҫ��
		if(!isNum(obj, null, null, null, null, 2)){
			return false;			
		}

		formateParcent(obj);
	}

	//�ж���˰��ʶ����Ƿ������ֻ��ַ����
	function checkNsrsbh(id)
	{
		//alert("id = " + id);
		var obj = document.getElementById("10" + id);
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
<%@include file="../include/header.jsp"%>
<br>

<html:form action="/webapp/smsb/qysds/zfjgqysdsjbAction.do">
	<html:hidden property="actionType" />
	<!--html:hidden property="nsrsbh" />
	<html:hidden property="nsrmc" /-->
	<!--html:hidden property="zgswjgzzjgmc" />
	<html:hidden property="lxdh" />
	<html:hidden property="jydz" />
	<html:hidden property="sknd" />
	<html:hidden property="qh" />
	<html:hidden property="bbqlx" />
	<html:hidden property="qxdm" />
	<html:hidden property="swjsjdm" />
	<html:hidden property="qyzslx" />
	<html:hidden property="cyl" />
	<html:hidden property="dezsse" /-->
	<html:hidden property="swjgzzjgdm" />
	<html:hidden property="qxdm"/>

	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="jsjdmQuery()">
		<tr>			
			<td class="1-td1" colspan="7">�л����񹲺͹���ҵ����˰������˰��֧���������</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7"><br>
			<table cellspacing="0" class="table-99">
				<tr class="black9">
					<td align="center" nowrap>
					<div align="left">�걨���� <html:text property="sbrq" size="11"
						maxlength="8" readonly='true' style='text-align:right' onchange="checkZQ(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,3)" /></div>
					</td>
					<!--html:text property="sbrq" size="11" maxlength="8" style="display:none"/-->
					<td colspan="2" align="left" nowrap>˰�������ڼ䣺
						<html:text property="skssksrq" size="11" maxlength="8" onchange="isDate(this,false);compareDate(this)" /> 
						�� 
						<html:text property="skssjsrq" size="11" maxlength="8" onchange="isDate(this,false);compareDate(this)" />
					</td>
				</tr>
				<tr class="black9"> 
				  <td width="30%" align="left" nowrap> 
					<div align="left">�ܻ�����˰��������룺&nbsp; <html:text property="jsjdm" size="12" maxlength="8" onchange="doQuery()" /></div></td>
				  <td width="53%" align="left" nowrap>&nbsp;
					���������Ч�ڣ�<html:text property="fpblyxqq" styleId="1_1" size="15" maxlength="8" onchange="isDate(this,false);compareFpblyxq(this)"/>
					�� 
					<html:text property="fpblyxqz" styleId="2_1" size="15" maxlength="8" onchange="isDate(this,false);compareFpblyxq(this)"/></td>
				  <td width="18%" align="right" nowrap>��λ��Ԫ�������Ƿ֣�</td>
				</tr>
			  </table>
			
      <table id="table_30" class="table-99" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="8%" rowspan="3" nowrap class="2-td2-t-left">�ܻ������</td>
          <td width="15%" rowspan="2" align=left nowrap class="2-td2-t-left">��˰��ʶ���</td>
          <td width="15%" rowspan="2" nowrap class="2-td2-t-left">�ܻ�������</td>
          <td colspan="4" align=left nowrap class="2-td2-t-left">��������</td>
          <td colspan="2" rowspan="2" align=left nowrap class="2-td2-t-center">��֧������̯������˰��</td>
        </tr>
        <TR> 
          <td width="11%" nowrap class="2-td2-left">�����ܶ�</td>
          <td width="11%" nowrap class="2-td2-left">�����ܶ�</td>
          <td width="11%" nowrap class="2-td2-left">�ʲ��ܶ�</td>
          <td width="11%" nowrap class="2-td2-left">�ϼ�</td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<html:text property="nsrsbh" styleId="3_1" size="19" maxlength="18" readonly='true' styleClass="text-gray"/> 
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<html:text property="nsrmc" styleId="4_1" size="19" readonly='true' styleClass="text-gray"/> 
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<html:text property="srze" styleId="5_1" style='text-align:right' value='0.00' size="12" onblur="formate(this)" onchange="compute_row_8()" /> 
          </td>
          <td nowrap class="2-td2-left" align=left>
			<html:text property="gzze" styleId="6_1" style='text-align:right' value='0.00' size="12" onblur="formate(this)" onchange="compute_row_8()" /> 
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<html:text property="zcze" styleId="7_1" style='text-align:right' value='0.00' size="12" onblur="formate(this)" onchange="compute_row_8()" /> 
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<html:text property="hj" styleId="8_1" style='text-align:right' value='0.00' size="12" readonly='true' styleClass="text-gray" /> 
		  </td>
          <td colspan=2 align=left nowrap class="2-td2-center">
			<html:text property="ftse" styleId="9_1" style='text-align:right' value='0.00' size="20" readonly='true' styleClass="text-gray" /> 
		  </td>
        </tr>
        <tr> 
          <td rowspan="19" id="fzjgqk" valign="middle" nowrap class="2-td2-left">��֧�������</td>
          <td rowspan="2" align=left nowrap class="2-td2-left">��˰��ʶ���</td>
          <td rowspan="2" nowrap class="2-td2-left">��֧��������</td>
          <td colspan="4" align=left nowrap class="2-td2-left">��������</td>
          <td width="6%" rowspan="2" align=left nowrap class="2-td2-left">�������</td>
          <td width="12%" rowspan="2" align=left nowrap class="2-td2-center">����˰��</td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left">�����ܶ�</td>
          <td nowrap class="2-td2-left">�����ܶ�</td>
          <td nowrap class="2-td2-left">�ʲ��ܶ�</td>
          <td nowrap class="2-td2-left">�ϼ�</td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='10.1_1' size='19' maxlength='18' onblur="checkNsrsbh('.1_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='11.1_1' name='fzjgmc' size='19'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='12.1_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.1_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='13.1_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.1_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='14.1_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.1_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjghj' id='15.1_1' style='text-align:right' value='0.00' size='13' readonly='true' class="text-gray">
		  </td>
          <td align=left nowrap class="2-td2-left">
			<input type='text' name='fzjgfpbl' id='16.1_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)">
		  </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='17.1_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='10.2_1' size='19' maxlength='18' onblur="checkNsrsbh('.2_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='11.2_1' name='fzjgmc' size='19'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='12.2_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.2_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='13.2_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.2_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='14.2_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.2_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjghj' id='15.2_1' style='text-align:right' value='0.00' size='13' readonly='true' class="text-gray">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='16.2_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='17.2_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='10.3_1' size='19' maxlength='18' onblur="checkNsrsbh('.3_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='11.3_1' name='fzjgmc' size='19'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='12.3_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.3_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='13.3_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.3_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='14.3_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.3_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjghj' id='15.3_1' style='text-align:right' value='0.00' size='13' readonly='true' class="text-gray">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='16.3_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='17.3_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='10.4_1' size='19' maxlength='18' onblur="checkNsrsbh('.4_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='11.4_1' name='fzjgmc' size='19'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='12.4_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.4_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='13.4_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.4_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='14.4_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.4_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjghj' id='15.4_1' style='text-align:right' value='0.00' size='13' readonly='true' class="text-gray">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
			<input type='text' name='fzjgfpbl' id='16.4_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)">
		  </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='17.4_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='10.5_1' size='19' maxlength='18' onblur="checkNsrsbh('.5_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='11.5_1' name='fzjgmc' size='19'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='12.5_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.5_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='13.5_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.5_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='14.5_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.5_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjghj' id='15.5_1' style='text-align:right' value='0.00' size='13' readonly='true' class="text-gray">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='16.5_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='17.5_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
		  </td>
        </tr>
        <tr> 
          <td align=left nowrap class="2-td2-left"> 
            <input type='text' name='fzjgnsrsbh' id='10.6_1' size='19' maxlength='18' onblur="checkNsrsbh('.6_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='11.6_1' name='fzjgmc' size='19'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='12.6_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.6_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='13.6_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.6_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='14.6_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.6_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjghj' id='15.6_1' style='text-align:right' value='0.00' size='13' readonly='true' class="text-gray">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='16.6_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='17.6_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='10.7_1' size='19' maxlength='18' onblur="checkNsrsbh('.7_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='11.7_1' name='fzjgmc' size='19'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='12.7_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.7_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='13.7_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.7_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='14.7_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.7_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjghj' id='15.7_1' style='text-align:right' value='0.00' size='13' readonly='true' class="text-gray">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='16.7_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='17.7_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='10.8_1' size='19' maxlength='18' onblur="checkNsrsbh('.8_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='11.8_1' name='fzjgmc' size='19'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='12.8_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.8_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='13.8_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.8_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='14.8_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.8_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left> 
			<input type='text' name='fzjghj' id='15.8_1' style='text-align:right' value='0.00' size='13' readonly='true' class="text-gray"></td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='16.8_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='17.8_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='10.9_1' size='19' maxlength='18' onblur="checkNsrsbh('.9_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='11.9_1' name='fzjgmc' size='19'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='12.9_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.9_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='13.9_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.9_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='14.9_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.9_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjghj' id='15.9_1' style='text-align:right' value='0.00' size='13' readonly='true' class="text-gray">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='16.9_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='17.9_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='10.10_1' size='19' maxlength='18' onblur="checkNsrsbh('.10_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='11.10_1' name='fzjgmc' size='19'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='12.10_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.10_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='13.10_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.10_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='14.10_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.10_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjghj' id='15.10_1' style='text-align:right' value='0.00' size='13' readonly='true' class="text-gray">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='16.10_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='17.10_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='10.11_1' size='19' maxlength='18' onblur="checkNsrsbh('.11_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='11.11_1' name='fzjgmc' size='19'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='12.11_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.11_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='13.11_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.11_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='14.11_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.11_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjghj' id='15.11_1' style='text-align:right' value='0.00' size='13' readonly='true' class="text-gray">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='16.11_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='17.11_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='10.12_1' size='19' maxlength='18' onblur="checkNsrsbh('.12_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='11.12_1' name='fzjgmc' size='19'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='12.12_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.12_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='13.12_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.12_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='14.12_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.12_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjghj' id='15.12_1' style='text-align:right' value='0.00' size='13' readonly='true' class="text-gray">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='16.12_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='17.12_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
		  </td>
        </tr>
        <tr> 
          <td height="19" align=left nowrap class="2-td2-left"> 
            <input type='text' name='fzjgnsrsbh' id='10.13_1' size='19' maxlength='18' onblur="checkNsrsbh('.13_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='11.13_1' name='fzjgmc' size='19'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='12.13_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.13_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='13.13_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.13_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='14.13_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.13_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjghj' id='15.13_1' style='text-align:right' value='0.00' size='13' readonly='true' class="text-gray">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='16.13_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='17.13_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='10.14_1' size='19' maxlength='18' onblur="checkNsrsbh('.14_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='11.14_1' name='fzjgmc' size='19'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='12.14_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.14_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='13.14_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.14_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='14.14_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.14_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjghj' id='15.14_1' style='text-align:right' value='0.00' size='13' readonly='true' class="text-gray">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='16.14_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='17.14_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
		  </td>
        </tr>
        <tr> 
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgnsrsbh' id='10.15_1' size='19' maxlength='18' onblur="checkNsrsbh('.15_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' id='11.15_1' name='fzjgmc' size='19'>
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgsrze' id='12.15_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.15_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjggzze' id='13.15_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.15_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjgzcze' id='14.15_1' style='text-align:right' value='0.00' size='13' onblur="formate(this)" onchange="compute_Row_15('.15_1')">
		  </td>
          <td nowrap class="2-td2-left" align=left>
			<input type='text' name='fzjghj' id='15.15_1' style='text-align:right' value='0.00' size='13' readonly='true' class="text-gray">
		  </td>
          <td align=left nowrap class="2-td2-left"> 
		  	<input type='text' name='fzjgfpbl' id='16.15_1' style='text-align:right' value='0' size='5' onblur="checkPercent(this)"> 
          </td>
          <td align=left nowrap class="2-td2-center">
			<input type='text' name='fzjgfpse' id='17.15_1' style='text-align:right' value='0.00' size='13' onblur="checkInput(this)">
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
			  <input type="image" accesskey="q"
						onClick="doQuery();return false;"
						onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)"
						onMouseOut="MM_swapImgRestore()" value="��ѯ"
						src="<%=static_contextpath%>images/cx-q1.jpg " width="79"
						height="22" id="chaxun" style="cursor:hand">
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

	<%@ include file="../include/footer.jsp"%>
</html:form>
</body>
</html>
