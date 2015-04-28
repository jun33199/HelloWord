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
			com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jbxxb.web.JbxxbForm2008 form = (com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jbxxb.web.JbxxbForm2008) request
			.getAttribute("jbxxbForm2008");

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

	if(parseInt(document.forms[0].sknd.value)<2008){
		alert("您正在使用的企业所得税申报资料录入系统为2008版，该版本支持的最小税款年度为2008年\n2007（含2007）以前的税款年度请使用旧版本系统！");
		document.forms[0].sknd.focus();
		document.forms[0].sknd.select();
		return false;	
	}
	
	if(parseInt(document.forms[0].sknd.value)>2008){
		alert("您正在使用的企业所得税申报资料录入系统为2008版，该版本支持的最大税款年度为2008年\n2009（含2009）以后的税款年度请使用新版本系统！");
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
	
		if(parseInt(document.forms[0].sknd.value)<2008){
			alert("您正在使用的企业所得税申报资料录入系统为2008版，该版本支持的最小税款年度为2008年\n2007（含2007）以前的税款年度请使用旧版本系统！");
			document.forms[0].sknd.focus();
			document.forms[0].sknd.select();
			return false;	
		}
		if(parseInt(document.forms[0].sknd.value)>2008){
			alert("您正在使用的企业所得税申报资料录入系统为2008版，该版本支持的最大税款年度为2008年\n2009（含2009）以后的税款年度请使用新版本系统！");
			document.forms[0].sknd.focus();
			document.forms[0].sknd.select();
			return false;	
		}
  	if(confirm("请如实填写基本信息表，如果填写错误可能会导致某些报表重复录入")){  
    	doSubmitForm("Save");
    }
    return false;
    
  }
}

//在跳转到申报资料之前，必须提示是否保存已经录入数据
function toSbzl(returnStr)
{
			window.location=returnStr;    

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

<html:form action="/webapp/smsb/qysds/2008/jbxxbAction2008.do" method="post">
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
							<td class="2-td2-t-left" nowrap colspan="3"><input
								type='text' name='sknd' id='sknd'
								value='<ttsoft:write name="jbxxbForm2008" property="sknd"  scope="request" />'
								size='4' tabindex='2' onKeyDown="jsjdmQuery()"></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>计算机代码</td>
							<td class="2-td2-left" nowrap><input type='text'
								name='jsjdm' id='jsjdm'
								value='<ttsoft:write name="jbxxbForm2008" property="jsjdm"  scope="request" />'
								size='13' tabindex='2' onKeyDown="jsjdmQuery()"></td>
							<td class="2-td2-left" nowrap>纳税人名称</td>
							<td class="2-td2-center" nowrap>
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm2008" property="nsrmc" scope="request" />							
							</div>
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>所属经济类型</td>
							<td class="2-td2-left" nowrap>
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm2008" property="ssjjlxmc" scope="request" /></div>
							</td>
							<td class="2-td2-left" nowrap>联系电话</td>
							<td class="2-td2-center" nowrap><input type='text'
								name='lxdh' id='lxdh'
								value='<ttsoft:write name="jbxxbForm2008" property="lxdh"  scope="request" />'
								size='13' tabindex='2'></td>
						</tr>
						<tr>
							<td class="2-td2-left" nowrap>所属行业</td>
							<td class="2-td2-left" nowrap >
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm2008" property="sshymc" scope="request" /></div>
							</td>
							<td class="2-td2-left" nowrap>企业所得税征收方式</td>
							<td class="2-td2-center" nowrap>
							<div align="left">&nbsp;&nbsp;<ttsoft:write
								name="jbxxbForm2008" property="zsfsmc" scope="request" /></div>
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
						<!--2008版 不要求此部分内容
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
						 <a href="javascript:toSbzl('qysdsMainAction2008.do?actionType=Show')">查帐征收企业所得税年度申报表(2008版)</a>
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
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6、企业类别：<br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一般企业：是指除金融企业，事业单位、社会团体、民办非企业单位以外的企业。<br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金融企业：是指执行《金融企业会计制度》、《企业会计准则》的商业银行、政策银行、保险公司、证券公司、信托投资公司、租赁公司、担保公司、财务公司、典当公司等金融企业。<br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;事业单位、社会团体、民办非企业单位：是指执行《事业单位会计准则》或《民间非营利组织会计制度》的企业或单位。<br>
	<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7、表中所列单项选择项：在符合选项的□中划“√”。
					</div>
	<br>
	<br>

	<%@ include file="../../include/footer.jsp"%>
</html:form>

</body>
</html>
