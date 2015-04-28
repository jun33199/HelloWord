<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>

<html>
<head>
<title>纳税人基本信息登记表</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../../js/treatImage.js"></script>
<script language=JavaScript src="../../../js/compute.js"></script>
<script language=JavaScript src="../../../js/function.js"></script>
<script language=JavaScript src="../../../js/smsb_common.js"></script>
<script language=JavaScript src="../../../js/Bolan.js"></script>
<script language=JavaScript src="../../../js/MathString.js"></script>
<script language=JavaScript src="../../../js/Stack.js"></script>
<script language=JavaScript src="../../../js/reader.js"></script>



</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onload="show()">
<%@ include file="../../include/header.jsp"%>

<br>

<%
			com.ttsoft.bjtax.smsb.sbzl.qysdsnb2013.jbxxb.web.JbxxbForm2013 form = (com.ttsoft.bjtax.smsb.sbzl.qysdsnb2013.jbxxb.web.JbxxbForm2013) request
			.getAttribute("jbxxbForm2013");

	String cwkjzddm = form.getCwkjzddm();
	String cwkjzddm_old = form.getCwkjzddm_old();
	//String gzglxsdm = form.getGzglxsdm();
	//String gzglxsdm_old = form.getGzglxsdm_old();

	//String jmlxdm = form.getJmlxdm();
	//String jmlxdm_old = form.getJmlxdm_old();
%>
<script type="text/javascript" language="JavaScript">



function show(){

<%if( cwkjzddm==null||"".equals(cwkjzddm)){ %>

document.getElementsByName("cwkjzddm")[0].checked = true;

<%}else{ %>
	<% if(!cwkjzddm.equals("99")){%>
		document.getElementById("cwkjzddm<%=cwkjzddm%>").checked = true;
	<%}%>

<%} %>

	doYwts();
}


<% /*查找*/ %>
function doQuery()
{
	if(trim(document.forms[0].jsjdm.value) == ""){
		alert("请输入纳税企业计算机代码！");
		document.forms[0].jsjdm.focus();
		return false;
	}

	if(trim(document.forms[0].sknd.value) == ""){
		alert("请输入年度！");
		document.forms[0].sknd.focus();
		return false;
	}
	
	if(!isDigit(document.forms[0].sknd.value)||document.forms[0].sknd.value.length!=4){
		alert("年度必须为四位数字，请重新输入！");
		document.forms[0].sknd.focus();
		document.forms[0].sknd.select();
		return false;
	}

	if(parseInt(document.forms[0].sknd.value)<2009){
		alert("您正在使用的企业所得税申报资料录入系统为2009版，该版本支持的最小税款年度为2009年\n2008（含2008）以前的税款年度请使用旧版本系统！");
		document.forms[0].sknd.focus();
		document.forms[0].sknd.select();
		return false;	
	}
	
	doSubmitForm('Query');
	return false;
}


//响应计算机代码、年度的修改
function doChange(){

  var jsjdm;
  var sknd;
  jsjdm = document.forms[0].jsjdm.value;
  sknd = document.forms[0].sknd.value;

  if(jsjdm=="" || sknd==""){

    return false;
  
  }else{

    doQuery();  

  }
	
}
//计算机代码进行了修改需重新进行查询  故修改查询标志
//add by wangcy  2013-12-06
function doChangeQuery(){
	document.forms[0].isQuery.value="0";
}

//响应计算机代码的回车查询
function jsjdmQuery(){
	if(event.keyCode==13) doQuery();
}

//保存
function doSave()
{  
  
  var jsjdm;
  var nsrmc;
  jsjdm = document.forms[0].jsjdm.value;
  nsrmc = document.forms[0].nsrmc.value;
  var ckzd=document.getElementsByName("cwkjzddm");
  
  var flag=false;
  for(var i=0;i<3;i++){
  	if(ckzd[i].checked){
  		flag=true;
  	}
  }
  if(!flag){
  	alert("企业类别为必选项，请选择！");
  	return false;
  }
  var zcze=document.forms[0].zcze;
  var zczbje=document.forms[0].zczbje;	
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
  if(!isDigit(document.forms[0].sknd.value)||document.forms[0].sknd.value.length!=4){
			alert("年度必须为四位数字，请重新输入！");
			document.forms[0].sknd.focus();
			document.forms[0].sknd.select();
			return false;
	}
	

		
  if(jsjdm==""){
    alert("请填写计算机代码，确认信息无误后再进行保存！");
    document.forms[0].jsjdm.focus();
		document.forms[0].jsjdm.select();
    return false;
  }else{
		if(nsrmc==""){
			alert("该纳税人的基本信息还没带出，请查询带出基本信息后再进行保存！\n计算机代码框内敲“回车”键或点击查询按钮可执行查询。");
			document.forms[0].jsjdm.focus();
			return false;
		}
		var isQuery=document.forms[0].isQuery.value;
	  	if(isQuery!="1"){
	  		alert("计算机代码或税款年度进行了修改,请重新执行查询动作！");
	  		return false;
	  	}
		if(parseInt(document.forms[0].sknd.value)<2013){
			alert("您正在使用的企业所得税申报资料录入系统为2013版，该版本支持的最小税款年度为2013年\n2012（含2012）以前的税款年度请使用旧版本系统！");
			document.forms[0].sknd.focus();
			document.forms[0].sknd.select();
			return false;	
		}
		if(!doYwts()){
		
			return false;
		}	
  	
	  	if(confirm("请如实填写基本信息表，如果填写错误可能会导致某些报表重复录入")){
	  		if(parseInt(document.forms[0].sknd.value)>=2013){
	  			doSubmit2013();
	  		}
	    }
    return false;
    
  }
}
	/**
	*针对税款年度为2013年以后的添加总地分的判断
	*如果是总的20140201年以后可以登记基本信息
	*/
	function doSubmit2013()
	{
		var sybs=document.forms[0].sybs.value;//税源标识
	  	var bbmsf_old=document.forms[0].bbmsf.value;//老的需要bbmsf用户填写的表
  		var bbmsf_new="";//新的需要填写bbmsf表
  		var ckzddm="";//财务会计代码
  		var ckzd=document.getElementsByName("cwkjzddm");
		for(var i=0;i<3;i++){
			if(ckzd[i].checked){
			   ckzddm=ckzd[i].value;
			}
		}
		//根据选择的财务会计制度重新分配要填写的表	
		if(ckzddm==<%=CodeConstant.CWKJZD01 %>){
			bbmsf_new="<%= CodeConstant.BBMSF_2009_10%>";
		}else if(ckzddm==<%=CodeConstant.CWKJZD02 %>){
			bbmsf_new="<%= CodeConstant.BBMSF_2009_20%>";
		}else if(ckzddm==<%=CodeConstant.CWKJZD03 %>){
			bbmsf_new="<%= CodeConstant.BBMSF_2009_30%>";
		}
		
		if(sybs!=<%=CodeConstant.CODE_QYSDS_ZGFWJD_OTHER %>){
			//税源标识为总的的进行处理
	  		if(sybs==<%=CodeConstant.CODE_QYSDS_ZGFWJD_KSSZJG %>){
	  			var tbrq=document.forms[0].tbrq.value;
	  			var sbrq_q="20140201";
	  			if(tbrq<sbrq_q){
	  				alert("请在2014年2月1日（含）之后填写基本信息登记表!");
	  				return false;
	  			}else{
	  				bbmsf_new+="<%= CodeConstant.BBMSF_2012_17%>";
	  				doSumbit2013Ts(bbmsf_old,bbmsf_new);
	  			}
	  		}
	  		//税源标识为地的进行处理
	  		if(sybs==<%=CodeConstant.CODE_QYSDS_ZGFWJD_DLNSR %> || sybs==<%=CodeConstant.CODE_QYSDS_ZGFWJD_ZFJGJZBSZJG%>){
	  			doSumbit2013Ts(bbmsf_old,bbmsf_new);
	  		}
  		}
	}
	
	//提交2013以后数据进行判断是否提示
	function doSumbit2013Ts(bbmsf_old,bbmsf_new){
		var queryFlag=document.forms[0].queryFlag.value;
		var ywts="该企业已进行"+document.forms[0].sknd.value+"年度企业所得税汇算清缴申报，本次变更将清除当期申报数据，是否清除?";
		if(queryFlag =="1" && bbmsf_new!=bbmsf_old){
  			if(confirm(ywts)){
  				doSubmitForm("Save");
  			}
  		}else{
  			doSubmitForm("Save");
  		}
	}
	
 	function checkZczb(){
		 var zczbje=document.forms[0].zczbje;		
		 
		if(!checkNumber(zczbje.value,15,2,true)){
			alert("注册资本金额必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
			zczbje.focus();
			zczbje.select();
			return false;
		}
		if(zczbje.value>100000){
			alert("注册资本金额超过10亿元，请核实！");
		}	
		return true;
	 }
	 
	 function checkZcze(){
		var zcze=document.forms[0].zcze;		
		
		if(!checkNumber(zcze.value,15,2,true)){
			alert("资产总额必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
			zcze.focus();
			zcze.select();
			return false;
		}
		if(zcze.value>100000){
			alert("资产总额金额超过10亿元，请核实！");
		}		
		return true;
	 }

//function  : 检查输入字符串是否由数字组成
//parameters: DigitString: String,待检查字符串的值
//			  totalLength: int, 数字的长度限制（不包括小数点）
//			  decimalLength: int，小数的长度限制
//            totalLength-decimalLength 整数部分的限制
//            isPositive 是否为非负数 true表示一定要是非负数，false表示一定要是负数，null表示任意 add by zhangp 2003-09-25
//return    : false: 字符串中包含除数字外的字符
//			  true : else
function checkNumber(DigitString, totalLength, decimalLength,isPositive)
{
    var re = "";
   

	if(isPositive == "true" || isPositive == true)
	{//如果是非负数
	  if(isNaN(DigitString*1) || DigitString*1<0)
			return false;
	}
	if(isPositive == "false" || isPositive == false)
	{//如果是负数
		if(isNaN(DigitString*1) || DigitString*1>=0)
			return false;
	}

    if (isNaN(DigitString))
    {
      return false;
    }
    if (decimalLength!=null && decimalLength != 0)
    {   //小数不为空
        re = re+"\\.[\\d]{1,"+ decimalLength +"}"
    }

    if (totalLength == null)
    {   //未设定总长度
        re= "\\d*"+re;
    }
    else
    {   //设定总长度
        //当小数部分为空的情况下，就是判断数字的长度
        var intergerLength = totalLength;
        if (decimalLength!=null)
        {   //当小数部分不为空判断，整数的位数和小数的位数是否正确
            intergerLength = totalLength-decimalLength;
        }
        re="([\\d]{0,"+ intergerLength +"}"+re+"|[\\d]{0,"+intergerLength+"})";
    }
    re = new RegExp("^[-|+]?"+re+"$","g");

    //当字符串为空、位数不对、不能匹配成数字时
    if(re.exec(DigitString) == null)
    {
        return false;
    }
    return true;
}

//在跳转到申报资料之前，必须提示是否保存已经录入数据
function toSbzl(returnStr)
{
			window.location=returnStr;    

}

//针对税源标识为不是总地的进行提示
function doYwts(){
	

	//如果税款年度大于等于2013年 对税源标识不是总和分的进行提示
	var sybs=document.forms[0].sybs.value;//税源标识
	if(parseInt(document.forms[0].sknd.value)>=2013){
		if(sybs!=<%=CodeConstant.CODE_QYSDS_ZGFWJD_OTHER %>){
		  	//税源标识为分的进行处理
		  	if(sybs==<%=CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG %>){
		  		alert("该企业只需填报企业所得税分支机构年度纳税申报表！");
		  		return false;
		  	}
	  	}else{
	  		alert("该企业的企业所得税不由地方税务局管辖！");
	  		return false;
	  	}
	}
	return true;
}
/**	//退出
function tuichu(){
	if(returnStr==null || returnStr==""){
		returnStr="zhsbAction.do";
	}
	//如果是由综合申报进入申报资料页面，则退出到综合申报页面
	if(document.all.iszhsb && document.all.iszhsb.value=='1')
		returnStr="zhsbAction.do?actionType=Show";
	window.location=returnStr;
}**/

</script>

<html:form action="/webapp/smsb/qysds/2013/jbxxbAction2013.do" method="post">
	<html:hidden property="actionType" />
	<html:hidden property="nsrsbh"/>
	<html:hidden property="nsrmc"/>
	<html:hidden property="sbnd"/>
	<html:hidden property="ssjjlxdm"/>
	<html:hidden property="ssjjlxmc"/>
	<html:hidden property="jydz"/>
	<html:hidden property="sshydm"/>
	<html:hidden property="sshymc"/>
	<html:hidden property="zsfsdm"/>
	<html:hidden property="zsfsmc"/>
	<html:hidden property="cwkjzddm_old"/>
	<html:hidden property="swjgzzjgdm"/>
	<html:hidden property="swjgzzjgmc"/>
	<html:hidden property="cjr"/>
	<html:hidden property="cjrq"/>
	<html:hidden property="lrr"/>
	<html:hidden property="lrrq"/>
	<html:hidden property="xtjb"/>
	<html:hidden property="bbmsf"/>
	<html:hidden property="skssksrq"/>
	<html:hidden property="skssjsrq"/>
	<html:hidden property="iszhsb"/>
	<html:hidden property="sybs"/>
	<html:hidden property="isQuery"/>
	<html:hidden property="tbrq"/>
	<html:hidden property="queryFlag"/>
	<table width="96%" align="center" cellspacing="0" class="table-99">
		<tr>
			<td class="1-td1" colspan="7">纳税人基本信息登记表</td>
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
							<td class="2-td2-t-left" nowrap>税款年度</td>
							<td class="2-td2-t-left" nowrap><input
								type='text' name='sknd' id='sknd'
								value='<ttsoft:write name="jbxxbForm2013" property="sknd"  scope="request" />'
								size='4' tabindex='2' onKeyDown="jsjdmQuery()" onchange="doChangeQuery()"></td>
							<td class="2-td2-t-left" nowrap>税源标识</td>
							<td class="2-td2-t-center" nowrap="nowrap">
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm2013" property="sybsmc" scope="request" />							
							</div>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>计算机代码</td>
							<td class="2-td2-left" nowrap><input type='text'
								name='jsjdm' id='jsjdm'
								value='<ttsoft:write name="jbxxbForm2013" property="jsjdm"  scope="request" />'
								size='13' tabindex='2' onKeyDown="jsjdmQuery()" onchange="doChangeQuery()" ></td>
							<td class="2-td2-left" nowrap>纳税人名称</td>
							<td class="2-td2-center" nowrap>
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm2013" property="nsrmc" scope="request" />							
							</div>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>所属经济类型</td>
							<td class="2-td2-left" nowrap>
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm2013" property="ssjjlxmc" scope="request" /></div>
							</td>
							<td class="2-td2-left" nowrap>联系电话</td>
							<td class="2-td2-center" nowrap><input type='text'
								name='lxdh' id='lxdh'
								value='<ttsoft:write name="jbxxbForm2013" property="lxdh"  scope="request" />'
								size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>所属行业</td>
							<td class="2-td2-left" nowrap >
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm2013" property="sshymc" scope="request" /></div>
							</td>
							<td class="2-td2-left" nowrap>企业所得税征收方式</td>
							<td class="2-td2-center" nowrap>
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm2013" property="zsfsmc" scope="request" /></div>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>企业类别（必选）</td>
							<td colspan="3" class="2-td2-center" nowrap>单项选择：（对报表有控制）<br>
							<input type="Radio" name="cwkjzddm"
								id="cwkjzddm<%=CodeConstant.CWKJZD01 %>"
								value="<%=CodeConstant.CWKJZD01 %>">一般企业； <input
								type="Radio" name="cwkjzddm" id="cwkjzddm<%=CodeConstant.CWKJZD02 %>"
								value="<%=CodeConstant.CWKJZD02	%>">金融企业会计制度； <input
								type="Radio" name="cwkjzddm" id="cwkjzddm<%=CodeConstant.CWKJZD03 %>"
								value="<%=CodeConstant.CWKJZD03 %>">事业单位、社会团体、民办非企业单位会计制度
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>注册资本金额（单位：万元）</td>
							<td class="2-td2-left" nowrap >
								<input type='text' name='zczbje' id='zczbje' onblur='checkZczb()'
								value='<ttsoft:write name="jbxxbForm2013" property="zczbje"  scope="request" />'
								size='13' tabindex='1'>
							</td>
							<td class="2-td2-left" nowrap>资产总额（单位：万元）</td>
							<td class="2-td2-center" nowrap>
								<input type='text' name='zcze' id='zcze' onblur='checkZcze()'
								value='<ttsoft:write name="jbxxbForm2013" property="zcze"  scope="request" />'
								size='13' tabindex='2'>
							</td>
						</tr>
						<!--2009版 不要求此部分内容
						<tr>
							<td class="2-td2-left" nowrap>工资管理形式</td>
							<td colspan="3" class="2-td2-center" nowrap>单项选择：<br>
							<input type="Radio" name="gzglxsdm"
								id="gzglxsdm<%=CodeConstant.GZGLXS01 %>"
								value="<%=CodeConstant.GZGLXS01 %>">非工效挂钩 <input
								type="Radio" name="gzglxsdm" id="gzglxsdm<%=CodeConstant.GZGLXS03 %>"
								value="<%=CodeConstant.GZGLXS03 %>">工效挂钩（对报表有控制）</td>
						</tr>
						
						<tr>
							<td colspan="4" class="2-td2-center" nowrap>纳税人享受减免企业所得税的类型</td>
						</tr>
						
						<tr>
							<td colspan="4" class="2-td2-center" nowrap>单项选择：<br>
							<input type="Radio" name="jmlxdm" id="jmlxdm<%=CodeConstant.JMLXNO %>"
								value="<%=CodeConstant.JMLXNO %>">1、 没有优惠； <input
								type="Radio" name="jmlxdm" id="jmlxdm<%=CodeConstant.JMLX9010 %>"
								value="<%=CodeConstant.JMLX9010 %>">2、 高新技术企业； <input
								type="Radio" name="jmlxdm" id="jmlxdm<%=CodeConstant.JMLX9020 %>"
								value="<%=CodeConstant.JMLX9020 %>">3、 软件产业； <input
								type="Radio" name="jmlxdm" id="jmlxdm<%=CodeConstant.JMLX9030 %>"
								value="<%=CodeConstant.JMLX9030 %>">4、 集成电路产业； <input
								type="Radio" name="jmlxdm" id="jmlxdm<%=CodeConstant.JMLX9090 %>"
								value="<%=CodeConstant.JMLX9090 %>">5、福利生产企业； <input
								type="Radio" name="jmlxdm" id="jmlxdm<%=CodeConstant.JMLX9070 %>"
								value="<%=CodeConstant.JMLX9070 %>">6、劳动就业服务企业； <input
								type="Radio" name="jmlxdm" id="jmlxdm<%=CodeConstant.JMLXOTHER %>"
								value="<%=CodeConstant.JMLXOTHER %>">7、其他优惠；</td>
						</tr>
						-->
					</table>
					</div>
					</TD>
				</TR>
				<TR class="black9">
					<TD><br>
					<div align="center">
					 
                   &nbsp;&nbsp;&nbsp;&nbsp;
                   <img onclick="doQuery();return false;"   onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg ',1)" onMouseOut="MM_swapImgRestore()" value="查询" id="tc1" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" style="cursor:hand"/>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <img onclick="doSave();return false;"  onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="保存" id="tc1" src="<%=static_contextpath%>images/bc-s1.jpg" width="79" height="22" style="cursor:hand"/>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <img onclick="tuichu()"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="退出" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22" style="cursor:hand"/>
                  </div>
					</TD>
				</TR>
				<TR class="black9">
					<TD><br>
						<div align="left">
						 <a href="javascript:toSbzl('/smsb/webapp/smsb/qysds/2009/qysdsMainAction2009.do?actionType=Show')">查帐征收企业所得税年度申报表(2013版)</a>
	                     </div>
					</TD>
				</TR>
			</TABLE>
			</td>
		</tr>
	</table>
	<br>
	<div align="left">
					<font size="2">&nbsp;&nbsp;&nbsp;填报要求：纳税人年度申报企业所得税时须填报此表。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;填报说明：</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、纳税人名称：填报税务登记证所载纳税人的全称。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、计算机代码：填写地税机关核发的征收管理码。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、联系电话：填写纳税人单位办税人员联系电话（或手机号码）。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、所属经济类型、所属行业：按照税务登记表中的有关内容填写。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5、企业所得税征收方式：选择核定征收的企业，是指由税务机关根据其生产经营情况或财务会计核算情况，按照规定的标准、程序、权限和方法，核定应税所得率（纯益率）或应纳税额的一种征收方式。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6、企业类别：</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一般企业：是指除金融企业，事业单位、社会团体、民办非企业单位以外的企业。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金融企业：是指执行《金融企业会计制度》、《企业会计准则》的商业银行、政策银行、保险公司、证券公司、信托投资公司、租赁公司、担保公司、财务公司、典当公司等金融企业。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;事业单位、社会团体、民办非企业单位：是指执行《事业单位会计准则》或《民间非营利组织会计制度》的企业或单位。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7、注册资本：企业法人按照企业法人营业执照上的注册资本填写；事业单位按照《事业单位法人证书》的开办资金填写；社会团体按照《社会团体法人登记证书》的注册资金填写；民办非企业法人单位按照《民办非企业单位登记证书》的开办资金填写。。</font><br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;8、资产总额：指企业拥有或控制的全部资产。包括流动资产、长期投资、固定资产、无形及递延资产、其他长期资产等，即为企业资产负债表的资产总计项。</font><br> 
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;9、表中所列单项选择项：在符合选项的□中划“√”。</font>
					</div>
	<br>
	<br>

	<%@ include file="../../include/footer.jsp"%>
</html:form>

</body>
</html>
