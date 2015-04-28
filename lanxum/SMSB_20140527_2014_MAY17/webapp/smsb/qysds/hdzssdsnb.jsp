<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.hdzssdsnb.web.HdzssdsnbForm"%>
<%@ page import="java.util.HashMap"%>
<jsp:useBean
	type="com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.hdzssdsnb.web.HdzssdsnbForm"
	scope="request" id="hdzssdsnbForm" />
<html>
<head>
<title>核定征收企业所得税年度纳税申报表</title>
<!-- modify  20140829 -->
<html:base/>
<!-- end 20140829 -->
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



<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="doInit()">
<%@ include file="../include/header.jsp"%>
<br>

<html:form action="/webapp/smsb/qysds/hdzssdsnbAction.do">
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
	<html:hidden property="jmzg" />
	<html:hidden property="ybjmsl" />
	<html:hidden property="sysl" />

	<table width="96%" align="center" cellspacing="0" class="table-99"
		onKeyDown="jsjdmQuery()">
		<tr>
			<td class="1-td1" colspan="7">核定征收企业所得税年度纳税申报表</td>
		</tr>
		<tr>
			<td class="1-td2" colspan="7"><br>
			<table cellspacing="0" class="table-99">
				<tr class="black9">
					<td align="left" nowrap>
					<div align="left">申报日期： <html:text size="8" maxLength="8"
						property="sbrq" readonly='true' style='text-align:right'
						onchange="getStartEndDate(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,1)" />
					</div>
					</td>
					<td align="center" nowrap>
					<div align="left">税款所属日期： <html:text property="skssksrq" size="11"
						maxlength="8"
						onchange="isDate(this,false);compareDate(this)" />
					至 <html:text property="skssjsrq" size="11" maxlength="8"
						onchange="isDate(this,false);compareDate(this)" />
					</div>
					</td>
				</tr>
				<tr class="black9">
					<td align="left" nowrap>
					<div align="left">纳税人识别号：&nbsp; <bean:write name="hdzssdsnbForm"
						property="nsrsbh" scope="request" filter="true" /></div>
					</td>

					<td align="right" nowrap>
					<div align="right">金额单位：元（列至角分）</div>
					</td>
				</tr>
			</table>

			<table class="table-99" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="15%" nowrap class="2-td2-t-left">计算机代码&nbsp;</td>
					<td width="15%" nowrap class="2-td2-t-left" align=left><html:text
						property="jsjdm" size="8" maxlength="8" onchange="doQuery()" /></td>
					<td width="15%" nowrap class="2-td2-t-left">主管税务机关&nbsp;</td>
					<td width="40%" nowrap class="2-td2-t-center" align=left>&nbsp; <bean:write
						name="hdzssdsnbForm" property="zgswjgzzjgmc" scope="request"
						filter="true" /></td>
				</tr>
				<TR>
					<td width="15%" nowrap class="2-td2-left">纳税人名称&nbsp;</td>
					<td width="30%" nowrap class="2-td2-left" align=left>&nbsp; <bean:write
						name="hdzssdsnbForm" property="nsrmc" scope="request"
						filter="true" /></td>
					<td width="10%" nowrap class="2-td2-left">联系电话&nbsp;</td>

					<td width="15%" nowrap class="2-td2-center">&nbsp; <bean:write
						name="hdzssdsnbForm" property="lxdh" scope="request" filter="true" />
					</td>
				</tr>
				<tr>
					<td nowrap class="2-td2-left">生产经营地址&nbsp;</td>
					<td colspan=3 nowrap class="2-td2-center" align=left>&nbsp; <bean:write
						name="hdzssdsnbForm" property="jydz" scope="request" filter="true" />
					</td>
				</tr>
			</table>
			<br>
			<TABLE class="table-99" align="center">
				<TR>
					<TD>
					<div id="Layer2" style="position:static;">
					<table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
						<tr>
							<!--<td class="2-td1-left" nowrap>行次</td>
										 <td class="2-td1-left" nowrap>项目</td><!-->
							<td class="2-td1-left" nowrap>行次</td>
							<td class="2-td1-left" nowrap>项目</td>
							<td class="2-td1-left" nowrap>本期数</td>
							<td class="2-td1-center" nowrap>累计数</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>1 <input type="hidden" name="hc"
								value="1" id="hc_1"></td>
							<td class="2-td2-left" nowrap>收入总额</td>
							<td class="2-td2-left" nowrap><input type='text' name='bqs'
								id='1_1' size='13' style='text-align:right' value='0.00'
								onblur="formate(this)" onchange="check_row_1('1',null)"></td>
							<td class="2-td2-center" nowrap><input type='text' name='ljs'
								id='1_2' size='13' style='text-align:right' value='0.00'
								onblur="formate(this)" onchange="check_row_1('2',null)"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>2 <input type="hidden" name="hc"
								value="2" id="hc_2"></td>
							<td class="2-td2-left" nowrap>成本费用</td>
							<td class="2-td2-left" nowrap><input type='text' name='bqs'
								id='2_1' value='*' size='1' readonly='true'
								class='text-noborder' style="text-align:center"></td>
							<td class="2-td2-center" nowrap><input type='text' name='ljs'
								id='2_2' value='*' size='1' readonly='true'
								class='text-noborder' style="text-align:center"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>3 <input type="hidden" name="hc"
								value="3" id="hc_3"></td>
							<td class="2-td2-left" nowrap>应税所得率</td>
							<td class="2-td2-left" nowrap><input type='text' name='bqs'
								id='3_1' style='text-align:right' value='0.00' size='13'
								class='text-gray' readonly='true'></td>
							<td class="2-td2-center" nowrap><input type='text' name='ljs'
								id='3_2' style='text-align:right' value='0.00' size='13'
								class='text-gray' readonly='true'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>4 <input type="hidden" name="hc"
								value="4" id="hc_4"></td>
							<td class="2-td2-left" nowrap>应纳税所得额</td>
							<td class="2-td2-left" nowrap><input type='text' name='bqs'
								id='4_1' style='text-align:right' value='0.00' size='13'
								onblur="formate(this)" onchange="compute_row_4d('1','0')"></td>
							<td class="2-td2-center" nowrap><input type='text' name='ljs'
								id='4_2' style='text-align:right' value='0.00' size='13'
								onblur="formate(this)" onchange="compute_row_4d('2','0')"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>5 <input type="hidden" name="hc"
								value="5" id="hc_5"></td>
							<td class="2-td2-left" nowrap>适用税率</td>
							<td class="2-td2-left" nowrap><input type='text' name='bqs'
								id='5_1' style='text-align:right' value='0.00' size='13'
								class='text-gray' readonly='true'></td>
							<td class="2-td2-center" nowrap><input type='text' name='ljs'
								id='5_2' style='text-align:right' value='0.00' size='13'
								class='text-gray' readonly='true'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>6 <input type="hidden" name="hc"
								value="6" id="hc_6"></td>
							<td class="2-td2-left" nowrap>应缴所得税额（4×5）</td>
							<td class="2-td2-left" nowrap><input type='text' name='bqs'
								id='6_1' style='text-align:right' value='0.00' size='13'
								class='text-gray' readonly='true'></td>
							<td class="2-td2-center" nowrap><input type='text' name='ljs'
								id='6_2' style='text-align:right' value='0.00' size='13'
								class='text-gray' readonly='true'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>7 <input type="hidden" name="hc"
								value="7" id="hc_7"></td>
							<td class="2-td2-left" nowrap>减：实际已预缴的所得税额</td>
							<td class="2-td2-left" nowrap><input type='text' name='bqs'
								id='7_1' style='text-align:right' value='0.00' size='13'
								onblur="formate(this)" onchange="compute_row_8('1','0')"></td>
							<td class="2-td2-center" nowrap><input type='text' name='ljs'
								id='7_2' style='text-align:right' value='0.00' size='13'
								onblur="formate(this)" onchange="compute_row_8('2','0')"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>8 <input type="hidden" name="hc"
								value="8" id="hc_8"></td>
							<td class="2-td2-left" nowrap>应补（退）的所得税额（8=6-7）</td>
							<td class="2-td2-left" nowrap><input type='text' name='bqs'
								id='8_1' style='text-align:right' value='0.00' size='13'
								class='text-gray' readonly='true'></td>
							<td class="2-td2-center" nowrap><input type='text' name='ljs'
								id='8_2' style='text-align:right' value='0.00' size='13'
								class='text-gray' readonly='true'></td>
						</tr>
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center"><input type="image" accesskey="q"
						onClick="doQuery();return false;"
						onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)"
						onMouseOut="MM_swapImgRestore()" value="查询"
						src="<%=static_contextpath%>images/cx-q1.jpg " width="79"
						height="22" id="chaxun" style="cursor:hand">
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="u"
						style="cursor:hand" onClick="doReset();return false;"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" name="Image13"
						width="79" height="22" border="0" id='qingchu'>
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="s"
						style="cursor:hand" onClick="doSave();return false;"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" name="Image13"
						width="79" height="22" border="0" id='baocun'>
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="x"
						style="cursor:hand" onClick="doDelete();return false;"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" name="Image13"
						width="79" height="22" border="0" id='shanchu'>
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="image" accesskey="c"
						onClick="tuichu();return false;"
						onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" value="退出" id="tc1"
						src="<%=static_contextpath%>images/tc-c1.jpg" width="79"
						height="22" style="cursor:hand"></div>
					</TD>
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


<script language="JavaScript">
	<%/*初始化*/%>	
	function doInit()	
	{
		//initNumText("bqs",8);
		//initNumText("ljs",8);
		<%
		HdzssdsnbForm nbForm=(HdzssdsnbForm)request.getAttribute("hdzssdsnbForm");
		if (nbForm!=null && nbForm.getDataList().size()>0){
			for(int i=0;i<nbForm.getDataList().size();i++){
				HashMap map=(HashMap)nbForm.getDataList().get(i);
				int hc=Integer.parseInt((String)map.get("hc"));
				String bqs=(String)map.get("bqs");
				String ljs=(String)map.get("ljs");
				%>
				obj = eval("document.getElementById('<%=hc%>_1')");
				obj.value = '<%=bqs%>';
				obj = eval("document.getElementById('<%=hc%>_2')");
				obj.value = '<%=ljs%>';		

					
				<% 
			}
		}
		%>
		
		//企业征收类型
		var qyzslx=document.forms[0].qyzslx.value;
		
		if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){
			var obj = document.getElementById('4_1');
			obj.readOnly=true;
			obj.className='text-gray';
			var obj = document.getElementById('4_2');
			obj.readOnly=true;
			obj.className='text-gray';
			

			
			/*应税所得率(亦即“纯益率”)*/
			var cylValue = document.forms[0].cyl.value;
			
			var cylValue31 = document.getElementById('3_1').value;
			var cylValue32 = document.getElementById('3_2').value;

			if(cylValue31==null||parseFloat(cylValue31)=='0'||cylValue31==""){
				document.getElementById('3_1').value = Math.round(cylValue*100)/100;
				formate(document.getElementById('3_1'));		
			}

			if(cylValue32==null||parseFloat(cylValue32)=='0'||cylValue32==""){				
				document.getElementById('3_2').value = Math.round(cylValue*100)/100;
				formate(document.getElementById('3_2'));
			}
			
		}
		if(qyzslx == 4){
			/*定额征收*/
			var obj = document.getElementById('1_1');
			obj.readOnly=true;
			obj.className='text-gray';
			obj.value='';
			var obj = document.getElementById('1_2');
			obj.readOnly=true;
			obj.className='text-gray';
			obj.value='';
			var obj = document.getElementById('3_1');
			obj.value='';
			var obj = document.getElementById('3_2');
			obj.value='';
			
			var dezsse51 = document.getElementById('5_1').value ;
			var dezsse52 = document.getElementById('5_2').value ;
			
			/*应纳税所得额（对定额征收而言，此处实际为“单位税额”）*/
			if(dezsse51==null||parseFloat(dezsse51)=='0'||dezsse51==""){
				document.getElementById('5_1').value = document.forms[0].dezsse.value;
				formate(document.getElementById('5_1'));		
			}
			if(dezsse52==null||parseFloat(dezsse52)=='0'||dezsse52==""){				
				document.getElementById('5_2').value = document.forms[0].dezsse.value;
				formate(document.getElementById('5_2'));
			}
		}
		
		if(qyzslx==4){
			setFoucs('4');		
		}else{
			setFoucs('1');
		}
	}		

		//响应计算机代码的回车查询
		function jsjdmQuery(){
			if(event.keyCode==13) event.keyCode = 9;
		}
		
		//设置输入计算机代码时的焦点变化
		function setFoucs(num){
			if(document.forms[0].nsrmc.value==""){
				document.forms[0].jsjdm.focus();
			}else{
				document.getElementById(num+'_1').focus();
			}	
		}
		
		<%/*查询*/%>
		function doQuery(){
			var jsjdm;
			jsjdm = document.forms[0].jsjdm.value;
			if(jsjdm==""){
			    alert("计算机代码不允许是空！");
			    return false;
		  	}else{
			    doSubmitForm("Query");
		    	return false;
		  	}
		}		
		
		
	<%/*保存*/%>
	function doSave(){
		if(document.forms[0].nsrmc.value==""){
			alert("基本信息不正确,保存失败,请重新输入!");
			return false;
		}
		
		//if(!isValidQysds())return false;
		if(!compareDate2Save(document.forms[0].skssjsrq))return false;
		if(!saveCheck(1,null))return false;
		if(!saveCheck(4,null))return false;
		if(!saveCheck(7,0))return false;
		
		//企业征税类型
		var qyzslx=document.forms[0].qyzslx.value;
		if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){
			if(!compare('1'))return false;
			if(!compare('7'))return false;		
		}
		if(qyzslx==4){
			if(!compare('4'))return false;
			if(!compare('7'))return false;		
		}
		doSubmitForm('Save');
	}
	
	<%/*审核*/%>
	function doCheck()
	{
		// doSubmitForm('Check');
	}
	
	<%/*清除*/%>
	function doReset()
	{
		
		if(confirm("确认是否清除当前数据？"))
		{
	   		//企业征税类型
			var qyzslx=document.forms[0].qyzslx.value;
			
	   		if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){
	   			for(var i=1; i < 9; i++){
					obj = eval("document.getElementById('" + i+ "_1')");
					if (obj.value!="*"&&i!=3)
						obj.value = "0.00";
					obj = eval("document.getElementById('" + i+ "_2')");
					if (obj.value!="*"&&i!=3)
						obj.value = "0.00";
	   			}
	   		}
	   		if(qyzslx == 4){
	   			for(var i=4; i < 9; i++){
					obj = eval("document.getElementById('" + i+ "_1')");
					if (i!=5)
						obj.value = "0.00";
					obj = eval("document.getElementById('" + i+ "_2')");
					if (i!=5)
						obj.value = "0.00";
	   			}
	   		}
		}
	}
	
	<%/*删除*/%>
	function doDelete()
	{
		if(document.forms[0].nsrmc.value==""){
			alert("基本信息不正确,删除失败,请重新输入!");
			return false;
		}
		doSubmitForm('Delete');
	}
		
	//校验1行数据,并计算第3、4行
	function check_row_1(num,zero){
		
		//企业征收类型
		var qyzslx=document.forms[0].qyzslx.value;
		
		if(!isNum(getCellObject() , zero, 9999999999999, null, 16, 2)){
			return false;			
		}		
		
		if(num=='2'){
			if(!compare('1'))return false;
		}
		
		if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){
			if(getCellObject().value == ""){
				for(i=4;i<7;i++){
					document.getElementById(i+'_'+num).value ="";
				}
				if(document.getElementById('7_'+num).value==""){
					document.getElementById('8_'+num).value ="";
				}else{
					document.getElementById('8_'+num).value = '0.00';
				}
			}else{
				//compute_row_3(num);
				compute_row_4(num);
				compute_row_5(num);
				compute_row_6(num);
				compute_row_8(num);				
			}
		}
	}
	
	//计算第3行数据
	function compute_row_3(num){
	
		var cylValue = document.forms[0].cyl.value;
		document.getElementById('3_'+num).value = Math.round(cylValue*100)/100;
					
	}
	
	//计算第4行数据
	function compute_row_4(num){
		
		var ynssde = parseFloat(document.getElementById('1_'+num).value) * parseFloat(document.forms[0].cyl.value);
			
		document.getElementById('4_'+num).value = Math.round(ynssde*100)/100;
		
		if(document.getElementById('4_'+num).value<0){
			document.getElementById('4_'+num).value = 0;			
		}
		
		formate(document.getElementById('4_'+num));
	}
	
	//计算第5行数据
	function compute_row_5(num){
		
		//企业征收类型
		var qyzslx=document.forms[0].qyzslx.value;
		
		if(qyzslx == 3 || qyzslx == 2 || qyzslx == 5 ||qyzslx== 1){
			if(document.getElementById('4_'+num).value<=30000.00){
				document.getElementById('5_'+num).value=0.18;				
			}
			else if(document.getElementById('4_'+num).value>30000.00&&document.getElementById('4_'+num).value<=100000.00){
				document.getElementById('5_'+num).value=0.27;				
			}
			else{
				document.getElementById('5_'+num).value=0.33;				
			}					
		}
	}
	
	//计算第6行数据
	function compute_row_6(num){
		if(document.getElementById('4_'+num).value==""){
			document.getElementById('6_'+num).value="";
		}else{
			var temp1=convertSpace2Zero(document.getElementById('4_'+num).value);
			var temp2=convertSpace2Zero(document.getElementById('5_'+num).value);
			var yjsdse = parseFloat(temp1) * parseFloat(temp2);
			document.getElementById('6_'+num).value = Math.round(yjsdse*100)/100;
			if(document.getElementById('6_'+num).value<0){
				document.getElementById('6_'+num).value=0;			
			}
			formate(document.getElementById('6_'+num));
		}		
	}
	
	//计算第8行数据,第7行用
	function compute_row_8(num,zero){
		
		if(!isNum(getCellObject() , zero, 9999999999999, null, 16, 2)){
			return false;			
		}
		
		if(num=='2'){
			if(!compare('7'))return false;
		}
		
		if(document.getElementById('6_'+num).value==""&&document.getElementById('7_'+num).value==""){
			document.getElementById('8_'+num).value="";
		}else{
		
			var temp3=convertSpace2Zero(document.getElementById('6_'+num).value);
			var temp4=convertSpace2Zero(document.getElementById('7_'+num).value);
			
			var ybdsdse = parseFloat(temp3) - parseFloat(temp4);
			document.getElementById('8_'+num).value = Math.round(ybdsdse*100)/100;
			//if(document.getElementById('8_'+num).value<0){
			//	document.getElementById('8_'+num).value = 0;			
			//}
			formate(document.getElementById('8_'+num));
		}
	}
	
	//定额计算公式,第4、5行
	function compute_row_4d(num,zero){
		
		if(!isNum(getCellObject() , zero, 9999999999999, null, 16, 2)){
			return false;			
		}		
		
		if(num=='2'){
			if(!compare('4'))return false;
		}
		
		if(document.forms[0].qyzslx.value == 4){
			compute_row_6(num);
			compute_row_8(num);
		}				
	}	
	
	//比较本期数与累计数的大小
	function compare(row){
		var tempnum1=convertSpace2Zero(document.getElementById(row+'_1').value);
		if(document.getElementById(row+'_2').value!=""){
			var tempnum2=document.getElementById(row+'_2').value;
			if(Math.round(parseFloat(tempnum1))>Math.round(parseFloat(tempnum2))){
				alert("累计数的值应该大于本期数的值");
				window.event.returnValue = false;
				document.getElementById(row+'_2').focus();
				document.getElementById(row+'_2').select();
				return false;				
			}
		}
		return true;			
	}
	
		
	//转换空格为零
	function convertSpace2Zero(inputValue){
		return inputValue==""?"0":inputValue;
	}	
	<%/*保存时对第1、4、7行的数据进行校验*/%>
	function saveCheck(row,zero){
		
		if(!isNum(document.getElementById(row+'_1'), zero, 9999999999999, null, 16, 2)){
			return false;			
		}
		if(!isNum(document.getElementById(row+'_2'), zero, 9999999999999, null, 16, 2)){
			return false;			
		}
		return true;	
	}
	
	/*保存时校验*/
	function isValidQysds(){
		/*税款所属开始日期不允许跨年度*/
		var ks_year=document.forms[0].skssksrq.value.substr(0,4);
		var js_year=document.forms[0].skssjsrq.value.substr(0,4);
		if(ks_year!=js_year){
			alert("税款开始时间与税款结束时间的年份不同，税款不能跨年！");
			return false;
		}
		return true;
	}
	
	<%/*判断比较税款所属日期*/%>
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
	  		alert("税款结束时间不能大于申报日期");
	  		window.event.returnValue=false;
			document.forms[0].skssjsrq.focus();
			document.forms[0].skssjsrq.select();
			return false;
	  	}
  	  	
  		if(objDate1>=objDate2){
			if(obj == document.forms[0].skssjsrq){
				alert("税款结束时间不能小于或等于税款开始时间");
			}else{
				alert("税款开始时间不能大于或等于税款结束时间");	
			}
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;
		}
		
		if(strYear1!=strYear2){
			alert("税款开始时间与税款结束时间的年份不同，税款不能跨年！");
			return false;				
		}
			
	}
	
	//判断比较税款所属日期
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
  		alert("税款结束时间不能大于申报日期");
  		window.event.returnValue=false;
			document.forms[0].skssjsrq.focus();
			document.forms[0].skssjsrq.select();
			return false;
  	}
  	  	
  		if(objDate1>=objDate2){
			if(obj == document.forms[0].skssjsrq){
				alert("税款结束时间不能小于或等于税款开始时间");
			}else{
				alert("税款开始时间不能大于或等于税款结束时间");	
			}
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;
		}
		
		if(strYear1!=strYear2){
			alert("税款开始时间与税款结束时间的年份不同，税款不能跨年！");
			return false;				
		}
		
		return true;
			
	}
	
</script>


</body>
</html>
