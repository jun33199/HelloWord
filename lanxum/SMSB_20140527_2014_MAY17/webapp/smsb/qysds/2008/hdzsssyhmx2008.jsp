<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.hdzsssyhmx.web.HdzsssyhmxForm"%>
<%@ page import="java.util.HashMap"%>

<html>
<head>
<title>˰���Ż���ϸ��</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/function.js"></script>
</head>

<script language="JavaScript">
		<%/*��ʼ��*/%>	
		function doInit()
		{
		
		//��ʼ���ı���onChange�¼�����
		initNumText("je",47);
		var row_45=document.getElementById("45_1");
		row_45.style.textAlign ="right";
		row_45.onkeyup = function(){formateSign();}
		row_45.onblur = function(){return isNum(getCellObject() , null, 9999999999999, null, 16, 0);}
		<%
		HdzsssyhmxForm nbForm=(HdzsssyhmxForm)request.getAttribute("hdzsssyhmxForm2008");
		String wlrdbs = nbForm.getWlrdbs();
			if (nbForm!=null && nbForm.getDataList().size()>0){
				for(int i=0;i<nbForm.getDataList().size();i++){
					HashMap map=(HashMap)nbForm.getDataList().get(i);
					int hc=Integer.parseInt((String)map.get("hc"));
					String je=(String)map.get("je");
					%>
					obj = eval("document.getElementById('<%=hc%>_1')");
					obj.value = '<%=je%>';
					<% 
				}
			}
			%>
      document.all("34_1").focus();
	    if(document.all("47_1").value == "01"){
	    	document.forms[0].hy47_1.checked = true;
	    	document.forms[0].hy47_2.checked = false;
	    }
	    if(document.all("47_1").value == "02"){
	    	document.forms[0].hy47_1.checked = false;
	    	document.forms[0].hy47_2.checked = true;
	    }
	    if(document.all("47_1").value != "01" && document.all("47_1").value != "02"){
	    	document.forms[0].hy47_1.checked = false;
	    	document.forms[0].hy47_2.checked = false;
	    }
	    
	    var obj= document.getElementById('33_1');
		  obj.readOnly=true;
		  obj.className='text-gray';
		  
	    if(getValues("zbh3")==0||getValues("zbh3")>300000){
	    	document.all("38_1").focus();
	      var obj= document.getElementById('34_1');
		  	obj.readOnly=true;
		  	obj.className='text-gray';
		  	obj.value="";
		  	compute_Row_33init();
		  	var obj= document.getElementById('45_1');
		  	obj.readOnly=true;
		  	obj.className='text-gray';
		  	obj.value="";
		  	var obj= document.getElementById('46_1');
		  	obj.readOnly=true;
		  	obj.className='text-gray';
		  	obj.value="";
		  	var obj= document.getElementById('47_1');
		  	obj.value="";
		  	document.forms[0].hy47_1.checked = false;
	    	document.forms[0].hy47_2.checked = false;
	    	document.forms[0].hy47_1.disabled = true;
	    	document.forms[0].hy47_2.disabled = true;
		  }
		}		
		
		<%/*����*/%>
		function doSave()
		{

		if(!doCheckmethod()){
				return;
			}
			doSubmitForm('Save');
		}
    
    function getValues(str){
		var strv = document.getElementById(str).value;
		if(strv=="")
		   return 0;
		else
			 return parseFloat(strv);  
	}
	
    function doCheck()
		{
			if(doCheckmethod()){
        alert("���ͨ����");
			}
		}
		
		<%/*���ڹ�ϵУ��*/%>
		function doCheckmethod()
		{

			var zbh3 = getValues("zbh3");
			var info = "";
			if(getValues('34_1')!=0&&getValues('34_1')!=Math.round(zbh3*0.05*100)/100){
				alert("��34��ֻ��Ϊ0�������3�е�5% �����3��=" + zbh3 + " ��3�С�5%=" + Math.round(zbh3*0.05*100)/100);
				return false;
			}
				
		//33��=34�У�35�У�36�У�37�У�38��
		var hj5 = getValues('34_1')+getValues('35_1')+getValues('36_1')+getValues('37_1')+getValues('38_1');
		if(getValues('33_1')!=Math.round(hj5*100)/100)
		{
			info = "�����Ϲ�ʽ��33��=34�У�35�У�36�У�37�У�38�У����������룡\n";
			alert(info);
			formate(document.getElementById('33_1'));
			return false;
		}
		//��47��Ϊ������ȣ����������������ֵΪ0���
		if(getValues('34_1')>0&&zbh3>0&&zbh3<=300000){
	  	if(getValues('47_1')==0)
	  	{
	  		info = "��47��Ϊ���������ѡ��������ҵ\n";
	  		alert(info);
	  		formate(document.getElementById('47_1'));
	  		return false;
	  	}
	  }

	  if(getValues('45_1')<0)
	  {
	  	info = "��45�в���Ϊ������\n";
	  	alert(info);
	  	//formate(document.getElementById('46_1'));
	  	document.all("45_1").focus();
	  	return false;
	  }

		if(getValues('34_1')>0&&zbh3>0&&zbh3<=300000){
	  	if(getValues('45_1')<=0||getValues('46_1')<=0)
	  	{
	  		info = "��45��46��Ϊ������ұ������0��\n";
	  		alert(info);
	  		//formate(document.getElementById('46_1'));
	  		document.all("45_1").focus();
	  		return false;
	  	}
	  }
		
		//�������34��>0�����������3��>0��<=300000ʱ
		if(getValues('34_1')>0&&zbh3>0&&zbh3<=300000){
			//��������47��=��ҵ��ҵ���򱾱��45��>0��<=100��ͬʱ�����46��>0��<=30000000
		if(getValues('47_1')==1){
			if(!(getValues('45_1')>0&&getValues('45_1')<=100))
			{
		  	info = "��ʽ ��34>0 ���� ��47 ������ҵΪ��ҵ��ҵʱ: ��45 >0��<=100 ��������ҵ��ҵ���� ��45=" + getValues('45_1') +  " ���޸�\n";
		  	alert(info);
		  	//formate(document.getElementById('46_1'));
		  	document.all("45_1").focus();
		  	return false;
		  }
		  if(!(getValues('46_1')>0&&getValues('46_1')<=30000000))
			{
		  	info = "��ʽ ��34>0 ���� ��47 ������ҵΪ��ҵ��ҵʱ: ��46 >0��<=30000000 �������ʲ��ܶ� ��46=" + getValues('46_1') +  " ���޸�\n";
		  	alert(info);
		  	formate(document.getElementById('46_1'));
		  	document.all("46_1").focus();
		  	return false;
		  }
		}
		if(getValues('47_1')==2){
		  if(!(getValues('45_1')>0&&getValues('45_1')<=80))
			{
		  	info = "��ʽ H34>0 ���� ��47 ������ҵΪ������ҵʱ:��45 >0��<=80 ��������ҵ��ҵ���� ��45=" + getValues('45_1') +  " ���޸�\n";
		  	alert(info);
		  	//formate(document.getElementById('46_1'));
		  	document.all("45_1").focus();
		  	return false;
		  }
		  if(!(getValues('46_1')>0&&getValues('46_1')<=10000000))
			{
		  	info = "��ʽ H34>0 ���� ��47 ������ҵΪ������ҵʱ: ��46 >0��<=10000000 �������ʲ��ܶ� ��46=" + getValues('46_1') +  " ���޸�\n";
		  	alert(info);
		  	formate(document.getElementById('46_1'));
		  	document.all("46_1").focus();
		  	return false;
		  }
		}
		}

		if(info!=""){
				return false;
			}
			else{
				return true;
			}
		}
		function compute_Row_33init(){
			var ybsds=Math.round((getValues('34_1')+getValues('35_1')+getValues('36_1')+getValues('37_1')+getValues('38_1'))*100)/100;
			document.getElementById('33_1').value=parseFloat(ybsds);
			formate(document.getElementById('33_1'));
		}
		function compute_Row_33(obj){
			if(!numberyz(obj))
			  return;
			checkNumInput(obj);
			var ybsds=Math.round((getValues('34_1')+getValues('35_1')+getValues('36_1')+getValues('37_1')+getValues('38_1'))*100)/100;
			document.getElementById('33_1').value=parseFloat(ybsds);
			formate(document.getElementById('33_1'));
		}
		
		//��ֵ��֤
		function numberyz(obj){
			var str=obj.value.replace(/\s+/gm,'');
			if(str.length!=obj.value.length){
				return false;
			}
			if(!isNumber(obj.value)){
	    	return false;
	    }
	    var temp = obj.value;
	    if(temp.indexOf(".")!=-1){
	      var len=temp.indexOf(".");
	      var cz = temp.length-len;
        if(cz<2||cz>3){
        	return false;
        }
      }
	    return true;
	  }
		
		<%/*���*/%>
		function doReset()
		{
			if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
			{
		   		for(var i=1; i < 48; i++){
					  obj = eval("document.getElementById('" + i+"_1')");
					  obj.value = "";
				  }
		   		document.forms[0].hy47_1.checked = false;
	 	      document.forms[0].hy47_2.checked = false;
			}
		}

      function checkNumInput(obj)

{

                   //�ж�����������Ƿ����Ҫ��

                   if(!isNum(getCellObject() , null, null, null, null, 2)){

                            return false;                         

                   }

                   //��ʽ������

                   formate(obj);

}

 

//��֤���ݵĺϷ���

function formate(obj){

 

         if(obj.value==""||obj.value==null){

                   obj.value="0.00";       

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
		
		<%/*��������*/%>
		function doReturn()
		{
			doSubmitForm('Return');
		}
		
		<%/*����ѡ�����õ�ѡ��ѡ�����*/%>
		function changeSelect()
	  {
	  	if(document.forms[0].hy47_1.checked == true)
	  	{
	  		//ѡ��Ϊ������˰�ģ����������˰��ʽ����ѡ��ͬʱ����Щ������λ���ɱ༭
	  		document.all("47_1").value = "01";
	  	}
	  	else
	  	{
	  		document.all("47_1").value = "02";
	  	}
	  }
	  /**
       * ��ʽ������������0��ͷ
       *
       * @param �ı���
       */
       function formateNum_a(obj){
       var tempNum=obj.value;
       if(tempNum.indexOf(".")!=-1)
          return;
       	var num=tempNum;
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
	marginheight="0" onLoad="doInit()">
<%@include file="../../include/header.jsp"%>
<br>

<html:form action="/webapp/smsb/qysds/2008/hdzsssyhmxAction2008">
	<html:hidden property="jsjdm" />
	<html:hidden property="sbrq" />
		
	<html:hidden property="actionType" />
	<html:hidden property="nsrsbh" />
	<html:hidden property="zgswjgzzjgmc" />
	<html:hidden property="swjgzzjgdm" />
	<html:hidden property="nsrmc" />
	<html:hidden property="lxdh" />
	<html:hidden property="jydz" />
	<html:hidden property="sknd" />
	<html:hidden property="qh" />
	<html:hidden property="bbqlx" />
	<html:hidden property="qxdm" />
	<html:hidden property="swjsjdm" />
	<html:hidden property="qyzslx" />
	<html:hidden property="cyl" />
	<html:hidden property="dezsse" />
	<html:hidden property="sysl" />
	<html:hidden property="zsfs"/>
	<html:hidden property="zbh3"/>
		<% if("no".equals(null)){ %>
<font color=red>����δ����С��΢����ҵ���ߡ���ȷ��������д�ļ���˰��</font>
    <% } %>
	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">˰���Ż���ϸ��</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="hdzsssyhmxForm2008" property="sbrq" scope="request" filter="true" />
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
				name="hdzsssyhmxForm2008" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="hdzsssyhmxForm2008" property="nsrmc" scope="request" filter="true" />&nbsp;
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
					<div id="Layer2" style="position:static;">
					<table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
						<tr>
							<!--<td class="2-td1-left" nowrap>�д�</td>
									   	<td class="2-td1-left" nowrap>��Ŀ</td><!-->
							<td class="2-td1-left" nowrap>�д�</td>
							<td class="2-td1-left" nowrap>��Ŀ</td>
							<td class="2-td1-center" nowrap>���</td>
							<td class="2-td1-left" nowrap>�д�</td>
							<td class="2-td1-left" nowrap>��Ŀ</td>
							<td class="2-td1-center" nowrap>���</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>1 <input type="hidden" name="hc"
								value="1" id="hc_1"></td>
							<td class="2-td2-left-qysds1" nowrap>һ����˰���루2��3��4��5��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='1_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>25 <input type="hidden" name="hc"
								value="25" id="hc_25"></td>
							<td class="2-td2-left-qysds1" nowrap>   ��������˰���ã�26��27��28��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='25_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>2 <input type="hidden" name="hc"
								value="2" id="hc_2"></td>
							<td class="2-td2-left-qysds1" nowrap>    1����ծ��Ϣ����</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='2_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>26 <input type="hidden" name="hc"
								value="26" id="hc_26"></td>
							<td class="2-td2-left-qysds1" nowrap>      1�����ܡ����Լ�������������������������ֲ</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='26_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>

						<tr>
							<td class="2-td2-left" nowrap>3 <input type="hidden" name="hc"
								value="3" id="hc_3"></td>
							<td class="2-td2-left-qysds1" nowrap>    2�����������ľ�����ҵ֮��Ĺ�Ϣ��������Ȩ����Ͷ������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='3_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>27 <input type="hidden" name="hc"
								value="27" id="hc_27"></td>
							<td class="2-td2-left-qysds1" nowrap>      2����ˮ��ֳ����½��ֳ</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='27_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>4 <input type="hidden" name="hc"
								value="4" id="hc_4"></td>
							<td class="2-td2-left-qysds1" nowrap>    3�����������ķ�Ӫ����֯������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='4_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>28 <input type="hidden" name="hc"
								value="28" id="hc_28"></td>
							<td class="2-td2-left-qysds1" nowrap>      3������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='28_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>5 <input type="hidden" name="hc"
								value="5" id="hc_5"></td>
							<td class="2-td2-left-qysds1" nowrap>    4������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='5_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>29 
							  <input type="hidden" name="hc"
								value="29" id="hc_29"></td>
							<td class="2-td2-left-qysds1">   ���������¹����ص���ֵĹ���������ʩ��ĿͶ�ʾ�Ӫ������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='29_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>6 <input type="hidden" name="hc"
								value="6" id="hc_6"></td>
							<td class="2-td2-left-qysds1" nowrap>�����������루7��8��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='6_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>30 
							  <input type="hidden" name="hc"
								value="30" id="hc_30"></td>
							<td class="2-td2-left-qysds1" nowrap>   ���ģ����·��������Ļ������������ܽ�ˮ��Ŀ������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='30_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>7 <input type="hidden" name="hc"
								value="7" id="hc_7"></td>
							<td class="2-td2-left-qysds1">    1����ҵ�ۺ�������Դ,�������Ϲ��Ҳ�ҵ���߹涨�Ĳ�Ʒ��ȡ�õ�����</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='7_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>31 
							  <input type="hidden" name="hc"
								value="31" id="hc_31"></td>
							<td class="2-td2-left-qysds1" nowrap>   ���壩���������ļ���ת������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='31_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>8 <input type="hidden" name="hc"
								value="8" id="hc_8"></td>
							<td class="2-td2-left-qysds1" nowrap>    2������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='8_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>32 
							  <input type="hidden" name="hc"
								value="32" id="hc_32"></td>
							<td class="2-td2-left-qysds1" nowrap>   ����������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='32_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>9 <input type="hidden" name="hc"
								value="9" id="hc_9"></td>
							<td class="2-td2-left-qysds1" nowrap>�����Ӽƿ۳���ϼƣ�10��11��12��13��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='9_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>33 <input type="hidden" name="hc"
								value="33" id="hc_33"></td>
							<td class="2-td2-left-qysds1" nowrap>�塢����˰�ϼƣ�34��35��36��37��38��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='33_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>10 <input type="hidden" name="hc"
								value="10" id="hc_10"></td>
							<td class="2-td2-left-qysds1" nowrap>    1�������¼������²�Ʒ���¹��շ������о���������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='10_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>34 <input type="hidden" name="hc"
								value="34" id="hc_34"></td>
							<td class="2-td2-left-qysds1" nowrap>   ��һ������������С��΢����ҵ</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='34_1' onchange="compute_Row_33(this)" value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>11 <input type="hidden" name="hc"
								value="11" id="hc_11"></td>
							<td class="2-td2-left-qysds1" nowrap>    2�����òм���Ա��֧���Ĺ���</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='11_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>35 <input type="hidden" name="hc"
								value="35" id="hc_35"></td>
							<td class="2-td2-left-qysds1" nowrap>   ������������Ҫ�ص���ֵĸ��¼�����ҵ</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='35_1' style='text-align:right' readonly='true' class="text-gray" onchange="compute_Row_33(this)" value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>12 <input type="hidden" name="hc"
								value="12" id="hc_12"></td>
							<td class="2-td2-left-qysds1" nowrap>    3�����ҹ������õ�������ҵ��Ա֧���Ĺ���</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='12_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>36 
							  <input type="hidden" name="hc"
								value="36" id="hc_36"></td>
							<td class="2-td2-left-qysds1">   �������������εط�����ҵӦ���ɵ���ҵ����˰�����ڵط�����Ĳ���</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='36_1' style='text-align:right' readonly='true' class="text-gray" onchange="compute_Row_33(this)" value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>13 <input type="hidden" name="hc"
								value="13" id="hc_13"></td>
							<td class="2-td2-left-qysds1" nowrap>    4������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='13_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>37 
							  <input type="hidden" name="hc"
								value="37" id="hc_37"></td>
							<td class="2-td2-left-qysds1" nowrap>   ���ģ�������˰���Ż�</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='37_1' style='text-align:right' readonly='true' class="text-gray" onchange="compute_Row_33(this)" value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>14 <input type="hidden" name="hc"
								value="14" id="hc_14"></td>
							<td class="2-td2-left-qysds1" nowrap>�ġ��������ö�ϼƣ�15��25��29��30��31��32��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='14_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>38 
							  <input type="hidden" name="hc"
								value="38" id="hc_38"></td>
							<td class="2-td2-left-qysds1" nowrap>   ���壩����</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='38_1' onchange="compute_Row_33(this)" value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>15 <input type="hidden" name="hc"
								value="15" id="hc_15"></td>
							<td class="2-td2-left-qysds1" nowrap>   ��һ����˰���ã�16��17������24��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='15_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>39 
							  <input type="hidden" name="hc"
								value="39" id="hc_39"></td>
							<td class="2-td2-left-qysds1" nowrap>������ҵͶ����ҵ�ֿ۵�Ӧ��˰���ö�</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='39_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>16 <input type="hidden" name="hc"
								value="16" id="hc_16"></td>
							<td class="2-td2-left-qysds1">      1���߲ˡ�������ࡢ���ϡ����ࡢ�޻������ࡢ���ϡ�ˮ�����������ֲ</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='16_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>40 
							  <input type="hidden" name="hc"
								value="40" id="hc_40"></td>
							<td class="2-td2-left-qysds1" nowrap>�ߡ���������˰��ϼƣ�41��42��43��44��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='40_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>17 <input type="hidden" name="hc"
								value="17" id="hc_17"></td>
							<td class="2-td2-left-qysds1" nowrap>      2��ũ������Ʒ�ֵ�ѡ��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='17_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>41 
							  <input type="hidden" name="hc"
								value="41" id="hc_41"></td>
							<td class="2-td2-left-qysds1" nowrap>   ��һ����ҵ�������ڻ�������ר���豸��Ͷ�ʶ�����˰��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='41_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>18 <input type="hidden" name="hc"
								value="18" id="hc_18"></td>
							<td class="2-td2-left-qysds1" nowrap>      3����ҩ�ĵ���ֲ</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='18_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>42 
							  <input type="hidden" name="hc"
								value="42" id="hc_42"></td>
							<td class="2-td2-left-qysds1" nowrap>   ��������ҵ�������ڽ��ܽ�ˮר���豸��Ͷ�ʶ�����˰��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='42_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>19 <input type="hidden" name="hc"
								value="19" id="hc_19"></td>
							<td class="2-td2-left-qysds1" nowrap>      4����ľ����������ֲ</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='19_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>43 
							  <input type="hidden" name="hc"
								value="43" id="hc_43"></td>
							<td class="2-td2-left-qysds1" nowrap>   ��������ҵ�������ڰ�ȫ����ר���豸��Ͷ�ʶ�����˰��</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='43_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>20 <input type="hidden" name="hc"
								value="20" id="hc_20"></td>
							<td class="2-td2-left-qysds1" nowrap>      5�����󡢼��ݵ�����</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='20_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>44 <input type="hidden" name="hc"
								value="44" id="hc_44"></td>
							<td class="2-td2-left-qysds1" nowrap>   ���ģ�����</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='44_1' value='' size='13' tabindex='2' style='text-align:right' readonly='true' class="text-gray"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>21 <input type="hidden" name="hc"
								value="21" id="hc_21"></td>
							<td class="2-td2-left-qysds1" nowrap>      6���ֲ�Ʒ�Ĳɼ�</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='21_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>45 <input type="hidden" name="hc"
								value="45" id="hc_45"></td>
							<td class="2-td2-left-qysds1" nowrap>��ҵ��ҵ������ȫ��ƽ��������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='45_1' onchange="formateNum_a(this)" value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>22 <input type="hidden" name="hc"
								value="22" id="hc_22"></td>
							<td class="2-td2-left-qysds1">      7����ȡ�ũ��Ʒ���ӹ�����ҽ��ũ���ƹ㡢ũ����ҵ��ά�޵�ũ���֡����������ҵ��Ŀ</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='22_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>46 <input type="hidden" name="hc"
								value="46" id="hc_46"></td>
							<td class="2-td2-left-qysds1" nowrap>�ʲ��ܶȫ��ƽ������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='46_1' onchange="checkNumInput(this)" value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>23 <input type="hidden" name="hc"
								value="23" id="hc_23"></td>
							<td class="2-td2-left-qysds1" nowrap>      8��Զ����</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='23_1' value='' size='13' tabindex='1' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>47 <input type="hidden" name="hc"
								value="47" id="hc_47"></td>
							<td class="2-td2-left-qysds1" nowrap>������ҵ����ҵ��ҵ    ������ҵ    ��</td>
							<td class="2-td2-center" nowrap><input type='hidden' name='je'
								id='47_1' value=''>
								<input type="Radio" name="hyje" id="hy47_1" value="01" onclick="changeSelect()" onchange="changeSelect()">��ҵ��ҵ
								<input type="Radio" name="hyje" id="hy47_2" value="02" onclick="changeSelect()" onchange="changeSelect()">������ҵ
								</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>24 <input type="hidden" name="hc"
								value="24" id="hc_24"></td>
							<td class="2-td2-left-qysds1" nowrap>      9������</td>
							<td class="2-td2-center" nowrap><input type='text' name='je'
								id='24_1' value='' size='13' tabindex='1' style='text-align:right' readonly='true' class="text-gray"></td>
							<td class="2-td2-left" nowrap>&nbsp;</td>
							<td class="2-td2-left-qysds1" nowrap>&nbsp;</td>
							<td class="2-td2-center" nowrap>&nbsp;</td>
						</tr>
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center">
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
					&nbsp;&nbsp; <input type="image" value="����" alt="���ص���ҵ����˰�걨��ҳ��"
						style="cursor:hand" tabIndex="-1" onClick="doReturn();return false;"
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
	<%@ include file="../../include/footernew.jsp"%>
</html:form>
</body>
</html>