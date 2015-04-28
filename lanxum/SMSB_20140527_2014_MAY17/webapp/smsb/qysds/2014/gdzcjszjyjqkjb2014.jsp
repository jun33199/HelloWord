<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page
	import="com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web.GdzcjszjyjqkjbForm"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ttsoft.bjtax.smsb.constant.CodeConstant"%>
<%@ page import="com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.CheckJdlx"%>
<%@ page import="com.syax.creports.Constants"%>
<html:html>
<head>
<title>固定资产加速折旧（扣除）预缴情况统计表</title>
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
    font-size: 10pt;
    text-align: right;
}
.inputalignright{
    font-size: 10pt;
    text-align: right;
}
.inbright {
    font-size: 10pt;
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

<html:form method="POST" action="/webapp/smsb/qysds/gdzcjszjyjqkjb2014Action.do">
	<html:hidden property="actionType" />
	<html:hidden property="jsjdm" />
	<html:hidden property="qh" />
	<html:hidden property="sknd" />
	<html:hidden property="jumpFlag" />
	<table width="96%" align="center" cellspacing="0" class="table-99">
		<tr>
			<td class="1-td1" colspan="7">
				固定资产加速折旧（扣除）预缴情况统计表
			</td>
		</tr>

		<tr>
			<td class="1-td2" colspan="7"><br>
			<table cellspacing="0" class="table-99">

				<tr class="black9">
					<td align="center"  style="display:none">
						<div align="left">&nbsp;&nbsp;申报日期： <html:text tabindex="1" property="sbrq" size="11"
						maxlength="8" readonly='true' style='text-align:left' size="32" readonly='true'  styleClass="text-gray" 
						onchange="checkZQ(this,document.forms[0].skssksrq,document.forms[0].skssjsrq,3)" /></div>
					</td>
					<td wigth="90%" align="center"  colspan="3" align="center" >税款所属期间： <html:text tabindex="2" property="skssksrq"
						size="15" maxlength="8"
						onchange="isDate(this,false);compareDate(this)"  style="text-align:left" size="32" readonly='true'  styleClass="text-gray" /> 至 <html:text tabindex="3"
						property="skssjsrq" size="15" maxlength="8"
						onchange="isDate(this,false);compareDate(this)" style="text-align:left" size="32" readonly='true'  styleClass="text-gray" /></td>

				</tr>
				<tr>
							<td   >
								<div style="font-size: 9pt; line-height: 26px; color: #3E6071; background-color: #F3F5F8; text-align: left;">&nbsp;&nbsp;纳税人识别号：
									 <html:text property="nsrsbh" size="32" size="32" readonly='true' style='text-align:left' styleClass="text-gray"  />
								</div>
							</td>

							<td   >
								<div style="font-size: 9pt; line-height: 26px; color: #3E6071; background-color: #F3F5F8; text-align: left;">纳税人名称：
									<html:text property="nsrmc" size="32" readonly='true' style='text-align:left' styleClass="text-gray" />
								</div>
							</td>
							
							<td  >
								<div style="font-size: 9pt; line-height: 26px; color: #3E6071; background-color: #F3F5F8; text-align: left;">所属行业：
									<html:text property="sshy" size="6"  onchange="setSshy(this)"/>
					                
					                <bean:define id="dta" name="gdzcjszjyjqkjb2014Form" property="gjbzhy"/>
			                  		<html:select property="gjbzhydm" style='width:190px' onchange="setSshy(this)">
			                  			<option>请选择</option>
			                    		<html:options collection="dta" labelProperty="gjbzhymc" property="gjbzhydm"/>
			                  		</html:select>
					              </div>
							</td>
						</tr>
			</table>
	
				
			<TABLE class="table-99" align="center">
			<TR>					
            <TD> 
              <div id="Layer2" style="position:static;">
					
                <table id="wrklistTable" border="1" cellspacing="0"
						class="table-99" align="center">
                  <tr> 
                    <td class="2-td1-left" rowspan="4" width="30"><div align="center">行次</div></td>
                    <td class="2-td1-left" width="300"  align="center" rowspan="4"><div align="center">项&nbsp;&nbsp;&nbsp;&nbsp;目</div></td>
                    <td align="center" class="2-td1-left" colspan="3">房屋、建筑物</td>
                    <td align="center" class="2-td1-left" colspan="3">机器设备和其他固定资产</td>
                    <td align="center" class="2-td1-center" colspan="5">合计</td>
                  </tr>
                  <tr> 
                    <td width="80"  align="center" class="2-td1-left" rowspan="2">原值</td>
                    <td width="80"  align="center" class="2-td1-left" rowspan="2">本期折旧（扣除）额</td>
                    <td width="80"  align="center" class="2-td1-left" rowspan="2">累计折旧（扣除）额</td>
                    <td width="80"  align="center" class="2-td1-left" rowspan="2">原值</td>
                    <td width="80"  align="center" class="2-td1-left" rowspan="2">本期折旧（扣除）额</td>
                    <td width="80"  align="center" class="2-td1-left" rowspan="2">累计折旧（扣除）额</td>
                    <td width="80"  align="center" class="2-td1-left" rowspan="2">原值</td>
                    <td align="center" class="2-td1-left" colspan="2">本期折旧（扣除）额</td>
                    <td align="center" class="2-td1-center" colspan="2">累计折旧（扣除）额</td>
                  </tr>
                  <tr> 
                    <td width="80"  class="2-td1-left" >正常折旧额</td>
                    <td width="80"  class="2-td1-left" >加速折旧额</td>
                    <td width="80"  class="2-td1-left" >正常折旧额</td>
                    <td width="80"  class="2-td1-center" >加速折旧额</td>
                  </tr>
				  <tr>
				    <td   class="2-td2-left">
						<div align="center">1	
						</div>
					</td>
				    <td   class="2-td2-left">
						<div align="center">2	
						</div>
					</td>	
				    <td   class="2-td2-left">
						<div align="center">3	
						</div>
					</td>
				    <td   class="2-td2-left">
						<div align="center">4	
						</div>
					</td>
				    <td   class="2-td2-left">
						<div align="center">5	
						</div>
					</td>
				    <td   class="2-td2-left">
						<div align="center">6	
						</div>
					</td>	
				    <td  class="2-td2-left">
						<div align="center">7	
						</div>
					</td>
				    <td   class="2-td2-left">
						<div align="center">8	
						</div>
					</td>
				    <td  class="2-td2-left">
						<div align="center">9	
						</div>
					</td>
				    <td  class="2-td2-left">
						<div align="center">10	
						</div>
					</td>	
				    <td  class="2-td2-center">
						<div align="center">11	
						</div>
					</td>																			
				  </tr>
                  <tr> 
                    <td   class="2-td2-left">
						<div align="center">1
							<input type="hidden" name="hc" value="1" id="hc_1">
						</div></td>
                    <td class="2-td2-left" ><div align="left">一、六大行业固定资产</div></td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="1" type='text' name='fwjzw_yz' 	id='1_1' style='text-align:right' value='' size='13' onblur="formate(this)"  onchange="changeNum(this,1)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="2" type='text' name='fwjzw_bqzje' 	id='1_2' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,2)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="3" type='text' name='fwjzw_ljzje' 	id='1_3' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,3)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="4" type='text' name='jqsbhqtgdzc_yz' 	id='1_4' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,4)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="5" type='text' name='jqsbhqtgdzc_bqzje' 	id='1_5' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,5)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="6" type='text' name='jqsbhqtgdzc_ljzje' 	id='1_6' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,6)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="7" type='text' name='hj_yz' 	id='1_7' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,7)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="8" type='text' name='hj_bqzje_zczje' 	id='1_8' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,8)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="9" type='text' name='hj_bqzje_jszje' 	id='1_9' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,9)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="10" type='text' name='hj_ljzje_zczje' 	id='1_10' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,10)">
					</td>																				
                    <td width="80"  class="2-td2-center">
						<input tabindex="11" type='text' name='hj_ljzje_jszje' 	id='1_11' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,11)">
					</td>
                  </tr>

                  <tr> 
                    <td   class="2-td2-left">
						<div align="center">2
							<input type="hidden" name="hc" value="2" id="hc_2">
						</div></td>
                    <td class="2-td2-left"><div align="left">&nbsp;&nbsp;（一）生物药品制造业</div></td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="12" type='text' name='fwjzw_yz' 	id='2_1' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,1)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="13" type='text' name='fwjzw_bqzje' 	id='2_2' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,2)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="14" type='text' name='fwjzw_ljzje' 	id='2_3' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,3)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="15" type='text' name='jqsbhqtgdzc_yz' 	id='2_4' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,4)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="16" type='text' name='jqsbhqtgdzc_bqzje' 	id='2_5' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,5)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="17" type='text' name='jqsbhqtgdzc_ljzje' 	id='2_6' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,6)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="18" type='text' name='hj_yz' 	id='2_7' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,7)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="19" type='text' name='hj_bqzje_zczje' 	id='2_8' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,8)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="20" type='text' name='hj_bqzje_jszje' 	id='2_9' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,9)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="21" type='text' name='hj_ljzje_zczje' 	id='2_10' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,10)">
					</td>																				
                    <td width="80"  class="2-td2-center">
						<input tabindex="22" type='text' name='hj_ljzje_jszje' 	id='2_11' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,11)">
					</td>
                  </tr>                  
                  
                  <tr> 
                    <td   class="2-td2-left">
						<div align="center">3
							<input type="hidden" name="hc" value="3" id="hc_3">
						</div></td>
                    <td class="2-td2-left"><div align="left">&nbsp;&nbsp;（二）专用设备制造业</div></td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="23" type='text' name='fwjzw_yz' 	id='3_1' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,1)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="24" type='text' name='fwjzw_bqzje' 	id='3_2' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,2)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="25" type='text' name='fwjzw_ljzje' 	id='3_3' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,3)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="26" type='text' name='jqsbhqtgdzc_yz' 	id='3_4' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,4)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="27" type='text' name='jqsbhqtgdzc_bqzje' 	id='3_5' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,5)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="28" type='text' name='jqsbhqtgdzc_ljzje' 	id='3_6' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,6)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="29" type='text' name='hj_yz' 	id='3_7' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,7)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="30" type='text' name='hj_bqzje_zczje' 	id='3_8' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,8)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="31" type='text' name='hj_bqzje_jszje' 	id='3_9' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,9)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="32" type='text' name='hj_ljzje_zczje' 	id='3_10' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,10)">
					</td>																				
                    <td width="80"  class="2-td2-center">
						<input tabindex="33" type='text' name='hj_ljzje_jszje' 	id='3_11' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,11)">
					</td>
                  </tr>                     
                  <tr> 
                    <td   class="2-td2-left">
						<div align="center">4
							<input type="hidden" name="hc" value="4" id="hc_4">
						</div></td>
                    <td class="2-td2-left"><div align="left">&nbsp;&nbsp;（三）铁路、船舶、航空航天和其他运输设备制造业</div></td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="34" type='text' name='fwjzw_yz' 	id='4_1' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,1)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="35" type='text' name='fwjzw_bqzje' 	id='4_2' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,2)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="36" type='text' name='fwjzw_ljzje' 	id='4_3' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,3)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="37" type='text' name='jqsbhqtgdzc_yz' 	id='4_4' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,4)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="38" type='text' name='jqsbhqtgdzc_bqzje' 	id='4_5' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,5)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="39" type='text' name='jqsbhqtgdzc_ljzje' 	id='4_6' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,6)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="40" type='text' name='hj_yz' 	id='4_7' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,7)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="41" type='text' name='hj_bqzje_zczje' 	id='4_8' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,8)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="42" type='text' name='hj_bqzje_jszje' 	id='4_9' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,9)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="43" type='text' name='hj_ljzje_zczje' 	id='4_10' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,10)">
					</td>																				
                    <td width="80"  class="2-td2-center">
						<input tabindex="44" type='text' name='hj_ljzje_jszje' 	id='4_11' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,11)">
					</td>
                  </tr>                  
                  
                  <tr> 
                    <td   class="2-td2-left">
						<div align="center">5
							<input type="hidden" name="hc" value="5" id="hc_5">
						</div></td>
                    <td class="2-td2-left"><div align="left">&nbsp;&nbsp;（四）计算机、通信和其他电子设备制造业</div></td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="45" type='text' name='fwjzw_yz' 	id='5_1' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,1)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="46" type='text' name='fwjzw_bqzje' 	id='5_2' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,2)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="47" type='text' name='fwjzw_ljzje' 	id='5_3' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,3)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="48" type='text' name='jqsbhqtgdzc_yz' 	id='5_4' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,4)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="49" type='text' name='jqsbhqtgdzc_bqzje' 	id='5_5' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,5)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="50" type='text' name='jqsbhqtgdzc_ljzje' 	id='5_6' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,6)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="51" type='text' name='hj_yz' 	id='5_7' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,7)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="52" type='text' name='hj_bqzje_zczje' 	id='5_8' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,8)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="53" type='text' name='hj_bqzje_jszje' 	id='5_9' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,9)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="54" type='text' name='hj_ljzje_zczje' 	id='5_10' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,10)">
					</td>																				
                    <td width="80"  class="2-td2-center">
						<input tabindex="55" type='text' name='hj_ljzje_jszje' 	id='5_11' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,11)">
					</td>
                  </tr>     
                  
                  <tr> 
                    <td   class="2-td2-left">
						<div align="center">6
							<input type="hidden" name="hc" value="6" id="hc_6">
						</div></td>
                    <td class="2-td2-left"><div align="left">&nbsp;&nbsp;（五）仪器仪表制造业</div></td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="56" type='text' name='fwjzw_yz' 	id='6_1' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,1)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="57" type='text' name='fwjzw_bqzje' 	id='6_2' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,2)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="58" type='text' name='fwjzw_ljzje' 	id='6_3' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,3)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="59" type='text' name='jqsbhqtgdzc_yz' 	id='6_4' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,4)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="60" type='text' name='jqsbhqtgdzc_bqzje' 	id='6_5' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,5)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="61" type='text' name='jqsbhqtgdzc_ljzje' 	id='6_6' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,6)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="62" type='text' name='hj_yz' 	id='6_7' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,7)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="63" type='text' name='hj_bqzje_zczje' 	id='6_8' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,8)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="64" type='text' name='hj_bqzje_jszje' 	id='6_9' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,9)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="65" type='text' name='hj_ljzje_zczje' 	id='6_10' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,10)">
					</td>																				
                    <td width="80"  class="2-td2-center">
						<input tabindex="66" type='text' name='hj_ljzje_jszje' 	id='6_11' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,11)">
					</td>
                  </tr>                                   

                 
                  <tr> 
                    <td   class="2-td2-left">
						<div align="center">7
							<input type="hidden" name="hc" value="7" id="hc_7">
						</div></td>
                    <td class="2-td2-left"><div align="left">&nbsp;&nbsp;（六）信息传输、软件和信息技术制造业</div></td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="67" type='text' name='fwjzw_yz' 	id='7_1' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,1)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="68" type='text' name='fwjzw_bqzje' 	id='7_2' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,2)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="69" type='text' name='fwjzw_ljzje' 	id='7_3' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,3)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="70" type='text' name='jqsbhqtgdzc_yz' 	id='7_4' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,4)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="71" type='text' name='jqsbhqtgdzc_bqzje' 	id='7_5' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,5)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="72" type='text' name='jqsbhqtgdzc_ljzje' 	id='7_6' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,6)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="73" type='text' name='hj_yz' 	id='7_7' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,7)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="74" type='text' name='hj_bqzje_zczje' 	id='7_8' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,8)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="75" type='text' name='hj_bqzje_jszje' 	id='7_9' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,9)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="76" type='text' name='hj_ljzje_zczje' 	id='7_10' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,10)">
					</td>																				
                    <td width="80"  class="2-td2-center">
						<input tabindex="77" type='text' name='hj_ljzje_jszje' 	id='7_11' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,11)">
					</td>
                  </tr> 
                  
                   <tr> 
                    <td   class="2-td2-left">
						<div align="center">8
							<input type="hidden" name="hc" value="8" id="hc_8">
						</div></td>
                    <td class="2-td2-left"><div align="left">&nbsp;&nbsp;（七）其他行业</div></td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="78" type='text' name='fwjzw_yz' 	id='8_1' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,1)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="79" type='text' name='fwjzw_bqzje' 	id='8_2' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,2)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="80" type='text' name='fwjzw_ljzje' 	id='8_3' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,3)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="81" type='text' name='jqsbhqtgdzc_yz' 	id='8_4' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,4)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="82" type='text' name='jqsbhqtgdzc_bqzje' 	id='8_5' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,5)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="83" type='text' name='jqsbhqtgdzc_ljzje' 	id='8_6' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,6)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="84" type='text' name='hj_yz' 	id='8_7' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,7)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="85" type='text' name='hj_bqzje_zczje' 	id='8_8' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,8)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="86" type='text' name='hj_bqzje_jszje' 	id='8_9' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,9)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="87" type='text' name='hj_ljzje_zczje' 	id='8_10' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,10)">
					</td>																				
                    <td width="80"  class="2-td2-center">
						<input tabindex="88" type='text' name='hj_ljzje_jszje' 	id='8_11' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,11)">
					</td>
                  </tr>                  
                  
                   <tr> 
                    <td   class="2-td2-left">
						<div align="center">9
							<input type="hidden" name="hc" value="9" id="hc_9">
						</div></td>
                    <td class="2-td2-left"><div align="left">二、允许一次性扣除的固定资产</div></td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="89" type='text' name='fwjzw_yz' 	id='9_1' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,1)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="90" type='text' name='fwjzw_bqzje' 	id='9_2' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,2)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="91" type='text' name='fwjzw_ljzje' 	id='9_3' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,3)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="92" type='text' name='jqsbhqtgdzc_yz' 	id='9_4' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,4)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="93" type='text' name='jqsbhqtgdzc_bqzje' 	id='9_5' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,5)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="94" type='text' name='jqsbhqtgdzc_ljzje' 	id='9_6' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,6)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="95" type='text' name='hj_yz' 	id='9_7' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,7)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="96" type='text' name='hj_bqzje_zczje' 	id='9_8' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,8)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="9" type='text' name='hj_bqzje_jszje' 	id='9_9' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,9)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="98" type='text' name='hj_ljzje_zczje' 	id='9_10' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,10)">
					</td>																				
                    <td width="80"  class="2-td2-center">
						<input tabindex="99" type='text' name='hj_ljzje_jszje' 	id='9_11' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,11)">
					</td>
                  </tr>           
                  
                  
                  
                   <tr> 
                    <td   class="2-td2-left">
						<div align="center">10
							<input type="hidden" name="hc" value="10" id="hc_10">
						</div></td>
                    <td class="2-td2-left"><div align="left">&nbsp;&nbsp;（一）单位价值不超过100万元的研发仪器、设备</div></td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="89" type='text' name='fwjzw_yz' 	id='10_1' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,1)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="90" type='text' name='fwjzw_bqzje' 	id='10_2' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,2)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="91" type='text' name='fwjzw_ljzje' 	id='10_3' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,3)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="92" type='text' name='jqsbhqtgdzc_yz' 	id='10_4' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,4)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="93" type='text' name='jqsbhqtgdzc_bqzje' 	id='10_5' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,5)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="94" type='text' name='jqsbhqtgdzc_ljzje' 	id='10_6' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,6)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="95" type='text' name='hj_yz' 	id='10_7' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,7)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="96" type='text' name='hj_bqzje_zczje' 	id='10_8' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,8)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="9" type='text' name='hj_bqzje_jszje' 	id='10_9' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,9)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="98" type='text' name='hj_ljzje_zczje' 	id='10_10' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,10)">
					</td>																				
                    <td width="80"  class="2-td2-center">
						<input tabindex="99" type='text' name='hj_ljzje_jszje' 	id='10_11' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,11)">
					</td>
                  </tr>                    
 
 
                   <tr> 
                    <td   class="2-td2-left">
						<div align="center">11
							<input type="hidden" name="hc" value="11" id="hc_11">
						</div></td>
                    <td class="2-td2-left"><div align="left">&nbsp;&nbsp;&nbsp;其中：六大行业小型微利企业研发和生产经营共用的仪器、设备</div></td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="100" type='text' name='fwjzw_yz' 	id='11_1' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,1)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="101" type='text' name='fwjzw_bqzje' 	id='11_2' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,2)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="102" type='text' name='fwjzw_ljzje' 	id='11_3' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,3)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="103" type='text' name='jqsbhqtgdzc_yz' 	id='11_4' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,4)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="104" type='text' name='jqsbhqtgdzc_bqzje' 	id='11_5' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,5)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="105" type='text' name='jqsbhqtgdzc_ljzje' 	id='11_6' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,6)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="106" type='text' name='hj_yz' 	id='11_7' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,7)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="107" type='text' name='hj_bqzje_zczje' 	id='11_8' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,8)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="108" type='text' name='hj_bqzje_jszje' 	id='11_9' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,9)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="109" type='text' name='hj_ljzje_zczje' 	id='11_10' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,10)">
					</td>																				
                    <td width="80"  class="2-td2-center">
						<input tabindex="110" type='text' name='hj_ljzje_jszje' 	id='11_11' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,11)">
					</td>
                  </tr>     
                 
                   <tr> 
                    <td   class="2-td2-left">
						<div align="center">12
							<input type="hidden" name="hc" value="12" id="hc_12">
						</div></td>
                    <td class="2-td2-left"><div align="left">&nbsp;&nbsp;（二）单位价值不超过5000元的固定资产</div></td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="122" type='text' name='fwjzw_yz' 	id='12_1' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,1)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="123" type='text' name='fwjzw_bqzje' 	id='12_2' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,2)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="124" type='text' name='fwjzw_ljzje' 	id='12_3' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,3)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="125" type='text' name='jqsbhqtgdzc_yz' 	id='12_4' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,4)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="126" type='text' name='jqsbhqtgdzc_bqzje' 	id='12_5' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,5)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="127" type='text' name='jqsbhqtgdzc_ljzje' 	id='12_6' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,6)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="128" type='text' name='hj_yz' 	id='12_7' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,7)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="129" type='text' name='hj_bqzje_zczje' 	id='12_8' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,8)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="130" type='text' name='hj_bqzje_jszje' 	id='12_9' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,9)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="131" type='text' name='hj_ljzje_zczje' 	id='12_10' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,10)">
					</td>																				
                    <td width="80"  class="2-td2-center">
						<input tabindex="132" type='text' name='hj_ljzje_jszje' 	id='12_11' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,11)">
					</td>
                  </tr>   
                  
                   <tr> 
                    <td   class="2-td2-left">
						<div align="center">13
							<input type="hidden" name="hc" value="13" id="hc_13">
						</div></td>
                    <td class="2-td2-left"><div align="center">总&nbsp;&nbsp;&nbsp;&nbsp;计&nbsp;&nbsp;&nbsp;</div></td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="133" type='text' name='fwjzw_yz' 	id='13_1' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,1)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="134" type='text' name='fwjzw_bqzje' 	id='13_2' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,2)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="135" type='text' name='fwjzw_ljzje' 	id='13_3' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,3)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="136" type='text' name='jqsbhqtgdzc_yz' 	id='13_4' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,4)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="137" type='text' name='jqsbhqtgdzc_bqzje' 	id='13_5' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,5)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="138" type='text' name='jqsbhqtgdzc_ljzje' 	id='13_6' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,6)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="139" type='text' name='hj_yz' 	id='13_7' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,7)">
					</td>
                    <td width="80"  class="2-td2-left">
						<input tabindex="140" type='text' name='hj_bqzje_zczje' 	id='13_8' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,8)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="141" type='text' name='hj_bqzje_jszje' 	id='13_9' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,9)">
					</td>	
                    <td width="80"  class="2-td2-left">
						<input tabindex="142" type='text' name='hj_ljzje_zczje' 	id='13_10' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,10)">
					</td>																				
                    <td width="80"  class="2-td2-center">
						<input tabindex="143" type='text' name='hj_ljzje_jszje' 	id='13_11' style='text-align:right' value='' size='13' onblur="formate(this)" onchange="changeNum(this,11)">
					</td>
                  </tr>                     
                  
                   
                </table>
					</div>
					</TD>
				</TR>

				<TR class="black9">
  					<TD> <div align="center">
				              <input type="image" accesskey="s"
										style="cursor:hand" onClick="doSave();return false;"
										onMouseOver="MM_swapImage('baocun','','<%=static_contextpath%>images/bc-s2.jpg',1)"
										onMouseOut="MM_swapImgRestore()"
										src="<%=static_contextpath%>images/bc-s1.jpg" name="Image13"
										width="79" height="22" border="0" id='baocun'>
				              &nbsp;&nbsp;&nbsp;&nbsp;
				              <input type="image" accesskey="u"
										style="cursor:hand" onClick="doReset();return false;"
										onMouseOver="MM_swapImage('qingchu','','<%=static_contextpath%>images/qc-u2.jpg',1)"
										onMouseOut="MM_swapImgRestore()"
										src="<%=static_contextpath%>images/qc-u1.jpg" name="Image132"
										width="79" height="22" border="0" id='qingchu'>
				              &nbsp;&nbsp;&nbsp;&nbsp; 

				              <input type="image" accesskey="x"
										style="cursor:hand" onClick="doDelete();return false;"
										onMouseOver="MM_swapImage('shanchu','','<%=static_contextpath%>images/qbsc-x2.jpg',1)"
										onMouseOut="MM_swapImgRestore()"
										src="<%=static_contextpath%>images/qbsc-x1.jpg" name="Image13"
										width="79" height="22" border="0" id='shanchu'>
				              &nbsp;&nbsp;&nbsp;&nbsp;
							  <input type="image" accesskey="r"
										style="cursor:hand" onClick="doReturn();return false;"
										onMouseOver="MM_swapImage('fanhui','','<%=static_contextpath%>images/fanhui2.jpg',1)"
										onMouseOut="MM_swapImgRestore()"
										src="<%=static_contextpath%>images/fanhui1.jpg" name="fanhui"
										width="79" height="22" border="0" id='fanhui' alt="企业所得税汇总纳税分支机构分配表">
				              &nbsp;&nbsp;&nbsp;&nbsp;
				              <input type="image" accesskey="c"
										onClick="tuichu();return false;"
										onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)"
										onMouseOut="MM_swapImgRestore()" value="退出" id="tc1"
										src="<%=static_contextpath%>images/tc-c1.jpg" width="79"
										height="22" style="cursor:hand">
            </div>
            <BR> </TD>
				</TR>
			</TABLE>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
<%@ include file="../../include/footer.jsp"%>

</html:form>
<script language="JavaScript">
/*初始化*/
function doInit()
{
	  initNumText("fwjzw_yz",10);
	  initNumText("fwjzw_bqzje",10);
	  initNumText("fwjzw_ljzje",10);
	  initNumText("jqsbhqtgdzc_yz",10);
	  initNumText("jqsbhqtgdzc_bqzje",10);
	  initNumText("jqsbhqtgdzc_ljzje",10);
	  initNumText("hj_yz",10);
	  initNumText("hj_bqzje_zczje",10);
	  initNumText("hj_bqzje_jszje",10);
	  initNumText("hj_ljzje_zczje",10);
	  initNumText("hj_ljzje_jszje",10);
	<%
	GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm=(GdzcjszjyjqkjbForm)request.getAttribute("gdzcjszjyjqkjb2014Form");
	ArrayList gdzcList=(ArrayList)gdzcjszjyjqkjbForm.getGdzcjszjyjqkjbList();
	if (gdzcjszjyjqkjbForm!=null && gdzcList.size()>0){
		for(int i=0;i<gdzcList.size();i++){
			HashMap map=(HashMap)gdzcList.get(i);
			int hc=Integer.parseInt((String)map.get("hc"));
			System.out.println("hc ==" + hc);
			String fwjzw_yz=(String)map.get("fwjzw_yz");
			String fwjzw_bqzje=(String)map.get("fwjzw_bqzje");
			String fwjzw_ljzje=(String)map.get("fwjzw_ljzje");
			String jqsbhqtgdzc_yz=(String)map.get("jqsbhqtgdzc_yz");
			String jqsbhqtgdzc_bqzje=(String)map.get("jqsbhqtgdzc_bqzje");
			String jqsbhqtgdzc_ljzje=(String)map.get("jqsbhqtgdzc_ljzje");
			String hj_yz=(String)map.get("hj_yz");
			String hj_bqzje_zczje=(String)map.get("hj_bqzje_zczje");
			String hj_bqzje_jszje=(String)map.get("hj_bqzje_jszje");
			String hj_ljzje_zczje=(String)map.get("hj_ljzje_zczje");
			String hj_ljzje_jszje=(String)map.get("hj_ljzje_jszje");
			%>
			obj = eval("document.getElementById('<%=hc%>_1')");
			obj.value = '<%=fwjzw_yz%>';
			obj = eval("document.getElementById('<%=hc%>_2')");
			obj.value = '<%=fwjzw_bqzje%>';
			obj = eval("document.getElementById('<%=hc%>_3')");
			obj.value = '<%=fwjzw_ljzje%>';
			obj = eval("document.getElementById('<%=hc%>_4')");
			obj.value = '<%=jqsbhqtgdzc_yz%>';
			obj = eval("document.getElementById('<%=hc%>_5')");
			obj.value = '<%=jqsbhqtgdzc_bqzje%>';
			obj = eval("document.getElementById('<%=hc%>_6')");
			obj.value = '<%=jqsbhqtgdzc_ljzje%>';
			obj = eval("document.getElementById('<%=hc%>_7')");
			obj.value = '<%=hj_yz%>';
			obj = eval("document.getElementById('<%=hc%>_8')");
			obj.value = '<%=hj_bqzje_zczje%>';
			obj = eval("document.getElementById('<%=hc%>_9')");
			obj.value = '<%=hj_bqzje_jszje%>';
			obj = eval("document.getElementById('<%=hc%>_10')");
			obj.value = '<%=hj_ljzje_zczje%>';
			obj = eval("document.getElementById('<%=hc%>_11')");
			obj.value = '<%=hj_ljzje_jszje%>';

			<% 
		}
	}
	
	%>
	setSshy(document.all.sshy);
	
	//初始化输入框
	initInput();
}
//初始化输入框
function initInput(){
	for(var i=1;i<=13;i++){
		if(i==1||i==9||i==13){
			for(var j=1;j<=11;j++){
				var id=i+"_"+j;
				var element=document.getElementById(id);
				element.setAttribute("readOnly",'true');
				element.style.backgroundColor = "#EEEEEE";				
			}
		}
		if(i==8||i==10||i==11||i==12){
			for(var j=1;j<=3;j++){
				var id=i+"_"+j;
				var element=document.getElementById(id);
				element.setAttribute("readOnly",'true');
				element.style.backgroundColor = "#EEEEEE";				
			}
		}
		if(i>=2&&i<=7){
			for(var j=1;j<=11;j++){
				if(j==7||j==9||j==11){
					var id=i+"_"+j;
					var element=document.getElementById(id);
					element.setAttribute("readOnly",'true');
					element.style.backgroundColor = "#EEEEEE";	
				}
			}
		}
	}
}


<%/*保存*/%>
	function doSave()
	{
	
	 if(!checkXhgx(true)){
	 	return false;
	 }		
      var list="<%=Constants.QYSDS_CONTROL_CHAR_FOR_JS%>";
		if (!Char_Vaildate(document.forms[0],list)){
			return false;	
		}
	  if(trim(document.all.gjbzhydm.value)==""){
	  	alert("请选择所属行业！");
	  	return false;
	  }
      doSubmitForm('Save');
		
	}
	
	<%/*清除*/%>
	function doReset()
	{
	    if(confirm("确认是否清除当前数据？"))
	    {
	    	for(var i=1;i<=13;i++){
	    		for(var j=1;j<=11;j++){
	    			var id=i+"_"+j;
	    			var obj=document.getElementById(id);
					obj.value = "";
	    		}	    			    		
	    	}
	    }
	    return false;
	}
	//删除
	function doDelete(){
		doSubmitForm('Delete');
	}

	<%/*返回*/%>
	function doReturn()
	{
		doSubmitForm('Return');
	}
	
	//检查输入数据是否为数字并格式化
	function numCheck(obj){
		if(!isNum(obj,null,null,null,null,2)){
			obj.value="0.00";
			return false;
		}
		formate(obj);
	}

	
	//给所属行业赋值
	function setSshy(obj){
		var sshydm=obj.value;
		if(sshydm!=null){
			document.all.gjbzhydm.value=sshydm;
			document.all.sshy.value=sshydm;
		}		
	}
	//格式化数据，计算总计，并校验数据之间相互关系是否正确
	function changeNum(obj,lc){
		numCheck(obj);
		computeZj(lc);
		autoCompute();
		checkXhgx(false);		
	}
	
	//计算总计
	function computeZj(lc){
		var qthy=0;
		var ldhygdzczj=0;
		var yxycxkcdgdzczj=0;
		var zj=0;
		
		for(var i=1;i<=13;i++){
			var id=i+"_"+lc;
			var value=document.getElementById(id).value;
			if(value==null || trim(value)=="") continue;

			if(i>=2&&i<=7){
				ldhygdzczj= (parseFloat(ldhygdzczj)+parseFloat(value)).toFixed(2);
			}else if(i==8){
				qthy=value;
			}else if(i==10||i==12){
				yxycxkcdgdzczj= (parseFloat(yxycxkcdgdzczj)+parseFloat(value)).toFixed(2);
			}						
		}
		zj=(parseFloat(yxycxkcdgdzczj)+parseFloat(ldhygdzczj)+parseFloat(qthy)).toFixed(2);
		document.getElementById("1_"+lc).value=ldhygdzczj==0?"":ldhygdzczj;
		numCheck(document.getElementById("1_"+lc));
		document.getElementById("9_"+lc).value=yxycxkcdgdzczj==0?"":yxycxkcdgdzczj;
		numCheck(document.getElementById("9_"+lc));
		document.getElementById("13_"+lc).value=zj==0?"":zj;
		numCheck(document.getElementById("13_"+lc));
		
	}
	//自动计算部分
	function autoCompute(){
		for(var i=2;i<=7;i++){
			var hj_yz=0;
			var hj_bq_jszje=0;
			var hj_lj_jszje=0;
			var lc1=document.getElementById(i+"_1").value;
			var lc2=document.getElementById(i+"_2").value;	
			var lc3=document.getElementById(i+"_3").value;		
			var lc4=document.getElementById(i+"_4").value;
			var lc5=document.getElementById(i+"_5").value;
			var lc6=document.getElementById(i+"_6").value;
			var lc7=document.getElementById(i+"_7").value;
			var lc9=document.getElementById(i+"_9").value;
			var lc11=document.getElementById(i+"_11").value;
			if(!(isEmpty(lc1)&&isEmpty(lc4))){
				lc1=isEmpty(lc1)?0:lc1;
				lc4=isEmpty(lc4)?0:lc4;
			//	document.getElementById(i+"_7").value=(parseFloat(lc1)+parseFloat(lc4)).toFixed(2);
				//computeZj(7);
				hj_yz=(parseFloat(lc1)+parseFloat(lc4)).toFixed(2);
			}
			if(!(isEmpty(lc2)&&isEmpty(lc5))){
				lc2=isEmpty(lc2)?0:lc2;
				lc5=isEmpty(lc5)?0:lc5;
				//document.getElementById(i+"_9").value=(parseFloat(lc2)+parseFloat(lc5)).toFixed(2);
				//computeZj(9);
				hj_bq_jszje=(parseFloat(lc2)+parseFloat(lc5)).toFixed(2);
			}
			if(!(isEmpty(lc3)&&isEmpty(lc6))){
				lc3=isEmpty(lc3)?0:lc3;
				lc6=isEmpty(lc6)?0:lc6;
				//document.getElementById(i+"_11").value=(parseFloat(lc3)+parseFloat(lc6)).toFixed(2);
				//computeZj(11);
				hj_lj_jszje=(parseFloat(lc3)+parseFloat(lc6)).toFixed(2);
			}
			document.getElementById(i+"_7").value=hj_yz==0?"":hj_yz;
			computeZj(7);
			document.getElementById(i+"_9").value=hj_bq_jszje==0?"":hj_bq_jszje;
			computeZj(9);
			document.getElementById(i+"_11").value=hj_lj_jszje==0?"":hj_lj_jszje;
			computeZj(11);
		}
	}
	function isEmpty(obj){
		if(""==obj||"null"==obj||null==obj){
			return true;
		}
		return false;
	}
	//检查数据之间的相互关系是否正确
	function checkXhgx(isSave){
		for(var i=1;i<=13;i++){
			if(isSave==true&&i==13){
				var lc7=document.getElementById(i+"_7").value==""?0:document.getElementById(i+"_7").value;	
				var lc10=document.getElementById(i+"_10").value==""?0:document.getElementById(i+"_10").value;
				var lc11=document.getElementById(i+"_11").value==""?0:document.getElementById(i+"_11").value;
				if(parseFloat(lc7)<=0){
					alert("该企业“行13列7”应>0，请检查该项目填写数据。");
					return false;
				}
				if(parseFloat(lc10)<=0){
					alert("该企业“行13列10”应>0，请检查该项目填写数据。");
					return false;
				}
				if(parseFloat(lc11)<=0){
					alert("该企业“行13列11”应>0，请检查该项目填写数据。");
					return false;
				}								
			}
			if (i==1||i==9||i==13) continue;
			for(var j=1;j<=11;j++){
				var id=i+"_"+j;
				if(i==10){
					var hc10 =document.getElementById("10_"+j).value==""?0:document.getElementById("10_"+j).value;
					var hc11 =document.getElementById("11_"+j).value==""?0:document.getElementById("11_"+j).value;
					if(parseFloat(hc10)<parseFloat(hc11)){
						alert("该企业“行11列"+j+"”("+hc11+")应≤“行10列"+j+"”("+hc10+")，请检查该项目填写数据。");
						document.getElementById(id).focus();
						return false;
					}					
				}
				var lc1=document.getElementById(i+"_1").value==""?0:document.getElementById(i+"_1").value;				
				var lc2=document.getElementById(i+"_2").value==""?0:document.getElementById(i+"_2").value;
				var lc3=document.getElementById(i+"_3").value==""?0:document.getElementById(i+"_3").value;
				var lc4=document.getElementById(i+"_4").value==""?0:document.getElementById(i+"_4").value;
				var lc5=document.getElementById(i+"_5").value==""?0:document.getElementById(i+"_5").value;
				var lc6=document.getElementById(i+"_6").value==""?0:document.getElementById(i+"_6").value;
				var lc7=document.getElementById(i+"_7").value==""?0:document.getElementById(i+"_7").value;				
				var lc8=document.getElementById(i+"_8").value==""?0:document.getElementById(i+"_8").value;
				var lc9=document.getElementById(i+"_9").value==""?0:document.getElementById(i+"_9").value;
				var lc10=document.getElementById(i+"_10").value==""?0:document.getElementById(i+"_10").value;
				var lc11=document.getElementById(i+"_11").value==""?0:document.getElementById(i+"_11").value;
				if(j==3){
					if(document.getElementById(i+"_3").value=="" && isSave==false) continue;
					if(parseFloat(lc2)>parseFloat(lc3)){
						alert("该企业“行"+i+"列2”("+lc2+")应≤“行"+i+"列3”("+lc3+")，请检查该项目填写数据。");
						document.getElementById(id).focus();
						return false;
					}	
				}
				if(j==6){
					if(document.getElementById(i+"_6").value==""&& isSave==false) continue;
					if(parseFloat(lc5)>parseFloat(lc6)){
						alert("该企业“行"+i+"列5”("+lc5+")应≤“行"+i+"列6”("+lc6+")，请检查该项目填写数据。");
						document.getElementById(id).focus();
						return false;
					}	
				}
				if(j==7){
					if(document.getElementById(i+"_7").value==""&& isSave==false) continue;
					if((parseFloat(lc1)+parseFloat(lc4)).toFixed(2)>parseFloat(lc7)){
						alert("该企业“行"+i+"列1”("+lc1+")与”行"+i+"列4“("+lc4+")的和("+(parseFloat(lc1)+parseFloat(lc4)).toFixed(2)+")应≤“行"+i+"列7”("+lc7+")，请检查该项目填写数据。");
						document.getElementById(id).focus();
						return false;
					}	
				}															
				if(j==9){
					if(document.getElementById(i+"_9").value==""&& isSave==false) continue;
					if(parseFloat(lc8)>=parseFloat(lc9)&& !(parseFloat(lc8)==parseFloat(0) && parseFloat(lc9)==parseFloat(0))){
						alert("该企业“行"+i+"列"+8+"”("+lc8+")应<“行"+i+"列"+9+"”("+lc9+")，请检查该项目填写数据。");
						document.getElementById(id).focus();
						return false;
					}
				}
				if(j==10){
					if(document.getElementById(i+"_10").value==""&& isSave==false) continue;
					if(parseFloat(lc8)>parseFloat(lc10)){
						alert("该企业“行"+i+"列"+8+"”("+lc8+")应≤“行"+i+"列"+10+"”("+lc10+")，请检查该项目填写数据。");
						document.getElementById(id).focus();
						return false;
					}
				}
				if(j==11){
					if(document.getElementById(i+"_11").value==""&& isSave==false) continue;
					if(parseFloat(lc9)>parseFloat(lc11)){
						alert("该企业“行"+i+"列"+9+"”("+lc9+")应≤“行"+i+"列"+11+"”("+lc11+")，请检查该项目填写数据。");
						document.getElementById(id).focus();
						return false;
					}
					if(parseFloat(lc10)>=parseFloat(lc11)&& !(parseFloat(lc10)==parseFloat(0) && parseFloat(lc11)==parseFloat(0))){
						alert("该企业“行"+i+"列"+10+"”("+lc10+")应<“行"+i+"列"+11+"”("+lc11+")，请检查该项目填写数据。");
						document.getElementById(id).focus();
						return false;
					}				
				}
			}
						
		}
		
		
		//检查数据之间的相互关系是否正确(新增)2015.02.11
		return checkXhgxNew(isSave);
		
		
		return true;
	}


function formateNumByValue(value){

		var temp = trim(value+"");
		if(temp.indexOf(".")!=-1){
			var len=temp.indexOf(".")+1;
			if(temp.length-len==1)
			temp = temp+"0";
		}else{
			temp=temp+".00";
		}
		return temp;
	}	
	//检查数据之间的相互关系是否正确(新增)2015.02.11
function checkXhgxNew(isSave){
	for(var i=2;i<=8;i++){
		for(var j=1;j<=11;j++){
			var lc=document.getElementById(i+"_"+j).value==""?0:document.getElementById(i+"_"+j).value;
			if(document.getElementById(i+"_"+j).value=="") continue;
			if(j==2||j==5){
				if(parseFloat(lc)<0){
					alert("该企业“行"+i+"列"+j+"("+lc+")”应≥0，请检查该项目填写数据。");
					document.getElementById(i+"_"+j).focus();
					return false;
				}				
			}else{
				if (i==8&&j>=1&&j<=3) continue;
				if(parseFloat(lc)<=0){
					alert("该企业“行"+i+"列"+j+"("+lc+")”应>0，请检查该项目填写数据。");
					document.getElementById(i+"_"+j).focus();
					return false;
				}				
			}
		}
		if(i==8) continue;
	
		for(var k=1;k<=11;k++){
		   var lc1=document.getElementById(i+"_1").value==""?0:document.getElementById(i+"_1").value;
		   var lc2=document.getElementById(i+"_2").value==""?0:document.getElementById(i+"_2").value;
		   var lc3=document.getElementById(i+"_3").value==""?0:document.getElementById(i+"_3").value;
		   var lc4=document.getElementById(i+"_4").value==""?0:document.getElementById(i+"_4").value;
		   var lc5=document.getElementById(i+"_5").value==""?0:document.getElementById(i+"_5").value;
		   var lc6=document.getElementById(i+"_6").value==""?0:document.getElementById(i+"_6").value;
		   var lc10=document.getElementById(i+"_10").value==""?0:document.getElementById(i+"_10").value;
		   
		 if(k==10){
		   if(document.getElementById(i+"_10").value==""&& isSave==false) continue;
		   if((parseFloat(lc1)/20+parseFloat(lc4)/3).toFixed(2)<parseFloat(lc10)){
			   alert("该企业“行"+i+"列1/20”("+(parseFloat(lc1)/20).toFixed(2)+")与”行"+i+"列4/3“("+(parseFloat(lc4)/3).toFixed(2)+")的和("+(parseFloat(lc1)/20+parseFloat(lc4)/3).toFixed(2)+")应≥“行"+i+"列10”("+lc10+")，请检查该项目填写数据。");
			   document.getElementById(i+"_10").focus();
			   return false;
		   }
		 }
		
		 if(k==3){
		   if(document.getElementById(i+"_3").value==""&& isSave==false) continue;
		   var qh=document.all.qh.value;
		   if(((parseFloat(lc1)*2/20)*(parseFloat(qh)/4)).toFixed(2)<parseFloat(lc3)){
			   alert("该企业“行"+i+"列3”("+lc3+")应≤“行"+i+"列1”×（2/20×100%）×季度值/4的值("+((parseFloat(lc1)*2/20)*(parseFloat(qh)/4)).toFixed(2)+")，请检查该项目填写数据。");
			   document.getElementById(i+"_3").focus();
			   return false;
		   }
		 }
		 
		 if(k==2){
		   if(document.getElementById(i+"_2").value==""&& isSave==false) continue;
		   if(((parseFloat(lc1)*2/20)*(1/4)).toFixed(2)<parseFloat(lc2)){
			   alert("该企业“行"+i+"列2”("+lc2+")应≤“行"+i+"列1”×（2/20×100%）/4的值("+((parseFloat(lc1)*2/20)*(1/4)).toFixed(2)+")，请检查该项目填写数据。");
			   document.getElementById(i+"_2").focus();
			   return false;
		   }
		 }
		 
		 if(k==6){
		   if(document.getElementById(i+"_6").value==""&& isSave==false) continue;
		   if(((parseFloat(lc4)*2/3)*(parseFloat(qh)/4)).toFixed(2)<parseFloat(lc6)){
			   alert("该企业“行"+i+"列6”("+lc6+")应≤“行"+i+"列4”×（2/3×100%）×季度值/4的值("+((parseFloat(lc4)*2/3)*(parseFloat(qh)/4)).toFixed(2)+")，请检查该项目填写数据。");
		   	   document.getElementById(i+"_6").focus();
			   return false;
		   }
		 }
		 
		 if(k==5){
		   if(document.getElementById(i+"_5").value==""&& isSave==false) continue;
		   if(((parseFloat(lc4)*2/3)*(1/4)).toFixed(2)<parseFloat(lc5)){
			   alert("该企业“行"+i+"列5”("+lc5+")应≤“行"+i+"列4”×（2/3×100%）/4的值("+((parseFloat(lc4)*2/3)*(1/4)).toFixed(2)+")，请检查该项目填写数据。");
			   document.getElementById(i+"_5").focus();
			   return false;
		   }
		 }
	   }
	}
	return true;
}

//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外  
 /*function banBackSpace(e){     
     var ev = e || window.event;//获取event对象     
     var obj = ev.target || ev.srcElement;//获取事件源     
       
     var t = obj.type || obj.getAttribute('type');//获取事件源类型    
       
     //获取作为判断条件的事件类型  
     var vReadOnly = obj.getAttribute('readonly');  
     var vEnabled = obj.getAttribute('enabled');  
     //处理null值情况  
     vReadOnly = (vReadOnly == null) ? false : vReadOnly;  
     vEnabled = (vEnabled == null) ? true : vEnabled;  
       
     //当敲Backspace键时，事件源类型为密码或单行、多行文本的，  
     //并且readonly属性为true或enabled属性为false的，则退格键失效  
     var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")   
                 && (vReadOnly==true || vEnabled!=true))?true:false;  
      
     //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效  
     var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")  
                 ?true:false;          
       
     //判断  
     if(flag2){  
         return false;  
     }  
     if(flag1){     
         return false;     
     }     
 }  
	*/
</script>
</body>
</html:html>
