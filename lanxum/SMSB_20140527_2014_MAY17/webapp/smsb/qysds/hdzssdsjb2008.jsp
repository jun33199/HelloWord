<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsjb2008.web.HdzssdsjbForm"%>
<%@ page import="java.util.HashMap"%>

<html:html>
<head>
<title>企业所得税月(季)度预缴纳税申报表(B类)</title>
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
<script language=JavaScript type="text/JavaScript"
	src="../../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../js/qysdsnew.js"></script>
<style>
input {
    font-size: 9pt;
    text-align: right;
}
.inputalignright{
    font-size: 9pt;
    text-align: right;
}
.inbright {
    font-size: 9pt;
    background :#F3F5F8;
	text-align: right;
    background-color: #F3F5F8;
    border: 0px;
}
</style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="doInit()">
<%@include file="../include/header.jsp"%>
<html:form method="POST" action="/webapp/smsb/qysds/hdzssdsjb2008Action.do">
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
	<html:hidden property="zsfs"/>
	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="jsjdmQuery()">
		<tr>
			<td class="1-td1" colspan="7">
				中华人民共和国<br>企业所得税月(季)度预缴纳税申报表(B类)
			</td>
		</tr>

		<tr>
			<td class="1-td2" colspan="7"><br>
			<table cellspacing="0" class="table-99">

				<tr class="black9">
					<td align="center" nowrap>
					<div align="left">申报日期 <html:text property="sbrq" size="11"
						maxlength="8" readonly='true' style='text-align:right'
						onchange="checkZQ(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,3)" /></div>
					</td>
					<td wigth="90%" align="center" nowrap>税款所属日期： <html:text property="skssksrq"
						size="15" maxlength="8"
						onchange="isDate(this,false);compareDate(this)"  style="text-align:left" /> 至 <html:text
						property="skssjsrq" size="15" maxlength="8"
						onchange="isDate(this,false);compareDate(this)" style="text-align:left" /></td>
					<td align="right" nowrap>
					<div align="right">金额单位：元（列至角分）</div>
					</td>
				</tr>
			</table>
			<table class="table-99" border="0" cellpadding="0" cellspacing="0" width="80%">
				<tr>
					<td width="16%" nowrap class="2-td2-t-left"><div align="center">纳税人地税计算机代码&nbsp;</div></td>
					<td colspan="3" nowrap class="2-td2-t-center" align=left><div align="left">&nbsp;&nbsp;<html:text tabindex="1" property="jsjdm" maxlength="8" size="15" onchange="doQuery()" style="text-align:left"/></div></td>
				</tr>
				<TR>
					<td width="16%" nowrap class="2-td2-left"><div align="center">纳税人识别号&nbsp;</div></td>
					<td width="30%" nowrap class="2-td2-left" align=left>&nbsp;<bean:write name="hdzssdsjb2008Form"
						property="nsrsbh" scope="request" filter="true" /></td>
					<td width="16%" nowrap class="2-td2-left"><div align="center">纳税人名称&nbsp;</div></td>
					<td width="30%" nowrap class="2-td2-center" align=left>&nbsp; <bean:write
						name="hdzssdsjb2008Form" property="nsrmc" scope="request"
						filter="true" /> </td>
				</tr>
			</table>
			<br>

			<TABLE class="table-99" align="center">
				<TR>
					
            <TD width="895" height="334"> 
              <div id="Layer2" style="position:static;">
					
                <table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
                  <tr> 
                    <td colspan="3" nowrap class="2-td1-left"><div align="center">项目</div></td>
                    <td width="52" nowrap class="2-td1-left"><div align="center">行次</div></td>
                    <td width="180" nowrap class="2-td1-center">累计金额</td>
                  </tr>
                  <tr> 
                    <td width="144" rowspan="10" nowrap class="2-td2-left">应纳税所得额的计算</td>
                    <td width="208" rowspan="3" nowrap class="2-td2-left">按收入总额核定应纳税所得额</td>
                    <td width="384" nowrap class="2-td2-left">收入总额</td>
                    <td class="2-td2-left" nowrap><div align="center">1<input type="hidden" name="hc"
								value="1" id="hc_1"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='2' type='text' name='lje'
								id='1_1' style='text-align:right' value='0.00' size='13'
								onblur="formate(this)" onchange="compute_Row_1()"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>税务机关核定的应税所得率（ %）</td>
                    <td class="2-td2-left" nowrap><div align="center">2<input type="hidden" name="hc"
								value="2" id="hc_2"></div></td>
                    <td class="2-td2-center" nowrap><input type='hidden' name='lje' value='0.00' id='2_1'><input tabindex='3' type='text' name='ljeView'
								id='2_1_1' style='text-align:right' readonly='true' class="text-gray" value='0.00' size='13'></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>应纳税所得额（1行×2行）</td>
                    <td class="2-td2-left" nowrap><div align="center">3<input type="hidden" name="hc"
								value="3" id="hc_3"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='4' type='text' name='lje'
								class="text-gray" id='3_1' style='text-align:right'  readonly='true' value='0.00' size='13'></td>
                  </tr>
                  <tr> 
                    <td rowspan="3" nowrap class="2-td2-left">按成本费用核定应纳税所得额</td>
                    <td class="2-td2-left" nowrap>成本费用总额</td>
                    <td class="2-td2-left" nowrap><div align="center">4<input type="hidden" name="hc"
								value="4" id="hc_4"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='-1' type='text' name='lje'
								id='4_1'  value='*'  size='1' readonly='true'
								class='text-noborder' style="text-align:center"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>税务机关核定的应税所得率（ %）</td>
                    <td class="2-td2-left" nowrap><div align="center">5<input type="hidden" name="hc"
								value="5" id="hc_5"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='-1' type='text' name='lje'
								id='5_1' value='*' size='1' readonly='true'
								class='text-noborder' style="text-align:center"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>应纳税所得额[4行÷（1－5行）×5行]</td>
                    <td class="2-td2-left" nowrap><div align="center">6<input type="hidden" name="hc"
								value="6" id="hc_6"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='-1' type='text' name='lje'
								id='6_1' value='*' size='1' readonly='true'
								class='text-noborder' style="text-align:center"></td>
                  </tr>
                  <tr> 
                    <td rowspan="4" nowrap class="2-td2-left">按经费支出换算应纳税所得额</td>
                    <td class="2-td2-left" nowrap>经费支出总额</td>
                    <td class="2-td2-left" nowrap><div align="center">7<input type="hidden" name="hc"
								value="7" id="hc_7"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='-1' type='text' name='lje'
								id='7_1' value='*' size='1' readonly='true'
								class='text-noborder' style="text-align:center"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>税务机关核定的应税所得率（ %）</td>
                    <td class="2-td2-left" nowrap><div align="center">8<input type="hidden" name="hc"
								value="8" id="hc_8"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='-1' type='text' name='lje'
								id='8_1' value='*' size='1' readonly='true'
								class='text-noborder' style="text-align:center"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>换算的收入额[7行÷（1－8行）]</td>
                    <td class="2-td2-left" nowrap><div align="center">9<input type="hidden" name="hc"
								value="9" id="hc_9"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='-1' type='text' name='lje'
								id='9_1' value='*' size='1' readonly='true'
								class='text-noborder' style="text-align:center"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>应纳税所得额（8行×9行）</td>
                    <td class="2-td2-left" nowrap><div align="center">10<input type="hidden" name="hc"
								value="10" id="hc_10"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='-1' type='text' name='lje'
								id='10_1' value='*' size='1' readonly='true'
								class='text-noborder' style="text-align:center"></td>
                  </tr>
                  <tr> 
                    <td colspan="2" rowspan="3" nowrap class="2-td2-left">应纳所得税额的计算</td>
                    <td class="2-td2-left" nowrap>税率（25%）</td>
                    <td class="2-td2-left" nowrap><div align="center">11<input type="hidden" name="hc"
								value="11" id="hc_11"></div></td>
                    <td class="2-td2-center" nowrap><input type='hidden' name='lje' id='11_1' value='0.00'><input tabindex='5' type='text' name='ljeView'
								id='11_1_1' style='text-align:right' readonly='true' value='0.00' size='13'
								readonly='true' class="text-gray"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>应纳所得税额（3行×11行）</td>
                    <td class="2-td2-left" nowrap><div align="center">12<input type="hidden" name="hc"
								value="12" id="hc_12"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='6' type='text' name='lje'
								id='12_1' class="text-gray" style='text-align:right' readonly='true' value='0.00' size='13'
								onblur="formate(this)" onchange="compute_Row_12()"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>减免所得税额</td>
                    <td class="2-td2-left" nowrap><div align="center">13<input type="hidden" name="hc"
								value="13" id="hc_13"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='7' type='text' name='lje'
								id='13_1' style='text-align:right' value='0.00' size='13'
								onblur="formate(this)" onchange="compute_Row_13()"></td>
                  </tr>
                  <tr> 
                    <td colspan="2" rowspan="2" nowrap class="2-td2-left">应补（退）所得税额的计算</td>
                    <td class="2-td2-left" nowrap>已预缴所得税额</td>
                    <td class="2-td2-left" nowrap><div align="center">14<input type="hidden" name="hc"
								value="14" id="hc_14"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='8' type='text' name='lje'
								id='14_1' style='text-align:right' value='0.00' size='13'
								onblur="formate(this)" onchange="compute_Row_14()"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>应补（退）所得税额（12行－13行－14行）</td>
                    <td class="2-td2-left" nowrap><div align="center">15<input type="hidden" name="hc"
								value="15" id="hc_15"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='9' type='text' name='lje'
								id='15_1' style='text-align:right' readonly='true' value='0.00' size='13'
								readonly='true' class="text-gray" 
								onblur="formate(this)"></td>
                  </tr>
                </table>
					</div>
					</TD>
				</TR>
				<BR>
				<TR class="black9">
					<TD>
					<div align="center"><input tabindex='-1' type="image" accesskey="q"
						onClick="doQuery();return false;"
						onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)"
						onMouseOut="MM_swapImgRestore()" value="查询"
						src="<%=static_contextpath%>images/cx-q1.jpg " width="79"
						height="22" id="chaxun" style="cursor:hand">
					&nbsp;&nbsp;&nbsp;&nbsp; <input tabindex='-1'type="image" accesskey="u"
						style="cursor:hand" onClick="doReset();return false;"
						onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qc-u1.jpg" name="Image13"
						width="79" height="22" border="0" id='qingchu'>
					&nbsp;&nbsp;&nbsp;&nbsp; <input tabindex='-1' type="image" accesskey="s"
						style="cursor:hand" onClick="doSave();return false;"
						onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/bc-s1.jpg" name="Image13"
						width="79" height="22" border="0" id='baocun'>

					&nbsp;&nbsp;&nbsp;&nbsp;<input tabindex='-1' type="image" accesskey="x"
						style="cursor:hand" onClick="doDelete();return false;"
						onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
						onMouseOut="MM_swapImgRestore()"
						src="<%=static_contextpath%>images/qbsc-x1.jpg" name="Image13"
						width="79" height="22" border="0" id='shanchu'>
					&nbsp;&nbsp;&nbsp;&nbsp;<input tabindex='-1' type="image" accesskey="c"
						onClick="tuichu();return false;"
						onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" value="退出" id="tc1"
						src="<%=static_contextpath%>images/tc-c1.jpg" width="79"
						height="22" style="cursor:hand"></div>
					<BR>
					</TD>
				</TR>
			</TABLE>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>

	
<table width="100%" height="33" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td background="<%=static_contextpath%>images/q-bottom-bg-01.jpg" height="9px" colspan="2" nowrap></td>
  </tr>
  <tr>
    <td nowrap><font size="2">&nbsp;&nbsp;&nbsp;* 必须使用 IE 5.5 以上，分辨率 800*600 浏览本网站</font></td>
    <td height="24" align="right"><img src="<%=static_contextpath%>images/q-bottom-tu-01.jpg" alt="承办单位：清华同方软件股份有限公司2007_OCT24" width="184" height="24"></td>
  </tr>
</table>

</html:form>
<script language="JavaScript">
	<%/*初始化*/%>	
	function doInit()	
	{
	<%
	HdzssdsjbForm jbForm=(HdzssdsjbForm)request.getAttribute("hdzssdsjb2008Form");
	if (jbForm!=null && jbForm.getQysdsjbList()!=null && jbForm.getQysdsjbList().size()>0){
		for(int i=0;i<jbForm.getQysdsjbList().size();i++){
			HashMap map=(HashMap)jbForm.getQysdsjbList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String lje=(String)map.get("lje");
			%>
			
			obj = eval("document.getElementById('<%=hc%>_1')");

			obj.value = '<%=lje%>';
			<% 
		}
	}
	%>
		document.forms[0].jsjdm.focus();
		<%/*企业征收类型*/%>
		var qyzslx=document.forms[0].qyzslx.value;
			
		if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){
			document.getElementById('1_1').focus();
			
			<%/*应税所得率(亦即“纯益率”)*/%>
			<%/*设置第2行*/%>
			var cylValue = document.forms[0].cyl.value;
			
			
			var cylValue21 = document.getElementById('2_1').value;
			if(cylValue21=="null"||cylValue21==null||parseFloat(cylValue21)==0 || cylValue21==""){
				document.getElementById('2_1').value = Math.round(cylValue*100)/100;
				formate(document.getElementById('2_1'));		
			}
			
			var obj = document.getElementById('2_1_1');
			obj.readOnly=true;
			obj.className='text-gray';
			obj.value=parseFloat(document.getElementById('2_1').value)*100+"%";
			<%/*设置第3行*/%>
			var obj = document.getElementById('3_1');
			obj.readOnly=true;
			obj.className='text-gray';
			<%/*设置第11行*/%>
			document.getElementById('11_1').value="0.25";
			document.getElementById('11_1_1').value=parseFloat(document.getElementById('11_1').value)*100+"%";
			var obj = document.getElementById('11_1_1');
			obj.readOnly=true;
			obj.className='text-gray';
			
			
			
		}
		if(qyzslx ==4){
			document.getElementById('1_1').tabIndex="-1";
			document.getElementById('2_1_1').tabIndex="-1";
			document.getElementById('3_1').tabIndex="-1";
			document.getElementById('11_1_1').tabIndex="-1";
			document.getElementById('12_1').tabIndex="2";
			document.getElementById('13_1').tabIndex="3";
			document.getElementById('14_1').tabIndex="4";
			document.getElementById('15_1').tabIndex="5";
			<%/*定额征收*/%>
			var obj = document.getElementById('1_1');
			obj.readOnly=true;
			obj.className='text-noborder';
			obj.value='*';
			obj.style.cssText='text-align:center';
			obj.onblur="";
			var obj = document.getElementById('2_1_1');
			obj.readOnly=true;
			obj.className='text-noborder';
			obj.value='*';
			obj.style.cssText='text-align:center';
			var obj = document.getElementById('3_1');
			obj.readOnly=true;
			obj.className='text-noborder';
			obj.value='*';
			obj.style.cssText='text-align:center';
			var obj = document.getElementById('11_1_1');
			obj.readOnly=true;
			obj.className='text-noborder';
			obj.style.cssText='text-align:center';
			obj.value='*';
			var obj= document.getElementById('12_1');
			obj.readOnly=false;
			obj.className='text-border';
			obj.focus();
		}

	}
		<%/*响应计算机代码的回车查询*/%>
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
	function doSave()
	{
		if(document.forms[0].nsrmc.value==""){
			alert("基本信息不正确,保存失败,请重新输入!");
			document.forms[0].jsjdm.focus();
			return false;
		}
		<%/*企业征税类型*/%>
		var qyzslx=document.forms[0].qyzslx.value;
		if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){
			if(!saveCheck(1,null))return false;
			if(!saveCheck(13,null))return false;
			if(!saveCheck(14,null))return false;
		}
		if(qyzslx==4){
			if(!saveCheck(12,null))return false;
			if(!saveCheck(13,null))return false;
			if(!saveCheck(14,null))return false;	
		}
		
	
		if(!compareDate2Save(document.forms[0].skssjsrq))return false;
		
		if(parseFloat(document.getElementById('12_'+1).value)<0){
			alert("输入的金额必须大于等于0!");
			window.event.returnValue=false;
			document.getElementById('12_1').focus();
			document.getElementById('12_1').select();
			return false;
		}
		if(parseFloat(document.getElementById('13_'+1).value)<0){
			alert("输入的金额必须大于等于0!");
			window.event.returnValue=false;
			document.getElementById('13_1').focus();
			document.getElementById('13_1').select();
			return false;
		}
		if(parseFloat(document.getElementById('14_'+1).value)<0){
			alert("输入的金额必须大于等于0!");
			window.event.returnValue=false;
			document.getElementById('14_1').focus();
			document.getElementById('14_1').select();
			return false;
		}
		
		if(parseFloat(document.getElementById('13_'+1).value)>parseFloat(document.getElementById('12_'+1).value)){
			alert("第13行的值必须小于等于第12行的值!");
			window.event.returnValue=false;
			document.getElementById('13_1').focus();
			document.getElementById('13_1').select();
			return false;
		}
		
		doSubmitForm('Save');
	
	}
		<%/*保存时对第1、14行的数据进行校验*/%>
	function saveCheck(row,zero){
		
		if(!isNum(document.getElementById(row+'_1'), zero, null, null, null, 2)){
			return false;			
		}
		return true;	
	}
			//判断比较税款所属日期
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
	
		<%/*判断比较税款所属日期*/%>
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
  		alert("税款结束时间不能大于当前日期");
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
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;				
		}
		
		return true;
			
	}
	<%/*计算第3,12行数据*/%>
	function compute_Row_1(){
		var ynssds;
		var ybsds;
		 if(!isNum(getCellObject(), null, null, null, null, 2)){
             return false;                         
          }
		if(parseFloat(document.getElementById('1_'+1).value)<0){
			alert("输入的金额必须大于等于0!");
			window.event.returnValue=false;
			document.getElementById('1_1').focus();
			document.getElementById('1_1').select();
			return false;
		}
		if(document.getElementById('1_1').value==""){
			ynssds=0;	
		}
		else{
			ynssds = Math.round((parseFloat(document.getElementById('1_'+1).value) * parseFloat(document.getElementById('2_'+1).value))*100)/100;
		}
		document.getElementById('3_1').value = parseFloat(ynssds);
		formate(document.getElementById('3_1'));
		var ynsdse =Math.round((parseFloat(document.getElementById('3_'+1).value) * parseFloat(document.getElementById('11_'+1).value))*100)/100;
			
		document.getElementById('12_'+1).value = parseFloat(ynsdse);
		formate(document.getElementById('12_'+1));
		
		ybsds=Math.round((parseFloat(document.getElementById('12_'+1).value)-parseFloat(document.getElementById('13_'+1).value)-parseFloat(document.getElementById('14_'+1).value))*100)/100;
			if(ybsds>0){
				document.getElementById('15_'+1).value=parseFloat(ybsds);
				
			}
			else{
				document.getElementById('15_'+1).value=0;
			}
			formate(document.getElementById('15_1'));
	}
	<%/*第13行输入判断*/%>
	function compute_Row_13(){
		var ybsds;
		if(!isNum(getCellObject(), null, null, null, null, 2)){
             return false;                         
          }
		if(parseFloat(document.getElementById('13_'+1).value)>parseFloat(document.getElementById('12_'+1).value)){
			alert("输入的金额必须小于等于第12行的值!");
			window.event.returnValue=false;
			document.getElementById('13_1').focus();
			document.getElementById('13_1').select();
			return false;
		}
		if(parseFloat(document.getElementById('13_'+1).value)<0){
			alert("输入的金额必须大于等于0!");
			window.event.returnValue=false;
			document.getElementById('13_1').focus();
			document.getElementById('13_1').select();
			return false;
		}
		if(document.getElementById('13_1').value==""){
			document.getElementById('13_1').value=0;
		}
		ybsds=Math.round((parseFloat(document.getElementById('12_'+1).value)-parseFloat(document.getElementById('13_'+1).value)-parseFloat(document.getElementById('14_'+1).value))*100)/100;
			if(ybsds>0){
				document.getElementById('15_1').value=parseFloat(ybsds);
				
			}
			else{
				document.getElementById('15_1').value=0;
			}
			formate(document.getElementById('15_1'));
		
	}
		<%/*第12行输入判断*/%>
	function compute_Row_12(){
		var ybsds;
		if(!isNum(getCellObject(), null, null, null, null, 2)){
             return false;                         
          }
		if(parseFloat(document.getElementById('12_'+1).value)<0){
			alert("输入的金额必须大于等于0!");
			window.event.returnValue=false;
			document.getElementById('12_1').focus();
			document.getElementById('12_1').select();
			return false;
		}
		if(document.getElementById('12_1').value==""){
			document.getElementById('12_1').value=0;
		}
		ybsds=Math.round((parseFloat(document.getElementById('12_'+1).value)-parseFloat(document.getElementById('13_'+1).value)-parseFloat(document.getElementById('14_'+1).value))*100)/100;
			if(ybsds>0){
				document.getElementById('15_'+1).value=parseFloat(ybsds);
				
			}
			else{
				document.getElementById('15_'+1).value=0;
			}
			formate(document.getElementById('15_1'));
	}
			<%/*第14行输入判断*/%>
	function compute_Row_14(){
		var ybsds;
		if(!isNum(getCellObject(), null, null, null, null, 2)){
             return false;                         
          }
		if(parseFloat(document.getElementById('14_'+1).value)<0){
			alert("输入的金额必须大于等于0!");
			window.event.returnValue=false;
			document.getElementById('14_1').focus();
			document.getElementById('14_1').select();
			return false;
		}
		if(document.getElementById('14_1').value==""){
			document.getElementById('14_1').value=0;
		}
		ybsds=Math.round((parseFloat(document.getElementById('12_'+1).value)-parseFloat(document.getElementById('13_'+1).value)-parseFloat(document.getElementById('14_'+1).value))*100)/100;
			if(ybsds>0){
				document.getElementById('15_'+1).value=parseFloat(ybsds);			
			}
			else{
				document.getElementById('15_'+1).value=0;
			}
			formate(document.getElementById('15_1'));
	}
		<%/*清除*/%>
	function doReset()
	{
		
		if(confirm("确认是否清除当前数据？"))
		{
	   		//企业征税类型
			var qyzslx=document.forms[0].qyzslx.value;
			
	   		if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){
	   			for(var i=1; i < 16; i++){
					obj = eval("document.getElementById('" + i+ "_1')");
					if (obj.value!="*"&&i!=2&&i!=11)
						obj.value = "0.00";
	   			}
	   		}
	   		else if(qyzslx == 4){
	   			for(var i=12; i < 16; i++){
					obj = eval("document.getElementById('" + i+ "_1')");
					obj.value = "0.00";
	   			}
	   		}
	   		else{
	   			for(var i=1;i<6;i++){
	   				obj = eval("document.getElementById('" + i+ "_1')");
					if (obj.value!="*")
						obj.value = "0.00";
	   			}
	   		}
	   		document.forms[0].jsjdm.focus();
			document.forms[0].jsjdm.select();
		}
	}
		<%/*删除*/%>
	function doDelete()
	{
		if(document.forms[0].jsjdm.value==""){
			alert("基本信息不正确,删除失败,请重新输入!");
			return false;
		}
		doSubmitForm('Delete');
	}
	
		//如果申报月份不属于企业所得税季度征期4、7、10则提示操作人员
	function checkZQ(sbrq,ksrq,jzrq,lx){
		if(!isDate(sbrq,"v")) return;
		getStartEndDate1(sbrq,ksrq,jzrq,lx);
		var inputDate = sbrq.value;
		mon = inputDate.substring(4,6);
		if(mon!='01' && mon!='04' && mon!='07' && mon!='10'){
			alert('注意：'+inputDate+'不属于征期。');
		}
	}
	
	/**
 * 格式化输入数据为小数点后两位
 * 
 * @param 文本框
 */
function formate(obj){
	//if(!isNumber(obj.value)){
	//	alert("请使用数字格式输入！");
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
 * 格式化输入数据以0开头
 *
 * @param 文本框
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
</body>
</html:html>
