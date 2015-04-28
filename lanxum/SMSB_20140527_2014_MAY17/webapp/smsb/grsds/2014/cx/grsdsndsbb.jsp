<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html>
<head>
<title>录入个人所得税基础信息表信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/swapImages.js"></script>
<script language="JavaScript" type="text/JavaScript" src="../../js/smsb_common.js"></script>
<script language=JavaScript>
function doReturn()
{
	document.forms[0].action = "quit.do";
	document.forms[0].submit();
}

function load(){
	
	var btzzxx_djzclx = document.getElementById("btzzxx_djzclx");				//纳税人类型
	var btzzxx_djzclx_cb = document.getElementsByName("btzzxx_djzclx_cb");
	splitCheckBox(btzzxx_djzclx,btzzxx_djzclx_cb);
	
	if(document.all.btzzxx_djzclx_3.checked) {//纳税人为合伙企业合伙人
		document.getElementById("hc_43").innerHTML="43=(38-39)*40-41-42";
	} else if(!document.all.btzzxx_djzclx_3.checked) {//纳税人为非合伙企业合伙人
		document.getElementById("hc_43").innerHTML="43=38-39-41-42";
	}
	
	var msg="<bean:write name='grsdsndsbbForm2014cx' property='msg'/>";
	if(msg){
		alert(msg);		
	}
}
function joinCheckBox(obj1,obj2){
	var length = obj2.length;
	var result="";
	for(var i=0;i<length;i++){
		if(obj2[i].checked){
			result+=obj2[i].value;
			result+="|";
		}
	}
	obj1.value=result;
}

function splitCheckBox(obj1,obj2){
	var values = obj1.value.split("|");
	for(var i=0;i<values.length;i++){
		for(var j=0;j<obj2.length;j++){
			if(values[i]==obj2[j].value){
				obj2[j].checked=true;
			}
		}
	}
}
function doBack(){
	var actionType = document.getElementById("actionType");
	actionType.value="Back";
	document.forms[0].submit();
}
function doPrevious(){
	var actionType = document.getElementById("actionType");
	actionType.value="Previous";
	document.forms[0].submit();
}
function clickTbsm(){
	var txsm = document.getElementById("tbsm");
	var display = txsm.style.display;
	if(display=="none"){
		txsm.style.display="";
	}else{
		txsm.style.display="none";
	}
}
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0" onload="load()">
		<jsp:include page="../../../include/header.jsp" flush="true" >
    		<jsp:param name="name" value="生产、经营所得个人所得税纳税申报表" />
			<jsp:param name="help_url" value="help/wssb/qyzhsb/gzsx/gzsx-000.htm"/>
    	</jsp:include>
<html:form action="/webapp/smsb/grsds/grsdsNdsbbAction2014cx.do">
<table width="80%" height="50%" border="0" align="center" cellpadding="0" cellspacing="0" >
	
	<bean:define id="sfzjlxListID" name="grsdsndsbbForm2014cx" property="sfzjlxList"/>	<!-- 身份证件类型代码表 -->
	<bean:define id="gjListID" name="grsdsndsbbForm2014cx" property="gjList"/>			<!-- 国籍 -->
	<input type="hidden" name="actionType" id="actionType" value="Show" />
			<!--录入信息-->
			<tr>
				<td class="1-td1" >录入生产、经营所得个人所得税纳税申报表</td>
			</tr>

			<tr>
				<td class="1-td2" >
					<br/>
					<div align="left" style="display:inline">
						<FONT color="#000000" size="1">税款所属期：</FONT>
						<html:hidden name="grsdsndsbbForm2014cx" property="nd"></html:hidden>
						<html:text styleId="skssqq" name="grsdsndsbbForm2014cx" disabled="true" property="skssqq" />至
						<html:text styleId="skssqz" name="grsdsndsbbForm2014cx" disabled="true" property="skssqz" />
					</div>
					<div align="right" style="display:inline"><FONT color="#000000" size="1">金额单位：人民币元（列至角分）</FONT></div>
					<table  cellSpacing="0" cellPadding="0" width="97%" border="0" align = "center">
						<tr>
							<td class="2-td2-t-left" rowspan="2" width="10%">投资者信息</td>
							<td class="2-td2-t-left" width="10%">姓名</td>
							<td class="2-td2-t-left" width="10%">
								<bean:write name="grsdsndsbbForm2014cx" property="tzzxx_name"/>&nbsp;
							</td>
							<td class="2-td2-t-left" width="10%">身份证件类型</td>
							<td class="2-td2-t-left" width="10%">
								<html:hidden name="grsdsndsbbForm2014cx" property="tzzxx_sfzjlx"></html:hidden>
								<html:select disabled="true" name="grsdsndsbbForm2014cx" property="tzzxx_sfzjlx" >
                          				<html:options collection="sfzjlxListID" property="value" labelProperty="text" />
                          		</html:select>
							</td>
							<td class="2-td2-t-left" width="10%">身份证件号码</td>
							<td class="2-td2-t-center" colspan="4" width="40%">
								<html:hidden name="grsdsndsbbForm2014cx" property="tzzxx_sfzjhm"></html:hidden>
								<bean:write name="grsdsndsbbForm2014cx" property="tzzxx_sfzjhm"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" width="10%">国籍</td>
							<td class="2-td2-left" colspan="3" width="30%">
								<html:select  name="grsdsndsbbForm2014cx" property="tzzxx_gj" disabled="true">
                          				<html:options collection="gjListID" property="value" labelProperty="text" />
                          		</html:select>
							</td>
							<td class="2-td2-left" width="10%">纳税人识别号</td>
							<td class="2-td2-center" colspan="4" width="40%">
								<bean:write name="grsdsndsbbForm2014cx" property="tzzxx_nsrsbh"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" rowspan="2" width="10%" >被投资单位信息</td>
							<td class="2-td2-left" width="10%">名称</td>
							<td class="2-td2-left" colspan="3" width="30%">
								
								<html:hidden property="btzzxx_jsjdm" name="grsdsndsbbForm2014cx" />
								
								<bean:write name="grsdsndsbbForm2014cx" property="btzzxx_name"/>&nbsp;
							
							</td>
							<td class="2-td2-left" width="10%">纳税人识别号</td>
							<td class="2-td2-center" colspan="4" width="40%">
									<bean:write name="grsdsndsbbForm2014cx" property="btzzxx_nsrsbh"/>&nbsp;
								</input>
							</td>
						</tr>
						<tr>
							
							<td class="2-td2-left" width="10%">类型</td>
							<td class="2-td2-center" colspan="8" width="80%">
								<html:hidden property="btzzxx_djzclx" name="grsdsndsbbForm2014cx" />
								<input type='checkbox' name='btzzxx_djzclx_cb' id="btzzxx_djzclx_0" value="0" disabled="disabled">个体工商户</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='btzzxx_djzclx_cb' id="btzzxx_djzclx_1" value="1" disabled="disabled">承包、承租经营者</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='btzzxx_djzclx_cb' id="btzzxx_djzclx_2" value="2" disabled="disabled">个人独资企业</input>&#160;&#160;&#160;&#160;
								<input type='checkbox' name='btzzxx_djzclx_cb' id="btzzxx_djzclx_3" value="3" disabled="disabled">合伙企业</input>
							</td>
						</tr>
						
						<tr>
							<td class="2-td2-left" style="border-bottom-width:0px" width="10%">年平均职工人数</td>
							<td class="2-td2-left" style="border-bottom-width:0px" colspan="2" width="20%">
								<bean:write name="grsdsndsbbForm2014cx" property="btzzxx_npjzgrs"/>&nbsp;
							</td>
							<td class="2-td2-left" style="border-bottom-width:0px" width="10%">工资总额（元）</td>
							<td class="2-td2-left" style="border-bottom-width:0px" colspan="2" width="20%">
								<bean:write name="grsdsndsbbForm2014cx" property="btzzxx_gzze"/>&nbsp;
							</td>
							<td class="2-td2-left" style="border-bottom-width:0px" width="10%">投资者人数（人）</td>
							<td class="2-td2-center" style="border-bottom-width:0px" colspan="3" width="30%">
								<bean:write name="grsdsndsbbForm2014cx" property="btzzxx_tzzrs"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td1-left" style="border-bottom-size:0" colspan="2" width="20%">项目</td>
							<td class="2-td1-left" style="border-bottom-size:0" width="10%">行次</td>
							<td class="2-td1-left" style="border-bottom-size:0" colspan="2" width="20%">金额</td>
							<td class="2-td1-left" style="border-bottom-size:0" colspan="2" width="20%">项目</td>
							<td class="2-td1-left" style="border-bottom-size:0" width="10%">行次</td>
							<td class="2-td1-center" style="border-bottom-size:0" colspan="2" width="20%">金额</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">一、收入总额</td>
							<td class="2-td2-left">1</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_1"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">税收滞纳金、罚金、罚款</td>
							<td class="2-td2-left">27</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_27"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">减：成本</td>
							<td class="2-td2-left">2</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_2"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">赞助支出、非教育和公益事业捐赠</td>
							<td class="2-td2-left">28</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_28"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">营业费用</td>
							<td class="2-td2-left">3</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_3"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">灾害事故损失赔偿</td>
							<td class="2-td2-left">29</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_29"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">管理费用</td>
							<td class="2-td2-left">4</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_4"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">计提的各种准备金</td>
							<td class="2-td2-left">30</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_30"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">财务费用</td>
							<td class="2-td2-left">5</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_5"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">投资者工资薪金</td>
							<td class="2-td2-left">31</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_31"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">营业税金及附加</td>
							<td class="2-td2-left">6</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_6"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">与收入无关的支出</td>
							<td class="2-td2-left">32</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_32"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">营业外支出</td>
							<td class="2-td2-left">7</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_7"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">其中：投资者家庭费用</td>
							<td class="2-td2-left">33</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_33"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">二、利润总额</td>
							<td class="2-td2-left">8=1-2-3-4-5-6-7</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_8"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">四、纳税调整减少额</td>
							<td class="2-td2-left">34</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_34"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">三、纳税调整增加额</td>
							<td class="2-td2-left">9</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_9"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">1、国债利息收入</td>
							<td class="2-td2-left">35</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_35"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">1、超过规定标准扣除的项目</td>
							<td class="2-td2-left">10</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_10"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">2、其他</td>
							<td class="2-td2-left">36</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_36"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">（1）职工福利费</td>
							<td class="2-td2-left">11</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_11"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">五、以前年度损益调整</td>
							<td class="2-td2-left">37</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_37"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">（2）职工教育经费</td>
							<td class="2-td2-left">12</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_12"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">六、经纳税调整后的生产经营所得</td>
							<td class="2-td2-left">38=8+9-34-37</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_38"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">（3）公会经费</td>
							<td class="2-td2-left">13</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_13"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">减：弥补以前年度亏损</td>
							<td class="2-td2-left">39</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_39"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">（4）利息支出</td>
							<td class="2-td2-left">14</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_14"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">乘：分配比率</td>
							<td class="2-td2-left">40</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_40"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">（5）业务招待费</td>
							<td class="2-td2-left">15</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_15"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">七、允许扣除的其他费用</td>
							<td class="2-td2-left">41</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_41"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">（6）广告费和业务宣传费</td>
							<td class="2-td2-left">16</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_16"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">八、投资者减除费用</td>
							<td class="2-td2-left">42</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_42"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">（7）教育和公益事业捐赠</td>
							<td class="2-td2-left">17</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_17"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">九、应纳税所得额</td>
							<td class="2-td2-left" id ="hc_43">43</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_43"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">（8）住房公积金</td>
							<td class="2-td2-left">18</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_18"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">十、税率（%）</td>
							<td class="2-td2-left">44</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_44"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">（9）社会保险费</td>
							<td class="2-td2-left">19</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_19"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">十一、速算扣除数</td>
							<td class="2-td2-left">45</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_45"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">（10）折旧费用</td>
							<td class="2-td2-left">20</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_20"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">十二、应纳税额</td>
							<td class="2-td2-left">46=43*44-45</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_46"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">（11）无形资产摊销</td>
							<td class="2-td2-left">21</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_21"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">减：减免税额</td>
							<td class="2-td2-left">47</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_47"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">（12）资产损失</td>
							<td class="2-td2-left">22</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_22"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">十三、全年应缴税额</td>
							<td class="2-td2-left">48=46-47</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_48"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">（13）其他</td>
							<td class="2-td2-left">23</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_23"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">加：期初未缴税额</td>
							<td class="2-td2-left">49</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_49"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">不允许扣除的项目</td>
							<td class="2-td2-left">24</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_24"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">减：全年已预缴税额</td>
							<td class="2-td2-left">50</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_50"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">资本性支出</td>
							<td class="2-td2-left">25</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_25"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">十四、应补（退）税额</td>
							<td class="2-td2-left">51=48+49-50</td>
							<td class="2-td2-center" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_51"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="2-td2-left" colspan="2">无形资产受让、开发支出</td>
							<td class="2-td2-left">26</td>
							<td class="2-td2-left" colspan="2">
								<bean:write name="grsdsndsbbForm2014cx" property="col_26"/>&nbsp;
							</td>
							<td class="2-td2-left" colspan="2">&#160;</td>
							<td class="2-td2-left">&#160;</td>
							<td class="2-td2-center" colspan="2">&#160;</td>
						</tr>
					</table>
					<br/>

				</td>
			</tr>
		
		
</table>

<div  align="center">
	<span>
		<!-- <a style="cursor:hand"  tabIndex="-1" onClick="doSave();return false;"><img name="baocun" value="保存" alt="保存" onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>/images/baocun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/baocun1.jpg" width="79" height="22" id="baocun"></a>&nbsp;&nbsp;-->
		<a style="cursor:hand"  tabIndex="-1" onClick="doPrevious();return false;"><img name="shangyiye" value="上一页" alt="上一页" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>/images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>&nbsp;&nbsp;
		<a style="cursor:hand"  tabIndex="-1" onClick="doBack();return false;"><img name="fanhui" value="返回" alt="返回" onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>/images/fanhui2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/fanhui1.jpg" width="79" height="22" id="fanhui"></a>&nbsp;&nbsp;
		<a style="cursor:hand"  tabIndex="-1" onClick="tuichu();return false;"><img  name="return" value="退出" alt="退出" onMouseOver="MM_swapImage('return','','<%=static_contextpath%>/images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>/images/tuichu1.jpg"  width="79" height="22" id="return"></a> 
	</span>			
</div>
<br/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
				 <tr>
				    <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				    <td width="20%" align="center" class="black9">	<a style="cursor:hand;text-decoration:underline;"  tabIndex="-1" onClick="javascript:clickTbsm();"><strong><font color="#0000FF">请点击查看填表说明</font></strong></a></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47" id="tbsm" style="display:none;">
				        	
						一、	适用范围<br/>
&nbsp;本表适用于查账征收“个体工商户的生产、经营所得”和“对企事业单位的承包经营、承租经营所得”个人所得税的个体工商户、承包承租经营者、个人独资企业投资者和合伙企业合伙人的个人所得税年度汇算清缴。纳税人在办理申报时，须同时附报附件2—《个人所得税基础信息表（B表）》。<br/>
&nbsp;合伙企业有两个或两个以上自然人投资者的，应分别填报本表。<br/>
二、	申报期限<br/>
&nbsp;个体工商户、个人独资企业投资者、合伙企业合伙人的生产、经营所得应纳个人所得税的年度纳税申报，应在年度终了后三个月内办理。<br/>
&nbsp;对企事业单位承包经营、承租经营者应纳个人所得税的年度纳税申报，应在年度终了后三十日内办理；纳税人一年内分次取得承包、承租经营所得的，应在年度终了后三个月内办理汇算清缴。<br/>
&nbsp;纳税人不能按规定期限办理纳税申报的，应当按照《中华人民共和国税收征收管理法》（以下简称税收征管法）及其实施细则的规定办理延期申报。<br/>
三、	本表各栏填写如下：<br/>
&nbsp;（一）表头项目<br/>
&nbsp;税款所属期：是指纳税人取得所得的应纳个人所得税款的所属期间，应填写具体的起止年月日。<br/>
&nbsp;（二）表内信息栏<br/>
&nbsp;&nbsp;1.	投资者信息栏：填写个体工商户业主、承包经营者、承租经营者、个人独资企业投资者、合伙企业合伙人的相关信息。<br/>
&nbsp;&nbsp;&nbsp;（1）	姓名：填写纳税人姓名。中国境内无住所个人，其姓名应当用中、外文同时填写。<br/>
&nbsp;&nbsp;&nbsp;（2）	身份证件类型：填写能识别纳税人唯一身份的有效证照名称。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;①	在中国境内有住所的个人，填写身份证、军官证、士兵证等证件名称。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;②	在中国境内无住所的个人，填写护照、港澳居民来往内地通行证、台湾居民来往大陆通行证等证照名称。<br/>
&nbsp;&nbsp;&nbsp;（3）	身份证件号码：填写纳税人身份证件上的号码。<br/>
&nbsp;&nbsp;&nbsp;（4）	国籍（地区）：填写纳税人的国籍或者地区。<br/>
&nbsp;&nbsp;&nbsp;（5）	纳税人识别号：在中国境内无住所的个人填写。有住所的个人不填写。该栏填写税务机关赋予的18位纳税人识别号。税务机关未赋予的，不填写。<br/>
&nbsp;&nbsp;&nbsp;税务机关赋予境内无住所个人的18位纳税人识别号，作为其唯一身份识别码，由纳税人到主管税务机关办理初次涉税事项，或扣缴义务人办理该纳税人初次扣缴申报时，由主管税务机关赋予。<br/>
&nbsp;&nbsp;2.	被投资单位信息栏：<br/>
&nbsp;&nbsp;&nbsp;（1）	名称：填写税务机关核发被投资单位税务登记证上载明的单位全称。<br/>
&nbsp;&nbsp;&nbsp;（2）	纳税人识别号：填写税务机关核发的税务登记证号码。<br/>
&nbsp;&nbsp;&nbsp;（3）	类型：纳税人根据自身情况在对应框内打“√”。<br/>
&nbsp;（三）表内各行的填写：<br/>
&nbsp;&nbsp1.	第1行“收入总额”：填写该投资单位在本期内取得的收入总额。<br/>
&nbsp;&nbsp;2.	第2行“成本”：填写该投资单位在本期内主要经营业务和其他经营业务发生的成本总额。<br/>
&nbsp;&nbsp;3.	第3行“营业费用”：填报该投资单位在销售商品和材料、提供劳务的过程中发生的各种费用。<br/>
&nbsp;&nbsp;4.	第4行“管理费用”：填报该投资单位为组织和管理企业生产经营发生的管理费用。<br/>
&nbsp;&nbsp;5.	第5行“财务费用”：填报该投资单位为筹集生产经营所需资金等发生的筹资费用。<br/>
&nbsp;&nbsp;6.	第6行“营业税金及附加”：填报该投资单位经营活动发生的营业税、消费税、城市维护建设税、资源税、土地增值税和教育费附加等相关税费。<br/>
&nbsp;&nbsp;7.	第8行“利润总额”：根据相关栏次计算。<br/>
&nbsp;&nbsp;第8行＝第1行―第2行―第3行―第4行―第5行―第6行―第7行<br/>
&nbsp;&nbsp;8.	第10行“超过规定标准扣除的项目”，是指被投资单位超过个人所得税法及其实施条例和相关税收法律法规政策规定的扣除标准，扣除的各种成本、费用和损失，应予调增应纳税所得额的部分。<br/>
&nbsp;&nbsp;9.	第24行“不允许扣除的项目”：是指规定不允许扣除，但被投资单位已将其扣除的各项成本、费用和损失，应予调增应纳税所得额的部分。<br/>
&nbsp;&nbsp;10.	第35行“国债利息收入”，是指企业将免于纳税、但已计入收入的因购买国债而取得的利息。<br/>
&nbsp;&nbsp;11.	第37行“以前年度损益调整”：是指以前年度发生的多计或少计的应纳税所得额。<br/>
&nbsp;&nbsp;12.	第38行“经纳税调整后的生产经营所得”：根据相关栏次计算。<br/>
&nbsp;&nbsp;第38行＝第8行＋第9行―第34行―第37行<br/>
&nbsp;&nbsp;13.	第39行“弥补以前年度亏损”：是指企业根据规定，以前年度亏损允许在税前弥补而相应调减的应纳税所得额。<br/>
&nbsp;&nbsp;14.	第40行“分配比例”：纳税人为合伙企业合伙人的，填写本栏。分配比例按照合伙企业分配方案中规定的该合伙人的比例填写；没有，则按人平均分配。<br/>
&nbsp;&nbsp;15.	第41行“允许扣除的其他费用”：是指按照法律法规规定可以税前扣除的其他费用。没有的，则不填。如：《国家税务总局关于律师事务所从业人员有关个人所得税问题的公告》（国家税务总局公告2012年第53号）第三条规定的事项。<br/>
&nbsp;&nbsp;16.	第42行“投资者减除费用”：是指按照税法及有关法律法规规定，在个体工商户业主、个人独资企业投资者和合伙企业合伙人的生产经营所得计征个人所得税时，可在税前扣除的投资者本人的生计减除费用。2011年9月1日起执行42000元/年标准；以后标准按国家政策规定执行。<br/>
&nbsp;&nbsp;17.	第43行“应纳税所得额”：根据不同情况，按相关行次计算填写。<br/>
&nbsp;&nbsp;&nbsp;（1）	纳税人为非合伙企业合伙人的<br/>
&nbsp;&nbsp;&nbsp;第43行＝第38行－第39行－第41行－第42行<br/>
&nbsp;&nbsp;&nbsp;（2）	纳税人为合伙企业合伙人的<br/>
&nbsp;&nbsp;&nbsp;第43行＝（第38行－第39行）×第40行－第41行－第42行<br/>
&nbsp;&nbsp;18.	第44行“税率”及第45行“速算扣除数”：按照税法第三条规定填写。<br/>
&nbsp;&nbsp;19.	第46行“应纳税额”：按相关栏次计算填写。<br/>
&nbsp;&nbsp;第46行＝第43行×第44行－第45行<br/>
&nbsp;&nbsp;20.	第48行“全年应缴税额”：按相关栏次计算填写。<br/>
&nbsp;&nbsp;第48行＝第46行－第47行<br/>
&nbsp;&nbsp;21.	第51行“应补（退）税额”：按相关栏次计算填写。<br/>
&nbsp;&nbsp;第51行＝第48行＋第49行－第50行<br/>

				       </td>
				    </tr>
				</table>
				<br/>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
				 <tr>
				    <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				    <td width="20%" align="center" class="black9"><strong><font color="#0000FF">注意事项</font></strong></td>
				     <td width="40%"> <hr width="100%" size="1" class="hr1" ></td>
				  </tr>
				</table>
				<table width="100%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" class="black9">
				   <tr bordercolor="#9BB4CA" bgcolor="#F6F6F6">
				      <td height="47"  >
				        <p style ="color:red">
				            &nbsp;&nbsp;&nbsp;&nbsp;谨声明：此表是根据《中华人民共和国个人所得税法》及其实施条例和国家相关法律法规规定填写的，是真实的、完整的、可靠的。<br>
				         </p>
				       </td>
				    </tr>
				</table>
<br/><br/>
</html:form>
<%@ include file="../../../include/footernew.jsp"%>
</body>
</html>