<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb1.web.QyqssdsFb1Form2014"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<%@ page import="java.util.HashMap"%>

<html>
<head>
<title>�ʲ�����������ϸ��</title>
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
	src="../../../js/qyqssdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/function.js"></script>
</head>
<script language="JavaScript">
	
	<%/*��ʼ��*/%>
	function doInit()
	{
	<%QyqssdsFb1Form2014 fb1Form=(QyqssdsFb1Form2014)request.getAttribute("qyqssdsFb1Form2014");
	if (fb1Form!=null && fb1Form.getDataList().size()>0)
	{%> 
		        //��ʼ���ı���onChange�¼�����
		        initNumText("zmjz",11);
		        initNumText("jsjc",11);
		        initNumText("jyjg",11);
		        initNumText("zcczxy",11);

		   	 <%
			   	 for(int i=0;i<fb1Form.getDataList().size();i++)
				 {
					HashMap map=(HashMap)fb1Form.getDataList().get(i);
					int hc1=Integer.parseInt((String)map.get("hc1"));
					String zmjz=(String)map.get("zmjz");
					String jsjc=(String)map.get("jsjc");
					String jyjg=(String)map.get("jyjg");
					String zcczxy=(String)map.get("zcczxy");
			%>	  			
				obj1=eval("document.getElementById('fgx_<%=hc1%>_1')");
				obj1.value='<%=zmjz%>';			
				obj2=eval("document.getElementById('fgx_<%=hc1%>_2')");
				obj2.value='<%=jsjc%>';
				obj3=eval("document.getElementById('fgx_<%=hc1%>_3')");
				obj3.value='<%=jyjg%>';
				obj4=eval("document.getElementById('fgx_<%=hc1%>_4')");
				obj4.value='<%=zcczxy%>';
							
			<% 
				}
		}
	%>
	
	var shzt = "<%=fb1Form.getSbShztbs().equals("")?"-1":fb1Form.getSbShztbs()%>";
	var sqlx = "<%=fb1Form.getSqlx()==null?"-1":fb1Form.getSqlx()%>";
	//�����걨�Ĳ������޸ģ����������Ϊֻ��
	if(sqlx=="0"){
		//doCheckInput();
	}else{
	//�걨���״̬Ϊ���ͨ���Ĳ������޸ģ����������Ϊֻ��
	if(shzt=="2"){
		doCheckInput();
	}
	}
	
	}
	
	
	//��֤���״̬������������ֻ���Ϳɱ༭
	function doCheckInput(){
		//ȡ����input��ǩ����
		 var all = document.getElementsByTagName("input");
		 for(var i = 0;i<all.length;i++){
			 //��input��ǩ�е�text������Ϊֻ��
			 if(all[i].type=="text"){
				 all[i].readOnly=true;
				 all[i].className="text-gray";
			 }
			 
		 }
	}
	<%/*���*/%>
	function doReset()
	{
	    if(confirm("ȷ���Ƿ������ǰ���ݣ�")){  
		   	       for(var i=1; i<=32; i++)
		   	       {
				      obj = eval("document.getElementById('fgx_"+i+"_1')");
				         if (obj.readOnly!=true)
					          obj.value = "";
				      obj = eval("document.getElementById('fgx_"+i+"_2')");
				          if (obj.readOnly!=true)
					          obj.value = "";
				      obj = eval("document.getElementById('fgx_"+i+"_3')");
				          if (obj.readOnly!=true)
					          obj.value = "";
				      obj = eval("document.getElementById('fgx_"+i+"_4')");
				          if (obj.readOnly!=true)
					         obj.value = "";
					}
      	}	
	}
	
	<%/*����*/%>
	function doSave()
	{
		doSubmitForm('Save');
	}
	<%/*У��*/%>
	function doCheck()
	{
		doSubmitForm('Check');
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

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInit()">
<%@ include file="../../include/header.jsp"%>


<html:form action="/webapp/smsb/qyqssds/2014/qyqssdsFb1Action2014">
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
	<html:hidden property="sbShztbs" />
	<html:hidden property="sqlx" />

	<table width="96%"  cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">�ʲ�����������ϸ��</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="qyqssdsFb1Form2014" property="tbrq" scope="request" filter="true" />
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
				name="qyqssdsFb1Form2014" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="qyqssdsFb1Form2014" property="nsrmc" scope="request" filter="true" />&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
			</td>

		</tr>
		<tr>
			<td class="1-td2" colspan="7">
			<TABLE class="table-99" >
				<TR>
					<TD>
          <div id="Layer2" style="position:static;">
					<table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
						<tr>
							<td rowspan="2" class="2-td1-left">��&nbsp;&nbsp;��</td>
							<td rowspan="2" class="2-td1-left">��&nbsp;&nbsp;&nbsp;&nbsp;Ŀ</td>
							<td class="2-td1-left" >�����ֵ</td>
							<td class="2-td1-left" >��˰����</td>
							<td class="2-td1-left" >�ɱ��ּ�ֵ���׼۸�</td>
							<td class="2-td1-center" >�ʲ���������</td>
						</tr>
						<tr>
							<td class="2-td2-left" >1</td>
							<td class="2-td2-left" >2</td>
							<td class="2-td2-left" >3</td>
							<td class="2-td2-center" >4��3-2��</td>
						</tr>
						<tr>
							<td class="2-td2-left" >1</td>
							<td class="2-td2-left-qysds1" >�����ʽ�</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_1_1' value='' size='13' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_1_2' value='' size='13' tabindex='3'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_1_3' value='' size='13' tabindex='4'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_1_4' value='' size='13' tabindex='5'></td>
							<input type='hidden' name='hc1' value='1'>
						</tr>
						<tr>
							<td class="2-td2-left" >2</td>
							<td class="2-td2-left-qysds1" >����Ͷ��*</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_2_1' value='' size='13' tabindex='6'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_2_2' value='' size='13' tabindex='7'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_2_3' value='' size='13' tabindex='8'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_2_4' value='' size='13' tabindex='9'></td>
							<input type='hidden' name='hc1' value='2'>
						</tr>
						<tr>
							<td class="2-td2-left" >3</td>
							<td class="2-td2-left-qysds1" >�����Խ����ʲ�#</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_3_1' value='' size='13' tabindex='10'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_3_2' value='' size='13' tabindex='11'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_3_3' value='' size='13' tabindex='12'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_3_4' value='' size='13' tabindex='13'></td>
							<input type='hidden' name='hc1' value='3'>
						</tr>
						<tr>
							<td class="2-td2-left" >4</td>
							<td class="2-td2-left-qysds1" >Ӧ��Ʊ��</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_4_1' value='' size='13' tabindex='14'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_4_2' value='' size='13' tabindex='15'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_4_3' value='' size='13' tabindex='16'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_4_4' value='' size='13' tabindex='17'></td>
							<input type='hidden' name='hc1' value='4'>
						</tr>
						<tr>
							<td class="2-td2-left" >5</td>
							<td class="2-td2-left-qysds1" >Ӧ���˿�</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_5_1' value='' size='13' tabindex='18'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_5_2' value='' size='13' tabindex='19'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_5_3' value='' size='13' tabindex='20'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_5_4' value='' size='13' tabindex='21'></td>
							<input type='hidden' name='hc1' value='5'>
						</tr>
						<tr>
							<td class="2-td2-left" >6</td>
							<td class="2-td2-left-qysds1" >Ԥ���˿�</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_6_1' value='' size='13' tabindex='22'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_6_2' value='' size='13' tabindex='23'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_6_3' value='' size='13' tabindex='24'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_6_4' value='' size='13' tabindex='25'></td>
							<input type='hidden' name='hc1' value='6'>
						</tr>
						<tr>
							<td class="2-td2-left" >7</td>
							<td class="2-td2-left-qysds1" >Ӧ����Ϣ</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_7_1' value='' size='13' tabindex='26'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_7_2' value='' size='13' tabindex='27'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_7_3' value='' size='13' tabindex='28'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_7_4' value='' size='13' tabindex='29'></td>
							<input type='hidden' name='hc1' value='7'>
						</tr>
						<tr>
							<td class="2-td2-left" >8</td>
							<td class="2-td2-left-qysds1" >Ӧ�չ���</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_8_1' value='' size='13' tabindex='30'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_8_2' value='' size='13' tabindex='31'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_8_3' value='' size='13' tabindex='32'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_8_4' value='' size='13' tabindex='33'></td>
							<input type='hidden' name='hc1' value='8'>
						</tr>
						<tr>
							<td class="2-td2-left" >9</td>
							<td class="2-td2-left-qysds1" >Ӧ�ղ�����*</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_9_1' value='' size='13' tabindex='34'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_9_2' value='' size='13' tabindex='35'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_9_3' value='' size='13' tabindex='36'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_9_4' value='' size='13' tabindex='37'></td>
							<input type='hidden' name='hc1' value='9'>
						</tr>
						
						<tr>
							<td class="2-td2-left" >10</td>
							<td class="2-td2-left-qysds1" >����Ӧ�տ�</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_10_1' value='' size='13' tabindex='38'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_10_2' value='' size='13' tabindex='39'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_10_3' value='' size='13' tabindex='40'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_10_4' value='' size='13' tabindex='41'></td>
							<input type='hidden' name='hc1' value='10'>
						</tr>
						<tr>
							<td class="2-td2-left" >11</td>
							<td class="2-td2-left-qysds1" >���</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_11_1' value='' size='13' tabindex='42'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_11_2' value='' size='13' tabindex='43'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_11_3' value='' size='13' tabindex='44'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_11_4' value='' size='13' tabindex='45'></td>
							<input type='hidden' name='hc1' value='11'>
						</tr>
						<tr>
							<td class="2-td2-left" >12</td>
							<td class="2-td2-left-qysds1" >��̯����*</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_12_1' value='' size='13' tabindex='46'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_12_2' value='' size='13' tabindex='47'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_12_3' value='' size='13' tabindex='48'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_12_4' value='' size='13' tabindex='49'></td>
							<input type='hidden' name='hc1' value='12'>
						</tr>
						<tr>
							<td class="2-td2-left" >13</td>
							<td class="2-td2-left-qysds1" >һ���ڵ��ڵķ������ʲ�</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_13_1' value='' size='13' tabindex='50'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_13_2' value='' size='13' tabindex='51'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_13_3' value='' size='13' tabindex='52'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_13_4' value='' size='13' tabindex='53'></td>
							<input type='hidden' name='hc1' value='13'>
						</tr>
						<tr>
							<td class="2-td2-left" >14</td>
							<td class="2-td2-left-qysds1" >���������ʲ�</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_14_1' value='' size='13' tabindex='54'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_14_2' value='' size='13' tabindex='55'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_14_3' value='' size='13' tabindex='56'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_14_4' value='' size='13' tabindex='57'></td>
							<input type='hidden' name='hc1' value='14'>
						</tr>
						<tr>
							<td class="2-td2-left" >15</td>
							<td class="2-td2-left-qysds1" >�ɹ����۽����ʲ�#</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_15_1' value='' size='13' tabindex='58'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_15_2' value='' size='13' tabindex='59'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_15_3' value='' size='13' tabindex='60'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_15_4' value='' size='13' tabindex='61'></td>
							<input type='hidden' name='hc1' value='15'>
						</tr>
						<tr>
							<td class="2-td2-left" >16</td>
							<td class="2-td2-left-qysds1" >����������Ͷ��#</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_16_1' value='' size='13' tabindex='62'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_16_2' value='' size='13' tabindex='63'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_16_3' value='' size='13' tabindex='64'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_16_4' value='' size='13' tabindex='65'></td>
							<input type='hidden' name='hc1' value='16'>
						</tr>
						<tr>
							<td class="2-td2-left" >17</td>
							<td class="2-td2-left-qysds1" >����Ӧ�տ�#</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_17_1' value='' size='13' tabindex='66'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_17_2' value='' size='13' tabindex='67'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_17_3' value='' size='13' tabindex='68'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_17_4' value='' size='13' tabindex='69'></td>
							<input type='hidden' name='hc1' value='17'>
						</tr>
						<tr>
							<td class="2-td2-left" >18</td>
							<td class="2-td2-left-qysds1" >���ڹ�ȨͶ��</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_18_1' value='' size='13' tabindex='70'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_18_2' value='' size='13' tabindex='71'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_18_3' value='' size='13' tabindex='72'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_18_4' value='' size='13' tabindex='73'></td>
							<input type='hidden' name='hc1' value='18'>
						</tr>
						<tr>
							<td class="2-td2-left" >19</td>
							<td class="2-td2-left-qysds1" >����ծȨͶ��*</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_19_1' value='' size='13' tabindex='74'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_19_2' value='' size='13' tabindex='75'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_19_3' value='' size='13' tabindex='76'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_19_4' value='' size='13' tabindex='77'></td>
							<input type='hidden' name='hc1' value='19'>
						</tr>
						<tr>
							<td class="2-td2-left" >20</td>
							<td class="2-td2-left-qysds1" >Ͷ���Է��ز�#</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_20_1' value='' size='13' tabindex='78'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_20_2' value='' size='13' tabindex='79'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_20_3' value='' size='13' tabindex='80'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_20_4' value='' size='13' tabindex='81'></td>
							<input type='hidden' name='hc1' value='20'>
						</tr>
						<tr>
							<td class="2-td2-left" >21</td>
							<td class="2-td2-left-qysds1" >�̶��ʲ�</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_21_1' value='' size='13' tabindex='82'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_21_2' value='' size='13' tabindex='83'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_21_3' value='' size='13' tabindex='84'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_21_4' value='' size='13' tabindex='85'></td>
							<input type='hidden' name='hc1' value='21'>
						</tr>
						<tr>
							<td class="2-td2-left" >22</td>
							<td class="2-td2-left-qysds1" >�ڽ�����</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_22_1' value='' size='13' tabindex='86'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_22_2' value='' size='13' tabindex='87'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_22_3' value='' size='13' tabindex='88'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_22_4' value='' size='13' tabindex='89'></td>
							<input type='hidden' name='hc1' value='22'>
						</tr>
						<tr>
							<td class="2-td2-left" >23</td>
							<td class="2-td2-left-qysds1" >��������</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_23_1' value='' size='13' tabindex='90'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_23_2' value='' size='13' tabindex='91'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_23_3' value='' size='13' tabindex='92'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_23_4' value='' size='13' tabindex='93'></td>
							<input type='hidden' name='hc1' value='23'>
						</tr>
						<tr>
							<td class="2-td2-left" >24</td>
							<td class="2-td2-left-qysds1" >�̶��ʲ�����</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_24_1' value='' size='13' tabindex='94'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_24_2' value='' size='13' tabindex='95'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_24_3' value='' size='13' tabindex='96'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_24_4' value='' size='13' tabindex='97'></td>
							<input type='hidden' name='hc1' value='24'>
						</tr>
						<tr>
							<td class="2-td2-left" >25</td>
							<td class="2-td2-left-qysds1" >�����ʲ�#</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_25_1' value='' size='13' tabindex='98'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_25_2' value='' size='13' tabindex='99'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_25_3' value='' size='13' tabindex='100'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_25_4' value='' size='13' tabindex='101'></td>
							<input type='hidden' name='hc1' value='25'>
						</tr>
						<tr>
							<td class="2-td2-left" >26</td>
							<td class="2-td2-left-qysds1" >�����ʲ�#</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_26_1' value='' size='13' tabindex='102'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_26_2' value='' size='13' tabindex='103'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_26_3' value='' size='13' tabindex='104'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_26_4' value='' size='13' tabindex='105'></td>
							<input type='hidden' name='hc1' value='26'>
						</tr>
						<tr>
							<td class="2-td2-left" >27</td>
							<td class="2-td2-left-qysds1" >�����ʲ�</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_27_1' value='' size='13' tabindex='106'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_27_2' value='' size='13' tabindex='107'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_27_3' value='' size='13' tabindex='108'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_27_4' value='' size='13' tabindex='109'></td>
							<input type='hidden' name='hc1' value='27'>
						</tr>
						<tr>
							<td class="2-td2-left" >28</td>
							<td class="2-td2-left-qysds1" >����֧��#</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_28_1' value='' size='13' tabindex='110'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_28_2' value='' size='13' tabindex='111'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_28_3' value='' size='13' tabindex='112'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_28_4' value='' size='13' tabindex='113'></td>
							<input type='hidden' name='hc1' value='28'>
						</tr>
						<tr>
							<td class="2-td2-left" >29</td>
							<td class="2-td2-left-qysds1" >����#</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_29_1' value='' size='13' tabindex='114'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_29_2' value='' size='13' tabindex='115'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_29_3' value='' size='13' tabindex='116'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_29_4' value='' size='13' tabindex='117'></td>
							<input type='hidden' name='hc1' value='29'>
						</tr>
						<tr>
							<td class="2-td2-left" >30</td>
							<td class="2-td2-left-qysds1" >���ڴ�̯����</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_30_1' value='' size='13' tabindex='118'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_30_2' value='' size='13' tabindex='119'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_30_3' value='' size='13' tabindex='120'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_30_4' value='' size='13' tabindex='121'></td>
							<input type='hidden' name='hc1' value='30'>
						</tr>
						<tr>
							<td class="2-td2-left" >31</td>
							<td class="2-td2-left-qysds1" >�����������ʲ�</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_31_1' value='' size='13' tabindex='122'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_31_2' value='' size='13' tabindex='123'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_31_3' value='' size='13' tabindex='124'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_31_4' value='' size='13' tabindex='125'></td>
							<input type='hidden' name='hc1' value='31'>
						</tr>
						<tr>
							<td class="2-td2-left" >32</td>
							<td class="2-td2-left-qysds1" >�ܼ�</td>
							<td class="2-td2-left" ><input type='text' name='zmjz' id='fgx_32_1' value='' size='13' tabindex='126'></td>
							<td class="2-td2-left" ><input type='text' name='jsjc' id='fgx_32_2' value='' size='13' tabindex='127'></td>
							<td class="2-td2-left" ><input type='text' name='jyjg' id='fgx_32_3' value='' size='13' tabindex='128'></td>
							<td class="2-td2-center" ><input type='text' name='zcczxy' id='fgx_32_4' value='' size='13' tabindex='129'></td>
							<input type='hidden' name='hc1' value='32'>
						</tr>
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center">
					<!-- Ӧ�ͻ�Ҫ���������
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
					-->
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
	<%@ include file="../../include/footernew.jsp"%>
</html:form>
</body>
</html>