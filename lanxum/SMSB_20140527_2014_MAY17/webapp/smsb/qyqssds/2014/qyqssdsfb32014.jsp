<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb3.web.QyqssdsFb3Form2014"%>
<%@ page import="java.util.HashMap"%>

<html>
<head>
<title>ʣ��Ʋ�����ͷ�����ϸ��</title>
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
		//ҳ���ʼ��ϸ����
		//var maxSize = document.forms[0].gdmc.length;
		var maxSize = document.getElementsByName("gdmc").length;
		//��ʼ���ı���onChange�¼�����
		initNumText("ljje",35);
		initNumText("tzbl",35);
		initNumText("tze",35);
		initNumText("ccje",35);
		initNumText("gxje",35);
		<%QyqssdsFb3Form2014 fb3Form=(QyqssdsFb3Form2014)request.getAttribute("qyqssdsFb3Form2014");
		if (fb3Form!=null && fb3Form.getDataList().size()>0){
			//��ϸ�����������
			int maxIndex = fb3Form.getMaxIndex();%> 
		    var test='<%=maxIndex%>';          
			//�����ϸ���������������ҳ��Ĭ����ϸ����,�򲹳�������
			if(maxSize < <%=maxIndex%>)
			{
				for(var i = 0; i<(<%=maxIndex%> - maxSize); i++)
				{
					doAddRow();
				}
			}
		<%
			for(int i=0;i<fb3Form.getDataList().size();i++){
				HashMap map=(HashMap)fb3Form.getDataList().get(i);
				String hc=(String)map.get("hc");
				String value=(String)map.get("ljje");
				%>
				obj = eval("document.getElementById('<%=hc%>_1')");
				obj.value = '<%=value%>';
				<% 
			}
		}
		%>
		
		var shzt ="<%=fb3Form.getSbShztbs()==null?"-1":fb3Form.getSbShztbs()%>";
		var sqlx ="<%=fb3Form.getSqlx()==null?"-1":fb3Form.getSqlx()%>";
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
				 //all[i].setAttribute("class","text-gray");
				 all[i].className="text-gray";
			 }
			 
		 }
	}
		<%/*����*/%>
		function doSave()
		{
			var gdmcList=document.getElementsByName("gdmc");
			var tzblList=document.getElementsByName("tzbl");
			var tzeList=document.getElementsByName("tze");
			var ccjeList=document.getElementsByName("ccje");
			var gxjeList=document.getElementsByName("gxje");
			var gdmcFlag =gdmcList[0].value=="";
			for(var i =0;i<gdmcList.length;i++){
				var gdmcFlag =gdmcList[i].value;
				var tzblFlag =tzblList[i].value;
				var tzeFlag =tzeList[i].value;
				var ccjeFlag =ccjeList[i].value;
				var gxjeFlag =gxjeList[i].value;
				<%/*-У�����Ƿ���д������Ҫôȫ���Ҫ����ȫ��-*/%>
				var flag =((gdmcFlag=="")&&(tzblFlag=="")&&(tzeFlag=="")&&(ccjeFlag=="")&&(gxjeFlag==""))||((gdmcFlag!="")&&(tzblFlag!="")&&(tzeFlag!="")&&(ccjeFlag!="")&&(gxjeFlag!=""));
				if(flag){
					continue;
				}else{
					var rownum =13+i;
					alert(rownum+"��û������������������д��");
					return false;
				}
							
			}
			doSubmitForm('Save');
		
		}
		<%/*���ڹ�ϵУ��*/%>
		function doCheck()
		{
			var gdmcList=document.getElementsByName("gdmc");
			var tzblList=document.getElementsByName("tzbl");
			var tzeList=document.getElementsByName("tze");
			var ccjeList=document.getElementsByName("ccje");
			var gxjeList=document.getElementsByName("gxje");
			var gdmcFlag =gdmcList[0].value=="";
			for(var i =0;i<gdmcList.length;i++){
				var gdmcFlag =gdmcList[i].value;
				var tzblFlag =tzblList[i].value;
				var tzeFlag =tzeList[i].value;
				var ccjeFlag =ccjeList[i].value;
				var gxjeFlag =gxjeList[i].value;
				<%/*-У�����Ƿ���д������Ҫôȫ���Ҫ����ȫ��-*/%>
				var flag =((gdmcFlag=="")&&(tzblFlag=="")&&(tzeFlag=="")&&(ccjeFlag=="")&&(gxjeFlag==""))||((gdmcFlag!="")&&(tzblFlag!="")&&(tzeFlag!="")&&(ccjeFlag!="")&&(gxjeFlag!=""));
				if(flag){
					continue;
				}else{
					var rownum =13+i;
					alert(rownum+"��û������������������д��");
					return false;
				}
							
			}
			doSubmitForm('Check');
		}
		<%/*���*/%>
		function doReset()
		{
			if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
			{
		   		for(var i=1; i < 13; i++){
					obj = eval("document.getElementById('" + i+"_1')");
					if(obj.readOnly!=true)
					obj.value = "";
		   		}

			   	// ���ʣ��Ʋ�������Ϣ
				//var gdmcArrLength = document.forms[0].gdmc.length;
			   	var gdmcArrLength = document.getElementsByName("gdmc").length;
			   	for(var j=13; j < 18; j++)
				{
					for(var k=0; k<gdmcArrLength; k++)
					{
						obj = eval("document.getElementById('" + j + "." + (k+1) + "_1')");
						
						obj.value = "";
						
					}							
			   	}
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
		
			<%/*������*/%>
	function doAddRow()
	{
		//��ȡ���з�֧������ϸ��Ϣ����
		var mxnum = document.getElementsByName("gdmc").length;
		//��ȡ��Ҫ����еı��
		var table = document.getElementById("table_mxxx");

		//Ȼ�󴴽���(TR����)
		var NewTr = document.createElement("tr");

		var hcIndex =parseInt(document.forms[0].maxRowIndex.value)+1;
		
		var xzTd1=document.createElement("<td class='2-td2-left' >");
		xzTd1.innerHTML="<input type='checkbox' tabindex='-1'  name='chkZJ' value=''>";
		NewTr.appendChild(xzTd1);
		
		var xzTd2=document.createElement("<td class='2-td2-left'>");
		xzTd2.innerHTML=hcIndex-2;
		NewTr.appendChild(xzTd2);
		
		//���ñ����
		for(var i=13; i<18; i++)
		{
			var NewTd;
			if(i == 17)
			{
				NewTd = document.createElement("<td  class='2-td2-center' nowrap>");
			}
			else
			{
				NewTd = document.createElement("<td  class='2-td2-left' nowrap>");
			}
			var end = "." + (mxnum + 1) + "_1";
			var id = i + end;
			//alert("id = " + id);
			switch(i)
			{
				case 13:
					NewTd.innerHTML = "<td><input type='text' name='gdmc' id=" + id + " value='' size='20' tabindex='23'></td>";
					break;
				case 14:
					NewTd.innerHTML = "<td  class='2-td2-left' nowrap><input type='text' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' name='tzbl' id=" + id + " style='text-align:right' value='' size='13' ></td>";
					break;
				case 15:
					NewTd.innerHTML = "<td  class='2-td2-left' nowrap><input type='text' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' name='tze' id=" + id + " style='text-align:right' value='' size='13' ></td>";
					break;
				case 16:
					NewTd.innerHTML = "<td  class='2-td2-left' nowrap><input type='text' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' name='ccje' id=" + id + " style='text-align:right' value='' size='13' ></td>";
					break;
				case 17:
					NewTd.innerHTML = "<td  class='2-td2-center' nowrap><input type='text' onkeyup = 'formateSign1(this)' onblur ='isNum(this , null, 9999999999999, null, 16, 2)' name='gxje' id=" + id + " style='text-align:right' value='' size='13' ></td>";
					break;		
			}
			//������������䵥Ԫ��
			NewTr.appendChild(NewTd);
		}
		//alert(NewTr.innerHTML);
		
		//�����
		var LastTr = table.rows[table.rows.length - 1];

		LastTr.parentNode.appendChild(NewTr);
		//���ÿ��е�Ԫ��Ŀ�����+1
		var fzjgqk = document.getElementById("syccfp");
		fzjgqk.rowSpan += 1;
		//����м� 1 
		document.forms[0].maxRowIndex.value=parseInt(document.forms[0].maxRowIndex.value)+1;
	}
	
	/**
	* ɾ����
	*/
	function RomoveRow()
	{
		//��ѡ��
		//var arrChk=document.all("chkZJ");
		var arrChk = document.getElementsByName("chkZJ");
		var list=document.getElementById("table_mxxx");
		var flag =true;
		
		//��ʼ����£�ֻ��һ��Checkboxʱ�����γ����飬��ֱ������
		//���ж��checkboxʱ�����ɶ��ɾ��Ϊһ��ʱ����ʵ���������������
		//���������ж�
		if(arrChk!=null){
			//if(arrChk.length==1){
				//if(arrChk.checked==true){
					//if(window.confirm("�ò���ֻ�Ǵ�ҳ����ɾ�����ӵ������У�ȷ��Ҫɾ��ѡ�е���������")){
					//	list.deleteRow(15);
					//	document.forms[0].maxRowIndex.value=parseInt(document.forms[0].maxRowIndex.value)-1;
  					//return false;
					//}else{
					//	arrChk.checked=false;
				//	}
			//	}else{
				//	alert("����ѡ��Ҫɾ���������У�");
				//	return false;
				//}
			//}
			
			if(arrChk.length>=1){
				for(var i=arrChk.length-1;i>=0;i--){
					if(arrChk[i].checked==true){
						flag=false;
						if(window.confirm("�ò���ֻ�Ǵ�ҳ����ɾ�����ӵ������У�ȷ��Ҫɾ��ѡ�е���������")){
							break;
						}else{
							return false;
						}
					}
				}
				
				if(flag){
					alert("����ѡ��Ҫɾ���������У�");
					return false;
				}
			}
			
			if(arrChk.length>=1){
				for(var i=arrChk.length-1;i>=0;i--){
					if(arrChk[i].checked==true){
						list.deleteRow(i+15);
						document.forms[0].maxRowIndex.value=parseInt(document.forms[0].maxRowIndex.value)-1;
					}
				}
				ResetHc();
				return false;
			}

		}
		return false;
	}
	
	function ResetHc(){
		var list=document.getElementById("table_mxxx");
		
		var maxrow=parseInt(document.forms[0].maxRowIndex.value);
		for(var i=15;i<parseInt(maxrow);i++){
			list.rows(i).cells(1).innerHTML=parseInt(i)-parseInt(1);
		}
	}
	</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInit()">
<%@include file="../../include/header.jsp"%>
<br>

<html:form action="/webapp/smsb/qyqssds/2014/qyqssdsFb3Action2014">
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
	<html:hidden property="sbShztbs" />
	<html:hidden property="sqlx" />
	
	<input type="hidden" name="maxRowIndex" value="15">
	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">ʣ��Ʋ�����ͷ�����ϸ��</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�걨����:<bean:write
				name="qyqssdsFb3Form2014" property="tbrq" scope="request" filter="true" />
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
				name="qyqssdsFb3Form2014" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="qyqssdsFb3Form2014" property="nsrmc" scope="request" filter="true" />&nbsp;
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
					<table id="table_mxxx" border="1" cellspacing="0"
						class="table-99" align="center">
						<tr>
							<td class="2-td1-left" nowrap>���</td>
							<td colspan="2" class="2-td1-left" nowrap>�д�</td>
							<td colspan="3" class="2-td1-left" nowrap>��Ŀ</td>
							<td colspan="2" class="2-td1-center" nowrap>���</td>
						</tr>
						<tr>
							<td rowspan="12" class="2-td2-left" nowrap>ʣ��Ʋ�����</td>
							<td colspan="2" class="2-td2-left" nowrap>1<input type="hidden" name="hc" value="1" id="hc_1"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>�ʲ��ɱ��ּ�ֵ���׼۸�</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='1_1' value='' size='13' tabindex='1'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>2<input type="hidden" name="hc" value="2" id="hc_2"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>�������</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='2_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>3<input type="hidden" name="hc" value="3" id="hc_3"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>ְ������</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='3_1' value='' size='13' tabindex='3'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>4<input type="hidden" name="hc" value="4" id="hc_4"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>��ᱣ�շ���</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='4_1' value='' size='13' tabindex='4'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>5<input type="hidden" name="hc" value="5" id="hc_5"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>����������</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='5_1' value='' size='13' tabindex='5'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>6<input type="hidden" name="hc" value="6" id="hc_6"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>����˰�𼰸���</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='6_1' value='' size='13' tabindex='6'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>7<input type="hidden" name="hc" value="7" id="hc_7"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>��������˰��</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='7_1' value='' size='13' tabindex='7'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>8<input type="hidden" name="hc" value="8" id="hc_8"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>��ǰ���Ƿ˰��</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='8_1' value='' size='13' tabindex='8'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>9<input type="hidden" name="hc" value="9" id="hc_9"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>����ծ��</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='9_1' value='' size='13' tabindex='9'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>10<input type="hidden" name="hc" value="10" id="hc_10"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>ʣ��Ʋ���1��2������9��</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='10_1' value='' size='13' tabindex='10'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>11<input type="hidden" name="hc" value="11" id="hc_11"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>���У��ۼ�ӯ�๫��</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='11_1' value='' size='13' tabindex='11'></td>
						</tr>
						<tr>
							<td colspan="2" class="2-td2-left" nowrap>12<input type="hidden" name="hc" value="12" id="hc_12"></td>
							<td colspan="3" class="2-td2-left-qysds1" nowrap>�ۼ�δ��������</td>
							<td colspan="2" class="2-td2-center" nowrap><input type='text' name='ljje'id='12_1' value='' size='13' tabindex='12'></td>
						</tr>
						<tr>
							<td  id="syccfp" rowspan="6" class="2-td2-left" nowrap>ʣ��Ʋ�����</td>
							<td  colspan="2" class="2-td2-left" nowrap>&nbsp;</td>
							<td  class="2-td2-left" nowrap>�ɶ�����</td>
							<td  class="2-td2-left" nowrap>����������ҵȨ����Ͷ�ʱ�����%��</td>
							<td  class="2-td2-left" nowrap>Ͷ�ʶ�</td>
							<td  class="2-td2-left" nowrap>����ĲƲ����</td>
							<td  class="2-td2-center" nowrap>���У�ȷ��Ϊ��Ϣ���</td>
						</tr>
						<tr>
							<td  class="2-td2-left" nowrap>&nbsp;&nbsp;</td>
							<td  class="2-td2-left" nowrap>13</td>
							<td  class="2-td2-left" nowrap><input type='text' name='gdmc' id='13.1_1' value='' size='20' tabindex='13' ></td>
							<td  class="2-td2-left" nowrap><input type='text' name='tzbl' id='14.1_1' value='' size='13' tabindex='14' style="text-align:right"></td>
							<td  class="2-td2-left" nowrap><input type='text' name='tze' id='15.1_1' value='' size='13' tabindex='15' style="text-align:right"></td>
							<td  class="2-td2-left" nowrap><input type='text' name='ccje' id='16.1_1' value='' size='13' tabindex='16' style="text-align:right"></td>
							<td  class="2-td2-center" nowrap><input type='text' name='gxje' id='17.1_1' value='' size='13' tabindex='17' style="text-align:right"></td>
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
					-->
					<input type="image" accesskey="a"
						onClick="doAddRow();return false;"
						onMouseOver="MM_swapImage('zengjia','','../../../images/add_2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" alt="������"
						src="../../../images/add_1.jpg" width="79"
						height="22" id="zengjia" style="cursor:hand">
					&nbsp;&nbsp;
					<input type="image" accesskey="a"
						onClick="RomoveRow();return false;"
						onMouseOver="MM_swapImage('shanchuhang','','../../../images/delete_2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" alt="ɾ����"
						src="../../../images/delete_1.jpg" width="79"
						height="22" id="shanchuhang" style="cursor:hand">
					&nbsp;&nbsp;<input type="image" style="cursor:hand" tabIndex="-1"
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
	<br>
	<%@ include file="../../include/footernew.jsp"%>
</html:form>
</body>
</html>
