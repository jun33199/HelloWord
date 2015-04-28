<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.hdzssdsnb.web.HdzssdsnbForm"%>
<%@ page import="java.util.HashMap"%>

<html:html>
<head>
<title>中华人民共和国 企业所得税年度纳税申报表（B类）</title>
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
<script language=JavaScript type="text/JavaScript"
	src="../../../js/function.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/qysdsnew.js"></script>
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
<%@include file="../../include/header.jsp"%>
<html:form method="POST" action="/webapp/smsb/qysds/2009/hdzssdsnbAction2009">
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
		<%
		String notify = (String)request.getAttribute("SYX_NOTIFY");	
		if(notify!=null && !notify.trim().equals("")){
			%>
				<font color=red face="宋体"><strong>尊敬的纳税人：</strong></font><br>
				<font color=red face="宋体"><strong>&nbsp;&nbsp;&nbsp;&nbsp;<%=notify%></strong></font>
			<%
		}
	%>
	<table width="96%" align="center" cellspacing="0" class="table-99"
		onkeydown="jsjdmQuery()">
		<tr>
			<td class="1-td1" colspan="7">
				中华人民共和国<br>企业所得税年度纳税申报表（B类）
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
					<td colspan="3" nowrap class="2-td2-t-center" align=left>
					<div align="left">
					&nbsp;&nbsp;<html:text tabindex="1" property="jsjdm" maxlength="8" size="15" onchange="doQuery()" style="text-align:left"/>
					</div>
					</td>
				</tr>
				<TR>
					<td width="16%" nowrap class="2-td2-left"><div align="center">纳税人识别号&nbsp;</div></td>
					<td width="30%" nowrap class="2-td2-left" align=left>&nbsp;<bean:write name="hdzssdsnbForm2009"
						property="nsrsbh" scope="request" filter="true" /></td>
					<td width="16%" nowrap class="2-td2-left"><div align="center">纳税人名称&nbsp;</div></td>
					<td width="30%" nowrap class="2-td2-center" align=left>&nbsp; <bean:write
						name="hdzssdsnbForm2009" property="nsrmc" scope="request"
						filter="true" /> </td>
				</tr>
				<TR>
					<td width="16%" nowrap class="2-td2-left"><div align="center">注册资本金额（单位：万元）&nbsp;<input type="hidden" name="hc"
								value="9" id="hc_9"></div></td>
					<td width="30%" nowrap class="2-td2-left" align=left>
						<input tabindex='8' type='text' name='je' id='9_1'  value='' size='13' onblur='checkZczb()'>
					</td>
					<td width="16%" nowrap class="2-td2-left"><div align="center">资产总额（单位：万元）&nbsp;<input type="hidden" name="hc"
								value="10" id="hc_10"></div></td>
					<td width="30%" nowrap class="2-td2-center" align=left>
						<input tabindex='9' type='text' name='je' id='10_1'  value='' size='13' onblur='checkZcze()'>
					</td>
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
                    <td class="2-td2-center" nowrap><input tabindex='2' type='text' name='je'
								id='1_1' style='text-align:right' value='' size='13'
								 onblur="checkNumInput(this)" onchange="compute_Row_3(this)" class="text-gray" readonly='true'></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>税务机关核定的应税所得率（ %）</td>
                    <td class="2-td2-left" nowrap><div align="center">2<input type="hidden" name="hc"
								value="2" id="hc_2"></div></td>
                    <td class="2-td2-center" nowrap>
                    	&nbsp;<input tabindex='3' type='text' name='je' onblur="checkNumInput(this)" id='2_1' style='text-align:right' readonly='true' onchange="compute_Row_3(this)" class="text-gray" value='' size='13'>%</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>应纳税所得额（1行×2行）</td>
                    <td class="2-td2-left" nowrap><div align="center">3<input type="hidden" name="hc" value="3" id="hc_3"></div></td>
                    <td class="2-td2-center" nowrap>
                    	<input tabindex='4' type='text' name='je' class="text-gray" 
                    	id='3_1' style='text-align:right'  readonly='true' value='' size='13' onchange="compute_Row_5(this)"></td>
                  </tr>
                  <tr> 
                    <td rowspan="3" nowrap class="2-td2-left">按成本费用核定应纳税所得额</td>
                    <td class="2-td2-left" nowrap>成本费用总额</td>
                    <td class="2-td2-left" nowrap><div align="center">4</div></td>
                    <td class="2-td2-center" nowrap>——</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>税务机关核定的应税所得率（ %）</td>
                    <td class="2-td2-left" nowrap><div align="center">5</div></td>
                    <td class="2-td2-center" nowrap>——</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>应纳税所得额[4行÷（1－5行）×5行]</td>
                    <td class="2-td2-left" nowrap><div align="center">6</div></td>
                    <td class="2-td2-center" nowrap>——</td>
                  </tr>
                  <tr> 
                    <td rowspan="4" nowrap class="2-td2-left">按经费支出换算应纳税所得额</td>
                    <td class="2-td2-left" nowrap>经费支出总额</td>
                    <td class="2-td2-left" nowrap><div align="center">7</div></td>
                    <td class="2-td2-center" nowrap>——</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>税务机关核定的应税所得率（ %）</td>
                    <td class="2-td2-left" nowrap><div align="center">8</div></td>
                    <td class="2-td2-center" nowrap>——</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>换算的收入额[7行÷（1－8行）]</td>
                    <td class="2-td2-left" nowrap><div align="center">9</div></td>
                    <td class="2-td2-center" nowrap>——</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>应纳税所得额（8行×9行）</td>
                    <td class="2-td2-left" nowrap><div align="center">10</div></td>
                    <td class="2-td2-center" nowrap>——</td>
                  </tr>
                  <tr> 
                    <td colspan="2" rowspan="3" nowrap class="2-td2-left">应纳所得税额的计算</td>
                    <td class="2-td2-left" nowrap>税率（25%）</td>
                    <td class="2-td2-left" nowrap><div align="center">11<input type="hidden" name="hc"
								value="4" id="hc_4"></div></td>
                    <td class="2-td2-center" nowrap>
                    	&nbsp;<input tabindex='5' type='text' name='je'
								id='4_1' style='text-align:right' value='' size='13' onchange="compute_Row_5(this)"
								readonly='true' class="text-gray">%</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>应纳所得税额（3行×11行）</td>
                    <td class="2-td2-left" nowrap><div align="center">12<input type="hidden" name="hc"
								value="5" id="hc_5"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='6' type='text' name='je'
								id='5_1' class="text-gray" style='text-align:right' readonly='true' value='' size='13'
								 onchange="compute_Row_7(this)" onblur="checkNumInput(this)"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>减免所得税额</td>
                    <td class="2-td2-left" nowrap><div align="center">13<input type="hidden" name="hc"
								value="6" id="hc_6"></div></td>
                    <td class="2-td2-center" nowrap>
                    	<input tabindex='7' type='text' name='je'
								id='6_1' style='text-align:right' onblur="checkNumInput(this)" value='' size='13' onchange="compute_Row_7(this)"
								 class="text-gray" readonly='true'>
								</td>
                  </tr>
                  <tr> 
                    <td colspan="2" rowspan="2" nowrap class="2-td2-left">应补（退）所得税额的计算</td>
                    <td class="2-td2-left" nowrap>已预缴所得税额</td>
                    <td class="2-td2-left" nowrap><div align="center">14<input type="hidden" name="hc"
								value="7" id="hc_7"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='8' type='text' name='je'
								id='7_1' style='text-align:right' value='' size='13'
								 onchange="compute_Row_7(this)" onblur="checkNumInput(this)" class="text-gray" readonly='true'></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap>应补（退）所得税额（12行－13行－14行）</td>
                    <td class="2-td2-left" nowrap><div align="center">15<input type="hidden" name="hc"
								value="8" id="hc_8"></div></td>
                    <td class="2-td2-center" nowrap><input tabindex='8' type='text' name='je'
								id='8_1' style='text-align:right' value='' size='13'
								 class="text-gray" readonly='true'>
								</td>
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
	<%
	  HdzssdsnbForm jbForm=(HdzssdsnbForm)request.getAttribute("hdzssdsnbForm2009");
	%>
	function doInit()	
	{
	<%
	if (jbForm!=null && jbForm.getDataList()!=null && jbForm.getDataList().size()>0){
		for(int i=0;i<jbForm.getDataList().size();i++){
			HashMap map=(HashMap)jbForm.getDataList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String je=(String)map.get("je");
			%>			
			obj = eval("document.getElementById('<%=hc%>_1')");
			obj.value = '<%=je%>';
			<% 
		}
	}
	%>
		document.forms[0].jsjdm.focus();
		<%/*企业征收类型*/%>
		var qyzslx=document.forms[0].qyzslx.value;
			
		if(qyzslx==3 || qyzslx == 2 || qyzslx == 5 ||qyzslx == 1){	
			<%/*应税所得率(亦即“纯益率”)*/%>
			<%/*设置第2行*/%>
			var obj= document.getElementById('1_1');
			obj.readOnly=false;
			obj.className='text-border';
			obj.focus();
			var obj= document.getElementById('2_1');
			var tmp = Math.round(parseFloat(getValues("cyl"))*100*100)/100;
			obj.value=tmp;
			var obj= document.getElementById('4_1');
			obj.value='25';
			var obj= document.getElementById('6_1');
			obj.readOnly=false;
			obj.className='text-border';
			var obj= document.getElementById('7_1');
			obj.readOnly=false;
			obj.className='text-border';
		}
		if(qyzslx ==4){
			<%/*定额征收*/%>
			var obj= document.getElementById('5_1');
			obj.readOnly=false;
			obj.className='text-border';
			obj.focus();
			var obj= document.getElementById('6_1');
			obj.readOnly=false;
			obj.className='text-border';
			var obj= document.getElementById('7_1');
			obj.readOnly=false;
			obj.className='text-border';
		}

	}
		<%/*响应计算机代码的回车查询*/%>
	function jsjdmQuery(){
		if(event.keyCode==13) event.keyCode = 9;
	}
	
	function ssshmx(){
		for(var i=1; i < 10; i++){
		  obj = eval("document.getElementById('" + i+ "_1')");
		  if(!numberyz(obj))
		    return;
	  }
		doSubmitForm('ToFb');
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
		if(!doCheckmethod()){
			return;
		}	
		var zczbje=document.getElementById('9_1');	
		var zcze=document.getElementById('10_1');	
		if(zcze.value.length<=0){
			alert("资产总额为必填项，请输入资产总额。");
			zcze.focus();
			zcze.select();
			return false;
		}		
		
		 if(zczbje.value.length<=0){
			alert("注册资本金额为必填项，请输入注册资本金额。");
			zczbje.focus();
			zczbje.select();
			return false;
		}		
		
		doSubmitForm('Save');	
	}
	
	 function checkZczb(){
		 var zczbje=document.getElementById('9_1');		
		
		if(!checkNumber(zczbje.value,15,2,true)){
			alert("注册资本金额必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
			zczbje.focus();
			zczbje.select();
			return false;
		}	
		return true;
	 }
	 
	 function checkZcze(){
		var zcze=document.getElementById('10_1');
		
		if(!checkNumber(zcze.value,15,2,true)){
			alert("资产总额必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
			zcze.focus();
			zcze.select();
			return false;
		}	
		return true;
	 }
	 
	
	

	//function  : 检查输入字符串是否由数字组成	//parameters: DigitString: String,待检查字符串的值	//			  totalLength: int, 数字的长度限制（不包括小数点）	//			  decimalLength: int，小数的长度限制	//            totalLength-decimalLength 整数部分的限制	//            isPositive 是否为非负数 true表示一定要是非负数，false表示一定要是负数，null表示任意 add by zhangp 2003-09-25	//return    : false: 字符串中包含除数字外的字符	//			  true : else	function checkNumber(DigitString, totalLength, decimalLength,isPositive)	{	    var re = "";	   			if(isPositive == "true" || isPositive == true)		{//如果是非负数		  if(isNaN(DigitString*1) || DigitString*1<0)				return false;		}		if(isPositive == "false" || isPositive == false)		{//如果是负数			if(isNaN(DigitString*1) || DigitString*1>=0)				return false;		}		    if (isNaN(DigitString))	    {	      return false;	    }	    if (decimalLength!=null && decimalLength != 0)	    {   //小数不为空	        re = re+"\\.[\\d]{1,"+ decimalLength +"}"	    }		    if (totalLength == null)	    {   //未设定总长度	        re= "\\d*"+re;	    }	    else	    {   //设定总长度	        //当小数部分为空的情况下，就是判断数字的长度	        var intergerLength = totalLength;	        if (decimalLength!=null)	        {   //当小数部分不为空判断，整数的位数和小数的位数是否正确	            intergerLength = totalLength-decimalLength;	        }	        re="([\\d]{0,"+ intergerLength +"}"+re+"|[\\d]{0,"+intergerLength+"})";	    }	    re = new RegExp("^[-|+]?"+re+"$","g");		    //当字符串为空、位数不对、不能匹配成数字时	    if(re.exec(DigitString) == null)	    {	        return false;	    }	    return true;	}

	
	function doCheck()
		{
			if(doCheckmethod()){
        alert("审核通过！");
			}
		}
	<%/*表内关系校验*/%>
		function doCheckmethod()
		{
			if(document.forms[0].nsrmc.value==""){
			alert("基本信息不正确,保存失败,请重新输入!");
			document.forms[0].jsjdm.focus();
			return false;
		}
		
		var info = "";
		
		//主表第13行≤第12行
		if(getValues('6_1')>getValues('5_1'))
		{
			info =  "不符合公式：主表第13行小于等于第12行，请重新输入！\n";
			alert(info);
			return false;
		}
		
		  //判断15行
		  var ybsds=Math.round((getValues('5_1')-getValues('6_1')-getValues('7_1'))*100)/100;
			if(document.getElementById('8_'+1).value!=parseFloat(ybsds)){
				info = "不符合公式：15行=12行-13行-14行，请重新输入！\n";
				alert(info);
				formate(document.getElementById('7_1'));
				return false;
			}
			
			if(info!=""){
				return false;
			}
			else{
				return true;
			}
		}
		
		function compute_Row_3(obj){
			if(!numberyz(obj))
			  return;
			var ybsds=Math.round((getValues('1_1')*getValues('2_1')*0.01)*100)/100;
			document.getElementById('3_'+1).value=parseFloat(ybsds);
			formate(document.getElementById('3_1'));
			compute_Row_5(obj);
		}
		
		function compute_Row_5(obj){
			if(!numberyz(obj))
			  return;
			var ybsds=Math.round((getValues('3_1')*getValues('4_1')*0.01)*100)/100;
			document.getElementById('5_'+1).value=parseFloat(ybsds);
			formate(document.getElementById('5_1'));
			compute_Row_7(obj);
		}
		
		function compute_Row_7(obj){
			if(!numberyz(obj))
			  return;
			var ybsds=Math.round((getValues('5_1')-getValues('6_1')-getValues('7_1'))*100)/100;
			document.getElementById('8_'+1).value=parseFloat(ybsds);
			formate(document.getElementById('8_1'));
		}
	
	  //数值验证
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

	function getValues(str){
		var strv = document.getElementById(str).value;
		if(strv=="")
		   return 0;
		else
			 return parseFloat(strv);  
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
					    if (i!=2&&i!=4&&i!=6)
						    obj.value = "0.00";
	   			}
	   		}
	   		else if(qyzslx == 4){
	   			for(var i=5; i < 9; i++){
	   				if(i!=6){
					    obj = eval("document.getElementById('" + i+ "_1')");
					    obj.value = "0.00";
					  }
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
	
		function checkNumInput(obj)

{

                   //判断输入的数据是否符合要求

                   if(!isNum(getCellObject() , null, null, null, null, 2)){

                            return false;                         

                   }

                   //格式化数据

                   formate(obj);

}

 

//验证数据的合法性

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

</script>
</body>
</html:html>