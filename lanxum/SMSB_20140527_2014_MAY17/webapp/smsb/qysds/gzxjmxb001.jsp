<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.gzxjmxb.web.GzxjmxbForm"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<%@ page import="java.util.HashMap"%>

<html>
<head>
<title>����н������������ϸ��</title>
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
	src="../../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/smsb_common.js"></script>
<script language="JavaScript" src="../../js/DTable.js"></script>
<script language=JavaScript src="../../js/reader.js"></script>
<script language=JavaScript src="../../js/compute.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/qysdsnew.js"></script>


</head>
<script language="JavaScript">
	
	<%/*��ʼ��*/%>
	function doInit()
	{
	<%
	GzxjmxbForm gzxjmxbForm=(GzxjmxbForm)request.getAttribute("gzxjmxbForm");	
	if (gzxjmxbForm!=null && gzxjmxbForm.getDataList().size()>0)
	{
		   if(gzxjmxbForm.getGzlx().equals(CodeConstant.SMSB_GZGLLX_FGX))
		   {
		%> 
		        //��ʼ���ı���onChange�¼�����
		        initNumText("gzxj",11);
		        initNumText("ghjf",11);
		        initNumText("zgflf",11);
		        initNumText("zgjyjf",11);
		        initNumText("xj",11);
		        
		        //���ں�׼��˰������ֻ����������
		        document.getElementById('fgx_8_1').onchange = function(){return isNum(getCellObject() , 0, 9999999999999, null, 16, 0);}
		        
		   		document.getElementById('layerFGX').style.display="block"; 				
	 			document.getElementById('layerGX').style.display="none";
	 			//����ҳ���9�е�չ����ʽ
				changeGray();	
	 				 
		   	 <%
			   	 for(int i=0;i<gzxjmxbForm.getDataList().size();i++)
				 {
					HashMap map=(HashMap)gzxjmxbForm.getDataList().get(i);
					int hc1=Integer.parseInt((String)map.get("hc1"));
					String gzxj=(String)map.get("gzxj");
					String ghjf=(String)map.get("ghjf");
					String zgflf=(String)map.get("zgflf");
					String zgjyjf=(String)map.get("zgjyjf");
					String xj=(String)map.get("xj");
			%>	  			
				obj1=eval("document.getElementById('fgx_<%=hc1%>_1')");
				obj1.value='<%=gzxj%>';			
				obj2=eval("document.getElementById('fgx_<%=hc1%>_2')");
				obj2.value='<%=ghjf%>';
				obj3=eval("document.getElementById('fgx_<%=hc1%>_3')");
				obj3.value='<%=zgflf%>';
				obj4=eval("document.getElementById('fgx_<%=hc1%>_4')");
				obj4.value='<%=zgjyjf%>';
				obj5=eval("document.getElementById('fgx_<%=hc1%>_5')");
				obj5.value='<%=xj%>';
							
			<% 
				}
			}
			if(gzxjmxbForm.getGzlx().equals(CodeConstant.SMSB_GZGLLX_GX))
			{	
			%>	
			    //��ʼ���ı���onChange�¼�����
		        initNumText("L1",15);
		        initNumText("L2",15);
		        initNumText("L3",15);
		        initNumText("L4",15);
		        initNumText("L5",15);
				document.getElementById('layerFGX').style.display="none";
	 			document.getElementById('layerGX').style.display="block";
				
			 		
			<%
				for(int i=0;i<gzxjmxbForm.getDataList().size();i++)
				{
					HashMap map=(HashMap)gzxjmxbForm.getDataList().get(i);
					int hc2=Integer.parseInt((String)map.get("hc2"));
					String s1=(String)map.get("L1");
					String s2=(String)map.get("L2");
					String s3=(String)map.get("L3");
					String s4=(String)map.get("L4");
					String s5=(String)map.get("L5");			
			%>			  
			 	obj6=eval("document.getElementById('gx_<%=hc2%>_1')");
				obj6.value='<%=s1%>';			
				obj7=eval("document.getElementById('gx_<%=hc2%>_2')");
				obj7.value='<%=s2%>';
				obj8=eval("document.getElementById('gx_<%=hc2%>_3')");
				obj8.value='<%=s3%>';
				obj9=eval("document.getElementById('gx_<%=hc2%>_4')");
				obj9.value='<%=s4%>';
				obj10=eval("document.getElementById('gx_<%=hc2%>_5')");
				obj10.value='<%=s5%>';
				
			<%		
				}
			}
		}
	%>
	
	//alert("gzglxs_gzxj========"+document.forms[0].gzglxs_gzxj.value);
	}
	<%/*���*/%>
	function doReset()
	{
	  	var gzlx=document.forms[0].gzlx.value;
	    if(confirm("ȷ���Ƿ������ǰ���ݣ�")){ 
	      	if('<%=CodeConstant.SMSB_GZGLLX_FGX%>'==gzlx){ 
		   	       for(var i=1; i<=11; i++)
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
				      obj = eval("document.getElementById('fgx_"+i+"_5')");
				          if (obj.readOnly!=true)
					         obj.value = "";
					}
			}else {
               for(var i=1; i<=15; i++){
				 	obj = eval("document.getElementById('gx_"+i+"_1')");
				     	if (obj.readOnly!=true)
					 		obj.value = "";
					obj = eval("document.getElementById('gx_"+i+"_2')");
				     	if (obj.readOnly!=true)
					 		obj.value = "";
					obj = eval("document.getElementById('gx_"+i+"_3')");
				     	if (obj.readOnly!=true)
							obj.value = "";
				 	obj = eval("document.getElementById('gx_"+i+"_4')");
				     	if (obj.readOnly!=true)
					 		obj.value = "";
				 	obj = eval("document.getElementById('gx_"+i+"_5')");
				     	if (obj.readOnly!=true)
					 		obj.value = "";
				}
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
	<%/*����ҳ���9�е�չ����ʽ*/%>
	function changeGray(){
		if(document.forms[0].gzglxs_gzxj.value=="<%=CodeConstant.GZGLXS02%>"){
			var obj = document.getElementById("fgx_9_1");
			obj.className='text-gray';
			obj.readOnly=true;
			obj.value='';
			obj.tabIndex='-1';
		}else{
			var obj = document.getElementById("fgx_9_1");
			obj.className='';
			obj.readOnly=false;
			obj.tabIndex='2';
		}
		InitTabindex(document.forms[0]);
	}

</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInit()">
<%@ include file="../include/header.jsp"%>


<html:form action="/webapp/smsb/qysds/gzxjmxbAction">
	<html:hidden property="actionType" />
	<html:hidden property="gzlx" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />

	<table width="96%"  cellspacing="0" class="table-99" onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">����н������������ϸ��</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="gzxjmxbForm" property="sbrq" scope="request" filter="true" />
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
				name="gzxjmxbForm" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="gzxjmxbForm" property="nsrmc" scope="request" filter="true" />&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
			<logic:equal name="gzxjmxbForm" property="gzlx" value="<%=CodeConstant.SMSB_GZGLLX_FGX%>">
			���ʹ�����ʽ��<html:select property="gzglxs_gzxj" onchange = "changeGray()">
				<html:option value="<%=CodeConstant.GZGLXS01%>">��˰����</html:option>
				<html:option value="<%=CodeConstant.GZGLXS02%>">ȫ��۳�</html:option>
			</html:select>
			</logic:equal>
			<logic:equal name="gzxjmxbForm" property="gzlx" value="<%=CodeConstant.SMSB_GZGLLX_GX%>">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</logic:equal>		
			</td>

		</tr>
		<tr>
			<td class="1-td2" colspan="7">
			<TABLE class="table-99" >
				<TR>
					<TD>


					<div id='layerFGX' style="display:block">

					<table width="96%">
						<tr>
							<td rowspan="2" class="2-td1-left">��&nbsp;&nbsp;��</td>
							<td rowspan="2" class="2-td1-left">��&nbsp;&nbsp;&nbsp;&nbsp;Ŀ</td>
							<td class="2-td1-left">&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td colspan="4" class="2-td1-center">�������</td>
						</tr>
						<tr>
							<td class="2-td2-left" >����н��</td>
							<td class="2-td2-left" >���ᾭ��</td>
							<td class="2-td2-left" >ְ��������</td>
							<td class="2-td2-left" >ְ����������</td>
							<td class="2-td2-center" >С&nbsp;&nbsp;��</td>
						</tr>
						<tr>
							<td class="2-td2-left" >&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td class="2-td2-left" >&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td class="2-td2-left" >1</td>
							<td class="2-td2-left" >2</td>
							<td class="2-td2-left" >3</td>
							<td class="2-td2-left" >4</td>
							<td class="2-td2-center" >5=2+3+4</td>
						</tr>
						<tr>
							<td class="2-td2-left" >1</td>
							<td class="2-td2-left-qysds1" >�����ɱ�</td>
							<td class="2-td2-left" ><input type='text'
								name='gzxj' id='fgx_1_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='ghjf' id='fgx_1_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgflf' id='fgx_1_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgjyjf' id='fgx_1_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-center" ><input type='text'
								name='xj' id='fgx_1_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc1' value='1'>
						</tr>
						<tr>
							<td class="2-td2-left" >2</td>
							<td class="2-td2-left-qysds1" >�������</td>
							<td class="2-td2-left" ><input type='text'
								name='gzxj' id='fgx_2_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='ghjf' id='fgx_2_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgflf' id='fgx_2_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgjyjf' id='fgx_2_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-center" ><input type='text'
								name='xj' id='fgx_2_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc1' value='2'>
						</tr>
						<tr>
							<td class="2-td2-left" >3</td>
							<td class="2-td2-left-qysds1" >����(Ӫҵ)����</td>
							<td class="2-td2-left" ><input type='text'
								name='gzxj' id='fgx_3_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='ghjf' id='fgx_3_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgflf' id='fgx_3_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgjyjf' id='fgx_3_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-center" ><input type='text'
								name='xj' id='fgx_3_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc1' value='3'>
						</tr>
						<tr>
							<td class="2-td2-left" >4</td>
							<td class="2-td2-left-qysds1">�������</td>
							<td class="2-td2-left" ><input type='text'
								name='gzxj' id='fgx_4_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='ghjf' id='fgx_4_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgflf' id='fgx_4_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgjyjf' id='fgx_4_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='xj' id='fgx_4_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc1' value='4'>
						</tr>
						<tr>
							<td class="2-td2-left" >5</td>
							<td class="2-td2-left-qysds1" >�ڽ�����</td>
							<td class="2-td2-left" ><input type='text'
								name='gzxj' id='fgx_5_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='ghjf' id='fgx_5_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgflf' id='fgx_5_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgjyjf' id='fgx_5_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-center" ><input type='text'
								name='xj' id='fgx_5_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc1' value='5'>
						</tr>
						<tr>
							<td class="2-td2-left" >6</td>
							<td class="2-td2-left-qysds1" >��&nbsp;&nbsp;��</td>
							<td class="2-td2-left" ><input type='text'
								name='gzxj' id='fgx_6_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='ghjf' id='fgx_6_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgflf' id='fgx_6_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgjyjf' id='fgx_6_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='xj' id='fgx_6_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc1' value='6'>
						</tr>
						<tr>
							<td class="2-td2-left" >7</td>
							<td class="2-td2-left-qysds1" >ʵ�ʷ��Ŷ�</td>
							<td class="2-td2-left" ><input type='text'
								name='gzxj' id='fgx_7_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='ghjf' id='fgx_7_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgflf' id='fgx_7_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgjyjf' id='fgx_7_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='xj' id='fgx_7_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc1' value='7'>
						</tr>
						<tr>
							<td class="2-td2-left" >8</td>
							<td class="2-td2-left-qysds1" >���ں�׼��˰����</td>
							<td colspan="2"  class="2-td2-left">&nbsp;</td>
							<input type='hidden' name='zgflf' id='fgx_8_3' value=''>
							<input type='hidden' name='ghjf' id='fgx_8_2' value=''>
							<td class="2-td2-left" ><input type='text'
								name='gzxj' id='fgx_8_1' value='' size='8' tabindex='2'></td>
							<td colspan="2"  class="2-td2-center">&nbsp;</td>
							<input type='hidden' name='zgjyjf' id='fgx_8_4' value=''>
							<input type='hidden' name='xj' id='fgx_8_5' value=''>
							<input type='hidden' name='hc1' value='8'>
						</tr>
						<tr>
							<td class="2-td2-left" >9</td>
							<td class="2-td2-left-qysds1" >��˰���ʱ�׼����д���¼�˰���ʱ�׼��ʵ�ʾ�Ӫ�·ݣ�</td>
							<td colspan="2"  class="2-td2-left">&nbsp;</td>
							<input type='hidden' name='zgflf' id='fgx_9_3' value=''>
							<input type='hidden' name='ghjf' id='fgx_9_2' value=''>
							<td class="2-td2-left" ><input type='text'
								name='gzxj' id='fgx_9_1' value='' size='8' tabindex='2'></td>
							<td colspan="2"  class="2-td2-center">&nbsp;</td>
							<input type='hidden' name='zgjyjf' id='fgx_9_4' value=''>
							<input type='hidden' name='xj' id='fgx_9_5' value=''>
							<input type='hidden' name='hc1' value='9'>
						</tr>
						<tr>
							<td class="2-td2-left" >10</td>
							<td class="2-td2-left-qysds1" >����˰ǰ�۳��޶�</td>
							<td class="2-td2-left" ><input type='text'
								name='gzxj' id='fgx_10_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='ghjf' id='fgx_10_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgflf' id='fgx_10_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgjyjf' id='fgx_10_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='xj' id='fgx_10_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc1' value='10'>
						</tr>
						<tr>
							<td class="2-td2-left" >11</td>
							<td class="2-td2-left-qysds1" >������˰������</td>
							<td class="2-td2-left" ><input type='text'
								name='gzxj' id='fgx_11_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='ghjf' id='fgx_11_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgflf' id='fgx_11_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='zgjyjf' id='fgx_11_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='xj' id='fgx_11_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc1' value='11'>
						</tr>
					</table>
					</div>
					<div id='layerGX' style="display:none">
					<table width="96%">
						<tr>
							<td rowspan="2" class="2-td1-left">��&nbsp;&nbsp;��</td>
							<td rowspan="2" class="2-td1-left">��&nbsp;&nbsp;&nbsp;&nbsp;Ŀ</td>
							<td class="2-td1-left">&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td colspan="4" class="2-td1-center">�������</td>
						</tr>
						<tr>
							<td class="2-td2-left" >����н��</td>
							<td class="2-td2-left" >���ᾭ��</td>
							<td class="2-td2-left" >ְ��������</td>
							<td class="2-td2-left" >ְ����������</td>
							<td class="2-td2-center" >С&nbsp;&nbsp;��</td>
						</tr>
						<tr>
							<td class="2-td2-left" >&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td class="2-td2-left" >&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td class="2-td2-left" >1</td>
							<td class="2-td2-left" >2</td>
							<td class="2-td2-left" >3</td>
							<td class="2-td2-left" >4</td>
							<td class="2-td2-center" >5=2+3+4</td>
						</tr>
						<tr>
							<td class="2-td2-left" >1</td>
							<td class="2-td2-left-qysds1" >�����ɱ�</td>
							<td class="2-td2-left" ><input type='text'
								name='L1' id='gx_1_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L2' id='gx_1_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='L3' id='gx_1_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L4' id='gx_1_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-center" ><input type='text'
								name='L5' id='gx_1_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc2' value='1'>
						</tr>
						<tr>
							<td class="2-td2-left" >2</td>
							<td class="2-td2-left-qysds1" >�������</td>
							<td class="2-td2-left" ><input type='text'
								name='L1' id='gx_2_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L2' id='gx_2_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='L3' id='gx_2_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L4' id='gx_2_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-center" ><input type='text'
								name='L5' id='gx_2_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc2' value='2'>
						</tr>
						<tr>
							<td class="2-td2-left" >3</td>
							<td class="2-td2-left-qysds1" >����(Ӫҵ)����</td>
							<td class="2-td2-left" ><input type='text'
								name='L1' id='gx_3_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L2' id='gx_3_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='L3' id='gx_3_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L4' id='gx_3_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-center" ><input type='text'
								name='L5' id='gx_3_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc2' value='3'>
						</tr>
						<tr>
							<td class="2-td2-left" >4</td>
							<td class="2-td2-left-qysds1" >�������</td>
							<td class="2-td2-left" ><input type='text'
								name='L1' id='gx_4_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L2' id='gx_4_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L3' id='gx_4_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L4' id='gx_4_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='L5' id='gx_4_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc2' value='4'>
						</tr>
						<tr>
							<td class="2-td2-left" >5</td>
							<td class="2-td2-left-qysds1" >�ڽ�����</td>
							<td class="2-td2-left" ><input type='text'
								name='L1' id='gx_5_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L2' id='gx_5_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='L3' id='gx_5_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L4' id='gx_5_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-center" ><input type='text'
								name='L5' id='gx_5_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc2' value='5'>
						</tr>
						<tr>
							<td class="2-td2-left" >6</td>
							<td class="2-td2-left-qysds1" >��&nbsp;&nbsp;��</td>
							<td class="2-td2-left" ><input type='text'
								name='L1' id='gx_6_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L2' id='gx_6_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L3' id='gx_6_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L4' id='gx_6_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='L5' id='gx_6_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc2' value='6'>
						</tr>
						<tr>
							<td class="2-td2-left" >7</td>
							<td class="2-td2-left-qysds1" >ʵ�ʷ��Ŷ�</td>
							<td class="2-td2-left" ><input type='text'
								name='L1' id='gx_7_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L2' id='gx_7_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L3' id='gx_7_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L4' id='gx_7_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='L5' id='gx_7_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc2' value='7'>
						</tr>
						<tr>
							<td class="2-td2-left" >8</td>
							<td class="2-td2-left-qysds1" >���У���׼�Ĺ�Ч�ҹ������ܶ�</td>
							<td class="2-td2-left" ><input type='text'
								name='L1' id='gx_8_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L2' id='gx_8_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L3' id='gx_8_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L4' id='gx_8_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='L5' id='gx_8_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc2' value='8'>
						</tr>
						<tr>
							<td class="2-td2-left" >9</td>
							<td class="2-td2-left-qysds1" >���У����ý����</td>
							<td class="2-td2-left" ><input type='text'
								name='L1' id='gx_9_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L2' id='gx_9_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='L3' id='gx_9_3' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='L4' id='gx_9_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-center" ><input type='text'
								name='L5' id='gx_9_5' value='' size='1' readonly='true'
								class='text-noborder'></td>
							<input type='hidden' name='hc2' value='9'>
						</tr>
						<tr>
							<td class="2-td2-left" >10</td>
							<td class="2-td2-left-qysds1" >������˰������</td>
							<td class="2-td2-left" ><input type='text'
								name='L1' id='gx_10_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L2' id='gx_10_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L3' id='gx_10_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L4' id='gx_10_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='L5' id='gx_10_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc2' value='10'>
						</tr>
						<tr>
							<td class="2-td2-left" >11</td>
							<td class="2-td2-left-qysds1" >������˰������</td>
							<td class="2-td2-left" ><input type='text'
								name='L1' id='gx_11_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L2' id='gx_11_2' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L3' id='gx_11_3' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L4' id='gx_11_4' value='' size='8' tabindex='2'></td>
							<td class="2-td2-center" ><input type='text'
								name='L5' id='gx_11_5' value='' size='8' tabindex='2'></td>
							<input type='hidden' name='hc2' value='11'>
						</tr>
						<tr>
							<td class="2-td2-left" >12</td>
							<td class="2-td2-left-qysds1" >��������</td>
							<td class="2-td2-left" ><input type='text'
								name='L1' id='gx_12_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L2' id='gx_12_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='L3' id='gx_12_3' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='L4' id='gx_12_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-center" ><input type='text'
								name='L5' id='gx_12_5' value='' size='1' size='1'
								readonly='true' class='text-noborder'></td>
							<input type='hidden' name='hc2' value='12'>
						</tr>
						<tr>
							<td class="2-td2-left" >13</td>
							<td class="2-td2-left-qysds1" >�ӣ���ǰ��Ƚ����</td>
							<td class="2-td2-left" ><input type='text'
								name='L1' id='gx_13_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L2' id='gx_13_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='L3' id='gx_13_3' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='L4' id='gx_13_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-center" ><input type='text'
								name='L5' id='gx_13_5' value='' size='1' readonly='true'
								class='text-noborder'></td>
							<input type='hidden' name='hc2' value='13'>
						</tr>
						<tr>
							<td class="2-td2-left" >14</td>
							<td class="2-td2-left-qysds1" >�������궯�ý����</td>
							<td class="2-td2-left" ><input type='text'
								name='L1' id='gx_14_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L2' id='gx_14_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='L3' id='gx_14_3' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='L4' id='gx_14_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-center" ><input type='text'
								name='L5' id='gx_14_5' value='' size='1' size='1'
								readonly='true' class='text-noborder'></td>
							<input type='hidden' name='hc2' value='14'>
						</tr>
						<tr>
							<td class="2-td2-left" >15</td>
							<td class="2-td2-left-qysds1" >�����ۼƽ����</td>
							<td class="2-td2-left" ><input type='text'
								name='L1' id='gx_15_1' value='' size='8' tabindex='2'></td>
							<td class="2-td2-left" ><input type='text'
								name='L2' id='gx_15_2' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='L3' id='gx_15_3' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-left" ><input type='text'
								name='L4' id='gx_15_4' value='*' size='1' readonly='true'
								class='text-noborder'></td>
							<td class="2-td2-center" ><input type='text'
								name='L5' id='gx_15_5' value='' size='1' readonly='true'
								class='text-noborder'></td>
							<input type='hidden' name='hc2' value='15'>
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
						onClick="doCheck();return false;" accesskey="d" value="����У��"
						alt="���ڹ�ϵУ��"
						onMouseOver="MM_swapImage('jiaoyan','','../../images/b-bdjyd2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" src="../../images/b-bdjyd1.jpg"
						 id="jiaoyan" /> &nbsp;&nbsp; <input type="image"
						style="cursor:hand" tabIndex="-1"
						onClick="doDelete();return false;" accesskey="x" value="ȫ��ɾ��"
						alt="ɾ�������������ݣ��Ҳ��ɻָ�"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" 
						id="shanchu" /> &nbsp;&nbsp; <input type="image" value="����"
						alt="���ص���ҵ����˰�걨��ҳ��" style="cursor:hand" tabIndex="-1"
						onClick="doExit();return false;"
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
	<%@ include file="../include/footernew.jsp"%>
</html:form>
</body>
</html>
