<%@page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.qysdsmbksmxb.web.QysdsmbksmxbForm2008"%>
<%@ page import="java.util.HashMap"%>
<html>
<head>
<title>��ҵ����˰�ֲ�������ϸ��</title>
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
<script language=JavaScript>
/*��ʼ��*/
function doInit()
{
   //��ʼ���ı���onChange�¼�����
	initNumText("kshyle",6);
    initNumText("kmbkse",6);
    initNumText("hj",6);
    initNumText("d2n",1);
    initNumText("d3n",2);
    initNumText("d4n",3);
    initNumText("d5n",4);
    initNumText("ymbgksehj",5);
    initNumText("bnkmbkse",6);
    initNumText("xynmbkse",7);
  <%
	QysdsmbksmxbForm2008 mbksmxbForm=(QysdsmbksmxbForm2008)request.getAttribute("qysdsmbksmxbForm2008");
	String sknd=mbksmxbForm.getSknd();	
	if (mbksmxbForm!=null && mbksmxbForm.getDataList().size()>0){
		for(int i=0;i<mbksmxbForm.getDataList().size();i++){
			HashMap map=(HashMap)mbksmxbForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String nd=(String)map.get("nd");
            String kshyle=(String)map.get("kshyle");
            String kmbkse=(String)map.get("kmbkse");
			String hj=(String)map.get("hj");
			String d2n=(String)map.get("d2n");
			String d3n=(String)map.get("d3n");
			String d4n=(String)map.get("d4n");
			String d5n=(String)map.get("d5n");
			String ymbgksehj=(String)map.get("ymbgksehj");
			String bnkmbkse=(String)map.get("bnkmbkse");
			String xynmbkse=(String)map.get("xynmbkse");
			%>
			obj1 = eval("document.getElementById('<%=hc%>_1')");
			obj1.value ='<%=nd%>';
			obj2 = eval("document.getElementById('<%=hc%>_2')");
			obj2.value = '<%=kshyle%>';
            obj3 = eval("document.getElementById('<%=hc%>_3')");
			obj3.value = '<%=kmbkse%>';
			obj4 = eval("document.getElementById('<%=hc%>_4')");
			obj4.value = '<%=hj%>';
			obj5 = eval("document.getElementById('<%=hc%>_5')");
			obj5.value = '<%=d2n%>';
			obj6 = eval("document.getElementById('<%=hc%>_6')");
			obj6.value = '<%=d3n%>';
			obj7 = eval("document.getElementById('<%=hc%>_7')");
			obj7.value = '<%=d4n%>';
			obj8 = eval("document.getElementById('<%=hc%>_8')");
			obj8.value = '<%=d5n%>';
			obj9 = eval("document.getElementById('<%=hc%>_9')");
			obj9.value = '<%=ymbgksehj%>';
			obj10 = eval("document.getElementById('<%=hc%>_10')");
			obj10.value = '<%=bnkmbkse%>';
			obj11 = eval("document.getElementById('<%=hc%>_11')");
			obj11.value = '<%=xynmbkse%>';
			<% 
		}
	}
	%>
	document.getElementById('1_1').value=<%=sknd%>-5;
	document.getElementById('2_1').value=<%=sknd%>-4;
	document.getElementById('3_1').value=<%=sknd%>-3;
	document.getElementById('4_1').value=<%=sknd%>-2;
	document.getElementById('5_1').value=<%=sknd%>-1;
	document.getElementById('6_1').value=<%=sknd%>;
}


<%/*����*/%>
	function doSave()
	{
		doSubmitForm('Save');
		
	}
	
	<%/*�ж��������Ƿ�Ϊ���*/%>
//function isYear(obj)
//{ 
  
   //var strYear = obj.value;
  // if(strYear.length!=4)
 //  {
   //  alert("��ȱ���Ϊ��λ���֣�����");
    // window.event.returnValue=false;
    // obj.focus();
    // obj.select();
    // return false;
   //}else{
   //var str1 = strYear.substr(0,1);
   
   //if(str1!=0&&str1!=1&&str1!=2&&str1!=3&&str1!=4&&str1!=5&&str1!=6&&str1!=7&&str1!=8&&str1!=9)
      	//{
      		// alert("��ȵ�1λ����Ϊ���֣�����");
      		 //window.event.returnValue=false;
      		// obj.focus();
            // obj.select();  
            // return false;  		
      //	}
      //	var str2 = strYear.substr(1,1);

   //if(str2!=0&&str2!=1&&str2!=2&&str2!=3&&str2!=4&&str2!=5&&str2!=6&&str2!=7&&str2!=8&&str2!=9)
      	//{
      		//alert("��ȵ�2λ����Ϊ���֣�����");
      		//window.event.returnValue=false;
      		//obj.focus();
            //obj.select(); 
            //return false;		
      	//}
      	//var str2 = strYear.substr(2,1);

      	// if(str2!=0&&str2!=1&&str2!=2&&str2!=3&&str2!=4&&str2!=5&&str2!=6&&str2!=7&&str2!=8&&str2!=9)
      	//{
      		// alert("��ȵ�3λ����Ϊ���֣�����");
      		// window.event.returnValue=false;
      		// obj.focus();
             //obj.select();	
            // return false;	
      	//}
      	//var str2 = strYear.substr(3,1);

      	// if(str2!=0&&str2!=1&&str2!=2&&str2!=3&&str2!=4&&str2!=5&&str2!=6&&str2!=7&&str2!=8&&str2!=9)
      	//{
      		 //alert("��ȵ�4λ����Ϊ���֣�����");
      		 //window.event.returnValue=false;
      		 //obj.focus();
            // obj.select();
            // return false;
      	//}
      //}
      //	return true;
//}
	
	<%/*���*/%>
	function doReset()
	{
	    if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
	    {
	    <%
		   	for(int i=1; i<=7; i++){
		   	%>
		   	    
				obj = eval("document.getElementById('<%=i%>_1')");
				if(obj.readOnly!=true)
				{
				obj.value = "";
				}
				obj = eval("document.getElementById('<%=i%>_2')");
				if(obj.readOnly!=true)
				{
				obj.value = "";
				}
				obj = eval("document.getElementById('<%=i%>_3')");
				if(obj.readOnly!=true)
				{
				obj.value = "";
				}
				obj = eval("document.getElementById('<%=i%>_4')");
				if(obj.readOnly!=true)
				{
				obj.value = "";
				}
				obj = eval("document.getElementById('<%=i%>_5')");
				if(obj.readOnly!=true)
				{
				obj.value = "";
				}
				obj = eval("document.getElementById('<%=i%>_6')");
				if(obj.readOnly!=true)
				{
				obj.value = "";
				}
				obj = eval("document.getElementById('<%=i%>_7')");
				if(obj.readOnly!=true)
				{
				obj.value = "";
				}
				obj = eval("document.getElementById('<%=i%>_8')");
				if(obj.readOnly!=true)
				{
				obj.value = "";
				}
				obj = eval("document.getElementById('<%=i%>_9')");
				if(obj.readOnly!=true)
				{
				obj.value = "";
				}
				obj = eval("document.getElementById('<%=i%>_10')");
				if(obj.readOnly!=true)
				{
				obj.value = "";
				}
				obj = eval("document.getElementById('<%=i%>_11')");
				if(obj.readOnly!=true)
				{
				obj.value = "";
				}
				<%
		   	}
		   	%>
	    }
	}
	
	<%/*ɾ��*/%>
	function doDelete()
	{
	
		doSubmitForm('Delete');
	}
	function doChecked()
	{
	   doSubmitForm('Check');
	}
		
		<%/*����*/%>
		function doExit()
		{
			doSubmitForm('Exit');
		}
		
	<%/*�ж���ȵķ���*/%>	
	function checknd(obj,i){
		if(isNum(obj,0,9999,null,4,0)){
			if(obj.value.length!=4&&obj.value!=""){
				alert("������Ȳ�Ϊ��λ!!!");
				window.event.returnValue=false;
				obj.focus();
				obj.select();
				return false;		
			}else{	
				var nd=parseInt(document.forms[0].sbrq.value.substring(0,4))-1;
				var ndx=parseInt(nd)-i;
				if(obj.value!=ndx){
					alert("������Ȳ��ԣ�Ӧ��Ϊ"+ndx+"!");
				    window.event.returnValue=false;
				    obj.focus();
				    obj.select();
				    return false;
				}
			}
		}
		else{
			return false;		
		}
	}
	<%/*�ж����*/%>	
	function isYear(obj){
		isNum(obj,null,9999999999999,null,16,0);
	}

</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInit()">
<%@ include file="../../include/header.jsp"%>
<br>
<html:form action="/webapp/smsb/qysds/2008/qysdsmbksmxbAction2008.do" method="post">

	<html:hidden property="actionType" />
	<html:hidden property="sbrq" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />

	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">��ҵ����˰�ֲ�������ϸ��</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="qysdsmbksmxbForm2008" property="sbrq" scope="request" filter="true" />
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
				name="qysdsmbksmxbForm2008" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="qysdsmbksmxbForm2008" property="nsrmc" scope="request" filter="true" />&nbsp;
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
					<div id="Layer2" style="position:static; overflow: auto; ">
					<table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
						<tr>
							<td rowspan="3" class="2-td1-left">��&nbsp;&nbsp;��</td>
							<td rowspan="3" class="2-td1-left">��&nbsp;&nbsp;&nbsp;&nbsp;Ŀ</td>
							<td rowspan="2" class="2-td1-left">���</td>
							<td rowspan="2" class="2-td1-left">ӯ���������</td>
							<td rowspan="2" class="2-td1-left">�ϲ�������ҵת����ֲ������</td>
							<td rowspan="2" class="2-td1-left">������ֲ������ö�</td>
							<td colspan="5" class="2-td1-left">��ǰ��ȿ����ֲ���</td>
							<td rowspan="2" class="2-td1-left">�����ʵ���ֲ�����ǰ��ȿ����</td>
							<td rowspan="2" class="2-td1-center">�ɽ�ת�Ժ�����ֲ��Ŀ����</td>
						</tr>
						<tr>
							<td class="2-td2-left">ǰ�����</td>
							<td class="2-td2-left">ǰ�����</td>
							<td class="2-td2-left">ǰ�����</td>
							<td class="2-td2-left">ǰһ���</td>
							<td class="2-td2-center">�ϼ�</td>
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
							<td class="2-td2-left">10</td>
							<td class="2-td2-center">11</td>
						</tr>
						<tr>
							<td class="2-td2-left">1</td>
							<td class="2-td2-left">��һ��</td>
							<td class="2-td2-left"><input type='text' name='nd' id='1_1'
								value='' size='8' tabindex='2' maxlength='4'
								style="text-align:center" readonly='true'></td>
							<td class="2-td2-left"><input type='text' name='kshyle' id='1_2'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='kmbkse' id='1_3'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='hj' id='1_4'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='d2n' id='1_5'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='d3n' id='1_6'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='d4n' id='1_7'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='d5n' id='1_8'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='ymbgksehj'
								id='1_9' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='bnkmbkse'
								id='1_10' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='xynmbkse'
								id='1_11' value='*' tabindex='-1' tabindex='-1' size='1' readonly='true'
								class='text-noborder'> <input type="hidden" name="hc" value="1" />
							</td>
						</tr>
						<tr>
							<td class="2-td2-left">2</td>
							<td class="2-td2-left">�ڶ���</td>
							<td class="2-td2-left"><input type='text' name='nd' id='2_1'
								value='' size='8' tabindex='2' maxlength='4'
								style="text-align:center" readonly='true'></td>
							<td class="2-td2-left"><input type='text' name='kshyle' id='2_2'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='kmbkse' id='2_3'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='hj' id='2_4'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='d2n' id='2_5'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='d3n' id='2_6'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='d4n' id='2_7'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='d5n' id='2_8'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='ymbgksehj'
								id='2_9' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='bnkmbkse'
								id='2_10' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='xynmbkse'
								id='2_11' value='' size='8' tabindex='2'> <input type="hidden"
								name="hc" value="2" /></td>
						</tr>
						<tr>
							<td class="2-td2-left">3</td>
							<td class="2-td2-left">������</td>
							<td class="2-td2-left"><input type='text' name='nd' id='3_1'
								value='' size='8' tabindex='2' maxlength='4'
								style="text-align:center" readonly='true'></td>
							<td class="2-td2-left"><input type='text' name='kshyle' id='3_2'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='kmbkse' id='3_3'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='hj' id='3_4'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='d2n' id='3_5'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='d3n' id='3_6'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='d4n' id='3_7'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='d5n' id='3_8'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='ymbgksehj'
								id='3_9' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='bnkmbkse'
								id='3_10' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='xynmbkse'
								id='3_11' value='' size='8' tabindex='2'> <input type="hidden"
								name="hc" value="3" /></td>
						</tr>
						<tr>
							<td class="2-td2-left">4</td>
							<td class="2-td2-left">������</td>
							<td class="2-td2-left"><input type='text' name='nd' id='4_1'
								value='' size='8' tabindex='2' maxlength='4'
								style="text-align:center" readonly='true'></td>
							<td class="2-td2-left"><input type='text' name='kshyle' id='4_2'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='kmbkse' id='4_3'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='hj' id='4_4'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='d2n' id='4_5'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='d3n' id='4_6'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='d4n' id='4_7'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='d5n' id='4_8'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='ymbgksehj'
								id='4_9' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='bnkmbkse'
								id='4_10' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='xynmbkse'
								id='4_11' value='' size='8' tabindex='2'> <input type="hidden"
								name="hc" value="4" /></td>
						</tr>
						<tr>
							<td class="2-td2-left">5</td>
							<td class="2-td2-left">������</td>
							<td class="2-td2-left"><input type='text' name='nd' id='5_1'
								value='' size='8' tabindex='2' maxlength='4'
								style="text-align:center" readonly='true'></td>
							<td class="2-td2-left"><input type='text' name='kshyle' id='5_2'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='kmbkse' id='5_3'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='hj' id='5_4'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='d2n' id='5_5'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='d3n' id='5_6'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='d4n' id='5_7'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='d5n' id='5_8'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='ymbgksehj'
								id='5_9' value='' tabindex='-1' size='8' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='bnkmbkse'
								id='5_10' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='xynmbkse'
								id='5_11' value='' size='8' tabindex='2'> <input type="hidden"
								name="hc" value="5" /></td>
						</tr>
						<tr>
							<td class="2-td2-left">6</td>
							<td class="2-td2-left">�� ��</td>
							<td class="2-td2-left"><input type='text' name='nd' id='6_1'
								value='' size='8' tabindex='2' maxlength='4'
								style="text-align:center" readonly='true'></td>
							<td class="2-td2-left"><input type='text' name='kshyle' id='6_2'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='kmbkse' id='6_3'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='hj' id='6_4'
								value='' size='8' tabindex='2'></td>
							<td class="2-td2-left"><input type='text' name='d2n' id='6_5'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='d3n' id='6_6'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='d4n' id='6_7'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='d5n' id='6_8'
								value='*' tabindex='-1' size='1' readonly='true' class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='ymbgksehj'
								id='6_9' value='*' tabindex='-1' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left"><input type='text' name='bnkmbkse'
								id='6_10' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center"><input type='text' name='xynmbkse'
								id='6_11' value='' size='8' tabindex='2'> <input type="hidden"
								name="hc" value="6" /></td>
						</tr>
						<tr>
							<td class="2-td2-left">7</td>
							<td colspan="11" class="2-td2-left-qysds1">�ɽ�ת�Ժ�����ֲ��Ŀ����ϼ�</td>
							<td class="2-td2-center"><input type='text' name='xynmbkse'
								id='7_11' value='' size='8' tabindex='2'> <input type="hidden"
								name="nd" value="" id="7_1" /> <input type="hidden"
								name="kshyle" value="" id="7_2" /> <input type="hidden"
								name="kmbkse" value="" id="7_3" /> <input type="hidden"
								name="hj" value="" id="7_4" /> <input type="hidden" name="d2n"
								value="" id="7_5" /> <input type="hidden" name="d3n" value=""
								id="7_6" /> <input type="hidden" name="d4n" value="" id="7_7" />
							<input type="hidden" name="d5n" value="" id="7_8" /> <input
								type="hidden" name="ymbgksehj" value="" id="7_9" /> <input
								type="hidden" name="bnkmbkse" value="" id="7_10" /> <input
								type="hidden" name="hc" value="7" /></td>
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
						onClick="doChecked();return false;" accesskey="d" value="����У��"
						alt="���ڹ�ϵУ��"
						onMouseOver="MM_swapImage('jiaoyan','','../../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../../images/b-bdjyd1.jpg"
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
	<%@ include file="../../include/footernew.jsp"%>
</html:form>

</body>
</html>
