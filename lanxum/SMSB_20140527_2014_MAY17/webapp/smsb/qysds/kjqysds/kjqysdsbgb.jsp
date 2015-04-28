<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjqysdsbgb.web.KjqysdsbgbForm"%>
<%@ page import="java.util.HashMap"%>
<%
KjqysdsbgbForm kjqysdsbgbForm=(KjqysdsbgbForm)request.getAttribute("kjqysdsbgbForm");

%>

<html:html>
<head>
<title>中华人民共和国扣缴企业所得税报告表</title>
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
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/gmit_selectcontrol.js"></script>
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
<html:form method="POST" action="/webapp/smsb/qysds/kjqysds/kjqysdsbgbAction.do">
	<html:hidden property="actionType" />
	<html:hidden property="badjxh"/>
	<html:hidden property="bgbxh"/>
	<html:hidden property="skssksrq"/>
	<html:hidden property="skssjsrq"/>
	<html:hidden property="bahtxx.htmc"/>
	<html:hidden property="bahtxx.htbh"/>
	<html:hidden property="jsjdm"/>
	<html:hidden property="kjywrxx.kjrnssbh"/>
	<html:hidden property="kjywrxx.kjrjjlxdm"/>
	<html:hidden property="kjywrxx.kjrjjlxmc"/>
	<html:hidden property="kjywrxx.kjrmc_en"/>
	<html:hidden property="kjywrxx.kjrlxr"/>
	<html:hidden property="kjywrxx.kjrdz_cn"/>
	<html:hidden property="kjywrxx.kjrlxdh"/>
	<html:hidden property="kjywrxx.kjryzbm"/>
	<html:hidden property="kjywrxx.kjrmc_cn"/>
	<html:hidden property="kjywrxx.kjrjjxlfldm"/>
	<html:hidden property="kjywrxx.kjrjjxlflmc"/>
	<html:hidden property="fjmqyxx.fjmmc_cn"/>
	<html:hidden property="fjmqyxx.fjmgb"/>
	<html:hidden property="fjmqyxx.fjmmc_en"/>
	<html:hidden property="fjmqyxx.fjmdz_cn"/>
	<html:hidden property="fjmqyxx.fjmdz_en"/>
	<html:hidden property="fjmqyxx.fjmdz_cn"/>
	<table width="85%" align="center" cellspacing="0" class="table-99" onkeydown="jsjdmQuery()">
		<tr>
			<td class="1-td1">
				中华人民共和国扣缴企业所得税报告表
			</td>
		</tr>

		<tr>
			<td class="1-td2"><br>
			<table cellspacing="0" class="table-99">
				<tr class="black9">
					<td align="center" nowrap>
					<div align="left">&nbsp;计算机代码: <bean:write name="kjqysdsbgbForm"
						property="jsjdm" scope="request" filter="true" /></div>
					</td>
					<td width="90%" align="center" nowrap>税款所属日期： <bean:write name="kjqysdsbgbForm"
						property="skssksrq" scope="request" filter="true" /> 至 <bean:write name="kjqysdsbgbForm"
						property="skssjsrq" scope="request" filter="true" /></td>
					<td align="right" nowrap>
					<div align="right">金额单位：人民币元（列至角分）</div>
					</td>
				</tr>
			</table>
			<table class="table-99" align="center" width="600">
				<TR>			
            <TD> 	
                <table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center" >
				  <tr> 
                   <td colspan="7" nowrap class="2-td1-center"><div align="left">扣缴义务人基本信息:</div></td>
                  </tr>
				  <tr> 
                    <td colspan="2" nowrap class="2-td2-left"><div align="center">纳税人识别号</div></td>
                    <td colspan="3" nowrap class="2-td2-left"><div align="left">&nbsp;<bean:write name="kjqysdsbgbForm"
						property="kjywrxx.kjrnssbh" scope="request" filter="true" /></div></td>
					<td nowrap class="2-td2-left"><div align="center">经济类型代码及名称</div></td>
                    <td  nowrap class="2-td2-center"><div align="left">&nbsp;<bean:write name="kjqysdsbgbForm"
						property="kjywrxx.kjrjjlxdm" scope="request" filter="true" />|<bean:write name="kjqysdsbgbForm"
						property="kjywrxx.kjrjjlxmc" scope="request" filter="true" /></div></td>
                  </tr>
				   <tr> 
                    <td rowspan="2" colspan="2" nowrap class="2-td2-left"><div align="center">名称</div></td>
                    <td colspan="3" nowrap class="2-td2-left"><div align="left">中:&nbsp;&nbsp;<bean:write name="kjqysdsbgbForm"
						property="kjywrxx.kjrmc_cn" scope="request" filter="true" /></div></td>
					<td nowrap class="2-td2-left"><div align="center">经济行业分类代码及名称</div></td>
                    <td nowrap class="2-td2-center"><div align="left">&nbsp;<bean:write name="kjqysdsbgbForm"
						property="kjywrxx.kjrjjxlfldm" scope="request" filter="true" />|<bean:write name="kjqysdsbgbForm"
						property="kjywrxx.kjrjjxlflmc" scope="request" filter="true" /></div></td>
                  </tr>
				  <tr> 
                    <td colspan="3" nowrap class="2-td2-left"><div align="left">英:&nbsp;&nbsp;<bean:write name="kjqysdsbgbForm"
						property="kjywrxx.kjrmc_en" scope="request" filter="true" /></div></td>
					<td nowrap class="2-td2-left"><div align="center">联系人</div></td>
                    <td nowrap class="2-td2-center"><div align="left">&nbsp;<bean:write name="kjqysdsbgbForm"
						property="kjywrxx.kjrlxr" scope="request" filter="true" /></div></td>
                  </tr>
				  <tr> 
                    <td rowspan="2" colspan="2" nowrap class="2-td2-left"><div align="center">地址</div></td>
                    <td colspan="3" nowrap class="2-td2-left"><div align="left">中:&nbsp;&nbsp;<bean:write name="kjqysdsbgbForm"
						property="kjywrxx.kjrdz_cn" scope="request" filter="true" /></div></td>
					<td nowrap class="2-td2-left"><div align="center">联系电话</div></td>
                    <td nowrap class="2-td2-center"><div align="left">&nbsp;<bean:write name="kjqysdsbgbForm"
						property="kjywrxx.kjrlxdh" scope="request" filter="true" /><div></td>
                  </tr>
				  <tr> 
                    <td colspan="3" nowrap class="2-td2-left"><div align="left">英:&nbsp;&nbsp;<html:text tabindex="1" property="kjywrxx.kjrdz_en" size="30" style='text-align:left'/></div></td>
					<td nowrap class="2-td2-left"><div align="center">邮政编码</div></td>
                    <td nowrap class="2-td2-center"><div align="left">&nbsp;<bean:write name="kjqysdsbgbForm"
						property="kjywrxx.kjryzbm" scope="request" filter="true" /></div></td>
                  </tr>
				  <tr> 
                   <td colspan="7" nowrap class="2-td1-center"><div align="left">纳税人基本信息:</div>
				   <div align="right">国家或地区: 港澳台<html:radio property="fjmqyxx.fjmgjdq" value="01" disabled='true'/>&nbsp;外国<html:radio property="fjmqyxx.fjmgjdq" value="02" disabled='true'/></div></td>
                  </tr>
                  <tr> 
                    <td colspan="2" nowrap class="2-td2-left"><div align="center">在其居民国纳税识别号</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">&nbsp;<html:text tabindex="2" property="fjmqyxx.fjmjmgnssbh" size="30" style='text-align:left'/></div></td>
					<td rowspan="2" nowrap class="2-td2-left"><div align="center">在中国境内的名称</div></td>
                    <td colspan="2" nowrap class="2-td2-center"><div align="left">中:&nbsp;<bean:write name="kjqysdsbgbForm"
						property="fjmqyxx.fjmmc_cn" scope="request" filter="true" /></div></td>
                  </tr>
                  <tr> 
                    <td colspan="2" nowrap class="2-td2-left"><div align="center">居民国（地区）名称及代码</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">&nbsp;<bean:write name="kjqysdsbgbForm"
						property="fjmqyxx.fjmgb" scope="request" filter="true" /></div></td>
                    <td colspan="2" nowrap class="2-td2-center"><div align="left">英:&nbsp;&nbsp;<bean:write name="kjqysdsbgbForm"
						property="fjmqyxx.fjmmc_en" scope="request" filter="true" /></div></td>
                  </tr>
                  <tr> 
                    <td colspan="2" rowspan="2" nowrap class="2-td2-left"><div align="center">在其居民国名称</div></td>
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">中:&nbsp;&nbsp;<html:text tabindex="3" property="fjmqyxx.fjmjmgmc_cn" size="30" style='text-align:left'/></div></td>
					<td rowspan="2" nowrap class="2-td2-left"><div align="center">在其居民国地址</div></td>
                    <td colspan="2" nowrap class="2-td2-center"><div align="left">中:&nbsp;&nbsp;<bean:write name="kjqysdsbgbForm"
						property="fjmqyxx.fjmdz_cn" scope="request" filter="true" /></div></td>
                  </tr>
                  <tr>   
                    <td colspan="2" nowrap class="2-td2-left"><div align="left">英:&nbsp;&nbsp;<html:text tabindex="4" property="fjmqyxx.fjmjmgmc_en" size="30" style='text-align:left'/></div></td>
                    <td colspan="2" nowrap class="2-td2-center"><div align="left">英:&nbsp;&nbsp;<bean:write name="kjqysdsbgbForm"
						property="fjmqyxx.fjmdz_en" scope="request" filter="true" /></div></td>
                  </tr>
                  <tr>
                  	<td colspan="7" class="2-td2-center">&nbsp;
                  	</td>
                  </tr>
                  <tr> 
                    <td colspan="2" nowrap class="2-td2-left"><div align="center">申报所得类型及代码</div></td>
                    <td colspan="3" nowrap class="2-td2-left"><div align="left">&nbsp;<html:select tabindex="5" name="kjqysdsbgbForm" styleId="sbsdlxSelect" property="sbsdlxdm" /></div></td>
					<td nowrap class="2-td2-left"><div align="center">本次申报所得取得日期</div></td>
                    <td nowrap class="2-td2-center"><div align="left">&nbsp;<html:text tabindex="6" property="sbsdqdrq" size="20" onchange="isDate(this,false);" style='text-align:left'/></div></td>
                  </tr>
                  <tr> 
                    <td colspan="2" nowrap class="2-td2-left"><div align="center">合同名称</div></td>
                    <td colspan="3" nowrap class="2-td2-left"><div align="left">&nbsp;<bean:write name="kjqysdsbgbForm"
						property="bahtxx.htmc" scope="request" filter="true" /></div></td>
					<td nowrap class="2-td2-left"><div align="center">合同编号</div></td>
                    <td nowrap class="2-td2-center"><div align="left">&nbsp;<bean:write name="kjqysdsbgbForm"
						property="bahtxx.htbh" scope="request" filter="true" /></div></td>
                  </tr>
                  <tr> 
                    <td colspan="2" nowrap class="2-td2-left"><div align="center">合同执行起始时间</div></td>
                    <td nowrap class="2-td2-left"><div align="left">&nbsp;<html:text tabindex="7" property="bahtxx.htzxqsrq" size="20" styleId="htzxqsrq" onchange="isDate(this,false);compareDate(this)" style='text-align:left'/></div></td>
                    <td width="70" nowrap class="2-td2-left"><div align="center">合同执行终止时间</td>
					<td nowrap class="2-td2-left"><div align="left">&nbsp;<html:text tabindex="8" property="bahtxx.htzxzzrq" size="20" styleId="htzxzzrq" onchange="isDate(this,false);compareDate(this)" style='text-align:left'/></div></td>
					<td nowrap class="2-td2-left"><div align="center">合同总金额</div></td>
                    <td nowrap class="2-td2-center"><div align="left">&nbsp;<bean:write name="kjqysdsbgbForm"
						property="bahtxx.htje" scope="request" filter="true" /></div></td>
                  </tr>
                  <tr> 
                   <td colspan="7" nowrap class="2-td1-center"><div align="left">以下内容适用于法定源泉扣缴情况填写：</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left"><div align="center">行次</div></td>
                    <td colspan="4" nowrap class="2-td2-left"><div align="center">项目</div></td>
                    
                    <td colspan="2"  nowrap class="2-td2-center">依法申报数据</td>
                  </tr>
                  <tr> 
                  <td class="2-td2-left" nowrap><div align="center">1<input type="hidden" name="hc"
								value="1" id="hc_1"></div></td>
                    <td width="144" rowspan="6" nowrap class="2-td2-left">本次申报收入</td>
                    <td colspan="3" nowrap class="2-td2-left">人民币金额</td>                    
                    <td colspan="2" class="2-td2-center" nowrap><input tabindex='9' type='text' name='sj'
								id='1_1' style='text-align:right' value='' size='13'
								 onblur="checkNumInput(this)"  onchange="compute_Row_6(this)"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap><div align="center">2<input type="hidden" name="hc"
								value="2" id="hc_2"></div></td>
                    <td width="40" rowspan="4" class="2-td2-left" nowrap>外币</td>
                    <td colspan="2" class="2-td2-left" nowrap>名称</td>
                    <td colspan="2" class="2-td2-center" nowrap>
                    	<select id="2_1" name="sj"  tabindex="10" ></td>
                  </tr>
                  <tr>
                  	<td class="2-td2-left" nowrap><div align="center">3<input type="hidden" name="hc" value="3" id="hc_3"></div></td> 
                    <td colspan="2" class="2-td2-left" nowrap>金额</td>
                    
                    <td colspan="2" class="2-td2-center" nowrap>
                    	<input tabindex='11' type='text' name='sj' 
                    	id='3_1' style='text-align:right'   value='' size='13' onblur="checkNumInput(this)" onchange="compute_Row_5(this)"></td>
                  </tr>
                  <tr>
                  	<td class="2-td2-left" nowrap><div align="center">4<input type="hidden" name="hc" value="4" id="hc_4"></div></td> 
                    <td colspan="2" class="2-td2-left" nowrap>汇率</td>
                    
                    <td colspan="2" class="2-td2-center" nowrap>
                    	<input tabindex='12' type='text' name='sj' 
                    	id='4_1' style='text-align:right'  value='' size='13' onblur="checkNumInput6(this)" onchange="compute_Row_5(this)"></td>
                  </tr>
                  <tr>
                  	<td class="2-td2-left" nowrap><div align="center">5<input type="hidden" name="hc" value="5" id="hc_5"></div></td> 
                    <td colspan="2" class="2-td2-left" nowrap>折算人民币金额　5=3×4</td>
                    
                    <td colspan="2" class="2-td2-center" nowrap>
                    	<input tabindex="-1" type='text' name='sj' class="text-gray" 
                    	id='5_1' style='text-align:right'  value='' size='13' readonly='true' onchange="compute_Row_6(this)"></td>
                  </tr>
                  <tr>
                  	<td class="2-td2-left" nowrap><div align="center">6<input type="hidden" name="hc" value="6" id="hc_6"></div></td> 
                    <td colspan="3" class="2-td2-left" nowrap>人民币金额合计　6=1+5</td>
                    
                    <td colspan="2" class="2-td2-center" nowrap>
                    	<input tabindex="-1" type='text' name='sj' class="text-gray" 
                    	id='6_1' style='text-align:right' value='' size='13' readonly='true' onchange="compute_Row_8(this)"></td>
                  </tr>
                  <tr> 
                  <td class="2-td2-left" nowrap><div align="center">7<input type="hidden" name="hc"
								value="7" id="hc_7"></div></td>
                    <td width="144" rowspan="2" nowrap class="2-td2-left">应纳税所得额的计算</td>
                    <td colspan="3" nowrap class="2-td2-left">扣除额</td>                    
                    <td colspan="2" class="2-td2-center" nowrap><input tabindex='13' type='text' name='sj'
								id='7_1' style='text-align:right' value='' size='13'
								 onblur="checkNumInput(this)" onchange="compute_Row_8(this)" ></td>
                  </tr>
                   <tr> 
                    <td class="2-td2-left" nowrap><div align="center">8<input type="hidden" name="hc"
								value="8" id="hc_8"></div></td>
                    
                    <td colspan="3" nowrap class="2-td2-left">应纳税所得额　8=6-7</td>                    
                    <td colspan="2" class="2-td2-center" nowrap><input tabindex="-1" type='text' name='sj'
								id='8_1' style='text-align:right' value='' size='13' readonly='true'
								 onblur="checkNumInput(this)" onchange="compute_Row_10(this)" class="text-gray"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap><div align="center">9<input type="hidden" name="hc"
								value="9" id="hc_9"></div></td>
                    <td width="144" rowspan="5" nowrap class="2-td2-left">应纳企业所得税额的计算</td>
                    <td colspan="3" nowrap class="2-td2-left">适用税率（10%）</td>                    
                    <td colspan="2" class="2-td2-center" nowrap><input tabindex="-1" type='text' name='sj'
								id='9_1' style='text-align:right' value='10' size='13' readonly='true'
								class="text-gray">%</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap><div align="center">10<input type="hidden" name="hc"
								value="10" id="hc_10"></div></td>
                    <td colspan="3" nowrap class="2-td2-left">应缴纳的企业所得税额　10=8×9</td>                    
                    <td colspan="2" class="2-td2-center" nowrap><input tabindex="-1" type='text' name='sj'
								id='10_1' style='text-align:right' value='' size='13' readonly='true'
								 onblur="checkNumInput(this)" onchange="compute_Row_13(this)" class="text-gray"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap><div align="center">11<input type="hidden" name="hc"
								value="11" id="hc_11"></div></td>
                    <td colspan="3" nowrap class="2-td2-left">实际征收率（%）</td>                    
                    <td colspan="2" class="2-td2-center" nowrap><input tabindex='14' type='text' name='sj'
								id='11_1' style='text-align:right' value='' size='13'
								 onblur="checkNumInput(this)" onchange="compute_Row_11(this)">%</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap><div align="center">12<input type="hidden" name="hc"
								value="12" id="hc_12"></div></td>
                    <td colspan="3" nowrap class="2-td2-left">实际应缴纳的企业所得税额　12=8×11</td>                    
                    <td colspan="2" class="2-td2-center" nowrap><input tabindex="-1" type='text' name='sj'
								id='12_1' style='text-align:right' value='' size='13' readonly='true'
								 onblur="checkNumInput(this)" onchange="compute_Row_13(this)" class="text-gray"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap><div align="center">13<input type="hidden" name="hc"
								value="13" id="hc_13"></div></td>
                    <td colspan="3" nowrap class="2-td2-left">减免企业所得税额　13=10-12</td>                    
                    <td colspan="2" class="2-td2-center" nowrap><input tabindex="-1" type='text' name='sj'
								id='13_1' style='text-align:right' value='' size='13' readonly='true'
								 onblur="checkNumInput(this)" class="text-gray"></td>
                  </tr>
                  <tr> 
                   <td colspan="7" nowrap class="2-td1-center"><div align="left">以下内容适用于主管税务机关指定扣缴情况填写：</div></td>
                  </tr>
                  <tr> 
                    <td width="52" nowrap class="2-td2-left"><div align="center">行次</div></td>
                    <td colspan="4" nowrap class="2-td2-left"><div align="center">项目</div></td>
                    
                    <td colspan="2"  nowrap class="2-td2-center">依法申报数据</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap><div align="center">14<input type="hidden" name="hc"
								value="14" id="hc_14"></div></td>
                    <td colspan="4" nowrap class="2-td2-left">本次申报的收入总额</td>                    
                    <td colspan="2" class="2-td2-center" nowrap><input tabindex='15' type='text' name='sj'
								id='14_1' style='text-align:right' value='' size='13'
								 onblur="checkNumInput(this)" onchange="compute_Row_16(this)"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap><div align="center">15<input type="hidden" name="hc"
								value="15" id="hc_15"></div></td>
                    <td colspan="4" nowrap class="2-td2-left">税务机关核定的利润率（%）</td>                    
                    <td colspan="2" class="2-td2-center" nowrap><input tabindex='16' type='text' name='sj'
								id='15_1' style='text-align:right' value='' size='13'
								 onblur="checkNumInput(this)" onchange="compute_Row_15(this)">%</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap><div align="center">16<input type="hidden" name="hc"
								value="16" id="hc_16"></div></td>
                    <td colspan="4" nowrap class="2-td2-left">应纳税所得额　16=14×15</td>                    
                    <td colspan="2" class="2-td2-center" nowrap><input tabindex="-1" type='text' name='sj'
								id='16_1' style='text-align:right' value='' size='13' readonly='true'
								 onblur="checkNumInput(this)"  class="text-gray"></td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap><div align="center">17<input type="hidden" name="hc"
								value="17" id="hc_17"></div></td>
                    <td colspan="4" nowrap class="2-td2-left">适用税率（%）</td>                    
                    <td colspan="2" class="2-td2-center" nowrap><input tabindex='17' type='text' name='sj'
								id='17_1' style='text-align:right' value='' size='13'
								 onblur="checkNumInput(this)" onchange="compute_Row_17(this)">%</td>
                  </tr>
                  <tr> 
                    <td class="2-td2-left" nowrap><div align="center">18<input type="hidden" name="hc"
								value="18" id="hc_18"></div></td>
                    <td colspan="4" nowrap class="2-td2-left">应纳企业所得税额　18=16×17</td>                    
                    <td colspan="2" class="2-td2-center" nowrap><input tabindex="-1" type='text' name='sj'
								id='18_1' style='text-align:right' value='' size='13' readonly='true'
								 onblur="checkNumInput(this)" class="text-gray"></td>
                  </tr>
                </table>
					</TD>
				</TR>
				<TR class="black9">
					<TD>
					<div align="center"><input type="image" accesskey="u"
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
					&nbsp;&nbsp;&nbsp;&nbsp;<input type="image" accesskey="f"
						onClick="back();return false;"
						onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" value="返回" id="fanhui"
						src="<%=static_contextpath%>images/fanhui1.jpg" width="79"
						height="22" style="cursor:hand">
					&nbsp;&nbsp;&nbsp;&nbsp;<input type="image" accesskey="c"
						onClick="tuichu();return false;"
						onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
						onMouseOut="MM_swapImgRestore()" value="退出" id="tc1"
						src="<%=static_contextpath%>images/tc-c1.jpg" width="79"
						height="22" style="cursor:hand">
					</div>
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
	
    // 构造申报所得类型及代码列表
    if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_SBSDLX) != null)
    {
        out.print("var arySelect_sbsdlx = new Array(");
        String[][] sbsdlx = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_SBSDLX);
        for(int i = 0 ; i < sbsdlx.length ; i++)
        {
            if(i != 0)
            {
                out.print(",[\"" + sbsdlx[i][0] + "\",\"" +sbsdlx[i][1] +"\"]");
            }
            else
            {
                out.print("[\"" + sbsdlx[i][0] + "\",\"" +sbsdlx[i][1] +"\"]");
            }
        }
        out.println(");");
    }
	// 构造币种列表
    if(session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ) != null)
    {
        out.print("var arySelect_bz = new Array(");
        String[][] bz = (String[][])session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ);
        for(int i = 1 ; i < bz.length ; i++)
        {
            if(i != 1)
            {
                out.print(",[\"" + bz[i][0] + "\",\"" +bz[i][1] +"\"]");
            }
            else
            {
                out.print("[\"" + bz[i][0] + "\",\"" +bz[i][1] +"\"]");
            }
        }
        out.println(");");
    }

	%>
	function doInit()	
	{
		// 初始化国家下拉菜单
		_addOptionsToSelect(document.forms[0].sbsdlxSelect, arySelect_sbsdlx);
		// 初始化币种下拉菜单
		_addOptionsToSelect(document.getElementById('2_'+1), arySelect_bz);
	<%
	if (kjqysdsbgbForm!=null && kjqysdsbgbForm.getQysdsbgbList()!=null && kjqysdsbgbForm.getQysdsbgbList().size()>0){
		for(int i=0;i<kjqysdsbgbForm.getQysdsbgbList().size();i++){
			HashMap map=(HashMap)kjqysdsbgbForm.getQysdsbgbList().get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			String sj=(String)map.get("sj");
			%>
			
			obj = eval("document.getElementById('<%=hc%>_1')");

			obj.value = '<%=sj%>';
			if('<%=hc%>'=='2'){
				for(var i=0;i<document.getElementById('<%=hc%>_1').length;i++){               
			        if (document.getElementById('<%=hc%>_1').options[i].value=='<%=sj%>'){
			        	document.getElementById('<%=hc%>_1').selectedIndex=i;
			            break;
			        }
			    }
			}
			<% 
		}
	}
	%>

    
	for(var i=0;i<document.forms[0].sbsdlxdm.length;i++){               
        if (document.forms[0].sbsdlxdm.options[i].value=='<%=kjqysdsbgbForm.getSbsdlxdm()%>'){
        	document.forms[0].sbsdlxdm.selectedIndex=i;
            break;
        }
    }
}
	
	
	
	<%/*保存*/%>
	function doSave()
	{
		if(document.forms[0].sbsdqdrq.value==""){
			alert("请填写本次申报所得取得日期!");
			document.forms[0].sbsdqdrq.focus();
			return false;
		}
		if(document.getElementById('htzxqsrq').value==""){
			alert("请填写合同执行起始时间!");
			document.getElementById('htzxqsrq').focus();
			return false;
		}
		if(document.getElementById('htzxzzrq').value==""){
			alert("请填写合同执行终止时间!");
			document.getElementById('htzxzzrq').focus();
			return false;
		}
		if(getValues('3_1')!=""&&getValues('4_1')!=""){
			document.getElementById('5_'+1).value=parseFloat((getValues('3_1')*getValues('4_1')).toFixed(2));
		}
		if(getValues('1_1')!=""&&getValues('5_1')!=""){
			document.getElementById('6_'+1).value=parseFloat((getValues('1_1')+getValues('5_1')).toFixed(2));
		}
		if(getValues('6_1')!=""&&getValues('7_1')!=""){
			document.getElementById('8_'+1).value=parseFloat((getValues('6_1')-getValues('7_1')).toFixed(2));
		}
		if(getValues('8_1')!=""&&getValues('9_1')!=""){
			document.getElementById('10_'+1).value=parseFloat((getValues('8_1')*(getValues('9_1')/100)).toFixed(2));
		}
		if(getValues('8_1')!=""&&getValues('11_1')!=""){
			document.getElementById('12_'+1).value=parseFloat((getValues('8_1')*(getValues('11_1')/100)).toFixed(2));
		}
		if(getValues('10_1')!=""&&getValues('12_1')!=""){
			document.getElementById('13_'+1).value=parseFloat((getValues('10_1')-getValues('12_1')).toFixed(2));
		}
		if(getValues('14_1')!=""&&getValues('15_1')!=""){
			document.getElementById('16_'+1).value=parseFloat((getValues('14_1')*(getValues('15_1')/100)).toFixed(2));
		}
		if(getValues('16_1')!=""&&getValues('17_1')!=""){
			document.getElementById('18_'+1).value=parseFloat((getValues('16_1')*(getValues('17_1')/100)).toFixed(2));
		}
		
		doSubmitForm('Save');
	
	}
	<%/*表内关系校验*/%>
		function doCheckmethod()
		{
			
			return true;
		}
		
		
		function compute_Row_5(obj){
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
	        if(cz>7){
	        	return false;
	        }
	      }
			var ybsds=getValues('3_1')*getValues('4_1');
			document.getElementById('5_'+1).value=parseFloat(ybsds.toFixed(2));
			formate(document.getElementById('5_1'));
			compute_Row_6(document.getElementById('5_'+1));
		}
		function compute_Row_6(obj){
			if(!numberyz(obj))
			  return;
			var ybsds=getValues('1_1')+getValues('5_1');
			document.getElementById('6_'+1).value=parseFloat(ybsds.toFixed(2));
			formate(document.getElementById('6_1'));
			compute_Row_8(obj);
		}

		
		function compute_Row_8(obj){
			if(!numberyz(obj))
			  return;
			var ybsds=getValues('6_1')-getValues('7_1');
			document.getElementById('8_'+1).value=parseFloat(ybsds.toFixed(2));
			formate(document.getElementById('8_1'));
			compute_Row_10(obj);
			compute_Row_12(obj);
		}

		function compute_Row_10(obj){
			if(!numberyz(obj))
			  return;
			var ybsds=getValues('8_1')*(getValues('9_1')/100);
			document.getElementById('10_'+1).value=parseFloat(ybsds.toFixed(2));
			formate(document.getElementById('10_1'));
			compute_Row_13(obj);
		}

		function compute_Row_11(obj){
			if(obj.value<0||obj.value>10){
				alert("请输入0-10之间的数据!");
				window.event.returnValue=false;
				document.getElementById('11_1').select();
				document.getElementById('11_1').focus();
				return false;
			}
			compute_Row_12(obj);
		}

		function compute_Row_12(obj){
			if(!numberyz(obj))
			  return;
			var ybsds=getValues('8_1')*(getValues('11_1')/100);
			document.getElementById('12_'+1).value=parseFloat(ybsds.toFixed(2));
			formate(document.getElementById('12_1'));
			compute_Row_13(obj);
		}

		function compute_Row_13(obj){
			if(!numberyz(obj))
			  return;
			var ybsds=getValues('10_1')-getValues('12_1');
			document.getElementById('13_'+1).value=parseFloat(ybsds.toFixed(2));
			formate(document.getElementById('13_1'));
		}

		function compute_Row_15(obj){
			if(!numberyz(obj))
			  return;
			if(obj.value<0||obj.value>100){
				alert("请输入0-100之间的数据!");
				window.event.returnValue=false;
				document.getElementById('15_1').select();
				document.getElementById('15_1').focus();
				return false;
			}
			compute_Row_16(obj);
		}
		
		function compute_Row_16(obj){
			if(!numberyz(obj))
			  return;
			var ybsds=getValues('14_1')*(getValues('15_1')/100);
			document.getElementById('16_'+1).value=parseFloat(ybsds.toFixed(2));
			formate(document.getElementById('16_1'));
			compute_Row_18(obj);
		}

		function compute_Row_17(obj){
			if(!numberyz(obj))
			  return;
			if(obj.value<0||obj.value>100){
				alert("请输入0-100之间的数据!");
				window.event.returnValue=false;
				document.getElementById('17_1').select();
				document.getElementById('17_1').focus();
				return false;
			}
			compute_Row_18(obj)
		}

		function compute_Row_18(obj){
			if(!numberyz(obj))
			  return;
			var ybsds=getValues('16_1')*(getValues('17_1')/100);
			document.getElementById('18_'+1).value=parseFloat(ybsds.toFixed(2));
			formate(document.getElementById('18_1'));
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

   			for(var i=1;i<=18;i++){
				  obj = eval("document.getElementById('" + i+ "_1')");
				  	if(i==2)
					    document.getElementById('2_1').selectedIndex=0;
				    if (i!=2&&i!=9)
					    obj.value = "0.00";
   			}
		}
	}

	function checkNumInput6(obj)

	{

                   //判断输入的数据是否符合要求

                   if(!isNum(getCellObject() , null, null, null, null, 6)){

                            return false;                         

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

<%/*响应计算机代码的回车查询*/%>
function jsjdmQuery(){
	if(event.keyCode==13) event.keyCode = 9;
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

	//判断比较税款所属日期
	function compareDate(obj){
		
		var strDate1 = document.getElementById('htzxqsrq').value;		
		var strYear1 = strDate1.substr(0,4);		
		var strMonth1 = strDate1.substr(4,2);
	 	var strDay1 = strDate1.substr(6,2);
	 	
	 	var strDate2 = document.getElementById('htzxzzrq').value;
	 	var strYear2 = strDate2.substr(0,4);		
		var strMonth2 = strDate2.substr(4,2);
	 	var strDay2 = strDate2.substr(6,2);
	 	
	var objDate1 = new Date(strMonth1+'-'+strDay1+'-'+strYear1);
	var objDate2 = new Date(strMonth2+'-'+strDay2+'-'+strYear2);
	
	  	
		if(objDate1>=objDate2){
			if(obj == document.getElementById('htzxzzrq')){
				alert("合同结束时间不能小于或等于合同开始时间");
			}else{
				alert("合同开始时间不能大于或等于合同结束时间");	
			}
			window.event.returnValue=false;
			obj.focus();
			obj.select();
			return false;
		}
				
	}


	//返回
	function back(){
		returnStr="kjqysdsbgbAction.do?actionType=Show&jsjdm="+document.forms[0].jsjdm.value;
		window.location=returnStr;
	}
</script>
</body>
</html:html>