<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.web.QyqssdsZbForm2014"%>
<%@ page import="java.util.HashMap"%>

<html>
<head>
<title>�л����񹲺͹���ҵ��������˰�걨��</title>
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
		
		//��ʼ���ı���onChange�¼�����
		initNumText("ljje",35);
		<%QyqssdsZbForm2014 zbForm=(QyqssdsZbForm2014)request.getAttribute("qyqssdsZbForm2014");
			if (zbForm!=null && zbForm.getDataList().size()>0){
				for(int i=0;i<zbForm.getDataList().size();i++){
					HashMap map=(HashMap)zbForm.getDataList().get(i);
					int hc=Integer.parseInt((String)map.get("hc"));
					String ljje=(String)map.get("ljje");%>
					obj = eval("document.getElementById('<%=hc%>_1')");
					obj.value = '<%=ljje%>';
					<% 
				}
			}
			%>
			var shzt = "<%=zbForm.getSbShztbs()==null?"-1":zbForm.getSbShztbs()%>";
			var sqlx = "<%=zbForm.getSqlx()==null?"-1":zbForm.getSqlx()%>";
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
				 }
				 
			 }
		}
		<%/*У��18���Ƿ�Ϊ�գ����Ϊ�յĻ�����Ϊ0.00*/%>
		function doCheckRow(){
			var str=document.getElementById("18_1").value;
			if(str==""){
				document.getElementById("18_1").value="0.00";
			}
		}
		<%/*����*/%>
		function doSave()
		{
			doCheckRow();
			doSubmitForm('Save');
		
		}
		<%/*���ڹ�ϵУ��*/%>
		function doCheck()
		{
			doCheckRow();
			doSubmitForm('Check');
		}
		<%/*���*/%>
		function doReset()
		{
			if(confirm("ȷ���Ƿ������ǰ���ݣ�"))
			{
		   		for(var i=1; i < 19; i++){
					obj = eval("document.getElementById('" + i+"_1')");
					if(obj.readOnly!=true)
					obj.value = "";
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
		
	</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInit()">
<%@include file="../../include/header.jsp"%>
<br>

<html:form action="/webapp/smsb/qyqssds/2014/qyqssdsZbAction2014">
	<html:hidden property="actionType" />
	<html:hidden property="nextTableURL" />
	<html:hidden property="previousTableURL" />
	<html:hidden property="skssq" />
	<html:hidden property="sbShztbs" />
	<html:hidden property="sqlx" />

	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="reponseEnterKey()">
		<tr>
			<td class="1-td1" colspan="7">�л����񹲺͹���ҵ��������˰�걨��</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;�����:<bean:write
				name="qyqssdsZbForm2014" property="tbrq" scope="request" filter="true" />
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
				name="qyqssdsZbForm2014" property="jsjdm" scope="request" filter="true" />&nbsp;&nbsp;&nbsp;&nbsp;��˰�����ƣ�<bean:write
				name="qyqssdsZbForm2014" property="nsrmc" scope="request" filter="true" />&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7">&nbsp;&nbsp;˰��������:<bean:write
				name="qyqssdsZbForm2014" property="skssq" scope="request" filter="true" />&nbsp;
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
							<td class="2-td1-left" nowrap>���</td>
							<td class="2-td1-left" nowrap>�д�</td>
							<td class="2-td1-left" nowrap>��Ŀ</td>
							<td class="2-td1-center" nowrap>���</td>
						</tr>
						<tr>
							<td rowspan="11" class="2-td2-left" nowrap>Ӧ��˰���ö����</td>
							<td class="2-td2-left" nowrap>1<input type="hidden" name="hc" value="1" id="hc_1"></td>
							<td class="2-td2-left-qysds1" nowrap>�ʲ��������棨���һ��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='1_1' value='' size='13' tabindex='1'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>2<input type="hidden" name="hc" value="2" id="hc_2"></td>
							<td class="2-td2-left-qysds1" nowrap>��ծ�峥���棨������</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='2_1' value='' size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>3<input type="hidden" name="hc" value="3" id="hc_3"></td>
							<td class="2-td2-left-qysds1" nowrap>�������</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='3_1' value='' size='13' tabindex='3'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>4<input type="hidden" name="hc" value="4" id="hc_4"></td>
							<td class="2-td2-left-qysds1" nowrap>����˰�𼰸���</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='4_1' value='' size='13' tabindex='4'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>5<input type="hidden" name="hc" value="5" id="hc_5"></td>
							<td class="2-td2-left-qysds1" nowrap>�������û�֧��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='5_1' value='' size='13' tabindex='5'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>6<input type="hidden" name="hc" value="6" id="hc_6"></td>
							<td class="2-td2-left-qysds1" nowrap>�������ã�1+2-3-4+5��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='6_1' value='' size='13' tabindex='6'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>7<input type="hidden" name="hc" value="7" id="hc_7"></td>
							<td class="2-td2-left-qysds1" nowrap>��˰����</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='7_1' value='' size='13' tabindex='7'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>8<input type="hidden" name="hc" value="8" id="hc_8"></td>
							<td class="2-td2-left-qysds1" nowrap>����˰����</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='8_1' value='' size='13' tabindex='8'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>9<input type="hidden" name="hc" value="9" id="hc_9"></td>
							<td class="2-td2-left-qysds1" nowrap>������˰����</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='9_1' value='' size='13' tabindex='9'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>10<input type="hidden" name="hc" value="10" id="hc_10"></td>
							<td class="2-td2-left-qysds1" nowrap>�ֲ���ǰ��ȿ���</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='10_1' value='' size='13' tabindex='10'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>11<input type="hidden" name="hc" value="11" id="hc_11"></td>
							<td class="2-td2-left-qysds1" nowrap>Ӧ��˰���ö6-7-8-9-10��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='11_1' value='' size='13' tabindex='11'></td>
						</tr>
						
						<tr>
							<td rowspan="2" class="2-td2-left" nowrap>Ӧ��˰���ö����</td>
							<td class="2-td2-left" nowrap>12 <input type="hidden" name="hc" value="12" id="hc_12"></td>
							<td class="2-td2-left-qysds1" nowrap>˰�ʣ�25%��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje' id='12_1' value='25' readOnly='true' class='text-noborder' style='text-align:center' size='1' tabindex='-1'>%</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>13<input type="hidden" name="hc" value="13" id="hc_13"></td>
							<td class="2-td2-left-qysds1" nowrap>Ӧ������˰�11��12��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='13_1' value='' size='13' tabindex='13'></td>
						</tr>
						
						<tr>
							<td rowspan="5" class="2-td2-left" nowrap>Ӧ��˰���ö����</td>
							<td class="2-td2-left" nowrap>14<input type="hidden" name="hc" value="14" id="hc_14"></td>
							<td class="2-td2-left-qysds1" nowrap>�����⣩��ҵ����˰��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='14_1' value='' size='13' tabindex='14'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>15<input type="hidden" name="hc" value="15" id="hc_15"></td>
							<td class="2-td2-left-qysds1" nowrap>����Ӧ������˰��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='15_1' value='' size='13' tabindex='15'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>16<input type="hidden" name="hc" value="16" id="hc_16"></td>
							<td class="2-td2-left-qysds1" nowrap>������ʵ��Ӧ������˰�13-14+15��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='16_1' value='' size='13' tabindex='16'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>17<input type="hidden" name="hc" value="17" id="hc_17"></td>
							<td class="2-td2-left-qysds1" nowrap>��ǰ��˰���Ӧ�����ˣ�����˰��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='17_1' value='' size='13' tabindex='17'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>18<input type="hidden" name="hc" value="18" id="hc_18"></td>
							<td class="2-td2-left-qysds1" nowrap>ʵ��Ӧ�����ˣ�����˰�16+17��</td>
							<td class="2-td2-center" nowrap><input type='text' name='ljje'id='18_1' value='' size='13' tabindex='18'></td>
						</tr>
						
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center">
					<!-- Ӧ�ͻ�Ҫ���������
					<a style="cursor:hand" id="next"
						onclick="gotoNextTable()"><img name="spage" value="��һ�ű�"
						alt="��д��һ�ű�"
						onMouseOver="MM_swapImage('nexttable','','<%=static_contextpath%>images/xaiyiye2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/xaiyiye1.jpg" id="nexttable"> </a>
						&nbsp;&nbsp;
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
	<br>
	<%@ include file="../../include/footernew.jsp"%>
</html:form>
</body>
</html>
